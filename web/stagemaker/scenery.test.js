// scenery.js head-line rules: where savefile puts each line, the RGB mask cap
// and the lights rule (StageMaker.java:3161-3245).
import test from 'node:test';
import assert from 'node:assert';
import { getLine, setLine, capSnap, lightsFor } from './scenery.js';

test('lines are edited in place, added around nlaps as savefile orders them, and removed', () => {
  const head = ['snap(0,0,0)', 'sky(1,2,3)', 'nlaps(5)'];
  setLine(head, 'sky', [9, 9, 9]);
  setLine(head, 'fog', [4, 5, 6]);
  setLine(head, 'soundtrack', ['airy.mod', 200, 90]);
  setLine(head, 'lightson', []);
  assert.deepStrictEqual(head, ['snap(0,0,0)', 'sky(9,9,9)', 'fog(4,5,6)', 'nlaps(5)', 'lightson()', 'soundtrack(airy.mod,200,90)']);
  assert.deepStrictEqual(getLine(head, 'soundtrack'), ['airy.mod', '200', '90']);
  setLine(head, 'lightson', null);
  assert.strictEqual(getLine(head, 'lightson'), null);
});

test('the mask sliders sum to at most 200, and a dark mask means car lights', () => {
  const s = capSnap([60, 60, 60], 0);
  assert.strictEqual(s[0], 60);                       // the moved channel keeps its value
  assert.ok(s.reduce((a, v) => a + Math.trunc(v / 1.2 + 50), 0) <= 200);
  assert.strictEqual(lightsFor([0, 0, 0]), false);    // sliders 50+50+50
  assert.strictEqual(lightsFor([-50, -50, -50]), true);
});
