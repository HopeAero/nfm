// Extended's car tiers (tiers.js) for the Rivals screen.

import { test } from 'node:test';
import assert from 'node:assert';
import { EXT_TIER } from './tiers.js';
import { EXT_CARS } from './catalog.js';

test('one tier per Extended car, all in C/B/A/S', () => {
  assert.strictEqual(EXT_TIER.length, EXT_CARS.length);
  for (const t of EXT_TIER) assert.ok(['C', 'B', 'A', 'S'].includes(t), t);
});
