import test from 'node:test';
import assert from 'node:assert/strict';
import { touchPointers, touchWanted, easyStuntsEnabled, installFinishTap, ACTIONS } from './touch.js';

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
  assert.deepEqual([ACTIONS.target.code, ACTIONS.target.key], ['KeyA', 'a']);   // toggle arrow target in both races
  assert.deepEqual([ACTIONS.pause.code, ACTIONS.pause.key], ['Escape', 'Escape']);
});

test('a finger can slide off and return without lifting', () => {
  const l = log(), t = touchPointers(l.send);
  t.down(1, 'up'); t.move(1, null); t.move(1, 'up'); t.up(1);
  assert.deepEqual(l.out, ['keydown:up', 'keyup:up', 'keydown:up', 'keyup:up']);
});
test('clearing interrupted gestures releases all held actions', () => {
  const l = log(), t = touchPointers(l.send);
  t.down(1, 'up'); t.down(2, 'left'); t.clear(); t.move(1, 'up'); t.up(2);
  assert.deepEqual(l.out, ['keydown:up', 'keydown:left', 'keyup:up', 'keyup:left']);
});

test('easy stunts require explicit opt-in, independent of touch detection', () => {
  const previous = globalThis.localStorage;
  try {
    for (const [saved, wanted] of [[{}, false], [{touch:'on'}, false], [{touch:'off', easyStunts:true}, true], [{easyStunts:false}, false]]) {
      globalThis.localStorage = {getItem: () => JSON.stringify(saved)};
      assert.equal(easyStuntsEnabled(), wanted);
    }
  } finally { if(previous === undefined) delete globalThis.localStorage; else globalThis.localStorage = previous; }
});

test('finish taps use Enter, never an unfinished or cancelled racing gesture', () => {
  const stage = new EventTarget(), control = {enter:false}; let active = false;
  const cleanup = installFinishTap(stage, () => active, control);
  const send = (type, pointerId = 1) => { const e = new Event(type, {cancelable:true}); e.pointerId = pointerId; stage.dispatchEvent(e); };
  send('pointerdown'); active = true; send('pointerup');
  assert.equal(control.enter,false, 'a race finger lifting after the result is not a continue tap');
  send('pointerdown'); send('pointercancel'); send('pointerup'); assert.equal(control.enter,false);
  send('pointerdown'); send('pointerup'); assert.equal(control.enter,true);
  control.enter = false; cleanup(); send('pointerdown'); send('pointerup'); assert.equal(control.enter,false);
});
