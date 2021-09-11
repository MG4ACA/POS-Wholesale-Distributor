package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.model.Customer;
import lk.ijse.pos.utils.CrudUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerFormController {

    public TextField txtID;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtCity;
    public TextField txtProvince;
    public TextField txtContact;
    private final CustomerController customerController = new CustomerController();


    public void updateCustomerOnAction(ActionEvent actionEvent) {

        try {
            boolean b = customerController.updateCustomer(new Customer(txtID.getText(), txtName.getText(), txtAddress.getText(), txtCity.getText(), txtProvince.getText(), Integer.parseInt(txtContact.getText())));
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Updated Successfully.!!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.WARNING, "Customer Not Updated.!!").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void deleteCustomerOnAction(ActionEvent actionEvent) {
        try {
            Boolean b = customerController.deleteCustomer(txtID.getText());
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Deleted Successfully.!!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Customer Not Deleted.!!").show();
            }
            clearFields();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void addCustomerOnAction(ActionEvent actionEvent) {
        try {
            boolean b = customerController.saveCustomer(new Customer(txtID.getText(), txtName.getText(), txtAddress.getText(), txtCity.getText(), txtProvince.getText(), Integer.parseInt(txtContact.getText())));
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Saved Successfully.!!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.WARNING, "Customer Not Saved.!!").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void clearFields() {
        txtID.clear();
        txtName.clear();
        txtAddress.clear();
        txtCity.clear();
        txtProvince.clear();
        txtContact.clear();
    }

    public void searchCustomerOnAction(ActionEvent actionEvent) {
        try {
            Customer customer = customerController.searchCustomer(txtID.getText());
            if (customer != null) {
                txtName.setText(customer.getCustomer_name());
                txtAddress.setText(customer.getAddress());
                txtCity.setText(customer.getCity());
                txtProvince.setText(customer.getProvince());
                txtContact.setText(String.valueOf(customer.getContact()));
            } else {
                new Alert(Alert.AlertType.WARNING, "Customer Not Found.!!").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void getAllCustomers() {
        try {
            ArrayList<Customer> allCustomers = customerController.getAllCustomers();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
