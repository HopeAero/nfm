// The stage maker's 3D view. The game's own loadstage builds the stage's
// sky, fog, ground, clouds, mountains, lights and boundary walls; the parts
// themselves are the editor's (map.js partObject, the same ContO loadstage
// makes), so an edit shows at once without rebuilding the stage. Drawn with
// the race's renderer. The camera orbits a point on the ground.
//
// Projection (ContO.d / Plane.xs), with dx = x - m.x - cx, dy = y - m.y - cy,
// dz = z - m.z - cz, a = xz (heading), b = zy (pitch down):
//   L = dx cos a - dz sin a            lateral
//   F = dx sin a + dz cos a            forward
//   D = cz + dy sin b + F cos b        depth
//   V = dy cos b - F sin b             vertical, y grows downward
//   screen = (cx + f L / D, cy + f V / D), f = focus_point
// toGround() inverts it on the plane y = GROUND, for clicks.

import { Plane } from '../Plane.js';
import { ContO } from '../ContO.js';

const GROUND = 250;
const WALL = 85;                   // models[] index of 'thewall' (sp 29 + 56)
const rad = (d) => (d * Math.PI) / 180;

export class View3D {
  constructor(world) {
    this.world = world;
    this.tx = 0; this.tz = 0;       // the point orbited, on the ground
    this.yaw = 0;                   // heading, degrees
    this.pitch = 35;                // degrees down
    this.dist = 9000;
    this.walls = [];
  }

  /** Build the stage's scenery and walls from `text` into the shared world. */
  build(text) {
    const { medium, trackers, checkPoints, models, gs, xt, record, placed, mads } = this.world;
    // loadstage leaves the stage's lights, fog and scenery counters in the
    // shared Medium; the map must not inherit them, so keep what was there.
    if (!this.saved) {
      this.saved = Object.fromEntries(Object.entries(medium)
        .filter(([, v]) => typeof v !== 'object' && typeof v !== 'function'));
    }
    checkPoints.stage = -2;                // a custom stage, as main.js loads one
    xt.nplayers = 1;
    // A stage that cannot race yet (under two checkpoints) is still built in
    // full -- loadstage only flags it -- so it can be looked at all the same.
    gs.loadstage(placed, models, medium, trackers, checkPoints, xt, mads, record, text);
    this.walls = [];
    for (let i = xt.nplayers; i < gs.nob; i++) {
      if (placed[i]?.baseIndex === WALL) this.walls.push(placed[i]);
    }
  }

  /** Put the Medium back the way the map had it. */
  restore() {
    if (this.saved) Object.assign(this.world.medium, this.saved);
  }

  /** Look at (x, z) from behind and above, far enough to take in `span`. */
  home(x, z, span = 20000) {
    this.tx = x; this.tz = z;
    this.yaw = 0;
    this.pitch = 35;
    this.dist = clampDist(span * 0.55);
  }

  orbit(dYaw, dPitch) {
    this.yaw = (((this.yaw + dYaw) % 360) + 360) % 360;
    this.pitch = Math.max(3, Math.min(85, this.pitch + dPitch));
  }

  /** Move the orbited point: `right` and `forward` in screen pixels. */
  pan(right, forward) {
    const r = rad(this.yaw);
    const k = this.dist / 800;
    this.tx += (right * Math.cos(r) + forward * Math.sin(r)) * k;
    this.tz += (-right * Math.sin(r) + forward * Math.cos(r)) * k;
  }

  zoom(f) { this.dist = clampDist(this.dist * f); }

  /** World units per screen pixel around the orbited point, for snapping. */
  get unitsPerPixel() { return this.dist / 400; }

  /** Set the Medium's camera. */
  camera() {
    const m = this.world.medium;
    const yaw = Math.round(this.yaw), pitch = Math.round(this.pitch);
    const h = this.dist * Math.cos(rad(pitch));
    m.trk = 0;
    m.crs = false;
    m.iw = 0; m.w = 800; m.ih = 0; m.h = 450;
    m.cx = 400; m.cy = 225; m.cz = 50;
    m.focus_point = 400;
    m.xz = yaw; m.zy = pitch;
    m.x = Math.round(this.tx - h * Math.sin(rad(yaw)) - m.cx);
    m.y = Math.round(Math.min(GROUND - this.dist * Math.sin(rad(pitch)), GROUND - 100) - m.cy);
    m.z = Math.round(this.tz - h * Math.cos(rad(yaw)) - m.cz);
  }

  /** Where world (x, y, z) lands on screen; d is its depth. */
  project(x, y, z) {
    const m = this.world.medium;
    const a = rad(m.xz), b = rad(m.zy);
    const dx = x - m.x - m.cx, dy = y - m.y - m.cy, dz = z - m.z - m.cz;
    const L = dx * Math.cos(a) - dz * Math.sin(a);
    const F = dx * Math.sin(a) + dz * Math.cos(a);
    const d = m.cz + dy * Math.sin(b) + F * Math.cos(b);
    const V = dy * Math.cos(b) - F * Math.sin(b);
    const f = m.focus_point;
    return { x: m.cx + (f * L) / d, y: m.cy + (f * V) / d, d };
  }

  /** The ground point under screen (sx, sy), or null above the horizon. */
  toGround(sx, sy) {
    const m = this.world.medium;
    const a = rad(m.xz), b = rad(m.zy);
    const f = m.focus_point;
    const u = (sy - m.cy) / f;
    const dy = GROUND - m.y - m.cy;
    const den = Math.sin(b) + u * Math.cos(b);
    if (Math.abs(den) < 1e-6) return null;
    const F = (dy * Math.cos(b) - u * m.cz - u * dy * Math.sin(b)) / den;
    const d = m.cz + dy * Math.sin(b) + F * Math.cos(b);
    if (d <= m.cz) return null;
    const L = ((sx - m.cx) / f) * d;
    const dx = L * Math.cos(a) + F * Math.sin(a);
    const dz = -L * Math.sin(a) + F * Math.cos(a);
    return { x: dx + m.x + m.cx, z: dz + m.z + m.cz };
  }

  /**
   * Draw the sky and ground, then `objs` and the walls in the game's depth
   * order; `overlay(rd)` draws on top before the frame ends.
   */
  draw(rd, objs, overlay) {
    const m = this.world.medium;
    this.camera();
    // The fog. Close up it is the stage's own (fadefrom); pulled back to see
    // the whole stage it recedes with the camera, or everything past 5000
    // would be fog. Put back after the frame.
    const fade = m.fade.slice();
    const n = Math.max(fade[0], Math.round(this.dist) + 4000);
    for (let i = 0; i < 16; ++i) m.fade[i] = Math.trunc(n / 2) * (i + 2);
    rd.begin();
    // Clouds are drawn with parallax at y / 20 (Medium.drawclouds): a camera
    // higher than 20x their altitude is above them, which the race never is,
    // and they project into a wedge across the sky. Leave them out up there.
    const noc = m.noc;
    if (-m.y / 20 > -m.cldd[4] - 300) m.noc = 0;
    m.d(rd);
    m.noc = noc;
    // The pieces with trk = 2, as the map: at trk = 0 ContO.d drops anything
    // that projects small (n4 > disp), which from afar is most of the stage.
    m.trk = 2;
    // And project in doubles. Plane.xs/ys and ContO.xs/ys multiply depth by
    // lateral offset in int32 (Math.imul, faithful to the Java); the race
    // never looks past its fog, but this view does, and past ~45000 deep the
    // product wraps and smears a polygon across the screen -- the tall pole
    // and the long lines the first version drew. Culling far pieces instead
    // cut half the stage off.
    const saved = [Plane.prototype.xs, Plane.prototype.ys, ContO.prototype.xs, ContO.prototype.ys];
    Plane.prototype.xs = ContO.prototype.xs = xsWide;
    Plane.prototype.ys = ContO.prototype.ys = ysWide;
    try {
      // Undistanced objects first, then far to near: the applet's two passes.
      const rest = [];
      for (const o of [...objs, ...this.walls]) {
        if (!o) continue;
        if (o.dist === 0) o.d(rd);
        else rest.push(o);
      }
      rest.sort((p, q) => q.dist - p.dist);
      for (const o of rest) o.d(rd);
      overlay?.(rd);
    } finally {
      [Plane.prototype.xs, Plane.prototype.ys, ContO.prototype.xs, ContO.prototype.ys] = saved;
      rd.end();
      for (let i = 0; i < 16; ++i) m.fade[i] = fade[i];
    }
  }
}

const clampDist = (d) => Math.max(1500, Math.min(60000, d));

// Plane.xs / ys without the int32 wrap (the clamp to cz is Plane's; ContO's
// is 50, the same value here since cz = 50).
function xsWide(n, cz) {
  if (cz < this.m.cz) cz = this.m.cz;
  return Math.trunc(((cz - this.m.focus_point) * (this.m.cx - n)) / cz) + n;
}
function ysWide(n, cz) {
  if (cz < this.m.cz) cz = this.m.cz;
  return Math.trunc(((cz - this.m.focus_point) * (this.m.cy - n)) / cz) + n;
}
