package com.example.secondhandcar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.ArrayList;

import Dao.CarDao;
import Dao.ShopcarDao;
import Entity.Car;
import Entity.Shopcar;

public class CarFrm extends AppCompatActivity {

    public String userid;
    public String buyid;
    public String cid;
    public String cname;
    public String cprice;
    public String ccount;
    public String cstatus;
    List<String > showList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_frm);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        userid = bundle.getString("userid");
        System.out.println("CarFrm:" + userid);
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Car> carList = CarDao.allCars();
                for (Car car : carList) {
                    cid = car.getCid();
                    cname = car.getCname();
                    cprice = car.getCprice();
                    ccount = car.getCcount();
                    cstatus = car.getCstatus();
                    showList.add("汽车编号:" + cid + "   " + "汽车名称:" + cname + "   " + "汽车价格:" + cprice + "   " + "剩余库存:" + ccount + "   " + "磨损情况:" + cstatus);
                }
            }
        }).start();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        final TextView textView = (TextView)findViewById(R.id.tv);
        ListView listView = (ListView)findViewById(R.id.lv);
        List<String> list = new ArrayList<String>();
        for (int i = 0 ; i < showList.size() ; i++) {
            System.out.println(showList.get(i));
            list.add(showList.get(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
        AdapterView.OnItemClickListener listViewListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String msg = "已添加至购物车!";
                textView.setText(msg);
                buyid = "00" + String.valueOf(id+1);
                System.out.println(buyid);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int i = ShopcarDao.addShopcar(userid,buyid);
                    }
                }).start();
            }
        };
        listView.setOnItemClickListener(listViewListener);
    }
}
