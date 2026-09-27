[Español](README.md) · **English**

# Need for Madness in the browser — NFM 2 and Extended Mode

![icon](data/icon.png)

Need for Madness 2 and **Need for Madness 2 Extended Mode (v2.8)** running directly in the
browser, in JavaScript + WebGL, with no Java and no plugins. Both games start from the same
launcher and share the renderer, the sound, the Car Maker and the Stage Maker, and a car made
in the Car Maker can be raced in either.

What changed since the starting point: [`CHANGELOG.en.md`](CHANGELOG.en.md).

## Where it comes from

This project builds on the web port of NFM by
**[radicalarchive/nfm](https://github.com/radicalarchive/nfm)**
([play the original](https://radicalarchive.github.io/nfm)). All the credit for the base port
is theirs: the original JARs were decompiled and the Java was transcribed line by line to JS,
with WebGL for rendering. That port already had:

- NFM 2 playable, with its physics, HUD, sound and menus
- private online multiplayer
- the Car Maker
- resolution and fps improvements over the original's fixed 800x450 / 19 fps
- a patched build of the Java game that runs on modern Java (`./start.sh`)

On top of that, this fork:

- **adds a Stage Maker**, which the port did not have, with a 3D view of the stage you can
  move around in while you edit it;
- **completes the base port**: instant replay, end-of-race highlights, the pause menu and
  the classic controls, all translated into Spanish;
- **fixes the base port**: wheel smoke, colours and physics identical to Java, sound with no
  whistle or buzz in any browser;
- **ports Extended Mode to the browser**: decompiles it and validates it against its jar,
  then makes it playable with Free Play, a Career Mode that saves your progress, its 39 cars,
  its stages and also the NFM 2 and Stage Maker ones, at 60 fps;
- **adds new Car Maker cars to Extended**, with a tab of its own in the Car Maker.

The details, split by base port, Stage Maker, Extended and Car Maker, are in
[`CHANGELOG.en.md`](CHANGELOG.en.md).

## How Extended was ported

The same way as the base port: Extended's decompiled Java is translated to JS under a strict
contract (`web/TRANSPILE_SPEC.md`: 32-bit ints, float32 rounding, compound assignments), and
each part is compared against the original jar running side by side (`web/tools/`). Models
load identically, drawing matches call for call, and physics and stage loading replay
exactly on traces captured from the jar.

## Running it

```sh
python3 web/tools/serve.py 8123     # from the repo root
# then open http://localhost:8123/
```

Tests: `cd web && node --test`. How to measure, verify and deploy: `AGENTS.md`.

## Layout

- **`index.html`** — the launcher (pick the game, car and stage; settings).
- **`web/`** — the port. `main.html` is the game; `web/ext/` is Extended Mode.
- **`ext/`** — Extended's data (its `.radq` archives, fonts and sounds), unmodified.
- **`java/`** — the patched original game (`Game.jar`, run by `start.sh`) and the pristine
  jar (`Game.jar.bak`). It is the reference the port is compared against, not a build input.
- **`decompilation/`** — the decompiled Java of NFM 2 (from radicalarchive) and of Extended
  (repaired and validated against its jar in this fork), and the port plan. Read-only.
- **`data/`, `stages/`, `mycars/`, `mystages/`, `music/`** — the game files, byte-identical
  to the original and **not to be modified**.

## Documents

- `CHANGELOG.en.md` — everything that changed since radicalarchive's port
- `AGENTS.md` — how to run, deploy, measure and verify; the invariants
- `WORK.md` — discoveries and gotchas, newest last
- `TASKS.md` — what is done, what is next, what is blocked
- `web/TRANSPILE_SPEC.md` — the Java → JS contract
- `decompilation/PORT_SPEC.md` — the original plan and the rules for delegating work
