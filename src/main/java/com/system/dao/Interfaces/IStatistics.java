package com.system.dao.Interfaces;

import javafx.collections.ObservableList;

public interface IStatistics {
    public float getDailySales(String date);
    public float getDailyProfit(String date);
    public float getMonthlySales(String date);
    public float getMonthlyProfit(String date);
    public float getYearlySales(String date);
    public float getYearlyProfit(String date);
    public ObservableList getTotalQuantity();
}
