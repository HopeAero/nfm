// Free Play's Rivals screen, NFM2 and Extended: how many cars race, and which.
// After the stage select (launcher.js for NFM2, ext/menus.js for Extended). The
// game still draws its own field (sortcars); pickRivals then rewrites xt.sc[1..n-1]
// from the player's pool and pinned slots. sc[0] is the player's car.

import { tr } from './i18n.js';

export const RIVALS_KEY = { nfm2: 'nfm.rivals.nfm2', ext: 'nfm.rivals.ext' };

// CarDefine.cclass of the 16 stock cars (CarDefine.js:49): the game's Class C .. Class A as 0-4
export const NFM2_CCLASS = [0, 0, 0, 0, 0, 1, 2, 2, 2, 2, 3, 4, 4, 4, 4, 4];
/** Class C and B&C -> C, B and A&B -> B, A -> A: six, five and five of the stock cars. */
export const tierOfClass = (c) => (c <= 1 ? 'C' : c <= 3 ? 'B' : 'A');

export const defaultRivals = (nCars) =>
  ({ count: 7, mode: 'game', pool: Array.from({ length: nCars }, (_, i) => i), fixed: [] });

/** A stored or URL config, repaired to something raceable; null if it is not a config at all. */
export function parseRivals(text, nCars, maxCount) {
  let o;
  try { o = JSON.parse(text); } catch { return null; }
  if (!o || typeof o !== 'object' || Array.isArray(o)) return null;
  const car = (v) => Number.isInteger(v) && v >= 0 && v < nCars;
  return {
    count: Number.isInteger(o.count) ? Math.max(1, Math.min(maxCount, o.count)) : Math.min(7, maxCount),
    mode: o.mode === 'pool' ? 'pool' : 'game',
    pool: Array.isArray(o.pool) ? [...new Set(o.pool.filter(car))] : [],
    fixed: Array.isArray(o.fixed) ? o.fixed.slice(0, maxCount - 1).map((v) => (car(v) ? v : null)) : [],
  };
}

export function loadRivals(key, nCars, maxCount) {
  let text = null;
  try { text = localStorage.getItem(key); } catch { /* private window, blocked storage */ }
  return parseRivals(text, nCars, maxCount) ?? defaultRivals(nCars);
}

export function saveRivals(key, cfg) {
  try { localStorage.setItem(key, JSON.stringify(cfg)); } catch { /* not remembered, still raced */ }
}

/**
 * The grid for slots 1..n-1. 'pool' draws from the pool with `random` (the game's
 * seeded one, so a replay draws the same), no repeats until every pool car is used;
 * an empty pool is the game's pick. Pinned slots (fixed[k-1]) win in both modes.
 */
export function pickRivals({ mode, pool, fixed }, gameSc, n, random) {
  const sc = Array.from(gameSc);
  let bag = [];
  for (let k = 1; k < n; k++) {
    if (fixed[k - 1] != null) { sc[k] = fixed[k - 1]; continue; }
    if (mode !== 'pool' || !pool.length) continue;
    if (!bag.length) bag = [...pool];
    sc[k] = bag.splice(Math.floor(random() * bag.length), 1)[0];
  }
  return sc;
}

/** A tier button: tick every car of the tier if any is unticked, else untick them all. */
export function toggleTier(pool, cars, tier) {
  const ids = cars.filter((c) => c.tier === tier).map((c) => c.i);
  if (ids.every((i) => pool.includes(i))) return pool.filter((i) => !ids.includes(i));
  return [...new Set([...pool, ...ids])];
}
