package com.cyberxnuke.hello.services;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "populateSettings")
public class populateSettings extends HttpServlet {

    static String name;
    static String description;
    static String email;

    HttpSession loginSession;
    dbDAO dbObj;
    String username;

    {
        dbObj = dbDAO.getInstance();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loginSession = request.getSession();

        if (checkLogin.checkLogin(request, response)) {
            try {
                username = loginSession.getAttribute("username").toString();
                System.out.println("LOGGED IN " + username);
                    ArrayList userDetails = dbObj.getUserDetails(username);
                    name = userDetails.get(0).toString();
                    description = userDetails.get(1).toString();
                    email = userDetails.get(2).toString();
                    System.out.println(name + description + email);
            } catch (Exception e) {
                e.fillInStackTrace();
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    public String getName() {
        if (name == null) {
            name = "Enter Your Name";
        }
        return name;
    }

    public String getDescription() {
        if (description == null) {
            description = "Enter Profile Description";
        }
        return description;
    }

    public String getEmail() {
        if (email == null) {
            email = "Enter Your Email";
        }
        return email;
    }
}
