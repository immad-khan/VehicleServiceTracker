package com.vehicleservice.dao;

import com.vehicleservice.model.Service;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAO {
    
    public List<Service> getServicesByVehicleId(int vehicleId) {
        List<Service> services = new ArrayList<>();
        String sql = "SELECT * FROM services WHERE vehicle_id = ? ORDER BY service_date DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, vehicleId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Service s = new Service();
                s.setId(rs.getInt("id"));
                s.setVehicleId(rs.getInt("vehicle_id"));
                s.setServiceType(rs.getString("service_type"));
                s.setDescription(rs.getString("description"));
                s.setCost(rs.getDouble("cost"));
                s.setServiceDate(rs.getDate("service_date"));
                s.setStatus(rs.getString("status"));
                services.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return services;
    }

    public List<Service> getAllServices() {
        List<Service> services = new ArrayList<>();
        String sql = "SELECT * FROM services ORDER BY service_date DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Service s = new Service();
                s.setId(rs.getInt("id"));
                s.setVehicleId(rs.getInt("vehicle_id"));
                s.setServiceType(rs.getString("service_type"));
                s.setDescription(rs.getString("description"));
                s.setCost(rs.getDouble("cost"));
                s.setServiceDate(rs.getDate("service_date"));
                s.setStatus(rs.getString("status"));
                services.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return services;
    }

    public boolean addService(Service service) {
        String sql = "INSERT INTO services (vehicle_id, service_type, description, cost, service_date, status) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, service.getVehicleId());
            stmt.setString(2, service.getServiceType());
            stmt.setString(3, service.getDescription());
            stmt.setDouble(4, service.getCost());
            stmt.setDate(5, service.getServiceDate());
            stmt.setString(6, service.getStatus());
            
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateServiceStatus(int id, String status) {
        String sql = "UPDATE services SET status = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, status);
            stmt.setInt(2, id);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}