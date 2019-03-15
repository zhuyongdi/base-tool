package com.zhuyongdi.basetool.tool;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期工具类
 * Created by Administrator on 2019/3/15.
 */
public class DateUtil {

    private static final String[] WEEK = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    private static final String DEFAULT_FORMAT_YND_HMS = "yyyy-MM-dd HH:mm:ss";
    private static final String DEFAULT_FORMAT_YND = "yyyy-MM-dd";
    private static final String DEFAULT_FORMAT_HMS = "HH:mm:ss";
    private static final String DEFAULT_FORMAT_HM = "HH:mm";

    /******************************************* 时间获取 *****************************************/

    /**
     * 时间获取:获取当前年月日时分秒
     */
    private static String getNowYNDHMS() {
        return new SimpleDateFormat(DEFAULT_FORMAT_YND_HMS, Locale.CHINA).format(new Date());
    }

    /**
     * 时间获取:获取当前年月日
     */
    public static String getNowYND() {
        return new SimpleDateFormat(DEFAULT_FORMAT_YND, Locale.CHINA).format(new Date());
    }

    /**
     * 时间获取:获取当前时分秒
     */
    public static String getNowHMS() {
        return new SimpleDateFormat(DEFAULT_FORMAT_HMS, Locale.CHINA).format(new Date());
    }

    /**
     * 时间获取:获取当前时分
     */
    public static String getNowHM() {
        return new SimpleDateFormat(DEFAULT_FORMAT_HM, Locale.CHINA).format(new Date());
    }

    /**
     * 时间获取:获取当前时间的UNIX时间戳
     */
    public static long getNowUnixTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 时间获取:获取指定时间是星期几
     */
    private static String getWeekByDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return WEEK[w];
    }

    /******************************************* 时间转换 *****************************************/

    /**
     * 时间转换: 2019-02-19 19:20:14-->Date
     */
    public static Date string2Date(String time, String format) {
        if (format == null || "".equals(format)) {
            format = DEFAULT_FORMAT_YND_HMS;
        }
        try {
            return new SimpleDateFormat(format, Locale.CHINA).parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /******************************************* 时间截取 *****************************************/

    /**
     * 时间截取:2019-02-19 19:20:14-->2019-02-19
     */
    public static String trimTime2YND(String s) {
        if (s == null) {
            return "";
        }
        if (s.length() < 10) {
            return s;
        }
        return s.substring(0, 10);
    }

    /**
     * 时间截取:2019-02-19 19:20:14-->02-19
     */
    public static String trimTime2ND(String s) {
        if (s == null) {
            return "";
        }
        if (s.length() < 10) {
            return s;
        }
        return s.substring(5, 10);
    }

    /**
     * 时间截取:2019-02-19 19:20:14-->19:20:14
     */
    public static String trimTime2HMS(String s) {
        if (s == null) {
            return "";
        }
        if (s.length() < 19) {
            return s;
        }
        return s.substring(11, 19);
    }

    /**
     * 时间截取:2019-02-19 19:20:14-->19:20
     */
    public static String trimTime2HM(String s) {
        if (s == null) {
            return "";
        }
        if (s.length() < 16) {
            return s;
        }
        return s.substring(11, 16);
    }

    /******************************************* 时间判断 *****************************************/

    /**
     * 时间判断:判断两天是否在同一周
     */
    public static boolean isInSameWeek(Date date1, Date date2) {
        Calendar date1Cal = Calendar.getInstance();
        Calendar date2Cal = Calendar.getInstance();
        date1Cal.setTime(date1);
        date2Cal.setTime(date2);
        int date1Week = date1Cal.get(Calendar.WEEK_OF_YEAR);
        if ("星期日".equals(getWeekByDate(date1))) {
            date1Week -= 1;
        }
        int date2Week = date2Cal.get(Calendar.WEEK_OF_YEAR);
        if ("星期日".equals(getWeekByDate(date2))) {
            date2Week -= 1;
        }
        return date1Week == date2Week;
    }

    /**
     * 时间判断:判断两个时间相差距离多少天多少小时多少分多少秒
     *
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return long[] 返回值为：{天, 时, 分, 秒}
     */
    public static long[] getDistanceTimes(String str1, String str2) {
        DateFormat df = new SimpleDateFormat(DEFAULT_FORMAT_YND_HMS, Locale.CHINA);
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long[] times = {day, hour, min, sec};
        return times;
    }

    /**
     * 时间判断:判断两个时间是否相差五分钟
     */
    public static boolean isMoreThan5Minutes(String time1, String time2) {
        long[] dif = getDistanceTimes(time1, time2);
        if (dif[0] != 0 || dif[1] != 0) {
            return true;
        } else {
            if (dif[2] <= 5) {
                return false;
            }
        }
        return true;
    }

}
