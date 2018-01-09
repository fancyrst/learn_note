package org.fancy.concurrent;


/**
 * MutablePoint
 * <p/>
 * Mutable Point class similar to java.awt.Point
 *
 * 非线程安全类
 */
public class MutablePoint {
    public int x, y;

    public MutablePoint() {
        x = 0;
        y = 0;
    }

    public MutablePoint(MutablePoint p) {
        this.x = p.x;
        this.y = p.y;
    }
}
