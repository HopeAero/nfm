import java.awt.Color;

// 
// Decompiled by Procyon v0.6.0
// 

public class Record
{
    Medium m;
    int caught;
    boolean hcaught;
    boolean prepit;
    ContO[] ocar;
    int cntf;
    ContO[][] car;
    int[][] squash;
    int[] fix;
    int[] dest;
    int[][] x;
    int[][] y;
    int[][] z;
    int[][] xy;
    int[][] zy;
    int[][] xz;
    int[][] wxz;
    int[][] wzy;
    int[][] ns;
    int[][][] sspark;
    int[][][] sx;
    int[][][] sy;
    int[][][] sz;
    float[][][] smag;
    int[][][] scx;
    int[][][] scz;
    boolean[][][] fulls;
    int[][] nry;
    int[][][] ry;
    int[][][] magy;
    boolean[][] mtouch;
    int[][] nrx;
    int[][][] rx;
    int[][][] magx;
    int[][] nrz;
    int[][][] rz;
    int[][][] magz;
    int[] checkpoint;
    boolean[] lastcheck;
    int wasted;
    int whenwasted;
    int powered;
    int closefinish;
    ContO[] starcar;
    int[] hsquash;
    int[] hfix;
    int[] hdest;
    int[][] hx;
    int[][] hy;
    int[][] hz;
    int[][] hxy;
    int[][] hzy;
    int[][] hxz;
    int[][] hwxz;
    int[][] hwzy;
    int[][] hns;
    int[][][] hsspark;
    int[][][] hsx;
    int[][][] hsy;
    int[][][] hsz;
    float[][][] hsmag;
    int[][][] hscx;
    int[][][] hscz;
    boolean[][][] hfulls;
    int[][] hnry;
    int[][][] hry;
    int[][][] hmagy;
    int[][] hnrx;
    int[][][] hrx;
    int[][][] hmagx;
    int[][] hnrz;
    int[][][] hrz;
    int[][][] hmagz;
    boolean[][] hmtouch;
    int[] hcheckpoint;
    boolean[] hlastcheck;
    int[] cntdest;
    int lastfr;
    
    public void regy(final int i, float f, final boolean flag, final ContO conto, final Madness madness) {
        if (f > 100.0f) {
            f -= 100.0f;
            byte byte0 = 0;
            byte byte2 = 0;
            int j = conto.zy;
            int k = conto.xy;
            while (j < 360) {
                j += 360;
            }
            while (j > 360) {
                j -= 360;
            }
            if (j < 210 && j > 150) {
                byte0 = -1;
            }
            if (j > 330 || j < 30) {
                byte0 = 1;
            }
            while (k < 360) {
                k += 360;
            }
            while (k > 360) {
                k -= 360;
            }
            if (k < 210 && k > 150) {
                byte2 = -1;
            }
            if (k > 330 || k < 30) {
                byte2 = 1;
            }
            if (byte2 * byte0 == 0 || flag) {
                for (int l = 0; l < conto.npl; ++l) {
                    float f2 = 0.0f;
                    for (int k2 = 0; k2 < conto.p[l].n; ++k2) {
                        if (conto.p[l].wz == 0 && this.py(conto.keyx[i], conto.p[l].ox[k2], conto.keyz[i], conto.p[l].oz[k2]) < madness.clrad[madness.cn]) {
                            f2 = f / 20.0f * this.m.random();
                            final int[] oz = conto.p[l].oz;
                            final int n = k2;
                            oz[n] += (f2 * this.m.sin(j));  // cast: bytecode-verified
                            final int[] ox = conto.p[l].ox;
                            final int n2 = k2;
                            ox[n2] -= (f2 * this.m.sin(k));  // cast: bytecode-verified
                        }
                    }
                    if (f2 != 0.0f) {
                        if (Math.abs(f2) >= 1.0f) {
                            conto.p[l].chip = 1;
                            conto.p[l].ctmag = f2;
                        }
                        if (!conto.p[l].nocol && conto.p[l].glass != 1) {
                            if (conto.p[l].bfase > 20 && conto.p[l].hsb[1] > 0.2) {
                                conto.p[l].hsb[1] = 0.2f;
                            }
                            if (conto.p[l].bfase > 30) {
                                if (conto.p[l].hsb[2] < 0.5) {
                                    conto.p[l].hsb[2] = 0.5f;
                                }
                                if (conto.p[l].hsb[1] > 0.1) {
                                    conto.p[l].hsb[1] = 0.1f;
                                }
                            }
                            if (conto.p[l].bfase > 40) {
                                conto.p[l].hsb[1] = 0.05f;
                            }
                            if (conto.p[l].bfase > 50) {
                                if (conto.p[l].hsb[2] > 0.8) {
                                    conto.p[l].hsb[2] = 0.8f;
                                }
                                conto.p[l].hsb[0] = 0.075f;
                                conto.p[l].hsb[1] = 0.05f;
                            }
                            if (conto.p[l].bfase > 60) {
                                conto.p[l].hsb[0] = 0.05f;
                            }
                            final Plane plane = conto.p[l];
                            plane.bfase += f2;  // cast: bytecode-verified
                            new Color(conto.p[l].c[0], conto.p[l].c[1], conto.p[l].c[2]);
                            final Color color = Color.getHSBColor(conto.p[l].hsb[0], conto.p[l].hsb[1], conto.p[l].hsb[2]);
                            conto.p[l].c[0] = color.getRed();
                            conto.p[l].c[1] = color.getGreen();
                            conto.p[l].c[2] = color.getBlue();
                        }
                        if (conto.p[l].glass == 1) {
                            final Plane plane2 = conto.p[l];
                            plane2.gr += Math.abs(f2 * 1.5);  // cast: bytecode-verified
                        }
                    }
                }
            }
            if (byte2 * byte0 == -1) {
                int i2 = 0;
                int j2 = 1;
                for (int l2 = 0; l2 < conto.npl; ++l2) {
                    float f3 = 0.0f;
                    for (int i3 = 0; i3 < conto.p[l2].n; ++i3) {
                        if (conto.p[l2].wz == 0) {
                            f3 = f / 15.0f * this.m.random();
                            if ((Math.abs(conto.p[l2].oy[i3] - madness.flipy[madness.cn] - this.squash[0][madness.im]) < madness.msquash[madness.cn] * 3 || conto.p[l2].oy[i3] < madness.flipy[madness.cn] + this.squash[0][madness.im]) && this.squash[0][madness.im] < madness.msquash[madness.cn]) {
                                final int[] oy = conto.p[l2].oy;
                                final int n3 = i3;
                                oy[n3] += f3;  // cast: bytecode-verified
                                i2 += f3;  // cast: bytecode-verified
                                ++j2;
                            }
                        }
                    }
                    if (conto.p[l2].glass == 1) {
                        final Plane plane3 = conto.p[l2];
                        plane3.gr += 5;
                    }
                    else if (f3 != 0.0f) {
                        final Plane plane4 = conto.p[l2];
                        plane4.bfase += f3;  // cast: bytecode-verified
                    }
                    if (Math.abs(f3) >= 1.0f) {
                        conto.p[l2].chip = 1;
                        conto.p[l2].ctmag = f3;
                    }
                }
                final int[] array = this.squash[0];
                final int im = madness.im;
                array[im] += i2 / j2;
            }
        }
    }
    
    public void reset(final ContO[] aconto, final int ncars) {
        this.caught = 0;
        this.hcaught = false;
        this.wasted = 0;
        this.whenwasted = 0;
        this.closefinish = 0;
        this.powered = 0;
        int i = 0;
        do {
            if (this.prepit) {
                this.starcar[i] = new ContO(aconto[i], 0, 0, 0, 0);
            }
            this.fix[i] = -1;
            this.dest[i] = -1;
            this.cntdest[i] = 0;
        } while (++i < ncars);
        i = 0;
        do {
            int j = 0;
            do {
                this.car[i][j] = new ContO(aconto[j], 0, 0, 0, 0);
                this.squash[i][j] = 0;
            } while (++j < ncars);
        } while (++i < 6);
        i = 0;
        do {
            int k = 0;
            do {
                int l = 0;
                do {
                    this.sspark[i][k][l] = -1;
                    this.ns[i][k] = 0;
                } while (++l < 30);
                l = 0;
                do {
                    this.ry[i][k][l] = -1;
                    this.nry[i][k] = 0;
                    this.rx[i][k][l] = -1;
                    this.nrx[i][k] = 0;
                    this.rz[i][k][l] = -1;
                    this.nrz[i][k] = 0;
                } while (++l < 7);
            } while (++k < 4);
        } while (++i < ncars);
        this.prepit = false;
    }
    
    public Record(final Medium medium, final int ncars) {
        this.hfix = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
        this.hdest = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
        this.caught = 0;
        this.hcaught = false;
        this.prepit = true;
        this.ocar = new ContO[101];
        this.cntf = 50;
        this.car = new ContO[6][101];
        this.squash = new int[6][101];
        this.fix = new int[101];
        this.dest = new int[101];
        this.x = new int[300][101];
        this.y = new int[300][101];
        this.z = new int[300][101];
        this.xy = new int[300][101];
        this.zy = new int[300][101];
        this.xz = new int[300][101];
        this.wxz = new int[300][101];
        this.wzy = new int[300][101];
        this.ns = new int[101][4];
        this.sspark = new int[101][4][30];
        this.sx = new int[101][4][30];
        this.sy = new int[101][4][30];
        this.sz = new int[101][4][30];
        this.smag = new float[101][4][30];
        this.scx = new int[101][4][30];
        this.scz = new int[101][4][30];
        this.fulls = new boolean[101][4][30];
        this.nry = new int[101][4];
        this.ry = new int[101][4][7];
        this.magy = new int[101][4][7];
        this.mtouch = new boolean[101][7];
        this.nrx = new int[101][4];
        this.rx = new int[101][4][7];
        this.magx = new int[101][4][7];
        this.nrz = new int[101][4];
        this.rz = new int[101][4][7];
        this.magz = new int[101][4][7];
        this.checkpoint = new int[300];
        this.lastcheck = new boolean[300];
        this.wasted = 0;
        this.whenwasted = 0;
        this.powered = 0;
        this.closefinish = 0;
        this.starcar = new ContO[101];
        this.hsquash = new int[101];
        this.hx = new int[300][101];
        this.hy = new int[300][101];
        this.hz = new int[300][101];
        this.hxy = new int[300][101];
        this.hzy = new int[300][101];
        this.hxz = new int[300][101];
        this.hwxz = new int[300][101];
        this.hwzy = new int[300][101];
        this.hns = new int[101][4];
        this.hsspark = new int[101][4][30];
        this.hsx = new int[101][4][30];
        this.hsy = new int[101][4][30];
        this.hsz = new int[101][4][30];
        this.hsmag = new float[101][4][30];
        this.hscx = new int[101][4][30];
        this.hscz = new int[101][4][30];
        this.hfulls = new boolean[101][4][30];
        this.hnry = new int[101][4];
        this.hry = new int[101][4][7];
        this.hmagy = new int[101][4][7];
        this.hnrx = new int[101][4];
        this.hrx = new int[101][4][7];
        this.hmagx = new int[101][4][7];
        this.hnrz = new int[101][4];
        this.hrz = new int[101][4][7];
        this.hmagz = new int[101][4][7];
        this.hmtouch = new boolean[101][7];
        this.hcheckpoint = new int[300];
        this.hlastcheck = new boolean[300];
        this.cntdest = new int[101];
        this.lastfr = 0;
        this.m = medium;
        this.cotchinow(this.caught = 0, ncars);
    }
    
    public void playh(final ContO conto, final Madness madness, final int i, final int j) {
        conto.x = this.hx[j][i];
        conto.y = this.hy[j][i];
        conto.z = this.hz[j][i];
        conto.zy = this.hzy[j][i];
        conto.xy = this.hxy[j][i];
        conto.xz = this.hxz[j][i];
        conto.wxz = this.hwxz[j][i];
        conto.wzy = this.hwzy[j][i];
        if (i == 0) {
            conto.m.checkpoint = this.hcheckpoint[j];
            conto.m.lastcheck = this.hlastcheck[j];
        }
        if (j == 0) {
            this.cntdest[i] = 0;
        }
        if (this.hdest[i] == j) {
            this.cntdest[i] = 7;
        }
        if (j == 0 && this.hdest[i] < -1) {
            for (int k = 0; k < conto.npl; ++k) {
                if (conto.p[k].wz == 0 || conto.p[k].gr == -17 || conto.p[k].gr == -16) {
                    conto.p[k].embos = 13;
                }
            }
        }
        if (this.cntdest[i] != 0) {
            for (int l = 0; l < conto.npl; ++l) {
                if (conto.p[l].wz == 0 || conto.p[l].gr == -17 || conto.p[l].gr == -16) {
                    conto.p[l].embos = 1;
                }
            }
            final int[] cntdest = this.cntdest;
            --cntdest[i];
        }
        int i2 = 0;
        do {
            int j2 = 0;
            do {
                if (this.hsspark[i][i2][j2] == j) {
                    conto.stg[i2] = 1;
                    conto.dov[i2] = -1;
                    conto.sx[i2] = this.hsx[i][i2][j2];
                    conto.sy[i2] = this.hsy[i][i2][j2];
                    conto.sz[i2] = this.hsz[i][i2][j2];
                    conto.smag[i2] = this.hsmag[i][i2][j2];
                    conto.scx[i2] = this.hscx[i][i2][j2];
                    conto.scz[i2] = this.hscz[i][i2][j2];
                    conto.fulls[i2] = this.hfulls[i][i2][j2];
                }
            } while (++j2 < 30);
            j2 = 0;
            do {
                if (this.hry[i][i2][j2] == j && this.lastfr != j) {
                    this.regy(i2, (float)this.hmagy[i][i2][j2], this.hmtouch[i][j2], conto, madness);
                }
                if (this.hrx[i][i2][j2] == j) {
                    if (this.lastfr != j) {
                        this.regx(i2, (float)this.hmagx[i][i2][j2], conto, madness);
                    }
                    else {
                        this.chipx(i2, (float)this.hmagx[i][i2][j2], conto, madness);
                    }
                }
                if (this.hrz[i][i2][j2] == j) {
                    if (this.lastfr != j) {
                        this.regz(i2, (float)this.hmagz[i][i2][j2], conto, madness);
                    }
                    else {
                        this.chipz(i2, (float)this.hmagz[i][i2][j2], conto, madness);
                    }
                }
            } while (++j2 < 7);
        } while (++i2 < 4);
        this.lastfr = j;
    }
    
    public void chipz(final int i, float f, final ContO conto, final Madness madness) {
        if (Math.abs(f) > 100.0f) {
            if (f > 100.0f) {
                f -= 100.0f;
            }
            if (f < -100.0f) {
                f += 100.0f;
            }
            for (int j = 0; j < conto.npl; ++j) {
                float f2 = 0.0f;
                for (int k = 0; k < conto.p[j].n; ++k) {
                    if (conto.p[j].wz == 0 && this.py(conto.keyx[i], conto.p[j].ox[k], conto.keyz[i], conto.p[j].oz[k]) < madness.clrad[madness.cn]) {
                        f2 = f / 20.0f * this.m.random();
                    }
                }
                if (f2 != 0.0f && Math.abs(f2) >= 1.0f) {
                    conto.p[j].chip = 1;
                    conto.p[j].ctmag = f2;
                }
            }
        }
    }
    
    public void regz(final int i, float f, final ContO conto, final Madness madness) {
        if (Math.abs(f) > 100.0f) {
            if (f > 100.0f) {
                f -= 100.0f;
            }
            if (f < -100.0f) {
                f += 100.0f;
            }
            for (int j = 0; j < conto.npl; ++j) {
                float f2 = 0.0f;
                for (int k = 0; k < conto.p[j].n; ++k) {
                    if (conto.p[j].wz == 0 && this.py(conto.keyx[i], conto.p[j].ox[k], conto.keyz[i], conto.p[j].oz[k]) < madness.clrad[madness.cn]) {
                        f2 = f / 20.0f * this.m.random();
                        final int[] oz = conto.p[j].oz;
                        final int n = k;
                        oz[n] += (f2 * this.m.cos(conto.xz) * this.m.cos(conto.zy));  // cast: bytecode-verified
                        final int[] ox = conto.p[j].ox;
                        final int n2 = k;
                        ox[n2] += (f2 * this.m.sin(conto.xz) * this.m.cos(conto.xy));  // cast: bytecode-verified
                    }
                }
                if (f2 != 0.0f) {
                    if (Math.abs(f2) >= 1.0f) {
                        conto.p[j].chip = 1;
                        conto.p[j].ctmag = f2;
                    }
                    if (!conto.p[j].nocol && conto.p[j].glass != 1) {
                        if (conto.p[j].bfase > 20 && conto.p[j].hsb[1] > 0.2) {
                            conto.p[j].hsb[1] = 0.2f;
                        }
                        if (conto.p[j].bfase > 30) {
                            if (conto.p[j].hsb[2] < 0.5) {
                                conto.p[j].hsb[2] = 0.5f;
                            }
                            if (conto.p[j].hsb[1] > 0.1) {
                                conto.p[j].hsb[1] = 0.1f;
                            }
                        }
                        if (conto.p[j].bfase > 40) {
                            conto.p[j].hsb[1] = 0.05f;
                        }
                        if (conto.p[j].bfase > 50) {
                            if (conto.p[j].hsb[2] > 0.8) {
                                conto.p[j].hsb[2] = 0.8f;
                            }
                            conto.p[j].hsb[0] = 0.075f;
                            conto.p[j].hsb[1] = 0.05f;
                        }
                        if (conto.p[j].bfase > 60) {
                            conto.p[j].hsb[0] = 0.05f;
                        }
                        final Plane plane = conto.p[j];
                        plane.bfase += Math.abs(f2);  // cast: bytecode-verified
                        new Color(conto.p[j].c[0], conto.p[j].c[1], conto.p[j].c[2]);
                        final Color color = Color.getHSBColor(conto.p[j].hsb[0], conto.p[j].hsb[1], conto.p[j].hsb[2]);
                        conto.p[j].c[0] = color.getRed();
                        conto.p[j].c[1] = color.getGreen();
                        conto.p[j].c[2] = color.getBlue();
                    }
                    if (conto.p[j].glass == 1) {
                        final Plane plane2 = conto.p[j];
                        plane2.gr += Math.abs(f2 * 1.5);  // cast: bytecode-verified
                    }
                }
            }
        }
    }
    
    public void play(final ContO conto, final Madness madness, final int i, final int j) {
        conto.x = this.x[j][i];
        conto.y = this.y[j][i];
        conto.z = this.z[j][i];
        conto.zy = this.zy[j][i];
        conto.xy = this.xy[j][i];
        conto.xz = this.xz[j][i];
        conto.wxz = this.wxz[j][i];
        conto.wzy = this.wzy[j][i];
        if (i == 0) {
            conto.m.checkpoint = this.checkpoint[j];
            conto.m.lastcheck = this.lastcheck[j];
        }
        if (j == 0) {
            this.cntdest[i] = 0;
        }
        if (this.dest[i] == j) {
            this.cntdest[i] = 7;
        }
        if (j == 0 && this.dest[i] < -1) {
            for (int k = 0; k < conto.npl; ++k) {
                if (conto.p[k].wz == 0 || conto.p[k].gr == -17 || conto.p[k].gr == -16) {
                    conto.p[k].embos = 13;
                }
            }
        }
        if (this.cntdest[i] != 0) {
            for (int l = 0; l < conto.npl; ++l) {
                if (conto.p[l].wz == 0 || conto.p[l].gr == -17 || conto.p[l].gr == -16) {
                    conto.p[l].embos = 1;
                }
            }
            final int[] cntdest = this.cntdest;
            --cntdest[i];
        }
        int i2 = 0;
        do {
            int j2 = 0;
            do {
                if (this.sspark[i][i2][j2] == j) {
                    conto.stg[i2] = 1;
                    conto.dov[i2] = -1;
                    conto.sx[i2] = this.sx[i][i2][j2];
                    conto.sy[i2] = this.sy[i][i2][j2];
                    conto.sz[i2] = this.sz[i][i2][j2];
                    conto.smag[i2] = this.smag[i][i2][j2];
                    conto.scx[i2] = this.scx[i][i2][j2];
                    conto.scz[i2] = this.scz[i][i2][j2];
                    conto.fulls[i2] = this.fulls[i][i2][j2];
                }
            } while (++j2 < 30);
            j2 = 0;
            do {
                if (this.ry[i][i2][j2] == j) {
                    this.regy(i2, (float)this.magy[i][i2][j2], this.mtouch[i][j2], conto, madness);
                }
                if (this.rx[i][i2][j2] == j) {
                    this.regx(i2, (float)this.magx[i][i2][j2], conto, madness);
                }
                if (this.rz[i][i2][j2] == j) {
                    this.regz(i2, (float)this.magz[i][i2][j2], conto, madness);
                }
            } while (++j2 < 7);
        } while (++i2 < 4);
    }
    
    public void rec(final ContO conto, final int i, final int j, final int k, final int l, final int ncars) {
        if (i == 0) {
            ++this.caught;
        }
        if (this.cntf == 50) {
            int i2 = 0;
            do {
                this.car[i2][i] = new ContO(this.car[i2 + 1][i], 0, 0, 0, 0);
                this.squash[i2][i] = this.squash[i2 + 1][i];
            } while (++i2 < 5);
            this.car[5][i] = new ContO(conto, 0, 0, 0, 0);
            this.squash[5][i] = j;
            this.cntf = 0;
        }
        else {
            ++this.cntf;
        }
        final int[] fix = this.fix;
        --fix[i];
        if (l != 0) {
            final int[] dest = this.dest;
            --dest[i];
        }
        if (this.dest[i] == 230) {
            if (i == 0) {
                this.cotchinow(0, ncars);
                this.whenwasted = 229;
            }
            else if (k != 0) {
                this.cotchinow(i, ncars);
                this.whenwasted = 165 + k;
            }
        }
        int j2 = 0;
        do {
            this.x[j2][i] = this.x[j2 + 1][i];
            this.y[j2][i] = this.y[j2 + 1][i];
            this.z[j2][i] = this.z[j2 + 1][i];
            this.zy[j2][i] = this.zy[j2 + 1][i];
            this.xy[j2][i] = this.xy[j2 + 1][i];
            this.xz[j2][i] = this.xz[j2 + 1][i];
            this.wxz[j2][i] = this.wxz[j2 + 1][i];
            this.wzy[j2][i] = this.wzy[j2 + 1][i];
        } while (++j2 < 299);
        this.x[299][i] = conto.x;
        this.y[299][i] = conto.y;
        this.z[299][i] = conto.z;
        this.xy[299][i] = conto.xy;
        this.zy[299][i] = conto.zy;
        this.xz[299][i] = conto.xz;
        this.wxz[299][i] = (int)conto.wxz;
        this.wzy[299][i] = conto.wzy;
        if (i == 0) {
            j2 = 0;
            do {
                this.checkpoint[j2] = this.checkpoint[j2 + 1];
                this.lastcheck[j2] = this.lastcheck[j2 + 1];
            } while (++j2 < 299);
            this.checkpoint[299] = conto.m.checkpoint;
            this.lastcheck[299] = conto.m.lastcheck;
        }
        j2 = 0;
        do {
            if (conto.stg[j2] == 1) {
                this.sspark[i][j2][this.ns[i][j2]] = 300;
                this.sx[i][j2][this.ns[i][j2]] = conto.sx[j2];
                this.sy[i][j2][this.ns[i][j2]] = conto.sy[j2];
                this.sz[i][j2][this.ns[i][j2]] = conto.sz[j2];
                this.smag[i][j2][this.ns[i][j2]] = conto.smag[j2];
                this.scx[i][j2][this.ns[i][j2]] = conto.scx[j2];
                this.scz[i][j2][this.ns[i][j2]] = conto.scz[j2];
                this.fulls[i][j2][this.ns[i][j2]] = conto.fulls[j2];
                final int[] array = this.ns[i];
                final int n = j2;
                ++array[n];
                if (this.ns[i][j2] == 30) {
                    this.ns[i][j2] = 0;
                }
            }
            int k2 = 0;
            do {
                final int[] array2 = this.sspark[i][j2];
                final int n2 = k2;
                --array2[n2];
            } while (++k2 < 30);
            k2 = 0;
            do {
                final int[] array3 = this.ry[i][j2];
                final int n3 = k2;
                --array3[n3];
                final int[] array4 = this.rx[i][j2];
                final int n4 = k2;
                --array4[n4];
                final int[] array5 = this.rz[i][j2];
                final int n5 = k2;
                --array5[n5];
            } while (++k2 < 7);
        } while (++j2 < 4);
    }
    
    public void recx(final int i, final float f, final int j) {
        this.rx[j][i][this.nry[j][i]] = 300;
        this.magx[j][i][this.nry[j][i]] = (int)f;
        final int[] array = this.nrx[j];
        ++array[i];
        if (this.nrx[j][i] == 7) {
            this.nrx[j][i] = 0;
        }
    }
    
    public void recy(final int i, final float f, final boolean flag, final int j) {
        this.ry[j][i][this.nry[j][i]] = 300;
        this.magy[j][i][this.nry[j][i]] = (int)f;
        this.mtouch[j][this.nry[j][i]] = flag;
        final int[] array = this.nry[j];
        ++array[i];
        if (this.nry[j][i] == 7) {
            this.nry[j][i] = 0;
        }
    }
    
    public void cotchinow(final int i, final int ncars) {
        if (this.caught >= 300) {
            this.wasted = i;
            int j = 0;
            do {
                this.starcar[j] = new ContO(this.car[0][j], 0, 0, 0, 0);
                this.hsquash[j] = this.squash[0][j];
                this.hfix[j] = this.fix[j];
                this.hdest[j] = this.dest[j];
            } while (++j < ncars);
            j = 0;
            do {
                int k = 0;
                do {
                    this.hx[j][k] = this.x[j][k];
                    this.hy[j][k] = this.y[j][k];
                    this.hz[j][k] = this.z[j][k];
                    this.hxy[j][k] = this.xy[j][k];
                    this.hzy[j][k] = this.zy[j][k];
                    this.hxz[j][k] = this.xz[j][k];
                    this.hwxz[j][k] = this.wxz[j][k];
                    this.hwzy[j][k] = this.wzy[j][k];
                } while (++k < ncars);
                this.hcheckpoint[j] = this.checkpoint[j];
                this.hlastcheck[j] = this.lastcheck[j];
            } while (++j < 300);
            j = 0;
            do {
                int l = 0;
                do {
                    this.hns[j][l] = this.ns[j][l];
                    int k2 = 0;
                    do {
                        this.hsspark[j][l][k2] = this.sspark[j][l][k2];
                        this.hsx[j][l][k2] = this.sx[j][l][k2];
                        this.hsy[j][l][k2] = this.sy[j][l][k2];
                        this.hsz[j][l][k2] = this.sz[j][l][k2];
                        this.hsmag[j][l][k2] = this.smag[j][l][k2];
                        this.hscx[j][l][k2] = this.scx[j][l][k2];
                        this.hscz[j][l][k2] = this.scz[j][l][k2];
                        this.hfulls[j][l][k2] = this.fulls[j][l][k2];
                    } while (++k2 < 30);
                } while (++l < 4);
            } while (++j < ncars);
            j = 0;
            do {
                int i2 = 0;
                do {
                    this.hnry[j][i2] = this.nry[j][i2];
                    this.hnrx[j][i2] = this.nrx[j][i2];
                    this.hnrz[j][i2] = this.nrz[j][i2];
                    int l2 = 0;
                    do {
                        this.hry[j][i2][l2] = this.ry[j][i2][l2];
                        this.hmagy[j][i2][l2] = this.magy[j][i2][l2];
                        this.hrx[j][i2][l2] = this.rx[j][i2][l2];
                        this.hmagx[j][i2][l2] = this.magx[j][i2][l2];
                        this.hrz[j][i2][l2] = this.rz[j][i2][l2];
                        this.hmagz[j][i2][l2] = this.magz[j][i2][l2];
                    } while (++l2 < 7);
                } while (++i2 < 4);
            } while (++j < ncars);
            j = 0;
            do {
                int j2 = 0;
                do {
                    this.hmtouch[j][j2] = this.mtouch[j][j2];
                } while (++j2 < 7);
            } while (++j < ncars);
            this.hcaught = true;
        }
    }
    
    public void chipx(final int i, float f, final ContO conto, final Madness madness) {
        if (Math.abs(f) > 100.0f) {
            if (f > 100.0f) {
                f -= 100.0f;
            }
            if (f < -100.0f) {
                f += 100.0f;
            }
            for (int j = 0; j < conto.npl; ++j) {
                float f2 = 0.0f;
                for (int k = 0; k < conto.p[j].n; ++k) {
                    if (conto.p[j].wz == 0 && this.py(conto.keyx[i], conto.p[j].ox[k], conto.keyz[i], conto.p[j].oz[k]) < madness.clrad[madness.cn]) {
                        f2 = f / 20.0f * this.m.random();
                    }
                }
                if (f2 != 0.0f && Math.abs(f2) >= 1.0f) {
                    conto.p[j].chip = 1;
                    conto.p[j].ctmag = f2;
                }
            }
        }
    }
    
    public void regx(final int i, float f, final ContO conto, final Madness madness) {
        if (Math.abs(f) > 100.0f) {
            if (f > 100.0f) {
                f -= 100.0f;
            }
            if (f < -100.0f) {
                f += 100.0f;
            }
            for (int j = 0; j < conto.npl; ++j) {
                float f2 = 0.0f;
                for (int k = 0; k < conto.p[j].n; ++k) {
                    if (conto.p[j].wz == 0 && this.py(conto.keyx[i], conto.p[j].ox[k], conto.keyz[i], conto.p[j].oz[k]) < madness.clrad[madness.cn]) {
                        f2 = f / 20.0f * this.m.random();
                        final int[] oz = conto.p[j].oz;
                        final int n = k;
                        oz[n] -= (f2 * this.m.sin(conto.xz) * this.m.cos(conto.zy));  // cast: bytecode-verified
                        final int[] ox = conto.p[j].ox;
                        final int n2 = k;
                        ox[n2] += (f2 * this.m.cos(conto.xz) * this.m.cos(conto.xy));  // cast: bytecode-verified
                    }
                }
                if (f2 != 0.0f) {
                    if (Math.abs(f2) >= 1.0f) {
                        conto.p[j].chip = 1;
                        conto.p[j].ctmag = f2;
                    }
                    if (!conto.p[j].nocol && conto.p[j].glass != 1) {
                        if (conto.p[j].bfase > 20 && conto.p[j].hsb[1] > 0.2) {
                            conto.p[j].hsb[1] = 0.2f;
                        }
                        if (conto.p[j].bfase > 30) {
                            if (conto.p[j].hsb[2] < 0.5) {
                                conto.p[j].hsb[2] = 0.5f;
                            }
                            if (conto.p[j].hsb[1] > 0.1) {
                                conto.p[j].hsb[1] = 0.1f;
                            }
                        }
                        if (conto.p[j].bfase > 40) {
                            conto.p[j].hsb[1] = 0.05f;
                        }
                        if (conto.p[j].bfase > 50) {
                            if (conto.p[j].hsb[2] > 0.8) {
                                conto.p[j].hsb[2] = 0.8f;
                            }
                            conto.p[j].hsb[0] = 0.075f;
                            conto.p[j].hsb[1] = 0.05f;
                        }
                        if (conto.p[j].bfase > 60) {
                            conto.p[j].hsb[0] = 0.05f;
                        }
                        final Plane plane = conto.p[j];
                        plane.bfase += Math.abs(f2);  // cast: bytecode-verified
                        new Color(conto.p[j].c[0], conto.p[j].c[1], conto.p[j].c[2]);
                        final Color color = Color.getHSBColor(conto.p[j].hsb[0], conto.p[j].hsb[1], conto.p[j].hsb[2]);
                        conto.p[j].c[0] = color.getRed();
                        conto.p[j].c[1] = color.getGreen();
                        conto.p[j].c[2] = color.getBlue();
                    }
                    if (conto.p[j].glass == 1) {
                        final Plane plane2 = conto.p[j];
                        plane2.gr += Math.abs(f2 * 1.5);  // cast: bytecode-verified
                    }
                }
            }
        }
    }
    
    public void recz(final int i, final float f, final int j) {
        this.rz[j][i][this.nry[j][i]] = 300;
        this.magz[j][i][this.nry[j][i]] = (int)f;
        final int[] array = this.nrz[j];
        ++array[i];
        if (this.nrz[j][i] == 7) {
            this.nrz[j][i] = 0;
        }
    }
    
    public int py(final int i, final int j, final int k, final int l) {
        return (i - j) * (i - j) + (k - l) * (k - l);
    }
}
