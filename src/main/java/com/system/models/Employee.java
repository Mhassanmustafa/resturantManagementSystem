package com.system.models;

public class Employee {


    private int id;
    private String name;
    private String address;
    private String phNo;
    private String emailAddress;
    private String designation;
    private float basicSalary;
    private String date;
    private float bonous;
    private float advance;
    private float credit;
    private float debit;
    private float balance;
    private String desc;
    private String password;

    public Employee(int id, String name, String address, String phNo, String emailAddress, String designation, float basicSalary) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phNo = phNo;
        this.emailAddress = emailAddress;
        this.designation = designation;
        this.basicSalary = basicSalary;
    }

    public Employee(int id, String phNo, String designation, float basicSalary, float bonous, float advance, float credit, float debit, float balance, String desc, String date) {
        this.id = id;
        this.phNo = phNo;
        this.designation = designation;
        this.basicSalary = basicSalary;
        this.date = date;
        this.bonous = bonous;
        this.advance = advance;
        this.credit = credit;
        this.debit = debit;
        this.balance = balance;
        this.desc = desc;
    }

    public Employee(int id, String name, String phNo, String designation, float basicSalary, String date) {
        this.id = id;
        this.name = name;
        this.phNo = phNo;
        this.designation = designation;
        this.basicSalary = basicSalary;
        this.date = date;
    }

    public Employee(int id, String name, String phNo, String designation , String date, float bonous) {
        this.id = id;
        this.name = name;
        this.phNo = phNo;
        this.designation = designation;
        this.date = date;
        this.bonous = bonous;
    }

    public Employee(String address) {
        this.address = address;
    }

    public Employee() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getPhNo() {
        return phNo;
    }

    public void setPhNo(String phNo) {
        this.phNo = phNo;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public float getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(float basicSalary) {
        this.basicSalary = basicSalary;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getBonous() {
        return bonous;
    }

    public void setBonous(float bonous) {
        this.bonous = bonous;
    }

    public float getAdvance() {
        return advance;
    }

    public void setAdvance(float advance) {
        this.advance = advance;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phNo='" + phNo + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", designation='" + designation + '\'' +
                ", basicSalary=" + basicSalary +
                ", date='" + date + '\'' +
                ", bonous=" + bonous +
                ", advance=" + advance +
                ", credit=" + credit +
                ", debit=" + debit +
                ", balance=" + balance +
                ", desc='" + desc + '\'' +
                '}';
    }
}
