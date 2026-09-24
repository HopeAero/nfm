import java.awt.Color;

// 
// Decompiled by Procyon v0.6.0
// 

public class Madness
{
    Medium m;
    Record rpd;
    xtGraphics xt;
    int cn;
    int im;
    int mxz;
    int xz;
    int xzadjust;
    int oldfcnt;
    int teleinvul;
    float nuclearmod;
    int fixtime;
    boolean fakedest;
    float initialspeed;
    int teletimer;
    boolean forcehandb;
    int slowstable;
    float groundlevel;
    double levelmod;
    boolean startedgoing;
    int cxz;
    float[][] acelf;
    int[][] swits;
    float[] handb;
    float[] handbreset;
    float[] airs;
    int[] airc;
    double[] turn;
    double[] turnreset;
    float[] grip;
    float[] gripreset;
    float[] bounce;
    float[] simag;
    float[] moment;
    float[] strengthreduce;
    float[] comprad;
    float[] push;
    float[] push2;
    float[] revpush;
    float[] revpush2;
    int[] lift;
    int[] lift2;
    int[] revlift;
    int[] powerloss;
    int[] powerloss2;
    int[] flipy;
    int[] msquash;
    int[] clrad;
    float[] dammult;
    int[] maxmag;
    int[] healthreset;
    int[] healthcut;
    boolean[] dominate;
    boolean[] caught;
    int pzy;
    int pxy;
    int[] tsstat;
    int[] accstat;
    int[] ovrstat;
    int[] gristat;
    int[] stustat;
    int[] strstat;
    int[] endstat;
    int[] endboosts;
    float speed;
    float forca;
    boolean[] fixdestai;
    float[] scy;
    float[] scz;
    float[] scx;
    boolean mtouch;
    boolean nofix;
    boolean wtouch;
    int cntouch;
    boolean capsized;
    int txz;
    int fxz;
    int pmlt;
    int nmlt;
    int dcnt;
    int skid;
    boolean pushed;
    boolean gtouch;
    boolean respawning;
    boolean pl;
    boolean pr;
    boolean pd;
    boolean pu;
    boolean speccheats;
    boolean[] doonce;
    boolean failsave;
    int loop;
    float ucomp;
    float dcomp;
    double stumultiplier;
    float speclast2;
    float lcomp;
    float rcomp;
    int lxz;
    int[] travxy;
    int[] travzy;
    int[] travxz;
    int trcnt;
    int capcnt;
    int[] srfcnt;
    boolean isabot;
    boolean teleported;
    boolean[] rtab;
    boolean[] ftab;
    boolean[] btab;
    int sendtofloor;
    boolean[] surfer;
    float[] powerup;
    float powsh;
    int[] realkiller;
    float spatk;
    boolean cop;
    float speclast;
    int xtpower;
    float tilt;
    int pusheddelay;
    int squash;
    int stagechks;
    boolean stagechkst;
    int nbsq;
    int hitmag;
    float dmgmag;
    int fixlimit;
    int cntdest;
    boolean dest;
    boolean fragfix;
    boolean newcar;
    boolean haxtroll;
    int stagwastes;
    int pan;
    int pcleared;
    int timer;
    int roadtyp;
    int clear;
    int[] exp;
    int[] level;
    int[] aitssp;
    int[] aiaccsp;
    int[] aigripsp;
    int[] aistusp;
    int[] aistrsp;
    int[] aiendsp;
    boolean failsave2;
    boolean specialact;
    boolean telechk;
    int nlaps;
    int focus;
    int stagekills;
    int fixesleft;
    float power;
    int missedcp;
    int nostunts;
    int[] lastcolido;
    boolean launched;
    int[] collided;
    int point;
    int timer2;
    int revive;
    boolean eliminate;
    boolean cntdale;
    int dtimer;
    float speedmulti;
    float powermulti;
    int prevhitmag;
    boolean shadowcar;
    boolean specialact2;
    boolean frozen;
    boolean redstr;
    boolean strswap;
    boolean leech;
    boolean nofocus;
    double[] pmulti;
    int rpdcatch;
    boolean stagedalest;
    boolean[] beast;
    double[] multiplier;
    boolean colidim;
    boolean onlyonce;
    int stagedales;
    float[][] nitroacelf;
    int[][] nitroswits;
    float[] airsreset;
    int[] aircreset;
    float[] momentreset;
    
    public int py(final int i, final int j, final int k, final int l) {
        return (i - j) * (i - j) + (k - l) * (k - l);
    }
    
    public void regy(final int i, float f, final ContO conto, final int attacker) {
        f *= this.dammult[this.cn];
        if (f > 100.0f) {
            this.rpd.recy(i, f, this.mtouch, this.im);
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
            if (this.im == 0 || this.colidim) {
                this.xt.crash(f, byte2 * byte0);
            }
            if (byte2 * byte0 == 0 || this.mtouch) {
                for (int l = 0; l < conto.npl; ++l) {
                    float f2 = 0.0f;
                    float f3 = 0.0f;
                    final double healthpc = this.hitmag * 100.0 / this.maxmag[this.cn];
                    final double dmgpc = this.dmgmag * 100.0 / this.healthreset[this.cn];
                    for (int k2 = 0; k2 < conto.p[l].n; ++k2) {
                        if (conto.p[l].wz == 0 && this.py(conto.keyx[i], conto.p[l].ox[k2], conto.keyz[i], conto.p[l].oz[k2]) < (int)(this.clrad[this.cn] * this.multiplier[this.im])) {
                            final float dale = this.m.random();
                            float dmgby = 20.0f;
                            if (this.cn == 2) {
                                dmgby = 10.0f;
                            }
                            if (this.cn == 11 || this.cn == 13 || this.cn == 18 || this.cn == 36) {
                                dmgby = 50.0f;
                            }
                            if (this.cn == 19 || this.cn == 22) {
                                dmgby = 80.0f;
                            }
                            if (this.cn == 20) {
                                dmgby = 40.0f;
                            }
                            if (dmgpc >= healthpc) {
                                f2 = 0.0f;
                            }
                            else {
                                f2 = f / dmgby * dale;
                                if (Math.abs(f2) > 5000.0f) {
                                    f2 = 5000.0f;
                                }
                            }
                            f3 = f / 20.0f * dale;
                            final int[] oz = conto.p[l].oz;
                            final int n = k2;
                            oz[n] += (f2 * this.m.sin(j));  // cast: bytecode-verified
                            final int[] ox = conto.p[l].ox;
                            final int n2 = k2;
                            ox[n2] -= (f2 * this.m.sin(k));  // cast: bytecode-verified
                            this.hitmag += Math.abs(f3);  // cast: bytecode-verified
                            if (attacker == 0 && this.xt.careermode) {
                                if (!this.dest) {
                                    final float percentage = Math.abs(f3) * 100.0f / this.maxmag[this.cn];
                                    float proportion = 0.0f;
                                    final float powdrain = 0.0f;
                                    for (int a = 0; a < 6; ++a) {
                                        if (this.xt.specialstats[this.xt.sc[attacker]][18][a] > 0) {
                                            proportion = this.xt.specialstats[this.xt.sc[attacker]][18][a] * 0.05f;
                                        }
                                        if (this.xt.specialstats[this.xt.sc[attacker]][19][a] > 0) {
                                            proportion = this.xt.specialstats[this.xt.sc[attacker]][18][a] * 0.025f;
                                        }
                                    }
                                    if (!this.specialact) {
                                        this.spatk -= percentage * proportion * 1.2f;
                                    }
                                    else {
                                        this.speclast -= percentage * proportion * 1.2f;
                                        this.speclast2 -= percentage * proportion * 1.2f;
                                    }
                                    if (this.power != 98.0f) {
                                        this.power -= percentage * powdrain * 0.98f;
                                    }
                                    else {
                                        this.xtpower -= (int)(percentage * powdrain * 2.0f);
                                    }
                                    final xtGraphics xt = this.xt;
                                    xt.hitgain += Math.abs(f3);  // cast: bytecode-verified
                                }
                                else {
                                    this.xt.hitgain = 0;
                                }
                            }
                            this.dmgmag += Math.abs(f2) * (dmgby / 20.0f);
                            if (this.dmgmag > this.healthreset[this.cn]) {
                                f2 = 0.0f;
                            }
                        }
                    }
                    if (f2 != 0.0f && dmgpc < healthpc) {
                        if (Math.abs(f2) >= 1.0f) {
                            conto.p[l].chip = 1;
                            conto.p[l].ctmag = f2;
                        }
                        if (!conto.p[l].nocol && conto.p[l].glass != 1) {
                            if (conto.p[l].bfase > 20 && conto.p[l].hsb[1] > 0.25) {
                                conto.p[l].hsb[1] = 0.25f;
                            }
                            if (conto.p[l].bfase > 25 && conto.p[l].hsb[2] > 0.7) {
                                conto.p[l].hsb[2] = 0.7f;
                            }
                            if (conto.p[l].bfase > 30 && conto.p[l].hsb[1] > 0.15) {
                                conto.p[l].hsb[1] = 0.15f;
                            }
                            if (conto.p[l].bfase > 35 && conto.p[l].hsb[2] > 0.6) {
                                conto.p[l].hsb[2] = 0.6f;
                            }
                            if (conto.p[l].bfase > 40) {
                                conto.p[l].hsb[0] = 0.075f;
                            }
                            if (conto.p[l].bfase > 50 && conto.p[l].hsb[2] > 0.5) {
                                conto.p[l].hsb[2] = 0.5f;
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
                if (this.nbsq > 0) {
                    int i2 = 0;
                    int j2 = 1;
                    for (int l2 = 0; l2 < conto.npl; ++l2) {
                        float f4 = 0.0f;
                        for (int i3 = 0; i3 < conto.p[l2].n; ++i3) {
                            if (conto.p[l2].wz == 0) {
                                f4 = f / 15.0f * this.m.random();
                                if ((Math.abs(conto.p[l2].oy[i3] - this.flipy[this.cn] - this.squash) < this.msquash[this.cn] * 3 || conto.p[l2].oy[i3] < this.flipy[this.cn] + this.squash) && this.squash < this.msquash[this.cn]) {
                                    final int[] oy = conto.p[l2].oy;
                                    final int n3 = i3;
                                    oy[n3] += f4;  // cast: bytecode-verified
                                    i2 += f4;  // cast: bytecode-verified
                                    ++j2;
                                    this.hitmag += Math.abs(f4);  // cast: bytecode-verified
                                }
                            }
                        }
                        if (conto.p[l2].glass == 1) {
                            final Plane plane3 = conto.p[l2];
                            plane3.gr += 5;
                        }
                        else if (f4 != 0.0f) {
                            final Plane plane4 = conto.p[l2];
                            plane4.bfase += f4;  // cast: bytecode-verified
                        }
                        if (Math.abs(f4) >= 1.0f) {
                            conto.p[l2].chip = 1;
                            conto.p[l2].ctmag = f4;
                        }
                    }
                    this.squash += i2 / j2;
                    this.nbsq = 0;
                }
                else {
                    ++this.nbsq;
                }
            }
        }
    }
    
    public Madness(final Medium medium, final Record record, final xtGraphics xtgraphics, final int i) {
        this.nuclearmod = 1.0f;
        this.groundlevel = 250.0f;
        this.levelmod = 1.0;
        this.acelf = new float[][] { { 11.0f, 5.0f, 3.0f }, { 14.0f, 7.0f, 5.0f }, { 10.0f, 5.0f, 3.5f }, { 12.0f, 7.0f, 4.0f }, { 10.0f, 5.0f, 3.5f }, { 13.0f, 6.5f, 5.0f }, { 12.5f, 7.5f, 4.0f }, { 10.0f, 6.0f, 3.0f }, { 14.5f, 7.0f, 6.0f }, { 12.0f, 7.0f, 3.5f }, { 11.0f, 6.0f, 3.0f }, { 9.0f, 5.0f, 3.0f }, { 13.0f, 7.0f, 4.5f }, { 10.5f, 6.0f, 3.0f }, { 11.0f, 7.5f, 4.0f }, { 12.0f, 6.0f, 3.5f }, { 17.5f, 11.5f, 9.0f }, { 14.5f, 7.5f, 6.0f }, { 13.0f, 7.0f, 4.5f }, { 14.0f, 7.0f, 5.0f }, { 8.0f, 3.5f, 3.0f }, { 15.0f, 10.0f, 7.0f }, { 9.0f, 5.0f, 3.0f }, { 11.0f, 5.0f, 3.0f }, { 14.0f, 7.0f, 5.0f }, { 10.0f, 5.0f, 3.5f }, { 11.0f, 6.0f, 3.5f }, { 10.0f, 5.0f, 3.5f }, { 12.0f, 6.0f, 3.0f }, { 9.0f, 7.0f, 4.0f }, { 11.0f, 5.0f, 3.0f }, { 12.0f, 7.0f, 4.0f }, { 12.0f, 7.0f, 3.5f }, { 11.5f, 6.5f, 3.5f }, { 9.0f, 5.0f, 3.0f }, { 13.0f, 7.0f, 4.5f }, { 7.5f, 3.5f, 3.0f }, { 11.0f, 7.5f, 4.0f }, { 12.0f, 6.0f, 3.5f } };
        this.swits = new int[][] { { 50, 180, 280 }, { 100, 200, 310 }, { 60, 180, 271 }, { 70, 200, 300 }, { 70, 170, 280 }, { 60, 200, 290 }, { 60, 170, 280 }, { 60, 180, 275 }, { 90, 210, 295 }, { 90, 190, 276 }, { 70, 200, 295 }, { 50, 160, 270 }, { 90, 200, 305 }, { 70, 150, 250 }, { 80, 200, 300 }, { 70, 210, 290 }, { 90, 200, 285 }, { 140, 225, 320 }, { 70, 180, 260 }, { 135, 210, 300 }, { 50, 130, 210 }, { 150, 250, 335 }, { 80, 170, 260 }, { 50, 180, 280 }, { 100, 200, 310 }, { 60, 180, 275 }, { 70, 200, 295 }, { 70, 170, 275 }, { 60, 200, 290 }, { 60, 170, 280 }, { 60, 180, 280 }, { 90, 210, 295 }, { 90, 190, 276 }, { 70, 200, 295 }, { 50, 160, 270 }, { 90, 200, 305 }, { 50, 130, 210 }, { 80, 200, 300 }, { 70, 210, 290 } };
        this.handb = new float[] { 7.0f, 10.0f, 7.0f, 15.0f, 12.0f, 8.0f, 9.0f, 10.0f, 5.0f, 7.0f, 8.0f, 10.0f, 8.0f, 12.0f, 7.0f, 7.0f, 6.0f, 10.0f, 15.0f, 20.0f, 10.0f, 10.0f, 8.0f, 7.0f, 10.0f, 7.0f, 15.0f, 12.0f, 8.0f, 9.0f, 10.0f, 5.0f, 7.0f, 8.0f, 10.0f, 8.0f, 12.0f, 7.0f, 7.0f };
        this.handbreset = new float[] { 7.0f, 10.0f, 7.0f, 15.0f, 12.0f, 8.0f, 9.0f, 10.0f, 5.0f, 7.0f, 8.0f, 10.0f, 8.0f, 12.0f, 7.0f, 7.0f, 6.0f, 10.0f, 15.0f, 20.0f, 10.0f, 10.0f, 8.0f, 7.0f, 10.0f, 7.0f, 15.0f, 12.0f, 8.0f, 9.0f, 10.0f, 5.0f, 7.0f, 8.0f, 10.0f, 8.0f, 12.0f, 7.0f, 7.0f };
        this.airs = new float[] { 1.0f, 1.2f, 0.95f, 1.1f, 2.2f, 1.0f, 0.9f, 0.8f, 1.0f, 0.85f, 1.15f, 0.8f, 1.0f, 0.75f, 1.3f, 1.0f, 1.2f, 1.4f, 0.3f, 0.65f, 0.25f, 1.5f, 0.5f, 1.0f, 1.2f, 0.95f, 1.0f, 2.2f, 1.0f, 0.9f, 0.8f, 1.0f, 0.9f, 1.15f, 0.8f, 1.0f, 0.3f, 1.3f, 1.0f };
        this.airc = new int[] { 70, 80, 40, 83, 30, 50, 40, 90, 40, 45, 55, 10, 50, 0, 100, 60, 70, 100, 10, 95, 0, 110, 15, 70, 30, 40, 40, 30, 50, 40, 90, 40, 50, 75, 10, 50, 0, 100, 60 };
        this.turn = new double[] { 6.0, 9.0, 5.0, 5.0, 7.5, 5.0, 4.5, 3.5, 7.0, 5.5, 7.0, 4.5, 6.0, 5.0, 6.0, 6.0, 6.5, 7.0, 4.0, 10.0, 6.0, 6.0, 5.5, 6.0, 9.0, 5.0, 7.0, 8.0, 7.0, 5.0, 5.0, 9.0, 7.0, 7.0, 4.0, 6.0, 5.0, 7.0, 6.0 };
        this.turnreset = new double[] { 6.0, 9.0, 5.0, 5.0, 7.5, 5.0, 4.5, 3.5, 7.0, 5.5, 7.0, 4.5, 6.0, 5.0, 6.0, 6.0, 6.5, 7.0, 4.0, 10.0, 6.0, 6.0, 5.5, 6.0, 9.0, 5.0, 7.0, 8.0, 7.0, 5.0, 5.0, 9.0, 7.0, 7.0, 4.0, 6.0, 5.0, 7.0, 6.0 };
        this.grip = new float[] { 27.2f, 20.8f, 17.6f, 21.4f, 25.2f, 23.0f, 19.8f, 14.6f, 18.4f, 23.8f, 22.4f, 26.6f, 24.2f, 42.4f, 23.6f, 32.8f, 19.4f, 35.0f, 37.0f, 56.0f, 40.0f, 35.0f, 30.0f, 20.0f, 27.0f, 18.0f, 22.0f, 19.0f, 20.0f, 22.0f, 20.0f, 16.0f, 24.0f, 22.4f, 25.0f, 30.0f, 27.0f, 30.0f, 24.0f };
        this.gripreset = new float[] { 27.2f, 20.8f, 17.6f, 21.4f, 25.2f, 23.0f, 19.8f, 14.6f, 18.4f, 23.8f, 22.4f, 26.6f, 24.2f, 42.4f, 23.6f, 32.8f, 19.4f, 35.0f, 37.0f, 56.0f, 40.0f, 35.0f, 30.0f, 20.0f, 27.0f, 18.0f, 22.0f, 19.0f, 20.0f, 22.0f, 20.0f, 16.0f, 24.0f, 22.4f, 25.0f, 30.0f, 27.0f, 30.0f, 24.0f };
        this.bounce = new float[] { 1.2f, 1.05f, 1.3f, 1.05f, 1.3f, 1.2f, 1.15f, 1.1f, 1.2f, 1.1f, 1.15f, 0.8f, 1.05f, 0.8f, 1.1f, 1.15f, 1.1f, 1.1f, 0.8f, 1.0f, 0.9f, 1.2f, 0.9f, 1.2f, 1.05f, 1.3f, 1.05f, 1.3f, 1.2f, 1.15f, 1.1f, 1.2f, 1.1f, 1.1f, 0.8f, 1.05f, 0.8f, 1.1f, 1.15f };
        this.simag = new float[] { 0.9f, 0.85f, 1.05f, 0.9f, 0.85f, 0.9f, 1.05f, 0.9f, 1.0f, 1.05f, 0.9f, 1.1f, 0.9f, 1.3f, 0.9f, 1.15f, 1.0f, 1.0f, 0.9f, 0.8f, 0.8f, 0.8f, 0.8f, 0.9f, 0.85f, 1.05f, 0.9f, 0.85f, 0.9f, 1.05f, 0.9f, 1.0f, 1.05f, 0.9f, 1.1f, 0.9f, 1.3f, 0.9f, 1.15f };
        this.moment = new float[] { 1.25f, 0.75f, 1.5f, 1.0f, 0.85f, 1.25f, 1.325f, 1.4f, 1.4f, 1.5f, 1.425f, 2.1f, 1.3f, 3.0f, 1.525f, 2.1f, 2.5f, 2.1f, 6.0f, 3.2f, 6.195f, 1.55f, 11.0f, 1.2f, 0.75f, 1.4f, 1.0f, 1.1f, 1.25f, 1.4f, 1.3f, 1.2f, 1.45f, 1.375f, 2.0f, 1.2f, 3.0f, 1.5f, 2.0f };
        this.strengthreduce = new float[] { 1.25f, 0.75f, 1.5f, 1.0f, 0.85f, 1.25f, 1.325f, 1.4f, 1.4f, 1.5f, 1.425f, 2.1f, 1.3f, 3.0f, 1.525f, 2.1f, 2.5f, 2.1f, 6.0f, 3.2f, 6.195f, 1.55f, 11.0f, 1.2f, 0.75f, 1.4f, 1.0f, 1.1f, 1.25f, 1.4f, 1.3f, 1.2f, 1.45f, 1.375f, 2.0f, 1.2f, 3.0f, 1.5f, 2.0f };
        this.comprad = new float[] { 0.5f, 0.4f, 0.8f, 0.5f, 0.3f, 0.5f, 0.5f, 0.5f, 0.5f, 0.8f, 0.5f, 1.0f, 0.5f, 0.6f, 0.5f, 0.8f, 0.6f, 0.65f, 0.8f, 0.85f, 1.0f, 0.7f, 1.0f, 0.5f, 0.4f, 0.8f, 0.5f, 0.3f, 0.5f, 0.5f, 0.5f, 0.5f, 0.8f, 0.5f, 1.0f, 0.5f, 0.6f, 0.5f, 0.8f };
        this.push = new float[] { 2.0f, 2.0f, 3.0f, 3.0f, 2.0f, 2.0f, 2.0f, 4.0f, 2.0f, 2.0f, 2.0f, 4.0f, 2.0f, 2.0f, 2.0f, 2.0f, 4.0f, 2.0f, 3.0f, 3.0f, 7.0f, 2.0f, 8.5f, 2.0f, 2.0f, 3.0f, 3.0f, 2.0f, 2.0f, 2.0f, 4.0f, 2.0f, 2.0f, 2.0f, 4.0f, 2.0f, 2.0f, 2.0f, 2.0f };
        this.push2 = new float[] { 2.0f, 2.0f, 3.0f, 3.0f, 2.0f, 2.0f, 2.0f, 4.0f, 2.0f, 2.0f, 2.0f, 4.0f, 2.0f, 2.0f, 2.0f, 2.0f, 4.0f, 2.0f, 3.0f, 3.0f, 7.0f, 2.0f, 8.5f, 2.0f, 2.0f, 3.0f, 3.0f, 2.0f, 2.0f, 2.0f, 4.0f, 2.0f, 2.0f, 2.0f, 4.0f, 2.0f, 2.0f, 2.0f, 2.0f };
        this.revpush = new float[] { 2.0f, 3.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 1.0f, 2.0f, 1.0f, 2.0f, 1.0f, 2.0f, 2.0f, 0.25f, 0.4f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 2.0f, 3.0f, 1.0f, 2.0f, 2.0f, 2.0f, 2.0f, 1.0f, 2.0f, 1.0f, 2.0f, 1.0f, 2.0f, 2.0f, 0.25f, 0.4f };
        this.revpush2 = new float[] { 2.0f, 3.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 1.0f, 2.0f, 1.0f, 2.0f, 1.0f, 2.0f, 2.0f, 0.25f, 0.4f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 2.0f, 3.0f, 1.0f, 2.0f, 2.0f, 2.0f, 2.0f, 1.0f, 2.0f, 1.0f, 2.0f, 1.0f, 2.0f, 2.0f, 0.25f, 0.4f };
        this.lift = new int[] { 0, 30, 0, 0, 0, 30, 10, 40, 20, 0, 0, 0, 10, 0, 30, 0, 35, 30, 0, 30, 40, 10, 0, 0, 30, 0, 20, 0, 30, 0, 0, 20, 0, 0, 0, 10, 0, 30, 0 };
        this.lift2 = new int[] { 0, 30, 0, 0, 0, 30, 10, 40, 20, 0, 0, 0, 10, 0, 30, 0, 35, 30, 0, 30, 40, 10, 0, 0, 30, 0, 20, 0, 30, 0, 0, 20, 0, 0, 0, 10, 0, 30, 0 };
        this.revlift = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 15 };
        this.powerloss = new int[] { 2500000, 2500000, 3500000, 2500000, 4000000, 2500000, 3200000, 3200000, 2750000, 5500000, 2750000, 4500000, 3500000, 16700000, 3000000, 5500000, 5500000, 12000000, 18000000, 18000000, 16700000, 4900000, 20000000, 2500000, 2500000, 3500000, 2500000, 4000000, 2500000, 3200000, 3200000, 2750000, 5500000, 2750000, 4500000, 3500000, 16700000, 3000000, 5500000 };
        this.powerloss2 = new int[] { 2500000, 2500000, 3500000, 2500000, 4000000, 2500000, 3200000, 3200000, 2750000, 5500000, 2750000, 4500000, 3500000, 16700000, 3000000, 5500000, 5500000, 12000000, 18000000, 18000000, 16700000, 4900000, 20000000, 2500000, 2500000, 3500000, 2500000, 4000000, 2500000, 3200000, 3200000, 2750000, 5500000, 2750000, 4500000, 3500000, 16700000, 3000000, 5500000 };
        this.flipy = new int[] { -50, -40, -92, -44, -60, -57, -79, -80, -77, -90, -48, -134, -40, -120, -63, -127, -78, -90, -193, -64, -170, -42, -210, -50, -60, -92, -44, -60, -57, -54, -60, -77, -57, -82, -85, -28, -100, -63, -127 };
        this.msquash = new int[] { 7, 4, 7, 2, 8, 4, 6, 2, 3, 8, 4, 10, 3, 20, 3, 8, 3, 3, 8, 2, 1, 1, 2, 7, 4, 7, 2, 8, 4, 6, 2, 3, 8, 4, 10, 3, 20, 3, 8 };
        this.clrad = new int[] { 2750, 1800, 2900, 1300, 1650, 3500, 2800, 4000, 3500, 3400, 4500, 50000, 4500, 250000, 2900, 4200, 2550, 2450, 100000, 100000, 100000, 5000, 100000, 3300, 2500, 4700, 3000, 2000, 4500, 3500, 5000, 10000, 9500, 4000, 7000, 10000, 500000, 5500, 4200 };
        this.dammult = new float[] { 0.72f, 0.75f, 0.55f, 0.775f, 0.56f, 0.7f, 0.7f, 0.57f, 0.6f, 0.46f, 0.6f, 0.25f, 0.6f, 0.2f, 0.3f, 0.46f, 0.325f, 0.26f, 0.2f, 0.18f, 0.185f, 0.5f, 0.19f, 0.8f, 1.0f, 0.55f, 1.0f, 0.6f, 0.7f, 0.72f, 0.8f, 0.6f, 0.46f, 0.6f, 0.48f, 0.6f, 0.2f, 0.3f, 0.46f };
        this.maxmag = new int[] { 6000, 4200, 6000, 9500, 6000, 9100, 11000, 7500, 11500, 12000, 18000, 100000, 18000, 110000, 5800, 18000, 11000, 6700, 100000, 130000, 115000, 20000, 360000, 6000, 4200, 7200, 6000, 6000, 9100, 14000, 12000, 12000, 9700, 13000, 10700, 13000, 63000, 5800, 18000 };
        this.healthreset = new int[] { 6000, 4200, 6000, 9500, 6000, 9100, 11000, 7500, 11500, 12000, 18000, 100000, 18000, 110000, 5800, 18000, 11000, 6700, 100000, 130000, 115000, 20000, 360000, 6000, 4200, 7200, 6000, 6000, 9100, 14000, 12000, 12000, 9700, 13000, 10700, 13000, 63000, 5800, 18000 };
        this.healthcut = new int[] { 6000, 4200, 6000, 9500, 6000, 9100, 11000, 7500, 11500, 12000, 18000, 100000, 18000, 110000, 5800, 18000, 11000, 6700, 100000, 130000, 115000, 20000, 360000, 6000, 4200, 7200, 6000, 6000, 9100, 14000, 12000, 12000, 9700, 13000, 10700, 13000, 63000, 5800, 18000 };
        this.tsstat = new int[39];
        this.accstat = new int[39];
        this.ovrstat = new int[39];
        this.gristat = new int[39];
        this.stustat = new int[39];
        this.strstat = new int[39];
        this.endstat = new int[39];
        this.endboosts = new int[39];
        this.fixdestai = new boolean[101];
        this.speclast2 = 120.0f;
        this.exp = new int[39];
        this.level = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
        this.aitssp = new int[39];
        this.aiaccsp = new int[39];
        this.aigripsp = new int[39];
        this.aistusp = new int[39];
        this.aistrsp = new int[39];
        this.aiendsp = new int[39];
        this.pmulti = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
        this.nitroacelf = new float[][] { { 11.0f, 5.0f, 3.0f }, { 14.0f, 7.0f, 5.0f }, { 10.0f, 5.0f, 3.5f }, { 12.0f, 7.0f, 4.0f }, { 10.0f, 5.0f, 3.5f }, { 13.0f, 6.5f, 5.0f }, { 12.5f, 7.5f, 4.0f }, { 10.0f, 6.0f, 3.0f }, { 14.5f, 7.0f, 6.0f }, { 12.0f, 7.0f, 3.5f }, { 11.0f, 6.0f, 3.0f }, { 9.0f, 5.0f, 3.0f }, { 13.0f, 7.0f, 4.5f }, { 10.5f, 6.0f, 3.0f }, { 11.0f, 7.5f, 4.0f }, { 12.0f, 6.0f, 3.5f }, { 17.5f, 11.5f, 9.0f }, { 14.5f, 7.5f, 6.0f }, { 13.0f, 7.0f, 4.5f }, { 14.0f, 7.0f, 5.0f }, { 8.0f, 3.5f, 3.0f }, { 15.0f, 10.0f, 7.0f }, { 9.0f, 5.0f, 3.0f }, { 11.0f, 5.0f, 3.0f }, { 14.0f, 7.0f, 5.0f }, { 10.0f, 5.0f, 3.5f }, { 11.0f, 6.0f, 3.5f }, { 10.0f, 5.0f, 3.5f }, { 12.0f, 6.0f, 3.0f }, { 9.0f, 7.0f, 4.0f }, { 11.0f, 5.0f, 3.0f }, { 12.0f, 7.0f, 4.0f }, { 12.0f, 7.0f, 3.5f }, { 11.5f, 6.5f, 3.5f }, { 9.0f, 5.0f, 3.0f }, { 13.0f, 7.0f, 4.5f }, { 7.5f, 3.5f, 3.0f }, { 11.0f, 7.5f, 4.0f }, { 12.0f, 6.0f, 3.5f } };
        this.nitroswits = new int[][] { { 50, 180, 280 }, { 100, 200, 310 }, { 60, 180, 271 }, { 70, 200, 300 }, { 70, 170, 280 }, { 60, 200, 290 }, { 60, 170, 280 }, { 60, 180, 275 }, { 90, 210, 295 }, { 90, 190, 276 }, { 70, 200, 295 }, { 50, 160, 270 }, { 90, 200, 305 }, { 70, 150, 250 }, { 80, 200, 300 }, { 70, 210, 290 }, { 90, 200, 285 }, { 140, 225, 320 }, { 70, 180, 260 }, { 135, 210, 300 }, { 50, 130, 210 }, { 150, 250, 335 }, { 80, 170, 260 }, { 50, 180, 280 }, { 100, 200, 310 }, { 60, 180, 275 }, { 70, 200, 295 }, { 70, 170, 275 }, { 60, 200, 290 }, { 60, 170, 280 }, { 60, 180, 280 }, { 90, 210, 295 }, { 90, 190, 276 }, { 70, 200, 295 }, { 50, 160, 270 }, { 90, 200, 305 }, { 50, 130, 210 }, { 80, 200, 300 }, { 70, 210, 290 } };
        this.airsreset = new float[] { 1.0f, 1.2f, 0.95f, 1.1f, 2.2f, 1.0f, 0.9f, 0.8f, 1.0f, 0.85f, 1.15f, 0.8f, 1.0f, 0.75f, 1.3f, 1.0f, 1.2f, 1.4f, 0.3f, 0.65f, 0.25f, 1.5f, 0.5f, 1.0f, 1.2f, 0.95f, 1.0f, 2.2f, 1.0f, 0.9f, 0.8f, 1.0f, 0.9f, 1.15f, 0.8f, 1.0f, 0.3f, 1.3f, 1.0f };
        this.aircreset = new int[] { 70, 80, 40, 83, 30, 50, 40, 90, 40, 45, 55, 10, 50, 0, 100, 60, 70, 100, 10, 95, 0, 110, 15, 70, 30, 40, 40, 30, 50, 40, 90, 40, 50, 75, 10, 50, 0, 100, 60 };
        this.momentreset = new float[] { 1.25f, 0.75f, 1.5f, 1.0f, 0.85f, 1.25f, 1.325f, 1.4f, 1.4f, 1.5f, 1.425f, 2.1f, 1.3f, 3.0f, 1.525f, 2.1f, 2.5f, 2.1f, 6.0f, 3.2f, 6.195f, 1.55f, 11.0f, 1.2f, 0.75f, 1.4f, 1.0f, 1.1f, 1.25f, 1.4f, 1.3f, 1.2f, 1.45f, 1.375f, 2.0f, 1.2f, 3.0f, 1.5f, 2.0f };
        this.cn = 0;
        this.im = 0;
        this.mxz = 0;
        this.cxz = 0;
        this.teleported = false;
        this.dominate = new boolean[101];
        this.caught = new boolean[101];
        this.pzy = 0;
        this.forcehandb = false;
        this.pxy = 0;
        this.teleinvul = 0;
        this.isabot = false;
        this.telechk = false;
        this.speed = 0.0f;
        this.initialspeed = 0.0f;
        this.teletimer = 0;
        this.slowstable = 0;
        this.sendtofloor = -1;
        this.doonce = new boolean[101];
        this.forca = 0.0f;
        this.startedgoing = false;
        this.launched = false;
        this.scy = new float[4];
        this.scz = new float[4];
        this.scx = new float[4];
        this.mtouch = false;
        this.oldfcnt = 0;
        this.fixesleft = 1;
        this.wtouch = false;
        this.shadowcar = false;
        this.respawning = false;
        this.cntouch = 0;
        this.fixtime = 0;
        this.capsized = false;
        this.txz = 0;
        this.fxz = 0;
        this.pmlt = 1;
        this.nmlt = 1;
        this.dcnt = 0;
        this.xzadjust = 0;
        this.nostunts = 0;
        this.realkiller = new int[101];
        this.multiplier = new double[101];
        this.skid = 0;
        this.pushed = false;
        this.pusheddelay = 0;
        this.speedmulti = 1.0f;
        this.powermulti = 1.0f;
        this.gtouch = false;
        this.pl = false;
        this.pr = false;
        this.frozen = false;
        this.redstr = false;
        this.stumultiplier = 0.0;
        this.strswap = false;
        this.leech = false;
        this.nofix = false;
        this.beast = new boolean[101];
        this.pd = false;
        this.pu = false;
        this.loop = 0;
        this.ucomp = 0.0f;
        this.dcomp = 0.0f;
        this.lcomp = 0.0f;
        this.rcomp = 0.0f;
        this.lxz = 0;
        this.travxy = new int[2];
        this.lastcolido = new int[101];
        this.collided = new int[101];
        this.travzy = new int[2];
        this.travxz = new int[2];
        this.trcnt = 0;
        this.capcnt = 0;
        this.srfcnt = new int[2];
        this.rtab = new boolean[2];
        this.ftab = new boolean[2];
        this.btab = new boolean[2];
        this.surfer = new boolean[2];
        this.powerup = new float[2];
        this.powsh = 0.0f;
        this.xtpower = 0;
        this.tilt = 0.0f;
        this.squash = 0;
        this.nbsq = 0;
        this.hitmag = 0;
        this.prevhitmag = 0;
        this.dmgmag = 0.0f;
        this.cntdest = 0;
        this.dest = false;
        this.fakedest = false;
        this.newcar = false;
        this.pan = 0;
        this.pcleared = 0;
        this.clear = 0;
        this.nlaps = 0;
        this.focus = -1;
        this.power = 75.0f;
        this.spatk = 0.0f;
        this.speclast = 0.0f;
        this.missedcp = 0;
        this.point = 0;
        this.nofocus = false;
        this.rpdcatch = 0;
        this.colidim = false;
        this.roadtyp = 0;
        this.m = medium;
        this.rpd = record;
        this.xt = xtgraphics;
        this.im = i;
    }
    
    public int rpy(final float f, final float f1, final float f2, final float f3, final float f4, final float f5) {
        return (int)((f - f1) * (f - f1) + (f2 - f3) * (f2 - f3) + (f4 - f5) * (f4 - f5));
    }
    
    public void regz(final int i, float f, final ContO conto, final int attacker) {
        f *= this.dammult[this.cn];
        if (Math.abs(f) > 100.0f) {
            this.rpd.recz(i, f, this.im);
            if (f > 100.0f) {
                f -= 100.0f;
            }
            if (f < -100.0f) {
                f += 100.0f;
            }
            if (this.im == 0 || this.colidim) {
                this.xt.crash(f, 0);
            }
            for (int j = 0; j < conto.npl; ++j) {
                float f2 = 0.0f;
                float f3 = 0.0f;
                final float dale2 = 0.0f;
                final double healthpc = this.hitmag * 100.0 / this.maxmag[this.cn];
                final double dmgpc = this.dmgmag * 100.0 / this.healthreset[this.cn];
                for (int k = 0; k < conto.p[j].n; ++k) {
                    if (conto.p[j].wz == 0 && this.py(conto.keyx[i], conto.p[j].ox[k], conto.keyz[i], conto.p[j].oz[k]) < (int)(this.clrad[this.cn] * this.multiplier[this.im])) {
                        final float dale3 = this.m.random();
                        float dmgby = 20.0f;
                        if (this.cn == 11 || this.cn == 13 || this.cn == 18 || this.cn == 36) {
                            dmgby = 50.0f;
                        }
                        if (this.cn == 2) {
                            dmgby = 10.0f;
                        }
                        if (this.cn == 19 || this.cn == 22) {
                            dmgby = 80.0f;
                        }
                        if (this.cn == 20) {
                            dmgby = 40.0f;
                        }
                        if (dmgpc >= healthpc) {
                            f2 = 0.0f;
                        }
                        else {
                            f2 = f / dmgby * dale3;
                            if (Math.abs(f2) > 5000.0f) {
                                f2 = 5000.0f;
                            }
                        }
                        f3 = f / 20.0f * dale3;
                        final int[] oz = conto.p[j].oz;
                        final int n = k;
                        oz[n] += (f2 * this.m.cos(conto.xz) * this.m.cos(conto.zy));  // cast: bytecode-verified
                        final int[] ox = conto.p[j].ox;
                        final int n2 = k;
                        ox[n2] += (f2 * this.m.sin(conto.xz) * this.m.cos(conto.xy));  // cast: bytecode-verified
                        this.hitmag += Math.abs(f3);  // cast: bytecode-verified
                        if (attacker == 0 && this.xt.careermode) {
                            if (!this.dest) {
                                final float percentage = Math.abs(f3) * 100.0f / this.maxmag[this.cn];
                                float proportion = 0.0f;
                                final float powdrain = 0.0f;
                                for (int a = 0; a < 6; ++a) {
                                    if (this.xt.specialstats[this.xt.sc[attacker]][18][a] > 0) {
                                        proportion = this.xt.specialstats[this.xt.sc[attacker]][18][a] * 0.05f;
                                    }
                                    if (this.xt.specialstats[this.xt.sc[attacker]][19][a] > 0) {
                                        proportion = this.xt.specialstats[this.xt.sc[attacker]][18][a] * 0.025f;
                                    }
                                }
                                if (!this.specialact) {
                                    this.spatk -= percentage * proportion * 1.2f;
                                }
                                else {
                                    this.speclast -= percentage * proportion * 1.2f;
                                    this.speclast2 -= percentage * proportion * 1.2f;
                                }
                                if (this.power != 98.0f) {
                                    this.power -= percentage * powdrain * 0.98f;
                                }
                                else {
                                    this.xtpower -= (int)(percentage * powdrain * 2.0f);
                                }
                                final xtGraphics xt = this.xt;
                                xt.hitgain += Math.abs(f3);  // cast: bytecode-verified
                            }
                            else {
                                this.xt.hitgain = 0;
                            }
                        }
                        this.dmgmag += Math.abs(f2) * (dmgby / 20.0f);
                        if (this.dmgmag > this.healthreset[this.cn]) {
                            f2 = 0.0f;
                        }
                    }
                }
                if (f2 != 0.0f && dmgpc < healthpc) {
                    if (Math.abs(f2) >= 1.0f) {
                        conto.p[j].chip = 1;
                        conto.p[j].ctmag = f2;
                    }
                    if (!conto.p[j].nocol && conto.p[j].glass != 1) {
                        if (conto.p[j].bfase > 20 && conto.p[j].hsb[1] > 0.25) {
                            conto.p[j].hsb[1] = 0.25f;
                        }
                        if (conto.p[j].bfase > 25 && conto.p[j].hsb[2] > 0.7) {
                            conto.p[j].hsb[2] = 0.7f;
                        }
                        if (conto.p[j].bfase > 30 && conto.p[j].hsb[1] > 0.15) {
                            conto.p[j].hsb[1] = 0.15f;
                        }
                        if (conto.p[j].bfase > 35 && conto.p[j].hsb[2] > 0.6) {
                            conto.p[j].hsb[2] = 0.6f;
                        }
                        if (conto.p[j].bfase > 40) {
                            conto.p[j].hsb[0] = 0.075f;
                        }
                        if (conto.p[j].bfase > 50 && conto.p[j].hsb[2] > 0.5) {
                            conto.p[j].hsb[2] = 0.5f;
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
    
    public void rot(final float[] af, final float[] af1, final int i, final int j, final int k, final int l) {
        if (k != 0) {
            for (int i2 = 0; i2 < l; ++i2) {
                final float f = af[i2];
                final float f2 = af1[i2];
                af[i2] = i + ((f - i) * this.m.cos(k) - (f2 - j) * this.m.sin(k));
                af1[i2] = j + ((f - i) * this.m.sin(k) + (f2 - j) * this.m.cos(k));
            }
        }
    }
    
    public void ghostcolide(final ContO conto, final float strength, final boolean racer) {
        for (int a = 0; a < 4; ++a) {
            this.regx(a, strength, conto, this.im);
            this.regz(a, strength, conto, this.im);
        }
        conto.xz = -360 + (int)(Math.random() * 720.0);
        this.xt.ghosthit = false;
    }
    
    public void colide(final ContO conto, final Madness madness, final ContO conto1, final CheckPoints checkpoints, final Bots bots) {
        final float[] af = new float[4];
        final float[] af2 = new float[4];
        final float[] af3 = new float[4];
        final float[] af4 = new float[4];
        final float[] af5 = new float[4];
        final float[] af6 = new float[4];
        int i = 0;
        do {
            af[i] = (float)(conto.x + conto.keyx[i]);
            if (this.capsized) {
                af2[i] = (float)(conto.y + this.flipy[this.cn] + this.squash);
            }
            else {
                af2[i] = (float)(conto.y + conto.grat);
            }
            af3[i] = (float)(conto.z + conto.keyz[i]);
            af4[i] = (float)(conto1.x + conto1.keyx[i]);
            if (this.capsized) {
                af5[i] = (float)(conto1.y + madness.flipy[madness.cn] + madness.squash);
            }
            else {
                af5[i] = (float)(conto1.y + conto1.grat);
            }
            af6[i] = (float)(conto1.z + conto1.keyz[i]);
        } while (++i < 4);
        this.rot(af, af2, conto.x, conto.y, conto.xy, 4);
        this.rot(af2, af3, conto.y, conto.z, conto.zy, 4);
        this.rot(af, af3, conto.x, conto.z, conto.xz, 4);
        this.rot(af4, af5, conto1.x, conto1.y, conto1.xy, 4);
        this.rot(af5, af6, conto1.y, conto1.z, conto1.zy, 4);
        this.rot(af4, af6, conto1.x, conto1.z, conto1.xz, 4);
        if (this.rpy((float)conto.x, (float)conto1.x, (float)conto.y, (float)conto1.y, (float)conto.z, (float)conto1.z) < (conto.maxR * conto.maxR + conto1.maxR * conto1.maxR) * 1.5) {
            if (!this.caught[madness.im] && (this.speed != 0.0f || madness.speed != 0.0f)) {
                if (Math.abs(this.power * this.speed * this.moment[this.cn]) != Math.abs(madness.power * madness.speed * madness.moment[madness.cn])) {
                    if (Math.abs(this.power * this.speed * this.moment[this.cn]) > Math.abs(madness.power * madness.speed * madness.moment[madness.cn])) {
                        this.dominate[madness.im] = true;
                    }
                    else {
                        this.dominate[madness.im] = false;
                    }
                }
                else if (this.moment[this.cn] > madness.moment[madness.cn]) {
                    this.dominate[madness.im] = true;
                }
                else {
                    this.dominate[madness.im] = false;
                }
                this.caught[madness.im] = true;
            }
        }
        else if (this.caught[madness.im]) {
            this.caught[madness.im] = false;
        }
        if (this.dominate[madness.im]) {
            final int j = (int)(((this.scz[0] - madness.scz[0] + this.scz[1] - madness.scz[1] + this.scz[2] - madness.scz[2] + this.scz[3] - madness.scz[3]) * (this.scz[0] - madness.scz[0] + this.scz[1] - madness.scz[1] + this.scz[2] - madness.scz[2] + this.scz[3] - madness.scz[3]) + (this.scx[0] - madness.scx[0] + this.scx[1] - madness.scx[1] + this.scx[2] - madness.scx[2] + this.scx[3] - madness.scx[3]) * (this.scx[0] - madness.scx[0] + this.scx[1] - madness.scx[1] + this.scx[2] - madness.scx[2] + this.scx[3] - madness.scx[3])) / 16.0f);
            int k = 0;
            do {
                int l = 0;
                do {
                    if (this.rpy(af[k], af4[l], af2[k], af5[l], af3[k], af6[l]) < (j + 7000) * (this.comprad[madness.cn] + this.comprad[this.cn])) {
                        float blmult = 1.0f;
                        float fearless = 1.0f;
                        float protection = 1.0f;
                        float bravery = 1.0f;
                        float reversedef = 1.0f;
                        float reversestr = 1.0f;
                        float lowpowdef = 1.0f;
                        float killstr = 1.0f;
                        float killdef = 1.0f;
                        if (this.xt.careermode) {
                            for (int a = 0; a < 6; ++a) {
                                if (this.im == 0) {
                                    if (this.xt.killtime[0] > 0 && this.xt.specialstats[this.cn][23][a] > 0) {
                                        killstr = 1.0f + this.xt.specialstats[this.cn][23][a] / 100.0f;
                                    }
                                    if (madness.capsized && madness.wtouch && this.xt.specialstats[this.cn][4][a] > 0) {
                                        blmult = 1.0f + this.xt.specialstats[this.cn][4][a] / 100.0f;
                                    }
                                    if (this.speed < 0.0f && this.xt.specialstats[this.cn][14][a] > 0) {
                                        reversestr = 1.0f + this.xt.specialstats[this.cn][14][a] / 100.0f;
                                    }
                                    if ((madness.beast[madness.im] || madness.shadowcar) && this.xt.specialstats[this.cn][12][a] > 0) {
                                        bravery = 1.0f + this.xt.specialstats[this.cn][12][a] / 100.0f;
                                    }
                                }
                                if (madness.im == 0) {
                                    if (this.xt.killtime[1] > 0 && this.xt.specialstats[this.cn][24][a] > 0) {
                                        killdef = 1.0f - this.xt.specialstats[madness.cn][6][a] / 100.0f;
                                    }
                                    if ((this.beast[this.im] || this.shadowcar) && this.xt.specialstats[madness.cn][6][a] > 0) {
                                        fearless = 1.0f - this.xt.specialstats[madness.cn][6][a] * 0.75f / 100.0f;
                                    }
                                    if (madness.capsized && madness.wtouch && this.xt.specialstats[madness.cn][10][a] > 0) {
                                        protection = 1.0f - this.xt.specialstats[madness.cn][10][a] * 1.25f / 50.0f;
                                    }
                                    if (madness.speed < 0.0f && this.xt.specialstats[madness.cn][13][a] > 0) {
                                        reversedef = 1.0f - this.xt.specialstats[madness.cn][13][a] / 100.0f;
                                    }
                                    if (madness.power < 73.5f && this.xt.specialstats[madness.cn][15][a] > 0) {
                                        lowpowdef = 1.0f - this.xt.specialstats[madness.cn][15][a] / 100.0f;
                                    }
                                }
                            }
                            if (this.im == 0) {
                                final int lcap = this.level[this.cn];
                                this.levelmod = 1.0;
                                if (madness.level[madness.cn] > lcap) {
                                    final double ratio = madness.aiendsp[madness.cn] / (double)this.xt.totalsp[madness.im];
                                    final int bspadjust = (int)(this.xt.bonuspoints[madness.im] * (double)lcap / madness.level[madness.cn]);
                                    int newtotalsp = this.xt.spcalc(lcap) + bspadjust;
                                    if (madness.beast[madness.im]) {
                                        newtotalsp = this.xt.beastspcalc(lcap);
                                    }
                                    final int endadj = (int)(newtotalsp * ratio);
                                    final int newhealth = this.xt.healthcalc(madness.healthreset[madness.cn], endadj, madness.cn, 1.0);
                                    final int orighealth = this.xt.healthcalc(madness.healthreset[madness.cn], madness.aiendsp[madness.cn], madness.cn, 1.0);
                                    this.levelmod = newhealth / (double)orighealth;
                                }
                                if (this.xt.undead[madness.im]) {
                                    this.levelmod = 0.0;
                                }
                            }
                        }
                        if (!this.xt.justonce && this.xt.careermode && this.im == 0) {
                            this.xt.ungain = false;
                            madness.prevhitmag = madness.hitmag;
                            this.xt.justonce = true;
                        }
                        if (Math.abs(this.scx[k] * this.moment[this.cn]) > Math.abs(madness.scx[l] * madness.moment[madness.cn])) {
                            float f = madness.scx[l] * this.revpush[this.cn];
                            if (f > 300.0f) {
                                f = 300.0f;
                            }
                            if (f < -300.0f) {
                                f = -300.0f;
                            }
                            if (this.specialact && (this.cn == 15 || this.cn == 25 || this.cn == 38)) {
                                f = 0.0f;
                            }
                            float f2 = this.scx[k] * this.push[this.cn];
                            if (f2 > 300.0f) {
                                f2 = 300.0f;
                            }
                            if (f2 < -300.0f) {
                                f2 = -300.0f;
                            }
                            final float[] scx = madness.scx;
                            final int n = l;
                            scx[n] += f2;
                            if (this.im == 0) {
                                madness.colidim = true;
                            }
                            float f3 = 1.0f;
                            if (this.xt.classicmode && madness.cn == 36) {
                                f3 = 1.27f;
                            }
                            madness.regx(l, f2 * this.moment[this.cn] * f3 * blmult * bravery * reversestr * fearless * protection * reversedef * lowpowdef * killstr * killdef, conto1, this.im);
                            if (madness.colidim) {
                                madness.colidim = false;
                            }
                            final float[] scx2 = this.scx;
                            final int n2 = k;
                            scx2[n2] -= f;
                            float norecoil = madness.moment[this.cn];
                            if (norecoil > 3.0f) {
                                norecoil = 3.0f;
                            }
                            this.regx(k, -f * norecoil, conto, this.im);
                            final float[] scy = this.scy;
                            final int n3 = k;
                            scy[n3] -= this.revlift[this.cn];
                            if (this.im == 0) {
                                madness.colidim = true;
                            }
                            madness.regy(l, (float)(this.revlift[this.cn] * 7), conto1, madness.im);
                            if (madness.colidim) {
                                madness.colidim = false;
                            }
                        }
                        if (Math.abs(this.scz[k] * this.moment[this.cn]) > Math.abs(madness.scz[l] * madness.moment[madness.cn])) {
                            float f4 = madness.scz[l] * this.revpush[this.cn];
                            if (f4 > 300.0f) {
                                f4 = 300.0f;
                            }
                            if (f4 < -300.0f) {
                                f4 = -300.0f;
                            }
                            if (this.specialact && (this.cn == 15 || this.cn == 25 || this.cn == 38)) {
                                f4 = 0.0f;
                            }
                            float f5 = this.scz[k] * this.push[this.cn];
                            if (f5 > 300.0f) {
                                f5 = 300.0f;
                            }
                            if (f5 < -300.0f) {
                                f5 = -300.0f;
                            }
                            float f6 = 1.0f;
                            if (this.xt.classicmode && madness.cn == 36) {
                                f6 = 1.27f;
                            }
                            final float[] scz = madness.scz;
                            final int n4 = l;
                            scz[n4] += f5;
                            if (this.im == 0) {
                                madness.colidim = true;
                            }
                            madness.regz(l, f5 * this.moment[this.cn] * f6 * blmult * bravery * reversestr * fearless * protection * reversedef * lowpowdef * killstr * killdef, conto1, this.im);
                            if (madness.colidim) {
                                madness.colidim = false;
                            }
                            final float[] scz2 = this.scz;
                            final int n5 = k;
                            scz2[n5] -= f4;
                            float norecoil = madness.moment[this.cn];
                            if (norecoil > 3.0f) {
                                norecoil = 3.0f;
                            }
                            this.regz(k, -f4 * norecoil, conto, madness.im);
                            final float[] scy2 = this.scy;
                            final int n6 = k;
                            scy2[n6] -= this.revlift[this.cn];
                            if (this.im == 0) {
                                madness.colidim = true;
                            }
                            madness.regy(l, (float)(this.revlift[this.cn] * 7), conto1, this.im);
                            if (madness.colidim) {
                                madness.colidim = false;
                            }
                        }
                        this.lastcolido[this.im] = 70;
                        madness.lastcolido[madness.im] = 70;
                        bots.botbreak[this.im] = true;
                        this.isabot = false;
                        bots.botbreak[madness.im] = true;
                        madness.isabot = false;
                        boolean whichdest = madness.dest;
                        if (this.xt.careermode && !this.xt.bonusstage[1] && madness.im != 0 && checkpoints.stage == 11) {
                            whichdest = madness.fakedest;
                        }
                        final boolean otherwhichdest = this.dest;
                        if (this.xt.careermode && !this.xt.bonusstage[1] && this.im != 0 && checkpoints.stage == 11) {
                            whichdest = this.fakedest;
                        }
                        if (!whichdest) {
                            this.collided[this.im] = madness.im;
                        }
                        if (!otherwhichdest) {
                            madness.collided[madness.im] = this.im;
                        }
                        int lifts = this.lift[this.cn];
                        if (this.xt.careermode && madness.im == 0) {
                            for (int a2 = 0; a2 < 6; ++a2) {
                                if (this.xt.specialstats[madness.cn][16][a2] > 0) {
                                    final double increment = this.lift[this.cn] / 20.0;
                                    lifts = this.lift[this.cn] - (int)(increment * this.xt.specialstats[madness.cn][16][a2]);
                                }
                            }
                        }
                        final float[] scy3 = madness.scy;
                        final int n7 = l;
                        scy3[n7] -= lifts;
                        if (lifts > 0 && this.im == 0) {
                            madness.launched = true;
                        }
                        if (this.xt.ungain) {
                            continue;
                        }
                        int healthleft = madness.maxmag[madness.cn] - madness.prevhitmag;
                        if (healthleft < 0) {
                            healthleft = 0;
                        }
                        if (this.xt.hitgain > healthleft) {
                            this.xt.hitgain = healthleft;
                        }
                        if (this.im == 0 && this.xt.hitgain > 0) {
                            this.xt.shexamthg = true;
                            this.xt.atrans = 255;
                            double extramod = 1.0;
                            for (int a3 = 0; a3 < 6; ++a3) {
                                if (this.xt.specialstats[this.cn][2][a3] > 0) {
                                    extramod = 1.0 + this.xt.specialstats[this.cn][2][a3] / 100.0;
                                }
                            }
                            final double xpratio = madness.maxmag[madness.cn] / (200.0 + madness.aiendsp[madness.cn] * 10.0);
                            if (!this.xt.noexp) {
                                final int[] exp = this.exp;
                                final int cn = this.cn;
                                exp[cn] += (int)(this.xt.hitgain * extramod * this.levelmod * this.xt.expmult / xpratio);
                            }
                            if (this.xt.combotime < 200) {
                                final xtGraphics xt = this.xt;
                                xt.fakehg += (int)(this.xt.hitgain * extramod * this.levelmod * this.xt.expmult / xpratio);
                            }
                            else {
                                this.xt.fakehg = (int)(this.xt.hitgain * extramod * this.levelmod * this.xt.expmult / xpratio);
                            }
                            this.xt.hitgain = 0;
                            this.xt.combotime = 0;
                        }
                        this.xt.ungain = true;
                    }
                    else {
                        this.xt.justonce = false;
                    }
                } while (++l < 4);
            } while (++k < 4);
        }
    }
    
    public void distruct(final ContO conto) {
        for (int i = 0; i < conto.npl; ++i) {
            if (conto.p[i].wz == 0 || conto.p[i].gr == -17 || conto.p[i].gr == -16) {
                conto.p[i].embos = 1;
            }
        }
    }
    
    public void reseto(final int i, final ContO conto, final CheckPoints checkpoints) {
        this.cn = i;
        int j = 0;
        do {
            this.dominate[j] = false;
            this.caught[j] = false;
            this.doonce[j] = false;
            this.beast[j] = false;
            this.multiplier[j] = 1.0;
            this.collided[j] = 0;
            this.realkiller[j] = 0;
        } while (++j < 101);
        this.frozen = false;
        this.fixtime = 0;
        this.groundlevel = 250.0f;
        this.leech = false;
        this.telechk = false;
        this.teleinvul = 0;
        this.isabot = false;
        this.teleported = false;
        this.forcehandb = false;
        this.sendtofloor = -1;
        this.nofix = false;
        this.launched = false;
        this.startedgoing = false;
        this.nuclearmod = 1.0f;
        this.levelmod = 1.0;
        this.slowstable = 0;
        this.redstr = false;
        this.oldfcnt = 0;
        this.strswap = false;
        if (!this.xt.classicmode) {
            this.dammult[36] = 0.3f;
            this.clrad[36] = 20000;
        }
        else {
            this.dammult[36] = 0.225f;
            this.clrad[36] = 30000;
        }
        this.mxz = 0;
        this.cxz = 0;
        this.pzy = 0;
        this.pxy = 0;
        this.stumultiplier = 0.0;
        this.speed = 0.0f;
        this.initialspeed = 0.0f;
        this.teletimer = 0;
        this.xzadjust = 0;
        this.nostunts = 0;
        j = 0;
        do {
            this.scy[j] = 0.0f;
            this.scx[j] = 0.0f;
            this.scz[j] = 0.0f;
        } while (++j < 4);
        this.forca = ((float)Math.sqrt(conto.keyz[0] * conto.keyz[0] + conto.keyx[0] * conto.keyx[0]) + (float)Math.sqrt(conto.keyz[1] * conto.keyz[1] + conto.keyx[1] * conto.keyx[1]) + (float)Math.sqrt(conto.keyz[2] * conto.keyz[2] + conto.keyx[2] * conto.keyx[2]) + (float)Math.sqrt(conto.keyz[3] * conto.keyz[3] + conto.keyx[3] * conto.keyx[3])) / 8000.0f * (float)(this.bounce[this.cn] - 0.3);
        this.mtouch = false;
        this.wtouch = false;
        this.txz = 0;
        this.fxz = 0;
        this.pmlt = 1;
        this.nmlt = 1;
        this.dcnt = 0;
        this.speedmulti = 1.0f;
        this.powermulti = 1.0f;
        this.skid = 0;
        this.pushed = false;
        this.pusheddelay = 0;
        this.gtouch = false;
        this.pl = false;
        this.pr = false;
        this.pd = false;
        this.prevhitmag = 0;
        this.pu = false;
        this.loop = 0;
        this.lxz = 0;
        this.ucomp = 0.0f;
        this.dcomp = 0.0f;
        this.lcomp = 0.0f;
        this.rcomp = 0.0f;
        for (int a = 0; a < 2; ++a) {
            this.travxy[a] = 0;
            this.travzy[a] = 0;
            this.travxz[a] = 0;
            this.rtab[a] = false;
            this.ftab[a] = false;
            this.btab[a] = false;
            this.surfer[a] = false;
            this.powerup[a] = 0.0f;
        }
        this.powsh = 0.0f;
        this.xtpower = 0;
        this.trcnt = 0;
        this.capcnt = 0;
        this.tilt = 0.0f;
        this.pan = 0;
        if (!this.respawning) {
            this.pcleared = checkpoints.pcs;
            this.clear = 0;
            this.nlaps = 0;
            this.power = 98.0f;
        }
        this.focus = -1;
        this.missedcp = 0;
        this.nofocus = false;
        this.spatk = 0.0f;
        this.speclast = 0.0f;
        for (int b = 0; b < 101; ++b) {
            this.lastcolido[b] = 0;
        }
        for (int b = 0; b < 23; ++b) {
            this.endboosts[b] = 0;
        }
        checkpoints.dested[this.im] = 0;
        this.squash = 0;
        this.nbsq = 0;
        this.hitmag = 0;
        this.roadtyp = 0;
        this.dmgmag = 0.0f;
        this.cntdest = 0;
        this.dest = false;
        this.fakedest = false;
        this.newcar = false;
        if (this.im == 0) {
            this.m.checkpoint = -1;
            this.m.lastcheck = false;
        }
        this.rpdcatch = 0;
        this.respawning = false;
    }
    
    public void regx(final int i, float f, final ContO conto, final int attacker) {
        f *= this.dammult[this.cn];
        if (Math.abs(f) > 100.0f) {
            this.rpd.recx(i, f, this.im);
            if (f > 100.0f) {
                f -= 100.0f;
            }
            if (f < -100.0f) {
                f += 100.0f;
            }
            if (this.im == 0 || this.colidim) {
                this.xt.crash(f, 0);
            }
            for (int j = 0; j < conto.npl; ++j) {
                float f2 = 0.0f;
                float f3 = 0.0f;
                final float dale2 = 0.0f;
                final double healthpc = this.hitmag * 100.0 / this.maxmag[this.cn];
                final double dmgpc = this.dmgmag * 100.0 / this.healthreset[this.cn];
                for (int k = 0; k < conto.p[j].n; ++k) {
                    if (conto.p[j].wz == 0 && this.py(conto.keyx[i], conto.p[j].ox[k], conto.keyz[i], conto.p[j].oz[k]) < (int)(this.clrad[this.cn] * this.multiplier[this.im])) {
                        final float dale3 = this.m.random();
                        float dmgby = 20.0f;
                        if (this.cn == 11 || this.cn == 13 || this.cn == 18 || this.cn == 36) {
                            dmgby = 50.0f;
                        }
                        if (this.cn == 19 || this.cn == 22) {
                            dmgby = 80.0f;
                        }
                        if (this.cn == 2) {
                            dmgby = 10.0f;
                        }
                        if (this.cn == 20) {
                            dmgby = 40.0f;
                        }
                        if (dmgpc >= healthpc) {
                            f2 = 0.0f;
                        }
                        else {
                            f2 = f / dmgby * dale3;
                            if (Math.abs(f2) > 5000.0f) {
                                f2 = 5000.0f;
                            }
                        }
                        f3 = f / 20.0f * dale3;
                        final int[] oz = conto.p[j].oz;
                        final int n = k;
                        oz[n] -= (f2 * this.m.sin(conto.xz) * this.m.cos(conto.zy));  // cast: bytecode-verified
                        final int[] ox = conto.p[j].ox;
                        final int n2 = k;
                        ox[n2] += (f2 * this.m.cos(conto.xz) * this.m.cos(conto.xy));  // cast: bytecode-verified
                        this.hitmag += Math.abs(f3);  // cast: bytecode-verified
                        if (attacker == 0 && this.xt.careermode) {
                            if (!this.dest) {
                                final float percentage = Math.abs(f3) * 100.0f / this.maxmag[this.cn];
                                float proportion = 0.0f;
                                final float powdrain = 0.0f;
                                for (int a = 0; a < 6; ++a) {
                                    if (this.xt.specialstats[this.xt.sc[attacker]][18][a] > 0) {
                                        proportion = this.xt.specialstats[this.xt.sc[attacker]][18][a] * 0.05f;
                                    }
                                    if (this.xt.specialstats[this.xt.sc[attacker]][19][a] > 0) {
                                        proportion = this.xt.specialstats[this.xt.sc[attacker]][18][a] * 0.025f;
                                    }
                                }
                                if (!this.specialact) {
                                    this.spatk -= percentage * proportion * 1.2f;
                                }
                                else {
                                    this.speclast -= percentage * proportion * 1.2f;
                                    this.speclast2 -= percentage * proportion * 1.2f;
                                }
                                if (this.power != 98.0f) {
                                    this.power -= percentage * powdrain * 0.98f;
                                }
                                else {
                                    this.xtpower -= (int)(percentage * powdrain * 2.0f);
                                }
                                final xtGraphics xt = this.xt;
                                xt.hitgain += Math.abs(f3);  // cast: bytecode-verified
                            }
                            else {
                                this.xt.hitgain = 0;
                            }
                        }
                        this.dmgmag += Math.abs(f2) * (dmgby / 20.0f);
                        if (this.dmgmag > this.healthreset[this.cn]) {
                            f2 = 0.0f;
                        }
                    }
                }
                if (f2 != 0.0f && dmgpc < healthpc) {
                    if (Math.abs(f2) >= 1.0f) {
                        conto.p[j].chip = 1;
                        conto.p[j].ctmag = f2;
                    }
                    if (!conto.p[j].nocol && conto.p[j].glass != 1) {
                        if (conto.p[j].bfase > 20 && conto.p[j].hsb[1] > 0.25) {
                            conto.p[j].hsb[1] = 0.25f;
                        }
                        if (conto.p[j].bfase > 25 && conto.p[j].hsb[2] > 0.7) {
                            conto.p[j].hsb[2] = 0.7f;
                        }
                        if (conto.p[j].bfase > 30 && conto.p[j].hsb[1] > 0.15) {
                            conto.p[j].hsb[1] = 0.15f;
                        }
                        if (conto.p[j].bfase > 35 && conto.p[j].hsb[2] > 0.6) {
                            conto.p[j].hsb[2] = 0.6f;
                        }
                        if (conto.p[j].bfase > 40) {
                            conto.p[j].hsb[0] = 0.075f;
                        }
                        if (conto.p[j].bfase > 50 && conto.p[j].hsb[2] > 0.5) {
                            conto.p[j].hsb[2] = 0.5f;
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
    
    public void drive(final Control control, final ContO conto, final Trackers trackers, final CheckPoints checkpoints, final Contva variable, final Bots bots) {
        int i = 1;
        int j = 1;
        boolean flag = false;
        boolean flag2 = false;
        boolean flag3 = false;
        this.capsized = false;
        int zyangle;
        for (zyangle = Math.abs(this.pzy); zyangle > 360; zyangle -= 360) {}
        int xyangle;
        for (xyangle = Math.abs(this.pxy); xyangle > 360; xyangle -= 360) {}
        int k;
        for (k = Math.abs(this.pzy); k > 270; k -= 360) {}
        k = Math.abs(k);
        if (k > 90) {
            flag = true;
        }
        boolean flag4 = false;
        int l;
        for (l = Math.abs(this.pxy); l > 270; l -= 360) {}
        l = Math.abs(l);
        if (l > 90) {
            flag4 = true;
            j = -1;
        }
        this.xt.specialflag[this.im] = flag4;
        this.xz = conto.xz + this.xzadjust * 360;
        float bouncemod = 1.0f;
        if (this.xt.careermode && checkpoints.stage == 24 && this.cn != 19) {
            float gripmod = (this.grip[this.cn] - 28.5f) / 100.0f;
            if (gripmod < 0.55f) {
                gripmod = 0.55f;
            }
            if (gripmod > 1.0f) {
                gripmod = 1.0f;
            }
            final float gripaffect = (gripmod - 0.55f) / 0.45f;
            bouncemod = 1.5f - gripaffect * 0.45f;
        }
        float bounciness = this.bounce[this.cn] * bouncemod;
        if (bounciness > 1.35f) {
            bounciness = 1.35f;
        }
        int i2 = conto.grat;
        if (flag) {
            if (flag4) {
                flag4 = false;
                flag2 = true;
            }
            else {
                flag4 = true;
                this.capsized = true;
            }
            i = -1;
        }
        else if (flag4) {
            this.capsized = true;
        }
        if (this.capsized) {
            i2 = this.flipy[this.cn] + this.squash;
        }
        control.zyinv = flag;
        float f = 0.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        if (this.mtouch) {
            this.loop = 0;
        }
        if (this.wtouch) {
            if (this.loop == 2 || this.loop == -1) {
                this.loop = -1;
                if (control.left) {
                    this.pl = true;
                }
                if (control.right) {
                    this.pr = true;
                }
                if (control.up) {
                    this.pu = true;
                }
                if (control.down) {
                    this.pd = true;
                }
            }
            this.ucomp = 0.0f;
            this.dcomp = 0.0f;
            this.lcomp = 0.0f;
            this.rcomp = 0.0f;
        }
        if (control.handb) {
            if (!this.pushed) {
                if (!this.wtouch) {
                    if (this.loop == 0) {
                        this.loop = 1;
                    }
                }
                else if (this.gtouch) {
                    this.pushed = true;
                }
            }
        }
        else {
            this.pushed = false;
        }
        if (this.loop == 1) {
            final float f4 = (this.scy[0] + this.scy[1] + this.scy[2] + this.scy[3]) / 4.0f;
            int j2 = 0;
            do {
                this.scy[j2] = f4;
            } while (++j2 < 4);
            this.loop = 2;
        }
        int handbboost = 0;
        if (this.xt.careermode) {
            int shadnegate = 0;
            if (this.shadowcar) {
                shadnegate = 50;
            }
            int cheatboost = 0;
            if (this.im == 0) {
                if (this.cn == 31) {
                    cheatboost = 60;
                }
                if (this.cn == 32) {
                    cheatboost = 10;
                }
                if (this.cn == 33) {
                    cheatboost = 12;
                }
                if (this.cn == 36) {
                    cheatboost = 20;
                }
                if (this.cn == 34 || this.cn == 35 || this.cn == 37 || this.cn == 38) {
                    cheatboost = 17;
                }
            }
            handbboost = this.aigripsp[this.cn] - (this.level[this.cn] - 1) - shadnegate - cheatboost;
        }
        this.handb[this.cn] = this.handbreset[this.cn] + handbboost * 0.1f;
        this.turn[this.cn] = this.turnreset[this.cn] + handbboost * 0.1;
        if (this.turn[this.cn] > 15.0) {
            this.turn[this.cn] = 15.0;
        }
        float waterdrag = 1.0f;
        double aircres = 1.0;
        if (this.xt.careermode && checkpoints.stage == 24 && this.cn != 19) {
            float gripmod2 = (this.grip[this.cn] - 28.5f) / 100.0f;
            if (gripmod2 < 0.55f) {
                gripmod2 = 0.55f;
            }
            if (gripmod2 > 1.0f) {
                gripmod2 = 1.0f;
            }
            final float gripaffect2 = (gripmod2 - 0.55f) / 0.45f;
            waterdrag = 0.2f + 0.6f * gripaffect2;
            aircres = 0.5 + 0.35f * gripaffect2;
        }
        final float stuntlimit = this.airsreset[this.cn] + 1.0f;
        float nomorestunts = this.airs[this.cn];
        if (nomorestunts > stuntlimit) {
            nomorestunts = stuntlimit;
        }
        final float stuntpower = nomorestunts * waterdrag;
        int airclimit = this.aircreset[this.cn] + 50;
        if (airclimit > 100) {
            airclimit = 100;
        }
        int nomoreairc = this.airc[this.cn];
        if (nomoreairc > airclimit) {
            nomoreairc = airclimit;
        }
        int realairc = (int)((this.airc[this.cn] - (double)airclimit) * 1.5) + airclimit;
        if (realairc < this.airc[this.cn]) {
            realairc = this.airc[this.cn];
        }
        final int aircpowernolim = (int)(realairc * aircres);
        final int aircpower = (int)(nomoreairc * aircres);
        if (!this.dest) {
            if (this.loop == 2) {
                if (control.up) {
                    if (this.ucomp == 0.0f) {
                        this.ucomp = 10.0f + (this.scy[0] + 50.0f) / 20.0f;
                        if (this.ucomp < 5.0f) {
                            this.ucomp = 5.0f;
                        }
                        if (this.ucomp > 10.0f) {
                            this.ucomp = 10.0f;
                        }
                        this.ucomp *= stuntpower;
                    }
                    if (this.ucomp < 20.0f) {
                        this.ucomp += (0.5 * stuntpower);  // cast: bytecode-verified
                    }
                    f = -aircpowernolim * this.m.sin(conto.xz) * j;
                    f2 = aircpowernolim * this.m.cos(conto.xz) * j;
                }
                else if (this.ucomp != 0.0f && this.ucomp > -2.0f) {
                    this.ucomp -= (0.5 * stuntpower);  // cast: bytecode-verified
                }
                if (control.down) {
                    if (this.dcomp == 0.0f) {
                        this.dcomp = 10.0f + (this.scy[0] + 50.0f) / 20.0f;
                        if (this.dcomp < 5.0f) {
                            this.dcomp = 5.0f;
                        }
                        if (this.dcomp > 10.0f) {
                            this.dcomp = 10.0f;
                        }
                        this.dcomp *= stuntpower;
                    }
                    if (this.dcomp < 20.0f) {
                        this.dcomp += (0.5 * stuntpower);  // cast: bytecode-verified
                    }
                    boolean spacestage = false;
                    if (checkpoints.stage == 15 && !this.xt.bonstage) {
                        spacestage = true;
                    }
                    if (conto.outoftrack && spacestage) {
                        f = -aircpower * this.m.sin(conto.xz) * j;
                        f2 = aircpower * this.m.cos(conto.xz) * j;
                    }
                    else {
                        f3 = (float)(-aircpower);
                    }
                }
                else if (this.dcomp != 0.0f && this.ucomp > -2.0f) {
                    this.dcomp -= (0.5 * stuntpower);  // cast: bytecode-verified
                }
                if (control.left) {
                    if (this.lcomp == 0.0f) {
                        this.lcomp = 5.0f;
                    }
                    if (this.lcomp < 20.0f) {
                        this.lcomp += 2.0f * stuntpower;
                    }
                    f = -aircpower * this.m.cos(conto.xz) * i;
                    f2 = -aircpower * this.m.sin(conto.xz) * i;
                }
                else if (this.lcomp > 0.0f) {
                    this.lcomp -= 2.0f * stuntpower;
                }
                if (control.right) {
                    if (this.rcomp == 0.0f) {
                        this.rcomp = 5.0f;
                    }
                    if (this.rcomp < 20.0f) {
                        this.rcomp += 2.0f * stuntpower;
                    }
                    f = aircpower * this.m.cos(conto.xz) * i;
                    f2 = aircpower * this.m.sin(conto.xz) * i;
                }
                else if (this.rcomp > 0.0f) {
                    this.rcomp -= 2.0f * stuntpower;
                }
                this.pzy += ((this.dcomp - this.ucomp) * this.m.cos(this.pxy));  // cast: bytecode-verified
                if (flag) {
                    conto.xz += ((this.dcomp - this.ucomp) * this.m.sin(this.pxy));  // cast: bytecode-verified
                }
                else {
                    conto.xz -= ((this.dcomp - this.ucomp) * this.m.sin(this.pxy));  // cast: bytecode-verified
                }
                this.pxy += (this.rcomp - this.lcomp);  // cast: bytecode-verified
            }
            else {
                float f5 = this.power * this.speedmulti;
                if (f5 < 40.0f * this.speedmulti) {
                    f5 = 40.0f * this.speedmulti;
                }
                boolean noslow = false;
                if (this.xt.careermode && checkpoints.stage == 21 && this.xt.entered[this.im]) {
                    noslow = true;
                }
                if (this.im == 0 && this.power != 98.0f && !noslow && !this.xt.makebot) {
                    int accelboost = 0;
                    if (this.xt.careermode) {
                        int cheatboost2 = 0;
                        if (this.cn == 32) {
                            cheatboost2 = 15;
                        }
                        if (this.cn == 33) {
                            cheatboost2 = 12;
                        }
                        if (this.cn == 31 || this.cn == 36) {
                            cheatboost2 = 10;
                        }
                        if (this.cn == 34 || this.cn == 35 || this.cn == 37 || this.cn == 38) {
                            cheatboost2 = 17;
                        }
                        accelboost = this.aiaccsp[this.cn] - (this.level[this.cn] - 1) - cheatboost2;
                    }
                    if (accelboost > 75) {
                        accelboost = 75;
                    }
                    f5 *= (0.76 + accelboost * 0.24 / 75.0);  // cast: bytecode-verified
                }
                float accelmod = 1.0f;
                if (this.xt.careermode && checkpoints.stage == 24 && this.cn != 19) {
                    float gripmod3 = (this.grip[this.cn] - 28.5f) / 100.0f;
                    if (gripmod3 < 0.55f) {
                        gripmod3 = 0.55f;
                    }
                    if (gripmod3 > 1.0f) {
                        gripmod3 = 1.0f;
                    }
                    final float gripaffect3 = (gripmod3 - 0.55f) / 0.45f;
                    accelmod = 0.25f + 0.55f * gripaffect3;
                }
                if (!this.forcehandb) {
                    this.initialspeed = this.speed;
                    this.teletimer = 0;
                }
                if (control.down && !this.forcehandb) {
                    if (this.speed > 0.0f) {
                        this.speed -= this.handb[this.cn] / 2.0f;
                    }
                    else {
                        final int maxgear = 2;
                        final float[] realspeed = new float[3];
                        final float[] realacelf = new float[3];
                        int accelboost2 = 0;
                        if (this.xt.careermode) {
                            int shadnegate2 = 0;
                            if (this.shadowcar) {
                                shadnegate2 = 50;
                            }
                            int cheatboost3 = 0;
                            if (this.im == 0) {
                                if (this.cn == 32) {
                                    cheatboost3 = 15;
                                }
                                if (this.cn == 33) {
                                    cheatboost3 = 12;
                                }
                                if (this.cn == 31 || this.cn == 36) {
                                    cheatboost3 = 10;
                                }
                                if (this.cn == 34 || this.cn == 35 || this.cn == 37 || this.cn == 38) {
                                    cheatboost3 = 17;
                                }
                            }
                            accelboost2 = this.aiaccsp[this.cn] - (this.level[this.cn] - 1) - shadnegate2 - cheatboost3;
                        }
                        if (accelboost2 > 100) {
                            accelboost2 = 100;
                        }
                        final double[] spdportion = { this.swits[this.cn][0] / (double)this.swits[this.cn][1], this.swits[this.cn][1] / (double)this.swits[this.cn][2] };
                        final float[] accportion = { this.acelf[this.cn][1] / this.acelf[this.cn][0], this.acelf[this.cn][2] / this.acelf[this.cn][1] };
                        final double[] increment = { (0.97 - spdportion[0]) / 100.0, (0.97 - spdportion[1]) / 100.0 };
                        final float[] accincre = { (0.97f - accportion[0]) / 100.0f, (0.97f - accportion[1]) / 100.0f };
                        realspeed[0] = (float)(this.swits[this.cn][0] + accelboost2 * increment[0] * this.swits[this.cn][1]);
                        realspeed[1] = (float)(this.swits[this.cn][1] + accelboost2 * increment[1] * this.swits[this.cn][2]);
                        realspeed[2] = (float)this.swits[this.cn][2];
                        realacelf[0] = this.acelf[this.cn][0];
                        realacelf[1] = this.acelf[this.cn][1] + accelboost2 * accincre[0] * this.acelf[this.cn][0];
                        realacelf[2] = this.acelf[this.cn][2] + accelboost2 * accincre[1] * this.acelf[this.cn][1];
                        final float[] thespeed = new float[3];
                        for (int a = 0; a < 3; ++a) {
                            thespeed[a] = realspeed[a];
                        }
                        int k2 = 0;
                        int j3 = 0;
                        do {
                            if (this.speed <= -(thespeed[j3] / 2.0f + f5 * thespeed[j3] / 196.0f)) {
                                ++k2;
                            }
                        } while (++j3 < maxgear);
                        if (k2 != maxgear) {
                            this.speed -= realacelf[k2] * accelmod / 2.0f + f5 * realacelf[k2] * accelmod / 196.0f;
                        }
                        else {
                            this.speed = -(thespeed[maxgear - 1] / 2.0f + f5 * thespeed[maxgear - 1] / 196.0f);
                        }
                    }
                }
                if (control.up && !this.forcehandb) {
                    if (this.speed < 0.0f) {
                        this.speed += this.handb[this.cn];
                    }
                    else {
                        final int maxgear = 3;
                        final float[] thespeed2 = new float[3];
                        for (int a2 = 0; a2 < 3; ++a2) {
                            thespeed2[a2] = (float)this.swits[this.cn][a2];
                        }
                        int l2 = 0;
                        int k3 = 0;
                        do {
                            if (this.speed >= thespeed2[k3] / 2.0f + f5 * thespeed2[k3] / 196.0f) {
                                ++l2;
                            }
                        } while (++k3 < maxgear);
                        if (l2 != maxgear) {
                            this.speed += this.acelf[this.cn][l2] * accelmod / 2.0f + f5 * this.acelf[this.cn][l2] * accelmod / 196.0f;
                        }
                        else {
                            this.speed = thespeed2[maxgear - 1] / 2.0f + f5 * thespeed2[maxgear - 1] / 196.0f;
                        }
                    }
                }
                float changeby = this.handb[this.cn];
                if (this.forcehandb) {
                    changeby = Math.abs(this.initialspeed) / 20.0f;
                    ++this.teletimer;
                }
                if ((control.handb || this.forcehandb) && Math.abs(this.speed) > changeby) {
                    if (this.speed < 0.0f) {
                        this.speed += changeby;
                    }
                    else {
                        this.speed -= changeby;
                    }
                }
                if (this.loop == -1 && conto.y < 100) {
                    if (control.left) {
                        if (!this.pl) {
                            if (this.lcomp == 0.0f) {
                                this.lcomp = 5.0f * stuntpower;
                            }
                            if (this.lcomp < 20.0f) {
                                this.lcomp += 2.0f * stuntpower;
                            }
                        }
                    }
                    else {
                        if (this.lcomp > 0.0f) {
                            this.lcomp -= 2.0f * stuntpower;
                        }
                        this.pl = false;
                    }
                    if (control.right) {
                        if (!this.pr) {
                            if (this.rcomp == 0.0f) {
                                this.rcomp = 5.0f * stuntpower;
                            }
                            if (this.rcomp < 20.0f) {
                                this.rcomp += 2.0f * stuntpower;
                            }
                        }
                    }
                    else {
                        if (this.rcomp > 0.0f) {
                            this.rcomp -= 2.0f * stuntpower;
                        }
                        this.pr = false;
                    }
                    if (control.up) {
                        if (!this.pu) {
                            if (this.ucomp == 0.0f) {
                                this.ucomp = 5.0f * stuntpower;
                            }
                            if (this.ucomp < 20.0f) {
                                this.ucomp += 2.0f * stuntpower;
                            }
                        }
                    }
                    else {
                        if (this.ucomp > 0.0f) {
                            this.ucomp -= 2.0f * stuntpower;
                        }
                        this.pu = false;
                    }
                    if (control.down) {
                        if (!this.pd) {
                            if (this.dcomp == 0.0f) {
                                this.dcomp = 5.0f * stuntpower;
                            }
                            if (this.dcomp < 20.0f) {
                                this.dcomp += 2.0f * stuntpower;
                            }
                        }
                    }
                    else {
                        if (this.dcomp > 0.0f) {
                            this.dcomp -= 2.0f * stuntpower;
                        }
                        this.pd = false;
                    }
                    this.pzy += ((this.dcomp - this.ucomp) * this.m.cos(this.pxy));  // cast: bytecode-verified
                    if (flag) {
                        conto.xz += ((this.dcomp - this.ucomp) * this.m.sin(this.pxy));  // cast: bytecode-verified
                    }
                    else {
                        conto.xz -= ((this.dcomp - this.ucomp) * this.m.sin(this.pxy));  // cast: bytecode-verified
                    }
                    this.pxy += (this.rcomp - this.lcomp);  // cast: bytecode-verified
                }
            }
        }
        float f6 = 20.0f * this.speed / (154.0f * this.simag[this.cn]);
        if (f6 > 20.0f) {
            f6 = 20.0f;
        }
        conto.wzy -= f6;  // cast: bytecode-verified
        if (conto.wzy < -45) {
            conto.wzy += 45;
        }
        if (conto.wzy > 45) {
            conto.wzy -= 45;
        }
        double turnmod = 1.0;
        if (this.xt.careermode && checkpoints.stage == 24 && this.cn != 19) {
            float gripmod3 = (this.grip[this.cn] - 28.5f) / 100.0f;
            if (gripmod3 < 0.55f) {
                gripmod3 = 0.55f;
            }
            if (gripmod3 > 1.0f) {
                gripmod3 = 1.0f;
            }
            final float gripaffect3 = (gripmod3 - 0.55f) / 0.45f;
            turnmod = 0.7 + gripaffect3 * 0.25f;
        }
        final double turningpower = this.turn[this.cn] * turnmod;
        if (control.right) {
            conto.wxz -= turningpower;
            if (conto.wxz < -36.0) {
                conto.wxz = -36.0;
            }
        }
        if (control.left) {
            conto.wxz += turningpower;
            if (conto.wxz > 36.0) {
                conto.wxz = 36.0;
            }
        }
        if (conto.wxz != 0.0 && !control.left && !control.right) {
            if (Math.abs(this.speed) < 10.0f) {
                if (Math.abs(conto.wxz) == 1.0) {
                    conto.wxz = 0.0;
                }
                if (conto.wxz > 0.0) {
                    --conto.wxz;
                }
                if (conto.wxz < 0.0) {
                    ++conto.wxz;
                }
            }
            else {
                if (Math.abs(conto.wxz) < turningpower * 2.0) {
                    conto.wxz = 0.0;
                }
                if (conto.wxz > 0.0) {
                    conto.wxz -= turningpower * 2.0;
                }
                if (conto.wxz < 0.0) {
                    conto.wxz += turningpower * 2.0;
                }
            }
        }
        float i3 = (float)(int)(3600.0f / (this.speed * this.speed));
        if (i3 < 5.0f) {
            i3 = 5.0f;
        }
        if (this.speed < 0.0f) {
            i3 = -i3;
        }
        if (this.wtouch) {
            if (!this.capsized) {
                if (!control.handb) {
                    this.fxz = (int)((float)conto.wxz / (i3 * 3.0f));
                }
                else {
                    this.fxz = (int)((float)conto.wxz / i3);
                }
                conto.xz += ((float)conto.wxz / i3);  // cast: bytecode-verified
            }
            this.wtouch = false;
            this.gtouch = false;
        }
        else {
            conto.xz += this.fxz;
        }
        if (this.speed > 30.0f || this.speed < -100.0f) {
            while (Math.abs(this.mxz - this.cxz) > 180) {
                if (this.cxz > this.mxz) {
                    this.cxz -= 360;
                }
                else {
                    if (this.cxz >= this.mxz) {
                        continue;
                    }
                    this.cxz += 360;
                }
            }
            if (Math.abs(this.mxz - this.cxz) < 30) {
                this.cxz += ((this.mxz - this.cxz) / 4.0f);  // cast: bytecode-verified
            }
            else {
                if (this.cxz > this.mxz) {
                    this.cxz -= 10;
                }
                if (this.cxz < this.mxz) {
                    this.cxz += 10;
                }
            }
        }
        final float[] af = new float[4];
        final float[] af2 = new float[4];
        final float[] af3 = new float[4];
        float gravity = 7.0f;
        final float xgravity = 0.0f;
        final float zgravity = 0.0f;
        if (this.xt.careermode) {
            if (checkpoints.stage == 15 && !this.xt.bonstage) {
                if (conto.outoftrack) {
                    gravity = 2.0f;
                }
                else {
                    gravity = 10.0f;
                }
            }
            if (checkpoints.stage == 21 && ((!this.xt.entered[this.im] && this.nostunts <= 1) || this.xt.crumblefail[this.im])) {
                gravity = 50.0f;
            }
            if (checkpoints.stage == 23 && this.xt.bossbattle && this.xt.cstimer >= 4 && this.im == 0) {
                gravity = 25.0f;
            }
        }
        int l3 = 0;
        do {
            af[l3] = (float)(conto.keyx[l3] + conto.x);
            af3[l3] = (float)(i2 + conto.y);
            af2[l3] = (float)(conto.z + conto.keyz[l3]);
            final float[] scy = this.scy;
            final int n = l3;
            scy[n] += gravity;
            final float[] scx = this.scx;
            final int n2 = l3;
            scx[n2] += xgravity;
            final float[] scz = this.scz;
            final int n3 = l3;
            scz[n3] += zgravity;
        } while (++l3 < 4);
        this.rot(af, af3, conto.x, conto.y, this.pxy, 4);
        this.rot(af3, af2, conto.y, conto.z, this.pzy, 4);
        this.rot(af, af2, conto.x, conto.z, conto.xz, 4);
        boolean flag5 = false;
        final double d = 0.0;
        final int i4 = (int)((this.scx[0] + this.scx[1] + this.scx[2] + this.scx[3]) / 4.0f);
        final int j4 = (int)((this.scz[0] + this.scz[1] + this.scz[2] + this.scz[3]) / 4.0f);
        for (int k4 = 0; k4 < 4; ++k4) {
            if (this.scx[k4] - i4 > 200.0f) {
                this.scx[k4] = (float)(200 + i4);
            }
            if (this.scx[k4] - i4 < -200.0f) {
                this.scx[k4] = (float)(i4 - 200);
            }
            if (this.scz[k4] - j4 > 200.0f) {
                this.scz[k4] = (float)(200 + j4);
            }
            if (this.scz[k4] - j4 < -200.0f) {
                this.scz[k4] = (float)(j4 - 200);
            }
        }
        for (int k4 = 0; k4 < 4; ++k4) {
            final float[] array = af3;
            final int n4 = k4;
            array[n4] += this.scy[k4];
            final float[] array2 = af;
            final int n5 = k4;
            array2[n5] += (this.scx[0] + this.scx[1] + this.scx[2] + this.scx[3]) / 4.0f;
            final float[] array3 = af2;
            final int n6 = k4;
            array3[n6] += (this.scz[0] + this.scz[1] + this.scz[2] + this.scz[3]) / 4.0f;
        }
        this.roadtyp = 1;
        for (int l4 = 0; l4 < trackers.nt; ++l4) {
            if (Math.abs(trackers.zy[l4]) != 90 && Math.abs(trackers.xy[l4]) != 90 && Math.abs(conto.x - trackers.x[l4]) < trackers.radx[l4] && Math.abs(conto.z - trackers.z[l4]) < trackers.radz[l4] && Math.abs(conto.y - trackers.y[l4]) < 1000) {
                this.roadtyp = trackers.skd[l4];
            }
        }
        if (this.mtouch) {
            float f7 = this.grip[this.cn];
            f7 -= Math.abs(this.txz - conto.xz) * this.speed / 250.0f;
            if (control.handb) {
                f7 -= Math.abs(this.txz - conto.xz) * 4;
            }
            if (f7 < this.grip[this.cn]) {
                if (this.skid != 2) {
                    this.skid = 1;
                }
                this.speed -= this.speed / 100.0f;
            }
            else if (this.skid == 1) {
                this.skid = 2;
            }
            if (this.roadtyp == 1) {
                f7 *= 0.75;
            }
            if (this.roadtyp == 2) {
                f7 *= 0.55;  // cast: bytecode-verified
            }
            if (this.xt.careermode) {
                if (checkpoints.stage == 16 && this.cn != 3 && this.cn != 15 && this.cn != 38) {
                    float gripmod4 = (this.grip[this.cn] - 31.0f) / 54.0f;
                    if (gripmod4 < 0.5f) {
                        gripmod4 = 0.5f;
                    }
                    if (gripmod4 > 1.0f) {
                        gripmod4 = 1.0f;
                    }
                    final float gripaffect4 = (gripmod4 - 0.5f) / 0.5f;
                    if (this.roadtyp == 0) {
                        this.speedmulti = 0.8f + 0.1f * gripaffect4;
                        f7 *= (double)(0.35f + gripaffect4 * 0.5f);  // cast: bytecode-verified (the jar multiplies in double; same result, see README)
                    }
                    if (this.roadtyp == 1) {
                        this.speedmulti = 0.25f + 0.65f * gripaffect4;
                        f7 *= (double)(0.6f + gripaffect4 * 0.1f);  // cast: bytecode-verified (the jar multiplies in double; same result, see README)
                    }
                    if (this.roadtyp == 2 || this.roadtyp == 3 || this.roadtyp == 4) {
                        this.speedmulti = 0.8f + 0.1f * gripaffect4;
                        f7 *= (double)(0.25f + gripaffect4 * 0.2f);  // cast: bytecode-verified (the jar multiplies in double; same result, see README)
                    }
                }
                if (checkpoints.stage == 18 && this.cn != 16 && !this.xt.bonusstage[3]) {
                    float gripmod4 = (this.grip[this.cn] - 33.5f) / 59.0f;
                    if (gripmod4 < 0.5f) {
                        gripmod4 = 0.5f;
                    }
                    if (gripmod4 > 1.0f) {
                        gripmod4 = 1.0f;
                    }
                    final float gripaffect4 = (gripmod4 - 0.5f) / 0.5f;
                    this.speedmulti = 0.45f + gripaffect4 * 0.45f;
                    this.powermulti = 2.5f - gripaffect4 * 1.3f;
                }
                if (checkpoints.stage == 24 && this.cn != 19) {
                    float gripmod4 = (this.grip[this.cn] - 28.5f) / 100.0f;
                    if (gripmod4 < 0.55f) {
                        gripmod4 = 0.55f;
                    }
                    if (gripmod4 > 1.0f) {
                        gripmod4 = 1.0f;
                    }
                    final float gripaffect4 = (gripmod4 - 0.55f) / 0.45f;
                    this.speedmulti = 0.8f + gripaffect4 * 0.2f;
                }
            }
            int j5 = -(int)(this.speed * this.m.sin(conto.xz) * this.m.cos(this.pzy));
            int k5 = (int)(this.speed * this.m.cos(conto.xz) * this.m.cos(this.pzy));
            int i5 = -(int)(this.speed * this.m.sin(this.pzy));
            if (this.capsized || this.dest || checkpoints.haltall) {
                j5 = 0;
                k5 = 0;
                i5 = 0;
                f7 = this.grip[this.cn] / 5.0f;
                if (this.speed > 0.0f) {
                    this.speed -= 2.0f;
                }
                else {
                    this.speed += 2.0f;
                }
            }
            if (f7 < 1.0f) {
                f7 = 1.0f;
            }
            float f8 = 0.0f;
            float f9 = 0.0f;
            int l5 = 0;
            do {
                if (Math.abs(this.scx[l5] - j5) > f7) {
                    if (this.scx[l5] < j5) {
                        final float[] scx2 = this.scx;
                        final int n7 = l5;
                        scx2[n7] += f7;
                    }
                    else {
                        final float[] scx3 = this.scx;
                        final int n8 = l5;
                        scx3[n8] -= f7;
                    }
                }
                else {
                    this.scx[l5] = (float)j5;
                }
                if (Math.abs(this.scz[l5] - k5) > f7) {
                    if (this.scz[l5] < k5) {
                        final float[] scz2 = this.scz;
                        final int n9 = l5;
                        scz2[n9] += f7;
                    }
                    else {
                        final float[] scz3 = this.scz;
                        final int n10 = l5;
                        scz3[n10] -= f7;
                    }
                }
                else {
                    this.scz[l5] = (float)k5;
                }
                if (Math.abs(this.scy[l5] - i5) > f7) {
                    if (this.scy[l5] < i5) {
                        final float[] scy2 = this.scy;
                        final int n11 = l5;
                        scy2[n11] += f7;
                    }
                    else {
                        final float[] scy3 = this.scy;
                        final int n12 = l5;
                        scy3[n12] -= f7;
                    }
                }
                else {
                    this.scy[l5] = (float)i5;
                }
                if (f7 < this.grip[this.cn]) {
                    if (this.txz != conto.xz) {
                        ++this.dcnt;
                    }
                    else if (this.dcnt != 0) {
                        this.dcnt = 0;
                    }
                    if (this.dcnt > 40.0f * f7 / this.grip[this.cn] || this.capsized) {
                        float f10 = 1.0f;
                        if (this.roadtyp != 0) {
                            f10 = 1.2f;
                        }
                        if (this.m.random() > 0.075) {
                            conto.dust(l5, af[l5], af3[l5], af2[l5], this.scx[l5], this.scz[l5], f10 * this.simag[this.cn], true, (int)this.tilt);
                            if (this.im == 0 && !this.capsized) {
                                this.xt.skid(this.roadtyp, (float)Math.sqrt(this.scx[l5] * this.scx[l5] + this.scz[l5] * this.scz[l5]));
                            }
                        }
                    }
                    else {
                        if (this.roadtyp == 1 && this.m.random() > 0.08499999999999999) {
                            conto.dust(l5, af[l5], af3[l5], af2[l5], this.scx[l5], this.scz[l5], 1.1f * this.simag[this.cn], false, (int)this.tilt);
                        }
                        if ((this.roadtyp == 2 || this.roadtyp == 3) && this.m.random() > 0.06999999999999999) {
                            conto.dust(l5, af[l5], af3[l5], af2[l5], this.scx[l5], this.scz[l5], 1.15f * this.simag[this.cn], false, (int)this.tilt);
                        }
                    }
                }
                else {
                    if (this.dcnt != 0) {
                        this.dcnt -= 2;
                        if (this.dcnt < 0) {
                            this.dcnt = 0;
                        }
                    }
                    float norandom = this.m.random();
                    if (this.isabot || this.xt.makebot) {
                        norandom = 0.5f;
                    }
                    if (this.roadtyp == 3) {
                        final int k6 = (int)(norandom * 4.0f);
                        this.scy[k6] = (float)(-100.0f * norandom * (this.speed / this.swits[this.cn][2]) * (bounciness - 0.3));
                    }
                    if (this.roadtyp == 4) {
                        final int k6 = (int)(norandom * 4.0f);
                        this.scy[k6] = (float)(-150.0f * norandom * (this.speed / this.swits[this.cn][2]) * (bounciness - 0.3));
                    }
                }
                f8 += this.scx[l5];
                f9 += this.scz[l5];
            } while (++l5 < 4);
            this.txz = conto.xz;
            if (f8 > 0.0f) {
                i = -1;
            }
            else {
                i = 1;
            }
            double d2 = f9 / Math.sqrt(f8 * f8 + f9 * f9);
            if (d2 > 1.0) {
                d2 = 1.0;
            }
            if (d2 < -1.0) {
                d2 = -1.0;
            }
            this.mxz = (int)(Math.acos(d2) / 0.017453292519943295 * i);
            if (this.xt.speedhack[this.im] > 0) {
                this.skid = 0;
            }
            if (this.skid == 2) {
                if (!this.capsized) {
                    f8 /= 4.0f;
                    f9 /= 4.0f;
                    if (flag2) {
                        this.speed = -((float)Math.sqrt(f8 * f8 + f9 * f9) * this.m.cos(this.mxz - conto.xz));
                    }
                    else {
                        this.speed = (float)Math.sqrt(f8 * f8 + f9 * f9) * this.m.cos(this.mxz - conto.xz);
                    }
                }
                this.skid = 0;
            }
            if (this.capsized && f8 == 0.0f && f9 == 0.0f) {
                this.roadtyp = 0;
            }
            this.mtouch = false;
            flag5 = true;
        }
        else if (this.skid != 2) {
            this.skid = 2;
        }
        int i6 = 0;
        final boolean[] aflag = new boolean[4];
        int l6 = 0;
        do {
            if (af3[l6] > this.groundlevel - 5.0f) {
                ++i6;
                this.wtouch = true;
                this.gtouch = true;
                if (!flag5 && this.scy[l6] != 7.0f) {
                    float f11 = this.scy[l6] / 333.33f;
                    if (f11 > 0.3) {
                        f11 = 0.3f;
                    }
                    if (this.roadtyp == 0) {
                        f11 += 1.1;  // cast: bytecode-verified
                    }
                    else {
                        f11 += 1.2;  // cast: bytecode-verified
                    }
                    conto.dust(l6, af[l6], af3[l6], af2[l6], this.scx[l6], this.scz[l6], f11 * this.simag[this.cn], true, 0);
                }
                af3[l6] = this.groundlevel;
                float f12 = 0.0f;
                do {
                    if (l6 != f12 && af3[(int)f12] <= this.groundlevel - 5.0f) {
                        final float[] array4 = af3;
                        final int n13 = (int)f12;
                        array4[n13] -= af3[l6] - this.groundlevel;
                    }
                } while (++f12 < 4.0f);
                f12 = Math.abs(this.m.sin(this.pxy)) + Math.abs(this.m.sin(this.pzy));
                f12 /= 3.0f;
                if (f12 > 0.4) {
                    f12 = 0.4f;
                }
                f12 += bounciness;
                if (f12 < 1.1) {
                    f12 = 1.1f;
                }
                this.regy(l6, Math.abs(this.scy[l6] * f12), conto, 1);
                if (this.scy[l6] > 0.0f) {
                    final float[] scy4 = this.scy;
                    final int n14 = l6;
                    scy4[n14] -= Math.abs(this.scy[l6] * f12);
                }
            }
            aflag[l6] = false;
        } while (++l6 < 4);
        int coldetection = trackers.nt;
        if (this.xt.careermode && checkpoints.stage == 11 && !this.xt.bonusstage[1] && this.im >= 2 && this.im <= 4) {
            coldetection = 0;
        }
        l6 = 0;
        for (int j6 = 0; j6 < coldetection; ++j6) {
            int l7 = 0;
            int j7 = 0;
            int i7 = 0;
            do {
                if (!aflag[i7] && af[i7] > trackers.x[j6] - trackers.radx[j6] && af[i7] < trackers.x[j6] + trackers.radx[j6] && af2[i7] > trackers.z[j6] - trackers.radz[j6] && af2[i7] < trackers.z[j6] + trackers.radz[j6] && af3[i7] > trackers.y[j6] - trackers.rady[j6] && af3[i7] < trackers.y[j6] + trackers.rady[j6]) {
                    float wallmulti = 1.0f;
                    if (this.xt.careermode) {
                        if (this.xt.averagelevel >= 8 && checkpoints.stage != 14 && checkpoints.stage != 10) {
                            if (this.xt.averagelevel <= 58) {
                                wallmulti = 1.0f + (this.xt.averagelevel - 8) * 0.06f;
                            }
                            else {
                                wallmulti = 4.0f + (this.xt.averagelevel - 58) * 0.04f;
                            }
                        }
                        if (this.xt.bonusstage[3]) {
                            if (this.im == 0) {
                                wallmulti = 10.0f;
                            }
                            else {
                                wallmulti = 4.0f;
                            }
                        }
                    }
                    float reddmg = 1.0f;
                    if (this.xt.careermode) {
                        final float[] griparray = new float[this.xt.nplayers];
                        for (int a3 = 0; a3 < this.xt.nplayers; ++a3) {
                            griparray[a3] = this.gripreset[this.xt.sc[a3]];
                        }
                        float sumgrip = 0.0f;
                        float[] array5;
                        for (int length = (array5 = griparray).length, n15 = 0; n15 < length; ++n15) {
                            final float gripadd = array5[n15];
                            sumgrip += gripadd;
                        }
                        final float avgstartgrip = sumgrip / this.xt.nplayers;
                        final float expectgrip = avgstartgrip + (this.xt.averagelevel - 1) * 0.2f;
                        if (this.grip[this.cn] >= expectgrip) {
                            final float mainboistat = (this.grip[this.cn] - 10.0f) / 20.0f;
                            final float targetstat = (expectgrip - 10.0f) / 20.0f;
                            final float difference = (mainboistat - targetstat) * 37.0f;
                            reddmg = 1.0f - difference / 20.0f;
                            if (reddmg < 0.2f) {
                                reddmg = 0.2f;
                            }
                        }
                        else {
                            final float mainboistat = (this.grip[this.cn] - 10.0f) / 20.0f;
                            final float targetstat = (expectgrip - 10.0f) / 20.0f;
                            final float difference = (targetstat - mainboistat) * 37.0f;
                            final float rapiddead = reddmg = 1.0f + difference / 20.0f;
                            if (reddmg > 1.5f) {
                                reddmg = 1.5f;
                            }
                        }
                    }
                    float totaldmgmod = wallmulti * reddmg;
                    if (this.im == 0 && this.xt.careermode && checkpoints.stage == 6) {
                        int thelevel = this.xt.averagelevel + 2;
                        if (thelevel > 20) {
                            thelevel = 20;
                        }
                        if (this.xt.wallimmunity > 0 && totaldmgmod > 0.35f + thelevel * 0.05f) {
                            totaldmgmod = 0.35f + thelevel * 0.05f;
                        }
                    }
                    if (trackers.xy[j6] == 0 && trackers.zy[j6] == 0 && trackers.y[j6] < (int)this.groundlevel && af3[i7] > trackers.y[j6] - 5) {
                        ++j7;
                        this.wtouch = true;
                        this.gtouch = true;
                        if (!flag5 && this.scy[i7] != 7.0f) {
                            float f13 = this.scy[i7] / 333.33f;
                            if (f13 > 0.3) {
                                f13 = 0.3f;
                            }
                            if (this.roadtyp == 0) {
                                f13 += 1.1;  // cast: bytecode-verified
                            }
                            else {
                                f13 += 1.2;  // cast: bytecode-verified
                            }
                            conto.dust(i7, af[i7], af3[i7], af2[i7], this.scx[i7], this.scz[i7], f13 * this.simag[this.cn], true, 0);
                        }
                        af3[i7] = (float)trackers.y[j6];
                        float f14 = 0.0f;
                        do {
                            if (i7 != f14 && af3[(int)f14] <= trackers.y[j6] - 5) {
                                final float[] array6 = af3;
                                final int n16 = (int)f14;
                                array6[n16] -= af3[i7] - trackers.y[j6];
                            }
                        } while (++f14 < 4.0f);
                        f14 = Math.abs(this.m.sin(this.pxy)) + Math.abs(this.m.sin(this.pzy));
                        f14 /= 3.0f;
                        if (f14 > 0.4) {
                            f14 = 0.4f;
                        }
                        f14 += bounciness;
                        if (f14 < 1.1) {
                            f14 = 1.1f;
                        }
                        this.regy(i7, Math.abs(this.scy[i7] * f14), conto, 1);
                        if (this.scy[i7] > 0.0f) {
                            final float[] scy5 = this.scy;
                            final int n17 = i7;
                            scy5[n17] -= Math.abs(this.scy[i7] * f14);
                        }
                        aflag[i7] = true;
                    }
                    if (trackers.zy[j6] == -90 && af2[i7] < trackers.z[j6] + trackers.radz[j6] && this.scz[i7] < 0.0f) {
                        af2[i7] = (float)(trackers.z[j6] + trackers.radz[j6]);
                        float f15 = 0.0f;
                        do {
                            if (i7 != f15 && af2[(int)f15] >= trackers.z[j6] + trackers.radz[j6]) {
                                final float[] array7 = af2;
                                final int n18 = (int)f15;
                                array7[n18] -= af2[i7] - (trackers.z[j6] + trackers.radz[j6]);
                            }
                        } while (++f15 < 4.0f);
                        f15 = Math.abs(this.m.cos(this.pxy)) + Math.abs(this.m.cos(this.pzy));
                        f15 /= 4.0f;
                        if (f15 > 0.3) {
                            f15 = 0.3f;
                        }
                        if (flag5) {
                            f15 = 0.0f;
                        }
                        f15 += (bounciness - 0.2);  // cast: bytecode-verified
                        if (f15 < 1.1) {
                            f15 = 1.1f;
                        }
                        this.regz(i7, Math.abs(this.scz[i7] * f15 * trackers.dam[j6] * totaldmgmod), conto, 1);
                        final float[] scz4 = this.scz;
                        final int n19 = i7;
                        scz4[n19] += Math.abs(this.scz[i7] * f15);
                        this.skid = 2;
                        flag3 = true;
                        aflag[i7] = true;
                        control.wall = j6;
                    }
                    if (trackers.zy[j6] == 90 && af2[i7] > trackers.z[j6] - trackers.radz[j6] && this.scz[i7] > 0.0f) {
                        af2[i7] = (float)(trackers.z[j6] - trackers.radz[j6]);
                        float f16 = 0.0f;
                        do {
                            if (i7 != f16 && af2[(int)f16] <= trackers.z[j6] - trackers.radz[j6]) {
                                final float[] array8 = af2;
                                final int n20 = (int)f16;
                                array8[n20] -= af2[i7] - (trackers.z[j6] - trackers.radz[j6]);
                            }
                        } while (++f16 < 4.0f);
                        f16 = Math.abs(this.m.cos(this.pxy)) + Math.abs(this.m.cos(this.pzy));
                        f16 /= 4.0f;
                        if (f16 > 0.3) {
                            f16 = 0.3f;
                        }
                        if (flag5) {
                            f16 = 0.0f;
                        }
                        f16 += (bounciness - 0.2);  // cast: bytecode-verified
                        if (f16 < 1.1) {
                            f16 = 1.1f;
                        }
                        this.regz(i7, -Math.abs(this.scz[i7] * f16 * trackers.dam[j6] * totaldmgmod), conto, 1);
                        final float[] scz5 = this.scz;
                        final int n21 = i7;
                        scz5[n21] -= Math.abs(this.scz[i7] * f16);
                        this.skid = 2;
                        flag3 = true;
                        aflag[i7] = true;
                        control.wall = j6;
                    }
                    if (trackers.xy[j6] == -90 && af[i7] < trackers.x[j6] + trackers.radx[j6] && this.scx[i7] < 0.0f) {
                        af[i7] = (float)(trackers.x[j6] + trackers.radx[j6]);
                        float f17 = 0.0f;
                        do {
                            if (i7 != f17 && af[(int)f17] >= trackers.x[j6] + trackers.radx[j6]) {
                                final float[] array9 = af;
                                final int n22 = (int)f17;
                                array9[n22] -= af[i7] - (trackers.x[j6] + trackers.radx[j6]);
                            }
                        } while (++f17 < 4.0f);
                        f17 = Math.abs(this.m.cos(this.pxy)) + Math.abs(this.m.cos(this.pzy));
                        f17 /= 4.0f;
                        if (f17 > 0.3) {
                            f17 = 0.3f;
                        }
                        if (flag5) {
                            f17 = 0.0f;
                        }
                        f17 += (bounciness - 0.2);  // cast: bytecode-verified
                        if (f17 < 1.1) {
                            f17 = 1.1f;
                        }
                        this.regx(i7, Math.abs(this.scx[i7] * f17 * trackers.dam[j6] * totaldmgmod), conto, 1);
                        final float[] scx4 = this.scx;
                        final int n23 = i7;
                        scx4[n23] += Math.abs(this.scx[i7] * f17);
                        this.skid = 2;
                        flag3 = true;
                        aflag[i7] = true;
                        control.wall = j6;
                    }
                    if (trackers.xy[j6] == 90 && af[i7] > trackers.x[j6] - trackers.radx[j6] && this.scx[i7] > 0.0f) {
                        af[i7] = (float)(trackers.x[j6] - trackers.radx[j6]);
                        float f18 = 0.0f;
                        do {
                            if (i7 != f18 && af[(int)f18] <= trackers.x[j6] - trackers.radx[j6]) {
                                final float[] array10 = af;
                                final int n24 = (int)f18;
                                array10[n24] -= af[i7] - (trackers.x[j6] - trackers.radx[j6]);
                            }
                        } while (++f18 < 4.0f);
                        f18 = Math.abs(this.m.cos(this.pxy)) + Math.abs(this.m.cos(this.pzy));
                        f18 /= 4.0f;
                        if (f18 > 0.3) {
                            f18 = 0.3f;
                        }
                        if (flag5) {
                            f18 = 0.0f;
                        }
                        f18 += (bounciness - 0.2);  // cast: bytecode-verified
                        if (f18 < 1.1) {
                            f18 = 1.1f;
                        }
                        this.regx(i7, -Math.abs(this.scx[i7] * f18 * trackers.dam[j6] * totaldmgmod), conto, 1);
                        final float[] scx5 = this.scx;
                        final int n25 = i7;
                        scx5[n25] -= Math.abs(this.scx[i7] * f18);
                        this.skid = 2;
                        flag3 = true;
                        aflag[i7] = true;
                        control.wall = j6;
                    }
                    if (trackers.zy[j6] != 0 && trackers.zy[j6] != 90 && trackers.zy[j6] != -90) {
                        final int l8 = 90 + trackers.zy[j6];
                        float f19 = 1.0f + (50 - Math.abs(trackers.zy[j6])) / 30.0f;
                        if (f19 < 1.0f) {
                            f19 = 1.0f;
                        }
                        final float f20 = trackers.y[j6] + ((af3[i7] - trackers.y[j6]) * this.m.cos(l8) - (af2[i7] - trackers.z[j6]) * this.m.sin(l8));
                        float f21 = trackers.z[j6] + ((af3[i7] - trackers.y[j6]) * this.m.sin(l8) + (af2[i7] - trackers.z[j6]) * this.m.cos(l8));
                        if (f21 > trackers.z[j6] && f21 < trackers.z[j6] + 200) {
                            final float[] scy6 = this.scy;
                            final int n26 = i7;
                            scy6[n26] -= (f21 - trackers.z[j6]) / f19;
                            f21 = (float)trackers.z[j6];
                        }
                        if (f21 > trackers.z[j6] - 30) {
                            if (trackers.skd[j6] == 2) {
                                ++l7;
                            }
                            else {
                                ++l6;
                            }
                            this.wtouch = true;
                            this.gtouch = false;
                            if (!flag5 && this.roadtyp != 0) {
                                final float f22 = 1.4f;
                                conto.dust(i7, af[i7], af3[i7], af2[i7], this.scx[i7], this.scz[i7], f22 * this.simag[this.cn], true, 0);
                            }
                        }
                        af3[i7] = trackers.y[j6] + ((f20 - trackers.y[j6]) * this.m.cos(-l8) - (f21 - trackers.z[j6]) * this.m.sin(-l8));
                        af2[i7] = trackers.z[j6] + ((f20 - trackers.y[j6]) * this.m.sin(-l8) + (f21 - trackers.z[j6]) * this.m.cos(-l8));
                        aflag[i7] = true;
                    }
                    if (trackers.xy[j6] == 0 || trackers.xy[j6] == 90 || trackers.xy[j6] == -90) {
                        continue;
                    }
                    final int i8 = 90 + trackers.xy[j6];
                    float f23 = 1.0f + (50 - Math.abs(trackers.xy[j6])) / 30.0f;
                    if (f23 < 1.0f) {
                        f23 = 1.0f;
                    }
                    final float f24 = trackers.y[j6] + ((af3[i7] - trackers.y[j6]) * this.m.cos(i8) - (af[i7] - trackers.x[j6]) * this.m.sin(i8));
                    float f25 = trackers.x[j6] + ((af3[i7] - trackers.y[j6]) * this.m.sin(i8) + (af[i7] - trackers.x[j6]) * this.m.cos(i8));
                    if (f25 > trackers.x[j6] && f25 < trackers.x[j6] + 200) {
                        final float[] scy7 = this.scy;
                        final int n27 = i7;
                        scy7[n27] -= (f25 - trackers.x[j6]) / f23;
                        f25 = (float)trackers.x[j6];
                    }
                    if (f25 > trackers.x[j6] - 30) {
                        if (trackers.skd[j6] == 2) {
                            ++l7;
                        }
                        else {
                            ++l6;
                        }
                        this.wtouch = true;
                        this.gtouch = false;
                        if (!flag5 && this.roadtyp != 0) {
                            final float f26 = 1.4f;
                            conto.dust(i7, af[i7], af3[i7], af2[i7], this.scx[i7], this.scz[i7], f26 * this.simag[this.cn], true, 0);
                        }
                    }
                    af3[i7] = trackers.y[j6] + ((f24 - trackers.y[j6]) * this.m.cos(-i8) - (f25 - trackers.x[j6]) * this.m.sin(-i8));
                    af[i7] = trackers.x[j6] + ((f24 - trackers.y[j6]) * this.m.sin(-i8) + (f25 - trackers.x[j6]) * this.m.cos(-i8));
                    aflag[i7] = true;
                }
            } while (++i7 < 4);
            if (l7 == 4) {
                this.mtouch = true;
            }
            if (j7 == 4) {
                i6 = 4;
            }
        }
        if (l6 == 4) {
            this.mtouch = true;
        }
        int k7 = 0;
        int i9 = 0;
        int k8 = 0;
        int j8 = 0;
        if (this.scy[2] != this.scy[0]) {
            if (this.scy[2] < this.scy[0]) {
                i = -1;
            }
            else {
                i = 1;
            }
            final double d3 = Math.sqrt((af2[0] - af2[2]) * (af2[0] - af2[2]) + (af3[0] - af3[2]) * (af3[0] - af3[2]) + (af[0] - af[2]) * (af[0] - af[2])) / (Math.abs(conto.keyz[0]) + Math.abs(conto.keyz[2]));
            if (d3 >= 0.9998) {
                k7 = i;
            }
            else {
                k7 = (int)(Math.acos(d3) / 0.017453292519943295 * i);
            }
        }
        if (this.scy[3] != this.scy[1]) {
            if (this.scy[3] < this.scy[1]) {
                i = -1;
            }
            else {
                i = 1;
            }
            final double d4 = Math.sqrt((af2[1] - af2[3]) * (af2[1] - af2[3]) + (af3[1] - af3[3]) * (af3[1] - af3[3]) + (af[1] - af[3]) * (af[1] - af[3])) / (Math.abs(conto.keyz[1]) + Math.abs(conto.keyz[3]));
            if (d4 >= 0.9998) {
                i9 = i;
            }
            else {
                i9 = (int)(Math.acos(d4) / 0.017453292519943295 * i);
            }
        }
        if (this.scy[1] != this.scy[0]) {
            if (this.scy[1] < this.scy[0]) {
                i = -1;
            }
            else {
                i = 1;
            }
            final double d5 = Math.sqrt((af2[0] - af2[1]) * (af2[0] - af2[1]) + (af3[0] - af3[1]) * (af3[0] - af3[1]) + (af[0] - af[1]) * (af[0] - af[1])) / (Math.abs(conto.keyx[0]) + Math.abs(conto.keyx[1]));
            if (d5 >= 0.9998) {
                k8 = i;
            }
            else {
                k8 = (int)(Math.acos(d5) / 0.017453292519943295 * i);
            }
        }
        if (this.scy[3] != this.scy[2]) {
            if (this.scy[3] < this.scy[2]) {
                i = -1;
            }
            else {
                i = 1;
            }
            final double d6 = Math.sqrt((af2[2] - af2[3]) * (af2[2] - af2[3]) + (af3[2] - af3[3]) * (af3[2] - af3[3]) + (af[2] - af[3]) * (af[2] - af[3])) / (Math.abs(conto.keyx[2]) + Math.abs(conto.keyx[3]));
            if (d6 >= 0.9998) {
                j8 = i;
            }
            else {
                j8 = (int)(Math.acos(d6) / 0.017453292519943295 * i);
            }
        }
        if (flag3) {
            int j9;
            for (j9 = Math.abs(conto.xz + 45); j9 > 180; j9 -= 360) {}
            if (Math.abs(j9) > 90) {
                this.pmlt = 1;
            }
            else {
                this.pmlt = -1;
            }
            for (j9 = Math.abs(conto.xz - 45); j9 > 180; j9 -= 360) {}
            if (Math.abs(j9) > 90) {
                this.nmlt = 1;
            }
            else {
                this.nmlt = -1;
            }
        }
        conto.xz += (this.forca * (this.scz[0] * this.nmlt - this.scz[1] * this.pmlt + this.scz[2] * this.pmlt - this.scz[3] * this.nmlt + this.scx[0] * this.pmlt + this.scx[1] * this.nmlt - this.scx[2] * this.nmlt - this.scx[3] * this.pmlt));  // cast: bytecode-verified
        if (Math.abs(i9) > Math.abs(k7)) {
            k7 = i9;
        }
        if (Math.abs(j8) > Math.abs(k8)) {
            k8 = j8;
        }
        if (!this.mtouch && !this.isabot && (!this.xt.makebot || this.im > 0)) {
            final int zeroanglezy = Math.min(zyangle, 360 - zyangle);
            final int flipanglezy = Math.abs(zyangle - 180);
            if ((zeroanglezy <= flipanglezy && zyangle < 180) || (flipanglezy < zeroanglezy && zyangle >= 180)) {
                if (this.pzy > 0) {
                    this.pzy -= Math.abs(k7);
                }
                else {
                    this.pzy += Math.abs(k7);
                }
            }
            if ((zeroanglezy <= flipanglezy && zyangle >= 180) || (flipanglezy < zeroanglezy && zyangle < 180)) {
                if (this.pzy > 0) {
                    this.pzy += Math.abs(k7);
                }
                else {
                    this.pzy -= Math.abs(k7);
                }
            }
            final int zeroanglexy = Math.min(xyangle, 360 - xyangle);
            final int flipanglexy = Math.abs(xyangle - 180);
            if ((zeroanglexy <= flipanglexy && xyangle < 180) || (flipanglexy < zeroanglexy && xyangle >= 180)) {
                if (this.pxy > 0) {
                    this.pxy -= Math.abs(k8);
                }
                else {
                    this.pxy += Math.abs(k8);
                }
            }
            if ((zeroanglexy <= flipanglexy && xyangle >= 180) || (flipanglexy < zeroanglexy && xyangle < 180)) {
                if (this.pxy > 0) {
                    this.pxy += Math.abs(k8);
                }
                else {
                    this.pxy -= Math.abs(k8);
                }
            }
        }
        else {
            if (!flag) {
                this.pzy += k7;
            }
            else {
                this.pzy -= k7;
            }
            if (!flag4) {
                this.pxy += k8;
            }
            else {
                this.pxy -= k8;
            }
        }
        if (i6 == 4) {
            int k9 = 0;
            while (this.pzy < 360) {
                this.pzy += 360;
                conto.zy += 360;
            }
            while (this.pzy > 360) {
                this.pzy -= 360;
                conto.zy -= 360;
            }
            if (this.pzy < 190 && this.pzy > 170) {
                this.pzy = 180;
                conto.zy = 180;
                ++k9;
            }
            if (this.pzy > 350 || this.pzy < 10) {
                this.pzy = 0;
                conto.zy = 0;
                ++k9;
            }
            while (this.pxy < 360) {
                this.pxy += 360;
                conto.xy += 360;
            }
            while (this.pxy > 360) {
                this.pxy -= 360;
                conto.xy -= 360;
            }
            if (this.pxy < 190 && this.pxy > 170) {
                this.pxy = 180;
                conto.xy = 180;
                ++k9;
            }
            if (this.pxy > 350 || this.pxy < 10) {
                this.pxy = 0;
                conto.xy = 0;
                ++k9;
            }
            if (k9 == 2) {
                this.mtouch = true;
            }
        }
        if (!this.mtouch && this.wtouch) {
            if (this.cntouch == 10) {
                this.mtouch = true;
            }
            else {
                ++this.cntouch;
            }
        }
        else {
            this.cntouch = 0;
        }
        conto.y = (int)((af3[0] + af3[1] + af3[2] + af3[3]) / 4.0f - i2 * this.m.cos(this.pzy) * this.m.cos(this.pxy) + f3);
        if (flag) {
            i = -1;
        }
        else {
            i = 1;
        }
        conto.x = (int)((af[0] - conto.keyx[0] * this.m.cos(conto.xz) + i * conto.keyz[0] * this.m.sin(conto.xz) + af[1] - conto.keyx[1] * this.m.cos(conto.xz) + i * conto.keyz[1] * this.m.sin(conto.xz) + af[2] - conto.keyx[2] * this.m.cos(conto.xz) + i * conto.keyz[2] * this.m.sin(conto.xz) + af[3] - conto.keyx[3] * this.m.cos(conto.xz) + i * conto.keyz[3] * this.m.sin(conto.xz)) / 4.0f + i2 * this.m.sin(this.pxy) * this.m.cos(conto.xz) - i2 * this.m.sin(this.pzy) * this.m.sin(conto.xz) + f);
        conto.z = (int)((af2[0] - i * conto.keyz[0] * this.m.cos(conto.xz) - conto.keyx[0] * this.m.sin(conto.xz) + af2[1] - i * conto.keyz[1] * this.m.cos(conto.xz) - conto.keyx[1] * this.m.sin(conto.xz) + af2[2] - i * conto.keyz[2] * this.m.cos(conto.xz) - conto.keyx[2] * this.m.sin(conto.xz) + af2[3] - i * conto.keyz[3] * this.m.cos(conto.xz) - conto.keyx[3] * this.m.sin(conto.xz)) / 4.0f + i2 * this.m.sin(this.pxy) * this.m.sin(conto.xz) - i2 * this.m.sin(this.pzy) * this.m.cos(conto.xz) + f2);
        if (!this.mtouch) {
            if (this.trcnt != 1) {
                this.trcnt = 1;
                this.lxz = this.xz;
            }
            if (this.loop == 2 || this.loop == -1) {
                for (int a4 = 0; a4 < 2; ++a4) {
                    if (a4 == 0 || !this.launched) {
                        final int[] travxy = this.travxy;
                        final int n28 = a4;
                        travxy[n28] += (this.rcomp - this.lcomp);  // cast: bytecode-verified
                        final int[] travzy = this.travzy;
                        final int n29 = a4;
                        travzy[n29] += (this.ucomp - this.dcomp);  // cast: bytecode-verified
                    }
                    if (Math.abs(this.travxy[a4]) > 135) {
                        this.rtab[a4] = true;
                    }
                    if (this.travzy[a4] > 135) {
                        this.ftab[a4] = true;
                    }
                    if (this.travzy[a4] < -135) {
                        this.btab[a4] = true;
                    }
                }
            }
            if (this.lxz != this.xz) {
                for (int a4 = 0; a4 < 2; ++a4) {
                    if (a4 == 0 || !this.launched) {
                        final int[] travxz = this.travxz;
                        final int n30 = a4;
                        travxz[n30] += this.lxz - this.xz;
                    }
                }
                this.lxz = this.xz;
            }
            for (int a4 = 0; a4 < 2; ++a4) {
                if ((!this.launched || a4 == 0 || this.im == 0) && this.srfcnt[a4] < 10) {
                    if (control.wall != -1) {
                        this.surfer[a4] = true;
                    }
                    final int[] srfcnt = this.srfcnt;
                    final int n31 = a4;
                    ++srfcnt[n31];
                }
            }
        }
        else if (!this.dest) {
            if (!this.capsized) {
                if (this.capcnt != 0) {
                    this.capcnt = 0;
                }
                if (this.gtouch && this.trcnt != 0) {
                    if (this.trcnt == 9) {
                        for (int a4 = 0; a4 < 2; ++a4) {
                            this.powerup[a4] = 0.0f;
                            if (Math.abs(this.travxy[a4]) > 90) {
                                final float[] powerup = this.powerup;
                                final int n32 = a4;
                                powerup[n32] += Math.abs(this.travxy[a4]) / 24.0f;
                            }
                            else if (this.rtab[a4]) {
                                final float[] powerup2 = this.powerup;
                                final int n33 = a4;
                                powerup2[n33] += 30.0f;
                            }
                            if (Math.abs(this.travzy[a4]) > 90) {
                                final float[] powerup3 = this.powerup;
                                final int n34 = a4;
                                powerup3[n34] += Math.abs(this.travzy[a4]) / 18.0f;
                            }
                            else {
                                if (this.ftab[a4]) {
                                    final float[] powerup4 = this.powerup;
                                    final int n35 = a4;
                                    powerup4[n35] += 40.0f;
                                }
                                if (this.btab[a4]) {
                                    final float[] powerup5 = this.powerup;
                                    final int n36 = a4;
                                    powerup5[n36] += 40.0f;
                                }
                            }
                            if (Math.abs(this.travxz[a4]) > 90) {
                                final float[] powerup6 = this.powerup;
                                final int n37 = a4;
                                powerup6[n37] += Math.abs(this.travxz[a4]) / 18.0f;
                            }
                            if (this.surfer[a4] && !this.xt.entered[this.im]) {
                                final float[] powerup7 = this.powerup;
                                final int n38 = a4;
                                powerup7[n38] += 15.0f;
                            }
                        }
                        this.power += this.powerup[0];
                        if (this.xt.careermode) {
                            if (checkpoints.stage == 21 && !this.xt.entered[this.im]) {
                                ++this.nostunts;
                            }
                            if (this.im == 0) {
                                double extramod = 1.0;
                                for (int a5 = 0; a5 < 6; ++a5) {
                                    if (this.xt.specialstats[this.cn][2][a5] > 0) {
                                        extramod = 1.0 + this.xt.specialstats[this.cn][2][a5] / 100.0;
                                    }
                                }
                                this.stumultiplier = this.xt.stat[3] / 50.0;
                                if (!this.xt.noexp) {
                                    final int[] exp = this.exp;
                                    final int cn = this.cn;
                                    exp[cn] += (int)(this.powerup[0] * this.stumultiplier * extramod * this.xt.expmult);
                                }
                            }
                            if (checkpoints.stage == 23 && this.xt.bossbattle && this.xt.cstimer >= 4 && this.im == 0) {
                                final xtGraphics xt = this.xt;
                                xt.stunthealth += (int)this.powerup[0];
                            }
                        }
                        boolean exception = false;
                        if (this.xt.careermode && checkpoints.stage == 21 && !this.xt.entered[this.im] && this.nostunts <= 1) {
                            exception = true;
                        }
                        if (!control.spatk && !exception) {
                            float splimit = 100.0f;
                            if (this.xt.careermode && checkpoints.stage >= 22) {
                                splimit = 125.0f;
                            }
                            boolean gaincep = false;
                            if (this.isabot) {
                                gaincep = true;
                            }
                            if (!this.launched) {
                                if ((this.im > 0 || this.xt.justcs == 6) && this.powerup[1] <= splimit && !gaincep) {
                                    this.spatk += this.powerup[1] / 3.0f;
                                }
                                else {
                                    this.spatk += this.powerup[1] / 5.0f;
                                }
                            }
                        }
                        if (this.im == 0 && (int)this.powerup[0] > this.rpd.powered && this.rpd.wasted == 0 && (this.powerup[0] > 60.0f || checkpoints.stage <= 2)) {
                            this.rpdcatch = 30;
                            if (this.rpd.hcaught) {
                                this.rpd.powered = (int)this.powerup[0];
                            }
                        }
                        if (this.power > 98.0f) {
                            this.power = 98.0f;
                            int accelboost3 = 0;
                            if (this.xt.careermode) {
                                int shadnegate3 = 0;
                                if (this.shadowcar) {
                                    shadnegate3 = 50;
                                }
                                int cheatboost4 = 0;
                                if (this.im == 0) {
                                    if (this.cn == 32) {
                                        cheatboost4 = 15;
                                    }
                                    if (this.cn == 33) {
                                        cheatboost4 = 12;
                                    }
                                    if (this.cn == 31 || this.cn == 36) {
                                        cheatboost4 = 10;
                                    }
                                    if (this.cn == 34 || this.cn == 35 || this.cn == 37 || this.cn == 38) {
                                        cheatboost4 = 17;
                                    }
                                }
                                accelboost3 = this.aiaccsp[this.cn] - (this.level[this.cn] - 1) - shadnegate3 - cheatboost4;
                            }
                            int powtime = 100 + accelboost3;
                            if (this.powerup[0] > 150.0f) {
                                powtime = 200 + accelboost3;
                            }
                            int actualpowtime = (int)(powtime * (1.0f / this.powermulti));
                            if (actualpowtime < 40) {
                                actualpowtime = 40;
                            }
                            this.xtpower = actualpowtime;
                        }
                    }
                    if (this.trcnt == 10) {
                        for (int a4 = 0; a4 < 2; ++a4) {
                            this.travxy[a4] = 0;
                            this.travzy[a4] = 0;
                            this.travxz[a4] = 0;
                            this.ftab[a4] = false;
                            this.rtab[a4] = false;
                            this.btab[a4] = false;
                            this.surfer[a4] = false;
                            this.srfcnt[a4] = 0;
                        }
                        this.trcnt = 0;
                        if (this.xt.careermode && checkpoints.stage == 21 && !this.xt.entered[this.im] && this.nostunts <= 1) {
                            this.nostunts = 2;
                        }
                        this.launched = false;
                    }
                    else {
                        this.cntdale = false;
                        ++this.trcnt;
                    }
                }
            }
            else {
                if (this.trcnt != 0) {
                    for (int a4 = 0; a4 < 2; ++a4) {
                        this.travxy[a4] = 0;
                        this.travzy[a4] = 0;
                        this.travxz[a4] = 0;
                        this.ftab[a4] = false;
                        this.rtab[a4] = false;
                        this.btab[a4] = false;
                        this.surfer[a4] = false;
                        this.srfcnt[a4] = 0;
                    }
                    this.trcnt = 0;
                }
                if (this.capcnt == 0) {
                    int l9 = 0;
                    int i10 = 0;
                    do {
                        if (Math.abs(this.scz[i10]) < 70.0f && Math.abs(this.scx[i10]) < 70.0f) {
                            ++l9;
                        }
                    } while (++i10 < 4);
                    if (l9 == 4) {
                        this.capcnt = 1;
                    }
                }
                else {
                    ++this.capcnt;
                    int time = 30;
                    if (this.im == 0 && this.xt.careermode) {
                        for (int a6 = 0; a6 < 6; ++a6) {
                            if (this.xt.specialstats[this.cn][5][a6] > 0) {
                                time = 30 - (int)(this.xt.specialstats[this.cn][5][a6] * 1.2);
                            }
                        }
                    }
                    if (this.capcnt == time) {
                        this.speed = 0.0f;
                        conto.y += this.flipy[this.cn];
                        this.pxy += 180;
                        conto.xy += 180;
                        this.capcnt = 0;
                    }
                }
            }
            if (this.trcnt == 0 && this.speed != 0.0f) {
                if (this.xtpower <= 0) {
                    this.xtpower = 0;
                    if (this.power > 0.0f) {
                        this.power -= this.power * this.power * this.power * this.powermulti / this.powerloss[this.cn];
                    }
                    else {
                        this.power = 0.0f;
                    }
                }
                else {
                    --this.xtpower;
                }
            }
        }
        float splimit2 = 100.0f;
        if (this.xt.careermode) {
            if (checkpoints.stage >= 22) {
                splimit2 = 125.0f;
            }
            if (this.xt.careermode && this.im == 0) {
                for (int a6 = 0; a6 < 6; ++a6) {
                    if (this.xt.specialstats[this.cn][0][a6] > 0 && this.xt.specialstats[this.cn][0][a6] <= 10) {
                        this.powerloss[this.cn] = this.powerloss2[this.cn] + (this.powerloss2[15] - this.powerloss2[this.cn]) / 10 * this.xt.specialstats[this.cn][0][a6];
                    }
                    if (this.xt.specialstats[this.cn][0][a6] > 10 && this.xt.specialstats[this.cn][0][a6] <= 15) {
                        this.powerloss[this.cn] = this.powerloss2[15] + (this.powerloss2[17] - this.powerloss2[15]) / 5 * (this.xt.specialstats[this.cn][0][a6] - 10);
                    }
                    if (this.xt.specialstats[this.cn][0][a6] > 15 && this.xt.specialstats[this.cn][0][a6] <= 20) {
                        this.powerloss[this.cn] = this.powerloss2[17] + (this.powerloss2[13] - this.powerloss2[17]) / 5 * (this.xt.specialstats[this.cn][0][a6] - 15);
                    }
                    if (this.xt.specialstats[this.cn][7][a6] > 0) {
                        final float increment2 = (this.push2[20] - this.push2[this.cn]) / 20.0f;
                        this.push[this.cn] = this.push2[this.cn] + increment2 * this.xt.specialstats[this.cn][7][a6];
                    }
                    if (this.xt.specialstats[this.cn][11][a6] > 0) {
                        final float increment2 = this.revpush2[this.cn] / 20.0f;
                        this.revpush[this.cn] = this.revpush2[this.cn] - increment2 * this.xt.specialstats[this.cn][11][a6];
                    }
                    if (this.xt.specialstats[this.cn][17][a6] > 0) {
                        final double increment3 = (this.lift2[7] - this.lift2[this.cn]) / 20.0;
                        this.lift[this.cn] = this.lift2[this.cn] + (int)(increment3 * this.xt.specialstats[this.cn][17][a6]);
                    }
                }
            }
        }
        boolean gaincep2 = false;
        if (this.isabot) {
            gaincep2 = true;
        }
        final float modifier = 1.0f - this.nuclearmod * this.nuclearmod;
        if (!this.xt.careermode || checkpoints.stage != 11 || !this.xt.bonusstage[1]) {}
        if ((this.im > 0 || this.xt.justcs == 6) && this.powerup[1] <= splimit2 && !gaincep2) {
            this.spatk += this.powerup[1] / 500.0f;
        }
        else {
            this.spatk += this.powerup[1] / 3500.0f;
        }
        if (control.spatk && this.spatk < 120.0f) {
            control.spatk = false;
        }
        if (this.spatk > 120.0f) {
            this.spatk = 120.0f;
        }
        if (this.spatk < 0.0f) {
            this.spatk = 0.0f;
        }
        int extrastunt = 0;
        if (this.xt.careermode) {
            int cheatboost5 = 0;
            if (this.im == 0) {
                if (this.cn == 31) {
                    cheatboost5 = 25;
                }
                if (this.cn == 32) {
                    cheatboost5 = 10;
                }
                if (this.cn == 33) {
                    cheatboost5 = 12;
                }
                if (this.cn == 36) {
                    cheatboost5 = 20;
                }
                if (this.cn == 34 || this.cn == 35 || this.cn == 37 || this.cn == 38) {
                    cheatboost5 = 17;
                }
            }
            extrastunt = this.aistusp[this.cn] - (this.level[this.cn] - 1) - cheatboost5;
        }
        if (control.spatk && this.speclast <= 120.0f && this.spatk == 120.0f && !this.xt.ghosttele) {
            this.speclast -= 343000.0f / (2500000 + extrastunt * 25000);
            this.speclast2 -= 343000.0f / (2500000 + extrastunt * 25000);
            if (checkpoints.stage == 11) {
                final boolean b = this.xt.bonusstage[1];
            }
        }
        if (!control.spatk && this.speclast != 120.0f && this.spatk == 120.0f && !this.xt.ghosttele) {
            this.speclast -= 343000.0f / (2500000 + extrastunt * 25000);
            this.speclast2 -= 343000.0f / (2500000 + extrastunt * 25000);
            if (checkpoints.stage == 11) {
                final boolean b2 = this.xt.bonusstage[1];
            }
        }
        if (this.speclast2 < 0.0f) {
            this.speclast2 = 0.0f;
        }
        if (this.speclast > 120.0f) {
            this.speclast = 120.0f;
        }
        if (this.speclast < 0.0f) {
            this.speclast = 0.0f;
        }
        if (this.speclast == 0.0f && this.spatk == 120.0f) {
            this.spatk = 0.0f;
            this.specialact = false;
            this.xt.fixspecials[this.im] = false;
        }
        if (this.speclast == 0.0f && this.spatk == 0.0f) {
            this.speclast = 120.0f;
            this.speclast2 = 120.0f;
        }
        if (this.xt.careermode && this.xt.bonusstage[3]) {
            this.spatk = 0.0f;
        }
        boolean whichdest = this.dest;
        boolean uniquecase = false;
        if (this.xt.careermode && checkpoints.stage == 11 && !this.xt.bonusstage[1] && this.im != 0) {
            uniquecase = true;
            whichdest = this.fakedest;
        }
        if (this.hitmag > this.maxmag[this.cn] && !this.xt.undead[this.im]) {
            if (!this.doonce[this.im]) {
                this.realkiller[this.im] = this.collided[this.im];
                this.doonce[this.im] = true;
            }
            if (!whichdest) {
                this.distruct(conto);
                if (this.cntdest == 7) {
                    if (uniquecase) {
                        this.xt.undead[this.im] = true;
                        this.fakedest = true;
                    }
                    else {
                        this.dest = true;
                    }
                }
                else {
                    ++this.cntdest;
                }
                if (this.cntdest == 1) {
                    this.rpd.dest[this.im] = 300;
                }
            }
        }
        else {
            this.doonce[this.im] = false;
        }
        if (conto.dist == 0) {
            for (int i11 = 0; i11 < conto.npl; ++i11) {
                if (conto.p[i11].chip != 0) {
                    conto.p[i11].chip = 0;
                }
                if (conto.p[i11].embos != 0) {
                    conto.p[i11].embos = 13;
                }
            }
        }
        if (this.lastcolido[this.im] > 0 && !whichdest) {
            final int[] lastcolido = this.lastcolido;
            final int im = this.im;
            --lastcolido[im];
        }
        if (whichdest) {
            if (checkpoints.dested[this.im] == 0) {
                if (this.lastcolido[this.im] == 0) {
                    checkpoints.dested[this.im] = 1;
                }
                else {
                    checkpoints.dested[this.im] = this.realkiller[this.im] + 2;
                }
            }
        }
        else if (checkpoints.dested[this.im] != 0) {
            checkpoints.dested[this.im] = 0;
        }
        if ((this.im == 0 || this.isabot) && this.xt.justcs == -1 && control.wall != -1) {
            control.wall = -1;
        }
        if (this.im == 0 && this.rpd.wasted == 0 && this.rpdcatch != 0) {
            --this.rpdcatch;
            if (this.rpdcatch == 0) {
                this.rpd.cotchinow(0, this.xt.nplayers);
                if (this.rpd.hcaught) {
                    this.rpd.whenwasted = (int)(185.0f + this.m.random() * 20.0f);
                }
            }
        }
        if (Math.abs(this.speed) > 10.0f || !this.mtouch) {
            if (Math.abs(this.pxy - conto.xy) >= 4) {
                if (this.pxy > conto.xy) {
                    conto.xy += 2 + (this.pxy - conto.xy) / 2;
                }
                else {
                    conto.xy -= 2 + (conto.xy - this.pxy) / 2;
                }
            }
            else {
                conto.xy = this.pxy;
            }
            if (Math.abs(this.pzy - conto.zy) >= 4) {
                if (this.pzy > conto.zy) {
                    conto.zy += 2 + (this.pzy - conto.zy) / 2;
                }
                else {
                    conto.zy -= 2 + (conto.zy - this.pzy) / 2;
                }
            }
            else {
                conto.zy = this.pzy;
            }
        }
        if (this.wtouch && !this.capsized) {
            final float f27 = (float)(this.speed / this.swits[this.cn][2] * 14.0f * (bounciness - 0.4));
            if (control.left && this.tilt < f27 && this.tilt >= 0.0f) {
                this.tilt += 0.4;  // cast: bytecode-verified
            }
            if (control.right && this.tilt > -f27 && this.tilt <= 0.0f) {
                this.tilt -= 0.4;  // cast: bytecode-verified
            }
            if (Math.abs(this.tilt) > 3.0 * (bounciness - 0.4)) {
                if (this.tilt > 0.0f) {
                    this.tilt -= (3.0 * (bounciness - 0.3));  // cast: bytecode-verified
                }
                else {
                    this.tilt += (3.0 * (bounciness - 0.3));  // cast: bytecode-verified
                }
            }
            else {
                this.tilt = 0.0f;
            }
            conto.xy += (int)this.tilt;
            if (this.gtouch) {
                conto.y -= (this.tilt / 1.5);  // cast: bytecode-verified
            }
        }
        else if (this.tilt != 0.0f) {
            this.tilt = 0.0f;
        }
        float norandom2 = this.m.random();
        if (this.isabot || this.xt.makebot) {
            norandom2 = 0.5f;
        }
        if (this.wtouch && this.roadtyp == 2) {
            conto.zy += (int)((norandom2 * 25.0f * this.speed / this.swits[this.cn][2] - 15.0f * this.speed / this.swits[this.cn][2]) * (bounciness - 0.9999999999999999));
            conto.xy += (int)((norandom2 * 25.0f * this.speed / this.swits[this.cn][2] - 15.0f * this.speed / this.swits[this.cn][2]) * (bounciness - 0.9999999999999999));
        }
        if (this.wtouch && this.roadtyp == 1) {
            conto.zy += (int)((norandom2 * 20.0f * this.speed / this.swits[this.cn][2] - 10.0f * this.speed / this.swits[this.cn][2]) * (bounciness - 0.9999999999999999));
            conto.xy += (int)((norandom2 * 20.0f * this.speed / this.swits[this.cn][2] - 10.0f * this.speed / this.swits[this.cn][2]) * (bounciness - 0.9999999999999999));
        }
        if (this.dest && this.hitmag < this.maxmag[this.cn]) {
            this.hitmag = this.maxmag[this.cn] + 1;
        }
        int j10 = 0;
        int k10 = 0;
        int l10 = 0;
        int aj = 7;
        if (this.nofocus) {
            aj = 1;
        }
        this.teleported = false;
        if (this.forcehandb) {
            conto.teleported = true;
            if (conto.telefade >= 15) {
                conto.telefade -= 15;
            }
            if (this.teletimer >= 20) {
                if (!this.capsized && this.mtouch) {
                    this.speed = 0.0f;
                    this.pxy = 0;
                    this.pzy = 0;
                    this.teleport(conto, this.im);
                    if (this.slowstable >= 4 && conto.telefade < 15) {
                        if ((!this.specialact || this.cn == 13 || this.cn == 36) && this.telechk) {
                            bots.botbreak[this.im] = false;
                            this.telechk = false;
                        }
                        this.xt.floor[this.im] = this.sendtofloor;
                        this.xt.speedhack[this.im] = 10;
                        this.teleinvul = 10;
                        if (this.im == 0) {
                            this.teleinvul = 30;
                        }
                        if (this.sendtofloor > 0) {
                            control.setfixfloor = true;
                        }
                        this.xtpower = 100;
                        conto.x = 0;
                        if (this.sendtofloor == 1) {
                            conto.x = -5000;
                        }
                        conto.z = 0;
                        conto.y = -(this.sendtofloor * 10000) - conto.grat;
                        if (this.sendtofloor == 0) {
                            conto.y = 250 - conto.grat;
                        }
                        int whichset = (3 - this.sendtofloor) * 2 + 1;
                        if (this.spatk == 120.0f && !this.specialact) {
                            whichset = (3 - this.sendtofloor) * 2 + 2;
                        }
                        if (this.cn == 13 || this.cn == 36) {
                            whichset = 3 - this.sendtofloor + 1;
                        }
                        bots.specialtimer[this.im] = bots.botoffset[whichset][this.im];
                        this.teleported = true;
                        conto.telefade = 255;
                        conto.teleported = false;
                        if (this.xt.makebot) {
                            bots.timer = 0;
                            this.xt.actions[0] = 0;
                        }
                        control.down = false;
                        control.left = false;
                        control.right = false;
                        control.handb = false;
                        this.forcehandb = false;
                    }
                }
                if (this.speed == 0.0f && this.pxy == 0 && this.pzy == 0) {
                    ++this.slowstable;
                }
            }
        }
        else {
            --this.teleinvul;
            this.slowstable = 0;
        }
        if (control.needtofix) {
            bots.botbreak[this.im] = true;
        }
        if (this.m.effect[9]) {
            if (this.im % 3 == 1 || this.im == 0 || this.im > 9) {
                conto.floorguardian = false;
            }
            else {
                conto.floorguardian = true;
            }
            if (this.xt.speedhack[this.im] > 0) {
                conto.x = 0;
                if (this.sendtofloor == 1) {
                    conto.x = -5000;
                }
                this.teleport(conto, this.im);
            }
        }
        if (conto.floorguardian) {
            boolean nomercy = true;
            for (int a7 = 1; a7 < this.xt.nplayers; ++a7) {
                if ((a7 == 1 || a7 == 4 || a7 == 7 || a7 >= 10) && checkpoints.dested[a7] == 0) {
                    nomercy = false;
                    break;
                }
            }
            boolean hardstage = false;
            if (checkpoints.stage == this.xt.unlocked[1] || this.xt.hardstage) {
                hardstage = true;
            }
            conto.guardswitch = true;
            if ((checkpoints.clear[0] >= 13 && hardstage) || nomercy) {
                conto.guardswitch = false;
            }
        }
        boolean fixcar = false;
        for (int j11 = 0; j11 < checkpoints.n; ++j11) {
            if (checkpoints.telefloor[j11] > -1 && !conto.floorguardian && !this.forcehandb) {
                if (checkpoints.rotation[j11] % 180 == 0 && Math.abs(conto.z - checkpoints.z[j11]) < 60.0f + Math.abs(this.scz[0] + this.scz[1] + this.scz[2] + this.scz[3]) / 4.0f && Math.abs(conto.x - checkpoints.x[j11]) < 700 && Math.abs(conto.y - checkpoints.y[j11]) < 800) {
                    this.sendtofloor = checkpoints.telefloor[j11];
                    this.forcehandb = true;
                }
                if (checkpoints.rotation[j11] % 90 == 0 && Math.abs(conto.x - checkpoints.x[j11]) < 60.0f + Math.abs(this.scx[0] + this.scx[1] + this.scx[2] + this.scx[3]) / 4.0f && Math.abs(conto.z - checkpoints.z[j11]) < 700 && Math.abs(conto.y - checkpoints.y[j11]) < 800) {
                    this.sendtofloor = checkpoints.telefloor[j11];
                    this.forcehandb = true;
                }
            }
            if (checkpoints.typ[j11] > 0) {
                ++l10;
                if (checkpoints.typ[j11] == 1 || checkpoints.typ[j11] == 3) {
                    if (this.clear == l10 + this.nlaps * checkpoints.nsp) {
                        aj = 1;
                    }
                    if (Math.abs(conto.z - checkpoints.z[j11]) < 60.0f + Math.abs(this.scz[0] + this.scz[1] + this.scz[2] + this.scz[3]) / 4.0f && Math.abs(conto.x - checkpoints.x[j11]) < 700 && Math.abs(conto.y - checkpoints.y[j11]) < 800 && this.clear == l10 + this.nlaps * checkpoints.nsp - 1) {
                        if (this.xt.careermode && this.xt.bonusstage[3]) {
                            this.squash = 0;
                            this.nbsq = 0;
                            this.hitmag = 0;
                            this.dmgmag = 0.0f;
                            this.cntdest = 0;
                            this.dest = false;
                            this.newcar = true;
                        }
                        if (this.forcehandb) {
                            this.telechk = true;
                        }
                        this.clear = l10 + this.nlaps * checkpoints.nsp;
                        this.pcleared = j11;
                        this.focus = -1;
                    }
                }
                if (checkpoints.typ[j11] == 2 || checkpoints.typ[j11] == 4) {
                    if (this.clear == l10 + this.nlaps * checkpoints.nsp) {
                        aj = 1;
                    }
                    if (Math.abs(conto.x - checkpoints.x[j11]) < 60.0f + Math.abs(this.scx[0] + this.scx[1] + this.scx[2] + this.scx[3]) / 4.0f && Math.abs(conto.z - checkpoints.z[j11]) < 700 && Math.abs(conto.y - checkpoints.y[j11]) < 800 && this.clear == l10 + this.nlaps * checkpoints.nsp - 1) {
                        if (this.xt.careermode && this.xt.bonusstage[3]) {
                            this.squash = 0;
                            this.nbsq = 0;
                            this.hitmag = 0;
                            this.dmgmag = 0.0f;
                            this.cntdest = 0;
                            this.dest = false;
                            this.newcar = true;
                        }
                        if (this.forcehandb) {
                            this.telechk = true;
                        }
                        this.clear = l10 + this.nlaps * checkpoints.nsp;
                        this.pcleared = j11;
                        this.focus = -1;
                    }
                }
                if (checkpoints.typ[j11] == 3 && this.hitmag > 0 && Math.abs(conto.z - checkpoints.z[j11]) < 60.0f + Math.abs(this.scz[0] + this.scz[1] + this.scz[2] + this.scz[3]) / 4.0f && Math.abs(conto.x - checkpoints.x[j11]) < 700 && Math.abs(conto.y - checkpoints.y[j11]) < 800 && !this.nofix) {
                    if (!this.isabot && !this.xt.makebot) {
                        if (conto.dist == 0) {
                            conto.fcnt = 8;
                        }
                        else {
                            if (this.im == 0 && !conto.fix && !this.xt.mutes) {
                                this.xt.carfixed.play();
                            }
                            conto.fix = true;
                        }
                    }
                    this.rpd.fix[this.im] = 300;
                }
                if (checkpoints.typ[j11] == 4 && this.hitmag > 0 && Math.abs(conto.x - checkpoints.x[j11]) < 60.0f + Math.abs(this.scx[0] + this.scx[1] + this.scx[2] + this.scx[3]) / 4.0f && Math.abs(conto.z - checkpoints.z[j11]) < 700 && Math.abs(conto.y - checkpoints.y[j11]) < 800 && !this.nofix) {
                    if (!this.isabot && !this.xt.makebot) {
                        if (conto.dist == 0) {
                            conto.fcnt = 8;
                        }
                        else {
                            if (this.im == 0 && !conto.fix && !this.xt.mutes) {
                                this.xt.carfixed.play();
                            }
                            conto.fix = true;
                        }
                    }
                    this.rpd.fix[this.im] = 300;
                }
            }
            boolean rightfloor = true;
            if (this.xt.careermode && checkpoints.stage == 13 && checkpoints.floor[j11] != this.xt.floor[this.im]) {
                rightfloor = false;
            }
            if ((this.py(conto.x / 100, checkpoints.x[j11] / 100, conto.z / 100, checkpoints.z[j11] / 100) * aj < k10 || k10 == 0) && rightfloor) {
                j10 = j11;
                k10 = this.py(conto.x / 100, checkpoints.x[j11] / 100, conto.z / 100, checkpoints.z[j11] / 100) * aj;
            }
        }
        if (this.clear == l10 + this.nlaps * checkpoints.nsp) {
            ++this.nlaps;
        }
        if (this.im == 0) {
            this.m.checkpoint = this.clear;
            while (this.m.checkpoint >= checkpoints.nsp) {
                final Medium m = this.m;
                m.checkpoint -= checkpoints.nsp;
            }
            if (this.clear == checkpoints.nlaps * checkpoints.nsp - 1) {
                this.m.lastcheck = true;
            }
            if (checkpoints.haltall) {
                this.m.lastcheck = false;
            }
        }
        if (this.focus == -1) {
            if (this.im == 0) {
                j10 += 2;
            }
            else {
                ++j10;
            }
            if (!this.nofocus) {
                int i12;
                i12 = this.pcleared + 1;
                while (checkpoints.typ[i12] <= 0) {  // procyon moved `i12 = 0` out of this if into the for-update, an infinite loop; control flow checked against javap
                    if (++i12 == checkpoints.n) {
                        i12 = 0;
                    }
                }
                boolean rightfloor = true;
                if (this.xt.careermode && checkpoints.stage == 13 && checkpoints.floor[i12] != this.xt.floor[this.im]) {
                    rightfloor = false;
                }
                if (((j10 > i12 && (this.clear != this.nlaps * checkpoints.nsp || j10 < this.pcleared)) || variable.dontdistract[this.im]) && rightfloor) {
                    j10 = i12;
                    this.focus = j10;
                }
            }
            if (j10 >= checkpoints.n) {
                j10 -= checkpoints.n;
            }
            if (checkpoints.typ[j10] == -3) {
                j10 = 0;
            }
            if (this.im == 0) {
                if (this.missedcp != -1) {
                    this.missedcp = -1;
                }
            }
            else if (this.missedcp != 0) {
                this.missedcp = 0;
            }
        }
        else {
            boolean rightfloor2 = true;
            if (this.xt.careermode && checkpoints.stage == 13 && checkpoints.floor[this.focus] != this.xt.floor[this.im]) {
                rightfloor2 = false;
            }
            if (rightfloor2) {
                j10 = this.focus;
            }
            if (this.im == 0) {
                if (this.missedcp == 0 && this.mtouch && Math.sqrt(this.py(conto.x / 10, checkpoints.x[this.focus] / 10, conto.z / 10, checkpoints.z[this.focus] / 10)) > 800.0) {
                    this.missedcp = 1;
                }
                if (this.missedcp == -2 && Math.sqrt(this.py(conto.x / 10, checkpoints.x[this.focus] / 10, conto.z / 10, checkpoints.z[this.focus] / 10)) < 400.0) {
                    this.missedcp = 0;
                }
                if (this.missedcp != 0 && this.mtouch && Math.sqrt(this.py(conto.x / 10, checkpoints.x[this.focus] / 10, conto.z / 10, checkpoints.z[this.focus] / 10)) < 250.0) {
                    this.missedcp = 68;
                }
            }
            else {
                this.missedcp = 1;
            }
            if (this.nofocus) {
                this.focus = -1;
                this.missedcp = 0;
            }
        }
        if (this.nofocus) {
            this.nofocus = false;
        }
        this.point = j10;
        this.nofix = false;
        if (this.xt.careermode) {
            if (this.xt.bonusstage[1]) {
                final float health = 100.0f * this.hitmag / this.maxmag[this.cn];
                if (this.beast[this.im] && health < 85.0f && this.cn == 36) {
                    this.nofix = true;
                }
            }
            if (this.xt.bonusstage[2] && this.im > 0) {
                this.nofix = true;
            }
            if (this.xt.bonusstage[3] && (this.im > 0 || this.clear < 26)) {
                this.nofix = true;
            }
            if (this.xt.undead[this.im]) {
                this.nofix = true;
            }
            if (checkpoints.stage == 13) {
                final float health = 100.0f * this.hitmag / this.maxmag[this.cn];
                if (this.beast[this.im] && health <= 70.0f) {
                    this.nofix = true;
                }
            }
            if (checkpoints.stage == 19 || checkpoints.stage == 6) {
                final float health = 100.0f * this.hitmag / this.maxmag[this.cn];
                float benchmark = 75.0f;
                if (checkpoints.stage == 19) {
                    benchmark = 50.0f;
                    if (this.shadowcar) {
                        benchmark = 70.0f;
                    }
                }
                if ((this.beast[this.im] || this.shadowcar) && health <= benchmark) {
                    this.nofix = true;
                }
                else {
                    this.nofix = false;
                }
            }
            if (checkpoints.stage == 23 && this.xt.bossbattle && this.xt.cstimer >= 4 && this.im > 0) {
                this.nofix = true;
            }
        }
        for (int k11 = 0; k11 < checkpoints.fn; ++k11) {
            if (!this.nofix) {
                if (!checkpoints.roted[k11]) {
                    if (Math.abs(conto.z - checkpoints.fz[k11]) < 200 && this.py(conto.x / 100, checkpoints.fx[k11] / 100, conto.y / 100, checkpoints.fy[k11] / 100) < 30) {
                        if (!this.isabot) {
                            if (conto.dist == 0) {
                                conto.fcnt = 8;
                            }
                            else {
                                if (this.im == 0 && !conto.fix && !this.xt.mutes) {
                                    this.xt.carfixed.play();
                                }
                                conto.fix = true;
                            }
                        }
                        else {
                            fixcar = true;
                        }
                        this.rpd.fix[this.im] = 300;
                    }
                }
                else if (Math.abs(conto.x - checkpoints.fx[k11]) < 200 && this.py(conto.z / 100, checkpoints.fz[k11] / 100, conto.y / 100, checkpoints.fy[k11] / 100) < 30) {
                    if (!this.isabot) {
                        if (conto.dist == 0) {
                            conto.fcnt = 8;
                        }
                        else {
                            if (this.im == 0 && !conto.fix && !this.xt.mutes) {
                                this.xt.carfixed.play();
                            }
                            conto.fix = true;
                        }
                    }
                    else {
                        fixcar = true;
                    }
                    this.rpd.fix[this.im] = 300;
                }
            }
        }
        if (this.xt.dontdisplay && (this.xt.ptmatch == 1 || this.xt.ptmatch == 4) && this.revive > 40) {
            fixcar = true;
        }
        if (this.xt.norender[this.im] && conto.fix) {
            fixcar = true;
            conto.fix = false;
        }
        if (conto.fcnt == 7 || conto.fcnt == 8 || fixcar) {
            if (this.xt.careermode && this.im == 0) {
                for (int a7 = 0; a7 < 6; ++a7) {
                    if (this.xt.specialstats[this.cn][21][a7] > 0) {
                        this.fixtime = 60 + this.xt.specialstats[this.cn][21][a7] * 3;
                    }
                    if (this.xt.specialstats[this.cn][22][a7] > 0) {
                        this.fixtime = 60 + (int)(this.xt.specialstats[this.cn][22][a7] * 2.4);
                    }
                }
            }
            this.squash = 0;
            this.nbsq = 0;
            this.hitmag = 0;
            this.dmgmag = 0.0f;
            this.xt.statdrain[this.im] = 0;
            this.cntdest = 0;
            this.dest = false;
            this.newcar = true;
        }
        if (this.fixtime == 0) {
            this.startedgoing = false;
        }
        if (this.mtouch || this.startedgoing) {
            this.startedgoing = true;
            if (this.fixtime > 0) {
                if (!this.dest) {
                    --this.fixtime;
                }
                else {
                    this.fixtime = 0;
                }
            }
        }
        if (this.im == 0) {
            for (int a7 = 0; a7 < 2; ++a7) {
                if (this.xt.killtime[a7] > 0) {
                    if (!this.dest) {
                        final int[] killtime = this.xt.killtime;
                        final int n39 = a7;
                        --killtime[n39];
                    }
                    else {
                        this.xt.killtime[a7] = 0;
                    }
                }
            }
        }
    }
    
    public void respawn(final ContO conto, final CheckPoints checkpoints) {
        int j1;
        j1 = this.pcleared - 1;
        while (checkpoints.typ[j1] <= 0) {  // procyon moved `j1 = 0` out of this if into the for-update, an infinite loop; control flow checked against javap
            if (++j1 == checkpoints.n) {
                j1 = 0;
            }
        }
        if (checkpoints.clear[0] > 0) {
            conto.xz = checkpoints.rotation[j1];
        }
        else {
            conto.xz = 0;
        }
        this.squash = 0;
        this.nbsq = 0;
        this.hitmag = 0;
        this.dmgmag = 0.0f;
        this.cntdest = 0;
        this.dest = false;
        this.newcar = true;
        this.respawning = true;
        if (checkpoints.clear[0] > 0) {
            conto.x = checkpoints.x[j1];
            conto.z = checkpoints.z[j1];
            conto.y = checkpoints.y[j1] - 250;
        }
        else {
            conto.x = -380;
            conto.z = 380;
            conto.y = -20250;
        }
    }
    
    public void teleport(final ContO conto, final int user) {
        int inc = 0;
        if (this.xt.specialflag[user]) {
            inc = 1;
        }
        conto.xz = 0 + 180 * inc;
    }
}
