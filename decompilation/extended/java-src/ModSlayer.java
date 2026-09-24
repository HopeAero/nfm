import java.io.IOException;

// 
// Decompiled by Procyon v0.6.0
// 

public class ModSlayer
{
    static final String VERSION = "1.0";
    static final String COPYRIGHT = "";
    static final int EFF_VOL_SLIDE = 1;
    static final int EFF_PORT_DOWN = 2;
    static final int EFF_PORT_UP = 4;
    static final int EFF_VIBRATO = 8;
    static final int EFF_ARPEGGIO = 16;
    static final int EFF_PORT_TO = 32;
    static final int EFF_TREMOLO = 64;
    static final int EFF_RETRIG = 128;
    static final int MIX_BUF_SIZE = 2048;
    static final int DEF_TEMPO_NTSC = 6;
    static final int DEF_TEMPO_PAL = 6;
    static final int DEF_BPM_NTSC = 125;
    static final int DEF_BPM_PAL = 145;
    static final int MIDCRATE = 8448;
    static final int MAX_SAMPLES = 100;
    static final int MAX_TRACKS = 32;
    static final int S3M_MAGIC1 = 4122;
    static final int S3M_MAGIC2;
    static final int S3M_INSTR2;
    static final int[] normal_vol_adj;
    static final int[] loud_vol_adj;
    static final int[] sintable;
    static final int[] period_set;
    static final int[] period_set_step;
    int def_tempo;
    int def_bpm;
    byte[] vol_table;
    int[] vol_adj;
    int vol_shift;
    Mod mod;
    int order_pos;
    int tempo;
    int tempo_wait;
    int bpm;
    int row;
    int break_row;
    int bpm_samples;
    int pattofs;
    byte[] patt;
    int numtracks;
    ModTrackInfo[] tracks;
    int mixspeed;
    boolean mod_done;
    public boolean bit16;
    public int samplingrate;
    public int oversample;
    public int gain;
    public int nloops;
    public boolean loud;
    static final byte[] sunfmt;
    private static final int ERROR_SHIFT = 12;
    private static final int ERROR_MASK = 4095;
    private static final long ratediv = 22748294283264L;
    int oln;
    
    static {
        S3M_MAGIC2 = Mod.FOURCC("SCRM");
        S3M_INSTR2 = Mod.FOURCC("SCRS");
        normal_vol_adj = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 63 };
        loud_vol_adj = new int[] { 0, 0, 1, 2, 2, 3, 3, 4, 5, 6, 7, 8, 9, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 42, 44, 46, 47, 48, 49, 50, 51, 52, 53, 53, 54, 55, 55, 56, 56, 57, 57, 58, 58, 59, 59, 60, 60, 61, 61, 61, 62, 62, 62, 63, 63, 63, 63, 63, 63 };
        sintable = new int[] { 0, 25, 50, 74, 98, 120, 142, 162, 180, 197, 212, 225, 236, 244, 250, 254, 255, 254, 250, 244, 236, 225, 212, 197, 180, 162, 142, 120, 98, 74, 50, 25 };
        period_set = new int[] { 1712, 1616, 1525, 1440, 1359, 1283, 1211, 1143, 1078, 1018, 961, 907, 856, 808, 763, 720, 679, 641, 605, 571, 539, 509, 480, 453, 428, 404, 381, 360, 340, 321, 303, 286, 270, 254, 240, 227, 214, 202, 191, 180, 170, 160, 151, 143, 135, 127, 120, 113, 107, 101, 95, 90, 85, 80, 76, 71, 67, 64, 60, 57, 53, 50, 48, 45, 42, 40, 38, 36, 34, 32, 30, 28, 27, 25, 24, 22, 21, 20, 19, 18, 17, 16, 15, 14 };
        period_set_step = new int[] { 1664, 1570, 1482, 1399, 1321, 1247, 1177, 1110, 1048, 989, 934, 881, 832, 785, 741, 699, 660, 623, 588, 555, 524, 494, 466, 440, 416, 392, 370, 350, 330, 312, 294, 278, 262, 247, 233, 220, 208, 196, 185, 175, 165, 155, 147, 139, 131, 123, 116, 110, 104, 98, 92, 87, 82, 78, 73, 69, 65, 62, 58, 55, 51, 49, 46, 43, 41, 39, 37, 35, 33, 31, 29, 27, 26, 24, 23, 21, 20, 19, 18, 17, 16, 15, 14, 14 };
        sunfmt = new byte[] { 46, 115, 110, 100, 0, 0, 0, 24, 127, 127, 127, 127, 0, 0, 0, 1, 0, 0, 31, 76, 0, 0, 0, 1, 0, 0, 0, 0 };
    }
    
    final void beattrack(final ModTrackInfo modtrackinfo) {
        if (modtrackinfo.period_low_limit == 0) {
            modtrackinfo.period_low_limit = 1;
        }
        if ((modtrackinfo.effect & 0x1) != 0x0) {
            modtrackinfo.volume += modtrackinfo.vol_slide;
            if (modtrackinfo.volume < 0) {
                modtrackinfo.volume = 0;
            }
            if (modtrackinfo.volume > 64) {
                modtrackinfo.volume = 64;
            }
        }
        if ((modtrackinfo.effect & 0x2) != 0x0) {
            if ((modtrackinfo.period += modtrackinfo.port_down) > modtrackinfo.period_high_limit) {
                modtrackinfo.period = modtrackinfo.period_high_limit;
            }
            modtrackinfo.pitch = modtrackinfo.finetune_rate / modtrackinfo.period;
        }
        if ((modtrackinfo.effect & 0x4) != 0x0) {
            if ((modtrackinfo.period -= modtrackinfo.port_up) < modtrackinfo.period_low_limit) {
                if (this.mod.s3m) {
                    modtrackinfo.period = modtrackinfo.period_high_limit;
                }
                else {
                    modtrackinfo.period = modtrackinfo.period_low_limit;
                }
            }
            modtrackinfo.pitch = modtrackinfo.finetune_rate / modtrackinfo.period;
        }
        if ((modtrackinfo.effect & 0x20) != 0x0) {
            if (modtrackinfo.portto < modtrackinfo.period) {
                if ((modtrackinfo.period += modtrackinfo.port_inc) > modtrackinfo.portto) {
                    modtrackinfo.period = modtrackinfo.portto;
                }
            }
            else if (modtrackinfo.portto > modtrackinfo.period && (modtrackinfo.period -= modtrackinfo.port_inc) < modtrackinfo.portto) {
                modtrackinfo.period = modtrackinfo.portto;
            }
            modtrackinfo.pitch = modtrackinfo.finetune_rate / modtrackinfo.period;
        }
        if ((modtrackinfo.effect & 0x8) != 0x0) {
            modtrackinfo.vibpos += modtrackinfo.vib_rate << 2;
            int i = ModSlayer.sintable[modtrackinfo.vibpos >> 2 & 0x1F] * modtrackinfo.vib_depth >> 7;
            if ((modtrackinfo.vibpos & 0x80) != 0x0) {
                i = -i;
            }
            i += modtrackinfo.period;
            if (i < modtrackinfo.period_low_limit) {
                i = modtrackinfo.period_low_limit;
            }
            if (i > modtrackinfo.period_high_limit) {
                i = modtrackinfo.period_high_limit;
            }
            modtrackinfo.pitch = modtrackinfo.finetune_rate / i;
        }
        if ((modtrackinfo.effect & 0x10) != 0x0) {
            modtrackinfo.pitch = modtrackinfo.finetune_rate / modtrackinfo.arp[modtrackinfo.arpindex];
            ++modtrackinfo.arpindex;
            if (modtrackinfo.arpindex >= 3) {
                modtrackinfo.arpindex = 0;
            }
        }
    }
    
    final void mixtrack_16_mono(final ModTrackInfo modtrackinfo, final int[] ai, int i, int j) {
        final byte[] abyte0 = modtrackinfo.samples;
        int k = modtrackinfo.position;
        final int j2 = this.vol_adj[modtrackinfo.volume] * this.gain >> this.vol_shift + 8;
        int i2 = modtrackinfo.error;
        final int k2 = modtrackinfo.pitch & 0xFFF;
        final int l1 = modtrackinfo.pitch >> 12;
        if (modtrackinfo.replen < 3) {
            final int m = modtrackinfo.length;
            if (k >= m) {
                return;
            }
            final int j3 = i + j;
            if (modtrackinfo.pitch < 4096) {
                while (k < m) {
                    if (i >= j3) {
                        break;
                    }
                    final int n = i++;
                    ai[n] += (abyte0[k] * (4096 - i2) + abyte0[k + 1] * i2) * j2 >> 12;
                    k += l1 + ((i2 += k2) >> 12);
                    i2 &= 0xFFF;
                }
            }
            else {
                while (k < m && i < j3) {
                    final int n2 = i++;
                    ai[n2] += abyte0[k] * j2;
                    k += l1 + ((i2 += k2) >> 12);
                    i2 &= 0xFFF;
                }
            }
            modtrackinfo.error = i2;
            modtrackinfo.position = k;
        }
        else {
            final int i3 = modtrackinfo.replen + modtrackinfo.repeat;
            if (modtrackinfo.pitch < 4096) {
                while (j > 0) {
                    if (k >= i3) {
                        k -= modtrackinfo.replen;
                    }
                    final int n3 = i++;
                    ai[n3] += (abyte0[k] * (4096 - i2) + abyte0[k + 1] * i2) * j2 >> 12;
                    k += l1 + ((i2 += k2) >> 12);
                    i2 &= 0xFFF;
                    --j;
                }
            }
            else {
                while (j > 0) {
                    if (k >= i3) {
                        k -= modtrackinfo.replen;
                    }
                    final int n4 = i++;
                    ai[n4] += abyte0[k] * j2;
                    k += l1 + ((i2 += k2) >> 12);
                    i2 &= 0xFFF;
                    --j;
                }
            }
            modtrackinfo.error = i2;
            modtrackinfo.position = k;
        }
    }
    
    ModSlayer(final Mod mod1, final int i, final int j, final int k) {
        this.mod_done = false;
        this.nloops = 1;
        this.loud = false;
        this.oln = 0;
        this.samplingrate = i;
        this.gain = j;
        this.oversample = 1;
        this.mod = mod1;
        this.def_tempo = 6;
        this.def_bpm = k;
    }
    
    final void make_vol_table8() {
        this.vol_table = new byte[16640];
        int i = 0;
        do {
            this.vol_table[i] = (byte)(this.vol_adj[i >> 8] * (byte)i >> 8 + this.vol_shift);
        } while (++i < 16640);
    }
    
    public byte[] turnbytesNorm() throws IOException {
        this.bit16 = true;
        this.startplaying(this.loud);
        final int[] ai = new int[this.mixspeed];
        final int[] ai2 = new int[this.mixspeed];
        final byte[] abyte0 = new byte[18000000];
        this.oln = 0;
        while (!this.mod_done) {
            if (--this.tempo_wait > 0) {
                for (int l = 0; l < this.numtracks; ++l) {
                    this.beattrack(this.tracks[l]);
                }
            }
            else {
                this.updatetracks();
            }
            System.arraycopy(ai2, 0, ai, 0, this.bpm_samples);
            for (int i = 0; i < this.numtracks; ++i) {
                this.mixtrack_16_mono(this.tracks[i], ai, 0, this.bpm_samples);
            }
            int i2 = this.bpm_samples;
            if (this.oversample > 1) {
                int j1 = 0;
                i2 = this.bpm_samples / this.oversample;
                if (this.oversample == 2) {
                    for (int k = 0; k < i2; ++k) {
                        ai[k] = ai[j1] + ai[j1 + 1] >> 1;
                        j1 += 2;
                    }
                }
                else {
                    for (int m = 0; m < i2; ++m) {
                        int k2 = ai[j1++];
                        for (int l2 = 1; l2 < this.oversample; ++l2) {
                            k2 += ai[j1++];
                        }
                        ai[m] = k2 / this.oversample;
                    }
                }
            }
            if (this.oln + i2 < 18000000) {
                intToBytes16(ai, abyte0, i2, this.oln);
                this.oln += i2;
            }
        }
        ++this.oln;
        return abyte0;
    }
    
    final void updatetracks() {
        this.tempo_wait = this.tempo;
        if (this.row >= 64) {
            if (this.order_pos >= this.mod.song_length_patterns) {
                this.order_pos = 0;
                --this.nloops;
                if (this.nloops == 0) {
                    this.mod_done = true;
                }
            }
            this.row = this.break_row;
            this.break_row = 0;
            if (this.mod.positions[this.order_pos] == 255) {
                this.order_pos = 0;
                this.row = 0;
            }
            this.patt = this.mod.patterns[this.mod.positions[this.order_pos]];
            this.pattofs = this.row * 4 * this.numtracks;
            ++this.order_pos;
        }
        ++this.row;
        for (int i = 0; i < this.numtracks; ++i) {
            this.pattofs = this.get_track(this.tracks[i], this.patt, this.pattofs);
        }
    }
    
    final int get_track(final ModTrackInfo modtrackinfo, final byte[] abyte0, int i) {
        int j = abyte0[i] & 0xF0;
        int k = (abyte0[i++] & 0xF) << 8;
        k |= (abyte0[i++] & 0xFF);
        final int l = abyte0[i] & 0xF;
        j |= (abyte0[i++] & 0xF0) >> 4;
        int i2 = abyte0[i++];
        modtrackinfo.effect = 0;
        if (j != 0) {
            --j;
            final ModInstrument modinstrument = this.mod.insts[j];
            modtrackinfo.volume = modinstrument.volume;
            modtrackinfo.length = modinstrument.sample_length;
            modtrackinfo.repeat = modinstrument.repeat_point;
            modtrackinfo.replen = modinstrument.repeat_length;
            modtrackinfo.finetune_rate = modinstrument.finetune_rate;
            modtrackinfo.samples = modinstrument.samples;
            modtrackinfo.period_low_limit = modinstrument.period_low_limit;
            modtrackinfo.period_high_limit = modinstrument.period_high_limit;
        }
        if (k != 0) {
            modtrackinfo.portto = k;
            if (l != 3 && l != 5) {
                final int n = k;
                modtrackinfo.period = n;
                modtrackinfo.start_period = n;
                modtrackinfo.pitch = modtrackinfo.finetune_rate / k;
                modtrackinfo.position = 0;
            }
        }
        Label_0901: {
            if (l != 0 || i2 != 0) {
                switch (l) {
                    case 0: {
                        int j2 = 12;
                        while (modtrackinfo.period < ModSlayer.period_set[j2] && ++j2 < 48) {}
                        modtrackinfo.arp[0] = ModSlayer.period_set[j2];
                        modtrackinfo.arp[1] = ModSlayer.period_set[j2 + (i2 & 0xF)];
                        modtrackinfo.arp[2] = ModSlayer.period_set[j2 + ((i2 & 0xF0) >> 4)];
                        modtrackinfo.arpindex = 0;
                        modtrackinfo.effect |= 0x10;
                        break;
                    }
                    case 1: {
                        modtrackinfo.effect |= 0x4;
                        if (i2 != 0) {
                            modtrackinfo.port_up = i2;
                            break;
                        }
                        break;
                    }
                    case 2: {
                        modtrackinfo.effect |= 0x2;
                        if (i2 != 0) {
                            modtrackinfo.port_down = i2;
                            break;
                        }
                        break;
                    }
                    case 3: {
                        if (i2 != 0) {
                            modtrackinfo.port_inc = (i2 & 0xFF);
                        }
                        modtrackinfo.effect |= 0x20;
                        break;
                    }
                    case 4: {
                        if ((i2 & 0xF) != 0x0) {
                            modtrackinfo.vib_depth = (i2 & 0xF);
                        }
                        if ((i2 & 0xF0) != 0x0) {
                            modtrackinfo.vib_rate = (i2 & 0xF0) >> 4;
                        }
                        if (k != 0) {
                            modtrackinfo.vibpos = 0;
                        }
                        modtrackinfo.effect |= 0x8;
                        break;
                    }
                    case 9: {
                        if (i2 == 0) {
                            i2 = modtrackinfo.oldsampofs;
                        }
                        modtrackinfo.oldsampofs = i2;
                        modtrackinfo.position = (i2 & 0xFF) << 8;
                        break;
                    }
                    case 5: {
                        modtrackinfo.effect |= 0x20;
                    }
                    case 6: {
                        if (l == 6) {
                            modtrackinfo.effect |= 0x8;
                        }
                    }
                    case 10: {
                        modtrackinfo.vol_slide = ((i2 & 0xF0) >> 4) - (i2 & 0xF);
                        modtrackinfo.effect |= 0x1;
                        break;
                    }
                    case 12: {
                        if (i2 > 64 || i2 < 0) {
                            modtrackinfo.volume = 64;
                            break;
                        }
                        modtrackinfo.volume = i2;
                        break;
                    }
                    case 13: {
                        this.break_row = ((i2 & 0xF0) >> 4) * 10 + (i2 & 0xF);
                        this.row = 64;
                        break;
                    }
                    case 14: {
                        final int k2 = i2 & 0xF0;
                        i2 &= 0xF;
                        switch (k2) {
                            default: {
                                break Label_0901;
                            }
                            case 1: {
                                modtrackinfo.period += i2;
                                if (modtrackinfo.period > modtrackinfo.period_high_limit) {
                                    modtrackinfo.period = modtrackinfo.period_high_limit;
                                }
                                modtrackinfo.pitch = modtrackinfo.finetune_rate / modtrackinfo.period;
                                break Label_0901;
                            }
                            case 2: {
                                modtrackinfo.period -= i2;
                                if (modtrackinfo.period < modtrackinfo.period_low_limit) {
                                    modtrackinfo.period = modtrackinfo.period_low_limit;
                                }
                                modtrackinfo.pitch = modtrackinfo.finetune_rate / modtrackinfo.period;
                                break Label_0901;
                            }
                        }
                    }
                    case 15: {
                        if (i2 == 0) {
                            break;
                        }
                        i2 &= 0xFF;
                        if (i2 <= 32) {
                            this.tempo = i2;
                            this.tempo_wait = i2;
                            break;
                        }
                        this.bpm = i2;
                        this.bpm_samples = this.samplingrate / (103 * i2 >> 8) * this.oversample;
                        break;
                    }
                }
            }
        }
        return i;
    }
    
    final void startplaying(final boolean flag) {
        this.vol_adj = (flag ? ModSlayer.loud_vol_adj : ModSlayer.normal_vol_adj);
        this.mixspeed = this.samplingrate * this.oversample;
        this.order_pos = 0;
        final int def_tempo = this.def_tempo;
        this.tempo = def_tempo;
        this.tempo_wait = def_tempo;
        this.bpm = this.def_bpm;
        this.row = 64;
        this.break_row = 0;
        this.bpm_samples = this.samplingrate / (24 * this.bpm / 60) * this.oversample;
        this.numtracks = this.mod.numtracks;
        this.tracks = new ModTrackInfo[this.numtracks];
        for (int i = 0; i < this.tracks.length; ++i) {
            this.tracks[i] = new ModTrackInfo();
        }
        if (this.mod.s3m) {
            for (int j = 0; j < this.mod.insts.length; ++j) {
                final ModInstrument modinstrument = this.mod.insts[j];
                modinstrument.finetune_rate = (int)(428L * modinstrument.finetune_value << 8) / this.mixspeed;
                modinstrument.period_low_limit = 14;
                modinstrument.period_high_limit = 1712;
            }
        }
        else {
            for (int k = 0; k < this.mod.insts.length; ++k) {
                final ModInstrument modinstrument2 = this.mod.insts[k];
                modinstrument2.finetune_rate = (int)(22748294283264L / (this.mixspeed * (1536 - modinstrument2.finetune_value)));
                modinstrument2.period_low_limit = 113;
                modinstrument2.period_high_limit = 856;
            }
        }
        if (this.numtracks > 8) {
            this.vol_shift = 2;
        }
        else if (this.numtracks > 4) {
            this.vol_shift = 1;
        }
        else {
            this.vol_shift = 0;
        }
        if (!this.bit16) {
            this.make_vol_table8();
        }
    }
    
    public static void intToBytes16(final int[] ai, final byte[] abyte0, final int i, final int j) {
        int k = j;
        for (int l = 0; l < i; ++l) {
            if (ai[l] < -32767) {
                ai[l] = -32767;
            }
            if (ai[l] > 32767) {
                ai[l] = 32767;
            }
            abyte0[k++] = (byte)(ai[l] >> 8);
            abyte0[k] = (byte)(ai[l] & 0xFF);
        }
    }
    
    public byte[] turnbytesUlaw() throws IOException {
        this.bit16 = true;
        this.startplaying(this.loud);
        final int[] ai = new int[this.mixspeed];
        final int[] ai2 = new int[this.mixspeed];
        final int[] ai3 = new int[3200000];
        this.oln = 0;
        while (!this.mod_done) {
            if (--this.tempo_wait > 0) {
                for (int k1 = 0; k1 < this.numtracks; ++k1) {
                    this.beattrack(this.tracks[k1]);
                }
            }
            else {
                this.updatetracks();
            }
            System.arraycopy(ai2, 0, ai, 0, this.bpm_samples);
            for (int i = 0; i < this.numtracks; ++i) {
                this.mixtrack_16_mono(this.tracks[i], ai, 0, this.bpm_samples);
            }
            int l1 = this.bpm_samples;
            if (this.oversample > 1) {
                int i2 = 0;
                l1 = this.bpm_samples / this.oversample;
                if (this.oversample == 2) {
                    for (int j = 0; j < l1; ++j) {
                        ai[j] = ai[i2] + ai[i2 + 1] >> 1;
                        i2 += 2;
                    }
                }
                else {
                    for (int m = 0; m < l1; ++m) {
                        int k2 = ai[i2++];
                        for (int l2 = 1; l2 < this.oversample; ++l2) {
                            k2 += ai[i2++];
                        }
                        ai[m] = k2 / this.oversample;
                    }
                }
            }
            for (int l3 = 0; l3 < l1; ++l3) {
                if (this.oln < 3200000) {
                    ai3[this.oln] = ai[l3];
                    ++this.oln;
                }
            }
        }
        for (int i3 = 2; i3 < this.oln; ++i3) {
            ai3[i3] = (ai3[i3] + ai3[i3 - 2]) / 2;
        }
        for (int j2 = 57; j2 < this.oln; ++j2) {
            ai3[j2] = (ai3[j2] + ai3[j2] + ai3[j2 - 50]) / 3;
        }
        final byte[] abyte0 = new byte[this.oln];
        for (int j3 = 0; j3 < this.oln; ++j3) {
            abyte0[j3] = UlawUtils.linear2ulawclip(ai3[j3]);
        }
        return abyte0;
    }
}
