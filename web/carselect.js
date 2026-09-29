// The original car-select and stage-select screens, run in the launcher
// between "Start" and the race: GameSparker fase 7 (xtGraphics.carselect),
// then fase 3 -> 2 -> 1 (inishstageselect, loadingstage + loadstage, and the
// fly-around under xtGraphics.stageselect).
//
// Both use the launcher's preview world -- the same models, CarDefine,
// XtGraphics and stage loader that draw the menu's previews -- because that is
// the state the Java screens read, already loaded. They draw on a Canvas2D
// surface of their own (canvas-graphics.js): both put images UNDER geometry
// (the backdrop, then the car or the stage), which the race renderer cannot
// order.
//
// They tick every 40ms. GameSparker's loop only runs at the race's 53ms in
// fase 0/-1/-3/7001; everywhere else it tunes its sleep so that 10 frames take
// 400ms (GameSparker.java:1737) -- 25fps. Enter or Space picks, Esc backs out.

import { initPreview, loadStage } from './preview.js';
import { CanvasGraphics } from './canvas-graphics.js';
import { readZip } from './vfs.js';
import { trunc, random } from './java.js';
import { loadCarSelectImages, loadFinishImages, loadStageSelectImages } from './images.js';
import { listAll as listCustomStages } from './stagestore.js';
import { tr } from './i18n.js';
import { rivalsButton } from './rivals.js';
import { lang } from './i18n.js';

const TICK_MS = 40;             // menus: 10 frames per 400ms

/** Put a career (or free play, when null) into the screens' XtGraphics. */
function setCareer(xt, career) {
  xt.gmode = career?.gmode | 0;
  if (career) {
    xt.unlocked.set(career.unlocked);
    xt.scm.set(career.scm);
    xt.justwon1 = !!career.justwon1;
    xt.justwon2 = !!career.justwon2;
  }
}
let images = null;
let imagesLang = null;

function loadImages(xt) {
  if (!images || imagesLang !== lang) {
    imagesLang = lang;
    images = readZip('data/images.zip').then(async (zip) => {
      await loadCarSelectImages(xt, zip);
      await loadStageSelectImages(xt, zip);
      await loadFinishImages(xt, zip);        // contin[], the Continue button
    });
  }
  return images;
}

/**
 * Drive one screen: keys into `control`, `tick()` every 40ms until it returns
 * something other than undefined, which resolves the promise. Esc resolves
 * null. Keys are set on keydown only -- the screens consume each flag
 * themselves (carselect clears control.right after acting on it), and
 * clearing on keyup drops any tap shorter than one tick.
 */
function runScreen(control, tick, gate = {}, canvas = null) {
  control.left = control.right = control.enter = control.handb = false;
  // The pointer, as GameSparker.java keeps it for xtGraphics: xm/ym in the 800x450
  // game space, `moused` while held, and `mouses` 1 on a press -> 2 on the next
  // tick -> 0 (step()), which ctachm reads as "show pressed" then "fire".
  const mouse = { xm: 0, ym: 0, moused: false, mouses: 0,
    step() { if (this.mouses === 2) this.mouses = 0; if (this.mouses === 1) this.mouses = 2; } };
  const at = (e) => {
    const b = canvas.getBoundingClientRect();
    mouse.xm = Math.trunc((e.clientX - b.left) * 800 / b.width);
    mouse.ym = Math.trunc((e.clientY - b.top) * 450 / b.height);
  };
  const onMove = (e) => at(e);
  const onDown = (e) => {
    if (gate.paused) return;
    if (mouse.mouses === 0) { at(e); mouse.mouses = 1; }
    mouse.moused = true;
  };
  const onUp = () => { mouse.moused = false; };
  canvas?.addEventListener('pointermove', onMove);
  canvas?.addEventListener('pointerdown', onDown);
  addEventListener('pointerup', onUp);
  return new Promise((resolve) => {
    let raf = 0;
    let acc = 0;
    let last = performance.now();
    let busy = false;
    let finished = false;
    const done = (result) => {
      if (finished) return;
      finished = true;
      cancelAnimationFrame(raf);
      removeEventListener('keydown', onKey, true);
      removeEventListener('keyup', onKey, true);
      canvas?.removeEventListener('pointermove', onMove);
      canvas?.removeEventListener('pointerdown', onDown);
      removeEventListener('pointerup', onUp);
      resolve(result);
    };
    const onKey = (e) => {
      if (gate.paused) return;           // the Rivals screen is up: its keys
      const down = e.type === 'keydown';
      switch (e.code) {
        case 'ArrowLeft':  if (down) control.left = true; break;
        case 'ArrowRight': if (down) control.right = true; break;
        case 'Enter':      if (down) control.enter = true; break;
        case 'Space':      if (down) control.handb = true; break;
        case 'Escape':     if (down) done(null); break;
        default: return;
      }
      // preventDefault only: the launcher's own key handler already stands
      // down while a screen runs, and its music unlock must still see the
      // key -- inishcarselect plays the menu track (intertrack) here, and
      // after a race reload a key on this screen is the first gesture.
      e.preventDefault();
    };
    addEventListener('keydown', onKey, true);
    addEventListener('keyup', onKey, true);

    const frame = async (now) => {
      if (finished) return;
      raf = requestAnimationFrame(frame);
      if (busy) { last = now; return; }        // a stage is loading
      acc += Math.min(250, now - last);
      last = now;
      while (acc >= TICK_MS && !finished) {
        acc -= TICK_MS;
        busy = true;
        let r;
        try { r = await tick(mouse); } finally { busy = false; }
        if (r !== undefined) { done(r); return; }
        // One tick per animation frame, never a catch-up burst. Each tick draws
        // the whole stage, so on the heaviest previews (NFM 1 9/10, NFM 2 15/16:
        // 2000-2500 polygons) a late frame made the next one run 2-6 ticks back
        // to back and stall 200-300 ms. The Java's loop does not catch up
        // either: it sleeps max(10, budget - elapsed) and simply runs slower.
        acc = Math.min(acc, TICK_MS - 1);
      }
    };
    raf = requestAnimationFrame(frame);
  });
}

/**
 * @param {HTMLCanvasElement} canvas  800x450 game space, any backing size
 * @param {number} slot               car to start on (0..15), free play
 * @param {object} [career]           {gmode, unlocked, scm}: gmode 1/2 is the
 *   NFM 1 / NFM 2 career, which starts on scm[gmode-1] and locks cars by
 *   `unlocked` (xtGraphics.carselect's `k`, already in XtGraphics)
 * @returns {Promise<number|null>}    the chosen car slot, or null on Esc
 */
export async function runCarSelect(canvas, slot, career = null) {
  const { xt, models, mads, gs, medium } = await initPreview();
  await loadImages(xt);
  const rd = new CanvasGraphics(canvas);
  const control = gs.u[0];
  const savedRd = xt.rd;
  xt.rd = rd;
  xt.m = medium;
  setCareer(xt, career);
  xt.multion = 0;
  xt.cfase = 0;
  xt.flipo = 0;
  xt.osc = Math.max(0, Math.min(15, slot | 0));
  xt.inishcarselect(models);
  xt.fase = 7;
  try {
    return await runScreen(control, (mouse) => {
      rd.begin();
      xt.carselect(control, models, mads[0], mouse.xm, mouse.ym, mouse.moused);
      xt.ctachm(mouse.xm, mouse.ym, mouse.mouses, control);   // GameSparker fase 7
      mouse.step();
      if (xt.fase !== 7) return xt.sc[0];
      return undefined;
    }, {}, canvas);
  } finally {
    medium.crs = false;
    xt.rd = savedRd;
  }
}

// The stage select's two choices (xtGraphics.java:1932-2170): the game and
// the stage within it, AWT Choice widgets the applet laid over its canvas at
// y = 62 -- here, HTML selects over this one. The Java's games were NFM 1,
// NFM 2, My Stages, the Top20 lists and Stage Maker (the stages made in the
// Stage Maker); the online ones need the account server and are left out.
// Stage Maker is "Custom" here, and "All" is the port's: every stage in turn.
// "Multiplayer" is stages 28-32, which the Java offered only in the online
// lobby's game list (Lobby.java:369, "NFM Multiplayer"); here they are free play.
const GAMES = ['NFM 1', 'NFM 2', 'Multiplayer', 'Custom', 'All'];
const T = { NFM1: 0, NFM2: 1, MULTI: 2, CUSTOM: 3, ALL: 4 };
const NFM1 = Array.from({ length: 10 }, (_, i) => i + 1);
const NFM2 = Array.from({ length: 17 }, (_, i) => i + 11);
const MULTI = Array.from({ length: 5 }, (_, i) => i + 28);
const groupOf = (st) => (typeof st === 'string' ? T.CUSTOM : st > 27 ? T.MULTI : st > 10 ? T.NFM2 : T.NFM1);

function stageLabel(st, all) {
  if (typeof st === 'string') return all ? `${tr('Custom')} · ${st}` : st;
  const n = st > 27 ? st - 27 : st > 10 ? st - 10 : st;
  return all ? `${st > 27 ? tr('Multiplayer') : st > 10 ? 'NFM 2' : 'NFM 1'} · ${tr(`Stage ${n}`)}` : ` ${tr(`Stage ${n}`)} `;
}

/** The two selects, placed as the applet placed its Choices. */
function makeChoices(host) {
  const box = document.createElement('div');
  box.style.cssText = 'position:absolute;left:0;top:62px;height:22px;z-index:3;';
  const style = 'position:absolute;top:0;height:22px;background:#000;color:rgb(47,179,255);'
    + 'border:1px solid rgb(47,179,255);font:bold 12px Arial,sans-serif;padding:0 2px;';
  const game = document.createElement('select');
  const list = document.createElement('select');
  game.style.cssText = style + 'width:131px;';
  list.style.cssText = style;
  game.setAttribute('aria-label', 'Stages');
  list.setAttribute('aria-label', 'Stage');
  box.append(game, list);
  host.append(box);
  return { box, game, list };
}

/**
 * @param {HTMLCanvasElement} canvas
 * @param {number|string} stage       stage to start on: a number, or a custom stage's name
 * @param {() => Promise<void>} [onRivals]  free play: the RIVALS button opens it (rivals.js)
 * @returns {Promise<number|string|null>}  the chosen stage (a custom one by name), or null on Esc
 */
export async function runStageSelect(canvas, stage, career = null, onRivals = null) {
  const { xt, gs, medium, checkPoints, placed } = await initPreview();
  await loadImages(xt);
  const customs = await listCustomStages().catch(() => []);
  const rd = new CanvasGraphics(canvas);
  const control = gs.u[0];
  const savedRd = xt.rd;
  xt.rd = rd;
  xt.m = medium;
  medium.crs = false;
  setCareer(xt, career);
  // xtGraphics.inishstageselect (xtGraphics.java:1894): past the 27 stages of
  // NFM 1 and 2 it picks one at random -- except, in free play, the port's
  // Multiplayer stages 28-32...
  const multi = xt.gmode === 0 && MULTI.includes(stage);
  let cur = typeof stage === 'string' && customs.includes(stage) ? stage
    : (stage > 27 && !multi) || stage < 1 || typeof stage !== 'number' ? trunc(random() * 27.0) + 1 : stage;
  // ...and a career starts on the stage it has reached. Once finished
  // (unlocked 11 / 17) it keeps the stage, or rolls one if the last race was
  // won. `unlocked[0] != 17` in the NFM 2 branch is the Java's own typo for
  // unlocked[1]; unlocked[0] never reaches 17, so NFM 2 always lands on
  // unlocked[1] + 10. Kept.
  const gmode = xt.gmode;
  if (gmode === 1) {
    if (typeof cur !== 'number') cur = 1;
    if (xt.unlocked[0] !== 11 || xt.justwon1) cur = xt.unlocked[0];
    else if (career?.winner || cur > 11) cur = trunc(random() * 11.0) + 1;
    if (cur === 11) cur = 27;
  }
  if (gmode === 2) {
    if (typeof cur !== 'number') cur = 11;
    if (xt.unlocked[0] !== 17 || xt.justwon2) cur = xt.unlocked[1] + 10;
    else if (career?.winner || cur < 11) cur = trunc(random() * 17.0) + 11;
  }
  let tab = groupOf(cur);
  const members = (t) => (t === T.NFM1 ? NFM1 : t === T.NFM2 ? NFM2 : t === T.MULTI ? MULTI
    : t === T.CUSTOM ? customs : [...NFM1, ...NFM2, ...MULTI, ...customs]);
  // the games in the list: careers have no Multiplayer tab (free play only)
  const games = GAMES.map((g, i) => [g, i]).filter(([, i]) => i !== T.MULTI || gmode === 0);

  const ui = makeChoices(canvas.parentElement);
  const paintChoices = () => {
    ui.game.innerHTML = games.map(([g, i]) => `<option value="${i}">${tr(g)}</option>`).join('');
    ui.game.value = String(tab);
    const items = members(tab);
    ui.list.innerHTML = '';
    items.forEach((st, i) => {
      const o = document.createElement('option');
      o.value = String(i);
      o.textContent = stageLabel(st, tab === T.ALL);
      ui.list.append(o);
    });
    if (!items.length) {
      const o = document.createElement('option');
      o.textContent = tr('No custom stages yet');
      ui.list.append(o);
    }
    const k = items.indexOf(cur);
    if (k >= 0) ui.list.value = String(k);
    // xtGraphics.java:2093: centred as a pair, 6px apart.
    const w = tab === T.CUSTOM || tab === T.ALL ? 338 : 120;
    const x = 400 - trunc((131 + 6 + w) / 2);
    ui.game.style.left = `${x}px`;
    ui.list.style.left = `${x + 137}px`;
    ui.list.style.width = `${w}px`;
  };
  const go = (st) => {
    if (st === cur) return;
    cur = st;
    if (tab !== T.ALL) tab = groupOf(cur);
    xt.fase = 2;
    paintChoices();
  };
  ui.game.onchange = () => {
    tab = +ui.game.value;
    const items = members(tab);
    ui.game.blur();                    // the applet's requestFocus(): keys drive the screen again
    if (tab !== T.ALL && items.length && !items.includes(cur)) go(items[0]);
    else paintChoices();
  };
  ui.list.onchange = () => {
    const st = members(tab)[+ui.list.value];
    ui.list.blur();
    if (st !== undefined) go(st);
  };
  paintChoices();
  if (gmode !== 0) ui.box.hidden = true;
  // free play: RIVALS beside the Java's CONTINUAR; the screen keeps drawing under it
  const gate = { paused: false };
  const rivals = onRivals && rivalsButton(async () => {
    gate.paused = true;
    try { await onRivals(); } finally {
      control.left = control.right = control.enter = control.handb = false;
      gate.paused = false;
    }
  });
  if (rivals) {
    rivals.style.cssText += 'position:absolute;left:470px;top:362px;z-index:3;font-size:13px;padding:1px 12px;';   // CONTINUAR's size
    canvas.parentElement.append(rivals);
  }

  xt.nfmtab = tab;
  xt.removeds = 0;
  xt.cd.staction = 0;
  xt.fase = 2;
  try {
    return await runScreen(control, async (mouse) => {
      // GameSparker fase 4: the locked-stage notice, alone on the screen.
      if (xt.fase === 4) {
        rd.begin();
        xt.cantgo(control);
        return undefined;
      }
      if (xt.fase === 2) {
        rd.begin();
        xt.loadingstage(checkPoints.stage, true);
        try {
          await loadStage(cur);                // loadstage leaves fase = 1
        } catch {
          checkPoints.stage = -3;
        }
        xt.fase = 1;
        return undefined;
      }
      // GameSparker fase 1: the pointer on the arrows and CONTINUAR, before the arrows are read
      xt.ctachm(mouse.xm, mouse.ym, mouse.mouses, control);
      mouse.step();
      // The arrows. The Java walks stages 1..27 itself (stageselect, below);
      // a custom stage (stage -2) and a Multiplayer one (28-32) have no arrows
      // there, so step through the current list here -- and in All, from
      // stage 27 on into them.
      const list = members(tab);
      const k = list.indexOf(cur);
      const custom = typeof cur === 'string';
      const walked = custom || cur > 27;
      const walkHere = gmode === 0 && (walked || (cur === 27 && tab === T.ALL && k < list.length - 1));
      if (walkHere && xt.cd.staction === 0) {
        if (control.right) { control.right = false; if (k < list.length - 1) go(list[k + 1]); }
        if (control.left) {
          control.left = false;
          if (walked && k > 0) go(list[k - 1]);
        }
        if (xt.fase === 2) return undefined;
      }
      // GameSparker fase 1 (GameSparker.java:461): backdrop, the camera
      // circling in, the stage's objects past the cars in depth order, then
      // the screen over it.
      rd.begin();
      xt.trackbg(false);
      if (checkPoints.stage !== -3) {
        medium.aroundtrack(checkPoints);
        const near = [];
        for (let j = xt.nplayers; j < gs.notb; ++j) {
          if (placed[j].dist !== 0) near.push(j);
          else placed[j].d(rd);
        }
        // The Java's O(n^2) rank sort, ties broken by index, kept as-is: it
        // decides the order equal-distance objects are painted in.
        const n12 = near.length;
        const rank = new Int32Array(n12);
        for (let l = 0; l < n12; ++l) {
          for (let m = l + 1; m < n12; ++m) {
            const dl = placed[near[l]].dist, dm = placed[near[m]].dist;
            if (dl !== dm) {
              if (dl < dm) ++rank[l];
              else ++rank[m];
            } else {
              ++rank[l];
            }
          }
        }
        const order = new Int32Array(n12);
        for (let i = 0; i < n12; ++i) order[rank[i]] = near[i];
        for (let i = 0; i < n12; ++i) placed[order[i]].d(rd);
      }
      const before = checkPoints.stage;
      xt.stageselect(checkPoints, control, mouse.xm, mouse.ym, mouse.moused);
      if (xt.fase === 2 && checkPoints.stage !== before) {
        // The Java's own arrows moved to another of the 27.
        const st = checkPoints.stage;
        checkPoints.stage = before;
        cur = null;
        go(st);
      }
      if (xt.cd.staction === 0) {
        // The arrows the Java does not draw: a custom or Multiplayer stage's, and 27's way on.
        if (walked && (k > 0)) rd.drawImage(xt.back[0], 115, 135);
        if (walkHere && k < list.length - 1) rd.drawImage(xt.next[0], 625, 135);
        if (checkPoints.stage === -3 && custom) {
          rd.setFont('Arial', 1, 11);
          xt.ftm = rd.getFontMetrics();
          xt.drawcs(155, 'Please Test Drive this stage in the Stage Maker to make sure it can be loaded!', 255, 138, 0, 3);
        }
        if (tab === T.CUSTOM && !customs.length) {
          rd.setFont('Arial', 1, 12);
          xt.ftm = rd.getFontMetrics();
          xt.drawcs(155, 'No custom stages yet: make one in the Stage Maker.', 255, 138, 0, 3);
        }
      }
      if (xt.fase === 5) return typeof cur === 'string' ? cur : checkPoints.stage;
      return undefined;
    }, gate, canvas);
  } finally {
    ui.box.remove();
    rivals?.remove();
    xt.rd = savedRd;
  }
}
