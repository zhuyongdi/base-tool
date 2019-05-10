package com.zhuyongdi.basetool.function.image_selector.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.zhuyongdi.basetool.R;
import com.zhuyongdi.basetool.function.image_selector.bean.MediaBean;
import com.zhuyongdi.basetool.function.image_selector.bean.MediaType;
import com.zhuyongdi.basetool.function.screen_adaption.ScreenAdapterTools;
import com.zhuyongdi.basetool.tool.ListUtil;
import com.zhuyongdi.basetool.tool.RandomUtil;
import com.zhuyongdi.basetool.tool.screen.ScreenTool;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by ZhuYongdi on 2019/3/27.
 */
public class SelectImageAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<MediaBean> list;
    private LayoutInflater inflater;
    private OnSelectListener onSelectListener;
    private OnItemClickListener onItemClickListener;

    private static final double SELECT_WIDTH = 0.2d; //选择图标在item中的宽度占比
    private static final double VIDEO_HEIGHT = 0.13d; //视频图标在item中的高度占比
    private static final double SELECT_PADDING = 0.07d; //选择图标父布局的padding在item中的宽度占比
    private static final double VIDEO_MARGIN = SELECT_PADDING; //视频图标距离item底部在item中的占比

    private int itemWidth;
    private int selectWidth;
    private int videoHeight;
    private int selectPadding;
    private int videoMargin;

    private RequestOptions glideOptions = new RequestOptions().centerCrop();

    public SelectImageAdapter(Context context, ArrayList<MediaBean> list, int gridColumnNum, int horizontalSpacing) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
        int hsTotal = (gridColumnNum - 1) * horizontalSpacing;
        this.itemWidth = (ScreenTool.getScreenWidth(context) - hsTotal) / gridColumnNum;
        this.selectWidth = (int) (itemWidth * SELECT_WIDTH);
        this.videoHeight = (int) (itemWidth * VIDEO_HEIGHT);
        this.selectPadding = (int) (itemWidth * SELECT_PADDING);
        this.videoMargin = (int) (itemWidth * VIDEO_MARGIN);
    }

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        this.onSelectListener = onSelectListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return ListUtil.size(list);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int type) {
        View itemView = inflater.inflate(R.layout.item_select_image, parent, false);
        ScreenAdapterTools.getInstance().loadView(itemView);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        MediaBean bean = list.get(position);
        resize(bean.getType(), (ViewHolder) holder);
        Glide.with(context).load(new File(bean.getPath())).apply(glideOptions).into(((ViewHolder) holder).iv_logo);
        if (bean.isSelect()) {
            ((ViewHolder) holder).iv_select.setBackgroundResource(R.mipmap.icon_select);
        } else {
            ((ViewHolder) holder).iv_select.setBackgroundResource(R.mipmap.icon_unselect);
        }
        if (bean.getType() == MediaType.VIDEO) {
            ((ViewHolder) holder).ll_videoTimeLength.setVisibility(View.VISIBLE);
            ((ViewHolder) holder).tv_videoTimeLength.setText(bean.getVideoTimeLength() + "");
        } else {
            ((ViewHolder) holder).ll_videoTimeLength.setVisibility(View.GONE);
        }
        ((ViewHolder) holder).iv_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onSelectListener != null) {
                    onSelectListener.onSelect(position);
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);
                }
            }
        });
        holder.itemView.setBackgroundColor(RandomUtil.getRandomColor());
    }

    private void resize(MediaType type, ViewHolder holder) {
        holder.itemView.getLayoutParams().width = itemWidth;
        holder.itemView.getLayoutParams().height = itemWidth;
        holder.itemView.setLayoutParams(holder.itemView.getLayoutParams());
        holder.rl_select.setPadding(selectPadding, selectPadding, selectPadding, selectPadding);
        holder.iv_select.getLayoutParams().width = selectWidth;
        holder.iv_select.getLayoutParams().height = selectWidth;
        holder.iv_select.setLayoutParams(holder.iv_select.getLayoutParams());
        if (type != MediaType.IMAGE) {
            LinearLayout.LayoutParams lp1 = (LinearLayout.LayoutParams) holder.iv_videoTimeLength.getLayoutParams();
            lp1.weight = videoHeight;
            lp1.height = videoHeight;
            lp1.rightMargin = videoMargin;
            holder.iv_videoTimeLength.setLayoutParams(lp1);
            holder.tv_videoTimeLength.setTextSize(videoHeight);
            RelativeLayout.LayoutParams lp2 = (RelativeLayout.LayoutParams) holder.ll_videoTimeLength.getLayoutParams();
            lp2.bottomMargin = videoMargin;
            holder.ll_videoTimeLength.setLayoutParams(lp2);
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_logo;
        RelativeLayout rl_select;
        ImageView iv_select;
        LinearLayout ll_videoTimeLength;
        ImageView iv_videoTimeLength;
        TextView tv_videoTimeLength;
        View v_mask;

        ViewHolder(View itemView) {
            super(itemView);
            iv_logo = itemView.findViewById(R.id.iv_image);
            rl_select = itemView.findViewById(R.id.rl_select);
            iv_select = itemView.findViewById(R.id.iv_select);
            ll_videoTimeLength = itemView.findViewById(R.id.ll_videoTimeLength);
            iv_videoTimeLength = itemView.findViewById(R.id.iv_videoTimeLength);
            tv_videoTimeLength = itemView.findViewById(R.id.tv_videoTimeLength);
            v_mask = itemView.findViewById(R.id.v_mask);
        }
    }

    public interface OnSelectListener {

        void onSelect(int position);

    }

    public interface OnItemClickListener {

        void onItemClick(int position);

    }

}
