package org.xy.rpc.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.xy.rpc.api.service.OrderService;
import org.xy.rpc.api.service.UserService;
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

    public static void main(String[] args) {
        log.info("{}", RpcClientApplication.class);

        UserService userService = RpcJdkProxy.create(UserService.class, HOST_URL);
        log.warn("{}", userService.findUserById(666));

        OrderService orderService = RpcJdkProxy.create(OrderService.class, HOST_URL);
        log.warn("{}", orderService.findOrderById(999));
    }
}
