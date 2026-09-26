// The identity half of Extended's new cars (web/ext/newcars.js): every comparison of a
// car number against an integer literal asks id(car), so a new car answers as its
// donor; tables keep reading the real index. Stock cars: id(c) === c, same race.
// Car-vs-car comparisons (`sc[k] === sc[l]`) ask "same car?", an index question:
// they are left alone.
//
//   node web/tools/ext-ident.mjs           apply (after every J2JS regeneration)
//   node web/tools/ext-ident.mjs --check   exit 1 if anything is left unwrapped
import fs from 'node:fs';
import { pathToFileURL } from 'node:url';

export const FILES = ['Madness.js', 'xtGraphics.js', 'Control.js', 'GameSparker.js', 'Contva.js'];
const CAR = String.raw`(?:(?:this|madness|usermad|amadness\[[^\]]+\]|madness\[[^\]]+\])\.cn|(?:this|xtgraphics|this\.xt)\.sc\[[^\]]+\]|this\.lastcar)`;
// a car expression not already inside id(...) and not the tail of a longer name
// (`amadness[k].cn` must not also match as `madness[k].cn`), compared with an integer literal
export const RAW = new RegExp(String.raw`(?<!id\()(?<![\w.$])(${CAR})(\s*(?:===|!==|<=|>=|<|>)\s*-?\d+\b)`, 'g');
// Car numbers that travel as a plain parameter (audited 2026-09-26: the functions called
// with sc[..]/.cn whose parameter meets a literal). healthcalc's carid runs in the race;
// reqneed(b) and resetstats(a) are the career's levels and stats, where no new car goes.
const LOCALS = { 'xtGraphics.js': ['carid'] };
const local = (name) => new RegExp(String.raw`(?<!id\()(?<![\w.$])(${name})(\s*(?:===|!==|<=|>=|<|>)\s*-?\d+\b)`, 'g');
export const rawIn = (file, src) => (src.match(RAW)?.length ?? 0)
  + (LOCALS[file] ?? []).reduce((n, name) => n + (src.match(local(name))?.length ?? 0), 0);
const IMPORT = `import { id } from './newcars.js';   // ext-ident (web/tools/ext-ident.mjs)\n`;

export function rewrite(src, file) {
  let out = src.replace(RAW, 'id($1)$2');
  for (const name of LOCALS[file] ?? []) out = out.replace(local(name), 'id($1)$2');
  if (!out.includes(IMPORT)) out = out.replace(/^(import [^\n]*\n)/m, `$1${IMPORT}`);
  return out;
}

if (process.argv[1] && import.meta.url === pathToFileURL(process.argv[1]).href) {
  const check = process.argv[2] === '--check';
  let bad = 0;
  for (const f of FILES) {
    const url = new URL(`../ext/${f}`, import.meta.url);
    const src = fs.readFileSync(url, 'utf8');
    const left = rawIn(f, src);
    if (check) {
      if (left || !src.includes(IMPORT)) { console.log(`${f}: ${left} unwrapped`); bad++; }
      continue;
    }
    const out = rewrite(src, f);
    if (out !== src) fs.writeFileSync(url, out);
    console.log(`${f}: ${left} wrapped`);
  }
  process.exit(bad ? 1 : 0);
}
