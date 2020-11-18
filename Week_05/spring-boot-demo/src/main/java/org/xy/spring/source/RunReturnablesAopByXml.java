package org.xy.spring.source;

import lombok.extern.slf4j.Slf4j;

import static org.xy.spring.source.RRWWB.LINE;

/**
 * run returnables aop by xml
 *
 * @author wangxinyu
 * @date 2020/11/18
 */
@Slf4j
public class RunReturnablesAopByXml {

    public void before() {
        showSpringBootApplicationThreads();
    }

    public void after() {
        log.info("\n{} THE END {}\n", LINE, LINE);
    }

    public void showSpringBootApplicationThreads() {
        // spring boot starts with 3 & spring boot web starts with 19
        log.info("\n{} {} threads {}\n", LINE, Thread.currentThread().getThreadGroup().activeCount(), LINE);
        Thread.currentThread().getThreadGroup().list();
        log.info("\n{} {} threads {}\n", LINE, Thread.currentThread().getThreadGroup().getParent().activeCount(), LINE);
        Thread.currentThread().getThreadGroup().getParent().list();
    }
}
