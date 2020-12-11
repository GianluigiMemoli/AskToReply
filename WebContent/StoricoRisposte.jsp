<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="content">
	
		<c:forEach var="storicoRisposte" items="${storicoRisposte}">

					<h1>${storicoRisposte.getIdRisposta()}</h1>
					
					</c:forEach>
					 			
</div>