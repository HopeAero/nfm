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
// ?ext=free|career: the launcher's Free Play / Career Mode, car and stage select first (menus.js)
// ?ext=classic|career  [&stage=N] [&car=M]  [&tickms=53] [&res=2] [&textres=1] [&aa=0|1]

import { detectFpath, readBytes, readText, readZip } from '../vfs.js';
import { OggClip, Panel, System, ZipInputStream, knownFiles, preload } from './jawt.js';
import { MUSIC_FILES } from './musicfiles.js';
import { JGraphics, JGraphics2D } from './jgraphics.js';
import { GameSparker } from './GameSparker.js';
import { RaceTick } from './racetick.js';
import { Bots } from './Bots.js';
import { appendModels, baseGround, baseLook, prepareBaseStage, renumberOldStage, translateStage } from './stagecompat.js';
import { runMenus } from './menus.js';
import { buildTrackGrid } from './trackgrid.js';
import { Bench, countScene, frameCap, installProfile } from './benchtools.js';
import { perfLevel, perfLine } from '../perfline.js';
import { ContO } from './ContO.js';
import { Plane } from './Plane.js';
import { Madness } from './Madness.js';
import { clearCareer, loadCareer, saveCareer } from './career-save.js';
import { installSound } from './sound.js';
import { installMusic } from './radmusic.js';
import { begin as musicBegin, fetchTracked, progressText, status as musicStatus } from './musicload.js';
import { installBaseLoadsnap, installSprites } from './sprites-es.js';
import { installBlendude, installFleximage } from './finish.js';
import { createRaceMenu } from '../race-ui.js';
import { spanishPauseBackground } from '../ui-sprites-es.js';
import { lang } from '../i18n.js';
import { random, setDrawPhase } from '../java.js';
import { listAll, readCar } from '../carstore.js';
import { NEW_BASE, newCars, setNewCars } from './newcars.js';
import { loadNewCars } from './newcars-stats.js';
import { newCarModel } from './newcars-model.js';

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
  const leave = onExit || (() => { location.href = `${base}index.html`; });
  let exit = () => { sound.stopAll(); tracker.stop(); for (const c of OggClip.all) c.pause(); leave(); };
  const mode = ['career', 'free'].includes(params.get('ext')) ? params.get('ext') : 'classic';
  const free = mode === 'free';
  // ?selftest= with ?stage= goes straight to the race, as before the menus: a hash that repeats
  const menus = (free || mode === 'career') && !(params.get('selftest') && params.has('stage'));
  // a real career is saved (career-save.js); developer mode races it with everything open, unsaved
  // The career is the real one -- saved, unlocking stage by stage, stat points earned --
  // in developer mode too; ?extdebug=1 is the jar captures' debug setup instead (every
  // stage open, 999 points, nothing saved).
  const realCareer = mode === 'career' && params.get('extdebug') !== '1';

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
  // the base race's own backing store, 800x450 per res (1600x900 at 2): the game space is
  // scaled into it per axis (graphics.js), 1.8% taller than Extended's 870x480 aspect
  const px = (r) => [Math.round(800 * r), Math.round(450 * r)];
  [glCanvas.width, glCanvas.height] = px(res);
  [textCanvas.width, textCanvas.height] = px(textRes);
  const rd = new JGraphics2D(glCanvas, textCanvas, W, H, { antialias: AA, fill: params.get('fill') || 'trap' });

  // ---- assets: synchronous for the Java, so fetched first -------------------
  log(`assets at ${base} -- loading Extended's archives...`);
  await preload(ARCHIVES, (p) => readBytes('ext/' + p));
  for (const [family, file] of FONTS) document.fonts.add(await new FontFace(family, `url(${base}ext/fonts/${file})`).load());
  knownFiles(MUSIC_FILES);
  // ?nfm2stage=N / ?mystage=NAME: an NFM2 or Stage Maker stage, translated for
  // Extended (stagecompat.js); it stands in for the stage the jar loads
  let baseStage = free ? null : await prepareBaseStage(params);
  // Free play can race any of the base game's 32 stages: their text and models, up front
  let nfm2 = null;
  if (free) {
    const [zip, ...texts] = await Promise.all([readZip('data/models.zip'),
      ...Array.from({ length: 32 }, (_, i) => readText(`stages/${i + 1}.txt`))]);
    const names = texts.map((t, i) => /name\(([^)]*)\)/.exec(t)?.[1] || `Stage ${i + 1}`);
    nfm2 = { zip, texts, names };
  }
  // ---- Extended new cars (newcars.js): Car Maker cars after the 39, Free Play only ----
  // Every car the Car Maker lists (its storage + mycars/), in listing order; each .rad
  // carries its Extended choices (extlines.js). ?newcar=name:donor races one, anywhere but the career.
  let newList = [];
  if (params.has('newcar') && mode !== 'career') {
    const [name, donor] = params.get('newcar').split(':');
    newList = [{ name, donor: donor === undefined ? undefined : +donor }];
  } else if (free) {
    // storage that will not list (private mode, a broken IndexedDB) means no new cars, not no race
    newList = (await listAll().catch((e) => { console.log(`new cars unavailable: ${e?.message || e}`); return []; }))
      .map((name) => ({ name }));
  }
  setNewCars(await loadNewCars(newList, readCar));   // a car that fails is skipped, the race starts
  System.live = true;
  Panel.graphicsFor = (c, w, h) => new JGraphics(c, w, h);

  // ---- sound: the base port's effects and tracker, the career's .ogg ---------
  // Installed before run(): xtGraphics.loaddata takes its AudioClips on the first frames.
  const sfxvol = +(params.get('sfxvol') ?? 100), musicvol = +(params.get('musicvol') ?? 100);
  // the base's race-end sprites, and in Spanish its redrawn lettered bitmaps (selectcar, next, back...)
  await installSprites();
  const sound = installSound(sfxvol);
  const tracker = installMusic(musicvol);
  OggClip.base = base;
  OggClip.load = fetchTracked;      // the career's .ogg, counted with the modules (musicload.js)
  OggClip.volume = musicvol / 100;
  // the gesture that started the race was on the launcher: resume on this page's first key or click
  const unlock = () => { sound.unlock(); tracker.unlock(); OggClip.unlock(); };
  for (const ev of ['keydown', 'pointerdown', 'touchstart']) addEventListener(ev, unlock, true);

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
  // The race's last frame, kept when the race stops (see freeze()): the jar's race-end
  // screens read it back through offImage (fleximage), but by then the WebGL buffer has
  // been presented and cleared (preserveDrawingBuffer is off), so a copy of it is black.
  let frozen = null;
  // The last drawn race frame while the start countdown runs, for the presenter's blend
  // (finish.js installBlendude); copied in the same task as the draw, before it is cleared.
  let startFrame = null;
  const keepStartFrame = () => {
    startFrame ??= Object.assign(document.createElement('canvas'), { width: W, height: H });
    startFrame.getContext('2d').drawImage(glCanvas, 0, 0, W, H);
  };
  gs.repaint = () => { offStale = true; };
  gs.offImage.beforeRead = () => {
    if (!offStale) return;
    offStale = false;
    rd.end();
    const ctx = gs.offImage.canvas.getContext('2d');
    ctx.drawImage(frozen || glCanvas, 0, 0, W, H);
    ctx.drawImage(textCanvas, 0, 0, W, H);
    rd.gl.clearColor(0, 0, 0, 1);
    rd.gl.clear(rd.gl.COLOR_BUFFER_BIT);
  };
  let xt = null, checkpoints = null, careerSave = () => {}, careerSaves = 0;
  gs.readdata = function (x, madness, cp) {
    GameSparker.prototype.readdata.call(this, x, madness, cp);
    xt = x; checkpoints = cp;
    // The presenter's screen shows the race music's real download in place of the jar's
    // hand-written size per stage (sndsize, "N KB").
    const hipnoload = xt.hipnoload, drawcs = xt.drawcs;
    let presenter = false;
    xt.hipnoload = function (...a) { presenter = true; try { return hipnoload.apply(this, a); } finally { presenter = false; } };
    xt.drawcs = function (y, s, ...r) {
      if (presenter && /^-?\d+ KB$/.test(s)) s = progressText(musicStatus());
      return drawcs.call(this, y, s, ...r);
    };
    installFleximage(xt);             // the base port's race-end smear (finish.js)
    installBlendude(xt, () => startFrame);   // the countdown presenter over the real frame (finish.js)
    installBaseLoadsnap(xt);          // HUD bitmaps without the sky-coloured box (sprites-es.js)
    // the whole career, not the jar's beta wall at stage 14 (the user, 2026-09-25)
    xt.betalimit = 100;
    if (realCareer) {
      loadCareer(xt, cp, madness);
      const save = () => { saveCareer(xt, checkpoints, madness); careerSaves++; };
      careerSave = save;
      // the jar saves after a race (fase 10), on a stat transfer and a sold car; here also on the way out
      this.writedata = (x2) => { save(); x2.savefase = 2; };
      const out = exit;
      exit = () => { xt.laststage = checkpoints.stage; save(); out(); };
    } else {
      // the debug setup the jar captures use (diffrun.debug): every stage open, points to spend
      xt.unlocked[0] = xt.realunlocked[0] = 27;
      xt.unlocked[1] = xt.realunlocked[1] = 30;
      xt.statpoints.fill(999);
      xt.carpoints = 999;
    }
    // what the menu does on Career / Classic Mode (xtGraphics.java:15198), then the
    // car select's Enter (xtGraphics.java:17538): classic races Extended's cars 23-38
    if (params.has('car')) xt.lastcar = +params.get('car');
    xt.laststage = params.has('stage') ? +params.get('stage') : baseStage ? 1 : (xt.laststage || cp.stage);
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
    xt.fase = menus ? -9 : 6476;       // free play and the career: the car select first (menus.js)
    // Extended's own menu is not the way out: back to the launcher, as the base race returns
    xt.maini = () => exit();
  };
  // no savedata.radq (writedata needs ZipOutputStream); a real career replaces this in readdata
  gs.writedata = () => {};
  // run()'s locals are what the race tick works on; loadstage is handed all of
  // them but Bots, which the first drive() of the race is.
  const w = {};
  gs.loadstage = function (aconto, aconto1, medium, trackers, cp, xtg, amadness, record, contva) {
    Object.assign(w, { aconto2: aconto, aconto: aconto1, medium, trackers, checkpoints: cp, xtgraphics: xtg, amadness, record, contva });
    record.ghosts = params.get('ghost') !== '0';   // Settings -> Replay recording (ext-patch record-ghosts)
    baseGround(medium, baseStage && (() => this.nob));   // on for an NFM 2 stage, off otherwise
    // the jar's normal mode reads tracks.radq, most of it on an old model list (renumberOldStage)
    const normalMode = !xtg.careermode && !xtg.classicmode;
    if (!baseStage && !normalMode) {
      const r = GameSparker.prototype.loadstage.call(this, aconto, aconto1, medium, trackers, cp, xtg, amadness, record, contva);
      if (params.get('grid') !== '0') buildTrackGrid(trackers);   // ?grid=0: every scan sweeps them all (A/B)
      return r;
    }
    const latin1 = (b) => Array.from(b, (c) => String.fromCharCode(c)).join('');
    const toBytes = (t) => Uint8Array.from(t, (c) => c.charCodeAt(0) & 255);
    // the stage text loadstage should read in place of the jar's stage file
    const replace = baseStage ? () => translateStage(baseStage.text, (i) => aconto1[i].grat)
      : (bytes) => renumberOldStage(latin1(bytes));
    const next = ZipInputStream.prototype.getNextEntry;
    ZipInputStream.prototype.getNextEntry = function () {
      const e = next.call(this);
      if (e && e.getName() === `${cp.stage}.txt`) { this.bytes = toBytes(replace(this.bytes)); this.pos = 0; }
      return e;
    };
    try {
      const r = GameSparker.prototype.loadstage.call(this, aconto, aconto1, medium, trackers, cp, xtg, amadness, record, contva);
      if (baseStage) baseLook(aconto, xtg.nplayers, this.nob, medium, baseStage.n ?? +params.get('nfm2stage'));
      if (params.get('grid') !== '0') buildTrackGrid(trackers);   // ?grid=0: every scan sweeps them all (A/B)
      return r;
    } finally {
      ZipInputStream.prototype.getNextEntry = next;
      if (baseStage?.name) cp.name = baseStage.name;   // a Stage Maker stage is named by its file, as in the base
    }
  };
  gs.loadbase = function (aconto, medium, trackers, xtg) {
    const r = GameSparker.prototype.loadbase.call(this, aconto, medium, trackers, xtg);
    if (baseStage || free) appendModels(aconto, (baseStage || nfm2).zip, medium, trackers, xtg);
    // new cars at NEW_BASE + i (newcars-model.js)
    newCars().forEach((car, i) => { aconto[NEW_BASE + i] = newCarModel(car, medium, trackers, xtg, NEW_BASE + i); });
    return r;
  };
  // run()'s Bots, taken as it is made (its constructor's first `doneload` write). The first
  // drive() hands it over too, but only once the start countdown is over (starcnt 130 -> 0),
  // and waiting for that fast-forwarded the whole fly-in and 3-2-1 unseen: ~130 full frames,
  // seconds with a full grid, and a race that began already counted down.
  Object.defineProperty(Bots.prototype, 'doneload', {
    configurable: true,
    set(v) {
      w.bots = this;
      delete Bots.prototype.doneload;
      Object.defineProperty(this, 'doneload', { value: v, writable: true, enumerable: true, configurable: true });
    },
  });
  window.gs = gs;                  // for the console

  const frame = gs.run();          // the jar's loop; each next() is one frame

  // ---- free play and the career: the car and stage select, shown ------------
  // Until then run() is stepped unseen to readdata (the first frames), which
  // hands over xtGraphics.
  const keyDown = (e) => { const k = javaKey(e); if (k) { gs.keyDown({}, k); e.preventDefault(); } };
  const keyUp = (e) => { const k = javaKey(e); if (k) { gs.keyUp({}, k); e.preventDefault(); } };
  const at = (e) => { const r = glCanvas.getBoundingClientRect(); return [Math.round((e.clientX - r.left) * W / r.width), Math.round((e.clientY - r.top) * H / r.height)]; };
  let menusUp = false;
  // on free play's stage select the jar's buttons are not drawn, so their hit areas must not
  // answer; the career's is the jar's own (menus.js), buttons and all
  stage.addEventListener('mousedown', (e) => { if (!(menusUp && free && xt?.fase === 1)) gs.mouseDown({}, ...at(e)); });
  stage.addEventListener('mousemove', (e) => gs.mouseMove({}, ...at(e)));
  if (menus) {
    for (let n = 0; !xt; n++) {
      if (n > 500) throw new Error('Extended never reached readdata');
      rd.begin();
      frame.next();
    }
    const menuCanvas = document.createElement('canvas');
    [menuCanvas.width, menuCanvas.height] = [W * 2, H * 2];
    menuCanvas.style.cssText = `position:absolute;left:0;top:0;width:${W}px;height:${H}px;z-index:3`;
    stage.append(menuCanvas);
    addEventListener('keydown', keyDown);
    addEventListener('keyup', keyUp);
    menusUp = true;
    log('');
    window.xt = xt; window.checkpoints = checkpoints;   // for the console
    await runMenus({
      mode, gs, frame, xt, cp: checkpoints, gl: rd, menu: new JGraphics(menuCanvas, W, H), menuCanvas, host: stage,
      nfm2Names: nfm2?.names, exit: () => exit(),
      // the car select's Confirm / Undo and New career (menus.js): a real career only
      careerStore: realCareer ? { save: () => careerSave(), saves: () => careerSaves, reset: () => { clearCareer(); location.reload(); } } : null,
      setBaseStage: (n) => { baseStage = n ? { n, name: null, text: nfm2.texts[n - 1], zip: nfm2.zip } : null; },
    });
    menuCanvas.remove();
    menusUp = false;
    // A finished career race goes where the jar goes, back to its car select (its main
    // menu, fase 10, is where the career is entered), with the progress saved: this page
    // again. Quitting from the pause menu still leaves for the launcher.
    if (realCareer) xt.maini = () => { xt.laststage = checkpoints.stage; careerSave(); location.reload(); };
  }

  // ---- to the presenter, unseen -----------------------------------------------
  // The stage preview (fase 1) waits for Enter and is pressed through; loading the
  // stage (fase 2) is not presented: rd.end() is not called, and the stage stays
  // hidden (the overlay is a live Canvas2D and would show the jar's loading draws)
  // while the shell's log line is the loading screen. The fast-forward stops at the
  // presenter's screen (fase 176, the music load, then 6, "press start": hipnoload,
  // with the stage's notes), which the race loop shows and which waits for the
  // player's own Enter, as in the jar; then the fly-in and countdown (starcnt
  // 130 -> 0) are the race's. ?selftest= goes straight through to fase 0.
  const selftestTicks = +params.get('selftest') || 0;
  const reached = () => xt && w.bots && (xt.fase === 0 || (!selftestTicks && (xt.fase === 176 || xt.fase === 6)));
  musicBegin();                      // what loadmusic fetches from here is this race's music
  log(`loading ${mode} stage ${menus ? checkpoints.stage : params.get('stage') || "(the jar's pick)"}...`);
  stage.style.visibility = 'hidden';
  for (let n = 0; !reached(); n++) {
    if (n > 5000) throw new Error(`Extended never reached the race (fase ${xt?.fase})`);
    if (xt && (xt.fase === 1 || (selftestTicks && xt.fase === 6))) gs.u[0].enter = true;
    rd.begin();
    frame.next();
    if (n % 20 === 19) await new Promise((r) => setTimeout(r, 0));   // let the page breathe
  }
  stage.style.visibility = '';
  const { medium } = w;
  const race = new RaceTick(gs, w);
  sound.attach(xt, w.aconto2[0]);  // the player's sparks scrape
  log(`stage ${checkpoints.stage}: ${checkpoints.name}`);
  window.xt = xt; window.checkpoints = checkpoints;
  window.ext = { w, race };        // for the console
  // ?debug=1: the base's console handle (web/main.js), for tools that read it
  if (params.get('debug') === '1') window.__nfm = { xt, checkPoints: checkpoints, gs, medium: w.medium, record: w.record, co: w.aconto2, w, race };

  // ---- the pause: the base port's race menu (web/race-ui.js) ------------------------
  // Esc, or Enter (stat() sets the jar's pause fase -6), opens it over the frozen frame
  // with the base's grey blur (race-ui.css); Resume hands the jar fase 609, its own way
  // back into the race (fcnt, wrecks), and restarts the music stat() stopped; Instant
  // Replay is the jar's (fase -1, which ends on -6: back to this menu); Quit leaves.
  let paused = false, pauseArt = null, replayArmed = false, musicHeld = false;
  try {
    const gif = (await readZip('data/images.zip')).get('paused.gif');
    const blob = new Blob([gif], { type: 'image/gif' });
    pauseArt = URL.createObjectURL(lang === 'es'
      ? await spanishPauseBackground(await createImageBitmap(blob)).convertToBlob({ type: 'image/png' }) : blob);
  } catch { /* the menu draws its own panel */ }
  // the jar's music pause and resume (xtGraphics.stat / pausedgame)
  const musicPaused = (on) => {
    if (!xt.loadedt[xt.lastload]) return;
    if (!xt.isMidi[xt.lastload]) { if (on) xt.stracks[xt.lastload].stop(); else xt.stracks[xt.lastload].resume(); return; }
    const t = xt.mtracks[xt.lastload];
    if (on && !xt.stopped) { t.setPaused(true); xt.resumed = false; xt.stopped = true; }
    if (!on && !xt.resumed && !xt.mutem) { t.setPaused(false); xt.resumed = true; xt.stopped = false; }
  };
  const raceMenu = createRaceMenu(stage, {
    multiplayer: false,
    pauseArt,
    pauseArtBackgroundOnly: lang === 'es',
    onLeave: () => exit(),
    onReplay: () => {
      if (w.record.ghosts === false) return 'Replay recording is off. Enable it in launcher Settings, then start a new race.';
      if (w.record.caught < 300) return 'Sorry not enough replay data to play available, please try again later.';
      // Through one jar frame of fase -7, which zeroes run()'s replay counter (a local, k2)
      // after pausedgame -- here a pausedgame that only starts the replay, as its option does.
      const own = xt.pausedgame;
      xt.pausedgame = function () {
        xt.pausedgame = own;
        replayArmed = false;
        musicPaused(false);
        this.fase = -1;
      };
      replayArmed = true;
      paused = false;
      gs.u[0].enter = gs.u[0].handb = false;   // the key that chose it would end the replay at once
      xt.fase = -7;
      return true;
    },
    onSkipReplay: () => { gs.u[0].enter = true; },   // the jar's replay ends on Enter
    onToggle: (open) => {
      const u = gs.u[0];
      u.up = u.down = u.left = u.right = u.handb = u.enter = false;
      paused = open;
      if (open) { sound.stopAll(); musicPaused(true); } else {
        musicPaused(false);
        if (xt.fase === 0) xt.fase = 609;
        last = performance.now();
        acc = 0;
      }
    },
  });
  // the base's menu is laid out on 800x450: centre it on Extended's 870x480
  stage.querySelector('.race-menu').style.transform = `translate(${(W - 800) / 2}px, ${(H - 450) / 2}px)`;
  const toPause = () => {
    xt.fase = 0;
    gs.u[0].enter = gs.u[0].handb = false;
    if (raceMenu.isOpen) raceMenu.returnToPause(); else raceMenu.show();
  };

  // ---- input ------------------------------------------------------------------
  // The race menu takes its keys first (after the audio unlock); outside it and the race
  // (the jar's finish screens) Esc is the jar's. GameSparker.keyDown is Extended's own map.
  addEventListener('keydown', (e) => {
    if ((xt.fase === 0 || raceMenu.isOpen) && raceMenu.handleKey(e)) { e.preventDefault(); e.stopImmediatePropagation(); }
  }, true);
  if (!menus) { addEventListener('keydown', keyDown); addEventListener('keyup', keyUp); }

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
  // Settings -> Show performance (perfline.js); 'all' is the full line with the sim/draw costs
  const PERF = perfLevel(params);
  const SHOW_STATS = params.get('stats') === '1' || PERF === 'all';
  const SPIKE_MS = parseFloat(params.get('spike') || '0');   // ?spike=MS logs slow frames
  window.spikes = [];
  // ?bench= ?prof=1 ?maxfps= (benchtools.js)
  const PROFILE = params.get('prof') === '1';
  const BENCH_S = parseFloat(params.get('bench') || '0');
  const MAX_FPS = parseFloat(params.get('maxfps') || '0');
  const cap = frameCap(MAX_FPS);
  if (PROFILE || BENCH_S > 0) countScene(ContO, Plane);
  const prof = PROFILE ? installProfile({ medium, Plane, Madness }) : null;
  const bench = BENCH_S > 0 ? new Bench(BENCH_S, parseFloat(params.get('warmup') || '3000'), prof) : null;
  const config = () => `buffer ${rd.gl.drawingBufferWidth}x${rd.gl.drawingBufferHeight}   interp=${INTERPOLATE ? 1 : 0}`
    + ` players=${xt.nplayers} stage=${checkpoints.stage}${MAX_FPS ? ` maxfps=${MAX_FPS}` : ''}${PROFILE ? ' prof=1' : ''}`;
  if (bench) {
    addEventListener('keydown', (e) => {
      if (e.code !== 'KeyR' || !bench.done) return;
      bench.restart();
      last = performance.now();    // no burst of catch-up ticks on the way back
      acc = 0;
    });
  }

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
  // The race just stopped (finish, pause): draw its frame once more and keep a copy, in the
  // same task, before the browser presents and clears the WebGL buffer.
  const freeze = () => {
    rd.begin(true);
    drawScene();
    rd.replay(hudVerts);
    rd.end();
    frozen ??= Object.assign(document.createElement('canvas'), { width: glCanvas.width, height: glCanvas.height });
    const ctx = frozen.getContext('2d');
    ctx.drawImage(glCanvas, 0, 0);
    ctx.drawImage(textCanvas, 0, 0, frozen.width, frozen.height);
  };
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
    if (bench?.done) return;         // frozen with the report up; R runs another window
    if (cap?.(now)) return;
    const w0 = performance.now();
    acc += now - last;
    last = now;
    if (paused) { acc = 0; return; }   // the race menu: the frozen frame stays up
    if (acc > TICK_MS * MAX_CATCHUP) acc = TICK_MS * MAX_CATCHUP;

    // the jar's pause: the race menu instead of its pausedgame screen (the replay ends on -6,
    // and run() goes on to -7 in the same frame)
    const jarPause = () => !replayArmed && (xt.fase === -6 || xt.fase === -7);
    if (jarPause()) { toPause(); return; }
    // the jar's own screens (finish, replays): its frame, once per tick
    if (xt.fase !== 0) {
      let ran = false;
      while (acc >= TICK_MS && !jarPause()) {
        acc -= TICK_MS; rd.begin(); frame.next(); ran = true;
        // The presenter's screen (fase 176) stays up until the race's music is all in:
        // run() leaves it for "press start" (6) after a fixed count; while the download
        // runs it is sent back, and its clock (duration, which times the career's .ogg
        // intro) restarts when it is let through.
        if (xt.fase === 6 && !musicStatus().done) { xt.fase = 176; musicHeld = true; }
        else if (xt.fase === 6 && musicHeld) { musicHeld = false; xt.duration = System.nanoTime(); xt.pausetime = xt.elapsed = 0; }
      }
      if (ran) rd.end();
      if (xt.fase === 0) { capture(snapPrev); capture(snapCurr); }   // back in the race: blend from here
      return;
    }

    let stepped = false, sim = 0, drew = 0, tf = 0;
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
      tf++;
      stepped = true;
      if (xt.fase !== 0) break;      // paused, finished: the jar's screens take the next frame
    }
    if (stepped) capture(snapCurr);
    if (stepped && xt.fase !== 0) { freeze(); return; }

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
    if (xt.starcnt > 36 && xt.fase === 0) keepStartFrame();

    const work = performance.now() - w0;
    simMs += sim; drawMs += drew;
    if (work > worst) worst = work;
    if (work > 16.7) over++;
    if (SPIKE_MS && work > SPIKE_MS) {
      const e = { at: Math.round(now), work: +work.toFixed(1), stepped, sim: +sim.toFixed(1), draw: +drew.toFixed(1), verts: rd.inputVerts };
      if (prof) e.prof = prof.line(prof.frame);
      window.spikes.push(e);
      console.log('spike', JSON.stringify(e));
    }
    prof?.next();
    // the race proper: the fly-in and countdown (starcnt 130 -> 0) are not in the warmup or the window
    if (xt.starcnt === 0 && bench?.frame(now, { sim, draw: drew, ticks: tf, rendered: stepped || INTERPOLATE, rd })) {
      log(bench.report(config()));
      return;
    }
    if (++frames >= 5 && now - lastFpsAt >= 500) {
      const dt = now - lastFpsAt;
      const buf = `${rd.gl.drawingBufferWidth}x${rd.gl.drawingBufferHeight}`;
      let line = `${(frames * 1000 / dt).toFixed(0)} fps  ${(ticks * 1000 / dt).toFixed(1)} tick/s  ${rd.inputVerts}/${rd.vertexCount} verts`
        + `  fan=${rd.fanPolys} concave=${rd.concavePolys}/${rd.concaveVerts}v  ${buf}  view=${gs.view}${bench ? bench.status(now) : ''}`;
      if (SHOW_STATS) {
        line += `\n  sim ${(simMs / Math.max(1, ticks)).toFixed(1)}ms/tick  draw ${(drawMs / Math.max(1, frames)).toFixed(1)}ms/frame`
          + `  worst ${worst.toFixed(1)}ms  over 16.7ms: ${over}/${frames}  [interp=${INTERPOLATE ? 1 : 0}]`;
      }
      if (!bench) line = perfLine(PERF, { fps: frames * 1000 / dt, tps: ticks * 1000 / dt, tickMs: simMs / Math.max(1, ticks), frameMs: drawMs / Math.max(1, frames) }) ?? line;
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
