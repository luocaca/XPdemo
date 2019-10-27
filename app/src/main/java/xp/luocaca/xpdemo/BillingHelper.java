package xp.luocaca.xpdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.NotificationCompat.Builder;

import xp.luocaca.xpdemo.entity.GetBilling;
import xp.luocaca.xpdemo.entity.GetBillingResp;
import xp.luocaca.xpdemo.vpnservice.aidl.VpnProfile;

public class BillingHelper implements Runnable {
    private static BillingHelper instance;
    private Context context;
    private volatile long dnsFailCount = 0;
    private volatile boolean isBilling = false;
    private volatile boolean isReporting = false;
    private volatile long lastRun = 0;
    private GetBilling remote = new GetBilling();

    private NotificationManager nm;
    private volatile long reqFailCount = 0;
    private Resources res;
    private Thread thread;

    private BillingHelper(Context context2) {
        this.context = context2;
        this.thread = new Thread(this);
        this.thread.start();
        this.res = context2.getResources();
    }

    public static synchronized BillingHelper getInstance(Context context2) {
        BillingHelper billingHelper;
        synchronized (BillingHelper.class) {
            if (instance == null) {
                instance = new BillingHelper(context2);
            }
            billingHelper = instance;
        }
        return billingHelper;
    }

    public static void start() {
        if (!instance.isBilling) {
            instance.isBilling = true;
        }
    }

    public static void stop() {
        if (instance.isBilling) {
            instance.isBilling = false;
        }
    }

    public void checkAsync() {
        new Thread(new Runnable() {
            public void run() {
                BillingHelper.this.checkBilling();
            }
        }).start();
    }

    public boolean checkBilling() {
        return checkBilling(false, null);
    }

    public boolean checkBilling(boolean isConnected, String remoteIp) {
        if (!AuthHelper.isLogined()) {
            return false;
        }
        if (!this.remote.doSubmit(isConnected, remoteIp) || this.remote.getResult() == null) {

            return false;
        }
        GetBillingResp resp = this.remote.getResult();
        GlobalVariable.billingType = resp.billingType;
        GlobalVariable.residueTraffic = resp.traffic;


        GlobalVariable.residueTraffic = 56556;


        GlobalVariable.residueDuration = resp.duration;
        String notify = null;
        switch (resp.result) {
            case 1:
                AuthHelper.onAuthFailed(this.context);

                break;
            case 3:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 25:
                break;
        }
        if (resp.result == 0 || notify == null) {
            return true;
        }

        return false;
    }


    public void testSmailSetDouble() {
        GlobalVariable.residueDuration = 99999999;
        GlobalVariable.residueTraffic = 18898;
    }


    public void showNotify(String title, String text) {
        if (AppUtil.isBackground(this.context)) {
            Builder builder = new Builder(this.context);
            builder.setContentTitle(title);
            builder.setContentText(text);
            builder.setSmallIcon(R.mipmap.ic_launcher);
            Notification notification = builder.build();
            notification.flags |= 16;
            this.nm.notify(2, notification);
            return;
        }
        ToastUtil.show(this.context, text);
    }

    public void run() {
        while (true) {
            boolean isTimeUp = System.currentTimeMillis() - this.lastRun > 60000;
            boolean isActive = VpnStatus.isActive();
            if (this.isBilling && isTimeUp && isActive) {
                VpnProfile profile = GlobalVariable.vpnProfile;
                String remote2 = profile == null ? "" : profile.host;
                String domain = profile == null ? "" : profile.domain;
                if (StringUtil.isNotEmpty(domain)) {
                    if (!checkResolve(domain, remote2)) {
                        this.dnsFailCount++;
                    } else {
                        this.dnsFailCount = 0;
                    }
                    remote2 = domain;
                } else {
                    this.dnsFailCount = 0;
                }
                if (!checkBilling(isActive, remote2)) {
                    this.reqFailCount++;
                } else {
                    this.reqFailCount = 0;
                }
                if (this.reqFailCount > 5 || this.dnsFailCount > 5) {

                }
                this.lastRun = System.currentTimeMillis();
            }
            try {
                Thread.sleep(6000);
            } catch (Exception e) {
            }
        }
    }

    private boolean checkResolve(String domain, String host) {

        return true;
    }
}