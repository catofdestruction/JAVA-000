package org.xy.mysqldemo.persistence;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.xy.mysqldemo.persistence.config.JpaConfiguration;

/**
 * persistence configuration
 *
 * @author wangxinyu
 * @date 2020/11/25
 */
@Configuration
@Import({JpaConfiguration.class})
public class PersistenceConfiguration {
}
