// The special descriptions the Car Maker's Extended tab shows (specials.js) are the car
// select's own: this evaluates the carselect block that draws them, for each of the 39,
// at specialboost 1 (Free Play), so a regeneration of xtGraphics.js cannot leave it stale.
import { test } from 'node:test';
import assert from 'node:assert';
import fs from 'node:fs';
import { SPECIALS } from './specials.js';

function drawn() {
  const src = fs.readFileSync(new URL('xtGraphics.js', import.meta.url), 'utf8');
  const from = src.indexOf("if ((id(this.sc[0]) === 0) || (id(this.sc[0]) === 23)) {\n          this.rd.drawString",
    src.indexOf('carselect(control'));
  const to = src.indexOf('this.rd.setColor(181, 120, 40);', from);
  assert.ok(from > 0 && to > from, 'the special block moved: update the markers');
  const block = new Function('id', 'trunc', 'specialboost', src.slice(from, to));
  const trunc = (v) => (v < 0 ? Math.ceil(v) : Math.floor(v));
  return Array.from({ length: 39 }, (_, k) => {
    const lines = [];
    block.call({ sc: [k], rd: { drawString: (s) => lines.push(s) } }, (c) => c, trunc, 1.0);
    return lines;
  });
}

test('the catalog is what the car select draws', () => {
  assert.deepStrictEqual(SPECIALS, drawn());
});

test('every description line is translated to Spanish', async () => {
  globalThis.localStorage = { getItem: () => JSON.stringify({ lang: 'es' }) };
  const es = await import('../i18n.js?lang=es-specials');
  delete globalThis.localStorage;
  const missing = SPECIALS.flat().filter((s) => es.tr(s) === s);
  assert.deepStrictEqual(missing, []);
});
