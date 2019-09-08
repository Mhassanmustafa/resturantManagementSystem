package com.system.dao;

import com.system.Logs.Log;
import com.system.Message.Messages;
import com.system.Queries.Query;
import com.system.dao.Interfaces.IProductManagement;
import com.system.models.Products;
import com.system.models.Recipie;
import com.system.models.Supplier;
import com.system.services.SqlConnectionServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class ProductManagementDao implements IProductManagement  {

    //to close the open sql connections
    public void closeSqlConnection(Connection connection){
        if(connection != null){
            SqlConnectionServices.closeConnection(connection);
        }
    }

    @Override
    public ObservableList<String> getAllCategoryNames() {

        ObservableList<String> list = FXCollections.observableArrayList();
        Connection connection = SqlConnectionServices.getConnection();

        try{

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection, Query.categoryNames,null);
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
    public ObservableList<String> getSuppliearNames() {

        ObservableList<String> list = FXCollections.observableArrayList();
        Connection connection = SqlConnectionServices.getConnection();

        try{

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection, Query.supplierNames,null);
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
    public int getSupplierId(String supplierName) {

        int id = 0;

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();

        params.put(1,supplierName);

        try {

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection,Query.supplierId,params);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                while (resultSet.next()){
                    id = resultSet.getInt(1);
                }
            }
        }catch (SQLException exp){
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }

        return id;
    }

    @Override
    public int getCategoryId(String categoryName) {

        int id = 0;

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();

        params.put(1,categoryName);

        try {

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection,Query.categoryId,params);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                while (resultSet.next()){
                    id = resultSet.getInt(1);
                }
            }
        }catch (SQLException exp){
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }

        return id;
    }

    @Override
    public ObservableList<String> getAllProductsNames() {
        ObservableList<String> list = FXCollections.observableArrayList();
        Connection connection = SqlConnectionServices.getConnection();

        try{

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection, Query.allProductsNames,null);
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
    public int getProductId(String productName) {
        int id = 0;

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();

        params.put(1,productName);

        try {

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection,Query.productId,params);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                while (resultSet.next()){
                    id = resultSet.getInt(1);
                }
            }
        }catch (SQLException exp){
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }

        return id;
    }

    @Override
    public void addSellPrice(Products product, String date) {

        int productId = getProductId(product.getProductName());

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();


        params.put(1,productId);
        params.put(2,product.getSellPrice());
        params.put(3,date);

        try{

            int affectedRows = SqlConnectionServices.prepareAStatement(connection,Query.addSellHistory,params).executeUpdate();

            if(affectedRows == 0){
                System.out.println("sell price not inserted");
            }else {
                System.out.println("selling history inserted");
            }
        }catch (Exception exp){
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }
    }

    @Override
    public void addNewProduct(Products product, String categoryName, String supplierName) {

        int categoryId = getCategoryId(categoryName);
        int suppliearId = getSupplierId(supplierName);

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();


        params.put(1,categoryId);
        params.put(2,suppliearId);
        params.put(3,product.getProductName());

        try{

            int affectedRows = SqlConnectionServices.prepareAStatement(connection,Query.addNewProductQuery,params).executeUpdate();

            if(affectedRows == 0){
                Messages.getWarning("Data is not Inserted");

            }else {
                Messages.getAlert("Data Inserted SuccessFully");

            }
        }catch (Exception exp){
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }

    }

    @Override
    public float getCurrentPrice(String ProductName) {

        float currentProductPrice = 0;
        int productId = getProductId(ProductName);

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();

        params.put(1,productId);

        try{

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection,Query.currentSellingPrice,params);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                while (resultSet.next()){
                    currentProductPrice = resultSet.getFloat(1);
                }
            }

        }catch (Exception exp){
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }

        return currentProductPrice;
    }

    @Override
    public void setNewSellPrice(Products product) {
        int productId = getProductId(product.getProductName());

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();

        params.put(1 , productId);
        params.put(2 , product.getSellPrice());
        params.put(3 , product.getDate());

        try{

            int affectedRows = SqlConnectionServices.prepareAStatement(connection,Query.updateSellingPrice,params).executeUpdate();

            if(affectedRows == 0){
                Messages.getWarning("new Price is not Updated try again");
            }else {
                Messages.getAlert("new Price is successfully updated");
            }

        }catch (Exception exp){
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }
    }

    @Override
    public void addNewCategory(Products product) {

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();


        params.put(1,product.getCategoryName());

        try{

            int affectedRows = SqlConnectionServices.prepareAStatement(connection,Query.newCategoryName,params).executeUpdate();

            if(affectedRows == 0){
                Messages.getWarning("Category is not added in sucessfully");
            }else{
                Messages.getAlert("Category is Successfully Added");
            }

        }catch (Exception exp){
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }
    }

    @Override
    public void addNewSupplier(Supplier supplier) {

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();

        params.put(1 , supplier.getSupplierName());
        params.put(2 , supplier.getPhoneNumber());
        params.put(3 , supplier.getAddress());
        params.put(4 , supplier.getEmail());

        try {

            int affcctedRows = SqlConnectionServices.prepareAStatement(connection,Query.newSupplier,params).executeUpdate();

            if(affcctedRows == 0){
                Messages.getWarning("Suppliear is not Entered ");
            }else {
                Messages.getAlert("Suppliear is Successfully entered.");
            }

        }catch (Exception exp){
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }
    }

    @Override
    public ObservableList<String> getSuppliearPhno() {

        ObservableList<String> list = FXCollections.observableArrayList();
        Connection connection = SqlConnectionServices.getConnection();

        try{

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection, Query.supplierPhNo,null);
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
    public ObservableList<String> getSuppliearInfo(Supplier supplier) {

        ObservableList<String> list = FXCollections.observableArrayList();

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer ,Object> params = new HashMap<>();


        params.put(1,supplier.getSupplierName());

        try{

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection,Query.supplierInfo,params);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                while (resultSet.next()){
                    for(int i = 1 ; i <= 5 ; i++) {
                        list.add(resultSet.getString(i));
                    }
                }
            }

        }catch (Exception exp){
             System.out.println("eror comming check it later");
        }finally {
            this.closeSqlConnection(connection);
        }
        return list;
    }

    @Override
    public ObservableList<String> getRecipieCategorys() {

        ObservableList<String> list = FXCollections.observableArrayList();
        Connection connection = SqlConnectionServices.getConnection();

        try{

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection, Query.recpieCategoryNames,null);
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
    public void addNewRecipieCategory(String name) {

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();


        params.put(1,name);

        try{

            int affectedRows = SqlConnectionServices.prepareAStatement(connection,Query.newRecipieCategory,params).executeUpdate();

            if(affectedRows == 0){
                Messages.getWarning("Category is not added in sucessfully");
            }else{
                Messages.getAlert("Category is Successfully Added");
            }

        }catch (Exception exp){
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }
    }

    @Override
    public ObservableList<String> getRecipieNames() {

        ObservableList<String> list = FXCollections.observableArrayList();
        Connection connection = SqlConnectionServices.getConnection();

        try{

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection, Query.recipieNames,null);
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
    public int getRecipieCatId(String catName) {
        int id = 0;

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();

        params.put(1,catName);

        try {

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection,Query.recipieCatId,params);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                while (resultSet.next()){
                    id = resultSet.getInt(1);
                }
            }
        }catch (SQLException exp){
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }

        return id;
    }

    @Override
    public void addNewRecipieName(String name,String recipieName) {

        int catId = getRecipieCatId(name);

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();

        params.put(1 , catId);
        params.put(2 , recipieName);

        try{

            int affectedRows = SqlConnectionServices.prepareAStatement(connection,Query.addRecipeName,params).executeUpdate();

            if(affectedRows == 0){
                System.out.println("new name is not Updated try again");
            }else {
                System.out.println("new name is successfully updated");
            }

        }catch (Exception exp){
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }

    }

    @Override
    public int getRecipieId(String name) {
        int id = 0;

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();

        params.put(1,name);

        try {

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection,Query.recipieProductId,params);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                while (resultSet.next()){
                    id = resultSet.getInt(1);
                }
            }
        }catch (SQLException exp){
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }

        return id;
    }

    @Override
    public void addRecipieSellPrice(float sellPrice, String date,String recipieName) {

        int recipieId = getRecipieId(recipieName);

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();

        params.put(1 , recipieId);
        params.put(2 , sellPrice);
        params.put(3 , date);

        try{

            int affectedRows = SqlConnectionServices.prepareAStatement(connection,Query.recipeSellPrice,params).executeUpdate();

            if(affectedRows == 0){
                Messages.getWarning("new sellPrice is not Updated try again");
            }else {
                Messages.getAlert("new sellPrice is successfully updated");
            }

        }catch (Exception exp){
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }

    }

    @Override
    public void addRecipeIngredents(Recipie recipie) {

        int recipieId = getRecipieId(recipie.getRecipieName());
        int productId = getProductId(recipie.getProductName());

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();

        params.put(1 , recipieId);
        params.put(2 , productId);
        params.put(3 , recipie.getQuantity());
        params.put(4 , recipie.getDate());

        try{

            int affectedRows = SqlConnectionServices.prepareAStatement(connection,Query.addRecipieIngredents,params).executeUpdate();

            if(affectedRows == 0){
                Messages.getWarning("new Ingredents not Added try again");
            }else {
                Messages.getAlert("new Ingredents Added");
            }

        }catch (Exception exp){
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }
    }

    @Override
    public ObservableList<Integer> getRecipieId() {
        ObservableList<Integer> list = FXCollections.observableArrayList();
        Connection connection = SqlConnectionServices.getConnection();

        try{

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection, Query.recipeNameId,null);
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
    public float getRecipeCurrentPrice(Recipie recipie) {

        float currentPrice = 0;
        int recipieId = getRecipieId(recipie.getRecipieName());
        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();

        params.put(1 , recipieId);

        try {

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection,Query.recipieCurrentPrice,params);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                while (resultSet.next()){
                    currentPrice = resultSet.getInt(1);
                }
            }
        }catch (SQLException exp){
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }

        return currentPrice;
    }

    @Override
    public void setNewRecipieSellPrice(Recipie recipie) {

        int productId = getRecipieId(recipie.getRecipieName());

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();

        params.put(1 , productId);
        params.put(2 , recipie.getPrice());
        params.put(3 , recipie.getDate());

        try{

            int affectedRows = SqlConnectionServices.prepareAStatement(connection,Query.newRecipieSellPrice,params).executeUpdate();

            if(affectedRows == 0){
                Messages.getWarning("new Price is not Updated try again");
            }else {
                Messages.getAlert("new Price is successfully updated");
            }

        }catch (Exception exp){
            exp.printStackTrace();
        }finally {
            this.closeSqlConnection(connection);
        }
    }
}
