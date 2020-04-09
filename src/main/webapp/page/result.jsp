<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Result</title>
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

        }

        @media (min-width: 800px) and (max-width: 850px) {
            .navbar:not(.top-nav-collapse) {
                background: #1C2331 !important;
            }
        }

        .form {
            background-color: rgba(90, 0, 142, 0.6);
        }
    </style>
</head>

<body>

<!-- Navbar -->
<%@include file="jspheader/nav.jsp" %>
<!-- Navbar -->

<!--Carousel Wrapper-->
<%--<div id="carousel-example-1z" class="carousel slide carousel-fade">--%>

<%--    <!--Slides-->--%>
<%--    <div class="carousel-inner" role="listbox">--%>

<%--        &lt;%&ndash;        <!--First slide-->&ndash;%&gt;--%>
<%--        <div class="carousel-item active">--%>
<%--            <div class="view">--%>
<%--                <!-- Mask & flexbox options-->--%>
<%--                <div class="mask rgba-black-light d-flex justify-content-center align-items-center">--%>

<%--                    <form>--%>
<%--                        <div>--%>
<%--                            <table class="table">--%>
<%--                                <thead class="thead white-text">--%>
<%--                                <tr>--%>
<%--                                    <th scope="col">Candy name</th>--%>
<%--                                    <th scope="col">Candy type</th>--%>
<%--                                    <th scope="col">Production</th>--%>
<%--                                    <th scope="col">Fat</th>--%>
<%--                                    <th scope="col">Protein</th>--%>
<%--                                    <th scope="col">Carbohydrates</th>--%>
<%--                                    <th scope="col">Energy</th>--%>
<%--                                    <th scope="col">Water</th>--%>
<%--                                    <th scope="col">Sugar</th>--%>
<%--                                    <th scope="col">Fructose</th>--%>
<%--                                    <th scope="col">Chocolate type</th>--%>
<%--                                </tr>--%>
<%--                                </thead>--%>

<%--                                <tbody class="white-text">--%>
<%--                                <c:forEach var="candy" items="${candies}">--%>
<%--                                    <tr>--%>
<%--                                        <td>${candy.name}</td>--%>
<%--                                        <td>${candy.candyType.type}</td>--%>
<%--                                        <td>${candy.production}</td>--%>
<%--                                        <td>${candy.value.fat}</td>--%>
<%--                                        <td>${candy.value.protein}</td>--%>
<%--                                        <td>${candy.value.carbohydrates}</td>--%>
<%--                                        <td>${candy.energy}</td>--%>
<%--                                        <td>${candy.ingredient.water}</td>--%>
<%--                                        <td>${candy.ingredient.sugar}</td>--%>
<%--                                        <td>${candy.ingredient.fructose}</td>--%>
<%--                                        <td>${candy.ingredient.chocolateType}</td>--%>
<%--                                    </tr>--%>
<%--                                </c:forEach>--%>
<%--                                </tbody>--%>
<%--                            </table>--%>
<%--                        </div>--%>

<%--                    </form>--%>

<%--                </div>--%>

<%--            </div>--%>

<%--        </div>--%>


<%--    </div>--%>
<%--</div>--%>
<form>
    <div>
        <table class="table">
            <thead class="thead white-text">
            <tr>
                <th scope="col">Candy name</th>
                <th scope="col">Candy type</th>
                <th scope="col">Production</th>
                <th scope="col">Fat</th>
                <th scope="col">Protein</th>
                <th scope="col">Carbohydrates</th>
                <th scope="col">Energy</th>
                <th scope="col">Water</th>
                <th scope="col">Sugar</th>
                <th scope="col">Fructose</th>
                <th scope="col">Chocolate type</th>
            </tr>
            </thead>

            <tbody class="white-text">
            <c:forEach var="candy" items="${candies}">
                <tr>
                    <td>${candy.name}</td>
                    <td>${candy.candyType.type}</td>
                    <td>${candy.production}</td>
                    <td>${candy.value.fat}</td>
                    <td>${candy.value.protein}</td>
                    <td>${candy.value.carbohydrates}</td>
                    <td>${candy.energy}</td>
                    <td>${candy.ingredient.water}</td>
                    <td>${candy.ingredient.sugar}</td>
                    <td>${candy.ingredient.fructose}</td>
                    <td>${candy.ingredient.chocolateType}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

</form>

<!--/.Footer-->

<!-- SCRIPTS -->
<!-- JQuery -->
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<!-- Bootstrap tooltips -->
<script type="text/javascript" src="js/popper.min.js"></script>
<!-- Bootstrap core JavaScript -->
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<!-- MDB core JavaScript -->
<script type="text/javascript" src="js/mdb.min.js"></script>
<!-- Initializations -->
<script type="text/javascript">
    // Animations initialization
    new WOW().init();
</script>
</body>

</html>
