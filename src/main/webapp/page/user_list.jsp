<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
        .view {
            height: 50%;
            top: 150px;
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

        form {
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


<%--<div class="view">--%>
<form>
    <div>
        <table class="table">
            <thead class="thead ">
            <tr>
                <th scope="col">User name</th>
                <th scope="col">User role</th>
                <th scope="col">Activity</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="user" items="${userList}">
                <tr>
                    <form action="coffee_machine" method="post">
                        <input type="hidden" name="userId" value="${user.id}"/>
                        <td>${user.name}</td>
                        <td>${user.role}</td>
                        <td>${user.activity}</td>
                        <td>
                            <button type="submit" class="btn" name="command" value="USER_EDIT">Edit user</button>
                        </td>
                    </form>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</form>


<%--</div>--%>

</body>

</html>

