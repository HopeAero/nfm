import { test } from 'node:test';
import assert from 'node:assert/strict';
import { readRenderDetail, installRenderDetail } from './render-detail.js';
import { Medium } from './Medium.js';
import { Medium as ExtendedMedium } from './ext/Medium.js';

test('saved drawing choices have safe defaults and reject unknown detail levels', () => {
  const original = globalThis.localStorage;
  try {
    for (const [value, expected] of [['bad', {lightIntro:true,backgroundDetail:'full',mountains:true}],
      [JSON.stringify({lightIntro:false,backgroundDetail:'low',mountains:false}), {lightIntro:false,backgroundDetail:'low',mountains:false}]]) {
      globalThis.localStorage = {getItem:()=>value};
      assert.deepEqual(readRenderDetail(),expected);
    }
  } finally { globalThis.localStorage = original; }
});

for (const [name, Type] of [['Classic', Medium], ['Extended', ExtendedMedium]]) {
  test(`${name}: detail changes preserve the original camera, fog and physics inputs`, () => {
    const m = new Type(), reference = new Type();
    const car = {x:500,y:0,z:1500};
    const options = {lightIntro:false,backgroundDetail:'full',mountains:true};
    let intro = true;
    const remove = installRenderDetail(m,()=>intro,options);
    m.around(car,true); reference.around(car,true);
    for (const key of ['x','y','z','xz','zy','adv','vxz','resdown']) assert.equal(m[key],reference[key]);
    options.lightIntro = true;
    const physics = structuredClone(car), resdown = m.resdown, fade = [...m.fade];
    m.around(car,true); reference.around(car,true);
    assert.deepEqual(car,physics);
    assert.equal(m.resdown,resdown);
    assert.deepEqual([...m.fade],fade);
    for (const key of ['x','y','z','xz','zy','adv','vxz']) assert.equal(m[key],reference[key]);
    assert.equal(m.renderDistance,4000);
    intro=false; assert.equal(m.renderDistance,Infinity);
    options.backgroundDetail='low'; assert.equal(m.renderDistance,8000);
    remove();
    assert.equal(m.around,Type.prototype.around);
    assert.equal(m.renderDistance,undefined);
  });
}

test('intro detail returns at countdown; mountain choice is independent of reduced background', () => {
  const calls = [];
  const m = {around(){},drawmountains(){calls.push('mountains');},drawclouds(){calls.push('clouds');},groundpolys(){calls.push('ground');}};
  let intro = true;
  const options = {lightIntro:true,backgroundDetail:'full',mountains:true};
  const remove = installRenderDetail(m,()=>intro,options);
  const draw = () => { m.drawmountains(); m.drawclouds(); m.groundpolys(); };
  draw(); assert.deepEqual(calls,[]);
  intro = false; draw(); assert.deepEqual(calls,['mountains','clouds','ground']);
  calls.length=0; options.mountains=false; draw(); assert.deepEqual(calls,['clouds','ground']);
  calls.length=0; options.mountains=true; options.backgroundDetail='low'; draw(); assert.deepEqual(calls,['mountains']);
  remove(); calls.length=0; draw(); assert.deepEqual(calls,['mountains','clouds','ground']);
});
