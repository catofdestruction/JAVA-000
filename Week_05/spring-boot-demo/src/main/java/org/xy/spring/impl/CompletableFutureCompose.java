package org.xy.spring.impl;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.xy.spring.source.ApplicationContextReturnable;

import java.util.concurrent.CompletableFuture;

/**
 * completable future compose
 *
 * @author wangxinyu
 * @date 2020/11/15
 */
@Order(16)
@Component
public class CompletableFutureCompose extends ApplicationContextReturnable {

    @Override
    public Integer doReturn() {
        return CompletableFuture.supplyAsync(this::fibo40)
                                .thenCompose(slow -> CompletableFuture.supplyAsync(this::optimizedFibo40))
                                .join();
    }
}
