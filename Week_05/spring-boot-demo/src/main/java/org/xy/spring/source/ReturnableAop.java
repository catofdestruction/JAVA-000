package org.xy.spring.source;

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
import java.util.concurrent.atomic.AtomicReference;

import static org.xy.spring.source.RRWWB.LINE;

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

    @Pointcut(value = "execution(* org.xy.spring.source.Returnable.doReturn(..))")
    public void point() {

    }

    @Before(value = "point()")
    public void before() {
    }

    @AfterReturning(value = "point()")
    public void after() {
    }

    /**
     * around
     * TODO: joinPoint.proceed() must be invoke or else origin method will not be invoked
     * TODO: must return value if origin method has a return value (or else return null)
     *
     * @param joinPoint ProceedingJoinPoint
     * @return Object
     */
    @Around("point()")
    public Object around(ProceedingJoinPoint joinPoint) {
        AtomicReference<Object> result = new AtomicReference<>();
        Optional.of(joinPoint.getTarget()).map(Returnable.class::cast).ifPresent(r -> {
            log.info("\n{} ({}) {} {}\n", LINE, OrderUtils.getOrder(r.getClass()), r.name(true), LINE);
            log.warn("\n[{} in {}] start ...\n", r.name(false), Thread.currentThread());
            try {
                result.set(joinPoint.proceed());
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            } finally {
                log.warn("\n[{} in {}] end ...\n", r.name(false), Thread.currentThread());
            }
        });
        return result.get();
    }
}
