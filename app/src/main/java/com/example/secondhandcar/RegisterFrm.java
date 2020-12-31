package com.example.secondhandcar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Dao.UserDao;

public class RegisterFrm extends AppCompatActivity {

    public EditText uid;
    public EditText uname;
    public EditText upwd;
    public Button confirm;
    public Button clean;
    public Button uidClear;
    public Button unameClear;
    public Button upwdClear;
    public Button pwdsee;
    public TextWatcher uidWachter;
    public TextWatcher unameWachter;
    public TextWatcher upwdWachter;
    public String newid;
    public String newname;
    public String newpwd;
    private final int SUBACTIVITY1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_frm);
        uid = (EditText)findViewById(R.id.uid);
        uname = (EditText)findViewById(R.id.uname);
        upwd = (EditText)findViewById(R.id.upwd);
        uidClear = (Button)findViewById(R.id.uidclear);
        unameClear = (Button)findViewById(R.id.unameclear);
        upwdClear = (Button)findViewById(R.id.upwdclear);
        pwdsee = (Button)findViewById(R.id.pwdsee);
        confirm = (Button)findViewById(R.id.confirm);
        clean = (Button)findViewById(R.id.clean);
        uidClear.setOnClickListener(uidClearButtonListener);
        unameClear.setOnClickListener(unameClearButtonListener);
        upwdClear.setOnClickListener(upwdClearButtonListener);
        pwdsee.setOnTouchListener(pwdSeeButtonListener);
        confirm.setOnClickListener(confirmButtonListener);
        clean.setOnClickListener(cleanButtonListener);
    }

    private void initWatcher() {
        uidWachter = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    uidClear.setVisibility(View.VISIBLE);
                }
                else {
                    uidClear.setVisibility(View.INVISIBLE);
                }
            }
        };

        unameWachter = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    unameClear.setVisibility(View.VISIBLE);
                }
                else {
                    unameClear.setVisibility(View.INVISIBLE);
                }
            }
        };

        upwdWachter = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    upwdClear.setVisibility(View.VISIBLE);
                }
                else {
                    upwdClear.setVisibility(View.INVISIBLE);
                }
            }
        };
    }

    Button.OnClickListener uidClearButtonListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            uid.setText("");
            uname.setText("");
            upwd.setText("");
            initWatcher();
            uid.addTextChangedListener(uidWachter);
        }
    };

    Button.OnClickListener unameClearButtonListener  = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            uname.setText("");
            initWatcher();
            uname.addTextChangedListener(unameWachter);
        }
    };

    Button.OnClickListener upwdClearButtonListener  = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            upwd.setText("");
            initWatcher();
            upwd.addTextChangedListener(upwdWachter);
        }
    };

    Button.OnTouchListener pwdSeeButtonListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (v.getId() == R.id.pwdsee) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    upwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    upwd.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
            return false;
        }
    };

    Button.OnClickListener confirmButtonListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            newid = uid.getText().toString();
            newname = uname.getText().toString();
            newpwd = upwd.getText().toString();
            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterFrm.this);
            builder.setTitle("注册结果反馈");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int i = UserDao.addUser(newid,newname,newpwd);
                }
            }).start();
            builder.setMessage("注册成功!欢迎" + newname + "!");
            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(RegisterFrm.this,MainActivity.class);
                    startActivityForResult(intent,SUBACTIVITY1);
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(RegisterFrm.this,MainActivity.class);
                    startActivityForResult(intent,SUBACTIVITY1);
                }
            });
            builder.setNeutralButton("忽略", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(RegisterFrm.this,MainActivity.class);
                    startActivityForResult(intent,SUBACTIVITY1);
                }
            });
            builder.show();
        }
    };

    Button.OnClickListener cleanButtonListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            uid.setText("");
            uname.setText("");
            upwd.setText("");
        }
    };
}
