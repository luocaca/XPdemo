package xp.luocaca.xpdemo;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.v4.app.NotificationCompat.Builder;
import android.util.Log;

public class AppUtil {
    public static String getPackageName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).packageName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getMetaData(Context context, String key) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.get(key).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isBackground(Context context) {
        for (RunningAppProcessInfo appProcess : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
            if (appProcess.processName.equals(context.getPackageName())) {
                if (appProcess.importance == 400) {
                    Log.i("后台", appProcess.processName);
                    return true;
                }
                Log.i("前台", appProcess.processName);
                return false;
            }
        }
        return false;
    }

    public static void showNotify(Context context, int notifyId, String title, String text) {
        if (isBackground(context)) {
            PendingIntent pi = PendingIntent.getActivity(context, 0, new Intent(context, context.getApplicationContext().getClass()), 134217728);
            Builder builder = new Builder(context);
            builder.setContentTitle(title);
            builder.setContentText(text);
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setContentIntent(pi);
            Notification notification = builder.build();
            notification.flags |= 16;
            ((NotificationManager) context.getSystemService("notification")).notify(notifyId, notification);
            return;
        }
        ToastUtil.show(context, title + "\r\n\r\n" + text);
    }
}
