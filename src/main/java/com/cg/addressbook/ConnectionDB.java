package com.cg.addressbook;

import java.sql.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ConnectionDB {
    Connection connection = null;

    public Connection getConnection(){
        String jdbcURL = "jdbc:mysql://localhost:3306/addressBookService?useSSL=false";
        String userName = "root";
        String password = "Akhilkumar@1";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Loaded!!");
        }catch (ClassNotFoundException e) {
            throw new IllegalStateException("driver not found in the classpath", e);
        }

        listDrivers();
        try {
            System.out.println("Connecting to the Database " + jdbcURL);
            connection = DriverManager.getConnection(jdbcURL, userName, password);
            System.out.println("Connection was successful");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static void listDrivers() {
        Enumeration<Driver> driverList = DriverManager.getDrivers();
        while(driverList.hasMoreElements()) {
            Driver driverClass = (Driver) driverList.nextElement();
            System.out.println( "  " + driverClass.getClass().getName());
        }
    }
}
