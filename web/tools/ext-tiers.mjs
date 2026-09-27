// First draft of web/ext/tiers.js from Extended's own car stats:
//   node web/tools/ext-tiers.mjs > web/ext/tiers.js
// Score = mean percentile of top speed (swits[.][2]), acceleration (sum of acelf),
// toughness (log maxmag) and damage dealt (outdam). S = maxmag at or above S_MAXMAG,
// the jump in the data from ~20000 to ~100000 (printed below, check it still holds).
// C/B/A cuts are the pair that puts most of cars 23-38 (the NFM2 cars) in their NFM2
// tier; the ones that still differ go to stderr. Hand edits to tiers.js win over this.
import fs from 'node:fs';
import { EXT_CARS } from '../ext/catalog.js';
import { NFM2_CCLASS, tierOfClass } from '../rivals.js';

const S_MAXMAG = 50000;
const N = EXT_CARS.length;
const src = (f) => fs.readFileSync(new URL(`../ext/${f}`, import.meta.url), 'utf8');
const mad = src('Madness.js'), xtg = src('xtGraphics.js');
const nested = (text, name) => [...text.match(new RegExp(`this\\.${name} = \\[(.*)\\];`))[1]
  .matchAll(/from\(\[([^\]]*)\]\)/g)].map((m) => m[1].split(',').map(Number)).slice(0, N);
const flat = (text, name) => text.match(new RegExp(`this\\.${name} = \\w+\\.from\\(\\[([^\\]]*)\\]\\)`))[1]
  .split(',').map(Number).slice(0, N);

const top = nested(mad, 'swits').map((s) => s[2]);
const acc = nested(mad, 'acelf').map((a) => a[0] + a[1] + a[2]);
const maxmag = flat(mad, 'maxmag');
const outdam = flat(xtg, 'outdam');

const rank = (v) => v.map((x) => (v.filter((y) => y < x).length + (v.filter((y) => y === x).length - 1) / 2) / (v.length - 1));
const parts = [rank(top), rank(acc), rank(maxmag.map(Math.log)), rank(outdam)];
const score = EXT_CARS.map((_, i) => parts.reduce((s, p) => s + p[i], 0) / parts.length);

const isS = maxmag.map((m) => m >= S_MAXMAG);
const want = (i) => (i >= 23 ? tierOfClass(NFM2_CCLASS[i - 23]) : null);
const cuts = [...new Set(score.filter((_, i) => !isS[i]))].sort((a, b) => a - b);
let best = { hits: -1, lo: 0, hi: 0 };
for (const lo of cuts) for (const hi of cuts) {
  if (hi <= lo) continue;
  const t = (s) => (s < lo ? 'C' : s < hi ? 'B' : 'A');
  const hits = EXT_CARS.filter((_, i) => want(i) && !isS[i] && t(score[i]) === want(i)).length;
  if (hits > best.hits) best = { hits, lo, hi };
}
const tier = EXT_CARS.map((_, i) => (isS[i] ? 'S' : score[i] < best.lo ? 'C' : score[i] < best.hi ? 'B' : 'A'));

console.error('maxmag, sorted:', [...maxmag].sort((a, b) => a - b).join(' '));
for (let i = 23; i < N; i++) if (tier[i] !== want(i)) console.error(`differs from NFM2: ${i} ${EXT_CARS[i]} ${tier[i]} (NFM2 ${want(i)})`);
console.log(`// Extended's car tiers for the Rivals screen (rivals.js): C / B / A as the base game's
// classes, S for the ones that shrug off damage (maxmag far above the rest). Drafted by
// web/tools/ext-tiers.mjs from the cars' stats; hand edits here win over the script.
export const EXT_TIER = [
${EXT_CARS.map((n, i) => `  '${tier[i]}',   // ${i} ${n}  score ${score[i].toFixed(2)} maxmag ${maxmag[i]}`).join('\n')}
];`);
