package org.xy.mysqldemo.persistence.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xy.mysqldemo.persistence.model.OrderDO;
import org.xy.mysqldemo.persistence.repository.OrderRepository;

/**
 * order service
 *
 * @author wangxinyu
 * @date 2020/12/2
 */
@Slf4j
@Service
public class OrderService extends BaseService<OrderDO, Long> {

    @Autowired
    private OrderRepository repository;

    public OrderService(OrderRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
