// src/main/java/com/ums/DatabaseConnectionManager.java
package com.ums;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager {

    // 1. Static instance of the class
    private static DatabaseConnectionManager instance;

    // 2. Private constructor to prevent external instantiation
    private DatabaseConnectionManager() {
        // Private constructor
    }

    // Database credentials (replace with your actual database details)
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ums_db"; // Example: ums_db is your database name
    private static final String DB_USER = "root"; // Your MySQL username
    private static final String DB_PASSWORD = "root"; // Your MySQL password

    private Connection connection;

    // 3. Public static method to get the single instance
    public static DatabaseConnectionManager getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnectionManager.class) {
                if (instance == null) { // Double-checked locking for thread safety
                    instance = new DatabaseConnectionManager();
                }
            }
        }
        return instance;
    }

    // Method to establish database connection
    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                // Ensure the JDBC driver is loaded (optional for modern JDBC, but good practice)
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                System.out.println("Database connection established.");
            } catch (ClassNotFoundException e) {
                System.err.println("MySQL JDBC Driver not found. Make sure it's in your classpath.");
                e.printStackTrace();
                throw new SQLException("JDBC Driver not found", e);
            }
        }
        return connection;
    }

    // Method to close database connection
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.err.println("Error closing database connection: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    // Example of how to use the connection (for testing)
    public void testConnection() {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Test Connection Successful! Connected to: " + conn.getMetaData().getURL());
            } else {
                System.out.println("Test Connection Failed! Connection is null.");
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception during test connection: " + e.getMessage());
            e.printStackTrace();
        }
    }
}