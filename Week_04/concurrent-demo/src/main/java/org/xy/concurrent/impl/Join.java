package org.xy.concurrent.impl;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.xy.concurrent.source.ApplicationContextReturnable;

import static org.xy.concurrent.source.RRWWB.TIMEOUT;

/**
 * join
 *
 * @author wangxinyu
 * @date 2020/11/11
 */
@Order(2)
@Component
public class Join extends ApplicationContextReturnable {

    private volatile Integer result = null;

    @Override
    public Integer doReturn() {
        Thread thread = new Thread(() -> result = fibo40(), name(false));
        thread.start();
        try {
            thread.join(TIMEOUT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}