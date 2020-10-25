package com.cyberxnuke.hello.pages;

import com.cyberxnuke.hello.services.dbDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Logout")
public class Logout extends HttpServlet {

    HttpSession loginSession;
    dbDAO dbObj;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loginSession = request.getSession();
        dbObj = dbDAO.getInstance();

        try {
            boolean loginStatus = (boolean) loginSession.getAttribute("loggedIn");
            if (loginStatus) {
                loginSession.setAttribute("loggedIn", false);
                loginSession.setAttribute("username", null);
                dbObj.closeConnection();
                response.sendRedirect("login.jsp");
            }
        } catch (Exception e) {
//            response.sendRedirect("login.jsp");
            e.fillInStackTrace();
        }
    }
}
