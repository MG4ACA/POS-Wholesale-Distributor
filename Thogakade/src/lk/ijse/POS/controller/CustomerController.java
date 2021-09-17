package lk.ijse.POS.controller;

import lk.ijse.POS.DBConnection.DBConnection;
import lk.ijse.POS.model.Customer;
import lk.ijse.POS.utils.CrudUtils;

import java.sql.*;
import java.util.ArrayList;


public class CustomerController {

    public boolean saveCustomer(Customer customer) throws ClassNotFoundException, SQLException {
/*      Without USing Singleton Class
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade","root","mysql");*/

//        Connection connection = DBConnection.getInstance().getConnection();
/*      Basic method of SQL
        String sql="INSERT INTO customer VALUES ('"+customer.getId()+"','"+customer.getName()+"','"+customer.getAddress()+"','"+customer.getSalary()+"')"; */

        String sql="INSERT INTO customer VALUES(?,?,?,?)";

/*      Without using Crud Utils
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setObject(1,customer.getId());
        stm.setObject(2,customer.getName());
        stm.setObject(3,customer.getAddress());
        stm.setObject(4,customer.getSalary());
        return stm.executeUpdate()>0;
*/
        return CrudUtils.execute(sql,customer.getId(),customer.getName(),customer.getAddress(),customer.getSalary());


    }
    public Customer searchCustomer(String id) throws ClassNotFoundException, SQLException {
        ResultSet rst = CrudUtils.execute("SELECT * FROM Customer WHERE id=?", id);
        if (rst.next()){
            return new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4)
            );
        }
        return null;
    }
    public boolean updateCustomer(Customer customer) throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.jdbc.Driver");
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "mysql");
//        PreparedStatement stm = connection.prepareStatement("UPDATE Customer SET name=?,address=?,salary=? WHERE id=?");
//        stm.setObject(1,customer.getName());
//        stm.setObject(2,customer.getAddress());
//        stm.setObject(3,customer.getSalary());
//        stm.setObject(4,customer.getId());
//        return stm.executeUpdate()>0;
        return CrudUtils.execute("UPDATE Customer SET name=?,address=?,salary=? WHERE id=?",customer.getName(),customer.getAddress(),customer.getSalary(),customer.getId());
    }

    public boolean deleteCustomer(String id) throws ClassNotFoundException, SQLException {
        return CrudUtils.execute("DELETE FROM Customer WHERE id=?",id);
    }

    public ArrayList<Customer> getAllCustomers() throws ClassNotFoundException, SQLException {
        ResultSet rst = CrudUtils.execute("SELECT * FROM Customer");
        ArrayList<Customer> cusList = new ArrayList();
        while (rst.next()){
            cusList.add(new Customer(
                    rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble("salary")));
        }
        return cusList;
    }
}
