package com.hldj.hmyg.hook;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import com.hldj.hmyg.wechat.WechatHook;

import static com.hldj.hmyg.wechat.WechatHook.微信包名;

/**
 * 微信认证hook
 */
public class HookAuth implements IXposedHookLoadPackage {

    public static String 当前包名 = "com.hldj.hmyg";

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {


        hookSelf(lpparam);


        hookWechat(lpparam);


    }

    private void hookWechat(XC_LoadPackage.LoadPackageParam lpparam) {
        if (lpparam.packageName.equals(微信包名))
            new WechatHook().hook(lpparam);
    }

    private void hookSelf(XC_LoadPackage.LoadPackageParam lpparam) {
        if (lpparam.packageName.equals(当前包名)) {
            XposedHelpers.findAndHookMethod("com.hldj.hmyg.MainActivity",
                    lpparam.classLoader,
                    "isBeHook",
                    new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                            param.setResult(true);
//                            param.setResult("劫持你。没道理");
                        }
                    });
        }


    }


}

