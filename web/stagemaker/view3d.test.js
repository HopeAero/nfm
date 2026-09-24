// The stage maker's 3D view builds a stage through the real loadstage, draws
// it, and hands the Medium back to the map untouched.
import test from 'node:test';
import assert from 'node:assert';
import { readFileSync } from 'node:fs';
import { parseZip } from '../vfs.js';
import { Graphics2D } from '../graphics.js';
import { Medium } from '../Medium.js';
import { Trackers } from '../Trackers.js';
import { CheckPoints } from '../CheckPoints.js';
import { Control } from '../Control.js';
import { Record } from '../Record.js';
import { CarDefine } from '../CarDefine.js';
import { Mad } from '../Mad.js';
import { GameSparker } from '../GameSparker.js';
import { XtGraphics } from '../XtGraphics.js';
import { objArray, setSeed } from '../java.js';
import { View3D } from './view3d.js';
import { partObject } from './map.js';

const R = new URL('../../', import.meta.url);

test('3D view: the example stage builds, draws, and the Medium is restored', async () => {
  setSeed(12345);
  const zip = await parseZip(new Uint8Array(readFileSync(new URL('data/models.zip', R))));
  const medium = new Medium();
  const trackers = new Trackers();
  const checkPoints = new CheckPoints();
  const models = objArray(124);
  const gs = new GameSparker();
  const cd = new CarDefine(models, medium, trackers, gs);
  const rd = new Graphics2D(null, null, 800, 450);
  const xt = new XtGraphics(medium, cd, rd, gs);
  xt.loadmusic = () => {};
  const record = new Record(medium);
  gs.loadbase(models, medium, trackers, zip);
  const mads = objArray(8);
  for (let i = 0; i < 8; ++i) { mads[i] = new Mad(cd, medium, record, xt, i); gs.u[i] = new Control(medium); }
  const world = { medium, trackers, checkPoints, models, gs, xt, record, placed: objArray(610), mads };

  const text = readFileSync(new URL('mystages/Example Stage - with all the parts used in it.txt', R), 'latin1');
  const v = new View3D(world);
  assert.strictEqual(medium.lightson, false);
  v.build(text);
  assert.ok(v.walls.length > 20, `${v.walls.length} wall pieces`);
  assert.strictEqual(medium.lightson, true, 'the stage turns its lights on');
  v.home(0, 0, 20000);
  v.draw(rd, []);
  assert.ok(medium.y < 0, 'the eye is above the ground');
  v.orbit(-30, 200);
  assert.deepStrictEqual([v.yaw, v.pitch], [330, 85], 'yaw wraps, pitch stops short of straight down');
  v.zoom(100);
  assert.strictEqual(v.dist, 60000, 'zoom out stops');
  v.draw(rd, []);
  // A click is the inverse of the projection: ground point -> screen -> ground.
  for (const [x, z] of [[1200, -3400], [-9000, 15000], [0, 0]]) {
    const sc = v.project(x, 250, z);
    const g = v.toGround(sc.x, sc.y);
    assert.ok(Math.abs(g.x - x) < 1 && Math.abs(g.z - z) < 1, `${x},${z} -> ${g.x},${g.z}`);
  }
  // The editor rebuilds every part on each edit; that must not fill the
  // trackers (it did, and every ContO after that threw).
  const nt = trackers.nt;
  for (let i = 0; i < 3000; i++) partObject({ sp: 0, x: 0, z: 0, rot: 0, y: 0 }, models, medium, trackers);
  assert.strictEqual(trackers.nt, nt);
  v.restore();
  assert.strictEqual(medium.lightson, false, 'the map gets its Medium back');
});
