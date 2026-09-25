// The career save (career-save.js) round-trips every field the jar's savedata.radq holds.

import { test } from 'node:test';
import assert from 'node:assert';
import { loadCareer, restoreStats, saveCareer, snapshot, statsOf } from './career-save.js';
import { clipName } from './sound.js';

const fresh = () => {
  const n = (v = 0) => new Array(39).fill(v);
  const xt = {
    unlocked: [1, 1], realunlocked: [1, 1], kills: 0, wins: 0, statchangers: [0, 0], lastcar: 0, sc: n(), laststage: 1,
    boncomp: [0, 0, 0, 0, 0, 0], carpoints: 0, statpoints: n(), killscn: n(), winscn: n(), extpoints: n(),
    specialstats: n().map(() => [0, 1, 2, 3, 4, 5, 6].map(() => [0, 0, 0, 0, 0, 0])),
    statsalc: n().map((_, a) => [0, 1, 2, 3, 4, 5].map((b) => (a + b) % 7)), rebsp: n(1), xbsp: n(0),
  };
  const m = { level: n(1), exp: n(), aitssp: n(), aiaccsp: n(), aigripsp: n(), aistusp: n(), aistrsp: n(), aiendsp: n() };
  return { xt, cp: { stage: 1 }, m };
};

test('a career saves and loads back field for field', () => {
  const a = fresh();
  Object.assign(a.xt, { kills: 7, wins: 3, lastcar: 12, laststage: 9, carpoints: 4 });
  a.xt.unlocked[1] = 9; a.xt.statchangers[1] = 1; a.xt.boncomp[4] = 1;
  a.m.level[12] = 14; a.m.exp[12] = 1234; a.m.aistrsp[12] = 5; a.xt.statpoints[12] = 6; a.xt.extpoints[3] = 2;
  a.xt.specialstats[12][a.xt.statsalc[12][2]][2] = 9; a.xt.rebsp[12] = 1.5; a.xt.xbsp[12] = 0.25;
  const store = new Map();
  const storage = { setItem: (k, v) => store.set(k, v), getItem: (k) => store.get(k) ?? null };
  saveCareer(a.xt, a.cp, a.m, storage);
  const b = fresh();
  assert.strictEqual(loadCareer(b.xt, b.cp, b.m, storage), true);
  assert.deepStrictEqual(snapshot(b.xt, b.cp, b.m), snapshot(a.xt, a.cp, a.m));
  assert.strictEqual(b.cp.stage, 9);
  assert.strictEqual(b.xt.sc[0], 12);
  assert.strictEqual(b.xt.realunlocked[1], 9);
  assert.strictEqual(loadCareer(fresh().xt, {}, fresh().m, { getItem: () => null }), false);
});

test('Undo puts every stat the car select spends back as it was', () => {
  const a = fresh();
  a.xt.statpoints[5] = 3; a.xt.carpoints = 2;
  const saved = statsOf(a.xt, a.m);
  a.xt.statpoints[5]--; a.m.aigripsp[5]++;                  // a + on control
  a.xt.carpoints--; a.xt.specialstats[5][a.xt.statsalc[5][1]][1]++;   // a bonus-car point
  assert.notStrictEqual(statsOf(a.xt, a.m), saved);
  restoreStats(a.xt, a.m, saved);
  assert.strictEqual(statsOf(a.xt, a.m), saved);
  assert.deepStrictEqual([a.xt.statpoints[5], a.m.aigripsp[5], a.xt.carpoints], [3, 0, 2]);
});

test("Extended's clip paths name the base port's clips", () => {
  assert.strictEqual(clipName('data/Files/sounds/JavaNew/crash1.wav'), 'crash1');
  assert.strictEqual(clipName('data/Files/sounds/JavaNew/42.wav'), '42');
  assert.strictEqual(clipName('data/Files/sounds/caught.wav'), 'caught');
});

test("jawt's Color(int rgb) unpacks the pixel, as java.awt.Color does", async () => {
  const { Color } = await import('./jawt.js');
  const c = new Color(0xff336699 | 0);
  assert.deepStrictEqual([c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()], [0x33, 0x66, 0x99, 255]);
  assert.strictEqual(new Color(0x80336699 | 0, true).getAlpha(), 0x80);
  assert.deepStrictEqual([new Color(1, 2, 3).getRed(), new Color(1, 2, 3).getBlue()], [1, 3]);
});

test("the presenter's download line", async () => {
  const { progressText } = await import('./musicload.js');
  assert.strictEqual(progressText({ loaded: 145408, total: 1739776, done: false }), '142 / 1699 KB');
  assert.strictEqual(progressText({ loaded: 1739776, total: 1739776, done: true }), '1699 KB');
  assert.strictEqual(progressText({ loaded: 51200, total: 0, done: false }), '50 KB');
});
