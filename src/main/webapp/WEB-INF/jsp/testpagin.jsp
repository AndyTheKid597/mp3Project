<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Chanson Page</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>
<body>

<h3>chanson List</h3>
<c:if test="${!empty resultat.resultats}">
	<table class="tg">
	<tr>
		<th width="80">Chanson ID</th>
		<th width="120">Chanson Name</th>
		<th width="120">Modifier</th>
		<th width="120">lien</th>

	</tr>
	<c:forEach items="${resultat.resultats}" var="sg">
		<tr>
			<td>${sg.id}</td>
			<td>${sg.nomfichier}</td>
			<td><a href="<c:url value='/edit/${sg.id}' />" >Edit</a></td>
                        <td><a href="<c:url value='/res/${sg.nomfichier}' />" >Lien</a></td>
		</tr>
	</c:forEach>
	</table>
</c:if>
<br/>
   
<br>
</body>
</html>