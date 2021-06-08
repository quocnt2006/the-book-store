<%-- 
    Document   : createDiscount
    Created on : May 21, 2021, 4:55:01 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Create new discount code</title>
        <style>
            #createForm label.error{
                color: red;
                font-weight: 600;
                padding-left: 10px;
            }
            #aHeader {
                font-size: 1.75rem;
                color: black;
                width: 80%;
            }
            
            #aLogin {
                width: 20%;
                padding-left: 250px;
            }
            
            #aUser {
                width: 20%;
                color: #F0F8FF;
            }
            
            #cartBtn {
                color: black;
            }
            
            #liUser {
                list-style-type: none;
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta charset="utf-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </head>
    <body>
        <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
        <script type="text/javascript" src="js/jquery.validate.js"></script>
        <script type="text/javascript" src="js/additional-methods.js"></script>
        <c:if test="${not empty sessionScope.USER}">
            <c:if test="${sessionScope.ADMIN}">
                <nav class="navbar navbar-expand-md" style="background-color: #FF8C00; position: fixed; width: 100%; z-index: 1;">
                    <a class="navbar-brand" href="InitSearchPageController" id="aHeader">The Book Store</a>
                    <c:if test="${empty sessionScope.USER}">
                        <a id="aLogin" class="nav-link" href="login.html">Login</a>
                    </c:if>
                    <c:if test="${not empty sessionScope.USER}">
                        <li id="liUser" class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbardrop" data-toggle="dropdown" style="color: white;">
                              Welcome, ${sessionScope.USER}
                            </a>
                            <div class="dropdown-menu">
                            <c:if test="${not empty sessionScope.ADMIN}">
                                <c:if test="${!sessionScope.ADMIN}">
                                     <a class="dropdown-item" href="ServletDispatcherController?btAction=History">History</a>
                                </c:if>
                                <a class="dropdown-item" href="ServletDispatcherController?btAction=Logout">Logout</a>
                            </c:if>
                            </div>
                        </li>
                    </c:if>   
                    <c:if test="${not empty sessionScope.ADMIN}">
                        <c:if test="${!sessionScope.ADMIN}">
                            <form action="viewCart.jsp">
                                <button id="cartBtn" class="btn" type="submit">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-cart" viewBox="0 0 16 16">
                                        <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l1.313 7h8.17l1.313-7H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                                    </svg> 
                                </button>
                            </form>
                            <a class="nav-link" href="viewCart.jsp" style="color: black;">Cart</a>
                        </c:if>
                    </c:if>
                </nav>
                <br/><br/><br/><br/>
                <div class="pl-3" style="font-size: 1.5rem;">
                    Create new discount
                </div>
                <form action="ServletDispatcherController" id="createForm">
                    <div class="pl-3">
                        <br/>
                        Code: ${requestScope.CODE} <br/>
                        <input type="hidden" name="txtDiscountCode" value="${requestScope.CODE}" />
                        Discount value: <input class="form-control mr-sm-2 shadow-sm" 
                                               style="width: 30%;"
                                               type="text" 
                                               name="txtDiscountValue" 
                                               value=""
                                               oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
                                               />
                        <br/>
                        <input 
                            class="btn shadow-sm" 
                            style="background-color: #FF8C00; color: #F0F8FF;" 
                            type="submit" name="btAction" value="Create discount code" />
                    </div>
                </form>
                <div class="pl-3">
                    <br/>
                    <a href="InitSearchPageController">Back to Search Page</a>
                </div>
                    <script type="text/javascript">
                        $(function () {
                            $("#createForm").validate({
                                rules: {
                                    txtDiscountValue: {
                                        required: true,
                                        number: true,
                                        range: [1, 100]
                                    }
                                },
                                messages: {
                                    txtDiscountValue: {
                                        required: "Please input discount value",
                                        number: "Please input a number",
                                        range: "Discount value is in range 1-100"
                                    }
                                }
                            });
                        });
                    </script>
            </c:if>
            <c:if test="${!sessionScope.ADMIN}">
               <a href="InitSearchPageController">Back to Search Page</a>
            </c:if>
        </c:if>
        <c:if test="${empty sessionScope.USER}">
            <a href="InitSearchPageController">Back to Search Page</a>
        </c:if>
    </body>
</html>
