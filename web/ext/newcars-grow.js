// Every per-car table grows to hold the new cars at NEW_BASE + i. Typed arrays do not
// grow: each is reallocated and copied. A new car's column is its donor's, then the
// CarDefine values (and the tables that mirror them) are written over it. A table
// missing from these lists would read undefined -> NaN physics with no error:
// newcars-grow.test.js compares them with every length-39 field.
import { NEW_BASE, newCars } from './newcars.js';

export const MAD_TABLES = ['acelf', 'swits', 'handb', 'handbreset', 'airs', 'airc', 'turn', 'turnreset', 'grip',
  'gripreset', 'bounce', 'simag', 'moment', 'strengthreduce', 'comprad', 'push', 'push2', 'revpush', 'revpush2',
  'lift', 'lift2', 'revlift', 'powerloss', 'powerloss2', 'flipy', 'msquash', 'clrad', 'dammult', 'maxmag',
  'healthreset', 'healthcut', 'tsstat', 'accstat', 'ovrstat', 'gristat', 'stustat', 'strstat', 'endstat',
  'endboosts', 'exp', 'level', 'aitssp', 'aiaccsp', 'aigripsp', 'aistusp', 'aistrsp', 'aiendsp', 'pmulti',
  'nitroacelf', 'nitroswits', 'airsreset', 'aircreset', 'momentreset'];
export const XT_TABLES = ['killscn', 'winscn', 'statpoints', 'proba', 'outdam', 'powersave', 'enginsignature',
  'names', 'statstext', 'wststatgain', 'rcestatgain', 'extpoints', 'specialstats', 'statsalc', 'xbspratio',
  'rebsp', 'xbsp'];
// [mirror, source]: equal in every stock car (measured), so equal in a new one
export const MIRRORS = [['handbreset', 'handb'], ['turnreset', 'turn'], ['gripreset', 'grip'],
  ['airsreset', 'airs'], ['aircreset', 'airc'], ['momentreset', 'moment'], ['healthreset', 'maxmag'],
  ['push2', 'push'], ['revpush2', 'revpush'], ['lift2', 'lift'], ['powerloss2', 'powerloss'],
  ['nitroacelf', 'acelf'], ['nitroswits', 'swits']];

const clone = (v) => (v && typeof v === 'object' ? (ArrayBuffer.isView(v) ? v.slice() : v.map(clone)) : v);

function grown(table, size) {
  if (ArrayBuffer.isView(table)) { const t = new table.constructor(size); t.set(table); return t; }
  const t = table.slice(); t.length = size; return t;
}

function grow(obj, keys, write) {
  const cars = newCars();
  const size = NEW_BASE + cars.length;
  if (!cars.length || obj[keys[0]].length >= size) return;   // nothing new, or already grown
  for (const k of keys) {
    obj[k] = grown(obj[k], size);
    cars.forEach((car, i) => { obj[k][NEW_BASE + i] = clone(obj[k][car.donor]); });
  }
  cars.forEach((car, i) => write(obj, NEW_BASE + i, car));
}

export function growMadness(m) {
  grow(m, MAD_TABLES, (o, c, car) => {
    for (const [f, v] of Object.entries(car.stat)) if (MAD_TABLES.includes(f)) o[f][c] = clone(v);
    for (const [mirror, src] of MIRRORS) o[mirror][c] = clone(o[src][c]);
  });
}

export function growXt(x) {
  grow(x, XT_TABLES, (o, c, car) => {
    o.names[c] = car.name;
    o.outdam[c] = car.stat.outdam;
    o.enginsignature[c] = car.stat.enginsignature;
  });
}
