package lk.ijse.pos.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lk.ijse.pos.model.Customer;
import lk.ijse.pos.utils.CrudUtils;
import lk.ijse.pos.view.tm.OrdersTM;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SystemReportFormController {
    public Pane paneItem;
    public Pane paneIncome;
    public DatePicker datePStart;
    public DatePicker datePEnd;
    public TableColumn colOrderId;
    public TableColumn colOderDate;
    public TableColumn colIncome;
    public TableColumn colCusId;
    public TableView tblIncome;
    public Pane paneDailyIncome;
    public Pane paneCustomer;
    public Label lblTotalIncome;
    public Button btnAnnual;
    public Button btnMonthly;
    public Button btnDaily;
    private final CustomerController customerController = new CustomerController();
    public ComboBox comboCusID;

    public void initialize() {
        disablePane();
        loadCustomerIDs();
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("order_id"));
        colOderDate.setCellValueFactory(new PropertyValueFactory<>("order_date"));
        colIncome.setCellValueFactory(new PropertyValueFactory<>("total_cost"));
        colCusId.setCellValueFactory(new PropertyValueFactory<>("customer_id"));

//        comboCusID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//            try {
//                setCustomerValuesOnAction(newValue);
//                loadOrdersTable(newValue);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
    }

    private void disablePane() {
        paneIncome.setVisible(false);
        paneItem.setVisible(false);
        paneDailyIncome.setVisible(false);
        paneCustomer.setVisible(false);
    }

    public void loadItemPaneOnAction(ActionEvent actionEvent) {
        paneItem.setVisible(true);
        paneIncome.setVisible(false);

    }

    public void loadIncomePaneOnAction(ActionEvent actionEvent) {
        paneIncome.setVisible(true);
        paneItem.setVisible(false);

    }

    public void loadDailyIncomeOnAction(ActionEvent actionEvent) {
        paneDailyIncome.setVisible(true);
        datePStart.setVisible(true);
        btnDaily.setVisible(true);
        btnAnnual.setVisible(false);
        btnMonthly.setVisible(false);
        datePEnd.setVisible(false);
        comboCusID.setVisible(false);

    }

    public void loadMonthlyIncomeOnAction(ActionEvent actionEvent) {
        paneDailyIncome.setVisible(true);

        datePStart.setVisible(true);
        datePEnd.setVisible(true);

        btnDaily.setVisible(false);
        btnAnnual.setVisible(false);
        btnMonthly.setVisible(true);
        comboCusID.setVisible(false);

    }

    public void loadAnnulIncomeOnAction(ActionEvent actionEvent) {
        paneDailyIncome.setVisible(true);

        datePStart.setVisible(true);
        datePEnd.setVisible(true);

        btnDaily.setVisible(false);
        btnAnnual.setVisible(true);
        btnMonthly.setVisible(false);

        comboCusID.setVisible(false);

    }

    public void loadCustomerWiseIncomeOnAction(ActionEvent actionEvent) {
        paneDailyIncome.setVisible(true);

        datePStart.setVisible(false);
        datePEnd.setVisible(false);

        btnDaily.setVisible(false);
        btnAnnual.setVisible(false);
        btnMonthly.setVisible(false);

        comboCusID.setVisible(true);

    }

    public void generateDailyIncomeReportOnAction(ActionEvent actionEvent) {
        try {
            ResultSet rst = CrudUtils.execute("SELECT * FROM orders WHERE order_date=?", datePStart.getValue());
            ObservableList<OrdersTM> list = FXCollections.observableArrayList();
            double totalIncome = 0;
            while (rst.next()) {
                list.add(new OrdersTM(rst.getString(1), rst.getDate(2), rst.getBigDecimal(3), rst.getString(4), rst.getString(5)));
            }
            tblIncome.setItems(list);

            for (OrdersTM ordersTM : list) {
                totalIncome += Double.valueOf(String.valueOf(ordersTM.getTotal_cost()));
                lblTotalIncome.setText(String.valueOf(totalIncome));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }


    public void generateMonthlyIncomeReportOnAction(ActionEvent actionEvent) {
        try {
            ResultSet rst = CrudUtils.execute("SELECT * FROM orders WHERE order_date BETWEEN ? AND ?", datePStart.getValue(), datePEnd.getValue());
            ObservableList<OrdersTM> list = FXCollections.observableArrayList();
            double totalIncome = 0;
            while (rst.next()) {
                list.add(new OrdersTM(rst.getString(1), rst.getDate(2), rst.getBigDecimal(3), rst.getString(4), rst.getString(5)));
            }
            tblIncome.setItems(list);

            for (OrdersTM ordersTM : list) {
                totalIncome += Double.valueOf(String.valueOf(ordersTM.getTotal_cost()));
                lblTotalIncome.setText(String.valueOf(totalIncome));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void generateYearlyIncomeReportOnAction(ActionEvent actionEvent) {
        try {
            ResultSet rst = CrudUtils.execute("SELECT * FROM orders WHERE order_date BETWEEN ? AND ?", datePStart.getValue(), datePEnd.getValue());
            ObservableList<OrdersTM> list = FXCollections.observableArrayList();
            double totalIncome = 0;
            while (rst.next()) {
                list.add(new OrdersTM(rst.getString(1), rst.getDate(2), rst.getBigDecimal(3), rst.getString(4), rst.getString(5)));
            }
            tblIncome.setItems(list);

            for (OrdersTM ordersTM : list) {
                totalIncome += Double.valueOf(String.valueOf(ordersTM.getTotal_cost()));
                lblTotalIncome.setText(String.valueOf(totalIncome));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadCustomerIDs() {
        try {
            ArrayList<Customer> allCustomers = customerController.getAllCustomers();
            ObservableList list = FXCollections.observableArrayList();
            for (Customer customer : allCustomers) {
                list.add(customer.getCustomer_id());
            }
            comboCusID.setItems(list);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadCustomerCombo( ActionEvent actionEvent) {
        ObservableList<OrdersTM> list = FXCollections.observableArrayList();
        double totalIncome = 0;

        try {
            ResultSet rst=CrudUtils.execute("SELECT * FROM orders WHERE customer_id=?", String.valueOf(comboCusID.getValue()));
            while (rst.next()){
                list.add(new OrdersTM(rst.getString(1), rst.getDate(2), rst.getBigDecimal(3), rst.getString(4), rst.getString(5)));
            }
            tblIncome.setItems(list);

            for (OrdersTM ordersTM : list) {
                totalIncome += Double.valueOf(String.valueOf(ordersTM.getTotal_cost()));
                lblTotalIncome.setText(String.valueOf(totalIncome));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
