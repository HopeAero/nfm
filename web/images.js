// HUD image assets: decode from images.zip and apply the game's recolour.
//
// xtGraphics keeps two copies of each HUD graphic: an `o`-prefixed original
// decoded straight from the zip (`odmg`, `orank[]`, ...) and a processed one
// used for drawing (`dmg`, `rank[]`, ...). The processing step is
// `loadsnap()`, which does two different things per pixel:
//
//   - COLOURED pixels (r != g && g != b) are tinted by Medium.snap[], the
//     same per-stage palette shift that biases the sky and track colours, and
//     forced opaque.
//   - GREY pixels (r == g || g == b) become BLACK with an alpha taken from
//     how far they sit below a reference white. This is how the original gets
//     antialiased edges out of a GIF with no alpha channel: the grey ramp
//     around each glyph is reinterpreted as coverage.
//
// The reference white is the BOTTOM-RIGHT pixel (`array[w*h-1]`), which in
// every one of these assets is the background. Read it from the source data,
// not from a constant -- a couple of the gifs are off-white.
//
// Java did this with PixelGrabber + BufferedImage.setRGB. Here it is
// getImageData/putImageData on an OffscreenCanvas, which produces a canvas
// that ctx.drawImage accepts directly.

import { RGBtoHSB, HSBtoRGB, fr } from './java.js';
import { lang } from './i18n.js';
import { spanishSprite } from './ui-sprites-es.js';

/** Zip entry name -> field on XtGraphics. Single images. */
const SINGLE = {
  'damage.gif': 'odmg',
  'power.gif': 'opwr',
  'position.gif': 'opos',
  'speed.gif': 'osped',
  'wasted.gif': 'owas',
  'lap.gif': 'olap',
  'wgame.gif': 'owgame',
  'gameh.gif': 'ogameh',        // the highlight's header (levelhigh)
  // End-of-race overlays. `gamefinished` and `disco` are multion-only in the
  // Java's snap() and are not loaded here for the same reason.
  'youwon.gif': 'oyouwon',
  'youlost.gif': 'oyoulost',
  'yourwasted.gif': 'oyourwasted',
  'youwastedem.gif': 'oyouwastedem',
};

/**
 * Zip entry name -> [field, index], loaded RAW.
 *
 * The countdown's NFM guy is the one HUD graphic the Java loads with plain
 * loadimage and never passes through snap(), so it keeps its own colours and
 * its PNG alpha. Running loadsnap over it would key its greys to transparent.
 */
const RAW_INDEXED = {
  'd1.png': ['dude', 0], 'd2.png': ['dude', 1], 'd3.png': ['dude', 2],
};

/** Zip entry name -> [field, index]. Indexed images. */
const INDEXED = {
  '1.gif': ['orank', 0], '2.gif': ['orank', 1], '3.gif': ['orank', 2],
  '4.gif': ['orank', 3], '5.gif': ['orank', 4], '6.gif': ['orank', 5],
  '7.gif': ['orank', 6], '8.gif': ['orank', 7],
  '0c.gif': ['ocntdn', 0], '1c.gif': ['ocntdn', 1],
  '2c.gif': ['ocntdn', 2], '3c.gif': ['ocntdn', 3],
};

/** MIME type for createImageBitmap's Blob. Only two formats appear here. */
function mimeOf(name) {
  if (name.endsWith('.png')) return 'image/png';
  if (name.endsWith('.jpg')) return 'image/jpeg';
  return 'image/gif';
}

/** Decode one zip entry to an ImageBitmap. */
async function decode(bytes, name) {
  const blob = new Blob([bytes], { type: mimeOf(name) });
  return createImageBitmap(blob);
}

/** Draw a decoded bitmap into a canvas and hand back its pixels. */
function pixelsOf(bitmap) {
  const w = bitmap.width, h = bitmap.height;
  const canvas = new OffscreenCanvas(w, h);
  const ctx = canvas.getContext('2d', { willReadFrequently: true });
  ctx.drawImage(bitmap, 0, 0);
  return { canvas, ctx, data: ctx.getImageData(0, 0, w, h), w, h };
}

/**
 * xtGraphics.loadsnap() — recolour + alpha-key one HUD image.
 *
 * @param {ImageBitmap} bitmap decoded original
 * @param {Int32Array}  snap   Medium.snap, three percentages (may be all 0)
 * @returns {OffscreenCanvas}  drawable, with alpha
 */
export function loadsnap(bitmap, snap) {
  const { canvas, ctx, data, w, h } = pixelsOf(bitmap);
  const px = data.data;

  // Reference background: bottom-right pixel, as the Java reads
  // array[width * height - 1] on every iteration of the loop.
  //
  // These assets are NOT uniform. 5.gif and position.gif have an opaque
  // 192-grey background; 8.gif (and the other later re-draws) use GIF index
  // transparency instead. Java didn't notice the difference: PixelGrabber
  // hands back the palette RGB even for the transparent index, so its
  // reference was 192 either way. Canvas zeroes the colour under transparent
  // pixels, so reading the corner directly gives 0 there -- which makes the
  // coverage term (0 - r)/0 collapse and erases every grey pixel in the
  // image. That is what dropped the "TH" off the rank badge while leaving the
  // blue numeral, which takes the coloured branch.
  //
  // So: when the corner is transparent, fall back to the 192 that the rest of
  // the family uses, and honour the source alpha directly.
  const cornerOpaque = px[(w * h - 1) * 4 + 3] !== 0;
  const refR = cornerOpaque ? px[(w * h - 1) * 4] : 192;

  for (let i = 0; i < w * h; i++) {
    const o = i * 4;
    const r = px[o], g = px[o + 1], b = px[o + 2];
    if (px[o + 3] === 0) {
      continue;                       // already transparent; leave it be
    }
    if (r !== g && g !== b) {
      // Coloured: tint and force opaque. Truncation matches the Java cast.
      px[o]     = clamp255(r + r * (snap[0] / 100.0));
      px[o + 1] = clamp255(g + g * (snap[1] / 100.0));
      px[o + 2] = clamp255(b + b * (snap[2] / 100.0));
      px[o + 3] = 255;
    } else {
      // Grey: black, with coverage from the distance below the reference.
      const a = clamp255(((refR - r) / refR) * 255.0);
      px[o] = 0; px[o + 1] = 0; px[o + 2] = 0; px[o + 3] = a;
    }
  }
  ctx.putImageData(data, 0, 0);
  return canvas;
}

/**
 * The port's alternative to the Java's dark-sky HUD boxes (xtGraphics.drawhi
 * and stat() fill a rectangle in the sky's hue at HSB brightness 0.6 behind
 * every HUD graphic when Medium.darksky): a soft rim of that hue baked around
 * the glyphs instead, so the black lettering still reads against a dark sky
 * without a slab behind it. Same size as the input, so no draw site moves.
 */
export function halo(img, [r, g, b]) {
  const w = img.width, h = img.height;
  const mask = new OffscreenCanvas(w, h);
  const m = mask.getContext('2d');
  m.drawImage(img, 0, 0);
  m.globalCompositeOperation = 'source-in';
  m.fillStyle = `rgb(${r},${g},${b})`;
  m.fillRect(0, 0, w, h);
  const out = new OffscreenCanvas(w, h);
  const o = out.getContext('2d');
  // ponytail: a ring of 12 offset stamps, radius 1.5 game px -- a dilation
  // cheap enough to run once per race; a real distance field if it ever shows.
  for (let k = 0; k < 12; ++k) {
    const t = (k / 12) * Math.PI * 2;
    o.drawImage(mask, Math.cos(t) * 1.5, Math.sin(t) * 1.5);
  }
  o.drawImage(img, 0, 0);
  return out;
}

// ---- contrast-aware HUD ink ("auto", the port's default) ----------------------
//
// WCAG 2 relative luminance and contrast ratio: (L1 + 0.05) / (L2 + 0.05),
// 1:1 invisible to 21:1 black on white. The HUD sprites' black lettering and
// their coloured parts (the blue numerals, the gold bar frames) are each moved
// along HSB brightness, away from the sky, until they reach MIN_CONTRAST --
// keeping their hue -- so a dark sky gets light lettering instead of a box.
const MIN_CONTRAST = 4.5;
const lin = (c) => { c /= 255; return c <= 0.04045 ? c / 12.92 : ((c + 0.055) / 1.055) ** 2.4; };
export const luminance = ([r, g, b]) => 0.2126 * lin(r) + 0.7152 * lin(g) + 0.0722 * lin(b);
export function contrast(a, b) {
  const x = luminance(a), y = luminance(b);
  return (Math.max(x, y) + 0.05) / (Math.min(x, y) + 0.05);
}

/** `c`, or the nearest brightness of its hue that reads against `bg`. */
export function readable(c, bg, min = MIN_CONTRAST) {
  if (contrast(c, bg) >= min) return c;
  const hsb = new Float32Array(3);
  RGBtoHSB(c[0], c[1], c[2], hsb);
  // Which way has room: lighter on a dark background, darker on a light one.
  const up = contrast([255, 255, 255], bg) >= contrast([0, 0, 0], bg);
  for (let k = 1; k <= 20; ++k) {
    const v = up ? hsb[2] + (1 - hsb[2]) * k / 20 : hsb[2] * (1 - k / 20);
    // Lightening also desaturates a little, the way a hue gets towards white.
    const s = up ? hsb[1] * (1 - 0.5 * k / 20) : hsb[1];
    const x = HSBtoRGB(hsb[0], s, v);
    const rgb = [(x >> 16) & 0xff, (x >> 8) & 0xff, x & 0xff];
    if (contrast(rgb, bg) >= min) return rgb;
  }
  return up ? [255, 255, 255] : [0, 0, 0];
}

/** Recolour every visible pixel of a loadsnap'd sprite to read against `bg`; alpha is kept. */
export function adaptInk(img, bg) {
  const { canvas, ctx, data } = pixelsOf(img);
  const px = data.data;
  const memo = new Map();
  for (let o = 0; o < px.length; o += 4) {
    if (px[o + 3] === 0) continue;
    const key = (px[o] << 16) | (px[o + 1] << 8) | px[o + 2];
    let c = memo.get(key);
    if (!c) memo.set(key, (c = readable([px[o], px[o + 1], px[o + 2]], bg)));
    px[o] = c[0]; px[o + 1] = c[1]; px[o + 2] = c[2];
  }
  ctx.putImageData(data, 0, 0);
  return canvas;
}

/** The rim colour: the sky's hue at HSB brightness 0.75 (the Java's box used 0.6). */
export function haloColor(csky) {
  const hsb = new Float32Array(3);
  RGBtoHSB(csky[0], csky[1], csky[2], hsb);
  const c = HSBtoRGB(hsb[0], hsb[1], 0.75);
  return [(c >> 16) & 0xff, (c >> 8) & 0xff, c & 0xff];
}

function clamp255(v) {
  const n = Math.trunc(v);
  return n > 255 ? 255 : (n < 0 ? 0 : n);
}

/**
 * Decode every HUD asset from images.zip and install it on `xt`, both the
 * `o`-prefixed originals and the recoloured drawables.
 *
 * Missing entries are skipped rather than thrown on: the null guards at the
 * draw sites stay meaningful, so an incomplete zip degrades to the
 * vector-only HUD instead of taking the whole race down.
 */
export async function loadHudImages(xt, zip, medium, { style = 'boxes' } = {}) {
  const snap = medium.snap;
  const jobs = [];
  // On a dark sky the Java draws boxes behind the HUD (style 'boxes'). The
  // port's styles instead bake the fix into the sprites: 'auto' recolours
  // them to contrast with the sky, 'outline' rims them in its hue.
  const sky = [medium.csky[0], medium.csky[1], medium.csky[2]];
  const loadsnapHud = (art) => {
    const img = loadsnap(art, snap);
    // Only where the Java itself saw a problem (darksky): a bright stock stage
    // keeps its original colours even where they fall short of 4.5:1.
    if (style === 'auto' && medium.darksky) return adaptInk(img, sky);
    if (style === 'outline' && medium.darksky) return halo(img, haloColor(sky));
    return img;
  };

  for (const [entry, field] of Object.entries(SINGLE)) {
    const bytes = zip.get(entry);
    if (!bytes) continue;
    jobs.push(decode(bytes, entry).then((bm) => {
      const art = lang === 'es' ? spanishSprite(entry, bm) : bm;
      xt[field] = art;
      xt[field.slice(1)] = loadsnapHud(art);       // odmg -> dmg
    }));
  }

  for (const [entry, [field, idx]] of Object.entries(INDEXED)) {
    const bytes = zip.get(entry);
    if (!bytes) continue;
    jobs.push(decode(bytes, entry).then((bm) => {
      if (!xt[field]) xt[field] = [];
      const art = lang === 'es' ? spanishSprite(entry, bm) : bm;
      xt[field][idx] = art;
      const target = field.slice(1);             // orank -> rank
      if (!xt[target]) xt[target] = [];
      xt[target][idx] = loadsnapHud(art);
    }));
  }

  for (const [entry, [field, idx]] of Object.entries(RAW_INDEXED)) {
    const bytes = zip.get(entry);
    if (!bytes) continue;
    jobs.push(decode(bytes, entry).then((bm) => {
      if (!xt[field]) xt[field] = [];
      xt[field][idx] = bm;
    }));
  }

  await Promise.all(jobs);
  return jobs.length;
}

/**
 * xtGraphics.bressed(): every pixel that is not the background (the
 * bottom-right pixel) becomes solid (247, 255, 165) -- the highlighted state
 * of a button. Compared as whole RGBA words, as the Java compares packed ARGB.
 */
export function bressed(bitmap) {
  const { canvas, ctx, data, w, h } = pixelsOf(bitmap);
  const u = new Uint32Array(data.data.buffer);
  const bg = u[w * h - 1];
  // Little-endian RGBA bytes: 0xAABBGGRR.
  const lit = (0xff << 24 | 165 << 16 | 255 << 8 | 247) >>> 0;
  for (let i = 0; i < w * h; i++) if (u[i] !== bg) u[i] = lit;
  ctx.putImageData(data, 0, 0);
  return canvas;
}

/**
 * xtGraphics.loadude(): green-dominant pixels become black with alpha
 * 255 - (g - (r + b) / 2) * 1.5 -- a green-screen key. The rest keep their
 * colour. Integer division and a float multiply, as in the Java.
 */
export function loadude(bitmap) {
  const { canvas, ctx, data, w, h } = pixelsOf(bitmap);
  const px = data.data;
  for (let i = 0; i < w * h; i++) {
    const o = i * 4;
    const r = px[o], g = px[o + 1], b = px[o + 2];
    if (g > r + 5 && g > b + 5) {
      const a = clamp255(Math.fround(255.0 - Math.fround((g - ((r + b) >> 1)) * 1.5)));
      px[o] = 0; px[o + 1] = 0; px[o + 2] = 0; px[o + 3] = a;
    }
  }
  ctx.putImageData(data, 0, 0);
  return canvas;
}

/**
 * The finish screen's images (xtGraphics.finish / GameSparker fase -4).
 * All plain loadimage except madness.gif (loadude) and the pressed copy of
 * continue.gif (bressed). Missing entries are skipped; finish() null-checks.
 */
export async function loadFinishImages(xt, zip) {
  const one = async (entry) => {
    const bytes = zip.get(entry);
    return bytes ? decode(bytes, entry) : null;
  };
  const [congrd, gameov, contin, mdness] = await Promise.all(
    ['congrad.gif', 'gameov.gif', 'continue.gif', 'madness.gif'].map(one));
  xt.congrd = lang === 'es' ? spanishSprite('congrad.gif', congrd) : congrd;
  xt.gameov = lang === 'es' ? spanishSprite('gameov.gif', gameov) : gameov;
  if (contin) {
    const art = lang === 'es' ? spanishSprite('continue.gif', contin) : contin;
    xt.contin = [art, bressed(art)];
  }
  if (mdness) xt.mdness = loadude(mdness);
}

/**
 * Tint every pixel to one hue/saturation, keeping its brightness -- the core
 * of xtGraphics.loadBimage (n = 1: every pixel) and smokeypix (all but the
 * background, pix[0]). Returns the pixels as packed 0xAARRGGBB words, which
 * is what the menu code indexes; alpha is kept so a transparent background
 * still compares unequal to opaque black.
 */
function tintPixels(bitmap, hue, sat, skipBg) {
  const { data, w, h } = pixelsOf(bitmap);
  const px = data.data;
  const out = new Int32Array(w * h);
  const hsb = new Float32Array(3);
  const bg = [px[0], px[1], px[2], px[3]];
  for (let i = 0; i < w * h; i++) {
    const o = i * 4;
    const r = px[o], g = px[o + 1], b = px[o + 2], a = px[o + 3];
    if (skipBg && r === bg[0] && g === bg[1] && b === bg[2] && a === bg[3]) {
      out[i] = a << 24 | r << 16 | g << 8 | b;
      continue;
    }
    RGBtoHSB(r, g, b, hsb);
    out[i] = 0xff << 24 | HSBtoRGB(hue, sat, hsb[2]);
  }
  return { pix: out, w, h };
}

/** Packed 0xAARRGGBB words -> a drawable canvas. */
export function canvasOf(pix, w, h) {
  const c = new OffscreenCanvas(w, h);
  const ctx = c.getContext('2d');
  const img = new ImageData(w, h);
  const d = img.data;
  for (let i = 0; i < w * h; i++) {
    const v = pix[i];
    d[i * 4] = v >> 16 & 255; d[i * 4 + 1] = v >> 8 & 255; d[i * 4 + 2] = v & 255;
    d[i * 4 + 3] = 255;
  }
  ctx.putImageData(img, 0, 0);
  return c;
}

/**
 * The car-select screen's images (xtGraphics.loadimages, cars.gif et al.).
 * `carsbgpix` keeps cars.gif's tinted pixels for carsbginflex(), which the
 * Java reads back out of the Image with a PixelGrabber.
 */
export async function loadCarSelectImages(xt, zip) {
  const one = async (entry) => {
    const bytes = zip.get(entry);
    return bytes ? decode(bytes, entry) : null;
  };
  const [cars, smokey, selectcar, back, next, statb, statbo] = await Promise.all(
    ['cars.gif', 'smokey.gif', 'selectcar.gif', 'back.gif', 'next.gif', 'statb.gif', 'statbo.gif'].map(one));
  if (cars) {
    const { pix, w, h } = tintPixels(cars, fr(0.12), fr(0.45), false);
    xt.carsbgpix = pix;
    xt.carsbg = canvasOf(pix, w, h);
  }
  if (smokey) xt.smokey = tintPixels(smokey, fr(0.11), fr(0.45), true).pix;
  xt.selectcar = lang === 'es' ? spanishSprite('selectcar.gif', selectcar) : selectcar;
  if (back) {
    const art = lang === 'es' ? spanishSprite('back.gif', back) : back;
    xt.back = [art, bressed(art)];
  }
  if (next) {
    const art = lang === 'es' ? spanishSprite('next.gif', next) : next;
    xt.next = [art, bressed(art)];
  }
  xt.statb = statb;
  xt.statbo = statbo;
  // The lock gate: a locked career car (carselect) and stage (cantgo).
  xt.pgate = await one('pgate.gif');
}

/**
 * The stage-select screen's images: track.jpg tinted as loadBimage(n = 3)
 * does (hue 0.13, every pixel), its dodgen() copy -- channel * 4 + 90, the
 * flash trackbg() cuts to at random -- plus br.png and select.gif.
 */
export async function loadStageSelectImages(xt, zip) {
  const one = async (entry) => {
    const bytes = zip.get(entry);
    return bytes ? decode(bytes, entry) : null;
  };
  const [track, br, select] = await Promise.all(['track.jpg', 'br.png', 'select.gif'].map(one));
  if (track) {
    const { pix, w, h } = tintPixels(track, fr(0.13), fr(0.45), false);
    const lit = new Int32Array(w * h);
    const c = (v) => (v > 255 ? 255 : v);
    for (let i = 0; i < w * h; i++) {
      const v = pix[i];
      lit[i] = c((v >> 16 & 255) * 4 + 90) << 16 | c((v >> 8 & 255) * 4 + 90) << 8 | c((v & 255) * 4 + 90);
    }
    xt.trackbgImg = [canvasOf(pix, w, h), canvasOf(lit, w, h)];
  }
  xt.br = br;
  xt.select = lang === 'es' ? spanishSprite('select.gif', select) : select;
}
