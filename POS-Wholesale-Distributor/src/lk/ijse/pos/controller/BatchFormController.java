package lk.ijse.pos.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.BatchBO;
import lk.ijse.pos.bo.custom.ProductBO;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.dto.BatchDTO;
import lk.ijse.pos.dto.ProductDTO;
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

    ProductBO productBO = (ProductBO) BOFactory.getInstance().getBo(BOFactory.getType.PRODUCT);
    BatchBO batchBO = (BatchBO) BOFactory.getInstance().getBo(BOFactory.getType.BATCH);
    public TextField txtPropertyID;

    public void initialize() {
        autoGenerateID();
        loadProductCombo();
    }

    private void loadProductCombo() {
        try {
            ArrayList<ProductDTO> allProductDTO = productBO.getAllProducts();
            ObservableList<String> list = FXCollections.observableArrayList();
            for (ProductDTO productDTO : allProductDTO
            ) {
                list.add(productDTO.getProductId());
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
            connection = DBConnection.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select property_id from batch order by property_id desc limit 1");
            boolean next = resultSet.next();
            if (next) {
                String oldID = resultSet.getString(1);

                String substring = oldID.substring(1, 4);

                int intId = Integer.parseInt(substring);

                intId = intId + 1;
                if (intId < 10) {
                    txtPropertyID.setText("B00" + intId);
                } else if (intId < 100) {
                    txtPropertyID.setText("B0" + intId);
                } else if (intId < 1000) {
                    txtPropertyID.setText("B" + intId);
                }
            } else {
                txtPropertyID.setText("B001");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = new Date();
        return formatter.format(date);
    }

    public void addBatchOnAction(ActionEvent actionEvent) {
        try {
            Boolean b = batchBO.addBatch(new BatchDTO(txtPropertyID.getText(), txtBatch.getText(), new BigDecimal(txtPrice.getText()), new BigDecimal(txtDiscount.getText()), cBoxDiscount.isSelected(), cBoxActiveStatus.isSelected(), Integer.parseInt(txtQty.getText()), getDate(), String.valueOf(cmbProduct.getValue())));
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Batch Saved Successfully.!!").show();
                clearFields();
                autoGenerateID();
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
            Boolean b = batchBO.updateBatch(new BatchDTO(txtPropertyID.getText(), txtBatch.getText(), new BigDecimal(txtPrice.getText()), new BigDecimal(txtDiscount.getText()), cBoxDiscount.isSelected(), cBoxActiveStatus.isSelected(), Integer.parseInt(txtQty.getText()), getDate(), String.valueOf(cmbProduct.getValue())));
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Batch Updated Successfully.!!").show();
                clearFields();
                autoGenerateID();

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
            BatchDTO batchDTO = batchBO.searchBatch(txtPropertyID.getText());
            if (batchDTO != null) {
                txtBatch.setText(batchDTO.getBatch());
                txtPrice.setText(String.valueOf(batchDTO.getPrice()));
                txtDiscount.setText(String.valueOf(batchDTO.getDiscount()));
                txtQty.setText(String.valueOf(batchDTO.getQuantity()));
                cBoxDiscount.setSelected(batchDTO.isDiscount_state());
                cBoxActiveStatus.setSelected(batchDTO.isActive_state());
                cmbProduct.setValue(batchDTO.getProduct_id());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteBatchOnAction(ActionEvent actionEvent) {
        try {
            Boolean b = batchBO.deleteBatch(txtPropertyID.getText());
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Batch Deleted Successfully.!!").show();
                clearFields();
                autoGenerateID();

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
