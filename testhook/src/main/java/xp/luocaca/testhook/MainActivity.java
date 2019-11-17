package com.hldj.hmyg;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.hldj.hmyg.shell.ShellUtils;
import com.hldj.hmyg.uitl.ToastUtil;
import com.hldj.hmyg.wechat.WechatHook;

import static com.hldj.hmyg.notice.NotinyUtil.startNotificationManager;
import static com.hldj.hmyg.wechat.WechatHook.微信授权第三方登陆界面;

public class MainActivity extends AppCompatActivity {

    TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        content = findViewById(R.id.content);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                startNotificationManager(MainActivity.this, "正在为你重启微信", R.mipmap.ic_launcher);
                ShellUtils.execCommand("am force-stop " + WechatHook.微信包名, true);
                ShellUtils.execCommand("am start -n " + WechatHook.微信包名 + "/" + "com.tencent.mm.ui.LauncherUI", true);
//                ShellUtils.execCommand("am start -n " + WechatHook.微信包名 + "/" + 微信授权第三方登陆界面, true);


                StringBuffer stringBuffer = new StringBuffer();

            }
        });


//        智能自杀系统(fab);


        if (isBeHook()) {
            content.setBackgroundColor(Color.RED);
        }


    }

    private void 智能自杀系统(final FloatingActionButton fab) {


        if (isBeHook()) {


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    fab.performClick();
                }
            }, 1500);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    ToastUtil.show(MainActivity.this, "没有激活，我给你跳过去，你点击下再回来");

                    ShellUtils.execCommand("am start -n " + "com.solohsu.android.edxp.manager" + "/" + "de.robv.android.xposed.installer.WelcomeActivity", true);
                    ShellUtils.execCommand("am force-stop " + MainActivity.this.getPackageName(), true);
                    //自杀
                    //启动 ed xp框架


                }
            }, 1500);
        }


    }

    private boolean isBeHook() {
        return false;
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

    public String toastMessage() {
        return "我未被劫持";
    }


    public void 自杀(View view) {
        System.exit(0);
    }


    public void 模拟授权(View view) {


//        WXEntryActivity.start(this);

        StringBuffer stringBuffer = new StringBuffer();

        //509群红包
//                Bundle[{pay_channel=14, key_way=1, key_from=1, key_type=1, key_chatroom_num=15, key_username=7230237521@chatroom}]
        stringBuffer.append(" --ez SendAppMessageWrapper_TokenValid true");
        stringBuffer.append(" --es _mmessage_appPackage com.hldj.hmyg");
        stringBuffer.append(" --ei _wxapi_command_type 1");
        stringBuffer.append(" --es _mmessage_content weixin://sendreq?appid=wx8349f112c57ef68b");
        stringBuffer.append(" --es _mmessage_checksum [B@7f4a17e");
        stringBuffer.append(" --ez key_auto_login_wizard_exit false");
        stringBuffer.append(" --es SendAppMessageWrapper_PkgName com.hldj.hmyg");
        stringBuffer.append(" --ez android.intent.extra.xspace_userid_selected true");
        stringBuffer.append(" --el _mmessage_sdkVersion 553844737");
        stringBuffer.append(" --es _wxapi_sendauth_req_scope snsapi_userinfo");
        stringBuffer.append(" --es _wxapi_sendauth_req_state sharesdk_wechat_auth");

//                ShellUtils.execCommand("am start -n " + WechatHook.微信包名 + "/" + WechatHook.发红包界面 + stringBuffer, true);


//        ShellUtils.execCommand("am start -n " + 微信包名 + "/" + "de.robv.android.xposed.installer.WelcomeActivity", true);
        ShellUtils.execCommand("am start -n " + WechatHook.微信包名 + "/" + 微信授权第三方登陆界面 + stringBuffer.toString(), true);

    }
}
