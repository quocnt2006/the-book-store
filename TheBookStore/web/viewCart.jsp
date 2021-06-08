<%-- 
    Document   : viewCart
    Created on : May 19, 2021, 11:09:24 AM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Cart page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta charset="utf-8">
        <style>
            #cartForm label.error{
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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </head>
    <body onload="OutOfStock()">
        <c:if test="${not empty sessionScope.USER}">
            <c:if test="${!sessionScope.ADMIN}">
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
                    Your cart
                    
                </div>
                <c:set var="cart" value="${sessionScope.CART}" />
                <c:if test="${not empty cart}">
                    <c:set var="items" value="${cart.items}" />
                    <c:if test="${not empty items}">
                        <br/>
                        <div class="pl-3 pr-3">
                            <table class="table table-striped">
                                <thead>
                                    <tr style="background-color: #FF8C00;">
                                        <th scope="col">No.</th>
                                        <th scope="col">Book ID</th>
                                        <th scope="col">Title</th>
                                        <th scope="col">Amount</th>
                                        <th scope="col">Price</th>
                                        <th scope="col">Total</th>
                                        <th scope="col">Update</th>
                                        <th scope="col">Remove</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="item" items="${items}" varStatus="counter">
                                        <form action="ServletDispatcherController" id="cartForm">
                                            <tr>
                                                <td scope="row">
                                                    ${counter.count}
                                                </td>
                                                <td>
                                                    ${item.key}
                                                    <input type="hidden" name="txtBookId" value="${item.key}" />
                                                </td>
                                                <td>
                                                    ${item.value.title}
                                                </td>
                                                <td>
                                                    <input 
                                                        class="form-control mr-sm-2 shadow-sm"
                                                        type="text" 
                                                        name="txtQuantity" 
                                                        value="${item.value.quantity}" 
                                                        id="${counter.count}" 
                                                        oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
                                                        />
                                                </td>
                                                <td>
                                                    $${item.value.unitPrice}
                                                </td>
                                                <td>
                                                    $${item.value.total}
                                                </td>
                                                <td>
                                                    <input 
                                                        class="btn shadow-sm" 
                                                        style="background-color: #FF8C00; color: #F0F8FF;" 
                                                        type="submit" value="Update Item" name="btAction" 
                                                        />
                                                </td>
                                                <td>
                                                    <c:url var="removeLink" value="ServletDispatcherController">
                                                        <c:param name="btAction" value="RemoveItem" />
                                                        <c:param name="txtBookId" value="${item.key}" />
                                                    </c:url>
                                                    <a href="${removeLink}" onclick="return confirm('Are you sure you want to remove this book?')" >Remove book</a>
                                                </td>                                     
                                            </tr>
                                        </form>
                                    </c:forEach>
                                <tr style="background-color: #FFF4E7;">
                                    <form action="ServletDispatcherController">
                                        <td colspan="3">
                                            DISCOUNT CODE
                                        </td>
                                        <td>
                                            <input class="form-control mr-sm-2 shadow-sm" type="text" name="txtDiscountCode" value="${DISCOUNTCODE}" />
                                        </td>
                                        <td colspan="2">
                                            <c:if test="${not empty sessionScope.DISCOUNTCODE}">
                                                <font color="green">
                                                Discount ${sessionScope.DISCOUNT}%
                                                </font>
                                            </c:if>
                                            <c:if test="${empty sessionScope.DISCOUNTCODE}">
                                                <input 
                                                        class="btn shadow-sm" 
                                                        style="background-color: #FF8C00; color: #F0F8FF;" 
                                                        type="submit" value="Use code" name="btAction" 
                                                        />
                                            </c:if>
                                        </td>
                                        <td colspan="2">
                                            <c:if test="${not empty requestScope.WRONGCODE}">
                                                <font color="red">
                                                ${requestScope.WRONGCODE}
                                                </font>
                                            </c:if>
                                            <c:if test="${not empty requestScope.TRUECODE}">
                                                <font color="green">
                                                ${requestScope.TRUECODE}
                                                </font>
                                            </c:if>
                                        </td>
                                    </form>
                                </tr>
                                <tr style="background-color: #FFF4E7;">
                                <form action="ServletDispatcherController">
                                    <td colspan="5"><strong>TOTAL</strong></td>
                                    <td>
                                        $${sessionScope.TOTAL}
                                    </td>
                                    <td>
                                        <input 
                                            class="btn shadow-sm" 
                                            style="background-color: #FF8C00; color: #F0F8FF;" 
                                            type="submit" value="Confirm" name="btAction" 
                                            onclick="return confirm('Are you sure you want to continue')"
                                            />
                                    </td>
                                    <td>
                                        <a href="InitSearchPageController">Add more</a>
                                    </td>
                                </form>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <script>
                            function OutOfStock() {
                                <c:if test="${not empty requestScope.QUANERR}">
                                    alert('${requestScope.QUANERR}');
                                </c:if>
                            }
                        </script>
                        <div class="pl-3">
                            <a href="InitSearchPageController" style="font-size: 1rem;">Back to Search Page</a>
                        </div>
                    </c:if>
                </c:if>
                <c:if test="${empty cart}">
                    <br/>
                    <div class="pl-3" style="font-size: 1.5rem;">
                        Cart is empty <br/>
                        <a href="InitSearchPageController" style="font-size: 1rem;">Back to Search Page</a>
                    </div>
                </c:if>
            </c:if>
            <c:if test="${sessionScope.ADMIN}">
               <a href="InitSearchPageController">Back to Search Page</a>
            </c:if>
        </c:if>
        <c:if test="${empty sessionScope.USER}">
            <a href="InitSearchPageController">Back to Search Page</a>
        </c:if>
    </body>
</html>
