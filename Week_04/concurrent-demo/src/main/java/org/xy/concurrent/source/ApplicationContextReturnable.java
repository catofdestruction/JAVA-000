package org.xy.concurrent.source;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * application context returnable
 *
 * @author wangxinyu
 * @date 2020/11/15
 */
public abstract class ApplicationContextReturnable implements Returnable<Integer>, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public String name(boolean pro) {
        Class<? extends ApplicationContextReturnable> thisClass = this.getClass();
        return pro ? applicationContext.getBean(thisClass).getClass().getSimpleName() : thisClass.getSimpleName();
    }
}
