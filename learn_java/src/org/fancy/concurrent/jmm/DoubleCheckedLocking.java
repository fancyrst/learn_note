package org.fancy.concurrent.jmm;


/**
 * DoubleCheckedLocking
 * <p/>
 * Double-checked-locking antipattern
 *
 * 非线程安全，双重检查加锁。如果对于共享对象加入volatile修饰可以避免
 */
public class DoubleCheckedLocking {
    private static Resource resource;//加入volatile

    public static Resource getInstance() {
        if (resource == null) {//在没有同步的情况下读取一个共享对象，可能只看到一个失效值（非原子性）。
            synchronized (DoubleCheckedLocking.class) {
                if (resource == null)
                    resource = new Resource();
            }
        }
        return resource;
    }

    static class Resource {

    }
}
