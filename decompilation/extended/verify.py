#!/usr/bin/env python3
"""Level-1 verification of the extended decompilation, end to end.

  python3 verify.py <madness.jar> <work-dir> [--all]

1. unpack the jar into <work>/jar
2. compile stubs/ (compile-only stand-ins for sun.audio and the jar's broken
   PausablePlayer; see README)
3. write the verification copy of java-src/ (fix_compound.verification_copy:
   method-local `final` stripped, Control.preform split under the 64 KB limit)
4. recompile it into <work>/out  (also the B side of diffrun/)
5. validate.py: per-method operation bags, recompiled vs jar

Needs JDK 17+ `javac` (JAVAC env var to override) and `javap` on PATH.
"""
import glob, os, subprocess, sys, zipfile

HERE = os.path.dirname(os.path.abspath(__file__))
sys.path.insert(0, HERE)
import fix_compound  # noqa: E402

JAVAC = os.environ.get('JAVAC', 'javac')


def run(cmd):
    r = subprocess.run(cmd, capture_output=True, text=True)
    if r.returncode:
        sys.exit(r.stdout + r.stderr)


def main():
    jar, work = sys.argv[1], sys.argv[2]
    jardir, stubs, vsrc, out = (os.path.join(work, d) for d in ('jar', 'stubs', 'vsrc', 'out'))
    for d in (jardir, stubs, vsrc, out):
        os.makedirs(d, exist_ok=True)
    zipfile.ZipFile(jar).extractall(jardir)
    run([JAVAC, '-nowarn', '--release', '8', '-cp', jardir, '-d', stubs]
        + glob.glob(os.path.join(HERE, 'stubs', '**', '*.java'), recursive=True))
    srcs = []
    for f in sorted(glob.glob(os.path.join(HERE, 'java-src', '*.java'))):
        cls = os.path.basename(f)[:-5]
        lines = open(f, encoding='utf8').read().split('\n')
        dst = os.path.join(vsrc, cls + '.java')
        open(dst, 'w', encoding='utf8').write('\n'.join(fix_compound.verification_copy(lines, cls)))
        srcs.append(dst)
    run([JAVAC, '-nowarn', '-proc:none', '--release', '8', '-encoding', 'UTF-8',
         '-cp', os.pathsep.join([stubs, jardir]), '-d', out] + srcs)
    sys.stdout.flush()
    subprocess.run([sys.executable, os.path.join(HERE, 'validate.py'), out, jardir] + sys.argv[3:])


if __name__ == '__main__':
    main()
