
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quiz Result</title>
      <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin: 0;
            padding: 0;
            background-color: #f0f0f0;
        }
        .result-container {
            background-color: #fff;
            padding: 30px;
            margin: 50px auto;
            max-width: 500px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }
        h1 {
            color: #333;
        }
        p {
            font-size: 18px;
        }
        .back-button {
            display: inline-block;
            padding: 10px 20px;
            margin-top: 20px;
            text-decoration: none;
            color: #fff;
            background-color: #007bff;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <div class="result-container">
        <h1>Quiz Completed!</h1>
        <p>Your score: <%= session.getAttribute("score") %> out of 15</p>
        <a href="<%= request.getContextPath() %>/quiz-types" class="back-button">Back to Quiz Selection</a>
    </div>
</body>
</html>
