package Dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DButil.SqlHelper;
import Entity.Shopcar;

public class ShopcarDao {

    public static List<Shopcar> allShopCars() {
        List<Shopcar> shopcarList = new ArrayList<Shopcar>();
        String sql = "select * from shopcar";
        ResultSet resultSet = SqlHelper.executeQuery(sql);
        try {
            while (resultSet.next()) {
                Shopcar sc = new Shopcar();
                sc.setUid(resultSet.getString(1));
                sc.setCid(resultSet.getString(2));
                sc.setCount(resultSet.getString(3));
                shopcarList.add(sc);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return shopcarList;
    }

    public static int addShopcar(String uid,String cid) {
        String sql = "insert into shopcar values('"+uid+"','"+cid+"','1')";
        return SqlHelper.executeUpdate(sql);
    }
}
