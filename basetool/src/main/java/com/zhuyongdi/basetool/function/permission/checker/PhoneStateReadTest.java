package com.zhuyongdi.basetool.function.permission.checker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

/**
 * Created by ZhuYongdi on 2019/5/10.
 */
class PhoneStateReadTest implements PermissionTest {

    private Context mContext;

    PhoneStateReadTest(Context context) {
        this.mContext = context;
    }

    @SuppressLint({"HardwareIds", "MissingPermission"})
    @Override
    public boolean test() throws Throwable {
        TelephonyManager service = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        return !TextUtils.isEmpty(service.getDeviceId()) || !TextUtils.isEmpty(service.getSubscriberId());
    }

}
