// career.js: the NFM1/NFM2 lines of user.data, and "unlock everything"
// never being written back as progress.
import test from 'node:test';
import assert from 'node:assert';

const store = new Map();
globalThis.localStorage = {
  getItem: (k) => (store.has(k) ? store.get(k) : null),
  setItem: (k, v) => store.set(k, String(v)),
};
const { loadCareer, saveCareer, effectiveUnlocked } = await import('./career.js');

test('a fresh career starts on stage 1 of both games', () => {
  store.clear();
  assert.deepStrictEqual(loadCareer(), { unlocked: [1, 1], scm: [0, 0] });
});

test('saving one mode leaves the other alone; out-of-range values are dropped', () => {
  store.clear();
  saveCareer(1, 5, [3, 1]);
  saveCareer(2, 9, [11, 4]);
  assert.deepStrictEqual(loadCareer(), { unlocked: [3, 4], scm: [5, 9] });
  store.set('nfm.career', JSON.stringify({ unlocked: [12, 0], scm: [16, -1] }));
  assert.deepStrictEqual(loadCareer(), { unlocked: [1, 1], scm: [0, 0] });
});

test('unlock everything is a view, and a car pick (null) keeps real progress', () => {
  store.clear();
  saveCareer(1, 2, [4, 1]);
  const c = loadCareer();
  assert.deepStrictEqual(effectiveUnlocked(c, true), [11, 17]);
  saveCareer(1, 6, null);
  assert.deepStrictEqual(loadCareer(), { unlocked: [4, 1], scm: [6, 0] });
});
