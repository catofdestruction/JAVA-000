package org.xy.mysqldemo.persistence.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.xy.mysqldemo.persistence.model.PersistenceDOPackage;
import org.xy.mysqldemo.persistence.repository.PersistenceRepositoryPackage;
import org.xy.mysqldemo.persistence.service.PersistenceServicePackage;

/**
 * jpa configuration
 *
 * @author wangxinyu
 * @date 2020/11/25
 */
@Configuration
@EnableJpaAuditing
@ComponentScan(basePackageClasses = {PersistenceServicePackage.class})
@EntityScan(basePackageClasses = {PersistenceDOPackage.class})
@EnableJpaRepositories(basePackageClasses = {PersistenceRepositoryPackage.class})
public class JpaConfiguration {

}
