<%--
  Created by IntelliJ IDEA.
  User: cyberxnuke
  Date: 10/17/20
  Time: 7:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles/menu_login.css" />
    <link rel="stylesheet" href="styles/loginbox.css" />
    <title>Login - The Hello App</title>
</head>
<body>
<div class="navigation">
    <header>
    </header>
</div>
<div class="loginBox">
    <div class="logocolumn">
        <span class="logo">Hello!</span>
        <p class="logotext">Welcome to the app!</p>
        <p class="desctext">Connect with friends and trainers at Techouts.</p>
    </div>
    <div class="verticallinecolumn verticalLine"></div>
    <div class="logincolumn">
        <h1>Login</h1>
        <form class="login" action="login" method="post">
            <input type="text" id="username" name="username" placeholder="Username" required/>
            <input type="password" id="password" name="password" placeholder="Password" required/>
            <input type="submit" name="login" value="Login" />
        </form>
        <a href="signup.jsp" class="signup">New User?</a>
    </div>
</div>
<div class="footer"><p>Created By Vineeth</p></div>
</body>
</html>
