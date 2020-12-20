package org.xy.rpc.core.client;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;
import org.xy.rpc.core.annotation.SimpleRpcProxy;

import java.util.Set;

/**
 * RpcServiceProxyRegistryProcessor
 *
 * @author wangxinyu
 * @date 2020/12/20
 */
@Component
public class RpcServiceProxyRegistryProcessor implements BeanDefinitionRegistryPostProcessor, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        // ref: https://juejin.cn/post/6844903885266485262 https://cloud.tencent.com/developer/article/1497799
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry) {
            @Override
            protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
                return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
            }

            @Override
            protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
                Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);

                GenericBeanDefinition definition;
                for (BeanDefinitionHolder definitionHolder : beanDefinitionHolders) {
                    definition = (GenericBeanDefinition) definitionHolder.getBeanDefinition();
                    definition.getConstructorArgumentValues().addGenericArgumentValue(definition.getBeanClassName());
                    definition.setBeanClass(RpcServiceProxyFactoryBean.class);
                }
                return beanDefinitionHolders;
            }
        };
        scanner.setResourceLoader(this.applicationContext);
        scanner.addIncludeFilter(new AnnotationTypeFilter(SimpleRpcProxy.class));
        // TODO: not a good idea, framework doesn't know package name
        scanner.scan("org.xy.rpc");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
