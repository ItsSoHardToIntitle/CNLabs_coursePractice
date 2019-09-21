package com.zz.tcpcs;

import java.io.Serializable;

/**
 * @author Zhang Zhen
 * @time 2019��9��20�� ����9:01:13
 */

/*
 * �ͻ�����Ҫ��ȡ����Ϣ���¶ȡ�ʪ�Ⱥ͹�ǿ��
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
