package com.example.jdbcdemo.service;

import java.sql.*;

public class ConnectionManagerJDBC {

    private Connection connection;

    private String url = "jdbc:hsqldb:hsql://localhost/workdb";

    private String createTableDevice = "CREATE TABLE IF NOT EXISTS Device(" +
            "id bigint GENERATED BY DEFAULT AS IDENTITY, " +
            "deviceName varchar(40) UNIQUE NOT NULL, " +
            "screenSize double NOT NULL, " +
            "dateOfRelease date NOT NULL)";

    private String createTableHardware = "CREATE TABLE IF NOT EXISTS Hardware(" +
            "id bigint GENERATED BY DEFAULT AS IDENTITY, " +
            "devicename varchar(40) UNIQUE NOT NULL, " +
            "storage int NOT NULL, " +
            "memory int NOT NULL, " +
            "processor varchar(30) NOT NULL, " +
            "FOREIGN KEY (deviceName) REFERENCES Device(deviceName) ON DELETE CASCADE)";

    private Statement statement;

    public ConnectionManagerJDBC() {
        try {
            connection = DriverManager.getConnection(url);

            statement = connection.createStatement();

            ResultSet rs = connection.getMetaData().getTables(null, null, null,null);
            boolean tablesExists = false;

            while (rs.next()) {
                if ("Device".equalsIgnoreCase(rs.getString("TABLE_NAME")))
                    tablesExists = true;
                break;
            }
            if(!tablesExists){
                statement.executeUpdate(createTableDevice);
                statement.executeUpdate(createTableHardware);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {

        return connection;
    }
}
