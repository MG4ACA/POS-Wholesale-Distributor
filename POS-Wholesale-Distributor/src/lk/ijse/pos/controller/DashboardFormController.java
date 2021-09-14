package lk.ijse.pos.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.model.Batch;
import lk.ijse.pos.model.Customer;
import lk.ijse.pos.utils.CrudUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DashboardFormController {
    public TextField txtSearchCustomer;
    public Label lblRealDate;
    public Label lblRealTime;
    public TextField txtOrderID;
    public ComboBox<String> comboCusID;
    public Label lblUserID;
    public TextField txtQty;
    public TextField txtUnitPrice;
    public ComboBox<String> comboPropertyID;
    public Label lblDiscount;
    public TableView tblPatient1;
    public TableColumn ItemCode;
    public TableColumn Description;
    public TableColumn UnitPrice;
    public TableColumn QTY;
    public TableColumn Option;
    public TableColumn OderID;
    public TableColumn OrderDate;
    public TableColumn Total;
    public TableColumn Customer;
    public Label lblTotalCost;
    private final CustomerController customerController=new CustomerController();
    private final BatchController batchController= new BatchController();
    public Button btnLogOut;
    public TextField txtCustomerName;
    public TextField txtItemDescription;
    public TextField txtQtyOnHand;

    public void initialize(){
        generateRealTime();
        autoGenerateID();

        loadCustomerIDs();
        loadPropertyIDs();


        comboCusID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setCustomerValuesOnAction(newValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        comboPropertyID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (observable.getValue()==null) {
                txtQtyOnHand.setText("");
                txtQty.setText("");
                txtItemDescription.setText("");
                txtUnitPrice.setText("");
                lblDiscount.setText("");
                return;
            }
            try {
                setBatchValuesOnAction(newValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }
    public void placeOrderOnAction(ActionEvent actionEvent) {

    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
    }

    public void btnAddToTableOnAction(ActionEvent actionEvent) {
        comboPropertyID.getSelectionModel().clearSelection();
    }

//    private void clearOnAddToTable() {
//        txtQtyOnHand.clear();
//        txtQty.clear();
//        txtItemDescription.clear();
//        txtUnitPrice.clear();
//        lblDiscount.setText(null);
//        comboPropertyID.setValue("");
//
//
//    }

    private void loadPropertyIDs() {
        try {
            ArrayList<Batch> allBatch = batchController.getAllBatch();
            ObservableList list= FXCollections.observableArrayList();
            for (Batch batch: allBatch
                 ) {
                list.add(batch.getProperty_id());
            }
            comboPropertyID.setItems(list);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadCustomerIDs() {
        try {
            ObservableList<Customer> allCustomers = customerController.getAllCustomers();
            ObservableList list= FXCollections.observableArrayList();
            for (Customer customer:allCustomers
                 ) {
                list.add(customer.getCustomer_id());
            }
            comboCusID.setItems(list);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addNewCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/CustomerForm.fxml"))));
        stage.show();
    }

    public void logOutOnAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"))));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage window = (Stage) btnLogOut.getScene().getWindow();
        window.close();

    }

    public void autoGenerateID() {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select order_id from orders order by order_id desc limit 1");
            boolean next = resultSet.next();
            if (next) {
                String oldID = resultSet.getString(1);

                String substring = oldID.substring(1, 4);

                int intId = Integer.parseInt(substring);

                intId = intId + 1;
                if (intId < 10) {
                    txtOrderID.setText("OI00" + intId);
                } else if (intId < 100) {
                    txtOrderID.setText("OI0" + intId);
                } else if (intId < 1000) {
                    txtOrderID.setText("OI" + intId);
                }
            } else {
                txtOrderID.setText("OI001");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void generateRealTime() {
        lblRealDate.setText(LocalDate.now().toString());
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
            lblRealTime.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void setCustomerValuesOnAction(String id) throws SQLException, ClassNotFoundException {
        Customer customer = customerController.searchCustomer(id);
        txtCustomerName.setText(customer.getCustomer_name());
    }

    public void setBatchValuesOnAction(String id) throws SQLException, ClassNotFoundException {
        Batch batch = batchController.searchBatch(id);
        txtUnitPrice.setText(String.valueOf(batch.getPrice()));
        lblDiscount.setText(String.valueOf(batch.getDiscount()));
        txtItemDescription.setText(batch.getBatch());
        txtQtyOnHand.setText(String.valueOf(batch.getQuantity()));
    }


}
