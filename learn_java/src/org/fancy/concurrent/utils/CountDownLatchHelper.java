package org.fancy.concurrent.utils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yeyx on 2018/1/4.
 *
 * 同步工具辅助类
 *
 * A CountDownLatch是一种通用的同步工具，可用于多种用途。
 * 用途一：一个CountDownLatch为一个计数的CountDownLatch用作一个简单的开/关锁存器，或者门：所有线程调用await在门口等待，
 *      直到被调用countDown()的线程打开。 一个CountDownLatch初始化N可以用来做一个线程等待，直到N个线程完成某项操作，
 *      或某些动作已经完成N次。
 *
 * 用途二：CountDownLatch一个有用的属性是，它不要求调用countDown线程等待计数到达零之前继续，它只是阻止任何线程通过await ，
 *       直到所有线程可以通过。
 *
 *
 */
public class CountDownLatchHelper {

    public static void main(String args[]) {
        CountDownLatchHelper helper = new CountDownLatchHelper();
        helper.workMethodTwo();
    }

    private int poolSize = 10;
    final CountDownLatch start = new CountDownLatch(1);
    final CountDownLatch end = new CountDownLatch(poolSize);

    ExecutorService executorService = null;

    /**
     * 用途二：使一个线程等待其它线程完成各自的工作后在执行。
     * 场景之一：使用CountDownLatch能够保证压力测试并发、并行场景，多个线程同一时刻执行。
     */
    public void workMethodTwo() {
        try {
            executorService = Executors.newFixedThreadPool(poolSize);
            for (int i = 0; i < poolSize; i++) {
                executorService.submit(new WorkRunable());
            }

            start.countDown();
            end.await();
            executorService.shutdown();
        } catch (InterruptedException ie) {

        }
    }

    class WorkRunable implements Runnable {

        public void run() {
            try {
                start.await();
                System.out.println(Thread.currentThread().getName() + " are doing something...");
            } catch (InterruptedException ie) {

            } finally {
                end.countDown();
            }

        }
    }
}
