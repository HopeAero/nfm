import assert from 'node:assert/strict';
import { spawn } from 'node:child_process';
import { mkdtempSync, writeFileSync, rmSync } from 'node:fs';
import { tmpdir } from 'node:os';
import { join } from 'node:path';
import { setTimeout as sleep } from 'node:timers/promises';
import { attach } from './cdp.mjs';
// Run with web/tools/serve.py on port 8123. Actual multi-touch events, including
// compatibility touch events, exercise the complete race input path.
const profile = mkdtempSync(join(tmpdir(), 'nfm-touch-'));
const port = 9700 + process.pid % 200;
const browser = spawn('C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe', [
  '--headless=new', '--no-sandbox', '--enable-unsafe-swiftshader', '--mute-audio',
  `--remote-debugging-port=${port}`, `--user-data-dir=${profile}`, 'about:blank',
], { stdio: 'ignore' });
let tab;
try {
  tab = await attach(port, 'about:blank');
  await tab.send('Page.enable');
  await tab.send('Emulation.setDeviceMetricsOverride', { width: 932, height: 430, deviceScaleFactor: 1, mobile: true });
  await tab.send('Emulation.setTouchEmulationEnabled', { enabled: true, maxTouchPoints: 5 });
  await tab.send('Page.addScriptToEvaluateOnNewDocument', { source: `localStorage.setItem('nfm.launcher', JSON.stringify({ touch:'on', easyStunts:true, devmode:true, lang:'es' }))` });
  for (const ext of [false, true]) {
    await tab.send('Page.navigate', { url: `http://localhost:8123/web/main.html?stage=1&car=2&players=1&res=1&music=0&sfxvol=0&debug=1${ext ? '&ext=classic' : ''}` });
    let ready = false;
    for (let n=0;n<240;n++) {
      if (ext && await tab.evaluate('window.__nfm?.xt.fase === 6')) {
        await tab.send('Input.dispatchKeyEvent',{type:'keyDown',key:'Enter',code:'Enter',windowsVirtualKeyCode:13});
        await sleep(250);
        await tab.send('Input.dispatchKeyEvent',{type:'keyUp',key:'Enter',code:'Enter',windowsVirtualKeyCode:13});
      }
      ready = await tab.evaluate(`!!window.__nfm && !!document.querySelector('[data-touch="up"]') && (!__nfm.xt.fase || __nfm.xt.fase === 0)`);
      if (ready) break;
      await sleep(250);
    }
    assert.ok(ready, 'race booted');
    await sleep(7500);
    // Screenshot also forces a headless rendering frame.
    const shot = await tab.send('Page.captureScreenshot', { format:'png' });
    if (!ext) writeFileSync(join(tmpdir(), 'nfm-touch-layout.png'), Buffer.from(shot.result.data,'base64'));
    const positions = await tab.evaluate(`(() => {
      const stage=document.getElementById('stage').getBoundingClientRect();
      const out={};
      for(const b of document.querySelectorAll('[data-touch]')) {
        const r=b.getBoundingClientRect(); out[b.dataset.touch]={x:r.x+r.width/2,y:r.y+r.height/2,id:b.dataset.touch==='up'?1:2};
        if(r.left<stage.left-1||r.right>stage.right+1||r.top<stage.top-1||r.bottom>stage.bottom+1) throw Error('button outside stage');
      } return out;
    })()`);
    assert.ok(positions?.up);
    const touch = async (type, points) => { const result = await tab.send('Input.dispatchTouchEvent', {type, touchPoints:points}); if(result.error) throw Error(JSON.stringify(result.error)); };
    const state = () => tab.evaluate(`({up:__nfm.gs.u[0].up,left:__nfm.gs.u[0].left,easy:__nfm.gs.u[0].easyStunts,z:__nfm.co[0].z,x:__nfm.co[0].x})`);
    const before = await state();
    await touch('touchStart',[positions.up]);
    await sleep(1200);
    const accelerating = await state();
    assert.equal(accelerating.up,true,'gas remains held');
    assert.ok(accelerating.x!==before.x || accelerating.z!==before.z,'holding gas drives the car');
    await touch('touchStart',[positions.up,positions.left]);
    await sleep(300);
    const together = await state();
    assert.deepEqual([together.up,together.left],[true,true],'gas and steering together');
    await touch('touchMove',[positions.up,{...positions.left,x:positions.left.x+2}]);
    assert.equal((await state()).up,true,'moving steering finger keeps gas');
    await touch('touchEnd',[positions.left]);
    await sleep(100);
    assert.equal((await state()).left,false,'steering released independently');
    assert.equal((await state()).up,true,'gas survives steering release');
    await touch('touchEnd',[]);
    assert.equal((await state()).up,false,'gas released');
    assert.equal((await state()).easy,true,'easy stunts explicitly enabled');
    const arrowMode = await tab.evaluate('__nfm.gs.u[0].arrace');
    await touch('touchStart',[positions.target]);
    await sleep(400);
    assert.equal(await tab.evaluate('__nfm.gs.u[0].arrace'),!arrowMode,'target button toggles arrow like A');
    await touch('touchEnd',[]);
    await touch('touchStart',[positions.target]);
    await touch('touchEnd',[]);
    assert.equal(await tab.evaluate('__nfm.gs.u[0].arrace'),arrowMode,'second press restores arrow mode');
    console.log(`${ext?'Extended':'Classic'}: held gas moves car, simultaneous steering, independent release, controls inside stage`);
  }
} finally {
  tab?.close(); browser.kill();
  await sleep(500);
  rmSync(profile,{recursive:true,force:true,maxRetries:10,retryDelay:200});
}

