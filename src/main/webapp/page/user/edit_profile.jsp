<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${not empty sessionScope.language}">
    <fmt:setLocale value="${sessionScope.language}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title><fmt:message key="edit_profile.title"/></title>
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Material Design Bootstrap -->
    <link href="css/mdb.min.css" rel="stylesheet">
    <!-- Your custom styles (optional) -->
    <link href="css/style.min.css" rel="stylesheet">
    <style type="text/css">
        /* Necessary for full page carousel*/
        html,
        body,
        header,
        .view {
            height: 100%;
            background-image: url(${pageContext.request.contextPath}/img/reg_back.jpg);
            background-position: center;
            background-repeat: no-repeat;
            background-size: cover;
        }

        form {
            width: 60%;
            margin: 60px auto;
            /*background: ;*/
            padding: 60px 120px 80px 120px;
            /*text-align: center;*/
            -webkit-box-shadow: 2px 2px 3px rgba(0,0,0,0.1);
            box-shadow: 2px 2px 3px rgba(0,0,0,0.1);
        }

        @media (min-width: 800px) and (max-width: 850px) {
            .navbar:not(.top-nav-collapse) {
                background: #1C2331 !important;
            }
        }

        .content {
            position: fixed;
            bottom: 0;
            background: rgba(0, 0, 0, 0.5);
            color: #f1f1f1;
            width: 100%;
            padding: 20px;
        }


    </style>
</head>
<body>
<%@include file="../jspheader/nav.jsp" %>

<section class="view card brown wow fadeIn" id="intro">

    <!-- Content -->
    <div class="card-body text-white  py-5 px-5 my-5">


        <form class="brown" action="coffee_machine" method="post">
            <c:if test="${not empty errorMessage}">
                <div class="text-center text-warning">
                    <label class="text">
                        <fmt:message key="${errorMessage}"/>
                    </label>
                </div>
            </c:if>
            <div class="form-group">
                <input type="hidden" class="form-control" id="id" name="userId" value="${user.id}">
            </div>
            <div class="form-group">
                <label for="inputName"><fmt:message key="edit_profile.name"/></label>
                <input type="text" class="form-control" id="inputName" name="userName" value="${user.name}" required>
            </div>
            <div class="form-group">
                <label for="inputEmail"><fmt:message key="edit_profile.email"/></label>
                <input type="email" class="form-control" id="inputEmail" name="userEmail" value="${user.email}" required>
            </div>
            <div class="form-group">
                <label for="inputPassword"><fmt:message key="edit_profile.password"/></label>
                <input type="password" class="form-control" id="inputPassword" name="userPassword">
            </div>
            <button type="submit" class="btn btn-outline-white btn-lg" name="command"
                    value="EDIT_USER_PROFILE"><fmt:message key="edit_profile.save"/></button>
        </form>
    </div>
    <!-- Content -->
</section>
</body>
</html>
