// Extended's car and stage select, for free play and the career: its own car
// select, then a stage select that renders the stage (the jar's fase 1
// fly-around) under the port's controls (DOM, translated) instead of the
// jar's title card, arrows and kB loading screen. Run by race.js before the
// race; resolves once a stage is picked (the jar's fase 5), and race.js
// fast-forwards to the start line from there.
//
// Free play: all 39 cars, and two groups of stages -- NFM 2 (the base game's
// 32, raced with the NFM2 models through stagecompat.js) and Extended (its
// own, tracks.radq); the career's too in developer mode. The car select runs
// in the jar's normal mode (neither classic nor career), which lists every car
// with its creator and stat bars; the jar sets no BACK/NEXT limit there, so 0
// and 38 are enforced here. A stage then sets the mode it is raced in:
// Extended's own stages are the normal mode's (tracks.radq, opponents from
// Extended's cars); NFM 2 stages are classic mode's (opponents from the NFM2
// cars, AI by classicTwin's stage number).
//
// Career: the jar's car select as it is (locks, levels, stat points, bonus
// cars, reset/sell, change stats: all the jar's own, mouse included), and the
// stage select's career extras as DOM buttons that do what its ctachm does --
// bonus stage, hard mode / scale levels / no levels, xp gain, scouting (the
// jar's own screen), change car, back to the menu.

import { CAREER_STAGES, EXT_CARS, EXT_STAGES, classicTwin } from './catalog.js';
import { devMode } from '../devmode.js';
import { tr } from '../i18n.js';

const STORE_KEY = 'nfm.ext.free';
// the career's stages only in developer mode (catalog.js)
export const groups = (dev = devMode()) => (dev ? ['nfm2', 'ext', 'career'] : ['nfm2', 'ext']);
const GROUP_LABEL = { nfm2: 'NFM 2', ext: 'Extended', career: 'Career' };
const MENU_TICK_MS = 40;   // the jar's menus: 10 frames per 400 ms

/** Remembered pick, as the base launcher remembers its car and stage. */
export function loadPick(GROUPS = groups()) {
  let p = {};
  try { p = JSON.parse(localStorage.getItem(STORE_KEY) || '{}'); } catch { /* none, or private mode */ }
  return {
    car: Number.isInteger(p.car) && p.car >= 0 && p.car < EXT_CARS.length ? p.car : 38,
    group: GROUPS.includes(p.group) ? p.group : 'ext',
    stage: Number.isInteger(p.stage) ? p.stage : 1,
  };
}
const savePick = (p) => { try { localStorage.setItem(STORE_KEY, JSON.stringify(p)); } catch { /* private mode */ } };

/** The stages of a group, [n, name], in list order. */
export function stagesOf(group, nfm2Names) {
  if (group === 'career') return CAREER_STAGES;
  return group === 'ext' ? EXT_STAGES : nfm2Names.map((name, i) => [i + 1, name]);
}

/** Step within the group's list, stopping at its ends (the jar's arrows do not wrap). */
export function step(list, n, d) {
  const k = list.findIndex(([m]) => m === n);
  const j = Math.max(0, Math.min(list.length - 1, (k < 0 ? 0 : k) + d));
  return list[j][0];
}

/** The car select's arrows, which the jar does not limit in its normal mode. */
export function clampCarArrows(control, car) {
  if (car <= 0) control.left = false;
  if (car >= EXT_CARS.length - 1) control.right = false;
}

/** Where the jar races stage n of a group: its mode and checkpoints.stage. */
export function jarStage(group, n) {
  if (group === 'nfm2') return { classic: true, career: false, stage: classicTwin(n) };
  return { classic: false, career: group === 'career', stage: n };
}

/** The career's arrows (xtGraphics.stageselect): right only up to unlocked[1] (and 31). */
export function careerStep(stage, unlocked, d) {
  if (d < 0) return { stage: stage > 1 ? stage - 1 : stage };
  return stage < 31 && stage < unlocked ? { stage: stage + 1 } : { stage, locked: true };
}

/** The jar's bonus stage at this career stage (ctachm fase 1), or -1. */
export const bonusAt = (stage) => [5, 11, 15, 18].indexOf(stage);

/**
 * @param {object} o
 * @param o.mode            'free' | 'career'
 * @param o.gs, o.frame     the GameSparker and its run() generator
 * @param o.xt, o.cp        what readdata handed over
 * @param o.gl              the race surface (JGraphics2D): the stage select draws here
 * @param o.menu            a Canvas2D JGraphics over it: the car select and scouting draw here
 *                          (they put their backdrop UNDER the car, which the race surface cannot)
 * @param o.menuCanvas, o.host  that canvas, and the #stage box the controls go in
 * @param o.nfm2Names       the base game's 32 stage names (free play)
 * @param o.setBaseStage    (n | null) -> the NFM 2 stage loadstage substitutes (free play)
 * @param o.exit            back to the launcher
 */
export function runMenus({ mode, gs, frame, xt, cp, gl, menu, menuCanvas, host, nfm2Names = [], setBaseStage = () => {}, exit }) {
  const career = mode === 'career';
  const GROUPS = groups();
  const pick = loadPick(GROUPS);
  const control = gs.u[0];
  const listOf = (g) => stagesOf(g, nfm2Names);
  if (!career && !listOf(pick.group).some(([n]) => n === pick.stage)) pick.stage = listOf(pick.group)[0][0];

  const apply = () => {
    const { classic, career: c, stage } = jarStage(pick.group, pick.stage);
    xt.careermode = c;
    xt.classicmode = classic;
    xt.hardstage = false;
    cp.stage = stage;
    setBaseStage(pick.group === 'nfm2' ? pick.stage : null);
  };

  // ---- the jar's screens, as the menus need them ----------------------------
  const own = { carselect: xt.carselect, stageselect: xt.stageselect, trackbg$m: xt.trackbg$m, loadingstage: xt.loadingstage };
  if (!career) {
    xt.careermode = xt.classicmode = false;
    xt.sc[0] = xt.lastcar = pick.car;
  }
  xt.carselect = function (control_, ...rest) {
    if (!career) {
      this.careermode = this.classicmode = false;
      clampCarArrows(control_, this.sc[0]);
    }
    own.carselect.call(this, control_, ...rest);
    if (this.fase !== 6476) return;      // Enter: the car is chosen, on to the stage
    if (career) { this.lastcar = this.sc[0]; return; }
    pick.car = this.sc[0];
    savePick(pick);
    apply();
  };
  // the rendered stage is the stage select's backdrop; scouting keeps its own (trackbg$m: the field is trackbg)
  xt.trackbg$m = function (...a) { if (this.fase !== 1) return own.trackbg$m.apply(this, a); };
  // no kB-counting loading screen between stages; its music part stays (xtGraphics.loadingstage)
  xt.loadingstage = function () {
    if (this.lastload !== -22) {
      this.stages.loadMod(135, 7800, 125, this.sunny, this.macn);
      this.lastload = -22;
    } else {
      this.stages.stop();
    }
  };
  // the jar's CONTINUE (xtGraphics.stageselect)
  const go = (c, asay) => {
    c.enter = c.handb = false;
    xt.asay = asay;
    xt.dudo = 150;
    xt.m.trk = false;
    xt.m.focus_point = 400;
    xt.fase = 5;
    try { xt.stages.stop(); xt.stages.unloadMod(); } catch { /* no stage music loaded */ }
  };
  let notice = '', noticeUntil = 0;
  xt.stageselect = function (checkpoints, c, madness) {
    // what the jar's stageselect does besides drawing: forget loaded music, play the menu's
    for (let i = 0; i < 200; i++) {
      this.mtracks[i] = null; this.stracks[i] = null;
      this.isMidi[i] = this.isOgg[i] = this.loadedt[i] = false;
    }
    this.stages.play();
    if (career) return careerSelect.call(this, checkpoints, c, madness);
    paint();
    let group = pick.group, to = pick.stage;
    if (c.left) to = step(listOf(group), to, -1);
    if (c.right) to = step(listOf(group), to, +1);
    if (c.up || c.down) {
      group = GROUPS[(GROUPS.indexOf(group) + 1) % GROUPS.length];
      to = listOf(group)[0][0];
    }
    c.left = c.right = c.up = c.down = false;
    choose(group, to);
    if (c.enter || c.handb) {
      savePick(pick);
      go(c, `Stage ${pick.stage}:  ${checkpoints.name}`);
    }
  };
  function careerSelect(checkpoints, c, madness) {
    paint(madness);
    // in a bonus stage the jar has no arrows; here back (the arrow or the button) returns to the career stage
    if (this.bonstage && c.left) extra.normal();
    else if (!this.bonstage && this.showopstage !== 195 && (c.left || c.right)) {
      const r = careerStep(checkpoints.stage, this.unlocked[1], c.right ? 1 : -1);
      if (r.locked) {
        notice = tr(`This stage will be unlocked when stage ${checkpoints.stage} is complete!`);
        noticeUntil = performance.now() + 2500;
      } else if (r.stage !== checkpoints.stage) {
        checkpoints.stage = r.stage;
        this.hardstage = this.scalelevels = this.nolevels = false;
        this.fase = 6476;
      }
    }
    c.left = c.right = c.up = c.down = false;
    if (c.enter || c.handb) {
      const b = this.bonusstage.findIndex(Boolean);
      go(c, this.bonstage && b >= 0 ? `Bonus Stage ${b + 1}: ${checkpoints.name}` : `Stage ${checkpoints.stage}:  ${checkpoints.name}`);
    }
  }
  // the career extras: what ctachm's fase 1 clicks do (xtGraphics.java:17657-17757)
  const extra = {
    bonus() {
      const a = bonusAt(cp.stage);
      if (a < 0 || xt.bonstage) return;
      xt.bonstage = true;
      xt.scalelevels = xt.nolevels = xt.hardstage = false;
      xt.bonusstage[a] = true;
      if (a === 0 || a === 2) xt.unlimitedlaps = true;
      xt.fase = 6476;
    },
    // out of the bonus stage, back to the career stage it hangs off: what resetmaini clears of it
    normal() {
      if (!xt.bonstage) return;
      xt.bonusstage.fill(false);
      xt.bonstage = xt.unlimitedlaps = false;
      xt.scalelevels = xt.nolevels = xt.hardstage = false;
      xt.musicswitch = 1;
      xt.duration = xt.pausetime = xt.elapsed = 0;
      xt.fase = 6476;
    },
    hard() { xt.hardstage = true; xt.scalelevels = xt.nolevels = false; xt.fase = 6476; },
    scale() { xt.scalelevels = !xt.scalelevels; xt.hardstage = xt.nolevels = false; xt.fase = 6476; },
    nolevels() {
      xt.nolevels = !xt.nolevels;
      if (xt.nolevels) xt.averagelevel = 1;
      xt.scalelevels = xt.hardstage = false;
      xt.fase = 6476;
    },
    xp() { xt.disablexp = !xt.disablexp; },
    scout() { xt.fase = 201; },
    // CHANGE CAR / RETURN TO MENU: fase 1110 with tocs / tomaini, as the jar's buttons
    car() { xt.fase = 1110; xt.tocs = true; xt.m.showsnow = false; xt.stages.stop(); xt.stages.unloadMod(); },
    menu() { xt.fase = 1110; xt.tomaini = true; xt.m.showsnow = false; xt.stages.stop(); xt.stages.unloadMod(); },
  };
  const choose = (group, n) => {
    if (group === pick.group && n === pick.stage) return;
    pick.group = group;
    pick.stage = n;
    apply();
    xt.fase = 6476;                      // randomno for this stage, then fase 2 loads it
    paint();
  };

  // ---- the stage select's controls (DOM, over the rendered stage) -----------
  const ui = document.createElement('div');
  ui.style.cssText = 'position:absolute;left:0;top:0;width:870px;height:480px;z-index:4;pointer-events:none;'
    + 'font:bold 14px Arial,sans-serif;color:#fff;';
  const sel = 'pointer-events:auto;height:24px;background:#000;color:rgb(47,179,255);'
    + 'border:1px solid rgb(47,179,255);font:bold 13px Arial,sans-serif;padding:0 2px;';
  const btn = 'pointer-events:auto;cursor:pointer;background:rgba(0,0,0,.75);color:#fff;border:1px solid #fff;'
    + 'font:bold 15px Arial,sans-serif;padding:5px 16px;';
  const small = 'pointer-events:auto;cursor:pointer;background:rgba(0,0,0,.7);color:#fff;border:1px solid rgba(255,255,255,.6);'
    + 'font:bold 12px Arial,sans-serif;padding:4px 10px;text-align:left;';
  const shade = 'text-shadow:0 0 4px #000,0 0 2px #000';
  ui.innerHTML = `
    <div data-k="title" style="position:absolute;left:0;right:0;top:10px;text-align:center;font-size:22px;${shade}"></div>
    <div data-k="sub" style="position:absolute;left:0;right:0;top:42px;text-align:center;font-size:14px;${shade}"></div>
    <div data-k="picks" style="position:absolute;left:0;right:0;top:46px;display:flex;justify-content:center;gap:6px">
      <select data-k="group" style="${sel}width:120px" aria-label="${tr('Stages')}"></select>
      <select data-k="stage" style="${sel}width:300px" aria-label="${tr('Stage')}"></select>
    </div>
    <div data-k="notice" style="position:absolute;left:0;right:0;top:200px;text-align:center;font-size:17px;color:#ffd24a;${shade}"></div>
    <div data-k="left" style="position:absolute;left:14px;top:90px;display:flex;flex-direction:column;gap:6px">
      <button data-k="bonus" style="${small}">${tr('BONUS STAGE!')}</button>
      <button data-k="normal" style="${small}">${tr('Back to the normal stage')}</button>
      <button data-k="scout" style="${small}">${tr('Scouting')}</button>
      <button data-k="xp" style="${small}"></button>
    </div>
    <div data-k="right" style="position:absolute;right:14px;top:90px;display:flex;flex-direction:column;gap:6px">
      <button data-k="hard" style="${small}">${tr('hard mode')}</button>
      <button data-k="scale" style="${small}">${tr('scale levels')}</button>
      <button data-k="nolevels" style="${small}">${tr('no levels')}</button>
    </div>
    <div style="position:absolute;left:0;right:0;top:420px;display:flex;justify-content:center;gap:10px">
      <button data-k="car" style="${btn}">${tr('Change car')}</button>
      <button data-k="prev" style="${btn}">◂</button>
      <button data-k="go" style="${btn}">${tr('Race')}</button>
      <button data-k="next" style="${btn}">▸</button>
      <button data-k="menu" style="${btn}">${tr('Menu')}</button>
    </div>
    <div data-k="hint" style="position:absolute;left:0;right:0;top:458px;text-align:center;font-size:12px;opacity:.85;${shade}">
      ${tr(career ? '◂ ▸ stage · Enter race · Esc change car' : '◂ ▸ stage · ▴ ▾ stages · Enter race · Esc change car')}</div>`;
  const $ = (k) => ui.querySelector(`[data-k="${k}"]`);
  ui.addEventListener('mousedown', (e) => e.stopPropagation());   // not a click on the jar's screen
  $('group').onchange = () => { const g = $('group').value; $('group').blur(); choose(g, listOf(g)[0][0]); };
  $('stage').onchange = () => { const n = +$('stage').value; $('stage').blur(); choose(pick.group, n); };
  $('prev').onclick = () => { control.left = true; };
  $('next').onclick = () => { control.right = true; };
  $('go').onclick = () => { control.enter = true; };
  for (const k of Object.keys(extra)) if ($(k)) $(k).onclick = (e) => { e.currentTarget.blur(); extra[k](); painted = ''; };
  if (career) $('picks').remove();
  else for (const k of ['left', 'right', 'car', 'menu', 'sub']) $(k).remove();
  host.append(ui);
  let painted = '';
  function paint(madness) {
    $('notice').textContent = performance.now() < noticeUntil ? notice : '';
    const key = `${pick.group}/${pick.stage}/${cp.stage}/${cp.name}/${xt.bonstage}/${xt.hardstage}/${xt.scalelevels}/${xt.nolevels}/${xt.disablexp}/${xt.averagelevel}`;
    if (key === painted) return;
    painted = key;
    if (!career) {
      $('group').replaceChildren(...GROUPS.map((g) => new Option(tr(GROUP_LABEL[g]), g)));
      $('group').value = pick.group;
      $('stage').replaceChildren(...listOf(pick.group).map(([n, name]) => new Option(`${n}. ${name}`, String(n))));
      $('stage').value = String(pick.stage);
      $('title').textContent = xt.fase === 1 ? tr(`Stage ${pick.stage}: ${cp.name}`) : '';
      return;
    }
    const b = xt.bonusstage.findIndex(Boolean);
    $('title').textContent = xt.bonstage && b >= 0 ? tr(`BONUS STAGE ${b + 1}`) : tr(`Stage ${cp.stage}: ${cp.name}`);
    $('title').style.color = !xt.bonstage && cp.stage < xt.unlocked[1] && xt.hardstage ? 'rgb(240,120,120)' : '';
    // the opponents' level, and whether this race earns bonus stat points (xtGraphics.stageselect)
    const mine = madness?.[0]?.level?.[xt.sc[0]] ?? 1;
    const hard = (xt.hardstage || cp.stage === xt.unlocked[1] || xt.bonstage) && mine < xt.averagelevel - 2;
    const capped = xt.startinglevel >= xt.softlevelcap && !xt.nolevels;
    const sub = $('sub');
    const lvl = document.createElement('span');
    lvl.textContent = tr(`Opponents: level ${xt.averagelevel}`);
    lvl.style.color = mine >= xt.averagelevel ? 'rgb(0,200,0)' : 'rgb(230,60,60)';
    sub.replaceChildren(lvl);
    if (hard || capped) {
      const w = document.createElement('span');
      w.textContent = ' · ' + tr(capped ? 'No bonus stat points' : '+ bonus stat points');
      w.style.color = capped ? 'rgb(255,60,60)' : 'rgb(0,200,0)';
      sub.append(w);
    }
    $('bonus').style.display = bonusAt(cp.stage) >= 0 && !xt.bonstage ? '' : 'none';
    $('xp').textContent = tr(xt.disablexp ? 'xp gain: DISABLED' : 'xp gain: ENABLED');
    $('xp').style.background = xt.disablexp ? 'rgba(125,0,0,.75)' : 'rgba(0,125,0,.75)';
    const custom = cp.stage < xt.unlocked[1] && !xt.bonstage && xt.unlocked[1] >= 3;
    $('right').style.display = custom ? 'flex' : 'none';
    for (const [k, on] of [['hard', xt.hardstage], ['scale', xt.scalelevels], ['nolevels', xt.nolevels]]) {
      $(k).style.borderColor = on ? '#ffd24a' : 'rgba(255,255,255,.6)';
      $(k).style.color = on ? '#ffd24a' : '#fff';
    }
    $('normal').style.display = xt.bonstage ? '' : 'none';
    $('next').style.visibility = xt.bonstage ? 'hidden' : '';
  }

  // ---- the loop: one jar frame per menu tick --------------------------------
  // The jar's canvas screens -- the car select, scouting -- draw on the Canvas2D
  // `menu`; the stage select on the race surface.
  const MENU_FASES = new Set([-9, 7, 201, 202, 205, 1110]);
  return new Promise((resolve, reject) => {
    let acc = 0, last = performance.now(), raf = 0;
    const onKey = (e) => {
      if (e.key !== 'Escape') return;
      if (xt.fase !== 7 && xt.fase !== 1) return;   // scouting: the jar takes Esc as Enter, back to the stage
      e.preventDefault();
      e.stopImmediatePropagation();
      if (xt.fase === 7) exit();
      else if (career) extra.car();
      else xt.fase = -9;                  // the jar's CHANGE CAR: back to the car select
    };
    addEventListener('keydown', onKey, true);
    const finish = (err) => {
      cancelAnimationFrame(raf);
      removeEventListener('keydown', onKey, true);
      ui.remove();
      menuCanvas.style.display = 'none';
      Object.assign(xt, own);
      gs.rd = xt.rd = gl;
      err ? reject(err) : resolve(pick);
    };
    const tick = () => {
      const onMenu = MENU_FASES.has(xt.fase);
      gs.rd = xt.rd = onMenu ? menu : gl;
      if (!onMenu) gl.begin();
      frame.next();
      if (xt.fase === 1 && !onMenu) gl.end();
      menuCanvas.style.display = MENU_FASES.has(xt.fase) ? '' : 'none';
      ui.style.display = xt.fase === 1 ? '' : 'none';
    };
    const loop = (now) => {
      raf = requestAnimationFrame(loop);
      acc = Math.min(acc + now - last, MENU_TICK_MS * 3);
      last = now;
      try {
        // one tick per animation frame at most: a stage draw is heavy, and the jar's loop never catches up
        if (acc >= MENU_TICK_MS) { acc = Math.min(acc - MENU_TICK_MS, MENU_TICK_MS - 1); tick(); }
        if (xt.fase === 5) finish();
        else if (xt.fase === 10) { finish(); exit(); }   // RETURN TO MENU
      } catch (e) { finish(e); }
    };
    raf = requestAnimationFrame(loop);
  });
}
