package org.xy.concurrent.source;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.xy.concurrent.source.RRWWB.LINES;

/**
 * Returnable
 *
 * @author wangxinyu
 * @date 2020/11/10
 */
public interface Returnable<V> {

    Logger log = LoggerFactory.getLogger(Returnable.class);

    /**
     * do return
     *
     * @return V
     */
    V doReturn();

    /**
     * name
     *
     * @param pro pro
     * @return name
     */
    default String name(boolean pro) {
        return pro ? this.getClass().getTypeName() : this.getClass().getSimpleName();
    }

    /**
     * fibo
     *
     * @param v         v
     * @param optimized optimized
     * @return int
     */
    default int fibo(final int v, final boolean optimized) {
        // 0 1 1 2 3 5 8 13 21 34
        if (v <= 0) {
            return 0;
        }
        if (v <= 2) {
            return 1;
        }
        if (optimized) {
            int f0 = 0;
            int f1 = 1;
            int result = 0;
            int i = 2;
            while (i++ <= v) {
                result = f0 + f1;
                f0 = f1;
                f1 = result;
            }
            return result;
        }
        return fibo(v - 1, false) + fibo(v - 2, false);
    }

    /**
     * fibo 40 with recursion
     *
     * @return fibo 40 (102334155)
     */
    default int fibo40() {
        long start = System.currentTimeMillis();
        int result = fibo(40, false);
        log.warn("{} [{} in {}] compute fibo40 result: {} cost: {} ms {}",
                LINES, name(false), Thread.currentThread(), result, System.currentTimeMillis() - start, LINES);
        return result;
    }

    /**
     * optimized fibo 40
     *
     * @return fibo 40
     */
    default int optimizedFibo40() {
        long start = System.currentTimeMillis();
        int result = fibo(40, true);
        log.warn("{} [{} in {}] compute fibo40 result: {} cost: {} ms {}",
                LINES, name(false), Thread.currentThread(), result, System.currentTimeMillis() - start, LINES);
        return result;
    }
}
