// Draw calls of every Extended model, as madness.jar's ContO.d makes them.
//
// Each model is loaded once, then for each pose placed with the game's copy
// constructor (new ContO(base, x, y, z, xz)), tilted, and drawn into RecG,
// which records setColor / fillPolygon / drawPolygon / fillRect. Math.random
// is java.js's xorshift (as in ExtContOProbe), reseeded before every pose.
// web/ext/draw.test.js does the same with the port and compares op by op.
//
//   javac -cp <jar-dir> -d /tmp/probe web/tools/RecG.java web/tools/ExtDrawProbe.java
//   java -cp "<jar-dir>;/tmp/probe" ExtDrawProbe ext/data/models.radq > web/ext/draw.expected.json

import java.io.*;
import java.lang.reflect.*;
import java.nio.file.*;
import java.util.*;
import java.util.zip.*;

public class ExtDrawProbe {
    // x, y, z, xz, xy, zy: near and far (the LOD switch), turned and tilted
    static final int[][] POSES = {
        {0, 100, 1500, 0, 0, 0},
        {200, 50, 2200, 45, 10, 0},
        {-300, 150, 3000, 135, 0, 20},
        {0, 250, 9000, 270, 0, 0},
    };

    public static void main(String[] a) throws Exception {
        byte[] b = Files.readAllBytes(Paths.get(a[0]));
        if (!(b[0] == 80 && b[1] == 75 && b[2] == 3)) for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xff;
            for (int[] p : ExtContOProbe.SWAP) { if (v == p[0]) { v = p[1]; break; } if (v == p[1]) { v = p[0]; break; } }
            b[i] = (byte) v;
        }
        Class<?> contO = Class.forName("ContO"), medium = Class.forName("Medium"), trackers = Class.forName("Trackers"), xt = Class.forName("xtGraphics");
        Field uf = Class.forName("sun.misc.Unsafe").getDeclaredField("theUnsafe");
        uf.setAccessible(true);
        Object unsafe = uf.get(null);
        Object xtg = unsafe.getClass().getMethod("allocateInstance", Class.class).invoke(unsafe, xt);
        ExtContOProbe.installRandom(unsafe);
        Object m = medium.getDeclaredConstructor().newInstance(), t = trackers.getDeclaredConstructor().newInstance();
        Constructor<?> load = contO.getDeclaredConstructor(byte[].class, medium, trackers, xt, int.class);
        Constructor<?> copy = contO.getDeclaredConstructor(contO, int.class, int.class, int.class, int.class);
        Method d = contO.getDeclaredMethod("d", java.awt.Graphics2D.class);
        StringBuilder out = new StringBuilder("{\"draws\":[\n");
        boolean first = true;
        try (ZipInputStream z = new ZipInputStream(new ByteArrayInputStream(b))) {
            for (ZipEntry e = z.getNextEntry(); e != null; e = z.getNextEntry()) {
                int j = 0;
                for (int k = 0; k < 129; k++) if (e.getName().startsWith(ExtContOProbe.AS[k])) j = k;
                byte[] data = z.readAllBytes();
                ExtContOProbe.xs = ExtContOProbe.SEED;
                Object base = load.newInstance(data, m, t, xtg, j);
                for (int p = 0; p < POSES.length; p++) {
                    int[] q = POSES[p];
                    ExtContOProbe.xs = ExtContOProbe.SEED;
                    Object c = copy.newInstance(base, q[0], q[1], q[2], q[3]);
                    contO.getDeclaredField("xy").setInt(c, q[4]);
                    contO.getDeclaredField("zy").setInt(c, q[5]);
                    RecG g = new RecG();
                    String err = "";
                    try { d.invoke(c, g); } catch (InvocationTargetException x) { err = String.valueOf(x.getCause()); }
                    if (!first) out.append(",\n");
                    first = false;
                    out.append("{\"name\":").append(ExtContOProbe.q(e.getName())).append(",\"pose\":").append(p)
                       .append(",\"error\":").append(ExtContOProbe.q(err)).append(",\"ops\":[").append(g.out).append("]}");
                }
            }
        }
        System.out.print(out.append("\n]}\n"));
    }
}
