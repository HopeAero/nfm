// Extended's new cars (newcars.js): a new car's identity is its donor's number,
// and the Free Play car select shows them under "My cars", the game's 39 under "Game cars".

import { test } from 'node:test';
import assert from 'node:assert';
import { NEW_BASE, id, setNewCars, lastCar, isNew, nextCar, setCarGroup, carGroup, firstCar, inGroup,
  cycleCarGroup } from './newcars.js';

test('stock cars are their own identity; new cars answer as their donor', () => {
  setNewCars([{ name: 'A', donor: 30 }, { name: 'B', donor: 13 }]);
  for (let c = 0; c < 39; c++) assert.strictEqual(id(c), c);
  assert.strictEqual(id(NEW_BASE), 30);
  assert.strictEqual(id(NEW_BASE + 1), 13);
  assert.ok(isNew(NEW_BASE) && !isNew(38));
});

test('Game cars: 0..38 only, clamped at both ends', () => {
  setNewCars([{ name: 'A', donor: 30 }, { name: 'B', donor: 13 }]);
  setCarGroup('game');
  assert.deepStrictEqual([firstCar(), lastCar()], [0, 38]);
  assert.strictEqual(nextCar(38, 1), 38);
  assert.strictEqual(nextCar(0, -1), 0);
  assert.strictEqual(nextCar(5, 1), 6);
  assert.ok(inGroup(5) && !inGroup(NEW_BASE));
});

test('My cars: the new cars only, clamped at both ends', () => {
  setNewCars([{ name: 'A', donor: 30 }, { name: 'B', donor: 13 }]);
  setCarGroup('mine');
  assert.deepStrictEqual([firstCar(), lastCar()], [NEW_BASE, NEW_BASE + 1]);
  assert.strictEqual(nextCar(NEW_BASE, -1), NEW_BASE);
  assert.strictEqual(nextCar(NEW_BASE, 1), NEW_BASE + 1);
  assert.strictEqual(nextCar(NEW_BASE + 1, 1), NEW_BASE + 1);
  assert.ok(!inGroup(38) && inGroup(NEW_BASE + 1));
  setCarGroup('game');
});

test('no Car Maker cars: always Game cars; nonsense is Game cars', () => {
  setNewCars([]);
  setCarGroup('mine');
  assert.strictEqual(carGroup(), 'game');
  assert.deepStrictEqual([firstCar(), lastCar()], [0, 38]);
  setNewCars([{ name: 'A', donor: 30 }]);
  setCarGroup('all');
  assert.strictEqual(carGroup(), 'game');
});

test('switching the group shows the first car of the other one', () => {
  setNewCars([{ name: 'A', donor: 30 }]);
  setCarGroup('game');
  assert.deepStrictEqual(cycleCarGroup(7), { group: 'mine', car: NEW_BASE });
  assert.deepStrictEqual(cycleCarGroup(NEW_BASE), { group: 'game', car: 0 });
  setNewCars([]);
  assert.deepStrictEqual(cycleCarGroup(7), { group: 'game', car: 7 });   // nothing to switch to
});
