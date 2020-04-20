<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title><fmt:message key="title"/></title>
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
            .active-form {
                width: 60%;
                margin: 60px auto;
                /*background: ;*/
                padding: 60px 120px 80px 120px;
            }

        </style>

    </head>
<body>
<!-- Navbar -->
<%@include file="jspheader/nav.jsp" %>
<!-- Navbar -->
<form class="active-form">
    <h1>Name: ${userProfile.name}</h1>
    <h1>Email: ${userProfile.email}</h1>
    <h1>Role: ${userProfile.role}</h1>
    <c:if test="${userProfile.activity.equals(true)}">
        <td>
            <form action="controller" method="POST">
                <input type="hidden" id="BlockUserID" name="userID" value="${userProfile.id}">
                <button type="submit" name="command" value="BLOCK_USER"
                        class="btn btn-outline-danger">
                    Block user
                </button>
            </form>
        </td>
    </c:if>

    <c:if test="${!userProfile.activity.equals(true)}">
        <td>
            <form action="controller" method="POST">
                <input type="hidden" id="UnblockUserID" name="userID" value="${userProfile.id}">
                <button type="submit" name="command" value="UNBLOCK_USER"
                        class="btn btn-outline-success">
                    Unblock user
                </button>
            </form>
        </td>
    </c:if>
</form>
</body>
</html>