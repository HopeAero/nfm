// 
// Decompiled by Procyon v0.6.0
// 

public class Contva
{
    boolean[] freeze;
    boolean[] weaken;
    boolean[] swapped;
    boolean[] leeching;
    boolean[] vulnerable;
    boolean[] specon;
    boolean urgency;
    boolean[] biglead;
    int[] incatt;
    int[] offatt;
    int[] completed;
    boolean lotswasted;
    boolean[] needhelp;
    int[] fixpoint;
    int numfixes;
    int whichfix;
    boolean[] camping;
    boolean[] nearchk;
    int[] chkcircle;
    int[] slowdown;
    int[] slowrange;
    int[] moreslow;
    boolean[] opbackloops;
    boolean[] dontdistract;
    boolean[] dontstunt;
    boolean[] spdexception;
    boolean[] dontmiss;
    boolean[] hugelead;
    boolean[] layoff;
    int[] sharpturn;
    
    public Contva() {
        this.vulnerable = new boolean[101];
        this.freeze = new boolean[101];
        this.swapped = new boolean[101];
        this.leeching = new boolean[101];
        this.weaken = new boolean[101];
        this.specon = new boolean[101];
        this.completed = new int[101];
        this.sharpturn = new int[101];
        this.fixpoint = new int[50];
        this.numfixes = 0;
        this.lotswasted = false;
        this.spdexception = new boolean[101];
        this.urgency = false;
        this.biglead = new boolean[101];
        this.dontmiss = new boolean[101];
        this.needhelp = new boolean[101];
        this.whichfix = 0;
        this.camping = new boolean[101];
        this.chkcircle = new int[101];
        this.moreslow = new int[101];
        this.nearchk = new boolean[101];
        this.slowdown = new int[101];
        this.slowrange = new int[101];
        this.opbackloops = new boolean[101];
        this.dontdistract = new boolean[101];
        this.dontstunt = new boolean[101];
        this.hugelead = new boolean[101];
        this.layoff = new boolean[101];
    }
    
    public void resetfp() {
        for (int a = 0; a < 50; ++a) {
            this.fixpoint[a] = 0;
        }
        this.numfixes = 0;
        this.whichfix = 0;
    }
    
    public void reset() {
        for (int a = 0; a < 101; ++a) {
            this.vulnerable[a] = false;
            this.freeze[a] = false;
            this.swapped[a] = false;
            this.leeching[a] = false;
            this.weaken[a] = false;
            this.specon[a] = false;
            this.needhelp[a] = false;
            this.camping[a] = false;
            this.dontstunt[a] = false;
            this.nearchk[a] = false;
            this.sharpturn[a] = 0;
            this.opbackloops[a] = false;
            this.biglead[a] = false;
            this.completed[a] = 0;
            this.spdexception[a] = false;
            this.slowdown[a] = 240;
            this.slowrange[a] = 4000;
            this.chkcircle[a] = 0;
            this.moreslow[a] = 0;
            this.dontdistract[a] = false;
            this.dontmiss[a] = false;
            this.hugelead[a] = false;
            this.layoff[a] = false;
        }
        this.lotswasted = false;
        this.whichfix = 0;
        this.urgency = false;
    }
    
    public void sortvariables(final Madness madness, final CheckPoints checkpoints, final Control[] u, final boolean careermode, final int nplayers) {
        if (madness.frozen) {
            this.vulnerable[madness.im] = true;
            this.freeze[madness.im] = true;
        }
        else {
            this.freeze[madness.im] = false;
        }
        if (madness.redstr) {
            this.vulnerable[madness.im] = true;
            this.weaken[madness.im] = true;
        }
        else {
            this.weaken[madness.im] = false;
        }
        if (madness.strswap) {
            this.swapped[madness.im] = true;
            this.vulnerable[madness.im] = true;
        }
        else {
            this.swapped[madness.im] = false;
        }
        if (madness.leech) {
            this.leeching[madness.im] = true;
        }
        else {
            this.leeching[madness.im] = false;
        }
        if (!madness.frozen && !madness.redstr && !madness.strswap) {
            this.vulnerable[madness.im] = false;
        }
        if (madness.specialact) {
            this.specon[madness.im] = true;
        }
        else {
            this.specon[madness.im] = false;
        }
        for (int a = 0; a < nplayers; ++a) {
            if (checkpoints.pos[madness.im] == 0 && checkpoints.pos[a] == 1) {
                if (checkpoints.clear[madness.im] > checkpoints.clear[a] + 2) {
                    this.biglead[madness.im] = true;
                    if (checkpoints.clear[madness.im] > checkpoints.clear[a] + 6) {
                        this.hugelead[madness.im] = true;
                    }
                    else {
                        this.hugelead[madness.im] = false;
                    }
                }
                else {
                    this.biglead[madness.im] = false;
                    this.hugelead[madness.im] = false;
                }
            }
            else {
                this.biglead[madness.im] = false;
                this.hugelead[madness.im] = false;
            }
        }
        this.completed[madness.im] = checkpoints.clear[madness.im] * 100 / (checkpoints.nsp * checkpoints.nlaps);
        if (checkpoints.wasted >= nplayers - 5) {
            this.lotswasted = true;
        }
        else {
            this.lotswasted = false;
        }
        if (u[madness.im].trfix >= 2 || madness.dest) {
            this.needhelp[madness.im] = true;
        }
        else {
            this.needhelp[madness.im] = false;
        }
        final int[] bitmore = new int[101];
        if (madness.beast[madness.im]) {
            bitmore[madness.im] = 1000;
        }
        else {
            bitmore[madness.im] = 0;
        }
        if (madness.swits[madness.cn][2] >= 340 && madness.swits[madness.cn][2] < 380) {
            this.slowdown[madness.im] = 180;
        }
        if (madness.swits[madness.cn][2] >= 380 && madness.swits[madness.cn][2] < 420) {
            this.slowdown[madness.im] = 170;
        }
        if (madness.swits[madness.cn][2] >= 420) {
            this.slowdown[madness.im] = 150;
        }
        if (madness.swits[madness.cn][2] >= 340) {
            this.slowrange[madness.im] = 2500 + bitmore[madness.im];
        }
        if (this.nearchk[madness.im]) {
            final int[] chkcircle = this.chkcircle;
            final int im = madness.im;
            ++chkcircle[im];
        }
        else {
            this.chkcircle[madness.im] = 0;
        }
        if (this.chkcircle[madness.im] > 120) {
            final int[] moreslow = this.moreslow;
            final int im2 = madness.im;
            ++moreslow[im2];
        }
        else {
            this.moreslow[madness.im] = 0;
        }
        if (this.moreslow[madness.im] > 80) {
            this.moreslow[madness.im] = 80;
        }
        if (careermode) {
            if (this.dontmiss[madness.im]) {
                int range = 9000;
                if (checkpoints.stage >= 23) {
                    range = 5000;
                }
                if (checkpoints.stage == 19) {
                    if (madness.cn == 17 || madness.shadowcar) {
                        int extrarange = 0;
                        if (madness.specialact) {
                            extrarange = 1300;
                        }
                        range = 3200 + extrarange;
                    }
                    else {
                        range = 6000;
                    }
                }
                this.slowrange[madness.im] = range;
                int slowby = 150;
                if (checkpoints.stage >= 21) {
                    slowby = 210;
                }
                if (checkpoints.stage == 9) {
                    range = 3000;
                    slowby = 240;
                }
                this.slowdown[madness.im] = slowby;
            }
            if (checkpoints.stage == 7 || checkpoints.stage == 8) {
                this.slowrange[madness.im] = 0;
            }
            if (checkpoints.stage == 22 && madness.im == nplayers - 1) {
                final float health = 100.0f * madness.hitmag / madness.maxmag[madness.cn];
                if (health > 85.0f) {
                    this.layoff[madness.im] = true;
                }
                else {
                    this.layoff[madness.im] = false;
                }
            }
            if (checkpoints.stage == 11 || checkpoints.stage == 12) {
                if (madness.cn == 10) {
                    this.spdexception[madness.im] = true;
                }
                else {
                    this.spdexception[madness.im] = false;
                }
                this.slowdown[madness.im] = 180;
                this.slowrange[madness.im] = 4500;
            }
            if (checkpoints.stage == 13 && madness.cn == 13) {
                this.spdexception[madness.im] = true;
                this.slowdown[madness.im] = 200;
                this.slowrange[madness.im] = 4000;
            }
            if (checkpoints.stage == 14) {
                if (madness.cn == 14) {
                    if (madness.power == 98.0f && madness.pcleared == 321) {
                        this.opbackloops[madness.im] = true;
                    }
                    else {
                        this.opbackloops[madness.im] = false;
                    }
                }
                if ((madness.pcleared == 130 && !madness.specialact) || madness.pcleared == 221 || madness.pcleared == 261 || madness.pcleared == 248) {
                    if (madness.point < madness.pcleared) {
                        this.dontdistract[madness.im] = true;
                    }
                    else {
                        this.dontdistract[madness.im] = false;
                    }
                }
                else {
                    this.dontdistract[madness.im] = false;
                }
            }
        }
    }
}
