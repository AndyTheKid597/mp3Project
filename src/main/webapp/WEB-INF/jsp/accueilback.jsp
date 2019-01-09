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
        <title>Accueil</title>
        <spring:url var="lien_assets" value="/admin/assets"/>
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
                            <c:set var = "listeChansons" value = "${valiny.resultats}"/>
                            <div class="row">

                                <div class="col-lg-12">
                                    <!-- DATA TABLE -->
                                    <h3 class="title-5 m-b-35">liste des chansons</h3>
                                    <div class="table-responsive">
                                          
                   
                          
                          
                                        <table class="table table-data2">
                                            <thead>
                                                <tr>
                                                    <th>Nom du fichier</th>
                                                    <th>titre</th>
                                                    <th>auteur</th>
                                                    <th>album</th>
                                                    <th>date</th>
                                                    <th>genre</th>
                                                    <th>duration</th>
                                                    <th>           
                                                            <form:form action="${pageContext.servletContext.contextPath}/admin/ajouterChanson" enctype="multipart/form-data" method="post" id="fileSubmitForm">
                                                                <input name="file" id="fileInputAjout"  type="file" onchange="this.form.submit()" style="display:none;" /> <input type="button" onclick="document.getElementById('fileInputAjout').click()" class="btn btn-outline-primary" value="Ajouter"</input>
                                                        </form:form>
                                                                <button onclick=" window.location.replace('${pageContext.servletContext.contextPath}/admin/syncstat')" class="btn btn-danger" >Synchroniser</button>
                                                    </th>
                                                    
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${listeChansons}" var="sg">
                                                    <tr class="tr-shadow">

                                                        <td>
                                                            ${sg.nomfichier}
                                                        </td>
                                                        <td class="desc">${sg.titre}</td>
                                                        <td>${sg.auteur}</td>
                                                        <td>
                                                            ${sg.album}
                                                        </td>
                                                        <td>${sg.date}</td>
                                                        <td>
                                                            ${sg.genre}
                                                        </td>
                                                        <td>
                                                            ${sg.duration}
                                                        </td>
                                                        <td>
                                                            <div class="table-data-feature">

                                                                <a href="modifier/${sg.id}"> <button class="item" data-toggle="tooltip" data-placement="top" title="" data-original-title="Modifier">
                                                                        <i class="zmdi zmdi-edit"></i>
                                                                    </button>
                                                                </a>
                                                                <a href="supprimer/${sg.id}">    <button class="item" data-toggle="tooltip" data-placement="top" title="" data-original-title="Supprimer">
                                                                        <i class="zmdi zmdi-delete"></i>
                                                                    </button>
                                                                </a>

                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <tr class="spacer"></tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                    <!-- END DATA TABLE -->
                                </div>

                            </div>

                            <div class="row" style="min-height:100px;">
                                                                <div class="col-lg-12">
                                <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
                                        <c:set var="u_param" value=""/>
                                    <c:if test="${!empty param}">

                                        <c:set var="u_param" value="?q=${param}"/>
                                    </c:if>
                                    <c:if test="${valiny.hasPrevious}">
                                        <li class="nav-item">
                                            <a class="nav-link" id="pills-home-tab" data-toggle="pill" href="${pageContext.servletContext.contextPath}/${lien}/${valiny.previous}/10${u_param}}" role="tab" >Precedent</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${valiny.hasNext}">

                                        <li class="nav-item">
                                            <a class="nav-link" id="pills-contact-tab" data-toggle="pill" href="${pageContext.servletContext.contextPath}/${lien}/${valiny.previous}/10${u_param}" role="tab">Suivant</a>
                                        </li>
                                    </c:if>
                                </ul>
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
