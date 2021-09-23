package lk.ijse.pos.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.BatchBO;
import lk.ijse.pos.bo.custom.CustomerBO;
import lk.ijse.pos.bo.custom.PlaceOrderBO;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.dto.BatchDTO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.OrderDetailsDTO;
import lk.ijse.pos.dto.OrdersDTO;
import lk.ijse.pos.view.tm.CartTM;
import lk.ijse.pos.view.tm.OrdersTM;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class DashboardFormController {
    public Label lblRealDate;
    public Label lblRealTime;
    public TextField txtOrderID;
    public ComboBox<String> comboCusID;
    public Label lblUserID;
    public TextField txtQty;
    public TextField txtUnitPrice;
    public ComboBox<String> comboPropertyID;
    public Label lblDiscount;
    public TableColumn OderID;
    public TableColumn OrderDate;
    public TableColumn Total;
    public Label lblTotalCost;
    public Button btnLogOut;
    public TextField txtCustomerName;
    public TextField txtItemDescription;
    public TextField txtQtyOnHand;
    public TableView tblItemDetails;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQTY;
    public TableColumn colTotal;
    public TableColumn colOption;
    public TableView tblOrders;
    public Label lblValidateQuantity;
    public TableColumn colOrderId;
    public TableColumn colOrderDate;
    public TableColumn colOTTotal;
    private ObservableList<CartTM> list = FXCollections.observableArrayList();
    private final CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBo(BOFactory.getType.CUSTOMER);
    private final BatchBO batchBO = (BatchBO) BOFactory.getInstance().getBo(BOFactory.getType.BATCH);
    private final PlaceOrderBO placeOrderBO = (PlaceOrderBO) BOFactory.getInstance().getBo(BOFactory.getType.ORDERS);



    public void initialize() {
        lblUserID.setText(LoginFormController.userId);
        System.out.println(LoginFormController.userId);
        generateRealTime();
        autoGenerateID();
        loadCustomerIDs();
        loadPropertyIDs();



        comboCusID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (observable.getValue()==null){
                txtCustomerName.setText("");
                return;
            }
            try {
                setCustomerValuesOnAction(newValue);
                loadOrdersTable(newValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        comboPropertyID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (observable.getValue() == null) {
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

        tblItemDetails.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CartTM>() {
            @Override
            public void changed(ObservableValue<? extends CartTM> observable, CartTM oldValue, CartTM newValue) {
                CartTM value = observable.getValue();
                if (value != null) {
                    comboPropertyID.getSelectionModel().select(value.getCode());
                    txtQty.setText(value.getQuantity() + "");
                } else {
                    System.out.println("null");
                }
            }
        });


        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));

        colOrderId.setCellValueFactory(new PropertyValueFactory<>("order_id"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("order_date"));
        colOTTotal.setCellValueFactory(new PropertyValueFactory<>("total_cost"));

    }

    private void loadOrdersTable(String cusId) {
        try {
            ArrayList<OrdersDTO> list = placeOrderBO.loadCustomerOrders(cusId);
            ObservableList<OrdersTM> oblist=FXCollections.observableArrayList();

            for (OrdersDTO ordersDTO : list
                 ) {
                oblist.add(new OrdersTM(ordersDTO.getOrder_id(), ordersDTO.getOrder_date(), ordersDTO.getTotal_cost(), ordersDTO.getCustomer_id(), ordersDTO.getUser_id()));
            }
            tblOrders.setItems(oblist);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void placeOrderOnAction(ActionEvent actionEvent) throws SQLException {
        ArrayList<OrderDetailsDTO> orderDetailDTOS = new ArrayList<>();
        for (CartTM tm : list
        ) {
            orderDetailDTOS.add(new OrderDetailsDTO(tm.getQuantity(), new BigDecimal(tm.getUnitPrice()), txtOrderID.getText(), tm.getCode()));
        }

        OrdersDTO ordersDTO = new OrdersDTO(txtOrderID.getText(),
                Date.valueOf(lblRealDate.getText()),
                BigDecimal.valueOf(Double.parseDouble(lblTotalCost.getText())),
                comboCusID.getValue(),
                lblUserID.getText(),
                orderDetailDTOS
        );

        boolean b = false;
        try {
            b = placeOrderBO.addOrder(ordersDTO);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (b) {
            new Alert(Alert.AlertType.CONFIRMATION, "Order Saved..!!").show();
            refreshPane();

        } else {
            new Alert(Alert.AlertType.WARNING, "Order Does not Saved..!!").show();
        }
    }

    private void refreshPane() {
        comboCusID.getSelectionModel().clearSelection();
        loadOrdersTable(comboCusID.getValue());
        tblOrders.refresh();
        list.remove(0,list.size());
        tblItemDetails.refresh();
        autoGenerateID();
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        comboPropertyID.getSelectionModel().clearSelection();
        comboCusID.getSelectionModel().clearSelection();
        txtQty.setText("");
    }

    public void btnAddToTableOnAction(ActionEvent actionEvent) {
        if (Pattern.compile("^[0-9]{1,10}").matcher(txtQty.getText()).matches()) {
            lblValidateQuantity.setVisible(false);
            if (comboCusID.getValue() != null) {
                if (comboPropertyID.getValue()!=null) {
                    double unitPrice = Double.parseDouble(txtUnitPrice.getText());
                    int qty = Integer.parseInt(txtQty.getText());
                    double discount = Double.parseDouble(lblDiscount.getText());
                    Double total = unitPrice * qty - discount * qty;
                    Button delete = new Button("Delete");

                    CartTM cartTM = new CartTM(
                            comboPropertyID.getValue(),
                            txtItemDescription.getText(),
                            unitPrice,
                            qty,
                            total,
                            delete
                    );

                    delete.setOnAction((event -> {
                        list.remove(cartTM);
                        tblItemDetails.refresh();
                        calFulTotal();
                    }));

                    int indexNumber = isExist(cartTM);
                    if (indexNumber == -1) {
                        if (Integer.parseInt(txtQtyOnHand.getText()) >= qty) {
                            list.add(cartTM);
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Invalid Quantity.!").show();
                        }
                    } else {
                        if (Integer.parseInt(txtQtyOnHand.getText()) >= (list.get(indexNumber).getQuantity() + qty)) {
                            list.get(indexNumber).setQuantity(list.get(indexNumber).getQuantity() + qty);
                            list.get(indexNumber).setTotal(list.get(indexNumber).getTotal() + total);
                            tblItemDetails.refresh();
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Invalid Quantity.!").show();
                        }

                    }
                    tblItemDetails.setItems(list);
                    calFulTotal();
                    comboPropertyID.getSelectionModel().clearSelection();
                }else{
                    new Alert(Alert.AlertType.WARNING, "Select BatchDTO..!!").show();
                }
            } else {
                new Alert(Alert.AlertType.WARNING, "Select CustomerDTO..!!").show();
            }
        } else {
//            txtQty.setStyle("-fx-border-color: #F21111;")( Paint.valueOf( "red" ) );
//            txtOrderQty.requestFocus( );
            lblValidateQuantity.setVisible(true);
        }
    }

    private void calFulTotal() {
        double fullTotal = 0;
        for (CartTM tm : list
        ) {
            fullTotal += tm.getTotal();
        }
        lblTotalCost.setText(String.valueOf(fullTotal));
    }

    private int isExist(CartTM cartTM) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCode().equals(cartTM.getCode())) {
                return i;
            }
        }
        return -1;
    }

    private void loadPropertyIDs() {
        try {
            ArrayList<BatchDTO> allBatchDTOS = batchBO.getAllActiveBatches();
            ObservableList list = FXCollections.observableArrayList();
            for (BatchDTO batchDTO : allBatchDTOS
            ) {
                list.add(batchDTO.getProperty_id());
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
            ArrayList<CustomerDTO> allCustomerDTOS = customerBO.getAllCustomers();
            ObservableList list = FXCollections.observableArrayList();
            for (CustomerDTO customerDTO : allCustomerDTOS) {
                list.add(customerDTO.getCustomer_id());
            }
            comboCusID.setItems(list);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (Exception e) {
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

                String substring = oldID.substring(2, 5);

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

    public void setCustomerValuesOnAction(String id) throws Exception {
        CustomerDTO customerDTO = customerBO.searchCustomer(id);
        txtCustomerName.setText(customerDTO.getCustomer_name());

    }

    public void setBatchValuesOnAction(String id) throws SQLException, ClassNotFoundException {
        BatchDTO batchDTO = batchBO.searchBatch(id);
        txtUnitPrice.setText(String.valueOf(batchDTO.getPrice()));
        lblDiscount.setText(String.valueOf(batchDTO.getDiscount()));
        txtItemDescription.setText(batchDTO.getBatch());
        txtQtyOnHand.setText(String.valueOf(batchDTO.getQuantity()));
    }

    public void loadCustomerCombo(MouseEvent mouseEvent) {
        loadCustomerIDs();
    }
}
