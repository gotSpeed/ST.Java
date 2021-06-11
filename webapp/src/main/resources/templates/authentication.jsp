<%@ page contentType="text/html;charset=UTF-8" %>

<html lang="en">

  <head>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/home.css" />
    <title>Authenticate</title>
  </head>

  <body>

    <div class="body-container">

      <div class="content-container">
        <h1>Sign in</h1>

        <form class="sign-in-form" method="POST" action="${pageContext.request.contextPath}/auth">
          <label class="form-label">Arrival point
            <input type="text" name="username" placeholder="Username">
          </label>

          <br>

          <label class="form-label">Arrival point
            <input type="password" name="password" placeholder="Password">
          </label>

          <br>

          <input type="submit" value="Sign in">
        </form>
      </div>

    </div>

  </body>

</html>
