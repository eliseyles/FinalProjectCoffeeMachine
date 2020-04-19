<%--
  Created by IntelliJ IDEA.
  User: Елисей
  Date: 3/29/2020
  Time: 8:55 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en">

<html>
<head>
    <title>Registration</title>
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


        .modal{
            /*padding: 50px;*/
            background: #ff3d00;
            position: fixed; top: 50%; left: 50%;
        }


    </style>
</head>
<body>
<%@include file="jspheader/nav.jsp" %>

<section class="view card brown wow fadeIn" id="intro">

    <!-- Content -->
    <div class="card-body text-white  py-5 px-5 my-5">


        <form class="brown" action="controller" method="post">
            <c:if test="${not empty errorMessage}">
                <div class="text-center text-warning">
                    <label class="text">
                        ${errorMessage}
                            <%--                    <fmt:message key="${error}"/>--%>
                    </label>
                </div>
            </c:if>
            <div class="form-group">
                <label for="inputName">Name</label>
                <input type="text" class="form-control" id="inputName" name="userName">
            </div>
            <div class="form-group">
                <label for="inputEmail">Email address</label>
                <input type="email" class="form-control" id="inputEmail" name="userEmail" aria-describedby="emailHelp">
                <small id="emailHelp" class="form-text  text-white">We'll never share your email with anyone else.</small>
            </div>
            <div class="form-group">
                <label for="inputPassword">Password</label>
                <input type="password" class="form-control" id="inputPassword" name="userPassword">
            </div>
            <button type="submit" class="btn btn-outline-white btn-lg" name="command"
                    value="REGISTER">Submit</button>
        </form>
    </div>
    <!-- Content -->
</section>
</body>
</html>
