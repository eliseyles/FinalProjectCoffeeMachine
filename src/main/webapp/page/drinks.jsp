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

    <c:if test="${empty drinkList}">
        <h1>Sorry, we don't have any positions now</h1>
    </c:if>
<%--    <div class="card-deck">--%>
        <div class="row row-cols-1 row-cols-md-4">
            <c:forEach var="drink" items="${drinkList}">

                <div class="col-md-4 mb-4">
                    <div class="card h-100">
                            <%--                    <img class="card-img-top">--%>
                        <div class="card-body">
                            <h5 class="card-title">${drink.title}</h5>
                            <h6 class="card-subtitle mb-2 text-muted">${drink.drinkSize}</h6>
                            <h6 class="card-title">${drink.price}</h6>
                            <form action="controller" method="post">
                                <input type="hidden" name="drinkID" value="${drink.id}"/>
                                <button type="submit" class="btn btn-sm">Add to cart</button>
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
<%--    </div>--%>


</form>

</body>

</html>

