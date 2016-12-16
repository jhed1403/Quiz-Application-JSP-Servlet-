<%-- 
    Document   : results
    Created on : 5-Dec-2016, 5:20:03 PM
    Author     : Jhed　Factolerin &　Ramon Mercader
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/styles.css">
        <title>Quizzes</title>
    </head>
    <body>
        <div id="container">
            <header>
                <h1>Java Project</h1>
            </header>
            <nav>
                <%
                    if (session != null) {
                        if (session.getAttribute("isAdmin") != null) {

                %>
                <ul>
                    <li><a href="admin.jsp">Home</a></li>
                    <li><a href="quizzes.jsp">Quizzes</a></li>
                    <li><a class="selectedNav">Results</a></li>
                    <li><a href="#">Feedback</a></li>
                    <li><a href="#">About Us</a></li>
                    <li><a href="contact.jsp">Contact Us</a></li>
                </ul>
                <%                }
                else {
                %>
                <ul>
                    <li><a href="index.jsp">Home</a></li>
                    <li><a href="quizzes.jsp">Quizzes</a></li>
                    <li><a class="selectedNav">Results</a></li>
                    <li><a href="#">Feedback</a></li>
                    <li><a href="#">About Us</a></li>
                    <li><a href="contact.jsp">Contact Us</a></li>
                </ul>

                <%
                        }
                    }
                %>

            </nav>
            <aside>
                <h3>More Info</h3>
                <ul>
                    <li><a href="#">Review for JSP</a></li>
                    <li><a href="#">Review for Java</a></li>
                    <li><a href="#">Review for HTML</a></li>
                    <li><a href="#">Review for Linux/Unix</a></li>
                    <li><a href="#">Review for Networking</a></li>
                    <li><a href="#">Review for SQL</a></li>
                </ul>
                <%
                    if (session != null) {
                        if (session.getAttribute("userName") != null) {
                            String name = (String) session.getAttribute("userName");
                %>
                <p>Logged in as <%=name%>&nbsp;&nbsp;<a href="LoginController?action=logout">Logout</a></p>
                <%
                }
                else {
                %>


                <%
                        }
                    }
                %>
            </aside>
            <section id="content">
                <h2>Select a category to view reports!</h2>
                <p>
                    <a href="ReportController?category=jsp">JSP</a>
                    <a href="ReportController?category=java">Java</a>
                    <a href="ReportController?category=html">HTML</a>
                    <a href="ReportController?category=linux">Linux/Unix</a>
                    <a href="ReportController?category=networking">Networking</a>
                    <a href="ReportController?category=sql">SQL</a>
                </p>
            </section>
            <footer>
                <hr>
                <div id="altnav">
                    <a href="index.jsp">Home</a> -
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
