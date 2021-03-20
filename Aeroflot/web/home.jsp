<%--
  Created by IntelliJ IDEA.
  User: 11
  Date: 20.03.2021
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="core.flightrelated.Flight" %>
<%@ page import="java.util.List" %>

<%
  List<Flight> flights = (List<Flight>) request.getAttribute("flights");
%>

<html>

  <head>
    <title>Registered flights</title>
  </head>

  <body>
    <div class="content-container">

      <div class="action-bar">

        <form method="post">
          <input name="Register new" type="submit">
        </form>

      </div>

      <div>
        <ul class="content-list">

          <% for (Flight obj : flights) { %>
          <li><%= obj.getStatus() %></li>
          <% } %>

        </ul>
      </div>

    </div>
  </body>

</html>
