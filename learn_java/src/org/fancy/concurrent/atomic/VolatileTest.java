package org.fancy.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试volatile仅确保可见性，但不能确保原子性
 * 可用三种方法保证共享对象的原子性
 * 在java 1.5的java.util.concurrent.atomic包下提供了一些原子操作类，即对基本数据类型的 自增（加1操作），
 * 自减（减1操作）、以及加法操作（加一个数），减法操作（减一个数）进行了封装，保证这些操作是原子性操作。
 */
public class VolatileTest {
	
    public volatile int inc = 0;
//    public  AtomicInteger inc = new AtomicInteger(); 方法一：或将基本类型int -> AtomicInger(线程安全的)
    
    Lock lock = new ReentrantLock();//方法三：采用lock方式也可保证原子性
     
    public void increase() {//方法二：加入synchronized关键字即可保证原子性
        inc++;//有三个操作，读取 -> 修改(自增) -> 写入主存，只保证了读取（即可见性）
//        lock.lock();
//        try {
//            inc++;
//        } finally{
//            lock.unlock();
//        }
        
//        inc.getAndIncrement();
    }
     
    public static void main(String[] args) {
        final VolatileTest test = new VolatileTest();
        for(int i=0;i<10;i++){
            new Thread(){ 
                public void run() {
                    for(int j=0;j<1000;j++)
                        test.increase(); //十个线程对inc进行自增，无法确保原子性
                };
            }.start();
        }
         
        while(Thread.activeCount()>1)  //保证前面的线程都执行完
            Thread.yield();
        System.out.println(test.inc);//结果是不确定的，运行它会发现每次运行结果都不一致，都是一个小于10000的数字。
    }

}
