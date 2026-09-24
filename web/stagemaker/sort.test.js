// sortStage() against the real StageMaker.sortstage(): the expected output
// for every stock stage's parts was produced by web/tools/SortStageProbe.java
// (seed 7) and is pinned by its SHA-256. A mismatch means the order the
// pieces are written in, a route-point flag or a boundary wall moved -- and
// the order is what the bots drive.
import test from 'node:test';
import assert from 'node:assert';
import { readFileSync } from 'node:fs';
import { createHash } from 'node:crypto';
import { parseZip } from '../vfs.js';
import { Medium } from '../Medium.js';
import { Trackers } from '../Trackers.js';
import { GameSparker } from '../GameSparker.js';
import { objArray, setSeed } from '../java.js';
import { readParts, sortStage, pyn, PILE } from './sort.js';

const R = new URL('../../', import.meta.url);
const readRepo = (p, enc) => readFileSync(new URL(p, R), enc);

test('pyn is the Java squared distance in hundreds, integer division included', () => {
  assert.strictEqual(pyn(250, 0, -250, 0), 2 * 2 + 2 * 2);   // 2.5 -> 2, -2.5 -> -2
  assert.strictEqual(pyn(5600, 0, 0, 0), 56 * 56);
});

test('readParts keeps the head, drops the walls, and numbers forced checkpoints', () => {
  const { head, parts } = readParts('name(X)\r\nsky(1,2,3)\r\nset(47,0,0,0)p\r\nchk(40,0,5600,0)r\r\nchk(40,0,9000,0)\r\nfix(41,0,12800,-2000,0)\r\nmaxl(3,1,2)\r\n');
  assert.deepStrictEqual(head, ['name(X)', 'sky(1,2,3)']);
  assert.deepStrictEqual(parts.map((p) => p.sp), [37, 30, 30, 31]);
  assert.strictEqual(parts[1].wh, 1);
  assert.strictEqual(parts[2].wh, 0);
  assert.strictEqual(parts[3].y, -2000);
});

test('sortStage reproduces StageMaker.sortstage byte for byte on all 32 stock stages', async () => {
  const expected = JSON.parse(readRepo('web/stagemaker/sortstage.expected.json', 'utf8'));
  const zip = await parseZip(new Uint8Array(readRepo('data/models.zip')));
  const m = new Medium();
  const models = objArray(124);
  new GameSparker().loadbase(models, m, new Trackers(), zip);
  const maxR = (sp) => (sp === PILE ? 0 : models[sp + 56]?.maxR ?? 0);
  const wrong = [];
  for (let n = 1; n <= 32; n++) {
    const { parts } = readParts(readRepo(`stages/${n}.txt`, 'latin1'));
    setSeed(expected.seed);
    const out = sortStage(parts, maxR, m);
    const hash = createHash('sha256').update(out, 'latin1').digest('hex');
    if (hash !== expected.stages[n]) wrong.push(n);
  }
  assert.deepStrictEqual(wrong, []);
});
