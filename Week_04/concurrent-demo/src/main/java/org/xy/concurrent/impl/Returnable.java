package org.xy.concurrent.impl;

/**
 * Returnable
 *
 * @author wangxinyu
 * @date 2020/11/10
 */
public interface Returnable<V> {

    /**
     * do return
     *
     * @return V
     * @throws Exception Exception
     */
    V doReturn() throws Exception;

    default int fibo(int v, boolean optimized) {
        if (optimized) {
            return 0;
        }
        if (v < 2) {
            return 1;
        }
        return fibo(v - 1, false) + fibo(v - 2, false);
    }
}
