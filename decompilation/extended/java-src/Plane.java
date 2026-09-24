import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.6.0
// 

public class Plane
{
    Medium m;
    Trackers t;
    int[] ox;
    int[] oy;
    int[] oz;
    int n;
    int[] c;
    int[] oc;
    float[] hsb;
    int glass;
    int gr;
    int fs;
    int disline;
    boolean road;
    int light;
    int master;
    int wx;
    int wz;
    int wy;
    float deltaf;
    float projf;
    int av;
    int bfase;
    boolean nocol;
    int chip;
    float ctmag;
    int cxz;
    int cxy;
    int czy;
    int[] cox;
    int[] coz;
    int[] coy;
    int dx;
    int dy;
    int dz;
    int vx;
    int vy;
    int vz;
    int embos;
    int typ;
    int pa;
    int pb;
    int flx;
    boolean solo;
    
    public void loadprojf() {
        this.projf = 1.0f;
        int i = 0;
        do {
            int j = 0;
            do {
                if (j != i) {
                    this.projf *= (float)(Math.sqrt((this.ox[i] - this.ox[j]) * (this.ox[i] - this.ox[j]) + (this.oz[i] - this.oz[j]) * (this.oz[i] - this.oz[j])) / 100.0);
                }
            } while (++j < 3);
        } while (++i < 3);
        this.projf /= 3.0f;
    }
    
    public int ys(final int i, int j) {
        if (j < this.m.cz) {
            j = this.m.cz;
        }
        return (j - this.m.focus_point) * (this.m.cy - i) / j + i;
    }
    
    public void recolour() {
        if (this.glass == 0) {
            for (int a = 0; a < 3; ++a) {
                this.c[a] = (int)(this.oc[a] + this.oc[a] * (this.m.snap[a] / 100.0f));
                if (this.c[a] > 255) {
                    this.c[a] = 255;
                }
                if (this.c[a] < 0) {
                    this.c[a] = 0;
                }
            }
        }
        if (this.glass == 1) {
            for (int a = 0; a < 3; ++a) {
                this.c[a] = (this.m.csky[a] * this.m.fade[0] * 2 + this.m.cfade[a] * 3000) / (this.m.fade[0] * 2 + 3000);
            }
        }
        if (this.glass == 2) {
            for (int a = 0; a < 3; ++a) {
                this.c[a] = (int)(this.m.cgrnd[a] * 0.925);
            }
        }
        if (this.glass == 3) {
            for (int a = 0; a < 3; ++a) {
                this.c[a] = (this.m.cgrnd[a] + this.m.cpol[a]) / 2;
            }
        }
        if (this.oc[0] == this.oc[1] && this.oc[1] == this.oc[2]) {
            this.nocol = true;
        }
        Color.RGBtoHSB(this.c[0], this.c[1], this.c[2], this.hsb);
        if (!this.nocol && this.glass == 0) {
            if (this.bfase > 20 && this.hsb[1] > 0.25) {
                this.hsb[1] = 0.25f;
            }
            if (this.bfase > 25 && this.hsb[2] > 0.7) {
                this.hsb[2] = 0.7f;
            }
            if (this.bfase > 30 && this.hsb[1] > 0.15) {
                this.hsb[1] = 0.15f;
            }
            if (this.bfase > 35 && this.hsb[2] > 0.6) {
                this.hsb[2] = 0.6f;
            }
            if (this.bfase > 40) {
                this.hsb[0] = 0.075f;
            }
            if (this.bfase > 50 && this.hsb[2] > 0.5) {
                this.hsb[2] = 0.5f;
            }
            if (this.bfase > 60) {
                this.hsb[0] = 0.05f;
            }
        }
    }
    
    public Plane(final Medium medium, final Trackers trackers, final int[] ai, final int[] ai1, final int[] ai2, final int i, final int[] ai3, final int shad, final int j, final int k, final int l, final int i1, final int j1, final int k1, final int l1, final boolean flag1, final int i2, final boolean bool2) {
        this.c = new int[3];
        this.oc = new int[3];
        this.hsb = new float[3];
        this.glass = 0;
        this.gr = 0;
        this.fs = 0;
        this.disline = 7;
        this.road = false;
        this.light = 0;
        this.master = 0;
        this.wx = 0;
        this.wz = 0;
        this.wy = 0;
        this.deltaf = 1.0f;
        this.projf = 1.0f;
        this.av = 0;
        this.bfase = 0;
        this.nocol = false;
        this.chip = 0;
        this.ctmag = 0.0f;
        this.cxz = 0;
        this.cxy = 0;
        this.czy = 0;
        this.cox = new int[3];
        this.coz = new int[3];
        this.coy = new int[3];
        this.dx = 0;
        this.dy = 0;
        this.dz = 0;
        this.vx = 0;
        this.vy = 0;
        this.vz = 0;
        this.embos = 0;
        this.typ = 0;
        this.pa = 0;
        this.pb = 0;
        this.flx = 0;
        this.m = medium;
        this.t = trackers;
        this.n = i;
        this.ox = new int[this.n];
        this.oz = new int[this.n];
        this.oy = new int[this.n];
        this.solo = bool2;
        for (int j2 = 0; j2 < this.n; ++j2) {
            this.ox[j2] = ai[j2];
            this.oy[j2] = ai2[j2];
            this.oz[j2] = ai1[j2];
        }
        final int k2 = Math.abs(this.ox[2] - this.ox[1]);
        final int l2 = Math.abs(this.oy[2] - this.oy[1]);
        final int i3 = Math.abs(this.oz[2] - this.oz[1]);
        if (l2 <= k2 && l2 <= i3) {
            this.typ = 2;
        }
        if (k2 <= l2 && k2 <= i3) {
            this.typ = 1;
        }
        if (i3 <= k2 && i3 <= l2) {
            this.typ = 3;
        }
        int j3 = 0;
        do {
            this.oc[j3] = ai3[j3];
        } while (++j3 < 3);
        if (j == -15) {
            j3 = (int)(185.0 + Math.random() * 30.0);
            ai3[0] = (217 + j3) / 2;
            ai3[1] = (189 + j3) / 2;
            ai3[2] = (132 + j3) / 2;
            for (int k3 = 0; k3 < this.n; ++k3) {
                if (Math.random() > Math.random()) {
                    final int[] ox = this.ox;
                    final int n = k3;
                    ox[n] += (int)(8.0 * Math.random() - 4.0);
                }
                if (Math.random() > Math.random()) {
                    final int[] oy = this.oy;
                    final int n2 = k3;
                    oy[n2] += (int)(8.0 * Math.random() - 4.0);
                }
                if (Math.random() > Math.random()) {
                    final int[] oz = this.oz;
                    final int n3 = k3;
                    oz[n3] += (int)(8.0 * Math.random() - 4.0);
                }
            }
        }
        if (ai3[0] == ai3[1] && ai3[1] == ai3[2]) {
            this.nocol = true;
        }
        if (shad == 0) {
            j3 = 0;
            do {
                this.c[j3] = (int)(ai3[j3] + ai3[j3] * (this.m.snap[j3] / 100.0f));
                if (this.c[j3] > 255) {
                    this.c[j3] = 255;
                }
                if (this.c[j3] < 0) {
                    this.c[j3] = 0;
                }
            } while (++j3 < 3);
        }
        if (shad == 1) {
            j3 = 0;
            do {
                this.c[j3] = (this.m.csky[j3] * this.m.fade[0] * 2 + this.m.cfade[j3] * 3000) / (this.m.fade[0] * 2 + 3000);
            } while (++j3 < 3);
        }
        if (shad == 2) {
            j3 = 0;
            do {
                this.c[j3] = (int)(this.m.cgrnd[j3] * 0.925);
            } while (++j3 < 3);
        }
        if (shad == 3) {
            for (int ig = 0; ig < 3; ++ig) {
                this.c[ig] = ai3[ig];
            }
        }
        this.disline = k1;
        this.bfase = l1;
        this.glass = shad;
        Color.RGBtoHSB(this.c[0], this.c[1], this.c[2], this.hsb);
        if (!this.nocol && this.glass == 0) {
            if (this.bfase > 20 && this.hsb[1] > 0.25) {
                this.hsb[1] = 0.25f;
            }
            if (this.bfase > 25 && this.hsb[2] > 0.7) {
                this.hsb[2] = 0.7f;
            }
            if (this.bfase > 30 && this.hsb[1] > 0.15) {
                this.hsb[1] = 0.15f;
            }
            if (this.bfase > 35 && this.hsb[2] > 0.6) {
                this.hsb[2] = 0.6f;
            }
            if (this.bfase > 40) {
                this.hsb[0] = 0.075f;
            }
            if (this.bfase > 50 && this.hsb[2] > 0.5) {
                this.hsb[2] = 0.5f;
            }
            if (this.bfase > 60) {
                this.hsb[0] = 0.05f;
            }
        }
        this.road = flag1;
        this.light = i2;
        this.gr = j;
        this.fs = k;
        this.wx = l;
        this.wy = i1;
        this.wz = j1;
        j3 = 0;
        do {
            int l3 = 0;
            do {
                if (l3 != j3) {
                    this.deltaf *= (float)(Math.sqrt((this.ox[l3] - this.ox[j3]) * (this.ox[l3] - this.ox[j3]) + (this.oy[l3] - this.oy[j3]) * (this.oy[l3] - this.oy[j3]) + (this.oz[l3] - this.oz[j3]) * (this.oz[l3] - this.oz[j3])) / 100.0);
                }
            } while (++l3 < 3);
        } while (++j3 < 3);
        this.deltaf /= 3.0f;
    }
    
    public void d(final Graphics g, final int i, final int j, final int k, final int l, final int i1, final int j1, final int k1, final int l1, boolean flag, final int i2, final int weakstage, final boolean shadowcar, final boolean greenflame, final int flameheight, final int invisiblepiece, final boolean glowlines, final int[] glowcolour, final boolean spatk, final boolean freeze, final boolean weaken, final boolean leech, final boolean strswap, final int sred, final int sgreen, final int sblue, final int groundlevel, final boolean playerglow, final boolean teleported, final int telefade, final boolean floorguardian, final int[] dmgcolours, final boolean isacar, final boolean lightup, final boolean outoftrack) {
        if (this.master != 0) {
            if (this.av > 1500) {
                this.n = 8;
            }
            else {
                this.n = 16;
            }
        }
        final int[] ai = new int[this.n];
        final int[] ai2 = new int[this.n];
        final int[] ai3 = new int[this.n];
        if (this.embos == 0) {
            for (int j2 = 0; j2 < this.n; ++j2) {
                ai[j2] = this.ox[j2] + i;
                ai3[j2] = this.oy[j2] + j;
                ai2[j2] = this.oz[j2] + k;
            }
            if ((this.gr == -11 || this.gr == -13) && this.m.lastmaf == 1) {
                for (int k2 = 0; k2 < this.n; ++k2) {
                    ai[k2] = -this.ox[k2] + i;
                    ai3[k2] = this.oy[k2] + j;
                    ai2[k2] = -this.oz[k2] + k;
                }
            }
        }
        else {
            if (this.embos <= 11 && this.m.random() > 0.5 && this.glass == 0) {
                for (int l2 = 0; l2 < this.n; ++l2) {
                    ai[l2] = (int)(this.ox[l2] + i + (15.0f - this.m.random() * 30.0f));
                    ai3[l2] = (int)(this.oy[l2] + j + (15.0f - this.m.random() * 30.0f));
                    ai2[l2] = (int)(this.oz[l2] + k + (15.0f - this.m.random() * 30.0f));
                }
                this.rot(ai, ai3, i, j, i1, this.n);
                this.rot(ai3, ai2, j, k, j1, this.n);
                this.rot(ai, ai2, i, k, l, this.n);
                this.rot(ai, ai2, this.m.cx, this.m.cz, this.m.xz, this.n);
                this.rot(ai3, ai2, this.m.cy, this.m.cz, this.m.zy, this.n);
                final int[] ai4 = new int[this.n];
                final int[] ai5 = new int[this.n];
                for (int i3 = 0; i3 < this.n; ++i3) {
                    ai4[i3] = this.xs(ai[i3], ai2[i3]);
                    ai5[i3] = this.ys(ai3[i3], ai2[i3]);
                }
                g.setColor(new Color(230, 230, 230));
                g.fillPolygon(ai4, ai5, this.n);
            }
            float f = 1.0f;
            if (this.embos <= 4) {
                f = 1.0f + this.m.random() / 5.0f;
            }
            if (this.embos > 4 && this.embos <= 7) {
                f = 1.0f + this.m.random() / 4.0f;
            }
            if (this.embos > 7 && this.embos <= 9) {
                f = 1.0f + this.m.random() / 3.0f;
                if (this.hsb[2] > 0.7) {
                    this.hsb[2] = 0.7f;
                }
            }
            if (this.embos > 9 && this.embos <= 10) {
                f = 1.0f + this.m.random() / 2.0f;
                if (this.hsb[2] > 0.6) {
                    this.hsb[2] = 0.6f;
                }
            }
            if (this.embos > 10 && this.embos <= 12) {
                f = 1.0f + this.m.random() / 1.0f;
                if (this.hsb[2] > 0.5) {
                    this.hsb[2] = 0.5f;
                }
            }
            if (this.embos == 12) {
                this.chip = 1;
                this.ctmag = 2.0f;
                this.bfase = -7;
            }
            if (this.embos == 13) {
                this.hsb[1] = 0.2f;
                this.hsb[2] = 0.4f;
            }
            if (this.embos == 16) {
                this.pa = (int)(this.m.random() * this.n);
                this.pb = (int)(this.m.random() * this.n);
                while (this.pa == this.pb) {
                    this.pb = (int)(this.m.random() * this.n);
                }
            }
            if (this.embos >= 16) {
                byte byte0 = 1;
                byte byte2 = 1;
                int j3;
                for (j3 = Math.abs(j1); j3 > 270; j3 -= 360) {}
                j3 = Math.abs(j3);
                if (j3 > 90) {
                    byte0 = -1;
                }
                int i4;
                for (i4 = Math.abs(i1); i4 > 270; i4 -= 360) {}
                i4 = Math.abs(i4);
                if (i4 > 90) {
                    byte2 = -1;
                }
                final int[] ai6 = new int[3];
                final int[] ai7 = new int[3];
                ai[0] = this.ox[this.pa] + i;
                ai3[0] = this.oy[this.pa] + j;
                ai2[0] = this.oz[this.pa] + k;
                ai[1] = this.ox[this.pb] + i;
                ai3[1] = this.oy[this.pb] + j;
                double actualflame = flameheight;
                if (this.m.effect[6]) {
                    actualflame = flameheight / 100.0;
                }
                if (floorguardian) {
                    actualflame = 1.5;
                }
                ai2[1] = this.oz[this.pb] + k;
                while (Math.abs(ai[0] - ai[1]) > (int)(100.0 * actualflame)) {
                    if (ai[1] > ai[0]) {
                        final int[] array = ai;
                        final int n = 1;
                        array[n] -= (int)(30.0 * actualflame);
                    }
                    else {
                        final int[] array2 = ai;
                        final int n2 = 1;
                        array2[n2] += (int)(30.0 * actualflame);
                    }
                }
                while (Math.abs(ai2[0] - ai2[1]) > (int)(100.0 * actualflame)) {
                    if (ai2[1] > ai2[0]) {
                        final int[] array3 = ai2;
                        final int n3 = 1;
                        array3[n3] -= (int)(30.0 * actualflame);
                    }
                    else {
                        final int[] array4 = ai2;
                        final int n4 = 1;
                        array4[n4] += (int)(30.0 * actualflame);
                    }
                }
                final int i5 = (int)(Math.abs(ai[0] - ai[1]) / 3 * (0.5 - this.m.random()));
                final int l3 = (int)(Math.abs(ai2[0] - ai2[1]) / 3 * (0.5 - this.m.random()));
                ai[2] = (ai[0] + ai[1]) / 2 + (int)(i5 * actualflame);
                ai2[2] = (ai2[0] + ai2[1]) / 2 + (int)(l3 * actualflame);
                int i6 = (int)((Math.abs(ai[0] - ai[1]) + Math.abs(ai2[0] - ai2[1])) / 1.5 * (this.m.random() / 2.0f + 0.5));
                ai3[2] = (ai3[0] + ai3[1]) / 2 - (int)(byte0 * byte2 * i6 * actualflame);
                this.rot(ai, ai3, i, j, i1, 3);
                this.rot(ai3, ai2, j, k, j1, 3);
                this.rot(ai, ai2, i, k, l, 3);
                this.rot(ai, ai2, this.m.cx, this.m.cz, this.m.xz, 3);
                this.rot(ai3, ai2, this.m.cy, this.m.cz, this.m.zy, 3);
                int k3 = 0;
                do {
                    ai6[k3] = this.xs(ai[k3], ai2[k3]);
                    ai7[k3] = this.ys(ai3[k3], ai2[k3]);
                } while (++k3 < 3);
                final float[] flamecolours = { 255.0f, 169.0f, 89.0f };
                if (greenflame) {
                    flamecolours[0] = 50.0f;
                    flamecolours[1] = 180.0f;
                    flamecolours[2] = 255.0f;
                }
                if (floorguardian) {
                    flamecolours[0] = (float)dmgcolours[0];
                    flamecolours[1] = (float)dmgcolours[1];
                    flamecolours[2] = (float)dmgcolours[2];
                }
                k3 = (int)(flamecolours[0] + flamecolours[0] * (this.m.snap[0] / 400.0f));
                if (k3 > 255) {
                    k3 = 255;
                }
                if (k3 < 0) {
                    k3 = 0;
                }
                int i7 = 0;
                i7 = (int)(flamecolours[1] + flamecolours[1] * (this.m.snap[1] / 300.0f));
                if (i7 > 255) {
                    i7 = 255;
                }
                if (i7 < 0) {
                    i7 = 0;
                }
                int k4 = 0;
                k4 = (int)(flamecolours[2] + flamecolours[2] * (this.m.snap[2] / 200.0f));
                if (k4 > 255) {
                    k4 = 255;
                }
                if (k4 < 0) {
                    k4 = 0;
                }
                g.setColor(new Color(k3, i7, k4));
                g.fillPolygon(ai6, ai7, 3);
                ai[0] = this.ox[this.pa] + i;
                ai3[0] = this.oy[this.pa] + j;
                ai2[0] = this.oz[this.pa] + k;
                ai[1] = this.ox[this.pb] + i;
                ai3[1] = this.oy[this.pb] + j;
                ai2[1] = this.oz[this.pb] + k;
                while (Math.abs(ai[0] - ai[1]) > (int)(100.0 * actualflame)) {
                    if (ai[1] > ai[0]) {
                        final int[] array5 = ai;
                        final int n5 = 1;
                        array5[n5] -= (int)(30.0 * actualflame);
                    }
                    else {
                        final int[] array6 = ai;
                        final int n6 = 1;
                        array6[n6] += (int)(30.0 * actualflame);
                    }
                }
                while (Math.abs(ai2[0] - ai2[1]) > (int)(100.0 * actualflame)) {
                    if (ai2[1] > ai2[0]) {
                        final int[] array7 = ai2;
                        final int n7 = 1;
                        array7[n7] -= (int)(30.0 * actualflame);
                    }
                    else {
                        final int[] array8 = ai2;
                        final int n8 = 1;
                        array8[n8] += (int)(30.0 * actualflame);
                    }
                }
                ai[2] = (ai[0] + ai[1]) / 2 + (int)(i5 * actualflame);
                ai2[2] = (ai2[0] + ai2[1]) / 2 + (int)(l3 * actualflame);
                i6 *= 0.8;  // cast: bytecode-verified
                ai3[2] = (ai3[0] + ai3[1]) / 2 - (int)(byte0 * byte2 * i6 * actualflame);
                this.rot(ai, ai3, i, j, i1, 3);
                this.rot(ai3, ai2, j, k, j1, 3);
                this.rot(ai, ai2, i, k, l, 3);
                this.rot(ai, ai2, this.m.cx, this.m.cz, this.m.xz, 3);
                this.rot(ai3, ai2, this.m.cy, this.m.cz, this.m.zy, 3);
                int i8 = 0;
                do {
                    ai6[i8] = this.xs(ai[i8], ai2[i8]);
                    ai7[i8] = this.ys(ai3[i8], ai2[i8]);
                } while (++i8 < 3);
                k3 = (int)(flamecolours[0] + flamecolours[0] * (this.m.snap[0] / 400.0f));
                if (k3 > 255) {
                    k3 = 255;
                }
                if (k3 < 0) {
                    k3 = 0;
                }
                i7 = (int)(flamecolours[1] + flamecolours[1] * (this.m.snap[1] / 300.0f));
                if (i7 > 255) {
                    i7 = 255;
                }
                if (i7 < 0) {
                    i7 = 0;
                }
                k4 = (int)(flamecolours[2] + flamecolours[2] * (this.m.snap[2] / 200.0f));
                if (k4 > 255) {
                    k4 = 255;
                }
                if (k4 < 0) {
                    k4 = 0;
                }
                g.setColor(new Color(k3, i7, k4));
                g.fillPolygon(ai6, ai7, 3);
            }
            for (int k5 = 0; k5 < this.n; ++k5) {
                if (this.typ == 1) {
                    ai[k5] = (int)(this.ox[k5] * f + i);
                }
                else {
                    ai[k5] = this.ox[k5] + i;
                }
                if (this.typ == 2) {
                    ai3[k5] = (int)(this.oy[k5] * f + j);
                }
                else {
                    ai3[k5] = this.oy[k5] + j;
                }
                if (this.typ == 3) {
                    ai2[k5] = (int)(this.oz[k5] * f + k);
                }
                else {
                    ai2[k5] = this.oz[k5] + k;
                }
            }
            if (this.embos != 70) {
                ++this.embos;
            }
            else {
                this.embos = 16;
            }
        }
        if (this.wz != 0) {
            this.rot(ai3, ai2, this.wy + j, this.wz + k, l1, this.n);
        }
        if (this.wx != 0) {
            this.rot(ai, ai2, this.wx + i, this.wz + k, k1, this.n);
        }
        if (this.chip == 1 && (this.m.random() > 0.6 || this.bfase == 0)) {
            this.chip = 0;
            if (this.bfase == 0 && this.nocol) {
                this.bfase = 1;
            }
        }
        if (this.chip != 0) {
            if (this.chip == 1) {
                this.cxz = l;
                this.cxy = i1;
                this.czy = j1;
                final int i9 = (int)(this.m.random() * this.n);
                this.cox[0] = this.ox[i9];
                this.coz[0] = this.oz[i9];
                this.coy[0] = this.oy[i9];
                if (this.ctmag > 3.0f) {
                    this.ctmag = 3.0f;
                }
                if (this.ctmag < -3.0f) {
                    this.ctmag = -3.0f;
                }
                this.cox[1] = (int)(this.cox[0] + this.ctmag * (10.0f - this.m.random() * 20.0f));
                this.cox[2] = (int)(this.cox[0] + this.ctmag * (10.0f - this.m.random() * 20.0f));
                this.coy[1] = (int)(this.coy[0] + this.ctmag * (10.0f - this.m.random() * 20.0f));
                this.coy[2] = (int)(this.coy[0] + this.ctmag * (10.0f - this.m.random() * 20.0f));
                this.coz[1] = (int)(this.coz[0] + this.ctmag * (10.0f - this.m.random() * 20.0f));
                this.coz[2] = (int)(this.coz[0] + this.ctmag * (10.0f - this.m.random() * 20.0f));
                this.dx = 0;
                this.dy = 0;
                this.dz = 0;
                if (this.bfase != -7) {
                    this.vx = (int)(this.ctmag * (30.0f - this.m.random() * 60.0f));
                    this.vz = (int)(this.ctmag * (30.0f - this.m.random() * 60.0f));
                    this.vy = (int)(this.ctmag * (30.0f - this.m.random() * 60.0f));
                }
                else {
                    this.vx = (int)(this.ctmag * (10.0f - this.m.random() * 20.0f));
                    this.vz = (int)(this.ctmag * (10.0f - this.m.random() * 20.0f));
                    this.vy = (int)(this.ctmag * (10.0f - this.m.random() * 20.0f));
                }
                this.chip = 2;
            }
            final int[] ai8 = new int[3];
            final int[] ai9 = new int[3];
            final int[] ai10 = new int[3];
            int k6 = 0;
            do {
                ai8[k6] = this.cox[k6] + i;
                ai10[k6] = this.coy[k6] + j;
                ai9[k6] = this.coz[k6] + k;
            } while (++k6 < 3);
            this.rot(ai8, ai10, i, j, this.cxy, 3);
            this.rot(ai10, ai9, j, k, this.czy, 3);
            this.rot(ai8, ai9, i, k, this.cxz, 3);
            k6 = 0;
            do {
                final int[] array9 = ai8;
                final int n9 = k6;
                array9[n9] += this.dx;
                final int[] array10 = ai10;
                final int n10 = k6;
                array10[n10] += this.dy;
                final int[] array11 = ai9;
                final int n11 = k6;
                array11[n11] += this.dz;
            } while (++k6 < 3);
            this.dx += this.vx;
            this.dz += this.vz;
            this.dy += this.vy;
            this.vy += 7;
            if (ai10[0] > groundlevel) {
                this.chip = 19;
            }
            this.rot(ai8, ai9, this.m.cx, this.m.cz, this.m.xz, 3);
            this.rot(ai10, ai9, this.m.cy, this.m.cz, this.m.zy, 3);
            final int[] ai11 = new int[3];
            final int[] ai12 = new int[3];
            int l4 = 0;
            do {
                ai11[l4] = this.xs(ai8[l4], ai9[l4]);
                ai12[l4] = this.ys(ai10[l4], ai9[l4]);
                if (ai12[l4] < 45 && this.m.flex != 0) {
                    this.m.flex = 0;
                }
            } while (++l4 < 3);
            if (this.bfase != -7) {
                if (l4 == 0) {
                    g.setColor(new Color(this.c[0], this.c[1], this.c[2]).darker());
                }
                if (l4 == 1) {
                    g.setColor(new Color(this.c[0], this.c[1], this.c[2]));
                }
                if (l4 == 2) {
                    g.setColor(new Color(this.c[0], this.c[1], this.c[2]).brighter());
                }
            }
            else {
                g.setColor(Color.getHSBColor(this.hsb[0], this.hsb[1], this.hsb[2]));
            }
            g.fillPolygon(ai11, ai12, 3);
            l4 = (int)(this.m.random() * 3.0f);
            ++this.chip;
            if (this.chip == 20) {
                this.chip = 0;
            }
        }
        this.rot(ai, ai3, i, j, i1, this.n);
        this.rot(ai3, ai2, j, k, j1, this.n);
        this.rot(ai, ai2, i, k, l, this.n);
        if (i1 != 0 || j1 != 0 || l != 0) {
            this.projf = 1.0f;
            int j4 = 0;
            do {
                int l5 = 0;
                do {
                    if (l5 != j4) {
                        this.projf *= (float)(Math.sqrt((ai[j4] - ai[l5]) * (ai[j4] - ai[l5]) + (ai2[j4] - ai2[l5]) * (ai2[j4] - ai2[l5])) / 100.0);
                    }
                } while (++l5 < 3);
            } while (++j4 < 3);
            this.projf /= 3.0f;
        }
        this.rot(ai, ai2, this.m.cx, this.m.cz, this.m.xz, this.n);
        boolean flag2 = false;
        final int[] ai13 = new int[this.n];
        final int[] ai14 = new int[this.n];
        int l6 = 500;
        for (int j5 = 0; j5 < this.n; ++j5) {
            ai13[j5] = this.xs(ai[j5], ai2[j5]);
            ai14[j5] = this.ys(ai3[j5], ai2[j5]);
        }
        int k7 = 0;
        int i10 = 1;
        for (int j6 = 0; j6 < this.n; ++j6) {
            for (int j7 = 0; j7 < this.n; ++j7) {
                if (j6 != j7 && Math.abs(ai13[j6] - ai13[j7]) - Math.abs(ai14[j6] - ai14[j7]) < l6) {
                    i10 = j6;
                    k7 = j7;
                    l6 = Math.abs(ai13[j6] - ai13[j7]) - Math.abs(ai14[j6] - ai14[j7]);
                }
            }
        }
        if (ai14[k7] < ai14[i10]) {
            final int k8 = k7;
            k7 = i10;
            i10 = k8;
        }
        if (this.spy(ai[k7], ai2[k7]) > this.spy(ai[i10], ai2[i10])) {
            flag2 = true;
            int l7 = 0;
            for (int k9 = 0; k9 < this.n; ++k9) {
                if (ai2[k9] < 50 && ai3[k9] > this.m.cy) {
                    flag2 = false;
                }
                else if (ai3[k9] == ai3[0]) {
                    ++l7;
                }
            }
            if (l7 == this.n && ai3[0] > this.m.cy) {
                flag2 = false;
            }
        }
        this.rot(ai3, ai2, this.m.cy, this.m.cz, this.m.zy, this.n);
        boolean flag3 = true;
        final int[] ai15 = new int[this.n];
        final int[] ai16 = new int[this.n];
        int j8 = 0;
        int l8 = 0;
        int j9 = 0;
        int l9 = 0;
        int j10 = 0;
        for (int k10 = 0; k10 < this.n; ++k10) {
            ai15[k10] = this.xs(ai[k10], ai2[k10]);
            ai16[k10] = this.ys(ai3[k10], ai2[k10]);
            if (ai16[k10] < 0 || ai2[k10] < 10) {
                ++j8;
            }
            if (ai16[k10] > this.m.h || ai2[k10] < 10) {
                ++l8;
            }
            if (ai15[k10] < 0 || ai2[k10] < 10) {
                ++j9;
            }
            if (ai15[k10] > this.m.w || ai2[k10] < 10) {
                ++l9;
            }
            if (ai16[k10] < 45 && this.m.flex != 0) {
                this.m.flex = 0;
            }
            if (ai2[k10] < 10) {
                ++j10;
            }
        }
        if (i2 != -1) {
            int l10 = 0;
            int j11 = 0;
            for (int k11 = 0; k11 < this.n; ++k11) {
                for (int l11 = k11; l11 < this.n; ++l11) {
                    if (k11 != l11) {
                        if (Math.abs(ai15[k11] - ai15[l11]) > l10) {
                            l10 = Math.abs(ai15[k11] - ai15[l11]);
                        }
                        if (Math.abs(ai16[k11] - ai16[l11]) > j11) {
                            j11 = Math.abs(ai16[k11] - ai16[l11]);
                        }
                    }
                }
            }
            if (l10 == 0 || j11 == 0) {
                flag3 = false;
            }
            else if (l10 < 3 && j11 < 3 && i2 / l10 > 15 && i2 / j11 > 15) {
                flag3 = false;
            }
        }
        if (j9 == this.n || j8 == this.n || l8 == this.n || l9 == this.n) {
            flag3 = false;
        }
        if (this.m.trk && (j9 != 0 || j8 != 0 || l8 != 0 || l9 != 0)) {
            flag3 = false;
        }
        if (j10 != 0) {
            flag = true;
        }
        if (flag3) {
            int i11 = 1;
            byte byte3 = 1;
            byte byte4 = 1;
            if (Math.abs(ai16[0] - ai16[1]) > Math.abs(ai16[2] - ai16[1])) {
                byte3 = 0;
                byte4 = 2;
            }
            else {
                byte3 = 2;
                byte4 = 0;
                i11 *= -1;
            }
            if (ai16[1] > ai16[byte3]) {
                i11 *= -1;
            }
            if (ai15[1] > ai15[byte4]) {
                i11 *= -1;
            }
            int i12 = this.gr;
            if (i12 < 0 && i12 >= -17) {
                i12 = 0;
            }
            if (this.gr == -11) {
                i12 = -90;
            }
            if (this.gr == -14 || this.gr == -15) {
                i12 = -50;
            }
            if (this.gr == -16) {
                i12 = 35;
            }
            if (this.fs != 0) {
                i11 *= this.fs;
                if (i11 == -1) {
                    i12 += 40;
                    if (!this.road) {
                        i11 = -111;
                    }
                }
            }
            if (this.m.lightson && this.light == 2) {
                i12 -= 40;
            }
            int j12 = 0;
            int k12 = 0;
            int k13 = 0;
            int l12 = 0;
            int i13 = 0;
            int j13 = 0;
            for (int k14 = 0; k14 < this.n; ++k14) {
                int i14 = 0;
                int k15 = 0;
                int i15 = 0;
                int j14 = 0;
                int k16 = 0;
                int l13 = 0;
                for (int i16 = 0; i16 < this.n; ++i16) {
                    if (ai3[k14] >= ai3[i16]) {
                        ++i14;
                    }
                    if (ai3[k14] <= ai3[i16]) {
                        ++k15;
                    }
                    if (ai[k14] >= ai[i16]) {
                        ++i15;
                    }
                    if (ai[k14] <= ai[i16]) {
                        ++j14;
                    }
                    if (ai2[k14] >= ai2[i16]) {
                        ++k16;
                    }
                    if (ai2[k14] <= ai2[i16]) {
                        ++l13;
                    }
                }
                if (i14 == this.n) {
                    j12 = ai3[k14];
                }
                if (k15 == this.n) {
                    k12 = ai3[k14];
                }
                if (i15 == this.n) {
                    k13 = ai[k14];
                }
                if (j14 == this.n) {
                    l12 = ai[k14];
                }
                if (k16 == this.n) {
                    i13 = ai2[k14];
                }
                if (l13 == this.n) {
                    j13 = ai2[k14];
                }
            }
            final int l14 = (j12 + k12) / 2;
            final int j15 = (k13 + l12) / 2;
            final int l15 = (i13 + j13) / 2;
            this.av = (int)Math.sqrt((this.m.cy - l14) * (this.m.cy - l14) + (this.m.cx - j15) * (this.m.cx - j15) + l15 * l15 + i12 * i12 * i12);
            if ((this.av > 100000 || this.av == 0) && !this.m.trk) {
                flag3 = false;
            }
            if (i11 == -111 && this.av > 4500) {
                flag3 = false;
            }
            if (i11 == -111 && this.av > 1500) {
                flag = true;
            }
            if (this.av > 3000 && this.m.adv <= 900) {
                boolean speciallines = false;
                if (spatk || freeze || leech || weaken || strswap || playerglow) {
                    speciallines = true;
                }
                if (!this.m.effect[5] && !speciallines && !this.m.effect[11] && !this.m.effect[10]) {
                    flag = true;
                }
            }
            if (this.gr == -12 && this.av < 11200) {
                this.m.lastmaf = i11;
            }
            if (this.gr == -13 && (!this.m.lastcheck || i2 != -1)) {
                flag3 = false;
            }
            if (this.gr == -16 && this.av > 1500) {
                flag3 = false;
            }
            if (this.flx != 0 && this.m.random() > 0.3) {
                flag3 = false;
            }
        }
        if (flag3) {
            this.sortpieces(g, flag, flag2, i2, weakstage, shadowcar, invisiblepiece, ai15, ai16, glowlines, glowcolour, spatk, freeze, weaken, leech, strswap, sred, sgreen, sblue, playerglow, teleported, telefade, floorguardian, dmgcolours, isacar, lightup, outoftrack);
        }
    }
    
    public void sortpieces(final Graphics g, boolean flag, final boolean flag1, final int i2, final int weakstage, final boolean shadowcar, final int invisiblepiece, final int[] ai14, final int[] ai15, boolean glowlines, final int[] glowcolour, final boolean spatk, final boolean freeze, final boolean weaken, final boolean leech, final boolean strswap, final int sred, final int sgreen, final int sblue, final boolean playerglow, final boolean teleported, final int telefade, final boolean floorguardian, final int[] dmgcolours, final boolean isacar, final boolean lightup, final boolean outoftrack) {
        float f1 = (float)(this.projf / this.deltaf + 0.3);
        if (flag && !this.solo) {
            boolean flag2 = false;
            if (f1 > 1.0f) {
                if (f1 >= 1.27) {
                    flag2 = true;
                }
                f1 = 1.0f;
            }
            if (flag2) {
                f1 *= 0.89;  // cast: bytecode-verified
            }
            else {
                f1 *= 0.86;  // cast: bytecode-verified
            }
            if (f1 < 0.37) {
                f1 = 0.37f;
            }
            if (this.gr == -9) {
                f1 = 0.7f;
            }
            if (this.gr == -4) {
                f1 = 0.74f;
            }
            if (this.gr != -7 && flag1) {
                f1 = 0.32f;
            }
            if (this.gr == -8 || this.gr == -14 || this.gr == -15) {
                f1 = 1.0f;
            }
            if (this.gr == -11) {
                f1 = 0.67f;
                if (i2 == -1) {
                    if (this.m.cpflik || (this.m.nochekflk && !this.m.lastcheck)) {
                        f1 = 1.0f;
                    }
                    else {
                        f1 = 0.76f;
                    }
                }
            }
            if (this.gr == -13 && i2 == -1) {
                if (this.m.cpflik) {
                    f1 = 0.0f;
                }
                else {
                    f1 = 0.76f;
                }
            }
            if (this.gr == -6) {
                f1 = 0.62f;
            }
            if (this.gr == -5) {
                f1 = 0.55f;
            }
        }
        else {
            if (f1 > 1.0f) {
                f1 = 1.0f;
            }
            if (f1 < 0.6 || flag1) {
                f1 = 0.6f;
            }
        }
        final Color color = Color.getHSBColor(this.hsb[0], this.hsb[1], this.hsb[2] * f1);
        int l11 = color.getRed();
        int j13 = color.getGreen();
        int k14 = color.getBlue();
        if (this.m.lightson) {
            if (this.light == 2) {
                l11 = 210;
                j13 = 0;
                k14 = 0;
            }
            if (this.light == 1) {
                l11 = 210;
                j13 = 210;
                k14 = 210;
            }
        }
        if (!this.m.trk) {
            int l12 = 0;
            do {
                if (this.av > this.m.fade[l12]) {
                    l11 = (l11 * this.m.fogd + this.m.cfade[0]) / (this.m.fogd + 1);
                    j13 = (j13 * this.m.fogd + this.m.cfade[1]) / (this.m.fogd + 1);
                    k14 = (k14 * this.m.fogd + this.m.cfade[2]) / (this.m.fogd + 1);
                }
            } while (++l12 < 16);
        }
        int redcol = l11;
        int greencol = j13;
        int bluecol = k14;
        if (weakstage > 0) {
            if (this.m.effect[6]) {
                redcol = l11 + (int)(l11 * (weakstage / 100.0f));
            }
            if (this.m.effect[7]) {
                greencol = j13 + (int)(j13 * (weakstage / 100.0f));
            }
        }
        if (redcol > 255) {
            redcol = 255;
        }
        if (greencol > 255) {
            greencol = 255;
        }
        if (bluecol > 255) {
            bluecol = 255;
        }
        int shadow = 255;
        if (shadowcar && this.light != 1 && this.light != 2) {
            shadow = this.m.shadowtrans;
        }
        if (floorguardian) {
            shadow = 200;
        }
        g.setColor(new Color(redcol, greencol, bluecol, shadow));
        if (teleported) {
            g.setColor(new Color(redcol, greencol, bluecol, telefade));
        }
        if (invisiblepiece != 255 && !this.m.trk) {
            g.setColor(new Color(l11, j13, bluecol, invisiblepiece));
        }
        if (this.m.effect[10] && !isacar) {
            int trans = 20;
            if (this.m.trk || lightup) {
                trans = 50;
            }
            g.setColor(new Color(glowcolour[0], glowcolour[1], glowcolour[2], trans));
        }
        g.fillPolygon(ai14, ai15, this.n);
        if (this.m.trk && this.gr == -10) {
            flag = false;
        }
        boolean speciallines = false;
        if (spatk || freeze || strswap || weaken || leech || playerglow || this.m.effect[11]) {
            speciallines = true;
        }
        if (this.m.effect[10] && !isacar) {
            speciallines = false;
            glowlines = false;
            if (lightup) {
                speciallines = true;
                glowlines = true;
            }
        }
        if (!flag && ((!shadowcar && !floorguardian) || speciallines) && !teleported && invisiblepiece == 255) {
            if (this.flx == 0) {
                if (!this.solo) {
                    l11 = 0;
                    j13 = 0;
                    k14 = 0;
                    if (this.m.lightson) {
                        if (this.light == 2) {
                            l11 = 100;
                        }
                        if (this.light == 1) {
                            l11 = 100;
                            j13 = 100;
                            k14 = 100;
                        }
                    }
                    g.setColor(new Color(l11, j13, k14));
                    int trans2 = 255;
                    if (shadowcar || floorguardian) {
                        trans2 = 100;
                    }
                    if (this.m.effect[5] || speciallines || playerglow || this.m.effect[11]) {
                        if (trans2 == 255) {
                            g.setColor(new Color(sred, sgreen, sblue));
                        }
                        else {
                            g.setColor(new Color(sred, sgreen, sblue, trans2));
                        }
                    }
                    if (this.m.effect[10] && lightup) {
                        g.setColor(new Color(glowcolour[0], glowcolour[1], glowcolour[2]));
                    }
                    g.drawPolygon(ai14, ai15, this.n);
                    g.setColor(new Color(l11, j13, k14));
                }
            }
            else {
                if (this.flx == 2) {
                    g.setColor(new Color(0, 0, 0));
                    g.drawPolygon(ai14, ai15, this.n);
                }
                if (this.flx == 1) {
                    l11 = 0;
                    j13 = (int)(223.0f + 223.0f * (this.m.snap[1] / 100.0f));
                    if (j13 > 255) {
                        j13 = 255;
                    }
                    if (j13 < 0) {
                        j13 = 0;
                    }
                    k14 = (int)(255.0f + 255.0f * (this.m.snap[2] / 100.0f));
                    if (k14 > 255) {
                        k14 = 255;
                    }
                    if (k14 < 0) {
                        k14 = 0;
                    }
                    g.setColor(new Color(l11, j13, k14));
                    g.drawPolygon(ai14, ai15, this.n);
                    this.flx = 2;
                }
                if (this.flx == 3) {
                    l11 = 0;
                    j13 = (int)(255.0f + 255.0f * (this.m.snap[1] / 100.0f));
                    if (j13 > 255) {
                        j13 = 255;
                    }
                    if (j13 < 0) {
                        j13 = 0;
                    }
                    k14 = (int)(223.0f + 223.0f * (this.m.snap[2] / 100.0f));
                    if (k14 > 255) {
                        k14 = 255;
                    }
                    if (k14 < 0) {
                        k14 = 0;
                    }
                    g.setColor(new Color(l11, j13, k14));
                    g.drawPolygon(ai14, ai15, this.n);
                    this.flx = 2;
                }
            }
        }
        else if (((this.road && this.av <= 3000 && !this.m.trk && this.m.fade[0] > 4000 && !this.m.effect[10]) || glowlines) && (invisiblepiece == 255 || this.m.effect[9])) {
            final int[] colours = new int[3];
            for (int a = 0; a < 3; ++a) {
                if (glowlines) {
                    colours[a] = glowcolour[a];
                }
                else {
                    colours[a] = 0;
                }
            }
            l11 -= 10;
            if (l11 < colours[0]) {
                l11 = colours[0];
            }
            j13 -= 10;
            if (j13 < colours[1]) {
                j13 = colours[1];
            }
            k14 -= 10;
            if (k14 < colours[2]) {
                k14 = colours[2];
            }
            g.setColor(new Color(l11, j13, k14));
            if (this.m.effect[10] && lightup) {
                g.setColor(new Color(l11, j13, k14, 50));
            }
            g.drawPolygon(ai14, ai15, this.n);
        }
        if (this.gr == -10 && !shadowcar && !floorguardian && !teleported && invisiblepiece == 255 && !this.m.effect[10]) {
            if (!this.m.trk) {
                int i3 = this.c[0];
                int k15 = this.c[1];
                int l13 = this.c[2];
                if (i2 == -1) {
                    if (this.m.nochekflk && !this.m.lastcheck) {
                        i3 *= 1.25;  // cast: bytecode-verified
                        if (i3 > 255) {
                            i3 = 255;
                        }
                        k15 *= 1.25;  // cast: bytecode-verified
                        if (k15 > 255) {
                            k15 = 255;
                        }
                        l13 *= 1.25;  // cast: bytecode-verified
                        if (l13 > 255) {
                            l13 = 255;
                        }
                    }
                    else if (this.m.cpflik) {
                        i3 *= 1.5;  // cast: bytecode-verified
                        if (i3 > 255) {
                            i3 = 255;
                        }
                        k15 *= 1.5;  // cast: bytecode-verified
                        if (k15 > 255) {
                            k15 = 255;
                        }
                        l13 *= 1.5;  // cast: bytecode-verified
                        if (l13 > 255) {
                            l13 = 255;
                        }
                    }
                }
                int i4 = 0;
                do {
                    if (this.av > this.m.fade[i4]) {
                        i3 = (i3 * this.m.fogd + this.m.cfade[0]) / (this.m.fogd + 1);
                        k15 = (k15 * this.m.fogd + this.m.cfade[1]) / (this.m.fogd + 1);
                        l13 = (l13 * this.m.fogd + this.m.cfade[2]) / (this.m.fogd + 1);
                    }
                } while (++i4 < 16);
                g.setColor(new Color(i3, k15, l13));
                g.drawPolygon(ai14, ai15, this.n);
            }
            else if (this.m.cpflik && this.m.hit == 5000) {
                int l14 = (int)(Math.random() * 115.0);
                int j14 = l14 * 2 - 54;
                if (j14 < 0) {
                    j14 = 0;
                }
                if (j14 > 255) {
                    j14 = 255;
                }
                int i5 = 202 + l14 * 2;
                if (i5 < 0) {
                    i5 = 0;
                }
                if (i5 > 255) {
                    i5 = 255;
                }
                l14 += 101;
                if (l14 < 0) {
                    l14 = 0;
                }
                if (l14 > 255) {
                    l14 = 255;
                }
                g.setColor(new Color(j14, l14, i5));
                g.drawPolygon(ai14, ai15, this.n);
            }
        }
        if (this.gr == -18 && !this.m.trk && !shadowcar && !floorguardian && !teleported && invisiblepiece == 255 && !this.m.effect[10]) {
            int k16 = this.c[0];
            int i6 = this.c[1];
            int j15 = this.c[2];
            if (this.m.cpflik && this.m.elecr >= 0.0f) {
                k16 = (int)(25.5f * this.m.elecr);
                if (k16 > 255) {
                    k16 = 255;
                }
                i6 = (int)(128.0f + 12.8f * this.m.elecr);
                if (i6 > 255) {
                    i6 = 255;
                }
                j15 = 255;
            }
            int j16 = 0;
            do {
                if (this.av > this.m.fade[j16]) {
                    k16 = (k16 * this.m.fogd + this.m.cfade[0]) / (this.m.fogd + 1);
                    i6 = (i6 * this.m.fogd + this.m.cfade[1]) / (this.m.fogd + 1);
                    j15 = (j15 * this.m.fogd + this.m.cfade[2]) / (this.m.fogd + 1);
                }
            } while (++j16 < 16);
            g.setColor(new Color(k16, i6, j15));
            g.drawPolygon(ai14, ai15, this.n);
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
    
    public int xs(final int i, int j) {
        if (j < this.m.cz) {
            j = this.m.cz;
        }
        return (j - this.m.focus_point) * (this.m.cx - i) / j + i;
    }
    
    public void s(final Graphics g, final int i, final int j, final int k, final int l, final int i1, final int j1, final int k1, final int groundlevel, final boolean teleported, final int telefade, final int[] shadcol, final boolean outoftrack) {
        final int[] ai = new int[this.n];
        final int[] ai2 = new int[this.n];
        final int[] ai3 = new int[this.n];
        for (int l2 = 0; l2 < this.n; ++l2) {
            ai[l2] = this.ox[l2] + i;
            ai3[l2] = this.oy[l2] + j;
            ai2[l2] = this.oz[l2] + k;
        }
        this.rot(ai, ai3, i, j, i1, this.n);
        this.rot(ai3, ai2, j, k, j1, this.n);
        this.rot(ai, ai2, i, k, l, this.n);
        final int[] coladj = { this.m.cgrnd[0], this.m.cgrnd[1], this.m.cgrnd[2] };
        int i2 = (int)((float)coladj[0] / 1.5);
        int j2 = (int)((float)coladj[1] / 1.5);
        int k2 = (int)((float)coladj[2] / 1.5);
        for (int l3 = 0; l3 < this.n; ++l3) {
            ai3[l3] = groundlevel;
        }
        if (k1 == 0) {
            int i3 = 0;
            int j3 = 0;
            int k3 = 0;
            int l4 = 0;
            for (int l5 = 0; l5 < this.n; ++l5) {
                int l6 = 0;
                int k4 = 0;
                int j4 = 0;
                int i4 = 0;
                for (int k5 = 0; k5 < this.n; ++k5) {
                    if (ai[l5] >= ai[k5]) {
                        ++l6;
                    }
                    if (ai[l5] <= ai[k5]) {
                        ++k4;
                    }
                    if (ai2[l5] >= ai2[k5]) {
                        ++j4;
                    }
                    if (ai2[l5] <= ai2[k5]) {
                        ++i4;
                    }
                }
                if (l6 == this.n) {
                    i3 = ai[l5];
                }
                if (k4 == this.n) {
                    j3 = ai[l5];
                }
                if (j4 == this.n) {
                    k3 = ai2[l5];
                }
                if (i4 == this.n) {
                    l4 = ai2[l5];
                }
            }
            final int i5 = (i3 + j3) / 2;
            final int i6 = (k3 + l4) / 2;
            for (int l7 = this.t.nt - 1; l7 >= 0; --l7) {
                if (this.t.y[l7] == groundlevel || !this.m.effect[9]) {
                    if (this.t.y[l7] <= groundlevel || this.m.effect[9]) {
                        int k6 = 0;
                        if (Math.abs(this.t.zy[l7]) != 90 && Math.abs(this.t.xy[l7]) != 90 && Math.abs(i5 - (this.t.x[l7] - this.m.x)) < this.t.radx[l7] && Math.abs(i6 - (this.t.z[l7] - this.m.z)) < this.t.radz[l7]) {
                            ++k6;
                        }
                        if (k6 != 0) {
                            for (int j5 = 0; j5 < this.n; ++j5) {
                                ai3[j5] = this.t.y[l7] - this.m.y;
                                if (this.t.zy[l7] != 0) {
                                    final int[] array = ai3;
                                    final int n = j5;
                                    array[n] += ((ai2[j5] - (this.t.z[l7] - this.m.z - this.t.radz[l7])) * this.m.sin(this.t.zy[l7]) / this.m.sin(90 - this.t.zy[l7]) - this.t.radz[l7] * this.m.sin(this.t.zy[l7]) / this.m.sin(90 - this.t.zy[l7]));  // cast: bytecode-verified
                                }
                                if (this.t.xy[l7] != 0) {
                                    final int[] array2 = ai3;
                                    final int n2 = j5;
                                    array2[n2] += ((ai[j5] - (this.t.x[l7] - this.m.x - this.t.radx[l7])) * this.m.sin(this.t.xy[l7]) / this.m.sin(90 - this.t.xy[l7]) - this.t.radx[l7] * this.m.sin(this.t.xy[l7]) / this.m.sin(90 - this.t.xy[l7]));  // cast: bytecode-verified
                                }
                            }
                            int red = this.t.c[l7][0];
                            int green = this.t.c[l7][1];
                            int blue = this.t.c[l7][2];
                            if ((this.m.switchfase == 0 || this.m.switchfase == 10 || this.m.switchfase == 20) && this.m.effect[2] && !this.m.trk) {
                                red = (int)(this.t.oc[l7][0] + this.t.oc[l7][0] * (this.m.snap[0] / 100.0f));
                                green = (int)(this.t.oc[l7][1] + this.t.oc[l7][1] * (this.m.snap[1] / 100.0f));
                                blue = (int)(this.t.oc[l7][2] + this.t.oc[l7][2] * (this.m.snap[2] / 100.0f));
                            }
                            i2 = (int)((float)red / 1.5);
                            j2 = (int)((float)green / 1.5);
                            k2 = (int)((float)blue / 1.5);
                            break;
                        }
                    }
                }
            }
        }
        boolean flag = true;
        final int[] ai4 = new int[this.n];
        final int[] ai5 = new int[this.n];
        if (k1 == 2) {
            i2 = 80;
            j2 = 80;
            k2 = 80;
        }
        else {
            for (int i7 = 0; i7 < this.m.nsp; ++i7) {
                for (int j6 = 0; j6 < this.n; ++j6) {
                    if (Math.abs(ai[j6] - this.m.spx[i7]) < this.m.sprad[i7] && Math.abs(ai2[j6] - this.m.spz[i7]) < this.m.sprad[i7]) {
                        flag = false;
                    }
                }
            }
        }
        if (flag) {
            this.rot(ai, ai2, this.m.cx, this.m.cz, this.m.xz, this.n);
            this.rot(ai3, ai2, this.m.cy, this.m.cz, this.m.zy, this.n);
            int j7 = 0;
            int k7 = 0;
            int j8 = 0;
            int i8 = 0;
            for (int l8 = 0; l8 < this.n; ++l8) {
                ai4[l8] = this.xs(ai[l8], ai2[l8]);
                ai5[l8] = this.ys(ai3[l8], ai2[l8]);
                if (ai5[l8] < 0 || ai2[l8] < 10) {
                    ++j7;
                }
                if (ai5[l8] > this.m.h || ai2[l8] < 10) {
                    ++k7;
                }
                if (ai4[l8] < 0 || ai2[l8] < 10) {
                    ++j8;
                }
                if (ai4[l8] > this.m.w || ai2[l8] < 10) {
                    ++i8;
                }
            }
            if (j8 == this.n || j7 == this.n || k7 == this.n || i8 == this.n) {
                flag = false;
            }
        }
        if (flag) {
            int k8 = 0;
            do {
                if (this.av > this.m.fade[k8]) {
                    i2 = (i2 * this.m.fogd + this.m.cfade[0]) / (this.m.fogd + 1);
                    j2 = (j2 * this.m.fogd + this.m.cfade[1]) / (this.m.fogd + 1);
                    k2 = (k2 * this.m.fogd + this.m.cfade[2]) / (this.m.fogd + 1);
                }
            } while (++k8 < 16);
            g.setColor(new Color(i2, j2, k2));
            if (teleported) {
                g.setColor(new Color(i2, j2, k2, telefade));
            }
            g.fillPolygon(ai4, ai5, this.n);
            if (this.m.effect[11] && outoftrack) {
                g.setColor(new Color(shadcol[0], shadcol[1], shadcol[2], 50));
                g.drawPolygon(ai4, ai5, this.n);
            }
        }
    }
    
    public int spy(final int i, final int j) {
        return (int)Math.sqrt((i - this.m.cx) * (i - this.m.cx) + j * j);
    }
}
