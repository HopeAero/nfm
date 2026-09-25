// Extended's English-lettered bitmaps in Spanish, with the base port's
// redrawn sprites (web/ui-sprites-es.js): the same files at the same sizes
// (selectcar.gif and select.gif are byte-identical to the base's; back.gif and
// next.gif share its 60x21 layout). xtGraphics.loadimages reads images.radq
// entry by entry and loadimage() decodes the current one; while it runs, the
// entry's name is kept and each decoded Image is swapped for its Spanish
// version -- before bressed() makes the pressed button from it.

import { spanishSprite } from '../ui-sprites-es.js';
import { lang } from '../i18n.js';
import { Image, ZipInputStream } from './jawt.js';
import { xtGraphics } from './xtGraphics.js';

// continue2.gif is Extended's CONTINUE button, drawn like the base's continue.gif
const ALIAS = { 'continue2.gif': 'continue.gif' };
// Extended writes its HUD labels as text (i18n-ext.js); the base's HUD sprites are not its layout
const SKIP = new Set(['damage.gif', 'power.gif', 'position.gif', 'speed.gif', 'wasted.gif', 'lap.gif']);

/** A jawt Image (ARGB pixels) of a drawable's pixels. */
function fromCanvas(c) {
  const d = c.getContext('2d').getImageData(0, 0, c.width, c.height).data;
  const px = new Int32Array(c.width * c.height);
  for (let i = 0; i < px.length; i++) px[i] = (d[i * 4 + 3] << 24) | (d[i * 4] << 16) | (d[i * 4 + 1] << 8) | d[i * 4 + 2];
  return new Image(c.width, c.height, px);
}

export function installSpanishSprites() {
  if (lang !== 'es') return;
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
    const src = img.source();
    const es = spanishSprite(ALIAS[entry] || entry, src);
    return es === src ? img : fromCanvas(es);
  };
}
