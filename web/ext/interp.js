// Display-rate frames between Extended's ticks, as the base race draws them
// (main.js "interpolation"), without hand-written hooks in the generated classes.
//
// The base port marks every mutation its ContO/Medium drawing makes
// (`if (!this.m.interpolating)`). Extended's classes are generated, so instead
// the redraw is bracketed: what the drawing code WRITES is saved before it and
// put back after it. The field lists are what ContO.d, Plane.d and Medium.d
// (and every method of their own class they reach) assign to, read off the
// generated source; re-derive them if J2JS output changes (web/ext/interp.test.js
// fails when a reached method writes a field not listed). Randoms come from
// java.js's draw bank (setDrawPhase), so the simulation's stream -- the jar's
// single Math.random -- is not advanced by a redraw.
//
// Between ticks the placed objects and the camera are blended from the last
// tick's state to this one's, the scene is redrawn from them, and the HUD's
// vector part (the bars and arrow stat() emits into the batch) is replayed from
// the tick; its text and images stay on the overlay, which a redraw keeps.

import { fr, setDrawPhase } from '../java.js';
import { ContO } from './ContO.js';
import { Plane } from './Plane.js';

// Scalars go to a reused Float64Array; the array-valued fields are copied.
export const CONTO_SCALARS = ['dist', 'glowlines', 'sred', 'sgreen', 'sblue', 'xy', 'zy', 'fcnt', 'fix'];
export const CONTO_ARRAYS = ['stg', 'edl', 'edr', 'elc', 'sy', 'dov', 'glowcolour'];
export const PLANE_SCALARS = ['n', 'chip', 'ctmag', 'bfase', 'pa', 'pb', 'embos', 'dx', 'dy', 'dz', 'vx', 'vz', 'vy', 'av', 'flx', 'nocol'];
export const PLANE_ARRAYS = ['hsb', 'c'];
// Written by Plane.d but always before it reads them within the same call, and
// read by nothing else: a redraw's value is simply overwritten by the next draw.
export const PLANE_TEMPORARIES = ['cxz', 'cxy', 'czy', 'cox', 'coz', 'coy', 'projf'];
export const MEDIUM_WRITES = ['nsp', 'zy', 'xz', 'y', 'ground', 'cgrnd', 'cpol', 'crgrnd', 'oneffect', 'makefase',
  'effecttime', 'csky', 'cfade', 'shadowtrans', 'changingsnap', 'switchfase', 'verydark', 'decrease', 'glowfase',
  'polyoutline', 'lilo', 'lightn', 'cpflik', 'elecr', 'rand', 'diup', 'cntrn', 'trn', 'stc', 'twn',
  // ContO.d and Plane.d write these on their Medium (this.m), Medium.addsp the rest
  'flex', 'lastmaf', 'x', 'z', 'atrx', 'atrz', 'fallen', 'fo', 'hit'];
// The spark list: Medium.addsp only appends (spx[nsp] = ...; nsp++), so putting
// nsp back is enough -- what a redraw appended is past the end and overwritten.
export const MEDIUM_APPENDED = ['spx', 'spz', 'sprad'];

const OBJ = ['x', 'y', 'z', 'xz', 'xy', 'zy'];
const CAM = ['x', 'y', 'z', 'xz', 'zy'];

/** A copy of a field's value that restore() can write back without replacing the array. */
function save(v) {
  if (ArrayBuffer.isView(v)) return v.slice();
  if (Array.isArray(v)) return v.map(save);
  return v;
}

function restoreInto(cur, saved) {
  for (let i = 0; i < saved.length; i++) {
    const c = cur[i], s = saved[i];
    if (ArrayBuffer.isView(c) && ArrayBuffer.isView(s) && c.length === s.length) c.set(s);
    else if (Array.isArray(c) && Array.isArray(s) && c.length === s.length) restoreInto(c, s);
    else cur[i] = s;
  }
}

function restore(obj, key, saved) {
  const cur = obj[key];
  if (ArrayBuffer.isView(cur) && ArrayBuffer.isView(saved) && cur.length === saved.length) cur.set(saved);
  else if (Array.isArray(cur) && Array.isArray(saved) && cur.length === saved.length) restoreInto(cur, saved);
  else obj[key] = saved;
}

function saveFields(obj, fields) {
  const s = new Array(fields.length);
  for (let i = 0; i < fields.length; i++) s[i] = save(obj[fields[i]]);
  return s;
}

function restoreFields(obj, fields, s) {
  for (let i = 0; i < fields.length; i++) restore(obj, fields[i], s[i]);
}

/**
 * Medium.sin/cos for a blended, fractional angle. The generated ones index the
 * table with it -- tsin[12.5] is undefined, the vertex NaN, and the object (or,
 * through the camera, the whole scene) vanishes from that frame. This is the
 * base port's Medium.sin, which lerps between entries and is exact on an
 * integer; it is installed on the Medium only for the length of a redraw.
 */
function trig(t) {
  return (i) => {
    if (i === (i | 0) && i >= 0 && i < 360) return t[i];   // the common case: an unblended angle
    while (i >= 360) i -= 360;
    while (i < 0) i += 360;
    const i0 = i | 0;
    if (i0 === i) return t[i0];
    const a = t[i0], b = t[i0 + 1 === 360 ? 0 : i0 + 1];
    return fr(a + (b - a) * (i - i0));
  };
}

/** Shortest-path lerp for angles in degrees; plain lerp otherwise (main.js's `blend`). */
function blend(a, b, t, isAngle) {
  if (!isAngle) return a + (b - a) * t;
  let d = b - a;
  while (d > 180) d -= 360;
  while (d < -180) d += 360;
  return a + d * t;
}

/**
 * Specialised save/restore for one class's written fields, compiled once from
 * the field lists and the types seen on the first object: scalars (booleans
 * as 0/1) and the fixed 3-element arrays (Plane.hsb, Plane.c) go straight into
 * the Float64Array; other arrays (ContO's effect state, mostly null on track
 * pieces) are copied.
 */
function compile(sample, scalars, arrays) {
  const small = arrays.filter((f) => ArrayBuffer.isView(sample[f]) && sample[f].length === 3);
  const other = arrays.filter((f) => !small.includes(f));
  const bool = new Set(scalars.filter((f) => typeof sample[f] === 'boolean'));
  let sv = '', rs = '';
  for (const f of scalars) {
    sv += `n[i++] = +o.${f};\n`;
    rs += bool.has(f) ? `o.${f} = n[i++] !== 0;\n` : `o.${f} = n[i++];\n`;
  }
  for (const f of small) {
    sv += `{ const a = o.${f}; if (a) { n[i++] = 1; n[i++] = a[0]; n[i++] = a[1]; n[i++] = a[2]; } else { n[i++] = 0; i += 3; } }\n`;
    rs += `{ const a = o.${f}; if (n[i++] === 1 && a) { a[0] = n[i]; a[1] = n[i + 1]; a[2] = n[i + 2]; } i += 3; }\n`;
  }
  return {
    width: scalars.length + 4 * small.length,
    other,
    save: new Function('o', 'n', 'i', `${sv}return i;`),
    restore: new Function('o', 'n', 'i', `${rs}return i;`),
  };
}

/**
 * While started, the first d() on each ContO and Plane saves what drawing may
 * write on it; restore() puts all of it back. Only what is actually drawn is
 * saved -- most of a stage's planes are culled before Plane.d is reached.
 */
function makeGuard() {
  const cD = ContO.prototype.d, pD = Plane.prototype.d;
  let stamp = 0;
  const kinds = [
    { scalars: CONTO_SCALARS, arrays: CONTO_ARRAYS, c: null, list: [] },
    { scalars: PLANE_SCALARS, arrays: PLANE_ARRAYS, c: null, list: [] },
  ];
  let num = new Float64Array(1 << 17), nn = 0;
  const arrs = [];
  const snap = (o, k) => {
    if (o.$interp === stamp) return;
    o.$interp = stamp;
    const c = k.c || (k.c = compile(o, k.scalars, k.arrays));
    if (nn + c.width > num.length) { const b = new Float64Array(num.length * 2); b.set(num); num = b; }
    k.list.push(o, nn, arrs.length);
    nn = c.save(o, num, nn);
    for (const f of c.other) arrs.push(o[f] == null ? o[f] : save(o[f]));
  };
  const back = (k) => {
    const c = k.c, list = k.list;
    if (!c) return;
    for (let j = 0; j < list.length; j += 3) {
      const o = list[j];
      c.restore(o, num, list[j + 1]);
      let a = list[j + 2];
      for (const f of c.other) { const v = arrs[a++]; if (v == null) o[f] = v; else restore(o, f, v); }
    }
  };
  const [objs, planes] = kinds;
  return {
    start() {
      stamp++;
      objs.list.length = 0; planes.list.length = 0; arrs.length = 0; nn = 0;
      ContO.prototype.d = function (...a) { snap(this, objs); return cD.apply(this, a); };
      Plane.prototype.d = function (...a) { snap(this, planes); return pD.apply(this, a); };
    },
    stop() { ContO.prototype.d = cD; Plane.prototype.d = pD; },
    restore() { back(planes); back(objs); },
  };
}

/**
 * @param w {{rd, gs, xt, medium, placed}}  placed: run()'s aconto2 (loadstage's first argument)
 */
export function makeInterp(w) {
  const { rd, gs, xt, medium } = w;
  let prev = null, curr = null, hud = null, hudStart = -1, sceneVerts = 0;
  const guard = makeGuard();
  const sin = trig(medium.tsin), cos = trig(medium.tcos);
  const prof = { draw: 0, restore: 0 };   // ms, for ?stats=1

  // The HUD's vector part starts where the scene ends: at the first of
  // nitroandspecials() and stat() in the race frame. (xtGraphics also has a
  // field `stat`, so J2JS names the method stat$m.)
  for (const name of ['nitroandspecials', typeof xt.stat$m === 'function' ? 'stat$m' : 'stat']) {
    const f = xt[name];
    xt[name] = function (...a) {
      if (hudStart < 0) { hudStart = rd.count; sceneVerts = rd.inputVerts; }
      return f.apply(this, a);
    };
  }

  const capture = () => {
    const objs = [];
    for (let i = 0; i < gs.nob; i++) {
      const o = w.placed[i];
      if (o) objs[i] = { o, x: o.x, y: o.y, z: o.z, xz: o.xz, xy: o.xy, zy: o.zy };
    }
    const cam = {};
    for (const f of CAM) cam[f] = medium[f];
    return { objs, cam };
  };

  /** The race frame's scene, as GameSparker.java:2160-2224 draws it (no mutation of its own). */
  const drawScene = () => {
    const placed = w.placed, nob = gs.nob;
    medium.d(rd);
    if (medium.effect[4]) medium.redrawpolys(rd);
    const far = [];
    for (let k = 0; k < nob; k++) {
      if (placed[k].dist !== 0) far.push(k);
      else if (!xt.norender[k]) placed[k].d(rd);
    }
    // the jar's rank sort, ties by index, kept as it is: it sets the painting order
    const n = far.length;
    const rank = new Int32Array(n), order = new Int32Array(n);
    for (let a = 0; a < n; a++) {
      for (let b = a + 1; b < n; b++) {
        const da = placed[far[a]].dist, db = placed[far[b]].dist;
        if (da !== db) { if (da < db) rank[a]++; else rank[b]++; }
        else rank[a]++;
      }
      order[rank[a]] = a;
    }
    for (let r = 0; r < n; r++) if (!xt.norender[far[order[r]]]) placed[far[order[r]]].d(rd);
    if (medium.showwater) xt.drawwater(medium.cfade[0], medium.cfade[1], medium.cfade[2]);
  };

  return {
    prof,
    /** Polygon vertices the last tick's scene submitted (before its HUD). */
    get sceneVerts() { return sceneVerts; },
    /** Before a tick: mark where its HUD will start. */
    beforeTick() { prev = curr; hudStart = -1; },
    /** After a tick: its end state, and its HUD geometry to replay. */
    afterTick() {
      curr = capture();
      if (!prev) prev = curr;
      hud = rd.snapshotFrom(hudStart >= 0 ? hudStart : rd.count);
    },
    /** Redraw the scene at t in [0,1) between the last two ticks; state is left as the tick left it. */
    redraw(t) {
      if (!curr || !prev) return false;
      const placed = w.placed, nob = gs.nob;
      // what drawing writes, saved; each ContO / Plane the first time its d() runs
      const med = saveFields(medium, MEDIUM_WRITES);
      const trans = new Array(nob);
      for (let i = 0; i < nob; i++) { const o = placed[i]; if (o) trans[i] = [o.x, o.y, o.z, o.xz, o.xy, o.zy]; }
      // the blend: an object that was rebuilt this tick (a respawn) is drawn where it is
      for (let i = 0; i < nob; i++) {
        const o = placed[i], p = prev.objs[i], c = curr.objs[i];
        if (!o || !p || !c || p.o !== o || c.o !== o) continue;
        o.x = Math.round(blend(p.x, c.x, t, false));
        o.y = Math.round(blend(p.y, c.y, t, false));
        o.z = Math.round(blend(p.z, c.z, t, false));
        o.xz = blend(p.xz, c.xz, t, true);
        o.xy = blend(p.xy, c.xy, t, true);
        o.zy = blend(p.zy, c.zy, t, true);
      }
      medium.x = Math.round(blend(prev.cam.x, curr.cam.x, t, false));
      medium.y = Math.round(blend(prev.cam.y, curr.cam.y, t, false));
      medium.z = Math.round(blend(prev.cam.z, curr.cam.z, t, false));
      medium.xz = blend(prev.cam.xz, curr.cam.xz, t, true);
      medium.zy = blend(prev.cam.zy, curr.cam.zy, t, true);

      rd.begin(true);             // the tick's HUD text and images stay on the overlay
      setDrawPhase(true);
      medium.sin = sin; medium.cos = cos;
      guard.start();
      const tDraw = performance.now();
      try {
        drawScene();
        prof.draw += performance.now() - tDraw;
      } finally {
        guard.stop();
        delete medium.sin; delete medium.cos;   // back to the prototype's, the jar's
        setDrawPhase(false);
        rd.replay(hud);
        // ...and everything put back as the tick left it
        restoreFields(medium, MEDIUM_WRITES, med);
        for (const f of CAM) medium[f] = curr.cam[f];
        const tRest = performance.now();
        guard.restore();
        prof.restore += performance.now() - tRest;
        for (let i = 0; i < nob; i++) { const o = placed[i]; if (o && trans[i]) [o.x, o.y, o.z, o.xz, o.xy, o.zy] = trans[i]; }
      }
      return true;
    },
  };
}
