package org.xy.rpc.core.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
public class RpcInvoker {

    private RpcResolver resolver;

    /**
     * invoke real service impl with rpc request
     * @param request request
     * @return rpc respnse
     */
    public RpcResponse invoke(RpcRequest request) {
        RpcResponse response = new RpcResponse();
        String serviceClass = request.getServiceClass();

        // TODO: generic type & reflection
        Object service = resolver.resolve(serviceClass);
        try {
            Method method = resolveMethodFromClass(service.getClass(), request.getMethod());
            // TODO: to be optimized ref: dubbo, fastjson,
            Object result = method.invoke(service, request.getParams());
            // TODO: merge double serialization
            // TODO: xml serialization ref: xStream
            response.setResult(JSON.toJSONString(result, SerializerFeature.WriteClassName));
            response.setStatus(true);
            return response;
        } catch ( IllegalAccessException | InvocationTargetException e) {
            // TODO: RpcException
            e.printStackTrace();
            response.setException(e);
            response.setStatus(false);
            return response;
        }
    }

    private Method resolveMethodFromClass(Class<?> klass, String methodName) {
        return Arrays.stream(klass.getMethods()).filter(m -> methodName.equals(m.getName())).findFirst().get();
    }
}

