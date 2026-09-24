import { test } from 'node:test';
import assert from 'node:assert';
import fs from 'node:fs';
import { parseRadq, isPlainZip, unswap } from './radq.js';

const DATA = new URL('../../ext/data/', import.meta.url);
const read = (p) => new Uint8Array(fs.readFileSync(new URL(p, DATA)));

const CRC = new Uint32Array(256).map((_, n) => {
  let c = n;
  for (let k = 0; k < 8; k++) c = c & 1 ? 0xedb88320 ^ (c >>> 1) : c >>> 1;
  return c >>> 0;
});
const crc32 = (b) => {
  let c = 0xffffffff;
  for (let i = 0; i < b.length; i++) c = CRC[(c ^ b[i]) & 0xff] ^ (c >>> 8);
  return (c ^ 0xffffffff) >>> 0;
};

/** name -> CRC from the central directory, read independently of parseZip. */
function centralCrcs(zip) {
  const dv = new DataView(zip.buffer, zip.byteOffset, zip.byteLength);
  let e = zip.length - 22;
  while (dv.getUint32(e, true) !== 0x06054b50) e--;
  const out = new Map();
  let p = dv.getUint32(e + 16, true);
  for (let i = dv.getUint16(e + 10, true); i > 0; i--) {
    const n = dv.getUint16(p + 28, true);
    out.set(new TextDecoder().decode(zip.subarray(p + 46, p + 46 + n)), dv.getUint32(p + 16, true));
    p += 46 + n + dv.getUint16(p + 30, true) + dv.getUint16(p + 32, true);
  }
  return out;
}

// [file, form, entries]; counts from research/extended-mode/README.md
const FILES = [
  ['models.radq', 'swapped', 129],
  ['images.radq', 'plain', 60],
  ['Files/tracks.radq', 'swapped', 27],
  ['Files/classictracks.radq', 'swapped', 17],
  ['Files/matchtracks.radq', 'swapped', 5],
  ['Files/careertracks.radq', 'swapped', null],
  ...['5', '9', '10', '11', '13', '14', '18', '20', '21'].map((n) => [`Files/Bots/stage${n}.radq`, 'swapped', null]),
];

for (const [file, form, count] of FILES) {
  test(`radq: ${file} (${form}) unpacks with every CRC intact`, async () => {
    const raw = read(file);
    assert.strictEqual(isPlainZip(raw) ? 'plain' : 'swapped', form);
    const entries = await parseRadq(raw);
    if (count !== null) assert.strictEqual(entries.size, count);
    const crcs = centralCrcs(isPlainZip(raw) ? raw : unswap(raw));
    assert.strictEqual(crcs.size, entries.size);
    for (const [name, bytes] of entries) {
      assert.strictEqual(crc32(bytes), crcs.get(name), `${file}: ${name}`);
    }
  });
}

test('radq: the swap is its own inverse', () => {
  const all = new Uint8Array(256).map((_, i) => i);
  assert.deepStrictEqual(unswap(unswap(all)), all);
  assert.notDeepStrictEqual(unswap(all), all);
});
