package com.example.secondhandcar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Dao.HistoryDao;
import Entity.History;

public class CheckHistory extends AppCompatActivity {

    public String userid;
    public String carid;
    public String date;
    public TextView history;
    public List<String> list = new ArrayList<String>();
    public List<String> dateList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_history);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        userid = bundle.getString("userid");
        System.out.println("CheckHistory:" + userid);
        history = (TextView)findViewById(R.id.history);
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<History> historyList = HistoryDao.queryByUid(userid);
                for (History h : historyList) {
                    carid = h.getCid();
                    date = h.getDate();
                    list.add(carid);
                    dateList.add(date);
                }
            }
        }).start();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        history.setText("汽车编号:" + list.get(0) + dateList.get(0) + "\n" + "汽车编号:" + list.get(1) + dateList.get(1) + "\n" + "汽车编号:" + list.get(2) + dateList.get(2));
    }
}
