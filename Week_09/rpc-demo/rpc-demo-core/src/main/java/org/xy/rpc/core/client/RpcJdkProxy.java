package org.xy.rpc.core.client;


import com.alibaba.fastjson.parser.ParserConfig;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.xy.rpc.core.api.RpcRequest;
import org.xy.rpc.core.api.RpcResponse;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * RpcJdkProxy
 *
 * @author wangxinyu
 * @date 2020/12/16
 */
@Slf4j
public final class RpcJdkProxy {

    static {
        ParserConfig.getGlobalInstance().addAccept("org.xy");
    }

    @SuppressWarnings("unchecked")
    public static <T> T create(final Invoker invoker) {
        // TODO: AOP
        Class<?> serviceClass = invoker.getServiceClass();
        return (T) Proxy.newProxyInstance(serviceClass.getClassLoader(), new Class[]{serviceClass},
                new InvokerInvocationHandler(invoker));
    }

    @NoArgsConstructor
    @AllArgsConstructor
    public static class InvokerInvocationHandler implements InvocationHandler, TempPostInterface {

        private Invoker invoker;

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            // do origin logic ref: https://juejin.cn/post/6844903478582575111
//            method.invoke(serviceClass.newInstance(), args);

            // do proxy logic (request remote rpc provider with service meta)
            // TODO: serialization(json/binary/text)  json: code.google.com/p/rpcfx
            // int byte char float double long bool
            // [], data class
            RpcRequest request = new RpcRequest(invoker.getServiceClass().getName(), method.getName(), args);
            RpcResponse response = post(request, invoker.getUrl());
            // TODO: response status for handling RpcException
            return response.getResult();
        }
    }
}
