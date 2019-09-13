package com.system.models;

public class Recipie {
    private String recipieName;
    private String productName;
    private float quantity;
    private float price;
    private String date;
    private float PurPrice;
    private int recipieID;


    public Recipie(String productName, float quantity, float price ,float PurPrice) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.PurPrice = PurPrice;
    }

    public Recipie(int recipieID ,String productName, float quantity ) {
        this.productName = productName;
        this.quantity = quantity;
        this.recipieID = recipieID;
    }

    public Recipie() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getRecipieName() {
        return recipieName;
    }

    public void setRecipieName(String recipieName) {
        this.recipieName = recipieName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getPurPrice() {
        return PurPrice;
    }

    public void setPurPrice(float purPrice) {
        PurPrice = purPrice;
    }

    public int getRecipieID() {
        return recipieID;
    }

    public void setRecipieID(int recipieID) {
        this.recipieID = recipieID;
    }

    @Override
    public String toString() {
        return "Recipie{" +
                "productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
