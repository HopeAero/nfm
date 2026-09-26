// Sample-rate conversion for the sound effects, done here rather than by the browser.
//
// The clips in sounds.zip are 8 kHz PCM; the AudioContext runs at the device rate (44.1 or
// 48 kHz). decodeAudioData resamples them, and how well depends on the browser: Chrome's
// sinc is clean, but a linear resampler leaves images of the engine's rumble around 8 kHz --
// ~30 dB more energy over 4 kHz on the idle loop (measured), a steady whistle while the car
// stands still. Converting them here with a windowed sinc makes the result the same
// everywhere; the browser is handed buffers already at its own rate.

/** 8- or 16-bit PCM mono WAV -> { rate, samples: Float32Array in [-1, 1) }; anything else null. */
export function parseWav(bytes) {
  const b = bytes instanceof Uint8Array ? bytes : new Uint8Array(bytes);
  const tag = (o) => String.fromCharCode(b[o], b[o + 1], b[o + 2], b[o + 3]);
  if (b.length < 12 || tag(0) !== 'RIFF' || tag(8) !== 'WAVE') return null;
  const dv = new DataView(b.buffer, b.byteOffset, b.byteLength);
  let fmt = null;
  for (let p = 12; p + 8 <= b.length;) {
    const id = tag(p), size = dv.getUint32(p + 4, true), body = p + 8;
    if (id === 'fmt ') {
      fmt = { format: dv.getUint16(body, true), channels: dv.getUint16(body + 2, true),
        rate: dv.getUint32(body + 4, true), bits: dv.getUint16(body + 14, true) };
    } else if (id === 'data') {
      if (!fmt || fmt.format !== 1 || fmt.channels !== 1 || (fmt.bits !== 8 && fmt.bits !== 16)) return null;
      const n = Math.floor(Math.min(size, b.length - body) / (fmt.bits / 8));
      const samples = new Float32Array(n);
      if (fmt.bits === 8) for (let i = 0; i < n; i++) samples[i] = (b[body + i] - 128) / 128;
      else for (let i = 0; i < n; i++) samples[i] = dv.getInt16(body + i * 2, true) / 32768;
      return { rate: fmt.rate, samples };
    }
    p = body + size + (size & 1);   // chunks are word-aligned
  }
  return null;
}

const HALF = 16;       // taps each side, in source samples
const CUTOFF = 0.9;    // of the source Nyquist: the passband ends a little short of it
const RES = 512;       // kernel table entries per source sample, read with linear interpolation

const sinc = (x) => (x === 0 ? 1 : Math.sin(Math.PI * x) / (Math.PI * x));
// Blackman window over [-HALF, HALF]
const blackman = (u) => 0.42 + 0.5 * Math.cos(Math.PI * u / HALF) + 0.08 * Math.cos(2 * Math.PI * u / HALF);
// the windowed sinc at u = i / RES for i in 0..HALF*RES (it is even: |u|), plus a guard entry
const KERNEL = Float64Array.from({ length: HALF * RES + 2 },
  (_, i) => (i / RES >= HALF ? 0 : CUTOFF * sinc(CUTOFF * i / RES) * blackman(i / RES)));
function kernel(u) {
  const x = Math.abs(u) * RES, i = x | 0;
  return KERNEL[i] + (KERNEL[i + 1] - KERNEL[i]) * (x - i);
}

/**
 * `samples` at `from` Hz -> a Float32Array at `to` Hz. `periodic` treats the clip as one
 * turn of a loop (the engine and air clips): the kernel wraps around its ends, so the
 * loop point stays as smooth as any other sample.
 */
export function resample(samples, from, to, { periodic = false } = {}) {
  if (from === to) return Float32Array.from(samples);
  const n = samples.length, step = from / to;
  const out = new Float32Array(Math.round(n * to / from));
  for (let j = 0; j < out.length; j++) {
    const t = j * step, k0 = Math.floor(t);
    let acc = 0;
    for (let k = k0 - HALF + 1; k <= k0 + HALF; k++) {
      let s;
      if (k >= 0 && k < n) s = samples[k];
      else if (periodic) s = samples[((k % n) + n) % n];
      else continue;
      acc += s * kernel(t - k);
    }
    out[j] = acc;
  }
  return out;
}
