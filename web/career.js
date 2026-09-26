// Career progress: the NFM 1 / NFM 2 lines of the Java's data/user.data.
//
// GameSparker.setcarcookie writes `NFM1(car,unlocked)` and `NFM2(car,unlocked)`
// and the loader at GameSparker.java:3222 reads them back with the same range
// checks used here: the last car picked in each mode (xtGraphics.scm) and how
// far each career has got (xtGraphics.unlocked, 1..11 and 1..17).
//
// "Unlock everything" (now: developer mode) is the port's, not the Java's. It never touches the
// saved progress: it only changes what the screens are told, so switching it
// off puts the real career back.

const KEY = 'nfm.career';
export const ALL_UNLOCKED = [11, 17];

export function loadCareer() {
  const c = { unlocked: [1, 1], scm: [0, 0] };
  try {
    const s = JSON.parse(localStorage.getItem(KEY) || '{}');
    if (s.scm?.[0] >= 0 && s.scm[0] < 16) c.scm[0] = s.scm[0] | 0;
    if (s.scm?.[1] >= 0 && s.scm[1] < 16) c.scm[1] = s.scm[1] | 0;
    if (s.unlocked?.[0] >= 1 && s.unlocked[0] <= 11) c.unlocked[0] = s.unlocked[0] | 0;
    if (s.unlocked?.[1] >= 1 && s.unlocked[1] <= 17) c.unlocked[1] = s.unlocked[1] | 0;
  } catch { /* first run, or private mode */ }
  return c;
}

/**
 * setcarcookie(car, …, gmode, unlocked): only mode `gmode`'s line changes.
 * `unlocked` null keeps the saved progress -- the car select passes null, so
 * "unlock everything" can never be written back as real progress.
 */
export function saveCareer(gmode, car, unlocked) {
  if (gmode !== 1 && gmode !== 2) return;
  const c = loadCareer();
  if (car >= 0 && car < 16) c.scm[gmode - 1] = car;
  if (unlocked) c.unlocked[gmode - 1] = unlocked[gmode - 1];
  try { localStorage.setItem(KEY, JSON.stringify(c)); } catch { /* private mode */ }
}

/** What the screens see: the saved progress, or everything when the setting says so. */
export function effectiveUnlocked(c, unlockAll) {
  return unlockAll ? ALL_UNLOCKED.slice() : c.unlocked.slice();
}
