<%-- 
    Document   : admin
    Created on : 4-Dec-2016, 11:06:35 PM
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
                    <li><a href="admin.jsp">Home</a></li>
                    <li><a href="#">Quizzes</a></li>
                    <li><a href="results.jsp">Results</a></li>
                    <li><a href="#">Feedback</a></li>
                    <li><a href="#">About Us</a></li>
                    <li><a href="contact.jsp">Contact Us</a></li>
                </ul>
            </nav>
            <div style="float: left">
                <aside>
                    <a href="AdminController?category=jsp">JSP</a>
                    <a href="AdminController?category=java">Java</a>
                    <a href="AdminController?category=html">HTML</a>
                    <a href="AdminController?category=linux">Linux/Unix</a>
                    <a href="AdminController?category=networking">Networking</a>
                    <a href="AdminController?category=sql">SQL</a>
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
            </div>
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
