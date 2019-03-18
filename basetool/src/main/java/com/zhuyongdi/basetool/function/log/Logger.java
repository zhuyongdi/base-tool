package com.zhuyongdi.basetool.function.log;

import android.util.Log;

/**
 * Log工具类
 * Created by Administrator on 2019/3/18.
 */
public class Logger {

    private static final String TAG = "Logger";
    private static LogHolder holder;

    public static void init(boolean isLog) {
        holder = new LogHolder(isLog);
    }

    public static void v(String tag, String msg) {
        if (holder == null) {
            Log.e(TAG, "please register this in application");
            return;
        }
        if (holder.log) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (holder == null) {
            Log.e(TAG, "please register this in application");
            return;
        }
        if (holder.log) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (holder == null) {
            Log.e(TAG, "please register this in application");
            return;
        }
        if (holder.log) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (holder == null) {
            Log.e(TAG, "please register this in application");
            return;
        }
        if (holder.log) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (holder == null) {
            Log.e(TAG, "please register this in application");
            return;
        }
        if (holder.log) {
            Log.e(tag, msg);
        }
    }

    private static final class LogHolder {

        private boolean log;

        private LogHolder(boolean log) {
            this.log = log;
        }
    }


}
