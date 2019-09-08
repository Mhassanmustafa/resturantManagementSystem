package com.system.models;

public class Ledger {
    private float debit;
    private float credit;
    private float balance;
    private int accountId;
    private int cutomerId;
    private int expenseId;
    private int customerOrderId;
    private int stockRecoverId;
    private int employeeId;
    private float bonous;
    private float advance;
    private float quantity;
    private String description;
    private String date;


    public Ledger(float debit, float credit, float balance, int accountId, int cutomerId, int expenseId, int customerOrderId, int stockRecoverId, int employeeId, float bonous, float advance, float quantity, String description, String date) {
        this.debit = debit;
        this.credit = credit;
        this.balance = balance;
        this.accountId = accountId;
        this.cutomerId = cutomerId;
        this.expenseId = expenseId;
        this.customerOrderId = customerOrderId;
        this.stockRecoverId = stockRecoverId;
        this.employeeId = employeeId;
        this.bonous = bonous;
        this.advance = advance;
        this.quantity = quantity;
        this.description = description;
        this.date = date;
    }

    public Ledger(float debit, float credit, float balance) {
        this.debit = debit;
        this.credit = credit;
        this.balance = balance;
    }

    public Ledger() {
    }

    public float getDebit() {
        return debit;
    }

    public void setDebit(float debit) {
        this.debit = debit;
    }

    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getCutomerId() {
        return cutomerId;
    }

    public void setCutomerId(int cutomerId) {
        this.cutomerId = cutomerId;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public int getCustomerOrderId() {
        return customerOrderId;
    }

    public void setCustomerOrderId(int customerOrderId) {
        this.customerOrderId = customerOrderId;
    }

    public int getStockRecoverId() {
        return stockRecoverId;
    }

    public void setStockRecoverId(int stockRecoverId) {
        this.stockRecoverId = stockRecoverId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Ledger{" +
                "debit=" + debit +
                ", credit=" + credit +
                ", balance=" + balance +
                ", accountId=" + accountId +
                ", cutomerId=" + cutomerId +
                ", expenseId=" + expenseId +
                ", customerOrderId=" + customerOrderId +
                ", stockRecoverId=" + stockRecoverId +
                ", employeeId=" + employeeId +
                ", bonous=" + bonous +
                ", advance=" + advance +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
