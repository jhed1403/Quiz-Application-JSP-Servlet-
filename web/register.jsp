<%-- 
    Document   : register
    Created on : 26-Nov-2016, 11:24:30 AM
    Author     : Jhed　Factolerin &　Ramon Mercader
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/styles.css">
        <title>Register</title>
    </head>
    <body>
        <div id="container">
            <header>
                <h1>Java Project</h1>
            </header>
            <nav>
                <ul>
                    <li><a href="index.jsp">Home</a></li>
                    <li><a href="quizzes.jsp">Quizzes</a></li>
                    <li><a href="results.jsp">Results</a></li>
                    <li><a href="#">Feedback</a></li>
                    <li><a href="#">About Us</a></li>
                    <li><a href="contact.jsp">Contact Us</a></li>
                </ul>
            </nav>
            <section id="content">
                <h2>Register</h2>
                <p>
                <form action="${pageContext.request.contextPath}/LoginController" method="post">
                    <strong>Username</strong><br><input type="text" name="username" placeholder="Username" required><br><br>
                    <strong>Password</strong><br><input type="password" name="password" placeholder="Password" required><br><br><br>
                    <button type="submit" value="regSubmit" name="button">Register</button>
                    <button type="reset" value="reset" name="button">Reset</button>
                </form>
                </p>
            </section>
            <footer>
                <hr>
                <div id="altnav">
                    <a href="#">Home</a> -
                    <a href="#">Quizzes</a> -
                    <a href="#">Results</a> -
                    <a href="#">Feedback</a> -
                    <a href="#">About Us</a> -
                    <a href="contact.jsp">Contact Us</a>
                </div>
                Copyright &copy; Ramon Mercader &amp; Jhed Factolerin
            </footer>
        </div>
    </body>
</html>

