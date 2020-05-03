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
        .content {
            /*height: 50%;*/
            padding: 40px 50px 10px 50px;
        }

    </style>
</head>

<body>

<!-- Navbar -->
<%@include file="jspheader/nav.jsp" %>
<!-- Navbar -->

<form class="content">

    <c:if test="${empty orderList}">
        <h1>Your cart is empty</h1>
    </c:if>


    <c:forEach var="order" items="${orderList}">


        <div class="card h-100 text-center">
                <%--                    <img class="card-img-top">--%>

            <div class="card-body">
                <div class="row">
                    <div class="col-md-4">
                        <h3 class="card-title"><strong>${order.drink.title}</strong></h3>
                    </div>
                    <div class="col-md-4">
                        <h6 class="card-title">${order.drink.drinkSize}</h6>
                    </div>
                    <div class="col-md-4">
                        <h6 class="card-title">${order.drink.price}</h6>
                    </div>
                </div>
            </div>

        </div>

    </c:forEach>

    <%--    </div>--%>


</form>

</html>

