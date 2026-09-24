// Compare a race frame with and without Medium's decorative ground polygons.
// Run while web/tools/serve.py is serving the repository on port 8123.
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
const profile = join(tmpdir(), `nfm-ground-${process.pid}`);
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
    url: 'http://localhost:8123/web/main.html?stage=8&players=8&res=1&bench=0',
  });
  await sleep(13000);
  const shot = async (name) => {
    const result = await tab.send('Page.captureScreenshot', { format: 'png' });
    writeFileSync(join('web', 'tools', name), Buffer.from(result.result.data, 'base64'));
  };
  await shot('ground-original.png');
  await tab.evaluate("import('/web/Medium.js').then(({ Medium }) => { Medium.prototype.groundpolys = () => {}; })");
  await sleep(1000);
  await shot('ground-without-polys.png');
  await tab.evaluate("Promise.all([import('/web/Plane.js'), import('/web/ContO.js')]).then(([{ Plane }, { ContO }]) => { Plane.prototype.s = () => {}; ContO.prototype.lowshadow = () => {}; })");
  await sleep(1000);
  await shot('ground-without-polys-or-shadows.png');
  console.log('Saved ground comparison screenshots');
} finally {
  tab?.close();
  browser.kill();
}
