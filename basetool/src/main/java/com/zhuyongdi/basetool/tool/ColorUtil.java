package com.zhuyongdi.basetool.tool;

import android.graphics.Color;
import android.support.annotation.FloatRange;

/**
 * Created by ZhuYongdi on 2019/4/4.
 */
public class ColorUtil {

    public static String getAlphaColorStr(String colorWithFullAlpha, @FloatRange(from = 0f, to = 1.0f) float alpha) {
        String a = "##";
        String b = Integer.toHexString((int) (255 * alpha));
        return a + (b.length() == 1 ? ("0" + b) : b) + colorWithFullAlpha;
    }

    public static int getAlphaColor(String colorWithFullAlpha, @FloatRange(from = 0f, to = 1.0f) float alpha) {
        String a = "##";
        String b = Integer.toHexString((int) (255 * alpha));
        String c = a + (b.length() == 1 ? ("0" + b) : b) + colorWithFullAlpha;
        Color color=new Color();
        return Color.parseColor(c);
    }


}
