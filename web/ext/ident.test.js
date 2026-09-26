// The identity half of Extended's new cars (web/tools/ext-ident.mjs): every car-number
// comparison against a literal asks id(car). A regeneration that forgot
// `node web/tools/ext-ident.mjs` would leave new cars without their donor's special.

import { test } from 'node:test';
import assert from 'node:assert';
import fs from 'node:fs';
import { FILES, rawIn, rewrite } from '../tools/ext-ident.mjs';

test('no car-number comparison against a literal is left unwrapped', () => {
  for (const f of FILES) {
    const src = fs.readFileSync(new URL(f, import.meta.url), 'utf8');
    assert.strictEqual(rawIn(f, src), 0, `${f}: run node web/tools/ext-ident.mjs`);
    assert.match(src, /import \{ id \} from '\.\/newcars\.js';/, f);
  }
});

test('the rewrite wraps the car side only, leaves car-vs-car alone, and is idempotent', () => {
  const src = "if ((this.cn === 36) || (madness.cn >= 23) || (amadness[k8].cn === 12) || (this.sc[k] === this.sc[l]) || (this.sc[0] <= 38)) {}";
  const once = rewrite(src);
  assert.strictEqual(once, "if ((id(this.cn) === 36) || (id(madness.cn) >= 23) || (id(amadness[k8].cn) === 12) || (this.sc[k] === this.sc[l]) || (id(this.sc[0]) <= 38)) {}");
  assert.strictEqual(rewrite(once), once);
});

test('healthcalc, handed a race car number as carid, asks id(carid) too', () => {
  const src = fs.readFileSync(new URL('xtGraphics.js', import.meta.url), 'utf8');
  const body = src.slice(src.indexOf('  healthcalc(initialhealth, statpoints, carid, modifier) {'));
  const line = body.slice(0, body.indexOf('\n  }\n'));
  assert.doesNotMatch(line, /(?<!id\()\bcarid\s*(===|!==)\s*\d/);
  assert.match(line, /id\(carid\) === 36/);
});
