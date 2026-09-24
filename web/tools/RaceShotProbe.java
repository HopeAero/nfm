package tools;

import java.awt.Color;
import java.awt.Event;
import java.awt.Frame;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * RaceShotProbe -- runs the REAL game out of java/Game.jar in a frame parked
 * off screen that never takes focus, and drives it from a command file, so
 * the original can be screenshotted next to the port without touching the
 * desktop (screen-coordinate clicks and SendKeys land in whatever window the
 * user has in front; PrintWindow returns black for the D3D pipeline).
 *
 * Commands, one per line, appended to <cmdfile> while it runs:
 *   key <code> [holdMs]   keyDown, wait, keyUp (Event.UP=1004 DOWN=1005
 *                         LEFT=1006 RIGHT=1007, Enter=10, Esc=27)
 *   down <code> / up <code>
 *   wait <ms>
 *   shot <file.png>       saves GameSparker.offImage (1600x900)
 *   quit
 *
 * Build and run from the repo root (Windows: ';' as the classpath separator):
 *   javac -cp java/Game.jar -d <out> web/tools/RaceShotProbe.java
 *   java -cp <out>;java/Game.jar tools.RaceShotProbe <cmdfile>
 */
public class RaceShotProbe {
    static Object get(Object o, String name) throws Exception {
        for (Class<?> c = o.getClass(); c != null; c = c.getSuperclass()) {
            try { Field f = c.getDeclaredField(name); f.setAccessible(true); return f.get(o); }
            catch (NoSuchFieldException e) { /* superclass */ }
        }
        throw new NoSuchFieldException(name);
    }

    static void setStatic(Class<?> c, String name, Object v) throws Exception {
        Field f = c.getDeclaredField(name); f.setAccessible(true); f.set(null, v);
    }

    public static void main(String[] args) throws Exception {
        File cmd = new File(args[0]);
        Class<?> madness = Class.forName("Madness");
        Frame frame = new Frame("NFM probe");
        frame.setBackground(new Color(0, 0, 0));
        frame.setIgnoreRepaint(true);
        frame.setFocusableWindowState(false);
        setStatic(madness, "frame", frame);
        setStatic(madness, "fpath", "");
        java.applet.Applet applet = (java.applet.Applet) Class.forName("GameSparker").newInstance();
        setStatic(madness, "applet", applet);
        frame.add("Center", applet);
        frame.setLocation(-4000, 0);
        frame.setSize(930, 586);
        frame.setVisible(true);
        applet.init();
        applet.start();

        int done = 0;
        while (true) {
            List<String> lines = cmd.exists() ? Files.readAllLines(cmd.toPath()) : java.util.Collections.<String>emptyList();
            if (done >= lines.size()) { Thread.sleep(50); continue; }
            String[] a = lines.get(done++).trim().split("\\s+");
            if (a[0].isEmpty()) continue;
            Event ev = new Event(applet, Event.KEY_PRESS, null);
            switch (a[0]) {
                case "key": {
                    int k = Integer.parseInt(a[1]);
                    applet.keyDown(ev, k);
                    Thread.sleep(a.length > 2 ? Integer.parseInt(a[2]) : 80);
                    applet.keyUp(ev, k);
                    break;
                }
                case "down": applet.keyDown(ev, Integer.parseInt(a[1])); break;
                case "up": applet.keyUp(ev, Integer.parseInt(a[1])); break;
                case "wait": Thread.sleep(Integer.parseInt(a[1])); break;
                case "shot": {
                    Image img = (Image) get(applet, "offImage");
                    BufferedImage out = new BufferedImage(1600, 900, BufferedImage.TYPE_INT_RGB);
                    out.getGraphics().drawImage(img, 0, 0, null);
                    ImageIO.write(out, "png", new File(a[1]));
                    System.out.println("shot " + a[1]);
                    break;
                }
                case "quit": System.exit(0);
                default: System.out.println("unknown: " + a[0]);
            }
            System.out.flush();
        }
    }
}
