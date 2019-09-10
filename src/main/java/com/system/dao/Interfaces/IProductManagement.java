package com.system.dao.Interfaces;

import com.system.models.Products;
import com.system.models.Recipie;
import com.system.models.Supplier;
import javafx.collections.ObservableList;

public interface IProductManagement  {
    public void addNewProduct(Products product,String categoryName,String supplierName);
    public ObservableList<String> getAllCategoryNames();
    public ObservableList<String> getSuppliearNames();
    public int getSupplierId(String supplierName);
    public int getCategoryId(String categoryName);
    public ObservableList<String> getAllProductsNames();
    public void addSellPrice(Products product,String date);
    public int getProductId (String productName);
    public float getCurrentPrice(String ProductName);
    public void setNewSellPrice(Products product);
    public void addNewCategory(Products product);
    public void addNewSupplier(Supplier supplier);
    public ObservableList<String> getSuppliearPhno();
    public ObservableList<String> getSuppliearInfo(Supplier supplier);
    public ObservableList<String> getRecipieCategorys();
    public void addNewRecipieCategory(String name);
    public ObservableList<String> getRecipieNames();
    public int getRecipieCatId(String catName);
    public void addNewRecipieName(String name,String recipieName);
    public int getRecipieId(String name);
    public void addRecipieSellPrice(float sellPrice , String date,String recipieName);
    public void addRecipeIngredents(Recipie recipie);
    public ObservableList<Integer> getRecipieId();
    public float getRecipeCurrentPrice(Recipie recipie);
    public void setNewRecipieSellPrice(Recipie recipie);
    public float getLatestPurchasePrice(String name);
    public void addnewRecipiePurchase(Recipie recipie);
}
