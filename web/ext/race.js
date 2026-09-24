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
  glCanvas.width = Math.round(W * res);
  glCanvas.height = Math.round(H * res);
  textCanvas.width = Math.round(W * textRes);
  textCanvas.height = Math.round(H * textRes);
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
  // run() calls repaint() and then reads the frame back (blendude(offImage) at
  // starcnt 36, the head over night stages). The frame lives in the WebGL
  // canvas here: flush the batch so far and copy both layers into offImage.
  gs.repaint = () => {
    rd.end();
    const ctx = gs.offImage.canvas.getContext('2d');
    ctx.drawImage(glCanvas, 0, 0, W, H);
    ctx.drawImage(textCanvas, 0, 0, W, H);
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
  window.gs = gs;                  // for the console

  const frame = gs.run();          // the jar's loop; each next() is one frame

  // ---- to the start line, unseen --------------------------------------------
  // Stage preview (fase 1) and "press start" (fase 6) wait for Enter; fase 176
  // counts down run()'s music wait. Nothing here is presented: rd.end() is not
  // called until the race.
  log(`loading ${mode} stage ${params.get('stage') || '(the jar\'s pick)'}...`);
  for (let n = 0; !(xt && xt.fase === 0); n++) {
    if (n > 5000) throw new Error(`Extended never reached the race (fase ${xt?.fase})`);
    if (xt && (xt.fase === 1 || xt.fase === 6)) gs.u[0].enter = true;
    rd.begin();
    frame.next();
    if (n % 20 === 19) await new Promise((r) => setTimeout(r, 0));   // let the page breathe
  }
  log(`stage ${checkpoints.stage}: ${checkpoints.name}`);
  window.xt = xt; window.checkpoints = checkpoints;

  // ---- input ------------------------------------------------------------------
  addEventListener('keydown', (e) => { const k = javaKey(e); if (k) { gs.keyDown({}, k); e.preventDefault(); } });
  addEventListener('keyup', (e) => { const k = javaKey(e); if (k) { gs.keyUp({}, k); e.preventDefault(); } });
  const at = (e) => { const r = glCanvas.getBoundingClientRect(); return [Math.round((e.clientX - r.left) * W / r.width), Math.round((e.clientY - r.top) * H / r.height)]; };
  stage.addEventListener('mousedown', (e) => gs.mouseDown({}, ...at(e)));
  stage.addEventListener('mousemove', (e) => gs.mouseMove({}, ...at(e)));

  // ---- the race loop: the base race's fixed tick, drawn at tick rate ----------
  // 53 ms is the jar's own budget (GameSparker.run: 530 ms per 10 frames on a
  // modern JVM). No interpolation yet: it needs hooks in ContO/Medium that the
  // base port wrote by hand and Extended's generated classes do not have.
  const TICK_MS = parseFloat(params.get('tickms') || '53');
  const MAX_CATCHUP = 3;
  let acc = 0, last = performance.now(), frames = 0, ticks = 0, lastFpsAt = last;
  const loop = (now) => {
    requestAnimationFrame(loop);
    acc = Math.min(acc + (now - last), TICK_MS * MAX_CATCHUP);
    last = now;
    let stepped = false;
    while (acc >= TICK_MS) {
      acc -= TICK_MS;
      rd.begin();                  // a catch-up tick's picture is replaced by the next one's
      frame.next();
      ticks++;
      stepped = true;
    }
    if (!stepped) return;
    rd.end();
    if (++frames >= 5 && now - lastFpsAt >= 500) {
      const dt = now - lastFpsAt;
      const buf = `${rd.gl.drawingBufferWidth}x${rd.gl.drawingBufferHeight}`;
      log(`${(frames * 1000 / dt).toFixed(0)} fps  ${(ticks * 1000 / dt).toFixed(1)} tick/s  ${rd.inputVerts}/${rd.vertexCount} verts`
        + `  fan=${rd.fanPolys} concave=${rd.concavePolys}/${rd.concaveVerts}v  ${buf}  fase=${xt.fase}`);
      frames = 0; ticks = 0; lastFpsAt = now;
    }
  };
  requestAnimationFrame(loop);
}
