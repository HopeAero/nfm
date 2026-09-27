**English** · [Español](CHANGELOG.es.md)

# Changes since radicalarchive's port

Everything that changed since the base port by
[radicalarchive/nfm](https://github.com/radicalarchive/nfm) (its last commit is from
2026-08-16), including the work done before this repo's first commit. It is split by what
each change affects: the **base port** (NFM 2), the **Stage Maker**, **Extended Mode** and
the **Car Maker**. Anything that lives in shared files (colours, physics, sound) is listed
under the base port, even when it applies to Extended too.

---

## Stage Maker (new)

The original port had no stage editor. This is a port of the Java Stage Maker, with
improvements the original did not have.

- **A stage editor in the browser**: an overhead map drawn with the game's own renderer. Pick
  a piece and click to place it: roads snap end to end, and checkpoints and ramps sit on the
  road under them. In Select mode you drag pieces to move them; <kbd>R</kbd> rotates and
  <kbd>Del</kbd> deletes. Untick "Snap to road" (or hold <kbd>Alt</kbd>) to put a piece
  exactly where you click.
- **3D view**: the stage as the game draws it, with sky, fog, mountains, clouds, lights and
  walls, from any angle. **You can move around in it**: dragging orbits the stage, the right
  button (or <kbd>Shift</kbd>) slides the view, the wheel zooms, and the arrows and
  <kbd>+</kbd> <kbd>-</kbd> move the camera too. Pieces are placed, picked and moved in 3D
  just as on the map.
- **Saves exactly like the original**: the step that orders the stage (driving order, the
  points the computer cars follow, the boundary walls) is a transcription of Java's
  `sortstage`, verified against the Java itself.
- **Undo and redo**, up to 100 steps (<kbd>Ctrl+Z</kbd> / <kbd>Ctrl+Y</kbd>).
- The stage can also be edited as text, and it **imports and exports as `.txt`**, which the
  desktop game opens.
- **Test drive** the stage in one click, without leaving the editor.
- Mountains generated from a seed, with a "New mountains" button.
- **Your own music**: besides the original's `.mod` files, it imports MP3, OGG, M4A or WAV.
- Stages are saved in the browser, and can be raced in NFM 2 and in Extended.
- Available in Spanish.

---

## Base port (NFM 2)

### New
- **Instant replay**: the last 300 ticks of the race, with the cars' damage, from the pause
  menu (with "Replay recording" on in the settings).
- **End-of-race highlights**: stunts, wrecks, losses or close finishes are played three
  times, with titles and camera cuts, as in the original game. <kbd>Enter</kbd> or
  <kbd>Esc</kbd> skips them.
- **The original pause menu** (the game's `paused.gif`): Resume, Instant Replay, Game
  Instructions and Quit, by keyboard or mouse. In multiplayer the race keeps running for the
  others, and they are told when you leave.
- **Classic race controls**: <kbd>A</kbd> shows the arrows pointing to the cars and
  <kbd>S</kbd> the radar, as in Java.
- The race-end screen and the car and stage selects are the original game's, with the
  camera flying in to the stage.
- **Spanish translation**: text, HUD, countdown, selects, results, and the English-lettered
  sprites, redrawn in Spanish over the originals. The Car Maker's help is translated too.
- The NFM 2 multiplayer stages (28–32), which used to exist only in the online lobby, are now
  in Free Play, under a Multiplayer tab.
- The launcher has an Extended Mode entry and a "Show performance" setting
  (off / fps / ms / all) that works for both games.
- Developer mode in the settings: the URL test switches (`?stage=`, `?stats=`, ...) only
  count when it is on, or when the local dev server serves the page. The Car Maker's and
  Stage Maker's test links always work.

### Bugs fixed
- **Wheel smoke**: the dust the wheels kick up had been ported from a different version of
  the code. It was drawn without subtracting the camera position, and opaque, at half the
  road colour, so instead of smoke you got **weird black blotches**, especially at the start
  of night stages. It now shows where it should, translucent, as in Java.
- **Physics identical to Java**: 13 float/double rounding errors in driving (`Mad.drive`) and
  drawing (`Plane`). Given the same randomness, Java and the port now give the same result
  on every one of the 300 ticks compared. An audit tool (`web/tools/float-audit.mjs`) checks
  the rest of the port.
- **Lighting colours**: `Color.RGBtoHSB` and `Color.HSBtoRGB` were computed in double
  precision, and Java computes them in float. Some faces of cars and stages came out a shade
  off from the original game. The computation is now identical to Java's.
- **Stage previews**: stage 8 and other large stages did not draw in the overhead view. All
  32 show now.
- A black grid showed across the roads on the car and stage selects, from the seams between
  polygons. Fixed.
- **Stutter on the stage select**: on the heaviest stages (NFM 1: 9 and 10; NFM 2: 15 and 16)
  one late frame chained several ticks back to back and the preview froze for 200–300 ms. The
  backlog is now dropped, as Java's loop does, and the worst case is about 60–85 ms.
- After a race, the car select had no music.
- <kbd>Enter</kbd> during a race (the game's pause) switched the stage's music.
- "Continue" after a race goes back to the main menu, as in Java.
- Effects volume at 0% or 25% still played at 100%: the volume control was created after the
  setting arrived and ignored it.

### Sound
- **Idle engine whistle**: the original effects are recorded at 8 kHz and each browser
  converted them its own way. With a linear conversion a high whistle stayed on while the car
  stood still. The game now does that conversion itself, with a high-quality sinc filter,
  and loops are converted so their seam stays smooth.
- **Buzz in Opera GX**: Opera GX produced a 375 Hz buzz (48,000 / 128) when looping the
  engine sounds. The game now **mixes every effect itself** in an AudioWorklet
  (`web/mixer.js`), and the browser only plays one finished signal, so it sounds the same in
  any browser. In Chromium the output was verified to be identical to the original sound,
  sample for sample. Where there is no AudioWorklet, the game plays the sounds as before.

---

## Extended Mode (new)

### The game
- Extended Mode runs in the browser. First its jar was decompiled and the result repaired
  against the bytecode (the decompiler got 126 type conversions wrong). Then its code was
  translated to JavaScript and every part compared against the original jar: models load
  identically, drawing matches call for call (~3 million calls, including fire, teleport,
  invisibility, freeze and the other effects of all 39 cars), and physics and stage loading
  replay exactly on traces captured from the jar.
- **Free Play** with all 39 cars and a stage select in two groups: the 32 NFM 2 stages and
  Extended's own stages.
- A complete **Career Mode**, with progress saved in the browser: stage-by-stage unlocks,
  stat points with Confirm / Undo, bonus stages, hard mode, scale levels, scouting and
  changing cars.
- **NFM 2 and Stage Maker stages** can be raced with Extended's gameplay. They look as in
  the base port: fog, ground patches, hill shading and checkpoint flicker.
- Extended uses the base port's pause menu and race-end screen, and has its own **instant
  replay**.
- Music (tracker and the career's `.ogg` tracks), sound effects and scrape sparks, as in the
  base port.
- A complete **Spanish translation**: race text, stunt calls, finish screens, car select and
  sprites.
- In Free Play you choose the number of cars from the launcher, down to a **one-car time
  trial**.

### Performance
- **60 fps**: the game advances at ~19 ticks per second, and Extended now draws in-between
  frames from one tick to the next, as the base port already did. Heavy scenes went from
  26–44 fps to 58–60 fps.
- On career stage 3, the simulation went from 8.9 to 3.8 ms per tick and drawing from 15 to
  11 ms per frame. The simulation's results did not change.
- **Faster loading**: files download in parallel (from ~2.5 s to ~1 s), the bots' data loads
  only for the chosen stage, and the game preloads while you are on the menu.

### Bugs fixed
- **24 of the 27 stages** in `tracks.radq` used an old model list, with every piece number
  off by 4; that is why the original game marked that mode UNAVAILABLE. They are now fixed
  on load and can be raced.
- The night stages (10 and 14) froze the game.
- Career stages with `.ogg` music crashed the race.
- The camera shook in turns.
- The race-end screen showed "TV static".
- NFM 2 stages lost their ground colour (white blotches).
- The stage select froze after racing an NFM 2 stage.
- NFM 2 hills popped in when you were already close.
- Wheel dust, sparks and skid dust now look as in the base port.

---

## Car Maker → new cars in Extended

- Cars you make in the port's Car Maker can be raced in Extended, alongside the original 39.
- A new **Extended** tab in the Car Maker: special ability, its own stats, health and
  damage, plus a **"Try in Extended"** button.
- The Car Maker now edits the car's **author** (NFM 2's `carmaker` line), and Extended's car
  select credits it.
- In Free Play, your cars have a view of their own, with the same switch NFM 2 had.
- If a car cannot load, the game says why, and one broken car no longer breaks Free Play.
- Fixed: the Extended tab doubled the cost of every slider.
