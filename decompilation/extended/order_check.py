#!/usr/bin/env python3
"""Order check: the sequence of type conversions per method, jar vs recompiled.

validate.py compares each method's MULTISET of operations. Two compensating
mistakes in one method -- a genuine cast dropped on one line, a false cast
kept on another -- leave the multiset unchanged, and Madness.drive had exactly
that. Type conversions (i2f, f2i, d2f, ...) keep their relative order through
any compiler's loop layout, so their ORDER is compared here, and each
differing stretch is reported with the decompiled source lines it covers
(from the recompiled class's line table).

  python3 order_check.py <recompiled-dir> <jar-dir> [Class ...]
"""
import difflib, os, re, subprocess, sys

INSN = re.compile(r'^\s*(\d+): ([a-z]\w*)')
CONV = re.compile(r'^[ifld]2[ifldbcs]$')
LINE = re.compile(r'^\s*line (\d+): (\d+)$')


def methods(path):
    out = subprocess.run(['javap', '-c', '-l', '-p', path], capture_output=True, text=True).stdout
    res, cur = {}, None
    for l in out.splitlines():
        if l.startswith('  ') and not l.startswith('    ') and l.rstrip().endswith(';'):
            cur = res.setdefault(l.strip(), {'ops': [], 'lines': []})
            continue
        if cur is None:
            continue
        m = INSN.match(l)
        if m and CONV.match(m.group(2)):
            cur['ops'].append((int(m.group(1)), m.group(2)))
        lm = LINE.match(l)
        if lm:
            cur['lines'].append((int(lm.group(2)), int(lm.group(1))))
    return res


def line_at(lines, pc):
    best = None
    for start, ln in sorted(lines):
        if start <= pc:
            best = ln
    return best


def main():
    rec, jar = sys.argv[1], sys.argv[2]
    only = set(sys.argv[3:])
    total = 0
    for f in sorted(os.listdir(jar)):
        if not f.endswith('.class') or '$' in f or (only and f[:-6] not in only):
            continue
        if not os.path.exists(os.path.join(rec, f)):
            continue
        o, r = methods(os.path.join(jar, f)), methods(os.path.join(rec, f))
        # a split method's helpers run in call order at the split point; good
        # enough to append them for Control.preform, whose blocks are in order
        for name in [n for n in r if '$split' in n]:
            parent = next(n for n in r if '$split' not in n and ' ' + name.split('$split')[0].split()[-1] + '(' in ' ' + n)
            r[parent]['ops'] += r[name]['ops']
        for name, om in o.items():
            rm = r.get(name)
            if not rm:
                continue
            a = [t for _, t in om['ops']]
            b = [t for _, t in rm['ops']]
            if a == b:
                continue
            sm = difflib.SequenceMatcher(None, a, b, autojunk=False)
            for tag, i1, i2, j1, j2 in sm.get_opcodes():
                if tag == 'equal':
                    continue
                lines = sorted({line_at(rm['lines'], rm['ops'][j][0]) for j in range(j1, j2)} - {None})
                total += 1
                print(f'{f[:-6]}.{name.split("(")[0].split()[-1]}  {tag:7} jar {a[i1:i2]}  recompiled {b[j1:j2]}  lines {lines}')
    print(f'{total} out-of-order stretches')


main()
