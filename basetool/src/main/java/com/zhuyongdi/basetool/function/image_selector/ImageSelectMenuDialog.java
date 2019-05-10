package com.zhuyongdi.basetool.function.image_selector;

import android.app.Dialog;
import android.content.Context;

import com.zhuyongdi.basetool.function.image_selector.adapter.SelectMenuAdapter;
import com.zhuyongdi.basetool.function.image_selector.bean.Folder;
import com.zhuyongdi.basetool.widget.MaxHeightListView;

import java.util.ArrayList;

/**
 * Created by ZhuYongdi on 2019/3/26.
 */
public class ImageSelectMenuDialog {

    private Dialog dialog;
    private MaxHeightListView lv_content;
    private SelectMenuAdapter adapter;
    private ArrayList<Folder> list;
    private OnSelectListener onSelectListener;

    private ImageSelectMenuDialog() {
    }

    public static ImageSelectMenuDialog getInstance() {
        return new ImageSelectMenuDialog();
    }

    public void init(Context context, ArrayList<Folder> list, OnSelectListener onSelectListener) {

    }

    public void show() {
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    public void dismiss() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public interface OnSelectListener {

        void onSelect(int position);

    }

}
