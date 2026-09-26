// The base race's measuring switches (web/main.js), for the Extended race:
//
//   ?bench=S     average S seconds of race after a warmup (?warmup=MS, 3000),
//                then freeze with the report on screen; R runs another window.
//                Unlike the base, ?stats=1 does not imply it: Extended's stats
//                line is the rolling one. Warmup counts race frames only, so
//                the menus, the presenter, the fly-in and the countdown are
//                not in it (race.js feeds it from starcnt 0).
//   ?prof=1      per-frame slices the scene counters cannot see: the backdrop
//                (Medium.d), Plane.d and Plane.s (the ground shadow) calls and
//                ms, and the tick's Madness.drive / colide (regx/regz inside).
//   ?maxfps=N    cap the frame rate by skipping rAF callbacks.
//
// Extended's ContO/Plane do not bump the scene counters graphics.js keeps for
// the base (objCalls, objDrawn, faceCalls, projVerts), so with ?bench or ?prof
// they are counted by wrapping ContO.d / Plane.d: a ContO.d that reached a
// Plane.d is an object drawn.

const FRAME_SLOP = 2;   // rAF timestamp jitter, as in web/main.js

/** A frame limiter: true means skip this rAF. */
export function frameCap(maxfps) {
  if (!(maxfps > 0)) return null;
  const interval = 1000 / maxfps;
  let nextAt = 0;
  return (now) => {
    if (now < nextAt - FRAME_SLOP) return true;
    nextAt += interval;
    if (nextAt < now) nextAt = now + interval;   // behind (hidden tab, stall): a fresh cadence
    return false;
  };
}

const wrap = (proto, name, around) => {
  const inner = proto[name];
  proto[name] = function (...a) { return around(this, inner, a); };
};

/** Scene counters on the graphics passed to ContO.d / Plane.d. */
export function countScene(ContO, Plane) {
  wrap(Plane.prototype, 'd', (p, inner, a) => {
    const g = a[0];
    ++g.faceCalls;
    g.projVerts += p.n;
    return inner.apply(p, a);
  });
  wrap(ContO.prototype, 'd', (o, inner, a) => {
    const g = a[0], before = g.faceCalls;
    ++g.objCalls;
    const r = inner.apply(o, a);
    if (g.faceCalls !== before) ++g.objDrawn;
    return r;
  });
}

/** ?prof=1: timed slices, per frame (`frame`, reset by the loop) and summed (`total`). */
export function installProfile({ medium, Plane, Madness }) {
  const keys = ['backdrop', 'plane', 'planeN', 'shadow', 'shadowN', 'drive', 'colide'];
  const frame = Object.fromEntries(keys.map((k) => [k, 0]));
  const total = { ...frame };
  const timed = (ms, n) => (self, inner, a) => {
    const t = performance.now();
    const r = inner.apply(self, a);
    frame[ms] += performance.now() - t;
    if (n) frame[n]++;
    return r;
  };
  const d = medium.d;
  medium.d = function (g) { const t = performance.now(); d.call(this, g); frame.backdrop += performance.now() - t; };
  wrap(Plane.prototype, 'd', timed('plane', 'planeN'));
  wrap(Plane.prototype, 's', timed('shadow', 'shadowN'));
  // colide runs regx/regz; drive is called from outside it, so the two do not nest
  wrap(Madness.prototype, 'drive', timed('drive'));
  wrap(Madness.prototype, 'colide', timed('colide'));
  return {
    frame, total,
    /** folds this frame into the total, then clears it */
    next() { for (const k of keys) { total[k] += frame[k]; frame[k] = 0; } },
    reset() { for (const k of keys) total[k] = frame[k] = 0; },
    line(f) {
      return `plane.d ${(f.plane).toFixed(1)}ms/${f.planeN}  shadow ${f.shadow.toFixed(1)}ms/${f.shadowN}`
        + `  backdrop ${f.backdrop.toFixed(1)}ms  drive ${f.drive.toFixed(1)}ms  colide ${f.colide.toFixed(1)}ms`;
    },
  };
}

/** ?bench=S: the base's fixed-window benchmark and its report. */
export class Bench {
  constructor(seconds, warmupMs, prof = null) {
    this.windowMs = seconds * 1000;
    this.warmupMs = warmupMs;
    this.prof = prof;
    this.restart();
    this.firstAt = 0;     // the first race frame: the warmup runs from it
  }

  restart() {
    Object.assign(this, {
      start: 0, done: false, frames: 0, ticks: 0, simMs: 0, drawMs: 0, verts: 0,
      inputVerts: 0, objCalls: 0, objDrawn: 0, faceCalls: 0, projVerts: 0,
      worstFrame: 0, lastFrameAt: 0, n: 0, sx: 0, sy: 0, sxy: 0, sxx: 0, syy: 0, xMin: Infinity, xMax: 0,
    });
    this.firstAt = -Infinity;   // a rerun is warm already
  }

  /** One race frame. Returns true when the window just closed. */
  frame(now, { sim, draw, ticks, rendered, rd }) {
    if (this.done) return false;
    if (!this.start) {
      if (!this.firstAt) this.firstAt = now;
      if (now - this.firstAt >= this.warmupMs) { this.start = now; this.lastFrameAt = now; this.prof?.reset(); }
      return false;
    }
    this.simMs += sim; this.drawMs += draw; this.ticks += ticks;
    if (rendered) {
      this.frames++;
      this.verts = Math.max(this.verts, rd.vertexCount);
      this.inputVerts += rd.inputVerts;
      this.objCalls += rd.objCalls; this.objDrawn += rd.objDrawn; this.faceCalls += rd.faceCalls;
      this.projVerts += rd.projVerts;
      const x = rd.projVerts, y = draw;
      this.n++; this.sx += x; this.sy += y; this.sxy += x * y; this.sxx += x * x; this.syy += y * y;
      if (x < this.xMin) this.xMin = x;
      if (x > this.xMax) this.xMax = x;
      this.worstFrame = Math.max(this.worstFrame, now - this.lastFrameAt);
      this.lastFrameAt = now;
    }
    if (now - this.start >= this.windowMs) { this.done = true; this.elapsed = now - this.start; return true; }
    return false;
  }

  /** For the rolling stats line while the window runs. */
  status(now) {
    if (this.done) return '';
    return this.start ? `   [measuring ${((this.windowMs - (now - this.start)) / 1000).toFixed(1)}s left]`
      : `   [warming up ${(this.firstAt ? Math.max(0, this.warmupMs - (now - this.firstAt)) / 1000 : this.warmupMs / 1000).toFixed(1)}s]`;
  }

  /** The base's report, lines for lines (web/main.js benchReport); `prof` adds its slices. */
  report(config) {
    const prof = this.prof;
    const elapsed = this.elapsed, f = Math.max(1, this.frames);
    const perTick = this.simMs / Math.max(1, this.ticks), perFrame = this.drawMs / f;
    const inPerFrame = this.inputVerts / f;
    const den = this.n * this.sxx - this.sx * this.sx;
    const slope = den === 0 ? 0 : (this.n * this.sxy - this.sx * this.sy) / den;
    const intercept = this.n === 0 ? 0 : (this.sy - slope * this.sx) / this.n;
    const meanY = this.sy / Math.max(1, this.n);
    const ssTot = this.syy - this.n * meanY * meanY;
    const ssRes = this.syy - intercept * this.sy - slope * this.sxy;
    const r2 = ssTot > 0 ? 1 - ssRes / ssTot : 0;
    const lines = [
      `BENCHMARK  ${(elapsed / 1000).toFixed(1)}s window, ${this.warmupMs / 1000}s warmup discarded  --  PAUSED, press R to rerun`,
      `  ${(this.frames * 1000 / elapsed).toFixed(1)} fps avg   ${(this.ticks * 1000 / elapsed).toFixed(1)} tick/s   worst frame ${this.worstFrame.toFixed(0)}ms`,
      `  sim ${perTick.toFixed(2)} ms/tick   draw ${perFrame.toFixed(2)} ms/frame   -> ${((this.simMs + this.drawMs) / elapsed * 100).toFixed(0)}% of one core`,
      `  ${(perFrame / Math.max(1, inPerFrame) * 1e6).toFixed(0)} ns/vert submitted   (${Math.round(inPerFrame)} submitted, ${this.verts} emitted)`,
      `  per frame: ${Math.round(this.objDrawn / f)} objs drawn of ${Math.round(this.objCalls / f)}, ${Math.round(this.faceCalls / f)} faces, ${Math.round(inPerFrame)} verts`
        + `   -> ${(perFrame / Math.max(1, this.objDrawn / f) * 1000).toFixed(1)} us/obj, ${(perFrame / Math.max(1, this.faceCalls / f) * 1e6).toFixed(0)} ns/face`,
      `  fit over ${this.n} frames: ${(slope * 1000).toFixed(3)} us/projected vert + ${intercept.toFixed(2)} ms fixed   (x ${this.xMin}..${this.xMax}, R2 ${r2.toFixed(2)})`,
    ];
    if (prof) {
      const t = prof.total, ticks = Math.max(1, this.ticks);
      lines.push(`  per frame: backdrop ${(t.backdrop / f).toFixed(2)} ms, Plane.d ${(t.plane / f).toFixed(2)} ms (${Math.round(t.planeN / f)}), shadow ${(t.shadow / f).toFixed(2)} ms (${Math.round(t.shadowN / f)})`);
      lines.push(`  per tick: drive ${(t.drive / ticks).toFixed(2)} ms, colide ${(t.colide / ticks).toFixed(2)} ms`);
    }
    lines.push(`  ${config}`);
    return lines.join('\n');
  }
}
