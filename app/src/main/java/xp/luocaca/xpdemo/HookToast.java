package xp.luocaca.xpdemo;

import android.widget.Button;

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
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {


        if (!loadPackageParam.packageName.equals("com.fivetime.meidajs")) {
            return;
        }

        final Class clazz = loadPackageParam.classLoader.loadClass("com.fivetime.vpn.utils.DesBase64");


        XposedHelpers.findAndHookMethod(clazz, "decrypt", String.class, String.class, new XC_MethodHook() {
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                XposedBridge.log("改1beforeHookedMethod别看了，老子已经成功Hook热重启\n" + param.getResult());
                super.beforeHookedMethod(param);

            }

            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                XposedBridge.log("afterHookedMethod劫持之前测试热重启改1" + param.getResult());
                XposedBridge.log("afterHookedMethod劫持之后热重启改1" + param.getResult());
                XposedBridge.log("改1beforeHookedMethod别看了，老子已经成功Hook热重启\n" + param.getResult());
            }
        });


        hookMainActivity(loadPackageParam);


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

//                exclFild.add("method");
//                exclFild.add("password");
//                exclFild.add("host");
//                exclFild.add("remotePort");

                //   Object[] objArr = new Object[5];
                //        objArr[0] = this.method;
                //        objArr[1] = this.auth ? "-auth" : "";
                //        objArr[2] = this.password;
                //        objArr[3] = this.host;
                //        objArr[4] = Integer.valueOf(this.remotePort);


                validateFild(obj, exclFild);


            }
        });


    }


    /**
     * <p>Title: validateFild</p>
     * <p>Description: 这是一个以反射机制为基础的判断对象内部的属性是否为空的方法</p>
     *
     * @param obj      要判断的对象实例
     * @param exclFild 放行的属性, 不需要做判断的属性
     * @return 布尔类型, 这个可以根据需求做出变更
     */
    public static boolean validateFild(Object obj, List<String> exclFild) {

        boolean target = false;

        for (Field f : obj.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            try {
                String name = f.getName();
                // 判断属性名称是否在排除属性值中
                // 判断字段是否为空，并且对象属性中的基本都会转为对象类型来判断
                target = true;
                System.out.println(name);
                System.out.println(f.get(obj).toString());
                XposedBridge.log(name);
                XposedBridge.log(f.get(obj).toString());

            } catch (IllegalArgumentException e) {
                target = false;
                System.out.println("对象属性解析异常" + e.getMessage());
                return target;
            } catch (IllegalAccessException e) {
                target = false;
                System.out.println("对象属性解析异常" + e.getMessage());
                return target;
            }
        }
        return target;
    }


    /**
     * 设置button hook
     *
     * @param param
     * @param clazz
     */
    private void setButtonText(XC_MethodHook.MethodHookParam param, Class clazz) throws Throwable {
        Field field = clazz.getDeclaredField("button");
        field.setAccessible(true);
        XposedBridge.log("--设置button hook--");
        Button button = (Button) field.get(param.thisObject);
        button.setText("我被hook了66666");

    }
}
