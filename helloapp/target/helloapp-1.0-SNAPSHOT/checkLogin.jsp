<%@ page import="com.cyberxnuke.hello.services.*" %>
<%--
  Created by IntelliJ IDEA.
  User: vineethpenugonda
  Date: 10/18/20
  Time: 12:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HttpSession loginSession;
    populateSettings populateSettings;
    populateProfileInfo populateProfileInfo;
    populateFriendInfo populateFriendInfo;
    dbDAO dbDAOobj = dbDAO.getInstance();

    {
        populateSettings = new populateSettings();
        populateProfileInfo = new populateProfileInfo();
        populateFriendInfo = new populateFriendInfo();
    }

    loginSession = request.getSession();
    try {
        boolean loginStatus = (boolean) loginSession.getAttribute("loggedIn");
        boolean newUserSignUp = dbDAOobj.checkIfUserInsertedSettings(loginSession.getAttribute("username").toString());

        if(!newUserSignUp && !request.getRequestURI().endsWith("profileInfo.jsp")){
            response.sendRedirect("onboarding/profileInfo.jsp?needToCompleteSignupFirst=1");
        }

        if (!loginStatus) {
            response.sendRedirect("login.jsp");
        }
    } catch (Exception e) {
        // response.sendRedirect("login.jsp");
    }
%>
<html>
<head>
</head>
<body>

</body>
</html>
