package lk.ijse.pos.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DashboardFormController {
    public TextField txtSearchCustomer;
    public Label lblRealDate;
    public Label lblRealTime;
    public TextField txtOrderID;
    public ComboBox comboCusID;
    public Label lblUserID;
    public TextField txtQty;
    public TextField txtUnitPrice;
    public ComboBox comboPropertyID;
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

    public void initialize(){
        generateRealTime();
        autoGenerateID();
        loadCustomerIDs();
        loadPropertyIDs();
    }

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

    public void searchCustomerOnAction(ActionEvent actionEvent) {
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

    public void placeOrderOnAction(ActionEvent actionEvent) {
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
    }

    public void btnlogOutOnAction(ActionEvent actionEvent) {
    }
}
