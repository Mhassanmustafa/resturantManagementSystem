package com.system.dao;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.system.Logs.Log;
import com.system.Message.Messages;
import com.system.Queries.Query;
import com.system.config.Config;
import com.system.dao.Interfaces.IInvoices;
import com.system.models.Customers;
import com.system.models.Invoices;
import com.system.models.Ledger;
import com.system.services.SqlConnectionServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.awt.*;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Date;
import java.util.HashMap;

import static com.system.config.Config.shopName;

public class InvoicesDao implements IInvoices {

    public static void getLogInfo (String message){
        try {
            Log log = new Log();
            log.logger.info(message);
        }catch (Exception exp){
            exp.printStackTrace();
        }

    }

    public static void getLogWarning (String message){
        try {
            Log log = new Log();
            log.logger.warning(message);
        }catch (Exception exp){
            exp.printStackTrace();
        }

    }

    //to close the open sql connections
    public void closeSqlConnection(Connection connection){
        if(connection != null){
            SqlConnectionServices.closeConnection(connection);
        }
    }

    @Override
    public ObservableList<String> getCustomerNames() {

        ObservableList<String> list = FXCollections.observableArrayList();
        Connection connection = SqlConnectionServices.getConnection();

        try{

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection, Query.customerNames,null);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                while (resultSet.next()){
                    list.add(resultSet.getString(1));
                }
            }

        }catch (SQLException exp){
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }

        return list;
    }

    @Override
    public ObservableList<String> getCustomerPhoneNos() {
        ObservableList<String> list = FXCollections.observableArrayList();
        Connection connection = SqlConnectionServices.getConnection();

        try{

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection, Query.customerPhonos,null);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                while (resultSet.next()){
                    list.add(resultSet.getString(1));
                }
            }

        }catch (SQLException exp){
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }

        return list;
    }

    @Override
    public void addnewCustomer(Customers customer) {

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();


        params.put(1 , customer.getName());
        params.put(2 , customer.getPhoneNumber());


        try{

            int affectedRows = SqlConnectionServices.prepareAStatement(connection,Query.addNewCustomer,params).executeUpdate();

            if(affectedRows == 0){
                getLogInfo("new customer not add successfully");
                System.out.println("data is not inserted");
            }else {
                System.out.println("Data inserted Successfully");
                getLogInfo("new customer add successfull");
            }

        }catch (Exception exp){
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }
    }

    @Override
    public void addNewOrder(int customerId, String date) {
        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();


        params.put(1 , customerId);
        params.put(2 , date);

        try{
            int affectedRows = SqlConnectionServices.prepareAStatement(connection,Query.customerOrder,params).executeUpdate();

            if(affectedRows == 0){
                getLogInfo("new order is not added");
                System.out.println("Value is not inserted");
            }else {
                System.out.println("Value inserted Successfully");
                getLogInfo("new order is added in database");
            }
        }catch (Exception exp){
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }
    }

    @Override
    public int getOrderId(int customerId) {
        int orderId = 0;

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();


        params.put(1 , customerId);

        try{
            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection,Query.customerOrderId,params);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                while (resultSet.next()){
                    orderId = resultSet.getInt(1);
                }
            }
        }catch (Exception exp){
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }
        return orderId;
    }

    @Override
    public void insertOrderHistory(int orderId, int productId, float quantity, float amount, float discount, String date) {
        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();


        params.put(1 , orderId);
        params.put(2 , productId);
        params.put(3 , quantity);
        params.put(4 , amount);
        params.put(5 , discount);
        params.put(6 , date);

        try{
            int affectedRows = SqlConnectionServices.prepareAStatement(connection,Query.customerOrderHistoy,params).executeUpdate();

            if(affectedRows == 0){
                getLogInfo("insert in orderHistory not successfull");
                System.out.println("Value is not inserted");
            }else {
                System.out.println("Value inserted Successfully");
                getLogInfo("insert in orderHistory is successfull");
            }
        }catch (Exception exp){
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }
    }

    @Override
    public void insertLedgerData(Ledger ledger) {
        float balaance = ledger.getDebit() - ledger.getCredit();
        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();


        params.put(1,ledger.getAccountId());
        params.put(2,ledger.getCutomerId());
        params.put(3,ledger.getCustomerOrderId());
        params.put(4,ledger.getCredit());
        params.put(5,ledger.getDebit());
        params.put(6,balaance);
        params.put(7,ledger.getDescription());
        params.put(8,ledger.getDate());

        try {

            int affectedRows = SqlConnectionServices.prepareAStatement(connection,Query.custLedgerData,params).executeUpdate();

            if(affectedRows == 0){
                getLogInfo("ledger data is not inserted successfull");
                System.out.println("Value is not inserted");
            }else {
                getLogInfo("ledger data is inserted successfull");
                System.out.println("Value inserted Successfully");
            }

        }catch (Exception exp){
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }
    }

    @Override
    public ObservableList<Integer> getProductIdList(int recipieId) {

        ObservableList<Integer> list = FXCollections.observableArrayList();
        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();

        params.put(1,recipieId);

        try{

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection, Query.getIngredentsId,params);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                while (resultSet.next()){
                    list.add(resultSet.getInt(1));
                }
            }

        }catch (SQLException exp){
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }

        return list;
    }

    @Override
    public ObservableList<Integer> getQuantityList(int recipieId) {
        ObservableList<Integer> list = FXCollections.observableArrayList();
        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();

        params.put(1,recipieId);

        try{

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection, Query.getProductQuantity,params);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                while (resultSet.next()){
                    list.add(resultSet.getInt(1));
                }
            }

        }catch (SQLException exp){
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }

        return list;
    }

    @Override
    public float getStockAvailableQuantity(int ProductId) {
        float availableQuantity = 0;

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();


        params.put(1 , ProductId);

        try{
            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection,Query.getStockQuantityAvailable,params);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                while (resultSet.next()){
                    availableQuantity = resultSet.getInt(1);
                }
            }
        }catch (Exception exp){
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }
        return availableQuantity;
    }

    @Override
    public void insertNewStockHistory(int productId, float totalQuantity, String description, String date) {

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();


        params.put(1,productId);
        params.put(2,totalQuantity);
        params.put(3,description);
        params.put(4,date);

        try {

            int affectedRows = SqlConnectionServices.prepareAStatement(connection,Query.newStockHistory,params).executeUpdate();

            if(affectedRows == 0){
                getLogInfo("values not inserted in new stock history");
                System.out.println("Value is not inserted");
            }else {
                System.out.println("Value inserted Successfully");
                getLogInfo("values inserted in new stock history");
            }

        }catch (Exception exp){
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }
    }

    @Override
    public float getLatestBalance(int customerId) {
        float balance = 0;

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();


        params.put(1 , customerId);
        try {

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection,Query.previousBalance,params);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet != null) {
                while (resultSet.next()) {
                    balance = resultSet.getFloat(1);
                }
            }

        }catch (Exception exp){
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }
        return balance;
    }

    @Override
    public void insertExistingLeger(Ledger ledger, int customerID) {
        float previousBlance = getLatestBalance(customerID);
        previousBlance = previousBlance +(ledger.getDebit() - ledger.getCredit());

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();


        params.put(1,ledger.getAccountId());
        params.put(2,customerID);
        params.put(3,ledger.getCustomerOrderId());
        params.put(4,ledger.getCredit());
        params.put(5,ledger.getDebit());
        params.put(6,previousBlance);
        params.put(7,ledger.getDescription());
        params.put(8,ledger.getDate());

        try {

            int affectedRows = SqlConnectionServices.prepareAStatement(connection,Query.custLedgerData,params).executeUpdate();

            if(affectedRows == 0){
                getLogInfo("existing  ledger data not inserted");
                System.out.println("Value is not inserted");
            }else {
                System.out.println("Value inserted Successfully");
                getLogInfo("existing edger data inserted");
            }

        }catch (Exception exp){
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }
    }


    @Override
    public ObservableList<Integer> getAllCustomerOrderId() {

        ObservableList<Integer> list = FXCollections.observableArrayList();
        Connection connection = SqlConnectionServices.getConnection();


        try{

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection,Query.getOrderId,null);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                while (resultSet.next()){
                    list.add(resultSet.getInt(1));
                }
            }

        }catch (SQLException exp){
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }

        return list;
    }

    @Override
    public ObservableList<Invoices> getOrderHistory(int  orderId) {



        ObservableList<Invoices> list = FXCollections.observableArrayList();

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();

        params.put(1 , orderId);

        try{

            PreparedStatement preparedStatement =SqlConnectionServices.prepareAStatement(connection,Query.customerOrderHistoryQuery,params);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                while (resultSet.next()){
                    list.add(new Invoices(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),
                            resultSet.getFloat(4),resultSet.getFloat(5),resultSet.getFloat(7),resultSet.getFloat(6),
                            resultSet.getString(8)));
                }
            }

        }catch (Exception exp){
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }
        return list;
    }

    @Override
    public void printOrderHistory(int orderId) throws Exception {

//        Document doc = new Document(PageSize.A4.rotate());
//        Date date = new Date();
//
//       // String file = Paths.get(Config.OrderHistoryPdf.toAbsolutePath().toString(),
//                String.format("OrderDetails-%tF-%tI-%tM-%tS.pdf", date, date, date, date)).toString();
//
//        FileOutputStream fileStream = new FileOutputStream(file);
//        PdfWriter writer = PdfWriter.getInstance(doc, fileStream);
//        doc.open();
//        doc.add(new Paragraph(Config.address, Config.font));
//        doc.add(new Paragraph(Config.contactNumber, Config.font));
//        doc.add(new Paragraph("Customer shop name: " + shopName, Config.simpleFont));
//        Paragraph paraDate = new Paragraph(new Date().toString());
//        paraDate.setAlignment(Config.alignRight);
//        doc.add(paraDate);
//
//        Paragraph shopTitle = new Paragraph(shopName, Config.boldFont);
//        shopTitle.setAlignment(Config.alignCenter);
//        doc.add(shopTitle);
//
//        doc.add(new Paragraph(Config.rotateLines));
//        doc.add(new Paragraph("The Order history is given"));
//        doc.add(new Paragraph("\n"));
//
//        Connection connection = SqlConnectionServices.getConnection();
//        HashMap<Integer , Object> params = new HashMap<>();
//
//
//        params.put(1 , orderId);
//
//        PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection, Query.customerOrderHistoryQuery, params);
//        ResultSet resultSet = preparedStatement.executeQuery();
//
//        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
//        PdfPTable table = new PdfPTable(resultSetMetaData.getColumnCount());
//        table.setWidthPercentage(100);
//
//        String header[] = {"Invoice Id", "Customer Name","product Name","Unit price","Quantity", "Amount" ,"Discount","Date"};
//        for (int i = 0; i < header.length; i++) {
//            PdfPCell pdfPCell = new PdfPCell(new Paragraph(header[i], FontFactory.getFont(FontFactory.HELVETICA, 11, Font.BOLD
//                    , BaseColor.DARK_GRAY)));
//            table.addCell(pdfPCell);
//        }
//
//        while (resultSet.next()) {
//            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
//                PdfPCell cell = new PdfPCell(new Paragraph(resultSet.getString(i)));
//                table.addCell(cell);
//            }
//        }
//
//        doc.add(table);
//        doc.addCreationDate();
//        doc.close();
//        writer.close();
//        Desktop d = Desktop.getDesktop();
//        d.open(new File(file));
//        Messages.getAlert("Exported to Pdf please Wait while file is opening");
//    }
    }
}
