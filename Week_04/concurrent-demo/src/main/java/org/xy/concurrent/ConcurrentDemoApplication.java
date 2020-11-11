package org.xy.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.xy.concurrent.source.Returnable;

import java.util.List;

import static org.xy.concurrent.source.RRWWB.LINE;

/**
 * Concurrent demo application
 *
 * @author wangxinyu
 * @date 2020/11/10
 */
@Slf4j
@SpringBootApplication(scanBasePackages = {"org.xy.concurrent"})
public class ConcurrentDemoApplication {

    private static List<Returnable<Integer>> returnables;

    @Autowired
    public ConcurrentDemoApplication(List<Returnable<Integer>> returnables) {
        ConcurrentDemoApplication.returnables = returnables;
    }

    public static void main(String[] args) {
        log.info("Application start ...");
        SpringApplication.run(ConcurrentDemoApplication.class, args);
        log.info("Application started ...");

        log.info("\n{} {} threads {}\n", LINE, Thread.currentThread().getThreadGroup().activeCount(), LINE);
        Thread.currentThread().getThreadGroup().list();
        log.info("\n{} {} threads {}\n", LINE, Thread.currentThread().getThreadGroup().getParent().activeCount(), LINE);
        Thread.currentThread().getThreadGroup().getParent().list();

        returnables.forEach(r -> {
            log.info("\n{} {} {}\n", LINE, r.name(), LINE);
            log.warn("\n[{} in {}] start ...\n", r.name(), Thread.currentThread());
            Integer integer = r.doReturn();
            log.warn("\n[{} in {}] end ...\n", r.name(), Thread.currentThread());
        });
    }
}
