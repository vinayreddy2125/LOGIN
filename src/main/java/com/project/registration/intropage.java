package com.project.registration;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class quiztypes
 */
@SuppressWarnings("serial")
@WebServlet("/start-quiz")
public class intropage extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward to the quiz types selection page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/quiz-types.jsp");
        dispatcher.forward(request, response);
    }

}

