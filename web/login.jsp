<%-- 
    Document   : Login
    Created on : 23-Nov-2016, 8:28:02 PM
    Author     : Jhed　Factolerin &　Ramon Mercader
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/styles.css">
        <title>Log in</title>
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
                <h2>Log-in</h2>
                <p>
                <h3 style="color:red">  ${message} </h3>
                <form action="${pageContext.request.contextPath}/LoginController" method="post">
                    <strong>Username</strong><br><input type="text" name="username" placeholder="Username" required><br><br>
                    <strong>Password</strong><br><input type="password" name="password" placeholder="Password" required><br><br><br>
                    <button type="submit" value="login" name="button">Login</button>
                    <button type="reset" value="reset" name="button">Reset</button>
                    <button type="submit" value="register" name="button">Register</button>
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
