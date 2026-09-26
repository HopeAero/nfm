// New cars in a real browser: stock hashes unchanged, a new car races (moves, finite).
// Run with the local server on :8123: node web/tools/browser-newcar.mjs
import { spawn } from 'node:child_process';
import { tmpdir } from 'node:os';
import { join } from 'node:path';
import { rmSync } from 'node:fs';
import { setTimeout as sleep } from 'node:timers/promises';
import { attach } from './cdp.mjs';

// before the new cars (plan Task 1). The career selftest (?ext=career&stage=3&car=5) blocks
// headless Chrome on extended-mode itself (2026-09-26), so it is not run here.
const BASELINE = { classic: '271c3367' };
const chrome = process.platform === 'win32' ? 'C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe' : 'chromium';
const port = 9400 + process.pid % 500;
const profile = join(tmpdir(), `nfm-newcar-${process.pid}`);   // removed at the end: ~40 MB each
const browser = spawn(chrome, ['--headless=new', '--no-sandbox', '--enable-unsafe-swiftshader', '--mute-audio',
  `--remote-debugging-port=${port}`, `--user-data-dir=${profile}`, 'about:blank'], { stdio: 'ignore' });

async function selftest(tab, query) {
  await tab.send('Page.navigate', { url: `http://localhost:8123/web/main.html?${query}` });
  for (const end = Date.now() + 150000; Date.now() < end;) {   // a blocked page answers nothing: each read is raced
    const t = await Promise.race([tab.evaluate(`document.getElementById('log')?.textContent || ''`), sleep(5000).then(() => '')]);
    const m = /selftest \d+ ticks interp=\d: ([0-9a-f]+)\s+car0 (-?\d+),(-?\d+)/.exec(t ?? '');
    if (m) return { hash: m[1], x: +m[2], z: +m[3] };
    await sleep(500);
  }
  throw new Error(`no selftest line for ${query}`);
}

let tab, fail = 0;
const check = (ok, msg) => { console.log(`${ok ? 'PASS' : 'FAIL'}: ${msg}`); if (!ok) fail++; };
const run = async (label, query, ok) => {
  try { const r = await selftest(tab, query); check(ok(r), `${label}: ${r.hash} car0 ${r.x},${r.z}`); } catch (e) { check(false, `${label}: ${e.message}`); }
};
try {
  tab = await attach(port, 'about:blank');
  await tab.send('Page.enable');
  // the store populated, as the launcher leaves it: stock races must not notice
  await tab.send('Page.addScriptToEvaluateOnNewDocument', {
    source: `localStorage.setItem('nfm.ext.newcars', JSON.stringify([{ name: 'Simple Car', donor: 30 }]))` });
  await run('classic stock hash', 'ext=classic&stage=4&car=30&selftest=400', (r) => r.hash === BASELINE.classic);
  for (const stage of [4, 11]) {      // two classic stages: Extended's own numbering
    await run(`new car on classic stage ${stage}`, `ext=classic&stage=${stage}&car=200&newcar=Simple%20Car:30&selftest=400`,
      (r) => Number.isFinite(r.x) && Number.isFinite(r.z) && Math.abs(r.z + 760) > 200);
  }
} finally {
  tab?.close();
  browser.kill();
  await new Promise((r) => browser.once('exit', r));
  rmSync(profile, { recursive: true, force: true, maxRetries: 5, retryDelay: 200 });
}
process.exit(fail ? 1 : 0);
