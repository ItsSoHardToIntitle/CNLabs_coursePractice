package com.zz.webserver;
/**
*@author Zhang Zhen
*@time 2019��9��20�� ����1:40:46
*/
public class Application {

    public static void main(String[] args) {
        try {
            new WebServer().webServerSerivice();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
