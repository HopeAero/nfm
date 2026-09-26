# Car Maker: Extended tab Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** One car, two games: the Car Maker gains an "Extended" tab whose choices are stored as inert `ext*` lines in the same `.rad`, and every Car Maker car shows up in Extended's Free Play by itself.

**Architecture:** One shared module, `web/ext/extlines.js`, reads and writes the `ext*` lines; the Car Maker tab writes them through it and `carFromRad` reads them through it (donor, own stats/handling sliders substituted into the text `CarDefine.loadstat` sees, health/damage multipliers after it). Extended's race loads every name `carstore.listAll()` returns in Free Play; the launcher's "New cars" page and its store are deleted. A static catalog `web/ext/specials.js` holds the 39 special descriptions, pinned to `xtGraphics.carselect` by a test that evaluates the source block.

**Tech Stack:** plain ES modules, `node --test` (run from `web/`), the base port's `CarDefine`/`ContO`, the Car Maker page (`web/careditor.html` + `web/careditor/editor.js`), `tr()` from `web/i18n.js`, the CDP browser check `web/tools/browser-newcar.mjs`.

**Spec:** `docs/superpowers/specs/2026-09-26-ext-carmaker-design.md` (read it with this plan). Builds on `docs/superpowers/plans/2026-09-26-ext-new-cars.md`.

## Rulings that refine the spec (measured while planning, 2026-09-26)

1. **No `exthandling` line.** `handling()` feeds only `CarDefine.dishandle` (a display bar, `web/CarDefine.js:162-172`), which is not in `STAT_FIELDS` and Extended never reads; the web Car Maker has no control for it. Writing it would be an inert copy.
2. **`extphysics` holds the 11 handling values only** (`physics()` indexes 0-10: the sliders on the Physics tab, slot 4 "Empty" kept so indexes line up). The crash look (11-13), engine (14) and crash calibration `actmag` (15) stay shared with NFM 2: `actmag` sets `maxmag` in `loadstat` (`web/CarDefine.js:445`), so a stale copy would silently change health after the user recalibrates. Health and damage in Extended have their own multipliers instead.
3. **Free Play only, literally.** `race.js` loads Car Maker cars when `free` (Extended Free Play) or when `?newcar=` is given, not in `?ext=classic`. Stock selftests (which run classic) then load no new car, so their hashes cannot move — and `listAll()` always returns the shipped `Simple Car`, so under the old `mode !== 'career'` rule every classic race would now grow its tables.
4. `?newcar=name:donor` stays as a developer switch; the donor after `:` overrides whatever the `.rad` says (`car.donor = donor` after `carFromRad`), so `browser-newcar.mjs` keeps working unchanged.
5. Car 21 (Agent Racer) draws no special description in the car select (the jar's own gap): its catalog entry is `[]` and the tab shows "No description in the game."

## Global Constraints

- Branch `ext-carmaker`, from `ext-new-cars` (spec §6).
- `.rad` lines exactly: `extspecial(n)` 0–38; `extstat(a,b,c,d,e)`; `extphysics(p0..p10)`; `exthealth(p)` percent 50–300; `extdamage(p)` percent 50–200. Missing → fallback; out-of-range or unparsable → **ignored, never clamped** (spec §1).
- `extstat` and `extphysics` go together: written both or neither; honoured only when both are present and valid.
- Defaults: donor by class (`defaultDonor`), health 100 %, damage 100 %.
- `web/ext/{xtGraphics,Madness,ContO,...}.js` are GENERATED — this plan edits none of them (it only reads `xtGraphics.js` in a test).
- Assets in `data/`, `mycars/`, `stages/` are not modified.
- Code and comments in English; every visible string goes through `tr()` with a Spanish entry (`web/i18n-careditor.js` for the Car Maker; `web/i18n-ext.js` already holds the special descriptions).
- Stock races unchanged: `node web/tools/browser-newcar.mjs` prints `PASS: classic stock hash: 271c3367`.
- `cd web && node --test` passes after every task.
- Opponents never race new cars; the career never loads them (spec "Out of scope").

## Review Focus

1. **`extspecial` that is not a stock car number** (`extspecial(-1)`, `(39)`, `(13.5)`, `(abc)`, `()`): donor by class, no crash. → Task 1 test `invalid ext values are reported and ignored`, Task 2 test `an invalid extspecial falls back to the class donor`.
2. **Choosing Own on a car with no `stat()`/`physics()` line** (a new car before its stats are set): `extstat`/`extphysics` get the same defaults the Stats/Physics tabs show (`DEFAULT_STATS`, `DEFAULT_PHYSICS`), not `NaN` or an empty line. → Task 1 test `own copies defaults when the car has no stat or physics line`.
3. **Editing NFM 2's stats after choosing Own**: Extended keeps its own copy; NFM 2's change does not leak into Extended and vice versa. → Task 2 test `own stats are independent of stat()`.
4. **A Car Maker car saved under a stock car's name** (saving "Tornado Shark" from the base models makes your own "Tornado Shark"): it is a new car in Extended; a remembered stock pick (by index) still opens the stock car, a remembered new pick (by name) opens the new one. → Task 3 test `a new car named like a stock car does not steal the stock pick`.
5. **Storage unavailable when pressing Try in Extended** (private mode: `localStorage`/`sessionStorage` throw) or a garbage `nfm.ext.free`: the car still saves and the launcher opens; the pick falls back to defaults. → Task 5 test `tryPick survives garbage and keeps group and stage`.

---

### Task 0: Branch

- [ ] **Step 1: Create the branch**

```bash
git checkout ext-new-cars && git checkout -b ext-carmaker
```

- [ ] **Step 2: Record the baseline**

Run: `cd web && node --test 2>&1 | tail -5`
Expected: `# fail 0`. Note the pass count in the Task 6 notes.

---

### Task 1: `web/ext/extlines.js` — the `ext*` lines

**Files:**
- Create: `web/ext/extlines.js`
- Test: `web/ext/extlines.test.js`

**Interfaces:**
- Consumes: `findLine(text, name)`, `argsOf(line)`, `setLine(text, name, replacement)`, `readStats(text)`, `readPhysics(text)`, `DEFAULT_STATS`, `DEFAULT_PHYSICS` from `web/careditor/rad.js` (pure, no DOM).
- Produces:
  - `HEALTH = { min: 50, max: 300 }`, `DAMAGE = { min: 50, max: 200 }`
  - `readExt(text) → { special: int|null, stat: int[5]|null, phys: int[11]|null, health: int|null, damage: int|null, invalid: string[] }` — `null` = missing or invalid; `invalid` lists the line names present but rejected (`'extspecial'`, `'extstat'`, ...).
  - `removeLine(text, name) → string` — every line whose trim starts with `name(`.
  - `writeSpecial(text, n|null) → string` (`null` removes the line)
  - `setOwn(text, on: boolean) → string`
  - `writeOwnStats(text, stat5) → string`, `writeOwnPhys(text, phys11) → string`
  - `writePercent(text, 'exthealth'|'extdamage', p) → string` (100 removes the line)
  - `forLoadstat(text) → string` — the text with `stat(...)` and physics 0-10 replaced by the own values when both are valid, else `text` unchanged.

- [ ] **Step 1: Write the failing test**

```js
// The ext* lines (extlines.js): the Car Maker's Extended tab writes them, Extended's
// carFromRad reads them, NFM 2 and every parser ignore them (spec §1).
import { test } from 'node:test';
import assert from 'node:assert';
import fs from 'node:fs';
import { readExt, removeLine, writeSpecial, setOwn, writeOwnStats, writeOwnPhys, writePercent,
  forLoadstat } from './extlines.js';
import { readStats, readPhysics, writeStats, DEFAULT_STATS, DEFAULT_PHYSICS } from '../careditor/rad.js';

const simple = fs.readFileSync(new URL('../../mycars/Simple Car.rad', import.meta.url), 'latin1');

test('a car without ext lines reads as all fallbacks', () => {
  assert.deepStrictEqual(readExt(simple),
    { special: null, stat: null, phys: null, health: null, damage: null, invalid: [] });
});

test('each line round-trips', () => {
  let t = writeSpecial(simple, 13);
  t = writePercent(t, 'exthealth', 200);
  t = writePercent(t, 'extdamage', 50);
  t = setOwn(t, true);
  const e = readExt(t);
  assert.strictEqual(e.special, 13);
  assert.strictEqual(e.health, 200);
  assert.strictEqual(e.damage, 50);
  assert.deepStrictEqual(e.stat, readStats(simple));
  assert.deepStrictEqual(e.phys, readPhysics(simple).phys);
  assert.strictEqual(e.phys.length, 11);
  assert.deepStrictEqual(e.invalid, []);
});

test('writing null or 100 removes the line; Same removes both own lines', () => {
  let t = writeSpecial(writePercent(setOwn(simple, true), 'exthealth', 150), 13);
  t = writeSpecial(writePercent(t, 'exthealth', 100), null);
  t = setOwn(t, false);
  assert.doesNotMatch(t, /^\s*ext/m);
  assert.strictEqual(t.replace(/\s+$/, ''), simple.replace(/\s+$/, ''));
});

test('lines the tab does not know survive its writes', () => {
  const t = writeSpecial(simple + '\nfoo(1,2)\n', 5);
  assert.match(t, /^foo\(1,2\)$/m);
});

test('invalid ext values are reported and ignored', () => {
  for (const bad of ['extspecial(-1)', 'extspecial(39)', 'extspecial(13.5)', 'extspecial(abc)', 'extspecial()']) {
    const e = readExt(simple + '\n' + bad + '\n');
    assert.strictEqual(e.special, null, bad);
    assert.deepStrictEqual(e.invalid, ['extspecial'], bad);
  }
  const e = readExt(simple + '\nexthealth(1000)\nextdamage(20)\nextstat(1,2,3)\n');
  assert.strictEqual(e.health, null);
  assert.strictEqual(e.damage, null);
  assert.strictEqual(e.stat, null);
  assert.deepStrictEqual(e.invalid.sort(), ['extdamage', 'exthealth', 'extstat']);
});

test('own copies defaults when the car has no stat or physics line', () => {
  const bare = removeLine(removeLine(simple, 'stat'), 'physics');
  const e = readExt(setOwn(bare, true));
  assert.deepStrictEqual(e.stat, DEFAULT_STATS);
  assert.deepStrictEqual(e.phys, DEFAULT_PHYSICS.phys);
});

test('forLoadstat swaps in the own values only when both lines are valid', () => {
  assert.strictEqual(forLoadstat(simple), simple);
  let t = setOwn(simple, true);
  t = writeOwnStats(t, [200, 160, 120, 100, 100]);
  t = writeOwnPhys(t, [10, 20, 30, 40, 0, 50, 60, 70, 80, 90, 100]);
  const l = forLoadstat(t);
  assert.deepStrictEqual(readStats(l), [200, 160, 120, 100, 100]);
  const p = readPhysics(l), orig = readPhysics(simple);
  assert.deepStrictEqual(p.phys, [10, 20, 30, 40, 0, 50, 60, 70, 80, 90, 100]);
  assert.deepStrictEqual([p.crash, p.engsel, p.actmag], [orig.crash, orig.engsel, orig.actmag]);   // shared with NFM 2
  assert.strictEqual(forLoadstat(removeLine(t, 'extphysics')), removeLine(t, 'extphysics'));   // one alone: ignored
});

test('own stats are independent of stat()', () => {
  const t = writeStats(setOwn(simple, true), [120, 120, 120, 120, 120]);
  assert.deepStrictEqual(readExt(t).stat, readStats(simple));
});
```

- [ ] **Step 2: Run test to verify it fails**

Run: `cd web && node --test ext/extlines.test.js`
Expected: FAIL, `Cannot find module ... extlines.js`

- [ ] **Step 3: Write the implementation**

```js
// The Car Maker's Extended tab, as lines in the same .rad (one car, two games). No parser
// -- base ContO, Extended's ContO, CarDefine, the Car Maker, the Java -- acts on a line
// starting with `e`, so NFM 2 ignores them and only carFromRad (newcars-stats.js) reads
// them. Out-of-range values are ignored, never clamped: the fallback applies.
// Spec: docs/superpowers/specs/2026-09-26-ext-carmaker-design.md
import { findLine, argsOf, setLine, readStats, readPhysics, DEFAULT_STATS, DEFAULT_PHYSICS,
  STAT_MIN, STAT_MAX } from '../careditor/rad.js';

export const HEALTH = { min: 50, max: 300 };
export const DAMAGE = { min: 50, max: 200 };
const PHYS_N = 11;   // physics() 0-10, the handling sliders; crash look, engine, actmag stay shared

// every argument an integer in [lo, hi], and exactly n of them; else null
function ints(text, name, n, lo, hi) {
  const l = findLine(text, name);
  if (!l) return undefined;
  const a = argsOf(l.line);
  if (a.length !== n || !a.every((s) => /^-?\d+$/.test(s))) return null;
  const v = a.map(Number);
  return v.every((x) => x >= lo && x <= hi) ? v : null;
}

export function readExt(text) {
  const invalid = [];
  const get = (name, n, lo, hi) => {
    const v = ints(text, name, n, lo, hi);
    if (v === null) invalid.push(name);
    return v ?? null;
  };
  const special = get('extspecial', 1, 0, 38);
  const stat = get('extstat', 5, STAT_MIN, STAT_MAX);
  const phys = get('extphysics', PHYS_N, 0, 100);
  const health = get('exthealth', 1, HEALTH.min, HEALTH.max);
  const damage = get('extdamage', 1, DAMAGE.min, DAMAGE.max);
  return { special: special && special[0], stat, phys, health: health && health[0], damage: damage && damage[0], invalid };
}

export function removeLine(text, name) {
  return text.split('\n').filter((l) => !l.trim().startsWith(name + '(')).join('\n');
}

export const writeSpecial = (text, n) =>
  (n === null ? removeLine(text, 'extspecial') : setLine(text, 'extspecial', `extspecial(${n})`));

export const writeOwnStats = (text, stat) => setLine(text, 'extstat', `extstat(${stat.join(',')})`);
export const writeOwnPhys = (text, phys) => setLine(text, 'extphysics', `extphysics(${phys.slice(0, PHYS_N).join(',')})`);

/** Own: copy NFM 2's stats and handling as Extended's; Same: drop both copies. */
export function setOwn(text, on) {
  if (!on) return removeLine(removeLine(text, 'extstat'), 'extphysics');
  const stat = readStats(text) || DEFAULT_STATS;
  const phys = (readPhysics(text) || DEFAULT_PHYSICS).phys;
  return writeOwnPhys(writeOwnStats(text, stat), phys);
}

export const writePercent = (text, name, p) => (p === 100 ? removeLine(text, name) : setLine(text, name, `${name}(${p})`));

/** The text CarDefine.loadstat should see for Extended: own stats and handling, when both are valid. */
export function forLoadstat(text) {
  const { stat, phys } = readExt(text);
  const base = readPhysics(text) || DEFAULT_PHYSICS;
  if (!stat || !phys) return text;
  const vals = [...phys, ...base.crash, base.engsel, base.actmag];
  return setLine(setLine(text, 'stat', `stat(${stat.join(',')})`), 'physics', `physics(${vals.join(',')})`);
}
```

- [ ] **Step 4: Run test to verify it passes**

Run: `cd web && node --test ext/extlines.test.js`
Expected: PASS (8 tests). If `'writing null or 100 removes the line…'` fails on whitespace, `setLine` appends with `\n\n` — the test already compares with trailing whitespace trimmed; if a blank line pair remains mid-file, make `removeLine` also drop one empty line directly after a removed line.

- [ ] **Step 5: Commit**

```bash
git add web/ext/extlines.js web/ext/extlines.test.js
git commit -m "Extended Car Maker tab: the ext* lines (read, write, what loadstat sees)"
```

---

### Task 2: `carFromRad` reads the `ext*` lines

**Files:**
- Modify: `web/ext/newcars-stats.js` (`carFromRad`, lines 43-54)
- Modify: `web/ext/newcars-stats.test.js`
- Modify: `web/ext/newcars-grow.test.js` (lines 27, 40, 52, 59: the third argument goes away)
- Modify: `web/ext/race.js` (line 128: the `?newcar=` donor becomes an override)

**Interfaces:**
- Consumes: `readExt`, `forLoadstat` from Task 1.
- Produces: `carFromRad(name, text) → { name, text, stat, cclass, donor } | null` — **two arguments**; `donor` from `extspecial` else `defaultDonor(cclass)`; `stat.maxmag`/`stat.dammult` already multiplied by `exthealth`/`extdamage`. `text` is the original `.rad` (the model is built from it, never from the loadstat text).

- [ ] **Step 1: Write the failing tests** — replace the test `'an explicit donor is kept'` in `web/ext/newcars-stats.test.js` and add:

```js
test('extspecial names the donor', () => {
  assert.strictEqual(carFromRad('Simple Car', simple + '\nextspecial(13)\n').donor, 13);
  assert.strictEqual(carFromRad('Simple Car', simple + '\nextspecial(0)\n').donor, 0);
});

test('an invalid extspecial falls back to the class donor', () => {
  const car = carFromRad('Simple Car', simple + '\nextspecial(77)\n');
  assert.strictEqual(car.donor, defaultDonor(car.cclass));
});

test('own stats and handling change the physics; the model text is untouched', () => {
  const own = setOwn(simple, true);
  const t = writeOwnPhys(writeOwnStats(own, [200, 200, 120, 80, 80]), [100, 100, 100, 0, 0, 0, 0, 0, 0, 100, 100]);
  const a = carFromRad('Simple Car', simple), b = carFromRad('Simple Car', t);
  assert.notDeepStrictEqual(b.stat.acelf, a.stat.acelf);
  assert.notDeepStrictEqual(b.stat.swits, a.stat.swits);
  assert.notStrictEqual(b.stat.grip, a.stat.grip);
  assert.strictEqual(b.text, t);
  // Own chosen but untouched: the same numbers as NFM 2
  assert.deepStrictEqual(carFromRad('Simple Car', own).stat, a.stat);
});

test('exthealth and extdamage scale maxmag and dammult', () => {
  const a = carFromRad('Simple Car', simple);
  const b = carFromRad('Simple Car', simple + '\nexthealth(200)\nextdamage(50)\n');
  assert.strictEqual(b.stat.maxmag, Math.trunc(Math.fround(a.stat.maxmag * 2)));
  assert.strictEqual(b.stat.dammult, Math.fround(a.stat.dammult * 0.5));
  const c = carFromRad('Simple Car', simple + '\nexthealth(1000)\n');   // invalid: ignored, not clamped
  assert.strictEqual(c.stat.maxmag, a.stat.maxmag);
});
```

Add to the imports: `import { setOwn, writeOwnStats, writeOwnPhys } from './extlines.js';`

- [ ] **Step 2: Run to verify they fail**

Run: `cd web && node --test ext/newcars-stats.test.js`
Expected: FAIL — `extspecial names the donor` (donor is the class donor), `exthealth…` (maxmag unchanged).

- [ ] **Step 3: Implement** — in `web/ext/newcars-stats.js`, add `import { readExt, forLoadstat } from './extlines.js';` and replace `carFromRad`:

```js
export function carFromRad(name, text) {
  let model;
  try { model = new BaseContO(text, stubMedium(), stubTrackers()); } catch { return null; }
  if (model.errd || model.npl <= 60 || !wheelsOk(model)) return null;
  const ext = readExt(text);
  const cd = new CarDefine(null, null, null, null);
  // Extended's own stats and handling, when the Car Maker's Extended tab set them (extlines.js)
  cd.loadstat(forLoadstat(text), name, model.maxR, model.roofat, model.wh, SLOT);
  if (!cd.names[SLOT]) return null;          // loadstat blanks the name when stat() is missing
  const stat = {};
  const copy = (v) => (v?.length !== undefined ? v.slice() : v);
  for (const f of STAT_FIELDS) stat[f] = copy(cd[f][SLOT]);
  // health and damage taken in Extended, as percentages of NFM 2's (float32, trunc as loadstat does)
  if (ext.health !== null) stat.maxmag = Math.trunc(Math.fround(stat.maxmag * ext.health / 100));
  if (ext.damage !== null) stat.dammult = Math.fround(stat.dammult * ext.damage / 100);
  const cclass = cd.cclass[SLOT];
  return { name, text, stat, cclass, donor: ext.special ?? defaultDonor(cclass) };
}
```

Update the header comment's last line to say the donor and Extended's own values come from the `ext*` lines (extlines.js).

In `web/ext/newcars-grow.test.js` replace each `carFromRad('Simple Car', simple, 30)` with `carFromRad('Simple Car', simple + '\nextspecial(30)\n')`.

In `web/ext/race.js` line 128 replace the `carFromRad(...)` call with:

```js
      const car = text && carFromRad(name, text);
      // ?newcar=name:donor, a developer switch: the donor after ':' overrides the .rad's
      if (car && Number.isInteger(donor) && donor >= 0 && donor < 39) car.donor = donor;
```

- [ ] **Step 4: Run the tests**

Run: `cd web && node --test ext/`
Expected: PASS, including `newcars-grow.test.js` and `newcars-model.test.js`. Check `exthealth…`: `maxmag` of Simple Car must be > 0 (it is `trunc(actmag * n21)`; Simple Car has `actmag` 4412). If `dammult` equality fails by one ulp, the implementation must compute exactly `Math.fround(stat.dammult * ext.damage / 100)` as the test does.

- [ ] **Step 5: Commit**

```bash
git add web/ext/newcars-stats.js web/ext/newcars-stats.test.js web/ext/newcars-grow.test.js web/ext/race.js
git commit -m "Extended new cars: carFromRad reads the ext* lines (special, own stats, health, damage)"
```

---

### Task 3: Every Car Maker car in Free Play; the launcher's New cars page goes

**Files:**
- Modify: `web/ext/race.js:46-50` (imports), `:117-132` (the new-car list)
- Delete: `web/ext/newcars-store.js`, `web/launcher-newcars.test.js`
- Modify: `web/launcher.js:27-28` (imports), `:204` (`EXT`), `:212-213` (`PAGE_IDS`, `BACK`), `:883` (`case 'ext'`), `:902-925` (`openNewCars`)
- Modify: `index.html:279-285` (the `page-extcars` block)
- Modify: `web/i18n.js:56` and `:161`, `web/i18n.test.js:118-124`
- Modify: `web/ext/menus-newcars.test.js` (one test)
- Modify: `web/tools/browser-newcar.mjs:39-41` (comment only)

**Interfaces:**
- Consumes: `listAll()`, `readCar(name)` from `web/carstore.js`; `carFromRad(name, text)` from Task 2.
- Produces: in Free Play, `newCars()` is every loadable car of `listAll()`, in its order, at `NEW_BASE + i`.

- [ ] **Step 1: Write the failing test** — append to `web/ext/menus-newcars.test.js`:

```js
test('a new car named like a stock car does not steal the stock pick', () => {
  setNewCars([{ name: 'Tornado Shark', donor: 23 }]);
  assert.strictEqual(pickCar({ car: 23, carName: 'Tornado Shark' }), 23);          // stock: by index
  assert.strictEqual(pickCar({ car: NEW_BASE, carName: 'Tornado Shark' }), NEW_BASE);   // new: by name
});

test('race.js takes Free Play\'s new cars from the Car Maker listing, not a launcher store', () => {
  const src = fs.readFileSync(new URL('race.js', import.meta.url), 'utf8');
  assert.match(src, /await listAll\(\)/);
  assert.doesNotMatch(src, /newcars-store|readNewCarStore/);
});
```

- [ ] **Step 2: Run to verify it fails**

Run: `cd web && node --test ext/menus-newcars.test.js`
Expected: the first new test PASSES already (it pins behaviour); the `race.js` one FAILS.

- [ ] **Step 3: Implement**

`web/ext/race.js`: replace `import { readCar } from '../carstore.js';` with `import { listAll, readCar } from '../carstore.js';`, delete the `newcars-store.js` import, and replace the block from `// ---- Extended new cars` to `setNewCars(newcars);` with:

```js
  // ---- Extended new cars (newcars.js): Car Maker cars after the 39, Free Play only ----
  // Every car the Car Maker lists (its storage + mycars/), in listing order; each .rad
  // carries its Extended choices (extlines.js). ?newcar=name:donor races one, anywhere.
  let newList = [];
  if (params.has('newcar') && mode !== 'career') {
    const [name, donor] = params.get('newcar').split(':');
    newList = [{ name, donor: donor === undefined ? undefined : +donor }];
  } else if (free) {
    newList = (await listAll()).map((name) => ({ name }));
  }
  const newcars = [];
  for (const { name, donor } of newList) {
    const text = await readCar(name);
    const car = text && carFromRad(name, text);
    // ?newcar=name:donor, a developer switch: the donor after ':' overrides the .rad's
    if (car && Number.isInteger(donor) && donor >= 0 && donor < 39) car.donor = donor;
    if (car) newcars.push(car); else console.log(`new car "${name}" skipped: not a car Extended can load`);
  }
  setNewCars(newcars);
```

(`mode` and `free` are defined at `race.js:69-70`. The career never loads new cars: it is not `free`, and `?newcar=` is refused there as before.)

`git rm web/ext/newcars-store.js web/launcher-newcars.test.js`

`web/launcher.js`:
- delete line 27 (`import { donorChoices, … } from './ext/newcars-store.js';`) and line 28 (`import { listAll } from './carstore.js';`) if `listAll` has no other use (`grep -n listAll web/launcher.js`).
- `const EXT = [['free', 'Free Play'], ['career', 'Career Mode']];`
- `PAGE_IDS`: drop `'extcars'`; `BACK`: drop `extcars: 'ext',`.
- `case 'ext':   return void startRace(null, { ext: arg });`
- delete the `openNewCars` doc comment and function (lines 902-925). Keep `escapeHtml` only if something else uses it (`grep -n escapeHtml web/launcher.js`); otherwise delete it too.

`index.html`: delete the comment `<!-- 1d : Extended Edition -> New cars … -->` and its `<div class="page" id="page-extcars">…</div>`.

`web/i18n.js`: delete `'New cars': 'Autos nuevos', 'No Car Maker cars yet': 'Todavía no hay autos del Car Maker',` and the pattern `[/^Special of (.+)$/, 'Especial de $1'],`. Keep `'Off'` (Settings uses it).

`web/i18n.test.js`: replace the test `"the launcher's New cars page (Extended new cars)"` with:

```js
test("'Off' is still translated (Settings)", () => {
  assert.strictEqual(es.tr('Off'), 'No');
});
```

`web/tools/browser-newcar.mjs`: change the comment above `addScriptToEvaluateOnNewDocument` to `// a stale store from the removed launcher page: ignored, stock races must not notice`.

- [ ] **Step 4: Run the tests**

Run: `cd web && node --test`
Expected: `# fail 0`. Then `grep -rn "newcars-store\|nfm.ext.newcars\|openNewCars\|extcars" web index.html --include=*.js --include=*.html` prints only the `browser-newcar.mjs` stale-key line.

- [ ] **Step 5: Commit**

```bash
git add -A web/ext/race.js web/launcher.js index.html web/i18n.js web/i18n.test.js web/ext/menus-newcars.test.js web/tools/browser-newcar.mjs
git commit -m "Extended new cars: every Car Maker car in Free Play; the launcher's New cars page goes"
```

---

### Task 4: `web/ext/specials.js` — the 39 special descriptions

**Files:**
- Create: `web/ext/specials.js`
- Test: `web/ext/specials.test.js`
- Modify (only if the test finds a gap): `web/i18n-ext.js`

**Interfaces:**
- Produces: `SPECIALS: string[][]` — 39 entries, each the lines `xtGraphics.carselect` draws under "SPECIAL ATTACK:" for that car at `specialboost = 1` (Free Play). Index = stock car number (`EXT_CARS` order).

- [ ] **Step 1: Write the failing test**

```js
// The special descriptions the Car Maker's Extended tab shows (specials.js) are the car
// select's own: this evaluates the carselect block that draws them, for each of the 39,
// at specialboost 1 (Free Play), so a regeneration of xtGraphics.js cannot leave it stale.
import { test } from 'node:test';
import assert from 'node:assert';
import fs from 'node:fs';
import { SPECIALS } from './specials.js';

function drawn() {
  const src = fs.readFileSync(new URL('xtGraphics.js', import.meta.url), 'utf8');
  const from = src.indexOf("if ((id(this.sc[0]) === 0) || (id(this.sc[0]) === 23)) {\n          this.rd.drawString",
    src.indexOf('carselect(control'));
  const to = src.indexOf('this.rd.setColor(181, 120, 40);', from);
  assert.ok(from > 0 && to > from, 'the special block moved: update the markers');
  const block = new Function('id', 'trunc', 'specialboost', src.slice(from, to));
  const trunc = (v) => (v < 0 ? Math.ceil(v) : Math.floor(v));
  return Array.from({ length: 39 }, (_, k) => {
    const lines = [];
    block.call({ sc: [k], rd: { drawString: (s) => lines.push(s) } }, (c) => c, trunc, 1.0);
    return lines;
  });
}

test('the catalog is what the car select draws', () => {
  assert.deepStrictEqual(SPECIALS, drawn());
});

test('every description line is translated to Spanish', async () => {
  globalThis.localStorage = { getItem: () => JSON.stringify({ lang: 'es' }) };
  const es = await import('../i18n.js?lang=es-specials');
  delete globalThis.localStorage;
  const missing = SPECIALS.flat().filter((s) => es.tr(s) === s);
  assert.deepStrictEqual(missing, []);
});
```

- [ ] **Step 2: Run to verify it fails**

Run: `cd web && node --test ext/specials.test.js`
Expected: FAIL, `Cannot find module ... specials.js`

- [ ] **Step 3: Write the catalog** (generated 2026-09-26 by evaluating that block; car 21 draws nothing)

```js
// The special power of each of Extended's 39 cars, as the car select describes it
// (xtGraphics.carselect, specialboost 1 = Free Play). A new car borrows one by its
// extspecial line (extlines.js). specials.test.js re-derives this from xtGraphics.js.
// Car 21 (Agent Racer): the game draws no description.
export const SPECIALS = [
  ['A random car gets reduced speed.', 'Strength/Defence boost: 50%/30%'],
  ['15% speed boost.', 'Swaps its strength with a random car.', 'Unlimited power.'],
  ['Unlimited power.', 'Strength/Defence boost: 45%/30%', "Drains a random car's health."],
  ['Reduces the defence of the car in first.', 'Speed/Stunting boost: 30%/75%'],
  ['70% strength boost.', "Reduces a random car's defence."],
  ['30% strength and 25% speed boost.', 'These boosts double past 50% damage.'],
  ['Strength/Speed boost: 50%/15%', "Drains a random car's health."],
  ['Strength/Defence boost: 60%/50%'],
  ['100% control boost.', '45% strength boost.', '30% speed boost.'],
  ['A random car gets reduced speed.', 'Strength/Speed boost: 40%/10%', 'You get unlimited power.'],
  ['30% speed boost.', 'Strength/Defence boost: 35%/40%', "Reduces a random car's defence."],
  ['Strength/Speed boost: 40%/30%.', "Drains a random car's health."],
  ['25% speed boost.', 'You get unlimited power.', 'Reduces the defence of the car in first.'],
  ['40% strength/defence boost.', "Reduces a random car's defence."],
  ['Speed/Stunting boost: 15%/100%.', 'Strength/Defence boost: 40%/70%.', 'A random car gets reduced speed.'],
  ['Every stat increases by 30%.'],
  ['Unlimited power.', 'Strength/Speed boost: 30%/15%.', 'Control boost: 50%', "Drains a random car's health."],
  ['Unlimited power.', '50% defence boost.', '15% speed boost.'],
  ['20% speed boost.', '25% strength/defence boost.', 'Reduces the defence of a random car.'],
  ['Strength/Speed boost: 20%/10%.', '30% defence boost.', 'Reduces the speed of a random car.'],
  ['Strength/Defence boost: 60%/40%.', '10% speed cut.', 'Reduces the defence of a random car.'],
  [],
  ['Strength/Speed boost: 15%.', 'Defence boost: 30%.', 'Acceleration boost: 100%.', 'Unlimited power.'],
  ['A random car gets reduced speed.', 'Strength/Defence boost: 55%/30%'],
  ['15% speed boost.', 'Swaps its strength with a random car.', 'Unlimited power.'],
  ['Unlimited power.', 'Strength/Defence boost: 45%/30%', "Drains a random car's health."],
  ['Reduces the defence of the car in first.', 'Speed/Stunting boost: 35%/60%'],
  ['70% strength boost.', "Reduces a random car's defence."],
  ['30% strength and 25% speed boost.', 'These boosts double past 50% damage.'],
  ['Strength/Speed boost: 50%/15%', "Drains a random car's health."],
  ['Strength/Defence boost: 60%/50%'],
  ['75% control boost.', '40% strength boost.', '20% speed boost.'],
  ['A random car gets reduced speed.', 'Strength/Speed boost: 35%/10%', 'You get unlimited power.'],
  ['20% speed/strength boost.', 'Strength/Defence boost: 30%', "Reduces a random car's defence."],
  ['Strength/Speed boost: 30%/20%.', "Drains a random car's health."],
  ['20% speed boost.', 'You get unlimited power.', 'Reduces the defence of the car in first.'],
  ['30% strength/defence boost.', "Reduces a random car's defence."],
  ['Speed boost: 15%', 'Strength/Defence boost: 35%/50%.', 'A random car gets reduced speed.'],
  ['Every stat increases by 20%.'],
];
```

- [ ] **Step 4: Run the tests**

Run: `cd web && node --test ext/specials.test.js`
Expected: PASS. If the Spanish test lists a line, add an entry or pattern for it to `web/i18n-ext.js` next to the `// special attacks` entries (patterns in `EXT_ES_PATTERNS`), then rerun `node --test i18n.test.js ext/specials.test.js`.

- [ ] **Step 5: Commit**

```bash
git add web/ext/specials.js web/ext/specials.test.js web/i18n-ext.js
git commit -m "Extended: the 39 special descriptions as a catalog, pinned to the car select"
```

---

### Task 5: The Car Maker's Extended tab and Try in Extended

**Files:**
- Create: `web/careditor/extended.js` (pure helpers for the tab)
- Test: `web/careditor/extended.test.js`
- Modify: `web/careditor.html:220-229` (tab button), after `:285` (the pane), the source bar (`#drive` neighbour: new button `#tryext`)
- Modify: `web/careditor/editor.js` (imports; `buildExtended()`; `buildAll()`; `$('tryext').onclick`)
- Modify: `web/i18n-careditor.js` (new strings)
- Modify: `web/launcher.js` (boot tail: `nfm.ext.try`)
- Modify: `web/i18n.test.js` (the car-maker coverage test lists the new strings)

**Interfaces:**
- Consumes: Task 1 (`readExt`, `writeSpecial`, `setOwn`, `writeOwnStats`, `writeOwnPhys`, `writePercent`, `HEALTH`, `DAMAGE`), Task 2 (`carFromRad`, `defaultDonor`), Task 4 (`SPECIALS`), `EXT_CARS` (`web/ext/catalog.js`), `NEW_BASE` (`web/ext/newcars.js`), `rad.setStat`, `rad.STAT_NAMES`, `rad.PHYS_NAMES`, `rad.PHYS_SLOTS`.
- Produces:
  - `specialLabel(k) → string` — `"${EXT_CARS[k]} — ${tr(SPECIALS[k][0] ?? 'No description in the game.')}"`
  - `specialText(k) → string` — the description lines, each through `tr`, joined by `\n`
  - `tryPick(prevJson: string|null, name: string) → { car: NEW_BASE, carName: name, group?, stage? }` — group and stage copied from the previous pick when present
  - `TRY_KEY = 'nfm.ext.try'`, `PICK_KEY = 'nfm.ext.free'`

- [ ] **Step 1: Write the failing test** — `web/careditor/extended.test.js`:

```js
// The Car Maker's Extended tab: its pure pieces (extended.js).
import { test } from 'node:test';
import assert from 'node:assert';
import { tryPick, specialLabel, specialText } from './extended.js';
import { NEW_BASE } from '../ext/newcars.js';

test('tryPick survives garbage and keeps group and stage', () => {
  assert.deepStrictEqual(tryPick(null, 'My Car'), { car: NEW_BASE, carName: 'My Car' });
  assert.deepStrictEqual(tryPick('not json', 'My Car'), { car: NEW_BASE, carName: 'My Car' });
  assert.deepStrictEqual(tryPick('[1,2]', 'My Car'), { car: NEW_BASE, carName: 'My Car' });
  assert.deepStrictEqual(tryPick('{"car":7,"carName":"x","group":"nfm2","stage":12}', 'My Car'),
    { car: NEW_BASE, carName: 'My Car', group: 'nfm2', stage: 12 });
});

test('special labels name the car and its first line; Agent Racer has none', () => {
  assert.strictEqual(specialLabel(13), "Stampede — 40% strength/defence boost.");
  assert.strictEqual(specialLabel(21), 'Agent Racer — No description in the game.');
  assert.strictEqual(specialText(13), "40% strength/defence boost.\nReduces a random car's defence.");
});
```

- [ ] **Step 2: Run to verify it fails**

Run: `cd web && node --test careditor/extended.test.js`
Expected: FAIL, `Cannot find module ... extended.js`

- [ ] **Step 3: Write `web/careditor/extended.js`**

```js
// The Car Maker's Extended tab: what it shows about specials, and the Free Play pick
// "Try in Extended" leaves for Extended's car select (web/ext/menus.js loadPick reads it:
// a car past 38 is found by name).
import { EXT_CARS } from '../ext/catalog.js';
import { SPECIALS } from '../ext/specials.js';
import { NEW_BASE } from '../ext/newcars.js';
import { tr } from '../i18n.js';

export const TRY_KEY = 'nfm.ext.try';
export const PICK_KEY = 'nfm.ext.free';

export const specialLabel = (k) => `${EXT_CARS[k]} — ${tr(SPECIALS[k][0] ?? 'No description in the game.')}`;
export const specialText = (k) => (SPECIALS[k].length ? SPECIALS[k].map(tr).join('\n') : tr('No description in the game.'));

export function tryPick(prevJson, name) {
  let p = null;
  try { p = JSON.parse(prevJson); } catch { /* none */ }
  const pick = { car: NEW_BASE, carName: name };
  if (p && typeof p === 'object' && !Array.isArray(p)) {
    if (typeof p.group === 'string') pick.group = p.group;
    if (Number.isInteger(p.stage)) pick.stage = p.stage;
  }
  return pick;
}
```

Run: `cd web && node --test careditor/extended.test.js` — Expected: PASS.

- [ ] **Step 4: The tab markup** — in `web/careditor.html`, after the Physics tab button:

```html
        <button role="tab" data-pane="extended" aria-selected="false" aria-controls="pane-extended">
          Extended <span class="sub" id="extended-sub"></span></button>
```

after `</div>` of `pane-physics`:

```html
      <div class="pane" id="pane-extended" role="tabpanel" hidden>
        <p class="hint">What this car is like in NFM 2 Extended. NFM 2 ignores this tab; the same car races in both games.</p>
        <div class="group">
          <h3>Special</h3>
          <div class="bar"><label class="field">Special <select id="ext-special"></select></label></div>
          <p class="hint" id="ext-special-text" style="white-space:pre-line"></p>
        </div>
        <div class="group">
          <h3>Stats and physics in Extended</h3>
          <div class="bar" role="radiogroup" aria-label="Stats and physics in Extended">
            <label class="field"><input type="radio" name="ext-own" id="ext-same" value="same"> Same as NFM 2</label>
            <label class="field"><input type="radio" name="ext-own" id="ext-own" value="own"> Own</label>
          </div>
          <div id="ext-own-rows" hidden>
            <div id="ext-stat-rows"></div>
            <div id="ext-phys-rows" style="margin-top:.5rem"></div>
          </div>
        </div>
        <div class="group">
          <h3>Health and damage in Extended</h3>
          <div id="ext-hd-rows"></div>
          <p class="hint" id="ext-hd-hint"></p>
        </div>
      </div>
```

and in the Source bar, after `<button id="drive" …>` (find it with `grep -n 'id="drive"' web/careditor.html`):

```html
        <button id="tryext" type="button">Try in Extended</button>
```

- [ ] **Step 5: `buildExtended()`** — in `web/careditor/editor.js` add imports:

```js
import * as ext from '../ext/extlines.js';
import { carFromRad, defaultDonor } from '../ext/newcars-stats.js';
import { specialLabel, specialText, tryPick, TRY_KEY, PICK_KEY } from './extended.js';
```

and after `buildPhysics()`:

```js
// The Extended tab: inert ext* lines in the same .rad (web/ext/extlines.js), read by
// Extended's carFromRad and ignored by NFM 2. Values out of range are shown, marked
// invalid, and left alone until the user moves the control.
function buildExtended() {
  const e = () => ext.readExt(source());
  const car = () => carFromRad(current || 'car', source());

  const sp = $('ext-special');
  sp.innerHTML = '';
  sp.add(new Option(tr('By class (automatic)'), -1));
  for (let k = 0; k < 39; k++) sp.add(new Option(specialLabel(k), k));
  sp.onchange = () => setSource(ext.writeSpecial(source(), Number(sp.value) < 0 ? null : Number(sp.value)));

  $('ext-same').onchange = () => setSource(ext.setOwn(source(), false));
  $('ext-own').onchange = () => setSource(ext.setOwn(source(), true));

  const statHost = $('ext-stat-rows'), physHost = $('ext-phys-rows');
  statHost.innerHTML = ''; physHost.innerHTML = '';
  rad.STAT_NAMES.forEach((name, i) => {
    rows.push(sliderRow(statHost, {
      caption: name, help: STAT_HELP[name], min: rad.STAT_MIN, max: rad.STAT_MAX,
      read: () => (e().stat || rad.DEFAULT_STATS)[i],
      write: (v) => ext.writeOwnStats(source(), rad.setStat(e().stat || rad.DEFAULT_STATS, i, v)),
    }));
  });
  for (const i of rad.PHYS_SLOTS) {
    rows.push(sliderRow(physHost, {
      caption: rad.PHYS_NAMES[i], help: physicsHelp(i), min: 0, max: 100,
      read: () => (e().phys || rad.DEFAULT_PHYSICS.phys)[i],
      write: (v) => { const p = (e().phys || rad.DEFAULT_PHYSICS.phys).slice(); p[i] = v; return ext.writeOwnPhys(source(), p); },
    }));
  }

  const hd = $('ext-hd-rows');
  hd.innerHTML = '';
  const percentRow = (name, caption, range, field) => sliderRow(hd, {
    caption, min: range.min, max: range.max,
    read: () => e()[field] ?? 100,
    write: (v) => ext.writePercent(source(), name, v),
    format: (v) => {
      if (e().invalid.includes(name)) return tr('invalid — ignored');
      const c = car();
      return c ? `${v}% · ${field === 'health' ? c.stat.maxmag : c.stat.dammult.toFixed(3)}` : `${v}%`;
    },
  });
  rows.push(percentRow('exthealth', 'Health', ext.HEALTH, 'health'));
  rows.push(percentRow('extdamage', 'Damage taken', ext.DAMAGE, 'damage'));
  $('ext-hd-hint').textContent = tr('Percent of what this car has in NFM 2. The number beside it is what Extended uses.');

  rows.push({
    sync() {
      const x = e();
      const own = !!(x.stat && x.phys);
      sp.value = x.special ?? -1;
      const c = car();
      sp.options[0].text = tr('By class (automatic)') + (c ? ` — ${specialLabel(defaultDonor(c.cclass)).split(' — ')[0]}` : '');
      const k = x.special ?? (c ? defaultDonor(c.cclass) : null);
      $('ext-special-text').textContent = k === null ? '' : specialText(k)
        + (x.invalid.includes('extspecial') ? '\n' + tr('The special in the file is not a stock car — ignored.') : '');
      $('ext-same').checked = !own;
      $('ext-own').checked = own;
      $('ext-own-rows').hidden = !own;
      $('extended-sub').textContent = x.invalid.length ? tr('invalid') : own ? tr('own') : '';
    },
  });
}
```

Add `buildExtended();` to `buildAll()` after `buildPhysics();`.

Note: the `format` callback in `sliderRow` receives the slider value; `sync()` calls `read()` then `format(v)`. `carFromRad` parses the model on every sync; the preview already re-parses on every input, so this is the same order of cost. If a drag stutters on a big car, cache `car()` per `source()` string (`let memo = [null, null]`).

- [ ] **Step 6: Try in Extended** — in `editor.js`, after `$('drive').onclick`:

```js
// Save, leave Extended's Free Play pick on this car, and let the launcher start Free Play
// (index.html reads TRY_KEY at the end of its boot: no developer mode needed, unlike ?ext=).
$('tryext').onclick = async () => {
  if (!current) return;
  await save(current);
  try {
    localStorage.setItem(PICK_KEY, JSON.stringify(tryPick(localStorage.getItem(PICK_KEY), current)));
    sessionStorage.setItem(TRY_KEY, '1');
  } catch { /* private mode: the launcher opens on its menu */ }
  location.href = '../index.html';
};
```

In `web/launcher.js`, at the boot tail replace `if (!(await resumeCareer())) await resumeRoom();` with:

```js
    // the Car Maker's "Try in Extended" (web/careditor/extended.js): straight into Extended's Free Play
    let tryExt = false;
    try { tryExt = sessionStorage.getItem('nfm.ext.try') === '1'; sessionStorage.removeItem('nfm.ext.try'); } catch { /* private mode */ }
    if (tryExt) await startRace(null, { ext: 'free' });
    else if (!(await resumeCareer())) await resumeRoom();
```

- [ ] **Step 7: Spanish** — add to `CAREDITOR_ES` in `web/i18n-careditor.js` (a `// ---- the Extended tab ----` block):

```js
  'Extended': 'Extended', 'Special': 'Especial', 'By class (automatic)': 'Según la clase (automático)',
  'Stats and physics in Extended': 'Estadísticas y física en Extended', 'Same as NFM 2': 'Igual que en NFM 2',
  'Own': 'Propias', 'own': 'propias', 'invalid': 'inválido', 'invalid — ignored': 'inválido: se ignora',
  'Health and damage in Extended': 'Vida y daño en Extended', 'Health': 'Vida', 'Damage taken': 'Daño recibido',
  'Try in Extended': 'Probar en Extended', 'No description in the game.': 'El juego no lo describe.',
  'What this car is like in NFM 2 Extended. NFM 2 ignores this tab; the same car races in both games.':
    'Cómo es este auto en NFM 2 Extended. NFM 2 ignora esta pestaña: el mismo auto corre en los dos juegos.',
  'Percent of what this car has in NFM 2. The number beside it is what Extended uses.':
    'Porcentaje de lo que este auto tiene en NFM 2. El número de al lado es el que usa Extended.',
  'The special in the file is not a stock car — ignored.': 'El especial del archivo no es un auto del juego: se ignora.',
```

(`'Extended'` maps to itself: the i18n test's stability check allows it. If `'Special'` already exists elsewhere in `i18n.js` with another translation, keep that one and drop this key.)

In `web/i18n.test.js`, add a test after the car-maker coverage test:

```js
test('car maker: the Extended tab is translated', async () => {
  const { specialLabel } = await import('./careditor/extended.js');
  for (const s of ['By class (automatic)', 'Same as NFM 2', 'Try in Extended', 'Health and damage in Extended',
    'No description in the game.', 'invalid — ignored']) assert.notStrictEqual(es.tr(s), s, s);
  assert.ok(specialLabel(13).startsWith('Stampede — '));
});
```

- [ ] **Step 8: Run the tests**

Run: `cd web && node --test`
Expected: `# fail 0`.

- [ ] **Step 9: Browser check** (`python3 web/tools/serve.py 8123` running; use the Chrome DevTools or Playwright MCP tools)
  1. Open `http://localhost:8123/web/careditor.html`, pick "Simple Car", open the Extended tab. Expected: Special select reads "By class (automatic) — <car>", the description below; Same checked; own rows hidden; Health 100 % · a number.
  2. Pick special 13 (Stampede), choose Own, move Speed, set Health 200 %. Expected: the source textarea shows `extspecial(13)`, `extstat(...)`, `extphysics(...)` (11 values), `exthealth(200)`; the health number doubled.
  3. Choose Same. Expected: `extstat`/`extphysics` gone from the source.
  4. Switch the page to Spanish (launcher Settings → Language, or `localStorage` `nfm.settings`), reload, screenshot the tab. Expected: every label in Spanish.
  5. Press Try in Extended. Expected: the launcher loads and goes straight to Extended's Free Play car select on "Simple Car", the special text drawn is Stampede's ("40% strength/defence boost."), and in the race the car's health bar lasts about twice as long (or read `madness[0].maxmag` via evaluate: twice NFM 2's value).
  Save the two screenshots in the scratchpad; mention them in the report.

- [ ] **Step 10: Commit**

```bash
git add web/careditor.html web/careditor/editor.js web/careditor/extended.js web/careditor/extended.test.js web/i18n-careditor.js web/i18n.test.js web/launcher.js
git commit -m "Car Maker: the Extended tab (special, own stats, health and damage) and Try in Extended"
```

---

### Task 6: Car select filter — all cars / the game's / mine

Asked by the user at plan review (2026-09-26): in Free Play's car select, choose whether you browse your own cars or the game's. ▴ ▾ (unused by the jar's normal-mode car select) cycle **All cars / Game cars / My cars**, as ▴ ▾ cycle the stage groups on the stage select; a DOM `<select>` over the car select does the same by mouse. ◂ ▸ then step within the group only. The choice is remembered in the Free Play pick (`carGroup`). With no Car Maker cars the control is hidden and the group is All.

**Files:**
- Modify: `web/ext/newcars.js` (group state; `firstCar`, `lastCar`, `nextCar` honour it)
- Modify: `web/ext/newcars.test.js`
- Modify: `web/ext/menus.js` (`loadPick` → `carGroup`; `clampCarArrows`; the car select wrapper's ▴ ▾; a DOM select shown in fase 7)
- Modify: `web/ext/menus-newcars.test.js`
- Modify: `web/i18n.js` (Spanish: `'All cars'`, `'Game cars'`, `'My cars'` exists already in the Car Maker dictionary — check, `'Cars'`, the hint)

**Interfaces:**
- Produces (newcars.js): `CAR_GROUPS = ['all', 'game', 'mine']`, `setCarGroup(g)`, `carGroup() → 'all'|'game'|'mine'` (always `'all'` with no new cars), `firstCar()`, `lastCar()`, `inGroup(c) → boolean`, `nextCar(c, d)` within the group, `cycleCarGroup(c) → { group, car }` (next group; `car` = `c` if still in it, else the group's first car).
- Produces (menus.js): the pick gains `carGroup`; `pickCar` unchanged.

- [ ] **Step 1: Write the failing tests** — append to `web/ext/newcars.test.js` (import the new names):

```js
test('the car groups: game cars only, my cars only, all', () => {
  setNewCars([{ name: 'A', donor: 30 }, { name: 'B', donor: 13 }]);
  setCarGroup('game');
  assert.deepStrictEqual([firstCar(), lastCar()], [0, 38]);
  assert.strictEqual(nextCar(38, 1), 38);
  assert.ok(inGroup(5) && !inGroup(NEW_BASE));
  setCarGroup('mine');
  assert.deepStrictEqual([firstCar(), lastCar()], [NEW_BASE, NEW_BASE + 1]);
  assert.strictEqual(nextCar(NEW_BASE, -1), NEW_BASE);
  assert.ok(!inGroup(38) && inGroup(NEW_BASE + 1));
  setCarGroup('all');
  assert.strictEqual(nextCar(38, 1), NEW_BASE);
  assert.strictEqual(nextCar(NEW_BASE, -1), 38);
});

test('no Car Maker cars: the group is always all; nonsense is all', () => {
  setNewCars([]);
  setCarGroup('mine');
  assert.strictEqual(carGroup(), 'all');
  assert.deepStrictEqual([firstCar(), lastCar()], [0, 38]);
  setNewCars([{ name: 'A', donor: 30 }]);
  setCarGroup('bogus');
  assert.strictEqual(carGroup(), 'all');
});

test('cycling the group keeps the car when it is still in it, else jumps to the first', () => {
  setNewCars([{ name: 'A', donor: 30 }]);
  setCarGroup('all');
  assert.deepStrictEqual(cycleCarGroup(7), { group: 'game', car: 7 });
  assert.deepStrictEqual(cycleCarGroup(7), { group: 'mine', car: NEW_BASE });
  assert.deepStrictEqual(cycleCarGroup(NEW_BASE), { group: 'all', car: NEW_BASE });
  setCarGroup('all');
});
```

and to `web/ext/menus-newcars.test.js`:

```js
test('the pick remembers the car group; a car outside it opens the group that holds it', () => {
  setNewCars([{ name: 'A', donor: 30 }]);
  const store = {};
  globalThis.localStorage = { getItem: (k) => store[k] ?? null, setItem: (k, v) => { store[k] = v; } };
  try {
    store['nfm.ext.free'] = JSON.stringify({ car: 7, carGroup: 'game' });
    assert.strictEqual(loadPick(['nfm2', 'ext']).carGroup, 'game');
    store['nfm.ext.free'] = JSON.stringify({ car: NEW_BASE, carName: 'A', carGroup: 'game' });   // Try in Extended from a 'game' filter
    assert.strictEqual(loadPick(['nfm2', 'ext']).carGroup, 'all');
    store['nfm.ext.free'] = '{}';
    assert.strictEqual(loadPick(['nfm2', 'ext']).carGroup, 'all');
  } finally { delete globalThis.localStorage; setCarGroup('all'); }
});

test('the car select arrows stop at the ends of the group', () => {
  setNewCars([{ name: 'A', donor: 30 }]);
  setCarGroup('mine');
  const c = { left: true, right: true };
  clampCarArrows(c, NEW_BASE);
  assert.deepStrictEqual(c, { left: false, right: false });
  setCarGroup('all');
});
```

(import `loadPick`, `clampCarArrows` from `./menus.js` and `setCarGroup` from `./newcars.js`.)

- [ ] **Step 2: Run to verify they fail**

Run: `cd web && node --test ext/newcars.test.js ext/menus-newcars.test.js`
Expected: FAIL — `setCarGroup is not a function` / `carGroup` undefined.

- [ ] **Step 3: Implement `web/ext/newcars.js`** — replace `lastCar` and `nextCar` with:

```js
// Free Play's car select can browse all cars, the game's 39 or the Car Maker's (menus.js)
export const CAR_GROUPS = ['all', 'game', 'mine'];
let group = 'all';
export function setCarGroup(g) { group = CAR_GROUPS.includes(g) ? g : 'all'; }
export const carGroup = () => (cars.length ? group : 'all');
export const firstCar = () => (carGroup() === 'mine' ? NEW_BASE : 0);
export const lastCar = () => (carGroup() === 'game' || !cars.length ? STOCK - 1 : NEW_BASE + cars.length - 1);
export const inGroup = (c) => c >= firstCar() && c <= lastCar() && (c < STOCK || c >= NEW_BASE);

/** The Free Play car select's arrows: 0..38, then the new cars, within the group, no wrap. */
export function nextCar(c, d) {
  if (d > 0) return c === STOCK - 1 ? (lastCar() >= NEW_BASE ? NEW_BASE : c) : Math.min(c + 1, lastCar());
  return c === NEW_BASE ? (firstCar() === 0 ? STOCK - 1 : c) : Math.max(c - 1, firstCar());
}

/** ▴ ▾ on the car select: the next group, and the car to show in it. */
export function cycleCarGroup(c) {
  setCarGroup(CAR_GROUPS[(CAR_GROUPS.indexOf(carGroup()) + 1) % CAR_GROUPS.length]);
  return { group: carGroup(), car: inGroup(c) ? c : firstCar() };
}
```

- [ ] **Step 4: Implement `web/ext/menus.js`**
  - import `firstCar, setCarGroup, carGroup, cycleCarGroup, inGroup, CAR_GROUPS` from `./newcars.js`.
  - `loadPick`: after computing `car`, `setCarGroup(p.carGroup); if (!inGroup(car)) setCarGroup('all');` and return `carGroup: carGroup()` in the object.
  - `clampCarArrows`: `if (car <= firstCar()) control.left = false;`
  - in `xt.carselect` wrapper, inside `if (!career) { … }` before `clampCarArrows`:

```js
      // ▴ ▾: all cars / the game's / mine (the jar's normal-mode car select does not use them)
      if ((control_.up || control_.down) && newCars().length && this.flipo === 0) {
        const { group, car } = cycleCarGroup(this.sc[0]);
        pick.carGroup = group;
        this.sc[0] = car;
        savePick(pick);
      }
      control_.up = control_.down = false;
```

  - on Enter (`pick.car = this.sc[0];` block) also `pick.carGroup = carGroup();`.
  - a DOM overlay for the car select, next to `statUi` (free play only, shown when `xt.fase === 7 && newCars().length`):

```js
  // ---- the car select's group (free play): all / the game's / mine ------------
  const carUi = document.createElement('div');
  carUi.style.cssText = 'position:absolute;left:0;right:0;top:8px;width:870px;z-index:4;display:none;'
    + 'text-align:center;pointer-events:none;font:bold 12px Arial,sans-serif;color:#fff;text-shadow:0 0 4px #000';
  const GROUP_NAMES = { all: 'All cars', game: 'Game cars', mine: 'My cars' };
  carUi.innerHTML = `<select data-k="cars" style="${'pointer-events:auto;height:24px;background:#000;color:rgb(47,179,255);'
    + 'border:1px solid rgb(47,179,255);font:bold 13px Arial,sans-serif;padding:0 2px;'}" aria-label="${tr('Cars')}">${
    CAR_GROUPS.map((g) => `<option value="${g}">${tr(GROUP_NAMES[g])}</option>`).join('')}</select>
    <div style="margin-top:2px;opacity:.85">${tr('▴ ▾ cars')}</div>`;
  const carSel = carUi.querySelector('select');
  carUi.addEventListener('mousedown', (e) => e.stopPropagation());
  carSel.onchange = () => {
    carSel.blur();
    setCarGroup(carSel.value);
    pick.carGroup = carGroup();
    if (!inGroup(xt.sc[0])) xt.sc[0] = firstCar();
    savePick(pick);
  };
  if (!career) host.append(carUi);
  const paintCars = () => {
    const show = !career && xt.fase === 7 && newCars().length > 0;
    carUi.style.display = show ? '' : 'none';
    if (show && carSel.value !== carGroup()) carSel.value = carGroup();
  };
```

  Call `paintCars()` in `tick()` after `paintStat()`, and `carUi.remove()` in `finish`. (`sel` is declared later in the file, which is why the style string is inline here.)

- [ ] **Step 5: Spanish** — in `web/i18n.js` next to `'◂ ▸ stage · ▴ ▾ stages · …'` add `'All cars': 'Todos los autos', 'Game cars': 'Autos del juego', 'Cars': 'Autos', '▴ ▾ cars': '▴ ▾ autos',` and `'My cars'` only if `grep -n "'My cars'" web/i18n*.js` finds none (the Car Maker has `'My cars': 'Mis autos'`, which covers it). Add to `web/i18n.test.js`:

```js
test('Free Play car select groups are translated', () => {
  for (const s of ['All cars', 'Game cars', 'My cars', '▴ ▾ cars']) assert.notStrictEqual(es.tr(s), s, s);
});
```

- [ ] **Step 6: Run the tests**

Run: `cd web && node --test`
Expected: `# fail 0`.

- [ ] **Step 7: Browser check** — with a Car Maker car stored, open Extended Free Play (launcher → Extended Edition → Free Play). Expected: the group select at the top of the car select; ▾ → Game cars, ▸ at DR Monstaa stays; ▾ → My cars shows the Car Maker car, ◂ stays; the select by mouse does the same; a reload remembers the group. Screenshot to the scratchpad.

- [ ] **Step 8: Commit**

```bash
git add web/ext/newcars.js web/ext/newcars.test.js web/ext/menus.js web/ext/menus-newcars.test.js web/i18n.js web/i18n.test.js
git commit -m "Extended Free Play: the car select browses all cars, the game's or yours"
```

---

### Task 7: Verify, record, hand over

**Files:**
- Modify: `TASKS.md` (the "Car Maker cars in Extended" entry and a new one), `WORK.md` (append)

- [ ] **Step 1: Unit + stock hashes**

Run: `cd web && node --test 2>&1 | tail -5` → `# fail 0`.
Run (server on :8123): `node web/tools/browser-newcar.mjs` → every line `PASS`, including `classic stock hash: 271c3367`.

- [ ] **Step 2: TASKS.md** — under "Content from the base game's editors", after the `Car Maker cars in Extended, as NEW cars` entry, add:

```markdown
- [x] **Car Maker's Extended tab (2026-09-26, branch `ext-carmaker`)**: one car, two games.
      The tab writes inert `ext*` lines (`web/ext/extlines.js`): `extspecial(0-38)`,
      `extstat`/`extphysics` (own stats and the 11 handling sliders; crash look, engine
      and `actmag` stay shared), `exthealth(50-300)`, `extdamage(50-200)`; invalid = ignored.
      Every Car Maker car is in Extended's Free Play (`listAll()` order); the launcher's
      New cars page is gone. "Try in Extended" saves and opens Free Play on the car.
      Plan: `docs/superpowers/plans/2026-09-26-ext-carmaker.md`.
```

- [ ] **Step 3: WORK.md** — append (newest at the bottom):

```markdown
- `handling()` in a .rad only sets `CarDefine.dishandle` (a display bar); Extended never reads it, so the Car Maker's Extended tab has no `exthandling`.
- `physics()` value 15 (`actmag`, the crash calibration) sets `maxmag` in `loadstat` (CarDefine.js:445): never copy it into a per-game line, or recalibrating silently changes the other game's health.
- `carstore.listAll()` always includes the shipped `Simple Car`, so "load every Car Maker car" must be gated to Extended Free Play, or every classic selftest grows its tables.
```

- [ ] **Step 4: Commit**

```bash
git add TASKS.md WORK.md
git commit -m "Car Maker's Extended tab: TASKS and WORK"
```

- [ ] **Step 5: Deploy** — `./deploy.sh` needs `rsync`, which this Windows machine lacks (TASKS.md "Deploy"). Try it; if it fails, say so in the report and do not work around it.
