package org.xy.rpc.core.resolver;

/**
 * RpcResolver
 *
 * @author wangxinyu
 * @date 2020/12/18
 */
public interface RpcResolver {

    /**
     * resolve a service impl by service class
     *
     * @param serviceClass serviceClass
     * @return service impl
     */
    Object resolve(String serviceClass);
}
