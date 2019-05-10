package com.zhuyongdi.basetool.function.screen_adaption.loadviewhelper;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.zhuyongdi.basetool.function.screen_adaption.conversion.IConversion;
import com.zhuyongdi.basetool.function.screen_adaption.conversion.SimpleConversion;
import com.zhuyongdi.basetool.function.screen_adaption.utils.ActualScreen;
import com.zhuyongdi.basetool.function.screen_adaption.utils.dp2pxUtils;

/**
 * Created by yosemite on 2018/3/21.
 */

public abstract class AbsLoadViewHelper implements ILoadViewHelper {

    protected float actualDensity;
    protected float actualDensityDpi;
    protected float actualWidth;
    protected float actualHeight;

    protected int designWidth;
    protected int designDpi;
    protected float fontSize;
    protected String unit;

    public AbsLoadViewHelper(Context context, int designWidth, int designDpi, float fontSize, String unit) {
        this.designWidth = designWidth;
        this.designDpi = designDpi;
        this.fontSize = fontSize;
        this.unit = unit;
        setActualParams(context);
    }

    public void reset(Context context) {
        setActualParams(context);
    }

    private void setActualParams(Context context) {
        float[] actualScreenInfo = ActualScreen.screenInfo(context);
        if (actualScreenInfo.length == 4) {
            actualWidth = actualScreenInfo[0];
            actualHeight = actualScreenInfo[1];
            actualDensity = actualScreenInfo[2];
            actualDensityDpi = actualScreenInfo[3];
        }
    }

    // if subclass has owner conversion，you need override this method and provide your conversion
    public void loadView(View view) {
        loadView(view, new SimpleConversion());
    }

    public final void loadView(View view, IConversion conversion) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            conversion.transform(viewGroup, this);
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                if (viewGroup.getChildAt(i) instanceof ViewGroup) {
                    loadView(viewGroup.getChildAt(i), conversion);
                } else {
                    conversion.transform(viewGroup.getChildAt(i), this);
                }
            }
        } else {
            conversion.transform(view, this);
        }

    }

    public int setValue(int value) {
        if (value == 0) {
            return 0;
        } else if (value == 1) {
            return 1;
        }
        return (int) calculateValue(value);
    }

    float calculateValue(float value) {
        if ("px".equals(unit)) {
            return value * ((float) actualWidth / (float) designWidth);
        } else if ("dp".equals(unit)) {
            int dip = dp2pxUtils.px2dip(actualDensity, value);
            value = ((float) designDpi / 160) * dip;
            return value * ((float) actualWidth / (float) designWidth);

        }
        return 0;
    }

}
