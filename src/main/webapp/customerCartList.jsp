<%@ page import="java.util.List"%>
<%@ page import="com.cognizant.webapp09.entity.CartItem"%>
<%@ page import="com.cognizant.webapp09.entity.FoodItem"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Cart</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

    <div class="cart-container">
        <h1>Your Cart</h1>

        <%
            List<CartItem> cartList = (List<CartItem>) request.getAttribute("cartList");
            double grandTotal = 0.0;

            if (cartList == null || cartList.isEmpty()) {
        %>
            <p class="empty-cart-message">Your cart is empty.</p>
        <%
            } else {
        %>
        <div class="cart-table-wrapper">
            <table class="cart-table">
                <thead>
                    <tr>
                        <th>Food Name</th>
                        <th>Description</th>
                        <th>Quantity</th>
                        <th>Price per Item (₹)</th>
                        <th>Total (₹)</th>
                        <th>Remove</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (CartItem cartItem : cartList) {
                            FoodItem item = cartItem.getFoodItem();
                            
                            if (item == null) continue; 

                            double total = cartItem.getPriceAtTimeOfAddition() * cartItem.getQuantity();
                            grandTotal += total;
                    %>
                    <tr>
                        <td><%= item.getName() %></td>
                        <td><%= item.getDescription() %></td>
                        <td><%= cartItem.getQuantity() %></td>
                        <td><%= String.format("%.2f", cartItem.getPriceAtTimeOfAddition()) %></td>
                        <td><%= String.format("%.2f", total) %></td>
                        <td class="remove-cell"><a
                            href="deletefromcart?cartitemid=<%= cartItem.getCartItemId()%>" class="remove-link">Remove</a></td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
                <tfoot>
                    <tr class="grand-total-row">
                        <td colspan="4" class="grand-total-label">Grand Total</td>
                        <td class="grand-total-value">₹<%= String.format("%.2f", grandTotal) %></td>
                        <td></td> <%-- Empty cell for alignment --%>
                    </tr>
                </tfoot>
            </table>
        </div>
        <%
            }
        %>

        <% 
            String isRemoved = (String) request.getAttribute("REMOVEDCARTITEM");
            if(isRemoved != null){
                if(isRemoved.equals("Yes")){
        %>
        <div class="message success-message">Item Removed Successfully</div>
        <% 
            }else{
        %>
        <div class="message error-message">Failed to Remove the Food item from Cart! Please try again.</div>
        <%
            }
        }
        %>

        <div class="cart-actions">
            <a href="customerDashboard.jsp" class="dashboard-link">← Back to Dashboard</a>
        </div>
    </div>

</body>
</html>