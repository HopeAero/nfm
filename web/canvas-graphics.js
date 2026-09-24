// java.awt.Graphics2D over ONE Canvas2D context, for the menu screens.
//
// The race renderer (graphics.js) splits a frame in two: geometry into a
// single WebGL batch, text and images onto a 2D overlay that always sits ON
// TOP. That is right for the HUD and wrong for the menus, which interleave
// the two -- finish() draws the frozen race frame as an IMAGE, then the
// unlocked car as GEOMETRY over it, then text over that. With no depth buffer
// the only occlusion is submission order (see the banner in graphics.js), so a
// surface that reorders images relative to polygons draws the car under its
// own backdrop.
//
// Here every call lands in the same context in the order it was made, which
// is what the Java's offscreen Graphics did. Canvas2D is fast enough for a
// menu: one or two cars, not a stage.
//
// The API mirrors graphics.js exactly (setColor takes 0-255 ints, setComposite
// a bare alpha, setFont either shape) so the transpiled screens and ContO.d /
// Plane.d run against either surface unchanged.
//
// begin() does NOT clear. The Java draws the menus onto an offscreen image it
// never wipes between frames, and fase -4 relies on that: the logo drawn over
// the last race frame on one tick is still there when fleximage() grabs the
// pixels on a later one.

import { tr } from './i18n.js';

export class CanvasGraphics {
  /**
   * @param {HTMLCanvasElement|OffscreenCanvas} canvas  any backing size; the
   *        context is scaled so callers keep using game-space coordinates.
   */
  constructor(canvas, width = 800, height = 450) {
    this.width = width;
    this.height = height;
    this.canvas = canvas;
    this.ctx = canvas.getContext('2d', { willReadFrequently: true });
    this.ctx.setTransform(canvas.width / width, 0, 0, canvas.height / height, 0, 0);
    this.r = 0; this.g = 0; this.b = 0; this.a = 1;
    this.lineWidth = 1;
    this.font = '12px sans-serif';
    this.ctx.font = this.font;
    // Scene counters ContO.d and Plane.d increment on whatever surface they
    // are handed. Kept so they run here unchanged; nothing reads them.
    this.count = 0;
    this.inputVerts = 0;
    this.objCalls = 0; this.objDrawn = 0; this.faceCalls = 0;
    this.fanPolys = 0; this.concavePolys = 0; this.concaveVerts = 0;
    this.projVerts = 0;
    this._style();
  }

  // --- state ---------------------------------------------------------------

  setColor(r, g, b) {
    this.r = r; this.g = g; this.b = b;
    this._style();
  }

  setComposite(alpha) {
    this.a = alpha;
    this.ctx.globalAlpha = alpha;
  }

  _style() {
    const c = `rgb(${this.r},${this.g},${this.b})`;
    this.ctx.fillStyle = c;
    this.ctx.strokeStyle = c;
  }

  setRenderingHint() {}

  /** Same two shapes as graphics.js: a CSS string, or Java's (name, style, size). */
  setFont(spec, style, size) {
    if (typeof spec !== 'string' || style !== undefined) {
      const f = (typeof spec === 'object' && spec !== null)
        ? spec : { name: spec, style, size };
      const bits = f.style | 0;
      spec = `${bits & 2 ? 'italic ' : ''}${bits & 1 ? 'bold ' : ''}${f.size}px "${f.name}"`;
    }
    this.font = spec;
    this.ctx.font = spec;
  }

  getFontMetrics() {
    const ctx = this.ctx;
    ctx.font = this.font;
    return {
      stringWidth: (s) => ctx.measureText(tr(s)).width,
      getHeight: () => {
        const m = ctx.measureText('Mg');
        return (m.actualBoundingBoxAscent + m.actualBoundingBoxDescent) || 12;
      },
    };
  }

  // --- geometry ------------------------------------------------------------

  _path(xs, ys, n) {
    const ctx = this.ctx;
    ctx.beginPath();
    ctx.moveTo(xs[0], ys[0]);
    for (let i = 1; i < n; i++) ctx.lineTo(xs[i], ys[i]);
    ctx.closePath();
  }

  /** Even-odd, as java.awt fills: the checkpoint glyphs are keyhole polygons. */
  fillPolygon(xs, ys, n) {
    this.inputVerts += n;
    if (n < 3) return;
    this._path(xs, ys, n);
    this.ctx.fill('evenodd');
    // Canvas2D antialiases every fill, and two polygons sharing an edge each
    // cover it only partly: the black under them shows through as a grid of
    // seams across the road (the Java fills unantialiased, Madness.anti = 0,
    // so it has none). Stroking the edge in the fill's own colour closes
    // them; half a game pixel is one backing pixel at the 2x canvas.
    this.ctx.lineWidth = 0.5;
    this.ctx.stroke();
  }

  drawPolygon(xs, ys, n) {
    this.inputVerts += n;
    if (n < 2) return;
    this._path(xs, ys, n);
    this.ctx.lineWidth = this.lineWidth;
    this.ctx.stroke();
  }

  drawLine(x0, y0, x1, y1) {
    const ctx = this.ctx;
    ctx.beginPath();
    // +0.5 centres a 1px stroke on the pixel, as AWT's lines are.
    ctx.moveTo(x0 + 0.5, y0 + 0.5);
    ctx.lineTo(x1 + 0.5, y1 + 0.5);
    ctx.lineWidth = this.lineWidth;
    ctx.stroke();
  }

  fillRect(x, y, w, h) { this.ctx.fillRect(x, y, w, h); }

  drawRect(x, y, w, h) {
    this.ctx.lineWidth = this.lineWidth;
    this.ctx.strokeRect(x + 0.5, y + 0.5, w, h);
  }

  fillOval(x, y, w, h) {
    const ctx = this.ctx;
    ctx.beginPath();
    ctx.ellipse(x + w / 2, y + h / 2, w / 2, h / 2, 0, 0, Math.PI * 2);
    ctx.fill();
  }

  fillRoundRect(x, y, w, h, aw, ah) {
    const ctx = this.ctx;
    ctx.beginPath();
    ctx.roundRect(x, y, w, h, [Math.min(aw, ah) / 2]);
    ctx.fill();
  }

  drawRoundRect(x, y, w, h, aw, ah) {
    const ctx = this.ctx;
    ctx.beginPath();
    ctx.roundRect(x + 0.5, y + 0.5, w, h, [Math.min(aw, ah) / 2]);
    ctx.lineWidth = this.lineWidth;
    ctx.stroke();
  }

  /** Black, like graphics.js: the Java only ever clears to the background. */
  clearRect(x, y, w, h) {
    const ctx = this.ctx;
    ctx.save();
    ctx.globalAlpha = 1;
    ctx.fillStyle = '#000';
    ctx.fillRect(x, y, w, h);
    ctx.restore();
  }

  // --- text / images --------------------------------------------------------

  // Translated here and in stringWidth alike -- see graphics.js.
  drawString(s, x, y) { this.ctx.fillText(tr(s), x, y); }

  drawImage(img, x, y, w, h) {
    if (!img) return;
    if (w === undefined) this.ctx.drawImage(img, x, y);
    else this.ctx.drawImage(img, x, y, w, h);
  }

  // --- frame ---------------------------------------------------------------

  begin() {
    this.a = 1;
    this.ctx.globalAlpha = 1;
  }

  end() {}

  get vertexCount() { return this.count; }
}
