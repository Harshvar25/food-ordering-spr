<%@page import="com.cognizant.webapp09.entity.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Dashboard</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

    <div class="dashboard-container">
        <%
        Customer customer = (Customer) session.getAttribute("CUSTOMER");
        if (customer != null) {
        %>
            <h1>Welcome, <%=customer.getName()%>!</h1>
            <nav class="dashboard-nav">
                <a href="viewmenu">View Menu</a>
                <a href="viewcart">View Cart</a>
                <a href="customersignout">Sign Out</a>
            </nav>
        <%
        } else {
        %>
            <div class="message error-message">
                <h2>No Customer found. Please sign in.</h2>
            </div>
        <%
        }
        %>
    </div>

</body>
</html>