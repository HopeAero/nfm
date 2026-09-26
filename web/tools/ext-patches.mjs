// Named patches to J2JS's output, for what the base port learned about its
// own ContO/Plane/Medium (TASKS.md "Base-port parity audit").
//
//   node web/tools/ext-patches.mjs           apply (after every J2JS regeneration)
//   node web/tools/ext-patches.mjs --check   exit 1 unless every patch is in place
//
// Each patch replaces exact generated text and says so at the site
// (`// ext-patch <name>:`). It throws if the text it expects is gone, so a
// regeneration that changes a site cannot slip through unpatched or
// half-patched. Two kinds:
// - cost (face-order, rot-hoist): an equivalent that is cheaper; results
//   unchanged, so draw.test.js (every ContO.d/Plane.d call against
//   madness.jar) and the trace replays must still pass after `apply`;
// - base-port features the jar lacks (sparks-*): they call into ContO and
//   change what is drawn, never the physics -- no sim state, no sim randoms.

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
const FACE_ORDER_REPLACE = `        // port: the face order is kept per object -- every slot is rewritten below
        let ai2 = this.faceOrder?.length === this.npl ? this.faceOrder : (this.faceOrder = intArray(this.npl));
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


/**
 * The base Mad's sparks (NFM2's newer drive/colide; the Extended jar has none):
 * a capsized car scraping the ground or a slope, a car held against a wall for a
 * second tick (crank, as the base), and car-to-car hits on a draw-bank roll. The
 * base's extra `random() > random()` crank on skd 5 walls is left out: it would
 * draw sim randoms and change Extended's race.
 */
const SPARK = (idx) => `conto.sprk(af[${idx}], af3[${idx}], af2[${idx}], this.scx[${idx}], this.scy[${idx}], this.scz[${idx}]`;
const SKD01 = '(trackers.skd[j6] === 0 || trackers.skd[j6] === 1)';
function after(name, find, add) { return { name, file: 'Madness.js', find, replace: find + add }; }
function wall(n, find) {
  return after(`sparks-wall${n}`, find,
    `            // ext-patch sparks-wall${n}: the base's wall sparks, from the second tick against it\n` +
    `            if (trackers.skd[j6] !== 2) ++this.crank[${n}][i7];\n` +
    `            if (this.crank[${n}][i7] > 1) ${SPARK('i7')}, 0);\n`);
}
const SPARK_PATCHES = [
  after('sparks-state', '    let aflag = new Array(4).fill(false);\n',
    '    // ext-patch sparks-state: the base Mad\'s scrape flags and wall counters (web/tools/ext-patches.mjs)\n' +
    '    const gscr = new Array(4).fill(false);\n' +
    '    this.crank ??= [0, 1, 2, 3].map(() => new Int32Array(4));\n' +
    '    this.lcrank ??= [0, 1, 2, 3].map(() => new Int32Array(4));\n'),
  after('sparks-ground', '        this.regy(l6, Math.abs(fr(this.scy[l6] * f12)), conto, 1);\n',
    '        if (this.capsized) gscr[l6] = true;   // ext-patch sparks-ground\n'),
  { name: 'sparks-scrape', file: 'Madness.js',
    find: '      do {\n        if ((((((!aflag[i7] && ',
    replace: '      do {\n' +
      '        // ext-patch sparks-scrape: a capsized car scraping the ground under a piece\n' +
      `        if (gscr[i7] && ${SKD01} && af[i7] > trackers.x[j6] - trackers.radx[j6] && af[i7] < trackers.x[j6] + trackers.radx[j6] && af2[i7] > trackers.z[j6] - trackers.radz[j6] && af2[i7] < trackers.z[j6] + trackers.radz[j6]) {\n` +
      `          ${SPARK('i7')}, 1);\n` +
      '        }\n' +
      '        if ((((((!aflag[i7] && ' },
  after('sparks-flat', '            af3[i7] = fr(trackers.y[j6]);\n',
    `            if (this.capsized && ${SKD01}) {   // ext-patch sparks-flat\n` +
    `              ${SPARK('i7')}, 1);\n` +
    '            }\n'),
  wall(0, '            af2[i7] = fr((i32(trackers.z[j6] + trackers.radz[j6])));\n            let f15 = 0.0;\n'),
  wall(1, '            af2[i7] = fr((i32(trackers.z[j6] - trackers.radz[j6])));\n            let f16 = 0.0;\n'),
  wall(2, '            af[i7] = fr((i32(trackers.x[j6] + trackers.radx[j6])));\n            let f17 = 0.0;\n'),
  wall(3, '            af[i7] = fr((i32(trackers.x[j6] - trackers.radx[j6])));\n            let f18 = 0.0;\n'),
  { name: 'sparks-slope-z', file: 'Madness.js',
    find: '              this.gtouch = false;\n              if (!flag5 && (this.roadtyp !== 0)) {\n                let f22 = ',
    replace: '              this.gtouch = false;\n' +
      `              if (this.capsized && ${SKD01}) {   // ext-patch sparks-slope-z\n` +
      `                ${SPARK('i7')}, 1);\n` +
      '              }\n' +
      '              if (!flag5 && (this.roadtyp !== 0)) {\n                let f22 = ' },
  { name: 'sparks-slope-x', file: 'Madness.js',
    find: '            this.gtouch = false;\n            if (!flag5 && (this.roadtyp !== 0)) {\n              let f26 = ',
    replace: '            this.gtouch = false;\n' +
      `            if (this.capsized && ${SKD01}) {   // ext-patch sparks-slope-x\n` +
      `              ${SPARK('i7')}, 1);\n` +
      '            }\n' +
      '            if (!flag5 && (this.roadtyp !== 0)) {\n              let f26 = ' },
  after('sparks-decay', '    if (l6 === 4) {\n      this.mtouch = true;\n    }\n',
    '    // ext-patch sparks-decay: a wall counter that did not move this tick restarts (the base Mad)\n' +
    '    for (let n100 = 0; n100 < 4; ++n100) {\n' +
    '      for (let n101 = 0; n101 < 4; ++n101) {\n' +
    '        if (this.crank[n100][n101] === this.lcrank[n100][n101]) this.crank[n100][n101] = 0;\n' +
    '        this.lcrank[n100][n101] = this.crank[n100][n101];\n' +
    '      }\n' +
    '    }\n'),
  ...['madness.im', 'this.im'].map((who, n) => after(`sparks-hit${n}`,
    `              madness.regy(l, fr((Math.imul(this.revlift[this.cn], 7))), conto1, ${who});\n              if (madness.colidim) {\n                madness.colidim = false;\n              }\n`,
    `              if (conto1.sprkRoll()) {   // ext-patch sparks-hit${n}: the base's car-to-car sparks\n` +
    '                conto1.sprk(fr(fr(af[k] + af4[l]) / 2.0), fr(fr(af2[k] + af5[l]) / 2.0), fr(fr(af3[k] + af6[l]) / 2.0), fr(fr(madness.scx[l] + this.scx[k]) / 4.0), fr(fr(madness.scy[l] + this.scy[k]) / 4.0), fr(fr(madness.scz[l] + this.scz[k]) / 4.0), 2);\n' +
    '              }\n')),
];


/**
 * Skid dust thinned to the base's rate for the base dust ring (basedust.js).
 * Extended rolls its skid puffs at 92.5/91.5/93%, where the base Mad rolls
 * 35/20/40%: its own four slots only took a puff when one was free, but every
 * call is a puff in the base's ring, which then drew ~3 a tick against the
 * base's ~1. The sim roll stays; ContO.dust keeps the puff with the ratio on a
 * draw-bank roll.
 */
const DUST_RATE = [
  ['skid', 'fr(f10 * this.simag[this.cn]), true', '0.35 / 0.925'],
  ['road1', 'fr(1.100000023841858 * this.simag[this.cn]), false', '0.2 / 0.915'],
  ['road23', 'fr(1.149999976158142 * this.simag[this.cn]), false', '0.4 / 0.93'],
].map(([name, args, keep]) => {
  const call = `conto.dust(l5, af[l5], af3[l5], af2[l5], this.scx[l5], this.scz[l5], ${args}, trunc(this.tilt));\n`;
  return { name: `dust-rate-${name}`, file: 'Madness.js', find: call,
    replace: `conto.dustkeep = ${keep};   // ext-patch dust-rate-${name}: the base Mad's odds for the ring\n              ` + call };
});

/**
 * RadicalMod's constructor reads its .radq synchronously and ModSlayer
 * pre-renders the whole song for a javax.sound line the browser does not have.
 * web/ext/radmusic.js plays the .mod through the base port's BassoonTracker
 * instead, fetching it when loadMod asks; the constructor only keeps the path
 * (loaded = 1, "file found", as the jar leaves it).
 */
const RADMOD_FIND = `    this.loaded = 1;
    try {
      let url = new URL(applet.getCodeBase(), s);
      let zipinputstream = new ZipInputStream(url.openStream());
      let zipentry = zipinputstream.getNextEntry();
      let i = i32(zipentry.getSize());
      this.modf = new Int8Array(i);
      let j = 0;
      while (i > 0) {
        let k = zipinputstream.read(this.modf, j, i);
        j = i32(j + k);
        i = i32(i - k);
      }
    } catch (exception) {
      System.out.println('Error loading Mod from zip file: ' + exception);
      this.loaded = 0;
    }
`;
const RADMOD_REPLACE = `    this.loaded = 1;
    // ext-patch radmod-lazy: web/ext/radmusic.js reads the .radq when loadMod asks
    this.path = s;
`;

/**
 * Trackers allocates a 3-int colour row per tracker slot up front, 2 x 67000 of
 * them: ~134k live typed arrays every major GC walks, for stages that use a few
 * thousand. ContO's trackerRow grows both lists to the slot it is about to fill;
 * a row it creates is zeros, as an untouched preallocated one was, and rows are
 * kept across stages, as before.
 */
const TRACKER_ROWS_FIND = `    this.oc = objArray(67000).map(() => intArray(3));
    this.c = objArray(67000).map(() => intArray(3));
`;
const TRACKER_ROWS_REPLACE = `    // ext-patch tracker-rows: rows added by ContO's trackerRow as slots fill
    this.oc = [];
    this.c = [];
`;

/**
 * Record.rec shifts the replay's six car snapshots every 50 ticks by copying
 * each one again: 6 ContO copies per car, a Plane with nine typed arrays per
 * face, ~20k Planes per shift with 19 cars. They live long enough to reach the
 * old generation, so they drive the major GCs (the base port measured the same
 * site at ~74% of its allocation; web/Record.js "ghosts"). A copy of a copy
 * holds the same geometry, and these snapshots are only ever read by copying
 * them again (the replay, starcar), so the older five move instead; only the
 * newest is copied. What a copy recomputes (c/hsb from m.snap) is recomputed
 * by the replay's own copy.
 */
const RECORD_SHIFT_FIND = `        this.car[i2][i] = new ContO(1, this.car[i32(i2 + 1)][i], 0, 0, 0, 0);
`;
const RECORD_SHIFT_REPLACE = `        // ext-patch record-shift: move the snapshot, do not copy it again
        this.car[i2][i] = this.car[i32(i2 + 1)][i];
`;

/**
 * Madness.drive sweeps every tracker twice per car per tick: the road type under
 * the car (last hit wins) and the wheel collisions (4 wheels x every tracker).
 * With 19 cars that was most of drive(). Both now visit only the trackers that
 * can hold the point(s), in the same ascending order, through web/ext/trackgrid.js
 * (race.js builds the grid after loadstage); the wheel sweep falls back to every
 * tracker after the last one visited as soon as a pushed wheel leaves the safe box.
 * No grid (the jar's own tests), a stale one or no match: the full sweep.
 */
const SWEEP_PATCHES = [
  { name: 'sweep-import', file: 'Madness.js',
    find: `import { Color } from './jawt.js';
`,
    replace: `import { Color } from './jawt.js';
// ext-patch sweep-import: the grid behind road-cell and wheel-sweep
import { WheelSweep, nearTrackers } from './trackgrid.js';
const WHEELS = new WheelSweep();
` },
  { name: 'road-cell', file: 'Madness.js',
    find: `    for (let l4 = 0; l4 < trackers.nt; l4 = i32(l4 + 1)) {
`,
    replace: `    // ext-patch road-cell: only the trackers under the car, ascending (trackgrid.js)
    const road = nearTrackers(trackers, conto.x, conto.z, 0);
    for (let q = 0, qn = road ? road.length : trackers.nt; q < qn; q = i32(q + 1)) {
      const l4 = road ? road[q] : q;
` },
  { name: 'wheel-sweep', file: 'Madness.js',
    find: `    l6 = 0;
    for (let j6 = 0; j6 < coldetection; j6 = i32(j6 + 1)) {
`,
    replace: `    l6 = 0;
    // ext-patch wheel-sweep: the trackers near the wheels, ascending (trackgrid.js WheelSweep)
    const sweep = WHEELS.begin(trackers, af, af2, coldetection);
    for (let j6 = sweep.next(); j6 >= 0; j6 = sweep.next()) {
` },
];

/**
 * Settings -> Replay recording off (race.js sets record.ghosts = false, as the
 * base port's ?ghost=0): no car snapshot is copied at all; Instant Replay says
 * so instead of playing.
 */
const RECORD_GHOSTS_FIND = `      this.car[5][i] = new ContO(1, conto, 0, 0, 0, 0);
`;
const RECORD_GHOSTS_REPLACE = `      // ext-patch record-ghosts: no snapshot when the replay is not recorded (race.js)
      if (this.ghosts !== false) this.car[5][i] = new ContO(1, conto, 0, 0, 0, 0);
`;

/**
 * Extended new cars (web/ext/newcars*.js): the per-car tables grow right after the
 * constructors fill them; a model at NEW_BASE (200)+ is a car; and such a model honours
 * ScaleX/Y/Z as the base ContO does (Car Maker cars use them; the jar has no Scale
 * directive, so stock codes keep ignoring the lines). Scale 1 leaves every
 * coordinate as it was: fr(x * 1.0) === x for a float32 x.
 */
const NEWCAR_PATCHES = [
  { name: 'newcar-import', file: 'GameSparker.js',
    find: `import { Bots } from './Bots.js';\n`,
    replace: `import { Bots } from './Bots.js';\n// ext-patch newcar-import\nimport { growMadness, growXt } from './newcars-grow.js';\n` },
  { name: 'newcar-grow-xt', file: 'GameSparker.js',
    find: `    let xtgraphics = new xtGraphics(medium, this.rd, this.sg, this);\n`,
    replace: `    let xtgraphics = new xtGraphics(medium, this.rd, this.sg, this);\n    growXt(xtgraphics);   // ext-patch newcar-grow-xt\n` },
  { name: 'newcar-grow-mad', file: 'GameSparker.js',
    find: `      amadness[l] = new Madness(medium, record, xtgraphics, l);\n`,
    replace: `      amadness[l] = new Madness(medium, record, xtgraphics, l);\n      growMadness(amadness[l]);   // ext-patch newcar-grow-mad\n` },
  { name: 'newcar-isacar', file: 'ContO.js',
    find: `    if (((code < 39) || (((code >= 78) && (code < 117)))) || (code === 64)) {\n`,
    replace: `    // ext-patch newcar-isacar: a new car (NEW_BASE+, web/ext/newcars.js) is a car\n    if (((code < 39) || (((code >= 78) && (code < 117)))) || (code === 64) || (code >= 200)) {\n` },
  { name: 'newcar-scale-init', file: 'ContO.js',
    find: `    let bool2 = false;\n    try {\n      let datainputstream = new DataInputStream(new ByteArrayInputStream(abyte0));\n`,
    replace: `    let bool2 = false;\n    this.scl = [1.0, 1.0, 1.0];   // ext-patch newcar-scale-init\n    try {\n      let datainputstream = new DataInputStream(new ByteArrayInputStream(abyte0));\n` },
  { name: 'newcar-scale-parse', file: 'ContO.js',
    find: `        if (s1.startsWith('iwid')) {\n`,
    replace: `        // ext-patch newcar-scale-parse: the base ContO's ScaleX/Y/Z, new cars only\n` +
      `        if (code >= 200 && s1.startsWith('Scale')) {\n` +
      `          const ax = 'XYZ'.indexOf(s1[5]);\n` +
      `          if (ax >= 0) this.scl[ax] = fr(fr(this.getvalue('Scale' + s1[5], s1, 0)) / 100.0);\n` +
      `        }\n` +
      `        if (s1.startsWith('iwid')) {\n` },
  { name: 'newcar-scale-p', file: 'ContO.js',
    find: `            ai[i] = trunc((fr((fr(fr(this.getvalue('p', s1, 0)) * this.div)) * this.iwid)));\n` +
      `            ai2[i] = trunc((fr(fr(this.getvalue('p', s1, 1)) * this.div)));\n` +
      `            ai3[i] = trunc((fr(fr(this.getvalue('p', s1, 2)) * this.div)));\n`,
    replace: `            // ext-patch newcar-scale-p: x, y, z times ScaleX/Y/Z (1 but on a new car)\n` +
      `            ai[i] = trunc(fr((fr((fr(fr(this.getvalue('p', s1, 0)) * this.div)) * this.iwid)) * this.scl[0]));\n` +
      `            ai2[i] = trunc(fr((fr(fr(this.getvalue('p', s1, 1)) * this.div)) * this.scl[1]));\n` +
      `            ai3[i] = trunc(fr((fr(fr(this.getvalue('p', s1, 2)) * this.div)) * this.scl[2]));\n` },
  { name: 'newcar-scale-w', file: 'ContO.js',
    find: `          this.keyx[j] = trunc((fr(fr(this.getvalue('w', s1, 0)) * this.div)));\n` +
      `          this.keyz[j] = trunc((fr(fr(this.getvalue('w', s1, 2)) * this.div)));\n` +
      `          j = i32(j + 1);\n` +
      `          wheels.make(this.m, this.t, this.p, this.npl, trunc((fr((fr(fr(this.getvalue('w', s1, 0)) * this.div)) * this.iwid))), trunc((fr(fr(this.getvalue('w', s1, 1)) * this.div))), trunc((fr(fr(this.getvalue('w', s1, 2)) * this.div))), `,
    replace: `          // ext-patch newcar-scale-w: the wheel's position times ScaleX/Y/Z; its size not (as the base)\n` +
      `          this.keyx[j] = trunc(fr((fr(fr(this.getvalue('w', s1, 0)) * this.div)) * this.scl[0]));\n` +
      `          this.keyz[j] = trunc(fr((fr(fr(this.getvalue('w', s1, 2)) * this.div)) * this.scl[2]));\n` +
      `          j = i32(j + 1);\n` +
      `          wheels.make(this.m, this.t, this.p, this.npl, trunc(fr((fr((fr(fr(this.getvalue('w', s1, 0)) * this.div)) * this.iwid)) * this.scl[0])), trunc(fr((fr(fr(this.getvalue('w', s1, 1)) * this.div)) * this.scl[1])), trunc(fr((fr(fr(this.getvalue('w', s1, 2)) * this.div)) * this.scl[2])), ` },
  // The car select's normal mode (Free Play) steps sc[0] with ++/-- and moves
  // aconto[sc[0]] in the same call: 38 -> 39 would move a road piece, 200 -> 199 a hole.
  // nextCar hops 38 <-> NEW_BASE and stops at both ends. Anchored on the second import:
  // ext-ident puts its own right after the first.
  { name: 'newcar-carselect-import', file: 'xtGraphics.js',
    find: `import { RadicalMidi } from './RadicalMidi.js';\n`,
    replace: `import { RadicalMidi } from './RadicalMidi.js';\n// ext-patch newcar-carselect-import\nimport { NEW_BASE, nextCar } from './newcars.js';\n` },
  // the car select puts the 39 on its floor (groundlevel -34: the shadow under the car)
  { name: 'newcar-carselect-ground', file: 'xtGraphics.js',
    find: `    for (let a = 0; a < 39; a = i32(a + 1)) {\n      aconto[a].groundlevel = -34;\n    }\n`,
    replace: `    for (let a = 0; a < 39; a = i32(a + 1)) {\n      aconto[a].groundlevel = -34;\n    }\n    for (let a = NEW_BASE; aconto[a]; a++) aconto[a].groundlevel = -34;   // ext-patch newcar-carselect-ground\n` },
  { name: 'newcar-carselect-next', file: 'xtGraphics.js',
    find: `              if (!this.careermode) {\n                let sc = this.sc;\n                let n15 = 0;\n                ++sc[n15];\n`,
    replace: `              if (!this.careermode) {\n                let sc = this.sc;\n                let n15 = 0;\n                sc[n15] = nextCar(sc[n15], 1);   // ext-patch newcar-carselect-next\n` },
  { name: 'newcar-carselect-back', file: 'xtGraphics.js',
    find: `            } else if (!this.careermode) {\n              let sc3 = this.sc;\n              let n18 = 0;\n              --sc3[n18];\n`,
    replace: `            } else if (!this.careermode) {\n              let sc3 = this.sc;\n              let n18 = 0;\n              sc3[n18] = nextCar(sc3[n18], -1);   // ext-patch newcar-carselect-back\n` },
];

export const PATCHES = [
  { name: 'record-ghosts', file: 'Record.js', find: RECORD_GHOSTS_FIND, replace: RECORD_GHOSTS_REPLACE },
  { name: 'record-shift', file: 'Record.js', find: RECORD_SHIFT_FIND, replace: RECORD_SHIFT_REPLACE },
  { name: 'tracker-rows', file: 'Trackers.js', find: TRACKER_ROWS_FIND, replace: TRACKER_ROWS_REPLACE },
  { name: 'radmod-lazy', file: 'RadicalMod.js', find: RADMOD_FIND, replace: RADMOD_REPLACE },
  { name: 'face-order', file: 'ContO.js', find: FACE_ORDER_FIND, replace: FACE_ORDER_REPLACE },
  { name: 'rot-hoist', file: 'ContO.js', ...rotHoist('this.m') },
  { name: 'rot-hoist', file: 'Plane.js', ...rotHoist('this.m') },
  { name: 'rot-hoist', file: 'Medium.js', ...rotHoist('this') },
  ...SPARK_PATCHES,
  ...DUST_RATE,
  ...SWEEP_PATCHES,
  ...NEWCAR_PATCHES,
];

/** 'applied' | 'pending' | throws when neither form is there exactly once. */
export function state(src, p) {
  const a = src.split(p.replace).length - 1;
  // an insertion keeps its anchor inside the replacement: those do not count
  const f = src.split(p.find).length - 1 - (p.replace.includes(p.find) ? a : 0);
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
