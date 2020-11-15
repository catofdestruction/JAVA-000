package org.xy.concurrent.impl;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.xy.concurrent.source.ApplicationContextReturnable;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * blocking queue
 *
 * @author wangxinyu
 * @date 2020/11/15
 */
@Order(8)
@Component
public class BlockingQueue extends ApplicationContextReturnable {

    @Override
    public Integer doReturn() {
        java.util.concurrent.BlockingQueue<Integer> linkedBlockingQueue = new LinkedBlockingQueue<>();
        try {
            new Thread(() -> {
                try {
                    linkedBlockingQueue.put(fibo40());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, name(false)).start();
            return linkedBlockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
