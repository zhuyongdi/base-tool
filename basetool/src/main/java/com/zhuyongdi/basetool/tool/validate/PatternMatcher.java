package com.zhuyongdi.basetool.tool.validate;

import com.zhuyongdi.basetool.tool.string.CharSequenceTool;

import java.util.regex.Pattern;

/**
 * Created by ZhuYongdi on 2019/4/18.
 */
public class PatternMatcher {

    //验证正则表达式是否匹配
    public static boolean isMatchByRegEx(String regEx, CharSequence input) {
        return CharSequenceTool.isNotEmpty(input) && Pattern.matches(regEx, input);
    }

}
