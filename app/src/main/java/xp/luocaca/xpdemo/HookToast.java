package xp.luocaca.xpdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by Administrator on 2019/7/27 0027.
 */

public class HookToast implements IXposedHookLoadPackage {


    public static ClassLoader 银行classerloader;

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {


        XposedBridge.log("------银行hook---------" + loadPackageParam.packageName);


        if (loadPackageParam.packageName.equals("xp.luocaca.xpdemo")) {
            XposedHelpers.findAndHookMethod(
                    "xp.luocaca.xpdemo.MainActivity",
                    loadPackageParam.classLoader,
                    "isBeHook",
                    new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                            param.setResult(true);
                            Field field = param.thisObject.getClass().getDeclaredField("paylog");
                            TextView textView = (TextView) field.get(param.thisObject);
                            textView.setText("3杀777999");
                        }
                    });
        }


        if (!loadPackageParam.packageName.equals("com.buybal.buybalpay.nxy.fkepay")) {
            return;
        }

//        final Class clazz = loadPackageParam.classLoader.loadClass("com.fivetime.vpn.utils.DesBase64");


        // @Override
        //    protected void attachBaseContext(Context base) {
        //        super.attachBaseContext(base);
        //    }


        XposedHelpers.findAndHookMethod(
                "s.h.e.l.l.S",
                loadPackageParam.classLoader,
                "attachBaseContext",
                Context.class,
                new XC_MethodHook() {
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("改1beforeHookedMethod别看了，老子已经成功Hook热重启\n" + param.getResult());
                        super.beforeHookedMethod(param);
                        银行classerloader = param.thisObject.getClass().getClassLoader();

                    }

                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                        Context context = ((Context) param.thisObject);
                        Toast.makeText(context, "xposed hook 到银行项目了", Toast.LENGTH_SHORT).show();
                        XposedBridge.log("xposed hook 到银行项目了");


                        //用这个classloader 加载壳项目

//                        XposedHelpers.findAndHookMethod(
//                                "com.buybal.buybalpay.activity.WelcomeActivity",
//                                银行classerloader,
//                                "onCreate",
//                                Bundle.class,
//                                new XC_MethodHook() {
//                                    @Override
//                                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                                        super.afterHookedMethod(param);
//                                        ToastUtil.show(((Activity) param.thisObject), "首页被hook");
//
//
//                                        银行classerloader = param.thisObject.getClass().getClassLoader();
//
//                                        XposedBridge.log("---银行---WelcomeActivity-");
//
//                                    }
//                                });


                        XposedBridge.log("银行classerloader->" + 银行classerloader);


                        XposedHelpers.findAndHookMethod(
                                "com.buybal.buybalpay.activity.ReciveAmtActivity",
                                银行classerloader,
                                "initData",
//                                Bundle.class,
                                new XC_MethodHook() {
                                    @Override
                                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                        super.afterHookedMethod(param);
                                        ToastUtil.show(((Activity) param.thisObject), "收款界面hook");
                                        XposedBridge.log("---银行--收款界面hook-");


                                        //   private OkhttpNetHandler w = new OkhttpNetHandler() {


                                        Class holder = XposedHelpers.findClass("com.buybal.buybalpay.net.okhttputil.OkhttpNetHandler", 银行classerloader);


                                        Object o = holder.newInstance();


//                                        final Field[] mCallbackSuper = holder.getFields();

//                                        Field call = null;
//                                        for (Field field : mCallbackSuper) {
//                                            if (field.getName().equals("mCallback")) {
//                                                call = field;
//                                            }
//                                        }

                                        try {
                                            XposedHelpers.setObjectField(o, "mCallback", new Handler.Callback() {
                                                @Override
                                                public boolean handleMessage(Message msg) {
                                                    XposedBridge.log("----handlermessage-----" + msg.toString());
                                                    return true;
                                                }
                                            });
                                        } catch (Exception e) {
                                            XposedBridge.log("-----失败" + e.getMessage());
                                        }


//                                        call.setAccessible(true);
//                                        call.set(o, new Handler.Callback() {
//                                            @Override
//                                            public boolean handleMessage(Message msg) {
//                                                XposedBridge.log("----handlermessage-----" + msg.toString());
//                                                return false;
//                                            }
//                                        });
//                                         new Handler(Looper.myLooper(), new Handler.Callback() {
//                                             @Override
//                                             public boolean handleMessage(Message msg) {
//
//                                                 XposedBridge.log("------");
//                                                 return true;
//                                             }
//                                         });


                                        Field field = XposedHelpers.findField(param.thisObject.getClass(), "w");
                                        field.set(param.thisObject, o);


                                        //getQrlink(this.n, AssistPushConsts.PUSHMESSAGE_ACTION_MULTI_BRAND_RECEIVE_XM);
//                                        Method getqurlink = param.thisObject.getClass().getMethod("getQrlink", String.class, String.class);


//                                        getqurlink.invoke(param.thisObject,"1","3");
//                                        getqurlink.invoke(param.thisObject,"2","3");


                                        for (int i = 10; i < 20; i++) {
                                            Thread.sleep(2000);
                                            XposedHelpers.callMethod(param.thisObject, "getQrlink", i + "", "3");
                                        }

                                    }
                                });


//                        XposedHelpers.findAndHookMethod(
//                                "com.buybal.buybalpay.activity.ReciveAmtActivity$w",
//                                银行classerloader,
//                                "onHttpSuccess",
////                                Bundle.class,
//                                new XC_MethodReplacement() {
//                                    @Override
//                                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
//
//                                        XposedBridge.log("-----测试回调---------" + param.args[0]);
//
//
//                                        return null;
//                                    }
//                                });


//        hookMainActivity(loadPackageParam);


                    }

                    /**
                     * hook main
                     *
                     * @param loadPackageParam
                     */
                    private void hookMainActivity(XC_LoadPackage.LoadPackageParam loadPackageParam) {

                        //com.fivetime.vpn.activity
                        //MainActivity
//        private void prepareStartService(VpnProfile vpnProfile) {


                        Class clazz = null;
                        Class VpnProfile = null;
                        try {
                            clazz = loadPackageParam.classLoader.loadClass("com.fivetime.vpn.activity.MainActivity");
                            VpnProfile = loadPackageParam.classLoader.loadClass("com.fivetime.vpnservice.aidl.VpnProfile");


                            XposedBridge.log("VpnProfile 类是不是para被找到国m" + (VpnProfile == null));


                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }

                        XposedHelpers.findAndHookMethod(clazz, "prepareStartService", VpnProfile, new XC_MethodHook() {


                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                XposedBridge.log("VpnProfile" + param.args[0].toString());
                                XposedBridge.log("VpnProfile" + param.args[0].toString());
                                XposedBridge.log("VpnProfile" + param.args[0].toString());

                                Object obj = param.args[0];


                                List<String> exclFild = new ArrayList<>();


                            }
                        });


                    }
                });


    }
}
