<%@ page import="com.cyberxnuke.hello.services.*" %><%--
  Created by IntelliJ IDEA.
  User: cyberxnuke
  Date: 10/17/20
  Time: 7:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="checkLogin.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles/profileview.css"/>
    <link rel="stylesheet" href="styles/menu_loggedin.css"/>
    <title>The Hello App</title>
</head>
<body>
<div class="menu">
    <header>
        <span class="logo">Hello!</span>
        <nav>
            <ul class="nav__links">
                <li><a href="profile.jsp">Profile</a></li>
                <li><a href="friends.jsp">Friends</a></li>
                <li><a href="settings.jsp">Settings</a></li>
            </ul>
        </nav>
        <a class="logout" href="logout">
            <button>Logout</button>
        </a>
    </header>
</div>
<!-- Profile -->
<div class="profile">
    <!-- 1st Child of profile -->
    <div class="profileInfo">
        <jsp:include page="/populateFriendInfo" flush="true"></jsp:include>
        <div class="photo">
            <img src=<%
                if (checkLogin.checkLogin(request, response)) {
                    dbDAOobj.getProfilePicture(populateFriendInfo.getUsername(), out);
                }
            %>/>
        </div>
        <div class="username">
            <%=populateFriendInfo.getName()%>
        </div>
        <div class="description">
            <div class="roletext">
                <span id="roleTextIdentifier">Role</span>
                <span id="divider"></span>
                <span id="roleType"><%=populateFriendInfo.getRole()%></span>
            </div>
            <div id="desc">
                <span><%=populateFriendInfo.getDescription()%></span>
            </div>
        </div>
    </div>
    <!-- 2nd Child of Friends -->
    <div class="friends">
        <% if (checkLogin.checkLogin(request, response)) {
            dbDAOobj.getMutualFriendsList(request.getParameter("userID"), loginSession.getAttribute("username").toString(), out);
        } %>
    </div>
</div>
</body>
</html>
