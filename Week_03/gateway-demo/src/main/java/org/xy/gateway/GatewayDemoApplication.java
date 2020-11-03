package org.xy.gateway;

/**
 * gateway demo application
 *
 * @author wangxinyu
 * @date 2020/11/3
 */
public class GatewayDemoApplication {

    public final static String PROXY_SERVER = System.getProperty("proxyServer", "http://localhost:8088");
    public final static String PROXY_PORT = System.getProperty("proxyPort", "8167");

    public static void main(String[] args) {
        //  http://localhost:8888/api/hello  ==> gateway API
        //  http://localhost:8088/api/hello  ==> backend service
        NettyGateway server = new NettyGateway(PROXY_SERVER, Integer.parseInt(PROXY_PORT), false);
        try {
            server.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
