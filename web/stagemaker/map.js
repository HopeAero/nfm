// The stage maker's overhead view: the stage's own parts, drawn by the game's
// renderer from straight above, with the camera the launcher's map uses
// (preview.js drawStage3D): trk = 2 (the applet's overhead editing mode, which
// keeps decor and skips the distance culls), zy = 90, xz = 0.
//
// With xz = 0 and zy = 90 the projection collapses to
//   screenX = cx + (x - camX) * focus / DEPTH
//   screenY = cy - (z - camZ) * focus / DEPTH
// so a zoom is a focus_point and a pan is the camera's x/z; toWorld() is the
// inverse, for clicks. DEPTH is kept short -- clear of the tallest part, no
// more -- because Plane.xs multiplies (cz - focus) by the camera-space x in
// int32, and a far camera overflows it (WORK.md, the overhead-map entry).

import { ContO } from '../ContO.js';

export const DEPTH = 9000;
export const GROUND = 250;

export class MapView {
  constructor() {
    this.x = 0;               // world point at the centre of the view
    this.z = 0;
    this.scale = 0.02;        // game-space pixels per world unit
  }

  get focus() { return Math.max(1, Math.round(this.scale * DEPTH)); }

  toWorld(sx, sy) {
    return { x: this.x + (sx - 400) / this.scale, z: this.z - (sy - 225) / this.scale };
  }

  toScreen(x, z) {
    return { x: 400 + (x - this.x) * this.scale, y: 225 - (z - this.z) * this.scale };
  }

  zoomAt(sx, sy, factor) {
    const before = this.toWorld(sx, sy);
    this.scale = Math.max(0.003, Math.min(0.25, this.scale * factor));
    const after = this.toWorld(sx, sy);
    this.x += before.x - after.x;
    this.z += before.z - after.z;
  }

  /** Frame every part, with a margin. */
  fit(parts, maxR) {
    if (!parts.length) { this.x = 0; this.z = 0; this.scale = 0.02; return; }
    let minX = Infinity, maxX = -Infinity, minZ = Infinity, maxZ = -Infinity;
    for (const p of parts) {
      const r = p.maxR ?? maxR(p.sp);
      minX = Math.min(minX, p.x - r); maxX = Math.max(maxX, p.x + r);
      minZ = Math.min(minZ, p.z - r); maxZ = Math.max(maxZ, p.z + r);
    }
    this.x = (minX + maxX) / 2;
    this.z = (minZ + maxZ) / 2;
    // At least ~40000 units across, so a new stage (the start alone) opens
    // with room around it to build in rather than zoomed onto one piece.
    const w = Math.max(maxX - minX, 40000);
    const h = Math.max(maxZ - minZ, 22500);
    this.scale = Math.max(0.003, Math.min(0.25, 0.9 * Math.min(800 / w, 450 / h)));
  }

  camera(medium) {
    medium.trk = 2;
    medium.crs = false;
    medium.ih = 0; medium.iw = 0; medium.w = 800; medium.h = 450;
    medium.cx = 400; medium.cy = 225; medium.cz = 50;
    medium.xz = 0; medium.zy = 90;
    medium.focus_point = this.focus;
    medium.x = this.x - medium.cx;
    medium.z = this.z - medium.cz;
    medium.y = GROUND - medium.cy + medium.cz - DEPTH;
    medium.ground = GROUND;
  }
}

/** A part as the game builds it in loadstage(): the model at its place. */
export function partObject(p, models, medium, trackers) {
  // A ContO appends its collision boxes to the trackers. The editor never
  // simulates, and it rebuilds every part on each edit (and a ghost on each
  // mouse move), so keep none: without this the arrays filled up and every
  // later ContO threw ("Cannot set properties of undefined").
  const t = p.sp === 66 ? trackers : models[p.sp + 56]?.t ?? trackers;   // a copy uses its base's
  const nt = t.nt;
  try {
    if (p.sp === 66) {
      return new ContO(p.srz, p.srx, p.sry, medium, trackers, p.x, p.z, GROUND);
    }
    const base = models[p.sp + 56];
    if (!base) return null;
    let y = GROUND - base.grat;
    if (p.sp === 54) y = p.y;
    if (p.sp === 31) y = p.y;
    const o = new ContO(base, p.x, y, p.z, p.rot);
    if (p.sp === 31) o.elec = true;
    return o;
  } finally {
    t.nt = nt;
  }
}

/**
 * Draw the parts in the game's depth order: the objects with no distance yet
 * first, then the rest far to near -- the two passes of the applet's own
 * overhead loop (and GameSparker's). There is no depth buffer; this order is
 * the occlusion.
 */
export function drawParts(rd, objs) {
  const near = [];
  for (const o of objs) {
    if (!o) continue;
    if (o.dist !== 0) near.push(o);
    else o.d(rd);
  }
  near.sort((a, b) => b.dist - a.dist);
  for (const o of near) o.d(rd);
}
