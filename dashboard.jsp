<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.vehicleservice.model.User" %>
<%
    User user = (User) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <%@ include file="navbar.jsp" %>
    
    <div class="container mt-4">
        <h2>Welcome, <%= user.getUsername() %>!</h2>
        <p class="text-muted">Role: <%= user.getRole() %></p>
        
        <div class="row mt-4">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">🚗 Manage Vehicles</h5>
                        <p>Add, view, and manage your vehicles</p>
                        <a href="vehicles" class="btn btn-primary">Go to Vehicles</a>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">🔧 Service History</h5>
                        <p>Track and manage service records</p>
                        <a href="services" class="btn btn-primary">Go to Services</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>