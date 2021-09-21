package lk.ijse.pos.dao;

import lk.ijse.pos.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudUtils {
//    public static <T>T execute(String sql, Object... object) throws SQLException, ClassNotFoundException {
////        for (int i = 0; i < object.length; i++) {
////            System.out.println(object[i]);
////        }
////        Connection connection = DBConnection.getInstance().getConnection();
////        System.out.println("asdsdasd");
////        PreparedStatement stm = connection.prepareStatement(sql);
////        for (int i = 0; i < object.length; i++) {
////            System.out.println("lol");
////            stm.setObject(i+1, object[i]);
////        }
////        if (sql.startsWith("SELECT")) {
////            return (T) stm.executeQuery();
////        }else {
////            return (T)(Boolean)(stm.executeUpdate()>0);
////        }
//
//    }

    public static <T>T execute(String sql, Object...params) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement( sql);
        for (int i = 0; i < params.length; i++) {
            stm.setObject((i + 1), params[i]);
        }
        if (sql.startsWith("SELECT")){
            return (T)stm.executeQuery();
        }
        return (T)(Boolean)(stm.executeUpdate()>0);
    }
}
