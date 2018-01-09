package org.fancy.concurrent.cancel;

import static java.util.concurrent.TimeUnit.SECONDS;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.*;

/**
 * PrimeGenerator
 * <p/>
 * Using a volatile field to hold cancellation state
 *  线程安全：使用volatile类型域来保证取消状态
 *  取消任务协作机制1：取消任务使用协作机制，通过一个线程来通知另外一个线程终止当前的工作。
 */
public class PrimeGenerator implements Runnable {
    private static ExecutorService exec = Executors.newCachedThreadPool();

    private final List<BigInteger> primes = new ArrayList<BigInteger>();
    private volatile boolean cancelled;//使用volatile类型域来保证取消状态

    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!cancelled) {
            p = p.nextProbablePrime();
            synchronized (this) {
                primes.add(p);
            }
        }
    }

    public void cancel() {
        cancelled = true;
    }

    public synchronized List<BigInteger> get() {
        return new ArrayList<BigInteger>(primes);
    }

    static List<BigInteger> aSecondOfPrimes() throws InterruptedException {
        PrimeGenerator generator = new PrimeGenerator();
        exec.execute(generator);
        try {
            SECONDS.sleep(1);
        } finally {
            generator.cancel();
        }
        return generator.get();
    }
}
