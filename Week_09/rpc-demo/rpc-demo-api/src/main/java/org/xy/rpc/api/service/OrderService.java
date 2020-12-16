package org.xy.rpc.api.service;

import org.xy.rpc.api.model.Order;

/**
 * OrderService
 *
 * @author wangxinyu
 * @date 2020/12/16
 */
public interface OrderService {

    /**
     * findOrderById
     *
     * @param id id
     * @return findOrderById
     */
    Order findOrderById(int id);
}
