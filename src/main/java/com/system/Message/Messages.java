package com.system.Message;

import javafx.scene.control.Alert;

public class Messages {

    //method for giving warning massages
    public static void getWarning(String message){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(message);
        alert.setContentText("please enter again!!!");
        alert.showAndWait();
    }

    //method for giving Alert massages
    public static void getAlert(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
