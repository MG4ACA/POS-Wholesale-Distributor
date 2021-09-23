package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.CustomerBO;
import lk.ijse.pos.dto.CustomerDTO;

import java.sql.SQLException;

public class CustomerFormController {

    public TextField txtID;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtCity;
    public TextField txtProvince;
    public TextField txtContact;
    public Button btnClose;
    private CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBo(BOFactory.getType.CUSTOMER);


    public void updateCustomerOnAction(ActionEvent actionEvent) {
        try {

            boolean b = customerBO.updateCustomer(new CustomerDTO(txtID.getText(), txtName.getText(), txtAddress.getText(), txtCity.getText(), txtProvince.getText(), Integer.parseInt(txtContact.getText())));
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
        }catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void deleteCustomerOnAction(ActionEvent actionEvent) {
        try {
            Boolean b = customerBO.deleteCustomer(txtID.getText());
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
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addCustomerOnAction(ActionEvent actionEvent) {
        try {
            boolean b = customerBO.addCustomer(new CustomerDTO(txtID.getText(), txtName.getText(), txtAddress.getText(), txtCity.getText(), txtProvince.getText(), Integer.parseInt(txtContact.getText())));
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
        }catch (Exception e) {
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
            CustomerDTO customerDTO = customerBO.searchCustomer(txtID.getText());
            System.out.println(customerDTO != null);
            if (customerDTO != null) {
                txtName.setText(customerDTO.getCustomer_name());
                txtAddress.setText(customerDTO.getAddress());
                txtCity.setText(customerDTO.getCity());
                txtProvince.setText(customerDTO.getProvince());
                txtContact.setText(String.valueOf(customerDTO.getContact()));
            } else {
                new Alert(Alert.AlertType.WARNING, "Customer Not Found.!!").show();
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public void loadDashBordBackOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage)btnClose.getScene().getWindow();
        stage.close();

    }
}
