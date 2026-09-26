// NFM2 stages (the base game's, and the Stage Maker's) raced in Extended.
//
// Gameplay stays Extended's; the stage and its pieces are the base game's. All
// 68 base stage models are read from the base's data/models.zip (untouched)
// and appended after Extended's own 129 models, and every set()/chk()/fix() id
// is rewritten to its base model there -- not to Extended's model of the same
// name: Extended reshapes some (its giant trees, its checkpoint), and 20 base
// pieces it does not have at all (palms, cacti, rocks, hills, launchpad, the
// aerial checkpoint, ...). The two games number ids differently anyway: base
// TRACK_NAMES[id - 10], Extended model id + 29 (set()'s 35 -> 68); appended
// ids start at 100, clear of that 35.
// A base model with <track> blocks but no tracks(N) line (the palms and cacti:
// `//tracks(4)`) has its blocks ignored by the base, so no collision; Extended's
// ContO reads them unconditionally and fails on arrays tracks() never made.
// Those blocks are dropped here, which is the base's behaviour.
// The base's texture(r,g,b,k) tints the ground patches (Medium.newpolys) as
// (ground*k + texture)/(1+k); Extended has no texture() and reads the patch
// colour from polys(r,g,b) instead, else its whitish default 215,210,210 -- the
// white blotches on NFM2 stages. So texture() becomes that polys().
// Other base-only lines (soundtrack, stagemaker, publish) are left in:
// Extended ignores what it does not know.

import { TRACK_NAMES } from '../GameSparker.js';
import { readText, readZip } from '../vfs.js';
import { ContO } from './ContO.js';
import { Medium as BaseMedium } from '../Medium.js';

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

/** The base stage models, appended in TRACK_NAMES order after EXT_MODELS. */
export const APPENDED = TRACK_NAMES;

/** Where each base object lives in Extended's (grown) model list. */
export function extIndex(name) {
  return EXT_MODELS.length + TRACK_NAMES.indexOf(name);
}

/**
 * Rewrites a base stage's object ids for Extended. `grat(index)` is the model's
 * ground offset: the base's aerial checkpoint (chk on model 110) takes its y
 * raw, Extended's chkfloat subtracts grat, so it is added back.
 */
export function translateStage(text, grat) {
  const lines = text.split(/\r\n|\r|\n/);
  const nums = (cmd) => {
    const l = lines.find((x) => x.trim().startsWith(`${cmd}(`));
    return l && l.slice(l.indexOf('(') + 1, l.indexOf(')')).split(',').map((v) => parseInt(v, 10));
  };
  const ground = nums('ground'), texture = nums('texture');
  const [tr, tg, tb, tk] = texture || [0, 0, 0, 50];   // the base Medium's default texture
  const k = Math.max(20, Math.min(60, tk));
  const polys = ground && `polys(${[tr, tg, tb].map((t, i) => Math.trunc((ground[i] * k + t) / (1 + k))).join(',')})`;
  return lines.map((line) => {
    if (polys && line.trim().startsWith(texture ? 'texture(' : 'ground(')) return texture ? polys : `${line}\n${polys}`;
    // the base reads density(n) as fog 2n+1, 1..30 (web/GameSparker.js); Extended takes n as it is
    const fog = /^(\s*)density\((-?\d+)\)/.exec(line);
    if (fog) return `${fog[1]}density(${Math.min(30, Math.max(1, 2 * +fog[2] + 1))})`;
    const m = /^(\s*)(set|chk|fix)\((-?\d+)(,.*)$/.exec(line);
    if (!m) return line;
    const [, pad, cmd, id, rest] = m;
    const name = TRACK_NAMES[+id - 10];
    if (!name) return line;
    const idx = extIndex(name);
    const eid = idx - 29;   // Extended: model = id + 29
    if (cmd === 'chk' && name === 'aircheckpoint') {
      const a = /^,(-?\d+),(-?\d+),(-?\d+),(-?\d+)(.*)$/.exec(rest);
      if (a) return `${pad}chkfloat(${eid},${a[1]},${a[2]},${a[3]},${+a[4] + grat(idx)}${a[5]}`;
    }
    return `${pad}${cmd}(${eid}${rest}`;
  }).join('\n');
}

/**
 * Extended's own stages on the OLD model list (tracks.radq, the jar's normal
 * mode, which its menu marks UNAVAILABLE!): 24 of the 27 were made when four
 * more models came before `road`, so every id is 4 too high -- checkpoint 44,
 * fixpoint 45 -- and the pieces load as their neighbours. The jar does not
 * renumber them. A checkpoint on 44 marks such a stage; stages 14, 16 and 27
 * are already on the current list.
 */
export function renumberOldStage(text) {
  if (!/^\s*chk\w*\(44,/m.test(text)) return text;
  return text.replace(/^(\s*(?:set\w*|chk\w*|fix|teleset)\()(\d+),/gm, (m, head, id) => `${head}${+id - 4},`);
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

/**
 * The ground patches (the "mounds") of an NFM 2 stage as the base draws them:
 * web/Medium.js's own newpolys/groundpolys run on Extended's Medium, whose
 * fields they share by name -- seeded patches 2-4x bigger, a halo pass, 4800
 * past the walls, a 25x25 window to fade[2]. Extended's make smaller unseeded
 * ones in a 15x15 window. Off (the prototype's again) for any other stage.
 * ponytail: the seed takes gs.nob for the base's notb, so the pattern is the
 * base's kind, not its exact layout; and the base's ys() clamps to cz where
 * Extended's clamps to 10, which only moves a vertex already behind the eye.
 */
export function baseGround(medium, notb) {
  medium.baseLook = !!notb;   // ContO's pile() reads it: the base's hill shading
  if (!notb) {
    delete medium.newpolys; delete medium.groundpolys;
    // the base's newpolys sized cgpx/cgpz/ogpx/ogpz to its stage; Extended's writes up to its
    // own 200000 cells, and past the end of the base's cgpz it read undefined, i32(NaN) is 0,
    // and its walk out of a tracker never ended: the stage select froze on the next
    // Extended stage after an NFM 2 one
    if (medium.extGround) { Object.assign(medium, medium.extGround); medium.extGround = null; }
    return;
  }
  const { cgpx, cgpz, ogpx, ogpz } = medium;
  medium.extGround ??= { cgpx, cgpz, ogpx, ogpz };
  medium.newpolys = (a, b, c, d, t) => BaseMedium.prototype.newpolys.call(medium, a, b, c, d, t, notb());
  medium.groundpolys = BaseMedium.prototype.groundpolys;
}

/**
 * An NFM 2 stage's pieces (aconto[from..to), after loadstage) drawn as the base
 * port draws them: its ContO doubles every model's disline (web/ContO.js), so
 * road, trees and ramps reach fade[8] and on, not fade[4]; its Plane culls
 * (Plane.basecull); and its checkpoints flicker on every stage but 1 and 11
 * (Extended's rule is "all but 1", and these run as the classic twin or 1).
 */
export function baseLook(aconto, from, to, medium, stage) {
  for (let i = from; i < to; i++) {
    const o = aconto[i];
    o.disline = Math.min(15, o.disline * 2);   // fade[] has 16 steps
    for (let j = 0; j < o.npl; j++) { o.p[j].disline = o.disline; o.p[j].basecull = true; }
  }
  medium.nochekflk = !(stage === 1 || stage === 11);
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
    aconto[idx] = new ContO(0, buf, medium, trackers, xtgraphics, idx);   // code >= 129: a stage piece
  }
}
