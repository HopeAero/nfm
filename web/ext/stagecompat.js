// NFM2 stages (the base game's, and the Stage Maker's) raced in Extended.
//
// Gameplay stays Extended's; only the stage file is translated. The two games
// number stage objects differently -- a stage line's id is TRACK_NAMES[id - 10]
// in the base, Extended's model list index id + 29 (35 -> 68, sofframp) -- so
// every set()/chk()/fix() id is rewritten by object NAME:
//   - 38 ids name the same object in both;
//   - offhill, roll1-5, tree4, tree6 exist in Extended under other ids;
//   - 20 objects exist only in the base (palms, cacti, rocks, hills, launchpad,
//     speedramp, the aerial checkpoint, ...): their models are read from the
//     base's data/models.zip (untouched) and appended to Extended's list;
//   - thewall is appended too: Extended's own (index 64) would need id 35,
//     which set() reads as sofframp.
// A base model with <track> blocks but no tracks(N) line (the palms and cacti:
// `//tracks(4)`) has its blocks ignored by the base, so no collision; Extended's
// ContO reads them unconditionally and fails on arrays tracks() never made.
// Those blocks are dropped here, which is the base's behaviour.
// Base-only stage lines (texture, soundtrack, stagemaker, publish) are left in:
// Extended ignores what it does not know.

import { TRACK_NAMES } from '../GameSparker.js';
import { readText, readZip } from '../vfs.js';
import { ContO } from './ContO.js';

/** Extended's model list, in loadbase's order (GameSparker.java `as`). */
export const EXT_MODELS = [
  '2000tornados', 'formula7', 'canyenaro', 'lescrab', 'nimi', 'maxrevenge', 'leadoxide', 'koolkat', 'drifter',
  'policecops', 'mustang', 'king', 'audir8', 'masheen', 'radicalone', 'drmonster', 'newcar1', 'newcar2', 'newcar3',
  'newcar4', 'secretcar1', 'secretcar2', 'secretcar3', 'tornadoshark', 'formula72', 'wowcaninaro', 'lavitacrab',
  'nimi2', 'maxrevenge2', 'leadoxide2', 'koolkat2', 'drifterx', 'swordofjustice', 'highrider', 'elking',
  'mightyeight', 'masheen2', 'radicalone2', 'drmonstaa', 'road', 'froad', 'twister2', 'twister1', 'turn', 'offroad',
  'bumproad', 'offturn', 'nroad', 'nturn', 'roblend', 'noblend', 'rnblend', 'roadend', 'offroadend', 'hpground',
  'ramp30', 'cramp35', 'dramp15', 'dhilo15', 'slide10', 'takeoff', 'sramp22', 'offbump', 'offramp', 'thewall',
  'halfpipe', 'spikes', 'rail', 'sofframp', 'checkpoint', 'fixpoint', 'offcheckpoint', 'sideoff', 'bsideoff',
  'uprise', 'riseroad', 'sroad', 'soffroad', '2000tornadosB', 'formula7B', 'canyenaroB', 'lescrabB', 'nimiB',
  'maxrevengeB', 'leadoxideB', 'koolkatB', 'drifterB', 'policecopsB', 'mustangB', 'kingB', 'audir8B', 'masheenB',
  'radicaloneB', 'drmonsterB', 'newcar1B', 'newcar2B', 'newcar3B', 'newcar4B', 'secretcar1B', 'secretcar2B',
  'secretcar3B', 'tornadosharkB', 'formula72B', 'wowcaninaroB', 'lavitacrabB', 'nimi2B', 'maxrevenge2B',
  'leadoxide2B', 'koolkat2B', 'drifterxB', 'swordofjusticeB', 'highriderB', 'elkingB', 'mightyeightB', 'masheen2B',
  'radicalone2B', 'drmonstaaB', 'tree4', 'tree6', 'offhill', 'spikefire', 'railfire', 'cactus', 'roll1', 'roll2',
  'roll3', 'roll4', 'roll5', 'roll6',
];

/** Base objects appended to Extended's list, with the model code ContO gets
 *  (it decides sfactor and isacar): Extended's own for thewall, the new index
 *  for the rest (>= 129: a stage piece). */
const REUSE_UNREACHABLE = { thewall: 64 };
export const APPENDED = [
  ...TRACK_NAMES.filter((n) => !EXT_MODELS.includes(n)),
  ...Object.keys(REUSE_UNREACHABLE),
];

/** Where each base object lives in Extended's (grown) model list. */
export function extIndex(name) {
  const a = APPENDED.indexOf(name);
  if (a >= 0) return EXT_MODELS.length + a;
  return EXT_MODELS.indexOf(name);
}

/**
 * Rewrites a base stage's object ids for Extended. `grat(index)` is the model's
 * ground offset: the base's aerial checkpoint (chk on model 110) takes its y
 * raw, Extended's chkfloat subtracts grat, so it is added back.
 */
export function translateStage(text, grat) {
  return text.split(/\r\n|\r|\n/).map((line) => {
    const m = /^(\s*)(set|chk|fix)\((-?\d+)(,.*)$/.exec(line);
    if (!m) return line;
    const [, pad, cmd, id, rest] = m;
    const name = TRACK_NAMES[+id - 10];
    if (!name) return line;
    const idx = extIndex(name);
    const eid = idx - 29;   // Extended: model = id + 29 (sofframp -> 39: set() also reads 35 as it, chk/fix do not)
    if (cmd === 'chk' && name === 'aircheckpoint') {
      const a = /^,(-?\d+),(-?\d+),(-?\d+),(-?\d+)(.*)$/.exec(rest);
      if (a) return `${pad}chkfloat(${eid},${a[1]},${a[2]},${a[3]},${+a[4] + grat(idx)}${a[5]}`;
    }
    return `${pad}${cmd}(${eid}${rest}`;
  }).join('\n');
}

/**
 * What a base stage needs before the game runs (loadbase and loadstage are
 * synchronous): its text -- `?nfm2stage=N` (stages/N.txt) or `?mystage=NAME`
 * (the Stage Maker's store or mystages/) -- and the base's models archive.
 * null when neither parameter is given.
 */
export async function prepareBaseStage(params) {
  let name = null, text;
  if (params.get('mystage')) {
    const { readStage } = await import('../stagestore.js');
    name = params.get('mystage');
    text = await readStage(name);
  } else if (params.get('nfm2stage')) {
    text = await readText(`stages/${+params.get('nfm2stage')}.txt`);
  } else {
    return null;
  }
  if (!text) throw new Error(`stagecompat: no stage ${name ?? params.get('nfm2stage')}`);
  return { name, text, zip: await readZip('data/models.zip') };
}

/** Puts the base's models for APPENDED into Extended's model array (after loadbase). */
export function appendModels(aconto, zip, medium, trackers, xtgraphics) {
  for (const name of APPENDED) {
    const bytes = zip.get(`${name}.rad`);
    if (!bytes) throw new Error(`stagecompat: data/models.zip has no ${name}.rad`);
    const idx = extIndex(name);
    // newstone (base: no outline) is Extended's stonecold
    let src = Array.from(bytes, (b) => String.fromCharCode(b)).join('').replace(/^(\s*)newstone\(\)/m, '$1stonecold()');
    if (!/^\s*tracks\(/m.test(src)) src = src.replace(/<track>[\s\S]*?<\/track>/g, '');
    const buf = Int8Array.from(src, (c) => c.charCodeAt(0));
    aconto[idx] = new ContO(0, buf, medium, trackers, xtgraphics, REUSE_UNREACHABLE[name] ?? idx);
  }
}
