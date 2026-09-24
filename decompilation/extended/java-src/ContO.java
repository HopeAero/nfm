import java.awt.Graphics;
import java.util.Random;
import java.util.Arrays;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import java.awt.Color;
import java.awt.Graphics2D;

// 
// Decompiled by Procyon v0.6.0
// 

public class ContO
{
    Medium m;
    Trackers t;
    Plane[] p;
    int npl;
    int x;
    int y;
    int z;
    int xz;
    int xy;
    int zy;
    double wxz;
    int wzy;
    int dist;
    int fixdist;
    int maxR;
    int disp;
    int disline;
    boolean shadow;
    boolean noline;
    float grounded;
    int[][] srgb;
    int grat;
    float[] osmag;
    int[] keyx;
    float[] smag;
    int[] keyz;
    int[] txy;
    int[] tzy;
    int[] sav;
    float[] sbln;
    int[][] tc;
    int[] tradx;
    int[] tradz;
    int[] trady;
    int[] tx;
    int[] ty;
    int[] sx;
    int[] tz;
    int[] skd;
    int[] dam;
    boolean[] notwall;
    int tnt;
    int[] sy;
    int[] sz;
    int[] stg;
    int[] dov;
    float[][] smag2;
    int[] scx;
    int[] scz;
    boolean[] fulls;
    boolean elec;
    boolean roted;
    int[] edl;
    int[] edr;
    int[] elc;
    boolean fix;
    int fcnt;
    int checkpoint;
    float div;
    float iwid;
    float sfactor;
    boolean spatk;
    boolean freeze;
    boolean weaken;
    boolean leech;
    boolean playerglow;
    boolean strswap;
    int sred;
    int sgreen;
    int sblue;
    boolean shadowcar;
    int descend;
    boolean greenflame;
    int flameheight;
    int invisiblepiece;
    boolean glowlines;
    boolean glowcustom;
    int[] glowcolour;
    int[] spec;
    int weakstage;
    int groundlevel;
    float fakegrounded;
    boolean wallpiece;
    int telechk;
    int telefade;
    boolean teleported;
    boolean floorguardian;
    boolean guardswitch;
    boolean isacar;
    int[] xextreme;
    int[] zextreme;
    boolean lightup;
    boolean outoftrack;
    int[] dmgcolours;
    
    public void pdust(final int i, final Graphics2D g, final int j) {
        if (j * this.dov[i] > 0) {
            int k;
            if (this.fulls[i]) {
                k = this.stg[i] * this.stg[i];
            }
            else {
                k = this.stg[i] * this.stg[i] * this.stg[i] + 1;
            }
            final int[] coladj = { this.m.cgrnd[0], this.m.cgrnd[1], this.m.cgrnd[2] };
            int l = (coladj[0] * k + this.m.cfade[0] * 2 + this.m.csky[0]) / (3 + k);
            int i2 = (coladj[1] * k + this.m.cfade[0] * 2 + this.m.csky[1]) / (3 + k);
            int j2 = (coladj[2] * k + this.m.cfade[0] * 2 + this.m.csky[2]) / (3 + k);
            boolean nodust = true;
            for (int k2 = 0; k2 < this.t.nt; ++k2) {
                if (!this.m.effect[9] || this.t.y[k2] == this.groundlevel) {
                    if (this.groundlevel >= 0) {
                        nodust = false;
                    }
                    if (Math.abs(this.t.zy[k2]) != 90 && Math.abs(this.t.xy[k2]) != 90 && Math.abs(this.sx[i] - this.t.x[k2]) < this.t.radx[k2] && Math.abs(this.sz[i] - this.t.z[k2]) < this.t.radz[k2]) {
                        nodust = false;
                        if (this.t.skd[k2] == 0) {
                            k = this.stg[i] * this.stg[i] * this.stg[i] + 2;
                        }
                        int red = this.t.c[k2][0];
                        int green = this.t.c[k2][1];
                        int blue = this.t.c[k2][2];
                        if ((this.m.switchfase == 0 || this.m.switchfase == 10 || this.m.switchfase == 20) && this.m.effect[2] && !this.m.trk) {
                            red = (int)(this.t.oc[k2][0] + this.t.oc[k2][0] * (this.m.snap[0] / 100.0f));
                            green = (int)(this.t.oc[k2][1] + this.t.oc[k2][1] * (this.m.snap[1] / 100.0f));
                            blue = (int)(this.t.oc[k2][2] + this.t.oc[k2][2] * (this.m.snap[2] / 100.0f));
                        }
                        l = (int)((red * 0.87 * k + this.m.cfade[0] * 2 + this.m.csky[0]) / (3 + k));
                        i2 = (int)((green * 0.87 * k + this.m.cfade[0] * 2 + this.m.csky[1]) / (3 + k));
                        j2 = (int)((blue * 0.87 * k + this.m.cfade[0] * 2 + this.m.csky[2]) / (3 + k));
                    }
                }
            }
            if (this.sy[i] > 250) {
                this.sy[i] = 250;
            }
            final int _tmp = this.sy[i];
            final int l2 = this.m.cx + (int)((this.sx[i] - this.m.x - this.m.cx) * this.m.cos(this.m.xz) - (this.sz[i] - this.m.z - this.m.cz) * this.m.sin(this.m.xz));
            int i3 = this.m.cz + (int)((this.sx[i] - this.m.x - this.m.cx) * this.m.sin(this.m.xz) + (this.sz[i] - this.m.z - this.m.cz) * this.m.cos(this.m.xz));
            final int j3 = this.m.cy + (int)((this.sy[i] - this.m.y - this.m.cy) * this.m.cos(this.m.zy) - (i3 - this.m.cz) * this.m.sin(this.m.zy));
            i3 = this.m.cz + (int)((this.sy[i] - this.m.y - this.m.cy) * this.m.sin(this.m.zy) + (i3 - this.m.cz) * this.m.cos(this.m.zy));
            final int k3 = (int)Math.sqrt((this.m.cy - j3) * (this.m.cy - j3) + (this.m.cx - l2) * (this.m.cx - l2) + i3 * i3);
            int l3 = 0;
            do {
                if (k3 > this.m.fade[l3]) {
                    l = (l * this.m.fogd + this.m.cfade[0]) / (this.m.fogd + 1);
                    i2 = (i2 * this.m.fogd + this.m.cfade[1]) / (this.m.fogd + 1);
                    j2 = (j2 * this.m.fogd + this.m.cfade[2]) / (this.m.fogd + 1);
                }
            } while (++l3 < 8);
            if (Math.abs(this.scx[i]) + Math.abs(this.scz[i]) > 150) {
                final int[] sy = this.sy;
                sy[i] -= (3.0f + 27.0f * this.smag[i]);  // cast: bytecode-verified
            }
            else {
                final int[] sy2 = this.sy;
                sy2[i] -= (23.0f + 7.0f * this.smag[i]);  // cast: bytecode-verified
            }
            final int[] sx = this.sx;
            sx[i] += (this.scx[i] / ((this.stg[i] + 1) * this.smag[i]));  // cast: bytecode-verified
            final int[] sz = this.sz;
            sz[i] += (this.scz[i] / ((this.stg[i] + 1) * this.smag[i]));  // cast: bytecode-verified
            final int[] ai = new int[8];
            final int[] ai2 = new int[8];
            final int i4 = this.stg[i] - 3;
            ai[0] = this.xs((int)(l2 - (18.0f + this.m.random() * 18.0f + i4 * 6) * this.smag[i]), i3);
            ai2[0] = this.ys((int)(j3 - (7.5 + this.m.random() * 7.5 + i4 * 2.5) * this.smag[i]), i3);
            if (ai2[0] < 45 && this.m.flex != 0) {
                this.m.flex = 0;
            }
            ai[1] = this.xs((int)(l2 - (18.0f + this.m.random() * 18.0f + i4 * 6) * this.smag[i]), i3);
            ai2[1] = this.ys((int)(j3 + (7.5 + this.m.random() * 7.5 + i4 * 2.5) * this.smag[i]), i3);
            ai[2] = this.xs((int)(l2 - (7.5 + this.m.random() * 7.5 + i4 * 2.5) * this.smag[i]), i3);
            ai2[2] = this.ys((int)(j3 + (18.0f + this.m.random() * 18.0f + i4 * 6) * this.smag[i]), i3);
            ai[3] = this.xs((int)(l2 + (7.5 + this.m.random() * 7.5 + i4 * 2.5) * this.smag[i]), i3);
            ai2[3] = this.ys((int)(j3 + (18.0f + this.m.random() * 18.0f + i4 * 6) * this.smag[i]), i3);
            ai[4] = this.xs((int)(l2 + (18.0f + this.m.random() * 18.0f + i4 * 6) * this.smag[i]), i3);
            ai2[4] = this.ys((int)(j3 + (7.5 + this.m.random() * 7.5 + i4 * 2.5) * this.smag[i]), i3);
            ai[5] = this.xs((int)(l2 + (18.0f + this.m.random() * 18.0f + i4 * 6) * this.smag[i]), i3);
            ai2[5] = this.ys((int)(j3 - (7.5 + this.m.random() * 7.5 + i4 * 2.5) * this.smag[i]), i3);
            ai[6] = this.xs((int)(l2 + (7.5 + this.m.random() * 7.5 + i4 * 2.5) * this.smag[i]), i3);
            ai2[6] = this.ys((int)(j3 - (18.0f + this.m.random() * 18.0f + i4 * 6) * this.smag[i]), i3);
            ai[7] = this.xs((int)(l2 - (7.5 + this.m.random() * 7.5 + i4 * 2.5) * this.smag[i]), i3);
            ai2[7] = this.ys((int)(j3 - (18.0f + this.m.random() * 18.0f + i4 * 6) * this.smag[i]), i3);
            boolean flag = true;
            if (nodust) {
                flag = false;
            }
            int j4 = 0;
            int k4 = 0;
            int l4 = 0;
            int i5 = 0;
            int j5 = 0;
            do {
                if (ai2[j5] < 0 || i3 < 10) {
                    ++j4;
                }
                if (ai2[j5] > this.m.h || i3 < 10) {
                    ++k4;
                }
                if (ai[j5] < 0 || i3 < 10) {
                    ++l4;
                }
                if (ai[j5] > this.m.w || i3 < 10) {
                    ++i5;
                }
                if (ai2[j5] < 45 && this.m.flex != 0) {
                    this.m.flex = 0;
                }
            } while (++j5 < 8);
            if (l4 == 4 || j4 == 4 || k4 == 4 || i5 == 4) {
                flag = false;
            }
            int fadedshad = 255;
            if (this.m.effect[4]) {
                fadedshad = 60;
            }
            if (flag) {
                g.setColor(new Color(l, i2, j2, fadedshad));
                g.fillPolygon(ai, ai2, 8);
            }
            if (this.dov[i] == 1) {
                this.dov[i] = -1;
            }
            if (this.stg[i] == 4) {
                this.stg[i] = 0;
            }
            else {
                final int[] stg = this.stg;
                ++stg[i];
                if (this.stg[i] == 2 && this.fulls[i]) {
                    this.dov[i] = 0;
                }
            }
        }
        else if (this.dov[i] == 0) {
            this.dov[i] = 1;
        }
    }
    
    public int ys(final int i, int j) {
        if (j < 50) {
            j = 50;
        }
        return (j - this.m.focus_point) * (this.m.cy - i) / j + i;
    }
    
    public ContO(final byte[] abyte0, final Medium medium, final Trackers trackers, final xtGraphics xtgraphics, final int code) {
        this.div = 1.0f;
        this.iwid = 1.0f;
        this.spec = new int[3];
        this.xextreme = new int[2];
        this.zextreme = new int[2];
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
        this.fakegrounded = 1.0f;
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
        this.xextreme = new int[2];
        this.zextreme = new int[2];
        this.dmgcolours = new int[3];
        this.glowcustom = false;
        this.glowcolour = new int[3];
        this.wzy = 0;
        this.descend = -10000;
        this.dist = 0;
        this.fixdist = 0;
        this.maxR = 0;
        this.disp = 0;
        this.disline = 7;
        this.shadow = false;
        this.noline = false;
        this.grounded = 1.0f;
        this.grat = 0;
        this.keyx = new int[6];
        this.keyz = new int[6];
        this.tnt = 0;
        this.sx = new int[6];
        this.sy = new int[6];
        this.sz = new int[6];
        this.stg = new int[6];
        this.dov = new int[6];
        this.smag = new float[6];
        this.scx = new int[6];
        this.scz = new int[6];
        this.fulls = new boolean[6];
        this.elec = false;
        this.roted = false;
        this.edl = new int[6];
        this.edr = new int[6];
        this.elc = new int[6];
        this.fix = false;
        this.fcnt = 0;
        this.checkpoint = 0;
        this.m = medium;
        this.t = trackers;
        this.p = new Plane[500];
        String s1 = "";
        boolean flag = false;
        boolean flag2 = false;
        int i = 0;
        final int[] ai = new int[100];
        final int[] ai2 = new int[100];
        final int[] ai3 = new int[100];
        final int[] ai4 = new int[3];
        int i2 = 0;
        boolean flag3 = false;
        final Wheels wheels = new Wheels();
        int j = 0;
        int k = 1;
        int l = 0;
        int i3 = 0;
        byte byte0 = 0;
        if (code < 78 || code >= 120) {
            this.sfactor = 10.0f;
        }
        else {
            this.sfactor = 6.0f;
        }
        this.isacar = false;
        if (code < 39 || (code >= 78 && code < 117) || code == 64) {
            this.isacar = true;
        }
        boolean bool2 = false;
        try {
            final DataInputStream datainputstream = new DataInputStream(new ByteArrayInputStream(abyte0));
            String s2;
            while ((s2 = datainputstream.readLine()) != null) {
                s1 = new StringBuilder().append(s2.trim()).toString();
                if (s1.startsWith("<p>")) {
                    flag = true;
                    i = 0;
                    k = 0;
                    l = 0;
                    byte0 = 0;
                    bool2 = false;
                }
                if (flag) {
                    if (s1.startsWith("gr")) {
                        k = this.getvalue("gr", s1, 0);
                    }
                    if (s1.startsWith("fs")) {
                        l = this.getvalue("fs", s1, 0);
                    }
                    if (s1.startsWith("c")) {
                        i2 = 0;
                        ai4[0] = this.getvalue("c", s1, 0);
                        ai4[1] = this.getvalue("c", s1, 1);
                        ai4[2] = this.getvalue("c", s1, 2);
                    }
                    if (s1.startsWith("glass")) {
                        i2 = 1;
                    }
                    if (s1.startsWith("gshadow")) {
                        i2 = 2;
                    }
                    if (s1.startsWith("lightF")) {
                        byte0 = 1;
                    }
                    if (s1.startsWith("lightB")) {
                        byte0 = 2;
                    }
                    if (s1.startsWith("noOutline")) {
                        bool2 = true;
                    }
                    if (s1.startsWith("p")) {
                        ai[i] = (int)(this.getvalue("p", s1, 0) * this.div * this.iwid);
                        ai2[i] = (int)(this.getvalue("p", s1, 1) * this.div);
                        ai3[i] = (int)(this.getvalue("p", s1, 2) * this.div);
                        final int j2 = (int)Math.sqrt(ai[i] * ai[i] + ai2[i] * ai2[i] + ai3[i] * ai3[i]);
                        if (j2 > this.maxR) {
                            this.maxR = j2;
                        }
                        ++i;
                    }
                }
                if (s1.startsWith("</p>")) {
                    this.p[this.npl] = new Plane(this.m, this.t, ai, ai3, ai2, i, ai4, i2, k, l, 0, 0, 0, this.disline, 0, flag3, byte0, bool2);
                    ++this.npl;
                    flag = false;
                }
                if (s1.startsWith("rims")) {
                    wheels.setrims(this.getvalue("rims", s1, 0), this.getvalue("rims", s1, 1), this.getvalue("rims", s1, 2), this.getvalue("rims", s1, 3), this.getvalue("rims", s1, 4));
                }
                if (s1.startsWith("w")) {
                    this.keyx[j] = (int)(this.getvalue("w", s1, 0) * this.div);
                    this.keyz[j] = (int)(this.getvalue("w", s1, 2) * this.div);
                    ++j;
                    wheels.make(this.m, this.t, this.p, this.npl, (int)(this.getvalue("w", s1, 0) * this.div * this.iwid), (int)(this.getvalue("w", s1, 1) * this.div), (int)(this.getvalue("w", s1, 2) * this.div), this.getvalue("w", s1, 3), (int)(this.getvalue("w", s1, 4) * this.div * this.iwid), (int)(this.getvalue("w", s1, 5) * this.div), i3);
                    this.npl += 15;
                }
                if (s1.startsWith("tracks")) {
                    final int k2 = this.getvalue("tracks", s1, 0);
                    this.txy = new int[k2];
                    this.tzy = new int[k2];
                    this.tc = new int[k2][3];
                    this.tradx = new int[k2];
                    this.tradz = new int[k2];
                    this.trady = new int[k2];
                    this.tx = new int[k2];
                    this.ty = new int[k2];
                    this.tz = new int[k2];
                    this.skd = new int[k2];
                    this.dam = new int[k2];
                    this.notwall = new boolean[k2];
                }
                if (s1.startsWith("<track>")) {
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
                    if (s1.startsWith("c")) {
                        this.tc[this.tnt][0] = this.getvalue("c", s1, 0);
                        this.tc[this.tnt][1] = this.getvalue("c", s1, 1);
                        this.tc[this.tnt][2] = this.getvalue("c", s1, 2);
                    }
                    if (s1.startsWith("xy")) {
                        this.txy[this.tnt] = this.getvalue("xy", s1, 0);
                    }
                    if (s1.startsWith("zy")) {
                        this.tzy[this.tnt] = this.getvalue("zy", s1, 0);
                    }
                    if (s1.startsWith("radx")) {
                        this.tradx[this.tnt] = (int)(this.getvalue("radx", s1, 0) * this.div);
                    }
                    if (s1.startsWith("rady")) {
                        this.trady[this.tnt] = (int)(this.getvalue("rady", s1, 0) * this.div);
                    }
                    if (s1.startsWith("radz")) {
                        this.tradz[this.tnt] = (int)(this.getvalue("radz", s1, 0) * this.div);
                    }
                    if (s1.startsWith("ty")) {
                        this.ty[this.tnt] = (int)(this.getvalue("ty", s1, 0) * this.div);
                    }
                    if (s1.startsWith("tx")) {
                        this.tx[this.tnt] = (int)(this.getvalue("tx", s1, 0) * this.div);
                    }
                    if (s1.startsWith("tz")) {
                        this.tz[this.tnt] = (int)(this.getvalue("tz", s1, 0) * this.div);
                    }
                    if (s1.startsWith("skid")) {
                        this.skd[this.tnt] = this.getvalue("skid", s1, 0);
                    }
                    if (s1.startsWith("dam")) {
                        this.dam[this.tnt] = 3;
                    }
                    if (s1.startsWith("firedam")) {
                        this.dam[this.tnt] = 9;
                    }
                    if (s1.startsWith("notwall")) {
                        this.notwall[this.tnt] = true;
                    }
                }
                if (s1.startsWith("</track>")) {
                    flag2 = false;
                    ++this.tnt;
                }
                if (s1.startsWith("disp")) {
                    this.disp = this.getvalue("disp", s1, 0);
                }
                if (s1.startsWith("disline")) {
                    this.disline = this.getvalue("disline", s1, 0);
                }
                if (s1.startsWith("shadow")) {
                    this.shadow = true;
                }
                if (s1.startsWith("stonecold")) {
                    this.noline = true;
                }
                if (s1.startsWith("road")) {
                    flag3 = true;
                }
                if (s1.startsWith("notroad")) {
                    flag3 = false;
                }
                if (s1.startsWith("grounded")) {
                    this.grounded = this.getvalue("grounded", s1, 0) / 100.0f;
                }
                if (s1.startsWith("div")) {
                    this.div = this.getvalue("div", s1, 0) / this.sfactor;
                }
                if (s1.startsWith("idiv")) {
                    this.div = this.getvalue("idiv", s1, 0) / (this.sfactor * 10.0f);
                }
                if (s1.startsWith("iwid")) {
                    this.iwid = this.getvalue("iwid", s1, 0) / 100.0f;
                }
                if (s1.startsWith("gwgr")) {
                    i3 = this.getvalue("gwgr", s1, 0);
                }
            }
            datainputstream.close();
        }
        catch (final Exception exception) {
            System.out.println("ContO Loading Error: " + exception);
            System.out.println("At File: " + abyte0 + ".rad");
            System.out.println("At Line: " + s1);
            System.out.println("--------------------");
        }
        this.grat = wheels.ground;
    }
    
    public ContO(final ContO conto, final int i, final int j, final int k, final int l) {
        this.div = 1.0f;
        this.iwid = 1.0f;
        this.spec = new int[3];
        this.xextreme = new int[2];
        this.zextreme = new int[2];
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
        this.fakegrounded = 1.0f;
        this.spatk = false;
        this.freeze = false;
        this.leech = false;
        this.playerglow = false;
        this.weaken = false;
        this.strswap = false;
        this.disline = 7;
        this.shadow = false;
        this.noline = false;
        this.grounded = 1.0f;
        this.lightup = false;
        this.outoftrack = false;
        this.xextreme = new int[2];
        this.zextreme = new int[2];
        this.dmgcolours = new int[3];
        this.grat = 0;
        this.keyx = new int[6];
        this.keyz = new int[6];
        this.tnt = 0;
        this.sx = new int[6];
        this.sy = new int[6];
        this.sz = new int[6];
        this.stg = new int[6];
        this.dov = new int[6];
        this.smag = new float[6];
        this.scx = new int[6];
        this.scz = new int[6];
        this.fulls = new boolean[6];
        this.elec = false;
        this.roted = false;
        this.edl = new int[6];
        this.edr = new int[6];
        this.elc = new int[6];
        this.fix = false;
        this.fcnt = 0;
        this.flameheight = 1;
        this.invisiblepiece = 255;
        this.glowlines = false;
        this.glowcustom = false;
        this.glowcolour = new int[3];
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
        this.p = new Plane[conto.npl];
        for (int i2 = 0; i2 < this.npl; ++i2) {
            if (conto.p[i2].master != 0) {
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
        for (int j2 = 0; j2 < this.npl; ++j2) {
            this.p[j2].master = conto.p[j2].master;
            this.p[j2].rot(this.p[j2].ox, this.p[j2].oz, 0, 0, l, this.p[j2].n);
            this.p[j2].loadprojf();
        }
        if (this.m.effect[10] || this.m.effect[11]) {
            final int[] xmax = new int[this.npl];
            final int[] xmin = new int[this.npl];
            final int[] zmax = new int[this.npl];
            final int[] zmin = new int[this.npl];
            for (int j3 = 0; j3 < this.npl; ++j3) {
                final int[] xpiece = new int[this.p[j3].n];
                final int[] zpiece = new int[this.p[j3].n];
                for (int a = 0; a < this.p[j3].n; ++a) {
                    xpiece[a] = this.p[j3].ox[a];
                    zpiece[a] = this.p[j3].oz[a];
                    Arrays.sort(xpiece);
                    Arrays.sort(zpiece);
                }
                xmin[j3] = xpiece[0];
                xmax[j3] = xpiece[this.p[j3].n - 1];
                zmin[j3] = zpiece[0];
                zmax[j3] = zpiece[this.p[j3].n - 1];
            }
            Arrays.sort(xmin);
            Arrays.sort(xmax);
            Arrays.sort(zmin);
            Arrays.sort(zmax);
            this.xextreme[0] = xmin[0] + i;
            this.xextreme[1] = xmax[this.npl - 1] + i;
            this.zextreme[0] = zmin[0] + k;
            this.zextreme[1] = zmax[this.npl - 1] + k;
        }
        if (conto.tnt != 0) {
            for (int k2 = 0; k2 < conto.tnt; ++k2) {
                this.t.xy[this.t.nt] = (int)(conto.txy[k2] * this.m.cos(l) - conto.tzy[k2] * this.m.sin(l));
                this.t.zy[this.t.nt] = (int)(conto.tzy[k2] * this.m.cos(l) + conto.txy[k2] * this.m.sin(l));
                int i3 = 0;
                do {
                    this.t.c[this.t.nt][i3] = (int)(conto.tc[k2][i3] + conto.tc[k2][i3] * (this.m.snap[i3] / 100.0f));
                    if (this.t.c[this.t.nt][i3] > 255) {
                        this.t.c[this.t.nt][i3] = 255;
                    }
                    if (this.t.c[this.t.nt][i3] < 0) {
                        this.t.c[this.t.nt][i3] = 0;
                    }
                    this.t.oc[this.t.nt][i3] = this.t.c[this.t.nt][i3];
                } while (++i3 < 3);
                this.t.x[this.t.nt] = (int)(this.x + conto.tx[k2] * this.m.cos(l) - conto.tz[k2] * this.m.sin(l));
                this.t.z[this.t.nt] = (int)(this.z + conto.tz[k2] * this.m.cos(l) + conto.tx[k2] * this.m.sin(l));
                this.t.y[this.t.nt] = this.y + conto.ty[k2];
                this.t.skd[this.t.nt] = conto.skd[k2];
                this.t.dam[this.t.nt] = conto.dam[k2];
                this.t.notwall[this.t.nt] = conto.notwall[k2];
                i3 = Math.abs(l);
                if (i3 == 180) {
                    i3 = 0;
                }
                this.t.radx[this.t.nt] = (int)Math.abs(conto.tradx[k2] * this.m.cos(i3) + conto.tradz[k2] * this.m.sin(i3));
                this.t.radz[this.t.nt] = (int)Math.abs(conto.tradx[k2] * this.m.sin(i3) + conto.tradz[k2] * this.m.cos(i3));
                this.t.rady[this.t.nt] = conto.trady[k2];
                final Trackers t = this.t;
                ++t.nt;
            }
        }
        int l2 = 0;
        do {
            this.stg[l2] = 0;
            this.keyx[l2] = conto.keyx[l2];
            this.keyz[l2] = conto.keyz[l2];
        } while (++l2 < 6);
    }
    
    public ContO(final int paramInt1, final int paramInt2, final int paramInt3, final Medium paramMedium, final Trackers paramTrackers, final int paramInt4, final int paramInt5, final int paramInt6) {
        this.div = 1.0f;
        this.iwid = 1.0f;
        this.spec = new int[3];
        this.xextreme = new int[2];
        this.zextreme = new int[2];
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
        this.glowcolour = new int[3];
        this.disline = 4;
        this.noline = true;
        this.shadow = false;
        this.grounded = 1.0f;
        this.npl = 5;
        this.p = new Plane[5];
        this.stg = new int[6];
        final Random localRandom = new Random(paramInt1);
        final int[] arrayOfInt1 = new int[8];
        final int[] arrayOfInt2 = new int[8];
        final int[] arrayOfInt3 = new int[8];
        final int[] arrayOfInt4 = new int[8];
        final int[] arrayOfInt5 = new int[8];
        float f1 = (float)paramInt2;
        float f2 = (float)paramInt3;
        if (f2 < 1.0f) {
            f2 = 1.0f;
        }
        if (f2 > 6000.0f) {
            f2 = 6000.0f;
        }
        if (f1 < 1.0f) {
            f1 = 1.0f;
        }
        if (f1 > 6000.0f) {
            f1 = 6000.0f;
        }
        f1 /= 1.5f;
        f2 /= 1.5f;
        f2 *= 1.0f + (f1 - 2.0f) * 0.1786f;
        float f3 = (float)(50.0 + 100.0 * localRandom.nextDouble());
        arrayOfInt1[0] = -(int)(f3 * f1 * 0.7071f);
        arrayOfInt2[0] = (int)(f3 * f1 * 0.7071f);
        f3 = (float)(50.0 + 100.0 * localRandom.nextDouble());
        arrayOfInt1[1] = 0;
        arrayOfInt2[1] = (int)(f3 * f1);
        f3 = (float)(50.0 + 100.0 * localRandom.nextDouble());
        arrayOfInt1[2] = (int)(f3 * f1 * 0.7071);
        arrayOfInt2[2] = (int)(f3 * f1 * 0.7071);
        f3 = (float)(50.0 + 100.0 * localRandom.nextDouble());
        arrayOfInt1[3] = (int)(f3 * f1);
        arrayOfInt2[3] = 0;
        f3 = (float)(50.0 + 100.0 * localRandom.nextDouble());
        arrayOfInt1[4] = (int)(f3 * f1 * 0.7071);
        arrayOfInt2[4] = -(int)(f3 * f1 * 0.7071);
        f3 = (float)(50.0 + 100.0 * localRandom.nextDouble());
        arrayOfInt1[5] = 0;
        arrayOfInt2[5] = -(int)(f3 * f1);
        f3 = (float)(50.0 + 100.0 * localRandom.nextDouble());
        arrayOfInt1[6] = -(int)(f3 * f1 * 0.7071);
        arrayOfInt2[6] = -(int)(f3 * f1 * 0.7071);
        f3 = (float)(50.0 + 100.0 * localRandom.nextDouble());
        arrayOfInt1[7] = -(int)(f3 * f1);
        arrayOfInt2[7] = 0;
        for (int i = 0; i < 8; ++i) {
            arrayOfInt3[i] = (int)(arrayOfInt1[i] * (0.2 + 0.4 * localRandom.nextDouble()));
            arrayOfInt4[i] = (int)(arrayOfInt2[i] * (0.2 + 0.4 * localRandom.nextDouble()));
            arrayOfInt5[i] = -(int)((10.0 + 15.0 * localRandom.nextDouble()) * f2);
        }
        this.maxR = 0;
        for (int i = 0; i < 8; ++i) {
            int j = i - 1;
            if (j == -1) {
                j = 7;
            }
            int k = i + 1;
            if (k == 8) {
                k = 0;
            }
            arrayOfInt1[i] = ((arrayOfInt1[j] + arrayOfInt1[k]) / 2 + arrayOfInt1[i]) / 2;
            arrayOfInt2[i] = ((arrayOfInt2[j] + arrayOfInt2[k]) / 2 + arrayOfInt2[i]) / 2;
            arrayOfInt3[i] = ((arrayOfInt3[j] + arrayOfInt3[k]) / 2 + arrayOfInt3[i]) / 2;
            arrayOfInt4[i] = ((arrayOfInt4[j] + arrayOfInt4[k]) / 2 + arrayOfInt4[i]) / 2;
            arrayOfInt5[i] = ((arrayOfInt5[j] + arrayOfInt5[k]) / 2 + arrayOfInt5[i]) / 2;
            int n = (int)Math.sqrt(arrayOfInt1[i] * arrayOfInt1[i] + arrayOfInt2[i] * arrayOfInt2[i]);
            if (n > this.maxR) {
                this.maxR = n;
            }
            n = (int)Math.sqrt(arrayOfInt3[i] * arrayOfInt3[i] + arrayOfInt5[i] * arrayOfInt5[i] + arrayOfInt4[i] * arrayOfInt4[i]);
            if (n > this.maxR) {
                this.maxR = n;
            }
        }
        this.disp = 90;
        final int[] arrayOfInt6 = new int[3];
        float f4 = -1.0f;
        float f5 = (f1 / f2 - 0.33f) / 33.4f;
        if (f5 < 0.005) {
            f5 = 0.0f;
        }
        if (f5 > 0.057) {
            f5 = 0.057f;
        }
        for (int n = 0; n < 4; ++n) {
            final int i2 = n * 2;
            int i3 = i2 + 2;
            if (i3 == 8) {
                i3 = 0;
            }
            final int[] arrayOfInt7 = new int[6];
            final int[] arrayOfInt8 = new int[6];
            final int[] arrayOfInt9 = new int[6];
            arrayOfInt7[0] = arrayOfInt1[i2];
            arrayOfInt7[1] = arrayOfInt1[i2 + 1];
            arrayOfInt7[2] = arrayOfInt1[i3];
            arrayOfInt7[5] = arrayOfInt3[i2];
            arrayOfInt7[4] = arrayOfInt3[i2 + 1];
            arrayOfInt7[3] = arrayOfInt3[i3];
            arrayOfInt9[0] = arrayOfInt2[i2];
            arrayOfInt9[1] = arrayOfInt2[i2 + 1];
            arrayOfInt9[2] = arrayOfInt2[i3];
            arrayOfInt9[5] = arrayOfInt4[i2];
            arrayOfInt9[4] = arrayOfInt4[i2 + 1];
            arrayOfInt9[3] = arrayOfInt4[i3];
            arrayOfInt8[0] = 0;
            arrayOfInt8[2] = (arrayOfInt8[1] = 0);
            arrayOfInt8[5] = arrayOfInt5[i2];
            arrayOfInt8[4] = arrayOfInt5[i2 + 1];
            arrayOfInt8[3] = arrayOfInt5[i3];
            for (f3 = (float)((0.17 - f5) * localRandom.nextDouble()); Math.abs(f4 - f3) < 0.03 - f5 * 0.176f; f3 = (f4 = (float)((0.17 - f5) * localRandom.nextDouble()))) {}
            for (int i4 = 0; i4 < 3; ++i4) {
                arrayOfInt6[i4] = (this.m.cgrnd[i4] + this.m.cpol[i4]) / 2;
            }
            this.p[n] = new Plane(this.m, this.t, arrayOfInt7, arrayOfInt9, arrayOfInt8, 6, arrayOfInt6, 3, -8, 0, 0, 0, 0, this.disline, 0, false, 0, false);
        }
        f3 = (float)(0.02 * localRandom.nextDouble());
        for (int n = 0; n < 3; ++n) {
            arrayOfInt6[n] = (this.m.cgrnd[n] + this.m.cpol[n]) / 2;
        }
        this.p[4] = new Plane(this.m, this.t, arrayOfInt3, arrayOfInt4, arrayOfInt5, 8, arrayOfInt6, 3, -8, 0, 0, 0, 0, this.disline, 0, false, 0, false);
        final int[] arrayOfInt10 = new int[2];
        final int[] arrayOfInt11 = new int[2];
        for (int i3 = 0; i3 < 4; ++i3) {
            int i5 = i3 * 2 + 1;
            this.t.y[this.t.nt] = arrayOfInt5[i5] / 2;
            this.t.rady[this.t.nt] = Math.abs(arrayOfInt5[i5] / 2);
            if (i3 == 0 || i3 == 2) {
                this.t.z[this.t.nt] = (arrayOfInt2[i5] + arrayOfInt4[i5]) / 2;
                this.t.radz[this.t.nt] = Math.abs(this.t.z[this.t.nt] - arrayOfInt2[i5]);
                i5 = i3 * 2 + 2;
                if (i5 == 8) {
                    i5 = 0;
                }
                this.t.x[this.t.nt] = (arrayOfInt1[i3 * 2] + arrayOfInt1[i5]) / 2;
                this.t.radx[this.t.nt] = Math.abs(this.t.x[this.t.nt] - arrayOfInt1[i3 * 2]);
            }
            else {
                this.t.x[this.t.nt] = (arrayOfInt1[i5] + arrayOfInt3[i5]) / 2;
                this.t.radx[this.t.nt] = Math.abs(this.t.x[this.t.nt] - arrayOfInt1[i5]);
                i5 = i3 * 2 + 2;
                if (i5 == 8) {
                    i5 = 0;
                }
                this.t.z[this.t.nt] = (arrayOfInt2[i3 * 2] + arrayOfInt2[i5]) / 2;
                this.t.radz[this.t.nt] = Math.abs(this.t.z[this.t.nt] - arrayOfInt2[i3 * 2]);
            }
            if (i3 == 0) {
                arrayOfInt11[0] = this.t.z[this.t.nt] - this.t.radz[this.t.nt];
                this.t.zy[this.t.nt] = (int)(Math.atan(this.t.rady[this.t.nt] / this.t.radz[this.t.nt]) / 0.0174532925199433);
                if (this.t.zy[this.t.nt] > 40) {
                    this.t.zy[this.t.nt] = 40;
                }
                this.t.xy[this.t.nt] = 0;
            }
            if (i3 == 1) {
                arrayOfInt10[0] = this.t.x[this.t.nt] - this.t.radx[this.t.nt];
                this.t.xy[this.t.nt] = (int)(Math.atan(this.t.rady[this.t.nt] / this.t.radx[this.t.nt]) / 0.0174532925199433);
                if (this.t.xy[this.t.nt] > 40) {
                    this.t.xy[this.t.nt] = 40;
                }
                this.t.zy[this.t.nt] = 0;
            }
            if (i3 == 2) {
                arrayOfInt11[1] = this.t.z[this.t.nt] + this.t.radz[this.t.nt];
                this.t.zy[this.t.nt] = -(int)(Math.atan(this.t.rady[this.t.nt] / this.t.radz[this.t.nt]) / 0.0174532925199433);
                if (this.t.zy[this.t.nt] < -40) {
                    this.t.zy[this.t.nt] = -40;
                }
                this.t.xy[this.t.nt] = 0;
            }
            if (i3 == 3) {
                arrayOfInt10[1] = this.t.x[this.t.nt] + this.t.radx[this.t.nt];
                this.t.xy[this.t.nt] = -(int)(Math.atan(this.t.rady[this.t.nt] / this.t.radx[this.t.nt]) / 0.0174532925199433);
                if (this.t.xy[this.t.nt] < -40) {
                    this.t.xy[this.t.nt] = -40;
                }
                this.t.zy[this.t.nt] = 0;
            }
            final int[] x = this.t.x;
            final int nt = this.t.nt;
            x[nt] += this.x;
            final int[] z = this.t.z;
            final int nt2 = this.t.nt;
            z[nt2] += this.z;
            final int[] y = this.t.y;
            final int nt3 = this.t.nt;
            y[nt3] += this.y;
            for (int i6 = 0; i6 < 3; ++i6) {
                this.t.c[this.t.nt][i6] = this.p[i3].oc[i6];
            }
            this.t.skd[this.t.nt] = 2;
            this.t.dam[this.t.nt] = 1;
            this.t.notwall[this.t.nt] = false;
            final int[] rady = this.t.rady;
            final int nt4 = this.t.nt;
            rady[nt4] += 10;
            final Trackers t = this.t;
            ++t.nt;
        }
        this.t.y[this.t.nt] = 0;
        for (int i3 = 0; i3 < 8; ++i3) {
            final int[] y2 = this.t.y;
            final int nt5 = this.t.nt;
            y2[nt5] += arrayOfInt5[i3];
        }
        final int[] y3 = this.t.y;
        final int nt6 = this.t.nt;
        y3[nt6] /= 8;
        final int[] y4 = this.t.y;
        final int nt7 = this.t.nt;
        y4[nt7] += this.y;
        this.t.rady[this.t.nt] = 20000;
        this.t.radx[this.t.nt] = arrayOfInt10[0] - arrayOfInt10[1];
        this.t.radz[this.t.nt] = arrayOfInt11[0] - arrayOfInt11[1];
        this.t.x[this.t.nt] = (arrayOfInt10[0] + arrayOfInt10[1]) / 2 + this.x;
        this.t.z[this.t.nt] = (arrayOfInt11[0] + arrayOfInt11[1]) / 2 + this.z;
        this.t.zy[this.t.nt] = 0;
        this.t.xy[this.t.nt] = 0;
        for (int i3 = 0; i3 < 3; ++i3) {
            this.t.c[this.t.nt][i3] = this.p[4].oc[i3];
            this.t.oc[this.t.nt][i3] = this.p[4].oc[i3];
        }
        this.t.skd[this.t.nt] = 4;
        this.t.dam[this.t.nt] = 1;
        this.t.notwall[this.t.nt] = false;
        final Trackers t2 = this.t;
        ++t2.nt;
    }
    
    public void d(final Graphics2D g) {
        if (this.dist != 0) {
            this.dist = 0;
        }
        final int i = this.m.cx + (int)((this.x - this.m.x - this.m.cx) * this.m.cos(this.m.xz) - (this.z - this.m.z - this.m.cz) * this.m.sin(this.m.xz));
        final int j = this.m.cz + (int)((this.x - this.m.x - this.m.cx) * this.m.sin(this.m.xz) + (this.z - this.m.z - this.m.cz) * this.m.cos(this.m.xz));
        final int k = this.m.cz + (int)((this.y - this.m.y - this.m.cy) * this.m.sin(this.m.zy) + (j - this.m.cz) * this.m.cos(this.m.zy));
        int l = this.xs(i + this.maxR, k) - this.xs(i - this.maxR, k);
        if (this.xs(i + this.maxR * 2, k) > 0 && this.xs(i - this.maxR * 2, k) < this.m.w && k > -this.maxR && (k < this.m.fade[this.disline] + this.maxR || this.m.trk) && (l > this.disp || this.m.trk)) {
            if (this.shadow) {
                if (!this.m.crs) {
                    if (k < 2000) {
                        boolean flag = false;
                        for (int l2 = this.t.nt - 1; l2 >= 0; --l2) {
                            if (Math.abs(this.t.zy[l2]) != 90 && Math.abs(this.t.xy[l2]) != 90 && Math.abs(this.x - this.t.x[l2]) < this.t.radx[l2] + this.maxR && Math.abs(this.z - this.t.z[l2]) < this.t.radz[l2] + this.maxR) {
                                flag = true;
                                break;
                            }
                        }
                        if (flag) {
                            for (int i2 = 0; i2 < this.npl; ++i2) {
                                this.p[i2].s(g, this.x - this.m.x, this.y - this.m.y, this.z - this.m.z, this.xz, this.xy, this.zy, 0, this.groundlevel - this.m.y, this.teleported, this.telefade, this.spec, this.outoftrack);
                            }
                        }
                        else {
                            final int j2 = this.m.cy + (int)((this.groundlevel - this.m.y - this.m.cy) * this.m.cos(this.m.zy) - (j - this.m.cz) * this.m.sin(this.m.zy));
                            final int k2 = this.m.cz + (int)((this.groundlevel - this.m.y - this.m.cy) * this.m.sin(this.m.zy) + (j - this.m.cz) * this.m.cos(this.m.zy));
                            if (this.ys(j2 + this.maxR, k2) > 0 && this.ys(j2 - this.maxR, k2) < this.m.h) {
                                for (int l3 = 0; l3 < this.npl; ++l3) {
                                    this.p[l3].s(g, this.x - this.m.x, this.y - this.m.y, this.z - this.m.z, this.xz, this.xy, this.zy, 1, this.groundlevel - this.m.y, this.teleported, this.telefade, this.spec, this.outoftrack);
                                }
                            }
                        }
                        this.m.addsp(this.x - this.m.x, this.z - this.m.z, (int)(this.maxR * 0.8));
                    }
                    else {
                        this.lowshadow(g, k);
                    }
                }
                else {
                    for (int i3 = 0; i3 < this.npl; ++i3) {
                        this.p[i3].s(g, this.x - this.m.x, this.y - this.m.y, this.z - this.m.z, this.xz, this.xy, this.zy, 2, this.groundlevel - this.m.y, this.teleported, this.telefade, this.spec, this.outoftrack);
                    }
                }
            }
            final int j3 = this.m.cy + (int)((this.y - this.m.y - this.m.cy) * this.m.cos(this.m.zy) - (j - this.m.cz) * this.m.sin(this.m.zy));
            if (this.ys(j3 + this.maxR, k) > 0 && this.ys(j3 - this.maxR, k) < this.m.h) {
                if (this.elec) {
                    this.electrify(g);
                }
                if (this.fix) {
                    this.fixit(g);
                }
                if (this.m.showsnow) {
                    this.drawsnow(g, this.m.snowno, this.m.snowheight);
                }
                final boolean groundcolour = this.m.groundcolour;
                if (this.checkpoint != 0 && this.checkpoint - 1 == this.m.checkpoint) {
                    l = -1;
                }
                final int[] ai = new int[this.npl];
                final int[] ai2 = new int[this.npl];
                int i4 = 0;
                do {
                    if (this.stg[i4] != 0 && !this.teleported && !this.m.effect[11]) {
                        this.pdust(i4, g, -1);
                    }
                } while (++i4 < 4);
                for (int j4 = 0; j4 < this.npl; ++j4) {
                    ai[j4] = 0;
                }
                for (int k3 = 0; k3 < this.npl; ++k3) {
                    for (int i5 = k3 + 1; i5 < this.npl; ++i5) {
                        if (this.p[k3].av != this.p[i5].av) {
                            if (this.p[k3].av < this.p[i5].av) {
                                final int[] array = ai;
                                final int n = k3;
                                ++array[n];
                            }
                            else {
                                final int[] array2 = ai;
                                final int n2 = i5;
                                ++array2[n2];
                            }
                        }
                        else if (k3 > i5) {
                            final int[] array3 = ai;
                            final int n3 = k3;
                            ++array3[n3];
                        }
                        else {
                            final int[] array4 = ai;
                            final int n4 = i5;
                            ++array4[n4];
                        }
                    }
                    ai2[ai[k3]] = k3;
                }
                for (int l4 = 0; l4 < this.npl; ++l4) {
                    if ((this.m.switchfase == 0 || this.m.switchfase == 10 || this.m.switchfase == 20) && this.m.effect[2] && !this.m.trk && this.p[ai2[l4]].embos == 0) {
                        this.p[ai2[l4]].recolour();
                    }
                    if (this.m.effect[10] && !this.isacar) {
                        this.glowlines = true;
                        this.rainbow(ai2[l4]);
                    }
                    this.p[ai2[l4]].d(g, this.x - this.m.x, this.y - this.m.y, this.z - this.m.z, this.xz, this.xy, this.zy, (int)this.wxz, this.wzy, this.noline, l, this.weakstage, this.shadowcar, this.greenflame, this.flameheight, this.invisiblepiece, this.glowlines, this.glowcolour, this.spatk, this.freeze, this.weaken, this.leech, this.strswap, this.spec[0], this.spec[1], this.spec[2], this.groundlevel - this.m.y, this.playerglow, this.teleported, this.telefade, this.floorguardian, this.dmgcolours, this.isacar, this.lightup, this.outoftrack);
                    if (this.p[ai2[l4]].master != 0 && this.stg[this.p[ai2[l4]].master - 1] != 0 && !this.teleported && !this.m.effect[11]) {
                        this.pdust(this.p[ai2[l4]].master - 1, g, 1);
                    }
                }
                float whichgrounded = this.grounded;
                if (this.m.effect[9]) {
                    whichgrounded = this.fakegrounded;
                }
                this.dist = (int)(Math.sqrt((int)Math.sqrt((this.m.x + this.m.cx - this.x) * (this.m.x + this.m.cx - this.x) + (this.m.z - this.z) * (this.m.z - this.z) + (this.m.y + this.m.cy - this.y) * (this.m.y + this.m.cy - this.y))) * whichgrounded);
            }
        }
        if (this.dist == 0) {
            int k4 = 0;
            do {
                if (this.stg[k4] != 0) {
                    if (this.stg[k4] == 4) {
                        this.stg[k4] = 0;
                    }
                    else {
                        final int[] stg = this.stg;
                        final int n5 = k4;
                        ++stg[n5];
                    }
                }
            } while (++k4 < 4);
        }
    }
    
    public int getpy(final int i, final int j, final int k) {
        return (i - this.x) / 10 * ((i - this.x) / 10) + (j - this.y) / 10 * ((j - this.y) / 10) + (k - this.z) / 10 * ((k - this.z) / 10);
    }
    
    public void rainbow(final int id) {
        if (id % 14 <= 1) {
            this.glowcolour[0] = 200;
            this.glowcolour[1] = 0;
            this.glowcolour[2] = 0;
        }
        if (id % 14 == 2 || id % 14 == 3) {
            this.glowcolour[0] = 200;
            this.glowcolour[1] = 100;
            this.glowcolour[2] = 0;
        }
        if (id % 14 == 4 || id % 14 == 5) {
            this.glowcolour[0] = 200;
            this.glowcolour[1] = 200;
            this.glowcolour[2] = 0;
        }
        if (id % 14 == 6 || id % 14 == 7) {
            this.glowcolour[0] = 0;
            this.glowcolour[1] = 200;
            this.glowcolour[2] = 0;
        }
        if (id % 14 == 8 || id % 14 == 9) {
            this.glowcolour[0] = 0;
            this.glowcolour[1] = 0;
            this.glowcolour[2] = 200;
        }
        if (id % 14 == 10 || id % 14 == 11) {
            this.glowcolour[0] = 59;
            this.glowcolour[1] = 0;
            this.glowcolour[2] = 102;
        }
        if (id % 14 == 12 || id % 14 == 13) {
            this.glowcolour[0] = 116;
            this.glowcolour[1] = 0;
            this.glowcolour[2] = 165;
        }
    }
    
    public void rot(final int[] ai, final int[] ai1, final int i, final int j, final int k, final int l) {
        if (k != 0) {
            for (int i2 = 0; i2 < l; ++i2) {
                final int j2 = ai[i2];
                final int k2 = ai1[i2];
                ai[i2] = i + (int)((j2 - i) * this.m.cos(k) - (k2 - j) * this.m.sin(k));
                ai1[i2] = j + (int)((j2 - i) * this.m.sin(k) + (k2 - j) * this.m.cos(k));
            }
        }
    }
    
    public void dust(final int i, final float f, final float f1, final float f2, final float f3, final float f4, final float f5, final boolean flag, final int j) {
        boolean flag2 = false;
        if (j > 5 && (i == 0 || i == 2)) {
            flag2 = true;
        }
        if (j < -5 && (i == 1 || i == 3)) {
            flag2 = true;
        }
        if (this.stg[i] == 0 && Math.abs(f3) + Math.abs(f4) > 100.0f && !flag2) {
            this.sx[i] = (int)f;
            this.sy[i] = (int)f1;
            this.sz[i] = (int)f2;
            this.stg[i] = 1;
            this.dov[i] = -1;
            this.smag[i] = f5;
            this.scx[i] = (int)f3;
            this.scz[i] = (int)f4;
            this.fulls[i] = flag;
        }
    }
    
    public int getvalue(final String s, final String s1, final int i) {
        int k = 0;
        String s2 = "";
        for (int j = s.length() + 1; j < s1.length(); ++j) {
            final String s3 = new StringBuilder().append(s1.charAt(j)).toString();
            if (s3.equals(",") || s3.equals(")")) {
                ++k;
                ++j;
            }
            if (k == i) {
                s2 = String.valueOf(s2) + s1.charAt(j);
            }
        }
        return Integer.valueOf(s2);
    }
    
    public int xs(final int i, int j) {
        if (j < 50) {
            j = 50;
        }
        return (j - this.m.focus_point) * (this.m.cx - i) / j + i;
    }
    
    public void lowshadow(final Graphics2D g, final int i) {
        final int[] ai = new int[4];
        final int[] ai2 = new int[4];
        final int[] ai3 = new int[4];
        byte byte0 = 1;
        int j;
        for (j = Math.abs(this.zy); j > 270; j -= 360) {}
        j = Math.abs(j);
        if (j > 90) {
            byte0 = -1;
        }
        ai[0] = (int)(this.keyx[0] * 1.2 + this.x - this.m.x);
        ai3[0] = (int)((this.keyz[0] + 30) * byte0 * 1.2 + this.z - this.m.z);
        ai[1] = (int)(this.keyx[1] * 1.2 + this.x - this.m.x);
        ai3[1] = (int)((this.keyz[1] + 30) * byte0 * 1.2 + this.z - this.m.z);
        ai[2] = (int)(this.keyx[3] * 1.2 + this.x - this.m.x);
        ai3[2] = (int)((this.keyz[3] - 30) * byte0 * 1.2 + this.z - this.m.z);
        ai[3] = (int)(this.keyx[2] * 1.2 + this.x - this.m.x);
        ai3[3] = (int)((this.keyz[2] - 30) * byte0 * 1.2 + this.z - this.m.z);
        this.rot(ai, ai3, this.x - this.m.x, this.z - this.m.z, this.xz, 4);
        final int[] coladj = { this.m.cgrnd[0], this.m.cgrnd[1], this.m.cgrnd[2] };
        this.sred = (int)((float)coladj[0] / 1.5);
        this.sgreen = (int)((float)coladj[1] / 1.5);
        this.sblue = (int)((float)coladj[2] / 1.5);
        int j2 = 0;
        do {
            ai2[j2] = this.groundlevel - this.m.y;
        } while (++j2 < 4);
        for (int k1 = this.t.nt - 1; k1 >= 0; --k1) {
            if (this.t.y[k1] == this.groundlevel || !this.m.effect[9]) {
                if (this.t.y[k1] <= this.groundlevel || this.m.effect[9]) {
                    int l1 = 0;
                    int j3 = 0;
                    do {
                        if (Math.abs(this.t.zy[k1]) != 90 && Math.abs(this.t.xy[k1]) != 90 && Math.abs(ai[j3] - (this.t.x[k1] - this.m.x)) < this.t.radx[k1] && Math.abs(ai3[j3] - (this.t.z[k1] - this.m.z)) < this.t.radz[k1]) {
                            ++l1;
                        }
                    } while (++j3 < 4);
                    if (l1 > 2) {
                        j3 = 0;
                        do {
                            ai2[j3] = this.t.y[k1] - this.m.y;
                            if (this.t.zy[k1] != 0) {
                                final int[] array = ai2;
                                final int n = j3;
                                array[n] += ((ai3[j3] - (this.t.z[k1] - this.m.z - this.t.radz[k1])) * this.m.sin(this.t.zy[k1]) / this.m.sin(90 - this.t.zy[k1]) - this.t.radz[k1] * this.m.sin(this.t.zy[k1]) / this.m.sin(90 - this.t.zy[k1]));  // cast: bytecode-verified
                            }
                            if (this.t.xy[k1] != 0) {
                                final int[] array2 = ai2;
                                final int n2 = j3;
                                array2[n2] += ((ai[j3] - (this.t.x[k1] - this.m.x - this.t.radx[k1])) * this.m.sin(this.t.xy[k1]) / this.m.sin(90 - this.t.xy[k1]) - this.t.radx[k1] * this.m.sin(this.t.xy[k1]) / this.m.sin(90 - this.t.xy[k1]));  // cast: bytecode-verified
                            }
                        } while (++j3 < 4);
                        int red = this.t.c[k1][0];
                        int green = this.t.c[k1][1];
                        int blue = this.t.c[k1][2];
                        if ((this.m.switchfase == 0 || this.m.switchfase == 10 || this.m.switchfase == 20) && this.m.effect[2] && !this.m.trk) {
                            red = (int)(this.t.oc[k1][0] + this.t.oc[k1][0] * (this.m.snap[0] / 100.0f));
                            green = (int)(this.t.oc[k1][1] + this.t.oc[k1][1] * (this.m.snap[1] / 100.0f));
                            blue = (int)(this.t.oc[k1][2] + this.t.oc[k1][2] * (this.m.snap[2] / 100.0f));
                        }
                        this.sred = (int)((float)red / 1.5);
                        this.sgreen = (int)((float)green / 1.5);
                        this.sblue = (int)((float)blue / 1.5);
                        break;
                    }
                }
            }
        }
        this.rot(ai, ai3, this.m.cx, this.m.cz, this.m.xz, 4);
        this.rot(ai2, ai3, this.m.cy, this.m.cz, this.m.zy, 4);
        boolean flag = true;
        int i2 = 0;
        int k2 = 0;
        int l2 = 0;
        int i3 = 0;
        int j4 = 0;
        do {
            ai[j4] = this.xs(ai[j4], ai3[j4]);
            ai2[j4] = this.ys(ai2[j4], ai3[j4]);
            if (ai2[j4] < 0 || ai3[j4] < 10) {
                ++i2;
            }
            if (ai2[j4] > this.m.h || ai3[j4] < 10) {
                ++k2;
            }
            if (ai[j4] < 0 || ai3[j4] < 10) {
                ++l2;
            }
            if (ai[j4] > this.m.w || ai3[j4] < 10) {
                ++i3;
            }
        } while (++j4 < 4);
        if (l2 == 4 || i2 == 4 || k2 == 4 || i3 == 4) {
            flag = false;
        }
        if (flag) {
            int k3 = 0;
            do {
                if (i > this.m.fade[k3]) {
                    this.sred = (this.sred * this.m.fogd + this.m.cfade[0]) / (this.m.fogd + 1);
                    this.sgreen = (this.sgreen * this.m.fogd + this.m.cfade[1]) / (this.m.fogd + 1);
                    this.sblue = (this.sblue * this.m.fogd + this.m.cfade[2]) / (this.m.fogd + 1);
                }
            } while (++k3 < 8);
            g.setColor(new Color(this.sred, this.sgreen, this.sblue));
            if (this.teleported) {
                g.setColor(new Color(this.sred, this.sgreen, this.sblue, this.telefade));
            }
            g.fillPolygon(ai, ai2, 4);
            if (this.m.effect[11] && this.outoftrack) {
                g.setColor(new Color(this.spec[0], this.spec[1], this.spec[2], 50));
                g.drawPolygon(ai, ai2, 4);
            }
        }
    }
    
    public void teleflash(final Graphics2D rd) {
        final int[] ai = new int[8];
        final int[] ai2 = new int[8];
        final int[] ai3 = new int[4];
        int j1 = 0;
        do {
            ai[j1] = this.keyx[j1] + this.x - this.m.x;
            ai2[j1] = this.grat + this.y - this.m.y;
            ai3[j1] = this.keyz[j1] + this.z - this.m.z;
        } while (++j1 < 4);
        this.rot(ai, ai2, this.x - this.m.x, this.y - this.m.y, this.xy, 4);
        this.rot(ai2, ai3, this.y - this.m.y, this.z - this.m.y, this.zy, 4);
        this.rot(ai, ai3, this.x - this.m.x, this.z - this.m.z, this.xz, 4);
        this.rot(ai, ai3, this.m.cx, this.m.cz, this.m.xz, 4);
        this.rot(ai2, ai3, this.m.cy, this.m.cz, this.m.zy, 4);
        j1 = 0;
        int l1 = 0;
        int i2 = 0;
        int j2 = 0;
        do {
            int k2 = 0;
            do {
                if (Math.abs(ai[j2] - ai[k2]) > j1) {
                    j1 = Math.abs(ai[j2] - ai[k2]);
                }
                if (Math.abs(ai2[j2] - ai2[k2]) > l1) {
                    l1 = Math.abs(ai2[j2] - ai2[k2]);
                }
                if (this.py(ai[j2], ai[k2], ai2[j2], ai2[k2]) > i2) {
                    i2 = this.py(ai[j2], ai[k2], ai2[j2], ai2[k2]);
                }
            } while (++k2 < 4);
        } while (++j2 < 4);
        i2 = (int)(Math.sqrt(i2) / 1.5);
        if (j1 < i2) {
            j1 = i2;
        }
        if (l1 < i2) {
            l1 = i2;
        }
        j2 = this.m.cx + (int)((this.x - this.m.x - this.m.cx) * this.m.cos(this.m.xz) - (this.z - this.m.z - this.m.cz) * this.m.sin(this.m.xz));
        int l2 = this.m.cz + (int)((this.x - this.m.x - this.m.cx) * this.m.sin(this.m.xz) + (this.z - this.m.z - this.m.cz) * this.m.cos(this.m.xz));
        final int i3 = this.m.cy + (int)((this.y - this.m.y - this.m.cy) * this.m.cos(this.m.zy) - (l2 - this.m.cz) * this.m.sin(this.m.zy));
        l2 = this.m.cz + (int)((this.y - this.m.y - this.m.cy) * this.m.sin(this.m.zy) + (l2 - this.m.cz) * this.m.cos(this.m.zy));
        ai[0] = this.xs((int)(j2 - j1 / 0.8 - this.m.random() * (j1 / 2.4)), l2);
        ai2[0] = this.ys((int)(i3 - l1 / 1.92 - this.m.random() * (l1 / 5.67)), l2);
        ai[1] = this.xs((int)(j2 - j1 / 0.8 - this.m.random() * (j1 / 2.4)), l2);
        ai2[1] = this.ys((int)(i3 + l1 / 1.92 + this.m.random() * (l1 / 5.67)), l2);
        ai[2] = this.xs((int)(j2 - j1 / 1.92 - this.m.random() * (j1 / 5.67)), l2);
        ai2[2] = this.ys((int)(i3 + l1 / 0.8 + this.m.random() * (l1 / 2.4)), l2);
        ai[3] = this.xs((int)(j2 + j1 / 1.92 + this.m.random() * (j1 / 5.67)), l2);
        ai2[3] = this.ys((int)(i3 + l1 / 0.8 + this.m.random() * (l1 / 2.4)), l2);
        ai[4] = this.xs((int)(j2 + j1 / 0.8 + this.m.random() * (j1 / 2.4)), l2);
        ai2[4] = this.ys((int)(i3 + l1 / 1.92 + this.m.random() * (l1 / 5.67)), l2);
        ai[5] = this.xs((int)(j2 + j1 / 0.8 + this.m.random() * (j1 / 2.4)), l2);
        ai2[5] = this.ys((int)(i3 - l1 / 1.92 - this.m.random() * (l1 / 5.67)), l2);
        ai[6] = this.xs((int)(j2 + j1 / 1.92 + this.m.random() * (j1 / 5.67)), l2);
        ai2[6] = this.ys((int)(i3 - l1 / 0.8 - this.m.random() * (l1 / 2.4)), l2);
        ai[7] = this.xs((int)(j2 - j1 / 1.92 - this.m.random() * (j1 / 5.67)), l2);
        ai2[7] = this.ys((int)(i3 - l1 / 0.8 - this.m.random() * (l1 / 2.4)), l2);
        this.rot(ai, ai2, this.xs(j2, l2), this.ys(i3, l2), 22, 8);
        int j3 = (int)(25.0f + 25.0f * (this.m.snap[0] / 200.0f));
        if (j3 > 25) {
            j3 = 25;
        }
        if (j3 < 0) {
            j3 = 0;
        }
        int k3 = (int)(0.0f + 0.0f * (this.m.snap[1] / 200.0f));
        if (k3 > 0) {
            k3 = 0;
        }
        if (k3 < 0) {
            k3 = 0;
        }
        int l3 = (int)(0.0f + 0.0f * (this.m.snap[2] / 200.0f));
        if (l3 > 0) {
            l3 = 0;
        }
        if (l3 < 0) {
            l3 = 0;
        }
        rd.setColor(new Color(j3, k3, l3));
        rd.fillPolygon(ai, ai2, 8);
        ai[0] = this.xs((int)(j2 - j1 - this.m.random() * (j1 / 4)), l2);
        ai2[0] = this.ys((int)(i3 - l1 / 2.4 - this.m.random() * (l1 / 9.6)), l2);
        ai[1] = this.xs((int)(j2 - j1 - this.m.random() * (j1 / 4)), l2);
        ai2[1] = this.ys((int)(i3 + l1 / 2.4 + this.m.random() * (l1 / 9.6)), l2);
        ai[2] = this.xs((int)(j2 - j1 / 2.4 - this.m.random() * (j1 / 9.6)), l2);
        ai2[2] = this.ys((int)(i3 + l1 + this.m.random() * (l1 / 4)), l2);
        ai[3] = this.xs((int)(j2 + j1 / 2.4 + this.m.random() * (j1 / 9.6)), l2);
        ai2[3] = this.ys((int)(i3 + l1 + this.m.random() * (l1 / 4)), l2);
        ai[4] = this.xs((int)(j2 + j1 + this.m.random() * (j1 / 4)), l2);
        ai2[4] = this.ys((int)(i3 + l1 / 2.4 + this.m.random() * (l1 / 9.6)), l2);
        ai[5] = this.xs((int)(j2 + j1 + this.m.random() * (j1 / 4)), l2);
        ai2[5] = this.ys((int)(i3 - l1 / 2.4 - this.m.random() * (l1 / 9.6)), l2);
        ai[6] = this.xs((int)(j2 + j1 / 2.4 + this.m.random() * (j1 / 9.6)), l2);
        ai2[6] = this.ys((int)(i3 - l1 - this.m.random() * (l1 / 4)), l2);
        ai[7] = this.xs((int)(j2 - j1 / 2.4 - this.m.random() * (j1 / 9.6)), l2);
        ai2[7] = this.ys((int)(i3 - l1 - this.m.random() * (l1 / 4)), l2);
        j3 = (int)(255.0f + 255.0f * (this.m.snap[0] / 200.0f));
        if (j3 > 255) {
            j3 = 255;
        }
        if (j3 < 0) {
            j3 = 0;
        }
        k3 = (int)(25.0f + 25.0f * (this.m.snap[1] / 200.0f));
        if (k3 > 255) {
            k3 = 255;
        }
        if (k3 < 0) {
            k3 = 0;
        }
        l3 = (int)(25.0f + 25.0f * (this.m.snap[2] / 200.0f));
        if (l3 > 255) {
            l3 = 255;
        }
        if (l3 < 0) {
            l3 = 0;
        }
        rd.setColor(new Color(j3, k3, l3));
        rd.fillPolygon(ai, ai2, 8);
    }
    
    public void fixit(final Graphics2D g) {
        if (this.fcnt == 1) {
            for (int i = 0; i < this.npl; ++i) {
                this.p[i].hsb[0] = 0.57f;
                this.p[i].hsb[2] = 0.8f;
                this.p[i].hsb[1] = 0.8f;
                final Color color = Color.getHSBColor(this.p[i].hsb[0], this.p[i].hsb[1], this.p[i].hsb[2]);
                int l = (int)(color.getRed() + color.getRed() * (this.m.snap[0] / 100.0f));
                if (l > 255) {
                    l = 255;
                }
                if (l < 0) {
                    l = 0;
                }
                int i2 = (int)(color.getGreen() + color.getGreen() * (this.m.snap[1] / 100.0f));
                if (i2 > 255) {
                    i2 = 255;
                }
                if (i2 < 0) {
                    i2 = 0;
                }
                int k1 = (int)(color.getBlue() + color.getBlue() * (this.m.snap[2] / 100.0f));
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
        if (this.fcnt == 2) {
            for (int j = 0; j < this.npl; ++j) {
                this.p[j].flx = 1;
            }
        }
        if (this.fcnt == 4) {
            for (int m = 0; m < this.npl; ++m) {
                this.p[m].flx = 3;
            }
        }
        if (this.fcnt == 1 || this.fcnt > 2) {
            final int[] ai = new int[8];
            final int[] ai2 = new int[8];
            final int[] ai3 = new int[4];
            int j2 = 0;
            do {
                ai[j2] = this.keyx[j2] + this.x - this.m.x;
                ai2[j2] = this.grat + this.y - this.m.y;
                ai3[j2] = this.keyz[j2] + this.z - this.m.z;
            } while (++j2 < 4);
            this.rot(ai, ai2, this.x - this.m.x, this.y - this.m.y, this.xy, 4);
            this.rot(ai2, ai3, this.y - this.m.y, this.z - this.m.y, this.zy, 4);
            this.rot(ai, ai3, this.x - this.m.x, this.z - this.m.z, this.xz, 4);
            this.rot(ai, ai3, this.m.cx, this.m.cz, this.m.xz, 4);
            this.rot(ai2, ai3, this.m.cy, this.m.cz, this.m.zy, 4);
            j2 = 0;
            int l2 = 0;
            int i3 = 0;
            int j3 = 0;
            do {
                int k2 = 0;
                do {
                    if (Math.abs(ai[j3] - ai[k2]) > j2) {
                        j2 = Math.abs(ai[j3] - ai[k2]);
                    }
                    if (Math.abs(ai2[j3] - ai2[k2]) > l2) {
                        l2 = Math.abs(ai2[j3] - ai2[k2]);
                    }
                    if (this.py(ai[j3], ai[k2], ai2[j3], ai2[k2]) > i3) {
                        i3 = this.py(ai[j3], ai[k2], ai2[j3], ai2[k2]);
                    }
                } while (++k2 < 4);
            } while (++j3 < 4);
            i3 = (int)(Math.sqrt(i3) / 1.5);
            if (j2 < i3) {
                j2 = i3;
            }
            if (l2 < i3) {
                l2 = i3;
            }
            j3 = this.m.cx + (int)((this.x - this.m.x - this.m.cx) * this.m.cos(this.m.xz) - (this.z - this.m.z - this.m.cz) * this.m.sin(this.m.xz));
            int l3 = this.m.cz + (int)((this.x - this.m.x - this.m.cx) * this.m.sin(this.m.xz) + (this.z - this.m.z - this.m.cz) * this.m.cos(this.m.xz));
            final int i4 = this.m.cy + (int)((this.y - this.m.y - this.m.cy) * this.m.cos(this.m.zy) - (l3 - this.m.cz) * this.m.sin(this.m.zy));
            l3 = this.m.cz + (int)((this.y - this.m.y - this.m.cy) * this.m.sin(this.m.zy) + (l3 - this.m.cz) * this.m.cos(this.m.zy));
            ai[0] = this.xs((int)(j3 - j2 / 0.8 - this.m.random() * (j2 / 2.4)), l3);
            ai2[0] = this.ys((int)(i4 - l2 / 1.92 - this.m.random() * (l2 / 5.67)), l3);
            ai[1] = this.xs((int)(j3 - j2 / 0.8 - this.m.random() * (j2 / 2.4)), l3);
            ai2[1] = this.ys((int)(i4 + l2 / 1.92 + this.m.random() * (l2 / 5.67)), l3);
            ai[2] = this.xs((int)(j3 - j2 / 1.92 - this.m.random() * (j2 / 5.67)), l3);
            ai2[2] = this.ys((int)(i4 + l2 / 0.8 + this.m.random() * (l2 / 2.4)), l3);
            ai[3] = this.xs((int)(j3 + j2 / 1.92 + this.m.random() * (j2 / 5.67)), l3);
            ai2[3] = this.ys((int)(i4 + l2 / 0.8 + this.m.random() * (l2 / 2.4)), l3);
            ai[4] = this.xs((int)(j3 + j2 / 0.8 + this.m.random() * (j2 / 2.4)), l3);
            ai2[4] = this.ys((int)(i4 + l2 / 1.92 + this.m.random() * (l2 / 5.67)), l3);
            ai[5] = this.xs((int)(j3 + j2 / 0.8 + this.m.random() * (j2 / 2.4)), l3);
            ai2[5] = this.ys((int)(i4 - l2 / 1.92 - this.m.random() * (l2 / 5.67)), l3);
            ai[6] = this.xs((int)(j3 + j2 / 1.92 + this.m.random() * (j2 / 5.67)), l3);
            ai2[6] = this.ys((int)(i4 - l2 / 0.8 - this.m.random() * (l2 / 2.4)), l3);
            ai[7] = this.xs((int)(j3 - j2 / 1.92 - this.m.random() * (j2 / 5.67)), l3);
            ai2[7] = this.ys((int)(i4 - l2 / 0.8 - this.m.random() * (l2 / 2.4)), l3);
            if (this.fcnt == 3) {
                this.rot(ai, ai2, this.xs(j3, l3), this.ys(i4, l3), 22, 8);
            }
            if (this.fcnt == 4) {
                this.rot(ai, ai2, this.xs(j3, l3), this.ys(i4, l3), 22, 8);
            }
            if (this.fcnt == 5) {
                this.rot(ai, ai2, this.xs(j3, l3), this.ys(i4, l3), 0, 8);
            }
            if (this.fcnt == 6) {
                this.rot(ai, ai2, this.xs(j3, l3), this.ys(i4, l3), -22, 8);
            }
            if (this.fcnt == 7) {
                this.rot(ai, ai2, this.xs(j3, l3), this.ys(i4, l3), -22, 8);
            }
            int j4 = (int)(191.0f + 191.0f * (this.m.snap[0] / 350.0f));
            if (j4 > 255) {
                j4 = 255;
            }
            if (j4 < 0) {
                j4 = 0;
            }
            int k3 = (int)(232.0f + 232.0f * (this.m.snap[1] / 350.0f));
            if (k3 > 255) {
                k3 = 255;
            }
            if (k3 < 0) {
                k3 = 0;
            }
            int l4 = (int)(255.0f + 255.0f * (this.m.snap[2] / 350.0f));
            if (l4 > 255) {
                l4 = 255;
            }
            if (l4 < 0) {
                l4 = 0;
            }
            g.setColor(new Color(j4, k3, l4));
            g.fillPolygon(ai, ai2, 8);
            ai[0] = this.xs((int)(j3 - j2 - this.m.random() * (j2 / 4)), l3);
            ai2[0] = this.ys((int)(i4 - l2 / 2.4 - this.m.random() * (l2 / 9.6)), l3);
            ai[1] = this.xs((int)(j3 - j2 - this.m.random() * (j2 / 4)), l3);
            ai2[1] = this.ys((int)(i4 + l2 / 2.4 + this.m.random() * (l2 / 9.6)), l3);
            ai[2] = this.xs((int)(j3 - j2 / 2.4 - this.m.random() * (j2 / 9.6)), l3);
            ai2[2] = this.ys((int)(i4 + l2 + this.m.random() * (l2 / 4)), l3);
            ai[3] = this.xs((int)(j3 + j2 / 2.4 + this.m.random() * (j2 / 9.6)), l3);
            ai2[3] = this.ys((int)(i4 + l2 + this.m.random() * (l2 / 4)), l3);
            ai[4] = this.xs((int)(j3 + j2 + this.m.random() * (j2 / 4)), l3);
            ai2[4] = this.ys((int)(i4 + l2 / 2.4 + this.m.random() * (l2 / 9.6)), l3);
            ai[5] = this.xs((int)(j3 + j2 + this.m.random() * (j2 / 4)), l3);
            ai2[5] = this.ys((int)(i4 - l2 / 2.4 - this.m.random() * (l2 / 9.6)), l3);
            ai[6] = this.xs((int)(j3 + j2 / 2.4 + this.m.random() * (j2 / 9.6)), l3);
            ai2[6] = this.ys((int)(i4 - l2 - this.m.random() * (l2 / 4)), l3);
            ai[7] = this.xs((int)(j3 - j2 / 2.4 - this.m.random() * (j2 / 9.6)), l3);
            ai2[7] = this.ys((int)(i4 - l2 - this.m.random() * (l2 / 4)), l3);
            j4 = (int)(213.0f + 213.0f * (this.m.snap[0] / 350.0f));
            if (j4 > 255) {
                j4 = 255;
            }
            if (j4 < 0) {
                j4 = 0;
            }
            k3 = (int)(239.0f + 239.0f * (this.m.snap[1] / 350.0f));
            if (k3 > 255) {
                k3 = 255;
            }
            if (k3 < 0) {
                k3 = 0;
            }
            l4 = (int)(255.0f + 255.0f * (this.m.snap[2] / 350.0f));
            if (l4 > 255) {
                l4 = 255;
            }
            if (l4 < 0) {
                l4 = 0;
            }
            g.setColor(new Color(j4, k3, l4));
            g.fillPolygon(ai, ai2, 8);
        }
        if (this.fcnt > 7) {
            this.fcnt = 0;
            this.fix = false;
        }
        else {
            ++this.fcnt;
        }
    }
    
    public void setfire() {
        for (int i = 0; i < this.npl; ++i) {
            if (this.p[i].wz == 0 || this.p[i].gr == -17 || this.p[i].gr == -16) {
                this.p[i].embos = 16;
            }
        }
    }
    
    public void unsetfire() {
        for (int i = 0; i < this.npl; ++i) {
            if (this.p[i].wz == 0 || this.p[i].gr == -17 || this.p[i].gr == -16) {
                this.p[i].embos = 0;
            }
        }
    }
    
    public void drawsun(final Graphics2D g) {
        final int[][] sunx = new int[2][8];
        final int[][] suny = new int[2][8];
        final int[][] sunz = new int[2][8];
        sunx[0][0] = -500 - this.m.x;
        sunx[0][1] = 500 - this.m.x;
        sunx[0][2] = 800 - this.m.x;
        sunx[0][3] = 800 - this.m.x;
        sunx[0][4] = 500 - this.m.x;
        sunx[0][5] = -500 - this.m.x;
        sunx[0][6] = -800 - this.m.x;
        sunx[0][7] = -800 - this.m.x;
        suny[0][0] = -1000 - this.m.y;
        suny[0][1] = -1000 - this.m.y;
        suny[0][2] = -1300 - this.m.y;
        suny[0][3] = -2300 - this.m.y;
        suny[0][4] = -2600 - this.m.y;
        suny[0][5] = -2600 - this.m.y;
        suny[0][6] = -2300 - this.m.y;
        suny[0][7] = -1300 - this.m.y;
        for (int a = 0; a < 8; ++a) {
            sunz[0][a] = 20000 - this.m.z;
        }
        for (int c = 0; c < 2; ++c) {
            if (this.roted) {
                this.rot(sunx[c], sunz[c], this.x - this.m.x, this.z - this.m.z, 90, 8);
            }
            this.rot(sunx[c], sunz[c], this.m.cx, this.m.cz, this.m.xz, 8);
            this.rot(suny[c], sunz[c], this.m.cy, this.m.cz, this.m.zy, 8);
            final int[] ai3 = new int[8];
            final int[] ai4 = new int[8];
            for (int k2 = 0; k2 < 8; ++k2) {
                ai3[k2] = this.xs(sunx[c][k2], sunz[c][k2]);
                ai4[k2] = this.ys(suny[c][k2], sunz[c][k2]);
            }
            g.setColor(new Color(230, 230, 0));
            g.fillPolygon(ai3, ai4, 8);
        }
    }
    
    public void drawsnow(final Graphics2D g, int i, final int height) {
        if (this.m.trk) {
            i = 20;
        }
        final int ndrops = i;
        final int[] snowxloc = new int[ndrops];
        final int[] snowzloc = new int[ndrops];
        final int[][][] snowx = new int[ndrops][2][8];
        final int[][][] snowy = new int[ndrops][2][8];
        final int[][][] snowz = new int[ndrops][2][8];
        final int[] godown = new int[ndrops];
        final int[] ypos = new int[ndrops];
        for (int a = 0; a < ndrops; ++a) {
            if (a < ndrops / 3) {
                snowxloc[a] = (int)(Math.random() * ((this.m.wallside[0] - this.m.wallside[1]) / 3)) + this.m.wallside[1];
            }
            if (a >= ndrops / 3 && a < ndrops * 2 / 3) {
                snowxloc[a] = (int)(Math.random() * ((this.m.wallside[0] - this.m.wallside[1]) / 3)) + this.m.wallside[1] + (this.m.wallside[0] - this.m.wallside[1]) / 3;
            }
            if (a >= ndrops * 2 / 3 && a < ndrops) {
                snowxloc[a] = (int)(Math.random() * ((this.m.wallside[0] - this.m.wallside[1]) / 3)) + this.m.wallside[1] + (this.m.wallside[0] - this.m.wallside[1]) * 2 / 3;
            }
            snowzloc[a] = (int)(Math.random() * (this.m.wallside[2] - this.m.wallside[3])) + this.m.wallside[3];
            godown[a] = (int)(Math.random() * (height + 250));
            ypos[a] = -height + godown[a];
            for (int e = 0; e < 2; ++e) {
                if (e % 2 == 0) {
                    snowx[a][e][0] = snowxloc[a] - this.m.x - 25;
                    snowx[a][e][1] = snowxloc[a] - this.m.x + 25;
                    snowx[a][e][2] = snowxloc[a] - this.m.x + 40;
                    snowx[a][e][3] = snowxloc[a] - this.m.x + 40;
                    snowx[a][e][4] = snowxloc[a] - this.m.x + 25;
                    snowx[a][e][5] = snowxloc[a] - this.m.x - 25;
                    snowx[a][e][6] = snowxloc[a] - this.m.x - 40;
                    snowx[a][e][7] = snowxloc[a] - this.m.x - 40;
                    for (int b = 0; b < 8; ++b) {
                        snowz[a][e][b] = snowzloc[a] - this.m.z;
                    }
                }
                else {
                    snowz[a][e][0] = snowzloc[a] - this.m.z - 25;
                    snowz[a][e][1] = snowzloc[a] - this.m.z + 25;
                    snowz[a][e][2] = snowzloc[a] - this.m.z + 40;
                    snowz[a][e][3] = snowzloc[a] - this.m.z + 40;
                    snowz[a][e][4] = snowzloc[a] - this.m.z + 25;
                    snowz[a][e][5] = snowzloc[a] - this.m.z - 25;
                    snowz[a][e][6] = snowzloc[a] - this.m.z - 40;
                    snowz[a][e][7] = snowzloc[a] - this.m.z - 40;
                    for (int b = 0; b < 8; ++b) {
                        snowx[a][e][b] = snowxloc[a] - this.m.x;
                    }
                }
                snowy[a][e][0] = ypos[a] - this.m.y - 25;
                snowy[a][e][1] = ypos[a] - this.m.y - 25;
                snowy[a][e][2] = ypos[a] - this.m.y - 10;
                snowy[a][e][3] = ypos[a] - this.m.y + 40;
                snowy[a][e][4] = ypos[a] - this.m.y + 55;
                snowy[a][e][5] = ypos[a] - this.m.y + 55;
                snowy[a][e][6] = ypos[a] - this.m.y + 40;
                snowy[a][e][7] = ypos[a] - this.m.y - 10;
                if (this.roted) {
                    this.rot(snowx[a][e], snowz[a][e], this.x - this.m.x, this.z - this.m.z, 90, 8);
                }
                this.rot(snowx[a][e], snowz[a][e], this.m.cx, this.m.cz, this.m.xz, 8);
                this.rot(snowy[a][e], snowz[a][e], this.m.cy, this.m.cz, this.m.zy, 8);
                final int[] ai3 = new int[8];
                final int[] ai4 = new int[8];
                int j8 = 0;
                int l8 = 0;
                int j9 = 0;
                int l9 = 0;
                for (int k2 = 0; k2 < 8; ++k2) {
                    ai3[k2] = this.xs(snowx[a][e][k2], snowz[a][e][k2]);
                    ai4[k2] = this.ys(snowy[a][e][k2], snowz[a][e][k2]);
                    if (ai4[k2] < 0 || snowz[a][e][k2] < 10) {
                        ++j8;
                    }
                    if (ai4[k2] > this.m.h || snowz[a][e][k2] < 10) {
                        ++l8;
                    }
                    if (ai3[k2] < 0 || snowz[a][e][k2] < 10) {
                        ++j9;
                    }
                    if (ai3[k2] > this.m.w || snowz[a][e][k2] < 10) {
                        ++l9;
                    }
                }
                boolean dontshow = false;
                if (j8 != 0 || l8 != 0 || j9 != 0 || l9 != 0) {
                    dontshow = true;
                }
                final int[] color = new int[3];
                for (int d = 0; d < 3; ++d) {
                    if (this.m.csky[d] + 20 <= 255) {
                        color[d] = this.m.csky[d] + 20;
                    }
                    else {
                        color[d] = 255;
                    }
                }
                g.setColor(new Color(color[0], color[1], color[2]));
                if (!dontshow) {
                    g.fillPolygon(ai3, ai4, 8);
                }
            }
        }
    }
    
    public void electrify(final Graphics2D g) {
        int i = 0;
        do {
            if (this.elc[i] == 0) {
                this.edl[i] = (int)(380.0f - this.m.random() * 760.0f);
                this.edr[i] = (int)(380.0f - this.m.random() * 760.0f);
                this.elc[i] = 1;
            }
            final int j = (int)(this.edl[i] + (190.0f - this.m.random() * 380.0f));
            final int k = (int)(this.edr[i] + (190.0f - this.m.random() * 380.0f));
            final int l = (int)(this.m.random() * 126.0f);
            final int i2 = (int)(this.m.random() * 126.0f);
            final int[] ai = new int[8];
            final int[] ai2 = new int[8];
            final int[] ai3 = new int[8];
            int j2 = 0;
            do {
                ai3[j2] = this.z - this.m.z;
            } while (++j2 < 8);
            ai[0] = this.x - this.m.x - 504;
            ai2[0] = this.y - this.m.y - this.edl[i] - 5 - (int)(this.m.random() * 5.0f);
            ai[1] = this.x - this.m.x - 252 + i2;
            ai2[1] = this.y - this.m.y - j - 5 - (int)(this.m.random() * 5.0f);
            ai[2] = this.x - this.m.x + 252 - l;
            ai2[2] = this.y - this.m.y - k - 5 - (int)(this.m.random() * 5.0f);
            ai[3] = this.x - this.m.x + 504;
            ai2[3] = this.y - this.m.y - this.edr[i] - 5 - (int)(this.m.random() * 5.0f);
            ai[4] = this.x - this.m.x + 504;
            ai2[4] = this.y - this.m.y - this.edr[i] + 5 + (int)(this.m.random() * 5.0f);
            ai[5] = this.x - this.m.x + 252 - l;
            ai2[5] = this.y - this.m.y - k + 5 + (int)(this.m.random() * 5.0f);
            ai[6] = this.x - this.m.x - 252 + i2;
            ai2[6] = this.y - this.m.y - j + 5 + (int)(this.m.random() * 5.0f);
            ai[7] = this.x - this.m.x - 504;
            ai2[7] = this.y - this.m.y - this.edl[i] + 5 + (int)(this.m.random() * 5.0f);
            if (this.roted) {
                this.rot(ai, ai3, this.x - this.m.x, this.z - this.m.z, 90, 8);
            }
            this.rot(ai, ai3, this.m.cx, this.m.cz, this.m.xz, 8);
            this.rot(ai2, ai3, this.m.cy, this.m.cz, this.m.zy, 8);
            boolean flag = true;
            int k2 = 0;
            int l2 = 0;
            int i3 = 0;
            int j3 = 0;
            final int[] ai4 = new int[8];
            final int[] ai5 = new int[8];
            int k3 = 0;
            do {
                ai4[k3] = this.xs(ai[k3], ai3[k3]);
                ai5[k3] = this.ys(ai2[k3], ai3[k3]);
                if (ai5[k3] < 0 || ai3[k3] < 10) {
                    ++k2;
                }
                if (ai5[k3] > this.m.h || ai3[k3] < 10) {
                    ++l2;
                }
                if (ai4[k3] < 0 || ai3[k3] < 10) {
                    ++i3;
                }
                if (ai4[k3] > this.m.w || ai3[k3] < 10) {
                    ++j3;
                }
            } while (++k3 < 8);
            if (i3 == 8 || k2 == 8 || l2 == 8 || j3 == 8) {
                flag = false;
            }
            if (flag) {
                int l3 = (int)(160.0f + 160.0f * (this.m.snap[0] / 500.0f));
                if (l3 > 255) {
                    l3 = 255;
                }
                if (l3 < 0) {
                    l3 = 0;
                }
                int j4 = (int)(238.0f + 238.0f * (this.m.snap[1] / 500.0f));
                if (j4 > 255) {
                    j4 = 255;
                }
                if (j4 < 0) {
                    j4 = 0;
                }
                int l4 = (int)(255.0f + 255.0f * (this.m.snap[2] / 500.0f));
                if (l4 > 255) {
                    l4 = 255;
                }
                if (l4 < 0) {
                    l4 = 0;
                }
                l3 = (l3 * 2 + 214 * (this.elc[i] - 1)) / (this.elc[i] + 1);
                j4 = (j4 * 2 + 236 * (this.elc[i] - 1)) / (this.elc[i] + 1);
                if (this.m.trk) {
                    l3 = 191;
                    j4 = 232;
                    l4 = 255;
                }
                g.setColor(new Color(l3, j4, l4));
                g.fillPolygon(ai4, ai5, 8);
                if (ai3[0] < 4000) {
                    int i4 = (int)(150.0f + 150.0f * (this.m.snap[0] / 500.0f));
                    if (i4 > 255) {
                        i4 = 255;
                    }
                    if (i4 < 0) {
                        i4 = 0;
                    }
                    int k4 = (int)(227.0f + 227.0f * (this.m.snap[1] / 500.0f));
                    if (k4 > 255) {
                        k4 = 255;
                    }
                    if (k4 < 0) {
                        k4 = 0;
                    }
                    int i5 = (int)(255.0f + 255.0f * (this.m.snap[2] / 500.0f));
                    if (i5 > 255) {
                        i5 = 255;
                    }
                    if (i5 < 0) {
                        i5 = 0;
                    }
                    g.setColor(new Color(i4, k4, i5));
                    g.drawPolygon(ai4, ai5, 8);
                }
            }
            if (this.elc[i] > this.m.random() * 60.0f) {
                this.elc[i] = 0;
            }
            else {
                final int[] elc = this.elc;
                final int n = i;
                ++elc[n];
            }
        } while (++i < 4);
        if (!this.roted) {
            this.xy += 11;
        }
        else {
            this.zy += 11;
        }
    }
    
    public int py(final int i, final int j, final int k, final int l) {
        return (i - j) * (i - j) + (k - l) * (k - l);
    }
}
