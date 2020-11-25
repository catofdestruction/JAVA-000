package org.xy.mysqldemo.persistence.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

/**
 * QuerydslAutoConfiguration
 *
 * @author wangxinyu
 * @date 2020/11/25
 */
@Configuration
@AutoConfigureAfter(JpaRepositoriesAutoConfiguration.class)
public class QuerydslAutoConfiguration {

    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager entityManager) {
        return new JPAQueryFactory(entityManager);
    }
}
