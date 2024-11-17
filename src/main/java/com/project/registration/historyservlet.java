package com.project.registration;

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
import java.sql.SQLException;

/**
 * Servlet implementation class historyservlet
 */
@SuppressWarnings("serial")
@WebServlet("/start-history-quiz")
public class historyservlet extends HttpServlet {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/youtube";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "vinay@21";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Integer questionIndex = (Integer) session.getAttribute("questionIndex");
        Integer score = (Integer) session.getAttribute("score");

        if (questionIndex == null || score == null) {
            questionIndex = 1;
            score = 0;
            session.setAttribute("questionIndex", questionIndex);
            session.setAttribute("score", score);
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Fetch question in order using questionIndex
            String query = "SELECT * FROM history WHERE quiz_id = 3 AND question_id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, questionIndex);  // Use questionIndex to get questions in order
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                request.setAttribute("question_id", resultSet.getInt("question_id"));
                request.setAttribute("title", resultSet.getString("title"));
                request.setAttribute("option1", resultSet.getString("option1"));
                request.setAttribute("option2", resultSet.getString("option2"));
                request.setAttribute("option3", resultSet.getString("option3"));
                request.setAttribute("option4", resultSet.getString("option4"));
                request.setAttribute("questionIndex", questionIndex);
                request.setAttribute("score", score);

                request.getRequestDispatcher("/historyquiz.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/quizResult.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Integer questionIndex = (Integer) session.getAttribute("questionIndex");
        Integer score = (Integer) session.getAttribute("score");

        if (questionIndex == null) {
            questionIndex = 1;
            score = 0;
        }

        String userAnswer = request.getParameter("answer");
        int questionId = Integer.parseInt(request.getParameter("question_id"));

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT correct_answer, option1, option2, option3, option4 FROM history WHERE question_id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, questionId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int correctAnswerIndex = resultSet.getInt("correct_answer");
                String correctAnswerText = switch (correctAnswerIndex) {
                    case 1 -> resultSet.getString("option1");
                    case 2 -> resultSet.getString("option2");
                    case 3 -> resultSet.getString("option3");
                    case 4 -> resultSet.getString("option4");
                    default -> null;
                };

                if (correctAnswerText != null && correctAnswerText.equals(userAnswer)) {
                    score++;
                    session.setAttribute("feedback", "Correct!");
                } else {
                    session.setAttribute("feedback", "Incorrect. The correct answer was: " + correctAnswerText);
                }

                session.setAttribute("score", score);
                session.setAttribute("questionIndex", questionIndex + 1); // Increment question index
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("start-history-quiz");
    }
}
