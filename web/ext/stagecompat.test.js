// NFM2 stage lines translated for Extended (stagecompat.js): ids by object name.

import { test } from 'node:test';
import assert from 'node:assert';
import fs from 'node:fs';
import { APPENDED, EXT_MODELS, extIndex, translateStage } from './stagecompat.js';

test('EXT_MODELS is the jar loadbase list', () => {
  const java = fs.readFileSync(new URL('../../decompilation/extended/java-src/GameSparker.java', import.meta.url), 'utf8');
  const as = [...java.match(/String\[\] as = \{([^}]*)\}/)[1].matchAll(/"([^"]*)"/g)].map((m) => m[1]);
  assert.deepStrictEqual(EXT_MODELS, as);
});

test('stage lines point at the base model of the same object', () => {
  const grat = (i) => (i === extIndex('aircheckpoint') ? 7 : 0);
  const out = translateStage([
    'set(10,0,5600,180)p',      // road
    'set(35,0,0,0)',            // sofframp: Extended's set() would read 35 as its own
    'set(39,100,200,90)',       // thewall
    'set(68,0,0,0)',            // tree4: Extended has a reshaped one
    'set(73,5,6,0)',            // cac1: Extended has none
    'chk(40,0,28000,0)',        // checkpoint: Extended's looks different
    'chk(64,1,2,90,-600)',      // aircheckpoint: y raw in the base, chkfloat subtracts grat
    'fix(41,0,0,-500,0)',       // fixpoint
    'sky(207,232,255)',
  ].join('\n'), grat).split('\n');
  const id = (name) => extIndex(name) - 29;
  assert.deepStrictEqual(out, [
    `set(${id('road')},0,5600,180)p`,
    `set(${id('sofframp')},0,0,0)`,
    `set(${id('thewall')},100,200,90)`,
    `set(${id('tree4')},0,0,0)`,
    `set(${id('cac1')},5,6,0)`,
    `chk(${id('checkpoint')},0,28000,0)`,
    `chkfloat(${id('aircheckpoint')},1,2,90,-593)`,
    `fix(${id('fixpoint')},0,0,-500,0)`,
    'sky(207,232,255)',
  ]);
  assert.strictEqual(id('road'), 100);   // appended after Extended's 129 models
  // no base piece may land on set()'s special id 35, nor on an Extended model
  for (const n of APPENDED) assert.ok(extIndex(n) >= EXT_MODELS.length && extIndex(n) - 29 !== 35, n);
});
