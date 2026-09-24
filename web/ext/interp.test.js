// The redraw between ticks (interp.js) saves and restores what drawing writes.
// Its field lists were read off the generated source; this re-reads it and
// fails when ContO.d, Plane.d or Medium.d -- or any method of their own class
// they reach -- assigns a field the lists do not cover, which would let a
// display-rate redraw change what the next tick simulates.

import { test } from 'node:test';
import assert from 'node:assert';
import fs from 'node:fs';
import { CONTO_SCALARS, CONTO_ARRAYS, PLANE_SCALARS, PLANE_ARRAYS, PLANE_TEMPORARIES, MEDIUM_WRITES, MEDIUM_APPENDED } from './interp.js';

const HERE = new URL('./', import.meta.url);

/** name -> body, for the class's top-level methods. */
function methods(file) {
  const out = new Map();
  let name = null, buf = [];
  for (const l of fs.readFileSync(new URL(file, HERE), 'utf8').split('\n')) {
    const m = /^ {2}\*?([A-Za-z_$][\w$]*)\(.*\) \{$/.exec(l);
    if (m) { if (name) out.set(name, buf.join('\n')); name = m[1]; buf = []; continue; }
    if (name) buf.push(l);
  }
  if (name) out.set(name, buf.join('\n'));
  return out;
}

/** Fields written on `this` by `starts` and every method of the class they call. */
function writes(file, starts) {
  const ms = methods(file);
  assert.ok(ms.has(starts[0]), `${file} has no ${starts[0]}()`);
  starts = starts.filter((m) => ms.has(m));
  const seen = new Set(starts), q = [...starts];
  while (q.length) {
    for (const x of (ms.get(q.pop()) || '').matchAll(/this\.([A-Za-z_$][\w$]*)\(/g)) {
      if (ms.has(x[1]) && !seen.has(x[1])) { seen.add(x[1]); q.push(x[1]); }
    }
  }
  const w = new Set();
  for (const m of seen) {
    const b = ms.get(m);
    for (const x of b.matchAll(/this\.([A-Za-z_$][\w$]*)(?:\[[^\]]*\])*\s*(?:=(?!=)|\+=|-=|\*=|\/=|\+\+|--)/g)) w.add(x[1]);
    for (const x of b.matchAll(/(?:\+\+|--)this\.([A-Za-z_$][\w$]*)/g)) w.add(x[1]);
  }
  return w;
}

const uncovered = (w, lists) => [...w].filter((f) => !lists.some((l) => l.includes(f))).sort();

test('what ContO.d writes is saved around a redraw', () => {
  assert.deepStrictEqual(uncovered(writes('ContO.js', ['d']), [CONTO_SCALARS, CONTO_ARRAYS]), []);
});

test('what Plane.d (and the Plane methods ContO.d calls) writes is saved or recomputed', () => {
  const w = writes('Plane.js', ['d', 's', 'recolour', 'py']);
  assert.deepStrictEqual(uncovered(w, [PLANE_SCALARS, PLANE_ARRAYS, PLANE_TEMPORARIES]), []);
});

test('what Medium.d and Medium.addsp write is saved', () => {
  assert.deepStrictEqual(uncovered(writes('Medium.js', ['d', 'addsp', 'redrawpolys']), [MEDIUM_WRITES, MEDIUM_APPENDED]), []);
});
