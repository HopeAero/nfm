#!/usr/bin/env python3
"""Resolve procyon's compound-assignment casts against the real bytecode.

procyon prints `x += (int)(e)` for bytecode that is really `x = (int)(x + e)`
in float (TRANSPILE_SPEC §2, Case A) or in double, and the two round
differently. The decompiled text cannot tell them apart; the jar can.

Method: recompile the decompiled source with javac, so its bytecode carries
the DECOMPILED line numbers, then diff each method's opcodes against the
original class. A compound statement whose line falls in a differing block is
one procyon misrendered, and the original's opcodes in that block say which
arithmetic it really does.

  python3 web/tools/compound-verdicts.py <recompiled-dir> <original-dir> Class... > verdicts.json

Output: {"Class:line": ["float" | "double" | "int"]} for every decompiled line
in a differing block. A line absent from it compiled as written. float-audit.mjs --bytecode reads it.
"""
import difflib, json, re, subprocess, sys

INSN = re.compile(r'^\s*(\d+): (\w+)\s*(.*)$')


def javap(path):
    out = subprocess.run(['javap', '-c', '-l', '-p', path], capture_output=True, text=True).stdout
    methods, cur, name = {}, None, None
    for line in out.splitlines():
        if line.startswith('  ') and not line.startswith('    ') and line.rstrip().endswith(';'):
            name = line.strip()
            cur = {'insns': [], 'lines': []}
            methods[name] = cur
            continue
        if cur is None:
            continue
        m = INSN.match(line)
        if m and 'line ' not in line:
            pc, op, rest = int(m.group(1)), m.group(2), m.group(3)
            # Normalise: local slots and constant-pool indices differ between
            # compilers; field/method names and constant values do not.
            op = re.sub(r'_\d+$', '', op)
            ref = re.search(r'//\s*(.*)$', rest)
            tok = op + (' ' + ref.group(1) if ref else '')
            if op in ('bipush', 'sipush'):
                tok = op + ' ' + rest.strip()
            cur['insns'].append((pc, tok))
            continue
        lm = re.match(r'^\s*line (\d+): (\d+)$', line)
        if lm:
            cur['lines'].append((int(lm.group(2)), int(lm.group(1))))
    return methods


def line_of(lines, pc):
    best = None
    for start, ln in sorted(lines):
        if start <= pc:
            best = ln
    return best


def main():
    recomp, orig, classes = sys.argv[1], sys.argv[2], sys.argv[3:]
    verdicts = {}
    for c in classes:
        rm, om = javap(f'{recomp}/{c}.class'), javap(f'{orig}/{c}.class')
        for name, r in rm.items():
            o = om.get(name)
            if not o:
                continue
            a = [t for _, t in o['insns']]
            b = [t for _, t in r['insns']]
            sm = difflib.SequenceMatcher(None, a, b, autojunk=False)
            for tag, i1, i2, j1, j2 in sm.get_opcodes():
                if tag == 'equal':
                    continue
                span = range(j1, max(j2, j1 + 1))
                lines = {line_of(r['lines'], r['insns'][min(j, len(b) - 1)][0]) for j in span}
                ops = ' '.join(a[i1:i2])
                kind = ('float' if re.search(r'\bf(add|sub|mul|div)\b', ops)
                        else 'double' if re.search(r'\bd(add|sub|mul|div)\b', ops)
                        else 'int' if re.search(r'\bi(add|sub|mul|div)\b', ops) else None)
                for ln in lines:
                    if ln is not None and kind:
                        verdicts.setdefault(f'{c}:{ln}', set()).add(kind)
    json.dump({k: sorted(v) for k, v in verdicts.items()}, sys.stdout, indent=0)


main()
