package com.system.controllers.ProductManagement;

import animatefx.animation.SlideInDown;
import com.jfoenix.controls.JFXTabPane;
import com.system.Message.Messages;
import com.system.config.Config;
import com.system.dao.ProductManagementDao;
import com.system.models.Products;
import com.system.models.Recipie;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UpdateSellPriceController implements Initializable {

    @FXML
    private ComboBox<String> productNameBox;
    @FXML
    private TextField currentPriceField;
    @FXML
    private TextField newPriceField;
    @FXML
    private JFXTabPane tab;
    @FXML
    private ComboBox<String> productNameBox1;
    @FXML
    private TextField currentPriceField1;
    @FXML
    private TextField newPriceField1;

    ProductManagementDao productManagementDao = new ProductManagementDao();

    ObservableList<String> productNamesList = FXCollections.observableArrayList(productManagementDao.getAllProductsNames());
    ObservableList<String> recipieNameList = FXCollections.observableArrayList(productManagementDao.getRecipieNames());
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
    public void addNewProductButtonEvent(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/ProductManagement/AddNewProduct.fxml"));
        Stage scene2 =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene2.setScene(new Scene(root,Config.width,Config.height));
        scene2.show();
    }
    //button event for update sell price button use to change the scene and goes to another scene
    public void UpdateSellPriceButtonEvent(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/ProductManagement/UpdateSellPrice.fxml"));
        Stage scene2 =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene2.setScene(new Scene(root,Config.width,Config.height));
        scene2.show();
    }

    //button event for product history  button use to change the scene and goes to another scene
    public void ProductHistoryButtonEvent(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/ProductManagement/ProductHistory.fxml"));
        Stage scene2 =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene2.setScene(new Scene(root,Config.width,Config.height));
        scene2.show();
    }
    //button event for category  button use to change the scene and goes to another scene
    public void CategoryButtonEvent(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/ProductManagement/Category.fxml"));
        Stage scene2 =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene2.setScene(new Scene(root,Config.width,Config.height));
        scene2.show();
    }

    public void getClear(){
        productNameBox.getSelectionModel().clearSelection();
        newPriceField.clear();
        currentPriceField.clear();
    }

    public void getPreviousSellPrice(){
        if(productNameBox.getSelectionModel().getSelectedItem() == null){
            System.out.println("Eror Handle");
        }else {
            currentPriceField.setText(Float.toString(productManagementDao.getCurrentPrice(productNameBox.getSelectionModel().getSelectedItem())));
        }
    }

    public void updateButtonEvent(){

        Products product = new Products();

        if(productNameBox.getSelectionModel().isEmpty() ||
                newPriceField.getText().trim().isEmpty()){
            Messages.getAlert("you Have to Fill All the Fields");
        }else{

            product.setProductName(productNameBox.getSelectionModel().getSelectedItem());
            product.setSellPrice(Float.parseFloat(newPriceField.getText()));
            product.setDate(java.time.LocalDate.now()+ " " + java.time.LocalTime.now());

            productManagementDao.setNewSellPrice(product);
            getClear();

        }
    }

    public void getRecipieCurrentPrice(){
        if(productNameBox1.getSelectionModel().getSelectedItem() == null){
            System.out.println("Eror Handle");
        }else {
            Recipie recipie = new Recipie();
            recipie.setRecipieName(productNameBox1.getSelectionModel().getSelectedItem());
            currentPriceField1.setText(Float.toString(productManagementDao.getRecipeCurrentPrice(recipie)));
        }
    }

    public void getClear1(){
        productNameBox1.getSelectionModel().clearSelection();
        newPriceField1.clear();
        currentPriceField1.clear();
    }
    public void updateEvent(){

        Recipie recipie = new Recipie();
        if(productNameBox1.getSelectionModel().isEmpty() ||
                newPriceField1.getText().trim().isEmpty()){
            Messages.getAlert("you Have to Fill All the Fields");
        }else{

            recipie.setRecipieName(productNameBox1.getSelectionModel().getSelectedItem());
            recipie.setPrice(Float.parseFloat(newPriceField1.getText()));
            recipie.setDate(java.time.LocalDate.now()+ " " + java.time.LocalTime.now());

            productManagementDao.setNewRecipieSellPrice(recipie);
            getClear1();

        }
    }

    public void tabRefresh(){
        tab.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Tab>() {
                    @Override
                    public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
                        getClear();
                        getClear1();
                    }
                }
        );
    }

    ChangeListener<String> forceNumberListener = (observable, oldValue, newValue) -> {
        if (!newValue.matches(Config.regix))
            ((StringProperty) observable).set(oldValue);
    };
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productNameBox.setItems(productNamesList);
        newPriceField.textProperty().addListener(forceNumberListener);
        productNameBox1.setItems(recipieNameList);
        newPriceField1.textProperty().addListener(forceNumberListener);
        tabRefresh();
    }
}
