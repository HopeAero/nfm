// Extended's sound effects, with the base port's audio (web/audio.js over
// data/sounds.zip): the same engine, air, crash, skid, countdown, checkpoint
// and wasted clips the base NFM2 plays, by the file name Extended asks for.
//
// Extended loads its clips as java.applet.AudioClips by path
// (xtGraphics.loaddata: data/Files/sounds/JavaNew/crash1.wav, ...);
// getAudioClip hands back a stand-in bound to the base clip of that name, with
// the base XtGraphics._clip's semantics (loop() on a looping clip is a no-op,
// stop() cuts the loop and the one-shot). Two clips are Extended's own and not
// in sounds.zip -- caught.wav (the career's ghost flash) and redflash.wav (the
// teleport) -- and are read from ext/data/Files/sounds/.
//
// The jar has no scrape sound (nor sparks: the sparks-* patches added the
// base's). The base Mad plays scrape() on a wall spark and gscrape() on a
// ground one, for the player's car only; here ContO.sprk on the player's car
// does the same, with the base XtGraphics's debounce, counted down per tick
// in playsounds.

import { Audio } from '../audio.js';
import { readBytes } from '../vfs.js';
import { Applet } from './jawt.js';
import { ContO } from './ContO.js';

const OWN = ['caught', 'redflash'];

/** The base clip for a path Extended asks for: its file name without the extension. */
export const clipName = (path) => String(path).split('/').pop().replace(/\.[^.]*$/, '');

export function installSound(sfxvol) {
  const snd = new Audio();
  snd.setVolume(sfxvol / 100);
  const ready = snd.load().then(() => Promise.all(OWN.map(async (name) => {
    try { await snd.addClip(name, await readBytes(`ext/data/Files/sounds/${name}.wav`)); } catch { /* silent */ }
  }))).catch((e) => console.warn('sound: failed to load', e));

  Applet.prototype.getAudioClip = (codeBase, path) => {
    const name = clipName(path);
    return {
      play: () => snd.play(name),
      loop: () => snd.loop(name),
      stop: () => { snd.stopLoop(name); snd.stop(name); },
    };
  };

  // ---- the base's scrape sounds, on the player's sparks ----------------------
  const sc = { bfscrape: 0, bfsc1: 0, bfsc2: 0, sturn0: 0, sturn1: 0 };
  let player = null, xt = null;
  const speed = (x, y, z) => Math.sqrt(x * x + y * y + z * z) / 10.0;
  // base XtGraphics.scrape: a wall, alternating scrape1/scrape2 with runs of at most 3
  // ponytail: Math.random, not the draw bank -- presentation only, and Extended has no netplay
  const scrape = (x, y, z) => {
    if (sc.bfscrape !== 0 || speed(x, y, z) <= 10.0) return;
    let n4 = Math.random() > Math.random() ? 1 : 0;
    if (n4 === 0) {
      sc.sturn1 = 0;
      if (++sc.sturn0 === 3) { n4 = 1; sc.sturn1 = 1; sc.sturn0 = 0; }
    } else {
      sc.sturn0 = 0;
      if (++sc.sturn1 === 3) { n4 = 0; sc.sturn0 = 1; sc.sturn1 = 0; }
    }
    snd.play(`scrape${n4 + 1}`);
    sc.bfscrape = 5;
  };
  // base XtGraphics.gscrape: the ground, two copies of scrape3 so a new one can cut the last
  const gscrape = (x, y, z) => {
    if ((sc.bfsc1 !== 0 && sc.bfsc2 !== 0) || speed(x, y, z) <= 15.0) return;
    const [clip, a, b] = sc.bfsc1 === 0 ? ['scrape3', 12, 6] : ['scrape3b', 6, 12];
    snd.stop(clip);
    snd.play(clip);
    sc.bfsc1 = a;
    sc.bfsc2 = b;
  };
  const sprk = ContO.prototype.sprk;
  ContO.prototype.sprk = function (n, n2, n3, rcx, rcy, rcz, kind) {
    if (this === player && !xt?.mutes) {
      if (kind === 0) scrape(Math.trunc(rcx), Math.trunc(rcy), Math.trunc(rcz));
      if (kind === 1) gscrape(Math.trunc(rcx), Math.trunc(rcy), Math.trunc(rcz));
    }
    return sprk.call(this, n, n2, n3, rcx, rcy, rcz, kind);
  };

  return {
    snd,
    ready,
    /** The race's xtGraphics and the player's car: scrape sounds from its sparks. */
    attach(xtg, playerConto) {
      xt = xtg;
      player = playerConto;
      const playsounds = xtg.playsounds;
      xtg.playsounds = function (...a) {
        for (const k of ['bfscrape', 'bfsc1', 'bfsc2']) if (sc[k] !== 0) --sc[k];
        return playsounds.apply(this, a);
      };
    },
    unlock: () => snd.unlock(),
    stopAll: () => snd.stopAllLoops(),
  };
}
