// The slice of the Java library the transpiled Extended classes call.
//
// Only what J2JS output actually uses, with Java's semantics where they can
// change a result: DataInputStream.readLine's line splitting, Integer.valueOf
// throwing on anything that is not an int (ContO's constructor relies on that
// to stop reading a malformed model), java.util.Random's exact LCG, and
// Color.brighter/darker's integer arithmetic.

import { JavaRandom, RGBtoHSB, HSBtoRGB } from '../java.js';
import { isPlainZip, parseRadq, unswap } from './radq.js';

export const Random = JavaRandom;

export class Color {
  constructor(r, g, b, a = 255) {
    this.r = r | 0; this.g = g | 0; this.b = b | 0; this.a = a | 0;
  }
  getRed() { return this.r; }
  getGreen() { return this.g; }
  getBlue() { return this.b; }
  getAlpha() { return this.a; }
  getRGB() { return ((this.a & 0xff) << 24) | ((this.r & 0xff) << 16) | ((this.g & 0xff) << 8) | (this.b & 0xff); }

  /** java.awt.Color.brighter(), FACTOR 0.7, alpha kept. */
  brighter() {
    let { r, g, b } = this;
    const i = Math.trunc(1.0 / (1.0 - 0.7));
    if (r === 0 && g === 0 && b === 0) return new Color(i, i, i, this.a);
    if (r > 0 && r < i) r = i;
    if (g > 0 && g < i) g = i;
    if (b > 0 && b < i) b = i;
    return new Color(Math.min(Math.trunc(r / 0.7), 255), Math.min(Math.trunc(g / 0.7), 255), Math.min(Math.trunc(b / 0.7), 255), this.a);
  }

  /** java.awt.Color.darker(). */
  darker() {
    return new Color(Math.max(Math.trunc(this.r * 0.7), 0), Math.max(Math.trunc(this.g * 0.7), 0), Math.max(Math.trunc(this.b * 0.7), 0), this.a);
  }

  static getHSBColor(h, s, b) {
    const p = HSBtoRGB(h, s, b);
    return new Color((p >> 16) & 0xff, (p >> 8) & 0xff, p & 0xff);
  }

  /** Java returns the array (allocating one when passed null). */
  static RGBtoHSB(r, g, b, out) {
    return RGBtoHSB(r, g, b, out || new Float32Array(3));
  }
}

export class ByteArrayInputStream {
  constructor(bytes) { this.bytes = bytes; this.pos = 0; }
  close() {}
}

// ---- files -------------------------------------------------------------------
// Java reads its archives synchronously (URL.openStream, ZipInputStream);
// the browser cannot. preload() fetches and inflates them first, then the
// generated code reads them from memory with Java's call shapes.
const FILES = new Map();   // codebase-relative path -> raw bytes
const ZIPS = new Map();    // fingerprint of a plain ZIP's bytes -> [[name, bytes]]
const IMAGES = new Map();  // fingerprint of an encoded image -> Image (decoded by preload)

/** FNV-1a over the bytes as unsigned: a ZIP as the game holds it (signed, unswapped). */
function fingerprint(b) {
  let h = 0x811c9dc5;
  for (let i = 0; i < b.length; i++) h = Math.imul(h ^ (b[i] & 0xff), 0x01000193);
  return `${b.length}:${h >>> 0}`;
}

/** Fetch each path (under ext/, e.g. 'data/Files/tracks.radq') for the code below. */
export async function preload(paths, read) {
  for (const p of paths) {
    if (FILES.has(p)) continue;
    const bytes = await read(p);
    FILES.set(p, bytes);
    if (p.endsWith('.radq')) {
      // keyed by the bytes ZipInputStream will be handed: plain, whichever form is on disk
      const entries = [...await parseRadq(bytes)];
      ZIPS.set(fingerprint(isPlainZip(bytes) ? bytes : unswap(bytes)), entries);
      // Toolkit.createImage(bytes) is synchronous in Java; decode every image now
      if (typeof createImageBitmap === 'function')
        for (const [name, b] of entries) if (/\.(gif|png|jpe?g)$/i.test(name)) IMAGES.set(fingerprint(b), await decodeImage(b));
    }
  }
}

/** java.io.File as the music loader asks it: does a preloaded file exist? */
export class File {
  constructor(path) { this.path = path; }
  exists() { return FILES.has(this.path); }
  getName() { return this.path.slice(this.path.lastIndexOf('/') + 1); }
}

export class URL {
  constructor(base, path) { this.path = path ?? base; }
  openConnection() { const b = this.bytes(); return { getContentLength: () => b.length }; }
  openStream() { return new ByteArrayInputStream(this.bytes()); }
  bytes() {
    const b = FILES.get(this.path);
    if (!b) throw new Error(`java.io.FileNotFoundException: ${this.path} (not preloaded)`);
    return b;
  }
}

// ---- images --------------------------------------------------------------------
// An Image is its ARGB pixels; PixelGrabber/MemoryImageSource copy them the way
// java.awt.image does (offset + scansize), which is all the game's recolouring
// (xtGraphics.loadsnap and friends) needs.
export class Image {
  constructor(width, height, pixels = new Int32Array(width * height), canvas = null) {
    this.width = width; this.height = height; this.pixels = pixels; this.canvas = canvas;
  }
  getWidth() { return this.width; }
  getHeight() { return this.height; }
  /**
   * Something CanvasRenderingContext2D.drawImage takes. A decoded image keeps its
   * canvas; one made from pixels (the game makes a new 870x480 one per frame) is
   * painted onto one shared scratch canvas each time, since a canvas per image
   * exhausted the tab's memory within a race.
   */
  source() {
    if (this.canvas) return this.canvas;
    const w = Math.max(1, this.width), h = Math.max(1, this.height);
    if (!Image.scratch || Image.scratch.width !== w || Image.scratch.height !== h) Image.scratch = new OffscreenCanvas(w, h);
    const data = new ImageData(w, h);
    const p = this.pixels, d = data.data;
    for (let i = 0; i < p.length; i++) { const v = p[i]; d[4 * i] = (v >> 16) & 255; d[4 * i + 1] = (v >> 8) & 255; d[4 * i + 2] = v & 255; d[4 * i + 3] = (v >>> 24) & 255; }
    Image.scratch.getContext('2d').putImageData(data, 0, 0);
    return Image.scratch;
  }
}
Image.scratch = null;

async function decodeImage(bytes) {
  const bmp = await createImageBitmap(new Blob([bytes]));
  const canvas = new OffscreenCanvas(bmp.width, bmp.height);
  const ctx = canvas.getContext('2d', { willReadFrequently: true });
  ctx.drawImage(bmp, 0, 0);
  const d = ctx.getImageData(0, 0, bmp.width, bmp.height).data;
  const px = new Int32Array(bmp.width * bmp.height);
  for (let i = 0; i < px.length; i++) px[i] = (d[4 * i + 3] << 24) | (d[4 * i] << 16) | (d[4 * i + 1] << 8) | d[4 * i + 2];
  return new Image(bmp.width, bmp.height, px, canvas);
}

export const Toolkit = {
  getDefaultToolkit: () => Toolkit,
  /** An image preload() decoded; outside a browser (node tests) an empty one. */
  createImage: (bytes) => IMAGES.get(fingerprint(bytes)) ?? new Image(0, 0),
};

/** Images are decoded before the game starts, so there is nothing to wait for. */
export class MediaTracker {
  addImage() {}
  waitForID() {}
}

/**
 * {name, style, size}: the shape graphics.js / canvas-graphics.js setFont takes.
 * createFont gets the .ttf's resource name (J2JS turns getResourceAsStream into
 * it); the page registers each under that family with FontFace.
 */
export class Font {
  constructor(name, style, size) { this.name = name; this.style = style; this.size = size; }
  static createFont(type, resource) { return new Font(resource.replace(/\.ttf$/i, ''), 0, 1); }
  deriveFont(style, size) { return new Font(this.name, style, size); }
  getSize() { return this.size; }
  getName() { return this.name; }
}

export class PixelGrabber {
  constructor(img, x, y, w, h, pix, off, scan) { Object.assign(this, { img, x, y, w, h, pix, off, scan }); }
  grabPixels() {
    const { img, x, y, w, h, pix, off, scan } = this;
    if (!(img instanceof Image)) return true;   // a replay's placeholder: not game state
    for (let r = 0; r < h; r++) for (let c = 0; c < w; c++) pix[off + r * scan + c] = img.pixels[(y + r) * img.width + x + c];
    return true;
  }
}

export class MemoryImageSource {
  constructor(w, h, pix, off, scan) { Object.assign(this, { w, h, pix, off, scan }); }
}

/** java.awt.Component's createImage(ImageProducer): a snapshot of the source's pixels. */
export class Panel {
  createImage(src, height) {
    if (typeof src === 'number') return Panel.offscreen(src, height);
    if (!(src instanceof MemoryImageSource)) throw new Error('jawt: createImage of ' + src);
    const { w, h, pix, off, scan } = src;
    const out = new Int32Array(w * h);
    for (let r = 0; r < h; r++) for (let c = 0; c < w; c++) out[r * w + c] = pix[off + r * scan + c];
    return new Image(w, h, out);
  }
}

/**
 * createImage(w, h): an offscreen Image whose getGraphics() draws on its canvas.
 * The page sets Panel.graphicsFor (canvas, w, h) -> a Graphics (web/ext/jgraphics.js),
 * so jawt stays free of the renderer.
 */
Panel.graphicsFor = null;
Panel.offscreen = (w, h) => {
  const img = new Image(w, h, new Int32Array(0), new OffscreenCanvas(w, h));
  let g = null;
  img.getGraphics = () => (g ??= Panel.graphicsFor(img.canvas, w, h));
  return img;
};

/** What GameSparker inherits from java.applet.Applet; files resolve under ext/. */
export class Applet extends Panel {
  getCodeBase() { return ''; }
  // ponytail: silent sound; BassoonTracker/WebAudio clips come after a race draws
  getAudioClip() { return { play() {}, loop() {}, stop() {} }; }
  getAppletContext() { return { toString: () => 'browser', showDocument() {} }; }
  /** The page sets `screen` (a Graphics on the visible canvas). */
  repaint() { if (this.screen) this.update(this.screen); }
  requestFocus() {}
  showStatus() {}
  /** The page sets `canvas`; Cursor.HAND_CURSOR (12) over a link, else the arrow. */
  setCursor(c) { if (this.canvas) this.canvas.style.cursor = c?.type === 12 ? 'pointer' : 'default'; }
}

/** java.awt.Polygon: the menus build their buttons point by point. */
export class Polygon {
  constructor() { this.xpoints = []; this.ypoints = []; this.npoints = 0; }
  addPoint(x, y) { this.xpoints.push(x); this.ypoints.push(y); this.npoints++; }
  reset() { this.xpoints = []; this.ypoints = []; this.npoints = 0; }
}

export class Cursor {
  constructor(type) { this.type = type; }
}

export class ZipEntry {
  constructor(name, bytes) { this.name = name; this.data = bytes; }
  getName() { return this.name; }
  getSize() { return this.data.length; }
}

/** Over an in-memory ZIP that preload() inflated; the current entry reads like a stream. */
export class ZipInputStream {
  constructor(input) {
    const entries = ZIPS.get(fingerprint((input.in ?? input).bytes));   // over a ByteArrayInputStream or a DataInputStream on one
    if (!entries) throw new Error('jawt: ZipInputStream over an archive preload() has not seen');
    this.entries = entries; this.next = 0; this.bytes = new Uint8Array(0); this.pos = 0;
  }
  getNextEntry() {
    if (this.next >= this.entries.length) return null;
    const [name, bytes] = this.entries[this.next++];
    this.bytes = bytes; this.pos = 0;
    return new ZipEntry(name, bytes);
  }
  read(buf, off, len) {
    const n = Math.min(len, this.bytes.length - this.pos);
    if (n <= 0) return -1;
    buf.set(this.bytes.subarray(this.pos, this.pos + n), off);
    this.pos += n;
    return n;
  }
  close() {}
}

export class InputStreamReader {
  constructor(input) { this.in = input; }
  close() {}
}

/** DataInputStream.readLine: bytes as Latin-1, lines end at \n, \r or \r\n. */
export class DataInputStream {
  constructor(input) { this.in = input; }
  readLine() {
    const { bytes } = this.in;
    let p = this.in.pos;
    if (p >= bytes.length) return null;
    let s = '';
    while (p < bytes.length) {
      const c = bytes[p++];
      if (c === 10) break;
      if (c === 13) { if (bytes[p] === 10) p++; break; }
      s += String.fromCharCode(c);
    }
    this.in.pos = p;
    return s;
  }
  readFully(buf) {
    const { bytes } = this.in;
    if (bytes.length - this.in.pos < buf.length) throw new Error('java.io.EOFException');
    buf.set(bytes.subarray(this.in.pos, this.in.pos + buf.length));   // Int8Array.set wraps like (byte)
    this.in.pos += buf.length;
  }
  close() {}
}

/** Over an InputStreamReader: lines as DataInputStream splits them (the files are ASCII). */
export class BufferedReader {
  constructor(reader) { this.in = reader.in; }
  close() {}
}
BufferedReader.prototype.readLine = DataInputStream.prototype.readLine;

/** String.charAt as a char code, throwing out of range like Java. */
export function charAt(s, i) {
  if (i < 0 || i >= s.length) throw new Error(`StringIndexOutOfBoundsException: index ${i}, length ${s.length}`);
  return s.charCodeAt(i);
}

const INT = /^[+-]?\d+$/;

export const Integer = {
  /** Integer.valueOf / parseInt: throws like NumberFormatException. */
  valueOf(s) {
    s = String(s);
    if (!INT.test(s)) throw new Error(`NumberFormatException: For input string: "${s}"`);
    const v = Number(s);
    if (v > 2147483647 || v < -2147483648) throw new Error(`NumberFormatException: For input string: "${s}"`);
    return v;
  },
  parseInt(s) { return Integer.valueOf(s); },
  MAX_VALUE: 2147483647,
  MIN_VALUE: -2147483648,
};

export class StringBuilder {
  constructor(s = '') { this.s = String(s); }
  append(x) { this.s += typeof x === 'number' ? String(x) : x; return this; }
  toString() { return this.s; }
}

export const System = {
  out: { println: (...a) => console.log(...a) },
  gc() {},
  // the game's clock; tests set `now` (ns) to replay a captured call, the page sets `live`
  now: 0,
  live: false,
  nanoTime() { return System.live ? Math.trunc(performance.now() * 1e6) : System.now; },
  currentTimeMillis() { return Math.trunc(System.nanoTime() / 1e6); },
  // as on the machine the jar was captured on (a non-Windows os.name sets xtGraphics.macn)
  getProperty(k) { return { 'java.version': '1.8.0', 'os.name': 'Windows 10' }[k] ?? null; },
};

/** java.util.Date as the game loop uses it: the clock, in ms. */
export class Date {
  constructor() { this.t = System.currentTimeMillis(); }
  getTime() { return this.t; }
}

/** Java's String.valueOf / concatenation of a float ('F') or double ('D'). */
export function jstr(x, t = 'D') {
  if (typeof x !== 'number') return String(x);
  if (Number.isNaN(x)) return 'NaN';
  if (!Number.isFinite(x)) return x > 0 ? 'Infinity' : '-Infinity';
  let s;
  if (t === 'F') {
    // shortest decimal that rounds back to the same float
    for (let p = 1; p <= 9; p++) { s = x.toPrecision(p); if (Math.fround(Number(s)) === x) break; }
    s = String(Number(s));
  } else s = String(x);
  const ax = Math.abs(x);
  if (ax >= 1e7 || (ax < 1e-3 && ax !== 0)) {
    let [m, e] = Number(s).toExponential().split('e');
    if (!m.includes('.')) m += '.0';
    return m + 'E' + Number(e);
  }
  return s.includes('.') || s.includes('e') ? s : s + '.0';
}

// ---- threads -------------------------------------------------------------------
// J2JS emits a run() that calls Thread.sleep as a generator yielding each sleep;
// start() steps it on the event loop, one sleep per setTimeout. That is a
// frame of GameSparker.run, whose loop ends in its frame wait.
export class Thread {
  constructor(runnable) { this.runnable = runnable; this.alive = false; }
  start() {
    const it = this.runnable.run();
    this.alive = true;
    if (!it || typeof it.next !== 'function') { this.alive = false; return; }   // a run() that never sleeps ran already
    const step = () => {
      if (!this.alive) return;
      const r = it.next();
      if (r.done) { this.alive = false; return; }
      setTimeout(step, Math.max(0, Number(r.value) || 0));
    };
    setTimeout(step, 0);
  }
  stop() { this.alive = false; }
  static yield() {}
}

// ---- placeholders ------------------------------------------------------------
// Names the generated xtGraphics imports but the ported code paths do not use
// yet. Constructing or calling one throws, so a path that starts to need it
// says so instead of silently doing nothing.
function unported(name) {
  return class { constructor() { throw new Error(`jawt: ${name} is not ported`); } static [Symbol.hasInstance]() { return false; } };
}
export const RenderingHints = unported('RenderingHints');
export const FileOutputStream = unported('FileOutputStream');
export const FileInputStream = unported('FileInputStream');
