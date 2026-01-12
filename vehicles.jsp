<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.vehicleservice.model.Vehicle" %>
<%
    List<Vehicle> vehicles = (List<Vehicle>) request.getAttribute("vehicles");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Vehicles</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <%@ include file="navbar.jsp" %>
    
    <div class="container mt-4">
        <h2>My Vehicles</h2>
        
        <!-- Add Vehicle Form -->
        <div class="card mb-4">
            <div class="card-header">Add New Vehicle</div>
            <div class="card-body">
                <form method="post" action="vehicles" class="row g-3">
                    <input type="hidden" name="action" value="add">
                    <div class="col-md-3">
                        <input type="text" name="make" class="form-control" placeholder="Make" required>
                    </div>
                    <div class="col-md-3">
                        <input type="text" name="model" class="form-control" placeholder="Model" required>
                    </div>
                    <div class="col-md-2">
                        <input type="number" name="year" class="form-control" placeholder="Year" required>
                    </div>
                    <div class="col-md-2">
                        <input type="text" name="licensePlate" class="form-control" placeholder="License Plate" required>
                    </div>
                    <div class="col-md-2">
                        <button type="submit" class="btn btn-success w-100">Add</button>
                    </div>
                </form>
            </div>
        </div>
        
        <!-- Vehicles Table -->
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Make</th>
                    <th>Model</th>
                    <th>Year</th>
                    <th>License Plate</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <% if (vehicles != null && !vehicles.isEmpty()) {
                    for (Vehicle v : vehicles) { %>
                        <tr>
                            <td><%= v.getId() %></td>
                            <td><%= v.getMake() %></td>
                            <td><%= v.getModel() %></td>
                            <td><%= v.getYear() %></td>
                            <td><%= v.getLicensePlate() %></td>
                            <td>
                                <form method="post" action="vehicles" style="display:inline;">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="id" value="<%= v.getId() %>">
                                    <button type="submit" class="btn btn-sm btn-danger">Delete</button>
                                </form>
                            </td>
                        </tr>
                    <% }
                } else { %>
                    <tr><td colspan="6" class="text-center">No vehicles found</td></tr>
                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>