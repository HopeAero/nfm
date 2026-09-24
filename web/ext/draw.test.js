// Every Extended model drawn by the transpiled ContO.d, call by call, against
// the jar's (draw.expected.json.gz, from web/tools/ExtDrawProbe.java).
//
// The recorder takes the calls the generated code makes -- setColor(r,g,b)
// for `new Color(r, g, b)`, setColorOf(color) for any other Color -- and
// writes them in RecG's format. new Color() range-checks in Java; so does
// this, so an out-of-range colour fails here as it would throw in the jar.

import { test } from 'node:test';
import assert from 'node:assert';
import fs from 'node:fs';
import zlib from 'node:zlib';
import { parseRadq } from './radq.js';
import { ContO } from './ContO.js';
import { Medium } from './Medium.js';
import { Trackers } from './Trackers.js';
import { setSeed } from '../java.js';
import { modelCode } from './models.js';

const SEED = 12345;
const POSES = [[0, 100, 1500, 0, 0, 0], [200, 50, 2200, 45, 10, 0], [-300, 150, 3000, 135, 0, 20], [0, 250, 9000, 270, 0, 0]];
const HERE = new URL('./', import.meta.url);
const expected = JSON.parse(zlib.gunzipSync(fs.readFileSync(new URL('draw.expected.json.gz', HERE)))).draws;
const models = await parseRadq(new Uint8Array(fs.readFileSync(new URL('../../ext/data/models.radq', HERE))));

class Recorder {
  constructor() { this.ops = []; }
  #rgb(r, g, b, a = 255) {
    for (const v of [r, g, b, a]) if (!(v >= 0 && v <= 255) || v !== Math.trunc(v)) throw new Error(`IllegalArgumentException: Color parameter outside of expected range: ${[r, g, b, a]}`);
    this.ops.push(['c', r, g, b, a]);
  }
  setColor(r, g, b) { this.#rgb(r, g, b); }
  setColorOf(c) { this.#rgb(c.r, c.g, c.b, c.a); }
  fillPolygon(x, y, n) { this.ops.push(['f', Array.from(x.slice(0, n)), Array.from(y.slice(0, n))]); }
  drawPolygon(x, y, n) { this.ops.push(['d', Array.from(x.slice(0, n)), Array.from(y.slice(0, n))]); }
  fillRect(x, y, w, h) { this.ops.push(['r', x, y, w, h]); }
}

test(`ContO.d: ${expected.length} draws of the Extended models match the jar call for call`, () => {
  const m = new Medium();
  const t = new Trackers();
  const base = new Map();
  const bad = [];
  let calls = 0;
  for (const e of expected) {
    if (!base.has(e.name)) {
      setSeed(SEED);
      const code = modelCode(e.name);
      base.set(e.name, new ContO(0, models.get(e.name), m, t, null, code));
    }
    const [x, y, z, xz, xy, zy] = POSES[e.pose];
    setSeed(SEED);
    const c = new ContO(1, base.get(e.name), x, y, z, xz);
    c.xy = xy; c.zy = zy;
    const g = new Recorder();
    let err = '';
    try { c.d(g); } catch (ex) { err = String(ex.message); }
    calls += g.ops.length;
    if ((e.error !== '') !== (err !== '')) { bad.push(`${e.name} pose ${e.pose}: jar error "${e.error}" vs port "${err}"`); continue; }
    for (let i = 0; i < Math.max(e.ops.length, g.ops.length); i++) {
      if (JSON.stringify(e.ops[i]) !== JSON.stringify(g.ops[i])) {
        bad.push(`${e.name} pose ${e.pose} op ${i}/${e.ops.length}: jar ${JSON.stringify(e.ops[i])?.slice(0, 120)} vs port ${JSON.stringify(g.ops[i])?.slice(0, 120)}`);
        break;
      }
    }
  }
  assert.ok(calls > 100000, `only ${calls} calls recorded`);
  assert.deepStrictEqual(bad, [], `${bad.length} draws differ; first: ${bad[0]}`);
});

// ---- effects: the same setup strings as ExtDrawProbe.EFFECTS ----------------
import { createHash } from 'node:crypto';

const EFFECTS = [
  'call:setfire',
  'c.teleported=true;c.telefade=128',
  'c.invisiblepiece=100',
  'c.glowlines=true;c.glowcustom=true;c.glowcolour=[255,40,40];c.playerglow=true',
  'c.spatk=true;c.freeze=true',
  'c.weaken=true;c.leech=true;c.strswap=true',
  'c.shadowcar=true;c.greenflame=true;c.flameheight=3',
  'c.lightup=true;c.outoftrack=true;c.floorguardian=true;c.dmgcolours=[200,60,60];c.spec=[90,20,160]',
  'c.weakstage=2',
  'c.elec=true',
  'c.fix=true',
  'm.showsnow=true;m.snowno=40;m.snowheight=300',
  'm.effect[10]=true',
  'm.effect[2]=true',
  'm.effect[9]=true',
  'call:teleflash',
  'call:drawsun',
];
const FRAMES = 6;
const effects = JSON.parse(zlib.gunzipSync(fs.readFileSync(new URL('draw.expected.json.gz', HERE)))).effects;

const value = (v) => v === 'true' ? true : v === 'false' ? false : v.startsWith('[') ? Int32Array.from(JSON.parse(v)) : Number(v);

/** Apply a setup; returns the ContO methods to call after each d(g). */
function apply(setup, c, m) {
  const calls = [];
  for (const part of setup.split(';')) {
    if (part.startsWith('call:')) {
      const f = c[part.slice(5)];
      if (f.length > 0) calls.push(f); else f.call(c);   // takes a Graphics: every frame
      continue;
    }
    const [k, v] = part.split('=');
    const target = k.startsWith('c.') ? c : m;
    const ix = k.slice(2).match(/^(\w+)\[(\d+)\]$/);
    if (ix) target[ix[1]][+ix[2]] = value(v); else target[k.slice(2)] = value(v);
  }
  return calls;
}

test(`ContO.d effects: ${effects.length} animated draws (fire, teleport, glow, snow, ...) match the jar`, () => {
  // All 129, in zip order, into one new Trackers: each ContO appends its
  // collision boxes to it and the shadow reads them (as ExtDrawProbe does).
  const m0 = new Medium();
  const t = new Trackers();
  const bases = new Map();
  for (const [name, bytes] of models) {
    setSeed(SEED);
    bases.set(name, new ContO(0, bytes, m0, t, null, modelCode(name)));
  }
  const bad = [];
  let alpha = 0;
  for (const e of effects) {
    const m = new Medium();
    setSeed(SEED);
    const c = new ContO(1, bases.get(e.name), 0, 100, 1500, 30);
    c.m = m;
    const g = new Recorder();
    let err = '';
    try {
      const calls = apply(EFFECTS[e.effect], c, m);
      for (let f = 0; f < FRAMES; f++) { c.d(g); for (const call of calls) call.call(c, g); }
    } catch (ex) { err = String(ex.message); }
    alpha += g.ops.filter((o) => o[0] === 'c' && o[4] !== 255).length;
    const sha = createHash('sha1').update(JSON.stringify(g.ops)).digest('hex');
    if ((e.error !== '') !== (err !== '')) bad.push(`${e.name} ${EFFECTS[e.effect]}: jar error "${e.error}" vs port "${err}"`);
    else if (g.ops.length !== e.n || sha !== e.sha1) bad.push(`${e.name} ${EFFECTS[e.effect]}: ${g.ops.length} ops vs jar ${e.n}${g.ops.length === e.n ? ' (same count, different content)' : ''}`);
  }
  assert.ok(alpha > 100000, `only ${alpha} translucent colours drawn`);
  assert.deepStrictEqual(bad, [], `${bad.length} effect draws differ; first: ${bad[0]}`);
});
