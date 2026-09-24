// The slice of the Java library the transpiled Extended classes call.
//
// Only what J2JS output actually uses, with Java's semantics where they can
// change a result: DataInputStream.readLine's line splitting, Integer.valueOf
// throwing on anything that is not an int (ContO's constructor relies on that
// to stop reading a malformed model), java.util.Random's exact LCG, and
// Color.brighter/darker's integer arithmetic.

import { JavaRandom, RGBtoHSB, HSBtoRGB } from '../java.js';

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
  close() {}
}

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
  // the game's clock; tests set `now` (ns) to replay a captured call
  now: 0,
  nanoTime() { return System.now; },
  currentTimeMillis() { return Math.trunc(System.now / 1e6); },
};

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

// ---- placeholders ------------------------------------------------------------
// Names the generated xtGraphics imports but the ported code paths do not use
// yet. Constructing or calling one throws, so a path that starts to need it
// says so instead of silently doing nothing.
function unported(name) {
  return class { constructor() { throw new Error(`jawt: ${name} is not ported`); } static [Symbol.hasInstance]() { return false; } };
}
export const Cursor = unported('Cursor');
export const File = unported('File');
export const Font = unported('Font');
export const MediaTracker = unported('MediaTracker');
export const Polygon = unported('Polygon');
export const RenderingHints = unported('RenderingHints');
export const Thread = unported('Thread');
export const Toolkit = unported('Toolkit');
export const URL = unported('URL');
export const ZipInputStream = unported('ZipInputStream');
export const FileInputStream = unported('FileInputStream');
