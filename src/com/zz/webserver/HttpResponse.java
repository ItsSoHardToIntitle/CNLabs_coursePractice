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
 * @time 2019年9月20日 下午1:44:11
 */
public class HttpResponse extends Thread {
    // 最开始忘记了/src，哎……
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

    // 获取客户端所请求的资源文件
    public String getUriPath() {
        // 依据http请求的报文格式，第一行：请求方法 uri 协议/版本（三者以空格分隔）
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
        // 所请求的资源是否存在
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
                // 在index.html中，字符编码方式是gb2312中文才不是乱码
                result.append("http-equiv=Content-Type; content=text/html; charset=UTF-8\r\n");
                result.append("\r\n" + temp.toString());
                outputStream.write(result.toString().getBytes());
                outputStream.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else { // 不存在，Google自己：404
        }
        return resouces;
    }

    @Override
    public void run() {
        // Step1.解析出uri
        // Step2.返回请求的资源（此处静态的html文件）
        responseHtml(getUriPath());
    }
}
