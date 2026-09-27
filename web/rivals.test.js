// The Rivals screen's logic (rivals.js): the grid it draws, what it accepts from storage/URL.

import { test } from 'node:test';
import assert from 'node:assert';
import fs from 'node:fs';
import { NFM2_CCLASS, defaultRivals, parseRivals, pickRivals, tierOfClass, toggleTier } from './rivals.js';

// a deterministic stand-in for java.js random(): cycles through the given values
const seq = (...v) => { let k = 0; return () => v[k++ % v.length]; };

test('game mode keeps sortcars, player slot untouched', () => {
  const sc = pickRivals({ mode: 'game', pool: [3], fixed: [] }, [9, 1, 2, 3, 4, 5, 6, 9], 7, seq(0));
  assert.deepStrictEqual(sc, [9, 1, 2, 3, 4, 5, 6, 9]);
});

test('a one-car pool races that car everywhere', () => {
  const sc = pickRivals({ mode: 'pool', pool: [13], fixed: [] }, [4, 1, 2, 3, 5, 6, 7, 4], 8, seq(0.5));
  assert.deepStrictEqual(sc, [4, 13, 13, 13, 13, 13, 13, 13]);
});

test('pool draws without repeats until the pool runs out', () => {
  const sc = pickRivals({ mode: 'pool', pool: [10, 11, 12], fixed: [] }, [0, 0, 0, 0, 0, 0, 0], 7, seq(0));
  assert.deepStrictEqual(sc.slice(1, 4).sort(), [10, 11, 12]);
  assert.deepStrictEqual(sc.slice(4, 7).sort(), [10, 11, 12]);
});

test('an empty pool falls back to the game pick', () => {
  const sc = pickRivals({ mode: 'pool', pool: [], fixed: [] }, [0, 1, 2, 3], 4, seq(0));
  assert.deepStrictEqual(sc, [0, 1, 2, 3]);
});

test('fixed slots win in both modes', () => {
  const fixed = [null, 15, undefined, 2];
  assert.deepStrictEqual(pickRivals({ mode: 'game', pool: [], fixed }, [0, 1, 2, 3, 4], 5, seq(0)), [0, 1, 15, 3, 2]);
  assert.deepStrictEqual(pickRivals({ mode: 'pool', pool: [7], fixed }, [0, 1, 2, 3, 4], 5, seq(0)), [0, 7, 15, 7, 2]);
});

test('same random, same grid', () => {
  const cfg = { mode: 'pool', pool: [1, 2, 3, 4, 5, 6], fixed: [] };
  const a = pickRivals(cfg, new Int32Array(8), 8, seq(0.1, 0.7, 0.3));
  const b = pickRivals(cfg, new Int32Array(8), 8, seq(0.1, 0.7, 0.3));
  assert.deepStrictEqual(a, b);
});

test('parseRivals accepts a good config', () => {
  assert.deepStrictEqual(parseRivals('{"count":5,"mode":"pool","pool":[1,2],"fixed":[3,null]}', 16, 8),
    { count: 5, mode: 'pool', pool: [1, 2], fixed: [3, null] });
});

test('parseRivals repairs garbage instead of throwing', () => {
  assert.strictEqual(parseRivals(null, 16, 8), null);
  assert.strictEqual(parseRivals('not json', 16, 8), null);
  assert.strictEqual(parseRivals('7', 16, 8), null);
  assert.deepStrictEqual(parseRivals('{"count":99,"mode":"x","pool":[1,1,40,-1,"2"],"fixed":[99,4,1,1,1,1,1,1,1]}', 16, 8),
    { count: 8, mode: 'game', pool: [1], fixed: [null, 4, 1, 1, 1, 1, 1] });
  assert.deepStrictEqual(parseRivals('{"pool":"all"}', 16, 8), { count: 7, mode: 'game', pool: [], fixed: [] });
});

test('defaultRivals: seven cars, every car, the game pick', () => {
  assert.deepStrictEqual(defaultRivals(3), { count: 7, mode: 'game', pool: [0, 1, 2], fixed: [] });
});

test('toggleTier adds the tier when any is missing, else removes it', () => {
  const cars = [{ i: 0, tier: 'C' }, { i: 1, tier: 'C' }, { i: 2, tier: 'A' }];
  assert.deepStrictEqual(toggleTier([2, 0], cars, 'C').sort(), [0, 1, 2]);
  assert.deepStrictEqual(toggleTier([0, 1, 2], cars, 'C'), [2]);
});

test('NFM2_CCLASS is CarDefine.cclass, and gives 6 C / 5 B / 5 A', () => {
  const src = fs.readFileSync(new URL('./CarDefine.js', import.meta.url), 'utf8');
  const cclass = src.match(/this\.cclass = Int32Array\.from\(\[([^\]]*)\]/)[1].split(',').map(Number).slice(0, 16);
  assert.deepStrictEqual(NFM2_CCLASS, cclass);
  const t = NFM2_CCLASS.map(tierOfClass);
  assert.deepStrictEqual(['C', 'B', 'A'].map((x) => t.filter((y) => y === x).length), [6, 5, 5]);
});
