// carstore: the listing and slot-assignment rules loadcarmaker() gets from the
// filesystem in Java and cannot get from a browser.
//
// The IndexedDB half is not tested here — node has no IndexedDB, and it is a
// thin wrapper. What is tested is everything that decides WHICH car ends up in
// WHICH slot, because a car changing slot between sessions changes what you
// race, and because a stored car failing to shadow a shipped one would make
// "edit a shipped car" silently edit nothing.
import test from 'node:test';
import assert from 'node:assert';
import { readFileSync } from 'node:fs';
import {
  SHIPPED, useBackend, memoryBackend, listAll, listStored,
  readCar, writeCar, deleteCar, renameCar, loadIntoCarDefine,
} from './carstore.js';
import { CarDefine } from './CarDefine.js';
import { Medium } from './Medium.js';
import { Trackers } from './Trackers.js';
import { ContO } from './ContO.js';

const repo = (p) => readFileSync(new URL(p, new URL('../', import.meta.url)), 'utf8');

function fresh(entries = []) {
  const map = new Map();
  for (const [name, text] of entries) map.set(name, { name, text, updated: 0 });
  useBackend(memoryBackend(map));
  return map;
}

test('listAll merges shipped and stored, with stored shadowing shipped', async () => {
  fresh([['Simple Car', 'edited'], ['My Car', 'new']]);
  const all = await listAll();
  // the shipped ones + 1 new; 'Simple Car' appears once, not twice.
  assert.strictEqual(all.length, SHIPPED.length + 1);
  assert.strictEqual(all.filter((n) => n === 'Simple Car').length, 1);
  assert.deepStrictEqual(all, [...all].sort((a, b) => a.localeCompare(b)));
});

test('readCar prefers the stored copy over the shipped file', async () => {
  fresh([['Simple Car', 'stored text']]);
  assert.strictEqual(await readCar('Simple Car'), 'stored text');
});

test('readCar returns null for an unknown car rather than throwing', async () => {
  fresh();
  assert.strictEqual(await readCar('no such car'), null);
});

test('deleting a stored copy un-shadows the shipped car', async () => {
  fresh([['Simple Car', 'stored text']]);
  await deleteCar('Simple Car');
  assert.deepStrictEqual(await listStored(), []);
  // Still listed: the shipped file is untouched by a delete.
  assert.ok((await listAll()).includes('Simple Car'));
});

test('renameCar moves the text and refuses to clobber', async () => {
  fresh([['a', 'text a'], ['b', 'text b']]);
  await renameCar('a', 'c');
  assert.strictEqual(await readCar('c'), 'text a');
  assert.strictEqual(await readCar('a'), null);
  await assert.rejects(() => renameCar('c', 'b'), /already exists/);
  assert.strictEqual(await readCar('c'), 'text a');   // failed rename kept it
});

test('writeCar rejects an empty name', async () => {
  fresh();
  await assert.rejects(() => writeCar('', 'x'), /needs a name/);
});

test('loadIntoCarDefine fills slots 16.. and skips cars loadcar rejects', async () => {
  // The real .rad off disk, so this exercises ContO's parser, not a stub.
  // It has to be a COMMITTED file: mycars/ is a runtime directory and the two
  // cars this used to read are gitignored, so the test passed only on a
  // machine that had run the desktop game.
  const good = repo('mycars/Simple Car.rad');
  fresh([['aaa good', good], ['bbb bad', 'not a car at all'], ['ccc good', good]]);

  const cd = new CarDefine(new Array(56).fill(null), new Medium(), new Trackers(), null);
  const loaded = await loadIntoCarDefine(cd);

  // 'bbb bad' consumes no slot: loadcar returns -1 and nlcars is untouched,
  // so the next good car reuses slot 17 (CarDefine.java:1579-1586).
  assert.deepStrictEqual(loaded, ['aaa good', 'ccc good']);
  assert.strictEqual(cd.nlcars, 18);
  assert.ok(cd.bco[16] instanceof ContO);
  assert.ok(cd.bco[17] instanceof ContO);
  assert.strictEqual(cd.lastload, 1);
});

test('loadIntoCarDefine leaves nlcars at 16 and lastload alone when nothing loads', async () => {
  fresh([['junk', 'not a car at all']]);
  useBackend(memoryBackend(new Map([['junk', { name: 'junk', text: 'not a car' }]])));
  const cd = new CarDefine(new Array(56).fill(null), new Medium(), new Trackers(), null);
  cd.lastload = 0;
  const loaded = await loadIntoCarDefine(cd);
  assert.deepStrictEqual(loaded, []);
  assert.strictEqual(cd.nlcars, 16);
  assert.strictEqual(cd.lastload, 0);
});

test('readModel: every Extended / Revised and Recharged car in the editor picker opens as a car', async () => {
  const { readModel, EXT_MODELS, RR_MODELS } = await import('./carstore.js');
  const { setFpath } = await import('./vfs.js');
  const saved = globalThis.fetch;
  globalThis.fetch = async (p) => new Response(readFileSync(new URL(p, import.meta.url)));
  setFpath('../');
  try {
    assert.strictEqual(Object.keys(EXT_MODELS).length, 23);
    assert.strictEqual(Object.keys(RR_MODELS).length, 25);
    for (const [src, table] of [['ext', EXT_MODELS], ['rr', RR_MODELS]]) {
      for (const name of Object.keys(table)) {
        assert.match(await readModel(src, name) ?? '', /\nw\(/, `${src}:${name}`);
      }
    }
    assert.match(await readModel('rr', 'A-1'), /\ncarmaker\(Ryan Albano\)\n$/);
    assert.doesNotMatch(await readModel('ext', 'Remington'), /carmaker\(Ryan/);
    assert.strictEqual(await readModel('rr', 'Formula 7'), null);
    // Past NFM 2's 210 polygons, under the raised 1000-piece cap: loads whole.
    const big = await readModel('rr', 'Over=Kill');
    const o = new ContO(new TextEncoder().encode(big), new Medium(), new Trackers());
    assert.strictEqual(o.npl, big.match(/<p>/g).length + 4 * 19);
  } finally {
    globalThis.fetch = saved;
  }
});

test('readModel: Revised and Recharged cars open with their raw stats and race; nfm-origins cars load', async () => {
  const { readModel, RR_STATS, ORIGINS_MODELS } = await import('./carstore.js');
  const { setFpath } = await import('./vfs.js');
  const saved = globalThis.fetch;
  globalThis.fetch = async (p) => new Response(readFileSync(new URL(p, import.meta.url)));
  setFpath('../');
  const race = (name, text) => new CarDefine(new Array(56).fill(null), new Medium(), new Trackers(), null).loadcar(name, 16, text);
  try {
    const rebound = await readModel('rr', 'Air Rebound');
    assert.match(rebound, /\nmaxmag\(5000\)\n/);
    assert.match(rebound, /\ncarmaker\(Ryan Albano\)\n$/);
    // R&R models carry no 1stColor/2ndColor, which the Car Maker needs before it races
    // a car: the two commonest face colours stand in, so the car looks the same.
    assert.match(rebound, /\n1stColor\(\d+,\d+,\d+\)\n2ndColor\(\d+,\d+,\d+\)\n/);
    // no stat(), no calibration: the raw stats alone make them raceable
    for (const name of Object.keys(RR_STATS)) {
      if (name === 'ROCKET M A S H E E N') continue;   // still refused by NFM 2's wheel rule (WORK.md)
      assert.equal(race(name, await readModel('rr', name)), 16, name);
    }
    for (const name of Object.keys(ORIGINS_MODELS)) {
      assert.equal(race(name, await readModel('origins', name)), 16, name);
    }
    assert.match(await readModel('origins', 'BMW M3 GTR'), /\ncarmaker\(ACVoong\)\n$/);
    assert.doesNotMatch(await readModel('origins', 'Electro LMP'), /carmaker\(/);
  } finally {
    globalThis.fetch = saved;
  }
});
