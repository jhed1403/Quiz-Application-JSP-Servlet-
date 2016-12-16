<%-- 
    Document   : adminView
    Created on : 4-Dec-2016, 11:36:37 PM
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
                    <li><a class="selectedNav">Home</a></li>
                    <li><a href="quizzes.jsp">Quizzes</a></li>
                    <li><a href="#">Results</a></li>
                    <li><a href="#">Feedback</a></li>
                    <li><a href="#">About Us</a></li>
                    <li><a href="contact.jsp">Contact Us</a></li>
                </ul>
            </nav><br><br>
            <form action="${pageContext.request.contextPath}/AdminController" method="post">
                Question: <textarea rows="2" cols="80" readonly>${question}</textarea><br><br>
                Choice A: <input type="text" id="code" value="${choice1}" size="80" readonly><input type="checkbox" name="correct" value="A" ${IsRight1} disabled>Correct<br><br>
                Choice B: <input type="text" id="code" value="${choice2}" size="80" readonly><input type="checkbox" name="correct" value="B" ${IsRight2} disabled>Correct<br><br>
                Choice C: <input type="text" id="code" value="${choice3}" size="80" readonly><input type="checkbox" name="correct" value="C" ${IsRight3} disabled>Correct<br><br>
                Choice D: <input type="text" id="code" value="${choice4}" size="80" readonly><input type="checkbox" name="correct" value="D" ${IsRight4} disabled>Correct<br><br><br>

                <div id="editButton">
                    <button type="submit" value="add" name="button">Add</button>
                    <button type="submit" value="delete" name="button">Delete</button>
                    <button type="submit" value="edit" name="button">Edit</button>
                    <button type="submit" value="save" name="button" disabled>Save</button>
                    <button type="submit" value="cancel" name="button" disabled>Cancel</button> 
                    <button type="submit" value="back" name="button">Go Back</button>
                </div>
                <br>
                <div id="navButton">
                    <button type="submit" value="first" name="button">First</button>
                    <button type="submit" value="previous" name="button" ${prevDisabled}>Previous</button>
                    <button type="submit" value="next" name="button" ${disabled}>Next</button>
                    <button type="submit" value="last" name="button">Last</button>
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

