package org.xy.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Concurrent demo application
 *
 * @author wangxinyu
 * @date 2020/11/10
 */
@Slf4j
@SpringBootApplication(scanBasePackages = {"org.xy.concurrent.*"})
public class ConcurrentDemoApplication {

    public static void main(String[] args) {
        log.info("Application start ...");
        SpringApplication.run(ConcurrentDemoApplication.class, args);
        log.info("Application started ...");
    }
}
