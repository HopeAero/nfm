// A new car's model: Extended's ContO at its NEW_BASE code (a car, ScaleX/Y/Z honoured:
// ext-patches newcar-*), then what the base CarDefine.loadcar sets on every Car Maker car
// (web/CarDefine.js:721-727). Extended's ContO has no `decor`.
import { ContO } from './ContO.js';

export function newCarModel(car, medium, trackers, xtg, code) {
  const o = new ContO(0, Int8Array.from(car.text, (ch) => ch.charCodeAt(0)), medium, trackers, xtg, code);
  o.shadow = true;
  o.noline = false;
  o.tnt = 0;
  o.disp = 0;
  o.disline = 7;
  o.grounded = 1.0;
  return o;
}
