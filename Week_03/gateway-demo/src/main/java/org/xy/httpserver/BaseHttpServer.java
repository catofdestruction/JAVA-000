package org.xy.httpserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * base http server
 *
 * @author wangxinyu
 * @date 2020/11/1
 */
public abstract class BaseHttpServer {

    protected static void service(Socket socket) {
        try {
            Thread.sleep(20);
            String body = String.format("Hello, nio (%s)", socket.getLocalPort());
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            printWriter.println("Content-Length:" + body.getBytes().length);
            printWriter.println();
            printWriter.write(body);
            printWriter.close();
            socket.close();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
