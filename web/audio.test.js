import test from 'node:test';
import assert from 'node:assert/strict';
import { Audio } from './audio.js';
import { Mixer } from './mixer.js';

test('zero effects volume does not start clips and stops a running engine loop', () => {
  const audio = new Audio();
  let starts = 0, stops = 0;
  audio.ctx = {
    state: 'running',
    createBufferSource: () => ({
      connect() {},
      start() { starts++; },
      stop() { stops++; },
    }),
  };
  audio.gain = { gain: { value: 1 } };
  audio.buffers.set('20', {});

  audio.setVolume(0);
  audio.play('20');
  audio.loop('20');
  assert.equal(starts, 0);
  assert.equal(audio.gain.gain.value, 0);

  audio.setVolume(1);
  audio.loop('20');
  assert.equal(starts, 1);
  audio.setVolume(0);
  assert.equal(stops, 1);
  assert.equal(audio.looping.size, 0);
});

test('mixer loops wrap sample-exact across blocks and do not double', () => {
  const m = new Mixer();
  m.command({ op: 'add', name: '20', samples: Float32Array.of(1, 2, 3) });
  m.command({ op: 'loop', name: '20' });
  m.command({ op: 'loop', name: '20' });            // already looping: no second voice
  const a = new Float32Array(4), b = new Float32Array(4);
  m.render(a); m.render(b);
  assert.deepEqual([...a, ...b], [1, 2, 3, 1, 2, 3, 1, 2]);
  m.command({ op: 'stopLoop', name: '20' });
  m.render(a);
  assert.deepEqual([...a], [0, 0, 0, 0]);
});

test('mixer one-shots sum, end on their own, and stop cuts only the latest', () => {
  const m = new Mixer();
  m.command({ op: 'add', name: 'crash1', samples: Float32Array.of(1, 1, 1, 1, 1, 1) });
  m.command({ op: 'play', name: 'crash1' });
  const out = new Float32Array(2);
  m.render(out);
  m.command({ op: 'play', name: 'crash1' });        // overlaps the first
  m.render(out);
  assert.deepEqual([...out], [2, 2]);
  m.command({ op: 'stop', name: 'crash1' });        // the second one
  m.render(out);
  assert.deepEqual([...out], [1, 1]);
  m.render(out);
  assert.equal(m.voices.length, 0);                 // the first ran out
  m.command({ op: 'play', name: 'unknown' });
  assert.equal(m.voices.length, 0);
});

test('with a mixer, audio.js posts commands instead of starting buffer sources', () => {
  const audio = new Audio();
  const sent = [];
  audio.ctx = { state: 'running', createBufferSource() { throw new Error('used buffer sources'); } };
  audio.gain = { gain: { value: 1 } };
  audio.mixer = { port: { postMessage: (m) => sent.push(m) } };
  audio.setClip('20', { getChannelData: () => Float32Array.of(0.5) });
  audio.loop('20'); audio.loop('20'); audio.play('20'); audio.stop('20');
  assert.equal(audio.isLooping('20'), true);
  audio.setVolume(0);
  assert.equal(audio.isLooping('20'), false);
  assert.deepEqual(sent.map((m) => m.op), ['add', 'loop', 'play', 'stop', 'stopLoop']);
});
