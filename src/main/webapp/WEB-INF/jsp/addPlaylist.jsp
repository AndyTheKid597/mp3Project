<%-- 
    Document   : advancedsearch
    Created on : 15 janv. 2019, 00:50:00
    Author     : Na-tefy
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:url var="lien_assets" value="/site/assets"/>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title -->
    <title>Nopacks</title>

    <!-- Favicon -->
    <link rel="icon" href="${lien_assets}/img/core-img/favicon.ico">

    <!-- Stylesheet -->
    <link rel="stylesheet" href="${lien_assets}/style.css">

</head>

<body>
    <!-- Preloader -->
    <div class="preloader d-flex align-items-center justify-content-center">
        <div class="lds-ellipsis">
            <div></div>
            <div></div>
            <div></div>
            <div></div>
        </div>
    </div>

    <!-- ##### Header Area Start ##### -->
       <jsp:include page="/WEB-INF/jsp/includables2/menu.jsp">
           <jsp:param name="lien_assets" value="${lien_assets}"/>
           </jsp:include>
    <!-- ##### Header Area End ##### -->
            <!-- ##### Contact Area Start ##### -->
    <section class="contact-area section-padding-100 bg-img bg-overlay bg-fixed has-bg-img" style="background-image: url(${lien_assets}/img/bg-img/bg-2.jpg);">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="section-heading white wow fadeInUp" data-wow-delay="100ms">
                        <p></p>
                        <h2>Advanced research</h2>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-12">
                    <!-- Contact Form Area -->
                    <div class="contact-form-area">
                        <form action="${pageContext.servletContext.contextPath}/site/creerPl" method="post">
                            <div class="row">
                                <div class="col-md-6 col-lg-4">
                                    <div class="form-group wow fadeInUp" data-wow-delay="100ms">
                                        <input type="text" class="form-control" id="name" placeholder="Nom de la playlist" name="np">
                                    </div>
                                </div>

                                <div class="col-12 text-center wow fadeInUp" data-wow-delay="500ms">
                                    <button class="btn oneMusic-btn mt-30" type="submit">Creer <i class="fa fa-angle-double-right"></i></button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ##### Contact Area End ##### -->
    <!-- ##### Footer Area Start ##### -->
    <footer class="footer-area">
        <div class="container">
            <div class="row d-flex flex-wrap align-items-center">
                <div class="col-12 col-md-6">
                    <a href="#"><img src="${lien_assets}/img/core-img/logo.png" alt=""></a>
                    <p class="copywrite-text"><a href="#"><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
                </div>

                <div class="col-12 col-md-6">
                    <div class="footer-nav">
                        <ul>
                            <li><a href="#">Home</a></li>
                            <li><a href="#">Albums</a></li>
                            <li><a href="#">Events</a></li>
                            <li><a href="#">News</a></li>
                            <li><a href="#">Contact</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </footer>
    <!-- ##### Footer Area Start ##### -->

    <!-- ##### All Javascript Script ##### -->
    <!-- jQuery-2.2.4 js -->
    <script src="${lien_assets}/js/jquery/jquery-2.2.4.min.js"></script>
    <!-- Popper js -->
    <script src="${lien_assets}/js/bootstrap/popper.min.js"></script>
    <!-- Bootstrap js -->
    <script src="${lien_assets}/js/bootstrap/bootstrap.min.js"></script>
    <!-- All Plugins js -->
    <script src="${lien_assets}/js/plugins/plugins.js"></script>
    <!-- Active js -->
    <script src="${lien_assets}/js/active.js"></script>
    </body>
</html>
