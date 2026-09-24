// images.js contrast rule for the dark-sky HUD (WCAG 2 ratio, 4.5:1).
import test from 'node:test';
import assert from 'node:assert';
import { contrast, readable } from './images.js';

test('WCAG contrast: black on white is 21:1, a colour on itself 1:1', () => {
  assert.ok(Math.abs(contrast([0, 0, 0], [255, 255, 255]) - 21) < 1e-9);
  assert.strictEqual(contrast([64, 32, 96], [64, 32, 96]), 1);
});

test('readable() lifts the HUD ink off a dark sky and leaves a readable one alone', () => {
  const sky = [64, 32, 96];
  const ink = readable([0, 0, 100], sky);
  assert.ok(contrast(ink, sky) >= 4.5);
  assert.deepStrictEqual(readable([0, 0, 0], [255, 255, 255]), [0, 0, 0]);
});
