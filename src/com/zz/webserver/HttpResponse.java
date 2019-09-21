package com.zz.webserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author Zhang Zhen
 * @time 2019��9��20�� ����1:44:11
 */
public class HttpResponse extends Thread {
    // �ʼ������/src��������
    private static final String ROOT_PATH = System.getProperty("user.dir") + File.separator + "src" + File.separator
            + "WEB-INFO" + File.separator;
    @SuppressWarnings("unused")
    private Socket socket;
    private static InputStream inputStream;
    private static OutputStream outputStream;

    public HttpResponse(Socket socket) {
        this.socket = socket;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ��ȡ�ͻ������������Դ�ļ�
    public String getUriPath() {
        // ����http����ı��ĸ�ʽ����һ�У����󷽷� uri Э��/�汾�������Կո�ָ���
        String uriPath = null;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String readLine = "";
        try {
            while ((readLine = bufferedReader.readLine()) != null && (!readLine.equals(""))) {
                String[] firstRow = readLine.split(" ");
                uriPath = firstRow[1].substring(1, firstRow[1].length());
                return uriPath;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uriPath;
    }

    public String responseHtml(String uri) {
        String resouces = "";
        String resoucesPath = ROOT_PATH + uri;
        File file = new File(resoucesPath);
        // ���������Դ�Ƿ����
        if (file.exists()) {
            try {
                @SuppressWarnings("resource")
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                StringBuffer temp = new StringBuffer();
                String readLine = "";
                while ((readLine = bufferedReader.readLine()) != null) {
                    temp.append(readLine).append("\r\n");
                }
                StringBuffer result = new StringBuffer();
                result.append("HTTP/1.1 200 ok\r\n");
                // ��index.html�У��ַ����뷽ʽ��gb2312���ĲŲ�������
                result.append("http-equiv=Content-Type; content=text/html; charset=UTF-8\r\n");
                result.append("\r\n" + temp.toString());
                outputStream.write(result.toString().getBytes());
                outputStream.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else { // �����ڣ�Google�Լ���404
        }
        return resouces;
    }

    @Override
    public void run() {
        // Step1.������uri
        // Step2.�����������Դ���˴���̬��html�ļ���
        responseHtml(getUriPath());
    }
}
