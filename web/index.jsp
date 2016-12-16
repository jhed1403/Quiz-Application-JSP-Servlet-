<%-- 
    Document   : index.jsp
    Created on : 24-Nov-2016, 3:33:00 PM
    Author     : Jhed　Factolerin &　Ramon Mercader
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/styles.css">
        <title>Home</title>
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
                    <li><a href="results.jsp">Results</a></li>
                    <li><a href="#">Feedback</a></li>
                    <li><a href="#">About Us</a></li>
                    <li><a href="contact.jsp">Contact Us</a></li>
                </ul>
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
                } else {
                %>


                <%
                        }
                    }
                %>
            </aside>
            <section id="content">
                <%
                    if (session != null) {
                        if (session.getAttribute("userName") != null) {
                            String name = (String) session.getAttribute("userName");
                %>

                <h2>Take a quiz now!</h2>
                <p>
                    Hello there, <%=name%>!
                </p>

                <%
                } else {
                %>
                <h2>Take a quiz now!<h2>

                        <p>&nbsp;&nbsp;&nbsp;&nbsp;
                            <a href ="login.jsp">Login</a> &nbsp;&nbsp;
                            <a href ="register.jsp">Register</a>
                        </p>
                        <%
                                }
                            }
                        %>
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
