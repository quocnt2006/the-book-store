<%-- 
    Document   : login
    Created on : May 11, 2021, 3:20:05 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <h2>Login page</h2>
            <form action="ServletDispatcherController" method="POST">
              <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" class="form-control" id="username" placeholder="Enter username" name="txtUsername">
              </div>
              <div class="form-group">
                <label for="pwd">Password:</label>
                <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="txtPassword">
              </div>
              <button type="submit" class="btn btn-primary" value="Login" name="btAction">Login</button>
              <input type="reset" value="Reset" class="btn btn-primary" />
            </form>
            <br/>
            <c:if test="${not empty requestScope.ERRORLOGIN}">
                <div class="alert alert-danger" role="alert">
                    ${requestScope.ERRORLOGIN}
                </div>
            </c:if> 
            <a href="InitSearchPageController">Back to Search Page</a>
          </div>
    </body>
</html>