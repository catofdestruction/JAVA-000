package org.xy.spring.impl;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.xy.spring.source.ApplicationContextReturnable;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.xy.spring.source.RRWWB.TIMEOUT;

/**
 * count down latch
 *
 * @author wangxinyu
 * @date 2020/11/15
 */
@Order(10)
@Component
public class CountDownLatch extends ApplicationContextReturnable {

    @Override
    public Integer doReturn() {
        AtomicInteger result = new AtomicInteger();
        java.util.concurrent.CountDownLatch countDownLatch = new java.util.concurrent.CountDownLatch(1);
        new Thread(() -> {
            try {
                result.set(fibo40());
            } finally {
                countDownLatch.countDown();
            }
        }, name(false)).start();
        try {
            countDownLatch.await(TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result.get();
    }
}