package org.xy.spring.impl;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.xy.spring.source.ApplicationContextReturnable;

import java.util.concurrent.CompletableFuture;

/**
 * completable future apply to either
 *
 * @author wangxinyu
 * @date 2020/11/15
 */
@Order(17)
@Component
public class CompletableFutureApplyToEither extends ApplicationContextReturnable {

    @Override
    public Integer doReturn() {
        return CompletableFuture.supplyAsync(this::fibo40)
                                .applyToEither(CompletableFuture.supplyAsync(this::optimizedFibo40), faster -> faster)
                                .exceptionally(throwable -> {
                                    log.error(throwable.getMessage());
                                    return 0;
                                })
                                .join();
    }
}
