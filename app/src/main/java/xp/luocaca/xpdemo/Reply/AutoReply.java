package xp.luocaca.xpdemo.Reply;

import android.os.Handler;

import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * 自动回复功能
 */
public class AutoReply {


    public static void 自动回复(long delay, String content, String replyWechatId, XC_LoadPackage.LoadPackageParam loadPackageParam) {

        /**
         *  com.tencent.mm.modelmulti.h hVar = new com.tencent.mm.modelmulti.h(str3, str, ov, i2, oVar.edB().iT(talkerUserName, str2));
         *                 ((t) ai.this.cEm.aX(t.class)).g(hVar);
         *                 az.ZS().a((m) hVar, 0);
         */

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                try {
                    Class clz_h = XposedHelpers.findClass("com.tencent.mm.modelmulti.h", loadPackageParam.classLoader);
                    Object msg = XposedHelpers.newInstance(clz_h, replyWechatId, content, 1);
                    Class clz_aw = XposedHelpers.findClass("com.tencent.mm.model.az", loadPackageParam.classLoader);
                    Object clz_p = XposedHelpers.callStaticMethod(clz_aw, "ZS");

                    /*  7.0.6  自动回复备份    新增7.0.7 自动回复功能 */
//            Class clz_h = XposedHelpers.findClass("com.tencent.mm.modelmulti.h", loadPackageParam.classLoader);
//            Object msg = XposedHelpers.newInstance(clz_h, replyWechatId, content, 1);
//            Class clz_aw = XposedHelpers.findClass("com.tencent.mm.model.aw", loadPackageParam.classLoader);
//            Object clz_p = XposedHelpers.callStaticMethod(clz_aw, "Vs");

                    //package com.tencent.mm.aj;
                    //public final class p implements f, j {
                    //    public final boolean a(m mVar, int i) {
                    XposedHelpers.callMethod(clz_p, "a", msg, 0);
                } catch (Exception e) {
                    XposedBridge.log(e);
                    XposedBridge.log(e);
                    XposedBridge.log(e);
                }

            }
        }, delay);


    }


    /**
     * 图灵文档地址
     * api接入
     * apikey：a1e256cf9518d5e472f8ef8656ae964b
     * <p>
     * 密钥： 78ad15901bd56912
     * https://www.kancloud.cn/turing/www-tuling123-com/718218
     *
     * @param delay
     * @param content
     * @param replyWechatId
     * @param loadPackageParam
     */
    public static void 机器人异步自动回复(long delay, String content, String replyWechatId, XC_LoadPackage.LoadPackageParam loadPackageParam) {

        /**
         *  com.tencent.mm.modelmulti.h hVar = new com.tencent.mm.modelmulti.h(str3, str, ov, i2, oVar.edB().iT(talkerUserName, str2));
         *                 ((t) ai.this.cEm.aX(t.class)).g(hVar);
         *                 az.ZS().a((m) hVar, 0);
         */

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                try {
                    Class clz_h = XposedHelpers.findClass("com.tencent.mm.modelmulti.h", loadPackageParam.classLoader);
                    Object msg = XposedHelpers.newInstance(clz_h, replyWechatId, content, 1);
                    Class clz_aw = XposedHelpers.findClass("com.tencent.mm.model.az", loadPackageParam.classLoader);
                    Object clz_p = XposedHelpers.callStaticMethod(clz_aw, "ZS");
                    /*  7.0.6  自动回复备份    新增7.0.7 自动回复功能 */
//            Class clz_h = XposedHelpers.findClass("com.tencent.mm.modelmulti.h", loadPackageParam.classLoader);
//            Object msg = XposedHelpers.newInstance(clz_h, replyWechatId, content, 1);
//            Class clz_aw = XposedHelpers.findClass("com.tencent.mm.model.aw", loadPackageParam.classLoader);
//            Object clz_p = XposedHelpers.callStaticMethod(clz_aw, "Vs");

                    //package com.tencent.mm.aj;
                    //public final class p implements f, j {
                    //    public final boolean a(m mVar, int i) {
                    XposedHelpers.callMethod(clz_p, "a", msg, 0);
                } catch (Exception e) {
                    XposedBridge.log(e);
                    XposedBridge.log(e);
                    XposedBridge.log(e);
                }

            }
        }, delay);


    }


}
