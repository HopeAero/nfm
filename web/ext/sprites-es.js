// Extended's bitmaps where the base port's are wanted instead: the race-end
// sprites (congrad, gameov, continue, the madness logo and its dude) are the
// base game's files, in either language; and in Spanish, every lettered bitmap
// the base port has redrawn (web/ui-sprites-es.js) -- the same files at the
// same sizes (selectcar.gif and select.gif are byte-identical to the base's;
// back/next.gif share its 60x21 layout).
//
// xtGraphics.loadimages reads images.radq entry by entry and loadimage()
// decodes the current one; while it runs, the entry's name is kept and each
// decoded Image is swapped -- before bressed() makes the pressed button from it.

import { spanishSprite } from '../ui-sprites-es.js';
import { loadude } from '../images.js';
import { lang } from '../i18n.js';
import { readZip } from '../vfs.js';
import { Image, ZipInputStream } from './jawt.js';
import { xtGraphics } from './xtGraphics.js';

// Extended entry -> the base's data/images.zip entry drawn in its place
const BASE = { 'congrad.gif': 'congrad.gif', 'gameov.gif': 'gameov.gif', 'continue2.gif': 'continue.gif',
  'madness.gif': 'madness.gif', 'd1.gif': 'd1.gif' };
// Extended writes its HUD labels as text (i18n-ext.js); the base's HUD sprites are not its layout
const SKIP = new Set(['damage.gif', 'power.gif', 'position.gif', 'speed.gif', 'wasted.gif', 'lap.gif']);

/** A jawt Image (ARGB pixels) of a drawable's pixels. */
function fromCanvas(c) {
  const d = c.getContext('2d', { willReadFrequently: true }).getImageData(0, 0, c.width, c.height).data;
  const px = new Int32Array(c.width * c.height);
  for (let i = 0; i < px.length; i++) px[i] = (d[i * 4 + 3] << 24) | (d[i * 4] << 16) | (d[i * 4 + 1] << 8) | d[i * 4 + 2];
  return new Image(c.width, c.height, px);
}

async function decode(bytes) {
  const bmp = await createImageBitmap(new Blob([bytes]));
  const c = new OffscreenCanvas(bmp.width, bmp.height);
  c.getContext('2d').drawImage(bmp, 0, 0);
  return c;
}

/** Before GameSparker.run(): the base's sprites decoded, and loadimages hooked. */
export async function installSprites() {
  const base = new Map();
  try {
    const zip = await readZip('data/images.zip');
    for (const name of new Set(Object.values(BASE))) if (zip.get(name)) base.set(name, await decode(zip.get(name)));
    // the base keys madness.gif's green background out (xtGraphics.loadude), as loadFinishImages does
    if (base.has('madness.gif')) base.set('madness.gif', loadude(base.get('madness.gif')));
  } catch (e) { console.warn('sprites: base images unavailable, keeping Extended\'s', e); }

  let entry = null;
  const loadimages = xtGraphics.prototype.loadimages;
  const loadimage = xtGraphics.prototype.loadimage;
  xtGraphics.prototype.loadimages = function (...a) {
    const next = ZipInputStream.prototype.getNextEntry;
    ZipInputStream.prototype.getNextEntry = function () { const e = next.call(this); entry = e?.getName() ?? null; return e; };
    try { return loadimages.apply(this, a); } finally { ZipInputStream.prototype.getNextEntry = next; entry = null; }
  };
  xtGraphics.prototype.loadimage = function (...a) {
    const img = loadimage.apply(this, a);
    if (!entry || !img || SKIP.has(entry)) return img;
    const name = BASE[entry] || entry;
    const own = base.get(BASE[entry]);
    const src = own || img.source();
    const out = lang === 'es' ? spanishSprite(name, src) : src;
    return out === src && !own ? img : fromCanvas(out);
  };
}
