package org.xy.spring.impl;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.xy.spring.source.ApplicationContextReturnable;

import java.util.concurrent.CompletableFuture;

/**
 * completable future apply
 *
 * @author wangxinyu
 * @date 2020/11/15
 */
@Order(13)
@Component
public class CompletableFutureApply extends ApplicationContextReturnable {

    @Override
    public Integer doReturn() {
        return CompletableFuture.supplyAsync(this::fibo40)
                                .thenApply(slow -> Math.max(slow, optimizedFibo40()))
                                .join();
    }
}
