package com.zhuyongdi.basetool.function.image_selector.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.zhuyongdi.basetool.R;
import com.zhuyongdi.basetool.function.image_selector.bean.Folder;
import com.zhuyongdi.basetool.function.image_selector.bean.MediaType;

import java.util.ArrayList;

/**
 * Created by ZhuYongdi on 2019/3/26.
 */
public class SelectMenuAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Folder> data;
    private LayoutInflater inflater;
    private MediaType selectType;
    private RequestOptions glideRequestOptions = new RequestOptions()
            .error(R.mipmap.image)
            .placeholder(R.mipmap.image);

    public SelectMenuAdapter(Context context, ArrayList<Folder> data, MediaType selectType) {
        this.context = context;
        this.data = data;
        this.selectType = selectType;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_select_menu, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Folder folder = data.get(position);
        Glide.with(context)
                .load(folder.getFirstImagePath())
                .apply(glideRequestOptions)
                .into(holder.iv_image);
        holder.tv_name.setText(folder.getName());
        String end;
        if (selectType == MediaType.VIDEO) {
            end = "个视频";
        } else {
            end = "个图片";
        }
        holder.tv_number.setText(folder.getCount() + end);
        if (folder.isSelect()) {
            holder.iv_select.setImageResource(R.mipmap.icon_select);
        } else {
            holder.iv_select.setImageResource(R.mipmap.icon_unselect);
        }
        return convertView;
    }

    private class ViewHolder {
        ImageView iv_image;
        TextView tv_name;
        TextView tv_number;
        ImageView iv_select;
        View v_divider;

        ViewHolder(View itemView) {
            iv_image = itemView.findViewById(R.id.iv_image);
            tv_name = itemView.findViewById(R.id.tv_menuName);
            tv_number = itemView.findViewById(R.id.tv_count);
            iv_select = itemView.findViewById(R.id.iv_select);
            v_divider = itemView.findViewById(R.id.v_divider);
        }
    }

}
