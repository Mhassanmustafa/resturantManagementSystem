package com.system.controllers.StockManagement;

import animatefx.animation.SlideInDown;
import com.system.config.Config;
import com.system.dao.StockManagementDao;
import com.system.models.StockDetails;
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

public class StockDetailController implements Initializable {

    @FXML
    private TableView<StockDetails> detailTable;
    @FXML
    private TableColumn<StockDetails, String> productId;
    @FXML
    private TableColumn<StockDetails , String> productName;
    @FXML
    private TableColumn<StockDetails , String> SupplierName;
    @FXML
    private TableColumn<StockDetails , String> Quantity;
    @FXML
    private TableColumn<StockDetails , String> SellingPrice;
    @FXML
    private TableColumn<StockDetails , String> SupplierPh;
    @FXML
    private TableColumn<StockDetails , String> BoughtPrice;

    StockManagementDao stockManagementDao = new StockManagementDao();

    ObservableList<StockDetails> stockList = FXCollections.observableArrayList(stockManagementDao.getStockDetailsData());

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

    public void setColumnData(){
        productId.setCellValueFactory(new PropertyValueFactory<StockDetails , String>("id"));
        productName.setCellValueFactory(new PropertyValueFactory<StockDetails , String>("productName"));
        SupplierName.setCellValueFactory(new PropertyValueFactory<StockDetails , String>("suppliearName"));
        Quantity.setCellValueFactory(new PropertyValueFactory<StockDetails , String>("quantity"));
        SellingPrice.setCellValueFactory(new PropertyValueFactory<StockDetails , String>("sellingPrice"));
        SupplierPh.setCellValueFactory(new PropertyValueFactory<StockDetails , String>("suppliearPhoneNo"));
        BoughtPrice.setCellValueFactory(new PropertyValueFactory<StockDetails , String>("boughtPrice"));
    }

    public void getDataInReport() throws Exception{
        stockManagementDao.printStockDetails();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setColumnData();
        detailTable.setItems(stockList);
    }
}
