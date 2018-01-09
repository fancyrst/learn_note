package org.fancy.concurrent.atomic;


/**
 * SynchronizedInteger
 * <p/>
 * Thread-safe mutable integer holder
 *
 * 线程安全类
 */
public class SynchronizedInteger {
    private int value;

    public synchronized int get() {
        return value;
    }

    public synchronized void set(int value) {
        this.value = value;
    }
}
