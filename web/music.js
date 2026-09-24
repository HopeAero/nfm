// Soundtrack playback.
//
// The game's music is 34 tracker modules in music/<name>.zip. The Java plays
// them through its own bundled `ibxm` tracker (RadicalMod -> ModuleLoader ->
// IBXModSlayer), which pre-renders each module to PCM at load. Rather than
// port ~2,500 lines of fixed-point mixer, this drives BassoonTracker, an
// existing pure-JS MOD/XM player (web/vendor/, ~16 KB gzipped). That keeps the
// soundtrack at the 3.3 MB of modules already in the repo -- pre-rendering
// them to audio instead came to 54 MB of Opus or 203 MB of FLAC.
//
// The tradeoff: BassoonTracker's mixer is not ibxm's, so playback is very
// close but not sample-identical to the desktop game.

import BassoonTracker from './vendor/bassoonplayer.js';
import { detectFpath, parseZip } from './vfs.js';
import { readSong, TRACKER } from './musicstore.js';

/**
 * Per-track constants, transcribed from `xtGraphics.loadstrack` (stages and
 * party) and `RadicalMod.loadimod` (interface). `[gain, rate, bpmflex]`.
 *
 * Note the rate column is in two different units and is unused either way:
 * the stage figures are pre-transform (RadicalMod turns 8400 into
 * `(int)(8400 / 8000f * 2f * 22000)` = 46200), while loadimod passes 44000
 * already transformed. See the comment on `rate` in load().
 */
const stageConstants = {
  '1': [240, 8400, 135],
  '2': [190, 9000, 145],
  '3': [170, 8500, 145],
  '4': [205, 7500, 125],
  '5': [170, 7900, 125],
  '6': [370, 7900, 125],
  '7': [205, 7500, 125],
  '8': [230, 7900, 125],
  '9': [180, 7900, 125],
  '10': [280, 8100, 145],
  '11': [120, 8000, 125],
  '12': [260, 7200, 125],
  '13': [270, 8000, 125],
  '14': [190, 8000, 125],
  '15': [162, 7800, 125],
  '16': [220, 7600, 125],
  '17': [300, 7500, 125],
  '18': [200, 7900, 125],
  '19': [200, 7900, 125],
  '20': [232, 7300, 125],
  '21': [370, 7900, 125],
  '22': [290, 7900, 125],
  '23': [222, 7600, 125],
  '24': [230, 8000, 125],
  // Stage 27 is party.zip instead when gmode == 2; the caller passes the name.
  '25': [220, 8000, 125],
  '26': [261, 8000, 125],
  '27': [276, 8800, 145],
  '28': [182, 8000, 125],
  '29': [220, 8000, 125],
  '30': [200, 8000, 125],
  '31': [350, 7900, 125],
  '32': [310, 8000, 125],
  'party': [400, 7600, 125],
  'interface': [160, 44000, 125],
};

let audioReady = false;
let isPlaying = false;
// Whether the game WANTS music playing, as distinct from whether it actually
// is. They differ before the first gesture: BassoonTracker has its own
// AudioContext, which starts suspended, so a play() issued at stage load is
// accepted and silent. unlock() reconciles the two.
let wantPlaying = false;
// The two volume terms, kept apart because they change independently: the
// track's own per-stage gain (set by load()) and the player's setting (set
// once at boot). Multiplying them at the sink means neither overwrites the
// other -- applying the track gain directly would silently undo a user
// setting on every stage change.
let trackGain = 1;
let userVolume = 1;
// Third term, fixed: BassoonTracker's mixer is a lot hotter than ibxm's at the
// same per-stage gain, so `gain/300` straight out of loadstrack drowns the
// engine and crashes. This trims the whole music bus so that a full slider is
// the desktop game's balance rather than an unusable maximum. Set by ear; the
// per-stage gain still varies around it (0.4 to 1.33).
const MUSIC_TRIM = 0.33;
export let stageLoaded = false;

// Bumped by every load(). A load that finishes after a newer one started must
// not report itself as the current track -- otherwise a slow interface load
// resolving mid-race marks the RACE track loaded and the wrong music resumes.
let generation = 0;

let audioFailed = false;

// The second backend: a song imported in the Stage Maker (musicstore.js),
// streamed by an <audio> element instead of mixed by the tracker.
// ponytail: SONG_TRIM set by ear against the engine, like MUSIC_TRIM.
const SONG_TRIM = 0.6;
let backend = 'tracker';          // or 'audio' while `song` is the track
let song = null;

function dropSong() {
  if (!song) return;
  song.pause();
  URL.revokeObjectURL(song.src);
  song = null;
  backend = 'tracker';
}

function startSong(blob) {
  if (audioFailed) return false;              // ?music=0 silences songs too
  if (audioReady) { BassoonTracker.stop(); isPlaying = false; }
  song = new Audio(URL.createObjectURL(blob));
  song.loop = true;
  backend = 'audio';
  stageLoaded = true;
  applyVolume();
  if (wantPlaying) song.play().catch(() => { /* no gesture yet: unlock() retries */ });
  return true;
}

/**
 * True once the tracker's audio is up; false anywhere it cannot be.
 *
 * Deliberately NOT a `typeof window`/`typeof AudioContext` check: importing
 * the vendored player DEFINES both of those as globals, so feature-detecting
 * them reports a browser under node, and init then dies on a stub with no
 * createGain. Try it and believe the result instead -- and remember a failure,
 * so a headless run does not retry on every call.
 *
 * The context starts suspended under the autoplay policy; the same
 * first-keypress unlock web/audio.js installs resumes it.
 */
function getContext() {
  if (audioReady) return true;
  if (audioFailed) return false;
  try {
    BassoonTracker.init(true);
    if (BassoonTracker.audio && typeof BassoonTracker.audio.init === 'function') {
      BassoonTracker.audio.init();
    }
    hookMasterVolume();
    audioReady = true;
    return true;
  } catch (e) {
    audioFailed = true;
    return false;
  }
}

/**
 * Turn the soundtrack off outright, before anything initialises it.
 *
 * This is NOT `setVolume(0)`, and the difference is the entire point.
 * BassoonTracker mixes through a ScriptProcessorNode -- which runs on the MAIN
 * THREAD, and at 256 samples a callback, i.e. every ~5.8ms. Silencing it at
 * the gain node leaves every one of those callbacks running, so the CPU cost
 * is identical to full volume. Only refusing to init removes it, which is what
 * makes `?music=0` a usable A/B against the frame-rate dips.
 *
 * Reuses the `audioFailed` latch rather than adding a second flag: every entry
 * point already routes through getContext(), so there is one gate to keep
 * correct instead of two.
 */
export function disable() {
  if (audioReady) stop();
  audioFailed = true;
  audioReady = false;
}

/** The track key for a custom stage's soundtrack(name, ...) line. */
export const customTrack = (name) => 'my:' + name;

/**
 * Load a track by name: a stage number, 'party', 'interface', or
 * customTrack(name) for a Stage Maker soundtrack in mystages/mymusic.
 *
 * Resolves to true if the module actually parsed and is the current track.
 * Never throws -- a missing zip or an undecodable module costs music and
 * nothing else, the same contract web/audio.js keeps for sound effects.
 */
export async function load(stage, trackvol = 200) {
  const mine = ++generation;
  stageLoaded = false;

  const key = String(stage);
  // loadstrack's `n < 0` branch: RadicalMod("mystages/mymusic/" + name +
  // ".zip", trackvol, 8000, 125) -- the stage's own trackvol is the gain.
  const custom = key.startsWith('my:');
  const trackName = custom ? key.slice(3)
    : (key === 'party' || key === 'interface') ? key : 'stage' + key;
  const [gain, , bpmflex] = custom ? [trackvol, 8000, 125] : stageConstants[key] || [200, 8000, 125];

  dropSong();
  // An imported song: audio files stream through <audio>; a .mod/.xm the
  // player imported goes to the tracker as raw bytes (no zip around it).
  let imported = null;
  if (custom) {
    const blob = await readSong(trackName);
    if (mine !== generation) return false;
    if (blob && !TRACKER.test(trackName)) {
      trackGain = gain / 300.0;
      return startSong(blob);
    }
    if (blob) imported = new Uint8Array(await blob.arrayBuffer());
  }

  if (!getContext()) return false;

  // The Java scales its mixed samples by gain/300 (IBXModSlayer.turnbytesNorm).
  //
  // `trackvol` is only the gain for a custom track (above): loadstrack
  // ignores it for stock stages, which use their own constant.
  //
  // `rate` is dropped. In the Java it set the mixer's sample rate, which
  // shifts pitch AND tempo together; BassoonTracker has no equivalent, and
  // faking it with playbackRate would detune the music.
  trackGain = gain / 300.0;
  applyVolume();

  try {
    let modBytes = imported;
    if (!modBytes) {
      const basePath = await detectFpath();
      const dir = custom ? 'mystages/mymusic/' : 'music/';
      const res = await fetch(`${basePath}${dir}${encodeURIComponent(trackName)}.zip`);
      if (!res.ok) throw new Error(`${res.status} fetching ${trackName}.zip`);
      const zipFiles = await parseZip(new Uint8Array(await res.arrayBuffer()));
      for (const [name, bytes] of zipFiles) {
        if (name.toLowerCase().endsWith('.mod')) { modBytes = bytes; break; }
      }
    }
    if (!modBytes) throw new Error(`no .mod inside ${trackName}.zip`);

    // Each music zip holds exactly one module and parseZip allocates per
    // entry, so the view spans its whole buffer; slice anyway rather than
    // depend on that.
    await BassoonTracker.processFile(modBytes.slice().buffer);

    // A load that started later has already superseded this one.
    if (mine !== generation) return false;

    if (typeof BassoonTracker.setBPM === 'function') BassoonTracker.setBPM(bpmflex);
    stageLoaded = true;
    return true;
  } catch (e) {
    if (mine === generation) stageLoaded = false;
    console.warn('music: failed to load', trackName, e);
    return false;
  }
}

export function play() {
  wantPlaying = true;
  if (backend === 'audio') { song.play().catch(() => {}); return; }
  if (!stageLoaded || !getContext()) return;
  BassoonTracker.play();
  isPlaying = true;
}

export function stop() {
  wantPlaying = false;
  if (backend === 'audio') { song.pause(); return; }
  if (!audioReady) return;          // nothing started, nothing to cut
  BassoonTracker.stop();
  isPlaying = false;
}

export function resume() {
  wantPlaying = true;
  if (backend === 'audio') { if (song.paused) song.play().catch(() => {}); return; }
  if (!stageLoaded || !getContext()) return;
  if (!isPlaying) {
    BassoonTracker.play();
    isPlaying = true;
  }
}

/**
 * Resume the tracker's AudioContext from a user gesture, and start the track
 * if one was asked for while it was suspended.
 *
 * BassoonTracker creates its OWN AudioContext, so web/audio.js's unlock()
 * resumes the sound-effects context and leaves this one suspended -- which
 * presents as "An AudioContext was prevented from starting automatically" and
 * silent music while the sound effects work fine. Wired to the same first
 * keypress; safe to call on every one.
 */
export function unlock() {
  if (backend === 'audio') { if (wantPlaying && song.paused) song.play().catch(() => {}); return; }
  if (!getContext()) return;
  const audio = BassoonTracker.audio;
  const ctx = audio && audio.context;
  if (ctx && ctx.state === 'suspended' && typeof ctx.resume === 'function') ctx.resume();
  if (audio && typeof audio.checkState === 'function') audio.checkState();
  // Ask the library whether it is actually playing rather than trusting our
  // own flag: play() was called while the context was suspended, so the flag
  // says yes whether or not the sequencer really started. Guarding on it also
  // avoids restarting a song that is already running.
  const reallyPlaying = typeof BassoonTracker.isPlaying === 'function'
    ? BassoonTracker.isPlaying()
    : isPlaying;
  if (wantPlaying && stageLoaded && !reallyPlaying) {
    BassoonTracker.play();
    isPlaying = true;
  }
}

/**
 * The music level has to be applied by scaling the tracker's own master-volume
 * writes, not by setting the gain node directly.
 *
 * `audio.setMasterVolume` drives the param with `setValueAtTime` +
 * `linearRampToValueAtTime`, and an AudioParam under automation ignores a
 * later `gain.value = v` — the write succeeds and is inaudible. The tracker
 * also calls `setMasterVolume(1)` on play and again for a module's
 * global-volume effect (MOD effect 0x10), so any level set beforehand is
 * overwritten regardless.
 *
 * Wrapping the setter covers both, and leaves a module's own volume
 * automation working relative to our level.
 */
let trackerLevel = 1;     // the last level the tracker asked for, unscaled
let rawSetMasterVolume = null;

function hookMasterVolume() {
  const audio = BassoonTracker.audio;
  if (!audio || typeof audio.setMasterVolume !== 'function' || rawSetMasterVolume) return;
  rawSetMasterVolume = audio.setMasterVolume.bind(audio);
  audio.setMasterVolume = (level, when) => {
    trackerLevel = level;
    rawSetMasterVolume(level * MUSIC_TRIM * trackGain * userVolume, when);
  };
}

/** Push the current trim x track-gain x user-volume product at the player. */
function applyVolume() {
  if (backend === 'audio') { song.volume = Math.min(1, SONG_TRIM * trackGain * userVolume); return; }
  if (!getContext() || !rawSetMasterVolume) return;
  rawSetMasterVolume(trackerLevel * MUSIC_TRIM * trackGain * userVolume);
}

/** The player's music level, 0..1. Survives track changes. */
export function setVolume(v) {
  userVolume = Math.max(0, Math.min(1, v));
  applyVolume();
}

export function unload() {
  stop();
  dropSong();
  stageLoaded = false;
  ++generation;                     // invalidate any in-flight load
}

/** Exposed for the tests; not part of the playback API. */
export const _stageConstants = stageConstants;
