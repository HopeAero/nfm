"""Decompilation fidelity: per-method opcode match of recompiled source vs the jar.

  python3 fidelity.py <recompiled-classes-dir> <original-classes-dir>

Local slots and constant-pool indices are normalised away; field and method
names are kept. ~98% is the ceiling between two different compilers: the
extended jar was built by a compiler that tests loop conditions at the bottom
(javac tests at the top), which costs ~14% on a short loop and nothing else.
"""
import difflib, re, subprocess, sys, os
INSN = re.compile(r'^\s*(\d+): (\w+)\s*(.*)$')
def javap(path):
    out = subprocess.run(['javap', '-c', '-p', path], capture_output=True, text=True).stdout
    ms, cur = {}, None
    for line in out.splitlines():
        if line.startswith('  ') and not line.startswith('    ') and line.rstrip().endswith(';'):
            cur = ms.setdefault(line.strip(), []); continue
        m = INSN.match(line)
        if m and cur is not None:
            op = re.sub(r'_\d+$', '', m.group(2))
            ref = re.search(r'//\s*(.*)$', m.group(3))
            cur.append(op + (' ' + ref.group(1) if ref else ''))
    return ms
rec, orig = sys.argv[1], sys.argv[2]
tot_eq = tot = 0; bad = []
for f in sorted(os.listdir(rec)):
    if not f.endswith('.class') or '$' in f: continue
    r, o = javap(os.path.join(rec, f)), javap(os.path.join(orig, f))
    ceq = ct = 0
    for name, ops in o.items():
        rops = r.get(name)
        if rops is None: bad.append((f, name, 0.0, len(ops))); ct += len(ops); continue
        sm = difflib.SequenceMatcher(None, ops, rops, autojunk=False)
        eq = sum(b.size for b in sm.get_matching_blocks())
        ceq += eq; ct += len(ops)
        ratio = eq / max(len(ops), 1)
        if ratio < 0.97 and len(ops) > 20: bad.append((f, name, ratio, len(ops)))
    tot_eq += ceq; tot += ct
    print(f'{f:24} {ceq/ max(ct,1):.3f}  ({ct} opcodes)')
print(f'TOTAL {tot_eq/tot:.4f} of {tot} opcodes')
print('methods under 97%:')
for b in sorted(bad, key=lambda x: x[2]): print(f'  {b[0]:20} {b[2]:.2f} {b[3]:6}  {b[1][:90]}')
