package DButil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SqlHelper {

    private static String driver = "com.mysql.jdbc.Driver";
    private static String user = "root";
    private static String password = "root";

    private static Connection getConn(String dbName) {
        Connection connection = null;
        try {
            Class.forName(driver);
            String ip = "10.0.2.2";
            connection = DriverManager.getConnection("jdbc:mysql://" + ip + ":3306/" + dbName, user, password);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static ResultSet executeQuery(String sql) {
        ResultSet rs = null;
        try {
            Connection connection = getConn("SecondHandCar");
            if (connection != null) {
                PreparedStatement ps = connection.prepareStatement(sql);
                if (ps != null) {
                    rs = ps.executeQuery(sql);
                    if (rs != null) {
                        try {
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        return null;
                    }
                }
                else {
                    return null;
                }
            }
            else {
                return null;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static int executeUpdate(String sql) {
        int r = 0;
        try {
            Connection connection = getConn("SecondHandCar");
            if (connection != null) {
                PreparedStatement ps = connection.prepareStatement(sql);
                if (ps != null) {
                    r = ps.executeUpdate(sql);
                    if (r != 0) {
                        try {
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                        connection.close();
                        ps.close();
                    }
                    else {
                        return 0;
                    }
                }
                else {
                    return 0;
                }
            }
            else {
                return 0;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }
}