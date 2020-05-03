<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<nav class="navbar navbar-expand-lg navbar-light bg-light">--%>
<%--    <div class="container-fluid">--%>
<%--        <div class="bd-example">--%>
<%--            <ul class="nav nav-pills">--%>
<%--                <li class="nav-item active">--%>
<%--                    <a class="nav-link" href="controller?command=INDEX_PAGE">Home</a>--%>
<%--                </li>--%>
<%--            </ul>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</nav>--%>

<nav class="navbar fixed-top navbar-expand-lg navbar-dark scrolling-navbar">
    <div class="container">

        <!-- Brand -->
        <a class="navbar-brand text-white" href="controller?command=INDEX_PAGE">
            <strong>Coffee machine</strong>
        </a>
        <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item ">
                    <a class="nav-link" href="controller?command=INDEX_PAGE">
                        Home
                    </a>
                </li>
                <li class="nav-item ">
                    <a class="nav-link" href="controller?command=DRINK_LIST">
                        Drinks
                    </a>
                </li>
                <c:if test="${user != null}">
                    <li class="nav-item ">
                        <a class="nav-link" href="controller?command=ORDERS_PAGE">
                            Orders
                        </a>
                    </li>
                </c:if>
                <c:if test="${user.role == 'ADMIN'}">
                    <li class="nav-item ">
                        <a class="nav-link" href="controller?command=USER_LIST">
                            User List
                        </a>
                    </li>
                    <li class="nav-item ">
                        <a class="nav-link" href="controller?command=DRINK_MANAGEMENT">
                            Drink Management
                        </a>
                    </li>
                </c:if>
            </ul>
            <ul class="navbar-nav ml-md-auto">
                <c:if test="${user != null}">
                    <li class="nav-item">
                        <a class="nav-link" href="controller?command=USER_PROFILE">
                                ${user.name}
                        </a>
                    </li>


                    <li class="nav-item">
                        <a class="nav-link btn-outline-white mr-1 ml-1" href="controller?command=SIGN_OUT">
                            Sign Out
                        </a>
                    </li>
                </c:if>


                <c:if test="${user == null}">
                    <li class="nav-item">
                        <a class="nav-link btn-outline-white mr-1 ml-1" href="controller?command=SIGN_IN_PAGE">
                            Sign In
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link btn-outline-white mr-1 ml-1" href="controller?command=REGISTRATION_PAGE">
                            Sign Up
                        </a>
                    </li>
                </c:if>
            </ul>

            <!-- Collapse -->
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
        </div>
    </div>
</nav>
