package xp.luocaca.xpdemo.wechat.iwechat;

import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * 微信分发接口
 */
public interface IWechat {



    void hook(XC_LoadPackage.LoadPackageParam lpparam);



    void 微信授权登陆界面(XC_LoadPackage.LoadPackageParam lpparam);


    void 口令发红包(XC_LoadPackage.LoadPackageParam lpparam);


    void 自动输入密码(XC_LoadPackage.LoadPackageParam lpparam);



    void 微信方法堆栈(XC_LoadPackage.LoadPackageParam lpparam);



    void 微信方法日志i(XC_LoadPackage.LoadPackageParam lpparam);



    void 聊天消息监听(XC_LoadPackage.LoadPackageParam lpparam);



    void 微信首页(XC_LoadPackage.LoadPackageParam lpparam);




}
