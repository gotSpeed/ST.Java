<%--
  Created by IntelliJ IDEA.
  User: 11
  Date: 20.03.2021
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="core.models.flightrelated.Flight" %>
<%@ page import="core.models.countries.Country" %>
<%@ page import="java.util.List" %>

<%
  Flight flight = (Flight) request.getAttribute("flight");
  List<Country> countries = (List<Country>) request.getAttribute("countries");
%>

<html lang="en">

  <head>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/home.css" />
    <title>Editing #<%= flight.getId() %></title>
  </head>

  <body>

    <div class="body-container">

      <header>
        <nav class="navigation">
          <ul class="navigation__linklist">

            <li class="linklist__link">
              <a class="link__text" href="/home">Home</a>
            </li>
            <li class="linklist__delimiter"></li>
            <li class="linklist__link">
              <a class="link__text" href="/transport">Transport park</a>
            </li>
            <li class="linklist__delimiter"></li>
            <li class="linklist__link">
              <a class="link__text" href="/staff">Staff</a>
            </li>

          </ul>
        </nav>
      </header>

      <div class="content-container">
        <h1>Editing flight #<%= flight.getId() %></h1>

        <form class="edit-flight-form" method="POST" action="/edit">
          <label class="edit-form-label">Arrival point
            <select name="arrival_point">
              <% for (Country item : countries) {
                if (item.getId() != flight.getArrivalPoint().getId()) { %>
              <option value="<%= item.getId() %>"><%= item.getTitle() %></option>
              <% }
              else { %>
              <option value="<%= item.getId() %>" selected><%= item.getTitle() %></option>
              <% }
              } %>
            </select>
          </label>

          <br>

          <label class="edit-form-label">Arrival time
            <input type="text"
                   name="arrival_datetime"
                   value="<%= flight.getArrivalDateTime() %>">
          </label>

          <br>

          <label class="edit-form-label">Status
            <input type="text" name="status" value="<%= flight.getStatus() %>">
          </label>

          <input type="submit" name="submit" value="Save">
        </form>

      </div>

    </div>

  </body>

</html>
