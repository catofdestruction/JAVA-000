package org.xy.rpc.provider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.xy.rpc.api.service.OrderService;
import org.xy.rpc.api.service.UserService;
import org.xy.rpc.core.api.RpcRequest;
import org.xy.rpc.core.api.RpcResponse;
import org.xy.rpc.core.resolver.RpcResolver;
import org.xy.rpc.core.server.RpcInvoker;
import org.xy.rpc.provider.resolver.ApplicationContextResolver;
import org.xy.rpc.provider.service.impl.OrderServiceImpl;
import org.xy.rpc.provider.service.impl.UserServiceImpl;

/**
 * RpcServerApplication
 *
 * @author wangxinyu
 * @date 2020/12/16
 */
@Slf4j
@RestController
@SpringBootApplication(scanBasePackages = {"org.xy.rpc.provider"})
public class RpcServerApplication {

    public static void main(String[] args) {
        log.info("{}", RpcServerApplication.class);
        SpringApplication.run(RpcServerApplication.class, args);
    }

    @Autowired
    RpcInvoker invoker;

    /**
     * inject rpc invoker with rpc resolver
     *
     * @param resolver rpc resolver
     * @return rpc invoker
     */
    @Bean
    public RpcInvoker createInvoker(@Autowired RpcResolver resolver) {
        return new RpcInvoker(resolver);
    }

    /**
     * inject rpc resolver
     *
     * @return rpc resolver
     */
    @Bean
    public RpcResolver createResolver() {
        return new ApplicationContextResolver();
    }

    @PostMapping("/")
    public RpcResponse invoke(@RequestBody RpcRequest request) {
        log.info("receive request ===> {}", request);
        return invoker.invoke(request);
    }

    @Bean
    public UserService createUserService() {
        return new UserServiceImpl();
    }

    @Bean
    public OrderService createOrderService() {
        return new OrderServiceImpl();
    }
}
