<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Sign In</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="form-container">
        <h1>Admin Sign In</h1>
        <form action="adminsignin" method="post">
            <div class="form-group">
                <label for="adminUsername">Username:</label>
                <input type="text" id="adminUsername" name="username" required />
            </div>

            <div class="form-group">
                <label for="adminPassword">Password:</label>
                <input type="password" id="adminPassword" name="password" required />
            </div>

            <button type="submit" class="submit-button">Sign In</button>
        </form>
    </div>
</body>
</html>