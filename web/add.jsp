<%-- 
    Document   : add
    Created on : 5-Dec-2016, 12:06:33 AM
    Author     : Jhed　Factolerin &　Ramon Mercader
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/styles.css">
        <title>Admin Page</title>
    </head>
    <body>
        <div id="container">
            <header>
                <h1>Java Project</h1>
            </header>
            <nav>
                <ul>
                    <li><a href="#">Home</a></li>
                    <li><a href="#">Quizzes</a></li>
                    <li><a href="#">Results</a></li>
                    <li><a href="#">Feedback</a></li>
                    <li><a href="#">About Us</a></li>
                    <li><a href="#">Contact Us</a></li>
                </ul>
            </nav><br><br>
            <form action="${pageContext.request.contextPath}/AdminController" method="post">
                Question: <textarea rows="2" cols="80" placeholder="Enter Question" name="q" required></textarea><br><br>
                Choice A: <input type="text" name="code1" size="80" placeholder="Choice A" required><input type="checkbox" name="correct1" value="A">Correct<br><br>
                Choice B: <input type="text" name="code2" size="80" placeholder="Choice B" required><input type="checkbox" name="correct2" value="B">Correct<br><br>
                Choice C: <input type="text" name="code3" size="80" placeholder="Choice C" required><input type="checkbox" name="correct3" value="C">Correct<br><br>
                Choice D: <input type="text" name="code4" size="80" placeholder="Choice D" required><input type="checkbox" name="correct4" value="D">Correct<br><br><br>
                <div id="editButton">
                    <button type="submit" value="add" name="button" disabled>Add</button>
                    <button type="submit" value="delete" name="button" disabled>Delete</button>
                    <button type="submit" value="edit" name="button" disabled>Edit</button>
                    <button type="submit" value="save" name="button">Save</button>
                    <a href="AdminController?category=cancel">Cancel</a>
                </div>
                <div id="navButton">
                    <!--                <button onclick="goBack()">Cancel</button> <br><br>-->
                    <button type="submit" value="first" name="button" disabled>First</button>
                    <button type="submit" value="previous" name="button" disabled>Previous</button>
                    <button type="submit" value="next" name="button" disabled>Next</button>
                    <button type="submit" value="last" name="button" disabled>Last</button>
                </div>
            </form>
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
