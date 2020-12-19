package org.xy.rpc.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.xy.rpc.api.service.OrderService;
import org.xy.rpc.api.service.UserService;
import org.xy.rpc.core.client.Invoker;
import org.xy.rpc.core.client.RpcCglibProxy;
import org.xy.rpc.core.client.RpcJdkProxy;

/**
 * RpcClientApplication
 *
 * @author wangxinyu
 * @date 2020/12/16
 */
@Slf4j
@SpringBootApplication(scanBasePackages = {"org.xy.rpc.consumer"})
public class RpcClientApplication {

    private static final String HOST_URL = "http://localhost:8167/";
    private static final Invoker USER_INVOKER = new Invoker(UserService.class, HOST_URL);
    private static final Invoker ORDER_INVOKER = new Invoker(OrderService.class, HOST_URL);

    public static void main(String[] args) {
        log.info("{}", RpcClientApplication.class);
        invokeByRpcJdkProxy();
        invokeByCglibProxy();
    }

    public static void invokeByRpcJdkProxy() {
        log.warn("\n========== RpcJdkProxy ==========\n");
        UserService userService = RpcJdkProxy.create(USER_INVOKER);
        log.warn("{}", userService.findUserById(666));
        OrderService orderService = RpcJdkProxy.create(ORDER_INVOKER);
        log.warn("{}", orderService.findOrderById(999));
    }

    public static void invokeByCglibProxy() {
        log.warn("\n========== RpcCglibProxy ==========\n");
        UserService userService = RpcCglibProxy.create(new RpcCglibProxy.InvokerMethodInterceptor(USER_INVOKER));
        log.warn("{}", userService.findUserById(666));
        OrderService orderService = RpcCglibProxy.create(new RpcCglibProxy.InvokerMethodInterceptor(ORDER_INVOKER));
        log.warn("{}", orderService.findOrderById(999));
    }
}
