import test from 'node:test';
import assert from 'node:assert';
import { WheelSweep, buildTrackGrid, nearTrackers } from './trackgrid.js';

test('nearTrackers finds exactly what the full sweep finds, in the same order', () => {
  let seed = 7;
  const rnd = (n) => { seed = (seed * 1103515245 + 12345) & 0x7fffffff; return seed % n; };
  const nt = 3000;
  const t = { nt, x: new Int32Array(nt), z: new Int32Array(nt), radx: new Int32Array(nt), radz: new Int32Array(nt) };
  for (let k = 0; k < nt; k++) {
    t.x[k] = rnd(120000) - 60000; t.z[k] = rnd(120000) - 60000;
    t.radx[k] = 50 + rnd(k % 10 ? 800 : 12000); t.radz[k] = 50 + rnd(k % 7 ? 800 : 12000);   // a few long pieces
  }
  buildTrackGrid(t);
  const hits = (list, x, z, m) => list.filter((k) => Math.abs(x - t.x[k]) < t.radx[k] + m && Math.abs(z - t.z[k]) < t.radz[k] + m);
  const all = Array.from({ length: nt }, (_, k) => k);
  for (let i = 0; i < 4000; i++) {
    const x = rnd(160000) - 80000, z = rnd(160000) - 80000, m = rnd(1501);
    const near = nearTrackers(t, x, z, m);
    assert.deepStrictEqual(hits([...near], x, z, m), hits(all, x, z, m));
  }
  assert.strictEqual(nearTrackers(t, 0, 0, 1501), null);   // past PAD: the caller sweeps
  t.nt++;
  assert.strictEqual(nearTrackers(t, 0, 0, 0), null);      // trackers added since the build
});

test('WheelSweep: same hits, same order, same pushes as the full sweep', () => {
  let seed = 11;
  const rnd = (n) => { seed = (seed * 1103515245 + 12345) & 0x7fffffff; return seed % n; };
  const nt = 2000;
  const t = { nt, x: new Int32Array(nt), z: new Int32Array(nt), radx: new Int32Array(nt), radz: new Int32Array(nt) };
  for (let k = 0; k < nt; k++) {
    t.x[k] = rnd(40000) - 20000; t.z[k] = rnd(40000) - 20000;
    t.radx[k] = 50 + rnd(k % 9 ? 900 : 9000); t.radz[k] = 50 + rnd(k % 5 ? 900 : 9000);
  }
  buildTrackGrid(t);
  // a stand-in for drive's body: a wheel inside a piece is pushed to its +x or +z face,
  // which for a long piece is far outside the wheels' first box
  const run = (xs, zs, order) => {
    const hits = [];
    for (let k = order.next(); k >= 0; k = order.next()) {
      for (let w = 0; w < 4; w++) {
        if (xs[w] > t.x[k] - t.radx[k] && xs[w] < t.x[k] + t.radx[k] && zs[w] > t.z[k] - t.radz[k] && zs[w] < t.z[k] + t.radz[k]) {
          hits.push(k * 4 + w);
          if (k % 2) xs[w] = t.x[k] + t.radx[k]; else zs[w] = t.z[k] + t.radz[k];
        }
      }
    }
    return hits;
  };
  const full = (count) => { let k = 0; return { next: () => (k < count ? k++ : -1) }; };
  const sweep = new WheelSweep();
  for (let i = 0; i < 3000; i++) {
    const cx = rnd(44000) - 22000, cz = rnd(44000) - 22000;
    const xs = Float32Array.from({ length: 4 }, () => cx + rnd(400) - 200), zs = Float32Array.from({ length: 4 }, () => cz + rnd(400) - 200);
    const xs2 = xs.slice(), zs2 = zs.slice();
    assert.deepStrictEqual(run(xs2, zs2, sweep.begin(t, xs2, zs2, nt)), run(xs, zs, full(nt)));
    assert.deepStrictEqual([...xs2, ...zs2], [...xs, ...zs]);
  }
  assert.strictEqual(sweep.begin(t, [0, 0, 0, 0], [0, 0, 0, 0], 0).next(), -1);   // coldetection 0
});
