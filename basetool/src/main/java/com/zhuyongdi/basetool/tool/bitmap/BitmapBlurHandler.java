package com.zhuyongdi.basetool.tool.bitmap;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.renderscript.RSRuntimeException;

import com.zhuyongdi.basetool.transformations.FastBlurTransformation;
import com.zhuyongdi.basetool.transformations.RSBlurTransformation;


/**
 * Bitmap模糊处理工具类
 * Created by ZhuYongdi on 2019/4/17.
 */
public class BitmapBlurHandler {

    /**
     * 添加高斯模糊
     */
    @SuppressLint("ObsoleteSdkInt")
    public static Bitmap addGaussBlur(Context context, Bitmap bitmap, int radius) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            try {
                return addGaussBlurByRSBlur(context, bitmap, radius);
            } catch (RSRuntimeException e) {
                return addGaussBlurByFastBlur(bitmap, radius);
            }
        } else {
            return addGaussBlurByFastBlur(bitmap, radius);
        }
    }

    /**
     * 添加网易云音乐播放界面的模糊
     */
    public static Bitmap addNeteaseGaussBlur(Context context, Bitmap srcBitmap) {
        if (srcBitmap == null) {
            return null;
        }
        int srcWidth = srcBitmap.getWidth();
        int srcHeight = srcBitmap.getHeight();
        int compare = Math.max(srcWidth, srcHeight);
        int ratio = compare / 20;
        int toWidth = srcWidth / ratio;
        int toHeight = srcHeight / ratio;
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(srcBitmap, toWidth, toHeight, false);
        return addGaussBlur(context, scaledBitmap, 10);
    }

    /**
     * 添加高斯模糊(Glide的FastBlur算法)
     */
    public static Bitmap addGaussBlurByFastBlur(Bitmap bitmap, int radius) {
        return bitmap == null ? null : FastBlurTransformation.transfer(bitmap, radius, true);
    }

    /**
     * 添加高斯模糊(Glide的)
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static Bitmap addGaussBlurByRSBlur(Context context, Bitmap bitmap, int radius) {
        return bitmap == null ? null : RSBlurTransformation.transfer(context, bitmap, radius);
    }

}
