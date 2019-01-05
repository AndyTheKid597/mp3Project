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

        <!-- Fontfaces CSS-->
        <link href="../assets/css/font-face.css" rel="stylesheet" media="all">
        <link href="../assets/vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
        <link href="../assets/vendor/font-awesome-5/css/fontawesome-all.min.css" rel="stylesheet" media="all">
        <link href="../assets/vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">

        <!-- Bootstrap CSS-->
        <link href="../assets/vendor/bootstrap-4.1/bootstrap.min.css" rel="stylesheet" media="all">

        <!-- Vendor CSS-->
        <link href="../assets/vendor/animsition/animsition.min.css" rel="stylesheet" media="all">
        <link href="../assets/vendor/bootstrap-progressbar/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet" media="all">
        <link href="../assets/vendor/wow/animate.css" rel="stylesheet" media="all">
        <link href="../assets/vendor/css-hamburgers/hamburgers.min.css" rel="stylesheet" media="all">
        <link href="../assets/vendor/slick/slick.css" rel="stylesheet" media="all">
        <link href="../assets/vendor/select2/select2.min.css" rel="stylesheet" media="all">
        <link href="../assets/vendor/perfect-scrollbar/perfect-scrollbar.css" rel="stylesheet" media="all">

        <!-- Main CSS-->
        <link href="../assets/css/theme.css" rel="stylesheet" media="all">

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

                                <div class="col-lg-12">
                                <div class="card">
                                    <div class="card-header">Chanson</div>
                                    <div class="card-body">
                                        <div class="card-title">
                                            <h3 class="text-center title-2">Modifier la chanson</h3>
                                        </div>
                                        <hr>
                                        <c:if test="${ !empty succ }">
                                    <div class="sufee-alert alert with-close alert-primary alert-dismissible fade show">
                                        Chanson modifieee
                                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                            <span aria-hidden="true">×</span>
                                        </button>
                                    </div>
                                </c:if>
                                        <form:form action="${action}" modelAttribute="chanson">
                                            <form:input hidden="true" path="id"/>
                                            <div class="form-group">
                                                <form:label path="nomfichier">
                                                        <spring:message text="Nom du fichier"/>
                                                </form:label>
                                                <form:input readonly="true" path="nomfichier" class="form-control cc-name valid"/>
                                            </div>
                                            <div class="form-group">
                                                <form:label path="titre">
                                                        <spring:message text="Titre"/>
                                                </form:label>
                                                <form:input  path="titre" class="form-control cc-name valid"/>
                                            </div>
                                            <div class="form-group">
                                                <form:label path="auteur">
                                                        <spring:message text="Auteur"/>
                                                </form:label>
                                                <form:input  path="auteur" class="form-control cc-name valid"/>
                                            </div>
                                            <div class="form-group">
                                                <form:label path="album">
                                                        <spring:message text="Album"/>
                                                </form:label>
                                                <form:input path="Album" class="form-control cc-name valid"/>
                                            </div>
                                            <div class="form-group">
                                                <form:label path="genre">
                                                        <spring:message text="Genre"/>
                                                </form:label>
                                                <form:input  path="genre" class="form-control cc-name valid"/>
                                            </div>
                                            <div class="form-group">
                                                <form:label path="duration">
                                                        <spring:message text="Duration"/>
                                                </form:label>
                                                <form:input readonly="true" path="duration" class="form-control cc-name valid"/>
                                            </div>
                                                                                        <div class="form-group">
                                                <form:label path="image">
                                                        <spring:message text="Image"/>
                                                </form:label>
                                                <form:input path="image" class="form-control cc-name valid"/>
                                            </div>
                                            <div>
                                                <form:button  class="btn btn-lg btn-info btn-block">
                                                    
                                                    <span id="payment-button-amount">Modifier</span>
                                                    
                                                </form:button>
                                            </div>
                                        </form:form>
                                    </div>
                                </div>
                            </div>
                                
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="copyright">
                                        <p>Copyright © 2018 Colorlib. All rights reserved. Template by <a href="https://colorlib.com">Colorlib</a>.</p>
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
        <script src="../assets/vendor/jquery-3.2.1.min.js"></script>
        <!-- Bootstrap JS-->
        <script src="../assets/vendor/bootstrap-4.1/popper.min.js"></script>
        <script src="../assets/vendor/bootstrap-4.1/bootstrap.min.js"></script>
        <!-- Vendor JS       -->
        <script src="../assets/vendor/slick/slick.min.js">
        </script>
        <script src="../assets/vendor/wow/wow.min.js"></script>
        <script src="../assets/vendor/animsition/animsition.min.js"></script>
        <script src="../assets/vendor/bootstrap-progressbar/bootstrap-progressbar.min.js">
        </script>
        <script src="../assets/vendor/counter-up/jquery.waypoints.min.js"></script>
        <script src="../assets/vendor/counter-up/jquery.counterup.min.js">
        </script>
        <script src="../assets/vendor/circle-progress/circle-progress.min.js"></script>
        <script src="../assets/vendor/perfect-scrollbar/perfect-scrollbar.js"></script>
        <script src="../assets/vendor/chartjs/Chart.bundle.min.js"></script>
        <script src="../assets/vendor/select2/select2.min.js">
        </script>

        <!-- Main JS-->
        <script src="../assets/js/main.js"></script>

    </body>

</html>
<!-- end document-->
