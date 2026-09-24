// Visual smoke test for the Spanish car and stage selection screens.
// Start web/tools/serve.py on :8123, then run this script.
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
const profile = join(tmpdir(), `nfm-ui-es-${process.pid}`);
const browser = spawn(chrome, [
  '--headless=new', '--no-sandbox', '--enable-unsafe-swiftshader',
  '--mute-audio', '--window-size=900,506',
  `--remote-debugging-port=${port}`, `--user-data-dir=${profile}`, 'about:blank',
], { stdio: 'ignore' });

let tab;
try {
  tab = await attach(port, 'about:blank');
  await tab.send('Page.enable');
  await tab.send('Page.addScriptToEvaluateOnNewDocument', {
    source: "localStorage.setItem('nfm.launcher', JSON.stringify({lang:'es', musicvol:0, sfxvol:0, res:1}))",
  });
  await tab.send('Page.navigate', { url: 'http://localhost:8123/' });
  for (let i = 0; i < 160; i++) {
    if (await tab.evaluate("document.getElementById('brandsub')?.textContent === 'presiona enter para correr'")) break;
    await sleep(250);
  }
  await sleep(1500); // preview boot finishes after the subtitle appears
  const enter = async () => {
    await tab.send('Input.dispatchKeyEvent', { type: 'keyDown', key: 'Enter', code: 'Enter', windowsVirtualKeyCode: 13 });
    await tab.send('Input.dispatchKeyEvent', { type: 'keyUp', key: 'Enter', code: 'Enter', windowsVirtualKeyCode: 13 });
  };
  const shot = async (name) => {
    const result = await tab.send('Page.captureScreenshot', { format: 'png' });
    writeFileSync(join('web', 'tools', name), Buffer.from(result.result.data, 'base64'));
  };
  await enter();
  await sleep(5000);
  await shot('car-select-es.png');
  await enter();
  await sleep(5000);
  await shot('stage-select-es.png');
  await enter();
  await sleep(9000);
  await shot('race-hud-es.png');
  await tab.send('Input.dispatchKeyEvent', { type: 'keyDown', key: 'Escape', code: 'Escape', windowsVirtualKeyCode: 27 });
  await tab.send('Input.dispatchKeyEvent', { type: 'keyUp', key: 'Escape', code: 'Escape', windowsVirtualKeyCode: 27 });
  await sleep(1000);
  await shot('race-pause-es.png');
  await tab.send('Page.navigate', { url: 'http://localhost:8123/web/tools/pause-es-preview.html' });
  let border = '';
  for (let i = 0; i < 80; i++) {
    border = await tab.evaluate('document.body?.dataset.border');
    if (border === 'exact') break;
    await sleep(250);
  }
  if (border !== 'exact') throw new Error('Spanish pause border differs from paused.gif');
  console.log('Saved Spanish UI screenshots; pause border matches the original sprite');
} finally {
  tab?.close();
  browser.kill();
}
