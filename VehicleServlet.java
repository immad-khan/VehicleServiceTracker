package com.vehicleservice.servlet;

import com.vehicleservice.dao.VehicleDAO;
import com.vehicleservice.model.User;
import com.vehicleservice.model.Vehicle;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/vehicles")
public class VehicleServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        
        VehicleDAO vehicleDAO = new VehicleDAO();
        List<Vehicle> vehicles;
        
        if ("ADMIN".equals(user.getRole())) {
            vehicles = vehicleDAO.getAllVehicles();
        } else {
            vehicles = vehicleDAO.getVehiclesByUserId(user.getId());
        }
        
        request.setAttribute("vehicles", vehicles);
        request.getRequestDispatcher("vehicles.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        VehicleDAO vehicleDAO = new VehicleDAO();
        
        if ("add".equals(action)) {
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("user");
            
            Vehicle vehicle = new Vehicle();
            vehicle.setUserId(user.getId());
            vehicle.setMake(request.getParameter("make"));
            vehicle.setModel(request.getParameter("model"));
            vehicle.setYear(Integer.parseInt(request.getParameter("year")));
            vehicle.setLicensePlate(request.getParameter("licensePlate"));
            
            vehicleDAO.addVehicle(vehicle);
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            vehicleDAO.deleteVehicle(id);
        }
        
        response.sendRedirect("vehicles");
    }
}