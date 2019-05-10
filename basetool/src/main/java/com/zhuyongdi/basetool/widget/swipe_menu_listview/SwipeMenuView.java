package com.zhuyongdi.basetool.widget.swipe_menu_listview;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import java.util.List;

/**
 * @author baoyz
 */
public class SwipeMenuView extends LinearLayout implements OnClickListener {

    private SwipeMenuLayout mLayout;
    private SwipeMenu mMenu;
    private OnSwipeItemClickListener onItemClickListener;
    private int position;
    private LayoutParams lp;

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public SwipeMenuView(SwipeMenu menu, SwipeMenuListView listView) {
        super(menu.getContext());
        this.setOrientation(LinearLayout.HORIZONTAL);
        this.lp = new LayoutParams(-2, -1);
        this.mMenu = menu;
        List<View> items = menu.getMenuViews();
        int id = 0;
        for (View item : items) {
            addItem(item, id);
            id++;
        }
    }

    private void addItem(View item, int id) {
        item.setId(id);
        if (item.getLayoutParams() == null) {
            item.setLayoutParams(this.lp);
        }
        item.setOnClickListener(this);
        addView(item);
    }

    @Override
    public void onClick(View v) {
        if (this.onItemClickListener != null && this.mLayout.isOpen()) {
            this.onItemClickListener.onItemClick(this, mMenu, v.getId());
        }
    }

    public void setOnSwipeItemClickListener(OnSwipeItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setLayout(SwipeMenuLayout mLayout) {
        this.mLayout = mLayout;
    }

    public interface OnSwipeItemClickListener {
        void onItemClick(SwipeMenuView view, SwipeMenu menu, int index);
    }
}
