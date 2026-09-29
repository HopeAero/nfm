// On-screen controls for phones and tablets, laid out like NFM Re-Lit's: steer
// bottom left, look back above it; brake and gas bottom right, the handbrake
// above the gas; pause top right.
//
// Each button is a KEY: pressing it dispatches the keydown the keyboard would,
// releasing it the keyup. That is what lets one overlay drive both races and
// their screens without knowing any of them -- the base race reads `e.code`
// (main.js installInput), Extended reads `e.key` (ext/race.js javaKey), so
// every action carries both. The race also sets `easyStunts` on the player's
// Control while these are up (Mad.js): arrows alone start a stunt in the air.

/** What each button presses: [code for main.js, key for Extended]. Look back is
 *  Shift in the base port and Z in the jar (lookback = 1). */
export const ACTIONS = {
  left: { code: 'ArrowLeft', key: 'ArrowLeft' },
  right: { code: 'ArrowRight', key: 'ArrowRight' },
  up: { code: 'ArrowUp', key: 'ArrowUp' },
  down: { code: 'ArrowDown', key: 'ArrowDown' },
  handb: { code: 'Space', key: ' ' },
  look: { code: 'ShiftLeft', key: 'z' },
  pause: { code: 'Escape', key: 'Escape' },
};

/** The launcher's "Touch controls" setting: 'auto' (touch screens), 'on', 'off'. */
export const touchWanted = (setting, coarse) => (setting === 'on' ? true : setting === 'off' ? false : !!coarse);

/**
 * Fingers -> key presses. `send(type, action)` fires 'keydown' / 'keyup'. Each
 * pointer holds at most one action; a thumb sliding onto another button swaps
 * keys without lifting, and a key two fingers hold is released when both lift.
 */
export function touchPointers(send) {
  const held = new Map();     // pointerId -> action
  const count = new Map();    // action -> fingers on it
  const press = (a) => { const n = count.get(a) || 0; count.set(a, n + 1); if (!n) send('keydown', a); };
  const release = (a) => { const n = count.get(a) || 0; if (n <= 1) { count.delete(a); if (n) send('keyup', a); } else count.set(a, n - 1); };
  const set = (id, a) => {
    const was = held.get(id) ?? null;
    if (was === a) return;
    if (was) release(was);
    if (a) { held.set(id, a); press(a); } else held.delete(id);
  };
  return {
    down: (id, a) => set(id, a),
    move: (id, a) => { if (held.has(id)) set(id, a); },
    up: (id) => set(id, null),
  };
}

const ICON = {
  left: '<path d="M30 12 L12 24 L30 36 Z"/><rect x="30" y="20" width="8" height="8"/>',
  right: '<path d="M18 12 L36 24 L18 36 Z"/><rect x="10" y="20" width="8" height="8"/>',
  up: '<path d="M12 28 L24 10 L36 28 Z"/><rect x="20" y="28" width="8" height="10"/>',
  down: '<path d="M12 20 L24 38 L36 20 Z"/><rect x="20" y="10" width="8" height="10"/>',
  handb: '<path d="M10 34 L34 12 L38 16 L16 38 Z"/><rect x="8" y="34" width="20" height="5" rx="2"/>',
  look: '<path d="M24 10 A14 14 0 1 1 11 19" fill="none" stroke="currentColor" stroke-width="4"/><path d="M6 12 L16 14 L10 23 Z"/><circle cx="24" cy="24" r="4"/>',
  pause: '<rect x="12" y="13" width="24" height="4"/><rect x="12" y="22" width="24" height="4"/><rect x="12" y="31" width="24" height="4"/>',
};

// [action, CSS position]; sizes in vmin so they scale with the phone, not the game
const LAYOUT = [
  ['left', 'left:3vmin;bottom:5vmin'],
  ['right', 'left:22vmin;bottom:5vmin'],
  ['look', 'left:3vmin;bottom:25vmin'],
  ['down', 'right:22vmin;bottom:5vmin'],
  ['up', 'right:3vmin;bottom:5vmin'],
  ['handb', 'right:3vmin;bottom:25vmin'],
  ['pause', 'right:2vmin;top:2vmin;width:10vmin;height:10vmin'],
];

/** Put the buttons on the page, shown while `visible()` holds. Returns a function that removes them. */
export function mountTouchControls(visible = () => true) {
  const root = document.createElement('div');
  root.id = 'touch-controls';
  root.style.cssText = 'position:fixed;inset:0;z-index:50;pointer-events:none;user-select:none;-webkit-user-select:none;';
  for (const [a, pos] of LAYOUT) {
    const b = document.createElement('div');
    b.dataset.touch = a;
    b.style.cssText = 'position:absolute;width:17vmin;height:17vmin;box-sizing:border-box;border-radius:2.5vmin;'
      + 'background:rgba(130,130,130,.38);border:.5vmin solid rgba(255,255,255,.45);color:rgba(255,255,255,.9);filter:drop-shadow(0 0 .4vmin rgba(0,0,0,.6));'
      + 'display:flex;align-items:center;justify-content:center;pointer-events:auto;touch-action:none;'
      + `-webkit-touch-callout:none;${pos}`;
    b.innerHTML = `<svg viewBox="0 0 48 48" width="62%" height="62%" fill="currentColor">${ICON[a]}</svg>`;
    root.append(b);
  }
  document.body.append(root);

  const key = (type, a) => {
    root.querySelector(`[data-touch="${a}"]`).style.background = type === 'keydown' ? 'rgba(255,196,0,.55)' : 'rgba(130,130,130,.38)';   // the game's yellow
    const { code, key } = ACTIONS[a];
    dispatchEvent(new KeyboardEvent(type, { code, key, bubbles: true, cancelable: true }));
  };
  const fingers = touchPointers(key);
  const under = (e) => document.elementFromPoint(e.clientX, e.clientY)?.closest?.('[data-touch]')?.dataset.touch ?? null;
  const onDown = (e) => {
    const a = e.target.closest?.('[data-touch]')?.dataset.touch;
    if (!a) return;
    e.preventDefault();
    // touch pointers are captured to the first element: let a thumb slide to the next button
    if (e.target.hasPointerCapture?.(e.pointerId)) e.target.releasePointerCapture(e.pointerId);
    fingers.down(e.pointerId, a);
  };
  const onMove = (e) => fingers.move(e.pointerId, under(e));
  const onUp = (e) => fingers.up(e.pointerId);
  let raf = 0;
  const show = () => { root.style.display = visible() ? '' : 'none'; raf = requestAnimationFrame(show); };
  show();
  root.addEventListener('pointerdown', onDown);
  root.addEventListener('contextmenu', (e) => e.preventDefault());
  addEventListener('pointermove', onMove);
  addEventListener('pointerup', onUp);
  addEventListener('pointercancel', onUp);
  return () => {
    cancelAnimationFrame(raf);
    removeEventListener('pointermove', onMove);
    removeEventListener('pointerup', onUp);
    removeEventListener('pointercancel', onUp);
    root.remove();
  };
}

/** The launcher's setting (localStorage 'nfm.launcher'.touch) and this screen, together. */
export function touchEnabled() {
  let setting = 'auto';
  try { setting = JSON.parse(localStorage.getItem('nfm.launcher') || '{}').touch || 'auto'; } catch { /* private mode */ }
  return touchWanted(setting, typeof matchMedia === 'function' && matchMedia('(pointer: coarse)').matches);
}
