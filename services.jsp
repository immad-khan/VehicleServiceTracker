<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.vehicleservice.model.Service" %>
<%@ page import="com.vehicleservice.model.Vehicle" %>
<%
    List<Service> services = (List<Service>) request.getAttribute("services");
    List<Vehicle> vehicles = (List<Vehicle>) request.getAttribute("vehicles");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Services</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <%@ include file="navbar.jsp" %>
    
    <div class="container mt-4">
        <h2>Service Records</h2>
        
        <!-- Add Service Form -->
        <div class="card mb-4">
            <div class="card-header">Add New Service</div>
            <div class="card-body">
                <form method="post" action="services" class="row g-3">
                    <input type="hidden" name="action" value="add">
                    <div class="col-md-3">
                        <select name="vehicleId" class="form-select" required>
                            <option value="">Select Vehicle</option>
                            <% for (Vehicle v : vehicles) { %>
                                <option value="<%= v.getId() %>">
                                    <%= v.getMake() %> <%= v.getModel() %> (<%= v.getLicensePlate() %>)
                                </option>
                            <% } %>
                        </select>
                    </div>
                    <div class="col-md-2">
                        <input type="text" name="serviceType" class="form-control" placeholder="Service Type" required>
                    </div>
                    <div class="col-md-2">
                        <input type="text" name="description" class="form-control" placeholder="Description">
                    </div>
                    <div class="col-md-1">
                        <input type="number" step="0.01" name="cost" class="form-control" placeholder="Cost" required>
                    </div>
                    <div class="col-md-2">
                        <input type="date" name="serviceDate" class="form-control" required>
                    </div>
                    <div class="col-md-1">
                        <select name="status" class="form-select">
                            <option>PENDING</option>
                            <option>COMPLETED</option>
                        </select>
                    </div>
                    <div class="col-md-1">
                        <button type="submit" class="btn btn-success w-100">Add</button>
                    </div>
                </form>
            </div>
        </div>
        
        <!-- Services Table -->
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Vehicle ID</th>
                    <th>Service Type</th>
                    <th>Description</th>
                    <th>Cost</th>
                    <th>Date</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <% if (services != null && !services.isEmpty()) {
                    for (Service s : services) { %>
                        <tr>
                            <td><%= s.getId() %></td>
                            <td><%= s.getVehicleId() %></td>
                            <td><%= s.getServiceType() %></td>
                            <td><%= s.getDescription() %></td>
                            <td>$<%= String.format("%.2f", s.getCost()) %></td>
                            <td><%= s.getServiceDate() %></td>
                            <td>
                                <span class="badge bg-<%= "COMPLETED".equals(s.getStatus()) ? "success" : "warning" %>">
                                    <%= s.getStatus() %>
                                </span>
                            </td>
                            <td>
                                <form method="post" action="services" style="display:inline;">
                                    <input type="hidden" name="action" value="updateStatus">
                                    <input type="hidden" name="id" value="<%= s.getId() %>">
                                    <select name="status" class="form-select form-select-sm" style="width:auto; display:inline;">
                                        <option>PENDING</option>
                                        <option>COMPLETED</option>
                                    </select>
                                    <button type="submit" class="btn btn-sm btn-primary">Update</button>
                                </form>
                            </td>
                        </tr>
                    <% }
                } else { %>
                    <tr><td colspan="8" class="text-center">No services found</td></tr>
                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>