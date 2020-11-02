package org.xy.httpserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * simple http server
 *
 * @author wangxinyu
 * @date 2020/11/1
 */
public class HttpServer01 extends BaseHttpServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8801);
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                service(socket);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}