// The Car Maker cars switch on Free Play's car select, as NFM 2 drew it (xtGraphics.carselect,
// cfase 0 / 3): a drawcarb button -- bcl, bc stretched, bcr; pbcl, pbc, pbcr under the pointer --
// labelled with cmc.gif ("Car Maker Cars") or gac.gif ("< Game Cars"), and in the Car Maker view
// the orange ycmc.gif header under the title. Extended has none of these files: they are the
// base game's (data/images.zip); in Spanish ui-sprites-es.js redraws the three labels.
import { readZip } from '../vfs.js';
import { spanishSprite } from '../ui-sprites-es.js';
import { lang } from '../i18n.js';

/** xtGraphics.drawcarb's geometry for an image label n5 wide. */
export const carbLayout = (n5) => ({ width: n5 + 14, height: 28, labelX: 7, labelY: n5 === 73 ? 6 : 7 });

async function decode(bytes) {
  const bmp = await createImageBitmap(new Blob([bytes], { type: 'image/gif' }));
  const c = new OffscreenCanvas(bmp.width, bmp.height);
  c.getContext('2d').drawImage(bmp, 0, 0);
  return c;
}

const url = (draw, w, h) => {
  const c = document.createElement('canvas');
  c.width = w; c.height = h;
  draw(c.getContext('2d'));
  return c.toDataURL();
};

// drawcarb(false, image, "", n, n2, ...): the frame, then the image label inside it
const carb = ([l, m, r], label) => {
  const { width, height, labelX, labelY } = carbLayout(label.width);
  return url((g) => {
    g.drawImage(l, 0, 0);
    g.drawImage(m, 4, 0, label.width + 6, height);
    g.drawImage(r, label.width + 10, 0);
    g.drawImage(label, labelX, labelY);
  }, width, height);
};

/** Data URLs: toMine / toGame buttons as [normal, hover], and the Car Maker view's header. */
export async function groupSprites() {
  const zip = await readZip('data/images.zip');
  const get = async (n) => { const c = await decode(zip.get(n)); return lang === 'es' ? spanishSprite(n, c) : c; };
  const [bcl, bc, bcr, pbcl, pbc, pbcr, cmc, gac, ycmc] = await Promise.all(
    ['bcl', 'bc', 'bcr', 'pbcl', 'pbc', 'pbcr', 'cmc', 'gac', 'ycmc'].map((n) => get(`${n}.gif`)));
  const up = [bcl, bc, bcr], over = [pbcl, pbc, pbcr];
  return {
    toMine: [carb(up, cmc), carb(over, cmc)],
    toGame: [carb(up, gac), carb(over, gac)],
    header: url((g) => g.drawImage(ycmc, 0, 0), ycmc.width, ycmc.height),
  };
}
