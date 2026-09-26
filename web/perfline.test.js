import test from 'node:test';
import assert from 'node:assert';
import { perfLevel, perfLine } from './perfline.js';

test('Show performance: the line for each level', () => {
  const m = { fps: 59.6, tps: 18.92, tickMs: 1.24, frameMs: 5.96 };
  assert.strictEqual(perfLine('off', m), '');
  assert.strictEqual(perfLine('fps', m), '60 fps');
  assert.strictEqual(perfLine('ms', m), '60 fps  18.9 tick/s  sim 1.2 ms/tick  draw 6.0 ms/frame');
  assert.strictEqual(perfLine('all', m), null);          // the caller's full line
  assert.strictEqual(perfLevel(new URLSearchParams('perf=ms')), 'ms');
  assert.strictEqual(perfLevel(new URLSearchParams('stats=1')), null);   // opened by URL: as always
});
