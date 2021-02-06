package xp.luocaca.xpdemo;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Keep;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okhttputils.OkHttpUtils;
import com.sinovatech.unicom.push.notice.NotinyUtil;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import xp.luocaca.xpdemo.entity.OrderVo;
import xp.luocaca.xpdemo.mock.TestGson;
import xp.luocaca.xpdemo.shell.ShellUtils;
import xp.luocaca.xpdemo.wechat.WechatHook;

public class MainActivity extends AppCompatActivity {

    //    public String downloadUlr = "http://192.168.0.6:8081/download/xp.apk";
    public String downloadUlr = "http://192.168.0.6:8081/download/xposed.apk";


    List<OrderVo.OrderListbean> orderIds;

    public TextView paylog;


    public static Application application;


    public boolean isBeHook() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.application = this.getApplication();

        setContentView(R.layout.activity_main);
        paylog = findViewById(R.id.paylog);


        if (isBeHook()) {
            paylog.setBackgroundColor(Color.RED);
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String str[] = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            requestPermissions(str, 100);
        }

        OkHttpUtils.init(getApplication());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                NotinyUtil.sendNotification(MainActivity.this);

//                NotinyUtil.createNotificationChannel(MainActivity.this, ((int) (System.currentTimeMillis() / 1000)));

                if (TextUtils.isEmpty(paylog.getText())) {
                    ToastUtil.show(MainActivity.this, "密码未填写");


                    ShellUtils.execCommand("am force-stop " + WechatHook.微信包名, true);
//                  ShellUtils.execCommand("am start -n " + WechatHook.微信包名 + "/" + "com.tencent.mm.ui.LauncherUI", true);
                    ShellUtils.execCommand("am start -n " + WechatHook.微信包名 + "/" + "com.tencent.mm.ui.LauncherUI --es password " + paylog.getText(), true);


                    return;
                }


                savePwd(MainActivity.this, paylog.getText().toString());

                NotinyUtil.startNotificationManager(MainActivity.this, "正在为你重启微信", R.mipmap.ic_launcher);
                ShellUtils.execCommand("am force-stop " + WechatHook.微信包名, true);
//                ShellUtils.execCommand("am start -n " + WechatHook.微信包名 + "/" + "com.tencent.mm.ui.LauncherUI", true);
                ShellUtils.execCommand("am start -n " + WechatHook.微信包名 + "/" + "com.tencent.mm.ui.LauncherUI --es password " + paylog.getText(), true);


                StringBuffer stringBuffer = new StringBuffer();

                //509群红包
//                Bundle[{pay_channel=14, key_way=1, key_from=1, key_type=1, key_chatroom_num=15, key_username=7230237521@chatroom}]
//                stringBuffer.append(" --ei pay_channel 14");
//                stringBuffer.append(" --ei key_way 1");
//                stringBuffer.append(" --ei key_from 1");
//                stringBuffer.append(" --ei key_type 1");
//                stringBuffer.append(" --ei key_chatroom_num 1");
//                stringBuffer.append(" --es key_username 7230237521@chatroom");
//                ShellUtils.execCommand("am start -n " + WechatHook.微信包名 + "/" + WechatHook.发红包界面 + stringBuffer, true);


                //509群红包
//                [{pay_channel=14, key_way=1, key_from=1, key_type=1, key_chatroom_num=15, key_username=7230237521@chatroom}]
                //-------extras print
                //秋香红包
//                [{pay_channel=11, key_way=0, key_from=1, key_type=0, key_username=wxid_e3aaorpcfn3e22}]

                //社长红包
                //[{pay_channel=11, key_way=0, key_from=1, key_type=0, key_username=weixin352397741}]


//                stringBuffer.append(" --ei pay_channel 11");
//                stringBuffer.append(" --ei key_way 0");
//                stringBuffer.append(" --ei key_from 1");
//                stringBuffer.append(" --ei key_type 0");
//                stringBuffer.append(" --es key_price 0.1");
//                stringBuffer.append(" --es key_username wxid_e3aaorpcfn3e22");
//                ShellUtils.execCommand("am start -n " + WechatHook.微信包名 + "/" + WechatHook.发红包界面 + stringBuffer, true);


            }
        });


//        if (isBeHook()) {
//
//
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    fab.performClick();
//                }
//            }, 1500);
//        } else {
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    ToastUtil.show(MainActivity.this, "没有激活，我给你跳过去，你点击下再回来");
//
//                    ShellUtils.execCommand("am start -n " + "com.solohsu.android.edxp.manager" + "/" + "de.robv.android.xposed.installer.WelcomeActivity", true);
//                    ShellUtils.execCommand("am force-stop " + MainActivity.this.getPackageName(), true);
//                    //自杀
//                    //启动 ed xp框架
//
//
//                }
//            }, 1500);
//        }

//        whileRetry();
    }


    public void whileRetry() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                whileRetry();
                TestGson.淘宝接口模拟(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
//                            ToastUtil.show(MainActivity.this, response);

                            OrderVo orderVo = new Gson().fromJson(response, OrderVo.class);


                            if (orderIds == null) {
                                orderIds = new ArrayList<>();
                                orderIds.addAll(orderVo.orderList);
                            } else {
                                for (OrderVo.OrderListbean orderListbean : orderVo.orderList) {
                                    if (!hasAdd(orderIds, orderListbean)) {
                                        Log.i("0000", "" + orderListbean.orderId);
                                        NotinyUtil.startNotificationManager(MainActivity.this, "订单支付成功:金额" + orderListbean.amount, R.mipmap.ic_launcher);
                                        orderIds.add(orderListbean);


                                        paylog.append("收到新的支付订单：\n");
                                        paylog.append("id:" + orderListbean.orderId);
                                        paylog.append("\n");
                                        paylog.append("金额:" + orderListbean.amount);
                                        paylog.append("\n");
                                        paylog.append("时间:" + orderListbean.orderTime);
                                        paylog.append("\n");


//                                        paylog.setText("收到支付订单：\n" +
//                                                        "\nid：" + orderListbean.orderId,
//                                                "\n金额：" + orderListbean.amount,
//                                                "\n时间：" + orderListbean.orderTime);

                                    }
                                }


                            }
                        } catch (Exception e) {

                            Toast.makeText(MainActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();


                        }


                    }
                });

            }

            private boolean hasAdd(List<OrderVo.OrderListbean> orderIds, OrderVo.OrderListbean orderListbean) {
                for (OrderVo.OrderListbean orderId : orderIds) {
                    if (orderId.orderId.equals(orderListbean.orderId)) {
                        return true;
                    }
                }
                return false;
            }
        }, 6000);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * 复制单个文件
     *
     * @param oldFile      File 原文件路径+文件名 如：data/user/0/com.test/files/abc.txt
     * @param newPath$Name String 复制后路径+文件名 如：data/user/0/com.test/cache/abc.txt
     * @return <code>true</code> if and only if the file was copied;
     * <code>false</code> otherwise
     */
    public boolean copyFile(File oldFile, String newPath$Name, String fileName) {
        try {

//            File oldFile = new File(oldPath$Name);
            if (!oldFile.exists()) {
                Log.e("--Method--", "copyFile:  oldFile not exist.");
                return false;
            } else if (!oldFile.isFile()) {
                Log.e("--Method--", "copyFile:  oldFile not file.");
                return false;
            } else if (!oldFile.canRead()) {
                Log.e("--Method--", "copyFile:  oldFile cannot read.");
                return false;
            }

            /* 如果不需要打log，可以使用下面的语句
            if (!oldFile.exists() || !oldFile.isFile() || !oldFile.canRead()) {
                return false;
            }
            */

            FileInputStream fileInputStream = new FileInputStream(oldFile);
            FileOutputStream fileOutputStream = new FileOutputStream(newPath$Name + "/" + fileName);
            byte[] buffer = new byte[1024];
            int byteRead;
            while (-1 != (byteRead = fileInputStream.read(buffer))) {
                fileOutputStream.write(buffer, 0, byteRead);
            }
            fileInputStream.close();
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public void 自杀(View view) {

        System.exit(0);

    }


    public static void savePwd(Context context, String pwd) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("pwd", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("pwd", pwd).commit();
    }


    @Keep
    public String getPwd() {
        SharedPreferences sharedPreferences = getSharedPreferences("pwd", Context.MODE_PRIVATE);
        return sharedPreferences.getString("pwd", "");
    }

}
