package com.cyberxnuke.hello.services;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "populateFriendInfo")
public class populateFriendInfo extends HttpServlet {

    HttpSession loginSession;
    dbDAO dbObj;
//    boolean checkNewSignUp;

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
                dbObj.connectToDatabase();
                username = dbObj.getUsername(Integer.parseInt(request.getParameter("userID")));
//                checkNewSignUp = dbObj.checkIfUserExists(username);
                ArrayList userDetails = dbObj.getUserDetails(username);
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

    public String getUsername() {
        return username;
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
