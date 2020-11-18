package org.xy.spring.impl;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.xy.spring.source.ApplicationContextReturnable;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.xy.spring.source.RRWWB.TIMEOUT;

/**
 * exchanger
 *
 * @author wangxinyu
 * @date 2020/11/15
 */
@Order(18)
@Component
public class Exchanger extends ApplicationContextReturnable {

    @Override
    public Integer doReturn() {
        Integer result = 0;
        java.util.concurrent.Exchanger<Integer> exchanger = new java.util.concurrent.Exchanger<>();
        new Thread(() -> {
            try {
                // change result is 0
                exchanger.exchange(fibo40(), TIMEOUT, TimeUnit.MILLISECONDS);
            } catch (InterruptedException | TimeoutException e) {
                e.printStackTrace();
            }
        }, name(false)).start();
        try {
            // exchange will block main
            result = exchanger.exchange(0, TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | TimeoutException e) {
            e.printStackTrace();
        }
        return result;
    }
}
