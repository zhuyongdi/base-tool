package com.zhuyongdi.basetool.tool.validate;

import com.zhuyongdi.basetool.constants.ChinaAreaIDCode;
import com.zhuyongdi.basetool.constants.CommonRegEx;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Locale;

/**
 * 身份证验证工具类
 * Created by ZhuYongdi on 2019/4/18.
 */
public class IDCardValidateTool {

    private static final String[] W_F = {"1", "0", "x", "9", "8", "7", "6", "5", "4", "3", "2"};
    private static final String[] CHECK_CODE = {"7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2"};

    /**
     * 验证是否是身份证号:
     * 1.18位或15位
     * 2.出生日期合法
     */
    public static boolean isIDCardSimple(String input) {
        return PatternMatcher.isMatchByRegEx(CommonRegEx.REGEX_ID_CARD15, input) || PatternMatcher.isMatchByRegEx(CommonRegEx.REGEX_ID_CARD18, input);
    }

    /**
     * 验证是否是身份证号:
     * 1.18位或15位
     * 2.出生日期合法
     * 3.地区码合法
     * 4.最后一位合法
     */
    public static boolean isIDCardExact(String input) {
        String iDCardNo = "";
        try {
            //判断号码的长度 15位或18位
            if (input.length() != 15 && input.length() != 18) {
                return false;
            }
            if (input.length() == 18) {
                iDCardNo = input.substring(0, 17);
            } else {
                iDCardNo = input.substring(0, 6) + "19" + input.substring(6, 15);
            }
            if (NumberValidateTool.isNumber(iDCardNo)) {
                return false;
            }
            //判断出生年月
            String strYear = iDCardNo.substring(6, 10);// 年份
            String strMonth = iDCardNo.substring(10, 12);// 月份
            String strDay = iDCardNo.substring(12, 14);// 月份
            if (DateValidateTool.isDate(strYear + "-" + strMonth + "-" + strDay)) {
                return false;
            }
            GregorianCalendar gc = new GregorianCalendar();
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
            if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150 || (gc.getTime().getTime() - s.parse(strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
                return false;
            }
            if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
                return false;
            }
            if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
                return false;
            }
            //判断地区码
            Hashtable h = ChinaAreaIDCode.AREA_CODE_TABLE;
            if (h.get(iDCardNo.substring(0, 2)) == null) {
                return false;
            }
            //判断最后一位
            int theLastOne = 0;
            for (int i = 0; i < 17; i++) {
                theLastOne = theLastOne + Integer.parseInt(String.valueOf(iDCardNo.charAt(i))) * Integer.parseInt(CHECK_CODE[i]);
            }
            int modValue = theLastOne % 11;
            String strVerifyCode = W_F[modValue];
            iDCardNo = iDCardNo + strVerifyCode;
            if (input.length() == 18 && !iDCardNo.equals(input)) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}
