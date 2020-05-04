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
    <link href="css/cart.css" rel="stylesheet" type="text/css">

</head>

<body>

<!-- Navbar -->
<%@include file="jspheader/nav.jsp" %>
<!-- Navbar -->

<h1>Shopping Cart</h1>

<div class="shopping-cart">

    <div class="column-labels">
        <label class="product-details">Product</label>
        <label class="product-price">Price</label>
<%--                    <label class="product-quantity">Quantity</label>--%>
        <label class="product-removal">Remove</label>
        <label class="product-line-price">Total</label>
    </div>

    <c:if test="${empty orderList}">
        <h1>Your cart is empty</h1>
    </c:if>

    <c:forEach var="order" items="${orderList}">
        <div class="product">
            <div class="product-details">
                <div class="product-title">${order.drink.title}</div>
                <p>${order.drink.drinkSize}</p>
            </div>
            <div class="product-price">${order.drink.price}</div>
<%--                            <div class="product-quantity">--%>
<%--                                <input type="number" value="1" min="1">--%>
<%--                            </div>--%>
            <div class="product-removal">
                <form action="controller" method="post">
                    <input type="hidden" name="drinkId" value="${order.id}">
                    <button type="submit" class="remove-product" name="command" value="DELETE_DRINK_FROM_CART">
                        Remove
                    </button>
                </form>
            </div>
            <div class="product-line-price">${order.drink.price}</div>
        </div>
    </c:forEach>

    <div class="totals">
        <div class="totals-item">
            <label>Total</label>
            <div class="totals-value" id="cart-subtotal">0</div>
        </div>
        <%--            <div class="totals-item">--%>
        <%--                <label>Tax (5%)</label>--%>
        <%--                <div class="totals-value" id="cart-tax">3.60</div>--%>
        <%--            </div>--%>
        <%--            <div class="totals-item">--%>
        <%--                <label>Shipping</label>--%>
        <%--                <div class="totals-value" id="cart-shipping">15.00</div>--%>
        <%--            </div>--%>
<%--        <div class="totals-item totals-item-total">--%>
<%--            <label>Grand Total</label>--%>
<%--            <div class="totals-value" id="cart-total">0</div>--%>
<%--        </div>--%>
    </div>

    <button class="checkout">Checkout</button>

</div>

<!-- SCRIPTS -->
<!-- JQuery -->
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<!-- Bootstrap tooltips -->
<script type="text/javascript" src="js/popper.min.js"></script>
<!-- Bootstrap core JavaScript -->
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<!-- MDB core JavaScript -->
<script type="text/javascript" src="js/mdb.min.js"></script>
<script type="text/javascript" src="js/cart.js"></script>
<!-- Initializations -->
<script type="text/javascript">
    // Animations initialization
    new WOW().init();
</script>
</body>
</html>

