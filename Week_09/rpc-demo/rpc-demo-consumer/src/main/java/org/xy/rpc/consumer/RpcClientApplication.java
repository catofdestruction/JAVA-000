package org.xy.rpc.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * RpcClientApplication
 *
 * @author wangxinyu
 * @date 2020/12/16
 */
@Slf4j
@SpringBootApplication(scanBasePackages = {"org.xy.rpc.consumer"})
public class RpcClientApplication {

    public static void main(String[] args) {
        log.info("{}", RpcClientApplication.class);


    }
}
