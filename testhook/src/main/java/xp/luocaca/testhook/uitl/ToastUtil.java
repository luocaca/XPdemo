package com.hldj.hmyg.uitl;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

public class ToastUtil {
    /* access modifiers changed from: private */
    public static Toast toast = null;

    public static void show(final Context aContext, final String aMessage) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            public void run() {
                if (ToastUtil.toast == null) {
                    ToastUtil.toast = Toast.makeText(aContext, aMessage, 0);
                }
                ToastUtil.toast.setText(aMessage);
                ToastUtil.toast.setGravity(17, 0, 0);
                ToastUtil.toast.show();
            }
        });
    }

    public static void show(Context aContext, int resourceID) {
        show(aContext, aContext.getResources().getString(resourceID));
    }
}
