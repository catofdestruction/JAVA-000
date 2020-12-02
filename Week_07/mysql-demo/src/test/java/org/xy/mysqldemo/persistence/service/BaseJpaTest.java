package org.xy.mysqldemo.persistence.service;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.xy.mysqldemo.persistence.PersistenceConfiguration;

/**
 * @author wangxinyu
 * @date 2020/12/2
 */
@SpringBootTest
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public abstract class BaseJpaTest {

//    @SpringBootApplication
    @Import(PersistenceConfiguration.class)
    public static class TestConfig {

    }
}
