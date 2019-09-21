package com.zz.webserver;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Zhang Zhen
 * @time 2019年9月20日 下午1:41:01
 */

public class WebServer {
    private static final int PORT = 8080;
    // private static ExecutorService fixedThreadPoor = Executors.newFixedThreadPool(10);

    public void webServerSerivice() throws Exception {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                // 每收到一个请求，线程池开启/启动一个线程处理，太菜，不如不用
                // fixedThreadPoor.execute(new HttpResponse(socket));
                new HttpResponse(socket).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // fixedThreadPoor.shutdown();
        }
    }
}
