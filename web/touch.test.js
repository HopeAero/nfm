import test from 'node:test';
import assert from 'node:assert/strict';
import { touchPointers, touchWanted, ACTIONS } from './touch.js';

const log = () => { const out = []; return { out, send: (type, a) => out.push(`${type}:${a}`) }; };

test('touchPointers: a finger presses its button and releases it', () => {
  const l = log(), t = touchPointers(l.send);
  t.down(1, 'up'); t.up(1);
  assert.deepEqual(l.out, ['keydown:up', 'keyup:up']);
});

test('touchPointers: two fingers at once (gas and steer), each released on its own', () => {
  const l = log(), t = touchPointers(l.send);
  t.down(1, 'up'); t.down(2, 'left'); t.up(2); t.up(1);
  assert.deepEqual(l.out, ['keydown:up', 'keydown:left', 'keyup:left', 'keyup:up']);
});

test('touchPointers: sliding a thumb from left to right swaps the key without lifting', () => {
  const l = log(), t = touchPointers(l.send);
  t.down(1, 'left'); t.move(1, 'right'); t.move(1, 'right'); t.move(1, null); t.up(1);
  assert.deepEqual(l.out, ['keydown:left', 'keyup:left', 'keydown:right', 'keyup:right']);
});

test('touchPointers: a key held by two fingers is released only when both lift', () => {
  const l = log(), t = touchPointers(l.send);
  t.down(1, 'up'); t.down(2, 'up'); t.up(1);
  assert.deepEqual(l.out, ['keydown:up']);
  t.up(2);
  assert.deepEqual(l.out, ['keydown:up', 'keyup:up']);
});

test('touchWanted: automatic on touch screens only; always / never override', () => {
  assert.equal(touchWanted('auto', true), true);
  assert.equal(touchWanted('auto', false), false);
  assert.equal(touchWanted(undefined, true), true);
  assert.equal(touchWanted('on', false), true);
  assert.equal(touchWanted('off', true), false);
});

test('ACTIONS carry the base race key (code) and Extended\'s (key)', () => {
  assert.deepEqual([ACTIONS.up.code, ACTIONS.up.key], ['ArrowUp', 'ArrowUp']);
  assert.deepEqual([ACTIONS.handb.code, ACTIONS.handb.key], ['Space', ' ']);
  assert.deepEqual([ACTIONS.look.code, ACTIONS.look.key], ['ShiftLeft', 'z']);   // main.js Shift; the jar's Z
  assert.deepEqual([ACTIONS.pause.code, ACTIONS.pause.key], ['Escape', 'Escape']);
});
