package com.system.dao.Interfaces;

import com.system.models.Products;
import com.system.models.StockDetails;
import javafx.collections.ObservableList;

public interface IStockManagement {

    public void addnewStock(Products product);
    public float getAvailableQuantity(Products product);
    public void removeQuantity(Products product);
    public ObservableList<StockDetails> getStockDetailsData();
    public void printStockDetails() throws Exception;
}
