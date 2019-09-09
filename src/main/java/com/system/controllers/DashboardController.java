package com.system.controllers;

import animatefx.animation.SlideInDown;
import com.system.config.Config;
import com.system.dao.StatisticsDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private Label monthlySales;
    @FXML
    private Label DailyProfit;
    @FXML
    private Label dailySales;
    @FXML
    private Label yearleySales;
    @FXML
    private PieChart pieChart;
    @FXML
    private AreaChart profitChart;

    //button event for the home button use to change the scene and come to dashBoard
    public void homeButtonEvent(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/DashBoard.fxml"));
        Stage scene2 =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene2.setScene(new Scene(root, Config.width,Config.height));
        scene2.show();
    }

    //button event for the invoice button use to change the scene and goes to invoice scene
    public void invoiceButtonEvent(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Invoice.fxml"));
        Stage scene2 =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene2.setScene(new Scene(root,Config.width,Config.height));
        scene2.show();
    }

    //button event for the product managemet button use to change the scene and goes to product management scene
    public void productManagementButtonEvent(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/ProductManagement.fxml"));
        Stage scene2 =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene2.setScene(new Scene(root,Config.width,Config.height));
        scene2.show();
    }

    //button event for the stock managemet button use to change the scene and goes to stock management scene
    public void stockManagementButtonEvent(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/StockManagement.fxml"));
        Stage scene2 =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene2.setScene(new Scene(root,Config.width,Config.height));
        scene2.show();
    }

    //button event for the account managemet button use to change the scene and goes to account management scene
    public void accManagementButtonEvent(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AccountManagement.fxml"));
        Stage scene2 =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene2.setScene(new Scene(root,Config.width,Config.height));
        scene2.show();
    }

    //button event for the statistic button use to change the scene and goes to statistic scene
    public void statisticsButtonEvent(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Statistics.fxml"));
        Stage scene2 =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene2.setScene(new Scene(root,Config.width,Config.height));
        scene2.show();
    }

    //button event for settings
    public void settingsButtonEvent(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Settings.fxml"));
        Stage scene2 =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene2.setScene(new Scene(root,Config.width,Config.height));
        scene2.show();
    }

    //button event for logout button use to change the scene and goes to logout scene
    public void logoutButtonEvent(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        Stage scene2 =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene2.setScene(new Scene(root,882,543));
        scene2.show();
        new SlideInDown(root).play();
    }

    //label data set according
    public void setLabelData(){

        StatisticsDao statistics = new StatisticsDao();
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "In"));

        dailySales.setText(formatter.format(Double.valueOf(Float.toString(statistics.getDailySales(java.time.LocalDate.now().toString())))));
        monthlySales.setText(formatter.format(Double.valueOf(Float.toString(statistics.getMonthlySales(java.time.LocalDate.now().toString())))));
        yearleySales.setText(formatter.format(Double.valueOf(Float.toString(statistics.getYearlySales(java.time.LocalDate.now().toString())))));
        DailyProfit.setText(formatter.format(Double.valueOf(Float.toString(statistics.getDailyProfit(java.time.LocalDate.now().toString())))));


    }

    //get pieChart Data
    public void getPieChartData(){
        StatisticsDao statistics = new StatisticsDao();
        pieChart.getData().addAll(statistics.getTotalQuantity());
    }

    public void monthlyProfit(){

        StatisticsDao statistics = new StatisticsDao();

        String date = java.time.LocalDate.now().toString();
        String[] dateParts = date.split("-");
        String year = dateParts[0];
        String presentMonth = dateParts[1];

        int month = Integer.parseInt(dateParts[1]);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(year));
        cal.set(Calendar.DAY_OF_YEAR, 1);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName(year);

        for(int i=0 ; i<= Integer.parseInt(presentMonth) ; i++){
            cal.set(Calendar.MONTH, i-1);
            dataSeries1.getData().add(new XYChart.Data(df.format(cal.getTime()),statistics.getMonthlyProfit(df.format(cal.getTime()).toString())));
        }

        profitChart.getData().add(dataSeries1);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setLabelData();
        getPieChartData();
        monthlyProfit();
    }
}
