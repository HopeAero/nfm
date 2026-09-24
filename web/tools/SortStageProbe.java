package tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import sun.misc.Unsafe;

/**
 * Runs the real StageMaker.sortstage() on a list of placed parts and prints
 * the `bstage` it writes -- the oracle for web/stagemaker/sort.js.
 *
 * Input, one part per line: sp x z rot wh y maxR srx sry srz. StageMaker and
 * ContO are allocated without their constructors (Unsafe) and only the fields
 * sortstage() reads are set: co[], nob, cp.nsp, m (for its cos/sin tables)
 * and atp, the literal from StageMaker.java:253 (the constructor that sets it
 * builds an applet).
 *
 * Math.random() is swapped for web/java.js's xorshift32 from SEED, so the
 * halfpipe route-point coin flips match the port's.
 *
 *   javac -source 8 -target 8 -cp java/Game.jar -d OUT web/tools/SortStageProbe.java
 *   java -cp OUT;java/Game.jar tools.SortStageProbe parts.txt [seed]
 */
public class SortStageProbe {
    static int xs;

    public static void main(String[] args) throws Exception {
        Field uf = Unsafe.class.getDeclaredField("theUnsafe");
        uf.setAccessible(true);
        Unsafe unsafe = (Unsafe) uf.get(null);
        int seed = args.length > 1 ? Integer.parseInt(args[1]) : 1;
        xs = seed == 0 ? 1 : seed;
        Field rf = Class.forName("java.lang.Math$RandomNumberGeneratorHolder").getDeclaredField("randomNumberGenerator");
        unsafe.putObject(unsafe.staticFieldBase(rf), unsafe.staticFieldOffset(rf), new java.util.Random() {
            @Override public double nextDouble() {
                int x = xs;
                x ^= x << 13;
                x ^= x >>> 17;
                x ^= x << 5;
                xs = x;
                return (x & 0xffffffffL) / 4294967296.0;
            }
        });

        Class<?> smc = Class.forName("StageMaker");
        Class<?> cc = Class.forName("ContO");
        Class<?> mc = Class.forName("Medium");
        Class<?> cpc = Class.forName("CheckPoints");
        Object sm = unsafe.allocateInstance(smc);
        set(sm, "m", mc.getDeclaredConstructor().newInstance());
        Object cp = cpc.getDeclaredConstructor().newInstance();
        set(sm, "cp", cp);
        set(sm, "atp", ATP);

        List<Object> parts = new ArrayList<>();
        int nsp = 0;
        try (BufferedReader r = new BufferedReader(new FileReader(args[0]))) {
            String line;
            while ((line = r.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] f = line.split("\\s+");
                Object c = unsafe.allocateInstance(cc);
                int sp = Integer.parseInt(f[0]);
                set(c, "colok", sp);
                set(c, "x", Integer.parseInt(f[1]));
                set(c, "z", Integer.parseInt(f[2]));
                set(c, "roofat", Integer.parseInt(f[3]));
                set(c, "wh", Integer.parseInt(f[4]));
                set(c, "y", Integer.parseInt(f[5]));
                set(c, "maxR", Integer.parseInt(f[6]));
                set(c, "srx", Integer.parseInt(f[7]));
                set(c, "sry", Integer.parseInt(f[8]));
                set(c, "srz", Integer.parseInt(f[9]));
                if (sp == 30 || sp == 32 || sp == 54) ++nsp;
                parts.add(c);
            }
        }
        Object co = java.lang.reflect.Array.newInstance(cc, parts.size());
        for (int i = 0; i < parts.size(); i++) java.lang.reflect.Array.set(co, i, parts.get(i));
        set(sm, "co", co);
        set(sm, "nob", parts.size());
        set(cp, "nsp", nsp);
        Method sort = smc.getDeclaredMethod("sortstage");
        sort.setAccessible(true);
        // Restart the stream here: the Medium and CheckPoints constructors above
        // consume Math.random() too, and the port's side seeds right before its
        // sortStage() call.
        xs = seed == 0 ? 1 : seed;
        sort.invoke(sm);
        System.out.print(get(sm, "bstage"));
    }

    static void set(Object o, String name, Object v) throws Exception {
        Field f = findField(o.getClass(), name);
        f.setAccessible(true);
        f.set(o, v);
    }

    static Object get(Object o, String name) throws Exception {
        Field f = findField(o.getClass(), name);
        f.setAccessible(true);
        return f.get(o);
    }

    static Field findField(Class<?> c, String name) throws NoSuchFieldException {
        for (Class<?> k = c; k != null; k = k.getSuperclass()) {
            try { return k.getDeclaredField(name); } catch (NoSuchFieldException e) { /* up */ }
        }
        throw new NoSuchFieldException(name);
    }

    // StageMaker.java:253, verbatim.
    static final int[][] ATP = new int[][] { { 0, 2800, 0, -2800 }, { 0, 2800, 0, -2800 }, { 1520, 2830, -1520, -2830 }, { -1520, 2830, 1520, -2830 }, { 0, -1750, 1750, 0 }, { 0, 2800, 0, -2800 }, { 0, 2800, 0, -2800 }, { 0, -1750, 1750, 0 }, { 0, 2800, 0, -2800 }, { 0, -1750, 1750, 0 }, { 0, 2800, 0, -2800 }, { 0, 2800, 0, -2800 }, { 0, 560, 0, -560 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 385, 980, 385, -980 }, { 0, 0, 0, -600 }, { 0, 0, 0, 0 }, { 0, 2164, 0, -2164 }, { 0, 2164, 0, -2164 }, { 0, 3309, 0, -1680 }, { 0, 1680, 0, -3309 }, { 350, 0, -350, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 1810, 980, 1810, -980 }, { 0, 0, 0, 0 }, { 0, 500, 0, -500 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 2800, 0, -2800 }, { 0, 2800, 0, -2800 }, { 0, 1680, 0, -3309 }, { 0, 2800, 0, -2800 }, { 0, 2800, 0, -2800 }, { 0, 2800, 0, -2800 }, { 700, 1400, 700, -1400 }, { 0, -1480, 0, -1480 }, { 0, 0, 0, 0 }, { 350, 0, -350, 0 }, { 0, 0, 0, 0 }, { 700, 0, -700, 0 }, { 0, 0, 0, 0 }, { 0, -2198, 0, 1482 }, { 0, -1319, 0, 1391 }, { 0, -1894, 0, 2271 }, { 0, -826, 0, 839 }, { 0, -1400, 0, 1400 }, { 0, -1400, 0, 1400 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };
}
