// The stage maker's placement rules (place.js): roads join end to end,
// checkpoints mount on the road under them and take its surface's type, and
// the readiness list names what stops a stage from racing.
import test from 'node:test';
import assert from 'node:assert';
import { Medium } from '../Medium.js';
import { snap, snapNear, problems } from './place.js';

const m = new Medium();
const maxR = () => 3000;
const start = { sp: 37, x: 0, z: 0, rot: 0, wh: 0, y: 0 };

test('a NormalRoad dropped near the start snaps exactly end to end', () => {
  // NormalRoad's ends are +-2800 on z; the start's are too. Dropped 150 off
  // in x and 120 short in z, it is pulled onto the start's far end.
  const s = snap([start], 0, 150, 5600 - 120, 0, maxR, m);
  assert.deepStrictEqual([s.x, s.z], [0, 5600]);
});

test('the applet pull is 200 units; snapNear widens it for clicks, same landing spot', () => {
  const far = snap([start], 0, 0, 5600 - 450, 0, maxR, m);
  assert.deepStrictEqual([far.x, far.z], [0, 5150], 'outside 200: left where it was put');
  const pulled = snapNear([start], 0, 0, 5600 - 450, 0, maxR, m, 800);
  assert.deepStrictEqual([pulled.x, pulled.z], [0, 5600]);
});

test('a checkpoint mounts on the road under it, asphalt or dirt', () => {
  const asphalt = { sp: 0, x: 0, z: 5600, rot: 90, wh: 0, y: 0 };
  const dirt = { sp: 5, x: 0, z: 11200, rot: 180, wh: 0, y: 0 };
  const a = snap([start, asphalt, dirt], 30, 300, 5800, 0, maxR, m);
  assert.deepStrictEqual([a.sp, a.x, a.z, a.rot], [30, 0, 5600, 90]);
  const d = snap([start, asphalt, dirt], 30, -200, 11000, 0, maxR, m);
  assert.deepStrictEqual([d.sp, d.x, d.z, d.rot], [32, 0, 11200, 0], 'dirt checkpoint; a checkpoint has no front');
});

test('trees and the fixing hoop go exactly where they are put', () => {
  const t = snap([start], 55, 1234.4, -987.6, 0, maxR, m);
  assert.deepStrictEqual([t.x, t.z], [1234, -988]);
});

test('problems: a start first, two checkpoints, at most five hoops', () => {
  assert.deepStrictEqual(problems([start, { sp: 30 }, { sp: 32 }]), []);
  assert.strictEqual(problems([{ sp: 0 }, { sp: 30 }, { sp: 30 }]).length, 1);
  assert.strictEqual(problems([start, { sp: 30 }]).length, 1);
  assert.strictEqual(problems([start, { sp: 30 }, { sp: 30 }, ...Array(6).fill({ sp: 31 })]).length, 1);
});
