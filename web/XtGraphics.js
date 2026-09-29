import { idiv, i32, trunc, fr, intArray, floatArray, random, RGBtoHSB, HSBtoRGB, setDrawPhase } from './java.js';
import * as music from './music.js';
import { tr, lang } from './i18n.js';

export class XtGraphics {
  constructor(m = null, cd = null, rd = null, app = null) {
    this.rd = rd;
    this.m = m;
    this.cd = cd;
    this.ftm = null;
    this.ob = null;
    this.app = app;
    this.fase = 111;
    this.oldfase = 0;
    this.starcnt = 0;
    this.mtop = false;
    this.opselect = 0;
    this.dropf = 0;
    this.cfase = 0;
    this.firstime = true;
    this.shaded = false;
    this.flipo = 0;
    this.nextc = 0;
    this.multion = 0;
    this.gmode = 0;
    this.unlocked = Int32Array.from([1, 1]);
    // The port's: true draws a rim around the HUD graphics on a dark sky
    // (images.js halo) instead of the Java's boxes behind them.
    this.hudOutline = false;
    // "automatic" dark-sky HUD, set by main.js: the counters' ink and the
    // contrast fix drawcs applies to every announcement. Null = the Java's.
    this.hudInk = null;
    this.hudReadable = null;
    this.scm = Int32Array.from([0, 0]);
    this.looped = 1;
    this.warning = 0;
    this.newparts = false;
    this.logged = false;
    this.gotlog = false;
    this.autolog = false;
    this.nofull = false;
    this.nfreeplays = 0;
    this.ndisco = 0;
    this.hours = 8;
    this.onviewpro = false;
    this.playingame = -1;
    this.onjoin = -1;
    this.ontyp = 0;
    this.lan = false;
    this.arnp = Float32Array.from([0.5, 0.0, 0.0, 1.0, 0.5, 0.0]);
    this.nickname = "";
    this.clan = "";
    this.nickey = "";
    this.clankey = "";
    this.backlog = "";
    this.server = "multiplayer.needformadness.com";
    this.localserver = "";
    this.servername = "Madness";
    this.servport = 7071;
    this.gameport = 7001;
    this.acexp = 0;
    this.discon = 0;
    this.cntptrys = 5;
    this.delays = Int32Array.from([600, 600, 600]);
    this.nplayers = 7;
    this.im = 0;
    // Netplay sets this to every human-driven slot. Single player leaves it
    // null, and `human()` then means exactly what `im` used to mean.
    this.humans = null;
    this.plnames = new Array(8).fill("");
    this.osc = 10;
    this.minsl = 0;
    this.maxsl = 15;
    this.sc = Int32Array.from([0, 0, 0, 0, 0, 0, 0, 0]);
    this.xstart = Int32Array.from([0, -350, 350, 0, -350, 350, 0, 0]);
    this.zstart = Int32Array.from([-760, -380, -380, 0, 380, 380, 760, 0]);
    this.allrnp = Array.from({ length: 8 }, () => floatArray(6));
    this.isbot = new Array(8).fill(false);
    this.clangame = 0;
    this.clanchat = false;
    this.pclan = new Array(8).fill("");
    this.gaclan = "";
    this.lcarx = 0;
    this.lcary = 0;
    this.lcarz = 0;
    this.dcrashes = intArray(8);
    this.beststunt = 0;
    this.laptime = 0;
    this.fastestlap = 0;
    this.sendstat = 0;
    this.testdrive = 0;
    this.holdit = false;
    this.holdcnt = 0;
    this.winner = true;
    this.flexpix = null;
    this.smokey = intArray(94132);
    this.flatrstart = 0;
    this.runner = null;
    this.runtyp = 0;
    this.forstart = 0;
    this.exitm = 0;
    this.odmg = null;
    this.opwr = null;
    this.opos = null;
    this.osped = null;
    this.owas = null;
    this.olap = null;
    this.oyourwasted = null;
    this.odisco = null;
    this.ogamefinished = null;
    this.oyoulost = null;
    this.oyouwon = null;
    this.oyouwastedem = null;
    this.ogameh = null;
    this.owgame = null;
    this.oloadingmusic = null;
    this.oflaot = null;
    this.oexitgame = null;
    this.mload = null;
    this.dmg = null;
    this.pwr = null;
    this.pos = null;
    this.sped = null;
    this.was = null;
    this.lap = null;
    this.br = null;
    this.select = null;
    this.loadingmusic = null;
    this.yourwasted = null;
    this.disco = null;
    this.gamefinished = null;
    this.youlost = null;
    this.youwon = null;
    this.youwastedem = null;
    this.gameh = null;
    this.wgame = null;
    this.congrd = null;
    this.gameov = null;
    this.carsbg = null;
    this.carsbgc = null;
    this.selectcar = null;
    this.statb = null;
    this.statbo = null;
    this.mdness = null;
    this.paused = null;
    this.radicalplay = null;
    this.logocars = null;
    this.logomadnes = null;
    this.logomadbg = null;
    this.byrd = null;
    this.bggo = null;
    this.opback = null;
    this.nfmcoms = null;
    this.opti = null;
    this.opti2 = null;
    this.bgmain = null;
    this.rpro = null;
    this.nfmcom = null;
    this.flaot = null;
    this.brt = null;
    this.arn = null;
    this.exitgame = null;
    this.pgate = null;
    this.fixhoop = null;
    this.sarrow = null;
    this.stunts = null;
    this.racing = null;
    this.wasting = null;
    this.plus = null;
    this.space = null;
    this.arrows = null;
    this.chil = null;
    this.ory = null;
    this.kz = null;
    this.kx = null;
    this.kv = null;
    this.km = null;
    this.kn = null;
    this.ks = null;
    this.kenter = null;
    this.nfm = null;
    this.login = null;
    this.register = null;
    this.play = null;
    this.sdets = null;
    this.cancel = null;
    this.bob = null;
    this.bot = null;
    this.bol = null;
    this.bolp = null;
    this.bor = null;
    this.borp = null;
    this.logout = null;
    this.change = null;
    this.pln = null;
    this.pon = null;
    this.dome = null;
    this.upgrade = null;
    this.bols = null;
    this.bolps = null;
    this.bors = null;
    this.borps = null;
    this.games = null;
    this.exit = null;
    this.chat = null;
    this.players = null;
    this.cgame = null;
    this.ccar = null;
    this.lanm = null;
    this.asu = null;
    this.asd = null;
    this.pls = null;
    this.sts = null;
    this.gmc = null;
    this.stg = null;
    this.crd = null;
    this.roomp = null;
    this.myfr = null;
    this.mycl = null;
    this.cnmc = null;
    this.redy = null;
    this.ntrg = null;
    this.bcl = new Array(2).fill(null);
    this.bcr = new Array(2).fill(null);
    this.bc = new Array(2).fill(null);
    this.cmc = null;
    this.myc = null;
    this.gac = null;
    this.yac = null;
    this.ycmc = null;
    this.top20s = null;
    this.trackbgImg = new Array(2).fill(null);   // Java's field trackbg; the name is taken by the method
    this.dude = new Array(3).fill(null);
    this.duds = 0;
    this.dudo = 0;
    this.next = new Array(2).fill(null);
    this.back = new Array(2).fill(null);
    this.contin = new Array(2).fill(null);
    this.ostar = new Array(2).fill(null);
    this.star = new Array(3).fill(null);
    this.pcontin = 0;
    this.pnext = 0;
    this.pback = 0;
    this.pstar = 0;
    this.orank = new Array(8).fill(null);
    this.rank = new Array(8).fill(null);
    this.ocntdn = new Array(4).fill(null);
    this.cntdn = new Array(4).fill(null);
    this.gocnt = 0;
    // engs[signature][rev] and air[] are LOOPS, switched by sparkeng(); the
    // rest are one-shots. Names are the entries in sounds.zip minus the
    // extension -- the engine's are literally "00".."44".
    this.engs = Array.from({ length: 5 }, (_, k) =>
      Array.from({ length: 5 }, (_, j) => this._clip(`${k}${j}`)));
    this.pengs = new Array(5).fill(false);
    this.air = Array.from({ length: 6 }, (_, l) => this._clip(`air${l}`));
    this.aird = false;
    this.grrd = false;
    this.crashClips = new Array(3).fill(null);
    this.lowcrash = new Array(3).fill(null);
    this.tires = this._clip('tires');
    this.checkpoint = this._clip('checkpoint');
    this.carfixed = this._clip('carfixed');
    this.powerup = this._clip('powerup');
    this.three = this._clip('three');
    this.two = this._clip('two');
    this.one = this._clip('one');
    this.go = this._clip('go');
    this.wastd = this._clip('wasted');
    this.firewasted = this._clip('firewasted');
    this.pwastd = false;
    this.skidClips = new Array(3).fill(null);
    this.dustskid = new Array(3).fill(null);
    this.scrapeClips = new Array(4).fill(null);
    this.mutes = false;
    this.snd = null;      // web/audio.js, attached by main.js
    this.intertrack = null;
    this.strack = music;
    this.loadedt = false;
    this.mutem = false;
    this.badmac = false;
    this.arrace = false;
    this.alocked = -1;
    this.lalocked = -1;
    this.cntflock = 0;
    this.onlock = false;
    this.ana = 0;
    this.cntan = 0;
    this.cntovn = 0;
    this.flk = false;
    this.tcnt = 30;
    this.tflk = false;
    this.say = "";
    this.wasay = false;
    this.clear = 0;
    this.posit = 0;
    this.wasted = 0;
    this.laps = 0;
    this.dested = Int32Array.from([0, 0, 0, 0, 0, 0, 0, 0]);
    this.dmcnt = 0;
    this.dmflk = false;
    this.pwcnt = 0;
    this.pwflk = false;
    // The stunt call-out is assembled from these fragments at runtime, so it
    // is translated here, piece by piece, rather than where it is drawn.
    this.adj = [
      ["Cool", "Alright", "Nice"],
      ["Wicked", "Amazing", "Super"],
      ["Awesome", "Ripping", "Radical"],
      ["What the...?", "You're a super star!!!!", "Who are you again...?"],
      ["surf style", "off the lip", "bounce back"]
    ].map((row) => row.map(tr));
    this.exlm = ["!", "!!", "!!!"];
    this.loop = "";
    this.spin = "";
    this.asay = "";
    this.auscnt = 45;
    this.aflk = false;
    this.sndsize = Int32Array.from([39, 128, 23, 58, 106, 140, 81, 135, 38, 141, 106, 76, 56, 116, 92, 208, 70, 80, 152, 102, 27, 65, 52, 30, 151, 129, 80, 44, 57, 123, 202, 210, 111]);
    this.hello = null;
    this.sign = null;
    this.loadbar = null;
    this.kbload = 0;
    this.dnload = 0;
    this.shload = 0.0;
    this.socket = null;
    this.din = null;
    this.dout = null;
    this.radpx = 212;
    this.pin = 60;
    this.trkx = Int32Array.from([65, 735]);
    this.trkl = 0;
    this.trklim = 0;
    this.lmode = 0;
    this.bgmy = Int32Array.from([0, -400]);
    this.bgf = 0.0;
    this.bgup = false;
    this.ovx = intArray(4);
    this.ovy = intArray(4);
    this.ovw = intArray(4);
    this.ovh = intArray(4);
    this.ovsx = intArray(4);
    this.removeds = 0;
    this.nfmtab = 0;
    this.justwon1 = false;
    this.justwon2 = false;
    this.lfrom = 0;
    this.lockcnt = 0;
    this.showtf = false;
    this.ransay = 0;
    this.cnames = [
      ["", "", "", "", "", "", "Game Chat  "],
      ["", "", "", "", "", "", "Your Clan's Chat  "]
    ];
    this.sentn = [
      ["", "", "", "", "", "", ""],
      ["", "", "", "", "", "", ""]
    ];
    this.updatec = Int32Array.from([-1, -1]);
    this.movepos = Int32Array.from([0, 0]);
    this.pointc = Int32Array.from([6, 6]);
    this.floater = Int32Array.from([0, 0]);
    this.cntchatp = Int32Array.from([0, 0]);
    this.msgflk = Int32Array.from([0, 0]);
    this.lcmsg = ["", ""];
    this.flkat = 0;
    this.movly = 0;
    this.gxdu = 0;
    this.gydu = 0;
    this.muhi = 0;
    this.lsc = -1;
    this.mouson = -1;
    this.onmsc = -1;
    this.remi = false;
    this.basefase = 0;
    this.noclass = false;
    this.gatey = 300;
    this.pgatx = Int32Array.from([211, 240, 280, 332, 399, 466, 517, 558, 586]);
    this.pgaty = Int32Array.from([193, 213, 226, 237, 244, 239, 228, 214, 196]);
    this.pgady = Int32Array.from([0, 0, 0, 0, 0, 0, 0, 0, 0]);
    this.pgas = new Array(9).fill(false);
    this.waitlink = 0;
    this.lxm = -10;
    this.lym = -10;
    this.pwait = 7;
    this.stopcnt = 0;
    this.cntwis = 0;
    this.lcn = 0;
    this.crshturn = 0;
    this.bfcrash = 0;
    this.bfskid = 0;
    this.crashup = false;
    this.skidup = false;
    this.skflg = 0;
    this.dskflg = 0;
    this.bfscrape = 0;
    this.sturn0 = 0;
    this.sturn1 = 0;
    this.bfsc1 = 0;
    this.bfsc2 = 0;
    this.flatr = 0;
    this.flyr = 0;
    this.flyrdest = 0;
    this.flang = 0;
  }

  // --- sound hooks ---
  // --- sound -----------------------------------------------------------
  //
  // Ported from xtGraphics.java:9289-9430. java.applet.AudioClip's contract is
  // "play() fires a one-shot, stop() cuts it", which web/audio.js reproduces,
  // so these bodies are the Java's unchanged -- including the rotation
  // counters that stop the same sample repeating back to back, and the bfXXX
  // debounce counters the tick decrements.
  //
  // `snd` is null until main.js attaches it; every call site tolerates that,
  // so a missing or undecodable sounds.zip costs sound and nothing else.

  /** One-shot by name; silent if audio is unavailable. */
  /**
   * Is slot `i` driven by a person?
   *
   * Distinct from `i === im`, which asks "is it MINE". Simulation branches
   * must use this one: the Java only ever has one human, so it conflates the
   * two, and a branch that keys on `im` runs on a different car on each
   * netplay client and diverges the two simulations. Presentation may keep
   * using `im` -- you only hear your own car scrape.
   */
  human(i) {
    return this.humans ? this.humans.has(i) : i === this.im;
  }

  _snd(name) {
    if (this.snd && !this.mutes) this.snd.play(name);
  }

  /**
   * An AudioClip stand-in bound to one name in sounds.zip.
   *
   * The Java holds `AudioClip` objects in `engs[][]`, `air[]`, `wastd` and so
   * on and calls `.play()` / `.loop()` / `.stop()` on them. Handing the port
   * the same shape keeps those call sites transpilable line by line, which
   * matters most in playsounds(), where the engine is a state machine over
   * which of five clips is currently looping.
   */
  _clip(name) {
    return {
      play: () => { if (this.snd && !this.mutes) this.snd.play(name); },
      loop: () => { if (this.snd && !this.mutes) this.snd.loop(name); },
      stop: () => { if (this.snd) { this.snd.stopLoop(name); this.snd.stop(name); } },
      resume: () => { if (this.snd && !this.mutes) this.snd.loop(name); },
    };
  }

  crash(a, n) {
    setDrawPhase(true);
    try { return this.#crash(a, n); } finally { setDrawPhase(false); }
  }

  #crash(a, n) {
    if (this.bfcrash !== 0) return;
    if (n === 0) {
      if (Math.abs(a) > 25.0 && Math.abs(a) < 170.0) {
        this._snd(`lowcrash${this.crshturn + 1}`);
        this.bfcrash = 2;
      }
      if (Math.abs(a) >= 170.0) {
        this._snd(`crash${this.crshturn + 1}`);
        this.bfcrash = 2;
      }
      if (Math.abs(a) > 25.0) {
        if (this.crashup) --this.crshturn;
        else ++this.crshturn;
        if (this.crshturn === -1) this.crshturn = 2;
        if (this.crshturn === 3) this.crshturn = 0;
      }
    }
    if (n === -1) {
      if (Math.abs(a) > 25.0 && Math.abs(a) < 170.0) {
        this._snd('lowcrash3');
        this.bfcrash = 2;
      }
      if (Math.abs(a) > 170.0) {
        this._snd('crash3');
        this.bfcrash = 2;
      }
    }
    if (n === 1) {
      this._snd('tires');
      this.bfcrash = 3;
    }
  }

  skid(n, n2) {
    setDrawPhase(true);
    try { return this.#skid(n, n2); } finally { setDrawPhase(false); }
  }

  #skid(n, n2) {
    if (this.bfcrash === 0 && this.bfskid === 0 && n2 > 150.0) {
      if (n === 0) {
        this._snd(`skid${this.skflg + 1}`);
        if (this.skidup) --this.skflg;
        else ++this.skflg;
        if (this.skflg === 3) this.skflg = 0;
        if (this.skflg === -1) this.skflg = 2;
      } else {
        this._snd(`dustskid${this.dskflg + 1}`);
        if (this.skidup) --this.dskflg;
        else ++this.dskflg;
        if (this.dskflg === 3) this.dskflg = 0;
        if (this.dskflg === -1) this.dskflg = 2;
      }
      this.bfskid = 5;
    }
  }

  scrape(n, n2, n3) {
    setDrawPhase(true);
    try { return this.#scrape(n, n2, n3); } finally { setDrawPhase(false); }
  }

  #scrape(n, n2, n3) {
    if (this.bfscrape === 0 && Math.sqrt(n * n + n2 * n2 + n3 * n3) / 10.0 > 10.0) {
      let n4 = 0;
      if (this.m.random() > this.m.random()) n4 = 1;
      if (n4 === 0) {
        this.sturn1 = 0;
        ++this.sturn0;
        if (this.sturn0 === 3) { n4 = 1; this.sturn1 = 1; this.sturn0 = 0; }
      } else {
        this.sturn0 = 0;
        ++this.sturn1;
        if (this.sturn1 === 3) { n4 = 0; this.sturn0 = 1; this.sturn1 = 0; }
      }
      this._snd(`scrape${n4 + 1}`);
      this.bfscrape = 5;
    }
  }

  gscrape(n, n2, n3) {
    setDrawPhase(true);
    try { return this.#gscrape(n, n2, n3); } finally { setDrawPhase(false); }
  }

  #gscrape(n, n2, n3) {
    if ((this.bfsc1 === 0 || this.bfsc2 === 0)
        && Math.sqrt(n * n + n2 * n2 + n3 * n3) / 10.0 > 15.0) {
      // scrape[2] and scrape[3] are two separate clips of scrape3.wav in the
      // Java, so a fresh scrape can cut the previous one while the other keeps
      // playing. Two distinct keys over the same buffer preserve that.
      if (this.bfsc1 === 0) {
        if (this.snd && !this.mutes) { this.snd.stop('scrape3'); this.snd.play('scrape3'); }
        this.bfsc1 = 12;
        this.bfsc2 = 6;
      } else {
        if (this.snd && !this.mutes) { this.snd.stop('scrape3b'); this.snd.play('scrape3b'); }
        this.bfsc2 = 12;
        this.bfsc1 = 6;
      }
    }
  }

  // --- stubs ---
  snap(_stage) {}
  /**
   * Reset every per-race counter — xtGraphics.java:1484.
   *
   * `starcnt = 130` and `gocnt = 3` are what start a race: GameSparker.simulate
   * holds the physics until starcnt reaches 0 and runs the orbiting intro
   * camera meanwhile, and stat() draws the 3-2-1-GO and plays its sounds on
   * the way down. Leave them at their constructor zeroes and the race begins
   * mid-air with no countdown, which is what this port did before.
   *
   * Java calls loadstrack() here too. `loadedt` gates every strack call in
   * playsounds(), so it flips true only once the module has actually parsed --
   * load() resolves false on a missing zip or a superseded load, and reading a
   * module-global flag afterwards instead would mark whatever finished last.
   *
   * Not ported: the `fase == 22` branch resetting the chat buffers, and the
   * `fase == 2 || fase == -22` guard around sortcars -- the port calls
   * sortcars from its own loadstage rather than from here.
   */
  resetstat(_stage, trackvol = 200, trackname = '') {
    this.arrace = false;
    this.alocked = -1;
    this.lalocked = -1;
    this.cntflock = 90;
    this.onlock = false;
    this.ana = 0;
    this.cntan = 0;
    this.cntovn = 0;
    this.tcnt = 30;
    this.wasay = false;
    this.clear = 0;
    this.dmcnt = 0;
    this.pwcnt = 0;
    this.auscnt = 45;
    this.pnext = 0;
    this.pback = 0;
    this.starcnt = 130;
    this.gocnt = 3;
    this.grrd = true;
    this.aird = true;
    this.bfcrash = 0;
    this.bfscrape = 0;
    this.cntwis = 0;
    this.bfskid = 0;
    this.pwait = 7;
    this.forstart = 200;
    this.exitm = 0;
    this.holdcnt = 0;
    this.holdit = false;
    this.winner = false;
    this.wasted = 0;
    for (let i = 0; i < 8; ++i) {
      this.dested[i] = 0;
      this.isbot[i] = false;
      this.dcrashes[i] = 0;
    }
    this.runtyp = 0;
    this.discon = 0;
    this.dnload = 0;
    this.beststunt = 0;
    this.laptime = 0;
    this.fastestlap = 0;
    this.sendstat = 0;

    // loadstrack's special cases: stage 27 is party.zip in party mode, and a
    // custom stage (stage < 0) plays its soundtrack(...) from mystages/mymusic
    // -- or nothing at all without one (the Java's empty RadicalMod()).
    if (_stage < 0) {
      this.loadedt = false;
      if (trackname) this.loadmusic(music.customTrack(trackname), trackvol);
      return;
    }
    const track = (_stage === 27 && this.gmode === 2) ? 'party' : _stage;
    this.loadmusic(track, trackvol);
  }

  /** Load a track and start it unless music is muted. Never throws. */
  loadmusic(track, trackvol = 200) {
    this.loadedt = false;
    this.strack.load(track, trackvol).then((ok) => {
      if (!ok) return;
      this.loadedt = true;
      if (!this.mutem) this.strack.resume();
    });
  }
  colorCar(_contO, _n) {}
  /**
   * xtGraphics.loadingstage (xtGraphics.java:1971) -- "Loading, please
   * wait..." over the scrolling track backdrop, drawn once before loadstage.
   */
  loadingstage(n, b) {
    this.trackbg(true);
    this.rd.drawImage(this.br, 65, 25);
    this.rd.setColor(212, 214, 138);
    this.rd.fillRoundRect(265, 201, 270, 26, 20, 40);
    this.rd.setColor(57, 64, 8);
    this.rd.drawRoundRect(265, 201, 270, 26, 20, 40);
    this.rd.setFont('Arial', 1, 12);
    this.ftm = this.rd.getFontMetrics();
    this.drawcs(219, 'Loading, please wait...', 58, 61, 17, 3);
    if (b) this.rd.drawImage(this.select, 338, 35);
    this.removeds = 0;
  }

  /**
   * xtGraphics.trackbg (xtGraphics.java:1707) -- two copies of track.jpg
   * scrolling left under the stage-select fly-around, cutting to the dodged
   * copy for one frame at random intervals. `b` forces the plain one. The
   * image field is `trackbgImg` here: in Java `trackbg` is both a field and
   * this method, which JS cannot express.
   */
  trackbg(b) {
    let n = 0;
    ++this.trkl;
    if (this.trkl > this.trklim) {
      n = 1;
      this.trklim = trunc(random() * 40.0);
      this.trkl = 0;
    }
    if (b) n = 0;
    for (let i = 0; i < 2; ++i) {
      this.rd.drawImage(this.trackbgImg[n], this.trkx[i], 25);
      this.trkx[i] -= 10;
      if (this.trkx[i] <= -605) this.trkx[i] = 735;
    }
    this.rd.setColor(0, 0, 0);
    this.rd.fillRect(0, 0, 65, 450);
    this.rd.fillRect(735, 0, 65, 450);
    this.rd.fillRect(65, 0, 670, 25);
    this.rd.fillRect(65, 425, 670, 25);
  }

  /**
   * xtGraphics.cantgo (xtGraphics.java:1993): fase 4, a career stage past the
   * one being played. Counts `lockcnt` down (set to 100 by stageselect) and
   * goes back to fase 1 when it runs out or on Enter/Space/Left.
   */
  /** The lap / wasted counters' ink: the Java's (0, 0, 100), or main.js's contrast-adjusted one. */
  hudText() {
    const c = this.hudInk;
    if (c) this.rd.setColor(c[0], c[1], c[2]);
    else this.rd.setColor(0, 0, 100);
  }

  cantgo(control) {
    this.pnext = 0;
    this.trackbg(false);
    this.rd.drawImage(this.br, 65, 25);
    this.rd.drawImage(this.select, 338, 35);
    this.rd.setFont('Arial', 1, 13);
    this.ftm = this.rd.getFontMetrics();
    this.drawcs(130, 'This stage will be unlocked when stage ' + this.unlocked[this.gmode - 1] + ' is complete!', 177, 177, 177, 3);
    for (let i = 0; i < 9; ++i) this.rd.drawImage(this.pgate, 277 + i * 30, 215);
    this.rd.setFont('Arial', 1, 12);
    this.ftm = this.rd.getFontMetrics();
    if (this.aflk) {
      this.drawcs(185, '[ Stage ' + (this.unlocked[this.gmode - 1] + 1) + ' Locked ]', 255, 128, 0, 3);
      this.aflk = false;
    } else {
      this.drawcs(185, '[ Stage ' + (this.unlocked[this.gmode - 1] + 1) + ' Locked ]', 255, 0, 0, 3);
      this.aflk = true;
    }
    this.rd.drawImage(this.back[this.pback], 370, 345);
    --this.lockcnt;
    if (this.lockcnt === 0 || control.enter || control.handb || control.left) {
      control.left = false;
      control.handb = false;
      control.enter = false;
      this.fase = 1;
    }
  }

  /**
   * xtGraphics.stageselect (xtGraphics.java:2024), drawn over the fly-around
   * at fase 1. Left/right step the stage and return to fase 2 (reload);
   * Enter goes on to fase 5, the race.
   */
  stageselect(checkPoints, control, n, n2, b) {
    setDrawPhase(true);
    try { this.#stageselect(checkPoints, control, n, n2, b); } finally { setDrawPhase(false); }
  }

  #stageselect(checkPoints, control, n, n2, b) {
    this.rd.drawImage(this.br, 65, 25);
    this.rd.drawImage(this.select, 338, 35);
    if (this.testdrive !== 3 && this.testdrive !== 4) {
      // `<= 27`: the port's free-play Multiplayer stages (28-32, carselect.js)
      // are walked and drawn by the stage select page, as custom stages are
      if (checkPoints.stage > 0 && checkPoints.stage <= 27 && this.cd.staction === 0) {
        if (checkPoints.stage !== 1 && (checkPoints.stage !== 11 || this.gmode !== 2)) {
          this.rd.drawImage(this.back[this.pback], 115, 135);
        }
        if (checkPoints.stage !== 27) this.rd.drawImage(this.next[this.pnext], 625, 135);
      }
      if (this.gmode === 0) {
        // TODO not ported: the AWT choices -- game (NFM 1 / NFM 2 / My Stages /
        // Top20 / Stage Maker), the per-game stage list and Normal/Practice --
        // and everything behind them: account login, My Stages, Top20
        // downloads. Left/right below still walk the 27 stages.
        this.rd.setFont('Arial', 1, 13);
        this.ftm = this.rd.getFontMetrics();
        if (this.cd.staction === 0 || this.cd.staction === 6) {
          if (checkPoints.stage !== -3) {
            let string = '';
            if (checkPoints.top20 >= 3) string = 'N#' + checkPoints.nto + '  ';
            if (this.aflk) {
              this.drawcs(132, string + checkPoints.name, 240, 240, 240, 3);
              this.aflk = false;
            } else {
              this.drawcs(132, string + checkPoints.name, 176, 176, 176, 3);
              this.aflk = true;
            }
          } else if (this.removeds !== 1) {
            this.drawcs(132, 'Failed to load stage...', 255, 138, 0, 3);
          }
        }
        if (checkPoints.stage !== -3 && this.cd.staction === 0 && checkPoints.top20 < 3) {
          this.rd.drawImage(this.contin[this.pcontin], 355, 360);
        } else {
          this.pcontin = 0;
        }
      } else {
        this.rd.setFont('SansSerif', 1, 13);
        this.ftm = this.rd.getFontMetrics();
        if (checkPoints.stage !== 27) {
          let stage = checkPoints.stage;
          if (stage > 10) stage -= 10;
          this.drawcs(80, 'Stage ' + stage + '  >', 255, 128, 0, 3);
        } else {
          this.drawcs(80, 'Final Party Stage  >', 255, 128, 0, 3);
        }
        if (this.aflk) {
          this.drawcs(100, '| ' + checkPoints.name + ' |', 240, 240, 240, 3);
          this.aflk = false;
        } else {
          this.drawcs(100, '| ' + checkPoints.name + ' |', 176, 176, 176, 3);
          this.aflk = true;
        }
        if (checkPoints.stage !== -3) this.rd.drawImage(this.contin[this.pcontin], 355, 360);
        else this.pcontin = 0;
      }
      if (this.cd.staction === 0) {
        if ((control.handb || control.enter) && checkPoints.stage !== -3 && checkPoints.top20 < 3) {
          this.dudo = 150;
          this.fase = 5;
          control.handb = false;
          control.enter = false;
        }
        if (checkPoints.stage > 0 && checkPoints.stage <= 27) {
          if (control.right) {
            if (this.gmode === 0 || (this.gmode === 1 && checkPoints.stage !== this.unlocked[0])
                || (this.gmode === 2 && checkPoints.stage !== this.unlocked[1] + 10) || checkPoints.stage === 27) {
              if (checkPoints.stage !== 27) {
                ++checkPoints.stage;
                if (this.gmode === 1 && checkPoints.stage === 11) checkPoints.stage = 27;
                this.nfmtab = checkPoints.stage > 10 ? 1 : 0;
                this.fase = 2;
              }
            } else {
              this.fase = 4;
              this.lockcnt = 100;
            }
            control.right = false;
          }
          if (control.left && checkPoints.stage !== 1 && (checkPoints.stage !== 11 || this.gmode !== 2)) {
            --checkPoints.stage;
            if (this.gmode === 1 && checkPoints.stage === 26) checkPoints.stage = 10;
            this.nfmtab = checkPoints.stage > 10 ? 1 : 0;
            this.fase = 2;
            control.left = false;
          }
        }
      }
    } else {
      if (this.aflk) {
        this.drawcs(132, checkPoints.name, 240, 240, 240, 3);
        this.aflk = false;
      } else {
        this.drawcs(132, checkPoints.name, 176, 176, 176, 3);
        this.aflk = true;
      }
      this.rd.drawImage(this.contin[this.pcontin], 355, 360);
      if (control.handb || control.enter) {
        this.dudo = 150;
        this.fase = 5;
        control.handb = false;
        control.enter = false;
      }
    }
    // TODO not ported: the " Exit X " button (drawcarb, mouse); Esc backs out.
  }
  stoploading() {}

  /**
   * The per-tick sound pump. `xtGraphics.java:9081`, called once per tick from
   * `GameSparker.java:1705`.
   *
   * Nothing else decrements the `bfXXX` debounce counters, so without this the
   * first crash sets `bfcrash = 2` and every later crash is suppressed for the
   * rest of the session -- you hear exactly one crash, one skid and one
   * scrape per race. It also drives the engine, which is not a series of
   * one-shots but five continuously looping samples with sparkeng() choosing
   * which one is live.
   */
  /** Per-tick sound pump. On the draw streams for the same reason stat() is. */
  playsounds(mad, control, n) {
    setDrawPhase(true);
    try {
      return this.#playsounds(mad, control, n);
    } finally {
      setDrawPhase(false);
    }
  }

  #playsounds(mad, control, n) {
    if ((this.fase === 0 || this.fase === 7001) && this.starcnt < 35 && this.cntwis !== 8 && !this.mutes) {
      let b = (control.up && mad.speed > 0.0) || (control.down && mad.speed < 10.0);
      let b2 = (mad.skid === 1 && control.handb)
        || Math.abs(fr(mad.scz[0] - fr(fr(fr(mad.scz[1] + mad.scz[0]) + mad.scz[2]) + mad.scz[3]) / 4.0)) > 1.0
        || Math.abs(fr(mad.scx[0] - fr(fr(fr(mad.scx[1] + mad.scx[0]) + mad.scx[2]) + mad.scx[3]) / 4.0)) > 1.0;
      let b3 = false;
      if (control.up && mad.speed < 10.0) {
        b2 = true;
        b = true;
        b3 = true;
      }
      if (b && mad.mtouch) {
        if (!mad.capsized) {
          if (!b2) {
            if (mad.power !== 98.0) {
              // Three rev bands, split at the car's own gear-change points.
              // Within a band the sample index is the fraction of the way
              // through it; `pwait` holds the top slot for a few ticks so the
              // engine does not chatter between two samples at a threshold.
              if (Math.abs(mad.speed) > 0.0 && Math.abs(mad.speed) <= this.cd.swits[mad.cn][0]) {
                let n2 = trunc(fr(fr(3.0 * Math.abs(mad.speed)) / this.cd.swits[mad.cn][0]));
                if (n2 === 2) {
                  if (this.pwait === 0) {
                    n2 = 0;
                  } else {
                    --this.pwait;
                  }
                } else {
                  this.pwait = 7;
                }
                this.sparkeng(n2, mad.cn);
              }
              if (Math.abs(mad.speed) > this.cd.swits[mad.cn][0] && Math.abs(mad.speed) <= this.cd.swits[mad.cn][1]) {
                let n3 = trunc(fr(fr(3.0 * fr(Math.abs(mad.speed) - this.cd.swits[mad.cn][0]))
                  / (this.cd.swits[mad.cn][1] - this.cd.swits[mad.cn][0])));
                if (n3 === 2) {
                  if (this.pwait === 0) {
                    n3 = 0;
                  } else {
                    --this.pwait;
                  }
                } else {
                  this.pwait = 7;
                }
                this.sparkeng(n3, mad.cn);
              }
              if (Math.abs(mad.speed) > this.cd.swits[mad.cn][1] && Math.abs(mad.speed) <= this.cd.swits[mad.cn][2]) {
                this.sparkeng(trunc(fr(fr(3.0 * fr(Math.abs(mad.speed) - this.cd.swits[mad.cn][1]))
                  / (this.cd.swits[mad.cn][2] - this.cd.swits[mad.cn][1]))), mad.cn);
              }
            } else {
              let n4 = 2;
              if (this.pwait === 0) {
                if (Math.abs(mad.speed) > this.cd.swits[mad.cn][1]) {
                  n4 = 3;
                }
              } else {
                --this.pwait;
              }
              this.sparkeng(n4, mad.cn);
            }
          } else {
            // Wheelspin: the engine drops out and a tyre-squeal air sample
            // takes over.
            this.sparkeng(-1, mad.cn);
            if (b3) {
              if (this.stopcnt <= 0) {
                this.air[5].loop();
                this.stopcnt = 10;
              }
            } else if (this.stopcnt <= -2) {
              this.air[2 + trunc(fr(this.m.random() * 3.0))].loop();
              this.stopcnt = 7;
            }
          }
        } else {
          this.sparkeng(3, mad.cn);
        }
        this.grrd = false;
        this.aird = false;
      } else {
        this.pwait = 15;
        if (!mad.mtouch && !this.grrd && this.m.random() > 0.4) {
          this.air[trunc(fr(this.m.random() * 4.0))].loop();
          this.stopcnt = 5;
          this.grrd = true;
        }
        if (!mad.wtouch && !this.aird) {
          this.stopairs();
          this.air[trunc(fr(this.m.random() * 4.0))].loop();
          this.stopcnt = 10;
          this.aird = true;
        }
        this.sparkeng(-1, mad.cn);
      }
      if (mad.cntdest !== 0 && this.cntwis < 7) {
        if (!this.pwastd) {
          this.wastd.loop();
          this.pwastd = true;
        }
      } else {
        if (this.pwastd) {
          this.wastd.stop();
          this.pwastd = false;
        }
        if (this.cntwis === 7 && !this.mutes) {
          this.firewasted.play();
        }
      }
    } else {
      this.sparkeng(-2, mad.cn);
      if (this.pwastd) {
        this.wastd.stop();
        this.pwastd = false;
      }
    }
    if (this.stopcnt !== -20) {
      if (this.stopcnt === 1) {
        this.stopairs();
      }
      --this.stopcnt;
    }
    // THE debounce decrements. crash()/skid()/scrape()/gscrape() set these and
    // nothing else clears them.
    if (this.bfcrash !== 0) {
      --this.bfcrash;
    }
    if (this.bfscrape !== 0) {
      --this.bfscrape;
    }
    if (this.bfsc1 !== 0) {
      --this.bfsc1;
    }
    if (this.bfsc2 !== 0) {
      --this.bfsc2;
    }
    if (this.bfskid !== 0) {
      --this.bfskid;
    }
    if (mad.newcar) {
      this.cntwis = 0;
    }
    if (this.fase === 0 || this.fase === 7001 || this.fase === 6 || this.fase === -1
        || this.fase === -2 || this.fase === -3 || this.fase === -4 || this.fase === -5) {
      if (this.mutes !== control.mutes) {
        this.mutes = control.mutes;
      }
      if (control.mutem !== this.mutem) {
        this.mutem = control.mutem;
        if (this.mutem) {
          if (this.loadedt) {
            this.strack.stop();
          }
        } else if (this.loadedt) {
          this.strack.resume();
        }
      }
    }
    if (mad.cntdest !== 0 && this.cntwis < 7) {
      if (mad.dest) {
        ++this.cntwis;
      }
    } else {
      if (mad.cntdest === 0) {
        this.cntwis = 0;
      }
      if (this.cntwis === 7) {
        this.cntwis = 8;
      }
    }
    // TODO not ported: `if (this.app.applejava) this.closesounds();` -- an
    // Apple-JVM workaround that closed and reopened clips every tick.
  }

  /**
   * Advance the missed-checkpoint countdown, once per tick per car.
   *
   * The Java does this inside stat(), while formatting the "Checkpoint
   * Missed!" banner -- but Mad.drive READS the counter back (`=== -2`,
   * `!== 0` at Mad.js:1580-1583), so it is simulation state wearing a HUD's
   * clothes. stat() only ever runs on the local player's car, so leaving the
   * increment there advanced a different car on each netplay client and the
   * two simulations parted company. Called from GameSparker.simulate for
   * every slot; an AI car's counter is pinned at 0 by drive(), so the `> 0`
   * gate makes it a no-op for them and single player is unchanged.
   */
  tickMissedCp(mad, checkPoints) {
    if (this.starcnt !== 0 || this.multion >= 2 || checkPoints.stage === 10) return;
    if (this.arrace) return;
    // The Java also gates on auscnt, exitm and holdit. Those are PER-CLIENT
    // HUD state -- auscnt is the announcer's timer, driven by the local
    // player's own stunts -- so gating a simulation counter on them advances
    // it on different ticks on each machine. Only mad.capcnt survives, being
    // the car's own state. The visible cost in single player is that the
    // "Checkpoint Missed!" banner no longer pauses while the announcer talks.
    if (mad.capcnt !== 0) return;
    if (mad.missedcp > 0) {
      ++mad.missedcp;
      if (mad.missedcp === 70) {
        mad.missedcp = -2;
      }
    }
  }

  /** `xtGraphics.java:9258`. Cut every air loop. */
  stopairs() {
    for (let i = 0; i < 6; ++i) {
      this.air[i].stop();
    }
  }

  /**
   * `xtGraphics.java:9264`. Hold exactly one of the five engine samples
   * looping: `n` selects it (-1 for silence, so the loop below compares
   * against `n + 1`), `lcn` is the car, whose `enginsignature` picks the bank.
   * Changing car stops the old bank's clips first, or they loop forever.
   */
  sparkeng(n, lcn) {
    if (this.lcn !== lcn) {
      for (let i = 0; i < 5; ++i) {
        if (this.pengs[i]) {
          this.engs[this.cd.enginsignature[this.lcn]][i].stop();
          this.pengs[i] = false;
        }
      }
      this.lcn = lcn;
    }
    ++n;
    for (let j = 0; j < 5; ++j) {
      if (n === j) {
        if (!this.pengs[j]) {
          this.engs[this.cd.enginsignature[lcn]][j].loop();
          this.pengs[j] = true;
        }
      } else if (this.pengs[j]) {
        this.engs[this.cd.enginsignature[lcn]][j].stop();
        this.pengs[j] = false;
      }
    }
  }

  stopchat() {
    // TODO not ported: chat network socket shutdown
  }

  /**
   * The HUD, the end-of-race overlays and the announcer chatter.
   *
   * Runs on the DRAW streams even though it is called from simulate(). It is
   * presentation: it picks a sound variant and an adjective out of random(),
   * and it is driven by the LOCAL player's Mad, so two netplay clients take
   * different branches through it and consume different numbers of randoms.
   * On the sim stream that silently desynchronises every AI car -- which is
   * exactly how it was found. Nothing here feeds physics except the race-end
   * flags, which are game state and not random.
   */
  stat(mad, contO, checkPoints, control, b) {
    setDrawPhase(true);
    try {
      return this.#stat(mad, contO, checkPoints, control, b);
    } finally {
      setDrawPhase(false);
    }
  }

  #stat(mad, contO, checkPoints, control, b) {
    if (this.holdit) {
      let n = 250;
      if (this.fase === 7001) {
        if (this.exitm !== 4) {
          this.exitm = 0;
          n = 600;
        } else {
          n = 1200;
        }
      }
      if (this.exitm !== 4 || !this.lan || this.im !== 0) {
        ++this.holdcnt;
        if ((control.enter || this.holdcnt > n) && (control.chatup === 0 || this.fase !== 7001)) {
          this.fase = -2;
          control.enter = false;
        }
      } else if (control.enter) {
        control.enter = false;
      }
    } else {
      if (this.holdcnt !== 0) {
        this.holdcnt = 0;
      }
      if (control.enter || control.exit) {
        if (this.fase === 0) {
          // Enter in a race is the Java's PAUSE (fase -6 -> pausedgame): stop the
          // stage's music and nothing else. main.js answers fase -6 with the
          // pause menu; Resume brings this same track back. Loading the menu
          // track here, as the port once did, swapped the race's music for
          // the launcher's and left the race frozen.
          if (this.loadedt && this.strack && this.strack.stop) {
            this.strack.stop();
          }
          this.fase = -6;
        } else if (this.starcnt === 0 && control.chatup === 0 && (this.multion < 2 || !this.lan)) {
          if (this.exitm === 0) {
            this.exitm = 1;
          } else {
            this.exitm = 0;
          }
        }
        if (control.chatup === 0 || this.fase !== 7001) {
          control.enter = false;
        }
        control.exit = false;
      }
    }
    if (this.exitm === 2) {
      this.fase = -2;
      this.winner = false;
    }
    if (this.fase !== -2) {
      this.holdit = false;
      if (checkPoints.haltall) {
        checkPoints.haltall = false;
      }
      let b2 = false;
      let str = "";
      let str2 = "";
      if (this.clangame !== 0 && (!mad.dest || this.multion >= 2)) {
        // TODO not ported: clan game player checking
        b2 = true;
        for (let i = 0; i < this.nplayers; ++i) {
          if (checkPoints.dested[i] === 0) {
            if (str === "") {
              str = this.pclan[i];
            } else if (str.toLowerCase() !== this.pclan[i].toLowerCase()) {
              b2 = false;
              break;
            }
          }
        }
      }
      if (this.clangame > 1) {
        // TODO not ported: clan game win/lose condition text and overlay
        let b3 = false;
        let s = "";
        if (b2) {
          for (let j = 0; j < this.nplayers; ++j) {
            if (str.toLowerCase() !== this.pclan[j].toLowerCase()) {
              str2 = this.pclan[j];
              break;
            }
          }
          if (this.clangame === 2) {
            b3 = true;
            s = "Clan " + str2 + " wasted, nobody won becuase this is a racing only game!";
          }
          if (this.clangame === 4 && str.toLowerCase() !== this.gaclan.toLowerCase()) {
            b3 = true;
            s = "Clan " + str2 + " wasted, nobody won becuase " + str + " should have raced in this racing vs wasting game!";
          }
          if (this.clangame === 5 && str.toLowerCase() === this.gaclan.toLowerCase()) {
            b3 = true;
            s = "Clan " + str2 + " wasted, nobody won becuase " + str + " should have raced in this racing vs wasting game!";
          }
        }
        for (let k = 0; k < this.nplayers; ++k) {
          if (checkPoints.clear[k] === checkPoints.nlaps * checkPoints.nsp && checkPoints.pos[k] === 0) {
            if (this.clangame === 3) {
              b3 = true;
              s = "" + this.plnames[k] + " of clan " + this.pclan[k] + " finished first, nobody won becuase this is a wasting only game!";
            }
            if (this.clangame === 4 && this.pclan[k].toLowerCase() === this.gaclan.toLowerCase()) {
              b3 = true;
              s = "" + this.plnames[k] + " of clan " + this.pclan[k] + " finished first, nobody won becuase " + this.pclan[k] + " should have wasted in this racing vs wasting game!";
            }
            if (this.clangame === 5 && this.pclan[k].toLowerCase() !== this.gaclan.toLowerCase()) {
              b3 = true;
              s = "" + this.plnames[k] + " of clan " + this.pclan[k] + " finished first, nobody won becuase " + this.pclan[k] + " should have wasted in this racing vs wasting game!";
            }
          }
        }
        if (b3) {
          if (this.gamefinished) {
            this.drawhi(this.gamefinished, 70);
          } else {
            // TODO not ported: gamefinished image asset not loaded
          }
          if (this.aflk) {
            this.drawcs(120, s, 0, 0, 0, 0);
            this.aflk = false;
          } else {
            this.drawcs(120, s, 0, 128, 255, 0);
            this.aflk = true;
          }
          this.drawcs(350, "Press  [ Enter ]  to continue", 0, 0, 0, 0);
          checkPoints.haltall = true;
          this.holdit = true;
          this.winner = false;
        }
      }
      if (this.multion < 2) {
        if (!this.holdit && ((checkPoints.wasted === this.nplayers - 1 && this.nplayers !== 1) || b2)) {
          if (this.youwastedem) {
            this.drawhi(this.youwastedem, 70);
          } else {
            // TODO not ported: youwastedem image asset not loaded
          }
          if (!b2) {
            if (this.aflk) {
              this.drawcs(120, "You Won, all cars have been wasted!", 0, 0, 0, 0);
              this.aflk = false;
            } else {
              this.drawcs(120, "You Won, all cars have been wasted!", 0, 128, 255, 0);
              this.aflk = true;
            }
          } else if (this.aflk) {
            this.drawcs(120, "Your clan " + str + " has wasted all the cars!", 0, 0, 0, 0);
            this.aflk = false;
          } else {
            this.drawcs(120, "Your clan " + str + " has wasted all the cars!", 0, 128, 255, 0);
            this.aflk = true;
          }
          this.drawcs(350, "Press  [ Enter ]  to continue", 0, 0, 0, 0);
          checkPoints.haltall = true;
          this.holdit = true;
          this.winner = true;
        }
        if (!this.holdit && mad.dest && this.cntwis === 8) {
          if (this.discon !== 240) {
            if (this.yourwasted) {
              this.drawhi(this.yourwasted, 70);
            } else {
              // TODO not ported: yourwasted image asset not loaded
            }
          } else {
            if (this.disco) {
              this.drawhi(this.disco, 70);
            } else {
              // TODO not ported: disco image asset not loaded
            }
            this.stopchat();
          }
          let b4 = false;
          if (this.lan) {
            // TODO not ported: LAN multiplayer bot checking
            b4 = true;
            for (let l = 0; l < this.nplayers; ++l) {
              if (l !== this.im && this.dested[l] === 0 && this.plnames[l].indexOf("MadBot") === -1) {
                b4 = false;
              }
            }
          }
          if (this.fase === 7001 && this.nplayers - (checkPoints.wasted + 1) >= 2 && this.discon !== 240 && !b4) {
            // TODO not ported: multiplayer server exit state 4
            this.exitm = 4;
          } else {
            if (this.exitm === 4) {
              this.exitm = 0;
            }
            this.drawcs(350, "Press  [ Enter ]  to continue", 0, 0, 0, 0);
          }
          this.holdit = true;
          this.winner = false;
        }
        if (!this.holdit) {
          for (let n2 = 0; n2 < this.nplayers; ++n2) {
            if (checkPoints.clear[n2] === checkPoints.nlaps * checkPoints.nsp && checkPoints.pos[n2] === 0) {
              if (this.clangame === 0) {
                if (n2 === this.im) {
                  if (this.youwon) {
                    this.drawhi(this.youwon, 70);
                  } else {
                    // TODO not ported: youwon image asset not loaded
                  }
                  if (this.aflk) {
                    this.drawcs(120, "You finished first, nice job!", 0, 0, 0, 0);
                    this.aflk = false;
                  } else {
                    this.drawcs(120, "You finished first, nice job!", 0, 128, 255, 0);
                    this.aflk = true;
                  }
                  this.winner = true;
                } else {
                  if (this.youlost) {
                    this.drawhi(this.youlost, 70);
                  } else {
                    // TODO not ported: youlost image asset not loaded
                  }
                  if (this.fase !== 7001) {
                    if (this.aflk) {
                      this.drawcs(120, "" + (this.cd && this.cd.names ? this.cd.names[this.sc[n2]] : "") + " finished first, race over!", 0, 0, 0, 0);
                      this.aflk = false;
                    } else {
                      this.drawcs(120, "" + (this.cd && this.cd.names ? this.cd.names[this.sc[n2]] : "") + " finished first, race over!", 0, 128, 255, 0);
                      this.aflk = true;
                    }
                  } else if (this.aflk) {
                    this.drawcs(120, "" + this.plnames[n2] + " finished first, race over!", 0, 0, 0, 0);
                    this.aflk = false;
                  } else {
                    this.drawcs(120, "" + this.plnames[n2] + " finished first, race over!", 0, 128, 255, 0);
                    this.aflk = true;
                  }
                  this.winner = false;
                }
              } else if (this.pclan[n2].toLowerCase() === this.pclan[this.im].toLowerCase()) {
                if (this.youwon) {
                  this.drawhi(this.youwon, 70);
                } else {
                  // TODO not ported: youwon image asset not loaded
                }
                if (this.aflk) {
                  this.drawcs(120, "Your clan " + this.pclan[this.im] + " finished first, nice job!", 0, 0, 0, 0);
                  this.aflk = false;
                } else {
                  this.drawcs(120, "Your clan " + this.pclan[this.im] + " finished first, nice job!", 0, 128, 255, 0);
                  this.aflk = true;
                }
                this.winner = true;
              } else {
                if (this.youlost) {
                  this.drawhi(this.youlost, 70);
                } else {
                  // TODO not ported: youlost image asset not loaded
                }
                if (this.aflk) {
                  this.drawcs(120, "" + this.plnames[n2] + " of clan " + this.pclan[n2] + " finished first, race over!", 0, 0, 0, 0);
                  this.aflk = false;
                } else {
                  this.drawcs(120, "" + this.plnames[n2] + " of clan " + this.pclan[n2] + " finished first, race over!", 0, 128, 255, 0);
                  this.aflk = true;
                }
                this.winner = false;
              }
              this.drawcs(350, "Press  [ Enter ]  to continue", 0, 0, 0, 0);
              checkPoints.haltall = true;
              this.holdit = true;
            }
          }
        }
      } else {
        // TODO not ported: multiplayer / spectator mode overlay
        if (!this.holdit && (checkPoints.wasted >= this.nplayers - 1 || b2)) {
          let string = "Someone";
          if (!b2) {
            for (let n3 = 0; n3 < this.nplayers; ++n3) {
              if (checkPoints.dested[n3] === 0) {
                string = this.plnames[n3];
              }
            }
          } else {
            string = "Clan " + str + "";
          }
          if (this.gamefinished) {
            this.drawhi(this.gamefinished, 70);
          } else {
            // TODO not ported: gamefinished image asset not loaded
          }
          if (this.aflk) {
            this.drawcs(120, "" + string + " has wasted all the cars!", 0, 0, 0, 0);
            this.aflk = false;
          } else {
            this.drawcs(120, "" + string + " has wasted all the cars!", 0, 128, 255, 0);
            this.aflk = true;
          }
          this.drawcs(350, "Press  [ Enter ]  to continue", 0, 0, 0, 0);
          checkPoints.haltall = true;
          this.holdit = true;
          this.winner = false;
        }
        if (!this.holdit) {
          for (let n4 = 0; n4 < this.nplayers; ++n4) {
            if (checkPoints.clear[n4] === checkPoints.nlaps * checkPoints.nsp && checkPoints.pos[n4] === 0) {
              if (this.gamefinished) {
                this.drawhi(this.gamefinished, 70);
              } else {
                // TODO not ported: gamefinished image asset not loaded
              }
              if (this.clangame === 0) {
                if (this.aflk) {
                  this.drawcs(120, "" + this.plnames[n4] + " finished first, race over!", 0, 0, 0, 0);
                  this.aflk = false;
                } else {
                  this.drawcs(120, "" + this.plnames[n4] + " finished first, race over!", 0, 128, 255, 0);
                  this.aflk = true;
                }
              } else if (this.aflk) {
                this.drawcs(120, "Clan " + this.pclan[n4] + " finished first, race over!", 0, 0, 0, 0);
                this.aflk = false;
              } else {
                this.drawcs(120, "Clan " + this.pclan[n4] + " finished first, race over!", 0, 128, 255, 0);
                this.aflk = true;
              }
              this.drawcs(350, "Press  [ Enter ]  to continue", 0, 0, 0, 0);
              checkPoints.haltall = true;
              this.holdit = true;
              this.winner = false;
            }
          }
        }
        if (!this.holdit && this.discon === 240) {
          if (this.gamefinished) {
            this.drawhi(this.gamefinished, 70);
          } else {
            // TODO not ported: gamefinished image asset not loaded
          }
          if (this.aflk) {
            this.drawcs(120, "Game got disconnected!", 0, 0, 0, 0);
            this.aflk = false;
          } else {
            this.drawcs(120, "Game got disconnected!", 0, 128, 255, 0);
            this.aflk = true;
          }
          this.drawcs(350, "Press  [ Enter ]  to continue", 0, 0, 0, 0);
          checkPoints.haltall = true;
          this.holdit = true;
          this.winner = false;
        }
        if (!this.holdit) {
          if (this.rd && this.wgame) {
            this.rd.drawImage(this.wgame, 311, 20);
          } else {
            // TODO not ported: wgame image asset not loaded
          }
          if (!this.clanchat) {
            this.drawcs(397, "Click any player on the right to follow!", 0, 0, 0, 0);
            if (!this.lan) {
              this.drawcs(412, "Press [V] to change view.  Press [Enter] to exit.", 0, 0, 0, 0);
            } else {
              this.drawcs(412, "Press [V] to change view.", 0, 0, 0, 0);
            }
          }
        }
      }
      if (b) {
        if (checkPoints.stage !== 10 && this.multion < 2 && this.nplayers !== 1 && this.arrace !== control.arrace) {
          this.arrace = control.arrace;
          if (this.multion === 1 && this.arrace) {
            control.radar = true;
          }
          if (this.arrace) {
            this.wasay = true;
            this.say = " Arrow now pointing at >  CARS";
            if (this.multion === 1) {
              this.say += "    Press [S] to toggle Radar!";
            }
            this.tcnt = -5;
          }
          if (!this.arrace) {
            this.wasay = false;
            this.say = " Arrow now pointing at >  TRACK";
            if (this.multion === 1) {
              this.say += "    Press [S] to toggle Radar!";
            }
            this.tcnt = -5;
            this.cntan = 20;
            this.alocked = -1;
            this.alocked = -1;
          }
        }
        if (!this.holdit && this.fase !== -6 && this.starcnt === 0 && this.multion < 2 && checkPoints.stage !== 10) {
          this.arrow(mad.point, mad.missedcp, checkPoints, this.arrace);
          if (!this.arrace) {
            if (this.auscnt === 45 && mad.capcnt === 0 && this.exitm === 0) {
              if (mad.missedcp > 0) {
                if (mad.missedcp > 15 && mad.missedcp < 50) {
                  if (this.flk) {
                    this.drawcs(70, "Checkpoint Missed!", 255, 0, 0, 0);
                  } else {
                    this.drawcs(70, "Checkpoint Missed!", 255, 150, 0, 2);
                  }
                }
                // The increment that used to live here now runs once per
                // tick for every car, in tickMissedCp() -- see there.
              } else if (mad.mtouch && this.cntovn < 70) {
                if (Math.abs(this.ana) > 100) {
                  ++this.cntan;
                } else if (this.cntan !== 0) {
                  --this.cntan;
                }
                if (this.cntan > 40) {
                  ++this.cntovn;
                  this.cntan = 40;
                  if (this.flk) {
                    this.drawcs(70, "Wrong Way!", 255, 150, 0, 0);
                    this.flk = false;
                  } else {
                    this.drawcs(70, "Wrong Way!", 255, 0, 0, 2);
                    this.flk = true;
                  }
                }
              }
            }
          } else if (this.alocked !== this.lalocked) {
            if (this.alocked !== -1) {
              this.wasay = true;
              this.say = " Arrow Locked on >  " + this.plnames[this.alocked] + "";
              this.tcnt = -5;
            } else {
              this.wasay = true;
              this.say = "Arrow Unlocked!";
              this.tcnt = 10;
            }
            this.lalocked = this.alocked;
          }
        }
        if (this.m && this.m.darksky && !this.hudOutline) {
          if (this.rd) {
            const hsbvals = floatArray(3);
            RGBtoHSB(this.m.csky[0], this.m.csky[1], this.m.csky[2], hsbvals);
            hsbvals[2] = 0.6;
            const rgb = HSBtoRGB(hsbvals[0], hsbvals[1], hsbvals[2]);
            const rSky = (rgb >> 16) & 0xff;
            const gSky = (rgb >> 8) & 0xff;
            const bSky = rgb & 0xff;
            this.rd.setColor(rSky, gSky, bSky);
            this.rd.fillRect(602, 9, 54, 14);
            this.rd.drawLine(601, 10, 601, 21);
            this.rd.drawLine(600, 12, 600, 19);
            this.rd.fillRect(607, 29, 49, 14);
            this.rd.drawLine(606, 30, 606, 41);
            this.rd.drawLine(605, 32, 605, 39);
            this.rd.fillRect(18, 6, lang === 'es' ? 232 : 155, 14);
            this.rd.drawLine(17, 7, 17, 18);
            this.rd.drawLine(16, 9, 16, 16);
            this.rd.drawLine(lang === 'es' ? 250 : 173, 7, lang === 'es' ? 250 : 173, 18);
            this.rd.drawLine(lang === 'es' ? 251 : 174, 9, lang === 'es' ? 251 : 174, 16);
            this.rd.fillRect(40, 26, 107, 21);
            this.rd.drawLine(39, 27, 39, 45);
            this.rd.drawLine(38, 29, 38, 43);
            this.rd.drawLine(147, 27, 147, 45);
            this.rd.drawLine(148, 29, 148, 43);
          }
        }
        if (this.rd) {
          if (this.dmg) this.rd.drawImage(this.dmg, 600, 7); else { /* TODO not ported: dmg image asset not loaded */ }
          if (this.pwr) this.rd.drawImage(this.pwr, 600, 27); else { /* TODO not ported: pwr image asset not loaded */ }
          if (this.lap) this.rd.drawImage(this.lap, 19, 7); else { /* TODO not ported: lap image asset not loaded */ }
          this.hudText();
          this.rd.drawString("" + (mad.nlaps + 1) + " / " + checkPoints.nlaps + "", lang === 'es' ? 93 : 51, 18);
          if (this.was) this.rd.drawImage(this.was, lang === 'es' ? 145 : 92, 7); else { /* TODO not ported: was image asset not loaded */ }
          this.hudText();
          this.rd.drawString("" + checkPoints.wasted + " / " + (this.nplayers - 1) + "", lang === 'es' ? 202 : 150, 18);
          if (this.pos) this.rd.drawImage(this.pos, 42, 27); else { /* TODO not ported: pos image asset not loaded */ }
          const rankImg = this.rank[checkPoints.pos[mad.im]];
          if (rankImg) this.rd.drawImage(rankImg, 110, 28); else { /* TODO not ported: rank image asset not loaded */ }
        }
        this.drawstat(this.cd ? this.cd.maxmag[mad.cn] : 100, mad.hitmag, mad.newcar, mad.power);
        if (control.radar && checkPoints.stage !== 10) {
          this.radarstat(mad, contO, checkPoints);
        }
      }
      if (!this.holdit) {
        if (this.starcnt !== 0 && this.starcnt <= 35) {
          if (this.starcnt === 35 && !this.mutes && this.three && this.three.play) {
            this.three.play();
          }
          if (this.starcnt === 24) {
            this.gocnt = 2;
            if (!this.mutes && this.two && this.two.play) {
              this.two.play();
            }
          }
          if (this.starcnt === 13) {
            this.gocnt = 1;
            if (!this.mutes && this.one && this.one.play) {
              this.one.play();
            }
          }
          if (this.starcnt === 2) {
            this.gocnt = 0;
            if (!this.mutes && this.go && this.go.play) {
              this.go.play();
            }
          }
          this.duds = 0;
          if (this.starcnt <= 37 && this.starcnt > 32) {
            this.duds = 1;
          }
          if (this.starcnt <= 26 && this.starcnt > 21) {
            this.duds = 1;
          }
          if (this.starcnt <= 15 && this.starcnt > 10) {
            this.duds = 1;
          }
          if (this.starcnt <= 4) {
            this.duds = 2;
          }
          if (this.dudo !== -1) {
            if (this.rd) this.rd.setComposite(0.3);
            const dudeImg = this.dude[this.duds];
            if (this.rd && dudeImg) {
              this.rd.drawImage(dudeImg, this.dudo, 0);
            } else {
              // TODO not ported: dude image asset not loaded
            }
            if (this.rd) this.rd.setComposite(1.0);
          }
          const cntdnImg = this.cntdn[this.gocnt];
          if (this.rd && cntdnImg) {
            if (this.gocnt !== 0) {
              this.rd.drawImage(cntdnImg, 385, 50);
            } else {
              this.rd.drawImage(cntdnImg, 363, 50);
            }
          } else {
            // TODO not ported: cntdn image asset not loaded
          }
        }
        if (this.looped !== 0 && mad.loop === 2) {
          this.looped = 0;
        }
        if (mad.power < 45.0) {
          if (this.tcnt === 30 && this.auscnt === 45 && mad.mtouch && mad.capcnt === 0 && this.exitm === 0) {
            if (this.looped !== 2) {
              if (this.pwcnt < 70 || (this.pwcnt < 100 && this.looped !== 0)) {
                if (this.pwflk) {
                  this.drawcs(110, "Power low, perform stunt!", 0, 0, 200, 0);
                  this.pwflk = false;
                } else {
                  this.drawcs(110, "Power low, perform stunt!", 255, 100, 0, 0);
                  this.pwflk = true;
                }
              }
            } else if (this.pwcnt < 100) {
              let s2 = "";
              if (this.multion === 0) {
                s2 = "  (Press Enter)";
              }
              if (this.pwflk) {
                this.drawcs(110, "Please read the Game Instructions!" + s2 + "", 0, 0, 200, 0);
                this.pwflk = false;
              } else {
                this.drawcs(110, "Please read the Game Instructions!" + s2 + "", 255, 100, 0, 0);
                this.pwflk = true;
              }
            }
            ++this.pwcnt;
            if (this.pwcnt === 300) {
              this.pwcnt = 0;
              if (this.looped !== 0) {
                ++this.looped;
                if (this.looped === 4) {
                  this.looped = 2;
                }
              }
            }
          }
        } else if (this.pwcnt !== 0) {
          this.pwcnt = 0;
        }
        if (mad.capcnt === 0) {
          if (this.tcnt < 30) {
            if (this.exitm === 0) {
              if (this.tflk) {
                if (!this.wasay) {
                  this.drawcs(105, this.say, 0, 0, 0, 0);
                } else {
                  this.drawcs(105, this.say, 0, 0, 0, 0);
                }
                this.tflk = false;
              } else {
                if (!this.wasay) {
                  this.drawcs(105, this.say, 0, 128, 255, 0);
                } else {
                  this.drawcs(105, this.say, 255, 128, 0, 0);
                }
                this.tflk = true;
              }
            }
            ++this.tcnt;
          } else if (this.wasay) {
            this.wasay = false;
          }
          if (this.auscnt < 45) {
            if (this.exitm === 0) {
              if (this.aflk) {
                this.drawcs(85, this.asay, 98, 176, 255, 0);
                this.aflk = false;
              } else {
                this.drawcs(85, this.asay, 0, 128, 255, 0);
                this.aflk = true;
              }
            }
            ++this.auscnt;
          }
        } else if (this.exitm === 0) {
          if (this.tflk) {
            this.drawcs(110, "Bad Landing!", 0, 0, 200, 0);
            this.tflk = false;
          } else {
            this.drawcs(110, "Bad Landing!", 255, 100, 0, 0);
            this.tflk = true;
          }
        }
        if (mad.trcnt === 10) {
          this.loop = "";
          this.spin = "";
          this.asay = "";
          let n5 = 0;
          while (mad.travzy > 225) {
            mad.travzy -= 360;
            ++n5;
          }
          while (mad.travzy < -225) {
            mad.travzy += 360;
            --n5;
          }
          if (n5 === 1) {
            this.loop = tr("Forward loop");
          }
          if (n5 === 2) {
            this.loop = tr("double Forward");
          }
          if (n5 === 3) {
            this.loop = tr("triple Forward");
          }
          if (n5 >= 4) {
            this.loop = tr("massive Forward looping");
          }
          if (n5 === -1) {
            this.loop = tr("Backloop");
          }
          if (n5 === -2) {
            this.loop = tr("double Back");
          }
          if (n5 === -3) {
            this.loop = tr("triple Back");
          }
          if (n5 <= -4) {
            this.loop = tr("massive Back looping");
          }
          if (n5 === 0) {
            if (mad.ftab && mad.btab) {
              this.loop = tr("Tabletop and reversed Tabletop");
            } else if (mad.ftab || mad.btab) {
              this.loop = tr("Tabletop");
            }
          }
          if (n5 > 0 && mad.btab) {
            this.loop = tr("Hanged ") + this.loop;
          }
          if (n5 < 0 && mad.ftab) {
            this.loop = tr("Hanged ") + this.loop;
          }
          if (this.loop !== "") {
            this.asay = this.asay + " " + this.loop;
          }
          let n6 = 0;
          // LOCAL COPIES. The Java consumes these counters destructively, out
          // of the live Mad -- and stat() only ever runs on the local player's
          // car, so in a netplay session each client mangles a DIFFERENT car's
          // stunt accumulator. travxy/travxz feed powerup, so the two
          // simulations diverge through the announcer's text formatting, which
          // is as indirect as a desync gets. Working on copies leaves the
          // announcement identical and the physics untouched.
          let travxy = Math.abs(mad.travxy);
          while (travxy > 270) {
            travxy -= 360;
            ++n6;
          }
          if (n6 === 0 && mad.rtab) {
            if (this.loop === "") {
              this.spin = tr("Tabletop");
            } else {
              this.spin = tr("Flipside");
            }
          }
          if (n6 === 1) {
            this.spin = tr("Rollspin");
          }
          if (n6 === 2) {
            this.spin = tr("double Rollspin");
          }
          if (n6 === 3) {
            this.spin = tr("triple Rollspin");
          }
          if (n6 >= 4) {
            this.spin = tr("massive Roll spinning");
          }
          let n7 = 0;
          let b5 = false;
          let travxz = Math.abs(mad.travxz);      // a copy; see travxy above
          while (travxz > 90) {
            travxz -= 180;
            n7 += 180;
            if (n7 > 900) {
              n7 = 900;
              b5 = true;
            }
          }
          if (n7 !== 0) {
            if (this.loop === "" && this.spin === "") {
              this.asay = this.asay + " " + n7;
              if (b5) {
                this.asay += " and beyond";
              }
            } else {
              if (this.spin !== "") {
                if (this.loop === "") {
                  this.asay = this.asay + " " + this.spin;
                } else {
                  this.asay = this.asay + tr(" with ") + this.spin;
                }
              }
              this.asay = this.asay + tr(" by ") + n7;
              if (b5) {
                this.asay += " and beyond";
              }
            }
          } else if (this.spin !== "") {
            if (this.loop === "") {
              this.asay = this.asay + " " + this.spin;
            } else {
              this.asay = this.asay + tr(" by ") + this.spin;
            }
          }
          if (this.asay !== "") {
            this.auscnt -= 15;
          }
          if (this.loop !== "") {
            this.auscnt -= 25;
          }
          if (this.spin !== "") {
            this.auscnt -= 25;
          }
          if (n7 !== 0) {
            this.auscnt -= 25;
          }
          if (this.auscnt < 45) {
            if (!this.mutes && this.powerup && this.powerup.play) {
              this.powerup.play();
            }
            if (this.auscnt < -20) {
              this.auscnt = -20;
            }
            let n8 = 0;
            if (mad.powerup > 20.0) {
              n8 = 1;
            }
            if (mad.powerup > 40.0) {
              n8 = 2;
            }
            if (mad.powerup > 150.0) {
              n8 = 3;
            }
            if (mad.surfer) {
              this.asay = " " + this.adj[4][trunc(fr(this.m.random() * 3.0))] + this.asay;
            }
            if (n8 !== 3) {
              this.asay = this.adj[n8][trunc(fr(this.m.random() * 3.0))] + this.asay + this.exlm[n8];
            } else {
              this.asay = this.adj[n8][trunc(fr(this.m.random() * 3.0))];
            }
            if (!this.wasay) {
              this.tcnt = this.auscnt;
              if (mad.power !== 98.0) {
                this.say = "Power Up " + trunc(fr(fr(100.0 * mad.powerup) / 98.0)) + "%";
              } else {
                this.say = "Power To The MAX";
              }
              if (this.skidup) {
                this.skidup = false;
              } else {
                this.skidup = true;
              }
            }
          }
        }
        if (mad.newcar) {
          if (!this.wasay) {
            this.say = "Car Fixed";
            this.tcnt = 0;
          }
          if (this.crashup) {
            this.crashup = false;
          } else {
            this.crashup = true;
          }
        }
        for (let n9 = 0; n9 < this.nplayers; ++n9) {
          if (this.dested[n9] !== checkPoints.dested[n9] && n9 !== this.im) {
            this.dested[n9] = checkPoints.dested[n9];
            if (this.fase !== 7001) {
              if (this.dested[n9] === 1) {
                this.wasay = true;
                this.say = "" + (this.cd && this.cd.names ? this.cd.names[this.sc[n9]] : "") + " has been wasted!";
                this.tcnt = -15;
              }
              if (this.dested[n9] === 2) {
                this.wasay = true;
                this.say = "You wasted " + (this.cd && this.cd.names ? this.cd.names[this.sc[n9]] : "") + "!";
                this.tcnt = -15;
              }
            } else {
              if (this.dested[n9] === 1) {
                this.wasay = true;
                this.say = "" + this.plnames[n9] + " has been wasted!";
                this.tcnt = -15;
              }
              if (this.dested[n9] === 2) {
                this.wasay = true;
                if (this.multion < 2) {
                  this.say = "You wasted " + this.plnames[n9] + "!";
                } else {
                  this.say = "" + this.plnames[this.im] + " wasted " + this.plnames[n9] + "!";
                }
                this.tcnt = -15;
              }
              if (this.dested[n9] === 3) {
                this.wasay = true;
                this.say = "" + this.plnames[n9] + " has been wasted! (Disconnected)";
                this.tcnt = -15;
              }
            }
          }
        }
        if (this.multion >= 2 && this.alocked !== this.lalocked) {
          if (this.alocked !== -1) {
            this.wasay = false;
            this.say = "Now following " + this.plnames[this.alocked] + "!";
            this.tcnt = -15;
          }
          this.lalocked = this.alocked;
          this.clear = mad.clear;
        }
        if (this.clear !== mad.clear && mad.clear !== 0) {
          if (!this.wasay) {
            this.say = "Checkpoint!";
            this.tcnt = 15;
          }
          this.clear = mad.clear;
          if (!this.mutes && this.checkpoint && this.checkpoint.play) {
            this.checkpoint.play();
          }
          this.cntovn = 0;
          if (this.cntan !== 0) {
            this.cntan = 0;
          }
        }
      }
    }
    if (this.m && this.m.lightn !== -1) {
      if (this.strack && this.strack.sClip && this.strack.sClip.stream && typeof this.strack.sClip.stream.available === 'function') {
        const available = this.strack.sClip.stream.available();
        this.m.lton = false;
        if (available <= 6380001 && available > 5368001) {
          this.m.lton = true;
        }
        if (available <= 2992001 && available > 1320001) {
          this.m.lton = true;
        }
      } else {
        // TODO not ported: strack soundClip stream available check for lightning flashes
      }
    }
  }

  drawstat(n, n2, b, n3) {
    if (!this.rd || !this.m) return;
    const array = intArray(4);
    const array2 = intArray(4);
    if (n2 > n) {
      n2 = n;
    }
    const n4 = trunc(fr(98.0 * fr(n2 / n)));
    array[0] = 662;
    array2[0] = 11;
    array[1] = 662;
    array2[1] = 20;
    array[2] = 662 + n4;
    array2[2] = 20;
    array[3] = 662 + n4;
    array2[3] = 11;
    const n5 = 244;
    let n6 = 244;
    const n7 = 11;
    if (n4 > 33) {
      n6 = trunc(fr(244.0 - fr(233.0 * fr((n4 - 33) / 65.0))));
    }
    if (n4 > 70) {
      if (this.dmcnt < 10) {
        if (this.dmflk) {
          n6 = 170;
          this.dmflk = false;
        } else {
          this.dmflk = true;
        }
      }
      ++this.dmcnt;
      if (this.dmcnt > 167.0 - n4 * 1.5) {
        this.dmcnt = 0;
      }
    }
    let r = trunc(fr(n5 + fr(n5 * fr(this.m.snap[0] / 100.0))));
    if (r > 255) r = 255;
    if (r < 0) r = 0;
    let g = trunc(fr(n6 + fr(n6 * fr(this.m.snap[1] / 100.0))));
    if (g > 255) g = 255;
    if (g < 0) g = 0;
    let b2 = trunc(fr(n7 + fr(n7 * fr(this.m.snap[2] / 100.0))));
    if (b2 > 255) b2 = 255;
    if (b2 < 0) b2 = 0;
    this.rd.setColor(r, g, b2);
    this.rd.fillPolygon(array, array2, 4);

    array[0] = 662;
    array2[0] = 31;
    array[1] = 662;
    array2[1] = 40;
    array[2] = trunc(fr(662.0 + n3));
    array2[2] = 40;
    array[3] = trunc(fr(662.0 + n3));
    array2[3] = 31;
    let n8 = 128;
    if (n3 === 98.0) {
      n8 = 64;
    }
    let n9 = trunc(190.0 + n3 * 0.37);
    let n10 = 244;
    if (this.auscnt < 45 && this.aflk) {
      n8 = 128;
      n9 = 244;
      n10 = 244;
    }
    let r2 = trunc(fr(n8 + fr(n8 * fr(this.m.snap[0] / 100.0))));
    if (r2 > 255) r2 = 255;
    if (r2 < 0) r2 = 0;
    let g2 = trunc(fr(n9 + fr(n9 * fr(this.m.snap[1] / 100.0))));
    if (g2 > 255) g2 = 255;
    if (g2 < 0) g2 = 0;
    let b3 = trunc(fr(n10 + fr(n10 * fr(this.m.snap[2] / 100.0))));
    if (b3 > 255) b3 = 255;
    if (b3 < 0) b3 = 0;
    this.rd.setColor(r2, g2, b3);
    this.rd.fillPolygon(array, array2, 4);
  }

  drawhi(image, n) {
    if (!image) {
      // TODO not ported: drawhi called with null image asset
      return;
    }
    const w = image.width || (image.getWidth ? image.getWidth(this.ob) : 0);
    const h = image.height || (image.getHeight ? image.getHeight(this.ob) : 0);
    if (this.m && this.m.darksky && this.rd && !this.hudOutline) {
      const hsbvals = floatArray(3);
      RGBtoHSB(this.m.csky[0], this.m.csky[1], this.m.csky[2], hsbvals);
      hsbvals[2] = 0.6;
      const rgb = HSBtoRGB(hsbvals[0], hsbvals[1], hsbvals[2]);
      const r = (rgb >> 16) & 0xff;
      const g = (rgb >> 8) & 0xff;
      const b = rgb & 0xff;
      this.rd.setColor(r, g, b);
      this.rd.fillRect(390 - idiv(w, 2), n - 2, w + 20, h + 2);
      this.rd.setColor(trunc(r / 1.1), trunc(g / 1.1), trunc(b / 1.1));
      this.rd.drawRect(390 - idiv(w, 2), n - 2, w + 20, h + 2);
    }
    if (this.rd) {
      this.rd.drawImage(image, 400 - idiv(w, 2), n);
    }
  }

  drawcs(n, str, r, g, b, n2) {
    if (!this.m) return;
    if (n2 !== 3 && n2 !== 4 && n2 !== 5) {
      r = trunc(fr(r + fr(r * fr(this.m.snap[0] / 100.0))));
      if (r > 255) r = 255;
      if (r < 0) r = 0;
      g = trunc(fr(g + fr(g * fr(this.m.snap[1] / 100.0))));
      if (g > 255) g = 255;
      if (g < 0) g = 0;
      b = trunc(fr(b + fr(b * fr(this.m.snap[2] / 100.0))));
      if (b > 255) b = 255;
      if (b < 0) b = 0;
    }
    if (n2 === 4) {
      r = trunc(fr(r - fr(r * fr(this.m.snap[0] / 100.0))));
      if (r > 255) r = 255;
      if (r < 0) r = 0;
      g = trunc(fr(g - fr(g * fr(this.m.snap[1] / 100.0))));
      if (g > 255) g = 255;
      if (g < 0) g = 0;
      b = trunc(fr(b - fr(b * fr(this.m.snap[2] / 100.0))));
      if (b > 255) b = 255;
      if (b < 0) b = 0;
    }
    const ftm = this.ftm || (this.rd ? this.rd.getFontMetrics() : { stringWidth: (s) => s.length * 6 });
    if (n2 === 1 && this.rd) {
      this.rd.setColor(0, 0, 0);
      this.rd.drawString(str, 400 - idiv(ftm.stringWidth(str), 2) + 1, n + 1);
    }
    if (n2 === 2) {
      r = idiv(r * 2 + this.m.csky[0] * 1, 3);
      if (r > 255) r = 255;
      if (r < 0) r = 0;
      g = idiv(g * 2 + this.m.csky[1] * 1, 3);
      if (g > 255) g = 255;
      if (g < 0) g = 0;
      b = idiv(b * 2 + this.m.csky[2] * 1, 3);
      if (b > 255) b = 255;
      if (b < 0) b = 0;
    }
    if (n2 === 5 && this.rd) {
      this.rd.setColor(idiv(this.m.csky[0], 2), idiv(this.m.csky[1], 2), idiv(this.m.csky[2], 2));
      this.rd.drawString(str, 400 - idiv(ftm.stringWidth(str), 2) + 1, n + 1);
    }
    // The port's "automatic" dark-sky HUD (main.js sets hudReadable): every
    // announcement -- stunts, power ups, wasted, checkpoints -- gets the same
    // WCAG contrast fix as the HUD sprites, on top of the Java's colour.
    if (this.hudReadable) [r, g, b] = this.hudReadable(r, g, b);
    if (this.rd) {
      this.rd.setColor(r, g, b);
      this.rd.drawString(str, 400 - idiv(ftm.stringWidth(str), 2), n);
    }
  }

  /** xtGraphics.replyn (xtGraphics.java) -- the Instant Replay's blinking label. */
  replyn() {
    if (this.aflk) {
      this.drawcs(30, 'Replay  > ', 0, 0, 0, 0);
      this.aflk = false;
    } else {
      this.drawcs(30, 'Replay  >>', 0, 128, 255, 0);
      this.aflk = true;
    }
  }

  /**
   * xtGraphics.levelhigh (xtGraphics.java:4004) -- the race highlight's
   * header image, its blinking title and the "press Enter" line.
   * n = record.wasted, n2 = record.whenwasted, n3 = record.closefinish,
   * n4 = the highlight frame, n5 = the stage.
   */
  levelhigh(n, n2, n3, n4, n5) {
    this.rd.drawImage(this.gameh, 301, 20);
    let n6 = 16;
    let n7 = 48;
    let n8 = 96;
    if (n4 < 50) {
      if (this.aflk) {
        n6 = 106;
        n7 = 176;
        n8 = 255;
        this.aflk = false;
      } else {
        this.aflk = true;
      }
    }
    if (n !== this.im) {
      if (n3 === 0) this.drawcs(60, "You Wasted 'em!", n6, n7, n8, 0);
      else if (n3 === 1) this.drawcs(60, 'Close Finish!', n6, n7, n8, 0);
      else this.drawcs(60, 'Close Finish!  Almost got it!', n6, n7, n8, 0);
    } else if (n2 === 229) {
      if (this.discon !== 240) this.drawcs(60, 'Wasted!', n6, n7, n8, 0);
      else this.drawcs(60, 'Disconnected!', n6, n7, n8, 0);
    } else if (n5 > 2 || n5 < 0) {
      this.drawcs(60, 'Stunts!', n6, n7, n8, 0);
    } else {
      this.drawcs(60, 'Best Stunt!', n6, n7, n8, 0);
    }
    this.drawcs(380, 'Press  [ Enter ]  to continue', 0, 0, 0, 0);
  }

  /**
   * xtGraphics.fleximage (xtGraphics.java:9535) -- the frozen, colour-bled
   * race frame behind the finish screen. Frame 0 grabs the pixels; each of
   * the 7 frames then smears every row left-to-right with a weight that grows
   * with `n`, and folds the channels together with a random per-run offset.
   *
   * `img` is a drawable holding the last race frame (main.js composites the
   * WebGL canvas and the overlay into it). flexpix keeps 0xRRGGBB, as the
   * Java's packed ints, so each frame reads back what the previous one wrote.
   */
  fleximage(img, n, n2) {
    setDrawPhase(true);
    try { this.#fleximage(img, n, n2); } finally { setDrawPhase(false); }
  }

  #fleximage(img, n, n2) {
    if (!this.badmac) {
      if (n === 0) {
        const c = new OffscreenCanvas(800, 450);
        const cx = c.getContext('2d', { willReadFrequently: true });
        cx.drawImage(img, 0, 0, 800, 450);
        const px = cx.getImageData(0, 0, 800, 450).data;
        this.flexpix = new Int32Array(360000);
        for (let i = 0; i < 360000; ++i) {
          this.flexpix[i] = px[i * 4] << 16 | px[i * 4 + 1] << 8 | px[i * 4 + 2];
        }
      }
      let n3 = 0;
      let red = 0;
      let green = 0;
      let blue = 0;
      let n4 = trunc(random() * 128.0);
      let n5 = trunc(5.0 + random() * 15.0);
      const w = fr(0.38);
      const den = fr(1.0 + fr(w * n));
      const out = new ImageData(800, 450);
      const od = out.data;
      for (let i = 0; i < 360000; ++i) {
        const v = this.flexpix[i];
        const cr = v >> 16 & 255, cg = v >> 8 & 255, cb = v & 255;
        let n6, n7, n8;
        if (n3 === 0) {
          n6 = red = cr;
          n7 = green = cg;
          n8 = blue = cb;
        } else {
          n6 = red = trunc(fr(fr(cr + fr(fr(red * w) * n)) / den));
          n7 = green = trunc(fr(fr(cg + fr(fr(green * w) * n)) / den));
          n8 = blue = trunc(fr(fr(cb + fr(fr(blue * w) * n)) / den));
        }
        if (++n3 === 800) n3 = 0;
        const r = trunc(fr((n6 * 17 + n7 + n8 + n4) / 21.0));
        const g = trunc(fr((n7 * 17 + n6 + n8 + n4) / 22.0));
        const b = trunc(fr((n8 * 17 + n6 + n7 + n4) / 24.0));
        if (--n5 === 0) {
          n4 = trunc(random() * 128.0);
          n5 = trunc(5.0 + random() * 15.0);
        }
        this.flexpix[i] = r << 16 | g << 8 | b;
        od[i * 4] = r; od[i * 4 + 1] = g; od[i * 4 + 2] = b; od[i * 4 + 3] = 255;
      }
      if (!this.fleximg) this.fleximg = new OffscreenCanvas(800, 450);
      this.fleximg.getContext('2d').putImageData(out, 0, 0);
      this.rd.drawImage(this.fleximg, 0, 0);
    } else {
      this.rd.setColor(0, 0, 0);
      this.rd.setComposite(0.1);
      this.rd.fillRect(0, 0, 800, 450);
      this.rd.setComposite(1.0);
    }
  }

  /**
   * xtGraphics.finish (xtGraphics.java:6645) -- the "You Won / You Lost"
   * screen, run once per tick at fase -5. `array` is the car models
   * (ContO[]), drawn when a win unlocks a car. Leaves fase = 102 on Enter;
   * the caller decides where that goes, since maini2 is not ported.
   */
  finish(checkPoints, array, control, n, n2, b) {
    setDrawPhase(true);
    try { this.#finish(checkPoints, array, control, n, n2, b); } finally { setDrawPhase(false); }
  }

  #finish(checkPoints, array, control, n, n2, b) {
    if (!this.badmac) {
      this.rd.drawImage(this.fleximg, 0, 0);
    } else {
      this.rd.setColor(0, 0, 0);
      this.rd.setComposite(0.1);
      this.rd.fillRect(0, 0, 800, 450);
      this.rd.setComposite(1.0);
    }
    this.rd.setFont('Arial', 1, 11);
    this.ftm = this.rd.getFontMetrics();
    let n3 = 0;
    let string = ':';
    if (checkPoints.stage > 0) {
      let stage = checkPoints.stage;
      if (stage > 10) stage -= 10;
      string = ' ' + stage + '!';
    }
    if (this.multion < 3) {
      if (this.winner) {
        this.rd.drawImage(this.congrd, 265, 87);
        this.drawcs(137, 'You Won!  At Stage' + string + '', 255, 161, 85, 3);
        this.drawcs(154, '' + checkPoints.name + '', 255, 115, 0, 3);
        n3 = 154;
      } else {
        this.rd.drawImage(this.gameov, 315, 117);
        if (this.multion !== 0 && (this.forstart === 700 || this.discon === 240)) {
          this.drawcs(167, 'Sorry, You where Disconnected from Game!', 255, 161, 85, 3);
          this.drawcs(184, 'Please check your connection!', 255, 115, 0, 3);
        } else {
          this.drawcs(167, 'You Lost!  At Stage' + string + '', 255, 161, 85, 3);
          this.drawcs(184, '' + checkPoints.name + '', 255, 115, 0, 3);
          n3 = 184;
        }
      }
    } else {
      this.rd.drawImage(this.gameov, 315, 117);
      this.drawcs(167, 'Finished Watching Game!  At Stage' + string + '', 255, 161, 85, 3);
      this.drawcs(184, '' + checkPoints.name + '', 255, 115, 0, 3);
      n3 = 184;
    }
    if (this.winner && this.multion === 0 && this.gmode !== 0
        && (checkPoints.stage === this.unlocked[this.gmode - 1] + (this.gmode - 1) * 10 || checkPoints.stage === 27)) {
      let n4 = 0;
      let y = 0;
      this.pin = 60;
      // [stage, car, y] per mode, xtGraphics.java:6696-6776.
      const unlocks = this.gmode === 1
        ? [[2, 5, 365], [4, 6, 320], [6, 11, 326], [8, 14, 350], [10, 15, 370]]
        : this.gmode === 2
          ? [[12, 8, 365], [14, 9, 320], [16, 10, 370], [18, 11, 326], [20, 12, 310],
             [22, 13, 310], [24, 14, 350], [26, 15, 370]]
          : [];
      for (const [st, car, yy] of unlocks) {
        if (checkPoints.stage === st) {
          n4 = car;
          y = yy;
          this.pin = -20;
          this.scm[this.gmode - 1] = car;
        }
      }
      if (checkPoints.stage !== 27) {
        this.rd.setFont('Arial', 1, 13);
        this.ftm = this.rd.getFontMetrics();
        const next = 'Stage ' + (checkPoints.stage + 1 - (this.gmode - 1) * 10) + ' is now unlocked!';
        if (this.aflk) this.drawcs(200 + this.pin, next, 196, 176, 0, 3);
        else this.drawcs(200 + this.pin, next, 255, 247, 165, 3);
        if (n4 !== 0) {
          if (this.aflk) this.drawcs(200, 'And:', 196, 176, 0, 3);
          else this.drawcs(200, 'And:', 255, 247, 165, 3);
          this.rd.setColor(236, 226, 202);
          if (random() > 0.5) {
            this.rd.setComposite(0.5);
            this.rd.fillRect(226, 211, 344, 125);
            this.rd.setComposite(1.0);
          }
          this.rd.setColor(0, 0, 0);
          this.rd.fillRect(226, 211, 348, 4);
          this.rd.fillRect(226, 211, 4, 125);
          this.rd.fillRect(226, 332, 348, 4);
          this.rd.fillRect(570, 211, 4, 125);
          array[n4].y = y;
          this.m.crs = true;
          this.m.x = -400;
          this.m.y = 0;
          this.m.z = -50;
          this.m.xz = 0;
          this.m.zy = 0;
          this.m.ground = 2470;
          array[n4].z = 1000;
          array[n4].x = 0;
          array[n4].xz += 5;
          array[n4].zy = 0;
          array[n4].wzy -= 10;
          array[n4].d(this.rd);
          if (random() < 0.5) {
            this.rd.setComposite(0.4);
            this.rd.setColor(236, 226, 202);
            for (let i = 0; i < 30; ++i) this.rd.drawLine(230, 215 + 4 * i, 569, 215 + 4 * i);
            this.rd.setComposite(1.0);
          }
          const s = n4 === 13 ? ' ' : '';
          const unl = '' + this.cd.names[n4] + '' + s + ' has been unlocked!';
          if (this.aflk) this.drawcs(320, unl, 196, 176, 0, 3);
          else this.drawcs(320, unl, 255, 247, 165, 3);
          this.pin = 140;
        }
        this.rd.setFont('Arial', 1, 11);
        this.ftm = this.rd.getFontMetrics();
        this.drawcs(220 + this.pin, 'GAME SAVED', 230, 167, 0, 3);
        if (this.pin === 60) this.pin = 30;
        else this.pin = 0;
      } else {
        this.rd.setFont('Arial', 1, 13);
        this.ftm = this.rd.getFontMetrics();
        const nfm = 'Woohoooo you finished NFM' + this.gmode + ' !!!';
        if (this.aflk) this.drawcs(180, nfm, 144, 167, 255, 3);
        else this.drawcs(180, nfm, 228, 240, 255, 3);
        if (this.aflk) this.drawcs(210, "You're Awesome!", 144, 167, 255, 3);
        else this.drawcs(212, "You're Awesome!", 228, 240, 255, 3);
        if (this.aflk) this.drawcs(240, "You're truly a RADICAL GAMER!", 144, 167, 255, 3);
        else this.drawcs(240, "You're truly a RADICAL GAMER!", 255, 100, 100, 3);
        this.rd.setColor(0, 0, 0);
        this.rd.fillRect(0, 255, 800, 62);
        this.rd.drawImage(this.radicalplay, this.radpx + trunc(8.0 * random() - 4.0), 255);
        if (this.radpx !== 212) {
          this.radpx += 40;
          if (this.radpx > 800) this.radpx = -468;
        }
        if (this.flipo === 40) this.radpx = 213;
        ++this.flipo;
        if (this.flipo === 70) this.flipo = 0;
        if (this.radpx === 212) {
          this.rd.setFont('Arial', 1, 11);
          this.ftm = this.rd.getFontMetrics();
          if (this.aflk) this.drawcs(309, 'A Game by Radicalplay.com', 144, 167, 255, 3);
          else this.drawcs(309, 'A Game by Radicalplay.com', 228, 240, 255, 3);
        }
        if (this.aflk) this.drawcs(350, 'Now get up and dance!', 144, 167, 255, 3);
        else this.drawcs(350, 'Now get up and dance!', 228, 240, 255, 3);
        this.pin = 0;
      }
      this.aflk = !this.aflk;
    }
    // TODO not ported: `multion != 0 && stage == -2` -- "Created by", public
    // stage flag and "Add to My Stages", which talk to the Radicalplay servers.
    this.rd.drawImage(this.contin[this.pcontin], 355, 380);
    if (control.enter || control.handb) {
      if (this.loadedt) music.stop();
      if (this.multion === 0) {
        this.opselect = 3;
        if (this.gmode === 1) {
          this.opselect = 0;
          if (this.winner && checkPoints.stage === this.unlocked[this.gmode - 1] + (this.gmode - 1) * 10 && checkPoints.stage !== 27) {
            ++this.unlocked[this.gmode - 1];
            this.justwon1 = true;
          } else {
            this.justwon1 = false;
          }
        }
        if (this.gmode === 2) {
          this.opselect = 1;
          if (this.winner && checkPoints.stage === this.unlocked[this.gmode - 1] + (this.gmode - 1) * 10 && checkPoints.stage !== 27) {
            ++this.unlocked[this.gmode - 1];
            this.justwon2 = true;
          } else {
            this.justwon2 = false;
          }
        }
        if (checkPoints.stage === 27 && this.gmode === 0) {
          checkPoints.stage = trunc(random() * 27.0) + 1;
        }
        this.fase = 102;
      } else if (this.cd.haltload === 1) {
        this.sc[0] = 36;
        this.fase = 1177;
      } else if (!this.mtop || (this.nfreeplays >= 5 && !this.logged)) {
        this.opselect = 2;
        this.fase = 102;
      } else {
        this.fase = -9;
      }
      if (this.multion === 0 && this.winner && checkPoints.stage !== 27 && checkPoints.stage > 0) {
        ++checkPoints.stage;
      }
      if (!this.winner && this.multion !== 0 && (this.forstart === 700 || this.discon === 240) && this.ndisco < 5) {
        ++this.ndisco;
      }
      this.flipo = 0;
      control.enter = false;
      control.handb = false;
    }
  }

  /**
   * xtGraphics.inishcarselect (xtGraphics.java:4844), single-player path.
   * `array` is the car models (ContO[]).
   */
  /**
   * Transpiled from xtGraphics.java ctachm (7305-7380), the fases the port's
   * screens run: 1 (stage select) and 7 (car select). `n3` is GameSparker's
   * `mouses`: 1 on the tick after a press (the button shows pressed), 2 on the
   * next (it fires). carselect.js feeds it the pointer.
   */
  ctachm(n, n2, n3, control) {
    if (this.fase === 1 || this.fase === 7) {
      const [nx, ny, bx, by, cy] = this.fase === 1 ? [625, 135, 115, 135, 360] : [645, 275, 95, 275, 385];
      if (n3 === 1) {
        if (this.over(this.next[0], n, n2, nx, ny)) this.pnext = 1;
        if (this.over(this.back[0], n, n2, bx, by)) this.pback = 1;
        if (this.over(this.contin[0], n, n2, 355, cy)) this.pcontin = 1;
      }
      if (n3 === 2) {
        if (this.pnext === 1) control.right = true;
        if (this.pback === 1) control.left = true;
        if (this.pcontin === 1) {
          control.enter = true;
          if (this.fase === 7) this.pcontin = 0;
        }
      }
    }
  }

  /** Transpiled from xtGraphics.java over (9529): a click within 5px of an image drawn at (n3, n4). */
  over(image, n, n2, n3, n4) {
    const height = image?.height ?? 0;
    const width = image?.width ?? 0;
    return n > n3 - 5 && n < n3 + width + 5 && n2 > n4 - 5 && n2 < n4 + height + 5;
  }

  inishcarselect(array) {
    this.nplayers = 7;
    this.im = 0;
    this.xstart.set([0, -350, 350, 0, -350, 350, 0]);
    this.zstart.set([-760, -380, -380, 0, 380, 380, 760]);
    this.onmsc = -1;
    this.remi = false;
    this.basefase = 0;
    this.noclass = false;
    if (this.testdrive !== 1 && this.testdrive !== 2) {
      if (this.gmode !== 0) {
        this.cfase = 0;
        this.sc[0] = this.scm[this.gmode - 1];
      }
      if (this.gmode === 0) this.sc[0] = this.osc;
      if (this.cd.lastload !== 1 || this.cfase !== 3) this.onmsc = this.sc[0];
      if (this.cfase === 0 && this.sc[0] > 15) this.sc[0] = 15;
      // TODO not ported: the onjoin/ontyp multiplayer class limits, and the
      // cfase 3/11/101 custom-car and account-car lists (cd.lastload).
      if (this.cfase !== 0) this.cfase = 0;
      this.minsl = 0;
      this.maxsl = 15;
      if (this.sc[0] < this.minsl) this.sc[0] = this.minsl;
      if (this.sc[0] > this.maxsl) this.sc[0] = this.maxsl;
    } else {
      this.minsl = this.sc[0];
      this.maxsl = this.sc[0];
    }
    this.carsbginflex();
    this.flatrstart = 0;
    this.m.lightson = false;
    this.pnext = 0;
    this.pback = 0;
    this.lsc = -1;
    this.mouson = -1;
    if (this.multion === 0) {
      const hsb = new Float32Array(3);
      for (let j = 0; j < 16; ++j) {
        RGBtoHSB(array[j].fcol[0], array[j].fcol[1], array[j].fcol[2], hsb);
        for (let k = 0; k < array[j].npl; ++k) {
          if (array[j].p[k].colnum === 1) {
            array[j].p[k].hsb[0] = hsb[0];
            array[j].p[k].hsb[1] = hsb[1];
            array[j].p[k].hsb[2] = hsb[2];
            array[j].p[k].oc[0] = array[j].fcol[0];
            array[j].p[k].oc[1] = array[j].fcol[1];
            array[j].p[k].oc[2] = array[j].fcol[2];
          }
        }
        RGBtoHSB(array[j].scol[0], array[j].scol[1], array[j].scol[2], hsb);
        for (let l = 0; l < array[j].npl; ++l) {
          if (array[j].p[l].colnum === 2) {
            array[j].p[l].hsb[0] = hsb[0];
            array[j].p[l].hsb[1] = hsb[1];
            array[j].p[l].hsb[2] = hsb[2];
            array[j].p[l].oc[0] = array[j].scol[0];
            array[j].p[l].oc[1] = array[j].scol[1];
            array[j].p[l].oc[2] = array[j].scol[2];
          }
        }
        array[j].xy = 0;
      }
      for (let n2 = 0; n2 < 6; ++n2) this.arnp[n2] = -1.0;
    }
    this.m.trk = 0;
    this.m.crs = true;
    this.m.x = -400;
    this.m.y = -525;
    this.m.z = -50;
    this.m.xz = 0;
    this.m.zy = 10;
    this.m.ground = 495;
    this.m.ih = 0;
    this.m.iw = 0;
    this.m.h = 450;
    this.m.w = 800;
    this.m.focus_point = 400;
    this.m.cx = 400;
    this.m.cy = 225;
    this.m.cz = 50;
    // The menu track (intertrack) is the launcher's, already playing.
  }

  /** xtGraphics.carsbginflex (xtGraphics.java:10146). */
  carsbginflex() {
    if (!this.badmac) {
      this.flatr = 0;
      this.flyr = trunc(fr(fr(this.m.random() * 160.0) - 80.0));
      this.flyrdest = trunc(fr(fr(this.flyr + fr(this.m.random() * 160.0)) - 80.0));
      this.flang = 1;
      this.flexpix = Int32Array.from(this.carsbgpix || new Int32Array(268000));
    }
  }

  /**
   * xtGraphics.drawSmokeCarsbg (xtGraphics.java) -- the car-select backdrop
   * bursting out of the smoke cloud over the first frames. Works on the
   * 670x400 flexpix grabbed by carsbginflex(), in packed 0xRRGGBB.
   */
  drawSmokeCarsbg() {
    if (!this.badmac) {
      if (Math.abs(this.flyr - this.flyrdest) > 20) {
        if (this.flyr > this.flyrdest) this.flyr -= 20;
        else this.flyr += 20;
      } else {
        this.flyr = this.flyrdest;
        this.flyrdest = trunc(fr(fr(this.flyr + fr(this.m.random() * 160.0)) - 80.0));
      }
      if (this.flyr > 160) this.flyr = 160;
      if (this.flatr > 170) {
        ++this.flatrstart;
        this.flatr = this.flatrstart * 3;
        this.flyr = trunc(fr(fr(this.m.random() * 160.0) - 80.0));
        this.flyrdest = trunc(fr(fr(this.flyr + fr(this.m.random() * 160.0)) - 80.0));
        this.flang = 1;
      }
      const fp = this.flexpix;
      const sm = this.smokey;
      const s0 = sm[0];
      const flang = this.flang, flatr = this.flatr, flyr = this.flyr;
      for (let i = 0; i < 466; ++i) {
        for (let j = 0; j < 202; ++j) {
          const sv = sm[i + j * 466];
          if (sv !== s0) {
            const pys = this.pys(i, 233, j, flyr);
            const n = trunc(fr(fr((i - 233) / pys) * flatr));
            const n2 = trunc(fr(fr((j - flyr) / pys) * flatr));
            const n3 = i + n + 100 + (j + n2 + 110) * 670;
            if (i + n + 100 < 670 && i + n + 100 > 0 && j + n2 + 110 < 400 && j + n2 + 110 > 0 && n3 < 268000 && n3 >= 0) {
              const c = fp[n3];
              const cr = c >> 16 & 255, cg = c >> 8 & 255, cb = c & 255;
              const sr = sv >> 16 & 255, sg = sv >> 8 & 255, sb = sv & 255;
              const n4 = fr((255.0 - sr) / 255.0);
              const n5 = fr((255.0 - sg) / 255.0);
              const n6 = fr((255.0 - sb) / 255.0);
              const a4 = fr(flang * n4), a5 = fr(flang * n5), a6 = fr(flang * n6);
              let r = trunc(fr(fr(fr(cr * a4) + fr(sr * fr(1.0 - n4))) / fr(a4 + fr(1.0 - n4))));
              let g = trunc(fr(fr(fr(cg * a5) + fr(sg * fr(1.0 - n5))) / fr(a5 + fr(1.0 - n5))));
              let b = trunc(fr(fr(fr(cb * a6) + fr(sb * fr(1.0 - n6))) / fr(a6 + fr(1.0 - n6))));
              if (r > 255) r = 255;
              if (r < 0) r = 0;
              if (g > 255) g = 255;
              if (g < 0) g = 0;
              if (b > 255) b = 255;
              if (b < 0) b = 0;
              fp[n3] = r << 16 | g << 8 | b;
            }
          }
        }
      }
      this.flang += 2;
      this.flatr += 10 + this.flatrstart * 2;
      if (!this._smokeImg) {
        this._smokeImg = new OffscreenCanvas(670, 400);
        this._smokeData = new ImageData(670, 400);
      }
      const d = this._smokeData.data;
      for (let i = 0; i < 268000; ++i) {
        const v = fp[i];
        d[i * 4] = v >> 16 & 255; d[i * 4 + 1] = v >> 8 & 255; d[i * 4 + 2] = v & 255; d[i * 4 + 3] = 255;
      }
      this._smokeImg.getContext('2d').putImageData(this._smokeData, 0, 0);
      this.rd.drawImage(this._smokeImg, 65, 25);
    } else {
      this.rd.drawImage(this.carsbg, 65, 25);
      ++this.flatrstart;
    }
  }

  /**
   * xtGraphics.carselect (xtGraphics.java:5080), the single-player cfase 0
   * screen: backdrop, the spinning car, name, arrows, career locks, stats and
   * Continue; left/right flip between cars, Enter picks. Leaves fase = 3
   * (stage select) on a pick.
   */
  carselect(control, array, mad, n, n2, b) {
    setDrawPhase(true);
    try { this.#carselect(control, array, mad, n, n2, b); } finally { setDrawPhase(false); }
  }

  #carselect(control, array, mad, n, n2, b) {
    this.rd.setColor(0, 0, 0);
    this.rd.fillRect(0, 0, 65, 450);
    this.rd.fillRect(735, 0, 65, 450);
    this.rd.fillRect(65, 0, 670, 25);
    this.rd.fillRect(65, 425, 670, 25);
    if (this.flatrstart === 6) {
      if (this.multion !== 0 || this.testdrive === 1 || this.testdrive === 2) this.rd.drawImage(this.carsbgc, 65, 25);
      else this.rd.drawImage(this.carsbg, 65, 25);
    } else if (this.flatrstart <= 1) {
      this.drawSmokeCarsbg();
    } else {
      this.rd.setColor(255, 255, 255);
      this.rd.fillRect(65, 25, 670, 400);
      this.carsbginflex();
      this.flatrstart = 6;
    }
    this.rd.drawImage(this.selectcar, 321, 37);
    // TODO not ported: cfase 3/7/11/101/8 headers (custom, account and
    // Top 20 car lists, "Removing Car..."), which need the Radicalplay servers.
    if (!this.remi) array[this.sc[0]].d(this.rd);
    // TODO not ported: the multion/testdrive recolour of the chosen car.
    let k = 0;
    if (this.flipo === 0) {
      this.rd.setFont('Arial', 1, 13);
      this.ftm = this.rd.getFontMetrics();
      let n8 = 0;
      if (this.flatrstart < 6) n8 = 2;
      if (!this.remi) {
        if (this.aflk) {
          this.drawcs(95 + n8, '' + this.cd.names[this.sc[0]], 240, 240, 240, 3);
          this.aflk = false;
        } else {
          this.drawcs(95, '' + this.cd.names[this.sc[0]], 176, 176, 176, 3);
          this.aflk = true;
        }
      }
      const c = array[this.sc[0]];
      c.z = 950;
      if (this.sc[0] === 13) c.z = 1000;
      c.y = -34 - c.grat;
      c.x = 0;
      if (this.mouson >= 0 && this.mouson <= 3) c.xz += 2;
      else c.xz += 5;
      if (c.xz > 360) c.xz -= 360;
      c.zy = 0;
      c.wzy -= 10;
      if (c.wzy < -30) c.wzy += 30;
      if (!this.remi) {
        if (this.sc[0] !== this.minsl) this.rd.drawImage(this.back[this.pback], 95, 275);
        if (this.sc[0] !== this.maxsl) this.rd.drawImage(this.next[this.pnext], 645, 275);
      }
      if (this.gmode === 1) {
        if (this.sc[0] === 5 && this.unlocked[0] <= 2) k = 2;
        if (this.sc[0] === 6 && this.unlocked[0] <= 4) k = 4;
        if (this.sc[0] === 11 && this.unlocked[0] <= 6) k = 6;
        if (this.sc[0] === 14 && this.unlocked[0] <= 8) k = 8;
        if (this.sc[0] === 15 && this.unlocked[0] <= 10) k = 10;
      }
      if (this.gmode === 2 && this.sc[0] >= 8 && this.unlocked[1] <= (this.sc[0] - 7) * 2) {
        k = (this.sc[0] - 7) * 2;
      }
      if (k !== 0) {
        if (this.gatey === 300) {
          for (let n9 = 0; n9 < 9; ++n9) {
            this.pgas[n9] = false;
            this.pgady[n9] = 0;
          }
          this.pgas[0] = true;
        }
        for (let n10 = 0; n10 < 9; ++n10) {
          this.rd.drawImage(this.pgate, this.pgatx[n10], this.pgaty[n10] + this.pgady[n10] - this.gatey);
          if (this.flatrstart === 6) {
            if (this.pgas[n10]) {
              this.pgady[n10] -= idiv(80 + idiv(100, n10 + 1) - Math.abs(this.pgady[n10]), 3);
              if (this.pgady[n10] < -(70 + idiv(100, n10 + 1))) {
                this.pgas[n10] = false;
                if (n10 !== 8) this.pgas[n10 + 1] = true;
              }
            } else {
              this.pgady[n10] += idiv(80 + idiv(100, n10 + 1) - Math.abs(this.pgady[n10]), 3);
              if (this.pgady[n10] > 0) this.pgady[n10] = 0;
            }
          }
        }
        if (this.gatey !== 0) this.gatey -= 100;
        if (this.flatrstart === 6) {
          this.drawcs(355, '[ Car Locked ]', 210, 210, 210, 3);
          this.drawcs(375, 'This car unlocks when stage ' + k + ' is completed...', 255, 96, 0, 3);
        }
      } else {
        if (this.flatrstart === 6) {
          // TODO not ported: the cfase 0 gmode 0 "Car Maker" / "My Cars" /
          // "Top 20" buttons and cfase 10/100/-1/9/7/3/11/101/5/4/2/1, the
          // custom-car and online-account screens.
          const sc = this.sc[0];
          this.rd.setFont('Arial', 1, 11);
          this.ftm = this.rd.getFontMetrics();
          this.rd.setColor(181, 120, 40);
          // The Java places each label by hand so the ENGLISH word ends at its
          // bar. A translation is longer, so it is right-aligned to the bar
          // instead; English keeps the original positions exactly.
          const label = (s, x, barX, y) => {
            const at = lang === 'en' ? x : barX - 2 - trunc(this.ftm.stringWidth(s));
            this.rd.drawString(s, at, y);
          };
          label('Top Speed:', 98, 162, 343);
          this.rd.drawImage(this.statb, 162, 337);
          label('Acceleration:', 88, 162, 358);
          this.rd.drawImage(this.statb, 162, 352);
          label('Handling:', 110, 162, 373);
          this.rd.drawImage(this.statb, 162, 367);
          label('Stunts:', 495, 536, 343);
          this.rd.drawImage(this.statb, 536, 337);
          label('Strength:', 483, 536, 358);
          this.rd.drawImage(this.statb, 536, 352);
          label('Endurance:', 473, 536, 373);
          this.rd.drawImage(this.statb, 536, 367);
          this.rd.setColor(0, 0, 0);
          const bar = (x, y, v) => this.rd.fillRect(trunc(fr(x + fr(156.0 * v))), y, trunc(fr(fr(156.0 * fr(1.0 - v)) + 1.0)), 7);
          let n19 = fr((this.cd.swits[sc][2] - 220) / 90.0);
          if (n19 < 0.2) n19 = fr(0.2);
          bar(162.0, 337, n19);
          let n20 = fr(fr(fr(fr(fr(this.cd.acelf[sc][1] * this.cd.acelf[sc][0]) * this.cd.acelf[sc][2]) * this.cd.grip[sc])) / 7700.0);
          if (n20 > 1.0) n20 = 1.0;
          bar(162.0, 352, n20);
          bar(162.0, 367, this.cd.dishandle[sc]);
          let n22 = fr(fr(fr(fr(this.cd.airc[sc] * this.cd.airs[sc]) * this.cd.bounce[sc]) + 28.0) / 139.0);
          if (n22 > 1.0) n22 = 1.0;
          bar(536.0, 337, n22);
          let n23 = fr(fr(this.cd.moment[sc] + 0.5) / 2.6);
          if (n23 > 1.0) n23 = 1.0;
          bar(536.0, 352, n23);
          bar(536.0, 367, this.cd.outdam[sc]);
          this.rd.drawImage(this.statbo, 162, 337);
          this.rd.drawImage(this.statbo, 162, 352);
          this.rd.drawImage(this.statbo, 162, 367);
          this.rd.drawImage(this.statbo, 536, 337);
          this.rd.drawImage(this.statbo, 536, 352);
          this.rd.drawImage(this.statbo, 536, 367);
          // TODO not ported: multion/testdrive car class and colour pickers.
        }
        if (!this.remi) this.rd.drawImage(this.contin[this.pcontin], 355, 385);
      }
    } else {
      this.pback = 0;
      this.pnext = 0;
      this.gatey = 300;
      const c = array[this.sc[0]];
      if (this.flipo > 10) {
        c.y -= 100;
        if (this.nextc === 1) c.zy += 20;
        if (this.nextc === -1) c.zy -= 20;
      } else {
        if (this.flipo === 10) {
          if (this.nextc >= 20) {
            this.sc[0] = this.nextc - 20;
            this.lsc = -1;
          }
          if (this.nextc === 1) {
            ++this.sc[0];
            if (this.gmode === 1) {
              if (this.sc[0] === 7) this.sc[0] = 11;
              if (this.sc[0] === 12) this.sc[0] = 14;
            }
          }
          if (this.nextc === -1) {
            --this.sc[0];
            if (this.gmode === 1) {
              if (this.sc[0] === 13) this.sc[0] = 11;
              if (this.sc[0] === 10) this.sc[0] = 6;
            }
          }
          const nc = array[this.sc[0]];
          nc.z = 950;
          nc.y = -34 - nc.grat - 1100;
          nc.x = 0;
          nc.zy = 0;
        }
        array[this.sc[0]].y += 100;
      }
      --this.flipo;
    }
    if (this.cfase === 0 || this.cfase === 3 || this.cfase === 11 || this.cfase === 101) {
      this.basefase = this.cfase;
      if (control.right) {
        control.right = false;
        if (this.sc[0] !== this.maxsl && this.flipo === 0) {
          if (this.flatrstart > 1) this.flatrstart = 0;
          this.nextc = 1;
          this.flipo = 20;
        }
      }
      if (control.left) {
        control.left = false;
        if (this.sc[0] !== this.minsl && this.flipo === 0) {
          if (this.flatrstart > 1) this.flatrstart = 0;
          this.nextc = -1;
          this.flipo = 20;
        }
      }
      if (this.cfase !== 11 && this.cfase !== 101 && k === 0 && this.flipo < 10 && (control.handb || control.enter)) {
        this.m.crs = false;
        if (this.multion !== 0) this.fase = 1177;
        else if (this.testdrive !== 3 && this.testdrive !== 4) this.fase = 3;
        else this.fase = -22;
        // TODO not ported: app.setcarcookie -- the launcher saves the car.
        if (this.gmode === 0) this.osc = this.sc[0];
        if (this.gmode === 1) this.scm[0] = this.sc[0];
        if (this.gmode === 2) this.scm[1] = this.sc[0];
        this.flexpix = null;
        control.handb = false;
        control.enter = false;
      }
    }
    if (control.handb || control.enter) {
      control.handb = false;
      control.enter = false;
    }
  }

  arrow(n, n2, checkPoints, b) {
    if (!this.m) return;
    const array = intArray(7);
    const array2 = intArray(7);
    const array3 = intArray(7);
    const n3 = 400;
    const n4 = -90;
    const n5 = 700;
    for (let i = 0; i < 7; ++i) {
      array2[i] = n4;
    }
    array[0] = n3;
    array3[0] = n5 + 110;
    array[1] = n3 - 35;
    array3[1] = n5 + 50;
    array[2] = n3 - 15;
    array3[2] = n5 + 50;
    array[3] = n3 - 15;
    array3[3] = n5 - 50;
    array[4] = n3 + 15;
    array3[4] = n5 - 50;
    array[5] = n3 + 15;
    array3[5] = n5 + 50;
    array[6] = n3 + 35;
    array3[6] = n5 + 50;
    let n7;
    if (!b) {
      let n6 = 0;
      if (checkPoints.x[n] - checkPoints.opx[this.im] >= 0) {
        n6 = 180;
      }
      n7 = trunc(90 + n6 + Math.atan((checkPoints.z[n] - checkPoints.opz[this.im]) / (checkPoints.x[n] - checkPoints.opx[this.im])) / 0.017453292519943295);
    } else {
      let alocked = 0;
      if (this.multion === 0 || this.alocked === -1) {
        let py = -1;
        let n8 = 0;
        for (let j = 0; j < this.nplayers; ++j) {
          if (j !== this.im && (this.py(idiv(checkPoints.opx[this.im], 100), idiv(checkPoints.opx[j], 100), idiv(checkPoints.opz[this.im], 100), idiv(checkPoints.opz[j], 100)) < py || py === -1) && (n8 === 0 || checkPoints.onscreen[j] !== 0) && checkPoints.dested[j] === 0) {
            alocked = j;
            py = this.py(idiv(checkPoints.opx[this.im], 100), idiv(checkPoints.opx[j], 100), idiv(checkPoints.opz[this.im], 100), idiv(checkPoints.opz[j], 100));
            if (checkPoints.onscreen[j] !== 0) {
              n8 = 1;
            }
          }
        }
      } else {
        alocked = this.alocked;
      }
      let n9 = 0;
      if (checkPoints.opx[alocked] - checkPoints.opx[this.im] >= 0) {
        n9 = 180;
      }
      n7 = trunc(90 + n9 + Math.atan((checkPoints.opz[alocked] - checkPoints.opz[this.im]) / (checkPoints.opx[alocked] - checkPoints.opx[this.im])) / 0.017453292519943295);
      if (this.multion === 0) {
        this.drawcs(13, "[                                ]", 76, 67, 240, 0);
        if (this.cd && this.cd.names) {
          this.drawcs(13, this.cd.names[this.sc[alocked]], 0, 0, 0, 0);
        }
      } else {
        // TODO not ported: multiplayer target arrow text formatting
        if (this.rd) {
          this.rd.setFont('bold 12px Arial');
          this.ftm = this.rd.getFontMetrics();
        }
        this.drawcs(17, "[                                ]", 76, 67, 240, 0);
        this.drawcs(12, this.plnames[alocked], 0, 0, 0, 0);
        if (this.rd) {
          this.rd.setFont('10px Arial');
          this.ftm = this.rd.getFontMetrics();
        }
        if (this.cd && this.cd.names) {
          this.drawcs(24, this.cd.names[this.sc[alocked]], 0, 0, 0, 0);
        }
        if (this.rd) {
          this.rd.setFont('bold 11px Arial');
          this.ftm = this.rd.getFontMetrics();
        }
      }
    }
    let k;
    for (k = n7 + this.m.xz; k < 0; k += 360) {}
    while (k > 180) {
      k -= 360;
    }
    if (!b) {
      if (k > 130) {
        k = 130;
      }
      if (k < -130) {
        k = -130;
      }
    } else {
      if (k > 100) {
        k = 100;
      }
      if (k < -100) {
        k = -100;
      }
    }
    if (Math.abs(this.ana - k) < 180) {
      if (Math.abs(this.ana - k) < 10) {
        this.ana = k;
      } else if (this.ana < k) {
        this.ana += 10;
      } else {
        this.ana -= 10;
      }
    } else {
      if (k < 0) {
        this.ana += 15;
        if (this.ana > 180) {
          this.ana -= 360;
        }
      }
      if (k > 0) {
        this.ana -= 15;
        if (this.ana < -180) {
          this.ana += 360;
        }
      }
    }
    this.rot(array, array3, n3, n5, this.ana, 7);
    const abs = Math.abs(this.ana);
    if (this.rd) this.rd.setRenderingHint();
    if (!b) {
      if (abs > 7 || n2 > 0 || n2 === -2 || this.cntan !== 0) {
        for (let l = 0; l < 7; ++l) {
          array[l] = this.xs(array[l], array3[l]);
          array2[l] = this.ys(array2[l], array3[l]);
        }
        let r = trunc(fr(190.0 + fr(190.0 * fr(this.m.snap[0] / 100.0))));
        if (r > 255) r = 255;
        if (r < 0) r = 0;
        let g = trunc(fr(255.0 + fr(255.0 * fr(this.m.snap[1] / 100.0))));
        if (g > 255) g = 255;
        if (g < 0) g = 0;
        let b2 = 0;
        if (n2 <= 0) {
          if (abs <= 45 && n2 !== -2 && this.cntan === 0) {
            r = idiv(r * abs + this.m.csky[0] * (45 - abs), 45);
            g = idiv(g * abs + this.m.csky[1] * (45 - abs), 45);
            b2 = idiv(b2 * abs + this.m.csky[2] * (45 - abs), 45);
          }
          if (abs >= 90) {
            let n10 = trunc(fr(255.0 + fr(255.0 * fr(this.m.snap[0] / 100.0))));
            if (n10 > 255) n10 = 255;
            if (n10 < 0) n10 = 0;
            r = idiv(r * (140 - abs) + n10 * (abs - 90), 50);
            if (r > 255) r = 255;
          }
        } else if (this.flk) {
          r = trunc(fr(255.0 + fr(255.0 * fr(this.m.snap[0] / 100.0))));
          if (r > 255) r = 255;
          if (r < 0) r = 0;
          this.flk = false;
        } else {
          r = trunc(fr(255.0 + fr(255.0 * fr(this.m.snap[0] / 100.0))));
          if (r > 255) r = 255;
          if (r < 0) r = 0;
          g = trunc(fr(220.0 + fr(220.0 * fr(this.m.snap[1] / 100.0))));
          if (g > 255) g = 255;
          if (g < 0) g = 0;
          this.flk = true;
        }
        if (this.rd) {
          this.rd.setColor(r, g, b2);
          this.rd.fillPolygon(array, array2, 7);
        }
        let r2 = trunc(fr(115.0 + fr(115.0 * fr(this.m.snap[0] / 100.0))));
        if (r2 > 255) r2 = 255;
        if (r2 < 0) r2 = 0;
        let g2 = trunc(fr(170.0 + fr(170.0 * fr(this.m.snap[1] / 100.0))));
        if (g2 > 255) g2 = 255;
        if (g2 < 0) g2 = 0;
        let b3 = 0;
        if (n2 <= 0) {
          if (abs <= 45 && n2 !== -2 && this.cntan === 0) {
            r2 = idiv(r2 * abs + this.m.csky[0] * (45 - abs), 45);
            g2 = idiv(g2 * abs + this.m.csky[1] * (45 - abs), 45);
            b3 = idiv(b3 * abs + this.m.csky[2] * (45 - abs), 45);
          }
        } else if (this.flk) {
          r2 = trunc(fr(255.0 + fr(255.0 * fr(this.m.snap[0] / 100.0))));
          if (r2 > 255) r2 = 255;
          if (r2 < 0) r2 = 0;
          g2 = 0;
        }
        if (this.rd) {
          this.rd.setColor(r2, g2, b3);
          this.rd.drawPolygon(array, array2, 7);
        }
      }
    } else {
      let n11 = 0;
      if (this.multion !== 0) {
        n11 = 8;
      }
      for (let n12 = 0; n12 < 7; ++n12) {
        array[n12] = this.xs(array[n12], array3[n12]);
        array2[n12] = this.ys(array2[n12], array3[n12]) + n11;
      }
      let r3 = trunc(fr(159.0 + fr(159.0 * fr(this.m.snap[0] / 100.0))));
      if (r3 > 255) r3 = 255;
      if (r3 < 0) r3 = 0;
      let g3 = trunc(fr(207.0 + fr(207.0 * fr(this.m.snap[1] / 100.0))));
      if (g3 > 255) g3 = 255;
      if (g3 < 0) g3 = 0;
      let b4 = trunc(fr(255.0 + fr(255.0 * fr(this.m.snap[2] / 100.0))));
      if (b4 > 255) b4 = 255;
      if (b4 < 0) b4 = 0;
      if (this.rd) {
        this.rd.setColor(r3, g3, b4);
        this.rd.fillPolygon(array, array2, 7);
      }
      let r4 = trunc(fr(120.0 + fr(120.0 * fr(this.m.snap[0] / 100.0))));
      if (r4 > 255) r4 = 255;
      if (r4 < 0) r4 = 0;
      let g4 = trunc(fr(114.0 + fr(114.0 * fr(this.m.snap[1] / 100.0))));
      if (g4 > 255) g4 = 255;
      if (g4 < 0) g4 = 0;
      let b5 = trunc(fr(255.0 + fr(255.0 * fr(this.m.snap[2] / 100.0))));
      if (b5 > 255) b5 = 255;
      if (b5 < 0) b5 = 0;
      if (this.rd) {
        this.rd.setColor(r4, g4, b5);
        this.rd.drawPolygon(array, array2, 7);
      }
    }
    if (this.rd) this.rd.setRenderingHint();
  }

  radarstat(mad, contO, checkPoints) {
    if (!this.rd || !this.m) return;
    this.rd.setComposite(0.5);
    this.rd.setColor(this.m.csky[0], this.m.csky[1], this.m.csky[2]);
    this.rd.fillRect(10, 55, 172, 172);
    this.rd.setComposite(1.0);
    this.rd.setRenderingHint();
    this.rd.setColor(idiv(this.m.csky[0], 2), idiv(this.m.csky[1], 2), idiv(this.m.csky[2], 2));
    for (let i = 0; i < checkPoints.n; ++i) {
      let n = i + 1;
      if (i === checkPoints.n - 1) {
        n = 0;
      }
      let b = false;
      if (checkPoints.typ[n] === -3) {
        n = 0;
        b = true;
      }
      const array = Int32Array.from([
        trunc(fr(96.0 - fr(fr(checkPoints.opx[this.im] - checkPoints.x[i]) / checkPoints.prox))),
        trunc(fr(96.0 - fr(fr(checkPoints.opx[this.im] - checkPoints.x[n]) / checkPoints.prox)))
      ]);
      const array2 = Int32Array.from([
        trunc(fr(141.0 - fr(fr(checkPoints.z[i] - checkPoints.opz[this.im]) / checkPoints.prox))),
        trunc(fr(141.0 - fr(fr(checkPoints.z[n] - checkPoints.opz[this.im]) / checkPoints.prox)))
      ]);
      this.rot(array, array2, 96, 141, mad.cxz, 2);
      this.rd.drawLine(array[0], array2[0], array[1], array2[1]);
      if (b) {
        break;
      }
    }
    if (this.arrace || this.multion > 1) {
      const array3 = intArray(this.nplayers);
      const array4 = intArray(this.nplayers);
      for (let j = 0; j < this.nplayers; ++j) {
        array3[j] = trunc(fr(96.0 - fr((checkPoints.opx[this.im] - checkPoints.opx[j]) / checkPoints.prox)));
        array4[j] = trunc(fr(141.0 - fr((checkPoints.opz[j] - checkPoints.opz[this.im]) / checkPoints.prox)));
      }
      this.rot(array3, array4, 96, 141, mad.cxz, this.nplayers);
      let n2 = 0;
      let n3 = trunc(fr(80.0 + fr(80.0 * fr(this.m.snap[1] / 100.0))));
      if (n3 > 255) n3 = 255;
      if (n3 < 0) n3 = 0;
      let n4 = trunc(fr(159.0 + fr(159.0 * fr(this.m.snap[2] / 100.0))));
      if (n4 > 255) n4 = 255;
      if (n4 < 0) n4 = 0;
      for (let k = 0; k < this.nplayers; ++k) {
        if (k !== this.im && checkPoints.dested[k] === 0) {
          if (this.clangame !== 0) {
            // TODO not ported: clan game radar car colors
            let n5, n6, n7;
            if (this.pclan[k].toLowerCase() === this.gaclan.toLowerCase()) {
              n5 = 159; n6 = 80; n7 = 0;
            } else {
              n5 = 0; n6 = 80; n7 = 159;
            }
            n2 = trunc(fr(n5 + fr(n5 * fr(this.m.snap[0] / 100.0))));
            if (n2 > 255) n2 = 255;
            if (n2 < 0) n2 = 0;
            n3 = trunc(fr(n6 + fr(n6 * fr(this.m.snap[1] / 100.0))));
            if (n3 > 255) n3 = 255;
            if (n3 < 0) n3 = 0;
            n4 = trunc(fr(n7 + fr(n7 * fr(this.m.snap[2] / 100.0))));
            if (n4 > 255) n4 = 255;
            if (n4 < 0) n4 = 0;
          }
          let n8 = 2;
          if (this.alocked === k) {
            n8 = 3;
            this.rd.setColor(n2, n3, n4);
          } else {
            this.rd.setColor(idiv(n2 + this.m.csky[0], 2), idiv(this.m.csky[1] + n3, 2), idiv(n4 + this.m.csky[2], 2));
          }
          this.rd.drawLine(array3[k] - n8, array4[k], array3[k] + n8, array4[k]);
          this.rd.drawLine(array3[k], array4[k] + n8, array3[k], array4[k] - n8);
          this.rd.setColor(n2, n3, n4);
          this.rd.fillRect(array3[k] - 1, array4[k] - 1, 3, 3);
        }
      }
    }
    let r = trunc(fr(159.0 + fr(159.0 * fr(this.m.snap[0] / 100.0))));
    if (r > 255) r = 255;
    if (r < 0) r = 0;
    let g = 0;
    let b2 = 0;
    if (this.clangame !== 0) {
      // TODO not ported: clan game player radar indicator color
      let n9, n10, n11;
      if (this.pclan[this.im].toLowerCase() === this.gaclan.toLowerCase()) {
        n9 = 159; n10 = 80; n11 = 0;
      } else {
        n9 = 0; n10 = 80; n11 = 159;
      }
      r = trunc(fr(n9 + fr(n9 * fr(this.m.snap[0] / 100.0))));
      if (r > 255) r = 255;
      if (r < 0) r = 0;
      g = trunc(fr(n10 + fr(n10 * fr(this.m.snap[1] / 100.0))));
      if (g > 255) g = 255;
      if (g < 0) g = 0;
      b2 = trunc(fr(n11 + fr(n11 * fr(this.m.snap[2] / 100.0))));
      if (b2 > 255) b2 = 255;
      if (b2 < 0) b2 = 0;
    }
    this.rd.setColor(idiv(r + this.m.csky[0], 2), idiv(this.m.csky[1] + g, 2), idiv(b2 + this.m.csky[2], 2));
    this.rd.drawLine(96, 139, 96, 143);
    this.rd.drawLine(94, 141, 98, 141);
    this.rd.setColor(r, g, b2);
    this.rd.fillRect(95, 140, 3, 3);
    this.rd.setRenderingHint();
    if (this.m.darksky && !this.hudOutline) {
      const hsbvals = floatArray(3);
      RGBtoHSB(this.m.csky[0], this.m.csky[1], this.m.csky[2], hsbvals);
      hsbvals[2] = 0.6;
      const rgb = HSBtoRGB(hsbvals[0], hsbvals[1], hsbvals[2]);
      const rSky = (rgb >> 16) & 0xff;
      const gSky = (rgb >> 8) & 0xff;
      const bSky = rgb & 0xff;
      this.rd.setColor(rSky, gSky, bSky);
      this.rd.fillRect(5, 232, 181, 17);
      this.rd.drawLine(4, 233, 4, 247);
      this.rd.drawLine(3, 235, 3, 245);
      this.rd.drawLine(186, 233, 186, 247);
      this.rd.drawLine(187, 235, 187, 245);
    }
    if (this.sped) {
      this.rd.drawImage(this.sped, 7, 234);
    } else {
      // TODO not ported: sped image asset not loaded
    }
    const n12 = contO.x - this.lcarx;
    this.lcarx = contO.x;
    const n13 = contO.y - this.lcary;
    this.lcary = contO.y;
    const n14 = contO.z - this.lcarz;
    this.lcarz = contO.z;
    const n15 = fr(fr(fr(fr(fr(fr(fr(Math.sqrt(i32(Math.imul(n12, n12) + Math.imul(n14, n14)))) * 1.4) * 21.0) * 60.0) * 60.0) / 100000.0));
    const n16 = fr(n15 * 0.621371);
    this.rd.setColor(0, 0, 100);
    this.rd.drawString("" + trunc(n15), 62, 245);
    this.rd.drawString("" + trunc(n16), 132, 245);
  }

  /**
   * Pick the opponent field for stage `n` — xtGraphics.java:7052.
   *
   * The original never uses one car for the whole grid: it draws sc[1..6] by
   * rejection sampling, biased so that later stages field faster cars, then
   * forces specific opponents in for certain stages (stage 10 wants a Radical
   * One and a Nimi, 12 wants a Radical One, 14 wants a Formula 7 and an
   * M.A.S.H.E.E.N., and so on).
   *
   * The rejection test is `(15 - sc[j]) / 15 * (n / 10)` capped at 0.8: a
   * low-numbered (slow) car is rejected more often the later the stage, so
   * the grid drifts upward through the roster without ever being fixed.
   *
   * `sc[0]` — the player's car — must be set BEFORE calling: several branches
   * avoid duplicating it.
   *
   * Duplicates are rejected across all 7 slots, so this loop only terminates
   * because the pool is larger than the grid. Slot 7 is untouched: the
   * original grid is 7 cars and this port allows 8.
   */
  sortcars(n) {
    if (n === 0) return;
    for (let i = 1; i < 7; ++i) {
      this.sc[i] = -1;
    }
    const array = new Array(7).fill(false);
    if (n < 0) {
      n = 27;
    }
    // PORT DIVERGENCE. Past stage 27 both pickers run off the 16-car roster
    // (forced opponent 7+(n-10+1)/2 = 16 at stage 28; the sampler reaches 18),
    // and loadstage then dies on a null model. The Java can't reach it: stage
    // select clamps at 27 (:1899, :2597) and 28-32 are the multiplayer stages,
    // where the grid comes from the network and sortcars is never called. This
    // port lets you pick them solo, so treat them as top difficulty.
    if (n > 27) {
      n = 27;
    }
    let n2 = 7;
    if (this.gmode === 1) {
      n2 = 5;
    }
    let b = false;
    if (n <= 10) {
      let n3 = 6;
      if (this.gmode === 1) {
        n3 = 4;
      }
      if ((n === 1 || n === 2) && this.sc[0] !== 5) {
        this.sc[n3] = 5;
        n2 = n3;
      }
      if ((n === 3 || n === 4) && this.sc[0] !== 6) {
        this.sc[n3] = 6;
        n2 = n3;
      }
      if ((n === 5 || n === 6) && this.sc[0] !== 11) {
        this.sc[n3] = 11;
        n2 = n3;
      }
      if ((n === 7 || n === 8) && this.sc[0] !== 14) {
        this.sc[n3] = 14;
        n2 = n3;
      }
      if ((n === 9 || n === 10) && this.sc[0] !== 15) {
        this.sc[n3] = 15;
        n2 = n3;
      }
    } else {
      n -= 10;
      b = true;
      if (this.sc[0] !== 7 + idiv(n + 1, 2) && n !== 17) {
        this.sc[6] = 7 + idiv(n + 1, 2);
        n2 = 6;
      }
    }
    let n4 = 16;
    let n5 = 1;
    let n6 = 2;
    for (let j = 1; j < n2; ++j) {
      array[j] = false;
      while (!array[j]) {
        let n7 = 10.0;
        if (b) {
          n7 = 17.0;
        }
        this.sc[j] = trunc(random() * fr(24.0 + fr(8.0 * fr(n / n7))));
        if (this.sc[j] >= 16) {
          this.sc[j] -= 16;
        }
        array[j] = true;
        for (let k = 0; k < 7; ++k) {
          if (j !== k && this.sc[j] === this.sc[k]) {
            array[j] = false;
          }
        }
        // n7 is reassigned here, AFTER it was used to pick the car and
        // BEFORE it is used to weight the rejection. Not a decompiler
        // artifact -- the two uses genuinely differ for the bonus stages.
        if (b) {
          n7 = 16.0;
        }
        let n9 = fr(fr((15 - this.sc[j]) / 15.0) * fr(n / n7));
        if (n9 > 0.8) {
          n9 = 0.8;
        }
        if (n === 17 && n9 > 0.5) {
          n9 = 0.5;
        }
        if (n9 > random()) {
          array[j] = false;
        }
        if (this.gmode === 1) {
          if (this.sc[j] >= 7 && this.sc[j] <= 10) array[j] = false;
          if (this.sc[j] === 12 || this.sc[j] === 13) array[j] = false;
          if (this.sc[j] > 5 && this.unlocked[0] <= 2) array[j] = false;
          if (this.sc[j] > 6 && this.unlocked[0] <= 4) array[j] = false;
          if (this.sc[j] > 11 && this.unlocked[0] <= 6) array[j] = false;
          if (this.sc[j] > 14 && this.unlocked[0] <= 8) array[j] = false;
        }
        if (this.gmode === 2) {
          if ((this.sc[j] - 7) * 2 > this.unlocked[1]) {
            array[j] = false;
          }
          if (n !== 16 || this.unlocked[1] !== 16 || this.sc[j] >= 9) {
            continue;
          }
          array[j] = false;
        }
      }
      // n5/n6 track the two slots holding the slowest cars; the forced
      // opponents below overwrite those rather than a random slot.
      if (this.sc[j] < n4) {
        n4 = this.sc[j];
        if (n5 !== j) {
          n6 = n5;
          n5 = j;
        }
      }
    }
    const has = (car) => {
      for (let i = 0; i < 7; ++i) {
        if (this.sc[i] === car) return true;
      }
      return false;
    };
    if (!b && n === 10) {
      if (!has(11) && (random() > random() || this.gmode !== 0)) this.sc[n5] = 11;
      if (!has(14) && (random() > random() || this.gmode !== 0)) this.sc[n6] = 14;
    }
    if (n === 12) {
      if (!has(11)) this.sc[n5] = 11;
    }
    if (n === 14) {
      if (!has(12) && (random() > random() || this.gmode !== 0)) this.sc[n5] = 12;
      if (!has(10) && (random() > random() || this.gmode !== 0)) this.sc[n6] = 10;
    }
    if (n === 15) {
      if (!has(11) && (random() > random() || this.gmode !== 0)) this.sc[n5] = 11;
      if (!has(13) && (random() > random() || this.gmode !== 0)) this.sc[n6] = 13;
    }
    if (n === 16) {
      if (!has(13) && (random() > random() || this.gmode !== 0)) this.sc[n5] = 13;
      if (!has(12) && (random() > random() || this.gmode !== 0)) this.sc[n6] = 12;
    }
    // Custom-car packs. lastload is 0 in the race-only harness, so neither
    // branch runs; kept so loading a pack later behaves as the original.
    if (this.cd.lastload === 1) {
      let n18 = 0;
      for (let n19 = 0; n19 < this.cd.nlcars - 16; ++n19) {
        if (n18 === 0) {
          for (let n20 = 1; n20 < n2; ++n20) array[n20] = false;
        }
        if (this.cd.include[n19] && this.sc[0] !== n19 + 16) {
          let n21;
          for (n21 = trunc(1.0 + random() * (n2 - 1)); array[n21]; n21 = trunc(1.0 + random() * (n2 - 1))) {}
          array[n21] = true;
          this.sc[n21] = n19 + 16;
          if (++n18 === n2 - 1) n18 = 0;
        }
      }
    }
    if (this.cd.lastload === 2) {
      let n22 = 0;
      for (let n23 = 0; n23 < this.cd.nlocars - 16; ++n23) {
        if (n22 === 0) {
          for (let n24 = 1; n24 < n2; ++n24) array[n24] = false;
        }
        if (this.cd.include[n23] && this.sc[0] !== n23 + 16) {
          let n25;
          for (n25 = trunc(1.0 + random() * (n2 - 1)); array[n25]; n25 = trunc(1.0 + random() * (n2 - 1))) {}
          array[n25] = true;
          this.sc[n25] = n23 + 16;
          if (++n22 === n2 - 1) n22 = 0;
        }
      }
    }
  }

  rot(array, array2, n, n2, n3, n4) {
    if (!this.m) return;
    if (n3 !== 0) {
      const cos = this.m.cos(n3);
      const sin = this.m.sin(n3);
      for (let i = 0; i < n4; ++i) {
        const n5 = array[i];
        const n6 = array2[i];
        array[i] = n + trunc(fr(fr((n5 - n) * cos) - fr((n6 - n2) * sin)));
        array2[i] = n2 + trunc(fr(fr((n5 - n) * sin) + fr((n6 - n2) * cos)));
      }
    }
  }

  xs(n, n2) {
    if (!this.m) return n;
    if (n2 < 50) {
      n2 = 50;
    }
    return idiv(Math.imul(n2 - this.m.focus_point, this.m.cx - n), n2) + n;
  }

  ys(n, n2) {
    if (!this.m) return n;
    if (n2 < 50) {
      n2 = 50;
    }
    return idiv(Math.imul(n2 - this.m.focus_point, this.m.cy - n), n2) + n;
  }

  py(n, n2, n3, n4) {
    return i32(Math.imul(n - n2, n - n2) + Math.imul(n3 - n4, n3 - n4));
  }

  pys(n, n2, n3, n4) {
    return fr(Math.sqrt(i32(Math.imul(n - n2, n - n2) + Math.imul(n3 - n4, n3 - n4))));
  }
}
