<%--
  Created by IntelliJ IDEA.
  User: cyberxnuke
  Date: 10/17/20
  Time: 7:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="checkLogin.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles/profile.css" />
    <link rel="stylesheet" href="styles/menu_loggedin.css" />
    <link rel="stylesheet" href="styles/settings.css" />
    <title>Settings - The Hello App</title>
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
        <a class="logout" href="logout"><button>Logout</button></a>
    </header>
</div>
<!-- Profile -->
<div class="profile">
    <!-- 1st Child of profile -->
    <div class="profileInfo">
        <jsp:include page="/populateProfileInfo" flush="true"></jsp:include>
        <div class="photo">
            <img src=<%
                if(checkLogin.checkLogin(request, response)){
                    dbDAOobj.getProfilePicture(loginSession.getAttribute("username").toString(), out);
                }
            %>/>
        </div>
        <div class="username">
            <%=populateProfileInfo.getName()%>
        </div>
        <div class="description">
            <div class="roletext">
                <span id="roleTextIdentifier">Role</span>
                <span id="divider"></span>
                <span id="roleType"><%=populateProfileInfo.getRole()%></span>
            </div>
            <div id="desc">
                <span><%=populateProfileInfo.getDescription()%></span>
            </div>
        </div>
    </div>
    <!-- 2nd Child of Profile -->
    <div class="settings">
        <h1>Settings</h1>
        <jsp:include page="/populateSettings" flush="true"></jsp:include>
        <form class="settingsForm" action="settings" method="post">
            <div>
                <label for="name">Name</label>
                <input type="text" id="name" name="name" placeholder="Name" value="<%=populateSettings.getName()%>"/>
            </div>
            <div>
                <label for="description">Description</label>
                <textarea id="description" name="description" placeholder="Description"><%=populateSettings.getDescription()%></textarea>
            </div>
            <div>
                <label for="emailAddress">Email Address</label>
                <input type="email" id="emailAddress" name="emailAddress" placeholder="Email" value="<%=populateSettings.getEmail()%>"/>
            </div>
            <input type="submit" name="submit"/>
        </form>
    </div>
</div>
</body>
</html>