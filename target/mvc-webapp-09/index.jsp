<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Sign In</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="form-container">
        <h1>Customer Sign In</h1>
        <form action="customersignin" method="post">
            <div class="form-group">
                <label for="signinEmail">Email Address:</label>
                <input type="text" id="signinEmail" name="email" required />
            </div>

            <div class="form-group">
                <label for="signinPassword">Password:</label>
                <input type="password" id="signinPassword" name="password" required />
            </div>
            
            <div>
            	<label>Don't have an Account? <a href="customersignup"> Sign Up here </a> </label>
            </div>

            <button type="submit" class="submit-button">Sign In</button>
        </form>

        <% String signInResult = (String) request.getAttribute("SIGNINRESULT");
            if(signInResult != null){
                if(signInResult.equals("Yes")){
        %>
        <p class="message success-message">Signed In Successfully!</p>
        <%
            }else{
        %>
        <p class="message error-message">Failed to Sign In! Please try again.</p>
        <%
                }
            }
        %>
    </div>
</body>
</html>