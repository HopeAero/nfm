// Extended free play: the launcher's lists against the jar's, and the pick logic.

import { test } from 'node:test';
import assert from 'node:assert';
import fs from 'node:fs';
import { CAREER_STAGES, EXT_CARS, EXT_STAGES, classicTwin } from './catalog.js';
import { bonusAt, careerStep, clampCarArrows, groups, jarStage, loadPick, stagesOf, step } from './menus.js';
import { parseRadq } from './radq.js';

test('EXT_CARS is xtGraphics.names', () => {
  const java = fs.readFileSync(new URL('../../decompilation/extended/java-src/xtGraphics.java', import.meta.url), 'utf8');
  const names = [...java.match(/this\.names = new String\[\] \{([^}]*)\}/)[1].matchAll(/"([^"]*)"/g)].map((m) => m[1]);
  assert.deepStrictEqual(EXT_CARS, names);
});

test('EXT_STAGES is tracks.radq, but for the Premier Tournament', async () => {
  const zip = await parseRadq(new Uint8Array(fs.readFileSync(new URL('../../ext/data/Files/tracks.radq', import.meta.url))));
  const names = [...zip.keys()].map((k) => [parseInt(k, 10), /name\(([^)]*)\)/.exec(new TextDecoder('latin1').decode(zip.get(k)))[1]])
    .sort((a, b) => a[0] - b[0]);
  assert.deepStrictEqual(EXT_STAGES, names);
  assert.ok(!EXT_STAGES.some(([n]) => n === 26));
});

test('CAREER_STAGES is careertracks.radq, but for the bonus stages', async () => {
  const zip = await parseRadq(new Uint8Array(fs.readFileSync(new URL('../../ext/data/Files/careertracks.radq', import.meta.url))));
  const names = [...zip.keys()].filter((k) => /^\d+\.txt$/.test(k))
    .map((k) => [parseInt(k, 10), /name\(([^)]*)\)/.exec(new TextDecoder('latin1').decode(zip.get(k)))[1]])
    .sort((a, b) => a[0] - b[0]);
  assert.deepStrictEqual(CAREER_STAGES, names);
});

test('the career group is for developer mode only, and races in career mode', () => {
  assert.deepStrictEqual(groups(false), ['nfm2', 'ext']);
  assert.deepStrictEqual(groups(true), ['nfm2', 'ext', 'career']);
  assert.deepStrictEqual(jarStage('career', 5), { classic: false, career: true, stage: 5 });
  assert.strictEqual(loadPick(groups(false)).group !== 'career', true);
});

test('NFM 2 stages 11-27 race as the jar classic mode 1-17; the rest as its stage 1', () => {
  assert.deepStrictEqual([1, 10, 11, 20, 27, 28, 32].map(classicTwin), [1, 1, 1, 10, 17, 1, 1]);
  assert.deepStrictEqual(jarStage('nfm2', 14), { classic: true, career: false, stage: 4 });
  assert.deepStrictEqual(jarStage('ext', 27), { classic: false, career: false, stage: 27 });
});

test('stage arrows stop at the list ends and skip the missing 26', () => {
  const ext = stagesOf('ext', []);
  assert.strictEqual(step(ext, 25, +1), 27);
  assert.strictEqual(step(ext, 27, -1), 25);
  assert.strictEqual(step(ext, 1, -1), 1);
  assert.strictEqual(step(ext, 28, +1), 28);
  const nfm2 = stagesOf('nfm2', Array.from({ length: 32 }, (_, i) => `s${i + 1}`));
  assert.deepStrictEqual(nfm2[31], [32, 's32']);
  assert.strictEqual(step(nfm2, 32, +1), 32);
});

test('car arrows stop at 0 and 38', () => {
  const c = (car) => { const k = { left: true, right: true }; clampCarArrows(k, car); return [k.left, k.right]; };
  assert.deepStrictEqual(c(0), [false, true]);
  assert.deepStrictEqual(c(22), [true, true]);
  assert.deepStrictEqual(c(38), [true, false]);
});

test('career arrows: right only up to the unlocked stage and 31, left down to 1', () => {
  assert.deepStrictEqual(careerStep(3, 5, +1), { stage: 4 });
  assert.deepStrictEqual(careerStep(5, 5, +1), { stage: 5, locked: true });
  assert.deepStrictEqual(careerStep(31, 40, +1), { stage: 31, locked: true });
  assert.deepStrictEqual(careerStep(1, 5, -1), { stage: 1 });
  assert.deepStrictEqual(careerStep(4, 5, -1), { stage: 3 });
  assert.deepStrictEqual([5, 11, 15, 18, 6].map(bonusAt), [0, 1, 2, 3, -1]);
});
