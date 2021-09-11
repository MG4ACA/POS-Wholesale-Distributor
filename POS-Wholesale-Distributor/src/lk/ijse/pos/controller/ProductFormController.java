package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.model.Product;
import lk.ijse.pos.utils.CrudUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductFormController {
    public Button btnSave;
    public CheckBox cBoxActiveStatus;
    public TextField txtName;
    public TextField txtDescription;
    public TextField txtSpecification;
    public TextField txtDisplayName;
    public CheckBox cBoxAvailability;
    public TextField txtBrand;
    public TextField txtId;
    private final ProductController productController= new ProductController();

    public void initialize(){
        autoGenerateID();
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
            ResultSet resultSet = statement.executeQuery ( "select product_id from product order by product_id desc limit 1" );
            boolean next = resultSet.next();
            if ( next ){
                String oldID = resultSet.getString ( 1 );

                String substring = oldID.substring ( 1, 4 );

                int intId = Integer.parseInt ( substring );

                intId = intId + 1;
                if ( intId <10 ){
                    txtId.setText ( "P00"+intId );
                }else if ( intId <100 ){
                    txtId.setText ( "P0"+intId );
                }else if ( intId <1000 ){
                    txtId.setText ( "P"+intId );
                }
            }else {
                txtId.setText ( "P001" );
            }

        } catch (SQLException e) {
            e.printStackTrace ( );
        }
    }


    public void addProductOnAction(ActionEvent actionEvent) {
        try {
            Product product = new Product(txtId.getText(), txtName.getText(), txtDescription.getText(), txtSpecification.getText(), txtDisplayName.getText(), cBoxAvailability.isSelected(), cBoxActiveStatus.isSelected(), txtBrand.getText());
            Boolean b = productController.saveProduct(product);
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION,"Product Saved Successfully.!!").show();
                clearFields();
            }else {
                new Alert(Alert.AlertType.WARNING,"Product Not Saved.!!").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        txtId.clear();
        txtName.clear();
        txtDescription.clear();
        txtSpecification.clear();
        txtDisplayName.clear();
        cBoxAvailability.setSelected(false);
        cBoxActiveStatus.setSelected(false);
        txtBrand.clear();
    }

    public void updateProductOnAction(ActionEvent actionEvent) {
        try {
            Boolean b = productController.updateProduct(new Product(txtId.getText(), txtName.getText(), txtDescription.getText(), txtSpecification.getText(), txtDisplayName.getText(), cBoxAvailability.isSelected(), cBoxActiveStatus.isSelected(), txtBrand.getText()));
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION,"Product Updated Successfully.!!").show();
                clearFields();
            }else {
                new Alert(Alert.AlertType.WARNING,"Product Not Updated.!!").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void searchProductOnAction(ActionEvent actionEvent) {
        try {
            Product product = productController.searchProduct(txtId.getText());
            if (product!=null) {
                txtName.setText(product.getProductName());
                txtDescription.setText(product.getDescription());
                txtSpecification.setText(product.getSpecification());
                txtDisplayName.setText(product.getDisplayName());
                cBoxAvailability.setSelected(product.isAvailability());
                cBoxActiveStatus.setSelected(product.isActiveState());
                txtBrand.setText(product.getAvailableBrands());
            }else {
                new Alert(Alert.AlertType.WARNING,"Product Not Available.!!").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteProductOnAction(ActionEvent actionEvent) {
        try {
            Boolean b = productController.deleteProduct(txtId.getText());
            if (!b) {
                new Alert(Alert.AlertType.WARNING,"Product Not Deleted.!!").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION,"Product Deleted Successfully.!!").show();
            }
            clearFields();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
