package com.sinovatech.unicom.push.notice;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat;

import xp.luocaca.xpdemo.MainActivity;
import xp.luocaca.xpdemo.R;

public class NotinyUtil {

    /**
     * 发送通知
     */
    public static void sendNotification(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_launcher); // 这里使用的系统默认图标，可自行更换
        builder.setTicker("您有一条新消息！");
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher));
        builder.setContentTitle("这是第一行标题栏");
        builder.setContentText("这里是第二行，用来显示主要内容");
        builder.setAutoCancel(true);

        // 点击后要执行的操作，打开MainActivity
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntents = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        builder.setContentIntent(pendingIntents);

        // 启动Notification，getNotification()方法已经过时了，不推荐使用，使用build()方法替代
        notificationManager.notify(1, builder.build());
    }


    public static void createNotificationChannel(Context context, int notifactionId) {
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder notification = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = String.valueOf(notifactionId);
            CharSequence channelName = "channelName";
            String channelDescription = "channelDescription";
            int channelImportance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, channelImportance);
            // 设置描述 最长30字符
            notificationChannel.setDescription(channelDescription);
            // 该渠道的通知是否使用震动
            notificationChannel.enableVibration(true);
            // 设置显示模式
            notificationChannel.setLockscreenVisibility(NotificationCompat.VISIBILITY_SECRET);
//          notificationChannel.setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.order_tishi), null);


            notificationManager.createNotificationChannel(notificationChannel);
            notification = new Notification.Builder(context);
            notification.setChannelId(channelId);
            notification.setContentTitle("活动");
            notification.setContentText("您有一项新活动");
//            notification.setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.order_tishi));
            notification.setSmallIcon(R.mipmap.ic_launcher_round).build();

        } else {
            notification = new Notification.Builder(context);
            notification.setAutoCancel(true)
                    .setContentText("自定义推送声音111")
                    .setContentTitle("111")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setDefaults(Notification.DEFAULT_ALL);
//            notification.setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.order_tishi));
        }
        notificationManager.notify(1024, notification.getNotification());
    }


    //开始通知


    @SuppressLint("WrongConstant")
    public static void startNotificationManager(Activity applicationContext, String title, int idIco) {


        NotificationManager notificationManager = (NotificationManager) applicationContext.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(applicationContext, MainActivity.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, 0);

        long[] vibrate = {0, 500, 1000, 1500};

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder notification = new Notification
                    .Builder(applicationContext)
                    .setContentTitle("您有一条新消息")
                    .setContentText(title)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(idIco)
                    .setLargeIcon(BitmapFactory.decodeResource(applicationContext.getResources(), idIco))
                    .setVibrate(vibrate)
                    .setContentIntent(pendingIntent)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setChannelId(applicationContext.getPackageName())
                    .setSound(MediaStore.Audio.Media.INTERNAL_CONTENT_URI);

            NotificationChannel channel = new NotificationChannel(
                    applicationContext.getPackageName(),
                    "会话消息(掌嗨)",
                    NotificationManager.IMPORTANCE_MAX

            );

            notificationManager.createNotificationChannel(channel);

            notificationManager.notify(0, notification.build());


        } else {

            Notification.Builder notification = new Notification
                    .Builder(applicationContext)
                    .setContentTitle("您有一条新消息")
                    .setContentText(title)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(idIco)
                    .setLargeIcon(BitmapFactory.decodeResource(applicationContext.getResources(), idIco))
                    .setVibrate(vibrate)
                    .setContentIntent(pendingIntent)
                    .setWhen(System.currentTimeMillis())
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setSound(MediaStore.Audio.Media.INTERNAL_CONTENT_URI);
            notificationManager.notify(0, notification.build());

        }


    }

}
