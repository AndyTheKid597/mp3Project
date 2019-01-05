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
        <title>Statut</title>
        <spring:url var="lien_assets" value="/admin/assets"/>
        <!-- Fontfaces CSS-->
        <link href="${lien_assets}/css/jquery.lineProgressbar.css"></script>
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
                            <div class="col-md-12">
                                <div class="alert alert-primary" id="div_no_sync"  role="alert">
                                    Aucune Synchronisation en cours
                                </div>
                                <div class="alert alert-danger" id="div_syncing" style="display:none;" role="alert">
                                    Synchronisation en cours
                                    <div class="col-md-12">
                                        <div class="progress mb-3">
                                            <div class="progress-bar bg-info progress-bar-striped progress-bar-animated" id="progressbar1" role="progressbar" style="width: 0%" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="alert alert-success" id="div_synced" style="display:none;" role="alert">
                                    Synchronisation terminee
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-lg-6">
                                <div class="au-card au-card--no-shadow au-card--no-pad m-b-40 au-card--border">
                                    <div class="au-card-title" style="background-image:url('images/bg-title-01.jpg');">
                                        <div class="bg-overlay bg-overlay--blue"></div>
                                        <h3>
                                            <i class="zmdi"></i>Statut</h3>

                                    </div>
                                    <div class="au-task js-list-load au-task--border">
                                        <div class="au-task__title">
                                            <p>Logs:</p>
                                        </div>
                                        <div class="au-task-list js-scrollbar3" id="div_anatiny">
                                        
                                            
                                            
                                        </div>

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
    <script src="${lien_assets}/js/jquery.lineProgressbar.js"></script>

    <script>
        $('#progressbar1').width('0%');
        function terminee() {
            $('#div_synced').show();
            $('#div_no_sync').hide();
            $('#div_syncing').hide();
        }

        function encours() {
            $('#div_synced').hide();
            $('#div_no_sync').hide();
            $('#div_syncing').show();
        }

        function attente() {
            $('#div_synced').hide();
            $('#div_no_sync').show();
            $('#div_syncing').hide();
        }

         function makediv(message){
var res = message.split(";");
var rt="";
res.forEach(function(ray){
        rt='<div class="au-task__item au-task__item--success"> <div class="au-task__item-inner"> <h5 class="task">'+ray+'</h5></div></div>'+rt;
         });
         console.log(rt);
              return rt;
         }

        function ol() {
            console.log("trying to sync");
            $.ajax('${pageContext.servletContext.contextPath}/admin/beginSynchro');
            console.log("voantso");
        }

        function actualiser() {
            console.log("hellooo");
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    // console.dir(this.responseText);
                    let dt = JSON.parse(this.responseText);
                    if (!dt.enCours) {
                        terminee();
                        console.log(dt.lastMessage);
                        clearInterval(boucler);
                        if(dt.lastMessage!=""){
                            document.getElementById("div_anatiny").innerHTML=makediv(dt.lastMessage)+document.getElementById("div_anatiny").innerHTML;
                        }
                    } else {
                        if (dt.termine == dt.total) {
                            clearInterval(actualiser);
                            console.log(dt.lastMessage);
                            $('#progressbar1').width('100%');
                        } else {
                            $('#progressbar1').width(((dt.termine / dt.total) * 100) + '%');
                            encours();
                            console.log(dt.lastMessage);
                        }
                        if(dt.lastMessage!=""){
                            document.getElementById("div_anatiny").innerHTML=makediv(dt.lastMessage)+document.getElementById("div_anatiny").innerHTML;
                        }
                    }
                }
            };
            xhttp.open("GET", "${pageContext.servletContext.contextPath}/admin/statutSynchro", true);
            xhttp.send();
        }
        ol();
        var boucler = setInterval(actualiser, 1000);

    </script>
</body>

</html>
<!-- end document-->
