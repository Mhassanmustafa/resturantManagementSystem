package com.system.models;

public class Invoices {
    private int invoiceId;
    private String customerName;
    private String productName;
    private float unitPrice;
    private float quantity;
    private float price;
    private float discount;
    private String date;
    private String unit;
    private float totalAmount;

    public Invoices(String productName, String unit, float price, float quantity, float totalAmount) {
        this.productName = productName;
        this.unit = unit;
        this.price = price;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
    }

    public Invoices(int invoiceId, String customerName, String productName, float unitPrice, float quantity, float price, float discount, String date) {
        this.invoiceId = invoiceId;
        this.customerName = customerName;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
        this.date = date;
    }

    public Invoices(String productName, float price, float quantity, float totalAmount) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
    }

    public Invoices() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Invoices{" +
                "invoiceId=" + invoiceId +
                ", customerName='" + customerName + '\'' +
                ", productName='" + productName + '\'' +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", price=" + price +
                ", discount=" + discount +
                ", date='" + date + '\'' +
                ", unit='" + unit + '\'' +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
