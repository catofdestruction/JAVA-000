package org.xy.spring.impl;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.xy.spring.source.ApplicationContextReturnable;

import java.util.concurrent.atomic.AtomicInteger;

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
        AtomicInteger result = new AtomicInteger();
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
