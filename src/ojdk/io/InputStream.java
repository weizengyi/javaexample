package ojdk.io;

import java.io.Closeable;
import java.io.IOException;

public abstract class InputStream implements Closeable {

	private static final int MAX_SKIP_BUFFER_SIZE = 2048;

	public abstract int read() throws IOException;

	public int read(byte b[], int off, int len) throws IOException {

		if (b == null) {
			throw new NullPointerException();
		} else if (off < 0 || len < 0 || len > b.length - off)
			throw new IndexOutOfBoundsException();
		else if (len == 0) {
			return 0;
		}

		int c = read();
		if (c == -1) {
			return -1;
		}
		b[off] = (byte) c;
		int i = 1;
		try {
			for (; i < len; i++) {
				c = read();
				if (c == -1) {
					break;
				}
				b[off + i] = (byte) c;
			}
		} catch (IOException ee) {

		}

		return i;

	}

	public long skip(long n) throws IOException {
		if (n <= 0)
			return 0;
		long remaining = n;
		int nr;
		int size = (int) Math.min(MAX_SKIP_BUFFER_SIZE, remaining);
		byte[] skipBuffer = new byte[size];
		while (remaining > 0) {
			nr = read(skipBuffer, 0, (int) Math.min(size, remaining));
			if (nr < 0) {
				break;
			}
			remaining -= nr;
		}

		return n - remaining;

	}

}
