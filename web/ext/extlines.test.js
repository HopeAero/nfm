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

test('car 0 is a valid special', () => {
  assert.strictEqual(readExt(writeSpecial(simple, 0)).special, 0);
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
