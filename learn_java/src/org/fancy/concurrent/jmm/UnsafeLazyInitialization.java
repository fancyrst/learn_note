package org.fancy.concurrent.jmm;


/**
 * UnsafeLazyInitialization
 * <p/>
 * Unsafe lazy initialization
 *
 * 非线程安全，不安全的延迟初始化
 */
public class UnsafeLazyInitialization {
    private static Resource resource;

    public static Resource getInstance() {
        if (resource == null)
            resource = new Resource(); // unsafe publication，线程A产生实例未写入，线程B获取到null值
        return resource;
    }

    static class Resource {
    }
}
