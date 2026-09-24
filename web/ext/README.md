# web/ext/ — the Extended Mode port (branch `extended-mode`)

Need for Madness 2 Extended Mode v2.8 in the browser. Its engine is not the
base game's with additions: `ContO` has other defaults (6 wheels, `disline`
7), another directive set (no `light`/`ScaleX`), and cars carry no stats in
their models (Extended has no `CarDefine`). So these classes are transpiled
from Extended's own repaired source, not patched from `web/*.js`.

## Generated files — do not edit

`ContO.js`, `Plane.js`, `Wheels.js`, `Trackers.js`, `Medium.js` are written by
`decompilation/extended/j2js/J2JS.java` from `decompilation/extended/java-src/`.
It types every expression with javac, so the float/int rules of
`web/TRANSPILE_SPEC.md` are applied mechanically (every float op in `fr()`,
every int `/` in `idiv`, compound casts per JLS 15.26.2). A wrong line is
fixed in the transpiler or the Java source, then regenerated:

```sh
JDK=".../jdk-21/bin"
X="--add-exports=jdk.compiler/com.sun.tools.javac.tree=ALL-UNNAMED --add-exports=jdk.compiler/com.sun.tools.javac.code=ALL-UNNAMED --add-exports=jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED"
"$JDK/javac" $X -d /tmp/j2js decompilation/extended/j2js/J2JS.java
# classpath: verify.py's <work>/stubs and <work>/jar (the unpacked madness.jar)
"$JDK/java" $X -cp /tmp/j2js J2JS "<stubs>;<jar>" web/ext Wheels,Plane,ContO,Trackers,Medium \
    $(ls decompilation/extended/java-src/*.java | grep -v Control.java)
```

Hand-written: `radq.js` (the archives), `jawt.js` (the slice of the Java
library the generated code calls, with Java's semantics where they matter).

## Verified

| Test | Against |
| --- | --- |
| `radq.test.js` | every entry's CRC in all 15 `ext/data/` archives |
| `ContO.test.js` | all 129 models, every ContO and Plane field, bit for bit, vs `madness.jar` (`contO.expected.json.gz` from `web/tools/ExtContOProbe.java`, `Math.random` seeded on both sides) |

Drawing (`ContO.d`, `Plane.d`, `Medium.d`) is generated but not yet wired to
the renderer or verified: Extended draws with per-colour alpha, which
`web/graphics.js` does not take yet.
