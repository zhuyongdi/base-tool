package com.zhuyongdi.basetool.widget.navigation_bar_bottom.anim;

import android.widget.ImageView;
import android.widget.TextView;

import com.zhuyongdi.basetool.widget.navigation_bar_bottom.BottomNavigationTab;

/**
 * Created by Administrator on 2019/3/16.
 */

public interface TabSelectAnimator {

    void onTabSelect(BottomNavigationTab tab, ImageView icon, TextView label);

    void onTabUnSelect(BottomNavigationTab tab, ImageView icon, TextView label);

}
