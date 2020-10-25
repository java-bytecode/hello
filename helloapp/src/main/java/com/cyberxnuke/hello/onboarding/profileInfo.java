package com.cyberxnuke.hello.onboarding;

import com.cyberxnuke.hello.services.dbDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "profileInfo")
public class profileInfo extends HttpServlet {

    HttpSession loginSession;
    PrintWriter out;
    String username;
    String name;
    String description;
    String email;
    dbDAO dbObj;

    {
        dbObj = dbDAO.getInstance();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        loginSession = request.getSession();

        // Get PrintWriter
        out = response.getWriter();

        username = loginSession.getAttribute("username").toString();

        name = request.getParameter("name");
        description = request.getParameter("description");
        email = request.getParameter("emailAddress");

        // Signup
        try {
            // Check if username and password is available in the database
            if (dbObj.checkIfUserExists(username)) {
                if (dbObj.insertUserDetails(username, name, description, email)) {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Thank You for signing up. You need to login again!');");
                    out.println("location='login.jsp';");
                    out.println("</script>");
                } else {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Please try again later!');");
                    out.println("location='login.jsp';");
                    out.println("</script>");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
