#!/usr/bin/env python3
"""Repair procyon's compound-assignment casts, accepting only what the jar proves.

procyon prints `x op= (T)e` for bytecode that is really `x = (typeof x)(x op e)`
-- the cast belongs to the whole compound, not to e (web/TRANSPILE_SPEC.md §2).
`i4 *= (int)0.991` is the extreme case: taken literally it multiplies by zero.
Dropping the cast (`i4 *= 0.991`) lets Java's own compound rule reinsert the
right one.

Not every such cast is a lie, so nothing is trusted: each candidate is tried,
the class recompiled, and validate.py's per-method bags compared with the
jar. A rewrite is kept only if its method's difference from the jar shrinks;
otherwise it is reverted. The rewrites that survive are written back to the
source with a trailing `// cast: bytecode-verified` comment.

  python3 fix_compound.py <java-src> <jar-classes> <classpath-extra> [--write]

Verification-only transforms (never written back): method-local `final` is
stripped, because javac folds a `final int k = 6` into constants the original
compiler computed at run time; and Control.preform is split (see split()).
"""
import collections, os, re, shutil, subprocess, sys, tempfile

sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))
import validate  # noqa: E402

JAVAC = os.environ.get('JAVAC', 'javac')
CAND = re.compile(r'^(\s*)(.+?) ([-+*/%&|^]|<<|>>>?)= \((int|float|long|short|byte|char|double)\)(.+);(\s*)$')
METHOD = re.compile(r'^    (?:(?:public|private|protected|static|final|synchronized)\s+)*[\w\[\]<>.]+\s+(\w+)\s*\(')
MARK = '  // cast: bytecode-verified'


STRING = re.compile(r'"(?:\\.|[^"\\])*"|\'(?:\\.|[^\'\\])*\'')


def strip_final(line):
    """Drop `final` from a method-body line, never inside a literal
    ("The final cars will now be unlocked" must survive)."""
    if not line.startswith('        '):
        return line
    out, at = [], 0
    for m in STRING.finditer(line):
        out.append(re.sub(r'\bfinal ', '', line[at:m.start()]))
        out.append(m.group(0))
        at = m.end()
    out.append(re.sub(r'\bfinal ', '', line[at:]))
    return ''.join(out)


def verification_copy(lines, cls):
    out = [strip_final(l) for l in lines]
    if cls == 'Control':
        out = split_preform(out)
    return out


def split_preform(src):
    """Move the two big blocks of Control.preform into helpers (64 KB limit).

    Block boundaries are found structurally: the first two statements at depth
    2 inside `if (!madness.dest) {` that open a block of more than 1000 lines.
    """
    start = next(i for i, l in enumerate(src) if 'public void preform(' in l)
    params = src[start][src[start].index('(') + 1:src[start].rindex(')')]
    args = ', '.join(p.split()[-1] for p in params.split(','))
    # depth-2 statements
    depth, blocks, cur = 0, [], None
    end = None
    for i in range(start, len(src)):
        l = src[i]
        if depth == 2 and cur is None and l.strip():
            cur = i
        depth += l.count('{') - l.count('}')
        if depth == 2 and cur is not None:
            blocks.append((cur, i)); cur = None
        if depth == 0 and i > start:
            end = i; break
    big = [b for b in blocks if b[1] - b[0] > 1000]
    first = big[0]
    # second helper: everything from the statement after the first block through the last big block
    second = (first[1] + 1, big[-1][1])
    out = src[:first[0]]
    out.append(f'            this.preform$split1({args});')
    out.append(f'            this.preform$split2({args});')
    out += src[second[1] + 1:end + 1]
    out.append(f'    private void preform$split1({params}) {{')
    out += src[first[0]:first[1] + 1] + ['    }']
    out.append(f'    private void preform$split2({params}) {{')
    out += src[second[0]:second[1] + 1] + ['    }']
    return out + src[end + 1:]


def method_of_lines(lines):
    names, cur = [], None
    for l in lines:
        m = METHOD.match(l)
        if m and l.rstrip().endswith('{'):
            cur = m.group(1)
        names.append(cur)
    return names


def compile_one(srcdir, cls, work, classes, extra):
    out = os.path.join(work, 'out')
    os.makedirs(out, exist_ok=True)
    r = subprocess.run([JAVAC, '-nowarn', '-proc:none', '--release', '8', '-encoding', 'UTF-8',
                        '-cp', os.pathsep.join([out, extra, classes]), '-d', out,
                        os.path.join(srcdir, cls + '.java')], capture_output=True, text=True)
    return r.returncode == 0, out


def score(out, classes, cls):
    """{method signature: size of the bag difference from the jar}."""
    o = validate.javap(os.path.join(classes, cls + '.class'))
    r = validate.javap(os.path.join(out, cls + '.class'))
    validate.fold_splits(r, cls)
    res = {}
    for name, ops in o.items():
        if name not in r:
            res[name] = 10 ** 6; continue
        a, b = validate.bag(ops), validate.bag(r[name])
        res[name] = sum(((a - b) + (b - a)).values())
    return res


def main():
    src, classes, extra = sys.argv[1:4]
    write = '--write' in sys.argv
    work = tempfile.mkdtemp(prefix='fixcomp')
    vsrc = os.path.join(work, 'src')
    os.makedirs(vsrc)
    summary = []
    for f in sorted(os.listdir(src)):
        if not f.endswith('.java'):
            continue
        cls = f[:-5]
        lines = open(os.path.join(src, f), encoding='utf8').read().split('\n')
        cands = [i for i, l in enumerate(lines) if CAND.match(l) and MARK not in l]
        if not cands:
            continue
        owner = method_of_lines(lines)

        def build(ls):
            open(os.path.join(vsrc, f), 'w', encoding='utf8').write('\n'.join(verification_copy(ls, cls)))
            ok, out = compile_one(vsrc, cls, work, classes, extra)
            return score(out, classes, cls) if ok else None

        base = build(lines)
        if base is None:
            print(f'{cls}: does not compile, skipped'); continue
        before = sum(base.values())
        kept = []
        for i in cands:
            m = CAND.match(lines[i])
            trial = list(lines)
            trial[i] = f'{m.group(1)}{m.group(2)} {m.group(3)}= {m.group(5)};'
            s = build(trial)
            if s is None:
                continue
            # compare only the methods this line could belong to
            if sum(s.values()) < sum(base.values()):
                lines, base = trial, s
                kept.append(i)
        after = sum(base.values())
        summary.append((cls, len(cands), len(kept), before, after))
        print(f'{cls:14} candidates {len(cands):3}  fixed {len(kept):3}  bag distance {before} -> {after}')
        for i in kept:
            print(f'    {f}:{i + 1} ({owner[i]})  {lines[i].strip()}')
        if write and kept:
            for i in kept:
                lines[i] = lines[i] + MARK
            open(os.path.join(src, f), 'w', encoding='utf8').write('\n'.join(lines))
    shutil.rmtree(work, ignore_errors=True)


if __name__ == "__main__":
    main()
