// Captured jar calls, replayed on the transpiled port.
//
// Each fixture holds sampled calls of one method from a real Extended game in
// madness.jar (DiffRun -Ddiffrun.trace=Class.method): the objects the port
// touched or the jar changed, before and after, plus the random and clock
// state. web/tools/ext-trace.mjs rebuilds "before", runs the port's method and
// compares every field of "after", identities included. The fixture is cut
// from a full capture by that tool (--fixture); see web/ext/README.md.

import { test } from 'node:test';
import assert from 'node:assert';
import fs from 'node:fs';
import zlib from 'node:zlib';
import { run } from '../tools/ext-trace.mjs';
import { buildTrackGrid } from './trackgrid.js';

const HERE = new URL('./', import.meta.url);

for (const [file, method] of [['trace-drive.json.gz', 'drive'], ['trace-preform.json.gz', 'preform']]) {
  const url = new URL(file, HERE);
  if (!fs.existsSync(url)) continue;
  const calls = JSON.parse(zlib.gunzipSync(fs.readFileSync(url)));
  test(`${method}: ${calls.length} captured calls from a real race replay identically`, () => {
    const bad = [];
    for (const rec of calls) {
      const { err, diffs } = run(rec, method);
      if (err) bad.push(`call ${rec.call}: ${err}`);
      else if (diffs.length) bad.push(`call ${rec.call}: ${diffs.slice(0, 3).join(' ; ')}`);
    }
    assert.deepStrictEqual(bad, [], `${bad.length} of ${calls.length} differ; first: ${bad[0]}`);
  });
}

// The captured drive() calls again with the tracker grid race.js builds (trackgrid.js):
// the road-cell and wheel-sweep patches must leave each call as the jar has it.
{
  const url = new URL('trace-drive.json.gz', HERE);
  if (fs.existsSync(url)) {
    const calls = JSON.parse(zlib.gunzipSync(fs.readFileSync(url)));
    test(`drive with the tracker grid: ${calls.length} captured calls replay identically`, () => {
      const bad = [];
      let gridded = 0;
      for (const rec of calls) {
        const { err, diffs } = run(rec, 'drive', true, (roots) => {
          try { buildTrackGrid(roots[3]); if (roots[3].grid) gridded++; } catch { /* a stub the call never reads */ }
        });
        if (err) bad.push(`call ${rec.call}: ${err}`);
        else if (diffs.length) bad.push(`call ${rec.call}: ${diffs.slice(0, 3).join(' ; ')}`);
      }
      assert.deepStrictEqual(bad, [], `${bad.length} of ${calls.length} differ; first: ${bad[0]}`);
      assert.ok(gridded > 0, 'no call got a grid');
    });
  }
}
