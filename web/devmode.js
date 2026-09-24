// Developer mode (the launcher's Settings -> Developer mode).
//
// The race, the dev pages and the editors read test switches from the query
// string (?stage=, ?selftest=, ?stats=, ?ext=, ?nfm2stage=, ...). A player
// opening a link should not land in them, so a query string is honoured only
// with developer mode on -- except the few parameters the game itself puts in
// a URL: the Car Maker's and Stage Maker's Test Drive (?mycar=, ?mystage=,
// ?from=). The launcher never goes through the URL (it hands main.js its
// params), so none of this touches a race started from the menus.

const STORE_KEY = 'nfm.launcher';   // launcher.js's settings

export function devMode() {
  try { return !!JSON.parse(localStorage.getItem(STORE_KEY) || '{}').devmode; } catch { return false; }
}

/** What the game itself links with: always allowed. */
export const PLAYER_PARAMS = ['mycar', 'mystage', 'from'];

/**
 * The page's query string, filtered unless developer mode is on.
 * @param {(msg: string) => void} [warn]  told which parameters were ignored
 */
export function pageParams(warn) {
  const all = new URLSearchParams(location.search);
  if (devMode()) return all;
  const kept = new URLSearchParams();
  const dropped = [];
  for (const [k, v] of all) (PLAYER_PARAMS.includes(k) ? kept.append(k, v) : dropped.push(k));
  if (dropped.length && warn) warn(`developer mode is off (Settings): ignoring ?${dropped.join(', ?')}`);
  return kept;
}
