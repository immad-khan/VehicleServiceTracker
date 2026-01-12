package com.vehicleservice.model;

import java.sql.Date;

public class Service {
    private int id;
    private int vehicleId;
    private String serviceType;
    private String description;
    private double cost;
    private Date serviceDate;
    private String status;

    // Constructors
    public Service() {}

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }
    
    public String getServiceType() { return serviceType; }
    public void setServiceType(String serviceType) { this.serviceType = serviceType; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }
    
    public Date getServiceDate() { return serviceDate; }
    public void setServiceDate(Date serviceDate) { this.serviceDate = serviceDate; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}