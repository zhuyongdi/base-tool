package com.zhuyongdi.basetool.function.permission.checker;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.provider.Telephony;
import android.support.annotation.RequiresApi;

/**
 * Created by ZhuYongdi on 2019/5/10.
 */
class SmsReadTest implements PermissionTest {


    private ContentResolver mResolver;

    SmsReadTest(Context context) {
        mResolver = context.getContentResolver();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean test() throws Throwable {
        String[] projection = new String[]{Telephony.Sms._ID, Telephony.Sms.ADDRESS, Telephony.Sms.PERSON, Telephony.Sms.BODY};
        Cursor cursor = mResolver.query(Telephony.Sms.CONTENT_URI, projection, null, null, null);
        if (cursor != null) {
            try {
                CursorTest.read(cursor);
            } finally {
                cursor.close();
            }
            return true;
        } else {
            return false;
        }
    }

}
