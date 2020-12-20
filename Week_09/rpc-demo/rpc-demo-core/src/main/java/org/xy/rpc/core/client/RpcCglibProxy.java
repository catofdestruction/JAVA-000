package org.xy.rpc.core.client;

import com.alibaba.fastjson.parser.ParserConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.xy.rpc.core.api.RpcRequest;
import org.xy.rpc.core.api.RpcResponse;

import java.lang.reflect.Method;

import static org.xy.rpc.core.client.TempPostInterface.post;

/**
 * RpcCglibProxy
 *
 * @author wangxinyu
 * @date 2020/12/19
 */
@Slf4j
public final class RpcCglibProxy {

    static {
        ParserConfig.getGlobalInstance().addAccept("org.xy");
    }

    @SuppressWarnings("unchecked")
    public static <T> T create(final Invoker invoker) {
        Enhancer en = new Enhancer();
        en.setSuperclass(invoker.getServiceClass());
        en.setCallback(new InvokerMethodInterceptor(invoker));
        return (T) en.create();
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InvokerMethodInterceptor implements MethodInterceptor {

        private Invoker invoker;

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            // do origin logic ref: https://blog.csdn.net/xiaohai0504/article/details/6832990
//            methodProxy.invokeSuper(o, objects);

            // do proxy logic (request remote rpc provider with service meta)
            RpcRequest request = new RpcRequest(invoker.getServiceClass().getName(), method.getName(), objects);
            RpcResponse response = post(request, invoker.getUrl());
            // TODO: response status for handling RpcException
            return response.getResult();
        }
    }
}
