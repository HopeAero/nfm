import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.awt.RenderingHints;
import java.util.zip.ZipEntry;
import java.io.InputStream;
import java.util.zip.ZipInputStream;
import java.io.DataInputStream;
import java.net.URL;
import java.awt.Component;
import java.io.File;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.MediaTracker;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.Polygon;
import java.util.Arrays;
import java.awt.Color;
import java.applet.AudioClip;
import java.awt.Image;
import java.awt.Font;
import java.applet.Applet;
import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.6.0
// 

public class xtGraphics extends Panel implements Runnable
{
    Graphics2D rd;
    Graphics sg;
    Medium m;
    FontMetrics ftm;
    ImageObserver ob;
    Applet app;
    Thread runner;
    Font adventure;
    Font fifa;
    int nplayers;
    int[] xkcnt;
    boolean[] xmove;
    boolean[] xmoveback;
    Image rpgsc;
    int lines;
    boolean dontdisplay;
    boolean[] glowphase;
    boolean[] glow;
    boolean[] glowy;
    int[] glowg;
    int[] showfor;
    String[] ptplayers;
    String[] ctplayers;
    String[] ltplayers;
    boolean[] hovering;
    int[] seethru;
    boolean shexamptsp;
    int spkamount;
    int clearlimit;
    int fase;
    int oldfase;
    int[] killscn;
    int[] winscn;
    int[] opselect;
    int[] unlocked;
    double[] fullpownit;
    int starcnt;
    int kills;
    int rlosamount;
    int wlosamount;
    boolean[] fixspecials;
    boolean[] doitonce;
    double chkmultiplier;
    double wstmultiplier;
    boolean notunlocked;
    boolean[] wstm;
    boolean ktch;
    boolean shexamt;
    boolean classicmode;
    int strans;
    boolean stextphase;
    int xfade;
    boolean xfadephase;
    boolean careermode;
    int[] statpoints;
    int wins;
    boolean winfix;
    boolean killfix;
    Image nmsc;
    int expneeded;
    int expneededform;
    int lockcnt;
    boolean shaded;
    int flipo;
    boolean nextc;
    boolean textphase;
    int textrans;
    int gatey;
    int looped;
    int[] sc;
    float[] proba;
    float[] outdam;
    float[] powersave;
    boolean holdit;
    int holdcnt;
    boolean winner;
    int[] flexpix;
    int[] smokey;
    int flatrstart;
    int runtyp;
    boolean shkcnt;
    boolean shwcnt;
    boolean shkcncnt;
    boolean shwcncnt;
    boolean shexamtr;
    int ptmatch;
    int duds;
    int dudo;
    Image fleximg;
    Image cleardata;
    Image odmg;
    Image expbar;
    Image opwr;
    Image cmsc;
    Image ptbg;
    Image dascop;
    Image opos;
    Image ospecial;
    Image special;
    Image owas;
    Image rusure;
    Image donors;
    Image audir8lms;
    Image scoutint;
    Image olap;
    Image oyourwasted;
    Image oyoulost;
    Image gamelogo;
    Image oyouwon;
    Image radicalracer;
    Image oyouwastedem;
    Image ogameh;
    Image oloadingmusic;
    Image oflaot;
    Image savecar2;
    Image dmg;
    Image mezzelo;
    Image pwr;
    Image savedata;
    Image pos;
    Image pickup;
    Image was;
    Image lap;
    Image br;
    Image select;
    Image loadingmusic;
    Image yourwasted;
    Image scoutbut;
    Image youlost;
    Image youwon;
    Image youwastedem;
    Image elking;
    Image gameh;
    Image emptyslot;
    Image congrd;
    Image gameov;
    Image carsbg;
    Image rpgmode;
    Image normalmode;
    Image practice;
    Image mazdarx;
    Image fosterc;
    Image timetrial;
    Image pgate;
    Image selectcar;
    Image statb;
    Image statplus;
    Image statbo;
    Image mdness;
    Image overall;
    Image paused;
    Image radicalplay;
    Image credscen;
    Image logocars;
    Image byrd;
    Image opback;
    Image nfmcoms;
    Image cleared;
    Image opti;
    Image bgmain;
    Image warning;
    Image lado;
    Image rpro;
    Image nfmcom;
    Image flaot;
    Image fixhoop;
    Image sarrow;
    Image statb2;
    Image statbo2;
    Image stunts;
    Image racing;
    Image wasting;
    Image plus;
    Image space;
    Image arrows;
    Image chil;
    Image featrues;
    Image credscentwo;
    Image ory;
    Image kz;
    Image kx;
    Image vitalogy;
    Image kv;
    Image ultimato;
    Image kp;
    Image km;
    Image bob;
    Image kn;
    Image drdaler;
    Image kenter;
    Image rpgpic;
    Image excalibur;
    Image nfm;
    Image[][] trackbg;
    Image[] dude;
    Image[] dudeb;
    Image[] next;
    Image[] back;
    Image[] contin;
    Image[] ostar;
    Image billy;
    Image hyde;
    Image apexnova;
    Image[] star;
    int pcontin;
    boolean extraboo;
    int pnext;
    int pback;
    int[] totalsp;
    int[] bonuspoints;
    int[] okdale;
    int pstar;
    Image blizzardrush;
    Image[] ocntdn;
    Image[] cntdn;
    Image uvan;
    int gocnt;
    AudioClip[][] engs;
    boolean[] pengs;
    int[] enginsignature;
    AudioClip[] air;
    boolean aird;
    boolean grrd;
    AudioClip[] crash;
    AudioClip[] lowcrash;
    AudioClip tires;
    AudioClip checkpoint;
    AudioClip carfixed;
    AudioClip powerup;
    AudioClip three;
    AudioClip two;
    AudioClip one;
    AudioClip go;
    AudioClip fuucked;
    AudioClip redflash;
    AudioClip wastd;
    AudioClip firewasted;
    boolean pwastd;
    AudioClip[] skid;
    AudioClip[] dustskid;
    boolean mutes;
    RadicalMod stages;
    RadicalMod cars;
    RadicalMod menu;
    RadicalMod credits;
    RadicalMod[] stracks;
    RadicalMidi[] mtracks;
    boolean[] isMidi;
    boolean[] isOgg;
    boolean[] loadedt;
    int lastload;
    boolean mutem;
    boolean sunny;
    boolean macn;
    boolean arrace;
    int ana;
    int cntan;
    int[] chkamount;
    int winamount;
    int[] wstamount;
    int cntovn;
    boolean flk;
    int tcnt;
    boolean tflk;
    String say;
    boolean wasay;
    int clear;
    int posit;
    int wasted;
    int laps;
    int[] dested;
    String[] names;
    int dmcnt;
    boolean dmflk;
    int pwcnt;
    boolean pwflk;
    String[][] adj;
    String[] exlm;
    String loop;
    String spin;
    String asay;
    int auscnt;
    boolean aflk;
    boolean shexamtpn;
    Image instsc;
    Image csc;
    boolean btextphase;
    int btrans;
    int[] sndsize;
    Image hello;
    Image sign;
    Image loadbar;
    Image djmiker;
    Image trelivision;
    Image features;
    int kbload;
    int dnload;
    float shload;
    int radpx;
    int pin;
    int[] bgmy;
    int[] trkx;
    int trkl;
    int trklim;
    float[] hipno;
    int[] pgatx;
    int[] pgaty;
    int[] pgady;
    boolean[] pgas;
    int lxm;
    int lym;
    boolean shexamts;
    boolean shexamtw;
    int pwait;
    int stopcnt;
    int cntwis;
    int crshturn;
    int bfcrash;
    int bfskid;
    boolean crashup;
    boolean skidup;
    int skflg;
    int dskflg;
    int flatr;
    int flyr;
    int flyrdest;
    int cpn;
    int flang;
    int flangados;
    float blackn;
    float blacknados;
    int showopstage;
    int glowgtext;
    boolean glowgtextphase;
    boolean[] initiate;
    boolean[] namephase;
    int[] nametrans;
    int[] inform;
    int[] noform;
    boolean[] pinform;
    boolean[] pnoform;
    boolean norepeat;
    boolean[] namehover;
    int[] butrans;
    boolean aprogress;
    double[] stataffect;
    int[] ptscore1;
    int[] orderscore;
    boolean[] ptscore1fase;
    boolean[] ptscore1fase2;
    int firstkiller;
    int secondkiller;
    int thirdkiller;
    int fourthkiller;
    int fifthkiller;
    int sixthkiller;
    int seventhkiller;
    int eighthkiller;
    int ninthkiller;
    int tenthkiller;
    int eleventhkiller;
    int[] position;
    int[] points;
    int glowb;
    boolean glowbphase;
    boolean[] initialise;
    boolean[] initialise2;
    boolean[] initialise3;
    int[] sstrans;
    boolean[] sstransphase;
    int[] sstrans2;
    boolean[] sstransphase2;
    int[] sstrans3;
    boolean[] sstransphase3;
    boolean spgained;
    int[] pointsscore;
    int first;
    int second;
    int third;
    int fourth;
    int fifth;
    int sixth;
    int seventh;
    int eighth;
    int ninth;
    boolean[] ptmatchend;
    int tenth;
    int eleventh;
    boolean[] eliminated;
    int lewinner;
    boolean eliminate;
    boolean eliminateonce;
    int ptimer;
    int neliminated;
    boolean[] alhover;
    boolean[] arrowlock;
    int lockedon;
    boolean arrowlocked;
    boolean lockonce;
    boolean[] targetsq;
    int[] randomcar;
    boolean savedatah;
    int[] strswapee;
    boolean gohover;
    float pace;
    float str;
    float accel;
    boolean[] statcm;
    boolean[] slowonce;
    boolean[][] newtimer;
    int[][] timershown;
    int[][] q;
    boolean[][] over;
    int[] xm;
    double[] specpower;
    double[] drainrate;
    boolean[][] finalfix;
    boolean[] correct;
    int[] glowg2;
    boolean[] glowphase2;
    int[] sortstr;
    boolean[][] fixhealth;
    boolean[] above;
    boolean[] affected;
    boolean tomaini;
    boolean tocs;
    int fade;
    int scoutpage;
    int[] wallcode;
    boolean[] beastopponent;
    int[] beastcar;
    boolean bonstage;
    boolean[] bonusstage;
    boolean unlimitedlaps;
    boolean setlevels;
    boolean scalelevels;
    boolean nolevels;
    int[] flash;
    boolean[] beastflash;
    boolean clickable;
    boolean levelup;
    boolean levelfase;
    int leveltrans;
    int leveluptimer;
    int tempinv;
    boolean invulnerable;
    int savefase;
    String[] statstext;
    int loadcomplete;
    boolean playlevel;
    boolean[] wststatgain;
    boolean[] rcestatgain;
    boolean ncarset;
    boolean resetfase;
    boolean[] nohit;
    boolean[] resetoption;
    int[] boncomp;
    boolean bchover;
    boolean bclicked;
    boolean oclicked;
    boolean racingwin;
    boolean wastingwin;
    boolean loserace;
    int[] findi;
    int[] winchance;
    int[] killchance;
    int[] colorcode;
    int[] stat;
    int[] fbar;
    boolean[] stopflashing;
    private long now;
    private int framesCount;
    private int framesCountAvg;
    private long framesTimer;
    int[] extpoints;
    boolean[] hoverstat;
    boolean shexamthg;
    boolean atextphase;
    boolean sortedcars;
    int atrans;
    int hitgain;
    boolean ungain;
    boolean justonce;
    int fakehg;
    int combotime;
    int[] shadow;
    boolean ssdone;
    int[] shadowtrans;
    boolean alldone;
    int noshadows;
    int averagelevel;
    int totallevel;
    boolean nhover;
    boolean[] undead;
    boolean[] noarrow;
    int undeadtarget;
    int undeadswitch;
    boolean[] newflame;
    boolean newtarget;
    boolean generate;
    boolean sendwarning;
    boolean absolutefuckingbullshit;
    int[] positions;
    int[] sortpos;
    int[] glitchtimer;
    int pieceglitch;
    boolean[] piecespin;
    int[] spintime;
    int[] origposx;
    int[] origposz;
    int[] origposxz;
    boolean[] entered;
    boolean[] halfhealth;
    float[] healthmulti;
    int[] endsp;
    int startexp;
    int startsp;
    int minitimer;
    boolean crumble;
    boolean[] crumblefail;
    int startfalling;
    boolean[] countfall;
    boolean verydark;
    int targetcar;
    int[] speedhack;
    int[] lives;
    boolean[] specialflag;
    int[] destimer;
    boolean bossbattle;
    int cstimer;
    int stunthealth;
    int telecooldown;
    int telewait;
    boolean[] revive;
    long duration;
    long elapsed;
    long pausetime;
    long musicswitch;
    int[] xzrot;
    boolean hardstage;
    int[] timesfallen;
    boolean[] showboosts;
    int justcs;
    int lastcar;
    int laststage;
    int bgfade;
    boolean rerun;
    boolean inst;
    boolean cred;
    int lastop;
    boolean nofile;
    boolean triggerinst;
    int betalimit;
    int statgain;
    boolean losepoints;
    int[] realunlocked;
    boolean[] randomtrans;
    boolean shufflehover;
    int shufflefase;
    boolean[] shuffleop;
    boolean[] shufophover;
    int teledelay;
    boolean[] cancerpiece;
    boolean playonce;
    int teletimer;
    int diepls;
    boolean[] stimulateclick;
    boolean alreadystarted;
    boolean resumed;
    boolean stopped;
    boolean nclicked;
    int[][][] specialstats;
    int[][] statsalc;
    String[] statnames;
    int carpoints;
    int[] killtime;
    int[] dmgflash;
    boolean[] dflashchange;
    int writetime;
    boolean glassfase;
    int ghosttimer;
    boolean[] ghostflash;
    int ghostfade;
    int ghostflashtimer;
    int randomtimes;
    boolean ghosthit;
    int whatghostdo;
    boolean ghosttele;
    int ghostteletimer;
    boolean shownghost;
    int[] oldx;
    int[] oldz;
    int ghostattack;
    int ghostfar;
    int ghostattempt;
    int stagefade;
    boolean stagefadephase;
    double[] healthloss;
    boolean[][] customise;
    int[] maxlevel;
    int softlevelcap;
    int[] spglow;
    int[] spglowchange;
    int[][][] refcol;
    int[] sametime;
    boolean[][] condition;
    boolean[] updatehealth;
    boolean makebot;
    boolean viewbot;
    int[] statdrain;
    boolean[] safezone;
    boolean[] norender;
    boolean disablexp;
    boolean xpbuttonhover;
    int[] statchangers;
    boolean noexp;
    boolean rollonce;
    float chance;
    double[][] statmod;
    int[] statchanges;
    float[] proportion;
    double[][] statreduce;
    boolean scareflash;
    int scareflashtime;
    boolean turnbackon;
    int wallimmunity;
    boolean wallcountdown;
    boolean isithard;
    int[] actions;
    int[] undeadlock;
    int[] floor;
    int[] viewlimit;
    boolean pglowchange;
    int pglow;
    int startinglevel;
    double expmult;
    int powxpadjust;
    boolean autoreplay;
    int replayoption;
    int replayphase;
    boolean replaydisable;
    boolean replayfade;
    int replaytrans;
    boolean[] carqhover;
    int[] transfercar;
    double[] xbspratio;
    double[] rebsp;
    double[] xbsp;
    String[] songname;
    String[] artistname;
    
    public boolean over(final Image image, final int i, final int j, final int k, final int l) {
        final int i2 = image.getHeight(this.ob);
        final int j2 = image.getWidth(this.ob);
        return i > k - 5 && i < k + j2 + 5 && j > l - 5 && j < l + i2 + 5;
    }
    
    public void scoreshow(final Control control) {
        ++this.flipo;
        this.rd.drawImage(this.ptbg, 0, 0, null);
        if (this.glowb <= 60) {
            this.glowbphase = false;
        }
        if (this.glowb >= 225) {
            this.glowbphase = true;
        }
        if (!this.glowbphase) {
            this.glowb += 12;
        }
        else {
            this.glowb -= 12;
        }
        this.rd.setFont(this.adventure.deriveFont(1, 22.0f));
        this.ftm = this.rd.getFontMetrics();
        this.drawcs(40, "RESULTS", 0, 0, this.glowb, 3);
        this.rd.setFont(this.adventure.deriveFont(1, 18.0f));
        for (int a = 0; a < 11; ++a) {
            if (this.flipo < 335) {
                if (this.flipo == 15 + a * 10) {
                    this.initialise[a] = true;
                }
                if (this.initialise[a]) {
                    if (!this.sstransphase[a]) {
                        final int[] sstrans = this.sstrans;
                        final int n = a;
                        sstrans[n] += 11;
                    }
                    if (this.sstrans[a] >= 240) {
                        this.sstransphase[a] = true;
                    }
                    this.rd.setColor(new Color(0, 0, 0, this.sstrans[a]));
                    this.rd.drawString(new StringBuilder().append(this.ptplayers[a]).toString(), 245, 84 + a * 35);
                    this.rd.setColor(new Color(0, 58, 0, this.sstrans[a]));
                    if (this.position[a] <= 2) {
                        if (this.position[a] == 0) {
                            this.rd.drawString("1st", 450, 84 + a * 35);
                        }
                        if (this.position[a] == 1) {
                            this.rd.drawString("2nd", 450, 84 + a * 35);
                        }
                        if (this.position[a] == 2) {
                            this.rd.drawString("3rd", 450, 84 + a * 35);
                        }
                    }
                    else {
                        this.rd.drawString(this.position[a] + 1 + "th", 450, 84 + a * 35);
                    }
                }
                if (this.flipo == 180 + a * 10) {
                    this.initialise2[a] = true;
                }
                if (this.initialise2[a]) {
                    if (!this.sstransphase2[a]) {
                        final int[] sstrans2 = this.sstrans2;
                        final int n2 = a;
                        sstrans2[n2] += 11;
                    }
                    if (this.sstrans2[a] >= 240) {
                        this.sstransphase2[a] = true;
                    }
                    this.rd.setColor(new Color(0, 45, 0, this.sstrans2[a]));
                    if (this.ptmatch == 1 || this.ptmatch == 2 || this.ptmatch == 3) {
                        this.rd.drawString(new StringBuilder().append(10 - this.position[a]).toString(), 625, 84 + a * 35);
                    }
                    if (this.ptmatch == 4 || this.ptmatch == 5) {
                        this.rd.drawString(new StringBuilder().append(20 - this.position[a] * 2).toString(), 625, 84 + a * 35);
                    }
                }
            }
            else {
                this.spgained = true;
            }
            if (this.spgained) {
                this.rd.setFont(this.adventure.deriveFont(3, 20.0f));
                this.ftm = this.rd.getFontMetrics();
                this.drawcs(85, "TOTAL POINTS", 0, 0, 85, 3);
                this.pointsscore[a] = this.points[a] * 1000 + a;
                final int[] ints2 = { this.pointsscore[0], this.pointsscore[1], this.pointsscore[2], this.pointsscore[3], this.pointsscore[4], this.pointsscore[5], this.pointsscore[6], this.pointsscore[7], this.pointsscore[8], this.pointsscore[9], this.pointsscore[10] };
                Arrays.sort(ints2);
                if (this.pointsscore[a] == ints2[10]) {
                    this.first = a;
                }
                if (this.pointsscore[a] == ints2[9]) {
                    this.second = a;
                }
                if (this.pointsscore[a] == ints2[8]) {
                    this.third = a;
                }
                if (this.pointsscore[a] == ints2[7]) {
                    this.fourth = a;
                }
                if (this.pointsscore[a] == ints2[6]) {
                    this.fifth = a;
                }
                if (this.pointsscore[a] == ints2[5]) {
                    this.sixth = a;
                }
                if (this.pointsscore[a] == ints2[4]) {
                    this.seventh = a;
                }
                if (this.pointsscore[a] == ints2[3]) {
                    this.eighth = a;
                }
                if (this.pointsscore[a] == ints2[2]) {
                    this.ninth = a;
                }
                if (this.pointsscore[a] == ints2[1]) {
                    this.tenth = a;
                }
                if (this.pointsscore[a] == ints2[0]) {
                    this.eleventh = a;
                }
            }
        }
        if (this.flipo == 340) {
            this.initialise3[this.first] = true;
        }
        if (this.flipo == 350) {
            this.initialise3[this.second] = true;
        }
        if (this.flipo == 360) {
            this.initialise3[this.third] = true;
        }
        if (this.flipo == 370) {
            this.initialise3[this.fourth] = true;
        }
        if (this.flipo == 380) {
            this.initialise3[this.fifth] = true;
        }
        if (this.flipo == 390) {
            this.initialise3[this.sixth] = true;
        }
        if (this.flipo == 400) {
            this.initialise3[this.seventh] = true;
        }
        if (this.flipo == 410) {
            this.initialise3[this.eighth] = true;
        }
        if (this.flipo == 420) {
            this.initialise3[this.ninth] = true;
        }
        if (this.flipo == 430) {
            this.initialise3[this.tenth] = true;
        }
        if (this.flipo == 440) {
            this.initialise3[this.eleventh] = true;
        }
        for (int a = 0; a < 11; ++a) {
            if (this.initialise3[a]) {
                if (!this.sstransphase3[a]) {
                    final int[] sstrans3 = this.sstrans3;
                    final int n3 = a;
                    sstrans3[n3] += 11;
                }
                if (this.sstrans3[a] >= 240) {
                    this.sstransphase3[a] = true;
                }
            }
        }
        if (this.initialise3[this.first]) {
            this.rd.setColor(new Color(0, 0, 0, this.sstrans3[this.first]));
            this.rd.drawString(new StringBuilder().append(this.ptplayers[this.first]).toString(), 320, 120);
            this.rd.setColor(new Color(0, 58, 0, this.sstrans3[this.first]));
            this.rd.drawString(new StringBuilder().append(this.points[this.first]).toString(), 550, 120);
        }
        if (this.initialise3[this.second]) {
            this.rd.setColor(new Color(0, 0, 0, this.sstrans3[this.second]));
            this.rd.drawString(new StringBuilder().append(this.ptplayers[this.second]).toString(), 320, 150);
            this.rd.setColor(new Color(0, 58, 0, this.sstrans3[this.second]));
            this.rd.drawString(new StringBuilder().append(this.points[this.second]).toString(), 550, 150);
        }
        if (this.initialise3[this.third]) {
            this.rd.setColor(new Color(0, 0, 0, this.sstrans3[this.third]));
            this.rd.drawString(new StringBuilder().append(this.ptplayers[this.third]).toString(), 320, 180);
            this.rd.setColor(new Color(0, 58, 0, this.sstrans3[this.third]));
            this.rd.drawString(new StringBuilder().append(this.points[this.third]).toString(), 550, 180);
        }
        if (this.initialise3[this.fourth]) {
            this.rd.setColor(new Color(0, 0, 0, this.sstrans3[this.fourth]));
            this.rd.drawString(new StringBuilder().append(this.ptplayers[this.fourth]).toString(), 320, 210);
            this.rd.setColor(new Color(0, 58, 0, this.sstrans3[this.fourth]));
            this.rd.drawString(new StringBuilder().append(this.points[this.fourth]).toString(), 550, 210);
        }
        if (this.initialise3[this.fifth]) {
            this.rd.setColor(new Color(0, 0, 0, this.sstrans3[this.fifth]));
            this.rd.drawString(new StringBuilder().append(this.ptplayers[this.fifth]).toString(), 320, 240);
            this.rd.setColor(new Color(0, 58, 0, this.sstrans3[this.fifth]));
            this.rd.drawString(new StringBuilder().append(this.points[this.fifth]).toString(), 550, 240);
        }
        if (this.initialise3[this.sixth]) {
            this.rd.setColor(new Color(0, 0, 0, this.sstrans3[this.sixth]));
            this.rd.drawString(new StringBuilder().append(this.ptplayers[this.sixth]).toString(), 320, 270);
            this.rd.setColor(new Color(0, 58, 0, this.sstrans3[this.sixth]));
            this.rd.drawString(new StringBuilder().append(this.points[this.sixth]).toString(), 550, 270);
        }
        if (this.initialise3[this.seventh]) {
            this.rd.setColor(new Color(0, 0, 0, this.sstrans3[this.seventh]));
            this.rd.drawString(new StringBuilder().append(this.ptplayers[this.seventh]).toString(), 320, 300);
            this.rd.setColor(new Color(0, 58, 0, this.sstrans3[this.seventh]));
            this.rd.drawString(new StringBuilder().append(this.points[this.seventh]).toString(), 550, 300);
        }
        if (this.initialise3[this.eighth]) {
            this.rd.setColor(new Color(0, 0, 0, this.sstrans3[this.eighth]));
            this.rd.drawString(new StringBuilder().append(this.ptplayers[this.eighth]).toString(), 320, 330);
            this.rd.setColor(new Color(0, 58, 0, this.sstrans3[this.eighth]));
            this.rd.drawString(new StringBuilder().append(this.points[this.eighth]).toString(), 550, 330);
        }
        if (this.initialise3[this.ninth]) {
            this.rd.setColor(new Color(0, 0, 0, this.sstrans3[this.ninth]));
            this.rd.drawString(new StringBuilder().append(this.ptplayers[this.ninth]).toString(), 320, 360);
            this.rd.setColor(new Color(0, 58, 0, this.sstrans3[this.ninth]));
            this.rd.drawString(new StringBuilder().append(this.points[this.ninth]).toString(), 550, 360);
        }
        if (this.initialise3[this.tenth]) {
            this.rd.setColor(new Color(0, 0, 0, this.sstrans3[this.tenth]));
            this.rd.drawString(new StringBuilder().append(this.ptplayers[this.tenth]).toString(), 320, 390);
            this.rd.setColor(new Color(0, 58, 0, this.sstrans3[this.tenth]));
            this.rd.drawString(new StringBuilder().append(this.points[this.tenth]).toString(), 550, 390);
        }
        if (this.initialise3[this.eleventh]) {
            this.rd.setColor(new Color(0, 0, 0, this.sstrans3[this.eleventh]));
            this.rd.drawString(new StringBuilder().append(this.ptplayers[this.eleventh]).toString(), 320, 420);
            this.rd.setColor(new Color(0, 58, 0, this.sstrans3[this.eleventh]));
            this.rd.drawString(new StringBuilder().append(this.points[this.eleventh]).toString(), 550, 420);
        }
        if (this.flipo >= 465) {
            this.rd.drawImage(this.contin[this.pcontin], 390, 447, null);
        }
    }
    
    public void ptstart(final Control control) {
        if (this.ptmatch == 1) {
            if (!this.norepeat) {
                for (int d = 0; d < 11; ++d) {
                    this.pinform[d] = false;
                    this.pnoform[d] = false;
                }
                this.inform[0] = (int)(Math.random() * 10.0) + 1;
                this.inform[1] = (int)(Math.random() * 10.0) + 1;
                this.noform[0] = (int)(Math.random() * 7.0) + 1;
                this.noform[1] = (int)(Math.random() * 7.0) + 1;
                if (this.inform[0] != this.inform[1] && this.inform[0] != this.noform[0] && this.inform[0] != this.noform[1] && this.inform[1] != this.noform[0] && this.inform[1] != this.noform[1] && this.noform[0] != this.noform[1]) {
                    this.norepeat = true;
                }
            }
            if (this.norepeat) {
                this.pinform[this.inform[0]] = true;
                this.pinform[this.inform[1]] = true;
                this.pnoform[this.noform[0]] = true;
                this.pnoform[this.noform[1]] = true;
            }
            if (this.showopstage < 150 || (this.showopstage >= 175 && this.showopstage < 180)) {
                ++this.showopstage;
            }
            if (this.showopstage < 200) {
                this.rd.drawImage(this.ptbg, 0, 0, null);
            }
            if (this.showopstage <= 150) {
                this.rd.setFont(this.adventure.deriveFont(1, 22.0f));
                this.ftm = this.rd.getFontMetrics();
                if (!this.glowgtextphase) {
                    this.glowgtext += 12;
                }
                else {
                    this.glowgtext -= 12;
                }
                if (this.glowgtext <= 60) {
                    this.glowgtextphase = false;
                }
                if (this.glowgtext >= 200) {
                    this.glowgtextphase = true;
                }
                this.drawcs(40, "OPPONENTS:", 0, this.glowgtext, 0, 3);
                for (int a = 1; a < 11; ++a) {
                    this.rd.setFont(this.adventure.deriveFont(1, 19.0f));
                    if (this.showopstage == 15 + a * 10) {
                        this.initiate[a - 1] = true;
                    }
                    if (this.initiate[a - 1]) {
                        if (!this.namephase[a - 1]) {
                            final int[] nametrans = this.nametrans;
                            final int n = a - 1;
                            nametrans[n] += 11;
                        }
                        if (this.nametrans[a - 1] >= 240) {
                            this.namephase[a - 1] = true;
                        }
                        this.rd.setColor(new Color(30, 30, 0, this.nametrans[a - 1]));
                        this.rd.drawString(new StringBuilder().append(this.ptplayers[a]).toString(), 25, 44 + a * 40);
                        if (this.pinform[a]) {
                            if (a <= 7) {
                                this.rd.setColor(new Color(150, 0, 0, this.nametrans[a - 1]));
                                this.rd.drawString("IN FORM", 200, 44 + a * 40);
                            }
                            if (a == 8 || a == 9) {
                                this.rd.setColor(new Color(150, 0, 0, this.nametrans[a - 1]));
                                this.rd.drawString("IN FORM,", 200, 44 + a * 40);
                                this.rd.setColor(new Color(100, 0, 0, this.nametrans[a - 1]));
                                this.rd.drawString("CONTENDER", 312, 44 + a * 40);
                            }
                            if (a == 10) {
                                this.rd.setColor(new Color(150, 0, 0, this.nametrans[a - 1]));
                                this.rd.drawString("IN FORM,", 200, 44 + a * 40);
                                this.rd.setColor(new Color(50, 0, 0, this.nametrans[a - 1]));
                                this.rd.drawString("FAVOURITE", 312, 44 + a * 40);
                            }
                        }
                        else {
                            if (a == 8 || a == 9) {
                                this.rd.setColor(new Color(100, 0, 0, this.nametrans[a - 1]));
                                this.rd.drawString("CONTENDER", 200, 44 + a * 40);
                            }
                            if (a == 10) {
                                this.rd.setColor(new Color(50, 0, 0, this.nametrans[a - 1]));
                                this.rd.drawString("FAVOURITE", 200, 44 + a * 40);
                            }
                        }
                        if (this.pnoform[a]) {
                            this.rd.setColor(new Color(0, 115, 0, this.nametrans[a - 1]));
                            this.rd.drawString("OUT OF FORM", 200, 44 + a * 40);
                        }
                    }
                }
            }
            if (this.showopstage == 150) {
                this.rd.setFont(this.adventure.deriveFont(1, 19.5f));
                this.rd.setColor(new Color(0, 0, 0));
                if (this.ptplayers[0] == "-") {
                    this.rd.drawString("AND YOUR NAME IS...?", 560, 84);
                }
                else {
                    this.rd.drawString(this.ptplayers[0] + "?", 560, 84);
                    this.rd.drawImage(this.next[this.pnext], 787, 447, null);
                    this.aprogress = true;
                }
                if (this.namehover[0]) {
                    this.butrans[0] = 255;
                    this.butrans[1] = 115;
                    this.butrans[2] = 115;
                    this.butrans[3] = 115;
                }
                if (this.namehover[1]) {
                    this.butrans[0] = 115;
                    this.butrans[1] = 255;
                    this.butrans[2] = 115;
                    this.butrans[3] = 115;
                }
                if (this.namehover[2]) {
                    this.butrans[0] = 115;
                    this.butrans[1] = 115;
                    this.butrans[2] = 255;
                    this.butrans[3] = 115;
                }
                if (this.namehover[3]) {
                    this.butrans[0] = 115;
                    this.butrans[1] = 115;
                    this.butrans[2] = 115;
                    this.butrans[3] = 255;
                }
                final Polygon dale = new Polygon();
                dale.addPoint(560, 145);
                dale.addPoint(568, 120);
                dale.addPoint(762, 120);
                dale.addPoint(770, 145);
                dale.addPoint(762, 170);
                dale.addPoint(568, 170);
                this.rd.setColor(new Color(20, 20, 20, this.butrans[0]));
                this.rd.fillPolygon(dale);
                final Polygon dale2 = new Polygon();
                dale2.addPoint(560, 220);
                dale2.addPoint(568, 195);
                dale2.addPoint(762, 195);
                dale2.addPoint(770, 220);
                dale2.addPoint(762, 245);
                dale2.addPoint(568, 245);
                this.rd.setColor(new Color(20, 20, 20, this.butrans[1]));
                this.rd.fillPolygon(dale2);
                final Polygon dale3 = new Polygon();
                dale3.addPoint(560, 295);
                dale3.addPoint(568, 270);
                dale3.addPoint(762, 270);
                dale3.addPoint(770, 295);
                dale3.addPoint(762, 320);
                dale3.addPoint(568, 320);
                this.rd.setColor(new Color(20, 20, 20, this.butrans[2]));
                this.rd.fillPolygon(dale3);
                final Polygon dale4 = new Polygon();
                dale4.addPoint(560, 370);
                dale4.addPoint(568, 345);
                dale4.addPoint(762, 345);
                dale4.addPoint(770, 370);
                dale4.addPoint(762, 395);
                dale4.addPoint(568, 395);
                this.rd.setColor(new Color(20, 20, 20, this.butrans[3]));
                this.rd.fillPolygon(dale4);
                this.rd.setColor(new Color(240, 240, 240));
                this.rd.setFont(this.adventure.deriveFont(1, 20.0f));
                this.rd.drawString("DragShot", 612, 152);
                this.rd.drawString("ToaZuka", 620, 227);
                this.rd.drawString("Velocity", 616, 302);
                this.rd.drawString("KRC", 638, 377);
            }
            if (this.showopstage == 180) {
                this.rd.drawImage(this.dude[0], 600, 80, null);
                this.rd.setFont(this.adventure.deriveFont(1, 22.0f));
                this.ftm = this.rd.getFontMetrics();
                this.drawcs(40, "MATCH 1", 0, 0, 55, 3);
                this.rd.setFont(this.adventure.deriveFont(1, 17.0f));
                this.rd.setColor(new Color(55, 0, 0));
                this.rd.drawString("VENUE - Centrifrugal Rush, Under Water?", 25, 90);
                this.rd.drawString("TYPE - Wasting, Bounty Hunter", 25, 115);
                this.rd.setFont(this.adventure.deriveFont(1, 20.0f));
                this.ftm = this.rd.getFontMetrics();
                this.drawcs(160, "RULES", 0, 0, 55, 3);
                this.drawcs(330, "SCORING", 0, 0, 55, 3);
                this.rd.setFont(this.adventure.deriveFont(1, 15.5f));
                this.rd.setColor(new Color(0, 0, 0));
                this.rd.drawString("- No fixing.", 25, 210);
                this.rd.drawString("- Every time you waste a car, you get a point.", 25, 230);
                this.rd.drawString("- You lose a point if you're wasted.", 25, 250);
                this.rd.drawString("- The first person to 7 points wins!", 25, 270);
                this.rd.drawString("- No special attacks!", 25, 290);
                this.rd.drawString("Winner gets 10 points, 2nd place gets 9 points, 3rd place gets 8, etc.", 25, 380);
                this.rd.drawString("This contributes to your overall score in the tournament!", 25, 400);
                this.rd.drawImage(this.contin[this.pcontin], 390, 447, null);
            }
            if (this.showopstage == 190) {
                this.stracks[25].unloadMod();
                this.loadedt[25] = false;
                this.showopstage = 195;
            }
        }
        for (int b = 0; b < 2; ++b) {
            if (this.inform[b] <= 7) {
                this.stataffect[this.inform[b]] = 0.085;
            }
            else {
                if (this.inform[b] == 8 || this.inform[b] == 9) {
                    this.stataffect[this.inform[b]] = 0.17;
                }
                if (this.inform[b] == 10) {
                    this.stataffect[this.inform[b]] = 0.255;
                }
            }
            this.stataffect[this.noform[b]] = -0.085;
            if (this.inform[0] != 8 && this.inform[1] != 8) {
                this.stataffect[8] = 0.085;
            }
            if (this.inform[0] != 9 && this.inform[1] != 9) {
                this.stataffect[9] = 0.085;
            }
            if (this.inform[0] != 10 && this.inform[1] != 10) {
                this.stataffect[10] = 0.17;
            }
        }
        if (this.ptmatch == 2) {
            if (this.showopstage == 0) {
                this.rd.drawImage(this.ptbg, 0, 0, null);
                this.rd.drawImage(this.dude[0], 620, 50, null);
                this.rd.setFont(this.adventure.deriveFont(1, 22.0f));
                this.ftm = this.rd.getFontMetrics();
                this.drawcs(40, "MATCH 2", 0, 0, 55, 3);
                this.rd.setFont(this.adventure.deriveFont(1, 17.0f));
                this.rd.setColor(new Color(55, 0, 0));
                this.rd.drawString("VENUE - The Fast and The Furious + The Radical", 25, 90);
                this.rd.drawString("TYPE - Elimination, Mighty Eight", 25, 115);
                this.rd.setFont(this.adventure.deriveFont(1, 20.0f));
                this.ftm = this.rd.getFontMetrics();
                this.drawcs(160, "RULES", 0, 0, 55, 3);
                this.drawcs(350, "SCORING", 0, 0, 55, 3);
                this.rd.setFont(this.adventure.deriveFont(1, 15.5f));
                this.rd.setColor(new Color(0, 0, 0));
                this.rd.drawString("- Every two checkpoints, the person in last is eliminated.", 25, 210);
                this.rd.drawString("- This means that they're wasted and can't continue.", 25, 230);
                this.rd.drawString("- The race continues until there's only one person left or if you're wasted.", 25, 250);
                this.rd.drawString("- You can't waste here - no one takes damage unless they're eliminated!", 25, 270);
                this.rd.drawString("- No special attacks allowed.", 25, 290);
                this.rd.drawString("- No nitro is allowed either.", 25, 310);
                this.rd.drawString("Winner gets 10 points, 2nd place gets 9 points, 3rd place gets 8, etc.", 25, 400);
                this.rd.drawImage(this.contin[this.pcontin], 390, 447, null);
            }
            if (this.showopstage == 190) {
                this.stracks[60].unloadMod();
                this.loadedt[60] = false;
                this.showopstage = 195;
            }
        }
        if (this.ptmatch == 3) {
            if (this.showopstage == 0) {
                this.rd.drawImage(this.ptbg, 0, 0, null);
                this.rd.drawImage(this.dude[0], 650, 30, null);
                this.rd.setFont(this.adventure.deriveFont(1, 22.0f));
                this.ftm = this.rd.getFontMetrics();
                this.drawcs(40, "MATCH 3", 0, 0, 55, 3);
                this.rd.setFont(this.adventure.deriveFont(1, 17.0f));
                this.rd.setColor(new Color(55, 0, 0));
                this.rd.drawString("VENUE - Rolling with the Big Boys (2 laps)", 25, 90);
                this.rd.drawString("TYPE - Racing, Nimi", 25, 115);
                this.rd.setFont(this.adventure.deriveFont(1, 20.0f));
                this.ftm = this.rd.getFontMetrics();
                this.drawcs(160, "RULES", 0, 0, 55, 3);
                this.drawcs(300, "SCORING", 0, 0, 55, 3);
                this.rd.setFont(this.adventure.deriveFont(1, 15.5f));
                this.rd.setColor(new Color(0, 0, 0));
                this.rd.drawString("- Racing only! No one takes damage here.", 25, 210);
                this.rd.drawString("- No nitro is allowed.", 25, 230);
                this.rd.drawString("- Specials aren't allowed either.", 25, 250);
                this.rd.drawString("Winner gets 10 points, 2nd place gets 9 points, 3rd place gets 8, etc.", 25, 350);
                this.rd.drawImage(this.contin[this.pcontin], 390, 447, null);
            }
            if (this.showopstage == 190) {
                this.stracks[73].unloadMod();
                this.loadedt[73] = false;
                this.showopstage = 195;
            }
        }
        if (this.ptmatch == 4) {
            if (this.showopstage == 0) {
                this.rd.drawImage(this.ptbg, 0, 0, null);
                this.rd.drawImage(this.dude[0], 650, 30, null);
                this.rd.setFont(this.adventure.deriveFont(1, 22.0f));
                this.ftm = this.rd.getFontMetrics();
                this.drawcs(40, "MATCH 4", 0, 0, 55, 3);
                this.rd.setFont(this.adventure.deriveFont(1, 17.0f));
                this.rd.setColor(new Color(55, 0, 0));
                this.rd.drawString("VENUE - The Mad Party", 25, 90);
                this.rd.drawString("TYPE - Wasting, Dr. Daler", 25, 115);
                this.rd.setFont(this.adventure.deriveFont(1, 20.0f));
                this.ftm = this.rd.getFontMetrics();
                this.drawcs(160, "RULES", 0, 0, 55, 3);
                this.drawcs(320, "SCORING", 0, 0, 55, 3);
                this.rd.setFont(this.adventure.deriveFont(1, 15.5f));
                this.rd.setColor(new Color(0, 0, 0));
                this.rd.drawString("- Wasting someone grants you a point.", 25, 210);
                this.rd.drawString("- Getting wasted loses you a point.", 25, 230);
                this.rd.drawString("- No nitro is allowed.", 25, 250);
                this.rd.drawString("- First to 8 points wins!", 25, 270);
                this.rd.drawString("Winner gets 20 points, 2nd place gets 18 points, 3rd place gets 16, etc.", 25, 370);
                this.rd.drawImage(this.contin[this.pcontin], 390, 447, null);
            }
            if (this.showopstage == 190) {
                this.stracks[66].unloadMod();
                this.loadedt[66] = false;
                this.showopstage = 195;
            }
        }
        if (this.ptmatch == 5) {
            if (this.showopstage == 0) {
                this.rd.drawImage(this.ptbg, 0, 0, null);
                this.rd.drawImage(this.dude[0], 650, 30, null);
                this.rd.setFont(this.adventure.deriveFont(1, 22.0f));
                this.ftm = this.rd.getFontMetrics();
                this.drawcs(40, "MATCH 5", 0, 0, 55, 3);
                this.rd.setFont(this.adventure.deriveFont(1, 17.0f));
                this.rd.setColor(new Color(55, 0, 0));
                this.rd.drawString("VENUE - The Garden of the Van", 25, 90);
                this.rd.drawString("TYPE - Wasting Elimination, Old Van", 25, 115);
                this.rd.setFont(this.adventure.deriveFont(1, 20.0f));
                this.ftm = this.rd.getFontMetrics();
                this.drawcs(160, "RULES", 0, 0, 55, 3);
                this.drawcs(320, "SCORING", 0, 0, 55, 3);
                this.rd.setFont(this.adventure.deriveFont(1, 15.5f));
                this.rd.setColor(new Color(0, 0, 0));
                this.rd.drawString("- This has a similar concept to the last match and Match 1.", 25, 210);
                this.rd.drawString("- The difference is there's a timer as well.", 25, 230);
                this.rd.drawString("- When the timer reaches 0, the car in last place is eliminated.", 25, 250);
                this.rd.drawString("- The game ends if you're eliminated or if only one car remains.", 25, 270);
                this.rd.drawString("Winner gets 20 points, 2nd place gets 18 points, 3rd place gets 16, etc.", 25, 370);
                this.rd.drawImage(this.contin[this.pcontin], 390, 447, null);
            }
            if (this.showopstage == 190) {
                this.stracks[60].unloadMod();
                this.loadedt[60] = false;
                this.showopstage = 195;
            }
        }
    }
    
    public void cantgo(final Control control, final CheckPoints checkpoints) {
        this.pnext = 0;
        this.trackbg(false);
        this.rd.setFont(new Font("SansSerif", 1, 13));
        this.ftm = this.rd.getFontMetrics();
        int mxstage = this.unlocked[0];
        if (this.careermode) {
            mxstage = this.unlocked[1];
        }
        this.drawcs(150, "This stage will be unlocked when stage " + mxstage + " is complete!", 177, 177, 177, 3);
        int i = 0;
        do {
            this.rd.drawImage(this.pgate, 312 + i * 30, 230, null);
        } while (++i < 9);
        this.rd.setFont(new Font("SansSerif", 1, 11));
        this.ftm = this.rd.getFontMetrics();
        if (this.aflk) {
            this.drawcs(200, "[ Stage " + (mxstage + 1) + " Locked ]", 255, 128, 0, 3);
            this.aflk = false;
        }
        else {
            this.drawcs(200, "[ Stage " + (mxstage + 1) + " Locked ]", 255, 0, 0, 3);
            this.aflk = true;
        }
        this.rd.drawImage(this.br, 100, 40, null);
        this.rd.drawImage(this.back[this.pback], 405, 360, null);
        this.rd.setFont(new Font("SansSerif", 1, 11));
        this.ftm = this.rd.getFontMetrics();
        --this.lockcnt;
        if (this.lockcnt == 0 || control.enter || control.handb || control.left) {
            control.left = false;
            control.handb = false;
            control.enter = false;
            this.fase = 1;
        }
        this.rd.setColor(new Color(0, 0, 0));
        this.rd.fillRect(0, 0, 100, 480);
        this.rd.fillRect(770, 0, 100, 480);
        this.rd.fillRect(100, 0, 670, 40);
        this.rd.fillRect(100, 440, 670, 40);
    }
    
    public void loadingstage(final int i) {
        this.trackbg(true);
        this.rd.drawImage(this.br, 100, 40, null);
        this.rd.setColor(new Color(0, 0, 0));
        this.rd.fillRect(0, 0, 100, 480);
        this.rd.fillRect(770, 0, 100, 480);
        this.rd.fillRect(100, 0, 670, 40);
        this.rd.fillRect(100, 440, 670, 40);
        this.rd.setColor(new Color(177, 177, 177));
        this.rd.fillRoundRect(300, 190, 270, 52, 20, 40);
        this.rd.setColor(new Color(120, 120, 120));
        this.rd.drawRoundRect(300, 190, 270, 52, 20, 40);
        this.rd.setFont(new Font("SansSerif", 1, 13));
        this.ftm = this.rd.getFontMetrics();
        if (!this.bonstage) {
            this.drawcs(220, "Loading Stage " + i + ", please wait...", 0, 0, 0, 3);
        }
        else {
            this.drawcs(220, "Loading stage, please wait...", 0, 0, 0, 3);
        }
        this.rd.setFont(new Font("SansSerif", 1, 11));
        this.ftm = this.rd.getFontMetrics();
        if (this.lastload != -22) {
            this.stages.loadMod(135, 7800, 125, this.sunny, this.macn);
            this.lastload = -22;
        }
        else {
            this.stages.stop();
        }
    }
    
    public void fleximage(final Image image, final int i, final int j) {
        if (i == 0) {
            this.flexpix = new int[600000];
            final PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, 870, 480, this.flexpix, 0, 870);
            try {
                pixelgrabber.grabPixels();
            }
            catch (final InterruptedException ex) {}
        }
        int k = 0;
        int l = 0;
        int i2 = 0;
        int j2 = 0;
        int k2 = (int)(Math.random() * 128.0);
        int l2 = (int)(5.0 + Math.random() * 15.0);
        int i3 = 0;
        do {
            final Color color = new Color(this.flexpix[i3]);
            int j3 = 0;
            int k3 = 0;
            int l3 = 0;
            if (k == 0) {
                j3 = (l = color.getRed());
                k3 = (i2 = color.getGreen());
                l3 = (j2 = color.getBlue());
            }
            else {
                j3 = (l = (int)((color.getRed() + l * 0.38f * i) / (1.0f + 0.38f * i)));
                k3 = (i2 = (int)((color.getGreen() + i2 * 0.38f * i) / (1.0f + 0.38f * i)));
                l3 = (j2 = (int)((color.getBlue() + j2 * 0.38f * i) / (1.0f + 0.38f * i)));
            }
            if (++k == 870) {
                k = 0;
            }
            int i4 = (j3 * 17 + k3 + l3 + k2) / 22;
            int j4 = (k3 * 17 + j3 + l3 + k2) / 22;
            int k4 = (l3 * 17 + j3 + k3 + k2) / 22;
            if (j == 17) {
                i4 = (j3 * 17 + k3 + l3 + k2) / 22;
                j4 = (k3 * 17 + j3 + l3 + k2) / 21;
                k4 = (l3 * 17 + j3 + k3 + k2) / 20;
            }
            if (--l2 == 0) {
                k2 = (int)(Math.random() * 128.0);
                l2 = (int)(5.0 + Math.random() * 15.0);
            }
            final Color color2 = new Color(i4, j4, k4);
            this.flexpix[i3] = color2.getRGB();
        } while (++i3 < 600000);
        this.fleximg = this.createImage(new MemoryImageSource(870, 480, this.flexpix, 0, 870));
        this.rd.drawImage(this.fleximg, 0, 0, null);
    }
    
    public void arrow(final int i, final int j, final CheckPoints checkpoints, final boolean flag, final Madness[] madness, final boolean swap, final ContO[] conto) {
        final int[] ai = new int[7];
        final int[] ai2 = new int[7];
        final int[] ai3 = new int[7];
        final int c = 435;
        final byte byte0 = -90;
        final char c2 = '\u02bc';
        int k = 0;
        do {
            ai2[k] = byte0;
        } while (++k < 7);
        ai[0] = c;
        ai3[0] = c2 + 'n';
        ai[1] = c - 35;
        ai3[1] = c2 + '2';
        ai[2] = c - 15;
        ai3[2] = c2 + '2';
        ai[3] = c - 15;
        ai3[3] = c2 - '2';
        ai[4] = c + 15;
        ai3[4] = c2 - '2';
        ai[5] = c + 15;
        ai3[5] = c2 + '2';
        ai[6] = c + 35;
        ai3[6] = c2 + '2';
        k = 0;
        boolean noopp = false;
        if (!flag) {
            char c3 = '\0';
            if (checkpoints.x[i] - checkpoints.opx[0] >= 0) {
                c3 = '´';
            }
            k = (int)('Z' + c3 + Math.atan((checkpoints.z[i] - checkpoints.opz[0]) / (double)(checkpoints.x[i] - checkpoints.opx[0])) / 0.017453292519943295);
        }
        else {
            int l = 0;
            int k2 = -1;
            boolean flag2 = false;
            int l2 = 1;
            do {
                if ((this.py(checkpoints.opx[0] / 100, checkpoints.opx[l2] / 100, checkpoints.opz[0] / 100, checkpoints.opz[l2] / 100) < k2 || k2 == -1) && (!flag2 || checkpoints.onscreen[l2] != 0) && checkpoints.dested[l2] == 0 && !this.norender[l2] && !this.noarrow[l2]) {
                    if (!this.arrowlocked) {
                        l = l2;
                    }
                    else if (checkpoints.dested[this.lockedon] == 0) {
                        l = this.lockedon;
                        if (this.noarrow[this.lockedon] || this.norender[this.lockedon]) {
                            l = 0;
                        }
                    }
                    else {
                        l = l2;
                        this.arrowlocked = false;
                    }
                    k2 = this.py(checkpoints.opx[0] / 100, checkpoints.opx[l2] / 100, checkpoints.opz[0] / 100, checkpoints.opz[l2] / 100);
                    if (checkpoints.onscreen[l2] == 0) {
                        continue;
                    }
                    flag2 = true;
                }
            } while (++l2 < this.nplayers);
            l2 = 0;
            if (checkpoints.opx[l] - checkpoints.opx[0] >= 0) {
                l2 = 180;
            }
            k = (int)(90 + l2 + Math.atan((checkpoints.opz[l] - checkpoints.opz[0]) / (double)(checkpoints.opx[l] - checkpoints.opx[0])) / 0.017453292519943295);
            if (!this.dontdisplay) {
                this.rd.setFont(new Font("Arial", 1, 11));
                this.ftm = this.rd.getFontMetrics();
                this.drawcs(13, "[                                    ]", 76, 67, 240, 0);
                if (this.beastopponent[l]) {
                    this.rd.setFont(this.adventure.deriveFont(1, 11.0f));
                    this.ftm = this.rd.getFontMetrics();
                }
                else {
                    this.rd.setFont(new Font("Arial", 1, 11));
                    this.ftm = this.rd.getFontMetrics();
                }
                int bright = 0;
                if ((checkpoints.stage == 6 || checkpoints.stage == 7 || checkpoints.stage == 10 || (checkpoints.stage == 15 && !this.bonstage) || checkpoints.stage == 22) && this.careermode) {
                    bright = 150;
                }
                if (!this.fixspecials[l] && !madness[l].frozen && !madness[l].redstr && !madness[l].leech && !madness[l].strswap) {
                    this.rd.setColor(new Color(bright, bright, bright, this.shadowtrans[l]));
                }
                if (this.fixspecials[l] && !madness[l].frozen && !madness[l].redstr && !madness[l].leech && !madness[l].strswap) {
                    this.rd.setColor(new Color(this.glowg[l], 0, 0, this.shadowtrans[l]));
                }
                if (madness[l].frozen && !madness[l].redstr && !madness[l].leech && !madness[l].strswap) {
                    this.rd.setColor(new Color(0, 0, this.glowg[l], this.shadowtrans[l]));
                }
                if (madness[l].redstr && !madness[l].leech && !madness[l].strswap) {
                    this.rd.setColor(new Color(this.glowg2[l], this.glowg2[l] / 2, 0, this.shadowtrans[l]));
                }
                if (madness[l].leech && !madness[l].strswap) {
                    this.rd.setColor(new Color(this.glowg2[l] - 60, (this.glowg2[l] - 60) * 3 / 4, 0, this.shadowtrans[l]));
                }
                if (madness[l].strswap) {
                    this.rd.setColor(new Color(0, this.glowg[l], 0, this.shadowtrans[l]));
                }
                if (l == 0) {
                    this.rd.drawString("-", 435 - this.ftm.stringWidth("-") / 2, 13);
                }
                else {
                    this.rd.drawString(new StringBuilder().append(this.names[this.sc[l]]).toString(), 435 - this.ftm.stringWidth(new StringBuilder().append(this.names[this.sc[l]]).toString()) / 2, 13);
                }
            }
            else {
                this.rd.setFont(new Font("Arial", 1, 12));
                this.ftm = this.rd.getFontMetrics();
                this.drawcs(13, "[                                    ]", 76, 67, 240, 0);
                this.drawcs(13, this.ptplayers[l], 0, 0, 0, 0);
                this.rd.setFont(new Font("Arial", 0, 11));
                this.ftm = this.rd.getFontMetrics();
                this.drawcs(25, this.names[this.sc[l]], 0, 0, 0, 0);
                this.rd.setFont(new Font("Arial", 1, 11));
                this.ftm = this.rd.getFontMetrics();
            }
            if (l != 0) {
                if (this.careermode) {
                    this.rd.setFont(this.adventure.deriveFont(1, 15.0f));
                    this.ftm = this.rd.getFontMetrics();
                    if (madness[l].level[this.sc[l]] > madness[0].level[this.sc[0]] + 5) {
                        this.rd.setColor(new Color(150, 0, 0));
                    }
                    else {
                        this.rd.setColor(new Color(0, 60, 0));
                    }
                    this.rd.drawString("Level " + madness[l].level[this.sc[l]], 523, 15);
                    this.rd.setFont(new Font("Arial", 1, 11));
                    this.ftm = this.rd.getFontMetrics();
                }
                if (!swap) {
                    int i_66_ = (int)(60.0f * (madness[l].hitmag / (float)madness[l].maxmag[this.sc[l]]));
                    int i_67_ = 244;
                    int i_68_ = 244;
                    int i_69_ = 11;
                    if (i_66_ > 20) {
                        i_68_ = (int)(244.0f - 233.0f * ((i_66_ - 20) / 40.0f));
                    }
                    i_67_ += (i_67_ * (this.m.snap[0] / 100.0f));  // cast: bytecode-verified
                    if (i_66_ > 60) {
                        i_66_ = 60;
                    }
                    if (i_67_ > 255) {
                        i_67_ = 255;
                    }
                    if (i_67_ < 0) {
                        i_67_ = 0;
                    }
                    i_68_ += (i_68_ * (this.m.snap[1] / 100.0f));  // cast: bytecode-verified
                    if (i_68_ > 255) {
                        i_68_ = 255;
                    }
                    if (i_68_ < 0) {
                        i_68_ = 0;
                    }
                    i_69_ += (i_69_ * (this.m.snap[2] / 100.0f));  // cast: bytecode-verified
                    if (i_69_ > 255) {
                        i_69_ = 255;
                    }
                    if (i_69_ < 0) {
                        i_69_ = 0;
                    }
                    this.rd.setColor(new Color(i_67_, i_68_, i_69_));
                    this.rd.fillRect(300, 5, i_66_, 6);
                    this.rd.setColor(new Color(this.dmgflash[l], 0, 0));
                    this.rd.drawRect(300, 5, 60, 6);
                    this.rd.setColor(new Color(129, 0, 0));
                }
                else {
                    int aispecs = (int)(60.0f * (madness[l].spatk / 120.0f));
                    int i_71_ = (int)(220.0f + 220.0f * (this.m.snap[0] / 100.0f));
                    if (!this.fixspecials[l]) {
                        if (i_71_ > 255) {
                            i_71_ = 255;
                        }
                        if (i_71_ < 0) {
                            i_71_ = 0;
                        }
                    }
                    else {
                        aispecs = (int)(60.0f * (madness[l].speclast / 120.0f));
                        i_71_ = (int)(85.0f + 85.0f * (this.m.snap[0] / 100.0f));
                        if (i_71_ > 255) {
                            i_71_ = 255;
                        }
                        if (i_71_ < 0) {
                            i_71_ = 0;
                        }
                    }
                    this.rd.setColor(new Color(i_71_, 0, 0));
                    this.rd.fillRect(300, 5, aispecs, 6);
                    this.rd.setColor(new Color(0, 0, 0));
                    this.rd.drawRect(300, 5, 60, 6);
                }
                final int aipower = (int)(60.0f * (madness[l].power / 98.0f));
                int red = (int)(128.0f + 128.0f * (this.m.snap[0] / 100.0f));
                if (madness[l].power == 98.0f) {
                    red = (int)(64.0f + 64.0f * (this.m.snap[0] / 100.0f));
                }
                if (red > 255) {
                    red = 255;
                }
                if (red < 0) {
                    red = 0;
                }
                int green = (int)(244.0f + 244.0f * (this.m.snap[1] / 100.0f));
                if (green > 255) {
                    green = 255;
                }
                if (green < 0) {
                    green = 0;
                }
                int blue = (int)(244.0f + 244.0f * (this.m.snap[2] / 100.0f));
                if (blue > 255) {
                    blue = 255;
                }
                if (blue < 0) {
                    blue = 0;
                }
                if (!conto[l].floorguardian) {
                    this.rd.setColor(new Color(red, green, blue));
                    this.rd.fillRect(300, 16, aipower, 6);
                    this.rd.setColor(new Color(0, 0, 0));
                    this.rd.drawRect(300, 16, 60, 6);
                }
            }
            else {
                noopp = true;
            }
        }
        for (k += this.m.xz; k < 0; k += 360) {}
        while (k > 180) {
            k -= 360;
        }
        if (!flag) {
            if (k > 130) {
                k = 130;
            }
            if (k < -130) {
                k = -130;
            }
        }
        else {
            if (k > 100) {
                k = 100;
            }
            if (k < -100) {
                k = -100;
            }
        }
        if (Math.abs(this.ana - k) < 180) {
            if (Math.abs(this.ana - k) < 10) {
                this.ana = k;
            }
            else if (this.ana < k) {
                this.ana += 10;
            }
            else {
                this.ana -= 10;
            }
        }
        else {
            if (k < 0) {
                this.ana += 15;
                if (this.ana > 180) {
                    this.ana -= 360;
                }
            }
            if (k > 0) {
                this.ana -= 15;
                if (this.ana < -180) {
                    this.ana += 360;
                }
            }
        }
        this.rot(ai, ai3, c, c2, this.ana, 7);
        k = Math.abs(this.ana);
        if (!flag) {
            if (k > 7 || j > 0 || j == -2 || this.cntan != 0) {
                int i2 = 0;
                do {
                    ai[i2] = this.xs(ai[i2], ai3[i2]);
                    ai2[i2] = this.ys(ai2[i2], ai3[i2]);
                } while (++i2 < 7);
                i2 = (int)(190.0f + 190.0f * (this.m.snap[0] / 100.0f));
                if (i2 > 255) {
                    i2 = 255;
                }
                if (i2 < 0) {
                    i2 = 0;
                }
                int l3 = (int)(255.0f + 255.0f * (this.m.snap[1] / 100.0f));
                if (l3 > 255) {
                    l3 = 255;
                }
                if (l3 < 0) {
                    l3 = 0;
                }
                int j2 = 0;
                if (j <= 0) {
                    if (k <= 45 && j != -2 && this.cntan == 0) {
                        i2 = (i2 * k + this.m.csky[0] * (45 - k)) / 45;
                        l3 = (l3 * k + this.m.csky[1] * (45 - k)) / 45;
                        j2 = (j2 * k + this.m.csky[2] * (45 - k)) / 45;
                    }
                    if (k >= 90) {
                        int i3 = (int)(255.0f + 255.0f * (this.m.snap[0] / 100.0f));
                        if (i3 > 255) {
                            i3 = 255;
                        }
                        if (i3 < 0) {
                            i3 = 0;
                        }
                        i2 = (i2 * (140 - k) + i3 * (k - 90)) / 50;
                        if (i2 > 255) {
                            i2 = 255;
                        }
                    }
                }
                else if (this.flk) {
                    i2 = (int)(255.0f + 255.0f * (this.m.snap[0] / 100.0f));
                    if (i2 > 255) {
                        i2 = 255;
                    }
                    if (i2 < 0) {
                        i2 = 0;
                    }
                    this.flk = false;
                }
                else {
                    i2 = (int)(255.0f + 255.0f * (this.m.snap[0] / 100.0f));
                    if (i2 > 255) {
                        i2 = 255;
                    }
                    if (i2 < 0) {
                        i2 = 0;
                    }
                    l3 = (int)(220.0f + 220.0f * (this.m.snap[1] / 100.0f));
                    if (l3 > 255) {
                        l3 = 255;
                    }
                    if (l3 < 0) {
                        l3 = 0;
                    }
                    this.flk = true;
                }
                this.rd.setColor(new Color(i2, l3, j2));
                this.rd.fillPolygon(ai, ai2, 7);
                i2 = (int)(115.0f + 115.0f * (this.m.snap[0] / 100.0f));
                if (i2 > 255) {
                    i2 = 255;
                }
                if (i2 < 0) {
                    i2 = 0;
                }
                l3 = (int)(170.0f + 170.0f * (this.m.snap[1] / 100.0f));
                if (l3 > 255) {
                    l3 = 255;
                }
                if (l3 < 0) {
                    l3 = 0;
                }
                j2 = 0;
                if (j <= 0) {
                    if (k <= 45 && j != -2 && this.cntan == 0) {
                        i2 = (i2 * k + this.m.csky[0] * (45 - k)) / 45;
                        l3 = (l3 * k + this.m.csky[1] * (45 - k)) / 45;
                        j2 = (j2 * k + this.m.csky[2] * (45 - k)) / 45;
                    }
                }
                else if (this.flk) {
                    i2 = (int)(255.0f + 255.0f * (this.m.snap[0] / 100.0f));
                    if (i2 > 255) {
                        i2 = 255;
                    }
                    if (i2 < 0) {
                        i2 = 0;
                    }
                    l3 = 0;
                }
                this.rd.setColor(new Color(i2, l3, j2));
                this.rd.drawPolygon(ai, ai2, 7);
            }
        }
        else if (!noopp) {
            int j3 = 0;
            do {
                ai[j3] = this.xs(ai[j3], ai3[j3]);
                ai2[j3] = this.ys(ai2[j3], ai3[j3]);
            } while (++j3 < 7);
            j3 = (int)(159.0f + 159.0f * (this.m.snap[0] / 100.0f));
            if (j3 > 255) {
                j3 = 255;
            }
            if (j3 < 0) {
                j3 = 0;
            }
            int i4 = (int)(207.0f + 207.0f * (this.m.snap[1] / 100.0f));
            if (i4 > 255) {
                i4 = 255;
            }
            if (i4 < 0) {
                i4 = 0;
            }
            int k3 = (int)(255.0f + 255.0f * (this.m.snap[2] / 100.0f));
            if (k3 > 255) {
                k3 = 255;
            }
            if (k3 < 0) {
                k3 = 0;
            }
            this.rd.setColor(new Color(j3, i4, k3));
            this.rd.fillPolygon(ai, ai2, 7);
            j3 = (int)(120.0f + 120.0f * (this.m.snap[0] / 100.0f));
            if (j3 > 255) {
                j3 = 255;
            }
            if (j3 < 0) {
                j3 = 0;
            }
            i4 = (int)(114.0f + 114.0f * (this.m.snap[1] / 100.0f));
            if (i4 > 255) {
                i4 = 255;
            }
            if (i4 < 0) {
                i4 = 0;
            }
            k3 = (int)(255.0f + 255.0f * (this.m.snap[2] / 100.0f));
            if (k3 > 255) {
                k3 = 255;
            }
            if (k3 < 0) {
                k3 = 0;
            }
            this.rd.setColor(new Color(j3, i4, k3));
            this.rd.drawPolygon(ai, ai2, 7);
        }
    }
    
    public void levelhigh(final int i, final int j, final int k, final int l, final int i1) {
        this.rd.drawImage(this.gameh, 336, 20, null);
        byte byte0 = 16;
        char c = '0';
        char c2 = '`';
        if (l < 50) {
            if (this.aflk) {
                byte0 = 106;
                c = '°';
                c2 = '\u00ff';
                this.aflk = false;
            }
            else {
                this.aflk = true;
            }
        }
        if (i != 0) {
            if (k == 0) {
                this.drawcs(60, "You Wasted 'em!", byte0, c, c2, 0);
            }
            else if (k == 1) {
                this.drawcs(60, "Close Finish!", byte0, c, c2, 0);
            }
            else {
                this.drawcs(60, "Close Finish!  Almost got it!", byte0, c, c2, 0);
            }
        }
        else if (j == 229) {
            this.drawcs(60, "Wasted!", byte0, c, c2, 0);
        }
        else if (i1 > 2) {
            this.drawcs(60, "Stunts!", byte0, c, c2, 0);
        }
        else {
            this.drawcs(60, "Best Stunt!", byte0, c, c2, 0);
        }
        this.drawcs(460, "Press  [ Enter ]  to continue", 0, 0, 0, 0);
    }
    
    public void playsounds(final Madness madness, final Control control, final int i) {
        if (this.fase == 0 && this.starcnt < 35 && this.cntwis != 8 && !this.mutes) {
            boolean flag = (control.up && madness.speed > 0.0f) || (control.down && madness.speed < 10.0f);
            boolean flag2 = (madness.skid == 1 && control.handb) || Math.abs(madness.scz[0] - (madness.scz[1] + madness.scz[0] + madness.scz[2] + madness.scz[3]) / 4.0f) > 1.0f || Math.abs(madness.scx[0] - (madness.scx[1] + madness.scx[0] + madness.scx[2] + madness.scx[3]) / 4.0f) > 1.0f;
            boolean flag3 = false;
            if (control.up && madness.speed < 10.0f) {
                flag2 = true;
                flag = true;
                flag3 = true;
            }
            if (flag && madness.mtouch) {
                if (!madness.capsized) {
                    if (!flag2) {
                        if (madness.power != 98.0f) {
                            if (Math.abs(madness.speed) > 0.0f && Math.abs(madness.speed) <= madness.swits[madness.cn][0]) {
                                int j = (int)(3.0f * Math.abs(madness.speed) / madness.swits[madness.cn][0]);
                                if (j == 2) {
                                    if (this.pwait == 0) {
                                        j = 0;
                                    }
                                    else {
                                        --this.pwait;
                                    }
                                }
                                else {
                                    this.pwait = 7;
                                }
                                this.sparkeng(j);
                            }
                            if (Math.abs(madness.speed) > madness.swits[madness.cn][0] && Math.abs(madness.speed) <= madness.swits[madness.cn][1]) {
                                int k = (int)(3.0f * (Math.abs(madness.speed) - madness.swits[madness.cn][0]) / (madness.swits[madness.cn][1] - madness.swits[madness.cn][0]));
                                if (k == 2) {
                                    if (this.pwait == 0) {
                                        k = 0;
                                    }
                                    else {
                                        --this.pwait;
                                    }
                                }
                                else {
                                    this.pwait = 7;
                                }
                                this.sparkeng(k);
                            }
                            if (Math.abs(madness.speed) > madness.swits[madness.cn][1] && Math.abs(madness.speed) <= madness.swits[madness.cn][2]) {
                                final int l = (int)(3.0f * (Math.abs(madness.speed) - madness.swits[madness.cn][1]) / (madness.swits[madness.cn][2] - madness.swits[madness.cn][1]));
                                this.sparkeng(l);
                            }
                        }
                        else {
                            byte byte0 = 2;
                            if (this.pwait == 0) {
                                if (Math.abs(madness.speed) > madness.swits[madness.cn][1]) {
                                    byte0 = 3;
                                }
                            }
                            else {
                                --this.pwait;
                            }
                            this.sparkeng(byte0);
                        }
                    }
                    else {
                        this.sparkeng(-1);
                        if (flag3) {
                            if (this.stopcnt <= 0) {
                                this.air[5].loop();
                                this.stopcnt = 10;
                            }
                        }
                        else if (this.stopcnt <= -2) {
                            this.air[2 + (int)(this.m.random() * 3.0f)].loop();
                            this.stopcnt = 7;
                        }
                    }
                }
                else {
                    this.sparkeng(3);
                }
                this.grrd = false;
                this.aird = false;
            }
            else {
                this.pwait = 15;
                if (!madness.mtouch && !this.grrd && this.m.random() > 0.4) {
                    this.air[(int)(this.m.random() * 4.0f)].loop();
                    this.stopcnt = 5;
                    this.grrd = true;
                }
                if (!madness.wtouch && !this.aird) {
                    this.stopairs();
                    this.air[(int)(this.m.random() * 4.0f)].loop();
                    this.stopcnt = 10;
                    this.aird = true;
                }
                this.sparkeng(-1);
            }
            if (madness.cntdest != 0 && this.cntwis < 7) {
                if (!this.pwastd) {
                    this.wastd.loop();
                    this.pwastd = true;
                }
            }
            else {
                if (this.pwastd) {
                    this.wastd.stop();
                    this.pwastd = false;
                }
                if (this.cntwis == 7 && !this.mutes) {
                    this.firewasted.play();
                }
            }
        }
        else {
            this.sparkeng(-2);
            if (this.pwastd) {
                this.wastd.stop();
                this.pwastd = false;
            }
        }
        if (this.stopcnt != -20) {
            if (this.stopcnt == 1) {
                this.stopairs();
            }
            --this.stopcnt;
        }
        if (this.bfcrash != 0) {
            --this.bfcrash;
        }
        if (this.bfskid != 0) {
            --this.bfskid;
        }
        if (madness.newcar) {
            this.cntwis = 0;
        }
        if (this.fase == 0 || this.fase == 6 || this.fase == -1 || this.fase == -2 || this.fase == -3 || this.fase == -4 || this.fase == -5) {
            if (this.mutes != control.mutes) {
                this.mutes = control.mutes;
            }
            if (control.mutem != this.mutem) {
                this.mutem = control.mutem;
                if (this.mutem) {
                    if (this.loadedt[this.lastload]) {
                        if (this.isMidi[this.lastload]) {
                            if (!this.stopped) {
                                this.mtracks[this.lastload].setPaused(true);
                                this.resumed = false;
                                this.stopped = true;
                            }
                        }
                        else {
                            this.stracks[this.lastload].stop();
                        }
                    }
                }
                else if (this.loadedt[this.lastload]) {
                    if (this.isMidi[this.lastload]) {
                        if (!this.resumed) {
                            this.mtracks[this.lastload].setPaused(false);
                            this.resumed = true;
                            this.stopped = false;
                        }
                    }
                    else {
                        this.stracks[this.lastload].resume();
                    }
                }
            }
        }
        if (madness.cntdest != 0 && this.cntwis < 7) {
            if (madness.dest) {
                ++this.cntwis;
            }
        }
        else {
            if (madness.cntdest == 0) {
                this.cntwis = 0;
            }
            if (this.cntwis == 7) {
                this.cntwis = 8;
            }
        }
    }
    
    public void crash(final float f, final int i) {
        if (this.bfcrash == 0) {
            if (i == 0) {
                if (Math.abs(f) > 25.0f && Math.abs(f) < 170.0f) {
                    if (!this.mutes) {
                        this.lowcrash[this.crshturn].play();
                    }
                    this.bfcrash = 2;
                }
                if (Math.abs(f) >= 170.0f) {
                    if (!this.mutes) {
                        this.crash[this.crshturn].play();
                    }
                    this.bfcrash = 2;
                }
                if (Math.abs(f) > 25.0f) {
                    if (this.crashup) {
                        --this.crshturn;
                    }
                    else {
                        ++this.crshturn;
                    }
                    if (this.crshturn == -1) {
                        this.crshturn = 2;
                    }
                    if (this.crshturn == 3) {
                        this.crshturn = 0;
                    }
                }
            }
            if (i == -1) {
                if (Math.abs(f) > 25.0f && Math.abs(f) < 170.0f) {
                    if (!this.mutes) {
                        this.lowcrash[2].play();
                    }
                    this.bfcrash = 2;
                }
                if (Math.abs(f) > 170.0f) {
                    if (!this.mutes) {
                        this.crash[2].play();
                    }
                    this.bfcrash = 2;
                }
            }
            if (i == 1) {
                if (!this.mutes) {
                    this.tires.play();
                }
                this.bfcrash = 3;
            }
        }
    }
    
    public int ys(final int i, final int j) {
        return (j - this.m.focus_point) * (this.m.cy - i) / j + i;
    }
    
    public void replyn() {
        if (this.aflk) {
            this.drawcs(30, "Replay  > ", 0, 0, 0, 0);
            this.aflk = false;
        }
        else {
            this.drawcs(30, "Replay  >>", 0, 128, 255, 0);
            this.aflk = true;
        }
    }
    
    private Image pressed(final Image image) {
        final int i = image.getHeight(this.ob);
        final int j = image.getWidth(this.ob);
        final int[] ai = new int[j * i];
        final PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, j, i, ai, 0, j);
        try {
            pixelgrabber.grabPixels();
        }
        catch (final InterruptedException ex) {}
        for (int k = 0; k < j * i; ++k) {
            if (ai[k] != ai[j * i - 1]) {
                ai[k] = -16777216;
            }
        }
        final Image image2 = this.createImage(new MemoryImageSource(j, i, ai, 0, j));
        return image2;
    }
    
    private Image dodgen(final Image image) {
        final int i = image.getHeight(this.ob);
        final int j = image.getWidth(this.ob);
        final int[] ai = new int[j * i];
        final PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, j, i, ai, 0, j);
        try {
            pixelgrabber.grabPixels();
        }
        catch (final InterruptedException ex) {}
        for (int k = 0; k < j * i; ++k) {
            final Color color = new Color(ai[k]);
            int l = color.getRed() * 3 + 90;
            if (l > 255) {
                l = 255;
            }
            if (l < 0) {
                l = 0;
            }
            int i2 = color.getGreen() * 3 + 90;
            if (i2 > 255) {
                i2 = 255;
            }
            if (i2 < 0) {
                i2 = 0;
            }
            int j2 = color.getBlue() * 3 + 90;
            if (j2 > 255) {
                j2 = 255;
            }
            if (j2 < 0) {
                j2 = 0;
            }
            final Color color2 = new Color(l, i2, j2);
            ai[k] = color2.getRGB();
        }
        final Image image2 = this.createImage(new MemoryImageSource(j, i, ai, 0, j));
        return image2;
    }
    
    private void smokeypix(final byte[] abyte0, final MediaTracker mediatracker, final Toolkit toolkit) {
        final Image image = toolkit.createImage(abyte0);
        mediatracker.addImage(image, 0);
        try {
            mediatracker.waitForID(0);
        }
        catch (final Exception ex) {}
        final PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, 616, 262, this.smokey, 0, 616);
        try {
            pixelgrabber.grabPixels();
        }
        catch (final InterruptedException ex2) {}
    }
    
    public void stoploading() {
        this.loading();
        this.app.repaint();
        this.runner.stop();
        this.runner = null;
        this.runtyp = 0;
    }
    
    public void nofocus() {
        this.rd.setColor(new Color(255, 255, 255));
        this.rd.fillRect(0, 0, 870, 20);
        this.rd.fillRect(0, 0, 20, 480);
        this.rd.fillRect(0, 460, 870, 20);
        this.rd.fillRect(850, 0, 20, 480);
        this.rd.setColor(new Color(192, 192, 192));
        this.rd.drawRect(20, 20, 830, 440);
        this.rd.setColor(new Color(0, 0, 0));
        this.rd.drawRect(22, 22, 826, 436);
        this.rd.setFont(new Font("SansSerif", 1, 11));
        this.ftm = this.rd.getFontMetrics();
        this.drawcs(14, "Game lost its focus.   Click screen with mouse to continue.", 100, 100, 100, 3);
        this.drawcs(475, "Game lost its focus.   Click screen with mouse to continue.", 100, 100, 100, 3);
    }
    
    public void rot(final int[] paramArrayOfInt1, final int[] paramArrayOfInt2, final int paramInt1, final int paramInt2, final int paramInt3, final int paramInt4) {
        if (paramInt3 != 0) {
            for (int i = 0; i < paramInt4; ++i) {
                final int j = paramArrayOfInt1[i];
                final int k = paramArrayOfInt2[i];
                paramArrayOfInt1[i] = paramInt1 + (int)((j - paramInt1) * this.m.cos(paramInt3) - (k - paramInt2) * this.m.sin(paramInt3));
                paramArrayOfInt2[i] = paramInt2 + (int)((j - paramInt1) * this.m.sin(paramInt3) + (k - paramInt2) * this.m.cos(paramInt3));
            }
        }
    }
    
    public boolean overon(final int i, final int j, final int k, final int l, final int i1, final int j1) {
        return i1 > i && i1 < i + k && j1 > j && j1 < j + l;
    }
    
    public void pauseimage(final Image image) {
        this.flexpix = new int[600000];
        final PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, 870, 480, this.flexpix, 0, 870);
        try {
            pixelgrabber.grabPixels();
        }
        catch (final InterruptedException ex) {}
        int i = 0;
        int j = 0;
        int k = 0;
        int l = 0;
        int i2 = 0;
        do {
            final Color color = new Color(this.flexpix[i2]);
            int j2 = 0;
            if (l == 0) {
                j2 = (k = (color.getRed() + color.getGreen() + color.getBlue()) / 3);
            }
            else {
                j2 = (k = (color.getRed() + color.getGreen() + color.getBlue() + k * 30) / 33);
            }
            if (++l == 870) {
                l = 0;
            }
            if (i2 > 870 * (8 + j) + 316 && j < 188) {
                final int k2 = (j2 + 60) / 3;
                final int l2 = (j2 + 135) / 3;
                final int i3 = (j2 + 220) / 3;
                if (++i == 237) {
                    ++j;
                    i = 0;
                }
                final Color color2 = new Color(k2, l2, i3);
                this.flexpix[i2] = color2.getRGB();
            }
            else {
                final Color color3 = new Color(j2, j2, j2);
                this.flexpix[i2] = color3.getRGB();
            }
        } while (++i2 < 600000);
        this.fleximg = this.createImage(new MemoryImageSource(870, 480, this.flexpix, 0, 870));
        this.rd.drawImage(this.fleximg, 0, 0, null);
        this.m.flex = 0;
    }
    
    public void loadmusic(final int i, final int j, final CheckPoints checkpoints) {
        this.hipnoload(checkpoints.stage, false);
        this.app.setCursor(new Cursor(3));
        this.app.repaint();
        boolean flag = false;
        int mxstage = this.unlocked[0];
        if (this.careermode) {
            mxstage = this.unlocked[1];
        }
        if (i == mxstage) {
            flag = true;
        }
        if (flag) {
            this.runtyp = i;
            (this.runner = new Thread(this)).start();
        }
        if (!this.classicmode && !this.careermode && !this.loadedt[i - 1]) {
            final File f = new File("data/Files/music/stage" + i + ".mid");
            final File f2 = new File("data/Files/music/stage" + i + ".mp3");
            final File f3 = new File("data/Files/music/stage" + i + ".ogg");
            if (f.exists()) {
                this.isMidi[i - 1] = true;
                (this.mtracks[i - 1] = new RadicalMidi("data/Files/music/stage" + i + ".mid")).load();
                this.loadedt[i - 1] = true;
            }
            else if (f2.exists()) {
                this.isMidi[i - 1] = true;
                (this.mtracks[i - 1] = new RadicalMidi("data/Files/music/stage" + i + ".mp3")).load();
                this.loadedt[i - 1] = true;
            }
            else if (f3.exists()) {
                this.isMidi[i - 1] = true;
                this.mtracks[i - 1] = new RadicalMidi("data/Files/music/stage" + i + ".ogg");
                this.loadedt[i - 1] = true;
            }
            else {
                this.isMidi[i - 1] = false;
                this.stracks[i - 1] = new RadicalMod("data/Files/music/stage" + i + ".radq", this.app);
                if (this.stracks[i - 1].loaded == 1) {
                    this.loadedt[i - 1] = true;
                }
            }
        }
        if (this.careermode) {
            if (!this.bonstage) {
                if (!this.loadedt[i + 27]) {
                    final File f = new File("data/Files/careermusic/stage" + i + ".mid");
                    final File f2 = new File("data/Files/careermusic/stage" + i + ".mp3");
                    final File f3 = new File("data/Files/careermusic/stage" + i + "a.ogg");
                    final File f4 = new File("data/Files/careermusic/stage" + i + "b.ogg");
                    final File f5 = new File("data/Files/careermusic/bossbattlea.ogg");
                    final File f6 = new File("data/Files/careermusic/bossbattleb.ogg");
                    if (f.exists()) {
                        this.isMidi[i + 27] = true;
                        (this.mtracks[i + 27] = new RadicalMidi("data/Files/careermusic/stage" + i + ".mid")).load();
                        this.loadedt[i + 27] = true;
                    }
                    else if (f2.exists()) {
                        this.isMidi[i + 27] = true;
                        (this.mtracks[i + 27] = new RadicalMidi("data/Files/careermusic/stage" + i + ".mp3")).load();
                        this.loadedt[i + 27] = true;
                    }
                    else if (f3.exists() && f4.exists()) {
                        if (i > 15) {
                            this.isMidi[i + 27] = true;
                            this.mtracks[i + 27] = new RadicalMidi("data/Files/careermusic/stage" + i + "a.ogg");
                            this.loadedt[i + 27] = true;
                            this.isOgg[i + 27] = true;
                            this.isMidi[i + 63] = true;
                            this.mtracks[i + 63] = new RadicalMidi("data/Files/careermusic/stage" + i + "b.ogg");
                            this.loadedt[i + 63] = true;
                            this.isOgg[i + 63] = true;
                        }
                        else {
                            this.isMidi[i + 99] = true;
                            this.mtracks[i + 99] = new RadicalMidi("data/Files/careermusic/stage" + i + "a.ogg");
                            this.loadedt[i + 99] = true;
                            this.isOgg[i + 99] = true;
                            this.isMidi[i + 100] = true;
                            this.mtracks[i + 100] = new RadicalMidi("data/Files/careermusic/stage" + i + "b.ogg");
                            this.loadedt[i + 100] = true;
                            this.isOgg[i + 100] = true;
                        }
                        if (i == 23 && f5.exists() && f6.exists()) {
                            this.isMidi[77] = true;
                            this.mtracks[77] = new RadicalMidi("data/Files/careermusic/bossbattlea.ogg");
                            this.loadedt[77] = true;
                            this.isOgg[77] = true;
                            this.isMidi[78] = true;
                            this.mtracks[78] = new RadicalMidi("data/Files/careermusic/bossbattleb.ogg");
                            this.loadedt[78] = true;
                            this.isOgg[78] = true;
                        }
                    }
                    else {
                        this.isMidi[i + 27] = false;
                        this.stracks[i + 27] = new RadicalMod("data/Files/careermusic/stage" + i + ".radq", this.app);
                        if (this.stracks[i + 27].loaded == 1) {
                            this.loadedt[i + 27] = true;
                        }
                    }
                }
            }
            else {
                if (this.bonusstage[0] && !this.loadedt[74]) {
                    this.stracks[74] = new RadicalMod("data/Files/bonusmusic/b1.radq", this.app);
                    if (this.stracks[74].loaded == 1) {
                        this.loadedt[74] = true;
                    }
                }
                if (this.bonusstage[1] && !this.loadedt[75]) {
                    this.stracks[75] = new RadicalMod("data/Files/bonusmusic/b2.radq", this.app);
                    if (this.stracks[75].loaded == 1) {
                        this.loadedt[75] = true;
                    }
                }
                if (this.bonusstage[2] && !this.loadedt[76]) {
                    this.stracks[76] = new RadicalMod("data/Files/bonusmusic/b3.radq", this.app);
                    if (this.stracks[76].loaded == 1) {
                        this.loadedt[76] = true;
                    }
                }
                if (this.bonusstage[3] && (!this.loadedt[94] || !this.loadedt[95])) {
                    this.isMidi[94] = true;
                    this.mtracks[94] = new RadicalMidi("data/Files/bonusmusic/b4a.ogg");
                    this.loadedt[94] = true;
                    this.isOgg[94] = true;
                    this.isMidi[95] = true;
                    this.mtracks[95] = new RadicalMidi("data/Files/bonusmusic/b4b.ogg");
                    this.loadedt[95] = true;
                    this.isOgg[95] = true;
                }
            }
        }
        if (this.classicmode && !this.loadedt[i + 55]) {
            final File f = new File("data/Files/classicmusic/stage" + i + ".mid");
            final File f2 = new File("data/Files/classicmusic/stage" + i + ".mp3");
            final File f3 = new File("data/Files/classicmusic/stage" + i + ".ogg");
            if (f.exists()) {
                this.isMidi[i + 55] = true;
                (this.mtracks[i + 55] = new RadicalMidi("data/Files/classicmusic/stage" + i + ".mid")).load();
                this.loadedt[i + 55] = true;
            }
            else if (f2.exists()) {
                this.isMidi[i + 55] = true;
                (this.mtracks[i + 55] = new RadicalMidi("data/Files/classicmusic/stage" + i + ".mp3")).load();
                this.loadedt[i + 55] = true;
            }
            else if (f3.exists()) {
                this.isMidi[i + 55] = true;
                this.mtracks[i + 55] = new RadicalMidi("data/Files/classicmusic/stage" + i + ".ogg");
                this.loadedt[i + 55] = true;
            }
            else {
                this.isMidi[i + 55] = false;
                this.stracks[i + 55] = new RadicalMod("data/Files/classicmusic/stage" + i + ".radq", this.app);
                if (this.stracks[i + 55].loaded == 1) {
                    this.loadedt[i + 55] = true;
                }
            }
        }
        if (!this.careermode && !this.classicmode && !this.isMidi[i - 1]) {
            if (i == 1) {
                this.stracks[0].loadMod(320, 8000, 125, this.sunny, this.macn);
            }
            if (i == 2) {
                this.stracks[1].loadMod(260, 7200, 125, this.sunny, this.macn);
            }
            if (i == 3) {
                this.stracks[2].loadMod(230, 8000, 125, this.sunny, this.macn);
            }
            if (i == 4) {
                this.stracks[3].loadMod(240, 8000, 125, this.sunny, this.macn);
            }
            if (i == 5) {
                this.stracks[4].loadMod(282, 7800, 125, this.sunny, this.macn);
            }
            if (i == 6) {
                this.stracks[5].loadMod(320, 7600, 125, this.sunny, this.macn);
            }
            if (i == 7) {
                this.stracks[6].loadMod(300, 7500, 125, this.sunny, this.macn);
            }
            if (i == 8) {
                this.stracks[7].loadMod(270, 7900, 125, this.sunny, this.macn);
            }
            if (i == 9) {
                this.stracks[8].loadMod(330, 7900, 125, this.sunny, this.macn);
            }
            if (i == 10) {
                this.stracks[9].loadMod(352, 7300, 125, this.sunny, this.macn);
            }
            if (i == 11) {
                this.stracks[10].loadMod(480, 7900, 125, this.sunny, this.macn);
            }
            if (i == 12) {
                this.stracks[11].loadMod(290, 7900, 125, this.sunny, this.macn);
            }
            if (i == 13) {
                this.stracks[12].loadMod(225, 7600, 137, this.sunny, this.macn);
            }
            if (i == 14) {
                this.stracks[13].loadMod(400, 8000, 125, this.sunny, this.macn);
            }
            if (i == 15) {
                this.stracks[14].loadMod(220, 8000, 125, this.sunny, this.macn);
            }
            if (i == 16) {
                this.stracks[15].loadMod(261, 8000, 125, this.sunny, this.macn);
            }
            if (i == 17) {
                this.stracks[16].loadMod(310, 7600, 125, this.sunny, this.macn);
            }
            if (i == 18) {
                this.stracks[17].loadMod(310, 7600, 125, this.sunny, this.macn);
            }
            if (i == 19) {
                this.stracks[18].loadMod(400, 7600, 125, this.sunny, this.macn);
            }
            if (i == 20) {
                this.stracks[19].loadMod(310, 7600, 125, this.sunny, this.macn);
            }
            if (i == 21) {
                this.stracks[20].loadMod(230, 7600, 125, this.sunny, this.macn);
            }
            if (i == 22) {
                this.stracks[21].loadMod(280, 8000, 125, this.sunny, this.macn);
            }
            if (i == 23) {
                this.stracks[22].loadMod(375, 7600, 125, this.sunny, this.macn);
            }
            if (i == 24) {
                this.stracks[23].loadMod(310, 7600, 125, this.sunny, this.macn);
            }
            if (i == 25) {
                this.stracks[24].loadMod(300, 7600, 125, this.sunny, this.macn);
            }
            if (i == 26) {
                this.stracks[25].loadMod(300, 7600, 125, this.sunny, this.macn);
            }
            if (i == 27) {
                this.stracks[26].loadMod(305, 7600, 136, this.sunny, this.macn);
            }
            if (i == 28) {
                this.stracks[27].loadMod(250, 7600, 135, this.sunny, this.macn);
            }
        }
        if (this.careermode && !this.bonstage && ((!this.isMidi[i + 27] && i > 15) || (!this.isMidi[i + 99] && i <= 15))) {
            if (i == 1) {
                this.stracks[28].loadMod(320, 8000, 125, this.sunny, this.macn);
            }
            if (i == 3) {
                this.stracks[30].loadMod(230, 8000, 125, this.sunny, this.macn);
            }
            if (i == 4) {
                this.stracks[31].loadMod(240, 8000, 125, this.sunny, this.macn);
            }
            if (i == 5) {
                this.stracks[32].loadMod(282, 7800, 125, this.sunny, this.macn);
            }
            if (i == 6) {
                this.stracks[33].loadMod(280, 7600, 125, this.sunny, this.macn);
            }
            if (i == 7) {
                this.stracks[34].loadMod(300, 7500, 125, this.sunny, this.macn);
            }
            if (i == 9) {
                this.stracks[36].loadMod(330, 7900, 125, this.sunny, this.macn);
            }
            if (i == 10) {
                this.stracks[37].loadMod(352, 7300, 125, this.sunny, this.macn);
            }
            if (i == 11) {
                this.stracks[38].loadMod(320, 7900, 125, this.sunny, this.macn);
            }
            if (i == 12) {
                this.stracks[39].loadMod(290, 7900, 125, this.sunny, this.macn);
            }
            if (i == 13) {
                this.stracks[40].loadMod(225, 7600, 137, this.sunny, this.macn);
            }
            if (i == 14) {
                this.stracks[41].loadMod(400, 8000, 125, this.sunny, this.macn);
            }
            if (i == 25) {
                this.stracks[52].loadMod(300, 7600, 125, this.sunny, this.macn);
            }
            if (i == 26) {
                this.stracks[53].loadMod(300, 7600, 125, this.sunny, this.macn);
            }
            if (i == 27) {
                this.stracks[54].loadMod(305, 7600, 136, this.sunny, this.macn);
            }
            if (i == 28) {
                this.stracks[55].loadMod(250, 7600, 135, this.sunny, this.macn);
            }
        }
        if (this.classicmode && !this.isMidi[i + 55]) {
            if (i == 1) {
                this.stracks[56].loadMod(130, 8000, 125, this.sunny, this.macn);
            }
            if (i == 2) {
                this.stracks[57].loadMod(260, 7200, 125, this.sunny, this.macn);
            }
            if (i == 3) {
                this.stracks[58].loadMod(270, 8000, 125, this.sunny, this.macn);
            }
            if (i == 4) {
                this.stracks[59].loadMod(190, 8000, 125, this.sunny, this.macn);
            }
            if (i == 5) {
                this.stracks[60].loadMod(162, 7800, 125, this.sunny, this.macn);
            }
            if (i == 6) {
                this.stracks[61].loadMod(220, 7600, 125, this.sunny, this.macn);
            }
            if (i == 7) {
                this.stracks[62].loadMod(300, 7500, 125, this.sunny, this.macn);
            }
            if (i == 8) {
                this.stracks[63].loadMod(200, 7900, 125, this.sunny, this.macn);
            }
            if (i == 9) {
                this.stracks[64].loadMod(200, 7900, 125, this.sunny, this.macn);
            }
            if (i == 10) {
                this.stracks[65].loadMod(232, 7300, 125, this.sunny, this.macn);
            }
            if (i == 11) {
                this.stracks[66].loadMod(370, 7900, 125, this.sunny, this.macn);
            }
            if (i == 12) {
                this.stracks[67].loadMod(290, 7900, 125, this.sunny, this.macn);
            }
            if (i == 13) {
                this.stracks[68].loadMod(222, 7600, 125, this.sunny, this.macn);
            }
            if (i == 14) {
                this.stracks[69].loadMod(230, 8000, 125, this.sunny, this.macn);
            }
            if (i == 15) {
                this.stracks[70].loadMod(220, 8000, 125, this.sunny, this.macn);
            }
            if (i == 16) {
                this.stracks[71].loadMod(261, 8000, 125, this.sunny, this.macn);
            }
            if (i == 17) {
                this.stracks[72].loadMod(320, 7600, 125, this.sunny, this.macn);
            }
        }
        if (i == 61) {
            this.stracks[60].loadMod(162, 7800, 125, this.sunny, this.macn);
        }
        if (i == 74) {
            this.stracks[73].loadMod(262, 7800, 125, this.sunny, this.macn);
        }
        if (i == 67) {
            this.stracks[66].loadMod(220, 7600, 125, this.sunny, this.macn);
        }
        if (i == 73) {
            this.stracks[72].loadMod(320, 7600, 125, this.sunny, this.macn);
        }
        if (i == 35) {
            this.stracks[34].loadMod(300, 7500, 125, this.sunny, this.macn);
        }
        if (i == 75) {
            this.stracks[74].loadMod(250, 8500, 145, this.sunny, this.macn);
        }
        if (i == 76) {
            this.stracks[75].loadMod(190, 8500, 200, this.sunny, this.macn);
        }
        if (i == 77) {
            this.stracks[76].loadMod(300, 8500, 220, this.sunny, this.macn);
        }
        if (flag) {
            this.runner.stop();
            this.runner = null;
            this.runtyp = 0;
        }
        System.gc();
        if (!this.classicmode && !this.careermode) {
            this.lastload = i - 1;
        }
        if (this.careermode) {
            if (!this.bonstage) {
                if (i > 15) {
                    this.lastload = i + 27;
                }
                else {
                    this.lastload = i + 99;
                }
            }
            else {
                if (this.bonusstage[0]) {
                    this.lastload = 74;
                }
                if (this.bonusstage[1]) {
                    this.lastload = 75;
                }
                if (this.bonusstage[2]) {
                    this.lastload = 76;
                }
                if (this.bonusstage[3]) {
                    this.lastload = 94;
                }
            }
        }
        if (this.classicmode) {
            this.lastload = i + 55;
        }
        this.fase = 176;
        this.pcontin = 0;
        this.mutem = false;
        this.mutes = false;
    }
    
    public void loadimages() {
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final MediaTracker mediatracker = new MediaTracker(this.app);
        this.dnload += 12;
        try {
            final URL url = new URL(this.app.getCodeBase(), "data/images.radq");
            final DataInputStream datainputstream = new DataInputStream(url.openStream());
            final ZipInputStream zipinputstream = new ZipInputStream(datainputstream);
            for (ZipEntry zipentry = zipinputstream.getNextEntry(); zipentry != null; zipentry = zipinputstream.getNextEntry()) {
                int i = (int)zipentry.getSize();
                final String s = zipentry.getName();
                final byte[] abyte0 = new byte[i];
                int j = 0;
                while (i > 0) {
                    final int k = zipinputstream.read(abyte0, j, i);
                    j += k;
                    i -= k;
                }
                if (s.equals("cars.gif")) {
                    this.carsbg = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("exp.gif")) {
                    this.expbar = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("smokey.gif")) {
                    this.smokeypix(abyte0, mediatracker, toolkit);
                }
                if (s.equals("normalmodescreen.png")) {
                    this.nmsc = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("ptbg.png")) {
                    this.ptbg = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("rpgmodescreen.png")) {
                    this.rpgsc = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("classicmodescreen.png")) {
                    this.cmsc = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("instructionsscreen.png")) {
                    this.instsc = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("creditsscreen.png")) {
                    this.csc = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("apexnova.gif")) {
                    this.apexnova = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("cleared.gif")) {
                    this.cleared = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("radicalracer.gif")) {
                    this.radicalracer = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("special.GIF")) {
                    this.ospecial = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("fosterc.gif")) {
                    this.fosterc = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("vitalogy.gif")) {
                    this.vitalogy = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("blizzardrush.gif")) {
                    this.blizzardrush = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("drdaler.gif")) {
                    this.drdaler = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("ladoderecho.gif")) {
                    this.lado = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("excalibur.gif")) {
                    this.excalibur = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("ultimato.gif")) {
                    this.ultimato = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("dascop.gif")) {
                    this.dascop = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("trelivision.gif")) {
                    this.trelivision = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("billy12345nfmm.gif")) {
                    this.billy = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("mazdarx.gif")) {
                    this.mazdarx = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("uvan.gif")) {
                    this.uvan = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("hyde233.gif")) {
                    this.hyde = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("djmiker.gif")) {
                    this.djmiker = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("pickup.gif")) {
                    this.pickup = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("audir8lms.gif")) {
                    this.audir8lms = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("mezzelo.gif")) {
                    this.mezzelo = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("bob.gif")) {
                    this.bob = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("gameh.gif")) {
                    this.ogameh = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("blackbut.gif")) {
                    this.emptyslot = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("elking.gif")) {
                    this.elking = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("donors.gif")) {
                    this.donors = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("creditsbg2.jpg")) {
                    this.credscentwo = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("gameov.gif")) {
                    this.gameov = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("savedata.gif")) {
                    this.savedata = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("lap.gif")) {
                    this.olap = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("paused.gif")) {
                    this.paused = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("interface.gif")) {
                    this.scoutint = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("gamelogo.gif")) {
                    this.gamelogo = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("creditsbg.jpg")) {
                    this.credscen = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("blackbutton.gif")) {
                    this.normalmode = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("blackbutton2.gif")) {
                    this.rpgmode = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("blackbutton3.gif")) {
                    this.practice = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("overall.gif")) {
                    this.overall = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("blackbutton4.gif")) {
                    this.timetrial = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("scoutbutton.gif")) {
                    this.scoutbut = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("select.gif")) {
                    this.select = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("yourwasted.gif")) {
                    this.oyourwasted = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("youwastedem.gif")) {
                    this.oyouwastedem = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("rusure.gif")) {
                    this.rusure = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("cleardata.gif")) {
                    this.cleardata = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("d1.gif")) {
                    this.dude[0] = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("d2.gif")) {
                    this.dude[1] = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("d3.gif")) {
                    this.dude[2] = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("float.gif")) {
                    this.oflaot = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("1c.gif")) {
                    this.ocntdn[1] = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("2c.gif")) {
                    this.ocntdn[2] = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("3c.gif")) {
                    this.ocntdn[3] = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("bgmain.jpg")) {
                    this.bgmain = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("warning.jpg")) {
                    this.warning = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("br.gif")) {
                    this.br = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("loadingmusic.gif")) {
                    this.oloadingmusic = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("radicalplay.gif")) {
                    this.radicalplay = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("back.gif")) {
                    this.back[0] = this.loadimage(abyte0, mediatracker, toolkit);
                    this.back[1] = this.bressed(this.back[0]);
                }
                if (s.equals("continue2.gif")) {
                    this.contin[0] = this.loadimage(abyte0, mediatracker, toolkit);
                    this.contin[1] = this.bressed(this.contin[0]);
                }
                if (s.equals("next.gif")) {
                    this.next[0] = this.loadimage(abyte0, mediatracker, toolkit);
                    this.next[1] = this.bressed(this.next[0]);
                }
                if (s.equals("pgate.gif")) {
                    this.pgate = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("rpro.gif")) {
                    this.rpro = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("selectcar.gif")) {
                    this.selectcar = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("track1.jpg")) {
                    this.trackbg[0][0] = this.loadimage(abyte0, mediatracker, toolkit);
                    this.trackbg[1][0] = this.dodgen(this.trackbg[0][0]);
                }
                if (s.equals("track2.jpg")) {
                    this.trackbg[0][1] = this.loadimage(abyte0, mediatracker, toolkit);
                    this.trackbg[1][1] = this.dodgen(this.trackbg[0][1]);
                }
                if (s.equals("youlost.gif")) {
                    this.oyoulost = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("youwon.gif")) {
                    this.oyouwon = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("0c.gif")) {
                    this.ocntdn[0] = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("damage.gif")) {
                    this.odmg = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("power.gif")) {
                    this.opwr = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("position.gif")) {
                    this.opos = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("wasted.gif")) {
                    this.owas = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("start1.gif")) {
                    this.ostar[0] = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("start2.gif")) {
                    this.ostar[1] = this.loadimage(abyte0, mediatracker, toolkit);
                    this.star[2] = this.pressed(this.ostar[1]);
                }
                if (s.equals("congrad.gif")) {
                    this.congrd = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("statb.gif")) {
                    this.statb = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("statb2.gif")) {
                    this.statb2 = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("statplus.gif")) {
                    this.statplus = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("statbo.gif")) {
                    this.statbo = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("statbo2.gif")) {
                    this.statbo2 = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("madness.gif")) {
                    this.mdness = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("fixhoop.gif")) {
                    this.fixhoop = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("arrow.gif")) {
                    this.sarrow = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("stunts.gif")) {
                    this.stunts = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("racing.gif")) {
                    this.racing = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("wasting.gif")) {
                    this.wasting = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("plus.gif")) {
                    this.plus = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("space.gif")) {
                    this.space = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("arrows.gif")) {
                    this.arrows = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("chil.gif")) {
                    this.chil = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("features.gif")) {
                    this.features = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("ory.gif")) {
                    this.ory = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("kz.gif")) {
                    this.kz = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("kx.gif")) {
                    this.kx = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("kv.gif")) {
                    this.kv = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("kp.gif")) {
                    this.kp = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("km.gif")) {
                    this.km = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("kn.gif")) {
                    this.kn = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("savecar.gif")) {
                    this.savecar2 = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("kenter.gif")) {
                    this.kenter = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("rpgmode.gif")) {
                    this.rpgpic = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("nfm.gif")) {
                    this.nfm = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("options.gif")) {
                    this.opti = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("opback.gif")) {
                    this.opback = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("logocars.gif")) {
                    this.logocars = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("byrd.gif")) {
                    this.byrd = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("nfmcoms.gif")) {
                    this.nfmcoms = this.loadimage(abyte0, mediatracker, toolkit);
                }
                if (s.equals("nfmcom.gif")) {
                    this.nfmcom = this.loadimage(abyte0, mediatracker, toolkit);
                }
                this.dnload += 3;
            }
            datainputstream.close();
            zipinputstream.close();
        }
        catch (final Exception exception) {
            System.out.println("Error Loading Images: " + exception);
        }
        System.gc();
    }
    
    public void displayinst() {
        if (this.fase != 10) {
            final Polygon bigbox = new Polygon();
            bigbox.addPoint(273, 150);
            bigbox.addPoint(280, 143);
            bigbox.addPoint(683, 143);
            bigbox.addPoint(690, 150);
            bigbox.addPoint(690, 453);
            bigbox.addPoint(683, 460);
            bigbox.addPoint(280, 460);
            bigbox.addPoint(273, 453);
            this.rd.setColor(new Color(0, 0, 0, 255));
            this.rd.fillPolygon(bigbox);
        }
        if (this.flipo == 0) {
            this.rd.setColor(new Color(255, 255, 255));
            this.rd.setFont(this.adventure.deriveFont(1, 22.0f));
            this.ftm = this.rd.getFontMetrics();
            this.rd.drawString("controls:", 481 - this.ftm.stringWidth("controls:") / 2, 168);
            this.rd.drawImage(this.arrows, 290, 250, null);
            this.rd.drawImage(this.space, 290, 335, null);
            this.rd.drawImage(this.kn, 290, 385, null);
            this.rd.setFont(this.adventure.deriveFont(1, 14.5f));
            this.ftm = this.rd.getFontMetrics();
            this.rd.drawString("you should already be familiar with the", 290, 200);
            this.rd.drawString("game's controls, but just in case...", 290, 223);
            this.rd.drawString("Moves the car as you'd expect.", 390, 285);
            this.rd.drawString("braking/stunting.", 516, 357);
            this.rd.drawString("mutes in-game sounds.", 330, 407);
            this.rd.drawImage(this.next[this.pnext], 590, 422, null);
        }
        if (this.flipo == 1) {
            this.rd.drawImage(this.kz, 290, 180, null);
            this.rd.drawImage(this.kx, 340, 180, null);
            this.rd.setColor(new Color(255, 255, 255));
            this.rd.setFont(this.adventure.deriveFont(1, 14.5f));
            this.ftm = this.rd.getFontMetrics();
            this.rd.drawString("used to look behind you.", 400, 202);
            this.rd.drawImage(this.km, 340, 240, null);
            this.rd.drawImage(this.kenter, 290, 300, null);
            this.rd.drawString("mutes the music track in-game.", 400, 272);
            this.rd.drawImage(this.next[this.pnext], 590, 422, null);
            this.rd.drawImage(this.back[this.pback], 319, 422, null);
            this.rd.drawString("pauses the game.", 430, 328);
            this.rd.drawString("pressing \"a\" changes the guidance arrow.", 290, 377);
            this.rd.drawString("pressing \"v\" changes the in-game view.", 290, 400);
        }
        if (this.flipo == 2) {
            this.rd.setColor(new Color(255, 255, 255));
            this.rd.setFont(this.adventure.deriveFont(1, 22.0f));
            this.ftm = this.rd.getFontMetrics();
            this.rd.drawString("new controls:", 481 - this.ftm.stringWidth("new controls:") / 2, 168);
            this.rd.drawString("special attacks:", 481 - this.ftm.stringWidth("special attacks:") / 2, 352);
            this.rd.drawImage(this.kp, 290, 190, null);
            this.rd.setFont(this.adventure.deriveFont(1, 14.5f));
            this.ftm = this.rd.getFontMetrics();
            this.rd.drawString("activates special attack.", 350, 212);
            this.rd.drawString("Pressing \"d\" displays the special bars", 290, 260);
            this.rd.drawString("of the opponents, rather than their damage", 290, 283);
            this.rd.drawString("bars, and vice versa.", 290, 306);
            this.rd.drawImage(this.special, 290, 374, null);
            this.rd.drawString("the special bars for all cars", 427, 384);
            this.rd.drawString("are charged by stunting.", 427, 406);
            this.rd.drawImage(this.next[this.pnext], 590, 422, null);
            this.rd.drawImage(this.back[this.pback], 319, 422, null);
        }
        if (this.flipo == 3) {
            this.rd.drawImage(this.special, 290, 178, null);
            int red = (int)(220.0f + 220.0f * (this.m.snap[0] / 100.0f));
            if (red > 255) {
                red = 255;
            }
            if (red < 0) {
                red = 0;
            }
            this.rd.setColor(new Color(red, 0, 0));
            this.rd.fillRect(294, 182, 98, 8);
            this.rd.drawImage(this.special, 290, 240, null);
            red = (int)(85.0f + 85.0f * (this.m.snap[0] / 100.0f));
            if (red > 255) {
                red = 255;
            }
            if (red < 0) {
                red = 0;
            }
            this.rd.setColor(new Color(red, 0, 0));
            this.rd.fillRect(294, 244, 70, 8);
            this.rd.setColor(new Color(255, 255, 255));
            this.rd.setFont(this.adventure.deriveFont(1, 14.5f));
            this.ftm = this.rd.getFontMetrics();
            this.rd.drawString("when your special bar is", 427, 180);
            this.rd.drawString("full, you can use your", 427, 202);
            this.rd.drawString("special attack.", 427, 224);
            this.rd.drawString("each car has a unique", 427, 250);
            this.rd.drawString("special attack.", 427, 272);
            this.rd.drawString("special attacks consist of a stat boost of", 290, 305);
            this.rd.drawString("some kind, and many also apply debuffs as", 290, 327);
            this.rd.drawString("well, usually to a random car.", 290, 349);
            this.rd.drawString("In a 1v1 situation, debuffs (such as having", 290, 385);
            this.rd.drawString("reduced speed) are removed for a fair fight.", 290, 407);
            this.rd.drawImage(this.next[this.pnext], 590, 422, null);
            this.rd.drawImage(this.back[this.pback], 319, 422, null);
        }
        if (this.flipo == 4) {
            this.rd.setColor(new Color(255, 255, 255));
            this.rd.setFont(this.adventure.deriveFont(1, 22.0f));
            this.ftm = this.rd.getFontMetrics();
            this.rd.drawString("how to win:", 481 - this.ftm.stringWidth("how to win:") / 2, 168);
            this.rd.setFont(this.adventure.deriveFont(1, 14.5f));
            this.ftm = this.rd.getFontMetrics();
            this.rd.drawString("as with the original need for madness", 290, 200);
            this.rd.drawString("games, you can win games by racing or", 290, 222);
            this.rd.drawString("wasting.", 290, 244);
            this.rd.drawString("power is as important as ever, and", 290, 280);
            this.rd.drawString("special attacks give the game an added", 290, 302);
            this.rd.drawString("dimension.", 290, 324);
            this.rd.drawString("on the whole, this game can get very", 290, 360);
            this.rd.drawString("hard. it was designed for veterans who had", 290, 382);
            this.rd.drawString("already beaten the original game.", 290, 404);
            this.rd.drawImage(this.next[this.pnext], 590, 422, null);
            this.rd.drawImage(this.back[this.pback], 319, 422, null);
        }
        if (this.flipo == 5) {
            this.rd.setColor(new Color(255, 255, 255));
            this.rd.setFont(this.adventure.deriveFont(1, 22.0f));
            this.ftm = this.rd.getFontMetrics();
            this.rd.drawString("classic mode:", 481 - this.ftm.stringWidth("classic mode:") / 2, 168);
            this.rd.setFont(this.adventure.deriveFont(1, 14.5f));
            this.ftm = this.rd.getFontMetrics();
            this.rd.drawString("classic mode uses the original stages, cars", 290, 200);
            this.rd.drawString("and music from the original Need for", 290, 222);
            this.rd.drawString("Madness 2.", 290, 244);
            this.rd.drawString("it is not very difficult and everything is", 290, 280);
            this.rd.drawString("already unlocked. It is useful to get used", 290, 302);
            this.rd.drawString("to the concept of special attacks.", 290, 324);
            this.rd.drawString("if you have not noticed yet, the gameplay", 290, 360);
            this.rd.drawString("in the background is taken from Classic", 290, 382);
            this.rd.drawString("Mode!", 290, 404);
            this.rd.drawImage(this.next[this.pnext], 590, 422, null);
            this.rd.drawImage(this.back[this.pback], 319, 422, null);
        }
        if (this.flipo == 6) {
            this.rd.drawImage(this.expbar, 290, 229, null);
            this.rd.setColor(new Color(230, 0, 0, 175));
            this.rd.fillRect(294, 232, 21, 16);
            this.rd.setFont(this.adventure.deriveFont(1, 13.5f));
            this.rd.setColor(new Color(0, 130, 0));
            this.rd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            this.rd.drawString("LEVEL 32", 333, 245);
            this.rd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
            this.rd.setColor(new Color(255, 255, 255));
            this.rd.setFont(this.adventure.deriveFont(1, 22.0f));
            this.ftm = this.rd.getFontMetrics();
            this.rd.drawString("rpg mode:", 481 - this.ftm.stringWidth("rpg mode:") / 2, 168);
            this.rd.setFont(this.adventure.deriveFont(1, 14.5f));
            this.ftm = this.rd.getFontMetrics();
            this.rd.drawString("This is the main game mode of this game.", 290, 200);
            this.rd.drawString("here, you can gain exp", 452, 239);
            this.rd.drawString("by general gameplay.", 452, 257);
            this.rd.drawString("every time you level up, you get 4 stat", 290, 290);
            this.rd.drawString("points to spend on any stat you wish.", 290, 312);
            this.rd.drawString("leveling up also increases each stat", 290, 334);
            this.rd.drawString("slightly as well.", 290, 356);
            this.rd.drawString("cars unlocked later on level more slowly", 290, 385);
            this.rd.drawString("compared to earlier cars.", 290, 407);
            this.rd.drawImage(this.next[this.pnext], 590, 422, null);
            this.rd.drawImage(this.back[this.pback], 319, 422, null);
        }
        if (this.flipo == 7) {
            this.rd.setColor(new Color(255, 255, 255));
            this.rd.setFont(this.adventure.deriveFont(1, 14.5f));
            this.ftm = this.rd.getFontMetrics();
            this.rd.drawString("every time you clear a checkpoint or", 290, 170);
            this.rd.drawString("waste a car, you have a small chance of", 290, 192);
            this.rd.drawString("receiving bonus stat points.", 290, 214);
            this.rd.drawString("at higher levels, you can get a lot of", 290, 250);
            this.rd.drawString("stat points this way!", 290, 272);
            this.rd.drawString("Starting from Stage 16, the opponent cars", 290, 307);
            this.rd.drawString("start getting bonus stat points as well.", 290, 329);
            this.rd.drawString("You should have more than them if you", 290, 364);
            this.rd.drawString("train your car from level 1 though.", 290, 386);
            this.rd.drawImage(this.next[this.pnext], 590, 422, null);
            this.rd.drawImage(this.back[this.pback], 319, 422, null);
        }
        if (this.flipo == 8) {
            this.rd.setColor(new Color(255, 255, 255));
            this.rd.setFont(this.adventure.deriveFont(1, 14.5f));
            this.ftm = this.rd.getFontMetrics();
            this.rd.drawString("starting from stage 4, you will also start", 290, 170);
            this.rd.drawString("to encounter beast opponents.", 290, 192);
            this.rd.drawString("these are larger, more powerful cars", 290, 227);
            this.rd.drawString("that have more stat points than usual.", 290, 249);
            this.rd.drawString("they do not get any bonus stat points.", 290, 271);
            this.rd.drawString("starting from stage 15, you will also", 290, 320);
            this.rd.drawString("start to encounter shadow cars. these are", 290, 342);
            this.rd.drawString("like normal cars, except that they are", 290, 364);
            this.rd.drawString("faster, more agile and have high defences.", 290, 386);
            this.rd.drawImage(this.next[this.pnext], 590, 422, null);
            this.rd.drawImage(this.back[this.pback], 319, 422, null);
        }
        if (this.flipo == 9) {
            this.rd.setColor(new Color(255, 255, 255));
            this.rd.setFont(this.adventure.deriveFont(1, 14.5f));
            this.ftm = this.rd.getFontMetrics();
            this.rd.drawString("those were just the basics - there is a", 290, 170);
            this.rd.drawString("large amount of depth to rpg mode.", 290, 192);
            this.rd.drawString("but the best way to learn about it is by", 290, 227);
            this.rd.drawString("playing through it. you will be able to see", 290, 249);
            this.rd.drawString("some tips before a stage begins which helps", 290, 271);
            this.rd.drawString("explain rpg mode mechanics as well.", 290, 293);
            this.rd.setFont(this.adventure.deriveFont(1, 22.0f));
            this.ftm = this.rd.getFontMetrics();
            this.rd.drawString("have fun playing!", 481 - this.ftm.stringWidth("have fun playing!") / 2, 345);
            this.rd.drawImage(this.back[this.pback], 319, 422, null);
            this.rd.drawImage(this.contin[this.pcontin], 437, 420, null);
        }
        this.rd.setFont(new Font("Arial", 1, 11));
        this.ftm = this.rd.getFontMetrics();
    }
    
    public void inststuff(final Control control) {
        if (control.left) {
            if (this.flipo > 0) {
                --this.flipo;
            }
            control.left = false;
        }
        if (control.right) {
            if (this.flipo < 9) {
                ++this.flipo;
            }
            control.right = false;
        }
        if (control.enter) {
            if (this.flipo == 9) {
                this.triggerinst = false;
            }
            control.enter = false;
        }
    }
    
    public void pausedgame(final int i, final Control control, final Record record, final int stage) {
        this.rd.drawImage(this.fleximg, 0, 0, null);
        if (control.up && !this.triggerinst) {
            final int[] opselect = this.opselect;
            final int n = 0;
            --opselect[n];
            if (this.opselect[0] == -1) {
                this.opselect[0] = 3;
            }
            control.up = false;
        }
        if (control.down && !this.triggerinst) {
            final int[] opselect2 = this.opselect;
            final int n2 = 0;
            ++opselect2[n2];
            if (this.opselect[0] == 4) {
                this.opselect[0] = 0;
            }
            control.down = false;
        }
        if (this.opselect[0] == 0) {
            this.rd.setColor(new Color(64, 143, 223));
            this.rd.fillRoundRect(364, 45, 137, 22, 7, 20);
            if (this.shaded) {
                this.rd.setColor(new Color(225, 200, 255));
            }
            else {
                this.rd.setColor(new Color(0, 89, 223));
            }
            this.rd.drawRoundRect(364, 45, 137, 22, 7, 20);
        }
        if (this.opselect[0] == 1) {
            this.rd.setColor(new Color(64, 143, 223));
            this.rd.fillRoundRect(355, 73, 155, 22, 7, 20);
            if (this.shaded) {
                this.rd.setColor(new Color(225, 200, 255));
            }
            else {
                this.rd.setColor(new Color(0, 89, 223));
            }
            this.rd.drawRoundRect(355, 73, 155, 22, 7, 20);
        }
        if (this.opselect[0] == 2) {
            this.rd.setColor(new Color(64, 143, 223));
            this.rd.fillRoundRect(338, 99, 190, 22, 7, 20);
            if (this.shaded) {
                this.rd.setColor(new Color(225, 200, 255));
            }
            else {
                this.rd.setColor(new Color(0, 89, 223));
            }
            this.rd.drawRoundRect(338, 99, 190, 22, 7, 20);
        }
        if (this.opselect[0] == 3) {
            this.rd.setColor(new Color(64, 143, 223));
            this.rd.fillRoundRect(376, 125, 109, 22, 7, 20);
            if (this.shaded) {
                this.rd.setColor(new Color(225, 200, 255));
            }
            else {
                this.rd.setColor(new Color(0, 89, 223));
            }
            this.rd.drawRoundRect(376, 125, 109, 22, 7, 20);
        }
        this.rd.drawImage(this.paused, 316, 8, null);
        if (control.enter && !this.triggerinst) {
            if (this.opselect[0] == 0) {
                if (this.loadedt[this.lastload]) {
                    if (this.isMidi[this.lastload]) {
                        if (!this.resumed && !this.mutem) {
                            this.mtracks[this.lastload].setPaused(false);
                            this.resumed = true;
                            this.stopped = false;
                        }
                    }
                    else {
                        this.stracks[this.lastload].resume();
                    }
                }
                this.fase = 609;
            }
            if (this.opselect[0] == 1) {
                if (record.caught >= 300) {
                    if (this.loadedt[this.lastload]) {
                        if (this.isMidi[this.lastload]) {
                            if (!this.resumed && !this.mutem) {
                                this.mtracks[this.lastload].setPaused(false);
                                this.stopped = false;
                                this.resumed = true;
                            }
                        }
                        else {
                            this.stracks[this.lastload].resume();
                        }
                    }
                    this.fase = -1;
                }
                else {
                    this.fase = -8;
                }
            }
            if (this.opselect[0] == 2) {
                if (this.loadedt[this.lastload]) {
                    if (this.isMidi[this.lastload]) {
                        if (!this.stopped) {
                            this.mtracks[this.lastload].setPaused(true);
                            this.resumed = false;
                            this.stopped = true;
                        }
                    }
                    else {
                        this.stracks[this.lastload].stop();
                    }
                }
                this.triggerinst = true;
            }
            if (this.opselect[0] == 3) {
                if (this.loadedt[this.lastload]) {
                    if (this.isMidi[this.lastload]) {
                        if (!this.stopped) {
                            this.mtracks[this.lastload].setPaused(true);
                            this.resumed = false;
                            this.stopped = true;
                        }
                    }
                    else {
                        this.stracks[this.lastload].stop();
                    }
                }
                if (this.careermode && stage == this.unlocked[1] && !this.bonstage) {
                    this.fase = 400;
                }
                else {
                    this.fase = 10;
                }
                this.opselect[0] = 0;
            }
            control.enter = false;
        }
        if (this.triggerinst) {
            this.inststuff(control);
            this.displayinst();
        }
        else {
            this.flipo = 0;
        }
    }
    
    public float pys(final int i, final int j, final int k, final int l) {
        return (float)Math.sqrt((i - j) * (i - j) + (k - l) * (k - l));
    }
    
    public void stat(final Madness[] madness, final CheckPoints checkpoints, final Control control, final ContO[] conto, final Contva contva, final boolean flag) {
        this.now = System.currentTimeMillis();
        ++this.framesCount;
        if (this.now - this.framesTimer > 1000L) {
            this.framesTimer = this.now;
            this.framesCountAvg = this.framesCount;
            this.framesCount = 0;
        }
        if (this.autoreplay) {
            boolean bspcheck = false;
            if ((checkpoints.stage < this.unlocked[1] || this.bonstage) && madness[0].level[this.sc[0]] >= this.maxlevel[this.unlocked[1] - 2] + 5) {
                bspcheck = true;
            }
            if (!this.scalelevels && madness[0].level[this.sc[0]] >= this.softlevelcap) {
                bspcheck = true;
            }
            this.rd.setFont(this.adventure.deriveFont(1, 22.0f));
            this.ftm = this.rd.getFontMetrics();
            final String message = "replay stage?";
            final int titlelength = this.ftm.stringWidth(message);
            this.rd.setColor(new Color(0, 0, 0, 175));
            final Polygon titlecard = new Polygon();
            titlecard.addPoint(428 - titlelength / 2, 50);
            titlecard.addPoint(442 + titlelength / 2, 50);
            titlecard.addPoint(457 + titlelength / 2, 65);
            titlecard.addPoint(457 + titlelength / 2, 76);
            titlecard.addPoint(442 + titlelength / 2, 91);
            titlecard.addPoint(428 - titlelength / 2, 91);
            titlecard.addPoint(413 - titlelength / 2, 76);
            titlecard.addPoint(413 - titlelength / 2, 65);
            this.rd.fillPolygon(titlecard);
            final String[] optiontitle = { "YES", "NO" };
            final int[] optionlength = new int[2];
            final Polygon[] optioncard = new Polygon[2];
            for (int a = 0; a < 2; ++a) {
                int highlight = 175;
                if ((this.replayoption == 1 && a == 1) || (this.replayoption == 2 && a == 0)) {
                    highlight = 90;
                }
                this.rd.setColor(new Color(0, 0, 0, highlight));
                (optioncard[a] = new Polygon()).addPoint(428 - titlelength / 2 + a * (14 + titlelength), 115);
                optioncard[a].addPoint(443 - titlelength / 2 + a * (-16 + titlelength), 130);
                optioncard[a].addPoint(493 - titlelength / 2 + a * (-116 + titlelength), 130);
                optioncard[a].addPoint(508 - titlelength / 2 + a * (-146 + titlelength), 115);
                optioncard[a].addPoint(493 - titlelength / 2 + a * (-116 + titlelength), 100);
                optioncard[a].addPoint(443 - titlelength / 2 + a * (-16 + titlelength), 100);
                this.rd.fillPolygon(optioncard[a]);
                this.rd.setFont(this.adventure.deriveFont(1, 14.0f));
                this.ftm = this.rd.getFontMetrics();
                optionlength[a] = this.ftm.stringWidth(optiontitle[a]);
                this.rd.setColor(new Color(255, 255, 255));
                this.rd.drawString(optiontitle[a], 468 - titlelength / 2 + a * (-66 + titlelength) - optionlength[a] / 2, 121);
            }
            this.rd.setFont(this.adventure.deriveFont(1, 19.0f));
            this.ftm = this.rd.getFontMetrics();
            if (!bspcheck) {
                this.drawcs(77, message, 255, 255, 255, 3);
            }
            else {
                this.drawcs(72, message, 255, 255, 255, 3);
                this.rd.setFont(this.adventure.deriveFont(1, 11.0f));
                this.ftm = this.rd.getFontMetrics();
                if (!this.stagefadephase) {
                    this.stagefade -= 50;
                }
                else {
                    this.stagefade += 50;
                }
                if (this.stagefade >= 255) {
                    this.stagefade = 255;
                    this.stagefadephase = false;
                }
                if (this.stagefade <= 0) {
                    this.stagefade = 0;
                    this.stagefadephase = true;
                }
                this.rd.setColor(new Color(255, 0, 0, this.stagefade));
                final String exclamation = "(!) ";
                final String nobsp = String.valueOf(exclamation) + " no bonus stat points";
                this.rd.drawString("no bonus stat points", 435 - this.ftm.stringWidth(nobsp) / 2 + this.ftm.stringWidth(exclamation), 85);
                this.rd.drawString(exclamation, 435 - this.ftm.stringWidth(nobsp) / 2, 85);
            }
            if (control.right || control.left) {
                if (this.replayoption == 1) {
                    this.replayoption = 2;
                }
                else {
                    this.replayoption = 1;
                }
                control.right = false;
                control.left = false;
            }
            if (control.enter || control.handb) {
                if (this.replayoption == 1) {
                    this.holdcnt = 0;
                    this.replayfade = true;
                }
                else {
                    this.replaydisable = true;
                    this.replayfade = false;
                    this.fase = -2;
                }
                this.autoreplay = false;
                control.handb = false;
                control.enter = false;
            }
        }
        this.rd.setFont(new Font("Arial", 1, 11));
        this.ftm = this.rd.getFontMetrics();
        if (this.holdit) {
            ++this.holdcnt;
            if (this.m.flex != 0) {
                this.m.flex = 0;
            }
            if ((!this.dontdisplay || (this.dontdisplay && this.ptmatch > 5)) && (control.enter || this.holdcnt > 250)) {
                if (!this.bossbattle) {
                    if (this.justcs == -1) {
                        this.autoreplay = false;
                        this.replayfade = false;
                        boolean donebonus = false;
                        for (int a2 = 0; a2 < 6; ++a2) {
                            if (this.bonusstage[a2] && ((this.boncomp[a2] == 1 && a2 != 1) || ((this.boncomp[a2] == 3 || (this.boncomp[a2] == 1 && this.racingwin) || (this.boncomp[a2] == 2 && this.wastingwin)) && a2 == 1))) {
                                donebonus = true;
                            }
                        }
                        if (this.careermode && this.winner && ((checkpoints.stage < this.unlocked[1] && !this.bonstage) || (this.bonstage && donebonus)) && !this.replaydisable && !this.replayfade) {
                            this.autoreplay = true;
                        }
                        if (!this.autoreplay) {
                            this.fase = -2;
                        }
                    }
                    if (this.justcs == 6 && this.holdcnt > 250) {
                        this.rerun = true;
                        this.justcs = 0;
                    }
                }
                else {
                    if (this.cstimer == 0) {
                        ++this.cstimer;
                    }
                    if (this.cstimer == 1) {
                        for (int a3 = 2; a3 < this.nplayers; ++a3) {
                            madness[a3].hitmag = madness[a3].maxmag[this.sc[a3]] + 1;
                        }
                        if (madness[0].hitmag == 0) {
                            madness[0].hitmag = 1;
                        }
                        ++this.cstimer;
                    }
                }
                control.enter = false;
            }
        }
        else {
            if (this.holdcnt != 0) {
                this.holdcnt = 0;
            }
            boolean nopause = false;
            if (this.careermode && this.starcnt == 0) {
                if (this.autoreplay || this.replayfade) {
                    nopause = true;
                }
                if (checkpoints.stage == 20 && !madness[0].mtouch) {
                    nopause = true;
                }
                if (checkpoints.stage == 21 && this.entered[0]) {
                    nopause = true;
                }
            }
            if (this.justcs == 6) {
                nopause = true;
            }
            if (control.enter && !nopause) {
                if (this.loadedt[this.lastload]) {
                    if (this.isMidi[this.lastload]) {
                        if (!this.stopped) {
                            this.mtracks[this.lastload].setPaused(true);
                            this.resumed = false;
                            this.stopped = true;
                        }
                    }
                    else {
                        this.stracks[this.lastload].stop();
                    }
                }
                this.fase = -6;
                control.enter = false;
            }
            else {
                control.enter = false;
            }
        }
        if (this.fase != -2) {
            this.holdit = false;
            int undeadextra = 0;
            boolean triggerboss = false;
            if (this.careermode) {
                if (checkpoints.stage == 17) {
                    undeadextra = 3;
                }
                if (checkpoints.stage == 11 && !this.bonusstage[1]) {
                    undeadextra = 4;
                }
                if (checkpoints.stage == 23 && this.cstimer < 10000 && (this.unlocked[1] == 23 || this.hardstage)) {
                    undeadextra = 1;
                    triggerboss = true;
                }
            }
            if (checkpoints.wasted == this.nplayers - 1 - undeadextra && (!this.dontdisplay || (this.dontdisplay && this.ptmatch > 5)) && (this.cstimer < 2 || this.cstimer == 10000)) {
                if (!this.autoreplay && !this.replayfade) {
                    this.rd.drawImage(this.youwastedem, 326, 70, null);
                    if (this.aflk) {
                        this.drawcs(120, "You Won, all cars have been wasted!", 0, 0, 0, 0);
                        this.aflk = false;
                    }
                    else {
                        this.drawcs(120, "You Won, all cars have been wasted!", 0, 128, 255, 0);
                        this.aflk = true;
                    }
                    if (this.justcs == -1) {
                        this.drawcs(460, "Press  [ Enter ]  to continue", 0, 0, 0, 0);
                    }
                }
                checkpoints.haltall = true;
                this.holdit = true;
                if (!triggerboss || this.cstimer == 10000) {
                    this.winner = true;
                }
                else {
                    this.bossbattle = true;
                }
                this.wastingwin = true;
                this.racingwin = false;
                if (!this.winfix && this.careermode && !triggerboss) {
                    double extramod = 1.0;
                    for (int a4 = 0; a4 < 6; ++a4) {
                        if (this.specialstats[this.sc[0]][2][a4] > 0) {
                            extramod = 1.0 + this.specialstats[this.sc[0]][2][a4] / 100.0;
                        }
                    }
                    int actualstage = 1;
                    final int whichlevel = Math.min(madness[this.nplayers - 1].level[this.sc[this.nplayers - 1]], madness[0].level[this.sc[0]]);
                    for (int a5 = 1; a5 < 31; ++a5) {
                        if (a5 < 30) {
                            if (whichlevel >= this.maxlevel[a5 - 1] && whichlevel < this.maxlevel[a5]) {
                                actualstage = a5;
                            }
                        }
                        else if (whichlevel >= this.maxlevel[a5 - 1]) {
                            actualstage = a5;
                        }
                    }
                    if (actualstage > this.unlocked[1]) {
                        actualstage = this.unlocked[1];
                    }
                    final int baseline = 217 + actualstage * 7;
                    double winlimit = this.winscn[this.sc[0]] / 2.0;
                    double newlimit = 1.0 / (this.expmult * 4.0);
                    if (newlimit > 1.0) {
                        newlimit = 1.0;
                    }
                    if (winlimit > 2500.0 * newlimit) {
                        winlimit = 2500.0 * newlimit;
                    }
                    double killslimit = this.killscn[this.sc[0]] * 6.0;
                    if (killslimit > 7200.0 * newlimit) {
                        killslimit = 7200.0 * newlimit;
                    }
                    final double winmultiplier = 1.0 + (winlimit + killslimit) / 200.0;
                    int chkpoints = (int)(checkpoints.nsp * (double)checkpoints.nlaps / 2.0);
                    if (chkpoints > 25 + (int)(actualstage * 0.5)) {
                        chkpoints = 25 + (int)(actualstage * 0.5);
                    }
                    double fakemult = this.expmult;
                    if (this.isithard) {
                        fakemult = Math.min(this.expmult, 0.25);
                    }
                    this.winamount = (int)(baseline * winmultiplier * chkpoints * fakemult * extramod * 0.325);
                    if (!this.noexp) {
                        final int[] exp = madness[0].exp;
                        final int n = this.sc[0];
                        exp[n] += this.winamount;
                        this.shexamtw = true;
                    }
                    this.winfix = true;
                }
            }
            if (this.dontdisplay) {
                for (int a6 = 1; a6 < 6; ++a6) {
                    if (this.ptmatch == a6 && this.ptmatchend[a6 - 1]) {
                        if (control.enter) {
                            for (int b = 0; b < this.nplayers; ++b) {
                                if (a6 < 4) {
                                    final int[] points = this.points;
                                    final int n2 = b;
                                    points[n2] += 10 - this.position[b];
                                }
                                else {
                                    final int[] points2 = this.points;
                                    final int n3 = b;
                                    points2[n3] += 20 - this.position[b] * 2;
                                }
                            }
                            this.fase = 51;
                            this.flipo = 0;
                            control.enter = false;
                        }
                        this.drawcs(460, "Press  [ Enter ]  to continue", 0, 0, 0, 0);
                    }
                }
            }
            if (!this.holdit && madness[0].dest && this.cntwis == 8) {
                if (this.careermode && ((checkpoints.stage == this.unlocked[1] && !this.bonstage && this.unlocked[1] > 1) || checkpoints.stage == 20)) {
                    madness[0].exp[this.sc[0]] = this.startexp;
                    this.statpoints[this.sc[0]] = this.startsp;
                    if (!this.losepoints) {
                        final int[] extpoints = this.extpoints;
                        final int n4 = this.sc[0];
                        extpoints[n4] -= this.statgain;
                        this.losepoints = true;
                    }
                }
                if (this.justcs == -1) {
                    if (checkpoints.stage != 20) {
                        this.drawcs(460, "You're wasted! Press  [ Enter ]  to continue...", 0, 0, 0, 0);
                    }
                    else {
                        this.drawcs(460, "Respawning at previous checkpoint...", 0, 0, 0, 0);
                    }
                }
                this.holdit = true;
                this.winner = false;
                this.bossbattle = false;
                this.wastingwin = false;
                this.racingwin = false;
            }
            if (!this.holdit && this.cstimer < 2) {
                int i = 0;
                do {
                    if (checkpoints.clear[i] == checkpoints.nlaps * checkpoints.nsp && checkpoints.pos[i] == 0) {
                        if (i == 0) {
                            if (!this.autoreplay && !this.replayfade) {
                                this.rd.drawImage(this.youwon, 368, 70, null);
                                if (this.aflk) {
                                    this.drawcs(120, "You finished first, nice job!", 0, 0, 0, 0);
                                    this.aflk = false;
                                }
                                else {
                                    this.drawcs(120, "You finished first, nice job!", 0, 128, 255, 0);
                                    this.aflk = true;
                                }
                            }
                            if (!triggerboss) {
                                this.winner = true;
                            }
                            else {
                                this.bossbattle = true;
                            }
                            this.racingwin = true;
                            this.wastingwin = false;
                            if (!this.winfix && this.careermode && !triggerboss) {
                                double extramod2 = 1.0;
                                for (int a7 = 0; a7 < 6; ++a7) {
                                    if (this.specialstats[this.sc[0]][2][a7] > 0) {
                                        extramod2 = 1.0 + this.specialstats[this.sc[0]][2][a7] / 100.0;
                                    }
                                }
                                int actualstage2 = 1;
                                final int whichlevel2 = Math.min(madness[this.nplayers - 1].level[this.sc[this.nplayers - 1]], madness[0].level[this.sc[0]]);
                                for (int a = 1; a < 31; ++a) {
                                    if (a < 30) {
                                        if (whichlevel2 >= this.maxlevel[a - 1] && whichlevel2 < this.maxlevel[a]) {
                                            actualstage2 = a;
                                        }
                                    }
                                    else if (whichlevel2 >= this.maxlevel[a - 1]) {
                                        actualstage2 = a;
                                    }
                                }
                                if (actualstage2 > this.unlocked[1]) {
                                    actualstage2 = this.unlocked[1];
                                }
                                final int baseline2 = 217 + actualstage2 * 7;
                                double winlimit2 = this.winscn[this.sc[0]] * 1.5;
                                double newlimit2 = 1.0 / (this.expmult * 4.0);
                                if (newlimit2 > 1.0) {
                                    newlimit2 = 1.0;
                                }
                                if (winlimit2 > 7500.0 * newlimit2) {
                                    winlimit2 = 7500.0 * newlimit2;
                                }
                                double killslimit2 = this.killscn[this.sc[0]] * 2.0;
                                if (killslimit2 > 2400.0 * newlimit2) {
                                    killslimit2 = 4800.0 * newlimit2;
                                }
                                final double winmultiplier2 = 1.0 + (winlimit2 + killslimit2) / 200.0;
                                int chkpoints2 = (int)(checkpoints.nsp * (double)checkpoints.nlaps / 2.0);
                                if (chkpoints2 > 25 + (int)(actualstage2 * 0.5)) {
                                    chkpoints2 = 25 + (int)(actualstage2 * 0.5);
                                }
                                double fakemult2 = this.expmult;
                                if (this.isithard) {
                                    fakemult2 = Math.min(this.expmult, 0.25);
                                }
                                this.winamount = (int)(baseline2 * winmultiplier2 * chkpoints2 * fakemult2 * extramod2 * 0.325);
                                if (!this.noexp) {
                                    final int[] exp2 = madness[0].exp;
                                    final int n5 = this.sc[0];
                                    exp2[n5] += this.winamount;
                                    this.shexamtr = false;
                                    this.shexamtw = true;
                                }
                                this.winfix = true;
                            }
                        }
                        else {
                            this.rd.drawImage(this.youlost, 371, 70, null);
                            this.rd.setFont(new Font("Arial", 1, 11));
                            this.ftm = this.rd.getFontMetrics();
                            if (this.aflk) {
                                this.drawcs(120, this.names[this.sc[i]] + " finished first, race over!", 0, 0, 0, 0);
                                this.aflk = false;
                            }
                            else {
                                this.drawcs(120, this.names[this.sc[i]] + " finished first, race over!", 0, 128, 255, 0);
                                this.aflk = true;
                            }
                            if (this.careermode && ((checkpoints.stage == this.unlocked[1] && !this.bonstage && this.unlocked[1] > 1) || checkpoints.stage == 20)) {
                                madness[0].exp[this.sc[0]] = this.startexp;
                                this.statpoints[this.sc[0]] = this.startsp;
                                if (!this.losepoints) {
                                    final int[] extpoints2 = this.extpoints;
                                    final int n6 = this.sc[0];
                                    extpoints2[n6] -= this.statgain;
                                    this.losepoints = true;
                                }
                            }
                            this.winner = false;
                            this.wastingwin = false;
                            this.racingwin = false;
                            this.loserace = true;
                        }
                        if (this.justcs == -1 && !this.autoreplay && !this.replayfade) {
                            this.drawcs(460, "Press  [ Enter ]  to continue", 0, 0, 0, 0);
                        }
                        checkpoints.haltall = true;
                        this.holdit = true;
                    }
                } while (++i < this.nplayers);
            }
            if (flag) {
                boolean disable = false;
                if (this.careermode) {
                    if (this.bonusstage[3]) {
                        disable = true;
                        this.arrace = true;
                    }
                    if (checkpoints.stage == 22 && this.targetcar == 0 && this.verydark) {
                        disable = true;
                    }
                    if (checkpoints.stage == 23 && this.bossbattle) {
                        disable = true;
                        this.arrace = true;
                    }
                }
                if (!disable && this.arrace != control.arrace && this.justcs == -1) {
                    this.arrace = control.arrace;
                    if (this.arrace) {
                        this.wasay = true;
                        this.say = " Arrow now pointing at  Cars  <";
                        this.tcnt = -5;
                    }
                    if (!this.arrace) {
                        this.wasay = false;
                        this.say = " Arrow now pointing at  Track  <";
                        this.tcnt = -5;
                        this.cntan = 20;
                    }
                }
                int bright = 0;
                if (checkpoints.stage == 6 || checkpoints.stage == 7 || checkpoints.stage == 10 || (checkpoints.stage == 15 && !this.bonstage) || checkpoints.stage == 22) {
                    bright = 150;
                }
                if (!this.holdit && this.fase != -6 && this.starcnt == 0) {
                    final int carnumber = this.nplayers;
                    for (int newnumber = 0; newnumber < 7; ++newnumber) {
                        boolean newbool = false;
                        for (int newnumbers = 0; newnumbers < carnumber; ++newnumbers) {
                            if (checkpoints.pos[newnumbers] == newnumber && checkpoints.dested[newnumbers] == 0 && !newbool && !this.noarrow[newnumbers]) {
                                if (!this.dontdisplay || this.dontdisplay) {
                                    this.rd.setColor(new Color(0, 0, 0));
                                    if (newnumber == 0) {
                                        this.rd.drawString("1st", 758, 142 + 30 * newnumber);
                                    }
                                    if (newnumber == 1) {
                                        this.rd.drawString("2nd", 756, 142 + 30 * newnumber);
                                    }
                                    if (newnumber == 2) {
                                        this.rd.drawString("3rd", 756, 142 + 30 * newnumber);
                                    }
                                    if (newnumber >= 3) {
                                        this.rd.drawString(String.valueOf(newnumber + 1) + "th", 756, 142 + 30 * newnumber);
                                    }
                                }
                                if (this.beastopponent[newnumbers]) {
                                    this.rd.setFont(this.adventure.deriveFont(1, 10.5f));
                                    this.ftm = this.rd.getFontMetrics();
                                }
                                else {
                                    this.rd.setFont(new Font("Arial", 1, 11));
                                    this.ftm = this.rd.getFontMetrics();
                                }
                                if (!this.fixspecials[newnumbers] && !madness[newnumbers].frozen && !madness[newnumbers].redstr && !madness[newnumbers].leech) {
                                    this.rd.setColor(new Color(0, 0, 0, this.shadowtrans[newnumbers]));
                                    if (this.m.effect[10]) {
                                        this.rd.setColor(new Color(bright, bright, bright, this.shadowtrans[newnumbers]));
                                    }
                                    this.glow[newnumbers] = false;
                                }
                                else {
                                    this.glow[newnumbers] = true;
                                }
                                if (this.glow[newnumbers]) {
                                    if (!this.glowphase[newnumbers]) {
                                        final int[] glowg = this.glowg;
                                        final int n7 = newnumbers;
                                        glowg[n7] += 12;
                                    }
                                    else {
                                        final int[] glowg2 = this.glowg;
                                        final int n8 = newnumbers;
                                        glowg2[n8] -= 12;
                                    }
                                    if (this.glowg[newnumbers] <= 65) {
                                        this.glowphase[newnumbers] = false;
                                    }
                                    if (this.glowg[newnumbers] >= 225) {
                                        this.glowphase[newnumbers] = true;
                                    }
                                    if (this.fixspecials[newnumbers] && !madness[newnumbers].frozen && !madness[newnumbers].redstr && !madness[newnumbers].leech && !madness[newnumbers].strswap) {
                                        this.rd.setColor(new Color(this.glowg[newnumbers], 0, 0, this.shadowtrans[newnumbers]));
                                    }
                                    if (madness[newnumbers].frozen && !madness[newnumbers].redstr && !madness[newnumbers].leech && !madness[newnumbers].strswap) {
                                        this.rd.setColor(new Color(0, 0, this.glowg[newnumbers], this.shadowtrans[newnumbers]));
                                    }
                                    if (!this.glowphase2[newnumbers]) {
                                        if (this.glowg2[newnumbers] < 245) {
                                            final int[] glowg3 = this.glowg2;
                                            final int n9 = newnumbers;
                                            glowg3[n9] += 10;
                                        }
                                    }
                                    else if (this.glowg2[newnumbers] > 75) {
                                        final int[] glowg4 = this.glowg2;
                                        final int n10 = newnumbers;
                                        glowg4[n10] -= 10;
                                    }
                                    if (this.glowg2[newnumbers] <= 150) {
                                        this.glowphase2[newnumbers] = false;
                                    }
                                    if (this.glowg2[newnumbers] >= 230) {
                                        this.glowphase2[newnumbers] = true;
                                    }
                                    if (madness[newnumbers].redstr && !madness[newnumbers].leech && !madness[newnumbers].strswap) {
                                        this.rd.setColor(new Color(this.glowg2[newnumbers], this.glowg2[newnumbers] / 2, 0, this.shadowtrans[newnumbers]));
                                    }
                                    if (madness[newnumbers].leech && !madness[newnumbers].strswap) {
                                        this.rd.setColor(new Color(this.glowg2[newnumbers] - 60, (this.glowg2[newnumbers] - 60) * 3 / 4, 0, this.shadowtrans[newnumbers]));
                                    }
                                    if (madness[newnumbers].strswap) {
                                        this.rd.setColor(new Color(0, this.glowg[newnumbers], 0, this.shadowtrans[newnumbers]));
                                    }
                                    if (this.glowg[newnumbers] > 255) {
                                        this.glowg[newnumbers] = 255;
                                    }
                                    if (this.glowg[newnumbers] < 0) {
                                        this.glowg[newnumbers] = 0;
                                    }
                                    if (this.glowg2[newnumbers] > 255) {
                                        this.glowg2[newnumbers] = 255;
                                    }
                                    if (this.glowg2[newnumbers] < 65) {
                                        this.glowg2[newnumbers] = 65;
                                    }
                                }
                                if (!this.dontdisplay) {
                                    if (this.sc[newnumbers] != 32 && this.sc[newnumbers] != 25) {
                                        this.rd.drawString(this.names[this.sc[newnumbers]], 820 - this.ftm.stringWidth(this.names[this.sc[newnumbers]]) / 2, 131 + 30 * newnumber);
                                    }
                                    else {
                                        if (this.sc[newnumbers] == 32) {
                                            this.rd.drawString("SoJ", 820 - this.ftm.stringWidth("SoJ") / 2, 131 + 30 * newnumber);
                                        }
                                        if (this.sc[newnumbers] == 25) {
                                            this.rd.drawString("Wow C.", 820 - this.ftm.stringWidth("Wow C.") / 2, 131 + 30 * newnumber);
                                        }
                                    }
                                }
                                else {
                                    this.rd.drawString(this.ptplayers[newnumbers], 816 - this.ftm.stringWidth(this.ptplayers[newnumbers]) / 2, 131 + 30 * newnumber);
                                }
                                this.rd.setFont(new Font("Arial", 1, 11));
                                for (int a8 = 0; a8 < 7; ++a8) {
                                    if (checkpoints.pos[0] == a8) {
                                        this.alhover[a8] = false;
                                        this.arrowlock[a8] = false;
                                    }
                                    if (this.alhover[a8]) {
                                        this.rd.setColor(new Color(80, 170, 255));
                                        this.rd.drawRect(753, 119 + 30 * a8, 112, 25);
                                        this.rd.drawRect(752, 120 + 30 * a8, 112, 23);
                                    }
                                    if (this.arrowlocked) {
                                        if (this.arrowlock[a8]) {
                                            for (int d = 1; d < this.nplayers; ++d) {
                                                if (checkpoints.pos[d] == a8) {
                                                    if (!this.lockonce) {
                                                        this.lockedon = d;
                                                        this.lockonce = true;
                                                    }
                                                    if (madness[this.lockedon].im == newnumbers) {
                                                        this.rd.setColor(new Color(80, 170, 255));
                                                        this.rd.drawRect(753, 119 + 30 * newnumber, 112, 25);
                                                        this.rd.drawRect(752, 120 + 30 * newnumber, 112, 23);
                                                        this.targetsq[newnumber] = true;
                                                        for (int c = 0; c < 7; ++c) {
                                                            if (c != newnumber) {
                                                                this.targetsq[c] = false;
                                                            }
                                                        }
                                                    }
                                                    if (madness[this.lockedon].dest) {
                                                        this.arrowlocked = false;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    else {
                                        this.lockonce = false;
                                        for (int e = 0; e < 7; ++e) {
                                            this.targetsq[e] = false;
                                        }
                                    }
                                }
                                if (!control.swap || !this.arrace || this.justcs == 6) {
                                    if (this.arrace) {
                                        int i_66_ = (int)(60.0f * (madness[newnumbers].hitmag / (float)madness[newnumbers].maxmag[this.sc[newnumbers]]));
                                        int i_67_ = 244;
                                        int i_68_ = 244;
                                        int i_69_ = 11;
                                        if (i_66_ > 20) {
                                            i_68_ = (int)(244.0f - 233.0f * ((i_66_ - 20) / 40.0f));
                                        }
                                        i_67_ += (i_67_ * (this.m.snap[0] / 100.0f));  // cast: bytecode-verified
                                        if (i_66_ > 60) {
                                            i_66_ = 60;
                                        }
                                        if (i_67_ > 255) {
                                            i_67_ = 255;
                                        }
                                        if (i_67_ < 0) {
                                            i_67_ = 0;
                                        }
                                        i_68_ += (i_68_ * (this.m.snap[1] / 100.0f));  // cast: bytecode-verified
                                        if (i_68_ > 255) {
                                            i_68_ = 255;
                                        }
                                        if (i_68_ < 0) {
                                            i_68_ = 0;
                                        }
                                        i_69_ += (i_69_ * (this.m.snap[2] / 100.0f));  // cast: bytecode-verified
                                        if (i_69_ > 255) {
                                            i_69_ = 255;
                                        }
                                        if (i_69_ < 0) {
                                            i_69_ = 0;
                                        }
                                        this.rd.setColor(new Color(i_67_, i_68_, i_69_));
                                        this.rd.fillRect(785, 135 + 30 * newnumber, i_66_, 5);
                                        this.rd.setColor(new Color(this.dmgflash[newnumbers], 0, 0));
                                        this.rd.drawRect(785, 135 + 30 * newnumber, 60, 5);
                                        newbool = true;
                                    }
                                    else {
                                        final int aipower = (int)(60.0f * (madness[newnumbers].power / 98.0f));
                                        int red = (int)(128.0f + 128.0f * (this.m.snap[0] / 100.0f));
                                        if (madness[newnumbers].power == 98.0f) {
                                            red = (int)(64.0f + 64.0f * (this.m.snap[0] / 100.0f));
                                        }
                                        if (red > 255) {
                                            red = 255;
                                        }
                                        if (red < 0) {
                                            red = 0;
                                        }
                                        int green = (int)(244.0f + 244.0f * (this.m.snap[1] / 100.0f));
                                        if (green > 255) {
                                            green = 255;
                                        }
                                        if (green < 0) {
                                            green = 0;
                                        }
                                        int blue = (int)(244.0f + 244.0f * (this.m.snap[2] / 100.0f));
                                        if (blue > 255) {
                                            blue = 255;
                                        }
                                        if (blue < 0) {
                                            blue = 0;
                                        }
                                        this.rd.setColor(new Color(red, green, blue));
                                        this.rd.fillRect(785, 135 + 30 * newnumber, aipower, 5);
                                        this.rd.setColor(new Color(0, 0, 0));
                                        this.rd.drawRect(785, 135 + 30 * newnumber, 60, 5);
                                    }
                                }
                                else {
                                    int aispecs = (int)(60.0f * (madness[newnumbers].spatk / 120.0f));
                                    int i_71_ = (int)(220.0f + 220.0f * (this.m.snap[0] / 100.0f));
                                    if (!this.fixspecials[newnumbers]) {
                                        if (i_71_ > 255) {
                                            i_71_ = 255;
                                        }
                                        if (i_71_ < 0) {
                                            i_71_ = 0;
                                        }
                                    }
                                    else {
                                        aispecs = (int)(60.0f * (madness[newnumbers].speclast / 120.0f));
                                        i_71_ = (int)(85.0f + 85.0f * (this.m.snap[0] / 100.0f));
                                        if (i_71_ > 255) {
                                            i_71_ = 255;
                                        }
                                        if (i_71_ < 0) {
                                            i_71_ = 0;
                                        }
                                    }
                                    this.rd.setColor(new Color(i_71_, 0, 0));
                                    this.rd.fillRect(785, 135 + 30 * newnumber, aispecs, 5);
                                    this.rd.setColor(new Color(0, 0, 0));
                                    this.rd.drawRect(785, 135 + 30 * newnumber, 60, 5);
                                }
                            }
                        }
                    }
                    if (!disable) {
                        this.arrow(madness[0].point, madness[0].missedcp, checkpoints, this.arrace, madness, control.swap, conto);
                    }
                    if (!this.arrace && this.auscnt == 45 && madness[0].capcnt == 0 && this.justcs == -1) {
                        if (madness[0].missedcp > 0) {
                            if (madness[0].missedcp > 15 && madness[0].missedcp < 50) {
                                if (this.flk) {
                                    this.drawcs(70, "Checkpoint Missed!", 255, 0, 0, 0);
                                }
                                else {
                                    this.drawcs(70, "Checkpoint Missed!", 255, 150, 0, 2);
                                }
                            }
                            final Madness madness2 = madness[0];
                            ++madness2.missedcp;
                            if (madness[0].missedcp == 70) {
                                madness[0].missedcp = -2;
                            }
                        }
                        else if (madness[0].mtouch && this.cntovn < 70) {
                            if (Math.abs(this.ana) > 100) {
                                ++this.cntan;
                            }
                            else if (this.cntan != 0) {
                                --this.cntan;
                            }
                            if (this.cntan > 40) {
                                ++this.cntovn;
                                this.cntan = 40;
                                if (this.flk) {
                                    this.drawcs(70, "Wrong Way!", 255, 150, 0, 0);
                                    this.flk = false;
                                }
                                else {
                                    this.drawcs(70, "Wrong Way!", 255, 0, 0, 2);
                                    this.flk = true;
                                }
                            }
                        }
                    }
                }
                this.rd.drawImage(this.dmg, 670, 7, null);
                this.rd.drawImage(this.pwr, 670, 27, null);
                this.rd.drawImage(this.special, 728, 47, null);
                int spatkpower = (int)(98.0f * (madness[0].spatk / 120.0f));
                if (!this.fixspecials[0]) {
                    int red2 = (int)(220.0f + 220.0f * (this.m.snap[0] / 100.0f));
                    if (red2 > 255) {
                        red2 = 255;
                    }
                    if (red2 < 0) {
                        red2 = 0;
                    }
                    this.rd.setColor(new Color(red2, 0, 0));
                }
                else {
                    int red2 = (int)(85.0f + 85.0f * (this.m.snap[0] / 100.0f));
                    if (red2 > 255) {
                        red2 = 255;
                    }
                    if (red2 < 0) {
                        red2 = 0;
                    }
                    this.rd.setColor(new Color(red2, 0, 0));
                    spatkpower = (int)(98.0f * (madness[0].speclast / 120.0f));
                }
                this.rd.fillRect(732, 51, spatkpower, 8);
                this.rd.setFont(this.adventure.deriveFont(1, 12.7f));
                this.ftm = this.rd.getFontMetrics();
                this.rd.setColor(new Color(0, 0, 0));
                this.rd.drawString("Special", 718 - this.ftm.stringWidth("Special"), 61);
                this.rd.drawString("Power", 718 - this.ftm.stringWidth("Power"), 41);
                this.rd.drawString("Damage", 718 - this.ftm.stringWidth("Damage"), 21);
                this.rd.setFont(new Font("Arial", 1, 11));
                this.ftm = this.rd.getFontMetrics();
                if (!this.dontdisplay || (this.dontdisplay && this.ptmatch != 1 && this.ptmatch != 4 && this.ptmatch != 5)) {
                    this.rd.setColor(new Color(0, 0, 100));
                    if (bright > 0) {
                        this.rd.setColor(new Color(bright, bright, bright));
                    }
                    if (!this.unlimitedlaps) {
                        this.rd.drawString(madness[0].nlaps + 1 + " / " + checkpoints.nlaps, 51, 18);
                    }
                    else {
                        this.rd.drawString("- / " + checkpoints.nlaps, 51, 18);
                    }
                    if (!this.bonusstage[3]) {
                        this.rd.drawString(checkpoints.wasted + " / " + (this.nplayers - 1 - undeadextra), 150, 18);
                    }
                    else {
                        this.rd.drawString("- / -", 150, 18);
                    }
                    this.rd.setColor(new Color(0, 0, 0));
                    this.rd.setFont(this.adventure.deriveFont(1, 13.4f));
                    this.ftm = this.rd.getFontMetrics();
                    this.rd.drawString("lap:", 43 - this.ftm.stringWidth("lap:"), 18);
                    this.rd.drawString("wasted:", 137 - this.ftm.stringWidth("wasted"), 18);
                    this.rd.drawString("position:", 81 - this.ftm.stringWidth("position:"), 40);
                    this.rd.setColor(new Color(bright, bright, bright));
                    this.rd.setFont(this.adventure.deriveFont(1, 17.0f));
                    String suffix = "";
                    int position = checkpoints.pos[0] + 1;
                    if (this.bonusstage[3]) {
                        position = 1;
                    }
                    if ((position - 1) % 10 == 0 && position != 11) {
                        suffix = "st";
                    }
                    if ((position - 2) % 10 == 0 && position != 12) {
                        suffix = "nd";
                    }
                    if ((position - 3) % 10 == 0 && position != 13) {
                        suffix = "rd";
                    }
                    if (position % 10 == 0 || position % 10 >= 4 || position == 11 || position == 12 || position == 13) {
                        suffix = "th";
                    }
                    if (!this.unlimitedlaps) {
                        this.rd.drawString(new StringBuilder().append(position).toString(), 88, 43);
                        final int offset = this.ftm.stringWidth(new StringBuilder().append(position).toString());
                        this.rd.setFont(this.adventure.deriveFont(1, 9.6f));
                        this.ftm = this.rd.getFontMetrics();
                        this.rd.drawString(new StringBuilder().append(suffix).toString(), 93 + offset, 38);
                    }
                    else {
                        this.rd.drawString(" - ", 88, 43);
                    }
                    this.rd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
                    this.rd.setFont(new Font("Arial", 1, 11));
                    this.ftm = this.rd.getFontMetrics();
                }
                if (this.dontdisplay && (this.ptmatch == 1 || this.ptmatch == 4 || this.ptmatch == 5)) {
                    this.rd.drawImage(this.pos, 20, 15, null);
                    this.rd.setFont(this.adventure.deriveFont(1, 25.0f));
                    this.rd.setColor(new Color(0, 0, 60));
                    if (this.position[0] <= 2) {
                        if (this.position[0] == 0) {
                            this.rd.drawString("1st", 95, 30);
                        }
                        if (this.position[0] == 1) {
                            this.rd.drawString("2nd", 95, 30);
                        }
                        if (this.position[0] == 2) {
                            this.rd.drawString("3rd", 95, 30);
                        }
                    }
                    else {
                        this.rd.drawString(this.position[0] + 1 + "th", 95, 30);
                    }
                    this.rd.setFont(new Font("Arial", 1, 11));
                    this.ftm = this.rd.getFontMetrics();
                }
                this.drawstat(madness[0].maxmag[madness[0].cn], madness[0].hitmag, madness[0].newcar, madness[0].power, madness[0].spatk, madness[0].speclast, control);
            }
            this.rd.setFont(new Font("Arial", 1, 11));
            this.rd.getFontMetrics();
            if (this.unlimitedlaps) {
                madness[0].nlaps = 0;
            }
            if (this.starcnt != 0 && this.starcnt <= 35 && !this.holdit) {
                if (this.starcnt == 35 && !this.mutes) {
                    this.three.play();
                }
                if (this.starcnt == 24) {
                    this.gocnt = 2;
                    if (!this.mutes) {
                        this.two.play();
                    }
                }
                if (this.starcnt == 13) {
                    this.gocnt = 1;
                    if (!this.mutes) {
                        this.one.play();
                    }
                }
                if (this.starcnt == 2) {
                    this.gocnt = 0;
                    if (!this.mutes) {
                        this.go.play();
                    }
                }
                this.duds = 0;
                if (this.starcnt <= 37 && this.starcnt > 32) {
                    this.duds = 1;
                }
                if (this.starcnt <= 26 && this.starcnt > 21) {
                    this.duds = 1;
                }
                if (this.starcnt <= 15 && this.starcnt > 10) {
                    this.duds = 1;
                }
                if (this.starcnt <= 4) {
                    this.duds = 2;
                    this.m.flex = 0;
                }
                if (this.dudo != -1) {
                    this.rd.drawImage(this.dudeb[this.duds], this.dudo, 0, null);
                }
                if (this.gocnt != 0) {
                    this.rd.drawImage(this.cntdn[this.gocnt], 420, 50, null);
                }
                else {
                    this.rd.drawImage(this.cntdn[this.gocnt], 398, 50, null);
                }
            }
            if (this.looped != 0 && madness[0].loop == 2 && !this.holdit) {
                this.looped = 0;
            }
            if (!this.holdit) {
                if (madness[0].power < 45.0f) {
                    if (this.tcnt == 30 && this.auscnt == 45 && madness[0].mtouch && madness[0].capcnt == 0 && this.justcs == -1) {
                        if (this.looped != 2) {
                            if (this.pwcnt < 70 || (this.pwcnt < 160 && this.looped != 0)) {
                                if (this.pwflk) {
                                    this.drawcs(110, "Power low, perform stunt!", 0, 0, 200, 0);
                                    this.pwflk = false;
                                }
                                else {
                                    this.drawcs(110, "Power low, perform stunt!", 255, 100, 0, 0);
                                    this.pwflk = true;
                                }
                            }
                        }
                        else if (this.pwcnt < 250) {
                            if (this.pwflk) {
                                this.drawcs(105, "> >  Press Enter for GAME INSTRUCTIONS!  < <", 0, 0, 200, 0);
                                this.drawcs(120, "To learn how to preform STUNTS!", 0, 0, 200, 0);
                                this.pwflk = false;
                            }
                            else {
                                this.drawcs(105, "> >  Press Enter for GAME INSTRUCTIONS!  < <", 255, 100, 0, 0);
                                this.drawcs(120, "To learn how to preform STUNTS!", 255, 100, 0, 0);
                                this.pwflk = true;
                            }
                        }
                        ++this.pwcnt;
                        if (this.pwcnt == 300) {
                            this.pwcnt = 0;
                            if (this.looped != 0) {
                                ++this.looped;
                                if (this.looped == 3) {
                                    this.looped = 1;
                                }
                            }
                        }
                    }
                }
                else if (this.pwcnt != 0) {
                    this.pwcnt = 0;
                }
                if (this.careermode && checkpoints.stage == 140 && madness[this.nplayers - 1].trcnt == 10) {
                    for (int a6 = 0; a6 < 2; ++a6) {
                        while (madness[this.nplayers - 1].travzy[a6] > 225) {
                            final int[] travzy = madness[this.nplayers - 1].travzy;
                            final int n11 = a6;
                            travzy[n11] -= 360;
                        }
                        while (madness[this.nplayers - 1].travzy[a6] < 65311) {
                            final int[] travzy2 = madness[this.nplayers - 1].travzy;
                            final int n12 = a6;
                            travzy2[n12] += 360;
                        }
                        madness[this.nplayers - 1].travxy[a6] = Math.abs(madness[this.nplayers - 1].travxy[a6]);
                        while (madness[this.nplayers - 1].travxy[a6] > 270) {
                            final int[] travxy = madness[this.nplayers - 1].travxy;
                            final int n13 = a6;
                            travxy[n13] -= 360;
                        }
                        madness[this.nplayers - 1].travxz[a6] = Math.abs(madness[this.nplayers - 1].travxz[a6]);
                        while (madness[this.nplayers - 1].travxz[a6] > 90) {
                            final int[] travxz = madness[this.nplayers - 1].travxz;
                            final int n14 = a6;
                            travxz[n14] -= 180;
                        }
                    }
                }
                if (madness[0].capcnt == 0) {
                    if (this.tcnt < 30) {
                        if (this.tflk) {
                            if (!this.wasay) {
                                this.drawcs(125, this.say, 0, 0, 0, 0);
                            }
                            else {
                                this.drawcs(125, this.say, 0, 0, 0, 0);
                            }
                            this.tflk = false;
                        }
                        else {
                            if (!this.wasay) {
                                this.drawcs(125, this.say, 0, 128, 255, 0);
                            }
                            else {
                                this.drawcs(125, this.say, 255, 128, 0, 0);
                            }
                            this.tflk = true;
                        }
                        ++this.tcnt;
                    }
                    else if (this.wasay) {
                        this.wasay = false;
                    }
                    if (this.auscnt < 45) {
                        if (this.aflk) {
                            this.drawcs(105, this.asay, 98, 176, 255, 0);
                            this.aflk = false;
                        }
                        else {
                            this.drawcs(105, this.asay, 0, 128, 255, 0);
                            this.aflk = true;
                        }
                        ++this.auscnt;
                    }
                }
                else if (this.tflk) {
                    this.drawcs(110, "Bad landing!", 0, 0, 200, 0);
                    this.tflk = false;
                }
                else {
                    this.drawcs(110, "Bad landing!", 255, 100, 0, 0);
                    this.tflk = true;
                }
                if (madness[0].trcnt == 10) {
                    this.loop = "";
                    this.spin = "";
                    this.asay = "";
                    int j = 0;
                    while (madness[0].travzy[0] > 225) {
                        final int[] travzy3 = madness[0].travzy;
                        final int n15 = 0;
                        travzy3[n15] -= 360;
                        ++j;
                    }
                    while (madness[0].travzy[0] < -225) {
                        final int[] travzy4 = madness[0].travzy;
                        final int n16 = 0;
                        travzy4[n16] += 360;
                        --j;
                    }
                    if (j == 1) {
                        this.loop = "forward loop";
                    }
                    if (j == 2) {
                        this.loop = "double forward";
                    }
                    if (j == 3) {
                        this.loop = "triple forward";
                    }
                    if (j >= 4) {
                        this.loop = "massive forward looping";
                    }
                    if (j == -1) {
                        this.loop = "backloop";
                    }
                    if (j == -2) {
                        this.loop = "double back";
                    }
                    if (j == -3) {
                        this.loop = "triple back";
                    }
                    if (j <= -4) {
                        this.loop = "massive back looping";
                    }
                    if (j == 0) {
                        if (madness[0].ftab[0] && madness[0].btab[0]) {
                            this.loop = "tabletop and reversed tabletop";
                        }
                        else if (madness[0].ftab[0] || madness[0].btab[0]) {
                            this.loop = "tabletop";
                        }
                    }
                    if (j > 0 && madness[0].btab[0]) {
                        this.loop = "hanged " + this.loop;
                    }
                    if (j < 0 && madness[0].ftab[0]) {
                        this.loop = "hanged " + this.loop;
                    }
                    if (this.loop != "") {
                        this.asay = String.valueOf(this.asay) + " " + this.loop;
                    }
                    j = 0;
                    madness[0].travxy[0] = Math.abs(madness[0].travxy[0]);
                    while (madness[0].travxy[0] > 270) {
                        final int[] travxy2 = madness[0].travxy;
                        final int n17 = 0;
                        travxy2[n17] -= 360;
                        ++j;
                    }
                    if (j == 0 && madness[0].rtab[0]) {
                        if (this.loop == "") {
                            this.spin = "tabletop";
                        }
                        else {
                            this.spin = "flipside";
                        }
                    }
                    if (j == 1) {
                        this.spin = "rollspin";
                    }
                    if (j == 2) {
                        this.spin = "double rollspin";
                    }
                    if (j == 3) {
                        this.spin = "triple rollspin";
                    }
                    if (j >= 4) {
                        this.spin = "massive roll spinning";
                    }
                    j = 0;
                    boolean flag2 = false;
                    madness[0].travxz[0] = Math.abs(madness[0].travxz[0]);
                    while (madness[0].travxz[0] > 90) {
                        final int[] travxz2 = madness[0].travxz;
                        final int n18 = 0;
                        travxz2[n18] -= 180;
                        j += 180;
                        if (j > 900) {
                            j = 900;
                            flag2 = true;
                        }
                    }
                    if (j != 0) {
                        if (this.loop == "" && this.spin == "") {
                            this.asay = String.valueOf(this.asay) + " " + j;
                            if (flag2) {
                                this.asay = String.valueOf(this.asay) + " and beyond";
                            }
                        }
                        else {
                            if (this.spin != "") {
                                if (this.loop == "") {
                                    this.asay = String.valueOf(this.asay) + " " + this.spin;
                                }
                                else {
                                    this.asay = String.valueOf(this.asay) + " with " + this.spin;
                                }
                            }
                            this.asay = String.valueOf(this.asay) + " by " + j;
                            if (flag2) {
                                this.asay = String.valueOf(this.asay) + " and beyond";
                            }
                        }
                    }
                    else if (this.spin != "") {
                        if (this.loop == "") {
                            this.asay = String.valueOf(this.asay) + " " + this.spin;
                        }
                        else {
                            this.asay = String.valueOf(this.asay) + " by " + this.spin;
                        }
                    }
                    if (this.asay != "") {
                        this.auscnt -= 15;
                    }
                    if (this.loop != "") {
                        this.auscnt -= 25;
                    }
                    if (this.spin != "") {
                        this.auscnt -= 25;
                    }
                    if (j != 0) {
                        this.auscnt -= 25;
                    }
                    if (this.auscnt < 45) {
                        if (!this.mutes) {
                            this.powerup.play();
                        }
                        if (this.auscnt < -20) {
                            this.auscnt = -20;
                        }
                        byte byte0 = 0;
                        if (madness[0].powerup[0] > 20.0f) {
                            byte0 = 1;
                        }
                        if (madness[0].powerup[0] > 40.0f) {
                            byte0 = 2;
                        }
                        if (madness[0].powerup[0] > 150.0f) {
                            byte0 = 3;
                        }
                        if (madness[0].surfer[0]) {
                            this.asay = " " + this.adj[4][(int)(this.m.random() * 3.0f)] + this.asay;
                        }
                        if (byte0 != 3) {
                            this.asay = String.valueOf(this.adj[byte0][(int)(this.m.random() * 3.0f)]) + this.asay + this.exlm[byte0];
                        }
                        else {
                            this.asay = this.adj[byte0][(int)(this.m.random() * 3.0f)];
                        }
                        if (!this.wasay) {
                            this.tcnt = this.auscnt;
                            if (madness[0].power != 98.0f) {
                                this.say = "Power up " + (int)(100.0f * madness[0].powerup[0] / 98.0f) + "%";
                            }
                            else {
                                this.say = "Power to the MAX";
                            }
                            if (this.skidup) {
                                this.skidup = false;
                            }
                            else {
                                this.skidup = true;
                            }
                        }
                    }
                }
                if (madness[0].newcar) {
                    if (!this.wasay) {
                        this.say = "Car fixed";
                        this.tcnt = 0;
                    }
                    if (this.crashup) {
                        this.crashup = false;
                    }
                    else {
                        this.crashup = true;
                    }
                }
            }
            boolean wintrue = false;
            boolean chkfix = false;
            if (this.clear != madness[0].clear && madness[0].clear != 0) {
                if (!this.wasay && !this.unlimitedlaps && !this.holdit) {
                    this.say = "Checkpoint!";
                    this.tcnt = 15;
                }
                if (!this.unlimitedlaps) {
                    if (this.justcs == -1) {
                        wintrue = true;
                    }
                    chkfix = true;
                }
                if (wintrue) {
                    ++this.wins;
                    this.rcestatgain[this.sc[0]] = false;
                    if (this.wins % 4 == 0) {
                        this.shwcnt = true;
                    }
                    if (this.careermode) {
                        this.clearlimit = checkpoints.clear[0];
                        if (this.clearlimit > 25) {
                            this.clearlimit = 25;
                        }
                        if (!this.noexp) {
                            this.winchance[0] = (int)(Math.random() * 1000.0) + 1;
                        }
                        else {
                            this.winchance[0] = 1000000;
                        }
                        double doublechance = 1.0;
                        int levelbarrier = (this.sc[0] - 7) * 5;
                        if (this.sc[0] <= 7 || (this.sc[0] >= 23 && this.sc[0] <= 30)) {
                            levelbarrier = 0;
                        }
                        if (this.sc[0] == 20) {
                            levelbarrier = 55;
                        }
                        if (this.sc[0] == 21) {
                            levelbarrier = 60;
                        }
                        if (this.sc[0] == 22) {
                            levelbarrier = 70;
                        }
                        if (this.sc[0] >= 31 && this.sc[0] <= 33) {
                            levelbarrier = 15;
                        }
                        if (this.sc[0] >= 34 && this.sc[0] <= 36) {
                            levelbarrier = 30;
                        }
                        if (this.sc[0] == 37 || this.sc[0] == 38) {
                            levelbarrier = 40;
                        }
                        if (madness[0].level[this.sc[0]] <= levelbarrier) {
                            doublechance = 1.3;
                        }
                        int leveldiff = this.averagelevel - this.startinglevel;
                        if (leveldiff > 40) {
                            leveldiff = 40;
                        }
                        int bspbuff = 0;
                        if (this.isithard) {
                            bspbuff = leveldiff;
                        }
                        double plshelp = 1.0;
                        if (madness[0].level[this.sc[0]] >= 10) {
                            if ((this.sc[0] < 8 || (this.sc[0] >= 23 && this.sc[0] <= 30)) && this.extpoints[this.sc[0]] < (int)(madness[0].level[this.sc[0]] * 0.5)) {
                                plshelp = 1.5;
                            }
                            if ((this.sc[0] == 8 || this.sc[0] == 9 || this.sc[0] == 10) && this.extpoints[this.sc[0]] < madness[0].level[this.sc[0]]) {
                                plshelp = 1.5;
                            }
                            if ((this.sc[0] == 11 || this.sc[0] == 12 || (this.sc[0] >= 31 && this.sc[0] <= 35)) && this.extpoints[this.sc[0]] < (int)(madness[0].level[this.sc[0]] * 1.5)) {
                                plshelp = 1.5;
                            }
                            if (((this.sc[0] >= 13 && this.sc[0] <= 17) || (this.sc[0] >= 36 && this.sc[0] <= 38)) && this.extpoints[this.sc[0]] < madness[0].level[this.sc[0]] * 2) {
                                plshelp = 1.5;
                            }
                            if ((this.sc[0] == 18 || this.sc[0] == 20 || this.sc[0] == 21) && this.extpoints[this.sc[0]] < (int)(madness[0].level[this.sc[0]] * 2.5)) {
                                plshelp = 1.5;
                            }
                            if ((this.sc[0] == 19 || this.sc[0] == 22) && this.extpoints[this.sc[0]] < madness[0].level[this.sc[0]] * 3) {
                                plshelp = 1.5;
                            }
                        }
                        double extramod3 = 1.0;
                        for (int a9 = 0; a9 < 6; ++a9) {
                            if (this.specialstats[this.sc[0]][1][a9] > 0) {
                                extramod3 = 1.0 + this.specialstats[this.sc[0]][1][a9] / 100.0;
                            }
                        }
                        final double chkproba = (18 + this.clearlimit + bspbuff) * doublechance * plshelp * extramod3;
                        int whichlevel3 = this.startinglevel;
                        if ((checkpoints.stage < this.unlocked[1] || this.bonstage) && madness[0].level[this.sc[0]] >= this.maxlevel[this.unlocked[1] - 2] + 5) {
                            whichlevel3 = madness[0].level[this.sc[0]];
                        }
                        if (this.winchance[0] <= (int)chkproba && whichlevel3 < this.softlevelcap) {
                            this.winchance[1] = (int)(Math.random() * 1000.0) + 1;
                        }
                        else {
                            this.winchance[1] = 1001;
                        }
                        if (!this.noexp) {
                            final int[] winscn = this.winscn;
                            final int n19 = this.sc[0];
                            ++winscn[n19];
                            if (this.winscn[this.sc[0]] % 2 == 0) {
                                this.shwcncnt = true;
                            }
                        }
                    }
                    wintrue = false;
                }
                if (chkfix && this.careermode) {
                    this.shexamtr = true;
                    int actualstage = 1;
                    final int whichlevel = Math.min(madness[this.nplayers - 1].level[this.sc[this.nplayers - 1]], madness[0].level[this.sc[0]]);
                    for (int a5 = 1; a5 < 31; ++a5) {
                        if (a5 < 30) {
                            if (whichlevel >= this.maxlevel[a5 - 1] && whichlevel < this.maxlevel[a5]) {
                                actualstage = a5;
                            }
                        }
                        else if (whichlevel >= this.maxlevel[a5 - 1]) {
                            actualstage = a5;
                        }
                    }
                    if (actualstage > this.unlocked[1]) {
                        actualstage = this.unlocked[1];
                    }
                    this.clearlimit = checkpoints.clear[0];
                    if (this.clearlimit > 25 + (int)(actualstage * 0.5)) {
                        this.clearlimit = 25 + (int)(actualstage * 0.5);
                    }
                    this.chkamount[1] = 67 + actualstage * 7 + this.clearlimit * 6;
                    int stagehigh = actualstage - 10;
                    if (stagehigh < 0) {
                        stagehigh = 0;
                    }
                    final double bonusdiff = 1.0 + stagehigh * 0.06;
                    double winlimit3 = this.winscn[this.sc[0]];
                    double newlimit3 = 1.0;
                    double extranerf = 1.0;
                    if (this.expmult < 1.0) {
                        extranerf = 0.325;
                        newlimit3 = 1.0 / (this.expmult * 4.0);
                        if (newlimit3 > 1.0) {
                            newlimit3 = 1.0;
                        }
                    }
                    if (winlimit3 > 5000.0 / newlimit3) {
                        winlimit3 = 5000.0 / newlimit3;
                    }
                    double killslimit3 = this.killscn[this.sc[0]] * 4.0;
                    if (killslimit3 > 4800.0 / newlimit3) {
                        killslimit3 = 4800.0 / newlimit3;
                    }
                    this.chkmultiplier = 1.0 + (winlimit3 + killslimit3) / 200.0;
                    double extramod4 = 1.0;
                    for (int a10 = 0; a10 < 6; ++a10) {
                        if (this.specialstats[this.sc[0]][2][a10] > 0) {
                            extramod4 = 1.0 + this.specialstats[this.sc[0]][2][a10] / 100.0;
                        }
                    }
                    this.chkamount[0] = (int)(this.chkamount[1] * this.chkmultiplier * bonusdiff * extramod4 * this.expmult * extranerf);
                    if (!this.noexp) {
                        final int[] exp3 = madness[0].exp;
                        final int n20 = this.sc[0];
                        exp3[n20] += this.chkamount[0];
                    }
                    chkfix = false;
                }
                if (!this.mutes && !this.unlimitedlaps && !this.holdit) {
                    this.checkpoint.play();
                }
                this.clear = madness[0].clear;
                if (!this.holdit) {
                    this.cntovn = 0;
                    if (this.cntan != 0) {
                        this.cntan = 0;
                    }
                }
            }
            boolean killtrue = false;
            int k = 0;
            do {
                if (this.dested[k] != checkpoints.dested[k]) {
                    this.dested[k] = checkpoints.dested[k];
                    final String[] namestouse = new String[this.nplayers];
                    final String[] extradetail = new String[this.nplayers];
                    for (int a8 = 0; a8 < this.nplayers; ++a8) {
                        boolean exception = false;
                        if (this.careermode && checkpoints.stage == 11 && !this.bonstage && a8 > 4) {
                            exception = true;
                        }
                        extradetail[a8] = "";
                        if (this.beastopponent[a8]) {
                            extradetail[a8] = "Beast ";
                        }
                        if (conto[a8].floorguardian) {
                            extradetail[a8] = "Guardian ";
                        }
                        if (this.undead[a8] && !exception) {
                            extradetail[a8] = "Undead ";
                        }
                        if (madness[a8].shadowcar) {
                            extradetail[a8] = "Shadow ";
                        }
                        namestouse[a8] = String.valueOf(extradetail[a8]) + this.names[this.sc[a8]];
                    }
                    if (this.dested[k] == 1 && !this.holdit) {
                        this.wasay = true;
                        if (k != 0) {
                            this.say = namestouse[k] + " has been wasted!";
                        }
                        else {
                            this.say = "";
                        }
                        this.tcnt = -15;
                    }
                    double chancemod = 1.0;
                    if (this.dested[k] == 2) {
                        if (!this.holdit) {
                            this.wasay = true;
                            this.say = "You wasted " + namestouse[k] + "!";
                            this.tcnt = -15;
                        }
                        if (this.justcs == -1) {
                            killtrue = true;
                        }
                        this.killfix = true;
                        if (this.killfix && this.careermode) {
                            this.shexamt = true;
                            this.cpn = madness[k].cn;
                            if (this.cpn <= 7) {
                                this.cpn = 0;
                            }
                            if (this.cpn >= 23) {
                                this.cpn = madness[k].cn - 23;
                            }
                            int stopcap = madness[k].level[this.sc[k]];
                            if (stopcap > madness[0].level[this.sc[0]]) {
                                stopcap = madness[0].level[this.sc[0]];
                            }
                            this.wstamount[1] = 300 + this.cpn * 9 + stopcap * 37;
                            double newlimit3 = 1.0;
                            if (this.expmult < 1.0) {
                                newlimit3 = 1.0 / (this.expmult * 4.0);
                                if (newlimit3 > 1.0) {
                                    newlimit3 = 1.0;
                                }
                            }
                            double kilimit = this.killscn[this.sc[0]];
                            if (kilimit > 1200.0 * newlimit3) {
                                kilimit = 1200.0 * newlimit3;
                            }
                            double winslimit = this.winscn[this.sc[0]] / 4.0;
                            if (winslimit > 1250.0 * newlimit3) {
                                winslimit = 1250.0 * newlimit3;
                            }
                            if (this.beastopponent[k]) {
                                if (!this.bonstage) {
                                    this.wstmultiplier = (1.0 + (kilimit + winslimit) / 120.0) * 2.5;
                                    chancemod = 2.55;
                                }
                                else {
                                    this.wstmultiplier = (1.0 + (kilimit + winslimit) / 120.0) * 1.2;
                                    chancemod = 1.3;
                                }
                            }
                            else {
                                this.wstmultiplier = 1.0 + (kilimit + winslimit) / 120.0;
                            }
                            if (madness[k].shadowcar) {
                                this.wstmultiplier = (1.0 + (kilimit + winslimit) / 120.0) * 3.0;
                                chancemod = 2.8;
                            }
                            double extramod4 = 1.0;
                            for (int a10 = 0; a10 < 6; ++a10) {
                                if (this.specialstats[this.sc[0]][2][a10] > 0) {
                                    extramod4 = 1.0 + this.specialstats[this.sc[0]][2][a10] / 100.0;
                                }
                                if (this.specialstats[this.sc[0]][23][a10] > 0) {
                                    this.killtime[0] = 75 + (int)(this.specialstats[this.sc[0]][23][a10] * 3.75);
                                }
                                if (this.specialstats[this.sc[0]][24][a10] > 0) {
                                    this.killtime[1] = 75 + (int)(this.specialstats[this.sc[0]][24][a10] * 3.75);
                                }
                            }
                            this.wstamount[0] = (int)(this.wstamount[1] * this.wstmultiplier * 0.85 * extramod4 * this.expmult);
                            if (!this.noexp) {
                                final int[] exp4 = madness[0].exp;
                                final int n21 = this.sc[0];
                                exp4[n21] += this.wstamount[0];
                            }
                            this.killfix = false;
                        }
                    }
                    if (this.dested[k] > 2 && !this.holdit) {
                        this.wasay = true;
                        String samecar = "";
                        if (this.sc[this.dested[k] - 2] == this.sc[k]) {
                            samecar = "another ";
                        }
                        if (k != 0) {
                            this.say = namestouse[this.dested[k] - 2] + " has wasted " + samecar + namestouse[k] + "!";
                        }
                        else {
                            this.say = namestouse[this.dested[k] - 2] + " has wasted you!";
                        }
                        this.tcnt = -15;
                    }
                    if (killtrue) {
                        ++this.kills;
                        if (this.kills % 2 == 0 && this.careermode) {
                            this.shkcnt = true;
                        }
                        if (!this.careermode && !this.ptmatchend[0]) {
                            this.shkcnt = true;
                        }
                        if (this.careermode && !this.noexp) {
                            final int[] killscn = this.killscn;
                            final int n22 = this.sc[0];
                            ++killscn[n22];
                            this.shkcncnt = true;
                        }
                        if (this.careermode) {
                            if (!this.noexp) {
                                this.killchance[0] = (int)(Math.random() * 1000.0) + 1;
                            }
                            else {
                                this.killchance[0] = 1000000;
                            }
                            int levelbarrier2 = (this.sc[0] - 7) * 5;
                            if (this.sc[0] <= 7 || (this.sc[0] >= 23 && this.sc[0] <= 30)) {
                                levelbarrier2 = 0;
                            }
                            if (this.sc[0] == 20) {
                                levelbarrier2 = 55;
                            }
                            if (this.sc[0] == 21) {
                                levelbarrier2 = 60;
                            }
                            if (this.sc[0] == 22) {
                                levelbarrier2 = 70;
                            }
                            if (this.sc[0] >= 31 && this.sc[0] <= 33) {
                                levelbarrier2 = 15;
                            }
                            if (this.sc[0] >= 34 && this.sc[0] <= 36) {
                                levelbarrier2 = 30;
                            }
                            if (this.sc[0] == 37 || this.sc[0] == 38) {
                                levelbarrier2 = 40;
                            }
                            double doublechance2 = 1.0;
                            if (madness[0].level[this.sc[0]] <= levelbarrier2) {
                                doublechance2 = 1.3;
                            }
                            double plshelp2 = 1.0;
                            if (madness[0].level[this.sc[0]] >= 10) {
                                if ((this.sc[0] < 8 || (this.sc[0] >= 23 && this.sc[0] <= 30)) && this.extpoints[this.sc[0]] < (int)(madness[0].level[this.sc[0]] * 0.5)) {
                                    plshelp2 = 1.5;
                                }
                                if ((this.sc[0] == 8 || this.sc[0] == 9 || this.sc[0] == 10) && this.extpoints[this.sc[0]] < madness[0].level[this.sc[0]]) {
                                    plshelp2 = 1.5;
                                }
                                if ((this.sc[0] == 11 || this.sc[0] == 12 || (this.sc[0] >= 31 && this.sc[0] <= 35)) && this.extpoints[this.sc[0]] < (int)(madness[0].level[this.sc[0]] * 1.5)) {
                                    plshelp2 = 1.5;
                                }
                                if (((this.sc[0] >= 13 && this.sc[0] <= 17) || (this.sc[0] >= 36 && this.sc[0] <= 38)) && this.extpoints[this.sc[0]] < madness[0].level[this.sc[0]] * 2) {
                                    plshelp2 = 1.5;
                                }
                                if ((this.sc[0] == 18 || this.sc[0] == 20 || this.sc[0] == 21) && this.extpoints[this.sc[0]] < (int)(madness[0].level[this.sc[0]] * 2.5)) {
                                    plshelp2 = 1.5;
                                }
                                if ((this.sc[0] == 19 || this.sc[0] == 22) && this.extpoints[this.sc[0]] < madness[0].level[this.sc[0]] * 3) {
                                    plshelp2 = 1.5;
                                }
                            }
                            double extramod5 = 1.0;
                            for (int a11 = 0; a11 < 6; ++a11) {
                                if (this.specialstats[this.sc[0]][1][a11] > 0) {
                                    extramod5 = 1.0 + this.specialstats[this.sc[0]][1][a11] / 100.0;
                                }
                            }
                            double levelboost = 1.0;
                            int leveldiff2 = madness[k].level[this.sc[k]] - madness[0].level[this.sc[0]];
                            if (leveldiff2 > 40) {
                                leveldiff2 = 40;
                            }
                            if (!this.isithard) {
                                leveldiff2 = 0;
                            }
                            if (leveldiff2 > 2) {
                                int bspbuff2 = 5;
                                if (leveldiff2 >= 5 && leveldiff2 <= 10) {
                                    bspbuff2 = leveldiff2;
                                }
                                if (leveldiff2 > 10 && leveldiff2 <= 20) {
                                    bspbuff2 = 10 + (leveldiff2 - 10) * 2;
                                }
                                if (leveldiff2 > 20 && leveldiff2 <= 30) {
                                    bspbuff2 = 30 + (leveldiff2 - 20) * 3;
                                }
                                if (leveldiff2 > 30 && leveldiff2 <= 40) {
                                    bspbuff2 = 60 + (leveldiff2 - 30) * 4;
                                }
                                levelboost = bspbuff2 / 100.0 + 1.0;
                            }
                            final int totalchance = (int)(140.0 * doublechance2 * chancemod * plshelp2 * extramod5 * levelboost);
                            int whichlevel4 = this.startinglevel;
                            if ((checkpoints.stage < this.unlocked[1] || this.bonstage) && madness[0].level[this.sc[0]] >= this.maxlevel[this.unlocked[1] - 2] + 5) {
                                whichlevel4 = madness[0].level[this.sc[0]];
                            }
                            if (this.killchance[0] <= totalchance && whichlevel4 < this.softlevelcap) {
                                this.killchance[1] = (int)(Math.random() * 1000.0) + 1;
                            }
                            else {
                                this.killchance[1] = 1001;
                            }
                        }
                        killtrue = false;
                    }
                    int ac = 0;
                    do {
                        if (this.ptscore1fase[ac] && !this.holdit) {
                            final int[] ptscore1 = this.ptscore1;
                            final int n23 = ac;
                            ++ptscore1[n23];
                            this.ptscore1fase[ac] = false;
                        }
                    } while (++ac < 11);
                }
            } while (++k < this.nplayers);
        }
        if (!this.arrowlocked) {
            for (int a3 = 0; a3 < 7; ++a3) {
                this.alhover[a3] = false;
            }
        }
        if (flag) {
            this.rd.setFont(this.fifa.deriveFont(1, 17.0f));
            this.rd.setColor(new Color(0, 0, 0));
            this.rd.drawString((int)Math.abs(madness[0].speed / 2.0f) + " MPH", 12, 70);
        }
        this.rd.setFont(new Font("Arial", 1, 11));
        this.ftm = this.rd.getFontMetrics();
        for (int a3 = 0; a3 < this.nplayers; ++a3) {
            if (madness[a3].dest || checkpoints.haltall) {
                madness[a3].spatk = 0.0f;
                madness[a3].speclast = 120.0f;
                madness[a3].speclast2 = 120.0f;
                madness[a3].specialact = false;
                this.fixspecials[a3] = false;
            }
        }
        final int[] y = { 218, 0, 0 };
        if (!this.shkcnt) {
            y[1] = 218;
        }
        else {
            y[1] = 241;
        }
        if (!this.shkcnt && !this.shkcncnt) {
            if (this.shwcnt && this.shwcncnt && this.winchance[1] <= 1000) {
                y[2] = 287;
            }
            else if ((this.shwcnt && this.shwcncnt) || (this.shwcnt && this.winchance[1] <= 1000) || (this.shwcncnt && this.winchance[1] <= 1000)) {
                y[2] = 264;
            }
            else if (this.shwcnt || this.shwcncnt || this.winchance[1] <= 1000) {
                y[2] = 241;
            }
            else {
                y[2] = 218;
            }
        }
        else if (this.shkcnt && this.shkcncnt) {
            y[2] = 264;
        }
        else {
            y[2] = 241;
        }
        final int[] z = { 218, 0, 0 };
        if (!this.shwcnt) {
            z[1] = 218;
        }
        else {
            z[1] = 241;
        }
        if (!this.shwcnt && !this.shwcncnt) {
            if (this.shkcnt && this.shkcncnt && this.killchance[1] <= 1000) {
                z[2] = 287;
            }
            else if ((this.shkcnt && this.shkcncnt) || (this.shkcnt && this.killchance[1] <= 1000) || (this.shkcncnt && this.killchance[1] <= 1000)) {
                z[2] = 264;
            }
            else if (this.shkcnt || this.shkcncnt || this.killchance[1] <= 1000) {
                z[2] = 241;
            }
            else {
                z[2] = 218;
            }
        }
        else if (this.shwcnt && this.shwcncnt) {
            z[2] = 264;
        }
        else {
            z[2] = 241;
        }
        final String name = new StringBuilder().append(this.names[this.sc[0]]).toString();
        if (this.shkcnt) {
            this.rd.setFont(this.adventure.deriveFont(1, 16.0f));
            this.rd.setColor(new Color(90, 0, 0));
            if (this.xkcnt[0] < 15 && !this.xmoveback[0]) {
                this.xmove[0] = true;
            }
            else {
                this.xmove[0] = false;
            }
            if (this.xmove[0]) {
                final int[] xkcnt = this.xkcnt;
                final int n24 = 0;
                xkcnt[n24] += 8;
            }
            if (this.xkcnt[0] >= 15) {
                final int[] showfor = this.showfor;
                final int n25 = 0;
                ++showfor[n25];
            }
            if (this.showfor[0] >= 30) {
                this.xmoveback[0] = true;
            }
            else {
                this.xmoveback[0] = false;
            }
            if (this.xmoveback[0]) {
                final int[] xkcnt2 = this.xkcnt;
                final int n26 = 0;
                xkcnt2[n26] -= 8;
            }
            if (this.xkcnt[0] <= -340) {
                this.xmoveback[0] = false;
                this.shkcnt = false;
            }
            this.rd.drawString(this.kills + " TOTAL WASTES", this.xkcnt[0], y[0]);
            this.rd.setFont(new Font("Arial", 1, 11));
            this.ftm = this.rd.getFontMetrics();
            this.shwcnt = false;
            this.shwcncnt = false;
        }
        else {
            this.showfor[0] = 0;
            this.xkcnt[0] = -55;
            this.xmoveback[0] = false;
            this.xmove[0] = false;
        }
        if (this.shkcncnt) {
            this.rd.setFont(this.adventure.deriveFont(1, 16.0f));
            this.rd.setColor(new Color(130, 0, 0));
            if (this.xkcnt[1] < 15 && !this.xmoveback[1]) {
                this.xmove[1] = true;
            }
            else {
                this.xmove[1] = false;
            }
            if (this.xmove[1]) {
                final int[] xkcnt3 = this.xkcnt;
                final int n27 = 1;
                xkcnt3[n27] += 8;
            }
            if (this.xkcnt[1] >= 15) {
                final int[] showfor2 = this.showfor;
                final int n28 = 1;
                ++showfor2[n28];
            }
            if (this.showfor[1] >= 30) {
                this.xmoveback[1] = true;
            }
            else {
                this.xmoveback[1] = false;
            }
            if (this.xmoveback[1]) {
                final int[] xkcnt4 = this.xkcnt;
                final int n29 = 1;
                xkcnt4[n29] -= 8;
            }
            if (this.xkcnt[1] <= -340) {
                this.xmoveback[1] = false;
                this.shkcncnt = false;
            }
            if (this.killscn[this.sc[0]] != 1) {
                this.rd.drawString(this.killscn[this.sc[0]] + " WASTES WITH " + name, this.xkcnt[1], y[1]);
            }
            else {
                this.rd.drawString(this.killscn[this.sc[0]] + " WASTE WITH " + name, this.xkcnt[1], y[1]);
            }
            this.rd.setFont(new Font("Arial", 1, 11));
            this.ftm = this.rd.getFontMetrics();
            this.shwcnt = false;
            this.shwcncnt = false;
        }
        else {
            this.showfor[1] = 0;
            this.xkcnt[1] = -55;
            this.xmoveback[1] = false;
            this.xmove[1] = false;
        }
        int leveldiff3 = this.averagelevel - this.startinglevel;
        if (leveldiff3 > 50) {
            leveldiff3 = 50;
        }
        if (leveldiff3 < 5) {
            leveldiff3 = 5;
        }
        double morechance = 0.0;
        if (this.isithard) {
            morechance = leveldiff3 / 100.0;
        }
        if (this.killchance[1] <= 1000) {
            this.rd.setFont(this.adventure.deriveFont(1, 16.0f));
            this.rd.setColor(new Color(130, 0, 0));
            if (this.xkcnt[2] < 15 && !this.xmoveback[2]) {
                this.xmove[2] = true;
            }
            else {
                this.xmove[2] = false;
            }
            if (this.xmove[2]) {
                final int[] xkcnt5 = this.xkcnt;
                final int n30 = 2;
                xkcnt5[n30] += 8;
            }
            if (this.xkcnt[2] >= 15) {
                final int[] showfor3 = this.showfor;
                final int n31 = 2;
                ++showfor3[n31];
            }
            if (this.showfor[2] >= 30) {
                this.xmoveback[2] = true;
            }
            else {
                this.xmoveback[2] = false;
            }
            if (this.xmoveback[2]) {
                final int[] xkcnt6 = this.xkcnt;
                final int n32 = 2;
                xkcnt6[n32] -= 8;
            }
            if (this.xkcnt[2] <= -340) {
                this.xmoveback[2] = false;
                this.killchance[1] = 1001;
            }
            if (this.killchance[1] <= (int)((0.05 + 0.2 * morechance) * 1000.0)) {
                this.rd.drawString("YOU HAVE GAINED 4 BONUS STAT POINTS!", this.xkcnt[2], y[2] + (int)(Math.random() * 5.0));
                if (!this.wststatgain[this.sc[0]] && !this.losepoints) {
                    final int[] statpoints = this.statpoints;
                    final int n33 = this.sc[0];
                    statpoints[n33] += 4;
                    this.wststatgain[this.sc[0]] = true;
                    final int[] extpoints3 = this.extpoints;
                    final int n34 = this.sc[0];
                    extpoints3[n34] += 4;
                    this.statgain += 4;
                }
            }
            else if (this.killchance[1] <= (int)((0.3 + morechance) * 1000.0)) {
                this.rd.drawString("YOU HAVE GAINED 2 BONUS STAT POINTS!", this.xkcnt[2], y[2] + (int)(Math.random() * 5.0));
                if (!this.wststatgain[this.sc[0]] && !this.losepoints) {
                    final int[] statpoints2 = this.statpoints;
                    final int n35 = this.sc[0];
                    statpoints2[n35] += 2;
                    this.wststatgain[this.sc[0]] = true;
                    final int[] extpoints4 = this.extpoints;
                    final int n36 = this.sc[0];
                    extpoints4[n36] += 2;
                    this.statgain += 2;
                }
            }
            else if (this.killchance[1] <= 1000) {
                this.rd.drawString("YOU HAVE GAINED 1 BONUS STAT POINT!", this.xkcnt[2], y[2] + (int)(Math.random() * 5.0));
                if (!this.wststatgain[this.sc[0]] && !this.losepoints) {
                    final int[] statpoints3 = this.statpoints;
                    final int n37 = this.sc[0];
                    ++statpoints3[n37];
                    this.wststatgain[this.sc[0]] = true;
                    final int[] extpoints5 = this.extpoints;
                    final int n38 = this.sc[0];
                    ++extpoints5[n38];
                    ++this.statgain;
                }
            }
            this.rd.setFont(new Font("Arial", 1, 11));
            this.ftm = this.rd.getFontMetrics();
        }
        else {
            this.showfor[2] = 0;
            this.xkcnt[2] = -55;
            this.xmoveback[2] = false;
            this.xmove[2] = false;
            this.wststatgain[this.sc[0]] = false;
        }
        if (this.shwcnt) {
            this.rd.setFont(this.adventure.deriveFont(1, 16.0f));
            this.rd.setColor(new Color(0, 70, 0));
            if (this.xkcnt[3] < 15 && !this.xmoveback[3]) {
                this.xmove[3] = true;
            }
            else {
                this.xmove[3] = false;
            }
            if (this.xmove[3]) {
                final int[] xkcnt7 = this.xkcnt;
                final int n39 = 3;
                xkcnt7[n39] += 8;
            }
            if (this.xkcnt[3] >= 15) {
                final int[] showfor4 = this.showfor;
                final int n40 = 3;
                ++showfor4[n40];
            }
            if (this.showfor[3] >= 30) {
                this.xmoveback[3] = true;
            }
            else {
                this.xmoveback[3] = false;
            }
            if (this.xmoveback[3]) {
                final int[] xkcnt8 = this.xkcnt;
                final int n41 = 3;
                xkcnt8[n41] -= 8;
            }
            if (this.xkcnt[3] <= -400) {
                this.xmoveback[3] = false;
                this.shwcnt = false;
            }
            this.shkcnt = false;
            this.shkcncnt = false;
            this.rd.drawString(this.wins + " CLEARED IN TOTAL", this.xkcnt[3], z[0]);
            this.rd.setFont(new Font("Arial", 1, 11));
            this.ftm = this.rd.getFontMetrics();
        }
        else {
            this.showfor[3] = 0;
            this.xkcnt[3] = -55;
            this.xmoveback[3] = false;
            this.xmove[3] = false;
        }
        if (this.shwcncnt) {
            this.rd.setFont(this.adventure.deriveFont(1, 16.0f));
            this.rd.setColor(new Color(0, 110, 0));
            if (this.xkcnt[4] < 15 && !this.xmoveback[4]) {
                this.xmove[4] = true;
            }
            else {
                this.xmove[4] = false;
            }
            if (this.xmove[4]) {
                final int[] xkcnt9 = this.xkcnt;
                final int n42 = 4;
                xkcnt9[n42] += 8;
            }
            if (this.xkcnt[4] >= 15) {
                final int[] showfor5 = this.showfor;
                final int n43 = 4;
                ++showfor5[n43];
            }
            if (this.showfor[4] >= 30) {
                this.xmoveback[4] = true;
            }
            else {
                this.xmoveback[4] = false;
            }
            if (this.xmoveback[4]) {
                final int[] xkcnt10 = this.xkcnt;
                final int n44 = 4;
                xkcnt10[n44] -= 8;
            }
            if (this.xkcnt[4] <= -400) {
                this.xmoveback[4] = false;
                this.shwcncnt = false;
            }
            this.rd.drawString(this.winscn[this.sc[0]] + " CLEARED WITH " + name, this.xkcnt[4], z[1]);
            this.shkcnt = false;
            this.shkcncnt = false;
            this.rd.setFont(new Font("Arial", 1, 11));
            this.ftm = this.rd.getFontMetrics();
        }
        else {
            this.showfor[4] = 0;
            this.xkcnt[4] = -55;
            this.xmoveback[4] = false;
            this.xmove[4] = false;
        }
        if (this.winchance[1] <= 1000) {
            this.rd.setFont(this.adventure.deriveFont(1, 16.0f));
            this.rd.setColor(new Color(0, 110, 0));
            if (this.xkcnt[5] < 15 && !this.xmoveback[5]) {
                this.xmove[5] = true;
            }
            else {
                this.xmove[5] = false;
            }
            if (this.xmove[5]) {
                final int[] xkcnt11 = this.xkcnt;
                final int n45 = 5;
                xkcnt11[n45] += 8;
            }
            if (this.xkcnt[5] >= 15) {
                final int[] showfor6 = this.showfor;
                final int n46 = 5;
                ++showfor6[n46];
            }
            if (this.showfor[5] >= 30) {
                this.xmoveback[5] = true;
            }
            else {
                this.xmoveback[5] = false;
            }
            if (this.xmoveback[5]) {
                final int[] xkcnt12 = this.xkcnt;
                final int n47 = 5;
                xkcnt12[n47] -= 8;
            }
            if (this.xkcnt[5] <= -400) {
                this.xmoveback[5] = false;
                this.winchance[1] = 1001;
            }
            if (this.winchance[1] <= (int)((0.05 + 0.2 * morechance) * 1000.0)) {
                this.rd.drawString("YOU HAVE GAINED 4 BONUS STAT POINTS!", this.xkcnt[5], z[2] + (int)(Math.random() * 5.0));
                if (!this.rcestatgain[this.sc[0]] && !this.losepoints) {
                    final int[] statpoints4 = this.statpoints;
                    final int n48 = this.sc[0];
                    statpoints4[n48] += 4;
                    this.rcestatgain[this.sc[0]] = true;
                    final int[] extpoints6 = this.extpoints;
                    final int n49 = this.sc[0];
                    extpoints6[n49] += 4;
                    this.statgain += 4;
                }
            }
            else if (this.winchance[1] <= (int)((0.3 + morechance) * 1000.0)) {
                this.rd.drawString("YOU HAVE GAINED 2 BONUS STAT POINTS!", this.xkcnt[5], z[2] + (int)(Math.random() * 5.0));
                if (!this.rcestatgain[this.sc[0]] && !this.losepoints) {
                    final int[] statpoints5 = this.statpoints;
                    final int n50 = this.sc[0];
                    statpoints5[n50] += 2;
                    final int[] extpoints7 = this.extpoints;
                    final int n51 = this.sc[0];
                    extpoints7[n51] += 2;
                    this.rcestatgain[this.sc[0]] = true;
                    this.statgain += 2;
                }
            }
            else if (this.winchance[1] <= 1000) {
                this.rd.drawString("YOU HAVE GAINED 1 BONUS STAT POINT!", this.xkcnt[5], z[2] + (int)(Math.random() * 5.0));
                if (!this.rcestatgain[this.sc[0]] && !this.losepoints) {
                    final int[] statpoints6 = this.statpoints;
                    final int n52 = this.sc[0];
                    ++statpoints6[n52];
                    final int[] extpoints8 = this.extpoints;
                    final int n53 = this.sc[0];
                    ++extpoints8[n53];
                    ++this.statgain;
                    this.rcestatgain[this.sc[0]] = true;
                }
            }
            this.rd.setFont(new Font("Arial", 1, 11));
            this.ftm = this.rd.getFontMetrics();
        }
        else {
            this.showfor[5] = 0;
            this.xkcnt[5] = -55;
            this.xmoveback[5] = false;
            this.xmove[5] = false;
        }
        if (this.justcs == 6) {
            control.swap = false;
            if (control.attack == 0) {
                this.arrace = false;
            }
            else {
                this.arrace = true;
            }
        }
    }
    
    public void tourney(final Madness[] madness, final CheckPoints checkpoints, final Control control, final ContO[] conto) {
        for (int a = 0; a < 11; ++a) {
            if ((this.ptmatch == 1 && !this.ptmatchend[0]) || (this.ptmatch == 4 && !this.ptmatchend[3])) {
                if (madness[a].dest) {
                    if (!this.ptscore1fase2[a]) {
                        final int[] ptscore1 = this.ptscore1;
                        final int n = a;
                        --ptscore1[n];
                        this.ptscore1fase2[a] = true;
                    }
                    final Madness madness2 = madness[a];
                    ++madness2.revive;
                }
                else {
                    madness[a].revive = 0;
                    this.ptscore1fase2[a] = false;
                }
            }
            if (this.ptmatch == 5 && !this.ptmatchend[4]) {
                if (madness[a].dest && !this.eliminated[a]) {
                    if (!this.ptscore1fase2[a]) {
                        final int[] ptscore2 = this.ptscore1;
                        final int n2 = a;
                        --ptscore2[n2];
                        this.ptscore1fase2[a] = true;
                    }
                    final Madness madness3 = madness[a];
                    ++madness3.revive;
                }
                else {
                    madness[a].revive = 0;
                    this.ptscore1fase2[a] = false;
                }
            }
        }
        for (int a = 0; a < this.nplayers; ++a) {
            this.stataffect[a] = 0.0;
        }
        if ((this.ptmatch == 1 && !this.ptmatchend[0]) || (this.ptmatch == 4 && !this.ptmatchend[3])) {
            for (int a = 0; a < 11; ++a) {
                if (this.ptmatch == 1) {
                    madness[a].spatk = 0.0f;
                }
                this.orderscore[a] = (this.ptscore1[a] + 1) * 1000000 + 100000 - madness[a].hitmag + madness[a].im;
            }
            final int[] ints = { this.orderscore[0], this.orderscore[1], this.orderscore[2], this.orderscore[3], this.orderscore[4], this.orderscore[5], this.orderscore[6], this.orderscore[7], this.orderscore[8], this.orderscore[9], this.orderscore[10] };
            Arrays.sort(ints);
            for (int a2 = 0; a2 < 11; ++a2) {
                if (ints[10] == this.orderscore[a2]) {
                    this.firstkiller = a2;
                    this.position[a2] = 0;
                }
                if (ints[9] == this.orderscore[a2]) {
                    this.secondkiller = a2;
                    this.position[a2] = 1;
                }
                if (ints[8] == this.orderscore[a2]) {
                    this.thirdkiller = a2;
                    this.position[a2] = 2;
                }
                if (ints[7] == this.orderscore[a2]) {
                    this.fourthkiller = a2;
                    this.position[a2] = 3;
                }
                if (ints[6] == this.orderscore[a2]) {
                    this.fifthkiller = a2;
                    this.position[a2] = 4;
                }
                if (ints[5] == this.orderscore[a2]) {
                    this.sixthkiller = a2;
                    this.position[a2] = 5;
                }
                if (ints[4] == this.orderscore[a2]) {
                    this.seventhkiller = a2;
                    this.position[a2] = 6;
                }
                if (ints[3] == this.orderscore[a2]) {
                    this.eighthkiller = a2;
                    this.position[a2] = 7;
                }
                if (ints[2] == this.orderscore[a2]) {
                    this.ninthkiller = a2;
                    this.position[a2] = 8;
                }
                if (ints[1] == this.orderscore[a2]) {
                    this.tenthkiller = a2;
                    this.position[a2] = 9;
                }
                if (ints[0] == this.orderscore[a2]) {
                    this.eleventhkiller = a2;
                    this.position[a2] = 10;
                }
            }
            this.rd.setFont(this.adventure.deriveFont(1, 20.0f));
            this.rd.setColor(new Color(233, 187, 0));
            this.rd.drawString(new StringBuilder().append(this.ptplayers[this.firstkiller]).toString(), 25, 370);
            this.rd.setColor(new Color(170, 170, 170));
            this.rd.drawString(new StringBuilder().append(this.ptplayers[this.secondkiller]).toString(), 25, 400);
            this.rd.setColor(new Color(210, 105, 0));
            this.rd.drawString(new StringBuilder().append(this.ptplayers[this.thirdkiller]).toString(), 25, 430);
            this.rd.setColor(new Color(0, 100, 0));
            this.rd.drawString(new StringBuilder().append(this.ptscore1[this.firstkiller]).toString(), 200, 370);
            this.rd.drawString(new StringBuilder().append(this.ptscore1[this.secondkiller]).toString(), 200, 400);
            this.rd.drawString(new StringBuilder().append(this.ptscore1[this.thirdkiller]).toString(), 200, 430);
            this.rd.drawString(new StringBuilder().append(this.ptscore1[0]).toString(), 200, 460);
            this.rd.setColor(new Color(0, 0, 0));
            this.rd.drawString("Your score:", 25, 460);
            this.rd.setFont(new Font("Arial", 1, 11));
            this.ftm = this.rd.getFontMetrics();
            for (int a2 = 0; a2 < 11; ++a2) {
                if (this.ptscore1[a2] == 7 && this.ptmatch == 1) {
                    this.ptmatchend[0] = true;
                }
                if (this.ptscore1[a2] == 8 && this.ptmatch == 4) {
                    this.ptmatchend[3] = true;
                }
            }
        }
        if ((this.ptmatch == 1 && this.ptmatchend[0]) || (this.ptmatch == 4 && this.ptmatchend[3])) {
            for (int a = 0; a < 11; ++a) {
                madness[a].hitmag = 100000;
            }
            this.rd.setFont(this.adventure.deriveFont(1, 22.0f));
            this.ftm = this.rd.getFontMetrics();
            this.drawcs(150, "MATCH OVER", 105, 0, 0, 3);
            this.drawcs(190, this.ptplayers[this.firstkiller] + "  WON!", 105, 0, 0, 3);
            this.rd.setFont(new Font("Arial", 1, 11));
            this.ftm = this.rd.getFontMetrics();
        }
        if (this.ptmatch == 2) {
            for (int a = 0; a < 11; ++a) {
                if (!this.eliminated[a] && !this.ptmatchend[1]) {
                    madness[a].squash = 0;
                    madness[a].nbsq = 0;
                    madness[a].hitmag = 0;
                    madness[a].cntdest = 0;
                    this.position[a] = checkpoints.pos[a];
                }
                else {
                    madness[a].hitmag = 100000;
                }
                if (checkpoints.pos[a] == 0) {
                    if (checkpoints.clear[a] % 2 == 0 && checkpoints.clear[a] >= 2) {
                        if (!this.eliminateonce) {
                            this.eliminate = true;
                        }
                        else {
                            this.eliminate = false;
                        }
                    }
                    else {
                        this.eliminateonce = false;
                    }
                    if (this.eliminate) {
                        for (int f = 0; f < 11; ++f) {
                            if (a != f && checkpoints.pos[f] == 10 - checkpoints.wasted) {
                                this.position[f] = 10 - checkpoints.wasted;
                                this.eliminated[f] = true;
                            }
                        }
                        this.eliminateonce = true;
                    }
                }
                madness[a].spatk = 0.0f;
            }
            if (this.eliminated[0] || checkpoints.wasted == 10) {
                this.ptmatchend[1] = true;
            }
            if (this.ptmatchend[1]) {
                this.rd.setFont(this.adventure.deriveFont(1, 22.0f));
                this.ftm = this.rd.getFontMetrics();
                this.drawcs(150, "MATCH OVER", 105, 0, 0, 3);
                for (int a = 0; a < 11; ++a) {
                    if (this.position[a] == 0) {
                        this.lewinner = a;
                    }
                }
                this.drawcs(190, this.ptplayers[this.lewinner] + "  WON!", 105, 0, 0, 3);
                this.rd.setFont(new Font("Arial", 1, 11));
                this.ftm = this.rd.getFontMetrics();
            }
        }
        if (this.ptmatch == 3) {
            for (int a = 0; a < 11; ++a) {
                if (!this.ptmatchend[2]) {
                    madness[a].squash = 0;
                    madness[a].nbsq = 0;
                    madness[a].hitmag = 0;
                    madness[a].cntdest = 0;
                    this.position[a] = checkpoints.pos[a];
                }
                else {
                    madness[a].hitmag = 100000;
                }
                madness[a].spatk = 0.0f;
            }
            for (int i = 0; i < 11; ++i) {
                if (checkpoints.clear[i] == checkpoints.nlaps * checkpoints.nsp && checkpoints.pos[i] == 0) {
                    this.ptmatchend[2] = true;
                }
            }
            if (this.ptmatchend[2]) {
                this.rd.setFont(this.adventure.deriveFont(1, 22.0f));
                this.ftm = this.rd.getFontMetrics();
                this.drawcs(150, "MATCH OVER", 105, 0, 0, 3);
                for (int a = 0; a < 11; ++a) {
                    if (this.position[a] == 0) {
                        this.lewinner = a;
                    }
                }
                this.drawcs(190, this.ptplayers[this.lewinner] + "  WON!", 105, 0, 0, 3);
                this.rd.setFont(new Font("Arial", 1, 11));
                this.ftm = this.rd.getFontMetrics();
            }
        }
        if (this.ptmatch == 5) {
            if (!this.ptmatchend[4]) {
                if (!this.eliminate && this.starcnt == 0) {
                    --this.ptimer;
                }
                if (this.ptimer < 0) {
                    this.eliminate = true;
                }
                for (int a = 0; a < 11; ++a) {
                    if (!this.eliminated[a]) {
                        this.orderscore[a] = (this.ptscore1[a] + 1) * 1000000 + 100000 - madness[a].hitmag + madness[a].im;
                    }
                    else {
                        this.orderscore[a] = -119999980 - this.position[a];
                    }
                    if (this.eliminate) {
                        if (this.position[a] == 10 - this.neliminated) {
                            madness[a].hitmag = 100000;
                            this.eliminated[a] = true;
                        }
                        ++this.neliminated;
                        this.ptimer = 1000;
                        this.eliminate = false;
                    }
                }
                final int[] ints = { this.orderscore[0], this.orderscore[1], this.orderscore[2], this.orderscore[3], this.orderscore[4], this.orderscore[5], this.orderscore[6], this.orderscore[7], this.orderscore[8], this.orderscore[9], this.orderscore[10] };
                Arrays.sort(ints);
                for (int a2 = 0; a2 < 11; ++a2) {
                    if (!this.eliminated[a2]) {
                        if (ints[10] == this.orderscore[a2]) {
                            this.firstkiller = a2;
                            this.position[a2] = 0;
                        }
                        if (ints[9] == this.orderscore[a2]) {
                            this.secondkiller = a2;
                            this.position[a2] = 1;
                        }
                        if (ints[8] == this.orderscore[a2]) {
                            this.thirdkiller = a2;
                            this.position[a2] = 2;
                        }
                        if (ints[7] == this.orderscore[a2]) {
                            this.fourthkiller = a2;
                            this.position[a2] = 3;
                        }
                        if (ints[6] == this.orderscore[a2]) {
                            this.fifthkiller = a2;
                            this.position[a2] = 4;
                        }
                        if (ints[5] == this.orderscore[a2]) {
                            this.sixthkiller = a2;
                            this.position[a2] = 5;
                        }
                        if (ints[4] == this.orderscore[a2]) {
                            this.seventhkiller = a2;
                            this.position[a2] = 6;
                        }
                        if (ints[3] == this.orderscore[a2]) {
                            this.eighthkiller = a2;
                            this.position[a2] = 7;
                        }
                        if (ints[2] == this.orderscore[a2]) {
                            this.ninthkiller = a2;
                            this.position[a2] = 8;
                        }
                        if (ints[1] == this.orderscore[a2]) {
                            this.tenthkiller = a2;
                            this.position[a2] = 9;
                        }
                        if (ints[0] == this.orderscore[a2]) {
                            this.eleventhkiller = a2;
                            this.position[a2] = 10;
                        }
                    }
                }
                this.rd.setFont(this.adventure.deriveFont(1, 20.0f));
                this.rd.setColor(new Color(233, 187, 0));
                this.rd.drawString(new StringBuilder().append(this.ptplayers[this.firstkiller]).toString(), 25, 340);
                this.rd.setColor(new Color(170, 170, 170));
                this.rd.drawString(new StringBuilder().append(this.ptplayers[this.secondkiller]).toString(), 25, 370);
                this.rd.setColor(new Color(210, 105, 0));
                this.rd.drawString(new StringBuilder().append(this.ptplayers[this.thirdkiller]).toString(), 25, 400);
                this.rd.setColor(new Color(0, 100, 0));
                this.rd.drawString(new StringBuilder().append(this.ptscore1[this.firstkiller]).toString(), 200, 340);
                this.rd.drawString(new StringBuilder().append(this.ptscore1[this.secondkiller]).toString(), 200, 370);
                this.rd.drawString(new StringBuilder().append(this.ptscore1[this.thirdkiller]).toString(), 200, 400);
                this.rd.drawString(new StringBuilder().append(this.ptscore1[0]).toString(), 200, 430);
                this.rd.setColor(new Color(0, 0, 0));
                this.rd.drawString("Your score:", 25, 430);
                this.rd.setColor(new Color(0, 40, 0));
                this.rd.drawString("TIME:   " + this.ptimer, 25, 465);
                this.rd.setFont(new Font("Arial", 1, 11));
                this.ftm = this.rd.getFontMetrics();
                if (this.dontdisplay) {
                    for (int a2 = 0; a2 < 11; ++a2) {
                        if (madness[a2].revive > 40 && !this.eliminated[a2]) {
                            madness[a2].squash = 0;
                            madness[a2].nbsq = 0;
                            madness[a2].hitmag = 0;
                            madness[a2].cntdest = 0;
                            madness[a2].dest = false;
                            madness[a2].newcar = true;
                        }
                    }
                }
                if (this.neliminated == 10 || this.eliminated[0]) {
                    this.ptmatchend[4] = true;
                }
            }
            else {
                for (int a = 0; a < 11; ++a) {
                    madness[a].hitmag = 100000;
                }
                this.rd.setFont(this.adventure.deriveFont(1, 22.0f));
                this.ftm = this.rd.getFontMetrics();
                this.drawcs(150, "MATCH OVER", 105, 0, 0, 3);
                this.drawcs(190, this.ptplayers[this.firstkiller] + "  WON!", 105, 0, 0, 3);
                this.rd.setFont(new Font("Arial", 1, 11));
                this.ftm = this.rd.getFontMetrics();
            }
        }
    }
    
    public void attack(final Graphics2D rd, final ContO[] conto, final int a, final int b) {
        conto[b].x = conto[a].x;
        conto[b].z = conto[a].z;
        conto[b].y = conto[a].y - 1500;
        ++this.undeadswitch;
    }
    
    public void randtele(final ContO[] conto, final int a, final boolean waster) {
        if (!waster) {
            conto[a].x = conto[a].x - 8500 + (int)(this.m.random() * 17000.0f);
            conto[a].z = conto[a].z - 8500 + (int)(this.m.random() * 17000.0f);
        }
        conto[a].xz = (int)(this.m.random() * 360.0f);
        conto[a].y = -350;
        final int[] glitchtimer = this.glitchtimer;
        ++glitchtimer[a];
    }
    
    public void randtelebig(final ContO[] conto, final int a, final boolean waster) {
        if (!waster) {
            conto[a].x = conto[a].x - 8500 + (int)(this.m.random() * 17000.0f);
            conto[a].z = conto[a].z - 8500 + (int)(this.m.random() * 17000.0f);
            conto[a].y = -7000;
        }
        else {
            conto[a].y = -350;
        }
        conto[a].xz = (int)(this.m.random() * 360.0f);
        final int[] glitchtimer = this.glitchtimer;
        ++glitchtimer[a];
    }
    
    public void teleport(final ContO[] conto, final int user, final int target, final int bemean, final boolean reversing, final int warning) {
        if (warning == 0) {
            this.speedhack[user] = 100;
            int distance = 9000;
            if (bemean == 1) {
                distance = 6500;
            }
            if (bemean == 2) {
                distance = 5000;
            }
            if (bemean == 3) {
                distance = 3200;
            }
            if (bemean > 3) {
                distance = bemean;
            }
            int fix = 0;
            if (this.specialflag[target]) {
                if (conto[target].xz <= 180) {
                    fix = 1;
                }
                else {
                    fix = -1;
                }
            }
            int inc = -1;
            if (this.specialflag[user] && this.specialflag[target]) {
                inc = 1;
            }
            else if (this.specialflag[user] || this.specialflag[target]) {
                inc = 0;
            }
            else {
                inc = 1;
            }
            int reversemod = 0;
            if (reversing) {
                reversemod = 180;
            }
            int rotation = conto[target].xz + 180 * fix + reversemod;
            if (rotation > 360) {
                rotation -= 360;
            }
            conto[user].y = 250 - conto[user].grat;
            if (rotation >= 315 || rotation < 45) {
                conto[user].x = conto[target].x - (int)(this.m.sin(rotation) * distance);
                conto[user].z = conto[target].z + distance;
            }
            if (rotation >= 45 && rotation < 135) {
                conto[user].x = conto[target].x - distance;
                conto[user].z = conto[target].z + (int)(this.m.cos(rotation) * distance);
            }
            if (rotation >= 135 && rotation < 225) {
                conto[user].x = conto[target].x - (int)(this.m.sin(rotation) * distance);
                conto[user].z = conto[target].z - distance;
            }
            if (rotation >= 225 && rotation < 315) {
                conto[user].x = conto[target].x + distance;
                conto[user].z = conto[target].z + (int)(this.m.cos(rotation) * distance);
            }
            int easier = 0;
            if (target != 0) {
                easier = 1;
            }
            int reversefix = 0;
            if (reversing) {
                reversefix = 180;
            }
            conto[user].xz = conto[target].xz + 180 * inc + 180 * easier + reversefix;
            this.teledelay = 0;
        }
        else if (!this.playonce) {
            if (!this.mutes) {
                this.redflash.play();
            }
            this.playonce = true;
        }
    }
    
    public void realwalls(final ContO[] conto, final Madness[] madness, final int i) {
        boolean exceptions = false;
        if (this.bonusstage[5] || i == 9 || i == 10 || this.m.effect[10] || (i == 18 && !this.bonusstage[3]) || this.viewbot) {
            exceptions = true;
        }
        for (int a = 0; a < this.nplayers; ++a) {
            int leeway = 1000;
            if (this.py(conto[a].x / 100, conto[this.wallcode[0]].x / 100, conto[a].z / 100, conto[this.wallcode[2]].z / 100) < 2000) {
                leeway = 100;
            }
            if (this.py(conto[a].x / 100, conto[this.wallcode[0]].x / 100, conto[a].z / 100, conto[this.wallcode[3]].z / 100) < 2000) {
                leeway = 100;
            }
            if (this.py(conto[a].x / 100, conto[this.wallcode[1]].x / 100, conto[a].z / 100, conto[this.wallcode[2]].z / 100) < 2000) {
                leeway = 100;
            }
            if (this.py(conto[a].x / 100, conto[this.wallcode[1]].x / 100, conto[a].z / 100, conto[this.wallcode[3]].z / 100) < 2000) {
                leeway = 100;
            }
            if (!madness[a].dest && (!this.undead[a] || this.bonusstage[3]) && (!exceptions || !this.careermode) && !this.entered[a] && (!this.bonusstage[3] || a > 0 || this.clear >= 46) && !this.ghosttele) {
                if (conto[a].x > conto[this.wallcode[0]].x + leeway) {
                    conto[a].x = conto[this.wallcode[0]].x + leeway;
                }
                if (conto[a].x < conto[this.wallcode[1]].x - leeway) {
                    conto[a].x = conto[this.wallcode[1]].x - leeway;
                }
                if (conto[a].z > conto[this.wallcode[2]].z + leeway) {
                    conto[a].z = conto[this.wallcode[2]].z + leeway;
                }
                if (conto[a].z < conto[this.wallcode[3]].z - leeway) {
                    conto[a].z = conto[this.wallcode[3]].z - leeway;
                }
            }
        }
    }
    
    public int healthcalc(final int initialhealth, final int statpoints, final int carid, final double modifier) {
        int increment = 500;
        if ((int)(initialhealth / 20.0) >= 500) {
            if ((int)(initialhealth / 20.0) <= 1250 || carid == 11 || carid == 13 || carid == 36 || carid == 18 || carid == 19 || carid == 20 || carid == 22) {
                increment = initialhealth / 20;
            }
            else {
                increment = 1250;
            }
        }
        final int endurance = (int)((initialhealth + statpoints * increment) * modifier);
        return endurance;
    }
    
    public void nitroandspecials(final Madness[] madness, final CheckPoints checkpoints, final Control[] control, final ContO[] conto, final int view) {
        this.rd.setColor(new Color(0, 0, 0));
        final int whichstats = this.nplayers - 1;
        if ((this.makebot || this.viewbot) && control[0].lookback == -1) {
            for (int a = 0; a < this.nplayers; ++a) {}
        }
        for (int a = 0; a < this.nplayers; ++a) {
            if ((a != 0 && this.makebot) || (a == 0 && this.viewbot)) {
                conto[a].z = conto[this.wallcode[3]].z - 20000 - a * 1000;
                conto[a].y = -20000 - a * 1000;
            }
        }
        for (int a = 0; a < this.nplayers; ++a) {
            final boolean[] whichdest = new boolean[this.nplayers];
            whichdest[a] = madness[a].dest;
            if (this.careermode && checkpoints.stage == 11 && !this.bonusstage[1] && a != 0) {
                whichdest[a] = madness[a].fakedest;
            }
            if (whichdest[a]) {
                this.fixspecials[a] = false;
                madness[a].specialact = false;
            }
            if (madness[a].shadowcar) {
                this.shadowtrans[a] = 65;
            }
            else {
                this.shadowtrans[a] = 255;
            }
            if (a > 0) {
                if (control[a].needtofix) {
                    if (this.dmgflash[a] >= 210) {
                        this.dflashchange[a] = true;
                    }
                    if (this.dmgflash[a] < 50) {
                        this.dflashchange[a] = false;
                    }
                    if (this.dflashchange[a]) {
                        final int[] dmgflash = this.dmgflash;
                        final int n = a;
                        dmgflash[n] -= 40;
                    }
                    else {
                        final int[] dmgflash2 = this.dmgflash;
                        final int n2 = a;
                        dmgflash2[n2] += 40;
                    }
                    if (this.dmgflash[a] < 0) {
                        this.dmgflash[a] = 0;
                    }
                    if (this.dmgflash[a] > 255) {
                        this.dmgflash[a] = 255;
                    }
                }
                else {
                    this.dmgflash[a] = 0;
                }
                if (this.beastopponent[a]) {
                    madness[a].powerloss[this.sc[a]] = madness[a].powerloss2[this.sc[a]] * 3;
                    madness[a].beast[a] = true;
                    madness[a].multiplier[a] = 2.0;
                }
                else {
                    madness[a].powerloss[this.sc[a]] = madness[a].powerloss2[this.sc[a]];
                    madness[a].beast[a] = false;
                    madness[a].multiplier[a] = 1.0;
                }
            }
            if (!whichdest[a]) {
                checkpoints.dested[a] = 0;
            }
            else {
                madness[a].frozen = false;
                madness[a].strswap = false;
                madness[a].leech = false;
                madness[a].redstr = false;
            }
            if (this.fixspecials[a]) {
                final int[] array = this.timershown[0];
                final int n3 = a;
                ++array[n3];
                if (this.timershown[0][a] >= 100) {
                    this.newtimer[0][a] = true;
                }
                else {
                    this.newtimer[0][a] = false;
                }
            }
            else {
                this.timershown[0][a] = 0;
                this.newtimer[0][a] = false;
            }
            if (madness[a].frozen) {
                final int[] array2 = this.timershown[1];
                final int n4 = a;
                ++array2[n4];
                if (this.timershown[1][a] >= 100) {
                    this.newtimer[1][a] = true;
                }
                else {
                    this.newtimer[1][a] = false;
                }
            }
            else {
                this.timershown[1][a] = 0;
                this.newtimer[1][a] = false;
            }
            if (madness[a].strswap) {
                final int[] array3 = this.timershown[2];
                final int n5 = a;
                ++array3[n5];
                if (this.timershown[2][a] >= 100) {
                    this.newtimer[2][a] = true;
                }
                else {
                    this.newtimer[2][a] = false;
                }
            }
            else {
                this.timershown[2][a] = 0;
                this.newtimer[2][a] = false;
            }
            if (madness[a].redstr) {
                final int[] array4 = this.timershown[3];
                final int n6 = a;
                ++array4[n6];
                if (this.timershown[3][a] >= 100) {
                    this.newtimer[3][a] = true;
                }
                else {
                    this.newtimer[3][a] = false;
                }
            }
            else {
                this.timershown[3][a] = 0;
                this.newtimer[3][a] = false;
            }
            if (madness[a].leech) {
                final int[] array5 = this.timershown[4];
                final int n7 = a;
                ++array5[n7];
                if (this.timershown[4][a] >= 100) {
                    this.newtimer[4][a] = true;
                }
                else {
                    this.newtimer[4][a] = false;
                }
            }
            else {
                this.timershown[4][a] = 0;
                this.newtimer[4][a] = false;
            }
            for (int b = 0; b < this.nplayers; ++b) {
                if (a != b) {
                    if (this.fixspecials[a] && !this.newtimer[0][a]) {
                        this.over[0][a] = true;
                    }
                    else {
                        this.over[0][a] = false;
                    }
                    if (madness[a].frozen && !this.newtimer[1][a]) {
                        this.over[1][a] = true;
                    }
                    else {
                        this.over[1][a] = false;
                    }
                    if (madness[a].strswap && !this.newtimer[2][a]) {
                        this.over[2][a] = true;
                    }
                    else {
                        this.over[2][a] = false;
                    }
                    if (madness[a].redstr && !this.newtimer[3][a]) {
                        this.over[3][a] = true;
                    }
                    else {
                        this.over[3][a] = false;
                    }
                    if (madness[a].leech && !this.newtimer[4][a]) {
                        this.over[4][a] = true;
                    }
                    else {
                        this.over[4][a] = false;
                    }
                    for (int c = 0; c < 5; ++c) {
                        for (int d = 0; d < 5; ++d) {
                            if (c != d) {
                                if (this.over[c][a]) {
                                    if (this.over[c][b]) {
                                        if (this.q[c][a] == this.q[c][b]) {
                                            if (this.timershown[c][a] >= this.timershown[c][b]) {
                                                final int[] array6 = this.q[c];
                                                final int n8 = a;
                                                ++array6[n8];
                                            }
                                            else {
                                                final int[] array7 = this.q[c];
                                                final int n9 = b;
                                                ++array7[n9];
                                            }
                                        }
                                    }
                                    else {
                                        this.q[c][b] = 0;
                                    }
                                    if (this.over[d][a]) {
                                        if (this.q[c][a] == this.q[d][a]) {
                                            if (this.timershown[c][a] >= this.timershown[d][a]) {
                                                final int[] array8 = this.q[c];
                                                final int n10 = a;
                                                ++array8[n10];
                                            }
                                            else {
                                                final int[] array9 = this.q[d];
                                                final int n11 = a;
                                                ++array9[n11];
                                            }
                                        }
                                    }
                                    else {
                                        this.q[d][a] = 0;
                                    }
                                    if (this.over[d][b]) {
                                        if (this.q[c][a] == this.q[d][b]) {
                                            if (this.timershown[c][a] >= this.timershown[d][b]) {
                                                final int[] array10 = this.q[c];
                                                final int n12 = a;
                                                ++array10[n12];
                                            }
                                            else {
                                                final int[] array11 = this.q[d];
                                                final int n13 = b;
                                                ++array11[n13];
                                            }
                                        }
                                    }
                                    else {
                                        this.q[d][b] = 0;
                                    }
                                }
                                else {
                                    this.q[c][a] = 0;
                                }
                            }
                        }
                    }
                }
            }
            if (!whichdest[a]) {
                for (int h = 0; h < 5; ++h) {
                    if (this.over[h][a]) {
                        this.xm[h] = 91 + 18 * this.q[h][a];
                        if (!this.xfadephase) {
                            this.xfade -= 12;
                        }
                        else {
                            this.xfade += 12;
                        }
                        if (this.xfade >= 240) {
                            this.xfadephase = false;
                        }
                        if (this.xfade <= 40) {
                            this.xfadephase = true;
                        }
                    }
                }
                final String[] namestouse = new String[this.nplayers];
                final String[] extradetail = new String[this.nplayers];
                for (int b2 = 0; b2 < this.nplayers; ++b2) {
                    boolean exception = false;
                    if (this.careermode && checkpoints.stage == 11 && !this.bonstage && b2 > 4) {
                        exception = true;
                    }
                    extradetail[b2] = "";
                    if (this.beastopponent[b2]) {
                        extradetail[b2] = "Beast ";
                    }
                    if (conto[b2].floorguardian) {
                        extradetail[b2] = "Guardian ";
                    }
                    if (this.undead[b2] && !exception) {
                        extradetail[b2] = "Undead ";
                    }
                    if (madness[b2].shadowcar) {
                        extradetail[b2] = "Shadow ";
                    }
                    namestouse[b2] = String.valueOf(extradetail[b2]) + this.names[this.sc[b2]];
                    if (this.dontdisplay) {
                        namestouse[b2] = this.ptplayers[b2];
                    }
                }
                if (this.over[0][a] && this.xm[0] < 199) {
                    this.rd.setColor(new Color(100, 0, 0, this.xfade));
                    String grammar = String.valueOf(namestouse[a]) + " activated its special!";
                    if (a == 0) {
                        grammar = "You activated your special!";
                    }
                    this.rd.drawString(new StringBuilder().append(grammar).toString(), 12, this.xm[0]);
                }
                if (this.over[1][a] && this.xm[1] < 199) {
                    String byhowmuch = "";
                    final int reducedby = (int)this.round(100.0 - this.statreduce[a][0] * 100.0, 0);
                    if (this.fixspecials[0] && this.randomcar[0] == a) {
                        byhowmuch = " (-" + reducedby + "%)";
                    }
                    this.rd.setColor(new Color(0, 0, 100, this.xfade));
                    String grammar2 = String.valueOf(namestouse[a]) + " has";
                    if (a == 0) {
                        grammar2 = "You have";
                    }
                    this.rd.drawString(grammar2 + " reduced speed!" + byhowmuch, 12, this.xm[1]);
                }
                if (this.over[2][a] && this.xm[2] < 199) {
                    this.rd.setColor(new Color(0, 50, 0, this.xfade));
                    String subject = namestouse[a];
                    String object = namestouse[this.strswapee[a]];
                    if (this.strswapee[a] == 0) {
                        object = "you";
                    }
                    if (a == 0) {
                        subject = "You have";
                    }
                    this.rd.drawString(subject + " swapped strength with " + object + "!", 12, this.xm[2]);
                }
                if (this.over[3][a] && this.xm[3] < 199) {
                    String byhowmuch = "";
                    final int reducedby = (int)this.round(this.statreduce[a][5] * 100.0, 0);
                    if (this.fixspecials[0] && this.randomcar[0] == a) {
                        byhowmuch = " (-" + reducedby + "%)";
                    }
                    this.rd.setColor(new Color(180, 90, 0, this.xfade));
                    String grammar2 = String.valueOf(namestouse[a]) + " has";
                    if (a == 0) {
                        grammar2 = "You have";
                    }
                    this.rd.drawString(grammar2 + " reduced defence!" + byhowmuch, 12, this.xm[3]);
                }
                if (this.over[4][a] && this.xm[4] < 199) {
                    this.rd.setColor(new Color(100, 75, 0, this.xfade));
                    String grammar = String.valueOf(namestouse[a]) + "'s";
                    if (a == 0) {
                        grammar = "Your";
                    }
                    this.rd.drawString(grammar + " health is being drained!", 12, this.xm[4]);
                }
            }
        }
        int cde = 0;
        do {
            if (madness[cde].specialact) {
                this.fixspecials[cde] = true;
            }
            if (this.fixspecials[cde] && madness[cde].speclast2 == 0.0f) {
                this.fixspecials[cde] = false;
            }
            if (this.fixspecials[cde] && !madness[cde].specialact) {
                madness[cde].specialact = true;
            }
        } while (++cde < this.nplayers);
        int forthebot = 1;
        if (this.makebot) {
            forthebot = 0;
        }
        int ai = forthebot;
        do {
            boolean specception = false;
            if (this.careermode) {
                if ((this.unlocked[1] == 9 || this.hardstage) && checkpoints.stage == 9 && this.sc[ai] == 0) {
                    specception = true;
                }
                if (checkpoints.stage == 13) {
                    if (this.unlocked[1] == 13 || this.hardstage) {
                        if (!madness[ai].teleported && (this.sc[ai] == 14 || this.sc[ai] == 10 || (this.sc[ai] == 11 && !conto[ai].floorguardian && !this.beastopponent[ai]) || this.makebot)) {
                            specception = true;
                        }
                    }
                    else if (!madness[ai].teleported && !this.scalelevels && !this.nolevels && (this.sc[ai] == 14 || this.makebot)) {
                        specception = true;
                    }
                }
            }
            if (madness[ai].spatk == 120.0f && madness[ai].speclast > 0.0f && !specception) {
                control[ai].spatk = true;
            }
            else {
                control[ai].spatk = false;
            }
            if (!specception) {
                if ((madness[ai].spatk == 120.0f && madness[ai].speclast > 0.0f) || (madness[ai].spatk == 120.0f && madness[ai].speclast != 120.0f && madness[ai].speclast > 0.0f)) {
                    madness[ai].specialact = true;
                }
                if (madness[ai].spatk == 120.0f && madness[ai].speclast != 0.0f) {
                    continue;
                }
                madness[ai].specialact = false;
            }
        } while (++ai < this.nplayers);
        if ((madness[0].spatk == 120.0f && control[0].spatk && madness[0].speclast > 0.0f) || (madness[0].spatk == 120.0f && madness[0].speclast != 120.0f && madness[0].speclast > 0.0f)) {
            madness[0].specialact = true;
        }
        if (madness[0].spatk != 120.0f || madness[0].speclast == 0.0f || (madness[0].spatk == 120.0f && madness[0].speclast == 120.0f && !control[0].spatk)) {
            madness[0].specialact = false;
        }
        boolean nodebuff = false;
        int undeadextra = 0;
        if (this.careermode) {
            if ((checkpoints.stage == 5 && !this.bonusstage[0]) || checkpoints.stage == 10 || checkpoints.stage == 14 || this.bonusstage[3]) {
                nodebuff = true;
            }
            if (checkpoints.stage == 17) {
                undeadextra = 3;
            }
            if (checkpoints.stage == 11 && !this.bonusstage[1]) {
                undeadextra = 4;
            }
            if (checkpoints.stage == 23 && (this.unlocked[1] == 23 || this.hardstage)) {
                undeadextra = 1;
                if (this.bossbattle) {
                    nodebuff = true;
                }
            }
        }
        if (checkpoints.wasted >= this.nplayers - 2 - undeadextra) {
            nodebuff = true;
        }
        for (int a2 = 0; a2 < this.nplayers; ++a2) {
            if (this.fixspecials[a2] && !madness[a2].dest) {
                if (this.sc[a2] == 1 || this.sc[a2] == 24 || this.sc[a2] == 2 || this.sc[a2] == 25 || this.sc[a2] == 9 || this.sc[a2] == 32 || this.sc[a2] == 12 || this.sc[a2] == 35 || this.sc[a2] == 16 || this.sc[a2] == 17 || this.sc[a2] == 22) {
                    madness[a2].power = 98.0f;
                }
                if ((this.sc[a2] == 0 || this.sc[a2] == 23 || this.sc[a2] == 7 || this.sc[a2] == 30 || this.sc[a2] == 2 || this.sc[a2] == 25 || this.sc[a2] == 20 || this.sc[a2] == 10 || this.sc[a2] == 33 || this.sc[a2] == 13 || this.sc[a2] == 14 || this.sc[a2] == 37 || this.sc[a2] == 36 || this.sc[a2] == 15 || this.sc[a2] == 17 || this.sc[a2] == 18 || this.sc[a2] == 19 || this.sc[a2] == 22 || this.sc[a2] == 38) && !this.fixhealth[a2][0]) {
                    this.updatehealth[a2] = true;
                    this.proportion[a2] = madness[a2].hitmag / (float)madness[a2].maxmag[this.sc[a2]];
                    this.fixhealth[a2][0] = true;
                }
                if (this.sc[a2] == 0 || this.sc[a2] == 23 || this.sc[a2] == 9 || this.sc[a2] == 32 || this.sc[a2] == 14 || this.sc[a2] == 37 || this.sc[a2] == 19) {
                    if (!nodebuff) {
                        this.randomise(madness, a2, checkpoints.stage);
                        if (this.affected[a2] && !madness[this.randomcar[a2]].frozen) {
                            if (checkpoints.pos[this.randomcar[a2]] > 0) {
                                this.sortpower(a2, this.randomcar[a2], madness);
                                double speedmod = 1.0;
                                if (this.specpower[a2] < 1.0) {
                                    speedmod = 0.01 + 0.2823529411764706 * (this.specpower[a2] - 0.15);
                                }
                                else {
                                    speedmod = 0.25 * ((this.specpower[a2] - 1.0) / 1.25 + 1.0);
                                }
                                if (speedmod > 0.45) {
                                    speedmod = 0.45;
                                }
                                this.statreduce[this.randomcar[a2]][0] = 1.0 - speedmod;
                                madness[this.randomcar[a2]].frozen = true;
                            }
                            else {
                                this.affected[a2] = false;
                                this.doitonce[a2] = false;
                            }
                        }
                    }
                    this.finalfix[0][a2] = true;
                }
                if (this.sc[a2] == 1 || this.sc[a2] == 24) {
                    if (!nodebuff) {
                        this.randomise(madness, a2, checkpoints.stage);
                        if (this.affected[a2]) {
                            madness[this.randomcar[a2]].strswap = true;
                            this.strswapee[this.randomcar[a2]] = a2;
                        }
                        madness[a2].moment[this.sc[a2]] = madness[this.randomcar[a2]].strengthreduce[this.sc[this.randomcar[a2]]];
                        madness[this.randomcar[a2]].moment[this.sc[this.randomcar[a2]]] = madness[a2].strengthreduce[this.sc[a2]];
                        this.correct[a2] = true;
                    }
                    this.finalfix[1][a2] = true;
                }
                if (this.sc[a2] == 2 || this.sc[a2] == 25 || this.sc[a2] == 6 || this.sc[a2] == 29 || this.sc[a2] == 11 || this.sc[a2] == 34 || this.sc[a2] == 16) {
                    if (!nodebuff) {
                        this.randomise(madness, a2, checkpoints.stage);
                        if (this.affected[a2]) {
                            this.sortpower(a2, this.randomcar[a2], madness);
                            madness[this.randomcar[a2]].leech = true;
                        }
                        double beastmod = 1.0;
                        if (this.beastopponent[this.randomcar[a2]]) {
                            beastmod = 1.5;
                        }
                        if (madness[a2].shadowcar) {
                            beastmod = 2.0;
                        }
                        if (this.specpower[a2] < 1.0) {
                            this.drainrate[a2] = madness[this.randomcar[a2]].maxmag[this.sc[this.randomcar[a2]]] / (1500.0 * beastmod) * this.specpower[a2];
                        }
                        else {
                            double elrato = (this.specpower[a2] - 1.0) * 1.5 + 1.0;
                            if (elrato > 2.5 * beastmod) {
                                elrato = 2.5 * beastmod;
                            }
                            this.drainrate[a2] = madness[this.randomcar[a2]].maxmag[this.sc[this.randomcar[a2]]] / (1500.0 * beastmod) * elrato;
                        }
                        if (madness[this.randomcar[a2]].hitmag < (int)(0.9 * madness[this.randomcar[a2]].maxmag[this.sc[this.randomcar[a2]]])) {
                            final Madness madness2 = madness[this.randomcar[a2]];
                            madness2.hitmag += (int)this.drainrate[a2];
                        }
                    }
                    this.finalfix[3][a2] = true;
                }
                if (this.sc[a2] == 3 || this.sc[a2] == 26 || this.sc[a2] == 4 || this.sc[a2] == 27 || this.sc[a2] == 10 || this.sc[a2] == 33 || this.sc[a2] == 12 || this.sc[a2] == 35 || this.sc[a2] == 13 || this.sc[a2] == 36 || this.sc[a2] == 18 || this.sc[a2] == 20) {
                    if (!nodebuff) {
                        if (this.sc[a2] == 3 || this.sc[a2] == 26 || this.sc[a2] == 12 || this.sc[a2] == 35) {
                            for (int d2 = 0; d2 < this.nplayers; ++d2) {
                                if (a2 != d2) {
                                    if (checkpoints.pos[a2] != 0) {
                                        if (checkpoints.pos[d2] == 0 && !this.slowonce[a2]) {
                                            this.randomcar[a2] = d2;
                                            this.slowonce[a2] = true;
                                        }
                                    }
                                    else if (checkpoints.pos[d2] == 1 && !this.slowonce[a2]) {
                                        this.randomcar[a2] = d2;
                                        this.slowonce[a2] = true;
                                    }
                                }
                            }
                            if (madness[this.randomcar[a2]].dest || this.undead[this.randomcar[a2]]) {
                                this.slowonce[a2] = false;
                            }
                            this.affected[a2] = true;
                        }
                        else {
                            this.randomise(madness, a2, checkpoints.stage);
                        }
                        if (this.affected[a2] && !madness[this.randomcar[a2]].redstr) {
                            this.sortpower(a2, this.randomcar[a2], madness);
                            if (!this.fixhealth[this.randomcar[a2]][1]) {
                                if (this.specpower[a2] < 1.0) {
                                    this.healthloss[this.randomcar[a2]] = 0.01 + 0.3411764705882353 * (this.specpower[a2] - 0.15);
                                }
                                else {
                                    this.healthloss[this.randomcar[a2]] = 0.3 * ((this.specpower[a2] - 1.0) / 1.2 + 1.0);
                                }
                                if (this.healthloss[this.randomcar[a2]] > 0.55) {
                                    this.healthloss[this.randomcar[a2]] = 0.55;
                                }
                                this.statreduce[this.randomcar[a2]][5] = this.healthloss[this.randomcar[a2]];
                                this.updatehealth[this.randomcar[a2]] = true;
                                this.proportion[this.randomcar[a2]] = madness[this.randomcar[a2]].hitmag / (float)madness[this.randomcar[a2]].maxmag[this.sc[this.randomcar[a2]]];
                                this.fixhealth[this.randomcar[a2]][1] = true;
                            }
                            madness[this.randomcar[a2]].redstr = true;
                        }
                    }
                    this.finalfix[2][a2] = true;
                }
            }
            else {
                if (this.fixhealth[a2][0]) {
                    this.proportion[a2] = madness[a2].hitmag / (float)madness[a2].maxmag[this.sc[a2]];
                    this.updatehealth[a2] = true;
                    this.fixhealth[a2][0] = false;
                }
                this.correct[a2] = false;
                this.slowonce[a2] = false;
                this.doitonce[a2] = false;
                this.affected[a2] = false;
                for (int b3 = 0; b3 < this.nplayers; ++b3) {
                    if (a2 != b3) {
                        if (this.finalfix[0][a2] && (!this.fixspecials[b3] || (this.fixspecials[b3] && (!this.finalfix[0][b3] || (this.finalfix[0][b3] && this.randomcar[a2] != this.randomcar[b3])))) && madness[this.randomcar[a2]].frozen) {
                            madness[this.randomcar[a2]].frozen = false;
                            this.finalfix[0][a2] = false;
                        }
                        if (this.finalfix[1][a2] && (!this.fixspecials[b3] || (this.fixspecials[b3] && (!this.finalfix[1][b3] || (this.finalfix[1][b3] && this.randomcar[a2] != this.randomcar[b3])))) && madness[this.randomcar[a2]].strswap) {
                            madness[this.randomcar[a2]].strswap = false;
                            this.finalfix[1][a2] = false;
                        }
                        if (this.finalfix[2][a2] && (!this.fixspecials[b3] || (this.fixspecials[b3] && (!this.finalfix[2][b3] || (this.finalfix[2][b3] && this.randomcar[a2] != this.randomcar[b3])))) && madness[this.randomcar[a2]].redstr) {
                            this.healthloss[this.randomcar[a2]] = 0.0;
                            madness[this.randomcar[a2]].redstr = false;
                            if (this.fixhealth[this.randomcar[a2]][1]) {
                                this.updatehealth[this.randomcar[a2]] = true;
                                this.proportion[this.randomcar[a2]] = madness[this.randomcar[a2]].hitmag / (float)madness[this.randomcar[a2]].maxmag[this.sc[this.randomcar[a2]]];
                                this.fixhealth[this.randomcar[a2]][1] = false;
                            }
                            this.finalfix[2][a2] = false;
                        }
                        if (this.finalfix[3][a2] && (!this.fixspecials[b3] || (this.fixspecials[b3] && (!this.finalfix[3][b3] || (this.finalfix[3][b3] && this.randomcar[a2] != this.randomcar[b3])))) && madness[this.randomcar[a2]].leech) {
                            madness[this.randomcar[a2]].leech = false;
                            this.finalfix[3][a2] = false;
                        }
                    }
                }
            }
        }
        for (int a2 = 0; a2 < this.nplayers; ++a2) {
            if (nodebuff) {
                if (madness[a2].frozen) {
                    madness[a2].frozen = false;
                }
                if (madness[a2].redstr) {
                    madness[a2].redstr = false;
                    if (this.fixhealth[a2][1]) {
                        this.updatehealth[a2] = true;
                        this.proportion[a2] = madness[a2].hitmag / (float)madness[a2].maxmag[this.sc[a2]];
                        this.fixhealth[a2][1] = false;
                    }
                    this.healthloss[a2] = 0.0;
                }
                if (madness[a2].leech) {
                    madness[a2].leech = false;
                }
                if (madness[a2].strswap) {
                    madness[a2].strswap = false;
                }
            }
        }
        for (int z = 0; z < this.nplayers; ++z) {
            if (!madness[z].strswap && !this.correct[z]) {
                madness[z].strengthreduce[this.sc[z]] = madness[z].moment[this.sc[z]];
            }
        }
        final int[][] speed = new int[101][3];
        final float[][] accel = new float[101][3];
        final float[] contgrip = new float[101];
        final float[] statairs = new float[101];
        final int[] statairc = new int[101];
        final float[] strength = new float[101];
        final int[] endurance = new int[101];
        final int[] speedcut = new int[101];
        if (this.careermode) {
            if (checkpoints.stage == 20) {
                for (int a3 = 0; a3 < this.nplayers; ++a3) {
                    speedcut[a3] = 400;
                }
            }
            if (this.bonusstage[3]) {
                final double proportion = 0.3 * checkpoints.clear[0] / (checkpoints.nlaps * checkpoints.nsp);
                speedcut[0] = (int)(proportion * (madness[0].nitroswits[this.sc[0]][2] + madness[0].aitssp[this.sc[0]]));
            }
        }
        for (int a3 = 0; a3 < this.nplayers; ++a3) {
            double rufreeze = 1.0;
            double spdboost = 1.0;
            float strboost = 1.0f;
            double fixspd = 1.0;
            float fixstr = 1.0f;
            float firemod = 1.0f;
            float nuclearmod = 1.0f;
            float statfall = 0.0f;
            double statdrop = 0.0;
            if (madness[a3].frozen) {
                rufreeze = this.statreduce[a3][0];
            }
            if (this.careermode) {
                boolean trigstat = false;
                final float health = madness[a3].hitmag / (float)madness[a3].maxmag[this.sc[a3]] * 100.0f;
                if (health >= 80.0f && a3 == 0) {
                    trigstat = true;
                }
                for (int b4 = 0; b4 < 6; ++b4) {
                    if (trigstat) {
                        if (this.specialstats[this.sc[0]][3][b4] > 0) {
                            spdboost = 1.0 + this.specialstats[this.sc[0]][3][b4] / 100.0;
                        }
                        if (this.specialstats[this.sc[0]][9][b4] > 0) {
                            strboost = 1.0f + this.specialstats[this.sc[0]][9][b4] / 100.0f;
                        }
                    }
                    if (a3 == 0 && madness[a3].fixtime > 0) {
                        if (this.specialstats[this.sc[0]][21][b4] > 0) {
                            fixspd = 1.0 + this.specialstats[this.sc[0]][21][b4] / 100.0;
                        }
                        if (this.specialstats[this.sc[0]][22][b4] > 0) {
                            fixstr = 1.0f + this.specialstats[this.sc[0]][22][b4] / 100.0f;
                        }
                    }
                }
                if (checkpoints.stage == 11 && !this.bonusstage[1]) {
                    float goalgrip = 24.2f + (madness[this.nplayers - 1].level[this.sc[this.nplayers - 1]] * 3 - 1) * 0.2f;
                    if (goalgrip > 45.6f) {
                        goalgrip = 45.6f;
                    }
                    float fireaffect = (madness[a3].grip[this.sc[a3]] - goalgrip * 5.0f / 6.0f) * 0.25f / (goalgrip / 6.0f) + 0.75f;
                    if (madness[a3].grip[this.sc[a3]] < goalgrip * 5.0f / 6.0f) {
                        fireaffect = (madness[a3].grip[this.sc[a3]] - goalgrip / 2.0f) * 0.2f / (goalgrip / 3.0f) + 0.55f;
                    }
                    if (fireaffect > 1.0f) {
                        fireaffect = 1.0f;
                    }
                    nuclearmod = fireaffect * fireaffect * 0.9f + 0.1f;
                    final double roundinterval = this.round(nuclearmod * nuclearmod * nuclearmod * 2000.0f, 0);
                    final int redinterval = (int)roundinterval;
                    if (redinterval < 2000) {
                        statdrop = this.statdrain[a3] / (double)redinterval;
                        madness[a3].nuclearmod = nuclearmod;
                    }
                    else {
                        madness[a3].nuclearmod = 1.0f;
                    }
                    statfall = (float)statdrop * 0.075f;
                    final float statfalllimit = (10.0f + (1.0f - nuclearmod) * 400.0f / 9.0f) * 0.01f;
                    if (statfall > statfalllimit) {
                        statfall = statfalllimit;
                    }
                }
                contgrip[a3] = (madness[a3].gripreset[this.sc[a3]] + madness[a3].aigripsp[this.sc[a3]] * 0.2f) * (1.0f - statfall * 0.25f);
                final int maxspeed = (int)(madness[a3].nitroswits[this.sc[a3]][2] + (double)madness[a3].aitssp[this.sc[a3]] - speedcut[a3]);
                speed[a3][0] = madness[a3].nitroswits[this.sc[a3]][0] * maxspeed / madness[a3].nitroswits[this.sc[a3]][2];
                speed[a3][1] = madness[a3].nitroswits[this.sc[a3]][1] * maxspeed / madness[a3].nitroswits[this.sc[a3]][2];
                speed[a3][2] = maxspeed;
                if (speed[a3][0] < 20) {
                    speed[a3][0] = 20;
                }
                if (speed[a3][1] < 20) {
                    speed[a3][1] = 20;
                }
                if (speed[a3][2] < 20) {
                    speed[a3][2] = 20;
                }
                if (checkpoints.stage == 9) {
                    float goalgrip2 = 24.2f + (madness[this.nplayers - 1].level[this.sc[this.nplayers - 1]] * 3 - 1) * 0.2f;
                    if (goalgrip2 > 42.0f) {
                        goalgrip2 = 42.0f;
                    }
                    float gripmod = (madness[a3].grip[this.sc[a3]] - goalgrip2 * 5.0f / 6.0f) * 0.4f / (goalgrip2 / 6.0f) + 0.6f;
                    if (madness[a3].grip[this.sc[a3]] < goalgrip2 * 5.0f / 6.0f) {
                        gripmod = (madness[a3].grip[this.sc[a3]] - goalgrip2 / 2.0f) * 0.35f / (goalgrip2 / 3.0f) + 0.25f;
                    }
                    if (gripmod < 0.25f) {
                        gripmod = 0.25f;
                    }
                    if (gripmod > 1.0f) {
                        gripmod = 1.0f;
                    }
                    final float powermod = gripmod * gripmod;
                    madness[a3].powermulti = 1.0f / powermod;
                    float fireaffect2 = (madness[a3].grip[this.sc[a3]] - goalgrip2 * 5.0f / 6.0f) * 0.25f / (goalgrip2 / 6.0f) + 0.75f;
                    if (madness[a3].grip[this.sc[a3]] < goalgrip2 * 5.0f / 6.0f) {
                        fireaffect2 = (madness[a3].grip[this.sc[a3]] - goalgrip2 / 2.0f) * 0.2f / (goalgrip2 / 3.0f) + 0.55f;
                    }
                    if (fireaffect2 > 1.0f) {
                        fireaffect2 = 1.0f;
                    }
                    firemod = fireaffect2 * fireaffect2 * 0.645f + 0.355f;
                    if (firemod < 0.55f) {
                        firemod = 0.55f;
                    }
                }
                final float maxaccel = (madness[a3].nitroacelf[this.sc[a3]][0] + madness[a3].aiaccsp[this.sc[a3]] / 10.0f) * ((1.0f - firemod) / 2.0f + firemod) * (1.0f - statfall * 0.5f);
                accel[a3][0] = maxaccel;
                accel[a3][1] = madness[a3].nitroacelf[this.sc[a3]][1] * maxaccel / madness[a3].nitroacelf[this.sc[a3]][0];
                accel[a3][2] = madness[a3].nitroacelf[this.sc[a3]][2] * maxaccel / madness[a3].nitroacelf[this.sc[a3]][0];
                statairs[a3] = (madness[a3].airsreset[this.sc[a3]] + madness[a3].aistusp[this.sc[a3]] * 0.025f) * (1.0f - statfall);
                statairc[a3] = (int)((madness[a3].aircreset[this.sc[a3]] + madness[a3].aistusp[this.sc[a3]]) * (1.0f - statfall));
                strength[a3] = madness[a3].momentreset[this.sc[a3]] + madness[a3].aistrsp[this.sc[a3]] * 0.025f;
                endurance[a3] = this.healthcalc(madness[a3].healthreset[this.sc[a3]], madness[a3].aiendsp[this.sc[a3]], madness[a3].cn, firemod);
            }
            else {
                speed[a3][0] = (int)(madness[a3].nitroswits[this.sc[a3]][0] * (1.0 + this.stataffect[a3]));
                speed[a3][1] = (int)(madness[a3].nitroswits[this.sc[a3]][1] * (1.0 + this.stataffect[a3]));
                speed[a3][2] = (int)(madness[a3].nitroswits[this.sc[a3]][2] * (1.0 + this.stataffect[a3]));
                accel[a3][0] = (float)(madness[a3].nitroacelf[this.sc[a3]][0] * (1.0 + this.stataffect[a3]));
                accel[a3][1] = (float)(madness[a3].nitroacelf[this.sc[a3]][1] * (1.0 + this.stataffect[a3]));
                accel[a3][2] = (float)(madness[a3].nitroacelf[this.sc[a3]][2] * (1.0 + this.stataffect[a3]));
                contgrip[a3] = (float)(madness[a3].gripreset[this.sc[a3]] * (1.0 + this.stataffect[a3]));
                statairs[a3] = (float)(madness[a3].airsreset[this.sc[a3]] * (1.0 + this.stataffect[a3]));
                statairc[a3] = (int)(madness[a3].aircreset[this.sc[a3]] * (1.0 + this.stataffect[a3]));
                strength[a3] = (float)(madness[a3].momentreset[this.sc[a3]] * (1.0 + this.stataffect[a3]));
                endurance[a3] = (int)(madness[a3].healthreset[this.sc[a3]] * (1.0 + this.stataffect[a3]));
            }
            final double spdmod = 1.0 + (spdboost - 1.0) + (fixspd - 1.0) - (1.0 - rufreeze);
            final float strmod = 1.0f + (fixstr - 1.0f) + (strboost - 1.0f);
            double spdspboost = 0.0;
            float strspboost = 0.0f;
            if (!this.fixspecials[a3]) {
                for (int b5 = 0; b5 < 3; ++b5) {
                    madness[a3].acelf[this.sc[a3]][b5] = accel[a3][b5];
                }
                madness[a3].grip[this.sc[a3]] = contgrip[a3];
                madness[a3].airs[this.sc[a3]] = statairs[a3];
                madness[a3].airc[this.sc[a3]] = statairc[a3];
                this.healthmulti[a3] = 1.0f;
            }
            else {
                double specialboost = 1.0;
                if (this.careermode && a3 == 0) {
                    for (int b6 = 0; b6 < 6; ++b6) {
                        if (this.specialstats[this.sc[0]][20][b6] > 0) {
                            specialboost = 1.0 + this.specialstats[this.sc[0]][20][b6] * 1.25 / 100.0;
                        }
                    }
                }
                if (this.sc[a3] == 3 || this.sc[a3] == 8 || this.sc[a3] == 10 || this.sc[a3] == 15 || this.sc[a3] == 11) {
                    spdspboost = 0.3 * specialboost;
                }
                if (this.sc[a3] == 26) {
                    spdspboost = 0.35 * specialboost;
                }
                if (this.sc[a3] == 12) {
                    spdspboost = 0.25 * specialboost;
                }
                if (this.sc[a3] == 16 || this.sc[a3] == 17 || this.sc[a3] == 14 || this.sc[a3] == 37 || this.sc[a3] == 22 || this.sc[a3] == 6 || this.sc[a3] == 29 || this.sc[a3] == 1 || this.sc[a3] == 24) {
                    spdspboost = 0.15 * specialboost;
                }
                if (this.sc[a3] == 9 || this.sc[a3] == 32 || this.sc[a3] == 19) {
                    spdspboost = 0.1 * specialboost;
                }
                if (this.sc[a3] == 20) {
                    spdspboost = -0.1 * specialboost;
                }
                if (this.sc[a3] == 18 || this.sc[a3] == 31 || this.sc[a3] == 33 || this.sc[a3] == 35 || this.sc[a3] == 34 || this.sc[a3] == 38) {
                    spdspboost = 0.2 * specialboost;
                }
                if (this.sc[a3] == 5 || this.sc[a3] == 28) {
                    if (madness[a3].hitmag * 2 < madness[a3].maxmag[this.sc[a3]]) {
                        spdspboost = 0.25 * specialboost;
                    }
                    else {
                        spdspboost = 0.5 * specialboost;
                    }
                }
                float maxaccel2 = accel[a3][0];
                if (this.sc[a3] == 15) {
                    maxaccel2 = accel[a3][0] * (1.0f + 0.3f * (float)specialboost);
                }
                if (this.sc[a3] == 38) {
                    maxaccel2 = accel[a3][0] * (1.0f + 0.2f * (float)specialboost);
                }
                if (this.sc[a3] == 22) {
                    maxaccel2 = accel[a3][0] * (1.0f + 1.0f * (float)specialboost);
                }
                madness[a3].acelf[this.sc[a3]][0] = maxaccel2;
                madness[a3].acelf[this.sc[a3]][1] = madness[a3].nitroacelf[this.sc[a3]][1] * maxaccel2 / madness[a3].nitroacelf[this.sc[a3]][0];
                madness[a3].acelf[this.sc[a3]][2] = madness[a3].nitroacelf[this.sc[a3]][2] * maxaccel2 / madness[a3].nitroacelf[this.sc[a3]][0];
                madness[a3].grip[this.sc[a3]] = contgrip[a3];
                madness[a3].airs[this.sc[a3]] = statairs[a3];
                madness[a3].airc[this.sc[a3]] = statairc[a3];
                if (this.sc[a3] == 8) {
                    final float contstat = (contgrip[a3] - 10.0f) * 5.0f * (1.0f + 1.0f * (float)specialboost);
                    final float backtogrip = contstat * 0.2f + 10.0f;
                    madness[a3].grip[this.sc[a3]] = backtogrip;
                }
                if (this.sc[a3] == 31) {
                    final float contstat = (contgrip[a3] - 10.0f) * 5.0f * (1.0f + 0.75f * (float)specialboost);
                    final float backtogrip = contstat * 0.2f + 10.0f;
                    madness[a3].grip[this.sc[a3]] = backtogrip;
                }
                if (this.sc[a3] == 16) {
                    final float contstat = (contgrip[a3] - 10.0f) * 5.0f * (1.0f + 0.5f * (float)specialboost);
                    final float backtogrip = contstat * 0.2f + 10.0f;
                    madness[a3].grip[this.sc[a3]] = backtogrip;
                }
                if (this.sc[a3] == 15) {
                    final float contstat = (contgrip[a3] - 10.0f) * 5.0f * (1.0f + 0.3f * (float)specialboost);
                    final float backtogrip = contstat * 0.2f + 10.0f;
                    madness[a3].grip[this.sc[a3]] = backtogrip;
                }
                if (this.sc[a3] == 38) {
                    final float contstat = (contgrip[a3] - 10.0f) * 5.0f * (1.0f + 0.2f * (float)specialboost);
                    final float backtogrip = contstat * 0.2f + 10.0f;
                    madness[a3].grip[this.sc[a3]] = backtogrip;
                }
                if (this.sc[a3] == 17 || this.sc[a3] == 7 || this.sc[a3] == 30 || this.sc[a3] == 37) {
                    this.healthmulti[a3] = 1.0f + 0.5f * (float)specialboost;
                }
                if (this.sc[a3] == 0 || this.sc[a3] == 23 || this.sc[a3] == 36 || this.sc[a3] == 22 || this.sc[a3] == 33 || this.sc[a3] == 2 || this.sc[a3] == 25 || this.sc[a3] == 19) {
                    this.healthmulti[a3] = 1.0f + 0.3f * (float)specialboost;
                }
                if (this.sc[a3] == 10 || this.sc[a3] == 13 || this.sc[a3] == 20) {
                    this.healthmulti[a3] = 1.0f + 0.4f * (float)specialboost;
                }
                if (this.sc[a3] == 14) {
                    this.healthmulti[a3] = 1.0f + 0.7f * (float)specialboost;
                }
                if (this.sc[a3] == 18) {
                    this.healthmulti[a3] = 1.0f + 0.25f * (float)specialboost;
                }
                if (this.sc[a3] == 3) {
                    madness[a3].airs[this.sc[a3]] = statairs[a3] * (1.0f + 0.75f * (float)specialboost);
                    madness[a3].airc[this.sc[a3]] = (int)(statairc[a3] * (1.0 + 0.75 * specialboost));
                }
                if (this.sc[a3] == 26) {
                    madness[a3].airs[this.sc[a3]] = statairs[a3] * (1.0f + 0.6f * (float)specialboost);
                    madness[a3].airc[this.sc[a3]] = (int)(statairc[a3] * (1.0 + 0.6 * specialboost));
                }
                if (this.sc[a3] == 14) {
                    madness[a3].airs[this.sc[a3]] = statairs[a3] * (1.0f + 1.0f * (float)specialboost);
                    madness[a3].airc[this.sc[a3]] = (int)(statairc[a3] * (1.0 + 1.0 * specialboost));
                }
                if (this.sc[a3] == 15) {
                    madness[a3].airs[this.sc[a3]] = statairs[a3] * (1.0f + 0.3f * (float)specialboost);
                    madness[a3].airc[this.sc[a3]] = (int)(statairc[a3] * (1.0 + 0.3 * specialboost));
                    this.healthmulti[a3] = 1.0f + 0.3f * (float)specialboost;
                }
                if (this.sc[a3] == 38) {
                    madness[a3].airs[this.sc[a3]] = statairs[a3] * (1.0f + 0.2f * (float)specialboost);
                    madness[a3].airc[this.sc[a3]] = (int)(statairc[a3] * (1.0 + 0.2 * specialboost));
                    this.healthmulti[a3] = 1.0f + 0.2f * (float)specialboost;
                }
                if (!madness[a3].strswap) {
                    if (this.sc[a3] == 0 || this.sc[a3] == 6 || this.sc[a3] == 29) {
                        strspboost = 0.5f * (float)specialboost;
                    }
                    if (this.sc[a3] == 23) {
                        strspboost = 0.55f * (float)specialboost;
                    }
                    if (this.sc[a3] == 4 || this.sc[a3] == 27) {
                        strspboost = 0.7f * (float)specialboost;
                    }
                    if (this.sc[a3] == 20 || this.sc[a3] == 7 || this.sc[a3] == 30) {
                        strspboost = 0.6f * (float)specialboost;
                    }
                    if (this.sc[a3] == 22) {
                        strspboost = 0.15f * (float)specialboost;
                    }
                    if (this.sc[a3] == 19 || this.sc[a3] == 38) {
                        strspboost = 0.2f * (float)specialboost;
                    }
                    if (this.sc[a3] == 14 || this.sc[a3] == 32 || this.sc[a3] == 13 || this.sc[a3] == 11 || this.sc[a3] == 9 || this.sc[a3] == 31) {
                        strspboost = 0.4f * (float)specialboost;
                    }
                    if (this.sc[a3] == 5 || this.sc[a3] == 28) {
                        if (madness[a3].hitmag * 2 < madness[a3].maxmag[this.sc[a3]]) {
                            strspboost = 0.3f * (float)specialboost;
                        }
                        else {
                            strspboost = 0.6f * (float)specialboost;
                        }
                    }
                    if (this.sc[a3] == 33 || this.sc[a3] == 15 || this.sc[a3] == 16 || this.sc[a3] == 34 || this.sc[a3] == 36) {
                        strspboost = 0.3f * (float)specialboost;
                    }
                    if (this.sc[a3] == 37 || this.sc[a3] == 10) {
                        strspboost = 0.35f * (float)specialboost;
                    }
                    if (this.sc[a3] == 2 || this.sc[a3] == 25 || this.sc[a3] == 8) {
                        strspboost = 0.45f * (float)specialboost;
                    }
                    if (this.sc[a3] == 18) {
                        strspboost = 0.25f * (float)specialboost;
                    }
                }
            }
            final float totalhealth = this.healthmulti[a3] - (float)this.healthloss[a3];
            madness[a3].maxmag[this.sc[a3]] = (int)(endurance[a3] * totalhealth);
            if (this.updatehealth[a3]) {
                madness[a3].hitmag = (int)(this.proportion[a3] * madness[a3].maxmag[this.sc[a3]]);
                this.updatehealth[a3] = false;
            }
            final double totalspd = spdmod + spdspboost;
            final int maxspeed2 = (int)(speed[a3][2] * totalspd);
            madness[a3].swits[this.sc[a3]][0] = madness[a3].nitroswits[this.sc[a3]][0] * maxspeed2 / madness[a3].nitroswits[this.sc[a3]][2];
            madness[a3].swits[this.sc[a3]][1] = madness[a3].nitroswits[this.sc[a3]][1] * maxspeed2 / madness[a3].nitroswits[this.sc[a3]][2];
            madness[a3].swits[this.sc[a3]][2] = maxspeed2;
            final float totalstr = strmod + strspboost;
            if (!madness[a3].strswap && !this.correct[a3]) {
                madness[a3].moment[this.sc[a3]] = strength[a3] * totalstr;
            }
            if (this.fixspecials[a3]) {
                conto[a3].spatk = true;
            }
            else {
                conto[a3].spatk = false;
            }
            if (madness[a3].frozen) {
                conto[a3].freeze = true;
            }
            else {
                conto[a3].freeze = false;
            }
            if (madness[a3].redstr) {
                conto[a3].weaken = true;
            }
            else {
                conto[a3].weaken = false;
            }
            if (madness[a3].leech) {
                conto[a3].leech = true;
            }
            else {
                conto[a3].leech = false;
            }
            if (madness[a3].strswap) {
                conto[a3].strswap = true;
            }
            else {
                conto[a3].strswap = false;
            }
            if (this.newflame[a3]) {
                conto[a3].greenflame = true;
            }
            else {
                conto[a3].greenflame = false;
            }
            double origspeed = madness[a3].nitroswits[this.sc[a3]][2] + (double)madness[a3].aitssp[this.sc[a3]];
            double origaccel = madness[a3].nitroacelf[this.sc[a3]][0] + madness[a3].aiaccsp[this.sc[a3]] / 10.0;
            double origgrip = madness[a3].gripreset[this.sc[a3]] + madness[a3].aigripsp[this.sc[a3]] * 0.2;
            double origstunt = madness[a3].airsreset[this.sc[a3]] + madness[a3].aistusp[this.sc[a3]] * 0.025;
            double origstr = madness[a3].momentreset[this.sc[a3]] + madness[a3].aistrsp[this.sc[a3]] * 0.025;
            double origdef = this.healthcalc(madness[a3].healthreset[this.sc[a3]], madness[a3].aiendsp[this.sc[a3]], this.sc[a3], 1.0);
            if (!this.careermode) {
                origspeed = madness[a3].nitroswits[this.sc[a3]][2] * (1.0 + this.stataffect[a3]);
                origaccel = madness[a3].nitroacelf[this.sc[a3]][0] * (1.0 + this.stataffect[a3]);
                origgrip = madness[a3].gripreset[this.sc[a3]] * (1.0 + this.stataffect[a3]);
                origstunt = madness[a3].airsreset[this.sc[a3]] * (1.0 + this.stataffect[a3]);
                origstr = madness[a3].momentreset[this.sc[a3]] * (1.0 + this.stataffect[a3]);
                origdef = madness[a3].healthreset[this.sc[a3]] * (1.0 + this.stataffect[a3]);
            }
            final double origcont = (origgrip - 10.0) * 5.0;
            this.statmod[a3][0] = this.round(100.0 * madness[a3].swits[this.sc[a3]][2] / origspeed, 0);
            this.statmod[a3][1] = this.round(100.0 * madness[a3].acelf[this.sc[a3]][0] / origaccel, 0);
            final double newcont = (madness[a3].grip[this.sc[a3]] - 10.0) * 5.0;
            this.statmod[a3][2] = this.round(100.0 * newcont / origcont, 0);
            this.statmod[a3][3] = this.round(100.0 * madness[a3].airs[this.sc[a3]] / origstunt, 0);
            this.statmod[a3][4] = this.round(100.0 * madness[a3].moment[this.sc[a3]] / origstr, 0);
            this.statmod[a3][5] = this.round(100.0 * madness[a3].maxmag[this.sc[a3]] / origdef, 0);
        }
        final int[][] buffcolour = new int[6][3];
        final Polygon[] buffpoly = new Polygon[6];
        final String[] statnames = { "spd", "acc", "con", "stu", "str", "def" };
        if (!madness[0].dest) {
            for (int a4 = 0; a4 < 6; ++a4) {
                if (a4 == 0) {
                    for (int b7 = 0; b7 < 6; ++b7) {
                        this.statchanges[b7] = b7;
                    }
                }
                if (this.statmod[0][a4] == 100.0) {
                    for (int b7 = a4 + 1; b7 < 6; ++b7) {
                        if (this.statchanges[b7] > 0) {
                            final int[] statchanges = this.statchanges;
                            final int n14 = b7;
                            --statchanges[n14];
                        }
                    }
                }
                else {
                    final int difference = Math.abs((int)this.statmod[0][a4] - 100);
                    String statsign = "+ ";
                    if (this.statmod[0][a4] < 100.0) {
                        statsign = "- ";
                        buffcolour[a4][0] = (int)(120.0f + 120.0f * (this.m.snap[0] / 100.0f));
                        buffcolour[a4][1] = 0;
                        buffcolour[a4][2] = 0;
                    }
                    else {
                        buffcolour[a4][0] = 0;
                        buffcolour[a4][1] = (int)(120.0f + 120.0f * (this.m.snap[1] / 100.0f));
                        buffcolour[a4][2] = 0;
                    }
                    for (int b8 = 0; b8 < 3; ++b8) {
                        if (buffcolour[a4][b8] > 255) {
                            buffcolour[a4][b8] = 255;
                        }
                        if (buffcolour[a4][b8] < 0) {
                            buffcolour[a4][b8] = 0;
                        }
                    }
                    this.rd.setColor(new Color(buffcolour[a4][0], buffcolour[a4][1], buffcolour[a4][2], 175));
                    int extdigits = 0;
                    if (difference >= 100) {
                        if (difference < 1000) {
                            extdigits = 1;
                        }
                        else {
                            extdigits = 2;
                        }
                    }
                    (buffpoly[a4] = new Polygon()).addPoint(24, 293 + this.statchanges[a4] * 21);
                    buffpoly[a4].addPoint(97 + extdigits * 6, 293 + this.statchanges[a4] * 21);
                    buffpoly[a4].addPoint(101 + extdigits * 6, 302 + this.statchanges[a4] * 21);
                    buffpoly[a4].addPoint(97 + extdigits * 6, 311 + this.statchanges[a4] * 21);
                    buffpoly[a4].addPoint(24, 311 + this.statchanges[a4] * 21);
                    buffpoly[a4].addPoint(20, 302 + this.statchanges[a4] * 21);
                    buffpoly[a4].addPoint(24, 293 + this.statchanges[a4] * 21);
                    this.rd.fillPolygon(buffpoly[a4]);
                    this.rd.setColor(new Color(255, 255, 255));
                    this.rd.setFont(this.adventure.deriveFont(1, 10.5f));
                    this.rd.drawString(statsign + difference + "% " + statnames[a4], 28, 306 + this.statchanges[a4] * 21);
                }
            }
        }
        final int[][] conditionc = { { 185, 0, 0 }, { 0, 0, 185 }, { 100, 75, 0 }, { 240, 120, 0 }, { 0, 150, 0 } };
        final int glowfor = 5;
        for (int a5 = 0; a5 < this.nplayers; ++a5) {
            this.condition[a5][0] = conto[a5].spatk;
            this.condition[a5][1] = conto[a5].freeze;
            this.condition[a5][2] = conto[a5].leech;
            this.condition[a5][3] = conto[a5].weaken;
            this.condition[a5][4] = conto[a5].strswap;
            for (int b8 = 0; b8 < 3; ++b8) {
                if (this.m.effect[5]) {
                    conto[a5].spec[b8] = 150;
                }
                if (this.m.effect[11]) {
                    conto[a5].spec[b8] = 150;
                    if (conto[a5].outoftrack) {
                        conto[a5].spec[b8] = 250;
                    }
                }
                if (madness[a5].dest) {
                    conto[a5].spec[b8] = 0;
                }
            }
            for (int c2 = 0; c2 < 5; ++c2) {
                for (int c3 = 0; c3 < 5; ++c3) {
                    for (int c4 = 0; c4 < 5; ++c4) {
                        for (int c5 = 0; c5 < 5; ++c5) {
                            for (int c6 = 0; c6 < 5; ++c6) {
                                if (c2 != c3 && c2 != c4 && c2 != c5 && c2 != c6 && c3 != c4 && c3 != c5 && c3 != c6 && c4 != c5 && c4 != c6 && c5 != c6) {
                                    if (this.condition[a5][c2] && !this.condition[a5][c3] && !this.condition[a5][c4] && !this.condition[a5][c5] && !this.condition[a5][c6]) {
                                        this.sametime[a5] = 1;
                                        for (int c7 = 0; c7 < 3; ++c7) {
                                            this.refcol[a5][0][c7] = conditionc[c2][c7];
                                        }
                                    }
                                    if (this.condition[a5][c2] && this.condition[a5][c3] && !this.condition[a5][c4] && !this.condition[a5][c5] && !this.condition[a5][c6]) {
                                        this.sametime[a5] = 2;
                                        for (int c7 = 0; c7 < 3; ++c7) {
                                            this.refcol[a5][0][c7] = conditionc[c2][c7];
                                            this.refcol[a5][1][c7] = conditionc[c3][c7];
                                        }
                                    }
                                    if (this.condition[a5][c2] && this.condition[a5][c3] && this.condition[a5][c4] && !this.condition[a5][c5] && !this.condition[a5][c6]) {
                                        this.sametime[a5] = 3;
                                        for (int c7 = 0; c7 < 3; ++c7) {
                                            this.refcol[a5][0][c7] = conditionc[c2][c7];
                                            this.refcol[a5][1][c7] = conditionc[c3][c7];
                                            this.refcol[a5][2][c7] = conditionc[c4][c7];
                                        }
                                    }
                                    if (this.condition[a5][c2] && this.condition[a5][c3] && this.condition[a5][c4] && this.condition[a5][c5] && !this.condition[a5][c6]) {
                                        this.sametime[a5] = 4;
                                        for (int c7 = 0; c7 < 3; ++c7) {
                                            this.refcol[a5][0][c7] = conditionc[c2][c7];
                                            this.refcol[a5][1][c7] = conditionc[c3][c7];
                                            this.refcol[a5][2][c7] = conditionc[c4][c7];
                                            this.refcol[a5][3][c7] = conditionc[c5][c7];
                                        }
                                    }
                                    if (this.condition[a5][c2] && this.condition[a5][c3] && this.condition[a5][c4] && this.condition[a5][c5] && this.condition[a5][c6]) {
                                        this.sametime[a5] = 5;
                                        for (int c7 = 0; c7 < 3; ++c7) {
                                            this.refcol[a5][0][c7] = conditionc[c2][c7];
                                            this.refcol[a5][1][c7] = conditionc[c3][c7];
                                            this.refcol[a5][2][c7] = conditionc[c4][c7];
                                            this.refcol[a5][3][c7] = conditionc[c5][c7];
                                            this.refcol[a5][4][c7] = conditionc[c6][c7];
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (!this.condition[a5][0] && !this.condition[a5][1] && !this.condition[a5][2] && !this.condition[a5][3] && !this.condition[a5][4]) {
                this.sametime[a5] = 0;
            }
            if (this.sametime[a5] == 1) {
                for (int c8 = 0; c8 < 3; ++c8) {
                    conto[a5].spec[c8] = this.refcol[a5][0][c8];
                }
            }
            if (this.sametime[a5] <= 1) {
                this.spglow[a5] = 0;
                this.spglowchange[a5] = 0;
            }
            if (this.sametime[a5] > 1) {
                if (this.spglow[a5] < glowfor) {
                    final int[] spglow = this.spglow;
                    final int n15 = a5;
                    ++spglow[n15];
                }
                else {
                    this.spglow[a5] = 0;
                    if (this.spglowchange[a5] < this.sametime[a5] - 1) {
                        final int[] spglowchange = this.spglowchange;
                        final int n16 = a5;
                        ++spglowchange[n16];
                    }
                    else {
                        this.spglowchange[a5] = 0;
                    }
                }
                final int[][] increment = new int[5][3];
                for (int c9 = 0; c9 < 3; ++c9) {
                    if (this.spglowchange[a5] < this.sametime[a5] - 1) {
                        increment[this.spglowchange[a5]][c9] = Math.abs(this.refcol[a5][this.spglowchange[a5]][c9] - this.refcol[a5][this.spglowchange[a5] + 1][c9]) / glowfor;
                        if (this.refcol[a5][this.spglowchange[a5]][c9] > this.refcol[a5][this.spglowchange[a5] + 1][c9]) {
                            conto[a5].spec[c9] = this.refcol[a5][this.spglowchange[a5]][c9] - this.spglow[a5] * increment[this.spglowchange[a5]][c9];
                        }
                        else {
                            conto[a5].spec[c9] = this.refcol[a5][this.spglowchange[a5]][c9] + this.spglow[a5] * increment[this.spglowchange[a5]][c9];
                        }
                    }
                    else {
                        increment[this.spglowchange[a5]][c9] = Math.abs(this.refcol[a5][this.spglowchange[a5]][c9] - this.refcol[a5][0][c9]) / glowfor;
                        if (this.refcol[a5][this.spglowchange[a5]][c9] > this.refcol[a5][0][c9]) {
                            conto[a5].spec[c9] = this.refcol[a5][this.spglowchange[a5]][c9] - this.spglow[a5] * increment[this.spglowchange[a5]][c9];
                        }
                        else {
                            conto[a5].spec[c9] = this.refcol[a5][this.spglowchange[a5]][c9] + this.spglow[a5] * increment[this.spglowchange[a5]][c9];
                        }
                    }
                }
            }
        }
        conto[0].playerglow = false;
        if (view != 0) {
            if (!this.pglowchange) {
                if (this.pglow < 5) {
                    ++this.pglow;
                }
                else {
                    this.pglow = 5;
                    this.pglowchange = true;
                }
            }
            else if (this.pglow > 0) {
                --this.pglow;
            }
            else {
                this.pglow = 0;
                this.pglowchange = false;
            }
            conto[0].playerglow = true;
            conto[0].spec[0] = this.pglow * 25;
            if (conto[0].spec[0] < 0) {
                conto[0].spec[0] = 0;
            }
            conto[0].spec[1] = 0;
            conto[0].spec[2] = this.pglow * 25;
            if (conto[0].spec[2] < 0) {
                conto[0].spec[2] = 0;
            }
        }
        else {
            this.pglow = 0;
            this.pglowchange = false;
        }
    }
    
    public double round(final double value, final int precision) {
        final int scale = (int)Math.pow(10.0, precision);
        return Math.round(value * scale) / (double)scale;
    }
    
    public void drawwater(final int cx, final int cy, final int cz) {
        this.rd.setColor(new Color(cx, cy, cz, 170));
        this.rd.fillRect(0, 0, 870, 480);
    }
    
    public static int getMax(final int[] inputArray) {
        int maxValue = inputArray[0];
        for (int i = 1; i < inputArray.length; ++i) {
            if (inputArray[i] > maxValue) {
                maxValue = inputArray[i];
            }
        }
        return maxValue;
    }
    
    public void sortpower(final int a, final int b, final Madness[] madness) {
        if (madness[a].grip[this.sc[a]] >= madness[b].grip[this.sc[b]]) {
            final float mainboistat = (madness[a].grip[this.sc[a]] - 10.0f) / 20.0f;
            final float targetstat = (madness[b].grip[this.sc[b]] - 10.0f) / 20.0f;
            final float difference = (mainboistat - targetstat) * 37.0f;
            float diffmod = 50.0f + (madness[b].level[this.sc[b]] - 1) * 0.25f;
            if (!this.careermode) {
                diffmod = 50.0f;
            }
            this.specpower[a] = difference / diffmod + 1.0;
        }
        else {
            final float mainboistat = (madness[a].grip[this.sc[a]] - 10.0f) / 20.0f;
            final float targetstat = (madness[b].grip[this.sc[b]] - 10.0f) / 20.0f;
            final float difference = (targetstat - mainboistat) * 37.0f;
            float diffmod = 50.0f + (madness[b].level[this.sc[b]] - 1) * 0.25f;
            if (!this.careermode) {
                diffmod = 50.0f;
            }
            this.specpower[a] = 1.0 - difference / diffmod;
            if (this.specpower[a] < 0.15) {
                this.specpower[a] = 0.15;
            }
        }
    }
    
    public void careermode(final Madness[] madness, final CheckPoints checkpoints, final Control[] control, final ContO[] conto, final Trackers trackers, final Contva contva) {
        this.viewbot = control[0].viewbot[0];
        this.makebot = control[0].viewbot[1];
        if (this.makebot) {
            madness[0].exp[this.sc[0]] = 0;
        }
        int whichlevel = this.startinglevel;
        if ((checkpoints.stage < this.unlocked[1] || this.bonstage) && madness[0].level[this.sc[0]] >= this.maxlevel[this.unlocked[1] - 2] + 5) {
            whichlevel = madness[0].level[this.sc[0]];
        }
        if (whichlevel >= this.softlevelcap) {
            int leveldiff = this.startinglevel - this.softlevelcap;
            if (leveldiff > 5) {
                leveldiff = 5;
            }
            this.expmult = 0.5 - leveldiff * 0.05;
        }
        else {
            this.expmult = 1.0;
        }
        if ((this.hardstage || checkpoints.stage == this.unlocked[1] || this.bonstage) && madness[0].level[this.sc[0]] < this.averagelevel - 2) {
            this.isithard = true;
        }
        if (this.m.effect[10]) {
            for (int a = this.nplayers; a < 200; ++a) {
                conto[a].lightup = false;
                if (conto[0].x >= conto[a].xextreme[0] && conto[0].x <= conto[a].xextreme[1] && conto[0].z >= conto[a].zextreme[0] && conto[0].z <= conto[a].zextreme[1]) {
                    conto[a].lightup = true;
                }
            }
        }
        if (checkpoints.stage == 15 && !this.bonusstage[2]) {
            for (int b = 0; b < this.nplayers; ++b) {
                conto[b].outoftrack = true;
                for (int a2 = this.nplayers; a2 < 200; ++a2) {
                    if (conto[b].x >= conto[a2].xextreme[0] && conto[b].x <= conto[a2].xextreme[1] && conto[b].z >= conto[a2].zextreme[0] && conto[b].z <= conto[a2].zextreme[1]) {
                        conto[b].outoftrack = false;
                    }
                }
            }
        }
        if (checkpoints.stage == 13) {
            final int[] xtfloor = new int[4];
            final int[] floorcount = { 216 + xtfloor[0] + this.nplayers, 412 + xtfloor[1] + xtfloor[0] + this.nplayers, 629 + xtfloor[2] + xtfloor[1] + xtfloor[0] + this.nplayers, 861 + xtfloor[3] + xtfloor[2] + xtfloor[1] + xtfloor[0] + this.nplayers };
            for (int a3 = 0; a3 < floorcount[3]; ++a3) {
                this.norender[a3] = false;
                if (conto[a3].telechk > -1) {
                    conto[a3].invisiblepiece = 150;
                    if (conto[a3].telechk == 0) {
                        conto[a3].glowlines = true;
                        conto[a3].glowcustom = true;
                        conto[a3].glowcolour[0] = 255;
                        conto[a3].glowcolour[1] = 0;
                        conto[a3].glowcolour[2] = 0;
                    }
                    if (conto[a3].telechk == 1) {
                        conto[a3].glowlines = true;
                        conto[a3].glowcustom = true;
                        conto[a3].glowcolour[0] = 255;
                        conto[a3].glowcolour[1] = 200;
                        conto[a3].glowcolour[2] = 0;
                    }
                    if (conto[a3].telechk == 2) {
                        conto[a3].glowlines = true;
                        conto[a3].glowcustom = true;
                        conto[a3].glowcolour[0] = 0;
                        conto[a3].glowcolour[1] = 255;
                        conto[a3].glowcolour[2] = 0;
                    }
                    if (conto[a3].telechk == 3) {
                        conto[a3].glowlines = true;
                        conto[a3].glowcustom = true;
                        conto[a3].glowcolour[0] = 255;
                        conto[a3].glowcolour[1] = 255;
                        conto[a3].glowcolour[2] = 255;
                    }
                }
                conto[a3].fakegrounded = conto[a3].grounded;
                if (conto[a3].wallpiece) {
                    int wallheight = -(this.floor[0] * 10000) - conto[a3].grat;
                    if (this.floor[0] == 0) {
                        wallheight = 250 - conto[a3].grat;
                    }
                    if (conto[a3].y != wallheight) {
                        this.norender[a3] = true;
                    }
                }
                if (conto[0].y <= -30000) {
                    if (a3 > 0 && a3 < this.nplayers && conto[a3].y > -30000) {
                        conto[a3].fakegrounded = 0.0f;
                    }
                    if (a3 >= floorcount[0]) {
                        conto[a3].fakegrounded = conto[a3].grounded * 10000.0f;
                        if (a3 >= floorcount[1]) {
                            conto[a3].fakegrounded = 0.0f;
                            if (a3 >= floorcount[2]) {
                                this.norender[a3] = true;
                            }
                        }
                    }
                }
                if (conto[0].y <= -20000 && conto[0].y > -30000) {
                    if (a3 > 0 && a3 < this.nplayers && (conto[a3].y <= -30000 || conto[a3].y > -20000)) {
                        conto[a3].fakegrounded = 0.0f;
                    }
                    if ((a3 >= this.nplayers && a3 < floorcount[0]) || a3 >= floorcount[1]) {
                        conto[a3].fakegrounded = conto[a3].grounded * 10000.0f;
                        if (a3 >= floorcount[2]) {
                            conto[a3].fakegrounded = 0.0f;
                        }
                    }
                }
                if (conto[0].y <= -10000 && conto[0].y > -20000) {
                    if (a3 > 0 && a3 < this.nplayers && (conto[a3].y <= -20000 || conto[a3].y > -10000)) {
                        conto[a3].fakegrounded = 0.0f;
                    }
                    if ((a3 >= this.nplayers && a3 < floorcount[1]) || a3 >= floorcount[2]) {
                        conto[a3].fakegrounded = conto[a3].grounded * 10000.0f;
                    }
                }
                if (conto[0].y > -10000) {
                    if (a3 > 0 && a3 < this.nplayers && conto[a3].y <= -10000) {
                        conto[a3].fakegrounded = 0.0f;
                    }
                    if (a3 >= this.nplayers && a3 < floorcount[2]) {
                        conto[a3].fakegrounded = conto[a3].grounded * 10000.0f;
                    }
                }
                if (a3 < this.nplayers) {
                    if (conto[a3].floorguardian) {
                        int i_66_ = (int)(60.0f * (madness[a3].hitmag / (float)madness[a3].maxmag[this.sc[a3]]));
                        int i_67_ = 244;
                        int i_68_ = 244;
                        int i_69_ = 11;
                        if (i_66_ > 20) {
                            i_68_ = (int)(244.0f - 233.0f * ((i_66_ - 20) / 40.0f));
                        }
                        i_67_ += (i_67_ * (this.m.snap[0] / 100.0f));  // cast: bytecode-verified
                        if (i_66_ > 60) {
                            i_66_ = 60;
                        }
                        if (i_67_ > 255) {
                            i_67_ = 255;
                        }
                        if (i_67_ < 0) {
                            i_67_ = 0;
                        }
                        i_68_ += (i_68_ * (this.m.snap[1] / 100.0f));  // cast: bytecode-verified
                        if (i_68_ > 255) {
                            i_68_ = 255;
                        }
                        if (i_68_ < 0) {
                            i_68_ = 0;
                        }
                        i_69_ += (i_69_ * (this.m.snap[2] / 100.0f));  // cast: bytecode-verified
                        if (i_69_ > 255) {
                            i_69_ = 255;
                        }
                        if (i_69_ < 0) {
                            i_69_ = 0;
                        }
                        conto[a3].dmgcolours[0] = i_67_;
                        conto[a3].dmgcolours[1] = i_68_;
                        conto[a3].dmgcolours[2] = i_69_;
                        madness[a3].power = 98.0f;
                        madness[a3].clear = -2;
                        checkpoints.clear[a3] = -2;
                        if (madness[a3].hitmag > madness[a3].maxmag[this.sc[a3]]) {
                            conto[a3].dmgcolours[0] = 255;
                            conto[a3].dmgcolours[1] = 169;
                            conto[a3].dmgcolours[2] = 89;
                        }
                        else {
                            conto[a3].setfire();
                        }
                    }
                    madness[a3].groundlevel = this.floor[a3] * -10000.0f;
                    if (madness[a3].groundlevel == 0.0f) {
                        madness[a3].groundlevel = 250.0f;
                    }
                    conto[a3].groundlevel = (int)madness[a3].groundlevel;
                    if (conto[a3].y < (int)(madness[a3].groundlevel - 9500.0f)) {
                        final int n = this.floor[a3];
                    }
                    if (this.speedhack[a3] > 0) {
                        int topspeed = madness[a3].swits[this.sc[a3]][2];
                        if (topspeed > 400 && this.floor[a3] == 2) {
                            topspeed = 400;
                        }
                        madness[a3].speed = (float)topspeed;
                        madness[a3].power = 98.0f;
                        final int[] speedhack = this.speedhack;
                        final int n2 = a3;
                        --speedhack[n2];
                    }
                    if (a3 > 0) {
                        this.norender[a3] = false;
                        if (this.floor[0] != this.floor[a3]) {
                            this.norender[a3] = true;
                        }
                    }
                }
            }
        }
        if (checkpoints.stage == 11 && !this.bonusstage[1]) {
            for (int a = 0; a < this.nplayers; ++a) {
                if (this.starcnt == 0) {
                    final int[] statdrain = this.statdrain;
                    final int n3 = a;
                    ++statdrain[n3];
                }
            }
            for (int a = this.nplayers + 155; a < this.nplayers + 221; ++a) {
                conto[a].invisiblepiece = 130;
                if (a < this.nplayers + 191) {}
            }
            for (int a = 1; a < 5; ++a) {
                if (!this.undead[a]) {
                    madness[a].distruct(conto[a]);
                    this.undead[a] = true;
                }
            }
            for (int a = 1; a < this.nplayers; ++a) {
                if (this.undead[a]) {
                    madness[a].spatk = 0.0f;
                    madness[a].hitmag = 0;
                    this.noarrow[a] = true;
                    madness[a].spatk = 0.0f;
                    madness[a].power = 98.0f;
                    if (madness[0].aitssp[this.sc[0]] <= madness[0].aistrsp[this.sc[0]] && checkpoints.clear[0] <= 4 && a >= 5) {
                        int speedlimit = madness[0].nitroswits[this.sc[0]][2] + madness[0].aitssp[this.sc[0]] - madness[a].nitroswits[this.sc[a]][2];
                        if (speedlimit < 0) {
                            speedlimit = 0;
                        }
                        madness[a].aitssp[this.sc[a]] = speedlimit;
                        madness[a].power = 60.0f;
                    }
                    madness[a].clear = -2;
                    this.newflame[a] = true;
                    checkpoints.clear[a] = -2;
                }
            }
            final int[] rightb = { -6100, 26800, -6400, 20418 };
            final int[] leftb = { -20500, 7600, -25600, 6018 };
            final int[] topb = { 25300, 24400, 54700, 51600 };
            final int[] bottomb = { 1300, -4400, 35500, 32400 };
            final int[] carsregion = new int[4];
            for (int a4 = 0; a4 < this.nplayers; ++a4) {
                if (a4 < 1 || a4 > 4) {
                    this.safezone[a4] = false;
                    if (conto[a4].x < rightb[0] && conto[a4].x > leftb[0] && conto[a4].z < topb[0] && conto[a4].z > bottomb[0] && !this.undead[a4]) {
                        this.safezone[a4] = true;
                    }
                }
            }
            for (int a4 = 1; a4 < this.nplayers - 1; ++a4) {
                if (a4 >= 1 && a4 <= 4) {
                    if (conto[a4].x > rightb[a4 - 1] - 700) {
                        conto[a4].x = rightb[a4 - 1] - 700;
                    }
                    if (conto[a4].x < leftb[a4 - 1] + 700) {
                        conto[a4].x = leftb[a4 - 1] + 700;
                    }
                    if (conto[a4].z > topb[a4 - 1] - 700) {
                        conto[a4].z = topb[a4 - 1] - 700;
                    }
                    if (conto[a4].z < bottomb[a4 - 1] + 700) {
                        conto[a4].z = bottomb[a4 - 1] + 700;
                    }
                }
                else {
                    for (int b2 = 0; b2 < 4; ++b2) {
                        if (conto[a4].x < rightb[b2] && conto[a4].x > leftb[b2] && conto[a4].z < topb[b2] && conto[a4].z > bottomb[b2] && !this.undead[a4]) {
                            final int[] array = carsregion;
                            final int n4 = b2;
                            ++array[n4];
                        }
                        if ((conto[0].x < rightb[b2] && conto[0].x > leftb[b2] && conto[0].z < topb[b2] && conto[0].z > bottomb[b2]) || carsregion[b2] == 0) {
                            this.undeadlock[b2 + 1] = 0;
                        }
                        else if (conto[a4].x < rightb[b2] && conto[a4].x > leftb[b2] && conto[a4].z < topb[b2] && conto[a4].z > bottomb[b2]) {
                            this.undeadlock[b2 + 1] = a4;
                        }
                    }
                }
            }
            int carszone = 0;
            for (int a5 = 0; a5 < this.nplayers - 1; ++a5) {
                if (conto[a5].x < -6100 && conto[a5].x > -20500 && conto[a5].z < 25300 && conto[a5].z > 1300 && a5 != 1 && !this.undead[a5]) {
                    ++carszone;
                }
            }
            if (carszone == 0) {
                if (conto[1].z < 13200) {
                    final ContO contO = conto[1];
                    contO.z += 500;
                }
                if (conto[1].z > 14300) {
                    final ContO contO2 = conto[1];
                    contO2.z -= 500;
                }
            }
        }
        if (checkpoints.stage == 6) {
            if (this.wallcountdown) {
                if (this.wallimmunity > 0) {
                    --this.wallimmunity;
                }
                else {
                    this.wallcountdown = false;
                }
            }
            else {
                this.wallimmunity = 100;
            }
            for (int a = this.nplayers + 5; a < this.nplayers + 101; ++a) {
                if ((this.ghosttimer >= 230 && this.ghosttimer < 250) || a < this.nplayers + 16) {
                    conto[a].invisiblepiece = (int)(Math.random() * 50.0);
                }
                else {
                    conto[a].invisiblepiece = 255;
                }
            }
            if (!this.scareflash) {
                if ((int)(Math.random() * 25000.0) == 0 && this.ghostattempt >= 3) {
                    for (int a = this.nplayers + 5; a < this.nplayers + 157; ++a) {
                        conto[a].glowlines = true;
                        conto[a].glowcustom = true;
                        conto[a].glowcolour[0] = 200;
                        conto[a].glowcolour[1] = 0;
                        conto[a].glowcolour[2] = 0;
                    }
                    this.scareflash = true;
                    if (!control[0].mutem) {
                        this.turnbackon = true;
                        control[0].mutem = true;
                    }
                }
                else {
                    for (int a = this.nplayers + 5; a < this.nplayers + 157; ++a) {
                        conto[a].glowlines = false;
                        conto[a].glowcustom = false;
                    }
                }
            }
            else {
                for (int a = this.nplayers + 5; a < this.nplayers + 157; ++a) {
                    conto[a].glowlines = false;
                    conto[a].glowcustom = false;
                }
                ++this.scareflashtime;
                conto[this.nplayers + 1].invisiblepiece = (int)(Math.random() * 50.0);
                if (this.scareflashtime > 15) {
                    if (!this.playonce && !this.mutes) {
                        this.fuucked.play();
                        this.playonce = true;
                    }
                    if (this.scareflashtime > 225) {
                        if (this.turnbackon) {
                            control[0].mutem = false;
                            this.turnbackon = false;
                        }
                        this.scareflash = false;
                        this.playonce = false;
                        this.scareflashtime = 0;
                    }
                }
            }
            for (int a = 0; a < this.nplayers; ++a) {
                if (a != 0 || !madness[a].dest) {
                    conto[a].invisiblepiece = 255;
                    conto[a].shadow = true;
                }
                this.entered[a] = false;
            }
            int timeperiod = 1000;
            final int delay = 250;
            if (checkpoints.clear[0] >= 9 && this.randomtimes == 0) {
                timeperiod = 500;
            }
            if (!this.ghostflash[0] && !this.ghostflash[1] && !this.ghostflash[2] && !this.ghosthit) {
                this.ghostfade = 0;
                if (this.ghosttimer < timeperiod + delay) {
                    ++this.ghosttimer;
                }
                else {
                    this.ghosttimer = 0;
                }
                if (this.randomtimes == 0) {
                    this.randomtimes = (int)(Math.random() * timeperiod) + 1;
                    this.ghostattack = (int)(Math.random() * 3.0);
                }
            }
            else {
                this.randomtimes = 0;
            }
            conto[this.nplayers].shadow = false;
            int teledistance = 3000;
            if (this.ghosttimer == this.randomtimes + delay && this.randomtimes != 0) {
                if (!this.ghostflash[this.ghostfar]) {
                    ++this.ghostattempt;
                    this.ghostflash[this.ghostfar] = true;
                }
            }
            else {
                this.ghostfar = (int)(Math.random() * 3.0);
            }
            for (int a6 = 0; a6 < 3; ++a6) {
                if (this.ghostflash[a6]) {
                    this.ghosttimer = 0;
                    if (a6 != this.ghostattack || this.ghostattempt < 3) {
                        teledistance = 3000 - a6 * 1000;
                    }
                    else if (this.ghostflashtimer < 65) {
                        teledistance = 3000 - a6 * 1000;
                    }
                    else {
                        teledistance = (900 - (this.ghostflashtimer - 65) * 100) * (3 - a6);
                        if (teledistance < 10) {
                            teledistance = 10;
                        }
                        if (this.ghostflashtimer == 79) {
                            this.ghosthit = true;
                        }
                    }
                    this.ghostfade = (int)(Math.random() * 80.0);
                    if (madness[0].mtouch) {
                        if (this.ghostflashtimer < 80) {
                            ++this.ghostflashtimer;
                        }
                        else {
                            this.ghostflashtimer = 0;
                            this.ghostflash[a6] = false;
                        }
                        conto[this.nplayers].shadow = true;
                    }
                    else {
                        this.ghostfade = 0;
                    }
                }
            }
            conto[this.nplayers].invisiblepiece = this.ghostfade;
            if (conto[0].xz < 0) {
                final ContO contO3 = conto[0];
                contO3.xz += 360;
                final Madness madness2 = madness[0];
                --madness2.xzadjust;
            }
            if (conto[0].xz > 360) {
                final ContO contO4 = conto[0];
                contO4.xz -= 360;
                final Madness madness3 = madness[0];
                ++madness3.xzadjust;
            }
            boolean reversing = false;
            if (madness[0].speed < 0.0f) {
                reversing = true;
            }
            this.teleport(conto, this.nplayers, 0, teledistance, reversing, 0);
            if (this.ghosthit) {
                if (this.whatghostdo == 1) {
                    boolean racer = false;
                    if (checkpoints.clear[0] >= 9) {
                        racer = true;
                    }
                    float damagetake = 200.0f;
                    int thelevel = this.averagelevel + 2;
                    if (thelevel > 20) {
                        thelevel = 20;
                    }
                    if (!racer) {
                        damagetake = 300.0f + thelevel * 25.0f;
                    }
                    if (racer) {
                        this.wallcountdown = true;
                    }
                    madness[0].ghostcolide(conto[0], damagetake, racer);
                }
                if (this.whatghostdo == 0 && !this.ghosttele) {
                    for (int a7 = 0; a7 < this.nplayers; ++a7) {
                        this.oldx[a7] = conto[a7].x;
                        this.oldz[a7] = conto[a7].z;
                    }
                    this.ghosttele = true;
                }
            }
            else {
                if (!this.shownghost) {
                    this.whatghostdo = (int)(Math.random() * 2.0);
                }
                else {
                    this.whatghostdo = 1;
                }
                if (this.ghostteletimer >= 200) {
                    if (this.ghostteletimer < 250) {
                        ++this.ghostteletimer;
                        for (int a7 = 0; a7 < this.nplayers; ++a7) {
                            this.entered[a7] = true;
                        }
                        conto[this.nplayers + 1].z = 300000;
                    }
                    else {
                        this.ghostteletimer = 0;
                    }
                }
                else {
                    conto[this.nplayers + 1].z = 300000;
                    this.ghostteletimer = 0;
                }
                for (int a7 = 0; a7 < 3; ++a7) {
                    conto[this.nplayers + 2 + a7].unsetfire();
                }
                this.ghosttele = false;
            }
            if (this.ghosttele) {
                this.shownghost = true;
                conto[this.nplayers + 1].invisiblepiece = (int)(Math.random() * 50.0);
                if (this.ghostteletimer >= 100) {
                    for (int a7 = 0; a7 < 3; ++a7) {
                        conto[this.nplayers + 2 + a7].setfire();
                    }
                }
                for (int a7 = 0; a7 < this.nplayers; ++a7) {
                    if (!madness[a7].dest) {
                        this.entered[a7] = true;
                        this.teleport(conto, a7, this.nplayers + 1, 3000, false, 0);
                        conto[a7].z = 297000;
                        conto[a7].y = 250 - conto[0].grat;
                        if (a7 <= 5) {
                            conto[a7].x = 0 - a7 * 500;
                        }
                        else {
                            conto[a7].x = 0 + (a7 - 5) * 500;
                        }
                        madness[a7].speed = 0.0f;
                        madness[a7].mtouch = true;
                    }
                    int howmuchleft = 255 - this.ghostteletimer * 5;
                    if (howmuchleft < 0) {
                        howmuchleft = 0;
                    }
                    if (a7 > 0 && (conto[a7].invisiblepiece = howmuchleft) == 0) {
                        conto[a7].shadow = false;
                    }
                }
                if (this.ghostteletimer >= 180) {
                    final ContO contO5 = conto[this.nplayers + 1];
                    contO5.z -= 250;
                }
                if (conto[this.nplayers + 1].z < conto[0].z) {
                    conto[this.nplayers + 1].z = conto[0].z;
                }
                if (this.ghostteletimer < 200) {
                    ++this.ghostteletimer;
                }
                else {
                    for (int a7 = 0; a7 < this.nplayers; ++a7) {
                        conto[a7].x = this.oldx[a7];
                        conto[a7].z = this.oldz[a7];
                    }
                    this.ghosthit = false;
                }
                this.ghostfade = 0;
            }
            if (checkpoints.haltall || madness[0].dest) {
                this.ghosttimer = 0;
            }
            if (madness[0].dest) {
                for (int a7 = 0; a7 < 3; ++a7) {
                    conto[this.nplayers + 2 + a7].setfire();
                }
                if (this.shownghost) {
                    if (this.holdcnt > 30) {
                        conto[0].unsetfire();
                        if (conto[0].invisiblepiece > 5) {
                            final ContO contO6 = conto[0];
                            contO6.invisiblepiece -= 10;
                        }
                        else {
                            conto[0].invisiblepiece = 0;
                            conto[0].shadow = false;
                        }
                    }
                    if (this.holdcnt > 85) {
                        conto[this.nplayers + 1].invisiblepiece = (int)(Math.random() * 50.0);
                        conto[this.nplayers].invisiblepiece = 0;
                    }
                }
            }
        }
        if (checkpoints.stage == 7) {
            if (conto[0].xz < 0) {
                final ContO contO7 = conto[0];
                contO7.xz += 360;
                final Madness madness4 = madness[0];
                --madness4.xzadjust;
            }
            if (conto[0].xz > 360) {
                final ContO contO8 = conto[0];
                contO8.xz -= 360;
                final Madness madness5 = madness[0];
                ++madness5.xzadjust;
            }
            boolean reversing2 = false;
            if (madness[0].speed < 0.0f) {
                reversing2 = true;
            }
            this.teleport(conto, this.nplayers, 0, 750, reversing2, 0);
            conto[this.nplayers].invisiblepiece = 0;
            conto[this.nplayers].shadow = false;
            if (this.ghosttimer < 1000) {
                if (!this.ghostflash[0]) {
                    ++this.ghosttimer;
                }
            }
            else {
                this.ghostflash[0] = true;
                this.ghosttimer = 0;
            }
            if (this.ghostflash[0] && madness[0].mtouch && !madness[0].dest) {
                conto[this.nplayers].invisiblepiece = 255;
                for (int b3 = 0; b3 < 3; ++b3) {
                    conto[this.nplayers].spec[b3] = conto[0].spec[b3];
                }
                conto[this.nplayers].shadow = true;
                if (this.ghostflashtimer < 150) {
                    ++this.ghostflashtimer;
                }
                else {
                    this.ghostflashtimer = 0;
                    this.ghostflash[0] = false;
                }
            }
            if (this.m.polyoutline[1] >= 120) {
                this.ghostflash[1] = true;
            }
            if (this.m.polyoutline[1] <= 0) {
                this.m.polyoutline[1] = 0;
                this.ghostflash[1] = false;
            }
            if (!this.ghostflash[1]) {
                final int[] polyoutline = this.m.polyoutline;
                final int n5 = 1;
                polyoutline[n5] += 12;
            }
            else {
                final int[] polyoutline2 = this.m.polyoutline;
                final int n6 = 1;
                polyoutline2[n6] -= 12;
            }
            for (int a2 = 0; a2 < this.nplayers; ++a2) {
                if (madness[a2].dest) {
                    conto[a2].invisiblepiece = 254;
                }
            }
        }
        if (checkpoints.stage == 12) {
            ++this.pieceglitch;
            for (int a = 0; a < 56; ++a) {
                if (a % 2 == 0) {
                    if (this.pieceglitch % 30 < 15) {
                        final ContO contO9 = conto[this.nplayers + a];
                        contO9.y -= 80;
                    }
                    else {
                        final ContO contO10 = conto[this.nplayers + a];
                        contO10.y += 80;
                    }
                }
                else if (this.pieceglitch >= 15) {
                    if ((this.pieceglitch + 15) % 30 < 15) {
                        final ContO contO11 = conto[this.nplayers + a];
                        contO11.y -= 80;
                    }
                    else {
                        final ContO contO12 = conto[this.nplayers + a];
                        contO12.y += 80;
                    }
                }
            }
            for (int a = 0; a < 48; ++a) {
                if (a % 2 == 0) {
                    if (this.pieceglitch % 20 < 10) {
                        final ContO contO13 = conto[this.nplayers + 56 + a];
                        contO13.x += 400;
                    }
                    else {
                        final ContO contO14 = conto[this.nplayers + 56 + a];
                        contO14.x -= 400;
                    }
                }
                else if (this.pieceglitch % 20 < 10) {
                    final ContO contO15 = conto[this.nplayers + 56 + a];
                    contO15.x -= 400;
                }
                else {
                    final ContO contO16 = conto[this.nplayers + 56 + a];
                    contO16.x += 400;
                }
            }
        }
        if (checkpoints.stage == 8) {
            for (int a = this.nplayers; a < this.nplayers + 163; ++a) {
                if (conto[a].glowlines && !conto[a].glowcustom) {
                    if (this.m.effecttime < 1000 && this.m.oneffect[3]) {
                        for (int b3 = 0; b3 < 3; ++b3) {
                            conto[a].glowcolour[b3] = (int)(this.m.grndcolour[b3] * 0.5);
                        }
                    }
                    else {
                        if (conto[a].glowcolour[0] > 0) {
                            final int[] glowcolour = conto[a].glowcolour;
                            final int n7 = 0;
                            glowcolour[n7] -= 8;
                        }
                        if (conto[a].glowcolour[1] > 0) {
                            final int[] glowcolour2 = conto[a].glowcolour;
                            final int n8 = 1;
                            glowcolour2[n8] -= 12;
                        }
                        if (conto[a].glowcolour[2] > 0) {
                            final int[] glowcolour3 = conto[a].glowcolour;
                            final int n9 = 2;
                            glowcolour3[n9] -= 8;
                        }
                        for (int b3 = 0; b3 < 3; ++b3) {
                            if (conto[a].glowcolour[b3] < 0) {
                                conto[a].glowcolour[b3] = 0;
                            }
                        }
                    }
                }
            }
            for (int a = this.nplayers + 163; a < this.nplayers + 225; ++a) {
                if (this.m.effecttime < 1000 && this.m.oneffect[3]) {
                    conto[a].glowlines = true;
                    for (int b3 = 0; b3 < 3; ++b3) {
                        conto[a].glowcolour[b3] = this.m.grndcolour[b3];
                    }
                }
                else {
                    if (conto[a].glowcolour[0] > 0) {
                        final int[] glowcolour4 = conto[a].glowcolour;
                        final int n10 = 0;
                        glowcolour4[n10] -= 15;
                    }
                    if (conto[a].glowcolour[1] > 0) {
                        final int[] glowcolour5 = conto[a].glowcolour;
                        final int n11 = 1;
                        glowcolour5[n11] -= 23;
                    }
                    if (conto[a].glowcolour[2] > 0) {
                        final int[] glowcolour6 = conto[a].glowcolour;
                        final int n12 = 2;
                        glowcolour6[n12] -= 15;
                    }
                    if (conto[a].glowcolour[0] == 0) {
                        conto[a].glowlines = false;
                    }
                }
            }
        }
        if (this.bonusstage[3]) {
            int yourtime = 340;
            if (checkpoints.clear[0] == 19) {
                yourtime = 480;
            }
            if (checkpoints.clear[0] >= 20 && checkpoints.clear[0] <= 23) {
                yourtime = 450;
            }
            if (checkpoints.clear[0] == 24 || checkpoints.clear[0] == 25) {
                yourtime = 600;
            }
            if (checkpoints.clear[0] >= 26 && checkpoints.clear[0] <= 40) {
                yourtime = 200;
            }
            if (checkpoints.clear[0] >= 41 && checkpoints.clear[0] <= 45) {
                yourtime = 420;
            }
            if (checkpoints.clear[0] >= 46) {
                yourtime = 1000;
                int i_66_2 = (int)(250.0f * (madness[this.nplayers - 1].hitmag / (float)madness[this.nplayers - 1].maxmag[this.sc[this.nplayers - 1]]));
                int i_67_2 = 244;
                int i_68_2 = 244;
                int i_69_2 = 11;
                if (i_66_2 > 83) {
                    i_68_2 = (int)(244.0f - 233.0f * ((i_66_2 - 83) / 40.0f));
                }
                i_67_2 += (i_67_2 * (this.m.snap[0] / 100.0f));  // cast: bytecode-verified
                if (i_66_2 > 250) {
                    i_66_2 = 250;
                }
                if (i_67_2 > 255) {
                    i_67_2 = 255;
                }
                if (i_67_2 < 0) {
                    i_67_2 = 0;
                }
                i_68_2 += (i_68_2 * (this.m.snap[1] / 100.0f));  // cast: bytecode-verified
                if (i_68_2 > 255) {
                    i_68_2 = 255;
                }
                if (i_68_2 < 0) {
                    i_68_2 = 0;
                }
                i_69_2 += (i_69_2 * (this.m.snap[2] / 100.0f));  // cast: bytecode-verified
                if (i_69_2 > 255) {
                    i_69_2 = 255;
                }
                if (i_69_2 < 0) {
                    i_69_2 = 0;
                }
                this.rd.setColor(new Color(i_67_2, i_68_2, i_69_2));
                this.rd.fillRect(310, 5, i_66_2, 20);
                this.rd.setColor(new Color(0, 0, 0));
                this.rd.drawRect(310, 5, 250, 20);
                this.rd.setFont(this.adventure.deriveFont(1, 17.0f));
                this.ftm = this.rd.getFontMetrics();
                this.drawcs(23, new StringBuilder().append(this.names[this.sc[this.nplayers - 1]]).toString(), 0, 0, 0, 3);
                this.rd.setFont(new Font("Arial", 1, 11));
                this.ftm = this.rd.getFontMetrics();
            }
            final float end = 4.5f - this.outdam[this.sc[0]];
            final int defence = (int)(end * 100.0f);
            int healthinc = 500;
            if (madness[0].healthreset[this.sc[0]] / 20 >= 500) {
                if (madness[0].healthreset[this.sc[0]] <= 25000 || this.sc[0] == 11 || this.sc[0] == 13 || this.sc[0] == 36 || this.sc[0] == 18 || this.sc[0] == 19 || this.sc[0] == 20) {
                    healthinc = madness[0].healthreset[this.sc[0]] / 20;
                }
                else {
                    healthinc = 1250;
                }
            }
            final int totalhealth = madness[0].healthreset[this.sc[0]] + defence * healthinc;
            final int maxbs = madness[0].healthreset[this.sc[0]] + (defence + 50) * healthinc;
            double dmgratio = madness[0].maxmag[this.sc[0]] / (double)maxbs;
            if (dmgratio < 1.0) {
                dmgratio = 1.0;
            }
            final int drainrate = (int)(totalhealth * dmgratio / yourtime);
            if (this.starcnt == 0 && !this.winner) {
                final Madness madness6 = madness[0];
                madness6.hitmag += drainrate;
            }
            if (checkpoints.clear[0] < 46 && !madness[0].dest) {
                madness[this.nplayers - 1].hitmag = 0;
                conto[this.nplayers - 1].x = 500000;
            }
            for (int a8 = 0; a8 < this.nplayers; ++a8) {
                this.noarrow[a8] = true;
                if (a8 > 0) {
                    madness[a8].nlaps = 0;
                    madness[a8].clear = -2;
                    checkpoints.clear[a8] = -2;
                    madness[a8].power = 98.0f;
                    if (a8 != this.nplayers - 1) {
                        if (!this.undead[a8]) {
                            madness[a8].distruct(conto[a8]);
                            this.undead[a8] = true;
                        }
                        madness[a8].hitmag = 0;
                        this.newflame[a8] = true;
                    }
                }
            }
            if (madness[this.nplayers - 1].dest) {
                for (int a8 = 1; a8 < this.nplayers - 1; ++a8) {
                    madness[a8].hitmag = madness[a8].maxmag[this.sc[a8]] + 1;
                }
            }
        }
        if (checkpoints.stage == 23) {
            for (int a = 0; a < this.nplayers; ++a) {
                if (this.speedhack[a] > 0) {
                    madness[a].revpush[this.sc[a]] = 0.0f;
                    madness[a].speed = (float)madness[a].swits[this.sc[a]][2];
                    final int[] speedhack2 = this.speedhack;
                    final int n13 = a;
                    --speedhack2[n13];
                }
                if (conto[a].xz < 0) {
                    final ContO contO17 = conto[a];
                    contO17.xz += 360;
                    final Madness madness7 = madness[a];
                    --madness7.xzadjust;
                }
                if (conto[a].xz > 360) {
                    final ContO contO18 = conto[a];
                    contO18.xz -= 360;
                    final Madness madness8 = madness[a];
                    ++madness8.xzadjust;
                }
            }
            if (!this.undead[1] && (this.unlocked[1] == 23 || this.hardstage)) {
                madness[1].distruct(conto[1]);
                this.undead[1] = true;
            }
            if (this.cstimer == 2) {
                checkpoints.haltall = false;
                this.holdit = false;
                this.unlimitedlaps = true;
                this.mtracks[this.lastload].setPaused(true);
                this.mtracks[this.lastload].unload();
                this.loadedt[this.lastload] = false;
                this.musicswitch = 10481000000L;
                this.duration = System.nanoTime();
                this.elapsed = 0L;
                this.pausetime = 0L;
                madness[0].spatk = 0.0f;
                if ((madness[0].hitmag > 0 || !madness[0].mtouch) && !madness[0].dest) {
                    this.rd.setFont(this.adventure.deriveFont(1, 17.0f));
                    this.ftm = this.rd.getFontMetrics();
                    if (this.absolutefuckingbullshit) {
                        this.drawcs(450, "Fix your car at the fix hoop!", 190, 0, 0, 3);
                        this.absolutefuckingbullshit = false;
                    }
                    else {
                        this.drawcs(450, "Fix your car at the fix hoop!", 95, 0, 0, 3);
                        this.absolutefuckingbullshit = true;
                    }
                    this.rd.setFont(new Font("Arial", 1, 11));
                    this.ftm = this.rd.getFontMetrics();
                }
                else if (!madness[0].dest) {
                    ++this.cstimer;
                }
                else {
                    this.holdit = true;
                }
            }
            if (this.cstimer <= 2 && (this.unlocked[1] == 23 || this.hardstage)) {
                conto[1].z = -150000;
            }
            if (this.cstimer == 3) {
                this.lastload = 77;
                this.mtracks[77].play(true);
                for (int a = 2; a < this.nplayers; ++a) {
                    conto[a].z = 500000 + a * 1000;
                }
                boolean reversing2 = false;
                if (madness[0].speed < 0.0f) {
                    reversing2 = true;
                }
                this.teleport(conto, 1, 0, 0, reversing2, 0);
                this.stunthealth = 0;
                ++this.cstimer;
            }
            if (this.cstimer >= 4 && this.cstimer < 10000) {
                final int grenlol = (int)(225.0f + 225.0f * (this.m.snap[1] / 100.0f));
                this.rd.setColor(new Color(0, grenlol, 0));
                this.rd.fillRect(310, 5, 250, 20);
                this.rd.setFont(this.adventure.deriveFont(1, 17.0f));
                this.ftm = this.rd.getFontMetrics();
                double hishealth = this.stunthealth / 2400.0;
                if (hishealth > 1.0) {
                    hishealth = 1.0;
                }
                final int redfill = (int)(hishealth * 250.0);
                final int redlol = (int)(225.0f + 225.0f * (this.m.snap[0] / 100.0f));
                this.rd.setColor(new Color(redlol, 0, 0));
                this.rd.fillRect(310, 5, redfill, 20);
                final double hishealth2 = hishealth * 100.0;
                final double hishealth3 = this.round(hishealth2, 1);
                this.drawcs(22, hishealth3 + " %", 0, 0, 0, 3);
                final float str = madness[1].moment[this.sc[1]] / 2.1f;
                final int strength = (int)(str * 100.0f);
                final int speed = madness[1].swits[this.sc[1]][2] / 2;
                this.rd.setFont(this.adventure.deriveFont(1, 15.0f));
                this.ftm = this.rd.getFontMetrics();
                int red = 0;
                int green = 0;
                int blue = 0;
                if (this.cstimer % 1200 < 400) {
                    if (madness[1].level[this.sc[1]] > madness[0].level[this.sc[0]] + 5) {
                        red = 150;
                        green = 0;
                        blue = 0;
                    }
                    else {
                        red = 0;
                        green = 60;
                        blue = 0;
                    }
                    this.drawcs(47, "Level " + madness[1].level[this.sc[1]], red, green, blue, 3);
                }
                else if (this.cstimer % 1200 < 800) {
                    if (madness[1].moment[this.sc[1]] > madness[0].moment[this.sc[0]]) {
                        red = 150;
                        green = 0;
                        blue = 0;
                    }
                    else {
                        red = 0;
                        green = 60;
                        blue = 0;
                    }
                    this.drawcs(47, "strength: " + strength, red, green, blue, 3);
                }
                else {
                    if (madness[1].swits[this.sc[1]][2] > madness[0].swits[this.sc[0]][2]) {
                        red = 150;
                        green = 0;
                        blue = 0;
                    }
                    else {
                        red = 0;
                        green = 60;
                        blue = 0;
                    }
                    this.drawcs(47, "speed: " + speed + " mph", red, green, blue, 3);
                }
                this.rd.setColor(new Color(0, 0, 0));
                this.rd.setFont(new Font("Arial", 1, 11));
                this.ftm = this.rd.getFontMetrics();
                this.rd.drawRect(310, 5, 250, 20);
            }
            if (this.cstimer >= 4 && this.cstimer < 180) {
                ++this.cstimer;
                this.rd.setFont(this.adventure.deriveFont(1, 17.0f));
                this.ftm = this.rd.getFontMetrics();
                if (this.absolutefuckingbullshit) {
                    this.drawcs(450, "Damage Titan by stunting!", 190, 0, 0, 3);
                    this.absolutefuckingbullshit = false;
                }
                else {
                    this.drawcs(450, "Damage Titan by stunting!", 95, 0, 0, 3);
                    this.absolutefuckingbullshit = true;
                }
                this.rd.setFont(new Font("Arial", 1, 11));
                this.ftm = this.rd.getFontMetrics();
            }
            if (this.cstimer >= 180 && this.cstimer < 10000) {
                final double hishealth4 = this.stunthealth / 2500.0;
                final double hishealth5 = hishealth4 * 100.0;
                for (int a7 = 2; a7 < this.nplayers; ++a7) {
                    madness[a7].power = 98.0f;
                    madness[a7].spatk = 0.0f;
                }
                if (this.cstimer < 9900) {
                    ++this.cstimer;
                }
                else {
                    this.cstimer = 180;
                }
                final int health = (int)hishealth5;
                if (this.cstimer % 2000 == 0 && health > 75) {
                    madness[1].spatk = 120.0f;
                }
                else if (madness[1].speclast2 == 0.0f || madness[1].speclast == 0.0f) {
                    madness[1].spatk = 0.0f;
                }
                if (health > 80) {
                    if (!this.revive[this.nplayers - 4]) {
                        madness[this.nplayers - 4].hitmag = 0;
                        madness[this.nplayers - 4].dest = false;
                        this.newflame[this.nplayers - 4] = true;
                        this.revive[this.nplayers - 4] = true;
                    }
                }
                else if (health > 60) {
                    if (!this.revive[this.nplayers - 5]) {
                        madness[this.nplayers - 5].hitmag = 0;
                        madness[this.nplayers - 5].dest = false;
                        this.newflame[this.nplayers - 5] = true;
                        this.revive[this.nplayers - 5] = true;
                    }
                }
                else if (health > 40) {
                    if (!this.revive[this.nplayers - 9]) {
                        madness[this.nplayers - 9].hitmag = 0;
                        madness[this.nplayers - 9].dest = false;
                        this.newflame[this.nplayers - 9] = true;
                        this.revive[this.nplayers - 9] = true;
                    }
                }
                else if (health > 20 && !this.revive[this.nplayers - 3]) {
                    madness[this.nplayers - 3].hitmag = 0;
                    madness[this.nplayers - 3].dest = false;
                    this.newflame[this.nplayers - 3] = true;
                    this.revive[this.nplayers - 3] = true;
                }
                double telerange = (1.0 - hishealth4) * 60000.0;
                if (telerange < 30000.0) {
                    telerange = 30000.0;
                }
                double modifier = 1.0;
                if (madness[0].speed < 0.0f) {
                    modifier = 0.4;
                }
                if ((this.py(conto[1].x / 100, conto[0].x / 100, conto[1].z / 100, conto[0].z / 100) > (int)(telerange * modifier) || this.teledelay > 300) && !madness[0].dest) {
                    if (this.telecooldown > 0) {
                        --this.telecooldown;
                    }
                    boolean reversing3 = false;
                    if (madness[0].speed < 0.0f) {
                        reversing3 = true;
                    }
                    this.teleport(conto, 1, 0, 2, reversing3, this.telecooldown);
                }
                else {
                    this.playonce = false;
                    this.telecooldown = 11;
                    ++this.teledelay;
                }
                if (hishealth4 < 1.0) {
                    madness[1].hitmag = 0;
                }
                else {
                    for (int a8 = 1; a8 < this.nplayers; ++a8) {
                        madness[a8].hitmag = madness[a8].maxmag[madness[a8].cn] + 1;
                    }
                    this.cstimer = 10000;
                    this.bossbattle = false;
                }
            }
            if (this.unlocked[1] == 23 || this.hardstage) {
                madness[1].revpush[this.sc[1]] = 0.0f;
                this.noarrow[1] = true;
                madness[1].power = 98.0f;
                madness[1].clear = -2;
                this.newflame[1] = true;
                checkpoints.clear[1] = -2;
            }
            int wastestage = checkpoints.wasted / 2 + 1;
            if (wastestage > 5) {
                wastestage = 5;
            }
            int racestage = contva.completed[0] / 20 + 1;
            if (racestage > 5) {
                racestage = 5;
            }
            final int realstage = Math.max(racestage, wastestage);
            this.m.greystage = realstage;
        }
        if (checkpoints.stage == 22) {
            final boolean[] bellend = new boolean[this.nplayers];
            for (int a2 = 0; a2 < this.nplayers; ++a2) {
                bellend[a2] = false;
                if (checkpoints.clear[a2] >= 3) {
                    bellend[a2] = true;
                }
                if (this.speedhack[a2] > 0) {
                    if (bellend[0] && this.speedhack[a2] > 75) {
                        control[a2].left = false;
                        control[a2].right = false;
                    }
                    madness[a2].revpush[this.sc[a2]] = 0.0f;
                    madness[a2].speed = (float)madness[a2].swits[this.sc[a2]][2];
                    final int[] speedhack3 = this.speedhack;
                    final int n14 = a2;
                    --speedhack3[n14];
                }
                if (conto[a2].xz < 0) {
                    final ContO contO19 = conto[a2];
                    contO19.xz += 360;
                    final Madness madness9 = madness[a2];
                    --madness9.xzadjust;
                }
                if (conto[a2].xz > 360) {
                    final ContO contO20 = conto[a2];
                    contO20.xz -= 360;
                    final Madness madness10 = madness[a2];
                    ++madness10.xzadjust;
                }
            }
            if (this.m.changingsnap) {
                this.snap(22);
            }
            boolean justrace = false;
            if ((checkpoints.clear[0] >= 3 && contva.biglead[this.nplayers - 1]) || contva.completed[this.nplayers - 1] >= 70 || checkpoints.wasted >= 7) {
                justrace = true;
            }
            if (contva.needhelp[this.nplayers - 1]) {
                justrace = true;
            }
            if (madness[this.nplayers - 1].frozen || madness[this.nplayers - 1].redstr || madness[this.nplayers - 1].strswap) {
                justrace = true;
            }
            if (justrace) {
                this.targetcar = 100;
            }
            if (!this.verydark && this.m.verydark && madness[this.targetcar].mtouch && !justrace && !madness[this.targetcar].dest) {
                int telescale = 1;
                if (bellend[this.targetcar]) {
                    telescale = 3;
                }
                if (madness[this.nplayers - 1].specialact && !bellend[this.targetcar]) {
                    telescale = 0;
                }
                if (this.teledelay > 0) {
                    --this.teledelay;
                    this.telecooldown = 13;
                    this.playonce = false;
                }
                else {
                    if (this.telecooldown > 0) {
                        --this.telecooldown;
                    }
                    if (this.targetcar != 0) {
                        this.telecooldown = 0;
                    }
                    boolean reversing = false;
                    if (madness[this.targetcar].speed < 0.0f) {
                        reversing = true;
                    }
                    this.teleport(conto, this.nplayers - 1, this.targetcar, telescale, reversing, this.telecooldown);
                    ++this.telewait;
                    if (telescale != 3 || this.telewait > 18) {
                        this.telewait = 0;
                        this.verydark = true;
                    }
                }
            }
            if (this.verydark && !this.m.verydark) {
                this.targetcar = 100;
                this.verydark = false;
            }
            if (!this.m.verydark) {
                this.teledelay = (int)(Math.random() * 30.0) + 1;
            }
            if (!this.verydark) {
                for (int a3 = 0; a3 < this.nplayers - 1; ++a3) {
                    if (checkpoints.clear[a3] >= 3) {
                        this.targetcar = a3;
                    }
                    else if (!madness[this.nplayers - 1].specialact) {
                        if (this.targetcar == 100 || madness[this.targetcar].dest) {
                            this.targetcar = (int)(this.m.random() * (this.nplayers - 1));
                        }
                    }
                    else {
                        this.targetcar = 0;
                    }
                }
            }
            else {
                int telerange2 = 50000;
                if (checkpoints.clear[this.targetcar] >= 3) {
                    if (contva.completed[this.targetcar] >= 50) {
                        telerange2 = 10000;
                    }
                    else if (contva.completed[this.targetcar] >= 33) {
                        telerange2 = 15000;
                    }
                    else {
                        telerange2 = 20000;
                    }
                }
                int noglitch = this.targetcar;
                if (noglitch == 100) {
                    noglitch = 0;
                }
                double modifier2 = 1.0;
                if (madness[0].speed < 0.0f) {
                    modifier2 = 0.5;
                }
                boolean smalldelay = false;
                if (this.telewait > 0 && this.telewait < 18 && bellend[noglitch]) {
                    smalldelay = true;
                }
                if (madness[noglitch].mtouch) {
                    if ((this.py(conto[this.nplayers - 1].x / 100, conto[noglitch].x / 100, conto[this.nplayers - 1].z / 100, conto[noglitch].z / 100) > (int)(telerange2 * modifier2) || smalldelay) && !justrace && !madness[noglitch].dest) {
                        ++this.telewait;
                        if (this.telecooldown > 0) {
                            --this.telecooldown;
                        }
                        int telescale2 = 1;
                        if (this.targetcar != 0) {
                            this.telecooldown = 0;
                        }
                        if (bellend[noglitch]) {
                            telescale2 = 3;
                        }
                        if (madness[this.nplayers - 1].specialact && !bellend[noglitch]) {
                            telescale2 = 0;
                        }
                        boolean reversing4 = false;
                        if (madness[noglitch].speed < 0.0f) {
                            reversing4 = true;
                        }
                        this.teleport(conto, this.nplayers - 1, noglitch, telescale2, reversing4, this.telecooldown);
                    }
                    else {
                        this.telewait = 0;
                        this.playonce = false;
                        this.telecooldown = 13;
                    }
                }
            }
        }
        if (checkpoints.stage == 21) {
            for (int a = 0; a < this.nplayers; ++a) {
                if (conto[a].z > conto[this.wallcode[3]].z - 500) {
                    this.entered[a] = false;
                }
                else {
                    this.entered[a] = true;
                }
                if (a > 0 && this.entered[a] && conto[a].z > -100000 && !this.countfall[a]) {
                    ++this.startfalling;
                    this.countfall[a] = true;
                }
                if (this.startfalling == this.nplayers - 1) {
                    this.crumble = true;
                }
            }
            if (this.crumble) {
                for (int a = 0; a < 20; ++a) {
                    if (conto[this.nplayers + a].invisiblepiece > 0) {
                        final ContO contO21 = conto[this.nplayers + a];
                        contO21.invisiblepiece -= 15;
                    }
                    else {
                        conto[this.nplayers + a].invisiblepiece = 0;
                        conto[this.nplayers + a].x = -300000;
                        for (int b3 = 0; b3 < 61; ++b3) {
                            trackers.x[b3] = -300000;
                        }
                    }
                }
                for (int a = 0; a < this.nplayers; ++a) {
                    if (conto[a].z <= -100000 && this.entered[a]) {
                        this.crumblefail[a] = true;
                    }
                }
            }
        }
        if (checkpoints.stage == 20 || checkpoints.stage == 21) {
            for (int a = 0; a < this.nplayers; ++a) {
                if (conto[a].xz < 0) {
                    final ContO contO22 = conto[a];
                    contO22.xz += 360;
                }
                if (conto[a].xz > 360) {
                    final ContO contO23 = conto[a];
                    contO23.xz -= 360;
                }
                if (conto[a].x > conto[this.wallcode[0]].x + 1000 || conto[a].x < conto[this.wallcode[1]].x - 1000 || conto[a].z > conto[this.wallcode[2]].z + 1000 || conto[a].z < conto[this.wallcode[3]].z - 1000) {
                    int deaddelay = 30;
                    if (this.timesfallen[a] < 4) {
                        deaddelay = (this.timesfallen[a] + 1) * 30;
                    }
                    else {
                        deaddelay = 120;
                    }
                    if (conto[a].y > -1000 || (this.lives[a] > 0 && this.lives[a] <= deaddelay)) {
                        boolean exception = false;
                        if (checkpoints.stage == 20) {
                            exception = true;
                        }
                        madness[a].hitmag = madness[a].maxmag[madness[a].cn] + 1;
                        if (exception) {
                            final int[] lives = this.lives;
                            final int n15 = a;
                            ++lives[n15];
                            if (this.lives[a] > deaddelay) {
                                this.lives[a] = 1000;
                                madness[a].respawn(conto[a], checkpoints);
                                final int[] timesfallen = this.timesfallen;
                                final int n16 = a;
                                ++timesfallen[n16];
                                if (this.losepoints) {
                                    this.statgain = 0;
                                    this.losepoints = false;
                                }
                            }
                        }
                        else {
                            this.lives[a] = 0;
                        }
                    }
                    else {
                        this.lives[a] = 0;
                        madness[a].respawning = false;
                    }
                }
                else {
                    this.lives[a] = 0;
                    madness[a].respawning = false;
                }
            }
        }
        if (checkpoints.stage == 19) {
            final int[] timetoglitch = new int[this.nplayers];
            for (int a2 = 0; a2 < this.nplayers; ++a2) {
                boolean waster = false;
                if (checkpoints.clear[a2] < 5) {
                    waster = true;
                }
                float goalgrip = 34.5f + (madness[this.nplayers - 1].level[this.sc[this.nplayers - 1]] * 1.75f - 1.0f) * 0.2f;
                if (goalgrip > 56.0f) {
                    goalgrip = 56.0f;
                }
                float gripmod = (madness[a2].grip[this.sc[a2]] - (goalgrip - 25.0f)) / 25.0f;
                if (gripmod < 0.5f) {
                    gripmod = 0.5f;
                }
                if (gripmod >= 1.0f) {
                    gripmod = 1.0f;
                    this.glitchtimer[a2] = 0;
                }
                final float gripaffect = (gripmod - 0.5f) / 0.5f;
                timetoglitch[a2] = 800 + (int)(2000.0f * gripaffect);
                if ((this.glitchtimer[a2] + a2 * 100) % (timetoglitch[a2] * 3) != 0 || this.glitchtimer[a2] == 0) {
                    if ((this.glitchtimer[a2] + a2 * 100) % timetoglitch[a2] != 0 || this.glitchtimer[a2] == 0 || (this.glitchtimer[a2] == timetoglitch[a2] - a2 * 100 && a2 != 0)) {
                        final int[] glitchtimer = this.glitchtimer;
                        final int n17 = a2;
                        ++glitchtimer[n17];
                    }
                    else {
                        this.randtele(conto, a2, waster);
                    }
                }
                else {
                    this.randtelebig(conto, a2, waster);
                }
            }
            ++this.pieceglitch;
            for (int a2 = 0; a2 < 50; ++a2) {
                if ((this.pieceglitch + a2 * 10) % 200 == 0 && this.pieceglitch > 10) {
                    this.piecespin[a2] = true;
                }
                if (this.piecespin[a2]) {
                    final int[] spintime = this.spintime;
                    final int n18 = a2;
                    ++spintime[n18];
                    if (this.spintime[a2] > 75) {
                        this.piecespin[a2] = false;
                        this.spintime[a2] = 0;
                    }
                }
            }
            for (int a2 = 0; a2 < 50; ++a2) {
                if (this.pieceglitch <= 10) {
                    this.origposx[a2] = conto[this.nplayers + a2].x;
                    this.origposz[a2] = conto[this.nplayers + a2].z;
                    this.origposxz[a2] = conto[this.nplayers + a2].xz;
                }
                if (this.piecespin[a2]) {
                    conto[this.nplayers + a2].y = 250 - conto[this.nplayers + a2].grat - (int)(this.m.random() * 1250.0f);
                    if (a2 % 2 == 0) {
                        final ContO contO24 = conto[this.nplayers + a2];
                        contO24.xz += 32;
                        conto[this.nplayers + a2].x = this.origposx[a2] - 600 + (int)(this.m.random() * 1200.0f);
                        conto[this.nplayers + a2].z = this.origposz[a2] - 600 + (int)(this.m.random() * 1200.0f);
                    }
                }
                else {
                    conto[this.nplayers + a2].y = 250 - conto[this.nplayers + a2].grat;
                    conto[this.nplayers + a2].x = this.origposx[a2];
                    conto[this.nplayers + a2].z = this.origposz[a2];
                    conto[this.nplayers + a2].xz = this.origposxz[a2];
                }
            }
        }
        if (checkpoints.stage == 3 || checkpoints.stage == 17 || checkpoints.stage == 21 || checkpoints.stage == 13) {
            for (int a = 1; a < this.nplayers; ++a) {
                if (madness[a].dest) {
                    if (this.destimer[a] > 80) {
                        conto[a].x = 500000 + a * 1000;
                        conto[a].z = 500000;
                        conto[a].y = -100000;
                        this.norender[a] = true;
                    }
                    else {
                        final int[] destimer = this.destimer;
                        final int n19 = a;
                        ++destimer[n19];
                    }
                }
            }
        }
        if (checkpoints.stage == 17) {
            if (this.undeadswitch % 500 != 0 || this.undeadswitch < 500) {
                ++this.undeadswitch;
                this.newtarget = false;
                this.generate = false;
            }
            else {
                for (int a = 0; a < this.nplayers; ++a) {
                    if (a == this.nplayers - 1 || madness[a].shadowcar || (a >= 1 && a <= 3)) {
                        this.positions[a] = 1000 + a;
                        this.sortpos[a] = 1000 + a;
                    }
                    else if (checkpoints.clear[a] >= 3) {
                        this.positions[a] = checkpoints.pos[a];
                        this.sortpos[a] = checkpoints.pos[a];
                    }
                    else {
                        this.positions[a] = 100 + a;
                        this.sortpos[a] = 100 + a;
                    }
                    Arrays.sort(this.sortpos);
                    if (this.sortpos[0] < 100) {
                        if (this.sortpos[0] == this.positions[a]) {
                            this.undeadtarget = a;
                            this.newtarget = true;
                        }
                    }
                    else if (!this.generate) {
                        this.undeadtarget = (int)(Math.random() * (this.nplayers - 1));
                        this.generate = true;
                    }
                    else if (!madness[this.undeadtarget].shadowcar && (this.undeadtarget == 0 || this.undeadtarget > 3) && !madness[this.undeadtarget].dest) {
                        this.newtarget = true;
                    }
                    else {
                        this.generate = false;
                        this.newtarget = false;
                    }
                    if (this.newtarget) {
                        this.sendwarning = true;
                        for (int c = 1; c < 4; ++c) {
                            this.attack(this.rd, conto, this.undeadtarget, c);
                        }
                    }
                }
            }
            for (int a = 1; a < 4; ++a) {
                if (!this.undead[a]) {
                    madness[a].distruct(conto[a]);
                    this.undead[a] = true;
                }
                madness[a].hitmag = 0;
                this.noarrow[a] = true;
                madness[a].spatk = 0.0f;
                madness[a].power = 98.0f;
                madness[a].clear = -2;
                this.newflame[a] = true;
                checkpoints.clear[a] = -2;
                if (this.undeadswitch < 500) {
                    conto[a].y = -20000;
                    conto[a].x = conto[this.wallcode[0]].x + 1000000;
                }
            }
            if (this.sendwarning) {
                if (this.undeadswitch % 500 < 120 && this.undeadswitch >= 500) {
                    this.rd.setFont(this.adventure.deriveFont(1, 17.0f));
                    this.ftm = this.rd.getFontMetrics();
                    if (this.absolutefuckingbullshit) {
                        if (this.undeadtarget != 0) {
                            this.drawcs(450, "The undead cars are targeting " + this.names[this.sc[this.undeadtarget]] + "!", 190, 0, 0, 3);
                        }
                        else {
                            this.drawcs(450, "The undead cars are targeting you!", 190, 0, 0, 3);
                        }
                        this.absolutefuckingbullshit = false;
                    }
                    else {
                        if (this.undeadtarget != 0) {
                            this.drawcs(450, "The undead cars are targeting " + this.names[this.sc[this.undeadtarget]] + "!", 95, 0, 0, 3);
                        }
                        else {
                            this.drawcs(450, "The undead cars are targeting you!", 95, 0, 0, 3);
                        }
                        this.absolutefuckingbullshit = true;
                    }
                    this.rd.setFont(new Font("Arial", 1, 11));
                    this.ftm = this.rd.getFontMetrics();
                }
                else {
                    this.sendwarning = false;
                }
            }
        }
        int extratime = 0;
        if (checkpoints.stage == 21) {
            extratime = 200;
        }
        if (checkpoints.stage == 5 || checkpoints.stage == 23) {
            extratime = 100;
        }
        if (checkpoints.stage >= 9 || checkpoints.stage == 5) {
            if (this.tempinv < 300 + extratime) {
                this.invulnerable = true;
                ++this.tempinv;
            }
            else {
                this.invulnerable = false;
            }
        }
        for (int a2 = 1; a2 < this.nplayers; ++a2) {
            if ((madness[a2].beast[a2] && checkpoints.stage < 11 && !this.bonstage) || this.sc[a2] == 18 || this.sc[a2] == 22 || this.sc[a2] == 19) {
                if (this.tempinv < 200) {
                    this.nohit[a2] = true;
                    ++this.tempinv;
                }
                else {
                    this.nohit[a2] = false;
                }
            }
        }
        if (this.starcnt > 0) {
            this.startexp = madness[0].exp[this.sc[0]];
            this.startsp = this.statpoints[this.sc[0]];
        }
        for (int a2 = 0; a2 < this.nplayers; ++a2) {
            this.endsp[a2] = madness[a2].aiendsp[this.sc[a2]];
        }
        this.getstats(madness[0]);
        if (madness[0].trcnt == 10 && madness[0].powerup[0] > 0.0f) {
            this.shexamts = true;
        }
        if (madness[0].power == 98.0f && this.starcnt == 0 && !madness[0].dest && !this.holdit && Math.abs(madness[0].speed) > 0.0f && !this.holdit) {
            final double lvmulti = (madness[0].level[this.sc[0]] * 0.1 + 1.0) * this.expmult;
            final double[] fullpownit = this.fullpownit;
            final int n20 = 0;
            fullpownit[n20] += lvmulti;
            this.powxpadjust += lvmulti;  // cast: bytecode-verified
            int extraxp = (int)this.fullpownit[0] - this.powxpadjust;
            if (!this.noexp) {
                final int[] exp = madness[0].exp;
                final int n21 = this.sc[0];
                exp[n21] += (int)lvmulti + extraxp;
            }
            if (extraxp > 0) {
                this.powxpadjust = (int)this.fullpownit[0];
            }
            if (extraxp < 0) {
                extraxp = 0;
            }
            this.shexamtpn = true;
            this.extraboo = false;
        }
        else {
            this.extraboo = true;
        }
        if (this.nolevels || this.disablexp || (checkpoints.stage == this.unlocked[1] && madness[0].level[this.sc[0]] >= this.maxlevel[this.unlocked[1]] && this.unlocked[1] > 1 && !this.bonstage)) {
            this.noexp = true;
        }
        if (madness[0].exp[this.sc[0]] > this.expneeded && this.starcnt == 0 && !this.noexp) {
            madness[0].exp[this.sc[0]] -= this.expneeded;
            final int[] level = madness[0].level;
            final int n22 = this.sc[0];
            ++level[n22];
            final int[] statpoints = this.statpoints;
            final int n23 = this.sc[0];
            statpoints[n23] += 4 + (int)(madness[0].level[this.sc[0]] / 15.0);
            this.startexp = 0;
            this.startsp = this.statpoints[this.sc[0]];
            this.statgain = 0;
            final int[] aitssp = madness[0].aitssp;
            final int n24 = this.sc[0];
            ++aitssp[n24];
            final int[] aiaccsp = madness[0].aiaccsp;
            final int n25 = this.sc[0];
            ++aiaccsp[n25];
            final int[] aigripsp = madness[0].aigripsp;
            final int n26 = this.sc[0];
            ++aigripsp[n26];
            final int[] aistusp = madness[0].aistusp;
            final int n27 = this.sc[0];
            ++aistusp[n27];
            final int[] aistrsp = madness[0].aistrsp;
            final int n28 = this.sc[0];
            ++aistrsp[n28];
            final int[] aiendsp = madness[0].aiendsp;
            final int n29 = this.sc[0];
            ++aiendsp[n29];
            this.playlevel = false;
            this.levelup = true;
        }
        if (madness[0].exp[this.sc[0]] < 0) {
            madness[0].exp[this.sc[0]] = 0;
        }
        this.expneeded = this.reqneed(madness[0].level[this.sc[0]], this.sc[0]);
        final int expfill = (int)(200.0f * (madness[0].exp[this.sc[0]] / (float)this.expneeded));
        if (this.noexp) {
            this.shexamt = false;
            this.shexamptsp = false;
            this.shexamtr = false;
            this.shexamtw = false;
            this.shexamts = false;
            this.shexamtpn = false;
            this.shexamthg = false;
            for (int a3 = 0; a3 < 2; ++a3) {
                this.fullpownit[a3] = 0.0;
            }
            this.powxpadjust = 0;
        }
        this.rd.setFont(this.adventure.deriveFont(1, 10.0f));
        this.rd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        final String levelstring = "level " + madness[0].level[this.sc[0]];
        this.rd.setColor(new Color(0, 0, 0, 180));
        final Polygon expbox = new Polygon();
        expbox.addPoint(10, 456);
        expbox.addPoint(7, 460);
        expbox.addPoint(7, 473);
        expbox.addPoint(10, 477);
        expbox.addPoint(215, 477);
        expbox.addPoint(217, 475);
        expbox.addPoint(217, 469);
        expbox.addPoint(215, 467);
        expbox.addPoint(27 + this.ftm.stringWidth(levelstring), 467);
        expbox.addPoint(25 + this.ftm.stringWidth(levelstring), 465);
        expbox.addPoint(25 + this.ftm.stringWidth(levelstring), 458);
        expbox.addPoint(23 + this.ftm.stringWidth(levelstring), 456);
        this.rd.fillPolygon(expbox);
        int lvlfade = 255;
        if (this.levelup) {
            lvlfade = this.leveltrans;
        }
        this.rd.setColor(new Color(255, 255, 255, lvlfade));
        this.rd.drawString(new StringBuilder().append(levelstring).toString(), 12, 467);
        this.rd.setColor(new Color(120, 120, 120));
        this.rd.fillRect(12, 470, 200, 4);
        this.rd.setColor(new Color(70, 70, 70));
        for (int a4 = 0; a4 < 50; ++a4) {
            this.rd.drawRect(12 + a4 * 4, 470, 4, 4);
        }
        this.rd.setColor(new Color(0, 125, 250));
        this.rd.fillRect(12, 470, expfill, 4);
        this.rd.setColor(new Color(70, 70, 70));
        this.rd.drawRect(12, 470, 200, 4);
        this.rd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        if (!this.stextphase) {
            this.strans = 255;
        }
        double extramod = 1.0;
        for (int a9 = 0; a9 < 6; ++a9) {
            if (this.specialstats[this.sc[0]][2][a9] > 0) {
                extramod = 1.0 + this.specialstats[this.sc[0]][2][a9] / 100.0;
            }
        }
        this.rd.setFont(this.adventure.deriveFont(1, 11.5f));
        if (this.shexamptsp) {
            this.shexamtr = false;
            this.shexamtw = false;
            this.shexamts = false;
            this.shexamt = false;
            this.stextphase = true;
            final String xpamount = "+" + (int)(this.spkamount * extramod * this.expmult);
            this.rd.setColor(new Color(255, 255, 255, this.strans));
            if (this.stextphase) {
                this.strans -= 7;
            }
            this.rd.drawString(new StringBuilder().append(xpamount).toString(), 32 + this.ftm.stringWidth(levelstring), 463);
            if (this.strans < 0) {
                this.stextphase = false;
                this.shexamptsp = false;
            }
        }
        if (this.shexamt) {
            this.shexamtr = false;
            this.shexamts = false;
            this.shexamptsp = false;
            this.stextphase = true;
            final String xpamount = "+" + this.wstamount[0];
            this.rd.setColor(new Color(255, 255, 255, this.strans));
            if (this.stextphase) {
                this.strans -= 7;
            }
            this.rd.drawString(new StringBuilder().append(xpamount).toString(), 32 + this.ftm.stringWidth(levelstring), 463);
            if (this.strans < 0) {
                this.strans = 255;
                this.stextphase = false;
                this.shexamt = false;
            }
        }
        if (this.shexamtr) {
            this.shexamt = false;
            this.shexamts = false;
            this.shexamptsp = false;
            this.stextphase = true;
            this.rd.setColor(new Color(255, 255, 255, this.strans));
            if (this.stextphase) {
                this.strans -= 7;
            }
            this.rd.drawString("+" + this.chkamount[0], 32 + this.ftm.stringWidth(levelstring), 463);
            if (this.strans < 0) {
                this.strans = 255;
                this.stextphase = false;
                this.shexamtr = false;
            }
        }
        if (this.shexamtw) {
            this.shexamt = false;
            this.shexamtr = false;
            this.shexamts = false;
            this.shexamptsp = false;
            this.stextphase = true;
            this.rd.setColor(new Color(255, 255, 255, this.strans));
            if (this.stextphase) {
                this.strans -= 7;
            }
            this.rd.drawString("+" + this.winamount, 32 + this.ftm.stringWidth(levelstring), 463);
            if (this.strans < 0) {
                this.strans = 255;
                this.stextphase = false;
                this.shexamtw = false;
            }
        }
        if (this.shexamts) {
            this.shexamt = false;
            this.shexamtr = false;
            this.shexamptsp = false;
            this.shexamtw = false;
            this.stextphase = true;
            this.rd.setColor(new Color(255, 255, 255, this.strans));
            if (this.stextphase) {
                this.strans -= 7;
            }
            this.rd.drawString("+" + (int)(madness[0].powerup[0] * madness[0].stumultiplier * extramod * this.expmult), 32 + this.ftm.stringWidth(levelstring), 463);
            if (this.strans < 0) {
                this.strans = 255;
                this.stextphase = false;
                this.shexamts = false;
            }
        }
        if (!this.btextphase) {
            this.btrans = 255;
        }
        if (this.shexamtpn) {
            this.btextphase = true;
            this.rd.setColor(new Color(255, 255, 255, this.btrans));
            if (this.btextphase && this.extraboo) {
                this.btrans -= 7;
            }
            else {
                this.btrans = 255;
                this.fullpownit[1] = this.fullpownit[0];
            }
            final String pownit = "+" + (int)this.fullpownit[1];
            this.rd.drawString("+" + (int)this.fullpownit[1], 210 - this.ftm.stringWidth(pownit), 463);
            if (this.btrans < 0) {
                this.btrans = 255;
                for (int a10 = 0; a10 < 2; ++a10) {
                    this.fullpownit[a10] = 0.0;
                }
                this.powxpadjust = 0;
                this.btextphase = false;
                this.shexamtpn = false;
            }
        }
        if (this.shexamthg) {
            this.atextphase = true;
            this.rd.setColor(new Color(255, 255, 255, this.atrans));
            if (this.atextphase) {
                this.atrans -= 7;
                if (this.atrans < 220) {
                    this.combotime = 200;
                }
            }
            this.rd.drawString("+" + this.fakehg, 221, 475);
            if (this.atrans < 0) {
                this.atrans = 255;
                this.atextphase = false;
                this.shexamthg = false;
            }
        }
        this.rd.setFont(new Font("Arial", 1, 11));
        if (!this.ktch) {
            if (this.fixspecials[0] && !madness[0].dest && !this.holdit) {
                this.spkamount = 450 + madness[0].level[this.sc[0]] * 11;
                this.shexamptsp = true;
                if (!this.noexp) {
                    final int[] exp2 = madness[0].exp;
                    final int n30 = this.sc[0];
                    exp2[n30] += (int)(this.spkamount * extramod * this.expmult);
                }
            }
            this.ktch = true;
        }
        if (!this.fixspecials[0]) {
            this.ktch = false;
        }
        if (this.levelup && !this.winner && !madness[0].dest && !this.loserace) {
            ++this.leveluptimer;
            if (!this.playlevel && !this.mutes) {
                this.powerup.play();
                this.playlevel = true;
            }
            if (this.leveltrans < 30) {
                this.levelfase = true;
            }
            if (this.leveltrans > 225) {
                this.levelfase = false;
            }
            if (this.levelfase) {
                this.leveltrans += 30;
            }
            else {
                this.leveltrans -= 30;
            }
            if (this.leveltrans > 255) {
                this.leveltrans = 255;
            }
            if (this.leveltrans < 0) {
                this.leveltrans = 0;
            }
            if (this.leveluptimer > 130) {
                this.levelup = false;
                this.leveluptimer = 0;
            }
        }
        else {
            this.levelup = false;
            this.leveluptimer = 0;
            this.leveltrans = 255;
        }
        this.rd.setFont(new Font("Arial", 1, 11));
        if (!this.nolevels) {
            this.airpgstats(madness, checkpoints);
        }
        else {
            for (int a9 = 1; a9 < this.nplayers; ++a9) {
                madness[a9].level[this.sc[a9]] = 1;
                this.nostatspls(madness[a9]);
            }
            this.setlevels = true;
        }
    }
    
    public void beasts(final int i, final Madness[] madness) {
        if (!this.bonstage && i != 5 && i != 6 && i != 7 && i != 8 && i != 11 && i != 12 && i != 13 && i != 17 && i != 21 && i != 18 && i != 19 && i != 22 && i != 23 && i != 24) {
            if (this.beastcar[0] == 0 || this.beastcar[0] == this.nplayers - 1) {
                this.beastcar[0] = (int)(Math.random() * (this.nplayers - 2)) + 1;
            }
            else {
                for (int a = 1; a < this.nplayers; ++a) {
                    if (a != this.beastcar[0]) {
                        this.beastopponent[a] = false;
                    }
                    else {
                        this.beastopponent[a] = true;
                    }
                }
                this.ssdone = true;
            }
        }
        else {
            if (this.bonusstage[0] || this.bonusstage[2] || this.bonusstage[3]) {
                for (int a = 1; a < this.nplayers; ++a) {
                    this.beastopponent[a] = true;
                }
                this.ssdone = true;
            }
            if (this.bonusstage[1]) {
                for (int a = 1; a < 8; ++a) {
                    this.beastopponent[a] = true;
                }
                this.ssdone = true;
                for (int a = 8; a < 11; ++a) {
                    this.beastopponent[a] = false;
                }
            }
            if (i == 5 && !this.bonstage) {
                for (int a = 1; a < this.nplayers; ++a) {
                    if (a != 1) {
                        this.beastopponent[a] = false;
                    }
                    else {
                        this.beastopponent[a] = true;
                    }
                }
                this.ssdone = true;
            }
            if (i == 6 || i == 7) {
                if (this.beastcar[0] <= 1 || this.beastcar[0] == this.nplayers - 1 || ((this.sc[this.beastcar[0]] == 10 || this.sc[this.beastcar[0]] == 6 || this.sc[this.beastcar[0]] == 7 || this.sc[this.beastcar[0]] == 2 || this.sc[this.beastcar[0]] == 33 || this.sc[this.beastcar[0]] == 29 || this.sc[this.beastcar[0]] == 30 || this.sc[this.beastcar[0]] == 25) && (this.hardstage || this.unlocked[1] == i))) {
                    this.beastcar[0] = (int)(Math.random() * (this.nplayers - 2)) + 1;
                }
                else {
                    for (int a = 1; a < this.nplayers; ++a) {
                        if (a != this.beastcar[0]) {
                            this.beastopponent[a] = false;
                        }
                        else {
                            this.beastopponent[a] = true;
                        }
                    }
                    this.ssdone = true;
                }
            }
            if ((i == 8 || i == 19 || i == 12 || i == 21 || i == 22) && !this.hardstage && this.unlocked[1] > i) {
                if (this.beastcar[0] == 0 || this.beastcar[1] == 0 || this.beastcar[0] == this.nplayers - 1 || this.beastcar[1] == this.nplayers - 1 || this.beastcar[0] == this.beastcar[1]) {
                    this.beastcar[0] = (int)(Math.random() * (this.nplayers - 2)) + 1;
                    this.beastcar[1] = (int)(Math.random() * (this.nplayers - 2)) + 1;
                }
                else {
                    for (int a = 0; a < this.nplayers; ++a) {
                        if (a == this.beastcar[0] || a == this.beastcar[1]) {
                            this.beastopponent[a] = true;
                        }
                        else {
                            this.beastopponent[a] = false;
                        }
                    }
                    this.ssdone = true;
                }
            }
            if (i == 8 && (this.hardstage || this.unlocked[1] == 8)) {
                for (int a = 0; a < this.nplayers; ++a) {
                    if (a == 1 || a == 2) {
                        this.beastopponent[a] = true;
                    }
                    else {
                        this.beastopponent[a] = false;
                    }
                }
                this.ssdone = true;
            }
            if (i == 12 && (this.hardstage || this.unlocked[1] == 12)) {
                final boolean[] areudone = new boolean[2];
                if (this.beastcar[0] == 0 || (this.sc[this.beastcar[0]] != 11 && this.sc[this.beastcar[0]] != 34)) {
                    this.beastcar[0] = (int)(Math.random() * (this.nplayers - 2)) + 1;
                }
                else {
                    areudone[0] = true;
                }
                if (this.beastcar[1] == 0 || (this.sc[this.beastcar[1]] != 10 && this.sc[this.beastcar[1]] != 9 && this.sc[this.beastcar[1]] != 8 && this.sc[this.beastcar[1]] != 31 && this.sc[this.beastcar[1]] != 33 && this.sc[this.beastcar[1]] != 32) || this.beastcar[1] == this.beastcar[0]) {
                    this.beastcar[1] = (int)(Math.random() * (this.nplayers - 2)) + 1;
                }
                else {
                    areudone[1] = true;
                }
                if (areudone[0] && areudone[1]) {
                    for (int a2 = 0; a2 < this.nplayers; ++a2) {
                        if (a2 == this.beastcar[0] || a2 == this.beastcar[1]) {
                            this.beastopponent[a2] = true;
                        }
                        else {
                            this.beastopponent[a2] = false;
                        }
                    }
                    this.ssdone = true;
                }
            }
            if (i == 13) {
                for (int a = 1; a < this.nplayers; ++a) {
                    if (a <= 9) {
                        this.beastopponent[a] = true;
                    }
                    else {
                        this.beastopponent[a] = false;
                    }
                }
                this.ssdone = true;
            }
            if ((i == 19 || i == 22) && (this.hardstage || this.unlocked[1] == i)) {
                final boolean[] areudone = new boolean[2];
                if (this.beastcar[0] == 0 || this.sc[this.beastcar[0]] != 16) {
                    this.beastcar[0] = (int)(Math.random() * (this.nplayers - 2)) + 1;
                }
                else {
                    areudone[0] = true;
                }
                if (this.beastcar[1] == 0 || (this.sc[this.beastcar[1]] != 15 && this.sc[this.beastcar[1]] != 38)) {
                    this.beastcar[1] = (int)(Math.random() * (this.nplayers - 2)) + 1;
                }
                else {
                    areudone[1] = true;
                }
                if (areudone[0] && areudone[1]) {
                    for (int a2 = 0; a2 < this.nplayers; ++a2) {
                        if (a2 == this.beastcar[0] || a2 == this.beastcar[1]) {
                            this.beastopponent[a2] = true;
                        }
                        else {
                            this.beastopponent[a2] = false;
                        }
                    }
                    this.ssdone = true;
                }
            }
            if (i == 21 && (this.hardstage || this.unlocked[1] == 21)) {
                if (this.beastcar[0] == 0 || this.beastcar[1] == 0 || this.beastcar[0] >= 8 || this.beastcar[1] >= 8 || this.beastcar[0] == this.beastcar[1]) {
                    this.beastcar[0] = (int)(Math.random() * (this.nplayers - 2)) + 1;
                    this.beastcar[1] = (int)(Math.random() * (this.nplayers - 2)) + 1;
                }
                else {
                    for (int a = 0; a < this.nplayers; ++a) {
                        if (a == this.beastcar[0] || a == this.beastcar[1]) {
                            this.beastopponent[a] = true;
                        }
                        else {
                            this.beastopponent[a] = false;
                        }
                    }
                    this.ssdone = true;
                }
            }
            if (i == 11 && !this.bonstage) {
                for (int a = 1; a < 5; ++a) {
                    this.beastopponent[a] = true;
                }
                if (this.beastcar[0] <= 4 || this.beastcar[0] == this.nplayers - 1) {
                    if (this.hardstage || this.unlocked[1] == 11) {
                        this.beastcar[0] = 9;
                    }
                    else {
                        this.beastcar[0] = (int)(Math.random() * (this.nplayers - 6)) + 5;
                    }
                }
                else {
                    for (int a = 5; a < this.nplayers; ++a) {
                        if (a != this.beastcar[0]) {
                            this.beastopponent[a] = false;
                        }
                        else {
                            this.beastopponent[a] = true;
                        }
                    }
                    this.ssdone = true;
                }
            }
            if (i == 17) {
                if (this.beastcar[0] == 0 || this.beastcar[0] == this.nplayers - 1) {
                    this.beastcar[0] = (int)(Math.random() * (this.nplayers - 5)) + 4;
                    this.beastcar[1] = (int)(Math.random() * (this.nplayers - 5)) + 4;
                }
                else {
                    for (int a = 0; a < this.nplayers; ++a) {
                        if (a == this.beastcar[0] || a == this.beastcar[1]) {
                            this.beastopponent[a] = true;
                        }
                        else {
                            this.beastopponent[a] = false;
                        }
                    }
                    for (int a = 1; a < 4; ++a) {
                        this.beastopponent[a] = true;
                    }
                    this.ssdone = true;
                }
            }
            if (i == 24 || (i == 18 && !this.bonusstage[3])) {
                for (int a = 1; a < this.nplayers; ++a) {
                    this.beastopponent[a] = false;
                }
                this.ssdone = true;
            }
            if (i == 23) {
                if (this.unlocked[1] == 23 || this.hardstage) {
                    this.beastopponent[1] = true;
                    for (int a = 2; a < this.nplayers; ++a) {
                        this.beastopponent[a] = false;
                    }
                }
                else {
                    for (int a = 1; a < this.nplayers; ++a) {
                        this.beastopponent[a] = false;
                    }
                }
                this.ssdone = true;
            }
        }
        if (i >= 15 && !this.bonusstage[2] && this.ssdone) {
            this.sortshadows(i, madness, this.noshadows);
        }
        if (this.ssdone && (i < 15 || this.bonusstage[2] || this.bonusstage[3] || i == 20 || i == 21)) {
            this.alldone = true;
            System.out.println("Done beasts and shadows for Stage " + i + "!");
        }
    }
    
    public void resetbeasts() {
        for (int a = 0; a < this.nplayers; ++a) {
            this.beastcar[a] = 0;
            this.beastopponent[a] = false;
        }
    }
    
    public void sortshadows(final int i, final Madness[] madness, final int num) {
        boolean hard = false;
        if (this.unlocked[1] == i || this.hardstage) {
            hard = true;
        }
        boolean specialar = false;
        if (i == 22 || (i == 19 && hard)) {
            specialar = true;
        }
        if (num == 1) {
            if (specialar) {
                if (i == 19) {
                    if (this.sc[this.beastcar[0]] == 16 && this.sc[this.beastcar[1]] == 16) {
                        if (this.shadow[0] == 0 || this.beastopponent[this.shadow[0]] || this.sc[this.shadow[0]] == 16) {
                            this.shadow[0] = (int)(Math.random() * (this.nplayers - 2)) + 1;
                        }
                        else {
                            madness[this.shadow[0]].shadowcar = true;
                            this.alldone = true;
                            System.out.println("Done beasts and shadows for Stage " + i + "!");
                        }
                    }
                    else if (this.shadow[0] == 0 || this.beastopponent[this.shadow[0]] || this.sc[this.shadow[0]] != 16) {
                        this.shadow[0] = (int)(Math.random() * (this.nplayers - 2)) + 1;
                    }
                    else {
                        madness[this.shadow[0]].shadowcar = true;
                        this.alldone = true;
                        System.out.println("Done beasts and shadows for Stage " + i + "!");
                    }
                }
                if (i == 22) {
                    madness[this.nplayers - 1].shadowcar = true;
                    this.alldone = true;
                    System.out.println("Done beasts and shadows for Stage " + i + "!");
                }
            }
            else if (this.shadow[0] == 0 || this.beastopponent[this.shadow[0]]) {
                this.shadow[0] = (int)(Math.random() * (this.nplayers - 2)) + 1;
            }
            else {
                madness[this.shadow[0]].shadowcar = true;
                this.alldone = true;
                System.out.println("Done beasts and shadows for Stage " + i + "!");
            }
        }
        else {
            int undeadsort = 0;
            if (i == 17) {
                undeadsort = 3;
            }
            if (i == 23 && (this.unlocked[1] == 23 || this.hardstage)) {
                undeadsort = 1;
            }
            for (int a = 0; a < num; ++a) {
                for (int b = 0; b < num; ++b) {
                    if (a != b) {
                        if (this.shadow[a] == 0 || this.shadow[a] == this.shadow[b] || this.beastopponent[this.shadow[a]] || this.beastopponent[this.shadow[b]]) {
                            this.shadow[a] = (int)(Math.random() * (this.nplayers - 2 - undeadsort)) + 1 + undeadsort;
                        }
                        else {
                            madness[this.shadow[a]].shadowcar = true;
                            this.alldone = true;
                            System.out.println("Done beasts and shadows for Stage " + i + "!");
                        }
                    }
                }
            }
        }
    }
    
    public void resetshadows(final Madness[] madness) {
        for (int a = 0; a < this.nplayers; ++a) {
            this.shadow[a] = 0;
            madness[a].shadowcar = false;
        }
    }
    
    public void getstats(final Madness[] madness, final CheckPoints checkpoints) {
        if (!this.nolevels) {
            this.airpgstats(madness, checkpoints);
        }
        else {
            for (int a = 1; a < this.nplayers; ++a) {
                madness[a].level[this.sc[a]] = 1;
                this.nostatspls(madness[a]);
            }
            this.setlevels = true;
        }
        if (this.setlevels) {
            if (this.replayphase == 3) {
                System.gc();
                this.replayphase = 4;
            }
            else if (this.fase == -69) {
                this.fase = 1;
            }
            else {
                int skipone = 0;
                if (checkpoints.stage == 23 && (this.unlocked[1] == 23 || this.hardstage)) {
                    skipone = 1;
                }
                this.scoutpage = 1 + skipone;
                this.fase = 205;
            }
        }
    }
    
    public void nostatspls(final Madness madness) {
        madness.aitssp[this.sc[madness.im]] = 0;
        madness.aiaccsp[this.sc[madness.im]] = 0;
        madness.aistrsp[this.sc[madness.im]] = 0;
        madness.aistusp[this.sc[madness.im]] = 0;
        madness.aigripsp[this.sc[madness.im]] = 0;
        madness.aiendsp[this.sc[madness.im]] = 0;
    }
    
    public void scouting(final Madness[] madness, final CheckPoints checkpoints, final Control control) {
        if (!this.nolevels) {
            this.airpgstats(madness, checkpoints);
        }
        else {
            for (int a = 1; a < this.nplayers; ++a) {
                madness[a].level[this.sc[a]] = 1;
                this.nostatspls(madness[a]);
            }
            this.setlevels = true;
        }
        this.trackbg(true);
        this.rd.setColor(new Color(0, 0, 0));
        this.rd.fillRect(0, 0, 100, 480);
        this.rd.fillRect(770, 0, 100, 480);
        this.rd.fillRect(100, 0, 670, 40);
        this.rd.fillRect(100, 440, 670, 40);
        this.rd.drawImage(this.br, 100, 40, null);
        this.rd.setFont(this.adventure.deriveFont(1, 24.0f));
        this.ftm = this.rd.getFontMetrics();
        int skipone = 0;
        if (checkpoints.stage == 23 && (this.unlocked[1] == 23 || this.hardstage)) {
            skipone = 1;
        }
        if (checkpoints.stage == 17 || (checkpoints.stage == 11 && !this.bonusstage[1])) {
            int caroffset = 4;
            if (checkpoints.stage == 17) {
                caroffset = 3;
            }
            if (this.scoutpage > caroffset) {
                this.drawcs(110, "OPPONENT " + (this.scoutpage - caroffset) + ": " + this.names[this.sc[this.scoutpage]], 255, 255, 255, 3);
            }
        }
        else {
            this.drawcs(110, "OPPONENT " + (this.scoutpage - skipone) + ": " + this.names[this.sc[this.scoutpage]], 255, 255, 255, 3);
        }
        this.rd.setFont(this.adventure.deriveFont(1, 20.0f));
        if (madness[this.scoutpage].level[this.sc[this.scoutpage]] > madness[0].level[this.sc[0]] + 5) {
            this.drawcs(135, "Level " + madness[this.scoutpage].level[this.sc[this.scoutpage]], 150, 0, 0, 3);
        }
        else {
            this.drawcs(135, "Level " + madness[this.scoutpage].level[this.sc[this.scoutpage]], 0, 100, 0, 3);
        }
        this.rd.setColor(new Color(255, 255, 255, 220));
        final Polygon statbox = new Polygon();
        statbox.addPoint(231, 150);
        statbox.addPoint(307, 150);
        statbox.addPoint(310, 153);
        statbox.addPoint(310, 172);
        statbox.addPoint(307, 175);
        statbox.addPoint(231, 175);
        statbox.addPoint(228, 172);
        statbox.addPoint(228, 153);
        this.rd.fillPolygon(statbox);
        final Polygon statbox2 = new Polygon();
        statbox2.addPoint(421, 150);
        statbox2.addPoint(545, 150);
        statbox2.addPoint(548, 153);
        statbox2.addPoint(548, 172);
        statbox2.addPoint(545, 175);
        statbox2.addPoint(421, 175);
        statbox2.addPoint(418, 172);
        statbox2.addPoint(418, 153);
        this.rd.fillPolygon(statbox2);
        this.rd.setFont(this.adventure.deriveFont(1, 15.0f));
        this.ftm = this.rd.getFontMetrics();
        this.rd.setColor(new Color(0, 0, 0));
        this.rd.drawString("STATS:", 269 - this.ftm.stringWidth("STATS:") / 2, 168);
        this.rd.drawString("YOUR STATS:", 483 - this.ftm.stringWidth("YOUR STATS:") / 2, 168);
        this.rd.setColor(new Color(255, 255, 255));
        this.rd.drawString("Top Speed: ", 165, 210);
        this.rd.drawString("Acceleration: ", 165, 240);
        this.rd.drawString("Control: ", 165, 270);
        this.rd.drawString("Stunting: ", 165, 300);
        this.rd.drawString("Strength: ", 165, 330);
        this.rd.drawString("Defence: ", 165, 360);
        this.rd.setColor(new Color(0, 100, 0));
        final Polygon[][] dale = new Polygon[6][2];
        for (int a2 = 0; a2 < 6; ++a2) {
            for (int b = 0; b < 2; ++b) {
                (dale[a2][b] = new Polygon()).addPoint(320 + b * 138, 190 + 30 * a2);
                dale[a2][b].addPoint(370 + b * 138, 190 + 30 * a2);
                dale[a2][b].addPoint(373 + b * 138, 193 + 30 * a2);
                dale[a2][b].addPoint(373 + b * 138, 212 + 30 * a2);
                dale[a2][b].addPoint(370 + b * 138, 215 + 30 * a2);
                dale[a2][b].addPoint(320 + b * 138, 215 + 30 * a2);
                dale[a2][b].addPoint(317 + b * 138, 212 + 30 * a2);
                dale[a2][b].addPoint(317 + b * 138, 193 + 30 * a2);
                this.rd.fillPolygon(dale[a2][b]);
            }
        }
        final int[] speed = new int[101];
        final float[] accelf = new float[101];
        final int[] acceleration = new int[101];
        final float[] contgri = new float[101];
        final int[] control2 = new int[101];
        final float[] stunts = new float[101];
        final int[] stunting = new int[101];
        final float[] str = new float[101];
        final int[] strength = new int[101];
        final float[] end = new float[101];
        final int[] defence = new int[101];
        final String[] spd = new String[101];
        final String[] accl = new String[101];
        final String[] cont = new String[101];
        final String[] stun = new String[101];
        final String[] strn = new String[101];
        final String[] def = new String[101];
        boolean undead = false;
        boolean partundead = false;
        if (checkpoints.stage == 17 && this.scoutpage >= 1 && this.scoutpage <= 3) {
            undead = true;
        }
        if (checkpoints.stage == 11 && !this.bonusstage[1] && this.scoutpage >= 1 && this.scoutpage <= 4) {
            undead = true;
        }
        if (checkpoints.stage == 13 && (this.scoutpage == 2 || this.scoutpage == 3 || this.scoutpage == 5 || this.scoutpage == 6 || this.scoutpage == 8 || this.scoutpage == 9)) {
            undead = true;
            partundead = true;
        }
        if (this.bonusstage[3] && this.scoutpage < this.nplayers - 1) {
            undead = true;
        }
        for (int a3 = 0; a3 < this.nplayers; ++a3) {
            speed[a3] = (madness[a3].nitroswits[this.sc[a3]][2] + madness[a3].aitssp[this.sc[a3]]) / 2;
            final float[] realacelf = { madness[a3].nitroacelf[this.sc[a3]][0] + madness[a3].aiaccsp[this.sc[a3]] * 0.1f - 6.0f, 0.0f, 0.0f };
            realacelf[1] = madness[a3].nitroacelf[this.sc[a3]][1] * realacelf[0] / madness[a3].nitroacelf[this.sc[a3]][0] - 3.0f;
            realacelf[2] = madness[a3].nitroacelf[this.sc[a3]][2] * realacelf[0] / madness[a3].nitroacelf[this.sc[a3]][0] - 2.0f;
            accelf[a3] = (realacelf[0] * 21.0f + realacelf[1] * 6.0f + realacelf[2] * 3.0f) / 201.0f;
            acceleration[a3] = (int)(accelf[a3] * 100.0f);
            contgri[a3] = (madness[a3].gripreset[this.sc[a3]] + madness[a3].aigripsp[this.sc[a3]] * 0.2f - 10.0f) / 20.0f;
            control2[a3] = (int)(contgri[a3] * 100.0f);
            stunts[a3] = (madness[a3].aircreset[this.sc[a3]] + madness[a3].aistusp[this.sc[a3]] + (madness[a3].airsreset[this.sc[a3]] + madness[a3].aistusp[this.sc[a3]] * 0.025f) * 10.0f) / 125.0f;
            stunting[a3] = (int)(stunts[a3] * 100.0f);
            str[a3] = (madness[a3].momentreset[this.sc[a3]] + madness[a3].aistrsp[this.sc[a3]] * 0.025f) / 2.1f;
            strength[a3] = (int)(str[a3] * 100.0f);
            end[a3] = this.outdam[this.sc[a3]] + madness[a3].aiendsp[this.sc[a3]] * 0.01f;
            defence[a3] = (int)(end[a3] * 100.0f);
            if (undead && !partundead) {
                if (a3 > 0) {
                    spd[a3] = new StringBuilder().append(speed[a3]).toString();
                    accl[a3] = "-";
                    stun[a3] = (cont[a3] = "-");
                    strn[a3] = new StringBuilder().append(strength[a3]).toString();
                    def[a3] = "-";
                }
                else {
                    spd[a3] = new StringBuilder().append(speed[a3]).toString();
                    accl[a3] = new StringBuilder().append(acceleration[a3]).toString();
                    cont[a3] = new StringBuilder().append(control2[a3]).toString();
                    stun[a3] = new StringBuilder().append(stunting[a3]).toString();
                    strn[a3] = new StringBuilder().append(strength[a3]).toString();
                    def[a3] = new StringBuilder().append(defence[a3]).toString();
                }
            }
            else {
                spd[a3] = new StringBuilder().append(speed[a3]).toString();
                accl[a3] = new StringBuilder().append(acceleration[a3]).toString();
                cont[a3] = new StringBuilder().append(control2[a3]).toString();
                stun[a3] = new StringBuilder().append(stunting[a3]).toString();
                strn[a3] = new StringBuilder().append(strength[a3]).toString();
                def[a3] = new StringBuilder().append(defence[a3]).toString();
            }
        }
        this.rd.setColor(new Color(230, 230, 230));
        this.rd.setFont(this.adventure.deriveFont(1, 20.0f));
        this.ftm = this.rd.getFontMetrics();
        this.rd.drawString(new StringBuilder().append(spd[this.scoutpage]).toString(), 345 - this.ftm.stringWidth(spd[this.scoutpage]) / 2, 211);
        this.rd.drawString(new StringBuilder().append(accl[this.scoutpage]).toString(), 345 - this.ftm.stringWidth(accl[this.scoutpage]) / 2, 241);
        this.rd.drawString(new StringBuilder().append(cont[this.scoutpage]).toString(), 345 - this.ftm.stringWidth(cont[this.scoutpage]) / 2, 271);
        this.rd.drawString(new StringBuilder().append(stun[this.scoutpage]).toString(), 345 - this.ftm.stringWidth(stun[this.scoutpage]) / 2, 301);
        this.rd.drawString(new StringBuilder().append(strn[this.scoutpage]).toString(), 345 - this.ftm.stringWidth(strn[this.scoutpage]) / 2, 331);
        this.rd.drawString(new StringBuilder().append(def[this.scoutpage]).toString(), 345 - this.ftm.stringWidth(def[this.scoutpage]) / 2, 361);
        this.rd.setFont(this.adventure.deriveFont(1, 15.0f));
        this.ftm = this.rd.getFontMetrics();
        if (checkpoints.stage >= 16) {
            this.rd.drawString("Bonus stat points: " + this.bonuspoints[this.scoutpage] + " (" + this.extpoints[this.sc[0]] + ")", 165, 391);
        }
        this.rd.setFont(this.adventure.deriveFont(1, 20.0f));
        this.ftm = this.rd.getFontMetrics();
        this.rd.drawString(new StringBuilder().append(spd[0]).toString(), 483 - this.ftm.stringWidth(spd[0]) / 2, 211);
        this.rd.drawString(new StringBuilder().append(accl[0]).toString(), 483 - this.ftm.stringWidth(accl[0]) / 2, 241);
        this.rd.drawString(new StringBuilder().append(cont[0]).toString(), 483 - this.ftm.stringWidth(cont[0]) / 2, 271);
        this.rd.drawString(new StringBuilder().append(stun[0]).toString(), 483 - this.ftm.stringWidth(stun[0]) / 2, 301);
        this.rd.drawString(new StringBuilder().append(strn[0]).toString(), 483 - this.ftm.stringWidth(strn[0]) / 2, 331);
        this.rd.drawString(new StringBuilder().append(def[0]).toString(), 483 - this.ftm.stringWidth(def[0]) / 2, 361);
        final int[] stat = { speed[0] - speed[this.scoutpage], acceleration[0] - acceleration[this.scoutpage], control2[0] - control2[this.scoutpage], stunting[0] - stunting[this.scoutpage], strength[0] - strength[this.scoutpage], defence[0] - defence[this.scoutpage] };
        final String[] stats = new String[6];
        final String[] signs = new String[6];
        for (int a4 = 0; a4 < 6; ++a4) {
            if (stat[a4] > 0) {
                this.rd.setColor(new Color(0, 100, 0));
                signs[a4] = "+";
            }
            if (stat[a4] < 0) {
                this.rd.setColor(new Color(150, 0, 0));
                signs[a4] = "";
            }
            if (stat[a4] == 0) {
                this.rd.setColor(new Color(220, 220, 220));
                signs[a4] = "";
            }
            stats[a4] = new StringBuilder().append(stat[a4]).toString();
            if (!undead || partundead) {
                if (stat[a4] != 0) {
                    this.rd.drawString(signs[a4] + stats[a4], 621 - this.ftm.stringWidth(String.valueOf(signs[a4]) + stats[a4]) / 2, 211 + a4 * 30);
                }
                else {
                    this.rd.drawString("-", 621 - this.ftm.stringWidth("-") / 2, 211 + a4 * 30);
                }
            }
        }
        if (this.scoutpage < this.nplayers - 1) {
            this.rd.drawImage(this.next[this.pnext], 645, 120, null);
            if (control.right) {
                ++this.scoutpage;
                control.right = false;
                this.pnext = 0;
            }
        }
        if (this.scoutpage > 1 + skipone) {
            this.rd.drawImage(this.back[this.pback], 155, 120, null);
            if (control.left) {
                --this.scoutpage;
                control.left = false;
                this.pback = 0;
            }
        }
        this.rd.drawImage(this.contin[this.pcontin], 390, 435, null);
        if (control.enter) {
            control.enter = false;
            this.pcontin = 0;
            this.scoutpage = 1 + skipone;
            this.fase = 1;
        }
        if (this.beastopponent[this.scoutpage] || madness[this.scoutpage].shadowcar) {
            if (this.flash[this.scoutpage] < 20) {
                this.beastflash[this.scoutpage] = true;
            }
            if (this.flash[this.scoutpage] > 240) {
                this.beastflash[this.scoutpage] = false;
            }
            if (this.beastflash[this.scoutpage]) {
                final int[] flash = this.flash;
                final int scoutpage = this.scoutpage;
                flash[scoutpage] += 12;
            }
            else {
                final int[] flash2 = this.flash;
                final int scoutpage2 = this.scoutpage;
                flash2[scoutpage2] -= 12;
            }
        }
        this.rd.setColor(new Color(255, 255, 255, this.flash[this.scoutpage]));
        this.rd.setFont(this.adventure.deriveFont(1, 30.0f));
        this.ftm = this.rd.getFontMetrics();
        if (undead) {
            if (!partundead) {
                this.rd.drawString("UNDEAD OPPONENT!", 435 - this.ftm.stringWidth("UNDEAD OPPONENT!") / 2, 35);
            }
            else {
                this.rd.drawString("GURADIAN!", 435 - this.ftm.stringWidth("GUARDIAN!") / 2, 35);
            }
        }
        else {
            if (this.beastopponent[this.scoutpage]) {
                this.rd.drawString("BEAST OPPONENT!", 435 - this.ftm.stringWidth("BEAST OPPONENT!") / 2, 35);
            }
            if (madness[this.scoutpage].shadowcar) {
                this.rd.drawString("SHADOW OPPONENT!", 435 - this.ftm.stringWidth("SHADOW OPPONENT!") / 2, 35);
            }
        }
    }
    
    public void randomise(final Madness[] madness, final int x, final int i) {
        if (!this.doitonce[x]) {
            this.okdale[x] = (int)(Math.random() * this.nplayers);
            int undeadextra = 0;
            if (i == 11 && !this.bonusstage[1]) {
                undeadextra = 4;
            }
            if (i == 17) {
                undeadextra = 3;
            }
            boolean specialredo = false;
            if (this.careermode) {
                if ((i == 17 || (i == 11 && !this.bonusstage[1])) && this.okdale[x] >= 1 && this.okdale[x] <= undeadextra) {
                    specialredo = true;
                }
                if (i == 23 && this.okdale[x] == 1 && (this.unlocked[1] == 23 || this.hardstage)) {
                    specialredo = true;
                }
            }
            if (this.okdale[x] == x || madness[this.okdale[x]].dest || madness[this.okdale[x]].fakedest || specialredo) {
                this.doitonce[x] = false;
            }
            else {
                this.doitonce[x] = true;
            }
        }
        else {
            this.randomcar[x] = this.okdale[x];
            if (madness[this.randomcar[x]].dest || madness[this.randomcar[x]].fakedest || this.undead[this.randomcar[x]]) {
                this.doitonce[x] = false;
                this.affected[x] = false;
            }
            else {
                this.doitonce[x] = true;
                this.affected[x] = true;
            }
        }
    }
    
    public int spcalc(final int level) {
        final int factor = (int)(level / 15.0);
        int leftovers = 0;
        if (factor == 1) {
            leftovers = 52;
        }
        if (factor >= 2) {
            leftovers = 52 + (int)this.round(0.5 * (15 * (factor - 1) * (factor - 1) + 135 * (factor - 1)), 0);
        }
        int leveladj = factor * 15 - 1;
        if (factor == 0) {
            leveladj = 1;
        }
        final int levelsp = (level - leveladj) * (factor + 4) + leftovers;
        return levelsp;
    }
    
    public int beastspcalc(final int level) {
        int levelsp = this.spcalc(level) + 320;
        if (level <= 21) {
            levelsp = this.spcalc(level) + (level - 1) * 16;
        }
        return levelsp;
    }
    
    public void airpgstats(final Madness[] madness, final CheckPoints checkpoints) {
        if (!this.setlevels) {
            this.startinglevel = madness[0].level[this.sc[0]];
            if (!this.scalelevels) {
                for (int b = 1; b < 31; ++b) {
                    if (checkpoints.stage == b) {
                        int variance = 3;
                        if (b == 5 || b == 14 || b == 26) {
                            variance = 4;
                        }
                        if (b == 4 || b == 6 || b == 24) {
                            variance = 2;
                        }
                        if (b == 8 || b == 10 || b == 12 || b == 16 || b == 18 || b == 22 || b == 25 || b == 29) {
                            variance = 1;
                        }
                        for (int a = 1; a < this.nplayers - 1; ++a) {
                            madness[a].level[this.sc[a]] = (int)(Math.random() * variance) + (this.maxlevel[b - 1] - variance);
                        }
                        madness[this.nplayers - 1].level[this.sc[this.nplayers - 1]] = this.maxlevel[b - 1];
                    }
                }
                if (this.bonusstage[0]) {
                    for (int a2 = 1; a2 < this.nplayers - 1; ++a2) {
                        madness[a2].level[this.sc[a2]] = Math.min(Math.max(6, madness[0].level[this.sc[0]] - 9), 20);
                    }
                    madness[this.nplayers - 1].level[this.sc[this.nplayers - 1]] = Math.min(Math.max(7, madness[0].level[this.sc[0]] - 8), 21);
                }
                if (this.bonusstage[1]) {
                    int level = madness[0].level[this.sc[0]] - 6;
                    if (level < 30) {
                        level = 30;
                    }
                    if (level > 65) {
                        level = 65;
                    }
                    madness[this.nplayers - 1].level[this.sc[this.nplayers - 1]] = level + 5;
                    for (int a3 = 1; a3 < this.nplayers - 1; ++a3) {
                        madness[a3].level[this.sc[a3]] = level;
                    }
                }
                if (this.bonusstage[2]) {
                    int level = madness[0].level[this.sc[0]] - 4;
                    if (level < 40) {
                        level = 40;
                    }
                    if (level > 80) {
                        level = 80;
                    }
                    for (int a3 = 1; a3 < 11; ++a3) {
                        madness[a3].level[this.sc[a3]] = level;
                    }
                }
                if (checkpoints.stage == 11 && !this.bonusstage[1]) {
                    for (int a2 = 1; a2 < 5; ++a2) {
                        madness[a2].level[this.sc[a2]] = 35;
                    }
                    for (int a2 = 5; a2 < this.nplayers - 1; ++a2) {
                        if ((this.hardstage || this.unlocked[1] == 11) && a2 >= this.nplayers - 3) {
                            madness[a2].level[this.sc[a2]] = 35;
                        }
                        else {
                            madness[a2].level[this.sc[a2]] = (int)(Math.random() * 2.0) + 34;
                        }
                    }
                    madness[this.nplayers - 1].level[this.sc[this.nplayers - 1]] = 36;
                }
                if (checkpoints.stage == 13) {
                    for (int a2 = 1; a2 < this.nplayers - 1; ++a2) {
                        if (a2 < this.nplayers - 6) {
                            madness[a2].level[this.sc[a2]] = (int)(Math.random() * 2.0) + 40;
                        }
                        else {
                            madness[a2].level[this.sc[a2]] = 41;
                        }
                    }
                    madness[this.nplayers - 1].level[this.sc[this.nplayers - 1]] = 42;
                }
                if (checkpoints.stage == 17) {
                    for (int a2 = 1; a2 < 4; ++a2) {
                        madness[a2].level[this.sc[a2]] = 55;
                    }
                    for (int a2 = 4; a2 < this.nplayers - 1; ++a2) {
                        madness[a2].level[this.sc[a2]] = (int)(Math.random() * 3.0) + 52;
                    }
                    madness[this.nplayers - 1].level[this.sc[this.nplayers - 1]] = 55;
                }
                if (this.bonusstage[3]) {
                    for (int a2 = 1; a2 < this.nplayers; ++a2) {
                        if (madness[0].level[this.sc[0]] <= 60) {
                            madness[a2].level[this.sc[a2]] = 60;
                        }
                        else {
                            madness[a2].level[this.sc[a2]] = madness[0].level[this.sc[0]];
                        }
                    }
                }
                if (checkpoints.stage == 21) {
                    for (int a2 = 1; a2 < this.nplayers - 3; ++a2) {
                        madness[a2].level[this.sc[a2]] = (int)(Math.random() * 4.0) + 66;
                    }
                    madness[this.nplayers - 1].level[this.sc[this.nplayers - 1]] = 70;
                    madness[this.nplayers - 2].level[this.sc[this.nplayers - 2]] = 69;
                    madness[this.nplayers - 3].level[this.sc[this.nplayers - 3]] = 69;
                }
                if (checkpoints.stage == 23) {
                    int whichnum = 1;
                    if (this.unlocked[1] == 23 || this.hardstage) {
                        madness[1].level[this.sc[1]] = 80;
                        whichnum = 2;
                    }
                    for (int a3 = whichnum; a3 < this.nplayers - 1; ++a3) {
                        madness[a3].level[this.sc[a3]] = (int)(Math.random() * 2.0) + 75;
                    }
                    madness[this.nplayers - 1].level[this.sc[this.nplayers - 1]] = 77;
                }
            }
            else {
                int level = madness[0].level[this.sc[0]];
                if (level < 3) {
                    level = 3;
                }
                if (level > this.maxlevel[this.unlocked[1] - 2] - 1) {
                    level = this.maxlevel[this.unlocked[1] - 2] - 1;
                }
                madness[this.nplayers - 1].level[this.sc[this.nplayers - 1]] = level + 1;
                for (int a3 = 1; a3 < this.nplayers - 1; ++a3) {
                    madness[a3].level[this.sc[a3]] = (int)(Math.random() * 2.0) + (level - 1);
                }
            }
            for (int g = 1; g < this.nplayers; ++g) {
                if (this.beastopponent[g] || checkpoints.stage < 16) {
                    this.bonuspoints[g] = 0;
                }
                else {
                    int carcode = this.sc[g];
                    if (carcode < 15 || carcode >= 23) {
                        carcode = 15;
                    }
                    if (carcode == 20) {
                        carcode = 18;
                    }
                    if (carcode == 21) {
                        carcode = 19;
                    }
                    int statvariance = madness[g].level[this.sc[g]] / 6 + 1;
                    double multip = 1.0;
                    if (checkpoints.stage < 20) {
                        multip = 1.0 + (checkpoints.stage - 16.0) * 0.3 + (carcode - 15.0) * 0.3;
                    }
                    if (checkpoints.stage == 20) {
                        multip = 2.0;
                        if (g == this.nplayers - 1 && !this.scalelevels) {
                            statvariance = 0;
                        }
                    }
                    if (checkpoints.stage >= 21 && checkpoints.stage <= 23) {
                        multip = 2.1 + (checkpoints.stage - 21.0) * 0.1 + (carcode - 15.0) * 0.3;
                    }
                    if (checkpoints.stage >= 24) {
                        multip = 2.5 + (checkpoints.stage - 24.0) * 0.25 + (carcode - 15.0) * 0.5;
                    }
                    this.bonuspoints[g] = (int)(madness[g].level[this.sc[g]] * multip) + (int)(this.m.random() * statvariance);
                }
                if (!this.beastopponent[g]) {
                    this.totalsp[g] = this.spcalc(madness[g].level[this.sc[g]]) + this.bonuspoints[g];
                }
                else {
                    this.totalsp[g] = this.beastspcalc(madness[g].level[this.sc[g]]);
                }
                final int[] statadjust = new int[6];
                for (int c = 0; c < 6; ++c) {
                    statadjust[c] = 0;
                    if (this.sc[g] >= 31) {
                        statadjust[c] = 7;
                    }
                    if (this.sc[g] == 36) {
                        statadjust[0] = 47;
                    }
                }
                if (this.bonstage) {
                    if ((this.bonusstage[0] || this.bonusstage[1]) && g == this.nplayers - 1) {
                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 4;
                        madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 2;
                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 4;
                    }
                    if ((this.bonusstage[2] || this.bonusstage[3]) && g == this.nplayers - 1) {
                        madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 3 / 4;
                        madness[g].aiendsp[this.sc[g]] = 60;
                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] - (madness[g].aistrsp[this.sc[g]] + madness[g].aiendsp[this.sc[g]]);
                    }
                }
                else if (g == this.nplayers - 1) {
                    if (checkpoints.stage == 1) {
                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 2;
                        madness[g].aigripsp[this.sc[g]] = this.totalsp[g] / 2;
                    }
                    if (checkpoints.stage == 2) {
                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 5 / 6;
                        madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]];
                    }
                    if (checkpoints.stage == 3) {
                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 5 / 12;
                        madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]];
                    }
                    if (checkpoints.stage == 4) {
                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 4;
                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 6;
                        madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]] - madness[g].aitssp[this.sc[g]];
                    }
                    if (checkpoints.stage == 5) {
                        madness[g].aiaccsp[this.sc[g]] = this.totalsp[g] * 1 / 6 + 2;
                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] - madness[g].aiaccsp[this.sc[g]];
                    }
                    if (checkpoints.stage == 6) {
                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 15 / 19;
                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]];
                    }
                    if (checkpoints.stage == 7) {
                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 4;
                        madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 5 / 8;
                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aistrsp[this.sc[g]] - madness[g].aitssp[this.sc[g]];
                    }
                    if (checkpoints.stage == 8) {
                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 5;
                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 5;
                        madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]] - madness[g].aitssp[this.sc[g]];
                    }
                    if (checkpoints.stage == 9) {
                        float goalgrip = 24.2f + (madness[this.nplayers - 1].level[this.sc[this.nplayers - 1]] * 3 - 1) * 0.2f;
                        if (goalgrip > 42.0f) {
                            goalgrip = 42.0f;
                        }
                        final double startgrip = madness[g].gripreset[this.sc[g]] + (madness[g].level[this.sc[g]] + (double)statadjust[2] - 1.0) / 5.0;
                        final double needgrip = this.round(goalgrip - startgrip, 1);
                        int gripstats = (int)(needgrip * 5.0);
                        if (needgrip < 0.0) {
                            gripstats = 0;
                        }
                        if (this.totalsp[g] < gripstats) {
                            madness[g].aigripsp[this.sc[g]] = this.totalsp[g];
                        }
                        else {
                            madness[g].aigripsp[this.sc[g]] = gripstats;
                            final int remaining = this.totalsp[g] - gripstats;
                            madness[g].aitssp[this.sc[g]] = remaining * 5 / 6;
                            madness[g].aiendsp[this.sc[g]] = remaining - madness[g].aitssp[this.sc[g]];
                        }
                    }
                    if (checkpoints.stage == 10) {
                        madness[g].aitssp[this.sc[g]] = this.totalsp[g];
                    }
                    if (checkpoints.stage == 11) {
                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 5 / 28;
                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 2 / 13;
                        madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]] - madness[g].aitssp[this.sc[g]];
                    }
                    if (checkpoints.stage == 12) {
                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 35 / 152;
                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 33 / 152;
                        madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]] - madness[g].aitssp[this.sc[g]];
                    }
                    if (checkpoints.stage == 13) {
                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 4 / 5;
                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]];
                    }
                    if (checkpoints.stage == 14) {
                        madness[g].aistusp[this.sc[g]] = this.totalsp[g] * 148 / 224;
                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] - madness[g].aistusp[this.sc[g]];
                    }
                    if (checkpoints.stage == 15) {
                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 5 / 96;
                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 35 / 96;
                        madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]] - madness[g].aitssp[this.sc[g]];
                    }
                    if (checkpoints.stage == 16) {
                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 5 / 16;
                        madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 7 / 16;
                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - (madness[g].aitssp[this.sc[g]] + madness[g].aistrsp[this.sc[g]]);
                    }
                    if (checkpoints.stage == 17) {
                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 4;
                        madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 9 / 16;
                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - (madness[g].aitssp[this.sc[g]] + madness[g].aistrsp[this.sc[g]]);
                    }
                    if (checkpoints.stage == 18) {
                        madness[g].aitssp[this.sc[g]] = 163;
                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 16;
                        madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - 163 - madness[g].aiendsp[this.sc[g]];
                    }
                    if (checkpoints.stage == 19) {
                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 23 / 32;
                        madness[g].aigripsp[this.sc[g]] = this.totalsp[g] * 3 / 32;
                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]] - madness[g].aigripsp[this.sc[g]];
                    }
                    if (checkpoints.stage == 20) {
                        madness[g].aitssp[this.sc[g]] = this.totalsp[g];
                    }
                    if (checkpoints.stage == 21) {
                        madness[g].aiendsp[this.sc[g]] = 40;
                        madness[g].aitssp[this.sc[g]] = 120;
                        madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - 160;
                    }
                    if (checkpoints.stage == 22) {
                        madness[g].aitssp[this.sc[g]] = 70;
                        madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - 70;
                    }
                    if (checkpoints.stage == 23) {
                        madness[g].aiendsp[this.sc[g]] = 40;
                        madness[g].aitssp[this.sc[g]] = 135;
                        madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - 175;
                    }
                    if (checkpoints.stage == 24) {
                        if (madness[0].moment[this.sc[0]] <= 13.65f) {
                            madness[g].aitssp[this.sc[g]] = 98;
                            madness[g].aistrsp[this.sc[g]] = 490;
                            madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - 588;
                        }
                        else {
                            madness[g].aitssp[this.sc[g]] = 98;
                            madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - 98;
                        }
                    }
                    if (checkpoints.stage > 24) {
                        madness[g].aiendsp[this.sc[g]] = 40;
                        madness[g].aitssp[this.sc[g]] = 135;
                        madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - 175;
                    }
                }
                if (g < this.nplayers - 1) {
                    if (this.sc[g] == 0 || this.sc[g] == 23) {
                        if (checkpoints.stage != 6 && checkpoints.stage != 9 && checkpoints.stage != 11) {
                            if (!this.beastopponent[g]) {
                                madness[g].aistusp[this.sc[g]] = this.totalsp[g] / 2;
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 6;
                                madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - madness[g].aistusp[this.sc[g]] - madness[g].aitssp[this.sc[g]];
                            }
                            else {
                                madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 2;
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 3 / 8;
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 8;
                            }
                        }
                        else {
                            if (checkpoints.stage == 6) {
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 4;
                                madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 4;
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 2;
                            }
                            if (checkpoints.stage == 9 || checkpoints.stage == 11) {
                                final double startgrip2 = madness[g].gripreset[this.sc[g]] + (madness[g].level[this.sc[g]] + (double)statadjust[2] - 1.0) / 5.0;
                                double whatneed = 24.2 + (madness[this.nplayers - 1].level[this.sc[this.nplayers - 1]] * 3 - 1) * 0.2;
                                if (whatneed > 42.0) {
                                    whatneed = 42.0;
                                }
                                if (checkpoints.stage == 11) {
                                    whatneed = 24.2 + (madness[this.nplayers - 1].level[this.sc[this.nplayers - 1]] * 3 - 1) * 0.2;
                                    if (whatneed > 45.6) {
                                        whatneed = 45.6;
                                    }
                                }
                                final double needgrip2 = this.round(whatneed - startgrip2, 1);
                                int gripstats2 = (int)(needgrip2 * 5.0);
                                if (needgrip2 < 0.0) {
                                    gripstats2 = 0;
                                }
                                if (this.totalsp[g] < gripstats2) {
                                    madness[g].aigripsp[this.sc[g]] = this.totalsp[g];
                                }
                                else {
                                    madness[g].aigripsp[this.sc[g]] = gripstats2;
                                    final int remaining2 = this.totalsp[g] - gripstats2;
                                    if (checkpoints.stage == 9) {
                                        madness[g].aitssp[this.sc[g]] = remaining2 * 3 / 4;
                                        madness[g].aiendsp[this.sc[g]] = remaining2 - madness[g].aitssp[this.sc[g]];
                                    }
                                    if (checkpoints.stage == 11) {
                                        if (!this.beastopponent[g]) {
                                            madness[g].aiendsp[this.sc[g]] = remaining2 * 3 / 4;
                                            madness[g].aitssp[this.sc[g]] = remaining2 - madness[g].aiendsp[this.sc[g]];
                                        }
                                        else {
                                            madness[g].aiendsp[this.sc[g]] = remaining2 / 2;
                                            madness[g].aistrsp[this.sc[g]] = remaining2 * 3 / 8;
                                            madness[g].aitssp[this.sc[g]] = remaining2 - madness[g].aiendsp[this.sc[g]] - madness[g].aistrsp[this.sc[g]];
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (this.sc[g] == 1 || this.sc[g] == 24) {
                        if (checkpoints.stage != 6 && !this.beastopponent[g] && checkpoints.stage != 10) {
                            if (checkpoints.stage != 14 && checkpoints.stage != 5) {
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 2;
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 2;
                            }
                            else {
                                if (checkpoints.stage == 14) {
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 3 / 4;
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 4;
                                }
                                if (checkpoints.stage == 5) {
                                    final float userstrength = madness[0].momentreset[this.sc[0]] + madness[0].aistrsp[this.sc[0]] * 0.025f;
                                    if (userstrength >= 2.5f) {
                                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 2;
                                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 2;
                                    }
                                    else {
                                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 3 / 4;
                                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 4;
                                    }
                                }
                            }
                        }
                        else {
                            if (checkpoints.stage == 10) {
                                final float userstrength = madness[0].momentreset[this.sc[0]] + madness[0].aistrsp[this.sc[0]] * 0.025f;
                                if (userstrength < 4.0f) {
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 4;
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]];
                                }
                                else {
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 3 / 4;
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]];
                                }
                            }
                            else {
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 4;
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 3 / 4;
                            }
                            if (checkpoints.stage == 5 && this.beastopponent[g]) {
                                madness[g].aitssp[this.sc[g]] = this.spcalc(madness[g].level[this.sc[g]] / 2);
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aitssp[this.sc[g]];
                            }
                        }
                    }
                    if (this.sc[g] == 2 || this.sc[g] == 25) {
                        if ((checkpoints.stage != 6 && checkpoints.stage != 7 && checkpoints.stage != 8) || !this.beastopponent[g]) {
                            madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 2;
                            madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 2;
                        }
                        else {
                            boolean afuckingracer = false;
                            final int totalpoints = this.spcalc(madness[0].level[this.sc[0]]) + this.extpoints[this.sc[0]];
                            final int instrength = totalpoints / 8;
                            if (madness[0].aistrsp[this.sc[0]] - madness[0].level[this.sc[0]] + 1 < instrength) {
                                afuckingracer = true;
                            }
                            int extras = 65;
                            if (this.scalelevels) {
                                extras = 0;
                            }
                            if (!afuckingracer) {
                                if (checkpoints.stage == 6) {
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 3 / 16;
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 8;
                                    madness[g].aiaccsp[this.sc[g]] = extras;
                                    madness[g].aistusp[this.sc[g]] = extras;
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aistrsp[this.sc[g]] - madness[g].aitssp[this.sc[g]] - extras * 2;
                                }
                                if (checkpoints.stage == 7) {
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 7 / 32;
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 5 / 32;
                                    madness[g].aistusp[this.sc[g]] = extras - this.totalsp[g] / 32;
                                    madness[g].aiaccsp[this.sc[g]] = extras - this.totalsp[g] / 32;
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aistrsp[this.sc[g]] - madness[g].aitssp[this.sc[g]] - madness[g].aistusp[this.sc[g]] - madness[g].aiaccsp[this.sc[g]];
                                }
                            }
                            else {
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 3 / 16;
                                if (checkpoints.stage == 6) {
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 5 / 16;
                                }
                                if (checkpoints.stage == 7) {
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 3 / 16;
                                }
                                madness[g].aistusp[this.sc[g]] = extras;
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]] - madness[g].aistrsp[this.sc[g]] - extras;
                            }
                            if (checkpoints.stage == 8) {
                                if (afuckingracer) {
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 2;
                                }
                                else {
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 5 / 16;
                                }
                                madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 5 / 16;
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]] - madness[g].aistrsp[this.sc[g]];
                            }
                        }
                    }
                    if (this.sc[g] == 3 || this.sc[g] == 26) {
                        if (checkpoints.stage != 10 && checkpoints.stage != 5) {
                            if (checkpoints.stage != 14 && checkpoints.stage != 9) {
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 2;
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 2;
                            }
                            else {
                                if (checkpoints.stage == 14) {
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 3 / 4;
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 4;
                                }
                                if (checkpoints.stage == 9) {
                                    final double startgrip2 = madness[g].gripreset[this.sc[g]] + (madness[g].level[this.sc[g]] + (double)statadjust[2] - 1.0) / 5.0;
                                    double whatneed = 24.2 + (madness[this.nplayers - 1].level[this.sc[this.nplayers - 1]] * 3 - 1) * 0.2;
                                    if (whatneed > 42.0) {
                                        whatneed = 42.0;
                                    }
                                    final double needgrip2 = this.round(whatneed - startgrip2, 1);
                                    int gripstats2 = (int)(needgrip2 * 5.0);
                                    if (needgrip2 < 0.0) {
                                        gripstats2 = 0;
                                    }
                                    if (this.totalsp[g] < gripstats2) {
                                        madness[g].aigripsp[this.sc[g]] = this.totalsp[g];
                                    }
                                    else {
                                        madness[g].aigripsp[this.sc[g]] = gripstats2;
                                        final int remaining2 = this.totalsp[g] - gripstats2;
                                        madness[g].aitssp[this.sc[g]] = remaining2 * 3 / 4;
                                        madness[g].aiendsp[this.sc[g]] = remaining2 - madness[g].aitssp[this.sc[g]];
                                    }
                                }
                            }
                        }
                        else {
                            final float userstrength = madness[0].momentreset[this.sc[0]] + madness[0].aistrsp[this.sc[0]] * 0.025f;
                            if (userstrength < 4.0f || (userstrength < 2.5f && checkpoints.stage == 5)) {
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 5 / 16;
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]];
                            }
                            else {
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 3 / 4;
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]];
                            }
                        }
                    }
                    if (this.sc[g] == 4 || this.sc[g] == 27) {
                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 9 / 16;
                        madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 4;
                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] - (madness[g].aiendsp[this.sc[g]] + madness[g].aistrsp[this.sc[g]]);
                    }
                    if (this.sc[g] == 5 || this.sc[g] == 28) {
                        if (checkpoints.stage != 6 && checkpoints.stage != 10 && checkpoints.stage != 5 && checkpoints.stage != 7 && checkpoints.stage != 9 && checkpoints.stage != 13) {
                            madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 3 / 8;
                            madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 3 / 8;
                            if (this.beastopponent[g]) {
                                madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 2;
                            }
                            madness[g].aitssp[this.sc[g]] = this.totalsp[g] - madness[g].aistrsp[this.sc[g]] - madness[g].aiendsp[this.sc[g]];
                        }
                        else {
                            if (checkpoints.stage == 5) {
                                final float userstrength = madness[0].momentreset[this.sc[0]] + madness[0].aistrsp[this.sc[0]] * 0.025f;
                                if (userstrength < 2.5f) {
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 3 / 8;
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]];
                                }
                                else {
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 5 / 8;
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]];
                                }
                                if (this.beastopponent[g]) {
                                    madness[g].aitssp[this.sc[g]] = this.spcalc(madness[g].level[this.sc[g]]) * 21 / 32;
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 3;
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aistrsp[this.sc[g]] - madness[g].aitssp[this.sc[g]];
                                }
                            }
                            if (checkpoints.stage == 6 || checkpoints.stage == 7) {
                                if (!this.beastopponent[g]) {
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 2;
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 2;
                                }
                                else {
                                    boolean afuckingracer = false;
                                    final int totalpoints = this.spcalc(madness[0].level[this.sc[0]]) + this.extpoints[this.sc[0]];
                                    final int instrength = totalpoints / 8;
                                    if (madness[0].aistrsp[this.sc[0]] - madness[0].level[this.sc[0]] + 1 < instrength) {
                                        afuckingracer = true;
                                    }
                                    int extras = 65;
                                    if (this.scalelevels) {
                                        extras = 0;
                                    }
                                    if (!afuckingracer) {
                                        if (checkpoints.stage == 6) {
                                            madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 3 / 16;
                                            madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 8;
                                            madness[g].aiaccsp[this.sc[g]] = extras;
                                            madness[g].aistusp[this.sc[g]] = extras;
                                            madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aistrsp[this.sc[g]] - madness[g].aitssp[this.sc[g]] - extras * 2;
                                        }
                                        if (checkpoints.stage == 7) {
                                            madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 7 / 32;
                                            madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 5 / 32;
                                            madness[g].aistusp[this.sc[g]] = extras - this.totalsp[g] / 32;
                                            madness[g].aiaccsp[this.sc[g]] = extras - this.totalsp[g] / 32;
                                            madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aistrsp[this.sc[g]] - madness[g].aitssp[this.sc[g]] - madness[g].aistusp[this.sc[g]] - madness[g].aiaccsp[this.sc[g]];
                                        }
                                    }
                                    else {
                                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 3 / 16;
                                        if (checkpoints.stage == 6) {
                                            madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 5 / 16;
                                        }
                                        if (checkpoints.stage == 7) {
                                            madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 3 / 16;
                                        }
                                        madness[g].aistusp[this.sc[g]] = extras;
                                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]] - madness[g].aistrsp[this.sc[g]] - extras;
                                    }
                                }
                            }
                            if (checkpoints.stage == 9) {
                                final double startgrip2 = madness[g].gripreset[this.sc[g]] + (madness[g].level[this.sc[g]] + (double)statadjust[2] - 1.0) / 5.0;
                                double whatneed = 24.2 + (madness[this.nplayers - 1].level[this.sc[this.nplayers - 1]] * 3 - 1) * 0.2;
                                if (whatneed > 42.0) {
                                    whatneed = 42.0;
                                }
                                final double needgrip2 = this.round(whatneed - startgrip2, 1);
                                int gripstats2 = (int)(needgrip2 * 5.0);
                                if (needgrip2 < 0.0) {
                                    gripstats2 = 0;
                                }
                                if (this.totalsp[g] < gripstats2) {
                                    madness[g].aigripsp[this.sc[g]] = this.totalsp[g];
                                }
                                else {
                                    madness[g].aigripsp[this.sc[g]] = gripstats2;
                                    final int remaining2 = this.totalsp[g] - gripstats2;
                                    madness[g].aitssp[this.sc[g]] = remaining2 * 3 / 4;
                                    madness[g].aiendsp[this.sc[g]] = remaining2 - madness[g].aitssp[this.sc[g]];
                                }
                            }
                            if (checkpoints.stage == 10) {
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 3 / 8;
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]];
                            }
                            if (checkpoints.stage == 13) {
                                madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 2;
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 8;
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aistrsp[this.sc[g]] - madness[g].aitssp[this.sc[g]];
                            }
                        }
                    }
                    if (this.sc[g] == 6 || this.sc[g] == 29) {
                        if (!this.beastopponent[g] && checkpoints.stage != 13) {
                            if (checkpoints.stage != 9 && checkpoints.stage != 11) {
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 9 / 16;
                                madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]];
                            }
                            else {
                                if (checkpoints.stage == 9) {
                                    final double startgrip2 = madness[g].gripreset[this.sc[g]] + (madness[g].level[this.sc[g]] + (double)statadjust[2] - 1.0) / 5.0;
                                    double whatneed = 24.2 + (madness[this.nplayers - 1].level[this.sc[this.nplayers - 1]] * 3 - 1) * 0.2;
                                    if (whatneed > 42.0) {
                                        whatneed = 42.0;
                                    }
                                    final double needgrip2 = this.round(whatneed - startgrip2, 1);
                                    int gripstats2 = (int)(needgrip2 * 5.0);
                                    if (needgrip2 < 0.0) {
                                        gripstats2 = 0;
                                    }
                                    if (this.totalsp[g] < gripstats2) {
                                        madness[g].aigripsp[this.sc[g]] = this.totalsp[g];
                                    }
                                    else {
                                        madness[g].aigripsp[this.sc[g]] = gripstats2;
                                        final int remaining2 = this.totalsp[g] - gripstats2;
                                        madness[g].aitssp[this.sc[g]] = remaining2 * 3 / 4;
                                        madness[g].aiendsp[this.sc[g]] = remaining2 - madness[g].aitssp[this.sc[g]];
                                    }
                                }
                                if (checkpoints.stage == 11) {
                                    final double startgrip2 = madness[g].gripreset[this.sc[g]] + (madness[g].level[this.sc[g]] + (double)statadjust[2] - 1.0) / 5.0;
                                    double whatneed = 24.2 + (madness[this.nplayers - 1].level[this.sc[this.nplayers - 1]] * 3 - 1) * 0.2;
                                    if (whatneed > 45.6) {
                                        whatneed = 45.6;
                                    }
                                    final double needgrip2 = this.round(whatneed - startgrip2, 1);
                                    int gripstats2 = (int)(needgrip2 * 5.0);
                                    if (needgrip2 < 0.0) {
                                        gripstats2 = 0;
                                    }
                                    if (this.totalsp[g] < gripstats2) {
                                        madness[g].aigripsp[this.sc[g]] = this.totalsp[g];
                                    }
                                    else {
                                        madness[g].aigripsp[this.sc[g]] = gripstats2;
                                        final int remaining2 = this.totalsp[g] - gripstats2;
                                        madness[g].aiendsp[this.sc[g]] = remaining2 / 2;
                                        madness[g].aistrsp[this.sc[g]] = remaining2 * 3 / 8;
                                        madness[g].aitssp[this.sc[g]] = remaining2 - madness[g].aiendsp[this.sc[g]] - madness[g].aistrsp[this.sc[g]];
                                    }
                                }
                            }
                        }
                        else if (this.beastopponent[g] && checkpoints.stage >= 6 && checkpoints.stage <= 8) {
                            boolean afuckingracer = false;
                            final int totalpoints = this.spcalc(madness[0].level[this.sc[0]]) + this.extpoints[this.sc[0]];
                            final int instrength = totalpoints / 8;
                            if (madness[0].aistrsp[this.sc[0]] - madness[0].level[this.sc[0]] + 1 < instrength) {
                                afuckingracer = true;
                            }
                            int extras = 65;
                            if (this.scalelevels) {
                                extras = 0;
                            }
                            if (!afuckingracer) {
                                if (checkpoints.stage == 6) {
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 3 / 16;
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 8;
                                    madness[g].aiaccsp[this.sc[g]] = extras;
                                    madness[g].aistusp[this.sc[g]] = extras;
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aistrsp[this.sc[g]] - madness[g].aitssp[this.sc[g]] - extras * 2;
                                }
                                if (checkpoints.stage == 7) {
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 7 / 32;
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 5 / 32;
                                    madness[g].aistusp[this.sc[g]] = extras - this.totalsp[g] / 32;
                                    madness[g].aiaccsp[this.sc[g]] = extras - this.totalsp[g] / 32;
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aistrsp[this.sc[g]] - madness[g].aitssp[this.sc[g]] - madness[g].aistusp[this.sc[g]] - madness[g].aiaccsp[this.sc[g]];
                                }
                            }
                            else {
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 3 / 16;
                                if (checkpoints.stage == 6) {
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 5 / 16;
                                }
                                if (checkpoints.stage == 7) {
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 3 / 16;
                                }
                                madness[g].aistusp[this.sc[g]] = extras;
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]] - madness[g].aistrsp[this.sc[g]] - extras;
                            }
                            if (checkpoints.stage == 8) {
                                if (afuckingracer) {
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 2;
                                }
                                else {
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 5 / 16;
                                }
                                madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 5 / 16;
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]] - madness[g].aistrsp[this.sc[g]];
                            }
                        }
                        else {
                            madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 2;
                            madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 2;
                        }
                    }
                    if (this.sc[g] == 7 || this.sc[g] == 30) {
                        if (checkpoints.stage != 6 && checkpoints.stage != 7 && checkpoints.stage != 8) {
                            if (!this.beastopponent[g]) {
                                madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 3;
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 6;
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aitssp[this.sc[g]] - madness[g].aistrsp[this.sc[g]];
                            }
                            else {
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 4;
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 3 / 8;
                                madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - madness[g].aitssp[this.sc[g]] - madness[g].aiendsp[this.sc[g]];
                            }
                        }
                        else if (!this.beastopponent[g]) {
                            madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 2;
                            madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 2;
                        }
                        else {
                            boolean afuckingracer = false;
                            final int totalpoints = this.spcalc(madness[0].level[this.sc[0]]) + this.extpoints[this.sc[0]];
                            final int instrength = totalpoints / 8;
                            if (madness[0].aistrsp[this.sc[0]] - madness[0].level[this.sc[0]] + 1 < instrength) {
                                afuckingracer = true;
                            }
                            int extras = 65;
                            if (this.scalelevels) {
                                extras = 0;
                            }
                            if (!afuckingracer) {
                                if (checkpoints.stage == 6) {
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 3 / 16;
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 8;
                                    madness[g].aiaccsp[this.sc[g]] = extras;
                                    madness[g].aistusp[this.sc[g]] = extras;
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aistrsp[this.sc[g]] - madness[g].aitssp[this.sc[g]] - extras * 2;
                                }
                                if (checkpoints.stage == 7) {
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 7 / 32;
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 5 / 32;
                                    madness[g].aistusp[this.sc[g]] = extras - this.totalsp[g] / 32;
                                    madness[g].aiaccsp[this.sc[g]] = extras - this.totalsp[g] / 32;
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aistrsp[this.sc[g]] - madness[g].aitssp[this.sc[g]] - madness[g].aistusp[this.sc[g]] - madness[g].aiaccsp[this.sc[g]];
                                }
                            }
                            else {
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 3 / 16;
                                if (checkpoints.stage == 6) {
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 5 / 16;
                                }
                                if (checkpoints.stage == 7) {
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 3 / 16;
                                }
                                madness[g].aistusp[this.sc[g]] = extras;
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]] - madness[g].aistrsp[this.sc[g]] - extras;
                            }
                            if (checkpoints.stage == 8) {
                                if (afuckingracer) {
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 2;
                                }
                                else {
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 5 / 16;
                                }
                                madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 5 / 16;
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]] - madness[g].aistrsp[this.sc[g]];
                            }
                        }
                    }
                    if (this.sc[g] == 8 || this.sc[g] == 31) {
                        if ((checkpoints.stage < 5 || checkpoints.stage > 14) && !this.beastopponent[g]) {
                            madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 3 / 8;
                            madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 8;
                            madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aistrsp[this.sc[g]] - madness[g].aitssp[this.sc[g]];
                        }
                        else if (this.beastopponent[g]) {
                            if (checkpoints.stage >= 6 && checkpoints.stage <= 8) {
                                boolean afuckingracer = false;
                                final int totalpoints = this.spcalc(madness[0].level[this.sc[0]]) + this.extpoints[this.sc[0]];
                                final int instrength = totalpoints / 8;
                                if (madness[0].aistrsp[this.sc[0]] - madness[0].level[this.sc[0]] + 1 < instrength) {
                                    afuckingracer = true;
                                }
                                int extras = 65;
                                if (this.scalelevels) {
                                    extras = 0;
                                }
                                if (!afuckingracer) {
                                    if (checkpoints.stage == 6) {
                                        madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 3 / 16;
                                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 8;
                                        madness[g].aiaccsp[this.sc[g]] = extras;
                                        madness[g].aistusp[this.sc[g]] = extras;
                                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aistrsp[this.sc[g]] - madness[g].aitssp[this.sc[g]] - extras * 2;
                                    }
                                    if (checkpoints.stage == 7) {
                                        madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 7 / 32;
                                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 5 / 32;
                                        madness[g].aistusp[this.sc[g]] = extras - this.totalsp[g] / 32;
                                        madness[g].aiaccsp[this.sc[g]] = extras - this.totalsp[g] / 32;
                                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aistrsp[this.sc[g]] - madness[g].aitssp[this.sc[g]] - madness[g].aistusp[this.sc[g]] - madness[g].aiaccsp[this.sc[g]];
                                    }
                                }
                                else {
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 3 / 16;
                                    if (checkpoints.stage == 6) {
                                        madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 5 / 16;
                                    }
                                    if (checkpoints.stage == 7) {
                                        madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 3 / 16;
                                    }
                                    madness[g].aistusp[this.sc[g]] = extras;
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]] - madness[g].aistrsp[this.sc[g]] - extras;
                                }
                                if (checkpoints.stage == 8) {
                                    if (afuckingracer) {
                                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 2;
                                    }
                                    else {
                                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 5 / 16;
                                    }
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 5 / 16;
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]] - madness[g].aistrsp[this.sc[g]];
                                }
                            }
                            else if (checkpoints.stage == 12 || checkpoints.stage == 13) {
                                if (checkpoints.stage == 12) {
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 9 / 64;
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 5 / 16;
                                    madness[g].aigripsp[this.sc[g]] = this.totalsp[g] / 16;
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - (madness[g].aitssp[this.sc[g]] + madness[g].aistrsp[this.sc[g]] + madness[g].aigripsp[this.sc[g]]);
                                }
                                else {
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 2;
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 8;
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aistrsp[this.sc[g]] - madness[g].aitssp[this.sc[g]];
                                }
                            }
                            else {
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 3 / 32;
                                madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 3 / 8;
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - (madness[g].aitssp[this.sc[g]] + madness[g].aistrsp[this.sc[g]]);
                            }
                        }
                        else {
                            if (checkpoints.stage == 10 || checkpoints.stage == 5) {
                                final float userstrength = madness[0].momentreset[this.sc[0]] + madness[0].aistrsp[this.sc[0]] * 0.025f;
                                if (userstrength < 4.0f || (userstrength < 2.5f && checkpoints.stage == 5)) {
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 3 / 8;
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]];
                                }
                                else {
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 3 / 4;
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]];
                                }
                            }
                            if (checkpoints.stage == 9) {
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 2;
                                madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 2;
                            }
                            if (checkpoints.stage == 11) {
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g];
                            }
                            if (checkpoints.stage == 12) {
                                madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 4;
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 3 / 4;
                            }
                            if (checkpoints.stage == 14) {
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 3 / 4;
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 4;
                            }
                            if (checkpoints.stage == 6 || checkpoints.stage == 7) {
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 8;
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 9 / 16;
                                madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - madness[g].aitssp[this.sc[g]] - madness[g].aiendsp[this.sc[g]];
                            }
                            if (checkpoints.stage == 8) {
                                madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 2;
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 8;
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aistrsp[this.sc[g]] - madness[g].aitssp[this.sc[g]];
                            }
                            if (checkpoints.stage == 13) {
                                madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 2;
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 8;
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aistrsp[this.sc[g]] - madness[g].aitssp[this.sc[g]];
                            }
                        }
                    }
                    if (this.sc[g] == 9 || this.sc[g] == 32) {
                        if (((checkpoints.stage != 11 && checkpoints.stage != 5 && checkpoints.stage != 8 && checkpoints.stage != 6 && checkpoints.stage != 7 && checkpoints.stage != 12 && checkpoints.stage != 16 && checkpoints.stage != 17 && checkpoints.stage != 18) || this.beastopponent[g]) && !this.bonusstage[2]) {
                            if (!this.beastopponent[g] || checkpoints.stage != 12) {
                                if (checkpoints.stage != 16 && checkpoints.stage != 17 && checkpoints.stage != 18 && checkpoints.stage != 6 && checkpoints.stage != 7 && checkpoints.stage != 8) {
                                    if (checkpoints.stage != 10 && checkpoints.stage != 11 && checkpoints.stage != 13) {
                                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 2;
                                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 4;
                                        madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 4;
                                    }
                                    else {
                                        if (checkpoints.stage == 10) {
                                            final float userstrength = madness[0].momentreset[this.sc[0]] + madness[0].aistrsp[this.sc[0]] * 0.025f;
                                            if (userstrength < 4.0f) {
                                                madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 4;
                                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 4;
                                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] - madness[g].aistrsp[this.sc[g]] - madness[g].aiendsp[this.sc[g]];
                                            }
                                            else {
                                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 3 / 4;
                                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 4;
                                            }
                                        }
                                        if (checkpoints.stage == 13) {
                                            madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 2;
                                            madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 8;
                                            madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aistrsp[this.sc[g]] - madness[g].aitssp[this.sc[g]];
                                        }
                                        if (checkpoints.stage == 11) {
                                            final double startgrip2 = madness[g].gripreset[this.sc[g]] + (madness[g].level[this.sc[g]] + (double)statadjust[2] - 1.0) / 5.0;
                                            double whatneed = 24.2 + (madness[this.nplayers - 1].level[this.sc[this.nplayers - 1]] * 3 - 1) * 0.2;
                                            if (whatneed > 45.6) {
                                                whatneed = 45.6;
                                            }
                                            final double needgrip2 = this.round(whatneed - startgrip2, 1);
                                            int gripstats2 = (int)(needgrip2 * 5.0);
                                            if (needgrip2 < 0.0) {
                                                gripstats2 = 0;
                                            }
                                            if (this.totalsp[g] < gripstats2) {
                                                madness[g].aigripsp[this.sc[g]] = this.totalsp[g];
                                            }
                                            else {
                                                madness[g].aigripsp[this.sc[g]] = gripstats2;
                                                final int remaining2 = this.totalsp[g] - gripstats2;
                                                madness[g].aistrsp[this.sc[g]] = remaining2 * 13 / 32;
                                                madness[g].aitssp[this.sc[g]] = remaining2 * 3 / 32;
                                                madness[g].aiendsp[this.sc[g]] = remaining2 - madness[g].aistrsp[this.sc[g]] - madness[g].aitssp[this.sc[g]];
                                            }
                                        }
                                    }
                                }
                                else if (checkpoints.stage != 6 && checkpoints.stage != 7 && checkpoints.stage != 8) {
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 2;
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 4;
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 4;
                                }
                                else {
                                    boolean afuckingracer = false;
                                    final int totalpoints = this.spcalc(madness[0].level[this.sc[0]]) + this.extpoints[this.sc[0]];
                                    final int instrength = totalpoints / 8;
                                    if (madness[0].aistrsp[this.sc[0]] - madness[0].level[this.sc[0]] + 1 < instrength) {
                                        afuckingracer = true;
                                    }
                                    int extras = 65;
                                    if (this.scalelevels) {
                                        extras = 0;
                                    }
                                    if (!afuckingracer) {
                                        if (checkpoints.stage == 6) {
                                            madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 3 / 16;
                                            madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 8;
                                            madness[g].aiaccsp[this.sc[g]] = extras;
                                            madness[g].aistusp[this.sc[g]] = extras;
                                            madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aistrsp[this.sc[g]] - madness[g].aitssp[this.sc[g]] - extras * 2;
                                        }
                                        if (checkpoints.stage == 7) {
                                            madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 7 / 32;
                                            madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 5 / 32;
                                            madness[g].aistusp[this.sc[g]] = extras - this.totalsp[g] / 32;
                                            madness[g].aiaccsp[this.sc[g]] = extras - this.totalsp[g] / 32;
                                            madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aistrsp[this.sc[g]] - madness[g].aitssp[this.sc[g]] - madness[g].aistusp[this.sc[g]] - madness[g].aiaccsp[this.sc[g]];
                                        }
                                    }
                                    else {
                                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 3 / 16;
                                        if (checkpoints.stage == 6) {
                                            madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 5 / 16;
                                        }
                                        if (checkpoints.stage == 7) {
                                            madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 3 / 16;
                                        }
                                        madness[g].aistusp[this.sc[g]] = extras;
                                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]] - madness[g].aistrsp[this.sc[g]] - extras;
                                    }
                                    if (checkpoints.stage == 8) {
                                        if (afuckingracer) {
                                            madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 2;
                                        }
                                        else {
                                            madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 5 / 16;
                                        }
                                        madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 5 / 16;
                                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]] - madness[g].aistrsp[this.sc[g]];
                                    }
                                }
                            }
                            else {
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 9 / 64;
                                madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 5 / 16;
                                madness[g].aigripsp[this.sc[g]] = this.totalsp[g] / 16;
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - (madness[g].aitssp[this.sc[g]] + madness[g].aistrsp[this.sc[g]] + madness[g].aigripsp[this.sc[g]]);
                            }
                        }
                        else {
                            if (checkpoints.stage == 11) {
                                final double startgrip2 = madness[g].gripreset[this.sc[g]] + (madness[g].level[this.sc[g]] + (double)statadjust[2] - 1.0) / 5.0;
                                double whatneed = 24.2 + (madness[this.nplayers - 1].level[this.sc[this.nplayers - 1]] * 3 - 1) * 0.2;
                                if (whatneed > 45.6) {
                                    whatneed = 45.6;
                                }
                                final double needgrip2 = this.round(whatneed - startgrip2, 1);
                                int gripstats2 = (int)(needgrip2 * 5.0);
                                if (needgrip2 < 0.0) {
                                    gripstats2 = 0;
                                }
                                if (this.totalsp[g] < gripstats2) {
                                    madness[g].aigripsp[this.sc[g]] = this.totalsp[g];
                                }
                                else {
                                    madness[g].aigripsp[this.sc[g]] = gripstats2;
                                    final int remaining2 = this.totalsp[g] - gripstats2;
                                    madness[g].aistrsp[this.sc[g]] = remaining2 * 3 / 4;
                                    madness[g].aiendsp[this.sc[g]] = remaining2 - madness[g].aistrsp[this.sc[g]];
                                }
                            }
                            if (checkpoints.stage == 12 || (checkpoints.stage == 17 && !madness[g].shadowcar)) {
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 2 / 3;
                                madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]];
                            }
                            if (checkpoints.stage == 16) {
                                if (!madness[g].shadowcar || this.scalelevels) {
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 2 / 3;
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]];
                                }
                                else {
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 2 / 3;
                                    final int alreadyhave = madness[g].level[this.sc[g]] + statadjust[2] + 49;
                                    madness[g].aigripsp[this.sc[g]] = 122 - alreadyhave;
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]] - madness[g].aigripsp[this.sc[g]];
                                }
                            }
                            if (checkpoints.stage == 5) {
                                madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 2;
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 2;
                            }
                            if (checkpoints.stage == 7 || checkpoints.stage == 8) {
                                madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 5 / 8;
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 8;
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aistrsp[this.sc[g]] - madness[g].aitssp[this.sc[g]];
                            }
                            if ((checkpoints.stage == 17 || checkpoints.stage == 18) && madness[g].shadowcar) {
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 4;
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 3 / 4;
                            }
                            if (checkpoints.stage == 18 && !madness[g].shadowcar) {
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 2;
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 2;
                            }
                            if (checkpoints.stage == 6) {
                                madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 3 / 4;
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 4;
                            }
                            if (this.bonusstage[2]) {
                                madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 2;
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 3 / 32;
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - (madness[g].aistrsp[this.sc[g]] + madness[g].aitssp[this.sc[g]]);
                            }
                        }
                    }
                    if (this.sc[g] == 10 || this.sc[g] == 33) {
                        if ((checkpoints.stage < 8 || checkpoints.stage > 16) && !this.beastopponent[g]) {
                            madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 4;
                            madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aitssp[this.sc[g]];
                        }
                        else {
                            if (this.beastopponent[g] && (checkpoints.stage != 15 || this.bonusstage[2]) && checkpoints.stage != 7 && checkpoints.stage != 8 && checkpoints.stage != 13) {
                                if (!this.bonstage) {
                                    if (checkpoints.stage != 16 || this.scalelevels) {
                                        if (checkpoints.stage < 8) {
                                            madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 3 / 4;
                                            madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]];
                                        }
                                        else if (checkpoints.stage == 11) {
                                            final double startgrip2 = madness[g].gripreset[this.sc[g]] + (madness[g].level[this.sc[g]] + (double)statadjust[2] - 1.0) / 5.0;
                                            double whatneed = 24.2 + (madness[this.nplayers - 1].level[this.sc[this.nplayers - 1]] * 3 - 1) * 0.2;
                                            if (whatneed > 45.6) {
                                                whatneed = 45.6;
                                            }
                                            final double needgrip2 = this.round(whatneed - startgrip2, 1);
                                            int gripstats2 = (int)(needgrip2 * 5.0);
                                            if (needgrip2 < 0.0) {
                                                gripstats2 = 0;
                                            }
                                            if (this.totalsp[g] < gripstats2) {
                                                madness[g].aigripsp[this.sc[g]] = this.totalsp[g];
                                            }
                                            else {
                                                madness[g].aigripsp[this.sc[g]] = gripstats2;
                                                final int remaining2 = this.totalsp[g] - gripstats2;
                                                madness[g].aiendsp[this.sc[g]] = remaining2 / 2;
                                                madness[g].aistrsp[this.sc[g]] = remaining2 * 3 / 8;
                                                madness[g].aitssp[this.sc[g]] = remaining2 - madness[g].aiendsp[this.sc[g]] - madness[g].aistrsp[this.sc[g]];
                                            }
                                        }
                                        else if (checkpoints.stage == 12) {
                                            madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 9 / 64;
                                            madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 5 / 16;
                                            madness[g].aigripsp[this.sc[g]] = this.totalsp[g] / 16;
                                            madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - (madness[g].aitssp[this.sc[g]] + madness[g].aistrsp[this.sc[g]] + madness[g].aigripsp[this.sc[g]]);
                                        }
                                        else {
                                            madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 7 / 32;
                                            madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 2;
                                            madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - (madness[g].aitssp[this.sc[g]] + madness[g].aistrsp[this.sc[g]]);
                                        }
                                    }
                                    else {
                                        final int alreadyhave = madness[g].level[this.sc[g]] + statadjust[2] - 1;
                                        madness[g].aigripsp[this.sc[g]] = 120 - alreadyhave;
                                        final int remaining3 = this.totalsp[g] - madness[g].aigripsp[this.sc[g]];
                                        madness[g].aistrsp[this.sc[g]] = remaining3 / 2;
                                        madness[g].aiendsp[this.sc[g]] = remaining3 / 4;
                                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] - madness[g].aistrsp[this.sc[g]] - madness[g].aiendsp[this.sc[g]] - madness[g].aigripsp[this.sc[g]];
                                    }
                                }
                                else if (!this.bonusstage[2]) {
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 2;
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 2;
                                }
                                else {
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 5 / 8;
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 3 / 32;
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - (madness[g].aiendsp[this.sc[g]] + madness[g].aitssp[this.sc[g]]);
                                }
                            }
                            if (checkpoints.stage == 9 || (checkpoints.stage == 11 && !this.bonusstage[2])) {
                                final double startgrip2 = madness[g].gripreset[this.sc[g]] + (madness[g].level[this.sc[g]] + (double)statadjust[2] - 1.0) / 5.0;
                                double whatneed = 24.2 + (madness[this.nplayers - 1].level[this.sc[this.nplayers - 1]] * 3 - 1) * 0.2;
                                if (whatneed > 42.0) {
                                    whatneed = 42.0;
                                }
                                if (checkpoints.stage == 11) {
                                    whatneed = 24.2 + (madness[this.nplayers - 1].level[this.sc[this.nplayers - 1]] * 3 - 1) * 0.2;
                                    if (whatneed > 45.6) {
                                        whatneed = 45.6;
                                    }
                                }
                                final double needgrip2 = this.round(whatneed - startgrip2, 1);
                                int gripstats2 = (int)(needgrip2 * 5.0);
                                if (needgrip2 < 0.0) {
                                    gripstats2 = 0;
                                }
                                if (this.totalsp[g] < gripstats2) {
                                    madness[g].aigripsp[this.sc[g]] = this.totalsp[g];
                                }
                                else {
                                    madness[g].aigripsp[this.sc[g]] = gripstats2;
                                    final int remaining2 = this.totalsp[g] - gripstats2;
                                    if (checkpoints.stage == 9) {
                                        madness[g].aitssp[this.sc[g]] = remaining2 * 3 / 4;
                                        madness[g].aiendsp[this.sc[g]] = remaining2 - madness[g].aitssp[this.sc[g]];
                                    }
                                    if (checkpoints.stage == 11) {
                                        madness[g].aiendsp[this.sc[g]] = remaining2 * 3 / 4;
                                        madness[g].aitssp[this.sc[g]] = remaining2 - madness[g].aitssp[this.sc[g]];
                                    }
                                }
                            }
                            if (checkpoints.stage == 12 && !this.beastopponent[g]) {
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g];
                            }
                            if (checkpoints.stage == 13) {
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 8;
                                if (this.unlocked[1] == checkpoints.stage || this.hardstage) {
                                    madness[g].aitssp[this.sc[g]] = 356 - madness[g].nitroswits[this.sc[g]][2] - (madness[g].level[this.sc[g]] + statadjust[0] - 1);
                                }
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aitssp[this.sc[g]];
                                if (this.beastopponent[g]) {
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 2;
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 8;
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aistrsp[this.sc[g]] - madness[g].aitssp[this.sc[g]];
                                }
                            }
                            if (checkpoints.stage == 15 && !this.bonusstage[2]) {
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 8;
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aitssp[this.sc[g]];
                            }
                            if (checkpoints.stage == 16 && !this.beastopponent[g]) {
                                if (!madness[g].shadowcar || this.scalelevels) {
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 8;
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aitssp[this.sc[g]];
                                }
                                else {
                                    final int alreadyhave = madness[g].level[this.sc[g]] + statadjust[2] + 49;
                                    madness[g].aigripsp[this.sc[g]] = 120 - alreadyhave;
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 8;
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aitssp[this.sc[g]] - madness[g].aigripsp[this.sc[g]];
                                }
                            }
                            if (checkpoints.stage == 8 && !this.beastopponent[g]) {
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 4;
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 4;
                                madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 2;
                            }
                            if (this.beastopponent[g]) {
                                boolean afuckingracer = false;
                                final int totalpoints = this.spcalc(madness[0].level[this.sc[0]]) + this.extpoints[this.sc[0]];
                                final int instrength = totalpoints / 8;
                                if (madness[0].aistrsp[this.sc[0]] - madness[0].level[this.sc[0]] + 1 < instrength) {
                                    afuckingracer = true;
                                }
                                int extras = 65;
                                if (this.scalelevels) {
                                    extras = 0;
                                }
                                if (checkpoints.stage == 7) {
                                    if (!afuckingracer) {
                                        madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 7 / 32;
                                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 5 / 32;
                                        madness[g].aistusp[this.sc[g]] = extras - this.totalsp[g] / 32;
                                        madness[g].aiaccsp[this.sc[g]] = extras - this.totalsp[g] / 32;
                                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aistrsp[this.sc[g]] - madness[g].aitssp[this.sc[g]] - madness[g].aistusp[this.sc[g]] - madness[g].aiaccsp[this.sc[g]];
                                    }
                                    else {
                                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 3 / 16;
                                        madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 3 / 16;
                                        madness[g].aistusp[this.sc[g]] = extras;
                                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]] - madness[g].aistrsp[this.sc[g]] - extras;
                                    }
                                }
                                if (checkpoints.stage == 8) {
                                    if (afuckingracer) {
                                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 2;
                                    }
                                    else {
                                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 5 / 16;
                                    }
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 5 / 16;
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]] - madness[g].aistrsp[this.sc[g]];
                                }
                            }
                            if (this.sc[g] == 33 && this.bonusstage[1]) {
                                madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 4;
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 4;
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 2;
                            }
                            if (checkpoints.stage == 10) {
                                final float userstrength = madness[0].momentreset[this.sc[0]] + madness[0].aistrsp[this.sc[0]] * 0.025f;
                                if (userstrength < 4.0f) {
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 3 / 8;
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]];
                                }
                                else {
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 3 / 4;
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]];
                                }
                            }
                            if (checkpoints.stage == 14) {
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 3 / 4;
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 8;
                                madness[g].aistusp[this.sc[g]] = this.totalsp[g] - (madness[g].aiendsp[this.sc[g]] + madness[g].aitssp[this.sc[g]]);
                            }
                        }
                    }
                    if (this.sc[g] == 11 || this.sc[g] == 34) {
                        if (checkpoints.stage != 11 && !this.beastopponent[g] && checkpoints.stage != 12 && checkpoints.stage != 15 && checkpoints.stage != 16 && checkpoints.stage != 17 && checkpoints.stage != 18) {
                            if (checkpoints.stage == 9) {
                                final double startgrip2 = madness[g].gripreset[this.sc[g]] + (madness[g].level[this.sc[g]] + (double)statadjust[2] - 1.0) / 5.0;
                                double whatneed = 24.2 + (madness[this.nplayers - 1].level[this.sc[this.nplayers - 1]] * 3 - 1) * 0.2;
                                if (whatneed > 42.0) {
                                    whatneed = 42.0;
                                }
                                final double needgrip2 = this.round(whatneed - startgrip2, 1);
                                int gripstats2 = (int)(needgrip2 * 5.0);
                                if (needgrip2 < 0.0) {
                                    gripstats2 = 0;
                                }
                                if (this.totalsp[g] < gripstats2) {
                                    madness[g].aigripsp[this.sc[g]] = this.totalsp[g];
                                }
                                else {
                                    madness[g].aigripsp[this.sc[g]] = gripstats2;
                                    final int remaining2 = this.totalsp[g] - gripstats2;
                                    madness[g].aitssp[this.sc[g]] = remaining2 / 2;
                                    madness[g].aiendsp[this.sc[g]] = remaining2 - madness[g].aitssp[this.sc[g]];
                                }
                            }
                            else {
                                boolean nostrength = false;
                                if (checkpoints.stage == 13 && (this.unlocked[1] == checkpoints.stage || this.hardstage)) {
                                    final float userstrength2 = madness[0].momentreset[this.sc[0]] + madness[0].aistrsp[this.sc[0]] * 0.025f;
                                    if (userstrength2 > 5.0f) {
                                        nostrength = true;
                                    }
                                }
                                if (!nostrength) {
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 2;
                                }
                                if (checkpoints.stage < 18) {
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 6;
                                    if (checkpoints.stage == 13 && (this.unlocked[1] == checkpoints.stage || this.hardstage)) {
                                        madness[g].aitssp[this.sc[g]] = 335 - madness[g].nitroswits[this.sc[g]][2] - (madness[g].level[this.sc[g]] + statadjust[0] - 1);
                                    }
                                }
                                if (checkpoints.stage == 19) {
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 17 / 32;
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 4;
                                }
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - (madness[g].aistrsp[this.sc[g]] + madness[g].aitssp[this.sc[g]]);
                            }
                        }
                        else if (!this.bonusstage[1] && !this.bonusstage[2] && !this.bonusstage[3]) {
                            if (((checkpoints.stage != 12 && checkpoints.stage != 15 && checkpoints.stage != 16) || this.beastopponent[g]) && checkpoints.stage != 17 && checkpoints.stage != 18) {
                                if (checkpoints.stage == 11) {
                                    if (g <= 4) {
                                        if (!this.scalelevels) {
                                            madness[g].aitssp[this.sc[g]] = 80;
                                            madness[g].aistrsp[this.sc[g]] = 210;
                                            madness[g].aigripsp[this.sc[g]] = this.totalsp[g] - (madness[g].aitssp[this.sc[g]] + madness[g].aistrsp[this.sc[g]]);
                                        }
                                        else {
                                            final double spdportion = 80.0 / this.beastspcalc(35);
                                            final double strportion = 210.0 / this.beastspcalc(35);
                                            madness[g].aitssp[this.sc[g]] = (int)(this.totalsp[g] * spdportion);
                                            madness[g].aistrsp[this.sc[g]] = (int)(this.totalsp[g] * strportion);
                                            madness[g].aigripsp[this.sc[g]] = this.totalsp[g] - madness[g].aitssp[this.sc[g]] - madness[g].aistrsp[this.sc[g]];
                                        }
                                    }
                                    else {
                                        final double startgrip2 = madness[g].gripreset[this.sc[g]] + (madness[g].level[this.sc[g]] + (double)statadjust[2] - 1.0) / 5.0;
                                        double whatneed = 24.2 + (madness[this.nplayers - 1].level[this.sc[this.nplayers - 1]] * 3 - 1) * 0.2;
                                        if (whatneed > 45.6) {
                                            whatneed = 45.6;
                                        }
                                        final double needgrip2 = this.round(whatneed - startgrip2, 1);
                                        int gripstats2 = (int)(needgrip2 * 5.0);
                                        if (needgrip2 < 0.0) {
                                            gripstats2 = 0;
                                        }
                                        if (this.totalsp[g] < gripstats2) {
                                            madness[g].aigripsp[this.sc[g]] = this.totalsp[g];
                                        }
                                        else {
                                            madness[g].aigripsp[this.sc[g]] = gripstats2;
                                            final int remaining2 = this.totalsp[g] - gripstats2;
                                            if (!this.beastopponent[g]) {
                                                madness[g].aistrsp[this.sc[g]] = remaining2 / 2;
                                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aistrsp[this.sc[g]] - madness[g].aigripsp[this.sc[g]];
                                            }
                                            else {
                                                madness[g].aiendsp[this.sc[g]] = remaining2 * 15 / 32;
                                                madness[g].aistrsp[this.sc[g]] = remaining2 * 7 / 16;
                                                madness[g].aitssp[this.sc[g]] = remaining2 - madness[g].aiendsp[this.sc[g]] - madness[g].aistrsp[this.sc[g]];
                                            }
                                        }
                                    }
                                }
                                else {
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 9 / 64;
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 2;
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - (madness[g].aitssp[this.sc[g]] + madness[g].aistrsp[this.sc[g]]);
                                }
                            }
                            else {
                                if (!madness[g].shadowcar || checkpoints.stage < 16 || this.scalelevels) {
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 2;
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 7 / 16;
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] - (madness[g].aistrsp[this.sc[g]] + madness[g].aiendsp[this.sc[g]]);
                                }
                                else {
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 2;
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 3 / 8;
                                    final int alreadyhave = madness[g].level[this.sc[g]] + statadjust[2] + 49;
                                    madness[g].aigripsp[this.sc[g]] = 120 - alreadyhave;
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - (madness[g].aistrsp[this.sc[g]] + madness[g].aitssp[this.sc[g]]) - madness[g].aigripsp[this.sc[g]];
                                }
                                if (checkpoints.stage == 17) {
                                    if (!madness[g].shadowcar) {
                                        madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 2;
                                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 7 / 16;
                                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] - (madness[g].aistrsp[this.sc[g]] + madness[g].aiendsp[this.sc[g]]);
                                    }
                                    else {
                                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 8;
                                        madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 3 / 8;
                                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - (madness[g].aistrsp[this.sc[g]] + madness[g].aitssp[this.sc[g]]);
                                    }
                                }
                                if (checkpoints.stage == 18) {
                                    if (madness[g].shadowcar && !this.scalelevels) {
                                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 8;
                                        madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 5 / 8;
                                        final int alreadyhave = madness[g].level[this.sc[g]] + 49 + statadjust[2];
                                        madness[g].aigripsp[this.sc[g]] = 135 - alreadyhave;
                                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aitssp[this.sc[g]] - madness[g].aistrsp[this.sc[g]] - madness[g].aigripsp[this.sc[g]];
                                    }
                                    else {
                                        madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 2;
                                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 7 / 16;
                                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] - (madness[g].aistrsp[this.sc[g]] + madness[g].aiendsp[this.sc[g]]);
                                    }
                                }
                            }
                        }
                        else {
                            if (this.bonusstage[1]) {
                                if (g == 5) {
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 2;
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]];
                                }
                                else {
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 2;
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 13 / 32;
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]] - madness[g].aistrsp[this.sc[g]];
                                }
                            }
                            if (this.bonusstage[2]) {
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 3 / 32;
                                madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 2;
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - (madness[g].aitssp[this.sc[g]] + madness[g].aistrsp[this.sc[g]]);
                            }
                            if (this.bonusstage[3]) {
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 7 / 32;
                                madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - madness[g].aitssp[this.sc[g]];
                            }
                        }
                    }
                    if (this.sc[g] == 12 || this.sc[g] == 35) {
                        if (checkpoints.stage != 11 && !this.beastopponent[g] && checkpoints.stage != 13 && checkpoints.stage != 14 && checkpoints.stage != 15 && checkpoints.stage != 16 && checkpoints.stage != 18) {
                            madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 2;
                            madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 4;
                            madness[g].aigripsp[this.sc[g]] = this.totalsp[g] / 4;
                        }
                        else if (checkpoints.stage != 14 && checkpoints.stage != 13 && ((checkpoints.stage != 18 && checkpoints.stage != 16) || this.scalelevels)) {
                            if (this.beastopponent[g]) {
                                if (checkpoints.stage != 11 && !this.bonusstage[2]) {
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 4;
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 3 / 4;
                                }
                                else {
                                    if (checkpoints.stage == 11) {
                                        final double startgrip2 = madness[g].gripreset[this.sc[g]] + (madness[g].level[this.sc[g]] + (double)statadjust[2] - 1.0) / 5.0;
                                        double whatneed = 24.2 + (madness[this.nplayers - 1].level[this.sc[this.nplayers - 1]] * 3 - 1) * 0.2;
                                        if (whatneed > 45.6) {
                                            whatneed = 45.6;
                                        }
                                        final double needgrip2 = this.round(whatneed - startgrip2, 1);
                                        int gripstats2 = (int)(needgrip2 * 5.0);
                                        if (needgrip2 < 0.0) {
                                            gripstats2 = 0;
                                        }
                                        if (this.totalsp[g] < gripstats2) {
                                            madness[g].aigripsp[this.sc[g]] = this.totalsp[g];
                                        }
                                        else {
                                            final int extrabeast = this.beastspcalc(madness[g].level[this.sc[g]]) - this.spcalc(madness[g].level[this.sc[g]]);
                                            madness[g].aigripsp[this.sc[g]] = gripstats2;
                                            madness[g].aitssp[this.sc[g]] = this.spcalc(madness[g].level[this.sc[g]]) - gripstats2 + extrabeast / 8;
                                            boolean userracer = false;
                                            if (madness[0].aitssp[this.sc[0]] > madness[0].aistrsp[this.sc[0]]) {
                                                userracer = true;
                                            }
                                            if (userracer) {
                                                madness[g].aiendsp[this.sc[g]] = extrabeast / 2;
                                            }
                                            else {
                                                madness[g].aiendsp[this.sc[g]] = extrabeast * 7 / 8;
                                            }
                                            madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - madness[g].aigripsp[this.sc[g]] - madness[g].aitssp[this.sc[g]] - madness[g].aiendsp[this.sc[g]];
                                        }
                                    }
                                    if (this.bonusstage[2]) {
                                        madness[g].aitssp[this.sc[g]] = 158 - madness[g].level[this.sc[g]];
                                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aitssp[this.sc[g]];
                                    }
                                }
                            }
                            else if (checkpoints.stage != 13 && checkpoints.stage != 15) {
                                if (!this.bonusstage[1]) {
                                    if (checkpoints.stage == 11) {
                                        final double startgrip2 = madness[g].gripreset[this.sc[g]] + (madness[g].level[this.sc[g]] + (double)statadjust[2] - 1.0) / 5.0;
                                        double whatneed = 24.2 + (madness[this.nplayers - 1].level[this.sc[this.nplayers - 1]] * 3 - 1) * 0.2;
                                        if (whatneed > 45.6) {
                                            whatneed = 45.6;
                                        }
                                        final double needgrip2 = this.round(whatneed - startgrip2, 1);
                                        int gripstats2 = (int)(needgrip2 * 5.0);
                                        if (needgrip2 < 0.0) {
                                            gripstats2 = 0;
                                        }
                                        if (this.totalsp[g] < gripstats2) {
                                            madness[g].aigripsp[this.sc[g]] = this.totalsp[g];
                                        }
                                        else {
                                            madness[g].aigripsp[this.sc[g]] = gripstats2;
                                            final int remaining2 = this.totalsp[g] - gripstats2;
                                            madness[g].aitssp[this.sc[g]] = remaining2;
                                        }
                                    }
                                    else {
                                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 2;
                                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 2;
                                    }
                                }
                                else {
                                    madness[g].aitssp[this.sc[g]] = 88 - madness[g].level[this.sc[g]];
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aitssp[this.sc[g]];
                                }
                            }
                            else {
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 4;
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 3 / 4;
                            }
                        }
                        else {
                            if (checkpoints.stage == 13) {
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 8;
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aitssp[this.sc[g]];
                            }
                            if (checkpoints.stage == 14) {
                                int stupoints = 10;
                                if (!this.scalelevels) {
                                    stupoints = 0;
                                }
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] - stupoints;
                                madness[g].aistusp[this.sc[g]] = stupoints;
                            }
                            else {
                                if (checkpoints.stage == 18) {
                                    int speedsp = 149;
                                    int shadboost = 0;
                                    if (madness[g].shadowcar) {
                                        shadboost = 50;
                                        speedsp = 109;
                                    }
                                    madness[g].aitssp[this.sc[g]] = speedsp;
                                    final int alreadyhave2 = madness[g].level[this.sc[g]] - 1 + statadjust[2] + shadboost;
                                    madness[g].aigripsp[this.sc[g]] = 125 - alreadyhave2;
                                    madness[g].aistusp[this.sc[g]] = 90 - madness[g].aigripsp[this.sc[g]];
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - speedsp - 90;
                                }
                                if (checkpoints.stage == 16) {
                                    int shadboost2 = 0;
                                    if (madness[g].shadowcar) {
                                        shadboost2 = 50;
                                    }
                                    final int alreadyhave3 = madness[g].level[this.sc[g]] - 1 + statadjust[2] + shadboost2;
                                    madness[g].aigripsp[this.sc[g]] = 110 - alreadyhave3;
                                    final int remaining4 = this.totalsp[g] - madness[g].aigripsp[this.sc[g]];
                                    madness[g].aitssp[this.sc[g]] = remaining4 / 4;
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aigripsp[this.sc[g]] - madness[g].aitssp[this.sc[g]];
                                }
                            }
                        }
                    }
                    if (this.sc[g] == 13 || this.sc[g] == 36) {
                        if (!this.bonusstage[1] && !this.bonusstage[2] && !this.bonusstage[3]) {
                            if (checkpoints.stage != 16 && checkpoints.stage != 17 && checkpoints.stage != 18) {
                                if (checkpoints.stage != 13 && checkpoints.stage != 15 && checkpoints.stage != 21 && checkpoints.stage != 19 && checkpoints.stage != 22 && checkpoints.stage != 23) {
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 2;
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 2;
                                }
                                else {
                                    if (checkpoints.stage == 21) {
                                        if (this.beastopponent[g] && !this.scalelevels) {
                                            madness[g].aitssp[this.sc[g]] = 76 - (madness[g].level[this.sc[g]] + statadjust[0] - 66);
                                            madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 4;
                                            madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]] - madness[g].aitssp[this.sc[g]];
                                        }
                                        else {
                                            madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 3 / 16;
                                            madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 4;
                                            madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]] - madness[g].aitssp[this.sc[g]];
                                        }
                                    }
                                    if (checkpoints.stage == 22) {
                                        if (this.beastopponent[g] && !this.scalelevels) {
                                            madness[g].aitssp[this.sc[g]] = 76 - (madness[g].level[this.sc[g]] + statadjust[0] - 66);
                                            madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 4;
                                            madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]] - madness[g].aitssp[this.sc[g]];
                                        }
                                        else {
                                            madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 3;
                                            madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]];
                                        }
                                    }
                                    if (checkpoints.stage == 23) {
                                        if (madness[g].shadowcar) {
                                            madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 6;
                                            madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - madness[g].aitssp[this.sc[g]];
                                        }
                                        else {
                                            madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 3;
                                            madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]];
                                        }
                                    }
                                    if (checkpoints.stage == 19) {
                                        if (!madness[g].shadowcar) {
                                            madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 8;
                                            madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 5 / 16;
                                            madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]] - madness[g].aitssp[this.sc[g]];
                                        }
                                        else {
                                            madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 3 / 16;
                                            madness[g].aigripsp[this.sc[g]] = this.totalsp[g] * 3 / 32;
                                            madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 19 / 32;
                                            madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aigripsp[this.sc[g]] - madness[g].aistrsp[this.sc[g]] - madness[g].aitssp[this.sc[g]];
                                        }
                                    }
                                    if (checkpoints.stage == 13 || checkpoints.stage == 15) {
                                        if (!this.beastopponent[g]) {
                                            madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 2;
                                            madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 6;
                                            if ((this.unlocked[1] == checkpoints.stage || this.hardstage) && checkpoints.stage == 13) {
                                                madness[g].aitssp[this.sc[g]] = 320 - madness[g].nitroswits[this.sc[g]][2] - (madness[g].level[this.sc[g]] + statadjust[0] - 1);
                                            }
                                            madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aistrsp[this.sc[g]] - madness[g].aitssp[this.sc[g]];
                                        }
                                        else {
                                            madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 3 / 16;
                                            madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 4;
                                            madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]] - madness[g].aitssp[this.sc[g]];
                                        }
                                    }
                                }
                            }
                            else if (g >= 4 || checkpoints.stage != 17) {
                                if (!this.beastopponent[g]) {
                                    if (!madness[g].shadowcar || this.scalelevels) {
                                        madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 3 / 4;
                                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aistrsp[this.sc[g]];
                                    }
                                    else {
                                        if (checkpoints.stage == 18) {
                                            final int alreadyhave = madness[g].level[this.sc[g]] + statadjust[2] + 49;
                                            madness[g].aigripsp[this.sc[g]] = 131 - alreadyhave;
                                            madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 3 / 4;
                                            madness[g].aitssp[this.sc[g]] = this.totalsp[g] - madness[g].aistrsp[this.sc[g]] - madness[g].aigripsp[this.sc[g]];
                                        }
                                        if (checkpoints.stage == 16) {
                                            final int alreadyhave = madness[g].level[this.sc[g]] + statadjust[2] + 49;
                                            madness[g].aigripsp[this.sc[g]] = 116 - alreadyhave;
                                            madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 3 / 4;
                                            madness[g].aitssp[this.sc[g]] = this.totalsp[g] - madness[g].aistrsp[this.sc[g]] - madness[g].aigripsp[this.sc[g]];
                                        }
                                        if (checkpoints.stage == 17) {
                                            madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 3 / 4;
                                            madness[g].aitssp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]];
                                        }
                                    }
                                }
                                else if (checkpoints.stage != 18 || this.scalelevels) {
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 2;
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aistrsp[this.sc[g]];
                                }
                                else {
                                    final int alreadyhave = madness[g].level[this.sc[g]] - 1 + statadjust[2];
                                    madness[g].aigripsp[this.sc[g]] = 116 - alreadyhave;
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 2;
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aistrsp[this.sc[g]] - madness[g].aigripsp[this.sc[g]];
                                }
                            }
                            else if (!this.scalelevels) {
                                madness[g].aitssp[this.sc[g]] = 105;
                                madness[g].aistrsp[this.sc[g]] = 195;
                                madness[g].aiaccsp[this.sc[g]] = this.totalsp[g] - (madness[g].aitssp[this.sc[g]] + madness[g].aistrsp[this.sc[g]]);
                            }
                            else {
                                final double spdportion = 105.0 / this.beastspcalc(55);
                                final double strportion = 195.0 / this.beastspcalc(55);
                                madness[g].aitssp[this.sc[g]] = (int)(this.totalsp[g] * spdportion);
                                madness[g].aistrsp[this.sc[g]] = (int)(this.totalsp[g] * strportion);
                                madness[g].aiaccsp[this.sc[g]] = this.totalsp[g] - madness[g].aitssp[this.sc[g]] - madness[g].aistrsp[this.sc[g]];
                            }
                        }
                        else if (!this.bonusstage[2] && !this.bonusstage[3]) {
                            if (g == 1 || g == 2) {
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 2;
                                madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]];
                            }
                            else {
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 2;
                                madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 3 / 8;
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]] - madness[g].aistrsp[this.sc[g]];
                            }
                        }
                        else if (this.bonusstage[2]) {
                            madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 9 / 16;
                            madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 4;
                            madness[g].aitssp[this.sc[g]] = this.totalsp[g] - (madness[g].aistrsp[this.sc[g]] + madness[g].aiendsp[this.sc[g]]);
                        }
                        else {
                            madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 3 / 16;
                            madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - madness[g].aitssp[this.sc[g]];
                        }
                    }
                    if (this.sc[g] == 14 || this.sc[g] == 37) {
                        if (checkpoints.stage != 21 && checkpoints.stage != 22) {
                            if (!this.beastopponent[g] || checkpoints.stage == 16 || checkpoints.stage == 17 || checkpoints.stage == 18) {
                                if (checkpoints.stage < 15 && checkpoints.stage > 19) {
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 4;
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - (madness[g].aitssp[this.sc[g]] + madness[g].aiaccsp[this.sc[g]]);
                                }
                                else if (checkpoints.stage != 17 && checkpoints.stage != 19) {
                                    if (checkpoints.stage != 18 || this.scalelevels) {
                                        if (checkpoints.stage != 16 || this.scalelevels) {
                                            madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 4;
                                            madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 3 / 4;
                                        }
                                        else if (this.beastopponent[g] || madness[g].shadowcar) {
                                            madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 8;
                                            madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 3 / 8;
                                            int shadboost2 = 0;
                                            if (madness[g].shadowcar) {
                                                shadboost2 = 50;
                                            }
                                            final int alreadyhave3 = madness[g].level[this.sc[g]] - 1 + statadjust[2] + shadboost2;
                                            madness[g].aigripsp[this.sc[g]] = 120 - alreadyhave3;
                                            madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - (madness[g].aitssp[this.sc[g]] + madness[g].aistrsp[this.sc[g]] + madness[g].aigripsp[this.sc[g]]);
                                        }
                                        else {
                                            madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 4;
                                            final int alreadyhave = madness[g].level[this.sc[g]] - 1 + statadjust[2];
                                            madness[g].aigripsp[this.sc[g]] = 120 - alreadyhave;
                                            madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aigripsp[this.sc[g]] - madness[g].aitssp[this.sc[g]];
                                        }
                                    }
                                    else if (!this.beastopponent[g] && !madness[g].shadowcar) {
                                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 2;
                                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aitssp[this.sc[g]];
                                    }
                                    else {
                                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 8;
                                        madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 3 / 8;
                                        int shadboost2 = 0;
                                        if (madness[g].shadowcar) {
                                            shadboost2 = 50;
                                        }
                                        final int alreadyhave3 = madness[g].level[this.sc[g]] - 1 + shadboost2 + statadjust[2];
                                        madness[g].aigripsp[this.sc[g]] = 135 - alreadyhave3;
                                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - (madness[g].aitssp[this.sc[g]] + madness[g].aistrsp[this.sc[g]] + madness[g].aigripsp[this.sc[g]]);
                                    }
                                }
                                else if (madness[g].shadowcar) {
                                    if (checkpoints.stage == 17) {
                                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 4;
                                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aitssp[this.sc[g]];
                                    }
                                    if (checkpoints.stage == 19) {
                                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 8;
                                        madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - madness[g].aitssp[this.sc[g]];
                                    }
                                }
                                else if (!this.beastopponent[g]) {
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 2;
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 3 / 16;
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] - (madness[g].aistrsp[this.sc[g]] + madness[g].aiendsp[this.sc[g]]);
                                }
                                else {
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 3 / 16;
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 7 / 16;
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - (madness[g].aitssp[this.sc[g]] + madness[g].aistrsp[this.sc[g]]);
                                }
                            }
                            else if (this.sc[g] == 37) {
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 3 / 32;
                                madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 4;
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - (madness[g].aitssp[this.sc[g]] + madness[g].aistrsp[this.sc[g]]);
                            }
                            else if (checkpoints.stage != 19) {
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 8;
                                madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 3 / 8;
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - (madness[g].aitssp[this.sc[g]] + madness[g].aistrsp[this.sc[g]]);
                            }
                            else {
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 3 / 4;
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 4;
                            }
                        }
                        else {
                            if (checkpoints.stage == 21 && !this.scalelevels) {
                                madness[g].aitssp[this.sc[g]] = 66 - (madness[g].level[this.sc[g]] + statadjust[0] - 66);
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aitssp[this.sc[g]];
                            }
                            if (checkpoints.stage == 22 || (checkpoints.stage == 21 && this.scalelevels)) {
                                if (!this.beastopponent[g]) {
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 2;
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 5 / 16;
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] - (madness[g].aistrsp[this.sc[g]] + madness[g].aiendsp[this.sc[g]]);
                                }
                                else {
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 3 / 16;
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 9 / 16;
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - (madness[g].aitssp[this.sc[g]] + madness[g].aistrsp[this.sc[g]]);
                                }
                            }
                        }
                    }
                    if (this.sc[g] == 15 || this.sc[g] == 38) {
                        if (!this.bonstage) {
                            if (checkpoints.stage < 17 || this.scalelevels) {
                                madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 2;
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 4;
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 4;
                            }
                            else if (madness[g].shadowcar && checkpoints.stage == 17) {
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 4;
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 3 / 4;
                            }
                            else if (!this.beastopponent[g]) {
                                if (checkpoints.stage == 21) {
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 19 / 32;
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 5 / 32;
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aistrsp[this.sc[g]] - madness[g].aitssp[this.sc[g]];
                                }
                                boolean toostrong = false;
                                final float userstrength2 = madness[0].momentreset[this.sc[0]] + madness[0].aistrsp[this.sc[0]] * 0.025f;
                                if (userstrength2 > 13.75f && (this.unlocked[1] == checkpoints.stage || this.hardstage) && !madness[g].shadowcar && (this.sc[0] == 18 || this.sc[0] == 22)) {
                                    toostrong = true;
                                }
                                if (checkpoints.stage == 22) {
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 23 / 32;
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 8;
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aitssp[this.sc[g]] - madness[g].aistrsp[this.sc[g]];
                                }
                                if (checkpoints.stage == 23) {
                                    int endpoints = this.totalsp[g] * 5 / 24;
                                    if (madness[g].shadowcar) {
                                        endpoints = 0;
                                    }
                                    madness[g].aiendsp[this.sc[g]] = endpoints;
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 4;
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]] - madness[g].aitssp[this.sc[g]];
                                }
                                if (checkpoints.stage == 24) {
                                    if (toostrong) {
                                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 3 / 16;
                                        madness[g].aigripsp[this.sc[g]] = 100;
                                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aitssp[this.sc[g]] - 100;
                                    }
                                    else if (!madness[g].shadowcar) {
                                        madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 11 / 16;
                                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 3 / 16;
                                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aitssp[this.sc[g]] - madness[g].aistrsp[this.sc[g]];
                                    }
                                    else {
                                        final int alreadyhave2 = madness[g].level[this.sc[g]] + 49 + statadjust[2];
                                        madness[g].aigripsp[this.sc[g]] = 203 - alreadyhave2;
                                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 8;
                                        madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - madness[g].aigripsp[this.sc[g]] - madness[g].aitssp[this.sc[g]];
                                    }
                                }
                                if (checkpoints.stage == 17 || checkpoints.stage == 19 || (checkpoints.stage == 18 && !madness[g].shadowcar)) {
                                    int gripboost = 0;
                                    if (checkpoints.stage == 19 && madness[g].shadowcar) {
                                        gripboost = this.totalsp[g] / 32;
                                    }
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 4 - gripboost * 2;
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 2 + gripboost * 3;
                                    madness[g].aigripsp[this.sc[g]] = gripboost * 3;
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - (madness[g].aitssp[this.sc[g]] + madness[g].aistrsp[this.sc[g]] + madness[g].aigripsp[this.sc[g]]);
                                }
                                if (checkpoints.stage == 18 && madness[g].shadowcar) {
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 4;
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 2;
                                    final int alreadyhave2 = madness[g].level[this.sc[g]] + 49 + statadjust[2];
                                    madness[g].aigripsp[this.sc[g]] = 131 - alreadyhave2;
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - (madness[g].aitssp[this.sc[g]] + madness[g].aistrsp[this.sc[g]] + madness[g].aigripsp[this.sc[g]]);
                                }
                            }
                            else {
                                if (checkpoints.stage == 22) {
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 3 / 16;
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 9 / 16;
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - (madness[g].aitssp[this.sc[g]] + madness[g].aistrsp[this.sc[g]]);
                                }
                                if (checkpoints.stage == 19) {
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 3 / 16;
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 5 / 8;
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - (madness[g].aitssp[this.sc[g]] + madness[g].aistrsp[this.sc[g]]);
                                }
                                if (checkpoints.stage == 17 || checkpoints.stage == 18 || checkpoints.stage == 21) {
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 8;
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 7 / 16;
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - (madness[g].aitssp[this.sc[g]] + madness[g].aistrsp[this.sc[g]]);
                                }
                            }
                        }
                        else if (!this.bonusstage[3]) {
                            madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 3 / 16;
                            madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 2;
                            madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - (madness[g].aistrsp[this.sc[g]] + madness[g].aitssp[this.sc[g]]);
                        }
                        else {
                            madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 4;
                            madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - madness[g].aitssp[this.sc[g]];
                        }
                    }
                    if (this.sc[g] == 16) {
                        if (!this.beastopponent[g]) {
                            if (checkpoints.stage < 21 || this.scalelevels) {
                                int gripboost2 = 0;
                                if (checkpoints.stage == 19 && madness[g].shadowcar && !this.scalelevels) {
                                    gripboost2 = this.totalsp[g] / 32;
                                }
                                madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 17 / 32 + gripboost2 * 2;
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 4 - gripboost2 * 2;
                                madness[g].aigripsp[this.sc[g]] = gripboost2 * 3;
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - (madness[g].aitssp[this.sc[g]] + madness[g].aistrsp[this.sc[g]] + madness[g].aigripsp[this.sc[g]]);
                            }
                            else {
                                if (checkpoints.stage == 21) {
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 19 / 32;
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 7 / 32;
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aitssp[this.sc[g]] - madness[g].aistrsp[this.sc[g]];
                                }
                                boolean toostrong = false;
                                final float userstrength2 = madness[0].momentreset[this.sc[0]] + madness[0].aistrsp[this.sc[0]] * 0.025f;
                                if (userstrength2 > 13.75f && (this.unlocked[1] == checkpoints.stage || this.hardstage) && !madness[g].shadowcar && (this.sc[0] == 18 || this.sc[0] == 22)) {
                                    toostrong = true;
                                }
                                if (checkpoints.stage == 22 || checkpoints.stage == 23) {
                                    int endpoints = this.totalsp[g] / 8;
                                    if (madness[g].shadowcar) {
                                        endpoints = 0;
                                    }
                                    madness[g].aiendsp[this.sc[g]] = endpoints;
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 4;
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - madness[g].aitssp[this.sc[g]] - madness[g].aiendsp[this.sc[g]];
                                }
                                if (checkpoints.stage == 24) {
                                    if (toostrong) {
                                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 3 / 16;
                                        madness[g].aigripsp[this.sc[g]] = 100;
                                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aitssp[this.sc[g]] - 100;
                                    }
                                    else {
                                        int endpoints = this.totalsp[g] / 8;
                                        int spdpoints = 0;
                                        int grippoints = 0;
                                        if (madness[g].shadowcar) {
                                            endpoints = 0;
                                            spdpoints = 40;
                                            final int alreadyhave4 = madness[g].level[this.sc[g]] + 49 + statadjust[2];
                                            grippoints = 207 - alreadyhave4;
                                        }
                                        madness[g].aigripsp[this.sc[g]] = grippoints;
                                        madness[g].aiendsp[this.sc[g]] = endpoints;
                                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 8 - spdpoints;
                                        madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - madness[g].aitssp[this.sc[g]] - endpoints - grippoints;
                                    }
                                }
                            }
                        }
                        else {
                            if (checkpoints.stage == 21 || checkpoints.stage == 22) {
                                madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 23 / 32;
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 3 / 16;
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] - madness[g].aistrsp[this.sc[g]] - madness[g].aiendsp[this.sc[g]];
                            }
                            if (checkpoints.stage == 19) {
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 3 / 16;
                                madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 5 / 8;
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - (madness[g].aitssp[this.sc[g]] + madness[g].aistrsp[this.sc[g]]);
                            }
                            if (checkpoints.stage < 19) {
                                madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 2;
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 4;
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 4;
                            }
                        }
                    }
                    if (this.sc[g] == 17) {
                        if (checkpoints.stage < 21 || this.scalelevels) {
                            madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 3 / 4;
                            madness[g].aitssp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]];
                        }
                        else {
                            if (checkpoints.stage == 24) {
                                int less = 0;
                                int alreadyhave3 = 0;
                                if (madness[g].shadowcar) {
                                    less = 40;
                                    alreadyhave3 = madness[g].level[this.sc[g]] - 1 + 50 + statadjust[2];
                                }
                                else {
                                    alreadyhave3 = 101;
                                }
                                madness[g].aitssp[this.sc[g]] = 110 - (madness[g].level[this.sc[g]] + statadjust[0] - 81) - less;
                                madness[g].aigripsp[this.sc[g]] = 201 - alreadyhave3;
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aitssp[this.sc[g]] - madness[g].aigripsp[this.sc[g]];
                            }
                            if (checkpoints.stage == 21) {
                                madness[g].aitssp[this.sc[g]] = 145 - (madness[g].level[this.sc[g]] + statadjust[0] - 66);
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aitssp[this.sc[g]];
                            }
                            if (checkpoints.stage == 23) {
                                int comp = 0;
                                int accpoints = 0;
                                if (madness[g].shadowcar) {
                                    comp = 24;
                                    accpoints = 50;
                                }
                                madness[g].aitssp[this.sc[g]] = 190 - (madness[g].level[this.sc[g]] + statadjust[0] - 75) - comp;
                                madness[g].aiaccsp[this.sc[g]] = accpoints;
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aitssp[this.sc[g]] + comp - accpoints;
                            }
                            if (checkpoints.stage == 22) {
                                madness[g].aitssp[this.sc[g]] = 78;
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] * 3 / 16;
                                madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]] - 78;
                            }
                        }
                    }
                    if (this.sc[g] == 18) {
                        if (checkpoints.stage < 24 || this.scalelevels) {
                            if (checkpoints.stage != 23 || this.scalelevels) {
                                if (!this.beastopponent[g]) {
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 11 / 16;
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 8;
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aitssp[this.sc[g]] - madness[g].aistrsp[this.sc[g]];
                                }
                                else {
                                    madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 11 / 16;
                                    madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 8;
                                    madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aitssp[this.sc[g]] - madness[g].aistrsp[this.sc[g]];
                                }
                            }
                            else if (g == 1) {
                                madness[g].aitssp[this.sc[g]] = 191;
                                madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - 191;
                            }
                        }
                        else if (checkpoints.stage == 24) {
                            if (!madness[g].shadowcar) {
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 3 / 16;
                                madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 11 / 16;
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aitssp[this.sc[g]] - madness[g].aistrsp[this.sc[g]];
                            }
                            else {
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 5 / 32;
                                final int alreadyhave = madness[g].level[this.sc[g]] - 1 + statadjust[2] + 50;
                                madness[g].aigripsp[this.sc[g]] = 209 - alreadyhave;
                                madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - madness[g].aitssp[this.sc[g]] - madness[g].aigripsp[this.sc[g]];
                            }
                        }
                    }
                    if (this.sc[g] == 19) {
                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 4;
                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 8;
                        madness[g].aistrsp[this.sc[g]] = this.totalsp[g] - madness[g].aiendsp[this.sc[g]] - madness[g].aitssp[this.sc[g]];
                    }
                    if (this.sc[g] == 20) {
                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 9 / 32;
                        madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 5 / 8;
                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aitssp[this.sc[g]] - madness[g].aistrsp[this.sc[g]];
                    }
                    if (this.sc[g] == 21) {
                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] / 2;
                        madness[g].aigripsp[this.sc[g]] = this.totalsp[g] / 4;
                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] / 4;
                    }
                    if (this.sc[g] == 22) {
                        madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 3 / 16;
                        madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 11 / 16;
                        madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - madness[g].aitssp[this.sc[g]] - madness[g].aistrsp[this.sc[g]];
                    }
                    if (checkpoints.stage == 13) {
                        boolean afuckingracer = false;
                        final int totalpoints = this.spcalc(madness[0].level[this.sc[0]]) + this.extpoints[this.sc[0]];
                        final int instrength = totalpoints / 4;
                        final float userstrength3 = madness[0].momentreset[this.sc[0]] + madness[0].aistrsp[this.sc[0]] * 0.025f;
                        final float strengthneed = 5.0f + (madness[this.nplayers - 1].level[this.sc[this.nplayers - 1]] - 42) * 0.075f;
                        if (madness[0].aistrsp[this.sc[0]] - madness[0].level[this.sc[0]] + 1 < instrength || userstrength3 < strengthneed) {
                            afuckingracer = true;
                        }
                        if (g == 2 || g == 3 || g == 5 || g == 6 || g == 8 || g == 9) {
                            if (!afuckingracer) {
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 9 / 64;
                                madness[g].aistrsp[this.sc[g]] = this.totalsp[g] / 2;
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - (madness[g].aitssp[this.sc[g]] + madness[g].aistrsp[this.sc[g]]);
                            }
                            else {
                                madness[g].aitssp[this.sc[g]] = this.totalsp[g] * 7 / 32;
                                madness[g].aistrsp[this.sc[g]] = this.totalsp[g] * 7 / 16;
                                madness[g].aigripsp[this.sc[g]] = this.totalsp[g] / 8;
                                madness[g].aiendsp[this.sc[g]] = this.totalsp[g] - (madness[g].aitssp[this.sc[g]] + madness[g].aistrsp[this.sc[g]]);
                            }
                        }
                    }
                }
                int minlvl = madness[g].level[this.sc[g]];
                if (minlvl < 6) {
                    minlvl = 6;
                }
                if (madness[g].shadowcar) {
                    final int[] aiendsp = madness[g].aiendsp;
                    final int n = this.sc[g];
                    aiendsp[n] += minlvl * 4;
                    final int[] aiaccsp = madness[g].aiaccsp;
                    final int n2 = this.sc[g];
                    aiaccsp[n2] += 50;
                    final int[] aigripsp = madness[g].aigripsp;
                    final int n3 = this.sc[g];
                    aigripsp[n3] += 50;
                    final int[] aitssp = madness[g].aitssp;
                    final int n4 = this.sc[g];
                    aitssp[n4] += 40;
                }
                final int[] aitssp2 = madness[g].aitssp;
                final int n5 = this.sc[g];
                aitssp2[n5] += madness[g].level[this.sc[g]] - 1 + statadjust[0];
                final int[] aiaccsp2 = madness[g].aiaccsp;
                final int n6 = this.sc[g];
                aiaccsp2[n6] += madness[g].level[this.sc[g]] - 1 + statadjust[1];
                final int[] aigripsp2 = madness[g].aigripsp;
                final int n7 = this.sc[g];
                aigripsp2[n7] += madness[g].level[this.sc[g]] - 1 + statadjust[2];
                final int[] aistusp = madness[g].aistusp;
                final int n8 = this.sc[g];
                aistusp[n8] += madness[g].level[this.sc[g]] - 1 + statadjust[3];
                final int[] aistrsp = madness[g].aistrsp;
                final int n9 = this.sc[g];
                aistrsp[n9] += madness[g].level[this.sc[g]] - 1 + statadjust[4];
                final int[] aiendsp2 = madness[g].aiendsp;
                final int n10 = this.sc[g];
                aiendsp2[n10] += madness[g].level[this.sc[g]] - 1 + statadjust[5];
            }
            final int[] array = new int[this.nplayers - 1];
            for (int a3 = 1; a3 < this.nplayers; ++a3) {
                array[a3 - 1] = madness[a3].level[this.sc[a3]];
            }
            this.totallevel = this.sumAll(array);
            this.averagelevel = this.totallevel / (this.nplayers - 1);
            this.softlevelcap = madness[this.nplayers - 1].level[this.sc[this.nplayers - 1]] + 5;
            if (this.bonstage) {
                int bsboost = 5;
                if (this.bonusstage[0]) {
                    bsboost = 9;
                }
                this.softlevelcap = Math.min(madness[this.nplayers - 1].level[this.sc[this.nplayers - 1]] + bsboost, this.maxlevel[this.unlocked[1] - 2] + 5);
            }
            this.setlevels = true;
        }
    }
    
    public void finish(final CheckPoints checkpoints, final ContO[] aconto, final Control control, final Madness madness) {
        this.rd.drawImage(this.fleximg, 0, 0, null);
        int mxstage = this.unlocked[0];
        if (this.careermode) {
            mxstage = this.unlocked[1];
        }
        if (this.winner) {
            if (!this.bonstage) {
                if (checkpoints.stage == mxstage) {
                    if (checkpoints.stage != 31) {
                        this.rd.drawImage(this.congrd, 300, 70, null);
                        this.drawcs(120, "Stage " + checkpoints.stage + " Completed!", 170, 170, 170, 3);
                    }
                    else {
                        this.rd.drawImage(this.congrd, 295 + (int)(this.m.random() * 10.0f), 70, null);
                    }
                    byte byte0 = 0;
                    int i = 0;
                    this.pin = 60;
                    if (checkpoints.stage == 2) {
                        byte0 = 8;
                        i = 265;
                        this.pin = 0;
                    }
                    if (checkpoints.stage == 4) {
                        byte0 = 9;
                        i = 240;
                        this.pin = 0;
                    }
                    if (checkpoints.stage == 6) {
                        byte0 = 10;
                        i = 290;
                        this.pin = 0;
                    }
                    if (checkpoints.stage == 8) {
                        byte0 = 11;
                        i = 226;
                        this.pin = 0;
                    }
                    if (checkpoints.stage == 10) {
                        byte0 = 12;
                        i = 200;
                        this.pin = 0;
                    }
                    if (checkpoints.stage == 12) {
                        byte0 = 13;
                        i = 200;
                        this.pin = 0;
                    }
                    if (checkpoints.stage == 14) {
                        byte0 = 14;
                        i = 270;
                        this.pin = 0;
                    }
                    if (checkpoints.stage == 16) {
                        byte0 = 15;
                        i = 290;
                        this.pin = 0;
                    }
                    if (checkpoints.stage == 18) {
                        byte0 = 16;
                        i = 200;
                        this.pin = 0;
                    }
                    if (checkpoints.stage == 20) {
                        byte0 = 17;
                        i = 200;
                        this.pin = 0;
                    }
                    if (checkpoints.stage == 23) {
                        byte0 = 18;
                        i = 200;
                        this.pin = 0;
                    }
                    if (checkpoints.stage == 26) {
                        byte0 = 19;
                        i = 200;
                        this.pin = 0;
                    }
                    if (checkpoints.stage != 31) {
                        this.rd.setFont(new Font("SansSerif", 1, 13));
                        this.ftm = this.rd.getFontMetrics();
                        if (this.aflk) {
                            this.drawcs(160 + this.pin, "Stage " + (checkpoints.stage + 1) + " is now unlocked!", 176, 196, 0, 3);
                        }
                        else {
                            this.drawcs(160 + this.pin, "Stage " + (checkpoints.stage + 1) + " is now unlocked!", 247, 255, 165, 3);
                        }
                        if (byte0 != 0) {
                            if (this.aflk) {
                                this.drawcs(180, "And:", 176, 196, 0, 3);
                            }
                            else {
                                this.drawcs(180, "And:", 247, 255, 165, 3);
                            }
                            this.rd.setColor(new Color(236, 226, 202));
                            final float f = (float)Math.random();
                            if (f < 0.7) {
                                this.rd.drawRect(260, 190, 349, 126);
                            }
                            else {
                                this.rd.fillRect(260, 190, 350, 127);
                            }
                            this.rd.setColor(new Color(255, 209, 89));
                            this.rd.fillRect(261, 191, 348, 4);
                            this.rd.fillRect(261, 191, 4, 125);
                            this.rd.fillRect(261, 312, 348, 4);
                            this.rd.fillRect(605, 191, 4, 125);
                            aconto[byte0].y = i;
                            this.m.crs = true;
                            this.m.x = -435;
                            this.m.y = 0;
                            this.m.z = -50;
                            this.m.xz = 0;
                            this.m.zy = 0;
                            this.m.ground = 2470;
                            aconto[byte0].z = 1000;
                            aconto[byte0].x = 0;
                            final ContO contO = aconto[byte0];
                            contO.xz += 5;
                            aconto[byte0].zy = 0;
                            final ContO contO2 = aconto[byte0];
                            contO2.wzy -= 10;
                            aconto[byte0].d(this.rd);
                            if (f < 0.1) {
                                this.rd.setColor(new Color(236, 226, 202));
                                int j = 0;
                                do {
                                    this.rd.drawLine(265, 195 + 4 * j, 504, 155 + 4 * j);
                                } while (++j < 30);
                            }
                            String s = "";
                            if (byte0 == 13) {
                                s = " ";
                            }
                            if (this.sc[0] != byte0) {
                                if (this.aflk) {
                                    this.drawcs(340, this.names[byte0] + s + " has been unlocked!", 176, 196, 0, 3);
                                }
                                else {
                                    this.drawcs(340, this.names[byte0] + s + " has been unlocked!", 247, 255, 165, 3);
                                }
                                this.pin = 180;
                            }
                        }
                        this.rd.setFont(new Font("SansSerif", 1, 11));
                        this.ftm = this.rd.getFontMetrics();
                        if (this.pin == 60) {
                            this.pin = 30;
                        }
                        else {
                            this.pin = 0;
                        }
                    }
                    else {
                        this.rd.setFont(new Font("SansSerif", 1, 13));
                        this.ftm = this.rd.getFontMetrics();
                        if (this.aflk) {
                            this.drawcs(130, "Woohoooo you finished the game!!!", 144, 167, 255, 3);
                        }
                        else {
                            this.drawcs(130, "Woohoooo you finished the game!!!", 228, 240, 255, 3);
                        }
                        if (this.aflk) {
                            this.drawcs(180, "Your Awesome!", 144, 167, 255, 3);
                        }
                        else {
                            this.drawcs(182, "Your Awesome!", 228, 240, 255, 3);
                        }
                        if (this.aflk) {
                            this.drawcs(230, "You're truly a RADICAL GAMER!", 144, 167, 255, 3);
                        }
                        else {
                            this.drawcs(230, "You're truly a RADICAL GAMER!", 255, 100, 100, 3);
                        }
                        this.rd.setColor(new Color(0, 0, 0));
                        this.rd.fillRect(0, 205, 670, 62);
                        this.rd.drawImage(this.radicalplay, this.radpx + (int)(8.0 * Math.random() - 4.0), 245, null);
                        if (this.radpx != 147) {
                            this.radpx += 40;
                            if (this.radpx > 870) {
                                this.radpx = -253;
                            }
                        }
                        if (this.flipo == 40) {
                            this.radpx = 148;
                        }
                        ++this.flipo;
                        if (this.flipo == 70) {
                            this.flipo = 0;
                        }
                        if (this.radpx == 147) {
                            this.rd.setFont(new Font("SansSerif", 1, 11));
                            this.ftm = this.rd.getFontMetrics();
                            if (this.aflk) {
                                this.drawcs(299, "A Game by Radicalplay.com", 144, 167, 255, 3);
                            }
                            else {
                                this.drawcs(299, "A Game by Radicalplay.com", 228, 240, 255, 3);
                            }
                        }
                        if (this.aflk) {
                            this.drawcs(340, "Now get up and dance!", 144, 167, 255, 3);
                        }
                        else {
                            this.drawcs(340, "Now get up and dance!", 228, 240, 255, 3);
                        }
                        this.pin = 0;
                    }
                    if (this.aflk) {
                        this.aflk = false;
                    }
                    else {
                        this.aflk = true;
                    }
                }
                else {
                    this.pin = 30;
                    this.rd.drawImage(this.congrd, 300, 127, null);
                    this.drawcs(177, "Stage " + checkpoints.stage + " Completed!", 170, 170, 170, 3);
                    this.drawcs(194, new StringBuilder().append(checkpoints.name).toString(), 128, 128, 128, 3);
                }
            }
            else {
                this.rd.setFont(this.adventure.deriveFont(1, 18.0f));
                this.ftm = this.rd.getFontMetrics();
                for (int a = 0; a < 6; ++a) {
                    if (this.bonusstage[a] && ((this.boncomp[a] == 1 && a != 1) || ((this.boncomp[a] == 3 || (this.boncomp[a] == 1 && this.racingwin) || (this.boncomp[a] == 2 && this.wastingwin)) && a == 1))) {
                        this.drawcs(225, "Congratulations on completing the bonus stage!", 205, 205, 205, 3);
                    }
                }
                if (this.bonusstage[0] && this.boncomp[0] == 0) {
                    this.drawcs(35, "Congratulations on completing the bonus stage!", 205, 205, 205, 3);
                    this.drawcs(70, "Your reward is:", 205, 205, 205, 3);
                    this.rd.setColor(new Color(236, 226, 202));
                    final float f2 = (float)Math.random();
                    if (f2 < 0.7) {
                        this.rd.drawRect(260, 130, 349, 126);
                    }
                    else {
                        this.rd.fillRect(260, 130, 350, 127);
                    }
                    this.rd.setColor(new Color(255, 209, 89));
                    this.rd.fillRect(261, 131, 348, 4);
                    this.rd.fillRect(261, 131, 4, 125);
                    this.rd.fillRect(261, 252, 348, 4);
                    this.rd.fillRect(605, 131, 4, 125);
                    this.m.crs = true;
                    this.m.x = -435;
                    this.m.y = 0;
                    this.m.z = -50;
                    this.m.xz = 0;
                    this.m.zy = 0;
                    this.m.ground = 2470;
                    for (int a2 = 32; a2 < 34; ++a2) {
                        aconto[a2].y = 110;
                        aconto[a2].z = 1000;
                        if (a2 == 32) {
                            aconto[a2].x = -200;
                        }
                        else {
                            aconto[a2].x = 200;
                        }
                        final ContO contO3 = aconto[a2];
                        contO3.xz += 5;
                        aconto[a2].zy = 0;
                        final ContO contO4 = aconto[a2];
                        contO4.wzy -= 10;
                        aconto[a2].d(this.rd);
                    }
                    this.drawcs(315, "While these cars are quite hard to train, they are", 205, 205, 205, 3);
                    this.drawcs(340, "very powerful and worth using!", 205, 205, 205, 3);
                }
                if (this.bonusstage[1] && (this.boncomp[1] == 0 || (this.boncomp[1] == 1 && this.wastingwin) || (this.boncomp[1] == 2 && this.racingwin))) {
                    this.drawcs(35, "Congratulations on completing the bonus stage!", 205, 205, 205, 3);
                    this.drawcs(70, "Your reward is:", 205, 205, 205, 3);
                    this.m.crs = true;
                    this.m.x = -435;
                    this.m.y = 0;
                    this.m.z = -50;
                    this.m.xz = 0;
                    this.m.zy = 0;
                    this.m.ground = 2470;
                    if ((this.boncomp[1] == 1 && this.wastingwin) || (this.boncomp[1] == 2 && this.racingwin)) {
                        this.rd.setColor(new Color(236, 226, 202));
                        final float f2 = (float)Math.random();
                        if (f2 < 0.7) {
                            this.rd.drawRect(260, 130, 349, 126);
                        }
                        else {
                            this.rd.fillRect(260, 130, 350, 127);
                        }
                        this.rd.setColor(new Color(255, 209, 89));
                        this.rd.fillRect(261, 131, 348, 4);
                        this.rd.fillRect(261, 131, 4, 125);
                        this.rd.fillRect(261, 252, 348, 4);
                        this.rd.fillRect(609, 131, 4, 125);
                    }
                    else {
                        this.rd.setColor(new Color(236, 226, 202));
                        final float f2 = (float)Math.random();
                        if (f2 < 0.7) {
                            this.rd.drawRect(130, 130, 609, 126);
                        }
                        else {
                            this.rd.fillRect(130, 130, 610, 127);
                        }
                        this.rd.setColor(new Color(255, 209, 89));
                        this.rd.fillRect(131, 131, 608, 4);
                        this.rd.fillRect(131, 131, 4, 125);
                        this.rd.fillRect(131, 252, 608, 4);
                        this.rd.fillRect(735, 131, 4, 125);
                    }
                    if (this.boncomp[1] == 0) {
                        for (int a = 34; a < 37; ++a) {
                            if ((this.racingwin && a < 36) || (this.wastingwin && (a == 34 || a == 36))) {
                                aconto[a].y = 110;
                                aconto[a].z = 1000;
                                if (a == 34) {
                                    aconto[a].x = -325;
                                }
                                else {
                                    aconto[a].x = 325;
                                }
                                final ContO contO5 = aconto[a];
                                contO5.xz += 5;
                                aconto[a].zy = 0;
                                final ContO contO6 = aconto[a];
                                contO6.wzy -= 10;
                                aconto[a].d(this.rd);
                            }
                        }
                        if (this.racingwin) {
                            this.drawcs(315, "If you beat the stage again by wasting, you can unlock " + this.names[36] + "!", 205, 205, 205, 3);
                        }
                        if (this.wastingwin) {
                            this.drawcs(315, "If you beat the stage again by racing, you can unlock " + this.names[35] + "!", 205, 205, 205, 3);
                        }
                    }
                    if ((this.boncomp[1] == 1 && this.wastingwin) || (this.boncomp[1] == 2 && this.racingwin)) {
                        for (int a = 34; a < 37; ++a) {
                            if ((this.racingwin && this.boncomp[1] == 2 && a == 35) || (this.wastingwin && this.boncomp[1] == 1 && a == 36)) {
                                aconto[a].y = 110;
                                aconto[a].z = 1000;
                                aconto[a].x = 0;
                                final ContO contO7 = aconto[a];
                                contO7.xz += 5;
                                aconto[a].zy = 0;
                                final ContO contO8 = aconto[a];
                                contO8.wzy -= 10;
                                aconto[a].d(this.rd);
                            }
                        }
                        this.drawcs(315, "Congratulations on unlocking the 3 bonus cars!", 205, 205, 205, 3);
                    }
                }
                if (this.bonusstage[2] && this.boncomp[2] == 0) {
                    this.drawcs(35, "Congratulations on completing the bonus stage!", 205, 205, 205, 3);
                    this.drawcs(70, "Your reward is:", 205, 205, 205, 3);
                    this.rd.setColor(new Color(236, 226, 202));
                    final float f2 = (float)Math.random();
                    if (f2 < 0.7) {
                        this.rd.drawRect(260, 130, 349, 126);
                    }
                    else {
                        this.rd.fillRect(260, 130, 350, 127);
                    }
                    this.rd.setColor(new Color(255, 209, 89));
                    this.rd.fillRect(261, 131, 348, 4);
                    this.rd.fillRect(261, 131, 4, 125);
                    this.rd.fillRect(261, 252, 348, 4);
                    this.rd.fillRect(605, 131, 4, 125);
                    this.m.crs = true;
                    this.m.x = -435;
                    this.m.y = 0;
                    this.m.z = -50;
                    this.m.xz = 0;
                    this.m.zy = 0;
                    this.m.ground = 2470;
                    for (int a2 = 37; a2 < 39; ++a2) {
                        aconto[a2].y = 160;
                        aconto[a2].z = 1000;
                        if (a2 == 37) {
                            aconto[a2].x = -200;
                        }
                        else {
                            aconto[a2].x = 200;
                        }
                        final ContO contO9 = aconto[a2];
                        contO9.xz += 5;
                        aconto[a2].zy = 0;
                        final ContO contO10 = aconto[a2];
                        contO10.wzy -= 10;
                        aconto[a2].d(this.rd);
                    }
                    this.drawcs(315, "Congratulations on unlocking these powerful cars!", 205, 205, 205, 3);
                }
                if (this.bonusstage[3] && this.boncomp[3] == 0) {
                    this.rd.setFont(this.adventure.deriveFont(1, 22.0f));
                    this.ftm = this.rd.getFontMetrics();
                    this.drawcs(100, "Congratulations on completing the bonus stage!", 205, 205, 205, 3);
                    this.drawcs(180, "You have unlocked the ability to use \"car points\", which can give your", 205, 205, 205, 3);
                    this.drawcs(215, "cars unique powers and perks, which last even if you then reset them.", 205, 205, 205, 3);
                    this.drawcs(310, "Earn car points by resetting (now \"selling\") unwanted cars.", 205, 205, 205, 3);
                }
                this.rd.setFont(new Font("Arial", 1, 11));
                this.ftm = this.rd.getFontMetrics();
            }
        }
        else {
            this.pin = 30;
            this.rd.drawImage(this.gameov, 350, 157, null);
            this.drawcs(207, "Failed to complete Stage " + checkpoints.stage + "!", 170, 170, 170, 3);
            this.drawcs(224, new StringBuilder().append(checkpoints.name).toString(), 128, 128, 128, 3);
        }
        this.rd.drawImage(this.contin[this.pcontin], 390, 390 - this.pin, null);
        if (control.enter || control.handb) {
            this.fase = 10;
            if (this.loadedt[this.lastload]) {
                if (this.isMidi[this.lastload]) {
                    this.mtracks[this.lastload].setPaused(true);
                }
                else {
                    this.stracks[this.lastload].stop();
                }
            }
            if (this.winner && !this.bonstage && !this.classicmode && checkpoints.stage == mxstage && mxstage != 31) {
                if (this.careermode) {
                    final int[] unlocked = this.unlocked;
                    final int n = 1;
                    ++unlocked[n];
                    this.statchangers[0] = 1;
                    this.statchangers[1] = 1;
                }
                else {
                    final int[] unlocked2 = this.unlocked;
                    final int n2 = 0;
                    ++unlocked2[n2];
                }
                checkpoints.stage = mxstage + 1;
            }
            for (int a = 0; a < 6; ++a) {
                if (this.bonusstage[a] && this.winner) {
                    if (a != 1) {
                        if (this.boncomp[a] == 0) {
                            this.boncomp[a] = 1;
                        }
                    }
                    else {
                        if (this.boncomp[a] == 0) {
                            if (this.racingwin) {
                                this.boncomp[a] = 1;
                            }
                            if (this.wastingwin) {
                                this.boncomp[a] = 2;
                            }
                        }
                        if ((this.boncomp[a] == 1 && this.wastingwin) || (this.boncomp[a] == 2 && this.racingwin)) {
                            this.boncomp[a] = 3;
                        }
                    }
                }
            }
            this.flipo = 0;
            control.enter = false;
            control.handb = false;
        }
        for (int a = 0; a < 2; ++a) {
            if (this.unlocked[a] > 31) {
                this.unlocked[a] = 31;
            }
        }
        this.fade = 0;
    }
    
    public void sortcars(final int i) {
        if (!this.bonstage) {
            final boolean[] aflag = new boolean[this.nplayers];
            boolean lateststage = false;
            if (!this.careermode && !this.classicmode && this.unlocked[0] == i && this.unlocked[0] != 28) {
                lateststage = true;
            }
            if (this.careermode && this.unlocked[1] == i && this.unlocked[1] != 31) {
                lateststage = true;
            }
            if (lateststage || this.hardstage) {
                int bestcar = 7 + (i + 1) / 2;
                if (this.careermode && i >= 21) {
                    bestcar = 11 + i / 3;
                }
                if (bestcar == 20 || bestcar == 21) {
                    bestcar = 22;
                }
                this.sc[this.nplayers - 1] = bestcar;
                int k = 1;
                do {
                    aflag[k] = false;
                } while (++k < this.nplayers - 1);
                k = 1;
                while (true) {
                    if (aflag[k]) {
                        if (++k >= this.nplayers - 1) {
                            break;
                        }
                        continue;
                    }
                    else {
                        if (!this.careermode) {
                            this.sc[k] = (int)(Math.random() * bestcar);
                        }
                        else {
                            int randomiser = (int)(Math.random() * bestcar);
                            if (bestcar == 18 || bestcar == 19) {
                                randomiser = (int)(Math.random() * 22.0);
                            }
                            int gcboost = 0;
                            if (this.m.random() > this.m.random() && randomiser <= 15) {
                                gcboost = 23;
                            }
                            this.sc[k] = gcboost + randomiser;
                            aflag[k] = true;
                        }
                        aflag[k] = true;
                        boolean stopception = false;
                        if (i == 16 || i == 12 || i == 6 || i == 7 || i == 8 || i == 11 || i == 13 || i == 17 || i == 19 || i == 23 || i == 24) {
                            stopception = true;
                        }
                        boolean allowrepeats = true;
                        if ((this.nplayers <= (i + 17) / 2 && this.nplayers <= 18) || this.careermode) {
                            allowrepeats = false;
                        }
                        int l = 0;
                        do {
                            if (k != l && this.sc[k] == this.sc[l] && !allowrepeats && !stopception) {
                                aflag[k] = false;
                            }
                        } while (++l < this.nplayers);
                        Math.random();
                        final float n = this.proba[this.sc[k]];
                        if (i != 11 && i != 12 && k != 1 && k != 2 && this.sc[k] == 13 && !this.careermode) {
                            aflag[k] = false;
                        }
                        if (!this.careermode) {
                            continue;
                        }
                        if (i == 4 && ((this.sc[k] <= 4 && this.sc[k] != 2) || (this.sc[k] >= 23 && this.sc[k] <= 27 && this.sc[k] != 25))) {
                            aflag[k] = false;
                        }
                        if (i == 12 && (this.sc[k] <= 4 || this.sc[k] == 7 || this.sc[k] == 12 || this.sc[k] == 30 || this.sc[k] == 35 || (this.sc[k] >= 23 && this.sc[k] <= 27))) {
                            aflag[k] = false;
                        }
                        if (i == 6 && ((this.sc[k] <= 4 && this.sc[k] != 2) || this.sc[k] == 9 || this.sc[k] == 32 || (this.sc[k] >= 23 && this.sc[k] <= 27 && this.sc[k] != 25))) {
                            aflag[k] = false;
                        }
                        if (i == 7 && ((this.sc[k] <= 4 && this.sc[k] != 2) || this.sc[k] == 9 || this.sc[k] == 10 || this.sc[k] == 32 || this.sc[k] == 33 || (this.sc[k] >= 23 && this.sc[k] <= 27 && this.sc[k] != 25))) {
                            aflag[k] = false;
                        }
                        if (i == 8 && ((this.sc[k] <= 5 && this.sc[k] != 2) || (this.sc[k] >= 23 && this.sc[k] <= 28 && this.sc[k] != 25))) {
                            aflag[k] = false;
                        }
                        if (i == 11 && ((this.sc[k] <= 7 && this.sc[k] != 0) || this.sc[k] == 8 || this.sc[k] == 11 || this.sc[k] == 12 || (this.sc[k] >= 23 && this.sc[k] <= 30 && this.sc[k] != 29) || this.sc[k] == 31 || this.sc[k] == 34 || this.sc[k] == 35)) {
                            aflag[k] = false;
                        }
                        if (i == 13 && k % 3 == 2 && k < 9 && ((this.sc[k] <= 7 && this.sc[k] != 5) || (this.sc[k] >= 11 && this.sc[k] <= 13) || (this.sc[k] >= 23 && this.sc[k] <= 30 && this.sc[k] != 28) || (this.sc[k] >= 34 && this.sc[k] <= 36))) {
                            aflag[k] = false;
                        }
                        if (i == 17 && this.sc[k] != 14 && this.sc[k] != 13 && this.sc[k] != 11 && this.sc[k] != 15 && this.sc[k] != 37 && this.sc[k] != 36 && this.sc[k] != 34 && this.sc[k] != 38) {
                            aflag[k] = false;
                        }
                        if (i == 19 && this.sc[k] != 15 && this.sc[k] != 16 && this.sc[k] != 38) {
                            aflag[k] = false;
                        }
                        if (i == 23 && (this.sc[k] <= 12 || this.sc[k] == 14 || (this.sc[k] >= 23 && this.sc[k] <= 35) || this.sc[k] == 37)) {
                            aflag[k] = false;
                        }
                        if (i == 24 && (this.sc[k] <= 14 || this.sc[k] == 17 || (this.sc[k] >= 23 && this.sc[k] <= 37))) {
                            aflag[k] = false;
                        }
                        if (this.sc[k] == bestcar || (bestcar == 18 && (this.sc[k] == 21 || this.sc[k] == 19)) || (bestcar == 19 && this.sc[k] == 21 && i < 25)) {
                            aflag[k] = false;
                        }
                        if (this.sc[k] != 21) {
                            continue;
                        }
                        aflag[k] = false;
                    }
                }
                if (this.careermode) {
                    if (i == 2) {
                        if (this.sc[0] != 29) {
                            this.sc[9] = 29;
                        }
                        else {
                            this.sc[9] = 25;
                        }
                        if (this.sc[0] != 6) {
                            this.sc[8] = 6;
                        }
                        else {
                            this.sc[8] = 25;
                        }
                    }
                    if (i == 3) {
                        if (this.sc[0] != 24) {
                            this.sc[this.nplayers - 2] = 24;
                        }
                        else {
                            this.sc[this.nplayers - 2] = 26;
                        }
                        if (this.sc[0] != 1) {
                            this.sc[this.nplayers - 3] = 1;
                        }
                        else {
                            this.sc[this.nplayers - 2] = 26;
                        }
                    }
                    if (i == 5) {
                        this.sc[5] = 3;
                        this.sc[4] = 1;
                        this.sc[3] = 8;
                        this.sc[2] = 31;
                        this.sc[1] = 24;
                    }
                    if (i == 6) {
                        this.sc[9] = 9;
                        this.sc[8] = 32;
                        this.sc[7] = 31;
                        this.sc[6] = 29;
                        this.sc[5] = 8;
                        this.sc[5] = 7;
                        this.sc[4] = 9;
                        this.sc[3] = 25;
                        this.sc[2] = 5;
                    }
                    if (i == 7) {
                        this.sc[9] = 10;
                        this.sc[8] = 10;
                        this.sc[7] = 33;
                        this.sc[6] = 32;
                        this.sc[5] = 8;
                        this.sc[4] = 32;
                        this.sc[3] = 31;
                        this.sc[2] = 9;
                    }
                    if (i == 8) {
                        if (this.m.random() > this.m.random()) {
                            this.sc[1] = 32;
                        }
                        else {
                            this.sc[1] = 9;
                        }
                        if (this.m.random() > this.m.random()) {
                            this.sc[2] = 33;
                        }
                        else {
                            this.sc[2] = 10;
                        }
                        this.sc[this.nplayers - 3] = 33;
                        this.sc[this.nplayers - 4] = 9;
                        this.sc[this.nplayers - 5] = 8;
                        this.sc[this.nplayers - 7] = 7;
                        this.sc[this.nplayers - 8] = 31;
                    }
                    if (i == 9) {
                        this.sc[9] = 10;
                        this.sc[8] = 33;
                        this.sc[7] = 28;
                        this.sc[6] = 10;
                        this.sc[5] = 0;
                        this.sc[4] = 29;
                        this.sc[3] = 34;
                        this.sc[2] = 11;
                        this.sc[1] = 5;
                    }
                    if (i == 10) {
                        this.sc[5] = 3;
                        this.sc[1] = 24;
                        this.sc[7] = 8;
                        this.sc[6] = 33;
                        this.sc[8] = 9;
                        this.sc[4] = 26;
                        this.sc[9] = 10;
                        this.sc[2] = 31;
                        if ((this.sc[0] >= 8 && this.sc[0] <= 10) || this.sc[0] == 1 || this.sc[0] == 3 || (this.sc[0] >= 31 && this.sc[0] <= 33) || this.sc[0] == 24 || this.sc[0] == 26) {
                            this.sc[3] = 11;
                        }
                        if (this.sc[0] == 11 || this.sc[0] == 34) {
                            this.sc[3] = 10;
                        }
                    }
                    if (i == 11) {
                        this.sc[9] = 12;
                        this.sc[8] = 12;
                        this.sc[7] = 11;
                        this.sc[6] = 34;
                        this.sc[5] = 33;
                        this.sc[4] = 32;
                    }
                    if (i == 12) {
                        this.sc[1] = 34;
                        this.sc[2] = 8;
                        this.sc[3] = 9;
                        this.sc[4] = 10;
                        this.sc[5] = 33;
                        this.sc[6] = 10;
                        this.sc[7] = 32;
                        this.sc[8] = 11;
                        this.sc[9] = 34;
                    }
                    if (i == 13) {
                        this.sc[1] = 36;
                        this.sc[4] = 10;
                        if (Math.random() > Math.random()) {
                            if (Math.random() > Math.random()) {
                                this.sc[7] = 9;
                            }
                            else {
                                this.sc[7] = 32;
                            }
                        }
                        else if (Math.random() > Math.random()) {
                            this.sc[7] = 8;
                        }
                        else {
                            this.sc[7] = 31;
                        }
                        this.sc[3] = 34;
                        this.sc[6] = 11;
                        this.sc[9] = 34;
                        this.sc[10] = 13;
                        this.sc[11] = 13;
                        this.sc[12] = 11;
                        this.sc[13] = 10;
                        this.sc[14] = 10;
                    }
                    if (i == 14) {
                        this.sc[4] = 12;
                        this.sc[3] = 3;
                        this.sc[2] = 13;
                        this.sc[1] = 11;
                    }
                    if (i == 15) {
                        this.sc[13] = 14;
                        this.sc[12] = 14;
                        this.sc[11] = 13;
                        this.sc[10] = 13;
                        this.sc[9] = 11;
                        this.sc[8] = 10;
                        for (int a = 3; a < 8; ++a) {
                            if (this.sc[a] < 10 || this.sc[a] == 13) {
                                if (this.m.random() > this.m.random()) {
                                    this.sc[a] = 10;
                                }
                                else {
                                    this.sc[a] = 14;
                                }
                            }
                        }
                        this.sc[2] = 13;
                        this.sc[1] = 11;
                    }
                    if (i == 16) {
                        this.sc[9] = 14;
                        this.sc[8] = 37;
                        this.sc[7] = 13;
                        this.sc[6] = 11;
                        this.sc[5] = 35;
                        this.sc[4] = 36;
                        this.sc[3] = 34;
                        this.sc[2] = 33;
                        this.sc[1] = 9;
                    }
                    if (i == 17) {
                        this.sc[this.nplayers - 2] = 15;
                        this.sc[this.nplayers - 3] = 38;
                        this.sc[this.nplayers - 4] = 14;
                        this.sc[this.nplayers - 5] = 13;
                        this.sc[this.nplayers - 6] = 36;
                        this.sc[this.nplayers - 7] = 11;
                        this.sc[this.nplayers - 8] = 34;
                        this.sc[this.nplayers - 9] = 9;
                        this.sc[this.nplayers - 10] = 32;
                    }
                    if (i == 18) {
                        this.sc[5] = 12;
                        this.sc[4] = 14;
                        this.sc[3] = 15;
                        this.sc[2] = 38;
                        this.sc[1] = 13;
                    }
                    if (i == 19) {
                        this.sc[this.nplayers - 2] = 16;
                        this.sc[this.nplayers - 3] = 16;
                        this.sc[this.nplayers - 4] = 36;
                        this.sc[this.nplayers - 5] = 15;
                        this.sc[this.nplayers - 6] = 38;
                        this.sc[this.nplayers - 7] = 16;
                        this.sc[this.nplayers - 8] = 13;
                        if (this.m.random() > this.m.random()) {
                            this.sc[this.nplayers - 9] = 15;
                        }
                        else {
                            this.sc[this.nplayers - 9] = 38;
                        }
                        this.sc[this.nplayers - 10] = 16;
                    }
                    if (i == 21) {
                        this.sc[9] = 17;
                        this.sc[8] = 17;
                        this.sc[7] = 16;
                        this.sc[6] = 15;
                        this.sc[5] = 16;
                        this.sc[4] = 38;
                        this.sc[3] = 37;
                        this.sc[2] = 13;
                        this.sc[1] = 20;
                    }
                    if (i == 22) {
                        this.sc[9] = 16;
                        this.sc[8] = 15;
                        this.sc[7] = 16;
                        this.sc[6] = 38;
                        this.sc[5] = 20;
                        this.sc[4] = 15;
                        this.sc[3] = 14;
                        this.sc[2] = 17;
                        this.sc[1] = 17;
                    }
                    if (i == 23) {
                        this.sc[10] = 17;
                        this.sc[9] = 17;
                        this.sc[8] = 16;
                        this.sc[7] = 15;
                        this.sc[6] = 17;
                        this.sc[5] = 38;
                        this.sc[4] = 16;
                    }
                    if (i == 24) {
                        this.sc[9] = 17;
                        this.sc[8] = 18;
                        this.sc[7] = 18;
                        this.sc[6] = 16;
                        this.sc[5] = 15;
                        this.sc[4] = 16;
                    }
                }
            }
            else {
                int byte0 = this.nplayers;
                int bestcar2 = 7 + (i + 1) / 2;
                if (this.careermode) {
                    if (i >= 21) {
                        bestcar2 = 11 + i / 3;
                    }
                    if (bestcar2 > 19) {
                        bestcar2 = 22;
                    }
                }
                if (this.classicmode) {
                    bestcar2 = 30 + (i + 1) / 2;
                    if (bestcar2 > 38) {
                        bestcar2 = 38;
                    }
                }
                boolean excep = false;
                if (this.careermode && i >= 21) {
                    excep = true;
                }
                if (this.sc[0] != bestcar2 || excep) {
                    this.sc[this.nplayers - 1] = bestcar2;
                    byte0 = this.nplayers - 1;
                }
                for (int k2 = 1; k2 < byte0; ++k2) {
                    aflag[k2] = false;
                    while (!aflag[k2]) {
                        int far = this.unlocked[0];
                        if (this.careermode) {
                            far = this.unlocked[1];
                        }
                        int bestunlocked = 7 + (far + 1) / 2;
                        if (this.careermode) {
                            if (far >= 21) {
                                bestunlocked = 11 + far / 3;
                            }
                            if (bestunlocked > 19) {
                                bestunlocked = 22;
                            }
                        }
                        if (this.classicmode) {
                            this.sc[k2] = (int)(Math.random() * 16.0) + 23;
                            aflag[k2] = true;
                        }
                        else if (!this.careermode) {
                            this.sc[k2] = (int)(Math.random() * (bestunlocked + 1));
                            aflag[k2] = true;
                        }
                        else {
                            int randomiser2 = (int)(Math.random() * (bestunlocked + 1));
                            if (bestunlocked == 18 || bestunlocked == 19) {
                                randomiser2 = (int)(Math.random() * 22.0);
                            }
                            int gcboost2 = 0;
                            if (this.m.random() > this.m.random() && randomiser2 <= 15) {
                                gcboost2 = 23;
                            }
                            this.sc[k2] = gcboost2 + randomiser2;
                            aflag[k2] = true;
                        }
                        float f = 0.0f;
                        int g = 0;
                        do {
                            if (!this.careermode && !this.classicmode && k2 != g && this.sc[k2] == this.sc[g] && this.unlocked[0] >= 5 && this.nplayers <= 11) {
                                aflag[k2] = false;
                            }
                            if (this.careermode) {
                                boolean allowrepeats2 = true;
                                if ((this.nplayers <= (this.unlocked[1] + 17) / 2 && this.nplayers <= 18) || this.careermode) {
                                    allowrepeats2 = false;
                                }
                                if (k2 != g && this.sc[k2] == this.sc[g] && !allowrepeats2) {
                                    aflag[k2] = false;
                                }
                                if (bestunlocked <= 15 && this.sc[k2] == bestunlocked + 23) {
                                    aflag[k2] = false;
                                }
                                int usercar = this.sc[0];
                                if (usercar == 20) {
                                    usercar = 18;
                                }
                                if (usercar == 21) {
                                    usercar = 17;
                                }
                                if (usercar >= 23) {
                                    usercar = this.sc[0] - 23;
                                }
                                if ((bestunlocked == 18 && (this.sc[k2] == 21 || this.sc[k2] == 19)) || (bestcar2 == 19 && this.sc[k2] == 21 && this.unlocked[1] < 25)) {
                                    aflag[k2] = false;
                                }
                                if (this.sc[k2] == 21) {
                                    aflag[k2] = false;
                                }
                                final boolean[] standardban = new boolean[4];
                                if ((this.sc[k2] == 13 || this.sc[k2] == 36) && this.averagelevel < this.maxlevel[5] && usercar < 13) {
                                    standardban[0] = true;
                                }
                                if ((this.sc[k2] == 18 || this.sc[k2] == 20) && this.averagelevel < this.maxlevel[10] && usercar < 18) {
                                    standardban[1] = true;
                                }
                                if ((this.sc[k2] == 19 || this.sc[k2] == 21) && this.averagelevel < this.maxlevel[17] && usercar < 18) {
                                    standardban[2] = true;
                                }
                                if (this.sc[k2] == 22 && this.averagelevel < this.maxlevel[24]) {
                                    standardban[3] = true;
                                }
                                boolean specialonly = false;
                                if (!this.rollonce) {
                                    this.chance = (float)(Math.random() * 1.0);
                                    this.rollonce = true;
                                }
                                if (usercar >= 18) {
                                    float probability = 0.5f;
                                    if (usercar == 22) {
                                        probability = 0.25f;
                                    }
                                    if ((this.sc[k2] == 18 || this.sc[k2] == 19) && this.chance >= probability) {
                                        specialonly = true;
                                    }
                                }
                                if (!specialonly && ((standardban[0] && this.sc[k2] == 13) || (standardban[1] && (this.sc[k2] == 18 || this.sc[k2] == 20)) || (standardban[2] && (this.sc[k2] == 19 || this.sc[k2] == 21)) || (standardban[3] && this.sc[k2] == 22))) {
                                    aflag[k2] = false;
                                }
                            }
                            if (!this.careermode && this.classicmode && k2 != g && this.sc[k2] == this.sc[g]) {
                                aflag[k2] = false;
                            }
                        } while (++g < this.nplayers);
                        f = this.proba[this.sc[k2]];
                        if (i - this.sc[k2] > 4 && i != 28 && !this.classicmode) {
                            f += (i - this.sc[k2] - 4) / 10.0f;
                            if (f > 0.9) {
                                f = 0.9f;
                            }
                        }
                        if (i - this.sc[k2] > 4 && i != 17 && this.classicmode) {
                            f += (i - this.sc[k2] - 4) / 10.0f;
                            if (f > 0.9) {
                                f = 0.9f;
                            }
                        }
                        if (i == 16 && f < 0.9) {
                            f = 0.9f;
                        }
                        if (Math.random() < f) {
                            aflag[k2] = false;
                        }
                        if (i != 11 && i != 12 && k2 != 1 && k2 != 2 && aflag[k2] && this.sc[k2] == 36 && this.classicmode) {
                            aflag[k2] = false;
                            if ((Math.random() <= Math.random() * 1.6 || i == 14) && (i != 16 || Math.random() <= Math.random())) {
                                continue;
                            }
                            if (Math.random() > Math.random()) {
                                this.sc[1] = 36;
                            }
                            else {
                                this.sc[2] = 36;
                            }
                        }
                    }
                }
            }
            if (i == 12 && this.classicmode) {
                boolean flag6 = false;
                int l2 = 0;
                do {
                    if (this.sc[l2] == 34) {
                        flag6 = true;
                    }
                } while (++l2 < this.nplayers - 1);
                if (!flag6) {
                    this.sc[2] = 34;
                }
            }
            if (this.dontdisplay) {
                if (this.ptmatch == 1) {
                    for (int a2 = 0; a2 < 11; ++a2) {
                        this.sc[a2] = 16;
                    }
                }
                if (this.ptmatch == 2) {
                    for (int a2 = 0; a2 < 11; ++a2) {
                        this.sc[a2] = 35;
                    }
                }
                if (this.ptmatch == 3) {
                    for (int a2 = 0; a2 < 11; ++a2) {
                        this.sc[a2] = 27;
                    }
                }
                if (this.ptmatch == 4) {
                    for (int a2 = 0; a2 < 11; ++a2) {
                        this.sc[a2] = 15;
                    }
                }
                if (this.ptmatch == 5) {
                    for (int a2 = 0; a2 < 11; ++a2) {
                        this.sc[a2] = 11;
                    }
                }
            }
            if (this.careermode) {
                if (i == 17) {
                    this.sc[1] = 13;
                    this.sc[2] = 13;
                    this.sc[3] = 13;
                }
                if (i == 11) {
                    for (int a2 = 1; a2 < 5; ++a2) {
                        this.sc[a2] = 11;
                    }
                }
                if (i == 20) {
                    this.sc[this.nplayers - 1] = 17;
                }
                if (i == 23 && (this.unlocked[1] == 23 || this.hardstage)) {
                    this.sc[1] = 18;
                }
            }
        }
        else {
            if (this.bonusstage[0]) {
                this.sc[10] = 32;
                this.sc[9] = 33;
                this.sc[8] = 30;
                this.sc[7] = 26;
                this.sc[6] = 31;
                this.sc[5] = 25;
                this.sc[4] = 28;
                this.sc[3] = 29;
                this.sc[2] = 27;
                this.sc[1] = 23;
            }
            if (this.bonusstage[1]) {
                this.sc[10] = 33;
                this.sc[9] = 35;
                this.sc[8] = 35;
                for (int a3 = 1; a3 < 5; ++a3) {
                    this.sc[a3] = 36;
                }
                for (int a3 = 5; a3 < 8; ++a3) {
                    this.sc[a3] = 34;
                }
            }
            if (this.bonusstage[2]) {
                this.sc[8] = 38;
                this.sc[7] = 37;
                this.sc[6] = 34;
                this.sc[5] = 31;
                this.sc[4] = 32;
                this.sc[3] = 33;
                this.sc[2] = 35;
                this.sc[1] = 36;
            }
            if (this.bonusstage[3]) {
                this.sc[4] = 20;
                this.sc[3] = 38;
                this.sc[2] = 36;
                this.sc[1] = 34;
            }
        }
        this.sortedcars = true;
    }
    
    public void sparkeng(int i) {
        ++i;
        int j = 0;
        do {
            if (i == j) {
                if (this.pengs[j]) {
                    continue;
                }
                this.engs[this.enginsignature[this.sc[0]]][j].loop();
                this.pengs[j] = true;
            }
            else {
                if (!this.pengs[j]) {
                    continue;
                }
                this.engs[this.enginsignature[this.sc[0]]][j].stop();
                this.pengs[j] = false;
            }
        } while (++j < 5);
    }
    
    public void drawcs(final int i, final String s, int j, int k, int l, final int i1) {
        if (i1 != 3 && i1 != 4) {
            j += (j * (this.m.snap[0] / 100.0f));  // cast: bytecode-verified
            if (j > 255) {
                j = 255;
            }
            if (j < 0) {
                j = 0;
            }
            k += (k * (this.m.snap[1] / 100.0f));  // cast: bytecode-verified
            if (k > 255) {
                k = 255;
            }
            if (k < 0) {
                k = 0;
            }
            l += (l * (this.m.snap[2] / 100.0f));  // cast: bytecode-verified
            if (l > 255) {
                l = 255;
            }
            if (l < 0) {
                l = 0;
            }
        }
        if (i1 == 4) {
            j -= (j * (this.m.snap[0] / 100.0f));  // cast: bytecode-verified
            if (j > 255) {
                j = 255;
            }
            if (j < 0) {
                j = 0;
            }
            k -= (k * (this.m.snap[1] / 100.0f));  // cast: bytecode-verified
            if (k > 255) {
                k = 255;
            }
            if (k < 0) {
                k = 0;
            }
            l -= (l * (this.m.snap[2] / 100.0f));  // cast: bytecode-verified
            if (l > 255) {
                l = 255;
            }
            if (l < 0) {
                l = 0;
            }
        }
        if (i1 == 1) {
            this.rd.setColor(new Color(0, 0, 0));
            this.rd.drawString(s, 435 - this.ftm.stringWidth(s) / 2 + 1, i + 1);
        }
        if (i1 == 2) {
            j = (j * 2 + this.m.csky[0] * 1) / 3;
            if (j > 255) {
                j = 255;
            }
            if (j < 0) {
                j = 0;
            }
            k = (k * 2 + this.m.csky[1] * 1) / 3;
            if (k > 255) {
                k = 255;
            }
            if (k < 0) {
                k = 0;
            }
            l = (l * 2 + this.m.csky[2] * 1) / 3;
            if (l > 255) {
                l = 255;
            }
            if (l < 0) {
                l = 0;
            }
        }
        this.rd.setColor(new Color(j, k, l));
        this.rd.drawString(s, 435 - this.ftm.stringWidth(s) / 2, i);
    }
    
    public int py(final int i, final int j, final int k, final int l) {
        return (i - j) * (i - j) + (k - l) * (k - l);
    }
    
    public void trackbg(final boolean flag) {
        int i = 0;
        ++this.trkl;
        if (this.trkl > this.trklim) {
            i = 1;
            this.trklim = (int)(Math.random() * 40.0);
            this.trkl = 0;
        }
        if (flag) {
            i = 0;
        }
        int j = 0;
        do {
            this.rd.drawImage(this.trackbg[i][j], this.trkx[j], 40, null);
            final int[] trkx = this.trkx;
            final int n = j;
            --trkx[n];
            if (this.trkx[j] <= -570) {
                this.trkx[j] = 770;
            }
        } while (++j < 2);
    }
    
    public int sumAll(final int... numbers) {
        int result = 0;
        for (int i = 0; i < numbers.length; ++i) {
            result += numbers[i];
        }
        return result;
    }
    
    public void stageselect(final CheckPoints checkpoints, final Control control, final Madness[] madness) {
        for (int i = 0; i < 200; ++i) {
            this.mtracks[i] = null;
            this.stracks[i] = null;
            this.isMidi[i] = false;
            this.isOgg[i] = false;
            this.loadedt[i] = false;
        }
        this.stages.play();
        this.rd.setColor(new Color(0, 0, 0));
        if (!this.bonstage) {
            int notcm = 0;
            if (!this.careermode) {
                notcm = 40;
            }
            if (checkpoints.stage != 1 && this.showopstage != 195) {
                this.rd.drawImage(this.back[this.pback], 310, 400 + notcm, null);
            }
            if (checkpoints.stage != 28 && !this.classicmode && this.showopstage != 195 && !this.careermode) {
                this.rd.drawImage(this.next[this.pnext], 500, 400 + notcm, null);
            }
            if (checkpoints.stage < 17 && this.classicmode) {
                this.rd.drawImage(this.next[this.pnext], 500, 400 + notcm, null);
            }
            if (checkpoints.stage < this.betalimit && this.careermode) {
                this.rd.drawImage(this.next[this.pnext], 500, 400 + notcm, null);
            }
        }
        this.rd.setFont(new Font("SansSerif", 1, 13));
        this.ftm = this.rd.getFontMetrics();
        String exclamation = "";
        if (!this.dontdisplay || (this.dontdisplay && this.showopstage == 195)) {
            if (this.careermode) {
                boolean isithard = false;
                if ((this.hardstage || checkpoints.stage == this.unlocked[1] || this.bonstage) && madness[0].level[this.sc[0]] < this.averagelevel - 2) {
                    isithard = true;
                }
                if (this.startinglevel >= this.softlevelcap && !this.nolevels) {
                    exclamation = " (!)";
                    final Polygon warning = new Polygon();
                    warning.addPoint(354, 65);
                    warning.addPoint(349, 70);
                    warning.addPoint(349, 80);
                    warning.addPoint(354, 85);
                    warning.addPoint(516, 85);
                    warning.addPoint(521, 80);
                    warning.addPoint(521, 70);
                    warning.addPoint(516, 65);
                    this.rd.setColor(new Color(0, 0, 0, 200));
                    this.rd.fillPolygon(warning);
                    this.rd.setFont(this.adventure.deriveFont(1, 13.0f));
                    this.ftm = this.rd.getFontMetrics();
                    this.rd.setColor(new Color(255, 0, 0));
                    this.rd.drawString("No bonus stat points", 359, 80);
                }
                if (isithard) {
                    final Polygon warning = new Polygon();
                    warning.addPoint(354, 65);
                    warning.addPoint(349, 70);
                    warning.addPoint(349, 80);
                    warning.addPoint(354, 85);
                    warning.addPoint(516, 85);
                    warning.addPoint(521, 80);
                    warning.addPoint(521, 70);
                    warning.addPoint(516, 65);
                    this.rd.setColor(new Color(0, 0, 0, 200));
                    this.rd.fillPolygon(warning);
                    this.rd.setFont(this.adventure.deriveFont(1, 13.0f));
                    this.ftm = this.rd.getFontMetrics();
                    final String tosay = "+ bonus stat points";
                    final int howlong = (int)(this.ftm.stringWidth(tosay) / 2.0);
                    this.rd.setColor(new Color(0, 200, 0));
                    this.rd.drawString(tosay, 433 - howlong, 80);
                }
            }
            this.rd.setFont(this.adventure.deriveFont(1, 22.0f));
            this.ftm = this.rd.getFontMetrics();
            int red = 230;
            int green = 230;
            int blue = 230;
            if (this.careermode && !this.bonstage && checkpoints.stage < this.unlocked[1] && this.hardstage) {
                red = 240;
                green = 120;
                blue = 120;
            }
            String title = "Stage " + checkpoints.stage + ":   " + checkpoints.name;
            if (this.bonstage) {
                for (int a = 0; a < 6; ++a) {
                    if (this.bonusstage[a]) {
                        title = "BONUS STAGE " + (a + 1);
                    }
                }
            }
            final int titlelength = this.ftm.stringWidth(new StringBuilder().append(title).toString());
            int notcm2 = 0;
            if (!this.careermode) {
                notcm2 = 15;
            }
            this.rd.setColor(new Color(0, 0, 0, 200));
            final Polygon titlecard = new Polygon();
            titlecard.addPoint(428 - titlelength / 2, 5);
            titlecard.addPoint(442 + titlelength / 2, 5);
            titlecard.addPoint(457 + titlelength / 2, 20);
            titlecard.addPoint(457 + titlelength / 2, 46 - notcm2);
            titlecard.addPoint(442 + titlelength / 2, 61 - notcm2);
            titlecard.addPoint(428 - titlelength / 2, 61 - notcm2);
            titlecard.addPoint(413 - titlelength / 2, 46 - notcm2);
            titlecard.addPoint(413 - titlelength / 2, 20);
            this.rd.fillPolygon(titlecard);
            this.drawcs(32, title, red, green, blue, 3);
            this.rd.setFont(new Font("SansSerif", 1, 13));
            this.ftm = this.rd.getFontMetrics();
        }
        if (!this.classicmode && !this.careermode && checkpoints.stage == 26) {
            this.dontdisplay = true;
            this.rd.setFont(this.adventure.deriveFont(1, 22.0f));
            this.ftm = this.rd.getFontMetrics();
            this.drawcs(32, "THE PREMIER TOURNAMENT", 255, 255, 255, 3);
            this.rd.setFont(new Font("SansSerif", 1, 13));
            this.ftm = this.rd.getFontMetrics();
        }
        if (this.classicmode || this.careermode || (!this.classicmode && !this.careermode && checkpoints.stage != 26)) {
            this.dontdisplay = false;
        }
        if (control.scouting && this.careermode) {
            this.fase = 202;
            control.scouting = false;
        }
        if (!this.careermode || (this.careermode && checkpoints.stage < this.betalimit)) {
            int notcm3 = 0;
            if (!this.careermode) {
                notcm3 = 40;
            }
            this.rd.drawImage(this.contin[this.pcontin], 390, 400 + notcm3, null);
        }
        if (this.careermode && (checkpoints.stage == 5 || checkpoints.stage == 11 || checkpoints.stage == 15 || checkpoints.stage == 18) && !this.bonstage) {
            this.clickable = true;
            final Polygon feg = new Polygon();
            feg.addPoint(23, 32);
            feg.addPoint(30, 15);
            feg.addPoint(200, 15);
            feg.addPoint(207, 32);
            feg.addPoint(200, 49);
            feg.addPoint(30, 49);
            int x = 150;
            if (this.above[2]) {
                x = 250;
            }
            this.rd.setColor(new Color(255, 255, 255, x));
            this.rd.fillPolygon(feg);
            this.rd.setFont(this.adventure.deriveFont(1, 18.0f));
            this.ftm = this.rd.getFontMetrics();
            this.rd.setColor(new Color(0, 0, 0, 255));
            if (!this.bonstage) {
                this.rd.drawString("BONUS STAGE!", 115 - this.ftm.stringWidth("BONUS STAGE!") / 2, 39);
            }
        }
        else {
            this.clickable = false;
        }
        if (this.careermode) {
            final Polygon xpcard = new Polygon();
            xpcard.addPoint(718, 18);
            xpcard.addPoint(725, 5);
            xpcard.addPoint(853, 5);
            xpcard.addPoint(860, 18);
            xpcard.addPoint(853, 31);
            xpcard.addPoint(725, 31);
            int xpcardfade = 100;
            if (this.xpbuttonhover) {
                xpcardfade = 200;
            }
            this.rd.setColor(new Color(0, 125, 0, xpcardfade));
            if (this.disablexp) {
                this.rd.setColor(new Color(125, 0, 0, xpcardfade));
            }
            this.rd.fillPolygon(xpcard);
            this.rd.setColor(new Color(230, 230, 230));
            this.rd.setFont(this.adventure.deriveFont(1, 12.0f));
            this.ftm = this.rd.getFontMetrics();
            if (this.disablexp) {
                this.rd.drawString("xp gain: DISABLED", 789 - this.ftm.stringWidth("xp gain: DISABLED") / 2, 22);
            }
            else {
                this.rd.drawString("xp gain: ENABLED", 789 - this.ftm.stringWidth("xp gain: ENABLED") / 2, 22);
            }
            if (checkpoints.stage < this.unlocked[1] && !this.bonstage && this.unlocked[1] >= 3) {
                this.rd.setFont(this.adventure.deriveFont(1, 18.0f));
                this.ftm = this.rd.getFontMetrics();
                final Polygon[] hardstage = new Polygon[3];
                final Polygon[] tfisthis = new Polygon[3];
                final int[][] hmfade = new int[3][2];
                for (int a2 = 0; a2 < 3; ++a2) {
                    for (int b = 0; b < 2; ++b) {
                        if (this.customise[a2][b]) {
                            hmfade[a2][b] = 200;
                        }
                        else {
                            hmfade[a2][b] = 100;
                        }
                    }
                    (hardstage[a2] = new Polygon()).addPoint(670, 452 - a2 * 36);
                    hardstage[a2].addPoint(677, 439 - a2 * 36);
                    hardstage[a2].addPoint(805, 439 - a2 * 36);
                    hardstage[a2].addPoint(812, 452 - a2 * 36);
                    hardstage[a2].addPoint(805, 465 - a2 * 36);
                    hardstage[a2].addPoint(677, 465 - a2 * 36);
                    this.rd.setColor(new Color(255, 255, 255, hmfade[a2][0]));
                    this.rd.fillPolygon(hardstage[a2]);
                    (tfisthis[a2] = new Polygon()).addPoint(823, 452 - a2 * 36);
                    tfisthis[a2].addPoint(830, 439 - a2 * 36);
                    tfisthis[a2].addPoint(853, 439 - a2 * 36);
                    tfisthis[a2].addPoint(860, 452 - a2 * 36);
                    tfisthis[a2].addPoint(853, 465 - a2 * 36);
                    tfisthis[a2].addPoint(830, 465 - a2 * 36);
                    this.rd.setColor(new Color(255, 255, 255, hmfade[a2][1]));
                    this.rd.fillPolygon(tfisthis[a2]);
                    this.rd.setColor(new Color(0, 0, 0));
                    this.rd.drawString("?", 842 - this.ftm.stringWidth("?") / 2, 459 - a2 * 36);
                }
                this.rd.setColor(new Color(0, 0, 0));
                this.rd.drawString("hard mode", 741 - this.ftm.stringWidth("hard mode") / 2, 459);
                if (!this.scalelevels) {
                    this.rd.drawString("scale levels", 741 - this.ftm.stringWidth("scale levels") / 2, 423);
                }
                else {
                    this.rd.drawString("old levels", 741 - this.ftm.stringWidth("old levels") / 2, 423);
                }
                if (!this.nolevels) {
                    this.rd.drawString("no levels", 741 - this.ftm.stringWidth("no levels") / 2, 387);
                }
                else {
                    this.rd.drawString("old levels", 741 - this.ftm.stringWidth("old levels") / 2, 387);
                }
                for (int a2 = 0; a2 < 3; ++a2) {
                    int boxchange = 0;
                    if (a2 == 1) {
                        boxchange = 40;
                    }
                    if (a2 == 2) {
                        boxchange = 22;
                    }
                    if (this.customise[a2][1]) {
                        final Polygon infobox = new Polygon();
                        infobox.addPoint(670, 345);
                        infobox.addPoint(680, 355);
                        infobox.addPoint(850, 355);
                        infobox.addPoint(860, 345);
                        infobox.addPoint(860, 255 - boxchange);
                        infobox.addPoint(850, 245 - boxchange);
                        infobox.addPoint(680, 245 - boxchange);
                        infobox.addPoint(670, 255 - boxchange);
                        this.rd.setColor(new Color(0, 0, 0, 150));
                        this.rd.fillPolygon(infobox);
                        this.rd.setColor(new Color(255, 255, 255));
                        this.rd.setFont(this.adventure.deriveFont(1, 12.0f));
                        this.ftm = this.rd.getFontMetrics();
                    }
                }
                if (this.customise[0][1]) {
                    this.rd.drawString("This puts the difficulty", 683, 266);
                    this.rd.drawString("of the stage back to", 683, 284);
                    this.rd.drawString("when you first unlocked", 683, 302);
                    this.rd.drawString("it.", 683, 320);
                    this.rd.drawString("Can you beat it again?", 683, 342);
                }
                if (this.customise[1][1]) {
                    this.rd.drawString("This changes the levels", 683, 226);
                    this.rd.drawString("of your opponents to", 683, 244);
                    this.rd.drawString("be around yours.", 683, 262);
                    this.rd.drawString("They cannot be higher", 683, 288);
                    this.rd.drawString("than a certain limit", 683, 306);
                    this.rd.drawString("though, depending on", 683, 324);
                    this.rd.drawString("your progress in game.", 683, 342);
                }
                if (this.customise[2][1]) {
                    this.rd.drawString("This puts all your", 683, 244);
                    this.rd.drawString("opponents at level 1.", 683, 262);
                    this.rd.drawString("This means they all have", 683, 280);
                    this.rd.drawString("their original stats.", 683, 298);
                    this.rd.drawString("Note that you don't get", 683, 324);
                    this.rd.drawString("any experience for this.", 683, 342);
                }
            }
            final Polygon feg2 = new Polygon();
            feg2.addPoint(343, 448);
            feg2.addPoint(350, 431);
            feg2.addPoint(520, 431);
            feg2.addPoint(527, 448);
            feg2.addPoint(520, 465);
            feg2.addPoint(350, 465);
            int x2 = 150;
            if (this.above[3]) {
                x2 = 250;
            }
            this.rd.setColor(new Color(255, 255, 255, x2));
            this.rd.fillPolygon(feg2);
            this.rd.setFont(this.adventure.deriveFont(1, 18.0f));
            this.ftm = this.rd.getFontMetrics();
            this.drawcs(456, "SCOUTING", 0, 0, 0, 3);
            final int levelwidth = this.ftm.stringWidth("level " + this.averagelevel);
            final int exclamwidth = this.ftm.stringWidth(new StringBuilder().append(exclamation).toString());
            final int totalwidth = levelwidth + exclamwidth;
            if (!this.stagefadephase) {
                this.stagefade -= 25;
            }
            else {
                this.stagefade += 25;
            }
            if (this.stagefade >= 255) {
                this.stagefade = 255;
                this.stagefadephase = false;
            }
            if (this.stagefade <= 0) {
                this.stagefade = 0;
                this.stagefadephase = true;
            }
            this.rd.setFont(this.adventure.deriveFont(1, 18.0f));
            this.ftm = this.rd.getFontMetrics();
            if (madness[0].level[this.sc[0]] >= this.averagelevel) {
                this.rd.setColor(new Color(0, 150, 0));
            }
            else {
                this.rd.setColor(new Color(150, 0, 0));
            }
            this.rd.drawString("level " + this.averagelevel, 435 - totalwidth / 2, 55);
            this.rd.setColor(new Color(200, 0, 0, this.stagefade));
            this.rd.drawString(new StringBuilder().append(exclamation).toString(), 435 - totalwidth / 2 + levelwidth, 55);
        }
        final Polygon twat = new Polygon();
        twat.addPoint(30, 426);
        twat.addPoint(38, 415);
        twat.addPoint(164, 415);
        twat.addPoint(169, 419);
        twat.addPoint(169, 433);
        twat.addPoint(164, 437);
        twat.addPoint(38, 437);
        int xy;
        if (!this.above[0]) {
            xy = 150;
        }
        else {
            xy = 250;
        }
        this.rd.setColor(new Color(200, 0, 0, xy));
        this.rd.fillPolygon(twat);
        this.rd.setFont(this.adventure.deriveFont(1, 13.0f));
        this.rd.setColor(new Color(255, 255, 255));
        this.rd.drawString("return to menu", 41, 431);
        final Polygon daler = new Polygon();
        daler.addPoint(30, 447);
        daler.addPoint(35, 443);
        daler.addPoint(164, 443);
        daler.addPoint(169, 447);
        daler.addPoint(169, 461);
        daler.addPoint(164, 465);
        daler.addPoint(35, 465);
        daler.addPoint(30, 461);
        int xza;
        if (!this.above[1]) {
            xza = 100;
        }
        else {
            xza = 250;
        }
        this.rd.setColor(new Color(0, 200, 0, xza));
        this.rd.fillPolygon(daler);
        this.rd.setColor(new Color(255, 255, 255));
        this.rd.drawString("change car", 57, 459);
        if (checkpoints.stage >= this.betalimit) {
            this.rd.setFont(this.adventure.deriveFont(1, 30.0f));
            this.ftm = this.rd.getFontMetrics();
            this.drawcs(230, "**END OF BETA**", 100, 0, 0, 3);
        }
        this.rd.setFont(new Font("SansSerif", 1, 13));
        this.ftm = this.rd.getFontMetrics();
        if ((control.enter || control.handb) && !this.above[0] && !this.above[1] && !this.above[2] && checkpoints.stage < this.betalimit) {
            if (!this.bonstage) {
                if (!this.dontdisplay) {
                    this.asay = "Stage " + checkpoints.stage + ":  " + checkpoints.name;
                }
                else {
                    this.asay = "Premier Tournament";
                }
            }
            else {
                for (int a = 0; a < 6; ++a) {
                    if (this.bonusstage[a]) {
                        this.asay = "Bonus Stage " + (a + 1) + ": " + checkpoints.name;
                    }
                }
            }
            this.dudo = 150;
            this.m.trk = false;
            this.m.focus_point = 400;
            this.fase = 5;
            control.handb = false;
            control.enter = false;
            this.stages.stop();
            this.stages.unloadMod();
        }
        int mxstage = this.unlocked[0];
        if (this.careermode) {
            mxstage = this.unlocked[1];
        }
        if (!this.bonstage && this.showopstage != 195) {
            if (control.right) {
                if (!this.classicmode) {
                    if (checkpoints.stage < 31) {
                        if (checkpoints.stage < mxstage) {
                            ++checkpoints.stage;
                            this.fase = 6476;
                            this.hardstage = false;
                            this.scalelevels = false;
                            this.nolevels = false;
                            control.right = false;
                        }
                        else {
                            this.fase = 4;
                            this.lockcnt = 100;
                            control.right = false;
                        }
                    }
                }
                else if (checkpoints.stage < 17) {
                    ++checkpoints.stage;
                    this.fase = 6476;
                    control.right = false;
                }
            }
            if (control.left && checkpoints.stage > 1) {
                --checkpoints.stage;
                this.fase = 6476;
                this.hardstage = false;
                this.scalelevels = false;
                this.nolevels = false;
                control.left = false;
            }
        }
    }
    
    public void fixbg() {
        this.app.repaint();
        this.rd.setColor(new Color(0, 0, 0));
        this.rd.fillRect(0, 0, 870, 480);
        this.fase = 201;
    }
    
    public void snap(final int i) {
        this.dmg = this.loadsnap(this.odmg);
        this.pwr = this.loadsnap(this.opwr);
        this.special = this.loadsnap(this.ospecial);
        this.was = this.loadsnap(this.owas);
        this.lap = this.loadsnap(this.olap);
        this.pos = this.loadsnap(this.opos);
        int j = 0;
        do {
            this.cntdn[j] = this.loadsnap(this.ocntdn[j]);
        } while (++j < 4);
        this.yourwasted = this.loadsnap(this.oyourwasted);
        this.youlost = this.loadsnap(this.oyoulost);
        this.youwon = this.loadsnap(this.oyouwon);
        this.youwastedem = this.loadsnap(this.oyouwastedem);
        this.gameh = this.loadsnap(this.ogameh);
        this.loadingmusic = this.loadopsnap(this.oloadingmusic, i, 76);
        this.star[0] = this.loadopsnap(this.ostar[0], i, 0);
        this.star[1] = this.loadopsnap(this.ostar[1], i, 0);
        this.flaot = this.loadopsnap(this.oflaot, i, 1);
    }
    
    private Image loadsnap(final Image image) {
        final int i = image.getHeight(this.ob);
        final int j = image.getWidth(this.ob);
        final int[] ai = new int[j * i];
        final PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, j, i, ai, 0, j);
        try {
            pixelgrabber.grabPixels();
        }
        catch (final InterruptedException ex) {}
        for (int k = 0; k < j * i; ++k) {
            if (ai[k] != -4144960 && ai[k] != ai[j * i - 1]) {
                final Color color = new Color(ai[k]);
                int l = (int)(color.getRed() + color.getRed() * (this.m.snap[0] / 100.0f));
                if (l > 225) {
                    l = 225;
                }
                if (l < 0) {
                    l = 0;
                }
                int i2 = (int)(color.getGreen() + color.getGreen() * (this.m.snap[1] / 100.0f));
                if (i2 > 225) {
                    i2 = 225;
                }
                if (i2 < 0) {
                    i2 = 0;
                }
                int j2 = (int)(color.getBlue() + color.getBlue() * (this.m.snap[2] / 100.0f));
                if (j2 > 225) {
                    j2 = 225;
                }
                if (j2 < 0) {
                    j2 = 0;
                }
                final Color color2 = new Color(l, i2, j2);
                ai[k] = color2.getRGB();
            }
            else if (ai[k] == -4144960) {
                final Color color3 = new Color(this.m.csky[0], this.m.csky[1], this.m.csky[2]);
                ai[k] = color3.getRGB();
            }
        }
        final Image image2 = this.createImage(new MemoryImageSource(j, i, ai, 0, j));
        return image2;
    }
    
    public void resetstat(final int i, final Madness[] madness) {
        if (this.replayphase == 0) {
            this.pieceglitch = 0;
        }
        this.arrace = false;
        this.sortedcars = false;
        this.ana = 0;
        this.cntan = 0;
        this.cntovn = 0;
        this.pglowchange = false;
        this.pglow = 0;
        this.wallimmunity = 100;
        this.wallcountdown = false;
        this.tcnt = 30;
        this.isithard = false;
        for (int a = 0; a < 6; ++a) {
            this.statchanges[a] = 0;
            for (int b = 0; b < 101; ++b) {
                this.statreduce[b][a] = 1.0;
            }
        }
        this.hitgain = 0;
        this.shownghost = false;
        this.ghosttimer = 0;
        this.ghostfade = 0;
        this.noexp = false;
        this.scareflash = false;
        this.turnbackon = false;
        this.scareflashtime = 0;
        this.stagefade = 255;
        this.stagefadephase = false;
        this.ghostattack = 0;
        this.ghosttele = false;
        this.ghostteletimer = 0;
        this.ghostattempt = 0;
        this.whatghostdo = 0;
        this.ghosthit = false;
        this.ghostfar = 0;
        this.ghostflashtimer = 0;
        this.randomtimes = 0;
        for (int a = 0; a < 3; ++a) {
            this.ghostflash[a] = false;
            for (int b = 0; b < 2; ++b) {
                this.customise[a][b] = false;
            }
        }
        this.playonce = false;
        this.stopped = false;
        this.wastingwin = false;
        this.diepls = 0;
        this.telewait = 0;
        this.glassfase = false;
        this.writetime = 0;
        this.shufflehover = false;
        this.resumed = false;
        for (int a = 0; a < 2; ++a) {
            this.killtime[a] = 0;
        }
        this.viewlimit[0] = 0;
        this.viewlimit[1] = 639;
        this.racingwin = false;
        this.losepoints = false;
        this.shwcnt = false;
        this.shwcncnt = false;
        this.triggerinst = false;
        this.nclicked = false;
        this.targetcar = 100;
        this.killchance[0] = 0;
        this.killchance[1] = 1001;
        this.winchance[0] = 0;
        this.winchance[1] = 1001;
        this.startexp = 0;
        this.teledelay = 0;
        this.teletimer = 0;
        this.telecooldown = 11;
        for (int a = 0; a < 1000; ++a) {
            this.randomtrans[a] = false;
            this.cancerpiece[a] = false;
        }
        this.startsp = 0;
        this.shkcnt = false;
        this.ungain = false;
        this.bossbattle = false;
        this.cstimer = 0;
        this.stunthealth = 0;
        this.verydark = false;
        this.crumble = false;
        this.fakehg = 0;
        this.justonce = false;
        this.absolutefuckingbullshit = false;
        this.combotime = 0;
        this.newtarget = false;
        this.sendwarning = false;
        this.generate = false;
        this.totallevel = 0;
        this.startfalling = 0;
        this.shkcncnt = false;
        for (int a = 0; a < 6; ++a) {
            this.xkcnt[a] = -50;
            this.showfor[a] = 0;
            this.hoverstat[a] = false;
            this.xmoveback[a] = false;
            this.xmove[a] = false;
            this.showboosts[a] = false;
        }
        if (this.justcs == -1) {
            this.savefase = 0;
            this.alreadystarted = false;
        }
        for (int a = 0; a < 2; ++a) {
            this.resetoption[a] = false;
            this.shuffleop[a] = false;
            this.shufophover[a] = false;
        }
        this.shufflefase = 0;
        this.ncarset = false;
        this.wasay = false;
        this.winfix = false;
        for (int a = 0; a < 7; ++a) {
            this.colorcode[a] = 0;
            this.stat[a] = 0;
            this.stopflashing[a] = false;
            this.stimulateclick[a] = false;
            this.fbar[a] = 0;
        }
        this.clear = 0;
        this.dmcnt = 0;
        this.leveluptimer = 0;
        this.levelfase = false;
        this.undeadtarget = 0;
        this.undeadswitch = 0;
        this.leveltrans = 0;
        this.pwcnt = 0;
        this.invulnerable = false;
        this.bchover = false;
        this.nhover = false;
        if (this.sc[0] < 23 && this.sc[0] != 20 && this.sc[0] != 21) {
            this.bclicked = false;
        }
        else {
            this.bclicked = true;
        }
        this.oclicked = false;
        this.tempinv = 0;
        this.auscnt = 45;
        this.pnext = 0;
        this.pback = 0;
        this.starcnt = 130;
        this.gocnt = 3;
        this.grrd = true;
        this.aird = true;
        this.loserace = false;
        this.setlevels = false;
        this.levelup = false;
        this.bfcrash = 0;
        this.arrowlocked = false;
        for (int a = 0; a < 15000; ++a) {
            this.norender[a] = false;
        }
        for (int a = 0; a < 101; ++a) {
            this.statdrain[a] = 0;
            this.undeadlock[a] = 0;
            this.sametime[a] = 0;
            this.floor[a] = 3;
            this.safezone[a] = false;
            this.spglow[a] = 0;
            this.spglowchange[a] = 0;
            for (int b = 0; b < 5; ++b) {
                this.condition[a][b] = false;
                for (int c = 0; c < 3; ++c) {
                    this.refcol[a][b][c] = -1;
                }
            }
            this.oldx[a] = 0;
            this.oldz[a] = 0;
            madness[a].frozen = false;
            this.nohit[a] = false;
            for (int c2 = 0; c2 < 4; ++c2) {
                this.finalfix[c2][a] = false;
            }
            for (int b = 0; b < 6; ++b) {
                this.statmod[a][b] = 100.0;
            }
            madness[a].strswap = false;
            this.sortstr[a] = 0;
            madness[a].redstr = false;
            this.randomcar[a] = a;
            this.dflashchange[a] = false;
            for (int b = 0; b < 2; ++b) {
                this.fixhealth[a][b] = false;
            }
            this.updatehealth[a] = false;
            this.proportion[a] = 0.0f;
            this.dmgflash[a] = 0;
            this.xzrot[a] = 0;
            this.halfhealth[a] = false;
            this.specpower[a] = 1.0;
            this.healthloss[a] = 0.0;
            this.countfall[a] = false;
            madness[a].leech = false;
            this.doitonce[a] = false;
            this.specialflag[a] = false;
            this.flash[a] = 0;
            this.beastflash[a] = false;
            this.revive[a] = false;
            this.glowphase[a] = false;
            this.fixspecials[a] = false;
            this.doitonce[a] = false;
            this.glowy[a] = false;
            this.wstm[a] = false;
            this.shadowtrans[a] = 0;
            this.newflame[a] = false;
            this.glow[a] = false;
            this.sortpos[a] = 0;
            this.positions[a] = 0;
            this.undead[a] = false;
            this.noarrow[a] = false;
            this.healthmulti[a] = 1.0f;
            this.glowg[a] = 0;
            this.glowg2[a] = 65;
            this.endsp[a] = 0;
            this.glitchtimer[a] = 0;
            this.destimer[a] = 0;
            this.entered[a] = false;
            this.crumblefail[a] = false;
            this.speedhack[a] = 0;
            this.lives[a] = 0;
            this.timesfallen[a] = 0;
        }
        this.cntwis = 0;
        for (int a = 0; a < 39; ++a) {
            this.rcestatgain[a] = false;
            this.wststatgain[a] = false;
        }
        this.bfskid = 0;
        this.pwait = 7;
        this.holdcnt = 0;
        this.holdit = false;
        this.winner = false;
        this.wasted = 0;
        int j = 0;
        do {
            this.dested[j] = 0;
        } while (++j < this.nplayers);
        for (int a2 = 1; a2 < this.nplayers; ++a2) {
            for (int b2 = 0; b2 < 39; ++b2) {
                madness[a2].aitssp[b2] = 0;
                madness[a2].aiaccsp[b2] = 0;
                madness[a2].aigripsp[b2] = 0;
                madness[a2].aistusp[b2] = 0;
                madness[a2].aistrsp[b2] = 0;
                madness[a2].aiendsp[b2] = 0;
            }
        }
        this.sortcars(i);
    }
    
    public void drawstat(final int i, int j, final boolean flag, final float f, final float spatk, final float speclast, final Control control) {
        final int[] ai = new int[4];
        final int[] ai2 = new int[4];
        if (flag) {
            ai[0] = 733;
            ai2[0] = 11;
            ai[1] = 733;
            ai2[1] = 19;
            ai[2] = 830;
            ai2[2] = 19;
            ai[3] = 830;
            ai2[3] = 11;
            this.rd.setColor(new Color(this.m.csky[0], this.m.csky[1], this.m.csky[2]));
            this.rd.fillPolygon(ai, ai2, 4);
        }
        if (j > i) {
            j = i;
        }
        final int k = (int)(98.0f * (j / (float)i));
        ai[0] = 732;
        ai2[0] = 11;
        ai[1] = 732;
        ai2[1] = 20;
        ai[2] = 732 + k;
        ai2[2] = 20;
        ai[3] = 732 + k;
        ai2[3] = 11;
        int l = 244;
        int i2 = 244;
        int j2 = 11;
        if (k > 33) {
            i2 = (int)(244.0f - 233.0f * ((k - 33) / 65.0f));
        }
        if (k > 70) {
            if (this.dmcnt < 10) {
                if (this.dmflk) {
                    i2 = 170;
                    this.dmflk = false;
                }
                else {
                    this.dmflk = true;
                }
            }
            ++this.dmcnt;
            if (this.dmcnt > 167.0 - k * 1.5) {
                this.dmcnt = 0;
            }
        }
        l += (l * (this.m.snap[0] / 100.0f));  // cast: bytecode-verified
        if (l > 255) {
            l = 255;
        }
        if (l < 0) {
            l = 0;
        }
        i2 += (i2 * (this.m.snap[1] / 100.0f));  // cast: bytecode-verified
        if (i2 > 255) {
            i2 = 255;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        j2 += (j2 * (this.m.snap[2] / 100.0f));  // cast: bytecode-verified
        if (j2 > 255) {
            j2 = 255;
        }
        if (j2 < 0) {
            j2 = 0;
        }
        this.rd.setColor(new Color(l, i2, j2));
        this.rd.fillPolygon(ai, ai2, 4);
        ai[0] = 732;
        ai2[0] = 31;
        ai[1] = 732;
        ai2[1] = 40;
        ai[2] = (int)(732.0f + f);
        ai2[2] = 40;
        ai[3] = (int)(732.0f + f);
        ai2[3] = 31;
        l = 128;
        if (f == 98.0f) {
            l = 64;
        }
        i2 = (int)(190.0 + f * 0.37);
        j2 = 244;
        if (this.auscnt < 45 && this.aflk) {
            l = 128;
            i2 = 244;
            j2 = 244;
        }
        l += (l * (this.m.snap[0] / 100.0f));  // cast: bytecode-verified
        if (l > 255) {
            l = 255;
        }
        if (l < 0) {
            l = 0;
        }
        i2 += (i2 * (this.m.snap[1] / 100.0f));  // cast: bytecode-verified
        if (i2 > 255) {
            i2 = 255;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        j2 += (j2 * (this.m.snap[2] / 100.0f));  // cast: bytecode-verified
        if (j2 > 255) {
            j2 = 255;
        }
        if (j2 < 0) {
            j2 = 0;
        }
        this.rd.setColor(new Color(l, i2, j2));
        this.rd.fillPolygon(ai, ai2, 4);
    }
    
    private Image bressed(final Image image) {
        final int i = image.getHeight(this.ob);
        final int j = image.getWidth(this.ob);
        final int[] ai = new int[j * i];
        final PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, j, i, ai, 0, j);
        try {
            pixelgrabber.grabPixels();
        }
        catch (final InterruptedException ex) {}
        final Color color = new Color(247, 255, 165);
        for (int k = 0; k < j * i; ++k) {
            if (ai[k] != ai[j * i - 1]) {
                ai[k] = color.getRGB();
            }
        }
        final Image image2 = this.createImage(new MemoryImageSource(j, i, ai, 0, j));
        return image2;
    }
    
    public void loading() {
        this.rd.setColor(new Color(0, 0, 0));
        this.rd.fillRect(0, 0, 870, 480);
        this.rd.setFont(this.adventure.deriveFont(1, 22.5f));
        this.ftm = this.rd.getFontMetrics();
        this.shload = (float)this.dnload;
        if (this.shload > this.kbload) {
            this.shload = (float)this.kbload;
        }
        this.loadcomplete = (int)(this.shload / this.kbload * 100.0f);
        if (this.loadcomplete < 100) {
            this.drawcs(225, "LOADING GAME DATA, PLEASE WAIT...", 255, 255, 255, 3);
            this.drawcs(255, this.loadcomplete + "% LOADED", 255, 255, 255, 3);
        }
        else {
            this.drawcs(240, "LOADING COMPLETE!", 255, 255, 255, 3);
        }
    }
    
    public xtGraphics(final Medium medium, final Graphics2D g, final Graphics h, final Applet applet) {
        this.nplayers = 11;
        this.xkcnt = new int[] { -50, -50, -50, -50, -50, -50 };
        this.xmove = new boolean[6];
        this.xmoveback = new boolean[6];
        this.glowphase = new boolean[101];
        this.glow = new boolean[101];
        this.glowy = new boolean[101];
        this.glowg = new int[101];
        this.showfor = new int[6];
        this.ptplayers = new String[] { "-", "Motion", "Redline", "Crash", "Swift", "Omega", "Olsie820", "RadicalRacer", "Kaffeinated", "InsanElite", "Grimjow" };
        this.ctplayers = new String[] { "-", "ACVoong", "WolfInABox", "Predator", "Physics", "NFMAddict", "A-Mile", "Radical24", "Shax700", "Victorious", "Prayers" };
        this.ltplayers = new String[] { "-", "Ultimato", "Champion", "Dexterity", "Steam", "Kodec", "Phyrexian", "Nitro", "Razor", "Demise", "Turbo" };
        this.hovering = new boolean[6];
        this.seethru = new int[6];
        this.killscn = new int[39];
        this.winscn = new int[39];
        this.unlocked = new int[] { 1, 1 };
        this.fullpownit = new double[2];
        this.fixspecials = new boolean[101];
        this.doitonce = new boolean[101];
        this.wstm = new boolean[101];
        this.strans = 255;
        this.xfade = 255;
        this.statpoints = new int[39];
        this.proba = new float[] { 0.5f, 0.5f, 0.4f, 0.3f, 0.3f, 0.4f, 0.3f, 0.3f, 0.3f, 0.1f, 0.1f, 0.5f, 0.1f, 0.0f, 0.0f, 0.0f, 0.0f, 0.1f, 0.1f, 0.5f, 0.85f, 0.85f, 0.0f, 0.5f, 0.5f, 0.4f, 0.3f, 0.5f, 0.4f, 0.3f, 0.3f, 0.3f, 0.1f, 0.1f, 0.5f, 0.1f, 0.0f, 0.0f, 0.0f };
        this.outdam = new float[] { 0.5f, 0.3f, 0.7f, 0.42f, 0.56f, 0.35f, 0.66f, 0.85f, 0.72f, 0.62f, 0.79f, 1.1f, 0.68f, 1.5f, 1.0f, 0.85f, 1.1f, 1.25f, 1.4f, 2.35f, 1.9f, 0.85f, 2.15f, 0.6f, 0.3f, 0.7f, 0.42f, 0.5f, 0.46f, 0.75f, 0.65f, 0.72f, 0.62f, 0.79f, 0.95f, 0.77f, 1.5f, 0.85f, 1.0f };
        this.powersave = new float[] { 0.4f, 0.4f, 0.75f, 0.4f, 0.81f, 0.4f, 0.68f, 0.68f, 0.49f, 0.94f, 0.49f, 0.88f, 0.75f, 1.0f, 0.59f, 0.94f, 0.75f, 0.94f, 1.0f, 1.0f, 1.0f, 0.94f, 1.0f, 0.4f, 0.4f, 0.75f, 0.4f, 0.81f, 0.4f, 0.68f, 0.68f, 0.49f, 0.94f, 0.49f, 0.88f, 0.75f, 1.0f, 0.59f, 0.94f };
        this.ptmatch = 1;
        this.totalsp = new int[101];
        this.bonuspoints = new int[101];
        this.okdale = new int[101];
        this.enginsignature = new int[] { 0, 1, 2, 1, 0, 3, 2, 2, 1, 0, 3, 4, 1, 4, 0, 3, 0, 1, 4, 0, 3, 1, 4, 0, 1, 2, 1, 0, 3, 2, 2, 1, 0, 3, 4, 1, 4, 0, 0 };
        this.chkamount = new int[2];
        this.wstamount = new int[2];
        this.names = new String[] { "Remington", "Speedy 7", "Damn Van", "Blizzard Rush", "Twingoor", "Steel Falcon", "Oldskool", "Revonater", "Comet", "Das Cop", "Hellfire", "Old Van", "Redspeed", "Stampede", "Skyrider", "DR Chaos", "Bounty Hunter", "Radical Racer", "Titan", "Deity", "Agent Waster", "Agent Racer", "Tesco Lorry", "Tornado Shark", "Formula 7", "Wow Caninaro", "La Vita Crab", "Nimi", "MAX Revenge", "Lead Oxide", "Kool Kat", "Drifter X", "Sword of Justice", "High Rider", "EL KING", "Mighty Eight", "M A S H E E N", "Radical One", "DR Monstaa" };
        this.adj = new String[][] { { "Decent", "Nice", "Impressive" }, { "Breathtaking", "Excellent", "Quality" }, { "Incredible", "World class", "Legendary" }, { "Beautiful, beautiful stunting.", "Absolutely sensational stunting!", "Simply magnificent." }, { "surf style", "off the ramp", "radical rebound" } };
        this.exlm = new String[] { "!", "!", "!" };
        this.sndsize = new int[] { 106, 76, 56, 116, 92, 208, 70, 80, 152, 102, 27, 65, 52, 30, 151, 129, 70, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100 };
        this.bgmy = new int[] { 0, 400 };
        this.trkx = new int[] { 100, 770 };
        this.hipno = new float[] { 1.0f, 1.0f, 3.0f, 1.0f, 1.2f, 1.0f, 1.7f, 1.0f, 1.0f, 8.0f, 1.5f, 2.0f, 1.2f, 10.0f, 1.8f, 1.4f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f };
        this.pgatx = new int[] { 246, 275, 315, 367, 434, 501, 552, 593, 621 };
        this.pgaty = new int[] { 208, 228, 241, 252, 259, 254, 243, 229, 211 };
        this.glowgtext = 60;
        this.initiate = new boolean[10];
        this.namephase = new boolean[10];
        this.nametrans = new int[10];
        this.inform = new int[2];
        this.noform = new int[2];
        this.pinform = new boolean[11];
        this.pnoform = new boolean[11];
        this.namehover = new boolean[4];
        this.butrans = new int[] { 115, 115, 115, 115 };
        this.stataffect = new double[101];
        this.ptscore1 = new int[11];
        this.orderscore = new int[11];
        this.ptscore1fase = new boolean[11];
        this.ptscore1fase2 = new boolean[11];
        this.position = new int[11];
        this.points = new int[11];
        this.glowb = 60;
        this.initialise = new boolean[11];
        this.initialise2 = new boolean[11];
        this.initialise3 = new boolean[11];
        this.sstrans = new int[11];
        this.sstransphase = new boolean[11];
        this.sstrans2 = new int[11];
        this.sstransphase2 = new boolean[11];
        this.sstrans3 = new int[11];
        this.sstransphase3 = new boolean[11];
        this.pointsscore = new int[11];
        this.ptmatchend = new boolean[10];
        this.eliminated = new boolean[11];
        this.ptimer = 1000;
        this.alhover = new boolean[7];
        this.arrowlock = new boolean[7];
        this.targetsq = new boolean[7];
        this.randomcar = new int[101];
        this.strswapee = new int[101];
        this.statcm = new boolean[6];
        this.slowonce = new boolean[101];
        this.newtimer = new boolean[5][101];
        this.timershown = new int[5][101];
        this.q = new int[5][101];
        this.over = new boolean[5][101];
        this.xm = new int[] { 100, 100, 100, 100, 100 };
        this.specpower = new double[101];
        this.drainrate = new double[101];
        this.finalfix = new boolean[4][101];
        this.correct = new boolean[101];
        this.glowg2 = new int[101];
        this.glowphase2 = new boolean[101];
        this.sortstr = new int[101];
        this.fixhealth = new boolean[101][2];
        this.above = new boolean[4];
        this.affected = new boolean[101];
        this.wallcode = new int[4];
        this.beastopponent = new boolean[101];
        this.beastcar = new int[101];
        this.bonusstage = new boolean[6];
        this.framesCount = 0;
        this.framesCountAvg = 0;
        this.framesTimer = 0L;
        this.extpoints = new int[39];
        this.atrans = 255;
        this.shadow = new int[101];
        this.shadowtrans = new int[101];
        this.lastop = 0;
        this.betalimit = 14;
        this.statsalc = new int[][] { { 0, 1, 5, 20, 27, 39 }, { 3, 8, 15, 25, 31, 36 }, { 4, 12, 18, 19, 26, 39 }, { 0, 6, 10, 13, 32, 38 }, { 2, 18, 20, 28, 33, 37 }, { 7, 11, 21, 23, 30, 34 }, { 9, 10, 14, 22, 24, 38 }, { 1, 2, 6, 8, 27, 36 }, { 0, 13, 16, 21, 30, 39 }, { 9, 12, 20, 23, 24, 32 }, { 1, 2, 15, 16, 31, 33 }, { 4, 5, 18, 19, 29, 34 }, { 3, 6, 16, 25, 36, 39 }, { 7, 14, 17, 26, 28, 32 }, { 0, 12, 19, 27, 30, 37 }, { 4, 8, 11, 17, 26, 35 }, { 7, 9, 19, 20, 23, 28 }, { 5, 13, 16, 21, 25, 37 }, { 4, 10, 14, 18, 22, 34 }, { 3, 11, 23, 24, 32, 33 }, { 4, 19, 26, 28, 29, 32 }, { 3, 8, 10, 31, 33, 38 }, { 8, 14, 23, 26, 29, 36 }, new int[6], new int[6], new int[6], new int[6], new int[6], new int[6], new int[6], new int[6], new int[6], { 1, 2, 9, 12, 22, 30 }, { 6, 10, 13, 15, 25, 37 }, { 5, 11, 15, 27, 29, 34 }, { 21, 30, 31, 33, 36, 39 }, { 7, 16, 17, 20, 28, 38 }, { 0, 3, 13, 25, 27, 37 }, { 17, 18, 22, 24, 34, 35 } };
        this.statnames = new String[] { "ENERGY", "GAMBLER", "GREED", "GETAWAY", "CHEAPSHOT", "ESCAPE", "FEARLESS", "PUSHING", "RESISTANCE", "RECKLESS", "SURVIVAL", "RAMPAGE", "BRAVERY", "AWARENESS", "BACKHIT", "ARMOUR", "WEIGHT", "LIFTING", "DRAINER", "LEAKAGE", "RUTHLESS", "FRESHNESS", "STEROIDS", "BERSERK", "SAFETY", "RECOVERY", "BLEED", "TURNING", "DEBUFF", "LEECH", "CHARGING", "REFLECT", "FREEZE", "SAVIOUR", "UNDEAD", "GROUNDED", "GRAVITY", "COMEBACK", "HEALING", "STABILITY" };
        this.healthloss = new double[101];
        this.customise = new boolean[3][2];
        this.maxlevel = new int[] { 4, 7, 10, 13, 16, 20, 23, 26, 30, 33, 36, 39, 42, 45, 49, 52, 55, 58, 62, 65, 70, 74, 77, 84, 90, 97, 103, 111, 120, 125, 125 };
        this.updatehealth = new boolean[101];
        this.makebot = false;
        this.viewbot = false;
        this.safezone = new boolean[101];
        this.norender = new boolean[15000];
        this.statchangers = new int[2];
        this.noexp = false;
        this.rollonce = false;
        this.chance = 0.0f;
        this.wallimmunity = 100;
        this.wallcountdown = false;
        this.isithard = false;
        this.actions = new int[2];
        this.floor = new int[101];
        this.viewlimit = new int[2];
        this.xbspratio = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.25, 1.25, 1.5, 1.5, 1.75, 1.75, 2.0, 2.0, 3.0, 3.5, 4.0, 4.5, 4.0, 4.0, 5.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 2.0, 2.0, 2.25, 2.25, 2.5, 2.75, 2.75 };
        this.rebsp = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };
        this.xbsp = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
        this.songname = new String[] { "Master Blaster Area 1 Remix", "Across the Plains", "Moonview Highway Eurobeat Remix", "Ninja Gaiden Unbreakable Determination", "Onward", "Distortion World Remix", "Undertale (Another Medium Remix)", "Studiopolis Act 2 (Sonic Mania) Remix", "F-Zero Fire Field Remix", "Battlefield SSBM Remastered", "Biolizard Theme Instrumental", "Crumbling Laboratory Round 1 & 2 Mashup", "Airborne Energizer", "Donkey Kong 2 (Forest Interlude) Remake", "Lunar Fractals", "Ice Cap Remix", "Mystic Cave Remix", "Duneriders", "Techno Base Classic Remix", "Something", "Chaos Angel Act 2 Remix", "Something", "Sonic Rush Adventure (Haunted Ship) Classic Remix", "Ocean Base Remix", "Something", "Something", "Something", "Something", "Something", "Something", "Something" };
        this.artistname = new String[] { "CobaltBW", "Vindsvept", "DT Music and Gaming Channel", "Neon X Game Remixes", "Vindsvept", "PokeRemixStudio", "teckwerks", "Jahn Davis", "Vagrant", "Funky Grip", "WildSangrita", "Cobretti Gaming Network", "Jahn Davis", "Nickolay Kunev", "Waterflame", "NicoCW", "NicoCW", "Waterflame", "NicoCW", "Someone", "bridgecapper227", "Someone", "NicoCW", "TheWhiteSnowOne", "Someone", "Someone", "Someone", "Someone", "Someone", "Someone", "Someone" };
        this.fase = 111;
        this.oldfase = 0;
        this.sortedcars = false;
        this.specialflag = new boolean[101];
        this.starcnt = 0;
        this.xzrot = new int[101];
        this.carqhover = new boolean[7];
        this.transfercar = new int[2];
        this.nclicked = false;
        this.scareflash = false;
        this.turnbackon = false;
        this.averagelevel = 0;
        this.replayfade = false;
        this.replaytrans = 0;
        this.replaydisable = false;
        this.pglowchange = false;
        this.xpbuttonhover = false;
        this.disablexp = false;
        this.pglow = 0;
        this.startinglevel = 1;
        this.replayoption = 1;
        this.expmult = 1.0;
        this.powxpadjust = 0;
        this.scareflashtime = 0;
        this.lockcnt = 0;
        this.undeadlock = new int[101];
        this.statdrain = new int[101];
        this.proportion = new float[101];
        this.spglow = new int[101];
        this.statreduce = new double[101][6];
        this.spglowchange = new int[101];
        this.refcol = new int[101][5][3];
        this.sametime = new int[101];
        this.condition = new boolean[101][5];
        this.statmod = new double[101][6];
        this.shownghost = false;
        this.ghosttimer = 0;
        this.ghostfade = 0;
        this.statchanges = new int[6];
        this.ghosthit = false;
        this.ghostflashtimer = 0;
        this.ghostattack = 0;
        this.stagefade = 255;
        this.scalelevels = false;
        this.nolevels = false;
        this.stagefadephase = false;
        this.ghostattempt = 0;
        this.ghosttele = false;
        this.ghostteletimer = 0;
        this.ghostfar = 0;
        this.whatghostdo = 0;
        this.randomtimes = 0;
        this.alreadystarted = false;
        this.diepls = 0;
        this.glassfase = false;
        this.findi = new int[101];
        this.playonce = false;
        this.revive = new boolean[101];
        this.ghostflash = new boolean[3];
        this.dflashchange = new boolean[101];
        this.oldx = new int[101];
        this.oldz = new int[101];
        this.dmgflash = new int[101];
        this.opselect = new int[2];
        this.killtime = new int[2];
        this.stimulateclick = new boolean[7];
        this.statgain = 0;
        this.resumed = false;
        this.stopped = false;
        this.writetime = 0;
        this.telewait = 0;
        this.pieceglitch = 0;
        this.teledelay = 0;
        this.teletimer = 0;
        this.shufophover = new boolean[2];
        this.realunlocked = new int[2];
        this.randomtrans = new boolean[1000];
        this.cancerpiece = new boolean[1000];
        this.specialstats = new int[39][40][6];
        this.shaded = false;
        this.startfalling = 0;
        this.crumble = false;
        this.losepoints = false;
        this.justcs = -1;
        this.bgfade = 0;
        this.targetcar = 100;
        this.shufflehover = false;
        this.showboosts = new boolean[6];
        this.verydark = false;
        this.triggerinst = false;
        this.musicswitch = 0L;
        this.cstimer = 0;
        this.stunthealth = 0;
        this.startexp = 0;
        this.hardstage = false;
        this.bossbattle = false;
        this.nofile = false;
        this.timesfallen = new int[101];
        this.telecooldown = 11;
        this.speedhack = new int[101];
        this.destimer = new int[101];
        this.startsp = 0;
        this.entered = new boolean[101];
        this.hoverstat = new boolean[6];
        this.nohit = new boolean[101];
        this.healthmulti = new float[101];
        this.crumblefail = new boolean[101];
        this.countfall = new boolean[101];
        this.flipo = 0;
        this.duration = 0L;
        this.elapsed = 0L;
        this.pausetime = 0L;
        this.combotime = 0;
        this.spintime = new int[50];
        this.origposx = new int[50];
        this.origposz = new int[50];
        this.origposxz = new int[50];
        this.alldone = false;
        this.lives = new int[101];
        this.glitchtimer = new int[101];
        this.sendwarning = false;
        this.generate = false;
        this.nextc = false;
        this.undeadtarget = 0;
        this.halfhealth = new boolean[101];
        this.endsp = new int[101];
        this.undeadswitch = 0;
        this.ungain = false;
        this.piecespin = new boolean[50];
        this.ssdone = false;
        this.fakehg = 0;
        this.totallevel = 0;
        this.justonce = false;
        this.sortpos = new int[101];
        this.positions = new int[101];
        this.gatey = 0;
        this.newflame = new boolean[101];
        this.looped = 1;
        this.sc = new int[101];
        this.absolutefuckingbullshit = false;
        this.noshadows = 0;
        this.boncomp = new int[6];
        this.holdit = false;
        this.stopflashing = new boolean[7];
        this.resetoption = new boolean[2];
        this.shuffleop = new boolean[2];
        this.shufflefase = 0;
        this.holdcnt = 0;
        this.newtarget = false;
        this.loserace = false;
        this.winner = false;
        this.colorcode = new int[7];
        this.fbar = new int[7];
        this.stat = new int[7];
        this.ncarset = false;
        this.resetfase = false;
        this.flexpix = new int[268000];
        this.smokey = new int[268000];
        this.flatrstart = 0;
        this.bclicked = false;
        this.nhover = false;
        this.runtyp = 0;
        this.averagelevel = 0;
        this.trackbg = new Image[2][2];
        this.dude = new Image[3];
        this.dudeb = new Image[3];
        this.duds = 0;
        this.dudo = 0;
        this.winchance = new int[2];
        this.killchance = new int[2];
        this.statstext = new String[39];
        this.next = new Image[2];
        this.back = new Image[2];
        this.contin = new Image[2];
        this.ostar = new Image[2];
        this.star = new Image[3];
        this.pcontin = 0;
        this.pnext = 0;
        this.undead = new boolean[101];
        this.noarrow = new boolean[101];
        this.pback = 0;
        this.bchover = false;
        this.wststatgain = new boolean[39];
        this.rcestatgain = new boolean[39];
        this.pstar = 0;
        this.ocntdn = new Image[4];
        this.cntdn = new Image[4];
        this.gocnt = 0;
        this.engs = new AudioClip[5][5];
        this.pengs = new boolean[5];
        this.air = new AudioClip[6];
        this.aird = false;
        this.grrd = false;
        this.crash = new AudioClip[3];
        this.lowcrash = new AudioClip[3];
        this.pwastd = false;
        this.skid = new AudioClip[3];
        this.dustskid = new AudioClip[3];
        this.mutes = false;
        this.stracks = new RadicalMod[200];
        this.loadedt = new boolean[200];
        this.mtracks = new RadicalMidi[200];
        this.isMidi = new boolean[200];
        this.isOgg = new boolean[200];
        this.lastload = -1;
        this.mutem = false;
        this.sunny = false;
        this.macn = false;
        this.arrace = false;
        this.ana = 0;
        this.cntan = 0;
        this.cntovn = 0;
        this.flk = false;
        this.tcnt = 30;
        this.beastflash = new boolean[101];
        this.flash = new int[101];
        this.tflk = false;
        this.say = "";
        this.wasay = false;
        this.clear = 0;
        this.posit = 0;
        this.wasted = 0;
        this.laps = 0;
        this.dested = new int[101];
        this.dmcnt = 0;
        this.dmflk = false;
        this.pwcnt = 0;
        this.pwflk = false;
        this.wastingwin = false;
        this.racingwin = false;
        this.loop = "";
        this.spin = "";
        this.asay = "";
        this.auscnt = 45;
        this.aflk = false;
        this.kbload = 0;
        this.dnload = 0;
        this.shload = 0.0f;
        this.radpx = 147;
        this.pin = 60;
        this.trkl = 0;
        this.oclicked = false;
        this.trklim = (int)(Math.random() * 40.0);
        this.pgady = new int[9];
        this.pgas = new boolean[9];
        this.lxm = -10;
        this.lym = -10;
        this.pwait = 7;
        this.stopcnt = 0;
        this.cntwis = 0;
        this.crshturn = 0;
        this.bfcrash = 0;
        this.bfskid = 0;
        this.crashup = false;
        this.skidup = false;
        this.skflg = 0;
        this.dskflg = 0;
        this.flatr = 0;
        this.flyr = 0;
        this.flyrdest = 0;
        this.flang = 0;
        this.flangados = 0;
        this.blackn = 0.0f;
        this.blacknados = 0.0f;
        this.m = medium;
        this.app = applet;
        this.rd = g;
        this.sg = h;
        try {
            this.adventure = Font.createFont(0, xtGraphics.class.getResourceAsStream("Adventure.ttf"));
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
        try {
            this.fifa = Font.createFont(0, xtGraphics.class.getResourceAsStream("fifawelcome1.3.ttf"));
        }
        catch (final Exception ex) {
            ex.printStackTrace();
        }
        int i = 0;
        do {
            this.loadedt[i] = false;
            this.isMidi[i] = false;
            this.isOgg[i] = false;
        } while (++i < 200);
    }
    
    public void resetmaini(final Madness[] madness) {
        if (this.replayphase == 0) {
            this.pieceglitch = 0;
            for (int a = 0; a < 50; ++a) {
                this.piecespin[a] = false;
                this.spintime[a] = 0;
                this.origposx[a] = 0;
                this.origposxz[a] = 0;
                this.origposz[a] = 0;
            }
            for (int a = 0; a < 6; ++a) {
                this.bonusstage[a] = false;
            }
            this.musicswitch = 1L;
            this.duration = 0L;
            this.pausetime = 0L;
            this.elapsed = 0L;
            this.scalelevels = false;
            this.nolevels = false;
            this.hardstage = false;
            this.bonstage = false;
            this.noshadows = 0;
        }
        this.minitimer = 0;
        for (int a = 0; a < 2; ++a) {
            this.actions[a] = 0;
        }
        this.viewlimit[0] = 0;
        this.viewlimit[1] = 639;
        this.stopped = false;
        this.pglowchange = false;
        this.pglow = 0;
        this.replaydisable = false;
        this.softlevelcap = 1;
        this.teledelay = 0;
        this.startinglevel = 1;
        this.expmult = 1.0;
        this.powxpadjust = 0;
        this.replayoption = 1;
        this.scareflash = false;
        this.wallimmunity = 100;
        this.wallcountdown = false;
        this.turnbackon = false;
        this.isithard = false;
        this.scareflashtime = 0;
        for (int a = 0; a < 6; ++a) {
            for (int b = 0; b < 101; ++b) {
                this.statreduce[b][a] = 1.0;
            }
            this.statchanges[a] = 0;
        }
        this.teletimer = 0;
        this.diepls = 0;
        this.ghosthit = false;
        this.shownghost = false;
        this.ghosttimer = 0;
        this.rollonce = false;
        this.chance = 0.0f;
        this.ghostteletimer = 0;
        this.noexp = false;
        this.ghosttele = false;
        this.ghostfade = 0;
        this.ghostattempt = 0;
        this.whatghostdo = 0;
        this.ghostfar = 0;
        this.ghostattack = 0;
        this.ghostflashtimer = 0;
        this.stagefade = 255;
        this.stagefadephase = false;
        this.triggerinst = false;
        this.writetime = 0;
        this.glassfase = false;
        this.telewait = 0;
        this.nclicked = false;
        this.resumed = false;
        this.losepoints = false;
        this.playonce = false;
        for (int a = 0; a < 3; ++a) {
            this.ghostflash[a] = false;
            for (int b = 0; b < 2; ++b) {
                this.customise[a][b] = false;
            }
        }
        this.randomtimes = 0;
        for (int a = 0; a < 1000; ++a) {
            this.cancerpiece[a] = false;
            this.randomtrans[a] = false;
        }
        for (int a = 0; a < 15000; ++a) {
            this.norender[a] = false;
        }
        for (int a = 0; a < 101; ++a) {
            this.undeadlock[a] = 0;
            this.floor[a] = 3;
            this.statdrain[a] = 0;
            this.sametime[a] = 0;
            this.safezone[a] = false;
            this.spglow[a] = 0;
            this.spglowchange[a] = 0;
            for (int b = 0; b < 5; ++b) {
                this.condition[a][b] = false;
                for (int c = 0; c < 3; ++c) {
                    this.refcol[a][b][c] = -1;
                }
            }
            for (int b = 0; b < 6; ++b) {
                this.statmod[a][b] = 100.0;
            }
            for (int b = 0; b < 4; ++b) {
                this.lives[a] = 0;
            }
            this.oldx[a] = 0;
            this.oldz[a] = 0;
            madness[a].spatk = 0.0f;
            this.specialflag[a] = false;
            this.dflashchange[a] = false;
            madness[a].speclast = 120.0f;
            madness[a].speclast2 = 120.0f;
            madness[a].specialact = false;
            this.fixspecials[a] = false;
            this.shadowtrans[a] = 0;
            this.dmgflash[a] = 0;
            this.shadow[a] = 0;
            this.xzrot[a] = 0;
            this.undead[a] = false;
            this.sortpos[a] = 0;
            this.specpower[a] = 1.0;
            this.healthloss[a] = 0.0;
            this.glitchtimer[a] = 0;
            this.positions[a] = 0;
            this.halfhealth[a] = false;
            this.healthmulti[a] = 1.0f;
            this.countfall[a] = false;
            this.noarrow[a] = false;
            this.crumblefail[a] = false;
            this.endsp[a] = 0;
            this.revive[a] = false;
            this.newflame[a] = false;
            this.entered[a] = false;
            this.speedhack[a] = 0;
            this.timesfallen[a] = 0;
            this.destimer[a] = 0;
        }
        for (int a = 0; a < 11; ++a) {
            this.ptscore1[a] = 0;
        }
        this.verydark = false;
        this.bossbattle = false;
        this.cstimer = 0;
        this.stunthealth = 0;
        this.ssdone = false;
        this.telecooldown = 11;
        this.targetcar = 100;
        this.startfalling = 0;
        this.crumble = false;
        this.winner = false;
        this.holdit = false;
        this.startexp = 0;
        this.startsp = 0;
        this.statgain = 0;
        this.generate = false;
        this.sendwarning = false;
        this.totallevel = 0;
        this.combotime = 0;
        for (int a = 0; a < 2; ++a) {
            this.killtime[a] = 0;
        }
        this.nhover = false;
        this.oclicked = false;
        this.wastingwin = false;
        this.newtarget = false;
        this.absolutefuckingbullshit = false;
        this.undeadtarget = 0;
        this.undeadswitch = 0;
        this.hitgain = 0;
        this.ungain = false;
        this.alldone = false;
        this.fakehg = 0;
        this.justonce = false;
        this.m.showsnow = false;
        this.racingwin = false;
        for (int a = 0; a < 7; ++a) {
            this.colorcode[a] = 0;
            this.stat[a] = 0;
            this.fbar[a] = 0;
            this.stopflashing[a] = false;
            this.stimulateclick[a] = false;
        }
        this.loserace = false;
        if (this.sc[0] < 23 && this.sc[0] != 20 && this.sc[0] != 21) {
            this.bclicked = false;
        }
        else {
            this.bclicked = true;
        }
        this.unlimitedlaps = false;
        this.setlevels = false;
        this.bchover = false;
        this.ncarset = false;
        for (int a = 0; a < 6; ++a) {
            this.showboosts[a] = false;
            this.hoverstat[a] = false;
        }
    }
    
    public void maini(final Control control, final CheckPoints checkpoints, final Madness madness) {
        if (!control.mutem) {
            if (!this.alreadystarted) {
                this.menu.play();
            }
            else {
                this.menu.resume();
            }
        }
        else {
            this.alreadystarted = true;
            this.menu.stop();
        }
        this.ktch = false;
        this.tomaini = false;
        this.ptmatch = 1;
        if (this.justcs < 6) {
            this.bgfade = 255;
        }
        else if (this.bgfade > 170) {
            this.bgfade -= 5;
        }
        this.rd.setColor(new Color(0, 0, 0, this.bgfade));
        this.rd.fillRect(0, 0, 870, 480);
        if (this.lastload >= 0 && this.loadedt[this.lastload]) {
            if (this.isMidi[this.lastload]) {
                this.mtracks[this.lastload].unload();
            }
            else {
                this.stracks[this.lastload].unloadMod();
            }
        }
        final Polygon dale = new Polygon();
        dale.addPoint(15, 190);
        dale.addPoint(23, 210);
        dale.addPoint(205, 210);
        dale.addPoint(213, 190);
        dale.addPoint(205, 170);
        dale.addPoint(23, 170);
        this.rd.setColor(new Color(0, 0, 0, this.seethru[0]));
        this.rd.fillPolygon(dale);
        final Polygon dale2 = new Polygon();
        dale2.addPoint(15, 240);
        dale2.addPoint(23, 260);
        dale2.addPoint(205, 260);
        dale2.addPoint(213, 240);
        dale2.addPoint(205, 220);
        dale2.addPoint(23, 220);
        this.rd.setColor(new Color(0, 0, 0, this.seethru[1]));
        this.rd.fillPolygon(dale2);
        final Polygon dale3 = new Polygon();
        dale3.addPoint(15, 290);
        dale3.addPoint(23, 310);
        dale3.addPoint(205, 310);
        dale3.addPoint(213, 290);
        dale3.addPoint(205, 270);
        dale3.addPoint(23, 270);
        this.rd.setColor(new Color(0, 0, 0, this.seethru[2]));
        this.rd.fillPolygon(dale3);
        final Polygon dale4 = new Polygon();
        dale4.addPoint(15, 340);
        dale4.addPoint(23, 360);
        dale4.addPoint(205, 360);
        dale4.addPoint(213, 340);
        dale4.addPoint(205, 320);
        dale4.addPoint(23, 320);
        this.rd.setColor(new Color(0, 0, 0, this.seethru[3]));
        this.rd.fillPolygon(dale4);
        final Polygon dale5 = new Polygon();
        dale5.addPoint(15, 390);
        dale5.addPoint(23, 410);
        dale5.addPoint(205, 410);
        dale5.addPoint(213, 390);
        dale5.addPoint(205, 370);
        dale5.addPoint(23, 370);
        this.rd.setColor(new Color(0, 0, 0, this.seethru[4]));
        this.rd.fillPolygon(dale5);
        final Polygon dale6 = new Polygon();
        dale6.addPoint(15, 440);
        dale6.addPoint(23, 460);
        dale6.addPoint(205, 460);
        dale6.addPoint(213, 440);
        dale6.addPoint(205, 420);
        dale6.addPoint(23, 420);
        this.rd.setColor(new Color(0, 0, 0, this.seethru[5]));
        this.rd.fillPolygon(dale6);
        this.rd.setColor(new Color(255, 255, 255));
        this.rd.setFont(this.adventure.deriveFont(1, 14.0f));
        this.rd.drawString("UNAVAILABLE!", 58, 196);
        this.rd.drawString("RPG MODE", 70, 246);
        this.rd.drawString("CLASSIC MODE", 56, 296);
        if (this.savefase == 0 || this.justcs < 0) {
            this.rd.drawString("SAVE PROGRESS", 56, 346);
        }
        if (this.savefase == 1 && this.justcs >= 0) {
            this.rd.drawString("SAVING...", 56, 346);
        }
        if (this.savefase == 2 && this.justcs >= 0) {
            this.rd.drawString("SAVED!", 87, 346);
        }
        this.rd.drawString("INSTRUCTIONS", 56, 396);
        this.rd.drawString("CREDITS", 80, 446);
        if (this.opselect[1] == 0) {
            this.seethru[0] = 255;
            for (int a = 1; a < 6; ++a) {
                this.seethru[a] = 115;
            }
        }
        if (this.opselect[1] == 1) {
            this.seethru[0] = 115;
            this.seethru[1] = 255;
            for (int a = 2; a < 6; ++a) {
                this.seethru[a] = 115;
            }
        }
        if (this.opselect[1] == 2) {
            this.seethru[0] = 115;
            this.seethru[1] = 115;
            this.seethru[2] = 255;
            for (int a = 3; a < 6; ++a) {
                this.seethru[a] = 115;
            }
        }
        if (this.opselect[1] == 3) {
            this.seethru[0] = 115;
            this.seethru[1] = 115;
            this.seethru[2] = 115;
            this.seethru[3] = 255;
            for (int a = 4; a < 6; ++a) {
                this.seethru[a] = 115;
            }
        }
        if (this.opselect[1] == 4) {
            this.seethru[4] = 255;
            this.seethru[5] = 115;
            for (int a = 0; a < 4; ++a) {
                this.seethru[a] = 115;
            }
        }
        if (this.opselect[1] == 5) {
            this.seethru[5] = 255;
            for (int a = 0; a < 5; ++a) {
                this.seethru[a] = 115;
            }
        }
        if (control.downalt && !this.inst && !this.cred) {
            final int[] opselect = this.opselect;
            final int n = 1;
            ++opselect[n];
            if (this.opselect[1] == 6) {
                this.opselect[1] = 0;
            }
            control.downalt = false;
        }
        if (control.upalt && !this.inst && !this.cred) {
            final int[] opselect2 = this.opselect;
            final int n2 = 1;
            --opselect2[n2];
            if (this.opselect[1] == -1) {
                this.opselect[1] = 5;
            }
            control.upalt = false;
        }
        if ((control.handbalt || control.enteralt) && !this.inst && !this.cred && this.justcs >= 0) {
            if (this.opselect[1] == 0) {
                final int n3 = this.sc[0];
            }
            if (this.opselect[1] == 1) {
                this.menu.stop();
                this.careermode = true;
                this.savefase = 0;
                this.justcs = -1;
                this.sc[0] = this.lastcar;
                this.classicmode = false;
                this.resetfase = false;
                checkpoints.stage = this.laststage;
                this.flipo = 0;
                this.lastop = 1;
                this.fase = -9;
            }
            if (this.opselect[1] == 2) {
                this.careermode = false;
                this.classicmode = true;
                this.justcs = -1;
                if (this.lastcar < 23) {
                    this.lastcar = 38;
                }
                this.sc[0] = this.lastcar;
                this.menu.stop();
                this.resetfase = false;
                checkpoints.stage = this.laststage;
                this.flipo = 0;
                this.lastop = 2;
                this.fase = -9;
            }
            if (this.opselect[1] == 3 && !this.resetfase) {
                this.savefase = 1;
                this.resetfase = true;
            }
            if (this.opselect[1] == 4) {
                this.inst = true;
            }
            if (this.opselect[1] == 5) {
                this.cred = true;
            }
            control.handbalt = false;
            control.enteralt = false;
            control.handb = false;
            control.enter = false;
            control.right = false;
            control.left = false;
        }
        if (this.inst || this.cred) {
            final Polygon bigbox = new Polygon();
            bigbox.addPoint(273, 150);
            bigbox.addPoint(280, 143);
            bigbox.addPoint(683, 143);
            bigbox.addPoint(690, 150);
            bigbox.addPoint(690, 453);
            bigbox.addPoint(683, 460);
            bigbox.addPoint(280, 460);
            bigbox.addPoint(273, 453);
            this.rd.setColor(new Color(0, 0, 0, 115));
            this.rd.fillPolygon(bigbox);
            if (control.enteralt || control.handbalt) {
                if ((this.cred && this.flipo == 2) || (this.inst && this.flipo == 9)) {
                    this.inst = false;
                    this.cred = false;
                    this.flipo = 0;
                }
                control.enteralt = false;
                control.handbalt = false;
                control.enter = false;
                control.handb = false;
            }
            if (control.leftalt) {
                if (this.flipo > 0) {
                    --this.flipo;
                }
                control.leftalt = false;
            }
            int rightlimit = 2;
            if (this.inst) {
                rightlimit = 9;
            }
            if (control.rightalt) {
                if (this.flipo < rightlimit) {
                    ++this.flipo;
                }
                control.rightalt = false;
            }
        }
        if (this.inst) {
            this.displayinst();
        }
        if (this.cred) {
            if (this.flipo == 0) {
                this.rd.setColor(new Color(255, 255, 255));
                this.rd.setFont(this.adventure.deriveFont(1, 22.0f));
                this.ftm = this.rd.getFontMetrics();
                this.rd.drawString("car creators:", 481 - this.ftm.stringWidth("car creators:") / 2, 168);
                this.rd.drawString("stage creators:", 481 - this.ftm.stringWidth("stage creators:") / 2, 350);
                this.rd.setFont(this.adventure.deriveFont(1, 14.5f));
                this.ftm = this.rd.getFontMetrics();
                this.rd.drawString("Tails, DJ Miker, Mezzelo, Phyrexian,", 300, 200);
                this.rd.drawString("Trelivision, Chaotic, Afterburn, Excalibur,", 300, 225);
                this.rd.drawString("Vitalogy, Ultimato, Rulue, Tunari, Toazuka,", 300, 250);
                this.rd.drawString("GX, projectDUB, RAD1 and KingOfSpeed.", 300, 275);
                this.rd.drawString("All Classic mode cars by Omar Waly.", 300, 310);
                this.rd.drawString("All other stages by Ten Graves.", 300, 407);
                this.rd.drawString("All Classic mode stages by Omar Waly.", 300, 382);
                this.rd.drawImage(this.next[this.pnext], 590, 422, null);
            }
            if (this.flipo == 1) {
                this.rd.setColor(new Color(255, 255, 255));
                this.rd.setFont(this.adventure.deriveFont(1, 22.0f));
                this.ftm = this.rd.getFontMetrics();
                this.rd.drawString("music:", 481 - this.ftm.stringWidth("music:") / 2, 168);
                this.rd.setFont(this.adventure.deriveFont(1, 14.5f));
                this.ftm = this.rd.getFontMetrics();
                this.rd.drawString("rpg mode stages 18, and 23 have been", 300, 200);
                this.rd.drawString("produced by SEGA. Some other music", 300, 225);
                this.rd.drawString("tracks are remixes of music by SEGA.", 300, 250);
                this.rd.drawString("rpg mode stages 16, 17, 19, 20, 21,", 300, 300);
                this.rd.drawString("22 and 23b are remixes by NicoCW,", 300, 325);
                this.rd.drawString("teckworks, bridgecapper227, Kamex", 300, 350);
                this.rd.drawString("and DJ FACT.50 of Vernian Process", 300, 375);
                this.rd.drawString("(check out their youtube accounts!).", 300, 400);
                this.rd.drawImage(this.next[this.pnext], 590, 422, null);
                this.rd.drawImage(this.back[this.pback], 319, 422, null);
            }
            if (this.flipo == 2) {
                this.rd.setColor(new Color(255, 255, 255));
                this.rd.setFont(this.adventure.deriveFont(1, 22.0f));
                this.ftm = this.rd.getFontMetrics();
                this.rd.drawString("programming:", 481 - this.ftm.stringWidth("programming:") / 2, 213);
                this.rd.setFont(this.adventure.deriveFont(1, 14.5f));
                this.ftm = this.rd.getFontMetrics();
                this.rd.drawString("All other music is from modarchive.org.", 300, 170);
                this.rd.drawString("The original game and concept of Need", 300, 245);
                this.rd.drawString("for Madness is by Omar Waly of", 300, 270);
                this.rd.drawString("radicalplay.com.", 300, 295);
                this.rd.drawString("This game was modded by Ten Graves, with", 300, 345);
                this.rd.drawString("guidance from DragShot, rafa1231518, and", 300, 370);
                this.rd.drawString("Kaffeinated.", 300, 395);
                this.rd.drawImage(this.back[this.pback], 319, 422, null);
                this.rd.drawImage(this.contin[this.pcontin], 437, 420, null);
            }
        }
        this.rd.setFont(new Font("Arial", 1, 11));
        this.ftm = this.rd.getFontMetrics();
    }
    
    public void areyousure(final Control control, final Madness madness) {
        if (this.flipo == 0) {
            this.bgmy[0] = 0;
            this.bgmy[1] = 400;
            this.app.setCursor(new Cursor(0));
        }
        this.rd.setColor(new Color(0, 0, 0));
        this.rd.fillRect(0, 0, 100, 480);
        this.rd.fillRect(770, 0, 100, 480);
        this.rd.fillRect(100, 0, 670, 40);
        this.rd.fillRect(100, 440, 670, 40);
        this.rd.drawImage(this.warning, 100, 40, null);
        this.rd.setFont(new Font("SansSerif", 1, 13));
        this.rd.getFontMetrics();
        this.drawcs(390, "Lost data can NOT be recovered!", 220, 0, 0, 3);
        this.rd.drawImage(this.dude[0], 137, 160, null);
        this.rd.drawImage(this.contin[this.pcontin], 404, 255, null);
        this.rd.drawImage(this.back[this.pback], 418, 325, null);
        this.rd.drawImage(this.rusure, 125, 120, null);
        if (control.goback) {
            control.goback = false;
            this.fase = 10;
            this.flipo = 0;
        }
    }
    
    public void blendude(final Image image) {
        if (!this.macn) {
            if (Math.random() > Math.random()) {
                this.dudo = 317;
            }
            else {
                this.dudo = 431;
            }
            final int[] ai = new int[19520];
            final PixelGrabber pixelgrabber = new PixelGrabber(image, this.dudo, 0, 122, 160, ai, 0, 122);
            try {
                pixelgrabber.grabPixels();
            }
            catch (final InterruptedException _ex) {
                this.dudo = -1;
            }
            int j = 0;
            do {
                final int[] ai2 = new int[19520];
                final PixelGrabber pixelgrabber2 = new PixelGrabber(this.dude[j], 0, 10, 122, 160, ai2, 0, 122);
                try {
                    pixelgrabber2.grabPixels();
                }
                catch (final InterruptedException _ex2) {
                    this.dudo = -1;
                }
                if (this.dudo != -1) {
                    int k = 0;
                    do {
                        if (ai2[k] != ai2[0]) {
                            final Color color = new Color(ai2[k]);
                            final Color color2 = new Color(ai[k]);
                            int l = (color.getRed() + color2.getRed() * 3) / 4;
                            if (l > 255) {
                                l = 255;
                            }
                            if (l < 0) {
                                l = 0;
                            }
                            int i1 = (color.getGreen() + color2.getGreen() * 3) / 4;
                            if (i1 > 255) {
                                i1 = 255;
                            }
                            if (i1 < 0) {
                                i1 = 0;
                            }
                            int j2 = (color.getBlue() + color2.getBlue() * 3) / 4;
                            if (j2 > 255) {
                                j2 = 255;
                            }
                            if (j2 < 0) {
                                j2 = 0;
                            }
                            final Color color3 = new Color(l, i1, j2);
                            ai2[k] = color3.getRGB();
                        }
                    } while (++k < 19520);
                    this.dudeb[j] = this.createImage(new MemoryImageSource(122, 160, ai2, 0, 122));
                }
            } while (++j < 3);
        }
        else {
            if (Math.random() > Math.random()) {
                this.dudo = 276;
            }
            else {
                this.dudo = 472;
            }
            int m = 0;
            do {
                this.dudeb[m] = this.dude[m];
            } while (++m < 3);
        }
    }
    
    public void musicomp(final int i, final Control control) {
        if (this.justcs == -1) {
            this.hipnoload(i, true);
        }
        if ((!this.dontdisplay || this.justcs == 5) && (control.handb || control.enter || this.justcs == 5)) {
            System.gc();
            if (this.justcs == -1) {
                this.replayphase = 0;
                this.fase = 0;
                control.handb = false;
                control.enter = false;
            }
            if (this.justcs == 5 && this.fase == 10) {
                this.justcs = 6;
            }
        }
        if (this.dontdisplay && this.showopstage != 195 && (control.handb || control.enter)) {
            this.fase = 49;
            control.handb = false;
            control.enter = false;
        }
        if (this.dontdisplay && this.showopstage == 195 && (control.handb || control.enter)) {
            System.gc();
            this.fase = 0;
            control.handb = false;
            control.enter = false;
            this.showopstage = 0;
            this.aprogress = false;
            this.norepeat = false;
            for (int c = 1; c < 11; ++c) {
                this.namephase[c - 1] = false;
                this.nametrans[c - 1] = 0;
                this.initiate[c - 1] = false;
            }
            this.rd.setFont(new Font("Arial", 1, 11));
            this.ftm = this.rd.getFontMetrics();
        }
    }
    
    public void drawSmokeCarsbg() {
        if (Math.abs(this.flyr - this.flyrdest) > 20) {
            if (this.flyr > this.flyrdest) {
                this.flyr -= 20;
            }
            else {
                this.flyr += 20;
            }
        }
        else {
            this.flyr = this.flyrdest;
            this.flyrdest = (int)(this.flyr + this.m.random() * 160.0f - 80.0f);
        }
        if (this.flyr > 160) {
            this.flyr = 160;
        }
        if (this.flatr > 170) {
            ++this.flatrstart;
            this.flatr = this.flatrstart * 3;
            this.flyr = (int)(this.m.random() * 160.0f - 80.0f);
            this.flyrdest = (int)(this.flyr + this.m.random() * 160.0f - 80.0f);
            this.flang = 1;
            this.flangados = (int)(this.m.random() * 6.0f + 2.0f);
            this.blackn = 0.0f;
            this.blacknados = this.m.random() * 0.4f;
        }
        int i = 0;
        do {
            int j = 0;
            do {
                if (this.smokey[i + j * 616] != this.smokey[0]) {
                    final float f = this.pys(i, 310, j, this.flyr);
                    final int k = (int)((i - 310) / f * this.flatr);
                    final int l = (int)((j - this.flyr) / f * this.flatr);
                    final int i2 = i + k + 123 + (j + l + 107) * 870;
                    if (i + k + 100 >= 870 || i + k + 100 <= 0 || j + l + 110 >= 480 || j + l + 110 <= 0 || i2 >= 600000 || i2 < 0) {
                        continue;
                    }
                    final Color color = new Color(this.flexpix[i2]);
                    final Color color2 = new Color(this.smokey[i + j * 616]);
                    final float f2 = (255.0f - color2.getRed()) / 255.0f;
                    int j2 = (int)((color.getRed() * (this.flang * f2) + color2.getRed() * (1.0f - f2)) / (this.flang * f2 + (1.0f - f2) + this.blackn));
                    if (j2 > 255) {
                        j2 = 255;
                    }
                    if (j2 < 0) {
                        j2 = 0;
                    }
                    final Color color3 = new Color(j2, j2, j2);
                    this.flexpix[i2] = color3.getRGB();
                }
            } while (++j < 262);
        } while (++i < 616);
        this.blackn += this.blacknados;
        this.flang += this.flangados;
        this.flatr += 10 + this.flatrstart * 2;
        final Image image = this.createImage(new MemoryImageSource(870, 480, this.flexpix, 0, 870));
        this.rd.drawImage(image, 0, 0, null);
    }
    
    public void loaddata(final int i) {
        this.kbload = 950;
        this.sunny = true;
        String s = "default/";
        String s2 = "wav";
        if (i == 2) {
            this.kbload = 950;
            this.sunny = true;
            s = "JavaNew/";
            s2 = "wav";
        }
        final String s3 = System.getProperty("os.name");
        if (!s3.startsWith("Win")) {
            this.macn = true;
        }
        this.runtyp = 176;
        (this.runner = new Thread(this)).start();
        this.loadimages();
        this.cars = new RadicalMod("data/Files/music/cars.radq", this.app);
        this.dnload += 27;
        this.menu = new RadicalMod("data/Files/music/menu.radq", this.app);
        this.dnload += 12;
        this.credits = new RadicalMod("data/Files/music/credits.radq", this.app);
        this.dnload += 138;
        int j = 0;
        do {
            int k = 0;
            do {
                this.engs[k][j] = this.getSound("data/Files/sounds/" + s + k + j + ".wav");
                this.dnload += 3;
            } while (++k < 5);
            this.pengs[j] = false;
        } while (++j < 5);
        this.fuucked = this.getSound("data/Files/sounds/caught.wav");
        this.dnload += 239;
        this.redflash = this.getSound("data/Files/sounds/redflash.wav");
        this.dnload += 468;
        this.stages = new RadicalMod("data/Files/music/stages.radq", this.app);
        this.dnload += 91;
        j = 0;
        do {
            this.air[j] = this.getSound("data/Files/sounds/" + s + "air" + j + ".wav");
            this.dnload += 2;
        } while (++j < 6);
        j = 0;
        do {
            this.crash[j] = this.getSound("data/Files/sounds/" + s + "crash" + (j + 1) + "." + s2);
            if (i == 2) {
                this.dnload += 10;
            }
            else {
                this.dnload += 7;
            }
        } while (++j < 3);
        j = 0;
        do {
            this.lowcrash[j] = this.getSound("data/Files/sounds/" + s + "lowcrash" + (j + 1) + "." + s2);
            if (i == 2) {
                this.dnload += 10;
            }
            else {
                this.dnload += 3;
            }
        } while (++j < 3);
        this.tires = this.getSound("data/Files/sounds/" + s + "tires." + s2);
        if (i == 2) {
            this.dnload += 24;
        }
        else {
            this.dnload += 4;
        }
        this.checkpoint = this.getSound("data/Files/sounds/" + s + "checkpoint." + s2);
        if (i == 2) {
            this.dnload += 24;
        }
        else {
            this.dnload += 6;
        }
        this.carfixed = this.getSound("data/Files/sounds/" + s + "carfixed." + s2);
        if (i == 2) {
            this.dnload += 24;
        }
        else {
            this.dnload += 10;
        }
        this.powerup = this.getSound("data/Files/sounds/" + s + "powerup." + s2);
        if (i == 2) {
            this.dnload += 42;
        }
        else {
            this.dnload += 8;
        }
        this.three = this.getSound("data/Files/sounds/" + s + "three." + s2);
        if (i == 2) {
            this.dnload += 24;
        }
        else {
            this.dnload += 4;
        }
        this.two = this.getSound("data/Files/sounds/" + s + "two." + s2);
        if (i == 2) {
            this.dnload += 24;
        }
        else {
            this.dnload += 2;
        }
        this.one = this.getSound("data/Files/sounds/" + s + "one." + s2);
        if (i == 2) {
            this.dnload += 24;
        }
        else {
            this.dnload += 4;
        }
        this.go = this.getSound("data/Files/sounds/" + s + "go." + s2);
        if (i == 2) {
            this.dnload += 24;
        }
        else {
            this.dnload += 4;
        }
        this.wastd = this.getSound("data/Files/sounds/" + s + "wasted." + s2);
        if (i == 2) {
            this.dnload += 24;
        }
        else {
            this.dnload += 4;
        }
        this.firewasted = this.getSound("data/Files/sounds/" + s + "firewasted." + s2);
        if (i == 2) {
            this.dnload += 24;
        }
        else {
            this.dnload += 10;
        }
        j = 0;
        do {
            this.skid[j] = this.getSound("data/Files/sounds/" + s + "skid" + (j + 1) + "." + s2);
            if (i == 2) {
                this.dnload += 22;
            }
            else {
                this.dnload += 6;
            }
        } while (++j < 3);
        j = 0;
        do {
            this.dustskid[j] = this.getSound("data/Files/sounds/" + s + "dustskid" + (j + 1) + "." + s2);
            if (i == 2) {
                this.dnload += 22;
            }
            else {
                this.dnload += 7;
            }
        } while (++j < 3);
        System.gc();
    }
    
    public void clicknow() {
        this.drawcs(395, "CLICK ANYWHERE TO START!", 255, 255, 255, 3);
    }
    
    private Image loadimage(final byte[] abyte0, final MediaTracker mediatracker, final Toolkit toolkit) {
        final Image image = toolkit.createImage(abyte0);
        mediatracker.addImage(image, 0);
        try {
            mediatracker.waitForID(0);
        }
        catch (final Exception ex) {}
        return image;
    }
    
    public void rad(final int i) {
        if (i == 0) {
            this.powerup.play();
            this.radpx = 247;
            this.pin = 0;
        }
        this.trackbg(false);
        this.rd.setColor(new Color(0, 0, 0));
        this.rd.fillRect(100, 150, 670, 59);
        this.menu.loadMod(345, 7900, 125, this.sunny, this.macn);
        if (this.pin != 0) {
            this.rd.drawImage(this.radicalplay, this.radpx + (int)(8.0 * Math.random() - 4.0), 150, null);
        }
        else {
            this.rd.drawImage(this.radicalplay, 247, 150, null);
        }
        if (this.radpx != 247) {
            this.radpx += 40;
            if (this.radpx > 770) {
                this.radpx = -353;
            }
        }
        else if (this.pin != 0) {
            --this.pin;
        }
        if (i == 40) {
            this.radpx = 248;
            this.pin = 7;
        }
        if (this.radpx == 247) {
            this.rd.setFont(new Font("SansSerif", 1, 11));
            this.ftm = this.rd.getFontMetrics();
            this.drawcs(200 + (int)(5.0f * this.m.random()), "Radicalplay.com / Ten Graves", 112, 120, 143, 3);
        }
        this.rd.setFont(new Font("SansSerif", 1, 11));
        this.ftm = this.rd.getFontMetrics();
        if (this.aflk) {
            this.drawcs(230, "Simply the best!", 112, 120, 143, 3);
            this.aflk = false;
        }
        else {
            this.drawcs(230, "Better than all the rest!", 150, 150, 150, 3);
            this.aflk = true;
        }
        this.rd.drawImage(this.rpro, 310, 280, null);
        this.rd.setColor(new Color(0, 0, 0));
        this.rd.fillRect(0, 0, 100, 480);
        this.rd.fillRect(770, 0, 100, 480);
        this.rd.fillRect(100, 0, 670, 40);
        this.rd.fillRect(100, 440, 670, 40);
    }
    
    public void skid(final int i, final float f) {
        if (this.bfcrash == 0 && this.bfskid == 0 && f > 150.0f) {
            if (i == 0) {
                if (!this.mutes) {
                    this.skid[this.skflg].play();
                }
                if (this.skidup) {
                    ++this.skflg;
                }
                else {
                    --this.skflg;
                }
                if (this.skflg == 3) {
                    this.skflg = 0;
                }
                if (this.skflg == -1) {
                    this.skflg = 2;
                }
            }
            else {
                if (!this.mutes) {
                    this.dustskid[this.dskflg].play();
                }
                if (this.skidup) {
                    ++this.dskflg;
                }
                else {
                    --this.dskflg;
                }
                if (this.dskflg == 3) {
                    this.dskflg = 0;
                }
                if (this.dskflg == -1) {
                    this.dskflg = 2;
                }
            }
            this.bfskid = 35;
        }
    }
    
    public int xs(final int i, final int j) {
        return (j - this.m.focus_point) * (this.m.cx - i) / j + i;
    }
    
    public void cantreply() {
        this.rd.setColor(new Color(64, 143, 223));
        this.rd.fillRoundRect(235, 73, 400, 23, 7, 20);
        this.rd.setColor(new Color(0, 89, 223));
        this.rd.drawRoundRect(235, 73, 400, 23, 7, 20);
        this.drawcs(89, "Sorry not enough replay data to play available, please try again later.", 255, 255, 255, 1);
    }
    
    public void quitwarning(final Control control, final Madness[] madness, final CheckPoints checkpoints) {
        this.ftm = this.rd.getFontMetrics();
        this.rd.setColor(new Color(64, 143, 223));
        this.rd.fillRoundRect(190, 203, 490, 23, 7, 20);
        this.rd.setColor(new Color(0, 89, 223));
        this.rd.drawRoundRect(190, 203, 490, 23, 7, 20);
        this.drawcs(219, "You'll lose XP if you quit! Press [Enter] to continue anyway, or spacebar to go back.", 255, 255, 255, 1);
        if (control.enter) {
            if (this.careermode && checkpoints.stage == this.unlocked[1] && !this.bonstage && this.unlocked[1] > 1) {
                madness[0].exp[this.sc[0]] = this.startexp;
                this.statpoints[this.sc[0]] = this.startsp;
                if (!this.losepoints) {
                    final int[] extpoints = this.extpoints;
                    final int n = this.sc[0];
                    extpoints[n] -= this.statgain;
                    this.losepoints = true;
                }
            }
            this.fase = 10;
            control.enter = false;
        }
        if (control.handb) {
            this.fase = -7;
            control.handb = false;
        }
    }
    
    public void stopallnow() {
        int i = 0;
        do {
            if (this.loadedt[i]) {
                if (this.isMidi[i]) {
                    this.mtracks[i].unloadMidi();
                }
                else {
                    this.stracks[i].unloadAll();
                    this.stracks[i] = null;
                }
            }
        } while (++i < 200);
        i = 0;
        do {
            this.engs[0][i].stop();
            this.engs[1][i].stop();
        } while (++i < 5);
        i = 0;
        do {
            this.air[i].stop();
        } while (++i < 6);
        this.wastd.stop();
        this.cars.unloadAll();
        this.stages.unloadAll();
    }
    
    public void inishcarselect() {
        this.carsbginflex();
        this.flatrstart = 0;
        this.m.lightson = false;
        this.cars.loadMod(200, 7900, 125, this.sunny, this.macn);
        this.pnext = 0;
        this.pback = 0;
    }
    
    public void carselect(final Control control, final ContO[] aconto, final Madness madness, final CheckPoints checkpoints) {
        this.cars.play();
        this.rd.setColor(new Color(0, 0, 0));
        this.rd.fillRect(0, 0, 100, 480);
        this.rd.fillRect(770, 0, 100, 480);
        this.rd.fillRect(100, 0, 670, 40);
        this.rd.fillRect(100, 440, 670, 40);
        this.tocs = false;
        if (this.flatrstart == 6) {
            this.rd.drawImage(this.carsbg, 0, 0, null);
        }
        else if (this.flatrstart <= 1) {
            this.drawSmokeCarsbg();
        }
        else {
            this.rd.setColor(new Color(255, 255, 255));
            this.rd.fillRect(0, 0, 870, 480);
            this.carsbginflex();
            this.flatrstart = 6;
        }
        this.rd.drawImage(this.selectcar, 356, 22, null);
        this.m.crs = true;
        this.m.x = -435;
        this.m.y = -540;
        this.m.z = -50;
        this.m.xz = 0;
        this.m.zy = 10;
        this.m.ground = 510;
        aconto[this.sc[0]].d(this.rd);
        boolean noback = false;
        boolean nonext = false;
        if (this.classicmode) {
            if (this.sc[0] == 23) {
                noback = true;
            }
            if (this.sc[0] == 38) {
                nonext = true;
            }
        }
        if (this.careermode) {
            if (this.sc[0] == 0 || this.sc[0] == 23 || this.shufflefase >= 6) {
                noback = true;
            }
            if (this.sc[0] == 21 || this.sc[0] == 22 || this.shufflefase >= 6) {
                nonext = true;
            }
        }
        final int normalun = (this.sc[0] - 7) * 2;
        final int endgame = (this.sc[0] - 11) * 3 + 2;
        if (this.careermode) {
            this.notunlocked = false;
            if (normalun >= this.unlocked[1] && this.sc[0] <= 17) {
                this.notunlocked = true;
            }
            if ((this.sc[0] == 18 || this.sc[0] == 19) && endgame >= this.unlocked[1]) {
                this.notunlocked = true;
            }
            if (this.sc[0] == 22 && this.unlocked[1] <= 30) {
                this.notunlocked = true;
            }
            if (this.boncomp[0] == 0 && (this.sc[0] == 31 || this.sc[0] == 32 || this.sc[0] == 33)) {
                this.notunlocked = true;
            }
            if (this.boncomp[1] == 0 && (this.sc[0] == 34 || this.sc[0] == 35 || this.sc[0] == 36)) {
                this.notunlocked = true;
            }
            if (this.boncomp[1] == 1 && this.sc[0] == 36) {
                this.notunlocked = true;
            }
            if (this.boncomp[1] == 2 && this.sc[0] == 35) {
                this.notunlocked = true;
            }
            if (this.boncomp[2] == 0 && (this.sc[0] == 37 || this.sc[0] == 38)) {
                this.notunlocked = true;
            }
            if (this.sc[0] == 20 && this.boncomp[4] == 0) {
                this.notunlocked = true;
            }
            if (this.sc[0] == 21 && this.boncomp[5] == 0) {
                this.notunlocked = true;
            }
        }
        else {
            this.notunlocked = false;
        }
        if (this.notunlocked) {
            aconto[this.sc[0]].invisiblepiece = 20;
        }
        else {
            aconto[this.sc[0]].invisiblepiece = 255;
        }
        for (int a = 0; a < 39; ++a) {
            aconto[a].groundlevel = -34;
        }
        if (this.flipo == 0) {
            this.rd.setFont(new Font("SansSerif", 1, 13));
            this.ftm = this.rd.getFontMetrics();
            byte byte0 = 0;
            if (this.flatrstart < 6) {
                byte0 = 2;
            }
            if (this.aflk) {
                this.drawcs(80 + byte0, this.names[this.sc[0]], 240, 240, 240, 3);
                this.aflk = false;
            }
            else {
                this.drawcs(80, this.names[this.sc[0]], 176, 176, 176, 3);
                this.aflk = true;
            }
            if (this.careermode) {
                this.rd.setFont(this.adventure.deriveFont(1, 18.0f));
                this.ftm = this.rd.getFontMetrics();
                this.drawcs(105, "level " + madness.level[this.sc[0]], 0, 185, 0, 3);
                this.rd.setFont(new Font("SansSerif", 1, 13));
                this.ftm = this.rd.getFontMetrics();
            }
            if (!this.classicmode) {
                this.rd.setFont(this.adventure.deriveFont(1, 15.0f));
                this.ftm = this.rd.getFontMetrics();
                if (this.sc[0] == 1) {
                    this.drawcs(60, "Created by Afterburn/ToaZuka", 246, 246, 246, 3);
                }
                if (this.sc[0] == 11) {
                    this.drawcs(60, "Created by Phyrexian", 246, 246, 246, 3);
                }
                if (this.sc[0] == 18) {
                    this.drawcs(60, "Created by aliff01/Phyrexian", 246, 246, 246, 3);
                }
                if (this.sc[0] == 15) {
                    this.drawcs(60, "Created by Ultimato", 246, 246, 246, 3);
                }
                if (this.sc[0] == 5) {
                    this.drawcs(60, "Created by Trelivision", 246, 246, 246, 3);
                }
                if (this.sc[0] == 6) {
                    this.drawcs(60, "Created by GX", 246, 246, 246, 3);
                }
                if (this.sc[0] == 3 || this.sc[0] == 16) {
                    this.drawcs(60, "Created by Rulue", 246, 246, 246, 3);
                }
                if (this.sc[0] == 7) {
                    this.drawcs(60, "Created by Chaotic", 246, 246, 246, 3);
                }
                if (this.sc[0] == 8) {
                    this.drawcs(60, "Created by projectDUB", 246, 246, 246, 3);
                }
                if (this.sc[0] == 0) {
                    this.drawcs(60, "Created by aliff01", 246, 246, 246, 3);
                }
                if (this.sc[0] == 17) {
                    this.drawcs(60, "Created by RAD1", 246, 246, 246, 3);
                }
                if (this.sc[0] == 14 || this.sc[0] == 9 || this.sc[0] == 4 || this.sc[0] == 20) {
                    this.drawcs(60, "Created by Tunari", 246, 246, 246, 3);
                }
                if (this.sc[0] == 19) {
                    this.drawcs(60, "Created by Vitalogy", 246, 246, 246, 3);
                }
                if (this.sc[0] == 2 || this.sc[0] == 22) {
                    this.drawcs(60, "Created by ACVoong", 246, 246, 246, 3);
                }
                if (this.sc[0] == 10 || this.sc[0] == 21) {
                    this.drawcs(60, "Created by DJ Miker", 246, 246, 246, 3);
                }
                if (this.sc[0] == 12) {
                    this.drawcs(60, "Created by KingOfSpeed", 246, 246, 246, 3);
                }
                if (this.sc[0] == 13) {
                    this.drawcs(60, "Created by Excalibur", 246, 246, 246, 3);
                }
                this.rd.setFont(new Font("Arial", 1, 11));
                this.ftm = this.rd.getFontMetrics();
            }
            aconto[this.sc[0]].z = 950;
            final int n = this.sc[0];
            final int n2 = this.sc[0];
            aconto[this.sc[0]].y = -34 - aconto[this.sc[0]].grat;
            aconto[this.sc[0]].x = 0;
            final ContO contO = aconto[this.sc[0]];
            contO.xz += 4;
            aconto[this.sc[0]].zy = 0;
            final ContO contO2 = aconto[this.sc[0]];
            contO2.wzy -= 10;
            if (aconto[this.sc[0]].wzy < -45) {
                final ContO contO3 = aconto[this.sc[0]];
                contO3.wzy += 45;
            }
            if (!noback) {
                this.rd.drawImage(this.back[this.pback], 30, 290, null);
            }
            if (!nonext) {
                this.rd.drawImage(this.next[this.pnext], 780, 290, null);
            }
            if (this.notunlocked) {
                if (this.flatrstart == 6 && this.careermode) {
                    if (this.sc[0] <= 17) {
                        this.drawcs(395, "[ Car Locked ]", 210, 210, 210, 3);
                        this.drawcs(415, "This car unlocks when stage " + normalun + " is completed...", 181, 120, 40, 3);
                    }
                    if (this.sc[0] == 18 || this.sc[0] == 19) {
                        this.drawcs(395, "[ Car Locked ]", 210, 210, 210, 3);
                        this.drawcs(415, "This car unlocks when stage " + endgame + " is completed...", 181, 120, 40, 3);
                    }
                    if (this.sc[0] == 22) {
                        this.drawcs(395, "[ Car Locked ]", 210, 210, 210, 3);
                        this.drawcs(415, "This car unlocks when stage 30 is completed...", 181, 120, 40, 3);
                    }
                    if ((this.sc[0] == 31 || this.sc[0] == 32 || this.sc[0] == 33) && this.boncomp[0] == 0) {
                        this.drawcs(395, "[ Car Locked ]", 210, 210, 210, 3);
                        this.drawcs(415, "This car unlocks when you complete the first bonus stage...", 181, 120, 40, 3);
                    }
                    if (this.sc[0] == 34 && this.boncomp[1] == 0) {
                        this.drawcs(395, "[ Car Locked ]", 210, 210, 210, 3);
                        this.drawcs(415, "This car unlocks when you complete the second bonus stage...", 181, 120, 40, 3);
                    }
                    if (this.sc[0] == 35 && (this.boncomp[1] == 0 || this.boncomp[1] == 2)) {
                        this.drawcs(395, "[ Car Locked ]", 210, 210, 210, 3);
                        this.drawcs(415, "This car unlocks when you complete the second bonus stage by racing...", 181, 120, 40, 3);
                    }
                    if (this.sc[0] == 36 && (this.boncomp[1] == 0 || this.boncomp[1] == 1)) {
                        this.drawcs(395, "[ Car Locked ]", 210, 210, 210, 3);
                        this.drawcs(415, "This car unlocks when you complete the second bonus stage by wasting...", 181, 120, 40, 3);
                    }
                    if ((this.sc[0] == 37 || this.sc[0] == 38) && this.boncomp[2] == 0) {
                        this.drawcs(395, "[ Car Locked ]", 210, 210, 210, 3);
                        this.drawcs(415, "This car unlocks when you complete the third bonus stage...", 181, 120, 40, 3);
                    }
                    if (this.sc[0] == 20 && this.boncomp[4] == 0) {
                        this.drawcs(395, "[ Car Locked ]", 210, 210, 210, 3);
                        this.drawcs(415, "This car unlocks when you complete the fifth bonus stage...", 181, 120, 40, 3);
                    }
                    if (this.sc[0] == 21 && this.boncomp[5] == 0) {
                        this.drawcs(395, "[ Car Locked ]", 210, 210, 210, 3);
                        this.drawcs(415, "This car unlocks when you complete the sixth bonus stage...", 181, 120, 40, 3);
                    }
                }
            }
            else if (this.flatrstart == 6) {
                this.rd.setColor(new Color(255, 255, 255, 210));
                final Polygon dale = new Polygon();
                dale.addPoint(10, 43);
                dale.addPoint(20, 28);
                dale.addPoint(280, 28);
                dale.addPoint(290, 43);
                dale.addPoint(290, 43 + this.lines * 15);
                dale.addPoint(280, 58 + this.lines * 15);
                dale.addPoint(20, 58 + this.lines * 15);
                dale.addPoint(10, 43 + this.lines * 15);
                this.rd.fillPolygon(dale);
                this.rd.setFont(this.adventure.deriveFont(1, 13.0f));
                this.ftm = this.rd.getFontMetrics();
                this.rd.setColor(new Color(30, 30, 30));
                this.rd.drawString("SPECIAL ATTACK:", 140 - this.ftm.stringWidth("SPECIAL ATTACK:") / 2, 43);
                this.rd.setFont(this.adventure.deriveFont(1, 11.0f));
                this.ftm = this.rd.getFontMetrics();
                double specialboost = 1.0;
                if (this.careermode) {
                    for (int a2 = 0; a2 < 6; ++a2) {
                        if (this.specialstats[this.sc[0]][20][a2] > 0) {
                            specialboost = 1.0 + this.specialstats[this.sc[0]][20][a2] * 1.25 / 100.0;
                        }
                    }
                }
                if (this.sc[0] == 0 || this.sc[0] == 23) {
                    this.rd.drawString("A random car gets reduced speed.", 20, 60);
                    if (this.sc[0] == 0) {
                        this.rd.drawString("Strength/Defence boost: " + (int)(50.0 * specialboost) + "%/" + (int)(30.0 * specialboost) + "%", 20, 75);
                    }
                    else {
                        this.rd.drawString("Strength/Defence boost: " + (int)(55.0 * specialboost) + "%/" + (int)(30.0 * specialboost) + "%", 20, 75);
                    }
                    this.lines = 2;
                }
                if (this.sc[0] == 1 || this.sc[0] == 24) {
                    this.rd.drawString((int)(15.0 * specialboost) + "% speed boost.", 20, 60);
                    this.rd.drawString("Swaps its strength with a random car.", 20, 75);
                    this.rd.drawString("Unlimited power.", 20, 90);
                    this.lines = 3;
                }
                if (this.sc[0] == 2 || this.sc[0] == 25) {
                    this.rd.drawString("Unlimited power.", 20, 60);
                    this.rd.drawString("Strength/Defence boost: " + (int)(45.0 * specialboost) + "%/" + (int)(30.0 * specialboost) + "%", 20, 75);
                    this.rd.drawString("Drains a random car's health.", 20, 90);
                    this.lines = 3;
                }
                if (this.sc[0] == 3 || this.sc[0] == 26) {
                    this.rd.drawString("Reduces the defence of the car in first.", 20, 60);
                    if (this.sc[0] == 3) {
                        this.rd.drawString("Speed/Stunting boost: " + (int)(30.0 * specialboost) + "%/" + (int)(75.0 * specialboost) + "%", 20, 75);
                    }
                    else {
                        this.rd.drawString("Speed/Stunting boost: " + (int)(35.0 * specialboost) + "%/" + (int)(60.0 * specialboost) + "%", 20, 75);
                    }
                    this.lines = 2;
                }
                if (this.sc[0] == 4 || this.sc[0] == 27) {
                    this.rd.drawString((int)(70.0 * specialboost) + "% strength boost.", 20, 60);
                    this.rd.drawString("Reduces a random car's defence.", 20, 75);
                    this.lines = 2;
                }
                if (this.sc[0] == 5 || this.sc[0] == 28) {
                    this.rd.drawString((int)(30.0 * specialboost) + "% strength and " + (int)(25.0 * specialboost) + "% speed boost.", 20, 60);
                    this.rd.drawString("These boosts double past 50% damage.", 20, 75);
                    this.lines = 2;
                }
                if (this.sc[0] == 6 || this.sc[0] == 29) {
                    this.rd.drawString("Strength/Speed boost: " + (int)(50.0 * specialboost) + "%/" + (int)(15.0 * specialboost) + "%", 20, 60);
                    this.rd.drawString("Drains a random car's health.", 20, 75);
                    this.lines = 2;
                }
                if (this.sc[0] == 7 || this.sc[0] == 30) {
                    this.rd.drawString("Strength/Defence boost: " + (int)(60.0 * specialboost) + "%/" + (int)(50.0 * specialboost) + "%", 20, 60);
                    this.lines = 1;
                }
                if (this.sc[0] == 8 || this.sc[0] == 31) {
                    if (this.sc[0] == 8) {
                        this.rd.drawString((int)(100.0 * specialboost) + "% control boost.", 20, 60);
                        this.rd.drawString((int)(45.0 * specialboost) + "% strength boost.", 20, 75);
                        this.rd.drawString((int)(30.0 * specialboost) + "% speed boost.", 20, 90);
                    }
                    else {
                        this.rd.drawString((int)(75.0 * specialboost) + "% control boost.", 20, 60);
                        this.rd.drawString((int)(40.0 * specialboost) + "% strength boost.", 20, 75);
                        this.rd.drawString((int)(20.0 * specialboost) + "% speed boost.", 20, 90);
                    }
                    this.lines = 3;
                }
                if (this.sc[0] == 9 || this.sc[0] == 32) {
                    this.rd.drawString("A random car gets reduced speed.", 20, 60);
                    if (this.sc[0] == 9) {
                        this.rd.drawString("Strength/Speed boost: " + (int)(40.0 * specialboost) + "%/" + (int)(10.0 * specialboost) + "%", 20, 75);
                    }
                    else {
                        this.rd.drawString("Strength/Speed boost: " + (int)(35.0 * specialboost) + "%/" + (int)(10.0 * specialboost) + "%", 20, 75);
                    }
                    this.rd.drawString("You get unlimited power.", 20, 90);
                    this.lines = 3;
                }
                if (this.sc[0] == 10 || this.sc[0] == 33) {
                    if (this.sc[0] == 10) {
                        this.rd.drawString((int)(30.0 * specialboost) + "% speed boost.", 20, 60);
                        this.rd.drawString("Strength/Defence boost: " + (int)(35.0 * specialboost) + "%/" + (int)(40.0 * specialboost) + "%", 20, 75);
                    }
                    else {
                        this.rd.drawString((int)(20.0 * specialboost) + "% speed/strength boost.", 20, 60);
                        this.rd.drawString("Strength/Defence boost: " + (int)(30.0 * specialboost) + "%", 20, 75);
                    }
                    this.rd.drawString("Reduces a random car's defence.", 20, 90);
                    this.lines = 3;
                }
                if (this.sc[0] == 11 || this.sc[0] == 34) {
                    if (this.sc[0] == 11) {
                        this.rd.drawString("Strength/Speed boost: " + (int)(40.0 * specialboost) + "%/" + (int)(30.0 * specialboost) + "%.", 20, 60);
                    }
                    else {
                        this.rd.drawString("Strength/Speed boost: " + (int)(30.0 * specialboost) + "%/" + (int)(20.0 * specialboost) + "%.", 20, 60);
                    }
                    this.rd.drawString("Drains a random car's health.", 20, 75);
                    this.lines = 2;
                }
                if (this.sc[0] == 12 || this.sc[0] == 35) {
                    if (this.sc[0] == 12) {
                        this.rd.drawString((int)(25.0 * specialboost) + "% speed boost.", 20, 60);
                    }
                    else {
                        this.rd.drawString((int)(20.0 * specialboost) + "% speed boost.", 20, 60);
                    }
                    this.rd.drawString("You get unlimited power.", 20, 75);
                    this.rd.drawString("Reduces the defence of the car in first.", 20, 90);
                    this.lines = 3;
                }
                if (this.sc[0] == 13 || this.sc[0] == 36) {
                    if (this.sc[0] == 13) {
                        this.rd.drawString((int)(40.0 * specialboost) + "% strength/defence boost.", 20, 60);
                    }
                    else {
                        this.rd.drawString((int)(30.0 * specialboost) + "% strength/defence boost.", 20, 60);
                    }
                    this.rd.drawString("Reduces a random car's defence.", 20, 75);
                    this.lines = 2;
                }
                if (this.sc[0] == 14 || this.sc[0] == 37) {
                    if (this.sc[0] == 14) {
                        this.rd.drawString("Speed/Stunting boost: " + (int)(15.0 * specialboost) + "%/" + (int)(100.0 * specialboost) + "%.", 20, 60);
                        this.rd.drawString("Strength/Defence boost: " + (int)(40.0 * specialboost) + "%/" + (int)(70.0 * specialboost) + "%.", 20, 75);
                    }
                    else {
                        this.rd.drawString("Speed boost: " + (int)(15.0 * specialboost) + "%", 20, 60);
                        this.rd.drawString("Strength/Defence boost: " + (int)(35.0 * specialboost) + "%/" + (int)(50.0 * specialboost) + "%.", 20, 75);
                    }
                    this.rd.drawString("A random car gets reduced speed.", 20, 90);
                    this.lines = 3;
                }
                if (this.sc[0] == 15 || this.sc[0] == 38) {
                    if (this.sc[0] == 15) {
                        this.rd.drawString("Every stat increases by " + (int)(30.0 * specialboost) + "%.", 20, 60);
                    }
                    else {
                        this.rd.drawString("Every stat increases by " + (int)(20.0 * specialboost) + "%.", 20, 60);
                    }
                    this.lines = 1;
                }
                if (this.sc[0] == 16) {
                    this.rd.drawString("Unlimited power.", 20, 60);
                    this.rd.drawString("Strength/Speed boost: " + (int)(30.0 * specialboost) + "%/" + (int)(15.0 * specialboost) + "%.", 20, 75);
                    this.rd.drawString("Control boost: " + (int)(50.0 * specialboost) + "%", 20, 90);
                    this.rd.drawString("Drains a random car's health.", 20, 105);
                    this.lines = 4;
                }
                if (this.sc[0] == 17) {
                    this.rd.drawString("Unlimited power.", 20, 60);
                    this.rd.drawString((int)(50.0 * specialboost) + "% defence boost.", 20, 75);
                    this.rd.drawString((int)(15.0 * specialboost) + "% speed boost.", 20, 90);
                    this.lines = 3;
                }
                if (this.sc[0] == 18) {
                    this.rd.drawString((int)(20.0 * specialboost) + "% speed boost.", 20, 60);
                    this.rd.drawString((int)(25.0 * specialboost) + "% strength/defence boost.", 20, 75);
                    this.rd.drawString("Reduces the defence of a random car.", 20, 90);
                    this.lines = 3;
                }
                if (this.sc[0] == 19) {
                    this.rd.drawString("Strength/Speed boost: " + (int)(20.0 * specialboost) + "%/" + (int)(10.0 * specialboost) + "%.", 20, 60);
                    this.rd.drawString((int)(30.0 * specialboost) + "% defence boost.", 20, 75);
                    this.rd.drawString("Reduces the speed of a random car.", 20, 90);
                    this.lines = 3;
                }
                if (this.sc[0] == 20) {
                    this.rd.drawString("Strength/Defence boost: " + (int)(60.0 * specialboost) + "%/" + (int)(40.0 * specialboost) + "%.", 20, 60);
                    this.rd.drawString((int)(10.0 * specialboost) + "% speed cut.", 20, 75);
                    this.rd.drawString("Reduces the defence of a random car.", 20, 90);
                    this.lines = 3;
                }
                if (this.sc[0] == 22) {
                    this.rd.drawString("Strength/Speed boost: " + (int)(15.0 * specialboost) + "%.", 20, 60);
                    this.rd.drawString("Defence boost: " + (int)(30.0 * specialboost) + "%.", 20, 75);
                    this.rd.drawString("Acceleration boost: " + (int)(100.0 * specialboost) + "%.", 20, 90);
                    this.rd.drawString("Unlimited power.", 20, 105);
                    this.lines = 4;
                }
                this.rd.setColor(new Color(181, 120, 40));
                this.rd.setFont(new Font("SansSerif", 1, 11));
                this.ftm = this.rd.getFontMetrics();
                if (this.careermode) {
                    final Polygon[] goodness = new Polygon[6];
                    for (int a3 = 0; a3 < 6; ++a3) {
                        goodness[a3] = new Polygon();
                        if (a3 < 3) {
                            goodness[a3].addPoint(355, 390 + a3 * 30);
                            goodness[a3].addPoint(375, 390 + a3 * 30);
                            goodness[a3].addPoint(378, 393 + a3 * 30);
                            goodness[a3].addPoint(378, 412 + a3 * 30);
                            goodness[a3].addPoint(375, 415 + a3 * 30);
                            goodness[a3].addPoint(355, 415 + a3 * 30);
                            goodness[a3].addPoint(352, 412 + a3 * 30);
                            goodness[a3].addPoint(352, 393 + a3 * 30);
                        }
                        else {
                            goodness[a3].addPoint(765, 390 + (a3 - 3) * 30);
                            goodness[a3].addPoint(785, 390 + (a3 - 3) * 30);
                            goodness[a3].addPoint(788, 393 + (a3 - 3) * 30);
                            goodness[a3].addPoint(788, 412 + (a3 - 3) * 30);
                            goodness[a3].addPoint(785, 415 + (a3 - 3) * 30);
                            goodness[a3].addPoint(765, 415 + (a3 - 3) * 30);
                            goodness[a3].addPoint(762, 412 + (a3 - 3) * 30);
                            goodness[a3].addPoint(762, 393 + (a3 - 3) * 30);
                        }
                        if (!this.hoverstat[a3]) {
                            this.rd.setColor(new Color(255, 255, 255, 150));
                        }
                        else {
                            this.rd.setColor(new Color(255, 255, 255, 255));
                        }
                        this.rd.fillPolygon(goodness[a3]);
                    }
                    this.rd.setColor(new Color(0, 0, 0));
                    this.rd.setFont(new Font("Arial", 1, 25));
                    for (int a3 = 0; a3 < 6; ++a3) {
                        if (a3 < 3) {
                            this.rd.drawString("+", 358, 412 + a3 * 30);
                        }
                        else {
                            this.rd.drawString("+", 768, 412 + (a3 - 3) * 30);
                        }
                    }
                    this.rd.setFont(new Font("Arial", 1, 11));
                }
                this.rd.setColor(new Color(0, 0, 0));
                if (control.statincrease) {
                    if (this.careermode && ((this.statpoints[this.sc[0]] > 0 && !this.nclicked) || (this.carpoints > 0 && this.nclicked))) {
                        if (!this.nclicked) {
                            if (this.statcm[0]) {
                                final int[] aitssp = madness.aitssp;
                                final int n3 = this.sc[0];
                                ++aitssp[n3];
                                this.statcm[0] = false;
                            }
                            if (this.statcm[1]) {
                                final int[] aiaccsp = madness.aiaccsp;
                                final int n4 = this.sc[0];
                                ++aiaccsp[n4];
                                this.statcm[1] = false;
                            }
                            if (this.statcm[2]) {
                                final int[] aigripsp = madness.aigripsp;
                                final int n5 = this.sc[0];
                                ++aigripsp[n5];
                                this.statcm[2] = false;
                            }
                            if (this.statcm[3]) {
                                final int[] aistusp = madness.aistusp;
                                final int n6 = this.sc[0];
                                ++aistusp[n6];
                                this.statcm[3] = false;
                            }
                            if (this.statcm[4]) {
                                final int[] aistrsp = madness.aistrsp;
                                final int n7 = this.sc[0];
                                ++aistrsp[n7];
                                this.statcm[4] = false;
                            }
                            if (this.statcm[5]) {
                                final int[] aiendsp = madness.aiendsp;
                                final int n8 = this.sc[0];
                                ++aiendsp[n8];
                                this.statcm[5] = false;
                            }
                        }
                        else {
                            for (int a2 = 0; a2 < 6; ++a2) {
                                if (this.statcm[a2]) {
                                    if (this.specialstats[this.sc[0]][this.statsalc[this.sc[0]][a2]][a2] < 20) {
                                        final int[] array = this.specialstats[this.sc[0]][this.statsalc[this.sc[0]][a2]];
                                        final int n9 = a2;
                                        ++array[n9];
                                        --this.carpoints;
                                    }
                                    this.statcm[a2] = false;
                                }
                            }
                        }
                        for (int a2 = 0; a2 < 7; ++a2) {
                            this.colorcode[a2] = 0;
                            this.stopflashing[a2] = false;
                        }
                        if (!this.nclicked) {
                            final int[] statpoints = this.statpoints;
                            final int n10 = this.sc[0];
                            --statpoints[n10];
                        }
                    }
                    control.statincrease = false;
                }
                if (!this.careermode) {
                    this.rd.setColor(new Color(235, 235, 235));
                    this.rd.setFont(this.fifa.deriveFont(1, 18.0f));
                    this.ftm = this.rd.getFontMetrics();
                    this.rd.drawString("OVERALL:", 160 - this.ftm.stringWidth("OVERALL:"), 378);
                    this.rd.drawString("TOP SPEED:", 160 - this.ftm.stringWidth("TOP SPEED:"), 408);
                    this.rd.drawString("ACCELERATION:", 160 - this.ftm.stringWidth("ACCELERATION:"), 438);
                    this.rd.drawString("CONTROL:", 160 - this.ftm.stringWidth("CONTROL:"), 468);
                    this.rd.drawString("STUNTING:", 570 - this.ftm.stringWidth("STUNTING:"), 408);
                    this.rd.drawString("STRENGTH:", 570 - this.ftm.stringWidth("STRENGTH:"), 438);
                    this.rd.drawString("DEFENCE:", 570 - this.ftm.stringWidth("DEFENCE:"), 468);
                    if (this.classicmode) {
                        this.pace = (madness.nitroswits[this.sc[0]][2] - 220) / 90.0f;
                    }
                    else {
                        this.pace = (madness.nitroswits[this.sc[0]][2] - 220) / 90.0f;
                    }
                    int f = (int)(this.pace * 163.0f);
                    if (f > 163) {
                        f = 163;
                    }
                    if (f < 15) {
                        f = 15;
                    }
                    final float[] realacelf = { madness.nitroacelf[this.sc[0]][0] - 6.0f, madness.nitroacelf[this.sc[0]][1] - 3.0f, madness.nitroacelf[this.sc[0]][2] - 2.0f };
                    this.accel = (realacelf[0] * 21.0f + realacelf[1] * 6.0f + realacelf[2] * 3.0f) / 201.0f;
                    int f2 = (int)(this.accel * 163.0f);
                    if (f2 > 163) {
                        f2 = 163;
                    }
                    final float grip = (madness.gripreset[this.sc[0]] - 10.0f) / 20.0f;
                    int f3 = (int)(grip * 163.0f);
                    if (f3 > 163) {
                        f3 = 163;
                    }
                    final float stunts = (madness.aircreset[this.sc[0]] + madness.airsreset[this.sc[0]] * 10.0f) / 125.0f;
                    int f4 = (int)(stunts * 163.0f);
                    if (f4 > 163) {
                        f4 = 163;
                    }
                    final float str = madness.momentreset[this.sc[0]] / 2.1f;
                    int f5 = (int)(str * 163.0f);
                    if (f5 > 163) {
                        f5 = 163;
                    }
                    final float tank = this.outdam[this.sc[0]];
                    int f6 = (int)(tank * 163.0f);
                    if (f6 > 163) {
                        f6 = 163;
                    }
                    final int accatt = (int)(this.accel * 100.0f);
                    final int griatt = (int)(grip * 100.0f);
                    final int stuatt = (int)(stunts * 100.0f);
                    final int stratt = (int)(str * 100.0f);
                    final int endatt = (int)(tank * 100.0f);
                    final float avg = (this.pace + this.accel + grip + stunts + str + tank) / 6.0f;
                    final int ovratt = (int)(avg * 100.0f);
                    int f7 = (int)(avg * 163.0f);
                    if (f7 > 163) {
                        f7 = 163;
                    }
                    this.rd.setColor(new Color(0, 130, 0));
                    this.rd.fillRect(175, 362, f7, 18);
                    this.rd.fillRect(175, 392, f, 18);
                    this.rd.fillRect(175, 422, f2, 18);
                    this.rd.fillRect(175, 452, f3, 18);
                    this.rd.fillRect(585, 392, f4, 18);
                    this.rd.fillRect(585, 422, f5, 18);
                    this.rd.fillRect(585, 452, f6, 18);
                    this.rd.setColor(new Color(252, 206, 0));
                    final String[] central = { "12", "12", "12", "12", "12", "12" };
                    if (accatt >= 100) {
                        central[0] = "120";
                    }
                    else {
                        central[0] = "12";
                    }
                    if (griatt >= 100) {
                        central[1] = "120";
                    }
                    else {
                        central[1] = "12";
                    }
                    if (stuatt >= 100) {
                        central[2] = "120";
                    }
                    else {
                        central[2] = "12";
                    }
                    if (stratt >= 100) {
                        central[3] = "120";
                    }
                    else {
                        central[3] = "12";
                    }
                    if (endatt >= 100) {
                        central[4] = "120";
                    }
                    else {
                        central[4] = "12";
                    }
                    if (ovratt >= 100) {
                        central[5] = "120";
                    }
                    else {
                        central[5] = "12";
                    }
                    this.rd.setColor(new Color(245, 245, 245));
                    this.rd.drawRect(584, 391, 165, 20);
                    this.rd.drawRect(585, 392, 163, 18);
                    this.rd.drawRect(584, 421, 165, 20);
                    this.rd.drawRect(585, 422, 163, 18);
                    this.rd.drawRect(584, 451, 165, 20);
                    this.rd.drawRect(585, 452, 163, 18);
                    this.rd.drawRect(174, 361, 165, 20);
                    this.rd.drawRect(175, 362, 163, 18);
                    this.rd.drawRect(174, 391, 165, 20);
                    this.rd.drawRect(175, 392, 163, 18);
                    this.rd.drawRect(174, 421, 165, 20);
                    this.rd.drawRect(175, 422, 163, 18);
                    this.rd.drawRect(174, 451, 165, 20);
                    this.rd.drawRect(175, 452, 163, 18);
                }
                else {
                    if (this.sc[0] >= 31 && madness.aitssp[this.sc[0]] == 0) {
                        this.resetstats(madness, this.sc[0]);
                    }
                    this.rd.setFont(this.fifa.deriveFont(1, 18.0f));
                    this.ftm = this.rd.getFontMetrics();
                    this.rd.setColor(new Color(255, 50, 0));
                    if (!this.nclicked) {
                        this.rd.drawString("STAT POINTS:   " + this.statpoints[this.sc[0]], 442, 375);
                    }
                    else {
                        this.rd.drawString("CAR POINTS:   " + this.carpoints, 454, 375);
                    }
                    final int[] fdack = new int[7];
                    final float[] estat = new float[7];
                    if (!this.nclicked) {
                        this.rd.setColor(new Color(235, 235, 235));
                        this.rd.drawString("OVERALL:", 160 - this.ftm.stringWidth("OVERALL:"), 378);
                        this.rd.drawString("TOP SPEED:", 160 - this.ftm.stringWidth("TOP SPEED:"), 408);
                        this.rd.drawString("ACCELERATION:", 160 - this.ftm.stringWidth("ACCELERATION:"), 438);
                        this.rd.drawString("CONTROL:", 160 - this.ftm.stringWidth("CONTROL:"), 468);
                        this.rd.drawString("STUNTING:", 570 - this.ftm.stringWidth("STUNTING:"), 408);
                        this.rd.drawString("STRENGTH:", 570 - this.ftm.stringWidth("STRENGTH:"), 438);
                        this.rd.drawString("DEFENCE:", 570 - this.ftm.stringWidth("DEFENCE:"), 468);
                        estat[1] = (madness.nitroswits[this.sc[0]][2] + madness.aitssp[this.sc[0]] - 220) / 90.0f;
                        final float[] realacelf2 = { madness.nitroacelf[this.sc[0]][0] + madness.aiaccsp[this.sc[0]] * 0.1f - 6.0f, 0.0f, 0.0f };
                        realacelf2[1] = madness.nitroacelf[this.sc[0]][1] * realacelf2[0] / madness.nitroacelf[this.sc[0]][0] - 3.0f;
                        realacelf2[2] = madness.nitroacelf[this.sc[0]][2] * realacelf2[0] / madness.nitroacelf[this.sc[0]][0] - 2.0f;
                        estat[2] = (realacelf2[0] * 21.0f + realacelf2[1] * 6.0f + realacelf2[2] * 3.0f) / 201.0f;
                        estat[3] = (madness.gripreset[this.sc[0]] + madness.aigripsp[this.sc[0]] * 0.2f - 10.0f) / 20.0f;
                        estat[4] = (madness.aircreset[this.sc[0]] + madness.aistusp[this.sc[0]] + (madness.airsreset[this.sc[0]] + madness.aistusp[this.sc[0]] * 0.025f) * 10.0f) / 125.0f;
                        estat[5] = (madness.momentreset[this.sc[0]] + madness.aistrsp[this.sc[0]] * 0.025f) / 2.1f;
                        estat[6] = this.outdam[this.sc[0]] + madness.aiendsp[this.sc[0]] * 0.01f;
                        estat[0] = (estat[1] + estat[2] + estat[3] + estat[4] + estat[5] + estat[6]) / 6.0f;
                    }
                    else {
                        for (int a4 = 0; a4 < 7; ++a4) {
                            this.colorcode[a4] = 1;
                            if (a4 > 0) {
                                estat[a4] = this.specialstats[this.sc[0]][this.statsalc[this.sc[0]][a4 - 1]][a4 - 1] / 20.0f;
                            }
                        }
                        for (int a4 = 0; a4 < 6; ++a4) {
                            this.writenames(a4, this.statsalc[this.sc[0]][a4]);
                        }
                    }
                    int whichlim = 0;
                    if (this.nclicked) {
                        whichlim = 1;
                    }
                    for (int a5 = whichlim; a5 < 7; ++a5) {
                        if (!this.stopflashing[a5]) {
                            this.fbar[a5] = (int)(estat[a5] * 163.0f);
                            if (this.fbar[a5] < 15 && a5 == 1 && !this.nclicked) {
                                this.fbar[a5] = 15;
                            }
                            this.stopflashing[a5] = true;
                        }
                        this.stat[a5] = (int)(estat[a5] * 100.0f);
                        if (this.nclicked) {
                            this.stat[a5] = (int)(estat[a5] * 20.0f);
                        }
                        fdack[a5] = this.fbar[a5];
                        if (fdack[a5] > 163) {
                            if (this.fbar[a5] / 163 <= 7) {
                                final int[] array2 = fdack;
                                final int n11 = a5;
                                array2[n11] -= 163 * (fdack[a5] / 163);
                            }
                            else {
                                fdack[a5] = 163;
                                this.colorcode[a5] = 7;
                            }
                        }
                        if (!this.nclicked) {
                            this.colorcode[a5] = (int)(this.fbar[a5] / 163.01);
                        }
                        if (this.colorcode[a5] == 0) {
                            this.rd.setColor(new Color(0, 130, 0));
                        }
                        if (this.colorcode[a5] == 1) {
                            this.rd.setColor(new Color(130, 0, 0));
                        }
                        if (this.colorcode[a5] == 2) {
                            this.rd.setColor(new Color(0, 95, 95));
                        }
                        if (this.colorcode[a5] == 3) {
                            this.rd.setColor(new Color(130, 0, 130));
                        }
                        if (this.colorcode[a5] == 4) {
                            this.rd.setColor(new Color(70, 70, 0));
                        }
                        if (this.colorcode[a5] == 5) {
                            this.rd.setColor(new Color(0, 0, 130));
                        }
                        if (this.colorcode[a5] == 6) {
                            this.rd.setColor(new Color(140, 80, 140));
                        }
                        if (this.colorcode[a5] == 7) {
                            this.rd.setColor(new Color(80, 80, 80));
                        }
                        if (a5 < 4) {
                            this.rd.fillRect(175, 362 + a5 * 30, fdack[a5], 18);
                        }
                        else {
                            this.rd.fillRect(585, 272 + a5 * 30, fdack[a5], 18);
                        }
                    }
                    this.rd.setColor(new Color(252, 206, 0));
                    final String[] central2 = { "12", "12", "12", "12", "12", "12", "12" };
                    for (int a6 = 0; a6 < 7; ++a6) {
                        if (a6 > 1) {
                            if (this.stat[a6] >= 100) {
                                central2[a6 - 2] = "120";
                            }
                            else {
                                central2[a6 - 2] = "12";
                            }
                        }
                        else if (a6 == 0 || this.nclicked) {
                            if (this.stat[a6] >= 100) {
                                central2[a6 + 5] = "120";
                            }
                            else {
                                central2[a6 + 5] = "12";
                            }
                        }
                    }
                    if (!this.nclicked) {
                        this.rd.drawString((madness.nitroswits[this.sc[0]][2] + madness.aitssp[this.sc[0]]) / 2 + " MPH", 219, 407);
                    }
                    else {
                        this.rd.drawString(new StringBuilder().append(this.stat[1]).toString(), 256 - this.ftm.stringWidth(central2[6]) / 2, 407);
                    }
                    this.rd.drawString(new StringBuilder().append(this.stat[2]).toString(), 256 - this.ftm.stringWidth(central2[0]) / 2, 437);
                    this.rd.drawString(new StringBuilder().append(this.stat[3]).toString(), 256 - this.ftm.stringWidth(central2[1]) / 2, 467);
                    this.rd.drawString(new StringBuilder().append(this.stat[4]).toString(), 666 - this.ftm.stringWidth(central2[2]) / 2, 407);
                    this.rd.drawString(new StringBuilder().append(this.stat[5]).toString(), 666 - this.ftm.stringWidth(central2[3]) / 2, 437);
                    this.rd.drawString(new StringBuilder().append(this.stat[6]).toString(), 666 - this.ftm.stringWidth(central2[4]) / 2, 467);
                    if (!this.nclicked) {
                        this.rd.drawString(new StringBuilder().append(this.stat[0]).toString(), 256 - this.ftm.stringWidth(central2[5]) / 2, 377);
                    }
                    this.rd.setColor(new Color(245, 245, 245));
                    this.rd.drawRect(584, 391, 165, 20);
                    this.rd.drawRect(585, 392, 163, 18);
                    this.rd.drawRect(584, 421, 165, 20);
                    this.rd.drawRect(585, 422, 163, 18);
                    this.rd.drawRect(584, 451, 165, 20);
                    this.rd.drawRect(585, 452, 163, 18);
                    if (!this.nclicked) {
                        this.rd.drawRect(174, 361, 165, 20);
                        this.rd.drawRect(175, 362, 163, 18);
                    }
                    this.rd.drawRect(174, 391, 165, 20);
                    this.rd.drawRect(175, 392, 163, 18);
                    this.rd.drawRect(174, 421, 165, 20);
                    this.rd.drawRect(175, 422, 163, 18);
                    this.rd.drawRect(174, 451, 165, 20);
                    this.rd.drawRect(175, 452, 163, 18);
                    madness.tsstat[this.sc[0]] = this.stat[0];
                    madness.accstat[this.sc[0]] = this.stat[1];
                    madness.gristat[this.sc[0]] = this.stat[2];
                    madness.stustat[this.sc[0]] = this.stat[3];
                    madness.strstat[this.sc[0]] = this.stat[4];
                    madness.endstat[this.sc[0]] = this.stat[5];
                    if (this.showboosts[0] || this.showboosts[1] || this.showboosts[2] || this.showboosts[3] || this.showboosts[4] || this.showboosts[5]) {
                        if (!this.nclicked) {
                            final Polygon boost = new Polygon();
                            int heightmod = 45;
                            if (this.showboosts[0] || this.showboosts[4] || this.showboosts[5]) {
                                heightmod = 15;
                            }
                            if (this.showboosts[1]) {
                                heightmod = 52;
                            }
                            if (this.showboosts[2]) {
                                heightmod = 60;
                            }
                            final int lengthmod = 0;
                            boost.addPoint(18, 160);
                            boost.addPoint(10, 160 + heightmod);
                            boost.addPoint(18, 160 + heightmod * 2);
                            boost.addPoint(240 + lengthmod, 160 + heightmod * 2);
                            boost.addPoint(248 + lengthmod, 160 + heightmod);
                            boost.addPoint(240 + lengthmod, 160);
                            this.rd.setColor(new Color(0, 0, 0, 220));
                            this.rd.fillPolygon(boost);
                            this.rd.setFont(this.adventure.deriveFont(1, 13.0f));
                            this.ftm = this.rd.getFontMetrics();
                            if (this.showboosts[0]) {
                                this.rd.setColor(new Color(235, 235, 235));
                                this.rd.drawString("+ overall speed", 27, 179);
                            }
                            if (this.showboosts[1]) {
                                this.rd.setColor(new Color(235, 235, 235));
                                this.rd.drawString("+ acceleration", 27, 176);
                                this.rd.setColor(new Color(240, 180, 180));
                                this.rd.drawString("+ reversing speed*", 27, 193);
                                this.rd.drawString("+ time spent at MAX power*", 27, 210);
                                this.rd.drawString("+ power efficiency*", 27, 227);
                                this.rd.setFont(new Font("Arial", 1, 11));
                                this.rd.drawString("* only affected by stat points.", 27, 254);
                                this.rd.setFont(this.adventure.deriveFont(1, 13.0f));
                            }
                            if (this.showboosts[2]) {
                                this.rd.setColor(new Color(235, 235, 235));
                                this.rd.drawString("+ grip on the ground", 27, 176);
                                this.rd.drawString("+ debuff power/resistance", 27, 193);
                                this.rd.drawString("+ stage hazard protection", 27, 210);
                                this.rd.setColor(new Color(240, 180, 180));
                                this.rd.drawString("+ braking power*", 27, 227);
                                this.rd.drawString("+ turning sensitivity*", 27, 244);
                                this.rd.setFont(new Font("Arial", 1, 11));
                                this.rd.drawString("* only affected by stat points.", 27, 271);
                                this.rd.setFont(this.adventure.deriveFont(1, 13.0f));
                            }
                            if (this.showboosts[3]) {
                                this.rd.setColor(new Color(235, 235, 235));
                                this.rd.drawString("+ distance reached in air", 27, 176);
                                this.rd.drawString("+ stunting speed", 27, 193);
                                this.rd.setColor(new Color(240, 180, 180));
                                this.rd.drawString("+ special attack duration*", 27, 210);
                                this.rd.setFont(new Font("Arial", 1, 11));
                                this.rd.drawString("* only affected by stat points.", 27, 237);
                                this.rd.setFont(this.adventure.deriveFont(1, 13.0f));
                            }
                            if (this.showboosts[4]) {
                                this.rd.setColor(new Color(235, 235, 235));
                                this.rd.drawString("+ damage dealt", 27, 179);
                            }
                            if (this.showboosts[5]) {
                                this.rd.setColor(new Color(235, 235, 235));
                                this.rd.drawString("+ maximum health", 27, 179);
                            }
                        }
                        else {
                            for (int a6 = 0; a6 < 6; ++a6) {
                                this.writeboosts(a6, this.statsalc[this.sc[0]][a6]);
                            }
                        }
                    }
                    if (this.savedatah || this.savefase > 0) {
                        this.rd.setColor(new Color(255, 255, 255, 255));
                    }
                    else {
                        this.rd.setColor(new Color(255, 255, 255, 200));
                    }
                    boolean statshow = false;
                    for (int a7 = 0; a7 < 6; ++a7) {
                        if (this.showboosts[a7]) {
                            statshow = true;
                        }
                    }
                    if (this.shufflefase < 5) {
                        if (!this.nclicked && !statshow) {
                            final int howmany = this.lines;
                            final Polygon omg = new Polygon();
                            omg.addPoint(10, 105 + howmany * 15);
                            omg.addPoint(18, 80 + howmany * 15);
                            omg.addPoint(200, 80 + howmany * 15);
                            omg.addPoint(208, 105 + howmany * 15);
                            omg.addPoint(200, 130 + howmany * 15);
                            omg.addPoint(18, 130 + howmany * 15);
                            this.rd.fillPolygon(omg);
                        }
                        this.rd.setFont(this.adventure.deriveFont(1, 20.0f));
                        this.ftm = this.rd.getFontMetrics();
                        this.rd.setColor(new Color(0, 0, 0));
                        if (this.savefase == 0 && !this.nclicked && !statshow) {
                            final int howmany = this.lines;
                            if (this.boncomp[3] == 0) {
                                this.rd.drawString("RESET CAR", 109 - this.ftm.stringWidth("RESET CAR") / 2, 113 + howmany * 15);
                            }
                            else {
                                this.rd.drawString("SELL CAR", 109 - this.ftm.stringWidth("SELL CAR") / 2, 113 + howmany * 15);
                            }
                        }
                    }
                    if (this.savefase == 1) {
                        if (!this.nclicked) {
                            this.rd.setFont(this.adventure.deriveFont(1, 12.3f));
                            this.ftm = this.rd.getFontMetrics();
                            int carprice = madness.level[this.sc[0]] / 3;
                            if (this.boncomp[3] == 0) {
                                carprice = 0;
                                this.rd.drawString("are you sure? You get no", 20, 102 + this.lines * 15);
                                this.rd.drawString("stat points from this!", 20, 117 + this.lines * 15);
                            }
                            else {
                                final String firstline = "you will get " + carprice + " car";
                                final String secondline = "points. Continue?";
                                this.rd.drawString(new StringBuilder().append(firstline).toString(), 109 - this.ftm.stringWidth(new StringBuilder().append(firstline).toString()) / 2, 102 + this.lines * 15);
                                this.rd.drawString(new StringBuilder().append(secondline).toString(), 109 - this.ftm.stringWidth(new StringBuilder().append(secondline).toString()) / 2, 117 + this.lines * 15);
                            }
                            final Polygon zomg = new Polygon();
                            zomg.addPoint(10, 165 + this.lines * 15);
                            zomg.addPoint(18, 145 + this.lines * 15);
                            zomg.addPoint(90, 145 + this.lines * 15);
                            zomg.addPoint(98, 165 + this.lines * 15);
                            zomg.addPoint(90, 185 + this.lines * 15);
                            zomg.addPoint(18, 185 + this.lines * 15);
                            if (this.resetoption[0]) {
                                this.rd.setColor(new Color(255, 255, 255, 255));
                            }
                            else {
                                this.rd.setColor(new Color(255, 255, 255, 210));
                            }
                            this.rd.fillPolygon(zomg);
                            final Polygon z0mg = new Polygon();
                            z0mg.addPoint(121, 165 + this.lines * 15);
                            z0mg.addPoint(129, 145 + this.lines * 15);
                            z0mg.addPoint(201, 145 + this.lines * 15);
                            z0mg.addPoint(209, 165 + this.lines * 15);
                            z0mg.addPoint(201, 185 + this.lines * 15);
                            z0mg.addPoint(129, 185 + this.lines * 15);
                            if (this.resetoption[1]) {
                                this.rd.setColor(new Color(255, 255, 255, 255));
                            }
                            else {
                                this.rd.setColor(new Color(255, 255, 255, 210));
                            }
                            this.rd.fillPolygon(z0mg);
                            this.rd.setColor(new Color(0, 0, 0));
                            this.rd.setFont(this.adventure.deriveFont(1, 20.0f));
                            this.ftm = this.rd.getFontMetrics();
                            this.rd.drawString("YES", 54 - this.ftm.stringWidth("YES") / 2, 173 + this.lines * 15);
                            this.rd.drawString("NO", 165 - this.ftm.stringWidth("NO") / 2, 173 + this.lines * 15);
                        }
                    }
                    else if (!this.showboosts[0] && !this.showboosts[1] && !this.showboosts[2] && !this.showboosts[3] && !this.showboosts[4] && !this.showboosts[5] && !this.nclicked) {
                        int statloss = (int)(this.extpoints[this.sc[0]] * 0.35);
                        if (statloss < 1 && this.extpoints[this.sc[0]] > 0) {
                            statloss = 1;
                        }
                        if (this.statchangers[0] > 0) {
                            statloss = 0;
                        }
                        int omgtrans = 200;
                        if (this.shufflehover) {
                            omgtrans = 255;
                        }
                        int statctrans = 200;
                        if (this.carqhover[0]) {
                            statctrans = 255;
                        }
                        this.rd.setColor(new Color(0, 0, 0));
                        if (this.shufflefase == 0) {
                            this.rd.setColor(new Color(255, 255, 255, omgtrans));
                            final Polygon omg2 = new Polygon();
                            omg2.addPoint(10, 175 + this.lines * 15);
                            omg2.addPoint(18, 150 + this.lines * 15);
                            omg2.addPoint(200, 150 + this.lines * 15);
                            omg2.addPoint(208, 175 + this.lines * 15);
                            omg2.addPoint(200, 200 + this.lines * 15);
                            omg2.addPoint(18, 200 + this.lines * 15);
                            this.rd.fillPolygon(omg2);
                            this.rd.setFont(this.adventure.deriveFont(1, 20.0f));
                            this.ftm = this.rd.getFontMetrics();
                            this.rd.setColor(new Color(0, 0, 0));
                            this.rd.drawString("CHANGE STATS", 109 - this.ftm.stringWidth("CHANGE STATS") / 2, 183 + this.lines * 15);
                            final Polygon statq = new Polygon();
                            statq.addPoint(219, 175 + this.lines * 15);
                            statq.addPoint(226, 160 + this.lines * 15);
                            statq.addPoint(250, 160 + this.lines * 15);
                            statq.addPoint(257, 175 + this.lines * 15);
                            statq.addPoint(250, 190 + this.lines * 15);
                            statq.addPoint(226, 190 + this.lines * 15);
                            this.rd.setColor(new Color(255, 255, 255, statctrans));
                            this.rd.fillPolygon(statq);
                            this.rd.setColor(new Color(0, 0, 0));
                            this.rd.setFont(this.adventure.deriveFont(1, 20.0f));
                            this.ftm = this.rd.getFontMetrics();
                            this.rd.drawString("?", 238 - this.ftm.stringWidth("?") / 2, 183 + this.lines * 15);
                            if (this.carqhover[0]) {
                                final Polygon infobox = new Polygon();
                                infobox.addPoint(268, 220 + this.lines * 15);
                                infobox.addPoint(278, 230 + this.lines * 15);
                                infobox.addPoint(445, 230 + this.lines * 15);
                                infobox.addPoint(458, 220 + this.lines * 15);
                                infobox.addPoint(458, 130 + this.lines * 15);
                                infobox.addPoint(448, 120 + this.lines * 15);
                                infobox.addPoint(278, 120 + this.lines * 15);
                                infobox.addPoint(268, 130 + this.lines * 15);
                                this.rd.setColor(new Color(0, 0, 0, 230));
                                this.rd.fillPolygon(infobox);
                                this.rd.setColor(new Color(255, 255, 255));
                                this.rd.setFont(this.adventure.deriveFont(1, 12.0f));
                                this.ftm = this.rd.getFontMetrics();
                                this.rd.drawString("This lets you either", 281, 141 + this.lines * 15);
                                this.rd.drawString("reshuffle the stats of", 281, 159 + this.lines * 15);
                                this.rd.drawString("your car, or transfer", 281, 177 + this.lines * 15);
                                this.rd.drawString("your level and stat", 281, 195 + this.lines * 15);
                                this.rd.drawString("points to another car.", 281, 213 + this.lines * 15);
                            }
                        }
                        if (this.shufflefase == 1) {
                            final int[] stattrans = new int[4];
                            final Polygon[] omg3 = new Polygon[4];
                            for (int a8 = 0; a8 < 2; ++a8) {
                                stattrans[a8 + 2] = (stattrans[a8] = 200);
                                if (this.carqhover[a8 + 1]) {
                                    stattrans[a8] = 255;
                                }
                                if (this.statchangers[1] == 0) {
                                    stattrans[1] = 150;
                                }
                                if (this.carqhover[a8 + 3]) {
                                    stattrans[a8 + 2] = 255;
                                }
                                (omg3[a8] = new Polygon()).addPoint(10, 165 + this.lines * 15 + a8 * 40);
                                omg3[a8].addPoint(18, 150 + this.lines * 15 + a8 * 40);
                                omg3[a8].addPoint(200, 150 + this.lines * 15 + a8 * 40);
                                omg3[a8].addPoint(208, 165 + this.lines * 15 + a8 * 40);
                                omg3[a8].addPoint(200, 180 + this.lines * 15 + a8 * 40);
                                omg3[a8].addPoint(18, 180 + this.lines * 15 + a8 * 40);
                                this.rd.setColor(new Color(255, 255, 255, stattrans[a8]));
                                this.rd.fillPolygon(omg3[a8]);
                                (omg3[a8 + 2] = new Polygon()).addPoint(219, 165 + this.lines * 15 + a8 * 40);
                                omg3[a8 + 2].addPoint(226, 150 + this.lines * 15 + a8 * 40);
                                omg3[a8 + 2].addPoint(250, 150 + this.lines * 15 + a8 * 40);
                                omg3[a8 + 2].addPoint(257, 165 + this.lines * 15 + a8 * 40);
                                omg3[a8 + 2].addPoint(250, 180 + this.lines * 15 + a8 * 40);
                                omg3[a8 + 2].addPoint(226, 180 + this.lines * 15 + a8 * 40);
                                this.rd.setColor(new Color(255, 255, 255, stattrans[a8 + 2]));
                                this.rd.fillPolygon(omg3[a8 + 2]);
                                this.rd.setColor(new Color(0, 0, 0));
                                this.rd.setFont(this.adventure.deriveFont(1, 20.0f));
                                this.ftm = this.rd.getFontMetrics();
                                this.rd.drawString("?", 238 - this.ftm.stringWidth("?") / 2, 173 + this.lines * 15 + a8 * 40);
                                if (this.carqhover[a8 + 3]) {
                                    final Polygon infobox2 = new Polygon();
                                    infobox2.addPoint(268, 210 + this.lines * 15 + a8 * 40);
                                    infobox2.addPoint(278, 220 + this.lines * 15 + a8 * 40);
                                    infobox2.addPoint(448, 220 + this.lines * 15 + a8 * 40);
                                    infobox2.addPoint(458, 210 + this.lines * 15 + a8 * 40);
                                    infobox2.addPoint(458, 120 + this.lines * 15 + a8 * 40);
                                    infobox2.addPoint(448, 110 + this.lines * 15 + a8 * 40);
                                    infobox2.addPoint(278, 110 + this.lines * 15 + a8 * 40);
                                    infobox2.addPoint(268, 120 + this.lines * 15 + a8 * 40);
                                    this.rd.setColor(new Color(0, 0, 0, 230));
                                    this.rd.fillPolygon(infobox2);
                                }
                            }
                            if (this.carqhover[3]) {
                                this.rd.setColor(new Color(255, 255, 255));
                                this.rd.setFont(this.adventure.deriveFont(1, 12.0f));
                                this.ftm = this.rd.getFontMetrics();
                                this.rd.drawString("Unlocking a new stage", 281, 131 + this.lines * 15);
                                this.rd.drawString("gives you a free stat", 281, 149 + this.lines * 15);
                                this.rd.drawString("reshuffle, but costs", 281, 167 + this.lines * 15);
                                this.rd.drawString("stat points otherwise.", 281, 185 + this.lines * 15);
                                if (this.statchangers[0] == 0) {
                                    this.rd.setColor(new Color(255, 0, 0));
                                    this.rd.drawString("No free reshuffles left.", 281, 207 + this.lines * 15);
                                }
                                else {
                                    this.rd.setColor(new Color(0, 255, 0));
                                    if (this.statchangers[0] == 1) {
                                        this.rd.drawString("1 free reshuffle left.", 281, 207 + this.lines * 15);
                                    }
                                    else {
                                        this.rd.drawString(this.statchangers[0] + " free reshuffles left.", 281, 207 + this.lines * 15);
                                    }
                                }
                            }
                            if (this.carqhover[4]) {
                                this.rd.setColor(new Color(255, 255, 255));
                                this.rd.setFont(this.adventure.deriveFont(1, 12.0f));
                                this.ftm = this.rd.getFontMetrics();
                                this.rd.drawString("Unlocking a new stage", 281, 171 + this.lines * 15);
                                this.rd.drawString("lets you transfer your", 281, 189 + this.lines * 15);
                                this.rd.drawString("level and stat points", 281, 207 + this.lines * 15);
                                this.rd.drawString("to another car once.", 281, 225 + this.lines * 15);
                                if (this.statchangers[1] == 0) {
                                    this.rd.setColor(new Color(255, 0, 0));
                                    this.rd.drawString("No level transfers left.", 281, 247 + this.lines * 15);
                                }
                                else {
                                    this.rd.setColor(new Color(0, 255, 0));
                                    if (this.statchangers[1] == 1) {
                                        this.rd.drawString("1 level transfer left.", 281, 247 + this.lines * 15);
                                    }
                                    else {
                                        this.rd.drawString(this.statchangers[0] + " level transfers left.", 281, 247 + this.lines * 15);
                                    }
                                }
                            }
                            this.rd.setColor(new Color(0, 0, 0));
                            this.rd.setFont(this.adventure.deriveFont(1, 16.0f));
                            this.ftm = this.rd.getFontMetrics();
                            this.rd.drawString("RESHUFFLE STATS", 109 - this.ftm.stringWidth("RESHUFFLE STATS") / 2, 171 + this.lines * 15);
                            this.rd.drawString("LEVEL TRANSFER", 109 - this.ftm.stringWidth("LEVEL TRANSFER") / 2, 211 + this.lines * 15);
                        }
                        if (this.shufflefase == 2 || this.shufflefase == 3) {
                            this.rd.setColor(new Color(255, 255, 255));
                            final Polygon omg2 = new Polygon();
                            omg2.addPoint(10, 184 + this.lines * 15);
                            omg2.addPoint(18, 150 + this.lines * 15);
                            omg2.addPoint(240, 150 + this.lines * 15);
                            omg2.addPoint(248, 184 + this.lines * 15);
                            omg2.addPoint(240, 218 + this.lines * 15);
                            omg2.addPoint(18, 218 + this.lines * 15);
                            this.rd.fillPolygon(omg2);
                            final Polygon[] omg4 = new Polygon[2];
                            for (int a8 = 0; a8 < 2; ++a8) {
                                (omg4[a8] = new Polygon()).addPoint(260, 165 + this.lines * 15 + a8 * 36);
                                omg4[a8].addPoint(268, 150 + this.lines * 15 + a8 * 36);
                                omg4[a8].addPoint(350, 150 + this.lines * 15 + a8 * 36);
                                omg4[a8].addPoint(358, 165 + this.lines * 15 + a8 * 36);
                                omg4[a8].addPoint(350, 180 + this.lines * 15 + a8 * 36);
                                omg4[a8].addPoint(268, 180 + this.lines * 15 + a8 * 36);
                                int loltrans = 200;
                                if (this.shufophover[a8]) {
                                    loltrans = 255;
                                }
                                this.rd.setColor(new Color(255, 255, 255, loltrans));
                                this.rd.fillPolygon(omg4[a8]);
                            }
                            this.rd.setFont(this.adventure.deriveFont(1, 17.0f));
                            this.ftm = this.rd.getFontMetrics();
                            this.rd.setColor(new Color(0, 0, 0));
                            this.rd.drawString("YES", 309 - this.ftm.stringWidth("YES") / 2, 171 + this.lines * 15);
                            this.rd.drawString("NO", 309 - this.ftm.stringWidth("NO") / 2, 207 + this.lines * 15);
                        }
                        if (this.shufflefase == 2) {
                            this.rd.setFont(this.adventure.deriveFont(1, 13.0f));
                            this.ftm = this.rd.getFontMetrics();
                            this.rd.setColor(new Color(0, 0, 0));
                            final String sndline = "back but at the cost of " + statloss + ".";
                            final String trdline = "do you want to continue?";
                            this.rd.drawString("you'll get your stat points", 129 - this.ftm.stringWidth("you'll get your stat points") / 2, 170 + this.lines * 15);
                            this.rd.drawString(sndline, 129 - this.ftm.stringWidth(sndline) / 2, 188 + this.lines * 15);
                            this.rd.drawString(trdline, 129 - this.ftm.stringWidth(trdline) / 2, 206 + this.lines * 15);
                        }
                        if (this.shufflefase == 3) {
                            this.rd.setFont(this.adventure.deriveFont(1, 13.0f));
                            this.ftm = this.rd.getFontMetrics();
                            this.rd.setColor(new Color(0, 0, 0));
                            final String[] txtline = { "you will reset this car and", "transfer its level and stat", "points. Continue?" };
                            for (int a9 = 0; a9 < 3; ++a9) {
                                this.rd.drawString(txtline[a9], 129 - this.ftm.stringWidth(txtline[a9]) / 2, 170 + this.lines * 15 + a9 * 18);
                            }
                        }
                        if (this.shufflefase == 4) {
                            final int[] extpoints = this.extpoints;
                            final int n12 = this.sc[0];
                            extpoints[n12] -= statloss;
                            if (this.extpoints[this.sc[0]] < 0) {
                                this.extpoints[this.sc[0]] = 0;
                            }
                            this.statpoints[this.sc[0]] = this.spcalc(madness.level[this.sc[0]]) + this.extpoints[this.sc[0]];
                            final int resetto = madness.level[this.sc[0]] - 1;
                            if (this.sc[0] >= 31) {
                                this.resetstats(madness, this.sc[0]);
                            }
                            else {
                                madness.aitssp[this.sc[0]] = resetto;
                                madness.aiaccsp[this.sc[0]] = resetto;
                                madness.aistusp[this.sc[0]] = resetto;
                                madness.aistrsp[this.sc[0]] = resetto;
                                madness.aigripsp[this.sc[0]] = resetto;
                                madness.aiendsp[this.sc[0]] = resetto;
                            }
                            for (int a9 = 0; a9 < 7; ++a9) {
                                this.stopflashing[a9] = false;
                                this.colorcode[a9] = 0;
                            }
                            if (this.statchangers[0] > 0) {
                                final int[] statchangers = this.statchangers;
                                final int n13 = 0;
                                --statchangers[n13];
                            }
                            this.shufflefase = 0;
                        }
                        if (this.shufflefase == 5) {
                            if (this.transfercar[0] == -1) {
                                this.transfercar[0] = this.sc[0];
                            }
                            this.transfercar[1] = this.sc[0];
                            this.rd.setFont(this.adventure.deriveFont(1, 16.0f));
                            this.ftm = this.rd.getFontMetrics();
                            final String[] txtline = new String[2];
                            if (this.transfercar[0] == this.transfercar[1]) {
                                txtline[0] = "select car to";
                                txtline[1] = "transfer to...";
                            }
                            else {
                                txtline[0] = "transfer to";
                                txtline[1] = String.valueOf(this.names[this.sc[0]]) + "?";
                            }
                            final int maxlength = Math.max(this.ftm.stringWidth(txtline[0]), this.ftm.stringWidth(txtline[1]));
                            this.rd.setColor(new Color(255, 255, 255));
                            final Polygon omg5 = new Polygon();
                            omg5.addPoint(93 - maxlength / 2, 150 + this.lines * 15);
                            omg5.addPoint(101 - maxlength / 2, 125 + this.lines * 15);
                            omg5.addPoint(117 + maxlength / 2, 125 + this.lines * 15);
                            omg5.addPoint(125 + maxlength / 2, 150 + this.lines * 15);
                            omg5.addPoint(117 + maxlength / 2, 175 + this.lines * 15);
                            omg5.addPoint(101 - maxlength / 2, 175 + this.lines * 15);
                            this.rd.fillPolygon(omg5);
                            this.rd.setColor(new Color(0, 0, 0));
                            for (int a10 = 0; a10 < 2; ++a10) {
                                this.rd.drawString(txtline[a10], 109 - this.ftm.stringWidth(txtline[a10]) / 2, 147 + this.lines * 15 + a10 * 18);
                            }
                            if (control.handb || control.enter) {
                                if (this.transfercar[0] != this.transfercar[1]) {
                                    this.shufflefase = 6;
                                }
                                control.enter = false;
                                control.handb = false;
                            }
                        }
                        if (this.shufflefase == 6) {
                            if (!this.carqhover[5] && !this.carqhover[6]) {
                                this.carqhover[6] = true;
                            }
                            final Polygon infobox3 = new Polygon();
                            infobox3.addPoint(275, 260);
                            infobox3.addPoint(285, 270);
                            infobox3.addPoint(585, 270);
                            infobox3.addPoint(595, 260);
                            infobox3.addPoint(595, 140);
                            infobox3.addPoint(585, 130);
                            infobox3.addPoint(285, 130);
                            infobox3.addPoint(275, 140);
                            this.rd.setColor(new Color(0, 0, 0, 225));
                            this.rd.fillPolygon(infobox3);
                            final Polygon[] yesno = new Polygon[2];
                            final String[] opstring = { "YES", "NO" };
                            for (int a10 = 0; a10 < 2; ++a10) {
                                (yesno[a10] = new Polygon()).addPoint(285 + a10 * 300, 310);
                                yesno[a10].addPoint(295 + a10 * 280, 320);
                                yesno[a10].addPoint(410 + a10 * 50, 320);
                                yesno[a10].addPoint(420 + a10 * 30, 310);
                                yesno[a10].addPoint(420 + a10 * 30, 290);
                                yesno[a10].addPoint(410 + a10 * 50, 280);
                                yesno[a10].addPoint(295 + a10 * 280, 280);
                                yesno[a10].addPoint(285 + a10 * 300, 290);
                                int yesnotrans = 150;
                                if (this.carqhover[a10 + 5]) {
                                    yesnotrans = 255;
                                }
                                this.rd.setColor(new Color(0, 0, 0, yesnotrans));
                                this.rd.fillPolygon(yesno[a10]);
                                this.rd.setFont(this.adventure.deriveFont(1, 20.0f));
                                this.ftm = this.rd.getFontMetrics();
                                this.rd.setColor(new Color(255, 255, 255));
                                this.rd.drawString(opstring[a10], 350 - this.ftm.stringWidth(opstring[a10]) / 2 + a10 * 170, 307);
                            }
                            if (this.carqhover[5] && (control.left || control.right)) {
                                this.carqhover[6] = true;
                                this.carqhover[5] = false;
                                control.left = false;
                                control.right = false;
                            }
                            if (this.carqhover[6] && (control.left || control.right)) {
                                this.carqhover[5] = true;
                                this.carqhover[6] = false;
                                control.left = false;
                                control.right = false;
                            }
                            if (control.enter || control.handb) {
                                if (this.carqhover[5]) {
                                    this.shufflefase = 7;
                                }
                                if (this.carqhover[6]) {
                                    this.shufflefase = 0;
                                }
                                control.enter = false;
                                control.handb = false;
                            }
                            this.rd.setFont(new Font("Arial", 1, 11));
                            this.ftm = this.rd.getFontMetrics();
                            if (this.aflk) {
                                this.rd.setColor(new Color(240, 240, 240));
                            }
                            else {
                                this.rd.setColor(new Color(176, 176, 176));
                            }
                            final String firsthalf = this.names[this.transfercar[0]] + ", ";
                            final String secondhalf = this.names[this.transfercar[1]] + ", ";
                            final int firstlength = this.ftm.stringWidth(firsthalf);
                            final int secondlength = this.ftm.stringWidth(secondhalf);
                            this.rd.drawString(firsthalf, 288, 170);
                            this.rd.drawString(secondhalf, 288, 214);
                            this.rd.setColor(new Color(255, 255, 255));
                            this.rd.setFont(this.adventure.deriveFont(1, 12.0f));
                            this.ftm = this.rd.getFontMetrics();
                            this.rd.drawString("You will reset your:", 288, 151);
                            this.rd.drawString("And transfer its level and stat points to:", 288, 193);
                            this.rd.setColor(new Color(0, 200, 0));
                            this.rd.drawString("level " + madness.level[this.transfercar[0]], 293 + firstlength, 172);
                            this.rd.drawString("level " + madness.level[this.transfercar[1]], 293 + secondlength, 216);
                            this.rd.setColor(new Color(255, 0, 0));
                            this.rd.drawString("This cannot be undone and will save the", 288, 239);
                            this.rd.drawString("game. Continue?", 288, 257);
                        }
                        if (this.shufflefase == 7) {
                            madness.level[this.sc[0]] = madness.level[this.transfercar[0]];
                            int bspoints = this.extpoints[this.transfercar[0]];
                            final BigDecimal fixbsp = new BigDecimal(this.rebsp[this.transfercar[0]] * (this.xbspratio[this.sc[0]] / this.xbspratio[this.transfercar[0]]));
                            this.rebsp[this.sc[0]] = fixbsp.doubleValue();
                            double adjcap = this.xbspratio[this.sc[0]] / this.xbspratio[this.transfercar[0]];
                            if (this.rebsp[this.sc[0]] > 1.0) {
                                adjcap = 1.0 / this.rebsp[this.transfercar[0]];
                            }
                            if (this.rebsp[this.transfercar[0]] > 1.0) {
                                adjcap *= this.rebsp[this.transfercar[0]];
                            }
                            final BigDecimal fixstat = new BigDecimal(this.extpoints[this.transfercar[0]] * adjcap);
                            bspoints = (int)fixstat.doubleValue();
                            this.statpoints[this.sc[0]] = this.spcalc(madness.level[this.transfercar[0]]) + bspoints;
                            madness.exp[this.sc[0]] = 0;
                            this.killscn[this.sc[0]] = this.killscn[this.transfercar[0]];
                            this.winscn[this.sc[0]] = this.winscn[this.transfercar[0]];
                            this.extpoints[this.sc[0]] = bspoints;
                            final int resetto2 = madness.level[this.transfercar[0]] - 1;
                            if (this.sc[0] >= 31) {
                                this.resetstats(madness, this.sc[0]);
                            }
                            else {
                                madness.aitssp[this.sc[0]] = resetto2;
                                madness.aiaccsp[this.sc[0]] = resetto2;
                                madness.aistusp[this.sc[0]] = resetto2;
                                madness.aistrsp[this.sc[0]] = resetto2;
                                madness.aigripsp[this.sc[0]] = resetto2;
                                madness.aiendsp[this.sc[0]] = resetto2;
                            }
                            for (int a11 = 0; a11 < 7; ++a11) {
                                this.stopflashing[a11] = false;
                                this.colorcode[a11] = 0;
                            }
                            this.shufflefase = 8;
                        }
                        if (this.shufflefase == 8) {
                            this.statpoints[this.transfercar[0]] = 0;
                            this.killscn[this.transfercar[0]] = 0;
                            this.winscn[this.transfercar[0]] = 0;
                            madness.level[this.transfercar[0]] = 1;
                            madness.exp[this.transfercar[0]] = 0;
                            this.extpoints[this.transfercar[0]] = 0;
                            this.xbsp[this.transfercar[0]] = 0.0;
                            this.rebsp[this.transfercar[0]] = 1.0;
                            if (this.transfercar[0] < 31) {
                                madness.aitssp[this.transfercar[0]] = 0;
                                madness.aiaccsp[this.transfercar[0]] = 0;
                                madness.aigripsp[this.transfercar[0]] = 0;
                                madness.aistusp[this.transfercar[0]] = 0;
                                madness.aistrsp[this.transfercar[0]] = 0;
                                madness.aiendsp[this.transfercar[0]] = 0;
                            }
                            else {
                                this.resetstats(madness, this.transfercar[0]);
                            }
                            if (this.statchangers[1] > 0) {
                                final int[] statchangers2 = this.statchangers;
                                final int n14 = 1;
                                --statchangers2[n14];
                            }
                            for (int a12 = 0; a12 < 7; ++a12) {
                                this.carqhover[a12] = false;
                            }
                            for (int a12 = 0; a12 < 38; ++a12) {
                                if (this.rebsp[a12] != 1.0) {
                                    System.out.println(a12 + ", " + this.rebsp[a12]);
                                }
                            }
                            this.shufflefase = 9;
                        }
                        this.rd.setFont(new Font("Arial", 1, 11));
                        this.ftm = this.rd.getFontMetrics();
                    }
                }
                if (this.shufflefase < 5 || this.transfercar[0] != this.transfercar[1]) {
                    final Polygon ealogic = new Polygon();
                    ealogic.addPoint(695, 12);
                    ealogic.addPoint(690, 17);
                    ealogic.addPoint(690, 45);
                    ealogic.addPoint(695, 50);
                    ealogic.addPoint(835, 50);
                    ealogic.addPoint(850, 31);
                    ealogic.addPoint(835, 12);
                    if (!this.gohover) {
                        this.rd.setColor(new Color(0, 100, 0));
                    }
                    else {
                        this.rd.setColor(new Color(0, 200, 0));
                    }
                    this.rd.fillPolygon(ealogic);
                    this.rd.setFont(this.adventure.deriveFont(1, 22.0f));
                    this.rd.setColor(new Color(235, 235, 235));
                    if (this.shufflefase < 5) {
                        this.rd.drawString("LET'S GO!", 710, 38);
                    }
                    else {
                        this.rd.drawString("TRANSFER", 710, 38);
                    }
                }
                if (this.careermode) {
                    final Polygon zomgnoob = new Polygon();
                    zomgnoob.addPoint(695, 62);
                    zomgnoob.addPoint(690, 67);
                    zomgnoob.addPoint(690, 95);
                    zomgnoob.addPoint(695, 100);
                    zomgnoob.addPoint(835, 100);
                    zomgnoob.addPoint(850, 81);
                    zomgnoob.addPoint(835, 62);
                    if (!this.bchover) {
                        this.rd.setColor(new Color(180, 90, 0, 210));
                    }
                    else {
                        this.rd.setColor(new Color(210, 105, 0, 255));
                    }
                    this.rd.fillPolygon(zomgnoob);
                    this.rd.setColor(new Color(235, 235, 235));
                    if (!this.bclicked) {
                        this.rd.setFont(this.adventure.deriveFont(1, 20.0f));
                        this.rd.drawString("bonus cars", 710, 88);
                    }
                    else {
                        this.rd.setFont(this.adventure.deriveFont(1, 19.0f));
                        this.rd.drawString("normal cars", 702, 88);
                    }
                }
                if (this.boncomp[3] > 0 && this.careermode) {
                    if (this.shufflefase < 5) {
                        final Polygon fuckinghell = new Polygon();
                        fuckinghell.addPoint(695, 112);
                        fuckinghell.addPoint(690, 117);
                        fuckinghell.addPoint(690, 145);
                        fuckinghell.addPoint(695, 150);
                        fuckinghell.addPoint(835, 150);
                        fuckinghell.addPoint(850, 131);
                        fuckinghell.addPoint(835, 112);
                        if (!this.nhover) {
                            this.rd.setColor(new Color(150, 0, 0, 180));
                        }
                        else {
                            this.rd.setColor(new Color(150, 0, 0, 255));
                        }
                        this.rd.fillPolygon(fuckinghell);
                        this.rd.setColor(new Color(235, 235, 235));
                        if (!this.nclicked) {
                            this.rd.setFont(this.adventure.deriveFont(1, 20.0f));
                            this.rd.drawString("extra stats", 702, 138);
                        }
                        else {
                            this.rd.setFont(this.adventure.deriveFont(1, 19.0f));
                            this.rd.drawString("normal stats", 698, 138);
                        }
                    }
                    else {
                        this.nclicked = false;
                    }
                }
                if (this.shufflefase <= 5) {
                    if (this.bclicked && this.sc[0] < 23 && this.sc[0] != 20 && this.sc[0] != 21) {
                        this.flipo = 20;
                    }
                    if (this.oclicked && (this.sc[0] >= 23 || this.sc[0] == 20 || this.sc[0] == 21)) {
                        this.flipo = 20;
                    }
                }
            }
        }
        else {
            this.pback = 0;
            this.pnext = 0;
            this.gatey = 0;
            if (this.flipo > 10) {
                final ContO contO4 = aconto[this.sc[0]];
                contO4.y -= 100;
                if (this.nextc) {
                    final ContO contO5 = aconto[this.sc[0]];
                    contO5.zy += 20;
                }
                else {
                    final ContO contO6 = aconto[this.sc[0]];
                    contO6.zy -= 20;
                }
            }
            else {
                if (this.flipo == 10) {
                    if (!this.bclicked || ((this.sc[0] >= 23 || this.sc[0] == 20 || this.sc[0] == 21) && this.careermode) || this.classicmode) {
                        if (this.nextc) {
                            if (!this.careermode) {
                                final int[] sc = this.sc;
                                final int n15 = 0;
                                ++sc[n15];
                            }
                            else {
                                if (this.sc[0] == 19) {
                                    this.sc[0] = 22;
                                }
                                if (this.sc[0] == 20) {
                                    this.sc[0] = 21;
                                }
                                if (this.sc[0] == 38) {
                                    this.sc[0] = 20;
                                }
                                if (this.sc[0] < 19 || (this.sc[0] >= 23 && this.sc[0] < 38)) {
                                    final int[] sc2 = this.sc;
                                    final int n16 = 0;
                                    ++sc2[n16];
                                }
                                final int n17 = this.sc[0];
                            }
                        }
                        else if (!this.careermode) {
                            final int[] sc3 = this.sc;
                            final int n18 = 0;
                            --sc3[n18];
                        }
                        else {
                            if (this.sc[0] <= 19 || (this.sc[0] > 23 && this.sc[0] <= 38)) {
                                final int[] sc4 = this.sc;
                                final int n19 = 0;
                                --sc4[n19];
                            }
                            final int n20 = this.sc[0];
                            if (this.sc[0] == 22) {
                                this.sc[0] = 19;
                            }
                            if (this.sc[0] == 20) {
                                this.sc[0] = 38;
                            }
                            if (this.sc[0] == 21) {
                                this.sc[0] = 20;
                            }
                        }
                    }
                    else {
                        this.sc[0] = 23;
                    }
                    if (this.oclicked && (this.sc[0] >= 23 || this.sc[0] == 20 || this.sc[0] == 21)) {
                        this.sc[0] = 0;
                    }
                    for (int a = 0; a < 7; ++a) {
                        this.stopflashing[a] = false;
                        this.colorcode[a] = 0;
                    }
                    this.savefase = 0;
                    for (int a = 0; a < 2; ++a) {
                        this.resetoption[a] = false;
                    }
                    aconto[this.sc[0]].z = 950;
                    aconto[this.sc[0]].y = -34 - aconto[this.sc[0]].grat - 1100;
                    aconto[this.sc[0]].x = 0;
                    aconto[this.sc[0]].zy = 0;
                    if (this.shufflefase < 5) {
                        this.shufflefase = 0;
                    }
                }
                final ContO contO7 = aconto[this.sc[0]];
                contO7.y += 100;
            }
            --this.flipo;
        }
        this.rd.setFont(new Font("SansSerif", 1, 11));
        this.ftm = this.rd.getFontMetrics();
        if (control.right) {
            if (!nonext && this.flipo == 0) {
                if (this.flatrstart > 1) {
                    this.flatrstart = 0;
                }
                this.nextc = true;
                this.flipo = 20;
            }
            control.right = false;
        }
        if (control.left) {
            if (!noback && this.flipo == 0) {
                if (this.flatrstart > 1) {
                    this.flatrstart = 0;
                }
                this.nextc = false;
                this.flipo = 20;
            }
            control.left = false;
        }
        if ((control.handb || control.enter) && this.shufflefase < 5) {
            if (!this.notunlocked) {
                this.lastload = -11;
                this.cars.stop();
                this.cars.unloadMod();
                this.m.crs = false;
                this.fase = 6476;
                this.hardstage = false;
                int mxstage = this.unlocked[0];
                if (this.careermode) {
                    mxstage = this.unlocked[1];
                    for (int a13 = 0; a13 < 6; ++a13) {
                        this.statcm[a13] = false;
                    }
                }
                if (!this.classicmode) {
                    if (checkpoints.stage > mxstage) {
                        checkpoints.stage = mxstage;
                    }
                }
                else {
                    checkpoints.stage = (int)(Math.random() * 17.0) + 1;
                }
            }
            control.handb = false;
            control.enter = false;
        }
    }
    
    public void ctachm(final int i, final int j, int k, final Control control, final CheckPoints checkpoints, final Madness madness) {
        if (this.fase == 205) {
            if (k == 1) {
                if (this.over(this.next[0], i, j, 645, 120)) {
                    this.pnext = 1;
                }
                if (this.over(this.back[0], i, j, 155, 120)) {
                    this.pback = 1;
                }
                if (this.over(this.contin[0], i, j, 390, 435)) {
                    this.pcontin = 1;
                }
            }
            if (k == 2) {
                if (this.pnext == 1) {
                    control.right = true;
                }
                if (this.pback == 1) {
                    control.left = true;
                }
                if (this.pcontin == 1) {
                    control.enter = true;
                }
            }
        }
        if (this.fase == 1) {
            int notcm = 0;
            if (!this.careermode) {
                notcm = 40;
            }
            if (k == 1) {
                if (this.over(this.next[0], i, j, 500, 400 + notcm) && checkpoints.stage < this.betalimit) {
                    this.pnext = 1;
                }
                if (this.over(this.back[0], i, j, 310, 400 + notcm)) {
                    this.pback = 1;
                }
                if (this.over(this.contin[0], i, j, 390, 400 + notcm) && checkpoints.stage < this.betalimit) {
                    this.pcontin = 1;
                }
            }
            if (k == 2) {
                if (this.pnext == 1) {
                    control.right = true;
                }
                if (this.pback == 1) {
                    control.left = true;
                }
                if (this.pcontin == 1) {
                    control.enter = true;
                }
            }
            if (this.careermode) {
                if (k == 0) {
                    if (this.overon(23, 15, 184, 34, i, j) && this.clickable) {
                        this.above[2] = true;
                    }
                    else {
                        this.above[2] = false;
                    }
                    if (this.overon(343, 431, 184, 34, i, j)) {
                        this.above[3] = true;
                    }
                    else {
                        this.above[3] = false;
                    }
                    if (this.overon(718, 5, 142, 26, i, j)) {
                        this.xpbuttonhover = true;
                    }
                    else {
                        this.xpbuttonhover = false;
                    }
                    if (checkpoints.stage < this.unlocked[1] && !this.bonstage && this.unlocked[1] >= 3) {
                        for (int a = 0; a < 3; ++a) {
                            if (this.overon(670, 439 - a * 36, 142, 26, i, j)) {
                                this.customise[a][0] = true;
                            }
                            else {
                                this.customise[a][0] = false;
                            }
                            if (this.overon(823, 439 - a * 36, 37, 26, i, j)) {
                                this.customise[a][1] = true;
                            }
                            else {
                                this.customise[a][1] = false;
                            }
                        }
                    }
                }
                if (k == 2) {
                    if (this.above[2]) {
                        this.bonstage = true;
                        this.scalelevels = false;
                        this.nolevels = false;
                        this.hardstage = false;
                        if (checkpoints.stage == 5) {
                            this.bonusstage[0] = true;
                            this.unlimitedlaps = true;
                        }
                        if (checkpoints.stage == 11) {
                            this.bonusstage[1] = true;
                        }
                        if (checkpoints.stage == 15) {
                            this.bonusstage[2] = true;
                            this.unlimitedlaps = true;
                        }
                        if (checkpoints.stage == 18) {
                            this.bonusstage[3] = true;
                        }
                        this.fase = 6476;
                        control.enter = false;
                        control.handb = false;
                        this.clickable = false;
                    }
                    if (this.above[3]) {
                        this.fase = 201;
                    }
                    if (this.customise[0][0]) {
                        this.hardstage = true;
                        this.scalelevels = false;
                        this.nolevels = false;
                        this.fase = 6476;
                    }
                    if (this.customise[1][0]) {
                        if (!this.scalelevels) {
                            this.scalelevels = true;
                        }
                        else {
                            this.scalelevels = false;
                        }
                        this.hardstage = false;
                        this.nolevels = false;
                        this.fase = 6476;
                    }
                    if (this.customise[2][0]) {
                        if (!this.nolevels) {
                            this.nolevels = true;
                            this.averagelevel = 1;
                        }
                        else {
                            this.nolevels = false;
                        }
                        this.scalelevels = false;
                        this.hardstage = false;
                        this.fase = 6476;
                    }
                    if (this.xpbuttonhover) {
                        if (this.disablexp) {
                            this.disablexp = false;
                        }
                        else {
                            this.disablexp = true;
                        }
                    }
                }
            }
            if (k == 0) {
                if (this.overon(30, 415, 139, 22, i, j)) {
                    this.above[0] = true;
                }
                else {
                    this.above[0] = false;
                }
                if (this.overon(30, 443, 139, 22, i, j)) {
                    this.above[1] = true;
                }
                else {
                    this.above[1] = false;
                }
            }
            if (k == 2) {
                if (this.above[0]) {
                    k = 0;
                    this.tomaini = true;
                    this.fase = 1110;
                    this.m.showsnow = false;
                    this.stages.stop();
                    this.stages.unloadMod();
                    control.enter = false;
                    control.handb = false;
                }
                if (this.above[1]) {
                    k = 0;
                    this.fase = 1110;
                    this.tocs = true;
                    this.m.showsnow = false;
                    this.stages.stop();
                    this.stages.unloadMod();
                    control.enter = false;
                    control.handb = false;
                }
            }
        }
        if (this.fase == 0) {
            if (this.autoreplay) {
                this.rd.setFont(this.adventure.deriveFont(1, 22.0f));
                this.ftm = this.rd.getFontMetrics();
                final String message = "replay stage?";
                final int titlelength = this.ftm.stringWidth(message);
                for (int a2 = 0; a2 < 2; ++a2) {
                    if (this.overon(428 - a2 * 66 - titlelength / 2 + a2 * titlelength, 100, 80, 30, i, j)) {
                        this.replayoption = a2 + 1;
                        if (k == 2) {
                            control.enter = true;
                        }
                    }
                }
                this.rd.setFont(new Font("Arial", 1, 11));
                this.ftm = this.rd.getFontMetrics();
            }
            for (int a3 = 0; a3 < 7; ++a3) {
                if (a3 != control.whichkeylock || this.justcs == 6) {
                    control.keylock[a3] = false;
                }
                if (!control.keylock[a3]) {
                    this.stimulateclick[a3] = false;
                    if (control.numpress[a3]) {
                        this.arrowlock[a3] = false;
                        this.alhover[a3] = false;
                        this.arrowlocked = false;
                    }
                }
                if (this.overon(752, 119 + a3 * 30, 113, 25, i, j) || (control.keylock[a3] && !this.stimulateclick[a3])) {
                    if (control.keylock[a3] && !this.stimulateclick[a3]) {
                        k = 2;
                        this.alhover[a3] = true;
                        this.stimulateclick[a3] = true;
                    }
                    if (this.overon(752, 119 + a3 * 30, 113, 25, i, j)) {
                        this.alhover[a3] = true;
                    }
                }
                else {
                    this.alhover[a3] = false;
                }
                if (k == 2) {
                    if (this.alhover[a3] && !this.arrowlocked) {
                        this.arrowlocked = true;
                        this.arrowlock[a3] = true;
                        for (int b = 0; b < 7; ++b) {
                            if (a3 != b) {
                                this.arrowlock[b] = false;
                            }
                        }
                    }
                    if (this.alhover[a3] && this.arrowlocked) {
                        if (this.targetsq[a3]) {
                            this.arrowlocked = false;
                        }
                        else {
                            this.lockonce = false;
                            this.arrowlock[a3] = true;
                            for (int b = 0; b < 7; ++b) {
                                if (a3 != b) {
                                    this.arrowlock[b] = false;
                                }
                            }
                        }
                    }
                }
            }
            for (int a3 = this.nplayers - checkpoints.wasted; a3 < 7; ++a3) {
                this.alhover[a3] = false;
                this.arrowlock[a3] = false;
                control.keylock[a3] = false;
            }
        }
        if (this.fase == 3) {
            if (k == 1 && this.over(this.contin[0], i, j, 390, 365)) {
                this.pcontin = 1;
            }
            if (k == 2 && this.pcontin == 1) {
                control.enter = true;
                this.pcontin = 0;
            }
        }
        if (this.fase == 4) {
            if (k == 1 && this.over(this.back[0], i, j, 405, 360)) {
                this.pback = 1;
            }
            if (k == 2 && this.pback == 1) {
                control.enter = true;
                this.pback = 0;
            }
        }
        if (this.fase == 6) {
            if (k == 1 && (this.over(this.star[0], i, j, 394, 400) || this.over(this.star[0], i, j, 394, 310))) {
                this.pstar = 2;
            }
            if (k == 2 && this.pstar == 2) {
                control.enter = true;
                this.pstar = 1;
            }
        }
        if (this.fase == 500) {
            if (k == 1 && this.over(this.back[0], i, j, 405, 395)) {
                this.pback = 1;
            }
            if (k == 2 && this.pback == 1) {
                this.pback = 0;
                this.fase = 7;
            }
        }
        if (this.fase == 7) {
            if (!this.careermode) {
                this.bclicked = false;
                this.oclicked = false;
            }
            if (this.overon(690, 12, 160, 38, i, j) && (this.shufflefase < 5 || this.transfercar[0] != this.transfercar[1])) {
                this.gohover = true;
            }
            else {
                this.gohover = false;
            }
            if (this.overon(690, 62, 160, 38, i, j) && this.careermode) {
                this.bchover = true;
            }
            else {
                this.bchover = false;
            }
            if (this.overon(690, 112, 160, 38, i, j) && this.boncomp[3] > 0 && this.careermode && this.shufflefase < 5) {
                this.nhover = true;
            }
            else {
                this.nhover = false;
            }
            for (int a3 = 0; a3 < 6; ++a3) {
                if (this.savefase == 0 && this.shufflefase == 0) {
                    if (a3 < 3) {
                        if (this.overon(355, 390 + a3 * 30, 26, 25, i, j)) {
                            this.hoverstat[a3] = true;
                            this.showboosts[a3] = true;
                        }
                        else {
                            this.hoverstat[a3] = false;
                            this.showboosts[a3] = false;
                        }
                    }
                    else if (this.overon(765, 390 + (a3 - 3) * 30, 26, 25, i, j)) {
                        this.hoverstat[a3] = true;
                        this.showboosts[a3] = true;
                    }
                    else {
                        this.hoverstat[a3] = false;
                        this.showboosts[a3] = false;
                    }
                }
                else {
                    this.hoverstat[a3] = false;
                    this.showboosts[a3] = false;
                }
            }
            if (k == 1) {
                if (this.gohover) {
                    control.enter = true;
                }
                if (this.careermode) {
                    if (this.nhover && this.flipo == 0 && !this.notunlocked) {
                        for (int a3 = 0; a3 < 7; ++a3) {
                            this.stopflashing[a3] = false;
                        }
                        if (!this.nclicked) {
                            this.nclicked = true;
                        }
                        else {
                            this.nclicked = false;
                        }
                    }
                    if (this.bchover && this.flipo == 0 && !this.notunlocked) {
                        if (!this.bclicked) {
                            this.oclicked = false;
                            this.bclicked = true;
                        }
                        else {
                            this.oclicked = true;
                            this.bclicked = false;
                        }
                    }
                }
                if (this.over(this.next[0], i, j, 780, 290)) {
                    this.pnext = 1;
                }
                if (this.over(this.back[0], i, j, 30, 290)) {
                    this.pback = 1;
                }
            }
            if (k == 2) {
                if (this.pnext == 1) {
                    control.right = true;
                }
                if (this.pback == 1) {
                    control.left = true;
                }
                for (int a3 = 0; a3 < 6; ++a3) {
                    if (this.hoverstat[a3]) {
                        this.statcm[a3] = true;
                        control.statincrease = true;
                    }
                }
            }
            if (this.careermode) {
                if (k == 0) {
                    if (this.overon(10, 80 + this.lines * 15, 198, 50, i, j) && this.shufflefase == 0) {
                        this.savedatah = true;
                    }
                    else {
                        this.savedatah = false;
                    }
                    if (this.savefase == 1) {
                        if (this.overon(10, 145 + this.lines * 15, 88, 40, i, j)) {
                            this.resetoption[0] = true;
                        }
                        else {
                            this.resetoption[0] = false;
                        }
                        if (this.overon(121, 145 + this.lines * 15, 88, 40, i, j)) {
                            this.resetoption[1] = true;
                        }
                        else {
                            this.resetoption[1] = false;
                        }
                        this.shufflehover = false;
                    }
                    else {
                        boolean nocover = false;
                        if (this.showboosts[0] || this.showboosts[1] || this.showboosts[2] || this.showboosts[3] || this.showboosts[4] || this.showboosts[5]) {
                            nocover = true;
                        }
                        if (!nocover) {
                            if (this.overon(10, 150 + this.lines * 15, 198, 50, i, j) && this.shufflefase == 0) {
                                this.shufflehover = true;
                            }
                            else {
                                this.shufflehover = false;
                            }
                            if (this.overon(219, 160 + this.lines * 15, 38, 30, i, j) && this.shufflefase == 0) {
                                this.carqhover[0] = true;
                            }
                            else {
                                this.carqhover[0] = false;
                            }
                        }
                        if (this.shufflefase == 1) {
                            for (int a = 0; a < 2; ++a) {
                                if (this.overon(10, 150 + this.lines * 15 + a * 40, 198, 30, i, j) && (a != 1 || this.statchangers[1] > 0)) {
                                    this.carqhover[a + 1] = true;
                                }
                                else {
                                    this.carqhover[a + 1] = false;
                                }
                                if (this.overon(219, 150 + this.lines * 15 + a * 40, 38, 30, i, j)) {
                                    this.carqhover[a + 3] = true;
                                }
                                else {
                                    this.carqhover[a + 3] = false;
                                }
                            }
                        }
                        if (this.shufflefase == 2 || this.shufflefase == 3) {
                            if (this.overon(268, 150 + this.lines * 15, 82, 30, i, j)) {
                                this.shufophover[0] = true;
                            }
                            else {
                                this.shufophover[0] = false;
                            }
                            if (this.overon(268, 186 + this.lines * 15, 82, 30, i, j)) {
                                this.shufophover[1] = true;
                            }
                            else {
                                this.shufophover[1] = false;
                            }
                        }
                        if (this.shufflefase == 6) {
                            if (this.overon(285, 280, 135, 40, i, j)) {
                                this.carqhover[6] = false;
                                this.carqhover[5] = true;
                            }
                            if (this.overon(450, 280, 135, 40, i, j)) {
                                this.carqhover[6] = true;
                                this.carqhover[5] = false;
                            }
                        }
                    }
                }
                if (k >= 1 && !this.nclicked) {
                    if (this.savedatah) {
                        this.savefase = 1;
                        this.savedatah = false;
                    }
                    if (this.shufflehover) {
                        this.shufflefase = 1;
                        this.shufflehover = false;
                    }
                    if (this.shufflefase == 6 && (this.overon(285, 280, 135, 40, i, j) || this.overon(450, 280, 135, 40, i, j))) {
                        control.enter = true;
                    }
                    for (int a3 = 1; a3 < 3; ++a3) {
                        if (this.carqhover[a3]) {
                            this.shufflefase = a3 + 1;
                            this.carqhover[a3] = false;
                        }
                    }
                    if (this.shufophover[1]) {
                        this.shufflefase = 0;
                        this.shufophover[1] = false;
                    }
                    if (this.shufophover[0]) {
                        if (this.shufflefase == 3) {
                            this.transfercar[0] = -1;
                            this.transfercar[1] = -1;
                        }
                        this.shufflefase += 2;
                        this.shufophover[0] = false;
                    }
                    if (this.resetoption[1]) {
                        this.savefase = 0;
                        this.resetoption[1] = false;
                    }
                    if (this.resetoption[0]) {
                        final int carprice = madness.level[this.sc[0]] / 3;
                        this.carpoints += carprice;
                        this.statpoints[this.sc[0]] = 0;
                        this.killscn[this.sc[0]] = 0;
                        this.winscn[this.sc[0]] = 0;
                        madness.level[this.sc[0]] = 1;
                        madness.exp[this.sc[0]] = 0;
                        this.extpoints[this.sc[0]] = 0;
                        this.xbsp[this.sc[0]] = 0.0;
                        this.rebsp[this.sc[0]] = 1.0;
                        if (this.sc[0] < 31) {
                            madness.aitssp[this.sc[0]] = 0;
                            madness.aiaccsp[this.sc[0]] = 0;
                            madness.aigripsp[this.sc[0]] = 0;
                            madness.aistusp[this.sc[0]] = 0;
                            madness.aistrsp[this.sc[0]] = 0;
                            madness.aiendsp[this.sc[0]] = 0;
                        }
                        else {
                            this.resetstats(madness, this.sc[0]);
                        }
                        for (int a = 0; a < 7; ++a) {
                            this.stopflashing[a] = false;
                            this.colorcode[a] = 0;
                        }
                        this.savefase = 0;
                        this.resetoption[0] = false;
                    }
                }
                if (this.nclicked) {
                    this.savefase = 0;
                    this.shufflefase = 0;
                }
            }
        }
        if (this.fase == -5) {
            this.lxm = i;
            this.lym = j;
            if (k == 1 && this.over(this.contin[0], i, j, 390, 390 - this.pin)) {
                this.pcontin = 1;
            }
            if (k == 2 && this.pcontin == 1) {
                control.enter = true;
                this.pcontin = 0;
            }
        }
        if (this.fase == -7 && !this.triggerinst) {
            if (k == 1) {
                if (this.overon(364, 45, 137, 22, i, j)) {
                    this.opselect[0] = 0;
                    this.shaded = true;
                }
                if (this.overon(355, 73, 155, 22, i, j)) {
                    this.opselect[0] = 1;
                    this.shaded = true;
                }
                if (this.overon(338, 89, 190, 22, i, j)) {
                    this.opselect[0] = 2;
                    this.shaded = true;
                }
                if (this.overon(376, 125, 109, 22, i, j)) {
                    this.opselect[0] = 3;
                    this.shaded = true;
                }
            }
            if (k == 2 && this.shaded) {
                control.enter = true;
                this.shaded = false;
            }
            if (k == 0 && (i != this.lxm || j != this.lym)) {
                if (this.overon(364, 45, 137, 22, i, j)) {
                    this.opselect[0] = 0;
                }
                if (this.overon(355, 73, 155, 22, i, j)) {
                    this.opselect[0] = 1;
                }
                if (this.overon(338, 99, 190, 22, i, j)) {
                    this.opselect[0] = 2;
                }
                if (this.overon(376, 125, 109, 22, i, j)) {
                    this.opselect[0] = 3;
                }
                this.lxm = i;
                this.lym = j;
            }
        }
        if (this.fase == 49) {
            if (k == 0 && (i != this.lxm || j != this.lym) && this.showopstage == 150) {
                if (this.overon(560, 120, 210, 50, i, j)) {
                    this.namehover[0] = true;
                }
                else {
                    this.namehover[0] = false;
                }
                if (this.overon(560, 195, 210, 50, i, j)) {
                    this.namehover[1] = true;
                }
                else {
                    this.namehover[1] = false;
                }
                if (this.overon(560, 270, 210, 50, i, j)) {
                    this.namehover[2] = true;
                }
                else {
                    this.namehover[2] = false;
                }
                if (this.overon(560, 345, 210, 50, i, j)) {
                    this.namehover[3] = true;
                }
                else {
                    this.namehover[3] = false;
                }
            }
            if (this.aprogress) {
                if (k == 1 && this.over(this.next[0], i, j, 787, 447)) {
                    this.pnext = 1;
                }
                if (k == 2 && this.pnext == 1) {
                    this.showopstage = 175;
                    this.pnext = 0;
                }
            }
            if (k == 2 && this.showopstage == 150) {
                if (this.namehover[0]) {
                    this.ptplayers[0] = "DragShot";
                }
                if (this.namehover[1]) {
                    this.ptplayers[0] = "ToaZuka";
                }
                if (this.namehover[2]) {
                    this.ptplayers[0] = "Velocity";
                }
                if (this.namehover[3]) {
                    this.ptplayers[0] = "KRC";
                }
            }
            if (this.showopstage == 180 || (this.showopstage == 0 && (this.ptmatch == 2 || this.ptmatch == 3 || this.ptmatch == 4 || this.ptmatch == 5))) {
                if (k == 1 && this.over(this.contin[0], i, j, 390, 447)) {
                    this.pcontin = 1;
                }
                if (k == 2 && this.pcontin == 1) {
                    this.pcontin = 0;
                    this.showopstage = 190;
                }
            }
        }
        if (this.fase == 51 && this.flipo >= 465) {
            if (k == 1 && this.over(this.contin[0], i, j, 390, 447)) {
                this.pcontin = 1;
            }
            if (k == 2 && this.pcontin == 1) {
                this.pcontin = 0;
                this.fase = 49;
                ++this.ptmatch;
                this.flipo = 0;
                for (int b2 = 0; b2 < 10; ++b2) {
                    this.ptmatchend[b2] = false;
                }
                for (int a3 = 0; a3 < 11; ++a3) {
                    this.initialise[a3] = false;
                    this.initialise2[a3] = false;
                    this.initialise3[a3] = false;
                    this.sstransphase[a3] = false;
                    this.sstransphase2[a3] = false;
                    this.sstransphase3[a3] = false;
                    this.sstrans[a3] = 0;
                    this.sstrans2[a3] = 0;
                    this.sstrans3[a3] = 0;
                    this.ptscore1[a3] = 0;
                    this.eliminated[a3] = false;
                }
                this.glowb = 60;
                this.showopstage = 0;
                this.neliminated = 0;
                this.ptimer = 1000;
                this.eliminate = false;
                this.spgained = false;
            }
        }
        if (this.fase == 10) {
            if (!this.inst && !this.cred) {
                if (k == 0 && (i != this.lxm || j != this.lym)) {
                    for (int a3 = 0; a3 < 6; ++a3) {
                        if (this.overon(15, 170 + a3 * 50, 198, 40, i, j)) {
                            this.opselect[1] = a3;
                            if ((this.savefase != 2 || a3 != 3) && a3 != 0) {
                                this.hovering[a3] = true;
                            }
                        }
                        else {
                            this.hovering[a3] = false;
                        }
                    }
                }
                if (k == 2) {
                    for (int a3 = 0; a3 < 6; ++a3) {
                        if (this.hovering[a3]) {
                            this.opselect[1] = a3;
                            control.enteralt = true;
                        }
                    }
                }
            }
            else {
                if (k == 1) {
                    if (((this.cred && this.flipo == 2) || (this.inst && this.flipo == 9)) && this.over(this.contin[0], i, j, 437, 420)) {
                        this.pcontin = 1;
                    }
                    if (((this.cred && this.flipo < 2) || (this.inst && this.flipo < 9)) && this.over(this.next[0], i, j, 590, 422)) {
                        this.pnext = 1;
                    }
                    if (this.flipo > 0 && this.over(this.back[0], i, j, 319, 422)) {
                        this.pback = 1;
                    }
                }
                if (k == 2) {
                    if (this.pcontin == 1) {
                        control.enteralt = true;
                        this.pcontin = 0;
                    }
                    if (this.pnext == 1) {
                        control.rightalt = true;
                        this.pnext = 0;
                    }
                    if (this.pback == 1) {
                        control.leftalt = true;
                        this.pback = 0;
                    }
                }
            }
        }
        if (this.triggerinst) {
            if (k == 1) {
                if (this.flipo == 9 && this.over(this.contin[0], i, j, 437, 420)) {
                    this.pcontin = 1;
                }
                if (this.flipo < 9 && this.over(this.next[0], i, j, 590, 422)) {
                    this.pnext = 1;
                }
                if (this.flipo > 0 && this.over(this.back[0], i, j, 319, 422)) {
                    this.pback = 1;
                }
            }
            if (k == 2) {
                if (this.pcontin == 1) {
                    control.enter = true;
                    this.pcontin = 0;
                }
                if (this.pnext == 1) {
                    control.right = true;
                    this.pnext = 0;
                }
                if (this.pback == 1) {
                    control.left = true;
                    this.pback = 0;
                }
            }
        }
        if (this.fase == 301) {
            if (k == 1) {
                if (this.over(this.normalmode, i, j, 333, 80)) {
                    control.normalmode = true;
                }
                if (this.over(this.rpgmode, i, j, 333, 160)) {
                    control.career = true;
                }
                if (this.over(this.practice, i, j, 333, 240)) {
                    control.classic = true;
                }
                if (this.over(this.back[0], i, j, 405, 405)) {
                    this.pback = 1;
                }
            }
            if (k == 2 && this.pback == 1) {
                control.goback3 = true;
                this.pback = 0;
            }
            if (k == 0 && (i != this.lxm || j != this.lym)) {
                this.lxm = i;
                this.lym = j;
            }
        }
        if (this.fase == 11) {
            if (this.flipo == 0) {
                if (k == 1 && this.over(this.next[0], i, j, 700, 410)) {
                    this.pnext = 1;
                }
                if (k == 2 && this.pnext == 1) {
                    control.enter = true;
                    this.pnext = 0;
                }
            }
            if (this.flipo == 1) {
                if (k == 1 && this.over(this.back[0], i, j, 110, 410)) {
                    this.pback = 1;
                }
                if (k == 2 && this.pback == 1) {
                    control.left = true;
                    this.pback = 0;
                }
                if (k == 1 && this.over(this.contin[0], i, j, 390, 410)) {
                    this.pcontin = 1;
                }
                if (k == 2 && this.pcontin == 1) {
                    control.enter = true;
                    this.pcontin = 0;
                }
            }
            if (this.flipo == 16) {
                if (k == 1 && this.over(this.contin[0], i, j, 600, 410)) {
                    this.pcontin = 1;
                }
                if (k == 2 && this.pcontin == 1) {
                    control.enter = true;
                    this.pcontin = 0;
                }
            }
        }
        if (this.fase == 8) {
            if (k == 1 && this.over(this.back[0], i, j, 20, 445)) {
                this.pback = 1;
            }
            if (k == 2 && this.pback == 1) {
                control.backtomaini = true;
                this.pback = 0;
            }
        }
    }
    
    public void stopairs() {
        int i = 0;
        do {
            this.air[i].stop();
        } while (++i < 6);
    }
    
    @Override
    public void run() {
        while (this.runtyp != 0) {
            if (this.runtyp >= 1 && this.runtyp <= 31) {
                this.hipnoload(this.runtyp, false);
            }
            if (this.runtyp == 176) {
                this.loading();
            }
            this.app.repaint();
            try {
                Thread.sleep(20L);
            }
            catch (final InterruptedException ex) {}
        }
    }
    
    public void loadingfailed(final int i, final Control control) {
        this.trackbg(false);
        this.rd.setFont(new Font("SansSerif", 1, 13));
        this.ftm = this.rd.getFontMetrics();
        this.drawcs(140, "Error Loading Stage " + i, 200, 0, 0, 3);
        this.drawcs(170, "This may be a random glitch, or you tried changing the stage's code.", 177, 177, 177, 3);
        this.drawcs(220, "Press Enter to try again.", 177, 177, 177, 3);
        this.rd.drawImage(this.contin[this.pcontin], 390, 365, null);
        this.rd.drawImage(this.br, 100, 40, null);
        this.rd.setColor(new Color(0, 0, 0));
        this.rd.fillRect(0, 0, 100, 480);
        this.rd.fillRect(770, 0, 100, 480);
        this.rd.fillRect(100, 0, 670, 40);
        this.rd.fillRect(100, 440, 670, 40);
        this.rd.setFont(new Font("SansSerif", 1, 11));
        this.ftm = this.rd.getFontMetrics();
        if (control.handb || control.enter) {
            this.fase = 6476;
            control.handb = false;
            control.enter = false;
        }
    }
    
    public void hipnoload(int i, final boolean flag) {
        if (i < 0) {
            i = 28;
        }
        final int[] arrayOfInt = { this.m.snap[0], this.m.snap[1], this.m.snap[2] };
        while (arrayOfInt[0] + arrayOfInt[1] + arrayOfInt[2] < -30) {
            for (int ix = 0; ix < 3; ++ix) {
                if (arrayOfInt[ix] < 50) {
                    final int[] array = arrayOfInt;
                    final int n = ix;
                    ++array[n];
                }
            }
        }
        int il = (int)(115.0f - 115.0f * (arrayOfInt[0] / 100.0f));
        if (il > 255) {
            i = 255;
        }
        if (il < 0) {
            il = 0;
        }
        int jl = (int)(115.0f - 115.0f * (arrayOfInt[1] / 100.0f));
        if (jl > 255) {
            jl = 255;
        }
        if (jl < 0) {
            jl = 0;
        }
        int kl = (int)(115.0f - 115.0f * (arrayOfInt[2] / 100.0f));
        if (kl > 255) {
            kl = 255;
        }
        if (kl < 0) {
            kl = 0;
        }
        this.rd.setColor(new Color(il, jl, kl));
        this.rd.fillRect(0, 0, 870, 480);
        il = (int)(230.0f - 230.0f * (arrayOfInt[0] / 100.0f));
        if (il > 235) {
            il = 235;
        }
        if (il < 0) {
            il = 0;
        }
        jl = (int)(230.0f - 230.0f * (arrayOfInt[1] / 100.0f));
        if (jl > 235) {
            jl = 235;
        }
        if (jl < 0) {
            jl = 0;
        }
        kl = (int)(230.0f - 230.0f * (arrayOfInt[2] / 100.0f));
        if (kl > 235) {
            kl = 235;
        }
        if (kl < 0) {
            kl = 0;
        }
        this.rd.setColor(new Color(il, jl, kl));
        this.rd.fillRect(100, 40, 670, 400);
        if (!this.dontdisplay) {
            this.rd.setFont(new Font("SansSerif", 1, 13));
            this.ftm = this.rd.getFontMetrics();
            this.drawcs(65, this.asay, 0, 0, 0, 3);
        }
        else {
            this.rd.setFont(this.adventure.deriveFont(1, 20.0f));
            this.ftm = this.rd.getFontMetrics();
            this.drawcs(65, this.asay, 0, 0, 0, 3);
            this.rd.setFont(new Font("SansSerif", 1, 13));
            this.ftm = this.rd.getFontMetrics();
        }
        byte byte0 = -90;
        if ((i == 1 || i == 2 || i == 3 || i == 4 || i == 7 || i == 8 || i == 9 || i == 10 || i == 12 || i == 13 || i == 16 || i == 17 || i == 18 || i == 19 || i == 20 || i == 21 || i == 23 || i == 24 || i == 25 || i == 27 || this.careermode) && !this.classicmode) {
            byte0 = 0;
        }
        if (this.dontdisplay && this.showopstage == 195 && this.ptmatch == 1) {
            byte0 = 0;
        }
        if (byte0 == 0) {
            if (this.dudo > 0) {
                if (this.aflk) {
                    if (Math.random() > Math.random()) {
                        this.duds = (int)(Math.random() * 3.0);
                    }
                    else {
                        this.duds = (int)(Math.random() * 2.0);
                    }
                    this.aflk = false;
                }
                else {
                    this.aflk = true;
                }
                --this.dudo;
            }
            else {
                this.duds = 0;
            }
            this.rd.drawImage(this.dude[this.duds], 130, 50, null);
            this.rd.drawImage(this.flaot, 227, 82, null);
            int k = (int)(80.0f - 80.0f * (this.m.snap[0] / (50.0f * this.hipno[i - 1])));
            if (k > 255) {
                k = 255;
            }
            if (k < 0) {
                k = 0;
            }
            int i2 = (int)(80.0f - 80.0f * (this.m.snap[1] / (50.0f * this.hipno[i - 1])));
            if (i2 > 255) {
                i2 = 255;
            }
            if (i2 < 0) {
                i2 = 0;
            }
            int k2 = (int)(80.0f - 80.0f * (this.m.snap[2] / (50.0f * this.hipno[i - 1])));
            if (k2 > 255) {
                k2 = 255;
            }
            if (k2 < 0) {
                k2 = 0;
            }
            if (i == 1) {
                k = 80;
                i2 = 80;
                k2 = 80;
            }
            this.rd.setColor(new Color(50, 50, 50));
            this.rd.setFont(new Font("SansSerif", 1, 13));
            if (this.careermode) {
                if (i == 1) {
                    this.rd.drawString("Welcome to RPG mode, where you train your cars to get stronger.", 297, 107);
                    this.rd.drawString("You start off with 4 stat points per level up, but every 15 levels you", 297, 127);
                    this.rd.drawString("gain 1 extra stat point per level!", 297, 147);
                }
                if (i == 2) {
                    this.rd.drawString("You will eventually stop gaining XP on the latest stage you've", 297, 107);
                    this.rd.drawString("unlocked if your level is too high. For you, this applies to Stage " + this.unlocked[1] + ".", 297, 127);
                    this.rd.drawString("However, you can continue to train on earlier stages if you wish!", 297, 167);
                }
                if (i == 3) {
                    this.rd.drawString("Most special attacks debuff another car in some way, such as by", 297, 107);
                    this.rd.drawString("lowering their speed. A high \"Control\" stat makes your own debuffs", 297, 127);
                    this.rd.drawString("more powerful and also makes you less crippled by other debuffs!", 297, 147);
                }
                if (i == 4) {
                    this.rd.drawString("This stage can be quite difficult; be prepared to dodge enemies.", 297, 107);
                    this.rd.drawString("WATCH OUT for the \"beast\" cars! These are bigger, more powerful", 297, 132);
                    this.rd.drawString("versions of their normal counterparts.", 297, 152);
                    this.rd.drawString("You should probably race here, unless you have very high strength.", 297, 177);
                }
                if (i == 5) {
                    if (!this.bonusstage[0]) {
                        this.rd.drawString("Bonus cars can appear alongside regular cars on a given stage.", 297, 107);
                        this.rd.drawString("They're very similar to their regular counterparts, but have slightly", 297, 137);
                        this.rd.drawString("different stats, special attacks or both.", 297, 157);
                        this.rd.drawString("You can unlock stronger versions of these bonus cars, however...", 297, 187);
                    }
                    else {
                        this.rd.drawString("ALL the AI cars are beast opponents here!", 297, 107);
                        this.rd.drawString("They can't hit each other either - only you can hit them.", 297, 127);
                        this.rd.drawString("You're not allowed to race either! GOOD LUCK!", 297, 147);
                        this.rd.drawString("Winning will unlock bonus cars - but stronger versions of the ones", 297, 172);
                        this.rd.drawString("you've been playing against...", 297, 192);
                    }
                }
                if (i == 6) {
                    this.rd.drawString("You may have noticed some stages have \"no bonus stat points.\"", 297, 107);
                    this.rd.drawString("This is because your car's level is too high for the stage.", 297, 127);
                    this.rd.drawString("XP gain is unchanged though, so it would still be easy training.", 297, 147);
                    this.rd.drawString("About this stage...wasting is a lot easier than racing. Your", 297, 172);
                    this.rd.drawString("opponents won't take too kindly to you if you race...", 297, 192);
                }
                if (i == 7) {
                    this.rd.drawString("This is another wasting stage, but a little tougher.", 297, 107);
                    this.rd.drawString("Cars are able to fix much more easily here, from up to four", 297, 127);
                    this.rd.drawString("different directions!", 297, 147);
                    this.rd.drawString("TIP: They always fix via the fastest route possible, so try and", 297, 172);
                    this.rd.drawString("anticipate this in advance.", 297, 192);
                }
                if (i == 8) {
                    this.rd.drawString("This stage is fun and colourful, but your opponents aren't always", 297, 107);
                    this.rd.drawString("willing to be civil, so it can get tough. You'd be best off just trying", 297, 127);
                    this.rd.drawString("to survive this one, rather than joining in...", 297, 147);
                    this.rd.drawString("TIP: Better cars tend to get more stat points, so try and train newly", 297, 172);
                    this.rd.drawString("unlocked cars if you're having trouble.", 297, 192);
                }
                if (i == 9) {
                    this.rd.drawString("This heat does not treat unprepared cars very kindly.", 297, 107);
                    this.rd.drawString("It ruins your engine, causing your power to drain very quickly, your", 297, 127);
                    this.rd.drawString("acceleration to be ruined and your car to be prone to further damage.", 297, 147);
                    this.rd.drawString("Training Control makes your car resistant to this, however...", 297, 172);
                }
                if (i == 10) {
                    this.rd.drawString("No flames, no carnivals, just pure speed in a race to the finish.", 297, 107);
                    this.rd.drawString("Can you keep up? Maybe try taking the air...", 297, 127);
                    this.rd.drawString("(Debuffs, such as reduced speed, are also disabled here - just like", 297, 152);
                    this.rd.drawString("they were in Stage 5.)", 297, 172);
                }
                if (i == 11) {
                    if (!this.bonusstage[1]) {
                        this.rd.drawString("This is a very deadly stage, where powerful undead cars run wild.", 297, 107);
                        this.rd.drawString("Undead cars cannot hit you in the fixing zone except its guarder.", 297, 127);
                        this.rd.drawString("The intense radiation also degrades your mobility over time, but", 297, 147);
                        this.rd.drawString("this can be allievated with high Control or just by fixing often.", 297, 167);
                        this.rd.drawString("It is best to waste here, or to at least waste the racers first.", 297, 192);
                    }
                    else {
                        this.rd.drawString("There are 7 wasters and 3 racers in this stage.", 297, 107);
                        this.rd.drawString("You can race or waste this time - it's up to you.", 297, 127);
                        this.rd.drawString("The AI cars still can't hit each other by the way!", 297, 147);
                    }
                }
                if (i == 12) {
                    this.rd.drawString("It takes a very brave soul to waste here - there are lots of", 297, 107);
                    this.rd.drawString("powerhouses running around. Best to just try to survive by racing.", 297, 127);
                    this.rd.drawString("High Control and Defence can let you take a hit while making it", 297, 152);
                    this.rd.drawString("easier to dodge attacks. It would also help sponge hits from the", 297, 172);
                    this.rd.drawString("moving spikes, which can sting otherwise...", 297, 192);
                }
                if (i == 13) {
                    this.rd.drawString("Use the coloured checkpoints to navigate this multifloored fortress.", 297, 104);
                    this.rd.drawString("On the lower floors, deadly guardians roam free. But if you're", 297, 128);
                    this.rd.drawString("wasting, they'll help you defeat the beast opponents.", 297, 148);
                    this.rd.drawString("You'll probably find wasting easier than racing, but be wary - the", 297, 172);
                    this.rd.drawString("racers can be excellent at dodging and fixing, especially " + this.names[14] + "!", 297, 192);
                }
                if (i == 14) {
                    this.rd.drawString("This stage marks the entry of the shadow cars.", 297, 107);
                    this.rd.drawString("These cars are faster, more agile and have very high defence.", 297, 127);
                    this.rd.drawString("But for now - they can be vital for outspeeding " + this.names[14] + ".", 297, 152);
                    this.rd.drawString("Touch the green shadow cars for stat boosts, but avoid the red ones!", 297, 172);
                    this.rd.drawString(this.names[14] + "'s speed in the air is absolutely incredible - take the help...", 297, 192);
                }
                if (i == 15) {
                    if (this.bonusstage[2]) {
                        this.rd.drawString("Can you defeat the best of the classic cars?", 297, 107);
                    }
                    else {
                        this.rd.drawString("Be wary of shadow cars, which can appear from this stage onwards.", 297, 107);
                        this.rd.drawString("They are faster, more agile, and have very high defence making", 297, 127);
                        this.rd.drawString("them difficult to take down.", 297, 147);
                    }
                }
                if (i == 16) {
                    this.rd.drawString("Your opponents are starting to get tougher. They now have bonus", 297, 107);
                    this.rd.drawString("stat points themselves, although beast cars get none.", 297, 127);
                    this.rd.drawString("Also, the weather conditions are terrible here. Only Blizzard Rush,", 297, 152);
                    this.rd.drawString("Dr. Monstaa and Dr. Daler can handle it!", 297, 172);
                    this.rd.drawString("(Training Control also works. 230 will be enough).", 297, 192);
                }
                if (i == 17) {
                    this.rd.drawString("WATCH OUT for the undead cars in this stage!", 297, 107);
                    this.rd.drawString("They are very powerful and indestructible, but you do not have to", 297, 127);
                    this.rd.drawString("waste them to win. They especially hate racers...", 297, 147);
                    this.rd.drawString("They will never attack the boss car or shadow cars though.", 297, 172);
                    this.rd.drawString("Racing is extremely difficult here. Wasting is your best option.", 297, 192);
                }
                if (i == 18) {
                    if (this.bonusstage[3]) {
                        this.rd.drawString("Race through this area as fast as you can, and destroy the source", 297, 107);
                        this.rd.drawString("of the radiation, Agent Waster. Watch out for the undead cars!", 297, 127);
                        this.rd.drawString("Clearing a checkpoints heals you, but your health drains very", 297, 152);
                        this.rd.drawString("quickly. The radiation wears your top speed down over time as well.", 297, 172);
                        this.rd.drawString("You should have at least 450 defence to win, and earn your reward.", 297, 192);
                    }
                    else {
                        this.rd.drawString("This desert stage is not easy to drive through.", 297, 107);
                        this.rd.drawString("The intense heat means that every car has slowed down and every", 297, 127);
                        this.rd.drawString("car's power drains faster.", 297, 147);
                        this.rd.drawString("But Bounty Hunter thrives in this heat, and so will you if you train", 297, 172);
                        this.rd.drawString("Control, preferably to about 250.", 297, 192);
                    }
                }
                if (i == 19) {
                    this.rd.drawString("With so many strong wasters running around, racing can be brutal.", 297, 107);
                    this.rd.drawString("Wasting is a lot easier. Just be wary of the glitches... ", 297, 127);
                    this.rd.drawString("TIP: Beast and shadow cars cannot fix below 75% health here!", 297, 152);
                    this.rd.drawString("Also, training Control to about 210 fully resists the glitches.", 297, 172);
                }
                if (i == 20) {
                    this.rd.drawString("This is the toughest racing stage in the game...good luck!", 297, 107);
                    this.rd.drawString("Every car has a speed cut of 200 MPH so you'll need an incredibly", 297, 127);
                    this.rd.drawString("high top speed stat to win this one.", 297, 147);
                    this.rd.drawString("Special attacks are disabled, and cars cannot hit each other.", 297, 172);
                    this.rd.drawString("Only the fastest, most skilful racers have a chance...", 297, 192);
                }
                if (i == 21) {
                    this.rd.drawString("Well done on getting this far, you're reaching the endgame...", 297, 107);
                    this.rd.drawString("The final cars will now be unlocked every 3 stages!", 297, 127);
                    this.rd.drawString("Watch out for Titan here, as he is very strong. Try to stay out of", 297, 167);
                    this.rd.drawString("his way as much as possible.", 297, 187);
                }
                if (i == 22) {
                    this.rd.drawString("There is no intense heat to deal with in this desert level.", 297, 107);
                    this.rd.drawString("Instead, there'll be periods where it gets very dark.", 297, 127);
                    this.rd.drawString("When this happens, Titan will teleport to a target and attack them", 297, 147);
                    this.rd.drawString("ruthlessly, showing no mercy to racers in particular.", 297, 167);
                    this.rd.drawString("Your best option is to waste here. Racing is extremely difficult.", 297, 192);
                }
                if (i == 23) {
                    this.rd.drawString("This stage is very fast-paced...make sure you're sharp!", 297, 107);
                    this.rd.drawString("Precision and high speed are vital, particularly for racers.", 297, 127);
                }
                if (i == 24) {
                    this.rd.drawString("This is the final stage where you will encounter a beast", 297, 107);
                    this.rd.drawString("opponent in.", 297, 127);
                    this.rd.drawString("It has extremely high defence though - you will need", 297, 162);
                    this.rd.drawString("very high strength and lethal combos to defeat it.", 297, 182);
                }
            }
        }
        this.rd.drawImage(this.loadingmusic, 324, 220 + byte0, null);
        this.rd.setFont(new Font("SansSerif", 1, 11));
        this.ftm = this.rd.getFontMetrics();
        if (!flag) {
            this.drawcs(355 + byte0, this.sndsize[i - 1] + " KB", 0, 0, 0, 3);
            this.drawcs(390 + byte0, " Please Wait...", 0, 0, 0, 3);
        }
        else {
            String startmessage = "Loading complete! Press Start to begin...";
            if (this.careermode && !this.bonstage) {
                startmessage = this.songname[i - 1] + " - " + this.artistname[i - 1] + " (YT)";
            }
            this.drawcs(380 + byte0, startmessage, 0, 0, 0, 3);
            this.rd.drawImage(this.star[this.pstar], 394, 400 + byte0, null);
            if (this.pstar != 2) {
                if (this.pstar == 0) {
                    this.pstar = 1;
                }
                else {
                    this.pstar = 0;
                }
            }
        }
    }
    
    private Image loadopsnap(final Image image, final int i, final int j) {
        final int k = image.getHeight(this.ob);
        final int l = image.getWidth(this.ob);
        final int[] ai = new int[l * k];
        final PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, l, k, ai, 0, l);
        try {
            pixelgrabber.grabPixels();
        }
        catch (final InterruptedException ex) {}
        int i2 = 0;
        if (j == 1) {
            i2 = ai[61993];
        }
        for (int j2 = 0; j2 < l * k; ++j2) {
            if (ai[j2] != ai[j]) {
                final Color color = new Color(ai[j2]);
                int k2 = 0;
                int l2 = 0;
                int i3 = 0;
                if (j == 1 && ai[j2] == i2) {
                    k2 = (int)(237.0f - 237.0f * (this.m.snap[0] / (150.0f * this.hipno[i - 1])));
                    if (k2 > 255) {
                        k2 = 255;
                    }
                    if (k2 < 0) {
                        k2 = 0;
                    }
                    l2 = (int)(237.0f - 237.0f * (this.m.snap[1] / (150.0f * this.hipno[i - 1])));
                    if (l2 > 255) {
                        l2 = 255;
                    }
                    if (l2 < 0) {
                        l2 = 0;
                    }
                    i3 = (int)(237.0f - 237.0f * (this.m.snap[2] / (150.0f * this.hipno[i - 1])));
                    if (i3 > 255) {
                        i3 = 255;
                    }
                    if (i3 < 0) {
                        i3 = 0;
                    }
                    if (i == 1) {
                        k2 = 250;
                        l2 = 250;
                        i3 = 250;
                    }
                }
                else {
                    k2 = (int)(color.getRed() - color.getRed() * (this.m.snap[0] / (50.0f * this.hipno[i - 1])));
                    if (k2 > 255) {
                        k2 = 255;
                    }
                    if (k2 < 0) {
                        k2 = 0;
                    }
                    l2 = (int)(color.getGreen() - color.getGreen() * (this.m.snap[1] / (50.0f * this.hipno[i - 1])));
                    if (l2 > 255) {
                        l2 = 255;
                    }
                    if (l2 < 0) {
                        l2 = 0;
                    }
                    i3 = (int)(color.getBlue() - color.getBlue() * (this.m.snap[2] / (50.0f * this.hipno[i - 1])));
                    if (i3 > 255) {
                        i3 = 255;
                    }
                    if (i3 < 0) {
                        i3 = 0;
                    }
                    if (i == 1) {
                        k2 = color.getRed();
                        l2 = color.getGreen();
                        i3 = color.getBlue();
                    }
                }
                final Color color2 = new Color(k2, l2, i3);
                ai[j2] = color2.getRGB();
            }
        }
        final Image image2 = this.createImage(new MemoryImageSource(l, k, ai, 0, l));
        return image2;
    }
    
    private AudioClip getSound(final String s) {
        final AudioClip audioclip = this.app.getAudioClip(this.app.getCodeBase(), s);
        if (s.startsWith("data/Files/sounds/default")) {
            audioclip.play();
            Thread.yield();
            audioclip.stop();
        }
        return audioclip;
    }
    
    public void carsbginflex() {
        this.flexpix = new int[600000];
        this.flatr = 0;
        this.flyr = (int)(this.m.random() * 160.0f - 80.0f);
        this.flyrdest = (int)(this.flyr + this.m.random() * 160.0f - 80.0f);
        this.flang = 1;
        this.flangados = (int)(this.m.random() * 6.0f + 2.0f);
        this.blackn = 0.0f;
        this.blacknados = this.m.random() * 0.4f;
        final PixelGrabber pixelgrabber = new PixelGrabber(this.carsbg, 0, 0, 870, 480, this.flexpix, 0, 870);
        try {
            pixelgrabber.grabPixels();
        }
        catch (final InterruptedException ex) {}
    }
    
    public void randomno(final CheckPoints checkpoints) {
        if (!this.ncarset) {
            if (this.careermode) {
                this.nplayers = 11;
                if (checkpoints.stage == 15 && this.bonusstage[2]) {
                    this.nplayers = 9;
                }
                if (checkpoints.stage == 3 || checkpoints.stage == 17) {
                    this.nplayers = 19;
                }
                if (checkpoints.stage == 8) {
                    this.nplayers = 15;
                }
                if (checkpoints.stage == 13) {
                    this.nplayers = 16;
                }
                if (checkpoints.stage == 11 && !this.bonstage && this.unlocked[1] > 11 && !this.hardstage) {
                    this.nplayers = 17;
                }
                if (checkpoints.stage == 20) {
                    this.nplayers = 2;
                }
                if ((checkpoints.stage == 5 && !this.bonstage) || checkpoints.stage == 18) {
                    this.nplayers = 7;
                }
                if (checkpoints.stage == 23) {
                    if (this.unlocked[1] == 23 || this.hardstage) {
                        this.nplayers = 12;
                    }
                    else {
                        this.nplayers = 11;
                    }
                }
                if (checkpoints.stage == 14) {
                    this.nplayers = 6;
                }
                if (this.bonusstage[3]) {
                    this.nplayers = 5;
                }
                this.ssdone = false;
                this.alldone = false;
                if (checkpoints.stage < 15 || this.bonusstage[2] || checkpoints.stage == 20 || checkpoints.stage == 21 || this.bonusstage[3]) {
                    this.noshadows = 0;
                }
                else if (checkpoints.stage >= 15) {
                    if (checkpoints.stage == 17 || checkpoints.stage == 23 || checkpoints.stage == 24 || checkpoints.stage == 18) {
                        this.noshadows = 2;
                    }
                    else {
                        this.noshadows = 1;
                    }
                }
            }
            else {
                if (this.classicmode) {
                    this.nplayers = 7;
                }
                else {
                    this.nplayers = 11;
                }
                this.noshadows = 0;
            }
            this.ncarset = true;
        }
        else {
            if (this.justcs == 1) {
                this.justcs = 2;
            }
            if (this.justcs == -1) {
                this.fase = 2;
            }
        }
    }
    
    public int reqneed(final int a, final int b) {
        if (b < 8 || (b >= 23 && b < 31)) {
            this.expneededform = 1;
        }
        if (b >= 8 && b < 16) {
            this.expneededform = b - 6;
        }
        if (b >= 16 && b <= 21 && b != 19 && b != 18) {
            this.expneededform = b + 1;
        }
        if (b == 18) {
            this.expneededform = 20;
        }
        if (b == 19) {
            this.expneededform = 24;
        }
        if (b == 22) {
            this.expneededform = 25;
        }
        if (b >= 31) {
            this.expneededform = b - 22;
        }
        final double level = a;
        final double half = a / 2;
        final double multi = 11.36 + a * 0.02;
        final double multiplier = 1.0 + a * 0.04166666667;
        final double value = (level * 259.0 + half * half + level * half + 721.0 + (level * level + level)) * 1.53 * multi * 0.1 * multiplier * (1.0 + 0.125 * this.expneededform) + level * level * level * 1.15;
        final double totalmulti = 0.705;
        return (int)(value * totalmulti);
    }
    
    public int reqneed2(final int a, final int b) {
        if (b < 8) {
            this.expneededform = 1;
        }
        if (b >= 8 && b < 23) {
            this.expneededform = b - 6;
        }
        if (b >= 23) {
            this.expneededform = b - 12;
        }
        int levelimit = a;
        final int levelimit2 = a;
        if (levelimit > 50) {
            levelimit = 50;
        }
        if (levelimit2 > 65) {
            levelimit = 65;
        }
        return (int)((a * 259 + a / 2 * (a / 2) + a * (a / 2) + 721 + (a * a + a)) * 1.53 * (11.36 + a / 50) / 10.0 * (1 + a / 24) * (1.0 + 0.125 * this.expneededform)) + (int)(a * levelimit2 * levelimit * 1.15);
    }
    
    public void resetstats(final Madness madness, final int a) {
        int addon = madness.level[a] - 1;
        if (this.shufflefase < 4) {
            addon = 0;
        }
        if (a == 31) {
            madness.aitssp[a] = 10 + addon;
            madness.aiaccsp[a] = 10 + addon;
            madness.aigripsp[a] = 60 + addon;
            madness.aistusp[a] = 25 + addon;
            madness.aistrsp[a] = 15 + addon;
            madness.aiendsp[a] = 10 + addon;
        }
        if (a == 32) {
            madness.aitssp[a] = 10 + addon;
            madness.aiaccsp[a] = 15 + addon;
            madness.aigripsp[a] = 10 + addon;
            madness.aistusp[a] = 10 + addon;
            madness.aistrsp[a] = 15 + addon;
            madness.aiendsp[a] = 20 + addon;
        }
        if (a == 33) {
            madness.aitssp[a] = 7 + addon;
            madness.aiaccsp[a] = 12 + addon;
            madness.aigripsp[a] = 12 + addon;
            madness.aistusp[a] = 12 + addon;
            madness.aistrsp[a] = 8 + addon;
            madness.aiendsp[a] = 21 + addon;
        }
        if (a == 34 || a == 35 || a == 37 || a == 38) {
            madness.aitssp[a] = 10 + addon;
            madness.aiaccsp[a] = 17 + addon;
            madness.aigripsp[a] = 17 + addon;
            madness.aistusp[a] = 17 + addon;
            madness.aistrsp[a] = 17 + addon;
            madness.aiendsp[a] = 24 + addon;
        }
        if (a == 36) {
            madness.aitssp[a] = 50 + addon;
            madness.aiaccsp[a] = 10 + addon;
            madness.aigripsp[a] = 20 + addon;
            madness.aistusp[a] = 20 + addon;
            madness.aistrsp[a] = 15 + addon;
            madness.aiendsp[a] = 25 + addon;
        }
    }
    
    public void getstats(final Madness madness) {
        final float pace = (madness.nitroswits[this.sc[0]][2] + madness.aitssp[this.sc[0]] - 220) / 90.0f;
        final float[] realacelf = { madness.nitroacelf[this.sc[0]][0] + madness.aiaccsp[this.sc[0]] * 0.1f - 6.0f, 0.0f, 0.0f };
        realacelf[1] = madness.nitroacelf[this.sc[0]][1] * realacelf[0] / madness.nitroacelf[this.sc[0]][0] - 3.0f;
        realacelf[2] = madness.nitroacelf[this.sc[0]][2] * realacelf[0] / madness.nitroacelf[this.sc[0]][0] - 2.0f;
        final float accel = (realacelf[0] * 21.0f + realacelf[1] * 6.0f + realacelf[2] * 3.0f) / 201.0f;
        final float grip = (madness.gripreset[this.sc[0]] + madness.aigripsp[this.sc[0]] * 0.2f - 10.0f) / 20.0f;
        final float stunts = (madness.aircreset[this.sc[0]] + madness.aistusp[this.sc[0]] + (madness.airsreset[this.sc[0]] + madness.aistusp[this.sc[0]] * 0.025f) * 10.0f) / 125.0f;
        final float str = (madness.momentreset[this.sc[0]] + madness.aistrsp[this.sc[0]] * 0.025f) / 2.1f;
        final float tank = this.outdam[this.sc[0]] + madness.aiendsp[this.sc[0]] * 0.01f;
        final float avg = (pace + accel + grip + stunts + str + tank) / 6.0f;
        this.stat[0] = (int)(pace * 100.0f);
        this.stat[1] = (int)(accel * 100.0f);
        this.stat[2] = (int)(grip * 100.0f);
        this.stat[3] = (int)(stunts * 100.0f);
        this.stat[4] = (int)(str * 100.0f);
        this.stat[5] = (int)(tank * 100.0f);
        this.stat[6] = (int)(avg * 100.0f);
    }
    
    public void testbots(final Bots bots, final CheckPoints checkpoints, final Control[] u, final boolean usebots) {
        if (usebots) {
            if (u[0].up) {
                bots.codeup[bots.timer] = new String("up(" + bots.timer + ")\r\n");
                final int[] actions = this.actions;
                final int n = 0;
                ++actions[n];
            }
            if (u[0].down) {
                bots.codedown[bots.timer] = new String("down(" + bots.timer + ")\r\n");
                final int[] actions2 = this.actions;
                final int n2 = 0;
                ++actions2[n2];
            }
            if (u[0].left) {
                bots.codeleft[bots.timer] = new String("left(" + bots.timer + ")\r\n");
                final int[] actions3 = this.actions;
                final int n3 = 0;
                ++actions3[n3];
            }
            if (u[0].right) {
                bots.coderight[bots.timer] = new String("right(" + bots.timer + ")\r\n");
                final int[] actions4 = this.actions;
                final int n4 = 0;
                ++actions4[n4];
            }
            if (u[0].handb) {
                bots.codehandb[bots.timer] = new String("handb(" + bots.timer + ")\r\n");
                final int[] actions5 = this.actions;
                final int n5 = 0;
                ++actions5[n5];
            }
            if (u[0].write) {
                bots.writecode = true;
                u[0].write = false;
            }
            if (bots.writecode) {
                try {
                    final File localFile = new File("data/Files/Bots/1.txt");
                    final BufferedWriter localBufferedWriter = new BufferedWriter(new FileWriter(localFile));
                    for (int a = 0; a < bots.timer; ++a) {
                        localBufferedWriter.write(new StringBuilder().append(bots.codeup[a]).toString());
                        System.out.println(new StringBuilder().append(bots.codeup[a]).toString());
                        localBufferedWriter.write(new StringBuilder().append(bots.codedown[a]).toString());
                        System.out.println(new StringBuilder().append(bots.codedown[a]).toString());
                        localBufferedWriter.write(new StringBuilder().append(bots.codeleft[a]).toString());
                        System.out.println(new StringBuilder().append(bots.codeleft[a]).toString());
                        localBufferedWriter.write(new StringBuilder().append(bots.coderight[a]).toString());
                        System.out.println(new StringBuilder().append(bots.coderight[a]).toString());
                        localBufferedWriter.write(new StringBuilder().append(bots.codehandb[a]).toString());
                        System.out.println(new StringBuilder().append(bots.codehandb[a]).toString());
                    }
                    localBufferedWriter.close();
                    bots.reset();
                    bots.writecode = false;
                }
                catch (final Exception ex) {
                    System.out.println(new StringBuilder().append(ex).toString());
                }
            }
            this.rd.setColor(new Color(0, 0, 0));
            this.rd.drawString("Actions: " + this.actions[0] + ", Timer: " + bots.timer, 200, 200);
        }
    }
    
    public void writenames(final int position, final int whichname) {
        this.rd.setFont(this.fifa.deriveFont(1, 18.0f));
        this.ftm = this.rd.getFontMetrics();
        this.rd.setColor(new Color(235, 235, 235));
        final int ypos = 408 + position % 3 * 30;
        int xpos = 160 - this.ftm.stringWidth(this.statnames[whichname] + ":");
        if (position > 2) {
            xpos = 570 - this.ftm.stringWidth(this.statnames[whichname] + ":");
        }
        this.rd.drawString(this.statnames[whichname] + ":", xpos, ypos);
    }
    
    public void writeboosts(final int position, final int whichname) {
        if (this.showboosts[position]) {
            int lengthmod = 0;
            if (whichname == 34) {
                lengthmod = 75;
            }
            if (whichname == 36) {
                lengthmod = 45;
            }
            final Polygon boost = new Polygon();
            boost.addPoint(18, 190);
            boost.addPoint(10, 231);
            boost.addPoint(18, 272);
            boost.addPoint(240 + lengthmod, 272);
            boost.addPoint(248 + lengthmod, 231);
            boost.addPoint(240 + lengthmod, 190);
            this.rd.setColor(new Color(0, 0, 0, 220));
            this.rd.fillPolygon(boost);
            this.rd.setFont(this.adventure.deriveFont(1, 13.0f));
            this.ftm = this.rd.getFontMetrics();
            this.rd.setColor(new Color(235, 235, 235));
            if (whichname == 0) {
                this.rd.drawString("makes your power drain", 27, 208);
                this.rd.drawString("less quickly (powersave).", 27, 225);
                this.rd.setColor(new Color(150, 240, 150));
                this.rd.drawString("stampede level at maximum.", 27, 259);
            }
            if (whichname == 1) {
                this.rd.drawString("increases your chances", 27, 208);
                this.rd.drawString("of receiving bonus stat", 27, 225);
                this.rd.drawString("points.", 27, 242);
                this.rd.setColor(new Color(150, 240, 150));
                this.rd.drawString("20% increase at maximum.", 27, 259);
            }
            if (whichname == 2) {
                this.rd.drawString("increases experience", 27, 208);
                this.rd.drawString("gained.", 27, 225);
                this.rd.setColor(new Color(150, 240, 150));
                this.rd.drawString("20% increase at maximum.", 27, 259);
            }
            if (whichname == 3) {
                this.rd.drawString("boosts your speed above", 27, 208);
                this.rd.drawString("80% damage.", 27, 225);
                this.rd.setColor(new Color(150, 240, 150));
                this.rd.drawString("20% increase at maximum.", 27, 259);
            }
            if (whichname == 4) {
                this.rd.drawString("increases damage to badly", 27, 208);
                this.rd.drawString("landed opponents.", 27, 225);
                this.rd.setColor(new Color(150, 240, 150));
                this.rd.drawString("20% increase at maximum.", 27, 259);
            }
            if (whichname == 5) {
                this.rd.drawString("lowers the time taken to", 27, 208);
                this.rd.drawString("recover from bad landings.", 27, 225);
                this.rd.setColor(new Color(150, 240, 150));
                this.rd.drawString("80% decrease at maximum.", 27, 259);
            }
            if (whichname == 6) {
                this.rd.drawString("lowers damage taken from", 27, 208);
                this.rd.drawString("beast and shadow cars.", 27, 225);
                this.rd.setColor(new Color(150, 240, 150));
                this.rd.drawString("15% decrease at maximum.", 27, 259);
            }
            if (whichname == 7) {
                this.rd.drawString("pushes cars further away", 27, 208);
                this.rd.drawString("when wasting.", 27, 225);
                this.rd.setColor(new Color(150, 240, 150));
                this.rd.drawString("equal to agent waster at", 27, 242);
                this.rd.drawString("maximum.", 27, 259);
            }
            if (whichname == 8) {
                this.rd.drawString("the effects of debuffs", 27, 208);
                this.rd.drawString("are reduced.", 27, 225);
                this.rd.setColor(new Color(150, 240, 150));
                this.rd.drawString("50% decrease at maximum.", 27, 259);
            }
            if (whichname == 9) {
                this.rd.drawString("boosts your strength at", 27, 208);
                this.rd.drawString("over 80% damage.", 27, 225);
                this.rd.setColor(new Color(150, 240, 150));
                this.rd.drawString("20% increase at maximum.", 27, 259);
            }
            if (whichname == 10) {
                this.rd.drawString("increases your defence in", 27, 208);
                this.rd.drawString("a bad landing.", 27, 225);
                this.rd.setColor(new Color(150, 240, 150));
                this.rd.drawString("25% increase at maximum.", 27, 259);
            }
            if (whichname == 11) {
                this.rd.drawString("lowers momentum lost", 27, 208);
                this.rd.drawString("when wasting other cars.", 27, 225);
                this.rd.setColor(new Color(150, 240, 150));
                this.rd.drawString("100% decrease at maximum.", 27, 259);
            }
            if (whichname == 12) {
                this.rd.drawString("increases damage dealt to", 27, 208);
                this.rd.drawString("beast and shadow cars.", 27, 225);
                this.rd.setColor(new Color(150, 240, 150));
                this.rd.drawString("20% increase at maximum.", 27, 259);
            }
            if (whichname == 13) {
                this.rd.drawString("increases defence whilst", 27, 208);
                this.rd.drawString("reversing.", 27, 225);
                this.rd.setColor(new Color(150, 240, 150));
                this.rd.drawString("20% increase at maximum.", 27, 259);
            }
            if (whichname == 14) {
                this.rd.drawString("increases strength whilst", 27, 208);
                this.rd.drawString("reversing.", 27, 225);
                this.rd.setColor(new Color(150, 240, 150));
                this.rd.drawString("20% increase at maximum.", 27, 259);
            }
            if (whichname == 15) {
                this.rd.drawString("increases defence when", 27, 208);
                this.rd.drawString("power falls below 75%.", 27, 225);
                this.rd.setColor(new Color(150, 240, 150));
                this.rd.drawString("20% increase at maximum.", 27, 259);
            }
            if (whichname == 16) {
                this.rd.drawString("lowers how high you are", 27, 208);
                this.rd.drawString("launched by opponents.", 27, 225);
                this.rd.setColor(new Color(150, 240, 150));
                this.rd.drawString("100% decrease at maximum.", 27, 259);
            }
            if (whichname == 17) {
                this.rd.drawString("increases how high you", 27, 208);
                this.rd.drawString("launch opponents.", 27, 225);
                this.rd.setColor(new Color(150, 240, 150));
                this.rd.drawString("equal to revonater at", 27, 242);
                this.rd.drawString("maximum.", 27, 259);
            }
            if (whichname == 18) {
                this.rd.drawString("hitting other cars drains", 27, 208);
                this.rd.drawString("their special.", 27, 225);
                this.rd.setColor(new Color(150, 240, 150));
                this.rd.drawString("100% of damage dealt is", 27, 242);
                this.rd.drawString("drained at maximum.", 27, 259);
            }
            if (whichname == 19) {
                this.rd.drawString("hitting other cars drains", 27, 208);
                this.rd.drawString("their power.", 27, 225);
                this.rd.setColor(new Color(150, 240, 150));
                this.rd.drawString("50% of damage dealt is", 27, 242);
                this.rd.drawString("drained at maximum.", 27, 259);
            }
            if (whichname == 20) {
                this.rd.drawString("increases your stat boosts", 27, 208);
                this.rd.drawString("in your special attack.", 27, 225);
                this.rd.setColor(new Color(150, 240, 150));
                this.rd.drawString("25% increase at maximum.", 27, 259);
            }
            if (whichname == 21) {
                this.rd.drawString("raises your speed after", 27, 208);
                this.rd.drawString("you fix (temporary).", 27, 225);
                this.rd.setColor(new Color(150, 240, 150));
                this.rd.drawString("20% speed/100% duration", 27, 242);
                this.rd.drawString("increase at maximum.", 27, 259);
            }
            if (whichname == 22) {
                this.rd.drawString("raises your strength after", 27, 208);
                this.rd.drawString("you fix (temporary).", 27, 225);
                this.rd.setColor(new Color(150, 240, 150));
                this.rd.drawString("20% strength/80% duration", 27, 242);
                this.rd.drawString("increase at maximum.", 27, 259);
            }
            if (whichname == 23) {
                this.rd.drawString("wasting cars increases", 27, 208);
                this.rd.drawString("strength (temporary).", 27, 225);
                this.rd.setColor(new Color(150, 240, 150));
                this.rd.drawString("20% strength/80% duration", 27, 242);
                this.rd.drawString("increase at maximum.", 27, 259);
            }
            if (whichname == 24) {
                this.rd.drawString("wasting cars increases", 27, 208);
                this.rd.drawString("defence (temporary).", 27, 225);
                this.rd.setColor(new Color(150, 240, 150));
                this.rd.drawString("20% defence/100% duration", 27, 242);
                this.rd.drawString("increase at maximum.", 27, 259);
            }
            if (whichname == 25) {
                this.rd.drawString("restores some health when", 27, 208);
                this.rd.drawString("clearing checkpoints.", 27, 225);
                this.rd.setColor(new Color(150, 240, 150));
                this.rd.drawString("4% recovery/checkpoint", 27, 242);
                this.rd.drawString("at maximum.", 27, 259);
            }
            if (whichname == 26) {
                this.rd.drawString("hitting cars drains their,", 27, 208);
                this.rd.drawString("health further over time.", 27, 225);
                this.rd.setColor(new Color(150, 240, 150));
                this.rd.drawString("20% of each hit is drained", 27, 242);
                this.rd.drawString("overall at maximum.", 27, 259);
            }
            if (whichname == 27) {
                this.rd.drawString("increases the sharpness of", 27, 208);
                this.rd.drawString("your turning.", 27, 225);
                this.rd.setColor(new Color(150, 240, 150));
                this.rd.drawString("Speedy 7 level at maximum.", 27, 259);
            }
            if (whichname == 28) {
                this.rd.drawString("increases the power of", 27, 208);
                this.rd.drawString("your special's debuffs.", 27, 225);
                this.rd.setColor(new Color(150, 240, 150));
                this.rd.drawString("50% increase at maximum.", 27, 259);
            }
            if (whichname == 29) {
                this.rd.drawString("hitting cars recovers", 27, 208);
                this.rd.drawString("your health.", 27, 225);
                this.rd.setColor(new Color(150, 240, 150));
                this.rd.drawString("15% of damage is recovered", 27, 242);
                this.rd.drawString("at maximum.", 27, 259);
            }
            if (whichname == 30) {
                this.rd.drawString("increases the rate that", 27, 208);
                this.rd.drawString("your special charges at.", 27, 225);
                this.rd.setColor(new Color(150, 240, 150));
                this.rd.drawString("20% increase at maximum.", 27, 259);
            }
            if (whichname == 31) {
                this.rd.drawString("increases recoil taken", 27, 208);
                this.rd.drawString("by your attackers.", 27, 225);
                this.rd.setColor(new Color(150, 240, 150));
                this.rd.drawString("50% increase at maximum.", 27, 259);
            }
            if (whichname == 32) {
                this.rd.drawString("hitting cars decreases", 27, 208);
                this.rd.drawString("their speed temporarily.", 27, 225);
                this.rd.setColor(new Color(150, 240, 150));
                this.rd.drawString("50% increase in duration", 27, 242);
                this.rd.drawString("at maximum.", 27, 259);
            }
            if (whichname == 33) {
                this.rd.drawString("increases your chances of", 27, 208);
                this.rd.drawString("surviving a fatal hit.", 27, 225);
                this.rd.setColor(new Color(150, 240, 150));
                this.rd.drawString("20% chance of survival at", 27, 242);
                this.rd.drawString("maximum.", 27, 259);
            }
            if (whichname == 34) {
                this.rd.drawString("you can become an undead car after", 27, 208);
                this.rd.drawString("you're wasted for a short time.", 27, 225);
                this.rd.setColor(new Color(150, 240, 150));
                this.rd.drawString("100% increase in duration and 25%", 27, 242);
                this.rd.drawString("chance of occurring at maximum.", 27, 259);
            }
            if (whichname == 35) {
                this.rd.drawString("reduces how much you get", 27, 208);
                this.rd.drawString("lifted when hitting cars.", 27, 225);
                this.rd.setColor(new Color(150, 240, 150));
                this.rd.drawString("100% decrease at maximum.", 27, 259);
            }
            if (whichname == 36) {
                this.rd.drawString("allows you to increase gravity", 27, 208);
                this.rd.drawString("on your car by pressing \"G\".", 27, 225);
                this.rd.setColor(new Color(150, 240, 150));
                this.rd.drawString("gravity increases 5x at maximum.", 27, 259);
            }
            if (whichname == 37) {
                this.rd.drawString("increases speed after a", 27, 208);
                this.rd.drawString("bad landing (temporary).", 27, 225);
                this.rd.setColor(new Color(150, 240, 150));
                this.rd.drawString("20% speed/100% duration", 27, 242);
                this.rd.drawString("increase at maximum.", 27, 259);
            }
            if (whichname == 38) {
                this.rd.drawString("health recovers steadily", 27, 208);
                this.rd.drawString("during your special.", 27, 225);
                this.rd.setColor(new Color(150, 240, 150));
                this.rd.drawString("60% health restored in", 27, 242);
                this.rd.drawString("total at maximum.", 27, 259);
            }
            if (whichname == 39) {
                this.rd.drawString("reduces the bounciness of", 27, 208);
                this.rd.drawString("your car.", 27, 225);
                this.rd.setColor(new Color(150, 240, 150));
                this.rd.drawString("stampede level at maximum.", 27, 259);
            }
        }
    }
}
