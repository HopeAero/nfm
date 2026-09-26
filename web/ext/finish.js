// The race-end smear as the base port draws it (web/XtGraphics.js fleximage):
// the frozen race frame bleeding colour along each row with a warm tint
// (r/21, g/22, b/24), seven passes, then held. Extended's own fleximage mixes
// every channel towards grey (/22 each) and runs twelve passes, which leaves
// the frame a near-black blur; its race-end screens (finish(): unlocks,
// rewards, CONTINUE) are drawn over whichever this is, unchanged.

import { Image } from './jawt.js';

const W = 870, H = 480, PASSES = 7;

export function installFleximage(xt) {
  let pix = null, out = null, img = null;
  xt.fleximage = function (image, i) {
    if (i === 0) {
      image.beforeRead?.();          // the race shell brings its frozen frame up to date
      const c = new OffscreenCanvas(W, H);
      const cx = c.getContext('2d', { willReadFrequently: true });
      cx.drawImage(image.source(), 0, 0, W, H);
      const d = cx.getImageData(0, 0, W, H).data;
      pix = new Int32Array(W * H);
      for (let k = 0; k < pix.length; k++) pix[k] = d[k * 4] << 16 | d[k * 4 + 1] << 8 | d[k * 4 + 2];
      out = new ImageData(W, H);
      img = new OffscreenCanvas(W, H);
    }
    if (!pix) return;
    if (i <= PASSES) {
      let col = 0, red = 0, green = 0, blue = 0;
      // ponytail: Math.random, not the draw bank -- a picture, nothing reads it back
      let n4 = Math.trunc(Math.random() * 128.0), n5 = Math.trunc(5.0 + Math.random() * 15.0);
      const wgt = 0.38, den = 1.0 + wgt * i, od = out.data;
      for (let k = 0; k < pix.length; k++) {
        const v = pix[k], cr = v >> 16 & 255, cg = v >> 8 & 255, cb = v & 255;
        if (col === 0) { red = cr; green = cg; blue = cb; } else {
          red = Math.trunc((cr + red * wgt * i) / den);
          green = Math.trunc((cg + green * wgt * i) / den);
          blue = Math.trunc((cb + blue * wgt * i) / den);
        }
        if (++col === W) col = 0;
        const r = Math.trunc((red * 17 + green + blue + n4) / 21.0);
        const g = Math.trunc((green * 17 + red + blue + n4) / 22.0);
        const b = Math.trunc((blue * 17 + red + green + n4) / 24.0);
        if (--n5 === 0) { n4 = Math.trunc(Math.random() * 128.0); n5 = Math.trunc(5.0 + Math.random() * 15.0); }
        pix[k] = r << 16 | g << 8 | b;
        od[k * 4] = r; od[k * 4 + 1] = g; od[k * 4 + 2] = b; od[k * 4 + 3] = 255;
      }
      img.getContext('2d').putImageData(out, 0, 0);
    }
    // finish() draws xt.fleximg as its backdrop
    this.fleximg = img;
    this.rd.drawImage(img, 0, 0);
  };
}

/**
 * The presenter over the start countdown (xtGraphics.blendude, starcnt 36): the dude
 * blended a quarter over the race frame behind him, a ghost on the stage. The jar reads
 * that frame from offImage, a copy of the WebGL canvas -- presented and cleared by then,
 * so he blended with black; `frame()` is race.js's copy of the last drawn frame instead.
 * And Extended places him at x = 317 or 431, both over the countdown digit (x 398-470);
 * the base game kept him clear of it (250 / 428 in its 800), so here 268 / 500.
 */
export function installBlendude(xt, frame) {
  xt.blendude = function () {
    this.dudo = Math.random() > Math.random() ? 268 : 500;
    const bg = frame();
    if (!bg) { for (let j = 0; j < 3; j++) this.dudeb[j] = this.dude[j]; return; }
    const b = bg.getContext('2d', { willReadFrequently: true }).getImageData(this.dudo, 0, 122, 160).data;
    for (let j = 0; j < 3; j++) {
      const c = new OffscreenCanvas(122, 160);
      const cx = c.getContext('2d', { willReadFrequently: true });
      cx.drawImage(this.dude[j].source(), 0, -10);        // the jar grabs the dude from y = 10
      const img = cx.getImageData(0, 0, 122, 160), d = img.data;
      const key = [d[0], d[1], d[2], d[3]];
      const px = new Int32Array(122 * 160);
      for (let k = 0; k < px.length; k++) {
        const o = k * 4;
        if (d[o] === key[0] && d[o + 1] === key[1] && d[o + 2] === key[2] && d[o + 3] === key[3]) {
          px[k] = (d[o + 3] << 24) | (d[o] << 16) | (d[o + 1] << 8) | d[o + 2];
          continue;
        }
        const r = (d[o] + b[o] * 3) >> 2, g = (d[o + 1] + b[o + 1] * 3) >> 2, bl = (d[o + 2] + b[o + 2] * 3) >> 2;
        px[k] = (255 << 24) | (r << 16) | (g << 8) | bl;
      }
      this.dudeb[j] = new Image(122, 160, px);
    }
  };
}
