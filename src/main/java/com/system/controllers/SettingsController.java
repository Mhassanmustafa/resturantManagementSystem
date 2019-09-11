package com.system.controllers;

import animatefx.animation.SlideInDown;
import com.system.Message.Messages;
import com.system.Queries.Query;
import com.system.SqlBackup.SqlBackUp;
import com.system.config.Config;
import com.system.dao.AccountManagementDao;
import com.system.services.SqlConnectionServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    @FXML
    private PasswordField oldPassword;

    @FXML
    private PasswordField newPassword;

    @FXML
    private PasswordField retypePassword;

    @FXML
    private TextField userName;


    //button event for the home button use to change the scene and come to dashBoard
    public void homeButtonEvent(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/DashBoard.fxml"));
        Stage scene2 =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene2.setScene(new Scene(root,Config.width,Config.height));
        scene2.show();
    }

    //button event for the invoice button use to change the scene and goes to invoice scene
    public void invoiceButtonEvent(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Invoice.fxml"));
        Stage scene2 =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene2.setScene(new Scene(root,Config.width,Config.height));
        scene2.show();
    }

    //button event for the product managemet button use to change the scene and goes to product management scene
    public void productManagementButtonEvent(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/ProductManagement.fxml"));
        Stage scene2 =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene2.setScene(new Scene(root,Config.width,Config.height));
        scene2.show();
    }

    //button event for the stock managemet button use to change the scene and goes to stock management scene
    public void stockManagementButtonEvent(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/StockManagement.fxml"));
        Stage scene2 =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene2.setScene(new Scene(root,Config.width,Config.height));
        scene2.show();
    }

    //button event for the account managemet button use to change the scene and goes to account management scene
    public void accManagementButtonEvent(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AccountManagement.fxml"));
        Stage scene2 =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene2.setScene(new Scene(root,Config.width,Config.height));
        scene2.show();
    }

    //button event for the statistic button use to change the scene and goes to statistic scene
    public void statisticsButtonEvent(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Statistics.fxml"));
        Stage scene2 =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene2.setScene(new Scene(root,Config.width,Config.height));
        scene2.show();
    }

    //button event for settings
    public void settingsButtonEvent(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Settings.fxml"));
        Stage scene2 =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene2.setScene(new Scene(root,Config.width,Config.height));
        scene2.show();
    }

    //button event for logout button use to change the scene and goes to logout scene
    public void logoutButtonEvent(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        Stage scene2 =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene2.setScene(new Scene(root,882,543));
        scene2.show();
        new SlideInDown(root).play();
    }
    public void updateButtonEvent(){
        AccountManagementDao accountManagementDao = new AccountManagementDao();
        if(userName.getText().trim().isEmpty() || oldPassword.getText().trim().isEmpty()||newPassword.getText().trim().isEmpty()
                ||retypePassword.getText().trim().isEmpty()){
            Messages.getWarning("Please fill All Fields");
        }else if(newPassword.getText().equals(retypePassword.getText())) {
            String newPasswordResult = DigestUtils.md5Hex(newPassword.getText());
            String oldPasswordResult = DigestUtils.md5Hex(oldPassword.getText());
            accountManagementDao.UpdatePassword(userName.getText(),newPasswordResult,oldPasswordResult);
            userName.clear();
            oldPassword.clear();
            newPassword.clear();
            retypePassword.clear();
        }else if(!(newPassword.getText().equals(retypePassword.getText()))){
            Messages.getWarning("new password and retype password don not match try again");
            newPassword.clear();
            retypePassword.clear();
        }
    }

    public void makeBackupOnDisk(){
        try {
            Connection connection = SqlConnectionServices.getConnection();
            Statement stmt = connection.createStatement();
            stmt.execute(Query.sqlBackupQuery);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void backupData(){
        SqlBackUp sqlBackUp = new SqlBackUp();
        if (sqlBackUp.checkInternetIsConnected()){
            if(sqlBackUp.checkFileisPresent()){
                if(sqlBackUp.delfile()){
                    makeBackupOnDisk();
                    try {


                        if (sqlBackUp.checkFilePresent()) {
                            sqlBackUp.getSqlDataBackup();
                        } else {
                            Messages.getWarning("operation unsucess");
                            System.out.println("operation unsuccess");
                        }
                    }catch (Exception exp){
                        exp.printStackTrace();
                    }
                }
            }else {
                makeBackupOnDisk();
                try {
                    if (sqlBackUp.checkFilePresent()) {
                        sqlBackUp.getSqlDataBackup();
                    } else {
                        Messages.getWarning("operation unsuccess");
                        System.out.println("operation unsuccess");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }else {
            Messages.getWarning("Internet not connected backup not made");
            System.out.println("Internet not connected backup not made");
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
