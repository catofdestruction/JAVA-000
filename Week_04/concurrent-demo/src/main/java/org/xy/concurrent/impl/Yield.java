package org.xy.concurrent.impl;

import org.springframework.stereotype.Component;
import org.xy.concurrent.source.Returnable;

import static org.xy.concurrent.source.RRWWB.SPRING_BOOT_MAIN_ACTIVE_THREAD_COUNT;

/**
 * simple
 *
 * @author wangxinyu
 * @date 2020/11/11
 */
@Component
public class Yield implements Returnable<Integer> {

    private volatile Integer result = null;

    @Override
    public Integer doReturn() {
        Thread thread = new Thread(() -> result = fibo40(), name());
        thread.start();
        while (Thread.activeCount() > SPRING_BOOT_MAIN_ACTIVE_THREAD_COUNT) {
            Thread.yield();
        }
        return result;
    }
}
