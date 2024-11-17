<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Select Quiz Type</title>
    <style>
        /* Reset some default styles */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        /* Body styling */
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background: linear-gradient(to right, #9dd5d9, #dfe3b6); /* Updated gradient */
            color: #ffffff;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            flex-direction: column; /* Center the content vertically */
            text-align: center;
        }

        /* Container styling */
        .container {
            padding: 3rem;
            border-radius: 15px;
            background-color: rgba(255, 255, 255, 0.1); /* Slight transparency */
            box-shadow: 0 8px 24px rgba(0, 0, 0, 0.2);
            max-width: 600px;
            width: 90%;
            backdrop-filter: blur(12px);
        }

        /* Heading styling */
        h1 {
            font-size: 3rem;
            margin-bottom: 1.5rem;
            color: #ffffff;
            text-shadow: 2px 2px 6px rgba(0, 0, 0, 0.3);
        }

        /* Subheading */
        .quiz-type {
            font-size: 1.4rem;
            margin-bottom: 2rem;
            color: #f1f1f1;
            font-weight: 500;
        }

        /* Button styling */
        .quiz-buttons-container {
            display: flex;
            flex-direction: column; /* Display buttons vertically */
            align-items: center; /* Center the buttons */
            gap: 20px; /* Space between buttons */
        }

        .quiz-button {
            padding: 1rem 2.5rem;
            margin: 0; /* Remove margin between buttons */
            border-radius: 25px;
            background: linear-gradient(to right, #50cec3, #ff80ab); /* Modern gradient */
            color: #ffffff;
            font-size: 1.2rem;
            font-weight: bold;
            text-decoration: none;
            transition: all 0.4s ease;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
            cursor: pointer;
        }

        .quiz-button:hover {
            background: linear-gradient(to right, #ff80ab, #5ed9de); /* Reverse gradient on hover */
            transform: translateY(-4px);
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.4);
        }

        /* Responsive design */
        @media (max-width: 600px) {
            h1 {
                font-size: 2.5rem;
            }
            .quiz-type {
                font-size: 1.1rem;
            }
            .quiz-button {
                font-size: 1rem;
                padding: 0.8rem 2rem;
            }
        }
    </style>
</head>
<body>

    <div class="container">
        <h1>Choose Your Quiz</h1>
        <p class="quiz-type">Select the quiz type you want to take:</p>
        <div class="quiz-buttons-container">
            <!-- Quiz type buttons displayed vertically -->
            <a href="<%= request.getContextPath() %>/start-math-quiz" class="quiz-button">Java Quiz</a>
            <a href="<%= request.getContextPath() %>/start-science-quiz" class="quiz-button">Science Quiz</a>
            <a href="<%= request.getContextPath() %>/start-history-quiz" class="quiz-button">History Quiz</a>
        </div>
    </div>

</body>
</html>
