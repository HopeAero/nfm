// Browser-side `mystages/` -- the stage maker's store, carstore.js's twin.
//
// IndexedDB holds `name -> stage .txt` verbatim, so a stage moves between the
// browser and the desktop game by copy-paste. The stages committed under
// mystages/ stay readable; a stored stage of the same name shadows a shipped
// one, which is how "edit a shipped stage" works without writing to the asset
// tree.
//
// Its OWN database, not a second store in carstore's `nfm`: adding a store
// means bumping that database's version, and any page still holding the old
// version open (the launcher, a car editor tab) would block the upgrade.

import { readText } from './vfs.js';

const DB_NAME = 'nfm-stages';
const DB_VERSION = 1;
const STORE = 'mystages';

/** The stages committed under mystages/ (HTTP has no directory listing). */
export const SHIPPED = ['Example Stage - with all the parts used in it'];

let dbPromise = null;

function open() {
  if (dbPromise) return dbPromise;
  dbPromise = new Promise((resolve, reject) => {
    const req = indexedDB.open(DB_NAME, DB_VERSION);
    req.onupgradeneeded = () => {
      const db = req.result;
      if (!db.objectStoreNames.contains(STORE)) db.createObjectStore(STORE, { keyPath: 'name' });
    };
    req.onsuccess = () => resolve(req.result);
    req.onerror = () => reject(req.error);
  });
  return dbPromise;
}

async function tx(mode, fn) {
  const db = await open();
  return new Promise((resolve, reject) => {
    const t = db.transaction(STORE, mode);
    const req = fn(t.objectStore(STORE));
    t.onabort = t.onerror = () => reject(t.error);
    if (req) {
      req.onsuccess = () => resolve(req.result);
      req.onerror = () => reject(req.error);
    } else {
      t.oncomplete = () => resolve(undefined);
    }
  });
}

let backend = {
  keys: () => tx('readonly', (s) => s.getAllKeys()),
  get: (name) => tx('readonly', (s) => s.get(name)),
  put: (rec) => tx('readwrite', (s) => s.put(rec)),
  del: (name) => tx('readwrite', (s) => s.delete(name)),
};

/** Swap the storage (tests use memoryBackend; node has no IndexedDB). */
export function useBackend(b) {
  backend = b;
}

export function memoryBackend(map = new Map()) {
  return {
    keys: async () => [...map.keys()],
    get: async (name) => map.get(name),
    put: async (rec) => { map.set(rec.name, rec); },
    del: async (name) => { map.delete(name); },
  };
}

export async function listStored() {
  const keys = await backend.keys();
  return keys.sort((a, b) => a.localeCompare(b));
}

/** Stored and shipped stages, one entry per name, sorted. */
export async function listAll() {
  const stored = await listStored();
  const names = new Set(stored);
  return [...stored, ...SHIPPED.filter((n) => !names.has(n))].sort((a, b) => a.localeCompare(b));
}

/** A stage's text: the stored copy if there is one, else the shipped file, else null. */
export async function readStage(name) {
  const rec = await backend.get(name);
  if (rec) return rec.text;
  if (SHIPPED.includes(name)) {
    try { return await readText(`mystages/${name}.txt`); } catch { return null; }
  }
  return null;
}

export async function saveStage(name, text) {
  if (!name || /[\\/:*?"<>|]/.test(name)) throw new Error(`not a valid stage name: ${name}`);
  await backend.put({ name, text, saved: Date.now() });
}

export async function deleteStage(name) {
  await backend.del(name);
}
