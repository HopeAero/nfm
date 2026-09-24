import javax.sound.sampled.Line;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.AudioFormat;
import java.io.ByteArrayInputStream;
import javax.sound.sampled.SourceDataLine;

// 
// Decompiled by Procyon v0.6.0
// 

public class SuperClip implements Runnable
{
    int skiprate;
    Thread cliper;
    int stoped;
    SourceDataLine source;
    ByteArrayInputStream stream;
    
    public SuperClip(final byte[] abyte0, final int i, final int j) {
        this.skiprate = 0;
        this.stoped = 1;
        this.source = null;
        this.stoped = 2;
        this.skiprate = j;
        this.stream = new ByteArrayInputStream(abyte0, 0, i);
    }
    
    @Override
    public void run() {
        boolean flag = false;
        try {
            final DataLine.Info info = new DataLine.Info(SourceDataLine.class, new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, -1.0f, 16, 2, 4, -1.0f, true));
            (this.source = (SourceDataLine)AudioSystem.getLine(info)).open(new AudioFormat((float)this.skiprate, 16, 1, false, false));
            this.source.start();
        }
        catch (final Exception exception) {
            this.stoped = 1;
        }
        while (this.stoped == 0) {
            try {
                if (this.source.available() < this.skiprate || !flag) {
                    final byte[] abyte0 = new byte[this.skiprate];
                    final int i = this.stream.read(abyte0, 0, abyte0.length);
                    if (i == -1) {
                        this.stream.reset();
                        this.stream.read(abyte0, 0, abyte0.length);
                    }
                    this.source.write(abyte0, 0, abyte0.length);
                    flag = true;
                }
            }
            catch (final Exception exception2) {
                System.out.println("play error: " + exception2);
                this.stoped = 1;
            }
            try {
                final Thread _tmp = this.cliper;
                Thread.sleep(200L);
            }
            catch (final InterruptedException ex) {}
        }
        this.source.stop();
        this.source.close();
        this.source = null;
        this.stoped = 2;
    }
    
    public void play() {
        if (this.stoped == 2) {
            this.stoped = 0;
            try {
                this.stream.reset();
            }
            catch (final Exception ex) {}
            (this.cliper = new Thread(this)).start();
        }
    }
    
    public void resume() {
        if (this.stoped == 2) {
            this.stoped = 0;
            (this.cliper = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.stoped == 0) {
            this.stoped = 1;
            if (this.source != null) {
                this.source.stop();
            }
        }
    }
    
    public void close() {
        try {
            this.stream.close();
            this.stream = null;
        }
        catch (final Exception ex) {}
    }
}
