package org.xy.gateway;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * http server
 *
 * @author wangxinyu
 * @date 2020/11/3
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class HttpServer {

    protected String proxyServer;

    protected int proxyPort;

    /**
     * run
     *
     * @throws Exception exception
     */
    public abstract void run() throws Exception;
}
