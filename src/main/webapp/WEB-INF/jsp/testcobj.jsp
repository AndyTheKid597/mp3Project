<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Person Page</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>
<body>


<c:if test="${!empty resultat}">
    <h3>chanson Creee!</h3>
	<table class="tg">
	<tr>
		<th width="120">Chanson Name</th>
		<th width="120">Chanson duration</th>

	</tr>
		<tr>
			<td>${resultat.nomfichier}</td>
			<td>${resultat.duration}</td>
		</tr>
	</table>
</c:if>
<br/>
    <h3> creerChanson </h3>

<form action="cObjet" method="POST">
<table>

	<tr>
		<td>
			<label>
				Nom du fichier
			</label>
		</td>
		<td>
			<input name="nomfichier" />
		</td> 
	</tr>
	<tr>
		<td>
			<label >
				Longueur
			</label>
		</td>
		<td>
			<input name="duration" />
		</td> 
	</tr>
	<tr>
		<td colspan="2">
				<input type="submit"
					value="Creer Objet" />


		</td>
	</tr>
</table>	
</form>
<br>
</body>
</html>