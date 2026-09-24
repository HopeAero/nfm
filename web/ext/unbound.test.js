// Every Java class the generated code names must be imported (from jawt.js or a
// game class). J2JS emits a class it does not know as a bare identifier, and a
// ReferenceError outside the game's own try blocks stops GameSparker.run() --
// `java.util.Arrays` in Medium.drawstars froze every night stage (10, 14) that way.

import { test } from 'node:test';
import assert from 'node:assert';
import fs from 'node:fs';

const HERE = new URL('./', import.meta.url);
const JS_GLOBALS = new Set(['Math', 'Number', 'Object', 'Array', 'String', 'JSON', 'Symbol', 'Error', 'Promise',
  'Boolean', 'Infinity', 'NaN', 'Map', 'Set', 'Reflect', 'ArrayBuffer',
  'Int8Array', 'Int16Array', 'Uint16Array', 'Int32Array', 'Uint8Array', 'Float32Array', 'Float64Array']);
// ponytail: sound is not ported yet; these are reached only by the audio classes' own paths
const AUDIO = new Set(['LoadMod', 'FOURCC', 'PausablePlayer', 'OggClip', 'MidiSystem', 'BufferedInputStream',
  'AudioPlayer', 'DataLine', 'SourceDataLine', 'AudioFormat', 'Encoding', 'AudioSystem']);

const generated = fs.readdirSync(HERE).filter((f) => f.endsWith('.js') && fs.readFileSync(new URL(f, HERE), 'utf8').startsWith('// GENERATED'));

test('generated classes import every Java class they name', () => {
  const missing = [];
  for (const f of generated) {
    const src = fs.readFileSync(new URL(f, HERE), 'utf8');
    const known = new Set([...src.matchAll(/import \{([^}]*)\}/g)].flatMap((m) => m[1].split(',').map((x) => x.trim())));
    for (const m of src.matchAll(/(?:class|function|const|let) ([A-Z]\w*)/g)) known.add(m[1]);
    const code = src.replace(/\/\/.*$/gm, '').replace(/'(?:[^'\\]|\\.)*'/g, "''").replace(/`(?:[^`\\]|\\.)*`/g, '``');
    for (const m of code.matchAll(/(?<![\w.$])([A-Z][A-Za-z0-9_]*)(?=\s*[.(])/g)) {
      const n = m[1];
      if (!JS_GLOBALS.has(n) && !known.has(n) && !AUDIO.has(n)) missing.push(`${f}: ${n}`);
    }
  }
  assert.ok(generated.length > 20, `found ${generated.length} generated files`);
  assert.deepStrictEqual([...new Set(missing)], []);
});
