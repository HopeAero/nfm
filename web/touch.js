// On-screen controls for phones and tablets, laid out like NFM Re-Lit's: steer
// bottom left, arrow target above it; brake and gas bottom right, the handbrake
// above the gas; pause top right.
//
// Each button is a KEY: pressing it dispatches the keydown the keyboard would,
// releasing it the keyup. That is what lets one overlay drive both races and
// their screens without knowing any of them -- the base race reads `e.code`
// (main.js installInput), Extended reads `e.key` (ext/race.js javaKey), so
// every action carries both. Easy stunts are a separate launcher setting.

/** What each button presses: [code for main.js, key for Extended]. */
export const ACTIONS = {
  left: { code: 'ArrowLeft', key: 'ArrowLeft' },
  right: { code: 'ArrowRight', key: 'ArrowRight' },
  up: { code: 'ArrowUp', key: 'ArrowUp' },
  down: { code: 'ArrowDown', key: 'ArrowDown' },
  handb: { code: 'Space', key: ' ' },
  target: { code: 'KeyA', key: 'a' },
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
    held.set(id, a);
    if (a) press(a);
  };
  return {
    down: (id, a) => set(id, a),
    move: (id, a) => { if (held.has(id)) set(id, a); },
    up: (id) => { set(id, null); held.delete(id); },
    clear: () => { for (const id of held.keys()) { set(id, null); } held.clear(); },
  };
}

const ICON = {
  left: '<path d="M30 12 L12 24 L30 36 Z"/><rect x="30" y="20" width="8" height="8"/>',
  right: '<path d="M18 12 L36 24 L18 36 Z"/><rect x="10" y="20" width="8" height="8"/>',
  up: '<path d="M12 28 L24 10 L36 28 Z"/><rect x="20" y="28" width="8" height="10"/>',
  down: '<path d="M12 20 L24 38 L36 20 Z"/><rect x="20" y="10" width="8" height="10"/>',
  target: '<path d="M24 10 A14 14 0 1 1 11 19" fill="none" stroke="currentColor" stroke-width="4"/><path d="M6 12 L16 14 L10 23 Z"/><circle cx="24" cy="24" r="4"/>',
  pause: '<rect x="12" y="13" width="24" height="4"/><rect x="12" y="22" width="24" height="4"/><rect x="12" y="31" width="24" height="4"/>',
};

// [action, CSS position]; anchored to the rendered stage, including letterboxing.
const LAYOUT = [
  ['left', 'left:5.5%;bottom:8%'],
  ['right', 'left:20.5%;bottom:3%'],
  ['target', 'left:1.5%;bottom:39%'],
  ['down', 'right:20.5%;bottom:3%'],
  ['up', 'right:5.5%;bottom:8%'],
  ['handb', 'right:1.5%;bottom:39%'],
  ['pause', 'right:1%;top:1%;width:var(--pause-size);height:var(--pause-size)'],
];

/** Put the buttons on the page, shown while `visible()` holds. Returns a function that removes them. */
export function mountTouchControls(visible = () => true) {
  const root = document.createElement('div');
  root.id = 'touch-controls';
  root.style.cssText = 'position:fixed;z-index:50;pointer-events:none;user-select:none;-webkit-user-select:none;';
  for (const [a, pos] of LAYOUT) {
    const b = document.createElement('div');
    b.dataset.touch = a;
    b.style.cssText = 'position:absolute;width:var(--touch-size);height:var(--touch-size);box-sizing:border-box;border-radius:calc(var(--touch-size) * .14);'
      + 'background:rgba(130,130,130,.38);border:2px solid rgba(255,255,255,.45);color:rgba(255,255,255,.9);filter:drop-shadow(0 0 .4vmin rgba(0,0,0,.6));'
      + 'display:flex;align-items:center;justify-content:center;pointer-events:auto;touch-action:none;'
      + `-webkit-touch-callout:none;${pos}`;
    if (a === 'handb') {
      const icon = document.createElement('img');
      icon.src = new URL('./handbrake-icon.png', import.meta.url).href;
      icon.alt = '';
      icon.draggable = false;
      icon.style.cssText = 'width:80%;height:80%;object-fit:contain;filter:brightness(0) invert(1);opacity:.9;pointer-events:none;';
      b.append(icon);
    } else b.innerHTML = `<svg viewBox="0 0 48 48" width="62%" height="62%" fill="currentColor">${ICON[a]}</svg>`;
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
    // Capture each finger independently; hit testing still allows sliding between buttons.
    e.target.closest('[data-touch]').setPointerCapture(e.pointerId);
    fingers.down(e.pointerId, a);
  };
  const onMove = (e) => fingers.move(e.pointerId, under(e));
  const onUp = (e) => fingers.up(e.pointerId);
  let raf = 0;
  const show = () => {
    const shown = visible() && !document.hidden;
    if (!shown) fingers.clear();
    root.style.display = shown ? '' : 'none';
    const rect = document.getElementById('stage').getBoundingClientRect();
    Object.assign(root.style, { left: rect.left + 'px', top: rect.top + 'px', width: rect.width + 'px', height: rect.height + 'px' });
    root.style.setProperty('--touch-size', Math.max(44, rect.height * .19) + 'px');
    root.style.setProperty('--pause-size', rect.height * .10 + 'px');
    raf = requestAnimationFrame(show);
  };
  show();
  root.addEventListener('pointerdown', onDown);
  root.addEventListener('contextmenu', (e) => e.preventDefault());
  const clear = () => fingers.clear();
  addEventListener('blur', clear);
  document.addEventListener('visibilitychange', clear);
  addEventListener('pointermove', onMove);
  addEventListener('pointerup', onUp);
  addEventListener('pointercancel', onUp);
  return () => {
    fingers.clear();
    cancelAnimationFrame(raf);
    removeEventListener('blur', clear);
    document.removeEventListener('visibilitychange', clear);
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

/** Explicit opt-in, independent of device detection and on-screen controls. */
export function easyStuntsEnabled() {
  try { return JSON.parse(localStorage.getItem('nfm.launcher') || '{}').easyStunts === true; }
  catch { return false; }
}

/** A completed click/tap on a finish screen feeds the same Control flag as Enter.
 * Require both ends of the gesture on that screen so lifting a race control
 * when the result appears cannot accidentally skip it. */
export function installFinishTap(stage, active, control) {
  const started = new Set();
  const down = (e) => { if (active()) started.add(e.pointerId); };
  const up = (e) => {
    if (started.delete(e.pointerId) && active()) { e.preventDefault(); control.enter = true; }
  };
  const cancel = (e) => started.delete(e.pointerId);
  stage.addEventListener('pointerdown', down);
  stage.addEventListener('pointerup', up);
  stage.addEventListener('pointercancel', cancel);
  return () => {
    started.clear();
    stage.removeEventListener('pointerdown', down);
    stage.removeEventListener('pointerup', up);
    stage.removeEventListener('pointercancel', cancel);
  };
}
