package lk.ijse.pos.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.pos.utils.CrudUtils;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFormController {
    public AnchorPane userPane;
    public AnchorPane adminPane;
    public TextField txtUserName;
    public PasswordField txtPassword;
    public TextField txtAdmin;
    public PasswordField txtAdminPw;
    public Button btnUserA;
    public Button btnAdminA;
    public Button btnUserL;
    public Button btnAdminL;

    public void initialize(){

    }
    public void loadUserAreaOnAction(ActionEvent actionEvent) {
        userPane.setVisible(true);
        adminPane.setVisible(false);
        btnUserA.setDisable(true);
        btnAdminA.setDisable(false);

    }

    public void loadAdminAreaOnAction(ActionEvent actionEvent) {
        userPane.setVisible(false);
        adminPane.setVisible(true);
        btnUserA.setDisable(false);
        btnAdminA.setDisable(true);
    }


    public void userLoginOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashBoardForm");

//        try {
//            ResultSet users = CrudUtils.execute("SELECT * FROM user");
//            while (users.next()){
//                if (users.getString(2).equals(txtUserName.getText()) && users.getString(3).equals(txtPassword.getText())){
//                    setUi("DashBoardForm");
//                    return;
//                }
//            }
//            new Alert(Alert.AlertType.WARNING,"No User Found.!").show();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        txtUserName.clear();
        txtPassword.clear();

    }


    public void adminLoginOnAction(ActionEvent actionEvent) {
        txtAdmin.clear();
        txtAdminPw.clear();
        setUi("AdminForm");

    }

    private void setUi(String location)  {
        Stage stage = (Stage)btnAdminL.getScene().getWindow();
        stage.close();

        Stage stage1 = new Stage();
        try {
            stage1.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
            stage1.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
