
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome to Quiz Application</title>
    <style>
        /* Reset some default styles */
        * {
            margin: 0;
            padding: 0;
        }

        /* Body styling */
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background: linear-gradient(to right, #ef6cd7, #dbeb89); /* Updated gradient colors */
            color: #ffffff;
        }

        /* Container styling */
        .container {
            text-align: center;
            padding: 2.5rem;
            border-radius: 12px;
            background-color: rgba(255, 255, 255, 0.15); /* Slightly darker opacity for better contrast */
            box-shadow: 0 6px 18px rgba(0, 0, 0, 0.3);
            max-width: 500px;
            width: 90%;
            backdrop-filter: blur(10px); /* Adds a nice frosted glass effect */
        }

        /* Heading and welcome text */
        h1 {
            font-size: 2.8rem;
            margin-bottom: 1rem;
            color: #ffffff; /* Stand out on gradient */
            text-shadow: 1px 1px 4px rgba(0, 0, 0, 0.3); /* Subtle shadow for depth */
        }

        p {
            font-size: 1.3rem;
            margin-bottom: 2rem;
            color: #f1f1f1; /* Softer white for readability */
        }

        /* Button styling */
        .btn {
            display: inline-block;
            padding: 0.9rem 2.5rem;
            border-radius: 8px;
            background: #00c6ff; /* Modern gradient for button */
            background: linear-gradient(to right, #dde1e2, #0072ff);
            color: #ffffff;
            font-size: 1.2rem;
            font-weight: bold;
            text-decoration: none;
            transition: all 0.3s ease;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
            cursor: pointer;
        }

        .btn:hover {
            background: linear-gradient(to right, #4c00ff, #d3e1e4); /* Reverse gradient on hover */
            transform: translateY(-2px); /* Slight lift effect on hover */
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.4);
        }

        /* Responsive design for smaller screens */
        @media (max-width: 600px) {
            h1 {
                font-size: 2.2rem;
            }
            p {
                font-size: 1rem;
            }
            .btn {
                font-size: 1rem;
                padding: 0.8rem 2rem;
            }
        }
    </style>
</head>
<body>

    <div class="container">
        <h1>Welcome to the Quiz Application App!</h1>
        <p>Test your knowledge on various topics, track your progress, and climb the leaderboard! Get ready to learn and challenge yourself.</p>
        <a href="<%= request.getContextPath() %>/start-quiz" class="btn">Start Quiz</a>


    </div>

</body>
</html>
