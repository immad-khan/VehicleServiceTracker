
package com.vehicleservice.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String URL = "jdbc:h2:mem:vehicledb;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    static {
        try {
            Class.forName("org.h2.Driver");
            initDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private static void initDatabase() {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            
            // Users table
            stmt.execute("CREATE TABLE IF NOT EXISTS users (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "username VARCHAR(50) UNIQUE NOT NULL, " +
                    "password VARCHAR(100) NOT NULL, " +
                    "email VARCHAR(100), " +
                    "role VARCHAR(20) DEFAULT 'CUSTOMER')");

            // Vehicles table
            stmt.execute("CREATE TABLE IF NOT EXISTS vehicles (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "user_id INT, " +
                    "make VARCHAR(50), " +
                    "model VARCHAR(50), " +
                    "year INT, " +
                    "license_plate VARCHAR(20) UNIQUE, " +
                    "FOREIGN KEY (user_id) REFERENCES users(id))");

            // Services table
            stmt.execute("CREATE TABLE IF NOT EXISTS services (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "vehicle_id INT, " +
                    "service_type VARCHAR(100), " +
                    "description TEXT, " +
                    "cost DECIMAL(10,2), " +
                    "service_date DATE, " +
                    "status VARCHAR(20) DEFAULT 'PENDING', " +
                    "FOREIGN KEY (vehicle_id) REFERENCES vehicles(id))");

            // Insert default admin user
            stmt.execute("INSERT INTO users (username, password, email, role) " +
                    "VALUES ('admin', 'admin123', 'admin@vehicle.com', 'ADMIN')");
            
            // Insert sample customer
            stmt.execute("INSERT INTO users (username, password, email, role) " +
                    "VALUES ('customer', 'customer123', 'customer@vehicle.com', 'CUSTOMER')");

            System.out.println("✅ Database initialized successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}