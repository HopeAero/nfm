// Need for Madness 2 Extended Mode's .radq archives.
//
// A .radq is a ZIP. 64 of the 78 are plain; 14 (models, the track packs, the
// bot files) have seven byte pairs swapped throughout, which the jar's
// GameSparker undoes before its ZipInputStream. The swap is an involution, so
// the same table encodes and decodes. research/extended-mode/radq.py is the
// reference; radq.test.js checks this against every entry's CRC.

import { parseZip, readBytes } from '../vfs.js';

const PAIRS = [[0x4B, 0x55], [0x24, 0x40], [0x35, 0x13], [0x15, 0x2C], [0x3B, 0x48], [0x0B, 0x31], [0x0D, 0x44]];
const TABLE = new Uint8Array(256).map((_, i) => i);
for (const [a, b] of PAIRS) { TABLE[a] = b; TABLE[b] = a; }

/** Undo (or apply) the byte swap. Returns a new array. */
export function unswap(bytes) {
  const out = new Uint8Array(bytes.length);
  for (let i = 0; i < bytes.length; i++) out[i] = TABLE[bytes[i]];
  return out;
}

/** A plain ZIP starts with a local header, "PK\3\4"; a swapped one with "PU\3\4". */
export const isPlainZip = (b) => b[0] === 0x50 && b[1] === 0x4B && b[2] === 0x03 && b[3] === 0x04;

/** Entries of a .radq (name -> bytes), whichever form it is in. */
export async function parseRadq(bytes) {
  return parseZip(isPlainZip(bytes) ? bytes : unswap(bytes));
}

/** Fetch and unpack a .radq under ext/ (e.g. 'ext/data/models.radq'). */
export async function readRadq(path) {
  return parseRadq(await readBytes(path));
}
