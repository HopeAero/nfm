import java.io.ByteArrayInputStream;

// 
// Decompiled by Procyon v0.6.0
// 

public class SuperStream extends ByteArrayInputStream
{
    public SuperStream(final byte[] abyte0) {
        super(abyte0);
    }
    
    @Override
    public int read() {
        int i = super.read();
        if (i == -1) {
            this.reset();
            i = super.read();
        }
        return i;
    }
    
    @Override
    public int read(final byte[] abyte0, final int i, final int j) {
        int k = 0;
        while (k < j) {
            final int l = super.read(abyte0, i + k, j - k);
            if (l >= 0) {
                k += l;
            }
            else {
                this.reset();
            }
        }
        return k;
    }
}
