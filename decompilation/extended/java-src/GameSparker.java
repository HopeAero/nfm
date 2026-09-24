import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.zip.ZipOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.awt.RenderingHints;
import java.util.Date;
import java.awt.Color;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.zip.ZipEntry;
import java.io.InputStream;
import java.util.zip.ZipInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.net.URL;
import java.awt.image.ImageObserver;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.6.0
// 

public class GameSparker extends Applet implements Runnable
{
    CheckPoints cp;
    Graphics2D rd;
    Graphics sg;
    Image offImage;
    Thread gamer;
    Control[] u;
    int mouses;
    int xm;
    int failed;
    int ym;
    boolean lostfcs;
    boolean exwist;
    int nob;
    int notb;
    boolean autosave;
    int view;
    int fps;
    long frametimer;
    boolean contception;
    boolean readfromtxt;
    
    @Override
    public boolean keyDown(final Event event, final int i) {
        if (!this.exwist) {
            if (i == 1004) {
                if (!this.contception) {
                    this.u[0].up = true;
                }
                if (this.u[0].upalt) {
                    this.u[0].upalt = false;
                }
                else {
                    this.u[0].upalt = true;
                }
            }
            if (i == 1005) {
                if (!this.contception) {
                    this.u[0].down = true;
                }
                if (this.u[0].downalt) {
                    this.u[0].downalt = false;
                }
                else {
                    this.u[0].downalt = true;
                }
            }
            if (i == 1007) {
                if (!this.contception) {
                    this.u[0].right = true;
                }
                if (this.u[0].rightalt) {
                    this.u[0].rightalt = false;
                }
                else {
                    this.u[0].rightalt = true;
                }
            }
            if (i == 1006) {
                if (!this.contception) {
                    this.u[0].left = true;
                }
                if (this.u[0].leftalt) {
                    this.u[0].leftalt = false;
                }
                else {
                    this.u[0].leftalt = true;
                }
            }
            if (i == 32) {
                if (!this.contception) {
                    this.u[0].handb = true;
                }
                if (this.u[0].handbalt) {
                    this.u[0].handbalt = false;
                }
                else {
                    this.u[0].handbalt = true;
                }
            }
            for (int a = 49; a < 56; ++a) {
                if (i == a) {
                    if (this.u[0].keylock[a - 49]) {
                        this.u[0].keylock[a - 49] = false;
                        this.u[0].whichkeylock = -1;
                    }
                    else {
                        this.u[0].keylock[a - 49] = true;
                        this.u[0].whichkeylock = a - 49;
                    }
                    this.u[0].numpress[a - 49] = true;
                }
                else {
                    this.u[0].numpress[a - 49] = false;
                }
            }
            if (i == 120 || i == 88) {
                this.u[0].lookback = -1;
            }
            if (i == 122 || i == 90) {
                this.u[0].lookback = 1;
            }
            if (i == 10 || i == 80 || i == 112 || i == 27) {
                if (this.u[0].enteralt) {
                    this.u[0].enteralt = false;
                }
                else {
                    this.u[0].enteralt = true;
                }
                if (!this.contception) {
                    this.u[0].enter = true;
                }
            }
            if (i == 77 || i == 109) {
                if (this.u[0].mutem) {
                    this.u[0].mutem = false;
                }
                else {
                    this.u[0].mutem = true;
                }
            }
            if (i == 78 || i == 110) {
                if (this.u[0].mutes) {
                    this.u[0].mutes = false;
                }
                else {
                    this.u[0].mutes = true;
                }
            }
            if (i == 97 || i == 65) {
                if (this.u[0].arrace) {
                    this.u[0].arrace = false;
                }
                else {
                    this.u[0].arrace = true;
                }
            }
            if (i == 98 || i == 66) {
                if (this.u[0].write) {
                    this.u[0].write = false;
                }
                else {
                    this.u[0].write = true;
                }
            }
            for (int a = 0; a < 2; ++a) {
                if (i == 111 + a || i == 79 + a) {
                    final boolean b = this.u[0].viewbot[a];
                }
            }
            if (i == 115 || i == 83) {
                if (this.u[0].spatk) {
                    this.u[0].spatk = false;
                }
                else {
                    this.u[0].spatk = true;
                }
            }
            if (i == 100 || i == 68) {
                if (this.u[0].swap) {
                    this.u[0].swap = false;
                }
                else {
                    this.u[0].swap = true;
                }
            }
            if (i == 118 || i == 86) {
                ++this.view;
                if (this.view == 2) {
                    this.view = 0;
                }
            }
        }
        return false;
    }
    
    @Override
    public boolean keyUp(final Event event, final int i) {
        if (!this.exwist) {
            if (i == 1004) {
                this.u[0].up = false;
            }
            if (i == 1005) {
                this.u[0].down = false;
            }
            if (i == 1007) {
                this.u[0].right = false;
            }
            if (i == 1006) {
                this.u[0].left = false;
            }
            if (i == 32) {
                this.u[0].handb = false;
            }
            if (i == 120 || i == 88 || i == 122 || i == 90) {
                this.u[0].lookback = 0;
            }
        }
        return false;
    }
    
    @Override
    public boolean mouseDown(final Event event, final int i, final int j) {
        if (!this.exwist && this.mouses == 0) {
            this.xm = i;
            this.ym = j;
            this.mouses = 1;
        }
        return false;
    }
    
    @Override
    public boolean mouseMove(final Event event, final int i, final int j) {
        if (!this.exwist && !this.lostfcs) {
            this.xm = i;
            this.ym = j;
        }
        return false;
    }
    
    @Override
    public void stop() {
        if (this.exwist) {
            System.gc();
            if (this.gamer != null) {
                this.gamer = null;
            }
        }
        this.exwist = true;
    }
    
    @Override
    public boolean lostFocus(final Event event, final Object obj) {
        if (!this.exwist && !this.lostfcs) {
            this.lostfcs = true;
            this.mouses = 0;
            this.setCursor(new Cursor(0));
        }
        return false;
    }
    
    @Override
    public boolean gotFocus(final Event event, final Object obj) {
        if (!this.exwist && this.lostfcs) {
            this.lostfcs = false;
        }
        return false;
    }
    
    public String getstring(final String s, final String s1, final int i) {
        int k = 0;
        String s2 = "";
        for (int j = s.length() + 1; j < s1.length(); ++j) {
            final String s3 = new StringBuilder().append(s1.charAt(j)).toString();
            if (s3.equals(",") || s3.equals(")")) {
                ++k;
                ++j;
            }
            if (k == i) {
                s2 = String.valueOf(s2) + s1.charAt(j);
            }
        }
        return s2;
    }
    
    public int getint(final String s, final String s1, final int i) {
        int k = 0;
        String s2 = "";
        for (int j = s.length() + 1; j < s1.length(); ++j) {
            final String s3 = new StringBuilder().append(s1.charAt(j)).toString();
            if (s3.equals(",") || s3.equals(")")) {
                ++k;
                ++j;
            }
            if (k == i) {
                s2 = String.valueOf(s2) + s1.charAt(j);
            }
        }
        return Integer.valueOf(s2);
    }
    
    @Override
    public void paint(final Graphics g) {
        g.drawImage(this.offImage, 0, 0, this);
    }
    
    public GameSparker() {
        this.u = new Control[101];
        this.mouses = 0;
        this.xm = 0;
        this.ym = 0;
        this.lostfcs = false;
        this.exwist = true;
        this.readfromtxt = false;
        this.failed = 1000;
        this.nob = 0;
        this.notb = 0;
        this.view = 0;
        this.fps = 25;
        this.frametimer = 0L;
        this.contception = false;
        this.autosave = false;
    }
    
    public void loadbase(final ContO[] aconto, final Medium medium, final Trackers trackers, final xtGraphics xtgraphics) {
        final String[] as = { "2000tornados", "formula7", "canyenaro", "lescrab", "nimi", "maxrevenge", "leadoxide", "koolkat", "drifter", "policecops", "mustang", "king", "audir8", "masheen", "radicalone", "drmonster", "newcar1", "newcar2", "newcar3", "newcar4", "secretcar1", "secretcar2", "secretcar3", "tornadoshark", "formula72", "wowcaninaro", "lavitacrab", "nimi2", "maxrevenge2", "leadoxide2", "koolkat2", "drifterx", "swordofjustice", "highrider", "elking", "mightyeight", "masheen2", "radicalone2", "drmonstaa", "road", "froad", "twister2", "twister1", "turn", "offroad", "bumproad", "offturn", "nroad", "nturn", "roblend", "noblend", "rnblend", "roadend", "offroadend", "hpground", "ramp30", "cramp35", "dramp15", "dhilo15", "slide10", "takeoff", "sramp22", "offbump", "offramp", "thewall", "halfpipe", "spikes", "rail", "sofframp", "checkpoint", "fixpoint", "offcheckpoint", "sideoff", "bsideoff", "uprise", "riseroad", "sroad", "soffroad", "2000tornadosB", "formula7B", "canyenaroB", "lescrabB", "nimiB", "maxrevengeB", "leadoxideB", "koolkatB", "drifterB", "policecopsB", "mustangB", "kingB", "audir8B", "masheenB", "radicaloneB", "drmonsterB", "newcar1B", "newcar2B", "newcar3B", "newcar4B", "secretcar1B", "secretcar2B", "secretcar3B", "tornadosharkB", "formula72B", "wowcaninaroB", "lavitacrabB", "nimi2B", "maxrevenge2B", "leadoxide2B", "koolkat2B", "drifterxB", "swordofjusticeB", "highriderB", "elkingB", "mightyeightB", "masheen2B", "radicalone2B", "drmonstaaB", "tree4", "tree6", "offhill", "spikefire", "railfire", "cactus", "roll1", "roll2", "roll3", "roll4", "roll5", "roll6" };
        xtgraphics.dnload += 6;
        try {
            final URL url = new URL(this.getCodeBase(), "data/models.radq");
            final int i35 = url.openConnection().getContentLength();
            final DataInputStream datainputstream = new DataInputStream(url.openStream());
            final byte[] arrayOfByte1 = new byte[i35];
            datainputstream.readFully(arrayOfByte1);
            ZipInputStream zipinputstream;
            if (arrayOfByte1[0] == 80 && arrayOfByte1[1] == 75 && arrayOfByte1[2] == 3) {
                zipinputstream = new ZipInputStream(new ByteArrayInputStream(arrayOfByte1));
            }
            else {
                for (int i36 = 0; i36 < i35; ++i36) {
                    if (arrayOfByte1[i36] == 75) {
                        arrayOfByte1[i36] = 85;
                    }
                    else if (arrayOfByte1[i36] == 85) {
                        arrayOfByte1[i36] = 75;
                    }
                    if (arrayOfByte1[i36] == 36) {
                        arrayOfByte1[i36] = 64;
                    }
                    else if (arrayOfByte1[i36] == 64) {
                        arrayOfByte1[i36] = 36;
                    }
                    if (arrayOfByte1[i36] == 53) {
                        arrayOfByte1[i36] = 19;
                    }
                    else if (arrayOfByte1[i36] == 19) {
                        arrayOfByte1[i36] = 53;
                    }
                    if (arrayOfByte1[i36] == 21) {
                        arrayOfByte1[i36] = 44;
                    }
                    else if (arrayOfByte1[i36] == 44) {
                        arrayOfByte1[i36] = 21;
                    }
                    if (arrayOfByte1[i36] == 59) {
                        arrayOfByte1[i36] = 72;
                    }
                    else if (arrayOfByte1[i36] == 72) {
                        arrayOfByte1[i36] = 59;
                    }
                    if (arrayOfByte1[i36] == 11) {
                        arrayOfByte1[i36] = 49;
                    }
                    else if (arrayOfByte1[i36] == 49) {
                        arrayOfByte1[i36] = 11;
                    }
                    if (arrayOfByte1[i36] == 13) {
                        arrayOfByte1[i36] = 68;
                    }
                    else if (arrayOfByte1[i36] == 68) {
                        arrayOfByte1[i36] = 13;
                    }
                }
                zipinputstream = new ZipInputStream(new ByteArrayInputStream(arrayOfByte1));
            }
            for (ZipEntry zipentry = zipinputstream.getNextEntry(); zipentry != null; zipentry = zipinputstream.getNextEntry()) {
                int j = 0;
                int k = 0;
                do {
                    if (zipentry.getName().startsWith(as[k])) {
                        j = k;
                    }
                } while (++k < 129);
                k = (int)zipentry.getSize();
                final byte[] abyte0 = new byte[k];
                int l = 0;
                while (k > 0) {
                    final int m = zipinputstream.read(abyte0, l, k);
                    l += m;
                    k -= m;
                }
                aconto[j] = new ContO(abyte0, medium, trackers, xtgraphics, j);
                ++xtgraphics.dnload;
            }
            datainputstream.close();
            zipinputstream.close();
        }
        catch (final Exception exception) {
            System.out.println("Error Reading Models: " + exception);
        }
        System.gc();
    }
    
    @Override
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public int sunytyp() {
        final String s = System.getProperty("java.version");
        final String s2 = new StringBuilder().append(this.getAppletContext()).toString();
        if (!s2.startsWith("com.ms.")) {
            return (!s.startsWith("1.3") && !s.startsWith("1.4")) ? 2 : 1;
        }
        return 0;
    }
    
    @Override
    public void start() {
        if (this.gamer == null) {
            this.gamer = new Thread(this);
        }
        this.gamer.start();
    }
    
    public void loadstage(final ContO[] aconto, final ContO[] aconto1, final Medium medium, final Trackers trackers, final CheckPoints checkpoints, final xtGraphics xtgraphics, final Madness[] amadness, final Record record, final Contva contva) {
        checkpoints.haltall = false;
        checkpoints.wasted = 0;
        checkpoints.catchfin = 0;
        medium.ground = 250;
        if (xtgraphics.replayphase == 2) {
            this.view = 0;
            if (xtgraphics.alldone) {
                xtgraphics.replayphase = 3;
            }
        }
        else {
            trackers.nt = 0;
            this.nob = xtgraphics.nplayers;
            this.notb = 0;
            checkpoints.n = 0;
            checkpoints.nsp = 0;
            checkpoints.fn = 0;
            medium.lightson = false;
            this.view = 0;
            int i = 0;
            int j = 100;
            int k = 0;
            int l = 100;
            int wallr = 0;
            int walll = 100;
            int wallt = 0;
            int wallb = 100;
            boolean showwater = false;
            if (xtgraphics.careermode) {
                if (checkpoints.stage == 24) {
                    showwater = true;
                }
                if (checkpoints.stage == 16) {
                    medium.showsnow = true;
                    if (checkpoints.stage == 16) {
                        medium.snowno = 40;
                        medium.snowheight = 10000;
                    }
                }
                else {
                    medium.showsnow = false;
                }
                if (checkpoints.stage == 18) {
                    medium.groundcolour = true;
                }
                else {
                    medium.groundcolour = false;
                }
            }
            else {
                medium.showsnow = false;
                medium.groundcolour = false;
            }
            medium.showwater = showwater;
            if (!medium.showsnow) {
                medium.snowno = 0;
            }
            String s1 = "";
            try {
                String trackloc = "data/Files/tracks.radq";
                if (xtgraphics.careermode) {
                    trackloc = "data/Files/careertracks.radq";
                }
                if (xtgraphics.classicmode) {
                    trackloc = "data/Files/classictracks.radq";
                }
                if (!xtgraphics.careermode && !xtgraphics.classicmode && checkpoints.stage == 26) {
                    trackloc = "data/Files/matchtracks.radq";
                }
                final URL url = new URL(this.getCodeBase(), trackloc);
                final int i2 = url.openConnection().getContentLength();
                final DataInputStream datainputstream = new DataInputStream(url.openStream());
                final byte[] arrayOfByte1 = new byte[i2];
                datainputstream.readFully(arrayOfByte1);
                ZipInputStream zipinputstream;
                if (arrayOfByte1[0] == 80 && arrayOfByte1[1] == 75 && arrayOfByte1[2] == 3) {
                    zipinputstream = new ZipInputStream(new ByteArrayInputStream(arrayOfByte1));
                }
                else {
                    for (int i3 = 0; i3 < i2; ++i3) {
                        if (arrayOfByte1[i3] == 75) {
                            arrayOfByte1[i3] = 85;
                        }
                        else if (arrayOfByte1[i3] == 85) {
                            arrayOfByte1[i3] = 75;
                        }
                        if (arrayOfByte1[i3] == 36) {
                            arrayOfByte1[i3] = 64;
                        }
                        else if (arrayOfByte1[i3] == 64) {
                            arrayOfByte1[i3] = 36;
                        }
                        if (arrayOfByte1[i3] == 53) {
                            arrayOfByte1[i3] = 19;
                        }
                        else if (arrayOfByte1[i3] == 19) {
                            arrayOfByte1[i3] = 53;
                        }
                        if (arrayOfByte1[i3] == 21) {
                            arrayOfByte1[i3] = 44;
                        }
                        else if (arrayOfByte1[i3] == 44) {
                            arrayOfByte1[i3] = 21;
                        }
                        if (arrayOfByte1[i3] == 59) {
                            arrayOfByte1[i3] = 72;
                        }
                        else if (arrayOfByte1[i3] == 72) {
                            arrayOfByte1[i3] = 59;
                        }
                        if (arrayOfByte1[i3] == 11) {
                            arrayOfByte1[i3] = 49;
                        }
                        else if (arrayOfByte1[i3] == 49) {
                            arrayOfByte1[i3] = 11;
                        }
                        if (arrayOfByte1[i3] == 13) {
                            arrayOfByte1[i3] = 68;
                        }
                        else if (arrayOfByte1[i3] == 68) {
                            arrayOfByte1[i3] = 13;
                        }
                    }
                    zipinputstream = new ZipInputStream(new ByteArrayInputStream(arrayOfByte1));
                }
                String secondloc = checkpoints.stage + ".txt";
                if (xtgraphics.careermode && xtgraphics.bonstage) {
                    for (int a = 0; a < 6; ++a) {
                        if (xtgraphics.bonusstage[a]) {
                            secondloc = "bonus/" + (a + 1) + ".txt";
                        }
                    }
                }
                if (!xtgraphics.careermode && !xtgraphics.classicmode && checkpoints.stage == 26) {
                    secondloc = checkpoints.stage + "m" + xtgraphics.ptmatch + ".txt";
                }
                ZipEntry entry;
                while ((entry = zipinputstream.getNextEntry()) != null) {
                    if (entry.getName().equals(new StringBuilder().append(secondloc).toString())) {
                        final BufferedReader datainputstreams = new BufferedReader(new InputStreamReader(zipinputstream));
                        String s2;
                        while ((s2 = datainputstreams.readLine()) != null) {
                            s1 = new StringBuilder().append(s2.trim()).toString();
                            if (s1.startsWith("snap")) {
                                medium.setsnap(this.getint("snap", s1, 0), this.getint("snap", s1, 1), this.getint("snap", s1, 2));
                            }
                            if (s1.startsWith("sky")) {
                                medium.setsky(this.getint("sky", s1, 0), this.getint("sky", s1, 1), this.getint("sky", s1, 2));
                                xtgraphics.snap(checkpoints.stage);
                            }
                            if (s1.startsWith("ground")) {
                                medium.setgrnd(this.getint("ground", s1, 0), this.getint("ground", s1, 1), this.getint("ground", s1, 2));
                            }
                            if (s1.startsWith("polys")) {
                                medium.setpolys(this.getint("polys", s1, 0), this.getint("polys", s1, 1), this.getint("polys", s1, 2));
                            }
                            if (s1.startsWith("fog")) {
                                medium.setfade(this.getint("fog", s1, 0), this.getint("fog", s1, 1), this.getint("fog", s1, 2));
                            }
                            if (s1.startsWith("density")) {
                                medium.fogd = this.getint("density", s1, 0);
                            }
                            if (s1.startsWith("fadefrom")) {
                                medium.fadfrom(this.getint("fadefrom", s1, 0));
                                medium.origfade = medium.fade[0];
                            }
                            if (s1.startsWith("lightson")) {
                                medium.lightson = true;
                            }
                            if (s1.startsWith("pile")) {
                                aconto[this.nob] = new ContO(this.getint("pile", s1, 0), this.getint("pile", s1, 1), this.getint("pile", s1, 2), medium, trackers, this.getint("pile", s1, 3), this.getint("pile", s1, 4), medium.ground);
                                ++this.nob;
                            }
                            if (s1.startsWith("fakewallr")) {
                                final String type = "fakewallr";
                                final int j2 = this.getint(type, s1, 0);
                                final int j3 = this.getint(type, s1, 1);
                                final int j4 = this.getint(type, s1, 2);
                                for (int j5 = 0; j5 < j2; ++j5) {
                                    aconto[this.nob] = new ContO(aconto1[64], j3, medium.ground - aconto1[64].grat, j5 * 4800 + j4, 0);
                                    aconto[this.nob].grounded = 1.0f;
                                    ++this.nob;
                                }
                                this.notb = this.nob + 1;
                            }
                            if (s1.startsWith("fakewalll")) {
                                final String type = "fakewalll";
                                final int k2 = this.getint(type, s1, 0);
                                final int k3 = this.getint(type, s1, 1);
                                final int k4 = this.getint(type, s1, 2);
                                for (int k5 = 0; k5 < k2; ++k5) {
                                    aconto[this.nob] = new ContO(aconto1[64], k3, medium.ground - aconto1[64].grat, k5 * 4800 + k4, 0);
                                    aconto[this.nob].grounded = 1.0f;
                                    ++this.nob;
                                }
                                this.notb = this.nob + 1;
                            }
                            if (s1.startsWith("fakewallt")) {
                                final String type = "fakewallt";
                                final int l2 = this.getint(type, s1, 0);
                                final int l3 = this.getint(type, s1, 1);
                                final int l4 = this.getint(type, s1, 2);
                                for (int l5 = 0; l5 < l2; ++l5) {
                                    aconto[this.nob] = new ContO(aconto1[64], l5 * 4800 + l4, medium.ground - aconto1[64].grat, l3, 90);
                                    aconto[this.nob].grounded = 1.0f;
                                    ++this.nob;
                                }
                                this.notb = this.nob + 1;
                            }
                            if (s1.startsWith("fakewallb")) {
                                final String type = "fakewallb";
                                final int i4 = this.getint(type, s1, 0);
                                final int i5 = this.getint(type, s1, 1);
                                final int i6 = this.getint(type, s1, 2);
                                for (int i7 = 0; i7 < i4; ++i7) {
                                    aconto[this.nob] = new ContO(aconto1[64], i7 * 4800 + i6, medium.ground - aconto1[64].grat, i5, 90);
                                    aconto[this.nob].grounded = 1.0f;
                                    ++this.nob;
                                }
                                this.notb = this.nob + 1;
                            }
                            if (s1.startsWith("set") || s1.startsWith("setpoint") || s1.startsWith("setfloat") || s1.startsWith("setfire") || s1.startsWith("setfade") || s1.startsWith("setfadepoint") || s1.startsWith("setfadefloat") || s1.startsWith("setcol") || s1.startsWith("setcolcode") || s1.startsWith("setfloatcolcode") || s1.startsWith("teleset") || s1.startsWith("setfloatpoint")) {
                                String type = "";
                                if (s1.startsWith("set")) {
                                    type = "set";
                                }
                                if (s1.startsWith("setpoint")) {
                                    type = "setpoint";
                                }
                                if (s1.startsWith("setfloat")) {
                                    type = "setfloat";
                                }
                                if (s1.startsWith("setfloatpoint")) {
                                    type = "setfloatpoint";
                                }
                                if (s1.startsWith("setfire")) {
                                    type = "setfire";
                                }
                                if (s1.startsWith("setfade")) {
                                    type = "setfade";
                                }
                                if (s1.startsWith("setfadepoint")) {
                                    type = "setfadepoint";
                                }
                                if (s1.startsWith("setfadefloat")) {
                                    type = "setfadefloat";
                                }
                                if (s1.startsWith("setcol")) {
                                    type = "setcol";
                                }
                                if (s1.startsWith("setcolcode")) {
                                    type = "setcolcode";
                                }
                                if (s1.startsWith("setfloatcolcode")) {
                                    type = "setfloatcolcode";
                                }
                                if (s1.startsWith("teleset")) {
                                    type = "teleset";
                                }
                                int k6 = this.getint(type, s1, 0);
                                if (k6 == 666) {
                                    k6 = -29 + xtgraphics.sc[0];
                                }
                                if (k6 == 616) {
                                    k6 = 49 + xtgraphics.sc[0];
                                }
                                if (k6 == 35) {
                                    k6 += 33;
                                }
                                else {
                                    k6 += 29;
                                }
                                if (type != "setfloat" && type != "setfadefloat" && type != "setfloatcolcode" && type != "teleset" && type != "setfloatpoint") {
                                    aconto[this.nob] = new ContO(aconto1[k6], this.getint(type, s1, 1), medium.ground - aconto1[k6].grat, this.getint(type, s1, 2), this.getint(type, s1, 3));
                                }
                                else if (type != "setfadefloat") {
                                    aconto[this.nob] = new ContO(aconto1[k6], this.getint(type, s1, 1), this.getint(type, s1, 4) - aconto1[k6].grat, this.getint(type, s1, 2), this.getint(type, s1, 3));
                                }
                                else {
                                    aconto[this.nob] = new ContO(aconto1[k6], this.getint(type, s1, 1), this.getint(type, s1, 4) - aconto1[k6].grat, this.getint(type, s1, 2), this.getint(type, s1, 3));
                                    aconto[this.nob].invisiblepiece = this.getint(type, s1, 5);
                                }
                                if (type == "setfire") {
                                    aconto[this.nob].setfire();
                                    aconto[this.nob].flameheight = this.getint(type, s1, 5);
                                    aconto[this.nob].invisiblepiece = this.getint(type, s1, 4);
                                }
                                if (type == "setcol") {
                                    aconto[this.nob].glowlines = true;
                                }
                                if (type == "setcolcode" || type == "setfloatcolcode") {
                                    int codeset = 0;
                                    if (type == "setfloatcolcode") {
                                        codeset = 1;
                                    }
                                    aconto[this.nob].glowlines = true;
                                    aconto[this.nob].glowcolour[0] = this.getint(type, s1, 4 + codeset);
                                    aconto[this.nob].glowcolour[1] = this.getint(type, s1, 5 + codeset);
                                    aconto[this.nob].glowcolour[2] = this.getint(type, s1, 6 + codeset);
                                    aconto[this.nob].glowcustom = true;
                                }
                                if (type == "setfade" || type == "setfadepoint") {
                                    aconto[this.nob].invisiblepiece = this.getint(type, s1, 4);
                                }
                                checkpoints.telefloor[checkpoints.n] = -1;
                                aconto[this.nob].telechk = -1;
                                if (type == "teleset") {
                                    checkpoints.telefloor[checkpoints.n] = this.getint(type, s1, 5);
                                    aconto[this.nob].telechk = this.getint(type, s1, 5);
                                }
                                if (s1.indexOf(")p") != -1) {
                                    checkpoints.floor[checkpoints.n] = 0;
                                    if (medium.effect[9] && (type == "setfloat" || type == "teleset" || type == "setfloatpoint" || type == "setfadefloat")) {
                                        int height = this.getint(type, s1, 4);
                                        if (height == 250) {
                                            height = 0;
                                        }
                                        checkpoints.floor[checkpoints.n] = height / -10000;
                                    }
                                    checkpoints.x[checkpoints.n] = this.getint(type, s1, 1);
                                    checkpoints.z[checkpoints.n] = this.getint(type, s1, 2);
                                    checkpoints.rotation[checkpoints.n] = this.getint(type, s1, 3);
                                    checkpoints.y[checkpoints.n] = 0;
                                    if (type == "setfloat" || type == "setfadefloat" || type == "setfloatcolcode" || type == "teleset") {
                                        checkpoints.y[checkpoints.n] = this.getint(type, s1, 4);
                                    }
                                    checkpoints.typ[checkpoints.n] = 0;
                                    if (s1.indexOf(")pt") != -1) {
                                        checkpoints.typ[checkpoints.n] = -1;
                                    }
                                    if (s1.indexOf(")pr") != -1) {
                                        checkpoints.typ[checkpoints.n] = -2;
                                    }
                                    if (s1.indexOf(")po") != -1) {
                                        checkpoints.typ[checkpoints.n] = -3;
                                    }
                                    if (s1.indexOf(")ph") != -1) {
                                        checkpoints.typ[checkpoints.n] = -4;
                                    }
                                    if (s1.indexOf("out") != -1) {
                                        System.out.println("out: " + checkpoints.n);
                                    }
                                    if ((type == "setpoint" || type == "setfadepoint" || type == "setfloatpoint") && contva.numfixes < 50) {
                                        contva.fixpoint[contva.numfixes] = checkpoints.n;
                                        ++contva.numfixes;
                                    }
                                    ++checkpoints.n;
                                    boolean exception = false;
                                    if (xtgraphics.careermode) {
                                        if ((checkpoints.stage == 14 || checkpoints.stage == 23) && (this.getint(type, s1, 0) == 88 || this.getint(type, s1, 0) == 89)) {
                                            exception = true;
                                        }
                                        if (checkpoints.stage == 5 && type == "setfadefloat") {
                                            exception = true;
                                        }
                                    }
                                    if (!exception) {
                                        this.notb = this.nob + 1;
                                    }
                                }
                                ++this.nob;
                            }
                            if (s1.startsWith("chk") || s1.startsWith("chkfloat") || s1.startsWith("specialchk") || s1.startsWith("chkcol") || s1.startsWith("chkcolcode") || s1.startsWith("chkfade") || s1.startsWith("telechk")) {
                                String type2 = "";
                                if (s1.startsWith("chk")) {
                                    type2 = "chk";
                                }
                                if (s1.startsWith("chkfloat")) {
                                    type2 = "chkfloat";
                                }
                                if (s1.startsWith("specialchk")) {
                                    type2 = "specialchk";
                                }
                                if (s1.startsWith("telechk")) {
                                    type2 = "telechk";
                                }
                                if (s1.startsWith("chkcol")) {
                                    type2 = "chkcol";
                                }
                                if (s1.startsWith("chkcolcode")) {
                                    type2 = "chkcolcode";
                                }
                                if (s1.startsWith("chkfade")) {
                                    type2 = "chkfade";
                                }
                                int l6 = this.getint(type2, s1, 0);
                                l6 += 29;
                                if (type2 == "chkfloat" || type2 == "telechk") {
                                    aconto[this.nob] = new ContO(aconto1[l6], this.getint(type2, s1, 1), this.getint(type2, s1, 4) - aconto1[l6].grat, this.getint(type2, s1, 2), this.getint(type2, s1, 3));
                                    checkpoints.y[checkpoints.n] = this.getint(type2, s1, 4) - aconto1[l6].grat;
                                }
                                else {
                                    aconto[this.nob] = new ContO(aconto1[l6], this.getint(type2, s1, 1), medium.ground - aconto1[l6].grat, this.getint(type2, s1, 2), this.getint(type2, s1, 3));
                                    checkpoints.y[checkpoints.n] = medium.ground - aconto1[l6].grat;
                                }
                                checkpoints.x[checkpoints.n] = this.getint(type2, s1, 1);
                                checkpoints.z[checkpoints.n] = this.getint(type2, s1, 2);
                                if (type2 == "chkcol") {
                                    aconto[this.nob].glowlines = true;
                                }
                                if (type2 == "chkfade") {
                                    aconto[this.nob].invisiblepiece = this.getint(type2, s1, 4);
                                }
                                checkpoints.telefloor[checkpoints.n] = -1;
                                aconto[this.nob].telechk = -1;
                                if (type2 == "telechk") {
                                    checkpoints.telefloor[checkpoints.n] = this.getint(type2, s1, 5);
                                    aconto[this.nob].telechk = this.getint(type2, s1, 5);
                                }
                                checkpoints.floor[checkpoints.n] = 0;
                                if (medium.effect[9] && (type2 == "chkfloat" || type2 == "telechk")) {
                                    int height = this.getint(type2, s1, 4);
                                    if (height == 250) {
                                        height = 0;
                                    }
                                    checkpoints.floor[checkpoints.n] = height / -10000;
                                }
                                if (type2 == "chkcolcode") {
                                    aconto[this.nob].glowlines = true;
                                    aconto[this.nob].glowcolour[0] = this.getint(type2, s1, 4);
                                    aconto[this.nob].glowcolour[1] = this.getint(type2, s1, 5);
                                    aconto[this.nob].glowcolour[2] = this.getint(type2, s1, 6);
                                    aconto[this.nob].glowcustom = true;
                                }
                                checkpoints.rotation[checkpoints.n] = this.getint(type2, s1, 3);
                                checkpoints.chkcode[checkpoints.nsp] = checkpoints.n;
                                if (type2 != "specialchk") {
                                    if (this.getint(type2, s1, 3) == 0 || this.getint(type2, s1, 3) == 180) {
                                        checkpoints.typ[checkpoints.n] = 1;
                                    }
                                    else {
                                        checkpoints.typ[checkpoints.n] = 2;
                                    }
                                }
                                else {
                                    if (this.getint(type2, s1, 3) == 0) {
                                        checkpoints.typ[checkpoints.n] = 3;
                                    }
                                    else {
                                        checkpoints.typ[checkpoints.n] = 4;
                                    }
                                    if (contva.numfixes < 50) {
                                        contva.fixpoint[contva.numfixes] = checkpoints.n;
                                        ++contva.numfixes;
                                    }
                                }
                                checkpoints.pcs = checkpoints.n;
                                ++checkpoints.n;
                                aconto[this.nob].checkpoint = checkpoints.nsp + 1;
                                ++checkpoints.nsp;
                                ++this.nob;
                                this.notb = this.nob;
                            }
                            if (s1.startsWith("fix")) {
                                int i8 = this.getint("fix", s1, 0);
                                i8 += 29;
                                aconto[this.nob] = new ContO(aconto1[i8], this.getint("fix", s1, 1), this.getint("fix", s1, 3), this.getint("fix", s1, 2), this.getint("fix", s1, 4));
                                checkpoints.fx[checkpoints.fn] = this.getint("fix", s1, 1);
                                checkpoints.fz[checkpoints.fn] = this.getint("fix", s1, 2);
                                checkpoints.fy[checkpoints.fn] = this.getint("fix", s1, 3);
                                aconto[this.nob].elec = true;
                                if (this.getint("fix", s1, 4) != 0) {
                                    checkpoints.roted[checkpoints.fn] = true;
                                    aconto[this.nob].roted = true;
                                }
                                else {
                                    checkpoints.roted[checkpoints.fn] = false;
                                }
                                if (s1.indexOf(")s") != -1) {
                                    checkpoints.special[checkpoints.fn] = true;
                                }
                                else {
                                    checkpoints.special[checkpoints.fn] = false;
                                }
                                ++checkpoints.fn;
                                ++this.nob;
                                this.notb = this.nob;
                            }
                            if (s1.startsWith("nlaps")) {
                                checkpoints.nlaps = this.getint("nlaps", s1, 0);
                            }
                            if (s1.startsWith("name")) {
                                checkpoints.name = this.getstring("name", s1, 0).replace('|', ',');
                            }
                            if (s1.startsWith("switch")) {
                                xtgraphics.musicswitch = this.getint("switch", s1, 0) * 1000000L;
                            }
                            if (s1.startsWith("mountains")) {
                                medium.mgen = this.getint("mountains", s1, 0);
                            }
                            if (s1.startsWith("clouds")) {
                                medium.setcloads(this.getint("clouds", s1, 0), this.getint("clouds", s1, 1), this.getint("clouds", s1, 2), this.getint("clouds", s1, 3), this.getint("clouds", s1, 4));
                            }
                            if (s1.startsWith("maxr") || s1.startsWith("flamerw") || s1.startsWith("noseerw") || s1.startsWith("igmaxr") || s1.startsWith("maxrfloat")) {
                                String type = "";
                                if (s1.startsWith("maxr")) {
                                    type = "maxr";
                                }
                                if (s1.startsWith("maxrfloat")) {
                                    type = "maxrfloat";
                                }
                                if (s1.startsWith("flamerw")) {
                                    type = "flamerw";
                                }
                                if (s1.startsWith("noseerw")) {
                                    type = "noseerw";
                                }
                                if (s1.startsWith("igmaxr")) {
                                    type = "igmaxr";
                                }
                                final int j2 = this.getint(type, s1, 0);
                                final int j3 = i = this.getint(type, s1, 1);
                                if (type != "igmaxr") {
                                    wallr = j3;
                                }
                                medium.wallside[0] = j3;
                                final int j4 = this.getint(type, s1, 2);
                                for (int j5 = 0; j5 < j2; ++j5) {
                                    if (type == "maxrfloat") {
                                        aconto[this.nob] = new ContO(aconto1[64], j3, this.getint(type, s1, 3) - aconto1[64].grat, j5 * 4800 + j4, 0);
                                    }
                                    if (type == "maxr" || type == "igmaxr") {
                                        aconto[this.nob] = new ContO(aconto1[64], j3, medium.ground - aconto1[64].grat, j5 * 4800 + j4, 0);
                                    }
                                    if (type == "flamerw") {
                                        (aconto[this.nob] = new ContO(aconto1[64], j3, medium.ground - aconto1[64].grat, j5 * 4800 + j4, 0)).setfire();
                                        aconto[this.nob].invisiblepiece = 50;
                                        aconto[this.nob].flameheight = this.getint(type, s1, 3);
                                    }
                                    if (type == "noseerw") {
                                        aconto[this.nob] = new ContO(aconto1[64], j3, medium.ground - aconto1[64].grat, j5 * 4800 + j4, 0);
                                        int fadeness = this.getint(type, s1, 3);
                                        if (fadeness < 0) {
                                            fadeness = 0;
                                        }
                                        aconto[this.nob].invisiblepiece = fadeness;
                                    }
                                    aconto[this.nob].wallpiece = true;
                                    xtgraphics.wallcode[0] = this.nob;
                                    ++this.nob;
                                }
                                if (type != "noseerw" || this.getint(type, s1, 3) >= 0) {
                                    if (type == "maxrfloat") {
                                        trackers.y[trackers.nt] = -5000 + this.getint(type, s1, 3);
                                    }
                                    else {
                                        trackers.y[trackers.nt] = -5000;
                                    }
                                    trackers.rady[trackers.nt] = 7100;
                                    trackers.x[trackers.nt] = j3 + 500;
                                    trackers.radx[trackers.nt] = 600;
                                    trackers.z[trackers.nt] = j2 * 4800 / 2 + j4 - 2400;
                                    trackers.radz[trackers.nt] = j2 * 4800 / 2;
                                    trackers.xy[trackers.nt] = 90;
                                    trackers.zy[trackers.nt] = 0;
                                    trackers.dam[trackers.nt] = 1;
                                    ++trackers.nt;
                                }
                                this.notb = this.nob;
                            }
                            if (s1.startsWith("maxl") || s1.startsWith("flamelw") || s1.startsWith("noseelw") || s1.startsWith("igmaxl") || s1.startsWith("maxlfloat")) {
                                String type = "";
                                if (s1.startsWith("maxl")) {
                                    type = "maxl";
                                }
                                if (s1.startsWith("maxlfloat")) {
                                    type = "maxlfloat";
                                }
                                if (s1.startsWith("flamelw")) {
                                    type = "flamelw";
                                }
                                if (s1.startsWith("noseelw")) {
                                    type = "noseelw";
                                }
                                if (s1.startsWith("igmaxl")) {
                                    type = "igmaxl";
                                }
                                final int k2 = this.getint(type, s1, 0);
                                final int k3 = j = this.getint(type, s1, 1);
                                if (type != "igmaxl") {
                                    walll = k3;
                                }
                                medium.wallside[1] = k3;
                                final int k4 = this.getint(type, s1, 2);
                                for (int k5 = 0; k5 < k2; ++k5) {
                                    if (type == "maxlfloat") {
                                        aconto[this.nob] = new ContO(aconto1[64], k3, this.getint(type, s1, 3) - aconto1[64].grat, k5 * 4800 + k4, 0);
                                    }
                                    if (type == "maxl" || type == "igmaxl") {
                                        aconto[this.nob] = new ContO(aconto1[64], k3, medium.ground - aconto1[64].grat, k5 * 4800 + k4, 0);
                                    }
                                    if (type == "flamelw") {
                                        (aconto[this.nob] = new ContO(aconto1[64], k3, medium.ground - aconto1[64].grat, k5 * 4800 + k4, 0)).setfire();
                                        aconto[this.nob].invisiblepiece = 50;
                                        aconto[this.nob].flameheight = this.getint(type, s1, 3);
                                    }
                                    if (type == "noseelw") {
                                        aconto[this.nob] = new ContO(aconto1[64], k3, medium.ground - aconto1[64].grat, k5 * 4800 + k4, 0);
                                        int fadeness = this.getint(type, s1, 3);
                                        if (fadeness < 0) {
                                            fadeness = 0;
                                        }
                                        aconto[this.nob].invisiblepiece = fadeness;
                                    }
                                    aconto[this.nob].wallpiece = true;
                                    xtgraphics.wallcode[1] = this.nob;
                                    ++this.nob;
                                }
                                if (type != "noseelw" || this.getint(type, s1, 3) >= 0) {
                                    if (type == "maxlfloat") {
                                        trackers.y[trackers.nt] = -5000 + this.getint(type, s1, 3);
                                    }
                                    else {
                                        trackers.y[trackers.nt] = -5000;
                                    }
                                    trackers.rady[trackers.nt] = 7100;
                                    trackers.x[trackers.nt] = k3 - 500;
                                    trackers.radx[trackers.nt] = 600;
                                    trackers.z[trackers.nt] = k2 * 4800 / 2 + k4 - 2400;
                                    trackers.radz[trackers.nt] = k2 * 4800 / 2;
                                    trackers.xy[trackers.nt] = -90;
                                    trackers.zy[trackers.nt] = 0;
                                    trackers.dam[trackers.nt] = 1;
                                    ++trackers.nt;
                                }
                                this.notb = this.nob;
                            }
                            if (s1.startsWith("maxt") || s1.startsWith("flametw") || s1.startsWith("noseetw") || s1.startsWith("igmaxt") || s1.startsWith("maxtfloat")) {
                                String type = "";
                                if (s1.startsWith("maxt")) {
                                    type = "maxt";
                                }
                                if (s1.startsWith("maxtfloat")) {
                                    type = "maxtfloat";
                                }
                                if (s1.startsWith("flametw")) {
                                    type = "flametw";
                                }
                                if (s1.startsWith("noseetw")) {
                                    type = "noseetw";
                                }
                                if (s1.startsWith("igmaxt")) {
                                    type = "igmaxt";
                                }
                                final int l2 = this.getint(type, s1, 0);
                                final int l3 = k = this.getint(type, s1, 1);
                                if (type != "igmaxt") {
                                    wallt = l3;
                                }
                                medium.wallside[2] = l3;
                                final int l4 = this.getint(type, s1, 2);
                                for (int l5 = 0; l5 < l2; ++l5) {
                                    if (type == "maxtfloat") {
                                        aconto[this.nob] = new ContO(aconto1[64], l5 * 4800 + l4, this.getint(type, s1, 3) - aconto1[64].grat, l3, 90);
                                    }
                                    if (type == "maxt" || type == "igmaxt") {
                                        aconto[this.nob] = new ContO(aconto1[64], l5 * 4800 + l4, medium.ground - aconto1[64].grat, l3, 90);
                                    }
                                    if (type == "flametw") {
                                        (aconto[this.nob] = new ContO(aconto1[64], l5 * 4800 + l4, medium.ground - aconto1[64].grat, l3, 90)).setfire();
                                        aconto[this.nob].invisiblepiece = 50;
                                        aconto[this.nob].flameheight = this.getint(type, s1, 3);
                                    }
                                    if (type == "noseetw") {
                                        aconto[this.nob] = new ContO(aconto1[64], l5 * 4800 + l4, medium.ground - aconto1[64].grat, l3, 90);
                                        int fadeness = this.getint(type, s1, 3);
                                        if (fadeness < 0) {
                                            fadeness = 0;
                                        }
                                        aconto[this.nob].invisiblepiece = fadeness;
                                    }
                                    aconto[this.nob].wallpiece = true;
                                    xtgraphics.wallcode[2] = this.nob;
                                    ++this.nob;
                                }
                                if (type != "noseetw" || this.getint(type, s1, 3) >= 0) {
                                    if (type == "maxtfloat") {
                                        trackers.y[trackers.nt] = -5000 + this.getint(type, s1, 3);
                                    }
                                    else {
                                        trackers.y[trackers.nt] = -5000;
                                    }
                                    trackers.rady[trackers.nt] = 7100;
                                    trackers.z[trackers.nt] = l3 + 500;
                                    trackers.radz[trackers.nt] = 600;
                                    trackers.x[trackers.nt] = l2 * 4800 / 2 + l4 - 2400;
                                    trackers.radx[trackers.nt] = l2 * 4800 / 2;
                                    trackers.zy[trackers.nt] = 90;
                                    trackers.xy[trackers.nt] = 0;
                                    trackers.dam[trackers.nt] = 1;
                                    ++trackers.nt;
                                }
                                this.notb = this.nob;
                            }
                            if (s1.startsWith("maxb") || s1.startsWith("flamebw") || s1.startsWith("noseebw") || s1.startsWith("igmaxb") || s1.startsWith("maxbfloat")) {
                                String type = "";
                                if (s1.startsWith("maxb")) {
                                    type = "maxb";
                                }
                                if (s1.startsWith("maxbfloat")) {
                                    type = "maxbfloat";
                                }
                                if (s1.startsWith("flamebw")) {
                                    type = "flamebw";
                                }
                                if (s1.startsWith("noseebw")) {
                                    type = "noseebw";
                                }
                                if (s1.startsWith("igmaxb")) {
                                    type = "igmaxb";
                                }
                                final int i4 = this.getint(type, s1, 0);
                                final int i5 = l = this.getint(type, s1, 1);
                                if (type != "igmaxb") {
                                    wallb = i5;
                                }
                                medium.wallside[3] = i5;
                                final int i6 = this.getint(type, s1, 2);
                                for (int i7 = 0; i7 < i4; ++i7) {
                                    if (type == "maxbfloat") {
                                        aconto[this.nob] = new ContO(aconto1[64], i7 * 4800 + i6, this.getint(type, s1, 3) - aconto1[64].grat, i5, 90);
                                    }
                                    if (type == "maxb" || type == "igmaxb") {
                                        aconto[this.nob] = new ContO(aconto1[64], i7 * 4800 + i6, medium.ground - aconto1[64].grat, i5, 90);
                                    }
                                    if (type == "flamebw") {
                                        (aconto[this.nob] = new ContO(aconto1[64], i7 * 4800 + i6, medium.ground - aconto1[64].grat, i5, 90)).setfire();
                                        aconto[this.nob].invisiblepiece = 50;
                                        aconto[this.nob].flameheight = this.getint(type, s1, 3);
                                    }
                                    if (type == "noseebw") {
                                        int fadeness = this.getint(type, s1, 3);
                                        if (fadeness < 0) {
                                            fadeness = 0;
                                        }
                                        aconto[this.nob] = new ContO(aconto1[64], i7 * 4800 + i6, medium.ground - aconto1[64].grat, i5, 90);
                                        aconto[this.nob].invisiblepiece = fadeness;
                                    }
                                    aconto[this.nob].wallpiece = true;
                                    xtgraphics.wallcode[3] = this.nob;
                                    ++this.nob;
                                }
                                if (type != "noseebw" || this.getint(type, s1, 3) >= 0) {
                                    if (type == "maxbfloat") {
                                        trackers.y[trackers.nt] = -5000 + this.getint(type, s1, 3);
                                    }
                                    else {
                                        trackers.y[trackers.nt] = -5000;
                                    }
                                    trackers.rady[trackers.nt] = 7100;
                                    trackers.z[trackers.nt] = i5 - 500;
                                    trackers.radz[trackers.nt] = 600;
                                    trackers.x[trackers.nt] = i4 * 4800 / 2 + i6 - 2400;
                                    trackers.radx[trackers.nt] = i4 * 4800 / 2;
                                    trackers.zy[trackers.nt] = -90;
                                    trackers.xy[trackers.nt] = 0;
                                    trackers.dam[trackers.nt] = 1;
                                    ++trackers.nt;
                                }
                                this.notb = this.nob;
                            }
                        }
                    }
                }
            }
            catch (final Exception exception2) {
                xtgraphics.fase = 3;
                System.out.println("Error in stage " + checkpoints.stage);
                System.out.println(new StringBuilder().append(exception2).toString());
                System.out.println("At line: " + s1);
            }
            for (int a2 = 0; a2 < 100; ++a2) {
                medium.effect[a2] = false;
            }
            medium.icehills = false;
            medium.greystage = 0;
            if (xtgraphics.careermode) {
                if (checkpoints.stage == 12) {
                    medium.effect[0] = true;
                }
                if (checkpoints.stage == 19) {
                    medium.effect[1] = true;
                }
                if (checkpoints.stage == 22) {
                    medium.effect[2] = true;
                }
                if (checkpoints.stage == 8) {
                    medium.effect[3] = true;
                }
                if (checkpoints.stage == 5 && !xtgraphics.bonusstage[0]) {
                    medium.effect[4] = true;
                }
                if (checkpoints.stage == 7) {
                    medium.effect[5] = true;
                }
                if (checkpoints.stage == 9) {
                    medium.effect[6] = true;
                }
                if (checkpoints.stage == 11 && !xtgraphics.bonusstage[1]) {
                    medium.effect[7] = true;
                }
                if (checkpoints.stage == 10) {
                    medium.effect[8] = true;
                }
                if (checkpoints.stage == 13) {
                    medium.effect[9] = true;
                }
                if (checkpoints.stage == 15 && !xtgraphics.bonusstage[2]) {
                    medium.effect[11] = true;
                }
                if (checkpoints.stage == 23) {
                    medium.greystage = 1;
                }
            }
            medium.lightn = -1;
            if ((checkpoints.stage == 16 && xtgraphics.classicmode) || medium.effect[1]) {
                medium.lightn = 0;
            }
            if (checkpoints.stage == 1) {
                medium.nochekflk = false;
            }
            else {
                medium.nochekflk = true;
            }
            medium.reducepolys = 1.0;
            if (xtgraphics.careermode) {
                if (checkpoints.stage == 14 || xtgraphics.bonusstage[3]) {
                    medium.reducepolys = 2.0;
                }
                if (checkpoints.stage == 7) {
                    medium.polyoutline[0] = 0;
                    medium.polyoutline[1] = 120;
                    medium.polyoutline[2] = 0;
                    medium.reducepolys = 1.5;
                }
            }
            int morespace = 0;
            if (medium.effect[4]) {
                morespace = 4800;
            }
            if (!medium.effect[10]) {
                medium.newpolys(walll - morespace, wallr - walll + morespace, wallb - morespace, wallt - wallb + morespace, trackers);
            }
            medium.newmountains(j, i, l, k, checkpoints.stage, xtgraphics);
            medium.newclouds(j, i, l, k, checkpoints.stage, xtgraphics);
            medium.newstars(checkpoints.stage, xtgraphics);
            if (xtgraphics.fase == 2 || xtgraphics.showopstage == 195 || xtgraphics.justcs == 2) {
                medium.trx = (j + i) / 2;
                medium.trz = (k + l) / 2;
                medium.ptcnt = -10;
                medium.hit = 25000;
                medium.fallen = 0;
                medium.nrnd = 0;
                if (xtgraphics.justcs == -1) {
                    medium.trk = true;
                }
                if (!xtgraphics.careermode) {
                    if (xtgraphics.justcs == -1) {
                        xtgraphics.fase = 1;
                    }
                    if (xtgraphics.justcs == 2) {
                        xtgraphics.justcs = 4;
                    }
                }
                else if (xtgraphics.alldone) {
                    xtgraphics.fase = -69;
                }
                this.mouses = 0;
            }
        }
        xtgraphics.resetstat(checkpoints.stage, amadness);
        if (xtgraphics.careermode) {
            boolean exceptstages = false;
            if (checkpoints.stage == 9 || checkpoints.stage == 10 || checkpoints.stage == 14 || checkpoints.stage == 20) {
                exceptstages = true;
            }
            if (checkpoints.stage >= 4 && !exceptstages && !xtgraphics.nolevels) {
                if (xtgraphics.sortedcars) {
                    xtgraphics.beasts(checkpoints.stage, amadness);
                }
            }
            else {
                xtgraphics.alldone = true;
            }
        }
        else {
            xtgraphics.resetbeasts();
            xtgraphics.resetshadows(amadness);
        }
        int j6 = 0;
        do {
            this.u[j6].reset(checkpoints, xtgraphics.sc[j6], xtgraphics, amadness[j6], amadness[0]);
        } while (++j6 < xtgraphics.nplayers);
        j6 = 0;
        do {
            final int[] diff = new int[xtgraphics.nplayers];
            if (xtgraphics.beastopponent[j6]) {
                diff[j6] = 78;
            }
            int moveback = 0;
            if (xtgraphics.careermode) {
                if (checkpoints.stage == 17) {
                    if (j6 >= 4) {
                        moveback = 760;
                    }
                    else if (j6 > 0) {
                        moveback = 100000;
                    }
                    else {
                        moveback = 0;
                    }
                }
                if (checkpoints.stage == 19) {
                    moveback = 760;
                }
                if (checkpoints.stage == 3) {
                    moveback = 3040;
                }
                if (checkpoints.stage == 6) {
                    moveback = -16800;
                }
            }
            boolean specialar = false;
            if (((checkpoints.stage == 5 || checkpoints.stage == 11) && !xtgraphics.bonstage) || checkpoints.stage == 13 || checkpoints.stage == 14 || checkpoints.stage == 20 || checkpoints.stage == 21 || (checkpoints.stage == 23 && (xtgraphics.unlocked[1] == 23 || xtgraphics.hardstage)) || (xtgraphics.bonusstage[3] && j6 > 0)) {
                specialar = true;
            }
            if (xtgraphics.careermode && specialar) {
                if (checkpoints.stage == 5) {
                    if (j6 < xtgraphics.nplayers - 1 && (j6 != 0 || !xtgraphics.makebot)) {
                        if (j6 % 3 == 0) {
                            aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6] + diff[j6]], 0, medium.ground - aconto1[xtgraphics.sc[j6] + diff[j6]].grat, 760 + j6 / 3 * 760, 0);
                        }
                        if (j6 % 3 == 1) {
                            aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6] + diff[j6]], -350, medium.ground - aconto1[xtgraphics.sc[j6] + diff[j6]].grat, 1140 + j6 / 3 * 760, 0);
                        }
                        if (j6 % 3 == 2) {
                            aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6] + diff[j6]], 350, medium.ground - aconto1[xtgraphics.sc[j6] + diff[j6]].grat, 1140 + j6 / 3 * 760, 0);
                        }
                    }
                    else {
                        aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6] + diff[j6]], 0, medium.ground - aconto1[xtgraphics.sc[j6] + diff[j6]].grat, 2280, 0);
                    }
                }
                if (checkpoints.stage == 11 && !xtgraphics.bonusstage[1]) {
                    if (j6 >= 1 && j6 <= 4) {
                        aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6] + diff[j6]], 0, medium.ground - aconto1[xtgraphics.sc[j6] + diff[j6]].grat, -500000, 0);
                    }
                    else if (j6 > 0) {
                        final int caroffset = j6 - 4;
                        if (j6 < xtgraphics.nplayers - 1) {
                            if (caroffset % 3 == 0) {
                                aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6] + diff[j6]], 0, medium.ground - aconto1[xtgraphics.sc[j6] + diff[j6]].grat, -760 + caroffset / 3 * 760, 0);
                            }
                            if (caroffset % 3 == 1) {
                                aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6] + diff[j6]], -350, medium.ground - aconto1[xtgraphics.sc[j6] + diff[j6]].grat, -380 + caroffset / 3 * 760, 0);
                            }
                            if (caroffset % 3 == 2) {
                                aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6] + diff[j6]], 350, medium.ground - aconto1[xtgraphics.sc[j6] + diff[j6]].grat, -380 + caroffset / 3 * 760, 0);
                            }
                        }
                        else {
                            aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6] + diff[j6]], 0, medium.ground - aconto1[xtgraphics.sc[j6] + diff[j6]].grat, -760 + caroffset / 3 * 760, 0);
                        }
                    }
                    else if (xtgraphics.makebot) {
                        aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6] + diff[j6]], 350, medium.ground - aconto1[xtgraphics.sc[j6] + diff[j6]].grat, 380, 0);
                    }
                    else {
                        aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6] + diff[j6]], 0, medium.ground - aconto1[xtgraphics.sc[j6] + diff[j6]].grat, -760, 0);
                    }
                }
                if (checkpoints.stage == 14) {
                    if (j6 < xtgraphics.nplayers - 3 && (j6 != 0 || !xtgraphics.makebot)) {
                        if (j6 == 0) {
                            aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6] + diff[j6]], 0, medium.ground - aconto1[xtgraphics.sc[j6] + diff[j6]].grat, 0, 0);
                        }
                        else {
                            if (j6 == xtgraphics.nplayers - 4) {
                                aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6] + diff[j6]], -350, medium.ground - aconto1[xtgraphics.sc[j6] + diff[j6]].grat, -38000, 0);
                            }
                            if (j6 == xtgraphics.nplayers - 5) {
                                aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6] + diff[j6]], 350, medium.ground - aconto1[xtgraphics.sc[j6] + diff[j6]].grat, -38000, 0);
                            }
                        }
                    }
                    else {
                        if (j6 == xtgraphics.nplayers - 1 || (j6 == 0 && xtgraphics.makebot)) {
                            aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6] + diff[j6]], 0, medium.ground - aconto1[xtgraphics.sc[j6] + diff[j6]].grat, 760, 0);
                        }
                        if (j6 == xtgraphics.nplayers - 2) {
                            aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6] + diff[j6]], -350, medium.ground - aconto1[xtgraphics.sc[j6] + diff[j6]].grat, -380, 0);
                        }
                        if (j6 == xtgraphics.nplayers - 3) {
                            aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6] + diff[j6]], 350, medium.ground - aconto1[xtgraphics.sc[j6] + diff[j6]].grat, -380, 0);
                        }
                    }
                }
                if (xtgraphics.bonusstage[3]) {
                    aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6] + diff[j6]], 100000, 250 - aconto1[xtgraphics.sc[j6] + diff[j6]].grat, 100000, 0);
                }
                if (checkpoints.stage == 20) {
                    if (j6 == xtgraphics.nplayers - 1) {
                        aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6] + diff[j6]], 0, -20000 - aconto1[xtgraphics.sc[j6] + diff[j6]].grat, 760, 0);
                    }
                    else {
                        aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6] + diff[j6]], 0, -20000 - aconto1[xtgraphics.sc[j6] + diff[j6]].grat, 760, 0);
                    }
                }
                if (checkpoints.stage == 13) {
                    if (j6 == 0 || j6 >= 10) {
                        xtgraphics.floor[j6] = 3;
                        int offset = j6 - 9;
                        if (j6 == 0) {
                            offset = 0;
                            if (xtgraphics.makebot) {
                                offset = xtgraphics.nplayers - 14;
                            }
                        }
                        if (j6 == xtgraphics.nplayers - 1) {
                            aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6] + diff[j6]], 0, xtgraphics.floor[j6] * -10000 - aconto1[xtgraphics.sc[j6] + diff[j6]].grat, offset / 3 * 760, 0);
                        }
                        else {
                            if (j6 % 3 == 0) {
                                aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6] + diff[j6]], 0, xtgraphics.floor[j6] * -10000 - aconto1[xtgraphics.sc[j6] + diff[j6]].grat, -380 + offset / 3 * 760, 0);
                            }
                            if (j6 % 3 == 1) {
                                aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6] + diff[j6]], -350, xtgraphics.floor[j6] * -10000 - aconto1[xtgraphics.sc[j6] + diff[j6]].grat, offset / 3 * 760, 0);
                            }
                            if (j6 % 3 == 2 || (xtgraphics.makebot && j6 == 0)) {
                                aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6] + diff[j6]], 350, xtgraphics.floor[j6] * -10000 - aconto1[xtgraphics.sc[j6] + diff[j6]].grat, offset / 3 * 760, 0);
                            }
                        }
                    }
                    else {
                        xtgraphics.floor[j6] = (j6 - 1) / 3;
                        xtgraphics.norender[j6] = true;
                        aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6] + diff[j6]], -10000, xtgraphics.floor[j6] * -10000 - aconto1[xtgraphics.sc[j6] + diff[j6]].grat, j6 * 5000, 0);
                    }
                }
                if (checkpoints.stage == 21) {
                    if (j6 == xtgraphics.nplayers - 1) {
                        aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6] + diff[j6]], 0, -293500 - aconto1[xtgraphics.sc[j6] + diff[j6]].grat, j6 / 3 * 760 - 202000, 0);
                    }
                    else {
                        if (j6 % 3 == 0) {
                            aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6] + diff[j6]], 0, -293500 - aconto1[xtgraphics.sc[j6] + diff[j6]].grat, -760 + j6 / 3 * 760 - 202000, 0);
                        }
                        if (j6 % 3 == 1) {
                            aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6] + diff[j6]], -350, -293500 - aconto1[xtgraphics.sc[j6] + diff[j6]].grat, -380 + j6 / 3 * 760 - 202000, 0);
                        }
                        if (j6 % 3 == 2) {
                            aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6] + diff[j6]], 350, -293500 - aconto1[xtgraphics.sc[j6] + diff[j6]].grat, -380 + j6 / 3 * 760 - 202000, 0);
                        }
                    }
                }
                if (checkpoints.stage == 23) {
                    if (j6 == xtgraphics.nplayers - 1) {
                        aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6] + diff[j6]], 0, 250 - aconto1[xtgraphics.sc[j6] + diff[j6]].grat, (j6 - 1) / 3 * 760, 0);
                    }
                    else if (j6 > 1) {
                        if (j6 % 3 == 1) {
                            aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6] + diff[j6]], 0, 250 - aconto1[xtgraphics.sc[j6] + diff[j6]].grat, -760 + (j6 - 1) / 3 * 760, 0);
                        }
                        if (j6 % 3 == 2) {
                            aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6] + diff[j6]], -350, 250 - aconto1[xtgraphics.sc[j6] + diff[j6]].grat, -380 + (j6 - 1) / 3 * 760, 0);
                        }
                        if (j6 % 3 == 0) {
                            aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6] + diff[j6]], 350, 250 - aconto1[xtgraphics.sc[j6] + diff[j6]].grat, -380 + (j6 - 1) / 3 * 760, 0);
                        }
                    }
                    else {
                        if (j6 == 0) {
                            aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6] + diff[j6]], 0, 250 - aconto1[xtgraphics.sc[j6] + diff[j6]].grat, -760, 0);
                        }
                        if (j6 == 1) {
                            aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6] + diff[j6]], 0, 250 - aconto1[xtgraphics.sc[j6] + diff[j6]].grat, -150000, 0);
                        }
                    }
                }
            }
            else {
                final int moveup = 0;
                if (xtgraphics.careermode && checkpoints.stage == 15) {
                    final boolean b = xtgraphics.bonusstage[2];
                }
                if ((j6 < xtgraphics.nplayers - 1 || xtgraphics.nplayers % 3 == 1) && (j6 != 0 || !xtgraphics.makebot)) {
                    if (!xtgraphics.bonusstage[1]) {
                        if (j6 % 3 == 0) {
                            aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6] + diff[j6]], 0, medium.ground - aconto1[xtgraphics.sc[j6] + diff[j6]].grat - moveup, -760 + j6 / 3 * 760 - moveback, 0);
                        }
                        if (j6 % 3 == 1) {
                            aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6] + diff[j6]], -350, medium.ground - aconto1[xtgraphics.sc[j6] + diff[j6]].grat - moveup, -380 + j6 / 3 * 760 - moveback, 0);
                        }
                        if (j6 % 3 == 2) {
                            aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6] + diff[j6]], 350, medium.ground - aconto1[xtgraphics.sc[j6] + diff[j6]].grat - moveup, -380 + j6 / 3 * 760 - moveback, 0);
                        }
                    }
                    else if (xtgraphics.beastopponent[j6]) {
                        if (j6 % 3 == 0) {
                            aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6] + diff[j6]], 0, -5000, -760 + j6 / 3 * 760 - moveback, 0);
                        }
                        if (j6 % 3 == 1) {
                            aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6] + diff[j6]], -350, -5000, -380 + j6 / 3 * 760 - moveback, 0);
                        }
                        if (j6 % 3 == 2) {
                            aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6] + diff[j6]], 350, -5000, -380 + j6 / 3 * 760 - moveback, 0);
                        }
                    }
                    else {
                        if (j6 % 3 == 0) {
                            aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6]], 0, medium.ground - aconto1[xtgraphics.sc[j6]].grat - moveup, -760 + j6 / 3 * 760 - moveback, 0);
                        }
                        if (j6 % 3 == 1) {
                            aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6]], -350, medium.ground - aconto1[xtgraphics.sc[j6]].grat - moveup, -380 + j6 / 3 * 760 - moveback, 0);
                        }
                        if (j6 % 3 == 2) {
                            aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6]], 350, medium.ground - aconto1[xtgraphics.sc[j6]].grat - moveup, -380 + j6 / 3 * 760 - moveback, 0);
                        }
                    }
                }
                else if (xtgraphics.makebot && j6 == 0) {
                    aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6] + diff[j6]], 0, medium.ground - aconto1[xtgraphics.sc[j6] + diff[j6]].grat - moveup, (xtgraphics.nplayers - 1) / 3 * 760 - moveback, 0);
                }
                else {
                    aconto[j6] = new ContO(aconto1[xtgraphics.sc[j6] + diff[j6]], 0, medium.ground - aconto1[xtgraphics.sc[j6] + diff[j6]].grat - moveup, j6 / 3 * 760 - moveback, 0);
                }
            }
            amadness[j6].reseto(xtgraphics.sc[j6], aconto[j6], checkpoints);
        } while (++j6 < xtgraphics.nplayers);
        record.reset(aconto, xtgraphics.nplayers);
        System.gc();
    }
    
    @Override
    public void run() {
        this.rd.setColor(new Color(0, 0, 0));
        this.rd.fillRect(0, 0, 870, 480);
        this.repaint();
        final Trackers trackers = new Trackers();
        final Medium medium = new Medium();
        final int i = 10;
        int j = 530;
        final int k = this.sunytyp();
        if (k != 2) {
            j = 500;
        }
        final CheckPoints checkpoints = new CheckPoints();
        final xtGraphics xtgraphics = new xtGraphics(medium, this.rd, this.sg, this);
        xtgraphics.loaddata(k);
        final Record record = new Record(medium, xtgraphics.nplayers);
        final ContO[] aconto = new ContO[129];
        final ContO[] aconto2 = new ContO[15000];
        this.loadbase(aconto, medium, trackers, xtgraphics);
        final Madness[] amadness = new Madness[101];
        final Contva contva = new Contva();
        final Bots bots = new Bots();
        int l = 0;
        do {
            amadness[l] = new Madness(medium, record, xtgraphics, l);
            this.u[l] = new Control(medium, contva);
        } while (++l < 101);
        this.readdata(xtgraphics, amadness[0], checkpoints);
        l = 0;
        float f = 35.0f;
        int i2 = 50;
        boolean flag = false;
        xtgraphics.stoploading();
        System.gc();
        final Date date = new Date();
        long l2 = date.getTime();
        float f2 = 30.0f;
        boolean flag2 = false;
        int j2 = 0;
        int k2 = 0;
        int i3 = 0;
        int j3 = 0;
        int k3 = 0;
        boolean flag3 = false;
        this.exwist = false;
        this.contception = false;
        while (true) {
            Date date2 = new Date();
            final long l3 = date2.getTime();
            if (xtgraphics.fase == 111 && xtgraphics.loadcomplete == 100) {
                if (this.failed == 1000) {
                    if (this.mouses == 1) {
                        i3 = 800;
                    }
                    if (i3 < 800) {
                        xtgraphics.clicknow();
                        ++i3;
                    }
                    else {
                        i3 = 0;
                        xtgraphics.fase = 9;
                        this.mouses = 0;
                        this.lostfcs = false;
                    }
                }
                else {
                    this.loadfail(xtgraphics, this.failed);
                }
            }
            if (xtgraphics.fase == 9) {
                if (i3 < 80) {
                    xtgraphics.rad(i3);
                    this.catchlink(0);
                    if (this.mouses == 2) {
                        this.mouses = 0;
                    }
                    if (this.mouses == 1) {
                        this.mouses = 2;
                    }
                    ++i3;
                }
                else {
                    i3 = 0;
                    if (xtgraphics.nofile) {
                        for (int a = 32; a < 39; ++a) {
                            xtgraphics.resetstats(amadness[0], a);
                        }
                    }
                    xtgraphics.fase = 10;
                    this.mouses = 0;
                    this.u[0].falseo(xtgraphics.justcs);
                }
            }
            if (xtgraphics.fase == -9) {
                if (i3 < 2) {
                    this.rd.setColor(new Color(0, 0, 0));
                    this.rd.fillRect(0, 0, 870, 480);
                    ++i3;
                }
                else {
                    xtgraphics.inishcarselect();
                    i3 = 0;
                    xtgraphics.fase = 7;
                    this.mouses = 0;
                }
                xtgraphics.rerun = false;
                xtgraphics.resetmaini(amadness);
                xtgraphics.resetbeasts();
                medium.reset();
                contva.resetfp();
                bots.reset();
            }
            if (xtgraphics.fase == 1110) {
                medium.focus_point = 400;
                if (xtgraphics.tomaini) {
                    xtgraphics.fase = 10;
                    xtgraphics.tomaini = false;
                }
                if (xtgraphics.tocs) {
                    xtgraphics.fase = -9;
                    xtgraphics.tocs = false;
                }
            }
            if (xtgraphics.fase == 49) {
                xtgraphics.ptstart(this.u[0]);
                if (xtgraphics.showopstage == 195) {
                    this.loadstage(aconto2, aconto, medium, trackers, checkpoints, xtgraphics, amadness, record, contva);
                    this.u[0].falseo(xtgraphics.justcs);
                }
                xtgraphics.ctachm(this.xm, this.ym, this.mouses, this.u[0], checkpoints, amadness[0]);
                if (this.mouses == 2) {
                    this.mouses = 0;
                }
                if (this.mouses == 1) {
                    this.mouses = 2;
                }
            }
            if (xtgraphics.fase == 51) {
                xtgraphics.scoreshow(this.u[0]);
                xtgraphics.ctachm(this.xm, this.ym, this.mouses, this.u[0], checkpoints, amadness[0]);
                if (this.mouses == 2) {
                    this.mouses = 0;
                }
                if (this.mouses == 1) {
                    this.mouses = 2;
                }
            }
            if (xtgraphics.fase == -5) {
                xtgraphics.finish(checkpoints, aconto, this.u[0], amadness[0]);
                xtgraphics.ctachm(this.xm, this.ym, this.mouses, this.u[0], checkpoints, amadness[0]);
                if (checkpoints.stage == 31 && xtgraphics.winner) {
                    this.catchlink(1);
                }
                if (this.mouses == 2) {
                    this.mouses = 0;
                }
                if (this.mouses == 1) {
                    this.mouses = 2;
                }
            }
            if (xtgraphics.fase == 7) {
                xtgraphics.carselect(this.u[0], aconto, amadness[0], checkpoints);
                xtgraphics.ctachm(this.xm, this.ym, this.mouses, this.u[0], checkpoints, amadness[0]);
                if (this.mouses == 2) {
                    this.mouses = 0;
                }
                if (this.mouses == 1) {
                    this.mouses = 2;
                }
                if (xtgraphics.shufflefase == 9) {
                    this.autosave = false;
                    this.writedata(xtgraphics, checkpoints, amadness[0]);
                    xtgraphics.savefase = 0;
                    xtgraphics.shufflefase = 10;
                }
                if (xtgraphics.shufflefase == 10) {
                    this.autosave = true;
                    this.writedata(xtgraphics, checkpoints, amadness[0]);
                    xtgraphics.savefase = 0;
                    xtgraphics.shufflefase = 11;
                }
                if (xtgraphics.shufflefase == 11) {
                    this.autosave = false;
                    xtgraphics.shufflefase = 0;
                }
            }
            if (xtgraphics.fase == 6 || xtgraphics.justcs == 5) {
                xtgraphics.musicomp(checkpoints.stage, this.u[0]);
                if (xtgraphics.justcs == -1) {
                    xtgraphics.ctachm(this.xm, this.ym, this.mouses, this.u[0], checkpoints, amadness[0]);
                    if (this.mouses == 2) {
                        this.mouses = 0;
                    }
                    if (this.mouses == 1) {
                        this.mouses = 2;
                    }
                }
            }
            if (xtgraphics.fase == 5) {
                for (int a = 0; a < 3; ++a) {
                    medium.polyoutline[a] = 0;
                }
                medium.switchfase = 0;
                if (!xtgraphics.dontdisplay || (xtgraphics.dontdisplay && xtgraphics.showopstage != 195)) {
                    if (!xtgraphics.bonstage) {
                        xtgraphics.loadmusic(checkpoints.stage, i2, checkpoints);
                    }
                    else {
                        if (xtgraphics.bonusstage[0]) {
                            xtgraphics.loadmusic(75, i2, checkpoints);
                        }
                        if (xtgraphics.bonusstage[1]) {
                            xtgraphics.loadmusic(76, i2, checkpoints);
                        }
                        if (xtgraphics.bonusstage[2]) {
                            xtgraphics.loadmusic(77, i2, checkpoints);
                        }
                        if (xtgraphics.bonusstage[3]) {
                            xtgraphics.loadmusic(94, i2, checkpoints);
                        }
                    }
                }
                if (xtgraphics.dontdisplay && xtgraphics.showopstage == 195) {
                    if (xtgraphics.ptmatch == 1) {
                        xtgraphics.loadmusic(61, i2, checkpoints);
                    }
                    if (xtgraphics.ptmatch == 2) {
                        xtgraphics.loadmusic(74, i2, checkpoints);
                    }
                    if (xtgraphics.ptmatch == 3) {
                        xtgraphics.loadmusic(67, i2, checkpoints);
                    }
                    if (xtgraphics.ptmatch == 4) {
                        xtgraphics.loadmusic(73, i2, checkpoints);
                    }
                    if (xtgraphics.ptmatch == 5) {
                        xtgraphics.loadmusic(35, i2, checkpoints);
                    }
                }
                if (!flag) {
                    flag = true;
                }
            }
            if (xtgraphics.fase == 4) {
                xtgraphics.cantgo(this.u[0], checkpoints);
                xtgraphics.ctachm(this.xm, this.ym, this.mouses, this.u[0], checkpoints, amadness[0]);
                if (this.mouses == 2) {
                    this.mouses = 0;
                }
                if (this.mouses == 1) {
                    this.mouses = 2;
                }
            }
            if (xtgraphics.fase == 3) {
                xtgraphics.loadingfailed(checkpoints.stage, this.u[0]);
                xtgraphics.ctachm(this.xm, this.ym, this.mouses, this.u[0], checkpoints, amadness[0]);
                if (this.mouses == 2) {
                    this.mouses = 0;
                }
                if (this.mouses == 1) {
                    this.mouses = 2;
                }
            }
            if ((xtgraphics.fase == 2 || xtgraphics.fase == 6476) && xtgraphics.justcs == -1) {
                xtgraphics.loadingstage(checkpoints.stage);
            }
            if (xtgraphics.fase == 2 || xtgraphics.justcs == 2 || xtgraphics.replayphase == 2) {
                boolean usebots = false;
                if (xtgraphics.careermode) {
                    boolean hard = false;
                    if (xtgraphics.unlocked[1] == checkpoints.stage || xtgraphics.hardstage) {
                        hard = true;
                    }
                    if (checkpoints.stage == 5 || checkpoints.stage == 9 || checkpoints.stage == 10 || ((checkpoints.stage == 11 || checkpoints.stage == 13) && !xtgraphics.bonusstage[1] && hard) || checkpoints.stage == 14 || checkpoints.stage == 18 || checkpoints.stage == 20 || (checkpoints.stage == 21 && hard)) {
                        usebots = true;
                    }
                }
                if (usebots && xtgraphics.justcs == -1 && xtgraphics.replayphase != 2) {
                    if (!bots.resetonce) {
                        bots.reset();
                        bots.resetonce = true;
                    }
                    else {
                        if (checkpoints.stage == 13) {
                            for (int a2 = xtgraphics.nplayers - 6; a2 < xtgraphics.nplayers; ++a2) {
                                this.loadbots(bots, a2, checkpoints.stage, xtgraphics.actions[1]);
                            }
                            final int p = xtgraphics.nplayers;
                            if (bots.oneloaded[p - 1] && bots.oneloaded[p - 2] && bots.oneloaded[p - 3] && bots.oneloaded[p - 4] && bots.oneloaded[p - 5] && bots.oneloaded[p - 6]) {
                                bots.doneload = true;
                            }
                        }
                        if (checkpoints.stage == 14 || checkpoints.stage == 18 || checkpoints.stage == 10 || checkpoints.stage == 9 || checkpoints.stage == 5 || checkpoints.stage == 20) {
                            this.loadbots(bots, xtgraphics.nplayers - 1, checkpoints.stage, xtgraphics.actions[1]);
                            if (bots.oneloaded[xtgraphics.nplayers - 1]) {
                                bots.doneload = true;
                            }
                        }
                        if (checkpoints.stage == 11 || checkpoints.stage == 21) {
                            this.loadbots(bots, 8, checkpoints.stage, xtgraphics.actions[1]);
                            this.loadbots(bots, 9, checkpoints.stage, xtgraphics.actions[1]);
                            if (bots.oneloaded[8] && bots.oneloaded[9]) {
                                bots.doneload = true;
                            }
                        }
                    }
                }
                else {
                    contva.reset();
                    if (xtgraphics.replayphase == 2) {
                        bots.resettimer();
                    }
                    bots.doneload = true;
                }
                if (bots.doneload) {
                    this.loadstage(aconto2, aconto, medium, trackers, checkpoints, xtgraphics, amadness, record, contva);
                }
                this.u[0].falseo(xtgraphics.justcs);
            }
            if (xtgraphics.fase == -69 || xtgraphics.justcs == 3 || xtgraphics.replayphase == 3) {
                xtgraphics.getstats(amadness, checkpoints);
            }
            if (xtgraphics.fase == 6476 || xtgraphics.justcs == 1 || xtgraphics.replayphase == 1) {
                xtgraphics.rollonce = false;
                xtgraphics.chance = 0.0f;
                xtgraphics.resetbeasts();
                xtgraphics.resetshadows(amadness);
                if (xtgraphics.replayphase == 1) {
                    xtgraphics.resetmaini(amadness);
                    xtgraphics.replayphase = 2;
                }
                else {
                    xtgraphics.randomno(checkpoints);
                    contva.resetfp();
                }
            }
            if (xtgraphics.fase == 1) {
                xtgraphics.trackbg(bots.resetonce = false);
                medium.d(this.rd);
                if (medium.effect[4]) {
                    medium.redrawpolys(this.rd);
                }
                medium.aroundtrack(checkpoints);
                if (checkpoints.stage < xtgraphics.betalimit) {
                    int i4 = 0;
                    final int[] ai = new int[1000];
                    final int extraadd = xtgraphics.nplayers + 0;
                    for (int k4 = xtgraphics.nplayers; k4 < this.notb; ++k4) {
                        if (aconto2[k4].invisiblepiece > 0 && (!medium.effect[9] || k4 < extraadd + 215 || k4 >= extraadd + 625)) {
                            if (aconto2[k4].dist != 0) {
                                ai[i4] = k4;
                                ++i4;
                            }
                            else {
                                aconto2[k4].d(this.rd);
                            }
                        }
                    }
                    final int[] ai2 = new int[i4];
                    for (int j4 = 0; j4 < i4; ++j4) {
                        ai2[j4] = 0;
                    }
                    for (int k5 = 0; k5 < i4; ++k5) {
                        for (int i5 = k5 + 1; i5 < i4; ++i5) {
                            if (aconto2[ai[k5]].dist != aconto2[ai[i5]].dist) {
                                if (aconto2[ai[k5]].dist < aconto2[ai[i5]].dist) {
                                    final int[] array = ai2;
                                    final int n = k5;
                                    ++array[n];
                                }
                                else {
                                    final int[] array2 = ai2;
                                    final int n2 = i5;
                                    ++array2[n2];
                                }
                            }
                            else if (i5 > k5) {
                                final int[] array3 = ai2;
                                final int n3 = k5;
                                ++array3[n3];
                            }
                            else {
                                final int[] array4 = ai2;
                                final int n4 = i5;
                                ++array4[n4];
                            }
                        }
                    }
                    for (int l4 = 0; l4 < i4; ++l4) {
                        for (int j5 = 0; j5 < i4; ++j5) {
                            if (ai2[j5] == l4) {
                                aconto2[ai[j5]].d(this.rd);
                            }
                        }
                    }
                }
                xtgraphics.ctachm(this.xm, this.ym, this.mouses, this.u[0], checkpoints, amadness[0]);
                if (this.mouses == 2) {
                    this.mouses = 0;
                }
                if (this.mouses == 1) {
                    this.mouses = 2;
                }
                xtgraphics.stageselect(checkpoints, this.u[0], amadness);
            }
            if (xtgraphics.fase == 201) {
                xtgraphics.getstats(amadness, checkpoints);
            }
            if (xtgraphics.fase == 202) {
                xtgraphics.fixbg();
            }
            if (xtgraphics.fase == 205) {
                xtgraphics.ctachm(this.xm, this.ym, this.mouses, this.u[0], checkpoints, amadness[0]);
                if (this.mouses == 2) {
                    this.mouses = 0;
                }
                if (this.mouses == 1) {
                    this.mouses = 2;
                }
                xtgraphics.scouting(amadness, checkpoints, this.u[0]);
            }
            if (xtgraphics.fase == 176 || xtgraphics.justcs == 4) {
                medium.d(this.rd);
                if (medium.effect[4]) {
                    medium.redrawpolys(this.rd);
                }
                int j6 = 0;
                final int[] ai3 = new int[400];
                for (int i6 = 0; i6 < this.nob; ++i6) {
                    if (aconto2[i6].dist != 0) {
                        ai3[j6] = i6;
                        ++j6;
                    }
                    else {
                        aconto2[i6].d(this.rd);
                    }
                }
                final int[] ai4 = new int[j6];
                for (int i7 = 0; i7 < j6; ++i7) {
                    ai4[i7] = 0;
                }
                for (int j7 = 0; j7 < j6; ++j7) {
                    for (int k6 = j7 + 1; k6 < j6; ++k6) {
                        if (aconto2[ai3[j7]].dist != aconto2[ai3[k6]].dist) {
                            if (aconto2[ai3[j7]].dist < aconto2[ai3[k6]].dist) {
                                final int[] array5 = ai4;
                                final int n5 = j7;
                                ++array5[n5];
                            }
                            else {
                                final int[] array6 = ai4;
                                final int n6 = k6;
                                ++array6[n6];
                            }
                        }
                        else if (k6 > j7) {
                            final int[] array7 = ai4;
                            final int n7 = j7;
                            ++array7[n7];
                        }
                        else {
                            final int[] array8 = ai4;
                            final int n8 = k6;
                            ++array8[n8];
                        }
                    }
                }
                for (int k7 = 0; k7 < j6; ++k7) {
                    for (int l5 = 0; l5 < j6; ++l5) {
                        if (ai4[l5] == k7) {
                            aconto2[ai3[l5]].d(this.rd);
                        }
                    }
                }
                medium.follow(aconto2[0], 0, 0, 0);
                if (xtgraphics.justcs == -1) {
                    xtgraphics.hipnoload(checkpoints.stage, false);
                }
                if (i2 > 0 || xtgraphics.justcs == 4) {
                    if (xtgraphics.justcs == 4) {
                        xtgraphics.justcs = 5;
                    }
                    else {
                        --i2;
                        xtgraphics.duration = System.nanoTime();
                    }
                }
                else {
                    this.u[0].enter = false;
                    this.u[0].handb = false;
                    if (!xtgraphics.careermode && !xtgraphics.classicmode && xtgraphics.loadedt[checkpoints.stage - 1]) {
                        if (xtgraphics.isMidi[checkpoints.stage - 1]) {
                            xtgraphics.mtracks[checkpoints.stage - 1].play();
                        }
                        else {
                            xtgraphics.stracks[checkpoints.stage - 1].play();
                        }
                    }
                    if (xtgraphics.classicmode && xtgraphics.loadedt[checkpoints.stage + 55]) {
                        if (xtgraphics.isMidi[checkpoints.stage + 55]) {
                            xtgraphics.mtracks[checkpoints.stage + 55].play();
                        }
                        else {
                            xtgraphics.stracks[checkpoints.stage + 55].play();
                        }
                    }
                    if (xtgraphics.careermode) {
                        if (!xtgraphics.bonstage) {
                            int musiccode = checkpoints.stage + 27;
                            if (checkpoints.stage <= 15) {
                                musiccode = checkpoints.stage + 99;
                            }
                            if (xtgraphics.loadedt[musiccode]) {
                                if (xtgraphics.isMidi[musiccode]) {
                                    if (!xtgraphics.isOgg[musiccode]) {
                                        xtgraphics.mtracks[musiccode].play();
                                    }
                                    else {
                                        xtgraphics.mtracks[musiccode].play(true);
                                    }
                                }
                                else {
                                    xtgraphics.stracks[musiccode].play();
                                }
                            }
                        }
                        else {
                            if (xtgraphics.bonusstage[0] && xtgraphics.loadedt[74]) {
                                xtgraphics.stracks[74].play();
                            }
                            if (xtgraphics.bonusstage[1] && xtgraphics.loadedt[75]) {
                                xtgraphics.stracks[75].play();
                            }
                            if (xtgraphics.bonusstage[2] && xtgraphics.loadedt[76]) {
                                xtgraphics.stracks[76].play();
                            }
                            if (xtgraphics.bonusstage[3] && xtgraphics.loadedt[94]) {
                                xtgraphics.mtracks[94].play(true);
                            }
                        }
                    }
                    this.setCursor(new Cursor(0));
                    xtgraphics.fase = 6;
                }
            }
            if (xtgraphics.fase == 0 || xtgraphics.justcs == 6) {
                i2 = 1;
                for (int a = 0; a < xtgraphics.nplayers; ++a) {
                    if (amadness[a].shadowcar) {
                        aconto2[a].shadowcar = true;
                    }
                    else {
                        aconto2[a].shadowcar = false;
                    }
                }
                xtgraphics.ctachm(this.xm, this.ym, this.mouses, this.u[0], checkpoints, amadness[0]);
                if (this.mouses == 2) {
                    this.mouses = 0;
                }
                if (this.mouses == 1) {
                    this.mouses = 2;
                }
                int k8 = 0;
                do {
                    if (amadness[k8].newcar) {
                        final int j8 = aconto2[k8].xz;
                        final int j9 = aconto2[k8].xy;
                        final int l6 = aconto2[k8].zy;
                        if (xtgraphics.beastopponent[k8]) {
                            aconto2[k8] = new ContO(aconto[amadness[k8].cn + 78], aconto2[k8].x, aconto2[k8].y, aconto2[k8].z, 0);
                        }
                        else {
                            aconto2[k8] = new ContO(aconto[amadness[k8].cn], aconto2[k8].x, aconto2[k8].y, aconto2[k8].z, 0);
                        }
                        aconto2[k8].xz = j8;
                        aconto2[k8].xy = j9;
                        aconto2[k8].zy = l6;
                        amadness[k8].newcar = false;
                    }
                } while (++k8 < xtgraphics.nplayers);
                medium.d(this.rd);
                if (medium.effect[4]) {
                    medium.redrawpolys(this.rd);
                }
                k8 = 0;
                final int[] ai5 = new int[200];
                final int renderlimit = this.nob;
                final int extraadd2 = xtgraphics.nplayers + 0;
                if (medium.effect[9] && xtgraphics.starcnt >= 38) {
                    for (int a3 = 0; a3 < renderlimit; ++a3) {
                        aconto2[a3].fakegrounded = aconto2[a3].grounded;
                        if (a3 >= extraadd2 + 215) {
                            aconto2[a3].fakegrounded = aconto2[a3].grounded * 10000.0f;
                            if (a3 >= extraadd2 + 408) {
                                aconto2[a3].fakegrounded = 0.0f;
                            }
                        }
                    }
                }
                for (int k9 = 0; k9 < renderlimit; ++k9) {
                    if (aconto2[k9].dist != 0) {
                        ai5[k8] = k9;
                        ++k8;
                    }
                    else if (!xtgraphics.norender[k9]) {
                        aconto2[k9].d(this.rd);
                    }
                }
                final int[] ai6 = new int[k8];
                final int[] ai7 = new int[k8];
                for (int i8 = 0; i8 < k8; ++i8) {
                    ai6[i8] = 0;
                }
                for (int j10 = 0; j10 < k8; ++j10) {
                    for (int i9 = j10 + 1; i9 < k8; ++i9) {
                        if (aconto2[ai5[j10]].dist != aconto2[ai5[i9]].dist) {
                            if (aconto2[ai5[j10]].dist < aconto2[ai5[i9]].dist) {
                                final int[] array9 = ai6;
                                final int n9 = j10;
                                ++array9[n9];
                            }
                            else {
                                final int[] array10 = ai6;
                                final int n10 = i9;
                                ++array10[n10];
                            }
                        }
                        else if (i9 > j10) {
                            final int[] array11 = ai6;
                            final int n11 = j10;
                            ++array11[n11];
                        }
                        else {
                            final int[] array12 = ai6;
                            final int n12 = i9;
                            ++array12[n12];
                        }
                    }
                    ai7[ai6[j10]] = j10;
                }
                for (int k10 = 0; k10 < k8; ++k10) {
                    if (!xtgraphics.norender[ai5[ai7[k10]]]) {
                        aconto2[ai5[ai7[k10]]].d(this.rd);
                    }
                }
                if (medium.effect[6] || medium.effect[7]) {
                    for (int a4 = 0; a4 < xtgraphics.nplayers; ++a4) {
                        if (medium.effect[6]) {
                            aconto2[a4].flameheight = 100;
                        }
                        final float[] extraneed = new float[2];
                        if (medium.effect[7]) {
                            extraneed[0] = 2.4f;
                            extraneed[1] = 6.0f;
                        }
                        final float griplevel = amadness[a4].gripreset[amadness[a4].cn] + amadness[a4].aigripsp[amadness[a4].cn] * 0.2f;
                        float goalgrip = 24.2f + extraneed[0] + (amadness[xtgraphics.nplayers - 1].level[amadness[xtgraphics.nplayers - 1].cn] * 3 - 1) * 0.2f;
                        if (goalgrip > 42.0f + extraneed[1]) {
                            goalgrip = 42.0f + extraneed[1];
                        }
                        float flame = (griplevel - goalgrip * 5.0f / 6.0f) * 0.3f / (goalgrip / 6.0f) + 0.7f;
                        if (griplevel < goalgrip * 5.0f / 6.0f) {
                            flame = (griplevel - goalgrip / 2.0f) * 0.2f / (goalgrip / 3.0f) + 0.5f;
                        }
                        if (flame < 0.5f) {
                            flame = 0.5f;
                        }
                        if (flame > 1.0f) {
                            flame = 1.0f;
                        }
                        aconto2[a4].weakstage = 100 - (int)((flame - 0.5f) * 200.0f);
                    }
                }
                if (medium.showwater) {
                    xtgraphics.drawwater(medium.cfade[0], medium.cfade[1], medium.cfade[2]);
                }
                boolean racingstage = false;
                final boolean[] racer = new boolean[101];
                if (xtgraphics.careermode) {
                    if ((checkpoints.stage == 5 && !xtgraphics.bonstage) || checkpoints.stage == 9 || checkpoints.stage == 10 || checkpoints.stage == 14 || checkpoints.stage == 20) {
                        racingstage = true;
                    }
                    for (int a5 = 0; a5 < 101; ++a5) {
                        if (amadness[a5].aitssp[amadness[a5].cn] >= amadness[a5].aistrsp[amadness[a5].cn] || checkpoints.clear[a5] >= 3) {
                            racer[a5] = true;
                        }
                    }
                }
                boolean noff = false;
                if (xtgraphics.careermode && checkpoints.stage == 23 && xtgraphics.bossbattle) {
                    noff = true;
                }
                final boolean[][] ghostmode = new boolean[101][101];
                for (int a6 = 0; a6 < xtgraphics.nplayers; ++a6) {
                    for (int b = 0; b < xtgraphics.nplayers; ++b) {
                        ghostmode[a6][b] = false;
                    }
                    if (racingstage && racer[a6]) {
                        for (int b = 0; b < xtgraphics.nplayers; ++b) {
                            if (a6 != b && checkpoints.pos[a6] == 0 && checkpoints.pos[b] == 1 && checkpoints.clear[a6] == checkpoints.clear[b]) {
                                ghostmode[a6][b] = true;
                            }
                        }
                    }
                    if (xtgraphics.careermode) {
                        if (checkpoints.stage == 13) {
                            if (amadness[a6].forcehandb || amadness[a6].teleinvul > 0) {
                                for (int b = 0; b < xtgraphics.nplayers; ++b) {
                                    ghostmode[a6][b] = true;
                                }
                            }
                            for (int b = 0; b < xtgraphics.nplayers; ++b) {
                                if (a6 != b && amadness[a6].isabot && amadness[b].isabot) {
                                    ghostmode[a6][b] = true;
                                }
                            }
                            if (aconto2[a6].floorguardian && !xtgraphics.nolevels) {
                                for (int b = 1; b < xtgraphics.nplayers; ++b) {
                                    if (a6 != b && (!xtgraphics.beastopponent[b] || aconto2[b].floorguardian)) {
                                        ghostmode[a6][b] = true;
                                    }
                                }
                            }
                        }
                        if (checkpoints.stage == 17 && a6 >= 1 && a6 <= 3) {
                            for (int b = 0; b < xtgraphics.nplayers; ++b) {
                                if (b != xtgraphics.undeadtarget) {
                                    ghostmode[a6][b] = true;
                                }
                            }
                        }
                    }
                    if (xtgraphics.entered[a6] || xtgraphics.bonusstage[5]) {
                        for (int b = 0; b < xtgraphics.nplayers; ++b) {
                            ghostmode[a6][b] = true;
                        }
                    }
                }
                for (int a6 = 1; a6 < xtgraphics.nplayers; ++a6) {
                    if (xtgraphics.careermode && checkpoints.stage == 11 && !xtgraphics.bonusstage[1] && (xtgraphics.hardstage || xtgraphics.unlocked[1] == 11)) {
                        if (amadness[a6].cn == 12 && !bots.botbreak[a6]) {
                            for (int b = 1; b < xtgraphics.nplayers; ++b) {
                                ghostmode[a6][b] = true;
                            }
                            if (amadness[0].aistrsp[amadness[0].cn] <= amadness[0].aitssp[amadness[0].cn]) {
                                for (int b = 0; b < xtgraphics.nplayers; ++b) {
                                    if (a6 != b && checkpoints.pos[a6] == 0 && checkpoints.pos[b] == 1 && checkpoints.clear[a6] == checkpoints.clear[b]) {
                                        ghostmode[a6][b] = true;
                                    }
                                }
                                if (checkpoints.clear[0] >= 3 && Math.abs(checkpoints.clear[0] - checkpoints.clear[a6]) <= 3) {
                                    ghostmode[a6][0] = true;
                                }
                            }
                        }
                        if (xtgraphics.undead[a6]) {
                            for (int b = 0; b < xtgraphics.nplayers; ++b) {
                                if (a6 != b && a6 > 4 && (b != this.u[a6].acr || xtgraphics.safezone[b])) {
                                    ghostmode[a6][b] = true;
                                }
                            }
                        }
                    }
                    if (xtgraphics.invulnerable || xtgraphics.nohit[a6] || xtgraphics.bonstage || noff) {
                        for (int b = 1; b < xtgraphics.nplayers; ++b) {
                            ghostmode[a6][b] = true;
                        }
                    }
                }
                if (xtgraphics.starcnt == 0) {
                    boolean usebots2 = false;
                    if (xtgraphics.careermode) {
                        boolean hard2 = false;
                        if (xtgraphics.unlocked[1] == checkpoints.stage || xtgraphics.hardstage) {
                            hard2 = true;
                        }
                        boolean cantbot = false;
                        if (xtgraphics.scalelevels || xtgraphics.nolevels) {
                            cantbot = true;
                        }
                        if (checkpoints.stage == 5 || checkpoints.stage == 9 || checkpoints.stage == 10 || checkpoints.stage == 13 || checkpoints.stage == 14 || checkpoints.stage == 18 || checkpoints.stage == 20 || ((checkpoints.stage == 11 || checkpoints.stage == 21) && hard2)) {
                            usebots2 = true;
                        }
                        if (checkpoints.stage == 5 || checkpoints.stage == 9 || checkpoints.stage == 10 || checkpoints.stage == 14) {
                            final int bot = xtgraphics.nplayers - 1;
                            int whichcar = 10;
                            int threshold = 2;
                            if (checkpoints.stage == 9 || checkpoints.stage == 10) {
                                whichcar = 12;
                                if (checkpoints.stage == 9) {
                                    if (checkpoints.clear[0] >= 2 && checkpoints.clear[0] <= 4) {
                                        threshold = 4;
                                    }
                                    if (checkpoints.clear[0] >= 9 && checkpoints.clear[0] <= 15) {
                                        threshold = 7;
                                    }
                                    if (checkpoints.clear[0] >= 15 && checkpoints.clear[0] <= 19) {
                                        threshold = 5;
                                    }
                                }
                            }
                            if (checkpoints.stage == 14) {
                                whichcar = 14;
                            }
                            final int distap = this.u[bot].py(aconto2[bot].x / 100, aconto2[0].x / 100, aconto2[bot].z / 100, aconto2[0].z / 100);
                            if ((distap < 10000 && checkpoints.clear[bot] - checkpoints.clear[0] >= threshold) || amadness[bot].cn != whichcar || cantbot || amadness[bot].frozen) {
                                bots.botbreak[bot] = true;
                            }
                            amadness[bot].isabot = true;
                            if (bots.botbreak[bot]) {
                                amadness[bot].isabot = false;
                            }
                        }
                        if (checkpoints.stage == 13) {
                            for (int bot = xtgraphics.nplayers - 6; bot < xtgraphics.nplayers; ++bot) {
                                final int distap2 = this.u[bot].py(aconto2[bot].x / 100, aconto2[0].x / 100, aconto2[bot].z / 100, aconto2[0].z / 100);
                                boolean worthdodging = false;
                                int rightcar = amadness[bot].cn;
                                if (bot == xtgraphics.nplayers - 1) {
                                    rightcar = 14;
                                }
                                int threshold2 = 4;
                                if (xtgraphics.floor[bot] == 0 || xtgraphics.floor[bot] == 2) {
                                    threshold2 = 5;
                                }
                                boolean nobreak = false;
                                if (xtgraphics.floor[bot] != xtgraphics.floor[0]) {
                                    nobreak = true;
                                }
                                if (amadness[0].moment[amadness[0].cn] > amadness[bot].moment[amadness[bot].cn] + 1.0f) {
                                    worthdodging = true;
                                }
                                if ((distap2 < 8000 && checkpoints.clear[bot] - checkpoints.clear[0] >= threshold2 && worthdodging && !nobreak) || amadness[bot].cn != rightcar || amadness[bot].frozen || cantbot) {
                                    bots.botbreak[bot] = true;
                                }
                                amadness[bot].isabot = true;
                                if (bots.botbreak[bot]) {
                                    amadness[bot].isabot = false;
                                }
                                if (!hard2) {
                                    bots.botbreak[bot] = true;
                                    amadness[bot].isabot = false;
                                }
                            }
                        }
                        if (checkpoints.stage == 18) {
                            final int bot = xtgraphics.nplayers - 1;
                            final int distap2 = this.u[bot].py(aconto2[bot].x / 100, aconto2[0].x / 100, aconto2[bot].z / 100, aconto2[0].z / 100);
                            boolean worthdodging = false;
                            if (amadness[0].moment[amadness[0].cn] > amadness[bot].moment[amadness[bot].cn]) {
                                worthdodging = true;
                            }
                            if ((distap2 < 6500 && checkpoints.clear[bot] - checkpoints.clear[0] >= 4 && worthdodging) || amadness[bot].cn != 16 || amadness[bot].frozen || contva.biglead[0] || cantbot) {
                                bots.botbreak[bot] = true;
                            }
                            amadness[bot].isabot = true;
                            if (bots.botbreak[bot]) {
                                amadness[bot].isabot = false;
                            }
                        }
                        if ((checkpoints.stage == 11 || checkpoints.stage == 21) && hard2) {
                            for (int bot = 8; bot < xtgraphics.nplayers - 1; ++bot) {
                                final int distap2 = this.u[bot].py(aconto2[bot].x / 100, aconto2[0].x / 100, aconto2[bot].z / 100, aconto2[0].z / 100);
                                boolean worthdodging = false;
                                int rightcar = 12;
                                if (checkpoints.stage == 21) {
                                    rightcar = 17;
                                }
                                if (amadness[0].moment[amadness[0].cn] > amadness[bot].moment[amadness[bot].cn] + 1.0f) {
                                    worthdodging = true;
                                }
                                if ((distap2 < 10000 && checkpoints.clear[bot] - checkpoints.clear[0] >= 4 && worthdodging) || amadness[bot].cn != rightcar || amadness[bot].frozen || cantbot) {
                                    bots.botbreak[bot] = true;
                                }
                                amadness[bot].isabot = true;
                                if (bots.botbreak[bot]) {
                                    amadness[bot].isabot = false;
                                }
                            }
                        }
                        if (usebots2) {
                            final Bots bots2 = bots;
                            ++bots2.timer;
                            for (int a7 = 0; a7 < xtgraphics.nplayers; ++a7) {
                                final int[] specialtimer2 = bots.specialtimer;
                                final int n13 = a7;
                                ++specialtimer2[n13];
                            }
                        }
                    }
                    boolean specialtimer = false;
                    if (checkpoints.stage == 13 && (xtgraphics.unlocked[1] == checkpoints.stage || xtgraphics.hardstage)) {
                        specialtimer = true;
                    }
                    for (int a8 = 1; a8 < xtgraphics.nplayers; ++a8) {
                        if (bots.oneloaded[a8] && !bots.botbreak[a8]) {
                            bots.runbots(this.u, usebots2, a8, specialtimer);
                        }
                    }
                    if (xtgraphics.makebot) {
                        xtgraphics.testbots(bots, checkpoints, this.u, usebots2);
                    }
                    for (int a8 = 0; a8 < xtgraphics.nplayers; ++a8) {
                        for (int b2 = 0; b2 < xtgraphics.nplayers; ++b2) {
                            if (a8 != b2 && !ghostmode[a8][b2] && !ghostmode[b2][a8]) {
                                amadness[a8].colide(aconto2[a8], amadness[b2], aconto2[b2], checkpoints, bots);
                            }
                        }
                    }
                    for (int l7 = 0; l7 < xtgraphics.nplayers; ++l7) {
                        if (!amadness[l7].respawning) {
                            amadness[l7].drive(this.u[l7], aconto2[l7], trackers, checkpoints, contva, bots);
                        }
                        else {
                            amadness[l7].reseto(xtgraphics.sc[l7], aconto[l7], checkpoints);
                        }
                    }
                    int l8 = 0;
                    do {
                        record.rec(aconto2[l8], l8, amadness[l8].squash, amadness[0].lastcolido[0], amadness[l8].cntdest, xtgraphics.nplayers);
                    } while (++l8 < xtgraphics.nplayers);
                    checkpoints.checkstat(amadness, aconto2, record, xtgraphics);
                    for (int a7 = 0; a7 < xtgraphics.nplayers; ++a7) {
                        if (amadness[a7].isabot || (xtgraphics.makebot && a7 == 0)) {
                            this.u[a7].wall = -1;
                            amadness[a7].surfer[0] = false;
                        }
                    }
                    if (xtgraphics.justcs == 6) {
                        l8 = 0;
                    }
                    else {
                        l8 = 1;
                    }
                    do {
                        boolean noai = false;
                        if (amadness[l8].isabot) {
                            noai = true;
                        }
                        if (xtgraphics.careermode && checkpoints.stage == 13 && (amadness[l8].forcehandb || xtgraphics.speedhack[l8] > 0)) {
                            this.u[l8].down = false;
                            this.u[l8].left = false;
                            this.u[l8].right = false;
                            this.u[l8].handb = false;
                            noai = true;
                        }
                        if (!noai) {
                            this.u[l8].preform(amadness[l8], aconto2[l8], checkpoints, trackers, xtgraphics, amadness[0], bots);
                        }
                    } while (++l8 < xtgraphics.nplayers);
                    l8 = 0;
                    do {
                        boolean noai = false;
                        if (amadness[l8].isabot) {
                            noai = true;
                        }
                        if (xtgraphics.careermode && checkpoints.stage == 13 && (amadness[l8].forcehandb || xtgraphics.speedhack[l8] > 0)) {
                            noai = true;
                        }
                        if (!noai) {
                            contva.sortvariables(amadness[l8], checkpoints, this.u, xtgraphics.careermode, xtgraphics.nplayers);
                        }
                    } while (++l8 < xtgraphics.nplayers);
                }
                else {
                    if (xtgraphics.starcnt == 130) {
                        medium.adv = 1900;
                        medium.zy = 40;
                        medium.vxz = 70;
                        this.rd.setColor(new Color(255, 255, 255));
                        this.rd.fillRect(0, 0, 870, 480);
                    }
                    if (xtgraphics.starcnt != 0) {
                        final xtGraphics xtGraphics = xtgraphics;
                        --xtGraphics.starcnt;
                    }
                }
                if (xtgraphics.justcs == 6 && xtgraphics.starcnt != 0) {
                    checkpoints.checkstat(amadness, aconto2, record, xtgraphics);
                    xtgraphics.starcnt = 0;
                }
                if (xtgraphics.starcnt < 38) {
                    if (xtgraphics.dontdisplay) {
                        xtgraphics.tourney(amadness, checkpoints, this.u[0], aconto2);
                    }
                    xtgraphics.nitroandspecials(amadness, checkpoints, this.u, aconto2, this.view);
                    int viewboost = 0;
                    int thebot = 0;
                    if (xtgraphics.viewbot) {
                        thebot = xtgraphics.nplayers - 1;
                    }
                    final int lookback = this.u[0].lookback;
                    if (xtgraphics.scareflash && xtgraphics.scareflashtime >= 149 && xtgraphics.scareflashtime % 30 == 29) {
                        thebot = xtgraphics.nplayers + 1;
                        viewboost = -200;
                    }
                    int whichfol = thebot;
                    if (amadness[whichfol].cn == 18) {
                        viewboost = 65;
                    }
                    if (amadness[whichfol].cn == 20) {
                        viewboost = 130;
                    }
                    if (amadness[whichfol].cn == 22) {
                        viewboost = 300;
                    }
                    if (xtgraphics.careermode && checkpoints.stage == 6 && xtgraphics.shownghost && amadness[0].dest && xtgraphics.holdcnt > 85) {
                        whichfol = xtgraphics.nplayers + 2;
                    }
                    if (!xtgraphics.ghosttele) {
                        if (this.view == 0) {
                            medium.follow(aconto2[whichfol], amadness[whichfol].cxz, this.u[0].lookback, viewboost);
                            xtgraphics.stat(amadness, checkpoints, this.u[0], aconto2, contva, true);
                        }
                        else {
                            medium.watch(aconto2[whichfol], amadness[whichfol].cxz / 15.0, viewboost);
                            xtgraphics.stat(amadness, checkpoints, this.u[0], aconto2, contva, true);
                        }
                    }
                    else {
                        medium.follow(aconto2[whichfol], 0, 0, viewboost);
                        xtgraphics.stat(amadness, checkpoints, this.u[0], aconto2, contva, true);
                    }
                    if (xtgraphics.starcnt == 36) {
                        this.repaint();
                        xtgraphics.blendude(this.offImage);
                    }
                    if (xtgraphics.starcnt == 0) {
                        xtgraphics.realwalls(aconto2, amadness, checkpoints.stage);
                    }
                    if (xtgraphics.careermode) {
                        xtgraphics.careermode(amadness, checkpoints, this.u, aconto2, trackers, contva);
                    }
                }
                else {
                    int cararound = 5;
                    if (xtgraphics.careermode && (xtgraphics.bonusstage[1] || checkpoints.stage == 9 || checkpoints.stage == 20 || xtgraphics.bonusstage[3] || checkpoints.stage == 13)) {
                        cararound = 0;
                    }
                    medium.around(aconto2[cararound], true);
                    if (this.u[0].enter || this.u[0].handb) {
                        xtgraphics.starcnt = 38;
                        this.u[0].enter = false;
                        this.u[0].handb = false;
                    }
                    if (xtgraphics.starcnt == 38) {
                        this.mouses = 0;
                        medium.vert = false;
                        medium.adv = 900;
                        medium.vxz = 180;
                        checkpoints.checkstat(amadness, aconto2, record, xtgraphics);
                        medium.follow(aconto2[0], amadness[0].cxz, 0, 0);
                        xtgraphics.stat(amadness, checkpoints, this.u[0], aconto2, contva, true);
                        this.rd.setColor(new Color(255, 255, 255));
                        this.rd.fillRect(0, 0, 870, 480);
                    }
                }
                if (xtgraphics.replayfade) {
                    if (xtgraphics.replayphase == 0) {
                        if (xtgraphics.replaytrans < 255) {
                            final xtGraphics xtGraphics2 = xtgraphics;
                            xtGraphics2.replaytrans += 25;
                        }
                        if (xtgraphics.replaytrans > 255) {
                            xtgraphics.replaytrans = 255;
                        }
                        if (xtgraphics.replaytrans == 255) {
                            xtgraphics.replayphase = 1;
                        }
                    }
                    if (xtgraphics.replayphase == 4) {
                        if (xtgraphics.replaytrans > 0) {
                            final xtGraphics xtGraphics3 = xtgraphics;
                            xtGraphics3.replaytrans -= 10;
                        }
                        if (xtgraphics.replaytrans < 0) {
                            xtgraphics.replaytrans = 0;
                        }
                        if (xtgraphics.replaytrans == 0) {
                            xtgraphics.replayphase = 0;
                            xtgraphics.replayfade = false;
                        }
                    }
                    this.rd.setColor(new Color(0, 0, 0, xtgraphics.replaytrans));
                    this.rd.fillRect(0, 0, 870, 480);
                }
            }
            if (xtgraphics.fase == 0) {
                for (int a = 0; a < xtgraphics.nplayers; ++a) {
                    amadness[a].oldfcnt = aconto2[a].fcnt;
                }
            }
            if (xtgraphics.fase == 609) {
                for (int a = 0; a < xtgraphics.nplayers; ++a) {
                    aconto2[a].fcnt = amadness[a].oldfcnt;
                    if (aconto2[a].fcnt > 0) {
                        aconto2[a].fix = true;
                    }
                    if (amadness[a].dest || xtgraphics.undead[a]) {
                        amadness[a].distruct(aconto2[a]);
                    }
                }
                xtgraphics.fase = 0;
            }
            if (xtgraphics.fase == -1) {
                if (k2 == 0) {
                    int i10 = 0;
                    do {
                        record.ocar[i10] = new ContO(aconto2[i10], 0, 0, 0, 0);
                        aconto2[i10] = new ContO(record.car[0][i10], 0, 0, 0, 0);
                    } while (++i10 < xtgraphics.nplayers);
                }
                medium.d(this.rd);
                if (medium.effect[4]) {
                    medium.redrawpolys(this.rd);
                }
                if (medium.effect[6] || medium.effect[7]) {
                    for (int a = 0; a < xtgraphics.nplayers; ++a) {
                        if (medium.effect[6]) {
                            aconto2[a].flameheight = 100;
                        }
                        final float[] extraneed2 = new float[2];
                        if (medium.effect[7]) {
                            extraneed2[0] = 2.4f;
                            extraneed2[1] = 6.0f;
                        }
                        final float griplevel2 = amadness[a].gripreset[amadness[a].cn] + amadness[a].aigripsp[amadness[a].cn] * 0.2f;
                        float goalgrip2 = 24.2f + extraneed2[0] + (amadness[xtgraphics.nplayers - 1].level[amadness[xtgraphics.nplayers - 1].cn] * 3 - 1) * 0.2f;
                        if (goalgrip2 > 42.0f + extraneed2[1]) {
                            goalgrip2 = 42.0f + extraneed2[1];
                        }
                        float flame2 = (griplevel2 - goalgrip2 * 5.0f / 6.0f) * 0.3f / (goalgrip2 / 6.0f) + 0.7f;
                        if (griplevel2 < goalgrip2 * 5.0f / 6.0f) {
                            flame2 = (griplevel2 - goalgrip2 / 2.0f) * 0.2f / (goalgrip2 / 3.0f) + 0.5f;
                        }
                        if (flame2 < 0.5f) {
                            flame2 = 0.5f;
                        }
                        if (flame2 > 1.0f) {
                            flame2 = 1.0f;
                        }
                        aconto2[a].weakstage = 100 - (int)((flame2 - 0.5f) * 200.0f);
                    }
                }
                int j11 = 0;
                final int[] ai8 = new int[400];
                for (int l9 = 0; l9 < this.nob; ++l9) {
                    if (aconto2[l9].dist != 0) {
                        ai8[j11] = l9;
                        ++j11;
                    }
                    else {
                        aconto2[l9].d(this.rd);
                    }
                }
                final int[] ai9 = new int[j11];
                for (int i11 = 0; i11 < j11; ++i11) {
                    ai9[i11] = 0;
                }
                for (int j12 = 0; j12 < j11; ++j12) {
                    for (int i12 = j12 + 1; i12 < j11; ++i12) {
                        if (aconto2[ai8[j12]].dist != aconto2[ai8[i12]].dist) {
                            if (aconto2[ai8[j12]].dist < aconto2[ai8[i12]].dist) {
                                final int[] array13 = ai9;
                                final int n14 = j12;
                                ++array13[n14];
                            }
                            else {
                                final int[] array14 = ai9;
                                final int n15 = i12;
                                ++array14[n15];
                            }
                        }
                        else if (i12 > j12) {
                            final int[] array15 = ai9;
                            final int n16 = j12;
                            ++array15[n16];
                        }
                        else {
                            final int[] array16 = ai9;
                            final int n17 = i12;
                            ++array16[n17];
                        }
                    }
                }
                for (int k11 = 0; k11 < j11; ++k11) {
                    for (int j13 = 0; j13 < j11; ++j13) {
                        if (ai9[j13] == k11) {
                            aconto2[ai8[j13]].d(this.rd);
                        }
                    }
                }
                if (this.u[0].enter || this.u[0].handb || this.mouses == 1) {
                    k2 = 299;
                    this.u[0].enter = false;
                    this.u[0].handb = false;
                    this.mouses = 0;
                }
                int l10 = 0;
                do {
                    if (record.fix[l10] == k2) {
                        if (aconto2[l10].dist == 0) {
                            aconto2[l10].fcnt = 8;
                        }
                        else {
                            aconto2[l10].fix = true;
                        }
                    }
                    if (aconto2[l10].fcnt == 7 || aconto2[l10].fcnt == 8) {
                        aconto2[l10] = new ContO(aconto[amadness[l10].cn], 0, 0, 0, 0);
                        record.cntdest[l10] = 0;
                    }
                    if (k2 == 299) {
                        aconto2[l10] = new ContO(record.ocar[l10], 0, 0, 0, 0);
                    }
                    record.play(aconto2[l10], amadness[l10], l10, k2);
                } while (++l10 < xtgraphics.nplayers);
                if (++k2 == 300) {
                    k2 = 0;
                    xtgraphics.fase = -6;
                }
                else {
                    xtgraphics.replyn();
                }
                medium.around(aconto2[0], false);
                if (medium.showwater) {
                    xtgraphics.drawwater(medium.cfade[0], medium.cfade[1], medium.cfade[2]);
                }
            }
            if (xtgraphics.fase == -2) {
                if (record.hcaught && record.wasted == 0 && record.whenwasted != 229 && checkpoints.stage <= 2 && xtgraphics.looped != 0) {
                    record.hcaught = false;
                }
                if (record.hcaught) {
                    medium.adv = (int)(900.0f * medium.random());
                    medium.vxz = (int)(360.0f * medium.random());
                    k2 = 0;
                    xtgraphics.fase = -3;
                    i3 = 0;
                    j3 = 0;
                }
                else {
                    k2 = -2;
                    xtgraphics.fase = -4;
                }
            }
            if (xtgraphics.fase == -3) {
                if (k2 == 0) {
                    if (record.wasted == 0) {
                        if (record.whenwasted == 229) {
                            k3 = 67;
                            final Medium medium2 = medium;
                            medium2.vxz += 90;
                        }
                        else {
                            k3 = (int)(medium.random() * 4.0f);
                            if (k3 == 1 || k3 == 3) {
                                k3 = 69;
                            }
                            if (k3 == 2 || k3 == 4) {
                                k3 = 30;
                            }
                        }
                    }
                    else if (record.closefinish != 0 && j3 != 0) {
                        final Medium medium3 = medium;
                        medium3.vxz += 90;
                    }
                    int k12 = 0;
                    do {
                        aconto2[k12] = new ContO(record.starcar[k12], 0, 0, 0, 0);
                    } while (++k12 < xtgraphics.nplayers);
                }
                medium.d(this.rd);
                if (medium.effect[4]) {
                    medium.redrawpolys(this.rd);
                }
                int i13 = 0;
                final int[] ai10 = new int[900];
                for (int i14 = 0; i14 < this.nob; ++i14) {
                    if (aconto2[i14].dist != 0) {
                        ai10[i13] = i14;
                        ++i13;
                    }
                    else {
                        aconto2[i14].d(this.rd);
                    }
                }
                final int[] ai11 = new int[i13];
                for (int i15 = 0; i15 < i13; ++i15) {
                    ai11[i15] = 0;
                }
                for (int j14 = 0; j14 < i13; ++j14) {
                    for (int k13 = j14 + 1; k13 < i13; ++k13) {
                        if (aconto2[ai10[j14]].dist != aconto2[ai10[k13]].dist) {
                            if (aconto2[ai10[j14]].dist < aconto2[ai10[k13]].dist) {
                                final int[] array17 = ai11;
                                final int n18 = j14;
                                ++array17[n18];
                            }
                            else {
                                final int[] array18 = ai11;
                                final int n19 = k13;
                                ++array18[n19];
                            }
                        }
                        else if (k13 > j14) {
                            final int[] array19 = ai11;
                            final int n20 = j14;
                            ++array19[n20];
                        }
                        else {
                            final int[] array20 = ai11;
                            final int n21 = k13;
                            ++array20[n21];
                        }
                    }
                }
                for (int k14 = 0; k14 < i13; ++k14) {
                    for (int l11 = 0; l11 < i13; ++l11) {
                        if (ai11[l11] == k14) {
                            aconto2[ai10[l11]].d(this.rd);
                        }
                    }
                }
                int l12 = 0;
                do {
                    if (record.hfix[l12] == k2) {
                        if (aconto2[l12].dist == 0) {
                            aconto2[l12].fcnt = 8;
                        }
                        else {
                            aconto2[l12].fix = true;
                        }
                    }
                    if (aconto2[l12].fcnt == 7 || aconto2[l12].fcnt == 8) {
                        aconto2[l12] = new ContO(aconto[amadness[l12].cn], 0, 0, 0, 0);
                        record.cntdest[l12] = 0;
                    }
                    record.playh(aconto2[l12], amadness[l12], l12, k2);
                } while (++l12 < xtgraphics.nplayers);
                if (j3 == 2 && k2 == 299) {
                    this.u[0].enter = true;
                }
                if (this.u[0].enter || this.u[0].handb) {
                    xtgraphics.fase = -4;
                    this.u[0].enter = false;
                    this.u[0].handb = false;
                    k2 = -7;
                }
                else {
                    xtgraphics.levelhigh(record.wasted, record.whenwasted, record.closefinish, k2, checkpoints.stage);
                    if (k2 == 0 || k2 == 1 || k2 == 2) {
                        this.rd.setColor(new Color(0, 0, 0));
                        this.rd.fillRect(0, 0, 870, 480);
                    }
                    if (record.wasted != 0) {
                        if (record.closefinish == 0) {
                            if (i3 == 9 || i3 == 11) {
                                this.rd.setColor(new Color(255, 255, 255));
                                this.rd.fillRect(0, 0, 870, 480);
                            }
                            if (i3 == 0) {
                                medium.around(aconto2[0], false);
                            }
                            if (i3 > 0 && i3 < 20) {
                                medium.transaround(aconto2[0], aconto2[record.wasted], i3);
                            }
                            if (i3 == 20) {
                                medium.around(aconto2[record.wasted], false);
                            }
                            if (k2 > record.whenwasted && i3 != 20) {
                                ++i3;
                            }
                            if ((i3 == 0 || i3 == 20) && ++k2 == 300) {
                                k2 = 0;
                                i3 = 0;
                                ++j3;
                            }
                        }
                        else if (record.closefinish == 1) {
                            if (i3 == 0) {
                                medium.around(aconto2[0], false);
                            }
                            if (i3 > 0 && i3 < 20) {
                                medium.transaround(aconto2[0], aconto2[record.wasted], i3);
                            }
                            if (i3 == 20) {
                                medium.around(aconto2[record.wasted], false);
                            }
                            if (i3 > 20 && i3 < 40) {
                                medium.transaround(aconto2[record.wasted], aconto2[0], i3 - 20);
                            }
                            if (i3 == 40) {
                                medium.around(aconto2[0], false);
                            }
                            if (i3 > 40 && i3 < 60) {
                                medium.transaround(aconto2[0], aconto2[record.wasted], i3 - 40);
                            }
                            if (i3 == 60) {
                                medium.around(aconto2[record.wasted], false);
                            }
                            if (k2 > 160 && i3 < 20) {
                                ++i3;
                            }
                            if (k2 > 230 && i3 < 40) {
                                ++i3;
                            }
                            if (k2 > 280 && i3 < 60) {
                                ++i3;
                            }
                            if ((i3 == 0 || i3 == 20 || i3 == 40 || i3 == 60) && ++k2 == 300) {
                                k2 = 0;
                                i3 = 0;
                                ++j3;
                            }
                        }
                        else {
                            if (i3 == 0) {
                                medium.around(aconto2[0], false);
                            }
                            if (i3 > 0 && i3 < 20) {
                                medium.transaround(aconto2[0], aconto2[record.wasted], i3);
                            }
                            if (i3 == 20) {
                                medium.around(aconto2[record.wasted], false);
                            }
                            if (i3 > 20 && i3 < 40) {
                                medium.transaround(aconto2[record.wasted], aconto2[0], i3 - 20);
                            }
                            if (i3 == 40) {
                                medium.around(aconto2[0], false);
                            }
                            if (i3 > 40 && i3 < 60) {
                                medium.transaround(aconto2[0], aconto2[record.wasted], i3 - 40);
                            }
                            if (i3 == 60) {
                                medium.around(aconto2[record.wasted], false);
                            }
                            if (i3 > 60 && i3 < 80) {
                                medium.transaround(aconto2[record.wasted], aconto2[0], i3 - 60);
                            }
                            if (i3 == 80) {
                                medium.around(aconto2[0], false);
                            }
                            if (k2 > 90 && i3 < 20) {
                                ++i3;
                            }
                            if (k2 > 160 && i3 < 40) {
                                ++i3;
                            }
                            if (k2 > 230 && i3 < 60) {
                                ++i3;
                            }
                            if (k2 > 280 && i3 < 80) {
                                ++i3;
                            }
                            if ((i3 == 0 || i3 == 20 || i3 == 40 || i3 == 60 || i3 == 80) && ++k2 == 300) {
                                k2 = 0;
                                i3 = 0;
                                ++j3;
                            }
                        }
                    }
                    else {
                        if (k3 == 67 && (i3 == 3 || i3 == 31 || i3 == 66)) {
                            this.rd.setColor(new Color(255, 255, 255));
                            this.rd.fillRect(0, 0, 870, 480);
                        }
                        if (k3 == 69 && (i3 == 3 || i3 == 5 || i3 == 31 || i3 == 33 || i3 == 66 || i3 == 68)) {
                            this.rd.setColor(new Color(255, 255, 255));
                            this.rd.fillRect(0, 0, 870, 480);
                        }
                        if (k3 == 30 && i3 >= 1 && i3 < 30) {
                            if (i3 % (int)(2.0f + medium.random() * 3.0f) == 0 && !flag3) {
                                this.rd.setColor(new Color(255, 255, 255));
                                this.rd.fillRect(0, 0, 870, 480);
                                flag3 = true;
                            }
                            else {
                                flag3 = false;
                            }
                        }
                        if (k2 > record.whenwasted && i3 != k3) {
                            ++i3;
                        }
                        medium.around(aconto2[0], false);
                        if ((i3 == 0 || i3 == k3) && ++k2 == 300) {
                            k2 = 0;
                            i3 = 0;
                            ++j3;
                        }
                    }
                }
                if (medium.effect[6] || medium.effect[7]) {
                    for (int a3 = 0; a3 < xtgraphics.nplayers; ++a3) {
                        if (medium.effect[6]) {
                            aconto2[a3].flameheight = 100;
                        }
                        final float[] extraneed3 = new float[2];
                        if (medium.effect[7]) {
                            extraneed3[0] = 2.4f;
                            extraneed3[1] = 6.0f;
                        }
                        final float griplevel3 = amadness[a3].gripreset[amadness[a3].cn] + amadness[a3].aigripsp[amadness[a3].cn] * 0.2f;
                        float goalgrip3 = 24.2f + extraneed3[0] + (amadness[xtgraphics.nplayers - 1].level[amadness[xtgraphics.nplayers - 1].cn] * 3 - 1) * 0.2f;
                        if (goalgrip3 > 42.0f + extraneed3[1]) {
                            goalgrip3 = 42.0f + extraneed3[1];
                        }
                        float flame3 = (griplevel3 - goalgrip3 * 5.0f / 6.0f) * 0.3f / (goalgrip3 / 6.0f) + 0.7f;
                        if (griplevel3 < goalgrip3 * 5.0f / 6.0f) {
                            flame3 = (griplevel3 - goalgrip3 / 2.0f) * 0.2f / (goalgrip3 / 3.0f) + 0.5f;
                        }
                        if (flame3 < 0.5f) {
                            flame3 = 0.5f;
                        }
                        if (flame3 > 1.0f) {
                            flame3 = 1.0f;
                        }
                        aconto2[a3].weakstage = 100 - (int)((flame3 - 0.5f) * 200.0f);
                    }
                }
                if (medium.showwater) {
                    xtgraphics.drawwater(medium.cfade[0], medium.cfade[1], medium.cfade[2]);
                }
            }
            if (xtgraphics.fase == -4) {
                if (k2 <= 0) {
                    this.rd.drawImage(xtgraphics.mdness, 224, 30, null);
                    this.rd.drawImage(xtgraphics.dude[0], 70, 10, null);
                }
                if (k2 >= 0) {
                    xtgraphics.fleximage(this.offImage, k2, checkpoints.stage);
                }
                ++k2;
                if (checkpoints.stage == 31 && k2 == 10) {
                    xtgraphics.fase = -5;
                }
                if (k2 == 12) {
                    xtgraphics.fase = -5;
                }
            }
            if (xtgraphics.fase == -6) {
                this.repaint();
                xtgraphics.pauseimage(this.offImage);
                xtgraphics.fase = -7;
                this.mouses = 0;
            }
            if (xtgraphics.fase == -7) {
                if (!xtgraphics.dontdisplay) {
                    xtgraphics.pausedgame(checkpoints.stage, this.u[0], record, checkpoints.stage);
                }
                else {
                    if (xtgraphics.ptmatch == 1) {
                        xtgraphics.pausedgame(61, this.u[0], record, checkpoints.stage);
                    }
                    if (xtgraphics.ptmatch == 2) {
                        xtgraphics.pausedgame(74, this.u[0], record, checkpoints.stage);
                    }
                    if (xtgraphics.ptmatch == 3) {
                        xtgraphics.pausedgame(67, this.u[0], record, checkpoints.stage);
                    }
                    if (xtgraphics.ptmatch == 4) {
                        xtgraphics.pausedgame(73, this.u[0], record, checkpoints.stage);
                    }
                    if (xtgraphics.ptmatch == 5) {
                        xtgraphics.pausedgame(35, this.u[0], record, checkpoints.stage);
                    }
                }
                if (k2 != 0) {
                    k2 = 0;
                }
                xtgraphics.ctachm(this.xm, this.ym, this.mouses, this.u[0], checkpoints, amadness[0]);
                if (this.mouses == 2) {
                    this.mouses = 0;
                }
                if (this.mouses == 1) {
                    this.mouses = 2;
                }
            }
            if (xtgraphics.fase == -8) {
                xtgraphics.cantreply();
                if (++k2 == 150 || this.u[0].enter || this.u[0].handb || this.mouses == 1) {
                    xtgraphics.fase = -7;
                    this.mouses = 0;
                    this.u[0].enter = false;
                    this.u[0].handb = false;
                }
            }
            if (xtgraphics.fase == 400) {
                xtgraphics.quitwarning(this.u[0], amadness, checkpoints);
            }
            if (this.lostfcs && xtgraphics.fase != 176 && xtgraphics.fase != 111 && xtgraphics.fase != 8) {
                if (xtgraphics.fase == 0 || xtgraphics.justcs == 6) {
                    this.u[0].enter = true;
                }
                else {
                    xtgraphics.nofocus();
                }
                if (this.mouses == 1 || this.mouses == 2) {
                    this.lostfcs = false;
                }
            }
            if (xtgraphics.fase == 10) {
                this.contception = true;
                if (xtgraphics.realunlocked[0] <= xtgraphics.unlocked[0] && xtgraphics.realunlocked[1] <= xtgraphics.unlocked[1]) {
                    if (xtgraphics.justcs == -1) {
                        this.autosave = true;
                        xtgraphics.justcs = -2;
                    }
                    if (xtgraphics.justcs == -2) {
                        xtgraphics.savefase = 1;
                        xtgraphics.justcs = -3;
                    }
                    if (xtgraphics.justcs == -3 && xtgraphics.savefase == 2) {
                        this.autosave = false;
                        xtgraphics.savefase = 0;
                        xtgraphics.justcs = 0;
                    }
                }
                else if (xtgraphics.justcs == -1) {
                    xtgraphics.justcs = 0;
                }
                if (xtgraphics.justcs == 0) {
                    this.u[0].handbalt = false;
                    this.u[0].enteralt = false;
                    if (!xtgraphics.rerun) {
                        xtgraphics.laststage = checkpoints.stage;
                        xtgraphics.lastcar = xtgraphics.sc[0];
                        xtgraphics.opselect[1] = xtgraphics.lastop;
                        xtgraphics.flipo = 0;
                    }
                    xtgraphics.careermode = false;
                    xtgraphics.resetmaini(amadness);
                    xtgraphics.resetbeasts();
                    medium.reset();
                    contva.resetfp();
                    checkpoints.stage = (int)(medium.random() * 17.0f) + 1;
                    xtgraphics.classicmode = true;
                    xtgraphics.sc[0] = (int)(medium.random() * 16.0f) + 23;
                    xtgraphics.justcs = 1;
                }
                xtgraphics.mutes = true;
                xtgraphics.maini(this.u[0], checkpoints, amadness[0]);
                xtgraphics.ctachm(this.xm, this.ym, this.mouses, this.u[0], checkpoints, amadness[0]);
                if (xtgraphics.savefase == 1) {
                    this.writedata(xtgraphics, checkpoints, amadness[0]);
                }
                if (this.mouses == 2) {
                    this.mouses = 0;
                }
                if (this.mouses == 1) {
                    this.mouses = 2;
                }
            }
            else {
                this.contception = false;
            }
            this.repaint();
            if (!xtgraphics.dontdisplay) {
                xtgraphics.playsounds(amadness[0], this.u[0], checkpoints.stage);
            }
            else {
                if (xtgraphics.ptmatch == 1) {
                    xtgraphics.playsounds(amadness[0], this.u[0], 61);
                }
                if (xtgraphics.ptmatch == 2) {
                    xtgraphics.playsounds(amadness[0], this.u[0], 74);
                }
                if (xtgraphics.ptmatch == 3) {
                    xtgraphics.playsounds(amadness[0], this.u[0], 67);
                }
                if (xtgraphics.ptmatch == 4) {
                    xtgraphics.playsounds(amadness[0], this.u[0], 73);
                }
                if (xtgraphics.ptmatch == 5) {
                    xtgraphics.playsounds(amadness[0], this.u[0], 35);
                }
            }
            date2 = new Date();
            final long l13 = date2.getTime();
            if (xtgraphics.fase == 0 || xtgraphics.fase == -1 || xtgraphics.fase == -3 || xtgraphics.justcs == 6) {
                if (!flag2) {
                    f2 = f;
                    flag2 = true;
                    j2 = 0;
                }
                if (j2 == 10) {
                    if (l13 - l2 < j) {
                        f2 += 0.5;
                    }
                    else {
                        f2 -= 0.5;
                        if (f2 < 5.0f) {
                            f2 = 5.0f;
                        }
                    }
                    final int starcnt = xtgraphics.starcnt;
                    l2 = l13;
                    j2 = 0;
                }
                else {
                    ++j2;
                }
            }
            else {
                if (flag2) {
                    f = f2;
                    flag2 = false;
                    j2 = 0;
                }
                if (i2 == 0 || xtgraphics.fase != 176) {
                    if (j2 == 10) {
                        if (l13 - l2 < 400L) {
                            f2 += 3.5;
                        }
                        else {
                            f2 -= 3.5;
                            if (f2 < 5.0f) {
                                f2 = 5.0f;
                            }
                        }
                        l2 = l13;
                        j2 = 0;
                    }
                    else {
                        ++j2;
                    }
                }
                else {
                    if (i2 == 79) {
                        f2 = f;
                        l2 = l13;
                        j2 = 0;
                    }
                    if (j2 == 10) {
                        if (l13 - l2 < j) {
                            f2 += 5.0f;
                        }
                        else {
                            f2 -= 5.0f;
                            if (f2 < 5.0f) {
                                f2 = 5.0f;
                            }
                        }
                        l2 = l13;
                        j2 = 0;
                    }
                    else {
                        ++j2;
                    }
                    if (i2 == 1) {
                        f = f2;
                    }
                }
            }
            if (xtgraphics.lastload >= 0 && xtgraphics.stracks[xtgraphics.lastload] == null && xtgraphics.fase != 10 && xtgraphics.mtracks[xtgraphics.lastload].nooggloop) {
                if (xtgraphics.mtracks[xtgraphics.lastload].playingogg) {
                    xtgraphics.elapsed = System.nanoTime() - xtgraphics.duration + xtgraphics.pausetime;
                }
                else if (xtgraphics.mtracks[xtgraphics.lastload].pausedogg) {
                    xtgraphics.pausetime = xtgraphics.elapsed + 500000000L;
                    xtgraphics.duration = System.nanoTime();
                }
                if (xtgraphics.elapsed >= xtgraphics.musicswitch) {
                    xtgraphics.mtracks[xtgraphics.lastload].setPaused(true);
                    xtgraphics.mtracks[xtgraphics.lastload].unload();
                    xtgraphics.loadedt[xtgraphics.lastload] = false;
                    if (xtgraphics.careermode) {
                        if (xtgraphics.lastload != 77 && xtgraphics.lastload < 94) {
                            xtgraphics.mtracks[checkpoints.stage + 63].play();
                            xtgraphics.lastload = checkpoints.stage + 63;
                        }
                        else {
                            if (xtgraphics.lastload == 77) {
                                xtgraphics.mtracks[78].play();
                                xtgraphics.lastload = 78;
                            }
                            if (xtgraphics.lastload == 94) {
                                xtgraphics.mtracks[95].play();
                                xtgraphics.lastload = 95;
                            }
                            if (xtgraphics.lastload == checkpoints.stage + 99) {
                                xtgraphics.mtracks[checkpoints.stage + 100].play();
                                xtgraphics.lastload = checkpoints.stage + 100;
                            }
                        }
                    }
                }
            }
            if (this.exwist) {
                this.rd.dispose();
                xtgraphics.stopallnow();
                System.gc();
                this.gamer = null;
            }
            long l14 = Math.round(f2) - (l13 - l3);
            if (l14 < i) {
                l14 = i;
            }
            try {
                Thread.sleep(l14);
            }
            catch (final InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    @Override
    public void init() {
        this.offImage = this.createImage(870, 480);
        if (this.offImage != null) {
            this.rd = (Graphics2D)this.offImage.getGraphics();
            this.sg = this.offImage.getGraphics();
        }
        this.rd.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    }
    
    public void catchlink(final int i) {
        if (!this.lostfcs) {
            if (i == 0) {
                if ((this.xm > 100 && this.xm < 770 && this.ym > 150 && this.ym < 209) || (this.xm > 310 && this.xm < 560 && this.ym > 280 && this.ym < 299)) {
                    this.setCursor(new Cursor(12));
                    if (this.mouses == 2) {
                        try {
                            final URL url = new URL("javascript:radicalplay();");
                            this.getAppletContext().showDocument(url);
                        }
                        catch (final Exception ex) {}
                    }
                }
                else {
                    this.setCursor(new Cursor(0));
                }
            }
            if (i == 1) {
                if (this.xm > 100 && this.xm < 770 && this.ym > 245 && this.ym < 307) {
                    this.setCursor(new Cursor(12));
                    if (this.mouses == 2) {
                        try {
                            final URL url2 = new URL("javascript:radicalplay();");
                            this.getAppletContext().showDocument(url2);
                        }
                        catch (final Exception ex2) {}
                    }
                }
                else {
                    this.setCursor(new Cursor(0));
                }
            }
        }
    }
    
    public void readdata(final xtGraphics xtgraphics, final Madness amadness, final CheckPoints checkpoints) {
        String s1 = "";
        try {
            final URL url = new URL(this.getCodeBase(), "data/Files/savedata.radq");
            final int i35 = url.openConnection().getContentLength();
            final DataInputStream datainputstream = new DataInputStream(url.openStream());
            final byte[] arrayOfByte1 = new byte[i35];
            datainputstream.readFully(arrayOfByte1);
            ZipInputStream zipinputstream;
            if (arrayOfByte1[0] == 80 && arrayOfByte1[1] == 75 && arrayOfByte1[2] == 3) {
                zipinputstream = new ZipInputStream(new ByteArrayInputStream(arrayOfByte1));
            }
            else {
                for (int i36 = 0; i36 < i35; ++i36) {
                    if (arrayOfByte1[i36] == 75) {
                        arrayOfByte1[i36] = 85;
                    }
                    else if (arrayOfByte1[i36] == 85) {
                        arrayOfByte1[i36] = 75;
                    }
                    if (arrayOfByte1[i36] == 36) {
                        arrayOfByte1[i36] = 64;
                    }
                    else if (arrayOfByte1[i36] == 64) {
                        arrayOfByte1[i36] = 36;
                    }
                    if (arrayOfByte1[i36] == 53) {
                        arrayOfByte1[i36] = 19;
                    }
                    else if (arrayOfByte1[i36] == 19) {
                        arrayOfByte1[i36] = 53;
                    }
                    if (arrayOfByte1[i36] == 21) {
                        arrayOfByte1[i36] = 44;
                    }
                    else if (arrayOfByte1[i36] == 44) {
                        arrayOfByte1[i36] = 21;
                    }
                    if (arrayOfByte1[i36] == 59) {
                        arrayOfByte1[i36] = 72;
                    }
                    else if (arrayOfByte1[i36] == 72) {
                        arrayOfByte1[i36] = 59;
                    }
                    if (arrayOfByte1[i36] == 11) {
                        arrayOfByte1[i36] = 49;
                    }
                    else if (arrayOfByte1[i36] == 49) {
                        arrayOfByte1[i36] = 11;
                    }
                    if (arrayOfByte1[i36] == 13) {
                        arrayOfByte1[i36] = 68;
                    }
                    else if (arrayOfByte1[i36] == 68) {
                        arrayOfByte1[i36] = 13;
                    }
                }
                zipinputstream = new ZipInputStream(new ByteArrayInputStream(arrayOfByte1));
            }
            ZipEntry entry;
            while ((entry = zipinputstream.getNextEntry()) != null) {
                if (entry.getName().equals("ud.txt")) {
                    final BufferedReader datainputstreams = new BufferedReader(new InputStreamReader(zipinputstream));
                    String s2;
                    while ((s2 = datainputstreams.readLine()) != null) {
                        s1 = new StringBuilder().append(s2.trim()).toString();
                        if (s1.startsWith("unlocked")) {
                            xtgraphics.unlocked[0] = this.getint("unlocked", s1, 0);
                            xtgraphics.unlocked[1] = this.getint("unlocked", s1, 1);
                            this.getint("unlocked", s1, 2);
                            xtgraphics.reqneed2(this.getint("unlocked", s1, 0) + this.getint("unlocked", s1, 1), 11);
                        }
                        if (s1.startsWith("kills")) {
                            xtgraphics.kills = this.getint("kills", s1, 0);
                        }
                        if (s1.startsWith("wins")) {
                            xtgraphics.wins = this.getint("wins", s1, 0);
                        }
                        if (s1.startsWith("code")) {
                            this.getint("code", s1, 0);
                            final int kills = xtgraphics.kills;
                            final int wins = xtgraphics.wins;
                            xtgraphics.reqneed2(62, 12);
                        }
                        if (s1.startsWith("changers")) {
                            xtgraphics.statchangers[0] = this.getint("changers", s1, 0);
                            xtgraphics.statchangers[1] = this.getint("changers", s1, 1);
                        }
                        if (s1.startsWith("usercar")) {
                            xtgraphics.lastcar = this.getint("usercar", s1, 0);
                            xtgraphics.sc[0] = this.getint("usercar", s1, 0);
                        }
                        if (s1.startsWith("car")) {
                            amadness.level[this.getint("car", s1, 0)] = this.getint("car", s1, 1);
                            amadness.exp[this.getint("car", s1, 0)] = this.getint("car", s1, 2);
                            xtgraphics.statpoints[this.getint("car", s1, 0)] = this.getint("car", s1, 3);
                            amadness.aitssp[this.getint("car", s1, 0)] = this.getint("car", s1, 4);
                            amadness.aiaccsp[this.getint("car", s1, 0)] = this.getint("car", s1, 5);
                            amadness.aigripsp[this.getint("car", s1, 0)] = this.getint("car", s1, 6);
                            amadness.aistusp[this.getint("car", s1, 0)] = this.getint("car", s1, 7);
                            amadness.aistrsp[this.getint("car", s1, 0)] = this.getint("car", s1, 8);
                            amadness.aiendsp[this.getint("car", s1, 0)] = this.getint("car", s1, 9);
                            xtgraphics.killscn[this.getint("car", s1, 0)] = this.getint("car", s1, 10);
                            xtgraphics.winscn[this.getint("car", s1, 0)] = this.getint("car", s1, 11);
                            xtgraphics.extpoints[this.getint("car", s1, 0)] = this.getint("car", s1, 12) - 46;
                        }
                        if (s1.startsWith("bsp")) {
                            xtgraphics.rebsp[this.getint("bsp", s1, 0)] = this.getint("bsp", s1, 1);
                            xtgraphics.xbsp[this.getint("bsp", s1, 0)] = this.getint("bsp", s1, 2);
                        }
                        if (s1.startsWith("bonus")) {
                            xtgraphics.boncomp[0] = this.getint("bonus", s1, 0);
                            xtgraphics.boncomp[1] = this.getint("bonus", s1, 1);
                            xtgraphics.boncomp[2] = this.getint("bonus", s1, 2);
                            this.getint("bonus", s1, 3);
                            xtgraphics.reqneed2(xtgraphics.boncomp[0] + xtgraphics.boncomp[1] + xtgraphics.boncomp[2] + 87, 7);
                        }
                        if (s1.startsWith("newbonus")) {
                            xtgraphics.boncomp[3] = this.getint("newbonus", s1, 0);
                            xtgraphics.boncomp[4] = this.getint("newbonus", s1, 1);
                            xtgraphics.boncomp[5] = this.getint("newbonus", s1, 2);
                            this.getint("newbonus", s1, 3);
                            xtgraphics.reqneed2(xtgraphics.boncomp[3] * 15 + xtgraphics.boncomp[4] * 33 + xtgraphics.boncomp[5] * 26, 6);
                        }
                        if (s1.startsWith("laststage")) {
                            checkpoints.stage = this.getint("laststage", s1, 0);
                            xtgraphics.laststage = this.getint("laststage", s1, 0);
                        }
                        if (s1.startsWith("cpoints")) {
                            xtgraphics.carpoints = this.getint("cpoints", s1, 0);
                            this.getint("cpoints", s1, 1);
                            xtgraphics.reqneed2(this.getint("cpoints", s1, 0) + 42, 11);
                        }
                        if (s1.startsWith("special")) {
                            final int[] total = new int[6];
                            int sum = 0;
                            for (int a = 0; a < 6; ++a) {
                                xtgraphics.specialstats[this.getint("special", s1, 0)][xtgraphics.statsalc[this.getint("special", s1, 0)][a]][a] = this.getint("special", s1, a + 1);
                                total[a] = this.getint("special", s1, a + 1) + (a + 3);
                                sum += total[a];
                            }
                            this.getint("special", s1, 7);
                            xtgraphics.reqneed2(sum, 8);
                        }
                    }
                }
            }
        }
        catch (final Exception ex) {
            System.out.println(new StringBuilder().append(ex).toString());
        }
        String s3 = "";
        try {
            final URL url2 = new URL(this.getCodeBase(), "data/Files/savedataBACKUP.radq");
            final int i37 = url2.openConnection().getContentLength();
            final DataInputStream datainputstream2 = new DataInputStream(url2.openStream());
            final byte[] arrayOfByte2 = new byte[i37];
            datainputstream2.readFully(arrayOfByte2);
            ZipInputStream zipinputstream2;
            if (arrayOfByte2[0] == 80 && arrayOfByte2[1] == 75 && arrayOfByte2[2] == 3) {
                zipinputstream2 = new ZipInputStream(new ByteArrayInputStream(arrayOfByte2));
            }
            else {
                for (int i38 = 0; i38 < i37; ++i38) {
                    if (arrayOfByte2[i38] == 75) {
                        arrayOfByte2[i38] = 85;
                    }
                    else if (arrayOfByte2[i38] == 85) {
                        arrayOfByte2[i38] = 75;
                    }
                    if (arrayOfByte2[i38] == 36) {
                        arrayOfByte2[i38] = 64;
                    }
                    else if (arrayOfByte2[i38] == 64) {
                        arrayOfByte2[i38] = 36;
                    }
                    if (arrayOfByte2[i38] == 53) {
                        arrayOfByte2[i38] = 19;
                    }
                    else if (arrayOfByte2[i38] == 19) {
                        arrayOfByte2[i38] = 53;
                    }
                    if (arrayOfByte2[i38] == 21) {
                        arrayOfByte2[i38] = 44;
                    }
                    else if (arrayOfByte2[i38] == 44) {
                        arrayOfByte2[i38] = 21;
                    }
                    if (arrayOfByte2[i38] == 59) {
                        arrayOfByte2[i38] = 72;
                    }
                    else if (arrayOfByte2[i38] == 72) {
                        arrayOfByte2[i38] = 59;
                    }
                    if (arrayOfByte2[i38] == 11) {
                        arrayOfByte2[i38] = 49;
                    }
                    else if (arrayOfByte2[i38] == 49) {
                        arrayOfByte2[i38] = 11;
                    }
                    if (arrayOfByte2[i38] == 13) {
                        arrayOfByte2[i38] = 68;
                    }
                    else if (arrayOfByte2[i38] == 68) {
                        arrayOfByte2[i38] = 13;
                    }
                }
                zipinputstream2 = new ZipInputStream(new ByteArrayInputStream(arrayOfByte2));
            }
            ZipEntry entry2;
            while ((entry2 = zipinputstream2.getNextEntry()) != null) {
                if (entry2.getName().equals("ud.txt")) {
                    final BufferedReader datainputstreams2 = new BufferedReader(new InputStreamReader(zipinputstream2));
                    String s4;
                    while ((s4 = datainputstreams2.readLine()) != null) {
                        s3 = new StringBuilder().append(s4.trim()).toString();
                        if (s3.startsWith("unlocked")) {
                            xtgraphics.realunlocked[0] = this.getint("unlocked", s3, 0);
                            xtgraphics.realunlocked[1] = this.getint("unlocked", s3, 1);
                            this.getint("unlocked", s3, 2);
                            xtgraphics.reqneed2(this.getint("unlocked", s3, 0) + this.getint("unlocked", s3, 1), 11);
                        }
                    }
                }
            }
        }
        catch (final Exception ex2) {
            System.out.println(new StringBuilder().append(ex2).toString());
        }
        String s5 = "";
        try {
            final URL url3 = new URL(this.getCodeBase(), "data/Files/ud.txt");
            final BufferedReader datainputstream3 = new BufferedReader(new InputStreamReader(url3.openStream()));
            String s6;
            while ((s6 = datainputstream3.readLine()) != null) {
                s5 = new StringBuilder().append(s6.trim()).toString();
                if (s5.startsWith("unlocked") && this.getint("unlocked", s5, 2) == xtgraphics.reqneed2(this.getint("unlocked", s5, 0) + this.getint("unlocked", s5, 1), 11) && (this.getint("unlocked", s5, 0) > xtgraphics.unlocked[0] || this.getint("unlocked", s5, 1) > xtgraphics.unlocked[1])) {
                    this.readfromtxt = true;
                }
            }
        }
        catch (final Exception ex3) {}
        if (this.readfromtxt) {
            String s7 = "";
            try {
                final URL url4 = new URL(this.getCodeBase(), "data/Files/ud.txt");
                final BufferedReader datainputstream4 = new BufferedReader(new InputStreamReader(url4.openStream()));
                String s8;
                while ((s8 = datainputstream4.readLine()) != null) {
                    s7 = new StringBuilder().append(s8.trim()).toString();
                    if (s7.startsWith("unlocked")) {
                        xtgraphics.unlocked[0] = this.getint("unlocked", s7, 0);
                        xtgraphics.unlocked[1] = this.getint("unlocked", s7, 1);
                        this.getint("unlocked", s7, 2);
                        xtgraphics.reqneed2(this.getint("unlocked", s7, 0) + this.getint("unlocked", s7, 1), 11);
                    }
                    if (s7.startsWith("kills")) {
                        xtgraphics.kills = this.getint("kills", s7, 0);
                    }
                    if (s7.startsWith("wins")) {
                        xtgraphics.wins = this.getint("wins", s7, 0);
                    }
                    if (s7.startsWith("code")) {
                        this.getint("code", s7, 0);
                        final int kills2 = xtgraphics.kills;
                        final int wins2 = xtgraphics.wins;
                        xtgraphics.reqneed2(62, 12);
                    }
                    if (s7.startsWith("usercar")) {
                        xtgraphics.sc[0] = this.getint("usercar", s7, 0);
                    }
                    if (s7.startsWith("car")) {
                        amadness.level[this.getint("car", s7, 0)] = this.getint("car", s7, 1);
                        amadness.exp[this.getint("car", s7, 0)] = this.getint("car", s7, 2);
                        xtgraphics.statpoints[this.getint("car", s7, 0)] = this.getint("car", s7, 3);
                        amadness.aitssp[this.getint("car", s7, 0)] = this.getint("car", s7, 4);
                        amadness.aiaccsp[this.getint("car", s7, 0)] = this.getint("car", s7, 5);
                        amadness.aigripsp[this.getint("car", s7, 0)] = this.getint("car", s7, 6);
                        amadness.aistusp[this.getint("car", s7, 0)] = this.getint("car", s7, 7);
                        amadness.aistrsp[this.getint("car", s7, 0)] = this.getint("car", s7, 8);
                        amadness.aiendsp[this.getint("car", s7, 0)] = this.getint("car", s7, 9);
                        xtgraphics.killscn[this.getint("car", s7, 0)] = this.getint("car", s7, 10);
                        xtgraphics.winscn[this.getint("car", s7, 0)] = this.getint("car", s7, 11);
                        xtgraphics.extpoints[this.getint("car", s7, 0)] = this.getint("car", s7, 12) - 46;
                        int suboff = 0;
                        if (this.getint("car", s7, 0) < 32) {
                            suboff = 0;
                        }
                        else {
                            if (this.getint("car", s7, 0) == 32) {
                                suboff = 80;
                            }
                            if (this.getint("car", s7, 0) == 33) {
                                suboff = 72;
                            }
                            if (this.getint("car", s7, 0) == 36) {
                                suboff = 160;
                            }
                            if (this.getint("car", s7, 0) == 34 || this.getint("car", s7, 0) == 35 || this.getint("car", s7, 0) == 37 || this.getint("car", s7, 0) == 38) {
                                suboff = 102;
                            }
                        }
                        final int sm = this.getint("car", s7, 3) + this.getint("car", s7, 4) + this.getint("car", s7, 5) + this.getint("car", s7, 6) + this.getint("car", s7, 7) + this.getint("car", s7, 8) + this.getint("car", s7, 9) - 6 * (this.getint("car", s7, 1) - 1) - suboff;
                        if ((this.getint("car", s7, 1) - 1) * 4 + (this.getint("car", s7, 12) - 46) != sm || this.getint("car", s7, 13) != xtgraphics.reqneed2(this.getint("car", s7, 1) + this.getint("car", s7, 3) + 2, this.getint("car", s7, 0))) {
                            boolean exception = false;
                            if (this.getint("car", s7, 0) == 36 && sm == (this.getint("car", s7, 1) - 1) * 4 + (this.getint("car", s7, 12) - 66)) {
                                exception = true;
                            }
                        }
                    }
                    if (s7.startsWith("bonus")) {
                        xtgraphics.boncomp[0] = this.getint("bonus", s7, 0);
                        xtgraphics.boncomp[1] = this.getint("bonus", s7, 1);
                        xtgraphics.boncomp[2] = this.getint("bonus", s7, 2);
                        this.getint("bonus", s7, 3);
                        xtgraphics.reqneed2(xtgraphics.boncomp[0] + xtgraphics.boncomp[1] + xtgraphics.boncomp[2] + 87, 7);
                    }
                    if (s7.startsWith("laststage")) {
                        checkpoints.stage = this.getint("laststage", s7, 0);
                    }
                }
                datainputstream4.close();
            }
            catch (final Exception ex4) {}
        }
    }
    
    public void loadfail(final xtGraphics xt, final int i) {
        this.rd.setColor(new Color(0, 0, 0));
        this.rd.fillRect(0, 0, 870, 480);
        this.rd.setFont(xt.adventure.deriveFont(1, 22.0f));
        xt.ftm = this.rd.getFontMetrics();
        xt.drawcs(230, "Illegal data detected. Game failed to continue...", 230, 230, 230, 3);
        xt.drawcs(260, "Error code: " + i, 230, 230, 230, 3);
    }
    
    public void writedata(final xtGraphics xt, final CheckPoints checkpoints, final Madness madness) {
        final StringBuilder sb = new StringBuilder();
        sb.append("DO NOT EDIT THIS FILE HERE OR THE GAME WON'T START!\r\n");
        sb.append("unlocked(" + xt.unlocked[0] + "," + xt.unlocked[1] + "," + xt.reqneed2(xt.unlocked[0] + xt.unlocked[1], 11) + ")\r\n");
        sb.append("kills(" + xt.kills + ")\r\n");
        sb.append("wins(" + xt.wins + ")\r\n");
        sb.append("code(" + (xt.kills + xt.wins + xt.reqneed2(62, 12)) + ")\r\n");
        sb.append("changers(" + xt.statchangers[0] + "," + xt.statchangers[1] + ")\r\n");
        sb.append("usercar(" + xt.lastcar + ")\r\n");
        for (int a = 0; a < 39; ++a) {
            if (a < 38) {
                xt.statstext[a] = "car(" + a + "," + madness.level[a] + "," + madness.exp[a] + "," + xt.statpoints[a] + "," + madness.aitssp[a] + "," + madness.aiaccsp[a] + "," + madness.aigripsp[a] + "," + madness.aistusp[a] + "," + madness.aistrsp[a] + "," + madness.aiendsp[a] + "," + xt.killscn[a] + "," + xt.winscn[a] + "," + (xt.extpoints[a] + 46) + "," + xt.reqneed2(madness.level[a] + xt.statpoints[a] + 2, a) + ")\r\n";
                sb.append(new StringBuilder().append(xt.statstext[a]).toString());
            }
            else {
                xt.statstext[a] = "car(" + a + "," + madness.level[a] + "," + madness.exp[a] + "," + xt.statpoints[a] + "," + madness.aitssp[a] + "," + madness.aiaccsp[a] + "," + madness.aigripsp[a] + "," + madness.aistusp[a] + "," + madness.aistrsp[a] + "," + madness.aiendsp[a] + "," + xt.killscn[a] + "," + xt.winscn[a] + "," + (xt.extpoints[a] + 46) + "," + xt.reqneed2(madness.level[a] + xt.statpoints[a] + 2, a) + ")";
                sb.append(xt.statstext[a] + "\r\n");
            }
        }
        sb.append("bonus(" + xt.boncomp[0] + "," + xt.boncomp[1] + "," + xt.boncomp[2] + "," + xt.reqneed2(xt.boncomp[0] + xt.boncomp[1] + xt.boncomp[2] + 87, 7) + ")\r\n");
        sb.append("laststage(" + xt.laststage + ")\r\n");
        sb.append("newbonus(" + xt.boncomp[3] + "," + xt.boncomp[4] + "," + xt.boncomp[5] + "," + xt.reqneed2(xt.boncomp[3] * 15 + xt.boncomp[4] * 33 + xt.boncomp[5] * 26, 6) + ")\r\n");
        sb.append("cpoints(" + xt.carpoints + "," + xt.reqneed2(xt.carpoints + 42, 11) + ")\r\n");
        for (int a = 0; a < 39; ++a) {
            final int[] total = new int[6];
            int sum = 0;
            for (int b = 0; b < 6; ++b) {
                total[b] = xt.specialstats[a][xt.statsalc[a][b]][b] + (b + 3);
                sum += total[b];
            }
            sb.append("special(" + a + "," + xt.specialstats[a][xt.statsalc[a][0]][0] + "," + xt.specialstats[a][xt.statsalc[a][1]][1] + "," + xt.specialstats[a][xt.statsalc[a][2]][2] + "," + xt.specialstats[a][xt.statsalc[a][3]][3] + "," + xt.specialstats[a][xt.statsalc[a][4]][4] + "," + xt.specialstats[a][xt.statsalc[a][5]][5] + "," + xt.reqneed2(sum, 8) + ")\r\n");
            sb.append("bsp(" + a + "," + xt.rebsp[a] + "," + xt.xbsp[a] + ")\r\n");
        }
        String filename = "savedata.radq";
        if (this.autosave) {
            filename = "savedataBACKUP.radq";
        }
        try {
            final File localFiles = new File("data/Files/" + filename);
            final ZipOutputStream out = new ZipOutputStream(new FileOutputStream(localFiles));
            final ZipEntry e = new ZipEntry("ud.txt");
            out.putNextEntry(e);
            final byte[] data = sb.toString().getBytes();
            out.write(data, 0, data.length);
            out.closeEntry();
            out.close();
            final File localFile = new File("data/Files/" + filename);
            final int i35 = (int)localFile.length();
            final FileInputStream in = new FileInputStream(localFile);
            final byte[] arrayOfByte1 = new byte[i35];
            in.read(arrayOfByte1);
            in.close();
            for (int i36 = 0; i36 < i35; ++i36) {
                if (arrayOfByte1[i36] == 75) {
                    arrayOfByte1[i36] = 85;
                }
                else if (arrayOfByte1[i36] == 85) {
                    arrayOfByte1[i36] = 75;
                }
                if (arrayOfByte1[i36] == 36) {
                    arrayOfByte1[i36] = 64;
                }
                else if (arrayOfByte1[i36] == 64) {
                    arrayOfByte1[i36] = 36;
                }
                if (arrayOfByte1[i36] == 53) {
                    arrayOfByte1[i36] = 19;
                }
                else if (arrayOfByte1[i36] == 19) {
                    arrayOfByte1[i36] = 53;
                }
                if (arrayOfByte1[i36] == 21) {
                    arrayOfByte1[i36] = 44;
                }
                else if (arrayOfByte1[i36] == 44) {
                    arrayOfByte1[i36] = 21;
                }
                if (arrayOfByte1[i36] == 59) {
                    arrayOfByte1[i36] = 72;
                }
                else if (arrayOfByte1[i36] == 72) {
                    arrayOfByte1[i36] = 59;
                }
                if (arrayOfByte1[i36] == 11) {
                    arrayOfByte1[i36] = 49;
                }
                else if (arrayOfByte1[i36] == 49) {
                    arrayOfByte1[i36] = 11;
                }
                if (arrayOfByte1[i36] == 13) {
                    arrayOfByte1[i36] = 68;
                }
                else if (arrayOfByte1[i36] == 68) {
                    arrayOfByte1[i36] = 13;
                }
            }
            localFile.createNewFile();
            final FileOutputStream out2 = new FileOutputStream(localFile);
            out2.write(arrayOfByte1, 0, arrayOfByte1.length);
            out2.close();
            xt.savefase = 2;
        }
        catch (final Exception ex) {
            System.out.println(new StringBuilder().append(ex).toString());
        }
    }
    
    public void loadbots(final Bots bots, final int i, final int stage, int actions) {
        String s1 = "";
        try {
            final String whichfile = "stage" + stage + ".radq";
            final URL url = new URL(this.getCodeBase(), "data/Files/Bots/" + whichfile);
            final int i2 = url.openConnection().getContentLength();
            final DataInputStream datainputstream = new DataInputStream(url.openStream());
            final byte[] arrayOfByte1 = new byte[i2];
            datainputstream.readFully(arrayOfByte1);
            ZipInputStream zipinputstream;
            if (arrayOfByte1[0] == 80 && arrayOfByte1[1] == 75 && arrayOfByte1[2] == 3) {
                zipinputstream = new ZipInputStream(new ByteArrayInputStream(arrayOfByte1));
            }
            else {
                for (int i3 = 0; i3 < i2; ++i3) {
                    if (arrayOfByte1[i3] == 75) {
                        arrayOfByte1[i3] = 85;
                    }
                    else if (arrayOfByte1[i3] == 85) {
                        arrayOfByte1[i3] = 75;
                    }
                    if (arrayOfByte1[i3] == 36) {
                        arrayOfByte1[i3] = 64;
                    }
                    else if (arrayOfByte1[i3] == 64) {
                        arrayOfByte1[i3] = 36;
                    }
                    if (arrayOfByte1[i3] == 53) {
                        arrayOfByte1[i3] = 19;
                    }
                    else if (arrayOfByte1[i3] == 19) {
                        arrayOfByte1[i3] = 53;
                    }
                    if (arrayOfByte1[i3] == 21) {
                        arrayOfByte1[i3] = 44;
                    }
                    else if (arrayOfByte1[i3] == 44) {
                        arrayOfByte1[i3] = 21;
                    }
                    if (arrayOfByte1[i3] == 59) {
                        arrayOfByte1[i3] = 72;
                    }
                    else if (arrayOfByte1[i3] == 72) {
                        arrayOfByte1[i3] = 59;
                    }
                    if (arrayOfByte1[i3] == 11) {
                        arrayOfByte1[i3] = 49;
                    }
                    else if (arrayOfByte1[i3] == 49) {
                        arrayOfByte1[i3] = 11;
                    }
                    if (arrayOfByte1[i3] == 13) {
                        arrayOfByte1[i3] = 68;
                    }
                    else if (arrayOfByte1[i3] == 68) {
                        arrayOfByte1[i3] = 13;
                    }
                }
                zipinputstream = new ZipInputStream(new ByteArrayInputStream(arrayOfByte1));
            }
            final String txtname = i + ".txt";
            int offset = 0;
            ZipEntry entry;
            while ((entry = zipinputstream.getNextEntry()) != null) {
                if (entry.getName().equals(txtname)) {
                    final BufferedReader datainputstreams = new BufferedReader(new InputStreamReader(zipinputstream));
                    String s2;
                    while ((s2 = datainputstreams.readLine()) != null) {
                        s1 = new StringBuilder().append(s2.trim()).toString();
                        if (s1.startsWith("offset")) {
                            offset = this.getint("offset", s1, 0);
                            bots.botoffset[this.getint("offset", s1, 1)][i] = this.getint("offset", s1, 0);
                        }
                        if (s1.startsWith("up") && !bots.upgo[this.getint("up", s1, 0) + offset][i]) {
                            bots.upgo[this.getint("up", s1, 0) + offset][i] = true;
                            ++actions;
                        }
                        if (s1.startsWith("down") && !bots.downgo[this.getint("down", s1, 0) + offset][i]) {
                            bots.downgo[this.getint("down", s1, 0) + offset][i] = true;
                            ++actions;
                        }
                        if (s1.startsWith("left") && !bots.leftgo[this.getint("left", s1, 0) + offset][i]) {
                            bots.leftgo[this.getint("left", s1, 0) + offset][i] = true;
                            ++actions;
                        }
                        if (s1.startsWith("right") && !bots.rightgo[this.getint("right", s1, 0) + offset][i]) {
                            bots.rightgo[this.getint("right", s1, 0) + offset][i] = true;
                            ++actions;
                        }
                        if (s1.startsWith("handb") && !bots.handbgo[this.getint("handb", s1, 0) + offset][i]) {
                            bots.handbgo[this.getint("handb", s1, 0) + offset][i] = true;
                            ++actions;
                        }
                    }
                }
            }
            for (int a = 0; a < 10; ++a) {}
            bots.oneloaded[i] = true;
        }
        catch (final Exception e) {
            System.out.println(new StringBuilder().append(e).toString());
        }
    }
}
