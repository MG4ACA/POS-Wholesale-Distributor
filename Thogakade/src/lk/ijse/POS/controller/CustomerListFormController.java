package lk.ijse.POS.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.POS.model.Customer;
import lk.ijse.POS.view.tm.CustomerTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerListFormController {
    public TableView<CustomerTM> tblCustomer;
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colSalary;


    public void initialize() throws SQLException, ClassNotFoundException {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        loadAllCustomers();
    }

    private void loadAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> allCustomers = new CustomerController().getAllCustomers();
        ObservableList<CustomerTM> list= FXCollections.observableArrayList();
        for (Customer temp: allCustomers
             ) {
            list.add(new CustomerTM(temp.getId(),temp.getName(), temp.getAddress(), temp.getSalary()));
        }

        tblCustomer.setItems(list);

    }
}