// The Car Maker's Extended tab: its pure pieces (extended.js).
import { test } from 'node:test';
import assert from 'node:assert';
import { tryPick, specialLabel, specialText, perText, whyNotExtended } from './extended.js';
import { NEW_BASE } from '../ext/newcars.js';

test('tryPick survives garbage and keeps group and stage', () => {
  assert.deepStrictEqual(tryPick(null, 'My Car'), { car: NEW_BASE, carName: 'My Car' });
  assert.deepStrictEqual(tryPick('not json', 'My Car'), { car: NEW_BASE, carName: 'My Car' });
  assert.deepStrictEqual(tryPick('[1,2]', 'My Car'), { car: NEW_BASE, carName: 'My Car' });
  assert.deepStrictEqual(tryPick('{"car":7,"carName":"x","group":"nfm2","stage":12}', 'My Car'),
    { car: NEW_BASE, carName: 'My Car', group: 'nfm2', stage: 12 });
});

test('special labels name the car and its first line; Agent Racer has none', () => {
  assert.strictEqual(specialLabel(13), 'Stampede — 40% strength/defence boost.');
  assert.strictEqual(specialLabel(21), 'Agent Racer — No description in the game.');
  assert.strictEqual(specialText(13), "40% strength/defence boost.\nReduces a random car's defence.");
});

test('perText computes once per distinct text (the tab reads it from every row)', () => {
  let calls = 0;
  const f = perText((t) => { calls++; return t.length; });
  assert.strictEqual(f('abc'), 3);
  f('abc'); f('abc');
  assert.strictEqual(calls, 1);
  assert.strictEqual(f('abcd'), 4);
  assert.strictEqual(calls, 2);
});

test("Try in Extended says why a car Extended cannot load, instead of racing car 38", async () => {
  const fs = await import('node:fs');
  const simple = fs.readFileSync(new URL('../../mycars/Simple Car.rad', import.meta.url), 'latin1');
  assert.strictEqual(whyNotExtended('Simple Car', simple), '');
  const uncalibrated = simple.replace(/^physics\((.*),\d+\)$/m, 'physics($1,0)');
  assert.notStrictEqual(uncalibrated, simple);
  assert.match(whyNotExtended('Simple Car', uncalibrated), /calibrat/i);
  assert.match(whyNotExtended('junk', 'nothing\n'), /race/i);
});
