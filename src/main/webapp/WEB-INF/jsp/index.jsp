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


<br>
<h3>Persons List</h3>
<c:if test="${!empty listChansons}">
	<table class="tg">
	<tr>
		<th width="80">Chanson ID</th>
		<th width="120">Chanson Name</th>

	</tr>
	<c:forEach items="${listChansons}" var="person">
		<tr>
			<td>${person.id}</td>
			<td>${person.nomfichier}</td>

		</tr>
	</c:forEach>
	</table>
</c:if>
</body>
</html>