package org.xy.concurrent.impl;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.xy.concurrent.source.ApplicationContextReturnable;

import java.util.concurrent.atomic.AtomicReference;

/**
 * reentrant lock
 *
 * @author wangxinyu
 * @date 2020/11/15
 */
@Order(5)
@Component
public class LockSupport extends ApplicationContextReturnable {

    @Override
    public Integer doReturn() {
        AtomicReference<Integer> result = new AtomicReference<>();
        Thread main = Thread.currentThread();
        Thread thread = new Thread(() -> {
            result.set(fibo40());
            java.util.concurrent.locks.LockSupport.unpark(main);
//            main.interrupt();
        }, name(false));
        thread.start();
        java.util.concurrent.locks.LockSupport.park();
        return result.get();
    }
}
