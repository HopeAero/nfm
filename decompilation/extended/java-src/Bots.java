// 
// Decompiled by Procyon v0.6.0
// 

public class Bots
{
    int timer;
    boolean[][] upgo;
    boolean[][] leftgo;
    boolean[][] rightgo;
    boolean[][] downgo;
    boolean[][] handbgo;
    boolean[] oneloaded;
    int[][] botoffset;
    boolean writecode;
    boolean doneload;
    boolean[] botbreak;
    int[] specialtimer;
    boolean resetonce;
    int[] whichbotcode;
    String[] codeup;
    String[] codedown;
    String[] codeleft;
    String[] coderight;
    String[] codehandb;
    
    public Bots() {
        this.codeup = new String[20000];
        this.codedown = new String[20000];
        this.codeleft = new String[20000];
        this.coderight = new String[20000];
        this.codehandb = new String[20000];
        this.timer = 0;
        this.whichbotcode = new int[101];
        this.specialtimer = new int[101];
        this.writecode = false;
        this.doneload = false;
        this.botbreak = new boolean[101];
        this.oneloaded = new boolean[101];
        this.upgo = new boolean[20000][101];
        this.leftgo = new boolean[20000][101];
        this.rightgo = new boolean[20000][101];
        this.handbgo = new boolean[20000][101];
        this.downgo = new boolean[20000][101];
        this.botoffset = new int[10][101];
        this.resetonce = false;
    }
    
    public void reset() {
        this.timer = 0;
        this.doneload = false;
        for (int a = 0; a < 101; ++a) {
            this.specialtimer[a] = 0;
            this.whichbotcode[a] = 0;
            this.oneloaded[a] = false;
            this.botbreak[a] = false;
            for (int b = 0; b < 10; ++b) {
                this.botoffset[b][a] = 0;
            }
        }
        for (int a = 0; a < 20000; ++a) {
            for (int b = 0; b < 101; ++b) {
                this.upgo[a][b] = false;
                this.leftgo[a][b] = false;
                this.rightgo[a][b] = false;
                this.downgo[a][b] = false;
                this.handbgo[a][b] = false;
            }
            this.codeup[a] = "";
            this.codedown[a] = "";
            this.codeleft[a] = "";
            this.coderight[a] = "";
            this.codehandb[a] = "";
        }
        this.writecode = false;
    }
    
    public void resettimer() {
        this.timer = 0;
        for (int a = 0; a < 101; ++a) {
            this.specialtimer[a] = 0;
            this.whichbotcode[a] = 0;
            this.botbreak[a] = false;
        }
    }
    
    public void runbots(final Control[] control, final boolean usebots, final int i, final boolean timerflag) {
        int whichtimer = this.timer;
        if (timerflag) {
            whichtimer = this.specialtimer[i];
        }
        if (usebots) {
            control[i].up = false;
            control[i].down = false;
            control[i].left = false;
            control[i].right = false;
            control[i].handb = false;
            if (this.upgo[whichtimer][i]) {
                control[i].up = true;
            }
            if (this.downgo[whichtimer][i]) {
                control[i].down = true;
            }
            if (this.leftgo[whichtimer][i]) {
                control[i].left = true;
            }
            if (this.rightgo[whichtimer][i]) {
                control[i].right = true;
            }
            if (this.handbgo[whichtimer][i]) {
                control[i].handb = true;
            }
        }
    }
}
