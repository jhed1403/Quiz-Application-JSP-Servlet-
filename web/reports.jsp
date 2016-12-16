<%-- 
    Document   : reports
    Created on : 5-Dec-2016, 5:11:11 PM
    Author     : Jhed　Factolerin &　Ramon Mercader
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.sql.*"%>
<jsp:useBean id="quizDB"  class="model.QuizDB" scope="application" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/styles.css">
        <title>Quizzes</title>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
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
                    <li><a class="selectedNav">Results</a></li>
                    <li><a href="#">Feedback</a></li>
                    <li><a href="#">About Us</a></li>
                    <li><a href="contact.jsp">Contact Us</a></li>
                </ul>
            </nav>

            <section id="content">
                <p>
                    <%
                        String categoryID = request.getParameter("categoryID");
                        if (categoryID.equals("1")) {
                    %>
                <h2>JSP Quiz Top 5 highest scorer:</h2>

                <%
                }
                else if (categoryID.equals("4")) {
                %>
                <h2>Java Quiz Top 5 highest scorer:</h2>
                <%
                }
                else if (categoryID.equals("5")) {
                %>
                <h2>HTML Quiz Top 5 highest scorer:</h2>
                <%
                }
                else if (categoryID.equals("2")) {
                %>
                <h2>Linux/Unix Quiz Top 5 highest scorer:</h2>
                <%
                }
                else if (categoryID.equals("3")) {
                %>
                <h2>Networking Quiz Top 5 highest scorer:</h2>
                <%
                }
                else if (categoryID.equals("6")) {
                %>
                <h2>SQL Quiz Top 5 highest scorer:</h2>
                <%
                    }
                %>

                <%
                    quizDB.connect();
                    quizDB.openJSP("SELECT * FROM Results a INNER JOIN User b on (a.UserID = b.UserID) WHERE a.CategoryID=" + categoryID + " ORDER BY Mark DESC LIMIT 5");
                    ResultSet RS = quizDB.getResultSet();
                %>

                <table BORDER WIDTH="100%" >
                    <tr>
                        <td><b>Username</b></td>
                        <td><b>Score</b></td>
                    </tr>

                    <%
                        while (RS.next()) {
                    %>

                    <tr>
                        <td><%=RS.getString("UserName")%></td>
                        <td><%=RS.getString("Mark")%></td>
                    </tr>
                    <%
                        }
                    %>
                </table>
                <%
                    quizDB.openJSP("SELECT AVG(Mark) FROM Results WHERE CategoryID=" + categoryID);
                    RS = quizDB.getResultSet();
                    RS.next();
                %>
                <br><br><h2>The overall average of the results is: <%=RS.getString(1)%></h2>
                <br><br>

                <%
                    String[] percentage = new String[11];
                    quizDB.openJSP("SELECT AVG(Mark) FROM Results WHERE CategoryID=" + categoryID);
                    RS = quizDB.getResultSet();
                    RS.next();

                    for (int i = 0; i < 11; i++) {
                        quizDB.openJSP("SELECT COUNT(*) FROM Results WHERE CategoryID="
                                + categoryID + " AND Mark=" + i);

                        RS = quizDB.getResultSet();
                        RS.next();
                        percentage[i] = RS.getString(1);
                    }
                %>
                <script type="text/javascript">
                    google.charts.load('current', {'packages': ['bar']});
                    google.charts.setOnLoadCallback(drawStuff);

                    function drawStuff() {
                        var data = new google.visualization.arrayToDataTable([
                            ['Percentage', 'Number of Users'],
                            ["0", <%=percentage[0]%>],
                            ["10", <%=percentage[1]%>],
                            ["20", <%=percentage[2]%>],
                            ["30", <%=percentage[3]%>],
                            ['40', <%=percentage[4]%>],
                            ['50', <%=percentage[5]%>],
                            ['60', <%=percentage[6]%>],
                            ['70', <%=percentage[7]%>],
                            ['80', <%=percentage[8]%>],
                            ['90', <%=percentage[9]%>],
                            ['100', <%=percentage[10]%>]

                        ]);

                        var options = {
                            title: 'Quiz Result Summary',
                            width: 710,
                            legend: {position: 'none'},
                            chart: {subtitle: ''},
                            axes: {
                                x: {
                                    0: {side: 'top', label: 'Percentage'} // Top x-axis.
                                }
                            },
                            bar: {groupWidth: "30%"}
                        };

                        var chart = new google.charts.Bar(document.getElementById('top_x_div'));
                        // Convert the Classic options to Material options.
                        chart.draw(data, google.charts.Bar.convertOptions(options));
                    }
                    ;
                </script>
                <div id="top_x_div" style="width: 300px; height: 300px;"></div>
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
