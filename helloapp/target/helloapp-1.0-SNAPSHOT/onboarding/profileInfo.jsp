<%--
  Created by IntelliJ IDEA.
  User: CyberxNuke
  Date: 10/25/20
  Time: 10:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../styles/menu_login.css" />
    <link rel="stylesheet" href="../styles/onboarding/profileInfoBox.css" />
    <title>Enter Your Profile Information - Hello</title>
</head>
<body>
<div class="navigation">
    <header>
    </header>
</div>
<div class="loginBox">
    <!-- <div class="logocolumn">
        <span class="logo">Hello!</span>
        <p class="logotext">Welcome to the app!</p>
        <p class="desctext">Connect with friends and trainers at Techouts.</p>
    </div>
    <div class="verticallinecolumn verticalLine"></div> -->
    <div class="settings">
        <h1>Profile Information</h1>
        <form class="settingsForm" action="../signupProfileInfo" method="post">
            <div>
                <label for="name">Name</label>
                <input type="text" id="name" name="name" placeholder="Name" required/>
            </div>
            <div>
                <label for="description">Description</label>
                <textarea id="description" name="description" placeholder="Description" required></textarea>
            </div>
            <div>
                <label for="emailAddress">Email Address</label>
                <input type="email" id="emailAddress" name="emailAddress" placeholder="Email" pattern="^[\w.#$]+@[\w]+[.][\w]{2,7}$" required/>
            </div>
            <input type="submit" name="submit"/>
        </form>
    </div>
</div>
<div class="footer"><p>Created By Vineeth</p></div>
<%
    if(request.getParameter("needToCompleteSignupFirst") != null){
        if(Integer.parseInt(request.getParameter("needToCompleteSignupFirst")) == 1) {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Enter your profile information before accessing your profile.');");
            out.println("</script>");
    }
}%>
</body>
</html>
