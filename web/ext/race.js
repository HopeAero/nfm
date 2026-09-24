// Extended Mode's race inside the base port's shell (web/main.html).
//
// main.js's boot() hands over here for ?ext=classic|career. What is the
// shell's: the page (#stage, #gl, #overlay, #log), the WebGL surface
// (graphics.js, ?res= / ?textres= / ?aa= as the base race reads them), the
// fixed-tick requestAnimationFrame loop and the stats line. What is
// Extended's: every class, and the frame itself -- GameSparker.run() is
// transpiled as a generator whose each next() is one frame of the jar's
// while(true), locals and all, so it is stepped here rather than reimplemented.
//
// Extended's menus are skipped, as the base race skips the base game's: the
// launcher picks. readdata() is where run() hands over its xtGraphics and
// CheckPoints; from there this applies the jar's menu setup and sets fase 6476
// (stage select's START), then fast-forwards the pre-race screens -- stage
// preview, music load, "press start" -- unseen, pressing Enter where the jar
// waits for it, until fase 0. From then on each tick is one next().
//
// ?ext=classic|career  [&stage=N] [&car=M]  [&tickms=53] [&res=2] [&textres=1] [&aa=0|1]

import { detectFpath, readBytes } from '../vfs.js';
import { Panel, System, knownFiles, preload } from './jawt.js';
import { MUSIC_FILES } from './musicfiles.js';
import { JGraphics, JGraphics2D } from './jgraphics.js';
import { GameSparker } from './GameSparker.js';
import { RaceTick } from './racetick.js';
import { Madness } from './Madness.js';
import { random, setDrawPhase } from '../java.js';

const W = 870, H = 480;   // Extended's game space (the base game's is 800x450)
const BOTS = [5, 9, 10, 11, 13, 14, 18, 20, 21].map((n) => `data/Files/Bots/stage${n}.radq`);
const ARCHIVES = ['data/models.radq', 'data/images.radq', 'data/Files/tracks.radq', 'data/Files/careertracks.radq',
  'data/Files/classictracks.radq', 'data/Files/matchtracks.radq', ...BOTS];
const FONTS = [['Adventure', 'Adventure.ttf'], ['fifawelcome1.3', 'fifawelcome1.3.ttf']];

// Java 1.0 key codes, as GameSparker.keyDown expects them.
const KEYS = { ArrowUp: 1004, ArrowDown: 1005, ArrowLeft: 1006, ArrowRight: 1007, Enter: 10, Escape: 27, Backspace: 8, Tab: 9,
  Home: 1000, End: 1001, PageUp: 1002, PageDown: 1003, F1: 1008, F2: 1009, F3: 1010, F4: 1011, F5: 1012, F6: 1013,
  F7: 1014, F8: 1015, F9: 1016, F10: 1017, F11: 1018, F12: 1019 };
const javaKey = (e) => KEYS[e.key] ?? (e.key.length === 1 ? e.key.charCodeAt(0) : 0);

export async function bootExtended(params, log, onExit) {
  const base = await detectFpath(params.get('path'));
  // the launcher reloads itself; main.html on its own goes back to the launcher
  const exit = onExit || (() => { location.href = `${base}index.html`; });
  const mode = params.get('ext') === 'career' ? 'career' : 'classic';

  // ---- the page: Extended's 870x480 in the shell's box ----------------------
  const stage = document.getElementById('stage');
  const glCanvas = document.getElementById('gl');
  const textCanvas = document.getElementById('overlay');
  stage.style.width = `${W}px`;
  stage.style.height = `${H}px`;
  for (const c of [glCanvas, textCanvas]) { c.style.width = `${W}px`; c.style.height = `${H}px`; }
  const fit = () => { stage.style.transform = `scale(${Math.min(innerWidth / W, innerHeight / H)})`; };
  addEventListener('resize', fit);   // after main.html's own 800x450 fit, so this one wins
  fit();
  const res = Math.max(1, Math.min(4, parseFloat(params.get('res') || '2')));
  const textRes = Math.max(1, Math.min(4, parseFloat(params.get('textres') || '1')));
  const AA = params.get('aa') !== null ? params.get('aa') === '1' : res <= 1;
  // ?res= means what it means on the base race: 800*res pixels across (1600 at
  // the default 2). Extended's wider game space keeps its own aspect under it.
  const px = (r) => [Math.round(800 * r), Math.round(800 * r * H / W)];
  [glCanvas.width, glCanvas.height] = px(res);
  [textCanvas.width, textCanvas.height] = px(textRes);
  const rd = new JGraphics2D(glCanvas, textCanvas, W, H, { antialias: AA, fill: params.get('fill') || 'trap' });

  // ---- assets: synchronous for the Java, so fetched first -------------------
  log(`assets at ${base} -- loading Extended's archives...`);
  await preload(ARCHIVES, (p) => readBytes('ext/' + p));
  for (const [family, file] of FONTS) document.fonts.add(await new FontFace(family, `url(${base}ext/fonts/${file})`).load());
  knownFiles(MUSIC_FILES);
  System.live = true;
  Panel.graphicsFor = (c, w, h) => new JGraphics(c, w, h);

  // ---- the game -------------------------------------------------------------
  const gs = new GameSparker();
  gs.rd = gs.sg = rd;               // what init() would take from its offscreen image
  gs.offImage = Panel.offscreen(W, H);
  gs.exwist = false;
  // In the jar rd draws INTO offImage and repaint() shows it; run() repaints
  // every frame and a few screens read offImage back (blendude at starcnt 36,
  // the pause screen's fleximage). Here the frame lives in the WebGL canvas, so
  // offImage is only made current when something reads it: flush the batch so
  // far, copy both layers, and clear the GL buffer so the frame's own end()
  // does not paint that part twice (translucent faces would double up).
  let offStale = false;
  gs.repaint = () => { offStale = true; };
  gs.offImage.beforeRead = () => {
    if (!offStale) return;
    offStale = false;
    rd.end();
    const ctx = gs.offImage.canvas.getContext('2d');
    ctx.drawImage(glCanvas, 0, 0, W, H);
    ctx.drawImage(textCanvas, 0, 0, W, H);
    rd.gl.clearColor(0, 0, 0, 1);
    rd.gl.clear(rd.gl.COLOR_BUFFER_BIT);
  };
  let xt = null, checkpoints = null;
  gs.readdata = function (x, madness, cp) {
    GameSparker.prototype.readdata.call(this, x, madness, cp);
    xt = x; checkpoints = cp;
    // the debug setup the jar captures use (diffrun.debug): every stage open, no beta wall
    xt.unlocked[0] = xt.realunlocked[0] = 27;
    xt.unlocked[1] = xt.realunlocked[1] = 30;
    xt.betalimit = 100;
    xt.statpoints.fill(999);
    xt.carpoints = 999;
    // what the menu does on Career / Classic Mode (xtGraphics.java:15198), then the
    // car select's Enter (xtGraphics.java:17538): classic races Extended's cars 23-38
    if (params.has('car')) xt.lastcar = +params.get('car');
    xt.laststage = params.has('stage') ? +params.get('stage') : (xt.laststage || cp.stage);
    xt.careermode = mode === 'career';
    xt.classicmode = !xt.careermode;
    if (xt.classicmode && xt.lastcar < 23) xt.lastcar = 38;
    xt.sc[0] = xt.lastcar;
    xt.justcs = -1;
    xt.resetfase = false;
    xt.flipo = 0;
    cp.stage = xt.laststage;
    xt.lastload = -11;
    xt.m.crs = false;
    xt.hardstage = false;
    xt.fase = 6476;
    // Extended's own menu is not the way out: back to the launcher, as the base race returns
    xt.maini = exit;
  };
  // ponytail: no saving yet (writedata needs ZipOutputStream); localStorage when careers matter
  gs.writedata = () => {};
  // run()'s locals are what the race tick works on; loadstage is handed all of
  // them but Bots, which the first drive() of the race is.
  const w = {};
  gs.loadstage = function (aconto, aconto1, medium, trackers, cp, xtg, amadness, record, contva) {
    Object.assign(w, { aconto2: aconto, aconto: aconto1, medium, trackers, checkpoints: cp, xtgraphics: xtg, amadness, record, contva });
    return GameSparker.prototype.loadstage.call(this, aconto, aconto1, medium, trackers, cp, xtg, amadness, record, contva);
  };
  const drive = Madness.prototype.drive;
  Madness.prototype.drive = function (u, conto, trackers, cp, contva, bots) {
    w.bots = bots;
    Madness.prototype.drive = drive;
    return drive.call(this, u, conto, trackers, cp, contva, bots);
  };
  window.gs = gs;                  // for the console

  const frame = gs.run();          // the jar's loop; each next() is one frame

  // ---- to the start line, unseen --------------------------------------------
  // Stage preview (fase 1) and "press start" (fase 6) wait for Enter; fase 176
  // counts down run()'s music wait. Nothing here is presented: rd.end() is not
  // called until the race. The next() that reaches fase 0 runs one jar race
  // frame, which is where drive() hands over Bots.
  log(`loading ${mode} stage ${params.get('stage') || "(the jar's pick)"}...`);
  for (let n = 0; !(xt && xt.fase === 0 && w.bots); n++) {
    if (n > 5000) throw new Error(`Extended never reached the race (fase ${xt?.fase})`);
    if (xt && (xt.fase === 1 || xt.fase === 6)) gs.u[0].enter = true;
    rd.begin();
    frame.next();
    if (n % 20 === 19) await new Promise((r) => setTimeout(r, 0));   // let the page breathe
  }
  const { medium } = w;
  const race = new RaceTick(gs, w);
  log(`stage ${checkpoints.stage}: ${checkpoints.name}`);
  window.xt = xt; window.checkpoints = checkpoints;
  window.ext = { w, race };        // for the console

  // ---- input ------------------------------------------------------------------
  // GameSparker.keyDown is Extended's own map (arrows, handbrake, V for the view, ...).
  addEventListener('keydown', (e) => { const k = javaKey(e); if (k) { gs.keyDown({}, k); e.preventDefault(); } });
  addEventListener('keyup', (e) => { const k = javaKey(e); if (k) { gs.keyUp({}, k); e.preventDefault(); } });
  const at = (e) => { const r = glCanvas.getBoundingClientRect(); return [Math.round((e.clientX - r.left) * W / r.width), Math.round((e.clientY - r.top) * H / r.height)]; };
  stage.addEventListener('mousedown', (e) => gs.mouseDown({}, ...at(e)));
  stage.addEventListener('mousemove', (e) => gs.mouseMove({}, ...at(e)));

  // ---- the race loop: the base race's (web/main.js frameBody) -------------------
  // Fixed 53 ms tick (the jar's budget: 530 ms per 10 frames); physics never at
  // display rate. A tick is rebuildNewCars() + simulate(); the frame draws ONCE,
  // after its ticks, from positions and camera blended between the last two tick
  // states (t = acc / TICK_MS). That draw is authoritative when a tick ran --
  // effects advance, ContO.dist is produced for the next tick's sort -- and a
  // redraw otherwise: medium.interpolating holds every draw-time advance still
  // and Medium.random() replays the tick draw's sequence (ContO/Plane/Medium).
  // The HUD simulate() emitted is replayed on top; its text stays on the overlay.
  // Outside the race (the jar's pause, finish and replay screens) the jar's own
  // frame runs, one per tick.
  const TICK_MS = parseFloat(params.get('tickms') || '53');
  const MAX_CATCHUP = 3;
  const INTERPOLATE = params.get('interp') !== '0';
  const SHOW_STATS = params.get('stats') === '1';
  const SPIKE_MS = parseFloat(params.get('spike') || '0');   // ?spike=MS logs slow frames
  window.spikes = [];

  // What is blended (main.js's FIELDS / CAM) and what the draw produces that must
  // survive a redraw's restore (dist; the draw bank of Medium's PRNG).
  const FIELDS = ['x', 'y', 'z', 'xz', 'xy', 'zy'];
  const CAM = ['x', 'y', 'z', 'xz', 'zy'];
  const MED_STATE = ['dcntrn', 'dtrn'];
  const snapPrev = { obj: [], cam: {} }, snapCurr = { obj: [], cam: {} };
  const capture = (into) => {
    const a2 = w.aconto2;
    for (let i = 0; i < gs.nob; i++) {
      const o = a2[i];
      if (!o) continue;
      const d = into.obj[i] || (into.obj[i] = {});
      d.o = o;
      for (const f of FIELDS) d[f] = o[f];
      d.dist = o.dist;
    }
    for (const f of CAM) into.cam[f] = medium[f];
    for (const f of MED_STATE) into.cam[f] = medium[f];
    if (!into.rand) into.rand = new Int32Array(3);
    into.rand.set(medium.drand);
    into.diup = medium.ddiup.slice();
  };
  const blend = (a, b, t, isAngle) => {
    if (!isAngle) return a + (b - a) * t;
    let d = b - a;
    while (d > 180) d -= 360;
    while (d < -180) d += 360;
    return a + d * t;
  };
  const applyBlend = (t) => {
    const a2 = w.aconto2;
    for (let i = 0; i < gs.nob; i++) {
      const o = a2[i], p = snapPrev.obj[i], c = snapCurr.obj[i];
      if (!o || !p || !c || p.o !== o || c.o !== o) continue;   // a car rebuilt this tick is drawn where it is
      o.x = Math.round(blend(p.x, c.x, t, false));
      o.y = Math.round(blend(p.y, c.y, t, false));
      o.z = Math.round(blend(p.z, c.z, t, false));
      o.xz = blend(p.xz, c.xz, t, true);
      o.xy = blend(p.xy, c.xy, t, true);
      o.zy = blend(p.zy, c.zy, t, true);
    }
    medium.x = Math.round(blend(snapPrev.cam.x, snapCurr.cam.x, t, false));
    medium.y = Math.round(blend(snapPrev.cam.y, snapCurr.cam.y, t, false));
    medium.z = Math.round(blend(snapPrev.cam.z, snapCurr.cam.z, t, false));
    medium.xz = blend(snapPrev.cam.xz, snapCurr.cam.xz, t, true);
    medium.zy = blend(snapPrev.cam.zy, snapCurr.cam.zy, t, true);
  };
  const restoreCurr = () => {
    const a2 = w.aconto2;
    for (let i = 0; i < gs.nob; i++) {
      const o = a2[i], c = snapCurr.obj[i];
      if (!o || !c || c.o !== o) continue;
      for (const f of FIELDS) o[f] = c[f];
      o.dist = c.dist;
    }
    for (const f of CAM) medium[f] = snapCurr.cam[f];
    for (const f of MED_STATE) medium[f] = snapCurr.cam[f];
    medium.drand.set(snapCurr.rand);
    for (let i = 0; i < 3; i++) medium.ddiup[i] = snapCurr.diup[i];
    restoreCarFaces();
  };
  // Also a draw output the simulation reads, which the base port's Mad does not
  // have: Plane.d sets a master face's vertex count n from its depth av (8 far,
  // 16 near), and Extended's Madness deforms `p[j].n` vertices on a hit. A redraw
  // from a blended camera must not leave either for the next tick; the cars'
  // faces are the ones a hit reaches.
  let carFaces = [], carAv = new Float64Array(0), carN = new Int32Array(0);
  const saveCarFaces = () => {
    carFaces = [];
    for (let i = 0; i < xt.nplayers; i++) { const o = w.aconto2[i]; for (let j = 0; j < o.npl; j++) carFaces.push(o.p[j]); }
    if (carAv.length < carFaces.length) { carAv = new Float64Array(carFaces.length); carN = new Int32Array(carFaces.length); }
    for (let k = 0; k < carFaces.length; k++) { carAv[k] = carFaces[k].av; carN[k] = carFaces[k].n; }
  };
  const restoreCarFaces = () => {
    for (let k = 0; k < carFaces.length; k++) { carFaces[k].av = carAv[k]; carFaces[k].n = carN[k]; }
  };
  const recaptureDrawOutputs = () => {
    const a2 = w.aconto2;
    for (let i = 0; i < gs.nob; i++) {
      const o = a2[i], c = snapCurr.obj[i];
      if (o && c && c.o === o) c.dist = o.dist;
    }
    saveCarFaces();
    for (const f of MED_STATE) snapCurr.cam[f] = medium[f];
    snapCurr.rand.set(medium.drand);
    snapCurr.diup = medium.ddiup.slice();
  };
  // The scene, on the draw bank of the random streams (the base's GameSparker.draw).
  const drawScene = () => {
    setDrawPhase(true);
    try { race.draw(rd); } finally { setDrawPhase(false); }
  };

  capture(snapPrev);
  capture(snapCurr);
  let hudVerts = null;
  let acc = 0, last = performance.now(), frames = 0, ticks = 0, lastFpsAt = last, simMs = 0, drawMs = 0, worst = 0, over = 0;

  const loop = (now) => {
    requestAnimationFrame(loop);
    const w0 = performance.now();
    acc += now - last;
    last = now;
    if (acc > TICK_MS * MAX_CATCHUP) acc = TICK_MS * MAX_CATCHUP;

    // the jar's own screens (pause, finish, replays): its frame, once per tick
    if (xt.fase !== 0) {
      let ran = false;
      while (acc >= TICK_MS) { acc -= TICK_MS; rd.begin(); frame.next(); ran = true; }
      if (ran) rd.end();
      if (xt.fase === 0) { capture(snapPrev); capture(snapCurr); }   // back in the race: blend from here
      return;
    }

    let stepped = false, sim = 0, drew = 0;
    while (acc >= TICK_MS) {
      capture(snapPrev);
      rd.begin();
      const t0 = performance.now();
      // Without interpolation the last tick's draw IS the frame (catch-up ticks never draw).
      if (!INTERPOLATE && acc - TICK_MS < TICK_MS) drawScene();
      const t1 = performance.now();
      race.rebuildNewCars();
      const hudStart = rd.vertexCount;
      race.simulate();
      hudVerts = rd.snapshotFrom(hudStart);
      sim += performance.now() - t1;
      drew += t1 - t0;
      acc -= TICK_MS;
      ticks++;
      stepped = true;
      if (xt.fase !== 0) break;      // paused, finished: the jar's screens take the next frame
    }
    if (stepped) capture(snapCurr);

    if (INTERPOLATE && xt.fase === 0) {
      const t1 = performance.now();
      applyBlend(Math.min(1, acc / TICK_MS));
      const redraw = !stepped;       // a tick ran: this is its one, authoritative, draw
      medium.interpolating = redraw;
      rd.begin(true);
      drawScene();
      rd.replay(hudVerts);
      medium.interpolating = false;
      if (!redraw) recaptureDrawOutputs();
      restoreCurr();
      drew += performance.now() - t1;
    }
    if (!stepped && !INTERPOLATE) return;
    rd.end();

    const work = performance.now() - w0;
    simMs += sim; drawMs += drew;
    if (work > worst) worst = work;
    if (work > 16.7) over++;
    if (SPIKE_MS && work > SPIKE_MS) {
      const e = { at: Math.round(now), work: +work.toFixed(1), stepped, sim: +sim.toFixed(1), draw: +drew.toFixed(1), verts: rd.inputVerts };
      window.spikes.push(e);
      console.log('spike', JSON.stringify(e));
    }
    if (++frames >= 5 && now - lastFpsAt >= 500) {
      const dt = now - lastFpsAt;
      const buf = `${rd.gl.drawingBufferWidth}x${rd.gl.drawingBufferHeight}`;
      let line = `${(frames * 1000 / dt).toFixed(0)} fps  ${(ticks * 1000 / dt).toFixed(1)} tick/s  ${rd.inputVerts}/${rd.vertexCount} verts`
        + `  fan=${rd.fanPolys} concave=${rd.concavePolys}/${rd.concaveVerts}v  ${buf}  view=${gs.view}`;
      if (SHOW_STATS) {
        line += `\n  sim ${(simMs / Math.max(1, ticks)).toFixed(1)}ms/tick  draw ${(drawMs / Math.max(1, frames)).toFixed(1)}ms/frame`
          + `  worst ${worst.toFixed(1)}ms  over 16.7ms: ${over}/${frames}  [interp=${INTERPOLATE ? 1 : 0}]`;
      }
      log(line);
      frames = 0; ticks = 0; lastFpsAt = now; simMs = 0; drawMs = 0; worst = 0; over = 0;
    }
  };

  // ?selftest=N: N ticks flat out and turning, with two redraws between ticks unless
  // interp=0, then a hash of every car and of the random streams. Equal hashes with
  // and without interp mean the redraws leave the simulation alone.
  const selftest = +params.get('selftest') || 0;
  if (selftest) {
    gs.u[0].up = true;
    for (let k = 0; k < selftest; k++) {
      gs.u[0].left = k % 60 < 20;
      gs.u[0].right = k % 60 >= 40;
      capture(snapPrev);
      rd.begin();
      race.rebuildNewCars();
      race.simulate();
      capture(snapCurr);
      // the tick's authoritative draw, then (interp) two redraws of it
      medium.interpolating = false;
      drawScene();
      recaptureDrawOutputs();
      if (INTERPOLATE) for (const t of [0.33, 0.66]) {
        applyBlend(t);
        medium.interpolating = true;
        rd.begin(true);
        drawScene();
        medium.interpolating = false;
        restoreCurr();
      }
    }
    const state = [];
    for (let i = 0; i < xt.nplayers; i++) {
      const o = w.aconto2[i];
      state.push(o.x, o.y, o.z, o.xz, o.xy, o.zy, o.dist);
    }
    // ...and the effect state drawing advances, so a redraw that steps an effect shows
    const labels = state.map((_, k) => `car${(k / 7) | 0}.pos${k % 7}`);
    const put = (label, ...v) => { for (let k = 0; k < v.length; k++) { state.push(v[k]); labels.push(v.length > 1 ? `${label}[${k}]` : label); } };
    for (let i = 0; i < xt.nplayers; i++) {
      const o = w.aconto2[i];
      put(`car${i}.fcnt`, o.fcnt); put(`car${i}.fix`, +o.fix); put(`car${i}.stg`, ...o.stg); put(`car${i}.dov`, ...o.dov);
      if (o.elc) put(`car${i}.elc`, ...o.elc);
      let emb = 0, chip = 0;
      for (let j = 0; j < o.npl; j++) { emb += o.p[j].embos; chip += o.p[j].chip; }
      put(`car${i}.embos`, emb); put(`car${i}.chip`, chip);
    }
    window.selfLabels = labels;
    state.push(medium.lightn, medium.lilo, medium.makefase, medium.effecttime, medium.switchfase, +medium.cpflik);
    state.push(random(), medium.trn, medium.cntrn, ...medium.rand);
    window.selfState = state;
    const text = state.join(',');
    let h = 0x811c9dc5;
    for (let i = 0; i < text.length; i++) h = Math.imul(h ^ text.charCodeAt(i), 0x01000193);
    log(`selftest ${selftest} ticks interp=${INTERPOLATE ? 1 : 0}: ${(h >>> 0).toString(16)}  car0 ${w.aconto2[0].x},${w.aconto2[0].z}`);
    return;
  }
  requestAnimationFrame(loop);
}
