package com.example.secondhandcar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Dao.CarDao;
import Dao.HistoryDao;
import Dao.ShopcarDao;
import Entity.Car;
import Entity.Shopcar;

public class BuyFrm extends AppCompatActivity {

    public String userid;
    public TextView car1;
    public TextView car2;
    public TextView car3;
    public Button buy;
    public List<Car> carList;
    public List<String> shopcarList = new ArrayList<String>();
    public String name1,name2,name3;
    public String count1,count2,count3;
    public String newcount1,newcount2,newcount3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_frm);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        userid = bundle.getString("userid");
        System.out.println("BuyFrm:" + userid);
        car1 = (TextView)findViewById(R.id.carid1);
        car2 = (TextView)findViewById(R.id.carid2);
        car3 = (TextView)findViewById(R.id.carid3);
        buy = (Button)findViewById(R.id.buycar);
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Shopcar> allList = ShopcarDao.allShopCars();
                for (Shopcar sc : allList) {
                    if (userid.equals(sc.getUid())) {
                        shopcarList.add(sc.getCid());
                    }
                }
            }
        }).start();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0 ; i < shopcarList.size() ; i++) {
                    List<Car> name1List = CarDao.queryById(shopcarList.get(0));
                    for (Car car : name1List) {
                        name1 = car.getCname();
                        count1 = car.getCcount();
                    }
                    List<Car> name2List = CarDao.queryById(shopcarList.get(1));
                    for (Car car : name2List) {
                        name2 = car.getCname();
                        count2 = car.getCcount();
                    }
                    List<Car> name3List = CarDao.queryById(shopcarList.get(2));
                    for (Car car : name3List) {
                        name3 = car.getCname();
                        count3 = car.getCcount();
                    }
                }
            }
        }).start();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        car1.setText("编号:" + shopcarList.get(0) + " 名称:" + name1 + " 数量:1");
        car2.setText("编号:" + shopcarList.get(1) + " 名称:" + name2 + " 数量:1");
        car3.setText("编号:" + shopcarList.get(2) + " 名称:" + name3 + " 数量:1");
        buy.setOnClickListener(buyButtonListener);
    }

    Button.OnClickListener buyButtonListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    newcount1 = String.valueOf(Integer.parseInt(count1)-1);
                    newcount2 = String.valueOf(Integer.parseInt(count2)-1);
                    newcount3 = String.valueOf(Integer.parseInt(count3)-1);
                    System.out.println(newcount1);
                    System.out.println(newcount2);
                    System.out.println(newcount3);
                    int a = CarDao.buyCar(shopcarList.get(0),newcount1);
                    int b = CarDao.buyCar(shopcarList.get(1),newcount2);
                    int c = CarDao.buyCar(shopcarList.get(2),newcount3);
                    Date date = new Date();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                        for (int i = 0 ; i < shopcarList.size() ; i++) {
                        int j = HistoryDao.addHistory(userid,shopcarList.get(i),simpleDateFormat.format(date));
                    }
                }
            }).start();
            Toast.makeText(BuyFrm.this,"购买成功!",Toast.LENGTH_SHORT).show();
        }
    };
}
