package com.zhuyongdi.basetool.widget.progress_bar;

/**
 * Created by ZhuYongdi on 2019/4/18.
 */
public interface OnCustomSeekBarChangeListener {

    void onProgressChanged(CustomSeekBar seekBar, int progress);

    void onStartTrackingTouch(CustomSeekBar seekBar);

    void onStopTrackingTouch(CustomSeekBar seekBar);

}
