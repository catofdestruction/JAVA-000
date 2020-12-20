package org.xy.rpc.core.client;

import com.alibaba.fastjson.parser.ParserConfig;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.FactoryBean;
import org.xy.rpc.core.annotation.SimpleRpcProxy;
import org.xy.rpc.core.api.RpcRequest;
import org.xy.rpc.core.api.RpcResponse;

import java.lang.reflect.Proxy;

import static org.xy.rpc.core.client.TempPostInterface.post;

/**
 * RpcServiceProxyFactoryBean
 *
 * @author wangxinyu
 * @date 2020/12/20
 */
@NoArgsConstructor
@AllArgsConstructor
public class RpcServiceProxyFactoryBean<T> implements FactoryBean<T> {

    static {
        ParserConfig.getGlobalInstance().addAccept("org.xy");
    }

    private Class<T> serviceClass;

    @Override
    @SuppressWarnings("unchecked")
    public T getObject() throws Exception {
        // ref: https://juejin.cn/post/6844903885266485262 https://cloud.tencent.com/developer/article/1497799
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{serviceClass},
                (proxy, method, args) -> {
                    SimpleRpcProxy simpleRpcProxy = serviceClass.getAnnotation(SimpleRpcProxy.class);
                    if (simpleRpcProxy == null) {
                        throw new IllegalStateException();
                    }
                    // do proxy logic (request remote rpc provider with service meta)
                    RpcRequest request = new RpcRequest(serviceClass.getName(), method.getName(), args);
                    RpcResponse response = post(request, simpleRpcProxy.namingServiceUrl());
                    // TODO: response status for handling RpcException
                    return response.getResult();
                });
    }

    @Override
    public Class<T> getObjectType() {
        return serviceClass;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
