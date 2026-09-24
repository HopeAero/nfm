import javax.sound.sampled.Line;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.AudioFormat;
import java.io.InputStream;
import javax.sound.sampled.AudioSystem;
import java.io.ByteArrayInputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import java.applet.AudioClip;

// 
// Decompiled by Procyon v0.6.0
// 

public class DesktopSoundClip implements AudioClip
{
    Clip clip;
    AudioInputStream sound;
    boolean loaded;
    int lfrpo;
    int cntcheck;
    
    public DesktopSoundClip() {
        this.clip = null;
        this.loaded = false;
        this.lfrpo = -1;
        this.cntcheck = 0;
    }
    
    public DesktopSoundClip(final byte[] is) {
        this.clip = null;
        this.loaded = false;
        this.lfrpo = -1;
        this.cntcheck = 0;
        try {
            final ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(is);
            (this.sound = AudioSystem.getAudioInputStream(bytearrayinputstream)).mark(is.length);
            AudioFormat format = this.sound.getFormat();
            if (format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
                format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, format.getSampleRate(), format.getSampleSizeInBits() * 2, format.getChannels(), format.getFrameSize() * 2, format.getFrameRate(), true);
                (this.sound = AudioSystem.getAudioInputStream(format, this.sound)).mark(is.length * 2);
            }
            final DataLine.Info info = new DataLine.Info(Clip.class, format);
            this.clip = (Clip)AudioSystem.getLine(info);
            this.loaded = true;
        }
        catch (final Exception exception) {
            System.out.println("Loading Clip error: " + exception);
            this.loaded = false;
        }
    }
    
    @Override
    public void play() {
        if (this.loaded) {
            try {
                if (!this.clip.isOpen()) {
                    try {
                        this.clip.open(this.sound);
                    }
                    catch (final Exception ex) {}
                    this.clip.loop(0);
                }
                else {
                    this.clip.loop(1);
                }
                this.lfrpo = -1;
                this.cntcheck = 5;
            }
            catch (final Exception ex2) {}
        }
    }
    
    @Override
    public void loop() {
        if (this.loaded) {
            try {
                if (!this.clip.isOpen()) {
                    try {
                        this.clip.open(this.sound);
                    }
                    catch (final Exception ex) {}
                }
                this.clip.loop(70);
                this.lfrpo = -2;
                this.cntcheck = 0;
            }
            catch (final Exception ex2) {}
        }
    }
    
    @Override
    public void stop() {
        if (this.loaded) {
            try {
                this.clip.stop();
                this.lfrpo = -1;
            }
            catch (final Exception ex) {}
        }
    }
    
    public void checkopen() {
        if (this.loaded && this.clip.isOpen() && this.lfrpo != -2) {
            if (this.cntcheck == 0) {
                final int i = this.clip.getFramePosition();
                if (this.lfrpo == i && !this.clip.isRunning()) {
                    try {
                        this.clip.close();
                        this.sound.reset();
                    }
                    catch (final Exception ex) {}
                    this.lfrpo = -1;
                }
                else {
                    this.lfrpo = i;
                }
            }
            else {
                --this.cntcheck;
            }
        }
    }
}
