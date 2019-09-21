package com.zz.smtp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Zhang Zhen
 * @time 2019��9��20�� ����5:18:02
 */
public class SMTPClient {
    private static final String SENDER = "m18801288750_1@163.com";
    private static final String PASSWORD = "�˴��������Ȩ�루���ǵ�½���룩";
    private static final String RECEVIER = "2089064610@qq.com";
    private static final String IP_163 = "smtp.163.com";
    private static final int PORT_163 = 25;

    public static void main(String[] args) {
        // ���û������������Base64����
        String userName = Base64ED.encodeBase64(SENDER.getBytes());
        String password = Base64ED.encodeBase64(PASSWORD.getBytes());

        Socket socket = null;
        try {
            socket = new Socket(IP_163, PORT_163);
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            PrintWriter printWriter = new PrintWriter(outputStreamWriter, true);
            // ����һЩsmtp�����ʾ�������ķ��������Ӧ��Ϣ
            // 1. helo����
            printWriter.println("helo zz");
            System.out.println(bufferedReader.readLine());

            // 2. auth login����
            printWriter.println("auth login");
            System.out.println(bufferedReader.readLine());
            printWriter.println(userName);
            System.out.println(bufferedReader.readLine());
            printWriter.println(password);
            System.out.println(bufferedReader.readLine());

            // 3. mail from �� rect to��������ʼ��ķ��ͷ��ͽ��շ���
            printWriter.println("mail from:<" + SENDER + ">");
            System.out.println(bufferedReader.readLine());
            printWriter.println("rcpt to:<" + RECEVIER + ">");
            System.out.println(bufferedReader.readLine());

            // 4. data����ͻ��˷��͵ġ����������ʼ����ݴ�������
            printWriter.println("data");
            System.out.println(bufferedReader.readLine());

            // 5. �ʼ�����
            // ��Ҳ�Ǻ�����ˣ���������1h��һֱ������ȥ������̨״̬��ɹ���
            // �������䶼û���շ���Ϣ����������������ֽ���ʼ����ݼ��ϸ�Ů�����֣�
            // Ī���ͺ��ˣ�������
            printWriter.println("subject: ʵ������");
            printWriter.println("from:" + SENDER);
            printWriter.println("to:" + RECEVIER);
            printWriter.println("Content-Type: text/plain;charset=\"gb2312\"");
            printWriter.println("");
            printWriter.println("hello, fmtp! Zhang XueYing(��Ȼ����Ů�񣬾��ܳɹ���");
            printWriter.println(".");
            printWriter.println("");
            System.out.println(bufferedReader.readLine());

            // 6. rset����
            printWriter.println("rset");
            System.out.println(bufferedReader.readLine());

            // 7. quit����
            printWriter.println("quit");
            System.out.println(bufferedReader.readLine());

            socket.close();
            bufferedReader.close();
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
