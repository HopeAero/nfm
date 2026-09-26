// Extended's in-race Spanish (i18n-ext.js), through the same tr() the drawString calls use.

import { test } from 'node:test';
import assert from 'node:assert';
import { setLang, tr } from './i18n.js';

test("Extended's stunt calls translate piece by piece", () => {
  setLang('es');
  try {
    assert.strictEqual(tr('Breathtaking forward loop!'), '¡Asombrosa giro adelante!');
    assert.strictEqual(tr('Nice hanged double back with triple rollspin by 540 and beyond!'),
      '¡Buena colgado doble atrás con triple giro de lado por 540 y más allá!');
    assert.strictEqual(tr('World class surf style 360!'), '¡De clase mundial estilo surf 360!');
    assert.strictEqual(tr('Decent!'), '¡Decente!');
    assert.strictEqual(tr('Simply magnificent.'), 'Simplemente magnífico.');
  } finally { setLang('en'); }
});

test("Extended's messages and HUD", () => {
  setLang('es');
  try {
    assert.strictEqual(tr(' Arrow now pointing at  Cars  <'), ' La flecha apunta a  Autos  <');
    assert.strictEqual(tr('Power up 57%'), 'Potencia +57%');
    assert.strictEqual(tr('DR Chaos has wasted you!'), '¡DR Chaos te destruyó!');
    assert.strictEqual(tr('Titan has wasted Nimi!'), '¡Titan destruyó a Nimi!');
    assert.strictEqual(tr('Titan has been wasted!'), '¡Titan fue destruido!');
    assert.strictEqual(tr('You wasted Titan!'), '¡Destruiste a Titan!');
    assert.strictEqual(tr('Stage 4 Completed!'), '¡Pista 4 completada!');
    assert.strictEqual(tr('lap:'), 'vta:');
    assert.strictEqual(tr('4.0th'), '4.0º');
    assert.strictEqual(tr('th'), 'º');
    // the base's own strings are untouched
    assert.strictEqual(tr('Checkpoint!'), '¡Punto de control!');
    assert.strictEqual(tr('Wicked Forward loop'), 'Wicked Forward loop');
  } finally { setLang('en'); }
});
