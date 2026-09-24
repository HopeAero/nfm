// Songs imported in the Stage Maker, kept in this browser (IndexedDB
// `nfm-music`), for a custom stage's soundtrack(name, vol, KB) line.
//
// The Java's Stage Maker imported .mod files into mystages/mymusic/. The port
// also takes ordinary audio -- MP3, OGG, M4A, WAV -- which the browser decodes
// itself and streams through an <audio> element (music.js): cheaper than
// mixing a tracker module, and a recorded song cannot become a MOD anyway (a
// MOD is a score over short 8-bit samples of at most 128 KB each).
//
// A stage naming an imported song still loads in the desktop game; it simply
// finds no such file in mystages/mymusic and races in silence.

const DB_NAME = 'nfm-music';
const STORE = 'songs';
/** Tracker modules go to BassoonTracker; everything else to <audio>. */
export const TRACKER = /\.(mod|xm)$/i;
export const ACCEPT = '.mp3,.ogg,.oga,.m4a,.aac,.wav,.flac,.opus,.webm,.mod,.xm';

let dbPromise = null;
function open() {
  if (dbPromise) return dbPromise;
  dbPromise = new Promise((resolve, reject) => {
    const req = indexedDB.open(DB_NAME, 1);
    req.onupgradeneeded = () => req.result.createObjectStore(STORE, { keyPath: 'name' });
    req.onsuccess = () => resolve(req.result);
    req.onerror = () => reject(req.error);
  });
  return dbPromise;
}

async function run(mode, fn) {
  const db = await open();
  return new Promise((resolve, reject) => {
    const t = db.transaction(STORE, mode);
    const req = fn(t.objectStore(STORE));
    t.oncomplete = () => resolve(req?.result);
    t.onerror = () => reject(t.error);
  });
}

/** Names of the imported songs, sorted. Empty wherever IndexedDB is not available. */
export async function listSongs() {
  try { return ((await run('readonly', (s) => s.getAllKeys())) || []).sort(); } catch { return []; }
}

/** The stored song as a Blob, or null. */
export async function readSong(name) {
  try { return (await run('readonly', (s) => s.get(name)))?.blob || null; } catch { return null; }
}

/**
 * Store a File under its own name. Commas and parentheses would break the
 * soundtrack(...) line the name is written into, so they become '_'.
 * Resolves to the stored name.
 */
export async function importSong(file) {
  const name = file.name.replace(/[,()\r\n]/g, '_');
  await run('readwrite', (s) => s.put({ name, blob: file }));
  return name;
}

export async function deleteSong(name) {
  await run('readwrite', (s) => s.delete(name));
}
