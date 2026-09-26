# Car Maker: Extended tab — design

Date: 2026-09-26 · Status: approved in conversation, awaiting spec review ·
Builds on: `docs/superpowers/plans/2026-09-26-ext-new-cars.md` (branch `ext-new-cars`).

## Intent

The user builds cars in the Car Maker and wants them in NFM 2 Extended with
Extended's own characteristics. **One car, two games:** the same Car Maker gains
an "Extended" tab; the same `.rad` races in NFM 2 (which ignores the new lines)
and in Extended (which reads them). Any car made in the Car Maker shows up in
Extended's Free Play by itself — no launcher step.

What the user chose (2026-09-26):
- the Car Maker is shared, not duplicated (option "un auto, dos juegos");
- the Extended tab edits: the **special** (one of the 39 existing), **health and
  damage for Extended**, and **own handling for Extended**;
- the career is a **separate, later project** (it was also asked for);
- approach **A**: per section, "same as NFM 2" or "own", reusing the existing
  sliders; health/damage as multipliers;
- all Car Maker cars appear in Extended automatically; the launcher's
  "New cars" page goes away;
- a **"Try in Extended"** button.

Out of scope: the career (save by name, unlocks, levels, perks, the career car
select); opponents racing new cars (still never); new special powers (only the
39 existing ones); Extended's raw tables (`maxmag`, `swits`...) as editable numbers.

## Facts this design rests on (measured 2026-09-26)

- The web Car Maker (`web/careditor.html` + `web/careditor/editor.js`) edits the
  `.rad` text in place (`rad.js` `setLine`): lines it does not know survive a save.
- No parser — base `ContO`, Extended `ContO`, `CarDefine`, the Car Maker, or the
  original Java — acts on a line starting with `e`. `ext*` lines are inert
  everywhere but where this design reads them.
- Extended did not rescale NFM 2's cars: the median Extended/base ratio over the
  16 shared cars is 1.00 for `maxmag`, `grip`, top speed, 1.07 for `dammult`, with
  individual cars ±40%. Extended's own cars sit on the same scale (median
  `maxmag` 11500 vs 10700). So health/damage for Extended are per-car choices,
  not a unit conversion.
- Specials come in pairs sharing one power at two strengths (Extended car k and
  NFM 2 car k + 23, e.g. 13 and 36: +40% / +30% strength/defence and a random
  car's defence down). Their descriptions are the strings `xtGraphics.carselect`
  draws (English; Extended's Spanish dictionary already translates them).

## 1. The `.rad` lines

| Line | Meaning | Missing / invalid |
|---|---|---|
| `extspecial(n)` | donor stock car 0–38 whose special (identity) the car takes | donor by class (`defaultDonor`, as today) |
| `extstat(a,b,c,d,e)` | Extended's `stat()` | `stat()` is used |
| `extphysics(...)` | Extended's `physics()` (same 16 values) | `physics()` is used |
| `exthandling(h)` | Extended's `handling()` | `handling()` is used |
| `exthealth(p)` | health in Extended, percent, 50–300 | 100 |
| `extdamage(p)` | damage taken in Extended, percent, 50–200 | 100 |

Out-of-range or unparsable values are ignored (the fallback column), never
clamped into something the user did not choose. `extstat`/`extphysics`/
`exthandling` go together: the tab writes all three or none.

## 2. The Car Maker's Extended tab

A fifth tab, **Extended**, after Physics (`careditor.html` tablist + pane;
`editor.js` builds it like `buildStats`/`buildPhysics`):

1. **Special** — a `<select>`: "By class (automatic)", then the 39 stock cars as
   "name — short description". Below it, the full description in the UI
   language. Descriptions come from a new catalog `web/ext/specials.js` (39
   entries, English, `specialboost` = 1 as in Free Play), checked by a test
   against the strings in `web/ext/xtGraphics.js` so a regeneration cannot
   leave it stale.
2. **Stats and physics in Extended** — two radio buttons: **Same as NFM 2**
   (default) / **Own**. Choosing Own copies the current `stat`/`physics`/
   `handling` into `extstat`/`extphysics`/`exthandling` and shows the Stats and
   Physics rows (the same slider rows, built by the same functions with the line
   name as a parameter: `rad.readStats(text, 'extstat')`). Choosing Same removes
   the three lines.
3. **Health and damage in Extended** — two sliders, health 50–300% and damage
   taken 50–200%, default 100%, each with the resulting value beside it
   ("Health in Extended: 6882") computed with `newcars-stats.carFromRad`.
4. **Try in Extended** — saves the car (as the Save button does), writes
   Extended Free Play's remembered pick (`nfm.ext.free`: `{ car: 200, carName,
   group, stage }`, group/stage kept from the existing pick), sets
   `sessionStorage['nfm.ext.try'] = '1'` and opens `index.html`. The launcher,
   at the end of its boot, reads and removes that key and calls its own
   `startRace(null, { ext: 'free' })` — the path its Extended Edition → Free
   Play row takes, so it works without developer mode (URL switches such as
   `?ext=` need it). Extended's car select then opens on the car (the pick is by
   name).

Spanish: every new string through the Car Maker's existing translation
(`web/i18n-careditor.js`).

## 3. Extended's side

- `web/ext/newcars-stats.js` `carFromRad(name, text)`:
  - donor from `extspecial` (0–38) else by class;
  - if `extstat`, `extphysics`, `exthandling` are all present and valid, the
    text handed to `CarDefine.loadstat` has them in place of `stat`/`physics`/
    `handling` (the model text is not touched);
  - after `loadstat`: `maxmag *= exthealth/100`, `dammult *= extdamage/100`
    (float32, `trunc` for `maxmag` as `loadstat` does). `healthreset` follows
    through the existing mirror in `newcars-grow.js`.
  - The optional `donor` argument goes away (the `.rad` carries it).
- `web/ext/race.js`: the new cars are every name `listAll()` returns (Car Maker
  storage + shipped `mycars/`), in that order, loaded as today; `?newcar=` stays
  as a developer switch.
- Removed: `web/ext/newcars-store.js`, the launcher's "New cars" row/page and
  its Spanish strings, `web/launcher-newcars.test.js`. A stale `nfm.ext.newcars`
  key is ignored.

## 4. Errors

- A car that does not load (not a car, broken `.rad`): skipped with a console
  line, as today; the race starts.
- An `ext*` value out of range: ignored, the fallback applies; the tab shows the
  value it read and marks it invalid rather than rewriting the file on open.
- A remembered Free Play pick naming a car that is gone: car 38, as today.
- NFM 2 and the base Car Maker's Test drive: unchanged (they never read `ext*`).

## 5. Testing

- `web/careditor/rad.test.js`: read/write of each `ext*` line; `setLine` keeps
  unknown lines; removing the three stats lines together.
- `web/ext/newcars-stats.test.js`: `extspecial` → donor; own stats change
  `acelf`/`swits` vs the same car without them; `exthealth(200)` doubles
  `maxmag`; invalid values fall back.
- `web/ext/specials.test.js`: 39 descriptions, each string present in
  `xtGraphics.js`, each translated in Spanish.
- Car Maker tab: the existing careditor test style (`ui.test.js`/`state.test.js`)
  for the radio round trip (Own → lines written, Same → removed).
- Browser: a screenshot of the tab in both languages; Try in Extended lands in
  Extended's car select on that car; a car with `extspecial(13)`, own stats and
  `exthealth(200)` races (special text in the car select is donor 13's, health
  doubled in the race).
- Stock races unchanged: `web/tools/browser-newcar.mjs` (classic hash) and the
  base-vs-branch classic hashes.

## 6. Branch

`ext-carmaker`, from `ext-new-cars` once its final-review fixes are in.
