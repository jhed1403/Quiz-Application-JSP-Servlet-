<%-- 
    Document   : quizJSP
    Created on : 27-Nov-2016, 7:37:38 PM
    Author     : Jhed　Factolerin &　Ramon Mercader
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/styles.css">
        <title>Category</title>
    </head>
    <body>
        <%
            String userID = "";
            if (session != null) {
                if (session.getAttribute("userID") != null) {
                    userID = (String) session.getAttribute("userID");
                }
            }
        %>
        <div id="container">
            <header>
                <h1>Java Project</h1>
            </header>
            <nav>
                <ul>
                    <li><a href="index.jsp">Home</a></li>
                    <li><a class="selectedNav">Quizzes</a></li>
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
                    String name = "";
                    if (session != null) {
                        if (session.getAttribute("userName") != null) {
                            name = (String) session.getAttribute("userName");
                %>
                <p>Logged in as <%=name%>&nbsp;&nbsp;<a href="logout.jsp">Logout</a></p>
                <%
                } else {
                %>


                <%
                        }
                    }
                %>
            </aside>
            <section id="content">
                <h2>Take the quiz today!</h2>
                <p>
                    <%
                        String categoryID = request.getParameter("categoryID");
                        if (categoryID.equals("1")) {
                    %>
                <p>JavaServer Pages (JSP) is a technology that helps software developers 
                    create dynamically generated web pages based on HTML, XML, or other 
                    document types. Released in 1999 by Sun Microsystems, JSP is similar 
                    to PHP and ASP, but it uses the Java programming language.</p>
                    <%
                    } else if (categoryID.equals("4")) {
                    %>
                <p>Java is a programming language and computing platform first released 
                    by Sun Microsystems in 1995. There are lots of applications and websites 
                    that will not work unless you have Java installed, and more are created every day. 
                    Java is fast, secure, and reliable. From laptops to datacenters, 
                    game consoles to scientific supercomputers, cell phones to the Internet, 
                    Java is everywhere!</p>
                    <%
                    } else if (categoryID.equals("5")) {
                    %>
                <p>HTML is a computer language devised to allow website creation. 
                    These websites can then be viewed by anyone else connected to the Internet. 
                    It is relatively easy to learn, with the basics being accessible to most people in one sitting; 
                    and quite powerful in what it allows you to create. It is constantly undergoing revision and 
                    evolution to meet the demands and requirements of the growing Internet audience under the direction 
                    of the » W3C, the organisation charged with designing and maintaining the language.</p>
                    <%
                    } else if (categoryID.equals("2")) {
                    %>
                <p>Linux is a Unix-like computer operating system assembled under the model 
                    of free and open-source software development and distribution. The defining 
                    component of Linux is the Linux kernel,[12] an operating system kernel first 
                    released on September 17, 1991 by Linus Torvalds.[13][14][15] The Free Software 
                    Foundation uses the name GNU/Linux to describe the operating system, which has 
                    led to some controversy;[16][17] while they explicitly have no controversy over 
                    the name Android (they object to it on proprietary grounds however), as GNU 
                    isn't a part of it.</p>
                    <%
                    } else if (categoryID.equals("3")) {
                    %>
                <p>A computer network or data network is a telecommunications network which 
                    allows computers to exchange data. In computer networks, networked computing 
                    devices exchange data with each other using a data link. The connections 
                    between nodes are established using either cable media or wireless media. 
                    The best-known computer network is the Internet.</p>
                    <%
                    } else if (categoryID.equals("6")) {
                    %>
                <p>SQL or Structured Query Language is a special-purpose programming 
                    language designed for managing data held in a relational database 
                    management system (RDBMS), or for stream processing in a relational 
                    data stream management system (RDSMS).</p>
                    <%
                        }
                    %>


                <form action="${pageContext.request.contextPath}/QuizServlet" method="post">

                    <button type="submit" value="start" name="button">Start Quiz</button>
                    <button onclick="goBack()">Go Back</button>
                    <input type="hidden" name="id" value="<%=userID%>" />
                    <input type="hidden" name="userName" value="<%=name%>" />
                </form>
                <script>
                    function goBack() {
                        window.history.back();
                    }
                </script>
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
