package com.zhuyongdi.basetool.widget.navigation_bar_bottom;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.LinearLayout;

import com.zhuyongdi.basetool.widget.navigation_bar_bottom.anim.TabSelectAnimator;
import com.zhuyongdi.basetool.widget.navigation_bar_bottom.item.TabBackgroundStyle;
import com.zhuyongdi.basetool.widget.navigation_bar_bottom.item.TabItem;

import java.util.ArrayList;

/**
 * Created by Administrator on 2019/3/16.
 */
public class BottomNavigationBar extends LinearLayout {

    private int mHeight; //总高度,单位dp
    private TabBackgroundStyle mBackgroundStyle;
    private TabSelectListener mListener;
    private TabSelectAnimator mAnimator;

    private ArrayList<TabItem> mItemList = new ArrayList<>();

    public BottomNavigationBar(Context context) {
        this(context, null);
    }

    public BottomNavigationBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomNavigationBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BottomNavigationBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void initParams() {
        mHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 56, getResources().getDisplayMetrics());
    }

    public void setOnTabSelectListener(TabSelectListener listener) {
        this.mListener = listener;
    }

    public void setOnTabSelectAnimator(TabSelectAnimator animator) {
        this.mAnimator = animator;
    }

    public void setTabBackgroundStyle(TabBackgroundStyle style) {
        this.mBackgroundStyle = style;
    }

    public void addTabItem(TabItem item) {
        this.mItemList.add(item);
    }

    private void init() {
    }

    public void initialise() {
        if (mItemList.isEmpty()) {
            return;
        }

    }

}
