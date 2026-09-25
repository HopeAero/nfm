// The career's two-part .ogg music switches from the intro to the loop during the race
// (RaceTick.musicSwitch: run() does it outside the fase 0 block racetick was cut from).

import { test } from 'node:test';
import assert from 'node:assert';
import { RaceTick } from './racetick.js';
import { System } from './jawt.js';

test('the intro gives way to the looping half once switch(ms) has passed', () => {
  const played = [];
  const track = (name) => ({ nooggloop: name.endsWith('a'), playingogg: true, pausedogg: false,
    setPaused() {}, unload() {}, play() { played.push(name); } });
  const mtracks = [];
  mtracks[100] = track('1a');
  mtracks[101] = track('1b');
  const xt = { lastload: 100, stracks: new Array(200).fill(null), mtracks, loadedt: [], fase: 0, careermode: true,
    duration: 0, pausetime: 0, elapsed: 0, musicswitch: 7748000000 };
  System.now = 1e9;
  const race = new RaceTick({}, { checkpoints: { stage: 1 }, xtgraphics: xt });
  race.musicSwitch();
  assert.deepStrictEqual(played, []);
  System.now = 8e9;                   // 8 s into the intro (the page's clock is performance.now)
  race.musicSwitch();
  assert.deepStrictEqual(played, ['1b']);
  assert.strictEqual(xt.lastload, 101);
  System.now = 0;
});
