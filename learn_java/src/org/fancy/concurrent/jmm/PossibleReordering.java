package org.fancy.concurrent.jmm;

/**
 * PossibleReordering
 * <p/>
 * Insufficiently synchronized program that can have surprising results
 * JMM:java 内存模型
 * 
 * 在没有正确同步的情况下，即使要推断最简单的并发程序的行为也是很困难的。可能产生奇怪的结果
 * 
 */
public class PossibleReordering {
    static int x = 0, y = 0;
    static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread one = new Thread(new Runnable() {
            public void run() {
                a = 1;
                x = b;
            }
        });
        Thread other = new Thread(new Runnable() {
            public void run() {
                b = 1;
                y = a;
            }
        });
        one.start();
        other.start();
        one.join();
        other.join();
        System.out.println("( " + x + "," + y + ")");
    }
}
