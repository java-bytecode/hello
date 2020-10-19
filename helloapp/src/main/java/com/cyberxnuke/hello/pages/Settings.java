package com.cyberxnuke.hello.pages;

import com.cyberxnuke.hello.services.checkLogin;
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

@WebServlet(name = "Settings")
public class Settings extends HttpServlet {

    HttpSession loginSession;
    PrintWriter out;
    dbDAO dbObj;
    boolean isCheckNewSignUpFromDB;

    {
        dbObj = dbDAO.getInstance();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get Session
        out = response.getWriter();
        loginSession = request.getSession();
        String username = (String) loginSession.getAttribute("username");

        if(checkLogin.checkLogin(request, response)) {
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String email = request.getParameter("emailAddress");
            try {
                isCheckNewSignUpFromDB = dbObj.checkIfUserExists(username);
                if(!isCheckNewSignUpFromDB) {
                    if (dbObj.updateUserDetails(username, name, description, email)) {
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('Settings Updated!');");
                        out.println("location='settings.jsp';");
                        out.println("</script>");
                    }
                } else {
                    if (dbObj.insertUserDetails(username, name, description, email)) {
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('Your settings have been updated!');");
                        out.println("location='settings.jsp';");
                        out.println("</script>");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            response.sendRedirect("login.jsp");
        }

    }
}
