// The race's music downloads, counted for real. The jar's presenter screen
// (hipnoload, fase 176) shows a hand-written size per stage (xtGraphics.sndsize)
// and waits a fixed number of frames; here each track -- a tracker module
// (radmusic.js) or the career's .ogg pair (jawt OggClip) -- is fetched through
// fetchTracked(), which reads the body as it arrives, and status() is what has
// come in of what the server said there is (Content-Length). race.js shows
// that on the screen and holds the jar there until it is all in.

import { fpath } from '../vfs.js';

let jobs = [];
const cache = new Map();          // url -> Promise<Uint8Array>

/** A new race: only what is fetched from now on is its music. */
export function begin() { jobs = []; }

/** Bytes in, bytes expected (0 if a server gave no length), and whether every download is done. */
export function status() {
  let loaded = 0, total = 0, done = true;
  for (const j of jobs) { loaded += j.loaded; total += j.total || j.loaded; if (!j.done) done = false; }
  return { loaded, total, done };
}

/** A file under the game root (e.g. 'ext/data/Files/music/stage1.radq'), with its progress counted. */
export function fetchTracked(path) {
  const url = fpath + path;
  if (cache.has(url)) {
    // already here (a menu module, a restarted race): nothing to wait for
    return cache.get(url);
  }
  const job = { loaded: 0, total: 0, done: false };
  jobs.push(job);
  const p = (async () => {
    try {
      const res = await fetch(url);
      if (!res.ok) throw new Error(`${res.status} fetching ${path}`);
      job.total = +res.headers.get('Content-Length') || 0;
      if (!res.body) {
        const b = new Uint8Array(await res.arrayBuffer());
        job.loaded = b.length;
        return b;
      }
      const reader = res.body.getReader(), parts = [];
      for (;;) {
        const { done, value } = await reader.read();
        if (done) break;
        parts.push(value);
        job.loaded += value.length;
      }
      const out = new Uint8Array(job.loaded);
      let at = 0;
      for (const v of parts) { out.set(v, at); at += v.length; }
      return out;
    } finally {
      job.done = true;              // a failed download must not hold the race forever
    }
  })();
  cache.set(url, p);
  p.catch(() => cache.delete(url));
  return p;
}

/** What the presenter's screen says in place of the jar's "N KB". */
export function progressText({ loaded, total, done }) {
  const kb = (n) => Math.round(n / 1024);
  if (done || !total) return `${kb(done ? total : loaded)} KB`;
  return `${kb(loaded)} / ${kb(total)} KB`;
}
