package com.project.registration;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/login")
public class login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname = request.getParameter("username");
        String upwd = request.getParameter("password");
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = null;

        if (uname == null || uname.equals("")) {
            request.setAttribute("status", "Invalidname");
            dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
            return; // Stop further execution
        }
        if (upwd == null || upwd.equals("")) {
            request.setAttribute("status", "inavlidpassword");
            dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
            return; // Stop further execution
        }

        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/youtube", "root", "vinay@21");
            PreparedStatement pst = con.prepareStatement("SELECT * FROM users WHERE uname=? AND upwd=?");
            pst.setString(1, uname);
            pst.setString(2, upwd);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                session.setAttribute("name", rs.getString("uname"));
                dispatcher = request.getRequestDispatcher("intropage.jsp");
            } else {
                request.setAttribute("status", "failed");
                dispatcher = request.getRequestDispatcher("login.jsp");
            }
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("status", "error");
            dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response); // Forward on error
        } 
    }
}
