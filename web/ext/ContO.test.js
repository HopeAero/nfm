// Every Extended model, built by the transpiled ContO, against the jar's own.
//
// contO.expected.json.gz is web/tools/ExtContOProbe.java's dump: each model as
// madness.jar's ContO constructs it, every instance field of the ContO and of
// its Planes. Numbers compare exactly (floats as the doubles they widen to).
// Regenerate the expectation only from the jar, never from this port.

import { test } from 'node:test';
import assert from 'node:assert';
import fs from 'node:fs';
import zlib from 'node:zlib';
import { parseRadq } from './radq.js';
import { ContO } from './ContO.js';
import { Medium } from './Medium.js';
import { Trackers } from './Trackers.js';
import { setSeed } from '../java.js';

const SEED = 12345;   // = ExtContOProbe.SEED; the probe reseeds before every model too

const HERE = new URL('./', import.meta.url);
const expected = JSON.parse(zlib.gunzipSync(fs.readFileSync(new URL('contO.expected.json.gz', HERE))));
const models = await parseRadq(new Uint8Array(fs.readFileSync(new URL('../../ext/data/models.radq', HERE))));

/** First path where the port's object differs from the jar's dump, or null. */
function diff(exp, got, path) {
  if (typeof exp === 'string') {
    if (exp.startsWith('<')) return null;                 // a shared reference (Medium, Trackers)
    if (exp === 'NaN') return Number.isNaN(got) ? null : `${path}: NaN vs ${got}`;
    return exp === got ? null : `${path}: ${JSON.stringify(exp)} vs ${JSON.stringify(got)}`;
  }
  if (exp === null) return got === null || got === undefined ? null : `${path}: null vs ${typeof got}`;
  if (Array.isArray(exp)) {
    if (!got || typeof got.length !== 'number') return `${path}: array vs ${got}`;
    if (got.length !== exp.length) return `${path}.length: ${exp.length} vs ${got.length}`;
    for (let i = 0; i < exp.length; i++) { const d = diff(exp[i], got[i], `${path}[${i}]`); if (d) return d; }
    return null;
  }
  if (typeof exp === 'object') {
    if (!got || typeof got !== 'object') return `${path}: object vs ${got}`;
    for (const k of Object.keys(exp)) {
      if (!(k in got)) return `${path}.${k}: missing in port`;
      const d = diff(exp[k], got[k], `${path}.${k}`);
      if (d) return d;
    }
    return null;
  }
  if (typeof exp === 'boolean') return exp === got ? null : `${path}: ${exp} vs ${got}`;
  return exp === got ? null : `${path}: ${exp} vs ${got}`;
}

test(`ContO: all ${expected.models.length} Extended models load exactly as the jar loads them`, () => {
  const m = new Medium();
  const t = new Trackers();
  const bad = [];
  for (const e of expected.models) {
    setSeed(SEED);
    const c = new ContO(0, models.get(e.name), m, t, null, e.code);
    const d = diff(e.state, c, e.name);
    if (d) bad.push(d);
  }
  assert.deepStrictEqual(bad, [], `${bad.length} models differ; first: ${bad[0]}`);
});
