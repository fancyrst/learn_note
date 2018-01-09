package org.fancy.concurrent.cancel;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.*;

/**
 * LogWriter
 * <p/>
 * Producer-consumer logging service with no shutdown support
 * 不支持关闭的生产者 - 消费者日志服务（多个生产者，一个消费者）
 * 生产日志（应用中调用日志）并存入queue，消费日志：从queue中拿出日志输出到文件流中
 * 
 * 问题：需提供终止日志线程的方法，从而避免使JVM无法正常关闭。
 * 解决：当取消一个生产者 - 消费者时，需要同时取消生产者和消费者。参考：LogService
 */
public class LogWriter {
	
    private final BlockingQueue<String> queue;
    
    private final LoggerThread logger;
    
    private static final int CAPACITY = 1000;

    public LogWriter(Writer writer) {
        this.queue = new LinkedBlockingQueue<String>(CAPACITY);
        this.logger = new LoggerThread(writer);
    }

    public void start() {
        logger.start();
    }

    public void log(String msg) throws InterruptedException {
        queue.put(msg);
    }

    private class LoggerThread extends Thread {
        private final PrintWriter writer;

        public LoggerThread(Writer writer) {
            this.writer = new PrintWriter(writer, true); // autoflush
        }

        public void run() {
            try {
                while (true)
                    writer.println(queue.take());
            } catch (InterruptedException ignored) {
            	//如果捕获到中断就退出，会丢失那些正在队列等待被写入到日志信息，其他线程将在调用log时被阻塞（因为日志消息队列是满的）
            } finally {
                writer.close();
            }
        }
    }
}
