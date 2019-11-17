package xp.luocaca.xpdemo.wechat.我要钱;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import xp.luocaca.xpdemo.wechat.WechatHook;
import xp.luocaca.xpdemo.wechat.share.WechatShareObject;

import static xp.luocaca.xpdemo.wechat.WechatHook.getAnyViews;
import static xp.luocaca.xpdemo.wechat.WechatHook.getMatchingView;

public class 我要钱功能 {

    public static String 给好友转账界面 = "com.tencent.mm.plugin.remittance.ui.RemittanceUI";

    public static void hook(final XC_LoadPackage.LoadPackageParam loadPackageParam) {


        自动补全手机号转账人名补全姓名(loadPackageParam);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                DispatchFriend.hook(loadPackageParam);
            }
        }, 3000);

    }


    public static void 跳转转账界面比U币把版本(XC_LoadPackage.LoadPackageParam loadPackageParam, String talker) {
        //给垃圾离任转账
//        ShellUtils.CommandResult re =
//                ShellUtils.execCmd("am start -n com.tencent.mm/.plugin.remittance.ui.RemittanceUI    --ei scene 2 --es name wxid_mdcenjqj477f22 --ei pay_scene 31 --es receiver_name wxid_mdcenjqj477f22 --ei pay_channel 11", true);
        //给包子转账
//        ShellUtils.CommandResult
//                re = ShellUtils.execCmd("am start -n com.tencent.mm/.plugin.remittance.ui.RemittanceUI    --ei scene 2 --es name wxid_mdcenjqj477f22 --ei pay_scene 31 --es receiver_name wxid_4uizojr23p1g22 --ei pay_channel 11", true);

//        if (re.result < 0) {
        Intent intent = new Intent(WechatShareObject.wechatLaunchActivity, XposedHelpers.findClass(给好友转账界面, loadPackageParam.classLoader));
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("scene", 2);
        intent.putExtra("name", talker);
        intent.putExtra("pay_scene", 31);
        intent.putExtra("receiver_name", talker);
        intent.putExtra("pay_channel", 11);
        intent.putExtra("price", "0.1");
        intent.putExtra("password", "733251");
        WechatShareObject.wechatLaunchActivity.startActivity(intent);
//        }
    }


    public static View.OnClickListener 我知道了监听回调;

    public static void 自动补全手机号转账人名补全姓名(final XC_LoadPackage.LoadPackageParam loadPackageParam) {
//
        XposedHelpers.findAndHookMethod(
                "com.tencent.mm.ui.widget.b.d",
                loadPackageParam.classLoader,
                "show",
//                String.class,
//                Bundle.class,
//                boolean.class,
                new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(final MethodHookParam param) throws Throwable {

                        Dialog dialog = (Dialog) param.thisObject;

                        List<EditText> editTextList = new ArrayList<>();

                        getAnyViews(((FrameLayout) dialog.getWindow().getDecorView()), editTextList, new WechatHook.IInstance666() {
                            @Override
                            public boolean invokeInstance(View view) {
                                return view instanceof EditText;
                            }
                        });


                        for (EditText editText : editTextList) {
                            editText.setText("啦");
                        }


                        TextView textView = getMatchingView(dialog, "验证", new WechatHook.IInstance666() {
                            @Override
                            public boolean invokeInstance(View view) {
                                return view instanceof TextView;
                            }
                        });
                        if (textView != null)
                            textView.performClick();


                        Thread.sleep(1000);
                        final TextView 我知道了 = getMatchingView(dialog, "我知道了", new WechatHook.IInstance666() {
                            @Override
                            public boolean invokeInstance(View view) {
                                return view instanceof TextView;
                            }
                        });
                        if (我知道了 != null) {


                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    我知道了.performClick();
                                }
                            }, 2000);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (我知道了监听回调 != null)
                                        我知道了监听回调.onClick(null);
//                                       我知道了监听回调 = null;
                                }
                            }, 5000);


                        }

                        final List<Button> 继续转账 = new ArrayList<Button>();
                        getAnyViews(((FrameLayout) dialog.getWindow().getDecorView()), 继续转账, new WechatHook.IInstance666() {
                            @Override
                            public boolean invokeInstance(View view) {
                                return view instanceof Button;
                            }
                        });
                        if (继续转账 != null) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    for (TextView view : 继续转账) {
                                        view.performClick();
                                    }
                                }
                            }, 2000);
                        }


                        Toast.makeText(dialog.getContext(), "继续转账---", Toast.LENGTH_SHORT).show();


                    }
                });
        XposedHelpers.findAndHookMethod(
                "com.tencent.mm.ui.widget.b.d.a",
                loadPackageParam.classLoader,
                "show",
//                String.class,
//                Bundle.class,
//                boolean.class,
                new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(final MethodHookParam param) throws Throwable {
                        Dialog dialog = (Dialog) param.thisObject;
                        final List<Button> 继续转账 = new ArrayList<Button>();
                        getAnyViews(((FrameLayout) dialog.getWindow().getDecorView()), 继续转账, new WechatHook.IInstance666() {
                            @Override
                            public boolean invokeInstance(View view) {
                                return view instanceof Button;
                            }
                        });
                        if (继续转账 != null) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    for (TextView view : 继续转账) {
                                        if (view.getText().toString().equals("继续转账"))
                                            view.performClick();
                                    }
                                }
                            }, 2000);
                        }
                        Toast.makeText(dialog.getContext(), "继续转账---*****", Toast.LENGTH_SHORT).show();


                    }
                });
    }


}
