// Extended's race frame, split the way the base port's web/GameSparker.js
// splits the base game's: rebuildNewCars() and simulate() run once per tick,
// draw() once per display frame -- authoritative when a tick ran, a redraw of
// it (medium.interpolating) otherwise. Taken from J2JS's output of
// GameSparker.run()'s `fase == 0` block (GameSparker.java:2125-2671), so every
// expression keeps its verified float/int semantics; maintained by hand from
// here, like the base's harness. What moved is only WHEN each part runs:
//   - the start of the Java frame (shadowcar flags, ctachm, the respawn) and
//     everything after the scene (ghost rules, bots, colide/drive, record,
//     checkstat, AI, camera, HUD, the replay fade) are the tick;
//   - the scene (Medium.d, the depth-sorted objects, flames, water) is draw().
// As in the base, the tick reads the draw outputs (ContO.dist, effect state)
// of the frame's authoritative draw rather than of a draw at its own start.

import { floatArray, fr, i32, idiv, intArray, jround, objArray, trunc } from '../java.js';
import { id } from './newcars.js';   // ext-ident (web/tools/ext-ident.mjs)
import { Applet, BufferedReader, ByteArrayInputStream, Color, Cursor, DataInputStream, Date, File, FileInputStream, FileOutputStream, InputStreamReader, Integer, RenderingHints, StringBuilder, System, Thread, URL, ZipEntry, ZipInputStream, ZipOutputStream, charAt, jstr } from './jawt.js';
import { Bots } from './Bots.js';
import { CheckPoints } from './CheckPoints.js';
import { ContO } from './ContO.js';
import { Control } from './Control.js';
import { Contva } from './Contva.js';
import { Madness } from './Madness.js';
import { Medium } from './Medium.js';
import { Record } from './Record.js';
import { Trackers } from './Trackers.js';
import { xtGraphics } from './xtGraphics.js';

export class RaceTick {
  /** w: run()'s locals {medium, trackers, checkpoints, xtgraphics, record, aconto, aconto2, amadness, contva, bots} and gs. */
  constructor(gs, w) { this.gs = gs; this.w = w; }

  /** The start of the Java frame: flags the draw reads, the mouse, and the respawn (simulation). */
  rebuildNewCars() {
    const gs = this.gs, rd = gs.rd;
    const { checkpoints, xtgraphics, aconto, aconto2, amadness } = this.w;
    for (let a = 0; a < xtgraphics.nplayers; a = i32(a + 1)) {
      if (amadness[a].shadowcar) {
        aconto2[a].shadowcar = true;
      } else {
        aconto2[a].shadowcar = false;
      }
    }
    xtgraphics.ctachm(gs.xm, gs.ym, gs.mouses, gs.u[0], checkpoints, amadness[0]);
    if (gs.mouses === 2) {
      gs.mouses = 0;
    }
    if (gs.mouses === 1) {
      gs.mouses = 2;
    }
    let k8 = 0;
    do {
      if (amadness[k8].newcar) {
        let j8 = aconto2[k8].xz;
        let j9 = aconto2[k8].xy;
        let l6 = aconto2[k8].zy;
        if (xtgraphics.beastopponent[k8]) {
          aconto2[k8] = new ContO(1, aconto[i32(amadness[k8].cn + 78)], aconto2[k8].x, aconto2[k8].y, aconto2[k8].z, 0);
        } else {
          aconto2[k8] = new ContO(1, aconto[amadness[k8].cn], aconto2[k8].x, aconto2[k8].y, aconto2[k8].z, 0);
        }
        aconto2[k8].xz = j8;
        aconto2[k8].xy = j9;
        aconto2[k8].zy = l6;
        amadness[k8].newcar = false;
      }
    } while (++k8 < xtgraphics.nplayers);
  }

  /** The scene, GameSparker.java:2160-2255. */
  draw(rd) {
    const gs = this.gs;
    const { medium, xtgraphics, aconto2, amadness } = this.w;
    medium.d(rd);
    if (medium.effect[4]) {
      medium.redrawpolys(rd);
    }
    // Which cars are braking or reversing, for their lightBrake lights (drawing only;
    // ext-patch lightbrake-*, web/tools/ext-patches.mjs).
    for (let n = 0; n < xtgraphics.nplayers; n++) aconto2[n].braking = !!gs.u[n]?.down;
    let k8 = 0;
    let ai5 = intArray(200);
    let renderlimit = gs.nob;
    let extraadd2 = i32(xtgraphics.nplayers + 0);
    if (medium.effect[9] && (xtgraphics.starcnt >= 38)) {
      for (let a3 = 0; a3 < renderlimit; a3 = i32(a3 + 1)) {
        aconto2[a3].fakegrounded = aconto2[a3].grounded;
        if (a3 >= (i32(extraadd2 + 215))) {
          aconto2[a3].fakegrounded = fr(aconto2[a3].grounded * 10000.0);
          if (a3 >= (i32(extraadd2 + 408))) {
            aconto2[a3].fakegrounded = 0.0;
          }
        }
      }
    }
    for (let k9 = 0; k9 < renderlimit; k9 = i32(k9 + 1)) {
      if (aconto2[k9].dist !== 0) {
        ai5[k8] = k9;
        k8 = i32(k8 + 1);
      } else if (!xtgraphics.norender[k9]) {
        aconto2[k9].d(rd);
      }
    }
    let ai6 = intArray(k8);
    let ai7 = intArray(k8);
    for (let i8 = 0; i8 < k8; i8 = i32(i8 + 1)) {
      ai6[i8] = 0;
    }
    for (let j10 = 0; j10 < k8; j10 = i32(j10 + 1)) {
      for (let i9 = i32(j10 + 1); i9 < k8; i9 = i32(i9 + 1)) {
        if (aconto2[ai5[j10]].dist !== aconto2[ai5[i9]].dist) {
          if (aconto2[ai5[j10]].dist < aconto2[ai5[i9]].dist) {
            let array9 = ai6;
            let n9 = j10;
            ++array9[n9];
          } else {
            let array10 = ai6;
            let n10 = i9;
            ++array10[n10];
          }
        } else if (i9 > j10) {
          let array11 = ai6;
          let n11 = j10;
          ++array11[n11];
        } else {
          let array12 = ai6;
          let n12 = i9;
          ++array12[n12];
        }
      }
      ai7[ai6[j10]] = j10;
    }
    for (let k10 = 0; k10 < k8; k10 = i32(k10 + 1)) {
      if (!xtgraphics.norender[ai5[ai7[k10]]]) {
        aconto2[ai5[ai7[k10]]].d(rd);
      }
    }
    if (medium.effect[6] || medium.effect[7]) {
      for (let a4 = 0; a4 < xtgraphics.nplayers; a4 = i32(a4 + 1)) {
        if (medium.effect[6]) {
          aconto2[a4].flameheight = 100;
        }
        let extraneed = floatArray(2);
        if (medium.effect[7]) {
          extraneed[0] = 2.4000000953674316;
          extraneed[1] = 6.0;
        }
        let griplevel = fr(amadness[a4].gripreset[amadness[a4].cn] + (fr(fr(amadness[a4].aigripsp[amadness[a4].cn]) * 0.20000000298023224)));
        let goalgrip = fr((fr(24.200000762939453 + extraneed[0])) + (fr(fr(((i32((Math.imul(amadness[i32(xtgraphics.nplayers - 1)].level[amadness[i32(xtgraphics.nplayers - 1)].cn], 3)) - 1)))) * 0.20000000298023224)));
        if (goalgrip > (fr(42.0 + extraneed[1]))) {
          goalgrip = fr(42.0 + extraneed[1]);
        }
        let flame = fr((fr((fr(((fr(griplevel - (fr((fr(goalgrip * 5.0)) / 6.0))))) * 0.30000001192092896)) / ((fr(goalgrip / 6.0))))) + 0.699999988079071);
        if (griplevel < (fr((fr(goalgrip * 5.0)) / 6.0))) {
          flame = fr((fr((fr(((fr(griplevel - (fr(goalgrip / 2.0))))) * 0.20000000298023224)) / ((fr(goalgrip / 3.0))))) + 0.5);
        }
        if (flame < 0.5) {
          flame = 0.5;
        }
        if (flame > 1.0) {
          flame = 1.0;
        }
        aconto2[a4].weakstage = i32(100 - trunc((fr(((fr(flame - 0.5))) * 200.0))));
      }
    }
    if (medium.showwater) {
      xtgraphics.drawwater(medium.cfade[0], medium.cfade[1], medium.cfade[2]);
    }
  }

  /** The rest of the Java frame, GameSparker.java:2256-2671, then the frame's sound pump. */
  simulate() {
    const gs = this.gs, rd = gs.rd;
    const { medium, trackers, checkpoints, xtgraphics, record, aconto, aconto2, amadness, contva, bots } = this.w;
    let racingstage = false;
    let racer = new Array(101).fill(false);
    if (xtgraphics.careermode) {
      if (((((((checkpoints.stage === 5) && !xtgraphics.bonstage)) || (checkpoints.stage === 9)) || (checkpoints.stage === 10)) || (checkpoints.stage === 14)) || (checkpoints.stage === 20)) {
        racingstage = true;
      }
      for (let a5 = 0; a5 < 101; a5 = i32(a5 + 1)) {
        if ((amadness[a5].aitssp[amadness[a5].cn] >= amadness[a5].aistrsp[amadness[a5].cn]) || (checkpoints.clear[a5] >= 3)) {
          racer[a5] = true;
        }
      }
    }
    let noff = false;
    if ((xtgraphics.careermode && (checkpoints.stage === 23)) && xtgraphics.bossbattle) {
      noff = true;
    }
    let ghostmode = objArray(101).map(() => new Array(101).fill(false));
    for (let a6 = 0; a6 < xtgraphics.nplayers; a6 = i32(a6 + 1)) {
      for (let b = 0; b < xtgraphics.nplayers; b = i32(b + 1)) {
        ghostmode[a6][b] = false;
      }
      if (racingstage && racer[a6]) {
        for (let b = 0; b < xtgraphics.nplayers; b = i32(b + 1)) {
          if ((((a6 !== b) && (checkpoints.pos[a6] === 0)) && (checkpoints.pos[b] === 1)) && (checkpoints.clear[a6] === checkpoints.clear[b])) {
            ghostmode[a6][b] = true;
          }
        }
      }
      if (xtgraphics.careermode) {
        if (checkpoints.stage === 13) {
          if (amadness[a6].forcehandb || (amadness[a6].teleinvul > 0)) {
            for (let b = 0; b < xtgraphics.nplayers; b = i32(b + 1)) {
              ghostmode[a6][b] = true;
            }
          }
          for (let b = 0; b < xtgraphics.nplayers; b = i32(b + 1)) {
            if (((a6 !== b) && amadness[a6].isabot) && amadness[b].isabot) {
              ghostmode[a6][b] = true;
            }
          }
          if (aconto2[a6].floorguardian && !xtgraphics.nolevels) {
            for (let b = 1; b < xtgraphics.nplayers; b = i32(b + 1)) {
              if ((a6 !== b) && ((!xtgraphics.beastopponent[b] || aconto2[b].floorguardian))) {
                ghostmode[a6][b] = true;
              }
            }
          }
        }
        if (((checkpoints.stage === 17) && (a6 >= 1)) && (a6 <= 3)) {
          for (let b = 0; b < xtgraphics.nplayers; b = i32(b + 1)) {
            if (b !== xtgraphics.undeadtarget) {
              ghostmode[a6][b] = true;
            }
          }
        }
      }
      if (xtgraphics.entered[a6] || xtgraphics.bonusstage[5]) {
        for (let b = 0; b < xtgraphics.nplayers; b = i32(b + 1)) {
          ghostmode[a6][b] = true;
        }
      }
    }
    for (let a6 = 1; a6 < xtgraphics.nplayers; a6 = i32(a6 + 1)) {
      if (((xtgraphics.careermode && (checkpoints.stage === 11)) && !xtgraphics.bonusstage[1]) && ((xtgraphics.hardstage || (xtgraphics.unlocked[1] === 11)))) {
        if ((id(amadness[a6].cn) === 12) && !bots.botbreak[a6]) {
          for (let b = 1; b < xtgraphics.nplayers; b = i32(b + 1)) {
            ghostmode[a6][b] = true;
          }
          if (amadness[0].aistrsp[amadness[0].cn] <= amadness[0].aitssp[amadness[0].cn]) {
            for (let b = 0; b < xtgraphics.nplayers; b = i32(b + 1)) {
              if ((((a6 !== b) && (checkpoints.pos[a6] === 0)) && (checkpoints.pos[b] === 1)) && (checkpoints.clear[a6] === checkpoints.clear[b])) {
                ghostmode[a6][b] = true;
              }
            }
            if ((checkpoints.clear[0] >= 3) && ((Math.abs(i32(checkpoints.clear[0] - checkpoints.clear[a6])) | 0) <= 3)) {
              ghostmode[a6][0] = true;
            }
          }
        }
        if (xtgraphics.undead[a6]) {
          for (let b = 0; b < xtgraphics.nplayers; b = i32(b + 1)) {
            if (((a6 !== b) && (a6 > 4)) && (((b !== gs.u[a6].acr) || xtgraphics.safezone[b]))) {
              ghostmode[a6][b] = true;
            }
          }
        }
      }
      if (((xtgraphics.invulnerable || xtgraphics.nohit[a6]) || xtgraphics.bonstage) || noff) {
        for (let b = 1; b < xtgraphics.nplayers; b = i32(b + 1)) {
          ghostmode[a6][b] = true;
        }
      }
    }
    if (xtgraphics.starcnt === 0) {
      let usebots2 = false;
      if (xtgraphics.careermode) {
        let hard2 = false;
        if ((xtgraphics.unlocked[1] === checkpoints.stage) || xtgraphics.hardstage) {
          hard2 = true;
        }
        let cantbot = false;
        if (xtgraphics.scalelevels || xtgraphics.nolevels) {
          cantbot = true;
        }
        if ((((((((checkpoints.stage === 5) || (checkpoints.stage === 9)) || (checkpoints.stage === 10)) || (checkpoints.stage === 13)) || (checkpoints.stage === 14)) || (checkpoints.stage === 18)) || (checkpoints.stage === 20)) || (((((checkpoints.stage === 11) || (checkpoints.stage === 21))) && hard2))) {
          usebots2 = true;
        }
        if ((((checkpoints.stage === 5) || (checkpoints.stage === 9)) || (checkpoints.stage === 10)) || (checkpoints.stage === 14)) {
          let bot = i32(xtgraphics.nplayers - 1);
          let whichcar = 10;
          let threshold = 2;
          if ((checkpoints.stage === 9) || (checkpoints.stage === 10)) {
            whichcar = 12;
            if (checkpoints.stage === 9) {
              if ((checkpoints.clear[0] >= 2) && (checkpoints.clear[0] <= 4)) {
                threshold = 4;
              }
              if ((checkpoints.clear[0] >= 9) && (checkpoints.clear[0] <= 15)) {
                threshold = 7;
              }
              if ((checkpoints.clear[0] >= 15) && (checkpoints.clear[0] <= 19)) {
                threshold = 5;
              }
            }
          }
          if (checkpoints.stage === 14) {
            whichcar = 14;
          }
          let distap = gs.u[bot].py(idiv(aconto2[bot].x, 100), idiv(aconto2[0].x, 100), idiv(aconto2[bot].z, 100), idiv(aconto2[0].z, 100));
          if ((((((distap < 10000) && ((i32(checkpoints.clear[bot] - checkpoints.clear[0])) >= threshold))) || (amadness[bot].cn !== whichcar)) || cantbot) || amadness[bot].frozen) {
            bots.botbreak[bot] = true;
          }
          amadness[bot].isabot = true;
          if (bots.botbreak[bot]) {
            amadness[bot].isabot = false;
          }
        }
        if (checkpoints.stage === 13) {
          for (let bot = i32(xtgraphics.nplayers - 6); bot < xtgraphics.nplayers; bot = i32(bot + 1)) {
            let distap2 = gs.u[bot].py(idiv(aconto2[bot].x, 100), idiv(aconto2[0].x, 100), idiv(aconto2[bot].z, 100), idiv(aconto2[0].z, 100));
            let worthdodging = false;
            let rightcar = amadness[bot].cn;
            if (bot === (i32(xtgraphics.nplayers - 1))) {
              rightcar = 14;
            }
            let threshold2 = 4;
            if ((xtgraphics.floor[bot] === 0) || (xtgraphics.floor[bot] === 2)) {
              threshold2 = 5;
            }
            let nobreak = false;
            if (xtgraphics.floor[bot] !== xtgraphics.floor[0]) {
              nobreak = true;
            }
            if (amadness[0].moment[amadness[0].cn] > (fr(amadness[bot].moment[amadness[bot].cn] + 1.0))) {
              worthdodging = true;
            }
            if ((((((((distap2 < 8000) && ((i32(checkpoints.clear[bot] - checkpoints.clear[0])) >= threshold2)) && worthdodging) && !nobreak)) || (amadness[bot].cn !== rightcar)) || amadness[bot].frozen) || cantbot) {
              bots.botbreak[bot] = true;
            }
            amadness[bot].isabot = true;
            if (bots.botbreak[bot]) {
              amadness[bot].isabot = false;
            }
            if (!hard2) {
              bots.botbreak[bot] = true;
              amadness[bot].isabot = false;
            }
          }
        }
        if (checkpoints.stage === 18) {
          let bot = i32(xtgraphics.nplayers - 1);
          let distap2 = gs.u[bot].py(idiv(aconto2[bot].x, 100), idiv(aconto2[0].x, 100), idiv(aconto2[bot].z, 100), idiv(aconto2[0].z, 100));
          let worthdodging = false;
          if (amadness[0].moment[amadness[0].cn] > amadness[bot].moment[amadness[bot].cn]) {
            worthdodging = true;
          }
          if ((((((((distap2 < 6500) && ((i32(checkpoints.clear[bot] - checkpoints.clear[0])) >= 4)) && worthdodging)) || (id(amadness[bot].cn) !== 16)) || amadness[bot].frozen) || contva.biglead[0]) || cantbot) {
            bots.botbreak[bot] = true;
          }
          amadness[bot].isabot = true;
          if (bots.botbreak[bot]) {
            amadness[bot].isabot = false;
          }
        }
        if ((((checkpoints.stage === 11) || (checkpoints.stage === 21))) && hard2) {
          for (let bot = 8; bot < (i32(xtgraphics.nplayers - 1)); bot = i32(bot + 1)) {
            let distap2 = gs.u[bot].py(idiv(aconto2[bot].x, 100), idiv(aconto2[0].x, 100), idiv(aconto2[bot].z, 100), idiv(aconto2[0].z, 100));
            let worthdodging = false;
            let rightcar = 12;
            if (checkpoints.stage === 21) {
              rightcar = 17;
            }
            if (amadness[0].moment[amadness[0].cn] > (fr(amadness[bot].moment[amadness[bot].cn] + 1.0))) {
              worthdodging = true;
            }
            if (((((((distap2 < 10000) && ((i32(checkpoints.clear[bot] - checkpoints.clear[0])) >= 4)) && worthdodging)) || (amadness[bot].cn !== rightcar)) || amadness[bot].frozen) || cantbot) {
              bots.botbreak[bot] = true;
            }
            amadness[bot].isabot = true;
            if (bots.botbreak[bot]) {
              amadness[bot].isabot = false;
            }
          }
        }
        if (usebots2) {
          let bots2 = bots;
          bots2.timer = i32(bots2.timer + 1);
          for (let a7 = 0; a7 < xtgraphics.nplayers; a7 = i32(a7 + 1)) {
            let specialtimer2 = bots.specialtimer;
            let n13 = a7;
            ++specialtimer2[n13];
          }
        }
      }
      let specialtimer = false;
      if ((checkpoints.stage === 13) && (((xtgraphics.unlocked[1] === checkpoints.stage) || xtgraphics.hardstage))) {
        specialtimer = true;
      }
      for (let a8 = 1; a8 < xtgraphics.nplayers; a8 = i32(a8 + 1)) {
        if (bots.oneloaded[a8] && !bots.botbreak[a8]) {
          bots.runbots(gs.u, usebots2, a8, specialtimer);
        }
      }
      if (xtgraphics.makebot) {
        xtgraphics.testbots(bots, checkpoints, gs.u, usebots2);
      }
      for (let a8 = 0; a8 < xtgraphics.nplayers; a8 = i32(a8 + 1)) {
        for (let b2 = 0; b2 < xtgraphics.nplayers; b2 = i32(b2 + 1)) {
          if (((a8 !== b2) && !ghostmode[a8][b2]) && !ghostmode[b2][a8]) {
            amadness[a8].colide(aconto2[a8], amadness[b2], aconto2[b2], checkpoints, bots);
          }
        }
      }
      for (let l7 = 0; l7 < xtgraphics.nplayers; l7 = i32(l7 + 1)) {
        if (!amadness[l7].respawning) {
          amadness[l7].drive(gs.u[l7], aconto2[l7], trackers, checkpoints, contva, bots);
        } else {
          amadness[l7].reseto(xtgraphics.sc[l7], aconto[l7], checkpoints);
        }
      }
      let l8 = 0;
      do {
        record.rec(aconto2[l8], l8, amadness[l8].squash, amadness[0].lastcolido[0], amadness[l8].cntdest, xtgraphics.nplayers);
      } while (++l8 < xtgraphics.nplayers);
      checkpoints.checkstat(amadness, aconto2, record, xtgraphics);
      for (let a7 = 0; a7 < xtgraphics.nplayers; a7 = i32(a7 + 1)) {
        if (amadness[a7].isabot || ((xtgraphics.makebot && (a7 === 0)))) {
          gs.u[a7].wall = -1;
          amadness[a7].surfer[0] = false;
        }
      }
      if (xtgraphics.justcs === 6) {
        l8 = 0;
      } else {
        l8 = 1;
      }
      do {
        let noai = false;
        if (amadness[l8].isabot) {
          noai = true;
        }
        if ((xtgraphics.careermode && (checkpoints.stage === 13)) && ((amadness[l8].forcehandb || (xtgraphics.speedhack[l8] > 0)))) {
          gs.u[l8].down = false;
          gs.u[l8].left = false;
          gs.u[l8].right = false;
          gs.u[l8].handb = false;
          noai = true;
        }
        if (!noai) {
          gs.u[l8].preform(amadness[l8], aconto2[l8], checkpoints, trackers, xtgraphics, amadness[0], bots);
        }
      } while (++l8 < xtgraphics.nplayers);
      l8 = 0;
      do {
        let noai = false;
        if (amadness[l8].isabot) {
          noai = true;
        }
        if ((xtgraphics.careermode && (checkpoints.stage === 13)) && ((amadness[l8].forcehandb || (xtgraphics.speedhack[l8] > 0)))) {
          noai = true;
        }
        if (!noai) {
          contva.sortvariables(amadness[l8], checkpoints, gs.u, xtgraphics.careermode, xtgraphics.nplayers);
        }
      } while (++l8 < xtgraphics.nplayers);
    } else {
      if (xtgraphics.starcnt === 130) {
        medium.adv = 1900;
        medium.zy = 40;
        medium.vxz = 70;
        rd.setColor(255, 255, 255);
        rd.fillRect(0, 0, 870, 480);
      }
      if (xtgraphics.starcnt !== 0) {
        let xtGraphics = xtgraphics;
        xtGraphics.starcnt = i32(xtGraphics.starcnt - 1);
      }
    }
    if ((xtgraphics.justcs === 6) && (xtgraphics.starcnt !== 0)) {
      checkpoints.checkstat(amadness, aconto2, record, xtgraphics);
      xtgraphics.starcnt = 0;
    }
    if (xtgraphics.starcnt < 38) {
      if (xtgraphics.dontdisplay) {
        xtgraphics.tourney(amadness, checkpoints, gs.u[0], aconto2);
      }
      xtgraphics.nitroandspecials(amadness, checkpoints, gs.u, aconto2, gs.view);
      let viewboost = 0;
      let thebot = 0;
      if (xtgraphics.viewbot) {
        thebot = i32(xtgraphics.nplayers - 1);
      }
      let lookback = gs.u[0].lookback;
      if ((xtgraphics.scareflash && (xtgraphics.scareflashtime >= 149)) && (((xtgraphics.scareflashtime % 30)) === 29)) {
        thebot = i32(xtgraphics.nplayers + 1);
        viewboost = -200;
      }
      let whichfol = thebot;
      if (id(amadness[whichfol].cn) === 18) {
        viewboost = 65;
      }
      if (id(amadness[whichfol].cn) === 20) {
        viewboost = 130;
      }
      if (id(amadness[whichfol].cn) === 22) {
        viewboost = 300;
      }
      if ((((xtgraphics.careermode && (checkpoints.stage === 6)) && xtgraphics.shownghost) && amadness[0].dest) && (xtgraphics.holdcnt > 85)) {
        whichfol = i32(xtgraphics.nplayers + 2);
      }
      if (!xtgraphics.ghosttele) {
        if (gs.view === 0) {
          medium.follow(aconto2[whichfol], amadness[whichfol].cxz, gs.u[0].lookback, viewboost);
          xtgraphics.stat$m(amadness, checkpoints, gs.u[0], aconto2, contva, true);
        } else {
          medium.watch(aconto2[whichfol], amadness[whichfol].cxz / 15.0, viewboost);
          xtgraphics.stat$m(amadness, checkpoints, gs.u[0], aconto2, contva, true);
        }
      } else {
        medium.follow(aconto2[whichfol], 0, 0, viewboost);
        xtgraphics.stat$m(amadness, checkpoints, gs.u[0], aconto2, contva, true);
      }
      if (xtgraphics.starcnt === 36) {
        gs.repaint();
        xtgraphics.blendude(gs.offImage);
      }
      if (xtgraphics.starcnt === 0) {
        xtgraphics.realwalls(aconto2, amadness, checkpoints.stage);
      }
      if (xtgraphics.careermode) {
        xtgraphics.careermode$m(amadness, checkpoints, gs.u, aconto2, trackers, contva);
      }
    } else {
      let cararound = 5;
      if (xtgraphics.careermode && (((((xtgraphics.bonusstage[1] || (checkpoints.stage === 9)) || (checkpoints.stage === 20)) || xtgraphics.bonusstage[3]) || (checkpoints.stage === 13)))) {
        cararound = 0;
      }
      medium.around(aconto2[cararound], true);
      if (gs.u[0].enter || gs.u[0].handb) {
        xtgraphics.starcnt = 38;
        gs.u[0].enter = false;
        gs.u[0].handb = false;
      }
      if (xtgraphics.starcnt === 38) {
        gs.mouses = 0;
        medium.vert = false;
        medium.adv = 900;
        medium.vxz = 180;
        checkpoints.checkstat(amadness, aconto2, record, xtgraphics);
        medium.follow(aconto2[0], amadness[0].cxz, 0, 0);
        xtgraphics.stat$m(amadness, checkpoints, gs.u[0], aconto2, contva, true);
        rd.setColor(255, 255, 255);
        rd.fillRect(0, 0, 870, 480);
      }
    }
    if (xtgraphics.replayfade) {
      if (xtgraphics.replayphase === 0) {
        if (xtgraphics.replaytrans < 255) {
          let xtGraphics2 = xtgraphics;
          xtGraphics2.replaytrans = i32(xtGraphics2.replaytrans + 25);
        }
        if (xtgraphics.replaytrans > 255) {
          xtgraphics.replaytrans = 255;
        }
        if (xtgraphics.replaytrans === 255) {
          xtgraphics.replayphase = 1;
        }
      }
      if (xtgraphics.replayphase === 4) {
        if (xtgraphics.replaytrans > 0) {
          let xtGraphics3 = xtgraphics;
          xtGraphics3.replaytrans = i32(xtGraphics3.replaytrans - 10);
        }
        if (xtgraphics.replaytrans < 0) {
          xtgraphics.replaytrans = 0;
        }
        if (xtgraphics.replaytrans === 0) {
          xtgraphics.replayphase = 0;
          xtgraphics.replayfade = false;
        }
      }
      rd.setColorOf(new Color(0, 0, 0, xtgraphics.replaytrans));
      rd.fillRect(0, 0, 870, 480);
    }
    if (xtgraphics.fase === 0) {
    for (let a = 0; a < xtgraphics.nplayers; a = i32(a + 1)) {
      amadness[a].oldfcnt = aconto2[a].fcnt;
    }
    }
    if (!xtgraphics.dontdisplay) {
    xtgraphics.playsounds(amadness[0], gs.u[0], checkpoints.stage);
    } else {
    const track = { 1: 61, 2: 74, 3: 67, 4: 73, 5: 35 }[xtgraphics.ptmatch];
    if (track !== undefined) xtgraphics.playsounds(amadness[0], gs.u[0], track);
    }
    this.musicSwitch();
  }

  /**
   * The career's two-part music: an .ogg intro played once ("a", the stage's
   * switch(ms) long), then the looping "b". run() does this after every fase
   * block, not in the race's (GameSparker.java:3336-3368), so it is not in the
   * `fase == 0` code above and the race never left the intro without it.
   */
  musicSwitch() {
    const { checkpoints, xtgraphics } = this.w;
    if ((((xtgraphics.lastload >= 0) && (xtgraphics.stracks[xtgraphics.lastload] === null)) && (xtgraphics.fase !== 10)) && xtgraphics.mtracks[xtgraphics.lastload].nooggloop) {
      if (xtgraphics.mtracks[xtgraphics.lastload].playingogg) {
        xtgraphics.elapsed = (System.nanoTime() - xtgraphics.duration) + xtgraphics.pausetime;
      } else if (xtgraphics.mtracks[xtgraphics.lastload].pausedogg) {
        xtgraphics.pausetime = xtgraphics.elapsed + 500000000;
        xtgraphics.duration = System.nanoTime();
      }
      if (xtgraphics.elapsed >= xtgraphics.musicswitch) {
        xtgraphics.mtracks[xtgraphics.lastload].setPaused(true);
        xtgraphics.mtracks[xtgraphics.lastload].unload();
        xtgraphics.loadedt[xtgraphics.lastload] = false;
        if (xtgraphics.careermode) {
          if ((xtgraphics.lastload !== 77) && (xtgraphics.lastload < 94)) {
            xtgraphics.mtracks[i32(checkpoints.stage + 63)].play();
            xtgraphics.lastload = i32(checkpoints.stage + 63);
          } else {
            if (xtgraphics.lastload === 77) {
              xtgraphics.mtracks[78].play();
              xtgraphics.lastload = 78;
            }
            if (xtgraphics.lastload === 94) {
              xtgraphics.mtracks[95].play();
              xtgraphics.lastload = 95;
            }
            if (xtgraphics.lastload === (i32(checkpoints.stage + 99))) {
              xtgraphics.mtracks[i32(checkpoints.stage + 100)].play();
              xtgraphics.lastload = i32(checkpoints.stage + 100);
            }
          }
        }
      }
    }
  }
}
