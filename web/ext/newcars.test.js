// Extended's new cars (newcars.js): a new car's identity is its donor's number,
// and the Free Play car select reaches the new cars past car 38.

import { test } from 'node:test';
import assert from 'node:assert';
import { NEW_BASE, id, setNewCars, lastCar, isNew, nextCar } from './newcars.js';

test('stock cars are their own identity; new cars answer as their donor', () => {
  setNewCars([{ name: 'A', donor: 30 }, { name: 'B', donor: 13 }]);
  for (let c = 0; c < 39; c++) assert.strictEqual(id(c), c);
  assert.strictEqual(id(NEW_BASE), 30);
  assert.strictEqual(id(NEW_BASE + 1), 13);
  assert.strictEqual(lastCar(), NEW_BASE + 1);
  assert.ok(isNew(NEW_BASE) && !isNew(38));
});

test('the car select steps 38 -> first new car -> ... and back, clamped at both ends', () => {
  setNewCars([{ name: 'A', donor: 30 }, { name: 'B', donor: 13 }]);
  assert.strictEqual(nextCar(38, 1), NEW_BASE);
  assert.strictEqual(nextCar(NEW_BASE, -1), 38);
  assert.strictEqual(nextCar(NEW_BASE + 1, 1), NEW_BASE + 1);
  assert.strictEqual(nextCar(0, -1), 0);
  setNewCars([]);
  assert.strictEqual(nextCar(38, 1), 38);
  assert.strictEqual(lastCar(), 38);
});
