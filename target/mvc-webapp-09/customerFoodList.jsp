<%@page import="com.cognizant.webapp09.entity.FoodItem"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Food Menu</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="menu-container">
        <a href="customerdasboard" class="back-link">Back to Customer Dashboard</a>
        
        <h1>Our Delicious Menu</h1>

        <div class="food-list-table">
            <table>
                <thead>
                    <tr>
                        <th>Food ID</th>
                        <th>Food Name</th>
                        <th>Food Description</th>
                        <th>Food Price</th>
                        <th>Food Category</th>
                        <th>Add to Cart</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                    List<FoodItem> foodList = (List) session.getAttribute("FOODLIST");

                    if (foodList != null && !foodList.isEmpty()) { // Added check for empty list
                        for (FoodItem food : foodList) {
                    %>
                    <tr>
                        <td><%=food.getId()%></td>
                        <td><%=food.getName()%></td>
                        <td><%=food.getDescription()%></td>
                        <td><%=food.getPrice()%></td>
                        <td><%=food.getCategory()%></td>
                        <td>
                            <form action="addtocart" method="post" class="add-to-cart-form">
                                <input type="hidden" name="id" value="<%=food.getId()%>" />
                                <input type="number" name="quantity" value="1" min="1" class="quantity-input" />
                                <button type="submit" class="add-to-cart-button">Add to Cart</button>
                            </form>
                        </td>
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
        String successMessage = (String) request.getAttribute("successMessage");
        String errorMessage = (String) request.getAttribute("errorMessage");

        if (successMessage != null) {
        %>
        <div class="message success-message"><%=successMessage%></div>
        <%
        } else if (errorMessage != null) {
        %>
        <div class="message error-message"><%=errorMessage%></div>
        <%
        }
        %>
    </div>
</body>
</html>