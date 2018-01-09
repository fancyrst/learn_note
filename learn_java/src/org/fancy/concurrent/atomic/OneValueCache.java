package org.fancy.concurrent.atomic;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * OneValueCache
 * <p/>
 * Immutable holder for caching a number and its factors
 *
 *
 */

public class OneValueCache {
    private final BigInteger lastNumber;//final 修饰对象表示引用不可变
    private final BigInteger[] lastFactors;

    public OneValueCache(BigInteger i,
                         BigInteger[] factors) {
        lastNumber = i;
        lastFactors = Arrays.copyOf(factors, factors.length);
    }

    public BigInteger[] getFactors(BigInteger i) {
        if (lastNumber == null || !lastNumber.equals(i))
            return null;
        else
            return Arrays.copyOf(lastFactors, lastFactors.length);
    }
}
