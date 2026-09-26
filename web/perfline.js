// Settings -> Show performance (the launcher's `perf`): what the line at the
// bottom of a race says. 'all' is the port's full line (vertex counts, buffer,
// and the sim/draw costs); without a `perf` parameter (a race opened by URL)
// the line stays as it always was.

export const PERF_LEVELS = ['off', 'fps', 'ms', 'all'];

/** The level asked for, or null for the line as it always was. */
export const perfLevel = (params) => (PERF_LEVELS.includes(params.get('perf')) ? params.get('perf') : null);

/**
 * The short lines; null means the caller's full line.
 * @param {string|null} level
 * @param {{fps: number, tps: number, tickMs: number, frameMs: number}} m  per second, per tick, per frame
 */
export function perfLine(level, m) {
  if (level === 'off') return '';
  if (level === 'fps') return `${m.fps.toFixed(0)} fps`;
  if (level === 'ms') return `${m.fps.toFixed(0)} fps  ${m.tps.toFixed(1)} tick/s  sim ${m.tickMs.toFixed(1)} ms/tick  draw ${m.frameMs.toFixed(1)} ms/frame`;
  return null;
}
