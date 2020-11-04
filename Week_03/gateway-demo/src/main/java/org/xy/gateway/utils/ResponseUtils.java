package org.xy.gateway.utils;

import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Optional;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * response utils
 *
 * @author wangxinyu
 * @date 2020/11/4
 */
@Slf4j
public abstract class ResponseUtils {

    /**
     * common response
     *
     * @param resp resp
     * @param mock mock
     * @return FullHttpResponse
     * @throws IOException IOException
     */
    public static FullHttpResponse commonResponse(final HttpResponse resp, final boolean mock) throws IOException {
        byte[] body = mock ? "hello xy".getBytes(StandardCharsets.UTF_8) : EntityUtils.toByteArray(resp.getEntity());
        log.info("body(length: {}):\n {}", body.length, new String(body));
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body));
        response.headers().set("Content-Type", "application/json");
//        response.headers().setInt("Content-Length",
//                Integer.parseInt(resp.getFirstHeader("Content-Length").getValue()))
        response.headers().setInt("Content-Length", response.content().readableBytes());
        return response;
    }

    /**
     * empty response
     *
     * @return FullHttpResponse
     */
    public static FullHttpResponse emptyResponse() {
        return new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
    }

    /**
     * log http headers
     *
     * @param resp HttpResponse
     */
    public static void logHttpHeaders(final HttpResponse resp) {
        Optional.ofNullable(resp).ifPresent(r -> {
            log.info("================== HEADERS ==================");
            Arrays.stream(r.getAllHeaders()).forEach(h -> log.info("name: {}, value {}", h.getName(), h.getValue()));
        });
    }
}
