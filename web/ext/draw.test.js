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
