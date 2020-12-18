package org.xy.rpc.core.client;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.xy.rpc.core.api.RpcRequest;
import org.xy.rpc.core.api.RpcResponse;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Objects;

/**
 * RpcJdkProxyFactory
 *
 * @author wangxinyu
 * @date 2020/12/16
 */
@Slf4j
public final class RpcJdkProxyFactory {

    static {
        ParserConfig.getGlobalInstance().addAccept("org.xy");
    }

    @SuppressWarnings("unchecked")
    public static <T> T getProxy(final Class<T> serviceClass, final String url) {
        // TODO: AOP
        return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{serviceClass}, new InvokerInvocationHandler(serviceClass, url));
    }

    public static class InvokerInvocationHandler implements InvocationHandler {

        public static final MediaType JSON_TYPE = MediaType.get("application/json; charset=utf-8");

        private final Class<?> serviceClass;
        private final String url;

        public <T> InvokerInvocationHandler(Class<T> serviceClass, String url) {
            this.serviceClass = serviceClass;
            this.url = url;
        }

        // TODO: serialization(json/binary/text)  json: code.google.com/p/rpcfx
        // int byte char float double long bool
        // [], data class
        @Override
        public Object invoke(Object proxy, Method method, Object[] params) throws Throwable {
            RpcRequest request = new RpcRequest(this.serviceClass.getName(), method.getName(), params);
            RpcResponse response = post(request, url);
            // TODO: response status for handling RpcException
            return response.getResult();
        }

        private RpcResponse post(RpcRequest req, String url) throws IOException {
            String reqJson = JSON.toJSONString(req);
            log.info("\n[request]: {}\n", reqJson);
            // TODO: reuse client
            // TODO: http client/netty client
            OkHttpClient client = new OkHttpClient();
            final Request request = new Request.Builder().url(url)
                                                         .post(RequestBody.create(JSON_TYPE, reqJson))
                                                         .build();
            String respJson = Objects.requireNonNull(client.newCall(request).execute().body()).string();
            log.info("\n[response]: {}\n", respJson);
            return JSON.parseObject(respJson, RpcResponse.class);
        }
    }
}
