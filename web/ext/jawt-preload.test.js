import test from 'node:test';
import assert from 'node:assert/strict';
import { preload, URL } from './jawt.js';

test('concurrent preloads of one archive share the same work', async () => {
  let reads = 0, finish;
  const path = 'test/shared-preload.bin';
  const read = () => { reads++; return new Promise((resolve) => { finish = resolve; }); };
  const first = preload([path], read);
  const second = preload([path], read);
  assert.equal(reads, 1);
  finish(new Uint8Array([1, 2, 3]));
  await Promise.all([first, second]);
  assert.deepEqual([...new URL('', path).bytes()], [1, 2, 3]);
});
