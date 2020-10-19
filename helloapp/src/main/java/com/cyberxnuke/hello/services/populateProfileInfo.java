package com.cyberxnuke.hello.services;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "populateProfileInfo")
public class populateProfileInfo extends HttpServlet {

    HttpSession loginSession;
    dbDAO dbObj;
    boolean checkNewSignUp;

    static String username;
    static String name;
    static String role;
    static String description;

    {
        dbObj = dbDAO.getInstance();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loginSession = request.getSession();

        if (checkLogin.checkLogin(request, response)) {
            try {
                username = loginSession.getAttribute("username").toString();
            } catch (Exception e) {
                //
            }

        try {
            dbObj.connectToDatabase();
            checkNewSignUp = dbObj.checkIfUserExists(username);
            ArrayList userDetails = dbObj.getUserDetails(username, checkNewSignUp);
            name = userDetails.get(0).toString();
            description = userDetails.get(1).toString();
            role = userDetails.get(3).toString();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    } else {
        response.sendRedirect("login.jsp");
    }
}

    public String getName() {
        if (name == null) {
            name = "New User";
        }
        return name;
    }

    public String getDescription() {
        if (description == null) {
            description = "HELLO NEW USER!";
        }
        return description;
    }

    public String getRole() {
        if (role == null) {
            role = "UNKNOWN";
        }
        return role;
    }


}