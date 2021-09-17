package lk.ijse.pos.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AdminFormController {
    public Label lblRealDate;
    public Label lblRealTime;

    public void initialize(){
        generateRealTime();
    }

    public void addProductOnAction(ActionEvent actionEvent) {
        setUi("ProductForm");
    }

    private void setUi(String location) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addBatchOnAction(ActionEvent actionEvent) {
        setUi("BatchForm");
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


    public void logOutOnAction(ActionEvent actionEvent) {
        setUi("LoginForm");
        Stage window = (Stage) lblRealTime.getScene().getWindow();
        window.close();
    }

    public void generateReportOnAction(ActionEvent actionEvent) {
        setUi("SystemReportForm");
    }
}
