package com.system.config;

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

}
