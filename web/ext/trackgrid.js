// A sector grid over the stage's trackers for the draw's "which piece is under
// this point" scans (ContO.d's shadow test, lowshadow, Plane.s), which Extended
// runs over every tracker for every car face: the start grid, cars bunched
// near the camera, cost 50-130 ms frames. The base port buckets by distance
// from a cell centre (Trackers.devidetrackers), which can miss a long piece;
// here a tracker goes into every cell its rectangle, grown by PAD, touches, so
// any tracker that can pass a scan's test at a point with margin <= PAD is in
// that point's cell. The scans keep their tests and their descending order, so
// they pick the same tracker the full sweep does.

import { intArray } from '../java.js';

const CELL = 3000, PAD = 1500;
const EMPTY = intArray(0);

export function buildTrackGrid(t) {
  let x0 = Infinity, z0 = Infinity, x1 = -Infinity, z1 = -Infinity;
  for (let k = 0; k < t.nt; k++) {
    x0 = Math.min(x0, t.x[k] - t.radx[k] - PAD); x1 = Math.max(x1, t.x[k] + t.radx[k] + PAD);
    z0 = Math.min(z0, t.z[k] - t.radz[k] - PAD); z1 = Math.max(z1, t.z[k] + t.radz[k] + PAD);
  }
  if (!t.nt) { t.grid = null; return; }
  const ncx = Math.floor((x1 - x0) / CELL) + 1, ncz = Math.floor((z1 - z0) / CELL) + 1;
  const lists = Array.from({ length: ncx * ncz }, () => []);
  for (let k = 0; k < t.nt; k++) {
    const i0 = Math.floor((t.x[k] - t.radx[k] - PAD - x0) / CELL), i1 = Math.floor((t.x[k] + t.radx[k] + PAD - x0) / CELL);
    const j0 = Math.floor((t.z[k] - t.radz[k] - PAD - z0) / CELL), j1 = Math.floor((t.z[k] + t.radz[k] + PAD - z0) / CELL);
    for (let j = j0; j <= j1; j++) for (let i = i0; i <= i1; i++) lists[i + j * ncx].push(k);
  }
  t.grid = { x0, z0, ncx, ncz, nt: t.nt, cells: lists.map((l) => Int32Array.from(l)) };
}

/**
 * The trackers, ascending, that can pass a test |x - t.x| < t.radx + margin (and
 * the same in z) at world point (x, z); null when the grid cannot answer (none
 * built, trackers added since, margin over PAD): scan them all.
 */
export function nearTrackers(t, x, z, margin) {
  const g = t.grid;
  if (!g || g.nt !== t.nt || margin > PAD) return null;
  const i = Math.floor((x - g.x0) / CELL), j = Math.floor((z - g.z0) / CELL);
  if (i < 0 || j < 0 || i >= g.ncx || j >= g.ncz) return EMPTY;
  return g.cells[i + j * g.ncx];
}

/**
 * Madness.drive's wheel sweep: for every tracker, ascending, each of the four
 * wheels (xs[w], zs[w]) is tested inside the tracker's rectangle -- and a hit
 * MOVES wheels (pushed to a wall's face). next() hands out the trackers of the
 * cells under the wheels' box; before each one it checks the wheels are still
 * within PAD - 1 of that box, and once one is not it goes on over every
 * tracker after the last one handed out. A tracker skipped on the way had the
 * wheels inside the safe box when its turn came, where only listed trackers
 * can hold them, so the sweep does exactly what the full one does.
 */
export class WheelSweep {
  constructor() { this.buf = new Int32Array(64); this.stamp = new Int32Array(0); this.gen = 0; }

  begin(t, xs, zs, count) {
    this.t = t; this.xs = xs; this.zs = zs; this.count = count;
    this.k = 0; this.i = 0; this.len = 0; this.last = -1; this.full = true;
    const g = t.grid;
    if (!g || g.nt !== t.nt || count !== t.nt) return this;
    let x0 = Infinity, x1 = -Infinity, z0 = Infinity, z1 = -Infinity;
    for (let w = 0; w < 4; w++) {
      x0 = Math.min(x0, xs[w]); x1 = Math.max(x1, xs[w]); z0 = Math.min(z0, zs[w]); z1 = Math.max(z1, zs[w]);
    }
    if (!(x1 - x0 < CELL * 4 && z1 - z0 < CELL * 4)) return this;   // NaN or a car torn across the map: sweep
    this.bx0 = x0 - (PAD - 1); this.bx1 = x1 + (PAD - 1); this.bz0 = z0 - (PAD - 1); this.bz1 = z1 + (PAD - 1);
    if (this.stamp.length < t.nt) this.stamp = new Int32Array(t.nt);
    const gen = ++this.gen;
    const i0 = Math.max(0, Math.floor((x0 - g.x0) / CELL)), i1 = Math.min(g.ncx - 1, Math.floor((x1 - g.x0) / CELL));
    const j0 = Math.max(0, Math.floor((z0 - g.z0) / CELL)), j1 = Math.min(g.ncz - 1, Math.floor((z1 - g.z0) / CELL));
    let len = 0;
    for (let j = j0; j <= j1; j++) for (let i = i0; i <= i1; i++) {
      for (const k of g.cells[i + j * g.ncx]) {
        if (this.stamp[k] === gen) continue;
        this.stamp[k] = gen;
        if (len === this.buf.length) { const b = new Int32Array(len * 2); b.set(this.buf); this.buf = b; }
        this.buf[len++] = k;
      }
    }
    this.buf.subarray(0, len).sort();
    this.len = len;
    this.full = false;
    return this;
  }

  /** The next tracker to test, or -1. */
  next() {
    if (!this.full) {
      const xs = this.xs, zs = this.zs;
      for (let w = 0; w < 4; w++) {
        if (!(xs[w] >= this.bx0 && xs[w] <= this.bx1 && zs[w] >= this.bz0 && zs[w] <= this.bz1)) {
          this.full = true;
          this.k = this.last + 1;
          break;
        }
      }
      if (!this.full) return this.i < this.len ? (this.last = this.buf[this.i++]) : -1;
    }
    return this.k < this.count ? (this.last = this.k++) : -1;
  }
}
