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
import { makeInterp } from './interp.js';
import { random } from '../java.js';

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
  // run()'s placed objects (aconto2) are a local; loadstage gets them first
  let placed = null, medium = null;
  gs.loadstage = function (aconto, ...rest) {
    placed = aconto; medium = rest[1];
    return GameSparker.prototype.loadstage.call(this, aconto, ...rest);
  };
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

  // ---- the race loop: the base race's fixed tick ------------------------------
  // 53 ms is the jar's own budget (GameSparker.run: 530 ms per 10 frames on a
  // modern JVM). Between ticks the scene is redrawn at display rate from
  // blended positions (interp.js); ?interp=0 draws only on a tick, as the jar.
  const TICK_MS = parseFloat(params.get('tickms') || '53');
  const INTERPOLATE = params.get('interp') !== '0';
  const interp = makeInterp({ rd, gs, xt, medium, get placed() { return placed; } });
  const MAX_CATCHUP = 3;
  const SHOW_STATS = params.get('stats') === '1';
  let acc = 0, last = performance.now(), frames = 0, ticks = 0, lastFpsAt = last, tickMs = 0, redrawMs = 0, tickAt = last;
  // ?stats=1: where a frame's time goes, split by frames with a tick and without
  const st = { tickFrames: 0, tickWork: 0, plainFrames: 0, plainWork: 0, worst: 0, over: 0 };
  const loop = (now) => {
    requestAnimationFrame(loop);
    const w0 = performance.now();
    acc = Math.min(acc + (now - last), TICK_MS * MAX_CATCHUP);
    last = now;
    let stepped = false, ticked = false;
    while (acc >= TICK_MS) {
      acc -= TICK_MS;
      rd.begin();                  // a catch-up tick's picture is replaced by the next one's
      interp.beforeTick();
      const t0 = performance.now();
      frame.next();
      tickMs += performance.now() - t0;
      interp.afterTick();
      ticks++;
      stepped = ticked = true;
    }
    // A tick's own picture is the jar's frame, drawn from the state its simulation
    // started from: exactly t = 0 of the blend towards the state it ended in. So a
    // frame with a tick shows it as is, and later frames redraw at the time since
    // that tick -- one scene draw per frame, as the base race manages, instead of
    // the jar's draw plus a redraw on top of it.
    if (ticked) tickAt = now;
    else if (INTERPOLATE && xt.fase === 0) {    // racing only: the jar's other screens draw once per frame
      const t0 = performance.now();
      if (interp.redraw(Math.min(0.999, (now - tickAt) / TICK_MS))) stepped = true;
      redrawMs += performance.now() - t0;
    }
    if (!stepped) return;
    rd.end();
    const work = performance.now() - w0;
    if (ticked) { st.tickFrames++; st.tickWork += work; } else { st.plainFrames++; st.plainWork += work; }
    if (work > st.worst) st.worst = work;
    if (work > 16.7) st.over++;
    if (++frames >= 5 && now - lastFpsAt >= 500) {
      const dt = now - lastFpsAt;
      const buf = `${rd.gl.drawingBufferWidth}x${rd.gl.drawingBufferHeight}`;
      let line = `${(frames * 1000 / dt).toFixed(0)} fps  ${(ticks * 1000 / dt).toFixed(1)} tick/s  ${rd.inputVerts}/${rd.vertexCount} verts`
        + `  fan=${rd.fanPolys} concave=${rd.concavePolys}/${rd.concaveVerts}v  ${buf}  fase=${xt.fase}`;
      if (SHOW_STATS) {
        line += `
  tick ${(tickMs / Math.max(1, ticks)).toFixed(1)}ms (the jar's frame: draw+sim)`
          + `  redraw ${(redrawMs / Math.max(1, frames)).toFixed(1)}ms = draw ${(interp.prof.draw / Math.max(1, frames)).toFixed(1)} + restore ${(interp.prof.restore / Math.max(1, frames)).toFixed(1)}`
          + `
  frame work: with a tick ${(st.tickWork / Math.max(1, st.tickFrames)).toFixed(1)}ms, without ${(st.plainWork / Math.max(1, st.plainFrames)).toFixed(1)}ms`
          + `, worst ${st.worst.toFixed(1)}ms, over 16.7ms: ${st.over}/${frames}  [interp=${INTERPOLATE ? 1 : 0}]`;
      }
      log(line);
      frames = 0; ticks = 0; lastFpsAt = now; tickMs = 0; redrawMs = 0;
      interp.prof.draw = interp.prof.restore = 0;
      Object.assign(st, { tickFrames: 0, tickWork: 0, plainFrames: 0, plainWork: 0, worst: 0, over: 0 });
    }
  };
  // ?selftest=N: N ticks flat out, with two redraws between ticks unless interp=0,
  // then a hash of every car and of the random stream. Equal hashes with and
  // without interp mean the redraws leave the simulation alone.
  const selftest = +params.get('selftest') || 0;
  if (selftest) {
    gs.u[0].up = true;
    let bad = 0, redraws = 0;
    for (let k = 0; k < selftest; k++) {
      gs.u[0].left = k % 60 < 20;           // turns, so headings blend across frames
      gs.u[0].right = k % 60 >= 40;
      rd.begin();
      interp.beforeTick();
      frame.next();
      interp.afterTick();
      if (INTERPOLATE) for (const t of [0.33, 0.66]) {
        interp.redraw(t);
        redraws++;
        // an object that vanishes from a redraw submits nothing: compare with the tick's scene
        if (interp.sceneVerts > 0 && rd.inputVerts < interp.sceneVerts * 0.8) bad++;
      }
    }
    const state = [];
    for (let i = 0; i < xt.nplayers; i++) {
      const o = placed[i];
      state.push(o.x, o.y, o.z, o.xz, o.xy, o.zy, o.dist);
    }
    state.push(random(), medium.trn, medium.cntrn, ...medium.rand);
    const text = state.join(',');
    let h = 0x811c9dc5;
    for (let i = 0; i < text.length; i++) h = Math.imul(h ^ text.charCodeAt(i), 0x01000193);
    log(`selftest ${selftest} ticks interp=${INTERPOLATE ? 1 : 0}: ${(h >>> 0).toString(16)}  car0 ${placed[0].x},${placed[0].z}`
      + `  redraws missing >20% of the tick's scene: ${bad}/${redraws}`);
    return;
  }
  requestAnimationFrame(loop);
}
