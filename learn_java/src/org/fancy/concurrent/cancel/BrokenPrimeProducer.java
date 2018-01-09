package org.fancy.concurrent.cancel;

import java.math.BigInteger;
import java.util.concurrent.*;

/**
 * BrokenPrimeProducer
 * <p/>
 * Unreliable cancellation that can leave producers stuck in a blocking operation
 * 不可靠的取消操作将把生产者置于阻塞的操作中，此处消费者没有实现
 * 问题根本原因：自定义的取消机制无法与阻塞的库函数实现良好的交互而导致死锁。
 *
 * 解决方法：使用中断(interrupt)而不是标志来请求取消，参加示例：PrimeProducer
 */
class BrokenPrimeProducer extends Thread {
	
    private final BlockingQueue<BigInteger> queue;//有容量限制的阻塞队列可能发生阻塞。消费者消费队列的素数
    
    private volatile boolean cancelled = false;

    BrokenPrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    /**
     * 如果生产者的速度大于消费者的速度，队列将被填满。当阻塞队列put()阻塞时，如果消费者希望取消生产者的任务，那么此时生产
     * 者却永远不能检查这个标志，因为它无法从put()中恢复过来（因为消费者此时已经停止从队列取出素数，所以put()将会一直阻塞
     * 状态。）
     */
    public void run() {
        try {
            BigInteger p = BigInteger.ONE;
            while (!cancelled)//可能永远也不能取消
                queue.put(p = p.nextProbablePrime());// 生产者线程生成素数，并放入阻塞队列中
        } catch (InterruptedException consumed) {
        }
    }

    public void cancel() {
        cancelled = true;
    }
}

