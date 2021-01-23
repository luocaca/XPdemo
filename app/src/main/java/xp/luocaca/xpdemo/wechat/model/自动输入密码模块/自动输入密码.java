package xp.luocaca.xpdemo.wechat.model.自动输入密码模块;

import android.content.Context;
import android.os.Handler;
import android.widget.EditText;
import android.widget.Toast;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import xp.luocaca.xpdemo.wechat.share.WechatShareObject;

public class 自动输入密码 {

    public void 自动输入密码(XC_LoadPackage.LoadPackageParam lpparam) {

        XposedBridge.log("-------微信密码-----被hook到了------");

        // private void aI(Context context) {
        //package com.tencent.mm.wallet_core.ui.formview;
        //EditHintPasswdView
        XposedHelpers.findAndHookMethod(
                "com.tencent.mm.wallet_core.ui.formview.EditHintPasswdView",
                lpparam.classLoader,
                "aL",
                Context.class,
                new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);
                        Toast.makeText(((Context) param.args[0]), "密码被hook到", Toast.LENGTH_SHORT).show();
                        EditText editText = (EditText) XposedHelpers.findField(param.thisObject.getClass(), "mEditText").get(param.thisObject);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                XposedBridge.log("-------2秒后主动输入2个文字---------");
                                editText.setText(WechatShareObject.password);
                            }
                        }, 0);

                        try {
                            throw new NullPointerException("--------自定义异常抛出，查看堆栈-------");
                        } catch (Exception e) {
                            XposedBridge.log(e);
                        }
                    }
                });

    }

}
