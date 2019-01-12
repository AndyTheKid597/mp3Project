<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html lang="en">
<head>
	<title>Login</title>
         <spring:url var="lien_assets" value="/loginpage/assets"/>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="${lien_assets}/images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${lien_assets}/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${lien_assets}/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${lien_assets}/fonts/iconic/css/material-design-iconic-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${lien_assets}/vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="${lien_assets}/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${lien_assets}/vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${lien_assets}/vendor/select2/select2.min.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="${lien_assets}/vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${lien_assets}/css/util.css">
	<link rel="stylesheet" type="text/css" href="${lien_assets}/css/main.css">
<!--===============================================================================================-->
</head>
<body>
	
	
	<div class="container-login100" style="background-image: url('${lien_assets}/images/bg-01.jpg');">
		<div class="wrap-login100 p-l-55 p-r-55 p-t-80 p-b-30">
			    <form:form action="testconnection" modelAttribute="client" method="post">
				<span class="login100-form-title p-b-37">
					Sign In
				</span>

				<div class="wrap-input100  m-b-20" >
				
                                   
                                     <form:input path="login" class="input100" type="text"  placeholder="login"/>
					
					<span class="focus-input100"></span>
				</div>

				<div class="wrap-input100  m-b-25" >
					
                                           <form:input path="mdp" class="input100" type="password" placeholder="password"/>
					<span class="focus-input100"></span>
				</div>

				<div class="container-login100-form-btn">
					<button class="login100-form-btn">
						Sign In
					</button>
				</div>
				</form:form>
                    <c:if test="${!empty erreur}">
                                <c:if test="${ erreur == '1' }">
                                    <div class="sufee-alert alert with-close alert-danger alert-dismissible fade show">
                                        <span class="badge badge-pill badge-danger">Erreur</span>
                                        Identifiants invalides ou privilège pas assez élevé.
                                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                            <span aria-hidden="true">×</span>
                                        </button>
                                    </div>
                                </c:if>
                                  <c:if test="${ erreur == '2' }">
                                    <div class="sufee-alert alert with-close alert-warning alert-dismissible fade show">
                                        <span class="badge badge-pill badge-danger">Attention</span>
                                        Veuillez d'abord vous identifier.
                                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                            <span aria-hidden="true">×</span>
                                        </button>
                                    </div>
                                </c:if>
                            </c:if>

				<div class="flex-col-c p-t-170 p-b-40">
						<span class="txt1 p-b-9">
							Don?t have an account?
						</span>

						<a href="#" class="txt3">
							Sign up now
						</a>
					</div>
			

			
		</div>
	</div>
	
	

	<div id="dropDownSelect1"></div>
	
<!--===============================================================================================-->
	<script src="${lien_assets}/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="${lien_assets}/vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
	<script src="${lien_assets}/vendor/bootstrap/js/popper.js"></script>
	<script src="${lien_assets}/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="${lien_assets}/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="${lien_assets}/vendor/daterangepicker/moment.min.js"></script>
	<script src="${lien_assets}/vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script src="${lien_assets}/vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
	<script src="${lien_assets}/js/main.js"></script>

</body>
</html>