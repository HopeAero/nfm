package det;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * The deterministic runtime a game instance sees in place of the JDK's clock,
 * sleep and Math.random. DiffRun loads a SEPARATE copy of this class into each
 * game's class loader, so each instance has its own clock and random stream.
 *
 * Time only moves when the game thread sleeps, by exactly the amount asked
 * for, and each game-thread sleep is a frame boundary where the harness holds
 * the thread until it has compared both instances.
 */
public final class Det {
    public static long nanos = 1_000_000_000L;
    // web/java.js random()'s xorshift32 (its sim stream), so a trace can hand
    // the port the exact generator state: setSeed(xs).
    public static int xs = 12345;
    public static volatile Thread game;       // GameSparker's thread; set by DiffRun
    public static volatile Thread harness;    // DiffRun's main thread: never blocked
    public static final Semaphore done = new Semaphore(0);
    public static final Semaphore go = new Semaphore(0);
    public static volatile boolean stopped;

    public static double random() {
        int x = xs;
        x ^= x << 13;
        x ^= x >>> 17;
        x ^= x << 5;
        xs = x;
        return (x & 0xffffffffL) / 4294967296.0;
    }
    public static long nanoTime() { return nanos; }
    public static long currentTimeMillis() { return nanos / 1_000_000L; }

    public static void sleep(long ms) throws InterruptedException {
        Thread t = Thread.currentThread();
        // Register the game thread at its FIRST sleep, not after start()
        // returns: otherwise a free-running frame or two slips through and
        // the two instances start out of step.
        if (game == null && t != harness && StackWalker.getInstance().walk(s -> s.anyMatch(
                f -> f.getClassName().equals("GameSparker") && f.getMethodName().equals("run")))) {
            game = t;
        }
        if (t == game) {
            nanos += ms * 1_000_000L;
            done.release();
            while (!go.tryAcquire(1, TimeUnit.SECONDS)) if (stopped) throw new InterruptedException("stopped");
        } else if (killed.contains(t)) {
            throw new ThreadDeath();
        } else if (t != harness) {
            // Loading-screen, sound and clip threads: real (short) sleeps. Their
            // effect on shared state is what the A/A control run measures.
            Thread.sleep(Math.min(ms, 20));
        }
    }

    /** Threads the game asked to stop(). Java 21 removed Thread.stop, which the
     *  game uses to kill its loading-screen thread; that thread dies instead at
     *  its next sleep, which it reaches within 20 ms. */
    static final java.util.Set<Thread> killed = java.util.Collections.newSetFromMap(new java.util.concurrent.ConcurrentHashMap<>());

    @SuppressWarnings("removal")
    public static void stop(Thread t) {
        if (t == null) throw new NullPointerException();
        killed.add(t);
        if (t == Thread.currentThread()) throw new ThreadDeath();
    }

    /** Every instance of a tracked game class, in construction order. The game
     *  keeps its state in locals of GameSparker.run, out of reach of the
     *  applet's fields; constructors register here instead (DiffRun.transform). */
    public static final java.util.List<Object> objects = java.util.Collections.synchronizedList(new java.util.ArrayList<>());

    public static void track(Object o) { objects.add(o); }

    // ---- trace capture (-Ddiffrun.trace=Class.method) ---------------------
    //
    // DiffRun instruments one method to call enter()/exit(). A sampled call
    // is written as the object graph reachable from `this` and the arguments,
    // before and after, with identities kept (the same id in both), plus the
    // random and clock state, so web/ext tests can rebuild the "before" with
    // the transpiled classes, run the port's method, and compare the "after".

    public static volatile boolean tracing;
    public static int traceFrom = 0, traceEvery = 1, traceMax = 20;
    public static java.io.Writer traceOut;
    static int calls = 0, taken = 0, depth = 0;
    static Object[] roots;
    static java.util.IdentityHashMap<Object, Integer> ids;
    static String preJson;
    static int preXs;
    static long preNanos;

    public static void enter(Object self, Object[] args) {
        if (!tracing || Thread.currentThread() != game) return;
        if (depth++ > 0) return;                        // not re-entrant
        int n = calls++;
        roots = null;
        if (n < traceFrom || (n - traceFrom) % traceEvery != 0 || taken >= traceMax) return;
        taken++;
        roots = new Object[args.length + 1];
        roots[0] = self;
        System.arraycopy(args, 0, roots, 1, args.length);
        ids = new java.util.IdentityHashMap<>();
        preXs = xs;
        preNanos = nanos;
        preJson = graph();
        if (logWrites) { writes = new StringBuilder(); nwrites = 0; }
    }

    public static boolean logWrites;

    // ---- field-write log (-Ddiffrun.trace.writes=true) ---------------------
    // Every PUTFIELD in a game class calls one of these (DiffRun.transform) with
    // the value about to be stored and the field's name. During a sampled call
    // they are appended in order; ext-trace.mjs logs the port's writes the same
    // way and reports the first one that differs -- the statement at fault.
    static StringBuilder writes;
    static int nwrites;

    static void w(String f, String v) {
        if (writes == null || Thread.currentThread() != game) return;
        if (nwrites++ > 0) writes.append(',');
        writes.append("[\"").append(f).append("\",").append(v).append(']');
    }
    public static void wI(int v, String f) { if (writes != null) w(f, Integer.toString(v)); }
    public static void wJ(long v, String f) { if (writes != null) w(f, Long.toString(v)); }
    public static void wF(float v, String f) { if (writes != null) w(f, value(v, null)); }
    public static void wD(double v, String f) { if (writes != null) w(f, value(v, null)); }
    public static void wA(Object v, String f) { if (writes != null) w(f, v == null ? "null" : v instanceof String s ? str(s) : "\"<" + v.getClass().getName() + ">\""); }

    public static void exit(Object self) {
        if (!tracing || Thread.currentThread() != game) return;
        if (--depth > 0 || roots == null) return;
        String wl = writes == null ? null : writes.toString();
        writes = null;
        String post = graph();
        try {
            traceOut.write("{\"call\":" + (calls - 1) + ",\"xs\":" + preXs + ",\"nanos\":" + preNanos
                    + (wl != null ? ",\"writes\":[" + wl + "]" : "")
                    + ",\"roots\":" + rootIds() + ",\"pre\":" + preJson + ",\"post\":" + post + "}\n");
            traceOut.flush();
        } catch (java.io.IOException e) { throw new RuntimeException(e); }
        roots = null;
    }

    static String rootIds() {
        StringBuilder b = new StringBuilder("[");
        for (int i = 0; i < roots.length; i++) { if (i > 0) b.append(','); b.append(value(roots[i], null)); }
        return b.append(']').toString();
    }

    static StringBuilder objs;
    static java.util.ArrayDeque<Object> queue;

    /** Every object reachable from the roots: {"id": {"c": class, "f": {...}} | {"a": elem, "v": [...]}} */
    static String graph() {
        objs = new StringBuilder("{");
        queue = new java.util.ArrayDeque<>();
        java.util.Set<Object> done = java.util.Collections.newSetFromMap(new java.util.IdentityHashMap<>());
        for (Object r : roots) value(r, null);
        boolean first = true;
        while (!queue.isEmpty()) {
            Object o = queue.poll();
            if (!done.add(o)) continue;
            if (!first) objs.append(',');
            first = false;
            objs.append('"').append(ids.get(o)).append("\":");
            Class<?> c = o.getClass();
            if (c.isArray()) {
                Class<?> ct = c.getComponentType();
                objs.append("{\"a\":\"").append(code(ct)).append("\",\"v\":[");
                int n = java.lang.reflect.Array.getLength(o);
                for (int i = 0; i < n; i++) { if (i > 0) objs.append(','); objs.append(value(java.lang.reflect.Array.get(o, i), ct)); }
                objs.append("]}");
            } else {
                objs.append("{\"c\":\"").append(c.getName()).append("\",\"f\":{");
                boolean ff = true;
                for (java.lang.reflect.Field f : fields(c)) {
                    Object v;
                    try { v = f.get(o); } catch (IllegalAccessException e) { throw new RuntimeException(e); }
                    if (!ff) objs.append(',');
                    ff = false;
                    objs.append('"').append(f.getName()).append("\":").append(value(v, f.getType()));
                }
                objs.append("}}");
            }
        }
        return objs.append('}').toString();
    }

    static final java.util.Map<Class<?>, java.util.List<java.lang.reflect.Field>> FIELDS = new java.util.HashMap<>();

    static java.util.List<java.lang.reflect.Field> fields(Class<?> c) {
        return FIELDS.computeIfAbsent(c, k -> {
            java.util.List<java.lang.reflect.Field> out = new java.util.ArrayList<>();
            for (Class<?> x = k; x != null && isGame(x); x = x.getSuperclass())
                for (java.lang.reflect.Field f : x.getDeclaredFields()) {
                    if (java.lang.reflect.Modifier.isStatic(f.getModifiers())) continue;
                    f.setAccessible(true);
                    out.add(f);
                }
            out.sort(java.util.Comparator.comparing(java.lang.reflect.Field::getName));
            return out;
        });
    }

    static boolean isGame(Class<?> c) { return c.getClassLoader() == Det.class.getClassLoader() && c.getPackageName().isEmpty(); }

    static String code(Class<?> t) {
        if (t == int.class) return "I"; if (t == float.class) return "F"; if (t == double.class) return "D";
        if (t == boolean.class) return "Z"; if (t == long.class) return "J"; if (t == byte.class) return "B";
        if (t == short.class) return "S"; if (t == char.class) return "C";
        return "L";
    }

    /** A value as JSON: a number, bool, string, null, {"r": id}, or {"x": class} for what is not game state. */
    static String value(Object v, Class<?> declared) {
        if (v == null) return "null";
        if (v instanceof Boolean) return v.toString();
        if (v instanceof Character ch) return Integer.toString(ch);
        if (v instanceof Number num) {
            double d = num instanceof Float fl ? (double) (float) fl : num.doubleValue();
            if (Double.isNaN(d)) return "\"NaN\"";
            if (Double.isInfinite(d)) return d > 0 ? "\"Infinity\"" : "\"-Infinity\"";
            if (d == Math.rint(d) && Math.abs(d) < 1e15) return Long.toString((long) d);
            return Double.toString(d);
        }
        if (v instanceof String s) return "{\"s\":" + str(s) + "}";
        Class<?> c = v.getClass();
        if (c.isArray() || isGame(c)) {
            Integer id = ids.get(v);
            if (id == null) { id = ids.size(); ids.put(v, id); }
            queue.add(v);
            return "{\"r\":" + id + "}";
        }
        return "{\"x\":\"" + c.getName() + "\"}";
    }

    static String str(String s) {
        StringBuilder b = new StringBuilder("\"");
        for (char ch : s.toCharArray()) {
            if (ch == '"' || ch == '\\') b.append('\\').append(ch);
            else if (ch < 32 || ch > 126) b.append(String.format("\\u%04x", (int) ch));
            else b.append(ch);
        }
        return b.append('"').toString();
    }

    public static final class DetDate {
        public DetDate() {}
        public long getTime() { return currentTimeMillis(); }
    }
}
