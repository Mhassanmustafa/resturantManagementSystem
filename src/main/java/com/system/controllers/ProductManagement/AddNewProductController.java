package com.system.controllers.ProductManagement;

import animatefx.animation.SlideInDown;
import com.system.Message.Messages;
import com.system.config.Config;
import com.system.dao.ProductManagementDao;
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
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddNewProductController implements Initializable {

    @FXML
    private TextField productName;
    @FXML
    private ComboBox<String> categoryBox;
    @FXML
    private ComboBox<String> suppliearBox;
    @FXML
    private TextField sellPrice;

    ProductManagementDao productManagementDao = new ProductManagementDao();


    ObservableList<String> getCategoryList = FXCollections.observableList(productManagementDao.getAllCategoryNames());
    ObservableList<String> getSupplierList = FXCollections.observableList(productManagementDao.getSuppliearNames());
    ObservableList<String> productNamesList = FXCollections.observableArrayList(productManagementDao.getAllProductsNames());

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
    public void getClearData(){
        productName.clear();
        categoryBox.getSelectionModel().clearSelection();
        suppliearBox.getSelectionModel().clearSelection();
        sellPrice.clear();
    }
    //add button action
    public void addNewProduct(){
        ObservableList<String> productNamesList = FXCollections.observableArrayList(productManagementDao.getAllProductsNames());
        Products product = new Products();

        if(productName.getText().trim().isEmpty() || categoryBox.getSelectionModel().isEmpty() || suppliearBox.getSelectionModel().isEmpty()
                || sellPrice.getText().trim().isEmpty()){
            Messages.getWarning("you have to fill all fields");
        }else{
            if(productNamesList.contains(productName.getText())){

                Messages.getWarning("there is already an product with same name Enter different name");

            }else {

                product.setProductName(productName.getText());
                product.setSellPrice(Float.parseFloat(sellPrice.getText()));
                productManagementDao.addNewProduct(product,categoryBox.getSelectionModel().getSelectedItem(),suppliearBox.getSelectionModel().getSelectedItem());
                productManagementDao.addSellPrice(product,java.time.LocalDate.now()+ " " + java.time.LocalTime.now());
                getClearData();

            }
        }
    }

    ChangeListener<String> forceNumberListener = (observable, oldValue, newValue) -> {
        if (!newValue.matches(Config.regix))
            ((StringProperty) observable).set(oldValue);
    };


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        categoryBox.setItems(getCategoryList);
        suppliearBox.setItems(getSupplierList);
        TextFields.bindAutoCompletion(productName,productNamesList);
        productName.setPromptText("Product name should not be in auto completeion");
        sellPrice.textProperty().addListener(forceNumberListener);
    }
}
