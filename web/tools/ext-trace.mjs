// Replay captured jar calls against the transpiled Extended port.
//
//   node --max-old-space-size=8000 web/tools/ext-trace.mjs <trace.jsonl> [--fixture out.json.gz]
//
// Each line of a trace (DiffRun -Ddiffrun.trace=Class.method, det.Det.enter/
// exit) is one sampled call: the object graph reachable from `this` and the
// arguments, before and after, ids shared between the two, plus the random
// and clock state. For each:
//   1. rebuild "before" with web/ext's classes (Object.create(Class.prototype)),
//      every object behind a Proxy that records what the port touches;
//   2. setSeed(xs), System.now = nanos, run the port's method;
//   3. walk the jar's "after" from the roots alongside the port's objects and
//      compare every field, keeping identities (a jar object seen twice must
//      be the same port object).
// --fixture keeps only the objects the port touched or the jar changed: a
// small regression test for web/ext/trace.test.js (references to anything
// else become a stub that throws, so a code path that starts reaching
// further fails loudly instead of reading a wrong default).

import fs from 'node:fs';
import zlib from 'node:zlib';
import readline from 'node:readline';
import { setSeed } from '../java.js';
import { System } from '../ext/jawt.js';

const CLASSES = {};
for (const n of ['ContO', 'Plane', 'Wheels', 'Trackers', 'Medium', 'Madness', 'Control', 'CheckPoints', 'Record', 'Contva', 'Bots', 'xtGraphics',
  'RadicalMidi', 'RadicalMod', 'Mod', 'ModSlayer', 'ModTrackInfo', 'ModInstrument', 'SuperClip', 'SuperStream', 'UlawUtils']) {
  CLASSES[n] = (await import(`../ext/${n}.js`))[n];
}
const TYPED = { I: Int32Array, F: Float32Array, D: Float64Array, J: Float64Array, B: Int8Array, S: Int16Array, C: Uint16Array };

/** What is not game state (sound clips, images, threads): absorbs any use. */
function absorbing(name) {
  const f = function () {};
  const p = new Proxy(f, {
    get: (t, k) => (k === Symbol.toPrimitive ? () => 0 : k === 'toString' ? () => `<${name}>` : p),
    apply: () => undefined,
    construct: () => p,
  });
  return p;
}

/** A reference the fixture does not carry: any use is a test failure. */
function missing(id) {
  return new Proxy({}, { get(t, k) { if (k === Symbol.toPrimitive || k === 'then') return undefined; throw new Error(`trace fixture lacks object #${id} (the port reached further than when it was recorded)`); } });
}

function decode(v, ref) {
  if (v === null || typeof v === 'number' || typeof v === 'boolean') return v;
  if (v === 'NaN') return NaN;
  if (v === 'Infinity') return Infinity;
  if (v === '-Infinity') return -Infinity;
  if ('s' in v) return v.s;
  if ('r' in v) return ref(v.r);
  if ('x' in v) return absorbing(v.x);
  throw new Error('bad value ' + JSON.stringify(v));
}

/** Rebuild the "before" graph. Returns {get(id), idOf(obj), touched:Set} */
let writes = null, writeStack = 0;   // PUTFIELD-order log of the port's field writes

export function rebuild(pre, track = true) {
  const raw = new Map(), proxied = new Map(), idOf = new Map(), touched = new Set();
  for (const [id, o] of Object.entries(pre)) {
    let t;
    if ('a' in o) t = TYPED[o.a] ? new TYPED[o.a](o.v.length) : new Array(o.v.length);
    else t = Object.create((CLASSES[o.c] || Object).prototype);
    raw.set(+id, t);
    idOf.set(t, +id);
    // Without tracking (no fixture, no write log) the port runs on the plain
    // objects: ~100x faster than through the recording proxies.
    if (!track) { proxied.set(+id, t); continue; }
    proxied.set(+id, new Proxy(t, {
      get(tg, k) {
        touched.add(+id);
        const v = tg[k];
        return typeof v === 'function' && ArrayBuffer.isView(tg) ? v.bind(tg) : v;
      },
      set(tg, k, v) {
        touched.add(+id);
        tg[k] = v;
        if (writes && !ArrayBuffer.isView(tg) && !Array.isArray(tg)) {
          writes.push([k, v === null || v === undefined ? null : typeof v === 'object' || typeof v === 'function' ? `<${v.constructor?.name ?? '?'}>` : typeof v === 'boolean' ? (v ? 1 : 0) : v]);
          if (writeStack && writes.length === writeStack) writes.stack = new Error().stack;
        }
        return true;
      },
      has(tg, k) { touched.add(+id); return k in tg; },
    }));
  }
  const ref = (id) => proxied.get(id) ?? missing(id);
  for (const [id, o] of Object.entries(pre)) {
    const t = raw.get(+id);
    if ('a' in o) o.v.forEach((v, i) => { t[i] = decode(v, ref); });
    else for (const [k, v] of Object.entries(o.f)) t[k] = decode(v, ref);
  }
  return { get: ref, raw, idOf, touched };
}

/** Compare the jar's "after" with the port's objects; returns the first differences. */
export function compare(post, roots, g, limit = 10) {
  const out = [];
  const pair = new Map();               // jar id -> port raw object
  const seen = new Set();
  const queue = [];
  const unwrap = (v) => (v && typeof v === 'object' && g.idOf.has(v) ? v : g.raw.get(g.idOf.get(v)) ?? v);
  const want = (jid, obj, path) => {
    const port = unwrap(obj);
    if (pair.has(jid)) { if (pair.get(jid) !== port) out.push(`${path}: identity differs (jar #${jid})`); return; }
    pair.set(jid, port);
    queue.push([jid, port, path]);
  };
  const eq = (a, b) => (Number.isNaN(a) && Number.isNaN(b)) || a === b;
  roots.forEach((r, i) => { if (r && 'r' in r) want(r.r, g.get(r.r), `root${i}`); });
  while (queue.length && out.length < limit) {
    const [jid, port, path] = queue.shift();
    if (seen.has(jid)) continue;
    seen.add(jid);
    const j = post[jid];
    if (!j) continue;
    const check = (jv, pv, p) => {
      if (jv && typeof jv === 'object' && 'r' in jv) {
        if (pv === null || pv === undefined || typeof pv !== 'object') { out.push(`${p}: jar ref #${jv.r}, port ${pv}`); return; }
        want(jv.r, pv, p);
      } else if (jv && typeof jv === 'object' && 'x' in jv) { /* not game state */ }
      else {
        const d = decode(jv, () => null);
        if (!eq(d, pv)) out.push(`${p}: jar ${JSON.stringify(d)} vs port ${JSON.stringify(pv)}`);
      }
    };
    if ('a' in j) {
      if (!port || port.length !== j.v.length) { out.push(`${path}.length: jar ${j.v.length} vs port ${port?.length}`); continue; }
      j.v.forEach((v, i) => check(v, port[i], `${path}[${i}]`));
    } else {
      for (const [k, v] of Object.entries(j.f)) check(v, port?.[k], `${path}.${k}`);
    }
  }
  return out;
}

/** Ids whose content differs between the jar's before and after. */
function changed(pre, post) {
  const s = new Set();
  for (const [id, o] of Object.entries(post)) if (!pre[id] || JSON.stringify(pre[id]) !== JSON.stringify(o)) s.add(+id);
  return s;
}

export function run(rec, method, track = true) {
  const g = rebuild(rec.pre, track || !!rec.writes);
  const roots = rec.roots.map((r) => decode(r, g.get));
  g.touched.clear();
  writes = rec.writes ? [] : null;
  setSeed(rec.xs >>> 0);
  System.now = rec.nanos;
  let err = '';
  try { roots[0][method](...roots.slice(1)); } catch (e) { err = e.stack?.split('\n').slice(0, 3).join(' | ') || String(e); }
  const touched = new Set(g.touched);   // before compare() walks the proxies too
  const portWrites = writes;
  writes = null;
  if (rec.writes && portWrites) {
    // Java logs booleans as ints, floats as their widened doubles: same as JSON here
    const norm = (x) => (typeof x === 'string' && x.startsWith('<') ? x.replace(/<.*\./, '<') : x);
    let i = 0;
    while (i < rec.writes.length && i < portWrites.length && rec.writes[i][0] === portWrites[i][0] && (norm(rec.writes[i][1]) === norm(portWrites[i][1]) || (Number.isNaN(rec.writes[i][1]) && Number.isNaN(portWrites[i][1])))) i++;
    if (i < rec.writes.length || i < portWrites.length) {
      const ctx = (a) => JSON.stringify(a.slice(Math.max(0, i - 4), i + 3));
      console.log(`  first differing write #${i} of ${rec.writes.length} (port ${portWrites.length})
    jar : ${ctx(rec.writes)}
    port: ${ctx(portWrites)}`);
      if (!writeStack) {
        writeStack = i + 1;                       // rerun, grabbing the stack at that write
        const again = run(rec, method);
        writeStack = 0;
        console.log('    port stack at that write:\n' + (again.stack || '').split('\n').slice(2, 7).join('\n'));
      }
    }
  }
  return { g, touched, err, stack: portWrites?.stack, diffs: err ? [] : compare(rec.post, rec.roots, g) };
}

if (process.argv[1] && process.argv[1].endsWith('ext-trace.mjs')) {
  const [file, ...rest] = process.argv.slice(2);
  const fixture = rest.includes('--fixture') ? rest[rest.indexOf('--fixture') + 1] : null;
  const method = rest.includes('--method') ? rest[rest.indexOf('--method') + 1] : 'drive';
  const kept = [];
  let n = 0, ok = 0;
  const input = file.endsWith('.gz') ? fs.createReadStream(file).pipe(zlib.createGunzip()) : fs.createReadStream(file);
  const lines = readline.createInterface({ input, crlfDelay: Infinity });
  for await (const line of lines) {
    if (!line.trim()) continue;
    const rec = JSON.parse(line);
    process.stdout.write(`call ${rec.call} ... `);
    const t0 = Date.now();
    const { touched, err, diffs } = run(rec, method, !!fixture);
    process.stdout.write(`(${((Date.now() - t0) / 1000).toFixed(1)}s) `);
    n++;
    if (!err && !diffs.length) ok++;
    console.log(`call ${rec.call}: ${err ? 'ERROR ' + err : diffs.length ? diffs.length + ' diffs: ' + diffs.slice(0, 3).join(' ; ') : 'identical'}  (${Object.keys(rec.pre).length} objects, port touched ${touched.size})`);
    if (fixture) {
      const keep = new Set([...touched, ...changed(rec.pre, rec.post)]);
      for (const r of rec.roots) if (r && 'r' in r) keep.add(r.r);
      const cut = (m) => Object.fromEntries(Object.entries(m).filter(([id]) => keep.has(+id)));
      kept.push({ call: rec.call, xs: rec.xs, nanos: rec.nanos, roots: rec.roots, pre: cut(rec.pre), post: cut(rec.post) });
    }
  }
  console.log(`${ok}/${n} calls identical`);
  if (fixture) {
    fs.writeFileSync(fixture, zlib.gzipSync(JSON.stringify(kept), { level: 9 }));
    console.log(`fixture: ${fixture} (${(fs.statSync(fixture).size / 1e6).toFixed(2)} MB)`);
  }
}
