<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html lang="en">

    <head>
        <!-- Required meta tags-->
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="au theme template">
        <meta name="author" content="Hau Nguyen">
        <meta name="keywords" content="au theme template">

        <!-- Title Page-->
        <title>Charts</title>
        <spring:url var="lien_assets" value="/admin/assets/"/>
        <!-- Fontfaces CSS-->
        <link href="${lien_assets}/css/font-face.css" rel="stylesheet" media="all">
        <link href="${lien_assets}/vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
        <link href="${lien_assets}/vendor/font-awesome-5/css/fontawesome-all.min.css" rel="stylesheet" media="all">
        <link href="${lien_assets}/vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">

        <!-- Bootstrap CSS-->
        <link href="${lien_assets}/vendor/bootstrap-4.1/bootstrap.min.css" rel="stylesheet" media="all">

        <!-- Vendor CSS-->
        <link href="${lien_assets}/vendor/animsition/animsition.min.css" rel="stylesheet" media="all">
        <link href="${lien_assets}/vendor/bootstrap-progressbar/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet" media="all">
        <link href="${lien_assets}/vendor/wow/animate.css" rel="stylesheet" media="all">
        <link href="${lien_assets}/vendor/css-hamburgers/hamburgers.min.css" rel="stylesheet" media="all">
        <link href="${lien_assets}/vendor/slick/slick.css" rel="stylesheet" media="all">
        <link href="${lien_assets}/vendor/select2/select2.min.css" rel="stylesheet" media="all">
        <link href="${lien_assets}/vendor/perfect-scrollbar/perfect-scrollbar.css" rel="stylesheet" media="all">

        <!-- Main CSS-->
        <link href="${lien_assets}/css/theme.css" rel="stylesheet" media="all">

    </head>

    <body class="animsition">
        <div class="page-wrapper">


            <c:import  url="/WEB-INF/jsp/includables/menu_sisiny.jsp"/>


            <!-- END MENU SIDEBAR-->

            <!-- PAGE CONTAINER-->
            <div class="page-container">
                <c:import  url="/WEB-INF/jsp/includables/header.jsp"/>
                <!-- MAIN CONTENT-->
                <div class="main-content">
                    <div class="section__content section__content--p30">
                        <div class="container-fluid">

                            <div class="row">
                                <div class="col-md-6 col-lg-4">
                                    <!-- TOP CAMPAIGN-->
                                    <div class="top-campaign">
                                        <h3 class="title-3 m-b-30">Chansons les plus ecoutees</h3>
                                        <div class="table-responsive">
                                            <table class="table table-top-campaign">
                                                <tbody>
                                                    <c:set var="ranghira" value="1"/>
                                                    <c:forEach items="${topChansons.resultats}" var="sg" >
                                                    <tr>
                                                        <td>${ranghira}. ${sg.titre}</td>
                                                        <td>${sg.counter}</td>
                                                    </tr>
                                                    <c:set var="ranghira" value="${ranghira + 1}"/>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <!-- END TOP CAMPAIGN-->
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="copyright">
                                        <p>Copyright � 2018 Colorlib. All rights reserved. Template by <a href="https://colorlib.com">Colorlib</a>.</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- END MAIN CONTENT-->
            </div>
            <!-- END PAGE CONTAINER-->

        </div>

        <!-- Jquery JS-->
        <script src="${lien_assets}/vendor/jquery-3.2.1.min.js"></script>
        <!-- Bootstrap JS-->
        <script src="${lien_assets}/vendor/bootstrap-4.1/popper.min.js"></script>
        <script src="${lien_assets}/vendor/bootstrap-4.1/bootstrap.min.js"></script>
        <!-- Vendor JS       -->
        <script src="${lien_assets}/vendor/slick/slick.min.js">
        </script>
        <script src="${lien_assets}/vendor/wow/wow.min.js"></script>
        <script src="${lien_assets}/vendor/animsition/animsition.min.js"></script>
        <script src="${lien_assets}/vendor/bootstrap-progressbar/bootstrap-progressbar.min.js">
        </script>
        <script src="${lien_assets}/vendor/counter-up/jquery.waypoints.min.js"></script>
        <script src="${lien_assets}/vendor/counter-up/jquery.counterup.min.js">
        </script>
        <script src="${lien_assets}/vendor/circle-progress/circle-progress.min.js"></script>
        <script src="${lien_assets}/vendor/perfect-scrollbar/perfect-scrollbar.js"></script>
        <script src="${lien_assets}/vendor/chartjs/Chart.bundle.min.js"></script>
        <script src="${lien_assets}/vendor/select2/select2.min.js">
        </script>

        <!-- Main JS-->
        <script src="${lien_assets}/js/main.js"></script>

    </body>

</html>
<!-- end document-->
