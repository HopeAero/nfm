# Extended: New Cars (Car Maker cars added, none replaced) Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Car Maker cars (browser storage + `mycars/`) race in Extended's Free Play as *new* cars after Extended's 39, each with a player-chosen donor whose special power it borrows.

**Architecture:** A new car gets two numbers. Its **index** (`NEW_BASE + i`, 200+) addresses the model array and every per-car table (70 tables grown after construction). Its **identity** is the donor's number: an ext-ident rewrite wraps every `car <op> literal` comparison in the generated classes as `id(car) <op> literal`, so the 1,110 `cn === 36`-style checks (specials, quirks) see the donor. Physics comes from the base port's `CarDefine.loadstat`; Extended-only values are copied from the donor column. Stock cars have `id(c) === c`, so every existing race hash stays the same.

**Tech Stack:** plain ES modules, `node --test`, the J2JS-generated classes in `web/ext/`, `web/tools/ext-patches.mjs`, a CDP browser check (`web/tools/cdp.mjs`).

**Spec:** this conversation (2026-09-26), summarised:
- New cars never replace a stock car; they are added after car 38.
- Opponents never use new cars.
- Special: one of the existing ones, chosen by the player as a donor car.
- Scope: Extended **Free Play** only. Career mode is out of scope (its save is per index, `career-save.js` `CARS = 39`).

## Global Constraints

- `web/ext/{ContO,Plane,Wheels,Trackers,Medium,Madness,xtGraphics,Control,GameSparker,Contva,...}.js` are GENERATED ("do not edit by hand"). Every change to them goes through a named, idempotent patch tool (`web/tools/ext-patches.mjs` or the new `web/tools/ext-ident.mjs`) and a test that fails when the patch is missing.
- Stock-car selftests must not change: `?ext=classic&stage=4&car=30&selftest=400` -> `271c3367`, `?ext=career&stage=3&car=5&selftest=600` -> `353ea4bf` (record the actual baseline in Task 1; if it differs from these, the recorded baseline is the reference).
- `NEW_BASE = 200`: model indices 0-38 cars, 39-77 track pieces, 78-116 beast variants, 117-128 scenery, 129-196 stagecompat's appended NFM 2 models.
- Assets in `data/`, `mycars/`, `stages/` are not modified.
- Code and comments in English; UI strings go through `tr()` with Spanish entries (`web/i18n*.js`).
- `cd web && node --test` passes after every task.

## Review Focus

1. **A broken or non-car `.rad` in storage** (no `stat(` line, `npl <= 60`, parse error): expected skipped with a console line, the race still starts. -> Task 2 test `rejects a model that is not a car`.
2. **A remembered pick or donor entry naming a car that was deleted**: expected fallback to car 38 / entry dropped, no crash. -> Task 7 test `a pick naming a missing new car falls back`.
3. **A stock model that happens to carry `ScaleX(`**: expected ignored exactly as today (the jar ignores it). -> Task 4 test `stock codes ignore Scale lines`.
4. **A new car on an NFM 2 stage** (classic mode, `sc >= 23` checks): expected races like on an Extended stage. -> Task 6 browser check runs both groups.
5. **Career mode with new cars in storage**: expected no new car reachable and the career hash unchanged. -> Task 6 browser check runs the career selftest with the store populated.

---

## File Structure

- Create `web/ext/newcars.js` — the registry: `NEW_BASE`, `id()`, `setNewCars()`, `newCars()`, `lastCar()`, `isNew()`, `nextCar()`. Pure, no DOM.
- Create `web/ext/newcars-stats.js` — `.rad` text -> `{ name, text, stat, cclass, donor }` via the base `CarDefine`/`ContO`; validation; default donor by class.
- Create `web/ext/newcars-grow.js` — `growMadness(m)`, `growXt(x)`, the explicit table lists.
- Create `web/tools/ext-ident.mjs` — the identity rewrite (apply / `--check` / `--audit`).
- Create tests: `web/ext/newcars.test.js`, `web/ext/newcars-stats.test.js`, `web/ext/newcars-grow.test.js`, `web/ext/ident.test.js`.
- Create `web/tools/browser-newcar.mjs` — selftests in headless Chrome.
- Modify `web/tools/ext-patches.mjs` — patches `newcar-grow`, `newcar-isacar`, `newcar-scale`.
- Modify `web/ext/race.js` — load new cars, add models after `loadbase`.
- Modify `web/ext/menus.js` — car select arrows past 38, pick by name.
- Modify `web/launcher.js`, `index.html`, `web/i18n.js` — "New cars" page in Extended Edition.
- Modify `web/ext/README.md`, `TASKS.md`, `WORK.md`.

---

### Task 1: Registry and identity function

**Files:**
- Create: `web/ext/newcars.js`
- Test: `web/ext/newcars.test.js`

**Interfaces:**
- Produces: `NEW_BASE = 200`, `STOCK = 39`, `id(c: number): number`, `setNewCars(list: {name, text, stat, cclass, donor}[])`, `newCars(): readonly array`, `isNew(c): boolean`, `lastCar(): number`, `nextCar(c: number, d: 1|-1): number`.

- [ ] **Step 1: Record the stock baseline.** Start `python web/tools/serve.py 8123`, open `http://localhost:8123/web/main.html?ext=classic&stage=4&car=30&selftest=400` and `...?ext=career&stage=3&car=5&selftest=600` (Playwright or Chrome). Read `#log`: `selftest N ticks interp=1: <hash>`. Write both hashes into this plan's Global Constraints if they differ from the ones listed.

- [ ] **Step 2: Write the failing test**

```js
// web/ext/newcars.test.js
import { test } from 'node:test';
import assert from 'node:assert';
import { NEW_BASE, id, setNewCars, lastCar, isNew, nextCar } from './newcars.js';

test('stock cars are their own identity; new cars answer as their donor', () => {
  setNewCars([{ name: 'A', donor: 30 }, { name: 'B', donor: 13 }]);
  for (let c = 0; c < 39; c++) assert.strictEqual(id(c), c);
  assert.strictEqual(id(NEW_BASE), 30);
  assert.strictEqual(id(NEW_BASE + 1), 13);
  assert.strictEqual(lastCar(), NEW_BASE + 1);
  assert.ok(isNew(NEW_BASE) && !isNew(38));
});

test('the car select steps 38 -> first new car -> ... and back, clamped at both ends', () => {
  setNewCars([{ name: 'A', donor: 30 }, { name: 'B', donor: 13 }]);
  assert.strictEqual(nextCar(38, 1), NEW_BASE);
  assert.strictEqual(nextCar(NEW_BASE, -1), 38);
  assert.strictEqual(nextCar(NEW_BASE + 1, 1), NEW_BASE + 1);
  assert.strictEqual(nextCar(0, -1), 0);
  setNewCars([]);
  assert.strictEqual(nextCar(38, 1), 38);
  assert.strictEqual(lastCar(), 38);
});
```

- [ ] **Step 3: Run it to verify it fails**

Run: `cd web && node --test ext/newcars.test.js`
Expected: FAIL, `Cannot find module './newcars.js'`.

- [ ] **Step 4: Write the implementation**

```js
// web/ext/newcars.js
// Extended's new cars: Car Maker cars ADDED after Extended's 39, never replacing one.
// A new car has two numbers. Its INDEX (NEW_BASE + i) addresses the model array and
// every per-car table (newcars-grow.js). Its IDENTITY is its donor's number: every
// `car <op> literal` comparison in the generated classes asks id(car) (ext-ident.mjs),
// so the donor's special power and quirks come with it. Stock cars are their own identity.
// Model array: 0-38 cars, 39-77 track pieces, 78-116 beast cars (cn + 78),
// 117-128 scenery, 129-196 stagecompat's NFM 2 models -- hence 200.
export const STOCK = 39;
export const NEW_BASE = 200;

let cars = [];
let donors = new Int32Array(0);

export function setNewCars(list) {
  cars = list.slice();
  donors = Int32Array.from(cars, (c) => c.donor);
}
export const newCars = () => cars;
export const id = (c) => (c >= NEW_BASE ? donors[c - NEW_BASE] : c);
export const isNew = (c) => c >= NEW_BASE;
export const lastCar = () => (cars.length ? NEW_BASE + cars.length - 1 : STOCK - 1);

/** The Free Play car select's arrows: 0..38, then the new cars, no wrap. */
export function nextCar(c, d) {
  if (d > 0) return c === STOCK - 1 ? (cars.length ? NEW_BASE : c) : Math.min(c + 1, lastCar());
  return c === NEW_BASE ? STOCK - 1 : Math.max(c - 1, 0);
}
```

- [ ] **Step 5: Run the test to verify it passes**

Run: `cd web && node --test ext/newcars.test.js`
Expected: PASS, 2 tests.

- [ ] **Step 6: Commit**

```bash
git add web/ext/newcars.js web/ext/newcars.test.js
git commit -m "Extended new cars: registry and identity function"
```

---

### Task 2: A `.rad` becomes a new car's stats

**Files:**
- Create: `web/ext/newcars-stats.js`
- Test: `web/ext/newcars-stats.test.js`

**Interfaces:**
- Consumes: base `web/CarDefine.js` (`new CarDefine(bco, m, t, gs)`, `loadstat(text, name, maxR, roofat, wh, 16)` writes slot 16 of its own tables), base `web/ContO.js` (`new ContO(text, medium, trackers)` exposes `maxR`, `roofat`, `wh`, `npl`, `errd`, `keyx`, `keyz`).
- Produces: `carFromRad(name: string, text: string, donor?: number): { name, text, stat, cclass, donor } | null`, where `stat` holds the 21 CarDefine fields plus `outdam`, `enginsignature` (see `STAT_FIELDS`), and `defaultDonor(cclass: number): number`.

- [ ] **Step 1: Write the failing test**

```js
// web/ext/newcars-stats.test.js
import { test } from 'node:test';
import assert from 'node:assert';
import fs from 'node:fs';
import { carFromRad, defaultDonor, STAT_FIELDS } from './newcars-stats.js';

const simple = fs.readFileSync(new URL('../../mycars/Simple Car.rad', import.meta.url), 'latin1');

test('a Car Maker car gets every CarDefine field, finite', () => {
  const car = carFromRad('Simple Car', simple);
  assert.ok(car);
  for (const f of STAT_FIELDS) {
    const v = car.stat[f];
    const flat = v?.length ? Array.from(v) : [v];
    assert.ok(flat.every(Number.isFinite), `${f} = ${flat}`);
  }
  assert.ok(car.cclass >= 0 && car.cclass <= 4);
  assert.strictEqual(car.donor, defaultDonor(car.cclass));   // no donor given: by class
});

test('an explicit donor is kept', () => {
  assert.strictEqual(carFromRad('Simple Car', simple, 13).donor, 13);
});

test('rejects a model that is not a car', () => {
  assert.strictEqual(carFromRad('junk', 'nothing here\n'), null);
  const noStat = simple.replace(/^stat\(.*$/m, '');
  assert.strictEqual(carFromRad('nostat', noStat), null);
});

test('the default donor is an NFM 2 car of the same class (Extended 23-38)', () => {
  for (let k = 0; k <= 4; k++) {
    const d = defaultDonor(k);
    assert.ok(d >= 23 && d <= 38, `class ${k} -> ${d}`);
  }
});
```

- [ ] **Step 2: Run it to verify it fails**

Run: `cd web && node --test ext/newcars-stats.test.js`
Expected: FAIL, module not found.

- [ ] **Step 3: Write the implementation.** The base `ContO` needs a medium with trig tables: the stub below is `web/ContO.test.js:9-48` plus the four arrays its line 143-146 adds. The validation is `CarDefine.loadcar`'s (`web/CarDefine.js:716-743`): reject `errd`, `npl <= 60`, and a wheel layout that is not front-left / front-right / rear-left / rear-right.

```js
// web/ext/newcars-stats.js
// A Car Maker .rad as an Extended new car: the physics the base port's CarDefine
// computes from stat()/physics()/handling() (NFM 2's formulas, not Extended's
// hand-tuned tables), measured on the base ContO as CarDefine.loadcar measures it.
import { CarDefine } from '../CarDefine.js';
import { ContO as BaseContO } from '../ContO.js';
import { floatArray, intArray } from '../java.js';

// CarDefine.loadstat's outputs that Extended's Madness / xtGraphics tables also have
export const STAT_FIELDS = ['acelf', 'swits', 'handb', 'airs', 'airc', 'turn', 'grip', 'bounce', 'simag',
  'moment', 'comprad', 'push', 'revpush', 'lift', 'revlift', 'powerloss', 'flipy', 'msquash', 'clrad',
  'dammult', 'maxmag', 'outdam', 'enginsignature'];

// base cars 0-15 are Extended's 23-38, same order (catalog.js EXT_CARS)
const BASE_CCLASS = [0, 0, 0, 0, 0, 1, 2, 2, 2, 2, 3, 4, 4, 4, 4, 4];
export const defaultDonor = (cclass) => 23 + Math.max(0, BASE_CCLASS.indexOf(cclass));

// only measured, never drawn: what the base ContO constructor reads (web/ContO.test.js)
function stubMedium() {
  const tcos = floatArray(360), tsin = floatArray(360);
  for (let i = 0; i < 360; ++i) { tcos[i] = Math.cos(i * 0.017453292519943295); tsin[i] = Math.sin(i * 0.017453292519943295); }
  return {
    tcos, tsin, cx: 400, cy: 225, cz: 100, focus_point: 400, xz: 0, zy: 0, x: 0, y: 0, z: 0,
    trk: 0, adv: 900, ground: 250, fogd: 7, resdown: 0, loadnew: false,
    cpol: intArray(3), cgrnd: intArray(3), snap: intArray(3), csky: intArray(3), cfade: intArray(3), fade: intArray(16),
    cos(i) { while (i >= 360) i -= 360; while (i < 0) i += 360; return this.tcos[i]; },
    sin(i) { while (i >= 360) i -= 360; while (i < 0) i += 360; return this.tsin[i]; },
    random: () => 0.5,
  };
}
function stubTrackers() {
  const n = () => intArray(100);
  return { nt: 0, xy: n(), zy: n(), c: Array.from({ length: 100 }, () => intArray(3)), x: n(), y: n(), z: n(),
    radx: n(), rady: n(), radz: n(), skd: n(), dam: n(), notwall: new Array(100).fill(false), decor: new Array(100).fill(false) };
}

// CarDefine.loadcar's wheel rule: w() 1-4 are front-left, front-right, rear-left, rear-right
const wheelsOk = (o) => !(o.keyz[0] < 0 || o.keyx[0] > 0) && !(o.keyz[1] < 0 || o.keyx[1] < 0)
  && !(o.keyz[2] > 0 || o.keyx[2] > 0) && !(o.keyz[3] > 0 || o.keyx[3] < 0);

const SLOT = 16;   // CarDefine's first custom slot

export function carFromRad(name, text, donor) {
  let model;
  try { model = new BaseContO(text, stubMedium(), stubTrackers()); } catch { return null; }
  if (model.errd || model.npl <= 60 || !wheelsOk(model)) return null;
  const cd = new CarDefine(null, null, null, null);
  cd.loadstat(text, name, model.maxR, model.roofat, model.wh, SLOT);
  if (!cd.names[SLOT]) return null;          // loadstat blanks the name when stat() is missing
  const stat = {};
  const copy = (v) => (v?.length !== undefined ? v.slice() : v);
  for (const f of STAT_FIELDS) stat[f] = copy(cd[f][SLOT]);
  const cclass = cd.cclass[SLOT];
  return { name, text, stat, cclass, donor: Number.isInteger(donor) ? donor : defaultDonor(cclass) };
}
```

If `new CarDefine(null, ...)` throws because loadstat reads `this.m`/`this.t`/`this.gs`, pass `{}` for each. If the base ContO throws on a field the stub lacks, add that field to the stub (it is only measured).

- [ ] **Step 4: Run the tests to verify they pass**

Run: `cd web && node --test ext/newcars-stats.test.js`
Expected: PASS, 4 tests. If `defaultDonor` fails for a class missing from `BASE_CCLASS` (none is), the fallback is car 23.

- [ ] **Step 5: Commit**

```bash
git add web/ext/newcars-stats.js web/ext/newcars-stats.test.js
git commit -m "Extended new cars: a Car Maker .rad becomes CarDefine stats"
```

---

### Task 3: Growing the 70 per-car tables

**Files:**
- Create: `web/ext/newcars-grow.js`
- Test: `web/ext/newcars-grow.test.js`

**Interfaces:**
- Consumes: `newCars()`, `NEW_BASE` (Task 1); car objects from `carFromRad` (Task 2).
- Produces: `growMadness(m: Madness)`, `growXt(x: xtGraphics)`, `MAD_TABLES`, `XT_TABLES`, `MIRRORS`.

Measured facts this relies on (node, 2026-09-26): `new Madness({}, {}, {}, 0)` has exactly 53 own fields of length 39; `new xtGraphics({}, null, null, {})` has 17. In every stock car, `handbreset/turnreset/gripreset/airsreset/aircreset/momentreset/healthreset/push2/revpush2/lift2/powerloss2/nitroacelf/nitroswits` equal `handb/turn/grip/airs/airc/moment/maxmag/push/revpush/lift/powerloss/acelf/swits`.

- [ ] **Step 1: Write the failing test**

```js
// web/ext/newcars-grow.test.js
import { test } from 'node:test';
import assert from 'node:assert';
import fs from 'node:fs';
import { Madness } from './Madness.js';
import { xtGraphics } from './xtGraphics.js';
import { NEW_BASE, setNewCars } from './newcars.js';
import { carFromRad } from './newcars-stats.js';
import { growMadness, growXt, MAD_TABLES, XT_TABLES, MIRRORS } from './newcars-grow.js';

const simple = fs.readFileSync(new URL('../../mycars/Simple Car.rad', import.meta.url), 'latin1');
const len39 = (o) => Object.keys(o).filter((k) => o[k] && o[k].length === 39);
const flat = (v) => (v?.length !== undefined ? Array.from(v, flat).flat() : [v]);

test('the lists name every per-car table (a regeneration that adds one fails here)', () => {
  assert.deepStrictEqual(len39(new Madness({}, {}, {}, 0)).sort(), [...MAD_TABLES].sort());
  assert.deepStrictEqual(len39(new xtGraphics({}, null, null, {})).sort(), [...XT_TABLES].sort());
});

test('a new car gets a column in every table: finite, stock columns untouched', () => {
  setNewCars([carFromRad('Simple Car', simple, 30)]);
  const m = new Madness({}, {}, {}, 0), x = new xtGraphics({}, null, null, {});
  const before = JSON.stringify(MAD_TABLES.map((k) => flat(Array.from(m[k]))));
  growMadness(m); growXt(x);
  for (const k of MAD_TABLES) {
    assert.ok(m[k].length > NEW_BASE, k);
    assert.ok(flat(m[k][NEW_BASE]).every(Number.isFinite), `Madness.${k}[${NEW_BASE}]`);
  }
  for (const k of XT_TABLES) assert.notStrictEqual(x[k][NEW_BASE], undefined, `xtGraphics.${k}`);
  assert.strictEqual(x.names[NEW_BASE], 'Simple Car');
  assert.strictEqual(JSON.stringify(MAD_TABLES.map((k) => flat(Array.from(m[k]).slice(0, 39)))), before);
});

test('mirrors hold the CarDefine value; Extended-only tables hold the donor value', () => {
  const car = carFromRad('Simple Car', simple, 30);
  setNewCars([car]);
  const m = new Madness({}, {}, {}, 0);
  const stock = new Madness({}, {}, {}, 0);
  growMadness(m);
  for (const [mirror, base] of MIRRORS) assert.deepStrictEqual(flat(m[mirror][NEW_BASE]), flat(m[base][NEW_BASE]), mirror);
  assert.deepStrictEqual(flat(m.acelf[NEW_BASE]), flat(car.stat.acelf));
  assert.deepStrictEqual(flat(m.pmulti[NEW_BASE]), flat(stock.pmulti[30]));
  assert.deepStrictEqual(flat(m.healthcut[NEW_BASE]), flat(stock.healthcut[30]));
});

test('growing twice is a no-op (the patch runs once per construction)', () => {
  setNewCars([carFromRad('Simple Car', simple, 30)]);
  const m = new Madness({}, {}, {}, 0);
  growMadness(m); const n = m.acelf.length; growMadness(m);
  assert.strictEqual(m.acelf.length, n);
});
```

- [ ] **Step 2: Run it to verify it fails**

Run: `cd web && node --test ext/newcars-grow.test.js`
Expected: FAIL, module not found.

- [ ] **Step 3: Write the implementation**

```js
// web/ext/newcars-grow.js
// Every per-car table grows to hold the new cars at NEW_BASE + i. Typed arrays do not
// grow: each is reallocated and copied. A new car's column is its donor's, then the
// CarDefine values (and the tables that mirror them) are written over it. A table
// missing from these lists would read undefined -> NaN physics with no error:
// newcars-grow.test.js compares them with every length-39 field.
import { NEW_BASE, newCars } from './newcars.js';

export const MAD_TABLES = ['acelf', 'swits', 'handb', 'handbreset', 'airs', 'airc', 'turn', 'turnreset', 'grip',
  'gripreset', 'bounce', 'simag', 'moment', 'strengthreduce', 'comprad', 'push', 'push2', 'revpush', 'revpush2',
  'lift', 'lift2', 'revlift', 'powerloss', 'powerloss2', 'flipy', 'msquash', 'clrad', 'dammult', 'maxmag',
  'healthreset', 'healthcut', 'tsstat', 'accstat', 'ovrstat', 'gristat', 'stustat', 'strstat', 'endstat',
  'endboosts', 'exp', 'level', 'aitssp', 'aiaccsp', 'aigripsp', 'aistusp', 'aistrsp', 'aiendsp', 'pmulti',
  'nitroacelf', 'nitroswits', 'airsreset', 'aircreset', 'momentreset'];
export const XT_TABLES = ['killscn', 'winscn', 'statpoints', 'proba', 'outdam', 'powersave', 'enginsignature',
  'names', 'statstext', 'wststatgain', 'rcestatgain', 'extpoints', 'specialstats', 'statsalc', 'xbspratio',
  'rebsp', 'xbsp'];
// [mirror, source]: equal in every stock car (measured), so equal in a new one
export const MIRRORS = [['handbreset', 'handb'], ['turnreset', 'turn'], ['gripreset', 'grip'],
  ['airsreset', 'airs'], ['aircreset', 'airc'], ['momentreset', 'moment'], ['healthreset', 'maxmag'],
  ['push2', 'push'], ['revpush2', 'revpush'], ['lift2', 'lift'], ['powerloss2', 'powerloss'],
  ['nitroacelf', 'acelf'], ['nitroswits', 'swits']];

const clone = (v) => (v && typeof v === 'object' ? (ArrayBuffer.isView(v) ? v.slice() : v.map(clone)) : v);

function grown(table, size) {
  if (table.length >= size) return table;
  if (ArrayBuffer.isView(table)) { const t = new table.constructor(size); t.set(table); return t; }
  const t = table.slice(); t.length = size; return t;
}

function grow(obj, keys, write) {
  const cars = newCars();
  if (!cars.length) return;
  const size = NEW_BASE + cars.length;
  if (obj[keys[0]].length >= size) return;        // already grown
  for (const k of keys) {
    obj[k] = grown(obj[k], size);
    cars.forEach((car, i) => { obj[k][NEW_BASE + i] = clone(obj[k][car.donor]); });
  }
  cars.forEach((car, i) => write(obj, NEW_BASE + i, car));
}

export function growMadness(m) {
  grow(m, MAD_TABLES, (o, c, car) => {
    for (const [f, v] of Object.entries(car.stat)) if (f in o && MAD_TABLES.includes(f)) o[f][c] = clone(v);
    for (const [mirror, src] of MIRRORS) o[mirror][c] = clone(o[src][c]);
  });
}

export function growXt(x) {
  grow(x, XT_TABLES, (o, c, car) => {
    o.names[c] = car.name;
    o.outdam[c] = car.stat.outdam;
    o.enginsignature[c] = car.stat.enginsignature;
  });
}
```

- [ ] **Step 4: Run the tests to verify they pass**

Run: `cd web && node --test ext/newcars-grow.test.js`
Expected: PASS, 4 tests. If the first test fails because a list is off by a name, fix the list from the assertion diff (the measured source of truth is the constructor).

- [ ] **Step 5: Commit**

```bash
git add web/ext/newcars-grow.js web/ext/newcars-grow.test.js
git commit -m "Extended new cars: grow the 70 per-car tables from the donor and CarDefine"
```

---

### Task 4: Patches — grow after construction, `isacar`, Scale lines

**Files:**
- Modify: `web/tools/ext-patches.mjs` (add `NEWCAR_PATCHES`, include in `PATCHES`)
- Test: `web/ext/patches.test.js` (already checks every patch); new tests in `web/ext/newcars-grow.test.js`

**Interfaces:**
- Consumes: `growMadness`, `growXt` (Task 3), `NEW_BASE` (Task 1).
- Produces: generated classes that grow themselves, treat `code >= NEW_BASE` as a car, and honour `ScaleX/Y/Z` only for those codes.

- [ ] **Step 1: Write the failing tests** (append to `web/ext/newcars-grow.test.js`)

```js
import { ContO } from './ContO.js';
import { Medium } from './Medium.js';
import { Trackers } from './Trackers.js';

// as web/ext/ContO.test.js builds its models: a real Medium and Trackers, no xtGraphics
const bytes = (s) => Int8Array.from(s, (c) => c.charCodeAt(0));
const box = (scale) => `${scale}\n<p>\nc(100,100,100)\np(-10,0,10)\np(10,0,10)\np(10,0,-10)\n</p>\n`;
const model = (code, s) => new ContO(0, bytes(box(s)), new Medium(), new Trackers(), null, code);

test('a new car code is a car; stock codes are unchanged', () => {
  assert.strictEqual(model(NEW_BASE, '').isacar, true);
  assert.strictEqual(model(39, '').isacar, false);
  assert.strictEqual(model(5, '').isacar, true);
});

test('new car codes honour ScaleX/Y/Z; stock codes ignore Scale lines', () => {
  assert.ok(model(NEW_BASE, 'ScaleX(200)\nScaleZ(200)').maxR > model(NEW_BASE, '').maxR * 1.9);
  assert.strictEqual(model(5, 'ScaleX(200)\nScaleZ(200)').maxR, model(5, '').maxR);
});
```

- [ ] **Step 2: Run them to verify they fail**

Run: `cd web && node --test ext/newcars-grow.test.js`
Expected: FAIL on `isacar` (false) and on the scale ratio (1.0).

- [ ] **Step 3: Add the patches.** In `web/tools/ext-patches.mjs`, before `export const PATCHES`, add the block below; then add `...NEWCAR_PATCHES,` at the end of `PATCHES`. Each `find` is exact generated text (checked 2026-09-26 at `ContO.js:235`, `ContO.js:280-283, 299-300, 408-419`, `GameSparker.js:1496, 1507`); re-read those lines first and adjust whitespace if the tool throws "expected text gone".

```js
/**
 * Extended new cars (web/ext/newcars*.js): the per-car tables grow right after the
 * constructors fill them; a model at NEW_BASE+ is a car; and such a model honours
 * ScaleX/Y/Z as the base ContO does (Car Maker cars use them; the jar has no Scale
 * directive, so stock codes keep ignoring the lines).
 */
const NEWCAR_PATCHES = [
  { name: 'newcar-import', file: 'GameSparker.js',
    find: `import { Bots } from './Bots.js';\n`,
    replace: `import { Bots } from './Bots.js';\n// ext-patch newcar-import\nimport { growMadness, growXt } from './newcars-grow.js';\n` },
  { name: 'newcar-grow-xt', file: 'GameSparker.js',
    find: `    let xtgraphics = new xtGraphics(medium, this.rd, this.sg, this);\n`,
    replace: `    let xtgraphics = new xtGraphics(medium, this.rd, this.sg, this);\n    growXt(xtgraphics);   // ext-patch newcar-grow-xt\n` },
  { name: 'newcar-grow-mad', file: 'GameSparker.js',
    find: `      amadness[l] = new Madness(medium, record, xtgraphics, l);\n`,
    replace: `      amadness[l] = new Madness(medium, record, xtgraphics, l);\n      growMadness(amadness[l]);   // ext-patch newcar-grow-mad\n` },
  { name: 'newcar-isacar', file: 'ContO.js',
    find: `    if (((code < 39) || (((code >= 78) && (code < 117)))) || (code === 64)) {\n`,
    replace: `    // ext-patch newcar-isacar: a new car (NEW_BASE+, web/ext/newcars.js) is a car\n    if (((code < 39) || (((code >= 78) && (code < 117)))) || (code === 64) || (code >= 200)) {\n` },
  { name: 'newcar-scale-parse', file: 'ContO.js',
    find: `        if (s1.startsWith('iwid')) {\n`,
    replace: `        // ext-patch newcar-scale-parse: the base ContO's ScaleX/Y/Z, new cars only\n` +
      `        if (code >= 200 && s1.startsWith('Scale')) {\n` +
      `          const ax = 'XYZ'.indexOf(s1[5]);\n` +
      `          if (ax >= 0) this.scl[ax] = fr(fr(this.getvalue('Scale' + s1[5], s1, 0)) / 100.0);\n` +
      `        }\n` +
      `        if (s1.startsWith('iwid')) {\n` },
];
```

And three more in the same array: the scale's initial value and the coordinates. The base scales x, y, z last (`web/ContO.js:223-225, 249-251`: `trunc(fr(fr(fr(p * div) * iwid) * scale))`) and leaves the wheel's width and height (`w` 4, 5) unscaled. With scale 1, `fr(x * 1.0) === x` for any float32 `x`, so stock models stay bit-identical.

```js
  { name: 'newcar-scale-init', file: 'ContO.js',
    find: `    let bool2 = false;\n    try {\n      let datainputstream = new DataInputStream(new ByteArrayInputStream(abyte0));\n`,
    replace: `    let bool2 = false;\n    this.scl = [1.0, 1.0, 1.0];   // ext-patch newcar-scale-init\n    try {\n      let datainputstream = new DataInputStream(new ByteArrayInputStream(abyte0));\n` },
  { name: 'newcar-scale-p', file: 'ContO.js',
    find: `            ai[i] = trunc((fr((fr(fr(this.getvalue('p', s1, 0)) * this.div)) * this.iwid)));\n` +
      `            ai2[i] = trunc((fr(fr(this.getvalue('p', s1, 1)) * this.div)));\n` +
      `            ai3[i] = trunc((fr(fr(this.getvalue('p', s1, 2)) * this.div)));\n`,
    replace: `            // ext-patch newcar-scale-p: x, y, z times ScaleX/Y/Z (1 but on a new car)\n` +
      `            ai[i] = trunc(fr((fr((fr(fr(this.getvalue('p', s1, 0)) * this.div)) * this.iwid)) * this.scl[0]));\n` +
      `            ai2[i] = trunc(fr((fr(fr(this.getvalue('p', s1, 1)) * this.div)) * this.scl[1]));\n` +
      `            ai3[i] = trunc(fr((fr(fr(this.getvalue('p', s1, 2)) * this.div)) * this.scl[2]));\n` },
  { name: 'newcar-scale-w', file: 'ContO.js',
    find: `          this.keyx[j] = trunc((fr(fr(this.getvalue('w', s1, 0)) * this.div)));\n` +
      `          this.keyz[j] = trunc((fr(fr(this.getvalue('w', s1, 2)) * this.div)));\n` +
      `          j = i32(j + 1);\n` +
      `          wheels.make(this.m, this.t, this.p, this.npl, trunc((fr((fr(fr(this.getvalue('w', s1, 0)) * this.div)) * this.iwid))), trunc((fr(fr(this.getvalue('w', s1, 1)) * this.div))), trunc((fr(fr(this.getvalue('w', s1, 2)) * this.div))), `,
    replace: `          // ext-patch newcar-scale-w: the wheel's position times ScaleX/Y/Z; its size not (as the base)\n` +
      `          this.keyx[j] = trunc(fr((fr(fr(this.getvalue('w', s1, 0)) * this.div)) * this.scl[0]));\n` +
      `          this.keyz[j] = trunc(fr((fr(fr(this.getvalue('w', s1, 2)) * this.div)) * this.scl[2]));\n` +
      `          j = i32(j + 1);\n` +
      `          wheels.make(this.m, this.t, this.p, this.npl, trunc(fr((fr((fr(fr(this.getvalue('w', s1, 0)) * this.div)) * this.iwid)) * this.scl[0])), trunc(fr((fr(fr(this.getvalue('w', s1, 1)) * this.div)) * this.scl[1])), trunc(fr((fr(fr(this.getvalue('w', s1, 2)) * this.div)) * this.scl[2])), ` },
```

The `newcar-scale-parse` patch reads `code`, the constructor's model-code parameter; confirm its name at `ContO.js:229` (`if ((code < 78) || ...`) — it is in scope for the whole parse loop. The verification is Step 4: `ext/ContO.test.js` (129 models, every field vs the jar) and `ext/draw.test.js` must still pass.

- [ ] **Step 4: Apply and run everything**

Run: `node web/tools/ext-patches.mjs && cd web && node --test`
Expected: all tests PASS, including `ext/ContO.test.js` and `ext/draw.test.js` (stock models bit for bit), `ext/patches.test.js`, and the two new tests.

- [ ] **Step 5: Commit**

```bash
git add web/tools/ext-patches.mjs web/ext/ContO.js web/ext/GameSparker.js web/ext/newcars-grow.test.js
git commit -m "Extended new cars: tables grow on construction; new models are cars and honour Scale"
```

---

### Task 5: The identity rewrite

**Files:**
- Create: `web/tools/ext-ident.mjs`
- Modify (by the tool): `web/ext/Madness.js`, `xtGraphics.js`, `Control.js`, `GameSparker.js`, `Contva.js`
- Test: `web/ext/ident.test.js`

**Interfaces:**
- Consumes: `id` from `web/ext/newcars.js`.
- Produces: generated classes in which `CAR <op> <integer literal>` reads `id(CAR) <op> <literal>`.

Measured (2026-09-26, the regex below run over the files): 1,110 comparisons against a literal: Madness 86, xtGraphics 535, Control 480, GameSparker 5, Contva 4; none left after one pass, no double wrap. Car-vs-car comparisons (`this.sc[k] === this.sc[l]`, `cn === whichcar`) are NOT rewritten: they ask "same car?", an index question.

- [ ] **Step 1: Write the failing test**

```js
// web/ext/ident.test.js
import { test } from 'node:test';
import assert from 'node:assert';
import fs from 'node:fs';
import { FILES, RAW, rewrite } from '../tools/ext-ident.mjs';

test('no car-number comparison against a literal is left unwrapped', () => {
  for (const f of FILES) {
    const src = fs.readFileSync(new URL(f, import.meta.url), 'utf8');
    const left = [...src.matchAll(RAW)].map((m) => m[0]);
    assert.deepStrictEqual(left, [], `${f}: run node web/tools/ext-ident.mjs`);
    assert.match(src, /import \{ id \} from '\.\/newcars\.js';/, f);
  }
});

test('the rewrite wraps the car side only, leaves car-vs-car alone, and is idempotent', () => {
  const src = "if ((this.cn === 36) || (madness.cn >= 23) || (amadness[k8].cn === 12) || (this.sc[k] === this.sc[l]) || (this.sc[0] <= 38)) {}";
  const once = rewrite(src);
  assert.strictEqual(once, "if ((id(this.cn) === 36) || (id(madness.cn) >= 23) || (id(amadness[k8].cn) === 12) || (this.sc[k] === this.sc[l]) || (id(this.sc[0]) <= 38)) {}");
  assert.strictEqual(rewrite(once), once);
});
```

- [ ] **Step 2: Run it to verify it fails**

Run: `cd web && node --test ext/ident.test.js`
Expected: FAIL, module not found.

- [ ] **Step 3: Write the tool**

```js
// web/tools/ext-ident.mjs
// The identity half of Extended's new cars (web/ext/newcars.js): every comparison of a
// car number against an integer literal asks id(car), so a new car answers as its
// donor; tables keep reading the real index. Stock cars: id(c) === c, same race.
//
//   node web/tools/ext-ident.mjs           apply (after every J2JS regeneration)
//   node web/tools/ext-ident.mjs --check   exit 1 if anything is left unwrapped
//   node web/tools/ext-ident.mjs --audit   list literal comparisons on OTHER names
//                                          in functions that are passed a car number
import fs from 'node:fs';

export const FILES = ['Madness.js', 'xtGraphics.js', 'Control.js', 'GameSparker.js', 'Contva.js'];
const CAR = String.raw`(?:(?:this|madness|usermad|amadness\[[^\]]+\]|madness\[[^\]]+\])\.cn|(?:this|xtgraphics|this\.xt)\.sc\[[^\]]+\]|this\.lastcar)`;
// a car expression not already inside id(...) and not the tail of a longer name
// (`amadness[k].cn` must not also match as `madness[k].cn`), compared with an integer literal
export const RAW = new RegExp(String.raw`(?<!id\()(?<![\w.$])(${CAR})(\s*(?:===|!==|<=|>=|<|>)\s*-?\d+\b)`, 'g');
const IMPORT = `import { id } from './newcars.js';   // ext-ident (web/tools/ext-ident.mjs)\n`;

export function rewrite(src) {
  let out = src.replace(RAW, 'id($1)$2');
  if (!out.includes(IMPORT)) out = out.replace(/^(import [^\n]*\n)/m, `$1${IMPORT}`);
  return out;
}

if (import.meta.url === `file://${process.argv[1].replace(/\\/g, '/')}` || process.argv[1]?.endsWith('ext-ident.mjs')) {
  const mode = process.argv[2];
  let bad = 0;
  for (const f of FILES) {
    const url = new URL(`../ext/${f}`, import.meta.url);
    const src = fs.readFileSync(url, 'utf8');
    const left = src.match(RAW)?.length ?? 0;
    if (mode === '--check') { if (left) { console.log(`${f}: ${left} unwrapped`); bad++; } continue; }
    if (mode === '--audit') continue;   // see Step 5
    const out = rewrite(src);
    if (out !== src) fs.writeFileSync(url, out);
    console.log(`${f}: ${left} wrapped`);
  }
  process.exit(bad ? 1 : 0);
}
```

Note: the test's sample has no `import` line, so `rewrite` leaves it without one; the real files each start with `import ... from '../java.js';`, which is where the import goes (after the first import line).

- [ ] **Step 4: Apply, then check the stock race hashes did not move**

Run: `node web/tools/ext-ident.mjs && cd web && node --test`
Expected: printed counts 86 / 535 / 480 / 5 / 4; all tests PASS (`ext/trace.test.js` replays captured `drive()`/`preform()` calls: unchanged). Then run the Task 1 Step 1 selftests in the browser: both hashes must equal the baseline. A changed hash means a rewritten comparison was an index in disguise: bisect by reverting one file at a time (`git checkout web/ext/<file>` and re-apply the others).

- [ ] **Step 5: Audit the car numbers that travel as plain locals.** Some functions take a car number as a parameter and compare it against literals (e.g. `xtGraphics.js:18282` `a === 34`, `:18661` `whichname === 38`). The regex does not see those. Find them:

Run: `grep -nE "\b(a|whichname|whichcar|bestcar2?|rightcar|carno|car)\s*(===|<=|>=|<|>)\s*[0-9]+" web/ext/{xtGraphics,Madness,Control,GameSparker}.js | wc -l`

For each function hit, read its call sites. If it is called with a race car's number (`sc[k]`, `.cn`) AND the comparison selects a special or quirk, wrap the argument at the call site as `id(...)`, in a named patch in `ext-patches.mjs`. If it is a menu/loop index (car select, career lists), leave it. Write every decision in WORK.md (Task 9). Expected: a handful, mostly menu code.

- [ ] **Step 6: Audit assignments driven by identity.** An identity check that ASSIGNS a car number would turn a new car into a stock one:

Run: `grep -nE "(\.sc\[[^]]+\]|\.cn|lastcar) = [0-9]+" web/ext/{xtGraphics,Madness,Control,GameSparker}.js`

Each hit inside race code (not `carselect`/career menus) must be read: if it runs for the player's car in Free Play, add a guard patch (`if (!isNew(...))`). Expected: all hits are in the career car select ordering (`xtGraphics.js:16729-16760`), which Free Play does not reach (normal mode steps with `++sc`/`--sc`).

- [ ] **Step 7: Document regeneration.** In `web/ext/README.md`, after the `node web/tools/ext-patches.mjs` block, add:

```sh
node web/tools/ext-ident.mjs          # new cars: car-number comparisons ask id() (ident.test.js fails until you do)
```

- [ ] **Step 8: Commit**

```bash
git add web/tools/ext-ident.mjs web/ext/ident.test.js web/ext/*.js web/ext/README.md
git commit -m "Extended new cars: car-number comparisons ask the car's identity"
```

---

### Task 6: Racing a new car

**Files:**
- Modify: `web/ext/race.js` (new cars loaded before `gs.run()`, models after `loadbase`)
- Create: `web/tools/browser-newcar.mjs`

**Interfaces:**
- Consumes: `carFromRad` (Task 2), `setNewCars`, `NEW_BASE` (Task 1), `readCar`, `listAll` from `web/carstore.js`.
- Produces: `?ext=free|classic` races with new cars at `NEW_BASE + i`; the store key `nfm.ext.newcars` = `[{ name, donor }]` (written by Task 8); dev switch `?newcar=<name>:<donor>` (counts only in developer mode / serve.py, like every URL switch).

- [ ] **Step 1: Write the failing browser check**

```js
// web/tools/browser-newcar.mjs
// New cars in a real browser: stock hashes unchanged, a new car races (moves, finite).
// Run with the local server on :8123: node web/tools/browser-newcar.mjs
import { spawn } from 'node:child_process';
import { tmpdir } from 'node:os';
import { join } from 'node:path';
import { setTimeout as sleep } from 'node:timers/promises';
import { attach } from './cdp.mjs';

const BASELINE = { classic: '271c3367', career: '353ea4bf' };   // Task 1 Step 1
const chrome = process.platform === 'win32' ? 'C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe' : 'chromium';
const port = 9400 + process.pid % 500;
const browser = spawn(chrome, ['--headless=new', '--no-sandbox', '--enable-unsafe-swiftshader', '--mute-audio',
  `--remote-debugging-port=${port}`, `--user-data-dir=${join(tmpdir(), `nfm-newcar-${process.pid}`)}`, 'about:blank'], { stdio: 'ignore' });

async function selftest(tab, query) {
  await tab.send('Page.navigate', { url: `http://localhost:8123/web/main.html?${query}` });
  for (let i = 0; i < 240; i++) {
    const t = await tab.evaluate(`document.getElementById('log')?.textContent || ''`);
    const m = /selftest \d+ ticks interp=\d: ([0-9a-f]+)\s+car0 (-?\d+),(-?\d+)/.exec(t);
    if (m) return { hash: m[1], x: +m[2], z: +m[3] };
    await sleep(500);
  }
  throw new Error(`no selftest line for ${query}`);
}

let tab, fail = 0;
const check = (ok, msg) => { console.log(`${ok ? 'PASS' : 'FAIL'}: ${msg}`); if (!ok) fail++; };
try {
  tab = await attach(port, 'about:blank');
  await tab.send('Page.enable');
  // the store populated, as the launcher leaves it: stock races must not notice
  await tab.send('Page.addScriptToEvaluateOnNewDocument', {
    source: `localStorage.setItem('nfm.ext.newcars', JSON.stringify([{ name: 'Simple Car', donor: 30 }]))` });
  const c = await selftest(tab, 'ext=classic&stage=4&car=30&selftest=400');
  check(c.hash === BASELINE.classic, `classic stock hash ${c.hash}`);
  const k = await selftest(tab, 'ext=career&stage=3&car=5&selftest=600');
  check(k.hash === BASELINE.career, `career stock hash ${k.hash} (new cars in storage)`);
  for (const stage of [4, 11]) {      // an Extended stage, then an NFM 2-classic one
    const n = await selftest(tab, `ext=classic&stage=${stage}&car=200&newcar=Simple%20Car:30&selftest=400`);
    check(Number.isFinite(n.x) && Number.isFinite(n.z) && Math.abs(n.z + 760) > 200, `new car on stage ${stage}: car0 ${n.x},${n.z}`);
  }
} finally {
  tab?.close();
  browser.kill();
}
process.exit(fail ? 1 : 0);
```

- [ ] **Step 2: Run it to verify it fails**

Run: `python web/tools/serve.py 8123` (background), then `node web/tools/browser-newcar.mjs`
Expected: the two stock checks PASS; the new-car checks FAIL (car 200 has no model: a throw in loadstage, "no selftest line").

- [ ] **Step 3: Load the new cars in `race.js`.** After the `nfm2` block (`race.js:104-111`) and before `System.live = true;`, add:

```js
  // ---- Extended new cars (newcars.js): Car Maker cars after the 39, Free Play only ----
  // The launcher's list (nfm.ext.newcars: [{ name, donor }]), or ?newcar=name:donor.
  let newList = [];
  try { newList = JSON.parse(localStorage.getItem('nfm.ext.newcars') || '[]'); } catch { /* none */ }
  if (params.has('newcar')) {
    const [name, donor] = params.get('newcar').split(':');
    newList = [{ name, donor: +donor }];
  }
  const newcars = [];
  if (mode !== 'career') {
    for (const { name, donor } of newList) {
      const text = await readCar(name);
      const car = text && carFromRad(name, text, Number.isInteger(donor) && donor >= 0 && donor < 39 ? donor : undefined);
      if (car) newcars.push(car); else console.log(`new car "${name}" skipped: not a car Extended can load`);
    }
  }
  setNewCars(newcars);
```

with imports at the top of `race.js`:

```js
import { readCar } from '../carstore.js';
import { NEW_BASE, setNewCars, newCars } from './newcars.js';
import { carFromRad } from './newcars-stats.js';
```

- [ ] **Step 4: Add the models after `loadbase`.** Replace the `if (baseStage || free) { gs.loadbase = ... }` block (`race.js:252-258`) with a hook that always runs:

```js
  gs.loadbase = function (aconto, medium, trackers, xtg) {
    const r = GameSparker.prototype.loadbase.call(this, aconto, medium, trackers, xtg);
    if (baseStage || free) appendModels(aconto, (baseStage || nfm2).zip, medium, trackers, xtg);
    // new cars at NEW_BASE + i: ContO treats those codes as cars (ext-patch newcar-isacar)
    newCars().forEach((car, i) => {
      const buf = Int8Array.from(car.text, (ch) => ch.charCodeAt(0));
      aconto[NEW_BASE + i] = new ContO(0, buf, medium, trackers, xtg, NEW_BASE + i);
    });
    return r;
  };
```

Check `ContO` is already imported in `race.js`; if not, `import { ContO } from './ContO.js';`.

- [ ] **Step 5: Let the selftest race a new car.** `race.js:198` sets `xt.lastcar = +params.get('car')` and `:201` sends classic cars below 23 to 38 — a new car (200) passes that. Confirm `?car=200` survives to `xt.sc[0]` (log `xt.sc[0]` once if the check fails with car0 at the start line).

- [ ] **Step 6: Run the checks**

Run: `node web/tools/browser-newcar.mjs && cd web && node --test`
Expected: 4 PASS lines, exit 0; unit tests PASS.

- [ ] **Step 7: See it.** Open `http://localhost:8123/web/main.html?ext=classic&stage=4&car=200&newcar=Simple%20Car:13` (donor 13 = Stampede, teleport per `Madness.js:3258`), race, fill the special bar, press the special key. Screenshot the car (right size: compare with the same car in the base game's `?mycar=Simple%20Car`) and the special effect. Record what you saw in WORK.md.

- [ ] **Step 8: Commit**

```bash
git add web/ext/race.js web/tools/browser-newcar.mjs
git commit -m "Extended new cars: load them and race one"
```

---

### Task 7: Free Play's car select reaches the new cars

**Files:**
- Modify: `web/ext/menus.js` (`loadPick`, `clampCarArrows`, the `xt.carselect` wrapper)
- Test: `web/menus.test.js` or a new `web/ext/menus-newcars.test.js`

**Interfaces:**
- Consumes: `nextCar`, `lastCar`, `NEW_BASE`, `newCars` (Task 1).
- Produces: `loadPick()` returns `car` as an index valid now (new cars remembered by `carName`); `carStepFix(control, car)`.

The jar's normal-mode car select does `++sc[0]` / `--sc[0]` on right/left (`xtGraphics.js:16720-16750`, the `!this.careermode` branches). The wrapper must turn 38 -> 39 into 38 -> 200 and 200 -> 199 into 200 -> 38, and stop at `lastCar()`.

- [ ] **Step 1: Write the failing tests**

```js
// web/ext/menus-newcars.test.js
import { test } from 'node:test';
import assert from 'node:assert';
import { setNewCars, NEW_BASE } from './newcars.js';
import { afterArrow, pickCar } from './menus.js';

test('after the jar steps, 39 becomes the first new car and 199 becomes 38', () => {
  setNewCars([{ name: 'A', donor: 30 }]);
  assert.strictEqual(afterArrow(39), NEW_BASE);
  assert.strictEqual(afterArrow(NEW_BASE - 1), 38);
  assert.strictEqual(afterArrow(NEW_BASE + 1), NEW_BASE);   // past the last new car
  assert.strictEqual(afterArrow(12), 12);
  setNewCars([]);
  assert.strictEqual(afterArrow(39), 38);
});

test('a pick naming a missing new car falls back', () => {
  setNewCars([{ name: 'A', donor: 30 }]);
  assert.strictEqual(pickCar({ car: NEW_BASE, carName: 'A' }), NEW_BASE);
  assert.strictEqual(pickCar({ car: NEW_BASE, carName: 'Gone' }), 38);
  assert.strictEqual(pickCar({ car: 7 }), 7);
  setNewCars([{ name: 'B', donor: 1 }, { name: 'A', donor: 30 }]);
  assert.strictEqual(pickCar({ car: NEW_BASE, carName: 'A' }), NEW_BASE + 1);   // by name, not slot
});
```

- [ ] **Step 2: Run to verify failure**

Run: `cd web && node --test ext/menus-newcars.test.js`
Expected: FAIL, `afterArrow` is not exported.

- [ ] **Step 3: Implement in `menus.js`**

```js
import { NEW_BASE, lastCar, newCars } from './newcars.js';

/** The jar's normal-mode arrows are ++/-- on sc[0]: hop the gap between 38 and the new cars. */
export function afterArrow(c) {
  if (c === 39) return newCars().length ? NEW_BASE : 38;
  if (c === NEW_BASE - 1) return 38;
  return Math.min(c, lastCar());
}

/** A remembered pick: stock cars by index, new cars by name (their index moves). */
export function pickCar(p) {
  if (Number.isInteger(p.car) && p.car >= 0 && p.car < 39) return p.car;
  const k = newCars().findIndex((c) => c.name === p.carName);
  return k >= 0 ? NEW_BASE + k : 38;
}
```

In `loadPick`, replace the `car:` line with `car: pickCar(p), carName: p.carName,`; in `clampCarArrows`, replace `EXT_CARS.length - 1` with `lastCar()`; in the `xt.carselect` wrapper, right after `own.carselect.call(this, control_, ...rest);` add `if (!career) this.sc[0] = this.lastcar = afterArrow(this.sc[0]);`; where the pick is saved (`pick.car = this.sc[0];`) add `pick.carName = newCars()[this.sc[0] - NEW_BASE]?.name;`.

- [ ] **Step 4: Run tests**

Run: `cd web && node --test`
Expected: PASS.

- [ ] **Step 5: See it.** Launcher -> Extended Edition -> Free Play with `nfm.ext.newcars` set (DevTools: `localStorage.setItem('nfm.ext.newcars', '[{"name":"Simple Car","donor":13}]')`): press right past DR Monstaa; the car select shows "Simple Car" with its model; Enter; a stage; the race starts with it. Reload: the pick is still Simple Car.

- [ ] **Step 6: Commit**

```bash
git add web/ext/menus.js web/ext/menus-newcars.test.js
git commit -m "Extended new cars: Free Play's car select steps past car 38"
```

---

### Task 8: The launcher's "New cars" page

**Files:**
- Modify: `web/launcher.js` (EXT rows, a page `extcars`, V entries per car), `index.html` (page markup + CSS display rule), `web/i18n.js` (Spanish)
- Test: `web/launcher-newcars.test.js` for the pure store helpers

**Interfaces:**
- Consumes: `listAll()` from `web/carstore.js`; `EXT_CARS` from `web/ext/catalog.js`.
- Produces: `localStorage['nfm.ext.newcars'] = [{ name, donor }]` (only cars switched on); `readNewCarStore()`, `writeNewCarStore(list)`, `donorChoices()` in a small module `web/ext/newcars-store.js`.

- [ ] **Step 1: Write the failing test**

```js
// web/launcher-newcars.test.js
import { test } from 'node:test';
import assert from 'node:assert';
import { parseStore, donorChoices, toggle } from './ext/newcars-store.js';

test('the store keeps valid entries only', () => {
  assert.deepStrictEqual(parseStore('[{"name":"A","donor":30},{"name":"","donor":1},{"name":"B","donor":99},"x"]'),
    [{ name: 'A', donor: 30 }]);
  assert.deepStrictEqual(parseStore('not json'), []);
});

test('donor choices: off, then the 39 stock cars', () => {
  const d = donorChoices();
  assert.strictEqual(d.length, 40);
  assert.strictEqual(d[0].donor, -1);
  assert.strictEqual(d[39].name, 'DR Monstaa');
});

test('choosing a donor switches a car on; choosing off removes it', () => {
  let s = toggle([], 'A', 13);
  assert.deepStrictEqual(s, [{ name: 'A', donor: 13 }]);
  s = toggle(s, 'A', 30);
  assert.deepStrictEqual(s, [{ name: 'A', donor: 30 }]);
  assert.deepStrictEqual(toggle(s, 'A', -1), []);
});
```

- [ ] **Step 2: Run to verify failure**

Run: `cd web && node --test launcher-newcars.test.js`
Expected: FAIL, module not found.

- [ ] **Step 3: Implement the store module**

```js
// web/ext/newcars-store.js
// The launcher's choice of new cars for Extended's Free Play: [{ name, donor }], one entry
// per car switched on; the donor (0-38) lends its special power (newcars.js).
import { EXT_CARS } from './catalog.js';

export const KEY = 'nfm.ext.newcars';
export function parseStore(text) {
  let a;
  try { a = JSON.parse(text || '[]'); } catch { return []; }
  return Array.isArray(a) ? a.filter((e) => e && typeof e.name === 'string' && e.name
    && Number.isInteger(e.donor) && e.donor >= 0 && e.donor < EXT_CARS.length).map(({ name, donor }) => ({ name, donor })) : [];
}
export const donorChoices = () => [{ donor: -1, name: 'Off' }, ...EXT_CARS.map((name, donor) => ({ donor, name }))];
export function toggle(list, name, donor) {
  const rest = list.filter((e) => e.name !== name);
  return donor < 0 ? rest : [...rest, { name, donor }];
}
export function readNewCarStore() { try { return parseStore(localStorage.getItem(KEY)); } catch { return []; } }
export function writeNewCarStore(list) { try { localStorage.setItem(KEY, JSON.stringify(list)); } catch { /* private mode */ } }
```

Use `readNewCarStore()` in `race.js` Task 6 Step 3 instead of the inline `JSON.parse` (it validates).

- [ ] **Step 4: The page.** In `index.html`, next to `page-ext`, add:

```html
<!-- 1d : Extended Edition -> New cars: Car Maker cars for Free Play, each with its donor -->
<div class="page" id="page-extcars">
  <div class="wrap" style="width:min(40rem,94vw)">
    <div class="phead"><span>New cars</span><span>Esc — back</span></div>
    <ul class="rows" id="extcars-rows"></ul>
  </div>
</div>
```

and the display rule beside `body[data-page="set"]` (index.html:59): `body[data-page="extcars"] #page-extcars { display: flex; }`.

In `web/launcher.js`:
- `const EXT = [['free', 'Free Play'], ['career', 'Career Mode'], ['cars', 'New cars']];`
- `PAGE_IDS` gets `'extcars'`; `BACK` gets `extcars: 'ext'`; `HINTS.extcars = HINTS.opts`.
- In `fire()`, `case 'ext':` becomes `return void (arg === 'cars' ? openNewCars() : startRace(null, { ext: arg }));`
- Add:

```js
import { donorChoices, readNewCarStore, toggle, writeNewCarStore } from './ext/newcars-store.js';
import { listAll } from './carstore.js';

/** Extended Edition -> New cars: one row per Car Maker car, ◂ ▸ picks its donor (or Off). */
async function openNewCars() {
  const names = await listAll();
  const choices = donorChoices();
  const rows = names.map((name, i) => {
    const k = `nc${i}`;
    V[k] = {
      list: () => choices,
      get: () => { const e = readNewCarStore().find((c) => c.name === name); return e ? e.donor + 1 : 0; },
      set: (j) => writeNewCarStore(toggle(readNewCarStore(), name, choices[j].donor)),
      text: () => { const d = choices[V[k].get()]; return d.donor < 0 ? tr('Off') : `${tr('special of')} ${d.name}`; },
    };
    return `<li class="item orow" data-row="${k}"><span class="slabel">${name}</span>${valueBits(k)}</li>`;
  });
  $('extcars-rows').innerHTML = (rows.length ? rows.join('') : `<li class="item orow"><span class="slabel">${tr('No Car Maker cars yet')}</span></li>`)
    + `<li class="item orow" data-act="back" style="justify-content:center"><span class="label" style="flex:none">${tr('Done')}</span></li>`;
  refreshItems('extcars');
  goPage('extcars');
  paintValues();
}
```

(`tr` is already imported in launcher.js if Spanish is wired there; otherwise import it from `./i18n.js`.)

- [ ] **Step 5: Spanish.** In `web/i18n.js`'s Spanish dictionary add: `'New cars': 'Autos nuevos'`, `'Off': 'Apagado'`, `'special of': 'especial de'`, `'No Car Maker cars yet': 'Todavía no hay autos del Car Maker'`. Run `cd web && node --test i18n.test.js`.

- [ ] **Step 6: Run everything**

Run: `cd web && node --test`
Expected: PASS.

- [ ] **Step 7: See it.** Launcher -> Extended Edition -> New cars: Simple Car and Example, MAX Revenge listed; ◂ ▸ cycles Off / special of Remington ... DR Monstaa; Esc; Free Play; the car select reaches them. In Spanish too (Settings -> Language).

- [ ] **Step 8: Commit**

```bash
git add web/ext/newcars-store.js web/launcher-newcars.test.js web/launcher.js index.html web/i18n.js web/ext/race.js
git commit -m "Extended new cars: the launcher's New cars page picks each car's donor"
```

---

### Task 9: Records

**Files:**
- Modify: `TASKS.md`, `WORK.md`, `web/ext/README.md`

- [ ] **Step 1: TASKS.md.** In the Extended section, replace the "Car Maker cars in Extended" item's plan ("load the .rad geometry at index 39+") with the done entry: new cars at 200+, identity split, Free Play only; add open items: career mode for new cars (save by name; `career-save.js` `CARS = 39`), the functions left unwrapped by the Task 5 Step 5 audit, and per-table calibration of NFM 2 vs Extended balance (`dammult`, `maxmag`, `swits`, `grip` differ in ~half the NFM 2 cars).

- [ ] **Step 2: WORK.md** (append, one line each):
- Model index 39 is `road`: cars 0-38, pieces 39-77, beast `cn + 78`, scenery to 128, stagecompat 129-196; new cars at 200.
- Extended's ContO has no ScaleX/Y/Z; Car Maker cars use them (Simple Car: 145) -- ext-patch newcar-scale, new codes only.
- Stock Extended cars: the 13 `*reset`/`*2`/`nitro*` tables equal their source tables; newcars-grow mirrors them.
- The car number has two jobs, index and identity; ext-ident wraps the 1,110 literal comparisons as `id()`; car-vs-car comparisons stay index.
- What Task 5's audits found, and what Task 6 Step 7 showed (size, special).

- [ ] **Step 3: Commit**

```bash
git add TASKS.md WORK.md web/ext/README.md
git commit -m "Extended new cars: records"
```

- [ ] **Step 4: Deploy.** `./deploy.sh` needs `rsync`, which this Windows machine lacks (TASKS.md). Tell the user the change is not deployed and why, unless rsync has been installed since.
