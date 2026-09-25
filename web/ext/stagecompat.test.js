// NFM2 stage lines translated for Extended (stagecompat.js): ids by object name.

import { test } from 'node:test';
import assert from 'node:assert';
import fs from 'node:fs';
import { APPENDED, EXT_MODELS, extIndex, renumberOldStage, translateStage } from './stagecompat.js';
import { parseRadq } from './radq.js';

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

test('a base texture() becomes the polys() Extended reads, coloured as the base mixes it', () => {
  const out = (text) => translateStage(text, () => 0).split('\n');
  // stage 13: ground(192,204,195) texture(0,133,255,37) -> (ground*37 + texture) / 38
  assert.deepStrictEqual(out('ground(192,204,195)\ntexture(0,133,255,37)\nfadefrom(5000)'),
    ['ground(192,204,195)', 'polys(186,202,196)', 'fadefrom(5000)']);
  // the base clamps the mix to 20..60, and texture may come before ground
  assert.deepStrictEqual(out('texture(255,0,0,99)\nground(0,0,0)'), ['polys(4,0,0)', 'ground(0,0,0)']);
  // no texture: the base's default (0,0,0,50); without it Extended's polys are its whitish 215,210,210
  assert.deepStrictEqual(out('ground(204,200,190)\nsky(1,2,3)'), ['ground(204,200,190)', 'polys(200,196,186)', 'sky(1,2,3)']);
});

test('translateStage reads density as the base does: 2n+1, 1..30', () => {
  const out = (text) => translateStage(text, () => 0);
  assert.strictEqual(out('density(4)'), 'density(9)');
  assert.strictEqual(out('density(-3)'), 'density(1)');
  assert.strictEqual(out('density(20)'), 'density(30)');
});

test("tracks.radq's stages on the old model list are renumbered, the rest left alone", async () => {
  const zip = await parseRadq(new Uint8Array(fs.readFileSync(new URL('../../ext/data/Files/tracks.radq', import.meta.url))));
  const ids = (t) => [...t.matchAll(/^(set\w*|chk\w*|fix|teleset)\((\d+),/gm)].map((m) => [m[1], +m[2]]);
  let old = 0;
  for (const [name, bytes] of zip) {
    const text = new TextDecoder('latin1').decode(bytes);
    const out = renumberOldStage(text);
    if (/^chk\w*\(44,/m.test(text)) old++;
    else assert.strictEqual(out, text, name);
    for (const [cmd, id] of ids(out)) {
      assert.ok(id >= 10 && id <= 48, `${name}: ${cmd}(${id})`);
      if (cmd === 'chk') assert.ok([36, 40, 42].includes(id), `${name}: chk(${id})`);
      if (cmd === 'fix') assert.strictEqual(id, 41, name);
    }
  }
  assert.strictEqual(old, 24);
  assert.strictEqual(renumberOldStage('set(51,0,0,0)p\nchk(44,0,1,0)\nfix(45,1,2,3,0)'), 'set(47,0,0,0)p\nchk(40,0,1,0)\nfix(41,1,2,3,0)');
});
