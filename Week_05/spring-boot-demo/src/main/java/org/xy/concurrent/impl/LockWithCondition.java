package org.xy.concurrent.impl;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.xy.concurrent.source.ApplicationContextReturnable;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock with condition
 *
 * @author wangxinyu
 * @date 2020/11/15
 */
@Order(6)
@Component
public class LockWithCondition extends ApplicationContextReturnable {

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    @Override
    public Integer doReturn() {
        AtomicInteger result = new AtomicInteger();
        Thread main = Thread.currentThread();
        lock.lock();
        try {
            Thread thread = new Thread(() -> {
                lock.lock();
                result.set(fibo40());
                condition.signal();
//                main.interrupt();
                lock.unlock();
            }, name(false));
            thread.start();
            condition.await();
        } catch (InterruptedException ignored) {

        } finally {
            lock.unlock();
        }
        return result.get();
    }
}
