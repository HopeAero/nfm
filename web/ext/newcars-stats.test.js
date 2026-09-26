// A Car Maker .rad as an Extended new car (newcars-stats.js): CarDefine's physics,
// CarDefine.loadcar's validation, and a donor by class when none is chosen.

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
