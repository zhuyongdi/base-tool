package com.zhuyongdi.basetool.tool;

import java.io.UnsupportedEncodingException;

/**
 * URL工具类
 * Created by Administrator on 2019/3/15.
 */
public class URLEncoder {

    private static final String ENCODE = "UTF-8";

    /**
     * URL转码,呵呵-->%E5%91%B5%E5%91%B5
     */
    public static String normal2URL(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str, ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * URL转码,呵呵-->%E5%91%B5%E5%91%B5
     */
    public static String normal2URL(String str, String charset) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str, (charset == null || "".equals(charset)) ? ENCODE : charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * URL解码,%E5%91%B5%E5%91%B5-->呵呵
     */
    public static String url2Normal(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLDecoder.decode(str, ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * URL解码,%E5%91%B5%E5%91%B5-->呵呵
     */
    public static String url2Normal(String str, String charset) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLDecoder.decode(str, (charset == null || "".equals(charset)) ? ENCODE : charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

}
