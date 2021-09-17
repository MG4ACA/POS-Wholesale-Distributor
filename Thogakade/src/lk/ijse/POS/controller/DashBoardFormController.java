package lk.ijse.POS.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardFormController {

    public void openSaveCustomerOnAction(ActionEvent actionEvent) throws IOException {
        setUi("CustomerSaveForm");
    }

    public void openSearchCustomerOnAction(ActionEvent actionEvent) throws IOException {
        setUi("CustomerSearchForm");
    }

    public void openUpdateCustomerOnAction(ActionEvent actionEvent) throws IOException {
        setUi("CustomerUpdateForm");
    }

    public void openDeleteCustomerOnAction(ActionEvent actionEvent) throws IOException {
        setUi("CustomerDeleteForm");
    }

    public void openGetAllCustomerOnAction(ActionEvent actionEvent) throws IOException {
        setUi("CustomerListForm");
    }

    public void openPlaceOrderCustomerOnAction(ActionEvent actionEvent) {
    }

    void setUi(String location) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.show();
    }

}
