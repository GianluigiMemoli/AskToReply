<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  	
	<c:if test="${errore != null}">
		<div class="alert alert-danger">${errore}</div>
	</c:if>