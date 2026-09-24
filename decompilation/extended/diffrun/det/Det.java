package det;

import java.util.Random;
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
    public static Random rnd = new Random(12345);
    public static volatile Thread game;       // GameSparker's thread; set by DiffRun
    public static volatile Thread harness;    // DiffRun's main thread: never blocked
    public static final Semaphore done = new Semaphore(0);
    public static final Semaphore go = new Semaphore(0);
    public static volatile boolean stopped;

    public static double random() { return rnd.nextDouble(); }
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

    public static final class DetDate {
        public DetDate() {}
        public long getTime() { return currentTimeMillis(); }
    }
}
