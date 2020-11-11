package org.xy.concurrent.impl;

import org.springframework.stereotype.Component;
import org.xy.concurrent.source.Returnable;

/**
 * simple thread
 *
 * @author wangxinyu
 * @date 2020/11/11
 */
@Component
public class SimpleThread implements Returnable<Integer> {

    private volatile Integer result = null;

    @Override
    public Integer doReturn() {
        Thread thread = new Thread(() -> result = fibo40(), name());
        thread.start();
        while (result == null) {}
        return result;
    }
}
