// The launcher's choice of new cars for Extended's Free Play (ext/newcars-store.js):
// one entry per Car Maker car switched on, with the stock car lending its special.

import { test } from 'node:test';
import assert from 'node:assert';
import { parseStore, donorChoices, toggle } from './ext/newcars-store.js';

test('the store keeps valid entries only', () => {
  assert.deepStrictEqual(parseStore('[{"name":"A","donor":30},{"name":"","donor":1},{"name":"B","donor":99},"x"]'),
    [{ name: 'A', donor: 30 }]);
  assert.deepStrictEqual(parseStore('not json'), []);
  assert.deepStrictEqual(parseStore(null), []);
});

test('donor choices: off, then the 39 stock cars', () => {
  const d = donorChoices();
  assert.strictEqual(d.length, 40);
  assert.strictEqual(d[0].donor, -1);
  assert.strictEqual(d[39].name, 'DR Monstaa');
});

test('choosing a donor switches a car on; choosing off removes it', () => {
  let s = toggle([], 'A', 13);
  assert.deepStrictEqual(s, [{ name: 'A', donor: 13 }]);
  s = toggle(s, 'A', 30);
  assert.deepStrictEqual(s, [{ name: 'A', donor: 30 }]);
  assert.deepStrictEqual(toggle(s, 'A', -1), []);
});
