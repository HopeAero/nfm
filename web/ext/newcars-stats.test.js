// A Car Maker .rad as an Extended new car (newcars-stats.js): CarDefine's physics,
// CarDefine.loadcar's validation, and a donor by class when none is chosen.

import { test } from 'node:test';
import assert from 'node:assert';
import fs from 'node:fs';
import { carFromRad, defaultDonor, STAT_FIELDS } from './newcars-stats.js';
import { setOwn, writeOwnStats, writeOwnPhys } from './extlines.js';

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
  assert.ok(a.stat.maxmag > 0);
  assert.strictEqual(b.stat.maxmag, Math.trunc(Math.fround(a.stat.maxmag * 200 / 100)));
  assert.strictEqual(b.stat.dammult, Math.fround(a.stat.dammult * 50 / 100));
  const c = carFromRad('Simple Car', simple + '\nexthealth(1000)\n');   // invalid: ignored, not clamped
  assert.strictEqual(c.stat.maxmag, a.stat.maxmag);
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

test("a new car's author is its carmaker() line; none, none", () => {
  assert.strictEqual(carFromRad('Simple Car', simple + '\ncarmaker(Excalibur)\n').author, 'Excalibur');
  assert.strictEqual(carFromRad('Simple Car', simple.replace(/^carmaker\(.*$/m, '')).author, '');
});
