import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.applet.AppletStub;
import java.util.List;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Toolkit;
import java.awt.Image;
import java.util.ArrayList;
import java.awt.Frame;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.6.0
// 

class RunApp extends Panel
{
    private static final long serialVersionUID = -8590687589434803725L;
    private static Frame frame;
    private static GameSparker applet;
    private static ArrayList<Image> icons;
    
    private static ArrayList<Image> getIcons() {
        if (RunApp.icons == null) {
            RunApp.icons = new ArrayList<Image>();
            final int[] resols = { 16, 32, 48 };
            int[] array;
            for (int length = (array = resols).length, i = 0; i < length; ++i) {
                final int res = array[i];
                RunApp.icons.add(Toolkit.getDefaultToolkit().createImage("data/ico_" + res + ".png"));
            }
        }
        return RunApp.icons;
    }
    
    public static void main(final String[] strings) {
        System.out.println("Extended Mode Console");
        System.out.println(new StringBuilder().append(strings).toString());
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (final Exception ex) {
            System.out.println("Could not setup System Look&Feel: " + ex.toString());
        }
        startup();
    }
    
    private static void startup() {
        (RunApp.frame = new Frame("Need For Madness 2 - Extended Mode")).setBackground(new Color(0, 0, 0));
        RunApp.frame.setIgnoreRepaint(true);
        RunApp.frame.setIconImages(getIcons());
        (RunApp.applet = new GameSparker()).setStub(new DesktopStub());
        RunApp.frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent windowevent) {
                RunApp.exitsequance();
            }
        });
        RunApp.applet.setPreferredSize(new Dimension(870, 480));
        RunApp.frame.add("Center", RunApp.applet);
        RunApp.frame.setResizable(false);
        RunApp.frame.pack();
        RunApp.frame.setMinimumSize(RunApp.frame.getSize());
        RunApp.frame.setLocationRelativeTo(null);
        RunApp.frame.setVisible(true);
        RunApp.applet.init();
        RunApp.applet.start();
    }
    
    public static void exitsequance() {
        RunApp.applet.stop();
        RunApp.frame.removeAll();
        try {
            Thread.sleep(200L);
        }
        catch (final Exception ex) {}
        RunApp.applet.destroy();
        RunApp.applet = null;
        System.exit(0);
    }
}
