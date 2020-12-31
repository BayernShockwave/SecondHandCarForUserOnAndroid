package Dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DButil.SqlHelper;
import Entity.Car;

public class CarDao {

    public static List<Car> allCars() {
        List<Car> carList = new ArrayList<Car>();
        String sql = "select * from car";
        ResultSet resultSet = SqlHelper.executeQuery(sql);
        try {
            while (resultSet.next()) {
                Car car = new Car();
                car.setCid(resultSet.getString(1));
                car.setCname(resultSet.getString(2));
                car.setCprice(resultSet.getString(3));
                car.setCcount(resultSet.getString(4));
                car.setCstatus(resultSet.getString(5));
                carList.add(car);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return carList;
    }

    public static int buyCar(String id,String count) {
        String sql = "update car set ccount='"+count+"' where cid='"+id+"'";
        return SqlHelper.executeUpdate(sql);
    }

    public static List<Car> queryById(String id) {
        List<Car> carList = new ArrayList<Car>();
        String sql = "select * from car where cid='"+id+"'";
        ResultSet resultSet = SqlHelper.executeQuery(sql);
        try {
            while (resultSet.next()) {
                Car car = new Car();
                car.setCid(resultSet.getString(1));
                car.setCname(resultSet.getString(2));
                car.setCprice(resultSet.getString(3));
                car.setCcount(resultSet.getString(4));
                car.setCstatus(resultSet.getString(5));
                carList.add(car);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return carList;
    }
}
