// calibrate(): the applet's Save & Finish crash calibration (physics() value 16).
// Without it CarDefine.loadstat blanks the car, and ?mycar= races Formula 7.

import test from 'node:test';
import assert from 'node:assert/strict';
import { readFileSync } from 'node:fs';
import { calibrate } from './damage.js';
import { newCarMaker } from './state.js';
import * as rad from './rad.js';
import { CarDefine } from '../CarDefine.js';
import { Medium } from '../Medium.js';
import { Trackers } from '../Trackers.js';

const SIMPLE = readFileSync(new URL('../../mycars/Simple Car.rad', import.meta.url), 'utf8');

test('calibrate: an uncalibrated car gets an actmag and then loads as a custom car', () => {
  const p = rad.readPhysics(SIMPLE);
  const text = rad.writePhysics(SIMPLE, { ...p, actmag: 0 });
  const load = (t) => {
    const cd = new CarDefine(new Array(56).fill(null), new Medium(), new Trackers(), null);
    return cd.loadcar('x', 16, t);
  };
  assert.equal(load(text), -1);   // the bug: loadstat blanks it

  const cm = newCarMaker();
  cm.editor = { getText: () => text, setText() {} };
  cm.stat = rad.readStats(text).slice();
  cm.crash = p.crash.slice();
  calibrate(cm);
  assert.ok(cm.actmag > 0, `actmag ${cm.actmag}`);
  assert.equal(load(rad.writePhysics(text, { ...p, crash: cm.crash.slice(), actmag: cm.actmag })), 16);
});
