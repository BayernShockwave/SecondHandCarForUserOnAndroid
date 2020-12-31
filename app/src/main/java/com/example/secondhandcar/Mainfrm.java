package com.example.secondhandcar;

import android.app.TabActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;

@SuppressWarnings("deprecation")//屏蔽警告

public class Mainfrm extends TabActivity {

    public String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainfrm);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        userid = bundle.getString("userid");
        System.out.println("MainFrm:" + userid);
        TabHost tabHost = getTabHost();
        Intent intent1 = new Intent(Mainfrm.this,CarFrm.class);
        Intent intent2 = new Intent(Mainfrm.this,BuyFrm.class);
        Intent intent3 = new Intent(Mainfrm.this,UserFrm.class);
        Bundle bundle1 = new Bundle();
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = new Bundle();
        bundle1.putString("userid",userid);
        bundle2.putString("userid",userid);
        bundle3.putString("userid",userid);
        intent1.putExtras(bundle1);
        intent2.putExtras(bundle2);
        intent3.putExtras(bundle3);
        tabHost.addTab(tabHost.newTabSpec("CarFrm").setIndicator("汽车广场").setContent(intent1));
        tabHost.addTab(tabHost.newTabSpec("BuyFrm").setIndicator("购物车").setContent(intent2));
        tabHost.addTab(tabHost.newTabSpec("UserFrm").setIndicator("我的").setContent(intent3));
    }
}
