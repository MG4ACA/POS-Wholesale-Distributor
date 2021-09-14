package lk.ijse.pos.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.pos.model.Customer;
import lk.ijse.pos.utils.CrudUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerController {
    public CustomerController() {
    }

    public boolean saveCustomer(Customer customer) throws SQLException, ClassNotFoundException {
       return CrudUtils.execute("INSERT INTO customer VALUES(?,?,?,?,?,?)", customer.getCustomer_id(), customer.getCustomer_name(), customer.getAddress(), customer.getCity(), customer.getProvince(), customer.getContact());
    }

    public boolean updateCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        return CrudUtils.execute("UPDATE customer SET customer_name=?, address=?, city=?, province=?, contact=? WHERE customer_id=?", customer.getCustomer_name(), customer.getAddress(), customer.getCity(), customer.getProvince(), customer.getContact(), customer.getCustomer_id());
    }

    public Customer searchCustomer(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtils.execute("SELECT * FROM customer WHERE customer_id=?", id);
        while (rst.next()){
            return new Customer(rst.getString(1), rst.getString(2),  rst.getString(3),  rst.getString(4),  rst.getString(5),  rst.getInt(6))  ;
        }
        return null;
    }
    public Boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return CrudUtils.execute("DELETE FROM customer WHERE customer_id=?", id);

    }
    public ObservableList<Customer> getAllCustomers( ) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtils.execute("SELECT * FROM customer");
        ObservableList<Customer> list= FXCollections.observableArrayList();
        if (rst.next()) {
            list.add(new Customer(rst.getString(1), rst.getString(2),  rst.getString(3),  rst.getString(4),  rst.getString(5),  rst.getInt(6)));
        }
        return  list;

    }

    public ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> list= new ArrayList<>();
        ResultSet rst= CrudUtils.execute("SELECT customer_id FROM customer");
        while (rst.next()) {
            list.add(rst.getString(1));
        }
        return list;
    }

}
