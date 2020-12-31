package com.example.secondhandcar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserFrm extends AppCompatActivity {

    public String userid;
    public Button editpersonalinfo;
    public Button checkhistoryorder;
    public Button outsystem;
    private final int SUBACTIVITY1 = 1 , SUBACTIVITY2 = 2 , SUBACTIVITY3 = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_frm);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        userid = bundle.getString("userid");
        System.out.println("UserFrm:" + userid);
        editpersonalinfo = (Button)findViewById(R.id.editpersonalinfo);
        checkhistoryorder = (Button)findViewById(R.id.checkhistoryorder);
        outsystem = (Button)findViewById(R.id.outsystem);
        editpersonalinfo.setOnClickListener(editButtonListener);
        checkhistoryorder.setOnClickListener(checkButtonListener);
        outsystem.setOnClickListener(outButtonListener);
    }

    Button.OnClickListener editButtonListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent newintent = new Intent(UserFrm.this,EditInfoFrm.class);
            Bundle newbundle = new Bundle();
            newbundle.putString("userid",userid);
            newintent.putExtras(newbundle);
            startActivityForResult(newintent,SUBACTIVITY1);
        }
    };

    Button.OnClickListener checkButtonListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent newintent = new Intent(UserFrm.this,CheckHistory.class);
            Bundle newbundle = new Bundle();
            newbundle.putString("userid",userid);
            newintent.putExtras(newbundle);
            startActivityForResult(newintent,SUBACTIVITY2);
        }
    };

    Button.OnClickListener outButtonListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(UserFrm.this);
            builder.setTitle("退出结果反馈");
            builder.setMessage("是否确认退出?");
            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(UserFrm.this,MainActivity.class);
                    startActivityForResult(intent,SUBACTIVITY3);
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.setNeutralButton("忽略", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.show();
        }
    };
}
