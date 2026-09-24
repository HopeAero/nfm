// java.awt.Graphics2D shapes for the Extended port, over either of the base
// port's surfaces: graphics.js (the race, WebGL + 2D overlay) and
// canvas-graphics.js (offscreen images, one Canvas2D).
//
// The generated code passes Java's arguments through: drawImage(img, x, y,
// observer) -- which the base surfaces would take the observer of for a width
// -- with img a jawt Image, fillPolygon(Polygon), and a jawt Font to setFont
// (already the {name, style, size} shape both surfaces understand).
// graphics.js has no rounded rectangles, so they are built from polygons here.

import { CanvasGraphics } from '../canvas-graphics.js';
import { Graphics2D } from '../graphics.js';

/** A rounded rectangle's outline, 6 segments per corner, as Java approximates it closely enough. */
function roundRect(x, y, w, h, aw, ah) {
  const rx = Math.min(aw / 2, w / 2), ry = Math.min(ah / 2, h / 2);
  const xs = [], ys = [];
  const corner = (cx, cy, from) => {
    for (let i = 0; i <= 6; i++) {
      const t = from + (i / 6) * (Math.PI / 2);
      xs.push(Math.round(cx + Math.cos(t) * rx));
      ys.push(Math.round(cy + Math.sin(t) * ry));
    }
  };
  corner(x + w - rx, y + ry, -Math.PI / 2);
  corner(x + w - rx, y + h - ry, 0);
  corner(x + rx, y + h - ry, Math.PI / 2);
  corner(x + rx, y + ry, Math.PI);
  return [xs, ys];
}

function javaShapes(Base) {
  const native = typeof Base.prototype.fillRoundRect === 'function';
  return class extends Base {
    drawImage(img, x, y, a, b) {
      if (!img) return true;
      const src = typeof img.source === 'function' ? img.source() : img;
      if (typeof a === 'number' && typeof b === 'number') super.drawImage(src, x, y, a, b);
      else super.drawImage(src, x, y);
      return true;
    }

    // Java's fillPolygon(Polygon) / drawPolygon(Polygon) overloads
    fillPolygon(xs, ys, n) { if (xs && xs.npoints !== undefined) super.fillPolygon(xs.xpoints, xs.ypoints, xs.npoints); else super.fillPolygon(xs, ys, n); }
    drawPolygon(xs, ys, n) { if (xs && xs.npoints !== undefined) super.drawPolygon(xs.xpoints, xs.ypoints, xs.npoints); else super.drawPolygon(xs, ys, n); }

    fillRoundRect(x, y, w, h, aw, ah) {
      if (native) return super.fillRoundRect(x, y, w, h, aw, ah);
      const [xs, ys] = roundRect(x, y, w, h, aw, ah);
      super.fillPolygon(xs, ys, xs.length);
    }

    drawRoundRect(x, y, w, h, aw, ah) {
      if (native) return super.drawRoundRect(x, y, w, h, aw, ah);
      const [xs, ys] = roundRect(x, y, w, h, aw, ah);
      super.drawPolygon(xs, ys, xs.length);
    }

    dispose() {}
  };
}

/** Canvas2D, for offscreen images (Panel.createImage(w, h)) and the dev page. */
export const JGraphics = javaShapes(CanvasGraphics);
/** The race surface: WebGL geometry, text and images on the overlay. */
export const JGraphics2D = javaShapes(Graphics2D);
