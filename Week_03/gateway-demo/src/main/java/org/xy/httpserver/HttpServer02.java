package org.xy.httpserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * thread http server
 *
 * @author wangxinyu
 * @date 2020/11/1
 */
public class HttpServer02 extends BaseHttpServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8802);
        while (true) {
            try {
                final Socket socket = serverSocket.accept();
                new Thread(() -> service(socket)).start();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}