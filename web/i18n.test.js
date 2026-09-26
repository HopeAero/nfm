import test from 'node:test';
import assert from 'node:assert';

// i18n.js reads the language once, at import, from the launcher's saved
// settings. Import it twice under different URLs to get one instance per
// language.
globalThis.localStorage = { getItem: () => JSON.stringify({ lang: 'es' }) };
const es = await import('./i18n.js?lang=es');
globalThis.localStorage = { getItem: () => '{}' };
const en = await import('./i18n.js?lang=en');
delete globalThis.localStorage;

test('English is the default and passes everything through', () => {
  assert.strictEqual(en.lang, 'en');
  assert.strictEqual(en.tr('Top Speed:'), 'Top Speed:');
  assert.strictEqual(en.tr('You wasted Nimi!'), 'You wasted Nimi!');
});

test('exact phrases, keeping the whitespace around them', () => {
  assert.strictEqual(es.tr('Single Player'), 'Un jugador');
  assert.strictEqual(es.tr('  Top Speed:  '), '  Velocidad:  ');
  assert.strictEqual(es.tr('Press  [ Enter ]  to continue'), 'Presiona  [ Enter ]  para continuar');
});

test('phrases around a name or number translate the frame, not the name', () => {
  assert.strictEqual(es.tr('You wasted Nimi!'), '¡Destruiste a Nimi!');
  assert.strictEqual(es.tr('Player 2 has been wasted!'), '¡Player 2 fue destruido!');
  assert.strictEqual(es.tr('9 laps · 4 checkpoints'), '9 vueltas · 4 puntos de control');
  assert.strictEqual(es.tr('You Won!  At Stage 3!'), '¡Ganaste!  En la pista 3!');
  assert.strictEqual(es.tr('7 cars · chosen by stage'), '7 autos · según la pista');
  assert.strictEqual(es.tr(' move · '), ' mover · ');
  assert.strictEqual(es.tr('Class Beginner'), 'Clase Principiante');
  assert.strictEqual(es.tr('Class Pro · my car'), 'Clase Pro · mi auto');
});

test('car and stage names are left alone', () => {
  for (const name of ['Radical One', 'Formula 7', 'Nimi', 'The Introductory Stage', 'He Is Coming For You Next']) {
    assert.strictEqual(es.tr(name), name);
  }
});

// The document observer re-translates whatever the page writes, including
// its own output. A translation that changed again when fed back in would
// never settle.
test('translation is idempotent', () => {
  const samples = ['Single Player', 'Settings', 'Top Speed:', 'You wasted Nimi!', '9 laps · 4 checkpoints',
    '7 cars · chosen by stage', ' move · ', 'Press [V] to change view.', 'Power Up 45%',
    'Stage 3  >', 'You Won!  At Stage 3!', 'Cool', 'double Forward', ' by ', 'Class Beginner',
    'on — display rate', 'Continue', 'Start Race — 3 players'];
  for (const s of samples) {
    const once = es.tr(s);
    assert.notStrictEqual(once, s, `${s} should translate`);
    assert.strictEqual(es.tr(once), once, `${s} -> ${once} changed again`);
  }
});

// Settings -> Language switches without a reload: tr() follows setLang() at
// once, which is what the game's text (translated as it is drawn) relies on.
test('setLang switches the language live, both ways', () => {
  assert.strictEqual(es.tr('Top Speed:'), 'Velocidad:');
  es.setLang('en');
  assert.strictEqual(es.lang, 'en');
  assert.strictEqual(es.tr('Top Speed:'), 'Top Speed:');
  es.setLang('es');
  assert.strictEqual(es.tr('Top Speed:'), 'Velocidad:');
  assert.strictEqual(es.tr('Español — Enter to apply'), 'Español — Enter para aplicar');
});

// Everything the two replays put on screen -- replyn(), levelhigh() and the
// pause menu's notices -- must come out in Spanish.
test('every replay string has a Spanish translation', () => {
  const strings = ['Replay  > ', 'Replay  >>', "You Wasted 'em!", 'Close Finish!', 'Close Finish!  Almost got it!',
    'Wasted!', 'Disconnected!', 'Stunts!', 'Best Stunt!', 'Press  [ Enter ]  to continue',
    'Sorry not enough replay data to play available, please try again later.',
    'Replay recording is off. Enable it in launcher Settings, then start a new race.',
    'Instant Replay is only available in a solo race.'];
  const missing = strings.filter((s) => es.tr(s) === s);
  assert.deepStrictEqual(missing, []);
});

// ---- the car maker ----------------------------------------------------------------

const { CAREDITOR_ES } = await import('./i18n-careditor.js');

test('car maker: every dictionary entry translates, and its translation is stable', () => {
  es.setLang('es');
  const unstable = [];
  for (const [en, spanish] of Object.entries(CAREDITOR_ES)) {
    const once = es.tr(en);
    if (es.tr(once) !== once) unstable.push(`${en} -> ${once} -> ${es.tr(once)}`);
    if (once !== spanish) unstable.push(`${en} gave ${once}, not its own entry`);
  }
  assert.deepStrictEqual(unstable, []);
});

test('car maker: every name, hint and readiness message in rad.js and helptext.js is translated', async () => {
  const rad = await import('./careditor/rad.js');
  const help = await import('./careditor/helptext.js');
  const strings = [...rad.STAT_NAMES, ...rad.CLASS_NAMES, ...rad.PHYS_NAMES.filter((n) => n !== 'Empty'),
    ...rad.CRASH_NAMES, ...rad.ENGINE_NAMES, ...rad.ENGINE_NOTES,
    ...Object.values(help.STAT_HELP), help.CLASS_HELP, help.SCALE_HELP, help.ALIGN_HELP, help.ENGINE_HELP,
    ...rad.problems('', { npl: 0 })];
  const missing = strings.filter((s) => es.tr(s) === s);
  assert.deepStrictEqual(missing, []);
});

test('car maker: the multi-line readiness list translates line by line', () => {
  const list = 'The car has no stats yet — set them on the Stats tab.\nPick a first and a second colour on the Body tab.';
  assert.strictEqual(es.tr(list),
    'El auto aún no tiene estadísticas: defínelas en la pestaña Estadísticas.\nElige un primer y un segundo color en la pestaña Carrocería.');
});

test('car maker: HTML text wrapped over several lines still matches', () => {
  assert.strictEqual(es.tr('\n    Every piece of the car stores its own colour, so changing one\n    here repaints all the pieces that were the old colour. Pieces you painted some other shade are left alone.\n  ').trim(),
    CAREDITOR_ES['Every piece of the car stores its own colour, so changing one here repaints all the pieces that were the old colour. Pieces you painted some other shade are left alone.']);
});

test("the launcher's New cars page (Extended new cars)", () => {
  assert.strictEqual(es.tr('New cars'), 'Autos nuevos');
  assert.strictEqual(es.tr('Off'), 'No');   // as in Settings
  assert.strictEqual(es.tr('Special of Stampede'), 'Especial de Stampede');
  assert.strictEqual(es.tr('No Car Maker cars yet'), 'Todavía no hay autos del Car Maker');
  assert.strictEqual(en.tr('Special of Stampede'), 'Special of Stampede');
});
