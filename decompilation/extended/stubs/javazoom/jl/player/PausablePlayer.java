package javazoom.jl.player;
import javazoom.jl.decoder.JavaLayerException;
// Compile-only stub: the jar's PausablePlayer was built by ECJ with unresolved
// types and names a default-package JavaLayerException; RadicalMidi catches
// the real javazoom one. Same member names as the jar's, so references match.
public class PausablePlayer {
  public PausablePlayer(java.io.InputStream s) throws JavaLayerException {}
  public void play() throws JavaLayerException {}
  public boolean pause() { return false; }
  public boolean resume() { return false; }
  public void stop() {}
  public void close() {}
}
