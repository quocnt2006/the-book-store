<%-- 
    Document   : search
    Created on : May 10, 2021, 12:58:46 PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Search Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta charset="utf-8">
        <style>
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
    <body>
        <nav class="navbar navbar-expand-md" style="background-color: #FF8C00; position: fixed; width: 100%; z-index: 1;">
            <a class="navbar-brand" href="InitSearchPageController" id="aHeader">The Book Store</a>
            <c:if test="${empty sessionScope.USER}">
                <a id="aLogin" class="nav-link" href="login.jsp">Login</a>
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
        <c:if test="${not empty sessionScope.USER}">
            <c:if test="${sessionScope.ADMIN}">
                <div class="pl-3">
                    <form action="createNewBook.jsp">
                        <input 
                            class="btn shadow-sm" 
                            style="background-color: #FF8C00; color: #F0F8FF;" 
                            type="submit" value="Create new book" name="btAction" 
                            />
                    </form>
                    <br/>
                    <form action="ServletDispatcherController">
                        <input 
                            class="btn shadow-sm" 
                            style="background-color: #FF8C00; color: #F0F8FF;" 
                            type="submit" value="Create new discount" name="btAction" 
                            />
                    </form>
                </div>
                <br/>
            </c:if>
        </c:if> 
        <div class="pl-3" style="font-size: 1.5rem;">
            Search Page 
        </div>
        <br/>
        <div class="pl-3">
            <form class="form-inline" action="ServletDispatcherController">
                <input class="form-control mr-sm-2 shadow-sm" type="text" name="txtSearch" value="${param.txtSearch}" placeholder="Search by name"/>
                <input 
                    class="btn shadow-sm" 
                    style="background-color: #FF8C00; color: #F0F8FF;" 
                    type="submit" value="Search" name="btAction" 
                    />
            </form>
            <br/>
            <form action="ServletDispatcherController">
                <p style="opacity: 0.54;">Search by category</p>
                <c:set var="categories" value="${sessionScope.CATEGORIES}" />
                <c:if test="${not empty categories}">
                    <div class="input-group mb-3" style="padding-right: 70%;">
                        <div class="input-group-prepend shadow-sm">
                            <label class="input-group-text" for="categorySelect">Category</label>
                        </div>
                        <select class="custom-select shadow-sm" name="category" id="categorySelect" style="width: 30%;">
                            <option value="All"
                                    <c:if test="${'All' eq param.category}">
                                            selected
                                    </c:if>
                                    >
                                All
                            </option>
                            <c:forEach var="dto" items="${categories}" >
                                <option value="${dto.categoryName}"
                                        <c:if test="${dto.categoryName eq param.category}">
                                            selected
                                        </c:if>
                                        >
                                    ${dto.categoryName}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </c:if>
                <input 
                        class="btn shadow-sm" 
                        style="background-color: #FF8C00; color: #F0F8FF;" 
                        type="submit" value="Search" name="btAction" 
                        />
            </form>
        </div>
        <br/>
        <form class="form-inline" action="ServletDispatcherController">
            <div class="pl-3">
                <p style="opacity: 0.54;">Choose price range</p>
                <input class="form-control mr-sm-2 shadow-sm" type="text" name="txtMinPrice" value="${param.txtMinPrice}" 
                                oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" 
                                placeholder="Min price"
                                />
                -  &emsp;<input class="form-control mr-sm-2 shadow-sm" type="text" name="txtMaxPrice" value="${param.txtMaxPrice}" 
                                oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');" 
                                placeholder="Max price"
                                />
                <input 
                    class="btn shadow-sm" 
                    style="background-color: #FF8C00; color: #F0F8FF;" 
                    type="submit" value="Search" name="btAction" 
                    />
            </div>
        </form>
        <br/>
        <br/>
        <c:set var="result" value="${requestScope.RESULT}" />
        <c:if test="${not empty result}">
            <div class="pl-3 pr-3">
                <table class="table table-striped shadow-sm">
                    <thead>
                        <tr style="background-color: #FF8C00;">
                            <th scope="col">No.</th>
                            <th scope="col">Title</th>
                            <th scope="col">Image</th>
                            <th scope="col">Description</th>
                            <th scope="col">Price</th>
                            <th scope="col">Author</th>
                            <th scope="col">Category</th>
                            <c:if test="${not empty sessionScope.ADMIN}">
                                <c:if test="${sessionScope.ADMIN}" >
                                    <th scope="col">Import Date</th>
                                    <th scope="col">Quantity</th>
                                    <th scope="col">Update</th>
                                    <th scope="col">Delete</th>
                                </c:if>
                            </c:if>
                            <c:if test="${not empty sessionScope.ADMIN}">
                                    <c:if test="${!sessionScope.ADMIN}" >
                                    <th scope="col">
                                        Add to cart
                                    </th>
                                </c:if>
                            </c:if>   
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                            <c:if test="${not empty sessionScope.ADMIN}">
                                <c:if test="${!sessionScope.ADMIN}">
                                    <form action="ServletDispatcherController">
                                </c:if>
                            </c:if>
                            <c:if test="${not empty sessionScope.ADMIN}">
                                <c:if test="${sessionScope.ADMIN}">
                                    <form action="updateBook.jsp">
                                </c:if>
                            </c:if>
                                <tr>
                                    <td scope="row">${counter.count}</td>
                                    <td>
                                        ${dto.title}
                                        <input type="hidden" name="txtTitle" value="${dto.title}" />
                                        <input type="hidden" name="txtId" value="${dto.id}" />
                                    </td>
                                    <td>
                                        <img src="${dto.image}" alt="No Image" height="200px" width="150px" />
                                        <input type="hidden" name="txtPhoto" value="${dto.image}" />
                                    </td>
                                    <td>
                                        ${dto.description}
                                        <input type="hidden" name="txtDescription" value="${dto.description}" />
                                    </td>
                                    <td>
                                        $${dto.price}
                                        <input type="hidden" name="txtPrice" value="${dto.price}" />
                                    </td>
                                    <td>
                                        ${dto.author}
                                        <input type="hidden" name="txtAuthor" value="${dto.author}" />
                                    </td>
                                    <td>
                                        ${dto.category}
                                        <input type="hidden" name="txtCategory" value="${dto.category}" />
                                    </td>
                                    <c:if test="${not empty sessionScope.ADMIN}">
                                        <c:if test="${sessionScope.ADMIN}">
                                            <td>
                                                ${dto.importDate}
                                                <input type="hidden" name="txtImportDate" value="${dto.importDate}" />
                                            </td>
                                            <td>
                                                ${dto.quantity}
                                                <input type="hidden" name="txtQuantity" value="${dto.quantity}" />
                                            </td>
                                            <td>
                                                <input 
                                                    class="btn shadow-sm" 
                                                    style="background-color: #FF8C00; color: #F0F8FF;" 
                                                    type="submit" value="Update" name="btAction" 
                                                    />
                                            </td>
                                            <td>
                                                <c:url var="deleleLink" value="ServletDispatcherController">
                                                    <c:param name="btAction" value="Delete" />
                                                    <c:param name="txtId" value="${dto.id}" />
                                                    <c:param name="searchValue" value="${param.txtSearch}" />
                                                    <c:param name="txtMinPrice" value="${param.txtMinPrice}"/>
                                                    <c:param name="txtMaxPrice" value="${param.txtMaxPrice}"/>
                                                    <c:param name="category" value="${param.category}"/>
                                                </c:url>
                                                <a href="${deleleLink}" onclick="return confirm('Are you sure you want to delete this book?')">Delete</a>
                                            </td>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${not empty sessionScope.ADMIN}">
                                        <c:if test="${!sessionScope.ADMIN}">
                                            <td>
                                                <input type="hidden" name="searchValue" value="${param.txtSearch}" />
                                                <input type="hidden" name="txtMinPrice" value="${param.txtMinPrice}" />
                                                <input type="hidden" name="txtMaxPrice" value="${param.txtMaxPrice}" />
                                                <input type="hidden" name="category" value="${param.category}" />
                                                <input 
                                                    class="btn shadow-sm" 
                                                    style="background-color: #FF8C00; color: #F0F8FF;" 
                                                    type="submit" value="Add to Cart" name="btAction" onclick="AddSuccess()"
                                                    />
                                            </td>
                                        </c:if>
                                        <script>
                                            function AddSuccess() {
                                                alert('Added!'); 
                                            };
                                        </script>
                                    </c:if>
                                </tr>
                            </form>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
        <c:if test="${empty result}">
            <div class="pl-3" style="font-size: 1.5rem;">
                No record !
            </div>
        </c:if>
    </body>
</html>
