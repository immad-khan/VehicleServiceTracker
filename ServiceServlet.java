package com.vehicleservice.servlet;

import com.vehicleservice.dao.ServiceDAO;
import com.vehicleservice.dao.VehicleDAO;
import com.vehicleservice.model.Service;
import com.vehicleservice.model.User;
import com.vehicleservice.model.Vehicle;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/services")
public class ServiceServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        
        VehicleDAO vehicleDAO = new VehicleDAO();
        ServiceDAO serviceDAO = new ServiceDAO();
        
        List<Vehicle> vehicles;
        List<Service> services;
        
        if ("ADMIN".equals(user.getRole())) {
            vehicles = vehicleDAO.getAllVehicles();
            services = serviceDAO.getAllServices();
        } else {
            vehicles = vehicleDAO.getVehiclesByUserId(user.getId());
            services = serviceDAO.getAllServices(); // Filter in JSP
        }
        
        request.setAttribute("vehicles", vehicles);
        request.setAttribute("services", services);
        request.getRequestDispatcher("services.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        ServiceDAO serviceDAO = new ServiceDAO();
        
        if ("add".equals(action)) {
            Service service = new Service();
            service.setVehicleId(Integer.parseInt(request.getParameter("vehicleId")));
            service.setServiceType(request.getParameter("serviceType"));
            service.setDescription(request.getParameter("description"));
            service.setCost(Double.parseDouble(request.getParameter("cost")));
            service.setServiceDate(Date.valueOf(request.getParameter("serviceDate")));
            service.setStatus(request.getParameter("status"));
            
            serviceDAO.addService(service);
        } else if ("updateStatus".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String status = request.getParameter("status");
            serviceDAO.updateServiceStatus(id, status);
        }
        
        response.sendRedirect("services");
    }
}