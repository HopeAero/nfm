// The Car Maker's Extended tab: what it shows about specials, and the Free Play pick
// "Try in Extended" leaves for Extended's car select (web/ext/menus.js loadPick reads it:
// a car past 38 is found by name).
import { EXT_CARS } from '../ext/catalog.js';
import { SPECIALS } from '../ext/specials.js';
import { NEW_BASE } from '../ext/newcars.js';
import { tr } from '../i18n.js';
import { carFromRad } from '../ext/newcars-stats.js';
import { readPhysics } from './rad.js';

export const TRY_KEY = 'nfm.ext.try';
export const PICK_KEY = 'nfm.ext.free';

const NONE = 'No description in the game.';
export const specialLabel = (k) => `${EXT_CARS[k]} — ${tr(SPECIALS[k][0] ?? NONE)}`;
export const specialText = (k) => (SPECIALS[k].length ? SPECIALS[k].map(tr).join('\n') : tr(NONE));

/**
 * '' when Extended can load this car, else why not. Extended skips a car it cannot load
 * (race.js), and the remembered pick then falls back to car 38 -- so Try in Extended asks
 * first. Besides being raceable, loadstat wants the crash calibrated (physics() value 16).
 */
export function whyNotExtended(name, text) {
  if (carFromRad(name, text)) return '';
  const p = readPhysics(text);
  // ponytail: the web Car Maker cannot calibrate yet (only the applet's tab2.js computes actmag);
  // cars made in the desktop Car Maker, or copied from one, carry it
  if (p && !p.actmag) return "Extended can't load this car: its crash is not calibrated (Physics tab, Crash look).";
  return "This car can't race yet — see the list under the preview.";
}

/** fn(text), computed again only when the text changes: every row of the tab reads it on each sync. */
export function perText(fn) {
  let last = null, value;
  return (text) => {
    if (text !== last) { last = text; value = fn(text); }
    return value;
  };
}

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
