package org.xy.spring.impl;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.xy.spring.source.ApplicationContextReturnable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.xy.spring.source.RRWWB.TIMEOUT;

/**
 * future task
 *
 * @author wangxinyu
 * @date 2020/11/15
 */
@Order(7)
@Component
public class FutureTask extends ApplicationContextReturnable {

    @Override
    public Integer doReturn() {
        java.util.concurrent.FutureTask<Integer> task = new java.util.concurrent.FutureTask<>(this::fibo40);
        new Thread(task, name(false)).start();
        try {
            return task.get(TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
        return 0;
    }
}


