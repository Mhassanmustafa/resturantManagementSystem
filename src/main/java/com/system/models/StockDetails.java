package com.system.models;

public class StockDetails {

    private int id;
    private String productName;
    private String suppliearName;
    private float quantity;
    private float sellingPrice;
    private String suppliearPhoneNo;
    private float boughtPrice;
    private String desc;
    private String date;
    private String date1;

    public StockDetails(int id, String productName, String suppliearName, float quantity, float sellingPrice, String suppliearPhoneNo, float boughtPrice) {
        this.id = id;
        this.productName = productName;
        this.suppliearName = suppliearName;
        this.quantity = quantity;
        this.sellingPrice = sellingPrice;
        this.suppliearPhoneNo = suppliearPhoneNo;
        this.boughtPrice = boughtPrice;
    }

    public StockDetails(int id, String productName, String desc, float quantity, String date) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.desc = desc;
        this.date = date;
    }

    public StockDetails(int id, String productName, float sellingPrice, float boughtPrice, String date) {
        this.id = id;
        this.productName = productName;
        this.sellingPrice = sellingPrice;
        this.boughtPrice = boughtPrice;
        this.date = date;
    }

    public StockDetails(int id, String productName, float boughtPrice, String date, float sellingPrice,String date1) {
        this.id = id;
        this.productName = productName;
        this.sellingPrice = sellingPrice;
        this.boughtPrice = boughtPrice;
        this.date = date;
        this.date1 = date1;
    }

    public StockDetails() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSuppliearName() {
        return suppliearName;
    }

    public void setSuppliearName(String suppliearName) {
        this.suppliearName = suppliearName;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public float getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(float sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getSuppliearPhoneNo() {
        return suppliearPhoneNo;
    }

    public void setSuppliearPhoneNo(String suppliearPhoneNo) {
        this.suppliearPhoneNo = suppliearPhoneNo;
    }

    public float getBoughtPrice() {
        return boughtPrice;
    }

    public void setBoughtPrice(float boughtPrice) {
        this.boughtPrice = boughtPrice;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    @Override
    public String toString() {
        return "StockDetails{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", suppliearName='" + suppliearName + '\'' +
                ", quantity=" + quantity +
                ", sellingPrice=" + sellingPrice +
                ", suppliearPhoneNo='" + suppliearPhoneNo + '\'' +
                ", boughtPrice=" + boughtPrice +
                ", desc='" + desc + '\'' +
                ", date='" + date + '\'' +
                ", date1='" + date1 + '\'' +
                '}';
    }
}
