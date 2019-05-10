package com.zhuyongdi.basetool.tool.input;

import android.text.Editable;
import android.widget.EditText;

/**
 * EditText工具类
 * Created by ZhuYongdi on 2019/3/14.
 */
public class EditTextTool {

    //逐一删除EditText输入内容,同软键盘删除键。当屏蔽了软键盘的删除键可调用
    public static void handleNormalText(EditText et) {
        if (et == null) {
            return;
        }
        int index = et.getSelectionStart();
        Editable editable = et.getText();
        if (index <= 0)
            return;
        editable.delete(index - 1, index);
    }

}
