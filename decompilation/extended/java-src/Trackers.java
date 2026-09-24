// 
// Decompiled by Procyon v0.6.0
// 

public class Trackers
{
    String[] sequ;
    int[] x;
    int[] y;
    int[] z;
    int[] xy;
    int[] zy;
    int[] skd;
    int sx;
    int sz;
    int ncx;
    int ncz;
    int[] dam;
    boolean[] notwall;
    int[][][] sect;
    boolean tracksReady;
    int[][] oc;
    int[][] c;
    int[] radx;
    int[] radz;
    int[] rady;
    int nt;
    
    public Trackers() {
        this.sequ = new String[] { "Access Denied !", "This game will not run under this http:/ loaction:", "Please contact radicalplay.com for details." };
        this.sx = 0;
        this.sz = 0;
        this.ncx = 0;
        this.ncz = 0;
        this.sect = null;
        this.x = new int[67000];
        this.y = new int[67000];
        this.z = new int[67000];
        this.xy = new int[67000];
        this.zy = new int[67000];
        this.skd = new int[67000];
        this.dam = new int[67000];
        this.notwall = new boolean[67000];
        this.tracksReady = false;
        this.oc = new int[67000][3];
        this.c = new int[67000][3];
        this.radx = new int[67000];
        this.radz = new int[67000];
        this.rady = new int[67000];
        this.nt = 0;
    }
}
