<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Food Item</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="form-container">
        <h1>Add Food Item</h1>
        <form action="addfooditem" method="post">
            <div class="form-group">
                <label for="foodName">Food Name</label>
                <input type="text" id="foodName" name="name" required />
            </div>

            <div class="form-group">
                <label for="foodDescription">Food Description</label>
                <textarea id="foodDescription" name="description" rows="4" placeholder="Enter the description" required></textarea>
            </div>

            <div class="form-group">
                <label for="foodPrice">Food Price</label>
                <input type="number" id="foodPrice" name="price" min="0" step="0.01" required />
            </div>

            <div class="form-group">
                <label for="foodCategory">Food Category</label>
                <select id="foodCategory" name="category" required>
                    <option value="" disabled selected>Select Category</option>
                    <option value="Appetizer">Appetizer</option>
                    <option value="Main Course">Main Course</option>
                    <option value="Dessert">Dessert</option>
                    <option value="Beverage">Beverage</option>
                    <option value="Side Dish">Side Dish</option>
                    <option value="Snack">Snack</option>
                    <option value="Salad">Salad</option>
                    <option value="Soup">Soup</option>
                </select>
            </div>

            <div class="form-buttons-group">
                <button type="submit" class="submit-button">Add Item</button>
                <a href="admindashboard" class="secondary-button">Back to Dashboard</a>
            </div>

            <%
            String isAdded = (String) request.getAttribute("FOODITEMADDED");
            if (isAdded != null) {
                if (isAdded.equals("Yes")) {
            %>
            <div class="message success-message">Added Successfully</div>
            <%
                } else {
            %>
            <div class="message error-message">Failed to Add Food Item! Please try again.</div>
            <%
                }
            }
            %>
        </form>
    </div>
</body>
</html>