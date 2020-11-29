package org.xy.mysqldemo.persistence.repository;

import org.springframework.stereotype.Repository;
import org.xy.mysqldemo.persistence.model.OrderDO;

/**
 * OrderRepository
 *
 * @author wangxinyu
 * @date 2020/11/25
 */
@Repository
public interface OrderRepository extends BaseRepository<OrderDO, Long> {
}
