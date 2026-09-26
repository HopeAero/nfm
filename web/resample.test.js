// The sound effects are 8 kHz WAVs; the output runs at 44.1 or 48 kHz. resample.js converts
// them itself, with a windowed sinc, so the result never depends on how a browser resamples:
// a linear one (measured: +30 dB over 4 kHz on the idle engine) is a steady whistle.
import { test } from 'node:test';
import assert from 'node:assert';
import fs from 'node:fs';
import { parseZip } from './vfs.js';
import { parseWav, resample } from './resample.js';

const zip = await parseZip(new Uint8Array(fs.readFileSync(new URL('../data/sounds.zip', import.meta.url))));

// energy above `split` Hz relative to below it, in dB, Hann-windowed DFT over the middle of x
function highVsLow(x, rate, split = 4000) {
  const N = 4096, off = Math.max(0, (x.length - N) >> 1);
  const w = Float64Array.from({ length: N }, (_, i) => (x[(off + i) % x.length]) * (0.5 - 0.5 * Math.cos(2 * Math.PI * i / (N - 1))));
  let lo = 0, hi = 0;
  for (let k = 1; k < N / 2; k++) {
    let re = 0, im = 0;
    const a = 2 * Math.PI * k / N;
    for (let i = 0; i < N; i++) { re += w[i] * Math.cos(a * i); im -= w[i] * Math.sin(a * i); }
    if (k * rate / N < split) lo += re * re + im * im; else hi += re * re + im * im;
  }
  return 10 * Math.log10(hi / lo);
}

test('parseWav reads the 16-bit PCM engine clips', () => {
  const b = zip.get('20.wav');
  const w = parseWav(b);
  assert.strictEqual(w.rate, 8000);
  assert.strictEqual(w.samples.length, 1215);
  const dv = new DataView(b.buffer, b.byteOffset, b.byteLength);
  const data = b.length - 1215 * 2 - (b.length - 44 - 1215 * 2);   // a plain 44-byte header here
  assert.strictEqual(w.samples[100], dv.getInt16(data + 200, true) / 32768);
});

test('parseWav reads 8-bit unsigned PCM, and refuses what it cannot read', () => {
  const wav = (bits, body) => {
    const b = new Uint8Array(44 + body.length), dv = new DataView(b.buffer);
    b.set([...'RIFF'].map((c) => c.charCodeAt(0)), 0); dv.setUint32(4, 36 + body.length, true);
    b.set([...'WAVEfmt '].map((c) => c.charCodeAt(0)), 8); dv.setUint32(16, 16, true);
    dv.setUint16(20, 1, true); dv.setUint16(22, 1, true); dv.setUint32(24, 11025, true);
    dv.setUint32(28, 11025 * bits / 8, true); dv.setUint16(32, bits / 8, true); dv.setUint16(34, bits, true);
    b.set([...'data'].map((c) => c.charCodeAt(0)), 36); dv.setUint32(40, body.length, true); b.set(body, 44);
    return b;
  };
  const w = parseWav(wav(8, [128, 255, 0]));
  assert.strictEqual(w.rate, 11025);
  assert.deepStrictEqual(Array.from(w.samples), [0, 127 / 128, -1]);
  assert.strictEqual(parseWav(new TextEncoder().encode('.snd not a wav')), null);   // Extended's .au clips
  assert.strictEqual(parseWav(wav(24, [0, 0, 0])), null);
});

test('resampling keeps the length ratio and a tone; nothing is left above the source Nyquist', () => {
  const n = 1600, x = Float32Array.from({ length: n }, (_, i) => Math.sin(2 * Math.PI * 300 * i / 8000));
  const y = resample(x, 8000, 48000);
  assert.strictEqual(y.length, n * 6);
  // the tone survives: sample 600 (75 ms in) matches the exact sine at 48 kHz
  for (const j of [3600, 3601, 3605]) assert.ok(Math.abs(y[j] - Math.sin(2 * Math.PI * 300 * j / 48000)) < 0.01, `${j}`);
  assert.ok(highVsLow(y, 48000) < -60, `${highVsLow(y, 48000).toFixed(1)} dB over 4 kHz`);
});

test('the idle engine clips come out clean (a linear resampler leaves ~-38 dB here)', () => {
  for (const name of ['00', '20', '40']) {
    const w = parseWav(zip.get(`${name}.wav`));
    for (const rate of [48000, 44100]) {
      const y = resample(w.samples, w.rate, rate, { periodic: true });
      const db = highVsLow(y, rate);
      assert.ok(db < -60, `${name} at ${rate}: ${db.toFixed(1)} dB`);
    }
  }
});

test('a looped clip resampled periodically has no seam: the wrap is like any other step', () => {
  const w = parseWav(zip.get('24.wav'));
  const y = resample(w.samples, 8000, 48000, { periodic: true });
  let maxStep = 0;
  for (let i = 1; i < y.length; i++) maxStep = Math.max(maxStep, Math.abs(y[i] - y[i - 1]));
  assert.ok(Math.abs(y[0] - y[y.length - 1]) <= maxStep, 'the loop point jumps more than any sample step');
});

test('same rate in, same samples out', () => {
  const x = Float32Array.from([0.1, -0.2, 0.3]);
  assert.deepStrictEqual(Array.from(resample(x, 48000, 48000)), Array.from(x));
});

test('fast enough to convert every clip at load (well under a second)', () => {
  let total = 0;
  const t0 = performance.now();
  for (const name of zip.keys()) {
    const w = name.endsWith('.wav') && parseWav(zip.get(name));
    if (w) total += resample(w.samples, w.rate, 48000).length;
  }
  const ms = performance.now() - t0;
  assert.ok(total > 1.5e6, `${total} samples out`);   // every clip was converted (1.7 M at 48 kHz)
  assert.ok(ms < 800, `${ms.toFixed(0)} ms`);
});

test('audio.js hands the browser buffers already at its rate; what it cannot parse it decodes', async () => {
  const { clipBuffer } = await import('./audio.js');
  const made = [], decoded = [];
  const ctx = {
    sampleRate: 44100,
    createBuffer(ch, n, rate) { const d = new Float32Array(n); const b = { ch, n, rate, d, copyToChannel: (s) => d.set(s) }; made.push(b); return b; },
    decodeAudioData: async (buf) => { decoded.push(buf.byteLength); return 'decoded'; },
  };
  const engine = await clipBuffer(ctx, '20', zip.get('20.wav'));
  assert.strictEqual(engine.rate, 44100);
  assert.strictEqual(engine.n, Math.round(1215 * 44100 / 8000));
  assert.ok(engine.d.some((v) => v !== 0));
  assert.strictEqual(decoded.length, 0);
  assert.strictEqual(await clipBuffer(ctx, 'caught', new TextEncoder().encode('.snd au')), 'decoded');   // Extended's .au
});
