import test from 'node:test';
import assert from 'node:assert';
import { Bench, frameCap } from './benchtools.js';

test('frameCap(30) on a 60 Hz rAF keeps every other frame', () => {
  const skip = frameCap(30);
  const kept = [];
  for (let k = 0; k < 60; k++) if (!skip(k * 1000 / 60)) kept.push(k);
  assert.strictEqual(kept.length, 30);
  assert.strictEqual(frameCap(0), null);
});

test('Bench: warmup from the first race frame, then a fixed window, then frozen', () => {
  const rd = { vertexCount: 10, inputVerts: 100, objCalls: 5, objDrawn: 4, faceCalls: 50, projVerts: 200 };
  let resets = 0;
  const b = new Bench(1, 500, { reset: () => resets++, total: { backdrop: 0, plane: 0, planeN: 0, shadow: 0, shadowN: 0, drive: 0, colide: 0 } });
  let closedAt = -1;
  for (let k = 0; k < 200 && closedAt < 0; k++) {
    const now = 10000 + k * 10;   // the race starts late: warmup counts from here
    if (b.frame(now, { sim: 2, draw: 3, ticks: k % 5 === 0 ? 1 : 0, rendered: true, rd })) closedAt = now;
  }
  assert.strictEqual(closedAt, 10000 + 500 + 1000);
  assert.strictEqual(resets, 1);
  assert.strictEqual(b.frames, 100);
  assert.match(b.report('cfg'), /100\.0 fps avg[\s\S]*draw 3\.00 ms\/frame[\s\S]*30000 ns\/vert[\s\S]*per tick: drive[\s\S]*cfg$/);
  assert.strictEqual(b.frame(99999, { sim: 1, draw: 1, ticks: 1, rendered: true, rd }), false);   // frozen
  b.restart();                                  // R: warm already, measures from the next frame
  b.frame(20000, { sim: 1, draw: 1, ticks: 1, rendered: true, rd });
  assert.strictEqual(b.start, 20000);
});
