package org.xy.rpc.provider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * RpcServerApplication
 *
 * @author wangxinyu
 * @date 2020/12/16
 */
@Slf4j
@SpringBootApplication(scanBasePackages = {"org.xy.rpc.provider"})
public class RpcServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RpcServerApplication.class, args);
	}

}
