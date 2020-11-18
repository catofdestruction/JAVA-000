package org.xy.spring.impl;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.xy.spring.source.ApplicationContextReturnable;

/**
 * simple
 *
 * @author wangxinyu
 * @date 2020/11/11
 */
@Order(0)
@Component
public class Simple extends ApplicationContextReturnable {

    private volatile Integer result = null;

    @Override
    public Integer doReturn() {
        Thread thread = new Thread(() -> result = fibo40(), name(false));
        thread.start();
        while (result == null) {}
        return result;
    }
}
