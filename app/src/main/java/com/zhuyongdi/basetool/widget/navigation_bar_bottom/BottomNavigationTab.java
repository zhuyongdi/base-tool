package com.zhuyongdi.basetool.widget.navigation_bar_bottom;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2019/3/16.
 */
public class BottomNavigationTab extends LinearLayout {

    private ImageView mIconView;
    private TextView mLabelView;

    public BottomNavigationTab(Context context) {
        this(context, null);
    }

    public BottomNavigationTab(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomNavigationTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BottomNavigationTab(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public ImageView getIconView() {
        return mIconView;
    }

    public TextView getLabelView() {
        return mLabelView;
    }

}
