// A Graphics2D that records the four calls Extended's engine draws with
// (setColor, fillPolygon, drawPolygon, fillRect) as JSON ops, and ignores the
// rest. Used by ExtDrawProbe; web/ext/draw.test.js records the port the same
// way and compares op by op. The no-op overrides are generated from
// Graphics2D's abstract methods.

import java.awt.*;

public class RecG extends Graphics2D {
    final StringBuilder out = new StringBuilder();
    Color color = Color.black;
    int n = 0;

    void op(String s) { if (n++ > 0) out.append(','); out.append(s); }

    static String arr(int[] a, int n) {
        StringBuilder b = new StringBuilder("[");
        for (int i = 0; i < n; i++) { if (i > 0) b.append(','); b.append(a[i]); }
        return b.append(']').toString();
    }

    @Override public void setColor(Color c) {
        color = c;
        op("[\"c\"," + c.getRed() + "," + c.getGreen() + "," + c.getBlue() + "," + c.getAlpha() + "]");
    }
    @Override public Color getColor() { return color; }
    @Override public void fillPolygon(int[] x, int[] y, int n) { op("[\"f\"," + arr(x, n) + "," + arr(y, n) + "]"); }
    @Override public void drawPolygon(int[] x, int[] y, int n) { op("[\"d\"," + arr(x, n) + "," + arr(y, n) + "]"); }
    @Override public void fillRect(int x, int y, int w, int h) { op("[\"r\"," + x + "," + y + "," + w + "," + h + "]"); }

    @Override public void scale(double p0, double p1) {  }
    @Override public void fill(java.awt.Shape p0) {  }
    @Override public void transform(java.awt.geom.AffineTransform p0) {  }
    @Override public void rotate(double p0, double p1, double p2) {  }
    @Override public void rotate(double p0) {  }
    @Override public boolean hit(java.awt.Rectangle p0, java.awt.Shape p1, boolean p2) { return false; }
    @Override public java.awt.Paint getPaint() { return null; }
    @Override public void setPaint(java.awt.Paint p0) {  }
    @Override public void draw(java.awt.Shape p0) {  }
    @Override public boolean drawImage(java.awt.Image p0, java.awt.geom.AffineTransform p1, java.awt.image.ImageObserver p2) { return false; }
    @Override public void drawImage(java.awt.image.BufferedImage p0, java.awt.image.BufferedImageOp p1, int p2, int p3) {  }
    @Override public void drawRenderedImage(java.awt.image.RenderedImage p0, java.awt.geom.AffineTransform p1) {  }
    @Override public void drawRenderableImage(java.awt.image.renderable.RenderableImage p0, java.awt.geom.AffineTransform p1) {  }
    @Override public void drawString(java.lang.String p0, float p1, float p2) {  }
    @Override public void drawString(java.lang.String p0, int p1, int p2) {  }
    @Override public void drawString(java.text.AttributedCharacterIterator p0, float p1, float p2) {  }
    @Override public void drawString(java.text.AttributedCharacterIterator p0, int p1, int p2) {  }
    @Override public void drawGlyphVector(java.awt.font.GlyphVector p0, float p1, float p2) {  }
    @Override public java.awt.GraphicsConfiguration getDeviceConfiguration() { return null; }
    @Override public void setComposite(java.awt.Composite p0) {  }
    @Override public void setStroke(java.awt.Stroke p0) {  }
    @Override public void setRenderingHint(java.awt.RenderingHints.Key p0, java.lang.Object p1) {  }
    @Override public java.lang.Object getRenderingHint(java.awt.RenderingHints.Key p0) { return null; }
    @Override public void setRenderingHints(java.util.Map p0) {  }
    @Override public void addRenderingHints(java.util.Map p0) {  }
    @Override public java.awt.RenderingHints getRenderingHints() { return null; }
    @Override public void translate(double p0, double p1) {  }
    @Override public void translate(int p0, int p1) {  }
    @Override public void shear(double p0, double p1) {  }
    @Override public void setTransform(java.awt.geom.AffineTransform p0) {  }
    @Override public java.awt.geom.AffineTransform getTransform() { return null; }
    @Override public java.awt.Composite getComposite() { return null; }
    @Override public void setBackground(java.awt.Color p0) {  }
    @Override public java.awt.Color getBackground() { return null; }
    @Override public java.awt.Stroke getStroke() { return null; }
    @Override public void clip(java.awt.Shape p0) {  }
    @Override public java.awt.font.FontRenderContext getFontRenderContext() { return null; }
    @Override public java.awt.Graphics create() { return null; }
    @Override public void dispose() {  }
    @Override public boolean drawImage(java.awt.Image p0, int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8, java.awt.image.ImageObserver p9) { return false; }
    @Override public boolean drawImage(java.awt.Image p0, int p1, int p2, java.awt.image.ImageObserver p3) { return false; }
    @Override public boolean drawImage(java.awt.Image p0, int p1, int p2, java.awt.Color p3, java.awt.image.ImageObserver p4) { return false; }
    @Override public boolean drawImage(java.awt.Image p0, int p1, int p2, int p3, int p4, java.awt.image.ImageObserver p5) { return false; }
    @Override public boolean drawImage(java.awt.Image p0, int p1, int p2, int p3, int p4, int p5, int p6, int p7, int p8, java.awt.Color p9, java.awt.image.ImageObserver p10) { return false; }
    @Override public boolean drawImage(java.awt.Image p0, int p1, int p2, int p3, int p4, java.awt.Color p5, java.awt.image.ImageObserver p6) { return false; }
    @Override public void clipRect(int p0, int p1, int p2, int p3) {  }
    @Override public java.awt.Font getFont() { return null; }
    @Override public java.awt.FontMetrics getFontMetrics(java.awt.Font p0) { return null; }
    @Override public void drawLine(int p0, int p1, int p2, int p3) {  }
    @Override public java.awt.Rectangle getClipBounds() { return null; }
    @Override public void setPaintMode() {  }
    @Override public void setXORMode(java.awt.Color p0) {  }
    @Override public void setFont(java.awt.Font p0) {  }
    @Override public void setClip(int p0, int p1, int p2, int p3) {  }
    @Override public void setClip(java.awt.Shape p0) {  }
    @Override public java.awt.Shape getClip() { return null; }
    @Override public void copyArea(int p0, int p1, int p2, int p3, int p4, int p5) {  }
    @Override public void clearRect(int p0, int p1, int p2, int p3) {  }
    @Override public void drawRoundRect(int p0, int p1, int p2, int p3, int p4, int p5) {  }
    @Override public void fillRoundRect(int p0, int p1, int p2, int p3, int p4, int p5) {  }
    @Override public void drawOval(int p0, int p1, int p2, int p3) {  }
    @Override public void fillOval(int p0, int p1, int p2, int p3) {  }
    @Override public void drawArc(int p0, int p1, int p2, int p3, int p4, int p5) {  }
    @Override public void fillArc(int p0, int p1, int p2, int p3, int p4, int p5) {  }
    @Override public void drawPolyline(int[] p0, int[] p1, int p2) {  }
}
