// 
// Decompiled by Procyon v0.6.0
// 

public class Wheels
{
    int ground;
    int mast;
    int[] rc;
    float size;
    float depth;
    
    public Wheels() {
        this.rc = new int[] { 120, 120, 120 };
        this.size = 2.0f;
        this.depth = 3.0f;
        this.ground = 0;
        this.mast = 0;
    }
    
    public void setrims(final int i, final int j, final int k, final int l, final int i1) {
        this.rc[0] = i;
        this.rc[1] = j;
        this.rc[2] = k;
        this.size = l / 10.0f;
        this.depth = i1 / 10.0f;
    }
    
    public void make(final Medium medium, final Trackers trackers, final Plane[] aplane, int i, final int j, final int k, final int l, final int i1, final int j1, final int k1, final int l1) {
        final int[] ai = new int[16];
        final int[] ai2 = new int[16];
        final int[] ai3 = new int[16];
        final int[] ai4 = { 45, 45, 45 };
        int i2 = 0;
        final float f = j1 / 10.0f;
        final float f2 = k1 / 10.0f;
        if (i1 == 11) {
            i2 = (int)(j + 4.0f * f);
        }
        byte byte0 = -1;
        if (j < 0) {
            byte0 = 1;
        }
        int j2 = 0;
        do {
            ai[j2] = (int)(j - 4.0f * f);
        } while (++j2 < 16);
        ai2[0] = (int)(k - 12.0f * f2);
        ai3[0] = (int)(l + 5.0f * f2);
        ai2[1] = (int)(k - 12.0f * f2);
        ai3[1] = (int)(l - 5.0f * f2);
        ai2[2] = (int)(k - 5.0f * f2);
        ai3[2] = (int)(l - 12.0f * f2);
        ai2[3] = (int)(k + 5.0f * f2);
        ai3[3] = (int)(l - 12.0f * f2);
        ai2[4] = (int)(k + 12.0f * f2);
        ai3[4] = (int)(l - 5.0f * f2);
        ai2[5] = (int)(k + 12.0f * f2);
        ai3[5] = (int)(l + 5.0f * f2);
        ai2[6] = (int)(k + 5.0f * f2);
        ai3[6] = (int)(l + 12.0f * f2);
        ai2[7] = (int)(k - 5.0f * f2);
        ai3[7] = (int)(l + 12.0f * f2);
        ai2[8] = k;
        ai3[8] = (int)(l + 10.0f * this.size);
        ai2[9] = (int)(k + 8.66 * this.size);
        ai3[9] = (int)(l + 5.0f * this.size);
        ai2[10] = (int)(k + 8.66 * this.size);
        ai3[10] = (int)(l - 5.0f * this.size);
        ai2[11] = k;
        ai3[11] = (int)(l - 10.0f * this.size);
        ai2[12] = (int)(k - 8.66 * this.size);
        ai3[12] = (int)(l - 5.0f * this.size);
        ai2[13] = (int)(k - 8.66 * this.size);
        ai3[13] = (int)(l + 5.0f * this.size);
        ai2[14] = k;
        ai3[14] = (int)(l + 10.0f * this.size);
        ai2[15] = (int)(k - 5.0f * f2);
        ai3[15] = (int)(l + 12.0f * f2);
        aplane[i] = new Plane(medium, trackers, ai, ai3, ai2, 16, ai4, 0, l1, 0, i2, k, l, 7, 0, false, 0, false);
        ++this.mast;
        aplane[i].master = this.mast;
        ++i;
        ai[2] = (int)(j - this.depth * f);
        ai2[2] = k;
        ai3[2] = l;
        j2 = -16;
        if (l1 == 21) {
            j2 = -17;
        }
        ai2[0] = k;
        ai3[0] = (int)(l + 10.0f * this.size);
        ai2[1] = (int)(k + 8.66 * this.size);
        ai3[1] = (int)(l + 5.0f * this.size);
        aplane[i] = new Plane(medium, trackers, ai, ai3, ai2, 3, this.rc, 0, j2, 0, i2, k, l, 7, 0, false, 0, false);
        ++i;
        ai2[0] = (int)(k + 8.66 * this.size);
        ai3[0] = (int)(l + 5.0f * this.size);
        ai2[1] = (int)(k + 8.66 * this.size);
        ai3[1] = (int)(l - 5.0f * this.size);
        aplane[i] = new Plane(medium, trackers, ai, ai3, ai2, 3, this.rc, 0, j2, 0, i2, k, l, 7, 0, false, 0, false);
        ++i;
        ai2[0] = (int)(k + 8.66 * this.size);
        ai3[0] = (int)(l - 5.0f * this.size);
        ai2[1] = k;
        ai3[1] = (int)(l - 10.0f * this.size);
        aplane[i] = new Plane(medium, trackers, ai, ai3, ai2, 3, this.rc, 0, j2, 0, i2, k, l, 7, 0, false, 0, false);
        ++i;
        ai2[0] = k;
        ai3[0] = (int)(l - 10.0f * this.size);
        ai2[1] = (int)(k - 8.66 * this.size);
        ai3[1] = (int)(l - 5.0f * this.size);
        aplane[i] = new Plane(medium, trackers, ai, ai3, ai2, 3, this.rc, 0, j2, 0, i2, k, l, 7, 0, false, 0, false);
        ++i;
        ai2[0] = (int)(k - 8.66 * this.size);
        ai3[0] = (int)(l - 5.0f * this.size);
        ai2[1] = (int)(k - 8.66 * this.size);
        ai3[1] = (int)(l + 5.0f * this.size);
        aplane[i] = new Plane(medium, trackers, ai, ai3, ai2, 3, this.rc, 0, j2, 0, i2, k, l, 7, 0, false, 0, false);
        ++i;
        ai2[0] = (int)(k - 8.66 * this.size);
        ai3[0] = (int)(l + 5.0f * this.size);
        ai2[1] = k;
        ai3[1] = (int)(l + 10.0f * this.size);
        aplane[i] = new Plane(medium, trackers, ai, ai3, ai2, 3, this.rc, 0, j2, 0, i2, k, l, 7, 0, false, 0, false);
        ++i;
        ai[0] = (int)(j - 4.0f * f);
        ai2[0] = (int)(k - 12.0f * f2);
        ai3[0] = (int)(l + 5.0f * f2);
        ai[1] = (int)(j - 4.0f * f);
        ai2[1] = (int)(k - 12.0f * f2);
        ai3[1] = (int)(l - 5.0f * f2);
        ai[2] = (int)(j + 4.0f * f);
        ai2[2] = (int)(k - 12.0f * f2);
        ai3[2] = (int)(l - 5.0f * f2);
        ai[3] = (int)(j + 4.0f * f);
        ai2[3] = (int)(k - 12.0f * f2);
        ai3[3] = (int)(l + 5.0f * f2);
        aplane[i] = new Plane(medium, trackers, ai, ai3, ai2, 4, ai4, 0, l1, -1 * byte0, i2, k, l, 7, 0, false, 0, false);
        ++i;
        ai[0] = (int)(j - 4.0f * f);
        ai2[0] = (int)(k - 5.0f * f2);
        ai3[0] = (int)(l - 12.0f * f2);
        ai[1] = (int)(j - 4.0f * f);
        ai2[1] = (int)(k - 12.0f * f2);
        ai3[1] = (int)(l - 5.0f * f2);
        ai[2] = (int)(j + 4.0f * f);
        ai2[2] = (int)(k - 12.0f * f2);
        ai3[2] = (int)(l - 5.0f * f2);
        ai[3] = (int)(j + 4.0f * f);
        ai2[3] = (int)(k - 5.0f * f2);
        ai3[3] = (int)(l - 12.0f * f2);
        aplane[i] = new Plane(medium, trackers, ai, ai3, ai2, 4, ai4, 0, l1, 1 * byte0, i2, k, l, 7, 0, false, 0, false);
        ++i;
        ai[0] = (int)(j - 4.0f * f);
        ai2[0] = (int)(k - 5.0f * f2);
        ai3[0] = (int)(l - 12.0f * f2);
        ai[1] = (int)(j - 4.0f * f);
        ai2[1] = (int)(k + 5.0f * f2);
        ai3[1] = (int)(l - 12.0f * f2);
        ai[2] = (int)(j + 4.0f * f);
        ai2[2] = (int)(k + 5.0f * f2);
        ai3[2] = (int)(l - 12.0f * f2);
        ai[3] = (int)(j + 4.0f * f);
        ai2[3] = (int)(k - 5.0f * f2);
        ai3[3] = (int)(l - 12.0f * f2);
        aplane[i] = new Plane(medium, trackers, ai, ai3, ai2, 4, ai4, 0, l1, -1 * byte0, i2, k, l, 7, 0, false, 0, false);
        ++i;
        ai[0] = (int)(j - 4.0f * f);
        ai2[0] = (int)(k + 12.0f * f2);
        ai3[0] = (int)(l - 5.0f * f2);
        ai[1] = (int)(j - 4.0f * f);
        ai2[1] = (int)(k + 5.0f * f2);
        ai3[1] = (int)(l - 12.0f * f2);
        ai[2] = (int)(j + 4.0f * f);
        ai2[2] = (int)(k + 5.0f * f2);
        ai3[2] = (int)(l - 12.0f * f2);
        ai[3] = (int)(j + 4.0f * f);
        ai2[3] = (int)(k + 12.0f * f2);
        ai3[3] = (int)(l - 5.0f * f2);
        aplane[i] = new Plane(medium, trackers, ai, ai3, ai2, 4, ai4, 0, l1, 1 * byte0, i2, k, l, 7, 0, false, 0, false);
        ++i;
        ai[0] = (int)(j - 4.0f * f);
        ai2[0] = (int)(k + 12.0f * f2);
        ai3[0] = (int)(l - 5.0f * f2);
        ai[1] = (int)(j - 4.0f * f);
        ai2[1] = (int)(k + 12.0f * f2);
        ai3[1] = (int)(l + 5.0f * f2);
        ai[2] = (int)(j + 4.0f * f);
        ai2[2] = (int)(k + 12.0f * f2);
        ai3[2] = (int)(l + 5.0f * f2);
        ai[3] = (int)(j + 4.0f * f);
        ai2[3] = (int)(k + 12.0f * f2);
        ai3[3] = (int)(l - 5.0f * f2);
        aplane[i] = new Plane(medium, trackers, ai, ai3, ai2, 4, ai4, 0, l1, -1 * byte0, i2, k, l, 7, 0, false, 0, false);
        ++i;
        if (this.ground < (int)(k + 12.0f * f2 + 1.0f)) {
            this.ground = (int)(k + 12.0f * f2 + 1.0f);
        }
        ai[0] = (int)(j - 4.0f * f);
        ai2[0] = (int)(k + 5.0f * f2);
        ai3[0] = (int)(l + 12.0f * f2);
        ai[1] = (int)(j - 4.0f * f);
        ai2[1] = (int)(k + 12.0f * f2);
        ai3[1] = (int)(l + 5.0f * f2);
        ai[2] = (int)(j + 4.0f * f);
        ai2[2] = (int)(k + 12.0f * f2);
        ai3[2] = (int)(l + 5.0f * f2);
        ai[3] = (int)(j + 4.0f * f);
        ai2[3] = (int)(k + 5.0f * f2);
        ai3[3] = (int)(l + 12.0f * f2);
        aplane[i] = new Plane(medium, trackers, ai, ai3, ai2, 4, ai4, 0, l1, 1 * byte0, i2, k, l, 7, 0, false, 0, false);
        ++i;
        ai[0] = (int)(j - 4.0f * f);
        ai2[0] = (int)(k + 5.0f * f2);
        ai3[0] = (int)(l + 12.0f * f2);
        ai[1] = (int)(j - 4.0f * f);
        ai2[1] = (int)(k - 5.0f * f2);
        ai3[1] = (int)(l + 12.0f * f2);
        ai[2] = (int)(j + 4.0f * f);
        ai2[2] = (int)(k - 5.0f * f2);
        ai3[2] = (int)(l + 12.0f * f2);
        ai[3] = (int)(j + 4.0f * f);
        ai2[3] = (int)(k + 5.0f * f2);
        ai3[3] = (int)(l + 12.0f * f2);
        aplane[i] = new Plane(medium, trackers, ai, ai3, ai2, 4, ai4, 0, l1, -1 * byte0, i2, k, l, 7, 0, false, 0, false);
        ++i;
        ai[0] = (int)(j - 4.0f * f);
        ai2[0] = (int)(k - 12.0f * f2);
        ai3[0] = (int)(l + 5.0f * f2);
        ai[1] = (int)(j - 4.0f * f);
        ai2[1] = (int)(k - 5.0f * f2);
        ai3[1] = (int)(l + 12.0f * f2);
        ai[2] = (int)(j + 4.0f * f);
        ai2[2] = (int)(k - 5.0f * f2);
        ai3[2] = (int)(l + 12.0f * f2);
        ai[3] = (int)(j + 4.0f * f);
        ai2[3] = (int)(k - 12.0f * f2);
        ai3[3] = (int)(l + 5.0f * f2);
        aplane[i] = new Plane(medium, trackers, ai, ai3, ai2, 4, ai4, 0, l1, 1 * byte0, i2, k, l, 7, 0, false, 0, false);
        ++i;
    }
}
