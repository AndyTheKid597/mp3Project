<%-- 
    Document   : index2
    Created on : 30 déc. 2018, 08:37:57
    Author     : ASUS
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FIchiers</title>
        	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
    </head>
    <body>
        <h1>Liste des fichiers dans le dossier ${path}</h1>
    <h3>Liste</h3>
        <c:if test="${!empty liste}">
            <table class="tg">
                <tr>
                    <th width="80">Nom de fichier</th>

                </tr>
                <c:forEach items="${liste}" var="sg">
                    <tr>
                        <td>${sg}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    
    
    </body>
</html>
