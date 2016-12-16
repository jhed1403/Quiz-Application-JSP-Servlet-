<%-- 
    Document   : JSPQuiz
    Created on : 24-Nov-2016, 9:38:09 PM
    Author     : Jhed　Factolerin &　Ramon Mercader
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/styles.css">
        <title>JSP Page</title>
    </head>
    <body>
        <div id="container">
            <header>
                <h1>Java Project</h1>
            </header>
            <nav>
                <ul>
                    <li><a href="#">Home</a></li>
                    <li><a class="selectedNav">Quizzes</a></li>
                    <li><a href="#">Results</a></li>
                    <li><a href="#">Feedback</a></li>
                    <li><a href="#">About Us</a></li>
                    <li><a href="#">Contact Us</a></li>
                </ul>
            </nav>
            <section id="content" style="margin-right:0px;">
                <h2>Questions: </h2>
                <form action="${pageContext.request.contextPath}/QuizServlet" method="post">
                    <h3>  ${question} </h3>
                    <ul style="list-style-type:none">
                        <h4><li><input type="radio" name="option" value="A" ${checked1} required> A. ${choice1} </li></h4>
                        <h4><li><input type="radio" name="option" value="B" ${checked2} required> B. ${choice2} </li></h4>
                        <h4><li><input type="radio" name="option" value="C" ${checked3} required> C. ${choice3} </li></h4>
                        <h4><li><input type="radio" name="option" value="D" ${checked4} required> D. ${choice4} </li></h4>
                    </ul>
                    <button type="submit" value="next" name="button" ${disabled}>Next</button>
                    <button type="submit" value="submitQuiz" name="button" ${fDisabled}>Finish Quiz</button>
                </form>
            </section>
            <footer>
                <hr>
                <div id="altnav">
                    <a href="index.jsp">Home</a> -
                    <a href="#">Quizzes</a> -
                    <a href="#">Results</a> -
                    <a href="#">Feedback</a> -
                    <a href="#">About Us</a> -
                    <a href="#">Contact Us</a>
                </div>
                Copyright &copy; Ramon Mercader &amp; Jhed Factolerin
            </footer>
        </div>
    </body>
</html>
