package xp.luocaca.xpdemo;

import android.content.Context;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import xp.luocaca.xpdemo.entity.ServiceListResp.NodeBase;
import xp.luocaca.xpdemo.vpnservice.aidl.VpnProfile;

public class GlobalVariable {
    public static String ServiceTime;
    public static String account;
    public static List<NodeBase> allNodes = new CopyOnWriteArrayList();
    public static String appChannel = "Official";
    public static String appDomain;
    public static String appPackageName;
    public static String appVersion;
    public static int billingType;
    public static Context context = null;
    public static String deviceId;
    public static Date expiryTime;
    public static String explainKey;
    public static Date getPackageTime;
    public static boolean isFirstRun = false;
    public static boolean isReview = true;
    public static String isoCode = "CN";
    public static String language = "en";
    public static Date lastLoadPackageTime = null;
    public static Date lastLoadServerTime = null;
    public static Date lastLoginTime;
    public static String officialSite;
    public static String password;
    public static String phoneNum;
    public static String publicIp;
    public static String pushToken;
    public static long residueDuration;
    public static double residueTraffic;
    public static int screenHeight;
    public static int screenWidth;
    public static String session;
    public static String supportMail;
    public static String supportSite;
    public static String telCode = "0086";
    public static String terminal;
    public static String vpnAccount;
    public static long vpnConnectedTime = 0;
    public static String vpnPassword;
    public static VpnProfile vpnProfile = null;
    public static int vpnStatus = VpnStatus.STOPPED;

    public static boolean init(Context context2) {
        if (context != null) {
            return true;
        }
        appVersion = AppUtil.getVersionName(context2);
        appPackageName = AppUtil.getPackageName(context2);
        appDomain = AppUtil.getMetaData(context2, "APP_DOMAIN");
        explainKey = AppUtil.getMetaData(context2, "EXPLAIN_KEY");
        if (StringUtil.isEmpty(appVersion, appDomain, explainKey)) {
            return false;
        }
        context = context2.getApplicationContext();
        supportSite = AppUtil.getMetaData(context2, "SUPPORT_SITE");
        supportMail = AppUtil.getMetaData(context2, "SUPPORT_MAIL");
        appChannel = AppUtil.getMetaData(context2, "InstallChannel");
        officialSite = AppUtil.getMetaData(context2, "OFFICIAL_SITE");

        return true;
    }
}
