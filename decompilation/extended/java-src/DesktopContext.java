import java.io.IOException;
import java.awt.Desktop;
import java.util.Enumeration;
import java.applet.Applet;
import java.awt.Toolkit;
import java.awt.Image;
import java.io.InputStream;
import java.applet.AudioClip;
import java.net.URL;
import java.util.Iterator;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.applet.AppletContext;

// 
// Decompiled by Procyon v0.6.0
// 

public class DesktopContext implements AppletContext, Runnable
{
    List<DesktopSoundClip> clips;
    Thread clipper;
    
    public DesktopContext() {
        this.clips = Collections.synchronizedList(new LinkedList<DesktopSoundClip>());
    }
    
    @Override
    public void run() {
        while (true) {
            for (final DesktopSoundClip clip : this.clips) {
                clip.checkopen();
            }
            try {
                Thread.sleep(100L);
            }
            catch (final InterruptedException ex) {}
        }
    }
    
    @Override
    public AudioClip getAudioClip(final URL url) {
        try {
            final InputStream in = url.openStream();
            int size = in.available();
            int read = 0;
            final byte[] buffer = new byte[size];
            while (size > 0) {
                read = in.read(buffer, 0, size);
                size -= read;
            }
            in.close();
            final DesktopSoundClip clip = new DesktopSoundClip(buffer);
            this.clips.add(clip);
            if (this.clipper == null) {
                (this.clipper = new Thread(this, "Clip stopper service")).start();
            }
            return clip;
        }
        catch (final Exception ex) {
            return new DesktopSoundClip();
        }
    }
    
    @Override
    public Image getImage(final URL url) {
        return Toolkit.getDefaultToolkit().getImage(url);
    }
    
    @Override
    public Applet getApplet(final String name) {
        throw new UnsupportedOperationException("Not supported.");
    }
    
    @Override
    public Enumeration<Applet> getApplets() {
        throw new UnsupportedOperationException("Not supported.");
    }
    
    @Override
    public void showDocument(final URL url) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(url.toURI());
            }
            catch (final Exception ex) {}
        }
    }
    
    @Override
    public void showDocument(final URL url, final String target) {
        this.showDocument(url);
    }
    
    @Override
    public void showStatus(final String status) {
    }
    
    @Override
    public void setStream(final String key, final InputStream stream) throws IOException {
        throw new UnsupportedOperationException("Not supported.");
    }
    
    @Override
    public InputStream getStream(final String key) {
        throw new UnsupportedOperationException("Not supported.");
    }
    
    @Override
    public Iterator<String> getStreamKeys() {
        throw new UnsupportedOperationException("Not supported.");
    }
}
