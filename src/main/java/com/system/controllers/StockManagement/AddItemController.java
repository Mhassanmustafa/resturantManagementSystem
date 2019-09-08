package com.system.controllers.StockManagement;

import animatefx.animation.SlideInDown;
import com.jfoenix.controls.JFXDatePicker;
import com.system.Message.Messages;
import com.system.config.Config;
import com.system.dao.ProductManagementDao;
import com.system.dao.StockManagementDao;
import com.system.models.Products;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddItemController implements Initializable {

    @FXML
    private ComboBox<String> productComboBox;
    @FXML
    private ComboBox<String> suppliearComboBox;
    @FXML
    private TextField quantityField;
    @FXML
    private TextField priceField;
    @FXML
    private JFXDatePicker datePicker;

    ProductManagementDao productManagementDao = new ProductManagementDao();
    StockManagementDao stockManagementDao = new StockManagementDao();

    ObservableList<String> productNameList = FXCollections.observableArrayList(productManagementDao.getAllProductsNames());
    ObservableList<String> suppliearNameList = FXCollections.observableArrayList(productManagementDao.getSuppliearNames());

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

    //button event for add new  button use to change the scene and goes to add new button scene
    public void addStockButtonEvent(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/StockManagement/AddItem.fxml"));
        Stage scene2 =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene2.setScene(new Scene(root,Config.width,Config.height));
        scene2.show();
    }

    //button event for  remove Stock price button use to change the scene and goes to another scene
    public void RemoveStockButtonEvent(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/StockManagement/RemoveStock.fxml"));
        Stage scene2 =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene2.setScene(new Scene(root,Config.width,Config.height));
        scene2.show();
    }

    //button event for stock detail button use to change the scene and goes to another scene
    public void StockDetailsButtonEvent(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/StockManagement/StockDetails.fxml"));
        Stage scene2 =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene2.setScene(new Scene(root,Config.width,Config.height));
        scene2.show();
    }

    public void clearData(){
        productComboBox.getSelectionModel().clearSelection();
        suppliearComboBox.getSelectionModel().clearSelection();
        quantityField.clear();
        priceField.clear();
        datePicker.getEditor().clear();
    }


    public void updateButtonEvent(){
        Products product = new Products();
        if(suppliearComboBox.getSelectionModel().isEmpty() || productComboBox.getSelectionModel().isEmpty()
                || quantityField.getText().trim().isEmpty() || priceField.getText().trim().isEmpty() ||
                datePicker.getEditor().getText().isEmpty()){

            Messages.getWarning("you should enter values in all fields");

        }else{

            product.setQuantity(Float.parseFloat(quantityField.getText()));
            product.setBoughtPrice(Float.parseFloat(priceField.getText()));
            product.setDate(datePicker.getValue().toString() +" "+ java.time.LocalTime.now());
            product.setProductName(productComboBox.getSelectionModel().getSelectedItem());
            product.setSuppliearName(suppliearComboBox.getSelectionModel().getSelectedItem());
            stockManagementDao.addnewStock(product);
            clearData();
        }
    }

    ChangeListener<String> forceNumberListener = (observable, oldValue, newValue) -> {
        if (!newValue.matches(Config.regix))
            ((StringProperty) observable).set(oldValue);
    };

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productComboBox.setItems(productNameList);
        suppliearComboBox.setItems(suppliearNameList);
        quantityField.textProperty().addListener(forceNumberListener);
        priceField.textProperty().addListener(forceNumberListener);

    }
}
