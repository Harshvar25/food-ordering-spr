<%@page import="com.cognizant.webapp09.entity.Customer"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer List</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

    <div class="list-container">
        <a href="admindashboard" class="back-link">← Back to Dashboard</a>
        
        <h1>Customer List</h1>

        <div class="food-list-table">
            <table>
                <thead>
                    <tr>
                        <th>Customer ID</th>
                        <th>Customer Name</th>
                        <th>Customer Email</th>
                        <th>Phone</th>
                        <th>Address</th>
                        <th>Remove</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                    List<Customer> customerList = (List) session.getAttribute("CUSTOMERLIST");
                    
                    if(customerList != null && !customerList.isEmpty()){ 
                        for (Customer customer : customerList) {    
                    %>
                    <tr>
                        <td><%= customer.getCustomerId() %></td>
                        <td><%= customer.getName() %></td>
                        <td><%= customer.getEmail() %></td>
                        <td><%= customer.getPhone() %></td>
                        <td><%= customer.getAddress()%></td>
                        <td class="remove-cell"><a href="deletecustomer?customerid=<%= customer.getCustomerId() %>" class="delete-link">Delete</a></td>
                    </tr>
                    <% 
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="6" class="no-items-message">No Customer available.</td>
                    </tr>
                    <%    
                    }
                    %>
                </tbody>
            </table>
        </div>

        <% 
            String isDelete = (String) request.getAttribute("DELETECUSTOMER");
            if(isDelete != null){
                if(isDelete.equals("Yes")){
        %>
        <div class="message success-message">Removed Successfully</div>
        <% 
            } else {
        %>
        <div class="message error-message">Failed to Removed! Please try again.</div>
        <%
                }
            }
        %>
    </div>
</body>
</html>