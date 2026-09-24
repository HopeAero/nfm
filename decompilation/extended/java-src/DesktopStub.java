import java.net.MalformedURLException;
import java.net.URL;
import java.applet.AppletContext;
import java.applet.AppletStub;

// 
// Decompiled by Procyon v0.6.0
// 

public class DesktopStub implements AppletStub
{
    AppletContext context;
    
    public DesktopStub() {
        this.context = new DesktopContext();
    }
    
    @Override
    public boolean isActive() {
        return true;
    }
    
    @Override
    public URL getDocumentBase() {
        try {
            return new URL("file:///" + System.getProperty("user.dir") + "/");
        }
        catch (final MalformedURLException ex) {
            return null;
        }
    }
    
    @Override
    public URL getCodeBase() {
        try {
            return new URL("file:///" + System.getProperty("user.dir") + "/");
        }
        catch (final MalformedURLException ex) {
            return null;
        }
    }
    
    @Override
    public String getParameter(final String name) {
        return null;
    }
    
    @Override
    public AppletContext getAppletContext() {
        return this.context;
    }
    
    @Override
    public void appletResize(final int width, final int height) {
    }
}
