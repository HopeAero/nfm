// 
// Decompiled by Procyon v0.6.0
// 

public class CheckPoints
{
    int[] x;
    int[] z;
    int[] y;
    int[] typ;
    int pcs;
    int nsp;
    int n;
    int[] fx;
    int[] fz;
    int[] fy;
    boolean[] roted;
    boolean[] special;
    int fn;
    int stage;
    int nlaps;
    String name;
    int[] pos;
    int[] clear;
    int[] dested;
    int wasted;
    boolean haltall;
    int pcleared;
    int[] opx;
    int[] opz;
    int[] onscreen;
    int[] omxz;
    int catchfin;
    int postwo;
    int[] rotation;
    int[] chkcode;
    int[] telefloor;
    int[] floor;
    
    public CheckPoints() {
        this.pos = new int[] { 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100 };
        this.x = new int[2000];
        this.z = new int[2000];
        this.y = new int[2000];
        this.telefloor = new int[2000];
        this.floor = new int[2000];
        this.typ = new int[2000];
        this.rotation = new int[2000];
        this.chkcode = new int[2000];
        this.pcs = 0;
        this.nsp = 0;
        this.n = 0;
        this.fx = new int[50];
        this.fz = new int[50];
        this.fy = new int[50];
        this.roted = new boolean[50];
        this.special = new boolean[50];
        this.fn = 0;
        this.stage = 1;
        this.nlaps = 0;
        this.name = "hogan rewish";
        this.clear = new int[101];
        this.dested = new int[101];
        this.wasted = 0;
        this.haltall = false;
        this.pcleared = 0;
        this.opx = new int[101];
        this.opz = new int[101];
        this.onscreen = new int[101];
        this.omxz = new int[101];
        this.catchfin = 0;
        this.postwo = 0;
    }
    
    public void checkstat(final Madness[] amadness, final ContO[] aconto, final Record record, final xtGraphics xtgraphics) {
        if (!this.haltall) {
            this.pcleared = amadness[0].pcleared;
            int i = 0;
            do {
                this.pos[i] = 0;
                this.onscreen[i] = aconto[i].dist;
                this.opx[i] = aconto[i].x;
                this.opz[i] = aconto[i].z;
                this.omxz[i] = amadness[i].mxz;
                if (this.dested[i] == 0 || (xtgraphics.careermode && this.stage == 20)) {
                    this.clear[i] = amadness[i].clear;
                }
                else {
                    this.clear[i] = -1;
                }
            } while (++i < xtgraphics.nplayers);
            i = 0;
            do {
                for (int l = i + 1; l < xtgraphics.nplayers; ++l) {
                    if (this.clear[i] != this.clear[l]) {
                        if (this.clear[i] < this.clear[l]) {
                            final int[] pos = this.pos;
                            final int n = i;
                            ++pos[n];
                        }
                        else {
                            final int[] pos2 = this.pos;
                            final int n2 = l;
                            ++pos2[n2];
                        }
                    }
                    else {
                        int j1;
                        j1 = amadness[i].pcleared + 1;
                        while (this.typ[j1] <= 0) {  // procyon moved `j1 = 0` out of this if into the for-update, an infinite loop; control flow checked against javap
                            if (++j1 == this.n) {
                                j1 = 0;
                            }
                        }
                        if (this.py(aconto[i].x / 100, this.x[j1] / 100, aconto[i].z / 100, this.z[j1] / 100) > this.py(aconto[l].x / 100, this.x[j1] / 100, aconto[l].z / 100, this.z[j1] / 100)) {
                            final int[] pos3 = this.pos;
                            final int n3 = i;
                            ++pos3[n3];
                        }
                        else {
                            final int[] pos4 = this.pos;
                            final int n4 = l;
                            ++pos4[n4];
                        }
                    }
                }
            } while (++i < xtgraphics.nplayers);
            if (this.stage > 2) {
                for (int k = 0; k < xtgraphics.nplayers; ++k) {
                    if (this.clear[k] == this.nlaps * this.nsp && this.pos[k] == 0) {
                        if (k == 0) {
                            for (int i2 = 0; i2 < xtgraphics.nplayers; ++i2) {
                                if (this.pos[i2] == 1) {
                                    this.postwo = i2;
                                }
                            }
                            if (this.py(this.opx[0] / 100, this.opx[this.postwo] / 100, this.opz[0] / 100, this.opz[this.postwo] / 100) < 14000 && this.clear[0] - this.clear[this.postwo] == 1) {
                                this.catchfin = 30;
                            }
                        }
                        else if (this.pos[0] == 1 && this.py(this.opx[0] / 100, this.opx[k] / 100, this.opz[0] / 100, this.opz[k] / 100) < 14000 && this.clear[k] - this.clear[0] == 1) {
                            this.catchfin = 30;
                            this.postwo = k;
                        }
                    }
                }
            }
        }
        this.wasted = 0;
        int m = 1;
        do {
            if (amadness[m].dest || amadness[m].fakedest) {
                ++this.wasted;
            }
        } while (++m < xtgraphics.nplayers);
        if (this.catchfin != 0) {
            --this.catchfin;
            if (this.catchfin == 0) {
                record.cotchinow(this.postwo, xtgraphics.nplayers);
                record.closefinish = this.pos[0] + 1;
            }
        }
    }
    
    public int py(final int i, final int j, final int k, final int l) {
        return (i - j) * (i - j) + (k - l) * (k - l);
    }
}
