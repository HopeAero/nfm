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

const HERE = new URL('./', import.meta.url);

for (const [file, method] of [['trace-drive.json.gz', 'drive']]) {
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
