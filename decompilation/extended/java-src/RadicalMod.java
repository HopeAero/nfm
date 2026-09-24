import java.io.ByteArrayInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.net.URL;
import java.applet.Applet;
import java.io.InputStream;
import sun.audio.AudioPlayer;

// 
// Decompiled by Procyon v0.6.0
// 

public class RadicalMod
{
    byte[] modf;
    SuperStream stream;
    SuperClip sClip;
    boolean suny;
    boolean playing;
    int loaded;
    
    public void stop() {
        if (this.playing && this.loaded == 2) {
            if (this.suny) {
                this.sClip.stop();
            }
            else {
                try {
                    AudioPlayer.player.stop((InputStream)this.stream);
                }
                catch (final Exception ex) {}
            }
            this.playing = false;
        }
    }
    
    public RadicalMod(final String s, final Applet applet) {
        this.suny = false;
        this.playing = false;
        this.loaded = 0;
        this.loaded = 1;
        try {
            final URL url = new URL(applet.getCodeBase(), s);
            final ZipInputStream zipinputstream = new ZipInputStream(url.openStream());
            final ZipEntry zipentry = zipinputstream.getNextEntry();
            int i = (int)zipentry.getSize();
            this.modf = new byte[i];
            int j = 0;
            while (i > 0) {
                final int k = zipinputstream.read(this.modf, j, i);
                j += k;
                i -= k;
            }
        }
        catch (final Exception exception) {
            System.out.println("Error loading Mod from zip file: " + exception);
            this.loaded = 0;
        }
    }
    
    public void resume() {
        if (!this.playing && this.loaded == 2) {
            if (this.suny) {
                this.sClip.resume();
                if (this.sClip.stoped == 0) {
                    this.playing = true;
                }
            }
            else {
                try {
                    AudioPlayer.player.start((InputStream)this.stream);
                }
                catch (final Exception ex) {}
                this.playing = true;
            }
        }
    }
    
    protected void unloadAll() {
        if (this.playing && this.loaded == 2) {
            if (this.suny) {
                this.sClip.stop();
            }
            else {
                try {
                    AudioPlayer.player.stop((InputStream)this.stream);
                }
                catch (final Exception ex) {}
            }
        }
        try {
            if (this.suny) {
                this.sClip.close();
                this.sClip = null;
            }
            else {
                this.stream.close();
                this.stream = null;
            }
        }
        catch (final Exception ex2) {}
        try {
            this.modf = null;
        }
        catch (final Exception ex3) {}
        System.gc();
    }
    
    public void play() {
        if (!this.playing && this.loaded == 2) {
            if (this.suny) {
                this.sClip.play();
                if (this.sClip.stoped == 0) {
                    this.playing = true;
                }
            }
            else {
                if (this.stream != null) {
                    this.stream.reset();
                }
                try {
                    AudioPlayer.player.start((InputStream)this.stream);
                }
                catch (final Exception ex) {}
                this.playing = true;
            }
        }
    }
    
    protected void unloadMod() {
        if (this.loaded == 2) {
            if (this.playing) {
                if (this.suny) {
                    this.sClip.stop();
                }
                else {
                    try {
                        AudioPlayer.player.stop((InputStream)this.stream);
                    }
                    catch (final Exception ex) {}
                }
                this.playing = false;
            }
            try {
                if (this.suny) {
                    this.sClip.close();
                    this.sClip = null;
                }
                else {
                    this.stream.close();
                    this.stream = null;
                }
            }
            catch (final Exception ex2) {}
            System.gc();
            this.loaded = 1;
        }
    }
    
    public void loadMod(int i, int j, final int k, final boolean flag, final boolean flag1) {
        if (this.loaded == 1) {
            this.loaded = 2;
            this.suny = flag;
            final int l = 22000;
            if (flag1) {
                this.suny = false;
            }
            if (this.suny) {
                j = (int)(j / 8000.0f * 2.0f * l);
            }
            if (!this.suny) {
                if (!flag1) {
                    i *= 1.5;  // cast: bytecode-verified
                }
                else {
                    i *= 2.2;  // cast: bytecode-verified
                }
            }
            final Mod mod = new Mod(new ByteArrayInputStream(this.modf));
            ModSlayer modslayer = new ModSlayer(mod, j, i, k);
            try {
                if (this.suny) {
                    final byte[] abyte0 = modslayer.turnbytesNorm();
                    this.sClip = new SuperClip(abyte0, modslayer.oln, l);
                }
                else {
                    final byte[] abyte2 = modslayer.turnbytesUlaw();
                    this.stream = new SuperStream(abyte2);
                }
                final Object obj1 = null;
                final Object obj2 = null;
                modslayer = null;
            }
            catch (final Exception exception) {
                System.out.println("Error making a Mod: " + exception);
                this.loaded = 0;
            }
            System.runFinalization();
            System.gc();
        }
    }
}
