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
}
