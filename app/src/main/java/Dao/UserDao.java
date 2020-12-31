package Dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DButil.SqlHelper;
import Entity.User;

public class UserDao {

    public static List<User> allUsers() {
        List<User> userList = new ArrayList<User>();
        String sql = "select * from user";
        ResultSet resultSet = SqlHelper.executeQuery(sql);
        try {
            while (resultSet.next()) {
                User user = new User();
                user.setUid(resultSet.getString(1));
                user.setUname(resultSet.getString(2));
                user.setUpwd(resultSet.getString(3));
                userList.add(user);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    public static int addDiary(String id,String ip,String time) {
        String sql = "insert into diary values('"+id+"','"+ip+"','"+time+"')";
        return SqlHelper.executeUpdate(sql);
    }

    public static int addUser(String id,String name,String pwd) {
        String sql = "insert into user values('"+id+"','"+name+"','"+pwd+"')";
        return SqlHelper.executeUpdate(sql);
    }

    public static int updateUser(String id,String name,String pwd) {
        String sql = "update user set uname='"+name+"',upwd='"+pwd+"' where uid='"+id+"'";
        return SqlHelper.executeUpdate(sql);
    }
}
