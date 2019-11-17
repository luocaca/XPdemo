package com.hldj.hmyg.wxapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXEntryActivity extends Activity  implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Button(this));
        api = WXAPIFactory.createWXAPI(this, "wx8349f112c57ef68b");
        api.handleIntent(getIntent(), this);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }


    public static void start(Context context) {
        Intent starter = new Intent(context, WXEntryActivity.class);
        context.startActivity(starter);
    }


    @Override
    public void onReq(BaseReq baseReq) {
        System.out.println("");
    }

    @Override
    public void onResp(BaseResp baseResp) {
        System.out.println("");
        Toast.makeText(this, "微信授权成功---》"+baseResp.toString(), Toast.LENGTH_SHORT).show();

    }
}
