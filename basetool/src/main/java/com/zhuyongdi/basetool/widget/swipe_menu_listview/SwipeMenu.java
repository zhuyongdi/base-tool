package com.zhuyongdi.basetool.widget.swipe_menu_listview;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baoyz
 * @date 2014-8-23
 */
public class SwipeMenu {

    private Context mContext;
    private List<View> mItems;
    private int mViewType;

    public SwipeMenu(Context context) {
        mContext = context;
        mItems = new ArrayList<>();
    }

    public Context getContext() {
        return mContext;
    }

    public void addMenuView(View itemView) {
        mItems.add(itemView);
    }

    public void removeMenuItem(View itemView) {
        mItems.remove(itemView);
    }

    public List<View> getMenuViews() {
        return mItems;
    }

    public View getMenuItem(int index) {
        return mItems.get(index);
    }

    public int getViewType() {
        return mViewType;
    }

    public void setViewType(int viewType) {
        this.mViewType = viewType;
    }

}
