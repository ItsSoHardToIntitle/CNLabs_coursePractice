package com.zz.smtp;

import java.io.UnsupportedEncodingException;

import com.owtelse.codec.Base64;

/**
 * @author Zhang Zhen
 * @time 2019��9��20�� ����7:26:13
 */
public class Base64ED {
    // ����
    public static String encodeBase64(byte[] data) {
        String string = null;
        try {
            string = Base64.encode(data);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return string;
    }

    // ����
    public static String decodeBase64(String string) {
        byte[] data = null;
        try {
            data = Base64.decode(string);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new String(data);
    }
}
