import java.util.Arrays;

// 
// Decompiled by Procyon v0.6.0
// 

public class Control
{
    Contva variable;
    boolean left;
    boolean right;
    boolean up;
    boolean down;
    boolean handb;
    boolean backtomaini;
    boolean spatk;
    boolean goback3;
    boolean statincrease;
    boolean career;
    boolean swap;
    int waitman;
    boolean[] hover;
    boolean classic;
    boolean fixnitro;
    boolean savedata;
    boolean[] opclick;
    boolean normalmode;
    boolean[] carsavedata;
    boolean cleardataop;
    int lookback;
    boolean enter;
    boolean scouting;
    boolean goback;
    boolean clearall;
    boolean arrace;
    boolean goback2;
    boolean mutem;
    boolean mutes;
    Medium m;
    int pan;
    int attack;
    int acr;
    int a1;
    boolean afta;
    int[] fpnt;
    int trfix;
    boolean forget;
    boolean bulistc;
    int runbul;
    int acuracy;
    int upwait;
    boolean agressed;
    float skiplev;
    int clrnce;
    int rampp;
    int turntyp;
    float aim;
    int saftey;
    boolean perfection;
    float mustland;
    boolean usebounce;
    float trickprf;
    int stuntf;
    boolean zyinv;
    boolean lastl;
    boolean wlastl;
    int hold;
    int wall;
    int lwall;
    int stcnt;
    int statusque;
    int turncnt;
    int randtcnt;
    int upcnt;
    int trickfase;
    int swat;
    boolean udcomp;
    boolean lrcomp;
    boolean udbare;
    boolean lrbare;
    boolean onceu;
    boolean onced;
    boolean oncel;
    boolean oncer;
    int lrdirect;
    int uddirect;
    int lrstart;
    int udstart;
    int oxy;
    int ozy;
    int flycnt;
    boolean lrswt;
    boolean udswt;
    boolean gowait;
    int actwait;
    int cntrn;
    int revstart;
    int oupnt;
    int wtz;
    int wtx;
    int frx;
    int frz;
    int frad;
    int apunch;
    boolean exitattack;
    int[] avoidnlev;
    boolean changefix;
    boolean waited;
    int abboost;
    int abdelay;
    int campchk;
    int campcool;
    int chkahead;
    boolean waitforuser;
    boolean intercept;
    int l1;
    int l3;
    int k5;
    int stuck;
    int downuse;
    boolean fewsecson;
    int fewsecs;
    boolean backfix;
    boolean switchspot;
    int staythere;
    boolean write;
    boolean delayturn;
    boolean dontback;
    boolean upalt;
    boolean downalt;
    boolean enteralt;
    boolean handbalt;
    boolean leftalt;
    boolean rightalt;
    boolean[] keylock;
    int whichkeylock;
    boolean[] numpress;
    boolean needtofix;
    int fixby;
    boolean[] viewbot;
    boolean[] neverhit;
    boolean wrongfloor;
    int gotofloor;
    boolean setfixfloor;
    
    public void preform(final Madness madness, final ContO conto, final CheckPoints checkpoints, final Trackers trackers, final xtGraphics xtgraphics, final Madness usermad, final Bots bots) {
        this.left = false;
        this.right = false;
        this.up = false;
        this.down = false;
        this.handb = false;
        this.spatk = false;
        if (!madness.dest) {
            if (madness.mtouch) {
                if (this.stcnt <= this.statusque) {
                    ++this.stcnt;
                }
                else {
                    this.clrnce = 5;
                    Label_0121: {
                        if (xtgraphics.dontdisplay) {
                            if (xtgraphics.ptmatch != 2) {
                                if (xtgraphics.ptmatch != 3) {
                                    break Label_0121;
                                }
                            }
                            this.clrnce = 3;
                        }
                    }
                    Label_0653: {
                        if (xtgraphics.careermode) {
                            Label_0195: {
                                if (checkpoints.stage != 15) {
                                    if (checkpoints.stage != 17) {
                                        if (checkpoints.stage != 6) {
                                            if (checkpoints.stage != 7) {
                                                break Label_0195;
                                            }
                                        }
                                    }
                                }
                                this.clrnce = 2;
                            }
                            Label_0270: {
                                if (checkpoints.stage != 19) {
                                    if (checkpoints.stage != 22) {
                                        if (checkpoints.stage != 23) {
                                            if (checkpoints.stage != 8) {
                                                if (checkpoints.stage != 16) {
                                                    break Label_0270;
                                                }
                                            }
                                        }
                                    }
                                }
                                this.clrnce = 4;
                            }
                            if (checkpoints.stage == 21) {
                                this.clrnce = 3;
                            }
                            if (checkpoints.stage == 18) {
                                if (madness.pcleared != 84) {
                                    this.clrnce = 3;
                                }
                                else {
                                    this.clrnce = 6;
                                }
                            }
                            if (checkpoints.stage == 5) {
                                this.clrnce = 4;
                            }
                            Label_0489: {
                                if (checkpoints.stage == 10) {
                                    if (madness.cn != 12) {
                                        if (madness.cn != 35) {
                                            this.clrnce = 3;
                                            break Label_0489;
                                        }
                                    }
                                    boolean exception = false;
                                    if (checkpoints.stage == 10) {
                                        if (madness.pcleared == 93) {
                                            if (madness.point >= 125) {
                                                exception = true;
                                            }
                                        }
                                        if (madness.specialact) {
                                            if (!exception) {
                                                this.clrnce = 9;
                                                break Label_0489;
                                            }
                                        }
                                        this.clrnce = 3;
                                    }
                                }
                            }
                            Label_0537: {
                                Label_0532: {
                                    if (checkpoints.stage == 11) {
                                        if (!xtgraphics.bonusstage[1]) {
                                            break Label_0532;
                                        }
                                    }
                                    if (checkpoints.stage != 12) {
                                        break Label_0537;
                                    }
                                }
                                this.clrnce = 3;
                            }
                            if (checkpoints.stage == 13) {
                                this.clrnce = 3;
                            }
                            if (checkpoints.stage == 14) {
                                if (madness.pcleared != 211) {
                                    if (madness.pcleared != 278) {
                                        Label_0642: {
                                            if (!madness.specialact) {
                                                if (madness.pcleared < 301) {
                                                    this.clrnce = 2;
                                                    break Label_0642;
                                                }
                                            }
                                            this.clrnce = 4;
                                        }
                                        break Label_0653;
                                    }
                                }
                                this.clrnce = 9;
                            }
                        }
                    }
                    float f = 0.0f;
                    if (checkpoints.stage == 4) {
                        if (xtgraphics.classicmode) {
                            f = 0.5f;
                        }
                    }
                    if (checkpoints.stage == 5) {
                        if (xtgraphics.classicmode) {
                            f = 0.2f;
                        }
                    }
                    if (checkpoints.pos[madness.im] - checkpoints.pos[0] < -1) {
                        this.skiplev += 0.2;  // cast: bytecode-verified
                        if (this.skiplev > f) {
                            this.skiplev = f;
                        }
                    }
                    else {
                        this.skiplev -= 0.1;  // cast: bytecode-verified
                        if (this.skiplev < 0.0f) {
                            this.skiplev = 0.0f;
                        }
                    }
                    if (checkpoints.stage == 14) {
                        this.skiplev = 1.0f;
                    }
                    Label_0866: {
                        if (checkpoints.stage != 16) {
                            if (checkpoints.stage != 15) {
                                break Label_0866;
                            }
                        }
                        this.skiplev = 0.0f;
                    }
                    this.rampp = (int)(this.m.random() * 4.0f - 2.0f);
                    if (madness.power == 98.0f) {
                        this.rampp = -1;
                    }
                    if (madness.power == 75.0f) {
                        if (this.rampp == -1) {
                            this.rampp = 0;
                        }
                    }
                    if (madness.power < 60.0f) {
                        this.rampp = 1;
                    }
                    if (xtgraphics.careermode) {
                        Label_1017: {
                            if (checkpoints.stage != 10) {
                                if (checkpoints.stage != 11) {
                                    break Label_1017;
                                }
                            }
                            if (madness.power < 90.0f) {
                                this.rampp = 1;
                            }
                        }
                        if (checkpoints.stage == 13) {
                            if (madness.power < 98.0f) {
                                this.rampp = 1;
                            }
                        }
                    }
                    if (this.cntrn != 0) {
                        --this.cntrn;
                    }
                    else {
                        this.agressed = false;
                        this.turntyp = (int)(this.m.random() * 4.0f);
                        if (checkpoints.stage == 3) {
                            this.turntyp = 1;
                        }
                        if (checkpoints.pos[0] - checkpoints.pos[madness.im] < 0) {
                            this.turntyp = (int)(this.m.random() * 2.0f);
                        }
                        if (checkpoints.stage == 8) {
                            if (xtgraphics.classicmode) {
                                this.turntyp = 2;
                            }
                        }
                        if (xtgraphics.careermode) {
                            Label_1258: {
                                if (checkpoints.stage != 10) {
                                    if (checkpoints.stage != 7) {
                                        if (checkpoints.stage != 11) {
                                            if (checkpoints.stage != 8) {
                                                if (checkpoints.stage != 5) {
                                                    break Label_1258;
                                                }
                                            }
                                        }
                                    }
                                }
                                this.turntyp = 0;
                            }
                            if (checkpoints.stage == 12) {
                                if (madness.pcleared != 33) {
                                    this.turntyp = 0;
                                }
                                else {
                                    this.turntyp = 2;
                                }
                            }
                        }
                        Label_1347: {
                            if (xtgraphics.dontdisplay) {
                                if (xtgraphics.ptmatch != 2) {
                                    if (xtgraphics.ptmatch != 3) {
                                        break Label_1347;
                                    }
                                }
                                this.turntyp = 0;
                            }
                        }
                        Label_1394: {
                            if (checkpoints.stage != 14) {
                                if (checkpoints.stage != 20) {
                                    if (checkpoints.stage != 6) {
                                        break Label_1394;
                                    }
                                }
                            }
                            this.turntyp = 0;
                        }
                        if (this.attack != 0) {
                            this.turntyp = 2;
                            if (checkpoints.stage == 11) {
                                if (xtgraphics.classicmode) {
                                    this.turntyp = (int)(this.m.random() * 3.0f);
                                }
                            }
                            if (checkpoints.stage == 16) {
                                if (checkpoints.clear[madness.im] - checkpoints.clear[0] >= 5) {
                                    this.turntyp = 0;
                                }
                            }
                        }
                        Label_1614: {
                            if (checkpoints.stage != 6) {
                                if (checkpoints.stage != 7) {
                                    if (checkpoints.stage != 10) {
                                        if (checkpoints.stage != 11) {
                                            if (checkpoints.stage != 12) {
                                                if (checkpoints.stage != 14) {
                                                    if (checkpoints.stage != 16) {
                                                        if (checkpoints.stage != 17) {
                                                            break Label_1614;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            this.agressed = true;
                        }
                        Label_1660: {
                            if (xtgraphics.dontdisplay) {
                                if (xtgraphics.ptmatch != 2) {
                                    if (xtgraphics.ptmatch != 3) {
                                        break Label_1660;
                                    }
                                }
                                this.agressed = true;
                            }
                        }
                        this.cntrn = 5;
                    }
                    this.saftey = (int)((98.0f - madness.power) / 2.0f * (this.m.random() / 2.0f + 0.5));
                    boolean relaxdude = false;
                    if (xtgraphics.careermode) {
                        if (checkpoints.stage >= 23) {
                            relaxdude = true;
                        }
                    }
                    if (this.saftey > 20) {
                        if (!relaxdude) {
                            this.saftey = 20;
                        }
                    }
                    f = 0.0f;
                    if (checkpoints.stage == 1) {
                        f = 0.9f;
                    }
                    this.mustland = f + (float)(this.m.random() / 2.0f - 0.25);
                    f = 1.0f;
                    if (madness.power <= 50.0f) {
                        this.mustland -= 0.5f;
                    }
                    else {
                        this.mustland = 0.0f;
                    }
                    Label_1945: {
                        if (checkpoints.stage != 8) {
                            if (checkpoints.stage != 10) {
                                if (checkpoints.stage != 12) {
                                    if (checkpoints.stage != 14) {
                                        if (checkpoints.stage != 22) {
                                            if (checkpoints.stage != 6) {
                                                break Label_1945;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        this.mustland = 0.0f;
                    }
                    Label_1991: {
                        if (xtgraphics.dontdisplay) {
                            if (xtgraphics.ptmatch != 2) {
                                if (xtgraphics.ptmatch != 3) {
                                    break Label_1991;
                                }
                            }
                            this.mustland = 0.0f;
                        }
                    }
                    if (xtgraphics.careermode) {
                        if (checkpoints.stage >= 7) {
                            this.mustland = 0.0f;
                        }
                    }
                    this.stuntf = 0;
                    if (checkpoints.stage == 8) {
                        if (madness.pcleared == 57) {
                            if (xtgraphics.classicmode) {
                                this.stuntf = 1;
                            }
                        }
                    }
                    if (checkpoints.stage == 10) {
                        if (!xtgraphics.careermode) {
                            Label_2181: {
                                if (checkpoints.pos[0] >= checkpoints.pos[madness.im]) {
                                    if (Math.abs(checkpoints.clear[0] - madness.clear) < 2) {
                                        if (madness.clear >= 2) {
                                            this.stuntf = 3;
                                            break Label_2181;
                                        }
                                    }
                                }
                                this.stuntf = 4;
                                this.saftey = 10;
                            }
                            if (madness.cn == 12) {
                                this.stuntf = 1;
                            }
                        }
                    }
                    Label_2246: {
                        if (xtgraphics.dontdisplay) {
                            if (xtgraphics.ptmatch != 2) {
                                if (xtgraphics.ptmatch != 3) {
                                    break Label_2246;
                                }
                            }
                            this.stuntf = 2;
                        }
                    }
                    if (checkpoints.stage == 11) {
                        if (madness.pcleared == 21) {
                            if (xtgraphics.classicmode) {
                                this.stuntf = 1;
                            }
                        }
                    }
                    Label_2363: {
                        if (checkpoints.stage == 10) {
                            if (!xtgraphics.careermode) {
                                if (madness.pcleared != 44) {
                                    if (madness.pcleared < 140) {
                                        this.stuntf = 2;
                                        break Label_2363;
                                    }
                                }
                                this.stuntf = 1;
                            }
                        }
                    }
                    if (checkpoints.stage == 14) {
                        if (xtgraphics.classicmode) {
                            this.saftey = 10;
                            Label_2466: {
                                if (madness.pcleared >= 4) {
                                    if (madness.pcleared < 70) {
                                        this.stuntf = 4;
                                        break Label_2466;
                                    }
                                }
                                if (madness.cn != 12) {
                                    if (madness.cn != 8) {
                                        break Label_2466;
                                    }
                                }
                                this.stuntf = 2;
                            }
                            if (madness.cn == 14) {
                                this.stuntf = 6;
                            }
                        }
                    }
                    Label_4141: {
                        if (xtgraphics.careermode) {
                            Label_2662: {
                                if (checkpoints.stage != 2) {
                                    if (checkpoints.stage != 3) {
                                        if (checkpoints.stage != 4) {
                                            if (checkpoints.stage != 6) {
                                                break Label_2662;
                                            }
                                        }
                                    }
                                }
                                if (madness.power <= 60.0f) {
                                    this.stuntf = 12;
                                }
                                else {
                                    this.stuntf = 4;
                                }
                                if (checkpoints.stage == 6) {
                                    if (madness.beast[madness.im]) {
                                        if (checkpoints.clear[0] >= 3) {
                                            if (madness.specialact) {
                                                this.stuntf = 4;
                                            }
                                            else {
                                                this.stuntf = 11;
                                            }
                                        }
                                    }
                                    this.saftey = 5;
                                }
                            }
                            Label_2816: {
                                if (checkpoints.stage == 7) {
                                    Label_2772: {
                                        if (madness.beast[madness.im]) {
                                            if (checkpoints.clear[0] >= 3) {
                                                if (madness.specialact) {
                                                    this.stuntf = 4;
                                                }
                                                else {
                                                    this.stuntf = 11;
                                                }
                                                break Label_2772;
                                            }
                                        }
                                        if (madness.power <= 75.0f) {
                                            this.stuntf = 12;
                                        }
                                        else {
                                            this.stuntf = 4;
                                        }
                                    }
                                    if (madness.cn != 10) {
                                        if (madness.cn != 33) {
                                            this.saftey = 5;
                                            break Label_2816;
                                        }
                                    }
                                    this.saftey = 8;
                                }
                            }
                            Label_2883: {
                                if (checkpoints.stage != 8) {
                                    if (!xtgraphics.bonusstage[1]) {
                                        break Label_2883;
                                    }
                                }
                                if (madness.power <= 65.0f) {
                                    this.stuntf = 11;
                                }
                                else {
                                    this.stuntf = 4;
                                }
                                this.saftey = 8;
                            }
                            if (checkpoints.stage == 5) {
                                this.stuntf = 4;
                                this.saftey = 3;
                            }
                            Label_3109: {
                                if (checkpoints.stage != 9) {
                                    if (checkpoints.stage != 11) {
                                        if (checkpoints.stage != 14) {
                                            break Label_3109;
                                        }
                                    }
                                }
                                if (!xtgraphics.bonusstage[1]) {
                                    int careful = 0;
                                    Label_3026: {
                                        Label_3022: {
                                            if (checkpoints.stage == 9) {
                                                if (madness.cn != 12) {
                                                    if (madness.cn != 35) {
                                                        break Label_3022;
                                                    }
                                                }
                                            }
                                            if (checkpoints.stage != 14) {
                                                break Label_3026;
                                            }
                                        }
                                        careful = 10;
                                    }
                                    Label_3070: {
                                        if (checkpoints.stage != 14) {
                                            if (checkpoints.stage != 9) {
                                                this.saftey = careful;
                                                break Label_3070;
                                            }
                                        }
                                        this.saftey = 0;
                                    }
                                    this.stuntf = 4;
                                    if (madness.cn != 13) {
                                        if (madness.cn != 36) {
                                            break Label_3109;
                                        }
                                    }
                                    this.stuntf = 11;
                                }
                            }
                            Label_3230: {
                                if (checkpoints.stage == 10) {
                                    this.saftey = 0;
                                    Label_3225: {
                                        if (checkpoints.clear[madness.im] == 0) {
                                            if (madness.cn != 1) {
                                                if (madness.cn != 8) {
                                                    if (madness.cn != 24) {
                                                        if (madness.cn != 31) {
                                                            break Label_3225;
                                                        }
                                                    }
                                                }
                                            }
                                            if (madness.point < 10) {
                                                this.stuntf = 12;
                                                break Label_3230;
                                            }
                                        }
                                    }
                                    this.stuntf = 4;
                                }
                            }
                            if (checkpoints.stage == 16) {
                                this.stuntf = 9;
                                this.saftey = 5;
                            }
                            if (checkpoints.stage == 15) {
                                this.saftey = 10;
                                this.stuntf = 4;
                            }
                            if (checkpoints.stage == 17) {
                                this.stuntf = 4;
                                this.saftey = 5;
                            }
                            Label_3452: {
                                if (checkpoints.stage == 19) {
                                    this.saftey = 5;
                                    int whichstuntf = 4;
                                    if (madness.beast[madness.im]) {
                                        if (madness.aistrsp[madness.cn] > 0) {
                                            whichstuntf = 11;
                                        }
                                    }
                                    if (madness.cn == 17) {
                                        this.saftey = 0;
                                        if (madness.pcleared != 14) {
                                            if (madness.pcleared != 30) {
                                                if (madness.pcleared != 46) {
                                                    this.stuntf = 12;
                                                    break Label_3452;
                                                }
                                            }
                                        }
                                        this.stuntf = 11;
                                    }
                                    else {
                                        this.stuntf = 4;
                                    }
                                }
                            }
                            if (checkpoints.stage == 18) {
                                if (!xtgraphics.bonusstage[3]) {
                                    Label_3540: {
                                        if (madness.power >= 60.0f) {
                                            if (madness.cn != 13) {
                                                if (madness.cn != 36) {
                                                    this.stuntf = 4;
                                                    break Label_3540;
                                                }
                                            }
                                        }
                                        this.stuntf = 12;
                                    }
                                    this.saftey = 10;
                                    if (madness.pcleared == 134) {
                                        this.saftey = 0;
                                    }
                                }
                            }
                            if (checkpoints.stage == 21) {
                                Label_3656: {
                                    Label_3651: {
                                        if (madness.im != 10) {
                                            if (madness.cn != 13) {
                                                if (madness.cn != 36) {
                                                    break Label_3651;
                                                }
                                            }
                                        }
                                        if (!xtgraphics.entered[madness.im]) {
                                            this.stuntf = 11;
                                            break Label_3656;
                                        }
                                    }
                                    this.stuntf = 4;
                                }
                                this.saftey = 7;
                            }
                            if (checkpoints.stage == 22) {
                                Label_3766: {
                                    if (madness.im == xtgraphics.nplayers - 1) {
                                        if (checkpoints.clear[0] < 3) {
                                            if (checkpoints.clear[madness.im] != 0) {
                                                if (!madness.specialact) {
                                                    this.stuntf = 11;
                                                    break Label_3766;
                                                }
                                            }
                                        }
                                        this.stuntf = 12;
                                    }
                                    else {
                                        this.stuntf = 4;
                                    }
                                }
                                this.saftey = 5;
                            }
                            if (checkpoints.stage == 23) {
                                Label_3911: {
                                    if (madness.im != 11) {
                                        if (madness.cn != 13) {
                                            if (madness.cn != 36) {
                                                this.stuntf = 4;
                                                this.saftey = 5;
                                                if (madness.speed >= 500.0f) {
                                                    this.dontback = true;
                                                }
                                                if (madness.pcleared != 38) {
                                                    break Label_3911;
                                                }
                                                this.saftey = 12;
                                                break Label_3911;
                                            }
                                        }
                                    }
                                    if (madness.specialact) {
                                        this.stuntf = 4;
                                    }
                                    else {
                                        this.stuntf = 11;
                                    }
                                }
                                if (this.trfix < 2) {
                                    this.saftey = 9;
                                }
                            }
                            Label_4056: {
                                if (checkpoints.stage == 13) {
                                    this.stuntf = 4;
                                    Label_4050: {
                                        if (madness.cn != 13) {
                                            if (madness.cn != 36) {
                                                if (madness.beast[madness.im]) {
                                                    if (madness.cn == 11) {
                                                        break Label_4050;
                                                    }
                                                    if (madness.cn == 34) {
                                                        break Label_4050;
                                                    }
                                                }
                                                if (madness.cn != 18) {
                                                    if (madness.cn != 19) {
                                                        break Label_4056;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    this.stuntf = 12;
                                }
                            }
                            if (checkpoints.stage == 12) {
                                if (madness.cn != 10) {
                                    if (madness.cn != 33) {
                                        this.stuntf = 9;
                                        break Label_4141;
                                    }
                                }
                                if (madness.power >= 70.0f) {
                                    this.stuntf = 4;
                                }
                                else {
                                    this.stuntf = 10;
                                }
                            }
                        }
                    }
                    Label_4193: {
                        if (xtgraphics.dontdisplay) {
                            if (xtgraphics.ptmatch != 2) {
                                if (xtgraphics.ptmatch != 3) {
                                    break Label_4193;
                                }
                            }
                            this.saftey = 10;
                            this.stuntf = 4;
                        }
                    }
                    if (checkpoints.stage == 16) {
                        if (xtgraphics.classicmode) {
                            this.mustland = 0.0f;
                            this.saftey = 10;
                            Label_4297: {
                                if (madness.pcleared != 15) {
                                    if (madness.pcleared != 51) {
                                        break Label_4297;
                                    }
                                }
                                if (this.m.random() <= 0.4) {
                                    if (this.trfix == 0) {
                                        break Label_4297;
                                    }
                                }
                                this.stuntf = 7;
                            }
                            if (madness.pcleared == 42) {
                                this.stuntf = 1;
                            }
                            if (madness.pcleared == 77) {
                                this.stuntf = 7;
                            }
                            this.avoidnlev[0] = (int)(2700.0f * this.m.random());
                        }
                    }
                    if (xtgraphics.careermode) {
                        for (int a = 0; a < xtgraphics.nplayers; ++a) {
                            this.avoidnlev[a] = 0;
                        }
                        boolean dodgecon = false;
                        Label_4489: {
                            if (checkpoints.stage == 11) {
                                if (!xtgraphics.bonusstage[1]) {
                                    if (madness.cn != 12) {
                                        if (madness.cn != 35) {
                                            break Label_4489;
                                        }
                                    }
                                    if (!madness.beast[madness.im]) {
                                        dodgecon = true;
                                    }
                                }
                                else {
                                    dodgecon = true;
                                }
                            }
                        }
                        if (checkpoints.stage == 12) {
                            if (!madness.beast[madness.im]) {
                                if (madness.cn != 11) {
                                    if (madness.cn != 34) {
                                        dodgecon = true;
                                    }
                                }
                            }
                        }
                        Label_4724: {
                            if (checkpoints.stage == 13) {
                                if (!madness.beast[madness.im]) {
                                    if (madness.cn != 13) {
                                        if (madness.cn != 36) {
                                            dodgecon = true;
                                        }
                                    }
                                }
                                if (xtgraphics.unlocked[1] != 13) {
                                    if (!xtgraphics.hardstage) {
                                        break Label_4724;
                                    }
                                }
                                if (madness.beast[madness.im]) {
                                    for (int a2 = xtgraphics.nplayers - 6; a2 < xtgraphics.nplayers; ++a2) {
                                        this.neverhit[a2] = true;
                                        this.avoidnlev[a2] = (int)(2000.0f * this.m.random()) + 8000;
                                    }
                                }
                            }
                        }
                        Label_5084: {
                            Label_4934: {
                                if (checkpoints.stage == 5) {
                                    if (!xtgraphics.bonstage) {
                                        break Label_4934;
                                    }
                                }
                                if (checkpoints.stage != 9) {
                                    if (!dodgecon) {
                                        if (checkpoints.stage == 15) {
                                            if (!xtgraphics.bonusstage[2]) {
                                                break Label_4934;
                                            }
                                        }
                                        if (checkpoints.stage != 10) {
                                            if (checkpoints.stage != 14) {
                                                if (checkpoints.stage != 17) {
                                                    if (checkpoints.stage != 18) {
                                                        if (checkpoints.stage != 19) {
                                                            if (checkpoints.stage != 21) {
                                                                if (checkpoints.stage != 23) {
                                                                    if (checkpoints.stage != 22) {
                                                                        break Label_5084;
                                                                    }
                                                                    if (madness.im != xtgraphics.nplayers - 1) {
                                                                        break Label_5084;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (usermad.speed >= 200.0f) {
                                if (usermad.speed >= 300.0f) {
                                    if (usermad.speed >= 400.0f) {
                                        this.avoidnlev[0] = (int)(2000.0f * this.m.random()) + 8000;
                                    }
                                    else {
                                        this.avoidnlev[0] = (int)(2000.0f * this.m.random()) + 5000;
                                    }
                                }
                                else {
                                    this.avoidnlev[0] = (int)(2000.0f * this.m.random()) + 2500;
                                }
                            }
                            else {
                                this.avoidnlev[0] = (int)(2700.0f * this.m.random());
                            }
                        }
                        Label_5333: {
                            if (checkpoints.stage != 11) {
                                if (checkpoints.stage != 21) {
                                    break Label_5333;
                                }
                            }
                            if (!xtgraphics.bonstage) {
                                if (!xtgraphics.hardstage) {
                                    if (xtgraphics.unlocked[1] != checkpoints.stage) {
                                        break Label_5333;
                                    }
                                }
                                for (int a2 = 8; a2 < 10; ++a2) {
                                    if (!xtgraphics.invulnerable) {
                                        if (bots.botbreak[a2]) {
                                            if (madness.cn == 12) {
                                                continue;
                                            }
                                            if (madness.cn == 35) {
                                                continue;
                                            }
                                        }
                                        Label_5264: {
                                            if (checkpoints.stage == 11) {
                                                if (madness.im > 4) {
                                                    break Label_5264;
                                                }
                                            }
                                            if (checkpoints.stage != 21) {
                                                continue;
                                            }
                                        }
                                        this.neverhit[a2] = true;
                                        this.avoidnlev[a2] = 20000;
                                    }
                                }
                                this.neverhit[0] = false;
                                if (this.trfix >= 2) {
                                    this.neverhit[0] = true;
                                    this.avoidnlev[0] = 10000;
                                }
                            }
                        }
                        if (checkpoints.stage == 22) {
                            if (madness.im != xtgraphics.nplayers - 1) {
                                this.neverhit[xtgraphics.nplayers - 1] = false;
                                Label_5423: {
                                    if (this.attack != 0) {
                                        if (this.acr == xtgraphics.nplayers - 1) {
                                            break Label_5423;
                                        }
                                    }
                                    this.neverhit[xtgraphics.nplayers - 1] = true;
                                }
                                this.avoidnlev[xtgraphics.nplayers - 1] = (int)(2000.0f * this.m.random()) + 5000;
                            }
                        }
                    }
                    this.trickprf = (madness.power - 38.0f) / 50.0f - this.m.random() / 2.0f;
                    if (madness.power < 60.0f) {
                        this.trickprf = -1.0f;
                    }
                    if (checkpoints.stage == 3) {
                        if (madness.im == 10) {
                            if (this.trickprf > 0.7) {
                                if (xtgraphics.classicmode) {
                                    this.trickprf = 0.7f;
                                }
                            }
                        }
                    }
                    if (checkpoints.stage == 6) {
                        if (this.trickprf > 0.3) {
                            this.trickprf = 0.3f;
                        }
                    }
                    if (checkpoints.stage == 8) {
                        if (this.trickprf > 0.2) {
                            this.trickprf = 0.2f;
                        }
                    }
                    Label_5740: {
                        if (checkpoints.stage == 9) {
                            if (xtgraphics.classicmode) {
                                if (this.trickprf > 0.5) {
                                    this.trickprf = 0.5f;
                                }
                                if (madness.im != 10) {
                                    if (madness.im != 9) {
                                        break Label_5740;
                                    }
                                }
                                if (this.trickprf > 0.3) {
                                    this.trickprf = 0.3f;
                                }
                            }
                        }
                    }
                    if (checkpoints.stage == 11) {
                        if (this.trickprf != -1.0f) {
                            if (xtgraphics.classicmode) {
                                this.trickprf *= 0.75f;
                            }
                        }
                    }
                    Label_5862: {
                        if (checkpoints.stage == 12) {
                            if (madness.pcleared != 55) {
                                if (madness.pcleared != 7) {
                                    break Label_5862;
                                }
                            }
                            if (xtgraphics.classicmode) {
                                this.trickprf = -1.0f;
                                this.stuntf = 5;
                            }
                        }
                    }
                    if (checkpoints.stage == 13) {
                        if (this.trickprf > 0.4) {
                            if (xtgraphics.classicmode) {
                                this.trickprf = 0.4f;
                            }
                        }
                    }
                    if (checkpoints.stage == 14) {
                        if (this.trickprf > 0.5) {
                            if (xtgraphics.classicmode) {
                                this.trickprf = 0.5f;
                            }
                        }
                    }
                    if (checkpoints.stage == 17) {
                        this.trickprf = -1.0f;
                    }
                    if (this.m.random() <= madness.hitmag / (float)madness.maxmag[madness.cn]) {
                        this.perfection = true;
                    }
                    else {
                        this.perfection = false;
                    }
                    if (100.0f * madness.hitmag / madness.maxmag[madness.cn] > 60.0f) {
                        this.perfection = true;
                    }
                    Label_6197: {
                        if (checkpoints.stage != 6) {
                            if (checkpoints.stage != 8) {
                                if (checkpoints.stage != 9) {
                                    if (checkpoints.stage != 10) {
                                        if (checkpoints.stage != 11) {
                                            if (checkpoints.stage != 12) {
                                                if (checkpoints.stage != 14) {
                                                    if (checkpoints.stage != 16) {
                                                        if (!xtgraphics.careermode) {
                                                            break Label_6197;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        this.perfection = true;
                    }
                    Label_6243: {
                        if (xtgraphics.dontdisplay) {
                            if (xtgraphics.ptmatch != 2) {
                                if (xtgraphics.ptmatch != 3) {
                                    break Label_6243;
                                }
                            }
                            this.perfection = true;
                        }
                    }
                    Label_24021: {
                        if (this.attack == 0) {
                            boolean flag1 = true;
                            Label_6345: {
                                if (checkpoints.stage != 1) {
                                    if (checkpoints.stage != 4) {
                                        if (checkpoints.stage != 9) {
                                            if (checkpoints.stage != 13) {
                                                if (checkpoints.stage != 16) {
                                                    break Label_6345;
                                                }
                                                if (!xtgraphics.classicmode) {
                                                    break Label_6345;
                                                }
                                            }
                                        }
                                    }
                                }
                                flag1 = this.afta;
                            }
                            Label_6404: {
                                if (checkpoints.stage != 8) {
                                    if (checkpoints.stage != 6) {
                                        if (checkpoints.stage != 10) {
                                            if (checkpoints.stage != 14) {
                                                break Label_6404;
                                            }
                                        }
                                    }
                                }
                                flag1 = false;
                            }
                            boolean flag2 = false;
                            Label_6451: {
                                if (checkpoints.stage == 3) {
                                    if (madness.cn != 9) {
                                        if (madness.cn != 32) {
                                            break Label_6451;
                                        }
                                    }
                                    flag2 = true;
                                }
                            }
                            Label_6601: {
                                if (xtgraphics.classicmode) {
                                    Label_6509: {
                                        if (checkpoints.stage == 8) {
                                            if (madness.cn != 11) {
                                                if (madness.cn != 34) {
                                                    break Label_6509;
                                                }
                                            }
                                            flag2 = true;
                                        }
                                    }
                                    if (checkpoints.stage == 9) {
                                        if (checkpoints.clear[0] >= 20) {
                                            flag2 = true;
                                        }
                                    }
                                    if (checkpoints.stage != 11) {
                                        if (checkpoints.stage != 13) {
                                            if (checkpoints.stage != 15) {
                                                if (checkpoints.stage != 16) {
                                                    break Label_6601;
                                                }
                                            }
                                        }
                                    }
                                    flag2 = true;
                                }
                            }
                            int j2 = 60;
                            Label_6678: {
                                if (checkpoints.stage != 3) {
                                    if (checkpoints.stage != 11) {
                                        if (checkpoints.stage != 17) {
                                            if (checkpoints.stage != 10) {
                                                if (checkpoints.stage != 8) {
                                                    break Label_6678;
                                                }
                                            }
                                        }
                                    }
                                }
                                j2 = 30;
                            }
                            Label_6737: {
                                if (checkpoints.stage != 2) {
                                    if (checkpoints.stage != 13) {
                                        break Label_6737;
                                    }
                                }
                                if (madness.cn != 13) {
                                    if (madness.cn != 36) {
                                        break Label_6737;
                                    }
                                }
                                j2 = 50;
                            }
                            if (checkpoints.stage == 4) {
                                j2 = 20;
                            }
                            if (checkpoints.stage == 5) {
                                if (madness.im != 6) {
                                    j2 = 40;
                                }
                            }
                            if (checkpoints.stage == 7) {
                                j2 = 40;
                            }
                            Label_6849: {
                                if (checkpoints.stage == 8) {
                                    if (madness.cn != 11) {
                                        if (madness.cn != 34) {
                                            break Label_6849;
                                        }
                                    }
                                    j2 = 40;
                                }
                            }
                            if (checkpoints.stage == 9) {
                                if (flag2) {
                                    j2 = 30;
                                }
                            }
                            if (checkpoints.stage == 11) {
                                if (this.bulistc) {
                                    j2 = 30;
                                }
                            }
                            if (checkpoints.stage == 12) {
                                j2 = 50;
                            }
                            if (checkpoints.stage == 15) {
                                if (this.bulistc) {
                                    j2 = 40;
                                }
                            }
                            if (checkpoints.stage == 16) {
                                if (xtgraphics.classicmode) {
                                    if (madness.cn == 11) {
                                        if (checkpoints.clear[0] == 27) {
                                            j2 = 0;
                                        }
                                    }
                                    Label_7047: {
                                        if (madness.cn != 15) {
                                            if (madness.cn != 9) {
                                                break Label_7047;
                                            }
                                        }
                                        j2 = 50;
                                    }
                                    if (madness.cn == 11) {
                                        j2 = 40;
                                    }
                                    if (checkpoints.pos[0] > checkpoints.pos[madness.im]) {
                                        j2 = 80;
                                    }
                                }
                            }
                            int i4 = 0;
                            do {
                                if (i4 != madness.im) {
                                    if (checkpoints.clear[i4] != -1) {
                                        int l5 = conto.xz;
                                        if (this.zyinv) {
                                            l5 += 180;
                                        }
                                        while (l5 < 0) {
                                            l5 += 360;
                                        }
                                        while (l5 > 180) {
                                            l5 -= 360;
                                        }
                                        char c4 = '\0';
                                        if (checkpoints.opx[i4] - conto.x >= 0) {
                                            c4 = '´';
                                        }
                                        int i5;
                                        for (i5 = (int)('Z' + c4 + Math.atan((checkpoints.opz[i4] - conto.z) / (double)(checkpoints.opx[i4] - conto.x)) / 0.017453292519943295); i5 < 0; i5 += 360) {}
                                        while (i5 > 180) {
                                            i5 -= 360;
                                        }
                                        int k8 = Math.abs(l5 - i5);
                                        if (k8 > 180) {
                                            k8 = Math.abs(k8 - 360);
                                        }
                                        int l6 = 2000 * (Math.abs(checkpoints.clear[i4] - madness.clear) + 1);
                                        Label_7427: {
                                            if (checkpoints.stage == 3) {
                                                if (madness.cn != 9) {
                                                    if (madness.cn != 32) {
                                                        break Label_7427;
                                                    }
                                                }
                                                if (l6 < 12000) {
                                                    l6 = 12000;
                                                }
                                            }
                                        }
                                        if (checkpoints.stage == 4) {
                                            if (l6 < 4000) {
                                                l6 = 4000;
                                            }
                                        }
                                        Label_7522: {
                                            if (checkpoints.stage == 8) {
                                                if (madness.cn != 11) {
                                                    if (madness.cn != 34) {
                                                        break Label_7522;
                                                    }
                                                }
                                                if (l6 < 12000) {
                                                    l6 = 12000;
                                                }
                                                k8 = 10;
                                            }
                                        }
                                        Label_7592: {
                                            if (checkpoints.stage == 9) {
                                                if (madness.pcleared != 13) {
                                                    if (madness.pcleared != 33) {
                                                        if (!flag2) {
                                                            break Label_7592;
                                                        }
                                                    }
                                                }
                                                if (l6 < 12000) {
                                                    l6 = 12000;
                                                }
                                            }
                                        }
                                        if (checkpoints.stage == 11) {
                                            if (xtgraphics.classicmode) {
                                                if (!this.bulistc) {
                                                    if (l6 < 6000) {
                                                        l6 = 6000;
                                                    }
                                                }
                                                else {
                                                    l6 = 8000;
                                                    k8 = 10;
                                                    this.afta = true;
                                                }
                                            }
                                        }
                                        if (checkpoints.stage == 12) {
                                            if (this.bulistc) {
                                                l6 = 6000;
                                                k8 = 10;
                                            }
                                        }
                                        if (checkpoints.stage == 13) {
                                            l6 = 21000;
                                        }
                                        if (checkpoints.stage == 15) {
                                            if (xtgraphics.classicmode) {
                                                l6 *= Math.abs(checkpoints.clear[i4] - madness.clear) + 1;
                                                if (this.bulistc) {
                                                    l6 = 4000 * (Math.abs(checkpoints.clear[i4] - madness.clear) + 1);
                                                    k8 = 10;
                                                }
                                            }
                                        }
                                        if (checkpoints.stage == 10) {
                                            l6 = 16000;
                                        }
                                        Label_7890: {
                                            if (xtgraphics.dontdisplay) {
                                                if (xtgraphics.ptmatch != 1) {
                                                    if (xtgraphics.ptmatch != 4) {
                                                        if (xtgraphics.ptmatch != 7) {
                                                            break Label_7890;
                                                        }
                                                    }
                                                }
                                                l6 = 80000;
                                            }
                                        }
                                        if (checkpoints.stage == 16) {
                                            if (xtgraphics.classicmode) {
                                                if (madness.cn == 13) {
                                                    if (this.bulistc) {
                                                        if (this.oupnt == 33) {
                                                            l6 = 17000;
                                                        }
                                                        if (this.oupnt == 51) {
                                                            l6 = 30000;
                                                        }
                                                        if (this.oupnt == 15) {
                                                            if (checkpoints.clear[0] >= 14) {
                                                                l6 = 60000;
                                                            }
                                                        }
                                                        k8 = 10;
                                                    }
                                                }
                                                Label_8070: {
                                                    if (madness.cn != 15) {
                                                        if (madness.cn != 9) {
                                                            break Label_8070;
                                                        }
                                                    }
                                                    l6 *= Math.abs(checkpoints.clear[i4] - madness.clear) + 1;
                                                }
                                                if (madness.cn == 11) {
                                                    l6 = 4000 * (Math.abs(checkpoints.clear[i4] - madness.clear) + 1);
                                                }
                                            }
                                        }
                                        int i6 = 85 + 15 * (Math.abs(checkpoints.clear[i4] - madness.clear) + 1);
                                        if (checkpoints.stage == 13) {
                                            if (xtgraphics.classicmode) {
                                                i6 = 45;
                                            }
                                        }
                                        Label_8269: {
                                            if (checkpoints.stage == 16) {
                                                if (xtgraphics.classicmode) {
                                                    if (madness.cn != 15) {
                                                        if (madness.cn != 9) {
                                                            if (madness.cn != 11) {
                                                                if (madness.cn != 14) {
                                                                    break Label_8269;
                                                                }
                                                            }
                                                        }
                                                    }
                                                    i6 = 50 + 70 * Math.abs(checkpoints.clear[i4] - madness.clear);
                                                }
                                            }
                                        }
                                        boolean rightfloor = true;
                                        if (xtgraphics.careermode) {
                                            if (checkpoints.stage == 13) {
                                                if (xtgraphics.floor[i4] != xtgraphics.floor[madness.im]) {
                                                    rightfloor = false;
                                                }
                                            }
                                        }
                                        if (k8 < i6) {
                                            if (this.py(conto.x / 100, checkpoints.opx[i4] / 100, conto.z / 100, checkpoints.opz[i4] / 100) < l6) {
                                                if (madness.power > j2) {
                                                    if (rightfloor) {
                                                        float f2 = (float)(35 - Math.abs(checkpoints.clear[i4] - madness.clear) * 10);
                                                        if (f2 < 1.0f) {
                                                            f2 = 1.0f;
                                                        }
                                                        float f3 = (checkpoints.pos[madness.im] + 1) * (5 - checkpoints.pos[i4]) / f2;
                                                        Label_9205: {
                                                            if (xtgraphics.classicmode) {
                                                                Label_8563: {
                                                                    if (checkpoints.stage == 8) {
                                                                        Label_8547: {
                                                                            if (madness.cn != 34) {
                                                                                if (madness.cn == 36) {
                                                                                    if (this.bulistc) {
                                                                                        break Label_8547;
                                                                                    }
                                                                                }
                                                                                f3 = 0.0f;
                                                                                break Label_8563;
                                                                            }
                                                                        }
                                                                        f3 *= 1.5f;
                                                                    }
                                                                }
                                                                Label_8683: {
                                                                    if (checkpoints.stage == 9) {
                                                                        if (i4 != 0) {
                                                                            f3 *= 0.5;
                                                                        }
                                                                        if (madness.pcleared != 13) {
                                                                            if (madness.pcleared != 33) {
                                                                                if (!flag2) {
                                                                                    f3 *= 0.5f;
                                                                                }
                                                                            }
                                                                        }
                                                                        if (madness.im != 10) {
                                                                            if (madness.im != 9) {
                                                                                break Label_8683;
                                                                            }
                                                                        }
                                                                        if (i4 != 0) {
                                                                            f3 = 0.0f;
                                                                        }
                                                                    }
                                                                }
                                                                Label_8740: {
                                                                    Label_8737: {
                                                                        if (checkpoints.stage != 6) {
                                                                            if (checkpoints.stage == 10) {
                                                                                if (!this.bulistc) {
                                                                                    break Label_8737;
                                                                                }
                                                                            }
                                                                            if (checkpoints.stage != 14) {
                                                                                break Label_8740;
                                                                            }
                                                                        }
                                                                    }
                                                                    f3 = 0.0f;
                                                                }
                                                                if (checkpoints.stage == 11) {
                                                                    if (madness.cn == 36) {
                                                                        if (this.bulistc) {
                                                                            if (i4 == 0) {
                                                                                f3 = 1.0f;
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                                if (checkpoints.stage == 12) {
                                                                    if (madness.cn != 34) {
                                                                        if (madness.cn != 36) {
                                                                            f3 = 0.0f;
                                                                        }
                                                                    }
                                                                    if (madness.cn == 36) {
                                                                        if (i4 == 0) {
                                                                            f3 = 1.0f;
                                                                        }
                                                                    }
                                                                }
                                                                if (checkpoints.stage == 15) {
                                                                    if (checkpoints.pos[madness.im] == 0) {
                                                                        f3 *= 0.5;
                                                                    }
                                                                    if (checkpoints.pos[0] < checkpoints.pos[madness.im]) {
                                                                        f3 *= 2.0f;
                                                                    }
                                                                    if (this.bulistc) {
                                                                        if (i4 == 0) {
                                                                            f3 = 1.0f;
                                                                        }
                                                                    }
                                                                }
                                                                if (checkpoints.stage == 16) {
                                                                    if (madness.cn == 37) {
                                                                        f3 *= 0.5;
                                                                    }
                                                                    else if (checkpoints.pos[0] < checkpoints.pos[madness.im]) {
                                                                        if (checkpoints.clear[0] - checkpoints.clear[madness.im] != 1) {
                                                                            f3 *= 2.0f;
                                                                        }
                                                                    }
                                                                    if (madness.cn == 36) {
                                                                        if (i4 == 0) {
                                                                            f3 = 1.0f;
                                                                        }
                                                                    }
                                                                    Label_9136: {
                                                                        if (checkpoints.pos[madness.im] != 0) {
                                                                            if (checkpoints.pos[madness.im] != 1) {
                                                                                break Label_9136;
                                                                            }
                                                                            if (checkpoints.pos[0] != 0) {
                                                                                break Label_9136;
                                                                            }
                                                                        }
                                                                        f3 = 0.0f;
                                                                    }
                                                                    if (checkpoints.clear[madness.im] - checkpoints.clear[0] >= 5) {
                                                                        if (i4 == 0) {
                                                                            f3 = 1.0f;
                                                                        }
                                                                    }
                                                                    if (madness.cn != 33) {
                                                                        if (madness.cn != 35) {
                                                                            break Label_9205;
                                                                        }
                                                                    }
                                                                    f3 = 0.0f;
                                                                }
                                                            }
                                                        }
                                                        Label_9296: {
                                                            if (xtgraphics.dontdisplay) {
                                                                Label_9265: {
                                                                    if (xtgraphics.ptmatch != 1) {
                                                                        if (xtgraphics.ptmatch != 4) {
                                                                            if (xtgraphics.ptmatch != 5) {
                                                                                break Label_9265;
                                                                            }
                                                                        }
                                                                    }
                                                                    f3 = 900.0f;
                                                                }
                                                                if (xtgraphics.ptmatch != 2) {
                                                                    if (xtgraphics.ptmatch != 3) {
                                                                        break Label_9296;
                                                                    }
                                                                }
                                                                f3 = 0.0f;
                                                            }
                                                        }
                                                        Label_12264: {
                                                            if (xtgraphics.careermode) {
                                                                Label_11804: {
                                                                    if (checkpoints.stage < xtgraphics.unlocked[1]) {
                                                                        if (!xtgraphics.hardstage) {
                                                                            Label_9474: {
                                                                                if (madness.cn != 3) {
                                                                                    if (madness.cn != 26) {
                                                                                        if (madness.cn != 12) {
                                                                                            if (madness.cn != 35) {
                                                                                                if (madness.cn != 17) {
                                                                                                    if (madness.cn != 1) {
                                                                                                        if (madness.cn != 24) {
                                                                                                            break Label_9474;
                                                                                                        }
                                                                                                    }
                                                                                                    if (madness.specialact) {
                                                                                                        break Label_9474;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                                if (checkpoints.stage > 3) {
                                                                                    f3 = 0.0f;
                                                                                }
                                                                                else {
                                                                                    f3 = 0.2f;
                                                                                }
                                                                            }
                                                                            Label_9518: {
                                                                                if (checkpoints.stage != 5) {
                                                                                    if (checkpoints.stage != 10) {
                                                                                        if (checkpoints.stage != 14) {
                                                                                            break Label_9518;
                                                                                        }
                                                                                    }
                                                                                }
                                                                                f3 = 0.0f;
                                                                            }
                                                                            Label_9621: {
                                                                                if (checkpoints.stage >= 7) {
                                                                                    if (madness.aistrsp[madness.cn] != 0) {
                                                                                        if (checkpoints.stage != 16) {
                                                                                            break Label_9621;
                                                                                        }
                                                                                        if (madness.cn != 15) {
                                                                                            if (madness.cn != 3) {
                                                                                                if (madness.cn != 38) {
                                                                                                    if (madness.cn != 26) {
                                                                                                        break Label_9621;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                    f3 = 0.0f;
                                                                                }
                                                                            }
                                                                            if (checkpoints.pos[madness.im] == 0) {
                                                                                if (this.variable.completed[madness.im] >= 60) {
                                                                                    f3 = 0.0f;
                                                                                }
                                                                            }
                                                                            Label_9756: {
                                                                                if (checkpoints.stage == 13) {
                                                                                    f3 = 0.0f;
                                                                                    if (conto.floorguardian) {
                                                                                        if (i4 != 0) {
                                                                                            if (i4 != 1) {
                                                                                                if (i4 != 4) {
                                                                                                    if (i4 != 7) {
                                                                                                        break Label_9756;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                        f3 = 0.25f;
                                                                                        if (madness.specialact) {
                                                                                            f3 = 1.0f;
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                            Label_9843: {
                                                                                if (checkpoints.stage == 19) {
                                                                                    if (madness.cn != 12) {
                                                                                        if (madness.cn != 14) {
                                                                                            if (madness.cn != 17) {
                                                                                                if (madness.cn != 35) {
                                                                                                    if (madness.cn != 37) {
                                                                                                        break Label_9843;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                    f3 = 0.0f;
                                                                                }
                                                                            }
                                                                            if (checkpoints.stage == 22) {
                                                                                if (madness.cn == 18) {
                                                                                    f3 = 0.0f;
                                                                                }
                                                                            }
                                                                            break Label_11804;
                                                                        }
                                                                    }
                                                                    Label_9973: {
                                                                        if (checkpoints.stage == 3) {
                                                                            if (madness.cn != 2) {
                                                                                if (madness.cn != 6) {
                                                                                    if (madness.cn != 25) {
                                                                                        if (madness.cn != 29) {
                                                                                            if (madness.cn != 9) {
                                                                                                f3 = 0.0f;
                                                                                                break Label_9973;
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                            f3 = 0.2f;
                                                                        }
                                                                    }
                                                                    if (checkpoints.stage == 11) {
                                                                        f3 = 0.0f;
                                                                        if (i4 == 0) {
                                                                            Label_10033: {
                                                                                if (madness.cn != 13) {
                                                                                    if (madness.cn != 36) {
                                                                                        break Label_10033;
                                                                                    }
                                                                                }
                                                                                f3 = 0.7f;
                                                                            }
                                                                            Label_10127: {
                                                                                if (madness.cn != 11) {
                                                                                    if (madness.cn != 34) {
                                                                                        break Label_10127;
                                                                                    }
                                                                                }
                                                                                if (checkpoints.clear[0] > 4) {
                                                                                    if (checkpoints.clear[madness.im] + 1 >= checkpoints.clear[0]) {
                                                                                        break Label_10127;
                                                                                    }
                                                                                }
                                                                                if (!madness.specialact) {
                                                                                    f3 = 0.25f;
                                                                                }
                                                                                else {
                                                                                    f3 = 0.5f;
                                                                                }
                                                                            }
                                                                            if (madness.beast[madness.im]) {
                                                                                if (checkpoints.clear[0] > 4) {
                                                                                    if (madness.moment[madness.cn] > usermad.moment[usermad.cn]) {
                                                                                        if (checkpoints.clear[madness.im] + 2 >= checkpoints.clear[0]) {
                                                                                            f3 = 0.0f;
                                                                                        }
                                                                                        else {
                                                                                            f3 = 0.6f;
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                    Label_10448: {
                                                                        if (checkpoints.stage == 13) {
                                                                            f3 = 0.0f;
                                                                            if (madness.beast[madness.im]) {
                                                                                if (madness.specialact) {
                                                                                    if (i4 == 0) {
                                                                                        f3 = 1.0f;
                                                                                    }
                                                                                }
                                                                            }
                                                                            Label_10372: {
                                                                                if (madness.cn != 13) {
                                                                                    if (madness.cn != 36) {
                                                                                        break Label_10372;
                                                                                    }
                                                                                }
                                                                                if (madness.specialact) {
                                                                                    if (i4 == 0) {
                                                                                        if (checkpoints.clear[0] <= 13) {
                                                                                            if (checkpoints.pos[madness.im] > 2) {
                                                                                                f3 = 1.0f;
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                            if (conto.floorguardian) {
                                                                                if (i4 != 0) {
                                                                                    if (i4 != 1) {
                                                                                        if (i4 != 4) {
                                                                                            if (i4 != 7) {
                                                                                                break Label_10448;
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                                f3 = 0.25f;
                                                                                if (madness.specialact) {
                                                                                    f3 = 1.0f;
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                    Label_10618: {
                                                                        if (checkpoints.stage == 16) {
                                                                            Label_10577: {
                                                                                if (madness.cn != 15) {
                                                                                    if (madness.cn != 14) {
                                                                                        if (madness.cn != 12) {
                                                                                            if (madness.cn != 10) {
                                                                                                if (madness.cn != 33) {
                                                                                                    if (madness.cn != 35) {
                                                                                                        if (madness.cn != 37) {
                                                                                                            if (madness.cn != 38) {
                                                                                                                break Label_10577;
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                                f3 = 0.0f;
                                                                            }
                                                                            if (madness.cn != 13) {
                                                                                if (madness.cn != 36) {
                                                                                    break Label_10618;
                                                                                }
                                                                            }
                                                                            if (i4 == 0) {
                                                                                f3 = 1.0f;
                                                                            }
                                                                        }
                                                                    }
                                                                    Label_10675: {
                                                                        if (checkpoints.stage == 17) {
                                                                            if (madness.im != xtgraphics.nplayers - 1) {
                                                                                if (!madness.shadowcar) {
                                                                                    f3 = 0.5f;
                                                                                    break Label_10675;
                                                                                }
                                                                            }
                                                                            f3 = 0.0f;
                                                                        }
                                                                    }
                                                                    Label_10734: {
                                                                        if (checkpoints.stage != 4) {
                                                                            if (!xtgraphics.bonusstage[2]) {
                                                                                if (checkpoints.stage != 8) {
                                                                                    if (checkpoints.stage != 15) {
                                                                                        break Label_10734;
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                        f3 = 0.0f;
                                                                    }
                                                                    if (checkpoints.stage == 7) {
                                                                        if (madness.beast[madness.im]) {
                                                                            if (checkpoints.clear[0] < 3) {
                                                                                f3 = 0.0f;
                                                                            }
                                                                            else {
                                                                                Label_10986: {
                                                                                    Label_10983: {
                                                                                        if (i4 == 0) {
                                                                                            if (madness.specialact) {
                                                                                                if (checkpoints.wasted < 7) {
                                                                                                    break Label_10983;
                                                                                                }
                                                                                            }
                                                                                            if (checkpoints.clear[0] < 3) {
                                                                                                f3 = 0.2f;
                                                                                            }
                                                                                            else {
                                                                                                f3 = 0.5f;
                                                                                            }
                                                                                            break Label_10986;
                                                                                        }
                                                                                    }
                                                                                    f3 = 0.0f;
                                                                                }
                                                                            }
                                                                        }
                                                                        else {
                                                                            Label_10894: {
                                                                                Label_10891: {
                                                                                    if (madness.cn != 9) {
                                                                                        if (madness.cn != 11) {
                                                                                            if (madness.cn != 32) {
                                                                                                if (madness.cn != 34) {
                                                                                                    break Label_10891;
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                    if (checkpoints.pos[madness.im] > 2) {
                                                                                        if (i4 != 0) {
                                                                                            f3 = 0.0f;
                                                                                        }
                                                                                        else if (checkpoints.clear[0] < 3) {
                                                                                            f3 = 0.15f;
                                                                                        }
                                                                                        else {
                                                                                            f3 = 0.5f;
                                                                                        }
                                                                                        break Label_10894;
                                                                                    }
                                                                                }
                                                                                f3 = 0.0f;
                                                                            }
                                                                        }
                                                                    }
                                                                    Label_11092: {
                                                                        if (checkpoints.stage == 2) {
                                                                            if (madness.cn != 1) {
                                                                                if (madness.cn != 24) {
                                                                                    if (madness.cn != 8) {
                                                                                        if (madness.cn != 3) {
                                                                                            if (madness.cn != 26) {
                                                                                                if (madness.cn != 31) {
                                                                                                    break Label_11092;
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                            f3 = 0.0f;
                                                                        }
                                                                    }
                                                                    if (checkpoints.stage == 12) {
                                                                        f3 = 0.0f;
                                                                        if (i4 == 0) {
                                                                            Label_11150: {
                                                                                if (madness.cn != 13) {
                                                                                    if (madness.cn != 36) {
                                                                                        break Label_11150;
                                                                                    }
                                                                                }
                                                                                f3 = 1.0f;
                                                                            }
                                                                            if (madness.beast[madness.im]) {
                                                                                if (checkpoints.clear[0] >= 4) {
                                                                                    if (checkpoints.clear[0] > checkpoints.clear[madness.im] + 2) {
                                                                                        f3 = 0.75f;
                                                                                    }
                                                                                }
                                                                                if (checkpoints.clear[0] < 4) {
                                                                                    if (checkpoints.clear[madness.im] >= 4) {
                                                                                        if (checkpoints.pos[madness.im] > 0) {
                                                                                            f3 = 0.25f;
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                    if (checkpoints.stage == 19) {
                                                                        if (madness.cn == 18) {
                                                                            f3 = 1.0f;
                                                                        }
                                                                        else {
                                                                            Label_11478: {
                                                                                Label_11465: {
                                                                                    if (i4 == 0) {
                                                                                        if (checkpoints.pos[madness.im] >= checkpoints.pos[usermad.im]) {
                                                                                            if (this.variable.biglead[0]) {
                                                                                                if (this.variable.completed[0] >= 60) {
                                                                                                    break Label_11465;
                                                                                                }
                                                                                            }
                                                                                            if (madness.cn != 17) {
                                                                                                if (madness.cn != 14) {
                                                                                                    if (madness.cn != 12) {
                                                                                                        if (madness.cn != 1) {
                                                                                                            if (madness.cn != 24) {
                                                                                                                if (madness.cn != 35) {
                                                                                                                    if (madness.cn != 37) {
                                                                                                                        f3 = 0.7f;
                                                                                                                        break Label_11478;
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                                f3 = 0.0f;
                                                                            }
                                                                        }
                                                                    }
                                                                    Label_11563: {
                                                                        if (checkpoints.stage == 21) {
                                                                            if (madness.cn != 18) {
                                                                                if (madness.cn != 13) {
                                                                                    if (madness.cn != 36) {
                                                                                        f3 = 0.0f;
                                                                                        break Label_11563;
                                                                                    }
                                                                                }
                                                                            }
                                                                            if (i4 == 0) {
                                                                                f3 = 1.0f;
                                                                            }
                                                                        }
                                                                    }
                                                                    Label_11619: {
                                                                        if (checkpoints.stage == 22) {
                                                                            if (madness.im != xtgraphics.nplayers - 1) {
                                                                                if (checkpoints.dested[xtgraphics.nplayers - 1] != 0) {
                                                                                    break Label_11619;
                                                                                }
                                                                            }
                                                                            f3 = 0.0f;
                                                                        }
                                                                    }
                                                                    if (checkpoints.stage == 23) {
                                                                        Label_11793: {
                                                                            if (i4 == 0) {
                                                                                if (madness.cn != 17) {
                                                                                    if (madness.cn != 14) {
                                                                                        if (madness.cn != 12) {
                                                                                            if (madness.cn == 1) {
                                                                                                if (!madness.specialact) {
                                                                                                    break Label_11793;
                                                                                                }
                                                                                            }
                                                                                            if (checkpoints.pos[madness.im] >= checkpoints.pos[0]) {
                                                                                                if (checkpoints.pos[madness.im] >= 3) {
                                                                                                    if (madness.cn != 24) {
                                                                                                        if (madness.cn != 35) {
                                                                                                            if (madness.cn != 37) {
                                                                                                                f3 = 1.0f;
                                                                                                                break Label_11804;
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                        f3 = 0.0f;
                                                                    }
                                                                }
                                                                Label_11889: {
                                                                    Label_11886: {
                                                                        if (checkpoints.stage == 10) {
                                                                            if (!this.bulistc) {
                                                                                break Label_11886;
                                                                            }
                                                                        }
                                                                        if (checkpoints.stage != 14) {
                                                                            if (checkpoints.stage != 5) {
                                                                                if (!xtgraphics.bonusstage[1]) {
                                                                                    if (checkpoints.stage != 9) {
                                                                                        break Label_11889;
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                    f3 = 0.0f;
                                                                }
                                                                if (checkpoints.stage == 11) {
                                                                    if (!xtgraphics.bonusstage[1]) {
                                                                        if (xtgraphics.undead[madness.im]) {
                                                                            if (madness.im > 4) {
                                                                                f3 = 1.0f;
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                                Label_12023: {
                                                                    if (checkpoints.stage == 18) {
                                                                        if (madness.cn != 13) {
                                                                            if (madness.cn != 36) {
                                                                                f3 = 0.0f;
                                                                                break Label_12023;
                                                                            }
                                                                        }
                                                                        if (i4 != 0) {
                                                                            f3 = 0.0f;
                                                                        }
                                                                        else {
                                                                            f3 = 1.0f;
                                                                        }
                                                                    }
                                                                }
                                                                if (checkpoints.stage == 6) {
                                                                    if (madness.beast[madness.im]) {
                                                                        Label_12261: {
                                                                            if (i4 == 0) {
                                                                                if (madness.specialact) {
                                                                                    if (checkpoints.wasted < 7) {
                                                                                        if (checkpoints.clear[0] < 3) {
                                                                                            break Label_12261;
                                                                                        }
                                                                                    }
                                                                                }
                                                                                f3 = 0.8f - checkpoints.wasted * 0.1f;
                                                                                if (f3 < 0.2f) {
                                                                                    f3 = 0.2f;
                                                                                }
                                                                                break Label_12264;
                                                                            }
                                                                        }
                                                                        f3 = 0.0f;
                                                                    }
                                                                    else {
                                                                        Label_12168: {
                                                                            Label_12165: {
                                                                                if (madness.cn != 9) {
                                                                                    if (madness.cn != 32) {
                                                                                        break Label_12165;
                                                                                    }
                                                                                }
                                                                                if (checkpoints.pos[madness.im] > 2) {
                                                                                    if (i4 != 0) {
                                                                                        f3 = 0.0f;
                                                                                    }
                                                                                    else {
                                                                                        Label_12152: {
                                                                                            if (checkpoints.wasted < 4) {
                                                                                                if (checkpoints.clear[0] < 3) {
                                                                                                    f3 = 0.15f;
                                                                                                    break Label_12152;
                                                                                                }
                                                                                            }
                                                                                            f3 = 0.5f;
                                                                                        }
                                                                                    }
                                                                                    break Label_12168;
                                                                                }
                                                                            }
                                                                            f3 = 0.0f;
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        if (i4 != 0) {
                                                            if (checkpoints.pos[0] < checkpoints.pos[madness.im]) {
                                                                if (!xtgraphics.undead[madness.im]) {
                                                                    f3 = 0.0f;
                                                                }
                                                            }
                                                        }
                                                        if (i4 != 0) {
                                                            if (flag2) {
                                                                f3 = 0.0f;
                                                            }
                                                        }
                                                        if (checkpoints.stage == 7) {
                                                            if (xtgraphics.classicmode) {
                                                                if (madness.im == xtgraphics.nplayers - 1) {
                                                                    if (i4 == 0) {
                                                                        f3 *= 1.5;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        if (this.m.random() < f3) {
                                                            this.attack = 40 * (Math.abs(checkpoints.clear[i4] - madness.clear) + 1);
                                                            if (this.attack > 500) {
                                                                this.attack = 500;
                                                            }
                                                            this.aim = 0.0f;
                                                            if (checkpoints.stage == 3) {
                                                                if (madness.im == xtgraphics.nplayers - 1) {
                                                                    if (this.m.random() > this.m.random()) {
                                                                        this.aim = 1.0f;
                                                                    }
                                                                }
                                                            }
                                                            Label_12605: {
                                                                if (checkpoints.stage == 4) {
                                                                    if (i4 == 0) {
                                                                        if (checkpoints.pos[0] < checkpoints.pos[madness.im]) {
                                                                            this.aim = 1.5f;
                                                                            break Label_12605;
                                                                        }
                                                                    }
                                                                    this.aim = this.m.random();
                                                                }
                                                            }
                                                            if (checkpoints.stage == 5) {
                                                                this.aim = this.m.random() * 1.5f;
                                                            }
                                                            Label_12717: {
                                                                if (checkpoints.stage == 8) {
                                                                    if (madness.cn != 11) {
                                                                        if (madness.cn != 34) {
                                                                            break Label_12717;
                                                                        }
                                                                    }
                                                                    if (this.m.random() > this.m.random()) {
                                                                        this.aim = 0.76f + this.m.random() * 0.76f;
                                                                    }
                                                                }
                                                            }
                                                            Label_12764: {
                                                                if (checkpoints.stage == 9) {
                                                                    if (madness.pcleared != 13) {
                                                                        if (madness.pcleared != 33) {
                                                                            break Label_12764;
                                                                        }
                                                                    }
                                                                    this.aim = 1.0f;
                                                                }
                                                            }
                                                            Label_12928: {
                                                                if (checkpoints.stage != 12) {
                                                                    if (checkpoints.stage != 6) {
                                                                        if (checkpoints.stage != 15) {
                                                                            break Label_12928;
                                                                        }
                                                                        if (xtgraphics.bonusstage[2]) {
                                                                            break Label_12928;
                                                                        }
                                                                    }
                                                                }
                                                                if (!this.bulistc) {
                                                                    this.aim = this.m.random();
                                                                }
                                                                else {
                                                                    this.aim = 0.75f + this.m.random() / 2.0f;
                                                                    if (xtgraphics.bonusstage[1]) {
                                                                        if (this.attack > 80) {
                                                                            this.attack = 80;
                                                                        }
                                                                    }
                                                                    else if (this.attack > 150) {
                                                                        this.attack = 150;
                                                                    }
                                                                }
                                                            }
                                                            if (checkpoints.stage == 12) {
                                                                if (xtgraphics.classicmode) {
                                                                    if (this.m.random() > this.m.random()) {
                                                                        this.aim = 0.7f;
                                                                    }
                                                                    if (this.bulistc) {
                                                                        if (this.attack > 150) {
                                                                            this.attack = 150;
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                            if (checkpoints.stage == 13) {
                                                                if (this.attack > 60) {
                                                                    if (xtgraphics.classicmode) {
                                                                        this.attack = 60;
                                                                    }
                                                                }
                                                            }
                                                            if (checkpoints.stage == 15) {
                                                                if (xtgraphics.classicmode) {
                                                                    this.aim = this.m.random() * 1.5f;
                                                                    this.attack /= 2;
                                                                    if (this.m.random() <= this.m.random()) {
                                                                        this.exitattack = false;
                                                                    }
                                                                    else {
                                                                        this.exitattack = true;
                                                                    }
                                                                }
                                                            }
                                                            Label_13279: {
                                                                if (checkpoints.stage == 16) {
                                                                    if (xtgraphics.classicmode) {
                                                                        if (madness.cn != 36) {
                                                                            this.aim = this.m.random() * 1.5f;
                                                                            if (Math.abs(checkpoints.clear[i4] - madness.clear) > 2) {
                                                                                if (madness.cn != 37) {
                                                                                    break Label_13279;
                                                                                }
                                                                            }
                                                                            this.attack /= 3;
                                                                        }
                                                                        else {
                                                                            this.aim = 0.76f;
                                                                            this.attack = 150;
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                            if (checkpoints.dested[i4] == 0) {
                                                                this.acr = i4;
                                                            }
                                                            this.turntyp = (int)(1.0f + this.m.random() * 2.0f);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        if (flag1) {
                                            if (k8 > 100) {
                                                if (this.py(conto.x / 100, checkpoints.opx[i4] / 100, conto.z / 100, checkpoints.opz[i4] / 100) < 300) {
                                                    if (this.m.random() > 0.6 - checkpoints.pos[madness.im] / 10.0f) {
                                                        this.clrnce = 0;
                                                        this.acuracy = 0;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            } while (++i4 < xtgraphics.nplayers);
                            if (xtgraphics.careermode) {
                                Label_13821: {
                                    if (xtgraphics.bonusstage[0]) {
                                        if (madness.im == 9) {
                                            if (usermad.specialact) {
                                                if (madness.moment[madness.cn] <= usermad.moment[usermad.cn]) {
                                                    this.attack = 0;
                                                }
                                                else {
                                                    this.attack = 150;
                                                }
                                            }
                                            else {
                                                this.attack = 150;
                                            }
                                            this.acr = 0;
                                        }
                                        if (this.variable.needhelp[9]) {
                                            Label_13655: {
                                                if (madness.im == 10) {
                                                    if (madness.moment[madness.cn] <= usermad.moment[usermad.cn]) {
                                                        if (usermad.specialact) {
                                                            break Label_13655;
                                                        }
                                                    }
                                                    this.attack = 150;
                                                    this.acr = 0;
                                                }
                                            }
                                            if (madness.specialact) {
                                                if (!usermad.specialact) {
                                                    if (madness.cn != 23) {
                                                        if (madness.cn != 27) {
                                                            if (madness.cn != 30) {
                                                                if (checkpoints.wasted < 5) {
                                                                    break Label_13821;
                                                                }
                                                                if (madness.cn != 28) {
                                                                    if (madness.cn != 31) {
                                                                        if (!this.variable.needhelp[10]) {
                                                                            break Label_13821;
                                                                        }
                                                                        if (madness.cn != 25) {
                                                                            if (madness.cn != 29) {
                                                                                break Label_13821;
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                    this.attack = 150;
                                                    this.acr = 0;
                                                }
                                            }
                                        }
                                    }
                                }
                                if (xtgraphics.bonusstage[1]) {
                                    Label_13955: {
                                        if (madness.im != 3) {
                                            if (madness.im != 6) {
                                                if (madness.im != 7) {
                                                    if (madness.im != 5) {
                                                        if (madness.im != 1) {
                                                            if (madness.im != 2) {
                                                                if (madness.im != 4) {
                                                                    break Label_13955;
                                                                }
                                                            }
                                                        }
                                                        if (checkpoints.clear[0] >= 2) {
                                                            break Label_13955;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        this.acr = 0;
                                        this.attack = 30;
                                    }
                                    if (madness.specialact) {
                                        if (madness.cn != 35) {
                                            this.acr = 0;
                                            this.attack = 30;
                                        }
                                    }
                                }
                                if (xtgraphics.bonusstage[2]) {
                                    this.aim = this.m.random() / 2.0f + 0.5f;
                                    Label_14078: {
                                        if (madness.im != 1) {
                                            if (madness.im != 8) {
                                                break Label_14078;
                                            }
                                            if (!this.variable.needhelp[1]) {
                                                break Label_14078;
                                            }
                                        }
                                        this.acr = 0;
                                        this.attack = 20;
                                    }
                                    Label_14185: {
                                        if (madness.specialact) {
                                            if (madness.moment[madness.cn] > usermad.moment[usermad.cn]) {
                                                Label_14160: {
                                                    if (madness.cn != 33) {
                                                        if (madness.cn != 32) {
                                                            break Label_14160;
                                                        }
                                                    }
                                                    if (checkpoints.wasted < 4) {
                                                        break Label_14185;
                                                    }
                                                }
                                                if (madness.cn != 35) {
                                                    this.acr = 0;
                                                    this.attack = 20;
                                                }
                                            }
                                        }
                                    }
                                    if (usermad.specialact) {
                                        if (usermad.moment[usermad.cn] > madness.moment[madness.cn]) {
                                            this.attack = 0;
                                        }
                                    }
                                }
                                Label_14358: {
                                    if (checkpoints.stage == 11) {
                                        if (!xtgraphics.bonusstage[1]) {
                                            boolean hardstage = false;
                                            Label_14297: {
                                                if (xtgraphics.unlocked[1] != 11) {
                                                    if (!xtgraphics.hardstage) {
                                                        break Label_14297;
                                                    }
                                                }
                                                hardstage = true;
                                            }
                                            if (xtgraphics.undead[madness.im]) {
                                                if (!hardstage) {
                                                    if (madness.im > 4) {
                                                        break Label_14358;
                                                    }
                                                }
                                                this.acr = xtgraphics.undeadlock[madness.im];
                                                this.attack = 30;
                                            }
                                        }
                                    }
                                }
                                if (checkpoints.stage == 17) {
                                    if (madness.im >= 1) {
                                        if (madness.im <= 3) {
                                            this.attack = 50;
                                            this.acr = xtgraphics.undeadtarget;
                                            if (xtgraphics.undeadtarget != 0) {
                                                this.aim = 1.0f;
                                            }
                                            else {
                                                this.aim = this.m.random() / 2.0f + 0.75f;
                                            }
                                        }
                                    }
                                }
                                if (checkpoints.stage == 13) {
                                    this.aim = this.m.random() / 2.0f + 0.75f;
                                    if (conto.floorguardian) {
                                        if (!conto.guardswitch) {
                                            this.aim = this.m.random() / 2.0f + 0.95f;
                                            this.acr = 0;
                                            this.attack = 30;
                                        }
                                        else {
                                            for (int a3 = 0; a3 < xtgraphics.nplayers; ++a3) {
                                                if (xtgraphics.beastopponent[a3]) {
                                                    if (!xtgraphics.undead[a3]) {
                                                        if (checkpoints.dested[a3] == 0) {
                                                            if (xtgraphics.floor[madness.im] == xtgraphics.floor[a3]) {
                                                                if (!madness.specialact) {
                                                                    Label_14660: {
                                                                        if (xtgraphics.sc[a3] != 13) {
                                                                            if (xtgraphics.sc[a3] != 36) {
                                                                                break Label_14660;
                                                                            }
                                                                        }
                                                                        if (this.variable.needhelp[a3]) {
                                                                            continue;
                                                                        }
                                                                    }
                                                                    this.acr = a3;
                                                                    this.attack = 30;
                                                                    break;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    if (checkpoints.clear[0] >= 13) {
                                        if (madness.beast[madness.im]) {
                                            if (!conto.floorguardian) {
                                                if (madness.specialact) {
                                                    this.acr = 0;
                                                    this.attack = 30;
                                                }
                                            }
                                        }
                                    }
                                }
                                Label_23008: {
                                    Label_14854: {
                                        if (checkpoints.stage < xtgraphics.unlocked[1]) {
                                            if (!xtgraphics.bonstage) {
                                                if (!xtgraphics.hardstage) {
                                                    break Label_14854;
                                                }
                                            }
                                        }
                                        if (checkpoints.stage != 1) {
                                            Label_17365: {
                                                if (checkpoints.stage <= 4) {
                                                    if (checkpoints.stage > 1) {
                                                        Label_16875: {
                                                            if (madness.specialact) {
                                                                Label_16760: {
                                                                    Label_16584: {
                                                                        if (madness.cn != 0) {
                                                                            if (madness.cn != 4) {
                                                                                if (madness.cn != 7) {
                                                                                    if (madness.cn != 8) {
                                                                                        Label_16528: {
                                                                                            if (checkpoints.stage >= 2) {
                                                                                                if (madness.cn == 2) {
                                                                                                    break Label_16584;
                                                                                                }
                                                                                                if (madness.cn == 6) {
                                                                                                    break Label_16584;
                                                                                                }
                                                                                                if (madness.cn == 25) {
                                                                                                    break Label_16584;
                                                                                                }
                                                                                                if (madness.cn == 29) {
                                                                                                    break Label_16584;
                                                                                                }
                                                                                                if (madness.cn != 5) {
                                                                                                    if (madness.cn != 28) {
                                                                                                        break Label_16528;
                                                                                                    }
                                                                                                }
                                                                                                if (checkpoints.pos[0] == 0) {
                                                                                                    break Label_16584;
                                                                                                }
                                                                                                if (checkpoints.stage > 2) {
                                                                                                    break Label_16584;
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                        if (madness.cn != 23) {
                                                                                            if (madness.cn != 27) {
                                                                                                if (madness.cn != 30) {
                                                                                                    if (madness.cn != 31) {
                                                                                                        break Label_16760;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                    this.attack = 150;
                                                                    Label_16755: {
                                                                        if (checkpoints.pos[0] > 0) {
                                                                            if (madness.cn != 8) {
                                                                                if (madness.cn != 31) {
                                                                                    Label_16736: {
                                                                                        if (checkpoints.stage > 2) {
                                                                                            if (checkpoints.stage == 3) {
                                                                                                if (checkpoints.wasted <= 5) {
                                                                                                    if (!this.variable.biglead[0]) {
                                                                                                        break Label_16736;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                            if (checkpoints.stage != 4) {
                                                                                                break Label_16755;
                                                                                            }
                                                                                            if (this.variable.completed[0] >= 35) {
                                                                                                break Label_16755;
                                                                                            }
                                                                                            if (checkpoints.clear[0] < 3) {
                                                                                                break Label_16755;
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                    this.acr = xtgraphics.randomcar[madness.im];
                                                                                    break Label_16760;
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                    this.acr = 0;
                                                                }
                                                                if (madness.cn != 1) {
                                                                    if (madness.cn != 24) {
                                                                        break Label_16875;
                                                                    }
                                                                }
                                                                if (checkpoints.wasted < 8) {
                                                                    if (checkpoints.pos[madness.im] <= 1) {
                                                                        break Label_16875;
                                                                    }
                                                                    if (checkpoints.wasted >= 8) {
                                                                        break Label_16875;
                                                                    }
                                                                }
                                                                if (madness.moment[madness.cn] >= 1.35f) {
                                                                    this.attack = 150;
                                                                    this.acr = xtgraphics.randomcar[madness.im];
                                                                }
                                                            }
                                                        }
                                                        for (int a3 = 0; a3 < 11; ++a3) {
                                                            if (this.variable.freeze[a3]) {
                                                                if (!this.variable.biglead[0]) {
                                                                    if (checkpoints.stage < 3) {
                                                                        if (madness.cn != 8) {
                                                                            if (madness.cn != 31) {
                                                                                if (madness.cn != 6) {
                                                                                    if (madness.cn != 29) {
                                                                                        if (!madness.specialact) {
                                                                                            continue;
                                                                                        }
                                                                                        if (madness.cn != 5) {
                                                                                            if (madness.cn != 2) {
                                                                                                if (madness.cn != 25) {
                                                                                                    if (madness.cn != 28) {
                                                                                                        continue;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                        this.attack = 150;
                                                                        this.acr = a3;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        if (!this.variable.biglead[0]) {
                                                            if (checkpoints.wasted <= 5) {
                                                                break Label_17365;
                                                            }
                                                            if (checkpoints.stage != 4) {
                                                                break Label_17365;
                                                            }
                                                        }
                                                        Label_17230: {
                                                            if (checkpoints.stage == 2) {
                                                                if (madness.cn != 0) {
                                                                    if (madness.cn != 4) {
                                                                        if (madness.cn != 7) {
                                                                            if (madness.cn != 23) {
                                                                                if (madness.cn != 27) {
                                                                                    if (madness.cn != 30) {
                                                                                        break Label_17230;
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                                this.attack = 150;
                                                                this.acr = 0;
                                                            }
                                                        }
                                                        Label_17353: {
                                                            Label_17311: {
                                                                if (checkpoints.stage != 3) {
                                                                    if (checkpoints.stage != 4) {
                                                                        break Label_17311;
                                                                    }
                                                                }
                                                                if (madness.cn == 2) {
                                                                    break Label_17353;
                                                                }
                                                                if (madness.cn == 6) {
                                                                    break Label_17353;
                                                                }
                                                                if (madness.cn == 25) {
                                                                    break Label_17353;
                                                                }
                                                                if (madness.cn == 29) {
                                                                    break Label_17353;
                                                                }
                                                            }
                                                            if (checkpoints.stage != 4) {
                                                                break Label_17365;
                                                            }
                                                            if (madness.moment[madness.cn] <= usermad.moment[usermad.cn]) {
                                                                break Label_17365;
                                                            }
                                                        }
                                                        this.attack = 150;
                                                        this.acr = 0;
                                                    }
                                                }
                                            }
                                            Label_17507: {
                                                if (checkpoints.stage != 3) {
                                                    if (checkpoints.stage != 4) {
                                                        break Label_17507;
                                                    }
                                                }
                                                if (madness.im == xtgraphics.nplayers - 1) {
                                                    Label_17502: {
                                                        Label_17485: {
                                                            if (madness.moment[madness.cn] >= usermad.moment[usermad.cn]) {
                                                                if (madness.specialact) {
                                                                    if (checkpoints.pos[madness.im] > 0) {
                                                                        break Label_17485;
                                                                    }
                                                                }
                                                            }
                                                            if (!this.variable.biglead[0]) {
                                                                this.attack = 0;
                                                                break Label_17502;
                                                            }
                                                        }
                                                        this.attack = 150;
                                                    }
                                                    this.acr = 0;
                                                }
                                            }
                                            if (checkpoints.stage == 4) {
                                                if (checkpoints.clear[0] >= 4) {
                                                    if (madness.beast[madness.im]) {
                                                        this.acr = 0;
                                                        this.attack = 150;
                                                    }
                                                }
                                            }
                                            if (xtgraphics.bonusstage[0]) {
                                                if (madness.im == 9) {
                                                    if (madness.moment[madness.cn] > usermad.moment[usermad.cn]) {
                                                        this.attack = 150;
                                                        this.acr = 0;
                                                    }
                                                }
                                            }
                                            Label_17931: {
                                                if (checkpoints.stage != 6) {
                                                    if (checkpoints.stage != 7) {
                                                        break Label_17931;
                                                    }
                                                }
                                                if (madness.specialact) {
                                                    if (checkpoints.clear[0] >= 3) {
                                                        if (!madness.beast[madness.im]) {
                                                            if (madness.cn != 10) {
                                                                if (madness.cn != 33) {
                                                                    if (checkpoints.pos[madness.im] > checkpoints.pos[0]) {
                                                                        this.acr = 0;
                                                                        this.attack = 30;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        else {
                                                            this.acr = 0;
                                                            this.attack = 30;
                                                        }
                                                    }
                                                }
                                                Label_17885: {
                                                    if (this.variable.biglead[0]) {
                                                        if (madness.cn != 9) {
                                                            if (!madness.beast[madness.im]) {
                                                                if (madness.cn != 11) {
                                                                    if (madness.cn != 32) {
                                                                        if (madness.cn != 34) {
                                                                            break Label_17885;
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        this.acr = 0;
                                                        this.attack = 30;
                                                    }
                                                }
                                                if (checkpoints.clear[0] >= 3) {
                                                    if (checkpoints.stage == 7) {
                                                        this.aim = this.m.random() / 2.0f + 0.75f;
                                                    }
                                                }
                                            }
                                            if (checkpoints.stage == 8) {
                                                Label_18369: {
                                                    if (madness.specialact) {
                                                        Label_18249: {
                                                            Label_18140: {
                                                                if (madness.cn != 2) {
                                                                    if (madness.cn != 25) {
                                                                        if (madness.cn != 5) {
                                                                            if (madness.cn != 28) {
                                                                                if (madness.cn != 6) {
                                                                                    if (madness.cn != 29) {
                                                                                        Label_18084: {
                                                                                            if (madness.cn != 8) {
                                                                                                if (madness.cn != 31) {
                                                                                                    break Label_18084;
                                                                                                }
                                                                                            }
                                                                                            if (this.variable.biglead[0]) {
                                                                                                break Label_18140;
                                                                                            }
                                                                                        }
                                                                                        if (madness.cn != 11) {
                                                                                            if (madness.cn != 9) {
                                                                                                if (madness.cn != 34) {
                                                                                                    if (madness.cn != 32) {
                                                                                                        break Label_18249;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                            if (!usermad.specialact) {
                                                                if (checkpoints.pos[madness.im] <= checkpoints.pos[0]) {
                                                                    if (checkpoints.clear[0] > 4) {
                                                                        break Label_18249;
                                                                    }
                                                                }
                                                                if (checkpoints.pos[madness.im] < 3) {
                                                                    if (checkpoints.clear[usermad.im] <= checkpoints.clear[madness.im] + 2) {
                                                                        break Label_18249;
                                                                    }
                                                                }
                                                                this.attack = 30;
                                                                this.acr = 0;
                                                            }
                                                        }
                                                        if (madness.cn != 0) {
                                                            if (madness.cn != 4) {
                                                                if (madness.cn != 7) {
                                                                    if (madness.cn != 10) {
                                                                        if (madness.cn != 23) {
                                                                            if (madness.cn != 27) {
                                                                                if (madness.cn != 30) {
                                                                                    if (madness.cn != 33) {
                                                                                        break Label_18369;
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        this.attack = 30;
                                                        this.acr = 0;
                                                    }
                                                }
                                                Label_18491: {
                                                    Label_18424: {
                                                        if (this.variable.biglead[0]) {
                                                            if (this.variable.completed[0] >= 50) {
                                                                break Label_18424;
                                                            }
                                                        }
                                                        if (this.variable.completed[0] < 70) {
                                                            break Label_18491;
                                                        }
                                                    }
                                                    Label_18480: {
                                                        if (madness.cn >= 9) {
                                                            if (madness.cn <= 11) {
                                                                break Label_18480;
                                                            }
                                                        }
                                                        if (madness.cn < 32) {
                                                            break Label_18491;
                                                        }
                                                        if (madness.cn > 34) {
                                                            break Label_18491;
                                                        }
                                                    }
                                                    this.attack = 30;
                                                    this.acr = 0;
                                                }
                                                boolean triggered = false;
                                                Label_18672: {
                                                    if (checkpoints.clear[0] < 7) {
                                                        if (checkpoints.wasted < 4) {
                                                            break Label_18672;
                                                        }
                                                    }
                                                    for (int a4 = 1; a4 < xtgraphics.nplayers; ++a4) {
                                                        for (int b = 1; b < xtgraphics.nplayers; ++b) {
                                                            if (a4 < b) {
                                                                if (xtgraphics.beastopponent[a4]) {
                                                                    if (xtgraphics.beastopponent[b]) {
                                                                        if (madness.im == a4) {
                                                                            triggered = true;
                                                                        }
                                                                        if (madness.im == b) {
                                                                            if (this.variable.completed[0] >= 40) {
                                                                                triggered = true;
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                                if (madness.beast[madness.im]) {
                                                    if (madness.moment[madness.cn] > usermad.moment[usermad.cn]) {
                                                        if (triggered) {
                                                            this.acr = 0;
                                                            this.attack = 30;
                                                        }
                                                    }
                                                }
                                            }
                                            Label_18820: {
                                                if (checkpoints.stage == 10) {
                                                    if (madness.specialact) {
                                                        if (madness.cn != 11) {
                                                            if (madness.cn != 34) {
                                                                break Label_18820;
                                                            }
                                                        }
                                                        if (checkpoints.pos[0] <= 1) {
                                                            this.acr = 0;
                                                            this.attack = 200;
                                                        }
                                                    }
                                                }
                                            }
                                            if (checkpoints.stage == 11) {
                                                if (!xtgraphics.bonusstage[1]) {
                                                    Label_18968: {
                                                        if (madness.specialact) {
                                                            if (madness.cn != 11) {
                                                                if (madness.cn != 13) {
                                                                    if (madness.cn != 34) {
                                                                        if (madness.cn != 36) {
                                                                            break Label_18968;
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                            if (checkpoints.clear[madness.im] + 2 >= checkpoints.clear[0]) {
                                                                if (checkpoints.clear[0] > 4) {
                                                                    break Label_18968;
                                                                }
                                                            }
                                                            this.acr = 0;
                                                            this.attack = 30;
                                                        }
                                                    }
                                                    this.aim = this.m.random() / 2.0f + 0.75f;
                                                }
                                            }
                                            if (checkpoints.stage == 12) {
                                                Label_19086: {
                                                    if (madness.beast[madness.im]) {
                                                        if (!this.bulistc) {
                                                            if (this.variable.completed[0] >= 25) {
                                                                if (madness.cn != 11) {
                                                                    if (madness.cn != 34) {
                                                                        break Label_19086;
                                                                    }
                                                                }
                                                                this.acr = 0;
                                                                this.attack = 30;
                                                            }
                                                        }
                                                    }
                                                }
                                                Label_19164: {
                                                    if (!this.variable.needhelp[8]) {
                                                        if (!this.variable.needhelp[9]) {
                                                            if (!this.variable.needhelp[1]) {
                                                                break Label_19164;
                                                            }
                                                        }
                                                    }
                                                    if (madness.im == 10) {
                                                        this.acr = 0;
                                                        this.attack = 30;
                                                    }
                                                }
                                                if (madness.specialact) {
                                                    Label_19336: {
                                                        Label_19280: {
                                                            if (madness.moment[madness.cn] > usermad.moment[usermad.cn]) {
                                                                if (checkpoints.clear[madness.im] + 2 < checkpoints.clear[0]) {
                                                                    break Label_19280;
                                                                }
                                                            }
                                                            if (!this.variable.biglead[0]) {
                                                                if (!this.variable.lotswasted) {
                                                                    break Label_19336;
                                                                }
                                                                if (checkpoints.pos[madness.im] < 2) {
                                                                    break Label_19336;
                                                                }
                                                            }
                                                        }
                                                        if (madness.cn != 10) {
                                                            if (madness.cn != 33) {
                                                                if (!madness.beast[madness.im]) {
                                                                    this.acr = 0;
                                                                    this.attack = 30;
                                                                }
                                                            }
                                                        }
                                                    }
                                                    if (madness.beast[madness.im]) {
                                                        if (checkpoints.clear[0] >= checkpoints.clear[madness.im] + 2) {
                                                            this.acr = 0;
                                                            this.attack = 30;
                                                        }
                                                    }
                                                }
                                            }
                                            Label_19969: {
                                                if (checkpoints.stage == 15) {
                                                    if (!xtgraphics.bonusstage[2]) {
                                                        this.aim = this.m.random() / 2.0f + 0.75f;
                                                        Label_19474: {
                                                            if (madness.im != 10) {
                                                                if (madness.im != 11) {
                                                                    break Label_19474;
                                                                }
                                                            }
                                                            this.acr = 0;
                                                            this.attack = 30;
                                                        }
                                                        Label_19678: {
                                                            if (madness.specialact) {
                                                                if (madness.cn != 11) {
                                                                    if (madness.cn != 15) {
                                                                        if (madness.cn != 34) {
                                                                            if (madness.cn != 38) {
                                                                                if (madness.cn != 14) {
                                                                                    if (madness.cn != 37) {
                                                                                        if (madness.cn != 10) {
                                                                                            if (madness.cn != 33) {
                                                                                                break Label_19678;
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                                if (checkpoints.pos[0] >= checkpoints.pos[madness.im] - 1) {
                                                                                    if (checkpoints.clear[0] >= 3) {
                                                                                        break Label_19678;
                                                                                    }
                                                                                    if (madness.moment[madness.cn] <= usermad.moment[usermad.cn]) {
                                                                                        break Label_19678;
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                                this.acr = 0;
                                                                this.attack = 30;
                                                            }
                                                        }
                                                        Label_19734: {
                                                            if (this.variable.biglead[0]) {
                                                                if (madness.cn != 15) {
                                                                    if (madness.cn != 38) {
                                                                        break Label_19734;
                                                                    }
                                                                }
                                                                this.acr = 0;
                                                                this.attack = 30;
                                                            }
                                                        }
                                                        Label_19845: {
                                                            if (checkpoints.clear[0] < 3) {
                                                                if (madness.cn != 13) {
                                                                    if (madness.cn != 36) {
                                                                        if (madness.cn != 11) {
                                                                            if (madness.cn != 34) {
                                                                                break Label_19845;
                                                                            }
                                                                        }
                                                                        if (!madness.beast[madness.im]) {
                                                                            if (!madness.shadowcar) {
                                                                                break Label_19845;
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                                this.acr = 0;
                                                                this.attack = 30;
                                                            }
                                                        }
                                                        if (madness.beast[madness.im]) {
                                                            if (madness.moment[madness.cn] > usermad.moment[usermad.cn]) {
                                                                if (checkpoints.clear[0] <= checkpoints.pos[madness.im] + 2) {
                                                                    if (this.variable.completed[0] < 70) {
                                                                        break Label_19969;
                                                                    }
                                                                    if (checkpoints.pos[0] >= checkpoints.pos[madness.im]) {
                                                                        break Label_19969;
                                                                    }
                                                                }
                                                                this.acr = 0;
                                                                this.attack = 30;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            Label_20540: {
                                                if (checkpoints.stage == 16) {
                                                    this.aim = this.m.random() / 2.0f + 0.75f;
                                                    Label_20071: {
                                                        if (madness.im != 7) {
                                                            if (!madness.beast[madness.im]) {
                                                                break Label_20071;
                                                            }
                                                        }
                                                        if (madness.moment[madness.cn] > usermad.moment[usermad.cn]) {
                                                            this.acr = 0;
                                                            this.attack = 20;
                                                        }
                                                    }
                                                    if (this.variable.needhelp[7]) {
                                                        if (madness.im == 4) {
                                                            if (madness.moment[madness.cn] > usermad.moment[usermad.cn]) {
                                                                this.acr = 0;
                                                                this.attack = 20;
                                                            }
                                                        }
                                                    }
                                                    Label_20346: {
                                                        if (madness.specialact) {
                                                            if (madness.cn != 11) {
                                                                if (madness.cn != 34) {
                                                                    if (madness.cn != 9) {
                                                                        if (madness.cn != 32) {
                                                                            if (madness.cn != 14) {
                                                                                if (madness.cn != 10) {
                                                                                    if (madness.cn != 33) {
                                                                                        if (madness.cn != 37) {
                                                                                            break Label_20346;
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                            if (checkpoints.pos[0] >= checkpoints.pos[madness.im] - 1) {
                                                                                if (checkpoints.clear[0] >= 3) {
                                                                                    break Label_20346;
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                            if (madness.moment[madness.cn] > usermad.moment[usermad.cn]) {
                                                                this.acr = 0;
                                                                this.attack = 30;
                                                            }
                                                        }
                                                    }
                                                    Label_20461: {
                                                        if (this.variable.biglead[0]) {
                                                            if (madness.cn != 13) {
                                                                if (madness.cn != 36) {
                                                                    if (madness.cn != 15) {
                                                                        if (madness.cn != 38) {
                                                                            break Label_20461;
                                                                        }
                                                                    }
                                                                    if (!madness.specialact) {
                                                                        if (this.variable.completed[0] < 65) {
                                                                            break Label_20461;
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                            this.acr = 0;
                                                            this.attack = 30;
                                                        }
                                                    }
                                                    if (madness.shadowcar) {
                                                        if (madness.cn != 11) {
                                                            if (madness.cn != 9) {
                                                                if (madness.cn != 32) {
                                                                    if (madness.cn != 34) {
                                                                        break Label_20540;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        this.acr = 0;
                                                        this.attack = 30;
                                                    }
                                                }
                                            }
                                            if (checkpoints.stage == 18) {
                                                if (!xtgraphics.bonusstage[3]) {
                                                    this.aim = this.m.random() / 2.0f + 0.75f;
                                                    Label_20761: {
                                                        if (madness.cn == 16) {
                                                            if (this.variable.needhelp[7]) {
                                                                if (this.variable.needhelp[8]) {
                                                                    if (this.variable.needhelp[9]) {
                                                                        this.attack = 0;
                                                                        break Label_20761;
                                                                    }
                                                                }
                                                            }
                                                            Label_20751: {
                                                                Label_20740: {
                                                                    Label_20704: {
                                                                        if (checkpoints.pos[madness.im] <= checkpoints.pos[0]) {
                                                                            if (checkpoints.clear[0] > 3) {
                                                                                break Label_20704;
                                                                            }
                                                                        }
                                                                        if (madness.specialact) {
                                                                            break Label_20740;
                                                                        }
                                                                    }
                                                                    if (this.variable.completed[0] < 40) {
                                                                        break Label_20751;
                                                                    }
                                                                    if (!this.variable.biglead[0]) {
                                                                        break Label_20751;
                                                                    }
                                                                }
                                                                this.attack = 30;
                                                                this.acr = 0;
                                                            }
                                                        }
                                                    }
                                                    Label_20935: {
                                                        if (madness.specialact) {
                                                            if (madness.cn != 15) {
                                                                if (madness.cn != 11) {
                                                                    if (madness.cn != 13) {
                                                                        if (madness.cn != 34) {
                                                                            if (madness.cn != 36) {
                                                                                if (madness.cn != 38) {
                                                                                    break Label_20935;
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                            if (checkpoints.pos[madness.im] <= checkpoints.pos[0]) {
                                                                if (checkpoints.clear[0] > 3) {
                                                                    break Label_20935;
                                                                }
                                                            }
                                                            if (madness.moment[madness.cn] > usermad.moment[usermad.cn]) {
                                                                this.acr = 0;
                                                                this.attack = 30;
                                                            }
                                                        }
                                                    }
                                                    if (madness.power < 45.0f) {
                                                        this.attack = 0;
                                                    }
                                                }
                                            }
                                            if (xtgraphics.bonusstage[3]) {
                                                this.acr = 0;
                                                this.attack = 30;
                                            }
                                            Label_21728: {
                                                if (checkpoints.stage == 19) {
                                                    this.aim = this.m.random() / 2.0f + 0.75f;
                                                    if (madness.cn != 14) {
                                                        if (madness.cn != 17) {
                                                            if (madness.cn != 1) {
                                                                if (madness.cn != 12) {
                                                                    if (madness.cn != 37) {
                                                                        if (madness.cn != 24) {
                                                                            if (madness.cn != 35) {
                                                                                boolean triggered = false;
                                                                                for (int a4 = 0; a4 < xtgraphics.nplayers; ++a4) {
                                                                                    for (int b = 0; b < xtgraphics.nplayers; ++b) {
                                                                                        if (a4 < b) {
                                                                                            if (xtgraphics.beastopponent[a4]) {
                                                                                                if (xtgraphics.beastopponent[b]) {
                                                                                                    Label_21219: {
                                                                                                        if (checkpoints.wasted < 1) {
                                                                                                            if (!xtgraphics.fixspecials[a4]) {
                                                                                                                break Label_21219;
                                                                                                            }
                                                                                                        }
                                                                                                        if (madness.im == a4) {
                                                                                                            triggered = true;
                                                                                                        }
                                                                                                    }
                                                                                                    Label_21352: {
                                                                                                        if (checkpoints.wasted < 4) {
                                                                                                            if (checkpoints.dested[a4] <= 0) {
                                                                                                                if (!this.variable.vulnerable[a4]) {
                                                                                                                    if (checkpoints.wasted != 0) {
                                                                                                                        break Label_21352;
                                                                                                                    }
                                                                                                                    if (!xtgraphics.fixspecials[b]) {
                                                                                                                        break Label_21352;
                                                                                                                    }
                                                                                                                    if (xtgraphics.fixspecials[a4]) {
                                                                                                                        break Label_21352;
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                        Label_21349: {
                                                                                                            if (madness.im == a4) {
                                                                                                                if (checkpoints.wasted > 0) {
                                                                                                                    break Label_21349;
                                                                                                                }
                                                                                                            }
                                                                                                            if (madness.im != b) {
                                                                                                                break Label_21352;
                                                                                                            }
                                                                                                        }
                                                                                                        triggered = true;
                                                                                                    }
                                                                                                    if (checkpoints.wasted >= 7) {
                                                                                                        if (checkpoints.dested[b] > 0) {
                                                                                                            if (checkpoints.dested[a4] == 0) {
                                                                                                                if (madness.im == a4) {
                                                                                                                    triggered = false;
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                        if (checkpoints.dested[b] == 0) {
                                                                                                            if (madness.im == b) {
                                                                                                                triggered = false;
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                                if (this.variable.completed[0] >= 12) {
                                                                                    triggered = true;
                                                                                }
                                                                                Label_21588: {
                                                                                    if (madness.beast[madness.im]) {
                                                                                        if (triggered) {
                                                                                            break Label_21588;
                                                                                        }
                                                                                    }
                                                                                    if (!madness.specialact) {
                                                                                        break Label_21728;
                                                                                    }
                                                                                    if (madness.moment[madness.cn] <= usermad.moment[usermad.cn]) {
                                                                                        break Label_21728;
                                                                                    }
                                                                                    if (madness.beast[madness.im]) {
                                                                                        break Label_21728;
                                                                                    }
                                                                                }
                                                                                Label_21695: {
                                                                                    if (checkpoints.pos[madness.im] <= checkpoints.pos[usermad.im]) {
                                                                                        if (this.variable.completed[0] < 12) {
                                                                                            if (checkpoints.wasted >= 2) {
                                                                                                break Label_21695;
                                                                                            }
                                                                                            if (!madness.shadowcar) {
                                                                                                break Label_21695;
                                                                                            }
                                                                                        }
                                                                                        if (checkpoints.pos[madness.im] < 6) {
                                                                                            if (!madness.beast[madness.im]) {
                                                                                                break Label_21728;
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                                this.acr = 0;
                                                                                this.attack = 30;
                                                                                if (usermad.speed < 200.0f) {
                                                                                    this.aim = 1.0f;
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            if (checkpoints.stage == 21) {
                                                if (!xtgraphics.entered[madness.im]) {
                                                    this.aim = this.m.random() / 2.0f + 0.75f;
                                                    boolean attackuser = false;
                                                    Label_22003: {
                                                        if (checkpoints.pos[madness.im] <= checkpoints.pos[usermad.im]) {
                                                            if (checkpoints.clear[0] > 3) {
                                                                if (checkpoints.clear[madness.im] > 3) {
                                                                    break Label_22003;
                                                                }
                                                            }
                                                        }
                                                        if (madness.specialact) {
                                                            if (madness.aistrsp[madness.cn] > 20) {
                                                                attackuser = true;
                                                            }
                                                        }
                                                        if (this.variable.completed[0] >= 30) {
                                                            if (madness.beast[madness.im]) {
                                                                if (madness.aistrsp[madness.cn] > 20) {
                                                                    if (madness.power > 62.0f) {
                                                                        attackuser = true;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        if (madness.cn != 14) {
                                                            if (madness.cn != 17) {
                                                                if (madness.cn != 37) {
                                                                    break Label_22003;
                                                                }
                                                            }
                                                        }
                                                        attackuser = false;
                                                    }
                                                    if (attackuser) {
                                                        if (madness.moment[madness.cn] > usermad.moment[usermad.cn]) {
                                                            this.acr = 0;
                                                            this.attack = 30;
                                                            if (usermad.speed < 200.0f) {
                                                                this.aim = 1.0f;
                                                            }
                                                        }
                                                    }
                                                }
                                                else {
                                                    this.attack = 0;
                                                }
                                            }
                                            if (checkpoints.stage != 22) {
                                                break Label_23008;
                                            }
                                            this.aim = this.m.random() / 4.0f + 0.875f;
                                            if (checkpoints.dested[xtgraphics.nplayers - 1] == 0) {
                                                boolean racer = false;
                                                boolean attackuser2 = false;
                                                boolean watchout = false;
                                                if (checkpoints.clear[0] >= 3) {
                                                    racer = true;
                                                    if (madness.cn == 16) {
                                                        attackuser2 = true;
                                                    }
                                                    if (this.variable.completed[0] >= 30) {
                                                        if (madness.beast[madness.im]) {
                                                            attackuser2 = true;
                                                        }
                                                    }
                                                    if (this.variable.completed[0] >= 50) {
                                                        attackuser2 = true;
                                                    }
                                                }
                                                Label_22282: {
                                                    if (madness.specialact) {
                                                        if (checkpoints.wasted < 4) {
                                                            if (this.variable.completed[0] < 30) {
                                                                break Label_22282;
                                                            }
                                                        }
                                                        racer = true;
                                                        attackuser2 = true;
                                                    }
                                                }
                                                if (xtgraphics.fixspecials[xtgraphics.nplayers - 1]) {
                                                    racer = true;
                                                    if (!madness.beast[madness.im]) {
                                                        watchout = true;
                                                        attackuser2 = false;
                                                    }
                                                }
                                                Label_22392: {
                                                    if (checkpoints.wasted >= 2) {
                                                        if (madness.beast[madness.im]) {
                                                            if (!xtgraphics.verydark) {
                                                                if (checkpoints.wasted < 6) {
                                                                    break Label_22392;
                                                                }
                                                            }
                                                            racer = true;
                                                            attackuser2 = true;
                                                        }
                                                    }
                                                }
                                                if (racer) {
                                                    if (attackuser2) {
                                                        if (madness.im != xtgraphics.nplayers - 1) {
                                                            this.acr = 0;
                                                            if (watchout) {
                                                                this.attack = 0;
                                                            }
                                                            else {
                                                                this.attack = 30;
                                                            }
                                                        }
                                                    }
                                                }
                                                else {
                                                    Label_22524: {
                                                        if (madness.cn != 16) {
                                                            if (madness.cn != 15) {
                                                                if (madness.cn != 13) {
                                                                    if (madness.cn != 36) {
                                                                        if (madness.cn != 38) {
                                                                            break Label_22524;
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                            if (!madness.specialact) {
                                                                break Label_22524;
                                                            }
                                                        }
                                                        if (!this.variable.layoff[xtgraphics.nplayers - 1]) {
                                                            this.attack = 30;
                                                            this.acr = xtgraphics.nplayers - 1;
                                                        }
                                                    }
                                                }
                                                Label_22674: {
                                                    if (madness.cn != 17) {
                                                        if (madness.cn != 14) {
                                                            if (madness.cn != 37) {
                                                                break Label_22674;
                                                            }
                                                        }
                                                    }
                                                    if (racer) {
                                                        if (!madness.specialact) {
                                                            break Label_22674;
                                                        }
                                                    }
                                                    if (!watchout) {
                                                        this.attack = 30;
                                                        this.acr = 0;
                                                    }
                                                }
                                                boolean justrace = false;
                                                Label_22746: {
                                                    Label_22743: {
                                                        if (racer) {
                                                            if (this.variable.biglead[madness.im]) {
                                                                break Label_22743;
                                                            }
                                                        }
                                                        if (this.variable.completed[madness.im] < 70) {
                                                            if (checkpoints.wasted < 7) {
                                                                break Label_22746;
                                                            }
                                                        }
                                                    }
                                                    justrace = true;
                                                }
                                                if (xtgraphics.verydark) {
                                                    if (madness.cn == 18) {
                                                        if (!justrace) {
                                                            this.attack = 30;
                                                            this.acr = xtgraphics.targetcar;
                                                        }
                                                    }
                                                }
                                                if (madness.specialact) {
                                                    if (madness.cn == 18) {
                                                        if (!justrace) {
                                                            this.attack = 30;
                                                            this.acr = 0;
                                                        }
                                                    }
                                                }
                                                break Label_23008;
                                            }
                                            this.bulistc = false;
                                            if (!this.variable.lotswasted) {
                                                if (madness.beast[madness.im]) {
                                                    this.acr = 0;
                                                    this.attack = 30;
                                                }
                                            }
                                            boolean justrace2 = false;
                                            if (madness.cn == 17) {
                                                justrace2 = true;
                                            }
                                            Label_22975: {
                                                if (checkpoints.pos[madness.im] != 0) {
                                                    if (checkpoints.pos[madness.im] != 1) {
                                                        break Label_22975;
                                                    }
                                                    if (checkpoints.wasted > xtgraphics.nplayers - 4) {
                                                        break Label_22975;
                                                    }
                                                }
                                                justrace2 = true;
                                            }
                                            if (justrace2) {
                                                break Label_23008;
                                            }
                                            if (!madness.specialact) {
                                                break Label_23008;
                                            }
                                            this.acr = 0;
                                            this.attack = 30;
                                            break Label_23008;
                                        }
                                    }
                                    if (checkpoints.stage < 15) {
                                        if (checkpoints.stage >= 11) {
                                            this.aim = this.m.random() / 2.0f + 0.5f;
                                        }
                                    }
                                    else {
                                        this.aim = this.m.random() / 2.0f + 0.75f;
                                    }
                                    if (madness.beast[madness.im]) {
                                        if (madness.aistrsp[madness.cn] > 0) {
                                            if (checkpoints.stage >= 11) {
                                                for (int a3 = 0; a3 < xtgraphics.nplayers; ++a3) {
                                                    if (this.variable.biglead[a3]) {
                                                        if (this.variable.completed[a3] >= 70) {
                                                            this.attack = 30;
                                                            this.acr = a3;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    Label_15483: {
                                        if (madness.specialact) {
                                            if (madness.aistrsp[madness.cn] <= 0) {
                                                if (madness.cn != 1) {
                                                    if (madness.cn != 24) {
                                                        break Label_15483;
                                                    }
                                                }
                                            }
                                            if (checkpoints.pos[madness.im] < 3) {
                                                if (checkpoints.wasted != xtgraphics.nplayers - 4) {
                                                    break Label_15483;
                                                }
                                                if (checkpoints.pos[madness.im] < 1) {
                                                    break Label_15483;
                                                }
                                            }
                                            this.attack = 30;
                                            if (!this.variable.lotswasted) {
                                                if (!this.variable.biglead[0]) {
                                                    Label_15473: {
                                                        if (madness.cn != 2) {
                                                            if (madness.cn != 5) {
                                                                if (madness.cn != 6) {
                                                                    if (madness.cn != 8) {
                                                                        if (madness.cn != 11) {
                                                                            if (madness.cn != 15) {
                                                                                if (madness.cn != 25) {
                                                                                    if (madness.cn != 28) {
                                                                                        if (madness.cn != 29) {
                                                                                            if (madness.cn != 31) {
                                                                                                if (madness.cn != 34) {
                                                                                                    this.acr = xtgraphics.randomcar[madness.im];
                                                                                                    break Label_15473;
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        for (int a3 = 0; a3 < xtgraphics.nplayers; ++a3) {
                                                            final int[] endurance = new int[101];
                                                            endurance[a3] = xtgraphics.endsp[a3];
                                                            Arrays.sort(xtgraphics.endsp);
                                                            if (endurance[a3] == xtgraphics.endsp[xtgraphics.nplayers - 1]) {
                                                                if (madness.im == a3) {
                                                                    this.acr = 0;
                                                                }
                                                                else {
                                                                    this.acr = a3;
                                                                }
                                                            }
                                                        }
                                                    }
                                                    break Label_15483;
                                                }
                                            }
                                            this.acr = 0;
                                        }
                                    }
                                    for (int a3 = 0; a3 < xtgraphics.nplayers; ++a3) {
                                        if (this.variable.biglead[a3]) {
                                            Label_15597: {
                                                if (this.variable.completed[a3] >= 65) {
                                                    if (madness.cn != 15) {
                                                        if (madness.cn != 16) {
                                                            if (madness.cn != 18) {
                                                                if (madness.cn != 38) {
                                                                    break Label_15597;
                                                                }
                                                            }
                                                        }
                                                    }
                                                    this.attack = 30;
                                                    this.acr = a3;
                                                }
                                            }
                                            Label_15713: {
                                                if (this.variable.completed[a3] >= 75) {
                                                    if (madness.cn != 11) {
                                                        if (madness.cn != 13) {
                                                            if (madness.cn != 9) {
                                                                if (madness.cn != 32) {
                                                                    if (madness.cn != 34) {
                                                                        if (madness.cn != 36) {
                                                                            break Label_15713;
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                    this.attack = 30;
                                                    this.acr = a3;
                                                }
                                            }
                                            if (this.variable.completed[a3] >= 85) {
                                                if (madness.aistrsp[madness.cn] > 0) {
                                                    this.attack = 30;
                                                    this.acr = a3;
                                                }
                                            }
                                        }
                                    }
                                    Label_15910: {
                                        Label_15905: {
                                            if (madness.power >= 45.0f) {
                                                if (madness.cn != 12) {
                                                    if (madness.cn != 3) {
                                                        if (madness.cn != 17) {
                                                            if (checkpoints.stage != 14) {
                                                                if (checkpoints.stage == 14) {
                                                                    if (!this.bulistc) {
                                                                        break Label_15905;
                                                                    }
                                                                }
                                                                if (madness.cn != 26) {
                                                                    if (madness.cn != 35) {
                                                                        break Label_15910;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        this.attack = 0;
                                    }
                                    Label_15956: {
                                        if (checkpoints.stage != 5) {
                                            if (checkpoints.stage != 10) {
                                                if (checkpoints.stage != 14) {
                                                    break Label_15956;
                                                }
                                            }
                                        }
                                        this.attack = 0;
                                    }
                                    if (checkpoints.stage == 21) {
                                        if (xtgraphics.entered[madness.im]) {
                                            this.attack = 0;
                                        }
                                    }
                                    if (checkpoints.stage == 22) {
                                        boolean justrace2 = false;
                                        Label_16084: {
                                            Label_16081: {
                                                if (checkpoints.clear[0] >= 3) {
                                                    if (this.variable.biglead[madness.im]) {
                                                        break Label_16081;
                                                    }
                                                }
                                                if (this.variable.completed[madness.im] < 70) {
                                                    if (checkpoints.wasted < 7) {
                                                        break Label_16084;
                                                    }
                                                }
                                            }
                                            justrace2 = true;
                                        }
                                        if (xtgraphics.verydark) {
                                            if (madness.cn == 18) {
                                                if (!justrace2) {
                                                    this.attack = 30;
                                                    this.acr = xtgraphics.targetcar;
                                                    this.aim = 1.0f;
                                                }
                                            }
                                        }
                                        if (madness.specialact) {
                                            if (madness.cn == 18) {
                                                if (!justrace2) {
                                                    this.attack = 30;
                                                    this.acr = 0;
                                                }
                                            }
                                        }
                                    }
                                    if (conto.floorguardian) {
                                        if (conto.guardswitch) {
                                            if (this.acr != 0) {
                                                if (this.acr != 1) {
                                                    if (this.acr != 4) {
                                                        if (this.acr != 7) {
                                                            this.attack = 0;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        else if (xtgraphics.floor[madness.im] == xtgraphics.floor[0]) {
                                            this.acr = 0;
                                            this.attack = 30;
                                        }
                                    }
                                }
                                if (checkpoints.stage == 23) {
                                    this.aim = this.m.random() / 4.0f + 0.875f;
                                    Label_23175: {
                                        if (madness.specialact) {
                                            if (madness.moment[madness.cn] > usermad.moment[usermad.cn]) {
                                                if (checkpoints.pos[madness.im] <= checkpoints.pos[usermad.im]) {
                                                    if (checkpoints.clear[0] > 3) {
                                                        if (checkpoints.pos[madness.im] < 7) {
                                                            break Label_23175;
                                                        }
                                                    }
                                                }
                                                this.acr = 0;
                                                this.attack = 30;
                                                if (usermad.speed < 200.0f) {
                                                    this.aim = 1.0f;
                                                }
                                            }
                                        }
                                    }
                                    if (this.variable.completed[0] >= 70) {
                                        if (madness.cn == 16) {
                                            if (checkpoints.pos[madness.im] > 2) {
                                                if (checkpoints.pos[0] < checkpoints.pos[madness.im]) {
                                                    this.acr = 0;
                                                    this.attack = 30;
                                                }
                                            }
                                        }
                                    }
                                    if (xtgraphics.bossbattle) {
                                        this.attack = 30;
                                        this.acr = 0;
                                    }
                                }
                                if (checkpoints.stage == 17) {
                                    this.aim = this.m.random() / 2.0f + 0.75f;
                                    if (madness.im > 3) {
                                        Label_23501: {
                                            if (!madness.shadowcar) {
                                                if (!madness.beast[madness.im]) {
                                                    if (madness.im != xtgraphics.nplayers - 1) {
                                                        Label_23418: {
                                                            if (this.attack != 0) {
                                                                if (!this.variable.needhelp[this.acr]) {
                                                                    break Label_23418;
                                                                }
                                                            }
                                                            this.staythere = 0;
                                                            this.attack = 0;
                                                        }
                                                        if (this.staythere == 0) {
                                                            this.attack = 200;
                                                            this.acr = (int)(Math.random() * (xtgraphics.nplayers - 1));
                                                            if (checkpoints.dested[this.acr] == 0) {
                                                                if (this.acr != 0) {
                                                                    if (this.acr <= 3) {
                                                                        break Label_23501;
                                                                    }
                                                                }
                                                                this.staythere = 1;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        Label_23614: {
                                            if (madness.specialact) {
                                                if (madness.im == xtgraphics.nplayers - 1) {
                                                    if (checkpoints.clear[0] >= 4) {
                                                        break Label_23614;
                                                    }
                                                    if (this.variable.lotswasted) {
                                                        break Label_23614;
                                                    }
                                                }
                                                if (!madness.shadowcar) {
                                                    if (madness.moment[madness.cn] > usermad.moment[usermad.cn]) {
                                                        this.attack = 30;
                                                        this.acr = 0;
                                                    }
                                                }
                                            }
                                        }
                                        for (int a3 = 4; a3 < xtgraphics.nplayers; ++a3) {
                                            for (int b2 = 4; b2 < xtgraphics.nplayers; ++b2) {
                                                if (a3 > b2) {
                                                    if (xtgraphics.beastopponent[a3]) {
                                                        if (xtgraphics.beastopponent[b2]) {
                                                            if (checkpoints.wasted < 6) {
                                                                if (checkpoints.dested[b2] == 0) {
                                                                    if (!this.variable.vulnerable[a3]) {
                                                                        if (madness.im == a3) {
                                                                            this.attack = 30;
                                                                            this.acr = 0;
                                                                        }
                                                                        continue;
                                                                    }
                                                                }
                                                            }
                                                            if (madness.im != a3) {
                                                                if (madness.im != b2) {
                                                                    continue;
                                                                }
                                                            }
                                                            this.attack = 30;
                                                            this.acr = 0;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                if (checkpoints.stage >= 19) {
                                    int speedlimit = 300;
                                    if (checkpoints.stage >= 21) {
                                        speedlimit = 350;
                                    }
                                    if (usermad.speed < speedlimit) {
                                        this.aim = 1.0f;
                                    }
                                }
                                boolean spatkcep = false;
                                if (madness.cn == 16) {
                                    if (checkpoints.stage == 18) {
                                        spatkcep = true;
                                    }
                                }
                                Label_23966: {
                                    Label_23954: {
                                        if (!madness.frozen) {
                                            if (!madness.redstr) {
                                                break Label_23954;
                                            }
                                        }
                                        if (!spatkcep) {
                                            break Label_23966;
                                        }
                                    }
                                    if (!madness.strswap) {
                                        break Label_24021;
                                    }
                                }
                                if (checkpoints.stage == 17) {
                                    if (madness.im != xtgraphics.nplayers - 1) {
                                        if (!madness.shadowcar) {
                                            this.bulistc = true;
                                        }
                                    }
                                }
                                this.attack = 0;
                            }
                        }
                    }
                    boolean flag3 = false;
                    Label_24095: {
                        Label_24092: {
                            if (checkpoints.stage == 6) {
                                if (!xtgraphics.careermode) {
                                    break Label_24092;
                                }
                            }
                            if (checkpoints.stage != 10) {
                                if (checkpoints.stage != 17) {
                                    break Label_24095;
                                }
                                if (!xtgraphics.classicmode) {
                                    break Label_24095;
                                }
                            }
                        }
                        flag3 = true;
                    }
                    Label_24182: {
                        Label_24179: {
                            if (checkpoints.stage == 8) {
                                if (madness.pcleared != 73) {
                                    if (xtgraphics.classicmode) {
                                        break Label_24179;
                                    }
                                }
                            }
                            if (checkpoints.stage != 14) {
                                if (checkpoints.stage != 20) {
                                    if (!xtgraphics.bonusstage[2]) {
                                        break Label_24182;
                                    }
                                }
                            }
                        }
                        flag3 = true;
                    }
                    if (xtgraphics.careermode) {
                        if (checkpoints.stage == 4) {
                            if (madness.beast[madness.im]) {
                                flag3 = true;
                            }
                        }
                        Label_24273: {
                            Label_24270: {
                                if (checkpoints.stage == 5) {
                                    if (!xtgraphics.bonusstage[0]) {
                                        break Label_24270;
                                    }
                                }
                                if (checkpoints.stage != 9) {
                                    break Label_24273;
                                }
                            }
                            flag3 = true;
                        }
                        if (checkpoints.stage == 13) {
                            if (conto.floorguardian) {
                                boolean nofix = true;
                                for (int a5 = 1; a5 < xtgraphics.nplayers; ++a5) {
                                    if (a5 != 1) {
                                        if (a5 != 4) {
                                            if (a5 != 7) {
                                                if (a5 < 10) {
                                                    continue;
                                                }
                                            }
                                        }
                                    }
                                    if (checkpoints.dested[a5] == 0) {
                                        nofix = false;
                                    }
                                }
                                flag3 = nofix;
                            }
                        }
                    }
                    if (madness.nofix) {
                        flag3 = true;
                    }
                    if (xtgraphics.dontdisplay) {
                        if (xtgraphics.ptmatch <= 5) {
                            flag3 = true;
                        }
                    }
                    if (this.trfix == 3) {
                        this.upwait = 0;
                        this.acuracy = 0;
                        this.skiplev = 1.0f;
                        this.clrnce = 2;
                    }
                    else {
                        this.trfix = 0;
                        int j3 = 50;
                        if (checkpoints.stage == 16) {
                            j3 = 40;
                        }
                        if (100.0f * madness.hitmag / madness.maxmag[madness.cn] > j3) {
                            this.trfix = 1;
                        }
                        if (!flag3) {
                            int k9 = 80;
                            if (checkpoints.stage == 9) {
                                if (!xtgraphics.careermode) {
                                    k9 = 70;
                                }
                            }
                            Label_24666: {
                                if (checkpoints.stage == 3) {
                                    if (xtgraphics.careermode) {
                                        if (checkpoints.pos[0] > 0) {
                                            if (xtgraphics.fixspecials[10]) {
                                                if (!xtgraphics.fixspecials[0]) {
                                                    break Label_24666;
                                                }
                                            }
                                            if (madness.cn != 9) {
                                                if (madness.cn != 32) {
                                                    break Label_24666;
                                                }
                                            }
                                            k9 = 50;
                                        }
                                    }
                                }
                            }
                            if (checkpoints.stage == 15) {
                                if (madness.pcleared == 91) {
                                    if (xtgraphics.classicmode) {
                                        k9 = 50;
                                    }
                                }
                            }
                            Label_26216: {
                                if (xtgraphics.careermode) {
                                    if (checkpoints.stage == 15) {
                                        if (!xtgraphics.bonusstage[2]) {
                                            if (this.attack != 0) {
                                                k9 = 80;
                                            }
                                            else {
                                                k9 = 50;
                                            }
                                        }
                                    }
                                    Label_24966: {
                                        if (checkpoints.stage != 2) {
                                            if (checkpoints.stage != 4) {
                                                if (checkpoints.stage != 6) {
                                                    if (checkpoints.stage != 7) {
                                                        break Label_24966;
                                                    }
                                                }
                                            }
                                        }
                                        if (this.attack != 0) {
                                            k9 = 80;
                                        }
                                        else if (!madness.beast[madness.im]) {
                                            if (checkpoints.stage != 6) {
                                                if (checkpoints.stage != 7) {
                                                    k9 = 75;
                                                }
                                                else {
                                                    k9 = 60;
                                                }
                                            }
                                            else {
                                                Label_24925: {
                                                    if (madness.cn != 10) {
                                                        if (madness.cn != 33) {
                                                            k9 = 70;
                                                            break Label_24925;
                                                        }
                                                    }
                                                    k9 = 50;
                                                }
                                            }
                                        }
                                        else {
                                            k9 = 80;
                                        }
                                    }
                                    Label_25059: {
                                        if (checkpoints.stage == 8) {
                                            if (!madness.beast[madness.im]) {
                                                if (this.attack == 0) {
                                                    if (madness.cn != 11) {
                                                        if (madness.cn != 34) {
                                                            k9 = 50;
                                                            break Label_25059;
                                                        }
                                                    }
                                                }
                                                k9 = 70;
                                            }
                                            else {
                                                k9 = 80;
                                            }
                                        }
                                    }
                                    Label_25249: {
                                        if (checkpoints.stage != 11) {
                                            if (checkpoints.stage != 12) {
                                                break Label_25249;
                                            }
                                        }
                                        Label_25218: {
                                            if (madness.beast[madness.im]) {
                                                if (checkpoints.clear[0] <= 4) {
                                                    k9 = 75;
                                                    break Label_25218;
                                                }
                                            }
                                            if (this.attack == 0) {
                                                if (madness.cn != 13) {
                                                    if (madness.cn != 36) {
                                                        k9 = 50;
                                                        break Label_25218;
                                                    }
                                                }
                                            }
                                            if (madness.cn != 13) {
                                                if (madness.cn != 36) {
                                                    k9 = 70;
                                                    break Label_25218;
                                                }
                                            }
                                            k9 = 80;
                                        }
                                        if (xtgraphics.bonstage) {
                                            if (madness.cn == 36) {
                                                k9 = 85;
                                            }
                                        }
                                    }
                                    if (checkpoints.stage == 13) {
                                        int fixlvl = 50;
                                        Label_25399: {
                                            if (!madness.beast[madness.im]) {
                                                if (madness.cn != 13) {
                                                    if (madness.cn != 36) {
                                                        if (madness.cn < 16) {
                                                            break Label_25399;
                                                        }
                                                        if (madness.cn > 22) {
                                                            break Label_25399;
                                                        }
                                                    }
                                                }
                                                fixlvl = 70;
                                            }
                                            else {
                                                fixlvl = 70;
                                                Label_25334: {
                                                    Label_25330: {
                                                        if (madness.cn >= 13) {
                                                            if (madness.cn <= 22) {
                                                                break Label_25330;
                                                            }
                                                        }
                                                        if (madness.cn < 36) {
                                                            break Label_25334;
                                                        }
                                                    }
                                                    fixlvl = 80;
                                                }
                                            }
                                        }
                                        if (this.attack != 0) {
                                            k9 = 80;
                                        }
                                        else {
                                            k9 = fixlvl;
                                        }
                                    }
                                    Label_25680: {
                                        if (checkpoints.stage != 19) {
                                            if (checkpoints.stage != 21) {
                                                if (checkpoints.stage != 23) {
                                                    break Label_25680;
                                                }
                                            }
                                        }
                                        int beastboost = 0;
                                        if (madness.beast[madness.im]) {
                                            beastboost = 10;
                                        }
                                        Label_25575: {
                                            if (this.variable.completed[0] >= 12) {
                                                if (checkpoints.clear[madness.im] <= checkpoints.clear[0] + 6) {
                                                    k9 = 70;
                                                    break Label_25575;
                                                }
                                            }
                                            if (this.attack == 0) {
                                                if (!madness.shadowcar) {
                                                    k9 = 50 + beastboost;
                                                }
                                            }
                                        }
                                        int nodumb = 0;
                                        Label_25647: {
                                            Label_25643: {
                                                if (this.variable.completed[0] >= 12) {
                                                    if (checkpoints.clear[madness.im] > checkpoints.clear[0] + 6) {
                                                        break Label_25643;
                                                    }
                                                }
                                                if (checkpoints.dested[xtgraphics.nplayers - 1] <= 0) {
                                                    break Label_25647;
                                                }
                                            }
                                            nodumb = 10;
                                        }
                                        if (checkpoints.stage == 19) {
                                            if (madness.shadowcar) {
                                                k9 = 80 - nodumb;
                                            }
                                        }
                                    }
                                    if (checkpoints.stage == 22) {
                                        if (madness.im == xtgraphics.nplayers - 1) {
                                            int gottagofix = 0;
                                            Label_25998: {
                                                if (!madness.redstr) {
                                                    if (!madness.leech) {
                                                        break Label_25998;
                                                    }
                                                }
                                                gottagofix = 15;
                                            }
                                            if (!madness.specialact) {
                                                k9 = 75 - gottagofix;
                                            }
                                            else {
                                                k9 = 90;
                                            }
                                        }
                                        else {
                                            int weakened = 0;
                                            if (this.variable.weaken[madness.im]) {
                                                weakened = 15;
                                            }
                                            int beastboost2 = 0;
                                            if (madness.beast[madness.im]) {
                                                beastboost2 = 20;
                                            }
                                            if (this.attack != 0) {
                                                int wimpness = 80;
                                                Label_25841: {
                                                    if (madness.cn != 14) {
                                                        if (madness.cn != 17) {
                                                            if (madness.cn != 37) {
                                                                break Label_25841;
                                                            }
                                                        }
                                                    }
                                                    wimpness = 50;
                                                }
                                                Label_25936: {
                                                    if (madness.cn != 15) {
                                                        if (madness.cn != 16) {
                                                            if (madness.cn != 13) {
                                                                if (madness.cn != 36) {
                                                                    if (madness.cn != 38) {
                                                                        break Label_25936;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                    if (!madness.specialact) {
                                                        wimpness = 50;
                                                    }
                                                    else {
                                                        wimpness = 65;
                                                    }
                                                }
                                                k9 = wimpness - weakened + beastboost2;
                                                if (k9 > 80) {
                                                    k9 = 80;
                                                }
                                            }
                                            else {
                                                k9 = 50 - weakened + beastboost2;
                                            }
                                        }
                                    }
                                    Label_26142: {
                                        if (checkpoints.stage != 16) {
                                            if (checkpoints.stage != 18) {
                                                break Label_26142;
                                            }
                                        }
                                        Label_26138: {
                                            if (this.attack == 0) {
                                                Label_26108: {
                                                    if (madness.cn != 15) {
                                                        if (madness.cn != 38) {
                                                            break Label_26108;
                                                        }
                                                    }
                                                    if (checkpoints.stage != 18) {
                                                        break Label_26138;
                                                    }
                                                }
                                                if (madness.shadowcar) {
                                                    k9 = 65;
                                                }
                                                else {
                                                    k9 = 55;
                                                }
                                                break Label_26142;
                                            }
                                        }
                                        k9 = 80;
                                    }
                                    if (checkpoints.stage == 17) {
                                        if (!madness.beast[madness.im]) {
                                            if (!madness.shadowcar) {
                                                k9 = 55;
                                                if (madness.cn == 16) {
                                                    k9 = 70;
                                                }
                                                break Label_26216;
                                            }
                                        }
                                        k9 = 80;
                                    }
                                }
                            }
                            if (checkpoints.stage == 16) {
                                if (checkpoints.clear[madness.im] - checkpoints.clear[0] >= 5) {
                                    if (madness.cn != 10) {
                                        if (madness.cn != 12) {
                                            if (madness.cn != 33) {
                                                if (madness.cn != 35) {
                                                    if (xtgraphics.classicmode) {
                                                        k9 = 50;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (100.0f * madness.hitmag / madness.maxmag[madness.cn] > k9) {
                                this.trfix = 2;
                            }
                            this.fixby = k9;
                        }
                        if (flag3) {
                            this.fixby = 100;
                        }
                    }
                    if (this.trfix == 0) {
                        this.setfixfloor = false;
                    }
                    if (this.bulistc) {
                        if (checkpoints.stage == 8) {
                            if (xtgraphics.classicmode) {
                                --this.runbul;
                                if (madness.pcleared == 10) {
                                    this.runbul = 0;
                                }
                                if (this.runbul <= 0) {
                                    this.bulistc = false;
                                }
                            }
                        }
                    }
                    else {
                        Label_27389: {
                            if (xtgraphics.careermode) {
                                Label_26518: {
                                    if (checkpoints.stage == 8) {
                                        if (madness.cn != 11) {
                                            if (madness.cn != 34) {
                                                break Label_26518;
                                            }
                                        }
                                        if (this.variable.biglead[0]) {
                                            this.bulistc = true;
                                        }
                                    }
                                }
                                Label_26752: {
                                    if (checkpoints.stage == 11) {
                                        if (!xtgraphics.bonusstage[1]) {
                                            boolean hardstage2 = false;
                                            Label_26678: {
                                                if (xtgraphics.unlocked[1] != 11) {
                                                    if (!xtgraphics.hardstage) {
                                                        break Label_26678;
                                                    }
                                                }
                                                hardstage2 = true;
                                            }
                                            if (madness.cn != 13) {
                                                if (madness.cn != 36) {
                                                    if (!hardstage2) {
                                                        break Label_26752;
                                                    }
                                                    if (madness.im <= 4) {
                                                        break Label_26752;
                                                    }
                                                    if (!xtgraphics.undead[madness.im]) {
                                                        break Label_26752;
                                                    }
                                                }
                                            }
                                            this.bulistc = true;
                                        }
                                        else {
                                            Label_26637: {
                                                Label_26632: {
                                                    Label_26601: {
                                                        if (madness.im != 1) {
                                                            if (madness.im != 2) {
                                                                if (madness.im != 4) {
                                                                    break Label_26601;
                                                                }
                                                            }
                                                        }
                                                        if (checkpoints.clear[0] >= 2) {
                                                            break Label_26632;
                                                        }
                                                    }
                                                    if (madness.cn != 33) {
                                                        break Label_26637;
                                                    }
                                                    if (!this.variable.biglead[0]) {
                                                        break Label_26637;
                                                    }
                                                }
                                                this.bulistc = true;
                                            }
                                        }
                                    }
                                }
                                Label_26799: {
                                    if (checkpoints.stage == 12) {
                                        if (madness.cn != 13) {
                                            if (madness.cn != 36) {
                                                break Label_26799;
                                            }
                                        }
                                        this.bulistc = true;
                                    }
                                }
                                if (checkpoints.stage == 13) {
                                    if (conto.floorguardian) {
                                        this.bulistc = true;
                                    }
                                }
                                Label_26977: {
                                    if (checkpoints.stage == 15) {
                                        if (!xtgraphics.bonusstage[2]) {
                                            if (checkpoints.clear[0] >= 3) {
                                                Label_26972: {
                                                    if (madness.cn != 15) {
                                                        if (madness.cn != 38) {
                                                            Label_26959: {
                                                                if (madness.cn != 11) {
                                                                    if (madness.cn != 34) {
                                                                        break Label_26959;
                                                                    }
                                                                }
                                                                if (madness.beast[madness.im]) {
                                                                    break Label_26972;
                                                                }
                                                                if (madness.shadowcar) {
                                                                    break Label_26972;
                                                                }
                                                            }
                                                            if (madness.im != 2) {
                                                                break Label_26977;
                                                            }
                                                        }
                                                    }
                                                }
                                                this.bulistc = true;
                                            }
                                        }
                                    }
                                }
                                Label_27039: {
                                    if (checkpoints.stage == 16) {
                                        if (checkpoints.clear[0] >= 3) {
                                            if (madness.cn != 13) {
                                                if (madness.cn != 36) {
                                                    break Label_27039;
                                                }
                                            }
                                            this.bulistc = true;
                                        }
                                    }
                                }
                                Label_27086: {
                                    if (checkpoints.stage == 18) {
                                        if (madness.cn != 13) {
                                            if (madness.cn != 36) {
                                                break Label_27086;
                                            }
                                        }
                                        this.bulistc = true;
                                    }
                                }
                                if (checkpoints.stage == 22) {
                                    if (madness.im != xtgraphics.nplayers - 1) {
                                        if (checkpoints.dested[xtgraphics.nplayers - 1] == 0) {
                                            this.bulistc = true;
                                        }
                                    }
                                }
                                Label_27205: {
                                    if (checkpoints.stage == 21) {
                                        if (madness.cn != 18) {
                                            if (madness.cn != 13) {
                                                if (madness.cn != 36) {
                                                    break Label_27205;
                                                }
                                            }
                                        }
                                        this.bulistc = true;
                                    }
                                }
                                if (checkpoints.stage == 23) {
                                    if (madness.cn == 18) {
                                        if (this.variable.lotswasted) {
                                            this.bulistc = false;
                                        }
                                        else {
                                            this.bulistc = true;
                                        }
                                    }
                                    Label_27296: {
                                        if (madness.cn != 13) {
                                            if (madness.cn != 36) {
                                                break Label_27296;
                                            }
                                        }
                                        this.bulistc = true;
                                    }
                                    if (madness.cn != 15) {
                                        if (madness.cn != 38) {
                                            break Label_27389;
                                        }
                                    }
                                    if (this.variable.completed[0] >= 33) {
                                        if (checkpoints.pos[0] < checkpoints.pos[madness.im]) {
                                            if (checkpoints.pos[madness.im] > 3) {
                                                this.bulistc = true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (checkpoints.stage == 8) {
                            if (madness.cn == 34) {
                                if (madness.pcleared == 35) {
                                    if (xtgraphics.classicmode) {
                                        madness.pcleared = 73;
                                        madness.clear = 0;
                                        this.bulistc = true;
                                        this.runbul = (int)(100.0f * this.m.random());
                                    }
                                }
                            }
                        }
                        if (checkpoints.stage == 11) {
                            if (madness.cn == 36) {
                                if (xtgraphics.classicmode) {
                                    this.bulistc = true;
                                }
                            }
                        }
                        if (checkpoints.stage == 12) {
                            if (madness.cn == 36) {
                                if (xtgraphics.classicmode) {
                                    this.bulistc = true;
                                }
                            }
                        }
                        if (checkpoints.stage == 15) {
                            if (checkpoints.clear[0] - madness.clear >= 3) {
                                if (this.trfix == 0) {
                                    if (xtgraphics.classicmode) {
                                        this.bulistc = true;
                                        this.oupnt = -1;
                                    }
                                }
                            }
                        }
                        if (checkpoints.stage == 16) {
                            if (xtgraphics.classicmode) {
                                if (madness.cn == 36) {
                                    if (checkpoints.pcleared == 8) {
                                        this.bulistc = true;
                                        this.attack = 0;
                                    }
                                }
                                if (madness.cn == 34) {
                                    if (checkpoints.clear[0] - madness.clear >= 2) {
                                        if (this.trfix == 0) {
                                            this.bulistc = true;
                                            this.oupnt = -1;
                                        }
                                    }
                                }
                            }
                        }
                        Label_27894: {
                            Label_27838: {
                                if (checkpoints.stage != 2) {
                                    if (checkpoints.stage != 3) {
                                        if (checkpoints.stage != 4) {
                                            if (checkpoints.stage == 8) {
                                                if (xtgraphics.classicmode) {
                                                    break Label_27838;
                                                }
                                            }
                                            if (checkpoints.stage != 10) {
                                                break Label_27894;
                                            }
                                        }
                                    }
                                }
                            }
                            if (madness.cn != 13) {
                                if (madness.cn != 36) {
                                    break Label_27894;
                                }
                            }
                            if (Math.abs(checkpoints.clear[0] - madness.clear) >= 2) {
                                this.bulistc = true;
                            }
                        }
                    }
                    this.stcnt = 0;
                    this.statusque = (int)(20.0f * this.m.random());
                }
            }
            boolean flag4 = false;
            if (!this.usebounce) {
                flag4 = madness.mtouch;
            }
            else {
                flag4 = madness.wtouch;
            }
            if (!flag4) {
                float groundlevel = madness.groundlevel;
                if (groundlevel >= 0.0f) {
                    groundlevel = 0.0f;
                }
                if (this.trickfase == 0) {
                    int m = (int)((madness.scy[0] + madness.scy[1] + madness.scy[2] + madness.scy[3]) * (conto.y - 300 - groundlevel) / 4000.0f);
                    int i7 = 3;
                    if (checkpoints.stage == 15) {
                        i7 = 10;
                    }
                    int heightlim = 7;
                    if (this.variable.opbackloops[madness.im]) {
                        m = 4;
                    }
                    if (xtgraphics.careermode) {
                        if (checkpoints.stage == 22) {
                            if (madness.cn == 18) {
                                if (madness.pcleared == 111) {
                                    heightlim = 15;
                                }
                                else {
                                    heightlim = 2;
                                }
                            }
                        }
                    }
                    Label_58694: {
                        Label_58689: {
                            if (m > heightlim) {
                                if (this.m.random() <= this.trickprf / i7) {
                                    if (this.stuntf != 4) {
                                        if (this.stuntf != 3) {
                                            if (this.stuntf != 5) {
                                                if (this.stuntf != 6) {
                                                    if (this.stuntf < 8) {
                                                        if (checkpoints.stage != 16) {
                                                            break Label_58689;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                this.oxy = madness.pxy;
                                this.ozy = madness.pzy;
                                this.flycnt = 0;
                                this.uddirect = 0;
                                this.lrdirect = 0;
                                this.udswt = false;
                                this.lrswt = false;
                                this.trickfase = 1;
                                Label_58684: {
                                    if (!this.variable.dontstunt[madness.im]) {
                                        if (m >= 16) {
                                            Label_57913: {
                                                Label_57651: {
                                                    Label_56419: {
                                                        if (this.m.random() > this.m.random()) {
                                                            if (this.stuntf != 1) {
                                                                break Label_56419;
                                                            }
                                                        }
                                                        if (this.stuntf != 4) {
                                                            if (this.stuntf < 6) {
                                                                break Label_57651;
                                                            }
                                                        }
                                                    }
                                                    if (this.stuntf != 13) {
                                                        if (this.stuntf != 14) {
                                                            if (this.stuntf == 9) {
                                                                if (madness.power >= 70.0f) {
                                                                    this.uddirect = 1;
                                                                }
                                                                else {
                                                                    this.uddirect = -1;
                                                                }
                                                            }
                                                            else {
                                                                Label_56595: {
                                                                    Label_56580: {
                                                                        Label_56552: {
                                                                            if (this.m.random() <= this.m.random()) {
                                                                                if (this.stuntf != 2) {
                                                                                    if (this.stuntf != 7) {
                                                                                        break Label_56552;
                                                                                    }
                                                                                }
                                                                            }
                                                                            if (this.stuntf != 4) {
                                                                                if (this.stuntf != 6) {
                                                                                    if (this.stuntf != 10) {
                                                                                        break Label_56580;
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                        if (this.stuntf != 11) {
                                                                            if (this.stuntf != 12) {
                                                                                this.uddirect = 1;
                                                                                break Label_56595;
                                                                            }
                                                                        }
                                                                    }
                                                                    this.uddirect = -1;
                                                                }
                                                            }
                                                            this.udstart = (int)(10.0f * this.m.random() * this.trickprf);
                                                            Label_56698: {
                                                                if (this.stuntf != 6) {
                                                                    if (this.stuntf != 4) {
                                                                        if (this.stuntf < 9) {
                                                                            break Label_56698;
                                                                        }
                                                                    }
                                                                }
                                                                this.udstart = 0;
                                                            }
                                                            Label_56772: {
                                                                if (checkpoints.stage != 16) {
                                                                    if (checkpoints.stage != 9) {
                                                                        if (checkpoints.stage != 10) {
                                                                            if (checkpoints.stage != 14) {
                                                                                break Label_56772;
                                                                            }
                                                                        }
                                                                    }
                                                                    if (!xtgraphics.careermode) {
                                                                        break Label_56772;
                                                                    }
                                                                }
                                                                this.udstart = 0;
                                                            }
                                                            Label_56839: {
                                                                if (checkpoints.stage == 14) {
                                                                    if (this.oupnt != 68) {
                                                                        if (this.oupnt != 69) {
                                                                            break Label_56839;
                                                                        }
                                                                    }
                                                                    if (xtgraphics.classicmode) {
                                                                        this.apunch = 20;
                                                                        this.oupnt = 70;
                                                                    }
                                                                }
                                                            }
                                                            if (xtgraphics.careermode) {
                                                                Label_56914: {
                                                                    if (checkpoints.stage == 15) {
                                                                        if (madness.pcleared == 20) {
                                                                            if (this.oupnt != 26) {
                                                                                if (this.oupnt != 27) {
                                                                                    break Label_56914;
                                                                                }
                                                                            }
                                                                            this.apunch = 20;
                                                                        }
                                                                    }
                                                                }
                                                                Label_57173: {
                                                                    if (checkpoints.stage == 14) {
                                                                        if (madness.cn != 14) {
                                                                            if (madness.cn != 10) {
                                                                                if (madness.cn != 33) {
                                                                                    if (madness.cn != 37) {
                                                                                        break Label_57173;
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                        Label_57161: {
                                                                            if (this.oupnt != 23) {
                                                                                if (this.oupnt != 24) {
                                                                                    Label_57058: {
                                                                                        if (this.oupnt != 148) {
                                                                                            if (this.oupnt != 149) {
                                                                                                break Label_57058;
                                                                                            }
                                                                                        }
                                                                                        if (madness.power >= 80.0f) {
                                                                                            break Label_57161;
                                                                                        }
                                                                                    }
                                                                                    if (this.oupnt != 189) {
                                                                                        if (this.oupnt != 190) {
                                                                                            if (this.oupnt != 234) {
                                                                                                if (this.oupnt != 320) {
                                                                                                    if (this.oupnt != 321) {
                                                                                                        break Label_57173;
                                                                                                    }
                                                                                                }
                                                                                                if (madness.specialact) {
                                                                                                    break Label_57173;
                                                                                                }
                                                                                                if (madness.power < 80.0f) {
                                                                                                    break Label_57173;
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                        this.abdelay = 3;
                                                                        this.abboost = 200;
                                                                    }
                                                                }
                                                                if (checkpoints.stage == 21) {
                                                                    if (madness.nostunts < 2) {
                                                                        Label_57243: {
                                                                            if (madness.cn != 13) {
                                                                                if (madness.cn != 36) {
                                                                                    this.abdelay = 3;
                                                                                    break Label_57243;
                                                                                }
                                                                            }
                                                                            this.abdelay = 2;
                                                                        }
                                                                        this.abboost = 4000;
                                                                    }
                                                                }
                                                            }
                                                            if (this.m.random() > 0.85) {
                                                                if (this.stuntf != 4) {
                                                                    if (this.stuntf != 3) {
                                                                        if (this.stuntf != 6) {
                                                                            if (this.stuntf != 8) {
                                                                                if (this.stuntf != 9) {
                                                                                    if (this.stuntf != 11) {
                                                                                        if (this.stuntf != 12) {
                                                                                            if (checkpoints.stage != 16) {
                                                                                                this.udswt = true;
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                            Label_57646: {
                                                                Label_57506: {
                                                                    if (this.m.random() > this.trickprf + 0.3f) {
                                                                        if (this.stuntf != 4) {
                                                                            if (this.stuntf != 6) {
                                                                                if (this.stuntf != 8) {
                                                                                    if (this.stuntf != 9) {
                                                                                        if (this.stuntf != 12) {
                                                                                            break Label_57506;
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                    if (this.stuntf != 10) {
                                                                        if (this.stuntf != 11) {
                                                                            break Label_57646;
                                                                        }
                                                                    }
                                                                }
                                                                if (this.m.random() <= this.m.random()) {
                                                                    this.lrdirect = 1;
                                                                }
                                                                else {
                                                                    this.lrdirect = -1;
                                                                }
                                                                this.lrstart = (int)(30.0f * this.m.random());
                                                                Label_57593: {
                                                                    if (this.stuntf != 10) {
                                                                        if (this.stuntf != 11) {
                                                                            break Label_57593;
                                                                        }
                                                                    }
                                                                    this.lrstart = 0;
                                                                }
                                                                if (this.m.random() > 0.75) {
                                                                    if (this.stuntf != 10) {
                                                                        if (this.stuntf != 11) {
                                                                            this.lrswt = true;
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                            break Label_57913;
                                                        }
                                                    }
                                                }
                                                Label_57703: {
                                                    if (this.m.random() > this.m.random()) {
                                                        if (this.stuntf != 14) {
                                                            this.lrdirect = -1;
                                                            break Label_57703;
                                                        }
                                                    }
                                                    this.lrdirect = 1;
                                                }
                                                this.lrstart = (int)(10.0f * this.m.random() * this.trickprf);
                                                if (this.stuntf == 13) {
                                                    this.lrstart = 0;
                                                }
                                                if (this.m.random() > 0.75) {
                                                    if (checkpoints.stage != 16) {
                                                        if (this.stuntf != 13) {
                                                            this.lrswt = true;
                                                        }
                                                    }
                                                }
                                                if (this.m.random() > this.trickprf + 0.3f) {
                                                    if (this.stuntf != 13) {
                                                        if (this.m.random() <= this.m.random()) {
                                                            this.uddirect = 1;
                                                        }
                                                        else {
                                                            this.uddirect = -1;
                                                        }
                                                        this.udstart = (int)(30.0f * this.m.random());
                                                        if (this.m.random() > 0.85) {
                                                            this.udswt = true;
                                                        }
                                                    }
                                                }
                                            }
                                            if (this.trfix != 3) {
                                                if (this.trfix != 4) {
                                                    break Label_58684;
                                                }
                                            }
                                            if (xtgraphics.classicmode) {
                                                if (checkpoints.stage == 8) {
                                                    if (this.uddirect != 0) {
                                                        this.uddirect = -1;
                                                    }
                                                    this.lrdirect = 0;
                                                }
                                                else {
                                                    if (checkpoints.stage != 15) {
                                                        if (this.lrdirect == -1) {
                                                            if (checkpoints.stage == 9) {
                                                                this.uddirect = 1;
                                                            }
                                                            else {
                                                                this.uddirect = -1;
                                                            }
                                                        }
                                                    }
                                                    this.lrdirect = 0;
                                                    Label_58073: {
                                                        if (checkpoints.stage != 9) {
                                                            if (checkpoints.stage != 15) {
                                                                break Label_58073;
                                                            }
                                                        }
                                                        if (this.uddirect == -1) {
                                                            this.uddirect = 1;
                                                        }
                                                    }
                                                    if (madness.power < 60.0f) {
                                                        this.uddirect = -1;
                                                    }
                                                }
                                            }
                                            if (xtgraphics.careermode) {
                                                Label_58179: {
                                                    if (checkpoints.stage != 9) {
                                                        if (checkpoints.stage != 2) {
                                                            if (checkpoints.stage != 12) {
                                                                break Label_58179;
                                                            }
                                                        }
                                                    }
                                                    this.uddirect = 1;
                                                }
                                                Label_58241: {
                                                    if (checkpoints.stage == 13) {
                                                        this.uddirect = 1;
                                                        if (xtgraphics.floor[madness.im] != 2) {
                                                            if (xtgraphics.floor[madness.im] != 1) {
                                                                break Label_58241;
                                                            }
                                                        }
                                                        this.uddirect = -1;
                                                    }
                                                }
                                                if (checkpoints.stage == 16) {
                                                    this.uddirect = 1;
                                                    this.udstart = 2;
                                                }
                                                Label_58298: {
                                                    if (checkpoints.stage != 17) {
                                                        if (checkpoints.stage != 18) {
                                                            break Label_58298;
                                                        }
                                                    }
                                                    this.uddirect = 1;
                                                }
                                                Label_58376: {
                                                    if (checkpoints.stage != 7) {
                                                        if (checkpoints.stage != 11) {
                                                            break Label_58376;
                                                        }
                                                        if (xtgraphics.bonusstage[1]) {
                                                            break Label_58376;
                                                        }
                                                        if (!xtgraphics.hardstage) {
                                                            if (xtgraphics.unlocked[1] != 11) {
                                                                break Label_58376;
                                                            }
                                                        }
                                                    }
                                                    this.uddirect = -1;
                                                }
                                                Label_58448: {
                                                    if (checkpoints.stage == 11) {
                                                        if (xtgraphics.bonusstage[1]) {
                                                            if (madness.cn != 13) {
                                                                if (madness.cn != 36) {
                                                                    this.uddirect = 1;
                                                                    break Label_58448;
                                                                }
                                                            }
                                                            this.uddirect = 0;
                                                        }
                                                    }
                                                }
                                                if (checkpoints.stage == 22) {
                                                    this.uddirect = 0;
                                                }
                                                Label_58538: {
                                                    if (checkpoints.stage == 23) {
                                                        if (madness.cn != 13) {
                                                            if (madness.cn != 18) {
                                                                if (madness.cn != 36) {
                                                                    this.uddirect = 1;
                                                                    break Label_58538;
                                                                }
                                                            }
                                                        }
                                                        this.uddirect = -1;
                                                    }
                                                }
                                                this.lrdirect = 0;
                                            }
                                            if (checkpoints.stage == 16) {
                                                if (xtgraphics.classicmode) {
                                                    this.uddirect = -1;
                                                    this.lrdirect = 0;
                                                    Label_58660: {
                                                        if (madness.cn != 34) {
                                                            if (madness.cn != 36) {
                                                                this.udstart = 7;
                                                                if (madness.cn == 37) {
                                                                    if (madness.power > 30.0f) {
                                                                        this.udstart = 14;
                                                                    }
                                                                }
                                                                break Label_58660;
                                                            }
                                                        }
                                                        this.udstart = 0;
                                                    }
                                                    if (madness.cn == 34) {
                                                        this.lrdirect = -1;
                                                        this.lrstart = 0;
                                                    }
                                                }
                                            }
                                        }
                                        else if (this.stuntf == 6) {
                                            this.uddirect = 1;
                                            this.udstart = 0;
                                            this.udswt = false;
                                        }
                                        else {
                                            if (!this.dontback) {
                                                this.uddirect = -1;
                                            }
                                            this.udstart = 0;
                                            this.udswt = false;
                                        }
                                    }
                                }
                                break Label_58694;
                            }
                        }
                        this.trickfase = -1;
                    }
                    if (!this.afta) {
                        this.afta = true;
                    }
                    if (this.trfix == 3) {
                        this.trfix = 4;
                        this.statusque += 30;
                    }
                }
                Label_59261: {
                    if (this.trickfase == 1) {
                        ++this.flycnt;
                        if (this.lrdirect != 0) {
                            if (this.flycnt > this.lrstart) {
                                if (this.lrswt) {
                                    if (Math.abs(madness.pxy - this.oxy) > 180) {
                                        if (this.lrdirect != -1) {
                                            this.lrdirect = -1;
                                        }
                                        else {
                                            this.lrdirect = 1;
                                        }
                                        this.lrswt = false;
                                    }
                                }
                                if (this.lrdirect != -1) {
                                    this.handb = true;
                                    this.right = true;
                                }
                                else {
                                    this.handb = true;
                                    this.left = true;
                                }
                            }
                        }
                        if (this.uddirect != 0) {
                            if (this.flycnt > this.udstart) {
                                if (this.udswt) {
                                    if (Math.abs(madness.pzy - this.ozy) > 180) {
                                        if (this.uddirect != -1) {
                                            this.uddirect = -1;
                                        }
                                        else {
                                            this.uddirect = 1;
                                        }
                                        this.udswt = false;
                                    }
                                }
                                if (this.uddirect != -1) {
                                    this.handb = true;
                                    this.up = true;
                                    if (this.apunch > 0) {
                                        this.down = true;
                                        --this.apunch;
                                    }
                                    if (this.abdelay > 0) {
                                        --this.abdelay;
                                    }
                                    if (this.abboost > 0) {
                                        if (this.abdelay == 0) {
                                            this.down = true;
                                            --this.abboost;
                                        }
                                    }
                                }
                                else {
                                    this.handb = true;
                                    this.down = true;
                                }
                            }
                        }
                        Label_59211: {
                            if ((madness.scy[0] + madness.scy[1] + madness.scy[2] + madness.scy[3]) * 100.0f / (conto.y - 300 - (int)groundlevel) < -this.saftey) {
                                if (this.abboost == 0) {
                                    break Label_59211;
                                }
                            }
                            if (!this.variable.dontstunt[madness.im]) {
                                break Label_59261;
                            }
                        }
                        this.onceu = false;
                        this.onced = false;
                        this.oncel = false;
                        this.oncer = false;
                        this.lrcomp = false;
                        this.udcomp = false;
                        this.udbare = false;
                        this.lrbare = false;
                        this.trickfase = 2;
                        this.swat = 0;
                    }
                }
                if (this.trickfase == 2) {
                    if (this.swat == 0) {
                        Label_59319: {
                            if (madness.dcomp == 0.0f) {
                                if (madness.ucomp == 0.0f) {
                                    break Label_59319;
                                }
                            }
                            this.udbare = true;
                        }
                        Label_59352: {
                            if (madness.lcomp == 0.0f) {
                                if (madness.rcomp == 0.0f) {
                                    break Label_59352;
                                }
                            }
                            this.lrbare = true;
                        }
                        this.swat = 1;
                    }
                    if (!madness.wtouch) {
                        if (this.swat == 2) {
                            if (madness.capsized) {
                                if (this.m.random() > this.mustland) {
                                    if (!this.udbare) {
                                        if (this.lrbare) {
                                            this.udbare = true;
                                            this.lrbare = false;
                                        }
                                    }
                                    else {
                                        this.lrbare = true;
                                        this.udbare = false;
                                    }
                                }
                            }
                            this.swat = 3;
                        }
                    }
                    else if (this.swat == 1) {
                        this.swat = 2;
                    }
                    if (this.udbare) {
                        int l7;
                        for (l7 = madness.pzy + 90; l7 < 0; l7 += 360) {}
                        while (l7 > 180) {
                            l7 -= 360;
                        }
                        l7 = Math.abs(l7);
                        Label_59614: {
                            if (madness.lcomp - madness.rcomp < 5.0f) {
                                if (!this.onced) {
                                    if (!this.onceu) {
                                        break Label_59614;
                                    }
                                }
                                this.udcomp = true;
                            }
                        }
                        if (madness.dcomp <= madness.ucomp) {
                            if (!madness.capsized) {
                                if (!this.udcomp) {
                                    if (this.m.random() > this.mustland) {
                                        this.down = true;
                                    }
                                }
                                else if (this.perfection) {
                                    if (Math.abs(l7 - 90) > 30) {
                                        if (l7 <= 90) {
                                            this.down = true;
                                        }
                                        else {
                                            this.up = true;
                                        }
                                    }
                                }
                                this.onceu = true;
                            }
                            else if (!this.udcomp) {
                                if (!this.onceu) {
                                    this.up = true;
                                }
                            }
                            else if (l7 <= 90) {
                                this.down = true;
                            }
                            else {
                                this.up = true;
                            }
                        }
                        else if (!madness.capsized) {
                            if (!this.udcomp) {
                                if (this.m.random() > this.mustland) {
                                    this.up = true;
                                }
                            }
                            else if (this.perfection) {
                                if (Math.abs(l7 - 90) > 30) {
                                    if (l7 <= 90) {
                                        this.down = true;
                                    }
                                    else {
                                        this.up = true;
                                    }
                                }
                            }
                            this.onced = true;
                        }
                        else if (!this.udcomp) {
                            if (!this.onced) {
                                this.down = true;
                            }
                        }
                        else if (l7 <= 90) {
                            this.down = true;
                        }
                        else {
                            this.up = true;
                        }
                    }
                    if (this.lrbare) {
                        int i8 = madness.pxy + 90;
                        if (this.zyinv) {
                            i8 += 180;
                        }
                        while (i8 < 0) {
                            i8 += 360;
                        }
                        while (i8 > 180) {
                            i8 -= 360;
                        }
                        i8 = Math.abs(i8);
                        Label_60141: {
                            if (madness.lcomp - madness.rcomp < 10.0f) {
                                if (!this.oncel) {
                                    if (!this.oncer) {
                                        break Label_60141;
                                    }
                                }
                                this.lrcomp = true;
                            }
                        }
                        if (madness.lcomp <= madness.rcomp) {
                            if (!madness.capsized) {
                                if (!this.lrcomp) {
                                    if (this.m.random() > this.mustland) {
                                        this.left = true;
                                    }
                                }
                                else if (this.perfection) {
                                    if (Math.abs(i8 - 90) > 30) {
                                        if (i8 <= 90) {
                                            this.right = true;
                                        }
                                        else {
                                            this.left = true;
                                        }
                                    }
                                }
                                this.oncer = true;
                            }
                            else if (!this.lrcomp) {
                                if (!this.oncer) {
                                    this.right = true;
                                }
                            }
                            else if (i8 <= 90) {
                                this.right = true;
                            }
                            else {
                                this.left = true;
                            }
                        }
                        else if (!madness.capsized) {
                            if (!this.lrcomp) {
                                if (this.m.random() > this.mustland) {
                                    this.right = true;
                                }
                            }
                            else if (this.perfection) {
                                if (Math.abs(i8 - 90) > 30) {
                                    if (i8 <= 90) {
                                        this.right = true;
                                    }
                                    else {
                                        this.left = true;
                                    }
                                }
                            }
                            this.oncel = true;
                        }
                        else if (!this.lrcomp) {
                            if (!this.oncel) {
                                this.left = true;
                            }
                        }
                        else if (i8 <= 90) {
                            this.right = true;
                        }
                        else {
                            this.left = true;
                        }
                    }
                }
            }
            else {
                if (this.trickfase != 0) {
                    this.trickfase = 0;
                }
                Label_28098: {
                    if (this.trfix != 2) {
                        if (this.trfix != 3) {
                            break Label_28098;
                        }
                    }
                    this.attack = 0;
                }
                if (this.trfix < 2) {
                    this.backfix = false;
                }
                if (xtgraphics.careermode) {
                    Label_28206: {
                        if (checkpoints.stage == 17) {
                            Label_28201: {
                                if (this.fpnt[this.variable.whichfix] == 14) {
                                    if (this.trfix != 2) {
                                        if (this.trfix != 3) {
                                            break Label_28201;
                                        }
                                    }
                                    this.backfix = true;
                                    break Label_28206;
                                }
                            }
                            this.backfix = false;
                        }
                    }
                    if (checkpoints.stage == 21) {
                        if (xtgraphics.entered[madness.im]) {
                            this.hold = 20;
                        }
                    }
                }
                if (this.attack != 0) {
                    this.wrongfloor = false;
                    if (xtgraphics.careermode) {
                        if (checkpoints.stage == 13) {
                            if (xtgraphics.floor[madness.im] != xtgraphics.floor[this.acr]) {
                                if (!conto.floorguardian) {
                                    this.wrongfloor = true;
                                    this.gotofloor = xtgraphics.floor[this.acr];
                                }
                                else {
                                    this.attack = 0;
                                }
                            }
                        }
                    }
                    if (this.wrongfloor) {
                        this.up = true;
                        for (int a6 = 0; a6 < checkpoints.n; ++a6) {
                            if (checkpoints.telefloor[a6] == this.gotofloor) {
                                if (checkpoints.floor[a6] == xtgraphics.floor[madness.im]) {
                                    char c5 = '\0';
                                    if (checkpoints.x[a6] - conto.x >= 0) {
                                        c5 = '´';
                                    }
                                    this.pan = (int)('Z' + c5 + Math.atan((checkpoints.z[a6] - conto.z) / (double)(checkpoints.x[a6] - conto.x)) / 0.017453292519943295);
                                }
                            }
                        }
                    }
                    else {
                        if (!this.fewsecson) {
                            this.up = true;
                        }
                        char c6 = '\0';
                        if (this.intercept) {
                            if (this.campchk == -1) {
                                this.campchk = checkpoints.clear[0] + this.chkahead;
                            }
                            int arrive = this.campchk;
                            if (arrive > checkpoints.nsp) {
                                arrive -= checkpoints.nsp;
                            }
                            this.l3 = checkpoints.x[checkpoints.chkcode[arrive]];
                            this.k5 = checkpoints.z[checkpoints.chkcode[arrive]];
                            if (this.py(conto.x / 100, this.l3 / 100, conto.z / 100, this.k5 / 100) < 1000) {
                                if (this.waitforuser) {
                                    if (this.py(conto.x / 100, checkpoints.opx[0] / 100, conto.z / 100, checkpoints.opz[0] / 100) < 8000) {
                                        this.campcool = this.campchk + 4;
                                        this.intercept = false;
                                    }
                                }
                                else {
                                    this.campcool = this.campchk + 4;
                                    this.intercept = false;
                                }
                            }
                        }
                        else {
                            this.l1 = (int)(this.pys(conto.x, checkpoints.opx[this.acr], conto.z, checkpoints.opz[this.acr]) / 2.0f * this.aim);
                            this.l3 = (int)(checkpoints.opx[this.acr] - this.l1 * this.m.sin(checkpoints.omxz[this.acr]));
                            this.k5 = (int)(checkpoints.opz[this.acr] + this.l1 * this.m.cos(checkpoints.omxz[this.acr]));
                            this.campchk = -1;
                        }
                        if (this.l3 - conto.x >= 0) {
                            c6 = '´';
                        }
                        this.pan = (int)('Z' + c6 + Math.atan((this.k5 - conto.z) / (double)(this.l3 - conto.x)) / 0.017453292519943295);
                        --this.attack;
                        if (this.attack <= 0) {
                            this.attack = 0;
                        }
                        if (checkpoints.stage == 15) {
                            if (this.exitattack) {
                                if (!this.bulistc) {
                                    if (madness.missedcp != 0) {
                                        this.attack = 0;
                                    }
                                }
                            }
                        }
                        if (checkpoints.stage == 1) {
                            if (this.exitattack) {
                                this.attack = 0;
                            }
                        }
                        Label_54083: {
                            if (checkpoints.stage == 16) {
                                if (madness.cn != 13) {
                                    if (madness.cn != 36) {
                                        break Label_54083;
                                    }
                                }
                                if (checkpoints.clear[0] != 4) {
                                    if (checkpoints.clear[0] != 13) {
                                        if (checkpoints.clear[0] != 21) {
                                            break Label_54083;
                                        }
                                    }
                                }
                                this.attack = 0;
                            }
                        }
                        Label_54163: {
                            if (checkpoints.stage == 16) {
                                if (madness.missedcp != 0) {
                                    if (checkpoints.pos[madness.im] != 0) {
                                        if (checkpoints.pos[madness.im] != 1) {
                                            break Label_54163;
                                        }
                                        if (checkpoints.pos[0] != 0) {
                                            break Label_54163;
                                        }
                                    }
                                    this.attack = 0;
                                }
                            }
                        }
                        if (checkpoints.stage == 16) {
                            if (checkpoints.pos[0] > checkpoints.pos[madness.im]) {
                                if (madness.power < 80.0f) {
                                    this.attack = 0;
                                }
                            }
                        }
                    }
                }
                else {
                    if (this.upcnt < 30) {
                        if (this.revstart > 0) {
                            this.down = true;
                            Label_28414: {
                                if (xtgraphics.careermode) {
                                    Label_28380: {
                                        if (checkpoints.stage == 23) {
                                            if (xtgraphics.unlocked[1] == 23) {
                                                break Label_28380;
                                            }
                                            if (xtgraphics.hardstage) {
                                                break Label_28380;
                                            }
                                        }
                                        if (checkpoints.stage != 7) {
                                            break Label_28414;
                                        }
                                    }
                                    if (conto.x > 0) {
                                        this.left = true;
                                    }
                                    if (conto.x < 0) {
                                        this.right = true;
                                    }
                                }
                            }
                            --this.revstart;
                        }
                        else if (!this.fewsecson) {
                            this.up = true;
                        }
                    }
                    boolean didntmiss = false;
                    Label_28486: {
                        if (xtgraphics.careermode) {
                            if (checkpoints.stage != 9) {
                                if (checkpoints.stage != 19) {
                                    break Label_28486;
                                }
                                if (conto.z <= 58000) {
                                    break Label_28486;
                                }
                            }
                            didntmiss = true;
                        }
                    }
                    Label_28813: {
                        if (xtgraphics.careermode) {
                            Label_28541: {
                                if (madness.missedcp != 0) {
                                    if (!didntmiss) {
                                        break Label_28541;
                                    }
                                }
                                if (!this.variable.dontmiss[madness.im]) {
                                    break Label_28813;
                                }
                            }
                            int j4 = madness.pcleared + 1;
                            while (checkpoints.typ[j4] <= 0) {
                                if (++j4 != checkpoints.n) {
                                    continue;
                                }
                                j4 = 0;
                            }
                            int dontslowdown = this.variable.moreslow[madness.im];
                            if (checkpoints.stage >= 23) {
                                if (this.variable.chkcircle[madness.im] < 200) {
                                    dontslowdown = 0;
                                }
                            }
                            if (this.py(conto.x / 100, checkpoints.x[j4] / 100, conto.z / 100, checkpoints.z[j4] / 100) >= this.variable.slowrange[madness.im]) {
                                this.variable.nearchk[madness.im] = false;
                            }
                            else {
                                Label_28782: {
                                    if (madness.swits[madness.cn][2] < 340) {
                                        if (!this.variable.spdexception[madness.im]) {
                                            break Label_28782;
                                        }
                                    }
                                    if (madness.speed > this.variable.slowdown[madness.im] - dontslowdown) {
                                        this.up = false;
                                        this.handb = true;
                                    }
                                }
                                this.variable.nearchk[madness.im] = true;
                            }
                        }
                    }
                    if (this.variable.sharpturn[madness.im] > 0) {
                        int j4 = madness.pcleared + 1;
                        while (checkpoints.typ[j4] <= 0) {
                            if (++j4 != checkpoints.n) {
                                continue;
                            }
                            j4 = 0;
                        }
                        if (this.py(conto.x / 100, checkpoints.x[j4] / 100, conto.z / 100, checkpoints.z[j4] / 100) < 6000 + this.variable.sharpturn[madness.im] * 2000) {
                            if (madness.speed > 200.0f) {
                                this.clrnce = 2;
                                if (this.variable.sharpturn[madness.im] == 1) {
                                    this.down = true;
                                }
                                if (this.variable.sharpturn[madness.im] == 2) {
                                    this.handb = true;
                                }
                                if (this.variable.sharpturn[madness.im] >= 3) {
                                    this.handb = true;
                                    this.down = true;
                                }
                                this.up = false;
                            }
                        }
                    }
                    if (this.upcnt >= 25 + this.actwait) {
                        this.upcnt = 0;
                        this.actwait = this.upwait;
                    }
                    else {
                        ++this.upcnt;
                    }
                    int i9 = madness.point;
                    int k10 = 50;
                    if (checkpoints.stage == 8) {
                        k10 = 20;
                    }
                    if (checkpoints.stage == 15) {
                        k10 = 40;
                    }
                    if (checkpoints.stage == 16) {
                        k10 = 20;
                    }
                    Label_50241: {
                        Label_48115: {
                            if (this.bulistc) {
                                if (this.trfix != 2) {
                                    if (this.trfix != 3) {
                                        if (this.trfix != 4) {
                                            if (madness.power >= k10) {
                                                break Label_48115;
                                            }
                                        }
                                    }
                                }
                            }
                            if (!this.backfix) {
                                if (this.rampp == 1) {
                                    if (checkpoints.typ[i9] <= 0) {
                                        int l8 = i9 + 1;
                                        if (l8 == checkpoints.n) {
                                            l8 = 0;
                                        }
                                        if (checkpoints.typ[l8] == -2) {
                                            i9 = l8;
                                        }
                                    }
                                }
                                if (this.rampp == -1) {
                                    if (checkpoints.typ[i9] == -2) {
                                        if (++i9 == checkpoints.n) {
                                            i9 = 0;
                                        }
                                    }
                                }
                                if (this.m.random() <= this.skiplev) {
                                    if (this.m.random() > this.skiplev) {
                                        while (checkpoints.typ[i9] == -1) {
                                            if (++i9 != checkpoints.n) {
                                                continue;
                                            }
                                            i9 = 0;
                                        }
                                    }
                                }
                                else {
                                    int i10 = i9;
                                    boolean flag5 = false;
                                    if (checkpoints.typ[i10] > 0) {
                                        int i11 = 0;
                                        for (int i12 = 0; i12 < checkpoints.n; ++i12) {
                                            if (checkpoints.typ[i12] > 0) {
                                                if (i12 < i10) {
                                                    ++i11;
                                                }
                                            }
                                        }
                                        flag5 = (madness.clear != i11 + madness.nlaps * checkpoints.nsp);
                                    }
                                    while (true) {
                                        if (checkpoints.typ[i10] != 0) {
                                            if (checkpoints.typ[i10] != -1) {
                                                if (checkpoints.typ[i10] != -3) {
                                                    if (!flag5) {
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                        i9 = i10;
                                        if (++i10 == checkpoints.n) {
                                            i10 = 0;
                                        }
                                        flag5 = false;
                                        if (checkpoints.typ[i10] <= 0) {
                                            continue;
                                        }
                                        int j5 = 0;
                                        for (int j6 = 0; j6 < checkpoints.n; ++j6) {
                                            if (checkpoints.typ[j6] > 0) {
                                                if (j6 < i10) {
                                                    ++j5;
                                                }
                                            }
                                        }
                                        flag5 = (madness.clear != j5 + madness.nlaps * checkpoints.nsp);
                                    }
                                }
                                if (checkpoints.stage == 8) {
                                    if (madness.pcleared == 73) {
                                        if (this.trfix == 0) {
                                            if (madness.clear != 0) {
                                                if (xtgraphics.classicmode) {
                                                    i9 = 10;
                                                }
                                            }
                                        }
                                    }
                                }
                                if (checkpoints.stage == 9) {
                                    if (madness.pcleared == 18) {
                                        if (this.trfix == 0) {
                                            if (xtgraphics.classicmode) {
                                                i9 = 27;
                                            }
                                        }
                                    }
                                }
                                if (checkpoints.stage == 11) {
                                    if (xtgraphics.classicmode) {
                                        if (madness.pcleared == 5) {
                                            if (this.trfix == 0) {
                                                if (madness.power < 70.0f) {
                                                    if (i9 > 16) {
                                                        i9 = 21;
                                                    }
                                                    else {
                                                        i9 = 16;
                                                    }
                                                }
                                            }
                                        }
                                        if (madness.pcleared == 50) {
                                            i9 = 57;
                                        }
                                    }
                                }
                                Label_30096: {
                                    if (checkpoints.stage == 12) {
                                        if (madness.pcleared != 27) {
                                            if (madness.pcleared != 37) {
                                                break Label_30096;
                                            }
                                        }
                                        if (xtgraphics.classicmode) {
                                            while (checkpoints.typ[i9] == -1) {
                                                if (++i9 != checkpoints.n) {
                                                    continue;
                                                }
                                                i9 = 0;
                                            }
                                        }
                                    }
                                }
                                Label_30192: {
                                    if (xtgraphics.careermode) {
                                        if (checkpoints.stage == 12) {
                                            if (madness.pcleared != 15) {
                                                if (madness.pcleared != 29) {
                                                    break Label_30192;
                                                }
                                            }
                                            while (checkpoints.typ[i9] == -1) {
                                                if (++i9 != checkpoints.n) {
                                                    continue;
                                                }
                                                i9 = 0;
                                            }
                                        }
                                    }
                                }
                                Label_30357: {
                                    if (checkpoints.stage != 14) {
                                        if (checkpoints.stage != 9) {
                                            break Label_30357;
                                        }
                                    }
                                    if (xtgraphics.classicmode) {
                                        while (checkpoints.typ[i9] == -1) {
                                            if (++i9 != checkpoints.n) {
                                                continue;
                                            }
                                            i9 = 0;
                                        }
                                        if (!madness.gtouch) {
                                            while (checkpoints.typ[i9] == -2) {
                                                if (++i9 != checkpoints.n) {
                                                    continue;
                                                }
                                                i9 = 0;
                                            }
                                        }
                                        if (this.oupnt < 68) {
                                            this.oupnt = i9;
                                        }
                                        else {
                                            i9 = 70;
                                        }
                                    }
                                }
                                Label_46204: {
                                    if (xtgraphics.careermode) {
                                        if (this.trfix < 2) {
                                            if (checkpoints.stage == 2) {
                                                if (madness.pcleared == 6) {
                                                    i9 = 13;
                                                }
                                                if (madness.pcleared == 13) {
                                                    if (i9 < 13) {
                                                        i9 = 26;
                                                    }
                                                }
                                                if (madness.pcleared == 26) {
                                                    i9 = 29;
                                                }
                                                if (madness.pcleared == 37) {
                                                    i9 = 42;
                                                    this.variable.dontmiss[madness.im] = true;
                                                }
                                                if (madness.pcleared == 44) {
                                                    this.variable.dontmiss[madness.im] = false;
                                                }
                                            }
                                            if (checkpoints.stage == 3) {
                                                if (madness.pcleared == 57) {
                                                    if (i9 > 5) {
                                                        i9 = 5;
                                                    }
                                                }
                                                if (madness.pcleared == 5) {
                                                    i9 = 9;
                                                }
                                                if (madness.pcleared == 9) {
                                                    i9 = 14;
                                                }
                                                if (madness.pcleared == 14) {
                                                    i9 = 21;
                                                }
                                                if (madness.pcleared == 21) {
                                                    if (i9 < 23) {
                                                        i9 = 23;
                                                    }
                                                }
                                                if (madness.pcleared == 34) {
                                                    if (i9 < 34) {
                                                        i9 = 43;
                                                    }
                                                }
                                                if (madness.pcleared == 43) {
                                                    if (i9 >= 48) {
                                                        i9 = 52;
                                                    }
                                                }
                                                if (madness.pcleared == 52) {
                                                    i9 = 57;
                                                }
                                            }
                                            if (checkpoints.stage == 5) {
                                                if (!xtgraphics.bonusstage[0]) {
                                                    Label_30812: {
                                                        if (madness.pcleared == 110) {
                                                            if (madness.cn >= 10) {
                                                                if (madness.cn < 23) {
                                                                    break Label_30812;
                                                                }
                                                                if (madness.cn >= 33) {
                                                                    break Label_30812;
                                                                }
                                                            }
                                                            this.stuntf = 12;
                                                        }
                                                    }
                                                    if (madness.pcleared == 26) {
                                                        if (!this.delayturn) {
                                                            this.hold = 3;
                                                            this.delayturn = true;
                                                        }
                                                        if (i9 <= 30) {
                                                            i9 = 30;
                                                        }
                                                    }
                                                    if (madness.pcleared == 31) {
                                                        this.delayturn = false;
                                                    }
                                                    if (madness.pcleared == 40) {
                                                        if (!this.delayturn) {
                                                            this.hold = 8;
                                                            this.delayturn = true;
                                                        }
                                                        this.clrnce = 2;
                                                        i9 = 47;
                                                    }
                                                    if (madness.pcleared == 47) {
                                                        this.delayturn = false;
                                                    }
                                                    if (madness.pcleared == 56) {
                                                        this.stuntf = 12;
                                                        this.saftey = 0;
                                                    }
                                                    if (madness.pcleared == 61) {
                                                        if (i9 < 61) {
                                                            i9 = 68;
                                                        }
                                                    }
                                                    if (madness.pcleared == 68) {
                                                        i9 = 71;
                                                    }
                                                    Label_31063: {
                                                        if (madness.pcleared == 71) {
                                                            if (i9 >= 71) {
                                                                if (i9 <= 77) {
                                                                    break Label_31063;
                                                                }
                                                            }
                                                            i9 = 77;
                                                        }
                                                    }
                                                    if (madness.pcleared == 86) {
                                                        i9 = 90;
                                                    }
                                                    if (madness.pcleared == 103) {
                                                        i9 = 110;
                                                    }
                                                }
                                            }
                                            if (xtgraphics.bonusstage[0]) {
                                                if (madness.pcleared == 32) {
                                                    i9 = 1;
                                                }
                                            }
                                            if (checkpoints.stage == 6) {
                                                if (madness.pcleared == 3) {
                                                    if (i9 >= 7) {
                                                        i9 = 10;
                                                    }
                                                    else {
                                                        i9 = 7;
                                                    }
                                                }
                                                if (madness.pcleared == 10) {
                                                    if (i9 >= 10) {
                                                        if (i9 >= 14) {
                                                            if (i9 > 16) {
                                                                this.delayturn = false;
                                                            }
                                                            else if (!this.delayturn) {
                                                                this.delayturn = true;
                                                                this.hold = 12;
                                                            }
                                                            if (i9 >= 17) {
                                                                i9 = 18;
                                                            }
                                                        }
                                                        else {
                                                            this.clrnce = 2;
                                                            i9 = 14;
                                                        }
                                                    }
                                                    else {
                                                        i9 = 18;
                                                    }
                                                }
                                                if (madness.pcleared == 18) {
                                                    i9 = 20;
                                                }
                                                if (madness.pcleared == 20) {
                                                    i9 = 25;
                                                }
                                                if (madness.pcleared == 25) {
                                                    if (i9 >= 25) {
                                                        if (i9 >= 32) {
                                                            i9 = 34;
                                                        }
                                                    }
                                                    else {
                                                        i9 = 34;
                                                    }
                                                }
                                                if (madness.pcleared == 34) {
                                                    if (i9 < 34) {
                                                        i9 = 38;
                                                    }
                                                }
                                                if (madness.pcleared == 38) {
                                                    i9 = 42;
                                                }
                                                if (madness.pcleared == 42) {
                                                    if (i9 < 42) {
                                                        i9 = 46;
                                                    }
                                                }
                                            }
                                            if (checkpoints.stage == 7) {
                                                int predrange = 600;
                                                Label_31538: {
                                                    if (!madness.beast[madness.im]) {
                                                        if (madness.power >= 60.0f) {
                                                            if (!madness.specialact) {
                                                                break Label_31538;
                                                            }
                                                        }
                                                    }
                                                    predrange = 500;
                                                }
                                                if (madness.pcleared == 28) {
                                                    int whatto = 6;
                                                    int j7 = madness.pcleared + 1;
                                                    while (checkpoints.typ[j7] <= 0) {
                                                        if (++j7 != checkpoints.n) {
                                                            continue;
                                                        }
                                                        j7 = 0;
                                                    }
                                                    if (this.py(conto.x / 100, checkpoints.x[j7] / 100, conto.z / 100, checkpoints.z[j7] / 100) < predrange) {
                                                        if (conto.x > checkpoints.x[j7]) {
                                                            if (madness.speed > 180.0f) {
                                                                this.clrnce = 2;
                                                                whatto = 8;
                                                            }
                                                        }
                                                    }
                                                    Label_31723: {
                                                        if (i9 < 5) {
                                                            if (i9 <= 28) {
                                                                break Label_31723;
                                                            }
                                                        }
                                                        i9 = whatto;
                                                    }
                                                    Label_31775: {
                                                        if (this.hold != 0) {
                                                            if (madness.cn == 10) {
                                                                break Label_31775;
                                                            }
                                                            if (madness.cn == 33) {
                                                                break Label_31775;
                                                            }
                                                        }
                                                        this.stuntf = 12;
                                                        this.saftey = 8;
                                                    }
                                                    this.clrnce = 2;
                                                }
                                                if (madness.pcleared == 6) {
                                                    i9 = 10;
                                                }
                                                if (madness.pcleared == 10) {
                                                    int whatto = 14;
                                                    int j7 = madness.pcleared + 1;
                                                    while (checkpoints.typ[j7] <= 0) {
                                                        if (++j7 != checkpoints.n) {
                                                            continue;
                                                        }
                                                        j7 = 0;
                                                    }
                                                    if (this.py(conto.x / 100, checkpoints.x[j7] / 100, conto.z / 100, checkpoints.z[j7] / 100) < predrange) {
                                                        if (conto.x > checkpoints.x[j7]) {
                                                            if (madness.speed > 180.0f) {
                                                                this.clrnce = 2;
                                                                whatto = 16;
                                                            }
                                                        }
                                                    }
                                                    i9 = whatto;
                                                }
                                                Label_32146: {
                                                    if (madness.pcleared == 14) {
                                                        int whatto = 20;
                                                        int j7 = madness.pcleared + 1;
                                                        while (checkpoints.typ[j7] <= 0) {
                                                            if (++j7 != checkpoints.n) {
                                                                continue;
                                                            }
                                                            j7 = 0;
                                                        }
                                                        if (this.py(conto.x / 100, checkpoints.x[j7] / 100, conto.z / 100, checkpoints.z[j7] / 100) < predrange) {
                                                            if (conto.x < checkpoints.x[j7]) {
                                                                if (madness.speed > 180.0f) {
                                                                    this.clrnce = 2;
                                                                    whatto = 21;
                                                                }
                                                            }
                                                        }
                                                        if (i9 < 19) {
                                                            if (i9 <= 28) {
                                                                break Label_32146;
                                                            }
                                                        }
                                                        i9 = whatto;
                                                    }
                                                }
                                                if (madness.pcleared == 20) {
                                                    i9 = 24;
                                                }
                                                if (madness.pcleared == 24) {
                                                    int whatto = 28;
                                                    int j7 = madness.pcleared + 1;
                                                    while (checkpoints.typ[j7] <= 0) {
                                                        if (++j7 != checkpoints.n) {
                                                            continue;
                                                        }
                                                        j7 = 0;
                                                    }
                                                    if (this.py(conto.x / 100, checkpoints.x[j7] / 100, conto.z / 100, checkpoints.z[j7] / 100) < predrange) {
                                                        if (conto.x < checkpoints.x[j7]) {
                                                            if (madness.speed > 180.0f) {
                                                                this.clrnce = 1;
                                                                whatto = 2;
                                                            }
                                                        }
                                                    }
                                                    i9 = whatto;
                                                }
                                            }
                                            if (checkpoints.stage == 9) {
                                                int predrange = 500;
                                                Label_32377: {
                                                    if (madness.cn == 12) {
                                                        if (madness.cn == 35) {
                                                            break Label_32377;
                                                        }
                                                    }
                                                    predrange = 250;
                                                }
                                                if (madness.pcleared == 125) {
                                                    int whatto = 12;
                                                    int j7 = madness.pcleared + 1;
                                                    while (checkpoints.typ[j7] <= 0) {
                                                        if (++j7 != checkpoints.n) {
                                                            continue;
                                                        }
                                                        j7 = 0;
                                                    }
                                                    if (this.py(conto.x / 100, checkpoints.x[j7] / 100, conto.z / 100, checkpoints.z[j7] / 100) < predrange) {
                                                        if (madness.speed > 180.0f) {
                                                            if (checkpoints.opz[madness.im] < checkpoints.z[j7]) {
                                                                this.clrnce = 1;
                                                                whatto = 14;
                                                            }
                                                        }
                                                    }
                                                    i9 = whatto;
                                                }
                                                if (madness.pcleared == 20) {
                                                    int point = i9;
                                                    if (point < 22) {
                                                        point = 22;
                                                    }
                                                    int whatto2 = point;
                                                    if (i9 >= 27) {
                                                        whatto2 = 30;
                                                    }
                                                    int j8 = madness.pcleared + 1;
                                                    while (checkpoints.typ[j8] <= 0) {
                                                        if (++j8 != checkpoints.n) {
                                                            continue;
                                                        }
                                                        j8 = 0;
                                                    }
                                                    if (this.py(conto.x / 100, checkpoints.x[j8] / 100, conto.z / 100, checkpoints.z[j8] / 100) < predrange) {
                                                        if (madness.speed > 180.0f) {
                                                            if (checkpoints.opz[madness.im] > checkpoints.z[j8]) {
                                                                this.clrnce = 1;
                                                                whatto2 = 32;
                                                            }
                                                        }
                                                    }
                                                    i9 = whatto2;
                                                }
                                                if (madness.pcleared == 30) {
                                                    this.variable.dontmiss[madness.im] = true;
                                                }
                                                if (madness.pcleared == 37) {
                                                    this.variable.dontmiss[madness.im] = false;
                                                }
                                                if (madness.pcleared == 42) {
                                                    int whatto = 45;
                                                    int j7 = madness.pcleared + 1;
                                                    while (checkpoints.typ[j7] <= 0) {
                                                        if (++j7 != checkpoints.n) {
                                                            continue;
                                                        }
                                                        j7 = 0;
                                                    }
                                                    if (this.py(conto.x / 100, checkpoints.x[j7] / 100, conto.z / 100, checkpoints.z[j7] / 100) < predrange) {
                                                        if (madness.speed > 180.0f) {
                                                            if (checkpoints.opx[madness.im] < checkpoints.x[j7]) {
                                                                this.clrnce = 1;
                                                                whatto = 47;
                                                            }
                                                        }
                                                    }
                                                    i9 = whatto;
                                                }
                                                if (madness.pcleared == 45) {
                                                    if (!this.delayturn) {
                                                        this.hold = 4;
                                                        this.delayturn = true;
                                                    }
                                                    int whatto = 50;
                                                    int j7 = madness.pcleared + 1;
                                                    while (checkpoints.typ[j7] <= 0) {
                                                        if (++j7 != checkpoints.n) {
                                                            continue;
                                                        }
                                                        j7 = 0;
                                                    }
                                                    if (this.py(conto.x / 100, checkpoints.x[j7] / 100, conto.z / 100, checkpoints.z[j7] / 100) < predrange) {
                                                        if (madness.speed > 180.0f) {
                                                            if (checkpoints.opx[madness.im] > checkpoints.x[j7]) {
                                                                this.clrnce = 1;
                                                                whatto = 51;
                                                            }
                                                        }
                                                    }
                                                    i9 = whatto;
                                                }
                                                if (madness.pcleared == 50) {
                                                    this.delayturn = false;
                                                    if (i9 < 50) {
                                                        i9 = 64;
                                                    }
                                                    if (i9 >= 55) {
                                                        if (i9 <= 60) {
                                                            i9 = 60;
                                                        }
                                                    }
                                                    Label_33276: {
                                                        if (i9 > 60) {
                                                            if (madness.specialact) {
                                                                if (madness.cn != 12) {
                                                                    if (madness.cn != 35) {
                                                                        break Label_33276;
                                                                    }
                                                                }
                                                                i9 = 64;
                                                            }
                                                        }
                                                    }
                                                    if (i9 == 63) {
                                                        if (checkpoints.opz[madness.im] < 75000) {
                                                            i9 = 62;
                                                        }
                                                    }
                                                }
                                                if (madness.pcleared == 64) {
                                                    if (i9 == 68) {
                                                        i9 = 69;
                                                    }
                                                }
                                                Label_33457: {
                                                    if (madness.pcleared == 69) {
                                                        Label_33424: {
                                                            if (madness.specialact) {
                                                                if (madness.cn != 12) {
                                                                    if (madness.cn != 35) {
                                                                        break Label_33424;
                                                                    }
                                                                }
                                                                if (!this.delayturn) {
                                                                    this.hold = 10;
                                                                    this.delayturn = true;
                                                                }
                                                                break Label_33457;
                                                            }
                                                        }
                                                        if (checkpoints.opz[madness.im] <= 50000) {
                                                            i9 = 77;
                                                        }
                                                        else {
                                                            i9 = 73;
                                                        }
                                                    }
                                                }
                                                Label_33536: {
                                                    Label_33513: {
                                                        if (madness.pcleared >= 77) {
                                                            if (madness.pcleared <= 80) {
                                                                break Label_33513;
                                                            }
                                                        }
                                                        if (madness.pcleared < 101) {
                                                            break Label_33536;
                                                        }
                                                        if (madness.pcleared > 103) {
                                                            break Label_33536;
                                                        }
                                                    }
                                                    this.clrnce = 1;
                                                    this.variable.dontmiss[madness.im] = true;
                                                    this.delayturn = false;
                                                }
                                                if (madness.pcleared == 82) {
                                                    this.variable.dontmiss[madness.im] = false;
                                                    i9 = 84;
                                                }
                                                if (madness.pcleared == 84) {
                                                    if (i9 >= 90) {
                                                        if (i9 < 99) {
                                                            if (madness.power == 98.0f) {
                                                                i9 = 99;
                                                            }
                                                        }
                                                    }
                                                    if (madness.power < 98.0f) {
                                                        if (i9 == 99) {
                                                            i9 = 101;
                                                        }
                                                    }
                                                    this.variable.dontmiss[madness.im] = true;
                                                }
                                                if (madness.pcleared == 105) {
                                                    this.variable.dontmiss[madness.im] = false;
                                                }
                                                if (madness.pcleared == 113) {
                                                    if (i9 < 113) {
                                                        i9 = 125;
                                                    }
                                                }
                                            }
                                            if (xtgraphics.bonusstage[1]) {
                                                if (madness.pcleared == 28) {
                                                    i9 = 3;
                                                }
                                                if (madness.pcleared == 3) {
                                                    i9 = 12;
                                                }
                                                if (madness.cn == 35) {
                                                    if (!madness.specialact) {
                                                        this.variable.dontstunt[madness.im] = false;
                                                    }
                                                    else {
                                                        if (madness.pcleared == 12) {
                                                            i9 = 20;
                                                        }
                                                        if (madness.pcleared == 20) {
                                                            i9 = 22;
                                                        }
                                                        if (madness.pcleared == 22) {
                                                            i9 = 28;
                                                        }
                                                        this.clrnce = 9;
                                                        this.variable.dontstunt[madness.im] = true;
                                                    }
                                                }
                                            }
                                            if (checkpoints.stage == 8) {
                                                if (madness.pcleared == 74) {
                                                    this.delayturn = false;
                                                }
                                                if (madness.pcleared == 6) {
                                                    i9 = 8;
                                                }
                                                if (madness.pcleared == 8) {
                                                    if (!this.delayturn) {
                                                        this.hold = 8;
                                                        this.delayturn = true;
                                                    }
                                                    if (i9 >= 14) {
                                                        i9 = 17;
                                                    }
                                                }
                                                if (madness.pcleared == 17) {
                                                    this.delayturn = false;
                                                    if (i9 >= 22) {
                                                        i9 = 26;
                                                    }
                                                    else {
                                                        i9 = 22;
                                                    }
                                                }
                                                if (madness.pcleared == 26) {
                                                    i9 = 29;
                                                }
                                                if (madness.pcleared == 29) {
                                                    i9 = 31;
                                                }
                                                if (madness.pcleared == 31) {
                                                    i9 = 33;
                                                }
                                                if (madness.pcleared == 33) {
                                                    if (i9 < 35) {
                                                        i9 = 35;
                                                    }
                                                    if (i9 >= 41) {
                                                        i9 = 44;
                                                    }
                                                }
                                                if (madness.pcleared == 44) {
                                                    i9 = 51;
                                                }
                                                if (madness.pcleared == 51) {
                                                    int whatto3 = 53;
                                                    int j9 = madness.pcleared + 1;
                                                    while (checkpoints.typ[j9] <= 0) {
                                                        if (++j9 != checkpoints.n) {
                                                            continue;
                                                        }
                                                        j9 = 0;
                                                    }
                                                    if (this.py(conto.x / 100, checkpoints.x[j9] / 100, conto.z / 100, checkpoints.z[j9] / 100) < 400) {
                                                        if (this.variable.chkcircle[madness.im] <= 20) {
                                                            if (madness.speed > 180.0f) {
                                                                this.clrnce = 2;
                                                                whatto3 = 54;
                                                            }
                                                        }
                                                    }
                                                    i9 = whatto3;
                                                }
                                                if (madness.pcleared == 53) {
                                                    this.variable.sharpturn[madness.im] = 1;
                                                    int whatto3 = 57;
                                                    int j9 = madness.pcleared + 1;
                                                    while (checkpoints.typ[j9] <= 0) {
                                                        if (++j9 != checkpoints.n) {
                                                            continue;
                                                        }
                                                        j9 = 0;
                                                    }
                                                    if (this.py(conto.x / 100, checkpoints.x[j9] / 100, conto.z / 100, checkpoints.z[j9] / 100) < 500) {
                                                        if (this.variable.chkcircle[madness.im] <= 20) {
                                                            if (madness.speed > 180.0f) {
                                                                this.clrnce = 2;
                                                                whatto3 = 59;
                                                            }
                                                        }
                                                    }
                                                    i9 = whatto3;
                                                }
                                                if (madness.pcleared == 57) {
                                                    Label_34558: {
                                                        if (i9 < 61) {
                                                            if (i9 >= madness.pcleared) {
                                                                break Label_34558;
                                                            }
                                                        }
                                                        if (i9 < 63) {
                                                            i9 = 63;
                                                        }
                                                    }
                                                    if (i9 >= 66) {
                                                        i9 = 68;
                                                    }
                                                }
                                                if (madness.pcleared == 68) {
                                                    if (i9 < 71) {
                                                        i9 = 71;
                                                    }
                                                    if (i9 >= 72) {
                                                        i9 = 74;
                                                    }
                                                }
                                            }
                                            Label_34991: {
                                                if (checkpoints.stage == 12) {
                                                    if (madness.pcleared == 1) {
                                                        i9 = 2;
                                                    }
                                                    if (madness.pcleared == 2) {
                                                        i9 = 3;
                                                    }
                                                    if (madness.pcleared == 11) {
                                                        int whatto3 = 15;
                                                        int j9 = madness.pcleared + 1;
                                                        while (checkpoints.typ[j9] <= 0) {
                                                            if (++j9 != checkpoints.n) {
                                                                continue;
                                                            }
                                                            j9 = 0;
                                                        }
                                                        if (this.py(conto.x / 100, checkpoints.x[j9] / 100, conto.z / 100, checkpoints.z[j9] / 100) < 600) {
                                                            if (this.variable.chkcircle[madness.im] <= 20) {
                                                                if (madness.speed > 180.0f) {
                                                                    this.clrnce = 2;
                                                                    whatto3 = 18;
                                                                }
                                                            }
                                                        }
                                                        i9 = whatto3;
                                                    }
                                                    if (madness.pcleared == 24) {
                                                        i9 = 29;
                                                    }
                                                    if (madness.pcleared == 29) {
                                                        i9 = 33;
                                                    }
                                                    if (madness.pcleared == 33) {
                                                        i9 = 36;
                                                    }
                                                    if (madness.pcleared == 50) {
                                                        i9 = 1;
                                                    }
                                                    if (madness.specialact) {
                                                        if (madness.power > 90.0f) {
                                                            if (madness.pcleared == 15) {
                                                                i9 = 24;
                                                            }
                                                        }
                                                    }
                                                    if (madness.pcleared != 42) {
                                                        if (madness.pcleared != 15) {
                                                            this.clrnce = 4;
                                                            break Label_34991;
                                                        }
                                                    }
                                                    this.clrnce = 1;
                                                }
                                            }
                                            Label_36429: {
                                                if (checkpoints.stage == 13) {
                                                    this.variable.dontstunt[madness.im] = false;
                                                    this.variable.slowrange[madness.im] = 0;
                                                    this.turntyp = 0;
                                                    Label_35082: {
                                                        if (madness.specialact) {
                                                            if (madness.cn != 14) {
                                                                if (madness.cn != 37) {
                                                                    break Label_35082;
                                                                }
                                                            }
                                                            this.stuntf = 12;
                                                        }
                                                    }
                                                    if (madness.beast[madness.im]) {
                                                        this.saftey = 0;
                                                    }
                                                    int j10 = madness.pcleared + 1;
                                                    while (checkpoints.typ[j10] <= 0) {
                                                        if (++j10 != checkpoints.n) {
                                                            continue;
                                                        }
                                                        j10 = 0;
                                                    }
                                                    Label_35195: {
                                                        if (i9 >= madness.pcleared) {
                                                            if (checkpoints.floor[j10] == xtgraphics.floor[madness.im]) {
                                                                break Label_35195;
                                                            }
                                                        }
                                                        i9 = j10;
                                                    }
                                                    final int[] pieceorder = { 319, 5, 10, 11, 13, 16, 21, 27, 35, 39, 44 };
                                                    for (int a3 = 0; a3 < 10; ++a3) {
                                                        if (madness.pcleared == pieceorder[a3]) {
                                                            this.saftey = 5;
                                                            this.stuntf = 12;
                                                            if (a3 < 7) {
                                                                this.turntyp = 1;
                                                            }
                                                            if (a3 == 1) {
                                                                this.turntyp = 2;
                                                            }
                                                            if (a3 == 7) {
                                                                this.stuntf = 11;
                                                            }
                                                            int whatto4 = pieceorder[a3 + 1];
                                                            if (this.py(conto.x / 100, checkpoints.x[j10] / 100, conto.z / 100, checkpoints.z[j10] / 100) < 700) {
                                                                if (this.variable.chkcircle[madness.im] <= 20) {
                                                                    if (madness.speed > 250.0f) {
                                                                        this.clrnce = 2;
                                                                        whatto4 = pieceorder[a3 + 1] + 2;
                                                                    }
                                                                }
                                                            }
                                                            if (a3 != 7) {
                                                                i9 = whatto4;
                                                            }
                                                        }
                                                    }
                                                    if (madness.pcleared == 27) {
                                                        this.clrnce = 5;
                                                        if (i9 < 31) {
                                                            i9 = 31;
                                                        }
                                                        if (i9 >= 33) {
                                                            i9 = 35;
                                                        }
                                                    }
                                                    if (madness.pcleared == 44) {
                                                        if (i9 >= 52) {
                                                            this.variable.dontstunt[madness.im] = true;
                                                        }
                                                    }
                                                    Label_35705: {
                                                        if (madness.pcleared != 54) {
                                                            if (madness.pcleared != 58) {
                                                                if (madness.pcleared != 62) {
                                                                    if (madness.pcleared != 76) {
                                                                        if (madness.pcleared != 126) {
                                                                            if (madness.pcleared != 132) {
                                                                                if (madness.pcleared != 206) {
                                                                                    if (madness.pcleared != 226) {
                                                                                        break Label_35705;
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        this.clrnce = 2;
                                                        i9 = j10;
                                                    }
                                                    if (madness.pcleared == 79) {
                                                        this.clrnce = 5;
                                                    }
                                                    if (madness.pcleared == 88) {
                                                        if (i9 >= 93) {
                                                            i9 = 97;
                                                        }
                                                    }
                                                    final int[] twopiece = { 97, 103, 106, 110, 113, 114, 115, 117 };
                                                    for (int a4 = 0; a4 < 7; ++a4) {
                                                        if (madness.pcleared == twopiece[a4]) {
                                                            this.variable.slowrange[madness.im] = 2500;
                                                            this.saftey = 5;
                                                            this.stuntf = 10;
                                                            int whatto5 = twopiece[a4 + 1];
                                                            if (this.py(conto.x / 100, checkpoints.x[j10] / 100, conto.z / 100, checkpoints.z[j10] / 100) < 700) {
                                                                if (this.variable.chkcircle[madness.im] <= 20) {
                                                                    if (madness.speed > 250.0f) {
                                                                        this.clrnce = 2;
                                                                        whatto5 = twopiece[a4 + 1] + 1;
                                                                    }
                                                                }
                                                            }
                                                            i9 = whatto5;
                                                        }
                                                    }
                                                    if (madness.pcleared == 117) {
                                                        if (i9 >= 122) {
                                                            i9 = 126;
                                                        }
                                                    }
                                                    final int[] threepiece = { 155, 159, 166, 178, 180, 182, 186 };
                                                    for (int a7 = 0; a7 < 6; ++a7) {
                                                        if (madness.pcleared == threepiece[a7]) {
                                                            this.variable.slowrange[madness.im] = 2500;
                                                            this.saftey = 5;
                                                            if (a7 == 2) {
                                                                this.stuntf = 4;
                                                            }
                                                            int whatto6 = threepiece[a7 + 1];
                                                            if (this.py(conto.x / 100, checkpoints.x[j10] / 100, conto.z / 100, checkpoints.z[j10] / 100) < 700) {
                                                                if (this.variable.chkcircle[madness.im] <= 20) {
                                                                    if (madness.speed > 250.0f) {
                                                                        this.clrnce = 2;
                                                                        whatto6 = threepiece[a7 + 1] + 1;
                                                                    }
                                                                }
                                                            }
                                                            i9 = whatto6;
                                                        }
                                                    }
                                                    if (madness.pcleared == 210) {
                                                        this.stuntf = 11;
                                                    }
                                                    Label_36360: {
                                                        if (madness.pcleared != 199) {
                                                            if (madness.pcleared != 259) {
                                                                if (madness.pcleared != 272) {
                                                                    if (madness.pcleared != 285) {
                                                                        if (madness.pcleared != 316) {
                                                                            break Label_36360;
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        this.clrnce = 5;
                                                        i9 = j10;
                                                    }
                                                    if (madness.pcleared != 292) {
                                                        if (madness.pcleared != 298) {
                                                            if (madness.pcleared != 305) {
                                                                if (madness.pcleared != 311) {
                                                                    break Label_36429;
                                                                }
                                                            }
                                                        }
                                                    }
                                                    this.turntyp = 1;
                                                    i9 = j10;
                                                }
                                            }
                                            Label_36805: {
                                                if (checkpoints.stage == 10) {
                                                    Label_36622: {
                                                        if (madness.cn != 12) {
                                                            if (madness.cn != 35) {
                                                                break Label_36622;
                                                            }
                                                        }
                                                        if (xtgraphics.fixspecials[madness.im]) {
                                                            if (madness.pcleared == 131) {
                                                                i9 = 29;
                                                            }
                                                            if (madness.pcleared == 29) {
                                                                i9 = 34;
                                                            }
                                                            if (madness.pcleared == 34) {
                                                                i9 = 58;
                                                            }
                                                            if (madness.pcleared == 58) {
                                                                i9 = 61;
                                                            }
                                                            if (madness.pcleared == 61) {
                                                                i9 = 87;
                                                            }
                                                            if (madness.pcleared == 87) {
                                                                i9 = 93;
                                                            }
                                                            if (madness.pcleared == 93) {
                                                                i9 = 131;
                                                            }
                                                            break Label_36805;
                                                        }
                                                    }
                                                    if (madness.pcleared == 61) {
                                                        if (this.oupnt < 85) {
                                                            this.oupnt = i9;
                                                        }
                                                        else {
                                                            i9 = 87;
                                                        }
                                                    }
                                                    if (madness.pcleared == 87) {
                                                        if (this.oupnt < 90) {
                                                            this.oupnt = i9;
                                                        }
                                                        else {
                                                            i9 = 93;
                                                        }
                                                    }
                                                    if (madness.pcleared == 93) {
                                                        if (this.oupnt < 127) {
                                                            this.oupnt = i9;
                                                        }
                                                        else {
                                                            i9 = 131;
                                                        }
                                                        if (i9 < 93) {
                                                            i9 = 131;
                                                        }
                                                    }
                                                    if (madness.pcleared == 131) {
                                                        this.oupnt = 0;
                                                        if (i9 > 29) {
                                                            i9 = 29;
                                                        }
                                                    }
                                                }
                                            }
                                            if (checkpoints.stage == 11) {
                                                if (!xtgraphics.bonusstage[1]) {
                                                    if (madness.pcleared == 38) {
                                                        if (i9 <= 4) {
                                                            i9 = 4;
                                                        }
                                                        if (i9 >= 7) {
                                                            i9 = 9;
                                                        }
                                                    }
                                                    if (madness.pcleared == 9) {
                                                        int whatto3 = 13;
                                                        int j9 = madness.pcleared + 1;
                                                        while (checkpoints.typ[j9] <= 0) {
                                                            if (++j9 != checkpoints.n) {
                                                                continue;
                                                            }
                                                            j9 = 0;
                                                        }
                                                        if (this.py(conto.x / 100, checkpoints.x[j9] / 100, conto.z / 100, checkpoints.z[j9] / 100) < 500) {
                                                            if (this.variable.chkcircle[madness.im] <= 20) {
                                                                if (madness.speed > 250.0f) {
                                                                    this.clrnce = 3;
                                                                    whatto3 = 14;
                                                                }
                                                            }
                                                        }
                                                        i9 = whatto3;
                                                        this.dontback = true;
                                                    }
                                                    if (madness.pcleared == 13) {
                                                        int whatto3 = 18;
                                                        int j9 = madness.pcleared + 1;
                                                        while (checkpoints.typ[j9] <= 0) {
                                                            if (++j9 != checkpoints.n) {
                                                                continue;
                                                            }
                                                            j9 = 0;
                                                        }
                                                        if (this.py(conto.x / 100, checkpoints.x[j9] / 100, conto.z / 100, checkpoints.z[j9] / 100) < 500) {
                                                            if (this.variable.chkcircle[madness.im] <= 20) {
                                                                if (madness.speed > 250.0f) {
                                                                    this.clrnce = 3;
                                                                    whatto3 = 20;
                                                                }
                                                            }
                                                        }
                                                        i9 = whatto3;
                                                        this.dontback = false;
                                                    }
                                                    Label_37290: {
                                                        if (madness.pcleared == 18) {
                                                            if (i9 >= 18) {
                                                                if (!madness.specialact) {
                                                                    break Label_37290;
                                                                }
                                                                if (madness.cn != 12) {
                                                                    if (madness.cn != 35) {
                                                                        break Label_37290;
                                                                    }
                                                                }
                                                            }
                                                            i9 = 25;
                                                        }
                                                    }
                                                    if (madness.pcleared == 25) {
                                                        i9 = 32;
                                                    }
                                                    if (madness.pcleared == 32) {
                                                        i9 = 36;
                                                    }
                                                }
                                            }
                                            Label_38174: {
                                                if (checkpoints.stage == 14) {
                                                    while (checkpoints.typ[i9] == -1) {
                                                        if (++i9 != checkpoints.n) {
                                                            continue;
                                                        }
                                                        i9 = 0;
                                                    }
                                                    this.oupnt = i9;
                                                    if (madness.pcleared == 66) {
                                                        if (i9 < 71) {
                                                            i9 = 71;
                                                        }
                                                        if (i9 >= 76) {
                                                            i9 = 83;
                                                        }
                                                    }
                                                    if (madness.pcleared == 115) {
                                                        if (i9 >= 125) {
                                                            i9 = 130;
                                                        }
                                                    }
                                                    if (madness.pcleared == 166) {
                                                        i9 = 173;
                                                    }
                                                    if (madness.pcleared == 173) {
                                                        if (i9 >= 189) {
                                                            if (i9 < 195) {
                                                                i9 = 195;
                                                            }
                                                        }
                                                    }
                                                    if (madness.pcleared == 208) {
                                                        i9 = 211;
                                                    }
                                                    if (madness.pcleared == 211) {
                                                        i9 = 221;
                                                    }
                                                    Label_37697: {
                                                        if (madness.pcleared == 221) {
                                                            if (i9 < 230) {
                                                                this.clrnce = 9;
                                                                i9 = 230;
                                                            }
                                                            if (i9 >= 234) {
                                                                if (i9 < 240) {
                                                                    i9 = 240;
                                                                }
                                                            }
                                                            if (i9 >= 243) {
                                                                if (i9 < 248) {
                                                                    this.variable.dontstunt[madness.im] = true;
                                                                    break Label_37697;
                                                                }
                                                            }
                                                            this.variable.dontstunt[madness.im] = false;
                                                        }
                                                    }
                                                    if (madness.pcleared == 278) {
                                                        i9 = 289;
                                                    }
                                                    if (madness.pcleared == 301) {
                                                        i9 = 307;
                                                    }
                                                    if (madness.specialact) {
                                                        if (madness.cn != 1) {
                                                            if (madness.cn != 12) {
                                                                if (madness.cn != 14) {
                                                                    if (madness.cn != 24) {
                                                                        if (madness.cn != 35) {
                                                                            if (madness.cn != 37) {
                                                                                break Label_38174;
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        if (madness.pcleared == 66) {
                                                            i9 = 83;
                                                        }
                                                        if (madness.pcleared == 83) {
                                                            if (i9 < 90) {
                                                                i9 = 90;
                                                            }
                                                        }
                                                        if (madness.pcleared == 115) {
                                                            if (i9 < 126) {
                                                                i9 = 126;
                                                            }
                                                        }
                                                        if (madness.pcleared == 289) {
                                                            if (i9 >= 297) {
                                                                this.variable.dontstunt[madness.im] = false;
                                                            }
                                                            else {
                                                                this.variable.dontstunt[madness.im] = true;
                                                                i9 = 297;
                                                            }
                                                        }
                                                        Label_38064: {
                                                            if (madness.pcleared == 130) {
                                                                if (i9 < 140) {
                                                                    i9 = 140;
                                                                }
                                                                if (i9 >= 158) {
                                                                    if (i9 < 165) {
                                                                        this.variable.dontstunt[madness.im] = true;
                                                                        break Label_38064;
                                                                    }
                                                                }
                                                                this.variable.dontstunt[madness.im] = false;
                                                            }
                                                        }
                                                        Label_38154: {
                                                            if (madness.pcleared == 173) {
                                                                if (i9 < 182) {
                                                                    i9 = 182;
                                                                }
                                                                if (i9 >= 198) {
                                                                    if (i9 < 207) {
                                                                        this.variable.dontstunt[madness.im] = true;
                                                                        break Label_38154;
                                                                    }
                                                                }
                                                                this.variable.dontstunt[madness.im] = false;
                                                            }
                                                        }
                                                        if (madness.pcleared == 248) {
                                                            i9 = 261;
                                                        }
                                                    }
                                                }
                                            }
                                            if (checkpoints.stage == 15) {
                                                this.oupnt = i9;
                                                if (madness.pcleared == 20) {
                                                    if (i9 < 22) {
                                                        i9 = 22;
                                                    }
                                                    if (i9 >= 26) {
                                                        if (i9 < 30) {
                                                            i9 = 30;
                                                        }
                                                    }
                                                }
                                                if (madness.pcleared == 30) {
                                                    i9 = 33;
                                                }
                                                if (madness.pcleared == 33) {
                                                    i9 = 40;
                                                }
                                                if (madness.pcleared == 40) {
                                                    if (i9 < 41) {
                                                        i9 = 41;
                                                    }
                                                }
                                                if (madness.pcleared == 62) {
                                                    if (i9 >= 62) {
                                                        if (i9 <= 69) {
                                                            if (madness.power >= 70.0f) {
                                                                this.stuntf = 4;
                                                            }
                                                            else {
                                                                this.stuntf = 10;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            if (checkpoints.stage == 16) {
                                                boolean spatt = false;
                                                Label_38476: {
                                                    if (madness.cn != 12) {
                                                        if (madness.cn != 15) {
                                                            if (madness.cn != 35) {
                                                                if (madness.cn != 38) {
                                                                    break Label_38476;
                                                                }
                                                            }
                                                        }
                                                    }
                                                    if (madness.specialact) {
                                                        spatt = true;
                                                    }
                                                }
                                                Label_38679: {
                                                    if (madness.pcleared == 65) {
                                                        if (i9 < 4) {
                                                            if (!spatt) {
                                                                break Label_38679;
                                                            }
                                                        }
                                                        int whatto = 8;
                                                        int j7 = madness.pcleared + 1;
                                                        while (checkpoints.typ[j7] <= 0) {
                                                            if (++j7 != checkpoints.n) {
                                                                continue;
                                                            }
                                                            j7 = 0;
                                                        }
                                                        if (spatt) {
                                                            this.clrnce = 11;
                                                        }
                                                        if (this.py(conto.x / 100, checkpoints.x[j7] / 100, conto.z / 100, checkpoints.z[j7] / 100) < 600) {
                                                            if (this.variable.chkcircle[madness.im] <= 20) {
                                                                if (madness.speed > 180.0f) {
                                                                    this.clrnce = 0;
                                                                    whatto = 9;
                                                                }
                                                            }
                                                        }
                                                        i9 = whatto;
                                                    }
                                                }
                                                if (madness.pcleared == 8) {
                                                    this.saftey = 20;
                                                    this.stuntf = 12;
                                                    if (i9 < 9) {
                                                        this.clrnce = 8;
                                                        i9 = 9;
                                                    }
                                                    if (i9 > 13) {
                                                        if (i9 < 16) {
                                                            i9 = 16;
                                                        }
                                                    }
                                                    if (i9 >= 16) {
                                                        int whatto = 17;
                                                        int j7 = madness.pcleared + 1;
                                                        while (checkpoints.typ[j7] <= 0) {
                                                            if (++j7 != checkpoints.n) {
                                                                continue;
                                                            }
                                                            j7 = 0;
                                                        }
                                                        if (this.py(conto.x / 100, checkpoints.x[j7] / 100, conto.z / 100, checkpoints.z[j7] / 100) < 600) {
                                                            if (this.variable.chkcircle[madness.im] <= 20) {
                                                                if (madness.speed > 180.0f) {
                                                                    this.clrnce = 0;
                                                                    whatto = 19;
                                                                }
                                                            }
                                                        }
                                                        i9 = whatto;
                                                    }
                                                }
                                                Label_39002: {
                                                    if (madness.pcleared == 17) {
                                                        this.saftey = 8;
                                                        if (i9 < 22) {
                                                            this.clrnce = 2;
                                                            i9 = 22;
                                                        }
                                                        Label_38998: {
                                                            if (i9 > 25) {
                                                                if (i9 < 30) {
                                                                    break Label_38998;
                                                                }
                                                            }
                                                            if (i9 >= madness.pcleared) {
                                                                break Label_39002;
                                                            }
                                                        }
                                                        i9 = 30;
                                                    }
                                                }
                                                if (madness.pcleared == 31) {
                                                    if (i9 < 36) {
                                                        this.clrnce = 5;
                                                        i9 = 36;
                                                    }
                                                    if (i9 >= 36) {
                                                        if (i9 < 38) {
                                                            i9 = 38;
                                                        }
                                                    }
                                                    Label_39107: {
                                                        Label_39103: {
                                                            if (i9 >= 38) {
                                                                if (i9 < 42) {
                                                                    break Label_39103;
                                                                }
                                                            }
                                                            if (i9 >= madness.pcleared) {
                                                                break Label_39107;
                                                            }
                                                        }
                                                        i9 = 42;
                                                    }
                                                    if (i9 >= 42) {
                                                        int whatto = 43;
                                                        int j7 = madness.pcleared + 1;
                                                        while (checkpoints.typ[j7] <= 0) {
                                                            if (++j7 != checkpoints.n) {
                                                                continue;
                                                            }
                                                            j7 = 0;
                                                        }
                                                        if (this.py(conto.x / 100, checkpoints.x[j7] / 100, conto.z / 100, checkpoints.z[j7] / 100) < 600) {
                                                            if (this.variable.chkcircle[madness.im] <= 20) {
                                                                if (madness.speed > 180.0f) {
                                                                    this.clrnce = 0;
                                                                    whatto = 45;
                                                                }
                                                            }
                                                        }
                                                        i9 = whatto;
                                                    }
                                                }
                                                if (madness.pcleared == 43) {
                                                    if (spatt) {
                                                        i9 = 54;
                                                    }
                                                    else {
                                                        if (i9 < 49) {
                                                            i9 = 49;
                                                        }
                                                        if (i9 >= 49) {
                                                            if (i9 < 53) {
                                                                i9 = 53;
                                                            }
                                                        }
                                                        Label_39381: {
                                                            if (i9 < 53) {
                                                                if (i9 >= madness.pcleared) {
                                                                    if (i9 <= 54) {
                                                                        break Label_39381;
                                                                    }
                                                                }
                                                            }
                                                            i9 = 54;
                                                        }
                                                    }
                                                }
                                                if (madness.pcleared == 54) {
                                                    i9 = 58;
                                                }
                                                if (madness.pcleared == 58) {
                                                    this.clrnce = 9;
                                                    i9 = 65;
                                                }
                                            }
                                            Label_40789: {
                                                if (checkpoints.stage == 18) {
                                                    boolean correctcar = false;
                                                    Label_39506: {
                                                        if (madness.cn != 16) {
                                                            if (madness.cn != 12) {
                                                                if (madness.cn != 35) {
                                                                    if (!madness.shadowcar) {
                                                                        break Label_39506;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        correctcar = true;
                                                    }
                                                    if (correctcar) {
                                                        if (madness.pcleared == 10) {
                                                            int whatto = 16;
                                                            int j7 = madness.pcleared + 1;
                                                            while (checkpoints.typ[j7] <= 0) {
                                                                if (++j7 != checkpoints.n) {
                                                                    continue;
                                                                }
                                                                j7 = 0;
                                                            }
                                                            if (this.py(conto.x / 100, checkpoints.x[j7] / 100, conto.z / 100, checkpoints.z[j7] / 100) < 700) {
                                                                if (this.variable.chkcircle[madness.im] <= 20) {
                                                                    if (madness.speed > 250.0f) {
                                                                        this.clrnce = 2;
                                                                        whatto = 24;
                                                                    }
                                                                }
                                                            }
                                                            i9 = whatto;
                                                        }
                                                        if (madness.pcleared == 16) {
                                                            i9 = 24;
                                                        }
                                                        if (madness.pcleared == 24) {
                                                            this.clrnce = 8;
                                                            int whatto = 33;
                                                            int j7 = madness.pcleared + 1;
                                                            while (checkpoints.typ[j7] <= 0) {
                                                                if (++j7 != checkpoints.n) {
                                                                    continue;
                                                                }
                                                                j7 = 0;
                                                            }
                                                            if (this.py(conto.x / 100, checkpoints.x[j7] / 100, conto.z / 100, checkpoints.z[j7] / 100) < 700) {
                                                                if (this.variable.chkcircle[madness.im] <= 20) {
                                                                    if (madness.speed > 250.0f) {
                                                                        this.clrnce = 2;
                                                                        whatto = 35;
                                                                    }
                                                                }
                                                            }
                                                            i9 = whatto;
                                                        }
                                                    }
                                                    if (madness.pcleared == 162) {
                                                        i9 = 166;
                                                    }
                                                    if (madness.pcleared == 175) {
                                                        if (i9 >= 183) {
                                                            if (i9 < 188) {
                                                                i9 = 188;
                                                            }
                                                        }
                                                    }
                                                    Label_39980: {
                                                        if (madness.pcleared == 33) {
                                                            if (i9 != 33) {
                                                                if (i9 != 34) {
                                                                    break Label_39980;
                                                                }
                                                            }
                                                            i9 = 35;
                                                        }
                                                    }
                                                    if (madness.pcleared == 42) {
                                                        if (i9 < madness.pcleared) {
                                                            i9 = 51;
                                                        }
                                                        if (correctcar) {
                                                            this.clrnce = 8;
                                                            if (i9 >= 48) {
                                                                i9 = 51;
                                                            }
                                                            else {
                                                                i9 = 48;
                                                            }
                                                        }
                                                    }
                                                    Label_40095: {
                                                        if (madness.pcleared == 69) {
                                                            if (i9 != 73) {
                                                                if (i9 != 74) {
                                                                    break Label_40095;
                                                                }
                                                            }
                                                            i9 = 75;
                                                        }
                                                    }
                                                    if (madness.pcleared == 75) {
                                                        i9 = 84;
                                                    }
                                                    if (madness.pcleared == 84) {
                                                        i9 = 91;
                                                    }
                                                    if (madness.pcleared == 91) {
                                                        if (i9 < madness.pcleared) {
                                                            i9 = 107;
                                                        }
                                                        if (i9 > 103) {
                                                            if (i9 < 107) {
                                                                i9 = 107;
                                                            }
                                                        }
                                                    }
                                                    if (madness.pcleared == 134) {
                                                        i9 = 142;
                                                    }
                                                    if (madness.pcleared == 142) {
                                                        if (i9 < madness.pcleared) {
                                                            i9 = 149;
                                                        }
                                                    }
                                                    if (madness.pcleared == 149) {
                                                        i9 = 156;
                                                    }
                                                    if (madness.pcleared == 172) {
                                                        i9 = 175;
                                                    }
                                                    if (madness.pcleared == 188) {
                                                        if (i9 > 197) {
                                                            if (i9 < 203) {
                                                                i9 = 203;
                                                            }
                                                        }
                                                    }
                                                    if (madness.specialact) {
                                                        if (madness.cn != 16) {
                                                            if (madness.cn != 12) {
                                                                if (madness.cn != 9) {
                                                                    if (madness.cn != 17) {
                                                                        if (madness.cn != 32) {
                                                                            if (madness.cn != 35) {
                                                                                break Label_40789;
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        if (madness.pcleared == 210) {
                                                            i9 = 10;
                                                        }
                                                        if (madness.pcleared == 10) {
                                                            i9 = 16;
                                                        }
                                                        if (madness.pcleared == 16) {
                                                            i9 = 24;
                                                        }
                                                        if (madness.pcleared == 24) {
                                                            i9 = 33;
                                                        }
                                                        if (madness.pcleared == 33) {
                                                            i9 = 42;
                                                        }
                                                        if (madness.pcleared == 42) {
                                                            this.clrnce = 8;
                                                            i9 = 51;
                                                        }
                                                        if (madness.pcleared == 51) {
                                                            i9 = 61;
                                                        }
                                                        if (madness.pcleared == 61) {
                                                            i9 = 69;
                                                        }
                                                        if (madness.pcleared == 69) {
                                                            i9 = 75;
                                                        }
                                                        if (madness.pcleared == 75) {
                                                            i9 = 84;
                                                        }
                                                        if (madness.pcleared == 91) {
                                                            i9 = 107;
                                                        }
                                                        if (madness.pcleared == 107) {
                                                            i9 = 125;
                                                        }
                                                        if (madness.pcleared == 125) {
                                                            i9 = 134;
                                                        }
                                                        if (madness.pcleared == 142) {
                                                            i9 = 149;
                                                        }
                                                        if (madness.pcleared == 156) {
                                                            i9 = 162;
                                                        }
                                                        if (madness.pcleared == 166) {
                                                            i9 = 172;
                                                        }
                                                        if (madness.pcleared == 175) {
                                                            i9 = 188;
                                                        }
                                                        if (madness.pcleared == 188) {
                                                            i9 = 203;
                                                        }
                                                        if (madness.pcleared == 203) {
                                                            i9 = 210;
                                                        }
                                                    }
                                                }
                                            }
                                            Label_41548: {
                                                if (checkpoints.stage == 17) {
                                                    if (madness.im != xtgraphics.nplayers - 1) {
                                                        if (!madness.shadowcar) {
                                                            final int[] pointsgo = { 1, 10, 12, 45, 48, 50, 57, 63, 75, 76 };
                                                            if (!this.switchspot) {
                                                                final int chooseone = (int)(Math.random() * 10.0);
                                                                i9 = pointsgo[chooseone];
                                                                this.switchspot = true;
                                                                break Label_41548;
                                                            }
                                                            if (madness.point != i9) {
                                                                break Label_41548;
                                                            }
                                                            ++this.staythere;
                                                            if (this.staythere <= 50) {
                                                                break Label_41548;
                                                            }
                                                            this.switchspot = false;
                                                            this.staythere = 0;
                                                            break Label_41548;
                                                        }
                                                    }
                                                    if (madness.pcleared == 82) {
                                                        i9 = 8;
                                                    }
                                                    if (madness.pcleared == 8) {
                                                        i9 = 14;
                                                    }
                                                    if (madness.pcleared == 14) {
                                                        i9 = 19;
                                                    }
                                                    if (madness.pcleared == 19) {
                                                        i9 = 22;
                                                    }
                                                    Label_40999: {
                                                        if (madness.pcleared == 22) {
                                                            Label_40983: {
                                                                if (madness.specialact) {
                                                                    if (madness.cn != 16) {
                                                                        if (madness.cn != 9) {
                                                                            if (madness.cn != 32) {
                                                                                break Label_40983;
                                                                            }
                                                                        }
                                                                    }
                                                                    i9 = 31;
                                                                    break Label_40999;
                                                                }
                                                            }
                                                            if (i9 >= 27) {
                                                                i9 = 31;
                                                            }
                                                        }
                                                    }
                                                    if (madness.pcleared == 31) {
                                                        i9 = 39;
                                                    }
                                                    if (madness.pcleared == 39) {
                                                        i9 = 43;
                                                    }
                                                    if (madness.pcleared == 43) {
                                                        if (i9 >= 52) {
                                                            i9 = 54;
                                                        }
                                                        this.stuntf = 13;
                                                    }
                                                    if (madness.pcleared == 54) {
                                                        i9 = 60;
                                                    }
                                                    if (madness.pcleared == 60) {
                                                        if (madness.speed > 170.0f) {
                                                            this.up = false;
                                                            this.handb = true;
                                                            this.down = true;
                                                        }
                                                        i9 = 62;
                                                    }
                                                    if (madness.pcleared == 62) {
                                                        i9 = 66;
                                                    }
                                                    if (madness.pcleared == 66) {
                                                        i9 = 71;
                                                    }
                                                    Label_41276: {
                                                        if (madness.pcleared == 71) {
                                                            Label_41251: {
                                                                if (madness.specialact) {
                                                                    if (madness.cn != 16) {
                                                                        if (madness.cn != 9) {
                                                                            if (madness.cn != 32) {
                                                                                break Label_41251;
                                                                            }
                                                                        }
                                                                    }
                                                                    i9 = 80;
                                                                    break Label_41276;
                                                                }
                                                            }
                                                            if (i9 >= 76) {
                                                                i9 = 80;
                                                            }
                                                            else {
                                                                i9 = 76;
                                                            }
                                                        }
                                                    }
                                                    if (madness.pcleared == 80) {
                                                        i9 = 82;
                                                    }
                                                    Label_41397: {
                                                        Label_41384: {
                                                            if (madness.specialact) {
                                                                if (madness.cn != 16) {
                                                                    if (madness.cn != 9) {
                                                                        if (madness.cn != 32) {
                                                                            break Label_41384;
                                                                        }
                                                                    }
                                                                }
                                                                this.variable.dontstunt[madness.im] = true;
                                                                if (madness.pcleared == 43) {
                                                                    i9 = 54;
                                                                }
                                                                break Label_41397;
                                                            }
                                                        }
                                                        this.variable.dontstunt[madness.im] = false;
                                                    }
                                                }
                                            }
                                            Label_42734: {
                                                if (checkpoints.stage == 19) {
                                                    boolean goodracing = false;
                                                    Label_41630: {
                                                        if (this.variable.completed[xtgraphics.nplayers - 1] < 50) {
                                                            if (!madness.shadowcar) {
                                                                break Label_41630;
                                                            }
                                                            if (this.variable.completed[xtgraphics.nplayers - 1] < 20) {
                                                                break Label_41630;
                                                            }
                                                        }
                                                        goodracing = true;
                                                    }
                                                    if (checkpoints.dested[xtgraphics.nplayers - 1] > 0) {
                                                        goodracing = true;
                                                    }
                                                    this.variable.dontmiss[madness.im] = false;
                                                    if (madness.cn != 17) {
                                                        if (madness.cn != 14) {
                                                            if (madness.cn != 1) {
                                                                if (madness.cn != 12) {
                                                                    if (madness.cn != 24) {
                                                                        if (madness.cn != 35) {
                                                                            if (madness.cn != 37) {
                                                                                if (!goodracing) {
                                                                                    break Label_42734;
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                    final boolean nostunt = false;
                                                    if (madness.pcleared == 53) {
                                                        this.clrnce = 3;
                                                        i9 = 2;
                                                    }
                                                    if (madness.pcleared == 2) {
                                                        this.stuntf = 4;
                                                        this.clrnce = 2;
                                                        int whatto2 = 4;
                                                        int j8 = madness.pcleared + 1;
                                                        while (checkpoints.typ[j8] <= 0) {
                                                            if (++j8 != checkpoints.n) {
                                                                continue;
                                                            }
                                                            j8 = 0;
                                                        }
                                                        if (this.py(conto.x / 100, checkpoints.x[j8] / 100, conto.z / 100, checkpoints.z[j8] / 100) < 200) {
                                                            if (this.variable.chkcircle[madness.im] <= 20) {
                                                                if (madness.speed > 150.0f) {
                                                                    whatto2 = 6;
                                                                }
                                                            }
                                                        }
                                                        i9 = whatto2;
                                                        this.variable.dontmiss[madness.im] = true;
                                                    }
                                                    if (madness.pcleared == 4) {
                                                        this.variable.dontmiss[madness.im] = true;
                                                        this.clrnce = 2;
                                                        i9 = 6;
                                                    }
                                                    if (madness.pcleared == 6) {
                                                        this.variable.dontmiss[madness.im] = true;
                                                        this.clrnce = 2;
                                                        i9 = 10;
                                                    }
                                                    if (madness.pcleared == 10) {
                                                        this.variable.dontmiss[madness.im] = true;
                                                        this.clrnce = 10;
                                                        i9 = 14;
                                                    }
                                                    if (madness.pcleared == 14) {
                                                        boolean dontglitch = true;
                                                        if (conto.z > 58000) {
                                                            dontglitch = false;
                                                        }
                                                        this.variable.dontmiss[madness.im] = dontglitch;
                                                        this.clrnce = 10;
                                                        i9 = 15;
                                                    }
                                                    if (madness.pcleared == 15) {
                                                        this.variable.dontmiss[madness.im] = true;
                                                        this.stuntf = 4;
                                                        this.clrnce = 7;
                                                        i9 = 16;
                                                    }
                                                    if (madness.pcleared == 16) {
                                                        this.clrnce = 10;
                                                        this.stuntf = 13;
                                                        i9 = 17;
                                                    }
                                                    if (madness.pcleared == 17) {
                                                        this.clrnce = 2;
                                                        if (!this.delayturn) {
                                                            this.hold = 20;
                                                            this.delayturn = true;
                                                        }
                                                        this.stuntf = 11;
                                                        this.variable.dontmiss[madness.im] = false;
                                                        int whatto2 = 23;
                                                        int j8 = madness.pcleared + 1;
                                                        while (checkpoints.typ[j8] <= 0) {
                                                            if (++j8 != checkpoints.n) {
                                                                continue;
                                                            }
                                                            j8 = 0;
                                                        }
                                                        if (this.py(conto.x / 100, checkpoints.x[j8] / 100, conto.z / 100, checkpoints.z[j8] / 100) < 400) {
                                                            if (this.variable.chkcircle[madness.im] <= 20) {
                                                                if (madness.speed > 150.0f) {
                                                                    whatto2 = 25;
                                                                    this.clrnce = 1;
                                                                }
                                                            }
                                                        }
                                                        i9 = whatto2;
                                                    }
                                                    if (madness.pcleared == 23) {
                                                        if (madness.power > 80.0f) {
                                                            this.variable.dontmiss[madness.im] = true;
                                                        }
                                                        this.clrnce = 1;
                                                        i9 = 25;
                                                    }
                                                    if (madness.pcleared == 25) {
                                                        this.variable.dontmiss[madness.im] = false;
                                                        this.clrnce = 12;
                                                        i9 = 28;
                                                    }
                                                    if (madness.pcleared == 28) {
                                                        if (!this.delayturn) {
                                                            this.hold = 7;
                                                            this.delayturn = true;
                                                        }
                                                        i9 = 30;
                                                    }
                                                    if (madness.pcleared == 30) {
                                                        this.stuntf = 4;
                                                        this.delayturn = false;
                                                        if (madness.cn == 17) {
                                                            if (madness.power >= 80.0f) {
                                                                i9 = 38;
                                                            }
                                                        }
                                                    }
                                                    if (madness.pcleared == 38) {
                                                        this.stuntf = 4;
                                                        i9 = 46;
                                                    }
                                                    Label_42714: {
                                                        if (madness.pcleared == 46) {
                                                            this.stuntf = 4;
                                                            if (i9 >= 46) {
                                                                if (madness.power < 80.0f) {
                                                                    break Label_42714;
                                                                }
                                                                if (madness.cn != 17) {
                                                                    break Label_42714;
                                                                }
                                                            }
                                                            this.clrnce = 10;
                                                            i9 = 53;
                                                        }
                                                    }
                                                    if (madness.cn == 36) {
                                                        this.stuntf = 12;
                                                    }
                                                }
                                            }
                                            Label_43412: {
                                                if (checkpoints.stage == 21) {
                                                    if (madness.pcleared == 40) {
                                                        i9 = 52;
                                                        this.variable.dontmiss[madness.im] = true;
                                                    }
                                                    if (madness.pcleared == 52) {
                                                        i9 = 54;
                                                    }
                                                    if (madness.pcleared == 54) {
                                                        i9 = 56;
                                                    }
                                                    if (madness.pcleared == 56) {
                                                        i9 = 58;
                                                    }
                                                    if (madness.pcleared == 58) {
                                                        this.variable.dontmiss[madness.im] = false;
                                                        if (i9 >= 64) {
                                                            i9 = 69;
                                                        }
                                                    }
                                                    if (madness.pcleared == 69) {
                                                        if (i9 >= 72) {
                                                            i9 = 78;
                                                        }
                                                    }
                                                    if (madness.pcleared == 78) {
                                                        this.clrnce = 12;
                                                        i9 = 84;
                                                    }
                                                    if (madness.pcleared == 84) {
                                                        this.clrnce = 2;
                                                        i9 = 96;
                                                        this.variable.dontmiss[madness.im] = true;
                                                    }
                                                    if (madness.pcleared == 96) {
                                                        i9 = 100;
                                                    }
                                                    if (madness.pcleared == 100) {
                                                        this.clrnce = 10;
                                                        i9 = 110;
                                                    }
                                                    if (madness.pcleared == 110) {
                                                        i9 = 115;
                                                    }
                                                    if (madness.pcleared == 115) {
                                                        this.variable.dontmiss[madness.im] = false;
                                                        i9 = 122;
                                                    }
                                                    if (madness.pcleared == 122) {
                                                        this.clrnce = 12;
                                                        i9 = 128;
                                                    }
                                                    if (madness.pcleared == 144) {
                                                        this.variable.dontmiss[madness.im] = true;
                                                        i9 = 151;
                                                    }
                                                    if (madness.pcleared == 151) {
                                                        this.clrnce = 8;
                                                        this.variable.dontmiss[madness.im] = false;
                                                        i9 = 159;
                                                    }
                                                    if (madness.pcleared == 159) {
                                                        if (i9 >= 177) {
                                                            i9 = 184;
                                                        }
                                                    }
                                                    if (madness.pcleared == 184) {
                                                        if (i9 >= 193) {
                                                            i9 = 198;
                                                        }
                                                    }
                                                    if (madness.pcleared == 198) {
                                                        if (i9 >= 30) {
                                                            i9 = 40;
                                                        }
                                                    }
                                                    if (madness.specialact) {
                                                        if (madness.cn != 16) {
                                                            if (madness.cn != 17) {
                                                                break Label_43412;
                                                            }
                                                        }
                                                        if (madness.pcleared == 58) {
                                                            i9 = 69;
                                                        }
                                                        if (madness.pcleared == 69) {
                                                            i9 = 78;
                                                        }
                                                        if (madness.pcleared == 128) {
                                                            i9 = 144;
                                                        }
                                                        if (madness.pcleared == 159) {
                                                            i9 = 184;
                                                        }
                                                        if (madness.pcleared == 184) {
                                                            i9 = 198;
                                                        }
                                                        if (madness.pcleared == 198) {
                                                            i9 = 40;
                                                        }
                                                        this.clrnce = 11;
                                                    }
                                                }
                                            }
                                            if (checkpoints.stage == 22) {
                                                if (madness.pcleared == 4) {
                                                    i9 = 12;
                                                }
                                                if (madness.pcleared == 12) {
                                                    i9 = 19;
                                                }
                                                if (madness.pcleared == 32) {
                                                    this.variable.dontmiss[madness.im] = true;
                                                    i9 = 34;
                                                }
                                                if (madness.pcleared == 34) {
                                                    this.variable.dontmiss[madness.im] = false;
                                                    i9 = 39;
                                                }
                                                if (madness.pcleared == 46) {
                                                    if (i9 >= 54) {
                                                        i9 = 57;
                                                    }
                                                }
                                                if (madness.pcleared == 57) {
                                                    i9 = 66;
                                                }
                                                if (madness.pcleared == 66) {
                                                    i9 = 74;
                                                }
                                                if (madness.pcleared == 74) {
                                                    i9 = 81;
                                                }
                                                if (madness.pcleared == 81) {
                                                    i9 = 89;
                                                }
                                                if (madness.pcleared == 89) {
                                                    i9 = 95;
                                                }
                                            }
                                            if (checkpoints.stage == 23) {
                                                this.variable.sharpturn[madness.im] = 0;
                                                this.variable.dontstunt[madness.im] = false;
                                                boolean specialcar = false;
                                                if (madness.cn == 17) {
                                                    specialcar = true;
                                                }
                                                if (madness.pcleared == 132) {
                                                    this.saftey = 0;
                                                    if (madness.power <= 70.0f) {
                                                        if (i9 >= 7) {
                                                            this.variable.dontstunt[madness.im] = true;
                                                            i9 = 9;
                                                        }
                                                    }
                                                    else {
                                                        if (this.variable.completed[madness.im] > 30) {
                                                            if (!this.delayturn) {
                                                                this.hold = 7;
                                                                this.delayturn = true;
                                                            }
                                                        }
                                                        Label_43836: {
                                                            if (madness.point > 6) {
                                                                if (this.variable.completed[madness.im] > 30) {
                                                                    this.clrnce = 3;
                                                                    break Label_43836;
                                                                }
                                                            }
                                                            this.clrnce = 6;
                                                        }
                                                        i9 = 9;
                                                    }
                                                }
                                                if (madness.pcleared == 9) {
                                                    this.delayturn = false;
                                                    this.saftey = 0;
                                                    this.clrnce = 4;
                                                    if (madness.power < 75.0f) {
                                                        this.stuntf = 12;
                                                        this.saftey = 8;
                                                    }
                                                    int whatto = 20;
                                                    int j7 = madness.pcleared + 1;
                                                    while (checkpoints.typ[j7] <= 0) {
                                                        if (++j7 != checkpoints.n) {
                                                            continue;
                                                        }
                                                        j7 = 0;
                                                    }
                                                    if (this.py(conto.x / 100, checkpoints.x[j7] / 100, conto.z / 100, checkpoints.z[j7] / 100) < 600) {
                                                        if (this.variable.chkcircle[madness.im] <= 20) {
                                                            if (madness.speed > 180.0f) {
                                                                this.clrnce = 2;
                                                                whatto = 21;
                                                            }
                                                        }
                                                    }
                                                    Label_44151: {
                                                        Label_44135: {
                                                            if (madness.specialact) {
                                                                if (!specialcar) {
                                                                    if (madness.cn != 16) {
                                                                        break Label_44135;
                                                                    }
                                                                }
                                                                if (i9 >= 11) {
                                                                    i9 = whatto;
                                                                }
                                                                break Label_44151;
                                                            }
                                                        }
                                                        if (i9 >= 18) {
                                                            i9 = whatto;
                                                        }
                                                    }
                                                    int turnlvl = 3;
                                                    if (specialcar) {
                                                        if (madness.specialact) {
                                                            turnlvl = 4;
                                                        }
                                                    }
                                                    this.variable.sharpturn[madness.im] = turnlvl;
                                                    this.turntyp = 1;
                                                }
                                                if (madness.pcleared == 20) {
                                                    this.turntyp = 1;
                                                    if (madness.point >= 22) {
                                                        this.clrnce = 5;
                                                    }
                                                    else {
                                                        i9 = 22;
                                                    }
                                                    this.saftey = 5;
                                                    int whatto = 23;
                                                    int j7 = madness.pcleared + 1;
                                                    while (checkpoints.typ[j7] <= 0) {
                                                        if (++j7 != checkpoints.n) {
                                                            continue;
                                                        }
                                                        j7 = 0;
                                                    }
                                                    if (this.py(conto.x / 100, checkpoints.x[j7] / 100, conto.z / 100, checkpoints.z[j7] / 100) < 700) {
                                                        if (this.variable.chkcircle[madness.im] <= 20) {
                                                            if (madness.speed > 250.0f) {
                                                                this.clrnce = 3;
                                                                whatto = 26;
                                                            }
                                                        }
                                                    }
                                                    i9 = whatto;
                                                    this.variable.sharpturn[madness.im] = 1;
                                                    this.delayturn = false;
                                                }
                                                if (madness.pcleared == 23) {
                                                    this.turntyp = 1;
                                                    this.saftey = 0;
                                                    if (i9 < 29) {
                                                        i9 = 29;
                                                    }
                                                    if (i9 >= 30) {
                                                        this.clrnce = 5;
                                                        i9 = 34;
                                                    }
                                                }
                                                if (madness.pcleared == 34) {
                                                    if (i9 >= 37) {
                                                        int whatto = 38;
                                                        int j7 = madness.pcleared + 1;
                                                        while (checkpoints.typ[j7] <= 0) {
                                                            if (++j7 != checkpoints.n) {
                                                                continue;
                                                            }
                                                            j7 = 0;
                                                        }
                                                        if (this.py(conto.x / 100, checkpoints.x[j7] / 100, conto.z / 100, checkpoints.z[j7] / 100) < 600) {
                                                            if (this.variable.chkcircle[madness.im] <= 20) {
                                                                if (madness.speed > 250.0f) {
                                                                    this.clrnce = 2;
                                                                    whatto = 40;
                                                                }
                                                            }
                                                        }
                                                        i9 = whatto;
                                                        this.delayturn = false;
                                                    }
                                                    else {
                                                        if (!this.delayturn) {
                                                            this.hold = 4;
                                                            this.delayturn = true;
                                                        }
                                                        i9 = 37;
                                                    }
                                                }
                                                if (madness.pcleared == 38) {
                                                    if (i9 < 41) {
                                                        i9 = 41;
                                                    }
                                                    if (madness.point <= 52) {
                                                        if (!madness.specialact) {
                                                            this.stuntf = 12;
                                                            if (madness.spatk < 60.0f) {
                                                                this.saftey = 20;
                                                            }
                                                        }
                                                    }
                                                    if (i9 >= 53) {
                                                        this.clrnce = 3;
                                                        i9 = 59;
                                                    }
                                                }
                                                if (madness.pcleared == 59) {
                                                    if (!this.delayturn) {
                                                        this.hold = 4;
                                                        this.delayturn = true;
                                                    }
                                                    int whatto = 63;
                                                    int j7 = madness.pcleared + 1;
                                                    while (checkpoints.typ[j7] <= 0) {
                                                        if (++j7 != checkpoints.n) {
                                                            continue;
                                                        }
                                                        j7 = 0;
                                                    }
                                                    if (this.py(conto.x / 100, checkpoints.x[j7] / 100, conto.z / 100, checkpoints.z[j7] / 100) < 550) {
                                                        if (this.variable.chkcircle[madness.im] <= 20) {
                                                            if (madness.speed > 250.0f) {
                                                                whatto = 66;
                                                            }
                                                        }
                                                    }
                                                    if (madness.power < 80.0f) {
                                                        this.variable.sharpturn[madness.im] = 3;
                                                    }
                                                    i9 = whatto;
                                                }
                                                if (madness.pcleared == 63) {
                                                    if (i9 < 66) {
                                                        i9 = 66;
                                                    }
                                                    this.delayturn = false;
                                                    int whatto = 77;
                                                    int j7 = madness.pcleared + 1;
                                                    while (checkpoints.typ[j7] <= 0) {
                                                        if (++j7 != checkpoints.n) {
                                                            continue;
                                                        }
                                                        j7 = 0;
                                                    }
                                                    if (madness.point >= 70) {
                                                        this.variable.dontstunt[madness.im] = true;
                                                    }
                                                    if (this.py(conto.x / 100, checkpoints.x[j7] / 100, conto.z / 100, checkpoints.z[j7] / 100) < 500) {
                                                        if (this.variable.chkcircle[madness.im] <= 20) {
                                                            if (madness.speed > 250.0f) {
                                                                this.clrnce = 2;
                                                                whatto = 79;
                                                            }
                                                        }
                                                    }
                                                    int turnlvl = 3;
                                                    if (specialcar) {
                                                        if (madness.specialact) {
                                                            turnlvl = 4;
                                                        }
                                                    }
                                                    this.variable.sharpturn[madness.im] = turnlvl;
                                                    i9 = whatto;
                                                }
                                                if (madness.pcleared == 77) {
                                                    int whatto = 80;
                                                    int j7 = madness.pcleared + 1;
                                                    while (checkpoints.typ[j7] <= 0) {
                                                        if (++j7 != checkpoints.n) {
                                                            continue;
                                                        }
                                                        j7 = 0;
                                                    }
                                                    if (this.py(conto.x / 100, checkpoints.x[j7] / 100, conto.z / 100, checkpoints.z[j7] / 100) < 500) {
                                                        if (this.variable.chkcircle[madness.im] <= 20) {
                                                            if (madness.speed > 250.0f) {
                                                                this.clrnce = 0;
                                                                whatto = 82;
                                                            }
                                                        }
                                                    }
                                                    i9 = whatto;
                                                    this.variable.sharpturn[madness.im] = 1;
                                                }
                                                if (madness.pcleared == 80) {
                                                    int whatto = 84;
                                                    int j7 = madness.pcleared + 1;
                                                    while (checkpoints.typ[j7] <= 0) {
                                                        if (++j7 != checkpoints.n) {
                                                            continue;
                                                        }
                                                        j7 = 0;
                                                    }
                                                    if (this.py(conto.x / 100, checkpoints.x[j7] / 100, conto.z / 100, checkpoints.z[j7] / 100) < 450) {
                                                        if (this.variable.chkcircle[madness.im] <= 20) {
                                                            if (madness.speed > 250.0f) {
                                                                whatto = 86;
                                                            }
                                                        }
                                                    }
                                                    i9 = whatto;
                                                }
                                                Label_45696: {
                                                    if (madness.pcleared == 84) {
                                                        if (i9 < 88) {
                                                            i9 = 88;
                                                        }
                                                        this.saftey = 0;
                                                        if (i9 < 89) {
                                                            if (madness.power <= 80.0f) {
                                                                break Label_45696;
                                                            }
                                                        }
                                                        if (madness.power > 80.0f) {
                                                            this.clrnce = 10;
                                                        }
                                                        i9 = 95;
                                                    }
                                                }
                                                if (madness.pcleared == 95) {
                                                    if (i9 >= 99) {
                                                        this.delayturn = false;
                                                        i9 = 100;
                                                    }
                                                    else {
                                                        if (!this.delayturn) {
                                                            this.hold = 3;
                                                            this.delayturn = true;
                                                        }
                                                        i9 = 99;
                                                    }
                                                }
                                                if (madness.pcleared == 100) {
                                                    if (i9 >= 106) {
                                                        this.delayturn = false;
                                                        i9 = 107;
                                                    }
                                                    else {
                                                        if (!this.delayturn) {
                                                            this.hold = 7;
                                                            this.delayturn = true;
                                                        }
                                                        i9 = 106;
                                                    }
                                                }
                                                if (madness.pcleared == 107) {
                                                    if (!this.delayturn) {
                                                        this.hold = 7;
                                                        this.delayturn = true;
                                                    }
                                                    this.clrnce = 9;
                                                    i9 = 114;
                                                }
                                                if (madness.pcleared == 114) {
                                                    i9 = 124;
                                                    this.delayturn = false;
                                                    this.variable.sharpturn[madness.im] = 1;
                                                    if (i9 >= 120) {
                                                        this.variable.dontstunt[madness.im] = true;
                                                    }
                                                }
                                                if (madness.pcleared == 124) {
                                                    if (i9 >= 130) {
                                                        i9 = 132;
                                                    }
                                                    else {
                                                        this.clrnce = 7;
                                                        i9 = 130;
                                                    }
                                                }
                                                boolean wideturn = false;
                                                Label_46031: {
                                                    if (madness.specialact) {
                                                        if (madness.cn != 17) {
                                                            if (madness.cn != 16) {
                                                                break Label_46031;
                                                            }
                                                        }
                                                        wideturn = true;
                                                    }
                                                }
                                                if (wideturn) {
                                                    if (madness.pcleared == 23) {
                                                        this.clrnce = 10;
                                                        i9 = 34;
                                                    }
                                                    if (madness.pcleared == 38) {
                                                        if (i9 >= 57) {
                                                            i9 = 59;
                                                            this.delayturn = false;
                                                        }
                                                        else {
                                                            if (!this.delayturn) {
                                                                this.hold = 4;
                                                                this.delayturn = true;
                                                            }
                                                            i9 = 57;
                                                        }
                                                    }
                                                    if (madness.pcleared == 63) {
                                                        i9 = 77;
                                                    }
                                                    if (madness.pcleared == 84) {
                                                        this.clrnce = 10;
                                                        i9 = 95;
                                                    }
                                                }
                                            }
                                            break Label_46204;
                                        }
                                    }
                                    if (this.trfix >= 2) {
                                        this.variable.dontstunt[madness.im] = false;
                                    }
                                }
                                if (checkpoints.stage == 15) {
                                    if (xtgraphics.classicmode) {
                                        Label_46383: {
                                            Label_46341: {
                                                if (madness.pcleared != 91) {
                                                    if (checkpoints.pos[0] < checkpoints.pos[madness.im]) {
                                                        if (madness.cn != 13) {
                                                            if (madness.cn != 36) {
                                                                break Label_46341;
                                                            }
                                                        }
                                                    }
                                                }
                                                if (checkpoints.pos[madness.im] != 0) {
                                                    break Label_46383;
                                                }
                                                if (madness.clear != 12) {
                                                    if (madness.clear != 20) {
                                                        break Label_46383;
                                                    }
                                                }
                                            }
                                            while (checkpoints.typ[i9] == -4) {
                                                if (++i9 != checkpoints.n) {
                                                    continue;
                                                }
                                                i9 = 0;
                                            }
                                        }
                                        if (madness.pcleared == 9) {
                                            if (this.py(conto.x / 100, 297, conto.z / 100, 347) < 400) {
                                                this.oupnt = 1;
                                            }
                                            if (this.oupnt == 1) {
                                                if (i9 < 22) {
                                                    i9 = 22;
                                                }
                                            }
                                        }
                                        if (madness.pcleared == 67) {
                                            if (this.py(conto.x / 100, 28, conto.z / 100, 494) < 4000) {
                                                this.oupnt = 2;
                                            }
                                            if (this.oupnt == 2) {
                                                i9 = 76;
                                            }
                                        }
                                        if (madness.pcleared == 76) {
                                            if (this.py(conto.x / 100, -50, conto.z / 100, 0) < 2000) {
                                                this.oupnt = 3;
                                            }
                                            if (this.oupnt != 3) {
                                                i9 = 89;
                                            }
                                            else {
                                                i9 = 91;
                                            }
                                        }
                                    }
                                }
                                if (checkpoints.stage == 16) {
                                    if (xtgraphics.classicmode) {
                                        if (madness.pcleared == 128) {
                                            Label_46710: {
                                                if (this.py(conto.x / 100, 0, conto.z / 100, 229) >= 1500) {
                                                    if (conto.z <= 23000) {
                                                        break Label_46710;
                                                    }
                                                }
                                                this.oupnt = 128;
                                            }
                                            if (this.oupnt != 128) {
                                                i9 = 3;
                                            }
                                        }
                                        if (madness.pcleared == 8) {
                                            Label_46798: {
                                                if (this.py(conto.x / 100, -207, conto.z / 100, 549) >= 1500) {
                                                    if (conto.x >= -20700) {
                                                        break Label_46798;
                                                    }
                                                }
                                                this.oupnt = 8;
                                            }
                                            if (this.oupnt != 8) {
                                                i9 = 12;
                                            }
                                        }
                                        if (madness.pcleared == 33) {
                                            Label_46886: {
                                                if (this.py(conto.x / 100, -60, conto.z / 100, 168) >= 250) {
                                                    if (conto.z <= 17000) {
                                                        break Label_46886;
                                                    }
                                                }
                                                this.oupnt = 331;
                                            }
                                            Label_46942: {
                                                if (this.py(conto.x / 100, -112, conto.z / 100, 414) >= 10000) {
                                                    if (conto.z <= 40000) {
                                                        break Label_46942;
                                                    }
                                                }
                                                this.oupnt = 332;
                                            }
                                            if (this.oupnt != 331) {
                                                if (this.oupnt != 332) {
                                                    if (this.trfix == 1) {
                                                        i9 = 39;
                                                    }
                                                    else {
                                                        i9 = 38;
                                                    }
                                                }
                                            }
                                            if (this.oupnt == 331) {
                                                i9 = 71;
                                            }
                                        }
                                        if (madness.pcleared == 42) {
                                            Label_47087: {
                                                if (this.py(conto.x / 100, -269, conto.z / 100, 493) >= 100) {
                                                    if (conto.x >= -27000) {
                                                        break Label_47087;
                                                    }
                                                }
                                                this.oupnt = 142;
                                            }
                                            if (this.oupnt != 142) {
                                                i9 = 47;
                                            }
                                        }
                                        if (madness.pcleared == 51) {
                                            Label_47176: {
                                                if (this.py(conto.x / 100, -352, conto.z / 100, 260) >= 100) {
                                                    if (conto.z >= 25000) {
                                                        break Label_47176;
                                                    }
                                                }
                                                this.oupnt = 511;
                                            }
                                            Label_47232: {
                                                if (this.py(conto.x / 100, -325, conto.z / 100, 10) >= 2000) {
                                                    if (conto.x <= -32000) {
                                                        break Label_47232;
                                                    }
                                                }
                                                this.oupnt = 512;
                                            }
                                            if (this.oupnt != 511) {
                                                if (this.oupnt != 512) {
                                                    i9 = 80;
                                                }
                                            }
                                            if (this.oupnt == 511) {
                                                i9 = 61;
                                            }
                                        }
                                        if (madness.pcleared == 77) {
                                            Label_47354: {
                                                if (this.py(conto.x / 100, -371, conto.z / 100, 319) >= 100) {
                                                    if (conto.z >= 31000) {
                                                        break Label_47354;
                                                    }
                                                }
                                                this.oupnt = 77;
                                            }
                                            if (this.oupnt != 77) {
                                                i9 = 78;
                                                madness.nofocus = true;
                                            }
                                        }
                                        if (madness.pcleared == 105) {
                                            Label_47446: {
                                                if (this.py(conto.x / 100, -179, conto.z / 100, 10) >= 2300) {
                                                    if (conto.z >= 1050) {
                                                        break Label_47446;
                                                    }
                                                }
                                                this.oupnt = 105;
                                            }
                                            if (this.oupnt == 105) {
                                                i9 = 125;
                                            }
                                            else {
                                                i9 = 65;
                                            }
                                        }
                                        if (this.trfix == 3) {
                                            Label_47541: {
                                                if (this.py(conto.x / 100, -52, conto.z / 100, 448) >= 100) {
                                                    if (conto.z <= 45000) {
                                                        break Label_47541;
                                                    }
                                                }
                                                this.oupnt = 176;
                                            }
                                            if (this.oupnt == 176) {
                                                i9 = 43;
                                            }
                                            else {
                                                i9 = 41;
                                            }
                                        }
                                    }
                                }
                                for (int a5 = 0; a5 < xtgraphics.nplayers; ++a5) {
                                    if (this.avoidnlev[a5] > 0) {
                                        if (checkpoints.stage >= 5) {
                                            if (checkpoints.dested[a5] == 0) {
                                                if (madness.im != a5) {
                                                    if (!this.neverhit[a5]) {
                                                        if (checkpoints.clear[madness.im] - checkpoints.clear[a5] >= 2) {
                                                            if (this.py(conto.x / 100, checkpoints.opx[a5] / 100, conto.z / 100, checkpoints.opz[a5] / 100) < 1000 + this.avoidnlev[a5]) {
                                                                int j11 = conto.xz;
                                                                if (this.zyinv) {
                                                                    j11 += 180;
                                                                }
                                                                while (j11 < 0) {
                                                                    j11 += 360;
                                                                }
                                                                while (j11 > 180) {
                                                                    j11 -= 360;
                                                                }
                                                                char c7 = '\0';
                                                                if (checkpoints.opx[a5] - conto.x >= 0) {
                                                                    c7 = '´';
                                                                }
                                                                int k11;
                                                                for (k11 = (int)('Z' + c7 + Math.atan((checkpoints.opz[a5] - conto.z) / (double)(checkpoints.opx[a5] - conto.x)) / 0.017453292519943295); k11 < 0; k11 += 360) {}
                                                                while (k11 > 180) {
                                                                    k11 -= 360;
                                                                }
                                                                int j12 = Math.abs(j11 - k11);
                                                                if (j12 > 180) {
                                                                    j12 = Math.abs(j12 - 360);
                                                                }
                                                                if (j12 < 90) {
                                                                    this.wall = 0;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                if (this.rampp == 2) {
                                    int j13 = i9 + 1;
                                    if (j13 == checkpoints.n) {
                                        j13 = 0;
                                    }
                                    if (checkpoints.typ[j13] == -2) {
                                        if (i9 != madness.point) {
                                            if (--i9 < 0) {
                                                i9 += checkpoints.n;
                                            }
                                        }
                                    }
                                }
                                if (this.bulistc) {
                                    madness.nofocus = true;
                                    if (this.gowait) {
                                        this.gowait = false;
                                    }
                                }
                                break Label_50241;
                            }
                        }
                        Label_48233: {
                            Label_48167: {
                                if (checkpoints.stage != 15) {
                                    if (checkpoints.stage != 16) {
                                        break Label_48167;
                                    }
                                }
                                if (this.runbul != 0) {
                                    if (!this.backfix) {
                                        break Label_48233;
                                    }
                                }
                            }
                            i9 -= 2;
                            if (i9 < 0) {
                                i9 += checkpoints.n;
                            }
                            while (checkpoints.typ[i9] == -4) {
                                if (--i9 >= 0) {
                                    continue;
                                }
                                i9 += checkpoints.n;
                            }
                        }
                        Label_48399: {
                            if (checkpoints.stage == 11) {
                                if (xtgraphics.classicmode) {
                                    if (i9 >= 14) {
                                        if (i9 <= 19) {
                                            i9 = 13;
                                        }
                                    }
                                    if (this.oupnt == 72) {
                                        if (i9 != 56) {
                                            i9 = 57;
                                            break Label_48399;
                                        }
                                    }
                                    if (this.oupnt == 54) {
                                        if (i9 != 52) {
                                            i9 = 53;
                                            break Label_48399;
                                        }
                                    }
                                    if (this.oupnt == 39) {
                                        if (i9 != 37) {
                                            i9 = 38;
                                            break Label_48399;
                                        }
                                    }
                                    this.oupnt = i9;
                                }
                            }
                        }
                        if (checkpoints.stage == 15) {
                            if (xtgraphics.classicmode) {
                                if (this.oupnt == -1) {
                                    int k12 = -10;
                                    for (int k13 = 0; k13 < checkpoints.n; ++k13) {
                                        if (checkpoints.typ[k13] != -2) {
                                            if (checkpoints.typ[k13] != -4) {
                                                continue;
                                            }
                                        }
                                        if (k13 >= 50) {
                                            if (k13 <= 54) {
                                                continue;
                                            }
                                        }
                                        if (this.py(conto.x / 100, checkpoints.x[k13] / 100, conto.z / 100, checkpoints.z[k13] / 100) >= k12) {
                                            if (k12 != -10) {
                                                continue;
                                            }
                                        }
                                        k12 = this.py(conto.x / 100, checkpoints.x[k13] / 100, conto.z / 100, checkpoints.z[k13] / 100);
                                        this.oupnt = k13;
                                    }
                                    --this.oupnt;
                                    if (i9 < 0) {
                                        this.oupnt += checkpoints.n;
                                    }
                                }
                                if (this.oupnt >= 0) {
                                    if (this.oupnt < checkpoints.n) {
                                        i9 = this.oupnt;
                                        if (this.py(conto.x / 100, checkpoints.x[i9] / 100, conto.z / 100, checkpoints.z[i9] / 100) < 800) {
                                            this.oupnt = -(int)(75.0f + this.m.random() * 200.0f);
                                            this.runbul = (int)(50.0f + this.m.random() * 100.0f);
                                        }
                                    }
                                }
                                if (this.oupnt < -1) {
                                    ++this.oupnt;
                                }
                                if (this.runbul != 0) {
                                    --this.runbul;
                                }
                            }
                        }
                        if (xtgraphics.careermode) {
                            if (checkpoints.stage == 11) {
                                if (this.bulistc) {
                                    if (i9 >= 24) {
                                        if (i9 <= 26) {
                                            i9 = 18;
                                        }
                                    }
                                }
                            }
                            if (checkpoints.stage == 12) {
                                if (this.bulistc) {
                                    if (i9 >= 0) {
                                        if (i9 <= 2) {
                                            i9 = 48;
                                        }
                                    }
                                }
                            }
                        }
                        if (checkpoints.stage == 16) {
                            if (xtgraphics.classicmode) {
                                boolean flag6 = false;
                                Label_49823: {
                                    if (madness.cn == 36) {
                                        if (!this.gowait) {
                                            if (checkpoints.clear[0] == 1) {
                                                if (this.m.random() <= 0.5) {
                                                    this.wtx = -5600;
                                                    this.wtz = 8000;
                                                    this.frx = -7350;
                                                    this.frz = -4550;
                                                    this.frad = 22000;
                                                    this.oupnt = 15;
                                                }
                                                else {
                                                    this.wtx = -14000;
                                                    this.wtz = 48000;
                                                    this.frx = -5600;
                                                    this.frz = 47600;
                                                    this.frad = 88000;
                                                    this.oupnt = 33;
                                                }
                                                this.gowait = true;
                                                this.afta = false;
                                            }
                                            if (checkpoints.clear[0] == 4) {
                                                this.wtx = -12700;
                                                this.wtz = 14000;
                                                this.frx = -31000;
                                                this.frz = 1050;
                                                this.frad = 11000;
                                                this.oupnt = 51;
                                                this.gowait = true;
                                                this.afta = false;
                                            }
                                            if (checkpoints.clear[0] == 14) {
                                                this.wtx = -35350;
                                                this.wtz = 6650;
                                                this.frx = -48300;
                                                this.frz = 54950;
                                                this.frad = 11000;
                                                this.oupnt = 15;
                                                this.gowait = true;
                                                this.afta = false;
                                            }
                                            if (checkpoints.clear[0] == 17) {
                                                this.wtx = -42700;
                                                this.wtz = 41000;
                                                this.frx = -40950;
                                                this.frz = 49350;
                                                this.frad = 7000;
                                                this.oupnt = 42;
                                                this.gowait = true;
                                                this.afta = false;
                                            }
                                            if (checkpoints.clear[0] == 21) {
                                                this.wtx = -1750;
                                                this.wtz = -15750;
                                                this.frx = -25900;
                                                this.frz = -14000;
                                                this.frad = 11000;
                                                this.oupnt = 125;
                                                this.gowait = true;
                                                this.afta = false;
                                            }
                                        }
                                        if (this.gowait) {
                                            if (this.py(conto.x / 100, this.wtx / 100, conto.z / 100, this.wtz / 100) < 10000) {
                                                if (madness.speed > 50.0f) {
                                                    this.up = false;
                                                }
                                            }
                                            if (this.py(conto.x / 100, this.wtx / 100, conto.z / 100, this.wtz / 100) < 200) {
                                                this.up = false;
                                                this.handb = true;
                                            }
                                            if (checkpoints.pcleared == this.oupnt) {
                                                if (this.py(checkpoints.opx[0] / 100, this.frx / 100, checkpoints.opz[0] / 100, this.frz / 100) < this.frad) {
                                                    this.runbul = 0;
                                                    this.afta = true;
                                                    this.gowait = false;
                                                }
                                            }
                                            if (this.py(conto.x / 100, checkpoints.opx[0] / 100, conto.z / 100, checkpoints.opz[0] / 100) < 25) {
                                                this.afta = true;
                                                this.gowait = false;
                                                this.attack = 200;
                                                this.acr = 0;
                                            }
                                            if (checkpoints.clear[0] == 21) {
                                                if (this.oupnt != 125) {
                                                    this.gowait = false;
                                                }
                                            }
                                        }
                                        Label_49776: {
                                            if (checkpoints.clear[0] >= 11) {
                                                if (!this.gowait) {
                                                    break Label_49776;
                                                }
                                            }
                                            if (madness.power < 60.0f) {
                                                if (checkpoints.clear[0] < 21) {
                                                    break Label_49776;
                                                }
                                            }
                                            if (!this.exitattack) {
                                                break Label_49823;
                                            }
                                            this.exitattack = false;
                                            break Label_49823;
                                        }
                                        flag6 = true;
                                        if (!this.exitattack) {
                                            this.oupnt = -1;
                                            this.exitattack = true;
                                        }
                                    }
                                }
                                if (madness.cn == 34) {
                                    flag6 = true;
                                }
                                if (flag6) {
                                    if (this.oupnt == -1) {
                                        int l9 = -10;
                                        for (int k14 = 0; k14 < checkpoints.n; ++k14) {
                                            if (checkpoints.typ[k14] == -4) {
                                                Label_49972: {
                                                    if (this.py(conto.x / 100, checkpoints.x[k14] / 100, conto.z / 100, checkpoints.z[k14] / 100) < l9) {
                                                        if (this.m.random() > 0.6) {
                                                            break Label_49972;
                                                        }
                                                    }
                                                    if (l9 != -10) {
                                                        continue;
                                                    }
                                                }
                                                l9 = this.py(conto.x / 100, checkpoints.x[k14] / 100, conto.z / 100, checkpoints.z[k14] / 100);
                                                this.oupnt = k14;
                                            }
                                        }
                                        --this.oupnt;
                                        if (i9 < 0) {
                                            this.oupnt += checkpoints.n;
                                        }
                                    }
                                    if (this.oupnt >= 0) {
                                        if (this.oupnt < checkpoints.n) {
                                            i9 = this.oupnt;
                                            if (this.py(conto.x / 100, checkpoints.x[i9] / 100, conto.z / 100, checkpoints.z[i9] / 100) < 800) {
                                                this.oupnt = -(int)(75.0f + this.m.random() * 200.0f);
                                                this.runbul = (int)(50.0f + this.m.random() * 100.0f);
                                            }
                                        }
                                    }
                                    if (this.oupnt < -1) {
                                        ++this.oupnt;
                                    }
                                    if (this.runbul != 0) {
                                        --this.runbul;
                                    }
                                }
                            }
                        }
                        madness.nofocus = true;
                    }
                    Label_50315: {
                        Label_50310: {
                            if (checkpoints.stage != 9) {
                                if (checkpoints.stage == 8) {
                                    if (madness.pcleared == 73) {
                                        break Label_50310;
                                    }
                                }
                                if (checkpoints.stage != 16) {
                                    if (!xtgraphics.careermode) {
                                        break Label_50315;
                                    }
                                }
                            }
                        }
                        this.forget = true;
                    }
                    Label_52930: {
                        if (madness.missedcp != 0) {
                            if (!this.forget) {
                                if (this.trfix != 4) {
                                    break Label_52930;
                                }
                            }
                        }
                        if (this.trfix != 0) {
                            Label_50413: {
                                if (checkpoints.stage != 15) {
                                    if (checkpoints.stage != 16) {
                                        break Label_50413;
                                    }
                                }
                                if (xtgraphics.classicmode) {
                                    this.variable.whichfix = 3;
                                }
                            }
                            if (xtgraphics.careermode) {
                                int numfixes = 2;
                                if (checkpoints.stage == 7) {
                                    numfixes = 4;
                                }
                                Label_50626: {
                                    if (checkpoints.stage != 7) {
                                        if (checkpoints.stage != 11) {
                                            break Label_50626;
                                        }
                                    }
                                    final int[] distance = new int[numfixes];
                                    final int[] match = new int[numfixes];
                                    for (int a4 = 0; a4 < numfixes; ++a4) {
                                        match[a4] = (distance[a4] = this.py(conto.x / 100, checkpoints.x[this.fpnt[a4]] / 100, conto.z / 100, checkpoints.z[this.fpnt[a4]] / 100));
                                    }
                                    Arrays.sort(match);
                                    for (int a4 = 0; a4 < numfixes; ++a4) {
                                        if (match[0] == distance[a4]) {
                                            this.variable.whichfix = a4;
                                        }
                                    }
                                }
                                if (checkpoints.stage == 13) {
                                    final int[] fixpoints = new int[4];
                                    final int[] fixid = new int[4];
                                    boolean easyfix = false;
                                    if (madness.cn != 14) {
                                        easyfix = true;
                                    }
                                    for (int a7 = 0; a7 < checkpoints.n; ++a7) {
                                        if (checkpoints.floor[a7] == xtgraphics.floor[madness.im]) {
                                            Label_50777: {
                                                if (checkpoints.telefloor[a7] != 3) {
                                                    if (checkpoints.telefloor[a7] != 1) {
                                                        break Label_50777;
                                                    }
                                                    if (xtgraphics.floor[madness.im] != 3) {
                                                        break Label_50777;
                                                    }
                                                }
                                                fixpoints[2] = a7;
                                                fixid[2] = (3 - checkpoints.telefloor[a7]) * 2 + 1;
                                            }
                                            if (checkpoints.telefloor[a7] != 2) {
                                                if (checkpoints.telefloor[a7] != 1) {
                                                    continue;
                                                }
                                                if (xtgraphics.floor[madness.im] != 2) {
                                                    continue;
                                                }
                                            }
                                            fixpoints[3] = a7;
                                            fixid[3] = (3 - checkpoints.telefloor[a7]) * 2 + 1;
                                        }
                                    }
                                    for (int a7 = 0; a7 < 2; ++a7) {
                                        fixid[a7] = (3 - xtgraphics.floor[madness.im]) * 2 + a7;
                                        fixpoints[a7] = this.fpnt[fixid[a7]];
                                        if (!this.setfixfloor) {
                                            if (xtgraphics.floor[madness.im] != 0) {
                                                if (xtgraphics.floor[madness.im] != 3) {
                                                    continue;
                                                }
                                            }
                                            if (!easyfix) {
                                                fixid[a7] = fixid[a7 + 2];
                                                fixpoints[a7] = fixpoints[a7 + 2];
                                            }
                                        }
                                    }
                                    int fixroutes = 4;
                                    Label_51038: {
                                        if (!this.setfixfloor) {
                                            if (!easyfix) {
                                                break Label_51038;
                                            }
                                        }
                                        fixroutes = 2;
                                    }
                                    final int[] distance2 = new int[fixroutes];
                                    final int[] match2 = new int[fixroutes];
                                    for (int a8 = 0; a8 < fixroutes; ++a8) {
                                        match2[a8] = (distance2[a8] = this.py(conto.x / 100, checkpoints.x[fixpoints[a8]] / 100, conto.z / 100, checkpoints.z[fixpoints[a8]] / 100));
                                    }
                                    Arrays.sort(match2);
                                    for (int a8 = 0; a8 < fixroutes; ++a8) {
                                        if (match2[0] == distance2[a8]) {
                                            if (this.setfixfloor) {
                                                if (checkpoints.floor[fixpoints[a8]] != xtgraphics.floor[madness.im]) {
                                                    continue;
                                                }
                                            }
                                            this.variable.whichfix = fixid[a8];
                                        }
                                    }
                                }
                            }
                            Label_51454: {
                                if (this.trfix == 2) {
                                    i9 = this.fpnt[this.variable.whichfix];
                                    this.clrnce = 3;
                                    if (xtgraphics.careermode) {
                                        Label_51307: {
                                            if (checkpoints.stage != 15) {
                                                if (checkpoints.stage != 17) {
                                                    break Label_51307;
                                                }
                                            }
                                            this.clrnce = 1;
                                        }
                                        if (checkpoints.stage == 22) {
                                            this.clrnce = 2;
                                        }
                                        if (checkpoints.stage != 21) {
                                            if (checkpoints.stage != 18) {
                                                break Label_51454;
                                            }
                                        }
                                        if (this.py(conto.x / 100, checkpoints.x[this.fpnt[this.variable.whichfix]] / 100, conto.z / 100, checkpoints.z[this.fpnt[this.variable.whichfix]] / 100) < 15000) {
                                            if (madness.speed >= 300.0f) {
                                                this.up = false;
                                                this.down = true;
                                                this.handb = true;
                                            }
                                        }
                                    }
                                }
                            }
                            if (this.trfix >= 2) {
                                if (xtgraphics.careermode) {
                                    Label_51615: {
                                        if (!xtgraphics.bonusstage[0]) {
                                            if (checkpoints.stage != 6) {
                                                if (checkpoints.stage != 7) {
                                                    if (checkpoints.stage != 8) {
                                                        if (checkpoints.stage != 18) {
                                                            if (checkpoints.stage != 19) {
                                                                if (checkpoints.stage != 21) {
                                                                    break Label_51615;
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        if (i9 < this.fpnt[this.variable.whichfix]) {
                                            i9 = this.fpnt[this.variable.whichfix];
                                        }
                                    }
                                    if (checkpoints.stage == 7) {
                                        if (this.variable.whichfix == 0) {
                                            if (i9 > 33) {
                                                i9 = 33;
                                            }
                                        }
                                        if (this.variable.whichfix == 1) {
                                            if (i9 == 34) {
                                                i9 = 37;
                                            }
                                        }
                                    }
                                    if (checkpoints.stage == 11) {
                                        if (this.variable.whichfix == 0) {
                                            if (i9 < this.fpnt[0]) {
                                                i9 = 44;
                                            }
                                        }
                                        if (this.variable.whichfix == 1) {
                                            if (i9 < this.fpnt[1]) {
                                                i9 = 47;
                                            }
                                        }
                                    }
                                    if (checkpoints.stage == 13) {
                                        Label_51850: {
                                            if (this.variable.whichfix == 0) {
                                                if (i9 <= this.fpnt[0] + 2) {
                                                    if (i9 >= this.fpnt[0]) {
                                                        break Label_51850;
                                                    }
                                                }
                                                i9 = this.fpnt[0] + 2;
                                            }
                                        }
                                        if (this.variable.whichfix == 1) {
                                            if (i9 < this.fpnt[1]) {
                                                i9 = this.fpnt[1] + 3;
                                            }
                                        }
                                        Label_51960: {
                                            if (this.variable.whichfix != 2) {
                                                if (this.variable.whichfix != 3) {
                                                    break Label_51960;
                                                }
                                            }
                                            if (i9 < this.fpnt[this.variable.whichfix]) {
                                                i9 = this.fpnt[this.variable.whichfix];
                                            }
                                        }
                                        if (this.variable.whichfix == 4) {
                                            if (i9 > this.fpnt[4] + 3) {
                                                i9 = this.fpnt[4] + 3;
                                            }
                                        }
                                        Label_52075: {
                                            if (this.variable.whichfix != 5) {
                                                if (this.variable.whichfix != 7) {
                                                    break Label_52075;
                                                }
                                            }
                                            if (i9 < this.fpnt[this.variable.whichfix]) {
                                                i9 = this.fpnt[this.variable.whichfix] + 4;
                                            }
                                        }
                                        if (this.variable.whichfix == 6) {
                                            if (i9 < this.fpnt[6]) {
                                                i9 = this.fpnt[6];
                                            }
                                            if (i9 > this.fpnt[6] + 2) {
                                                i9 = this.fpnt[6] + 2;
                                            }
                                        }
                                    }
                                }
                            }
                            boolean fixstage = false;
                            Label_52237: {
                                if (xtgraphics.careermode) {
                                    if (checkpoints.stage != 7) {
                                        if (checkpoints.stage != 11) {
                                            if (checkpoints.stage != 13) {
                                                if (checkpoints.stage != 17) {
                                                    if (checkpoints.stage != 19) {
                                                        break Label_52237;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    fixstage = true;
                                }
                            }
                            Label_52452: {
                                if (this.py(conto.x / 100, checkpoints.x[this.fpnt[this.variable.whichfix]] / 100, conto.z / 100, checkpoints.z[this.fpnt[this.variable.whichfix]] / 100) < 2000) {
                                    if (this.trfix != 2) {
                                        if (fixstage) {
                                            break Label_52452;
                                        }
                                    }
                                    this.forget = false;
                                    this.actwait = 0;
                                    this.upwait = 0;
                                    this.turntyp = 2;
                                    this.delayturn = false;
                                    if (xtgraphics.careermode) {
                                        if (checkpoints.stage == 22) {
                                            this.hold = 20;
                                        }
                                        if (checkpoints.stage == 17) {
                                            if (madness.speed > 150.0f) {
                                                this.up = false;
                                                this.handb = true;
                                                this.down = true;
                                            }
                                        }
                                    }
                                    this.randtcnt = -1;
                                    this.acuracy = 0;
                                    this.rampp = 0;
                                    this.trfix = 3;
                                }
                            }
                            Label_52832: {
                                if (this.trfix != 3) {
                                    if (this.trfix != 4) {
                                        break Label_52832;
                                    }
                                }
                                if (xtgraphics.careermode) {
                                    Label_52571: {
                                        if (checkpoints.stage != 3) {
                                            if (checkpoints.stage != 8) {
                                                break Label_52571;
                                            }
                                        }
                                        if (this.py(conto.x / 100, checkpoints.fx[0] / 100, conto.z / 100, checkpoints.fz[0] / 100) < 2200) {
                                            this.hold = 25;
                                        }
                                    }
                                    Label_52630: {
                                        if (checkpoints.stage != 21) {
                                            if (checkpoints.stage != 18) {
                                                break Label_52630;
                                            }
                                        }
                                        if (madness.speed >= 300.0f) {
                                            this.up = false;
                                            this.down = true;
                                            this.handb = true;
                                        }
                                    }
                                    if (checkpoints.stage == 23) {
                                        if (i9 < this.fpnt[this.variable.whichfix]) {
                                            i9 = 144;
                                        }
                                        if (this.py(conto.x / 100, checkpoints.fx[0] / 100, conto.z / 100, checkpoints.fz[0] / 100) < 30000) {
                                            Label_52779: {
                                                if (madness.speed <= 430.0f) {
                                                    if (madness.speed <= 350.0f) {
                                                        break Label_52779;
                                                    }
                                                    if (madness.cn != 17) {
                                                        break Label_52779;
                                                    }
                                                }
                                                this.up = false;
                                                this.handb = true;
                                                this.down = true;
                                            }
                                            if (this.py(conto.x / 100, checkpoints.fx[0] / 100, conto.z / 100, checkpoints.fz[0] / 100) < 8500) {
                                                this.hold = 20;
                                            }
                                        }
                                    }
                                }
                            }
                            if (xtgraphics.careermode) {
                                if (checkpoints.stage == 17) {
                                    if (conto.fix) {
                                        this.trfix = 0;
                                        this.backfix = false;
                                    }
                                }
                                if (checkpoints.stage == 19) {
                                    if (conto.fix) {
                                        this.trfix = 0;
                                    }
                                }
                            }
                            if (this.trfix == 3) {
                                madness.nofocus = true;
                            }
                        }
                    }
                    this.wrongfloor = false;
                    if (xtgraphics.careermode) {
                        if (checkpoints.stage == 13) {
                            if (checkpoints.floor[i9] != xtgraphics.floor[madness.im]) {
                                this.wrongfloor = true;
                                this.gotofloor = checkpoints.floor[i9];
                            }
                        }
                    }
                    if (this.wrongfloor) {
                        for (int a5 = 0; a5 < checkpoints.n; ++a5) {
                            if (checkpoints.telefloor[a5] == this.gotofloor) {
                                if (checkpoints.floor[a5] == xtgraphics.floor[madness.im]) {
                                    i9 = a5;
                                }
                            }
                        }
                    }
                    if (this.turncnt <= this.randtcnt) {
                        ++this.turncnt;
                    }
                    else {
                        if (this.gowait) {
                            char c8 = '\0';
                            if (this.wtx - conto.x >= 0) {
                                c8 = '´';
                            }
                            this.pan = (int)('Z' + c8 + Math.atan((this.wtz - conto.z) / (double)(this.wtx - conto.x)) / 0.017453292519943295);
                        }
                        else {
                            char c9 = '\0';
                            if (checkpoints.x[i9] - conto.x >= 0) {
                                c9 = '´';
                            }
                            this.pan = (int)('Z' + c9 + Math.atan((checkpoints.z[i9] - conto.z) / (double)(checkpoints.x[i9] - conto.x)) / 0.017453292519943295);
                        }
                        this.turncnt = 0;
                        this.randtcnt = (int)(this.acuracy * this.m.random());
                    }
                    xtgraphics.findi[madness.im] = i9;
                }
                int j14 = conto.xz;
                if (this.zyinv) {
                    j14 += 180;
                }
                while (j14 < 0) {
                    j14 += 360;
                }
                while (j14 > 180) {
                    j14 -= 360;
                }
                while (this.pan < 0) {
                    this.pan += 360;
                }
                while (this.pan > 180) {
                    this.pan -= 360;
                }
                if (this.wall != -1) {
                    if (this.hold == 0) {
                        this.clrnce = 0;
                    }
                }
                if (this.hold == 0) {
                    if (Math.abs(j14 - this.pan) >= 180) {
                        if (Math.abs(j14 - this.pan) < 360 - this.clrnce) {
                            if (j14 >= this.pan) {
                                this.left = true;
                                this.lastl = true;
                            }
                            else {
                                this.right = true;
                                this.lastl = false;
                            }
                            if (Math.abs(j14 - this.pan) < 310) {
                                if (madness.speed > madness.swits[madness.cn][0]) {
                                    if (this.turntyp != 0) {
                                        if (this.turntyp == 1) {
                                            if (this.stuck == 0) {
                                                this.down = true;
                                            }
                                        }
                                        if (this.turntyp == 2) {
                                            this.handb = true;
                                        }
                                        if (!this.agressed) {
                                            this.up = false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else if (Math.abs(j14 - this.pan) > this.clrnce) {
                        if (j14 >= this.pan) {
                            this.right = true;
                            this.lastl = false;
                        }
                        else {
                            this.left = true;
                            this.lastl = true;
                        }
                        if (Math.abs(j14 - this.pan) > 50) {
                            if (madness.speed > madness.swits[madness.cn][0]) {
                                if (this.turntyp != 0) {
                                    if (this.turntyp == 1) {
                                        if (this.stuck == 0) {
                                            this.down = true;
                                        }
                                    }
                                    if (this.turntyp == 2) {
                                        this.handb = true;
                                    }
                                    if (!this.agressed) {
                                        this.up = false;
                                    }
                                }
                            }
                        }
                    }
                }
                if (checkpoints.stage == 14) {
                    if (this.wall != -1) {
                        if (xtgraphics.classicmode) {
                            Label_55020: {
                                if (trackers.dam[this.wall] != 0) {
                                    if (madness.pcleared != 45) {
                                        break Label_55020;
                                    }
                                }
                                this.wall = -1;
                            }
                            if (madness.pcleared == 58) {
                                if (checkpoints.opz[madness.im] < 36700) {
                                    this.wall = -1;
                                    this.hold = 0;
                                }
                            }
                        }
                    }
                }
                if (this.wall == -1) {
                    if (this.hold != 0) {
                        --this.hold;
                    }
                    this.stuck = 0;
                }
                else {
                    if (this.lwall == this.wall) {
                        if (!this.wlastl) {
                            this.right = true;
                        }
                        else {
                            this.left = true;
                        }
                    }
                    else {
                        if (!this.lastl) {
                            this.right = true;
                        }
                        else {
                            this.left = true;
                        }
                        this.wlastl = this.lastl;
                        this.lwall = this.wall;
                    }
                    int stucklim = 70;
                    if (xtgraphics.careermode) {
                        if (checkpoints.stage == 23) {
                            stucklim = 15;
                        }
                    }
                    if (this.stuck <= stucklim) {
                        this.down = false;
                        this.fewsecson = false;
                        ++this.stuck;
                        this.downuse = 0;
                    }
                    else {
                        ++this.downuse;
                        this.up = false;
                        if (this.downuse <= 5) {
                            this.down = false;
                            this.fewsecson = false;
                        }
                        else {
                            this.fewsecson = true;
                        }
                    }
                    if (trackers.dam[this.wall] == 0) {
                        this.hold = 0;
                    }
                    else {
                        byte byte0 = 1;
                        if (trackers.skd[this.wall] == 1) {
                            byte0 = 3;
                        }
                        this.hold += byte0;
                        if (this.hold > 10 * byte0) {
                            this.hold = 10 * byte0;
                        }
                    }
                    this.wall = -1;
                }
                if (!this.fewsecson) {
                    this.fewsecs = 0;
                }
                else {
                    ++this.fewsecs;
                    this.up = false;
                    if (this.fewsecs >= 40) {
                        this.down = false;
                        this.fewsecson = false;
                    }
                    else {
                        this.down = true;
                    }
                }
                this.apunch = 0;
                this.abboost = 0;
                for (int a = 0; a < xtgraphics.nplayers; ++a) {
                    if (this.avoidnlev[a] > 0) {
                        if (checkpoints.stage >= 5) {
                            if (checkpoints.dested[a] == 0) {
                                if (madness.im != a) {
                                    if (this.neverhit[a]) {
                                        if (this.py(conto.x / 100, checkpoints.opx[a] / 100, conto.z / 100, checkpoints.opz[a] / 100) < 1000 + this.avoidnlev[a]) {
                                            int j15 = conto.xz;
                                            if (this.zyinv) {
                                                j15 += 180;
                                            }
                                            while (j15 < 0) {
                                                j15 += 360;
                                            }
                                            while (j15 > 180) {
                                                j15 -= 360;
                                            }
                                            char c10 = '\0';
                                            if (checkpoints.opx[a] - conto.x >= 0) {
                                                c10 = '´';
                                            }
                                            int k15;
                                            for (k15 = (int)('Z' + c10 + Math.atan((checkpoints.opz[a] - conto.z) / (double)(checkpoints.opx[a] - conto.x)) / 0.017453292519943295); k15 < 0; k15 += 360) {}
                                            while (k15 > 180) {
                                                k15 -= 360;
                                            }
                                            int j16 = Math.abs(j15 - k15);
                                            if (j16 > 180) {
                                                j16 = Math.abs(j16 - 360);
                                            }
                                            if (j16 < 90) {
                                                this.wall = 0;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            this.fpnt[0] = this.variable.fixpoint[0];
            Label_60728: {
                if (xtgraphics.careermode) {
                    if (checkpoints.stage == 17) {
                        if (conto.x <= checkpoints.fx[0]) {
                            this.fpnt[0] = 14;
                        }
                        else {
                            this.fpnt[0] = 8;
                        }
                    }
                    int numfixes2 = 2;
                    if (checkpoints.stage == 7) {
                        numfixes2 = 4;
                    }
                    if (checkpoints.stage == 13) {
                        numfixes2 = 8;
                    }
                    if (checkpoints.stage != 7) {
                        if (checkpoints.stage != 11) {
                            if (checkpoints.stage != 13) {
                                break Label_60728;
                            }
                        }
                    }
                    for (int a = 0; a < numfixes2; ++a) {
                        this.fpnt[a] = this.variable.fixpoint[a];
                    }
                }
            }
            if (madness.hitmag * 100.0f / madness.maxmag[madness.cn] <= this.fixby) {
                this.needtofix = false;
            }
            else {
                this.needtofix = true;
            }
        }
    }
    
    public void reset(final CheckPoints checkpoints, final int i, final xtGraphics xt, final Madness madness, final Madness usermad) {
        this.pan = 0;
        this.attack = 0;
        this.acr = 0;
        this.afta = false;
        this.trfix = 0;
        this.setfixfloor = false;
        this.stuck = 0;
        this.waited = false;
        this.wrongfloor = false;
        this.gotofloor = 0;
        this.fewsecs = 0;
        this.fewsecson = false;
        this.delayturn = false;
        this.dontback = false;
        this.downuse = 0;
        this.acuracy = 0;
        this.abboost = 0;
        this.upwait = 0;
        this.switchspot = false;
        this.forget = false;
        this.bulistc = false;
        this.campcool = 0;
        this.staythere = 0;
        this.chkahead = 0;
        this.l1 = 0;
        this.backfix = false;
        this.l3 = 0;
        this.k5 = 0;
        this.campchk = -1;
        this.waitforuser = false;
        this.intercept = false;
        this.runbul = 0;
        this.abdelay = 0;
        this.revstart = 0;
        this.waitman = 0;
        this.oupnt = 0;
        this.gowait = false;
        this.needtofix = false;
        this.apunch = 0;
        this.exitattack = false;
        if (checkpoints.stage == 8 && xt.classicmode) {
            this.hold = 50;
        }
        if (checkpoints.stage == 10) {
            this.hold = 30;
        }
        if (checkpoints.stage == 11 && !xt.careermode) {
            if (i != 13 && i != 18 && i != 19) {
                this.hold = 35;
                this.revstart = 25;
            }
            else {
                this.hold = 5;
            }
            this.statusque = 0;
        }
        if (xt.careermode) {
            if (checkpoints.stage == 2 || checkpoints.stage == 3) {
                this.hold = 40;
            }
            if (checkpoints.stage == 5) {
                this.hold = 60;
            }
            if (checkpoints.stage == 7) {
                boolean afuckingracer = false;
                final int totalpoints = (usermad.level[usermad.cn] - 1) * 4 + xt.extpoints[usermad.cn];
                final int instrength = totalpoints / 8;
                if (usermad.aistrsp[usermad.cn] - usermad.level[usermad.cn] + 1 < instrength) {
                    afuckingracer = true;
                }
                if (xt.beastopponent[madness.im] && (xt.unlocked[1] == 7 || xt.hardstage) && madness.im > 2 && afuckingracer) {
                    this.revstart = 30;
                }
                if (madness.cn == 10 || madness.cn == 33) {
                    this.hold = 40;
                }
            }
            if (checkpoints.stage == 8 && i != 11 && !xt.beastopponent[madness.im]) {
                this.revstart = 30;
            }
            if (checkpoints.stage == 11) {
                boolean hard = false;
                if (xt.unlocked[1] == 11 || xt.hardstage) {
                    hard = true;
                }
                if (!hard && !this.bulistc) {
                    this.hold = 40;
                }
            }
            if (checkpoints.stage == 23 && (xt.unlocked[1] == 23 || xt.hardstage) && i != 17 && (madness.im == 2 || madness.im == 3)) {
                this.revstart = 30;
            }
        }
        if (checkpoints.stage == 12 && xt.classicmode) {
            if (i != 13) {
                this.hold = (int)(20.0f + 10.0f * this.m.random());
                this.revstart = (int)(10.0f + 10.0f * this.m.random());
            }
            else {
                this.hold = 5;
            }
            this.statusque = 0;
        }
        if (checkpoints.stage == 16) {
            this.hold = 20;
        }
        this.left = false;
        this.right = false;
        this.up = false;
        this.down = false;
        this.handb = false;
        this.lookback = 0;
        this.fixby = 80;
        this.arrace = false;
        for (int a = 0; a < 101; ++a) {
            this.avoidnlev[a] = 0;
            this.neverhit[a] = false;
        }
        if (xt.justcs == -1) {
            this.mutem = false;
        }
        this.mutes = false;
    }
    
    public Control(final Medium medium, final Contva contva) {
        this.hover = new boolean[5];
        this.opclick = new boolean[5];
        this.carsavedata = new boolean[3];
        this.campchk = -1;
        this.neverhit = new boolean[101];
        this.left = false;
        this.right = false;
        this.up = false;
        this.down = false;
        this.handb = false;
        this.setfixfloor = false;
        this.lookback = 0;
        this.enter = false;
        this.arrace = false;
        this.mutem = false;
        this.wrongfloor = false;
        this.gotofloor = 0;
        this.viewbot = new boolean[2];
        this.mutes = false;
        this.backfix = false;
        this.write = false;
        this.stuck = 0;
        this.needtofix = false;
        this.delayturn = false;
        this.pan = 0;
        this.attack = 0;
        this.downuse = 0;
        this.l1 = 0;
        this.staythere = 0;
        this.l3 = 0;
        this.k5 = 0;
        this.fewsecs = 0;
        this.fewsecson = false;
        this.intercept = false;
        this.dontback = false;
        this.waitforuser = false;
        this.campchk = -1;
        this.campcool = 0;
        this.chkahead = 0;
        this.acr = 0;
        this.afta = false;
        this.fpnt = new int[50];
        this.trfix = 0;
        this.forget = false;
        this.keylock = new boolean[7];
        this.numpress = new boolean[7];
        this.whichkeylock = -1;
        this.abdelay = 0;
        this.bulistc = false;
        this.runbul = 0;
        this.switchspot = false;
        this.acuracy = 0;
        this.waited = false;
        this.upwait = 0;
        this.agressed = false;
        this.skiplev = 1.0f;
        this.clrnce = 5;
        this.fixby = 80;
        this.rampp = 0;
        this.abboost = 0;
        this.turntyp = 0;
        this.aim = 0.0f;
        this.saftey = 30;
        this.perfection = false;
        this.mustland = 0.5f;
        this.usebounce = false;
        this.trickprf = 0.5f;
        this.stuntf = 0;
        this.zyinv = false;
        this.lastl = false;
        this.wlastl = false;
        this.hold = 0;
        this.wall = -1;
        this.lwall = -1;
        this.stcnt = 0;
        this.statusque = 0;
        this.turncnt = 0;
        this.randtcnt = 0;
        this.upcnt = 0;
        this.trickfase = 0;
        this.swat = 0;
        this.udcomp = false;
        this.lrcomp = false;
        this.udbare = false;
        this.lrbare = false;
        this.onceu = false;
        this.onced = false;
        this.oncel = false;
        this.oncer = false;
        this.lrdirect = 0;
        this.uddirect = 0;
        this.lrstart = 0;
        this.udstart = 0;
        this.oxy = 0;
        this.ozy = 0;
        this.flycnt = 0;
        this.lrswt = false;
        this.udswt = false;
        this.gowait = false;
        this.actwait = 0;
        this.cntrn = 0;
        this.revstart = 0;
        this.waitman = 0;
        this.oupnt = 0;
        this.wtz = 0;
        this.wtx = 0;
        this.frx = 0;
        this.frz = 0;
        this.frad = 0;
        this.apunch = 0;
        this.exitattack = false;
        this.avoidnlev = new int[101];
        this.m = medium;
        this.variable = contva;
    }
    
    public void falseo(final int justcs) {
        this.left = false;
        this.right = false;
        this.up = false;
        this.down = false;
        this.handb = false;
        this.lookback = 0;
        this.enter = false;
        this.arrace = false;
        this.needtofix = false;
        if (justcs == -1) {
            this.mutem = false;
        }
        this.write = false;
        this.mutes = false;
        for (int a = 0; a < 7; ++a) {
            this.keylock[a] = false;
            this.numpress[a] = false;
        }
        this.whichkeylock = -1;
    }
    
    public int pys(final int i, final int j, final int k, final int l) {
        return (int)Math.sqrt((i - j) * (i - j) + (k - l) * (k - l));
    }
    
    public int max(final int a, final int b) {
        if (a > b) {
            return a;
        }
        return b;
    }
    
    public int py(final int i, final int j, final int k, final int l) {
        return (i - j) * (i - j) + (k - l) * (k - l);
    }
}
