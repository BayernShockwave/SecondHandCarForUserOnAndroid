package Dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DButil.SqlHelper;
import Entity.History;

public class HistoryDao {

    public static List<History> allHistories() {
        List<History> historyList = new ArrayList<History>();
        String sql = "select * from history";
        ResultSet resultSet = SqlHelper.executeQuery(sql);
        try {
            while (resultSet.next()) {
                History history = new History();
                history.setUid(resultSet.getString(1));
                history.setCid(resultSet.getString(2));
                history.setDate(resultSet.getString(3));
                historyList.add(history);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return historyList;
    }

    public static int addHistory(String uid,String cid,String date) {
        String sql = "insert into history values('"+uid+"','"+cid+"','"+date+"')";
        return SqlHelper.executeUpdate(sql);
    }

    public static List<History> queryByUid(String id) {
        List<History> historyList = new ArrayList<History>();
        String sql = "select * from history where uid='"+id+"'";
        ResultSet resultSet = SqlHelper.executeQuery(sql);
        try {
            while (resultSet.next()) {
                History history = new History();
                history.setUid(resultSet.getString(1));
                history.setCid(resultSet.getString(2));
                history.setDate(resultSet.getString(3));
                historyList.add(history);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return historyList;
    }
}
