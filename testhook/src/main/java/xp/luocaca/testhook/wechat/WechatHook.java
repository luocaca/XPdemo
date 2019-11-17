package com.hldj.hmyg.wechat;


import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import com.hldj.hmyg.uitl.ToastUtil;
import com.hldj.hmyg.wechat.iwechat.IWechat;
import com.hldj.hmyg.wechat.share.WechatShareObject;

/**
 * hook 微信密码矿
 */
public class WechatHook implements IXposedHookLoadPackage, IWechat {


    public static final String 微信包名 = "com.tencent.mm";
    public static final String 发红包界面 = "com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyPrepareUI";
    public static final String 微信授权第三方登陆界面 = "com.tencent.mm.plugin.webview.ui.tools.SDKOAuthUI";

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) {
        微信首页(lpparam);

        微信方法日志i(lpparam);


        微信授权登陆界面(lpparam);
    }

    @Override
    public void 微信授权登陆界面(XC_LoadPackage.LoadPackageParam lpparam) {
        //微信授权第三方登陆界面

        XposedBridge.log("-------------微信授权登陆界面--------------");

        XposedHelpers.findAndHookMethod(
                微信授权第三方登陆界面,
                lpparam.classLoader,
                "onCreate",
                Bundle.class,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

                        super.beforeHookedMethod(param);
                        XposedBridge.log("  getextras--微信授权登陆界面---getextras-----  ");

                        Activity thisObject = ((Activity) param.thisObject);

                        StringBuffer stringBuffer = new StringBuffer();

                        Bundle bundle = thisObject.getIntent().getExtras();
                        for (String s : bundle.keySet()) {
                            XposedBridge.log("微信授权登陆界面-----  " + s + " ---> " + bundle.get(s));
                            stringBuffer.append(s + " ---> " + bundle.get(s));
                        }

                        ToastUtil.show(thisObject, "收到数据\n" + stringBuffer.toString());


                        XposedBridge.log("  getextras--微信授权登陆界面---getextras-----  " +
                                thisObject.getIntent().getExtras().toString());
                        XposedBridge.log("  getextras--微信授权登陆界面---getextras-----  " +
                                thisObject.getIntent().toString());


                        for (String key : bundle.keySet()) {
                            XposedBridge.log("getextras" + key);
                            XposedBridge.log("getextrasBundle Contengetextrast " +
                                    bundle.getString(key));
                        }


                    }
                });


    }


    @Override
    public void hook(XC_LoadPackage.LoadPackageParam lpparam) {
        handleLoadPackage(lpparam);
    }


    @Override
    public void 微信方法日志i(XC_LoadPackage.LoadPackageParam lpparam) {
//        public static void i(String str, String str2) {
        XposedHelpers.findAndHookMethod(
                "com.tencent.mm.sdk.platformtools.ab",
                lpparam.classLoader,
                "i",
                String.class,
                String.class,
                new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);
                        XposedBridge.log("-----微信日志 " + param.args[0] + " " + param.args[1]);
                    }
                });
    }


    public static long lastSendTime = 0;


    @Override
    public void 微信首页(XC_LoadPackage.LoadPackageParam lpparam) {
        XposedHelpers.findAndHookMethod(
                "com.tencent.mm.ui.LauncherUI",
                lpparam.classLoader,
                "onCreate",
                Bundle.class,
                new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);
                        Activity thisObject = ((Activity) param.thisObject);


                        WechatShareObject.wechatLaunchActivity = thisObject;
                        WechatShareObject.wechatApplication = thisObject.getApplication();


                    }
                });
    }


    /**
     * 获取当前界面所有的 想要的view
     */

    public static void getAnyViews(ViewGroup viewGroup, List listViews, IInstance666 iInstance) {

        for (int i = 0; i < viewGroup.getChildCount(); i++) {

            if (viewGroup.getChildAt(i) instanceof ViewGroup) {

                getAnyViews((ViewGroup) viewGroup.getChildAt(i), listViews, iInstance);

            } else {

                View view = viewGroup.getChildAt(i);

                if (iInstance.invokeInstance(view)) {

                    listViews.add(view);

                }

            }

        }


    }


    /**
     * 主打根据文本进行  获取匹配到的控件   应为是文本匹配 所以选择继承 TextView
     *
     * @param <T>
     * @param mActivity
     * @param containText
     * @param iInstance   实例接口
     * @return
     */
    public static <T extends TextView> T getMatchingView(Activity mActivity, String containText, IInstance666 iInstance) {
        List<T> matchingViewList = new ArrayList<>();
        getAnyViews(((FrameLayout) mActivity.getWindow().getDecorView()), matchingViewList, iInstance);
        for (int i = 0; i < ((FrameLayout) mActivity.getWindow().getDecorView()).getChildCount(); i++) {
            for (T editText : matchingViewList) {
                if (iInstance.invokeInstance(editText)) {

                    CharSequence hint = editText.getHint();

                    if (hint != null) {
                        if (containText.equals(editText.getText().toString().trim()) || containText.equals(editText.getHint().toString().trim())) {
                            return editText;
                        }
                    } else {
                        if (containText.equals(editText.getText().toString().trim())) {
                            return editText;
                        }
                    }
                }
            }
        }
        return null;
    }


    public static <T extends TextView> T getMatchingView(Dialog dialog, String containText, IInstance666 iInstance) {
        List<T> matchingViewList = new ArrayList<>();
        getAnyViews(((FrameLayout) dialog.getWindow().getDecorView()), matchingViewList, iInstance);
        for (int i = 0; i < ((FrameLayout) dialog.getWindow().getDecorView()).getChildCount(); i++) {
            for (T editText : matchingViewList) {
                if (iInstance.invokeInstance(editText)) {

                    CharSequence hint = editText.getHint();

                    if (hint != null) {
                        if (containText.equals(editText.getText().toString().trim()) || containText.equals(editText.getHint().toString().trim())) {
                            return editText;
                        }
                    } else {
                        if (containText.equals(editText.getText().toString().trim())) {
                            return editText;
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * 类型判断回调接口， instanceof 不懂怎么写态传类型 。所以用回调。
     * <p>
     * 要是有大佬能指点下。请回复哦——哈哈
     */

    public interface IInstance666 {

        boolean invokeInstance(View view);

    }
}
