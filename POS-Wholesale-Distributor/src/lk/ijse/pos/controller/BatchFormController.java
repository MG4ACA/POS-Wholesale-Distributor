package lk.ijse.pos.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.utils.CrudUtils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BatchFormController {
    public Button btnSave;
    public TextField txtBatch;
    public TextField txtPrice;
    public TextField txtDiscount;
    public TextField txtQty;
    public CheckBox cBoxDiscount;
    public CheckBox cBoxActiveStatus;
    public ComboBox cmbProduct;
    public Label lblBatchNo;

    public void initialize(){
        autoGenerateID();
        loadProductCombo();
    }

    private void loadProductCombo() {
        try {
            ResultSet rst = CrudUtils.execute("SELECT * FROM product");
            ObservableList<String> list = FXCollections.observableArrayList();
               while (rst.next()){
                   list.add(rst.getString(1));
               }
                cmbProduct.setItems(list);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void autoGenerateID() {
        Connection connection = null;
        try {
            connection = DBConnection.getInstance ( ).getConnection ( );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery ( "select property_id from batch order by property_id desc limit 1" );
            boolean next = resultSet.next();
            if ( next ){
                String oldID = resultSet.getString ( 1 );

                String substring = oldID.substring ( 1, 4 );

                int intId = Integer.parseInt ( substring );

                intId = intId + 1;
                if ( intId <10 ){
                    lblBatchNo.setText ( "B00"+intId );
                }else if ( intId <100 ){
                    lblBatchNo.setText ( "B0"+intId );
                }else if ( intId <1000 ){
                    lblBatchNo.setText ( "B"+intId );
                }
            }else {
                lblBatchNo.setText ( "B001" );
            }

        } catch (SQLException e) {
            e.printStackTrace ( );
        }
    }

    private String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = new Date();
        return formatter.format(date);
    }

    public void addBatchOnAction(ActionEvent actionEvent) {
        try {
            Boolean b = CrudUtils.execute("INSERT INTO batch VALUES (?,?,?,?,?,?,?,?,?)", lblBatchNo.getText(), txtBatch.getText(), new BigDecimal(txtPrice.getText()), new BigDecimal(txtDiscount.getText()), cBoxDiscount.isSelected(), cBoxActiveStatus.isSelected(), Integer.parseInt(txtQty.getText()), getDate(), String.valueOf(cmbProduct.getValue()));
            System.out.println(b);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateBatchOnAction(ActionEvent actionEvent) {
    }

    public void searchBatchOnAction(ActionEvent actionEvent) {
    }

    public void deleteBatchOnAction(ActionEvent actionEvent) {
    }

}
