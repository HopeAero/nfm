import javax.sound.midi.MidiChannel;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.MidiSystem;
import java.io.IOException;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;
import java.io.InputStream;
import javazoom.jl.player.PausablePlayer;
import org.newdawn.easyogg.OggClip;
import java.io.File;
import java.io.FileInputStream;
import javax.sound.midi.Sequencer;
import java.io.BufferedInputStream;

// 
// Decompiled by Procyon v0.6.0
// 

public class RadicalMidi
{
    BufferedInputStream is;
    Sequencer sequencer;
    boolean paused;
    boolean loaded;
    boolean playing;
    boolean playingogg;
    boolean nooggloop;
    boolean pausedogg;
    boolean isMp3;
    boolean isOgg;
    String s;
    FileInputStream fi;
    File fl;
    OggClip ogg;
    PausablePlayer player;
    String filePath;
    
    public RadicalMidi(final String fn) {
        this.loaded = false;
        this.playing = false;
        this.playingogg = false;
        this.pausedogg = false;
        this.nooggloop = false;
        this.isMp3 = false;
        this.isOgg = false;
        if (fn.endsWith(".mp3")) {
            this.s = fn;
            this.isMp3 = true;
            this.isOgg = false;
            this.fl = new File(fn);
            try {
                this.fi = new FileInputStream(this.fl);
                this.player = new PausablePlayer(this.fi);
            }
            catch (final JavaLayerException | FileNotFoundException ex) {
                System.out.println("Error loading Mp3!");
                ex.printStackTrace();
            }
        }
        else if (fn.endsWith(".ogg")) {
            this.s = fn;
            this.isMp3 = false;
            this.isOgg = true;
            this.fl = new File(fn);
            try {
                this.fi = new FileInputStream(this.fl);
                this.ogg = new OggClip(this.fi);
            }
            catch (final IOException e) {
                System.out.println("Error loading Ogg!");
                e.printStackTrace();
            }
        }
        else {
            this.isMp3 = false;
            this.isOgg = false;
            this.s = fn;
            try {
                this.fi = new FileInputStream(new File(fn));
            }
            catch (final FileNotFoundException ex2) {
                System.out.println("Midi file \"" + fn + "\" not found!");
                ex2.printStackTrace();
            }
            try {
                (this.sequencer = MidiSystem.getSequencer()).open();
            }
            catch (final Exception ex) {
                System.out.println("Error loading Midi file \"" + fn + "\":");
                ex.printStackTrace();
            }
        }
    }
    
    public void load() {
        if (!this.isOgg && !this.isMp3) {
            this.loadMidi();
        }
    }
    
    public void play() {
        if (this.isMp3) {
            this.playMp3();
        }
        else if (this.isOgg) {
            this.ogg.loop();
            this.playingogg = true;
            this.nooggloop = false;
        }
        else {
            this.playMidi();
        }
    }
    
    public void play(final boolean once) {
        if (this.isMp3) {
            this.playMp3();
        }
        else if (this.isOgg) {
            if (once) {
                this.ogg.play();
                this.nooggloop = true;
            }
            else {
                this.ogg.loop();
                this.nooggloop = false;
            }
            this.playingogg = true;
        }
        else {
            this.playMidi();
        }
    }
    
    @Deprecated
    public void resume() {
        if (this.isMp3) {
            this.player.resume();
        }
        else if (this.isOgg) {
            this.ogg.resume();
            this.pausedogg = false;
            this.playingogg = true;
        }
        else {
            this.resumeMidi();
        }
    }
    
    public void setPaused(final boolean paused) {
        if (this.isOgg || this.isMp3) {
            if (paused) {
                if (this.isMp3) {
                    this.player.pause();
                }
                else if (this.isOgg) {
                    this.playingogg = false;
                    this.pausedogg = true;
                    this.ogg.pause();
                }
            }
            else if (this.isMp3) {
                this.player.resume();
            }
            else if (this.isOgg) {
                this.ogg.resume();
                this.playingogg = true;
                this.pausedogg = false;
            }
        }
        else if (this.paused != paused && this.sequencer != null && this.sequencer.isOpen()) {
            this.paused = paused;
            if (paused) {
                this.sequencer.stop();
            }
            else {
                this.sequencer.start();
            }
        }
    }
    
    @Deprecated
    public void stop() {
        if (this.isMp3) {
            this.player.pause();
        }
        else if (this.isOgg) {
            this.ogg.pause();
        }
        else {
            this.stopMidi();
        }
    }
    
    public void unload() {
        if (this.isMp3) {
            this.player.close();
        }
        else if (this.isOgg) {
            this.unloadOgg();
        }
        else {
            this.unloadMidi();
        }
    }
    
    public void loadMidi() {
        try {
            this.is = new BufferedInputStream(this.fi);
            this.loaded = true;
        }
        catch (final Exception ex) {
            System.out.println("Error buffering Midi file:");
            ex.printStackTrace();
        }
    }
    
    @Deprecated
    public void resumeMidi(final int gain, final int loops) {
        try {
            this.fi = new FileInputStream(new File(this.s));
            this.is = new BufferedInputStream(this.fi);
        }
        catch (final IOException ex) {
            System.out.println("Midi file not found!");
            ex.printStackTrace();
        }
        catch (final Exception ex2) {
            System.out.println("Error buffering Midi file:");
            ex2.printStackTrace();
        }
        this.playMidi(gain, loops);
    }
    
    @Deprecated
    public void resumeMidi(final int gain) {
        try {
            this.fi = new FileInputStream(new File(this.s));
            this.is = new BufferedInputStream(this.fi);
        }
        catch (final IOException ex) {
            System.out.println("Midi file not found!");
            ex.printStackTrace();
        }
        catch (final Exception ex2) {
            System.out.println("Error buffering Midi file:");
            ex2.printStackTrace();
        }
        this.playMidi(gain);
    }
    
    @Deprecated
    public void resumeMidi() {
        try {
            this.fi = new FileInputStream(new File(this.s));
            this.is = new BufferedInputStream(this.fi);
        }
        catch (final IOException ex) {
            System.out.println("Midi file not found!");
            ex.printStackTrace();
        }
        catch (final Exception ex2) {
            System.out.println("Error buffering Midi file:");
            ex2.printStackTrace();
        }
        this.playMidi();
    }
    
    public void playMidi(final int gain, final int loops) {
        try {
            this.sequencer.setSequence(this.is);
            this.sequencer.setLoopCount(loops);
            if (this.sequencer instanceof Synthesizer) {
                final Synthesizer synthesizer = (Synthesizer)this.sequencer;
                final MidiChannel[] channels = synthesizer.getChannels();
                for (int i = 0; i < channels.length; ++i) {
                    channels[i].controlChange(7, (int)((float)gain * 1.27));
                }
            }
            this.sequencer.start();
            this.playing = true;
        }
        catch (final IllegalArgumentException ex) {
            System.out.println("There is a mistake in your Midi code,");
            System.out.println("please re-check!");
            ex.printStackTrace();
        }
        catch (final IllegalStateException ex2) {
            System.out.println("Error playing Midi file " + this.s + ", check if the file exists!");
            ex2.printStackTrace();
        }
        catch (final Exception ex3) {
            System.out.println("Error playing Midi file:");
            ex3.printStackTrace();
        }
    }
    
    public void playMidi(final int gain) {
        try {
            this.sequencer.setSequence(this.is);
            this.sequencer.setLoopCount(9999);
            if (this.sequencer instanceof Synthesizer) {
                final Synthesizer synthesizer = (Synthesizer)this.sequencer;
                final MidiChannel[] channels = synthesizer.getChannels();
                for (int i = 0; i < channels.length; ++i) {
                    channels[i].controlChange(7, (int)((float)gain * 1.27));
                }
            }
            this.sequencer.start();
            this.playing = true;
        }
        catch (final IllegalArgumentException ex) {
            System.out.println("There is a mistake in your Midi code,");
            System.out.println("please re-check!");
            ex.printStackTrace();
        }
        catch (final IllegalStateException ex2) {
            System.out.println("Error playing Midi file " + this.s + ", check if the file exists!");
            ex2.printStackTrace();
        }
        catch (final Exception ex3) {
            System.out.println("Error playing Midi file:");
            ex3.printStackTrace();
        }
    }
    
    public void playMidi() {
        try {
            this.sequencer.setSequence(this.is);
            this.sequencer.setLoopCount(9999);
            this.sequencer.start();
            this.playing = true;
        }
        catch (final IllegalArgumentException ex) {
            System.out.println("There is a mistake in your Midi code,");
            System.out.println("please re-check!");
            ex.printStackTrace();
        }
        catch (final IllegalStateException ex2) {
            System.out.println("Error playing Midi file " + this.s + ", check if the file exists!");
            ex2.printStackTrace();
        }
        catch (final Exception ex3) {
            System.out.println("Error playing Midi file:");
            ex3.printStackTrace();
        }
    }
    
    public boolean isPaused() {
        return this.paused;
    }
    
    public void stopMidi() {
        System.out.println("Stopping Midi file...");
        try {
            this.sequencer.stop();
            this.playing = false;
        }
        catch (final Exception ex) {
            System.out.println("Error stopping Midi file:");
            ex.printStackTrace();
        }
    }
    
    public void unloadMidi() {
        try {
            this.is.close();
            this.loaded = false;
        }
        catch (final Exception ex) {
            System.out.println("Error unloading Midi file:");
            ex.printStackTrace();
        }
    }
    
    public void playMp3() {
        try {
            this.player.play();
        }
        catch (final JavaLayerException e) {
            e.printStackTrace();
        }
    }
    
    public void unloadOgg() {
        this.ogg.stop();
        this.ogg.close();
    }
}
