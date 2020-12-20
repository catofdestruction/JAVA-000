package org.xy.rpc.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * SimpleRpcProxy
 *
 * @author wangxinyu
 * @date 2020/12/20
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SimpleRpcProxy {

    /**
     * RPC server naming url to connect.
     *
     * @return the string
     */
    String namingServiceUrl() default "";

    /**
     * version for naming service
     */
    String version() default "1.0.0";
}
