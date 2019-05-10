package com.zhuyongdi.basetool.tool.validate;

import com.zhuyongdi.basetool.constants.CommonRegEx;

/**
 * 数字验证工具类
 * Created by ZhuYongdi on 2019/4/18.
 */
public class NumberValidateTool {

    //判断是否为数字
    //true(000),(0001),(0123.123)
    //false(11.123.123)
    public static boolean isNumber(String input) {
        return PatternMatcher.isMatchByRegEx(CommonRegEx.REGEX_NUMBER, input);
    }

    //验证是否为0
    //true:(0),(-0),(+0)
    //false:(000)
    public static boolean isZero(CharSequence input) {
        return PatternMatcher.isMatchByRegEx(CommonRegEx.REGEX_ZERO, input);
    }

    //验证是否是整数
    //true:(0),(-0),(+0)
    //false:(003)
    public static boolean isInteger(CharSequence input) {
        return isZero(input) || isIntegerPositive(input) || isIntegerNegative(input);
    }

    //验证是否是正整数
    //true:(1),(123)
    //false:(001)
    public static boolean isIntegerPositive(CharSequence input) {
        return PatternMatcher.isMatchByRegEx(CommonRegEx.REGEX_INTEGER_POSITIVE, input);
    }

    // 验证是否是负整数
    //true:(-1),(-123)
    //false:(-001)
    public static boolean isIntegerNegative(CharSequence input) {
        return PatternMatcher.isMatchByRegEx(CommonRegEx.REGEX_INTEGER_NEGATIVE, input);
    }

    //验证是否是浮点数
    //true:(0.111),(-0.111),
    //false:(0.000),(0)
    public static boolean isFloat(CharSequence input) {
        return isFloatPositive(input) || isFloatNegative(input);
    }

    //验证是否是正浮点数
    //true:(0.1),(0.111)
    //false:(0.0)
    public static boolean isFloatPositive(CharSequence input) {
        return PatternMatcher.isMatchByRegEx(CommonRegEx.REGEX_FLOAT_POSITIVE, input);
    }

    //验证是否是负浮点数
    //true:(-0.1),(-0.111)
    //false:(0.0)
    public static boolean isFloatNegative(CharSequence input) {
        return PatternMatcher.isMatchByRegEx(CommonRegEx.REGEX_FLOAT_NEGATIVE, input);
    }

}
