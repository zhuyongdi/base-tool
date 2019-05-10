package com.zhuyongdi.basetool.tool.validate;

import com.zhuyongdi.basetool.constants.CommonRegEx;

/**
 * 手机号验证工具类
 * Created by ZhuYongdi on 2019/4/18.
 */
public class PhoneValidateTool {

    //验证是否是手机号(只判断是否是11位)
    public static boolean isPhoneSimple(CharSequence input) {
        return PatternMatcher.isMatchByRegEx(CommonRegEx.REGEX_MOBILE_SIMPLE, input);
    }

    //验证是否是手机号(判断11位,并且判断手机号开头)
    public static boolean isPhoneExact(CharSequence input) {
        return PatternMatcher.isMatchByRegEx(CommonRegEx.REGEX_MOBILE_EXACT, input);
    }

}
