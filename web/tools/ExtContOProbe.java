// State dump of every Extended Mode model as the jar builds it.
//
// Loads models.radq the way GameSparker.loadbase does (undo the byte swap,
// code = the LAST name in its list that prefixes the entry), constructs each
// ContO with the jar's own class, and prints every instance field of the
// ContO and of each Plane in p[0..npl) as JSON. web/ext/ContO.test.js builds
// the same models with the port and compares field by field.
//
// Classpath: the unpacked madness.jar. Build/run with JDK 17+:
//   javac -cp <jar-dir> -d /tmp/probe web/tools/ExtContOProbe.java
//   java -cp "<jar-dir>;/tmp/probe" ExtContOProbe ext/data/models.radq > web/ext/contO.expected.json

import java.io.*;
import java.lang.reflect.*;
import java.nio.file.*;
import java.util.*;
import java.util.zip.*;

public class ExtContOProbe {
    static final String[] AS = { "2000tornados", "formula7", "canyenaro", "lescrab", "nimi", "maxrevenge", "leadoxide", "koolkat", "drifter", "policecops", "mustang", "king", "audir8", "masheen", "radicalone", "drmonster", "newcar1", "newcar2", "newcar3", "newcar4", "secretcar1", "secretcar2", "secretcar3", "tornadoshark", "formula72", "wowcaninaro", "lavitacrab", "nimi2", "maxrevenge2", "leadoxide2", "koolkat2", "drifterx", "swordofjustice", "highrider", "elking", "mightyeight", "masheen2", "radicalone2", "drmonstaa", "road", "froad", "twister2", "twister1", "turn", "offroad", "bumproad", "offturn", "nroad", "nturn", "roblend", "noblend", "rnblend", "roadend", "offroadend", "hpground", "ramp30", "cramp35", "dramp15", "dhilo15", "slide10", "takeoff", "sramp22", "offbump", "offramp", "thewall", "halfpipe", "spikes", "rail", "sofframp", "checkpoint", "fixpoint", "offcheckpoint", "sideoff", "bsideoff", "uprise", "riseroad", "sroad", "soffroad", "2000tornadosB", "formula7B", "canyenaroB", "lescrabB", "nimiB", "maxrevengeB", "leadoxideB", "koolkatB", "drifterB", "policecopsB", "mustangB", "kingB", "audir8B", "masheenB", "radicaloneB", "drmonsterB", "newcar1B", "newcar2B", "newcar3B", "newcar4B", "secretcar1B", "secretcar2B", "secretcar3B", "tornadosharkB", "formula72B", "wowcaninaroB", "lavitacrabB", "nimi2B", "maxrevenge2B", "leadoxide2B", "koolkat2B", "drifterxB", "swordofjusticeB", "highriderB", "elkingB", "mightyeightB", "masheen2B", "radicalone2B", "drmonstaaB", "tree4", "tree6", "offhill", "spikefire", "railfire", "cactus", "roll1", "roll2", "roll3", "roll4", "roll5", "roll6" };
    static final int[][] SWAP = { {0x4B, 0x55}, {0x24, 0x40}, {0x35, 0x13}, {0x15, 0x2C}, {0x3B, 0x48}, {0x0B, 0x31}, {0x0D, 0x44} };
    static final Set<String> SHARED = Set.of("Medium", "Trackers", "xtGraphics");

    public static void main(String[] a) throws Exception {
        byte[] b = Files.readAllBytes(Paths.get(a[0]));
        if (!(b[0] == 80 && b[1] == 75 && b[2] == 3)) for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xff;
            for (int[] p : SWAP) { if (v == p[0]) { v = p[1]; break; } if (v == p[1]) { v = p[0]; break; } }
            b[i] = (byte) v;
        }
        Class<?> contO = Class.forName("ContO"), medium = Class.forName("Medium"), trackers = Class.forName("Trackers"), xt = Class.forName("xtGraphics");
        Field uf = Class.forName("sun.misc.Unsafe").getDeclaredField("theUnsafe");
        uf.setAccessible(true);
        Object unsafe = uf.get(null);
        Object xtg = unsafe.getClass().getMethod("allocateInstance", Class.class).invoke(unsafe, xt);
        installRandom(unsafe);
        Object m = medium.getDeclaredConstructor().newInstance(), t = trackers.getDeclaredConstructor().newInstance();
        Constructor<?> ctor = contO.getDeclaredConstructor(byte[].class, medium, trackers, xt, int.class);
        StringBuilder out = new StringBuilder("{\"models\":[\n");
        boolean first = true;
        try (ZipInputStream z = new ZipInputStream(new ByteArrayInputStream(b))) {
            for (ZipEntry e = z.getNextEntry(); e != null; e = z.getNextEntry()) {
                int j = 0;
                for (int k = 0; k < 129; k++) if (e.getName().startsWith(AS[k])) j = k;
                byte[] data = z.readAllBytes();
                xs = SEED;   // every model from the same point of the stream (the test does setSeed(SEED))
                Object c = ctor.newInstance(data, m, t, xtg, j);
                if (!first) out.append(",\n");
                first = false;
                out.append("{\"name\":").append(q(e.getName())).append(",\"code\":").append(j).append(",\"state\":");
                dump(c, out, new IdentityHashMap<>());
                out.append("}");
            }
        }
        System.out.print(out.append("\n]}\n"));
    }

    // Math.random() made deterministic: web/java.js random()'s xorshift32 (sim
    // stream), swapped into the JDK's static generator before anything calls it.
    // Plane's constructor rolls shading and vertex jitter from it.
    static final int SEED = 12345;
    static int xs = SEED;

    static void installRandom(Object unsafe) throws Exception {
        Field f = Class.forName("java.lang.Math$RandomNumberGeneratorHolder").getDeclaredField("randomNumberGenerator");
        Class<?> u = unsafe.getClass();
        Object base = u.getMethod("staticFieldBase", Field.class).invoke(unsafe, f);
        long off = (long) u.getMethod("staticFieldOffset", Field.class).invoke(unsafe, f);
        u.getMethod("putObject", Object.class, long.class, Object.class).invoke(unsafe, base, off, new Random() {
            @Override public double nextDouble() {
                int x = xs;
                x ^= x << 13;
                x ^= x >>> 17;
                x ^= x << 5;
                xs = x;
                return (x & 0xffffffffL) / 4294967296.0;
            }
        });
    }

    static void dump(Object o, StringBuilder out, IdentityHashMap<Object, Boolean> seen) throws Exception {
        if (o == null) { out.append("null"); return; }
        Class<?> c = o.getClass();
        if (o instanceof String s) { out.append(q(s)); return; }
        if (o instanceof Number || o instanceof Boolean) { num(o, out); return; }
        if (c.isArray()) {
            out.append('[');
            int n = Array.getLength(o);
            // Plane[] p: only the planes that exist; the rest of the 500 are null
            for (int i = 0; i < n; i++) { if (i > 0) out.append(','); dump(Array.get(o, i), out, seen); }
            out.append(']');
            return;
        }
        if (SHARED.contains(c.getName())) { out.append("\"<").append(c.getName()).append(">\""); return; }
        if (c.getPackageName().length() > 0) { out.append("\"<").append(c.getName()).append(">\""); return; }
        if (seen.put(o, true) != null) { out.append("\"<cycle>\""); return; }
        out.append('{');
        boolean first = true;
        List<Field> fs = new ArrayList<>(List.of(c.getDeclaredFields()));
        fs.sort(Comparator.comparing(Field::getName));
        for (Field f : fs) {
            if (Modifier.isStatic(f.getModifiers())) continue;
            f.setAccessible(true);
            if (!first) out.append(',');
            first = false;
            out.append(q(f.getName())).append(':');
            dump(f.get(o), out, seen);
        }
        out.append('}');
    }

    /** Numbers exactly: a float is printed as the double it widens to. */
    static void num(Object o, StringBuilder out) {
        if (o instanceof Boolean) { out.append(o); return; }
        double d = ((Number) o).doubleValue();
        if (o instanceof Float f) d = (double) (float) f;
        if (Double.isNaN(d)) { out.append("\"NaN\""); return; }
        if (Double.isInfinite(d)) { out.append(d > 0 ? "\"Infinity\"" : "\"-Infinity\""); return; }
        if (d == Math.rint(d) && Math.abs(d) < 1e15) out.append((long) d);
        else out.append(Double.toString(d));
    }

    static String q(String s) {
        StringBuilder b = new StringBuilder("\"");
        for (char ch : s.toCharArray()) {
            if (ch == '"' || ch == '\\') b.append('\\').append(ch);
            else if (ch < 32) b.append(String.format("\\u%04x", (int) ch));
            else b.append(ch);
        }
        return b.append('"').toString();
    }
}
