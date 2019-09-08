package com.system.dao;

import com.system.Queries.Query;
import com.system.dao.Interfaces.IInvoices;
import com.system.models.Customers;
import com.system.models.Ledger;
import com.system.services.SqlConnectionServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class InvoicesDao implements IInvoices {

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
                System.out.println("data is not inserted");
            }else {
                System.out.println("Data inserted Successfully");
            }

        }catch (Exception exp){
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
                System.out.println("Value is not inserted");
            }else {
                System.out.println("Value inserted Successfully");
            }
        }catch (Exception exp){
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
                System.out.println("Value is not inserted");
            }else {
                System.out.println("Value inserted Successfully");
            }
        }catch (Exception exp){
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
                System.out.println("Value is not inserted");
            }else {
                System.out.println("Value inserted Successfully");
            }

        }catch (Exception exp){
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
                System.out.println("Value is not inserted");
            }else {
                System.out.println("Value inserted Successfully");
            }

        }catch (Exception exp){
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }
    }

    @Override
    public void insertExistingLeger(Ledger ledger, int customerID) {

    }
}
