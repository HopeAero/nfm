// Free Play's Rivals screen, NFM2 and Extended: how many cars race, and which.
// After the stage select (launcher.js for NFM2, ext/menus.js for Extended). The
// game still draws its own field (sortcars); pickRivals then rewrites xt.sc[1..n-1]
// from the player's pool and pinned slots. sc[0] is the player's car.

import { tr } from './i18n.js';

export const RIVALS_KEY = { nfm2: 'nfm.rivals.nfm2', ext: 'nfm.rivals.ext' };

// CarDefine.cclass of the 16 stock cars (CarDefine.js:49): the game's Class C .. Class A as 0-4
export const NFM2_CCLASS = [0, 0, 0, 0, 0, 1, 2, 2, 2, 2, 3, 4, 4, 4, 4, 4];
/** Class C and B&C -> C, B and A&B -> B, A -> A: six, five and five of the stock cars. */
export const tierOfClass = (c) => (c <= 1 ? 'C' : c <= 3 ? 'B' : 'A');

export const defaultRivals = (nCars) =>
  ({ count: 7, mode: 'game', pool: Array.from({ length: nCars }, (_, i) => i), fixed: [] });

/** A stored or URL config, repaired to something raceable; null if it is not a config at all. */
export function parseRivals(text, nCars, maxCount) {
  let o;
  try { o = JSON.parse(text); } catch { return null; }
  if (!o || typeof o !== 'object' || Array.isArray(o)) return null;
  const car = (v) => Number.isInteger(v) && v >= 0 && v < nCars;
  return {
    count: Number.isInteger(o.count) ? Math.max(1, Math.min(maxCount, o.count)) : Math.min(7, maxCount),
    mode: o.mode === 'pool' ? 'pool' : 'game',
    pool: Array.isArray(o.pool) ? [...new Set(o.pool.filter(car))] : [],
    fixed: Array.isArray(o.fixed) ? o.fixed.slice(0, maxCount - 1).map((v) => (car(v) ? v : null)) : [],
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
 * seeded one, so a replay draws the same), no repeats until every pool car is used;
 * an empty pool is the game's pick. Pinned slots (fixed[k-1]) win in both modes.
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

/** A tier button: tick every car of the tier if any is unticked, else untick them all. */
export function toggleTier(pool, cars, tier) {
  const ids = cars.filter((c) => c.tier === tier).map((c) => c.i);
  if (ids.every((i) => pool.includes(i))) return pool.filter((i) => !ids.includes(i));
  return [...new Set([...pool, ...ids])];
}

const esc = (s) => String(s).replace(/[&<>"]/g, (ch) => ({ '&': '&amp;', '<': '&lt;', '>': '&gt;', '"': '&quot;' })[ch]);

/**
 * The screen, over `host` (the stage box the game's canvases are in). Mouse for the
 * pool and the pinned slots; ◂ ▸ the count, Enter races, Esc goes back. Every key is
 * kept from the game while it is up; controls give their focus back after each change
 * (as the stage selects' <select>s do) so the keys keep reaching it.
 */
export function runRivals({ host, title = '', cars, tiers, min, max, cfg }) {
  const st = { count: cfg.count, mode: cfg.mode, pool: [...cfg.pool], fixed: [...cfg.fixed] };
  const blue = 'rgb(47,179,255)';
  const btn = `cursor:pointer;background:#000;color:#fff;border:1px solid ${blue};font:bold 13px Arial,sans-serif;padding:3px 10px`;
  const sel = `background:#000;color:${blue};border:1px solid ${blue};font:bold 12px Arial,sans-serif`;
  const box = document.createElement('div');
  box.style.cssText = 'position:absolute;inset:0;z-index:6;overflow:auto;box-sizing:border-box;padding:12px 20px;'
    + 'background:rgba(0,0,0,.85);color:#fff;font:bold 13px Arial,sans-serif;display:flex;flex-direction:column;gap:8px';
  const opt = (v, text, on) => `<option value="${v}"${on ? ' selected' : ''}>${esc(text)}</option>`;
  const radio = (v, label) =>
    `<label><input type="radio" name="rv-mode" value="${v}"${st.mode === v ? ' checked' : ''}> ${esc(tr(label))}</label>`;
  const render = () => {
    box.innerHTML = `
      <div style="text-align:center;font-size:20px">${esc(tr('Rivals'))}${title ? ` — ${esc(title)}` : ''}</div>
      <div>${esc(tr('Cars on track'))}: <button data-k="less" style="${btn}">◂</button>
        <span style="display:inline-block;min-width:28px;text-align:center">${st.count}</span>
        <button data-k="more" style="${btn}">▸</button></div>
      <div>${esc(tr('Draw'))}: ${radio('game', "Game's pick")} ${radio('pool', 'Only my pool')}</div>
      <div>${esc(tr('Tiers'))}: ${tiers.map((t) => `<button data-tier="${t}" style="${btn}">${t}</button>`).join(' ')}
        <button data-k="all" style="${btn}">${esc(tr('All cars'))}</button>
        <button data-k="none" style="${btn}">${esc(tr('No cars'))}</button></div>
      ${tiers.map((t) => `<div style="display:flex;flex-wrap:wrap;gap:2px 12px;opacity:${st.mode === 'pool' ? 1 : 0.5}">
        <b style="color:${blue};width:16px">${t}</b>
        ${cars.filter((c) => c.tier === t).map((c) =>
          `<label><input type="checkbox" data-car="${c.i}"${st.pool.includes(c.i) ? ' checked' : ''}> ${esc(c.name)}</label>`).join('')}
      </div>`).join('')}
      <div style="display:flex;flex-wrap:wrap;gap:4px 10px;align-items:center">${esc(tr('Fixed slots'))}:
        ${Array.from({ length: st.count - 1 }, (_, k) => `<span>${k + 1} <select data-slot="${k}" style="${sel}">`
          + opt('', tr('Random'), st.fixed[k] == null)
          + cars.map((c) => opt(c.i, c.name, st.fixed[k] === c.i)).join('') + '</select></span>').join('')}</div>
      <div style="display:flex;justify-content:space-between;align-items:center;margin-top:auto">
        <button data-k="back" style="${btn}">◂ ${esc(tr('Stage'))}</button>
        <span style="opacity:.8;font-size:12px">${esc(tr('◂ ▸ cars · Enter race · Esc back to the stage'))}</span>
        <button data-k="go" style="${btn}">${esc(tr('Race'))}</button></div>`;
    for (const el of box.querySelectorAll('input,select,button')) el.tabIndex = -1;
  };
  return new Promise((resolve) => {
    const result = () => ({ ...st, fixed: st.fixed.slice(0, st.count - 1) });
    const done = (v) => { removeEventListener('keydown', onKey, true); box.remove(); resolve(v); };
    const setCount = (n) => { st.count = Math.max(min, Math.min(max, n)); render(); };
    const onKey = (e) => {
      e.stopImmediatePropagation();      // the game's keys stay the game's: none of them while this is up
      if (e.key === 'ArrowLeft' || e.key === 'ArrowRight') { e.preventDefault(); setCount(st.count + (e.key === 'ArrowRight' ? 1 : -1)); }
      // a held Enter from the stage select must not race straight through
      else if (e.key === 'Enter' && !e.repeat) { e.preventDefault(); done(result()); }
      else if (e.key === 'Escape') { e.preventDefault(); done(null); }
    };
    box.addEventListener('mousedown', (e) => e.stopPropagation());   // not a click on the game's screen
    box.addEventListener('click', (e) => {
      const b = e.target.closest('button');
      if (!b) return;
      b.blur();
      const k = b.dataset.k;
      if (k === 'back') return done(null);
      if (k === 'go') return done(result());
      if (k === 'less' || k === 'more') return setCount(st.count + (k === 'more' ? 1 : -1));
      if (b.dataset.tier) st.pool = toggleTier(st.pool, cars, b.dataset.tier);
      if (k === 'all') st.pool = cars.map((c) => c.i);
      if (k === 'none') st.pool = [];
      render();
    });
    box.addEventListener('change', (e) => {
      const el = e.target;
      el.blur();
      if (el.name === 'rv-mode') st.mode = el.value;
      else if (el.dataset.car !== undefined) {
        const i = +el.dataset.car;
        st.pool = el.checked ? [...st.pool, i] : st.pool.filter((x) => x !== i);
      } else if (el.dataset.slot !== undefined) st.fixed[+el.dataset.slot] = el.value === '' ? null : +el.value;
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
