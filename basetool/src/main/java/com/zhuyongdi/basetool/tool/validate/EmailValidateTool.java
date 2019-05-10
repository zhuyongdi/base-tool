package com.zhuyongdi.basetool.tool.validate;

import com.zhuyongdi.basetool.constants.CommonRegEx;

/**
 * Created by ZhuYongdi on 2019/4/18.
 */
public class EmailValidateTool {

    /**
     * 验证是否是电子邮箱
     * true:(90@qq.com)
     * false:(@qq.com),(90@),(90@qq)
     */
    public static boolean isEmail(CharSequence input) {
        return PatternMatcher.isMatchByRegEx(CommonRegEx.REGEX_EMAIL, input);
    }

}
