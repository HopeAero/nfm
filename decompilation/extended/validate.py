#!/usr/bin/env python3
"""Per-method equivalence check: recompiled decompiled source vs the jar.

fidelity.py measures how SIMILAR two opcode sequences are; this asks whether
they do the same WORK. Every method becomes a multiset ("bag") of operations
with compiler-dependent noise removed:

  - control-flow layout: goto dropped, conditional branches folded to their
    family (if_icmplt/if_icmpge -> if_icmp), since a bottom-tested loop
    inverts its condition and moves it;
  - local slot numbers and constant-pool indices;
  - constant encoding (iconst_2 / bipush 2 / ldc 2 -> const 2);
  - `iinc x, k` expanded to the iload/const/iadd/istore it stands for.

What is left -- arithmetic, every type conversion, every field and method
reference, every constant -- must match. A method whose bags are equal
performs the same operations; one whose bags differ is listed with the
exact difference, split into NUMERIC (a conversion or arithmetic op of a
different type: the procyon cast bug) and OTHER.

  python3 validate.py <recompiled-dir> <original-dir> [--all] > report.txt
"""
import collections, os, re, subprocess, sys

INSN = re.compile(r'^\s*(\d+): ([a-z]\w*)\s*(.*)$')   # [a-z]: not a switch table's `1: 308`
BRANCH = {
    **{f'if_icmp{c}': 'if_icmp' for c in ('eq', 'ne', 'lt', 'ge', 'gt', 'le')},
    **{f'if{c}': 'if' for c in ('eq', 'ne', 'lt', 'ge', 'gt', 'le')},
    'if_acmpeq': 'if_acmp', 'if_acmpne': 'if_acmp', 'ifnull': 'ifnull', 'ifnonnull': 'ifnull',
}
DROP = {'goto', 'goto_w', 'nop'}
NUMERIC = re.compile(r'^([ifld]2[ifldbcs]|[ifld](add|sub|mul|div|rem|neg)|[fd]cmp[lg]|lcmp|[il](shl|shr|ushr|and|or|xor))$')
CONST_OPS = ('iconst', 'lconst', 'fconst', 'dconst')


def javap(path):
    out = subprocess.run(['javap', '-c', '-p', path], capture_output=True, text=True).stdout
    methods, cur = {}, None
    for line in out.splitlines():
        if line.startswith('  ') and not line.startswith('    ') and line.rstrip().endswith(';'):
            cur = methods.setdefault(line.strip(), [])
            continue
        m = INSN.match(line)
        if not m or cur is None:
            continue
        op, rest = m.group(2), m.group(3)
        ref = re.search(r'//\s*(.*)$', rest)
        ref = ref.group(1).strip() if ref else ''
        base = re.sub(r'_(m1|\d+)$', '', op)
        if base in DROP:
            continue
        if base in BRANCH:
            cur.append(BRANCH[base]); continue
        if base in CONST_OPS:
            v = op.split('_', 1)[1] if '_' in op else rest.strip()
            v = '-1' if v == 'm1' else v
            cur.append(f'const {base[0]} {v}'); continue
        if base in ('bipush', 'sipush'):
            cur.append(f'const i {rest.strip()}'); continue
        if base in ('ldc', 'ldc_w', 'ldc2_w'):
            # "int 5", "float 0.5f", "double 0.3d", "String foo", "class X"
            kind, _, val = ref.partition(' ')
            if kind in ('int', 'float', 'long', 'double'):
                cur.append(f'const {kind[0]} {val.rstrip("fdlFDL")}')
            else:
                cur.append(f'ldc {ref}')
            continue
        if base == 'iinc':
            k = rest.split(',')[1].strip()
            cur.extend(['iload', f'const i {k}', 'iadd', 'istore']); continue
        if base in ('iload', 'lload', 'fload', 'dload', 'aload', 'istore', 'lstore', 'fstore', 'dstore', 'astore'):
            cur.append(base); continue
        if base in ('tableswitch', 'lookupswitch'):
            cur.append('switch'); continue
        cur.append(base + (' ' + ref if ref else ''))
    return methods


def canon_const(tok):
    # const f 1.0 and const i 1 are different types -- keep the type, normalise the number.
    if tok.startswith('const '):
        _, t, v = tok.split(' ', 2)
        try:
            n = float(v)
            v = repr(int(n)) if n == int(n) else repr(n)
        except ValueError:
            pass
        return f'const {t} {v}'
    return tok


SB_INIT_S = 'invokespecial Method java/lang/StringBuilder."<init>":(Ljava/lang/String;)V'
SB_INIT = 'invokespecial Method java/lang/StringBuilder."<init>":()V'
SB_APPEND_S = 'invokevirtual Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;'
# Data movement with no semantics of its own: which local a value passes
# through, and how the stack is shuffled, are the compiler's choice. procyon
# adds temporaries (`final int[] a = this.x; final int n = i; a[n] += ...`),
# ECJ re-reads a field where javac dups it. The operations stay in the bag.
MOVES = {'iload', 'lload', 'fload', 'dload', 'aload', 'istore', 'lstore', 'fstore', 'dstore', 'astore',
         'dup', 'dup_x1', 'dup_x2', 'dup2', 'dup2_x1', 'dup2_x2', 'swap', 'pop', 'pop2'}


def normalise(ops):
    out = []
    for t in ops:
        if t == SB_INIT_S:                 # new StringBuilder(s) == new StringBuilder().append(s)
            out += [SB_INIT, SB_APPEND_S]
        else:
            out.append(canon_const(t))
    # `new int[]{0, 0, ...}`: storing a zero into a fresh array is a no-op
    # procyon writes out; the original allocates and leaves it zero-filled.
    res, i = [], 0
    while i < len(out):
        w = out[i:i + 4]
        if (len(w) == 4 and w[0] == 'dup' and w[1].startswith('const i ')
                and w[2] in ('const i 0', 'const f 0', 'const d 0', 'const l 0')
                and w[3] in ('iastore', 'fastore', 'dastore', 'lastore', 'bastore', 'sastore', 'castore')):
            i += 4; continue
        res.append(out[i]); i += 1
    return [t for t in res if t not in MOVES]


def bag(ops):
    return collections.Counter(normalise(ops))


SPLIT = re.compile(r'\b(\w+)\$split\d+\(')


def fold_splits(methods, cls):
    """Merge `m$splitN` helpers back into `m`.

    A method over the JVM's 64 KB limit (Control.preform) cannot be recompiled
    whole, so a verification-only copy moves blocks of its body into
    `m$split1`, `m$split2`, ... (see README). Their operations are added to the
    parent's, and the call's own cost -- `this`, one load per argument, the
    invoke, the helper's return -- is taken back out.
    """
    for name in [n for n in methods if '$split' in n]:
        parent_name = SPLIT.search(name).group(1)
        parent = next(n for n in methods if re.search(rf'\b{re.escape(parent_name)}\(', n) and '$split' not in n)
        helper = methods.pop(name)
        nargs = name[name.index('(') + 1:name.index(')')].count(',') + 1
        ops = methods[parent]
        invoke = next(t for t in ops if t.startswith(('invokevirtual', 'invokespecial')) and '$split' in t and name.split('(')[0].split()[-1] in t)
        ops.remove(invoke)
        for _ in range(nargs + 1):
            ops.remove('aload')
        helper = list(helper)
        helper.remove('return')
        ops.extend(helper)


def main():
    rec, orig = sys.argv[1], sys.argv[2]
    show_all = '--all' in sys.argv
    totals = collections.Counter()
    listing = []
    for f in sorted(os.listdir(orig)):
        if not f.endswith('.class') or '$' in f:
            continue
        cls = f[:-6]
        rp = os.path.join(rec, f)
        if not os.path.exists(rp):
            print(f'{cls:18} NOT RECOMPILED')
            continue
        o, r = javap(os.path.join(orig, f)), javap(rp)
        fold_splits(r, cls)
        eq = num = oth = 0
        for name, ops in o.items():
            if name not in r:
                listing.append((cls, name, 'MISSING in recompiled', [], []))
                oth += 1
                continue
            a, b = bag(ops), bag(r[name])
            if a == b:
                eq += 1
                continue
            only_o = sorted((a - b).elements())
            only_r = sorted((b - a).elements())
            numeric = any(NUMERIC.match(t.split(' ')[0]) for t in only_o + only_r)
            kind = 'NUMERIC' if numeric else 'OTHER'
            num += numeric
            oth += not numeric
            listing.append((cls, name, kind, only_o, only_r))
        totals.update(eq=eq, num=num, oth=oth)
        print(f'{cls:18} {eq:4} equivalent  {num:3} numeric diff  {oth:3} other diff   of {len(o)}')
    print(f'\nTOTAL {totals["eq"]} equivalent, {totals["num"]} numeric, {totals["oth"]} other\n')
    for cls, name, kind, only_o, only_r in sorted(listing, key=lambda x: (x[2] != 'NUMERIC', x[0])):
        if kind == 'OTHER' and not show_all:
            continue
        print(f'[{kind}] {cls}  {name}')
        if only_o:
            print('   jar only:        ' + ' | '.join(collections.Counter(only_o).__repr__()[9:-2].split(', ')))
        if only_r:
            print('   recompiled only: ' + ' | '.join(collections.Counter(only_r).__repr__()[9:-2].split(', ')))


if __name__ == "__main__":
    main()
