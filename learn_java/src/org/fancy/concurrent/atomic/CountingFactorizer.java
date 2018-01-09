package org.fancy.concurrent.atomic;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


/**
 * CountingFactorizer
 *
 * Servlet that counts requests using AtomicLong
 * 在非单例的类中，使用两种方式统计已请求的数量：
 * 1. 基本类型long
 * 2. AtomicLong(该类本身是线程安全的)类型
 *
 * 建议：应尽可能使用现有的线程安全对象来管理类的状态
 */
public class CountingFactorizer extends GenericServlet implements Servlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7884521826488137234L;
	private final AtomicLong count = new AtomicLong(0);// 线程安全
	//private long count = 0;//并发时线程不安全

    public long getCount() { return count.get(); }

    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        count.incrementAndGet();
//      count++; 此操作并不是原子操作，包括三个独立的操作：读取count值,将值加1，然后将计算结果写入count。读取->修改->写入的操作序列。
        encodeIntoResponse(resp, factors);
    }

    void encodeIntoResponse(ServletResponse res, BigInteger[] factors) {}
    BigInteger extractFromRequest(ServletRequest req) {return null; }
    BigInteger[] factor(BigInteger i) { return null; }
}
