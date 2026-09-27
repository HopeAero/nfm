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
// cars, reset/sell, change stats: all the jar's own, mouse included) plus the
// port's Confirm / Undo, -, held + and New career; and the jar's own stage
// select over the rendered stage -- the base port's look, with Extended's
// buttons (bonus stage, xp gain, hard / scale / no levels, scouting, change
// car, menu) and its locked-stage screen -- plus a way back out of a bonus
// stage.

import { CAREER_STAGES, EXT_CARS, EXT_STAGES, classicTwin } from './catalog.js';
import { devMode } from '../devmode.js';
import { tr } from '../i18n.js';
import { restoreStats, statsOf } from './career-save.js';
import { NEW_BASE, lastCar, firstCar, newCars, setCarGroup, carGroup, cycleCarGroup, groupOf } from './newcars.js';
import { groupSprites } from './cargroup.js';
import { RIVALS_KEY, closeRivals, fitThumb, loadRivals, rivalsButton, runRivals, saveRivals } from '../rivals.js';
import { JGraphics } from './jgraphics.js';
import { EXT_TIER } from './tiers.js';

const STORE_KEY = 'nfm.ext.free';
// the career's stages only in developer mode (catalog.js)
export const groups = (dev = devMode()) => (dev ? ['nfm2', 'ext', 'career'] : ['nfm2', 'ext']);
const GROUP_LABEL = { nfm2: 'NFM 2', ext: 'Extended', career: 'Career' };
const MENU_TICK_MS = 40;   // the jar's menus: 10 frames per 400 ms

/** Remembered pick, as the base launcher remembers its car and stage. */
export function loadPick(GROUPS = groups()) {
  let p = {};
  try { p = JSON.parse(localStorage.getItem(STORE_KEY) || '{}'); } catch { /* none, or private mode */ }
  const car = pickCar(p);
  // the car select's group (the game's / mine): the one that holds the remembered car
  setCarGroup(groupOf(car));
  return {
    car, carName: p.carName, carGroup: carGroup(),
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

/** A remembered pick: stock cars by index, new cars by name (their index moves with the list). */
export function pickCar(p) {
  if (Number.isInteger(p.car) && p.car >= 0 && p.car < EXT_CARS.length) return p.car;
  const k = newCars().findIndex((c) => c.name === p.carName);
  return k >= 0 ? NEW_BASE + k : 38;
}

/** The car select's arrows, which the jar does not limit in its normal mode; past 38, the new cars. */
export function clampCarArrows(control, car) {
  if (car <= firstCar()) control.left = false;
  if (car >= lastCar()) control.right = false;
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
 * @param o.careerStore     a real career's { save, saves, reset } (race.js), or null
 * @param o.setPlayers      (n) -> the free-play field size race.js gives randomno (the Rivals screen)
 */
export function runMenus({ mode, gs, frame, xt, cp, gl, menu, menuCanvas, host, nfm2Names = [], setBaseStage = () => {}, prepareStage = () => Promise.resolve(), exit, careerStore = null, setPlayers = () => {} }) {
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
  let aconto = null;
  const own = { carselect: xt.carselect, stageselect: xt.stageselect, trackbg$m: xt.trackbg$m, loadingstage: xt.loadingstage };
  if (!career) {
    xt.careermode = xt.classicmode = false;
    xt.sc[0] = xt.lastcar = pick.car;
  }
  xt.carselect = function (control_, ...rest) {
    if (!career) {
      this.careermode = this.classicmode = false;
      control_.up = control_.down = false;   // ▴ ▾ switch the car group (onKey), nothing in the jar
      clampCarArrows(control_, this.sc[0]);
    }
    if (careerStore) {
      stat.mad = rest[1];
      // what Undo returns to: the stats as last saved (by Confirm, or by the jar's own saves)
      if (stat.base === null || careerStore.saves() !== stat.saves) { stat.base = statsOf(this, stat.mad); stat.saves = careerStore.saves(); }
    }
    aconto = rest[0];                    // the car models, for the Rivals cards
    own.carselect.call(this, control_, ...rest);
    if (careerStore) stat.pending = statsOf(this, stat.mad) !== stat.base;
    if (this.fase !== 6476) return;      // Enter: the car is chosen, on to the stage
    if (career) { this.lastcar = this.sc[0]; if (stat.pending) stat.confirm(); return; }
    pick.car = this.sc[0];
    pick.carName = newCars()[this.sc[0] - NEW_BASE]?.name;   // a new car is found again by name
    pick.carGroup = carGroup();
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
  // Free play's RIVALS button on the stage select (rivals.js). The stage on show was
  // loaded with the old field, so a changed config reloads it (fase 6476: randomno,
  // loadstage, sortcars -- race.js applies it) and the select carries on.
  let rivalsOpen = false;
  const openRivals = async () => {
    if (rivalsOpen || xt.fase !== 1) return;
    rivalsOpen = true;
    const before = loadRivals(RIVALS_KEY.ext, EXT_CARS.length, 19);
    const cfg = await runRivals({
      host, tiers: ['C', 'B', 'A', 'S'], min: 1, max: 19,
      gameCount: xt.classicmode ? 7 : 11,          // the jar's randomno for the stage group
      cars: EXT_CARS.map((n, i) => ({ i, name: n, tier: EXT_TIER[i] })),
      cfg: before, thumb: carThumb,
    });
    rivalsOpen = false;
    painted = '';
    if (!cfg || JSON.stringify(cfg) === JSON.stringify(before)) return;
    saveRivals(RIVALS_KEY.ext, cfg);
    setPlayers(cfg.count);
    xt.fase = 6476;
  };
  // a car card: the car select's camera and spin pose on a canvas of its own, the
  // shared camera and the model put back after (xtGraphics.carselect)
  let thumbCanvas = null, thumbRd = null;
  const carThumb = (i) => {
    const o = aconto?.[i];
    if (!o) return null;
    thumbCanvas ??= Object.assign(document.createElement('canvas'), { width: 870 * 2, height: 480 * 2 });
    thumbRd ??= new JGraphics(thumbCanvas, 870, 480);
    const m = xt.m, CAM = ['crs', 'x', 'y', 'z', 'xz', 'zy', 'ground'], POSE = ['x', 'y', 'z', 'xz', 'zy', 'wzy'];
    const cam = CAM.map((k) => m[k]), pose = POSE.map((k) => o[k]);
    Object.assign(m, { crs: true, x: -435, y: -540, z: -50, xz: 0, zy: 10, ground: 510 });
    Object.assign(o, { x: 0, y: -34 - o.grat, z: 950, xz: 200, zy: 0 });
    thumbCanvas.getContext('2d').clearRect(0, 0, thumbCanvas.width, thumbCanvas.height);
    o.d(thumbRd);
    CAM.forEach((k, j) => { m[k] = cam[j]; });
    POSE.forEach((k, j) => { o[k] = pose[j]; });
    return fitThumb(thumbCanvas);
  };
  xt.stageselect = function (checkpoints, c, madness) {
    // The career: the jar's own stage select, drawn over the rendered stage -- the base port's
    // look (its arrows, CONTINUE, the locked-stage screen) with Extended's own buttons (bonus
    // stage, xp gain, hard mode / scale / no levels, scouting, change car, menu). The one
    // addition: out of a bonus stage, back to the career stage (the arrow, or the button).
    if (career) {
      paint();
      if (this.bonstage && c.left) { c.left = false; extra.normal(); return; }
      return own.stageselect.call(this, checkpoints, c, madness);
    }
    // what the jar's stageselect does besides drawing: forget loaded music, play the menu's
    for (let i = 0; i < 200; i++) {
      this.mtracks[i] = null; this.stracks[i] = null;
      this.isMidi[i] = this.isOgg[i] = this.loadedt[i] = false;
    }
    this.stages.play();
    paint();
    if (rivalsOpen) { c.left = c.right = c.up = c.down = c.enter = c.handb = false; return; }
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
  const extra = {
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
    // CHANGE CAR: fase 1110 with tocs, as the jar's button (Esc on the stage select)
    car() { xt.fase = 1110; xt.tocs = true; xt.m.showsnow = false; xt.stages.stop(); xt.stages.unloadMod(); },
  };
  const choose = (group, n) => {
    if (group === pick.group && n === pick.stage) return;
    pick.group = group;
    pick.stage = n;
    apply();
    xt.fase = 6476;                      // randomno for this stage, then fase 2 loads it
    paint();
  };

  // ---- the car select's stat changes: Confirm / Undo (a real career) -----------
  // The jar spends a stat point the moment its + is clicked, and keeps it. Here the
  // spending waits: Confirm saves it, Undo puts the stats back as they were saved.
  // Racing (Enter) with changes pending confirms them; leaving (Esc) drops them.
  const AI = ['aitssp', 'aiaccsp', 'aigripsp', 'aistusp', 'aistrsp', 'aiendsp'];
  const stat = {
    mad: null, base: null, saves: -1, pending: false,
    // a point spent on stat k (the jar's order: speed, acceleration, control, stunts, strength,
    // endurance) since the last save, that the - can give back: the car's, or a bonus car's special
    spent(k) {
      if (!stat.base || !stat.mad) return false;
      const b = JSON.parse(stat.base), a = xt.sc[0];
      if (xt.nclicked) return xt.specialstats[a][xt.statsalc[a][k]][k] > b.special[a][xt.statsalc[a][k]][k];
      return stat.mad[AI[k]][a] > b.ai[k][a];
    },
    minus(k) {
      if (!stat.spent(k)) return false;
      const a = xt.sc[0];
      if (xt.nclicked) { xt.specialstats[a][xt.statsalc[a][k]][k]--; xt.carpoints++; } else { stat.mad[AI[k]][a]--; xt.statpoints[a]++; }
      for (let i = 0; i < 7; i++) { xt.colorcode[i] = 0; xt.stopflashing[i] = false; }   // what the jar's + resets
      stat.pending = statsOf(xt, stat.mad) !== stat.base;
      return true;
    },
    confirm() { careerStore.save(); stat.base = statsOf(xt, stat.mad); stat.saves = careerStore.saves(); stat.pending = false; },
    undo() { restoreStats(xt, stat.mad, stat.base); stat.pending = false; },
  };
  const statUi = document.createElement('div');
  // beside the jar's STAT POINTS line (442, 375) and under its CHANGE STATS button, in its 870x480
  statUi.style.cssText = 'position:absolute;left:0;top:0;width:870px;height:480px;z-index:4;display:none;pointer-events:none;'
    + 'font:bold 13px Arial,sans-serif;color:#fff;text-shadow:0 0 4px #000;';
  const statBtn = 'cursor:pointer;border:1px solid #fff;font:bold 13px Arial,sans-serif;padding:4px 10px;color:#fff;';
  // New career in the style of the jar's RESET CAR / CHANGE STATS (xtGraphics carselect: the
  // pointed white box 198 x 50, Adventure bold 20), in the right column under BONUS CARS: the
  // left column's next slot is where the jar draws its BACK arrow (30, 290) for most cars
  // the jar's + boxes (352..378 / 762..788 x 390 + 30 per row, grey), with a - beside each
  const minusBtn = 'position:absolute;width:27px;height:26px;pointer-events:auto;cursor:pointer;border:0;padding:0;'
    + 'border-radius:4px;background:rgb(150,150,150);color:#000;font:bold 25px Arial,sans-serif;line-height:22px;text-shadow:none;';
  const jarBtn = 'position:absolute;left:652px;width:198px;height:50px;pointer-events:auto;cursor:pointer;border:0;padding:0;'
    + 'clip-path:polygon(0 50%,8px 0,190px 0,100% 50%,190px 100%,8px 100%);background:rgba(255,255,255,.78);'
    + "color:#000;font:bold 20px Adventure,Arial,sans-serif;text-shadow:none;";
  statUi.innerHTML = `
    <div style="position:absolute;left:720px;top:326px;display:flex;flex-direction:column;gap:4px;pointer-events:auto">
      <div data-s="note" style="color:#ffd24a;font-size:11px">${tr('Stat changes not saved')}</div>
      <div style="display:flex;gap:5px">
        <button data-s="confirm" style="${statBtn}background:rgba(0,125,0,.9)">${tr('Confirm')}</button>
        <button data-s="undo" style="${statBtn}background:rgba(125,0,0,.9)">${tr('Undo')}</button>
      </div>
    </div>
    <button data-s="reset" style="${jarBtn}">${tr('New career').toUpperCase()}</button>
    ${[0, 1, 2, 3, 4, 5].map((k) => `<button data-s="minus${k}" aria-label="-" style="${minusBtn}left:${k < 3 ? 382 : 792}px;top:${390 + (k % 3) * 30}px">−</button>`).join('')}`;
  const $s = (k) => statUi.querySelector(`[data-s="${k}"]`);
  // not a click on the jar's screen; and no focus, or the game's next Enter would press the button
  statUi.addEventListener('mousedown', (e) => { e.stopPropagation(); e.preventDefault(); });
  for (const b of statUi.querySelectorAll('button')) b.tabIndex = -1;
  $s('confirm').onclick = (e) => { e.currentTarget.blur(); stat.confirm(); };
  $s('undo').onclick = (e) => { e.currentTarget.blur(); stat.undo(); };
  $s('reset').onmouseenter = (e) => { e.currentTarget.style.background = '#fff'; };   // the jar's hover: opaque
  $s('reset').onmouseleave = (e) => { e.currentTarget.style.background = 'rgba(255,255,255,.78)'; };
  $s('reset').onclick = (e) => {
    e.currentTarget.blur();
    if (confirm(tr('Start a new career? All career progress will be lost.'))) careerStore.reset();
  };
  if (careerStore) host.append(statUi);
  // Held down, a + or - keeps going: after 350 ms, one more every 90 ms (the + is the jar's own
  // click, repeated while the pointer stays on it: statcm + statincrease, what its mouse sets).
  let hold = 0;
  const stopHold = () => { clearTimeout(hold); clearInterval(hold); hold = 0; };
  const holdRepeat = (step) => {
    stopHold();
    hold = setTimeout(() => { hold = setInterval(() => { if (!step()) stopHold(); }, 90); }, 350);
  };
  for (let k = 0; k < 6; k++) {
    $s(`minus${k}`).addEventListener('mousedown', (e) => { if (e.button === 0 && stat.minus(k)) holdRepeat(() => stat.minus(k)); });
  }
  const plusHeld = (e) => {
    if (!careerStore || xt.fase !== 7 || e.button !== 0) return;
    const k = xt.hoverstat?.findIndex(Boolean) ?? -1;
    if (k < 0) return;                                   // the jar takes this first click itself
    holdRepeat(() => {
      if (xt.fase !== 7 || !xt.hoverstat[k]) return false;   // moved off the +
      xt.statcm[k] = true;
      control.statincrease = true;
      return true;
    });
  };
  host.addEventListener('mousedown', plusHeld);
  addEventListener('mouseup', stopHold);
  addEventListener('blur', stopHold);
  const paintStat = () => {
    const show = careerStore && xt.fase === 7;
    statUi.style.display = show ? '' : 'none';
    if (!show) return;
    for (const k of ['note', 'confirm', 'undo']) $s(k).style.visibility = stat.pending ? '' : 'hidden';
    // shown with the jar's own buttons: once the car has been presented (flipo 0, flatrstart 6),
    // not on a locked car nor while it shuffles; a slot lower when its EXTRA STATS (y 112) is up
    const reset = $s('reset');
    const up = xt.flipo === 0 && xt.flatrstart === 6 && !xt.notunlocked && !xt.nclicked && xt.shufflefase < 5 && !xt.showboosts?.some(Boolean);
    reset.style.display = up ? '' : 'none';
    reset.style.top = xt.boncomp[3] > 0 ? '162px' : '110px';
    // a - where a point was spent since the last save, while the jar's + boxes are up
    const boxes = xt.flipo === 0 && xt.flatrstart === 6 && !xt.notunlocked && xt.shufflefase < 5;
    for (let k = 0; k < 6; k++) $s(`minus${k}`).style.display = boxes && stat.spent(k) ? '' : 'none';
  };

  // ---- the car select's group (free play): the game's cars or yours ----------
  // Your Car Maker cars show only in their own view. NFM 2's switch (cargroup.js): its
  // "Car Maker Cars" button among the game's cars, "< Game Cars" and the orange "Car Maker
  // Cars" header among yours -- the base game's sprites. ▴ ▾ do the same (onKey).
  const carUi = document.createElement('div');
  carUi.style.cssText = 'position:absolute;left:0;top:0;width:870px;height:480px;z-index:4;display:none;pointer-events:none';
  const carBtn = document.createElement('button');
  carBtn.style.cssText = 'position:absolute;left:20px;top:128px;height:28px;border:0;padding:0;cursor:pointer;'
    + 'pointer-events:auto;background:none no-repeat;image-rendering:pixelated';
  carBtn.title = tr('▴ ▾ my cars / game cars');
  const carHead = document.createElement('img');
  carHead.alt = '';
  carHead.style.cssText = 'position:absolute;left:0;right:0;margin:auto;top:86px;image-rendering:pixelated';
  carUi.append(carBtn, carHead);
  carUi.addEventListener('mousedown', (e) => e.stopPropagation());   // not a click on the jar's screen
  let sprites = null, hover = false;
  groupSprites().then((s) => { sprites = s; }, (e) => console.warn('car select: no Car Maker cars button', e));
  function setGroup(car) {
    xt.sc[0] = car;
    pick.carGroup = carGroup();
    savePick(pick);
  }
  carBtn.onclick = () => { carBtn.blur(); setGroup(cycleCarGroup(xt.sc[0]).car); };
  carBtn.onmouseenter = () => { hover = true; };
  carBtn.onmouseleave = () => { hover = false; };
  if (!career) host.append(carUi);
  let carPainted = '';
  const paintCars = () => {
    const show = !career && xt.fase === 7 && newCars().length > 0 && sprites !== null && xt.flipo === 0;
    carUi.style.display = show ? '' : 'none';
    const key = `${show}/${carGroup()}/${hover}`;
    if (!show || key === carPainted) return;
    carPainted = key;
    const mine = carGroup() === 'mine';
    const [up, over] = mine ? sprites.toGame : sprites.toMine;
    carBtn.style.backgroundImage = `url(${hover ? over : up})`;
    const w = new Image(); w.src = up;
    w.decode().then(() => { carBtn.style.width = `${w.width}px`; }, () => {});
    carBtn.setAttribute('aria-label', tr(mine ? 'Game cars' : 'My cars'));
    carHead.style.display = mine ? '' : 'none';
    if (mine && carHead.src !== sprites.header) carHead.src = sprites.header;
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
    <div data-k="picks" style="position:absolute;left:0;right:0;top:46px;display:flex;justify-content:center;gap:6px">
      <select data-k="group" style="${sel}width:120px" aria-label="${tr('Stages')}"></select>
      <select data-k="stage" style="${sel}width:300px" aria-label="${tr('Stage')}"></select>
    </div>
    <button data-k="normal" style="${small}position:absolute;left:14px;top:60px">${tr('Back to the normal stage')}</button>
    <div style="position:absolute;left:0;right:0;top:420px;display:flex;justify-content:center;gap:10px">
      <button data-k="prev" style="${btn}">◂</button>
      <button data-k="go" style="${btn}">${tr('Race')}</button>
      <button data-k="next" style="${btn}">▸</button>
      <span data-k="rivals"></span>
    </div>
    <div data-k="hint" style="position:absolute;left:0;right:0;top:458px;text-align:center;font-size:12px;opacity:.85;${shade}">
      ${tr('◂ ▸ stage · ▴ ▾ stages · Enter race · Esc change car')}</div>`;
  const $ = (k) => ui.querySelector(`[data-k="${k}"]`);
  ui.addEventListener('mousedown', (e) => e.stopPropagation());   // not a click on the jar's screen
  $('group').onchange = () => { const g = $('group').value; $('group').blur(); choose(g, listOf(g)[0][0]); };
  $('stage').onchange = () => { const n = +$('stage').value; $('stage').blur(); choose(pick.group, n); };
  $('prev').onclick = () => { control.left = true; };
  $('next').onclick = () => { control.right = true; };
  $('go').onclick = () => { control.enter = true; };
  $('rivals').replaceWith(Object.assign(rivalsButton(() => openRivals()), { style: 'pointer-events:auto' }));
  for (const k of Object.keys(extra)) if ($(k)) $(k).onclick = (e) => { e.currentTarget.blur(); extra[k](); painted = ''; };
  // the career: the jar draws the rest, the bonus stage's way back is the port's
  if (career) ui.replaceChildren($('normal'));
  else $('normal').remove();
  host.append(ui);
  let painted = '';
  function paint(madness) {
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
    $('normal').style.display = xt.bonstage ? '' : 'none';
  }

  // ---- the loop: one jar frame per menu tick --------------------------------
  // The jar's canvas screens -- the car select, scouting -- draw on the Canvas2D
  // `menu`; the stage select on the race surface.
  const MENU_FASES = new Set([-9, 7, 4, 201, 202, 205, 1110]);
  return new Promise((resolve, reject) => {
    let acc = 0, last = performance.now(), raf = 0;
    let preparedBotStage = null, pendingBotStage = null, botLoadError = null;
    const onKey = (e) => {
      if (rivalsOpen) return;             // the Rivals screen has the keys (its own capture listener)
      // ▴ ▾ on the free play car select: the game's cars / yours. On keydown, not through the
      // jar's control flags: a quick tap releases before the next menu tick and was lost.
      if ((e.key === 'ArrowUp' || e.key === 'ArrowDown') && !career && xt.fase === 7 && newCars().length) {
        e.preventDefault();
        e.stopImmediatePropagation();
        if (xt.flipo === 0 && !e.repeat) setGroup(cycleCarGroup(xt.sc[0]).car);
        return;
      }
      if (e.key !== 'Escape') return;
      if (xt.fase !== 7 && xt.fase !== 1) return;   // scouting: the jar takes Esc as Enter, back to the stage
      e.preventDefault();
      e.stopImmediatePropagation();
      if (xt.fase === 7) { if (stat.pending) stat.undo(); exit(); }   // unconfirmed stat changes are dropped
      else if (career) extra.car();
      else xt.fase = -9;                  // the jar's CHANGE CAR: back to the car select
    };
    addEventListener('keydown', onKey, true);
    const finish = (err) => {
      closeRivals();
      cancelAnimationFrame(raf);
      removeEventListener('keydown', onKey, true);
      ui.remove();
      statUi.remove();
      carUi.remove();
      stopHold();
      host.removeEventListener('mousedown', plusHeld);
      removeEventListener('mouseup', stopHold);
      removeEventListener('blur', stopHold);
      menuCanvas.style.display = 'none';
      Object.assign(xt, own);
      gs.rd = xt.rd = gl;
      err ? reject(err) : resolve(pick);
    };
    const tick = () => {
      if (botLoadError) throw botLoadError;
      if (xt.careermode) {
        const stage = cp.stage;
        if (preparedBotStage !== stage) {
          if (pendingBotStage?.stage !== stage) {
            const pending = { stage };
            pendingBotStage = pending;
            Promise.resolve(prepareStage(true, stage)).then(() => {
              if (pendingBotStage === pending) { preparedBotStage = stage; pendingBotStage = null; }
            }, (error) => {
              if (pendingBotStage === pending) { botLoadError = error; pendingBotStage = null; }
            });
          }
          return;
        }
      } else {
        preparedBotStage = pendingBotStage = null;
      }
      const onMenu = MENU_FASES.has(xt.fase);
      gs.rd = xt.rd = onMenu ? menu : gl;
      if (!onMenu) gl.begin();
      frame.next();
      if (xt.fase === 1 && !onMenu) gl.end();
      menuCanvas.style.display = MENU_FASES.has(xt.fase) ? '' : 'none';
      ui.style.display = xt.fase === 1 && !rivalsOpen ? '' : 'none';   // the Rivals screen covers it
      paintStat();
      paintCars();
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
