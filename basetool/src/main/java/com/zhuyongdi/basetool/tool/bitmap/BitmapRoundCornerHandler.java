package com.zhuyongdi.basetool.tool.bitmap;

import android.content.Context;
import android.graphics.Bitmap;

import com.zhuyongdi.basetool.transformations.CircleTransformation;
import com.zhuyongdi.basetool.transformations.RoundedCornersTransformation;


/**
 * Bitmap圆角
 * Created by ZhuYongdi on 2019/4/17.
 */
public class BitmapRoundCornerHandler {

    /**
     * Bitmap圆角
     */
    public static Bitmap addRoundCorner(Context context, Bitmap bitmap, int radius) {
        if (bitmap == null) {
            return null;
        }
        return new RoundedCornersTransformation(context, radius, 0).transfer(bitmap);
    }

    /**
     * Bitmap圆角
     */
    public static Bitmap addRoundCorner(Context context, Bitmap bitmap, int radius, RoundedCornersTransformation.CornerType type) {
        if (bitmap == null) {
            return null;
        }
        return new RoundedCornersTransformation(context, radius, 0, type).transfer(bitmap);
    }

    /**
     * Bitmap圆
     */
    public static Bitmap addCircle(Bitmap source) {
        if (source == null) {
            return null;
        }
        return new CircleTransformation().transfer(source);
    }

}
