package xp.luocaca.xpdemo.wechat;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import xp.luocaca.xpdemo.Reply.AutoReply;
import xp.luocaca.xpdemo.ToastUtil;
import xp.luocaca.xpdemo.shell.ShellUtils;
import xp.luocaca.xpdemo.wechat.iwechat.IWechat;
import xp.luocaca.xpdemo.wechat.model.自动输入密码模块.自动输入密码;
import xp.luocaca.xpdemo.wechat.share.WechatShareObject;
import xp.luocaca.xpdemo.wechat.我要钱.我要钱功能;
import xp.luocaca.xpdemo.图片识别系统.筛子剪刀石头布等等图片识别工具类;
import xp.luocaca.xpdemo.粗话系统.SensitivewordFilter;

import static android.view.KeyEvent.ACTION_DOWN;
import static de.robv.android.xposed.XposedBridge.log;
import static xp.luocaca.xpdemo.wechat.我要钱.我要钱功能.跳转转账界面比U币把版本;

/**
 * hook 微信密码矿
 */
public class WechatHook implements IXposedHookLoadPackage, IWechat {

    private static final String TAG = "WechatHook";

    public static final String 微信包名 = "com.tencent.mm";
    public static final String 发红包界面 = "com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyPrepareUI";
    public static final String 微信授权第三方登陆界面 = "com.tencent.mm.plugin.webview.ui.tools.SDKOAuthUI";

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) {
        XposedBridge.log(TAG + "-------------聊天消息开始--------------");

        聊天消息监听(lpparam);

        微信首页(lpparam);
        口令发红包(lpparam);
//        自动输入密码(lpparam);
//        微信方法堆栈(lpparam);
//        微信方法日志i(lpparam);
        new 自动输入密码().自动输入密码(lpparam);


        我要钱功能.hook(lpparam);


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


                        Bundle bundle = thisObject.getIntent().getExtras();

                        ToastUtil.show(thisObject, "收到数据" + thisObject.getIntent().getExtras
                                ().toString());


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
    public void 口令发红包(XC_LoadPackage.LoadPackageParam lpparam) {


        XposedHelpers.findAndHookMethod(
                发红包界面,
                lpparam.classLoader,
                "onCreate",
                Bundle.class,
                new XC_MethodHook() {

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);
                        Activity thisObject = ((Activity) param.thisObject);

                        int chatId = thisObject.getIntent().getIntExtra("key_chatroom_num", 0);
                        String key_username = thisObject.getIntent().getStringExtra("key_username");


                        Toast.makeText(thisObject, "我被你抓到了。亲\n" + thisObject.getIntent().getExtras().toString(), Toast.LENGTH_SHORT).show();

                        //反射打印所有方法 与属性


                        String price = thisObject.getIntent().getExtras().getString("key_price");

                        List<EditText> editTexts = new ArrayList<>();


                        getAnyViews(((FrameLayout) thisObject.getWindow().getDecorView()), editTexts, new IInstance666() {
                            @Override
                            public boolean invokeInstance(View view) {
                                return view instanceof EditText;
                            }
                        });


                        int 基数 = 1;
                        int count = 1;
                        if (WechatShareObject.回复的敏感词.contains("1点")) {
                            基数 = 1;
                        } else if (WechatShareObject.回复的敏感词.contains("2点")) {
                            基数 = 2;
                        } else if (WechatShareObject.回复的敏感词.contains("3点")) {
                            基数 = 3;
                        } else if (WechatShareObject.回复的敏感词.contains("4点")) {
                            基数 = 4;
                        } else if (WechatShareObject.回复的敏感词.contains("5点")) {
                            基数 = 5;
                        } else if (WechatShareObject.回复的敏感词.contains("6点")) {
                            基数 = 6;
                        } else if (WechatShareObject.回复的敏感词.contains("剪刀")) {
                            基数 = 5;
                            count = 2;
                        } else if (WechatShareObject.回复的敏感词.contains("石头")) {
                            基数 = 4;
                            count = 3;
                        } else if (WechatShareObject.回复的敏感词.contains("布")) {
                            基数 = 2;
                            count = 5;
                        } else if (WechatShareObject.回复的敏感词.contains("别砍我")) {
                            基数 = 4;
                            count = 1;
                        }


                        double realPrice = Double.parseDouble(price) * 基数 * count;

                        for (int i = 0; i < editTexts.size(); i++) {
                            if (i == 0) {
                                editTexts.get(i).setText("" + realPrice);
                            } else if (i == 1) {
                                if (key_username.contains("@")) {
                                    editTexts.get(i).setText("" + count);
                                } else {

                                }
                            } else if (i == 2) {
                                editTexts.get(i).setText(WechatShareObject.回复的敏感词);
//                                editTexts.get(i).setText("自动红包");
                            }
                        }


                        List<TextView> buttons = new ArrayList<>();


                        getAnyViews(((FrameLayout) thisObject.getWindow().getDecorView()), buttons, new IInstance666() {
                            @Override
                            public boolean invokeInstance(View view) {
                                return view instanceof TextView;
                            }
                        });


                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                for (TextView button : buttons) {
                                    XposedBridge.log("---------看看有多少个被找到--------" + button.getText());


                                    if (button.getText().toString().equals("塞钱进红包")) {
                                        button.performClick();
                                    }
                                }
                            }
                        }, 1000);


                        XposedBridge.log("-------extras print -----\n" + thisObject.getIntent().getExtras().toString());
                        XposedBridge.log("-------extras print -----\n" + thisObject.getIntent().getExtras().toString());
                        XposedBridge.log("-------extras print -----\n" + thisObject.getIntent().getExtras().toString());
                        XposedBridge.log("-------extras print -----\n" + thisObject.getIntent().getExtras().toString());
                        XposedBridge.log("-------extras print -----\n" + thisObject.getIntent().getExtras().toString());


                    }
                });

    }

    @Override
    public void 自动输入密码(XC_LoadPackage.LoadPackageParam lpparam) {
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
                        final EditText editText = (EditText) XposedHelpers.findField(param.thisObject.getClass(), "mEditText").get(param.thisObject);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                XposedBridge.log("-------2秒后主动输入2个文字---------");
                                editText.setText(WechatShareObject.password);
                            }
                        }, 0);
//                        try {
//                            throw new NullPointerException("--------自定义异常抛出，查看堆栈-------");
//                        } catch (Exception e) {
//                            XposedBridge.log(e);
//                        }
                    }
                });
    }

    @Override
    public void 微信方法堆栈(XC_LoadPackage.LoadPackageParam lpparam) {
        XposedBridge.log("-------微信方法堆栈-----被hook到了------");

        //package com.tencent.matrix.trace.core;
        //public class AppMethodBeat {
        //public static void o(int i) {
//        XposedHelpers.findAndHookMethod(
//                "com.tencent.matrix.trace.core.AppMethodBeat",
//                lpparam.classLoader,
//                "o",
//                int.class,
//                new XC_MethodHook() {
//                    @Override
//                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                        super.afterHookedMethod(param);
//                        XposedBridge.log("-----AppMethodBeat堆栈记录------- " + param.args[0]);
//
////                        new Thread(new Runnable() {
////                            @Override
////                            public void run() {
////                                try {
////                                    throw new NullPointerException("--------AppMethodBeat堆栈记录 主动抛出异常-----");
////                                } catch (Exception e) {
////                                    XposedBridge.log(e);
////                                }
////                            }
////                        }).start();
//
//                    }
//                });
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


    private static SensitivewordFilter filter;

    public float autoPrice = 0.1f;
    public float feilu = 0.1f;

    @Override
    public void 聊天消息监听(XC_LoadPackage.LoadPackageParam lpparam) {
        /**
         * 插入消息监听
         */
        XposedBridge.log(TAG + "---------监听微信消息---------- 开始");
        Object[] arrayOfObject = new Object[4];
        arrayOfObject[0] = String.class;
        arrayOfObject[1] = String.class;
        arrayOfObject[2] = ContentValues.class;
        arrayOfObject[3] = new XC_MethodHook() {
            protected void afterHookedMethod(MethodHookParam paramAnonymousMethodHookParam) throws XmlPullParserException, IOException {
                //5.577 24979-25044/? I/EdXposed-Bridge: 监听微信消息 insertMsgDBListener 0message
                //1573359185.577 24979-25044/? I/EdXposed-Bridge: 监听微信消息insertMsgDBListener 1msgId
                //insertMsgDBListener 2bizClientMsgId=
                // talker=gh_3dfda90e39d6
                // flag=0 bizChatId=-1
                // msgId=981
                // type=1 content=您好，很高兴认识您~
                // msgSvrId=6203557812977276320
                // lvbuffer=[B@d2cc7df createTime=1573359183000
                // talkerId=110 isSend=0 msgSeq=678076699
                // status=3
                XposedBridge.log(TAG + "监听微信消息 insertMsgDBListener talbe--> " + paramAnonymousMethodHookParam.args[0]);
                XposedBridge.log(TAG + "监听微信消息insertMsgDBListener msgId-->" + paramAnonymousMethodHookParam.args[1]);
                XposedBridge.log(TAG + "监听微信消息insertMsgDBListener content value -->" + paramAnonymousMethodHookParam.args[2].toString());

                ContentValues contentValues = (ContentValues) paramAnonymousMethodHookParam.args[2];


                String talker = contentValues.getAsString("talker");

                String content = contentValues.getAsString("content");

                XposedBridge.log(TAG + "------微信消息内容---content------" + content);

                if (content == null) {
                    return;
                }
                if (talker == null) {
                    return;
                }


                if (content.contains("你好")) {
                    XposedBridge.log(TAG + "-----自动回复----你好---------");
                    AutoReply.自动回复(2500, "@王超  你好啊。么么哒", talker, lpparam);
                    XposedBridge.log(TAG + "------自动回复----你好--------");

                }


                new
                        Thread(new Runnable() {


                    @Override
                    public void run() {
                        XposedBridge.log(TAG + "----------微信消息内容----------" + contentValues.getAsString("content"));
                        XposedBridge.log(TAG + "----------微信消息内容----------" + contentValues.getAsString("talker"));

                        //@chatroom


                        if (filter == null) {
                            filter = new SensitivewordFilter();
                        }
                        System.out.println("敏感词的数量：" + filter.sensitiveWordMap.size());

                        System.out.println("待检测语句字数：" + content.length());
                        long beginTime = System.currentTimeMillis();
                        Set<String> set = filter.getSensitiveWord(content, 1);
                        long endTime = System.currentTimeMillis();
                        System.out.println("语句中包含敏感词的个数为：" + set.size() + "。包含：" + set);
                        System.out.println("总共消耗时间为：" + (endTime - beginTime));

                        String num1 = "da1c289d4e363f3ce1ff36538903b92f";
                        String num2 = "9e3f303561566dc9342a3ea41e6552a6";
                        String num3 = "dbcc51db2765c1d0106290bae6326fc4";
                        String num4 = "9a21c57defc4974ab5b7c842e3232671";
                        String num5 = "3a8e16d650f7e66ba5516b2780512830";
                        String num6 = "5ba8e9694b853df10b9f2a77b312cc09";

                        String 剪刀 = "514914788fc461e7205bf0b6ba496c49";
                        String 石头 = "f790e342a02e0f99d34b316547f9aeab";
                        String 布 = "091577322c40c05aa3dd701da29d6423";
                        String 的确有一套_周润发 = "a5ffc14cebac560a015ee22f2651df11";

                        String 烟花 = "[烟花]";
                        String 炸弹 = "[炸弹]";
                        String 便便 = "[便便]";
                        String 庆祝 = "[庆祝]";
                        String 菜刀 = "[菜刀]";


                        if (!TextUtils.isEmpty(content) && content.contains("我要钱")) {

                            if (lastSendTime != 0) {
                                if (System.currentTimeMillis() - lastSendTime < 5000) {
                                    XposedBridge.log("-----------微信消息内容-----5秒防刷机制--------");

                                    return;
                                } else {
                                    lastSendTime = System.currentTimeMillis();
                                }
                            } else {
                                lastSendTime = System.currentTimeMillis();
                            }

                            if (talker.contains("@"))//过滤群组我要钱，因为不能进行转账
                            {
                                跳转转账界面比U币把版本(lpparam, content.split(":")[0]);
                            } else {
                                跳转转账界面比U币把版本(lpparam, talker);
                            }

                        }


                        if (content.contains("我要红包") || set.size() > 0 || 筛子剪刀石头布等等图片识别工具类.判断是否敏感图片() ||
                                content.contains(num1) ||
                                content.contains(num2) ||
                                content.contains(num3) ||
                                content.contains(num4) ||
                                content.contains(num5) ||
                                content.contains(num6) ||
                                content.contains(剪刀) ||
                                content.contains(石头) ||
                                content.contains(布) ||
                                content.contains(烟花) ||
                                content.contains(炸弹) ||
                                content.contains(的确有一套_周润发) ||
                                content.contains(便便) ||
                                content.contains(庆祝) ||
                                content.contains(菜刀)
                        ) {
//                        if (content.contains("我要红包") || set.size() > 0) {


                            if (content.contains("不许说脏话") || content.contains("给你想要的")) {
                                System.out.println("不许说脏话===========过滤死循环===");
                                return;
                            }
                            if (content.contains("我要红包")) {
                                WechatShareObject.回复的敏感词 = "给你想要的。。";
                            } else if (content.contains(num1)) {
                                WechatShareObject.回复的敏感词 = "红包1点";
                            } else if (content.contains(num2)) {
                                WechatShareObject.回复的敏感词 = "红包2点。。";
                            } else if (content.contains(num3)) {
                                WechatShareObject.回复的敏感词 = "红包3点。。";
                            } else if (content.contains(num4)) {
                                WechatShareObject.回复的敏感词 = "红包4点。。";
                            } else if (content.contains(num5)) {
                                WechatShareObject.回复的敏感词 = "红包5点。。";
                            } else if (content.contains(num6)) {
                                WechatShareObject.回复的敏感词 = "红包6点。。";
                            } else if (content.contains(剪刀)) {
                                WechatShareObject.回复的敏感词 = "剪刀";
                            } else if (content.contains(石头)) {
                                WechatShareObject.回复的敏感词 = "石头";
                            } else if (content.contains(布)) {
                                WechatShareObject.回复的敏感词 = "布";
                            } else if (content.contains(烟花)) {
                                WechatShareObject.回复的敏感词 = "一起骚扰苹果开花";
                            } else if (content.contains(炸弹)) {
                                WechatShareObject.回复的敏感词 = "一起骚扰苹果炸弹";
                            } else if (content.contains(便便)) {
                                WechatShareObject.回复的敏感词 = "想吃屎吗，兄弟";
                            } else if (content.contains(的确有一套_周润发)) {
                                WechatShareObject.回复的敏感词 = "不上你的套";
                            } else if (content.contains(庆祝)) {
                                WechatShareObject.回复的敏感词 = "新年快乐哦哦(*^▽^*)";
                            } else if (content.contains(菜刀)) {
                                WechatShareObject.回复的敏感词 = "别砍我。我把钱都给你...囧.囧.囧.囧";
                            } else {
                                WechatShareObject.回复的敏感词 = "不许说脏话：" + set.iterator().next();
                            }


                            if (lastSendTime != 0) {
                                if (System.currentTimeMillis() - lastSendTime < 5000) {
                                    XposedBridge.log("-----------微信消息内容-----5秒防刷机制--------");


                                    return;
                                } else {
                                    lastSendTime = System.currentTimeMillis();
                                }
                            } else {
                                lastSendTime = System.currentTimeMillis();
                            }


                            if (System.currentTimeMillis() % 5 == 0) {
                                feilu = feilu / 2;

//                                feilu -= 0.02;
                            } else {
                                feilu += 0.03;

                            }


                            StringBuffer stringBuffer = new StringBuffer();
                            Intent intent = new Intent(WechatShareObject.wechatLaunchActivity, XposedHelpers.findClass(WechatHook.发红包界面, lpparam.classLoader));

                            if (talker.endsWith("@chatroom")) {
                                stringBuffer.append(" --ei pay_channel 14");
                                stringBuffer.append(" --ei key_way 1");
                                stringBuffer.append(" --ei key_from 1");
                                stringBuffer.append(" --ei key_type 1");
                                stringBuffer.append(" --es key_price " + feilu);
                                stringBuffer.append(" --ei key_chatroom_num 1");
                                stringBuffer.append(" --es key_username " + talker);
                            } else {
                                stringBuffer.append(" --ei pay_channel 11");
                                stringBuffer.append(" --ei key_way 0");
                                stringBuffer.append(" --ei key_from 1");
                                stringBuffer.append(" --es key_price " + feilu);
                                stringBuffer.append(" --ei key_type 0");
                                stringBuffer.append(" --es key_username " + talker);
                            }

                            if (talker.endsWith("@chatroom")) {
                                intent.putExtra("pay_channel", "14");
                                intent.putExtra("key_way", "1");
                                intent.putExtra("key_from", "1");
                                intent.putExtra("key_type", "1");
                                intent.putExtra("key_price", "" + feilu);
                                intent.putExtra("key_chatroom_num", "1");
                                intent.putExtra("key_username", talker);
                            } else {
                                intent.putExtra("key_way", "0");
                                intent.putExtra("key_from", "1");
                                intent.putExtra("key_type", "0");
                                intent.putExtra("key_price", "" + feilu);
                                intent.putExtra("key_username", talker);


                            }


//                        stringBuffer.append(" --es key_username wxid_e3aaorpcfn3e22");
                            ShellUtils.execCommand("am start -n " + WechatHook.微信包名 + "/" + WechatHook.发红包界面 + stringBuffer, true);


//                            WechatShareObject.wechatLaunchActivity.startActivity(intent);


                            //群消息微信消息内容
                            //bizClientMsgId= talker=5491751483@chatroom flag=0 bizChatId=-1 msgId=986 type=1 content=huweiws707242308:
                            //    荣耀带的动吗？ msgSvrId=2653146997463004420 lvbuffer=[B@7196ba7 createTime=1573359521000 talkerId=543 isSend=0 msgSeq=678076704 status=3


//                            监听微信消息insertMsgDBListener content
//                            value-- > bizClientMsgId = talker = weixin flag = 0 bizChatId = -1
//                            msgId = 985 type = 1 content = 好的。msgSvrId = 2949682216028027455
//                            lvbuffer =[B @ece484d createTime = 1573359448000 talkerId = 139
//                            isSend = 0 msgSeq = 678076702 status = 3
//                            监听微信消息 insertMsgDBListener talbe-- > message
//                            监听微信消息insertMsgDBListener msgId -- > msgId
//                            监听微信消息insertMsgDBListener content
//                            value-- > bizClientMsgId = talker = 5491751483 @chatroom flag = 0
//                            bizChatId = -1 msgId = 986 type = 1 content = huweiws707242308:
//                            荣耀带的动吗？msgSvrId = 2653146997463004420 lvbuffer =[B @ 7196
//                            ba7 createTime = 1573359521000 talkerId = 543 isSend = 0
//                            msgSeq = 678076704 status = 3

                        }


                    }
                }).start();


                //talker=weixin


            }
        };
        XposedHelpers.findAndHookMethod(XposedHelpers.findClass("com.tencent.wcdb.database.SQLiteDatabase", lpparam.classLoader), "insert", arrayOfObject);
        XposedBridge.log("监听微信消息insertMsgDBListener 结束");
    }

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

                        WechatShareObject.password = thisObject.getIntent().getStringExtra("password");


                        /**
                         *   // 生成一个MD5加密计算摘要
                         *             MessageDigest md = MessageDigest.getInstance("MD5");
                         *             // 计算md5函数
                         *             md.update(str.getBytes());
                         *             // digest()最后确定返回md5 hash值，返回值为8位字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
                         *             // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
                         *             //一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方）
                         *             return new BigInteger(1, md.digest()).toString(16);
                         * ————————————————
                         * 版权声明：本文为CSDN博主「逐风的小黄」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
                         * 原文链接：https://blog.csdn.net/qq_30683329/article/details/80879058
                         */


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
                    log("------getMatchingView--getText-" + editText.getText() + "666");
                    log("------getMatchingView--getHint--" + editText.getHint() + "666");
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
     * 模拟用户点击
     *
     * @param view 要触发操作的view
     * @param x    相对于要操作view的左上角x轴偏移量
     * @param y    相对于要操作view的左上角y轴偏移量
     */
    public static void analogUserClick(View view, float x, float y) {
        if (view == null) {
            return;
        }
        Log.e("222", "正：p->" + x + "," + y);
        long downTime = SystemClock.uptimeMillis();//模拟按下去的时间

        long eventTime = downTime;//事件发生时间

        MotionEvent downEvent = MotionEvent.obtain(downTime, eventTime,
                ACTION_DOWN, x, y, 0);
        view.onTouchEvent(downEvent);

        eventTime = eventTime + 90;//离开屏幕时间

        MotionEvent upEvent = MotionEvent.obtain(downTime, eventTime,
                MotionEvent.ACTION_UP, x, y, 0);
        view.onTouchEvent(upEvent);

        //回收事件
        downEvent.recycle();
        upEvent.recycle();
    }


    public static <T extends TextView> T getMatchingView(Dialog dialog, String containText, IInstance666 iInstance) {
        List<T> matchingViewList = new ArrayList<>();
        getAnyViews(((FrameLayout) dialog.getWindow().getDecorView()), matchingViewList, iInstance);
        for (int i = 0; i < ((FrameLayout) dialog.getWindow().getDecorView()).getChildCount(); i++) {
            for (T editText : matchingViewList) {
                if (iInstance.invokeInstance(editText)) {
                    log("------getMatchingView--getText-" + editText.getText() + "666");
                    log("------getMatchingView--getHint--" + editText.getHint() + "666");
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
