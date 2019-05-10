package com.zhuyongdi.basetool.tool.string;

/**
 * Created by ZhuYongdi on 2019/4/18.
 */
public class CharSequenceTool {

    //判断为空
    public static boolean isEmpty(CharSequence c) {
        return c == null || c.length() == 0;
    }

    //判断不为空
    public static boolean isNotEmpty(CharSequence c) {
        return c != null && c.length() != 0;
    }

}
