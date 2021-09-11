package lk.ijse.pos.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.model.Batch;
import lk.ijse.pos.model.Product;
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
    private final ProductController productController=new ProductController();
    private final BatchController batchController=new BatchController();

    public void initialize(){
        autoGenerateID();
        loadProductCombo();
    }

    private void loadProductCombo() {
        try {
            ArrayList<Product> allProduct = productController.getAllProduct();
            ObservableList<String> list = FXCollections.observableArrayList();
            for (Product product:allProduct
                 ) {
                list.add(product.getProductId());
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
            Boolean b = batchController.saveBatch(new Batch(lblBatchNo.getText(), txtBatch.getText(), new BigDecimal(txtPrice.getText()), new BigDecimal(txtDiscount.getText()), cBoxDiscount.isSelected(), cBoxActiveStatus.isSelected(), Integer.parseInt(txtQty.getText()), getDate(), String.valueOf(cmbProduct.getValue())));
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Batch Saved Successfully.!!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.WARNING, "Batch Not Saved.!!").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        txtBatch.clear();
         txtPrice.clear();
        txtDiscount.clear();
     txtQty.clear();
        cBoxDiscount.setSelected(false);
     cBoxActiveStatus.setSelected(false);
       cmbProduct.setValue(null);

    }

    public void updateBatchOnAction(ActionEvent actionEvent) {
        try {
            Boolean b = batchController.updateBatch(new Batch(lblBatchNo.getText(), txtBatch.getText(), new BigDecimal(txtPrice.getText()), new BigDecimal(txtDiscount.getText()), cBoxDiscount.isSelected(), cBoxActiveStatus.isSelected(), Integer.parseInt(txtQty.getText()), getDate(), String.valueOf(cmbProduct.getValue())));
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Batch Updated Successfully.!!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.WARNING, "Batch Not Updated.!!").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void searchBatchOnAction(ActionEvent actionEvent) {
        try {
            Batch batch = batchController.searchBatch(lblBatchNo.getText());
            if (batch!=null) {
                txtBatch.setText(batch.getBatch());
                txtPrice.setText(String.valueOf(batch.getPrice()));
                txtDiscount.setText(String.valueOf(batch.getDiscount()));
               txtQty.setText(String.valueOf(batch.getQuantity()));
             cBoxDiscount.setSelected(batch.isDiscount_state());
               cBoxActiveStatus.setSelected(batch.isActive_state());
              cmbProduct.setValue(batch.getProduct_id());
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteBatchOnAction(ActionEvent actionEvent) {
        try {
            Boolean b = batchController.deleteBatch(lblBatchNo.getText());
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Batch Deleted Successfully.!!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.WARNING, "Batch Not Deleted.!!").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
