package org.xy.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.xy.concurrent.source.Returnable;

import java.util.List;

import static org.xy.concurrent.source.RRWWB.LINE;

/**
 * Spring Boot demo application
 *
 * @author wangxinyu
 * @date 2020/11/10
 */
@Slf4j
@SpringBootApplication(scanBasePackages = {"org.xy.concurrent"})
public class SpringBootDemoApplication {

    private static List<Returnable<Integer>> returnables;

    @Autowired
    public SpringBootDemoApplication(List<Returnable<Integer>> returnables) {
        SpringBootDemoApplication.returnables = returnables;
    }

    public static void main(String[] args) {
        log.info("Application start ...");
        SpringApplication.run(SpringBootDemoApplication.class, args);
        log.info("Application started ...");

        // spring boot starts with 3 & spring boot web starts with 19
        log.info("\n{} {} threads {}\n", LINE, Thread.currentThread().getThreadGroup().activeCount(), LINE);
        Thread.currentThread().getThreadGroup().list();
        log.info("\n{} {} threads {}\n", LINE, Thread.currentThread().getThreadGroup().getParent().activeCount(), LINE);
        Thread.currentThread().getThreadGroup().getParent().list();

        returnables.forEach(Returnable::doReturn);

        log.info("\n{} THE END {}\n", LINE, LINE);
    }
}
