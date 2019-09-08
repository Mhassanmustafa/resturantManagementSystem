package com.system.dao.Interfaces;

import com.system.models.Customers;
import com.system.models.Ledger;
import javafx.collections.ObservableList;

public interface IInvoices {

    public ObservableList<String> getCustomerNames();
    public ObservableList<String> getCustomerPhoneNos();
    public void addnewCustomer(Customers customer);
    public void addNewOrder(int customerId, String date);
    public int getOrderId(int customerId);
    public void insertOrderHistory(int orderId, int productId, float quantity, float amount, float discount, String date);
    public void insertLedgerData(Ledger ledger);
    public ObservableList<Integer> getProductIdList(int recipieId);
    public ObservableList<Integer> getQuantityList(int recipieId);
    public float getStockAvailableQuantity(int ProductId);
    public void insertNewStockHistory (int productId,float totalQuantity,String description,String date);
    public void insertExistingLeger(Ledger ledger, int customerID);
}
