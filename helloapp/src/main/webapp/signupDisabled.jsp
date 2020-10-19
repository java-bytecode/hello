<%--
  Created by IntelliJ IDEA.
  User: vineethpenugonda
  Date: 10/18/20
  Time: 8:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Signup is disabled! - The Hello App</title>
</head>
<body>
<% out.println("<script type=\"text/javascript\">");
    out.println("alert('Signup is currently disabled!');");
    out.println("location='login.jsp';");
    out.println("</script>"); %>
</body>
</html>
