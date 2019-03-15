package com.zhuyongdi.basetool.tool;

/**
 * 字符串工具类
 * Created by Administrator on 2019/3/15.
 */
public class StringUtil {

    private StringUtil() {
        /* cannot be instantiated */
    }

    //字符串是否为空
    public static boolean isEmpty(String s) {
        return s == null || s.trim().length() == 0;
    }

    //字符串是否不为空
    public static boolean isNotEmpty(String s) {
        return s != null && s.trim().length() != 0;
    }

    /**
     * 替换所有指定字符串
     * trimAll("222--333--444","--","??")-->("222??333??444")
     */
    public static String replaceAll(String src, String regex, String replacement) {
        if (isEmpty(src)) {
            return "";
        }
        if (isEmpty(regex)) {
            return src;
        }
        String result = src;
        try {
            result = src.replaceAll(regex, replacement);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 剪切所有指定字符串
     * trimAll("222--333--444","--")-->("222333444")
     * trimAll("222--333--444","2")-->("--333--444")
     */
    public static String trimAll(String src, String regex) {
        if (isEmpty(src)) {
            return "";
        }
        if (isEmpty(regex)) {
            return src;
        }
        StringBuilder sb = new StringBuilder(src);
        int regexSize = regex.length();
        int index = 0;
        while ((index = sb.indexOf(regex)) != -1) {
            sb.delete(index, index + regexSize);
        }
        return sb.toString();
    }

    /**
     * 裁剪字符串头部尾部的空格
     * trimHeadTail("  1  ")-->裁剪为"1"
     */
    public static String trimHeadTail(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (!" ".equals(String.valueOf(s.charAt(i)))) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

}
