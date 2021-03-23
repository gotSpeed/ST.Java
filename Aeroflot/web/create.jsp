<%@ page import="core.models.countries.Country" %>
<%@ page import="java.util.List" %>
<%@ page import="core.models.transportrelated.Plane" %>
<%@ page import="core.models.personrelated.Crew" %>
<%@ page import="core.models.personrelated.Person" %><%--
  Created by IntelliJ IDEA.
  User: 11
  Date: 22.03.2021
  Time: 2:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  List<Country> countries = (List<Country>) request.getAttribute("countries");
  List<Plane> planes = (List<Plane>) request.getAttribute("planes");
  List<Crew> personal = (List<Crew>) request.getAttribute("personal");
%>

<html lang="en">

  <head>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/home.css" />
    <title>Registering new flight</title>
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
        <h1>Register new</h1>

        <form class="new-flight-form" method="POST" action="/new">
          <label class="form-label">Departure date and time
            <input type="text"
                   name="departure_datetime"
                   placeholder="yyyy-MM-dd hh:mm:ss">
          </label>

          <br>

          <label class="form-label">Arrival date and time
            <input type="text"
                   name="arrival_datetime"
                   placeholder="yyyy-MM-dd hh:mm:ss">
          </label>

          <br>

          <label class="form-label">Departure point
            <select name="departure_point">
              <% for (Country item : countries) { %>
              <option value="<%= item.getId() %>"><%= item.getTitle() %></option>
              <% } %>
            </select>
          </label>

          <br>

          <label class="form-label">Arrival point
            <select name="arrival_point">
              <% for (Country item : countries) { %>
              <option value="<%= item.getId() %>"><%= item.getTitle() %></option>
              <% } %>
            </select>
          </label>

          <br>

          <label class="form-label">Plane
            <select name="plane">
              <% for (Plane item : planes) { %>
              <option value="<%= item.getId() %>"><%= item.getModel() %></option>
              <% } %>
            </select>
          </label>

          <br>

          <label class="form-label">Departure point
            <select name="personal" multiple>
              <% for (Crew item : personal) {
                Person personalInfo = item.getPerson(); %>
              <option value="<%= personalInfo.getId() %>">
                <%= personalInfo.getName() +
                    personalInfo.getSurname() + " (" +
                    personalInfo.getPosition().getTitle() + ", " +
                    item.getRank() + ")" %>
              </option>
              <% } %>
            </select>
          </label>

          <br>

          <input type="submit" value="Register">
        </form>

      </div>

    </div>

  </body>

</html>
