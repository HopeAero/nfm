import java.io.EOFException;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.6.0
// 

public class Mod
{
    String name;
    int numtracks;
    int track_shift;
    int numpatterns;
    byte[][] patterns;
    ModInstrument[] insts;
    byte[] positions;
    int song_length_patterns;
    int song_repeat_patterns;
    boolean s3m;
    static final int voice_mk;
    static final int voice_mk2;
    static final int voice_mk3;
    static final int voice_flt4;
    static final int voice_flt8;
    static final int voice_28ch;
    static final int voice_8chn;
    static final int voice_6chn;
    static final int[] voice_31_list;
    
    static {
        voice_mk = FOURCC("M.K.");
        voice_mk2 = FOURCC("M!K!");
        voice_mk3 = FOURCC("M&K!");
        voice_flt4 = FOURCC("FLT4");
        voice_flt8 = FOURCC("FLT8");
        voice_28ch = FOURCC("28CH");
        voice_8chn = FOURCC("8CHN");
        voice_6chn = FOURCC("6CHN");
        voice_31_list = new int[] { Mod.voice_mk, Mod.voice_mk2, Mod.voice_mk3, Mod.voice_flt4, Mod.voice_flt8, Mod.voice_8chn, Mod.voice_6chn, Mod.voice_28ch };
    }
    
    public int getNumPatterns() {
        return this.numpatterns;
    }
    
    @Override
    public String toString() {
        return String.valueOf(this.name) + " (" + this.numtracks + " tracks, " + this.numpatterns + " patterns, " + this.insts.length + " samples)";
    }
    
    public Mod(final InputStream inputstream) {
        try {
            this.LoadMod(inputstream);
        }
        catch (final Exception exception) {
            System.out.println("Error loading up a Mod: " + exception);
        }
    }
    
    static final int readu16(final DataInputStream datainputstream) throws IOException {
        return datainputstream.readShort() & 0xFFFF;
    }
    
    static final String readText(final DataInputStream datainputstream, final int i) throws IOException {
        final byte[] abyte0 = new byte[i];
        datainputstream.readFully(abyte0, 0, i);
        for (int j = i - 1; j >= 0; --j) {
            if (abyte0[j] != 0) {
                return new String(abyte0, 0, 0, j + 1);
            }
        }
        return "";
    }
    
    void readSequence(final DataInputStream datainputstream) throws IOException {
        this.positions = new byte[128];
        this.song_length_patterns = readu8(datainputstream);
        this.song_repeat_patterns = readu8(datainputstream);
        datainputstream.readFully(this.positions, 0, 128);
        if (this.song_repeat_patterns > this.song_length_patterns) {
            this.song_repeat_patterns = this.song_length_patterns;
        }
        this.numpatterns = 0;
        for (int i = 0; i < this.positions.length; ++i) {
            if (this.positions[i] > this.numpatterns) {
                this.numpatterns = this.positions[i];
            }
        }
        ++this.numpatterns;
    }
    
    public void LoadMod(final InputStream inputstream) throws IOException {
        final DataInputStream datainputstream = new DataInputStream(inputstream);
        byte byte0 = 15;
        this.numtracks = 4;
        this.name = readText(datainputstream, 20);
        datainputstream.mark(1068);
        datainputstream.skip(1060L);
        final int i = datainputstream.readInt();
        datainputstream.reset();
        for (int j = 0; j < Mod.voice_31_list.length; ++j) {
            if (i == Mod.voice_31_list[j]) {
                byte0 = 31;
                break;
            }
        }
        if (byte0 == 31) {
            if (i == Mod.voice_8chn) {
                this.numtracks = 8;
            }
            else if (i == Mod.voice_6chn) {
                this.numtracks = 6;
            }
            else if (i == Mod.voice_28ch) {
                this.numtracks = 28;
            }
        }
        this.insts = new ModInstrument[byte0];
        for (int k = 0; k < byte0; ++k) {
            this.insts[k] = readInstrument(datainputstream);
        }
        this.readSequence(datainputstream);
        datainputstream.skipBytes(4);
        this.readPatterns(datainputstream);
        try {
            for (int l = 0; l < byte0; ++l) {
                readSampleData(datainputstream, this.insts[l]);
            }
        }
        catch (final EOFException _ex) {
            System.out.println("Warning: EOF on MOD file");
        }
        datainputstream.close();
        inputstream.close();
    }
    
    static void readSampleData(final DataInputStream datainputstream, final ModInstrument modinstrument) throws IOException {
        datainputstream.readFully(modinstrument.samples, 0, modinstrument.sample_length);
        if (modinstrument.repeat_length > 3) {
            System.arraycopy(modinstrument.samples, modinstrument.repeat_point, modinstrument.samples, modinstrument.sample_length, 8);
        }
    }
    
    static ModInstrument readInstrument(final DataInputStream datainputstream) throws IOException {
        final ModInstrument modinstrument = new ModInstrument();
        modinstrument.name = readText(datainputstream, 22);
        modinstrument.sample_length = readu16(datainputstream) << 1;
        modinstrument.samples = new byte[modinstrument.sample_length + 8];
        modinstrument.finetune_value = (byte)(readu8(datainputstream) << 4);
        modinstrument.volume = readu8(datainputstream);
        modinstrument.repeat_point = readu16(datainputstream) << 1;
        modinstrument.repeat_length = readu16(datainputstream) << 1;
        if (modinstrument.repeat_point > modinstrument.sample_length) {
            modinstrument.repeat_point = modinstrument.sample_length;
        }
        if (modinstrument.repeat_point + modinstrument.repeat_length > modinstrument.sample_length) {
            modinstrument.repeat_length = modinstrument.sample_length - modinstrument.repeat_point;
        }
        return modinstrument;
    }
    
    static final int FOURCC(final String s) {
        return (s.charAt(3) & '\u00ff') | (s.charAt(2) & '\u00ff') << 8 | (s.charAt(1) & '\u00ff') << 16 | (s.charAt(0) & '\u00ff') << 24;
    }
    
    public int getNumTracks() {
        return this.numtracks;
    }
    
    public String getName() {
        return this.name;
    }
    
    void readPatterns(final DataInputStream datainputstream) throws IOException {
        final int i = this.numtracks * 4 * 64;
        this.patterns = new byte[this.numpatterns][];
        for (int j = 0; j < this.numpatterns; ++j) {
            datainputstream.readFully(this.patterns[j] = new byte[i], 0, i);
        }
    }
    
    static final int readu8(final DataInputStream datainputstream) throws IOException {
        return datainputstream.readByte() & 0xFF;
    }
}
