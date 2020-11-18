package org.xy.concurrent.source;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.OrderUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.xy.concurrent.source.RRWWB.LINE;

/**
 * returnable aop
 *
 * @author wangxinyu
 * @date 2020/11/15
 */
@Slf4j
@Aspect
@Component
public class ReturnableAop {

    @Pointcut(value="execution(* org.xy.concurrent.source.Returnable.doReturn(..))")
    public void point(){

    }

    @Before(value="point()")
    public void before(){
    }

    @AfterReturning(value = "point()")
    public void after(){
    }

    @Around("point()")
    public void around(ProceedingJoinPoint joinPoint) {
        Optional.of(joinPoint.getTarget()).map(Returnable.class::cast).ifPresent(r -> {
            log.info("\n{} ({}) {} {}\n", LINE, OrderUtils.getOrder(r.getClass()), r.name(true), LINE);
            log.warn("\n[{} in {}] start ...\n", r.name(false), Thread.currentThread());
            try {
                joinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            } finally {
                log.warn("\n[{} in {}] end ...\n", r.name(false), Thread.currentThread());
            }
        });
    }
}
