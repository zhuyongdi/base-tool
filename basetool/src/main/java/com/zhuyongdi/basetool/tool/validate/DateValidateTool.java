package com.zhuyongdi.basetool.tool.validate;

import com.zhuyongdi.basetool.constants.CommonRegEx;

/**
 * 日期验证工具类
 * Created by ZhuYongdi on 2019/4/18.
 */
public class DateValidateTool {

    /**
     * 验证是否为日期,已考虑平闰年
     * true:(2018-2-28),(2018-02-28),(2018-2-2)
     * false:(2018-2-28 ),( 2018-2-28),(2019-3-1 16:41:41),
     */
    public static boolean isDate(String input) {
        return PatternMatcher.isMatchByRegEx(CommonRegEx.REGEX_DATE, input);
    }

}
