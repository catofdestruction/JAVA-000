package org.xy.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.xy.spring.source.RRWWB;

/**
 * Spring Boot demo application
 *
 * @author wangxinyu
 * @date 2020/11/10
 */
@Slf4j
@SpringBootApplication(scanBasePackages = {"org.xy.spring"})
public class SpringBootDemoApplication {

    private static RRWWB rrwwb;

    @Autowired
    public SpringBootDemoApplication(RRWWB rrwwb) {
        SpringBootDemoApplication.rrwwb = rrwwb;
    }

    public static void main(String[] args) {
        log.info("Application start ...");
        SpringApplication.run(SpringBootDemoApplication.class, args);
        log.info("Application started ...");
        rrwwb.runReturnables();
    }
}
