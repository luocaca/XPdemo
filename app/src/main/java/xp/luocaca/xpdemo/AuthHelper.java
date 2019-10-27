package xp.luocaca.xpdemo;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import xp.luocaca.xpdemo.entity.UserLogin;
import xp.luocaca.xpdemo.entity.UserLoginResp;

public class AuthHelper {
    private static AuthHelper instance = null;
    /* access modifiers changed from: private */
    public boolean isAuthing = false;
    /* access modifiers changed from: private */
    public List<AuthListener> mListener = new ArrayList();

    private AuthHelper() {
    }

    public static synchronized AuthHelper getInstance() {
        AuthHelper authHelper;
        synchronized (AuthHelper.class) {
            if (instance == null) {
                instance = new AuthHelper();
            }
            authHelper = instance;
        }
        return authHelper;
    }

    public static void addListener(AuthListener listener) {
        if (!getInstance().mListener.contains(listener)) {
            getInstance().mListener.add(listener);
        }
    }

    public static void removeListener(AuthListener listener) {
        if (!getInstance().mListener.contains(listener)) {
            getInstance().mListener.remove(listener);
        }
    }

    public static boolean isLogined() {
        return StringUtil.isNotEmpty(GlobalVariable.account, GlobalVariable.password, GlobalVariable.vpnAccount, GlobalVariable.vpnPassword);
    }

    public static UserLoginResp doEmailLogin(String email, String password) {
        UserLogin remote = new UserLogin();
        UserLoginResp resp = remote.getResult();
        if (resp != null && resp.result == 0) {
            getInstance();
            saveUserInfo(resp);
        }
        return resp;
    }

    public static UserLoginResp doPhoneLogin(String phoneNum, String password) {

        UserLoginResp resp = null ;
        if (resp != null && resp.result == 0) {
            getInstance();
            saveUserInfo(resp);
        }
        return resp;
    }

    public static UserLoginResp doAutoAuth() {

        return null;
    }

    public static synchronized void asyncAuth() {
        synchronized (AuthHelper.class) {
            if (!getInstance().isAuthing) {
                getInstance().isAuthing = true;
                new Thread(new Runnable() {
                    public void run() {
                        boolean result;
                        UserLoginResp resp = AuthHelper.doAutoAuth();
                        if (resp == null || resp.result != 0) {
                            result = false;
                        } else {
                            result = true;
                        }
                        for (AuthListener listener : AuthHelper.getInstance().mListener) {
                            try {
                                listener.OnAuthResult(result, resp);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        AuthHelper.getInstance().isAuthing = false;
                    }
                }).start();
            }
        }
    }

    public static void onAuthFailed(Context context) {
        cleanUserinfo();
        if (VpnStatus.isActive()) {
        }
        new Thread(new Runnable() {
            public void run() {
                for (AuthListener listener : AuthHelper.getInstance().mListener) {
                    try {
                        listener.OnAuthResult(false, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public static void saveUserInfo(UserLoginResp resp) {
        if (resp != null && resp.result == 0) {

            GlobalVariable.session = resp.session;
            GlobalVariable.phoneNum = resp.phoneNum;
            GlobalVariable.account = resp.account;
            GlobalVariable.password = resp.password;
            GlobalVariable.vpnAccount = resp.vpnAccount;
            GlobalVariable.vpnPassword = resp.vpnPassword;
            GlobalVariable.publicIp = resp.yourip;
            GlobalVariable.isoCode = resp.isoCode;
            GlobalVariable.telCode = resp.telCode;
            GlobalVariable.expiryTime = resp.stopDate;
            GlobalVariable.lastLoginTime = resp.lastLogin;

        }
    }

    public static boolean cleanUserinfo() {

        return true;
    }

    public static boolean loadUserinfo() {
        return true;
    }
}
