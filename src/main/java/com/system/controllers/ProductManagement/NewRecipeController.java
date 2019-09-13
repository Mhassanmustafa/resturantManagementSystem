package com.system.controllers.ProductManagement;

import animatefx.animation.SlideInDown;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTabPane;
import com.system.Message.Messages;
import com.system.config.Config;
import com.system.dao.ProductManagementDao;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.FloatStringConverter;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NewRecipeController implements Initializable {

    @FXML
    private JFXComboBox<String> recipieNameBox;

    @FXML
    private JFXComboBox<String> ingreidentsBox;

    @FXML
    private TextField quantityField;

    @FXML
    private TableView<Recipie> recipieTable;

    @FXML
    private TableColumn<Recipie, String> c1;

    @FXML
    private TableColumn<Recipie, String> c2;

    @FXML
    private TableColumn<Recipie, String> c3;

    @FXML
    private TableColumn<Recipie, String> c4;

    @FXML
    private TextField estimatedField;

    @FXML
    private TextField pPf;

    @FXML
    private TextField recipieNamefield;

    @FXML
    private TableView<Recipie> viewTable;

    @FXML
    private TableColumn<Recipie, String> c5;

    @FXML
    private TableColumn<Recipie, String> c6;

    @FXML
    private TableColumn<Recipie, String> c7;

    @FXML
    private JFXTabPane tab;



    ProductManagementDao productManagementDao = new ProductManagementDao();

    ObservableList<String> recipieProducts = FXCollections.observableArrayList(productManagementDao.getRecipieNames());
    ObservableList<String> productList = FXCollections.observableArrayList(productManagementDao.getAllProductsNames());
    ObservableList<Recipie> tableData = FXCollections.observableArrayList();
    ObservableList<Integer> recipeIdList = FXCollections.observableArrayList(productManagementDao.getRecipieId());
    ObservableList<Recipie> tableList = FXCollections.observableArrayList();

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

    public void setColumnData(){
        c1.setCellValueFactory(new PropertyValueFactory<>("productName"));
        c2.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        c3.setCellValueFactory(new PropertyValueFactory<>("price"));
        c4.setCellValueFactory(new PropertyValueFactory<>("PurPrice"));
    }

    public void setColumnData2(){
        c5.setCellValueFactory(new PropertyValueFactory<>("recipieID"));
        c6.setCellValueFactory(new PropertyValueFactory<>("productName"));
        c7.setCellValueFactory(new PropertyValueFactory<>("quantity"));

    }

    public void addButtonEvent(){
        float sellPrice = 0;
        float puchasePrice = 0;
        if(recipieNameBox.getSelectionModel().getSelectedItem().isEmpty() || quantityField.getText().trim().isEmpty()
        || ingreidentsBox.getSelectionModel().getSelectedItem().isEmpty()){
            Messages.getWarning("Please enter data in above Fields first");
        }else{
            if(recipieProducts.contains(recipieNameBox.getSelectionModel().getSelectedItem())){
                if(productList.contains(ingreidentsBox.getSelectionModel().getSelectedItem())){
                    if(tableData.stream().anyMatch(itemsCheck->ingreidentsBox.getSelectionModel().getSelectedItem().equals(itemsCheck.getProductName()))){
                        Messages.getWarning("Already have a same product in table first delete it then add new");
                        ingreidentsBox.getSelectionModel().clearSelection();
                        quantityField.clear();
                    }else {
                        float price = Float.parseFloat(quantityField.getText());
                        puchasePrice = productManagementDao.getLatestPurchasePrice(ingreidentsBox.getSelectionModel().getSelectedItem());
                        sellPrice = productManagementDao.getCurrentPrice(ingreidentsBox.getSelectionModel().getSelectedItem());
                        tableData.add(new Recipie(ingreidentsBox.getSelectionModel().getSelectedItem(), Float.parseFloat(quantityField.getText()), price * sellPrice, price * puchasePrice));
                        setColumnData();
                        recipieTable.setItems(tableData);
                        estimatedField.setText(Float.toString(getSum()));
                        pPf.setText(Float.toString(getSumP()));
                        System.out.println(getSumP());
                        ingreidentsBox.getSelectionModel().clearSelection();
                        quantityField.clear();
                    }
                }else{
                    Messages.getWarning("ingredents not found try again");
                }
            }else{
                Messages.getWarning("Recipie not found try again");
            }
        }

    }

    public void delButtonEvent(){
        if(recipieTable.getItems().isEmpty()){
            Messages.getWarning("please enter some data in table first");
        }else {
            recipieTable.getItems().remove(recipieTable.getSelectionModel().getSelectedItem());
            recipieTable.getSelectionModel().clearSelection();
            estimatedField.setText(Float.toString(getSum()));
            pPf.setText(Float.toString(getSumP()));
        }
    }

    public void updateColumnData(TableColumn.CellEditEvent<Recipie , Float> editEvent){
        if(recipieTable.getItems().isEmpty()){
            Messages.getWarning("please enter some data in table first");
        }else{
            Recipie recipie = recipieTable.getSelectionModel().getSelectedItem();
            recipie.setQuantity(editEvent.getNewValue());
            recipie.setPrice(editEvent.getNewValue() * recipie.getPrice());
            recipieTable.refresh();
            recipieTable.getSelectionModel().clearSelection();
            estimatedField.setText(Float.toString(getSum()));
            pPf.setText(Float.toString(getSumP()));
        }
    }

    public float getSum(){
        TableView<Recipie> table = c3.getTableView();
        float total = 0;
        for(Recipie recipie : table.getItems()){
            total = total + recipie.getPrice();
        }
        return total;
    }

    public float getSumP(){
        TableView<Recipie> table = c4.getTableView();
        float total = 0;
        for(Recipie recipie : table.getItems()){
            total = total + recipie.getPurPrice();
        }
        return total;
    }
    public void getClearData(){

        recipieNameBox.getSelectionModel().clearSelection();
        recipieTable.getItems().clear();
        estimatedField.clear();
    }

    public void addButton(){

        Recipie sqlData = new Recipie();

        if(recipieNameBox.getSelectionModel().getSelectedItem().isEmpty() || recipieTable.getItems().isEmpty()){
            Messages.getWarning("Please write the recipie Name first or enter data in table");
        }else {
            if(recipeIdList.contains(productManagementDao.getRecipieId(recipieNameBox.getSelectionModel().getSelectedItem()))){
                Messages.getWarning("this recipe is already availabe please create new recipie");
                getClearData();
            }else {

                    for (int i = 0; i < tableData.size(); i++) {
                        Recipie recipie = tableData.get(i);
                        sqlData.setRecipieName(recipieNameBox.getSelectionModel().getSelectedItem());
                        sqlData.setProductName(recipie.getProductName());
                        sqlData.setQuantity(recipie.getQuantity());
                        sqlData.setDate(java.time.LocalDate.now() + " " + java.time.LocalTime.now());
                        productManagementDao.addRecipeIngredents(sqlData);
                        sqlData.setPurPrice(Float.parseFloat(pPf.getText()));
                        productManagementDao.addnewRecipiePurchase(sqlData);
                        getClearData();
                    }

            }
        }

    }

    public void searchByName(){
        int id = productManagementDao.getRecipieId(recipieNamefield.getText());
        if(recipieNamefield.getText().isEmpty()){
            Messages.getAlert("please write recipie name first");
        }else {
            if(recipieProducts.contains(recipieNamefield.getText())){
                setColumnData2();
                viewTable.setItems(productManagementDao.getRecipieDetails(id));

            }else {
                Messages.getAlert("there is no such recipie with this name");
            }
        }
    }

    public void tabRefresh(){
        tab.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Tab>() {
                    @Override
                    public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
                        viewTable.getItems().clear();
                        recipieNamefield.clear();
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
        recipieNameBox.setItems(recipieProducts);
        TextFields.bindAutoCompletion(recipieNameBox.getEditor(),recipieProducts);
        TextFields.bindAutoCompletion(recipieNamefield,recipieProducts);
        ingreidentsBox.setItems(productList);
        TextFields.bindAutoCompletion(ingreidentsBox.getEditor(),productList);
        quantityField.textProperty().addListener(forceNumberListener);
        recipieTable.setEditable(true);
        tabRefresh();

    }
}
