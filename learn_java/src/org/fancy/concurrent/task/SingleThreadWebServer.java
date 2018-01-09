package org.fancy.concurrent.task;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * SingleThreadWebServer
 * <p/>
 * Sequential web server(串行处理任务)，理论上是正确，性能上却非常糟糕
 *
 * @author Brian Goetz and Tim Peierls
 */

public class SingleThreadWebServer {
    public static void main(String[] args) throws Exception {
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            Socket connection = socket.accept();
            handleRequest(connection);
        }
    }

    private static void handleRequest(Socket connection) {
        // request-handling logic here
    }
}
