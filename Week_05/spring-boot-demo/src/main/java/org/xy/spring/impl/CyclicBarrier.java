package org.xy.spring.impl;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.xy.spring.source.ApplicationContextReturnable;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

import static org.xy.spring.source.RRWWB.TIMEOUT;

/**
 * cyclic barrier
 *
 * @author wangxinyu
 * @date 2020/11/15
 */
@Order(11)
@Component
public class CyclicBarrier extends ApplicationContextReturnable {

    @Override
    public Integer doReturn() {
        AtomicInteger result = new AtomicInteger();
        // call back asynchronously
        java.util.concurrent.CyclicBarrier cyclicBarrier =
                new java.util.concurrent.CyclicBarrier(2, () -> result.set(fibo40()));
        new Thread(() -> {
            try {
                // +1
                cyclicBarrier.await(TIMEOUT, TimeUnit.MILLISECONDS);
            } catch (InterruptedException | BrokenBarrierException | TimeoutException e) {
                e.printStackTrace();
            }
        }, name(false)).start();
        try {
            // +1
            cyclicBarrier.await(TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | BrokenBarrierException | TimeoutException e) {
            e.printStackTrace();
        }
        return result.get();
    }
}
