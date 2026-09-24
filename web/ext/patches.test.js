// The named patches over J2JS output (web/tools/ext-patches.mjs) must be in
// place: a regeneration that forgot `node web/tools/ext-patches.mjs` would put
// the O(n^2) face order and the per-vertex trig back, silently.

import { test } from 'node:test';
import assert from 'node:assert';
import fs from 'node:fs';
import { PATCHES, state } from '../tools/ext-patches.mjs';

test('every ext-patch is applied to the generated classes', () => {
  for (const p of PATCHES) {
    const src = fs.readFileSync(new URL(p.file, import.meta.url), 'utf8');
    assert.strictEqual(state(src, p), 'applied', `${p.name} in ${p.file}: run node web/tools/ext-patches.mjs`);
  }
});
