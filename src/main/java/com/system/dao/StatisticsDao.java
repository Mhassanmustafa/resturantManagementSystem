package com.system.dao;

import com.system.Logs.Log;
import com.system.Queries.Query;
import com.system.dao.Interfaces.IStatistics;
import com.system.services.SqlConnectionServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

public class StatisticsDao implements IStatistics {

    public static void getLogInfo (String message){
        try {
            Log log = Log.getInstance();
            log.logger.info(message);
        }catch (Exception exp){
            exp.printStackTrace();
        }

    }

    public static void getLogWarning (String message){
        try {
            Log log = Log.getInstance();
            log.logger.warning(message);
        }catch (Exception exp){
            exp.printStackTrace();
        }

    }

    public void close(Connection connection){
        if(connection != null){
            SqlConnectionServices.closeConnection(connection);
        }
    }

    @Override
    public float getDailySales(String date) {

        float dailySales = 0;
        String[] dateParts = date.split("-");
        String year = dateParts[0];
        String month = dateParts[1];
        String day = dateParts[2];
        int accountId = 1;



        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();


        params.put(1 , accountId);
        params.put(2 , day);
        params.put(3,month);
        params.put(4,year);

        try {

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection, Query.getDailySales,params);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                while (resultSet.next()){
                    dailySales = resultSet.getFloat(1);
                }
            }

        }catch (Exception exp){
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        }finally {
            this.close(connection);
        }
        return dailySales;
    }

    @Override
    public float getDailyProfit(String date) {

        float profit = 0;



        String[] dateParts = date.split("-");
        String year = dateParts[0];
        String month = dateParts[1];
        String day = dateParts[2];
        int accountId = 1;
        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();



        params.put(1, accountId);
        params.put(2,day);
        params.put(3,month);
        params.put(4,year);
        params.put(5,day);
        params.put(6,month);
        params.put(7,year);


        try{

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection,Query.getDailyProfit,params);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                while (resultSet.next()){
                    profit = resultSet.getFloat(1);
                }
            }
        }catch (Exception exp){
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        }finally {
            this.close(connection);
        }

        return profit;
    }

    @Override
    public float getMonthlySales(String date) {

        float sales = 0;
        String[] dateParts = date.split("-");
        String year = dateParts[0];
        String month = dateParts[1];

        int accountId = 1;

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();


        params.put(1, accountId);
        params.put(2,month);
        params.put(3,year);

        try{

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection,Query.getMonthlySale,params);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                while (resultSet.next()){
                    sales = resultSet.getFloat(1);
                }
            }
        }catch (Exception exp){
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        }finally {
            this.close(connection);
        }

        return sales;
    }

    //get yearly profit


    @Override
    public float getMonthlyProfit(String date) {
        int customerAccId = 1;
        int empAccId = 2;

        float sales = 0;
        String[] dateParts = date.split("-");
        String year = dateParts[0];
        String month = dateParts[1];


        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();


        params.put(1,customerAccId);
        params.put(2,month);
        params.put(3,year);
        params.put(4,month);
        params.put(5,year);
        params.put(6,empAccId);
        params.put(7,month);
        params.put(8,year);


        try{

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection,Query.getMonthlyProfit,params);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                while (resultSet.next()){
                    sales = resultSet.getFloat(1);
                }
            }
        }catch (Exception exp){
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        }finally {
            this.close(connection);
        }


        return sales;
    }

    @Override

    public float getYearlySales(String date) {
        float sales =0;

        String[] dateParts = date.split("-");
        String year = dateParts[0];
        int accountId = 1;

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();


        params.put(1,accountId);
        params.put(2,year);

        try{

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection,Query.getYearlySales,params);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                while (resultSet.next()){
                    sales = resultSet.getFloat(1);
                }
            }
        }catch (Exception exp){
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        }finally {
            this.close(connection);
        }


        return sales;
    }

    //get yearly profit

    @Override
    public float getYearlyProfit(String date) {
        float profit = 0;
        int customerAccId = 1;
        int empAccId = 2;

        String[] dateParts = date.split("-");
        String year = dateParts[0];

        Connection connection = SqlConnectionServices.getConnection();
        HashMap<Integer , Object> params = new HashMap<>();


        params.put(1,customerAccId);
        params.put(2,year);
        params.put(3,year);
        params.put(4,empAccId);
        params.put(5,year);


        try{

            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection,Query.getYearlyProfit,params);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                while (resultSet.next()){
                    profit = resultSet.getFloat(1);
                }
            }
        }catch (Exception exp){
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        }finally {
            this.close(connection);
        }

        return profit;
    }

    @Override
    public ObservableList getTotalQuantity() {

        ObservableList list = FXCollections.observableArrayList();

        Connection connection = SqlConnectionServices.getConnection();


        try{
            PreparedStatement preparedStatement = SqlConnectionServices.prepareAStatement(connection,Query.getTotalQuantityData,null);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet != null){
                while (resultSet.next()){
                    list.add(new  PieChart.Data(resultSet.getString(1),resultSet.getDouble(2)));
                }
            }
        }catch (Exception exp){
            getLogWarning(exp.getMessage());
            exp.printStackTrace();
        }finally {
            this.close(connection);
        }

        return list;
    }
}
