package com.zhuyongdi.basetool.function.screen_adaption.loadviewhelper;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhuyongdi.basetool.function.screen_adaption.utils.ViewUtils;


/**
 * Created by yatoooon on 2018/2/6.
 */

public class LoadViewHelper extends AbsLoadViewHelper {

    public LoadViewHelper(Context context, int designWidth, int designDpi, float fontSize, String unit) {
        super(context, designWidth, designDpi, fontSize, unit);
    }

    @Override
    public void loadWidthHeightFont(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams.width > 0) {
            layoutParams.width = setValue(layoutParams.width);
        }
        if (layoutParams.height > 0) {
            layoutParams.height = setValue(layoutParams.height);
        }
        loadViewFont(view);
    }


    private void loadViewFont(View view) {
        if ((view instanceof TextView)) {
            TextView textView = (TextView) view;
            ((TextView) view).setTextSize(0, setFontSize(textView));
        }
    }

    private float setFontSize(TextView textView) {
        return calculateValue(textView.getTextSize() * fontSize);
    }

    @Override
    public void loadPadding(View view) {
        view.setPadding(setValue(view.getPaddingLeft()), setValue(view.getPaddingTop()), setValue(view.getPaddingRight()), setValue(view.getPaddingBottom()));
    }

    @Override
    public void loadLayoutMargin(View view) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams;
        if (params instanceof ViewGroup.MarginLayoutParams) {
            marginLayoutParams = (ViewGroup.MarginLayoutParams) params;
            marginLayoutParams.leftMargin = setValue(marginLayoutParams.leftMargin);
            marginLayoutParams.topMargin = setValue(marginLayoutParams.topMargin);
            marginLayoutParams.rightMargin = setValue(marginLayoutParams.rightMargin);
            marginLayoutParams.bottomMargin = setValue(marginLayoutParams.bottomMargin);
            view.setLayoutParams(marginLayoutParams);
        }
    }

    @Override
    public void loadMaxWidthAndHeight(View view) {
        ViewUtils.setMaxWidth(view, setValue(ViewUtils.getMaxWidth(view)));
        ViewUtils.setMaxHeight(view, setValue(ViewUtils.getMaxHeight(view)));
    }

    @Override
    public void loadMinWidthAndHeight(View view) {
        ViewUtils.setMinWidth(view, setValue(ViewUtils.getMinWidth(view)));
        ViewUtils.setMinHeight(view, setValue(ViewUtils.getMinHeight(view)));
    }

    @Override
    public int loadCustomAttrValue(int px) {
        return setValue(px);
    }


}
