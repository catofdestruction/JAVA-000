package org.xy.mysqldemo.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * BaseRepository
 *
 * @author wangxinyu
 * @date 2020/11/25
 */
@NoRepositoryBean
public interface BaseRepository<T, ID> extends QuerydslPredicateExecutor<T>, JpaRepository<T, ID> {
}
