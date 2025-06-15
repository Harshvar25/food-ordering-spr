<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="admin-dashboard-container">
        <h1>Welcome, Admin!</h1>
        <nav class="admin-dashboard-nav">
            <a href="addfooditem" class="admin-nav-link">Add Food Item</a>
            <a href="fooditemlist" class="admin-nav-link">Delete Food Item</a>
			<a href="customerlist" class="admin-nav-link">Delete Customer</a>
            <a href="signout" class="admin-nav-link signout-link">Sign Out</a>
        </nav>
    </div>
</body>
</html>