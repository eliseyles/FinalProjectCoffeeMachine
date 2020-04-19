<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="content"/>

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

    </style>
</head>

<body>

<!-- Navbar -->
<%@include file="jspheader/nav.jsp" %>
<!-- Navbar -->

<%--<!--Carousel Wrapper-->--%>
<%--<div id="carousel-example-1z" class="carousel slide carousel-fade">--%>

<%--    <!--Slides-->--%>
<%--    <div class="carousel-inner" role="listbox">--%>

<%--        &lt;%&ndash;        <!--First slide-->&ndash;%&gt;--%>
<%--        <div class="carousel-item active">--%>
<div class="view">


    <video class="video-intro" autoplay loop muted>
        <source src="${pageContext.request.contextPath}/img/coffee_back.mp4" type="video/mp4"/>
    </video>

<%--    <div class="content">--%>
<%--        <form action="controller" method="post">--%>
<%--            <div class="col-sm-10">--%>
<%--                <button type="submit" class="btn btn-outline-white btn-lg" name="command"--%>
<%--                        value="REGISTRATION">--%>
<%--                    Registration--%>
<%--                </button>--%>
<%--                &lt;%&ndash;                                            <button type="submit" class="btn-outline-white btn-lg" name="command" value="parse">Parse</button>&ndash;%&gt;&ndash;%&gt;--%>
<%--            </div>--%>
<%--        </form>--%>
<%--    </div>--%>


<%--                --%>
<%--    <div class="content">--%>
<%--        <form action="controller" method="post" enctype='multipart/form-data'>--%>

<%--            <div class="container white-text mt-4 wow fadeIn">--%>

<%--                <!-- Section: Main info-->--%>
<%--                <section class="mt-5 wow fadeIn">--%>
<%--                    <div class="mb-4">--%>
<%--                        <!-- Main heading -->--%>
<%--                        <h3 class="h3 mb-3">Input XML file to parse</h3>--%>

<%--                        <input type="file" class="form-control-file white-text btn-lg" id="file" name="file"--%>
<%--                               accept=".xml">--%>

<%--                        <hr>--%>

<%--                        <fieldset class="form-group">--%>
<%--                            <div class="row">--%>
<%--                                <!--                          <legend class="col-form-label col-sm-2 pt-0">Radios</legend>-->--%>
<%--                                <div class="col-sm-10">--%>
<%--                                    <div class="form-check">--%>
<%--                                        <input class="form-check-input border-left-1" type="radio"--%>
<%--                                               name="command" id="SAX_PARSE"--%>
<%--                                               value="SAX_PARSE">--%>
<%--                                        <label class="form-check-label white-text" for="SAX_PARSE">--%>
<%--                                            SAX PARSE--%>
<%--                                        </label>--%>
<%--                                    </div>--%>
<%--                                    <div class="form-check">--%>
<%--                                        <input class="form-check-input" type="radio" name="command"--%>
<%--                                               id="DOM_PARSE"--%>
<%--                                               value="DOM_PARSE">--%>
<%--                                        <label class="form-check-label white-text" for="DOM_PARSE">--%>
<%--                                            DOM PARSE--%>
<%--                                        </label>--%>
<%--                                    </div>--%>
<%--                                    <div class="form-check">--%>
<%--                                        <input class="form-check-input" type="radio" name="command"--%>
<%--                                               id="STAX_PARSE"--%>
<%--                                               value="STAX_PARSE">--%>
<%--                                        <label class="form-check-label white-text" for="STAX_PARSE">--%>
<%--                                            STAX PARSE--%>
<%--                                        </label>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </fieldset>--%>
<%--                        <div class="form-group row">--%>
<%--                            <div class="col-sm-10">--%>
<%--                                <button type="submit" class="btn btn-outline-white btn-lg" name="command"--%>
<%--                                        value="parse">--%>
<%--                                    Parse--%>
<%--                                </button>--%>
<%--                                &lt;%&ndash;                                            <button type="submit" class="btn-outline-white btn-lg" name="command" value="parse">Parse</button>&ndash;%&gt;&ndash;%&gt;--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </section>--%>
<%--                <!--Section: Main info-->--%>
<%--            </div>--%>
<%--        </form>--%>
<%--    </div>--%>

</div>

<%--            </div>--%>

<%--        </div>--%>


<%--    </div>--%>
<%--</div>--%>

<%--<!--/.Footer-->--%>

<%--<!-- SCRIPTS -->--%>
<%--<!-- JQuery -->--%>
<%--<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>--%>
<%--<!-- Bootstrap tooltips -->--%>
<%--<script type="text/javascript" src="js/popper.min.js"></script>--%>
<%--<!-- Bootstrap core JavaScript -->--%>
<%--<script type="text/javascript" src="js/bootstrap.min.js"></script>--%>
<%--<!-- MDB core JavaScript -->--%>
<%--<script type="text/javascript" src="js/mdb.min.js"></script>--%>
<%--<!-- Initializations -->--%>
<%--<script type="text/javascript">--%>
<%--    // Animations initialization--%>
<%--    new WOW().init();--%>
<%--</script>--%>
</body>

</html>

