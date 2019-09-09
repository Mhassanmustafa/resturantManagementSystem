package com.system.controllers.AccountManagement;

import animatefx.animation.SlideInDown;
import com.system.config.Config;
import com.system.dao.AccountManagementDao;
import com.system.models.Customers;
import com.system.models.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AccountHistoryController implements Initializable {

    @FXML
    private TableView<Customers> customerTable;
    @FXML
    private TableColumn<Customers ,String> c1;
    @FXML
    private TableColumn<Customers ,String> c2;
    @FXML
    private TableColumn<Customers ,String> c3;
    @FXML
    private TableColumn<Customers ,String> c4;
    @FXML
    private TableColumn<Customers ,String> c5;
    @FXML
    private TableColumn<Customers ,String> c6;
    @FXML
    private TableColumn<Customers ,String> c7;
    @FXML
    private TableColumn<Customers,String> c8;

    //table 2
    @FXML
    private TableView<Employee> EmployeeTable1;
    @FXML
    private TableColumn<Employee ,String> ec1;
    @FXML
    private TableColumn<Employee,String> ec2;
    @FXML
    private TableColumn<Employee ,String> ec3;
    @FXML
    private TableColumn<Employee ,String> ec4;
    @FXML
    private TableColumn<Employee ,String> ec5;
    @FXML
    private TableColumn<Employee ,String> ec6;

    AccountManagementDao accountManagementDao = new AccountManagementDao();

    ObservableList<Customers> customerTableList = FXCollections.observableArrayList(accountManagementDao.getAllCustomersData());
    ObservableList<Employee> empSalaryList = FXCollections.observableArrayList(accountManagementDao.getAllSalaryHistory());

    //button event for the home button use to change the scene and come to dashBoard
    public void homeButtonEvent(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/DashBoard.fxml"));
        Stage scene2 =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene2.setScene(new Scene(root, Config.width,Config.height));
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



    //button event for  employee account price button use to change the scene and goes to another scene
    public void EmployeeAccountButtonEvent(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AccountManagement/EmployeeAccount.fxml"));
        Stage scene2 =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene2.setScene(new Scene(root,Config.width,Config.height));
        scene2.show();
    }



    //button event for account history  button use to change the scene and goes to another scene
    public void accountHistoryButtonEvent(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AccountManagement/AccountHistory.fxml"));
        Stage scene2 =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene2.setScene(new Scene(root,Config.width,Config.height));
        scene2.show();
    }

    public void setCustomerTableColmn(){
        c1.setCellValueFactory(new PropertyValueFactory<Customers , String>("id"));
        c2.setCellValueFactory(new PropertyValueFactory<Customers , String>("name"));
        c3.setCellValueFactory(new PropertyValueFactory<Customers , String>("vistedShop"));
        c4.setCellValueFactory(new PropertyValueFactory<Customers , String>("phoneNumber"));
        c5.setCellValueFactory(new PropertyValueFactory<Customers , String>("credit"));
        c6.setCellValueFactory(new PropertyValueFactory<Customers , String>("debit"));
        c7.setCellValueFactory(new PropertyValueFactory<Customers , String>("balance"));
        c8.setCellValueFactory(new PropertyValueFactory<Customers , String>("dat"));
    }

    public void setEmployeeTableData(){
        ec1.setCellValueFactory(new PropertyValueFactory<Employee , String>("id"));
        ec2.setCellValueFactory(new PropertyValueFactory<Employee , String>("name"));
        ec3.setCellValueFactory(new PropertyValueFactory<Employee , String>("phNo"));
        ec4.setCellValueFactory(new PropertyValueFactory<Employee , String>("designation"));
        ec5.setCellValueFactory(new PropertyValueFactory<Employee , String>("basicSalary"));
        ec6.setCellValueFactory(new PropertyValueFactory<Employee , String>("date"));
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCustomerTableColmn();
        customerTable.setItems(customerTableList);
        setEmployeeTableData();
        EmployeeTable1.setItems(empSalaryList);
    }
}
