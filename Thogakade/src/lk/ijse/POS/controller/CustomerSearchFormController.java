package lk.ijse.POS.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.POS.model.Customer;

import java.sql.SQLException;

public class CustomerSearchFormController {
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtSalary;

    public void searchCustomerOnAction(ActionEvent actionEvent) {
        try {
            Customer customer=new CustomerController().searchCustomer(txtId.getText());

            if (customer!=null){
                txtName.setText(customer.getName());
                txtAddress.setText(customer.getAddress());
                txtSalary.setText(String.valueOf(customer.getSalary()));
            }else {
                new Alert(Alert.AlertType.WARNING,"Empty Result").show();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
