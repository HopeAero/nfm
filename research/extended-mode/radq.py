#!/usr/bin/env python3
"""Unpack Extended Mode's .radq archives without touching the originals.

A .radq is a ZIP, either plain or with seven byte pairs swapped throughout the
file (GameSparker undoes the swap before its ZipInputStream; see README.md).
This tries the file as-is, then decoded, and extracts whichever opens with
every entry's CRC intact.

  python3 research/extended-mode/radq.py <file.radq | dir> <out-dir>
"""
import io, os, sys, zipfile

PAIRS = [(0x4B, 0x55), (0x24, 0x40), (0x35, 0x13), (0x15, 0x2C), (0x3B, 0x48), (0x0B, 0x31), (0x0D, 0x44)]
TABLE = bytearray(range(256))
for a, b in PAIRS:
    TABLE[a], TABLE[b] = b, a


def open_radq(data):
    """Return (ZipFile, 'plain'|'swapped'), or raise if neither form is a valid ZIP."""
    for form, buf in (('plain', data), ('swapped', data.translate(TABLE))):
        try:
            z = zipfile.ZipFile(io.BytesIO(buf))
            if z.testzip() is None:
                return z, form
        except zipfile.BadZipFile:
            pass
    raise ValueError('neither plain nor swapped form is a valid ZIP')


def unpack(path, out):
    with open(path, 'rb') as f:
        z, form = open_radq(f.read())
    dest = os.path.join(out, os.path.splitext(os.path.basename(path))[0])
    z.extractall(dest)
    print(f'{form:8} {len(z.namelist()):4} entries  {path}')


def main():
    src, out = sys.argv[1], sys.argv[2]
    paths = [src] if os.path.isfile(src) else [
        os.path.join(d, f) for d, _, fs in os.walk(src) for f in fs if f.endswith('.radq')]
    bad = 0
    for p in sorted(paths):
        try:
            unpack(p, out)
        except ValueError as e:
            bad += 1
            print(f'FAILED   {p}: {e}')
    print(f'{len(paths) - bad}/{len(paths)} unpacked')
    sys.exit(1 if bad else 0)


if __name__ == '__main__':
    # Self-check: the swap is an involution, so decoding twice is the identity.
    assert bytes(range(256)).translate(TABLE).translate(TABLE) == bytes(range(256))
    main()
