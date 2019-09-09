package com.system.models;

public class Customers {

    private int id;
    private String name;
    private String shopName;
    private String phoneNumber;
    private String emailAddress;
    private String dat;
    private String decription;
    private float credit;
    private float debit;
    private float balance;
    private int OrderId;
    private int vistedShop;

    public Customers(int id, String name, String shopName, String phoneNumber, String emailAddress, String dat) {
        this.id = id;
        this.name = name;
        this.shopName = shopName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.dat = dat;
    }

    public Customers(int id, String name, String shopName, String phoneNumber, String emailAddress, float credit, float debit, float balance) {
        this.id = id;
        this.name = name;
        this.shopName = shopName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.credit = credit;
        this.debit = debit;
        this.balance = balance;
    }

    public Customers(int id, String name,String phoneNumber,float credit, float debit, float balance , String decription , String dat){
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.credit = credit;
        this.debit = debit;
        this.balance = balance;
        this.decription = decription;
        this.dat = dat;
    }

    public Customers(int id, String name, int vistedShop, String phoneNumber, float credit, float debit, float balance , String dat) {
        this.id = id;
        this.name = name;
        this.vistedShop = vistedShop;
        this.phoneNumber = phoneNumber;
        this.credit = credit;
        this.debit = debit;
        this.balance = balance;
        this.dat = dat;
    }

    public Customers(int id, String name, String phoneNumber, int orderId, float credit, float debit, float balance, int vistedShop) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.credit = credit;
        this.debit = debit;
        this.balance = balance;
        OrderId = orderId;
        this.vistedShop = vistedShop;
    }

    public Customers() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public int getVistedShop() {
        return vistedShop;
    }

    public void setVistedShop(int vistedShop) {
        this.vistedShop = vistedShop;
    }

    public String getDat() {
        return dat;
    }

    public void setDat(String dat) {
        this.dat = dat;
    }

    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }

    public float getDebit() {
        return debit;
    }

    public void setDebit(float debit) {
        this.debit = debit;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    @Override
    public String toString() {
        return "Customers{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shopName='" + shopName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", dat='" + dat + '\'' +
                ", decription='" + decription + '\'' +
                ", credit=" + credit +
                ", debit=" + debit +
                ", balance=" + balance +
                '}';
    }
}
