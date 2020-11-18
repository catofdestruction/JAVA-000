package org.xy.spring.impl;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.xy.spring.source.ApplicationContextReturnable;

import static org.xy.spring.source.RRWWB.SPRING_BOOT_MAIN_ACTIVE_THREAD_COUNT;

/**
 * simple
 *
 * @author wangxinyu
 * @date 2020/11/11
 */
@Order(1)
@Component
public class Yield extends ApplicationContextReturnable {

    private volatile Integer result = null;

    @Override
    public Integer doReturn() {
        Thread thread = new Thread(() -> result = fibo40(), name(false));
        thread.start();
        while (Thread.activeCount() > SPRING_BOOT_MAIN_ACTIVE_THREAD_COUNT) {
            Thread.yield();
        }
        return result;
    }
}
