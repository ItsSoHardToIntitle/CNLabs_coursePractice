package com.zz.smtp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Zhang Zhen
 * @time 2019年9月20日 下午5:18:02
 */
public class SMTPClient {
    private static final String SENDER = "m18801288750_1@163.com";
    private static final String PASSWORD = "此处填你的授权码（不是登陆密码）";
    private static final String RECEVIER = "2089064610@qq.com";
    private static final String IP_163 = "smtp.163.com";
    private static final int PORT_163 = 25;

    public static void main(String[] args) {
        // 对用户名和密码进行Base64编码
        String userName = Base64ED.encodeBase64(SENDER.getBytes());
        String password = Base64ED.encodeBase64(PASSWORD.getBytes());

        Socket socket = null;
        try {
            socket = new Socket(IP_163, PORT_163);
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            PrintWriter printWriter = new PrintWriter(outputStreamWriter, true);
            // 发送一些smtp命令并显示服务器的返回码和相应信息
            // 1. helo命令
            printWriter.println("helo zz");
            System.out.println(bufferedReader.readLine());

            // 2. auth login命令
            printWriter.println("auth login");
            System.out.println(bufferedReader.readLine());
            printWriter.println(userName);
            System.out.println(bufferedReader.readLine());
            printWriter.println(password);
            System.out.println(bufferedReader.readLine());

            // 3. mail from 和 rect to命令（设置邮件的发送方和接收方）
            printWriter.println("mail from:<" + SENDER + ">");
            System.out.println(bufferedReader.readLine());
            printWriter.println("rcpt to:<" + RECEVIER + ">");
            System.out.println(bufferedReader.readLine());

            // 4. data命令（客户端发送的、用于启动邮件内容传输的命令）
            printWriter.println("data");
            System.out.println(bufferedReader.readLine());

            // 5. 邮件正文
            // （也是很奇怪了，昨天晚上1h都一直发不出去，控制台状态码成功，
            // 两个邮箱都没有收发信息，今天起来换个壁纸，邮件内容加上个女神名字，
            // 莫名就好了？？？）
            printWriter.println("subject: 实践安排");
            printWriter.println("from:" + SENDER);
            printWriter.println("to:" + RECEVIER);
            printWriter.println("Content-Type: text/plain;charset=\"gb2312\"");
            printWriter.println("");
            printWriter.println("hello, fmtp! Zhang XueYing(果然，信女神，就能成功）");
            printWriter.println(".");
            printWriter.println("");
            System.out.println(bufferedReader.readLine());

            // 6. rset命令
            printWriter.println("rset");
            System.out.println(bufferedReader.readLine());

            // 7. quit命令
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
