package com.project.registration;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@SuppressWarnings("serial")
@WebServlet("/quiz-types")
public class backtoquizselection extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Invalidate the session to clear all previous data
        HttpSession session = request.getSession();
        session.invalidate();  // Clears session data

        // Redirect to the quiz selection page
        response.sendRedirect(request.getContextPath() + "/quiz-types.jsp");  // Using context path to make sure it's correct
    }
}

