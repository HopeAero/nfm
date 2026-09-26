// Archives needed before Extended can show its menus. Bot scripts are selected
// by stage and loaded separately; most races never read any of them.
import { detectFpath, readBytes } from '../vfs.js';
import { preload } from './jawt.js';

export const CORE_ARCHIVES = [
  'data/models.radq', 'data/images.radq',
  'data/Files/tracks.radq', 'data/Files/careertracks.radq',
  'data/Files/classictracks.radq', 'data/Files/matchtracks.radq',
];
const BOT_STAGES = new Set([5, 9, 10, 11, 13, 14, 18, 20, 21]);
const readArchive = (path) => readBytes(`ext/${path}`);
let corePromise = null;

export function botArchiveFor(career, stage) {
  return career && BOT_STAGES.has(Number(stage)) ? `data/Files/Bots/stage${stage}.radq` : null;
}

/** Safe to start in the launcher: a later race awaits the same promise. */
export function preloadCoreArchives(base) {
  if (!corePromise) {
    corePromise = (async () => {
      if (!base) await detectFpath();
      globalThis.performance?.mark?.('nfm-ext-archives-start');
      await preload(CORE_ARCHIVES, readArchive);
      globalThis.performance?.mark?.('nfm-ext-archives-ready');
    })().catch((error) => { corePromise = null; throw error; });
  }
  return corePromise;
}

export function preloadRaceBot(career, stage) {
  const path = botArchiveFor(career, stage);
  return path ? preload([path], readArchive) : Promise.resolve();
}
