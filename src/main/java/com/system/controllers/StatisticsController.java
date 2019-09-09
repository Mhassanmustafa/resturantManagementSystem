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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;


public class StatisticsController implements Initializable {

    //tab daily statistics
    @FXML
    private TextField profitField;
    @FXML
    private BarChart dailyChart;
    @FXML
    private CategoryAxis dateAxis;
    @FXML
    private NumberAxis SalesAxis;
    @FXML
    private DatePicker DailyDate;

    //tab Monthly statistics
    @FXML
    private TextField profitText;
    @FXML
    private BarChart graphMonth;
    @FXML
    private DatePicker DailyDateMonth;

    //tab year statistics
    @FXML
    private TextField profitField3;
    @FXML
    private BarChart chart3;
    @FXML
    private DatePicker datePicker3;

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

    public void getGraphData(){

        StatisticsDao statistics = new StatisticsDao();

        String date = java.time.LocalDate.now().toString();
        String[] dateParts = date.split("-");
        String year = dateParts[0];
        String presentDate = dateParts[2];

        int month = Integer.parseInt(dateParts[1]);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName(year);

        for(int i=0 ; i<= Integer.parseInt(presentDate) ; i++){
            cal.set(Calendar.DAY_OF_MONTH, i);
            dataSeries1.getData().add(new XYChart.Data(df.format(cal.getTime()),statistics.getDailySales(df.format(cal.getTime()).toString())));
        }

        dailyChart.getData().add(dataSeries1);

    }

    public void datePickerAction(){
        StatisticsDao statistics = new StatisticsDao();
        profitField.setText(Float.toString(statistics.getDailyProfit(DailyDate.getValue().toString())));
    }


    public void getGraph2Data(){
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
            dataSeries1.getData().add(new XYChart.Data(df.format(cal.getTime()),statistics.getMonthlySales(df.format(cal.getTime()).toString())));
        }

        graphMonth.getData().add(dataSeries1);
    }

    public void datePicker2Action(){
        StatisticsDao statistics = new StatisticsDao();
        profitText.setText(Float.toString(statistics.getMonthlyProfit(DailyDateMonth.getValue().toString())));
    }

    //set values of chart3

    public void getYearlySalesChart(){
        StatisticsDao statistics = new StatisticsDao();

        String date = java.time.LocalDate.now().toString();
        String[] dateParts = date.split("-");
        String year = dateParts[0];
        String presentyear = dateParts[0];

        int month = Integer.parseInt(dateParts[1]);

        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, -1); //one year back

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName(year);

        dataSeries1.getData().add(new XYChart.Data(df.format(c.getTime()),statistics.getYearlySales(df.format(c.getTime()).toString())));
        dataSeries1.getData().add(new XYChart.Data(date,statistics.getYearlySales(date)));

        chart3.getData().add(dataSeries1);
    }
    public void datePicker3Action(){
        StatisticsDao statistics = new StatisticsDao();
        profitField3.setText(Float.toString(statistics.getYearlyProfit(datePicker3.getValue().toString())));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getGraphData();
        getGraph2Data();
        getYearlySalesChart();
    }
}
