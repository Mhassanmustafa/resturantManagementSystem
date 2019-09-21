package com.system.controllers.Invoice;

import animatefx.animation.SlideInDown;
import com.system.Message.Messages;
import com.system.config.Config;
import com.system.dao.AccountManagementDao;
import com.system.dao.InvoicesDao;
import com.system.models.Invoices;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OrderHistoryController implements Initializable {

    @FXML
    private TextField shopNameField;
    @FXML
    private TableView<Invoices> table;
    @FXML
    private TableColumn<Invoices , String> invoiceIdC;
    @FXML
    private TableColumn<Invoices , String> customerNameC;
    @FXML
    private TableColumn<Invoices , String> productNameC;
    @FXML
    private TableColumn<Invoices , String> unitPriceC;
    @FXML
    private TableColumn<Invoices , String> quantityC;
    @FXML
    private TableColumn<Invoices , String> discountC;
    @FXML
    private TableColumn<Invoices , String> amountC;
    @FXML
    private TableColumn<Invoices, String> dateC;

    AccountManagementDao accountManagementDao = new AccountManagementDao();
    InvoicesDao invoice = new InvoicesDao();

    ObservableList<Integer> idList = FXCollections.observableArrayList(invoice.getAllCustomerOrderId());
    ObservableList<Invoices> tableList = FXCollections.observableArrayList();

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

    //button event for  new invoice button use to change the scene and goes to add new button scene
    public void NewInvoiceButtonEvent(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Invoice/NewInvoice.fxml"));
        Stage scene2 =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene2.setScene(new Scene(root,Config.width,Config.height));
        scene2.show();
    }

    //button event for update sell price button use to change the scene and goes to another scene
    public void OrderHistoryButtonEvent(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Invoice/OrderHistory.fxml"));
        Stage scene2 =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene2.setScene(new Scene(root,Config.width,Config.height));
        scene2.show();
    }
    //button event for update sell price button use to change the scene and goes to another scene
    public void DeleteOrderButtonEvent(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Invoice/DeleteOrder.fxml"));
        Stage scene2 =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene2.setScene(new Scene(root,Config.width,Config.height));
        scene2.show();
    }

    //set colmn data
    public void setColumnData(){
        invoiceIdC.setCellValueFactory(new PropertyValueFactory<>("invoiceId"));
        customerNameC.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        productNameC.setCellValueFactory(new PropertyValueFactory<>("productName"));
        unitPriceC.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        quantityC.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        discountC.setCellValueFactory(new PropertyValueFactory<>("price"));
        amountC.setCellValueFactory(new PropertyValueFactory<>("discount"));
        dateC.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    //action on search button
    public void searchButtonAction(){
        if(shopNameField.getText().trim().isEmpty()){
            Messages.getWarning("Please enter Order id first in field");
        }else {
            if(idList.contains(Integer.parseInt(shopNameField.getText()))){
                table.getItems().clear();
                tableList =FXCollections.observableArrayList(invoice.getOrderHistory(Integer.parseInt(shopNameField.getText())));
                setColumnData();
                table.setItems(tableList);
                shopNameField.clear();
            }else {
                Messages.getWarning("There is no order with this id");
            }
        }
    }

    public void declarePrint(){
        InvoicesDao invoicesDao = new InvoicesDao();
        try {
            invoicesDao.printOrderHistory(tableList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TextFields.bindAutoCompletion(shopNameField,idList);
    }
}
