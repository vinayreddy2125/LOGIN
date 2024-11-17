<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Math Quiz</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f0f0f0;
        }
        .quiz-container {
            background-color: #fff;
            padding: 30px;
            margin: 50px auto;
            width: 75%; /* 3/4 of the page */
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }
        h1 {
            color: #333;
            font-size: 2rem; /* Increased size for title */
        }
        h3 {
            font-size: 1.5rem; /* Increased size for subtitle */
            color: #555;
        }
        form {
            margin-top: 20px;
        }
        label {
            display: block;
            margin: 20px 0;
            font-size: 1.3rem; /* Increased font size for options */
        }
        .feedback {
            font-weight: bold;
            color: green;
            margin-top: 15px;
            font-size: 1.2rem; /* Slightly larger font for feedback */
        }
        .next-button {
            margin-top: 20px;
        }
        /* Header styling for the username */
        .header {
            background-color: #333;
            color: white;
            padding: 10px;
            text-align: right;
            font-size: 1.2rem; /* Increased size for username display */
        }
        button {
            background-color: #4CAF50;
            color: white;
            padding: 12px 25px; /* Increased padding for button */
            font-size: 1.2rem; /* Larger font size for the button */
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 20px;
        }
        button:hover {
            background-color: #45a049;
        }
        #timer {
            font-size: 1.5rem;
            font-weight: bold;
            color: red;
            margin-top: 20px;
        }
    </style>
</head>
<body>

    <!-- Display logged-in username at the top -->
    <div class="header">
        <%
            HttpSession session1 = request.getSession();
            String username = (String) session1.getAttribute("name");
            if (username != null) {
        %>
            Welcome, <%= username %>!
        <%
            } else {
        %>
            Welcome, Guest!
        <%
            }
        %>
    </div>

    <div class="quiz-container">
        <h1>Math Quiz - Question <%= request.getAttribute("questionIndex") %></h1>
        <h3><%= request.getAttribute("title") %></h3>

        <form id="quizForm" action="start-math-quiz" method="post">
            <input type="hidden" name="question_id" value="<%= request.getAttribute("question_id") %>">
            <label>
                <input type="radio" name="answer" value="<%= request.getAttribute("option1") %>" required>
                <%= request.getAttribute("option1") %>
            </label>
            <label>
                <input type="radio" name="answer" value="<%= request.getAttribute("option2") %>">
                <%= request.getAttribute("option2") %>
            </label>
            <label>
                <input type="radio" name="answer" value="<%= request.getAttribute("option3") %>">
                <%= request.getAttribute("option3") %>
            </label>
            <label>
                <input type="radio" name="answer" value="<%= request.getAttribute("option4") %>">
                <%= request.getAttribute("option4") %>
            </label>
            <button type="submit">Submit Answer  Next</button>
        </form>

        <!-- Timer display -->
        <div id="timer">Time Left: 30s</div>

        <!-- Display feedback message -->
        <%
            String feedback = (String) session.getAttribute("feedback");
            if (feedback != null) {
        %>
            <p class="feedback"><%= feedback %></p>
        <%
                session.removeAttribute("feedback"); // Clear feedback after displaying
            }
        %>
    </div>

    <script>
        // Timer functionality
        let timeLeft = 30; // 30 seconds countdown
        const timerElement = document.getElementById('timer');
        
        function updateTimer() {
            timeLeft--;
            if (timeLeft <= 0) {
                document.getElementById('quizForm').submit(); // Auto-submit after time expires
            }
            timerElement.textContent = "Time Left: " + timeLeft + "s";
        }

        setInterval(updateTimer, 1000); // Update timer every second
    </script>
</body>
</html>
