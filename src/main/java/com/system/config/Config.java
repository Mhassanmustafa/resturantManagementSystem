package com.system.config;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Config {

    public static final String DB_USERNAME = "sa";  //database login
    public static final String DB_PASSWORD = "123";   //database login password
    public static final String DB_ADDRESS = "MHASSAN-PC"; //database server address
    public static final String DB_NAME = "data_database";   //database or schema name
    public static final int port = 1433;        //db port
    public static final int width = 1350;       //width for stages
    public static final int height = 700;       //height for stages
    public static final String regix = "\\d{0,7}([\\.]\\d{0,4})?";
    public static final Path logsFile = Paths.get("\\resturantManagementSystem\\Reports\\logs");
    public static final Path stockDetailPath = Paths.get("\\resturantManagementSystem\\ReportsAndSheets\\StockDetailsReports");  //path of folder where the reports are save
    public static final Font font = FontFactory.getFont(FontFactory.HELVETICA,10, BaseColor.BLACK); //font
    public static final String address = "House 44 street 20 A chaklala scheme 3 rawalpindi"; //shop address
    public static final String shopName = "SK Enterprises"; //shop name
    public static final int alignRight = Element.ALIGN_RIGHT; //align right
    public static final int alignCenter = Element.ALIGN_CENTER; //align left
    public static final String lines = "-------------------------------------------------------------------------------" +
            "---------------------------------------------------"; //decoration
    public static final String rotateLines = "-------------------------------------------------------------------------------" +
            "----------------------------------------------------------------------------------------------------------------"; //decoration
    public static final String contactNumber = "Contact no : 0303-5992887";
    public static final Font boldFont = FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD
            , BaseColor.DARK_GRAY);
    public static final Font simpleFont = FontFactory.getFont(FontFactory.HELVETICA,13, BaseColor.BLACK);
    public static final Path billsPdf = Paths.get("\\resturantManagementSystem\\ReportsAndSheets\\Bills");

}
