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

<h3>chanson List</h3>
<c:if test="${!empty listChansons}">
	<table class="tg">
	<tr>
		<th width="80">Chanson ID</th>
		<th width="120">Chanson Name</th>
                <th width="120">Duration</th>
		<th width="120">Modifier</th>

	</tr>
	<c:forEach items="${listChansons}" var="sg">
		<tr>
			<td>${sg.id}</td>
			<td>${sg.nomfichier}</td>
                        <td>${sg.duration}</td>
			<td><a href="<c:url value='/edit/${sg.id}' />" >Edit</a></td>
		</tr>
	</c:forEach>
	</table>
</c:if>
<br/>
    <h3> ajoutpersonne </h3>
    <c:url var="addAction" value="/add" ></c:url>

<form:form action="${addAction}" commandName="hira">
<table>
	<c:if test="${!empty hira.nomfichier}">
	<tr>
		<td>
			<form:label path="id">
				<spring:message text="ID"/>
			</form:label>
		</td>
		<td>
			<form:input path="id" readonly="true" size="8"  disabled="true" />
			<form:hidden path="id" />
		</td> 
	</tr>
	</c:if>
	<tr>
		<td>
			<form:label path="nomfichier">
				<spring:message text="Nom du fichier"/>
			</form:label>
		</td>
		<td>
			<form:input path="nomfichier" />
		</td> 
	</tr>
        	<tr>
		<td>
			<form:label path="duration">
				<spring:message text="Dration"/>
			</form:label>
		</td>
		<td>
			<form:input path="duration" />
		</td> 
	</tr>

	<tr>
		<td colspan="2">
			<c:if test="${!empty hira.nomfichier}">
				<input type="submit"
					value="<spring:message text="Update chanson"/>" />
			</c:if>
			<c:if test="${empty hira.nomfichier}">
				<input type="submit"
					value="<spring:message text="Ajouter chanson"/>" />
			</c:if>
		</td>
	</tr>
</table>	
</form:form>
<br>
</body>
</html>