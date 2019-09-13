package com.system.controllers.EmployeeInvoice;

import animatefx.animation.SlideInDown;
import com.system.Message.Messages;
import com.system.config.Config;
import com.system.dao.AccountManagementDao;
import com.system.dao.InvoicesDao;
import com.system.dao.ProductManagementDao;
import com.system.models.Invoices;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DeleteOrderController implements Initializable {

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
    InvoicesDao invoicesDao = new InvoicesDao();
    ProductManagementDao productManagementDao = new ProductManagementDao();


    ObservableList<Integer> idList = FXCollections.observableArrayList(invoicesDao.getAllCustomerOrderId());
    ObservableList<Invoices> tableList = FXCollections.observableArrayList();

    ChangeListener<String> forceNumberListener = (observable, oldValue, newValue) -> {
        if (!newValue.matches(Config.regix))
            ((StringProperty) observable).set(oldValue);
    };

    //button event for the order history button use to change the scene
    public void orderHistoryEvent(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/EmployerLogin/EmployeeOrderHistory.fxml"));
        Stage scene2 =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene2.setScene(new Scene(root, Config.width,Config.height));
        scene2.show();
    }

    public void DeleteOrderEvent(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/EmployerLogin/EmployeeDeleteOrder.fxml"));
        Stage scene2 =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene2.setScene(new Scene(root, Config.width,Config.height));
        scene2.show();
    }

    //button event for the invoice button use to change the scene and goes to invoice scene
    public void invoiceButtonEvent(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/EmployerLogin/EmployeeInvoice.fxml"));
        Stage scene2 =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene2.setScene(new Scene(root,Config.width,Config.height));
        scene2.show();
    }

    //button event for the log out
    public void logoutEvent(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        Stage scene2 =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene2.setScene(new Scene(root,882,543));
        scene2.show();
        new SlideInDown(root).play();
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


    public void searchButtonEvent(){
        if(shopNameField.getText().trim().isEmpty()){
            Messages.getWarning("Please enter Order id first in field");
        }else {
            if(idList.contains(Integer.parseInt(shopNameField.getText()))){
                table.getItems().clear();
                tableList =FXCollections.observableArrayList(invoicesDao.getOrderHistory(Integer.parseInt(shopNameField.getText())));
                setColumnData();
                table.setItems(tableList);

            }else {
                Messages.getWarning("There is no order with this id");
            }
        }
    }


    public void deleteButtonEvent(){
        if(table.getItems().isEmpty()){
            Messages.getAlert("please add values in table first");
        }else {
            for(int i = 0 ; i < tableList.size() ; i++){


                Invoices invoices = tableList.get(i);
                int productId = productManagementDao.getRecipieId(invoices.getProductName());
                ObservableList<Integer> productIdList = FXCollections.observableArrayList(invoicesDao.getProductIdList(productId));
                ObservableList<Integer> productQuantityList = FXCollections.observableArrayList(invoicesDao.getQuantityList(productId));

                for(int j = 0; j < productIdList.size() && j < productQuantityList.size() ; j++){

                    float totalQuantity = invoicesDao.getStockAvailableQuantity(productIdList.get(j));
                    float availableQuantity = (invoices.getQuantity() * productQuantityList.get(j));
                    invoicesDao.insertNewStockHistory(productIdList.get(j),totalQuantity + availableQuantity,"Order deleted",
                            java.time.LocalDate.now()+ " " + java.time.LocalTime.now());

                }

            }

            invoicesDao.deleteLedgerData(Integer.parseInt(shopNameField.getText()));
            invoicesDao.deleteCustOrderHistoryData(Integer.parseInt(shopNameField.getText()));
            invoicesDao.deleteCustomerOrderData(Integer.parseInt(shopNameField.getText()));
            invoicesDao.deleteTempData(Integer.parseInt(shopNameField.getText()));
            idList.remove(shopNameField.getText());
            shopNameField.clear();
            table.getItems().clear();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        shopNameField.textProperty().addListener(forceNumberListener);
        TextFields.bindAutoCompletion(shopNameField,idList);
    }
}
