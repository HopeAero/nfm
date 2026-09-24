// Named patches to J2JS's output, for what the base port learned about its
// own ContO/Plane/Medium (TASKS.md "Base-port parity audit").
//
//   node web/tools/ext-patches.mjs           apply (after every J2JS regeneration)
//   node web/tools/ext-patches.mjs --check   exit 1 unless every patch is in place
//
// Each patch replaces exact generated text with an equivalent that is cheaper,
// and says so at the site (`// ext-patch <name>:`). It throws if the text it
// expects is gone, so a regeneration that changes a site cannot slip through
// unpatched or half-patched. They change cost, never results: draw.test.js
// (every ContO.d/Plane.d call against madness.jar) and the trace replays must
// still pass after `apply`.

import fs from 'node:fs';

const EXT = new URL('../ext/', import.meta.url);

/**
 * The rank count in ContO.d is a stable sort by Plane.av, descending, ties by
 * index: for a pair k3 < i5 the smaller av gains rank, equal avs rank the later
 * face higher, and faces are drawn by ascending rank. The base port measured
 * the O(npl^2) loop at ~12% of a frame and replaced it the same way.
 */
const FACE_ORDER_FIND = `        let ai = intArray(this.npl);
        let ai2 = intArray(this.npl);
        let i4 = 0;
        do {
          if (((this.stg[i4] !== 0) && !this.teleported) && !this.m.effect[11]) {
            this.pdust(i4, g, -1);
          }
        } while (++i4 < 4);
        for (let j4 = 0; j4 < this.npl; j4 = i32(j4 + 1)) {
          ai[j4] = 0;
        }
        for (let k3 = 0; k3 < this.npl; k3 = i32(k3 + 1)) {
          for (let i5 = i32(k3 + 1); i5 < this.npl; i5 = i32(i5 + 1)) {
            if (this.p[k3].av !== this.p[i5].av) {
              if (this.p[k3].av < this.p[i5].av) {
                let array = ai;
                let n = k3;
                ++array[n];
              } else {
                let array2 = ai;
                let n2 = i5;
                ++array2[n2];
              }
            } else if (k3 > i5) {
              let array3 = ai;
              let n3 = k3;
              ++array3[n3];
            } else {
              let array4 = ai;
              let n4 = i5;
              ++array4[n4];
            }
          }
          ai2[ai[k3]] = k3;
        }
`;
const FACE_ORDER_REPLACE = `        let ai2 = intArray(this.npl);
        let i4 = 0;
        do {
          if (((this.stg[i4] !== 0) && !this.teleported) && !this.m.effect[11]) {
            this.pdust(i4, g, -1);
          }
        } while (++i4 < 4);
        // ext-patch face-order: the jar's pairwise rank count is this stable sort --
        // av descending, ties by index -- in O(n log n) (web/tools/ext-patches.mjs)
        for (let j4 = 0; j4 < this.npl; j4 = i32(j4 + 1)) {
          ai2[j4] = j4;
        }
        {
          const p = this.p;
          ai2.sort((a, b) => (p[b].av - p[a].av) || (a - b));
        }
`;

/** rot(): the angle is fixed for the whole loop, so cos/sin come out of it. Pure table reads: same values. */
function rotHoist(trig) {
  const find = `  rot(ai, ai1, i, j, k, l) {
    if (k !== 0) {
      for (let i2 = 0; i2 < l; i2 = i32(i2 + 1)) {
        let j2 = ai[i2];
        let k2 = ai1[i2];
        ai[i2] = i32(i + trunc((fr((fr(fr(((i32(j2 - i)))) * ${trig}.cos(k))) - (fr(fr(((i32(k2 - j)))) * ${trig}.sin(k)))))));
        ai1[i2] = i32(j + trunc((fr((fr(fr(((i32(j2 - i)))) * ${trig}.sin(k))) + (fr(fr(((i32(k2 - j)))) * ${trig}.cos(k)))))));
      }
    }
  }
`;
  const replace = `  rot(ai, ai1, i, j, k, l) {
    if (k !== 0) {
      // ext-patch rot-hoist: one table read per rotation, not four per vertex (web/tools/ext-patches.mjs)
      const cos = ${trig}.cos(k), sin = ${trig}.sin(k);
      for (let i2 = 0; i2 < l; i2 = i32(i2 + 1)) {
        let j2 = ai[i2];
        let k2 = ai1[i2];
        ai[i2] = i32(i + trunc((fr((fr(fr(((i32(j2 - i)))) * cos)) - (fr(fr(((i32(k2 - j)))) * sin))))));
        ai1[i2] = i32(j + trunc((fr((fr(fr(((i32(j2 - i)))) * sin)) + (fr(fr(((i32(k2 - j)))) * cos))))));
      }
    }
  }
`;
  return { find, replace };
}

export const PATCHES = [
  { name: 'face-order', file: 'ContO.js', find: FACE_ORDER_FIND, replace: FACE_ORDER_REPLACE },
  { name: 'rot-hoist', file: 'ContO.js', ...rotHoist('this.m') },
  { name: 'rot-hoist', file: 'Plane.js', ...rotHoist('this.m') },
  { name: 'rot-hoist', file: 'Medium.js', ...rotHoist('this') },
];

/** 'applied' | 'pending' | throws when neither form is there exactly once. */
export function state(src, p) {
  const a = src.split(p.replace).length - 1, f = src.split(p.find).length - 1;
  if (a === 1 && f === 0) return 'applied';
  if (a === 0 && f === 1) return 'pending';
  throw new Error(`ext-patch ${p.name} (${p.file}): generated text not found as expected (applied ${a}, original ${f}) -- update the patch`);
}

if (process.argv[1] && process.argv[1].endsWith('ext-patches.mjs')) {
  const check = process.argv.includes('--check');
  let bad = 0;
  for (const p of PATCHES) {
    const url = new URL(p.file, EXT);
    // generated files are LF; read and write them as such
    const src = fs.readFileSync(url, 'utf8');
    const s = state(src, p);
    if (check) {
      if (s !== 'applied') { bad++; console.log(`pending: ${p.name} in ${p.file}`); }
      continue;
    }
    if (s === 'pending') fs.writeFileSync(url, src.replace(p.find, p.replace));
    console.log(`${p.name} ${p.file}: ${s === 'pending' ? 'applied' : 'already applied'}`);
  }
  if (bad) process.exit(1);
}
