// The original menu screens ported so far -- carselect, stageselect, finish --
// and the plumbing they depend on. Pixel effects (drawSmokeCarsbg,
// fleximage) need OffscreenCanvas, which node lacks, so these run with
// `badmac = true`, the Java's own no-pixel-effects path: what is tested is the
// state machine each screen drives, which is what breaks silently.
import test from 'node:test';
import assert from 'node:assert';
import { readFileSync, readdirSync } from 'node:fs';
import { parseZip } from './vfs.js';
import { Graphics2D } from './graphics.js';
import { CanvasGraphics } from './canvas-graphics.js';
import { Medium } from './Medium.js';
import { Trackers } from './Trackers.js';
import { CheckPoints } from './CheckPoints.js';
import { Control } from './Control.js';
import { Record } from './Record.js';
import { CarDefine } from './CarDefine.js';
import { Mad } from './Mad.js';
import { GameSparker } from './GameSparker.js';
import { XtGraphics } from './XtGraphics.js';
import { objArray, setSeed } from './java.js';

const R = new URL('../', import.meta.url);
const readRepo = (p, enc) => readFileSync(new URL(p, R), enc);

async function world() {
  setSeed(12345);
  const zip = await parseZip(new Uint8Array(readRepo('data/models.zip')));
  const medium = new Medium();
  const trackers = new Trackers();
  const checkPoints = new CheckPoints();
  const models = objArray(124);
  const gs = new GameSparker();
  const cd = new CarDefine(models, medium, trackers, gs);
  const xt = new XtGraphics(medium, cd, new Graphics2D(null, null, 800, 450), gs);
  const record = new Record(medium);
  gs.loadbase(models, medium, trackers, zip);
  const mads = objArray(8);
  for (let i = 0; i < 8; ++i) {
    mads[i] = new Mad(cd, medium, record, xt, i);
    gs.u[i] = new Control(medium);
  }
  xt.badmac = true;
  return { medium, trackers, checkPoints, models, gs, cd, xt, record, mads, control: gs.u[0] };
}

// ---- plumbing ----------------------------------------------------------------

// The failure that shipped: carselect.js imported loadCarSelectImages from a
// stale images.js. A browser serves that from cache; this checks the files on
// disk, so a named import with no matching export can never land again.
test('every named import in web/ resolves to an export of its module', () => {
  const dir = new URL('./', import.meta.url);
  const files = readdirSync(dir).filter((f) => f.endsWith('.js') && !f.endsWith('.test.js'));
  const exportsOf = new Map();
  const names = (src) => {
    const out = new Set();
    for (const m of src.matchAll(/export\s+(?:async\s+)?(?:function\*?|class|const|let|var)\s+([A-Za-z_$][\w$]*)/g)) out.add(m[1]);
    for (const m of src.matchAll(/export\s*\{([^}]*)\}/g)) {
      for (const part of m[1].split(',')) {
        const n = part.trim().split(/\s+as\s+/).pop();
        if (n) out.add(n);
      }
    }
    return out;
  };
  for (const f of files) exportsOf.set(f, names(readFileSync(new URL(f, dir), 'utf8')));
  const missing = [];
  for (const f of files) {
    const src = readFileSync(new URL(f, dir), 'utf8');
    for (const m of src.matchAll(/import\s*\{([^}]*)\}\s*from\s*'\.\/([\w.-]+\.js)'/g)) {
      const target = exportsOf.get(m[2]);
      if (!target) continue;                       // outside web/ (vendor, subdirs)
      for (const part of m[1].split(',')) {
        const n = part.trim().split(/\s+as\s+/)[0];
        if (n && !target.has(n)) missing.push(`${f}: '${n}' is not exported by ${m[2]}`);
      }
    }
  }
  assert.deepStrictEqual(missing, []);
});

// Java allows a field and a method to share a name (xtGraphics has both
// `Image[] trackbg` and `trackbg(boolean)`); in JS the instance property
// silently shadows the method and the call throws "not a function".
test('no XtGraphics field shadows one of its methods', () => {
  const xt = new XtGraphics();
  const methods = Object.getOwnPropertyNames(XtGraphics.prototype).filter((k) => k !== 'constructor');
  const shadowed = methods.filter((k) => Object.prototype.hasOwnProperty.call(xt, k));
  assert.deepStrictEqual(shadowed, []);
});

test('CanvasGraphics paints in call order and never clears on begin()', () => {
  const calls = [];
  const ctx = new Proxy({}, {
    get: (o, k) => (k in o ? o[k] : (...a) => calls.push(k)),
    set: (o, k, v) => { o[k] = v; return true; },
  });
  const canvas = { width: 1600, height: 900, getContext: () => ctx };
  const rd = new CanvasGraphics(canvas);
  calls.length = 0;
  rd.begin();
  rd.drawImage({}, 0, 0);
  rd.fillPolygon([0, 10, 0], [0, 0, 10], 3);
  rd.drawString('x', 5, 5);
  assert.deepStrictEqual(calls.filter((k) => ['drawImage', 'fill', 'fillText', 'clearRect'].includes(k)),
    ['drawImage', 'fill', 'fillText']);
});

// ---- carselect -----------------------------------------------------------------

async function carSelect(slot) {
  const w = await world();
  w.xt.gmode = 0;
  w.xt.osc = slot;
  w.xt.inishcarselect(w.models);
  w.xt.fase = 7;
  const tick = () => w.xt.carselect(w.control, w.models, w.mads[0], 0, 0, false);
  return { ...w, tick };
}

test('carselect: right flips to the next car after the 20-tick animation', async () => {
  const { xt, control, tick } = await carSelect(3);
  assert.strictEqual(xt.sc[0], 3);
  tick();
  control.right = true;
  tick();
  assert.strictEqual(control.right, false, 'the screen consumes the key');
  for (let i = 0; i < 25; i++) tick();
  assert.strictEqual(xt.sc[0], 4);
  assert.strictEqual(xt.flipo, 0);
});

test('carselect: left on the first car does nothing', async () => {
  const { xt, control, tick } = await carSelect(0);
  control.left = true;
  for (let i = 0; i < 25; i++) tick();
  assert.strictEqual(xt.sc[0], 0);
});

test('carselect: Enter picks the car and leaves fase 3 (stage select)', async () => {
  const { xt, control, tick, medium } = await carSelect(7);
  tick();
  control.enter = true;
  tick();
  assert.strictEqual(xt.fase, 3);
  assert.strictEqual(xt.osc, 7);
  assert.strictEqual(medium.crs, false);
});

test('carselect: Enter is ignored while the car is still flipping', async () => {
  const { xt, control, tick } = await carSelect(2);
  control.right = true;
  tick();                                 // flipo = 20 -> 19
  control.enter = true;
  tick();
  assert.strictEqual(xt.fase, 7);
});

// ---- stage select ----------------------------------------------------------------

async function loadedStage(w, n) {
  w.checkPoints.stage = n;
  w.xt.nplayers = 1;
  w.xt.fase = 2;
  w.gs.loadstage(objArray(610), w.models, w.medium, w.trackers, w.checkPoints, w.xt, w.mads, w.record,
    readRepo(`stages/${n}.txt`, 'latin1'));
}

test('loadstage at fase 2 starts the stage-select fly-in and hands over to fase 1', async () => {
  const w = await world();
  await loadedStage(w, 1);
  assert.strictEqual(w.xt.fase, 1);
  assert.strictEqual(w.medium.hit, 45000);
  assert.strictEqual(w.medium.trk, 1);
  assert.deepStrictEqual([w.medium.iw, w.medium.ih, w.medium.w, w.medium.h], [65, 25, 735, 425]);
});

test('loadstage at race fase leaves the camera alone', async () => {
  const w = await world();
  w.checkPoints.stage = 1;
  w.xt.fase = 0;
  w.gs.loadstage(objArray(610), w.models, w.medium, w.trackers, w.checkPoints, w.xt, w.mads, w.record,
    readRepo('stages/1.txt', 'latin1'));
  assert.strictEqual(w.xt.fase, 0);
  assert.strictEqual(w.medium.trk, 0);
});

test('stageselect: right steps to the next stage and asks for a reload', async () => {
  const w = await world();
  await loadedStage(w, 1);
  w.control.right = true;
  w.xt.stageselect(w.checkPoints, w.control, 0, 0, false);
  assert.strictEqual(w.checkPoints.stage, 2);
  assert.strictEqual(w.xt.fase, 2);
});

test('stageselect: left on stage 1 does nothing; Enter goes to the race', async () => {
  const w = await world();
  await loadedStage(w, 1);
  w.control.left = true;
  w.xt.stageselect(w.checkPoints, w.control, 0, 0, false);
  assert.strictEqual(w.checkPoints.stage, 1);
  assert.strictEqual(w.xt.fase, 1);
  w.control.enter = true;
  w.xt.stageselect(w.checkPoints, w.control, 0, 0, false);
  assert.strictEqual(w.xt.fase, 5);
});

// ---- finish ------------------------------------------------------------------------

test('finish: Enter after a win leaves fase 102 on the next stage', async () => {
  const w = await world();
  w.xt.winner = true;
  w.xt.contin = [null, null];
  w.checkPoints.stage = 3;
  w.xt.fase = -5;
  w.xt.finish(w.checkPoints, w.models, w.control, 0, 0, false);
  assert.strictEqual(w.xt.fase, -5, 'waits for Enter');
  w.control.enter = true;
  w.xt.finish(w.checkPoints, w.models, w.control, 0, 0, false);
  assert.strictEqual(w.xt.fase, 102);
  assert.strictEqual(w.checkPoints.stage, 4);
  assert.strictEqual(w.control.enter, false);
});

test('finish: a loss keeps the stage', async () => {
  const w = await world();
  w.xt.winner = false;
  w.xt.contin = [null, null];
  w.checkPoints.stage = 3;
  w.control.handb = true;
  w.xt.finish(w.checkPoints, w.models, w.control, 0, 0, false);
  assert.strictEqual(w.xt.fase, 102);
  assert.strictEqual(w.checkPoints.stage, 3);
});

// ---- the race's Enter ----------------------------------------------------------

// Enter in a race is the Java's pause: stat() stops the stage's music and sets
// fase -6. The port once also loaded the MENU track there, which is what made
// Enter mid-race swap the race's music for the launcher's.
test('stat: Enter in a race pauses (fase -6) and only stops the music', async () => {
  const w = await world();
  const placed = objArray(610);
  w.checkPoints.stage = 1;
  w.xt.nplayers = 1;
  w.xt.fase = 0;
  w.gs.loadstage(placed, w.models, w.medium, w.trackers, w.checkPoints, w.xt, w.mads, w.record,
    readRepo('stages/1.txt', 'latin1'));
  w.xt.fase = 0;
  w.xt.starcnt = 0;
  w.xt.holdit = false;
  w.xt.loadedt = true;
  let stopped = 0;
  const loaded = [];
  w.xt.strack = { stop() { stopped++; }, resume() {}, load: async () => true };
  w.xt.loadmusic = (t) => loaded.push(t);
  w.control.enter = true;
  w.xt.stat(w.mads[0], placed[0], w.checkPoints, w.control, true);
  assert.strictEqual(w.xt.fase, -6);
  assert.strictEqual(stopped, 1);
  assert.deepStrictEqual(loaded, []);
});

// ---- interpolated redraws must terminate ---------------------------------------

// Plane.d's wasted-car effect (embos >= 16) picks two distinct vertices with a
// rejection loop on m.random(). An interpolated redraw REPLAYS the tick's
// recorded randoms in a cycle, so a cycle that never differs spun forever:
// the page froze on the Instant Replay of a destroyed car.
test('a wasted car drawn in an interpolated pass cannot spin forever', async () => {
  const w = await world();
  w.xt.osc = 0;
  w.xt.inishcarselect(w.models);           // camera pointed at the car
  const car = w.models[0];
  car.x = 0; car.y = -34 - car.grat; car.z = 950;
  for (const pl of car.p.slice(0, car.npl)) pl.embos = 16;
  w.medium.interpolating = true;
  w.medium.rlog = new Float32Array([0.0]);  // every replayed value -> vertex 0
  w.medium.rn = 1;
  w.medium.rp = 0;
  car.d(new Graphics2D(null, null, 800, 450));
  w.medium.interpolating = false;
  assert.ok(car.p.slice(0, car.npl).every((pl) => pl.pa !== pl.pb || pl.n < 2));
});

// ---- the replays' own UI (replyn / levelhigh) --------------------------------------

function textRecorder() {
  const rd = new Graphics2D(null, null, 800, 450);
  const drawn = [];
  const images = [];
  rd.drawString = (s) => drawn.push(s);
  rd.drawImage = (img, x, y) => images.push([img, x, y]);
  return { rd, drawn, images };
}

test('replyn: the Instant Replay label blinks between "Replay  >" and "Replay  >>"', () => {
  const xt = new XtGraphics(new Medium(), null, null, null);
  const { rd, drawn } = textRecorder();
  xt.rd = rd;
  xt.aflk = true;
  xt.replyn();
  xt.replyn();
  xt.replyn();
  assert.deepStrictEqual(drawn, ['Replay  > ', 'Replay  >>', 'Replay  > ']);
});

test('levelhigh: header image, the title for each case, and the Enter line', () => {
  const xt = new XtGraphics(new Medium(), null, null, null);
  xt.im = 0;
  xt.gameh = { tag: 'gameh' };
  const title = (wasted, whenwasted, closefinish, stage) => {
    const { rd, drawn, images } = textRecorder();
    xt.rd = rd;
    xt.levelhigh(wasted, whenwasted, closefinish, 100, stage);
    assert.deepStrictEqual(images[0], [xt.gameh, 301, 20]);
    assert.strictEqual(drawn[drawn.length - 1], 'Press  [ Enter ]  to continue');
    return drawn[0];
  };
  assert.strictEqual(title(3, 0, 0, 1), "You Wasted 'em!");
  assert.strictEqual(title(3, 0, 1, 1), 'Close Finish!');
  assert.strictEqual(title(3, 0, 2, 1), 'Close Finish!  Almost got it!');
  assert.strictEqual(title(0, 229, 0, 1), 'Wasted!');
  assert.strictEqual(title(0, 100, 0, 5), 'Stunts!');
  assert.strictEqual(title(0, 100, 0, 2), 'Best Stunt!');
});
