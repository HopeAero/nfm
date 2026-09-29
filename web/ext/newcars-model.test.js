// A new car's model (newcars-model.js): Extended's ContO at its NEW_BASE code, with the
// settings the base CarDefine.loadcar gives every Car Maker car (a shadow above all).

import { test } from 'node:test';
import assert from 'node:assert';
import fs from 'node:fs';
import { Medium } from './Medium.js';
import { Trackers } from './Trackers.js';
import { NEW_BASE } from './newcars.js';
import { carFromRad } from './newcars-stats.js';
import { newCarModel } from './newcars-model.js';

const simple = fs.readFileSync(new URL('../../mycars/Simple Car.rad', import.meta.url), 'latin1');

test('a new car model casts a shadow and is set up as the base sets Car Maker cars', () => {
  const o = newCarModel(carFromRad('Simple Car', simple), new Medium(), new Trackers(), null, NEW_BASE);
  assert.strictEqual(o.isacar, true);
  assert.strictEqual(o.shadow, true);
  assert.strictEqual(o.noline, false);
  assert.strictEqual(o.disline, 7);
  assert.strictEqual(o.grounded, 1.0);
  assert.strictEqual(o.maxR, 130);        // the base ContO's size for this car (ScaleX/Y/Z 145)
  assert.ok(o.npl > 60);
});

test('lightBrake parses as light 3 in Extended too (ext-patch lightbrake-parse)', async () => {
  const { ContO } = await import('./ContO.js');
  const poly = (tag) => `<p>\nc(200,60,60)\n${tag}\np(0,0,0)\np(10,0,0)\np(0,10,0)\n</p>\n`;
  const text = poly('lightF') + poly('lightB') + poly('lightBrake');
  const o = new ContO(0, Int8Array.from(text, (ch) => ch.charCodeAt(0)), new Medium(), new Trackers(), null, NEW_BASE);
  assert.deepEqual([o.p[0].light, o.p[1].light, o.p[2].light], [1, 2, 3]);
});
