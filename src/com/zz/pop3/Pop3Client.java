package com.zz.pop3;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Zhang Zhen
 * @time 2019��9��21�� ����8:52:59
 */
public class Pop3Client {

    private static final String USER_NAME = "m18801288750_1@163.com";
    private static final String PASSWORD = "��Ȩ��";
    private static final String IP_163 = "pop3.163.com";
    private static final int PORT_163 = 110;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(IP_163, PORT_163);
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream, true);

            // 1. user����
            System.out.println("��Pop3 Server��:" + bufferedReader.readLine());
            printWriter.println("user " + USER_NAME);
            System.out.println("��Pop3 Server��:" + bufferedReader.readLine());
            // 2. pass����
            printWriter.println("pass " + PASSWORD);
            System.out.println("��Pop3 Server��:" + bufferedReader.readLine());
            // 3. stat����
            printWriter.println("stat");
            String[] strings = bufferedReader.readLine().split(" ");
            int cnt = Integer.parseInt(strings[1]); // �ʼ�����
            for (int i = 1; i <= cnt; i++) {
                // 4. retr����
                printWriter.println("retr " + i);
                System.out.println("�����ǵ�" + i + "���ʼ������ݡ�:");
                while (true) {
                    String string = bufferedReader.readLine();
                    // System.out.println(convert(string));
                    System.out.println(string);
                    if (string.toLowerCase().equals(".")) {
                        break;
                    }
                }
            }
            socket.close();
            bufferedReader.close();
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // ���ʼ����⡢���ĵȽ��н���
    // ����ͬ�ķ��ͷ�������ʱ��ȡ�ı��뷽ʽ��utf-8��gbkxxx)�������֣��Լ�
    // ���ܷ�ʽҲ��ͬ��Base64�ȣ�������ѶȽϴ����鷳���������ޣ��ʶ�ʡȥ����
    public static String convert(String string) {
        String res = "";
        return res;
    }
}
