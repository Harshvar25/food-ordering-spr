<%@page import="com.cognizant.webapp09.entity.FoodItem"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Food List</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

    <div class="list-container">
        <a href="admindashboard" class="back-link">← Back to Dashboard</a>
        
        <h1>Food Items List</h1>

        <div class="food-list-table">
            <table>
                <thead>
                    <tr>
                        <th>Food ID</th>
                        <th>Food Name</th>
                        <th>Food Description</th>
                        <th>Food Price</th>
                        <th>Food Category</th>
                        <th>Remove</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                    List<FoodItem> foodList = (List) session.getAttribute("FOODLIST");
                    
                    if(foodList != null && !foodList.isEmpty()){ 
                        for (FoodItem food : foodList) {    
                    %>
                    <tr>
                        <td><%= food.getId() %></td>
                        <td><%= food.getName() %></td>
                        <td><%= food.getDescription() %></td>
                        <td><%= food.getPrice() %></td>
                        <td><%= food.getCategory() %></td>
                        <td class="remove-cell"><a href="delete?foodid=<%= food.getId() %>" class="delete-link">Delete</a></td>
                    </tr>
                    <% 
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="6" class="no-items-message">No food items available.</td>
                    </tr>
                    <%    
                    }
                    %>
                </tbody>
            </table>
        </div>

        <% 
            String isDelete = (String) request.getAttribute("DELETEFOODITEM");
            if(isDelete != null){
                if(isDelete.equals("Yes")){
        %>
        <div class="message success-message">Deleted Successfully</div>
        <% 
            } else {
        %>
        <div class="message error-message">Failed to Delete! Please try again.</div>
        <%
                }
            }
        %>
    </div>
</body>
</html>