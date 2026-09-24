// A page's query string counts only in developer mode; Test Drive links always.

import { test } from 'node:test';
import assert from 'node:assert';
import { pageParams } from './devmode.js';

function at(search, devmode) {
  const store = { 'nfm.launcher': JSON.stringify({ devmode }) };
  globalThis.localStorage = { getItem: (k) => store[k] ?? null };
  globalThis.location = { search };
  const warned = [];
  const p = pageParams((m) => warned.push(m));
  return { p: Object.fromEntries(p), warned };
}

test('developer mode off: only the editors\' Test Drive parameters', () => {
  const { p, warned } = at('?mystage=Loop&from=stagemaker&stage=30&selftest=400', false);
  assert.deepStrictEqual(p, { mystage: 'Loop', from: 'stagemaker' });
  assert.match(warned[0], /\?stage, \?selftest/);
});

test('developer mode on: everything', () => {
  const { p, warned } = at('?ext=classic&nfm2stage=30&stats=1', true);
  assert.deepStrictEqual(p, { ext: 'classic', nfm2stage: '30', stats: '1' });
  assert.deepStrictEqual(warned, []);
});
