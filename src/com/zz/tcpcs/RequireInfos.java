package com.zz.tcpcs;

import java.io.Serializable;

/**
 * @author Zhang Zhen
 * @time 2019年9月20日 上午9:01:13
 */

/*
 * 客户端所要获取的信息：温度、湿度和光强度
 */
@SuppressWarnings("serial")
public class RequireInfos implements Serializable{
    int temperature;
    int humidity;
    int lightData;

    public RequireInfos(int temperature, int humidity, int lightData) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.lightData = lightData;
    }

    public void showInfo() {
        System.out.println("TEMPERATURE = " + temperature + " HUMIDITY = " + humidity + " LIGHT = " + lightData);
        ;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getLightData() {
        return lightData;
    }
}
