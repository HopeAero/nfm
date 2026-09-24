import java.applet.Applet;
import java.applet.AppletStub;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Frame;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import jdk.internal.org.objectweb.asm.*;
import jdk.internal.org.objectweb.asm.commons.ClassRemapper;
import jdk.internal.org.objectweb.asm.commons.Remapper;

/**
 * Differential run: two copies of Need for Madness 2 Extended in one JVM, fed
 * the same clock, random stream and key presses, compared field by field and
 * pixel by pixel at every frame.
 *
 *   A = the jar's own classes, B = the decompiled source recompiled
 *   (or A = B = the jar: the A/A control run that measures harness noise).
 *
 * Each copy has its own class loader, so statics are separate. Game classes
 * are rewritten as they load: System.nanoTime/currentTimeMillis, Math.random,
 * Thread.sleep, Thread.stop and java.util.Date go to det.Det (see there). Each sleep on the
 * game thread is a frame boundary; both threads are held there while the
 * harness compares them, then released together.
 *
 * Run from a COPY of the game directory (the game reads data/ relatively and
 * writes its save files there):
 *   java --add-exports java.base/jdk.internal.org.objectweb.asm=ALL-UNNAMED \
 *        --add-exports java.base/jdk.internal.org.objectweb.asm.commons=ALL-UNNAMED \
 *        -cp <diffrun-classes> DiffRun <A-classes> <B-classes> <jar-classes> <frames> [script]
 *
 * script: comma-separated `frame:key[:hold]` or `frame:click:x:y`, e.g. `200:10,260:1004:120` presses
 * Enter at frame 200 and holds Up from 260 for 120 frames.
 *
 * -Ddiffrun.debug=true: everything unlocked and 999 stat/car points (see Inst.debug).
 * -Ddiffrun.trace=Madness.drive [-Ddiffrun.trace.from/every/max/out]: capture sampled
 *   calls of one method in copy A as before/after object graphs (det.Det.enter/exit). Keys are AWT Event
 * codes (Enter 10, Up 1004, Down 1005, Left 1006, Right 1007, Space 32).
 */
public class DiffRun {
    static final int MAX_DIFFS = 25;

    /** Child-first loader for the game's default-package classes and det.*. */
    static final class GameLoader extends ClassLoader {
        final Path game, lib;
        GameLoader(Path game, Path lib) { super(ClassLoader.getPlatformClassLoader()); this.game = game; this.lib = lib; }

        @Override protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
            synchronized (getClassLoadingLock(name)) {
                Class<?> c = findLoadedClass(name);
                if (c != null) return c;
                try {
                    byte[] b = null;
                    String p = name.replace('.', '/') + ".class";
                    if (name.startsWith("det.")) {
                        try (InputStream in = DiffRun.class.getClassLoader().getResourceAsStream(p)) { b = in.readAllBytes(); }
                    } else if (!name.contains(".")) {
                        Path f = game.resolve(p);
                        if (!Files.exists(f)) f = lib.resolve(p);
                        if (Files.exists(f)) b = transform(Files.readAllBytes(f));
                    } else {
                        Path f = lib.resolve(p);
                        if (Files.exists(f)) b = Files.readAllBytes(f);
                    }
                    if (b != null) {
                        c = defineClass(name, b, 0, b.length);
                        if (resolve) resolveClass(c);
                        return c;
                    }
                } catch (java.io.IOException e) { throw new ClassNotFoundException(name, e); }
                return super.loadClass(name, resolve);
            }
        }

        @Override protected URL findResource(String name) {
            try {
                Path f = lib.resolve(name);
                return Files.exists(f) ? f.toUri().toURL() : null;
            } catch (Exception e) { return null; }
        }
    }

    static final Set<String> TRACKED = new HashSet<>(Arrays.asList(
            "xtGraphics", "Medium", "Madness", "ContO", "Control", "CheckPoints", "Record", "Contva", "Bots", "Trackers"));

    // -Ddiffrun.trace=Class.method: that method calls det.Det.enter/exit (see there)
    static final String TRACE = System.getProperty("diffrun.trace", "");

    static byte[] transform(byte[] bytes) {
        ClassReader cr = new ClassReader(bytes);
        boolean tracked = TRACKED.contains(cr.getClassName());
        String traceCls = TRACE.contains(".") ? TRACE.substring(0, TRACE.indexOf('.')) : "";
        String traceMethod = TRACE.contains(".") ? TRACE.substring(TRACE.indexOf('.') + 1) : "";
        boolean traced = cr.getClassName().equals(traceCls);
        ClassWriter cw = new ClassWriter(traced ? ClassWriter.COMPUTE_MAXS : 0);
        Remapper dates = new Remapper() {
            @Override public String map(String n) { return n.equals("java/util/Date") ? "det/Det$DetDate" : n; }
        };
        ClassVisitor cv = new ClassVisitor(Opcodes.ASM9, new ClassRemapper(cw, dates)) {
            @Override public MethodVisitor visitMethod(int acc, String name, String desc, String sig, String[] exc) {
                boolean ctor = tracked && name.equals("<init>");
                MethodVisitor base = super.visitMethod(acc, name, desc, sig, exc);
                if (traced && name.equals(traceMethod)) {
                    base = new jdk.internal.org.objectweb.asm.commons.AdviceAdapter(Opcodes.ASM9, base, acc, name, desc) {
                        @Override protected void onMethodEnter() {
                            loadThis();
                            loadArgArray();
                            super.visitMethodInsn(Opcodes.INVOKESTATIC, "det/Det", "enter", "(Ljava/lang/Object;[Ljava/lang/Object;)V", false);
                        }
                        @Override protected void onMethodExit(int opcode) {
                            if (opcode == Opcodes.ATHROW) return;
                            loadThis();
                            super.visitMethodInsn(Opcodes.INVOKESTATIC, "det/Det", "exit", "(Ljava/lang/Object;)V", false);
                        }
                    };
                }
                return new MethodVisitor(Opcodes.ASM9, base) {
                    @Override public void visitInsn(int op) {
                        if (ctor && op == Opcodes.RETURN) {   // register the finished object
                            super.visitVarInsn(Opcodes.ALOAD, 0);
                            super.visitMethodInsn(Opcodes.INVOKESTATIC, "det/Det", "track", "(Ljava/lang/Object;)V", false);
                        }
                        super.visitInsn(op);
                    }
                    @Override public void visitMethodInsn(int op, String owner, String n, String d, boolean itf) {
                        if (op == Opcodes.INVOKESTATIC && (
                                (owner.equals("java/lang/System") && (n.equals("nanoTime") || n.equals("currentTimeMillis")))
                                || (owner.equals("java/lang/Math") && n.equals("random"))
                                || (owner.equals("java/lang/Thread") && n.equals("sleep") && d.equals("(J)V")))) {
                            owner = "det/Det";
                        }
                        if (op == Opcodes.INVOKEVIRTUAL && owner.equals("java/lang/Thread") && n.equals("stop") && d.equals("()V")) {
                            super.visitMethodInsn(Opcodes.INVOKESTATIC, "det/Det", "stop", "(Ljava/lang/Thread;)V", false);
                            return;
                        }
                        super.visitMethodInsn(op, owner, n, d, itf);
                    }
                };
            }
        };
        cr.accept(cv, traced ? ClassReader.EXPAND_FRAMES : 0);   // AdviceAdapter needs expanded frames
        return cw.toByteArray();
    }

    /** One running copy of the game. */
    static final class Inst {
        final String name;
        final GameLoader loader;
        Applet applet;
        Frame frame;
        Class<?> det;
        Semaphore done, go;

        Inst(String name, Path classes, Path lib) { this.name = name; this.loader = new GameLoader(classes, lib); }

        void boot(int x) throws Exception {
            det = loader.loadClass("det.Det");
            det.getField("harness").set(null, Thread.currentThread());
            done = (Semaphore) det.getField("done").get(null);
            go = (Semaphore) det.getField("go").get(null);
            applet = (Applet) loader.loadClass("GameSparker").getDeclaredConstructor().newInstance();
            applet.setStub((AppletStub) loader.loadClass("DesktopStub").getDeclaredConstructor().newInstance());
            frame = new Frame("diffrun " + name);
            frame.setFocusableWindowState(false);
            applet.setPreferredSize(new Dimension(870, 480));
            frame.add("Center", applet);
            frame.pack();
            frame.setLocation(x, 0);
            frame.setVisible(true);
            applet.init();
            applet.start();   // the game thread registers itself at its first sleep (Det.sleep)
        }

        boolean loading() throws Exception {
            for (Object o : objects()) if (o.getClass().getName().equals("xtGraphics")) {
                Object t = field(o, "runner");
                return t instanceof Thread && ((Thread) t).isAlive();
            }
            return true;
        }

        List<?> objects() throws Exception { return new ArrayList<>((List<?>) det.getField("objects").get(null)); }

        boolean waitFrame() throws InterruptedException { return done.tryAcquire(120, TimeUnit.SECONDS); }
        void release() { go.release(); }

        void key(int key, boolean down) {
            Event e = new Event(applet, 0, down ? Event.KEY_ACTION : Event.KEY_ACTION_RELEASE, 0, 0, key, 0);
            if (down) applet.keyDown(e, key); else applet.keyUp(e, key);
        }

        void mouse(boolean down, int x, int y) {
            Event e = new Event(applet, 0, down ? Event.MOUSE_DOWN : Event.MOUSE_UP, x, y, 0, 0);
            applet.mouseMove(new Event(applet, 0, Event.MOUSE_MOVE, x, y, 0, 0), x, y);
            if (down) applet.mouseDown(e, x, y); else applet.mouseUp(e, x, y);
        }

        Object field(Object o, String f) throws Exception {
            Field fl = o.getClass().getDeclaredField(f);
            fl.setAccessible(true);
            return fl.get(o);
        }

        long pixels() throws Exception {
            Object img = field(applet, "offImage");
            if (!(img instanceof BufferedImage)) return 0;
            BufferedImage b = (BufferedImage) img;
            int[] px = b.getRGB(0, 0, b.getWidth(), b.getHeight(), null, 0, b.getWidth());
            return Arrays.hashCode(px);
        }

        /**
         * Test setup (-Ddiffrun.debug=true), applied to both copies at the same
         * frame so they stay comparable: every stage open in both modes, no
         * "END OF BETA" wall, and 999 stat and car points, refilled each frame.
         * Caveat: career rules keyed on the frontier stage (stage == unlocked[1]:
         * the stage-23 boss, the hard 8/9/12/13, the level cap) then only
         * apply on stage 30.
         */
        void debug() throws Exception {
            for (Object o : objects()) {
                if (!o.getClass().getName().equals("xtGraphics")) continue;
                int[] u = (int[]) field(o, "unlocked"), ru = (int[]) field(o, "realunlocked");
                u[0] = ru[0] = 27;   // tracks.radq: 27 stages
                u[1] = ru[1] = 30;   // maxlevel[] has 31 entries, indexed by unlocked[1]
                set(o, "betalimit", 100);
                Arrays.fill((int[]) field(o, "statpoints"), 999);
                set(o, "carpoints", 999);
            }
        }

        void set(Object o, String f, int v) throws Exception {
            Field fl = o.getClass().getDeclaredField(f);
            fl.setAccessible(true);
            fl.setInt(o, v);
        }

        void stop() throws Exception {
            det.getField("stopped").set(null, true);
            frame.dispose();
        }
    }

    // ---- deep comparison ---------------------------------------------------

    static final Map<Class<?>, List<Field>> FIELDS = new HashMap<>();

    static List<Field> fields(Class<?> c) {
        return FIELDS.computeIfAbsent(c, k -> {
            List<Field> out = new ArrayList<>();
            for (Class<?> x = k; x != null && x.getClassLoader() instanceof GameLoader; x = x.getSuperclass()) {
                for (Field f : x.getDeclaredFields()) {
                    if (Modifier.isStatic(f.getModifiers()) || skipType(f.getType())) continue;
                    f.setAccessible(true);
                    out.add(f);
                }
            }
            out.sort(Comparator.comparing(Field::getName));
            return out;
        });
    }

    static boolean skipType(Class<?> t) {
        while (t.isArray()) t = t.getComponentType();
        if (t.isPrimitive() || t == String.class || t.getClassLoader() instanceof GameLoader) return false;
        if (Number.class.isAssignableFrom(t) || t == Boolean.class || t == Character.class) return false;
        return true;   // AWT, sound, threads, streams, JDK collections: not game state
    }

    static final class Cmp {
        final List<String> diffs = new ArrayList<>();
        final IdentityHashMap<Object, Boolean> seen = new IdentityHashMap<>();
        final Set<String> ignore;
        Cmp(Set<String> ignore) { this.ignore = ignore; }

        void diff(String path, Object a, Object b) {
            if (ignored(path)) return;
            if (diffs.size() < MAX_DIFFS) diffs.add(path + ": " + show(a) + " vs " + show(b));
            else if (diffs.size() == MAX_DIFFS) diffs.add("...");
        }

        boolean ignored(String path) {
            for (String p : ignore) if (path.equals(p) || path.startsWith(p + ".") || path.startsWith(p + "[")) return true;
            // field-name wildcard: "*.name" ignores that field anywhere
            int i = path.lastIndexOf('.');
            String leaf = i < 0 ? path : path.substring(i + 1).replaceAll("\\[.*$", "");
            return ignore.contains("*." + leaf);
        }

        static String show(Object o) {
            if (o == null) return "null";
            if (o instanceof Float) return o + "f(0x" + Integer.toHexString(Float.floatToRawIntBits((Float) o)) + ")";
            return String.valueOf(o);
        }

        void cmp(String path, Object a, Object b) {
            if (a == null || b == null) { if (a != b) diff(path, a, b); return; }
            Class<?> ca = a.getClass();
            if (ca.isArray()) { cmpArray(path, a, b); return; }
            if (a instanceof String || a instanceof Number || a instanceof Boolean || a instanceof Character) {
                if (!a.equals(b)) diff(path, a, b);
                return;
            }
            if (!(ca.getClassLoader() instanceof GameLoader)) return;
            if (!ca.getName().equals(b.getClass().getName())) { diff(path, ca.getName(), b.getClass().getName()); return; }
            if (seen.put(a, Boolean.TRUE) != null) return;
            List<Field> fa = fields(ca), fb = fields(b.getClass());
            for (int i = 0; i < fa.size(); i++) {
                Field x = fa.get(i), y = fb.get(i);
                String p = path + "." + x.getName();
                if (ignored(p)) continue;
                try {
                    Class<?> t = x.getType();
                    if (t == float.class) {
                        if (Float.floatToRawIntBits(x.getFloat(a)) != Float.floatToRawIntBits(y.getFloat(b))) diff(p, x.getFloat(a), y.getFloat(b));
                    } else if (t == double.class) {
                        if (Double.doubleToRawLongBits(x.getDouble(a)) != Double.doubleToRawLongBits(y.getDouble(b))) diff(p, x.getDouble(a), y.getDouble(b));
                    } else if (t.isPrimitive()) {
                        Object va = x.get(a), vb = y.get(b);
                        if (!va.equals(vb)) diff(p, va, vb);
                    } else cmp(p, x.get(a), y.get(b));
                } catch (IllegalAccessException e) { throw new RuntimeException(e); }
            }
        }

        void cmpArray(String path, Object a, Object b) {
            int n = Array.getLength(a), m = Array.getLength(b);
            if (n != m) { diff(path + ".length", n, m); return; }
            Class<?> ct = a.getClass().getComponentType();
            if (ct.isPrimitive()) {
                boolean eq;
                if (ct == float.class) {
                    float[] x = (float[]) a, y = (float[]) b; eq = true;
                    for (int i = 0; i < n; i++) if (Float.floatToRawIntBits(x[i]) != Float.floatToRawIntBits(y[i])) { diff(path + "[" + i + "]", x[i], y[i]); eq = false; break; }
                    return;
                }
                if (ct == int.class) eq = Arrays.equals((int[]) a, (int[]) b);
                else if (ct == boolean.class) eq = Arrays.equals((boolean[]) a, (boolean[]) b);
                else if (ct == byte.class) eq = Arrays.equals((byte[]) a, (byte[]) b);
                else if (ct == long.class) eq = Arrays.equals((long[]) a, (long[]) b);
                else if (ct == double.class) eq = Arrays.equals((double[]) a, (double[]) b);
                else if (ct == short.class) eq = Arrays.equals((short[]) a, (short[]) b);
                else eq = Arrays.equals((char[]) a, (char[]) b);
                if (!eq) for (int i = 0; i < n; i++) {
                    Object x = Array.get(a, i), y = Array.get(b, i);
                    if (!x.equals(y)) { diff(path + "[" + i + "]", x, y); break; }
                }
                return;
            }
            if (skipType(ct) && ct != Object.class) return;
            for (int i = 0; i < n; i++) cmp(path + "[" + i + "]", Array.get(a, i), Array.get(b, i));
        }
    }

    // ---- main ----------------------------------------------------------------

    public static void main(String[] args) throws Exception {
        Path a = Paths.get(args[0]), b = Paths.get(args[1]), lib = Paths.get(args[2]);
        int frames = Integer.parseInt(args[3]);
        Map<Integer, List<int[]>> script = new TreeMap<>();
        if (args.length > 4 && !args[4].isEmpty()) for (String s : args[4].split(",")) {
            String[] p = s.split(":");
            if (p[1].equals("click")) {   // f:click:x:y -> move, down at f, up at f+2
                int f = Integer.parseInt(p[0]), x = Integer.parseInt(p[2]), y = Integer.parseInt(p[3]);
                script.computeIfAbsent(f, k -> new ArrayList<>()).add(new int[]{-1, 1, x, y});
                script.computeIfAbsent(f + 2, k -> new ArrayList<>()).add(new int[]{-1, 0, x, y});
                continue;
            }
            int f = Integer.parseInt(p[0]), key = Integer.parseInt(p[1]), hold = p.length > 2 ? Integer.parseInt(p[2]) : 2;
            script.computeIfAbsent(f, k -> new ArrayList<>()).add(new int[]{key, 1});
            script.computeIfAbsent(f + hold, k -> new ArrayList<>()).add(new int[]{key, 0});
        }
        Set<String> ignore = new HashSet<>();
        if (args.length > 5 && !args[5].isEmpty()) ignore.addAll(Arrays.asList(args[5].split(",")));
        boolean pixelsCheck = !Boolean.getBoolean("diffrun.nopixels");
        int every = Integer.getInteger("diffrun.every", 1);
        boolean debugSetup = Boolean.getBoolean("diffrun.debug");
        // -Ddiffrun.shots=100,200: save both frames as diffrun-<n>A.png / <n>B.png (to script menus)
        Set<Integer> shots = new HashSet<>();
        for (String x : System.getProperty("diffrun.shots", "").split(",")) if (!x.isEmpty()) shots.add(Integer.parseInt(x));

        Inst A = new Inst("A", a, lib), B = new Inst("B", b, lib);
        A.boot(-4000); B.boot(-3000);
        if (!TRACE.isEmpty()) {   // trace copy A only; B runs the same code untraced
            A.det.getField("traceFrom").setInt(null, Integer.getInteger("diffrun.trace.from", 0));
            A.det.getField("traceEvery").setInt(null, Integer.getInteger("diffrun.trace.every", 1));
            A.det.getField("traceMax").setInt(null, Integer.getInteger("diffrun.trace.max", 20));
            String to = System.getProperty("diffrun.trace.out", "trace.jsonl.gz");   // gzip: one capture is ~200 MB of JSON
            A.det.getField("traceOut").set(null, new java.io.BufferedWriter(new java.io.OutputStreamWriter(
                    to.endsWith(".gz") ? new java.util.zip.GZIPOutputStream(new java.io.FileOutputStream(to), 1 << 16) : new java.io.FileOutputStream(to),
                    java.nio.charset.StandardCharsets.UTF_8)));
            A.det.getField("tracing").setBoolean(null, true);
        }
        int firstState = -1, firstPixel = -1, stateFrames = 0, pixelFrames = 0;
        for (int f = 0; f < frames; f++) {
            boolean ra = A.waitFrame(), rb = B.waitFrame();
            if (!ra || !rb) {
                System.out.println("frame " + f + ": HANG (A " + ra + ", B " + rb + ")");
                for (Inst in : new Inst[]{A, B}) {
                    Thread t = (Thread) in.det.getField("game").get(null);
                    if (t == null) continue;
                    System.out.println("  " + in.name + " game thread " + t.getState() + ":");
                    for (StackTraceElement e : t.getStackTrace()) System.out.println("      at " + e);
                }
                break;
            }
            for (int[] k : script.getOrDefault(f, List.of())) {
                if (k[0] == -1) { A.mouse(k[1] == 1, k[2], k[3]); B.mouse(k[1] == 1, k[2], k[3]); }
                else { A.key(k[0], k[1] == 1); B.key(k[0], k[1] == 1); }
            }
            Cmp c = new Cmp(ignore);
            // -Ddiffrun.every=N: deep-compare every Nth frame (a race holds thousands of
            // objects). Pixels are still hashed every frame, which catches any drift early.
            if (f % every == 0 || firstPixel >= 0) {
            c.cmp("gs", A.applet, B.applet);
            List<?> oa = A.objects(), ob = B.objects();
            if (oa.size() != ob.size()) c.diff("objects.size", oa.size(), ob.size());
            for (int i = 0; i < Math.min(oa.size(), ob.size()); i++)
                c.cmp("#" + i + ":" + oa.get(i).getClass().getName(), oa.get(i), ob.get(i));
            }
            long pa = pixelsCheck ? A.pixels() : 0, pb = pixelsCheck ? B.pixels() : 0;
            Object xa = A.objects().stream().filter(o -> o.getClass().getName().equals("xtGraphics")).findFirst().orElse(null);
            Object fa = xa == null ? "-" : A.field(xa, "fase");
            if (!c.diffs.isEmpty()) {
                stateFrames++;
                if (firstState < 0) {
                    firstState = f;
                    System.out.println("frame " + f + " (fase " + fa + "): FIRST STATE DIVERGENCE");
                    for (String d : c.diffs) System.out.println("    " + d);
                }
            }
            // xtGraphics.runner draws loading screens on real time (Det.sleep), so
            // pixels are only comparable while it is not running in either copy.
            boolean loading = A.loading() || B.loading();
            if (pa != pb && !loading) { pixelFrames++; if (firstPixel < 0) { firstPixel = f; System.out.println("frame " + f + " (fase " + fa + "): first pixel divergence"); } }
            if (shots.contains(f)) {
                for (Inst in : new Inst[]{A, B}) {
                    Object img = in.field(in.applet, "offImage");
                    if (img instanceof BufferedImage) javax.imageio.ImageIO.write((BufferedImage) img, "png", new java.io.File("diffrun-" + f + in.name + ".png"));
                }
            }
            if (f % 100 == 0) System.out.println("frame " + f + " fase " + fa + "  objects " + A.objects().size() + "  state diffs " + c.diffs.size() + "  pixels " + (pa == pb ? "same" : loading ? "differ (loading screen)" : "DIFFER"));
            if (debugSetup) { A.debug(); B.debug(); }
            A.release(); B.release();
        }
        System.out.println("RESULT frames=" + frames + " stateDivergentFrames=" + stateFrames + " (first " + firstState + ")"
                + " pixelDivergentFrames=" + pixelFrames + " (first " + firstPixel + ")");
        if (!TRACE.isEmpty()) ((java.io.Writer) A.det.getField("traceOut").get(null)).close();
        A.stop(); B.stop();
        System.exit(0);
    }
}
