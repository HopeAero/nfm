package tools;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import sun.misc.Unsafe;

public class MadProbe {
    // Must equal the setSeed() argument in web/Mad.test.js.
    static final int SEED = 12345;
    static int xs, dxs;
    static boolean forceDraw;

    // Math.random() is RandomNumberGeneratorHolder.randomNumberGenerator.nextDouble()
    // in JDK 8, so swapping that static final Random makes the unmodified
    // Game.jar deterministic -- no bytecode patch. The replacement is web/java.js
    // random()'s xorshift32, bit for bit, INCLUDING its two-stream split: the
    // port deliberately runs ContO.dust (particles only) on a separate draw
    // stream for netplay (WORK.md, 2026-08-02), so calls reached from it take
    // the draw stream here too. Must run before Math.random() is JIT-compiled,
    // or the old Random may already be folded in as a constant.
    // ponytail: JDK 8 internals and a stack walk per call; fine for a probe.
    static void seedMathRandom(Unsafe unsafe, int seed) throws Exception {
        xs = seed == 0 ? 1 : seed;
        dxs = (xs ^ 0x9e3779b9) == 0 ? 1 : xs ^ 0x9e3779b9;
        Field f = Class.forName("java.lang.Math$RandomNumberGeneratorHolder")
                .getDeclaredField("randomNumberGenerator");
        unsafe.putObject(unsafe.staticFieldBase(f), unsafe.staticFieldOffset(f), new java.util.Random() {
            @Override public double nextDouble() {
                boolean draw = forceDraw || inDust();
                int x = draw ? dxs : xs;
                x ^= x << 13;
                x ^= x >>> 17;
                x ^= x << 5;
                if (draw) dxs = x; else xs = x;
                return (x & 0xffffffffL) / 4294967296.0;
            }
        });
    }

    static boolean inDust() {
        for (StackTraceElement e : new Throwable().getStackTrace())
            if (e.getClassName().equals("ContO") && e.getMethodName().equals("dust")) return true;
        return false;
    }

    public static void main(String[] args) throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);

        // Before anything touches Math.random(): Medium's constructor already does.
        seedMathRandom(unsafe, SEED);

        // Instantiate collaborators
        Class<?> mClass = Class.forName("Medium");
        Object m = mClass.getDeclaredConstructor().newInstance();

        Class<?> trackersClass = Class.forName("Trackers");
        Object trackers = trackersClass.getDeclaredConstructor().newInstance();

        Class<?> checkPointsClass = Class.forName("CheckPoints");
        Object checkPoints = checkPointsClass.getDeclaredConstructor().newInstance();

        Class<?> controlClass = Class.forName("Control");
        Object control = controlClass.getDeclaredConstructor(mClass).newInstance(m);

        Class<?> rpdClass = Class.forName("Record");
        Object rpd = rpdClass.getDeclaredConstructor(mClass).newInstance(m);

        Class<?> cdClass = Class.forName("CarDefine");
        Object cd = unsafe.allocateInstance(cdClass);

        Class<?> xtClass = Class.forName("xtGraphics");
        Object xt = unsafe.allocateInstance(xtClass);

        Class<?> contOClass = Class.forName("ContO");
        // Build a REAL ContO from an actual model out of data/models.zip.
        //
        // Unsafe.allocateInstance() skips the constructor, so every reference
        // field stays null and drive() NPEs on a different one each time you
        // patch the last (ContO.m, then ContO.sx, ...). Running the real
        // constructor initialises all of them at once and is closer to what
        // the game actually does.
        byte[] modelBytes = null;
        try (java.util.zip.ZipInputStream zis = new java.util.zip.ZipInputStream(
                new java.io.FileInputStream("data/models.zip"))) {
            java.util.zip.ZipEntry ze;
            while ((ze = zis.getNextEntry()) != null) {
                if (ze.getName().equals("formula7.rad")) {
                    java.io.ByteArrayOutputStream bos = new java.io.ByteArrayOutputStream();
                    byte[] chunk = new byte[4096];
                    int r;
                    while ((r = zis.read(chunk)) > 0) bos.write(chunk, 0, r);
                    modelBytes = bos.toByteArray();
                    break;
                }
            }
        }
        if (modelBytes == null) throw new IllegalStateException("formula7.rad not found in models.zip");
        Object baseContO = contOClass
                .getDeclaredConstructor(byte[].class, mClass, trackersClass)
                .newInstance(modelBytes, m, trackers);
        // Place it: ContO(ContO, x, y, z, a)
        Object contO = contOClass
                .getDeclaredConstructor(contOClass, int.class, int.class, int.class, int.class)
                .newInstance(baseContO, 1000, 200, -5000, 0);

        // Populate cd fields for car index 0
        float[] bounce = new float[16]; Arrays.fill(bounce, 1.2f);
        int[] flipy = new int[16]; Arrays.fill(flipy, 100);
        float[] airs = new float[16]; Arrays.fill(airs, 1.0f);
        int[] airc = new int[16]; Arrays.fill(airc, 1);
        // -Dnfm.odd=1: odd gear thresholds and handbrake (car 0's real swits
        // are 50/185/282). The default 50/100/150 and handb 5 hide Java's int
        // division `swits / 2` in Mad.drive: an even value divides the same
        // either way. Expected output: MadProbe.odd.expected.txt.
        boolean odd = Boolean.getBoolean("nfm.odd");
        int[][] swits = new int[16][3]; for (int i=0; i<16; i++) { swits[i][0]=odd ? 51 : 50; swits[i][1]=odd ? 185 : 100; swits[i][2]=odd ? 283 : 150; }
        float[][] acelf = new float[16][3]; for (int i=0; i<16; i++) { acelf[i][0]=10f; acelf[i][1]=10f; acelf[i][2]=10f; }
        int[] handb = new int[16]; Arrays.fill(handb, odd ? 7 : 5);
        int[] turn = new int[16]; Arrays.fill(turn, 4);
        float[] simag = new float[16]; Arrays.fill(simag, 1.0f);
        float[] grip = new float[16]; Arrays.fill(grip, 20.0f);
        int[] powerloss = new int[16]; Arrays.fill(powerloss, 100000);
        int[] maxmag = new int[16]; Arrays.fill(maxmag, 1000);
        int[] msquash = new int[16]; Arrays.fill(msquash, 100);
        int[] clrad = new int[16]; Arrays.fill(clrad, 500);
        float[] dammult = new float[16]; Arrays.fill(dammult, 1.0f);
        float[] moment = new float[16]; Arrays.fill(moment, 1.0f);
        int[] push = new int[16]; Arrays.fill(push, 100);
        int[] revpush = new int[16]; Arrays.fill(revpush, 100);
        int[] revlift = new int[16]; Arrays.fill(revlift, 10);
        int[] lift = new int[16]; Arrays.fill(lift, 10);
        float[] comprad = new float[16]; Arrays.fill(comprad, 300f);

        setField(cd, "bounce", bounce);
        setField(cd, "flipy", flipy);
        setField(cd, "airs", airs);
        setField(cd, "airc", airc);
        setField(cd, "swits", swits);
        setField(cd, "acelf", acelf);
        setField(cd, "handb", handb);
        setField(cd, "turn", turn);
        setField(cd, "simag", simag);
        setField(cd, "grip", grip);
        setField(cd, "powerloss", powerloss);
        setField(cd, "maxmag", maxmag);
        setField(cd, "msquash", msquash);
        setField(cd, "clrad", clrad);
        setField(cd, "dammult", dammult);
        setField(cd, "moment", moment);
        setField(cd, "push", push);
        setField(cd, "revpush", revpush);
        setField(cd, "revlift", revlift);
        setField(cd, "lift", lift);
        setField(cd, "comprad", comprad);

        // The draw bank: web/Medium.js keeps drand/ddiup/dcntrn/dtrn beside the
        // sim bank and ContO.dust walks those. Here that is a second Medium on
        // contO.m, built on the draw stream so its constructor's Math.random()
        // calls do not advance the sim stream (JS has one Medium, so it never
        // pays them), then the draw stream is restarted to match a fresh JS one.
        forceDraw = true;
        Object mDraw = mClass.getDeclaredConstructor().newInstance();
        forceDraw = false;
        dxs = (SEED ^ 0x9e3779b9) == 0 ? 1 : SEED ^ 0x9e3779b9;
        setField(contO, "m", mDraw);

        // Set contO keyx/keyz/grat
        int[] keyx = new int[]{-100, 100, 100, -100};
        int[] keyz = new int[]{200, 200, -200, -200};
        setField(contO, "keyx", keyx);
        setField(contO, "keyz", keyz);
        setField(contO, "grat", 0);
        setField(contO, "x", 1000);
        setField(contO, "y", 200);
        setField(contO, "z", -5000);
        setField(contO, "xz", 45);
        setField(contO, "zy", 0);
        setField(contO, "xy", 0);

        // Populate trackers sect
        Method devideMethod = trackersClass.getDeclaredMethod("devidetrackers", int.class, int.class, int.class, int.class);
        devideMethod.invoke(trackers, -10000, 40000, -10000, 40000);

        // Populate checkPoints fields
        setField(checkPoints, "pcs", 0);
        setField(checkPoints, "dested", new int[8]);
        setField(checkPoints, "nfix", 0);
        setField(checkPoints, "n", 4);
        // typ must contain POSITIVE entries: Mad.java:1600 spins forever on
        // `while (checkPoints.typ[n114] <= 0)` if they are all zero, which is
        // the default new int[140]. loadstage() normally fills these in.
        setField(checkPoints, "typ", new int[]{1, 1, 1, 1});
        setField(checkPoints, "x", new int[]{0, 5000, 5000, 0});
        setField(checkPoints, "y", new int[]{0, 0, 0, 0});
        setField(checkPoints, "z", new int[]{0, 0, 5000, 5000});
        // nsp must be > 0: Mad.java:1578 `while (m.checkpoint >= nsp)` subtracts
        // zero forever when nsp == 0.
        setField(checkPoints, "nsp", 4);
        setField(checkPoints, "nlaps", 1);
        setField(checkPoints, "stage", 1);
        setField(checkPoints, "fn", 0);

        // Record arrays
        setField(rpd, "dest", new int[8]);
        setField(rpd, "fix", new int[8]);

        // xt fields
        setField(xt, "im", 0);
        setField(xt, "dcrashes", new int[8]);
        // Muted: xt is allocateInstance'd, so its sound clips are null, and a
        // faster car (the odd run) reaches skid() -> skid[i].play(). Sound only.
        setField(xt, "mutes", true);

        // Instantiate Mad
        Class<?> madClass = Class.forName("Mad");
        Object mad = madClass.getDeclaredConstructor(cdClass, mClass, rpdClass, xtClass, int.class)
                              .newInstance(cd, m, rpd, xt, 0);

        // Test reseto
        Method resetoMethod = madClass.getDeclaredMethod("reseto", int.class, contOClass, checkPointsClass);
        resetoMethod.invoke(mad, 0, contO, checkPoints);

        System.out.println("RESETO_FORCA=" + getField(mad, "forca"));
        System.out.println("RESETO_POWER=" + getField(mad, "power"));

        // Setup control for driving: up=true, right=true
        setField(control, "up", true);
        setField(control, "right", true);

        // Run multi-tick test: 300 ticks!
        Method driveMethod = madClass.getDeclaredMethod("drive", controlClass, contOClass, trackersClass, checkPointsClass);

        System.out.println("--- MULTI-TICK DRIFT PROBE (300 TICKS) ---");
        for (int tick = 1; tick <= 300; tick++) {
            try {
                driveMethod.invoke(mad, control, contO, trackers, checkPoints);
            } catch (Exception e) {
                System.out.println("Exception at tick " + tick + ": " + e.getCause());
                e.getCause().printStackTrace(System.out);
                break;
            }
            int x = (Integer) getField(contO, "x");
            int y = (Integer) getField(contO, "y");
            int z = (Integer) getField(contO, "z");
            int xz = (Integer) getField(contO, "xz");
            int zy = (Integer) getField(contO, "zy");
            int xy = (Integer) getField(contO, "xy");
            float speed = (Float) getField(mad, "speed");
            int pzy = (Integer) getField(mad, "pzy");
            int pxy = (Integer) getField(mad, "pxy");

            if (tick <= 50 || tick == 100 || tick == 200 || tick == 300) {
                System.out.printf(java.util.Locale.ROOT, "TICK_%d: x=%d y=%d z=%d xz=%d zy=%d xy=%d speed=%.6f pzy=%d pxy=%d\n",
                    tick, x, y, z, xz, zy, xy, speed, pzy, pxy);
            }
        }

        setField(control, "up", true);
        setField(control, "right", true);
        // --- coast test: throttle for 80 more ticks, then release. AFTER the drift
        // probe, which Mad.test.js replays from a fresh reseto. ---
        System.out.println("--- COAST TEST ---");
        for (int t = 1; t <= 80; t++) driveMethod.invoke(mad, control, contO, trackers, checkPoints);
        System.out.println("after 80 throttle: speed=" + getField(mad, "speed"));
        setField(control, "up", false);
        setField(control, "right", false);
        for (int t = 1; t <= 300; t++) {
            driveMethod.invoke(mad, control, contO, trackers, checkPoints);
            if (t % 50 == 0 || t == 300) {
                System.out.println("  coast t+" + t + ": speed=" + getField(mad, "speed")
                    + " skid=" + getField(mad, "skid") + " mtouch=" + getField(mad, "mtouch"));
            }
        }
        // Probe leaf functions py, rpy
        Method pyMethod = madClass.getDeclaredMethod("py", int.class, int.class, int.class, int.class);
        int py1 = (Integer) pyMethod.invoke(mad, 50000, 0, 50000, 0);
        int py2 = (Integer) pyMethod.invoke(mad, -30000, 40000, -50000, 60000);
        System.out.println("PY_OVERFLOW=" + py1);
        System.out.println("PY_NEG=" + py2);

        Method rpyMethod = madClass.getDeclaredMethod("rpy", float.class, float.class, float.class, float.class, float.class, float.class);
        int rpy1 = (Integer) rpyMethod.invoke(mad, 500.5f, -200.25f, 100.0f, -300.0f, 400.0f, 1000.0f);
        System.out.println("RPY_VAL=" + rpy1);
    }

    private static void setField(Object obj, String fieldName, Object val) throws Exception {
        Field f = obj.getClass().getDeclaredField(fieldName);
        f.setAccessible(true);
        f.set(obj, val);
    }

    private static Object getField(Object obj, String fieldName) throws Exception {
        Field f = obj.getClass().getDeclaredField(fieldName);
        f.setAccessible(true);
        return f.get(obj);
    }
}
