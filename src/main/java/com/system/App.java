package com.system;


import animatefx.animation.SlideInDown;
import com.system.config.Config;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/DashBoard.fxml"));

        primaryStage.setTitle("Resturant Management System");
        primaryStage.setScene(new Scene(root, Config.width,Config.height));
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
