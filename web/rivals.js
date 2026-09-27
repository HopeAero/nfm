// Free Play's Rivals screen, NFM2 and Extended: how many cars race, and which.
// Opened from the stage select's RIVALS button (carselect.js for NFM2, ext/menus.js for Extended). The
// game still draws its own field (sortcars); pickRivals then rewrites xt.sc[1..n-1]
// from the player's pool and pinned slots. sc[0] is the player's car.

import { tr } from './i18n.js';
import { fpath } from './vfs.js';

export const RIVALS_KEY = { nfm2: 'nfm.rivals.nfm2', ext: 'nfm.rivals.ext' };

// CarDefine.cclass of the 16 stock cars (CarDefine.js:49): the game's Class C .. Class A as 0-4
export const NFM2_CCLASS = [0, 0, 0, 0, 0, 1, 2, 2, 2, 2, 3, 4, 4, 4, 4, 4];
/** Class C and B&C -> C, B and A&B -> B, A -> A: six, five and five of the stock cars. */
export const tierOfClass = (c) => (c <= 1 ? 'C' : c <= 3 ? 'B' : 'A');

/** count null: the game's own field size (NFM2 7, Extended the jar's randomno). */
export const defaultRivals = (nCars) =>
  ({ count: null, mode: 'game', pool: Array.from({ length: nCars }, (_, i) => i), fixed: [] });

/** A stored or URL config, repaired to something raceable; null if it is not a config at all. */
export function parseRivals(text, nCars, maxCount) {
  let o;
  try { o = JSON.parse(text); } catch { return null; }
  if (!o || typeof o !== 'object' || Array.isArray(o)) return null;
  const car = (v) => Number.isInteger(v) && v >= 0 && v < nCars;
  return {
    count: Number.isInteger(o.count) ? Math.max(1, Math.min(maxCount, o.count)) : null,
    mode: o.mode === 'pool' ? 'pool' : 'game',
    pool: Array.isArray(o.pool) ? [...new Set(o.pool.filter(car))] : [],
    fixed: Array.isArray(o.fixed) ? o.fixed.slice(0, maxCount - 1).map((v) => (car(v) ? v : null)) : [],
    ...(Number.isInteger(o.seed) ? { seed: o.seed } : {}),   // NFM2's pool draw (seededRandom)
  };
}

export function loadRivals(key, nCars, maxCount) {
  let text = null;
  try { text = localStorage.getItem(key); } catch { /* private window, blocked storage */ }
  return parseRivals(text, nCars, maxCount) ?? defaultRivals(nCars);
}

export function saveRivals(key, cfg) {
  try { localStorage.setItem(key, JSON.stringify(cfg)); } catch { /* not remembered, still raced */ }
}

/**
 * The grid for slots 1..n-1. 'pool' draws from the pool with `random` (the game's
 * seeded one for Extended; NFM2 passes seededRandom, its race seed being fixed), no
 * repeats until every pool car is used; an empty pool is the game's pick. Pinned
 * slots (fixed[k-1]) win in both modes.
 */
export function pickRivals({ mode, pool, fixed }, gameSc, n, random) {
  const sc = Array.from(gameSc);
  let bag = [];
  for (let k = 1; k < n; k++) {
    if (fixed[k - 1] != null) { sc[k] = fixed[k - 1]; continue; }
    if (mode !== 'pool' || !pool.length) continue;
    if (!bag.length) bag = [...pool];
    sc[k] = bag.splice(Math.floor(random() * bag.length), 1)[0];
  }
  return sc;
}

/** Two configs that race the same field (the pool only counts when drawing from it). */
export function sameRivals(a, b) {
  const pins = (f) => { const t = f.map((v) => v ?? null); while (t.length && t[t.length - 1] === null) t.pop(); return t.join(); };
  const pool = (c) => (c.mode === 'pool' ? [...c.pool].sort((x, y) => x - y).join() : '');
  return a.count === b.count && a.mode === b.mode && pool(a) === pool(b) && pins(a.fixed) === pins(b.fixed);
}

/**
 * A tier button on the Rivals screen. From every car (the default pool) it narrows to
 * that tier -- "only the S cars" is the usual ask -- otherwise it toggles the tier.
 * ALL fills the pool, or empties it when already full.
 */
export function tierClick(pool, cars, tier) {
  const all = cars.every((c) => pool.includes(c.i));
  if (tier === 'ALL') return all ? [] : cars.map((c) => c.i);
  if (all) return cars.filter((c) => c.tier === tier).map((c) => c.i);
  return toggleTier(pool, cars, tier);
}

/**
 * The pool's own draw for NFM2, whose race runs on a fixed seed (12345): the pool
 * would otherwise give the same grid every race. mulberry32.
 */
export function seededRandom(seed) {
  let a = seed | 0;
  return () => {
    a = (a + 0x6d2b79f5) | 0;
    let t = Math.imul(a ^ (a >>> 15), 1 | a);
    t = (t + Math.imul(t ^ (t >>> 7), 61 | t)) ^ t;
    return ((t ^ (t >>> 14)) >>> 0) / 4294967296;
  };
}

/** A tier button: tick every car of the tier if any is unticked, else untick them all. */
export function toggleTier(pool, cars, tier) {
  const ids = cars.filter((c) => c.tier === tier).map((c) => c.i);
  if (ids.every((i) => pool.includes(i))) return pool.filter((i) => !ids.includes(i));
  return [...new Set([...pool, ...ids])];
}

const esc = (s) => String(s).replace(/[&<>"]/g, (ch) => ({ '&': '&amp;', '<': '&lt;', '>': '&gt;', '"': '&quot;' })[ch]);

// The game's look: the Adventure face (Extended's ext/fonts), the orange ELIGE PISTA
// title, the yellow pill buttons of ATRÁS / SIGUE / CONTINUAR.
const CSS = `
.rv{position:absolute;inset:0;z-index:6;box-sizing:border-box;padding:10px 18px 12px;display:flex;flex-direction:column;gap:7px;
  background:rgba(0,0,0,.88);color:#fff;font:italic 15px Adventure,Arial,sans-serif;user-select:none}
.rv h1{margin:0;text-align:center;font:italic 34px Adventure,Arial,sans-serif;letter-spacing:3px;line-height:1.1;
  background:linear-gradient(#fff0b8,#ffb04a 50%,#e8501c);-webkit-background-clip:text;background-clip:text;color:transparent;
  -webkit-text-stroke:1px #4a1800;filter:drop-shadow(0 2px 0 #000)}
.rv .row{display:flex;align-items:center;justify-content:center;gap:10px}
.rv button,.rv-btn{cursor:pointer;font:italic 15px Adventure,Arial,sans-serif;color:#ffd21f;text-shadow:0 1px 0 #000;
  background:linear-gradient(#3d3c10,#1c1b04);border:2px solid #8f8216;border-radius:14px;padding:3px 14px;
  box-shadow:0 0 6px rgba(255,210,31,.3)}
.rv button:hover,.rv-btn:hover{color:#fff36b;border-color:#e0c41c}
.rv button.on{color:#1c1b04;text-shadow:none;background:linear-gradient(#fff08a,#e3ac00);border-color:#fff6b0}
.rv button.go{font-size:19px;padding:4px 28px;color:#e8f53a;border-color:#b8c81c;box-shadow:0 0 10px rgba(200,220,30,.55)}
.rv .count{font-size:20px;min-width:190px;text-align:center}
.rv .count small{font-size:13px;color:#ffd21f}
.rv .grid{flex:1;min-height:0;overflow:auto;display:grid;align-content:start;grid-template-columns:repeat(auto-fill,minmax(94px,1fr));gap:6px;padding:2px 4px}
.rv .card{position:relative;cursor:pointer;background:#141414;border:2px solid #444;border-radius:7px;padding:2px 2px 3px;
  display:flex;flex-direction:column;align-items:center}
.rv .card.on{border-color:#ffd21f;box-shadow:0 0 8px rgba(255,210,31,.45)}
.rv .card.dim{filter:grayscale(1) brightness(.4)}
.rv .card canvas{position:static!important;width:88px!important;height:50px!important}   /* over the page's #stage canvas rule */
.rv .card span{font:bold 11px Arial,sans-serif;color:#eee;text-align:center;line-height:1.1}
.rv .card b{position:absolute;left:4px;top:1px;font:italic 13px Adventure,Arial,sans-serif;color:#ffd21f}
.rv .slots{display:flex;flex-wrap:wrap;gap:4px;justify-content:center;align-items:center}
.rv .slot{cursor:pointer;width:56px;background:#0d0d0d;border:2px solid #555;border-radius:6px;text-align:center;
  font:italic 11px Adventure,Arial,sans-serif;color:#ffd21f;padding:1px}
.rv .slot canvas{position:static!important;width:52px!important;height:30px!important;display:block}
.rv .slot.sel{border-color:#fff;box-shadow:0 0 8px #fff}
.rv .hint{font:12px Arial,sans-serif;color:#ccc;text-align:center}`;

let fontReady = null;
function ensureStyle() {
  if (!document.getElementById('rivals-css')) {
    const st = document.createElement('style');
    st.id = 'rivals-css';
    st.textContent = CSS;
    document.head.append(st);
  }
  // Extended has the face already (race.js); the launcher's NFM2 does not. (Not
  // document.fonts.check: it answers true for a face that does not exist at all.)
  fontReady ??= [...document.fonts].some((f) => f.family.replace(/"/g, '') === 'Adventure') ? Promise.resolve()
    : new FontFace('Adventure', `url(${fpath}ext/fonts/Adventure.ttf)`).load()
      .then((f) => document.fonts.add(f), () => { /* Arial stands in */ });
  return fontReady;
}

/** The stage selects' RIVALS button, in the game's pill look. */
export function rivalsButton(onClick) {
  ensureStyle();
  const b = document.createElement('button');
  b.className = 'rv-btn';
  b.textContent = tr('Rivals').toUpperCase();
  b.tabIndex = -1;
  b.addEventListener('mousedown', (e) => e.stopPropagation());   // not a click on the game's screen
  b.addEventListener('click', () => { b.blur(); onClick(); });
  return b;
}

/**
 * A car drawn somewhere on `src`, cropped to its outline and fitted to w x h: the
 * cars differ by a factor of three in size. The background is the corner pixel.
 */
export function fitThumb(src, w = 176, h = 100) {
  const out = Object.assign(document.createElement('canvas'), { width: w, height: h });
  const sw = 220, sh = Math.max(1, Math.round(sw * src.height / src.width));
  const small = Object.assign(document.createElement('canvas'), { width: sw, height: sh });
  const g = small.getContext('2d', { willReadFrequently: true });
  g.drawImage(src, 0, 0, sw, sh);
  const d = g.getImageData(0, 0, sw, sh).data;
  const bg = [d[0], d[1], d[2], d[3]];
  let x0 = sw, y0 = sh, x1 = -1, y1 = -1;
  for (let y = 0; y < sh; y++) for (let x = 0; x < sw; x++) {
    const o = (y * sw + x) * 4;
    const ink = bg[3] < 16 ? d[o + 3] > 16
      : Math.abs(d[o] - bg[0]) + Math.abs(d[o + 1] - bg[1]) + Math.abs(d[o + 2] - bg[2]) > 30;
    if (ink) { if (x < x0) x0 = x; if (x > x1) x1 = x; if (y < y0) y0 = y; if (y > y1) y1 = y; }
  }
  if (x1 < 0) return out;
  const k = src.width / sw;
  let bx = (x0 - 2) * k, by = (y0 - 2) * k, bw = (x1 - x0 + 5) * k, bh = (y1 - y0 + 5) * k;
  if (bw / bh > w / h) { const nh = bw * h / w; by -= (nh - bh) / 2; bh = nh; } else { const nw = bh * w / h; bx -= (nw - bw) / 2; bw = nw; }
  out.getContext('2d').drawImage(src, bx, by, bw, bh, 0, 0, w, h);
  return out;
}

let closeOpen = null;
/** Take the screen down without an answer (a menu that failed under it). */
export function closeRivals() { closeOpen?.(null); }

/**
 * The screen, over `host` (the stage box the game's canvases are in), opened by
 * the stage select's RIVALS button. Resolves with the new config on Done / Enter,
 * null on Cancel / Esc. ◂ ▸ change the count; every key is kept from the game
 * while it is up.
 * @param o.cars       [{ i, name, tier }] the stock cars
 * @param o.gameCount  the game's own field size, shown when cfg.count is null
 * @param o.thumb      (i) -> a canvas with car i drawn, or null
 */
export function runRivals({ host, cars, tiers, min, max, gameCount, cfg, thumb = () => null }) {
  ensureStyle();
  const st = { count: cfg.count, mode: cfg.mode, pool: [...cfg.pool], fixed: [...cfg.fixed] };
  let slot = -1, jump = false;   // jump: show the first chosen car after a tier click
  const order = [...cars].sort((a, b) => tiers.indexOf(a.tier) - tiers.indexOf(b.tier));   // C .. S
  const shots = new Map();
  const shot = (i) => { if (!shots.has(i)) shots.set(i, thumb(i)); return shots.get(i); };
  const nameOf = new Map(cars.map((c) => [c.i, c.name]));
  const count = () => st.count ?? gameCount;
  const box = document.createElement('div');
  box.className = 'rv';
  const render = () => {
    const lit = (t) => st.mode === 'pool' && (t === 'ALL' ? cars : cars.filter((c) => c.tier === t)).every((c) => st.pool.includes(c.i));
    const n = count();
    box.innerHTML = `
      <h1>${esc(tr('Rivals').toUpperCase())}</h1>
      <div class="row">
        <button data-k="less">◂ ${esc(tr('Less'))}</button>
        <span class="count">${esc(tr('Cars'))}: ${n}${st.count == null ? ` <small>(${esc(tr('the game'))})</small>` : ''}</span>
        <button data-k="more">${esc(tr('More'))} ▸</button>
      </div>
      <div class="row">
        <button data-k="game" class="${st.mode === 'game' && st.count == null && !st.fixed.some((v) => v != null) ? 'on' : ''}">${esc(tr('As the game'))}</button>
        <button data-k="pool" class="${st.mode === 'pool' ? 'on' : ''}">${esc(tr('Choose'))}</button>
      </div>
      <div class="row">${tiers.map((t) => `<button data-tier="${t}" class="${lit(t) ? 'on' : ''}">${t}</button>`).join('')}
        <button data-tier="ALL" class="${lit('ALL') ? 'on' : ''}">${esc(tr('All cars'))}</button></div>
      <div class="grid">${order.map((c) =>
        `<div class="card${st.mode !== 'pool' || slot >= 0 ? '' : st.pool.includes(c.i) ? ' on' : ' dim'}" data-car="${c.i}"><b>${c.tier}</b>`
        + `<canvas width="176" height="100"></canvas><span>${esc(c.name)}</span></div>`).join('')}</div>
      <div class="slots">${esc(tr('Fixed slots'))}: ${Array.from({ length: n - 1 }, (_, k) =>
        `<div class="slot${slot === k ? ' sel' : ''}" data-slot="${k}">${st.fixed[k] != null
          ? '<canvas width="104" height="60"></canvas>' : `${k + 1}<br>${esc(tr('Random'))}`}</div>`).join('')}</div>
      <div class="hint">${esc(tr(slot >= 0 ? 'Now click a car for that slot · click the slot again: random'
        : '◂ ▸ cars · click a slot, then a car, to fix it · Enter done · Esc cancel'))}</div>
      <div class="row"><button data-k="cancel">${esc(tr('Cancel'))}</button><button data-k="done" class="go">${esc(tr('Done'))}</button></div>`;
    for (const el of box.querySelectorAll('.card')) paint(el.querySelector('canvas'), +el.dataset.car);
    for (const el of box.querySelectorAll('.slot')) {
      const c = st.fixed[+el.dataset.slot];
      if (c != null) { paint(el.querySelector('canvas'), c); el.title = nameOf.get(c) || ''; }
    }
    for (const el of box.querySelectorAll('button')) el.tabIndex = -1;
    if (jump) { jump = false; box.querySelector('.card.on')?.scrollIntoView({ block: 'nearest' }); }
  };
  const paint = (canvas, i) => {
    const src = shot(i);
    if (src && canvas) canvas.getContext('2d').drawImage(src, 0, 0, canvas.width, canvas.height);
  };
  return new Promise((resolve) => {
    const done = (v) => {
      closeOpen = null;
      removeEventListener('keydown', onKey, true);
      box.remove();
      resolve(v);
    };
    closeOpen = done;
    const result = () => ({ ...st, fixed: st.fixed.slice(0, (st.count ?? max) - 1) });
    const setCount = (d) => { st.count = Math.max(min, Math.min(max, count() + d)); render(); };
    const onKey = (e) => {
      e.stopImmediatePropagation();      // the game's keys stay the game's: none of them while this is up
      if (e.key === 'ArrowLeft' || e.key === 'ArrowRight') { e.preventDefault(); setCount(e.key === 'ArrowRight' ? 1 : -1); }
      else if (e.key === 'Enter' && !e.repeat) { e.preventDefault(); done(result()); }
      else if (e.key === 'Escape') { e.preventDefault(); done(null); }
    };
    box.addEventListener('mousedown', (e) => e.stopPropagation());   // not a click on the game's screen
    box.addEventListener('click', (e) => {
      const b = e.target.closest('button,.card,.slot');
      if (!b) return;
      b.blur?.();
      const k = b.dataset.k;
      if (k === 'cancel') return done(null);
      if (k === 'done') return done(result());
      if (k === 'less' || k === 'more') return setCount(k === 'more' ? 1 : -1);
      if (k === 'game') Object.assign(st, { mode: 'game', count: null, fixed: [], pool: cars.map((c) => c.i) });
      if (k === 'pool') st.mode = 'pool';
      if (b.dataset.tier) {                // a tier chooses: see tierClick
        st.pool = tierClick(st.mode === 'pool' ? st.pool : cars.map((c) => c.i), cars, b.dataset.tier);
        st.mode = 'pool';
        jump = true;
      }
      if (b.dataset.slot !== undefined) {
        const k2 = +b.dataset.slot;
        if (slot === k2) { st.fixed[k2] = null; slot = -1; } else slot = k2;
      }
      if (b.dataset.car !== undefined) {
        const i = +b.dataset.car;
        if (slot >= 0) { st.fixed[slot] = i; slot = -1; } else if (st.mode === 'pool') {
          st.pool = st.pool.includes(i) ? st.pool.filter((x) => x !== i) : [...st.pool, i];
        } else { st.mode = 'pool'; st.pool = [i]; }   // a car clicked in the game's pick: choosing starts with it
      }
      render();
    });
    addEventListener('keydown', onKey, true);
    render();
    host.append(box);
  });
}

/**
 * Extended's xt.sortcars with the Rivals config (load()) over the game's draw. The
 * player's sc[0] is put back: sortcars writes the stage's boss car to sc[nplayers-1],
 * which with one car on track is the player's own slot.
 */
export function withRivals(sortcars, load, random) {
  return function (...a) {
    const me = this.sc[0];
    sortcars.apply(this, a);
    this.sc[0] = me;
    const sc = pickRivals(load(), this.sc, this.nplayers, random);
    for (let k = 1; k < this.nplayers; k++) this.sc[k] = sc[k];
  };
}

/**
 * Extended's classic draw (the NFM 2 stages, xtGraphics.sortcars) never repeats a car
 * and has sixteen to draw from (23-38, and M A S H E E N only in slots 1-2): the jar
 * races seven there, and past about fifteen its `while (!aflag)` never ends. It draws
 * its own seven; the rest come from the same sixteen, repeats allowed, each passing the
 * jar's own proba rejection. Normal mode repeats past eleven cars and needs none of this.
 */
export const CLASSIC_FIELD = 7;
export function withClassicField(sortcars, random) {
  return function (...a) {
    const n = this.nplayers;
    if (!this.classicmode || n <= CLASSIC_FIELD) return sortcars.apply(this, a);
    this.nplayers = CLASSIC_FIELD;
    try { sortcars.apply(this, a); } finally { this.nplayers = n; }
    for (let k = CLASSIC_FIELD; k < n; k++) {
      let c;
      do c = 23 + Math.trunc(random() * 16); while (c === 36 || random() < this.proba[c]);
      this.sc[k] = c;
    }
  };
}
