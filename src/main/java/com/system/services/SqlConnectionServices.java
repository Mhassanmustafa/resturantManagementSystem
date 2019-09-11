package com.system.services;

import com.system.Message.Messages;
import com.system.config.Config;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.sql.*;
import java.util.HashMap;

public class SqlConnectionServices {


    //getting connection with sql server
    public static Connection getConnection(){
        ObservableList<String> list = FXCollections.observableArrayList();
        try {
            File file = new File(Config.filePath);
            if(!Files.exists(Config.logFile)){
                Files.createDirectories(Config.logFile);
                if(!file.exists()){
                    file.createNewFile();
                    Messages.getWarning("Please add server details first in config files and try again");
                }
            }else{

                    FileReader fr = new FileReader(file);
                    BufferedReader br = new BufferedReader(fr);
                    String line;
                    while ((line = br.readLine()) != null) {

                        list.add(line);
                    }
            }


        }catch (Exception e){
            e.printStackTrace();
        }

        String connectionString = String.format(
                "jdbc:sqlserver://%s:1433;"
                        + "database=%s;"
                        + "user=%s;"
                        + "password={%s};"
                ,list.get(2), list.get(3), list.get(0),list.get(1));
        try {
            return DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //to close the sql server connection
    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //sql prepared statement to avoid to sql injection attack
    public static PreparedStatement prepareAStatement(Connection connection, String query, HashMap<Integer, Object> params) {

        PreparedStatement preparedStatement;

        try {
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            // insert parameters in the query;
            if (params != null) {
                params.forEach((key, value) -> {
                    try {
                        if (value instanceof Integer) {
                            // TODO: support for more datatype
                            preparedStatement.setInt(key, (Integer) value);
                        } else if (value instanceof String) {
                            preparedStatement.setString(key, (String) value);
                        } else if (value instanceof Float) {
                            preparedStatement.setFloat(key, (Float) value);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
            }

            return preparedStatement;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
