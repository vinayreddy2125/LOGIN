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

@SuppressWarnings("serial")
@WebServlet("/start-math-quiz")
public class MathQuizServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/youtube";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "vinay@21";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Retrieve or initialize session attributes for question index and score
        Integer questionIndex = (Integer) session.getAttribute("questionIndex");
        Integer score = (Integer) session.getAttribute("score");

        // Start quiz or reset if null
        if (questionIndex == null || score == null) {
            questionIndex = 1;
            score = 0;
            session.setAttribute("questionIndex", questionIndex);
            session.setAttribute("score", score);
        }

        // Check if quiz is complete
        if (questionIndex > 15) { // Assuming you have exactly 15 questions
            request.getRequestDispatcher("/quizResult.jsp").forward(request, response);
            return;
        }

        // Fetch the question in sequence based on question index
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM Questions WHERE quiz_id = 1 AND question_id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, questionIndex); // Use questionIndex to get the next question in order
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Set question data as attributes for the JSP
                request.setAttribute("question_id", resultSet.getInt("question_id"));
                request.setAttribute("title", resultSet.getString("title"));
                request.setAttribute("option1", resultSet.getString("option1"));
                request.setAttribute("option2", resultSet.getString("option2"));
                request.setAttribute("option3", resultSet.getString("option3"));
                request.setAttribute("option4", resultSet.getString("option4"));
                request.setAttribute("questionIndex", questionIndex);
                request.setAttribute("score", score);

                // Forward to JSP for question display
                request.getRequestDispatcher("/mathQuiz.jsp").forward(request, response);
            } else {
                // No more questions, forward to result page
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
            String query = "SELECT correct_answer, option1, option2, option3, option4 FROM Questions WHERE question_id = ?";
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
                session.setAttribute("questionIndex", questionIndex + 1); // Move to the next question
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Redirect to doGet method to display the next question or end quiz if finished
        response.sendRedirect("start-math-quiz");
    }

}
