package com.system.controllers.ProductManagement;

import animatefx.animation.SlideInDown;
import com.jfoenix.controls.JFXTabPane;
import com.system.Message.Messages;
import com.system.config.Config;
import com.system.dao.ProductManagementDao;
import com.system.models.Products;
import com.system.models.Supplier;
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
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CategoryController implements Initializable {

    @FXML
    private TextField catName;
    @FXML
    private TextField  supName;
    @FXML
    private TextField phField;
    @FXML
    private TextField addField;
    @FXML
    private TextField emailAddress;
    @FXML
    private ComboBox<String> spNameBox;
    @FXML
    private ListView<String> spList;
    @FXML
    private JFXTabPane tab;
    @FXML
    private TextField recpCat;
    @FXML
    private TextField newRecipeName;
    @FXML
    private TextField sellPriceRecipie;
    @FXML
    private ComboBox<String> recipieCatBox;



    ProductManagementDao productManagementDao = new ProductManagementDao();

    ObservableList<String> categoryNames = FXCollections.observableArrayList(productManagementDao.getAllCategoryNames());
    ObservableList<String> suppliearNames = FXCollections.observableArrayList(productManagementDao.getSuppliearNames());
    ObservableList<String> recipieCategoryNames = FXCollections.observableArrayList(productManagementDao.getRecipieCategorys());
    ObservableList<String> recipieNames = FXCollections.observableArrayList(productManagementDao.getRecipieNames());

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

    public void getClearSuppliear(){
        supName.clear();
        phField.clear();
        addField.clear();
        emailAddress.clear();
    }
    public void suppliearButtonEvent(){

        Supplier supplier = new Supplier();

        ObservableList<String> phoneNoList = FXCollections.observableArrayList(productManagementDao.getSuppliearPhno());

        if(supName.getText().trim().isEmpty() || phField.getText().trim().isEmpty() || addField.getText().trim().isEmpty()
                || emailAddress.getText().trim().isEmpty()){
            Messages.getWarning("you have to fill All Fields");
        }else{
            if(phoneNoList.contains(phField.getText())){
                Messages.getWarning("Already have phone number with same name please change the Ph no");
            }else{
                supplier.setSupplierName(supName.getText());
                supplier.setPhoneNumber(phField.getText());
                supplier.setAddress(addField.getText());
                supplier.setEmail(emailAddress.getText());
                productManagementDao.addNewSupplier(supplier);
                getClearSuppliear();
            }
        }
    }


    public void clearField(){
        catName.clear();
    }

    public void categoryButtonEvent(){

        Products product = new Products();

        categoryNames = FXCollections.observableArrayList(productManagementDao.getAllCategoryNames());
        ProductManagementDao productManagementDao = new ProductManagementDao();
        if(catName.getText().trim().isEmpty()){
            Messages.getWarning("please enter the Category Name");
        }else{
            if(categoryNames.contains(catName.getText())){
                Messages.getWarning("Already have a category name with same name try another");
            }else {
                product.setCategoryName(catName.getText());
                productManagementDao.addNewCategory(product);
                clearField();

            }
        }
    }
    public void getSuppliearDataInList(){
        if(spNameBox.getSelectionModel().getSelectedItem() == null){
            System.out.println("error tackle");
        }
        Supplier supplier = new Supplier();
        supplier.setSupplierName(spNameBox.getSelectionModel().getSelectedItem());
        spList.getItems().clear();
        spList.getItems().addAll(productManagementDao.getSuppliearInfo(supplier));
    }


    public void addNewRecipieCategory(){

        ProductManagementDao productManagementDao = new ProductManagementDao();

        if(recpCat.getText().trim().isEmpty()){
            Messages.getWarning("please enter the Category Name");
        }else{
            if(recipieCategoryNames.contains(recpCat.getText())){
                Messages.getWarning("Already have a category name with same name try another");
            }else {

                productManagementDao.addNewRecipieCategory(recpCat.getText());
                recpCat.clear();

            }
        }
    }

    public void getClearAllData(){
        recipieNames.clear();
        recipieCatBox.getSelectionModel().clearSelection();
        sellPriceRecipie.clear();
    }

    public void addNewRecipieName(){

            recipieNames = FXCollections.observableArrayList(productManagementDao.getRecipieNames());
        if(newRecipeName.getText().trim().isEmpty()|| recipieCatBox.getSelectionModel().getSelectedItem().isEmpty()
                || sellPriceRecipie.getText().trim().isEmpty()){
            Messages.getWarning("please fill all data in fields");
        }else{
            if(recipieNames.contains(newRecipeName.getText())) {
                Messages.getWarning("there is Already have a Recipie with same name ");
            }else {
                productManagementDao.addNewRecipieName(recipieCatBox.getSelectionModel().getSelectedItem(),newRecipeName.getText());
                productManagementDao.addRecipieSellPrice(Float.parseFloat(sellPriceRecipie.getText()),java.time.LocalDate.now()+ " " + java.time.LocalTime.now(),newRecipeName.getText());
                getClearAllData();
            }
        }
    }

    public void tabRefresh(){
        tab.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Tab>() {
                    @Override
                    public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
                        suppliearNames = FXCollections.observableArrayList(productManagementDao.getSuppliearNames());
                        spNameBox.setItems(suppliearNames);
                        getClearSuppliear();
                        clearField();
                        categoryNames = FXCollections.observableArrayList(productManagementDao.getAllCategoryNames());
                        TextFields.bindAutoCompletion(catName,categoryNames);
                        spList.getItems().clear();
                        spNameBox.getSelectionModel().clearSelection();
                        getClearAllData();
                        recpCat.clear();
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
        tabRefresh();
        spNameBox.setItems(suppliearNames);
        TextFields.bindAutoCompletion(catName,categoryNames);
        recipieCatBox.setItems(recipieCategoryNames);
        sellPriceRecipie.textProperty().addListener(forceNumberListener);

    }
}
