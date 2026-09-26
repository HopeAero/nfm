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

After every regeneration, apply the named patches that carry the base port's
optimisations over (`ext/patches.test.js` fails until you do):

```sh
node web/tools/ext-patches.mjs
node web/tools/ext-ident.mjs          # new cars: car-number comparisons ask id() (ident.test.js fails until you do)
```

Hand-written: `radq.js` (the archives), `jawt.js` (the slice of the Java
library the generated code calls, with Java's semantics where they matter).

## Verified

| Test | Against |
| --- | --- |
| `radq.test.js` | every entry's CRC in all 15 `ext/data/` archives |
| `ContO.test.js` | all 129 models, every ContO and Plane field, bit for bit, vs `madness.jar` (`contO.expected.json.gz` from `web/tools/ExtContOProbe.java`, `Math.random` seeded on both sides) |
| `draw.test.js` | `ContO.d`/`Plane.d`/`Plane.s` call for call vs the jar (`draw.expected.json.gz` from `web/tools/ExtDrawProbe.java` + `RecG.java`): 129 models x 4 poses (170k calls), and 39 cars x 17 effect setups x 6 frames -- fire, teleport, invisibility, glow, freeze, ghost, lightup, electricity, repair, snow, rainbow, sun (~3M calls, 160k translucent colours; stored as hashes) |

`Medium.d` (sky, ground, clouds, mountains) is generated but verified only
with the stage loader (step 4): its inputs come from loadstage.

Extended draws with per-colour alpha (`new Color(r, g, b, a)`);
`graphics.js`/`canvas-graphics.js` `setColor` take an optional fourth
argument, multiplied with the composite as in Java2D, and `setColorOf(c)`.

`viewer.html` draws any model with any effect on the Canvas2D Graphics: a
developer page, not part of the game.

## Racing

```
python3 web/tools/serve.py 8123
http://localhost:8123/web/main.html?ext=classic&stage=1&car=23      # the race, in the base shell
http://localhost:8123/web/ext/main.html?mode=classic                 # dev page: Extended's own car select
```
The launcher's Extended Edition -> Classic Race / Career Mode runs the first
(`web/ext/race.js`: the base page, WebGL, stats line and tick loop around
the generated `GameSparker.run()`). The dev page
preloads the archives and fonts (`ext/fonts/`, the two `.ttf` from
`madness.jar`), then runs the jar's own `GameSparker.run()` with Extended's
menus skipped (see `main.html` and WORK.md). `jgraphics.js` is the Graphics:
`canvas-graphics.js` with Java's `drawImage`/`fillPolygon(Polygon)` shapes.
