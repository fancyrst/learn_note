package org.fancy.concurrent.cancel;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 取消机制
 * 
 * ReaderThread
 * <p/>
 * Encapsulating nonstandard cancellation in a Thread by overriding interrupt
 * 通过改写interrupt方法将非标准的取消操作封装在Thread中
 * 
 * @author Brian Goetz and Tim Peierls
 */
public class ReaderThread extends Thread {
	
    private static final int BUFSZ = 512;
    private final Socket socket;
    private final InputStream in;

    public ReaderThread(Socket socket) throws IOException {
        this.socket = socket;
        this.in = socket.getInputStream();
    }

    public void interrupt() {
        try {
            socket.close();
        } catch (IOException ignored) {
        } finally {
            super.interrupt();
        }
    }

    public void run() {
        try {
            byte[] buf = new byte[BUFSZ];
            while (true) {
                int count = in.read(buf);
                if (count < 0)
                    break;
                else if (count > 0)
                    processBuffer(buf, count);
            }
        } catch (IOException e) { /* Allow thread to exit */
        }
    }

    public void processBuffer(byte[] buf, int count) {
    }
}
