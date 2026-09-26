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
import { loadsnap as baseLoadsnap, loadude } from '../images.js';
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

/**
 * Extended's own "Loading Stage Sound Track" card (the presenter's screen before a race):
 * its black lettering (rows 66-81) replaced with the pixel above or below it -- the stage
 * silhouette or clear -- and the Spanish drawn in the same Adventure face (race.js loads
 * it before run()). loadopsnap tints the result afterwards, as it did the original.
 */
function spanishLoadingMusic(src) {
  const c = new OffscreenCanvas(src.width, src.height);
  const ctx = c.getContext('2d', { willReadFrequently: true });
  ctx.drawImage(src, 0, 0);
  const img = ctx.getImageData(0, 0, c.width, c.height), p = img.data, w = c.width;
  const ink = (o) => p[o + 3] > 0 && p[o] < 80 && p[o + 1] < 80 && p[o + 2] < 80;
  for (let y = 64; y <= 83; y++) {
    for (let x = 0; x < w; x++) {
      const o = (y * w + x) * 4;
      if (!ink(o)) continue;
      const from = ((y < 73 ? 64 : 83) * w + x) * 4;
      for (let k = 0; k < 4; k++) p[o + k] = p[from + k];
    }
  }
  ctx.putImageData(img, 0, 0);
  let size = 17;
  do { ctx.font = `italic ${size}px Adventure`; } while (ctx.measureText('CARGANDO MÚSICA DE LA PISTA').width > w - 8 && --size > 8);
  ctx.textAlign = 'center';
  ctx.textBaseline = 'middle';
  ctx.fillStyle = '#000';
  ctx.fillText('CARGANDO MÚSICA DE LA PISTA', w / 2, 74);
  return c;
}

/**
 * The presenter's START button (start1.gif, start2.gif: its two blinking states, 82x26):
 * the lettering inside the grey pill (132) refilled with it, and INICIAR drawn in the
 * Adventure face in each state's own ink -- white on start1, light grey outlined dark on
 * start2. The jar makes the pressed copy from start2 and tints both (loadopsnap) after.
 */
function spanishStart(src, second) {
  const c = new OffscreenCanvas(src.width, src.height);
  const ctx = c.getContext('2d', { willReadFrequently: true });
  ctx.drawImage(src, 0, 0);
  const img = ctx.getImageData(0, 0, c.width, c.height), p = img.data, w = c.width;
  for (let y = 5; y <= 20; y++) {
    for (let x = 9; x <= 73; x++) {
      const o = (y * w + x) * 4;
      p[o] = p[o + 1] = p[o + 2] = 132; p[o + 3] = 255;
    }
  }
  ctx.putImageData(img, 0, 0);
  let size = 18;
  do { ctx.font = `italic ${size}px Adventure`; } while (ctx.measureText('INICIAR').width > 62 && --size > 8);
  ctx.textAlign = 'center';
  ctx.textBaseline = 'middle';
  ctx.lineJoin = 'round';
  ctx.lineWidth = 2;
  ctx.strokeStyle = second ? 'rgb(90,90,90)' : 'rgb(110,110,110)';
  ctx.strokeText('INICIAR', w / 2, 13.5);
  ctx.fillStyle = second ? 'rgb(231,231,231)' : '#fff';
  ctx.fillText('INICIAR', w / 2, 13.5);
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
    const out = lang !== 'es' ? src
      : entry === 'loadingmusic.gif' ? spanishLoadingMusic(src)
      : entry === 'start1.gif' || entry === 'start2.gif' ? spanishStart(src, entry === 'start2.gif')
      : spanishSprite(name, src);
    return out === src && !own ? img : fromCanvas(out);
  };
}

/**
 * Extended's race messages (you won / lost / wasted..., the highlight title) through the
 * base port's loadsnap: grey is black lettering with coverage and the 192 background is
 * clear. Extended's own loadsnap paints that background the sky's colour -- a box behind
 * every message wherever the sky is not flat. The bars (dmg, pwr, special) keep
 * Extended's: the base's reads a pixel with g === b as grey, so special.gif's pure red
 * would go clear.
 */
const LETTERED = ['oyourwasted', 'oyoulost', 'oyouwon', 'oyouwastedem', 'ogameh'];
export function installBaseLoadsnap(xt) {
  const own = xt.loadsnap;
  xt.loadsnap = function (image) {
    if (!LETTERED.some((k) => this[k] === image)) return own.call(this, image);
    return fromCanvas(baseLoadsnap(image.source(), this.m.snap));
  };
}
