package org.xy.concurrent.impl;

import org.springframework.stereotype.Component;
import org.xy.concurrent.source.Returnable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

import static org.xy.concurrent.source.RRWWB.TIMEOUT;

/**
 * future thread factory
 *
 * @author wangxinyu
 * @date 2020/11/11
 */
@Component
public class FutureThreadFactory implements ThreadFactory, Returnable<Integer> {

    private final AtomicInteger id = new AtomicInteger(0);

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, String.format("%s-%s", name(), id.getAndIncrement()));
    }

    /**
     * using {@link java.util.concurrent.Future#get(long, TimeUnit)} to return fibo40
     *
     * @return fibo40
     */
    @Override
    public Integer doReturn() {
        try {
            return Executors.newSingleThreadExecutor(this)
                            .submit(this::fibo40)
                            .get(TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | TimeoutException | ExecutionException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
