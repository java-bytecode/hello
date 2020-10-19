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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "Signup")
public class Signup extends HttpServlet {

    HttpSession loginSession;
    PrintWriter out;
    String username;
    String password;
    dbDAO dbObj;
    Matcher matchUsername;
    Matcher matchPassword;

    {
        dbObj = dbDAO.getInstance();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        loginSession = request.getSession();

        // Get PrintWriter
        out = response.getWriter();

        username = request.getParameter("username");
        password = request.getParameter("password");

        // Connect To Database
        try {
            if(dbObj.isDatabaseConnected()) {
                dbObj.connectToDatabase();
                System.out.println("Connected from signup.");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // Username should of minimum 5 characters
        Pattern pattern = Pattern.compile("^[\\w@.#]{5,}$");
        try {
            matchUsername = pattern.matcher(username);
            matchPassword = pattern.matcher(password);
        } catch (Exception e){
            response.sendRedirect("signup.jsp");
        }

        // Signup
        try {
            // Check if username and password is available in the database
            if(dbObj.checkIfUserExists(username)){
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Username is already taken!');");
                out.println("location='signup.jsp';");
                out.println("</script>");
            } else if(username == null){
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Username cannot be empty! How do you think you will login?');");
                out.println("location='signup.jsp';");
                out.println("</script>");
            } else if(password == null){
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Password cannot be empty! How do you think you will login?');");
                out.println("location='signup.jsp';");
                out.println("</script>");
            } else if(!matchUsername.find() || !matchPassword.find()){
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Username and Password should be of minimum 5 characters each and _@# special characters are only allowed. Try again!');");
                out.println("location='signup.jsp';");
                out.println("</script>");
            } else {
                if(dbObj.signup(username, password))
                {
                    loginSession.setAttribute("loggedIn", true);
                    loginSession.setAttribute("username", username);
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Thank You for signing up! You will now be redirected to the settings page.');");
                    out.println("location='settings.jsp';");
                    out.println("</script>");
                } else {
                    loginSession.setAttribute("loggedIn", false);
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Please try again later!');");
                    out.println("location='settings.jsp';");
                    out.println("</script>");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
