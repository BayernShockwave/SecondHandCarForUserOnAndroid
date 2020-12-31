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
import android.widget.TextView;

import java.util.List;

import Dao.UserDao;
import Entity.User;

public class EditInfoFrm extends AppCompatActivity {

    public String userid;
    public String oldname;
    public String oldpwd;
    public String newname;
    public String newpwd;
    public TextView uid;
    public EditText uname;
    public EditText upwd;
    public Button confirm;
    public Button clean;
    public Button unameClear;
    public Button upwdClear;
    public Button upwdsee;
    public TextWatcher unameWachter;
    public TextWatcher upwdWachter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info_frm);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        userid = bundle.getString("userid");
        System.out.println("EditInfoFrm:" + userid);
        uid = (TextView)findViewById(R.id.userid);
        uname = (EditText)findViewById(R.id.username);
        upwd = (EditText)findViewById(R.id.userpwd);
        unameClear = (Button)findViewById(R.id.unameclear);
        upwdClear = (Button)findViewById(R.id.upwdclear);
        upwdsee = (Button)findViewById(R.id.upwdsee);
        confirm = (Button)findViewById(R.id.confirm);
        clean = (Button)findViewById(R.id.clean);
        unameClear.setOnClickListener(unameClearButtonListener);
        upwdClear.setOnClickListener(upwdClearButtonListener);
        upwdsee.setOnTouchListener(upwdSeeButtonListener);
        confirm.setOnClickListener(confirmButtonListener);
        clean.setOnClickListener(cleanButtonListener);
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<User> userList = UserDao.allUsers();
                for (User user : userList) {
                    if (userid.equals(user.getUid())) {
                        oldname = user.getUname();
                        oldpwd = user.getUpwd();
                    }
                }
            }
        }).start();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("oldname:" + oldname);
        System.out.println("oldpwd:" + oldpwd);
        uid.setText(userid);
        uname.setText(oldname);
        upwd.setText(oldpwd);

        uname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                newname = uname.getText().toString();
                //System.out.println("newname:" + newname);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        upwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                newpwd = upwd.getText().toString();
                //System.out.println("newpwd:" + newpwd);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void initWatcher() {
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

    Button.OnTouchListener upwdSeeButtonListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (v.getId() == R.id.upwdsee) {
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
            AlertDialog.Builder builder = new AlertDialog.Builder(EditInfoFrm.this);
            builder.setTitle("更新结果反馈");
            builder.setMessage("是否确认修改?");
            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    System.out.println(userid);
                    System.out.println(newname);
                    System.out.println(newpwd);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            int i = UserDao.updateUser(userid,newname,newpwd);
                        }
                    }).start();
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

    Button.OnClickListener cleanButtonListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            uname.setText("");
            upwd.setText("");
        }
    };
}
