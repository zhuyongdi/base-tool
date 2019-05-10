package com.zhuyongdi.basetool.tool.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;

/**
 * Bitmap转换工具类
 * Created by ZhuYongdi on 2019/4/17.
 */
public class BitmapConvertHandler {

    /*---------------resource转换---------------*/

    //resId转bitmap
    public static Bitmap resourceToBitmap(Context context, int resId) {
        return BitmapFactory.decodeResource(context.getResources(), resId);
    }

    //resId转drawable
    public static Drawable resourceToDrawable(Context context, int resId) {
        return context.getResources().getDrawable(resId);
    }

    //resId转byte
    public static byte[] resourceToBytes(Context context, int resId) {
        Bitmap bitmap = resourceToBitmap(context, resId);
        return bitmapToBytes(bitmap);
    }

    /*-----------------byte转换-----------------*/

    //byte转bitmap
    public static Bitmap byteToBitmap(byte[] bytes) {
        return (bytes == null || bytes.length == 0) ? null : BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    //byte转drawable
    public static Drawable byteToDrawable(Context context, byte[] bytes) {
        Bitmap bitmap = byteToBitmap(bytes);
        return bitmapToDrawable(context, bitmap);
    }

    /*-----------------bitmap转换-----------------*/

    //bitmap转drawable
    public static Drawable bitmapToDrawable(Context context, Bitmap bitmap) {
        return bitmap == null ? null : new BitmapDrawable(context.getResources(), bitmap);
    }

    //bitmap转byte
    public static byte[] bitmapToBytes(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }

}
