package com.zz.tcpcs;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * @author Zhang Zhen
 * @time 2019��9��20�� ����8:52:43
 */
public class TCPClient {
    public static void main(String[] args) {
        TCPClient tcpClient = new TCPClient();
        try {
            tcpClient.clientService();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clientService() throws IOException {
        int cnt = 5; // �������
        Socket socket = null;
        try {
            while (cnt > 0) {
                socket = new Socket(SerialSR.getIp(), SerialSR.getsPort());
                SerialSR.reciveInfo(socket).showInfo();
                cnt--;
                TimeUnit.SECONDS.sleep(1); // ������ʱ��
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }
}
