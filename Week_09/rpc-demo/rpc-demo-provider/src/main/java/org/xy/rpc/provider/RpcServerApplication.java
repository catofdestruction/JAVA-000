package org.xy.rpc.provider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.xy.rpc.api.model.User;
import org.xy.rpc.core.api.RpcRequest;
import org.xy.rpc.core.api.RpcResponse;

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

	@PostMapping("/")
	public RpcResponse invoke(@RequestBody RpcRequest request) {
		log.info("receive request ===> {}", request);
		return new RpcResponse(JSON.toJSONString(new User(1, "go"), SerializerFeature.WriteClassName), true, null);
	}
}
