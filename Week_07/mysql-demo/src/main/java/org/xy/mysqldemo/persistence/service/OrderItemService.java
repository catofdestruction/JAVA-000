package org.xy.mysqldemo.persistence.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xy.mysqldemo.persistence.model.OrderItemDO;
import org.xy.mysqldemo.persistence.repository.OrderItemRepository;

/**
 * order item service
 *
 * @author wangxinyu
 * @date 2020/12/2
 */
@Slf4j
@Service
public class OrderItemService extends BaseService<OrderItemDO, Long> {

    @Autowired
    private OrderItemRepository repository;

    public OrderItemService(OrderItemRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
