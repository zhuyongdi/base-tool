package com.zhuyongdi.basetool.tool.validate;

import com.zhuyongdi.basetool.constants.CommonRegEx;

/**
 * 汉字验证工具类
 * Created by ZhuYongdi on 2019/4/18.
 */
public class ChineseCharValidateTool {

    //验证是否是汉字
    public static boolean isChineseChar(CharSequence input) {
        return PatternMatcher.isMatchByRegEx(CommonRegEx.REGEX_CHINESE_CHAR, input);
    }

}
