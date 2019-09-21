package com.zz.tcpcs;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * @author Zhang Zhen
 * @time 2019年9月20日 上午8:53:07
 */
public class TCPServer {
    public static void main(String[] args) {
        TCPServer tcpServer = new TCPServer();
        try {
            tcpServer.serverService();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public RequireInfos getInfo() {
        Random random = new Random();
        int temperature = random.nextInt(100);
        int humidity = random.nextInt(100);
        int lightData = random.nextInt(100);
        return new RequireInfos(temperature, humidity, lightData);
    }

    public void serverService() throws IOException {
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(SerialSR.getsPort());
            while (true) {
                socket = serverSocket.accept();
                SerialSR.sendInfo(getInfo(), socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            serverSocket.close();
            socket.close();
        }
    }
}
