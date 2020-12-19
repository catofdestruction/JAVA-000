package org.xy.rpc.core.client;

import com.alibaba.fastjson.JSON;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xy.rpc.core.api.RpcRequest;
import org.xy.rpc.core.api.RpcResponse;

import java.io.IOException;
import java.util.Objects;

/**
 * TempPostInterface
 *
 * @author wangxinyu
 * @date 2020/12/19
 */
public interface TempPostInterface {

    Logger log = LoggerFactory.getLogger(TempPostInterface.class);

    MediaType JSON_TYPE = MediaType.get("application/json; charset=utf-8");

    /**
     * for temp post
     *
     * @param req RpcRequest
     * @param url url
     * @return RpcResponse
     * @throws IOException IOException
     */
    default RpcResponse post(RpcRequest req, String url) throws IOException {
        String reqJson = JSON.toJSONString(req);
        log.info("\n[request]: {}\n", reqJson);
        // TODO: reuse client
        // TODO: http client/netty client
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder().url(url)
                                                     .post(RequestBody.create(JSON_TYPE, reqJson))
                                                     .build();
        String respJson = Objects.requireNonNull(client.newCall(request).execute().body()).string();
        log.info("\n[response]: {}\n", respJson);
        return JSON.parseObject(respJson, RpcResponse.class);
    }
}
