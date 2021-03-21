<%--
  Created by IntelliJ IDEA.
  User: 11
  Date: 20.03.2021
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="core.models.flightrelated.Flight" %>
<%@ page import="java.util.List" %>
<%@ page import="core.models.transportrelated.Plane" %>

<%
  List<Flight> flights = (List<Flight>) request.getAttribute("flights");
%>

<html lang="en">

  <head>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/home.css" />
    <title>Registered flights</title>
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
        <h1>Registered flights</h1>
        <form id="edit_form" style="display: none" method="GET" action="/edit"></form>

        <button class="edit-btn" type="submit" formaction="/new" form="edit_form">
          New
        </button>
        <ul class="content-list">

          <% for (Flight flight : flights) { %>
          <li class="content-list__item">
            <div class="item__block id-block">#<%= flight.getId() %></div>
            <div class="item__block info-block">
              <div class="info-block__info">
                <%= flight.getDeparturePoint().getTitle() %> —
                <%= flight.getArrivalPoint().getTitle() %>
              </div>
              <div class="info-block__info">
                <%= flight.getDepartureDateTime().toString() %> —
                <%= flight.getArrivalDateTime().toString() %>
              </div>
              <div class="info-block__info"><%= flight.getStatus() %></div>
            </div>
            <div class="item__block edit-btn-block">
              <button class="edit-btn"
                      type="submit"
                      name="editable_id"
                      value="<%= flight.getId() %>"
                      form="edit_form">Edit
              </button>
            </div>
          </li>

          <% } %>

        </ul>
      </div>

    </div>

  </body>

</html>
