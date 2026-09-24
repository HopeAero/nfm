// java.awt.Graphics2D for the Extended port: canvas-graphics.js with Java's
// argument shapes where the generated code passes them through.
//
// drawImage(img, x, y, observer) is Java's 4-argument form -- canvas-graphics
// would take the observer for a width -- and img is a jawt Image, drawn from
// its canvas. setFont gets a jawt Font, which is already the {name, style,
// size} shape canvas-graphics understands.

import { CanvasGraphics } from '../canvas-graphics.js';

export class JGraphics extends CanvasGraphics {
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

  dispose() {}
}
