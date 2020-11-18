package org.xy.spring.impl;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.xy.spring.source.ApplicationContextReturnable;

import java.util.concurrent.CompletableFuture;

/**
 * completable future combine
 *
 * @author wangxinyu
 * @date 2020/11/15
 */
@Order(15)
@Component
public class CompletableFutureCombine extends ApplicationContextReturnable {

    @Override
    public Integer doReturn() {
        return CompletableFuture.supplyAsync(this::fibo40)
                                .thenCombine(CompletableFuture.supplyAsync(this::optimizedFibo40), Math::max)
                                .join();
    }
}
