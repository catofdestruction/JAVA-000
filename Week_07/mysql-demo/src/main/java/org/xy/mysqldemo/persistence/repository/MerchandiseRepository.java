package org.xy.mysqldemo.persistence.repository;

import org.springframework.stereotype.Repository;
import org.xy.mysqldemo.persistence.model.MerchandiseDO;

/**
 * MerchandiseRepository
 *
 * @author wangxinyu
 * @date 2020/11/25
 */
@Repository
public interface MerchandiseRepository extends BaseRepository<MerchandiseDO, Long> {
}
