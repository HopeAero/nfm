// Extended's per-car tables grow to hold the new cars (newcars-grow.js). A table
// left out would read undefined -> NaN physics with no error, so the lists are
// checked against every length-39 field the constructors make.

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
const flat = (v) => (v?.length !== undefined && typeof v !== 'string' ? Array.from(v, flat).flat() : [v]);

test('the lists name every per-car table (a regeneration that adds one fails here)', () => {
  setNewCars([]);
  assert.deepStrictEqual(len39(new Madness({}, {}, {}, 0)).sort(), [...MAD_TABLES].sort());
  assert.deepStrictEqual(len39(new xtGraphics({}, null, null, {})).sort(), [...XT_TABLES].sort());
});

test('a new car gets a column in every table: finite, stock columns untouched', () => {
  setNewCars([]);
  const before = JSON.stringify(MAD_TABLES.map((k) => flat(Array.from(new Madness({}, {}, {}, 0)[k]))));
  setNewCars([carFromRad('Simple Car', simple, 30)]);
  const m = new Madness({}, {}, {}, 0), x = new xtGraphics({}, null, null, {});
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

test('a donor column is a copy: changing the new car leaves the donor alone', () => {
  setNewCars([carFromRad('Simple Car', simple, 30)]);
  const x = new xtGraphics({}, null, null, {});
  growXt(x);
  x.specialstats[NEW_BASE][0][0] = 99;
  assert.notStrictEqual(x.specialstats[30][0][0], 99);
});
