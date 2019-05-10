package com.zhuyongdi.basetool.tool.device;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

import java.lang.reflect.Method;

/**
 * 设备网络工具类
 * Created by ZhuYongdi on 2019/4/17.
 */
@SuppressLint("MissingPermission")
public class DeviceNetTool {

    //获取当前网络信息
    private static NetworkInfo getCurrentNetworkInfo(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager == null ? null : connectivityManager.getActiveNetworkInfo();
    }

    //判断是否联网
    public static boolean isConnectedToNet(Context context) {
        NetworkInfo info = getCurrentNetworkInfo(context);
        return info != null && info.isConnected();
    }

    //判断WIFI是否打开
    public static boolean isWIFIOpened(Context context) {
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        return wifiManager != null && wifiManager.isWifiEnabled();
    }

    //判断WIFI是否连接
    public static boolean isWIFIConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager != null
                && connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
    }

    //打开或关闭WIFI
    public static void openOrCloseWIFI(Context context, boolean enable) {
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (enable) {
            if (wifiManager != null && !wifiManager.isWifiEnabled()) {
                wifiManager.setWifiEnabled(true);
            }
        } else {
            if (wifiManager != null && wifiManager.isWifiEnabled()) {
                wifiManager.setWifiEnabled(false);
            }
        }
    }

    //判断移动网络是否打开
    @SuppressLint("PrivateApi")
    public static boolean isMobileNetEnable(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        try {
            if (telephonyManager != null) {
                Method method = telephonyManager.getClass().getDeclaredMethod("getDataEnabled");
                return (boolean) method.invoke(telephonyManager);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //打开或关闭移动网络
    @SuppressLint("PrivateApi")
    public static void openOrCloseMobileNet(Context context, boolean enable) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        try {
            if (telephonyManager != null) {
                Method method = telephonyManager.getClass().getDeclaredMethod("setDataEnable", boolean.class);
                method.invoke(telephonyManager, enable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
