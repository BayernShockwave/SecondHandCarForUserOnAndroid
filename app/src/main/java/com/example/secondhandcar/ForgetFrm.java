package com.example.secondhandcar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Dao.FindPwdDao;
import Entity.FindPwd;

public class ForgetFrm extends AppCompatActivity {

    public EditText userid;
    public EditText userphone;
    public EditText useremail;
    public Button confirm;
    public Button clean;
    public String id;
    public String phone;
    public String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_frm);
        userid = (EditText)findViewById(R.id.uid);
        userphone = (EditText)findViewById(R.id.uphone);
        useremail = (EditText)findViewById(R.id.uemail);
        confirm = (Button)findViewById(R.id.confirm);
        clean = (Button)findViewById(R.id.clean);
        confirm.setOnClickListener(confirmButtonListener);
        clean.setOnClickListener(cleanButtonListener);
    }

    Button.OnClickListener confirmButtonListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            id = userid.getText().toString();
            phone = userphone.getText().toString();
            email = useremail.getText().toString();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int i = FindPwdDao.addRequest(id,phone,email);
                }
            }).start();
            Toast.makeText(ForgetFrm.this,"已发送请求!您将在24小时内得到回复!",Toast.LENGTH_SHORT).show();
        }
    };

    Button.OnClickListener cleanButtonListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            userid.setText("");
            userphone.setText("");
            useremail.setText("");
        }
    };
}
