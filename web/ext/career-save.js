// Extended's career progress in localStorage, standing in for the jar's
// data/Files/savedata.radq (GameSparker.writedata / readdata, which need
// ZipOutputStream and a file system). The same fields, as JSON rather than the
// jar's checksummed ud.txt lines:
//   unlocked, kills, wins, changers (statchangers), usercar (lastcar), laststage,
//   per car: level, exp (the player's Madness), statpoints, the six ai*sp,
//   killscn, winscn, extpoints; boncomp[0..5]; carpoints (cpoints);
//   specialstats (special(), through statsalc); rebsp/xbsp (bsp()).
// The jar's reader stops at the first bsp() line (Integer.valueOf on "1.0"),
// losing every later special() and bsp(); this store keeps them all.

const KEY = 'nfm.ext.career';
const CARS = 39;
const AI = ['aitssp', 'aiaccsp', 'aigripsp', 'aistusp', 'aistrsp', 'aiendsp'];

/** The save, as the jar's writedata would write it. */
export function snapshot(xt, cp, m) {
  const car = [];
  for (let a = 0; a < CARS; a++) {
    car.push({
      level: m.level[a], exp: m.exp[a], statpoints: xt.statpoints[a],
      ai: AI.map((k) => m[k][a]), killscn: xt.killscn[a], winscn: xt.winscn[a], extpoints: xt.extpoints[a],
      special: [0, 1, 2, 3, 4, 5].map((b) => xt.specialstats[a][xt.statsalc[a][b]][b]),
      rebsp: xt.rebsp[a], xbsp: xt.xbsp[a],
    });
  }
  return {
    unlocked: [xt.unlocked[0], xt.unlocked[1]], kills: xt.kills, wins: xt.wins,
    changers: [xt.statchangers[0], xt.statchangers[1]], usercar: xt.lastcar, laststage: xt.laststage,
    boncomp: Array.from(xt.boncomp.slice(0, 6)), carpoints: xt.carpoints, car,
  };
}

/** Put a snapshot back, as readdata does (checkpoints.stage too, and realunlocked). */
export function restore(xt, cp, m, s) {
  for (let i = 0; i < 2; i++) xt.unlocked[i] = xt.realunlocked[i] = s.unlocked[i];
  xt.kills = s.kills;
  xt.wins = s.wins;
  xt.statchangers[0] = s.changers[0];
  xt.statchangers[1] = s.changers[1];
  xt.lastcar = xt.sc[0] = s.usercar;
  xt.laststage = cp.stage = s.laststage;
  for (let i = 0; i < 6; i++) xt.boncomp[i] = s.boncomp[i] | 0;
  xt.carpoints = s.carpoints;
  s.car.forEach((c, a) => {
    m.level[a] = c.level;
    m.exp[a] = c.exp;
    xt.statpoints[a] = c.statpoints;
    AI.forEach((k, i) => { m[k][a] = c.ai[i]; });
    xt.killscn[a] = c.killscn;
    xt.winscn[a] = c.winscn;
    xt.extpoints[a] = c.extpoints;
    c.special.forEach((v, b) => { xt.specialstats[a][xt.statsalc[a][b]][b] = v; });
    xt.rebsp[a] = c.rebsp;
    xt.xbsp[a] = c.xbsp;
  });
}

export function saveCareer(xt, cp, m, store = globalThis.localStorage) {
  try { store.setItem(KEY, JSON.stringify(snapshot(xt, cp, m))); } catch (e) { console.warn('career: not saved', e); }
}

/** true when a saved career was put back. */
export function loadCareer(xt, cp, m, store = globalThis.localStorage) {
  let s = null;
  try { s = JSON.parse(store.getItem(KEY) || 'null'); } catch { /* none, or private mode */ }
  if (!s || !Array.isArray(s.car)) return false;
  restore(xt, cp, m, s);
  return true;
}

/**
 * What the car select's stat buttons change: every car's stat points and six
 * ai*sp, the car points and the bonus-car specials. The port's Confirm / Undo
 * (menus.js) compares against and puts back one of these.
 */
export function statsOf(xt, m) {
  return JSON.stringify({
    sp: Array.from(xt.statpoints), cp: xt.carpoints,
    ai: AI.map((k) => Array.from(m[k])),
    special: xt.specialstats.map((car) => car.map((row) => Array.from(row))),
  });
}

export function restoreStats(xt, m, saved) {
  const s = JSON.parse(saved);
  s.sp.forEach((v, a) => { xt.statpoints[a] = v; });
  xt.carpoints = s.cp;
  AI.forEach((k, i) => s.ai[i].forEach((v, a) => { m[k][a] = v; }));
  s.special.forEach((car, a) => car.forEach((row, x) => row.forEach((v, b) => { xt.specialstats[a][x][b] = v; })));
}

/** A new career: the jar's defaults next time it loads. */
export function clearCareer(store = globalThis.localStorage) {
  try { store.removeItem(KEY); } catch { /* private mode */ }
}
