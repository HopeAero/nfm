// The Car Maker cars button on Free Play's car select is NFM 2's own (cargroup.js):
// xtGraphics.drawcarb's frame around an image label, measured from the Java.
import { test } from 'node:test';
import assert from 'node:assert';
import fs from 'node:fs';
import { carbLayout } from './cargroup.js';

test("drawcarb's geometry: 4px caps, the middle stretched to the label + 6, the label at 7,7", () => {
  assert.deepStrictEqual(carbLayout(126), { width: 140, height: 28, labelX: 7, labelY: 7 });   // cmc.gif
  assert.deepStrictEqual(carbLayout(73), { width: 87, height: 28, labelX: 7, labelY: 6 });     // the Java's one-pixel nudge
});

test('the geometry is still the Java drawcarb', () => {
  const java = fs.readFileSync(new URL('../../decompilation/java-src/xtGraphics.java', import.meta.url), 'utf8');
  assert.match(java, /this\.rd\.drawImage\(this\.bc\[n6\], n \+ 4, n2, n5 \+ 6, 28, null\);/);
  assert.match(java, /this\.rd\.drawImage\(this\.bcr\[n6\], n \+ n5 \+ 10, n2, null\);/);
  assert.match(java, /if \(!b && n5 == 73\) \{\s*--n2;/);
  assert.match(java, /this\.rd\.drawImage\(image, n \+ 7, n2 \+ 7, null\);/);
});
