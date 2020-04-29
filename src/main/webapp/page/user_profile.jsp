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
        <link href="css/profile_style.css" rel="stylesheet" type="text/css">

    </head>
<body>
<!-- Navbar -->
<%@include file="jspheader/nav.jsp" %>
<!-- Navbar -->

<div class="shift">
    <div class="container emp-profile">
        <form action="controller" method="get">
            <div class="row">
                <div class="col-md-4">
                </div>

                <div class="col-md-6">
                    <div class="profile-head">
                        <h5>
                            ${user.name}
                        </h5>

                        <ul class="nav nav-tabs" id="myTab">
                            <li class="nav-item">
                                <a class="nav-link active" id="home-tab" data-toggle="tab" data-target="#about"
                                   role="tab"
                                   href=""><label>About</label></a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" id="profile-tab" data-toggle="tab" data-target="#history" href=""
                                   role="tab">
                                    <label>Order History</label></a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-2">
                    <button type="submit" name="command" value="EDIT_PROFILE_PAGE" class="btn profile-edit-btn">
                        Edit Profile
                    </button>
                    <%--                    <input type="submit" class="profile-edit-btn" name="command" value="EDIT_PROFILE">Edit Profile</input>--%>
                </div>
            </div>

            <div class="row">
                <div class="col-md-4">
                    <div class="profile-work">
                    </div>
                </div>

                <div class="col-md-8">
                    <div class="tab-content profile-tab" id="myTabContent">

                        <div class="tab-pane fade show active" id="about" role="tabpanel" aria-labelledby="home-tab">
                            <div class="row">
                                <div class="col-md-6">
                                    <label>Name</label>
                                </div>
                                <div class="col-md-6">
                                    <p>${user.name}</p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <label>Email</label>
                                </div>
                                <div class="col-md-6">
                                    <p>${user.email}</p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <label>Card</label>
                                </div>
                                <div class="col-md-6">
                                    <c:if test="${user.cardAccount == null}">
                                        <p>Card is not attached yet</p>
                                    </c:if>
                                    <c:if test="${user.cardAccount == null}">
                                        <p>${user.cardAccount}</p>
                                        <%--                                        <input type="submit" class="profile-edit-btn" name="btnAddCard" value="Add card"/>--%>
                                    </c:if>
                                </div>
                            </div>
                        </div>

                        <div class="tab-pane fade" id="history" role="tabpanel" aria-labelledby="profile-tab">
                            <c:if test="${history == null}">
                                <p>Оrder list is empty</p>
                            </c:if>
                            <c:if test="${history != null}">
                                <div class="row">
                                    <div class="col-md-6">
                                        <label></label>
                                    </div>
                                    <div class="col-md-6">
                                        <p></p>
                                    </div>
                                </div>
                            </c:if>

                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
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
<!-- Initializations -->
<script type="text/javascript">
    // Animations initialization
    new WOW().init();
</script>

</body>
</html>
