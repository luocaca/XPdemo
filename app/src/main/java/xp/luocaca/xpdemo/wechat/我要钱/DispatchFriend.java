package xp.luocaca.xpdemo.wechat.我要钱;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import xp.luocaca.xpdemo.wechat.WechatHook;

import static xp.luocaca.xpdemo.wechat.WechatHook.analogUserClick;
import static xp.luocaca.xpdemo.wechat.WechatHook.getAnyViews;
import static xp.luocaca.xpdemo.wechat.WechatHook.getMatchingView;

/**
 * 转账给好友
 */
public class DispatchFriend {


    public static void hook(final XC_LoadPackage.LoadPackageParam loadPackageParam) {


        转账给好友功能hook(loadPackageParam);

        自动输入金额输入密码等别人调用把(loadPackageParam, 0.1, true);


    }

    private static final String TAG = "DispatchFriend";

    private static void 转账给好友功能hook(XC_LoadPackage.LoadPackageParam loadPackageParam) {

        XposedBridge.log("------转账给好友功能hook--------");

        XposedHelpers.findAndHookMethod(
                "com.tencent.mm.plugin.remittance.ui.RemittanceUI",
                loadPackageParam.classLoader,
                "onCreate",
                Bundle.class, // nullColumnHack
                new XC_MethodHook() {

                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        super.beforeHookedMethod(param);
                        Activity activity = ((Activity) param.thisObject);
                        String price = activity.getIntent().getStringExtra("price");
                        String password = activity.getIntent().getStringExtra("password");


                        XposedBridge.log(TAG + "--------price--------" + price);
                        XposedBridge.log(TAG + "--------price--------" + password);
                        XposedBridge.log(TAG + "--------receiver_name--------");


                    }


                });


    }


    public static void 自动输入金额输入密码等别人调用把(final XC_LoadPackage.LoadPackageParam loadPackageParam, double price, final boolean isActive) {


        XposedHelpers.findAndHookMethod(
                "com.tencent.mm.plugin.remittance.ui.RemittanceUI",
//                "com.tencent.mm.plugin.remittance.mobile.ui.MobileRemittanceUI",
                loadPackageParam.classLoader,
                "onCreate",
//                "onResume",
                Bundle.class, // table
                new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(final MethodHookParam param) throws Throwable {


                        Activity mactivity = ((Activity) param.thisObject);

//                    List<EditText> editTexts = DispatchWechat.getMatchingViewList(mactivity, new IInstance666() {
//                        @Override
//                        public boolean invokeInstance(View view) {
//                            return view instanceof EditText;
//                        }
//                    });
                        Thread.sleep(500);


                        List<EditText> editTexts = new ArrayList<>();

                        getAnyViews(((FrameLayout) mactivity.getWindow().getDecorView()), editTexts, new WechatHook.IInstance666() {
                            @Override
                            public boolean invokeInstance(View view) {
                                return view instanceof EditText;
                            }
                        });


                        Toast.makeText(mactivity, "看看没有找到edit" + editTexts, Toast.LENGTH_SHORT).show();

                        for (EditText text : editTexts) {

                            text.performClick();
                            text.requestFocus();


                            text.setText("0.1");

                        }

                        Thread.sleep(500);

//                        XposedHelpers.callMethod(mactivity, "lV", new Class[]{boolean.class}, true);


//                        DispatchWechat.getCurrentActivity(param).findViewById(viewId).performClick();


                        final TextView zz = getMatchingView(mactivity, "转账", new WechatHook.IInstance666() {
                            @Override
                            public boolean invokeInstance(View view) {
                                return view instanceof TextView;
                            }
                        });


                        analogUserClick(zz, zz.getX(), zz.getY());

                        我要钱功能.我知道了监听回调 = new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try {
                                    Thread.sleep(500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                analogUserClick(zz, zz.getX(), zz.getY());

                            }
                        };

                        //
//                    自动输入金额输入密码等别人调用把(loadPackageParam, 0.1, false);


                    }
                });


    }


}
