package com.system.controllers;

import animatefx.animation.*;
import com.system.Message.Messages;
import com.system.Queries.Query;
import com.system.SqlBackup.SqlBackUp;
import com.system.config.Config;
import com.system.dao.AccountManagementDao;
import com.system.services.SqlConnectionServices;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private PasswordField password;

    @FXML
    private TextField userName;

    AccountManagementDao accountManagementDao = new AccountManagementDao();
    //log in button action
    public void logInAction(ActionEvent event)throws Exception {
        String result = DigestUtils.md5Hex(password.getText());
        if(accountManagementDao.adminLogIn(userName.getText(),result)){
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/DashBoard.fxml"));
            Stage scene2 =(Stage)((Node)event.getSource()).getScene().getWindow();
            scene2.setScene(new Scene(root, Config.width,Config.height));
            scene2.show();

        }else {
            Messages.getWarning("User Name or password is Wrong");
        }
    }

    public void passwordFieldAction(ActionEvent event)throws Exception{
        password.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER)) {
                    try {


                        String result = DigestUtils.md5Hex(password.getText());
                        if (accountManagementDao.adminLogIn(userName.getText(), result)) {
                            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Dashboard.fxml"));
                            Stage scene2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            scene2.setScene(new Scene(root, Config.width, Config.height));
                            scene2.show();
                            new FadeIn(root).play();
                        } else {
                            Messages.getWarning("User Name or password is Wrong");
                        }
                    }catch (Exception exp){
                        exp.printStackTrace();
                    }
                }
            }
        });
    }

    public void labelAction(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/EmployerLogin/EmpLogIn.fxml"));
        Stage scene2 =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene2.setScene(new Scene(root,882,543));
        scene2.show();
        new SlideInDown(root).play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
