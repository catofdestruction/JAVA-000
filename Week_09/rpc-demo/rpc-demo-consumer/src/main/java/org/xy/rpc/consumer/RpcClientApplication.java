package org.xy.rpc.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.xy.rpc.api.service.OrderService;
import org.xy.rpc.api.service.UserService;
import org.xy.rpc.core.client.RpcJdkProxyFactory;

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

    public static void main(String[] args) {
        log.info("{}", RpcClientApplication.class);

        UserService userService = RpcJdkProxyFactory.getProxy(UserService.class, HOST_URL);
        userService.findUserById(666);

        OrderService orderService = RpcJdkProxyFactory.getProxy(OrderService.class, HOST_URL);
        orderService.findOrderById(999);
    }
}
