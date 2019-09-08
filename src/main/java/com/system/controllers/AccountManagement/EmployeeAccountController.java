package com.system.controllers.AccountManagement;

import animatefx.animation.SlideInDown;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTabPane;
import com.system.Message.Messages;
import com.system.config.Config;
import com.system.dao.AccountManagementDao;
import com.system.models.Employee;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeAccountController implements Initializable {

    @FXML
    private TextField empNameField;
    @FXML
    private TextField empAddressField;
    @FXML
    private TextField empPhoneField;
    @FXML
    private TextField empolyeeEmailField;
    @FXML
    private TextField empDesignationField;
    @FXML
    private TextField basicSalField;
    @FXML
    private JFXPasswordField empassword;
    @FXML
    private JFXTabPane tab;
    @FXML
    private JFXTabPane tab2;
    @FXML
    private ComboBox<String> updateSalaryNameFielld;
    @FXML
    private TextField updateSalaryAddress;
    @FXML
    private TextField updateSalaryphno;
    @FXML
    private TextField updateSalaryEmail;
    @FXML
    private TextField UpdateSakaryDesignation;
    @FXML
    private TextField UpdateSalaryAmount;
    @FXML
    private TextField UpdateSalaryprevious;
    @FXML
    private TextField viewAccountNameField;
    @FXML
    private TableView<Employee> table;
    @FXML
    private TableColumn<Employee , String> c1;
    @FXML
    private TableColumn<Employee , String> c2;
    @FXML
    private TableColumn<Employee , String> c3;
    @FXML
    private TableColumn<Employee , String> c4;
    @FXML
    private TableColumn<Employee , String> c7;
    @FXML
    private TableColumn<Employee , String> c8;
    @FXML
    private TableColumn<Employee , String> c9;
    @FXML
    private TableColumn<Employee , String> c10;
    @FXML
    private TableColumn<Employee , String> c11;

    AccountManagementDao accountManagementDao = new AccountManagementDao();

    ObservableList<String> userNameList = FXCollections.observableArrayList(accountManagementDao.getAllUserNames());
    ObservableList<String> empNameList = FXCollections.observableArrayList(accountManagementDao.getAllUserNames());

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
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/DashBoard.fxml"));
        Stage scene2 =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene2.setScene(new Scene(root,Config.width,Config.height));
        scene2.show();
    }

    //button event for settings
    public void settingsButtonEvent(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Statistics.fxml"));
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

    public void clearAddNewEmployeeData(){
        empNameField.clear();
        empPhoneField.clear();
        empAddressField.clear();
        empolyeeEmailField.clear();
        empDesignationField.clear();
        basicSalField.clear();
    }

    public void addNewEmployeeButton(){
        if(empNameField.getText().trim().isEmpty() || empPhoneField.getText().trim().isEmpty() || empAddressField.getText().trim().isEmpty()
                || empolyeeEmailField.getText().trim().isEmpty() || empDesignationField.getText().trim().isEmpty()
                || basicSalField.getText().trim().isEmpty()){
            Messages.getWarning("you have to fill all the Fields");
        }else {

            if(userNameList.contains(empolyeeEmailField.getText())){
                Messages.getWarning("there is Already have Employee with same UserName please change the UserName");
            }else{
                Employee employee = new Employee();
                employee.setName(empNameField.getText());
                employee.setPhNo(empPhoneField.getText());
                employee.setAddress(empAddressField.getText());
                employee.setEmailAddress(empolyeeEmailField.getText());
                employee.setDesignation(empDesignationField.getText());
                employee.setBasicSalary(Float.parseFloat(basicSalField.getText()));
                employee.setDate(java.time.LocalDate.now()+ " " + java.time.LocalTime.now());
                employee.setPassword(empassword.getText());
                accountManagementDao.addNewEmployee(employee);
                accountManagementDao.addNewSalary(employee);
                accountManagementDao.addNewEmployeeUser(employee);
                clearAddNewEmployeeData();
            }
        }
    }

    public void tabRefresh(){
        tab.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Tab>() {
                    @Override
                    public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
                        clearAddNewEmployeeData();
                        clearDataOfUpdateSalary();
                    }
                }
        );
    }

    public void setFieldData(){
        if(updateSalaryNameFielld.getSelectionModel().getSelectedItem() == null){
            System.out.println("eror handle");
        }else {
            Employee employee = accountManagementDao.getEmployeeData(updateSalaryNameFielld.getSelectionModel().getSelectedItem());
            updateSalaryAddress.setText(employee.getAddress());
            updateSalaryphno.setText(employee.getPhNo());
            updateSalaryEmail.setText(employee.getEmailAddress());
            UpdateSakaryDesignation.setText(employee.getDesignation());
            UpdateSalaryprevious.setText(Float.toString(employee.getBasicSalary()));
        }
    }
    //get data clear from text field
    public void clearDataOfUpdateSalary(){
        updateSalaryNameFielld.getSelectionModel().clearSelection();
        updateSalaryEmail.clear();
        updateSalaryphno.clear();
        updateSalaryAddress.clear();
        UpdateSalaryAmount.clear();
        UpdateSalaryprevious.clear();
        UpdateSakaryDesignation.clear();
    }


    public void updateSalaryButtonEvent(){
        if(updateSalaryNameFielld.getSelectionModel().isEmpty() || UpdateSalaryAmount.getText().trim().isEmpty()){
            Messages.getWarning("you have to fill both the Fields");
        }else {
            Employee employee = new Employee();
            employee.setEmailAddress(updateSalaryNameFielld.getSelectionModel().getSelectedItem());
            employee.setBasicSalary(Float.parseFloat(UpdateSalaryAmount.getText()));
            employee.setDate(java.time.LocalDate.now()+ " " + java.time.LocalTime.now());
            accountManagementDao.addnewBaseSalary(employee);
            clearDataOfUpdateSalary();
        }
    }

    ChangeListener<String> forceNumberListener = (observable, oldValue, newValue) -> {
        if (!newValue.matches(Config.regix))
            ((StringProperty) observable).set(oldValue);
    };



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabRefresh();
        basicSalField.textProperty().addListener(forceNumberListener);
        updateSalaryNameFielld.setItems(empNameList);
    }
}
