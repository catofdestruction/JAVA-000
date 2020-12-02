package org.xy.mysqldemo.persistence.repository;

import org.springframework.stereotype.Repository;
import org.xy.mysqldemo.persistence.model.OrderDO;
import org.xy.mysqldemo.persistence.model.OrderItemDO;

/**
 * @author wangxinyu
 * @date 2020/12/2
 */
@Repository
public interface OrderItemRepository extends BaseRepository<OrderItemDO, Long> {
}
