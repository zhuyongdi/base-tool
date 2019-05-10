package com.zhuyongdi.basetool.function.image_selector.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhuyongdi.basetool.R;
import com.zhuyongdi.basetool.function.image_selector.adapter.SelectImageAdapter;
import com.zhuyongdi.basetool.function.image_selector.bean.MediaBean;
import com.zhuyongdi.basetool.function.image_selector.bean.MediaType;
import com.zhuyongdi.basetool.function.image_selector.config.Config;
import com.zhuyongdi.basetool.function.image_selector.handler.LocalDataHandler;
import com.zhuyongdi.basetool.function.screen_adaption.ScreenAdapterTools;
import com.zhuyongdi.basetool.support.SpaceItemDecoration;
import com.zhuyongdi.basetool.tool.ApplicationUtil;

import java.util.ArrayList;

/**
 * 布局文件设计按1080*1920,Manifest中designWidth=1080,fontSize=1
 * 权限获取由AndPermission
 * Created by ZhuYongdi on 2019/3/26.
 */
public class ImageSelectActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = ImageSelectActivity.class.getSimpleName();

    public static final String COLUMN_NUM = "column_num";
    public static final String MAX_NUM = "max_num";
    public static final String SELECT_TYPE = "select_type";
    public static final String BOTH = "both";
    public static final String IS_SINGLE = "is_single";
    public static final String BOTTOM_PREVIEW_ENABLE = "bottom_preview_enable";

    private RecyclerView rlv_content;
    private RelativeLayout rl_menu;
    private TextView tv_menu;
    private RelativeLayout rl_preView;
    private TextView tv_preView;

    private int columnNum = Config.DEFAULT_GRID_COLUMN_NUM;
    private int maxNum = Config.DEFAULT_MAX_SELECT;
    private MediaType selectType = Config.DEFAULT_SELECT_TYPE;
    private boolean selectBoth = Config.DEFAULT_SELECT_BOTH;
    private boolean isSingle = Config.DEFAULT_IS_SINGLE;
    private boolean bottomPreViewEnable = Config.BOTTOM_PREVIEW_ENABLE;

    private SelectImageAdapter adapter;
    private LocalDataHandler localDataHandler;
    private ArrayList<MediaBean> localDataList = new ArrayList<>();

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (localDataList.isEmpty()) {
                showToast(selectType == MediaType.IMAGE ? "无本地图片" : "无本地视频");
            } else {
                Log.e(TAG, "有数据");
                adapter.notifyItemRangeInserted(0, localDataList.size());
            }
            return false;
        }
    });

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_select);
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());
        localDataHandler = LocalDataHandler.getInstance(this);
        setTxtStatusBar();
        initIntent();
        initUI();
    }

    private void initIntent() {
        Intent intent = getIntent();
        columnNum = intent.getIntExtra(COLUMN_NUM, Config.DEFAULT_GRID_COLUMN_NUM);
        maxNum = intent.getIntExtra(MAX_NUM, Config.DEFAULT_MAX_SELECT);
        selectType = (MediaType) intent.getSerializableExtra(SELECT_TYPE);
        selectBoth = intent.getBooleanExtra(BOTH, Config.DEFAULT_SELECT_BOTH);
        isSingle = intent.getBooleanExtra(IS_SINGLE, Config.DEFAULT_IS_SINGLE);
        bottomPreViewEnable = intent.getBooleanExtra(BOTTOM_PREVIEW_ENABLE, Config.BOTTOM_PREVIEW_ENABLE);
        if (columnNum < 0) {
            columnNum = Config.DEFAULT_GRID_COLUMN_NUM;
        }
        if (maxNum < 0) {
            maxNum = Config.DEFAULT_MAX_SELECT;
        }
        if (selectType == null) {
            selectType = Config.DEFAULT_SELECT_TYPE;
        }
    }

    private void initUI() {
        rlv_content = findViewById(R.id.rlv_content);
        rl_menu = findViewById(R.id.rl_menu);
        tv_menu = findViewById(R.id.tv_menu);
        rl_preView = findViewById(R.id.rl_preView);
        tv_preView = findViewById(R.id.tv_preView);
        initConfigOptions();
        initImageSelectAdapter();
        rl_menu.setOnClickListener(this);
        checkPermission(new OnPermissionGrantedCallback() {
            @Override
            public void onPermissionGranted() {
                getLoadMediaBeans();
            }
        });
    }

    private void initConfigOptions() {
        if (bottomPreViewEnable) {
            rl_preView.setVisibility(View.GONE);
        }
    }

    private void initImageSelectAdapter() {
        int spacing = ScreenAdapterTools.getInstance().setValue(Config.DEFAULT_HORIZONTAL_SPACING);
        rlv_content.setLayoutManager(new GridLayoutManager(this, columnNum));
        ((SimpleItemAnimator) rlv_content.getItemAnimator()).setSupportsChangeAnimations(false);
        rlv_content.addItemDecoration(new SpaceItemDecoration(spacing, false, SpaceItemDecoration.GRIDLAYOUT));
        adapter = new SelectImageAdapter(this, localDataList, columnNum, spacing);
        adapter.setOnSelectListener(new SelectImageAdapter.OnSelectListener() {
            @Override
            public void onSelect(int position) {
                if (localDataList.get(position).isSelect()) {
                    localDataList.get(position).setSelect(false);
                    adapter.notifyItemChanged(position);
                } else {
                    if (selectBoth && localDataHandler.checkIsNotSameSelect(localDataList, position)) {
                        showToast("不可同时选择图片和视频");
                    } else {
                        localDataList.get(position).setSelect(true);
                        adapter.notifyItemChanged(position);
                    }
                }
            }
        });
        adapter.setOnItemClickListener(new SelectImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
        rlv_content.setAdapter(adapter);
    }

    private void getLoadMediaBeans() {
        if (!ApplicationUtil.hasExternalStorage()) {
            showToast("没有外部存储");
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                localDataList.clear();
                if (selectType == MediaType.IMAGE) {
                    localDataList.addAll(localDataHandler.getImages());
                } else if (selectType == MediaType.VIDEO) {
                    localDataList.addAll(localDataHandler.getVideos());
                } else {
                    localDataList.addAll(localDataHandler.getImagesAndVideos());
                }
                handler.sendEmptyMessage(1);
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();//分类
        if (i == R.id.rl_menu) {//预览
        } else if (i == R.id.rl_preView) {
        }
    }
}
