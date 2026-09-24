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

test('stage lines keep their object, whatever each game numbers it', () => {
  const grat = (i) => (i === extIndex('aircheckpoint') ? 7 : 0);
  const out = translateStage([
    'set(10,0,5600,180)p',      // road: the same id in both
    'set(35,0,0,0)',            // sofframp: 39 in Extended (its set() also reads 35 as sofframp; chk/fix do not)
    'set(39,100,200,90)',       // thewall: base 39, Extended's own would need 35
    'set(53,0,0,0)',            // offhill: in Extended under another id
    'set(73,5,6,0)',            // cac1: base only, appended
    'chk(40,0,28000,0)',        // checkpoint
    'chk(64,1,2,90,-600)',      // aircheckpoint: y raw in the base, chkfloat subtracts grat
    'fix(41,0,0,-500,0)',       // fixpoint
    'sky(207,232,255)',
  ].join('\n'), grat).split('\n');
  const id = (name) => extIndex(name) - 29;
  assert.deepStrictEqual(out, [
    'set(10,0,5600,180)p',
    'set(39,0,0,0)',
    `set(${id('thewall')},100,200,90)`,
    `set(${id('offhill')},0,0,0)`,
    `set(${id('cac1')},5,6,0)`,
    'chk(40,0,28000,0)',
    `chkfloat(${id('aircheckpoint')},1,2,90,-593)`,
    'fix(41,0,0,-500,0)',
    'sky(207,232,255)',
  ]);
  // no appended object may land on set()'s special id 35
  for (const n of APPENDED) assert.notStrictEqual(extIndex(n) - 29, 35, n);
});
