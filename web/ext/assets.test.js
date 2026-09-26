import test from 'node:test';
import assert from 'node:assert/strict';
import { botArchiveFor, CORE_ARCHIVES } from './assets.js';

test('Extended only needs a bot archive for a matching career stage', () => {
  assert.equal(CORE_ARCHIVES.length, 6);
  assert.equal(CORE_ARCHIVES.some((p) => p.includes('/Bots/')), false);
  assert.equal(botArchiveFor(false, 5), null);
  assert.equal(botArchiveFor(true, 2), null);
  assert.equal(botArchiveFor(true, 5), 'data/Files/Bots/stage5.radq');
  assert.equal(botArchiveFor(true, 21), 'data/Files/Bots/stage21.radq');
});
