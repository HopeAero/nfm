// Check the single-player Continue -> launcher reload boundary in a real browser.
// Run with the local server on :8123: node web/tools/browsercareer.mjs
import { spawn } from 'node:child_process';
import { tmpdir } from 'node:os';
import { join } from 'node:path';
import { setTimeout as sleep } from 'node:timers/promises';
import { attach } from './cdp.mjs';

const chrome = process.platform === 'win32'
  ? 'C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe'
  : 'chromium';
const port = 9400 + process.pid % 500;
const profile = join(tmpdir(), `nfm-career-${process.pid}`);
const browser = spawn(chrome, [
  '--headless=new', '--no-sandbox', '--enable-unsafe-swiftshader',
  '--autoplay-policy=no-user-gesture-required', '--mute-audio',
  `--remote-debugging-port=${port}`, `--user-data-dir=${profile}`, 'about:blank',
], { stdio: 'ignore' });

let tab;
try {
  tab = await attach(port, 'about:blank');
  await tab.send('Page.enable');
  await tab.send('Page.addScriptToEvaluateOnNewDocument', {
    source: "sessionStorage.setItem('nfm.next', JSON.stringify({ stage: 4 }))",
  });
  await tab.send('Page.navigate', { url: 'http://localhost:8123/' });

  let state;
  for (let i = 0; i < 160; i++) {
    state = await tab.evaluate(`({
      page: document.body?.dataset.page,
      pending: sessionStorage.getItem('nfm.next'),
      stage: JSON.parse(localStorage.getItem('nfm.launcher') || '{}').stage,
      ready: document.getElementById('brandsub')?.textContent,
    })`);
    if (state?.pending === null && state?.ready === 'press enter to race') break;
    await sleep(250);
  }
  if (state?.pending !== null || state?.ready !== 'press enter to race') {
    throw new Error(`launcher did not finish restoring career: ${JSON.stringify(state)}`);
  }
  await sleep(1500); // catch an asynchronous jump into car/stage selection
  state.page = await tab.evaluate('document.body.dataset.page');
  if (state.page !== 'menu' || state.stage !== 4) {
    throw new Error(`Continue should stay on menu with stage 4: ${JSON.stringify(state)}`);
  }
  console.log('PASS: Continue restores stage 4 and stays on the launcher menu');
} finally {
  tab?.close();
  browser.kill();
}
