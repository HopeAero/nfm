// The Rivals wrapper over Extended's real sortcars (rivals.js withRivals).

import { test } from 'node:test';
import assert from 'node:assert';
import * as mod from './xtGraphics.js';
import { withClassicField, withRivals } from '../rivals.js';

const XT = Object.values(mod).find((v) => typeof v === 'function' && v.prototype?.sortcars);
const seq = (...v) => { let k = 0; return () => v[k++ % v.length]; };

function world(nplayers, car) {
  const x = Object.create(XT.prototype);
  Object.assign(x, {
    bonstage: false, nplayers, careermode: false, classicmode: false, unlocked: Int32Array.from([27, 27]),
    hardstage: false, sc: new Int32Array(101), proba: new Float32Array(101).fill(0.1), dontdisplay: false,
    ptmatch: 0, rollonce: false, averagelevel: 0, maxlevel: new Int32Array(40),
    bonusstage: [false, false, false, false], m: { random: Math.random },
  });
  x.sc[0] = car;
  return x;
}

test('one car on track: the player keeps their car (sortcars puts the stage boss in slot nplayers-1)', () => {
  for (const stage of [1, 4, 10, 16]) {
    const x = world(1, 3);
    withRivals(XT.prototype.sortcars, () => ({ mode: 'game', pool: [], fixed: [] }), seq(0)).call(x, stage);
    assert.strictEqual(x.sc[0], 3, `stage ${stage}`);
  }
});

test('the pool replaces the game draw, player slot untouched', () => {
  const x = world(5, 3);
  withRivals(XT.prototype.sortcars, () => ({ mode: 'pool', pool: [22], fixed: [] }), seq(0)).call(x, 4);
  assert.deepStrictEqual(Array.from(x.sc.slice(0, 5)), [3, 22, 22, 22, 22]);
});

test("classic (NFM 2) stages past the jar's 7 cars: the draw ends, every car an NFM 2 one", () => {
  // the jar's classic draw never repeats a car and has 16: 19 cars looped forever
  for (const stage of [1, 12, 17]) {
    const x = world(19, 21);
    x.classicmode = true;
    withClassicField(XT.prototype.sortcars, seq(0.3, 0.7, 0.05, 0.9)).call(x, stage);
    assert.strictEqual(x.nplayers, 19);
    assert.strictEqual(x.sc[0], 21, 'player kept');
    for (let k = 1; k < 19; k++) assert.ok(x.sc[k] >= 23 && x.sc[k] <= 38, `stage ${stage} slot ${k}: ${x.sc[k]}`);
  }
});

test("classic with the jar's own field size is the jar untouched", () => {
  const a = world(7, 21), b = world(7, 21);
  a.classicmode = b.classicmode = true;
  withClassicField(XT.prototype.sortcars, seq(0.5)).call(a, 5);
  assert.strictEqual(a.nplayers, 7);
  assert.ok(Array.from(a.sc.slice(1, 7)).every((c) => c >= 23 && c <= 38));
});
