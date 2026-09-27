# Free Play Rivals Screen Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** In NFM2 and Extended Free Play, a Rivals screen after the stage select sets the number of cars, filters opponents by tier, builds an opponent pool and pins cars to slots.

**Architecture:** One DOM module, `web/rivals.js`, holds the pure logic (`pickRivals`, `parseRivals`, `toggleTier`, storage) and the screen (`runRivals`). NFM2 opens it from the launcher's `startCarSelect` and hands the result to `main.js` as `?players=` + `?rivals=`; `main.js` rewrites `xt.sc[]` after `sortcars()`. Extended opens it from `runMenus` (`web/ext/menus.js`); `web/ext/race.js` wraps `xt.sortcars` to rewrite `sc[]` and the stage is reloaded so the new field is placed.

**Tech Stack:** Vanilla ES modules, DOM, `node --test`. No dependencies.

**Spec:** `docs/superpowers/specs/2026-09-27-free-play-rivals-design.md`

## Global Constraints

- Free Play only. Career (`gmode` 1/2, Extended `mode === 'career'`) and netplay never open Rivals nor read its config.
- Tiers: C / B / A / S. NFM2: `cclass` 0–1 → C, 2–3 → B, 4 → A, no S. Extended: fixed table `web/ext/tiers.js`.
- Car count range: NFM2 1–8, Extended 1–19. Default config: `{ count: 7, mode: 'game', pool: <every car>, fixed: [] }`.
- Storage keys: `nfm.rivals.nfm2`, `nfm.rivals.ext`; every `localStorage` access in try/catch.
- Draws use the game's seeded `random()` from `web/java.js`, never `Math.random`.
- Car Maker cars are never rivals (pool lists only the 16 / 39 stock cars).
- UI strings in English in code, Spanish in `web/i18n.js`'s `ES` table, rendered through `tr()`.
- Screen style: black, `rgb(47,179,255)` borders, bold Arial — as Extended's stage select controls (`web/ext/menus.js:375`).
- The no-depth-buffer invariant (AGENTS.md) is untouched: this work never touches drawing order.
- Finish by deploying (push to `main`) and saying so; note the work in `TASKS.md`, lessons in `WORK.md`.

## Review Focus

1. Held Enter from the stage select must not race straight through Rivals — `Enter` with `e.repeat` is ignored (Task 3 test via browser step; pinned by `onKey` code).
2. Stored config from a future/garbled version (`count: 99`, unknown car ids, non-array `pool`) must load as a valid config, never crash the menu — `parseRivals` tests in Task 1.
3. `mode: 'pool'` with every car unticked must still race a full grid (falls back to the game's pick) — Task 1 test.
4. Extended one-car race (count 1) chosen on the Rivals screen, not by URL, must still finish its lap (the `stat$m` wasted-check proxy must follow the live count) — Task 5 browser step.
5. Esc on Rivals returns to the same stage (not the car select, not a random stage) in both modes — Tasks 4 and 5 browser steps.

---

## File Structure

- Create `web/rivals.js` — pure logic + DOM screen; importable by node (no DOM at module top level).
- Create `web/rivals.test.js` — unit tests for the pure logic.
- Create `web/tools/ext-tiers.mjs` — one-off generator for the Extended tier table.
- Create `web/ext/tiers.js` — `EXT_TIER`, 39 entries.
- Create `web/ext/tiers.test.js` — table sanity test.
- Modify `web/i18n.js` — Spanish strings.
- Modify `web/launcher.js` — NFM2 flow; remove Extended's "Free Play cars" row and `S.extplayers`.
- Modify `web/main.js` — apply `?rivals=`.
- Modify `web/ext/race.js` — count from Rivals storage, `sortcars` wrapper, live one-car proxy.
- Modify `web/ext/menus.js` — open Rivals on the stage select's Enter, reload, race.
- Modify `TASKS.md`, `WORK.md`.

---

### Task 1: Rivals logic (`pickRivals`, `parseRivals`, `toggleTier`, storage, NFM2 tiers)

**Files:**
- Create: `web/rivals.js`
- Test: `web/rivals.test.js`

**Interfaces:**
- Produces (all exported from `web/rivals.js`):
  - `RIVALS_KEY = { nfm2: 'nfm.rivals.nfm2', ext: 'nfm.rivals.ext' }`
  - `NFM2_CCLASS: number[16]`, `tierOfClass(c: number) -> 'C'|'B'|'A'`
  - `defaultRivals(nCars: number) -> Cfg` where `Cfg = { count: number, mode: 'game'|'pool', pool: number[], fixed: (number|null)[] }`
  - `parseRivals(text: string|null, nCars: number, maxCount: number) -> Cfg|null`
  - `loadRivals(key: string, nCars: number, maxCount: number) -> Cfg`
  - `saveRivals(key: string, cfg: Cfg) -> void`
  - `pickRivals(cfg: {mode, pool, fixed}, gameSc: ArrayLike<number>, n: number, random: () => number) -> number[]`
  - `toggleTier(pool: number[], cars: {i, tier}[], tier: string) -> number[]`

- [ ] **Step 1: Write the failing tests**

`web/rivals.test.js`:

```js
// The Rivals screen's logic (rivals.js): the grid it draws, what it accepts from storage/URL.

import { test } from 'node:test';
import assert from 'node:assert';
import fs from 'node:fs';
import { NFM2_CCLASS, defaultRivals, parseRivals, pickRivals, tierOfClass, toggleTier } from './rivals.js';

// a deterministic stand-in for java.js random(): cycles through the given values
const seq = (...v) => { let k = 0; return () => v[k++ % v.length]; };

test('game mode keeps sortcars, player slot untouched', () => {
  const sc = pickRivals({ mode: 'game', pool: [3], fixed: [] }, [9, 1, 2, 3, 4, 5, 6, 9], 7, seq(0));
  assert.deepStrictEqual(sc, [9, 1, 2, 3, 4, 5, 6, 9]);
});

test('a one-car pool races that car everywhere', () => {
  const sc = pickRivals({ mode: 'pool', pool: [13], fixed: [] }, [4, 1, 2, 3, 5, 6, 7, 4], 8, seq(0.5));
  assert.deepStrictEqual(sc, [4, 13, 13, 13, 13, 13, 13, 13]);
});

test('pool draws without repeats until the pool runs out', () => {
  const sc = pickRivals({ mode: 'pool', pool: [10, 11, 12], fixed: [] }, [0, 0, 0, 0, 0, 0, 0], 7, seq(0));
  assert.deepStrictEqual(sc.slice(1, 4).sort(), [10, 11, 12]);
  assert.deepStrictEqual(sc.slice(4, 7).sort(), [10, 11, 12]);
});

test('an empty pool falls back to the game pick', () => {
  const sc = pickRivals({ mode: 'pool', pool: [], fixed: [] }, [0, 1, 2, 3], 4, seq(0));
  assert.deepStrictEqual(sc, [0, 1, 2, 3]);
});

test('fixed slots win in both modes', () => {
  const fixed = [null, 15, undefined, 2];
  assert.deepStrictEqual(pickRivals({ mode: 'game', pool: [], fixed }, [0, 1, 2, 3, 4], 5, seq(0)), [0, 1, 15, 3, 2]);
  assert.deepStrictEqual(pickRivals({ mode: 'pool', pool: [7], fixed }, [0, 1, 2, 3, 4], 5, seq(0)), [0, 7, 15, 7, 2]);
});

test('same random, same grid', () => {
  const cfg = { mode: 'pool', pool: [1, 2, 3, 4, 5, 6], fixed: [] };
  const a = pickRivals(cfg, new Int32Array(8), 8, seq(0.1, 0.7, 0.3));
  const b = pickRivals(cfg, new Int32Array(8), 8, seq(0.1, 0.7, 0.3));
  assert.deepStrictEqual(a, b);
});

test('parseRivals accepts a good config', () => {
  assert.deepStrictEqual(parseRivals('{"count":5,"mode":"pool","pool":[1,2],"fixed":[3,null]}', 16, 8),
    { count: 5, mode: 'pool', pool: [1, 2], fixed: [3, null] });
});

test('parseRivals repairs garbage instead of throwing', () => {
  assert.strictEqual(parseRivals(null, 16, 8), null);
  assert.strictEqual(parseRivals('not json', 16, 8), null);
  assert.strictEqual(parseRivals('7', 16, 8), null);
  assert.deepStrictEqual(parseRivals('{"count":99,"mode":"x","pool":[1,1,40,-1,"2"],"fixed":[99,4,1,1,1,1,1,1,1]}', 16, 8),
    { count: 8, mode: 'game', pool: [1], fixed: [null, 4, 1, 1, 1, 1, 1] });
  assert.deepStrictEqual(parseRivals('{"pool":"all"}', 16, 8), { count: 7, mode: 'game', pool: [], fixed: [] });
});

test('defaultRivals: seven cars, every car, the game pick', () => {
  assert.deepStrictEqual(defaultRivals(3), { count: 7, mode: 'game', pool: [0, 1, 2], fixed: [] });
});

test('toggleTier adds the tier when any is missing, else removes it', () => {
  const cars = [{ i: 0, tier: 'C' }, { i: 1, tier: 'C' }, { i: 2, tier: 'A' }];
  assert.deepStrictEqual(toggleTier([2, 0], cars, 'C').sort(), [0, 1, 2]);
  assert.deepStrictEqual(toggleTier([0, 1, 2], cars, 'C'), [2]);
});

test('NFM2_CCLASS is CarDefine.cclass, and gives 6 C / 5 B / 5 A', () => {
  const src = fs.readFileSync(new URL('./CarDefine.js', import.meta.url), 'utf8');
  const cclass = src.match(/this\.cclass = Int32Array\.from\(\[([^\]]*)\]/)[1].split(',').map(Number).slice(0, 16);
  assert.deepStrictEqual(NFM2_CCLASS, cclass);
  const t = NFM2_CCLASS.map(tierOfClass);
  assert.deepStrictEqual(['C', 'B', 'A'].map((x) => t.filter((y) => y === x).length), [6, 5, 5]);
});
```

- [ ] **Step 2: Run to verify they fail**

Run: `cd web && node --test rivals.test.js`
Expected: FAIL — `Cannot find module ... rivals.js`.

- [ ] **Step 3: Write the logic**

`web/rivals.js`:

```js
// Free Play's Rivals screen, NFM2 and Extended: how many cars race, and which.
// After the stage select (launcher.js for NFM2, ext/menus.js for Extended). The
// game still draws its own field (sortcars); pickRivals then rewrites xt.sc[1..n-1]
// from the player's pool and pinned slots. sc[0] is the player's car.

import { tr } from './i18n.js';

export const RIVALS_KEY = { nfm2: 'nfm.rivals.nfm2', ext: 'nfm.rivals.ext' };

// CarDefine.cclass of the 16 stock cars (CarDefine.js:49): the game's Class C .. Class A as 0-4
export const NFM2_CCLASS = [0, 0, 0, 0, 0, 1, 2, 2, 2, 2, 3, 4, 4, 4, 4, 4];
/** Class C and B&C -> C, B and A&B -> B, A -> A: six, five and five of the stock cars. */
export const tierOfClass = (c) => (c <= 1 ? 'C' : c <= 3 ? 'B' : 'A');

export const defaultRivals = (nCars) =>
  ({ count: 7, mode: 'game', pool: Array.from({ length: nCars }, (_, i) => i), fixed: [] });

/** A stored or URL config, repaired to something raceable; null if it is not a config at all. */
export function parseRivals(text, nCars, maxCount) {
  let o;
  try { o = JSON.parse(text); } catch { return null; }
  if (!o || typeof o !== 'object' || Array.isArray(o)) return null;
  const car = (v) => Number.isInteger(v) && v >= 0 && v < nCars;
  return {
    count: Number.isInteger(o.count) ? Math.max(1, Math.min(maxCount, o.count)) : Math.min(7, maxCount),
    mode: o.mode === 'pool' ? 'pool' : 'game',
    pool: Array.isArray(o.pool) ? [...new Set(o.pool.filter(car))] : [],
    fixed: Array.isArray(o.fixed) ? o.fixed.slice(0, maxCount - 1).map((v) => (car(v) ? v : null)) : [],
  };
}

export function loadRivals(key, nCars, maxCount) {
  let text = null;
  try { text = localStorage.getItem(key); } catch { /* private window, blocked storage */ }
  return parseRivals(text, nCars, maxCount) ?? defaultRivals(nCars);
}

export function saveRivals(key, cfg) {
  try { localStorage.setItem(key, JSON.stringify(cfg)); } catch { /* not remembered, still raced */ }
}

/**
 * The grid for slots 1..n-1. 'pool' draws from the pool with `random` (the game's
 * seeded one, so a replay draws the same), no repeats until every pool car is used;
 * an empty pool is the game's pick. Pinned slots (fixed[k-1]) win in both modes.
 */
export function pickRivals({ mode, pool, fixed }, gameSc, n, random) {
  const sc = Array.from(gameSc);
  let bag = [];
  for (let k = 1; k < n; k++) {
    if (fixed[k - 1] != null) { sc[k] = fixed[k - 1]; continue; }
    if (mode !== 'pool' || !pool.length) continue;
    if (!bag.length) bag = [...pool];
    sc[k] = bag.splice(Math.floor(random() * bag.length), 1)[0];
  }
  return sc;
}

/** A tier button: tick every car of the tier if any is unticked, else untick them all. */
export function toggleTier(pool, cars, tier) {
  const ids = cars.filter((c) => c.tier === tier).map((c) => c.i);
  if (ids.every((i) => pool.includes(i))) return pool.filter((i) => !ids.includes(i));
  return [...new Set([...pool, ...ids])];
}
```

- [ ] **Step 4: Run to verify they pass**

Run: `cd web && node --test rivals.test.js`
Expected: all 11 tests PASS.

- [ ] **Step 5: Commit**

```bash
git add web/rivals.js web/rivals.test.js
git commit -m "Rivals: grid logic, config parsing, NFM2 tiers"
```

---

### Task 2: Extended tier table

**Files:**
- Create: `web/tools/ext-tiers.mjs`
- Create: `web/ext/tiers.js` (generated, then hand-edited)
- Test: `web/ext/tiers.test.js`

**Interfaces:**
- Consumes: `NFM2_CCLASS`, `tierOfClass` from `web/rivals.js` (Task 1).
- Produces: `EXT_TIER: ('C'|'B'|'A'|'S')[39]` exported from `web/ext/tiers.js`.

- [ ] **Step 1: Write the failing test**

`web/ext/tiers.test.js`:

```js
// Extended's car tiers (tiers.js) for the Rivals screen.

import { test } from 'node:test';
import assert from 'node:assert';
import { EXT_TIER } from './tiers.js';
import { EXT_CARS } from './catalog.js';

test('one tier per Extended car, all in C/B/A/S', () => {
  assert.strictEqual(EXT_TIER.length, EXT_CARS.length);
  for (const t of EXT_TIER) assert.ok(['C', 'B', 'A', 'S'].includes(t), t);
});
```

- [ ] **Step 2: Run to verify it fails**

Run: `cd web && node --test ext/tiers.test.js`
Expected: FAIL — `Cannot find module ... tiers.js`.

- [ ] **Step 3: Write the generator**

`web/tools/ext-tiers.mjs`:

```js
// First draft of web/ext/tiers.js from Extended's own car stats:
//   node web/tools/ext-tiers.mjs > web/ext/tiers.js
// Score = mean percentile of top speed (swits[.][2]), acceleration (sum of acelf),
// toughness (log maxmag) and damage dealt (outdam). S = maxmag at or above S_MAXMAG,
// the jump in the data from ~20000 to ~100000 (printed below, check it still holds).
// C/B/A cuts are the pair that puts most of cars 23-38 (the NFM2 cars) in their NFM2
// tier; the ones that still differ go to stderr. Hand edits to tiers.js win over this.
import fs from 'node:fs';
import { EXT_CARS } from '../ext/catalog.js';
import { NFM2_CCLASS, tierOfClass } from '../rivals.js';

const S_MAXMAG = 50000;
const N = EXT_CARS.length;
const src = (f) => fs.readFileSync(new URL(`../ext/${f}`, import.meta.url), 'utf8');
const mad = src('Madness.js'), xtg = src('xtGraphics.js');
const nested = (text, name) => [...text.match(new RegExp(`this\\.${name} = \\[(.*)\\];`))[1]
  .matchAll(/from\(\[([^\]]*)\]\)/g)].map((m) => m[1].split(',').map(Number)).slice(0, N);
const flat = (text, name) => text.match(new RegExp(`this\\.${name} = \\w+\\.from\\(\\[([^\\]]*)\\]\\)`))[1]
  .split(',').map(Number).slice(0, N);

const top = nested(mad, 'swits').map((s) => s[2]);
const acc = nested(mad, 'acelf').map((a) => a[0] + a[1] + a[2]);
const maxmag = flat(mad, 'maxmag');
const outdam = flat(xtg, 'outdam');

const rank = (v) => v.map((x) => (v.filter((y) => y < x).length + (v.filter((y) => y === x).length - 1) / 2) / (v.length - 1));
const parts = [rank(top), rank(acc), rank(maxmag.map(Math.log)), rank(outdam)];
const score = EXT_CARS.map((_, i) => parts.reduce((s, p) => s + p[i], 0) / parts.length);

const isS = maxmag.map((m) => m >= S_MAXMAG);
const want = (i) => (i >= 23 ? tierOfClass(NFM2_CCLASS[i - 23]) : null);
const cuts = [...new Set(score.filter((_, i) => !isS[i]))].sort((a, b) => a - b);
let best = { hits: -1, lo: 0, hi: 0 };
for (const lo of cuts) for (const hi of cuts) {
  if (hi <= lo) continue;
  const t = (s) => (s < lo ? 'C' : s < hi ? 'B' : 'A');
  const hits = EXT_CARS.filter((_, i) => want(i) && !isS[i] && t(score[i]) === want(i)).length;
  if (hits > best.hits) best = { hits, lo, hi };
}
const tier = EXT_CARS.map((_, i) => (isS[i] ? 'S' : score[i] < best.lo ? 'C' : score[i] < best.hi ? 'B' : 'A'));

console.error('maxmag, sorted:', [...maxmag].sort((a, b) => a - b).join(' '));
for (let i = 23; i < N; i++) if (tier[i] !== want(i)) console.error(`differs from NFM2: ${i} ${EXT_CARS[i]} ${tier[i]} (NFM2 ${want(i)})`);
console.log(`// Extended's car tiers for the Rivals screen (rivals.js): C / B / A as the base game's
// classes, S for the ones that shrug off damage (maxmag far above the rest). Drafted by
// web/tools/ext-tiers.mjs from the cars' stats; hand edits here win over the script.
export const EXT_TIER = [
${EXT_CARS.map((n, i) => `  '${tier[i]}',   // ${i} ${n}  score ${score[i].toFixed(2)} maxmag ${maxmag[i]}`).join('\n')}
];`);
```

- [ ] **Step 4: Generate the table and read the output**

Run: `node web/tools/ext-tiers.mjs > web/ext/tiers.js`
Expected: stderr prints the sorted `maxmag` list (check there is a clear gap around `S_MAXMAG`; if the gap sits elsewhere, move `S_MAXMAG` into it and re-run) and the `differs from NFM2:` lines. `web/ext/tiers.js` has 39 entries.

- [ ] **Step 5: Run the test**

Run: `cd web && node --test ext/tiers.test.js`
Expected: PASS.

- [ ] **Step 6: CHECKPOINT — the user reviews the table**

Show the user the 39 rows grouped by tier (name, tier) and the `differs from NFM2` lines. Apply their corrections by editing the entries in `web/ext/tiers.js` directly (append `// hand` to each edited line). Re-run `cd web && node --test ext/tiers.test.js` → PASS.

- [ ] **Step 7: Commit**

```bash
git add web/tools/ext-tiers.mjs web/ext/tiers.js web/ext/tiers.test.js
git commit -m "Extended: car tiers C/B/A/S for the Rivals screen"
```

---

### Task 3: The Rivals screen (`runRivals`) and its strings

**Files:**
- Modify: `web/rivals.js` (append)
- Modify: `web/i18n.js` (`ES` table, near line 75 `'Cars on track'`)

**Interfaces:**
- Consumes: `toggleTier` (Task 1), `tr` from `web/i18n.js`.
- Produces: `runRivals({ host: HTMLElement, title?: string, cars: {i:number, name:string, tier:string}[], tiers: string[], min: number, max: number, cfg: Cfg }) -> Promise<Cfg|null>` — resolves with the chosen config on Race / Enter, `null` on Back / Esc. Keys: `◂ ▸` change the count; all keys are kept from the game while it is open (capture listener, `stopImmediatePropagation`).

- [ ] **Step 1: Append the screen to `web/rivals.js`**

```js
const esc = (s) => String(s).replace(/[&<>"]/g, (ch) => ({ '&': '&amp;', '<': '&lt;', '>': '&gt;', '"': '&quot;' })[ch]);

/**
 * The screen, over `host` (the stage box the game's canvases are in). Mouse for the
 * pool and the pinned slots; ◂ ▸ the count, Enter races, Esc goes back. Every key is
 * kept from the game while it is up; controls give their focus back after each change
 * (as the stage selects' <select>s do) so the keys keep reaching it.
 */
export function runRivals({ host, title = '', cars, tiers, min, max, cfg }) {
  const st = { count: cfg.count, mode: cfg.mode, pool: [...cfg.pool], fixed: [...cfg.fixed] };
  const blue = 'rgb(47,179,255)';
  const btn = `cursor:pointer;background:#000;color:#fff;border:1px solid ${blue};font:bold 13px Arial,sans-serif;padding:3px 10px`;
  const sel = `background:#000;color:${blue};border:1px solid ${blue};font:bold 12px Arial,sans-serif`;
  const box = document.createElement('div');
  box.style.cssText = 'position:absolute;inset:0;z-index:6;overflow:auto;box-sizing:border-box;padding:12px 20px;'
    + 'background:rgba(0,0,0,.85);color:#fff;font:bold 13px Arial,sans-serif;display:flex;flex-direction:column;gap:8px';
  const opt = (v, text, on) => `<option value="${v}"${on ? ' selected' : ''}>${esc(text)}</option>`;
  const radio = (v, label) =>
    `<label><input type="radio" name="rv-mode" value="${v}"${st.mode === v ? ' checked' : ''}> ${esc(tr(label))}</label>`;
  const render = () => {
    box.innerHTML = `
      <div style="text-align:center;font-size:20px">${esc(tr('Rivals'))}${title ? ` — ${esc(title)}` : ''}</div>
      <div>${esc(tr('Cars on track'))}: <button data-k="less" style="${btn}">◂</button>
        <span style="display:inline-block;min-width:28px;text-align:center">${st.count}</span>
        <button data-k="more" style="${btn}">▸</button></div>
      <div>${esc(tr('Draw'))}: ${radio('game', "Game's pick")} ${radio('pool', 'Only my pool')}</div>
      <div>${esc(tr('Tiers'))}: ${tiers.map((t) => `<button data-tier="${t}" style="${btn}">${t}</button>`).join(' ')}
        <button data-k="all" style="${btn}">${esc(tr('All'))}</button>
        <button data-k="none" style="${btn}">${esc(tr('None'))}</button></div>
      ${tiers.map((t) => `<div style="display:flex;flex-wrap:wrap;gap:2px 12px;opacity:${st.mode === 'pool' ? 1 : 0.5}">
        <b style="color:${blue};width:16px">${t}</b>
        ${cars.filter((c) => c.tier === t).map((c) =>
          `<label><input type="checkbox" data-car="${c.i}"${st.pool.includes(c.i) ? ' checked' : ''}> ${esc(c.name)}</label>`).join('')}
      </div>`).join('')}
      <div style="display:flex;flex-wrap:wrap;gap:4px 10px;align-items:center">${esc(tr('Fixed slots'))}:
        ${Array.from({ length: st.count - 1 }, (_, k) => `<span>${k + 1} <select data-slot="${k}" style="${sel}">`
          + opt('', tr('Random'), st.fixed[k] == null)
          + cars.map((c) => opt(c.i, c.name, st.fixed[k] === c.i)).join('') + '</select></span>').join('')}</div>
      <div style="display:flex;justify-content:space-between;align-items:center;margin-top:auto">
        <button data-k="back" style="${btn}">◂ ${esc(tr('Stage'))}</button>
        <span style="opacity:.8;font-size:12px">${esc(tr('◂ ▸ cars · Enter race · Esc back to the stage'))}</span>
        <button data-k="go" style="${btn}">${esc(tr('Race'))}</button></div>`;
    for (const el of box.querySelectorAll('input,select,button')) el.tabIndex = -1;
  };
  return new Promise((resolve) => {
    const result = () => ({ ...st, fixed: st.fixed.slice(0, st.count - 1) });
    const done = (v) => { removeEventListener('keydown', onKey, true); box.remove(); resolve(v); };
    const setCount = (n) => { st.count = Math.max(min, Math.min(max, n)); render(); };
    const onKey = (e) => {
      e.stopImmediatePropagation();      // the game's keys stay the game's: none of them while this is up
      if (e.key === 'ArrowLeft' || e.key === 'ArrowRight') { e.preventDefault(); setCount(st.count + (e.key === 'ArrowRight' ? 1 : -1)); }
      // a held Enter from the stage select must not race straight through
      else if (e.key === 'Enter' && !e.repeat) { e.preventDefault(); done(result()); }
      else if (e.key === 'Escape') { e.preventDefault(); done(null); }
    };
    box.addEventListener('mousedown', (e) => e.stopPropagation());   // not a click on the game's screen
    box.addEventListener('click', (e) => {
      const b = e.target.closest('button');
      if (!b) return;
      b.blur();
      const k = b.dataset.k;
      if (k === 'back') return done(null);
      if (k === 'go') return done(result());
      if (k === 'less' || k === 'more') return setCount(st.count + (k === 'more' ? 1 : -1));
      if (b.dataset.tier) st.pool = toggleTier(st.pool, cars, b.dataset.tier);
      if (k === 'all') st.pool = cars.map((c) => c.i);
      if (k === 'none') st.pool = [];
      render();
    });
    box.addEventListener('change', (e) => {
      const el = e.target;
      el.blur();
      if (el.name === 'rv-mode') st.mode = el.value;
      else if (el.dataset.car !== undefined) {
        const i = +el.dataset.car;
        st.pool = el.checked ? [...st.pool, i] : st.pool.filter((x) => x !== i);
      } else if (el.dataset.slot !== undefined) st.fixed[+el.dataset.slot] = el.value === '' ? null : +el.value;
      render();
    });
    addEventListener('keydown', onKey, true);
    render();
    host.append(box);
  });
}
```

- [ ] **Step 2: Add the Spanish strings**

First check which already exist: `grep -n "'Rivals'\|'Draw'\|\"Game's pick\"\|'Only my pool'\|'Tiers'\|'All'\|'None'\|'Fixed slots'\|'Random'\|'Stage':" web/i18n.js`. Add only the missing ones to the `ES` object, next to `'Cars on track': 'Autos en pista'` (`web/i18n.js:75`):

```js
  'Rivals': 'Rivales', 'Draw': 'Sorteo', "Game's pick": 'Como el juego', 'Only my pool': 'Solo mi pool',
  'Tiers': 'Tiers', 'All': 'Todos', 'None': 'Ninguno', 'Fixed slots': 'Puestos fijos', 'Random': 'Al azar',
  'Stage': 'Pista', '◂ ▸ cars · Enter race · Esc back to the stage': '◂ ▸ autos · Enter correr · Esc volver a la pista',
```

A duplicate key in an object literal silently overwrites; if `'Stage'` (or any other) already exists with a different Spanish text, keep the existing one and drop it from this line.

- [ ] **Step 3: Run the whole suite**

Run: `cd web && node --test`
Expected: all tests PASS (the module still imports under node: no DOM at top level).

- [ ] **Step 4: Commit**

```bash
git add web/rivals.js web/i18n.js
git commit -m "Rivals: the screen (count, draw mode, tier pool, pinned slots)"
```

---

### Task 4: NFM2 Free Play — launcher and main.js

**Files:**
- Modify: `web/launcher.js` (imports at top; `startCarSelect` at ~605-673)
- Modify: `web/main.js:23` (import), `:234` (`sameCars`), `:367-378` (grid branch)

**Interfaces:**
- Consumes: `runRivals`, `loadRivals`, `saveRivals`, `parseRivals`, `pickRivals`, `RIVALS_KEY`, `NFM2_CCLASS`, `tierOfClass` (Tasks 1, 3); `random` from `web/java.js`.
- Produces: race params `players=<count>` and `rivals=<JSON {mode, pool, fixed}>` for NFM2 Free Play.

- [ ] **Step 1: main.js applies `?rivals=`**

`web/main.js:23`:

```js
import { objArray, random, setDrawPhase, setSeed } from './java.js';
```

Add after the other imports:

```js
import { parseRivals, pickRivals } from './rivals.js';
```

After `const sameCars = params.get('cars') === 'same';` (`:234`):

```js
  // ?rivals=: Free Play's Rivals screen (rivals.js), the field's size being ?players=
  const rivals = params.has('rivals') ? parseRivals(params.get('rivals'), 16, 8) : null;
```

Change `} else if (sameCars) {` to `} else if (sameCars && !rivals) {`, and in the final `else` branch, after `xt.sc[7] = car;`:

```js
    if (rivals) {
      const sc = pickRivals(rivals, xt.sc, players, random);
      for (let k = 1; k < players; ++k) xt.sc[k] = sc[k];
    }
```

- [ ] **Step 2: Run the suite**

Run: `cd web && node --test`
Expected: all PASS (no race is built with `?rivals=` yet; nothing else changed).

- [ ] **Step 3: launcher opens Rivals after the stage select**

Top of `web/launcher.js`, with the other static imports:

```js
import { RIVALS_KEY, NFM2_CCLASS, loadRivals, runRivals, saveRivals, tierOfClass } from './rivals.js';
```

In `startCarSelect`, replace `let slot = null;` / `let stage = null;` and the `for (;;)` loop with:

```js
  let slot = null;
  let stage = null;
  let rivals = null;
  try {
    const { runCarSelect, runStageSelect } = await import('./carselect.js');
    const cur = CARS[V.car.get()];
    let start = cur && !cur.custom ? cur.slot : 0;
    let stagePick = gmode ? S.stage : (S.mystage || S.stage);
    // Car, then stage, as the Java orders them; free play then its Rivals screen
    // (rivals.js). Esc on Rivals goes back to the stage, Esc on the stage select
    // back to the car select, Esc there leaves to this menu.
    for (;;) {
      slot = await runCarSelect(canvas, start, careerFor(gmode));
      if (slot === null) break;
      start = slot;
      if (gmode) saveCareer(gmode, slot, null);      // setcarcookie on the pick
      for (;;) {
        stage = await runStageSelect(canvas, stagePick, careerFor(gmode));
        if (stage === null || gmode) break;
        stagePick = stage;
        rivals = await runRivals({
          host: $('stage'),
          title: typeof stage === 'string' ? stage : STAGES.find((s) => s.n === stage)?.name,
          cars: CARS.filter((c) => !c.custom).map((c) => ({ i: c.slot, name: c.name, tier: tierOfClass(NFM2_CCLASS[c.slot]) })),
          tiers: ['C', 'B', 'A'], min: 1, max: 8,
          cfg: loadRivals(RIVALS_KEY.nfm2, 16, 8),
        });
        if (rivals) break;
      }
      if (stage !== null) break;
    }
```

(The `catch` block and everything up to `const extra = custom ? ...` stay as they are.) Then, after the `if (gmode) { ... }` block and before `await startRace(null, extra);`:

```js
  if (!gmode && rivals) {
    saveRivals(RIVALS_KEY.nfm2, rivals);
    extra.players = rivals.count;
    extra.rivals = JSON.stringify({ mode: rivals.mode, pool: rivals.pool, fixed: rivals.fixed });
  }
```

- [ ] **Step 4: Run the suite**

Run: `cd web && node --test`
Expected: all PASS.

- [ ] **Step 5: Browser check (real browser, `python3 web/tools/serve.py 8123`)**

Open `http://localhost:8123/` → Single Player → Free Play. Check each, noting results:
1. Car → stage → Rivals appears over the stage; the title is the stage's name.
2. `Enter` directly races 7 cars drawn by the game (as before).
3. Esc on Rivals → back on the same stage's select. Esc again → car select.
4. Only my pool, None, tick only M A S H E E N, count 8 → race: all 7 opponents are MASHEEN.
5. Pin slot 1 to DR Monstaa with the game's pick → DR Monstaa is in the grid.
6. Count 1 → a one-car race starts and finishes the lap.
7. Back to the launcher and in again: the last config is remembered.
8. NFM 1 / NFM 2 careers: car → stage → race with no Rivals screen.

- [ ] **Step 6: Commit**

```bash
git add web/launcher.js web/main.js
git commit -m "NFM2 Free Play: the Rivals screen after the stage select"
```

---

### Task 5: Extended Free Play — race.js, menus.js, launcher row removal

**Files:**
- Modify: `web/ext/race.js:67-71` (`freePlayPlayers`), `:186-215` (`readdata` wrapper), `:350-357` (`runMenus` call)
- Modify: `web/ext/menus.js` (`runMenus` signature ~109; `xt.stageselect` free-play branch ~184-205; `onKey` ~434)
- Modify: `web/launcher.js:38,45,76-81,212-213,907` (`extplayers`)

**Interfaces:**
- Consumes: `RIVALS_KEY`, `loadRivals`, `pickRivals`, `runRivals` (Tasks 1, 3); `EXT_TIER` (Task 2); `EXT_CARS` from `web/ext/catalog.js`.
- Produces: `runMenus({ ..., setPlayers: (n: number) => void })` — new option, free play only.

- [ ] **Step 1: race.js — the count comes from Rivals storage, the grid from `pickRivals`**

Imports in `web/ext/race.js`:

```js
import { RIVALS_KEY, loadRivals, pickRivals } from '../rivals.js';
import { EXT_CARS } from './catalog.js';
```

(If `race.js` already imports from `./catalog.js`, add `EXT_CARS` to that import instead.)

Replace `const freePlayPlayers = free && Number.isInteger(playersParam) ? Math.max(1, Math.min(19, playersParam)) : null;` with:

```js
  // Free Play's field: ?players= (developer mode, the self-tests), else the Rivals
  // screen's (rivals.js), which menus.js updates through setPlayers before the stage reloads
  let freePlayPlayers = !free ? null : Number.isInteger(playersParam)
    ? Math.max(1, Math.min(19, playersParam)) : loadRivals(RIVALS_KEY.ext, EXT_CARS.length, 19).count;
```

In the `gs.readdata` wrapper, change `if (freePlayPlayers === 1) {` to `if (free) {` and make the wrapped `stat$m` check the live count first:

```js
      if (free) {
        // Extended treats zero wasted opponents as an immediate wasting win.
        // In a one-car time trial, skip that one check so the lap can finish.
        // The count can change on the Rivals screen after this, so it is read per call.
        const stat = xt.stat$m;
        xt.stat$m = function (...a) {
          if (freePlayPlayers !== 1) return stat.apply(this, a);
          let firstWastedRead = true;
          a[1] = new Proxy(a[1], {
            get(target, key, receiver) {
              if (key === 'wasted' && firstWastedRead) {
                firstWastedRead = false;
                return Math.max(1, Reflect.get(target, key, receiver));
              }
              return Reflect.get(target, key, receiver);
            },
          });
          return stat.apply(this, a);
        };
        // The Rivals screen's pool and pinned slots over the game's own draw
        const sortcars = xt.sortcars;
        xt.sortcars = function (...a) {
          sortcars.apply(this, a);
          if (params.get('selftest')) return;   // a self-test's hash must not depend on this browser's stored pool
          const sc = pickRivals(loadRivals(RIVALS_KEY.ext, EXT_CARS.length, 19), this.sc, this.nplayers, random);
          for (let k = 1; k < this.nplayers; k++) this.sc[k] = sc[k];
        };
      }
```

In the `runMenus({...})` call add:

```js
      setPlayers: (n) => { freePlayPlayers = n; },
```

- [ ] **Step 2: menus.js — Rivals on the stage select's Enter, then reload and race**

Imports in `web/ext/menus.js`:

```js
import { RIVALS_KEY, loadRivals, runRivals, saveRivals } from '../rivals.js';
import { EXT_TIER } from './tiers.js';
```

(`EXT_CARS` comes from `./catalog.js`; add it to that module's existing import if it is not already imported.)

Add `setPlayers = () => {}` to the destructured `runMenus` options, and document it in the JSDoc: `@param o.setPlayers  (n) -> the free-play field size race.js gives randomno (the Rivals screen)`.

Immediately before `xt.stageselect = function (checkpoints, c, madness) {`, add:

```js
  // Free play's Rivals screen (rivals.js), after the stage select's Enter. The stage is
  // already loaded with the old field, so its choice reloads it (fase 6476: randomno,
  // loadstage, sortcars -- race.js applies the pool) and the reload goes straight to the race.
  let rivalsOpen = false, raceOnLoad = false;
  const openRivals = async (name) => {
    rivalsOpen = true;
    ui.style.display = 'none';
    const cfg = await runRivals({
      host, title: name,
      cars: EXT_CARS.map((n, i) => ({ i, name: n, tier: EXT_TIER[i] })),
      tiers: ['C', 'B', 'A', 'S'], min: 1, max: 19,
      cfg: loadRivals(RIVALS_KEY.ext, EXT_CARS.length, 19),
    });
    rivalsOpen = false;
    ui.style.display = '';
    painted = '';
    if (!cfg) return;                    // Esc: back to this stage's select
    saveRivals(RIVALS_KEY.ext, cfg);
    setPlayers(cfg.count);
    raceOnLoad = true;
    xt.fase = 6476;
  };
```

In `xt.stageselect`, in the free-play branch (after `if (career) { ... }`), make the start:

```js
    if (raceOnLoad) {                    // the stage reloaded with the Rivals screen's field: race it
      raceOnLoad = false;
      go(c, `Stage ${pick.stage}:  ${checkpoints.name}`);
      return;
    }
```

After `paint();` (the free-play one, after `this.stages.play();`):

```js
    if (rivalsOpen) { c.left = c.right = c.up = c.down = c.enter = c.handb = false; return; }
```

Replace the Enter handling at the end of the free-play branch:

```js
    if (c.enter || c.handb) {
      c.enter = c.handb = false;
      savePick(pick);
      openRivals(checkpoints.name);
    }
```

In `onKey`, first line of the function body:

```js
      if (rivalsOpen) return;             // the Rivals screen has the keys (its own capture listener)
```

`ui`, `painted` and `host` are defined in `runMenus`' scope. `ui`/`painted` are declared later in the function (`const ui` ~376, `let painted` ~413) — `openRivals` only runs after they exist, so the reference is safe.

- [ ] **Step 3: launcher — drop "Free Play cars"**

In `web/launcher.js`:
- `:38` DEFAULTS: remove `extplayers: 7, `.
- `:45`: delete the `S.extplayers = ...` line.
- `:76-81`: delete the `extplayers: { ... },` entry of `V`.
- `:212-213`: remove the `+ \`<li class="item orow" data-row="extplayers">...\`` part, ending the statement after `.join('')`.
- `:907`: `case 'ext':   return void startRace(null, { ext: arg });`
- `:207` comment: `(freeplay.js)` → `(ext/menus.js)`.

Run: `grep -n "extplayers" web/*.js web/ext/*.js`
Expected: no matches.

- [ ] **Step 4: Run the suite**

Run: `cd web && node --test`
Expected: all PASS.

- [ ] **Step 5: Browser check (real browser)**

Launcher → Extended Edition → Free Play. Check each, noting results:
1. The menu no longer shows "Free Play cars".
2. Car → stage → Enter → Rivals over the stage, tiers C B A S; the stage keeps flying around behind.
3. `Enter` directly → the stage reloads and the race starts with 7 cars.
4. Esc on Rivals → same stage select, arrows work there again; Esc there → car select.
5. Only my pool, None, tier S only, count 19 → race: every opponent is an S car.
6. Only my pool, only Nimi (index 27), an NFM2-group stage → all opponents are Nimi.
7. Pin slot 1 to Tesco Lorry → it races.
8. Count 1 from the screen → the one-car lap finishes (no instant win).
9. Reload the page into Free Play → last config remembered.
10. Career Mode → no Rivals screen.

- [ ] **Step 6: Commit**

```bash
git add web/ext/race.js web/ext/menus.js web/launcher.js
git commit -m "Extended Free Play: the Rivals screen; drop the launcher's Free Play cars row"
```

---

### Task 6: Docs and deploy

**Files:**
- Modify: `TASKS.md`, `WORK.md`

- [ ] **Step 1: TASKS.md**

Under the Extended Free Play entries (~line 1044-1063), add:

```markdown
- [x] **Rivals screen (2026-09-27)**, NFM2 and Extended Free Play: after the stage
      select, `web/rivals.js` sets the field size (1-8 / 1-19), draws opponents from a
      pool ticked by car or by tier (C/B/A from `CarDefine.cclass`; Extended's C/B/A/S
      in `web/ext/tiers.js`, drafted by `web/tools/ext-tiers.mjs`) and pins cars to
      slots. Replaces Extended's launcher "Free Play cars" row. Spec:
      `docs/superpowers/specs/2026-09-27-free-play-rivals-design.md`.
```

And at `:1284-1285` (base-only `?cars=same` / `?players`), note that Extended now has both through the Rivals screen.

- [ ] **Step 2: WORK.md — append at the bottom**

```markdown
## 2026-09-27 — Rivals screen
- **Extended's stage select has already placed the field.** fase 6476 -> randomno (nplayers) -> fase 2 loadstage -> resetstat -> sortcars -> u[j].reset, all while the stage flies around behind the select. A field chosen after it needs the stage reloaded (`xt.fase = 6476`), which menus.js does and then goes straight to fase 5.
- **The one-car wasted-check proxy has to read the count per call.** It was installed only when `?players=1` at readdata; with the count chosen later on the Rivals screen it must be installed for all of free play and check `freePlayPlayers` inside.
```

Add any other surprise met during Tasks 4-5 the same way.

- [ ] **Step 3: Full suite, commit, deploy**

```bash
cd web && node --test && cd ..
git add TASKS.md WORK.md docs/superpowers/specs/2026-09-27-free-play-rivals-design.md
git commit -m "Docs: Rivals screen in TASKS and WORK"
git push origin main
gh run watch -R HopeAero/nfm
```

Expected: tests PASS; the Pages workflow succeeds. Open https://hopeaero.github.io/nfm/ and repeat Task 4 step 5 check 4 and Task 5 step 5 check 5 on the deployed site. Tell the user it is deployed.
