package org.fancy.concurrent.atomic;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * UnsafeCachingFactorizer
 *
 * Servlet that attempts to cache its last result without adequate atomicity
 *
 * 非线程安全类，不变性的条件之一是：
 * 在lastFactors中缓存的因数之积应该等于在lastNumber中缓存的数值。不变性被破坏
 */

public class UnsafeCachingFactorizer extends GenericServlet implements Servlet {
	
	private static final long serialVersionUID = 6562849058220943549L;
	
	private final AtomicReference<BigInteger> lastNumber
            = new AtomicReference<BigInteger>();// 虽然使用了AtomicReference（本身线程安全），但是存在竞态条件。
	
    private final AtomicReference<BigInteger[]> lastFactors
            = new AtomicReference<BigInteger[]>();

    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        if (i.equals(lastNumber.get()))
            encodeIntoResponse(resp, lastFactors.get());
        else {
            BigInteger[] factors = factor(i);
            lastNumber.set(i);
            lastFactors.set(factors);
            encodeIntoResponse(resp, factors);
        }
    }

    void encodeIntoResponse(ServletResponse resp, BigInteger[] factors) {
    }

    BigInteger extractFromRequest(ServletRequest req) {
        return new BigInteger("7");
    }

    BigInteger[] factor(BigInteger i) {
        // Doesn't really factor
        return new BigInteger[]{i};
    }
}

