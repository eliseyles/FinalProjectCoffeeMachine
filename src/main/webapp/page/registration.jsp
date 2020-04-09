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

<form  action="controller" method="post">
    <input type="text" name="uname" value="Name..." onclick="this.value=''"/><br/>
    <input type="text" name="uemail" value="Email ID..." onclick="this.value=''"/><br/>
    <input type="password" name="upass" value="Password..." onclick="this.value=''"/><br/>
    <button type="submit" class="btn" name="command" value="INDEX_PAGE">
        Register
    </button>
</form>
</body>
</html>
