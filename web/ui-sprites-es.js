// Spanish variants of the small English-only bitmaps in images.zip.
// Keep the original dimensions and chrome except for the lap label, widened
// into the available HUD gap to spell VUELTAS. The English ZIP stays intact.

const HUD = {
  'damage.gif': { text: 'DAÑO', erase: 58, size: 11 },
  'power.gif': { text: 'PODER', erase: 58, size: 11 },
  'position.gif': { text: 'PUESTO:', erase: 65, size: 10 },
  'speed.gif': { text: 'VEL:', erase: 48, size: 11 },
  'wasted.gif': { text: 'BAJAS:', erase: 53, size: 10 },
  'lap.gif': { text: 'VUELTAS:', erase: 78, width: 78, size: 11 },
};

const TITLES = {
  'selectcar.gif': ['ELIGE TU AUTO', 'orange', 16],
  'select.gif': ['ELIGE PISTA', 'orange', 17],
  'congrad.gif': ['¡FELICIDADES!', 'orange', 35],
  'gameov.gif': ['FIN DE JUEGO', 'orange', 30],
  'gameh.gif': ['MEJOR JUGADA', 'ice', 21],
  'wgame.gif': ['VER CARRERA', 'ice', 21],
  'youwon.gif': ['¡GANASTE!', 'ink', 24],
  'youlost.gif': ['¡PERDISTE!', 'ink', 24],
  'yourwasted.gif': ['¡TE DESTRUYERON!', 'ink', 23],
  'youwastedem.gif': ['¡LOS DESTRUISTE!', 'ink', 23],
  '0c.gif': ['¡YA!', 'ink', 35],
  // the car select's Car Maker cars switch (web/ext/cargroup.js); [label, style, size, width]
  'cmc.gif': ['MIS AUTOS', 'cyan', 12],
  'gac.gif': ['< AUTOS DEL JUEGO', 'cyan', 12, 132],
  'ycmc.gif': ['MIS AUTOS', 'orange', 12],
};

const newCanvas = (source, width = source.width) => {
  const canvas = new OffscreenCanvas(width, source.height);
  return { canvas, ctx: canvas.getContext('2d') };
};

/** Keep the exact border pixels from paused.gif and remove its English ink. */
export function spanishPauseBackground(source) {
  const { canvas, ctx } = newCanvas(source);
  ctx.drawImage(source, 0, 0);
  // In the source, x=6..230 and y=6..181 are the flat blue interior.
  ctx.fillStyle = 'rgb(60,135,220)';
  ctx.fillRect(6, 6, 225, 176);
  return canvas;
}

function eraseButtonLetters(ctx, x0, x1, height) {
  const img = ctx.getImageData(x0, 4, x1 - x0, height - 8);
  const p = img.data;
  for (let i = 0; i < p.length; i += 4) {
    // The letters are gold; the original button interior is dark olive.
    // Change just those pixels, retaining the original frame and shading.
    if (p[i] > 72 && p[i + 1] > 65 && p[i + 2] < 95) {
      p[i] = 49; p[i + 1] = 49; p[i + 2] = 0;
    }
  }
  ctx.putImageData(img, x0, 4);
}

function fit(ctx, label, size, width) {
  while (size > 7) {
    ctx.font = `italic 900 ${size}px "Arial Black", Arial, sans-serif`;
    if (ctx.measureText(label).width <= width) break;
    size--;
  }
  return size;
}

function drawTitle(ctx, label, style, size, width, height) {
  size = fit(ctx, label, size, width - 4);
  ctx.textAlign = 'center';
  ctx.textBaseline = 'middle';
  ctx.lineJoin = 'round';
  const x = width / 2, y = height / 2 + 0.5;
  if (style === 'ink') {
    ctx.fillStyle = '#080808';
    ctx.fillText(label, x, y);
    return;
  }
  if (style === 'cyan') {
    // cmc.gif / gac.gif: black letters edged in the button's sky blue
    ctx.strokeStyle = '#1eb4ff';
    ctx.lineWidth = 2;
    ctx.strokeText(label, x, y);
    ctx.fillStyle = '#050505';
    ctx.fillText(label, x, y);
    return;
  }
  ctx.shadowColor = '#080808';
  ctx.shadowBlur = 0;
  ctx.shadowOffsetX = 1;
  ctx.shadowOffsetY = 1;
  ctx.strokeStyle = style === 'ice' ? '#143657' : '#301b0e';
  ctx.lineWidth = style === 'ice' ? 2.4 : 1.8;
  ctx.strokeText(label, x, y);
  ctx.shadowColor = 'transparent';
  const gradient = ctx.createLinearGradient(0, 1, 0, height - 1);
  if (style === 'ice') {
    gradient.addColorStop(0, '#e8faff');
    gradient.addColorStop(0.48, '#81c7f6');
    gradient.addColorStop(1, '#d7f6ff');
  } else {
    gradient.addColorStop(0, '#fff1c2');
    gradient.addColorStop(0.48, '#ffbc88');
    gradient.addColorStop(1, '#f77756');
  }
  ctx.fillStyle = gradient;
  ctx.fillText(label, x, y);
}

/** Return a native-size drawable for a Spanish UI sprite, or the source. */
export function spanishSprite(name, source) {
  if (!source) return source;
  const hud = HUD[name];
  const title = TITLES[name];
  const rank = /^[1-8]\.gif$/.test(name);
  const arrow = name === 'back.gif' || name === 'next.gif';
  if (!hud && !title && name !== 'continue.gif' && !rank && !arrow) return source;
  const { canvas, ctx } = newCanvas(source, hud?.width ?? title?.[3]);

  if (rank) {
    ctx.fillStyle = 'rgb(192,192,192)';
    ctx.fillRect(0, 0, canvas.width, canvas.height);
    // The original suffix overlaps the glyph's right edge on ranks 2–8.
    // Draw the whole numeral so none of its strokes are erased with the suffix.
    ctx.font = 'italic 900 18px "Arial Black", Arial, sans-serif';
    ctx.textAlign = 'left';
    ctx.textBaseline = 'middle';
    ctx.lineJoin = 'round';
    ctx.strokeStyle = '#202020';
    ctx.lineWidth = 1.2;
    ctx.strokeText(name[0], 2, canvas.height / 2 + 0.5);
    ctx.fillStyle = '#075aaa';
    ctx.fillText(name[0], 2, canvas.height / 2 + 0.5);
  } else if (hud) {
    ctx.fillStyle = 'rgb(192,192,192)';
    ctx.fillRect(0, 0, canvas.width, canvas.height);
    ctx.drawImage(source, 0, 0);
    // The source GIF's grey 192 is the transparent key used by loadsnap().
    ctx.fillStyle = 'rgb(192,192,192)';
    ctx.fillRect(0, 0, hud.erase, canvas.height);
    fit(ctx, hud.text, hud.size, hud.erase - 2);
    ctx.fillStyle = '#050505';
    ctx.textAlign = 'left';
    ctx.textBaseline = 'middle';
    ctx.fillText(hud.text, 1, canvas.height / 2 + 0.5);
  } else if (arrow) {
    ctx.drawImage(source, 0, 0);
    const back = name === 'back.gif';
    eraseButtonLetters(ctx, back ? 16 : 8, back ? 51 : 46, canvas.height);
    const label = back ? 'ATRÁS' : 'SIGUE';
    fit(ctx, label, 12, 38);
    ctx.textAlign = 'center';
    ctx.textBaseline = 'middle';
    ctx.fillStyle = '#ffcb18';
    ctx.fillText(label, back ? 35 : 27, canvas.height / 2 + 0.5);
  } else if (name === 'continue.gif') {
    ctx.drawImage(source, 0, 0);
    eraseButtonLetters(ctx, 11, 80, canvas.height);
    fit(ctx, 'CONTINUAR', 12, canvas.width - 15);
    ctx.textAlign = 'center';
    ctx.textBaseline = 'middle';
    ctx.fillStyle = '#ffcb18';
    ctx.fillText('CONTINUAR', canvas.width / 2, canvas.height / 2 + 0.5);
  } else {
    // Snap-processed HUD headings need the same grey key as their originals.
    if (title[1] === 'ink') {
      ctx.fillStyle = 'rgb(192,192,192)';
      ctx.fillRect(0, 0, canvas.width, canvas.height);
    }
    drawTitle(ctx, title[0], title[1], title[2], canvas.width, canvas.height);
  }
  return canvas;
}
