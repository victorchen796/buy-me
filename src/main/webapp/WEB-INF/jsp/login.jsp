<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>BuyMe: Login</title>

    <link rel="stylesheet" type="text/css" href="/static/css/styles.css">
    <link rel="stylesheet" type="text/css" href="/static/css/login.css">
</head>
<body>
    <div id="body-div">
        <div id="outer-div">
            <div id="logo-div">
                <a href="/home" id="logo">BuyMe</a>
            </div>
            <div id="inner-div">
                <form action="/login/attempt-login" method="post">
                    <div class="section">
                        <p class="field">Username</p>
                        <input type="text" name="username" required>
                    </div>
                    <div class="section">
                        <p class="field">Password</p>
                        <input type="password" name="password" required>
                    </div>
                    <c:if test="${not empty error}">
                        <p id="login-error">${error}</p>
                    </c:if>
                    <button id="login-button" type="submit" class="login-button">
                        Log In
                    </button>
                    <button id="sign-up-button" type="submit" class="login-button" formaction="/login/attempt-sign-up">
                        Sign Up
                    </button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>