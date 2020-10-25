package com.cyberxnuke.hello.pages;

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

@WebServlet(name = "Login")
public class Login extends HttpServlet {

    HttpSession loginSession;
    PrintWriter out;
    dbDAO dbObj;
    String username;
    String password;

    {
        dbObj = dbDAO.getInstance();
        username = null;
        password = null;
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        loginSession = request.getSession();

        // Get Session
        loginSession.setAttribute("loggedIn", false);
        loginSession.setAttribute("username", null);

        // Get PrintWriter
        out = response.getWriter();

        // Get Username and Password
        username = request.getParameter("username");
        password = request.getParameter("password");

        // Connect To Database
        try {
            dbObj.connectToDatabase();
            System.out.println("Connected");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // Check Login
        try {
            // Check if username and password is available in the database
            if (dbObj.login(username, password)) {
                response.sendRedirect("profile.jsp");
                loginSession.setAttribute("loggedIn", true);
                loginSession.setAttribute("username", username);
            } else if (username == null || password == null) {
                response.sendRedirect("login.jsp");
            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Username or password is incorrect!');");
                out.println("location='login.jsp';");
                out.println("</script>");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
