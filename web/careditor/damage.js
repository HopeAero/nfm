// The crash test: what the car will look like once the race has beaten it up.
//
// This is the one place the editor cannot work from the .rad text, because
// damage is not a property of the car -- it is what the game's deformation code
// does to a loaded model. So the ported math runs here, on the same ContO the
// preview draws, and the "Fix" button simply re-parses the source to throw the
// damage away.
//
// regx/regz/roofsqsh are transpiled from CarMaker verbatim; the only thing this
// file adds is a scratch state for them to write into and a sane calling order.

import { regx, regz, roofsqsh } from './shape.js';
import { setupo } from './files.js';
import { trunc, fr, i32, random } from '../java.js';

/**
 * One round of collision damage, the way the applet's CRASH! button delivers
 * it: four hits of decaying force, alternating between the two axes, with the
 * car swinging round between them so the dents do not all land in one place.
 *
 * `cm` needs `o`, `m`, `crash[]`, `hitmag` and `actmag` -- the fields the ported
 * deformation reads. The editor hands it the same CarMaker state bag it keeps
 * for exactly this.
 */
export function crashOnce(cm, { sideways = Math.random() > 0.5 } = {}) {
  let force = 700;
  for (let i = 0; i < 4; ++i) {
    const key = i;
    force = i32(force - 50 * i);
    if (force < 150) force = 150;
    if (sideways) regx(cm, key, fr(force), false);
    else regz(cm, key, fr(force), false);
  }
  // Turn the car between rounds, so repeated crashes chew a different corner.
  cm.o.xz = i32(cm.o.xz + (sideways ? 22 : -22));
}

/** A roof landing: the crush that happens when the car comes down upside down. */
export function roofCrash(cm) {
  cm.crashup = Math.random() > 0.5;
  roofsqsh(cm, fr(trunc(fr(230.0 + fr(Math.random() * 80.0)))));
}

/** True once the car has taken all the damage the game models. */
export const isWasted = (cm) => cm.hitmag >= 17000;

/**
 * The crash calibration, physics() value 16 (`actmag`): how much damage the car
 * takes before it is wasted. Moved verbatim out of the applet's Save & Finish
 * (tab2.js pfase 4, CarMaker.java). CarDefine.loadstat blanks a custom car whose
 * actmag is 0, so an uncalibrated car never races -- ?mycar= falls back to
 * Formula 7. Reads cm.editor (via setupo), cm.stat and cm.crash; writes
 * cm.actmag and may widen cm.crash[0] when a hit changes nothing.
 */
export function calibrate(cm) {
  for (let n83 = 0; n83 < 4; ++n83) {
    let n84 = 0;
    let n85 = 4;
    if (n83 === 1) {
      n85 = 2;
    }
    if (n83 === 2) {
      n84 = 2;
    }
    for (let n86 = 0; n86 < 10; ++n86) {
      setupo(cm);
      cm.o.xy = 0;
      cm.hitmag = 0;
      let actmag = 0;
      cm.actmag = 0;
      let n87 = n84;
      let n88 = 0;
      while (cm.hitmag < 17000) {
        if (n88 !== 0) {
          regx(cm, n87, fr(trunc(150.0 + 600.0 * random())), true);
        } else {
          regz(cm, n87, fr(trunc(150.0 + 600.0 * random())), true);
        }
        if (++n87 === n85) {
          const o = cm.o;
          o.xz = i32(o.xz + 45);
          const o2 = cm.o;
          o2.zy = i32(o2.zy + 45);
          n87 = 0;
          if (n88 !== 0) {
            n88 = 0;
          } else {
            n88 = 1;
          }
          if (actmag === cm.actmag) {
            const crash = cm.crash;
            const n89 = 0;
            crash[n89] = i32(crash[n89] + 10);
          }
          actmag = cm.actmag;
        }
      }
    }
    let n90 = 0.0;
    for (let n91 = 0; n91 < 10; ++n91) {
      setupo(cm);
      cm.o.xy = 0;
      cm.actmag = 0;
      cm.hitmag = 0;
      let n92 = n84;
      let n93 = 0;
      while (cm.hitmag < 17000) {
        if (n93 !== 0) {
          regx(cm, n92, fr(trunc(150.0 + 600.0 * random())), true);
        } else {
          regz(cm, n92, fr(trunc(150.0 + 600.0 * random())), true);
        }
        if (++n92 === n85) {
          const o3 = cm.o;
          o3.xz = i32(o3.xz + 45);
          const o4 = cm.o;
          o4.zy = i32(o4.zy + 45);
          n92 = 0;
          if (n93 !== 0) {
            n93 = 0;
          } else {
            n93 = 1;
          }
        }
      }
      n90 = fr(n90 + fr(cm.actmag / cm.hitmag));
    }
    cm.actmag = trunc(fr(cm.hitmag * fr(n90 / 10.0)));
    if (cm.stat[4] > 200) {
      cm.stat[4] = 200;
    }
    if (cm.stat[4] < 16) {
      cm.stat[4] = 16;
    }
    let n94 = fr(0.9 + fr((cm.stat[4] - 90) * 0.01));
    if (n94 < 0.6) {
      n94 = 0.6;
    }
    if (cm.stat[4] === 200 && cm.stat[0] <= 88) {
      n94 = 3.0;
    }
    const n95 = trunc(fr(cm.actmag * n94));
    for (let n96 = 0; n96 < 12; ++n96) {
      setupo(cm);
      cm.o.xy = 0;
      cm.o.xz = i32(90 * n96);
      if (cm.o.xz >= 360) {
        const o5 = cm.o;
        o5.xz = i32(o5.xz - 360);
      }
      cm.hitmag = 0;
      let actmag2 = 0;
      cm.actmag = 0;
      let n97 = n84;
      let n98 = 0;
      while (cm.actmag < n95) {
        if (n98 !== 0) {
          regx(cm, n97, fr(trunc(150.0 + 600.0 * random())), true);
        } else {
          regz(cm, n97, fr(trunc(150.0 + 600.0 * random())), true);
        }
        if (++n97 === n85) {
          if (n98 !== 0) {
            n98 = 0;
          } else {
            n98 = 1;
          }
          n97 = 0;
          if (actmag2 === cm.actmag) {
            const crash2 = cm.crash;
            const n99 = 0;
            crash2[n99] = i32(crash2[n99] + 10);
          }
          actmag2 = cm.actmag;
        }
      }
    }
    if (n83 === 3) {
      let n100 = 0.0;
      for (let n101 = 0; n101 < 10; ++n101) {
        setupo(cm);
        cm.o.xy = 0;
        cm.actmag = 0;
        cm.hitmag = 0;
        let n102 = n84;
        let n103 = 0;
        while (cm.hitmag < 17000) {
          if (n103 !== 0) {
            regx(cm, n102, fr(trunc(150.0 + 600.0 * random())), true);
          } else {
            regz(cm, n102, fr(trunc(150.0 + 600.0 * random())), true);
          }
          if (++n102 === n85) {
            const o6 = cm.o;
            o6.xz = i32(o6.xz + 45);
            const o7 = cm.o;
            o7.zy = i32(o7.zy + 45);
            n102 = 0;
            if (n103 !== 0) {
              n103 = 0;
            } else {
              n103 = 1;
            }
          }
        }
        n100 = fr(n100 + fr(cm.actmag / cm.hitmag));
      }
      cm.actmag = trunc(fr(cm.hitmag * fr(n100 / 10.0)));
    }
  }
}
