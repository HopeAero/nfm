import java.util.Arrays;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics2D;

// 
// Decompiled by Procyon v0.6.0
// 

public class Medium
{
    int focus_point;
    int ground;
    int skyline;
    int[] fade;
    int[] originalfade;
    int[] csky;
    int[] cgrnd;
    int[] cpol;
    int[] cfade;
    int[] snap;
    int[] osnap;
    int[] polyoutline;
    int[] osky;
    int[] cldd;
    int[] clds;
    int[] clx;
    int[] stx;
    float[][] pvr;
    int nst;
    int[] cgpx;
    float[] pcv;
    int[] cgpz;
    int[] pmx;
    int[] stz;
    int[][][] stc;
    boolean[] bst;
    int[] twn;
    int resdown;
    int rescnt;
    int mgen;
    int[] clz;
    int[] cmx;
    int[][][] clax;
    int[][][] clay;
    int[][][] claz;
    int[][][][] clc;
    int origfade;
    int noc;
    int nmt;
    int glassfade;
    int fogd;
    boolean lightson;
    int lightn;
    int lilo;
    int flex;
    int[] mrd;
    int[] nmv;
    int[][] mtx;
    int[][] mty;
    int[][] mtz;
    int[][][] mtc;
    int[] ogrnd;
    int[] crgrnd;
    boolean trk;
    boolean crs;
    int cx;
    int cy;
    boolean showsnow;
    boolean showwater;
    int cz;
    int xz;
    int zy;
    int x;
    int y;
    int z;
    int w;
    int h;
    boolean[] effect;
    int ih;
    int iw;
    int nsp;
    int[] spx;
    int[] spz;
    int[] sprad;
    boolean td;
    int bcxz;
    double bcxzwatch;
    boolean bt;
    int vxz;
    int adv;
    boolean vert;
    int trns;
    int dispolys;
    int[][] ogpx;
    int[][] ogpz;
    int sgpx;
    int sgpz;
    int nrw;
    int ncl;
    float[] tcos;
    float[] tsin;
    int lastmaf;
    int checkpoint;
    boolean lastcheck;
    float elecr;
    boolean cpflik;
    boolean nochekflk;
    int cntrn;
    boolean[] diup;
    int[] rand;
    int trn;
    int hit;
    int ptr;
    int ptcnt;
    int nrnd;
    long trx;
    long trz;
    long atrx;
    long atrz;
    int fallen;
    float fo;
    float gofo;
    boolean decrease;
    int switchfase;
    int glowfase;
    int makefase;
    int darklevel;
    boolean[] oneffect;
    int[] ocsky;
    int[] ocfade;
    int[] wallside;
    boolean groundcolour;
    boolean darken;
    int darkenfor;
    int[] skycolour;
    int[] fadecolour;
    int[] grndcolour;
    int[] polycolour;
    int effecttime;
    int snowno;
    boolean changingsnap;
    int shadowtrans;
    boolean verydark;
    int snowheight;
    boolean drowned;
    boolean icehills;
    double reducepolys;
    int greystage;
    int[] starfase;
    
    public float random() {
        if (this.cntrn == 0) {
            int i = 0;
            do {
                this.rand[i] = (int)(10.0 * Math.random());
                if (Math.random() > Math.random()) {
                    this.diup[i] = false;
                }
                else {
                    this.diup[i] = true;
                }
            } while (++i < 3);
            this.cntrn = 20;
        }
        else {
            --this.cntrn;
        }
        int j = 0;
        do {
            if (this.diup[j]) {
                final int[] rand = this.rand;
                final int n = j;
                ++rand[n];
                if (this.rand[j] != 10) {
                    continue;
                }
                this.rand[j] = 0;
            }
            else {
                final int[] rand2 = this.rand;
                final int n2 = j;
                --rand2[n2];
                if (this.rand[j] != -1) {
                    continue;
                }
                this.rand[j] = 9;
            }
        } while (++j < 3);
        ++this.trn;
        if (this.trn == 3) {
            this.trn = 0;
        }
        return this.rand[this.trn] / 10.0f;
    }
    
    public void groundpolys(final Graphics2D g) {
        double extra = 1.0;
        if (this.effect[4]) {
            extra = 2.0;
        }
        int i = (this.x - this.sgpx) / (int)(1200.0 * this.reducepolys) - (int)(7.0 * extra);
        if (i < 0) {
            i = 0;
        }
        int j = i + (int)(15.0 * extra);
        if (j > this.nrw) {
            j = this.nrw;
        }
        int k = (this.z - this.sgpz) / (int)(1200.0 * this.reducepolys) - (int)(7.0 * extra);
        if (k < 0) {
            k = 0;
        }
        int l = k + (int)(15.0 * extra);
        if (l > this.ncl) {
            l = this.ncl;
        }
        for (int i2 = i; i2 < j; ++i2) {
            for (int j2 = k; j2 < l; ++j2) {
                final int k2 = i2 + j2 * this.nrw;
                final int l2 = this.cx + (int)((this.cgpx[k2] - this.x - this.cx) * this.cos(this.xz) - (this.cgpz[k2] - this.z - this.cz) * this.sin(this.xz));
                final int i3 = this.cz + (int)((this.cgpx[k2] - this.x - this.cx) * this.sin(this.xz) + (this.cgpz[k2] - this.z - this.cz) * this.cos(this.xz));
                final int j3 = this.cz + (int)((this.ground - this.y - this.cy) * this.sin(this.zy) + (i3 - this.cz) * this.cos(this.zy));
                if ((this.xs(l2 + 700, j3) > 0 && this.xs(l2 - 700, j3) < this.w && j3 > -700 && j3 < (this.fade[0] + this.fade[1]) / 2) || this.effect[4]) {
                    final int[][] ai = new int[4][8];
                    final int[][] ai2 = new int[4][8];
                    final int[][] ai3 = new int[4][8];
                    int groundloc = this.ground;
                    if (this.effect[4]) {
                        groundloc = this.ground + 2500;
                    }
                    final int[] groundlevels = { groundloc, -10000, -20000, -30000 };
                    final int polylimit = 1;
                    final boolean b2 = this.effect[9];
                    for (int a = 0; a < polylimit; ++a) {
                        int k3 = 0;
                        do {
                            ai[a][k3] = this.ogpx[k2][k3] + this.cgpx[k2] - this.x;
                            ai2[a][k3] = this.ogpz[k2][k3] + this.cgpz[k2] - this.z;
                            ai3[a][k3] = groundlevels[a];
                        } while (++k3 < 8);
                        this.rot(ai[a], ai2[a], this.cx, this.cz, this.xz, 8);
                        this.rot(ai3[a], ai2[a], this.cy, this.cz, this.zy, 8);
                    }
                    final int[][] ai4 = new int[4][8];
                    final int[][] ai5 = new int[4][8];
                    int l3 = 0;
                    int i4 = 0;
                    int j4 = 0;
                    int k4 = 0;
                    boolean flag = true;
                    for (int a2 = 0; a2 < polylimit; ++a2) {
                        int l4 = 0;
                        do {
                            ai4[a2][l4] = this.xs(ai[a2][l4], ai2[a2][l4]);
                            ai5[a2][l4] = this.ys(ai3[a2][l4], ai2[a2][l4]);
                            if (ai5[a2][l4] < 0 || ai2[a2][l4] < 10) {
                                ++l3;
                            }
                            if (ai5[a2][l4] > this.h || ai2[a2][l4] < 10) {
                                ++i4;
                            }
                            if (ai4[a2][l4] < 0 || ai2[a2][l4] < 10) {
                                ++j4;
                            }
                            if (ai4[a2][l4] > this.w || ai2[a2][l4] < 10) {
                                ++k4;
                            }
                        } while (++l4 < 8);
                        if (j4 == 8 || l3 == 8 || i4 == 8 || k4 == 8) {
                            flag = false;
                        }
                    }
                    if (flag) {
                        for (int a2 = 0; a2 < polylimit; ++a2) {
                            int i5 = this.cpol[0];
                            int j5 = this.cpol[1];
                            int k5 = this.cpol[2];
                            if (j3 > this.fade[0]) {
                                i5 = (i5 * 3 + this.cfade[0]) / 4;
                                j5 = (j5 * 3 + this.cfade[1]) / 4;
                                k5 = (k5 * 3 + this.cfade[2]) / 4;
                            }
                            g.setColor(new Color(i5, j5, k5));
                            g.fillPolygon(ai4[a2], ai5[a2], 8);
                            boolean glowingpolys = false;
                            if (this.effect[0] && (this.polyoutline[0] > 0 || this.polyoutline[1] > 0 || this.polyoutline[2] > 0)) {
                                glowingpolys = true;
                            }
                            if (this.effect[5] && this.polyoutline[1] > 20) {
                                glowingpolys = true;
                            }
                            if (glowingpolys) {
                                for (int b = 0; b < 3; ++b) {
                                    if (this.polyoutline[b] > 255) {
                                        this.polyoutline[b] = 255;
                                    }
                                    if (this.polyoutline[b] < 0) {
                                        this.polyoutline[b] = 0;
                                    }
                                }
                                g.setColor(new Color(this.polyoutline[0], this.polyoutline[1], this.polyoutline[2]));
                                g.drawPolygon(ai4[a2], ai5[a2], 8);
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void setcloads(final int i, final int i_252_, final int i_253_, int i_254_, int i_255_) {
        if (i_254_ < 0) {
            i_254_ = 0;
        }
        if (i_254_ > 10) {
            i_254_ = 10;
        }
        if (i_255_ < -1500) {
            i_255_ = -1500;
        }
        if (i_255_ > -500) {
            i_255_ = -500;
        }
        this.cldd[0] = i;
        this.cldd[1] = i_252_;
        this.cldd[2] = i_253_;
        this.cldd[3] = i_254_;
        this.cldd[4] = i_255_;
        if (this.effect[9]) {
            this.cldd[4] = i_255_ * 5;
        }
        for (int i_256_ = 0; i_256_ < 3; ++i_256_) {
            this.clds[i_256_] = (this.osky[i_256_] * this.cldd[3] + this.cldd[i_256_]) / (this.cldd[3] + 1);
            this.clds[i_256_] += (this.clds[i_256_] * (this.snap[i_256_] / 100.0f));  // cast: bytecode-verified
            if (this.clds[i_256_] > 255) {
                this.clds[i_256_] = 255;
            }
            if (this.clds[i_256_] < 0) {
                this.clds[i_256_] = 0;
            }
        }
    }
    
    public void newclouds(int i, int i_88_, int i_89_, int i_90_, final int x, final xtGraphics xtgraphics) {
        this.clx = null;
        this.clz = null;
        this.cmx = null;
        this.clax = null;
        this.clay = null;
        this.claz = null;
        this.clc = null;
        i = i / 20 - 10000;
        i_88_ = i_88_ / 20 + 10000;
        i_89_ = i_89_ / 20 - 10000;
        i_90_ = i_90_ / 20 + 10000;
        this.noc = (i_88_ - i) * (i_90_ - i_89_) / 16666667;
        if (xtgraphics.careermode) {
            if (x == 11) {
                this.noc = (int)((i_88_ - i) * (i_90_ - i_89_) / 16666667 / 2.5);
            }
            if (this.effect[8] || this.effect[11] || x == 18 || x == 22 || x == 24 || x == 16) {
                this.noc = 0;
            }
        }
        this.clx = new int[this.noc];
        this.clz = new int[this.noc];
        this.cmx = new int[this.noc];
        this.clax = new int[this.noc][3][12];
        this.clay = new int[this.noc][3][12];
        this.claz = new int[this.noc][3][12];
        this.clc = new int[this.noc][2][6][3];
        for (int i_91_ = 0; i_91_ < this.noc; ++i_91_) {
            this.clx[i_91_] = (int)(i + (i_88_ - i) * Math.random());
            this.clz[i_91_] = (int)(i_89_ + (i_90_ - i_89_) * Math.random());
            final float f = (float)(0.25 + Math.random() * 1.25);
            float f_92_ = (float)((200.0 + Math.random() * 700.0) * f);
            if (xtgraphics.careermode) {}
            this.clax[i_91_][0][0] = (int)(f_92_ * 0.3826);
            this.claz[i_91_][0][0] = (int)(f_92_ * 0.9238);
            this.clay[i_91_][0][0] = (int)((25.0 - Math.random() * 50.0) * f);
            this.clax[i_91_][0][1] = (int)(f_92_ * 0.7071);
            this.claz[i_91_][0][1] = (int)(f_92_ * 0.7071);
            this.clay[i_91_][0][1] = (int)((25.0 - Math.random() * 50.0) * f);
            this.clax[i_91_][0][2] = (int)(f_92_ * 0.9238);
            this.claz[i_91_][0][2] = (int)(f_92_ * 0.3826);
            this.clay[i_91_][0][2] = (int)((25.0 - Math.random() * 50.0) * f);
            this.clax[i_91_][0][3] = (int)(f_92_ * 0.9238);
            this.claz[i_91_][0][3] = -(int)(f_92_ * 0.3826);
            this.clay[i_91_][0][3] = (int)((25.0 - Math.random() * 50.0) * f);
            this.clax[i_91_][0][4] = (int)(f_92_ * 0.7071);
            this.claz[i_91_][0][4] = -(int)(f_92_ * 0.7071);
            this.clay[i_91_][0][4] = (int)((25.0 - Math.random() * 50.0) * f);
            this.clax[i_91_][0][5] = (int)(f_92_ * 0.3826);
            this.claz[i_91_][0][5] = -(int)(f_92_ * 0.9238);
            this.clay[i_91_][0][5] = (int)((25.0 - Math.random() * 50.0) * f);
            this.clax[i_91_][0][6] = -(int)(f_92_ * 0.3826);
            this.claz[i_91_][0][6] = -(int)(f_92_ * 0.9238);
            this.clay[i_91_][0][6] = (int)((25.0 - Math.random() * 50.0) * f);
            this.clax[i_91_][0][7] = -(int)(f_92_ * 0.7071);
            this.claz[i_91_][0][7] = -(int)(f_92_ * 0.7071);
            this.clay[i_91_][0][7] = (int)((25.0 - Math.random() * 50.0) * f);
            this.clax[i_91_][0][8] = -(int)(f_92_ * 0.9238);
            this.claz[i_91_][0][8] = -(int)(f_92_ * 0.3826);
            this.clay[i_91_][0][8] = (int)((25.0 - Math.random() * 50.0) * f);
            this.clax[i_91_][0][9] = -(int)(f_92_ * 0.9238);
            this.claz[i_91_][0][9] = (int)(f_92_ * 0.3826);
            this.clay[i_91_][0][9] = (int)((25.0 - Math.random() * 50.0) * f);
            this.clax[i_91_][0][10] = -(int)(f_92_ * 0.7071);
            this.claz[i_91_][0][10] = (int)(f_92_ * 0.7071);
            this.clay[i_91_][0][10] = (int)((25.0 - Math.random() * 50.0) * f);
            this.clax[i_91_][0][11] = -(int)(f_92_ * 0.3826);
            this.claz[i_91_][0][11] = (int)(f_92_ * 0.9238);
            this.clay[i_91_][0][11] = (int)((25.0 - Math.random() * 50.0) * f);
            for (int i_93_ = 0; i_93_ < 12; ++i_93_) {
                int i_94_ = i_93_ - 1;
                if (i_94_ == -1) {
                    i_94_ = 11;
                }
                int i_95_ = i_93_ + 1;
                if (i_95_ == 12) {
                    i_95_ = 0;
                }
                this.clax[i_91_][0][i_93_] = ((this.clax[i_91_][0][i_94_] + this.clax[i_91_][0][i_95_]) / 2 + this.clax[i_91_][0][i_93_]) / 2;
                this.clay[i_91_][0][i_93_] = ((this.clay[i_91_][0][i_94_] + this.clay[i_91_][0][i_95_]) / 2 + this.clay[i_91_][0][i_93_]) / 2;
                this.claz[i_91_][0][i_93_] = ((this.claz[i_91_][0][i_94_] + this.claz[i_91_][0][i_95_]) / 2 + this.claz[i_91_][0][i_93_]) / 2;
            }
            for (int i_96_ = 0; i_96_ < 12; ++i_96_) {
                f_92_ = (float)(1.2 + 0.6 * Math.random());
                this.clax[i_91_][1][i_96_] = (int)(this.clax[i_91_][0][i_96_] * f_92_);
                this.claz[i_91_][1][i_96_] = (int)(this.claz[i_91_][0][i_96_] * f_92_);
                this.clay[i_91_][1][i_96_] = (int)(this.clay[i_91_][0][i_96_] - 100.0 * Math.random());
                f_92_ = (float)(1.1 + 0.3 * Math.random());
                this.clax[i_91_][2][i_96_] = (int)(this.clax[i_91_][1][i_96_] * f_92_);
                this.claz[i_91_][2][i_96_] = (int)(this.claz[i_91_][1][i_96_] * f_92_);
                this.clay[i_91_][2][i_96_] = (int)(this.clay[i_91_][1][i_96_] - 240.0 * Math.random());
            }
            this.cmx[i_91_] = 0;
            for (int i_97_ = 0; i_97_ < 12; ++i_97_) {
                int i_98_ = i_97_ - 1;
                if (i_98_ == -1) {
                    i_98_ = 11;
                }
                int i_99_ = i_97_ + 1;
                if (i_99_ == 12) {
                    i_99_ = 0;
                }
                this.clay[i_91_][1][i_97_] = ((this.clay[i_91_][1][i_98_] + this.clay[i_91_][1][i_99_]) / 2 + this.clay[i_91_][1][i_97_]) / 2;
                this.clay[i_91_][2][i_97_] = ((this.clay[i_91_][2][i_98_] + this.clay[i_91_][2][i_99_]) / 2 + this.clay[i_91_][2][i_97_]) / 2;
                final int i_100_ = (int)Math.sqrt(this.clax[i_91_][2][i_97_] * this.clax[i_91_][2][i_97_] + this.claz[i_91_][2][i_97_] * this.claz[i_91_][2][i_97_]);
                if (i_100_ > this.cmx[i_91_]) {
                    this.cmx[i_91_] = i_100_;
                }
            }
            for (int i_101_ = 0; i_101_ < 6; ++i_101_) {
                final double d = Math.random();
                final double d_102_ = Math.random();
                for (int i_103_ = 0; i_103_ < 3; ++i_103_) {
                    f_92_ = this.clds[i_103_] * 1.05f - this.clds[i_103_];
                    this.clc[i_91_][0][i_101_][i_103_] = (int)(this.clds[i_103_] + f_92_ * d);
                    if (this.clc[i_91_][0][i_101_][i_103_] > 255) {
                        this.clc[i_91_][0][i_101_][i_103_] = 255;
                    }
                    if (this.clc[i_91_][0][i_101_][i_103_] < 0) {
                        this.clc[i_91_][0][i_101_][i_103_] = 0;
                    }
                    this.clc[i_91_][1][i_101_][i_103_] = (int)(this.clds[i_103_] * 1.05f + f_92_ * d_102_);
                    if (this.clc[i_91_][1][i_101_][i_103_] > 255) {
                        this.clc[i_91_][1][i_101_][i_103_] = 255;
                    }
                    if (this.clc[i_91_][1][i_101_][i_103_] < 0) {
                        this.clc[i_91_][1][i_101_][i_103_] = 0;
                    }
                }
            }
        }
    }
    
    public void drawclouds(final Graphics2D graphics2d) {
        for (int i = 0; i < this.noc; ++i) {
            final int i_104_ = this.cx + (int)((this.clx[i] - this.x / 20 - this.cx) * this.cos(this.xz) - (this.clz[i] - this.z / 20 - this.cz) * this.sin(this.xz));
            final int i_105_ = this.cz + (int)((this.clx[i] - this.x / 20 - this.cx) * this.sin(this.xz) + (this.clz[i] - this.z / 20 - this.cz) * this.cos(this.xz));
            final int i_106_ = this.cz + (int)((this.cldd[4] - this.y / 20 - this.cy) * this.sin(this.zy) + (i_105_ - this.cz) * this.cos(this.zy));
            final int i_107_ = this.xs(i_104_ + this.cmx[i], i_106_);
            final int i_108_ = this.xs(i_104_ - this.cmx[i], i_106_);
            if (i_107_ > 0 && i_108_ < this.w && i_106_ > -this.cmx[i] && i_107_ - i_108_ > 20) {
                final int[][] is = new int[3][12];
                final int[][] is_109_ = new int[3][12];
                final int[][] is_110_ = new int[3][12];
                final int[] is_111_ = new int[12];
                final int[] is_112_ = new int[12];
                boolean bool_116_ = true;
                for (int i_120_ = 0; i_120_ < 3; ++i_120_) {
                    for (int i_121_ = 0; i_121_ < 12; ++i_121_) {
                        is[i_120_][i_121_] = this.clax[i][i_120_][i_121_] + this.clx[i] - this.x / 20;
                        is_110_[i_120_][i_121_] = this.claz[i][i_120_][i_121_] + this.clz[i] - this.z / 20;
                        is_109_[i_120_][i_121_] = this.clay[i][i_120_][i_121_] + this.cldd[4] - this.y / 20;
                    }
                    this.rot(is[i_120_], is_110_[i_120_], this.cx, this.cz, this.xz, 12);
                    this.rot(is_109_[i_120_], is_110_[i_120_], this.cy, this.cz, this.zy, 12);
                }
                for (int i_122_ = 0; i_122_ < 12; i_122_ += 2) {
                    int i_123_ = 0;
                    int i_124_ = 0;
                    int i_125_ = 0;
                    int i_126_ = 0;
                    bool_116_ = true;
                    int i_127_ = 0;
                    int i_128_ = 0;
                    int i_129_ = 0;
                    for (int i_130_ = 0; i_130_ < 6; ++i_130_) {
                        int i_131_ = 0;
                        int i_132_ = 1;
                        if (i_130_ == 0) {
                            i_131_ = i_122_;
                        }
                        if (i_130_ == 1) {
                            i_131_ = i_122_ + 1;
                            if (i_131_ >= 12) {
                                i_131_ -= 12;
                            }
                        }
                        if (i_130_ == 2) {
                            i_131_ = i_122_ + 2;
                            if (i_131_ >= 12) {
                                i_131_ -= 12;
                            }
                        }
                        if (i_130_ == 3) {
                            i_131_ = i_122_ + 2;
                            if (i_131_ >= 12) {
                                i_131_ -= 12;
                            }
                            i_132_ = 2;
                        }
                        if (i_130_ == 4) {
                            i_131_ = i_122_ + 1;
                            if (i_131_ >= 12) {
                                i_131_ -= 12;
                            }
                            i_132_ = 2;
                        }
                        if (i_130_ == 5) {
                            i_131_ = i_122_;
                            i_132_ = 2;
                        }
                        is_111_[i_130_] = this.xs(is[i_132_][i_131_], is_110_[i_132_][i_131_]);
                        is_112_[i_130_] = this.ys(is_109_[i_132_][i_131_], is_110_[i_132_][i_131_]);
                        i_128_ += is[i_132_][i_131_];
                        i_127_ += is_109_[i_132_][i_131_];
                        i_129_ += is_110_[i_132_][i_131_];
                        if (is_112_[i_130_] < 0 || is_110_[0][i_130_] < 10) {
                            ++i_123_;
                        }
                        if (is_112_[i_130_] > this.h || is_110_[0][i_130_] < 10) {
                            ++i_124_;
                        }
                        if (is_111_[i_130_] < 0 || is_110_[0][i_130_] < 10) {
                            ++i_125_;
                        }
                        if (is_111_[i_130_] > this.w || is_110_[0][i_130_] < 10) {
                            ++i_126_;
                        }
                    }
                    if (i_125_ == 6 || i_123_ == 6 || i_124_ == 6 || i_126_ == 6) {
                        bool_116_ = false;
                    }
                    if (bool_116_) {
                        i_128_ /= 6;
                        i_127_ /= 6;
                        i_129_ /= 6;
                        final int i_133_ = (int)Math.sqrt((this.cy - i_127_) * (this.cy - i_127_) + (this.cx - i_128_) * (this.cx - i_128_) + i_129_ * i_129_);
                        if (i_133_ < this.fade[7]) {
                            int i_134_ = this.clc[i][1][i_122_ / 2][0];
                            int i_135_ = this.clc[i][1][i_122_ / 2][1];
                            int i_136_ = this.clc[i][1][i_122_ / 2][2];
                            for (int i_137_ = 0; i_137_ < 16; ++i_137_) {
                                if (i_133_ > this.fade[i_137_]) {
                                    i_134_ = (i_134_ * this.fogd + this.cfade[0]) / (this.fogd + 1);
                                    i_135_ = (i_135_ * this.fogd + this.cfade[1]) / (this.fogd + 1);
                                    i_136_ = (i_136_ * this.fogd + this.cfade[2]) / (this.fogd + 1);
                                }
                            }
                            graphics2d.setColor(new Color(i_134_, i_135_, i_136_));
                            graphics2d.fillPolygon(is_111_, is_112_, 6);
                            if (this.effect[5]) {
                                final int n = this.polyoutline[1];
                            }
                        }
                    }
                }
                for (int i_138_ = 0; i_138_ < 12; i_138_ += 2) {
                    int i_139_ = 0;
                    int i_140_ = 0;
                    int i_141_ = 0;
                    int i_142_ = 0;
                    bool_116_ = true;
                    int i_143_ = 0;
                    int i_144_ = 0;
                    int i_145_ = 0;
                    for (int i_146_ = 0; i_146_ < 6; ++i_146_) {
                        int i_147_ = 0;
                        int i_148_ = 0;
                        if (i_146_ == 0) {
                            i_147_ = i_138_;
                        }
                        if (i_146_ == 1) {
                            i_147_ = i_138_ + 1;
                            if (i_147_ >= 12) {
                                i_147_ -= 12;
                            }
                        }
                        if (i_146_ == 2) {
                            i_147_ = i_138_ + 2;
                            if (i_147_ >= 12) {
                                i_147_ -= 12;
                            }
                        }
                        if (i_146_ == 3) {
                            i_147_ = i_138_ + 2;
                            if (i_147_ >= 12) {
                                i_147_ -= 12;
                            }
                            i_148_ = 1;
                        }
                        if (i_146_ == 4) {
                            i_147_ = i_138_ + 1;
                            if (i_147_ >= 12) {
                                i_147_ -= 12;
                            }
                            i_148_ = 1;
                        }
                        if (i_146_ == 5) {
                            i_147_ = i_138_;
                            i_148_ = 1;
                        }
                        is_111_[i_146_] = this.xs(is[i_148_][i_147_], is_110_[i_148_][i_147_]);
                        is_112_[i_146_] = this.ys(is_109_[i_148_][i_147_], is_110_[i_148_][i_147_]);
                        i_144_ += is[i_148_][i_147_];
                        i_143_ += is_109_[i_148_][i_147_];
                        i_145_ += is_110_[i_148_][i_147_];
                        if (is_112_[i_146_] < 0 || is_110_[0][i_146_] < 10) {
                            ++i_139_;
                        }
                        if (is_112_[i_146_] > this.h || is_110_[0][i_146_] < 10) {
                            ++i_140_;
                        }
                        if (is_111_[i_146_] < 0 || is_110_[0][i_146_] < 10) {
                            ++i_141_;
                        }
                        if (is_111_[i_146_] > this.w || is_110_[0][i_146_] < 10) {
                            ++i_142_;
                        }
                    }
                    if (i_141_ == 6 || i_139_ == 6 || i_140_ == 6 || i_142_ == 6) {
                        bool_116_ = false;
                    }
                    if (bool_116_) {
                        i_144_ /= 6;
                        i_143_ /= 6;
                        i_145_ /= 6;
                        final int i_149_ = (int)Math.sqrt((this.cy - i_143_) * (this.cy - i_143_) + (this.cx - i_144_) * (this.cx - i_144_) + i_145_ * i_145_);
                        if (i_149_ < this.fade[7]) {
                            int i_150_ = this.clc[i][0][i_138_ / 2][0];
                            int i_151_ = this.clc[i][0][i_138_ / 2][1];
                            int i_152_ = this.clc[i][0][i_138_ / 2][2];
                            for (int i_153_ = 0; i_153_ < 16; ++i_153_) {
                                if (i_149_ > this.fade[i_153_]) {
                                    i_150_ = (i_150_ * this.fogd + this.cfade[0]) / (this.fogd + 1);
                                    i_151_ = (i_151_ * this.fogd + this.cfade[1]) / (this.fogd + 1);
                                    i_152_ = (i_152_ * this.fogd + this.cfade[2]) / (this.fogd + 1);
                                }
                            }
                            graphics2d.setColor(new Color(i_150_, i_151_, i_152_));
                            graphics2d.fillPolygon(is_111_, is_112_, 6);
                            if (this.effect[5]) {
                                final int n2 = this.polyoutline[1];
                            }
                        }
                    }
                }
                int i_154_ = 0;
                int i_155_ = 0;
                int i_156_ = 0;
                int i_157_ = 0;
                bool_116_ = true;
                int i_158_ = 0;
                int i_159_ = 0;
                int i_160_ = 0;
                for (int i_161_ = 0; i_161_ < 12; ++i_161_) {
                    is_111_[i_161_] = this.xs(is[0][i_161_], is_110_[0][i_161_]);
                    is_112_[i_161_] = this.ys(is_109_[0][i_161_], is_110_[0][i_161_]);
                    i_159_ += is[0][i_161_];
                    i_158_ += is_109_[0][i_161_];
                    i_160_ += is_110_[0][i_161_];
                    if (is_112_[i_161_] < 0 || is_110_[0][i_161_] < 10) {
                        ++i_154_;
                    }
                    if (is_112_[i_161_] > this.h || is_110_[0][i_161_] < 10) {
                        ++i_155_;
                    }
                    if (is_111_[i_161_] < 0 || is_110_[0][i_161_] < 10) {
                        ++i_156_;
                    }
                    if (is_111_[i_161_] > this.w || is_110_[0][i_161_] < 10) {
                        ++i_157_;
                    }
                }
                if (i_156_ == 12 || i_154_ == 12 || i_155_ == 12 || i_157_ == 12) {
                    bool_116_ = false;
                }
                if (bool_116_) {
                    i_159_ /= 12;
                    i_158_ /= 12;
                    i_160_ /= 12;
                    final int i_162_ = (int)Math.sqrt((this.cy - i_158_) * (this.cy - i_158_) + (this.cx - i_159_) * (this.cx - i_159_) + i_160_ * i_160_);
                    if (i_162_ < this.fade[7]) {
                        int i_163_ = this.clds[0];
                        int i_164_ = this.clds[1];
                        int i_165_ = this.clds[2];
                        for (int i_166_ = 0; i_166_ < 16; ++i_166_) {
                            if (i_162_ > this.fade[i_166_]) {
                                i_163_ = (i_163_ * this.fogd + this.cfade[0]) / (this.fogd + 1);
                                i_164_ = (i_164_ * this.fogd + this.cfade[1]) / (this.fogd + 1);
                                i_165_ = (i_165_ * this.fogd + this.cfade[2]) / (this.fogd + 1);
                            }
                        }
                        graphics2d.setColor(new Color(i_163_, i_164_, i_165_));
                        graphics2d.fillPolygon(is_111_, is_112_, 12);
                        if (this.effect[5]) {
                            final int n3 = this.polyoutline[1];
                        }
                    }
                }
            }
        }
    }
    
    public void newmountains(final int paramInt1, final int paramInt2, final int paramInt3, final int paramInt4, final int x, final xtGraphics xtgraphics) {
        final Random localRandom = new Random(this.mgen);
        this.nmt = (int)(20.0 + 10.0 * localRandom.nextDouble());
        if (xtgraphics.careermode && (this.effect[8] || x == 11 || this.effect[11] || x == 22 || x == 24 || x == 7)) {
            this.nmt = 0;
        }
        final int i = (paramInt1 + paramInt2) / 60;
        final int j = (paramInt3 + paramInt4) / 60;
        final int k = Math.max(paramInt2 - paramInt1, paramInt4 - paramInt3) / 60;
        this.mrd = null;
        this.nmv = null;
        this.mtx = null;
        this.mty = null;
        this.mtz = null;
        this.mtc = null;
        this.mrd = new int[this.nmt];
        this.nmv = new int[this.nmt];
        this.mtx = new int[this.nmt][];
        this.mty = new int[this.nmt][];
        this.mtz = new int[this.nmt][];
        this.mtc = new int[this.nmt][][];
        final int[] arrayOfInt1 = new int[this.nmt];
        final int[] arrayOfInt2 = new int[this.nmt];
        for (int m = 0; m < this.nmt; ++m) {
            int n = 85;
            float f1 = 0.5f;
            float f2 = 0.5f;
            arrayOfInt1[m] = (int)(10000.0 + localRandom.nextDouble() * 10000.0);
            final int i2 = (int)(localRandom.nextDouble() * 360.0);
            if (localRandom.nextDouble() > localRandom.nextDouble()) {
                f1 = (float)(0.2 + localRandom.nextDouble() * 0.35);
                f2 = (float)(0.2 + localRandom.nextDouble() * 0.35);
                this.nmv[m] = (int)(f1 * (24.0 + 16.0 * localRandom.nextDouble()));
                n = (int)(85.0 + 10.0 * localRandom.nextDouble());
            }
            else {
                f1 = (float)(0.3 + localRandom.nextDouble() * 1.1);
                f2 = (float)(0.2 + localRandom.nextDouble() * 0.35);
                this.nmv[m] = (int)(f1 * (12.0 + 8.0 * localRandom.nextDouble()));
                n = (int)(104.0 - 10.0 * localRandom.nextDouble());
            }
            this.mtx[m] = new int[this.nmv[m] * 2];
            this.mty[m] = new int[this.nmv[m] * 2];
            this.mtz[m] = new int[this.nmv[m] * 2];
            this.mtc[m] = new int[this.nmv[m]][3];
            for (int i3 = 0; i3 < this.nmv[m]; ++i3) {
                this.mtx[m][i3] = (int)((i3 * 500 + (localRandom.nextDouble() * 800.0 - 400.0) - 250 * (this.nmv[m] - 1)) * f1);
                this.mtx[m][i3 + this.nmv[m]] = (int)((i3 * 500 + (localRandom.nextDouble() * 800.0 - 400.0) - 250 * (this.nmv[m] - 1)) * f1);
                this.mtx[m][this.nmv[m]] = (int)(this.mtx[m][0] - (100.0 + localRandom.nextDouble() * 600.0) * f1);
                this.mtx[m][this.nmv[m] * 2 - 1] = (int)(this.mtx[m][this.nmv[m] - 1] + (100.0 + localRandom.nextDouble() * 600.0) * f1);
                if (i3 == 0 || i3 == this.nmv[m] - 1) {
                    this.mty[m][i3] = (int)((-400.0 - 1200.0 * localRandom.nextDouble()) * f2 + this.ground);
                }
                if (i3 == 1 || i3 == this.nmv[m] - 2) {
                    this.mty[m][i3] = (int)((-1000.0 - 1450.0 * localRandom.nextDouble()) * f2 + this.ground);
                }
                if (i3 > 1 && i3 < this.nmv[m] - 2) {
                    this.mty[m][i3] = (int)((-1600.0 - 1700.0 * localRandom.nextDouble()) * f2 + this.ground);
                }
                this.mty[m][i3 + this.nmv[m]] = this.ground - 70;
                this.mtz[m][i3] = j + k + arrayOfInt1[m];
                this.mtz[m][i3 + this.nmv[m]] = j + k + arrayOfInt1[m];
                final float f3 = (float)(0.5 + localRandom.nextDouble() * 0.5);
                this.mtc[m][i3][0] = (int)(170.0f * f3 + 170.0f * f3 * (this.snap[0] / 100.0f));
                if (this.mtc[m][i3][0] > 255) {
                    this.mtc[m][i3][0] = 255;
                }
                if (this.mtc[m][i3][0] < 0) {
                    this.mtc[m][i3][0] = 0;
                }
                this.mtc[m][i3][1] = (int)(n * f3 + 85.0f * f3 * (this.snap[1] / 100.0f));
                if (this.mtc[m][i3][1] > 255) {
                    this.mtc[m][i3][1] = 255;
                }
                if (this.mtc[m][i3][1] < 1) {
                    this.mtc[m][i3][1] = 0;
                }
                this.mtc[m][i3][2] = 0;
            }
            for (int i3 = 1; i3 < this.nmv[m] - 1; ++i3) {
                final int i4 = i3 - 1;
                final int i5 = i3 + 1;
                this.mty[m][i3] = ((this.mty[m][i4] + this.mty[m][i5]) / 2 + this.mty[m][i3]) / 2;
            }
            this.rot(this.mtx[m], this.mtz[m], i, j, i2, this.nmv[m] * 2);
            arrayOfInt2[m] = 0;
        }
        for (int m = 0; m < this.nmt; ++m) {
            for (int n = m + 1; n < this.nmt; ++n) {
                if (arrayOfInt1[m] < arrayOfInt1[n]) {
                    final int[] array = arrayOfInt2;
                    final int n2 = m;
                    ++array[n2];
                }
                else {
                    final int[] array2 = arrayOfInt2;
                    final int n3 = n;
                    ++array2[n3];
                }
            }
            this.mrd[arrayOfInt2[m]] = m;
        }
    }
    
    public void drawmountains(final Graphics2D paramGraphics) {
        for (int i = 0; i < this.nmt; ++i) {
            final int j = this.mrd[i];
            final int k = this.cx + (int)((this.mtx[j][0] - this.x / 30 - this.cx) * this.cos(this.xz) - (this.mtz[j][0] - this.z / 30 - this.cz) * this.sin(this.xz));
            final int m = this.cz + (int)((this.mtx[j][0] - this.x / 30 - this.cx) * this.sin(this.xz) + (this.mtz[j][0] - this.z / 30 - this.cz) * this.cos(this.xz));
            final int n = this.cz + (int)((this.mty[j][0] - this.y / 30 - this.cy) * this.sin(this.zy) + (m - this.cz) * this.cos(this.zy));
            final int i2 = this.cx + (int)((this.mtx[j][this.nmv[j] - 1] - this.x / 30 - this.cx) * this.cos(this.xz) - (this.mtz[j][this.nmv[j] - 1] - this.z / 30 - this.cz) * this.sin(this.xz));
            final int i3 = this.cz + (int)((this.mtx[j][this.nmv[j] - 1] - this.x / 30 - this.cx) * this.sin(this.xz) + (this.mtz[j][this.nmv[j] - 1] - this.z / 30 - this.cz) * this.cos(this.xz));
            final int i4 = this.cz + (int)((this.mty[j][this.nmv[j] - 1] - this.y / 30 - this.cy) * this.sin(this.zy) + (i3 - this.cz) * this.cos(this.zy));
            if (this.xs(i2, i4) > 0 && this.xs(k, n) < this.w) {
                final int[] arrayOfInt1 = new int[this.nmv[j] * 2];
                final int[] arrayOfInt2 = new int[this.nmv[j] * 2];
                final int[] arrayOfInt3 = new int[this.nmv[j] * 2];
                for (int i5 = 0; i5 < this.nmv[j] * 2; ++i5) {
                    arrayOfInt1[i5] = this.mtx[j][i5] - this.x / 30;
                    arrayOfInt2[i5] = this.mty[j][i5] - this.y / 30;
                    arrayOfInt3[i5] = this.mtz[j][i5] - this.z / 30;
                }
                int i5 = (int)Math.sqrt(arrayOfInt1[this.nmv[j] / 4] * arrayOfInt1[this.nmv[j] / 4] + arrayOfInt3[this.nmv[j] / 4] * arrayOfInt3[this.nmv[j] / 4]);
                this.rot(arrayOfInt1, arrayOfInt3, this.cx, this.cz, this.xz, this.nmv[j] * 2);
                this.rot(arrayOfInt2, arrayOfInt3, this.cy, this.cz, this.zy, this.nmv[j] * 2);
                final int[] arrayOfInt4 = new int[4];
                final int[] arrayOfInt5 = new int[4];
                int i6 = 0;
                int i7 = 0;
                int i8 = 0;
                int i9 = 0;
                int i10 = 1;
                for (int i11 = 0; i11 < this.nmv[j] - 1; ++i11) {
                    i6 = 0;
                    i7 = 0;
                    i8 = 0;
                    i9 = 0;
                    i10 = 1;
                    for (int i12 = 0; i12 < 4; ++i12) {
                        int i13 = i12 + i11;
                        if (i12 == 2) {
                            i13 = i11 + this.nmv[j] + 1;
                        }
                        if (i12 == 3) {
                            i13 = i11 + this.nmv[j];
                        }
                        arrayOfInt4[i12] = this.xs(arrayOfInt1[i13], arrayOfInt3[i13]);
                        arrayOfInt5[i12] = this.ys(arrayOfInt2[i13], arrayOfInt3[i13]);
                        if (arrayOfInt5[i12] < 0 || arrayOfInt3[i13] < 10) {
                            ++i6;
                        }
                        if (arrayOfInt5[i12] > this.h || arrayOfInt3[i13] < 10) {
                            ++i7;
                        }
                        if (arrayOfInt4[i12] < 0 || arrayOfInt3[i13] < 10) {
                            ++i8;
                        }
                        if (arrayOfInt4[i12] > this.w || arrayOfInt3[i13] < 10) {
                            ++i9;
                        }
                    }
                    if (i8 == 4 || i6 == 4 || i7 == 4 || i9 == 4) {
                        i10 = 0;
                    }
                    if (i10 != 0) {
                        float f = i5 / 2500.0f + (8000.0f - this.fade[0]) / 1000.0f - 2.0f - (Math.abs(this.y) - 250.0f) / 5000.0f;
                        if (f > 0.0f && f < 10.0f) {
                            if (f < 3.5) {
                                f = 3.5f;
                            }
                            final int i13 = (int)((this.mtc[j][i11][0] + this.cgrnd[0] + this.csky[0] * f + this.cfade[0] * f) / (2.0f + f * 2.0f));
                            final int i14 = (int)((this.mtc[j][i11][1] + this.cgrnd[1] + this.csky[1] * f + this.cfade[1] * f) / (2.0f + f * 2.0f));
                            final int i15 = (int)((this.mtc[j][i11][2] + this.cgrnd[2] + this.csky[2] * f + this.cfade[2] * f) / (2.0f + f * 2.0f));
                            paramGraphics.setColor(new Color(i13, i14, i15));
                            paramGraphics.fillPolygon(arrayOfInt4, arrayOfInt5, 4);
                        }
                    }
                }
            }
        }
    }
    
    public void newstars(final int x, final xtGraphics xtgraphics) {
        this.stx = null;
        this.stz = null;
        this.stc = null;
        this.bst = null;
        this.twn = null;
        this.nst = 0;
        if (this.lightson) {
            final Random random = new Random(100000L);
            this.nst = 50;
            if (xtgraphics.careermode) {
                if (x == 11 || x == 24 || x == 16) {
                    this.nst = 0;
                }
                if (this.effect[8]) {
                    this.nst = 120;
                }
                if (this.effect[11]) {
                    this.nst = 180;
                }
            }
            double starrange = 2000.0;
            if (this.effect[8]) {
                starrange = 20000.0;
            }
            this.stx = new int[this.nst];
            this.stz = new int[this.nst];
            this.stc = new int[this.nst][2][3];
            this.bst = new boolean[this.nst];
            this.twn = new int[this.nst];
            for (int i = 0; i < this.nst; ++i) {
                this.stx[i] = (int)(starrange * random.nextDouble() - starrange / 2.0);
                this.stz[i] = (int)(starrange * random.nextDouble() - starrange / 2.0);
                int i_212_ = (int)(3.0 * random.nextDouble());
                if (i_212_ >= 3) {
                    i_212_ = 0;
                }
                if (i_212_ <= -1) {
                    i_212_ = 2;
                }
                int i_213_ = i_212_ + 1;
                if (random.nextDouble() > random.nextDouble()) {
                    i_213_ = i_212_ - 1;
                }
                if (i_213_ == 3) {
                    i_213_ = 0;
                }
                if (i_213_ == -1) {
                    i_213_ = 2;
                }
                for (int i_214_ = 0; i_214_ < 3; ++i_214_) {
                    this.stc[i][0][i_214_] = 200;
                    if (i_212_ == i_214_) {
                        final int[] array = this.stc[i][0];
                        final int n = i_214_;
                        array[n] += (int)(55.0 * random.nextDouble());
                    }
                    if (i_213_ == i_214_) {
                        final int[] array2 = this.stc[i][0];
                        final int n2 = i_214_;
                        array2[n2] += 55;
                    }
                    this.stc[i][0][i_214_] = (this.stc[i][0][i_214_] * 2 + this.csky[i_214_]) / 3;
                    this.stc[i][1][i_214_] = (this.stc[i][0][i_214_] + this.csky[i_214_]) / 2;
                }
                this.twn[i] = (int)(4.0 * random.nextDouble());
                if (random.nextDouble() > 0.8) {
                    this.bst[i] = true;
                }
                else {
                    this.bst[i] = false;
                }
            }
        }
    }
    
    public void drawstars(final Graphics2D graphics2d) {
        if (this.effect[8]) {
            ++this.effecttime;
        }
        final int[] newstz = new int[this.nst];
        final int[] starzsort = new int[this.nst];
        for (int a = 0; a < this.nst; ++a) {
            starzsort[a] = this.stz[a];
        }
        Arrays.sort(starzsort);
        final int minstarz = starzsort[0];
        final int maxstarz = starzsort[this.nst - 1];
        final int totalstarz = maxstarz - minstarz;
        int starraise = 0;
        if (this.effect[8]) {
            starraise = 1500;
        }
        for (int i = 0; i < this.nst; ++i) {
            newstz[i] = this.stz[i];
            if (this.effect[8]) {
                final int starspeed = 500;
                if (this.stz[i] + this.effecttime * starspeed - this.starfase[i] * totalstarz <= maxstarz) {
                    newstz[i] = this.stz[i] + this.effecttime * starspeed - this.starfase[i] * totalstarz;
                }
                else {
                    final int[] starfase = this.starfase;
                    final int n = i;
                    ++starfase[n];
                }
            }
            int i_215_ = this.cx + (int)(this.stx[i] * this.cos(this.xz) - newstz[i] * this.sin(this.xz));
            final int i_216_ = this.cz + (int)(this.stx[i] * this.sin(this.xz) + newstz[i] * this.cos(this.xz));
            final int starsections = 6;
            if (this.effect[11]) {
                for (int a2 = 0; a2 < starsections; ++a2) {
                    if (i >= this.nst * a2 / starsections && i < this.nst * (a2 + 1) / starsections) {
                        starraise = -600 + a2 * (600 / (starsections - 1));
                    }
                }
            }
            int i_217_ = this.cy - starraise + (int)(-200.0f * this.cos(this.zy) - i_216_ * this.sin(this.zy));
            final int i_218_ = this.cz + (int)(-200.0f * this.sin(this.zy) + i_216_ * this.cos(this.zy));
            i_215_ = this.xs(i_215_, i_218_);
            i_217_ = this.ys(i_217_, i_218_);
            boolean starception = false;
            if (this.effect[11] && (i == 121 || i == 125 || i == 129)) {
                starception = true;
            }
            if (i_215_ - 1 > this.iw && i_215_ + 3 < this.w && i_217_ - 1 > this.ih && i_217_ + 3 < this.h && !starception) {
                if (this.twn[i] == 0) {
                    int i_219_ = (int)(3.0 * Math.random());
                    if (i_219_ >= 3) {
                        i_219_ = 0;
                    }
                    if (i_219_ <= -1) {
                        i_219_ = 2;
                    }
                    int i_220_ = i_219_ + 1;
                    if (Math.random() > Math.random()) {
                        i_220_ = i_219_ - 1;
                    }
                    if (i_220_ == 3) {
                        i_220_ = 0;
                    }
                    if (i_220_ == -1) {
                        i_220_ = 2;
                    }
                    for (int i_221_ = 0; i_221_ < 3; ++i_221_) {
                        this.stc[i][0][i_221_] = 200;
                        if (i_219_ == i_221_) {
                            final int[] array = this.stc[i][0];
                            final int n2 = i_221_;
                            array[n2] += (int)(55.0 * Math.random());
                        }
                        if (i_220_ == i_221_) {
                            final int[] array2 = this.stc[i][0];
                            final int n3 = i_221_;
                            array2[n3] += 55;
                        }
                        this.stc[i][0][i_221_] = (this.stc[i][0][i_221_] * 2 + this.csky[i_221_]) / 3;
                        this.stc[i][1][i_221_] = (this.stc[i][0][i_221_] + this.csky[i_221_]) / 2;
                    }
                    this.twn[i] = 3;
                }
                else {
                    final int[] twn = this.twn;
                    final int n4 = i;
                    --twn[n4];
                }
                int i_222_ = 0;
                if (this.bst[i]) {
                    i_222_ = 1;
                }
                if (this.effect[5]) {
                    for (int a3 = 0; a3 < 2; ++a3) {}
                }
                graphics2d.setColor(new Color(this.stc[i][1][0], this.stc[i][1][1], this.stc[i][1][2]));
                graphics2d.fillRect(i_215_ - 1, i_217_, 3 + i_222_, 1 + i_222_);
                graphics2d.fillRect(i_215_, i_217_ - 1, 1 + i_222_, 3 + i_222_);
                graphics2d.setColor(new Color(this.stc[i][0][0], this.stc[i][0][1], this.stc[i][0][2]));
                graphics2d.fillRect(i_215_, i_217_, 1 + i_222_, 1 + i_222_);
            }
        }
    }
    
    public void setpolys(int i, final int j, final int k) {
        this.polycolour[0] = i;
        this.polycolour[1] = j;
        this.polycolour[2] = k;
        this.cpol[0] = (int)(i + i * (this.snap[0] / 100.0f));
        if (this.cpol[0] > 255) {
            this.cpol[0] = 255;
        }
        if (this.cpol[0] < 0) {
            this.cpol[0] = 0;
        }
        this.cpol[1] = (int)(j + j * (this.snap[1] / 100.0f));
        if (this.cpol[1] > 255) {
            this.cpol[1] = 255;
        }
        if (this.cpol[1] < 0) {
            this.cpol[1] = 0;
        }
        this.cpol[2] = (int)(k + k * (this.snap[2] / 100.0f));
        if (this.cpol[2] > 255) {
            this.cpol[2] = 255;
        }
        if (this.cpol[2] < 0) {
            this.cpol[2] = 0;
        }
        this.dispolys = 0;
        for (i = 0; i < 3; ++i) {
            this.crgrnd[i] = (int)((this.cpol[i] * 0.99 + this.cgrnd[i]) / 2.0);
            this.ogrnd[i] = (int)((this.cpol[i] * 0.99 + this.cgrnd[i]) / 2.0);
        }
    }
    
    public int ys(final int i, int j) {
        if (j < 10) {
            j = 10;
        }
        return (j - this.focus_point) * (this.cy - i) / j + i;
    }
    
    public float sin(int i) {
        while (i >= 360) {
            i -= 360;
        }
        while (i < 0) {
            i += 360;
        }
        return this.tsin[i];
    }
    
    public Medium() {
        this.fade = new int[] { 3000, 4500, 6000, 7500, 9000, 10500, 12000, 13500, 15000, 16500, 18000, 19500, 21000, 22500, 24000, 25500 };
        this.originalfade = new int[] { 3000, 4500, 6000, 7500, 9000, 10500, 12000, 13500, 15000, 16500, 18000, 19500, 21000, 22500, 24000, 25500 };
        this.csky = new int[] { 170, 220, 255 };
        this.cgrnd = new int[] { 205, 200, 200 };
        this.cpol = new int[] { 215, 210, 210 };
        this.cfade = new int[] { 255, 220, 220 };
        this.osky = new int[] { 170, 220, 255 };
        this.cldd = new int[] { 210, 210, 210, 1, -1000 };
        this.clds = new int[] { 210, 210, 210 };
        this.clx = null;
        this.stx = null;
        this.pvr = null;
        this.nst = 0;
        this.cgpx = null;
        this.pcv = null;
        this.cgpz = null;
        this.pmx = null;
        this.stz = null;
        this.stc = null;
        this.bst = null;
        this.twn = null;
        this.resdown = 0;
        this.rescnt = 0;
        this.mgen = (int)(Math.random() * 100000.0);
        this.clz = null;
        this.cmx = null;
        this.clax = null;
        this.clay = null;
        this.claz = null;
        this.clc = null;
        this.noc = 0;
        this.nmt = 0;
        this.mrd = null;
        this.nmv = null;
        this.mtx = null;
        this.mty = null;
        this.mtz = null;
        this.mtc = null;
        this.ogrnd = new int[] { 205, 200, 200 };
        this.crgrnd = new int[] { 205, 200, 200 };
        this.ocsky = new int[3];
        this.ocfade = new int[3];
        this.starfase = new int[1000];
        this.focus_point = 400;
        this.ground = 250;
        this.skyline = -300;
        this.snap = new int[3];
        this.osnap = new int[3];
        this.polyoutline = new int[3];
        this.origfade = 3000;
        this.fogd = 7;
        this.switchfase = 0;
        this.icehills = false;
        this.makefase = 0;
        this.darklevel = 0;
        this.glassfade = 65;
        this.changingsnap = false;
        this.snowheight = 0;
        this.oneffect = new boolean[100];
        this.effect = new boolean[100];
        this.darken = false;
        this.darkenfor = 0;
        this.reducepolys = 1.0;
        this.shadowtrans = 80;
        this.decrease = false;
        this.glowfase = 0;
        this.drowned = false;
        this.lightson = false;
        this.verydark = false;
        this.lightn = -1;
        this.lilo = 217;
        this.greystage = 0;
        this.iw = 0;
        this.ih = 0;
        this.snowno = 0;
        this.effecttime = 0;
        this.flex = 0;
        this.trk = false;
        this.groundcolour = false;
        this.crs = false;
        this.cx = 435;
        this.cy = 240;
        this.skycolour = new int[3];
        this.fadecolour = new int[3];
        this.grndcolour = new int[3];
        this.polycolour = new int[3];
        this.cz = 50;
        this.xz = 0;
        this.zy = 0;
        this.wallside = new int[4];
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.w = 870;
        this.h = 480;
        this.nsp = 0;
        this.showsnow = false;
        this.showwater = false;
        this.spx = new int[101];
        this.spz = new int[101];
        this.sprad = new int[101];
        this.td = false;
        this.bcxz = 0;
        this.bcxzwatch = 0.0;
        this.bt = false;
        this.vxz = 180;
        this.adv = 500;
        this.vert = false;
        this.trns = 1;
        this.dispolys = 0;
        this.ogpx = new int[200000][8];
        this.ogpz = new int[200000][8];
        this.cgpx = new int[200000];
        this.cgpz = new int[200000];
        this.sgpx = 0;
        this.sgpz = 0;
        this.nrw = 0;
        this.ncl = 0;
        this.tcos = new float[360];
        this.tsin = new float[360];
        this.lastmaf = 0;
        this.checkpoint = -1;
        this.lastcheck = false;
        this.elecr = 0.0f;
        this.cpflik = false;
        this.nochekflk = false;
        this.cntrn = 0;
        this.diup = new boolean[3];
        this.rand = new int[3];
        this.trn = 0;
        this.hit = 45000;
        this.ptr = 0;
        this.ptcnt = -10;
        this.nrnd = 0;
        this.trx = 0L;
        this.trz = 0L;
        this.atrx = 0L;
        this.atrz = 0L;
        this.fallen = 0;
        this.fo = 1.0f;
        this.gofo = (float)(0.33000001311302185 + Math.random() * 1.34);
        int i = 0;
        do {
            this.tcos[i] = (float)Math.cos(i * 0.017453292519943295);
        } while (++i < 360);
        i = 0;
        do {
            this.tsin[i] = (float)Math.sin(i * 0.017453292519943295);
        } while (++i < 360);
    }
    
    public void reset() {
        this.vxz = 180;
        this.trx = 0L;
        this.trz = 0L;
        this.atrx = 0L;
        this.atrz = 0L;
        this.fallen = 0;
        this.fo = 1.0f;
        this.gofo = (float)(0.33000001311302185 + Math.random() * 1.34);
        for (int a = 0; a < 3; ++a) {
            this.snap[a] = 0;
            this.osnap[a] = 0;
            this.skycolour[a] = 0;
            this.fadecolour[a] = 0;
            this.grndcolour[a] = 0;
            this.polycolour[a] = 0;
        }
        for (int a = 0; a < 8; ++a) {}
        for (int a = 0; a < 100; ++a) {
            this.effect[a] = false;
            this.oneffect[a] = false;
        }
        for (int a = 0; a < 1000; ++a) {
            this.starfase[a] = 0;
        }
        this.csky[0] = 170;
        this.csky[1] = 220;
        this.csky[2] = 255;
        this.reducepolys = 1.0;
        this.glassfade = 65;
        this.verydark = false;
        this.makefase = 0;
        this.darklevel = 0;
        this.shadowtrans = 80;
        this.changingsnap = false;
        this.snowheight = 0;
        this.cfade[0] = 255;
        this.icehills = false;
        this.greystage = 0;
        this.darken = false;
        this.effecttime = 0;
        this.trk = false;
        this.snowno = 0;
        this.darkenfor = 0;
        this.switchfase = 0;
        this.groundcolour = false;
        this.drowned = false;
        for (int a = 1; a < 3; ++a) {
            this.cfade[a] = 220;
        }
        this.fade[0] = 3000;
    }
    
    public void setfade(final int i, final int j, final int k) {
        this.fadecolour[0] = i;
        this.fadecolour[1] = j;
        this.fadecolour[2] = k;
        this.cfade[0] = (int)(i + i * (this.snap[0] / 100.0f));
        if (this.cfade[0] > 255) {
            this.cfade[0] = 255;
        }
        if (this.cfade[0] < 0) {
            this.cfade[0] = 0;
        }
        this.cfade[1] = (int)(j + j * (this.snap[1] / 100.0f));
        if (this.cfade[1] > 255) {
            this.cfade[1] = 255;
        }
        if (this.cfade[1] < 0) {
            this.cfade[1] = 0;
        }
        this.cfade[2] = (int)(k + k * (this.snap[2] / 100.0f));
        if (this.cfade[2] > 255) {
            this.cfade[2] = 255;
        }
        if (this.cfade[2] < 0) {
            this.cfade[2] = 0;
        }
        this.ocfade[0] = this.cfade[0];
        this.ocfade[1] = this.cfade[1];
        this.ocfade[2] = this.cfade[2];
    }
    
    public void d(final Graphics2D paramGraphics2D) {
        this.nsp = 0;
        if (this.zy > 90) {
            this.zy = 90;
        }
        if (this.zy < -90) {
            this.zy = -90;
        }
        if (this.xz > 360) {
            this.xz -= 360;
        }
        if (this.xz < 0) {
            this.xz += 360;
        }
        if (this.y > 0) {
            this.y = 0;
        }
        this.ground = 250 - this.y;
        if (this.greystage > 0) {
            for (int a = 0; a < 4; ++a) {
                if (this.greystage == a + 2) {
                    if (this.grndcolour[0] > 99 - a * 7) {
                        final int[] grndcolour = this.grndcolour;
                        final int n2 = 0;
                        --grndcolour[n2];
                        final int[] polycolour = this.polycolour;
                        final int n3 = 0;
                        --polycolour[n3];
                    }
                    if (this.grndcolour[1] > 186 - a * 36) {
                        final int[] grndcolour2 = this.grndcolour;
                        final int n4 = 1;
                        grndcolour2[n4] -= 3;
                        final int[] polycolour2 = this.polycolour;
                        final int n5 = 1;
                        polycolour2[n5] -= 3;
                    }
                    if (this.grndcolour[2] > 102 - a * 8) {
                        final int[] grndcolour3 = this.grndcolour;
                        final int n6 = 2;
                        --grndcolour3[n6];
                        final int[] polycolour3 = this.polycolour;
                        final int n7 = 2;
                        --polycolour3[n7];
                    }
                }
            }
            for (int a = 0; a < 3; ++a) {
                this.cgrnd[a] = (int)(this.grndcolour[a] + this.grndcolour[a] * (this.snap[a] / 100.0f));
                this.cpol[a] = (int)(this.polycolour[a] + this.polycolour[a] * (this.snap[a] / 100.0f));
                this.crgrnd[a] = (int)((this.cpol[a] * 0.99 + this.cgrnd[a]) / 2.0);
            }
        }
        if (this.effect[11]) {
            for (int a = 0; a < 3; ++a) {
                this.crgrnd[a] = this.cgrnd[a];
            }
        }
        if (this.effect[3]) {
            if (this.makefase > 250) {
                this.oneffect[3] = true;
            }
            else {
                ++this.makefase;
            }
            if (this.oneffect[3]) {
                ++this.effecttime;
                for (int a = 0; a < 3; ++a) {
                    this.cgrnd[a] = (int)(this.grndcolour[a] + this.grndcolour[a] * (this.snap[a] / 100.0f));
                    this.cpol[a] = (int)(this.polycolour[a] + this.polycolour[a] * (this.snap[a] / 100.0f));
                    this.crgrnd[a] = (int)((this.cpol[a] * 0.99 + this.cgrnd[a]) / 2.0);
                }
                if (this.effecttime > 0 && this.effecttime < 250) {
                    for (int a = 1; a < 3; ++a) {
                        if (this.grndcolour[a] > 150) {
                            final int[] grndcolour4 = this.grndcolour;
                            final int n8 = a;
                            grndcolour4[n8] -= 8;
                        }
                        if (this.polycolour[a] > 140) {
                            final int[] polycolour4 = this.polycolour;
                            final int n9 = a;
                            polycolour4[n9] -= 8;
                        }
                    }
                }
                if (this.effecttime >= 250 && this.effecttime < 500) {
                    if (this.grndcolour[0] > 150) {
                        final int[] grndcolour5 = this.grndcolour;
                        final int n10 = 0;
                        grndcolour5[n10] -= 8;
                    }
                    if (this.polycolour[0] > 140) {
                        final int[] polycolour5 = this.polycolour;
                        final int n11 = 0;
                        polycolour5[n11] -= 8;
                    }
                    if (this.grndcolour[2] < 230) {
                        final int[] grndcolour6 = this.grndcolour;
                        final int n12 = 2;
                        grndcolour6[n12] += 8;
                    }
                    if (this.polycolour[2] < 220) {
                        final int[] polycolour6 = this.polycolour;
                        final int n13 = 2;
                        polycolour6[n13] += 8;
                    }
                }
                if (this.effecttime >= 500 && this.effecttime < 750) {
                    if (this.grndcolour[2] > 150) {
                        final int[] grndcolour7 = this.grndcolour;
                        final int n14 = 2;
                        grndcolour7[n14] -= 8;
                    }
                    if (this.polycolour[2] > 140) {
                        final int[] polycolour7 = this.polycolour;
                        final int n15 = 2;
                        polycolour7[n15] -= 8;
                    }
                    if (this.grndcolour[0] < 230) {
                        final int[] grndcolour8 = this.grndcolour;
                        final int n16 = 0;
                        grndcolour8[n16] += 8;
                    }
                    if (this.polycolour[0] < 220) {
                        final int[] polycolour8 = this.polycolour;
                        final int n17 = 0;
                        polycolour8[n17] += 8;
                    }
                    if (this.grndcolour[1] < 190) {
                        final int[] grndcolour9 = this.grndcolour;
                        final int n18 = 1;
                        grndcolour9[n18] += 4;
                    }
                    if (this.polycolour[1] < 180) {
                        final int[] polycolour9 = this.polycolour;
                        final int n19 = 1;
                        polycolour9[n19] += 4;
                    }
                }
                if (this.effecttime >= 750 && this.effecttime < 1000) {
                    if (this.grndcolour[0] > 150) {
                        final int[] grndcolour10 = this.grndcolour;
                        final int n20 = 0;
                        grndcolour10[n20] -= 8;
                    }
                    if (this.polycolour[0] > 140) {
                        final int[] polycolour10 = this.polycolour;
                        final int n21 = 0;
                        polycolour10[n21] -= 8;
                    }
                    if (this.grndcolour[1] < 230) {
                        final int[] grndcolour11 = this.grndcolour;
                        final int n22 = 1;
                        grndcolour11[n22] += 4;
                    }
                    if (this.polycolour[1] < 220) {
                        final int[] polycolour11 = this.polycolour;
                        final int n23 = 1;
                        polycolour11[n23] += 4;
                    }
                }
                if (this.effecttime >= 1000) {
                    for (int a = 0; a < 3; ++a) {
                        if (a != 1) {
                            if (this.grndcolour[a] < 230) {
                                final int[] grndcolour12 = this.grndcolour;
                                final int n24 = a;
                                grndcolour12[n24] += 8;
                            }
                            if (this.polycolour[a] < 220) {
                                final int[] polycolour12 = this.polycolour;
                                final int n25 = a;
                                polycolour12[n25] += 8;
                            }
                        }
                    }
                    if (this.effecttime >= 1025) {
                        this.effecttime = 0;
                        this.oneffect[3] = false;
                        this.makefase = 0;
                    }
                }
            }
        }
        if (this.effect[2] && !this.trk) {
            if (this.makefase > 1000) {
                this.oneffect[2] = true;
            }
            else {
                ++this.makefase;
            }
            if (this.oneffect[2]) {
                ++this.effecttime;
                if (this.switchfase < 10) {
                    for (int a = 0; a < 3; ++a) {
                        final int[] snap = this.snap;
                        final int n26 = a;
                        snap[n26] -= 2;
                        this.csky[a] = (int)(this.skycolour[a] + this.skycolour[a] * (this.snap[a] / 100.0f));
                        this.cfade[a] = (int)(this.fadecolour[a] + this.fadecolour[a] * (this.snap[a] / 100.0f));
                        this.cgrnd[a] = (int)(this.grndcolour[a] + this.grndcolour[a] * (this.snap[a] / 100.0f));
                        this.cpol[a] = (int)(this.polycolour[a] + this.polycolour[a] * (this.snap[a] / 100.0f));
                        this.crgrnd[a] = (int)((this.cpol[a] * 0.99 + this.cgrnd[a]) / 2.0);
                    }
                    this.shadowtrans -= 8;
                    this.changingsnap = true;
                    ++this.switchfase;
                }
                else {
                    this.changingsnap = false;
                    this.verydark = true;
                }
                if (this.effecttime > 500) {
                    for (int a = 0; a < 3; ++a) {
                        if (this.snap[a] < this.osnap[a]) {
                            final int[] snap2 = this.snap;
                            final int n27 = a;
                            snap2[n27] += 2;
                            this.csky[a] = (int)(this.skycolour[a] + this.skycolour[a] * (this.snap[a] / 100.0f));
                            this.cfade[a] = (int)(this.fadecolour[a] + this.fadecolour[a] * (this.snap[a] / 100.0f));
                            this.cgrnd[a] = (int)(this.grndcolour[a] + this.grndcolour[a] * (this.snap[a] / 100.0f));
                            this.cpol[a] = (int)(this.polycolour[a] + this.polycolour[a] * (this.snap[a] / 100.0f));
                            this.crgrnd[a] = (int)((this.cpol[a] * 0.99 + this.cgrnd[a]) / 2.0);
                            this.changingsnap = true;
                            if (a == 0) {
                                this.shadowtrans += 8;
                            }
                        }
                        else {
                            this.verydark = false;
                            this.shadowtrans = 80;
                            this.changingsnap = false;
                            this.makefase = 0;
                            this.switchfase = 0;
                            this.oneffect[2] = false;
                            this.effecttime = 0;
                        }
                    }
                }
            }
        }
        final int[] arrayOfInt1 = new int[4];
        final int[] arrayOfInt2 = new int[4];
        int i = this.cgrnd[0];
        int j = this.cgrnd[1];
        int k = this.cgrnd[2];
        int m = this.crgrnd[0];
        int n = this.crgrnd[1];
        int i2 = this.crgrnd[2];
        int i3 = this.h;
        for (int i4 = 0; i4 < 16; ++i4) {
            int i5 = this.fade[i4];
            int groundloc = this.ground;
            if (this.effect[4]) {
                groundloc = this.ground + 2500;
            }
            int i6 = groundloc;
            if (this.zy != 0) {
                i6 = this.cy + (int)((groundloc - this.cy) * this.cos(this.zy) - (this.fade[i4] - this.cz) * this.sin(this.zy));
                i5 = this.cz + (int)((groundloc - this.cy) * this.sin(this.zy) + (this.fade[i4] - this.cz) * this.cos(this.zy));
            }
            arrayOfInt1[0] = this.iw;
            arrayOfInt2[0] = this.ys(i6, i5);
            if (arrayOfInt2[0] < this.ih) {
                arrayOfInt2[0] = this.ih;
            }
            if (arrayOfInt2[0] > this.h) {
                arrayOfInt2[0] = this.h;
            }
            arrayOfInt1[1] = this.iw;
            arrayOfInt2[1] = i3;
            arrayOfInt1[2] = this.w;
            arrayOfInt2[2] = i3;
            arrayOfInt1[3] = this.w;
            arrayOfInt2[3] = arrayOfInt2[0];
            i3 = arrayOfInt2[0];
            if (i4 > 0) {
                m = (m * 7 + this.cfade[0]) / 8;
                n = (n * 7 + this.cfade[1]) / 8;
                i2 = (i2 * 7 + this.cfade[2]) / 8;
                if (i4 < 3) {
                    i = (i * 7 + this.cfade[0]) / 8;
                    j = (j * 7 + this.cfade[1]) / 8;
                    k = (k * 7 + this.cfade[2]) / 8;
                }
                else {
                    i = m;
                    j = n;
                    k = i2;
                }
            }
            if (arrayOfInt2[0] < this.h && arrayOfInt2[1] > this.ih) {
                paramGraphics2D.setColor(new Color(i, j, k));
                paramGraphics2D.fillPolygon(arrayOfInt1, arrayOfInt2, 4);
            }
        }
        if (this.effect[0]) {
            ++this.makefase;
            if (this.makefase % 90 == 0) {
                this.oneffect[0] = true;
                this.makefase = 0;
            }
            if (this.oneffect[0]) {
                ++this.switchfase;
                if (this.switchfase > 30) {
                    this.switchfase = 30;
                }
                if (this.switchfase % 30 != 0) {
                    if (this.switchfase % 15 == 0) {
                        if (this.decrease) {
                            this.decrease = false;
                        }
                        else {
                            this.decrease = true;
                        }
                        this.glowfase = 1;
                    }
                }
                else {
                    this.glowfase = 2;
                }
                if (!this.decrease) {
                    final int[] polyoutline = this.polyoutline;
                    final int n28 = 0;
                    polyoutline[n28] += 15;
                }
                else if (this.polyoutline[0] >= 15) {
                    final int[] polyoutline2 = this.polyoutline;
                    final int n29 = 0;
                    polyoutline2[n29] -= 15;
                }
                if (this.glowfase == 2) {
                    this.polyoutline[0] = 0;
                    this.decrease = false;
                    this.switchfase = 0;
                    this.glowfase = 0;
                    this.oneffect[0] = false;
                }
            }
            this.polyoutline[1] = 0;
            this.polyoutline[2] = 0;
        }
        if (this.lightn != -1) {
            if (!this.effect[1]) {
                if (this.lightn < 16) {
                    if (this.lilo > this.lightn + 217) {
                        this.lilo -= 3;
                    }
                    else {
                        this.lightn = (int)(16.0f + 16.0f * this.random());
                    }
                }
                else if (this.lilo < this.lightn + 217) {
                    this.lilo += 7;
                }
                else {
                    this.lightn = (int)(16.0f * this.random());
                }
            }
            else {
                if (this.lightn < 16) {
                    if (this.lilo > this.lightn + 217) {
                        this.lilo -= 5;
                    }
                    else {
                        this.lightn = (int)(24.0f + 24.0f * this.random());
                    }
                }
                else if (this.lilo < this.lightn + 217) {
                    this.lilo += 12;
                }
                else {
                    this.lightn = (int)(24.0f * this.random());
                }
                for (int a2 = 0; a2 < 3; ++a2) {
                    this.cgrnd[a2] = (int)(this.lilo + this.lilo * (this.snap[a2] / 100.0f)) + 25;
                    if (this.cgrnd[a2] > 255) {
                        this.cgrnd[a2] = 255;
                    }
                    if (this.cgrnd[a2] < 0) {
                        this.cgrnd[a2] = 0;
                    }
                    this.cpol[a2] = this.cgrnd[a2] - 7;
                    if (this.cpol[a2] < 0) {
                        this.cpol[a2] = 0;
                    }
                    this.cfade[a2] = this.csky[a2] + 10;
                    if (this.cfade[a2] < 0) {
                        this.cpol[a2] = 0;
                    }
                }
            }
            for (int a2 = 0; a2 < 3; ++a2) {
                this.csky[a2] = (int)(this.lilo + this.lilo * (this.snap[a2] / 100.0f));
                if (this.csky[a2] > 255) {
                    this.csky[a2] = 255;
                }
                if (this.csky[a2] < 0) {
                    this.csky[a2] = 0;
                }
            }
        }
        i = this.csky[0];
        j = this.csky[1];
        k = this.csky[2];
        int i4 = i;
        int i5 = j;
        int i7 = k;
        int i8 = this.cy + (int)((this.skyline - 700 - this.cy) * this.cos(this.zy) - (7000 - this.cz) * this.sin(this.zy));
        final int i9 = this.cz + (int)((this.skyline - 700 - this.cy) * this.sin(this.zy) + (7000 - this.cz) * this.cos(this.zy));
        i8 = this.ys(i8, i9);
        int i10 = this.ih;
        for (int i11 = 0; i11 < 16; ++i11) {
            int i12 = this.fade[i11];
            int i13 = this.skyline;
            if (this.zy != 0) {
                i13 = this.cy + (int)((this.skyline - this.cy) * this.cos(this.zy) - (this.fade[i11] - this.cz) * this.sin(this.zy));
                i12 = this.cz + (int)((this.skyline - this.cy) * this.sin(this.zy) + (this.fade[i11] - this.cz) * this.cos(this.zy));
            }
            arrayOfInt1[0] = this.iw;
            arrayOfInt2[0] = this.ys(i13, i12);
            if (arrayOfInt2[0] > this.h) {
                arrayOfInt2[0] = this.h;
            }
            if (arrayOfInt2[0] < this.ih) {
                arrayOfInt2[0] = this.ih;
            }
            arrayOfInt1[1] = this.iw;
            arrayOfInt2[1] = i10;
            arrayOfInt1[2] = this.w;
            arrayOfInt2[2] = i10;
            arrayOfInt1[3] = this.w;
            arrayOfInt2[3] = arrayOfInt2[0];
            i10 = arrayOfInt2[0];
            if (i11 > 0) {
                i = (i * 7 + this.cfade[0]) / 8;
                j = (j * 7 + this.cfade[1]) / 8;
                k = (k * 7 + this.cfade[2]) / 8;
            }
            if (arrayOfInt2[1] < i8) {
                i4 = i;
                i5 = j;
                i7 = k;
            }
            if (arrayOfInt2[0] > this.ih && arrayOfInt2[1] < this.h) {
                paramGraphics2D.setColor(new Color(i, j, k));
                paramGraphics2D.fillPolygon(arrayOfInt1, arrayOfInt2, 4);
            }
        }
        arrayOfInt1[0] = this.iw;
        arrayOfInt2[0] = i10;
        arrayOfInt1[1] = this.iw;
        arrayOfInt2[1] = i3;
        arrayOfInt1[2] = this.w;
        arrayOfInt2[2] = i3;
        arrayOfInt1[3] = this.w;
        arrayOfInt2[3] = i10;
        if (arrayOfInt2[0] < this.h && arrayOfInt2[1] > this.ih) {
            float f = (Math.abs(this.y) - 250.0f) / (this.fade[0] * 2);
            if (f < 0.0f) {
                f = 0.0f;
            }
            if (f > 1.0f) {
                f = 1.0f;
            }
            i = (int)((i * (1.0f - f) + m * (1.0f + f)) / 2.0f);
            j = (int)((j * (1.0f - f) + n * (1.0f + f)) / 2.0f);
            k = (int)((k * (1.0f - f) + i2 * (1.0f + f)) / 2.0f);
            paramGraphics2D.setColor(new Color(i, j, k));
            paramGraphics2D.fillPolygon(arrayOfInt1, arrayOfInt2, 4);
        }
        if (this.resdown != 2) {
            for (int i14 = 1; i14 < 20; ++i14) {
                int i12 = 7000;
                int i13 = this.skyline - 700 - i14 * 70;
                if (this.zy != 0 && i14 != 19) {
                    i13 = this.cy + (int)((this.skyline - 700 - i14 * 70 - this.cy) * this.cos(this.zy) - (7000 - this.cz) * this.sin(this.zy));
                    i12 = this.cz + (int)((this.skyline - 700 - i14 * 70 - this.cy) * this.sin(this.zy) + (7000 - this.cz) * this.cos(this.zy));
                }
                arrayOfInt1[0] = this.iw;
                if (i14 != 19) {
                    arrayOfInt2[0] = this.ys(i13, i12);
                    if (arrayOfInt2[0] > this.h) {
                        arrayOfInt2[0] = this.h;
                    }
                    if (arrayOfInt2[0] < this.ih) {
                        arrayOfInt2[0] = this.ih;
                    }
                }
                else {
                    arrayOfInt2[0] = this.ih;
                }
                arrayOfInt1[1] = this.iw;
                arrayOfInt2[1] = i8;
                arrayOfInt1[2] = this.w;
                arrayOfInt2[2] = i8;
                arrayOfInt1[3] = this.w;
                arrayOfInt2[3] = arrayOfInt2[0];
                i8 = arrayOfInt2[0];
                i4 *= 0.991;  // cast: bytecode-verified
                i5 *= 0.991;  // cast: bytecode-verified
                i7 *= 0.998;  // cast: bytecode-verified
                if (arrayOfInt2[1] > this.ih && arrayOfInt2[0] < this.h) {
                    paramGraphics2D.setColor(new Color(i4, i5, i7));
                    paramGraphics2D.fillPolygon(arrayOfInt1, arrayOfInt2, 4);
                }
            }
            if (this.lightson && this.nst > 0) {
                this.drawstars(paramGraphics2D);
            }
            this.drawmountains(paramGraphics2D);
            this.drawclouds(paramGraphics2D);
        }
        if (!this.effect[10] && !this.effect[11]) {
            this.groundpolys(paramGraphics2D);
        }
        if (this.cpflik) {
            this.cpflik = false;
        }
        else {
            this.cpflik = true;
            this.elecr = this.random() * 15.0f - 6.0f;
        }
    }
    
    public void redrawpolys(final Graphics2D g) {
        final int[] arrayOfInt1 = new int[4];
        final int[] arrayOfInt2 = new int[4];
        final int[] fixgrnd = new int[3];
        for (int i = 0; i < 3; ++i) {
            fixgrnd[i] = (int)((this.cpol[i] * 0.99 + this.cgrnd[i]) / 2.0);
            this.crgrnd[i] = fixgrnd[i];
        }
        int i = this.cgrnd[0];
        int j = this.cgrnd[1];
        int k = this.cgrnd[2];
        int m = this.crgrnd[0];
        int n = this.crgrnd[1];
        int i2 = this.crgrnd[2];
        int i3 = this.h;
        for (int i4 = 0; i4 < 16; ++i4) {
            int i5 = this.fade[i4];
            int i6 = this.ground;
            if (this.zy != 0) {
                i6 = this.cy + (int)((this.ground - this.cy) * this.cos(this.zy) - (this.fade[i4] - this.cz) * this.sin(this.zy));
                i5 = this.cz + (int)((this.ground - this.cy) * this.sin(this.zy) + (this.fade[i4] - this.cz) * this.cos(this.zy));
            }
            arrayOfInt1[0] = this.iw;
            arrayOfInt2[0] = this.ys(i6, i5);
            if (arrayOfInt2[0] < this.ih) {
                arrayOfInt2[0] = this.ih;
            }
            if (arrayOfInt2[0] > this.h) {
                arrayOfInt2[0] = this.h;
            }
            arrayOfInt1[1] = this.iw;
            arrayOfInt2[1] = i3;
            arrayOfInt1[2] = this.w;
            arrayOfInt2[2] = i3;
            arrayOfInt1[3] = this.w;
            arrayOfInt2[3] = arrayOfInt2[0];
            i3 = arrayOfInt2[0];
            if (i4 > 0) {
                m = (m * 7 + this.cfade[0]) / 8;
                n = (n * 7 + this.cfade[1]) / 8;
                i2 = (i2 * 7 + this.cfade[2]) / 8;
                if (i4 < 3) {
                    i = (i * 7 + this.cfade[0]) / 8;
                    j = (j * 7 + this.cfade[1]) / 8;
                    k = (k * 7 + this.cfade[2]) / 8;
                }
                else {
                    i = m;
                    j = n;
                    k = i2;
                }
            }
            if (arrayOfInt2[0] < this.h && arrayOfInt2[1] > this.ih) {
                g.setColor(new Color(i, j, k, this.glassfade));
                g.fillPolygon(arrayOfInt1, arrayOfInt2, 4);
            }
        }
    }
    
    public void watch(final ContO paramContO, double paramInt1, final int boost) {
        this.zy = 10;
        double i = (2.0 + Math.abs(this.bcxzwatch) / 4.0) / 15.0;
        if (i > 1.3333333333333333) {
            i = 1.3333333333333333;
        }
        if (Math.abs(this.bcxzwatch) > i / 15.0) {
            if (this.bcxzwatch > 0.0) {
                this.bcxzwatch -= i / 15.0;
            }
            else {
                this.bcxzwatch += i / 15.0;
            }
        }
        else if (this.bcxzwatch != 0.0) {
            this.bcxzwatch = 0.0;
        }
        paramInt1 += this.bcxzwatch;
        this.xz = (int)(-paramInt1);
        System.out.println(new StringBuilder().append(this.xz).toString());
        this.x = paramContO.x - this.cx + (int)(-(paramContO.z - 800 - boost - paramContO.z) * Math.sin(paramInt1 * 3.141592653589793 / 180.0));
        this.z = paramContO.z - this.cz + (int)((paramContO.z - 12000 - boost - paramContO.z) * Math.cos(paramInt1 * 3.141592653589793 / 180.0));
        this.y = paramContO.y - 3750 - this.cy - boost;
    }
    
    public void rot(final int[] ai, final int[] ai1, final int i, final int j, final int k, final int l) {
        if (k != 0) {
            for (int i2 = 0; i2 < l; ++i2) {
                final int j2 = ai[i2];
                final int k2 = ai1[i2];
                ai[i2] = i + (int)((j2 - i) * this.cos(k) - (k2 - j) * this.sin(k));
                ai1[i2] = j + (int)((j2 - i) * this.sin(k) + (k2 - j) * this.cos(k));
            }
        }
    }
    
    public void setsnap(final int i, final int j, final int k) {
        this.snap[0] = i;
        this.snap[1] = j;
        this.snap[2] = k;
        this.osnap[0] = i;
        this.osnap[1] = j;
        this.osnap[2] = k;
    }
    
    public void around(final ContO paramContO, final boolean paramBoolean) {
        if (this.flex != 0) {
            this.flex = 0;
        }
        if (!paramBoolean) {
            if (!this.vert) {
                this.adv += 2;
            }
            else {
                this.adv -= 2;
            }
            if (this.adv > 900) {
                this.vert = true;
            }
            if (this.adv < 2000) {
                this.vert = false;
            }
        }
        else {
            this.adv -= 14;
        }
        int i = 2500;
        if (paramBoolean && i < 1300) {
            i = 1300;
        }
        if (i < 1000) {
            i = 1000;
        }
        this.y = paramContO.y - 2000;
        if (this.y > 10) {
            this.vert = false;
        }
        this.x = paramContO.x + (int)((paramContO.x - i - paramContO.x) * this.cos(this.vxz));
        this.z = paramContO.z + (int)((paramContO.x - i - paramContO.x) * this.sin(this.vxz));
        if (!paramBoolean) {
            this.vxz += 2;
        }
        else {
            this.vxz += 4;
        }
        int j = 0;
        int k = this.y;
        if (k > 0) {
            k = 0;
        }
        int number = paramContO.y - k - this.cy;
        if (number == 0) {
            number = 1;
        }
        if (number < 0) {
            j = 2300;
        }
        final int m = (int)Math.sqrt((paramContO.z - this.z + this.cz) * (paramContO.z - this.z + this.cz) + (paramContO.x - this.x - this.cx) * (paramContO.x - this.x - this.cx));
        int n = (int)(90 + j - Math.atan(m / number) / 0.017453292519943295);
        this.xz = -this.vxz + 90;
        if (paramBoolean) {
            n -= 15;
        }
        this.zy += (n - this.zy) / 10;
        if (this.trns != 5) {
            this.trns = 5;
        }
    }
    
    public void setgrnd(int i, final int j, final int k) {
        this.grndcolour[0] = i;
        this.grndcolour[1] = j;
        this.grndcolour[2] = k;
        this.cgrnd[0] = (int)(i + i * (this.snap[0] / 100.0f));
        if (this.cgrnd[0] > 255) {
            this.cgrnd[0] = 255;
        }
        if (this.cgrnd[0] < 0) {
            this.cgrnd[0] = 0;
        }
        this.cgrnd[1] = (int)(j + j * (this.snap[1] / 100.0f));
        if (this.cgrnd[1] > 255) {
            this.cgrnd[1] = 255;
        }
        if (this.cgrnd[1] < 0) {
            this.cgrnd[1] = 0;
        }
        this.cgrnd[2] = (int)(k + k * (this.snap[2] / 100.0f));
        if (this.cgrnd[2] > 255) {
            this.cgrnd[2] = 255;
        }
        if (this.cgrnd[2] < 0) {
            this.cgrnd[2] = 0;
        }
        this.dispolys = 2;
        for (i = 0; i < 3; ++i) {
            this.crgrnd[i] = (int)((this.cpol[i] * 0.99 + this.cgrnd[i]) / 2.0);
            this.ogrnd[i] = (int)((this.cpol[i] * 0.99 + this.cgrnd[i]) / 2.0);
        }
    }
    
    public int xs(final int i, int j) {
        if (j < this.cz) {
            j = this.cz;
        }
        return (j - this.focus_point) * (this.cx - i) / j + i;
    }
    
    public void adjstfade(final float f) {
        if (f < 15.0f) {
            this.fade[0] = (int)(this.origfade - 1000.0f * (15.0f - f));
            if (this.fade[0] < 3000) {
                this.fade[0] = 3000;
            }
            this.fadfrom(this.fade[0]);
        }
        else if (this.fade[0] != this.origfade) {
            final int[] fade = this.fade;
            final int n = 0;
            fade[n] += 500;
            if (this.fade[0] > this.origfade) {
                this.fade[0] = this.origfade;
            }
            this.fadfrom(this.fade[0]);
        }
    }
    
    public void addsp(final int i, final int j, final int k) {
        if (this.nsp != 101) {
            this.spx[this.nsp] = i;
            this.spz[this.nsp] = j;
            this.sprad[this.nsp] = k;
            ++this.nsp;
        }
    }
    
    public void aroundtrack(final CheckPoints checkpoints) {
        this.y = -this.hit;
        if (this.effect[9]) {
            this.y = -this.hit - 30000;
        }
        this.x = this.cx + (int)this.trx + (int)(17000.0f * this.cos(this.vxz));
        this.z = (int)this.trz + (int)(17000.0f * this.sin(this.vxz));
        if (this.hit > 5000) {
            if (this.hit == 25000) {
                this.fo = 1.0f;
                this.zy = 67;
                this.atrx = (checkpoints.x[0] - this.trx) / 116L;
                this.atrz = (checkpoints.z[0] - this.trz) / 116L;
                this.focus_point = 400;
            }
            this.hit -= this.fallen;
            this.fallen += 7;
            this.trx += this.atrx;
            this.trz += this.atrz;
            if (this.hit < 17600) {
                this.zy -= 2;
            }
            if (this.fallen > 500) {
                this.fallen = 500;
            }
            if (this.hit <= 5000) {
                this.hit = 5000;
                this.fallen = 0;
            }
            this.vxz += 3;
        }
        else {
            this.focus_point = (int)(400.0f * this.fo);
            if (Math.abs(this.fo - this.gofo) > 0.005) {
                if (this.fo < this.gofo) {
                    this.fo += 0.005f;
                }
                else {
                    this.fo -= 0.005f;
                }
            }
            else {
                this.gofo = (float)(0.3499999940395355 + Math.random() * 1.3);
            }
            ++this.vxz;
            this.trx -= (this.trx - checkpoints.x[this.ptr]) / 10L;
            this.trz -= (this.trz - checkpoints.z[this.ptr]) / 10L;
            if (this.ptcnt == 7) {
                ++this.ptr;
                if (this.ptr >= checkpoints.n) {
                    this.ptr = 0;
                    ++this.nrnd;
                }
                this.ptcnt = 0;
            }
            else {
                ++this.ptcnt;
            }
        }
        if (this.vxz > 360) {
            this.vxz -= 360;
        }
        this.xz = -this.vxz - 90;
        char c = '\0';
        if (-this.y - this.cy < 0) {
            c = '\uff4c';
        }
        Math.sqrt((double)((this.trz - this.z + this.cz) * (this.trz - this.z + this.cz) + (this.trx - this.x - this.cx) * (this.trx - this.x - this.cx)));
        if (this.cpflik) {
            this.cpflik = false;
        }
        else {
            this.cpflik = true;
        }
    }
    
    public void setsky(final int paramInt1, final int paramInt2, final int paramInt3) {
        this.osky[0] = paramInt1;
        this.osky[1] = paramInt2;
        this.osky[2] = paramInt3;
        this.skycolour[0] = paramInt1;
        this.skycolour[1] = paramInt2;
        this.skycolour[2] = paramInt3;
        for (int i = 0; i < 3; ++i) {
            this.clds[i] = (this.osky[i] * this.cldd[3] + this.cldd[i]) / (this.cldd[3] + 1);
            this.clds[i] += (this.clds[i] * (this.snap[i] / 100.0f));  // cast: bytecode-verified
            if (this.clds[i] > 255) {
                this.clds[i] = 255;
            }
            if (this.clds[i] < 0) {
                this.clds[i] = 0;
            }
        }
        this.csky[0] = (int)(paramInt1 + paramInt1 * (this.snap[0] / 100.0f));
        if (this.csky[0] > 255) {
            this.csky[0] = 255;
        }
        if (this.csky[0] < 0) {
            this.csky[0] = 0;
        }
        this.csky[1] = (int)(paramInt2 + paramInt2 * (this.snap[1] / 100.0f));
        if (this.csky[1] > 255) {
            this.csky[1] = 255;
        }
        if (this.csky[1] < 0) {
            this.csky[1] = 0;
        }
        this.csky[2] = (int)(paramInt3 + paramInt3 * (this.snap[2] / 100.0f));
        if (this.csky[2] > 255) {
            this.csky[2] = 255;
        }
        if (this.csky[2] < 0) {
            this.csky[2] = 0;
        }
        final float[] arrayOfFloat = new float[3];
        Color.RGBtoHSB(this.csky[0], this.csky[1], this.csky[2], arrayOfFloat);
        this.ocsky[0] = this.csky[0];
        this.ocsky[1] = this.csky[1];
        this.ocsky[2] = this.csky[2];
    }
    
    public void fadfrom(int paramInt) {
        if (paramInt > 12000) {
            paramInt = 12000;
        }
        for (int i = 0; i < 16; ++i) {
            this.fade[i] = paramInt / 2 * (i + 2);
            this.originalfade[i] = paramInt / 2 * (i + 2);
        }
    }
    
    public void follow(final ContO paramContO, int paramInt1, final int paramInt2, final int boost) {
        this.zy = 10;
        int i = 2 + Math.abs(this.bcxz) / 4;
        if (i > 20) {
            i = 20;
        }
        if (paramInt2 != 0) {
            if (paramInt2 == 1) {
                if (this.bcxz < 180) {
                    this.bcxz += i;
                }
                if (this.bcxz > 180) {
                    this.bcxz = 180;
                }
            }
            if (paramInt2 == -1) {
                if (this.bcxz > -180) {
                    this.bcxz -= i;
                }
                if (this.bcxz < -180) {
                    this.bcxz = -180;
                }
            }
        }
        else if (Math.abs(this.bcxz) > i) {
            if (this.bcxz > 0) {
                this.bcxz -= i;
            }
            else {
                this.bcxz += i;
            }
        }
        else if (this.bcxz != 0) {
            this.bcxz = 0;
        }
        paramInt1 += this.bcxz;
        this.xz = -paramInt1;
        this.x = paramContO.x - this.cx + (int)(-(paramContO.z - 800 - boost - paramContO.z) * this.sin(paramInt1));
        this.z = paramContO.z - this.cz + (int)((paramContO.z - 800 - boost - paramContO.z) * this.cos(paramInt1));
        this.y = paramContO.y - 250 - this.cy - boost;
    }
    
    public void newpolys(final int i, final int j, final int k, final int l, final Trackers trackers) {
        this.nrw = j / (int)(1200.0 * this.reducepolys) + 1;
        this.ncl = l / (int)(1200.0 * this.reducepolys) + 1;
        this.sgpx = i;
        this.sgpz = k;
        int i2 = 0;
        int j2 = 0;
        for (int k2 = 0; k2 < this.nrw * this.ncl; ++k2) {
            this.cgpx[k2] = i + (int)(i2 * 1200 * this.reducepolys) + (int)(Math.random() * 1000.0 - 500.0);
            this.cgpz[k2] = k + (int)(j2 * 1200 * this.reducepolys) + (int)(Math.random() * 1000.0 - 500.0);
            for (int i3 = 0; i3 < trackers.nt; ++i3) {
                if (trackers.zy[i3] == 0 && trackers.xy[i3] == 0) {
                    if (trackers.radx[i3] < trackers.radz[i3] && Math.abs(this.cgpz[k2] - trackers.z[i3]) < trackers.radz[i3]) {
                        while (Math.abs(this.cgpx[k2] - trackers.x[i3]) < trackers.radx[i3]) {
                            final int[] cgpx = this.cgpx;
                            final int n = k2;
                            cgpx[n] += (Math.random() * trackers.radx[i3] * 2.0 - trackers.radx[i3]);  // cast: bytecode-verified
                        }
                    }
                    if (trackers.radz[i3] < trackers.radx[i3] && Math.abs(this.cgpx[k2] - trackers.x[i3]) < trackers.radx[i3]) {
                        while (Math.abs(this.cgpz[k2] - trackers.z[i3]) < trackers.radz[i3]) {
                            final int[] cgpz = this.cgpz;
                            final int n2 = k2;
                            cgpz[n2] += (Math.random() * trackers.radz[i3] * 2.0 - trackers.radz[i3]);  // cast: bytecode-verified
                        }
                    }
                }
            }
            if (++i2 == this.nrw) {
                i2 = 0;
                ++j2;
            }
        }
        final double polysize = 1.0;
        for (int l2 = 0; l2 < this.nrw * this.ncl; ++l2) {
            this.ogpx[l2][0] = 0;
            this.ogpz[l2][0] = (int)(100.0 + Math.random() * 600.0 * polysize);
            this.ogpx[l2][1] = (int)((100.0 + Math.random() * 600.0) * 0.7071 * polysize);
            this.ogpz[l2][1] = this.ogpx[l2][1];
            this.ogpx[l2][2] = (int)(100.0 + Math.random() * 600.0 * polysize);
            this.ogpz[l2][2] = 0;
            this.ogpx[l2][3] = (int)((100.0 + Math.random() * 600.0) * 0.7071 * polysize);
            this.ogpz[l2][3] = -this.ogpx[l2][3];
            this.ogpx[l2][4] = 0;
            this.ogpz[l2][4] = -(int)(100.0 + Math.random() * 600.0 * polysize);
            this.ogpx[l2][5] = -(int)((100.0 + Math.random() * 600.0) * 0.7071 * polysize);
            this.ogpz[l2][5] = this.ogpx[l2][5];
            this.ogpx[l2][6] = -(int)(100.0 + Math.random() * 600.0 * polysize);
            this.ogpz[l2][6] = 0;
            this.ogpx[l2][7] = -(int)((100.0 + Math.random() * 600.0) * 0.7071 * polysize);
            this.ogpz[l2][7] = -this.ogpx[l2][7];
        }
    }
    
    public void transaround(final ContO paramContO1, final ContO paramContO2, final int paramInt) {
        if (this.flex != 0) {
            this.flex = 0;
        }
        final int i = (paramContO1.x * (20 - paramInt) + paramContO2.x * paramInt) / 20;
        final int j = (paramContO1.y * (20 - paramInt) + paramContO2.y * paramInt) / 20;
        final int k = (paramContO1.z * (20 - paramInt) + paramContO2.z * paramInt) / 20;
        if (!this.vert) {
            this.adv += 2;
        }
        else {
            this.adv -= 2;
        }
        if (this.adv > 900) {
            this.vert = true;
        }
        if (this.adv < 2000) {
            this.vert = false;
        }
        int m = 2500;
        if (m < 1000) {
            m = 1000;
        }
        this.y = j - 2000;
        if (this.y > 10) {
            this.vert = false;
        }
        this.x = i + (int)((i - m - i) * this.cos(this.vxz));
        this.z = k + (int)((i - m - i) * this.sin(this.vxz));
        this.vxz += 2;
        int n = 0;
        int i2 = this.y;
        if (i2 > 0) {
            i2 = 0;
        }
        int number = j - i2 - this.cy;
        if (number == 0) {
            number = 1;
        }
        if (number < 0) {
            n = 2300;
        }
        final int i3 = (int)Math.sqrt((k - this.z + this.cz) * (k - this.z + this.cz) + (i - this.x - this.cx) * (i - this.x - this.cx));
        final int i4 = (int)(90 + n - Math.atan(i3 / number) / 0.017453292519943295);
        this.xz = -this.vxz + 90;
        this.zy += (i4 - this.zy) / 10;
        if (this.trns != 5) {
            this.trns = 5;
        }
    }
    
    public float cos(int i) {
        while (i >= 360) {
            i -= 360;
        }
        while (i < 0) {
            i += 360;
        }
        return this.tcos[i];
    }
}
