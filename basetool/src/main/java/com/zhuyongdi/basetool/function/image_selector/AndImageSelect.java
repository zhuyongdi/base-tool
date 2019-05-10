package com.zhuyongdi.basetool.function.image_selector;

import android.app.Activity;
import android.content.Context;

import com.zhuyongdi.basetool.function.image_selector.source.ActivitySource;
import com.zhuyongdi.basetool.function.image_selector.source.ContextSource;
import com.zhuyongdi.basetool.function.image_selector.source.FragmentSource;
import com.zhuyongdi.basetool.function.image_selector.source.Source;
import com.zhuyongdi.basetool.function.image_selector.source.SupportFragmentSource;

/**
 * Created by ZhuYongdi on 2019/3/26.
 */
public class AndImageSelect {

    private static RequestFactory FACTORY = new RequestFactory() {
        @Override
        public Request create(Source source) {
            return new MRequest(source);
        }
    };

    private AndImageSelect() {
    }

    public static Request with(Context context) {
        return FACTORY.create(new ContextSource(context));
    }

    public static Request with(Activity activity) {
        return FACTORY.create(new ActivitySource(activity));
    }

    public static Request with(android.app.Fragment fragment) {
        return FACTORY.create(new FragmentSource(fragment));
    }

    public static Request with(android.support.v4.app.Fragment fragment) {
        return FACTORY.create(new SupportFragmentSource(fragment));
    }

    private interface RequestFactory {
        Request create(Source source);
    }

}
