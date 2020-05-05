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
            header{}

            form {
                width: 60%;
                margin: 60px auto;
                /*background: ;*/
                padding: 60px 120px 80px 120px;
                /*text-align: center;*/
                -webkit-box-shadow: 2px 2px 3px rgba(0, 0, 0, 0.1);
                box-shadow: 2px 2px 3px rgba(0, 0, 0, 0.1);
            }

        </style>

    </head>
<body>
<!-- Navbar -->
<%@include file="jspheader/nav.jsp" %>
<!-- Navbar -->
<form>
    <h5>Name: ${userProfile.name}</h5>
    <h5>Email: ${userProfile.email}</h5>
    <h5>Role: ${userProfile.role}</h5>
    <c:if test="${userProfile.activity.equals(true)}">
        <td>
            <form action="coffee_machine" method="POST">
                <input type="hidden" id="BlockUserID" name="userId" value="${userProfile.id}">
                <button type="submit" name="command" value="BLOCK_USER"
                        class="btn btn-outline-danger">
                    Block user
                </button>
            </form>
        </td>
    </c:if>

    <c:if test="${!userProfile.activity.equals(true)}">
        <td>
            <form action="coffee_machine" method="POST">
                <input type="hidden" id="UnblockUserID" name="userId" value="${userProfile.id}">
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
