// Free Play's car select past car 38. The jar's normal-mode arrows step sc[0] with
// ++/-- and then move aconto[sc[0]] in the same call, so the step itself goes through
// nextCar (ext-patch newcar-carselect): 38 <-> NEW_BASE, never 39 (a road piece) or 199.
// A remembered new car is found again by name, since its index moves with the list.

import { test } from 'node:test';
import assert from 'node:assert';
import fs from 'node:fs';
import { setNewCars, setCarGroup, NEW_BASE } from './newcars.js';
import { pickCar, loadPick, clampCarArrows } from './menus.js';

test("the car select's normal-mode steps go through nextCar", () => {
  const src = fs.readFileSync(new URL('xtGraphics.js', import.meta.url), 'utf8');
  assert.match(src, /sc\[n15\] = nextCar\(sc\[n15\], 1\);/);
  assert.match(src, /sc3\[n18\] = nextCar\(sc3\[n18\], -1\);/);
  assert.doesNotMatch(src, /\+\+sc\[n15\];|--sc3\[n18\];/);
});

test('a pick naming a missing new car falls back', () => {
  setNewCars([{ name: 'A', donor: 30 }]);
  assert.strictEqual(pickCar({ car: NEW_BASE, carName: 'A' }), NEW_BASE);
  assert.strictEqual(pickCar({ car: NEW_BASE, carName: 'Gone' }), 38);
  assert.strictEqual(pickCar({ car: 7 }), 7);
  setNewCars([{ name: 'B', donor: 1 }, { name: 'A', donor: 30 }]);
  assert.strictEqual(pickCar({ car: NEW_BASE, carName: 'A' }), NEW_BASE + 1);   // by name, not slot
});

test("the car select sets a new car's ground as it sets the 39's (its shadow under the car)", () => {
  const src = fs.readFileSync(new URL('xtGraphics.js', import.meta.url), 'utf8');
  assert.match(src, /for \(let a = 0; a < 39; a = i32\(a \+ 1\)\) \{\n      aconto\[a\]\.groundlevel = -34;\n    \}\n    for \(let a = NEW_BASE; aconto\[a\]; a\+\+\) aconto\[a\]\.groundlevel = -34;/);
});

test('a new car named like a stock car does not steal the stock pick', () => {
  setNewCars([{ name: 'Tornado Shark', donor: 23 }]);
  assert.strictEqual(pickCar({ car: 23, carName: 'Tornado Shark' }), 23);          // stock: by index
  assert.strictEqual(pickCar({ car: NEW_BASE, carName: 'Tornado Shark' }), NEW_BASE);   // new: by name
});

test("race.js takes Free Play's new cars from the Car Maker listing, not a launcher store", () => {
  const src = fs.readFileSync(new URL('race.js', import.meta.url), 'utf8');
  assert.match(src, /await listAll\(\)/);
  assert.doesNotMatch(src, /newcars-store|readNewCarStore/);
});

test('the pick opens the group that holds its car', () => {
  setNewCars([{ name: 'A', donor: 30 }]);
  const store = {};
  globalThis.localStorage = { getItem: (k) => store[k] ?? null, setItem: (k, v) => { store[k] = v; } };
  try {
    store['nfm.ext.free'] = JSON.stringify({ car: 7 });
    assert.strictEqual(loadPick(['nfm2', 'ext']).carGroup, 'game');
    store['nfm.ext.free'] = JSON.stringify({ car: NEW_BASE, carName: 'A', carGroup: 'game' });   // Try in Extended
    assert.strictEqual(loadPick(['nfm2', 'ext']).carGroup, 'mine');
    store['nfm.ext.free'] = JSON.stringify({ car: NEW_BASE, carName: 'Gone', carGroup: 'mine' });   // deleted: car 38
    assert.deepStrictEqual([loadPick(['nfm2', 'ext']).car, loadPick(['nfm2', 'ext']).carGroup], [38, 'game']);
    store['nfm.ext.free'] = '{}';
    assert.strictEqual(loadPick(['nfm2', 'ext']).carGroup, 'game');
  } finally { delete globalThis.localStorage; setCarGroup('game'); }
});

test('the car select arrows stop at the ends of the group', () => {
  setNewCars([{ name: 'A', donor: 30 }]);
  setCarGroup('mine');
  const c = { left: true, right: true };
  clampCarArrows(c, NEW_BASE);
  assert.deepStrictEqual(c, { left: false, right: false });
  setCarGroup('game');
  const d = { left: true, right: true };
  clampCarArrows(d, 38);
  assert.deepStrictEqual(d, { left: true, right: false });
});

test("the car select credits a new car's own author, never its donor's", () => {
  const src = fs.readFileSync(new URL('xtGraphics.js', import.meta.url), 'utf8');
  assert.match(src, /if \(isNew\(this\.sc\[0\]\)\) \{\n\s+const by = newCars\(\)\[this\.sc\[0\] - NEW_BASE\]\?\.author;\n\s+if \(by\) this\.drawcs\(60, 'Created by ' \+ by, 246, 246, 246, 3\);\n\s+\} else \{/);
  assert.match(src, /'Created by Excalibur', 246, 246, 246, 3\);\n        \}\n        \}   \/\/ ext-patch newcar-credit-close/);
});
