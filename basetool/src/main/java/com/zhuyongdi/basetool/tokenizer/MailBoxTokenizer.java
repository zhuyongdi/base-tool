package com.zhuyongdi.basetool.tokenizer;

import android.widget.MultiAutoCompleteTextView;

/**
 * Created by ZhuYongdi on 2019/4/8.
 */
public class MailBoxTokenizer implements MultiAutoCompleteTextView.Tokenizer {

    public int findTokenStart(CharSequence text, int cursor) {
        int i = cursor;

        while (i > 0 && text.charAt(i - 1) != '@') {
            i--;
        }
        return i;
    }

    public int findTokenEnd(CharSequence text, int cursor) {
        int i = cursor;
        int len = text.length();

        while (i < len) {
            if (text.charAt(i) == '@') {
                return i;
            } else {
                i++;
            }
        }

        return len;
    }

    // 由于邮箱注册信息一般为纯文本内容，这里就不再进行富文本处理，直接返回
    public CharSequence terminateToken(CharSequence text) {
        return text;
    }
}
