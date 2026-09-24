// Transpiled by decompilation/extended/j2js/J2JS.java from
// decompilation/extended/java-src/ContO.java, then maintained by hand like the
// base port's web/ContO.js, whose `interpolating` guards it carries at the same
// sites (electric ring, landing dust, repair sparkle) and extends to Extended's
// own effects. The face-order sort and the rot() trig hoist are the base's
// optimisations. The Java's behaviour is checked against madness.jar by
// ContO.test.js and draw.test.js (call for call); run them after any edit.

import { floatArray, fr, i32, idiv, intArray, objArray, random, trunc } from '../java.js';
import { Arrays, ByteArrayInputStream, Color, DataInputStream, Integer, Random, StringBuilder, System, charAt, jstr } from './jawt.js';
import { Plane } from './Plane.js';
import { Wheels } from './Wheels.js';

export class ContO {
  constructor(k, ...a) {
    this.m = null;
    this.t = null;
    this.p = null;
    this.npl = 0;
    this.x = 0;
    this.y = 0;
    this.z = 0;
    this.xz = 0;
    this.xy = 0;
    this.zy = 0;
    this.wxz = 0;
    this.wzy = 0;
    this.dist = 0;
    this.fixdist = 0;
    this.maxR = 0;
    this.disp = 0;
    this.disline = 0;
    this.shadow = false;
    this.noline = false;
    this.grounded = 0;
    this.srgb = null;
    this.grat = 0;
    this.osmag = null;
    this.keyx = null;
    this.smag = null;
    this.keyz = null;
    this.txy = null;
    this.tzy = null;
    this.sav = null;
    this.sbln = null;
    this.tc = null;
    this.tradx = null;
    this.tradz = null;
    this.trady = null;
    this.tx = null;
    this.ty = null;
    this.sx = null;
    this.tz = null;
    this.skd = null;
    this.dam = null;
    this.notwall = null;
    this.tnt = 0;
    this.sy = null;
    this.sz = null;
    this.stg = null;
    this.dov = null;
    this.smag2 = null;
    this.scx = null;
    this.scz = null;
    this.fulls = null;
    this.elec = false;
    this.roted = false;
    this.edl = null;
    this.edr = null;
    this.elc = null;
    this.fix = false;
    this.fcnt = 0;
    this.checkpoint = 0;
    this.div = 0;
    this.iwid = 0;
    this.sfactor = 0;
    this.spatk = false;
    this.freeze = false;
    this.weaken = false;
    this.leech = false;
    this.playerglow = false;
    this.strswap = false;
    this.sred = 0;
    this.sgreen = 0;
    this.sblue = 0;
    this.shadowcar = false;
    this.descend = 0;
    this.greenflame = false;
    this.flameheight = 0;
    this.invisiblepiece = 0;
    this.glowlines = false;
    this.glowcustom = false;
    this.glowcolour = null;
    this.spec = null;
    this.weakstage = 0;
    this.groundlevel = 0;
    this.fakegrounded = 0;
    this.wallpiece = false;
    this.telechk = 0;
    this.telefade = 0;
    this.teleported = false;
    this.floorguardian = false;
    this.guardswitch = false;
    this.isacar = false;
    this.xextreme = null;
    this.zextreme = null;
    this.lightup = false;
    this.outoftrack = false;
    this.dmgcolours = null;
    switch (k) {
      case 0: this.$ctor0(...a); break;
      case 1: this.$ctor1(...a); break;
      case 2: this.$ctor2(...a); break;
    }
  }

  $ctor0(abyte0, medium, trackers, xtgraphics, code) {
    this.div = 1.0;
    this.iwid = 1.0;
    this.spec = intArray(3);
    this.xextreme = intArray(2);
    this.zextreme = intArray(2);
    this.npl = 0;
    this.x = 0;
    this.y = 0;
    this.z = 0;
    this.xz = 0;
    this.xy = 0;
    this.zy = 0;
    this.wxz = 0.0;
    this.sred = 0;
    this.sblue = 0;
    this.sgreen = 0;
    this.weakstage = 0;
    this.groundlevel = 250;
    this.telechk = -1;
    this.telefade = 255;
    this.teleported = false;
    this.floorguardian = false;
    this.guardswitch = false;
    this.wallpiece = false;
    this.fakegrounded = 1.0;
    this.spatk = false;
    this.playerglow = false;
    this.freeze = false;
    this.leech = false;
    this.weaken = false;
    this.strswap = false;
    this.shadowcar = false;
    this.greenflame = false;
    this.flameheight = 1;
    this.invisiblepiece = 255;
    this.glowlines = false;
    this.lightup = false;
    this.outoftrack = false;
    this.xextreme = intArray(2);
    this.zextreme = intArray(2);
    this.dmgcolours = intArray(3);
    this.glowcustom = false;
    this.glowcolour = intArray(3);
    this.wzy = 0;
    this.descend = -10000;
    this.dist = 0;
    this.fixdist = 0;
    this.maxR = 0;
    this.disp = 0;
    this.disline = 7;
    this.shadow = false;
    this.noline = false;
    this.grounded = 1.0;
    this.grat = 0;
    this.keyx = intArray(6);
    this.keyz = intArray(6);
    this.tnt = 0;
    this.sx = intArray(6);
    this.sy = intArray(6);
    this.sz = intArray(6);
    this.stg = intArray(6);
    this.dov = intArray(6);
    this.smag = floatArray(6);
    this.scx = intArray(6);
    this.scz = intArray(6);
    this.fulls = new Array(6).fill(false);
    this.elec = false;
    this.roted = false;
    this.edl = intArray(6);
    this.edr = intArray(6);
    this.elc = intArray(6);
    this.fix = false;
    this.fcnt = 0;
    this.checkpoint = 0;
    this.m = medium;
    this.t = trackers;
    this.p = objArray(500);
    let s1 = '';
    let flag = false;
    let flag2 = false;
    let i = 0;
    let ai = intArray(100);
    let ai2 = intArray(100);
    let ai3 = intArray(100);
    let ai4 = intArray(3);
    let i2 = 0;
    let flag3 = false;
    let wheels = new Wheels();
    let j = 0;
    let k = 1;
    let l = 0;
    let i3 = 0;
    let byte0 = 0;
    if ((code < 78) || (code >= 120)) {
      this.sfactor = 10.0;
    } else {
      this.sfactor = 6.0;
    }
    this.isacar = false;
    if (((code < 39) || (((code >= 78) && (code < 117)))) || (code === 64)) {
      this.isacar = true;
    }
    let bool2 = false;
    try {
      let datainputstream = new DataInputStream(new ByteArrayInputStream(abyte0));
      let s2 = null;
      while (((s2 = datainputstream.readLine())) !== null) {
        s1 = new StringBuilder().append(s2.trim()).toString();
        if (s1.startsWith('<p>')) {
          flag = true;
          i = 0;
          k = 0;
          l = 0;
          byte0 = 0;
          bool2 = false;
        }
        if (flag) {
          if (s1.startsWith('gr')) {
            k = this.getvalue('gr', s1, 0);
          }
          if (s1.startsWith('fs')) {
            l = this.getvalue('fs', s1, 0);
          }
          if (s1.startsWith('c')) {
            i2 = 0;
            ai4[0] = this.getvalue('c', s1, 0);
            ai4[1] = this.getvalue('c', s1, 1);
            ai4[2] = this.getvalue('c', s1, 2);
          }
          if (s1.startsWith('glass')) {
            i2 = 1;
          }
          if (s1.startsWith('gshadow')) {
            i2 = 2;
          }
          if (s1.startsWith('lightF')) {
            byte0 = 1;
          }
          if (s1.startsWith('lightB')) {
            byte0 = 2;
          }
          if (s1.startsWith('noOutline')) {
            bool2 = true;
          }
          if (s1.startsWith('p')) {
            ai[i] = trunc((fr((fr(fr(this.getvalue('p', s1, 0)) * this.div)) * this.iwid)));
            ai2[i] = trunc((fr(fr(this.getvalue('p', s1, 1)) * this.div)));
            ai3[i] = trunc((fr(fr(this.getvalue('p', s1, 2)) * this.div)));
            let j2 = trunc(Math.sqrt(i32((i32((Math.imul(ai[i], ai[i])) + (Math.imul(ai2[i], ai2[i])))) + (Math.imul(ai3[i], ai3[i])))));
            if (j2 > this.maxR) {
              this.maxR = j2;
            }
            i = i32(i + 1);
          }
        }
        if (s1.startsWith('</p>')) {
          this.p[this.npl] = new Plane(this.m, this.t, ai, ai3, ai2, i, ai4, i2, k, l, 0, 0, 0, this.disline, 0, flag3, byte0, bool2);
          this.npl = i32(this.npl + 1);
          flag = false;
        }
        if (s1.startsWith('rims')) {
          wheels.setrims(this.getvalue('rims', s1, 0), this.getvalue('rims', s1, 1), this.getvalue('rims', s1, 2), this.getvalue('rims', s1, 3), this.getvalue('rims', s1, 4));
        }
        if (s1.startsWith('w')) {
          this.keyx[j] = trunc((fr(fr(this.getvalue('w', s1, 0)) * this.div)));
          this.keyz[j] = trunc((fr(fr(this.getvalue('w', s1, 2)) * this.div)));
          j = i32(j + 1);
          wheels.make(this.m, this.t, this.p, this.npl, trunc((fr((fr(fr(this.getvalue('w', s1, 0)) * this.div)) * this.iwid))), trunc((fr(fr(this.getvalue('w', s1, 1)) * this.div))), trunc((fr(fr(this.getvalue('w', s1, 2)) * this.div))), this.getvalue('w', s1, 3), trunc((fr((fr(fr(this.getvalue('w', s1, 4)) * this.div)) * this.iwid))), trunc((fr(fr(this.getvalue('w', s1, 5)) * this.div))), i3);
          this.npl = i32(this.npl + 15);
        }
        if (s1.startsWith('tracks')) {
          let k2 = this.getvalue('tracks', s1, 0);
          this.txy = intArray(k2);
          this.tzy = intArray(k2);
          this.tc = objArray(k2).map(() => intArray(3));
          this.tradx = intArray(k2);
          this.tradz = intArray(k2);
          this.trady = intArray(k2);
          this.tx = intArray(k2);
          this.ty = intArray(k2);
          this.tz = intArray(k2);
          this.skd = intArray(k2);
          this.dam = intArray(k2);
          this.notwall = new Array(k2).fill(false);
        }
        if (s1.startsWith('<track>')) {
          flag2 = true;
          this.notwall[this.tnt] = false;
          this.dam[this.tnt] = 1;
          this.skd[this.tnt] = 0;
          this.ty[this.tnt] = 0;
          this.tx[this.tnt] = 0;
          this.tz[this.tnt] = 0;
          this.txy[this.tnt] = 0;
          this.tzy[this.tnt] = 0;
          this.trady[this.tnt] = 0;
          this.tradx[this.tnt] = 0;
          this.tradz[this.tnt] = 0;
          this.tc[this.tnt][0] = 0;
          this.tc[this.tnt][1] = 0;
          this.tc[this.tnt][2] = 0;
        }
        if (flag2) {
          if (s1.startsWith('c')) {
            this.tc[this.tnt][0] = this.getvalue('c', s1, 0);
            this.tc[this.tnt][1] = this.getvalue('c', s1, 1);
            this.tc[this.tnt][2] = this.getvalue('c', s1, 2);
          }
          if (s1.startsWith('xy')) {
            this.txy[this.tnt] = this.getvalue('xy', s1, 0);
          }
          if (s1.startsWith('zy')) {
            this.tzy[this.tnt] = this.getvalue('zy', s1, 0);
          }
          if (s1.startsWith('radx')) {
            this.tradx[this.tnt] = trunc((fr(fr(this.getvalue('radx', s1, 0)) * this.div)));
          }
          if (s1.startsWith('rady')) {
            this.trady[this.tnt] = trunc((fr(fr(this.getvalue('rady', s1, 0)) * this.div)));
          }
          if (s1.startsWith('radz')) {
            this.tradz[this.tnt] = trunc((fr(fr(this.getvalue('radz', s1, 0)) * this.div)));
          }
          if (s1.startsWith('ty')) {
            this.ty[this.tnt] = trunc((fr(fr(this.getvalue('ty', s1, 0)) * this.div)));
          }
          if (s1.startsWith('tx')) {
            this.tx[this.tnt] = trunc((fr(fr(this.getvalue('tx', s1, 0)) * this.div)));
          }
          if (s1.startsWith('tz')) {
            this.tz[this.tnt] = trunc((fr(fr(this.getvalue('tz', s1, 0)) * this.div)));
          }
          if (s1.startsWith('skid')) {
            this.skd[this.tnt] = this.getvalue('skid', s1, 0);
          }
          if (s1.startsWith('dam')) {
            this.dam[this.tnt] = 3;
          }
          if (s1.startsWith('firedam')) {
            this.dam[this.tnt] = 9;
          }
          if (s1.startsWith('notwall')) {
            this.notwall[this.tnt] = true;
          }
        }
        if (s1.startsWith('</track>')) {
          flag2 = false;
          this.tnt = i32(this.tnt + 1);
        }
        if (s1.startsWith('disp')) {
          this.disp = this.getvalue('disp', s1, 0);
        }
        if (s1.startsWith('disline')) {
          this.disline = this.getvalue('disline', s1, 0);
        }
        if (s1.startsWith('shadow')) {
          this.shadow = true;
        }
        if (s1.startsWith('stonecold')) {
          this.noline = true;
        }
        if (s1.startsWith('road')) {
          flag3 = true;
        }
        if (s1.startsWith('notroad')) {
          flag3 = false;
        }
        if (s1.startsWith('grounded')) {
          this.grounded = fr(fr(this.getvalue('grounded', s1, 0)) / 100.0);
        }
        if (s1.startsWith('div')) {
          this.div = fr(fr(this.getvalue('div', s1, 0)) / this.sfactor);
        }
        if (s1.startsWith('idiv')) {
          this.div = fr(fr(this.getvalue('idiv', s1, 0)) / ((fr(this.sfactor * 10.0))));
        }
        if (s1.startsWith('iwid')) {
          this.iwid = fr(fr(this.getvalue('iwid', s1, 0)) / 100.0);
        }
        if (s1.startsWith('gwgr')) {
          i3 = this.getvalue('gwgr', s1, 0);
        }
      }
      datainputstream.close();
    } catch (exception) {
      System.out.println('ContO Loading Error: ' + exception);
      System.out.println(('At File: ' + abyte0) + '.rad');
      System.out.println('At Line: ' + s1);
      System.out.println('--------------------');
    }
    this.grat = wheels.ground;
  }

  $ctor1(conto, i, j, k, l) {
    this.div = 1.0;
    this.iwid = 1.0;
    this.spec = intArray(3);
    this.xextreme = intArray(2);
    this.zextreme = intArray(2);
    this.npl = 0;
    this.x = 0;
    this.y = 0;
    this.z = 0;
    this.xz = 0;
    this.xy = 0;
    this.zy = 0;
    this.wxz = 0.0;
    this.wzy = 0;
    this.dist = 0;
    this.fixdist = 0;
    this.maxR = 0;
    this.disp = 0;
    this.sred = 0;
    this.sblue = 0;
    this.sgreen = 0;
    this.weakstage = 0;
    this.telechk = -1;
    this.wallpiece = false;
    this.groundlevel = 250;
    this.telefade = 255;
    this.teleported = false;
    this.floorguardian = false;
    this.isacar = false;
    this.guardswitch = false;
    this.fakegrounded = 1.0;
    this.spatk = false;
    this.freeze = false;
    this.leech = false;
    this.playerglow = false;
    this.weaken = false;
    this.strswap = false;
    this.disline = 7;
    this.shadow = false;
    this.noline = false;
    this.grounded = 1.0;
    this.lightup = false;
    this.outoftrack = false;
    this.xextreme = intArray(2);
    this.zextreme = intArray(2);
    this.dmgcolours = intArray(3);
    this.grat = 0;
    this.keyx = intArray(6);
    this.keyz = intArray(6);
    this.tnt = 0;
    this.sx = intArray(6);
    this.sy = intArray(6);
    this.sz = intArray(6);
    this.stg = intArray(6);
    this.dov = intArray(6);
    this.smag = floatArray(6);
    this.scx = intArray(6);
    this.scz = intArray(6);
    this.fulls = new Array(6).fill(false);
    this.elec = false;
    this.roted = false;
    this.edl = intArray(6);
    this.edr = intArray(6);
    this.elc = intArray(6);
    this.fix = false;
    this.fcnt = 0;
    this.flameheight = 1;
    this.invisiblepiece = 255;
    this.glowlines = false;
    this.glowcustom = false;
    this.glowcolour = intArray(3);
    this.checkpoint = 0;
    this.m = conto.m;
    this.t = conto.t;
    this.npl = conto.npl;
    this.maxR = conto.maxR;
    this.disp = conto.disp;
    this.disline = conto.disline;
    this.noline = conto.noline;
    this.shadow = conto.shadow;
    this.grounded = conto.grounded;
    this.isacar = conto.isacar;
    this.grat = conto.grat;
    this.p = objArray(conto.npl);
    for (let i2 = 0; i2 < this.npl; i2 = i32(i2 + 1)) {
      if (conto.p[i2].master !== 0) {
        conto.p[i2].n = 16;
      }
      this.p[i2] = new Plane(this.m, this.t, conto.p[i2].ox, conto.p[i2].oz, conto.p[i2].oy, conto.p[i2].n, conto.p[i2].oc, conto.p[i2].glass, conto.p[i2].gr, conto.p[i2].fs, conto.p[i2].wx, conto.p[i2].wy, conto.p[i2].wz, conto.disline, conto.p[i2].bfase, conto.p[i2].road, conto.p[i2].light, conto.p[i2].solo);
    }
    this.x = i;
    this.y = j;
    this.z = k;
    this.xz = 0;
    this.xy = 0;
    this.zy = 0;
    for (let j2 = 0; j2 < this.npl; j2 = i32(j2 + 1)) {
      this.p[j2].master = conto.p[j2].master;
      this.p[j2].rot(this.p[j2].ox, this.p[j2].oz, 0, 0, l, this.p[j2].n);
      this.p[j2].loadprojf();
    }
    if (this.m.effect[10] || this.m.effect[11]) {
      let xmax = intArray(this.npl);
      let xmin = intArray(this.npl);
      let zmax = intArray(this.npl);
      let zmin = intArray(this.npl);
      for (let j3 = 0; j3 < this.npl; j3 = i32(j3 + 1)) {
        let xpiece = intArray(this.p[j3].n);
        let zpiece = intArray(this.p[j3].n);
        for (let a = 0; a < this.p[j3].n; a = i32(a + 1)) {
          xpiece[a] = this.p[j3].ox[a];
          zpiece[a] = this.p[j3].oz[a];
          Arrays.sort(xpiece);
          Arrays.sort(zpiece);
        }
        xmin[j3] = xpiece[0];
        xmax[j3] = xpiece[i32(this.p[j3].n - 1)];
        zmin[j3] = zpiece[0];
        zmax[j3] = zpiece[i32(this.p[j3].n - 1)];
      }
      Arrays.sort(xmin);
      Arrays.sort(xmax);
      Arrays.sort(zmin);
      Arrays.sort(zmax);
      this.xextreme[0] = i32(xmin[0] + i);
      this.xextreme[1] = i32(xmax[i32(this.npl - 1)] + i);
      this.zextreme[0] = i32(zmin[0] + k);
      this.zextreme[1] = i32(zmax[i32(this.npl - 1)] + k);
    }
    if (conto.tnt !== 0) {
      for (let k2 = 0; k2 < conto.tnt; k2 = i32(k2 + 1)) {
        this.t.xy[this.t.nt] = trunc((fr((fr(fr(conto.txy[k2]) * this.m.cos(l))) - (fr(fr(conto.tzy[k2]) * this.m.sin(l))))));
        this.t.zy[this.t.nt] = trunc((fr((fr(fr(conto.tzy[k2]) * this.m.cos(l))) + (fr(fr(conto.txy[k2]) * this.m.sin(l))))));
        let i3 = 0;
        do {
          this.t.c[this.t.nt][i3] = trunc((fr(fr(conto.tc[k2][i3]) + (fr(fr(conto.tc[k2][i3]) * ((fr(fr(this.m.snap[i3]) / 100.0))))))));
          if (this.t.c[this.t.nt][i3] > 255) {
            this.t.c[this.t.nt][i3] = 255;
          }
          if (this.t.c[this.t.nt][i3] < 0) {
            this.t.c[this.t.nt][i3] = 0;
          }
          this.t.oc[this.t.nt][i3] = this.t.c[this.t.nt][i3];
        } while (++i3 < 3);
        this.t.x[this.t.nt] = trunc((fr((fr(fr(this.x) + (fr(fr(conto.tx[k2]) * this.m.cos(l))))) - (fr(fr(conto.tz[k2]) * this.m.sin(l))))));
        this.t.z[this.t.nt] = trunc((fr((fr(fr(this.z) + (fr(fr(conto.tz[k2]) * this.m.cos(l))))) + (fr(fr(conto.tx[k2]) * this.m.sin(l))))));
        this.t.y[this.t.nt] = i32(this.y + conto.ty[k2]);
        this.t.skd[this.t.nt] = conto.skd[k2];
        this.t.dam[this.t.nt] = conto.dam[k2];
        this.t.notwall[this.t.nt] = conto.notwall[k2];
        i3 = (Math.abs(l) | 0);
        if (i3 === 180) {
          i3 = 0;
        }
        this.t.radx[this.t.nt] = trunc(Math.abs(fr((fr(fr(conto.tradx[k2]) * this.m.cos(i3))) + (fr(fr(conto.tradz[k2]) * this.m.sin(i3))))));
        this.t.radz[this.t.nt] = trunc(Math.abs(fr((fr(fr(conto.tradx[k2]) * this.m.sin(i3))) + (fr(fr(conto.tradz[k2]) * this.m.cos(i3))))));
        this.t.rady[this.t.nt] = conto.trady[k2];
        let t = this.t;
        t.nt = i32(t.nt + 1);
      }
    }
    let l2 = 0;
    do {
      this.stg[l2] = 0;
      this.keyx[l2] = conto.keyx[l2];
      this.keyz[l2] = conto.keyz[l2];
    } while (++l2 < 6);
  }

  $ctor2(paramInt1, paramInt2, paramInt3, paramMedium, paramTrackers, paramInt4, paramInt5, paramInt6) {
    this.div = 1.0;
    this.iwid = 1.0;
    this.spec = intArray(3);
    this.xextreme = intArray(2);
    this.zextreme = intArray(2);
    this.m = paramMedium;
    this.t = paramTrackers;
    this.x = paramInt4;
    this.z = paramInt5;
    this.y = paramInt6;
    this.xz = 0;
    this.xy = 0;
    this.zy = 0;
    this.grat = 0;
    this.flameheight = 1;
    this.invisiblepiece = 255;
    this.glowlines = false;
    this.glowcustom = false;
    this.glowcolour = intArray(3);
    this.disline = 4;
    this.noline = true;
    this.shadow = false;
    this.grounded = 1.0;
    this.npl = 5;
    this.p = objArray(5);
    this.stg = intArray(6);
    let localRandom = new Random(paramInt1);
    let arrayOfInt1 = intArray(8);
    let arrayOfInt2 = intArray(8);
    let arrayOfInt3 = intArray(8);
    let arrayOfInt4 = intArray(8);
    let arrayOfInt5 = intArray(8);
    let f1 = fr(paramInt2);
    let f2 = fr(paramInt3);
    if (f2 < 1.0) {
      f2 = 1.0;
    }
    if (f2 > 6000.0) {
      f2 = 6000.0;
    }
    if (f1 < 1.0) {
      f1 = 1.0;
    }
    if (f1 > 6000.0) {
      f1 = 6000.0;
    }
    f1 = fr(f1 / 1.5);
    f2 = fr(f2 / 1.5);
    f2 = fr(f2 * (fr(1.0 + (fr(((fr(f1 - 2.0))) * 0.1785999983549118)))));
    let f3 = fr((50.0 + (100.0 * localRandom.nextDouble())));
    arrayOfInt1[0] = i32(-trunc((fr((fr(f3 * f1)) * 0.707099974155426))));
    arrayOfInt2[0] = trunc((fr((fr(f3 * f1)) * 0.707099974155426)));
    f3 = fr((50.0 + (100.0 * localRandom.nextDouble())));
    arrayOfInt1[1] = 0;
    arrayOfInt2[1] = trunc((fr(f3 * f1)));
    f3 = fr((50.0 + (100.0 * localRandom.nextDouble())));
    arrayOfInt1[2] = trunc(((fr(f3 * f1)) * 0.7071));
    arrayOfInt2[2] = trunc(((fr(f3 * f1)) * 0.7071));
    f3 = fr((50.0 + (100.0 * localRandom.nextDouble())));
    arrayOfInt1[3] = trunc((fr(f3 * f1)));
    arrayOfInt2[3] = 0;
    f3 = fr((50.0 + (100.0 * localRandom.nextDouble())));
    arrayOfInt1[4] = trunc(((fr(f3 * f1)) * 0.7071));
    arrayOfInt2[4] = i32(-trunc(((fr(f3 * f1)) * 0.7071)));
    f3 = fr((50.0 + (100.0 * localRandom.nextDouble())));
    arrayOfInt1[5] = 0;
    arrayOfInt2[5] = i32(-trunc((fr(f3 * f1))));
    f3 = fr((50.0 + (100.0 * localRandom.nextDouble())));
    arrayOfInt1[6] = i32(-trunc(((fr(f3 * f1)) * 0.7071)));
    arrayOfInt2[6] = i32(-trunc(((fr(f3 * f1)) * 0.7071)));
    f3 = fr((50.0 + (100.0 * localRandom.nextDouble())));
    arrayOfInt1[7] = i32(-trunc((fr(f3 * f1))));
    arrayOfInt2[7] = 0;
    for (let i = 0; i < 8; i = i32(i + 1)) {
      arrayOfInt3[i] = trunc((arrayOfInt1[i] * ((0.2 + (0.4 * localRandom.nextDouble())))));
      arrayOfInt4[i] = trunc((arrayOfInt2[i] * ((0.2 + (0.4 * localRandom.nextDouble())))));
      arrayOfInt5[i] = i32(-trunc((((10.0 + (15.0 * localRandom.nextDouble()))) * f2)));
    }
    this.maxR = 0;
    for (let i = 0; i < 8; i = i32(i + 1)) {
      let j = i32(i - 1);
      if (j === -1) {
        j = 7;
      }
      let k = i32(i + 1);
      if (k === 8) {
        k = 0;
      }
      arrayOfInt1[i] = idiv(((i32((idiv(((i32(arrayOfInt1[j] + arrayOfInt1[k]))), 2)) + arrayOfInt1[i]))), 2);
      arrayOfInt2[i] = idiv(((i32((idiv(((i32(arrayOfInt2[j] + arrayOfInt2[k]))), 2)) + arrayOfInt2[i]))), 2);
      arrayOfInt3[i] = idiv(((i32((idiv(((i32(arrayOfInt3[j] + arrayOfInt3[k]))), 2)) + arrayOfInt3[i]))), 2);
      arrayOfInt4[i] = idiv(((i32((idiv(((i32(arrayOfInt4[j] + arrayOfInt4[k]))), 2)) + arrayOfInt4[i]))), 2);
      arrayOfInt5[i] = idiv(((i32((idiv(((i32(arrayOfInt5[j] + arrayOfInt5[k]))), 2)) + arrayOfInt5[i]))), 2);
      let n = trunc(Math.sqrt(i32((Math.imul(arrayOfInt1[i], arrayOfInt1[i])) + (Math.imul(arrayOfInt2[i], arrayOfInt2[i])))));
      if (n > this.maxR) {
        this.maxR = n;
      }
      n = trunc(Math.sqrt(i32((i32((Math.imul(arrayOfInt3[i], arrayOfInt3[i])) + (Math.imul(arrayOfInt5[i], arrayOfInt5[i])))) + (Math.imul(arrayOfInt4[i], arrayOfInt4[i])))));
      if (n > this.maxR) {
        this.maxR = n;
      }
    }
    this.disp = 90;
    let arrayOfInt6 = intArray(3);
    let f4 = -1.0;
    let f5 = fr(((fr((fr(f1 / f2)) - 0.33000001311302185))) / 33.400001525878906);
    if (f5 < 0.005) {
      f5 = 0.0;
    }
    if (f5 > 0.057) {
      f5 = 0.05700000002980232;
    }
    for (let n = 0; n < 4; n = i32(n + 1)) {
      let i2 = Math.imul(n, 2);
      let i3 = i32(i2 + 2);
      if (i3 === 8) {
        i3 = 0;
      }
      let arrayOfInt7 = intArray(6);
      let arrayOfInt8 = intArray(6);
      let arrayOfInt9 = intArray(6);
      arrayOfInt7[0] = arrayOfInt1[i2];
      arrayOfInt7[1] = arrayOfInt1[i32(i2 + 1)];
      arrayOfInt7[2] = arrayOfInt1[i3];
      arrayOfInt7[5] = arrayOfInt3[i2];
      arrayOfInt7[4] = arrayOfInt3[i32(i2 + 1)];
      arrayOfInt7[3] = arrayOfInt3[i3];
      arrayOfInt9[0] = arrayOfInt2[i2];
      arrayOfInt9[1] = arrayOfInt2[i32(i2 + 1)];
      arrayOfInt9[2] = arrayOfInt2[i3];
      arrayOfInt9[5] = arrayOfInt4[i2];
      arrayOfInt9[4] = arrayOfInt4[i32(i2 + 1)];
      arrayOfInt9[3] = arrayOfInt4[i3];
      arrayOfInt8[0] = 0;
      arrayOfInt8[2] = (arrayOfInt8[1] = 0);
      arrayOfInt8[5] = arrayOfInt5[i2];
      arrayOfInt8[4] = arrayOfInt5[i32(i2 + 1)];
      arrayOfInt8[3] = arrayOfInt5[i3];
      for (f3 = fr((((0.17 - f5)) * localRandom.nextDouble())); Math.abs(fr(f4 - f3)) < (0.03 - (fr(f5 * 0.17599999904632568))); f3 = (f4 = fr((((0.17 - f5)) * localRandom.nextDouble())))) {
      }
      for (let i4 = 0; i4 < 3; i4 = i32(i4 + 1)) {
        arrayOfInt6[i4] = idiv(((i32(this.m.cgrnd[i4] + this.m.cpol[i4]))), 2);
      }
      this.p[n] = new Plane(this.m, this.t, arrayOfInt7, arrayOfInt9, arrayOfInt8, 6, arrayOfInt6, 3, -8, 0, 0, 0, 0, this.disline, 0, false, 0, false);
    }
    f3 = fr((0.02 * localRandom.nextDouble()));
    for (let n = 0; n < 3; n = i32(n + 1)) {
      arrayOfInt6[n] = idiv(((i32(this.m.cgrnd[n] + this.m.cpol[n]))), 2);
    }
    this.p[4] = new Plane(this.m, this.t, arrayOfInt3, arrayOfInt4, arrayOfInt5, 8, arrayOfInt6, 3, -8, 0, 0, 0, 0, this.disline, 0, false, 0, false);
    let arrayOfInt10 = intArray(2);
    let arrayOfInt11 = intArray(2);
    for (let i3 = 0; i3 < 4; i3 = i32(i3 + 1)) {
      let i5 = i32((Math.imul(i3, 2)) + 1);
      this.t.y[this.t.nt] = idiv(arrayOfInt5[i5], 2);
      this.t.rady[this.t.nt] = (Math.abs(idiv(arrayOfInt5[i5], 2)) | 0);
      if ((i3 === 0) || (i3 === 2)) {
        this.t.z[this.t.nt] = idiv(((i32(arrayOfInt2[i5] + arrayOfInt4[i5]))), 2);
        this.t.radz[this.t.nt] = (Math.abs(i32(this.t.z[this.t.nt] - arrayOfInt2[i5])) | 0);
        i5 = i32((Math.imul(i3, 2)) + 2);
        if (i5 === 8) {
          i5 = 0;
        }
        this.t.x[this.t.nt] = idiv(((i32(arrayOfInt1[Math.imul(i3, 2)] + arrayOfInt1[i5]))), 2);
        this.t.radx[this.t.nt] = (Math.abs(i32(this.t.x[this.t.nt] - arrayOfInt1[Math.imul(i3, 2)])) | 0);
      } else {
        this.t.x[this.t.nt] = idiv(((i32(arrayOfInt1[i5] + arrayOfInt3[i5]))), 2);
        this.t.radx[this.t.nt] = (Math.abs(i32(this.t.x[this.t.nt] - arrayOfInt1[i5])) | 0);
        i5 = i32((Math.imul(i3, 2)) + 2);
        if (i5 === 8) {
          i5 = 0;
        }
        this.t.z[this.t.nt] = idiv(((i32(arrayOfInt2[Math.imul(i3, 2)] + arrayOfInt2[i5]))), 2);
        this.t.radz[this.t.nt] = (Math.abs(i32(this.t.z[this.t.nt] - arrayOfInt2[Math.imul(i3, 2)])) | 0);
      }
      if (i3 === 0) {
        arrayOfInt11[0] = i32(this.t.z[this.t.nt] - this.t.radz[this.t.nt]);
        this.t.zy[this.t.nt] = trunc((Math.atan(idiv(this.t.rady[this.t.nt], this.t.radz[this.t.nt])) / 0.0174532925199433));
        if (this.t.zy[this.t.nt] > 40) {
          this.t.zy[this.t.nt] = 40;
        }
        this.t.xy[this.t.nt] = 0;
      }
      if (i3 === 1) {
        arrayOfInt10[0] = i32(this.t.x[this.t.nt] - this.t.radx[this.t.nt]);
        this.t.xy[this.t.nt] = trunc((Math.atan(idiv(this.t.rady[this.t.nt], this.t.radx[this.t.nt])) / 0.0174532925199433));
        if (this.t.xy[this.t.nt] > 40) {
          this.t.xy[this.t.nt] = 40;
        }
        this.t.zy[this.t.nt] = 0;
      }
      if (i3 === 2) {
        arrayOfInt11[1] = i32(this.t.z[this.t.nt] + this.t.radz[this.t.nt]);
        this.t.zy[this.t.nt] = i32(-trunc((Math.atan(idiv(this.t.rady[this.t.nt], this.t.radz[this.t.nt])) / 0.0174532925199433)));
        if (this.t.zy[this.t.nt] < -40) {
          this.t.zy[this.t.nt] = -40;
        }
        this.t.xy[this.t.nt] = 0;
      }
      if (i3 === 3) {
        arrayOfInt10[1] = i32(this.t.x[this.t.nt] + this.t.radx[this.t.nt]);
        this.t.xy[this.t.nt] = i32(-trunc((Math.atan(idiv(this.t.rady[this.t.nt], this.t.radx[this.t.nt])) / 0.0174532925199433)));
        if (this.t.xy[this.t.nt] < -40) {
          this.t.xy[this.t.nt] = -40;
        }
        this.t.zy[this.t.nt] = 0;
      }
      let x = this.t.x;
      let nt = this.t.nt;
      x[nt] = i32(x[nt] + this.x);
      let z = this.t.z;
      let nt2 = this.t.nt;
      z[nt2] = i32(z[nt2] + this.z);
      let y = this.t.y;
      let nt3 = this.t.nt;
      y[nt3] = i32(y[nt3] + this.y);
      for (let i6 = 0; i6 < 3; i6 = i32(i6 + 1)) {
        this.t.c[this.t.nt][i6] = this.p[i3].oc[i6];
      }
      this.t.skd[this.t.nt] = 2;
      this.t.dam[this.t.nt] = 1;
      this.t.notwall[this.t.nt] = false;
      let rady = this.t.rady;
      let nt4 = this.t.nt;
      rady[nt4] = i32(rady[nt4] + 10);
      let t = this.t;
      t.nt = i32(t.nt + 1);
    }
    this.t.y[this.t.nt] = 0;
    for (let i3 = 0; i3 < 8; i3 = i32(i3 + 1)) {
      let y2 = this.t.y;
      let nt5 = this.t.nt;
      y2[nt5] = i32(y2[nt5] + arrayOfInt5[i3]);
    }
    let y3 = this.t.y;
    let nt6 = this.t.nt;
    y3[nt6] = idiv(y3[nt6], 8);
    let y4 = this.t.y;
    let nt7 = this.t.nt;
    y4[nt7] = i32(y4[nt7] + this.y);
    this.t.rady[this.t.nt] = 20000;
    this.t.radx[this.t.nt] = i32(arrayOfInt10[0] - arrayOfInt10[1]);
    this.t.radz[this.t.nt] = i32(arrayOfInt11[0] - arrayOfInt11[1]);
    this.t.x[this.t.nt] = i32((idiv(((i32(arrayOfInt10[0] + arrayOfInt10[1]))), 2)) + this.x);
    this.t.z[this.t.nt] = i32((idiv(((i32(arrayOfInt11[0] + arrayOfInt11[1]))), 2)) + this.z);
    this.t.zy[this.t.nt] = 0;
    this.t.xy[this.t.nt] = 0;
    for (let i3 = 0; i3 < 3; i3 = i32(i3 + 1)) {
      this.t.c[this.t.nt][i3] = this.p[4].oc[i3];
      this.t.oc[this.t.nt][i3] = this.p[4].oc[i3];
    }
    this.t.skd[this.t.nt] = 4;
    this.t.dam[this.t.nt] = 1;
    this.t.notwall[this.t.nt] = false;
    let t2 = this.t;
    t2.nt = i32(t2.nt + 1);
  }

  pdust(i, g, j) {
    // The base port's look (web/ContO.js pdust: a translucent octagon tinted by
    // the road under it, whitened, radii kept per puff) on Extended's own puff
    // lifecycle -- dust()'s four slots, stg 0..4, the dov pre/post-face
    // handshake, the drift and rise -- which Record also replays. The jar's
    // own pdust (an older one) drew an opaque star of ground/sky/fog that read
    // as white blobs; replaced at the user's request.
    // Randoms run on a tick only: an interpolated frame redraws the same puff.
    const tick = !this.m.interpolating;
    if ((Math.imul(j, this.dov[i])) > 0) {
      const m = this.m;
      const white = intArray(3);
      for (let c = 0; c < 3; c++) {
        white[c] = trunc(fr(255.0 + fr(255.0 * fr(m.snap[c] / 100.0))));
        if (white[c] > 255) white[c] = 255;
        if (white[c] < 0) white[c] = 0;
      }
      let r = idiv(m.crgrnd[0] + white[0], 2);
      let gr = idiv(m.crgrnd[1] + white[1], 2);
      let b = idiv(m.crgrnd[2] + white[2], 2);
      let sbln = 0.6;
      let nodust = true;
      for (let k2 = 0; k2 < this.t.nt; k2 = i32(k2 + 1)) {
        if (!m.effect[9] || (this.t.y[k2] === this.groundlevel)) {
          if (this.groundlevel >= 0) {
            nodust = false;
          }
          if (((((Math.abs(this.t.zy[k2]) | 0) !== 90) && ((Math.abs(this.t.xy[k2]) | 0) !== 90)) && ((Math.abs(i32(this.sx[i] - this.t.x[k2])) | 0) < this.t.radx[k2])) && ((Math.abs(i32(this.sz[i] - this.t.z[k2])) | 0) < this.t.radz[k2])) {
            nodust = false;
            if (this.t.skd[k2] === 0) sbln = 0.2;
            if (this.t.skd[k2] === 1) sbln = 0.4;
            if (this.t.skd[k2] === 2) sbln = 0.45;
            r = idiv(this.t.c[k2][0] + white[0], 2);
            gr = idiv(this.t.c[k2][1] + white[1], 2);
            b = idiv(this.t.c[k2][2] + white[2], 2);
          }
        }
      }
      if (this.sy[i] > 250) {
        this.sy[i] = 250;
      }
      let l2 = i32(m.cx + trunc((fr((fr(fr(((i32((i32(this.sx[i] - m.x)) - m.cx)))) * m.cos(m.xz))) - (fr(fr(((i32((i32(this.sz[i] - m.z)) - m.cz)))) * m.sin(m.xz)))))));
      let i3 = i32(m.cz + trunc((fr((fr(fr(((i32((i32(this.sx[i] - m.x)) - m.cx)))) * m.sin(m.xz))) + (fr(fr(((i32((i32(this.sz[i] - m.z)) - m.cz)))) * m.cos(m.xz)))))));
      let j3 = i32(m.cy + trunc((fr((fr(fr(((i32((i32(this.sy[i] - m.y)) - m.cy)))) * m.cos(m.zy))) - (fr(fr(((i32(i3 - m.cz)))) * m.sin(m.zy)))))));
      i3 = i32(m.cz + trunc((fr((fr(fr(((i32((i32(this.sy[i] - m.y)) - m.cy)))) * m.sin(m.zy))) + (fr(fr(((i32(i3 - m.cz)))) * m.cos(m.zy)))))));
      let k3 = trunc(Math.sqrt(i32((i32((Math.imul(((i32(m.cy - j3))), (i32(m.cy - j3)))) + (Math.imul(((i32(m.cx - l2))), (i32(m.cx - l2)))))) + (Math.imul(i3, i3)))));
      for (let l3 = 0; l3 < 16; l3++) {
        if (k3 > m.fade[l3]) {
          r = idiv(r * m.fogd + m.cfade[0], m.fogd + 1);
          gr = idiv(gr * m.fogd + m.cfade[1], m.fogd + 1);
          b = idiv(b * m.fogd + m.cfade[2], m.fogd + 1);
        }
      }
      if (tick) {
        if ((i32((Math.abs(this.scx[i]) | 0) + (Math.abs(this.scz[i]) | 0))) > 150) {
          let sy = this.sy;
          sy[i] = trunc(fr(fr(sy[i]) - ((fr(3.0 + (fr(27.0 * this.smag[i])))))));
        } else {
          let sy2 = this.sy;
          sy2[i] = trunc(fr(fr(sy2[i]) - ((fr(23.0 + (fr(7.0 * this.smag[i])))))));
        }
        let sx = this.sx;
        sx[i] = trunc(fr(fr(sx[i]) + ((fr(fr(this.scx[i]) / ((fr(fr(((i32(this.stg[i] + 1)))) * this.smag[i]))))))));
        let sz = this.sz;
        sz[i] = trunc(fr(fr(sz[i]) + ((fr(fr(this.scz[i]) / ((fr(fr(((i32(this.stg[i] + 1)))) * this.smag[i]))))))));
      }
      // the base's per-vertex radii: rolled when the puff is born (osmag is
      // the base dust()'s size x speed factor), grown every tick
      this.vmag ??= objArray(6).map(() => floatArray(8));
      const v = this.vmag[i];
      if (this.stg[i] === 1 && tick) {
        let n9 = fr((Math.sqrt(Math.imul(this.scx[i], this.scx[i]) + Math.imul(this.scz[i], this.scz[i])) - 40.0) / 160.0);
        if (n9 > 1.0) n9 = 1.0;
        const osmag = fr(this.smag[i] * n9);
        for (let n4 = 0; n4 < 8; ++n4) v[n4] = fr(fr(osmag * m.random()) * 50.0);
        for (let n5 = 0; n5 < 8; ++n5) {
          const n6 = n5 === 0 ? 7 : n5 - 1;
          const n7 = n5 === 7 ? 0 : n5 + 1;
          v[n5] = fr(fr(fr(fr(v[n6] + v[n7]) / 2.0) + v[n5]) / 2.0);
        }
        v[6] = v[7];
      }
      const A = 0.9238, B = 0.3826;
      const ai = intArray(8);
      const ai2 = intArray(8);
      ai[0] = this.xs(trunc(l2 + fr(fr(v[0] * A) * 1.5)), i3);
      ai2[0] = this.ys(trunc(j3 + fr(fr(v[0] * B) * 1.5)), i3);
      ai[1] = this.xs(trunc(l2 + fr(fr(v[1] * A) * 1.5)), i3);
      ai2[1] = this.ys(trunc(j3 - fr(fr(v[1] * B) * 1.5)), i3);
      ai[2] = this.xs(trunc(l2 + fr(v[2] * B)), i3);
      ai2[2] = this.ys(trunc(j3 - fr(v[2] * A)), i3);
      ai[3] = this.xs(trunc(l2 - fr(v[3] * B)), i3);
      ai2[3] = this.ys(trunc(j3 - fr(v[3] * A)), i3);
      ai[4] = this.xs(trunc(l2 - fr(fr(v[4] * A) * 1.5)), i3);
      ai2[4] = this.ys(trunc(j3 - fr(fr(v[4] * B) * 1.5)), i3);
      ai[5] = this.xs(trunc(l2 - fr(fr(v[5] * A) * 1.5)), i3);
      ai2[5] = this.ys(trunc(j3 + fr(fr(v[5] * B) * 1.5)), i3);
      ai[6] = this.xs(trunc(l2 - fr(fr(v[6] * B) * 1.7)), i3);
      ai2[6] = this.ys(trunc(j3 + fr(v[6] * A)), i3);
      ai[7] = this.xs(trunc(l2 + fr(fr(v[7] * B) * 1.7)), i3);
      ai2[7] = this.ys(trunc(j3 + fr(v[7] * A)), i3);
      if (tick) {
        for (let n12 = 0; n12 < 7; ++n12) v[n12] = fr(v[n12] + fr(5.0 + fr(m.random() * 15.0)));
        v[7] = v[6];
      }
      let flag = true;
      if (nodust) {
        flag = false;
      }
      let j4 = 0;
      let k4 = 0;
      let l4 = 0;
      let i5 = 0;
      let j5 = 0;
      do {
        if ((ai2[j5] < 0) || (i3 < 10)) {
          j4 = i32(j4 + 1);
        }
        if ((ai2[j5] > m.h) || (i3 < 10)) {
          k4 = i32(k4 + 1);
        }
        if ((ai[j5] < 0) || (i3 < 10)) {
          l4 = i32(l4 + 1);
        }
        if ((ai[j5] > m.w) || (i3 < 10)) {
          i5 = i32(i5 + 1);
        }
        if ((ai2[j5] < 45) && (m.flex !== 0)) {
          m.flex = 0;
        }
      } while (++j5 < 8);
      if ((((l4 === 4) || (j4 === 4)) || (k4 === 4)) || (i5 === 4)) {
        flag = false;
      }
      if (flag) {
        // ponytail: /5, not the base's /8 -- Extended's puff lives 4 stages, not 7
        // (a longer life would change dust()'s spawn cadence and Record's replays)
        let alpha = fr(sbln - fr(this.stg[i] * fr(sbln / 5.0)));
        if (m.effect[4]) {
          alpha = fr(alpha * (60 / 255));   // the jar's fadedshad
        }
        g.setColor(r, gr, b);
        g.setComposite(alpha);
        g.fillPolygon(ai, ai2, 8);
        g.setComposite(1.0);
      }
      if (!tick) {
        return;
      }
      if (this.dov[i] === 1) {
        this.dov[i] = -1;
      }
      if (this.stg[i] === 4) {
        this.stg[i] = 0;
      } else {
        let stg = this.stg;
        ++stg[i];
        if ((this.stg[i] === 2) && this.fulls[i]) {
          this.dov[i] = 0;
        }
      }
    } else if (this.dov[i] === 0 && tick) {
      this.dov[i] = 1;
    }
  }

  ys(i, j) {
    if (j < 50) {
      j = 50;
    }
    return i32((idiv((Math.imul(((i32(j - this.m.focus_point))), (i32(this.m.cy - i)))), j)) + i);
  }

  d(g) {
    if (this.dist !== 0) {
      this.dist = 0;
    }
    let i = i32(this.m.cx + trunc((fr((fr(fr(((i32((i32(this.x - this.m.x)) - this.m.cx)))) * this.m.cos(this.m.xz))) - (fr(fr(((i32((i32(this.z - this.m.z)) - this.m.cz)))) * this.m.sin(this.m.xz)))))));
    let j = i32(this.m.cz + trunc((fr((fr(fr(((i32((i32(this.x - this.m.x)) - this.m.cx)))) * this.m.sin(this.m.xz))) + (fr(fr(((i32((i32(this.z - this.m.z)) - this.m.cz)))) * this.m.cos(this.m.xz)))))));
    let k = i32(this.m.cz + trunc((fr((fr(fr(((i32((i32(this.y - this.m.y)) - this.m.cy)))) * this.m.sin(this.m.zy))) + (fr(fr(((i32(j - this.m.cz)))) * this.m.cos(this.m.zy)))))));
    let l = i32(this.xs(i32(i + this.maxR), k) - this.xs(i32(i - this.maxR), k));
    if (((((this.xs(i32(i + (Math.imul(this.maxR, 2))), k) > 0) && (this.xs(i32(i - (Math.imul(this.maxR, 2))), k) < this.m.w)) && (k > i32(-this.maxR))) && (((k < (i32(this.m.fade[this.disline] + this.maxR))) || this.m.trk))) && (((l > this.disp) || this.m.trk))) {
      if (this.shadow) {
        if (!this.m.crs) {
          if (k < 2000) {
            let flag = false;
            for (let l2 = i32(this.t.nt - 1); l2 >= 0; l2 = i32(l2 - 1)) {
              if (((((Math.abs(this.t.zy[l2]) | 0) !== 90) && ((Math.abs(this.t.xy[l2]) | 0) !== 90)) && ((Math.abs(i32(this.x - this.t.x[l2])) | 0) < (i32(this.t.radx[l2] + this.maxR)))) && ((Math.abs(i32(this.z - this.t.z[l2])) | 0) < (i32(this.t.radz[l2] + this.maxR)))) {
                flag = true;
                break;
              }
            }
            if (flag) {
              for (let i2 = 0; i2 < this.npl; i2 = i32(i2 + 1)) {
                this.p[i2].s(g, i32(this.x - this.m.x), i32(this.y - this.m.y), i32(this.z - this.m.z), this.xz, this.xy, this.zy, 0, i32(this.groundlevel - this.m.y), this.teleported, this.telefade, this.spec, this.outoftrack);
              }
            } else {
              let j2 = i32(this.m.cy + trunc((fr((fr(fr(((i32((i32(this.groundlevel - this.m.y)) - this.m.cy)))) * this.m.cos(this.m.zy))) - (fr(fr(((i32(j - this.m.cz)))) * this.m.sin(this.m.zy)))))));
              let k2 = i32(this.m.cz + trunc((fr((fr(fr(((i32((i32(this.groundlevel - this.m.y)) - this.m.cy)))) * this.m.sin(this.m.zy))) + (fr(fr(((i32(j - this.m.cz)))) * this.m.cos(this.m.zy)))))));
              if ((this.ys(i32(j2 + this.maxR), k2) > 0) && (this.ys(i32(j2 - this.maxR), k2) < this.m.h)) {
                for (let l3 = 0; l3 < this.npl; l3 = i32(l3 + 1)) {
                  this.p[l3].s(g, i32(this.x - this.m.x), i32(this.y - this.m.y), i32(this.z - this.m.z), this.xz, this.xy, this.zy, 1, i32(this.groundlevel - this.m.y), this.teleported, this.telefade, this.spec, this.outoftrack);
                }
              }
            }
            this.m.addsp(i32(this.x - this.m.x), i32(this.z - this.m.z), trunc((this.maxR * 0.8)));
          } else {
            this.lowshadow(g, k);
          }
        } else {
          for (let i3 = 0; i3 < this.npl; i3 = i32(i3 + 1)) {
            this.p[i3].s(g, i32(this.x - this.m.x), i32(this.y - this.m.y), i32(this.z - this.m.z), this.xz, this.xy, this.zy, 2, i32(this.groundlevel - this.m.y), this.teleported, this.telefade, this.spec, this.outoftrack);
          }
        }
      }
      let j3 = i32(this.m.cy + trunc((fr((fr(fr(((i32((i32(this.y - this.m.y)) - this.m.cy)))) * this.m.cos(this.m.zy))) - (fr(fr(((i32(j - this.m.cz)))) * this.m.sin(this.m.zy)))))));
      if ((this.ys(i32(j3 + this.maxR), k) > 0) && (this.ys(i32(j3 - this.maxR), k) < this.m.h)) {
        if (this.elec) {
          this.electrify(g);
        }
        if (this.fix) {
          this.fixit(g);
        }
        if (this.m.showsnow) {
          this.drawsnow(g, this.m.snowno, this.m.snowheight);
        }
        let groundcolour = this.m.groundcolour;
        if ((this.checkpoint !== 0) && ((i32(this.checkpoint - 1)) === this.m.checkpoint)) {
          l = -1;
        }
        let ai2 = intArray(this.npl);
        let i4 = 0;
        do {
          if (((this.stg[i4] !== 0) && !this.teleported) && !this.m.effect[11]) {
            this.pdust(i4, g, -1);
          }
        } while (++i4 < 4);
        // ext-patch face-order: the jar's pairwise rank count is this stable sort --
        // av descending, ties by index -- in O(n log n) (web/tools/ext-patches.mjs)
        for (let j4 = 0; j4 < this.npl; j4 = i32(j4 + 1)) {
          ai2[j4] = j4;
        }
        {
          const p = this.p;
          ai2.sort((a, b) => (p[b].av - p[a].av) || (a - b));
        }
        for (let l4 = 0; l4 < this.npl; l4 = i32(l4 + 1)) {
          if (((((((this.m.switchfase === 0) || (this.m.switchfase === 10)) || (this.m.switchfase === 20))) && this.m.effect[2]) && !this.m.trk) && (this.p[ai2[l4]].embos === 0)) {
            this.p[ai2[l4]].recolour();
          }
          if (this.m.effect[10] && !this.isacar) {
            this.glowlines = true;
            this.rainbow(ai2[l4]);
          }
          this.p[ai2[l4]].d(g, i32(this.x - this.m.x), i32(this.y - this.m.y), i32(this.z - this.m.z), this.xz, this.xy, this.zy, trunc(this.wxz), this.wzy, this.noline, l, this.weakstage, this.shadowcar, this.greenflame, this.flameheight, this.invisiblepiece, this.glowlines, this.glowcolour, this.spatk, this.freeze, this.weaken, this.leech, this.strswap, this.spec[0], this.spec[1], this.spec[2], i32(this.groundlevel - this.m.y), this.playerglow, this.teleported, this.telefade, this.floorguardian, this.dmgcolours, this.isacar, this.lightup, this.outoftrack);
          if ((((this.p[ai2[l4]].master !== 0) && (this.stg[i32(this.p[ai2[l4]].master - 1)] !== 0)) && !this.teleported) && !this.m.effect[11]) {
            this.pdust(i32(this.p[ai2[l4]].master - 1), g, 1);
          }
        }
        let whichgrounded = this.grounded;
        if (this.m.effect[9]) {
          whichgrounded = this.fakegrounded;
        }
        this.dist = trunc((Math.sqrt(trunc(Math.sqrt(i32((i32((Math.imul(((i32((i32(this.m.x + this.m.cx)) - this.x))), (i32((i32(this.m.x + this.m.cx)) - this.x)))) + (Math.imul(((i32(this.m.z - this.z))), (i32(this.m.z - this.z)))))) + (Math.imul(((i32((i32(this.m.y + this.m.cy)) - this.y))), (i32((i32(this.m.y + this.m.cy)) - this.y)))))))) * whichgrounded));
      }
    }
    if (this.dist === 0 && !this.m.interpolating) {   // base port: an off-screen puff still ages at tick rate
      let k4 = 0;
      do {
        if (this.stg[k4] !== 0) {
          if (this.stg[k4] === 4) {
            this.stg[k4] = 0;
          } else {
            let stg = this.stg;
            let n5 = k4;
            ++stg[n5];
          }
        }
      } while (++k4 < 4);
    }
  }

  getpy(i, j, k) {
    return i32((i32((Math.imul((idiv(((i32(i - this.x))), 10)), (idiv(((i32(i - this.x))), 10)))) + (Math.imul((idiv(((i32(j - this.y))), 10)), (idiv(((i32(j - this.y))), 10)))))) + (Math.imul((idiv(((i32(k - this.z))), 10)), (idiv(((i32(k - this.z))), 10)))));
  }

  rainbow(id) {
    if (((id % 14)) <= 1) {
      this.glowcolour[0] = 200;
      this.glowcolour[1] = 0;
      this.glowcolour[2] = 0;
    }
    if ((((id % 14)) === 2) || (((id % 14)) === 3)) {
      this.glowcolour[0] = 200;
      this.glowcolour[1] = 100;
      this.glowcolour[2] = 0;
    }
    if ((((id % 14)) === 4) || (((id % 14)) === 5)) {
      this.glowcolour[0] = 200;
      this.glowcolour[1] = 200;
      this.glowcolour[2] = 0;
    }
    if ((((id % 14)) === 6) || (((id % 14)) === 7)) {
      this.glowcolour[0] = 0;
      this.glowcolour[1] = 200;
      this.glowcolour[2] = 0;
    }
    if ((((id % 14)) === 8) || (((id % 14)) === 9)) {
      this.glowcolour[0] = 0;
      this.glowcolour[1] = 0;
      this.glowcolour[2] = 200;
    }
    if ((((id % 14)) === 10) || (((id % 14)) === 11)) {
      this.glowcolour[0] = 59;
      this.glowcolour[1] = 0;
      this.glowcolour[2] = 102;
    }
    if ((((id % 14)) === 12) || (((id % 14)) === 13)) {
      this.glowcolour[0] = 116;
      this.glowcolour[1] = 0;
      this.glowcolour[2] = 165;
    }
  }

  rot(ai, ai1, i, j, k, l) {
    if (k !== 0) {
      // ext-patch rot-hoist: one table read per rotation, not four per vertex (web/tools/ext-patches.mjs)
      const cos = this.m.cos(k), sin = this.m.sin(k);
      for (let i2 = 0; i2 < l; i2 = i32(i2 + 1)) {
        let j2 = ai[i2];
        let k2 = ai1[i2];
        ai[i2] = i32(i + trunc((fr((fr(fr(((i32(j2 - i)))) * cos)) - (fr(fr(((i32(k2 - j)))) * sin))))));
        ai1[i2] = i32(j + trunc((fr((fr(fr(((i32(j2 - i)))) * sin)) + (fr(fr(((i32(k2 - j)))) * cos))))));
      }
    }
  }

  dust(i, f, f1, f2, f3, f4, f5, flag, j) {
    let flag2 = false;
    if ((j > 5) && (((i === 0) || (i === 2)))) {
      flag2 = true;
    }
    if ((j < -5) && (((i === 1) || (i === 3)))) {
      flag2 = true;
    }
    if (((this.stg[i] === 0) && ((fr(Math.abs(f3) + Math.abs(f4))) > 100.0)) && !flag2) {
      this.sx[i] = trunc(f);
      this.sy[i] = trunc(f1);
      this.sz[i] = trunc(f2);
      this.stg[i] = 1;
      this.dov[i] = -1;
      this.smag[i] = f5;
      this.scx[i] = trunc(f3);
      this.scz[i] = trunc(f4);
      this.fulls[i] = flag;
    }
  }

  getvalue(s, s1, i) {
    let k = 0;
    let s2 = '';
    for (let j = i32(s.length + 1); j < s1.length; j = i32(j + 1)) {
      let s3 = new StringBuilder().append(String.fromCharCode(charAt(s1, j))).toString();
      if ((s3 === ',') || (s3 === ')')) {
        k = i32(k + 1);
        j = i32(j + 1);
      }
      if (k === i) {
        s2 = jstr(s2) + String.fromCharCode(charAt(s1, j));
      }
    }
    return Integer.valueOf(s2);
  }

  xs(i, j) {
    if (j < 50) {
      j = 50;
    }
    return i32((idiv((Math.imul(((i32(j - this.m.focus_point))), (i32(this.m.cx - i)))), j)) + i);
  }

  lowshadow(g, i) {
    let ai = intArray(4);
    let ai2 = intArray(4);
    let ai3 = intArray(4);
    let byte0 = 1;
    let j = 0;
    for (j = (Math.abs(this.zy) | 0); j > 270; j = i32(j - 360)) {
    }
    j = (Math.abs(j) | 0);
    if (j > 90) {
      byte0 = -1;
    }
    ai[0] = trunc((((this.keyx[0] * 1.2) + this.x) - this.m.x));
    ai3[0] = trunc(((((Math.imul(((i32(this.keyz[0] + 30))), byte0)) * 1.2) + this.z) - this.m.z));
    ai[1] = trunc((((this.keyx[1] * 1.2) + this.x) - this.m.x));
    ai3[1] = trunc(((((Math.imul(((i32(this.keyz[1] + 30))), byte0)) * 1.2) + this.z) - this.m.z));
    ai[2] = trunc((((this.keyx[3] * 1.2) + this.x) - this.m.x));
    ai3[2] = trunc(((((Math.imul(((i32(this.keyz[3] - 30))), byte0)) * 1.2) + this.z) - this.m.z));
    ai[3] = trunc((((this.keyx[2] * 1.2) + this.x) - this.m.x));
    ai3[3] = trunc(((((Math.imul(((i32(this.keyz[2] - 30))), byte0)) * 1.2) + this.z) - this.m.z));
    this.rot(ai, ai3, i32(this.x - this.m.x), i32(this.z - this.m.z), this.xz, 4);
    let coladj = Int32Array.from([this.m.cgrnd[0], this.m.cgrnd[1], this.m.cgrnd[2]]);
    this.sred = trunc((fr(coladj[0]) / 1.5));
    this.sgreen = trunc((fr(coladj[1]) / 1.5));
    this.sblue = trunc((fr(coladj[2]) / 1.5));
    let j2 = 0;
    do {
      ai2[j2] = i32(this.groundlevel - this.m.y);
    } while (++j2 < 4);
    for (let k1 = i32(this.t.nt - 1); k1 >= 0; k1 = i32(k1 - 1)) {
      if ((this.t.y[k1] === this.groundlevel) || !this.m.effect[9]) {
        if ((this.t.y[k1] <= this.groundlevel) || this.m.effect[9]) {
          let l1 = 0;
          let j3 = 0;
          do {
            if (((((Math.abs(this.t.zy[k1]) | 0) !== 90) && ((Math.abs(this.t.xy[k1]) | 0) !== 90)) && ((Math.abs(i32(ai[j3] - ((i32(this.t.x[k1] - this.m.x))))) | 0) < this.t.radx[k1])) && ((Math.abs(i32(ai3[j3] - ((i32(this.t.z[k1] - this.m.z))))) | 0) < this.t.radz[k1])) {
              l1 = i32(l1 + 1);
            }
          } while (++j3 < 4);
          if (l1 > 2) {
            j3 = 0;
            do {
              ai2[j3] = i32(this.t.y[k1] - this.m.y);
              if (this.t.zy[k1] !== 0) {
                let array = ai2;
                let n = j3;
                array[n] = trunc(fr(fr(array[n]) + ((fr((fr((fr(fr(((i32(ai3[j3] - ((i32((i32(this.t.z[k1] - this.m.z)) - this.t.radz[k1]))))))) * this.m.sin(this.t.zy[k1]))) / this.m.sin(i32(90 - this.t.zy[k1])))) - (fr((fr(fr(this.t.radz[k1]) * this.m.sin(this.t.zy[k1]))) / this.m.sin(i32(90 - this.t.zy[k1])))))))));
              }
              if (this.t.xy[k1] !== 0) {
                let array2 = ai2;
                let n2 = j3;
                array2[n2] = trunc(fr(fr(array2[n2]) + ((fr((fr((fr(fr(((i32(ai[j3] - ((i32((i32(this.t.x[k1] - this.m.x)) - this.t.radx[k1]))))))) * this.m.sin(this.t.xy[k1]))) / this.m.sin(i32(90 - this.t.xy[k1])))) - (fr((fr(fr(this.t.radx[k1]) * this.m.sin(this.t.xy[k1]))) / this.m.sin(i32(90 - this.t.xy[k1])))))))));
              }
            } while (++j3 < 4);
            let red = this.t.c[k1][0];
            let green = this.t.c[k1][1];
            let blue = this.t.c[k1][2];
            if ((((((this.m.switchfase === 0) || (this.m.switchfase === 10)) || (this.m.switchfase === 20))) && this.m.effect[2]) && !this.m.trk) {
              red = trunc((fr(fr(this.t.oc[k1][0]) + (fr(fr(this.t.oc[k1][0]) * ((fr(fr(this.m.snap[0]) / 100.0))))))));
              green = trunc((fr(fr(this.t.oc[k1][1]) + (fr(fr(this.t.oc[k1][1]) * ((fr(fr(this.m.snap[1]) / 100.0))))))));
              blue = trunc((fr(fr(this.t.oc[k1][2]) + (fr(fr(this.t.oc[k1][2]) * ((fr(fr(this.m.snap[2]) / 100.0))))))));
            }
            this.sred = trunc((fr(red) / 1.5));
            this.sgreen = trunc((fr(green) / 1.5));
            this.sblue = trunc((fr(blue) / 1.5));
            break;
          }
        }
      }
    }
    this.rot(ai, ai3, this.m.cx, this.m.cz, this.m.xz, 4);
    this.rot(ai2, ai3, this.m.cy, this.m.cz, this.m.zy, 4);
    let flag = true;
    let i2 = 0;
    let k2 = 0;
    let l2 = 0;
    let i3 = 0;
    let j4 = 0;
    do {
      ai[j4] = this.xs(ai[j4], ai3[j4]);
      ai2[j4] = this.ys(ai2[j4], ai3[j4]);
      if ((ai2[j4] < 0) || (ai3[j4] < 10)) {
        i2 = i32(i2 + 1);
      }
      if ((ai2[j4] > this.m.h) || (ai3[j4] < 10)) {
        k2 = i32(k2 + 1);
      }
      if ((ai[j4] < 0) || (ai3[j4] < 10)) {
        l2 = i32(l2 + 1);
      }
      if ((ai[j4] > this.m.w) || (ai3[j4] < 10)) {
        i3 = i32(i3 + 1);
      }
    } while (++j4 < 4);
    if ((((l2 === 4) || (i2 === 4)) || (k2 === 4)) || (i3 === 4)) {
      flag = false;
    }
    if (flag) {
      let k3 = 0;
      do {
        if (i > this.m.fade[k3]) {
          this.sred = idiv(((i32((Math.imul(this.sred, this.m.fogd)) + this.m.cfade[0]))), (i32(this.m.fogd + 1)));
          this.sgreen = idiv(((i32((Math.imul(this.sgreen, this.m.fogd)) + this.m.cfade[1]))), (i32(this.m.fogd + 1)));
          this.sblue = idiv(((i32((Math.imul(this.sblue, this.m.fogd)) + this.m.cfade[2]))), (i32(this.m.fogd + 1)));
        }
      } while (++k3 < 8);
      g.setColor(this.sred, this.sgreen, this.sblue);
      if (this.teleported) {
        g.setColorOf(new Color(this.sred, this.sgreen, this.sblue, this.telefade));
      }
      g.fillPolygon(ai, ai2, 4);
      if (this.m.effect[11] && this.outoftrack) {
        g.setColorOf(new Color(this.spec[0], this.spec[1], this.spec[2], 50));
        g.drawPolygon(ai, ai2, 4);
      }
    }
  }

  teleflash(rd) {
    let ai = intArray(8);
    let ai2 = intArray(8);
    let ai3 = intArray(4);
    let j1 = 0;
    do {
      ai[j1] = i32((i32(this.keyx[j1] + this.x)) - this.m.x);
      ai2[j1] = i32((i32(this.grat + this.y)) - this.m.y);
      ai3[j1] = i32((i32(this.keyz[j1] + this.z)) - this.m.z);
    } while (++j1 < 4);
    this.rot(ai, ai2, i32(this.x - this.m.x), i32(this.y - this.m.y), this.xy, 4);
    this.rot(ai2, ai3, i32(this.y - this.m.y), i32(this.z - this.m.y), this.zy, 4);
    this.rot(ai, ai3, i32(this.x - this.m.x), i32(this.z - this.m.z), this.xz, 4);
    this.rot(ai, ai3, this.m.cx, this.m.cz, this.m.xz, 4);
    this.rot(ai2, ai3, this.m.cy, this.m.cz, this.m.zy, 4);
    j1 = 0;
    let l1 = 0;
    let i2 = 0;
    let j2 = 0;
    do {
      let k2 = 0;
      do {
        if ((Math.abs(i32(ai[j2] - ai[k2])) | 0) > j1) {
          j1 = (Math.abs(i32(ai[j2] - ai[k2])) | 0);
        }
        if ((Math.abs(i32(ai2[j2] - ai2[k2])) | 0) > l1) {
          l1 = (Math.abs(i32(ai2[j2] - ai2[k2])) | 0);
        }
        if (this.py(ai[j2], ai[k2], ai2[j2], ai2[k2]) > i2) {
          i2 = this.py(ai[j2], ai[k2], ai2[j2], ai2[k2]);
        }
      } while (++k2 < 4);
    } while (++j2 < 4);
    i2 = trunc((Math.sqrt(i2) / 1.5));
    if (j1 < i2) {
      j1 = i2;
    }
    if (l1 < i2) {
      l1 = i2;
    }
    j2 = i32(this.m.cx + trunc((fr((fr(fr(((i32((i32(this.x - this.m.x)) - this.m.cx)))) * this.m.cos(this.m.xz))) - (fr(fr(((i32((i32(this.z - this.m.z)) - this.m.cz)))) * this.m.sin(this.m.xz)))))));
    let l2 = i32(this.m.cz + trunc((fr((fr(fr(((i32((i32(this.x - this.m.x)) - this.m.cx)))) * this.m.sin(this.m.xz))) + (fr(fr(((i32((i32(this.z - this.m.z)) - this.m.cz)))) * this.m.cos(this.m.xz)))))));
    let i3 = i32(this.m.cy + trunc((fr((fr(fr(((i32((i32(this.y - this.m.y)) - this.m.cy)))) * this.m.cos(this.m.zy))) - (fr(fr(((i32(l2 - this.m.cz)))) * this.m.sin(this.m.zy)))))));
    l2 = i32(this.m.cz + trunc((fr((fr(fr(((i32((i32(this.y - this.m.y)) - this.m.cy)))) * this.m.sin(this.m.zy))) + (fr(fr(((i32(l2 - this.m.cz)))) * this.m.cos(this.m.zy)))))));
    ai[0] = this.xs(trunc(((j2 - (j1 / 0.8)) - (this.m.random() * ((j1 / 2.4))))), l2);
    ai2[0] = this.ys(trunc(((i3 - (l1 / 1.92)) - (this.m.random() * ((l1 / 5.67))))), l2);
    ai[1] = this.xs(trunc(((j2 - (j1 / 0.8)) - (this.m.random() * ((j1 / 2.4))))), l2);
    ai2[1] = this.ys(trunc(((i3 + (l1 / 1.92)) + (this.m.random() * ((l1 / 5.67))))), l2);
    ai[2] = this.xs(trunc(((j2 - (j1 / 1.92)) - (this.m.random() * ((j1 / 5.67))))), l2);
    ai2[2] = this.ys(trunc(((i3 + (l1 / 0.8)) + (this.m.random() * ((l1 / 2.4))))), l2);
    ai[3] = this.xs(trunc(((j2 + (j1 / 1.92)) + (this.m.random() * ((j1 / 5.67))))), l2);
    ai2[3] = this.ys(trunc(((i3 + (l1 / 0.8)) + (this.m.random() * ((l1 / 2.4))))), l2);
    ai[4] = this.xs(trunc(((j2 + (j1 / 0.8)) + (this.m.random() * ((j1 / 2.4))))), l2);
    ai2[4] = this.ys(trunc(((i3 + (l1 / 1.92)) + (this.m.random() * ((l1 / 5.67))))), l2);
    ai[5] = this.xs(trunc(((j2 + (j1 / 0.8)) + (this.m.random() * ((j1 / 2.4))))), l2);
    ai2[5] = this.ys(trunc(((i3 - (l1 / 1.92)) - (this.m.random() * ((l1 / 5.67))))), l2);
    ai[6] = this.xs(trunc(((j2 + (j1 / 1.92)) + (this.m.random() * ((j1 / 5.67))))), l2);
    ai2[6] = this.ys(trunc(((i3 - (l1 / 0.8)) - (this.m.random() * ((l1 / 2.4))))), l2);
    ai[7] = this.xs(trunc(((j2 - (j1 / 1.92)) - (this.m.random() * ((j1 / 5.67))))), l2);
    ai2[7] = this.ys(trunc(((i3 - (l1 / 0.8)) - (this.m.random() * ((l1 / 2.4))))), l2);
    this.rot(ai, ai2, this.xs(j2, l2), this.ys(i3, l2), 22, 8);
    let j3 = trunc((fr(25.0 + (fr(25.0 * ((fr(fr(this.m.snap[0]) / 200.0))))))));
    if (j3 > 25) {
      j3 = 25;
    }
    if (j3 < 0) {
      j3 = 0;
    }
    let k3 = trunc((fr(0.0 + (fr(0.0 * ((fr(fr(this.m.snap[1]) / 200.0))))))));
    if (k3 > 0) {
      k3 = 0;
    }
    if (k3 < 0) {
      k3 = 0;
    }
    let l3 = trunc((fr(0.0 + (fr(0.0 * ((fr(fr(this.m.snap[2]) / 200.0))))))));
    if (l3 > 0) {
      l3 = 0;
    }
    if (l3 < 0) {
      l3 = 0;
    }
    rd.setColor(j3, k3, l3);
    rd.fillPolygon(ai, ai2, 8);
    ai[0] = this.xs(trunc((fr(fr((i32(j2 - j1))) - (fr(this.m.random() * fr(((idiv(j1, 4))))))))), l2);
    ai2[0] = this.ys(trunc(((i3 - (l1 / 2.4)) - (this.m.random() * ((l1 / 9.6))))), l2);
    ai[1] = this.xs(trunc((fr(fr((i32(j2 - j1))) - (fr(this.m.random() * fr(((idiv(j1, 4))))))))), l2);
    ai2[1] = this.ys(trunc(((i3 + (l1 / 2.4)) + (this.m.random() * ((l1 / 9.6))))), l2);
    ai[2] = this.xs(trunc(((j2 - (j1 / 2.4)) - (this.m.random() * ((j1 / 9.6))))), l2);
    ai2[2] = this.ys(trunc((fr(fr((i32(i3 + l1))) + (fr(this.m.random() * fr(((idiv(l1, 4))))))))), l2);
    ai[3] = this.xs(trunc(((j2 + (j1 / 2.4)) + (this.m.random() * ((j1 / 9.6))))), l2);
    ai2[3] = this.ys(trunc((fr(fr((i32(i3 + l1))) + (fr(this.m.random() * fr(((idiv(l1, 4))))))))), l2);
    ai[4] = this.xs(trunc((fr(fr((i32(j2 + j1))) + (fr(this.m.random() * fr(((idiv(j1, 4))))))))), l2);
    ai2[4] = this.ys(trunc(((i3 + (l1 / 2.4)) + (this.m.random() * ((l1 / 9.6))))), l2);
    ai[5] = this.xs(trunc((fr(fr((i32(j2 + j1))) + (fr(this.m.random() * fr(((idiv(j1, 4))))))))), l2);
    ai2[5] = this.ys(trunc(((i3 - (l1 / 2.4)) - (this.m.random() * ((l1 / 9.6))))), l2);
    ai[6] = this.xs(trunc(((j2 + (j1 / 2.4)) + (this.m.random() * ((j1 / 9.6))))), l2);
    ai2[6] = this.ys(trunc((fr(fr((i32(i3 - l1))) - (fr(this.m.random() * fr(((idiv(l1, 4))))))))), l2);
    ai[7] = this.xs(trunc(((j2 - (j1 / 2.4)) - (this.m.random() * ((j1 / 9.6))))), l2);
    ai2[7] = this.ys(trunc((fr(fr((i32(i3 - l1))) - (fr(this.m.random() * fr(((idiv(l1, 4))))))))), l2);
    j3 = trunc((fr(255.0 + (fr(255.0 * ((fr(fr(this.m.snap[0]) / 200.0))))))));
    if (j3 > 255) {
      j3 = 255;
    }
    if (j3 < 0) {
      j3 = 0;
    }
    k3 = trunc((fr(25.0 + (fr(25.0 * ((fr(fr(this.m.snap[1]) / 200.0))))))));
    if (k3 > 255) {
      k3 = 255;
    }
    if (k3 < 0) {
      k3 = 0;
    }
    l3 = trunc((fr(25.0 + (fr(25.0 * ((fr(fr(this.m.snap[2]) / 200.0))))))));
    if (l3 > 255) {
      l3 = 255;
    }
    if (l3 < 0) {
      l3 = 0;
    }
    rd.setColor(j3, k3, l3);
    rd.fillPolygon(ai, ai2, 8);
  }

  fixit(g) {
    if (this.fcnt === 1) {
      for (let i = 0; i < this.npl; i = i32(i + 1)) {
        this.p[i].hsb[0] = 0.5699999928474426;
        this.p[i].hsb[2] = 0.800000011920929;
        this.p[i].hsb[1] = 0.800000011920929;
        let color = Color.getHSBColor(this.p[i].hsb[0], this.p[i].hsb[1], this.p[i].hsb[2]);
        let l = trunc((fr(fr(color.getRed()) + (fr(fr(color.getRed()) * ((fr(fr(this.m.snap[0]) / 100.0))))))));
        if (l > 255) {
          l = 255;
        }
        if (l < 0) {
          l = 0;
        }
        let i2 = trunc((fr(fr(color.getGreen()) + (fr(fr(color.getGreen()) * ((fr(fr(this.m.snap[1]) / 100.0))))))));
        if (i2 > 255) {
          i2 = 255;
        }
        if (i2 < 0) {
          i2 = 0;
        }
        let k1 = trunc((fr(fr(color.getBlue()) + (fr(fr(color.getBlue()) * ((fr(fr(this.m.snap[2]) / 100.0))))))));
        if (k1 > 255) {
          k1 = 255;
        }
        if (k1 < 0) {
          k1 = 0;
        }
        Color.RGBtoHSB(l, i2, k1, this.p[i].hsb);
        this.p[i].flx = 1;
      }
    }
    if (this.fcnt === 2) {
      for (let j = 0; j < this.npl; j = i32(j + 1)) {
        this.p[j].flx = 1;
      }
    }
    if (this.fcnt === 4) {
      for (let m = 0; m < this.npl; m = i32(m + 1)) {
        this.p[m].flx = 3;
      }
    }
    if ((this.fcnt === 1) || (this.fcnt > 2)) {
      let ai = intArray(8);
      let ai2 = intArray(8);
      let ai3 = intArray(4);
      let j2 = 0;
      do {
        ai[j2] = i32((i32(this.keyx[j2] + this.x)) - this.m.x);
        ai2[j2] = i32((i32(this.grat + this.y)) - this.m.y);
        ai3[j2] = i32((i32(this.keyz[j2] + this.z)) - this.m.z);
      } while (++j2 < 4);
      this.rot(ai, ai2, i32(this.x - this.m.x), i32(this.y - this.m.y), this.xy, 4);
      this.rot(ai2, ai3, i32(this.y - this.m.y), i32(this.z - this.m.y), this.zy, 4);
      this.rot(ai, ai3, i32(this.x - this.m.x), i32(this.z - this.m.z), this.xz, 4);
      this.rot(ai, ai3, this.m.cx, this.m.cz, this.m.xz, 4);
      this.rot(ai2, ai3, this.m.cy, this.m.cz, this.m.zy, 4);
      j2 = 0;
      let l2 = 0;
      let i3 = 0;
      let j3 = 0;
      do {
        let k2 = 0;
        do {
          if ((Math.abs(i32(ai[j3] - ai[k2])) | 0) > j2) {
            j2 = (Math.abs(i32(ai[j3] - ai[k2])) | 0);
          }
          if ((Math.abs(i32(ai2[j3] - ai2[k2])) | 0) > l2) {
            l2 = (Math.abs(i32(ai2[j3] - ai2[k2])) | 0);
          }
          if (this.py(ai[j3], ai[k2], ai2[j3], ai2[k2]) > i3) {
            i3 = this.py(ai[j3], ai[k2], ai2[j3], ai2[k2]);
          }
        } while (++k2 < 4);
      } while (++j3 < 4);
      i3 = trunc((Math.sqrt(i3) / 1.5));
      if (j2 < i3) {
        j2 = i3;
      }
      if (l2 < i3) {
        l2 = i3;
      }
      j3 = i32(this.m.cx + trunc((fr((fr(fr(((i32((i32(this.x - this.m.x)) - this.m.cx)))) * this.m.cos(this.m.xz))) - (fr(fr(((i32((i32(this.z - this.m.z)) - this.m.cz)))) * this.m.sin(this.m.xz)))))));
      let l3 = i32(this.m.cz + trunc((fr((fr(fr(((i32((i32(this.x - this.m.x)) - this.m.cx)))) * this.m.sin(this.m.xz))) + (fr(fr(((i32((i32(this.z - this.m.z)) - this.m.cz)))) * this.m.cos(this.m.xz)))))));
      let i4 = i32(this.m.cy + trunc((fr((fr(fr(((i32((i32(this.y - this.m.y)) - this.m.cy)))) * this.m.cos(this.m.zy))) - (fr(fr(((i32(l3 - this.m.cz)))) * this.m.sin(this.m.zy)))))));
      l3 = i32(this.m.cz + trunc((fr((fr(fr(((i32((i32(this.y - this.m.y)) - this.m.cy)))) * this.m.sin(this.m.zy))) + (fr(fr(((i32(l3 - this.m.cz)))) * this.m.cos(this.m.zy)))))));
      ai[0] = this.xs(trunc(((j3 - (j2 / 0.8)) - (this.m.random() * ((j2 / 2.4))))), l3);
      ai2[0] = this.ys(trunc(((i4 - (l2 / 1.92)) - (this.m.random() * ((l2 / 5.67))))), l3);
      ai[1] = this.xs(trunc(((j3 - (j2 / 0.8)) - (this.m.random() * ((j2 / 2.4))))), l3);
      ai2[1] = this.ys(trunc(((i4 + (l2 / 1.92)) + (this.m.random() * ((l2 / 5.67))))), l3);
      ai[2] = this.xs(trunc(((j3 - (j2 / 1.92)) - (this.m.random() * ((j2 / 5.67))))), l3);
      ai2[2] = this.ys(trunc(((i4 + (l2 / 0.8)) + (this.m.random() * ((l2 / 2.4))))), l3);
      ai[3] = this.xs(trunc(((j3 + (j2 / 1.92)) + (this.m.random() * ((j2 / 5.67))))), l3);
      ai2[3] = this.ys(trunc(((i4 + (l2 / 0.8)) + (this.m.random() * ((l2 / 2.4))))), l3);
      ai[4] = this.xs(trunc(((j3 + (j2 / 0.8)) + (this.m.random() * ((j2 / 2.4))))), l3);
      ai2[4] = this.ys(trunc(((i4 + (l2 / 1.92)) + (this.m.random() * ((l2 / 5.67))))), l3);
      ai[5] = this.xs(trunc(((j3 + (j2 / 0.8)) + (this.m.random() * ((j2 / 2.4))))), l3);
      ai2[5] = this.ys(trunc(((i4 - (l2 / 1.92)) - (this.m.random() * ((l2 / 5.67))))), l3);
      ai[6] = this.xs(trunc(((j3 + (j2 / 1.92)) + (this.m.random() * ((j2 / 5.67))))), l3);
      ai2[6] = this.ys(trunc(((i4 - (l2 / 0.8)) - (this.m.random() * ((l2 / 2.4))))), l3);
      ai[7] = this.xs(trunc(((j3 - (j2 / 1.92)) - (this.m.random() * ((j2 / 5.67))))), l3);
      ai2[7] = this.ys(trunc(((i4 - (l2 / 0.8)) - (this.m.random() * ((l2 / 2.4))))), l3);
      if (this.fcnt === 3) {
        this.rot(ai, ai2, this.xs(j3, l3), this.ys(i4, l3), 22, 8);
      }
      if (this.fcnt === 4) {
        this.rot(ai, ai2, this.xs(j3, l3), this.ys(i4, l3), 22, 8);
      }
      if (this.fcnt === 5) {
        this.rot(ai, ai2, this.xs(j3, l3), this.ys(i4, l3), 0, 8);
      }
      if (this.fcnt === 6) {
        this.rot(ai, ai2, this.xs(j3, l3), this.ys(i4, l3), -22, 8);
      }
      if (this.fcnt === 7) {
        this.rot(ai, ai2, this.xs(j3, l3), this.ys(i4, l3), -22, 8);
      }
      let j4 = trunc((fr(191.0 + (fr(191.0 * ((fr(fr(this.m.snap[0]) / 350.0))))))));
      if (j4 > 255) {
        j4 = 255;
      }
      if (j4 < 0) {
        j4 = 0;
      }
      let k3 = trunc((fr(232.0 + (fr(232.0 * ((fr(fr(this.m.snap[1]) / 350.0))))))));
      if (k3 > 255) {
        k3 = 255;
      }
      if (k3 < 0) {
        k3 = 0;
      }
      let l4 = trunc((fr(255.0 + (fr(255.0 * ((fr(fr(this.m.snap[2]) / 350.0))))))));
      if (l4 > 255) {
        l4 = 255;
      }
      if (l4 < 0) {
        l4 = 0;
      }
      g.setColor(j4, k3, l4);
      g.fillPolygon(ai, ai2, 8);
      ai[0] = this.xs(trunc((fr(fr((i32(j3 - j2))) - (fr(this.m.random() * fr(((idiv(j2, 4))))))))), l3);
      ai2[0] = this.ys(trunc(((i4 - (l2 / 2.4)) - (this.m.random() * ((l2 / 9.6))))), l3);
      ai[1] = this.xs(trunc((fr(fr((i32(j3 - j2))) - (fr(this.m.random() * fr(((idiv(j2, 4))))))))), l3);
      ai2[1] = this.ys(trunc(((i4 + (l2 / 2.4)) + (this.m.random() * ((l2 / 9.6))))), l3);
      ai[2] = this.xs(trunc(((j3 - (j2 / 2.4)) - (this.m.random() * ((j2 / 9.6))))), l3);
      ai2[2] = this.ys(trunc((fr(fr((i32(i4 + l2))) + (fr(this.m.random() * fr(((idiv(l2, 4))))))))), l3);
      ai[3] = this.xs(trunc(((j3 + (j2 / 2.4)) + (this.m.random() * ((j2 / 9.6))))), l3);
      ai2[3] = this.ys(trunc((fr(fr((i32(i4 + l2))) + (fr(this.m.random() * fr(((idiv(l2, 4))))))))), l3);
      ai[4] = this.xs(trunc((fr(fr((i32(j3 + j2))) + (fr(this.m.random() * fr(((idiv(j2, 4))))))))), l3);
      ai2[4] = this.ys(trunc(((i4 + (l2 / 2.4)) + (this.m.random() * ((l2 / 9.6))))), l3);
      ai[5] = this.xs(trunc((fr(fr((i32(j3 + j2))) + (fr(this.m.random() * fr(((idiv(j2, 4))))))))), l3);
      ai2[5] = this.ys(trunc(((i4 - (l2 / 2.4)) - (this.m.random() * ((l2 / 9.6))))), l3);
      ai[6] = this.xs(trunc(((j3 + (j2 / 2.4)) + (this.m.random() * ((j2 / 9.6))))), l3);
      ai2[6] = this.ys(trunc((fr(fr((i32(i4 - l2))) - (fr(this.m.random() * fr(((idiv(l2, 4))))))))), l3);
      ai[7] = this.xs(trunc(((j3 - (j2 / 2.4)) - (this.m.random() * ((j2 / 9.6))))), l3);
      ai2[7] = this.ys(trunc((fr(fr((i32(i4 - l2))) - (fr(this.m.random() * fr(((idiv(l2, 4))))))))), l3);
      j4 = trunc((fr(213.0 + (fr(213.0 * ((fr(fr(this.m.snap[0]) / 350.0))))))));
      if (j4 > 255) {
        j4 = 255;
      }
      if (j4 < 0) {
        j4 = 0;
      }
      k3 = trunc((fr(239.0 + (fr(239.0 * ((fr(fr(this.m.snap[1]) / 350.0))))))));
      if (k3 > 255) {
        k3 = 255;
      }
      if (k3 < 0) {
        k3 = 0;
      }
      l4 = trunc((fr(255.0 + (fr(255.0 * ((fr(fr(this.m.snap[2]) / 350.0))))))));
      if (l4 > 255) {
        l4 = 255;
      }
      if (l4 < 0) {
        l4 = 0;
      }
      g.setColor(j4, k3, l4);
      g.fillPolygon(ai, ai2, 8);
    }
    if (this.m.interpolating) {   // base port: the repair sparkle steps at tick rate
      return;
    }
    if (this.fcnt > 7) {
      this.fcnt = 0;
      this.fix = false;
    } else {
      this.fcnt = i32(this.fcnt + 1);
    }
  }

  setfire() {
    for (let i = 0; i < this.npl; i = i32(i + 1)) {
      if (((this.p[i].wz === 0) || (this.p[i].gr === -17)) || (this.p[i].gr === -16)) {
        this.p[i].embos = 16;
      }
    }
  }

  unsetfire() {
    for (let i = 0; i < this.npl; i = i32(i + 1)) {
      if (((this.p[i].wz === 0) || (this.p[i].gr === -17)) || (this.p[i].gr === -16)) {
        this.p[i].embos = 0;
      }
    }
  }

  drawsun(g) {
    let sunx = objArray(2).map(() => intArray(8));
    let suny = objArray(2).map(() => intArray(8));
    let sunz = objArray(2).map(() => intArray(8));
    sunx[0][0] = i32(-500 - this.m.x);
    sunx[0][1] = i32(500 - this.m.x);
    sunx[0][2] = i32(800 - this.m.x);
    sunx[0][3] = i32(800 - this.m.x);
    sunx[0][4] = i32(500 - this.m.x);
    sunx[0][5] = i32(-500 - this.m.x);
    sunx[0][6] = i32(-800 - this.m.x);
    sunx[0][7] = i32(-800 - this.m.x);
    suny[0][0] = i32(-1000 - this.m.y);
    suny[0][1] = i32(-1000 - this.m.y);
    suny[0][2] = i32(-1300 - this.m.y);
    suny[0][3] = i32(-2300 - this.m.y);
    suny[0][4] = i32(-2600 - this.m.y);
    suny[0][5] = i32(-2600 - this.m.y);
    suny[0][6] = i32(-2300 - this.m.y);
    suny[0][7] = i32(-1300 - this.m.y);
    for (let a = 0; a < 8; a = i32(a + 1)) {
      sunz[0][a] = i32(20000 - this.m.z);
    }
    for (let c = 0; c < 2; c = i32(c + 1)) {
      if (this.roted) {
        this.rot(sunx[c], sunz[c], i32(this.x - this.m.x), i32(this.z - this.m.z), 90, 8);
      }
      this.rot(sunx[c], sunz[c], this.m.cx, this.m.cz, this.m.xz, 8);
      this.rot(suny[c], sunz[c], this.m.cy, this.m.cz, this.m.zy, 8);
      let ai3 = intArray(8);
      let ai4 = intArray(8);
      for (let k2 = 0; k2 < 8; k2 = i32(k2 + 1)) {
        ai3[k2] = this.xs(sunx[c][k2], sunz[c][k2]);
        ai4[k2] = this.ys(suny[c][k2], sunz[c][k2]);
      }
      g.setColor(230, 230, 0);
      g.fillPolygon(ai3, ai4, 8);
    }
  }

  drawsnow(g, i, height) {
    if (this.m.trk) {
      i = 20;
    }
    let ndrops = i;
    let snowxloc = intArray(ndrops);
    let snowzloc = intArray(ndrops);
    let snowx = objArray(ndrops).map(() => objArray(2).map(() => intArray(8)));
    let snowy = objArray(ndrops).map(() => objArray(2).map(() => intArray(8)));
    let snowz = objArray(ndrops).map(() => objArray(2).map(() => intArray(8)));
    let godown = intArray(ndrops);
    let ypos = intArray(ndrops);
    for (let a = 0; a < ndrops; a = i32(a + 1)) {
      if (a < (idiv(ndrops, 3))) {
        snowxloc[a] = i32(trunc((random() * ((idiv(((i32(this.m.wallside[0] - this.m.wallside[1]))), 3))))) + this.m.wallside[1]);
      }
      if ((a >= (idiv(ndrops, 3))) && (a < (idiv((Math.imul(ndrops, 2)), 3)))) {
        snowxloc[a] = i32((i32(trunc((random() * ((idiv(((i32(this.m.wallside[0] - this.m.wallside[1]))), 3))))) + this.m.wallside[1])) + (idiv(((i32(this.m.wallside[0] - this.m.wallside[1]))), 3)));
      }
      if ((a >= (idiv((Math.imul(ndrops, 2)), 3))) && (a < ndrops)) {
        snowxloc[a] = i32((i32(trunc((random() * ((idiv(((i32(this.m.wallside[0] - this.m.wallside[1]))), 3))))) + this.m.wallside[1])) + (idiv((Math.imul(((i32(this.m.wallside[0] - this.m.wallside[1]))), 2)), 3)));
      }
      snowzloc[a] = i32(trunc((random() * ((i32(this.m.wallside[2] - this.m.wallside[3]))))) + this.m.wallside[3]);
      godown[a] = trunc((random() * ((i32(height + 250)))));
      ypos[a] = i32(i32(-height) + godown[a]);
      for (let e = 0; e < 2; e = i32(e + 1)) {
        if (((e % 2)) === 0) {
          snowx[a][e][0] = i32((i32(snowxloc[a] - this.m.x)) - 25);
          snowx[a][e][1] = i32((i32(snowxloc[a] - this.m.x)) + 25);
          snowx[a][e][2] = i32((i32(snowxloc[a] - this.m.x)) + 40);
          snowx[a][e][3] = i32((i32(snowxloc[a] - this.m.x)) + 40);
          snowx[a][e][4] = i32((i32(snowxloc[a] - this.m.x)) + 25);
          snowx[a][e][5] = i32((i32(snowxloc[a] - this.m.x)) - 25);
          snowx[a][e][6] = i32((i32(snowxloc[a] - this.m.x)) - 40);
          snowx[a][e][7] = i32((i32(snowxloc[a] - this.m.x)) - 40);
          for (let b = 0; b < 8; b = i32(b + 1)) {
            snowz[a][e][b] = i32(snowzloc[a] - this.m.z);
          }
        } else {
          snowz[a][e][0] = i32((i32(snowzloc[a] - this.m.z)) - 25);
          snowz[a][e][1] = i32((i32(snowzloc[a] - this.m.z)) + 25);
          snowz[a][e][2] = i32((i32(snowzloc[a] - this.m.z)) + 40);
          snowz[a][e][3] = i32((i32(snowzloc[a] - this.m.z)) + 40);
          snowz[a][e][4] = i32((i32(snowzloc[a] - this.m.z)) + 25);
          snowz[a][e][5] = i32((i32(snowzloc[a] - this.m.z)) - 25);
          snowz[a][e][6] = i32((i32(snowzloc[a] - this.m.z)) - 40);
          snowz[a][e][7] = i32((i32(snowzloc[a] - this.m.z)) - 40);
          for (let b = 0; b < 8; b = i32(b + 1)) {
            snowx[a][e][b] = i32(snowxloc[a] - this.m.x);
          }
        }
        snowy[a][e][0] = i32((i32(ypos[a] - this.m.y)) - 25);
        snowy[a][e][1] = i32((i32(ypos[a] - this.m.y)) - 25);
        snowy[a][e][2] = i32((i32(ypos[a] - this.m.y)) - 10);
        snowy[a][e][3] = i32((i32(ypos[a] - this.m.y)) + 40);
        snowy[a][e][4] = i32((i32(ypos[a] - this.m.y)) + 55);
        snowy[a][e][5] = i32((i32(ypos[a] - this.m.y)) + 55);
        snowy[a][e][6] = i32((i32(ypos[a] - this.m.y)) + 40);
        snowy[a][e][7] = i32((i32(ypos[a] - this.m.y)) - 10);
        if (this.roted) {
          this.rot(snowx[a][e], snowz[a][e], i32(this.x - this.m.x), i32(this.z - this.m.z), 90, 8);
        }
        this.rot(snowx[a][e], snowz[a][e], this.m.cx, this.m.cz, this.m.xz, 8);
        this.rot(snowy[a][e], snowz[a][e], this.m.cy, this.m.cz, this.m.zy, 8);
        let ai3 = intArray(8);
        let ai4 = intArray(8);
        let j8 = 0;
        let l8 = 0;
        let j9 = 0;
        let l9 = 0;
        for (let k2 = 0; k2 < 8; k2 = i32(k2 + 1)) {
          ai3[k2] = this.xs(snowx[a][e][k2], snowz[a][e][k2]);
          ai4[k2] = this.ys(snowy[a][e][k2], snowz[a][e][k2]);
          if ((ai4[k2] < 0) || (snowz[a][e][k2] < 10)) {
            j8 = i32(j8 + 1);
          }
          if ((ai4[k2] > this.m.h) || (snowz[a][e][k2] < 10)) {
            l8 = i32(l8 + 1);
          }
          if ((ai3[k2] < 0) || (snowz[a][e][k2] < 10)) {
            j9 = i32(j9 + 1);
          }
          if ((ai3[k2] > this.m.w) || (snowz[a][e][k2] < 10)) {
            l9 = i32(l9 + 1);
          }
        }
        let dontshow = false;
        if ((((j8 !== 0) || (l8 !== 0)) || (j9 !== 0)) || (l9 !== 0)) {
          dontshow = true;
        }
        let color = intArray(3);
        for (let d = 0; d < 3; d = i32(d + 1)) {
          if ((i32(this.m.csky[d] + 20)) <= 255) {
            color[d] = i32(this.m.csky[d] + 20);
          } else {
            color[d] = 255;
          }
        }
        g.setColor(color[0], color[1], color[2]);
        if (!dontshow) {
          g.fillPolygon(ai3, ai4, 8);
        }
      }
    }
  }

  electrify(g) {
    let i = 0;
    do {
      // base port: a new bolt is a tick's job; its shape holds across interpolated
      // frames because Medium.random() replays the tick's sequence
      if (this.elc[i] === 0 && !this.m.interpolating) {
        this.edl[i] = trunc((fr(380.0 - (fr(this.m.random() * 760.0)))));
        this.edr[i] = trunc((fr(380.0 - (fr(this.m.random() * 760.0)))));
        this.elc[i] = 1;
      }
      let j = trunc((fr(fr(this.edl[i]) + ((fr(190.0 - (fr(this.m.random() * 380.0))))))));
      let k = trunc((fr(fr(this.edr[i]) + ((fr(190.0 - (fr(this.m.random() * 380.0))))))));
      let l = trunc((fr(this.m.random() * 126.0)));
      let i2 = trunc((fr(this.m.random() * 126.0)));
      let ai = intArray(8);
      let ai2 = intArray(8);
      let ai3 = intArray(8);
      let j2 = 0;
      do {
        ai3[j2] = i32(this.z - this.m.z);
      } while (++j2 < 8);
      ai[0] = i32((i32(this.x - this.m.x)) - 504);
      ai2[0] = i32((i32((i32((i32(this.y - this.m.y)) - this.edl[i])) - 5)) - trunc((fr(this.m.random() * 5.0))));
      ai[1] = i32((i32((i32(this.x - this.m.x)) - 252)) + i2);
      ai2[1] = i32((i32((i32((i32(this.y - this.m.y)) - j)) - 5)) - trunc((fr(this.m.random() * 5.0))));
      ai[2] = i32((i32((i32(this.x - this.m.x)) + 252)) - l);
      ai2[2] = i32((i32((i32((i32(this.y - this.m.y)) - k)) - 5)) - trunc((fr(this.m.random() * 5.0))));
      ai[3] = i32((i32(this.x - this.m.x)) + 504);
      ai2[3] = i32((i32((i32((i32(this.y - this.m.y)) - this.edr[i])) - 5)) - trunc((fr(this.m.random() * 5.0))));
      ai[4] = i32((i32(this.x - this.m.x)) + 504);
      ai2[4] = i32((i32((i32((i32(this.y - this.m.y)) - this.edr[i])) + 5)) + trunc((fr(this.m.random() * 5.0))));
      ai[5] = i32((i32((i32(this.x - this.m.x)) + 252)) - l);
      ai2[5] = i32((i32((i32((i32(this.y - this.m.y)) - k)) + 5)) + trunc((fr(this.m.random() * 5.0))));
      ai[6] = i32((i32((i32(this.x - this.m.x)) - 252)) + i2);
      ai2[6] = i32((i32((i32((i32(this.y - this.m.y)) - j)) + 5)) + trunc((fr(this.m.random() * 5.0))));
      ai[7] = i32((i32(this.x - this.m.x)) - 504);
      ai2[7] = i32((i32((i32((i32(this.y - this.m.y)) - this.edl[i])) + 5)) + trunc((fr(this.m.random() * 5.0))));
      if (this.roted) {
        this.rot(ai, ai3, i32(this.x - this.m.x), i32(this.z - this.m.z), 90, 8);
      }
      this.rot(ai, ai3, this.m.cx, this.m.cz, this.m.xz, 8);
      this.rot(ai2, ai3, this.m.cy, this.m.cz, this.m.zy, 8);
      let flag = true;
      let k2 = 0;
      let l2 = 0;
      let i3 = 0;
      let j3 = 0;
      let ai4 = intArray(8);
      let ai5 = intArray(8);
      let k3 = 0;
      do {
        ai4[k3] = this.xs(ai[k3], ai3[k3]);
        ai5[k3] = this.ys(ai2[k3], ai3[k3]);
        if ((ai5[k3] < 0) || (ai3[k3] < 10)) {
          k2 = i32(k2 + 1);
        }
        if ((ai5[k3] > this.m.h) || (ai3[k3] < 10)) {
          l2 = i32(l2 + 1);
        }
        if ((ai4[k3] < 0) || (ai3[k3] < 10)) {
          i3 = i32(i3 + 1);
        }
        if ((ai4[k3] > this.m.w) || (ai3[k3] < 10)) {
          j3 = i32(j3 + 1);
        }
      } while (++k3 < 8);
      if ((((i3 === 8) || (k2 === 8)) || (l2 === 8)) || (j3 === 8)) {
        flag = false;
      }
      if (flag) {
        let l3 = trunc((fr(160.0 + (fr(160.0 * ((fr(fr(this.m.snap[0]) / 500.0))))))));
        if (l3 > 255) {
          l3 = 255;
        }
        if (l3 < 0) {
          l3 = 0;
        }
        let j4 = trunc((fr(238.0 + (fr(238.0 * ((fr(fr(this.m.snap[1]) / 500.0))))))));
        if (j4 > 255) {
          j4 = 255;
        }
        if (j4 < 0) {
          j4 = 0;
        }
        let l4 = trunc((fr(255.0 + (fr(255.0 * ((fr(fr(this.m.snap[2]) / 500.0))))))));
        if (l4 > 255) {
          l4 = 255;
        }
        if (l4 < 0) {
          l4 = 0;
        }
        l3 = idiv(((i32((Math.imul(l3, 2)) + (Math.imul(214, (i32(this.elc[i] - 1))))))), (i32(this.elc[i] + 1)));
        j4 = idiv(((i32((Math.imul(j4, 2)) + (Math.imul(236, (i32(this.elc[i] - 1))))))), (i32(this.elc[i] + 1)));
        if (this.m.trk) {
          l3 = 191;
          j4 = 232;
          l4 = 255;
        }
        g.setColor(l3, j4, l4);
        g.fillPolygon(ai4, ai5, 8);
        if (ai3[0] < 4000) {
          let i4 = trunc((fr(150.0 + (fr(150.0 * ((fr(fr(this.m.snap[0]) / 500.0))))))));
          if (i4 > 255) {
            i4 = 255;
          }
          if (i4 < 0) {
            i4 = 0;
          }
          let k4 = trunc((fr(227.0 + (fr(227.0 * ((fr(fr(this.m.snap[1]) / 500.0))))))));
          if (k4 > 255) {
            k4 = 255;
          }
          if (k4 < 0) {
            k4 = 0;
          }
          let i5 = trunc((fr(255.0 + (fr(255.0 * ((fr(fr(this.m.snap[2]) / 500.0))))))));
          if (i5 > 255) {
            i5 = 255;
          }
          if (i5 < 0) {
            i5 = 0;
          }
          g.setColor(i4, k4, i5);
          g.drawPolygon(ai4, ai5, 8);
        }
      }
      if (!this.m.interpolating) {   // base port: tick rate
        if (this.elc[i] > (fr(this.m.random() * 60.0))) {
          this.elc[i] = 0;
        } else {
          let elc = this.elc;
          let n = i;
          ++elc[n];
        }
      }
    } while (++i < 4);
    if (this.m.interpolating) {   // base port: the ring spins at tick rate
      return;
    }
    if (!this.roted) {
      this.xy = i32(this.xy + 11);
    } else {
      this.zy = i32(this.zy + 11);
    }
  }

  py(i, j, k, l) {
    return i32((Math.imul(((i32(i - j))), (i32(i - j)))) + (Math.imul(((i32(k - l))), (i32(k - l)))));
  }
}
