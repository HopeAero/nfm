// Where a part lands when you put it down -- the Stage Maker's snapping,
// after StageMaker.java:1004-1130.
//
//   Roads (anything with attachment points) snap END TO END: of every pair of
//   ends -- one of the new part's, one of an existing part's -- the closest
//   pair under 200 units is pulled together. That is what makes a road drawn
//   piece by piece line up exactly.
//   A checkpoint mounts on the road under it, centred on it and turned with
//   it, and becomes the asphalt (30) or the dirt (32) checkpoint to match
//   (rcheckp / ocheckp).
//   Ramps and obstacles centre on the road under them.
//   Trees, piles and the fixing hoop go where they are put.

import { ATP, rot } from './sort.js';
import { ASPHALT_CHECKPOINT_ROADS, DIRT_CHECKPOINT_ROADS } from './parts.js';

const turnFix = (c, r) => (c === 2 ? r + 30 : c === 3 ? r - 30 : c === 15 ? r - 90 : c === 20 ? r - 180 : c === 26 ? r - 90 : r);
const dist = (a, b, c, d) => Math.sqrt((a - b) * (a - b) + (c - d) * (c - d));
const hasEnds = (sp) => { const a = ATP[sp]; return !!a && (a[0] || a[1] || a[2] || a[3]); };
export const isCheckpoint = (sp) => sp === 30 || sp === 32 || sp === 54;
const isTree = (sp) => sp >= 55 && sp <= 65;
const isFree = (sp) => isTree(sp) || sp === 31 || sp === 66;

function ends(sp, x, z, deg, m, fixTurn) {
  const a = ATP[sp] || [0, 0, 0, 0];
  const xs = [x + a[0], x + a[2]];
  const zs = [z + a[1], z + a[3]];
  rot(xs, zs, x, z, fixTurn ? turnFix(sp, deg) : deg, 2, m);
  return [xs, zs];
}

/** The part whose footprint is under (x, z), preferring the nearest centre. */
export function partUnder(parts, x, z, maxR, filter = () => true) {
  let best = -1;
  let bestD = Infinity;
  parts.forEach((p, i) => {
    if (!filter(p)) return;
    const d = dist(p.x, x, p.z, z);
    if (d < (p.maxR ?? maxR(p.sp)) * 0.75 && d < bestD) { bestD = d; best = i; }
  });
  return best;
}

/**
 * Snap a part about to be placed. Returns { sp, x, z, rot } -- sp can change
 * (a checkpoint on dirt becomes the dirt checkpoint).
 */
export function snap(parts, sp, x, z, deg, maxR, m) {
  if (isFree(sp)) return { sp, x: Math.round(x), z: Math.round(z), rot: deg };
  if (isCheckpoint(sp)) {
    const i = partUnder(parts, x, z, maxR,
      (p) => ASPHALT_CHECKPOINT_ROADS.includes(p.sp) || DIRT_CHECKPOINT_ROADS.includes(p.sp));
    if (i === -1) return { sp, x: Math.round(x), z: Math.round(z), rot: deg };
    const road = parts[i];
    const r = ((road.rot % 180) + 180) % 180;       // a checkpoint has no front
    return { sp: DIRT_CHECKPOINT_ROADS.includes(road.sp) ? 32 : 30, x: road.x, z: road.z, rot: r };
  }
  if (!hasEnds(sp)) {
    const i = partUnder(parts, x, z, maxR, (p) => hasEnds(p.sp));
    if (i !== -1) return { sp, x: parts[i].x, z: parts[i].z, rot: deg };
    return { sp, x: Math.round(x), z: Math.round(z), rot: deg };
  }
  // End-to-end: the applet's own threshold is 200 units (py < n23 = 200).
  const [nx, nz] = ends(sp, x, z, deg, m, false);
  let best = 200;
  let dx = 0;
  let dz = 0;
  for (const p of parts) {
    if (!hasEnds(p.sp)) continue;
    const [ex, ez] = ends(p.sp, p.x, p.z, p.rot, m, true);
    for (let a = 0; a < 2; ++a) {
      for (let b = 0; b < 2; ++b) {
        const d = dist(ex[a], nx[b], ez[a], nz[b]);
        if (d < best && d !== 0) { best = d; dx = ex[a] - nx[b]; dz = ez[a] - nz[b]; }
      }
    }
  }
  return { sp, x: Math.round(x + dx), z: Math.round(z + dz), rot: deg };
}

/**
 * Snap by DRAGGING: when a road part is moved near an existing end, find the
 * pull within `radius` world units rather than the applet's 200 -- the
 * applet moves the part under the cursor continuously, so the part only has
 * to come within 200 of an end once; a click has to get there in one go.
 */
export function snapNear(parts, sp, x, z, deg, maxR, m, radius) {
  if (!hasEnds(sp) || isCheckpoint(sp)) return snap(parts, sp, x, z, deg, maxR, m);
  const [nx, nz] = ends(sp, x, z, deg, m, false);
  let best = radius;
  let dx = 0;
  let dz = 0;
  for (const p of parts) {
    if (!hasEnds(p.sp)) continue;
    const [ex, ez] = ends(p.sp, p.x, p.z, p.rot, m, true);
    for (let a = 0; a < 2; ++a) {
      for (let b = 0; b < 2; ++b) {
        const d = dist(ex[a], nx[b], ez[a], nz[b]);
        if (d < best) { best = d; dx = ex[a] - nx[b]; dz = ez[a] - nz[b]; }
      }
    }
  }
  return { sp, x: Math.round(x + dx), z: Math.round(z + dz), rot: deg };
}

/** What stops a stage from racing, in the terms a player can act on. */
export function problems(parts) {
  const out = [];
  if (!parts.length || (parts[0].sp !== 37 && parts[0].sp !== 38)) {
    out.push('The stage needs a start piece, and it has to be the first part.');
  }
  const checks = parts.filter((p) => isCheckpoint(p.sp)).length;
  if (checks < 2) out.push('A stage needs at least two checkpoints to race.');
  if (parts.filter((p) => p.sp === 31).length > 5) out.push('A stage can have at most 5 fixing hoops.');
  return out;
}
