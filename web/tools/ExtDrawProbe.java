// Draw calls of Extended models, as madness.jar's ContO.d makes them.
//
// "draws": each of the 129 models is loaded once, then for each pose placed
// with the game's copy constructor (new ContO(base, x, y, z, xz)), tilted, and
// drawn into RecG, which records setColor / fillPolygon / drawPolygon /
// fillRect.
// "effects": every car (codes 0..38) at pose 0 under each EFFECTS setup, drawn
// for FRAMES consecutive frames so animated state (fire, repair, sparks) moves.
//
// Math.random is java.js's xorshift (as in ExtContOProbe), reseeded before
// every draw. web/ext/draw.test.js does the same with the port and compares
// op by op.
//
//   javac -cp <jar-dir> -d /tmp/probe web/tools/RecG.java web/tools/ExtContOProbe.java web/tools/ExtDrawProbe.java
//   java -cp "<jar-dir>;/tmp/probe" ExtDrawProbe ext/data/models.radq > draw.json   (gzip -> web/ext/draw.expected.json.gz)

import java.io.*;
import java.lang.reflect.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;
import java.util.zip.*;

public class ExtDrawProbe {
    // x, y, z, xz, xy, zy: near and far (the LOD switch), turned and tilted
    static final int[][] POSES = {
        {0, 100, 1500, 0, 0, 0},
        {200, 50, 2200, 45, 10, 0},
        {-300, 150, 3000, 135, 0, 20},
        {0, 250, 9000, 270, 0, 0},
    };

    // `c.f=v` sets a ContO field, `m.f=v` a Medium field (v: int, bool,
    // [a,b,c]; `m.effect[i]=true` sets one element), `call:x` calls ContO.x(g)
    // every frame (or x() once when it takes no Graphics). draw.test.js parses
    // the same strings.
    public static final String[] EFFECTS = {
        "call:setfire",
        "c.teleported=true;c.telefade=128",
        "c.invisiblepiece=100",
        "c.glowlines=true;c.glowcustom=true;c.glowcolour=[255,40,40];c.playerglow=true",
        "c.spatk=true;c.freeze=true",
        "c.weaken=true;c.leech=true;c.strswap=true",
        "c.shadowcar=true;c.greenflame=true;c.flameheight=3",
        "c.lightup=true;c.outoftrack=true;c.floorguardian=true;c.dmgcolours=[200,60,60];c.spec=[90,20,160]",
        "c.weakstage=2",
        "c.elec=true",
        "c.fix=true",
        "m.showsnow=true;m.snowno=40;m.snowheight=300",
        "m.effect[10]=true",
        "m.effect[2]=true",
        "m.effect[9]=true",
        "call:teleflash",
        "call:drawsun",
    };
    public static final int FRAMES = 6;

    static Object parse(String v, Class<?> t) {
        if (t == boolean.class) return Boolean.parseBoolean(v);
        if (t == int.class) return Integer.parseInt(v);
        if (t == float.class) return Float.parseFloat(v);
        if (t == int[].class) return Arrays.stream(v.replaceAll("[\\[\\]]", "").split(",")).mapToInt(Integer::parseInt).toArray();
        throw new IllegalArgumentException(t.toString());
    }

    static final Pattern INDEXED = Pattern.compile("(\\w+)\\[(\\d+)\\]");

    /** Apply a setup; returns the ContO methods to call after each d(g). */
    static List<Method> apply(String setup, Object c, Object m) throws Exception {
        List<Method> calls = new ArrayList<>();
        for (String part : setup.split(";")) {
            if (part.startsWith("call:")) {
                String n = part.substring(5);
                try { calls.add(c.getClass().getDeclaredMethod(n, java.awt.Graphics2D.class)); }
                catch (NoSuchMethodException e) { c.getClass().getDeclaredMethod(n).invoke(c); }
                continue;
            }
            String[] kv = part.split("=", 2);
            Object target = kv[0].startsWith("c.") ? c : m;
            String f = kv[0].substring(2);
            Matcher ix = INDEXED.matcher(f);
            if (ix.matches()) {
                Field fl = target.getClass().getDeclaredField(ix.group(1));
                fl.setAccessible(true);
                Array.set(fl.get(target), Integer.parseInt(ix.group(2)), parse(kv[1], fl.getType().getComponentType()));
            } else {
                Field fl = target.getClass().getDeclaredField(f);
                fl.setAccessible(true);
                fl.set(target, parse(kv[1], fl.getType()));
            }
        }
        return calls;
    }

    static String sha1(String s) throws Exception {
        byte[] h = java.security.MessageDigest.getInstance("SHA-1").digest(s.getBytes(java.nio.charset.StandardCharsets.UTF_8));
        StringBuilder b = new StringBuilder();
        for (byte x : h) b.append(String.format("%02x", x));
        return b.toString();
    }

    static int code(String name) {
        int j = 0;
        for (int k = 0; k < 129; k++) if (name.startsWith(ExtContOProbe.AS[k])) j = k;
        return j;
    }

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
        Field mField = contO.getDeclaredField("m");
        mField.setAccessible(true);

        List<String> names = new ArrayList<>();
        Map<String, Object> bases = new HashMap<>();
        try (ZipInputStream z = new ZipInputStream(new ByteArrayInputStream(b))) {
            for (ZipEntry e = z.getNextEntry(); e != null; e = z.getNextEntry()) {
                byte[] data = z.readAllBytes();
                ExtContOProbe.xs = ExtContOProbe.SEED;
                bases.put(e.getName(), load.newInstance(data, m, t, xtg, code(e.getName())));
                names.add(e.getName());
            }
        }

        StringBuilder out = new StringBuilder("{\"draws\":[\n");
        boolean first = true;
        for (String name : names) {
            for (int p = 0; p < POSES.length; p++) {
                int[] q = POSES[p];
                ExtContOProbe.xs = ExtContOProbe.SEED;
                Object c = copy.newInstance(bases.get(name), q[0], q[1], q[2], q[3]);
                contO.getDeclaredField("xy").setInt(c, q[4]);
                contO.getDeclaredField("zy").setInt(c, q[5]);
                RecG g = new RecG();
                String err = "";
                try { d.invoke(c, g); } catch (InvocationTargetException x) { err = String.valueOf(x.getCause()); }
                if (!first) out.append(",\n");
                first = false;
                out.append("{\"name\":").append(ExtContOProbe.q(name)).append(",\"pose\":").append(p)
                   .append(",\"error\":").append(ExtContOProbe.q(err)).append(",\"ops\":[").append(g.out).append("]}");
            }
        }

        out.append("\n],\"effects\":[\n");
        first = true;
        // Fresh bases AND a fresh Trackers: every ContO appends its collision
        // boxes to the Trackers it is built with, and the shadow code reads
        // them. draw.test.js does exactly this: all 129 models, in zip order,
        // into one new Trackers.
        Object t2 = trackers.getDeclaredConstructor().newInstance();
        Object mb = medium.getDeclaredConstructor().newInstance();   // and a Medium the draws above never touched
        try (ZipInputStream z = new ZipInputStream(new ByteArrayInputStream(b))) {
            for (ZipEntry e = z.getNextEntry(); e != null; e = z.getNextEntry()) {
                byte[] data = z.readAllBytes();
                ExtContOProbe.xs = ExtContOProbe.SEED;
                bases.put(e.getName(), load.newInstance(data, mb, t2, xtg, code(e.getName())));
            }
        }
        for (int s = 0; s < EFFECTS.length; s++) {
            for (String name : names) {
                if (code(name) >= 39) continue;             // cars only
                Object m2 = medium.getDeclaredConstructor().newInstance();   // m.* effects must not leak
                ExtContOProbe.xs = ExtContOProbe.SEED;
                Object c = copy.newInstance(bases.get(name), 0, 100, 1500, 30);
                if (!Boolean.getBoolean("probe.keepm")) mField.set(c, m2);
                RecG g = new RecG();
                String err = "";
                try {
                    List<Method> calls = apply(EFFECTS[s], c, m2);
                    for (int f = 0; f < FRAMES; f++) {
                        d.invoke(c, g);
                        for (Method call : calls) call.invoke(c, g);
                    }
                } catch (InvocationTargetException x) { err = String.valueOf(x.getCause()); }
                if (!first) out.append(",\n");
                first = false;
                // effects are stored as a hash of "[" + ops + "]" (100+ MB otherwise);
                // rerun this probe to see the ops of a draw that differs
                String ops = "[" + g.out + "]";
                // -Dprobe.only=<model>:<effect>: print that draw's ops in full to stderr
                if ((name + ":" + s).equals(System.getProperty("probe.only"))) System.err.println(ops);
                out.append("{\"name\":").append(ExtContOProbe.q(name)).append(",\"effect\":").append(s)
                   .append(",\"error\":").append(ExtContOProbe.q(err)).append(",\"n\":").append(g.n)
                   .append(",\"sha1\":\"").append(sha1(ops)).append("\"}");
            }
        }
        System.out.print(out.append("\n]}\n"));
    }
}
