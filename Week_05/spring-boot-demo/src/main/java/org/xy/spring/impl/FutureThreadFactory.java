package org.xy.spring.impl;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.xy.spring.source.ApplicationContextReturnable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

import static org.xy.spring.source.RRWWB.TIMEOUT;

/**
 * future thread factory
 *
 * @author wangxinyu
 * @date 2020/11/11
 */
@Order(4)
@Component
public class FutureThreadFactory extends ApplicationContextReturnable implements ThreadFactory {

    private final AtomicInteger id = new AtomicInteger(0);

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, String.format("%s-%s", name(false), id.getAndIncrement()));
    }

    /**
     * using {@link java.util.concurrent.Future#get(long, TimeUnit)} to return fibo40
     * note: {@link ExecutorService#shutdown()} is needed for the end of application
     *
     * @return fibo40
     */
    @Override
    public Integer doReturn() {
        ExecutorService executorService = Executors.newSingleThreadExecutor(this);
        try {
            return executorService.submit(this::fibo40).get(TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | TimeoutException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
        return 0;
    }
}
