package com.zhuyongdi.basetool.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.MultiAutoCompleteTextView;

/**
 * Created by ZhuYongdi on 2019/4/8.
 */
@SuppressLint("AppCompatCustomView")
public class MailBoxAutoCompleteTextView extends MultiAutoCompleteTextView {

    public MailBoxAutoCompleteTextView(Context context) {
        super(context);
    }

    public MailBoxAutoCompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MailBoxAutoCompleteTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // 当输入@符号时，就会去调用Tokenizer.findTokenStart()方法一次
    // 当点击下拉提示框中的某个信息时，会再次调用Tokenizer.findTokenStart()方法一次，然后再调用terminateToken()方法一次
    @Override
    public boolean enoughToFilter() {
        String text = getText().toString();
        return text.contains("@") && text.indexOf("@") > 0;
    }

}
