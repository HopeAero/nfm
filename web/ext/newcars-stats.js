// A Car Maker .rad as an Extended new car: the physics the base port's CarDefine
// computes from stat()/physics()/handling() (NFM 2's formulas, not Extended's
// hand-tuned tables), measured on the base ContO as CarDefine.loadcar measures it.
import { CarDefine } from '../CarDefine.js';
import { ContO as BaseContO } from '../ContO.js';
import { floatArray, intArray } from '../java.js';

// CarDefine.loadstat's outputs that Extended's Madness / xtGraphics tables also have
export const STAT_FIELDS = ['acelf', 'swits', 'handb', 'airs', 'airc', 'turn', 'grip', 'bounce', 'simag',
  'moment', 'comprad', 'push', 'revpush', 'lift', 'revlift', 'powerloss', 'flipy', 'msquash', 'clrad',
  'dammult', 'maxmag', 'outdam', 'enginsignature'];

// base cars 0-15 are Extended's 23-38, same order (catalog.js EXT_CARS)
const BASE_CCLASS = [0, 0, 0, 0, 0, 1, 2, 2, 2, 2, 3, 4, 4, 4, 4, 4];
export const defaultDonor = (cclass) => 23 + Math.max(0, BASE_CCLASS.indexOf(cclass));

// only measured, never drawn: what the base ContO constructor reads (web/ContO.test.js)
function stubMedium() {
  const tcos = floatArray(360), tsin = floatArray(360);
  for (let i = 0; i < 360; ++i) { tcos[i] = Math.cos(i * 0.017453292519943295); tsin[i] = Math.sin(i * 0.017453292519943295); }
  return {
    tcos, tsin, cx: 400, cy: 225, cz: 100, focus_point: 400, xz: 0, zy: 0, x: 0, y: 0, z: 0,
    trk: 0, adv: 900, ground: 250, fogd: 7, resdown: 0, loadnew: false,
    cpol: intArray(3), cgrnd: intArray(3), snap: intArray(3), csky: intArray(3), cfade: intArray(3), fade: intArray(16),
    cos(i) { while (i >= 360) i -= 360; while (i < 0) i += 360; return this.tcos[i]; },
    sin(i) { while (i >= 360) i -= 360; while (i < 0) i += 360; return this.tsin[i]; },
    random: () => 0.5,
  };
}
function stubTrackers() {
  const n = () => intArray(100);
  return { nt: 0, xy: n(), zy: n(), c: Array.from({ length: 100 }, () => intArray(3)), x: n(), y: n(), z: n(),
    radx: n(), rady: n(), radz: n(), skd: n(), dam: n(), notwall: new Array(100).fill(false), decor: new Array(100).fill(false) };
}

// CarDefine.loadcar's wheel rule: w() 1-4 are front-left, front-right, rear-left, rear-right
const wheelsOk = (o) => !(o.keyz[0] < 0 || o.keyx[0] > 0) && !(o.keyz[1] < 0 || o.keyx[1] < 0)
  && !(o.keyz[2] > 0 || o.keyx[2] > 0) && !(o.keyz[3] > 0 || o.keyx[3] < 0);

const SLOT = 16;   // CarDefine's first custom slot

export function carFromRad(name, text, donor) {
  let model;
  try { model = new BaseContO(text, stubMedium(), stubTrackers()); } catch { return null; }
  if (model.errd || model.npl <= 60 || !wheelsOk(model)) return null;
  const cd = new CarDefine(null, null, null, null);
  cd.loadstat(text, name, model.maxR, model.roofat, model.wh, SLOT);
  if (!cd.names[SLOT]) return null;          // loadstat blanks the name when stat() is missing
  const stat = {};
  const copy = (v) => (v?.length !== undefined ? v.slice() : v);
  for (const f of STAT_FIELDS) stat[f] = copy(cd[f][SLOT]);
  const cclass = cd.cclass[SLOT];
  return { name, text, stat, cclass, donor: Number.isInteger(donor) ? donor : defaultDonor(cclass) };
}
