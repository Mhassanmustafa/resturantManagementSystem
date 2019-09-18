package com.system.config;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Config {

    static ObservableList<String>  list = FXCollections.observableArrayList();


    public static final int port = 1433;        //db port
    public static final int width = 1350;       //width for stages
    public static final int height = 700;       //height for stages
    public static final String regix = "\\d{0,7}([\\.]\\d{0,4})?";
    public static final Path logsFile = Paths.get("\\resturantManagementSystem\\Reports\\logs");
    public static final Path logFile = Paths.get("\\resturantManagementSystem\\Reports\\Sql");
    public static final String logPath = Paths.get(logsFile.toAbsolutePath().toString(),String.format("logs.txt")).toString();
    public static final String filePath = Paths.get(logFile.toAbsolutePath().toString(),String.format("Config.txt")).toString();
    public static final Path stockDetailPath = Paths.get("\\resturantManagementSystem\\ReportsAndSheets\\StockDetailsReports");  //path of folder where the reports are save
    public static final Font font = FontFactory.getFont(FontFactory.HELVETICA,10, BaseColor.BLACK); //font
    public static final String address = "Shop no 14, 33 civic center Main Plaza Johar Town Lahore."; //shop address
    public static final String shopName = "BIG BNG BRGR"; //shop name
    public static final int alignRight = Element.ALIGN_RIGHT; //align right
    public static final int alignCenter = Element.ALIGN_CENTER; //align left
    public static final String lines = "-------------------------------------------------------------------------------" +
            "---------------------------------------------------"; //decoration
    public static final String rotateLines = "-------------------------------------------------------------------------------" +
            "----------------------------------------------------------------------------------------------------------------"; //decoration
    public static final String contactNumber = "0311-4111029";
    public static final Font boldFont = FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD
            , BaseColor.DARK_GRAY);
    public static final Font simpleFont = FontFactory.getFont(FontFactory.HELVETICA,13, BaseColor.BLACK);
    public static final Path billsPdf = Paths.get("\\resturantManagementSystem\\ReportsAndSheets\\Bills");




    }
