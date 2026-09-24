# extended/ — decompiled Need for Madness 2 Extended Mode v2.8

Procyon 0.6.0 output for the 26 game classes of `madness.jar`
(SHA-256 `d35de9c3…2478d`, see `../../research/extended-mode/README.md` for
where the original lives). Reference for a future port, like `../java-src/` is
for the base game. Not compiled into anything; the jar stays the authority.

The 127 bundled library classes (`com/jcraft` Ogg/Vorbis, `javazoom` MP3,
`org/`) are not decompiled: they are published libraries.

## Validation — read this before porting anything from here

**`java-src/` is procyon's output REPAIRED against the jar.** Porting procyon's
raw text would have ported wrong code: its compound assignments misstate
their casts (`web/TRANSPILE_SPEC.md` §2), and here that includes
`i4 *= (int)0.991` -- literally "multiply by zero" -- for bytecode that does
`i4 = (int)(i4 * 0.991)`. 126 lines are repaired, each marked
`// cast: bytecode-verified`: 123 misplaced casts dropped, and 3 products
in `Madness.drive` given the explicit `(double)` the jar multiplies in (the
same result either way: a product of two floats is exact in a double). The unrepaired output is reproducible (see
Regenerating) and was kept out of the repo on purpose.

### Level 1: every method, by operations (`verify.py`)

`verify.py` recompiles a verification copy (method-local `final` stripped so
javac does not fold constants the original computed at run time;
`Control.preform` split into helpers under the JVM's 64 KB limit and folded
back) and `validate.py` compares each method's multiset of operations with the
jar's -- arithmetic, every conversion, every field/method reference, every
constant -- after removing what legitimately differs between compilers (loop
layout, local slots, stack shuffles, `StringBuilder(s)` vs `().append(s)`).

**1,924 of 1,950 methods are equivalent.** The other 26 were read one by one
against `javap`; all are benign, in four kinds:

| Kind | Methods | Why it is the same behaviour |
| --- | --- | --- |
| Chained assignment | `loadmusic`, `carselect`, `stat`, `careermode`, `scouting`, `stageselect`, `nitroandspecials`, `loadstage`, `setsky`, `setcloads`, `Control.preform` (2 of its diffs), audio/launcher classes | procyon writes `(a[i] = v).m()` / `x = (y[i] = v)`; the jar stores, then re-reads the same slot |
| NaN-only comparison order | `Control.preform` (17), `xtGraphics.sortcars` (2) | `!(a <= b)` vs `a > b`: differ only if an operand is NaN. sortcars compares `Math.random()` (never NaN); preform compares speeds. **Port these from the bytecode's form** (`fcmpl` = `>`/`>=`, `fcmpg` = `<`/`<=`), since JS NaN compares like Java's |
| Explicit zero | `ContO(...)`, `Record(...)`, `GameSparker.run`, `careermode` | the original assigns 0 where procyon relies on the default |
| Duplicate return | `xtGraphics.over`, `overon` | two `return`s merged into one |

`fix_compound.py` made the repairs: each candidate `x op= (T)e` had its cast
dropped, the class was recompiled, and the change was kept only if the method
moved closer to the jar. A greedy search can over-accept, so every repair
inside a still-non-equivalent method was re-tested by reverting it alone; one
(`Madness.java:2888`, `exp[cn] += (int)(...)`, a genuine cast) was restored
that way. The other 14 checked held.

**What level 1 cannot see:** swapped operands (`a - b` vs `b - a`) and a
wrongly inverted condition have the same operations. Level 2 covers those on
the paths it runs.

### Level 2: both games side by side (`diffrun/`)

`diffrun/DiffRun.java` runs the jar and the recompiled source in one JVM, each
in its own class loader, with the clock, `Math.random`, `Thread.sleep` and
`Date` replaced by a deterministic runtime (`diffrun/det/Det.java`) and the
same scripted keys and clicks, and compares every tracked game object field
by field (floats bit for bit) and the frame pixel by pixel, every frame.

- **Control run (jar vs jar): 0 divergent frames** through intro, menu, car
  and stage select -- the harness adds no noise.
- **Unrepaired procyon vs jar: diverged at frame 385**, the menu's background
  stage: `Medium.newpolys`'s `cgpx[n] += (int)(Math.random() * ...)` truncates
  the random term separately, so a `while` loop runs a different number of
  times, the random stream desyncs, and the copy then hung. Same bug level 1
  found, found independently.
- **Repaired source vs jar: 0 divergent frames, state and pixels, twice, with
  a clean jar-vs-jar control alongside** (2026-09-23): 1,900 frames through
  intro, menu (whose background runs a live bot race), Classic Mode, car and
  stage select, loading, and ~650 frames of racing on stage 13 against five
  AI cars -- accelerating, steering both ways, collisions, a car wasted.
  That exercises `Madness.drive`/`colide`, `Control.preform` (AI),
  `CheckPoints.checkstat`, `Record`, `ContO`/`Plane`/`Medium` drawing and the
  race HUD.
- It caught what level 1 could not: `CheckPoints.checkstat` hung the
  recompiled copy at frame 386 in an infinite loop with exactly the jar's
  operations (control flow, see the `while` comments in `CheckPoints.java`
  and `Madness.java`, 3 sites incl. `respawn`).

Not exercised by any run, so covered by level 1 only: career/RPG mode,
tourney, bots test, specials/nitro, respawn/teleport, the stage editor paths.
**Run `diffrun` on a mode before porting it.** Rules learned the hard way:
use a fresh copy of the game directory per run (both copies share `data/` and
the save files), nothing else on the CPU, and count a divergence only if it
reproduces while jar-vs-jar stays clean.

```sh
# from a fresh COPY of the game directory; B = verify.py's <work>/out
java --add-exports java.base/jdk.internal.org.objectweb.asm=ALL-UNNAMED      --add-exports java.base/jdk.internal.org.objectweb.asm.commons=ALL-UNNAMED      -Ddiffrun.every=10 -Ddiffrun.shots=1500 -cp <diffrun-classes> DiffRun <work>/jar <work>/out <work>/jar 1900      "300:click:435:380,420:1005,440:1005,460:10,560:10,660:10,720:1006,820:1006,950:10,1100:10,1120:click:435:322,1250:1004:600,1450:1007:60,1600:1006:40"
```
(that script: start, Classic Mode, car, stage 15 -> 13 (14+ are "END OF BETA",
`betalimit`), START, race.) `order_check.py` is the third tool: the sequence of
type conversions per method, for compensating mistakes a bag cannot see.

### Opcode similarity (first pass, superseded by the above)

Recompiled with `javac --release 8` and diffed per method against the jar with
`fidelity.py` (opcode sequences, local slots and pool indices normalised):

| | opcodes | match |
| --- | --- | --- |
| **Extended, 23 of 26 classes** | 190,388 | **97.6%** |
| Base game `java-src/` (already ported and verified), same measure | 98,196 | 98.7% |

The gap is compiler, not decompiler: the extended jar is class version 51
(Java 7) built by a compiler that tests loop conditions at the bottom
(`goto cond; body; cond: if_icmplt body`), where javac tests at the top. A
short loop like `ContO.setfire` scores 86% on layout alone with identical
logic. Procyon flagged no method as undecompilable.

Three files needed help to recompile at all (all three now pass level 1):

| File | Reason | Handled by |
| --- | --- | --- |
| `Control.java` | `preform()` recompiles past the JVM's 64 KB method limit: the original is 60,777 bytes and javac's output is longer | `verify.py` splits it (verification copy only) |
| `RadicalMod.java` | uses `sun.audio`, removed from the JDK after 8 | compile-only stub, `stubs/sun/audio/` |
| `RadicalMidi.java` | the jar's `javazoom/jl/player/PausablePlayer` was compiled by Eclipse **with errors** ("Unresolved compilation problems" is in its constant pool) and names a default-package `JavaLayerException`. **MP3 playback is broken in the original game itself** | compile-only stub, `stubs/javazoom/` |

`ModSlayer.java` had one unreachable `break` (the procyon artifact the base
game's copy also has); deleted.

Vineflower 1.10.1 was run as a second opinion and failed on four methods,
`Control.preform` among them, so it adds nothing here.

**Same caveat as the base game:** procyon misrenders compound-assignment casts
(`web/TRANSPILE_SPEC.md` §2). Before porting any numeric method, run the float
audit (`web/tools/FloatAudit.java`, `compound-verdicts.py`) against this jar.

## What differs from the base game

Method names per class, extended vs base (`Madness` is the base's `Mad`; the
base's `Madness` launcher is `RunApp` here):

| Class | shared | new | gone | new methods |
| --- | --- | --- | --- | --- |
| `xtGraphics` | 56 | 43 | 34 | career mode, tourney, bots test, beasts, drawwater, stats screens, … |
| `GameSparker` | 18 | 5 | 24 | loadbots, readdata/writedata (save), sunytyp |
| `ContO` | 13 | 6 | 2 | drawsnow, drawsun, rainbow, setfire/unsetfire, teleflash |
| `Madness` (= `Mad`) | 10 | 4 | 1 | ghostcolide, respawn, teleport |
| `Medium` | 30 | 2 | 3 | redrawpolys, reset |
| `Plane` | 8 | 2 | 1 | recolour, sortpieces |
| `Control` | 6 | 1 | 0 | max |
| `CheckPoints`, `Record`, `Trackers`, `Wheels`, `SuperClip`, `UlawUtils` | — | 0 | 0–2 | same API; bodies differ |

New classes: `Bots` (bot track data), `Contva`, `RunApp` + `Desktop*` (the
standalone launcher replacing the applet), `SuperStream`, `RadicalMidi` and
the `Mod*` MOD-player classes.

## Regenerating

```sh
mkdir -p /tmp/ext && cd /tmp/ext && unzip -oq "<path>/madness.jar"
java -Xmx4g -jar procyon-decompiler-0.6.0.jar -o java-src $(ls *.class | grep -v '\$')
# verify:
javac -nowarn -proc:none --release 8 -encoding UTF-8 -cp /tmp/ext -d /tmp/out \
  $(ls java-src/*.java | grep -v 'Control\|RadicalMod\|RadicalMidi')
python3 fidelity.py /tmp/out /tmp/ext
```

Procyon took 84 s with 4 GB of heap; `xtGraphics.class` is 355 KB. Classes
must be listed explicitly, as for the base jar. On Windows pass
`cygpath -w` paths to `java`.

The game data (`.radq`) unpacks with `research/extended-mode/radq.py`, which
never writes to the originals.
