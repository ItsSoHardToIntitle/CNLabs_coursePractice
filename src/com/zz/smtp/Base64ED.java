package com.zz.smtp;

import java.io.UnsupportedEncodingException;

import com.owtelse.codec.Base64;

/**
 * @author Zhang Zhen
 * @time 2019年9月20日 下午7:26:13
 */
public class Base64ED {
    // 编码
    public static String encodeBase64(byte[] data) {
        String string = null;
        try {
            string = Base64.encode(data);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return string;
    }

    // 解码
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
