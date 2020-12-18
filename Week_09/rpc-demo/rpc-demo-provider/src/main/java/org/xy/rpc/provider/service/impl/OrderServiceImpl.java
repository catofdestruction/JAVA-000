package org.xy.rpc.provider.service.impl;

import org.xy.rpc.api.model.Order;
import org.xy.rpc.api.service.OrderService;

import java.util.UUID;

/**
 * OrderServiceImpl
 *
 * @author wangxinyu
 * @date 2020/12/18
 */
public class OrderServiceImpl implements OrderService {

    @Override
    public Order findOrderById(int id) {
        return new Order(id, UUID.randomUUID().toString(), 81f);
    }
}
