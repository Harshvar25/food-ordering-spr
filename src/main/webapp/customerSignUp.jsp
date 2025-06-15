<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign Up</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="form-container">
        <h1>Create Account</h1>
        <form action="customersignup" method="post">
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" id="name" name="name" required />
            </div>

            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" required />
            </div>

            <div class="form-group">
                <label for="phone">Phone</label>
                <input type="text" id="phone" name="phone" required />
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" required />
            </div>

            <div class="form-group">
                <label for="address">Address</label>
                <input type="text" id="address" name="address" required />
            </div>

            <button type="submit" class="submit-button">Sign Up</button>
        </form>

        <% String signUpResult = (String) request.getAttribute("SIGNUPRESULT");
            if(signUpResult != null){
                if(signUpResult.equals("Yes")){
        %>
        <p class="message success-message">Signed Up Successfully!</p>
        <%
            }else{
        %>
        <p class="message error-message">Failed to Sign Up! Please try again.</p>
        <%
                }
            }
        %>
    </div>
</body>
</html>