<%@ page import="com.vehicleservice.model.User" %>
<%
    User navUser = (User) session.getAttribute("user");
%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="dashboard">🚗 Vehicle Service Tracker</a>
        <div class="navbar-nav ms-auto">
            <a class="nav-link" href="dashboard">Dashboard</a>
            <a class="nav-link" href="vehicles">Vehicles</a>
            <a class="nav-link" href="services">Services</a>
            <span class="nav-link text-light">👤 <%= navUser.getUsername() %></span>
            <a class="nav-link" href="logout">Logout</a>
        </div>
    </div>
</nav>