// The Car Maker's Extended tab, as lines in the same .rad (one car, two games). No parser
// -- base ContO, Extended's ContO, CarDefine, the Car Maker, the Java -- acts on a line
// starting with `e`, so NFM 2 ignores them and only carFromRad (newcars-stats.js) reads
// them. Out-of-range values are ignored, never clamped: the fallback applies.
// Spec: docs/superpowers/specs/2026-09-26-ext-carmaker-design.md
import { findLine, argsOf, setLine, readStats, readPhysics, DEFAULT_STATS, DEFAULT_PHYSICS,
  STAT_MIN, STAT_MAX } from '../careditor/rad.js';

export const HEALTH = { min: 50, max: 300 };
export const DAMAGE = { min: 50, max: 200 };
const PHYS_N = 11;   // physics() 0-10, the handling sliders; crash look, engine, actmag stay shared

// exactly n integer arguments, each in [lo, hi]; undefined when the line is missing, null when invalid
function ints(text, name, n, lo, hi) {
  const l = findLine(text, name);
  if (!l) return undefined;
  const a = argsOf(l.line);
  if (a.length !== n || !a.every((s) => /^-?\d+$/.test(s))) return null;
  const v = a.map(Number);
  return v.every((x) => x >= lo && x <= hi) ? v : null;
}

export function readExt(text) {
  const invalid = [];
  const get = (name, n, lo, hi) => {
    const v = ints(text, name, n, lo, hi);
    if (v === null) invalid.push(name);
    return v ?? null;
  };
  const one = (v) => (v ? v[0] : null);
  const special = one(get('extspecial', 1, 0, 38));
  const stat = get('extstat', 5, STAT_MIN, STAT_MAX);
  const phys = get('extphysics', PHYS_N, 0, 100);
  const health = one(get('exthealth', 1, HEALTH.min, HEALTH.max));
  const damage = one(get('extdamage', 1, DAMAGE.min, DAMAGE.max));
  return { special, stat, phys, health, damage, invalid };
}

export function removeLine(text, name) {
  return text.split('\n').filter((l) => !l.trim().startsWith(name + '(')).join('\n');
}

export const writeSpecial = (text, n) =>
  (n === null ? removeLine(text, 'extspecial') : setLine(text, 'extspecial', `extspecial(${n})`));

export const writeOwnStats = (text, stat) => setLine(text, 'extstat', `extstat(${stat.join(',')})`);
export const writeOwnPhys = (text, phys) => setLine(text, 'extphysics', `extphysics(${phys.slice(0, PHYS_N).join(',')})`);

/** Own: copy NFM 2's stats and handling as Extended's; Same: drop both copies. */
export function setOwn(text, on) {
  if (!on) return removeLine(removeLine(text, 'extstat'), 'extphysics');
  const stat = readStats(text) || DEFAULT_STATS;
  const phys = (readPhysics(text) || DEFAULT_PHYSICS).phys;
  return writeOwnPhys(writeOwnStats(text, stat), phys);
}

export const writePercent = (text, name, p) => (p === 100 ? removeLine(text, name) : setLine(text, name, `${name}(${p})`));

/** The text CarDefine.loadstat should see for Extended: own stats and handling, when both are valid. */
export function forLoadstat(text) {
  const { stat, phys } = readExt(text);
  if (!stat || !phys) return text;
  const base = readPhysics(text) || DEFAULT_PHYSICS;
  const vals = [...phys, ...base.crash, base.engsel, base.actmag];
  return setLine(setLine(text, 'stat', `stat(${stat.join(',')})`), 'physics', `physics(${vals.join(',')})`);
}
