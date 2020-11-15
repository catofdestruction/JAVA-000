package org.xy.concurrent.impl;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.xy.concurrent.source.ApplicationContextReturnable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * completable future accept
 *
 * @author wangxinyu
 * @date 2020/11/15
 */
@Order(14)
@Component
public class CompletableFutureAccept extends ApplicationContextReturnable {

    @Override
    public Integer doReturn() {
        AtomicInteger result = new AtomicInteger(0);
        CompletableFuture.supplyAsync(this::fibo40)
                         .thenAccept(result::set);
        while (result.get() == 0) {

        }
        return result.get();
    }
}
