<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${not empty sessionScope.language}">
    <fmt:setLocale value="${sessionScope.language}"/>
</c:if>
<fmt:setBundle basename="locale"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title><fmt:message key="cart.title"/></title>
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Material Design Bootstrap -->
    <link href="css/mdb.min.css" rel="stylesheet">
    <!-- Your custom styles (optional) -->
    <link href="css/style.min.css" rel="stylesheet">
    <link href="css/cart.css" rel="stylesheet" type="text/css">
    <link href="css/fullstyle.css" rel="stylesheet">
</head>

<body>

<!-- Navbar -->
<%@include file="../jspheader/nav.jsp" %>
<!-- Navbar -->

<h1><fmt:message key="cart.cart"/></h1>
<c:if test="${not empty errorMessage}">
    <div class="text-center text-warning">
        <h2 class="text">
            <fmt:message key="${errorMessage}"/>
        </h2>
    </div>
</c:if>
<div class="shopping-cart">

    <div class="column-labels">
        <label class="product-details"><fmt:message key="cart.product"/></label>
        <label class="product-price"><fmt:message key="cart.price"/></label>
<%--                    <label class="product-quantity">Quantity</label>--%>
        <label class="product-removal"><fmt:message key="cart.removing"/></label>
        <label class="product-line-price"><fmt:message key="cart.total"/></label>
    </div>

    <c:if test="${empty orderList}">
        <h1><fmt:message key="cart.empty_cart"/></h1>
    </c:if>

    <c:forEach var="order" items="${orderList}">
        <div class="product">
            <div class="product-details">
                <div class="product-title">${order.drink.title}</div>
                <p><fmt:message key="cart.${order.drink.drinkSize}"/></p>
            </div>
            <div class="product-price">${order.drink.price}</div>
<%--                            <div class="product-quantity">--%>
<%--                                <input type="number" value="1" min="1">--%>
<%--                            </div>--%>
            <div class="product-removal">
                <form action="coffee_machine" method="post">
                    <input type="hidden" name="drinkId" value="${order.id}">
                    <button type="submit" class="remove-product" name="command" value="DELETE_DRINK_FROM_CART">
                        <fmt:message key="cart.remove"/>
                    </button>
                </form>
            </div>
            <div class="product-line-price">${order.drink.price}</div>
        </div>
    </c:forEach>

<%--    <div class="totals">--%>
<%--        <div class="totals-item">--%>
<%--            <label>Total</label>--%>
<%--            <div class="totals-value" id="cart-subtotal">0</div>--%>
<%--        </div>--%>
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
<%--    </div>--%>
<%--    <input id="getTotalPrice" type="button" class="checkout" value="Get total">--%>

    <form action="coffee_machine" method="post">
        <button id="checkoutButton" type="submit" class="checkout" name="command" value="CHECKOUT_CART">
            <fmt:message key="cart.checkout"/>
        </button>
    </form>

</div>


</body>
</html>

