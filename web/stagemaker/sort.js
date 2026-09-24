// The Stage Maker's stage model and its save step, transcribed from
// StageMaker.java: readstage() (text -> placed parts), sortstage() (parts ->
// the `set/chk/fix/pile/max*` block the race reads) and the rot()/pyn()
// helpers they use.
//
// sortstage() is the part that matters. The race's bots drive the ROUTE
// POINTS -- `set(...)p/pt/pr/ph/pl` lines -- in FILE ORDER, so the order the
// pieces are written in is not cosmetic: sortstage() walks the road from the
// start piece along each piece's two attachment points (ATP) and writes them
// in driving order, flags which ones are route points, threads checkpoints and
// the fixing hoop into the sequence, and sizes the four boundary walls from
// the pieces' radii. Transcribed as-is, integer arithmetic included; the one
// Math.random() (which halfpipes become a bot's 'h' route point) takes the
// port's seeded random().
//
// A part: { sp, x, z, rot, wh, y, maxR, srx, sry, srz }
//   sp  -- the Stage Maker's piece number (`colok`): file id - 10, model sp + 56.
//          30/32 checkpoints, 54 the raised checkpoint, 31 the fixing hoop,
//          66 a `pile`.
//   rot -- `roofat`, the heading in degrees.
//   wh  -- a checkpoint's forced order (1..), from `chk(...)r`; 0 = by distance.

import { trunc, fr, idiv, random } from '../java.js';

export const CHECKPOINT = 30;
export const CHECKPOINT_2 = 32;
export const CHECKPOINT_RAISED = 54;
export const FIX_HOOP = 31;
export const PILE = 66;

/** Each piece's two attachment points, [x0, z0, x1, z1] (StageMaker.java:253). */
export const ATP = [
  [0, 2800, 0, -2800], [0, 2800, 0, -2800], [1520, 2830, -1520, -2830], [-1520, 2830, 1520, -2830],
  [0, -1750, 1750, 0], [0, 2800, 0, -2800], [0, 2800, 0, -2800], [0, -1750, 1750, 0],
  [0, 2800, 0, -2800], [0, -1750, 1750, 0], [0, 2800, 0, -2800], [0, 2800, 0, -2800],
  [0, 560, 0, -560], [0, 0, 0, 0], [0, 0, 0, 0], [385, 980, 385, -980],
  [0, 0, 0, -600], [0, 0, 0, 0], [0, 2164, 0, -2164], [0, 2164, 0, -2164],
  [0, 3309, 0, -1680], [0, 1680, 0, -3309], [350, 0, -350, 0], [0, 0, 0, 0],
  [0, 0, 0, 0], [0, 0, 0, 0], [1810, 980, 1810, -980], [0, 0, 0, 0],
  [0, 500, 0, -500], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0],
  [0, 0, 0, 0], [0, 2800, 0, -2800], [0, 2800, 0, -2800], [0, 1680, 0, -3309],
  [0, 2800, 0, -2800], [0, 2800, 0, -2800], [0, 2800, 0, -2800], [700, 1400, 700, -1400],
  [0, -1480, 0, -1480], [0, 0, 0, 0], [350, 0, -350, 0], [0, 0, 0, 0],
  [700, 0, -700, 0], [0, 0, 0, 0], [0, -2198, 0, 1482], [0, -1319, 0, 1391],
  [0, -1894, 0, 2271], [0, -826, 0, 839], [0, -1400, 0, 1400], [0, -1400, 0, 1400],
  [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0],
  [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0],
  [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0],
  [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0],
];

/** StageMaker.pyn: squared distance in hundreds, integer division as in Java. */
export function pyn(n, n2, n3, n4) {
  const a = idiv(n - n2, 100);
  const b = idiv(n3 - n4, 100);
  return a * a + b * b;
}

/** StageMaker.rot over `count` points, with the game's cos/sin tables (m). */
export function rot(xs, zs, cx, cz, deg, count, m) {
  if (deg === 0) return;
  const c = m.cos(deg), s = m.sin(deg);
  for (let i = 0; i < count; ++i) {
    const a = xs[i], b = zs[i];
    xs[i] = cx + trunc(fr(fr((a - cx) * c) - fr((b - cz) * s)));
    zs[i] = cz + trunc(fr(fr((a - cx) * s) + fr((b - cz) * c)));
  }
}

function getint(name, line, n) {
  // CheckPoints/GameSparker.getint: the n-th comma field inside name(...).
  const inner = line.slice(name.length + 1, line.indexOf(')'));
  const v = parseInt(inner.split(',')[n], 10);
  return Number.isNaN(v) ? 0 : v;
}

/**
 * Split a stage file into its head (every line that is not a placed part or
 * a boundary wall -- sky, fog, laps, music, name ...) and its parts, as
 * readstage() builds `co[]`. The walls are dropped: sortstage() writes them
 * fresh from the parts.
 */
export function readParts(text) {
  const head = [];
  const parts = [];
  let nsp = 0;
  for (const raw of text.split(/\r?\n/)) {
    const line = raw.trim();
    if (line.startsWith('set(')) {
      parts.push({ sp: getint('set', line, 0) - 10, x: getint('set', line, 1), z: getint('set', line, 2),
        rot: getint('set', line, 3), wh: 0, y: 0 });
    } else if (line.startsWith('chk(')) {
      const sp = getint('chk', line, 0) - 10;
      const p = { sp, x: getint('chk', line, 1), z: getint('chk', line, 2), rot: getint('chk', line, 3),
        wh: 0, y: sp === CHECKPOINT_RAISED ? getint('chk', line, 4) : 0 };
      if (line.indexOf(')r') !== -1) p.wh = nsp + 1;
      ++nsp;
      parts.push(p);
    } else if (line.startsWith('fix(')) {
      parts.push({ sp: getint('fix', line, 0) - 10, x: getint('fix', line, 1), z: getint('fix', line, 2),
        y: getint('fix', line, 3), rot: getint('fix', line, 4), wh: 0 });
    } else if (line.startsWith('pile(')) {
      parts.push({ sp: PILE, srz: getint('pile', line, 0), srx: getint('pile', line, 1), sry: getint('pile', line, 2),
        x: getint('pile', line, 3), z: getint('pile', line, 4), rot: 0, wh: 0, y: 0 });
    } else if (/^max[rlbt]\(/.test(line)) {
      // regenerated by sortStage
    } else if (line !== '') {
      head.push(line);
    }
  }
  return { head, parts };
}

const isRoad = (c) => (c <= 14 || c >= 33) && (c < 39 || c >= 46) && c < 52;
const isCheck = (c) => c === CHECKPOINT || c === CHECKPOINT_2 || c === CHECKPOINT_RAISED;
const turnFix = (c, r) => (c === 2 ? r + 30 : c === 3 ? r - 30 : c === 15 ? r - 90 : c === 20 ? r - 180 : c === 26 ? r - 90 : r);
const isBend = (c) => c === 2 || c === 3 || c === 4 || c === 7 || c === 9;

/**
 * StageMaker.sortstage (StageMaker.java:5551). `co` is the parts, co[0] the
 * start piece; `maxR(sp)` the model radius; `m` a Medium (cos/sin tables);
 * `nsp` the number of checkpoints. Returns the `bstage` text.
 */
export function sortStage(co, maxR, m) {
  const nob = co.length;
  if (nob === 0) return '';
  const R = (i) => co[i].maxR ?? maxR(co[i].sp);
  const nsp = co.filter((p) => isCheck(p.sp)).length;
  const array = new Array(nob * 2).fill(0);
  const array2 = new Array(nob * 2).fill(0);
  const ends = (i) => {
    const a = ATP[co[i].sp] || [0, 0, 0, 0];
    const xs = [co[i].x + a[0], co[i].x + a[2]];
    const zs = [co[i].z + a[1], co[i].z + a[3]];
    rot(xs, zs, co[i].x, co[i].z, turnFix(co[i].sp, co[i].rot), 2, m);
    return [xs, zs];
  };
  let n = 0;
  let n2 = 0;
  array2[n2++] = 0;
  let j = 0;
  let n3 = 0;
  while (j === 0) {
    const [array3, array4] = ends(n);
    let n4 = -1;
    let n5 = -1;
    if (n3 !== 0) {
      for (let k = 0; k < nob; ++k) {
        const b = n2 === 2 && k === 0;
        if (n !== k && !b && array[k] === 0 && isRoad(co[k].sp)) {
          let n6 = 0;
          if (!isBend(co[k].sp)) {
            if (n3 === 1 && co[k].z > co[n].z && Math.abs(co[k].x - co[n].x) < 1000 && (co[k].rot === 180 || co[k].rot === 0)) n6 = 1;
            if (n3 === 2 && co[k].z < co[n].z && Math.abs(co[k].x - co[n].x) < 1000 && (co[k].rot === 180 || co[k].rot === 0)) n6 = 1;
            if (n3 === 3 && co[k].x > co[n].x && Math.abs(co[k].z - co[n].z) < 1000 && (co[k].rot === 90 || co[k].rot === -90)) n6 = 1;
            if (n3 === 4 && co[k].x < co[n].x && Math.abs(co[k].z - co[n].z) < 1000 && (co[k].rot === 90 || co[k].rot === -90)) n6 = 1;
          } else {
            n6 = 2;
          }
          if (n6 !== 0) {
            const [array5, array6] = ends(k);
            if (k !== 0) {
              const d = pyn(array5[0], array3[0], array6[0], array4[0]);
              if (d >= 0 && (d < 100 || n6 !== 2) && (d < n4 || n4 === -1)) { n4 = d; n5 = k; }
            }
            const d2 = pyn(array5[1], array3[0], array6[1], array4[0]);
            if (d2 >= 0 && (d2 < 100 || n6 !== 2) && (d2 < n4 || n4 === -1)) { n4 = d2; n5 = k; }
            if (n !== 0) {
              if (k !== 0) {
                const d3 = pyn(array5[0], array3[1], array6[0], array4[1]);
                if (d3 >= 0 && (d3 < 100 || n6 !== 2) && d3 < n4) { n4 = d3; n5 = k; }
              }
              const d4 = pyn(array5[1], array3[1], array6[1], array4[1]);
              if (d4 >= 0 && (d4 < 100 || n6 !== 2) && d4 < n4) { n4 = d4; n5 = k; }
            }
          }
        }
      }
    }
    if (n5 === -1) {
      for (let l = 0; l < nob; ++l) {
        const b2 = n2 === 2 && l === 0;
        if (n !== l && !b2 && array[l] === 0 && isRoad(co[l].sp)) {
          const [array7, array8] = ends(l);
          if (l !== 0) {
            const d = pyn(array7[0], array3[0], array8[0], array4[0]);
            if (d >= 0 && (d < n4 || n4 === -1)) { n4 = d; n5 = l; }
          }
          const d2 = pyn(array7[1], array3[0], array8[1], array4[0]);
          if (d2 >= 0 && (d2 < n4 || n4 === -1)) { n4 = d2; n5 = l; }
          if (n !== 0) {
            if (l !== 0) {
              const d3 = pyn(array7[0], array3[1], array8[0], array4[1]);
              if (d3 >= 0 && d3 < n4) { n4 = d3; n5 = l; }
            }
            const d4 = pyn(array7[1], array3[1], array8[1], array4[1]);
            if (d4 >= 0 && d4 < n4) { n4 = d4; n5 = l; }
          }
        }
      }
    }
    if (n5 !== -1) {
      n3 = 0;
      if (!isBend(co[n5].sp)) {
        if ((co[n5].rot === 180 || co[n5].rot === 0) && co[n5].z > co[n].z) n3 = 1;
        if ((co[n5].rot === 180 || co[n5].rot === 0) && co[n5].z < co[n].z) n3 = 2;
        if ((co[n5].rot === 90 || co[n5].rot === -90) && co[n5].x > co[n].x) n3 = 3;
        if ((co[n5].rot === 90 || co[n5].rot === -90) && co[n5].x < co[n].x) n3 = 4;
      }
      if (co[n5].sp === 4 || co[n5].sp === 7 || co[n5].sp === 9) array[n5] = 2;
      else array[n5] = 1;
      if (co[n5].sp >= 46 && co[n5].sp <= 51) array[n5] = 6;
      n = n5;
      if (n === 0) {
        array[0] = 1;
        j = 1;
      } else {
        array2[n2++] = n5;
      }
    } else {
      array[0] = 1;
      j = 1;
    }
  }
  for (let n7 = 0; n7 < nob; ++n7) {
    if (array[n7] === 0 && isRoad(co[n7].sp)) array2[n2++] = n7;
  }
  const near = (a, b) => {
    const s = idiv(R(a) + R(b), 100);
    return s * s;
  };
  for (let n8 = 0; n8 < n2; ++n8) {
    if (co[array2[n8]].sp >= 46 && co[array2[n8]].sp <= 51) {
      for (let n9 = n8 + 1; n9 < n2; ++n9) {
        const d = pyn(co[array2[n8]].x, co[array2[n9]].x, co[array2[n8]].z, co[array2[n9]].z);
        if (d >= 0 && (co[array2[n9]].sp < 46 || co[array2[n8]].sp > 51) && d < near(array2[n8], array2[n9])) {
          const n10 = array2[n9];
          for (let n11 = n9; n11 > n8; --n11) array2[n11] = array2[n11 - 1];
          array2[n8] = n10;
          array[n10] = 0;
          ++n8;
        }
      }
    }
  }
  let n12 = 1;
  const insertAfterNearest = (who, from, skipChecks) => {
    let best = -1;
    let at = -1;
    for (let q = from; q < n2; ++q) {
      if (skipChecks && isCheck(co[array2[q]].sp)) continue;
      const d = pyn(co[who].x, co[array2[q]].x, co[who].z, co[array2[q]].z);
      if (d >= 0 && (d < best || best === -1)) { best = d; at = q; }
    }
    return at;
  };
  for (let n13 = 0; n13 < nsp; ++n13) {
    for (let n14 = 0; n14 < nob; ++n14) {
      if (co[n14].wh === n13 + 1 && isCheck(co[n14].sp)) {
        const n16 = insertAfterNearest(n14, n12, true);
        if (n16 !== -1) {
          array[array2[n16]] = 0;
          for (let n18 = n2; n18 > n16; --n18) array2[n18] = array2[n18 - 1];
          array2[n16 + 1] = n14;
          n12 = n16 + 1;
          ++n2;
        } else {
          array2[n2] = n14;
          n12 = n2;
          ++n2;
        }
      }
    }
  }
  for (let n19 = 0; n19 < nob; ++n19) {
    if (co[n19].wh === 0 && isCheck(co[n19].sp)) {
      const n21 = insertAfterNearest(n19, n12, true);
      if (n21 !== -1) {
        array[array2[n21]] = 0;
        for (let n23 = n2; n23 > n21; --n23) array2[n23] = array2[n23 - 1];
        array2[n21 + 1] = n19;
        ++n2;
      } else {
        array2[n2++] = n19;
      }
    }
  }
  for (let n24 = 0; n24 < nob; ++n24) {
    if (co[n24].sp === FIX_HOOP) {
      const n26 = insertAfterNearest(n24, 0, false);
      if (n26 !== -1) {
        for (let n28 = n2; n28 > n26; --n28) array2[n28] = array2[n28 - 1];
        array2[n26] = n24;
        ++n2;
      } else {
        array2[n2++] = n24;
      }
    }
  }
  // Obstacles, then ramps, then halfpipes/tunnel ramps: each goes in after the
  // last road piece it overlaps.
  const lastOverlapping = (who, onHit) => {
    let at = -1;
    for (let q = 0; q < n2; ++q) {
      const c = co[array2[q]].sp;
      if ((c <= 14 || c >= 33) && c < 39) {
        const d = pyn(co[who].x, co[array2[q]].x, co[who].z, co[array2[q]].z);
        if (d >= 0 && d < near(who, array2[q])) {
          if (!onHit || onHit(q)) at = q;
        }
      }
    }
    return at;
  };
  const insertAfter = (who, at) => {
    if (at !== -1) {
      for (let q = n2; q > at; --q) array2[q] = array2[q - 1];
      array2[at + 1] = who;
      ++n2;
    } else {
      array2[n2++] = who;
    }
  };
  for (let n29 = 0; n29 < nob; ++n29) {
    const c = co[n29].sp;
    if (c === 15 || c === 27 || c === 28 || c === 41 || c === 44 || c === 52 || c === 53) {
      insertAfter(n29, lastOverlapping(n29));
    }
  }
  for (let n33 = 0; n33 < nob; ++n33) {
    const c = co[n33].sp;
    if ((c >= 16 && c <= 25) || c === 40 || c === 42 || c === 43 || c === 45) {
      const at = lastOverlapping(n33, (q) => {
        if (array[array2[q]] !== 0) {
          array[array2[q]] = 0;
          array[n33] = c !== 20 ? 3 : 5;
        }
        return true;
      });
      insertAfter(n33, at);
    }
  }
  for (let n37 = 0; n37 < nob; ++n37) {
    const c = co[n37].sp;
    if (c === 26 || c === 39) {
      let b3 = false;
      if (random() > random()) {
        b3 = true;
        if (c === 39) {
          if (random() > random()) b3 = false;
          else if (random() > random()) b3 = false;
        }
      }
      const at = lastOverlapping(n37, (q) => {
        const o = co[array2[q]];
        const me = co[n37];
        let b4 = false;
        if (c === 26) {
          if (me.rot === 90 && o.x > me.x) b4 = true;
          if (me.rot === -90 && o.x < me.x) b4 = true;
          if (me.rot === 0 && o.z < me.z) b4 = true;
          if (me.rot === 180 && o.z > me.z) b4 = true;
        } else {
          if (me.rot === 90 && o.z > me.z) b4 = true;
          if (me.rot === -90 && o.z < me.z) b4 = true;
          if (me.rot === 0 && o.x > me.x) b4 = true;
          if (me.rot === 180 && o.x < me.x) b4 = true;
        }
        if (b4 && array[array2[q]] === 1 && b3) {
          array[array2[q]] = 0;
          array[n37] = 4;
        }
        return b4;
      });
      insertAfter(n37, at);
    }
  }
  for (let n41 = 0; n41 < nob; ++n41) {
    if ((co[n41].sp >= 55 && co[n41].sp <= 65) || co[n41].sp === PILE) array2[n2++] = n41;
  }
  let n42 = 0, n43 = 0, n44 = 0, n45 = 0;
  const out = [];
  for (let n46 = 0; n46 < n2; ++n46) {
    const p = co[array2[n46]];
    const c = p.sp;
    if (c !== CHECKPOINT && c !== FIX_HOOP && c !== CHECKPOINT_2 && c !== CHECKPOINT_RAISED && c !== PILE) {
      const flag = ['', 'p', 'pt', 'pr', 'ph', 'pl', 'pr'][array[array2[n46]]] || '';
      out.push(`set(${c + 10},${p.x},${p.z},${p.rot})${flag}`);
    }
    if (c === CHECKPOINT || c === CHECKPOINT_2) {
      if (p.rot === 180) p.rot = 0;
      out.push(`chk(${c + 10},${p.x},${p.z},${p.rot})${p.wh !== 0 ? 'r' : ''}`);
    }
    if (c === CHECKPOINT_RAISED) {
      if (p.rot === 180) p.rot = 0;
      out.push(`chk(${c + 10},${p.x},${p.z},${p.rot},${p.y})${p.wh !== 0 ? 'r' : ''}`);
    }
    if (c === FIX_HOOP) out.push(`fix(${c + 10},${p.x},${p.z},${p.y},${p.rot})`);
    if (c === PILE) out.push(`pile(${p.srz},${p.srx},${p.sry},${p.x},${p.z})`);
    const r = R(array2[n46]);
    if (p.x + r > n42) n42 = p.x + r;
    if (p.x - r < n44) n44 = p.x - r;
    if (p.z + r > n43) n43 = p.z + r;
    if (p.z - r < n45) n45 = p.z - r;
  }
  const n47 = n44, n48 = n42;
  const n49 = trunc(fr((n48 - n47) / 4800.0)) + 1;
  const n50 = idiv(n49 * 4800 - (n48 - n47), 2);
  const mm = n47 - n50, i2 = n48 + n50, n51 = mm + 2400;
  const n52 = n45, n53 = n43;
  const n54 = trunc(fr((n53 - n52) / 4800.0)) + 1;
  const n55 = idiv(n54 * 4800 - (n53 - n52), 2);
  const i3 = n52 - n55, i4 = n53 + n55, n56 = i3 + 2400;
  return out.join('\r\n') + '\r\n'
    + `\r\nmaxl(${n54},${mm},${n56})\r\nmaxb(${n49},${i3},${n51})\r\nmaxr(${n54},${i2},${n56})\r\nmaxt(${n49},${i4},${n51})\r\n`;
}
