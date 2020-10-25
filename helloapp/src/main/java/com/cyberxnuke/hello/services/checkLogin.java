package com.cyberxnuke.hello.services;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "checkLogin")
public class checkLogin extends HttpServlet {

    public static boolean checkLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession loginSession;
        loginSession = request.getSession();
        try {
            boolean loginStatus = (boolean) loginSession.getAttribute("loggedIn");
            if (loginStatus) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
