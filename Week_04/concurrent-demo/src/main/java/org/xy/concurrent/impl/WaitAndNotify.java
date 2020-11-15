package org.xy.concurrent.impl;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.xy.concurrent.source.ApplicationContextReturnable;

/**
 * wait and notify
 *
 * @author wangxinyu
 * @date 2020/11/11
 */
@Order(3)
@Component
public class WaitAndNotify extends ApplicationContextReturnable {

    private volatile Integer result = null;

    @Override
    public Integer doReturn() {
        Thread thread = new Thread(() -> {
            result = fibo40();
            synchronized (this) {
                notify();
            }
        }, name(false));
        thread.start();
        try {
            // https://stackoverflow.com/questions/7126550/java-wait-and-notify-illegalmonitorstateexception
            // https://stackoverflow.com/questions/1537116/illegalmonitorstateexception-on-wait-call
            // https://stackoverflow.com/questions/886722/how-to-use-wait-and-notify-in-java-without-illegalmonitorstateexception
            synchronized (this) {
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}