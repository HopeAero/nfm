// Float/double audit, JS side: compares the port's fr() placement against
// what javac says the Java rounds. Input is FloatAudit.java's output (see the
// banner there for how to produce it).
//
//   node web/tools/float-audit.mjs /tmp/fa.jsonl [Class ...]
//
// Statements are paired per method by their canonical shape (an LCS over the
// method's statement list), so a port that reorders or rewrites a statement
// simply leaves it unpaired -- counted, never guessed at. For a paired
// statement, every canonical node is compared:
//   MISSING  Java rounds this node to float32 and the port does not
//   EXTRA    the port rounds a node that is DOUBLE in Java
// fr() on a node that is already float in Java is redundant, not wrong, and
// is not reported. int -> float conversions (exact below 2^24) are counted
// separately as `int2f` and not listed unless --int2f.
//
// Parses JS with the globally installed `typescript` (npm root -g); nothing
// is added to the project.

import { readFileSync, writeFileSync, existsSync } from 'node:fs';
import { createRequire } from 'node:module';
import { execSync } from 'node:child_process';
import { fileURLToPath } from 'node:url';
import path from 'node:path';

const root = execSync('npm root -g').toString().trim();
const ts = createRequire(path.join(root, 'noop.js'))('typescript');
const WEB = path.join(path.dirname(fileURLToPath(import.meta.url)), '..');

const args = process.argv.slice(2);
const showInt = args.includes('--int2f');
const showUnpaired = args.includes('--unpaired');
const showExact = args.includes('--exact');
const [input, ...only] = args.filter((a) => !a.startsWith('--'));
const javaRows = readFileSync(input, 'utf8').trim().split('\n').map((l) => JSON.parse(l));
// --bytecode=<verdicts.json> (web/tools/compound-verdicts.py): what each
// compound assignment really does. 'float'/'double' mean procyon's `x += (int)e`
// is really `x = (int)(x + e)` in that type; a line absent from the file
// compiled exactly as written.
const bcArg = args.find((a) => a.startsWith('--bytecode='));
const verdicts = bcArg ? JSON.parse(readFileSync(bcArg.slice(11), 'utf8')) : null;
if (verdicts) for (const r of javaRows) {
  if (!r.ca) continue;
  const v = verdicts[`${r.c}:${r.l}`];
  if (!v) { r.ca = false; r.bc = 'as written'; continue; }
  if (v.length !== 1 || v[0] === 'int') continue;          // ambiguous: leave it flagged
  // Node 0 is the synthetic `x op e`, node 1 is x, node ri is e's top.
  r.t = (v[0] === 'float' ? 'F' : 'D') + r.t.slice(1);
  r.r = r.r.filter((id) => id !== 0);
  if (v[0] === 'float') r.r.push(0);
  else {
    // In double the `(float)` procyon printed on e does not exist; the only
    // rounding is the narrowing store into a float x.
    r.r = r.r.filter((id) => id !== r.ri);
    if (r.t[1] === 'F') r.r.push(0);
  }
  r.ca = false; r.bc = v[0];
}

const jsFile = (c) => path.join(WEB, (c === 'xtGraphics' ? 'XtGraphics' : c) + '.js');

// Wrappers that are transparent in the canonical tree. `fr` marks rounding.
const ROUND = new Set(['fr', 'Math.fround']);
const TRANSPARENT = new Set(['trunc', 'i32']);
const AS_BINARY = { idiv: '/', ldiv: '/', 'Math.imul': '*', lmul: '*' };
const RENAME = { jround: 'Math.round', random: 'Math.random' };

function plainNum(txt) {
  const n = Number(txt.replace(/_/g, ''));
  if (Number.isInteger(n) && Math.abs(n) < 1e15) return String(n);
  let s = String(Number(n.toPrecision(6)));   // FloatAudit.java rounds to 6 digits too
  if (!/e/i.test(s)) return s;
  // 1e-7 -> 0.0000001, as BigDecimal.toPlainString prints it.
  const [m, e] = s.split(/e/i);
  const exp = parseInt(e, 10);
  const [ip, fp = ''] = m.replace('-', '').split('.');
  const digits = ip + fp;
  const point = ip.length + exp;
  const sign = m.startsWith('-') ? '-' : '';
  if (point <= 0) return sign + '0.' + '0'.repeat(-point) + digits;
  return sign + digits.padEnd(point, '0');
}

const opText = (k) => ts.tokenToString(k);

class Ser {
  constructor(sf) {
    this.sf = sf; this.next = 0; this.rounded = new Set(); this.intDiv = new Set();
    this.nodes = []; this.frOf = new Map();   // id -> JS node, id -> fr() calls around it (for --fix)
  }
  strip(n) { while (ts.isParenthesizedExpression(n) || ts.isAsExpression?.(n)) n = n.expression; return n; }
  chain(n) {
    if (ts.isIdentifier(n)) return n.text;
    if (ts.isPropertyAccessExpression(n)) {
      if (n.expression.kind === ts.SyntaxKind.ThisKeyword) return n.name.text;
      const h = this.chain(n.expression);
      return h == null ? null : h + '.' + n.name.text;
    }
    return null;
  }
  ser(n) {
    n = this.strip(n);
    if (ts.isCallExpression(n)) {
      const callee = this.chain(n.expression);
      if (callee && (ROUND.has(callee) || TRANSPARENT.has(callee)) && n.arguments.length === 1) {
        const id = this.next;
        const arg = this.strip(n.arguments[0]);
        // trunc(a / b) is Java's int division too (both truncate toward zero).
        if (callee === 'trunc' && ts.isBinaryExpression(arg) && arg.operatorToken.kind === ts.SyntaxKind.SlashToken) this.intDiv.add(id);
        const s = this.ser(n.arguments[0]);
        if (ROUND.has(callee)) {
          this.rounded.add(id);
          if (!this.frOf.has(id)) this.frOf.set(id, []);
          this.frOf.get(id).push(n);
        }
        return s;
      }
      if (callee && AS_BINARY[callee] && n.arguments.length === 2) {
        if (AS_BINARY[callee] === '/') this.intDiv.add(this.next);
        this.nodes[this.next++] = n;
        return '(' + this.ser(n.arguments[0]) + AS_BINARY[callee] + this.ser(n.arguments[1]) + ')';
      }
      this.nodes[this.next++] = n;
      let head;
      if (callee && RENAME[callee]) { this.nodes[this.next++] = n.expression; head = RENAME[callee]; } else head = this.ser(n.expression);
      return head + '(' + n.arguments.map((a) => this.ser(a)).join(',') + ')';
    }
    const c = this.chain(n);
    if (c != null) { this.nodes[this.next++] = n; return c; }
    this.nodes[this.next++] = n;
    if (ts.isBinaryExpression(n)) {
      let op = opText(n.operatorToken.kind);
      if (op === '===') op = '=='; else if (op === '!==') op = '!=';
      return '(' + this.ser(n.left) + op + this.ser(n.right) + ')';
    }
    if (ts.isPrefixUnaryExpression(n) || ts.isPostfixUnaryExpression(n)) {
      return opText(n.operator) + '(' + this.ser(n.operand) + ')';
    }
    if (ts.isNumericLiteral(n)) return plainNum(n.text);
    if (n.kind === ts.SyntaxKind.TrueKeyword) return 'true';
    if (n.kind === ts.SyntaxKind.FalseKeyword) return 'false';
    if (ts.isStringLiteral(n) || ts.isNoSubstitutionTemplateLiteral(n)) return 'str';
    if (ts.isElementAccessExpression(n)) return this.ser(n.expression) + '[' + this.ser(n.argumentExpression) + ']';
    if (ts.isPropertyAccessExpression(n)) return this.ser(n.expression) + '.' + n.name.text;
    if (ts.isConditionalExpression(n)) {
      return '(' + this.ser(n.condition) + '?' + this.ser(n.whenTrue) + ':' + this.ser(n.whenFalse) + ')';
    }
    return '?';
  }
}

const COMPOUND = {
  [ts.SyntaxKind.PlusEqualsToken]: '+', [ts.SyntaxKind.MinusEqualsToken]: '-',
  [ts.SyntaxKind.AsteriskEqualsToken]: '*', [ts.SyntaxKind.SlashEqualsToken]: '/',
  [ts.SyntaxKind.PercentEqualsToken]: '%',
};

/** Every numeric statement of a JS file, grouped by method, in source order. */
function jsStatements(file) {
  const sf = ts.createSourceFile(file, readFileSync(file, 'utf8'), ts.ScriptTarget.Latest, true);
  const byMethod = new Map();
  const floatArrays = new Set();
  const lineOf = (n) => sf.getLineAndCharacterOfPosition(n.getStart()).line + 1;
  let method = null;
  const push = (node, target, rhs, compound) => {
    if (!method) return;
    const s = new Ser(sf);
    let key;
    if (compound) {
      s.next++;
      const l = s.ser(target); const r = s.ser(rhs);
      key = new Ser(sf).ser(target) + '=(' + l + compound + r + ')';
    } else {
      key = (typeof target === 'string' ? target : new Ser(sf).ser(target)) + '=' + s.ser(rhs);
    }
    if (!byMethod.has(method)) byMethod.set(method, []);
    byMethod.get(method).push({ key, rounded: s.rounded, intDiv: s.intDiv, nodes: s.nodes, frOf: s.frOf, sf, line: lineOf(node), text: node.getText().replace(/\s+/g, ' '),
      target: typeof target === 'string' ? null : target });
  };
  const visit = (n) => {
    if (ts.isMethodDeclaration(n) || ts.isConstructorDeclaration(n)) {
      const prev = method;
      method = ts.isConstructorDeclaration(n) ? 'constructor' : n.name.getText();
      ts.forEachChild(n, visit);
      method = prev;
      return;
    }
    if (ts.isExpressionStatement(n)) {
      const e = n.expression;
      if (ts.isBinaryExpression(e) && e.operatorToken.kind === ts.SyntaxKind.EqualsToken) {
        push(n, e.left, e.right, null);
        const r = e.right;
        if (ts.isCallExpression(r) && r.expression.getText() === 'floatArray') floatArrays.add(new Ser(sf).ser(e.left));
      } else if (ts.isBinaryExpression(e) && COMPOUND[e.operatorToken.kind]) push(n, e.left, e.right, COMPOUND[e.operatorToken.kind]);
      else if (ts.isCallExpression(e)) push(n, 'call', e, null);
    } else if (ts.isVariableDeclaration(n) && n.initializer && ts.isIdentifier(n.name)) {
      push(n, n.name.text, n.initializer, null);
    } else if (ts.isReturnStatement(n) && n.expression) push(n, 'return', n.expression, null);
    else if (ts.isIfStatement(n)) push(n, 'if', n.expression, null);
    else if (ts.isWhileStatement(n)) push(n, 'while', n.expression, null);
    ts.forEachChild(n, visit);
  };
  visit(sf);
  return { byMethod, floatArrays };
}

/** LCS pairing of two key lists; returns [i, j] index pairs. */
function lcs(a, b) {
  const n = a.length, m = b.length;
  const dp = Array.from({ length: n + 1 }, () => new Int32Array(m + 1));
  for (let i = n - 1; i >= 0; i--) for (let j = m - 1; j >= 0; j--) {
    dp[i][j] = a[i] === b[j] ? dp[i + 1][j + 1] + 1 : Math.max(dp[i + 1][j], dp[i][j + 1]);
  }
  const out = [];
  for (let i = 0, j = 0; i < n && j < m;) {
    if (a[i] === b[j]) { out.push([i, j]); i++; j++; } else if (dp[i + 1][j] >= dp[i][j + 1]) i++; else j++;
  }
  return out;
}

const classes = [...new Set(javaRows.map((r) => r.c))].filter((c) => existsSync(jsFile(c)) && (!only.length || only.includes(c)));
let totals = { java: 0, paired: 0, missing: 0, extra: 0, int2f: 0, exact: 0 };
const findings = [];
for (const c of classes) {
  const { byMethod, floatArrays } = jsStatements(jsFile(c));
  const javaByMethod = new Map();
  for (const r of javaRows.filter((r) => r.c === c)) {
    if (!javaByMethod.has(r.m)) javaByMethod.set(r.m, []);
    javaByMethod.get(r.m).push(r);
  }
  let paired = 0, count = 0;
  const pairs = [];
  const usedJs = new Set();
  const leftover = [];
  for (const [m, jrows] of javaByMethod) {
    count += jrows.length;
    const js = byMethod.get(m) || [];
    const got = new Set();
    for (const [i, j] of lcs(jrows.map((r) => r.k), js.map((s) => s.key))) {
      pairs.push([jrows[i], js[j]]); usedJs.add(js[j]); got.add(i);
    }
    jrows.forEach((r, i) => { if (!got.has(i)) leftover.push(r); });
  }
  // Second pass: Java overloads the port split into differently named methods
  // (ContO's constructors -> #initBuf/#initModel/...). Pair what is left by
  // exact shape anywhere in the class.
  const pool = new Map();
  for (const list of byMethod.values()) for (const st of list) {
    if (usedJs.has(st)) continue;
    if (!pool.has(st.key)) pool.set(st.key, []);
    pool.get(st.key).push(st);
  }
  const still = [];
  for (const r of leftover) {
    const st = pool.get(r.k)?.shift();
    if (st) { pairs.push([r, st]); usedJs.add(st); } else still.push(r);
  }
  // Third pass: same method, in order, by shape with every name blanked -- a
  // local the port renamed (`random` -> `rnd`) is the usual reason left.
  // Types come from the Java side either way, so a rename cannot hide an error.
  const shape = (k) => k.replace(/[A-Za-z_$#][\w$.#]*/g, 'x');
  for (const [m, js] of byMethod) {
    const jl = still.filter((r) => r.m === m && !r.done);
    const sl = js.filter((st) => !usedJs.has(st));
    for (const [i, j] of lcs(jl.map((r) => shape(r.k)), sl.map((st) => shape(st.key)))) {
      pairs.push([jl[i], sl[j], true]); usedJs.add(sl[j]); jl[i].done = true;
    }
  }
  if (showUnpaired) for (const r of still) if (!r.done) console.log(`  unpaired ${c}.${r.m} java:${r.l}  ${r.k}`);
  {
    for (const [jr, st, fuzzy] of pairs) {
      paired++;
      const want = new Set(jr.r);
      for (const id of want) {
        if (st.rounded.has(id)) continue;
        const ty = jr.t[id];
        // A float result stored straight into a Float32Array rounds anyway.
        if (id === 0 && st.target && floatArrays.has(st.key.split('[')[0])) continue;
        if (ty === 'I' || ty === 'J') { totals.int2f++; if (!showInt) continue; }
        const g = jr.g?.[id];
        if (g === 'g' || g === 'x') { totals.exact++; if (!showExact) continue; }
        findings.push({ kind: 'MISSING', c, m: jr.m, id, ty, jl: jr.l, line: st.line, java: jr.s, js: st.text, ca: jr.ca, bc: jr.bc, st, fuzzy });
      }
      // Java int division the port wrote as a plain `/`: 185 / 2 is 92, not 92.5.
      for (const id of jr.d || []) {
        if (st.intDiv.has(id)) continue;
        findings.push({ kind: 'INTDIV', c, m: jr.m, id, ty: 'I', jl: jr.l, line: st.line, java: jr.s, js: st.text, ca: jr.ca, bc: jr.bc, st, fuzzy });
      }
      for (const id of st.rounded) {
        const ty = jr.t[id];
        if (want.has(id) || (ty !== 'D' && ty !== 'I' && ty !== 'J')) continue;
        // fr() of an int is exact below 2^24 and wrong above it: listed apart.
        findings.push({ kind: ty === 'D' ? 'EXTRA' : 'EXTRA-int', c, m: jr.m, id, ty, jl: jr.l, line: st.line, java: jr.s, js: st.text, ca: jr.ca, bc: jr.bc, st, fuzzy });
      }
    }
  }
  totals.java += count; totals.paired += paired;
  console.log(`${c.padEnd(12)} ${String(paired).padStart(5)}/${count} numeric statements paired`);
}
const stmts = new Map();
for (const f of findings) {
  const k = `${f.c}:${f.line}`;
  if (!stmts.has(k)) stmts.set(k, { ...f, kinds: [] });
  stmts.get(k).kinds.push(`${f.kind}@${f.id}`);
}
totals.missing = findings.filter((f) => f.kind === 'MISSING').length;
totals.extra = findings.filter((f) => f.kind === 'EXTRA').length;
console.log('\n' + JSON.stringify(totals), ` statements with findings: ${stmts.size}\n`);
const sorted = [...stmts.values()].sort((a, b) => (a.ca - b.ca));
let header = false;
for (const f of sorted) {
  if (f.ca && !header) {
    header = true;
    console.log('\n==== compound assignments: procyon misrenders their casts (TRANSPILE_SPEC 2); verify each against javap before acting ====\n');
  }
  console.log(`${f.c === 'xtGraphics' ? 'XtGraphics' : f.c}.js:${f.line}  (java ${f.jl}, ${f.m})  ${f.kinds.join(' ')}${f.ca ? '  [compound: check bytecode]' : ''}${f.fuzzy ? '  [paired by shape only]' : ''}${f.bc ? `  [bytecode: ${f.bc}]` : ''}`);
  console.log(`   java: ${f.java}`);
  console.log(`   js:   ${f.js}`);
}

// --fix: rewrite the port where the pairing is exact (not the shape-only third
// pass, and not compound assignments, which need javap). MISSING wraps the
// node in fr(), EXTRA/EXTRA-int unwraps the fr() around it, INTDIV turns the
// division into idiv(). Edits are applied by reprinting the AST bottom-up, so
// nested ones compose.
if (args.includes('--fix') || args.includes('--fix-shape')) {
  const byFile = new Map();
  for (const f of findings) {
    // --fix-shape also takes the shape-only pairs: review them in the report first.
    if (f.ca || (f.fuzzy && !args.includes('--fix-shape'))) continue;
    const e = byFile.get(f.st.sf) || { wrap: new Set(), unwrap: new Set(), idiv: new Set() };
    byFile.set(f.st.sf, e);
    if (f.kind === 'MISSING') e.wrap.add(f.st.nodes[f.id]);
    else if (f.kind === 'INTDIV') {
      const n = f.st.nodes[f.id];
      if (n && ts.isBinaryExpression(n)) e.idiv.add(n);
    } else for (const call of f.st.frOf.get(f.id) || []) e.unwrap.add(call);
  }
  const simple = (n) => ts.isIdentifier(n) || ts.isCallExpression(n) || ts.isPropertyAccessExpression(n)
    || ts.isElementAccessExpression(n) || ts.isNumericLiteral(n) || ts.isParenthesizedExpression(n);
  for (const [sf, e] of byFile) {
    const marked = new Set();
    for (const set of [e.wrap, e.unwrap, e.idiv]) for (let n of set) for (; n; n = n.parent) marked.add(n);
    const text = sf.text;
    const emit = (n) => {
      const start = n.getStart(sf);
      if (!marked.has(n)) return text.slice(start, n.end);
      if (e.unwrap.has(n)) {
        const a = n.arguments[0];
        const inner = emit(a);
        return simple(a) ? inner : '(' + inner + ')';
      }
      let out;
      if (e.idiv.has(n)) out = `idiv(${emit(n.left)}, ${emit(n.right)})`;
      else {
        const kids = [];
        ts.forEachChild(n, (k) => { kids.push(k); });
        out = '';
        let at = start;
        for (const k of kids) {
          const ks = k.getStart(sf);
          if (ks < at) continue;   // JSDoc and the like
          out += text.slice(at, ks) + emit(k);
          at = k.end;
        }
        out += text.slice(at, n.end);
      }
      return e.wrap.has(n) ? `fr(${out})` : out;
    };
    const full = text.slice(0, sf.getStart(sf)) + emit(sf);
    writeFileSync(sf.fileName, full);
    console.log(`fixed ${path.basename(sf.fileName)}: ${e.wrap.size} fr() added, ${e.unwrap.size} removed, ${e.idiv.size} idiv`);
  }
}
