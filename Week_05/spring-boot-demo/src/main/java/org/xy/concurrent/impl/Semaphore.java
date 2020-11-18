package org.xy.concurrent.impl;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.xy.concurrent.source.ApplicationContextReturnable;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * semaphore
 *
 * @author wangxinyu
 * @date 2020/11/15
 */
@Order(9)
@Component
public class Semaphore extends ApplicationContextReturnable {

    @Override
    public Integer doReturn() {
        java.util.concurrent.Semaphore semaphore = new java.util.concurrent.Semaphore(0, true);
        AtomicInteger result = new AtomicInteger();
        new Thread(() -> {
            try {
                result.set(fibo40());
            } finally {
                semaphore.release();
            }
        }, name(false)).start();
        semaphore.acquireUninterruptibly(1);
        return result.get();
    }
}
