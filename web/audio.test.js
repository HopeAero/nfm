import test from 'node:test';
import assert from 'node:assert/strict';
import { Audio, loopBackendFor, loopWav } from './audio.js';

test('Opera OPR user agents use media elements for continuous loops', () => {
  assert.equal(loopBackendFor('Mozilla/5.0 Chrome/140.0.0.0 Safari/537.36'), 'buffer');
  assert.equal(loopBackendFor('Mozilla/5.0 Chrome/140.0.0.0 OPR/125.0.0.0'), 'media');
  assert.equal(loopBackendFor(''), 'buffer');
});

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

test('media-backed engine loops use resampled WAV and obey effects volume', () => {
  const OriginalAudio = globalThis.Audio;
  const played = [];
  globalThis.Audio = class {
    paused = true;
    constructor(url) { this.url = url; }
    play() { this.paused = false; played.push(this); return Promise.resolve(); }
    pause() { this.paused = true; }
  };
  try {
    const audio = new Audio({ loopBackend: 'media' });
    audio.ctx = { state: 'running', createBufferSource() { throw new Error('used Web Audio'); } };
    audio.gain = { gain: { value: 1 } };
    audio.buffers.set('20', {
      sampleRate: 48000,
      getChannelData: () => Float32Array.of(-1, 0, 1, 0),
    });
    const wav = new DataView(loopWav(audio.buffers.get('20'), 0.001));
    assert.equal(wav.getUint32(24, true), 48000);
    assert.equal(wav.getInt16(44, true), -32768);
    assert.equal(wav.getInt16(48, true), 32767);
    assert.equal(wav.getInt16(52, true), -32768); // the source repeats exactly

    audio.setVolume(0.25);
    audio.loop('20');
    assert.equal(played.length, 1);
    assert.equal(played[0].loop, true);
    assert.equal(played[0].volume, 0.25);
    audio.setVolume(0.5);
    assert.equal(played[0].volume, 0.5);
    audio.setVolume(0);
    assert.equal(played[0].paused, true);
    assert.equal(audio.looping.size, 0);
    audio.loop('20');
    assert.equal(played.length, 1);
    for (const url of audio.loopUrls.values()) URL.revokeObjectURL(url);
  } finally {
    globalThis.Audio = OriginalAudio;
  }
});
