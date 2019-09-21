package com.zz.pop3;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Zhang Zhen
 * @time 2019年9月21日 上午8:52:59
 */
public class Pop3Client {

    private static final String USER_NAME = "m18801288750_1@163.com";
    private static final String PASSWORD = "授权码";
    private static final String IP_163 = "pop3.163.com";
    private static final int PORT_163 = 110;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(IP_163, PORT_163);
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream, true);

            // 1. user命令
            System.out.println("【Pop3 Server】:" + bufferedReader.readLine());
            printWriter.println("user " + USER_NAME);
            System.out.println("【Pop3 Server】:" + bufferedReader.readLine());
            // 2. pass命令
            printWriter.println("pass " + PASSWORD);
            System.out.println("【Pop3 Server】:" + bufferedReader.readLine());
            // 3. stat命令
            printWriter.println("stat");
            String[] strings = bufferedReader.readLine().split(" ");
            int cnt = Integer.parseInt(strings[1]); // 邮件总数
            for (int i = 1; i <= cnt; i++) {
                // 4. retr命令
                printWriter.println("retr " + i);
                System.out.println("【这是第" + i + "封邮件的内容】:");
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

    // 对邮件标题、正文等进行解码
    // 但不同的发送方，发送时采取的编码方式（utf-8、gbkxxx)等若干种，以及
    // 加密方式也不同（Base64等），因此难度较大且麻烦，能力有限，故而省去……
    public static String convert(String string) {
        String res = "";
        return res;
    }
}
