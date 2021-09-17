package lk.ijse.POS.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.POS.model.Customer;

import java.sql.SQLException;

public class CustomerSaveFormController {
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtSalary;

    public void saveCustomerOnAction(ActionEvent actionEvent) {
        Customer customer = new Customer(
                txtId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                Double.parseDouble(txtSalary.getText())
        );

        try {
            if (new CustomerController().saveCustomer(customer)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer "+txtName.getText()+" Saved").show();
            }else{
                new Alert(Alert.AlertType.ERROR,"Try Again").show();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
