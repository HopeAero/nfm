// The base port's wheel dust (web/ContO.js dust/pdust: NFM2's newer ContO) for
// an Extended car. The Extended jar's own dust is four slots, one puff per wheel
// until it dies, drawn as an opaque star; the base spawns a puff per wheel per
// tick into a 20-slot ring, each a translucent octagon tinted by the road that
// grows and fades over 7 stages -- many overlapping puffs, which is what makes
// it read. Extended's slots (stg/dov/..., which Record replays) keep running in
// ContO; only this ring is drawn.
//
// Copied from web/ContO.js with the car's fields reached through `c`; the one
// change is the track lookup: Extended's Trackers have no sections, so a new
// puff looks through every piece.

import { floatArray, fr, i32, idiv, intArray, objArray, setDrawPhase, trunc } from '../java.js';
import { nearTrackers } from './trackgrid.js';

export class BaseDust {
  constructor(c) {
    this.c = c;
    this.ust = 0;
    this.sx = intArray(20);
    this.sy = intArray(20);
    this.sz = intArray(20);
    this.stg = intArray(20);
    this.scx = intArray(20);
    this.scz = intArray(20);
    this.sav = intArray(20);
    this.osmag = floatArray(20);
    this.sbln = floatArray(20);
    this.smag = objArray(20).map(() => floatArray(8));
    this.srgb = objArray(20).map(() => intArray(3));
  }

  // n: wheel, n2/n3/n4: its x/y/z, n5/n6: its scx/scz, n7: size, n8: tilt,
  // b: capsized && mtouch. Particles only -- on the draw streams, as the base.
  // keep: the odds this call makes a puff at all -- Extended's skid calls roll
  // far likelier than the base Mad's, so they are thinned to its rate here.
  dust(n, n2, n3, n4, n5, n6, n7, n8, b, keep = 1) {
    setDrawPhase(true);
    try {
      if (keep < 1 && this.c.m.random() >= keep) return;
      this.#dust(n, n2, n3, n4, n5, n6, n7, n8, b);
    } finally { setDrawPhase(false); }
  }

  #dust(n, n2, n3, n4, n5, n6, n7, n8, b) {
    const c = this.c;
    let b2 = false;
    if (n8 > 5 && (n === 0 || n === 2)) b2 = true;
    if (n8 < -5 && (n === 1 || n === 3)) b2 = true;
    let n9 = fr((Math.sqrt(n5 * n5 + n6 * n6) - 40.0) / 160.0);
    if (n9 > 1.0) n9 = 1.0;
    if (n9 > 0.2 && !b2) {
      ++this.ust;
      if (this.ust === 20) this.ust = 0;
      if (!b) {
        const random = c.m.random();
        this.sx[this.ust] = trunc(fr(fr(n2 + fr(c.x * random)) / fr(1.0 + random)));
        this.sz[this.ust] = trunc(fr(fr(n4 + fr(c.z * random)) / fr(1.0 + random)));
        this.sy[this.ust] = trunc(fr(fr(n3 + fr(c.y * random)) / fr(1.0 + random)));
      } else {
        this.sx[this.ust] = trunc(fr(n2 + (c.x + n5)) / 2.0);
        this.sz[this.ust] = trunc(fr(n4 + (c.z + n6)) / 2.0);
        this.sy[this.ust] = trunc(n3);
      }
      if (this.sy[n] > 250) this.sy[n] = 250;
      this.osmag[this.ust] = fr(n7 * n9);
      this.scx[this.ust] = n5;
      this.scz[this.ust] = n6;
      this.stg[this.ust] = 1;
    }
  }

  // Every live puff: b before the car's faces (the ones farther than it), !b after.
  draw(g, b, dist) {
    for (let n = 0; n < 20; ++n) if (this.stg[n] !== 0) this.#pdust(n, g, b, dist);
  }

  #pdust(n, graphics2D, b, dist) {
    const c = this.c;
    const m = c.m;
    if (b) {
      this.sav[n] = trunc(Math.sqrt(i32(
        Math.imul(m.x + m.cx - this.sx[n], m.x + m.cx - this.sx[n]) +
        Math.imul(m.y + m.cy - this.sy[n], m.y + m.cy - this.sy[n]) +
        Math.imul(m.z - this.sz[n], m.z - this.sz[n])
      )));
    }
    if (!((b && this.sav[n] > dist) || (!b && this.sav[n] <= dist))) return;
    // Interpolated redraws draw the puff where the tick left it: the roll at
    // stage 1, the drift, the growth and the stage advance only run on a tick.
    const tick = !m.interpolating;
    if (this.stg[n] === 1 && tick) {
      this.sbln[n] = 0.6;
      let b2 = false;
      const array = intArray(3);
      for (let i = 0; i < 3; ++i) {
        array[i] = trunc(fr(255.0 + fr(255.0 * fr(m.snap[i] / 100.0))));
        if (array[i] > 255) array[i] = 255;
        if (array[i] < 0) array[i] = 0;
      }
      const t = c.t;
      const near = nearTrackers(t, this.sx[n], this.sz[n], 0);   // trackgrid.js: same pieces, same order
      for (let q = 0, qn = near ? near.length : t.nt; q < qn; ++q) {
        const n2 = near ? near[q] : q;
        if (Math.abs(t.zy[n2]) !== 90 && Math.abs(t.xy[n2]) !== 90
            && Math.abs(this.sx[n] - t.x[n2]) < t.radx[n2]
            && Math.abs(this.sz[n] - t.z[n2]) < t.radz[n2]) {
          if (t.skd[n2] === 0) this.sbln[n] = 0.2;
          if (t.skd[n2] === 1) this.sbln[n] = 0.4;
          if (t.skd[n2] === 2) this.sbln[n] = 0.45;
          for (let k = 0; k < 3; ++k) this.srgb[n][k] = idiv(t.c[n2][k] + array[k], 2);
          b2 = true;
        }
      }
      if (!b2) {
        for (let l = 0; l < 3; ++l) this.srgb[n][l] = idiv(m.crgrnd[l] + array[l], 2);
      }
      let n3 = fr(0.1 + m.random());
      if (n3 > 1.0) n3 = 1.0;
      // bytecode: iaload; i2f; fmul; f2i; iastore -> §2 Case A, not `*= (int)n3`
      this.scx[n] = trunc(fr(this.scx[n] * n3));
      this.scz[n] = trunc(fr(this.scx[n] * n3));
      const smag = this.smag[n];
      for (let n4 = 0; n4 < 8; ++n4) smag[n4] = fr(fr(this.osmag[n] * m.random()) * 50.0);
      for (let n5 = 0; n5 < 8; ++n5) {
        const n6 = n5 === 0 ? 7 : n5 - 1;
        const n7 = n5 === 7 ? 0 : n5 + 1;
        smag[n5] = fr(fr(fr(fr(smag[n6] + smag[n7]) / 2.0) + smag[n5]) / 2.0);
      }
      smag[6] = smag[7];
    }
    const smag = this.smag[n];
    const n8 = m.cx + trunc(fr(fr((this.sx[n] - m.x - m.cx) * m.cos(m.xz)) - fr((this.sz[n] - m.z - m.cz) * m.sin(m.xz))));
    const n9 = m.cz + trunc(fr(fr((this.sx[n] - m.x - m.cx) * m.sin(m.xz)) + fr((this.sz[n] - m.z - m.cz) * m.cos(m.xz))));
    const n10 = m.cy + trunc(fr(fr(fr(this.sy[n] - m.y - m.cy - smag[7]) * m.cos(m.zy)) - fr((n9 - m.cz) * m.sin(m.zy))));
    const n11 = m.cz + trunc(fr(fr(fr(this.sy[n] - m.y - m.cy - smag[7]) * m.sin(m.zy)) + fr((n9 - m.cz) * m.cos(m.zy))));
    if (tick) {
      this.sx[n] = i32(this.sx[n] + idiv(this.scx[n], this.stg[n] + 1));
      this.sz[n] = i32(this.sz[n] + idiv(this.scz[n], this.stg[n] + 1));
    }
    const A = 0.9238, B = 0.3826;
    const array2 = intArray(8);
    const array3 = intArray(8);
    array2[0] = c.xs(trunc(n8 + fr(fr(smag[0] * A) * 1.5)), n11);
    array3[0] = c.ys(trunc(n10 + fr(fr(smag[0] * B) * 1.5)), n11);
    array2[1] = c.xs(trunc(n8 + fr(fr(smag[1] * A) * 1.5)), n11);
    array3[1] = c.ys(trunc(n10 - fr(fr(smag[1] * B) * 1.5)), n11);
    array2[2] = c.xs(trunc(n8 + fr(smag[2] * B)), n11);
    array3[2] = c.ys(trunc(n10 - fr(smag[2] * A)), n11);
    array2[3] = c.xs(trunc(n8 - fr(smag[3] * B)), n11);
    array3[3] = c.ys(trunc(n10 - fr(smag[3] * A)), n11);
    array2[4] = c.xs(trunc(n8 - fr(fr(smag[4] * A) * 1.5)), n11);
    array3[4] = c.ys(trunc(n10 - fr(fr(smag[4] * B) * 1.5)), n11);
    array2[5] = c.xs(trunc(n8 - fr(fr(smag[5] * A) * 1.5)), n11);
    array3[5] = c.ys(trunc(n10 + fr(fr(smag[5] * B) * 1.5)), n11);
    array2[6] = c.xs(trunc(n8 - fr(fr(smag[6] * B) * 1.7)), n11);
    array3[6] = c.ys(trunc(n10 + fr(smag[6] * A)), n11);
    array2[7] = c.xs(trunc(n8 + fr(fr(smag[7] * B) * 1.7)), n11);
    array3[7] = c.ys(trunc(n10 + fr(smag[7] * A)), n11);
    if (tick) {
      for (let n12 = 0; n12 < 7; ++n12) smag[n12] = fr(smag[n12] + fr(5.0 + fr(m.random() * 15.0)));
      smag[7] = smag[6];
    }
    let n14 = 0, n15 = 0, n16 = 0, n17 = 0;
    for (let n18 = 0; n18 < 8; ++n18) {
      if (array3[n18] < m.ih || n11 < 10) ++n14;
      if (array3[n18] > m.h || n11 < 10) ++n15;
      if (array2[n18] < m.iw || n11 < 10) ++n16;
      if (array2[n18] > m.w || n11 < 10) ++n17;
    }
    // `== 4`, not 8, in the original: an off-screen test that rarely fires.
    if (!(n16 === 4 || n14 === 4 || n15 === 4 || n17 === 4)) {
      let r = this.srgb[n][0];
      let g = this.srgb[n][1];
      let b4 = this.srgb[n][2];
      for (let n19 = 0; n19 < 16; ++n19) {
        if (this.sav[n] > m.fade[n19]) {
          r = idiv(r * m.fogd + m.cfade[0], m.fogd + 1);
          g = idiv(g * m.fogd + m.cfade[1], m.fogd + 1);
          b4 = idiv(b4 * m.fogd + m.cfade[2], m.fogd + 1);
        }
      }
      let alpha = fr(this.sbln[n] - fr(this.stg[n] * fr(this.sbln[n] / 8.0)));
      if (m.effect[4]) alpha = fr(alpha * (60 / 255));   // Extended's faded shadows (the jar's fadedshad)
      graphics2D.setColor(r, g, b4);
      graphics2D.setComposite(alpha);
      graphics2D.fillPolygon(array2, array3, 8);
      graphics2D.setComposite(1.0);
    }
    if (tick) {
      if (this.stg[n] === 7) this.stg[n] = 0;
      else ++this.stg[n];
    }
  }
}
