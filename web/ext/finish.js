// The race-end smear as the base port draws it (web/XtGraphics.js fleximage):
// the frozen race frame bleeding colour along each row with a warm tint
// (r/21, g/22, b/24), seven passes, then held. Extended's own fleximage mixes
// every channel towards grey (/22 each) and runs twelve passes, which leaves
// the frame a near-black blur; its race-end screens (finish(): unlocks,
// rewards, CONTINUE) are drawn over whichever this is, unchanged.

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
