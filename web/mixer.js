// The sound effects' mixer, run on the audio thread as an AudioWorklet.
//
// Every clip arrives already at the context's rate (resample.js) and is summed
// here into one mono stream, so the browser only ever plays a single
// continuous output. Nothing is left to its AudioBufferSourceNode loop or its
// resampler -- the path where Opera GX buzzed at 48 kHz / 128 on the held
// engine loops (WORK.md). The same module is the worklet in a browser and, under
// node, the mixer the tests drive.
//
// Commands mirror web/audio.js, which posts them: play/stop are
// AudioClip.play()/stop() (stop cuts the most recent one-shot only), loop is a
// no-op on a clip already looping, stopLoop ends it.

const ENDED = (v) => !v.loop && v.pos === v.data.length;

export class Mixer {
  constructor() {
    this.clips = new Map();   // name -> Float32Array at the context's rate
    this.voices = [];         // { name, data, pos, loop }
    this.last = new Map();    // name -> its most recent one-shot voice, for stop
  }

  command({ op, name, samples }) {
    if (op === 'add') { this.clips.set(name, samples); return; }
    if (op === 'play' || op === 'loop') {
      const data = this.clips.get(name);
      if (!data || !data.length) return;
      const loop = op === 'loop';
      if (loop && this.voices.some((v) => v.loop && v.name === name)) return;
      const voice = { name, data, pos: 0, loop };
      this.voices.push(voice);
      if (!loop) this.last.set(name, voice);
      return;
    }
    if (op === 'stop') {
      const voice = this.last.get(name);
      this.last.delete(name);
      if (voice) this.remove((v) => v === voice);
      return;
    }
    if (op === 'stopLoop') this.remove((v) => v.loop && v.name === name);
  }

  /** Fill `out` with one block: the sum of every voice, loops wrapping sample-exact. */
  render(out) {
    out.fill(0);
    for (const v of this.voices) {
      const { data } = v;
      let pos = v.pos;
      for (let i = 0; i < out.length; i++) {
        if (pos === data.length) {
          if (!v.loop) break;
          pos = 0;
        }
        out[i] += data[pos++];
      }
      v.pos = pos;
    }
    this.remove(ENDED);
  }

  /** Drop the voices `gone` matches, in place: no garbage on the audio thread. */
  remove(gone) {
    let n = 0;
    for (const v of this.voices) {
      if (!gone(v)) this.voices[n++] = v;
      else if (this.last.get(v.name) === v) this.last.delete(v.name);
    }
    this.voices.length = n;
  }
}

if (typeof registerProcessor === 'function') {
  registerProcessor('nfm-mixer', class extends AudioWorkletProcessor {
    constructor() {
      super();
      this.mixer = new Mixer();
      this.port.onmessage = (e) => this.mixer.command(e.data);
    }
    process(inputs, outputs) {
      this.mixer.render(outputs[0][0]);
      return true;   // no inputs: stay alive until the context closes
    }
  });
}
