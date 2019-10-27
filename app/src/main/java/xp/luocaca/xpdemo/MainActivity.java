package xp.luocaca.xpdemo;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

public class MainActivity extends AppCompatActivity {

    //    public String downloadUlr = "http://192.168.0.6:8081/download/xp.apk";
    public String downloadUlr = "http://192.168.0.6:8081/download/xposed.apk";


    List<OrderVo.OrderListbean> orderIds;

    TextView paylog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        paylog = findViewById(R.id.paylog);

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

                NotinyUtil.startNotificationManager(MainActivity.this, "订单支付成功", R.mipmap.ic_launcher


                );
            }
        });


        whileRetry();
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


}
