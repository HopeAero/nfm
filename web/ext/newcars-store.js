// The launcher's choice of new cars for Extended's Free Play: [{ name, donor }], one entry
// per car switched on; the donor (0-38) lends its special power (newcars.js).
import { EXT_CARS } from './catalog.js';

export const KEY = 'nfm.ext.newcars';

export function parseStore(text) {
  let a;
  try { a = JSON.parse(text || '[]'); } catch { return []; }
  if (!Array.isArray(a)) return [];
  return a.filter((e) => e && typeof e.name === 'string' && e.name
    && Number.isInteger(e.donor) && e.donor >= 0 && e.donor < EXT_CARS.length).map(({ name, donor }) => ({ name, donor }));
}

export const donorChoices = () => [{ donor: -1, name: 'Off' }, ...EXT_CARS.map((name, donor) => ({ donor, name }))];

export function toggle(list, name, donor) {
  const rest = list.filter((e) => e.name !== name);
  return donor < 0 ? rest : [...rest, { name, donor }];
}

export function readNewCarStore() { try { return parseStore(localStorage.getItem(KEY)); } catch { return []; } }
export function writeNewCarStore(list) { try { localStorage.setItem(KEY, JSON.stringify(list)); } catch { /* private mode */ } }
