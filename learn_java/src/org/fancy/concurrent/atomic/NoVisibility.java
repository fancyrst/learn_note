package org.fancy.concurrent.atomic;

/**
 * NoVisibility
 * <p/>
 * Sharing variables without synchronization
 *
 *	可见性
 */

public class NoVisibility {
	
    private static boolean ready = false;//共享变量，但没有同步
    private static int number = 0; //共享变量，但没有同步

    /**
     * 子线程
     */
    private static class ReaderThread extends Thread {
        public void run() {
            while (!ready)
                Thread.yield();
            System.out.println(number);//可能输出42或0，或者根本无法终止
        }
    }

    /**
     * 主线程
     * @param args
     */
    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }
}
