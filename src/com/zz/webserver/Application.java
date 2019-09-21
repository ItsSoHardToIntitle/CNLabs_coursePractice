package com.zz.webserver;
/**
*@author Zhang Zhen
*@time 2019年9月20日 下午1:40:46
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
