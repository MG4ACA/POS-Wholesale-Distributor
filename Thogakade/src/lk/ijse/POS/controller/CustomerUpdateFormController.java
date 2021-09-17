package lk.ijse.POS.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.POS.model.Customer;

import java.sql.SQLException;

public class CustomerUpdateFormController {
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtSalary;

    public void searchCustomerOnAction(ActionEvent actionEvent) {
        try {
            Customer customer = new CustomerController().searchCustomer(txtId.getText());
            if (customer!=null){
                txtName.setText(customer.getName());
                txtAddress.setText(customer.getAddress());
                txtSalary.setText(String.valueOf(customer.getSalary()));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void updateCustomerOnAction(ActionEvent actionEvent) {
        Customer customer = new Customer(
                txtId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                Double.parseDouble(txtSalary.getText())
        );

        try {
            if (new CustomerController().updateCustomer(customer)){
                new Alert(Alert.AlertType.CONFIRMATION,"Customer Saved").show();
            }else {
                new Alert(Alert.AlertType.WARNING,"ERROR").show();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
