package com.zz.tcpcs;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

/**
 * @author Zhang Zhen
 * @time 2019��9��20�� ����10:17:12
 */
@SuppressWarnings("serial")
public class SerialSR implements Serializable {
    private static final String IP = "127.0.0.1";
    private static final int S_PORT = 8889;

    // TCP���л�������Ϣ
    public static void sendInfo(Object object, Socket socket) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(object);
            objectOutputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // TCP���л�������Ϣ
    public static RequireInfos reciveInfo(Socket socket) {
        RequireInfos requireInfos = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Object object = objectInputStream.readObject();
            if (object instanceof RequireInfos) {
                requireInfos = (RequireInfos) object;
            }
            objectInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requireInfos;

    }

    public static String getIp() {
        return IP;
    }

    public static int getsPort() {
        return S_PORT;
    }
}
