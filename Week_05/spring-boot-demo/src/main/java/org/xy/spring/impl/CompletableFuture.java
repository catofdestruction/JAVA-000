package org.xy.spring.impl;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.xy.spring.source.ApplicationContextReturnable;

/**
 * completable future
 *
 * @author wangxinyu
 * @date 2020/11/15
 */
@Order(12)
@Component
public class CompletableFuture extends ApplicationContextReturnable {

    @Override
    public Integer doReturn() {
        return java.util.concurrent.CompletableFuture.supplyAsync(this::fibo40).join();
    }
}
