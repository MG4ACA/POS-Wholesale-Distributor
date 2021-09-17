package lk.ijse.POS.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.POS.model.Customer;

import java.sql.SQLException;

public class CustomerDeleteFormController {

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
            }else {
                new Alert(Alert.AlertType.WARNING,"Empty Result Set").show();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteCustomerOnAction(ActionEvent actionEvent) {
        try {
            if (new CustomerController().deleteCustomer(txtId.getText())){
                new Alert(Alert.AlertType.CONFIRMATION,"Customer Deleted").show();
                clearFields();
            }else {
                new Alert(Alert.AlertType.WARNING,"ERROR").show();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void clearFields(){
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtSalary.clear();
    }
}
