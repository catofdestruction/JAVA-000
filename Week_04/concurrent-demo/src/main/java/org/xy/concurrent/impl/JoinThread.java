package org.xy.concurrent.impl;

import org.springframework.stereotype.Component;
import org.xy.concurrent.source.Returnable;

import static org.xy.concurrent.source.RRWWB.TIMEOUT;

/**
 * join thread
 *
 * @author wangxinyu
 * @date 2020/11/11
 */
@Component
public class JoinThread implements Returnable<Integer> {

    private volatile Integer result = null;

    @Override
    public Integer doReturn() {
        Thread thread = new Thread(() -> result = fibo40(), name());
        thread.start();
        try {
            thread.join(TIMEOUT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}