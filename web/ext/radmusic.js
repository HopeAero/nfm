// Extended's tracker music through the base port's BassoonTracker (web/music.js).
//
// A music .radq is a ZIP holding one ProTracker .mod. The jar reads it in
// RadicalMod's constructor and ModSlayer pre-renders the whole song into a
// javax.sound line; here the constructor only keeps the path (the radmod-lazy
// ext-patch), and these methods stand in for the rest with the jar's contract:
// loaded 1 = file known, 2 = loadMod'ed; playing; play() on a playing module is
// a no-op (carselect calls it every frame). loadMod's gain and bpm go to the
// tracker as the base's loadstrack does; its rate is dropped, as music.js
// explains.
//
// There is one tracker, so one module sounds at a time: play()/resume() make
// that RadicalMod the current one and load it into the tracker if it is not
// already there; stop()/unloadMod() on the current one stop the tracker.

import * as music from '../music.js';
import { readRadq } from './radq.js';
import { RadicalMod } from './RadicalMod.js';

const mods = new Map();        // path -> Promise<Uint8Array | null>, the .mod inside
const modBytes = (path) => {
  if (!mods.has(path)) {
    mods.set(path, readRadq(`ext/${path}`).then((zip) => zip.values().next().value || null).catch((e) => {
      console.warn('music: no module', path, e);
      return null;
    }));
  }
  return mods.get(path);
};

let current = null;            // the RadicalMod in the tracker
let loadedInTracker = null;    // the RadicalMod whose module the tracker holds

async function start(mod) {
  current = mod;
  if (loadedInTracker !== mod) {
    const bytes = await modBytes(mod.path);
    if (current !== mod || !mod.playing || !bytes) return;
    loadedInTracker = null;
    if (!(await music.loadBytes(bytes, mod.gain, mod.bpm))) return;
    loadedInTracker = mod;
    if (current !== mod || !mod.playing) return;
  }
  music.resume();
}

export function installMusic(musicvol) {
  music.setVolume(musicvol / 100);
  if (!musicvol) music.disable();       // the tracker's mixer costs CPU even at zero volume
  Object.assign(RadicalMod.prototype, {
    loadMod(gain, rate, bpm) {
      if (this.loaded !== 1) return;
      this.gain = gain;
      this.bpm = bpm;
      this.loaded = 2;
      void modBytes(this.path);        // start the fetch now; play() comes later
    },
    play() {
      if (this.playing || this.loaded !== 2) return;
      this.playing = true;
      void start(this);
    },
    resume() { this.play(); },
    stop() {
      if (!this.playing) return;
      this.playing = false;
      if (current === this) { music.stop(); current = null; }
    },
    unloadMod() {
      if (this.loaded !== 2) return;
      this.stop();
      if (loadedInTracker === this) loadedInTracker = null;
      this.loaded = 1;
    },
    unloadAll() { this.unloadMod(); },
  });
  return { unlock: () => music.unlock(), stop: () => { current = null; music.stop(); } };
}
