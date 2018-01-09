package org.fancy.concurrent.cancel;

import java.math.BigInteger;
import java.util.concurrent.*;

/**
 * PrimeProducer
 * <p/>
 * Using interruption for cancellation
 *
 * 使用中断(interrupt)而不是标志来请求取消
 */
public class PrimeProducer extends Thread {
    private final BlockingQueue<BigInteger> queue;

    PrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            while (!Thread.currentThread().isInterrupted())// put()阻塞也会通过中断来取消生产者
                queue.put(p = p.nextProbablePrime());// 生产者线程生成素数，并放入阻塞队列中
        } catch (InterruptedException consumed) {//屏蔽中断，因为它知道线程将要结束。一般情况下，不允许屏蔽InterruptedException
            /* Allow thread to exit */
        }
    }

    public void cancel() {
        interrupt();
    }
}
