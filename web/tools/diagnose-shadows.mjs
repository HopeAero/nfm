// Inspect the stage 8 race intro and identify polygons cast by object shadows.
import { spawn } from 'node:child_process';
import { writeFileSync } from 'node:fs';
import { tmpdir } from 'node:os';
import { join } from 'node:path';
import { setTimeout as sleep } from 'node:timers/promises';
import { attach } from './cdp.mjs';

const chrome = process.platform === 'win32'
  ? 'C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe'
  : 'chromium';
const port = 9500 + process.pid % 400;
const profile = join(tmpdir(), `nfm-shadow-${process.pid}`);
const browser = spawn(chrome, [
  '--headless=new', '--no-sandbox', '--enable-unsafe-swiftshader',
  '--mute-audio', '--window-size=900,506',
  `--remote-debugging-port=${port}`, `--user-data-dir=${profile}`, 'about:blank',
], { stdio: 'ignore' });

let tab;
try {
  tab = await attach(port, 'about:blank');
  await tab.send('Page.enable');
  await tab.send('Page.navigate', {
    url: 'http://localhost:8123/web/main.html?stage=8&car=0&players=7&res=1&bench=0&debug=1',
  });
  const shot = async (name) => {
    const result = await tab.send('Page.captureScreenshot', { format: 'png' });
    writeFileSync(join('web', 'tools', name), Buffer.from(result.result.data, 'base64'));
  };
  let elapsed = 0;
  for (const sec of [3, 6, 9, 10]) {
    if (sec === 10) await tab.send('Input.dispatchKeyEvent', { type: 'keyDown', key: 'ArrowUp', code: 'ArrowUp', windowsVirtualKeyCode: 38 });
    await sleep((sec - elapsed) * 1000);
    elapsed = sec;
    await shot(`shadow-${sec}s.png`);
    console.log(sec, await tab.evaluate('({ fase: window.__nfm?.xt.fase, starcnt: window.__nfm?.xt.starcnt, camera: window.__nfm?.medium.trk })'));
  }
  await tab.send('Input.dispatchKeyEvent', { type: 'keyUp', key: 'ArrowUp', code: 'ArrowUp', windowsVirtualKeyCode: 38 });
  await tab.evaluate("import('/web/GameSparker.js').then(({ GameSparker }) => { GameSparker.prototype.simulate = () => {}; })");
  await sleep(200);
  await shot('shadow-frozen.png');
  await tab.evaluate("Promise.all([import('/web/Plane.js'), import('/web/ContO.js')]).then(([{ Plane }, { ContO }]) => { Plane.prototype.s = () => {}; ContO.prototype.lowshadow = () => {}; })");
  await sleep(200);
  await shot('shadow-disabled.png');
  await tab.evaluate("import('/web/ContO.js').then(({ ContO }) => { const draw = ContO.prototype.d; ContO.prototype.d = function (g) { if (this.baseIndex >= 0 && this.baseIndex < 16) return; return draw.call(this, g); }; })");
  await sleep(200);
  await shot('cars-disabled.png');
} finally {
  tab?.close();
  browser.kill();
}
