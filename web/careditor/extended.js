// The Car Maker's Extended tab: what it shows about specials, and the Free Play pick
// "Try in Extended" leaves for Extended's car select (web/ext/menus.js loadPick reads it:
// a car past 38 is found by name).
import { EXT_CARS } from '../ext/catalog.js';
import { SPECIALS } from '../ext/specials.js';
import { NEW_BASE } from '../ext/newcars.js';
import { tr } from '../i18n.js';

export const TRY_KEY = 'nfm.ext.try';
export const PICK_KEY = 'nfm.ext.free';

const NONE = 'No description in the game.';
export const specialLabel = (k) => `${EXT_CARS[k]} — ${tr(SPECIALS[k][0] ?? NONE)}`;
export const specialText = (k) => (SPECIALS[k].length ? SPECIALS[k].map(tr).join('\n') : tr(NONE));

export function tryPick(prevJson, name) {
  let p = null;
  try { p = JSON.parse(prevJson); } catch { /* none */ }
  const pick = { car: NEW_BASE, carName: name };
  if (p && typeof p === 'object' && !Array.isArray(p)) {
    if (typeof p.group === 'string') pick.group = p.group;
    if (Number.isInteger(p.stage)) pick.stage = p.stage;
  }
  return pick;
}
