// The stage maker page (web/stagemaker.html).
//
// The model is the stage file split the way StageMaker.readstage() splits it:
// `head` (sky, fog, laps, music ... every line that is not a part) and
// `parts` (sort.js readParts -- set/chk/fix/pile, the start piece first).
// Every edit rewrites the text through sortStage(), the transcribed
// StageMaker.sortstage(), so what is saved is what the original editor would
// have saved: parts in driving order, route points flagged, walls sized.
//
// The map draws the parts with the game's renderer from straight above
// (map.js). Parts are placed with the applet's snapping (place.js).

import { detectFpath } from '../vfs.js';
import { initPreview } from '../preview.js';
import { Graphics2D } from '../graphics.js';
import { random, trunc } from '../java.js';
import { translateDocument, tr } from '../i18n.js';
import { readParts, sortStage, PILE } from './sort.js';
import { snap, snapNear, problems, isCheckpoint } from './place.js';
import { CATEGORIES, DESCRIPTIONS } from './parts.js';
import { mountScenery } from './scenery.js';
import { MapView, partObject, drawParts } from './map.js';
import * as store from '../stagestore.js';
import { View3D } from './view3d.js';

const $ = (id) => document.getElementById(id);

let world = null;            // preview.js: models, medium, trackers
let rd = null;
const view = new MapView();
let head = [];
let scenery = null;          // scenery.js's panel, once the page is wired
let parts = [];
let objs = [];
let current = '';            // the stage's name, which is its key in the store
let dirty = false;
let sel = -1;                // selected part, in select mode
let tool = null;             // sp of the part being placed, or null = select
let toolRot = 0;
let ghost = null;            // { part, obj } under the cursor while placing
let category = CATEGORIES[0].key;
let v3 = null;               // View3D while the 3D view is on, else null
let hand = false;            // the hand tool: dragging always moves the view
const held = {};             // 3D view keys held: up/down/left/right/zoomi/zoomo

const maxR = (sp) => (sp === PILE ? 600 : world?.models[sp + 56]?.maxR ?? 1000);

// ---- the text ------------------------------------------------------------------

function newStageText() {
  // StageMaker.newstage (StageMaker.java): the applet's defaults.
  return ['snap(0,0,0)', 'sky(191,215,255)', 'clouds(255,255,255,5,-1000)', 'fog(195,207,230)',
    'ground(192,194,202)', 'texture(0,0,0,50)', 'fadefrom(5000)', 'density(5)',
    `mountains(${trunc(random() * 100000.0)})`, 'nlaps(5)', '', 'set(47,0,0,0)', ''].join('\r\n');
}

function laps() {
  const line = head.find((l) => l.startsWith('nlaps('));
  return line ? parseInt(line.slice(6), 10) || 5 : 5;
}

function setLaps(n) {
  n = Math.max(1, Math.min(15, n | 0));
  const i = head.findIndex((l) => l.startsWith('nlaps('));
  if (i === -1) head.push(`nlaps(${n})`);
  else head[i] = `nlaps(${n})`;
}

function textOf() {
  return head.join('\r\n') + '\r\n\r\n' + sortStage(parts.map((p) => ({ ...p })), maxR, world.medium);
}

/** The model changed: rewrite the text, rebuild the picture. */
function changed() {
  remember();
  $('code').value = textOf();
  markDirty(true);
  rebuild();
}

/** Load a stage's text. `typed`: an edit in the source box, which can be undone. */
function loadText(text, typed = false) {
  const r = readParts(text);
  head = r.head;
  parts = r.parts;
  sel = -1;
  $('laps').value = laps();
  scenery?.paint();
  if (typed) remember();
  else forget();
  rebuild();
}

// ---- undo ---------------------------------------------------------------------------

// Every edit ends in changed() (or a typed edit in loadText), which records
// the state it replaced. ponytail: whole-stage snapshots, 100 deep -- a
// stage is a few hundred parts, so a copy is cheap; diffs if that changes.
const undo = [];
const redo = [];
let last = null;             // the state now, as undo would restore it
const snapshot = () => JSON.stringify({ head, parts });

function remember() {
  const now = snapshot();
  if (last !== null && now !== last) {
    undo.push(last);
    if (undo.length > 100) undo.shift();
    redo.length = 0;
  }
  last = now;
  paintUndo();
}

function forget() {
  undo.length = 0;
  redo.length = 0;
  last = snapshot();
  paintUndo();
}

/** Undo: step(undo, redo). Redo: step(redo, undo). */
function step(from, to) {
  if (!from.length) return;
  to.push(last);
  last = from.pop();
  ({ head, parts } = JSON.parse(last));
  sel = -1;
  ghost = null;
  $('laps').value = laps();
  scenery?.paint();
  $('code').value = textOf();
  markDirty(true);
  rebuild();
  paintUndo();
}

function paintUndo() {
  $('undo').disabled = !undo.length;
  $('redo').disabled = !redo.length;
}

// ---- the picture ----------------------------------------------------------------

function applySnap() {
  // The stage's colour shift tints every part; loadstage applies it the same way.
  const line = head.find((l) => l.startsWith('snap('));
  if (!line) { world.medium.setsnap(0, 0, 0); return; }
  const v = line.slice(5, line.indexOf(')')).split(',').map((s) => parseInt(s, 10) || 0);
  world.medium.setsnap(v[0], v[1], v[2]);
}

function rebuild() {
  applySnap();
  for (const p of parts) p.maxR = maxR(p.sp);
  objs = parts.map((p) => partObject(p, world.models, world.medium, world.trackers));
  facts();
  if (v3) build3d();
  render();
}

// ---- the 3D view ----------------------------------------------------------------

function build3d() {
  try {
    v3.build(textOf());
  } catch (e) {
    $('err').textContent = String(e.message || e);
  }
}

function set3d(on) {
  if (on === !!v3) return;
  if (on) {
    v3 = new View3D(world);
    build3d();
    // Frame the whole stage, looking at its middle.
    let x0 = Infinity, x1 = -Infinity, z0 = Infinity, z1 = -Infinity;
    for (const p of parts) { x0 = Math.min(x0, p.x); x1 = Math.max(x1, p.x); z0 = Math.min(z0, p.z); z1 = Math.max(z1, p.z); }
    if (parts.length) v3.home((x0 + x1) / 2, (z0 + z1) / 2, Math.max(x1 - x0, z1 - z0, 12000));
  } else {
    v3.restore();
    v3 = null;
    for (const k in held) held[k] = false;
    rebuild();
  }
  ghost = null;
  $('view3d').setAttribute('aria-pressed', String(!!v3));
  paintTools();
  $('mapnote').hidden = !!v3;
  $('note3d').hidden = !v3;
  render();
}

function paintTools() {
  $('selecttool').setAttribute('aria-pressed', String(!hand && tool === null));
  $('handtool').setAttribute('aria-pressed', String(hand));
  $('viewbox').classList.toggle('hand', hand);
}

// Keys move the 3D camera while held, one step per 40 ms.
setInterval(() => {
  if (!v3 || !Object.values(held).some(Boolean)) return;
  if (held.left) v3.orbit(-3, 0);
  if (held.right) v3.orbit(3, 0);
  if (held.up) v3.pan(0, 20);
  if (held.down) v3.pan(0, -20);
  if (held.zoomi) v3.zoom(1 / 1.05);
  if (held.zoomo) v3.zoom(1.05);
  render();
}, 40);

const KEYS3D = {
  ArrowUp: 'up', ArrowDown: 'down', ArrowLeft: 'left', ArrowRight: 'right', '+': 'zoomi', '-': 'zoomo',
};

// Screen position of a ground point, and how many pixels a world unit is
// there -- the map's or the 3D camera's.
function screenOf(x, z, y = 250) {
  if (!v3) return { ...view.toScreen(x, z), k: view.scale };
  const s = v3.project(x, y, z);
  return s.d > 50 ? { x: s.x, y: s.y, k: 400 / s.d } : null;
}

function outline(p, color) {
  // At the part's height: the fixing hoop and the raised checkpoint float.
  const s = screenOf(p.x, p.z, p.sp === 31 || p.sp === 54 ? p.y : 250);
  if (!s) return;
  const r = (p.maxR ?? maxR(p.sp)) * 0.72 * s.k;
  rd.setColor(...color);
  rd.drawRect(s.x - r, s.y - r, 2 * r, 2 * r);
}

function overlay() {
  if (ghost?.obj) {
    ghost.obj.d(rd);
    outline(ghost.part, [255, 210, 0]);
  }
  if (sel >= 0 && parts[sel]) outline(parts[sel], [255, 128, 0]);
  // Checkpoint order, as the race will count them.
  rd.setFont('Arial', 1, 12);
  let n = 0;
  for (const p of parts) {
    if (!isCheckpoint(p.sp)) continue;
    ++n;
    const s = screenOf(p.x, p.z);
    if (!s) continue;
    rd.setColor(255, 255, 255);
    rd.drawString(`${n}`, s.x - 3, s.y + 4);
  }
  const s = parts[0] && screenOf(parts[0].x, parts[0].z);
  if (s) {
    rd.setColor(255, 210, 0);
    rd.drawString('START', s.x - 18, s.y - 10);
  }
}

function render() {
  if (!rd) return;
  paintSel();
  if (v3) { v3.draw(rd, objs, overlay); return; }
  view.camera(world.medium);
  rd.begin();
  rd.setColor(28, 32, 40);
  rd.fillRect(0, 0, 800, 450);
  drawParts(rd, objs);
  overlay();
  rd.end();
}

function facts() {
  $('nparts').textContent = parts.length;
  $('nchecks').textContent = parts.filter((p) => isCheckpoint(p.sp)).length;
  $('nhoops').textContent = parts.filter((p) => p.sp === 31).length;
  const issues = problems(parts);
  $('err').textContent = issues.join('\n');
  $('ready').innerHTML = issues.length ? `<span class="notok">${tr('not raceable yet')}</span>`
    : `<b class="ok">${tr('ready to race')}</b>`;
  $('drive').disabled = issues.length > 0;
}

// ---- the selected part --------------------------------------------------------------

const PART_NAMES = new Map(CATEGORIES.flatMap((c) => c.parts.map((p) => [p.sp, p.name])));
PART_NAMES.set(32, 'Checkpoint');
PART_NAMES.set(54, 'Checkpoint (raised)');
PART_NAMES.set(66, 'Ground Pile');

// What the stage file can hold for a part is all this can edit: set() is
// id, x, z, rotation -- the game stands every such part on the ground -- and
// only the fixing hoop (fix), the raised checkpoint (chk 64) and the ground
// pile (its size) carry more. Heights show as positive up; the file's y is
// negative up.
function paintSel() {
  const p = parts[sel];
  $('selbox').hidden = !p;
  if (!p || $('selbox').contains(document.activeElement)) return;
  $('selname').textContent = tr(PART_NAMES.get(p.sp) ?? `Part ${p.sp}`);
  $('selx').value = p.x;
  $('selz').value = p.z;
  $('selrot').value = p.rot;
  const hoop = p.sp === 31;
  const check = isCheckpoint(p.sp);
  const pile = p.sp === 66;
  $('selhrow').hidden = !hoop && !check;
  $('sely').value = hoop || p.sp === 54 ? -p.y : 0;
  $('selprow').hidden = !pile;
  if (pile) { $('selw').value = p.srx; $('sell').value = p.sry; }
  $('selnote').textContent = tr(hoop ? 'Height above the ground, at least 500. Cars fly through it to get fixed.'
    : check ? 'Height 0 keeps it on the road. Above 0 it becomes the raised checkpoint the original editor makes, floating at that height.'
      : pile ? 'Width and length from 2 to 6, as in the original editor.'
        : 'The game always stands this part on the ground: the stage file keeps no height for it, so neither the race nor the desktop game could use one.');
}

function applySel() {
  const p = parts[sel];
  if (!p) return;
  const num = (id, d) => { const v = parseInt($(id).value, 10); return Number.isFinite(v) ? v : d; };
  p.x = num('selx', p.x);
  p.z = num('selz', p.z);
  let r = ((num('selrot', p.rot) % 360) + 360) % 360;
  if (r > 180) r -= 360;
  p.rot = r;
  if (p.sp === 31) p.y = -Math.max(500, num('sely', -p.y));
  else if (isCheckpoint(p.sp)) {
    const h = Math.max(0, num('sely', 0));
    if (h > 0) { p.sp = 54; p.y = -h; }
    else if (p.sp === 54) {
      // Back on the ground: asphalt or dirt, from the road under it.
      p.sp = snap(parts.filter((q) => q !== p), 30, p.x, p.z, p.rot, maxR, world.medium).sp;
      p.y = 0;
    }
  }
  if (p.sp === 66) {
    p.srx = Math.max(2, Math.min(6, num('selw', p.srx)));
    p.sry = Math.max(2, Math.min(6, num('sell', p.sry)));
  }
  document.activeElement?.blur?.();
  changed();
}

// ---- the palette ------------------------------------------------------------------

function paintPalette() {
  $('cats').innerHTML = '';
  for (const c of CATEGORIES) {
    const b = document.createElement('button');
    b.type = 'button';
    b.setAttribute('role', 'tab');
    b.setAttribute('aria-selected', String(c.key === category));
    b.textContent = c.label;
    b.onclick = () => { category = c.key; paintPalette(); };
    $('cats').appendChild(b);
  }
  $('palette').innerHTML = '';
  for (const p of CATEGORIES.find((c) => c.key === category).parts) {
    const b = document.createElement('button');
    b.type = 'button';
    b.textContent = p.name;
    b.setAttribute('aria-pressed', String(tool === p.sp));
    b.onclick = () => pickTool(tool === p.sp ? null : p.sp);
    $('palette').appendChild(b);
  }
  const d = tool !== null ? DESCRIPTIONS[tool] : '';
  $('partdesc').textContent = d || '';
  paintTools();
}

function pickTool(sp) {
  hand = false;
  tool = sp;
  ghost = null;
  if (sp !== null) sel = -1;
  paintPalette();
  render();
}

// ---- the map's input ----------------------------------------------------------------

function gamePoint(e) {
  const c = $('map');
  const r = c.getBoundingClientRect();
  return { x: ((e.clientX - r.left) / r.width) * 800, y: ((e.clientY - r.top) / r.height) * 450 };
}

// The applet's pull is 200 world units, but its part follows the mouse and
// snaps the moment it passes within 200 of an end; a click has to land there
// in one go, and 200 units is ~4 pixels at a normal zoom. So the pull is 16
// SCREEN pixels, whatever the zoom -- where the part lands is still the
// applet's: exactly end to end.
const pull = () => Math.max(200, 16 * (v3 ? v3.unitsPerPixel : 1 / view.scale));

// Snapping on or off (the checkbox; Alt held flips it for one placement or
// drag). Off, a part goes exactly where it is put, turned as it is -- a
// checkpoint still takes the asphalt or dirt type of the road under it,
// since that is which part it is, not where.
let snapOn = (() => { try { return localStorage.getItem('nfm.sm.snap') !== '0'; } catch { return true; } })();

function snapAt(others, sp, x, z, rot, e) {
  const s = snapNear(others, sp, x, z, rot, maxR, world.medium, pull());
  if (snapOn !== !!e?.altKey) return s;
  return { sp: s.sp, x: Math.round(x), z: Math.round(z), rot };
}

function placedAt(w, e) {
  const s = snapAt(parts, tool, w.x, w.z, toolRot, e);
  const part = { sp: s.sp, x: s.x, z: s.z, rot: s.rot, wh: 0, y: 0 };
  if (part.sp === 31) part.y = -Math.max(500, parseInt($('hoopy').value, 10) || 2000);
  return part;
}

// The part under the cursor. Where parts overlap the smallest wins -- a
// fixing hoop or a checkpoint over the road it sits on -- then the nearest.
// In 3D a part is found where it is drawn, at its height (a hoop floats),
// not at the ground point behind it.
function pickAt(g, w) {
  let best = -1, bestR = Infinity, bestD = Infinity;
  parts.forEach((p, i) => {
    const r = (p.maxR ?? maxR(p.sp)) * 0.75;
    let d, lim;
    if (v3) {
      const s = v3.project(p.x, objs[i]?.y ?? 250, p.z);
      if (s.d <= 50) return;
      d = Math.hypot(s.x - g.x, s.y - g.y);
      lim = Math.max(10, (r * 400) / s.d);
    } else {
      if (!w) return;
      d = Math.hypot(p.x - w.x, p.z - w.z);
      lim = Math.max(r, 10 / view.scale);
    }
    if (d >= lim) return;
    if (r < bestR || (r === bestR && d < bestD)) { best = i; bestR = r; bestD = d; }
  });
  return best;
}

let drag = null;             // { mode: 'pan' | 'move', ... }

function installMapInput() {
  const c = $('map');
  // The world point under the cursor: on the map, or on the 3D ground.
  const worldAt = (g) => (v3 ? v3.toGround(g.x, g.y) : view.toWorld(g.x, g.y));
  // Moving the view: the map pans; the 3D view orbits, or slides with Shift
  // or the right button.
  const viewDrag = (e, g) => ({ mode: v3 && !e.shiftKey && e.button === 0 ? 'orbit' : 'pan', g, vx: view.x, vz: view.z });
  c.addEventListener('contextmenu', (e) => e.preventDefault());
  c.addEventListener('pointerdown', (e) => {
    c.focus();
    c.setPointerCapture(e.pointerId);
    const g = gamePoint(e);
    const w = worldAt(g);
    if (e.button === 2 || e.button === 1 || hand || e.shiftKey || (!w && tool !== null)) {
      drag = viewDrag(e, g);
      $('viewbox').classList.add('panning');
      return;
    }
    if (tool !== null) {
      const part = placedAt(w, e);
      parts.push(part);
      changed();
      return;
    }
    const i = pickAt(g, w);
    sel = i;
    if (i >= 0 && w) drag = { mode: 'move', i, dx: parts[i].x - w.x, dz: parts[i].z - w.z, moved: false };
    else if (i < 0) {
      drag = viewDrag(e, g);
      $('viewbox').classList.add('panning');
    }
    render();
  });
  c.addEventListener('pointermove', (e) => {
    const g = gamePoint(e);
    if (v3 && (drag?.mode === 'orbit' || drag?.mode === 'pan')) {
      const dx = g.x - drag.g.x, dy = g.y - drag.g.y;
      drag.g = g;
      if (drag.mode === 'orbit') v3.orbit(dx * 0.4, dy * 0.3);
      else v3.pan(-dx, dy);
      render();
      return;
    }
    if (drag?.mode === 'pan') {
      view.x = drag.vx - (g.x - drag.g.x) / view.scale;
      view.z = drag.vz + (g.y - drag.g.y) / view.scale;
      render();
      return;
    }
    const w = worldAt(g);
    if (!w) return;
    if (drag?.mode === 'move') {
      const p = parts[drag.i];
      const others = parts.filter((_, k) => k !== drag.i);
      const s = snapAt(others, p.sp, w.x + drag.dx, w.z + drag.dz, p.rot, e);
      p.x = s.x; p.z = s.z;
      if (isCheckpoint(p.sp)) { p.sp = s.sp; p.rot = s.rot; }
      drag.moved = true;
      objs[drag.i] = partObject(p, world.models, world.medium, world.trackers);
      render();
      return;
    }
    if (tool !== null && !hand) {
      const part = placedAt(w, e);
      ghost = { part, obj: partObject(part, world.models, world.medium, world.trackers) };
      render();
    }
  });
  const end = () => {
    if (drag?.mode === 'move' && drag.moved) changed();
    drag = null;
    $('viewbox').classList.remove('panning');
  };
  c.addEventListener('pointerup', end);
  c.addEventListener('pointercancel', end);
  c.addEventListener('pointerleave', () => { if (ghost) { ghost = null; render(); } });
  c.addEventListener('wheel', (e) => {
    e.preventDefault();
    if (v3) v3.zoom(e.deltaY < 0 ? 1 / 1.15 : 1.15);
    else {
      const g = gamePoint(e);
      view.zoomAt(g.x, g.y, e.deltaY < 0 ? 1.15 : 1 / 1.15);
    }
    render();
  }, { passive: false });
}

function rotate() {
  if (tool !== null) {
    toolRot = (toolRot + 90) % 360;
    if (toolRot > 180) toolRot -= 360;
    ghost = null;
    render();
    return;
  }
  if (sel < 0) return;
  const p = parts[sel];
  p.rot += 90;
  if (p.rot > 180) p.rot -= 360;
  changed();
}

function removeSelected() {
  if (sel < 0) return;
  if (sel === 0) { say(tr('The start piece cannot be deleted; move it instead.')); return; }
  parts.splice(sel, 1);
  sel = -1;
  changed();
}

function installKeys() {
  addEventListener('keydown', (e) => {
    const t = e.target;
    if (t && (t.tagName === 'TEXTAREA' || t.tagName === 'INPUT' || t.tagName === 'SELECT')) return;
    if (e.ctrlKey || e.metaKey) {
      const key = e.key.toLowerCase();
      if (key === 'z' && !e.shiftKey) { step(undo, redo); e.preventDefault(); }
      else if (key === 'y' || (key === 'z' && e.shiftKey)) { step(redo, undo); e.preventDefault(); }
      return;
    }
    const k = v3 && KEYS3D[e.key];
    if (k) { held[k] = true; e.preventDefault(); return; }
    if (e.key === 'r' || e.key === 'R') { rotate(); e.preventDefault(); }
    if (e.key === 'Delete' || e.key === 'Backspace') { removeSelected(); e.preventDefault(); }
    if (e.key === 'Escape') {
      // Back to Select; from Select in 3D, back to the map.
      if (v3 && tool === null && !hand && sel < 0) { set3d(false); return; }
      pickTool(null); sel = -1; render();
    }
  });
  addEventListener('keyup', (e) => { const k = KEYS3D[e.key]; if (k) held[k] = false; });
  addEventListener('blur', () => { for (const k in held) held[k] = false; });
}

// ---- files --------------------------------------------------------------------------

function say(msg) { $('status').textContent = msg; }

function markDirty(d) {
  dirty = d;
  $('save').textContent = d ? tr('Save •') : tr('Save');
}

async function refreshPicker() {
  const names = await store.listAll();
  const stored = await store.listStored();
  const pick = $('pick');
  pick.innerHTML = '';
  for (const n of names) {
    const o = document.createElement('option');
    o.value = n;
    o.textContent = stored.includes(n) ? n : `${n} (shipped)`;
    pick.appendChild(o);
  }
  if (current && names.includes(current)) pick.value = current;
  $('del').disabled = !current || !stored.includes(current);
}

async function open(name) {
  const text = await store.readStage(name);
  if (text === null) { say(tr(`no such stage: ${name}`)); return; }
  current = name;
  $('stagename').textContent = name;
  $('code').value = text;
  loadText(text);
  view.fit(parts, maxR);
  render();
  markDirty(false);
  await refreshPicker();
  history.replaceState(null, '', `?stage=${encodeURIComponent(name)}`);
}

async function saveAs(name) {
  if (!name) return false;
  try {
    await store.saveStage(name, $('code').value);
  } catch (e) {
    say(String(e.message || e));
    return false;
  }
  current = name;
  $('stagename').textContent = name;
  markDirty(false);
  say(tr(`saved ${name}`));
  await refreshPicker();
  history.replaceState(null, '', `?stage=${encodeURIComponent(name)}`);
  return true;
}

function installFiles() {
  $('pick').onchange = () => {
    if (dirty && !confirm(tr('Discard your unsaved changes?'))) { $('pick').value = current; return; }
    open($('pick').value);
  };
  $('save').onclick = async () => {
    if (!current || store.SHIPPED.includes(current) && !(await store.listStored()).includes(current)) {
      const n = prompt(tr('Save as:'), current || tr('My Stage'));
      if (n) await saveAs(n.trim());
      return;
    }
    await saveAs(current);
  };
  $('saveas').onclick = async () => {
    const n = prompt(tr('Save as:'), current ? tr(`${current} copy`) : tr('My Stage'));
    if (n) await saveAs(n.trim());
  };
  $('new').onclick = async () => {
    if (dirty && !confirm(tr('Discard your unsaved changes?'))) return;
    const n = prompt(tr('Name for the new stage:'));
    if (!n) return;
    const text = newStageText();
    current = n.trim();
    $('code').value = text;
    loadText(text);
    view.fit(parts, maxR);
    render();
    await saveAs(current);
  };
  $('del').onclick = async () => {
    if (!current || !confirm(tr(`Delete "${current}"?`))) return;
    await store.deleteStage(current);
    say(tr(`deleted ${current}`));
    current = '';
    const names = await store.listAll();
    if (names.length) await open(names[0]);
    else await refreshPicker();
  };
  $('importbtn').onclick = () => $('file').click();
  $('file').onchange = async () => {
    const f = $('file').files[0];
    if (!f) return;
    const text = await f.text();
    const name = f.name.replace(/\.txt$/i, '');
    $('code').value = text;
    loadText(text);
    view.fit(parts, maxR);
    render();
    await saveAs(name);
    $('file').value = '';
  };
  $('export').onclick = () => {
    const blob = new Blob([$('code').value], { type: 'text/plain' });
    const a = document.createElement('a');
    a.href = URL.createObjectURL(blob);
    a.download = `${current || 'stage'}.txt`;
    a.click();
    setTimeout(() => URL.revokeObjectURL(a.href), 1000);
  };
  let typing = 0;
  $('code').addEventListener('input', () => {
    markDirty(true);
    clearTimeout(typing);
    typing = setTimeout(() => loadText($('code').value, true), 350);
  });
  $('laps').addEventListener('change', () => { setLaps(parseInt($('laps').value, 10)); changed(); });
  scenery = mountScenery($('scenery'), () => head, changed);
  for (const id of ['selx', 'selz', 'selrot', 'sely', 'selw', 'sell']) $(id).addEventListener('change', applySel);
  $('drive').onclick = async () => {
    if (problems(parts).length) return;
    if (dirty || !current) {
      const ok = current ? await saveAs(current) : await saveAs((prompt(tr('Save as:'), tr('My Stage')) || '').trim());
      if (!ok) return;
    }
    location.href = `./main.html?mystage=${encodeURIComponent(current)}&from=stagemaker`;
  };
  $('selecttool').onclick = () => pickTool(null);
  $('rotate').onclick = rotate;
  $('remove').onclick = removeSelected;
  $('fit').onclick = () => { if (v3) { v3.restore(); v3 = null; set3d(true); return; } view.fit(parts, maxR); render(); };
  $('view3d').onclick = () => set3d(!v3);
  $('snapon').checked = snapOn;
  $('snapon').onchange = () => {
    snapOn = $('snapon').checked;
    try { localStorage.setItem('nfm.sm.snap', snapOn ? '1' : '0'); } catch { /* private mode */ }
    ghost = null;
    render();
  };
  $('undo').onclick = () => step(undo, redo);
  $('redo').onclick = () => step(redo, undo);
  $('handtool').onclick = () => { pickTool(null); hand = true; paintTools(); };
  addEventListener('beforeunload', (e) => { if (dirty) { e.preventDefault(); e.returnValue = ''; } });
}

// ---- boot ---------------------------------------------------------------------------

(async () => {
  translateDocument();
  try {
    await detectFpath();
    world = await initPreview();
    const overlay = $('mapover');
    rd = new Graphics2D($('map'), overlay, 800, 450, { clear: true });
    installMapInput();
    installKeys();
    installFiles();
    paintPalette();
    // ?debug=1: the editor's state on window, for driving it from a test.
    if (new URLSearchParams(location.search).get('debug') === '1') {
      window.__sm = { view, world, get parts() { return parts; }, get sel() { return sel; }, get v3() { return v3; }, render };
    }
    const wanted = new URLSearchParams(location.search).get('stage');
    const names = await store.listAll();
    await open(wanted && names.includes(wanted) ? wanted : names[0]);
  } catch (e) {
    say('failed to start: ' + (e.message || e));
    console.error(e);
  }
})();
