# TASKS.md — remaining work on the JS/WebGL port

See `WORK.md` for gotchas, `web/TRANSPILE_SPEC.md` for the transpilation
contract, `AGENTS.md` for how to run, deploy and measure.

Status key: `[x]` done · `[~]` in progress · `[ ]` not started · `[!]` blocked/needs a human

---

## Done

- [x] Java-semantics runtime (`web/java.js`) — idiv/i32/trunc/fr, JavaRandom, Color
- [x] VFS + zip reader (`web/vfs.js`) — fetch, `DecompressionStream`, fpath autodetect
- [x] WebGL `Graphics2D` (`web/graphics.js`) — colour-as-attribute, one draw call, even-odd fill
- [x] Transpile: `Plane`, `Medium`, `Trackers`, `Wheels`, `CheckPoints`, `ContO`, `Record`, `Control`, `CarDefine`, `Mad`
- [x] Race harness (`web/GameSparker.js`) — `loadbase`, `loadstage`, `fase == 0` tick
- [x] Browser shell (`web/main.js`, `web/main.html`) — rAF pacing, keyboard, scale-to-fit
- [x] Audit `ContO`/`Record`/`Control` against §2/§2b
- [x] Fix black skybox, checkpoint flash (`*= (int)<float>` artifacts)
- [x] Fix tick rate (530ms/10 frames, not the 400ms menu figure)
- [x] Fix glyph fill (even-odd; keyhole counters in O/A/R)
- [x] `xtGraphics.stat()` — the in-race HUD. 11 multiplayer/clan/LAN branches
      deliberately skipped, each marked `// TODO not ported:` at the site.
- [x] `CarDefine.loadcar()` — un-stubbed; IO seam takes the file text as a parameter
- [x] **HUD image assets** (`web/images.js`) — decode from images.zip, port
      `loadsnap()` (per-stage tint + grey-ramp-as-alpha). Watch the mixed
      backgrounds: some assets are opaque 192-grey, later ones use GIF index
      transparency.
- [x] **Spanish variants of classic UI sprites** (`web/ui-sprites-es.js`) —
      labels for car/stage selection, navigation, race HUD and countdown,
      highlights, results and Continue; VUELTAS uses the available 70px HUD
      gap. Pause preserves the exact original GIF border with translated live
      text. English ZIP assets remain unchanged. Visual comparison lives in
      `web/tools/ui-sprites-preview.html`.
- [x] **Automatic end-of-race highlights** — play captured stunt, destruction,
      losing or close-finish footage three times (900 ticks total), with event
      titles and camera cuts;
      Enter/Esc skips to a continue screen. Uses `Record.playh()` even when the
      rolling instant replay setting is off.
- [x] Deploy script with cache-stamped module imports (`deploy.sh`)
- [x] Benchmark harness — `?bench=`, fixed window, freezes on completion
- [x] Packed vertex colour (uint32, 12 bytes/vertex). Measured ~3%.

---

## Car editor + browser storage (branch `careditor`)

See `decompilation/CAREDITOR_PORT_SPEC.md` for the chunk table and the
delegation contract; `decompilation/agy_careditor <chunk>` runs one.

- [x] Browser storage (`web/carstore.js`) — IndexedDB `name -> .rad text`,
      stored cars shadow the four shipped in `mycars/`, and it supplies the
      listing `CarDefine.loadcarmaker()` gets from `File.list()` on the desktop.
- [x] Race a custom car: `web/main.html?mycar=<name>`, and the launcher lists,
      previews, imports and deletes them.
- [x] Chunk `shape` (calibration) — regx, regz, roofsqsh, crash, setheme, py, rot, xs, ys
- [x] Chunk `files` — IO seam + the `.rad` parser
- [x] Chunk `ui` — hidefields, movefield, drawms, stringbutton, ovbutton
- [x] Chunk `ctachm` — the editor's draw and hit-testing pass
- [x] Chunk `tab2` (1,600 lines — split if it comes back thin), `tab0`, `tab1`,
      `tab3`, `input`, `boot`
- [x] ~~The editor shell driving the transpiled panes headlessly.~~ Built, then
      thrown away: running the applet's panes for their button rects inherited
      the applet's UI (Scale and Align sharing a pane, Apply/Save pairs, a
      paginating physics tab) and most of the work went into the bridge rather
      than the editor. See WORK.md 2026-08-03.
- [x] The editor proper (`web/careditor/rad.js` + `editor.js`): the `.rad` text
      is the model, every control reads and rewrites it live, one Save, and the
      preview is the game's own `ContO`.
- [x] Wire save -> `carstore.writeCar` and Test Drive -> `?mycar=`.
- [x] Embed `stat()`/`physics()` in the sixteen base cars
      (`web/tools/embedstats.mjs`) so they are editable and a car saved from one
      is raceable.
- [ ] Retrofit the original sixteen to READ their handling from those embedded
      lines instead of `CarDefine`'s constructor tables, so the `.rad` is the
      single source of truth — the tool reports which fields do not yet
      round-trip (`moment`, `comprad`, `outdam`, `powerloss`, `airs`/`airc` are
      derived from stats and geometry, not from `physics()`).
- [ ] Keyboard navigation, consistently, across the launcher, the editor and
      the in-game menus. The editor's tabs already follow the WAI-ARIA tablist
      pattern (roving tabindex, arrows between tabs) — make that the house
      style rather than a one-off.

## Performance

Measured on the target machine, stage 1, res=2. `simulate()` is 0.6–2.5 ms/tick
(2–7%); **drawing is everything else.** Draw breaks down as:

| layer | share | isolate with |
|---|---|---|
| Canvas2D overlay (HUD text/images) | 1.8% | `?overlay=0` |
| geometry batching (triangulation, vertex writes) | 19% | `?geom=0` |
| projection + traversal (`Plane.d`) | 81% | `?raster=0` |

**Re-measured 2026-08-09 on a healthy machine (governor `performance`,
2.5-2.7GHz).** Steady-state racing, stage 1, 8 cars: `res=1 interp=0` draw
4.7-6.9 ms/frame, `res=1 interp=1` 6.3-7.6, `res=2 interp=1` 7.4-7.8 -- so
**resolution is no longer a lever** (MSAA is off above 1x) and the table below
predates that. `interp=0` cannot exceed 18.9fps by construction, so it is not
a mode any fps target can be tested in.

Cost model: `draw ≈ fixed + ~1.0–1.3us per PROJECTED vertex`. Projected is not
submitted — `Plane.d` transforms 12–20 vertices per face before culling decides
whether to submit any, so ~14,800 are projected to submit ~10,300. Do NOT trust
a fitted intercept: the within-run regression is ill-conditioned (R² 0.07–0.29,
and it has returned a negative fixed cost). Measure fixed costs with `?prof=1`.

- [x] **Account for the ~9.8 ms fixed term.** It was `medium.d()`, and most of
      it was the O(height) scanline fill rather than the backdrop's geometry —
      the trapezoid fill cut the backdrop 6.08 -> 3.83 ms on its own. What
      remains of it (~3.8 ms of sky/ground bands, mountains, clouds) has no
      single hotspot; further gains there mean fewer gradient bands (a visual
      change) or moving the gradient to a fragment shader.
- [x] **Trapezoid fill for concave/self-intersecting polygons.** One trapezoid
      per span per band instead of one quad per pixel row. Emitted verts
      57,069 -> 37,269 (-35%), normalised cost -12%. Exact even-odd, verified
      against a point-in-polygon truth; `?fill=scan` restores the old path.
- [ ] ~~Account for the fixed term~~ (superseded) It scales with nothing, and node
      shows only 1.4 ms fixed on the identical scene. Two candidates: the
      even-odd scanline fill, whose cost is proportional to polygon AREA and
      which no counter tracks; or the fit itself, since two points 1.7x apart
      extrapolate an intercept badly. Settle this BEFORE the shader rewrite —
      if it is real it may be a smaller change for a similar win, and if it is
      an artifact the shader payoff is larger than currently estimated.
- [x] **The replay's ghost buffer was the game's largest allocator** —
      `Record.rec` copy-constructs a whole `ContO` (one `Plane` per face) six
      times a cycle per car, ~74% of all allocation and ~5MB/s, growing the
      heap 57 -> 156MB across a race before a single frame freed 110.9MB.
      ~~Nothing reads it back until the replay viewer is ported.~~ Instant
      Replay now reads these model snapshots to reproduce car damage.
      `Record.ghosts` guards the snapshots, exposed as the launcher's
      **Replay recording** setting (default off) and `?ghost=0`. Measured: heap
      flat at 110-115MB with recording disabled.
      **It buys no frame rate** -- in the representative condition mean fps and
      the frame-gap tail are unchanged with it on or off. Kept for the heap
      alone: the growth is unbounded across a race, and one 110.9MB collection
      was directly observed costing a 133ms frame. Do not cite it as a
      performance fix; the "whole second at 17fps" it appeared to remove was
      measured in the unrepresentative accelerate-held condition, and the real
      cause of those frames was the catch-up draw.
- [x] **Interpolation drew the whole scene twice per ticked frame.** The tick
      draw's vertices were discarded by the interpolated pass; it existed only
      to advance per-tick visual state and refresh `ContO.dist`, and the single
      interpolated draw does both now (`interpolating = !stepped`, plus
      `recaptureDrawOutputs()` so `dist` and the draw PRNG survive
      `restoreCurr`). **Stage 9 451 -> 706 frames/25s (+57%), stage 1 677 ->
      915 (+35%); over 100ms 9 -> 1 and 3 -> 0.** `?tickdraw=1` reverts.
- [ ] **What is left of ">30fps consistently": ordinary draw cost with the
      other cars on screen, worst on the big stages.**
      **2026-09-23, Windows machine (Chromium via Playwright, res default):
      stage 9 parked with the pack in view, `?stats=1&bench=3` -> 60.0fps,
      draw 8.52 ms/frame, worst frame 17ms, 22,992 verts.** On this hardware
      it is solved; the figures below are the slower Linux machine. Reopen
      only with a bench line from the machine that is slow. Parked at the grid with
      the pack in view (`NFM_KEY=none`, the only condition worth measuring in
      — holding accelerate spins the player away from everyone), 25s windows,
      after the face sort, the catch-up fix and the single-draw change:
      **stage 1 ~935 frames (~37fps mean, 0 over 100ms), stage 9 ~672 (~27fps
      mean, 2 over 100ms)**, at 9.5-12.7k submitted and 23-28k emitted
      vertices. So the WORST frames are essentially solved and the MEAN on the
      big stages is not: stage 9 is still under 30. Profile of stage 9 after
      the face sort: `Plane.d` 28.1%, `_fillTrapezoid` 8.6%, `ContO.d` 9.4%,
      `Plane.s` 3.6%, `rot` 3.0%, GC ~2.5% — no idle time at all, and no
      single item worth more than a few percent. The remaining lever is
      drawing fewer faces, not drawing them faster.
- [ ] **The ground shadow is uncounted and costs 20-60ms of a heavy frame.**
      `ContO.d` runs `Plane.s` for EVERY plane of an object -- ~5,000 calls a
      frame with the pack in view, against 1.0-2.4ms when the camera looks
      away, which is why turning away restores 60fps. Each call is three
      `rot()`s plus an O(n^2) sweep for the face's silhouette extremes plus a
      fill. Candidates: one silhouette per OBJECT rather than per plane, or a
      distance/LOD gate like the one `lowshadow()` already provides.
- [x] **The per-object face sort — ~12% of the frame, now an O(n log n) sort.**
      `ContO.d` ranked an object's faces back-to-front by comparing every pair
      (58.6% of that method, ~5,000 comparisons a frame for a 100-face car).
      It computes exactly a stable descending sort by `av`; `#faceOrder` does
      that instead, `?facesort=rank` restores the original, and an integration
      test draws a real scene both ways and compares every vertex word.
      **Measured, stage 9 parked: +30% frames (406 -> 528 in 25s), frames under
      20ms 28 -> 134. Stage 1: +8.5%, under 20ms 307 -> 405.**
- [x] **The 100-330ms frames were the frame loop drawing once per CATCH-UP
      TICK.** A late frame earns extra ticks, each ran a full `gs.draw()`, and
      every batch but the last was discarded — a spiral, since the next frame
      is then later still. Only the last tick of `while (acc >= TICK_MS)` draws
      now; the others call `rebuildNewCars` directly. **Measured, parked:
      frames over 100ms stage 9 17 -> 4, stage 1 9 -> 2, none over 200ms on
      either, total frames +5.5%.** `?catchupdraw=1` restores the old
      behaviour. Found with a per-FRAME count of `Plane.d` calls: `rd.faceCalls`
      resets per batch, so a frame drawing four times reported one draw's
      worth (`face=4087` beside `planeD=16348`).
      Deviation to be aware of: during a catch-up burst the per-tick visual
      effects now advance once per FRAME rather than once per tick, so they
      animate slightly slow while the machine is already behind.
- [ ] **`Plane.d` has no single hot block — stop looking for one.** Line-level
      ticks: initial vertex copy 13.4%, the O(n^2) vertex-pair sweep 12.3%,
      the O(n^2) screen-extent sweep 10.7%, the two projection loops ~12%,
      colour and fog ~5%. Each is 2-3% of a frame, and an A/B of any one of
      them returns noise. The lever here is calling `Plane.d` fewer times —
      face count, LOD, earlier culling — not making it cheaper.
- [!] **Read this before attempting GPU-side projection — it is much bigger
      than the entry below says, and `dist` is not what blocks it.** Four
      things in `Plane.d` consume the transformed coordinates BEFORE anything
      is submitted, so a vertex shader taking over the transform means either
      computing it twice or replacing the game's own culling and shading model:
      1. **Culling.** Seven separate `n45 = 0` rules read the projected screen
         coords (`array26`/`array27`) — every-vertex-off-screen on each of the
         four edges, the `abs3 < 3 && abs4 < 3` sub-3px test — and `av`.
      2. **`this.av`**, the face's distance, is the camera-space centroid plus
         a `gr` term, and it drives the fog ramp, the `fade[disline]` cull and
         the 12/20-vertex LOD.
      3. **Face orientation.** `b4` and `lastmaf` come from comparing the
         SCREEN x/y of particular vertices, and `lastmaf` is written back to
         `m.lastmaf`, which a LATER face reads to decide whether to mirror its
         own geometry (`gr === -11/-12/-13`). It is cross-face state derived
         from screen space.
      4. **Brightness `n66`**, which becomes the vertex colour, is a function
         of `b4` and `av`.
      On top of those, the even-odd trapezoid fill is a CPU algorithm over
      screen coordinates, so every concave face needs them anyway.
      **Also measured (2026-08-09):** `rot` is only 2.4% of a representative
      frame while `Plane.d`'s own body is 25.1%, so the per-vertex rotation —
      the part a shader would take — is not where the time is. And the O(n^2)
      vertex-pair sweep at `Plane.js:487` is NOT the cost either: stubbing it
      out (`b4` forced false) rendered 399 frames in 25s against 391, i.e.
      nothing, so "reorder d() to cull before that sweep" is not worth writing.
- [ ] **GPU-side projection.** The only term that provably scales, and the
      majority of draw. Budget ~2–3x on draw, not 5x. **But it will not fix
      the fps DIPS:** `?res=1` dips noticeably less than `?res=2` on the same
      scene, so the dips scale with pixels and are fill/overdraw-bound, which
      a vertex shader does not touch. Average frame cost is what improves. It is also what would
      make interpolation affordable: per-object transforms as uniforms means
      interpolating costs a lerp instead of a full CPU redraw.
      The old note claiming the painter's sort blocks this was wrong: `dist` is
      per-OBJECT (126 of them), not per-vertex, and stays CPU-side untouched
      while only the vertex transform moves. Still several hundred lines across
      `graphics.js` and `Plane.js`.
- [x] ~~Cache polygon triangulation topology at load rather than re-deriving
      convexity per frame.~~ **Measured and dropped.** `isConvex` is ~29% of
      `fillPolygon`'s ticks and `fillPolygon` is 3.1% of a frame, so the cache
      is worth ~1%; the batcher's 13% is mostly `_fillTrapezoid`, the concave
      path, which a convexity cache does not touch. It is also unsound:
      convexity is a property of the PROJECTED polygon, so a face folded around
      the camera arrives concave and a load-time flag would fan a
      self-intersecting outline. `isConvex` is modulo-free now instead.
      The fill splits 1,895 fan to 490 concave polygons a frame (stage 9,
      parked), the concave ones carrying 3,699 vertices — `rd.fanPolys` /
      `concavePolys` / `concaveVerts` on the fps line. The backdrop's bowtie
      quads are the obvious special case if it is ever attacked.
- [x] **Array pooling is deleted.** Measured as a pessimisation twice — once
      in node, and again in a browser on a representative scene after being
      rewritten to a direct property with no Map and no string key (stage 9,
      445 frames against 514). A small `Int32Array` dies in V8's young
      generation for free; a reused one is promoted and pays write barriers.
      `scratchInt`/`setPooling`/`isPooling`/`?pool=` and the two equivalence
      tests are gone. Original note follows.
- [x] ~~Widen array pooling~~ — **pooling is a 30% PESSIMISATION**
      (10.96 → 14.27 ms, node, identical scene). The earlier "1.13x" figure and
      the in-game A/B that showed no difference were both wrong; the A/B
      compared fps, which is pinned at 18.9. Leave `?pool=` off.
- [x] Packed vertex colour (uint32, 12 bytes/vertex). ~3%, pixel-identical.
- [x] MSAA off above res=1 (~12%); overlay no longer scales with `?res=`.

## Launcher (`index.html` + `web/launcher.js` + `web/preview.js`)

The launcher is the slab-themed menu app: main menu, single player, race
options, multiplayer browser, lobby, settings — and the race itself, in the
same page. `web/menu-mockups.html` is the design study it was built from.

- [x] Car/stage picker with names, live rotating 3D car preview (the game's own
      car-select camera and car-maker spin), stat bars from CarDefine's tables,
      the NFM face keyed as `loadude` keys it. ~~a collapsed advanced panel
      carrying every query parameter~~ — replaced by a Settings screen carrying
      only what a player changes (name, sound, music, resolution, smooth
      frames), persisted in localStorage. The dev knobs are URL parameters on
      `web/main.html`.
- [x] Audio volume settings — sfx and music volume sliders in the advanced panel,
      piped to the audio and music modules.
- [x] Stage-specific opponent grid (`xtGraphics.sortcars`).
- [x] Overhead stage preview rendered with the real renderer (`m.trk = 2`).
- [x] ~~**Stage 8 renders nothing in the overhead view** and falls back to the
      flat map. 172 objects pass the object-level gates and emit 24 vertices;
      the cause is inside `Plane.d`'s face culling at ~91k depth and is not
      understood. Stages needing more than 85k depth all take the fallback,
      so any large stage is affected.~~ Fixed: it was the `Plane.xs`
      `Math.imul` int32 wrap at the far camera (see `preview.js:443`); the
      camera now stands close and shrinks `focus_point`. **Measured
      2026-09-22 in Chromium: all 32 stages render, stage 8 = 48,051 verts,
      minimum 2,220 (stage 16), none below the 200-vert fallback.** Stage 16
      ("The Stretch") is a thin line by design: one ~100k-long straight.
- [x] ~~Reported but unconfirmed: "some tracks have textures extending off
      screen" (stage 9). Not reproduced since the camera-clearance fix; needs
      a look with fresh eyes.~~ Looked at 2026-09-22 (stages 8, 9, 25
      screenshotted): nothing leaves the frame. Closed.
- [x] **Menu-mockup backdrops should be CSS, not a canvas** — done for the
      `slab` backdrop the real launcher uses: a `repeating-linear-gradient` on
      an overhanging strip, translated exactly one period, compositor-only and
      with no rAF loop at all. `web/menu-mockups.html` itself is unchanged and
      still paints its five backdrops on a canvas; it is a reference document
      now, not a page anyone plays through. Original note:
- [ ] **Menu-mockup backdrops should probably be CSS, not a canvas**
      (`web/menu-mockups.html`). Redrawing a full-screen canvas is the biggest
      CPU cost on that page — it is throttled to 20fps only because it is
      expensive. Every backdrop there is a repeating pattern under a fixed
      transform: neon is horizontal lines sliding down from a screen midpoint,
      which a `perspective()` + `rotateX()` on a `repeating-linear-gradient`
      reproduces exactly, with the scroll as a compositor-only `translateY`
      (see the hazard rail for the seamless-loop arithmetic). Same shape for
      pixel's starfield and slab's bars. That would take the page's steady-state
      cost to ~zero and remove the rAF loop entirely.

## Launcher bugs found in real use (2026-08-06)

Reported after the first hands-on session with the new launcher. Two are
fixed and verified, one is diagnosed and mitigated, one is measured and open.

- [x] **Chat showed the wrong names** — host saw its own name twice, guest saw
      "me" and "Player 1". Root cause is a dropped greeting: `NetPeer` had a
      no-op `onMessage` until a `Lobby` was constructed, and a guest greets the
      instant its channel opens, which can be while the host is still inside
      `openRoom()`. The greeting is sent ONCE, so losing it is permanent: that
      guest is never seated, keeps `localIndex = -1`, and its chat goes out as
      slot 0 — the host's slot — which is exactly the symptom. Fixed in the
      transport (early messages are queued and delivered when a handler
      attaches) with a re-greet every 2s in `netlobby.js` as a safety net.
      `browserlobby.mjs` now asserts ATTRIBUTION and not just delivery; the
      old check passed while the lobby was thoroughly confused about who was
      who, because every line still arrived.
- [x] **"invalid action in WS response: undefined" when clicking Host.** Not an
      error and nothing had failed: it is a tracker answering with something
      `bittorrent-tracker` cannot parse, which costs nothing while another
      tracker answers. `netpeer.js` was passing tracker warnings to `onStatus`,
      which the launcher shows to the player — and since connection status is
      now sticky, a raw library error sat on screen at the moment of pressing
      Host. Warnings go to `console.warn` now.
- [x] **The game list is unreliable, and joining took ~30s.** Root cause found
      and it was one bug wearing two faces. p2pt's `setIdentifier()` is async
      and unawaited, so the SECOND swarm in a page announces with no
      `info_hash`; the tracker rejects that with a message carrying no `action`
      field, and bittorrent-tracker treats an unrecognised action as a socket
      error — `destroy()` plus a 10s + up-to-300s backoff. Sockets are pooled
      per tracker URL across every p2pt in the page, so opening a room took the
      DIRECTORY down with it. That is what the "invalid action in WS response:
      undefined" warning was; it was written off as tracker noise.
      Fixed structurally rather than patched: **one swarm** (`nfm-v1`), a room
      is a label in the frame (`[tag][len][room]`), and joining a listed game
      is a message to a peer already connected. The second p2pt instance, the
      pooled-socket collision, `requestMorePeers()` polling and
      `JOIN_TIMEOUT_MS` on the listed path are all gone.
      **Measured** (`browserlobby.mjs`): join **31.5s -> 0.6s**, hosting local,
      and `browsern.mjs 25 3` still pairs three browsers by code, races and
      chats guest-to-guest. `Mesh.lock()` bounds the mesh at race start by
      dropping every peer outside the room. **Measured with 2-3 peers only** —
      the mesh cost at 20 is unknown.
- [x] **Re-measured 2026-09-22, not reproduced:** cold load (fresh browser
      context, empty cache) to "press enter to race" with the stage preview
      drawn is **0.47s** on a 12-thread Windows machine, and **2.2s with CPU
      throttled 6x** (CDP `Emulation.setCPUThrottlingRate`). Reordering the
      boot to show the menu first is not worth it at that size. Original
      entry, measured on the machine pinned at 798 MHz:
- [ ] ~~**First load takes ~10s of CPU, and this machine makes it ~13s.**~~
      Measured per phase in a browser (`ready` is before the previews draw):
      import modules 0.9s · `initPreview` (loadbase parsing models.zip) 2.8s ·
      `loadCustomCars` 0.5s · 32x `stageName` 0.9s · `faceURL` (unzipping a
      715 KB images.zip for one face) 1.2s · `drawCar` 0.5s · `loadStage(1)`
      1.8s · `drawStage3D` 1.5s. **Warm load transfers 0 bytes and still takes
      11.5s, so it is not the network.** Two things to do: the boot is serial
      when most of it need not be (the menu needs none of the previews — show
      it immediately and load behind it), and **the CPU was pinned at 798 MHz
      again** (`intel_pstate` passive + `powersave`, the trap in WORK.md), so
      every figure here is up to 3.6x pessimistic. Check the clock before
      optimising any of it.

## Multiplayer race lifecycle (2026-08-06)

Walked the whole lifecycle after two bugs were reported, rather than fixing the
two. The seam work below is done. **The rules INSIDE the race are not, and are
not to be hand-patched further** — see the porting job at the end of this
section, which is the next piece of netplay work.

Done, all of it on our side of the seam and none of it superseded by the port:

- [x] **A netplay race ran at `fase = 0`** — the game believed it was single
      player, so every `fase === 7001` branch of `stat()` was dead code. That
      was both reported bugs at once (the wasted announcer read out CAR names;
      a wasted player was dropped back to the launcher instead of spectating).
      `main.js` now sets `fase`/`lan`, which is the wiring the ported branches
      need in order to run at all.
- [x] **Nothing handled a disconnect anywhere** — `NetPeer.onClose` was
      assigned by no one. A guest that closed its tab stayed on the roster and
      was seated into the race; mid-race its car dead-reckoned forever, never
      became `dest`, and an "everyone else is wasted" race could never end.
      Handled in the lobby (roster, host-gone) and in the race (`playerGone`
      wastes the car and sets the original's `dested = 3`).
- [x] **A departing player takes the cars they OWNED with them.** The host owns
      the bots, so a host leaving stranded one car per bot — nobody transmits
      them, so they never get wasted and the survivors' race cannot end. There
      is no host migration to hand them to, so they are wasted too.
- [x] **Leaving is no longer a disconnect**, because one swarm means the peer
      connection outlives both the lobby and the race — so quitting SAYS so
      (`{t:'bye'}`) in both, and `NetPeer.flush()` gets the goodbye out before
      the page reloads.
- [x] **The race start was not gated on both worlds existing.** Each client
      loads its own assets after the lobby says go, so the faster machine ran
      the countdown alone and raced into a car still parsing zips.
      `waitForEveryone()` barriers on a `ready` broadcast, with a timeout so
      one broken load cannot strand everyone.
- [x] **A race returns to the ROOM, not the main menu.** The reload stays (see
      WORK.md), and the room survives it in `sessionStorage`; the host reopens
      the same code and guests re-`find` it, which is affordable only because a
      join is now a message.
- [x] `pos` was transmitted and dropped on receipt; the received `holdit` is
      now recorded, and separates "quiet because they finished" from "quiet
      because the network broke".
- [x] **Classic race controls and pause menu.** The Java's A/S bindings now
      toggle the existing `XtGraphics.arrow()`/`radarstat()` HUD; movement uses
      the original arrow keys. Escape opens the original `paused.gif` panel
      from `xtGraphics.pausedgame()`, with keyboard and pointer selection for
      Resume, Instant Replay, Game Instructions and Quit. The instructions
      view is adapted for the browser. Instant Replay plays the last 300 ticks
      in solo races when Replay recording is enabled in launcher Settings;
      Enter, Escape or Skip returns to the pause menu. Solo play stops
      simulation while paused;
      multiplayer keeps the shared race running and shows Resume / Quit.
      Quit uses `leaveRace()` so online peers receive `bye` and the player
      returns to the lobby. `multistat()` and the rest of the original GUI
      remain unported.

### [ ] Port the multiplayer race lifecycle — the next netplay job

Every bug found by playing this session was a branch of the original that is
not ported, and each was hand-patched one report at a time. Three deviations
inside the transcription were written and then **reverted deliberately**, so
that this port starts from an unmodified `XtGraphics`/`Mad`:

- `XtGraphics:936`'s `im != 0` term, which ejects a wasted GUEST after 1200
  ticks while the host spectates indefinitely. Reverted to the Java.
- A `colme` per-car timer added to attribute kills: `Mad.lastcolido` is set by
  a collision with ANY human, so `dested == 2` means "a person hit them
  recently" and every client claimed every human's kill ("You wasted Player 2"
  when the AI did it). The original carries attribution over the wire instead
  (`multion >= 2` prints `plnames[im] wasted plnames[n9]`); that is what to
  port. Reverted.
- Escape-to-leave was replaced by the in-race Resume / Leave menu. The menu
  stays in the browser shell until `multistat()` is ported.

**Scope: the race only.** Port `multistat()` and the 11 `multion`/`lan`
branches of `stat()` marked `// TODO not ported:`, plus the multiplayer arms of
`GameSparker`'s race loop. **Not** `UDPMistro`/`udpServe`/`udpOnline` — that is
DatagramSocket plumbing with no browser analogue, and the part of it that is
game logic (`setinfo`/`readinfo`) is already transcribed in `netcodec.js`.
**Not** the lobby or menu screens either: the launcher replaces them
deliberately, and the Java's are mouse-driven applet UI.

Follow `decompilation/PORT_SPEC.md`'s "Calibrate before batching". The wiring
these branches need already exists — `fase`/`lan`, `plnames`, `humans`,
`isbot`, `dested = 3` on disconnect — so this is transcription, not design.

## Rendering / correctness

- [x] **Stop patching per-effect state field by field — mark the pass instead.**
      Done, and it took TWO mechanisms because the effects break in two ways.
      **Counters:** `Medium.interpolating` marks the redraw and each effect
      guards its own advance at the mutation (`if (!this.m.interpolating)`) —
      repair sparkle, dust stages and drift, crash-spark spawn/velocity/stage
      (every interpolated frame was seeding another 100 sparks), the electric
      ring's `elc`, checkpoint flicker, lightning, `noelec`, star twinkle, and
      `Plane`'s whole damage animation (`embos`, and the `chip` debris with
      its own velocity integration) which no list had ever covered.
      **Shape:** effects roll their geometry straight out of `random()`, so a
      redraw drew a *different* random shape rather than a later one — that is
      what made the repair ring's electricity buzz. Fixed once, centrally:
      `Medium.random()` records the tick draw's sequence and an interpolated
      pass replays it, armed in `Medium.d()` (always draw's first call). No
      per-call-site caching, which matters because `Plane.s()` alone has ~30
      shape-rolling randoms.
      `OBJ_STATE`, `OBJ_ARRAYS` and the effect half of `MED_STATE` are gone
      from `main.js`, which now snapshots draw's one real output, `ContO.dist`.
      Regression test pins both halves: *"an interpolated draw advances no
      per-effect animation state"* also asserts two interpolated frames of one
      tick are vertex-identical.
      The stronger version is still open: don't re-execute `draw()` at all,
      keep the tick's vertex buffer and re-project it, which removes the class
      outright rather than requiring the guard to be remembered.

- [x] HUD vanished with `?interp=1` — `rd.begin()` clears the 2D overlay, and
      the interpolated redraw ran it after `simulate()` had drawn the HUD
      there. Interpolated frames now pass `keepOverlay`.
- [x] **Interpolation jitter — fixed, and interpolation is now the default.**
      Cause: `Medium.sin`/`cos` index a
      360-entry table by whole degrees, so blended headings were rounded to 1
      degree — ~13px of yaw — giving smooth translation with stepped rotation.
      Hence jitter on turns only. Both now interpolate between table entries
      for fractional arguments; integers take the old branch, so the
      simulation is unchanged. Confirmed smooth in play, so `?interp=1` is now
      the default and the game renders at display rate (~58fps measured)
      instead of the 18.9fps tick rate. `?interp=0` restores tick-rate drawing.
      `cam=` is gone: re-derive was structurally unfixable, since `follow()` is
      a stateful ease rather than a function of the interpolation fraction and
      lurched once per tick regardless.
- [x] **Seeded PRNG on the Java side.** ~~Patch + recompile `Medium.class`~~ —
      not needed: `MadProbe.seedMathRandom` swaps the JDK 8
      `Math$RandomNumberGeneratorHolder` Random via `Unsafe` for `web/java.js`'s
      xorshift32, so the UNMODIFIED `Game.jar` runs deterministic (3 runs,
      identical). It reproduces the port's sim/draw stream split too
      (`ContO.dust` on the draw stream, own `Medium` bank). `Mad.test.js` now
      asserts `zy`/`xy`, and doing so found **five real float/double rounding
      bugs in `Mad.drive`** (`bounce - 0.3/0.4` is double in the bytecode; the
      port rounded it to float32; plus `contO.y -= tilt/1.5`) — fixed, Java and
      JS identical on every printed tick of 300. **Audit, 2026-09-22:** a
      regex cross-reference (`fr(A op LIT)` in JS vs the same operand beside a
      DOUBLE literal in the Java) found 13 suspects; `javap` confirmed 8 more
      bugs, all fixed — `Mad.js` `bounce - 0.2` x4, `gr += abs(n*1.5)` x3
      (all-double Case A: `i2d … dadd; d2i`, so no `fr` at all), and
      `Plane.js` `fr(fr(projf / deltaf) + 0.3)` (the `fdiv` IS rounded).
- [x] **Float/double audit of the rest of the port — done 2026-09-23 with a
      tool.** `web/tools/FloatAudit.java` (javac-attributed types per node) +
      `web/tools/compound-verdicts.py` (the jar's opcodes for every compound
      assignment) + `web/tools/float-audit.mjs` (pairs each Java statement with
      the port's, compares fr()/idiv, `--fix` rewrites the AST). Run recipe in
      the FloatAudit.java banner. 1925 of 2467 numeric Java statements paired
      (Mad 532/558, Wheels 225/225, Record 123/123, Plane 140/147, ContO
      269/302, Medium 197/234; GameSparker/xtGraphics are restructured or
      unported). Fixed: ~250 missing fr(), ~60 fr() on a double or int, 13 int
      divisions written as float ones -- among them `swits / 2` and
      `handb / 2` in `Mad.drive`, the gear thresholds and top speed. New
      differential `Mad.test.js` "odd gear thresholds" (MadProbe
      `-Dnfm.odd=true`, `MadProbe.odd.expected.txt`): the old port left Java
      at tick 34, the fixed one matches all 300 ticks. Not covered: the 18
      int->float conversions (exact below 2^24, `--int2f` lists them) and the
      ~540 unpaired statements (`--unpaired`).
      Original note: 354 `= trunc(fr(` lines carry a double literal or `Math.*`; a
      10-line sample had 2 wrong (`CarDefine.js:453` `clrad`: extra `fr`
      around `* 1.5`; `ContO.js:1751` `160f + 160f*x`: float product not
      rounded before the add). Two error shapes: `fr()` around a DOUBLE
      subexpression, and a FLOAT op left unrounded inside a double one.
      Proper fix: type every subexpression from the bytecode (or javac's
      attributed tree of the decompiled source, whose literal and variable
      types are reliable — only compound-assignment casts lie) and diff the
      port's `fr()` placement against it.
- [x] Stage-specific opponent grid — `xtGraphics.sortcars()` ported. Draws
      slots 1..6 by rejection sampling biased toward faster cars in later
      stages, then forces specific opponents for stages 10/12/14/15/16.
      `?cars=same` restores one-car-for-everyone.
- [x] Car stats bugs in index.html — the launcher showed four invented stats
      normalised against the roster maximum. Replaced with the car-select
      screen's own six bars and its own absolute formulas
      (`xtGraphics.java:6096-6131`): Top Speed, Acceleration, Handling,
      Stunts, Strength, Endurance.


## Remaining Tasks

- [x] Backend: `web/audio.js` decodes sounds.zip into AudioBuffers and plays
      one-shots. Autoplay-gesture unlock on first key press; missing zip or
      absent Web Audio costs sound and nothing else.
- [x] `crash` / `skid` / `scrape` / `gscrape` ported from xtGraphics.java
      (9289-9430), rotation counters and `bfXXX` debounce included.
- [x] **A sound effect plays once per race, then never again** — fixed by
      `playsounds()` below, which decrements the `bfXXX` counters. Covered by
      *"playsounds decrements the sound debounce counters"*, which asserts a
      second crash actually sounds rather than just checking the field.
- [x] **`playsounds()` — ported** (`XtGraphics.playsounds`), with
      `sparkeng()` and `stopairs()`, called once per tick from the end of
      `GameSparker.simulate()`. It decrements every `bfXXX` debounce counter
      and drives the engine and air loops. Two tests pin it: the counters
      clearing, and the tick actually calling the pump — porting the method
      and leaving it unreferenced would otherwise pass everything.
      **Not ported:** the `multion==2/3` branch that mirrors player 0's mute
      flags onto a remote player, and the `app.applejava` clip-reopen
      workaround; both marked `// TODO not ported:` at the site.
- [x] **Engine sound.** The looping clip type is in (`web/audio.js`:
      `loop`/`stopLoop`/`isLooping`/`stopAllLoops`; muting cuts live loops),
      the 25 numbered samples and `air0`-`air5` decode, and `sparkeng()` holds
      exactly one of five engine clips looping per rev band.
      **Confirmed by ear in a real browser (2026-08-01) — the only oracle that
      counts for audio.** `checkopen()`'s clip reopening is deliberately not
      ported; Web Audio has no equivalent failure to work around.
- [x] **Race start and finish wired up.** `resetstat()` now ports the whole
      per-race reset (`xtGraphics.java:1484`), not just the music load; its
      `starcnt = 130` / `gocnt = 3` are what arm the intro fly-by and the
      3-2-1-GO. Both sequences were already ported in `GameSparker.simulate`
      and `stat()` but unreachable with the counter left at zero. The
      end-of-race overlays (`youwon`, `youlost`, `yourwasted`, `youwastedem`)
      and the countdown's `d1/d2/d3.png` faces now load in `images.js`;
      `gamefinished`/`disco`/`wgame` stay out, being multion-only in the
      Java's own `snap()`. `fase == -2` -- the Java's leave-the-race signal --
      returns to the launcher, since the menus are not ported.
- [x] Remaining one-shots: all bound to real clips via `XtGraphics._clip`.
      ~~`checkpoint`, `wasted`, `powerup` and the countdown are wired but still
      have no call site~~ — stale: `stat()` now calls `three/two/one/go`,
      `checkpoint` and `powerup`, `wastd.loop()` is in, and `firewasted` /
      `carfixed` were already live. **Verified 2026-09-22** by patching
      `Audio.prototype.play` in a real race: three 5.4s, two 6.0, one 6.6,
      go 7.2, then tires, checkpoint, lowcrash1 while driving. Every race-time
      `.play()`/`.loop()` in the Java has a port call site; the two missing
      `powerup.play()` are in `rad()` and `credits()`, i.e. the unported menus.
- [x] **Music — done, without porting the tracker.** `web/music.js` plays the
      game's own `.mod` modules through **BassoonTracker**, a pure-JS MOD/XM
      player vendored into `web/vendor/` (48.8 KB raw, **16.3 KB gzipped**,
      MIT). Wired to the existing `strack`/`loadedt`/`mutem` call sites, so
      `playsounds()` is untouched; `resetstat()` loads the stage track and
      handles `loadstrack`'s one special case (stage 27 is `party.zip` when
      `gmode == 2`). All 34 `[gain, rate, bpmflex]` triples are transcribed and
      **verified against the Java by a test that parses `loadstrack` itself** —
      a shape-only test would pass with every number wrong.
      `gain/300` maps to the master gain. `rate` is deliberately NOT applied:
      it set the mixer's sample rate, shifting pitch and tempo together, and
      faking it with `playbackRate` would detune the music. `trackvol` is
      accepted and ignored, because `loadstrack` only uses it for custom
      `mystages/mymusic` tracks (where it IS the gain), not for stock stages.
      **Costs 3.3 MB — the modules already in the repo — versus 54 MB of Opus
      or 203 MB of FLAC for pre-rendering, or ~2,500 lines to port `ibxm`.**
      Verified: 119/119 tests, and a headless browser boot where the module
      fetches, unzips and parses with no warning. **Audible check outstanding**
      — headless Chromium produces no sound, so someone has to listen.
      Not sample-identical to the desktop game: BassoonTracker's mixer is not
      `ibxm`'s. Uses a `ScriptProcessorNode` (deprecated but functional).
- [x] ~~Port the `ibxm` tracker~~ — **no longer needed.** `Data`, `Sample` and
      `Envelope` are ported and verified (steps 1-2, kept: they cost nothing to
      keep and are the reference if bit-exactness is ever wanted), but
      `Channel`/`Module`/`IBXM` and the `RadicalMod` wrapper layer are dropped.
- [x] ~~Pre-render the soundtrack at build time~~ — built and working
      (`tools/bake-music.sh`, `web/tools/BakeMusic.java`, output gitignored),
      but superseded: 203 MB FLAC / 54 MB Opus against 3.3 MB of modules.
      Kept as a fallback. Its loop points come from `rollBackPos`/`rollBackTrig`
      and needed a fix — `SuperClip` compares the latter against bytes
      REMAINING, so loop end is `length - rollBackTrig`, not `rollBackTrig`.
- [x] **Finish screen and car select — ported (2026-09-23).** After the race
      (and its highlight) comes GameSparker fase -4 (`mdness` + face over the
      frozen frame, then `fleximage()`'s 7-tick bleed) and -5 (`finish()`:
      You Won / You Lost, stage name, Continue). Single player then goes to
      the original car select (`inishcarselect`/`carselect`, smoke-burst
      backdrop, spinning car, stats, arrows, Continue) with the stage
      `finish()` chose -- the next one after a win -- and on to the race.
      The launcher stands in for `maini2` (finish's fase 102), which is not
      ported. Both screens draw on `web/canvas-graphics.js`, a Canvas2D
      Graphics2D that keeps call order: they put images UNDER geometry, which
      the race surface cannot. Flow: `web/carselect.js`, launcher
      `startCarSelect`/`resumeCareer` (sessionStorage `nfm.next`).
      **Music:** the car and stage selects play `web/sounds/select-your-car.mp3`
      (an `<audio>` element, looped, at the Music volume), in place of the
      menu track the Java plays there (`intertrack`); Esc back to the menu
      resumes the menu track, the race stops it. A deliberate change from the
      original, at the user's request; the original assets are untouched.
      The main menu's Single Player opens the car select directly (the old
      single-player page is kept but unlinked; Esc returns to the main menu).
      Menus tick at 40ms, not the race's 53ms: GameSparker tunes its sleep to
      10 frames per 400ms outside fase 0/-1/-3/7001 (GameSparker.java:1737).
      Measured 25.0 ticks/s; a carselect tick costs ~1.4ms, so the rate, not
      the drawing, is what the eye sees.
      **Not ported:** the online/custom-car branches of carselect (cfase
      3/5/7/8/9/10/11/100/101, Car Maker / My Cars / Top 20 buttons), the
      multiplayer colour pickers, `setcarcookie`/`sendwin`, mouse
      (`ctachm`), and all cars stay unlocked (gmode 0). `?debug=1` on
      main.html exposes `window.__nfm` to force a finish (`xt.fase = -2`).
- [x] **Stage select — ported (2026-09-23).** After the car: fase 2
      (`loadingstage`, then `loadstage`) and fase 1 (`trackbg`'s scrolling
      backdrop, `Medium.aroundtrack`'s fly-in, the stage objects in the
      Java's rank-sort order, `stageselect()` over it). Left/right step the
      stage and reload it, Enter races it, Esc goes back to the car select.
      `loadstage` got back the dropped fase-2 block that starts the fly-in
      (`GameSparker.java:2736`: `hit = 45000`, `trx/trz` at the stage centre,
      the 65/25/735/425 viewport). Canvas2D holds 60fps on it.
      **Not ported:** the AWT choices (NFM 1 / NFM 2 / My Stages / Top20 /
      Stage Maker, the stage list, Normal/Practice) and the account screens
      behind them; the gmode 1/2 career rules and fase 4 (`cantgo`); the
      "Exit X" button and mouse. As in the Java, a stage past 27 becomes a
      random one of the 27.
- [ ] Menus — the rest of `xtGraphics` (maini/maini2, credits, inst, …).
      Genuine brute work; the one part of this port that would suit a subagent.
      **Follow `decompilation/PORT_SPEC.md`'s "Calibrate before batching" procedure** — one
      representative class first, catalogue every systematic error into the
      template, and only then fan out. See also the warning in `WORK.md` about
      subagents editing tests green.
- [ ] `CarMaker` / `StageMaker` — on decompilation/PORT_SPEC.md's drop list.
      CarMaker: done as `web/careditor.html`. StageMaker: see below.

## Career (2026-09-23)

- [x] **NFM 1 / NFM 2 careers.** Single Player opens the Java's maini2 choice
      (NFM 1 / NFM 2 / Free Play, launcher page `gm`). Progress is the
      user.data `NFM1(car,unlocked)` / `NFM2(car,unlocked)` lines, in
      localStorage `nfm.career` (`web/career.js`, same range checks as
      GameSparker.java:3222). Ported: inishstageselect's career rules
      (including the Java's `unlocked[0] != 17` typo), fase 4 `cantgo` with
      `pgate.gif`, the fase -4 n7==0 save (GameSparker.java:1633), the race
      as seven cars drawn by sortcars (loadstage cuts NFM 1 to five). The car
      locks, "Stage N is now unlocked!" and `++unlocked` were already in
      XtGraphics. The HTML stage choices are hidden in a career, as the Java
      shows `sgame` only in gmode 0.
- [x] **Settings -> Unlock everything** (the port's): the careers see
      unlocked = [11, 17]; the saved career is never overwritten by it.

## Stage maker (2026-09-23) — `web/stagemaker.html`

- [x] **Phase 1: build and race a custom stage.** HTML editor in the car
      maker's style over an overhead WebGL map (`stagemaker/map.js`, trk=2).
      Place/move/rotate/delete, roads snap end to end, checkpoints mount on
      the road under them (30 asphalt / 32 dirt), hoop height, laps,
      save/new/save as/delete/import/export (IndexedDB `nfm-stages`,
      `web/stagestore.js`), editable .txt source. Saving runs a faithful
      `sortstage` (`stagemaker/sort.js`), checked against the real
      `StageMaker.sortstage` by `web/tools/SortStageProbe.java`: 32/32 stock
      stages hash-identical (`sortstage.expected.json`). "Test drive" races
      it through `main.html?mystage=<name>&from=stagemaker`; leaving returns
      to the editor. Launcher menu entry "Stage Maker". Spanish: `i18n-stagemaker.js`.
- [x] **3D view (2026-09-23)** — orbit camera over the stage as the game
      draws it (loadstage builds sky, fog, clouds, mountains, lights, walls;
      the parts are the editor's own objects, so edits show at once). Place,
      select, drag, rotate and delete work in 3D as on the map; clicks go
      through `View3D.toGround`, the inverse of ContO's projection. Empty
      ground drag orbits, right/Shift drag slides, wheel zooms; "✋ Hand"
      makes every drag move the view (map too). The applet's walk-through
      camera (View tab, zy=6) came first; the user found it unusable.
- [x] **Undo / redo (2026-09-23)** — ↶/↷ buttons, Ctrl+Z / Ctrl+Y; fixing
      hoops selectable (pick at drawn height, smallest part wins) with the
      height field editing the selected hoop.
- [x] **Phase 2 (2026-09-23): scenery and sound track.** A "Scenery" card
      (`stagemaker/scenery.js`) edits StageMaker's Atmosphere / Colors /
      Scenery / Sound Track lines with the applet's ranges: sky, dust/fog,
      ground, clouds (coverage 0..10, height -500..-1500), ground texture
      (20..60), the RGB mask (-60..60, sliders capped at 200; <= 110 writes
      lightson(), as the applet's Car Lights rule), density 3..8, near/far
      5000..8000, mountains seed, and soundtrack(name,vol,KB) from
      mystages/mymusic with a Listen button. In the race, stage < 0 plays its
      soundtrack from mystages/mymusic with trackvol as the gain (loadstrack's
      n < 0 branch), or nothing without one -- no more stage-2.zip 404.
      Not ported: the applet derives trackvol from the module's loudness
      (220 / (rvol / 3750)); here it is a slider.
- [x] **Imported songs (2026-09-23), the port's.** "Import song…" keeps any
      MP3/OGG/M4A/WAV/FLAC (or .mod/.xm) in IndexedDB `nfm-music`
      (`web/musicstore.js`); soundtrack(name,...) names it. music.js plays
      audio files through an <audio> element (a second backend beside
      BassoonTracker) and imported modules through the tracker. A recorded
      song cannot be converted to MOD -- MOD is a score over short 8-bit
      samples -- and needs no converting: the browser decodes it natively.
      The desktop game races such a stage in silence (no such file).
- [x] **Phase 3 (2026-09-23): custom stages in the stage select.** The
      Java's two AWT Choices at y=62 (xtGraphics.java:1932-2170) as HTML
      selects over the canvas: game (NFM 1 / NFM 2 / Custom = the Java's
      "Stage Maker" list / All, the port's) and the stage within it. The
      arrows walk the Java's 1..27 as before; on a custom stage (stage -2,
      which the Java gives no arrows) they walk the custom list, and in All
      they run on from 27 into it. `preview.loadStage(name)` builds a custom
      stage; the launcher races it with `?mystage=` and remembers it
      (`S.mystage`). Not ported: My Stages / Top20 (need the account server).

## Replay screens (2026-09-23)

- [x] **Both replays draw the original UI on the canvas; the HTML panels are
      gone.** Instant Replay (GameSparker fase -1): `xt.replyn()`'s blinking
      "Replay  >" / "Replay  >>", 300 frames, Enter/Space/Esc skip to the end
      and back to the pause. End-of-race highlight (fase -3): `xt.levelhigh()`
      (the `gameh.gif` header, the blinking case title -- You Wasted 'em! /
      Close Finish! / Wasted! / Stunts! / Best Stunt! -- and "Press [ Enter ]
      to continue"), and the camera is now the Java's n7/n8/n9/n10/n11 state
      machine transcribed as-is: the frame counter pauses while a camera cut
      runs, the black first frames and the per-case white flashes are drawn,
      and it ends by itself after three passes. This replaces an
      approximation that cut by fixed frame numbers. Text is drawn once per
      tick, flashes are geometry in every frame's batch.
      The pause menu itself stays HTML (by choice); its "not ready" notice is
      now styled and worded as the Java's `cantreply()` -- the blue rounded
      box at (200, 73, 400x23), over the pause, gone after 150 ticks. All of
      it is in the Spanish dictionary, checked by a test (`GAME HIGHLIGHT`
      is part of gameh.gif and stays English).

## Language (2026-09-23)

- [x] **English / Spanish, from Settings -> Language.** `web/i18n.js` holds the
      dictionary (exact phrases plus patterns for the ones around a name or a
      number). The launcher and the HTML race menus are translated by a
      MutationObserver over the document; the game's own text is translated
      in `drawString` AND `stringWidth` of both surfaces, so `drawcs()` still
      centres what is drawn. Stunt call-outs are assembled from fragments at
      runtime, so those fragments are translated where `XtGraphics` builds
      them. Car and stage names pass through. The switch is LIVE, no reload:
      left/right on the row only picks ("Español — Enter to apply"), Enter
      applies, leaving Settings drops an unapplied pick. Each text node
      remembers the English it was written in, so it re-translates both ways.
      In Spanish the car-select stat labels are right-aligned to their bars
      (the Java's hand-placed x only fits the English words).
      **Not translated:** text baked into the game's GIFs (HUD labels, Select
      your Car / Stage, Continue, Back/Next, Congratulations, Game Over, the
      paused.gif menu) and dev/log output.
      **The car maker is translated too (2026-09-23):** its dictionary is
      `web/i18n-careditor.js`; the applet's long physics/crash help texts are
      translated by index in `careditor/helptext-es.js` rather than keyed by
      their exact text; multi-line messages translate line by line, and HTML
      text is matched with its whitespace collapsed. The language is read on
      load there (it follows the launcher's setting). The Java-era canvas tabs
      (`careditor/tab0-3.js`, not shown in this layout) are not translated.

## Netplay (private multiplayer)

Decided 2026-08-02, nothing built yet. Static hosting only (GitHub Pages), so
there is no backend of ours anywhere in this design.

- **Transport:** p2pt over public WebTorrent WebSocket trackers (swapped from
  PeerJS 2026-08-06), one reliable/ordered DataChannel per peer. **One swarm**
  (`nfm-v1`) that every client announces on; a room is a LABEL carried in the
  frame, not an identifier of its own, so joining a game you can see costs no
  network at all. Full mesh: every peer talks to every other, and there is no
  relay.
- **Sync:** lockstep with a fixed input delay. Rollback is wanted later, so
  keep the world's snapshot/restore path (`main.js`'s `capture`/`restore`)
  general rather than assuming inputs never need re-simulating.
- **First cut:** 2 players, join by URL room code, AI fills slots 2-6. No
  lobby, chat or player list — those are in the ~9600 unported `xtGraphics`
  lines and the 11 skipped multiplayer branches of `stat()`.
- [x] **Determinism groundwork** — done. Seeded sim/draw PRNG split (`java.js`),
      baked `trig.js` tables, and a test that two runs reach bit-identical
      state whatever they draw. Confirmed it fails with the split disabled.
- [x] **Prototype works.** `netsync.js` (lockstep
      rules, 12 tests), `netpeer.js` (PeerJS transport), launcher UI, and
      `web/tools/netloop.mjs`, which runs two clients in separate processes
      through a lossy relay. **Syncs for ~1118 ticks (~60s of racing), then
      diverges via collision damage.** The lead is recorded: the collision
      MESH already differs at tick 3, long before any position does, and
      nothing in Mad writes it in those ticks — so the cause is at or near
      world construction, not in the tick loop. Untested end to end in a
      browser: PeerJS could not be exercised headlessly, because
      `--virtual-time-budget` starves the real network.
- [x] ~~Determinism groundwork, FIRST and verified on its own.~~ A desync found
      after the transport exists is very hard to attribute; found now it is a
      unit test. Two parts:
      - Seed `Medium.random()`. Note it is consumed by the DRAW path too
        (`Plane.s()` rolls ~30 per face, and the interpolation replay already
        records and replays that sequence), so sim and draw need SEPARATE
        streams or two clients rendering different numbers of interpolated
        frames will desync the simulation.
      - Bake `Medium`'s `tsin`/`tcos` as literal float32 constants. They are
        built from `Math.sin`/`Math.cos` at init, which are not guaranteed
        bit-identical across JS engines, and players will be on different ones.
      - Acceptance: two independently built worlds tick 1000 times to
        bit-identical state, asserted in a test.
- [x] ~~**The browser-level sync check has never compared anything.**~~ Gone with
      lockstep, and it cannot recur: state sync measures drift against a packet
      that has by definition already ARRIVED, so there is no tick-in-flight to
      race. Original note kept below for the shape of the bug.
- [x] **The browser-level sync check has never compared anything, and reports
      that as success.** `netCheck()` (main.js) sends its hash for tick N and
      then reads `peerChecks.get(N)` *synchronously*. The peer's hash for tick
      N is still crossing the network at that moment — two lockstepped clients
      reach the same tick at nearly the same time — so `theirs` is essentially
      always `undefined`, the comparison is skipped, and nothing ever reads the
      entry once it lands: the only read is that one. So `browsern.mjs`
      reports "desyncs: 0" while performing zero checks. Verified 2026-08-05:
      both peers pair and race (host "racing Guest", guest "joined Host") for
      35s and produce 0 sync-ok checkpoints where ~5 were due.
      **Deliberately not fixed** — it only invalidates lockstep's own
      verification, and the topology is being replaced. **Whoever writes the
      state-sync checker must not reproduce the shape:** compare on the tick
      whose hash has ARRIVED, not the tick you are on. Note this does not
      touch `netloop.mjs`'s 8000-tick result, which runs two node processes
      and compares fields directly.
- [x] **Switched from lockstep to client-authoritative state sync.** Each
      client is authoritative for its own car and the host only for the bots,
      so the trust model is that every player is honest about themselves —
      fine for a private game, and no basis for anti-cheat. `netcodec.js`/`netsession.js`
      transcribes `UDPMistro.setinfo`/`readinfo`: 16 booleans and 20 numbers per
      car, humans owning their own slot and the host owning every bot
      (`GameSparker.java:1348`). `netcodec.test.js` + `netsession.test.js` (18 tests) pins the field set
      by PARSING `UDPMistro.java` — a round-trip test is symmetric and would
      agree with any self-consistent mistake. `main.js` has no gate and no
      stall: a quiet peer costs accuracy on its own car and nothing else, and
      the lockstep input-shadow control is gone, so local input latency is zero.
      `Control.remote` stops a guest running the AI for the host's bots.
      **Measured** (`netloop.mjs`, two processes, lossy relay): the guest's view
      of a host-owned car trails by exactly LATENCY ticks with a residual of
      0.0–4.8 units clean, 11.8 at 10% loss, 31.4 at 30% loss + 20% reorder, and
      drift SHRINKS across every run (x0.52 / x0.97 / x0.72 first half to
      second). The lockstep implementation (`netsync.js` and its 12 tests) is
      deleted; git history has it if rollback ever wants the input timeline.
- [x] **3+ players.** Star topology centred on the host: `netpeer.js` holds one
      connection per guest (`openRoom`/`accept`/`broadcastExcept`), the host
      assigns slots and relays each guest's packet to the others VERBATIM, and
      `netcodec.js`/`netsession.js` needed no change — it was already slot-general. A guest
      transmits only its own car however many are racing, so guest upload is
      flat and only the host's scales. `?humans=N` on the host URL.
      **Measured** (`netloop.mjs`, N processes, two-hop relay modelled): at 3
      players clean, 1-hop views trail exactly LATENCY ticks and relayed
      guest-to-guest views exactly 2xLATENCY, residual 4.5–5.3 units, bots
      0.0; at 4 players with 12% loss and 15% reorder, residual 42.6 and drift
      still shrinking (x0.92). **Confirmed in three real browsers**
      (`browsern.mjs 45 3`): peers pair, slots are distinct, and each guest
      hears the other guest through the relay.
- [x] ~~**Second DataChannel for lobby/chat**~~ — gone with the p2pt swap
      (2026-08-06), which offers one reliable/ordered channel per peer and
      nothing to split. Lobby and state now share it, distinguished by a frame
      tag byte. The reason it existed still holds and is why the handshake must
      stay on a delivered-guaranteed path: a dropped `hello`/`start` strands a
      guest, where a dropped state packet heals itself. Original note follows.
- [x] **Second DataChannel for lobby/chat**, reliable and ordered, over the
      same peer connection (`netpeer.js` `sendMessage`/`onMessage`, paired by
      `conn.peer`). The join handshake moved onto it — it was previously riding
      the unreliable channel, where a dropped `hello`/`start` stranded the
      guest. Chat is relayed by the host like state. Verified guest-to-guest in
      three browsers. The lobby and chat SCREENS are still unported xtGraphics.
- [x] **Transport swapped from PeerJS to p2pt.** PeerJS's broker cannot list
      peers (discovery endpoint 404s), so a room could only ever be JOINED by a
      code someone read out — a game browser would have needed a hardcoded id
      squatted by one player's tab, or a registry of ours. p2pt announces on
      public WebTorrent trackers under an identifier and is handed everyone
      else on it. `netpeer.js` is the only file that changed shape; the codec,
      the session rules and the launcher parameters are untouched.
      **What it cost:** simple-peer's channel is reliable and ordered, so the
      unreliable state channel and the second lobby channel are both gone.
      Measured first (`netloop.mjs ... reliable`, which emulates head-of-line
      blocking): residual 22 -> 3 units clean and 46 -> 9 at 2% loss — reliable
      is BETTER there, because retransmission beats the unordered channel's
      reordering — and 24 -> 101 at 10%, 40 -> 354 at 30%, with 11-19 tick
      stalls in that range.
      **What it bought besides discovery:** the star became a mesh, so
      guest-to-guest state goes direct instead of through the host and the
      relay is deleted. **Confirmed in three real browsers** (`browsern.mjs 60
      3`): peers pair over the trackers, slots are distinct, state is heard
      both ways and every client saw every chat line.
- [x] **Public game browser — built** (`web/netdirectory.js`). One well-known
      identifier (`nfm-directory-v1`) that every client on the Multiplayer
      screen announces on; hosts advertise `{code, name, stage, players, max}`
      and browsers collect the replies. No registry, no authority, no
      persistence — the list IS the set of hosts currently holding the screen
      open. Entries expire after 16s (re-announced every 5s) because a host
      that closes its laptop cannot withdraw, and ping is a round trip on the
      directory channel. Unauthenticated like everything else here: anyone can
      list a game that does not exist, so the room CODE stays the load-bearing
      way in.
- [x] **The multiplayer GUI is wired to the netcode.** `index.html` +
      `web/launcher.js` are the launcher proper, built from the mockups' `slab`
      theme: menu, single player, race options, multiplayer browser, lobby and
      settings, keyboard-first with pointer support on the same two verbs.
      `web/netlobby.js` holds the pre-race protocol (roster, chat, car and
      stage choice, start) and is driven BOTH by the lobby screen and by
      `main.js`'s `?net=host&humans=N`, so the tested path and the shipped path
      are the same code. Settings persist in localStorage. The old advanced
      form is gone; every dev knob still works as a URL parameter on
      `web/main.html`, which remains the entry point for the tools and for
      shared links.
      **One page**, because a navigation would tear down the lobby's
      connection: `boot({ params, session, onExit })` takes the live `{net,
      cfg}` instead of re-negotiating, and the race ends by reloading the
      launcher. **Verified end to end in two real browsers**
      (`web/tools/browserlobby.mjs`): hosted, found in the public list, joined,
      chatted both ways, raced, and each client applied the other's car state.
      Original seam notes follow.
- [x] ~~Wire the multiplayer GUI (lobby / join / chat) to the netcode.~~ Build
      it from `web/menu-mockups.html`'s `slab` theme. The seam, which otherwise
      exists only in the code:
      - **A lobby must live PRE-BOOT, not in the race loop.** `negotiate()`
        (`main.js`) runs to completion before the world is built, because the
        host decides seed, stage, grid and slot assignment and ships them
        verbatim — a client that regenerated any of it would consume different
        randoms. So the lobby is a launcher/pre-race screen; there is nowhere
        in `main.html`'s frame loop to put one.
      - **Params:** `?net=host|join`, `?room=CODE`, `?humans=N` (host only),
        `?name=`, alongside the existing `?car=`/`?stage=`. The host generates
        a room code if none is given.
      - **Handshake:** guest sends `{t:'hello', name, car}`; host replies
        `{t:'start', seed, stage, players, cars, humanSlots, names,
        localIndex}`, sent per guest since `localIndex` differs. Slot
        assignment is the host's alone — two guests choosing for themselves can
        collide, and nothing downstream notices until both drive the same car.
      - **Chat** already works end to end (`net.sendMessage`/`onMessage`);
        on the mesh it reaches every peer directly and nobody relays.
        `window.nfmChat(text)` in `main.js` is a console stub standing in for
        the missing UI — replace it, do not build alongside it.
- [ ] **Measure the correction distribution properly, before smoothing it.**
      The drift line samples the most recent correction every ~3s rather than
      logging each one, so nothing here knows how OFTEN a large correction
      happens — and the distribution is heavily tailed (`netloop`: mean ~4
      units, max ~437 on a clean channel), so a mean and a max describe neither
      the typical case nor the felt one. Log every correction and histogram it.
      Two things make this matter more than it looks: 300 units is ~1.5 car
      lengths, and this game is about ramming people, so a hit that connected
      locally may not have connected on their machine — smoothing hides that
      visually without making the two machines agree about the collision. Note
      the browser figures (256–420 units) came from **localhost with zero
      packet loss**, so the cause was CPU/tick-rate jitter, not the network;
      a run over a real connection is needed before trusting any number.
- [ ] **Smooth the correction on the render side.** The one real cost of the
      switch: a run of lost packets lets the prediction wander and the snap back
      reaches ~600 units at 30% loss. That is the price of the loss, not a
      protocol defect — `netloop.mjs` reports it as "the render-side smoothing
      budget" rather than failing on it. Correct the DRAWN position toward the
      authoritative one over a few frames; do NOT smooth inside `applyCar`,
      which would make the simulation and the wire disagree about where a car
      is, the exact bug state sync exists to avoid.
- [ ] ~~Reconsider lockstep before adding a 3rd player.~~ (done, above) The original is
      client-authoritative state sync: `UDPMistro.setinfo()` sends inputs AND
      absolute state per car per tick, and the host simulates only the bots
      (`GameSparker.java:1348`). Lockstep gates every peer on the laggiest one,
      needs an N(N-1)/2 mesh and can't do drop-in, and its one big win — the AI
      syncing for free — is worth nothing once humans fill those slots.
      Switching costs `netsync.js` and its 12 tests (~20-25% of the netplay
      work); `netpeer.js`, the launcher UI, `browsern.mjs` and every
      determinism fix survive — determinism is what would make state sync's
      dead reckoning accurate enough that corrections never show.
      - Write the transport fresh in JS: `UDPMistro`/`udpServe`/`udpOnline` are
        DatagramSocket plumbing and a stringly-typed relay protocol with no
        browser analogue. But transcribe `setinfo`/`getinfo` closely — which
        fields are authoritative per car, and how the receive side folds them
        back into `Mad`/`ContO`, is real game logic.

## Extended Mode v2.8 (branch `extended-mode`, `web/ext/`)

Classes are transpiled from Extended's own repaired source by
`decompilation/extended/j2js/J2JS.java` (not patched from `web/*.js`: see
`web/ext/README.md` for why). Verified against `madness.jar` by captured-call
replay (`web/tools/ext-trace.mjs`, DiffRun `-Ddiffrun.trace=Class.method`).

- [x] **Step 1, archives:** `radq.js`, every entry's CRC in all 15 archives.
- [x] **Step 2, models and drawing:** `ContO`/`Plane` bit for bit on 129 models;
      `ContO.d`/`Plane.d` call for call (170k pose calls, ~3M effect calls).
- [x] **Step 3, physics:** `Madness.drive` identical on 21 captured race calls,
      `Control.preform` (AI) on 12. `trace-drive`/`trace-preform` fixtures.
- [~] **Step 3, AI coverage:** the 12 preform calls run ~4% of its 9,520 lines
      (most calls take the `stcnt <= statusque` short path). Capture calls where
      the AI re-decides (`stcnt > statusque`) to cover the rest.
- [ ] **Step 3, rest:** `Madness.colide`, `CheckPoints.checkstat` replays.
- [x] **Step 4, stage loading:** `GameSparker.loadstage` identical on the menu's
      stage 16 and the race's stage 9 (`game-L`). Fixture is 4.2 MB, kept in
      `D:\platica\.nfm-ext-work\` rather than the repo.
- [ ] **Step 4, backdrop:** `Medium.d` (sky, ground, clouds, mountains) is drawn
      but not verified call for call; do it on real stages like `draw.test.js`.
- [x] **A race in the browser (2026-09-24):** `web/ext/main.html?mode=classic|career&stage=N&car=M`
      runs the jar's own `GameSparker.run()` loop (a generator, see WORK.md),
      skipping Extended's menus: it sets the stage, car and mode where
      `readdata` hands over `xtGraphics`/`CheckPoints`, then `fase = 6476` as
      stage select's START does. The launcher's Extended Edition -> Classic
      Race / Career Mode opens it. 7-car race on stage 1 with the full HUD.
- [x] **Car select (2026-09-24):** the launcher's Extended entries open
      Extended's own car select (`fase -9` -> `inishcarselect`, `fase 7`), as
      the base port opens the game's; Enter -> stage preview -> START -> race.
      Career shows levels, stat points, Change Stats and Bonus Cars. Esc on it
      returns to the launcher (the jar's car select has no way out). The stage
      preview's CHANGE CAR and RETURN TO MENU work (car select / launcher).
- [x] **Fixed after the first hands-on run (2026-09-24):** night stages (10, 14)
      froze on `Arrays`; black smoke/silhouettes from `PixelGrabber` on the
      offscreen frame; BACK past the first classic car crashed (the page chose
      a car outside classic's 23-38). See WORK.md.
- [ ] Watch: once, in career, a run ended on the stage preview (stage 4, after
      8 and 11) with no Enter sent; not reproduced in three repeats.
- [x] **Free Play for Extended (2026-09-24):** launcher -> Extended Edition ->
      Free Play (`?ext=free`, `web/ext/freeplay.js`): Extended's own car select
      with all 39 cars (run in the jar's normal mode; 0/38 limits added), then a
      stage select that renders the stage (the jar's fase 1 fly-around, no title
      card, no kB loading screen) under the port's DOM controls, in Spanish:
      NFM 2 (the base's 32, classic mode, NFM2 models) / Extended (tracks.radq's
      27, normal mode); arrows, Up/Down switch group, Enter races, Esc changes
      car. The pick is remembered (`nfm.ext.free`). Classic stays at `?ext=classic`.
- [x] Free Play fixes (2026-09-24): NFM 2 stages lost their ground tint (Extended
      has no texture(); now polys()), and 24 Extended stages were on an old model
      list (ids +4; renumbered). See WORK.md.
- [x] Free Play, developer mode only: a Career group (careertracks.radq 1-31,
      raced in career mode: its HUD levels, bots, per-stage AI). Hidden from
      players so the career stays a surprise (the user, 2026-09-24).
- [ ] Free Play: the Premier Tournament (Extended stage 26, matchtracks.radq's
      five rounds) is not listed.
- [ ] Free Play: the car select still draws BACK at car 0 and NEXT at 38 (the
      jar hides them only in classic/career); they do nothing there.
- [ ] Free Play: NFM 2 stages 1-10 and 28-32 have no classic twin, so they race
      with classic stage 1's per-stage AI (Control has ~260 stage checks).
- [x] **Sound and music (2026-09-25):** effects are the base port's (web/audio.js over
      data/sounds.zip) behind getAudioClip by file name (web/ext/sound.js), plus caught/redflash
      from Extended and the base's scrape sounds on the player's sparks; tracker music (.radq
      .mod) through the base's BassoonTracker (web/ext/radmusic.js, `radmod-lazy` patch,
      music.loadBytes); career .ogg through an <audio> OggClip. Assets in ext/data/Files
      (77 MB, untouched copies). Needs a listen: the user checks by ear.
- [x] **Saving (2026-09-25):** a real career saves to localStorage `nfm.ext.career` (web/ext/career-save.js, the savedata.radq fields); developer mode keeps the debug unlocks and saves nothing.
- [x] **The race in the base shell (2026-09-24):** `web/main.html?ext=classic|career`
      (and the launcher's Extended Edition entries, through the same `boot()`)
      runs Extended's race on the base race's page, WebGL surface (`?res=`,
      `?textres=`, `?aa=`), fixed-tick rAF loop and stats line. `web/ext/race.js`
      steps the generated `GameSparker.run()` one frame per tick and fast-forwards
      the jar's pre-race screens unseen (see WORK.md). `web/ext/main.html` stays as
      the dev page with Extended's own car select until the launcher has one.
- [x] **Interpolation for Extended, the base port's way (2026-09-24):** ContO/Plane/
      Medium carry the base's `interpolating` guards and random replay at the
      same sites (hand-maintained from here); `web/ext/racetick.js` is the race
      frame split like the base harness (rebuildNewCars + simulate per tick,
      draw once per frame, authoritative when a tick ran); `web/ext/race.js` runs
      main.js's frameBody logic. The first attempt (`interp.js`, save/restore
      around a redraw plus a tick-picture shortcut) is gone: it juddered in
      turns and cost double on tick frames. `?selftest=N` hashes cars AND
      effect state; interp=0 and 1 must agree (stage 4, car 30, 400 ticks:
      `8215cdd7`). 60 fps, worst frame 6-14 ms, 0 over budget on stage 4.
- [x] **Shaking in turns fixed (2026-09-24):** Medium.d's `i32(xz +/- 360)` dropped
      the fraction of the blended camera heading on every redraw; now `xz += 360`
      as the base port's Medium.d. See WORK.md.
- [ ] **The base shell's race features** for Extended: pause menu, finish
      screen, highlights, Spanish HUD sprites. Today the jar's own finish runs.
- [ ] **Deploy:** `deploy.sh` needs `rsync`, which this Windows machine lacks.
### Priorities (the user, 2026-09-24)

In this order of importance; content creation (below) waits behind them.

- [x] **Car select** for Extended: Free Play and Career run the jar's own car select (web/ext/menus.js).
- [x] **Career mode** through the menus (2026-09-25): the jar's car select, a rendered stage select with
      the career extras as Spanish DOM buttons (bonus stage, hard/scale/no levels, xp, scouting, change car,
      menu), progress saved, no beta wall (all 31). Still to see by hand: a whole career run, level-ups,
      bonus stages 1-4, the stage 23 boss music switch.
- [x] **Music on every stage** (modules through BassoonTracker, career `.ogg`), 2026-09-25.
- [x] **Stage select** working (rendered stage, DOM controls; free play and career).
- [ ] **The base port's UI on Extended, keeping Extended's own pieces:** the
      special bar, the car list, health/special on car select, Extended's
      speedometer; the base's screen effects on pause and race end.
- [x] **Extended's own cars in Extended's free mode:** Free Play lists all 39 and
      Extended's own stages (2026-09-24).
- [ ] **Spanish** (HUD sprites, strings).

### Content from the base game's editors

Gameplay stays separate; Extended learns to read what the editors make.

- [x] **NFM2 / Stage Maker stages in Extended (2026-09-24):**
      `web/main.html?ext=classic&nfm2stage=N` (the base game's stages/N.txt) or
      `&mystage=NAME` (Stage Maker store / mystages/) races it with Extended's
      gameplay (`web/ext/stagecompat.js`, hooked in race.js). Ids translated by
      the base models: all 68 appended from the base `data/models.zip` after
      Extended's 129 (Extended reshapes some, e.g. giant trees); checked on NFM2 stage 30
      (cacti, slider, launchpad) and the Stage Maker example stage.
- [x] **Pick them from the launcher** for Extended: Free Play's NFM 2 group (Stage Maker stages not yet).
- [x] **Car Maker cars in Extended, as NEW cars (2026-09-26, branch `ext-new-cars`)**:
      added after the 39, none replaced; Free Play only; opponents never race them.
      Plan and rulings: `docs/superpowers/plans/2026-09-26-ext-new-cars.md`.
      A new car is index `NEW_BASE + i` (200+: 39-196 are track pieces, beast cars,
      scenery and stagecompat's models) in the model array and the 70 per-car tables
      (`web/ext/newcars-grow.js`, grown by ext-patch after the constructors; a test
      compares its lists with every length-39 field), and its donor's number as its
      IDENTITY: `web/tools/ext-ident.mjs` wraps the 1,110 `car <op> literal`
      comparisons (+ healthcalc's `carid`) as `id(car)`, so the donor's special and
      quirks come with it. Physics: the base `CarDefine.loadstat` (NFM 2's formulas);
      Extended-only values from the donor. Model: ext-patches `newcar-isacar` and
      `newcar-scale-*` (Extended's ContO ignored ScaleX/Y/Z). Launcher: Extended
      Edition -> New cars (donor per car). Stock races unchanged (selftests base vs
      branch: classic 4/30 `271c3367`, 11/36 `79ff2d50`, 9/25 `38386f63`).
- [ ] New cars in the career: `career-save.js` saves per index (`CARS = 39`), and a
      new car's index moves with the list -- save them by name; level-ups
      (`reqneed`, `resetstats` take the car number as a plain parameter: see WORK.md).
- [ ] New cars' balance: they drive with NFM 2's numbers; Extended retuned ~half of
      the NFM 2 cars by hand (`dammult`, `maxmag`, `swits`, `grip`). A per-table
      factor from the 16 pairs, as a knob, if they feel off.
- [ ] The career selftest (`?ext=career&stage=3&car=5&selftest=600`, also `=50`)
      blocks the main thread in headless Chrome on `extended-mode` itself
      (2026-09-26); WORK.md recorded `353ea4bf` for it earlier. Find what changed.
### Base-port parity audit (2026-09-24)

The base port is not just the Java transcribed: it carries port-level fixes and
features no Java has. Extended was transpiled from its own source and checked
against the jar, so it did NOT inherit them, and several were being
rediscovered one bug at a time. This list is every base-port item (from
WORK.md, TASKS.md and the code of web/ContO/Plane/Medium/graphics/main/
XtGraphics/Mad), sorted against Extended. Engine comparison, for scale:
Plane shares 8/10 methods and all fields with the base; Medium 30/32 (+39
fields); ContO 13/19 (+40 fields, 6 new effects); Mad->Madness +120 fields;
Control 4x the code; xtGraphics twice. The render core is the base's; physics,
AI and UI are the mod.

**Covered already** (shared code, or redone for Extended):
- graphics.js as a whole: WebGL batch in submission order, even-odd trapezoid
  fill, packed colour, modulo-free isConvex, textres/AA defaults, per-colour
  alpha, setFont(Font), drawString/stringWidth through tr(). java.js: float
  RGBtoHSB/HSBtoRGB, idiv(x,0) = 0, the draw random bank.
- Fixed tick 53 ms, MAX_CATCHUP 3, display-rate interpolation with the camera
  blended between its tick states, HUD vector replay, overlay kept
  (web/ext/interp.js); one scene draw per frame (the tick's own picture is t=0).
- Fractional sin/cos (during redraws); draw-time mutations kept out of the sim
  (save/restore instead of `interpolating` hooks, checked by interp.test.js).
- Procyon `*= (int)literal` artifacts (sky 0.991, etc.): Extended's repaired
  source is bytecode-verified; field/method name clashes (trackbg$m, stat$m);
  pacing without catch-up bursts on menu screens (carselect.js fix is shared).
- Canvas2D edge stroke on fills (offscreen images use canvas-graphics.js),
  race in the same page via boot(), onExit back to the launcher, stats line.

**Applies -- to do, by impact:**
- [x] Perf: ContO.d face order by stable `Array#sort` on `av` instead of the
      O(npl^2) rank count, and cos/sin hoisted out of ContO/Plane/Medium.rot:
      named patches over J2JS output (`web/tools/ext-patches.mjs`, checked by
      `ext/patches.test.js`; draw.test.js still matches the jar call for call).
      Tick 13.9 -> 10.8 ms and redraw 9.6 -> 7.7 ms on a 16.5k-vertex scene;
      22-40% off race frames.
- [x] ~~Perf: Record's per-cycle ContO copies~~ -- measured, not the cause (6 cars; overturned below):
      `?spike=18` logged 5 frames over 18 ms in 20 s, none following a Record
      cycle (cntf 4..43), heap +3.4 MB/s of short-lived draw arrays. The copy
      constructor also consumes randoms (gr == -15 planes) and writes the
      source and the Trackers, so skipping copies would change the race.
- [x] Perf (2026-09-25): with 19 cars Record's copies ARE the cause -- ~20k Planes per
      shift reach the old generation; the major GCs were the 53-61 ms hitches. ext-patch
      `record-shift` moves the older five snapshots and copies only the newest. The
      objection above does not hold for cars: none of the 39 has a gr -15 face or a
      track (checked in the browser), so the copy consumes no randoms and adds no
      trackers; it writes `n = 16` on master faces, already 16 on a snapshot.
      Selftest `271c3367` unchanged. Also `tracker-rows`, Medium ogpx/ogpz by stage.
- [x] Perf: trackgrid.js -- ContO.d's shadow test, lowshadow, Plane.s and the base dust
      scan only the trackers in the point's cell (conservative grid, same order, tested
      against the full sweep). The start grid of NFM 2 stage 13 had 54-136 ms frames.
- [x] Perf (2026-09-25): career stage 3's start (19 cars bunched). Madness.drive swept every
      tracker twice per car per tick (road type; 4 wheels x every tracker) -- ext-patches
      `road-cell` / `wheel-sweep` over trackgrid.js (the wheel sweep falls back to every tracker
      after the last one visited once a pushed wheel leaves the safe box; tested against the
      full sweep and on the captured drive() calls). Plane.d/sortpieces/s per-call arrays from
      a pool; ContO.d's face order kept per object; Extended groundpolys' per-cell arrays
      reused; each Plane's nine arrays are views of one buffer. `?bench=10&warmup=0` from the
      start, grid vs `?grid=0`: sim 8.86 -> 3.75 ms/tick, draw 15.0 -> 11.1 ms/frame, 866 ->
      595 ns/vert, 47.5 -> 54.6 fps, long tasks in 10 s 9 -> 2. Heap nodes 3.11M -> 1.86M.
      Selftests unchanged: classic 4 `271c3367`, career 3 (19 cars, 600 ticks) `353ea4bf`.
- [ ] Perf: Plane.d's own body (~2.3 us/face) is now most of the start's draw -- the
      J2JS float/int wrappers; and the replay's 6 x 19 car snapshots (~20k Planes) that could
      keep only the vertex arrays.
- [x] Visual (2026-09-25): an NFM 2 stage in Extended looks and costs as in the base:
      density 2n+1, disline x2, the base Plane culls (basecull), the base's ground
      patches (web/Medium.js newpolys/groundpolys on Extended's Medium, seed approx.),
      the base pile shading, nochekflk but on 1 and 11 (stagecompat.js baseLook /
      baseGround). Stage 13: 19.2k verts submitted vs the base's 18.9k.
- [ ] Visual: replay the tick draw's random sequence on redraws (the base's
      Medium random log), so sparks, dust and bolts keep their shape between
      ticks instead of re-rolling at 60 Hz.
- [x] Visual: wheel dust is the base's (web/ext/basedust.js: the 20-slot ring, a puff
      per wheel per tick, translucent road-tinted octagons); Extended's own 4 slots
      keep their state for Record but are not drawn. At the user's request.
- [x] Visual: sparks (the jar has none): base ContO.sprk/dsprk, Madness calls via
      `sparks-*` patches (scrape capsized, walls, slopes, car-to-car).
- [ ] Replays: Record replays Extended's dust slots, not the base ring -- feed the
      ring from Record's puff events when instant replay/highlights are wired.
- [ ] Visual: loadsnap's corner-pixel reference when the corner is transparent
      (base fix for the missing "TH" on rank badges); the probable cause of the
      black shapes on Extended's loading/stage images.
- [ ] Visual: `?hud=auto` contrast-adapted HUD ink on dark skies, `outline`,
      `boxes` (images.js readable/halo).
- [ ] Screens: Esc opens the base's DOM pause menu (race-ui.js: Resume /
      Instant Replay / Instructions / Quit), pause drops accumulated time;
      Enter mid-race (the jar's fase -6/-7) routed to it instead of Extended's
      pause screen.
- [ ] Screens: race end -> the base's finish screen, automatic highlights and
      Instant Replay (Record.playh on cloned cars), then back to the launcher.
- [ ] Screens: launcher car and stage pickers for Extended (base carselect.js /
      stage select with the NFM1/NFM2 lists and 3D preview).
- [ ] Audio: SFX through web/audio.js (the base's AudioClip stand-ins for
      getAudioClip), unlocked on key/pointer, volumes from Settings.
- [ ] Audio: music -- the .radq stage modules through BassoonTracker
      (music.js), career .ogg pairs through an <audio> element; ship the files.
- [ ] i18n: Spanish HUD sprites and layout (ui-sprites-es.js), Extended's
      strings in the i18n dictionary.
- [ ] Input: touch joystick and two-finger trick; check Extended's own key map
      (A/S toggles, Shift lookback) against the base's.
- [x] Career (2026-09-25): the jar's own stage select (base-port look + Extended's buttons, its
      locked-stage screen), translated; a - per stat and held +/- in the car select.
- [x] Career (2026-09-25): the real career in developer mode too (`?extdebug=1` for the debug
      setup); Confirm / Undo for the car select's stat points; New career; a finished race
      returns to the career's car select with the progress saved. Checked in the browser: a
      forced win on stage 1 then 2 -> unlocked [1,2] -> [1,3], the next stage selected.
- [x] Tooling (2026-09-25): `?bench=S` (window from the end of the countdown, `?warmup=`,
      R reruns; the base's report plus Extended's slices), `?prof=1` (Plane.d, Plane.s,
      Medium.d, Madness.drive/colide), `?maxfps=`, `?debug=1` (window.__nfm), `?spike=`
      (with prof slices) -- web/ext/benchtools.js. Unlike the base, ?stats=1 does not imply ?bench.

**Does not apply now** (decide when Extended gets multiplayer):
- Netplay determinism fixes that deliberately depart from the Java: sound and
  announcer randoms on the draw bank, stepFix / tickMissedCp moved out of
  drawing, repair never branching on ContO.dist, `human(i)` / `Control.remote`
  / `xt.im`, trig tables baked as constants. Extended stays jar-faithful here;
  they matter once two clients must agree.
- Base deviations flagged as possibly unintended (Medium.ys clamp at 50, dsprk
  truncation): not to be copied.
- Base-only screens (car maker, stage maker, lobby) and ?mystage / ?mycar /
  ?cars=same / ?players.

- [ ] **Extended's own menus, last** (as in the base port, the launcher stands
      in for them). They already run from the transpiled `xtGraphics`; images
      decode, but the menu backdrop draws black.

- [x] **Validated and repaired (2026-09-23):** procyon's raw output was wrong in 129 places (126 compound casts, 3 infinite loops); `java-src/` is the repaired copy. 1,924/1,950 methods equivalent by operations, the other 26 read and benign; a side-by-side run (`decompilation/extended/diffrun/`) matches the jar bit for bit through menus and ~650 frames of a 6-car race. Modes not yet run side by side: career/RPG, tourney, specials. See `decompilation/extended/README.md`.
- [x] **Decompiled (2026-09-23):** `decompilation/extended/java-src/`, 26 classes, 97.6% opcode match on recompile (base game 98.7% by the same measure); `Control.preform` is too large to recompile. `research/extended-mode/radq.py` unpacks all 78 `.radq`. See `decompilation/extended/README.md`.

## Known gaps / risks

- [!] `Mad`'s `zy`/`xy` are unverified pending the seeded-PRNG task above.
      Everything else in `Mad` matches Java exactly for 300 ticks.
- [!] A headless coast test showed `skid` sticking at 2 and speed pinning at
      18.0 where Java reaches 0. Not reproduced in gameplay; may be an artifact
      of the synthetic probe state. Worth revisiting.
- [ ] Glyph shimmer at distance is believed authentic (`Plane.java:742` culls
      sub-3px faces; `:261` is a 12/20-vertex LOD switch) — wants a side-by-side
      against `./start.sh` to confirm.
- [x] ~~BassoonTracker's `ScriptProcessorNode` can starve and stutter under heavy load; needs an `AudioWorklet` port~~
      **Measured 2026-09-23: not a real risk, no port needed.** The
      ScriptProcessor is only WAAClock's CLOCK (256 frames, outputs nothing);
      notes are `AudioBufferSourceNode`s scheduled ~0.1-1.1s ahead from the
      main thread. Stage 9, 8 cars, patching `AudioBufferSourceNode.prototype.start`
      to log `when - currentTime`: 0 late of 153 notes, including under
      repeated 150ms main-thread stalls and one 900ms stall (min lead 9ms).
      Ceiling: a stall over ~1s (GC pause, hidden tab) can drop notes.
