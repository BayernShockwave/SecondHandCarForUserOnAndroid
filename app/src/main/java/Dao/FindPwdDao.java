package Dao;

import DButil.SqlHelper;

public class FindPwdDao {

    public static int addRequest(String id,String phone,String email) {
        String sql = "insert into findpwd values('"+id+"','"+phone+"','"+email+"')";
        return SqlHelper.executeUpdate(sql);
    }
}
