// The stage maker's scenery and soundtrack: StageMaker's Atmosphere, Colors,
// Scenery and Sound Track tabs, as HTML controls over the stage's head lines.
//
// Every control reads and writes a head line in the format StageMaker.savefile
// writes it (StageMaker.java:5538), with the applet's ranges:
//   sky / fog / ground (r,g,b)            Atmosphere: three colour pickers
//   clouds(r,g,b,coverage,height)         coverage 0..10, height -500..-1500
//   texture(r,g,b,amount)                 amount 20..60
//   snap(r,g,b)                           the RGB mask, -60..60 a channel; the
//                                         sliders' sum is capped at 200, and at
//                                         110 or less the stage is dark enough
//                                         that the applet turns on lightson()
//   density(d), fadefrom(f)               Dust/Fog: 3..8 and 5000..8000
//   mountains(seed)                       any seed; "New" rolls one
//   soundtrack(name,vol,sizeKB)           a module in mystages/mymusic
//
// Only the line a control changed is rewritten, so a stage typed by hand or
// saved by the desktop editor keeps every other line as it was.

import * as music from '../music.js';
import { listSongs, readSong, importSong, deleteSong, ACCEPT } from '../musicstore.js';
import { tr } from '../i18n.js';
import { detectFpath } from '../vfs.js';
import { trunc, random } from '../java.js';

// ponytail: the folder listing, hardcoded (a static host cannot list a
// directory). Add a manifest if tracks can ever be added from the page.
export const MYMUSIC = [
  'airy.mod', 'ghost_me.mod', 'inner_feelings.mod', 'inner_planet.mod',
  'jellybean_-_mean_machine.mod', 'jensz.mod', 'kyrom-malysty.mod',
  'logical_move_4-01.mod', 'lolander.mod', 'midnight_run.mod',
  'noggins_nokkin_stik.mod', 'puro_acid_5.mod', 'purple_dream_5.mod',
  'pyrox_1_1.mod', 'pythons_loony_too.mod', 'q-bic.mod', 'quickn_easy.mod',
  'uaahhversion1.mod', 'uncle_human.mod',
];

// ---- head lines ----------------------------------------------------------------

/** The values of `key(...)`, as strings, or null when the stage has no such line. */
export function getLine(head, key) {
  const l = head.find((s) => s.startsWith(key + '('));
  if (!l) return null;
  return l.slice(key.length + 1, l.lastIndexOf(')')).split(',');
}

/**
 * Set `key(...)` in place, or add it where savefile writes it: soundtrack and
 * lightson after nlaps, everything else before it. `vals` null removes it.
 */
export function setLine(head, key, vals) {
  const i = head.findIndex((s) => s.startsWith(key + '('));
  if (vals === null) { if (i !== -1) head.splice(i, 1); return; }
  const line = `${key}(${vals.join(',')})`;
  if (i !== -1) { head[i] = line; return; }
  const laps = head.findIndex((s) => s.startsWith('nlaps('));
  if (laps === -1) head.push(line);
  else head.splice(key === 'soundtrack' || key === 'lightson' ? laps + 1 : laps, 0, line);
}

const nums = (head, key, dflt) => {
  const v = getLine(head, key);
  return dflt.map((d, i) => (v && v[i] !== undefined && v[i] !== '' ? parseInt(v[i], 10) || 0 : d));
};
const clamp = (v, a, b) => Math.max(a, Math.min(b, v));
const hex = (r, g, b) => '#' + [r, g, b].map((c) => clamp(c, 0, 255).toString(16).padStart(2, '0')).join('');
const rgb = (h) => [1, 3, 5].map((i) => parseInt(h.slice(i, i + 2), 16));

// The applet's RGB mask slider for a channel (StageMaker.java:3161), and the
// lights rule it drives: dark enough (sum <= 110) turns the car lights on.
const slider = (s) => trunc(s / 1.2 + 50.0);
export const lightsFor = (snap) => snap.reduce((a, s) => a + slider(s), 0) <= 110;

/** Cap the mask as the applet does: the other channels give way until the sliders sum to 200. */
export function capSnap(snap, moved) {
  const s = snap.slice();
  for (let guard = 0; s.reduce((a, v) => a + slider(v), 0) > 200 && guard < 400; ++guard) {
    for (let k = 0; k < 3; ++k) if (k !== moved && s[k] > -60) s[k] -= 1;
  }
  return s;
}

// ---- the panel -----------------------------------------------------------------

const DEFAULTS = {
  sky: [191, 215, 255], fog: [195, 207, 230], ground: [192, 194, 202],
  clouds: [255, 255, 255, 5, -1000], texture: [0, 0, 0, 50], snap: [0, 0, 0],
  density: [5], fadefrom: [5000],
};

/**
 * Build the controls into `root`. `getHead()` is the live head array (the
 * editor replaces it on load/undo); `changed()` is the editor's commit, which
 * records undo, rewrites the source and redraws both views.
 * Returns paint(), to call whenever the head was replaced.
 */
export function mountScenery(root, getHead, changed) {
  root.innerHTML = `
    <div class="bar">
      <label class="field">Sky <input type="color" data-c="sky"></label>
      <label class="field">Dust / Fog <input type="color" data-c="fog"></label>
      <label class="field">Ground <input type="color" data-c="ground"></label>
    </div>
    <div class="bar">
      <label class="field">Clouds <input type="color" data-c="clouds"></label>
      <label class="field">Coverage <input type="range" min="0" max="10" data-k="clouds" data-i="3"></label>
      <label class="field">Height <input type="range" min="500" max="1500" step="50" data-k="clouds" data-i="4" data-neg></label>
    </div>
    <div class="bar">
      <label class="field">Ground Texture <input type="color" data-c="texture"></label>
      <label class="field">Amount <input type="range" min="20" max="60" data-k="texture" data-i="3"></label>
    </div>
    <div class="bar">
      <span class="field">Atmosphere RGB Mask</span>
      <label class="field">R <input type="range" min="-60" max="60" data-k="snap" data-i="0"></label>
      <label class="field">G <input type="range" min="-60" max="60" data-k="snap" data-i="1"></label>
      <label class="field">B <input type="range" min="-60" max="60" data-k="snap" data-i="2"></label>
      <span class="field">Car Lights : <b data-lights></b></span>
    </div>
    <div class="bar">
      <span class="field">Dust/Fog Properties</span>
      <label class="field">Density <input type="range" min="3" max="8" data-k="density" data-i="0"></label>
      <label class="field">Near / Far <input type="range" min="5000" max="8000" step="30" data-k="fadefrom" data-i="0"></label>
    </div>
    <div class="bar">
      <label class="field">Mountains <input type="number" min="0" max="99999" data-k="mountains" data-i="0" style="width:6.5em"></label>
      <button type="button" data-newmount>New mountains</button>
    </div>
    <p class="hint">The mountains on the horizon are not drawn by hand: this number is the seed the game builds the whole range from. The same number always gives the same mountains; "New mountains" rolls another range. Turn on the 3D view to see them.</p>
    <div class="bar">
      <label class="field">Sound Track <select data-track></select></label>
      <label class="field">Volume <input type="range" min="50" max="300" step="10" data-vol></label>
      <button type="button" data-listen>Listen</button>
    </div>
    <div class="bar">
      <button type="button" data-import>Import song…</button>
      <button type="button" data-delsong hidden>Delete this song</button>
      <input type="file" data-file accept="${ACCEPT}" hidden>
    </div>
    <p class="hint">Import any MP3, OGG, M4A, WAV (or a .mod / .xm tracker module): it is kept in this browser and plays in the race. An imported song only plays here; the desktop game races that stage in silence.</p>`;
  const $$ = (sel) => [...root.querySelectorAll(sel)];
  const track = root.querySelector('[data-track]');
  const vol = root.querySelector('[data-vol]');
  const listen = root.querySelector('[data-listen]');
  const fileIn = root.querySelector('[data-file]');
  const delSong = root.querySelector('[data-delsong]');
  let mine = [];                    // the imported songs' names
  // Built-in names are constants; imported ones are the player's own file
  // names, so those are added as text, never as markup.
  const fillTracks = async () => {
    mine = await listSongs();
    track.innerHTML = '<option value="">(none)</option>'
      + '<optgroup></optgroup><optgroup></optgroup>';
    const [built, own] = track.querySelectorAll('optgroup');
    built.label = tr('Stage Maker');
    own.label = tr('My songs');
    for (const n of MYMUSIC) built.append(new Option(n, n));
    for (const n of mine) own.append(new Option(n, n));
    own.hidden = !mine.length;
  };

  const read = (key) => nums(getHead(), key, DEFAULTS[key] || [0]);

  function paint() {
    const head = getHead();
    for (const el of $$('[data-c]')) el.value = hex(...read(el.dataset.c));
    for (const el of $$('[data-k]')) {
      const v = key0(el);
      el.value = el.hasAttribute('data-neg') ? -v : v;
    }
    root.querySelector('[data-lights]').textContent = head.some((l) => l.startsWith('lightson(')) ? 'On' : 'Off';
    const st = getLine(head, 'soundtrack');
    track.value = st && (MYMUSIC.includes(st[0]) || mine.includes(st[0])) ? st[0] : '';
    vol.value = st ? clamp(parseInt(st[1], 10) || 200, 50, 300) : 200;
    vol.disabled = !track.value;
    listen.disabled = !track.value;
    delSong.hidden = !mine.includes(track.value);
  }
  const key0 = (el) => {
    if (el.dataset.k === 'mountains') return nums(getHead(), 'mountains', [0])[0];
    return read(el.dataset.k)[+el.dataset.i];
  };

  for (const el of $$('[data-c]')) {
    el.addEventListener('change', () => {
      const k = el.dataset.c;
      const v = read(k);
      v.splice(0, 3, ...rgb(el.value));
      setLine(getHead(), k, v);
      changed();
    });
  }
  for (const el of $$('[data-k]')) {
    el.addEventListener('change', () => {
      const k = el.dataset.k;
      let n = parseInt(el.value, 10) || 0;
      if (el.hasAttribute('data-neg')) n = -n;
      let v = k === 'mountains' ? [clamp(n, 0, 99999)] : read(k);
      if (k !== 'mountains') v[+el.dataset.i] = n;
      if (k === 'snap') {
        v = capSnap(v, +el.dataset.i);
        setLine(getHead(), 'lightson', lightsFor(v) ? [] : null);
      }
      setLine(getHead(), k, v);
      changed();
    });
  }
  root.querySelector('[data-newmount]').onclick = () => {
    setLine(getHead(), 'mountains', [trunc(random() * 100000.0)]);
    changed();
  };

  // The sound track: the name, the volume (the applet derives it from the
  // module's loudness, 220 / (rvol / 3750); BassoonTracker reports no such
  // figure, so here it is a slider) and the zip's size in KB, which the game
  // only uses for its loading bar.
  const writeTrack = async () => {
    const name = track.value;
    if (!name) { setLine(getHead(), 'soundtrack', null); changed(); paint(); return; }
    let kb = 0;
    try {
      const own = await readSong(name);
      if (own) kb = trunc(own.size / 1024);
      else {
        const res = await fetch(`${await detectFpath()}mystages/mymusic/${encodeURIComponent(name)}.zip`, { method: 'HEAD' });
        kb = trunc((parseInt(res.headers.get('content-length'), 10) || 0) / 1024);
      }
    } catch { /* size is cosmetic */ }
    setLine(getHead(), 'soundtrack', [name, vol.value, kb]);
    changed();
    paint();                          // the volume / delete controls follow the pick
  };
  track.onchange = () => { stopListening(); writeTrack(); };
  root.querySelector('[data-import]').onclick = () => fileIn.click();
  fileIn.onchange = async () => {
    const f = fileIn.files[0];
    fileIn.value = '';
    if (!f) return;
    stopListening();
    const name = await importSong(f);
    await fillTracks();
    track.value = name;
    await writeTrack();
  };
  delSong.onclick = async () => {
    const name = track.value;
    if (!mine.includes(name)) return;
    stopListening();
    await deleteSong(name);
    await fillTracks();
    track.value = '';
    await writeTrack();
  };
  vol.onchange = writeTrack;

  let playing = false;
  const stopListening = () => {
    if (playing) music.stop();
    playing = false;
    listen.textContent = 'Listen';
  };
  listen.onclick = async () => {
    if (playing) { stopListening(); return; }
    listen.textContent = 'Stop';
    playing = true;
    const ok = await music.load(music.customTrack(track.value), parseInt(vol.value, 10));
    if (ok && playing) music.play();
    else stopListening();
  };

  paint();
  fillTracks().then(paint);
  return { paint, stopListening };
}
