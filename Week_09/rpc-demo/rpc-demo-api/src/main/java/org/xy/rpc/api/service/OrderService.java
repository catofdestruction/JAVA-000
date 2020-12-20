package org.xy.rpc.api.service;

import org.xy.rpc.api.model.Order;
import org.xy.rpc.core.annotation.SimpleRpcProxy;

/**
 * OrderService
 *
 * @author wangxinyu
 * @date 2020/12/16
 */
@SimpleRpcProxy(namingServiceUrl = "http://localhost:8167/")
public interface OrderService {

    /**
     * findOrderById
     *
     * @param id id
     * @return findOrderById
     */
    Order findOrderById(int id);
}
