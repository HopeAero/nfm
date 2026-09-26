import test from 'node:test';
import assert from 'node:assert/strict';
import { OggClip } from './jawt.js';

test('zero music volume does not start an Extended Ogg clip', () => {
  const OriginalAudio = globalThis.Audio;
  const previousVolume = OggClip.volume;
  const previousLoad = OggClip.load;
  let plays = 0;
  globalThis.Audio = class {
    paused = true;
    volume = 1;
    play() { plays++; this.paused = false; return Promise.resolve(); }
    pause() { this.paused = true; }
  };
  OggClip.volume = 0;
  OggClip.load = null;
  try {
    const clip = new OggClip({ path: 'data/Files/careermusic/test.ogg' });
    clip.play();
    OggClip.unlock();
    assert.equal(clip.el.volume, 0);
    assert.equal(plays, 0);
    clip.close();

    OggClip.volume = 1;
    const audible = new OggClip({ path: 'data/Files/careermusic/test.ogg' });
    audible.play();
    assert.equal(audible.el.volume, 0.6);
    assert.equal(plays, 1);
    audible.close();
  } finally {
    globalThis.Audio = OriginalAudio;
    OggClip.volume = previousVolume;
    OggClip.load = previousLoad;
  }
});
