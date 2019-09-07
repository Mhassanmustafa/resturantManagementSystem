package com.system.models;

public class Products {

    private int categoryId;
    private int suppliearId;
    private float sellPrice;
    private String productName;
    private String date;
    private float boughtPrice;
    private String suppliearName;
    private int productId;
    private String categoryName;


    public Products(int productId, String productName,  String suppliearName, float boughtPrice,float sellPrice,String date ) {
        this.sellPrice = sellPrice;
        this.productName = productName;
        this.date = date;
        this.boughtPrice = boughtPrice;
        this.suppliearName = suppliearName;
        this.productId = productId;
    }

    public Products(int categoryId, int suppliearId) {
        this.categoryId = categoryId;
        this.suppliearId = suppliearId;
    }

    public Products() {
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getSuppliearId() {
        return suppliearId;
    }

    public void setSuppliearId(int suppliearId) {
        this.suppliearId = suppliearId;
    }

    public float getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(float sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getBoughtPrice() {
        return boughtPrice;
    }

    public void setBoughtPrice(float boughtPrice) {
        this.boughtPrice = boughtPrice;
    }

    public String getSuppliearName() {
        return suppliearName;
    }

    public void setSuppliearName(String suppliearName) {
        this.suppliearName = suppliearName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Products{" +
                "categoryId=" + categoryId +
                ", suppliearId=" + suppliearId +
                ", sellPrice=" + sellPrice +
                ", productName='" + productName + '\'' +
                ", date='" + date + '\'' +
                ", boughtPrice=" + boughtPrice +
                ", suppliearName='" + suppliearName + '\'' +
                ", productId=" + productId +
                '}';
    }
}
