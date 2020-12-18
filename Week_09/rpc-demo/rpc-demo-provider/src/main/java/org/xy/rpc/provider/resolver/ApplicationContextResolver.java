package org.xy.rpc.provider.resolver;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.xy.rpc.core.resolver.RpcResolver;

/**
 * ApplicationContextResolver
 *
 * @author wangxinyu
 * @date 2020/12/18
 */
public class ApplicationContextResolver implements RpcResolver, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object resolve(String serviceClass) {
        return this.applicationContext.getBean(serviceClass);
    }
}
