// xtGraphics.ctachm for the car select (fase 7) and stage select (fase 1): a press
// over a button shows it pressed (mouses 1), the next tick fires it (mouses 2),
// as GameSparker.java hands xm/ym/mouses to it (xtGraphics.java:7305).
import test from 'node:test';
import assert from 'node:assert/strict';
import { XtGraphics } from './xtGraphics.js';

const img = { width: 60, height: 30 };
const fake = (fase) => ({ fase, next: [img, img], back: [img, img], contin: [img, img], pnext: 0, pback: 0, pcontin: 0,
  over: XtGraphics.prototype.over });
const ctl = () => ({ left: false, right: false, enter: false });

test('ctachm fase 7: press then fire on the car select arrows and CONTINUAR', () => {
  for (const [x, y, flag, field] of [[650, 280, 'right', 'pnext'], [100, 280, 'left', 'pback'], [360, 390, 'enter', 'pcontin']]) {
    const xt = fake(7), c = ctl();
    XtGraphics.prototype.ctachm.call(xt, x, y, 1, c);
    assert.equal(xt[field], 1, field);
    assert.equal(c[flag], false);
    XtGraphics.prototype.ctachm.call(xt, x, y, 2, c);
    assert.equal(c[flag], true, flag);
  }
});

test('ctachm fase 1: the stage select arrows and CONTINUAR; a click elsewhere does nothing', () => {
  const xt = fake(1), c = ctl();
  XtGraphics.prototype.ctachm.call(xt, 630, 140, 1, c);
  XtGraphics.prototype.ctachm.call(xt, 630, 140, 2, c);
  assert.equal(c.right, true);
  const xt2 = fake(1), c2 = ctl();
  XtGraphics.prototype.ctachm.call(xt2, 10, 10, 1, c2);
  XtGraphics.prototype.ctachm.call(xt2, 10, 10, 2, c2);
  assert.deepEqual(c2, ctl());
});
