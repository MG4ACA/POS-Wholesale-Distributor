package lk.ijse.POS.utils;

import lk.ijse.POS.DBConnection.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudUtils {
    /*private static PreparedStatement getPreparedStatement(String sql, Object...params) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        for (int i=0; i< params.length; i++){
            stm.setObject((i+1),params[i]);
        }
        System.out.println("lol");
        return stm;
    }

    public static boolean executeUpdate(String sql, Object...params) throws SQLException, ClassNotFoundException {
        return getPreparedStatement(sql, params).executeUpdate()>0;
    }

    public static ResultSet executeQuery(String sql, Object...params) throws SQLException, ClassNotFoundException {
        return getPreparedStatement(sql, params).executeQuery();
    }
*/
    public static <T>T execute(String sql, Object...params) throws ClassNotFoundException, SQLException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
        for (int i=0; i< params.length; i++){
            stm.setObject((i+1),params[i]);
        }
        if (sql.startsWith("SELECT")){
            return (T) stm.executeQuery();
        }else {
            return (T)(Boolean)(stm.executeUpdate()>0);
        }
    }
}
