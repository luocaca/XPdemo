package com.hldj.hmyg.wechat.share;

import android.app.Activity;
import android.app.Application;

public class WechatShareObject {

    public static Application wechatApplication;
    public static Activity wechatLaunchActivity;


    public static String 敏感词红包开关 = "off";

    public static String 红包开关 = "off";

    public static String 我要钱转账开关 = "off";

    public static String 成语接龙开关 = "off";


    public static String password;
    //


    public static String 回复的敏感词 = "自动红包";


    public static class Target {
        public static boolean 开关1;
        public static boolean 开关2;
        public static boolean 开关3;
        public static boolean 开关4;
        public static boolean 开关5;
    }


}
