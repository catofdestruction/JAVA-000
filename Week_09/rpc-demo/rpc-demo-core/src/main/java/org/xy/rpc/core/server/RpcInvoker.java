package org.xy.rpc.core.server;

import org.xy.rpc.core.api.RpcRequest;
import org.xy.rpc.core.api.RpcResponse;
import org.xy.rpc.core.resolver.RpcResolver;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * RpcInvoker
 *
 * @author wangxinyu
 * @date 2020/12/18
 */
public class RpcInvoker {

    private final RpcResolver resolver;

    public RpcInvoker(RpcResolver resolver) {
        this.resolver = resolver;
    }

    /**
     * invoke real service impl with rpc request
     *
     * @param request request
     * @return rpc response
     */
    public RpcResponse invoke(RpcRequest request) {
        RpcResponse response = new RpcResponse();
        String serviceClass = request.getServiceClass();

        resolver.resolve(serviceClass);
        Object service = resolver.resolve(serviceClass);
        try {
            Method method = resolveMethodFromClass(service.getClass(), request.getMethod());
            // TODO: to be optimized ref: dubbo, fastjson,
            Object result = method.invoke(service, request.getParams());
            // TODO: xml serialization ref: xStream
            response.setResult(result);
            response.setStatus(true);
            return response;
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO: RpcException
            e.printStackTrace();
            response.setException(e);
            response.setStatus(false);
            return response;
        }
    }

    private Method resolveMethodFromClass(Class<?> klass, String methodName) {
        String errMessage = String.format("can not resolve for method %s from class %s", methodName, klass);
        return Arrays.stream(klass.getMethods())
                     .filter(m -> methodName.equals(m.getName()))
                     .findFirst()
                     .orElseThrow(() -> new RuntimeException(errMessage));
    }
}

