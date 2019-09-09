package com.system.controllers.EmployeeInvoice;

import animatefx.animation.SlideInDown;
import com.jfoenix.controls.JFXComboBox;
import com.system.InvoiceGenerator.InvoicesGenerator;
import com.system.Message.Messages;
import com.system.config.Config;
import com.system.dao.AccountManagementDao;
import com.system.dao.InvoicesDao;
import com.system.dao.ProductManagementDao;
import com.system.models.Customers;
import com.system.models.Invoices;
import com.system.models.Ledger;
import com.system.models.Recipie;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.FloatStringConverter;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class EmployeeInvoiceController implements Initializable {

    @FXML
    private TextField customerName;

    @FXML
    private TextField phoneNoField;

    @FXML
    private TextField price;

    @FXML
    private TextField quantity;

    @FXML
    private TextField totalAmount;

    @FXML
    private RadioButton add;

    @FXML
    private ToggleGroup choice;

    @FXML
    private RadioButton edit;

    @FXML
    private JFXComboBox<String> productNamesField;

    @FXML
    private TableView<Invoices> invoiceTable;

    @FXML
    private TableColumn<Invoices, String> productNameC;

    @FXML
    private TableColumn<Invoices, String> priceC;

    @FXML
    private TableColumn<Invoices, Float> quanttiyC;

    @FXML
    private TableColumn<Invoices, String> totalAmountC;

    @FXML
    private TextField subTotal;

    @FXML
    private TextField discount;

    @FXML
    private TextField netAmount;

    ProductManagementDao productManagementDao = new ProductManagementDao();
    InvoicesDao invoicesDao = new InvoicesDao();
    AccountManagementDao accountManagementDao = new AccountManagementDao();
    InvoicesGenerator generator = new InvoicesGenerator();
    Date date = new Date();


    ObservableList<String> productList = FXCollections.observableArrayList(productManagementDao.getRecipieNames());
    ObservableList<String> customerNamesList = FXCollections.observableArrayList(invoicesDao.getCustomerNames());
    ObservableList<String> customerPhoneList = FXCollections.observableArrayList(invoicesDao.getCustomerPhoneNos());
    ObservableList<Invoices> tableData = FXCollections.observableArrayList();


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

    //get total amount in the Total Amount Field
    public void getTotalAmount(){
        float amount = 0;
        float totalQuantity = Float.parseFloat(quantity.getText());
        amount = totalQuantity * Float.parseFloat(price.getText());
        totalAmount.setText(Float.toString(amount));
    }

    //set  product names combo box action to get the current selling price of the product.
    public void ProductNamesFieldAction(){
        if(productNamesField.getSelectionModel().getSelectedItem() == null){
            System.out.println("Do nothing");
        }else{
            Recipie recipie = new Recipie();
            recipie.setRecipieName(productNamesField.getSelectionModel().getSelectedItem());
            price.setText(Float.toString(productManagementDao.getRecipeCurrentPrice(recipie)));
        }
    }

    //get new Customer
    public Customers getNewCustomer(){
        Customers customer  = new Customers();
        customer.setName(customerName.getText());
        customer.setPhoneNumber(phoneNoField.getText());
        return customer;
    }

    //set column data of table
    public void setTableColmnData(){
        productNameC.setCellValueFactory(new PropertyValueFactory<>("productName"));
        priceC.setCellValueFactory(new PropertyValueFactory<>("price"));
        quanttiyC.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        totalAmountC.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
    }

    //set add button action event

    public void productAddButtonEvent(){

        if(add.isSelected() == false && edit.isSelected() == false ){
            Messages.getWarning("Please select any one option add or edit");
        }else if(edit.isSelected()) {
            Messages.getAlert("Please select add to enter data");
        }else if(add.isSelected()){
            if(productNamesField.getEditor().getText().isEmpty() ||
                    quantity.getText().trim().isEmpty() ||totalAmount.getText().trim().isEmpty()){
                Messages.getWarning("please enter data about unit ,product ,totalQuantity and quantity");
            }else {
                if(productList.contains(productNamesField.getSelectionModel().getSelectedItem())) {

                    if(tableData.stream().anyMatch(itemsCheck->productNamesField.getSelectionModel().getSelectedItem().equals(itemsCheck.getProductName()))){
                        Messages.getWarning("Please select edit and double click on quantity colmn enter new vale and press enter");
                        clearFieldData();
                    }else {
                        tableData.add(new Invoices(productNamesField.getSelectionModel().getSelectedItem(),
                                Float.parseFloat(price.getText()), Float.parseFloat(quantity.getText()), Float.parseFloat(totalAmount.getText())));
                        setTableColmnData();
                        invoiceTable.setItems(tableData);
                        subTotal.setText(Float.toString(getSum()));
                        clearFieldData();
                    }
                }else {
                    Messages.getWarning("There is no such product you have please check your product name ");
                }

            }
        }
    }

    //clear data of the fields
    public void clearFieldData(){
        productNamesField.getSelectionModel().clearSelection();
        price.clear();
        quantity.clear();
        totalAmount.clear();
    }

    public float getSum(){
        TableView<Invoices> table = totalAmountC.getTableView();
        float total = 0;
        for (Invoices value : table.getItems()) {
            total = total + value.getTotalAmount();
        }
        return total;
    }

    public void productRemoveActionEvent(){
        if(add.isSelected() == false && edit.isSelected() == false ){
            Messages.getWarning("Please select any one option add or edit");
        }else if(add.isSelected()) {
            Messages.getAlert("Please select edit to delete the row");
        }else if(edit.isSelected()) {
            if(invoiceTable.getItems().isEmpty()){
                Messages.getWarning("please Add some Data First before Continue");
            }else {
                invoiceTable.getItems().removeAll(invoiceTable.getSelectionModel().getSelectedItem());
                invoiceTable.getSelectionModel().clearSelection();
                subTotal.setText(Float.toString(getSum()));
            }
        }
    }

    //edit the quantity colmn
    public void updateColumnData(TableColumn.CellEditEvent<Invoices , Float> editEvent){
        if(add.isSelected() == false && edit.isSelected() == false ){
            Messages.getWarning("Please select any one option add or edit");
        }else if(add.isSelected()) {
            Messages.getAlert("Please select edit to edit the row");
        }else if(edit.isSelected()) {

            Invoices invoices = invoiceTable.getSelectionModel().getSelectedItem();
            invoices.setQuantity(editEvent.getNewValue());
            invoices.setTotalAmount(editEvent.getNewValue() * invoices.getPrice());
            invoiceTable.refresh();
            invoiceTable.getSelectionModel().clearSelection();
            subTotal.setText(Float.toString(getSum()));
        }
    }

    //get discount action
    public void discountAction(){
        float subtract = 0;
        if(discount.getText().trim().isEmpty()){
            discount.setText("0.0");
            subtract = getSum() - Float.parseFloat(discount.getText());
            netAmount.setText(Float.toString(subtract));
        }else{
            subtract = getSum() - Float.parseFloat(discount.getText());
            netAmount.setText(Float.toString(subtract));

        }
    }

    //get total amount paid
    public float getAmountPaid(){
        float paid = 0;
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Enter Amount Paid");
        dialog.setHeaderText("Input Amount");
        dialog.setContentText("Enter Amount:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            paid = Integer.parseInt(result.get());
        }
        return paid;
    }

    //get clear all fields
    public void clearAllFields(){
        productNamesField.getSelectionModel().clearSelection();
        price.clear();
        quantity.clear();
        totalAmount.clear();
        subTotal.clear();
        discount.clear();
        netAmount.clear();
        add.setSelected(false);
        edit.setSelected(false);
        invoiceTable.getItems().clear();
        customerName.clear();
        phoneNoField.clear();

    }

    public void callExistingCustomerInvoice() throws Exception{
        Customers customer = accountManagementDao.getCustomerInfo(customerName.getText(),phoneNoField.getText());
        int customerId = customer.getId();
        float amountPaid = getAmountPaid();

        invoicesDao.addNewOrder(customerId,java.time.LocalDate.now()+ " " + java.time.LocalTime.now());
        int orderId = invoicesDao.getOrderId(customerId);

        for (int i = 0 ; i < tableData.size();i++ ){
            Invoices invoices = tableData.get(i);
            int productId = productManagementDao.getRecipieId(invoices.getProductName());
            invoicesDao.insertOrderHistory(orderId,productId,invoices.getQuantity(),invoices.getTotalAmount(),0,java.time.LocalDate.now()+ " " + java.time.LocalTime.now());

            ObservableList<Integer> productIdList = FXCollections.observableArrayList(invoicesDao.getProductIdList(productId));
            ObservableList<Integer> productQuantityList = FXCollections.observableArrayList(invoicesDao.getQuantityList(productId));

            for(int j = 0; j < productIdList.size() && j < productQuantityList.size() ; j++){

                float totalQuantity = invoicesDao.getStockAvailableQuantity(productIdList.get(j));
                float availableQuantity = (invoices.getQuantity() * productQuantityList.get(j));
                invoicesDao.insertNewStockHistory(productIdList.get(j),totalQuantity - availableQuantity,"Product sell",
                        java.time.LocalDate.now()+ " " + java.time.LocalTime.now());

            }

        }

        Ledger ledger = new Ledger();
        ledger.setAccountId(1);
        ledger.setCutomerId(customerId);
        ledger.setCustomerOrderId(orderId);
        ledger.setCredit(Float.parseFloat(netAmount.getText()));
        ledger.setDebit( amountPaid);
        ledger.setDescription("Bill added");
        ledger.setDate(java.time.LocalDate.now()+ " " + java.time.LocalTime.now());
        invoicesDao.insertExistingLeger(ledger,customerId);
        if(!Files.exists(Config.billsPdf)){
            Files.createDirectories(Config.billsPdf);
        }

        String file = Paths.get(Config.billsPdf.toAbsolutePath().toString(),
                String.format(customerName.getText() + "-Bill-%tF-%tI-%tM-%tS.pdf", date, date, date, date)).toString();
        generator.createPDF(file, tableData, Integer.toString(orderId), java.time.LocalDate.now().toString()
                , customer, netAmount.getText(), subTotal.getText(), discount.getText(), Float.toString(amountPaid));
        clearAllFields();
        clearAllFields();
    }


    public void callNewCustomerInvoice() throws Exception{
        Customers customer = getNewCustomer();
        float amountPaid = getAmountPaid();
        invoicesDao.addnewCustomer(customer);

        Customers currentCustomer = accountManagementDao.getCustomerInfo(customerName.getText(),phoneNoField.getText());
        int customerId = currentCustomer.getId();
        invoicesDao.addNewOrder(customerId,java.time.LocalDate.now()+ " " + java.time.LocalTime.now());

        int orderId = invoicesDao.getOrderId(customerId);

        for (int i = 0 ; i < tableData.size();i++ ){
            Invoices invoices = tableData.get(i);
            int productId = productManagementDao.getRecipieId(invoices.getProductName());
            invoicesDao.insertOrderHistory(orderId,productId,invoices.getQuantity(),invoices.getTotalAmount(),0,java.time.LocalDate.now()+ " " + java.time.LocalTime.now());

            ObservableList<Integer> productIdList = FXCollections.observableArrayList(invoicesDao.getProductIdList(productId));
            ObservableList<Integer> productQuantityList = FXCollections.observableArrayList(invoicesDao.getQuantityList(productId));

            for(int j = 0; j < productIdList.size() && j < productQuantityList.size() ; j++){

                float totalQuantity = invoicesDao.getStockAvailableQuantity(productIdList.get(j));
                float availableQuantity = (invoices.getQuantity() * productQuantityList.get(j));
                invoicesDao.insertNewStockHistory(productIdList.get(j),totalQuantity - availableQuantity,"Product sell",
                        java.time.LocalDate.now()+ " " + java.time.LocalTime.now());

            }

        }

        Ledger ledger = new Ledger();
        ledger.setAccountId(1);
        ledger.setCutomerId(customerId);
        ledger.setCustomerOrderId(orderId);
        ledger.setCredit(Float.parseFloat(netAmount.getText()));
        ledger.setDebit( amountPaid);
        ledger.setDescription("Bill added");
        ledger.setDate(java.time.LocalDate.now()+ " " + java.time.LocalTime.now());
        invoicesDao.insertLedgerData(ledger);
        customerNamesList.add(customerName.getText());
        customerPhoneList.add(phoneNoField.getText());

        if(!Files.exists(Config.billsPdf)){
            Files.createDirectories(Config.billsPdf);
        }

        String file = Paths.get(Config.billsPdf.toAbsolutePath().toString(),
                String.format(customerName.getText() + "-Bill-%tF-%tI-%tM-%tS.pdf", date, date, date, date)).toString();
        generator.createPDF(file, tableData, Integer.toString(orderId), java.time.LocalDate.now().toString()
                , customer, netAmount.getText(), subTotal.getText(), discount.getText(), Float.toString(amountPaid));
        clearAllFields();


    }

    //print button action
    public void printAction() throws Exception {
        if (customerName.getText().trim().isEmpty() || invoiceTable.getItems().isEmpty() || netAmount.getText().trim().isEmpty()) {
            Messages.getWarning("Please enter Customer  name or date or table data first");
        } else {
            if (customerNamesList.contains(customerName.getText()) && customerPhoneList.contains(phoneNoField.getText())) {
                callExistingCustomerInvoice();
            } else {
                callNewCustomerInvoice();
            }
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productNamesField.setItems(productList);
        TextFields.bindAutoCompletion(productNamesField.getEditor(),productList);
        quantity.textProperty().addListener(forceNumberListener);
        discount.textProperty().addListener(forceNumberListener);
        invoiceTable.setEditable(true);
        quanttiyC.setCellFactory((TextFieldTableCell.forTableColumn(new FloatStringConverter())));
    }
}
