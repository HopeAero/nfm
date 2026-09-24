import test from 'node:test';
import assert from 'node:assert/strict';
import { HIGHLIGHT_FRAMES, HIGHLIGHT_PASSES, highlightTitle, shouldPlayHighlight } from './highlight.js';
import { Record } from './Record.js';

test('highlight playback requires a captured solo event', () => {
  assert.equal(shouldPlayHighlight({ hcaught: false, wasted: 0, whenwasted: 190, stage: 3, looped: 0 }), false);
  assert.equal(shouldPlayHighlight({ hcaught: true, wasted: 0, whenwasted: 190, stage: 3, looped: 0, multiplayer: true }), false);
  assert.equal(shouldPlayHighlight({ hcaught: true, wasted: 0, whenwasted: 190, stage: 3, looped: 0 }), true);
});

test('tutorial stunt highlights stay suppressed until a loop, but a loss still plays', () => {
  const event = { hcaught: true, wasted: 0, whenwasted: 190, stage: 1, looped: 1 };
  assert.equal(shouldPlayHighlight(event), false);
  assert.equal(shouldPlayHighlight({ ...event, whenwasted: 229 }), true);
  assert.equal(shouldPlayHighlight({ ...event, looped: 0 }), true);
});

test('highlight title follows the original event-specific labels', () => {
  assert.equal(highlightTitle({ wasted: 2, whenwasted: 190, closefinish: 0, localPlayer: 0, stage: 3 }), "You Wasted 'em!");
  assert.equal(highlightTitle({ wasted: 2, whenwasted: 190, closefinish: 1, localPlayer: 0, stage: 3 }), 'Close Finish!');
  assert.equal(highlightTitle({ wasted: 2, whenwasted: 190, closefinish: 2, localPlayer: 0, stage: 3 }), 'Close Finish! Almost got it!');
  assert.equal(highlightTitle({ wasted: 0, whenwasted: 229, closefinish: 0, localPlayer: 0, stage: 3 }), 'Wasted!');
  assert.equal(highlightTitle({ wasted: 0, whenwasted: 190, closefinish: 0, localPlayer: 0, stage: 3 }), 'Stunts!');
  assert.equal(highlightTitle({ wasted: 0, whenwasted: 190, closefinish: 0, localPlayer: 0, stage: 2 }), 'Best Stunt!');
});

test('a highlight repeats its captured 300 tick clip three times like Java', () => {
  assert.equal(HIGHLIGHT_FRAMES * HIGHLIGHT_PASSES, 900);
});

test('Record captures a real destroy event and plays its frozen frames without ghost recording', () => {
  const record = new Record(null);
  record.ghosts = false;
  record.caught = HIGHLIGHT_FRAMES;
  record.dest[0] = 231;
  record.x[0][0] = 1234;
  record.y[0][0] = 567;
  record.z[299][0] = -890;

  const car = {
    x: 0, y: 0, z: 0, xy: 0, zy: 0, xz: 0, wxz: 0, wzy: 0,
    m: { checkpoint: 7, lastcheck: true },
    stg: new Array(20).fill(0), sprk: 0,
  };
  record.rec(car, 0, 0, 0, 1, 0);

  assert.equal(record.hcaught, true);
  assert.equal(record.whenwasted, 229);
  assert.equal(record.hx[0][0], 1234);
  assert.equal(record.hdest[0], 230);
  assert.equal(shouldPlayHighlight({
    hcaught: record.hcaught,
    wasted: record.wasted,
    whenwasted: record.whenwasted,
    stage: 3,
    looped: 0,
  }), true);

  record.playh(car, {}, 0, HIGHLIGHT_FRAMES - 1, 0);
  assert.equal(car.z, -890);
  assert.equal(car.m.checkpoint, record.hcheckpoint[HIGHLIGHT_FRAMES - 1]);
});
