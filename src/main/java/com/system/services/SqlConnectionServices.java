package com.system.services;

import com.system.config.Config;

import java.sql.*;
import java.util.HashMap;

public class SqlConnectionServices {


    //getting connection with sql server
    public static Connection getConnection() {
        String connectionString = String.format(
                "jdbc:sqlserver://%s:1433;"
                        + "database=%s;"
                        + "user=%s;"
                        + "password={%s};"
                , Config.DB_ADDRESS, Config.DB_NAME, Config.DB_USERNAME, Config.DB_PASSWORD);
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
