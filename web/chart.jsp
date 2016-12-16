<%-- 
    Document   : chart
    Created on : 5-Dec-2016, 7:56:42 PM
    Author     : Jhed　Factolerin &　Ramon Mercader
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['bar']});
      google.charts.setOnLoadCallback(drawStuff);

      function drawStuff() {
        var data = new google.visualization.arrayToDataTable([
          ['Percentage', 'Number of Users'],
          ["0", 0],
          ["10", 0],
          ["20", 0],
          ["30", 0],
          ['40', 0],
          ['50', 0],
          ['60', 0],
          ['70', 0],
          ['80', 0],
          ['90', 0],
          ['100', 0]
          
        ]);

        var options = {
          title: 'Quiz Result Summary',
          width: 900,
          legend: { position: 'none' },
          chart: { subtitle: '' },
          axes: {
            x: {
              0: { side: 'top', label: 'Percentage'} // Top x-axis.
            }
          },
          bar: { groupWidth: "50%" }
        };

        var chart = new google.charts.Bar(document.getElementById('top_x_div'));
        // Convert the Classic options to Material options.
        chart.draw(data, google.charts.Bar.convertOptions(options));
      };
    </script>
  </head>
  <body>
    <div id="top_x_div" style="width: 600px; height: 300px;"></div>
  </body>
</html>
