package com.system.dao;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.system.InvoiceGenerator.InvoicesGenerator;
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

    public static void getLogInfo(String message) {
        try {
            Log log = Log.getInstance();
            log.logger.info(message);
        } catch (Exception exp) {
            exp.printStackTrace();
        }

    }

    public static void getLogWarning(String message) {
        try {
            Log log = Log.getInstance();
            log.logger.warning(message);
        } catch (Exception exp) {
            exp.printStackTrace();
        }

    }

    //to close the open sql connections
    public void closeSqlConnection(Connection connection) {
        if (connection != null) {
            SqlConnectionServices.closeConnection(connection);
        }
    }

    @Override
    public ObservableList<String> getCustomerNames() {

        ObservableList<String> list = FXCollections.observableArrayList();
        Connection connection = SqlConnectionServices.getConnection();

        try {

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection, Query.customerNames, null);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    list.add(resultSet.getString(1));
                }
            }

        } catch (SQLException exp) {
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        } finally {
            this.closeSqlConnection(connection);
        }

        return list;
    }

    @Override
    public ObservableList<String> getCustomerPhoneNos() {
        ObservableList<String> list = FXCollections.observableArrayList();
        Connection connection = SqlConnectionServices.getConnection();

        try {

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection, Query.customerPhonos, null);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    list.add(resultSet.getString(1));
                }
            }

        } catch (SQLException exp) {
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        } finally {
            this.closeSqlConnection(connection);
        }

        return list;
    }

    @Override
    public void addnewCustomer(Customers customer) {

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer, Object> params = new HashMap<>();


        params.put(1, customer.getName());
        params.put(2, customer.getPhoneNumber());


        try {

            int affectedRows = SqlConnectionServices.prepareAStatement(connection, Query.addNewCustomer, params).executeUpdate();

            if (affectedRows == 0) {
                getLogInfo("new customer not add successfully");
                System.out.println("data is not inserted");
            } else {
                System.out.println("Data inserted Successfully");
                getLogInfo("new customer add successfull");
            }

        } catch (Exception exp) {
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        } finally {
            this.closeSqlConnection(connection);
        }
    }

    @Override
    public void addNewOrder(int customerId, String date) {
        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer, Object> params = new HashMap<>();


        params.put(1, customerId);
        params.put(2, date);

        try {
            int affectedRows = SqlConnectionServices.prepareAStatement(connection, Query.customerOrder, params).executeUpdate();

            if (affectedRows == 0) {
                getLogInfo("new order is not added");
                System.out.println("Value is not inserted");
            } else {
                System.out.println("Value inserted Successfully");
                getLogInfo("new order is added in database");
            }
        } catch (Exception exp) {
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        } finally {
            this.closeSqlConnection(connection);
        }
    }

    @Override
    public int getOrderId(int customerId) {
        int orderId = 0;

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer, Object> params = new HashMap<>();


        params.put(1, customerId);

        try {
            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection, Query.customerOrderId, params);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    orderId = resultSet.getInt(1);
                }
            }
        } catch (Exception exp) {
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        } finally {
            this.closeSqlConnection(connection);
        }
        return orderId;
    }

    @Override
    public void insertOrderHistory(int orderId, int productId, float quantity, float amount, float discount, String date) {
        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer, Object> params = new HashMap<>();


        params.put(1, orderId);
        params.put(2, productId);
        params.put(3, quantity);
        params.put(4, amount);
        params.put(5, discount);
        params.put(6, date);

        try {
            int affectedRows = SqlConnectionServices.prepareAStatement(connection, Query.customerOrderHistoy, params).executeUpdate();

            if (affectedRows == 0) {
                getLogInfo("insert in orderHistory not successfull");
                System.out.println("Value is not inserted");
            } else {
                System.out.println("Value inserted Successfully");
                getLogInfo("insert in orderHistory is successfull");
            }
        } catch (Exception exp) {
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        } finally {
            this.closeSqlConnection(connection);
        }
    }

    @Override
    public void insertLedgerData(Ledger ledger) {
        float balaance = ledger.getDebit() - ledger.getCredit();
        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer, Object> params = new HashMap<>();


        params.put(1, ledger.getAccountId());
        params.put(2, ledger.getCutomerId());
        params.put(3, ledger.getCustomerOrderId());
        params.put(4, ledger.getCredit());
        params.put(5, ledger.getDebit());
        params.put(6, balaance);
        params.put(7, ledger.getDescription());
        params.put(8, ledger.getDate());

        try {

            int affectedRows = SqlConnectionServices.prepareAStatement(connection, Query.custLedgerData, params).executeUpdate();

            if (affectedRows == 0) {
                getLogInfo("ledger data is not inserted successfull");
                System.out.println("Value is not inserted");
            } else {
                getLogInfo("ledger data is inserted successfull");
                System.out.println("Value inserted Successfully");
            }

        } catch (Exception exp) {
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        } finally {
            this.closeSqlConnection(connection);
        }
    }

    @Override
    public ObservableList<Integer> getProductIdList(int recipieId) {

        ObservableList<Integer> list = FXCollections.observableArrayList();
        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer, Object> params = new HashMap<>();

        params.put(1, recipieId);

        try {

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection, Query.getIngredentsId, params);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    list.add(resultSet.getInt(1));
                }
            }

        } catch (SQLException exp) {
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        } finally {
            this.closeSqlConnection(connection);
        }

        return list;
    }

    @Override
    public ObservableList<Integer> getQuantityList(int recipieId) {
        ObservableList<Integer> list = FXCollections.observableArrayList();
        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer, Object> params = new HashMap<>();

        params.put(1, recipieId);

        try {

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection, Query.getProductQuantity, params);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    list.add(resultSet.getInt(1));
                }
            }

        } catch (SQLException exp) {
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        } finally {
            this.closeSqlConnection(connection);
        }

        return list;
    }

    @Override
    public float getStockAvailableQuantity(int ProductId) {
        float availableQuantity = 0;

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer, Object> params = new HashMap<>();


        params.put(1, ProductId);

        try {
            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection, Query.getStockQuantityAvailable, params);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    availableQuantity = resultSet.getInt(1);
                }
            }
        } catch (Exception exp) {
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        } finally {
            this.closeSqlConnection(connection);
        }
        return availableQuantity;
    }

    @Override
    public void insertNewStockHistory(int productId, float totalQuantity, String description, String date) {

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer, Object> params = new HashMap<>();


        params.put(1, productId);
        params.put(2, totalQuantity);
        params.put(3, description);
        params.put(4, date);

        try {

            int affectedRows = SqlConnectionServices.prepareAStatement(connection, Query.newStockHistory, params).executeUpdate();

            if (affectedRows == 0) {
                getLogInfo("values not inserted in new stock history");
                System.out.println("Value is not inserted");
            } else {
                System.out.println("Value inserted Successfully");
                getLogInfo("values inserted in new stock history");
            }

        } catch (Exception exp) {
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        } finally {
            this.closeSqlConnection(connection);
        }
    }

    @Override
    public float getLatestBalance(int customerId) {
        float balance = 0;

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer, Object> params = new HashMap<>();


        params.put(1, customerId);
        try {

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection, Query.previousBalance, params);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    balance = resultSet.getFloat(1);
                }
            }

        } catch (Exception exp) {
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        } finally {
            this.closeSqlConnection(connection);
        }
        return balance;
    }

    @Override
    public void insertExistingLeger(Ledger ledger, int customerID) {
        float previousBlance = 0;

        previousBlance = previousBlance + (ledger.getDebit() - ledger.getCredit());

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer, Object> params = new HashMap<>();


        params.put(1, ledger.getAccountId());
        params.put(2, customerID);
        params.put(3, ledger.getCustomerOrderId());
        params.put(4, ledger.getCredit());
        params.put(5, ledger.getDebit());
        params.put(6, previousBlance);
        params.put(7, ledger.getDescription());
        params.put(8, ledger.getDate());

        try {

            int affectedRows = SqlConnectionServices.prepareAStatement(connection, Query.custLedgerData, params).executeUpdate();

            if (affectedRows == 0) {
                getLogInfo("existing  ledger data not inserted");
                System.out.println("Value is not inserted");
            } else {
                System.out.println("Value inserted Successfully");
                getLogInfo("existing edger data inserted");
            }

        } catch (Exception exp) {
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        } finally {
            this.closeSqlConnection(connection);
        }
    }


    @Override
    public ObservableList<Integer> getAllCustomerOrderId() {

        ObservableList<Integer> list = FXCollections.observableArrayList();
        Connection connection = SqlConnectionServices.getConnection();


        try {

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection, Query.getOrderId, null);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    list.add(resultSet.getInt(1));
                }
            }

        } catch (SQLException exp) {
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        } finally {
            this.closeSqlConnection(connection);
        }

        return list;
    }

    @Override
    public ObservableList<Invoices> getOrderHistory(int orderId) {


        ObservableList<Invoices> list = FXCollections.observableArrayList();

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer, Object> params = new HashMap<>();

        params.put(1, orderId);

        try {

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection, Query.customerOrderHistoryQuery, params);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    list.add(new Invoices(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                            resultSet.getFloat(4), resultSet.getFloat(5), resultSet.getFloat(7), resultSet.getFloat(6),
                            resultSet.getString(8)));
                }
            }

        } catch (Exception exp) {
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        } finally {
            this.closeSqlConnection(connection);
        }
        return list;
    }

    public float getTotalAmount(ObservableList<Invoices> list){
        float total = 0;
        for (int i = 0; i < list.size(); i++) {
            Invoices invoices = list.get(i);
            total = total + invoices.getDiscount();
        }

        return total;
    }

    @Override
    public void printOrderHistory(ObservableList<Invoices> data) throws Exception {
        Invoices invoices = data.get(0);
        Customers customer = new Customers();
        String discount = Float.toString(invoices.getPrice());
        customer.setName(invoices.getCustomerName());
        int orderId = invoices.getInvoiceId();
        InvoicesGenerator invoicesGenerator = new InvoicesGenerator();
        invoicesGenerator.createOrderBill(data,customer,Float.toString(getTotalAmount(data)),Float.toString(getTotalAmount(data)),discount,orderId);

    }


    public void deleteLedgerData(int custOrderId) {
        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer, Object> params = new HashMap<>();

        params.put(1, custOrderId);
        try {

            int affectedRows = SqlConnectionServices.prepareAStatement(connection, Query.deleteLedgerOrder, params).executeUpdate();

            if (affectedRows == 0) {
                getLogInfo("ledger data is not deleted");
                System.out.println("Value is not deleted by orderId");
            } else {
                System.out.println("Value deleted Successfully");
                getLogInfo(" Ledger data is deleted by orderId");
            }

        } catch (Exception exp) {
            exp.printStackTrace();
            getLogWarning(exp.getMessage());
        } finally {
            this.closeSqlConnection(connection);
        }
    }

    public void deleteCustomerOrderData(int custOrderId) {
        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer, Object> params = new HashMap<>();

        params.put(1, custOrderId);
        try {

            int affectedRows = SqlConnectionServices.prepareAStatement(connection, Query.deleteCustomerOrder, params).executeUpdate();

            if (affectedRows == 0) {
                getLogInfo("customer order data is not deleted");
                System.out.println("Value is not deleted by orderId");
            } else {
                System.out.println("Value deleted Successfully");
                getLogInfo("customer order data is deleted by orderId");
            }

        } catch (Exception exp) {
            exp.printStackTrace();
            getLogWarning(exp.getMessage());
        } finally {
            this.closeSqlConnection(connection);
        }
    }

    public void deleteCustOrderHistoryData(int custOrderId) {
        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer, Object> params = new HashMap<>();

        params.put(1, custOrderId);
        try {

            int affectedRows = SqlConnectionServices.prepareAStatement(connection, Query.deleteOrderhistory, params).executeUpdate();

            if (affectedRows == 0) {
                getLogInfo("customer order history data is not deleted");
                System.out.println("Value is not deleted by orderId");
            } else {
                System.out.println("Value deleted Successfully");
                getLogInfo(" customer order history data is deleted by orderId");
            }

        } catch (Exception exp) {
            exp.printStackTrace();
            getLogWarning(exp.getMessage());
        } finally {
            this.closeSqlConnection(connection);
        }
    }

    public void deleteTempData(int custOrderId) {
        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer, Object> params = new HashMap<>();

        params.put(1, custOrderId);
        try {

            int affectedRows = SqlConnectionServices.prepareAStatement(connection, Query.deleteTemp, params).executeUpdate();

            if (affectedRows == 0) {
                getLogInfo("temp data is not deleted");
                System.out.println("Value is not deleted by orderId");
            } else {
                System.out.println("Value deleted Successfully");
                getLogInfo(" temp data is deleted by orderId");
            }

        } catch (Exception exp) {
            exp.printStackTrace();
            getLogWarning(exp.getMessage());
        } finally {
            this.closeSqlConnection(connection);
        }
    }

    public void addCallOrder(int orderId,String date){
        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer, Object> params = new HashMap<>();

        params.put(1, orderId);
        params.put(2, date);
        try {

            int affectedRows = SqlConnectionServices.prepareAStatement(connection, Query.addCallOrder, params).executeUpdate();

            if (affectedRows == 0) {
                getLogInfo("call order data is not added");
                System.out.println("Value is not deleted by orderId");
            } else {
                System.out.println("Value deleted Successfully");
                getLogInfo(" call order data is added");
            }

        } catch (Exception exp) {
            exp.printStackTrace();
            getLogWarning(exp.getMessage());
        } finally {
            this.closeSqlConnection(connection);
        }
    }


    public int getCallOrder(){
        int order = 0;

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer, Object> params = new HashMap<>();



        try {
            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection, Query.getCallOrder, params);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    order = resultSet.getInt(1);
                }
            }
        } catch (Exception exp) {
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        } finally {
            this.closeSqlConnection(connection);
        }
        return order;

    }


    public String getLatestDate(){
        String date = "";

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer, Object> params = new HashMap<>();



        try {
            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection, Query.getLatestDate, null);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null) {
                while (resultSet.next()) {
                    date = resultSet.getString(1);
                }
            }
        } catch (Exception exp) {
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        } finally {
            this.closeSqlConnection(connection);
        }
        return date;
    }



    public void truncateTable(){
        Connection connection = SqlConnectionServices.getConnection();

        try {
            Statement statement = connection.createStatement();
            statement.execute(Query.trucateCallOrder);

            System.out.println("truncated");
            getLogInfo("table truncated");
        }catch (Exception e){
            e.printStackTrace();

            getLogWarning(e.getMessage());
        }finally {
            this.closeSqlConnection(connection);
        }
    }
}